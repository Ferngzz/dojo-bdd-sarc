package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.Resource;
import java.util.Optional;

public interface ResourceRepository {
    Optional<Resource> findById(String id);
}
