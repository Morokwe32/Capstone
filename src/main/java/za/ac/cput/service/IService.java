package za.ac.cput.service;

public interface IService<T, ID>
{
    T create(T enitity);
    T read(ID Id);
    T update(T entity);
    void delete(T entity);
}