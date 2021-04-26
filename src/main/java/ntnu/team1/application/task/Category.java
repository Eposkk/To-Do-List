
package ntnu.team1.application.task;

import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.Objects;

/**
 * A class that represents a category
 * Implements serializable to be able to store the data
 */

public class Category implements Serializable {
    /**
     * Id for the category
     */
    private final int ID;
    /**
     * Red value
     */
    private double r;
    /**
     * Green value
     */
    private double g;
    /**
     * Blue value
     */
    private double b;
    /**
     * Name of the category
     */
    private String name;
    /**
     * Id used for storing
     */
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
        this.serialVersionUID = 98128L;
    }

    /**
     * Gets the category id
     * @return returns the ID of the category
     */

    public int getID() {
        return ID;
    }

    /**
     * Gets the category color
     * @return returns the color of the category
     */

    public Color getColor() {
        return Color.color(r,g,b);
    }

    /**
     * Gets the name
     * @return returns the name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Update the color of the category, sets it to int values. This gives us the ability to serialize the object
     * @param color Color
     */

    public void setColor(Color color) {
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
    }

    /**
     * updates the name of the category
     * @param name Name as a string
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method checks similarity: two Category objects are equal if their name and color are identical
     * Identical similarity - two references to the same object
     * Content similarity - the content of two objects is compared by category name and color
     * @param o Object that is comparing to
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

    /**
     * To string for object
     * @return All relevant information for the object
     */

    @Override
    public String toString() {
        return "Category{" +
                "ID=" + ID +
                ", color=" + r +g+b+
                ", name='" + name + '\'' +
                '}';
    }
}
