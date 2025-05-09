package com.oopsmails;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class GetterSetterTestUtils {
    public static void test(Class<?> clazz) throws Exception {
        Object instance = clazz.getDeclaredConstructor().newInstance();

        for (PropertyDescriptor pd : Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors()) {
            Method setter = pd.getWriteMethod();
            Method getter = pd.getReadMethod();

            if (setter != null && getter != null) {
                Object testValue = getDummyValue(pd.getPropertyType());
                setter.invoke(instance, testValue);
                Object result = getter.invoke(instance);

                if (testValue != null && !testValue.equals(result)) {
                    throw new AssertionError("Mismatch for property: " + pd.getName());
                }
            }
        }
    }

    private static Object getDummyValue(Class<?> type) {
        if (type.equals(String.class)) {
            return "test";
        }
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return 123;
        }
        if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return true;
        }
        if (type.equals(long.class) || type.equals(Long.class)) {
            return 123L;
        }
        if (type.equals(double.class) || type.equals(Double.class)) {
            return 12.34;
        }
        if (type.isEnum()) {
            return type.getEnumConstants()[0];
        }
        return null; // skip unknowns
    }
}
