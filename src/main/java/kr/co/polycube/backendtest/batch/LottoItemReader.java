package kr.co.polycube.backendtest.batch;

import kr.co.polycube.backendtest.domain.lotto.LottoEntity;
import kr.co.polycube.backendtest.service.lotto.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

@RequiredArgsConstructor
public class LottoItemReader implements ItemReader<LottoEntity> {
    private final LottoService LottoService;

    private int nextIdx = 0;
    private List<LottoEntity> items;

    @Override
    public LottoEntity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (items == null){
            LottoService.findLotto();
        }

        LottoEntity nextItem = null;
        if (nextIdx < items.size()){
            nextItem = items.get(nextIdx);
            nextIdx++;
        }else{
            items = null;
            nextIdx = 0;
        }

        return nextItem;
    }
}
