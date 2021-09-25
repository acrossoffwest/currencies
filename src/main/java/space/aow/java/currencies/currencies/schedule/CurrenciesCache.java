package space.aow.java.currencies.currencies.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.aow.java.currencies.currencies.service.CurrencyService;

import java.util.Objects;

@Component
public class CurrenciesCache {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CurrencyService currenciesService;

    @Scheduled(fixedDelay = 60000)
    public void cacheCurrencies() throws Exception {
        Objects.requireNonNull(cacheManager.getCache("currencies")).clear();
        currenciesService.getAll();
    }
}
