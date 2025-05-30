package org.example.chromaglambackend.DAO;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;

@Entity
@Table(name = "outfit_items")
@Data
@Setter
@Getter
public class Outfit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long item_id;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "style")
    private String style;

    @Column(name = "available")
    private int available;

    public Outfit() {}

    public Outfit(String image, String description, String style)
    {
        this.image = image;
        this.description = description;
        this.style = style;
    }

    //getters and setters

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public long getItem_id() {
        return item_id;
    }

    @Override
    public String toString()
    {
        return this.item_id + "\t" + this.description + "\t" + this.style + "\t" + this.available;
    }
}
