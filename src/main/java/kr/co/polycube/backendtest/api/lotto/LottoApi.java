package kr.co.polycube.backendtest.api.lotto;

import kr.co.polycube.backendtest.domain.lotto.LottoEntity;
import kr.co.polycube.backendtest.service.lotto.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class LottoApi {
    private final LottoService lottoService;

    @PostMapping("/lottos")
    public Map<String, List> registerLotto() {
        List<Integer> lotto = lottoService.registerLotto();
        return Map.of("numbers", lotto);
    }

    /*
    @GetMapping("/lottos")
    public List<LottoEntity> findLotto(){
        List<LottoEntity> lottos = lottoService.findLotto();

        System.out.println("number_1 : " + lottos.get(0).getNumber_1());
        return lottos;
    }
    */
}
