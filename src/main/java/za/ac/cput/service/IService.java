package za.ac.cput.service;
import java.util.*;

public interface IService<T, ID>
{
    T create(T t);
    T read(ID id);
    T update(T t);
    boolean delete(ID id);
    //Set<T> getAll();

}