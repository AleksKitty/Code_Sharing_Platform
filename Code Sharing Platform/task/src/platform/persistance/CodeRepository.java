package platform.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.businesslayer.Code;

import java.util.List;

@Repository
public interface CodeRepository extends CrudRepository<Code, String> {
    Code findCodeById(String id);
    List<Code> findAll();
}
