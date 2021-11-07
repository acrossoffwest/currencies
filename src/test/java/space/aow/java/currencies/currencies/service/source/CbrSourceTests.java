package space.aow.java.currencies.currencies.service.source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import space.aow.java.currencies.currencies.model.Currency;
import space.aow.java.currencies.currencies.service.CurrencyService;
import space.aow.java.currencies.currencies.service.source.CbrSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CbrSourceTests {

    @Autowired
    public CurrencyService currencyService;

    @Test
    public void parse() throws Exception {
        CbrSource cbrSource = new CbrSource("https://www.cbr.ru/currency_base/daily/", Arrays.asList(
                currencyService.currencyWithDefaultExchange("PLN", "Польский злотый"),
                currencyService.currencyWithDefaultExchange("CHF", "Швейцарский франк"),
                currencyService.currencyWithDefaultExchange("RON", "Румынский лей"),
                currencyService.currencyWithDefaultExchange("CNY", "Китайский юань"),
                currencyService.currencyWithDefaultExchange("SGD", "Сингапурский доллар")
        ));

        List<Currency> currencies;
        currencies = cbrSource.loadRawData().parseCurrencies();

        for (Currency currency: currencies) {
            assertThat(currency).isNotNull();
            assertThat(currency.getExchanges().get(0).getAmount()).isGreaterThan(0);
        }
    }
}
