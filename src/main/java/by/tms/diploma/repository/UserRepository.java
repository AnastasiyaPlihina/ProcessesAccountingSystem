package by.tms.diploma.repository;

import by.tms.diploma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    @Query(value = "select * from users where department_id is not null", nativeQuery = true)
    List<User> findAllEmployees();

}
