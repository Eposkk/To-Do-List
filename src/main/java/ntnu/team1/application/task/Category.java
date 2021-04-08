package ntnu.team1.application.task;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class that represents a category
 * Implements serializable to be able to store the data
 */

public class Category implements Serializable {
    private final int ID;
    private double r;
    private double g;
    private double b;
    private String name;
    private long serialVersionUID;

    /**
     *
     * @param ID Integer that represents the category`s ID
     * @param color Color object that represents the color of the category
     * @param name String that represents the name of the category
     */
    public Category(int ID, Color color, String name) {
        this.ID = ID;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
        this.name = name;
    }

    /**
     *
     * @return returns the ID of the category
     */

    public int getID() {
        return ID;
    }

    /**
     *
     * @return returns the color of the category
     */

    public Color getColor() {
        Color color = new Color(0,r,g,b);
        return color;
    }

    /**
     *
     * @return returns the name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * update the color of the category
     * @param color
     */

    public void setColor(Color color) {
        this.r = color.getRed();
        this.g= color.getGreen();
        this.b = color.getBlue();
    }

    /**
     * updates the name of the category
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method checks similarity: two Category objects are equal if their name and color are identical
     * Identical similarity - two references to the same object
     * Content similarity - the content of two objects is compared by category name and color
     * @param o
     * @return returns true if similarity, false if inequality.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(r, category.r) &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, r,g,b, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "ID=" + ID +
                ", color=" + r +g+b+
                ", name='" + name + '\'' +
                '}';
    }
}
