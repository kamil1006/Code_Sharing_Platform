package platform.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import platform.entity.Wpis2;

import java.util.List;

public interface Wpis2Repository extends CrudRepository<Wpis2, Long> {
    Wpis2 findByDate(String date);
    List<Wpis2> findTop10ByOrderByIdDesc();
    List<Wpis2> findAll(Sort id);
    Wpis2 findById(int id);
    List<Wpis2> findTop1ByOrderByIdDesc();

    @Query(value= "SELECT TOP 10 * FROM tablica_wpisow WHERE restrykcje=false ORDER BY id DESC;",nativeQuery = true)
    List<Wpis2> findTop10ByOrderByIdDescBezRestrykcji();

    Wpis2 findByLinq(String linq);




}
