package pl.sda.fitpossible.entity;

public enum LifestyleType {
    INACTIVE ("inactive"),
    LOW_ACTIVE ("low active"),
    MODERATELY_ACTIVE ("moderately active"),
    ACTIVE ("active"),
    VERY_ACTIVE ("very active");

    private String name;

    LifestyleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
