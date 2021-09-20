package space.aow.java.currencies.currencies.Services.Parser.Sources;

import space.aow.java.currencies.currencies.Services.Parser.Models.Currency;

import java.util.List;

public interface Source {
    public Source setUri(String uri);
    public String getUri();
    public Source setCurrencies(List<Currency> currencies);
    public Source loadRawData() throws Exception;
    public List<Currency> parseCurrencies();
}
