package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne //many Bookings to one user
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne //many Bookings to one room
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDateTime inDate;
    private LocalDateTime outDate;

    public Booking()
    {

    }

    public Booking(Builder builder)
    {
        this.id = builder.id;
        this.user = builder.user;
        this.room = builder.room;
        this.inDate = builder.inDate;
        this.outDate = builder.outDate;
    }

    //Getters
    public int getId()
    {
        return id;
    }

    public User getUser()
    {
        return user;
    }

    public Room getRoom()
    {
        return room;
    }

    public LocalDateTime getInDate()
    {
        return inDate;
    }

    public LocalDateTime getOutDate()
    {
        return outDate;
    }

    //Setters

    public void setId(int id)
    {
        this.id = id;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public void setInDate(LocalDateTime inDate)
    {
        this.inDate = inDate;
    }

    public void setOutDate(LocalDateTime outDate)
    {
        this.outDate = outDate;
    }

    @Override
    public String toString()
    {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", room=" + room +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                '}';
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private User user;
        private Room room;
        private LocalDateTime inDate;
        private LocalDateTime outDate;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder room(Room room) {
            this.room = room;
            return this;
        }

        public Builder InDate(LocalDateTime inDate) {
            this.inDate = inDate;
            return this;
        }

        public Builder OutDate(LocalDateTime outDate) {
            this.outDate = outDate;
            return this;
        }


        public Booking build() {
            return new Booking(this);
        }

        public Builder copy(Booking booking) {
            this.id = booking.id;
            this.user = booking.user;
            this.room = booking.room;
            this.inDate = booking.inDate;
            this.outDate = booking.outDate;
            return this;
        }
    }
}
