package space.aow.java.currencies.currencies.service.source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import space.aow.java.currencies.currencies.model.Currency;
import space.aow.java.currencies.currencies.service.CurrencyService;
import space.aow.java.currencies.currencies.service.source.SberSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;

public class SberSourceTests {

    @Autowired
    public CurrencyService currencyService;
    
    @Test
    public void parse() throws Exception {
        SberSource sberSource = new SberSource("https://www.sberbank.ru/proxy/services/rates/public/actual", Arrays.asList(
                currencyService.currencyWithDefaultExchange("EUR", "Евро"),
                currencyService.currencyWithDefaultExchange("CAD", "Канадский доллар"),
                currencyService.currencyWithDefaultExchange("USD", "Доллар США"),
                currencyService.currencyWithDefaultExchange("GBP", "Фунт стерлингов"),
                currencyService.currencyWithDefaultExchange("JPY", "Японская иена")
        ));

        List<Currency> currencies;
        currencies = sberSource.loadRawData().parseCurrencies();

        for (Currency currency: currencies) {
            assertThat(currency).isNotNull();
            assertThat(currency.getExchanges().get(0).getAmount()).isGreaterThan(0);
        }
    }
}
