package org.example.chromaglambackend.DTO;

public class OutfitRequest
{
    private long item_id;
    private String image;
    private String description;
    private String style;
    private int available;

    public OutfitRequest(String image, String description, String style)
    {
        this.image = image;
        this.description = description;
        this.style = style;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public long getItem_id() {
        return item_id;
    }

    public int getAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }

    public String getStyle() {
        return style;
    }

    public String getImage() {
        return image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
