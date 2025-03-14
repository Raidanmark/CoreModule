package repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    Optional<T> fiindById(Long id);

    T save (T entity);

    List<T> findAll();
}
