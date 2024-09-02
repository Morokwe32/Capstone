package za.ac.cput.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import za.ac.cput.exceptions.RoomNotAvailableException;
import za.ac.cput.exceptions.UserCannotRentMoreThanOneRoomException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<String> handleRoomNotAvailableException(RoomNotAvailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserCannotRentMoreThanOneRoomException.class)
    public ResponseEntity<String> handleUserCantRentMoreThanOneRoomException(UserCannotRentMoreThanOneRoomException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
