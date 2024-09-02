package za.ac.cput.api;

import org.springframework.stereotype.Component;

@Component
public class RoomTypeAPI
{
    public static final String BASE_URL = "/api/roomType";
    public static final String CREATE = BASE_URL + "/create";
    public static final String READ = BASE_URL + "/read/{id}";
    public static final String UPDATE = BASE_URL + "/update";
    public static final String DELETE = BASE_URL + "/delete/{id}";
    public static final String GET_ALL = BASE_URL + "/all";
}
