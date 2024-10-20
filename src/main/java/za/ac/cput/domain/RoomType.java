package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "roomtype")
public class RoomType {

    @Id
    private Integer id;

    @Column(length = 50, nullable = false)
    private String type;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob // Used to indicate that the picture is a large object, like an image.
    @Column(columnDefinition = "LONGBLOB")
    private byte[] picture;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
