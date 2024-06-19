package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class RoomType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeId;
    private String roomtypeName;
    private double roomPrice;

    protected RoomType()
    {

    }

    private RoomType(Builder builder)
    {
        this.typeId = builder.typeId;
        this.roomtypeName = builder.roomtypeName;
        this.roomPrice = builder.roomPrice;
    }



    public long getTypeId()
    {
        return typeId;
    }

    public String getRoomtypeName()
    {
        return roomtypeName;
    }

    public double getRoomPrice()
    {
        return roomPrice;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return getTypeId() == roomType.getTypeId() && Double.compare(getRoomPrice(), roomType.getRoomPrice()) == 0 && Objects.equals(getRoomtypeName(), roomType.getRoomtypeName());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getTypeId(), getRoomtypeName(), getRoomPrice());
    }

    @Override
    public String toString()
    {
        return "RoomType{" +
                "typeId=" + typeId +
                ", roomtypeName='" + roomtypeName + '\'' +
                ", roomPrice=" + roomPrice +
                '}';
    }

    public static class Builder
    {
        private long typeId;
        private String roomtypeName;
        private double roomPrice;

        public Builder setTypeId(long typeId)
        {
            this.typeId = typeId;
            return this;
        }

        public Builder setRoomtypeName(String roomtypeName)
        {
            this.roomtypeName = roomtypeName;
            return this;
        }

        public Builder setRoomPrice(double roomPrice)
        {
            this.roomPrice = roomPrice;
            return this;
        }

        public Builder copy(RoomType roomtype)
        {
            this.typeId = roomtype.typeId;
            this.roomtypeName = roomtype.roomtypeName;
            this.roomPrice = roomtype.roomPrice;
            return this;
        }

        public RoomType build()
        {
            return new RoomType(this);
        }
    }
}
