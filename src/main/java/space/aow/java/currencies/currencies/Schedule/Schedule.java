package space.aow.java.currencies.currencies.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.aow.java.currencies.currencies.Services.CurrenciesService;

import java.util.Objects;

@Component
public class Schedule {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CurrenciesService currenciesService;

    @Scheduled(fixedDelay = 60000)
    public void cacheCurrencies() throws Exception {
        Objects.requireNonNull(cacheManager.getCache("currencies")).clear();
        currenciesService.getCurrencies();
    }
}
