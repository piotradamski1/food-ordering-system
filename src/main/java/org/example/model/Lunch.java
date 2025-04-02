package org.example.model;

public class Lunch {

    private final MainCourse mainCourse;
    private final Dessert dessert;

    public Lunch(MainCourse mainCourse, Dessert dessert) {
        this.mainCourse = mainCourse;
        this.dessert = dessert;
    }

    public MainCourse getMainCourse() {
        return mainCourse;
    }

    public Dessert getDessert() {
        return dessert;
    }

    public double getTotalPrice() {
        double mainCoursePrice = mainCourse.getPrice();
        if (dessert != null) {
            return getPriceWithDessert(mainCoursePrice);
        }

        return mainCoursePrice;
    }

    public String getName() {
        String mainCourseName = mainCourse.getName();
        if (dessert != null) {
            return getNameWithDessert(mainCourseName);
        }

        return mainCourseName;
    }

    private double getPriceWithDessert(double price) {
        double dessertPrice = dessert.getPrice();
        return price + dessertPrice;
    }

    private String getNameWithDessert(String mainCourseName) {
        String dessertName = dessert.getName();
        return mainCourseName + " & " + dessertName;
    }

}
