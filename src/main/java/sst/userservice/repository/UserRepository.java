package sst.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sst.userservice.model.User;

@Repository
@Component
public interface UserRepository extends JpaRepository<User,Long> {
}
