package com.oopsmails.springboot.unittest5.domain3;

public enum YesNoEnum {
    YES("yes"),
    NO("no");

    private String value;

    YesNoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static YesNoEnum fromValue(String value) {
        for (YesNoEnum yesNo : YesNoEnum.values()) {
            if (yesNo.value.equalsIgnoreCase(value)) {
                return yesNo;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
