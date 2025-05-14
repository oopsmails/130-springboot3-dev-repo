package com.oopsmails;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Slf4j
public class PackageClassLister {

    public static List<Class<?>> getClassesInPackage(String packageName) throws ClassNotFoundException, IOException {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File directory = new File(resource.getFile());
            if (directory.exists()) {
                findClasses(directory, packageName, classes);
            }
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName, List<Class<?>> classes) throws ClassNotFoundException {
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return classes;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                findClasses(file, packageName + '.' + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                classes.add(Class.forName(className));
            }
        }
        return classes;
    }

    public static void main(String[] args) throws Exception {
        String packageName = "com.oopsmails.springboot.unittest5";
        List<Class<?>> allClasses = getClassesInPackage(packageName);
        allClasses.forEach(clazz -> log.info("All Classes: {}", clazz.getName()));

        List<Class<?>> classes = new ArrayList<>();
        List<Class<?>> eNums = new ArrayList<>();
        List<Class<?>> testClasses = new ArrayList<>();

        for (Class<?> clazz : allClasses) {
            if (clazz.isEnum()) {
                eNums.add(clazz);
            } else if (clazz.getName().endsWith("Test")) {
                testClasses.add(clazz);
            } else {
                classes.add(clazz);
            }
        }

        classes.forEach(clazz -> log.info("Normal Classes: {}", clazz.getName()));
        eNums.forEach(clazz -> log.info("Enums: {}", clazz.getName()));
        testClasses.forEach(clazz -> log.info("Test Classes: {}", clazz.getName()));
    }
}