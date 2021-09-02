package com.magnus.managee.support.dicts;

public enum DataSourceEnum {
    DRUID_URL("druid.url"),
    DRUID_MAXIDLE("druid.maxIdle"),
    DRUID_USERNAME("druid.username"),
    DRUID_PASSWORD("druid.password"),
    DRUID_MAXACTIVE("druid.maxActive"),
    DRUID_INITIALSIZE("druid.initialSize");

    public String name;

    DataSourceEnum(String name) {
        this.name = name;
    }

    public String value() {
        return this.name;
    }
}
