package space.aow.java.currencies.currencies.service.source;

import org.junit.jupiter.api.Test;
import space.aow.java.currencies.currencies.model.Currency;
import space.aow.java.currencies.currencies.service.source.CbrSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CbrSourceTests {

    @Test
    public void parse() throws Exception {
        CbrSource cbrSource = new CbrSource("https://www.cbr.ru/currency_base/daily/", Arrays.asList(
                new Currency("PLN", "Польский злотый", 0),
                new Currency("CHF", "Швейцарский франк", 0),
                new Currency("RON", "Румынский лей", 0),
                new Currency("CNY", "Китайский юань", 0),
                new Currency("SGD", "Сингапурский доллар", 0)
        ));

        List<Currency> currencies;
        currencies = cbrSource.loadRawData().parseCurrencies();

        for (Currency currency: currencies) {
            assertThat(currency).isNotNull();
            assertThat(currency.getExchange()).isGreaterThan(0);
        }
    }
}
