package za.ac.cput.repository;

public interface iRepository<T, ID>
{
    T create(T entity);
    T read(ID id);
    T update(T entity);
    boolean delete(ID id);
}
