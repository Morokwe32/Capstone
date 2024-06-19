package za.ac.cput.service;

public interface iService <T, ID>
{
    T create(T enitity);
    T read(ID Id);
    T update(T entity);
    void delete(T entity);
}