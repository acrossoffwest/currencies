package space.aow.java.currencies.currencies.service.source;

import org.junit.jupiter.api.Test;
import space.aow.java.currencies.currencies.model.Currency;
import space.aow.java.currencies.currencies.service.source.SberSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;

public class SberSourceTests {

    @Test
    public void parse() throws Exception {
        SberSource sberSource = new SberSource("https://www.sberbank.ru/proxy/services/rates/public/actual", Arrays.asList(
                new Currency("EUR", "Евро", 0),
                new Currency("CAD", "Канадский доллар", 0),
                new Currency("USD", "Доллар США", 0),
                new Currency("GBP", "Фунт стерлингов", 0),
                new Currency("JPY", "Японская иена", 0)
        ));

        List<Currency> currencies;
        currencies = sberSource.loadRawData().parseCurrencies();

        for (Currency currency: currencies) {
            assertThat(currency).isNotNull();
            assertThat(currency.getExchange()).isGreaterThan(0);
        }
    }
}
