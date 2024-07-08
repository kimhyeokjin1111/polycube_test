package kr.co.polycube.backendtest.batch;

import kr.co.polycube.backendtest.domain.lotto.LottoEntity;
import kr.co.polycube.backendtest.domain.lotto.WinnerEntity;
import kr.co.polycube.backendtest.repository.lotto.WinnerRepository;
import kr.co.polycube.backendtest.service.lotto.LottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LottoBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final WinnerRepository winnerRepository;
    private final LottoService lottoService;

    @Bean
    public ItemReader<LottoEntity> lottoItemReader(){
        return new LottoItemReader(lottoService);
    }

    @Bean
    public ItemProcessor<LottoEntity, WinnerEntity> lottoItemProcessor(){
        return item -> {
            Set<Integer> randNums = new HashSet<>(lottoService.makeRandNums());
            List<Integer> rankList = lottoService.makeLottoRankList(randNums);
            return lottoService.makeWinnerEntity(item, randNums, rankList);
        };
    }

    @Bean
    public ItemWriter<WinnerEntity> lottoItemWriter(){
        return items -> {


            for (WinnerEntity item : items) {
                winnerRepository.save(item);
            }
        };
    }

    @Bean
    public Step lottoStep(){
        return new StepBuilder("lottoStep", jobRepository)
                .<LottoEntity, WinnerEntity>chunk(10, transactionManager)
                .reader(lottoItemReader())
                .processor(lottoItemProcessor())
                .writer(lottoItemWriter())
                .build();
    }

    @Bean
    public Job lottoJob(){
        return new JobBuilder("lottoJob", jobRepository)
                .start(lottoStep())
                .preventRestart()
                .build();

    }
}
