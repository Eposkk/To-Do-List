package ntnu.team1.application.task;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {
    private final int ID;
    private Color color;
    private String name;
    private long serialVersionUID;

    public Category(int ID, Color color, String name) {
        this.ID = ID;
        this.color = color;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(color, category.color) &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, color, name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "ID=" + ID +
                ", color=" + color +
                ", name='" + name + '\'' +
                '}';
    }
}
