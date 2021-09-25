package space.aow.java.currencies.currencies.service.source;

import space.aow.java.currencies.currencies.model.Currency;

import java.util.List;

public interface Source {
    Source setUri(String uri);
    String getUri();
    Source setCurrencies(List<Currency> currencies);
    Source loadRawData() throws Exception;
    List<Currency> parseCurrencies();
}
