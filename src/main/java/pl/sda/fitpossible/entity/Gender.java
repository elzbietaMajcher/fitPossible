package pl.sda.fitpossible.entity;

public enum Gender {
    FEMALE("Female"),
    MALE("Male");
    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
