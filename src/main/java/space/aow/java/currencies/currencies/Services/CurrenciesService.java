package space.aow.java.currencies.currencies.Services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import space.aow.java.currencies.currencies.Services.Parser.Models.Currencies;
import space.aow.java.currencies.currencies.Services.Parser.Models.Currency;
import space.aow.java.currencies.currencies.Services.Parser.Parser;
import space.aow.java.currencies.currencies.Services.Parser.Sources.CbrSource;
import space.aow.java.currencies.currencies.Services.Parser.Sources.SberSource;

import java.util.Arrays;
import java.util.List;

@Component
public class CurrenciesService {
    private Parser parser;

    CurrenciesService() {
        parser = new Parser(List.of(
                new SberSource("https://www.sberbank.ru/proxy/services/rates/public/actual", Arrays.asList(
                        new Currency("EUR", "Евро", 0),
                        new Currency("CAD", "Канадский доллар", 0),
                        new Currency("USD", "Доллар США", 0),
                        new Currency("GBP", "Фунт стерлингов", 0),
                        new Currency("JPY", "Японская иена", 0)
                )),
                new CbrSource("https://www.cbr.ru/currency_base/daily/", Arrays.asList(
                        new Currency("PLN", "Польский злотый", 0),
                        new Currency("CHF", "Швейцарский франк", 0),
                        new Currency("RON", "Румынский лей", 0),
                        new Currency("CNY", "Китайский юань", 0),
                        new Currency("SGD", "Сингапурский доллар", 0)
                ))
        ));
    }

    @Cacheable(value = "currencies", sync = true)
    public Currencies getCurrencies() throws Exception {
        return new Currencies(parser.parse());
    }
}
