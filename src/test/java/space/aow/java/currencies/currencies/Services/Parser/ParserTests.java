package space.aow.java.currencies.currencies.Services.Parser;

import org.junit.jupiter.api.Test;
import space.aow.java.currencies.currencies.Services.Parser.Models.Currency;
import space.aow.java.currencies.currencies.Services.Parser.Sources.SberSource;
import space.aow.java.currencies.currencies.Services.Parser.Sources.CbrSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParserTests {

    @Test
    public void parse() throws Exception {
        Parser parser = new Parser(Arrays.asList(
                new SberSource("https://www.sberbank.ru/proxy/services/rates/public/actual", Arrays.asList(
                        new Currency("EUR", "Евро", 0),
                        new Currency("CAD", "Канадский доллар", 0),
                        new Currency("USD", "Доллар США", 0)
                )),
                new SberSource("https://www.sberbank.ru/proxy/services/rates/public/actual", Arrays.asList(
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

        List<Currency> currencies;
        currencies = parser.parse();

        assertThat(currencies.size()).isEqualTo(10);

        for (Currency currency: currencies) {
            assertThat(currency).isNotNull();
            assertThat(currency.getExchange()).isGreaterThan(0);
        }
    }
}
