package space.aow.java.currencies.currencies.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import space.aow.java.currencies.currencies.model.Currency;
import space.aow.java.currencies.currencies.service.source.SberSource;
import space.aow.java.currencies.currencies.service.source.CbrSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParserTests {

    @Autowired
    public CurrencyService currencyService;

    @Test
    public void parse() throws Exception {
        CurrencyParserService parser = new CurrencyParserService(Arrays.asList(
                new SberSource("https://www.sberbank.ru/proxy/services/rates/public/actual", Arrays.asList(
                        currencyService.currencyWithDefaultExchange("EUR", "Евро"),
                        currencyService.currencyWithDefaultExchange("CAD", "Канадский доллар"),
                        currencyService.currencyWithDefaultExchange("USD", "Доллар США")
                )),
                new SberSource("https://www.sberbank.ru/proxy/services/rates/public/actual", Arrays.asList(
                        currencyService.currencyWithDefaultExchange("GBP", "Фунт стерлингов"),
                        currencyService.currencyWithDefaultExchange("JPY", "Японская иена")
                )),
                new CbrSource("https://www.cbr.ru/currency_base/daily/", Arrays.asList(
                        currencyService.currencyWithDefaultExchange("PLN", "Польский злотый"),
                        currencyService.currencyWithDefaultExchange("CHF", "Швейцарский франк"),
                        currencyService.currencyWithDefaultExchange("RON", "Румынский лей"),
                        currencyService.currencyWithDefaultExchange("CNY", "Китайский юань"),
                        currencyService.currencyWithDefaultExchange("SGD", "Сингапурский доллар")
                ))
        ));

        List<Currency> currencies;
        currencies = parser.parse();

        assertThat(currencies.size()).isEqualTo(10);

        for (Currency currency: currencies) {
            assertThat(currency).isNotNull();
            assertThat(currency.getExchanges().get(0).getAmount()).isGreaterThan(0);
        }
    }
}
