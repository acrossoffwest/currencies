package space.aow.java.currencies.currencies.service;

import lombok.Setter;
import space.aow.java.currencies.currencies.model.Currency;
import space.aow.java.currencies.currencies.service.source.Source;

import java.util.ArrayList;
import java.util.List;

public class CurrencyParserService {
    private @Setter List<Source> sources;
    private List<Currency> parsedCurrencies = new ArrayList<>();

    public CurrencyParserService(List<Source> sources) {
        setSources(sources);
    }

    public CurrencyParserService addSource(Source source) {
        sources.add(source);
        return this;
    }

    public List<Currency> parse() throws Exception {
        cleanParsedCurrencies();
        for (Source source: sources) {
            List<Currency> sourceParsedCurrencies = source.loadRawData().parseCurrencies();
            parsedCurrencies.addAll(sourceParsedCurrencies);
        }

        return parsedCurrencies;
    }

    private void cleanParsedCurrencies() {
        parsedCurrencies = new ArrayList<>();
    }
}
