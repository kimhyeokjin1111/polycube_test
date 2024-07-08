package kr.co.polycube.backendtest.repository.lotto;

import kr.co.polycube.backendtest.domain.lotto.LottoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRepository extends JpaRepository<LottoEntity, Long> {
}
