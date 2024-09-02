package za.ac.cput.domain;

/*
*Room.java
 *Domain class for Room page
 *Author: Jade John Arendse
 *Student Number: 220282188
 * */

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Room {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roomType;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private PriceGroup priceGroup;
    private boolean isAvailable;

    protected Room() {
    }

    public Room(int i, String roomType, String economy, double v, boolean b, int i1, String s, PriceGroup priceGroup) {
        this.id = id;
        this.roomType = roomType;
        this.capacity = capacity;
        this.priceGroup = priceGroup;
        this.isAvailable = isAvailable;
    }

    public Room(Builder builder) {
        this.id = builder.id;
        this.roomType = builder.roomType;
        this.capacity = builder.capacity;
        this.priceGroup = builder.priceGroup;
        this.isAvailable = builder.isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public PriceGroup getPriceGroup() {
        return priceGroup;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setPriceGroup(PriceGroup priceGroup) {
        this.priceGroup = priceGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return getId() == room.getId() && getCapacity() == room.getCapacity() && isAvailable() == room.isAvailable() && Objects.equals(getRoomType(), room.getRoomType()) && getPriceGroup() == room.getPriceGroup();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoomType(), getCapacity(), getPriceGroup(), isAvailable());
    }

    public String getPriceGroupString() {
        return priceGroup != null ? priceGroup.toString() : "NONE";
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", capacity=" + capacity +
                ", priceGroup=" + priceGroup +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String roomType;
        private int capacity;
        private PriceGroup priceGroup;
        private boolean isAvailable;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder roomType(String roomType) {
            this.roomType = roomType;
            return this;
        }

        public Builder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder priceGroup(PriceGroup priceGroup) {
            this.priceGroup = priceGroup;
            return this;
        }

        public Builder available(boolean available) {
            this.isAvailable = available;
            return this;
        }

        public Room build() {
            return new Room(this);
        }

        public Builder copy(Room room) {
            this.id = room.id;
            this.roomType = room.roomType;
            this.capacity = room.capacity;
            this.priceGroup = room.priceGroup;
            this.isAvailable = room.isAvailable;
            return this;
        }
    }
}
