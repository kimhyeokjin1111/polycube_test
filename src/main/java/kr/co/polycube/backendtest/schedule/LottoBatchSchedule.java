package kr.co.polycube.backendtest.schedule;

import kr.co.polycube.backendtest.batch.LottoBatchConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LottoBatchSchedule {
    /*
    private final JobLauncher jobLauncher;
    private final LottoBatchConfig lottoBatchConfig;

    @Scheduled(cron = "0 0 0 ? * SUN")
    public void bookRegister(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(lottoBatchConfig.lottoJob(), jobParameters);
        } catch (Exception e) {
            log.error("job error");
        }
    }
    */

}
