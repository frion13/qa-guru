package junit.lesson1.annotation.data;

public enum Languege {
    RU("ЧТО ТАКОЕ SELENIDE?"),
    EN("WHAT IS SELENIDE");
    public final String description;

    Languege(String description) {
        this.description=description;
    }
}
