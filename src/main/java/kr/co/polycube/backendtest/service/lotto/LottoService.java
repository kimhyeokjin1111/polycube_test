package kr.co.polycube.backendtest.service.lotto;

import kr.co.polycube.backendtest.aspect.LoggingPointCut;
import kr.co.polycube.backendtest.domain.lotto.LottoEntity;
import kr.co.polycube.backendtest.domain.lotto.WinnerEntity;
import kr.co.polycube.backendtest.repository.lotto.LottoRepository;
import kr.co.polycube.backendtest.repository.lotto.WinnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LottoService {
    private final LottoRepository lottoRepository;
    private final WinnerRepository winnerRepository;

    //lotto 등록 API 구현
    public List<Integer> registerLotto(){
        List<Integer> numberes = makeRandNums();
        Collections.sort(numberes);

        LottoEntity lotto = LottoEntity.builder()
                .number_1(numberes.get(0))
                .number_2(numberes.get(1))
                .number_3(numberes.get(2))
                .number_4(numberes.get(3))
                .number_5(numberes.get(4))
                .number_6(numberes.get(5))
                .build();

        lottoRepository.save(lotto);

        return numberes;
    }

    public List<Integer> makeRandNums() {
        final int NUM_COUNT = 6; // lotto 개수
        final int BOUND = 45; // lotto 범위
        Random random = new Random();
        List<Integer> randNums = new ArrayList<>();

        while (randNums.size() < NUM_COUNT) {
            int randNum = random.nextInt(BOUND) + 1;

            if (!randNums.contains(randNum)) {
                randNums.add(randNum);
            }
        }

        return randNums;
    }

    public List<LottoEntity> findLotto(){
        return lottoRepository.findAll();
    }


    //----------------------------------------------------------------------


    // lotto 당첨자 등록
    public WinnerEntity makeWinnerEntity(LottoEntity userLottoEntity, Set<Integer> winnerLotto, List<Integer> rankList) {

        Set<Integer> userLottoSet = new HashSet<>();
        userLottoSet.add(userLottoEntity.getNumber_1());
        userLottoSet.add(userLottoEntity.getNumber_2());
        userLottoSet.add(userLottoEntity.getNumber_3());
        userLottoSet.add(userLottoEntity.getNumber_4());
        userLottoSet.add(userLottoEntity.getNumber_5());
        userLottoSet.add(userLottoEntity.getNumber_6());

        System.out.println("###################userLotto = " + userLottoSet);

        int matchCnt = 0;
        for (int num : userLottoSet) {
            if (winnerLotto.contains(num)) {
                matchCnt++;
            }
        }

        System.out.println(userLottoEntity.getId()+"번쨰 로또 matchCnt = " + matchCnt);

        int rank = -1;
        if(rankList.indexOf(matchCnt) != -1){
            rank = rankList.indexOf(matchCnt);
        }

        return WinnerEntity.builder()
                .lotto_id(userLottoEntity.getId())
                .rank(rank + 1)
                .build();

        //winnerRepository.save(winner);
    }

    // 로또 등수 리스트 반환 함수
    public List<Integer> makeLottoRankList(Set<Integer> winnerLotto) {
        List<Integer> rank = new ArrayList<>();
        Set<LottoEntity> lottoSet = new HashSet<>(lottoRepository.findAll());

        Set<Integer> lotto;

        for(LottoEntity entity : lottoSet) {
            lotto = new HashSet<>();

            lotto.add(entity.getNumber_1());
            lotto.add(entity.getNumber_2());
            lotto.add(entity.getNumber_3());
            lotto.add(entity.getNumber_4());
            lotto.add(entity.getNumber_5());
            lotto.add(entity.getNumber_6());

            int matchCnt = 0;
            for (int num : lotto) {
                if (winnerLotto.contains(num)) {
                    matchCnt++;
                }
            }

            if (!rank.contains(matchCnt)) {
                rank.add(matchCnt);
            }
        }

        if(rank.size() > 5){
            rank.remove(rank.size() -1);
        }

        Collections.sort(rank, Comparator.reverseOrder());

        return rank;
    }

}
