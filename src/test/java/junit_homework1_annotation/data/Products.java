package junit_homework1_annotation.data;

public enum Products {
    BACKPACK("Sauce Labs Backpack", "$29.99",
            "carry.allTheThings() with the sleek, streamlined Sly Pack " +
                    "that melds uncompromising style with unequaled laptop and tablet protection."),
    BIKE("Sauce Labs Bike Light", "$9.99",
            "A red light isn't the desired state in testing but it sure helps when riding " +
                    "your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."),
    JACKET("Sauce Labs Fleece Jacket", "$49.99",
            "It's not every day that you come across a midweight quarter-zip fleece jacket" +
                    " capable of handling everything from a relaxing day outdoors to a busy day at the " +
                    "office.");
    public final String title;
    public final String price;
    public final String description;


    Products(String title, String price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;

    }
}
