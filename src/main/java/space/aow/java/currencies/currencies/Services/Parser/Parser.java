package space.aow.java.currencies.currencies.Services.Parser;

import space.aow.java.currencies.currencies.Services.Parser.Models.Currency;
import space.aow.java.currencies.currencies.Services.Parser.Sources.Source;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<Source> sources;
    private List<Currency> parsedCurrencies = new ArrayList<>();

    public Parser(List<Source> sources) {
        setSources(sources);
    }

    public Parser setSources(List<Source> sources) {
        this.sources = sources;
        return this;
    }

    public Parser addSource(Source source) {
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
