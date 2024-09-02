package za.ac.cput.exceptions;

public class UserCannotRentMoreThanOneRoomException extends RuntimeException
{
    public UserCannotRentMoreThanOneRoomException(String message) {
        super(message);
    }
}
