package kr.co.polycube.backendtest.repository.lotto;

import kr.co.polycube.backendtest.domain.lotto.WinnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<WinnerEntity, Long> {
}
