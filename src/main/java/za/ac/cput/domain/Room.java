package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Room
{
    @Id
    private String roomNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomTypeId", referencedColumnName = "typeId")
    private RoomType roomType;
    private int capacity;
    private boolean isOccupied;

    protected Room() {
    }

    private Room(Builder builder) {
        this.roomNumber = builder.roomNumber;
        this.roomType = builder.roomType;
        this.capacity = builder.capacity;
        this.isOccupied = builder.isOccupied;

    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Room room)) return false;
        return capacity == room.capacity && isOccupied == room.isOccupied && Objects.equals(roomNumber, room.roomNumber) && Objects.equals(roomType, room.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, roomType, capacity, isOccupied);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber= '" + roomNumber + '\'' +
                ", roomType=" + roomType + '\'' +
                ", capacity=" + capacity +
                ", isOccupied=" + isOccupied + '}';

    }

    public static class Builder {
        private String roomNumber;
        private RoomType roomType;
        private int capacity;
        private boolean isOccupied;


        public Builder setRoomNumber (String roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder setRoomType(RoomType roomType) {
            this.roomType = roomType;
            return this;
        }

        public Builder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder setOccupied(boolean occupied) {
            isOccupied = occupied;
            return this;
        }

        public Builder copy(Room room) {
            this.roomNumber = room.roomNumber;
            this.roomType = room.roomType;
            this.capacity = room.capacity;
            this.isOccupied = room.isOccupied;
            return this;
        }

        public Room build() {
            return new Room(this);

        }
    }
}
