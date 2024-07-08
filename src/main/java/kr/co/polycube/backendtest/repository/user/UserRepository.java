package kr.co.polycube.backendtest.repository.user;

import kr.co.polycube.backendtest.domain.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
