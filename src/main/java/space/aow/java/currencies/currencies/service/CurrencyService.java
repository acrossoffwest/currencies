package space.aow.java.currencies.currencies.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import space.aow.java.currencies.currencies.model.Currency;
import space.aow.java.currencies.currencies.model.Exchange;
import space.aow.java.currencies.currencies.service.source.CbrSource;
import space.aow.java.currencies.currencies.service.source.SberSource;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Component
public class CurrencyService {
    private final CurrencyParserService parser;

    CurrencyService() {
        parser = getNewParser();
    }

    @Cacheable(value = "currencies", sync = true)
    public List<Currency> getAll() throws Exception {
        return parser.parse();
    }

    private CurrencyParserService getNewParser()
    {
        return new CurrencyParserService(List.of(
                new SberSource("https://www.sberbank.ru/proxy/services/rates/public/actual", Arrays.asList(
                        currencyWithDefaultExchange("EUR", "Евро"),
                        currencyWithDefaultExchange("CAD", "Канадский доллар"),
                        currencyWithDefaultExchange("USD", "Доллар США"),
                        currencyWithDefaultExchange("GBP", "Фунт стерлингов"),
                        currencyWithDefaultExchange("JPY", "Японская иена")
                )),
                new CbrSource("https://www.cbr.ru/currency_base/daily/", Arrays.asList(
                        currencyWithDefaultExchange("PLN", "Польский злотый"),
                        currencyWithDefaultExchange("CHF", "Швейцарский франк"),
                        currencyWithDefaultExchange("RON", "Румынский лей"),
                        currencyWithDefaultExchange("CNY", "Китайский юань"),
                        currencyWithDefaultExchange("SGD", "Сингапурский доллар")
                ))
        ));
    }
    
    public Currency currencyWithDefaultExchange(String code, String name)
    {
        return new Currency(code, name, List.of(new Exchange(0, new Timestamp(System.currentTimeMillis()))));
    }
}
