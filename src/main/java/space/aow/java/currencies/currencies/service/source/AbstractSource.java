package space.aow.java.currencies.currencies.service.source;

import space.aow.java.currencies.currencies.model.Currency;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public abstract class AbstractSource implements Source {
    protected URI uri;
    protected String rawData;
    protected List<Currency> currencies;

    AbstractSource(String uri, List<Currency> currencies) {
        setUri(uri);
        setCurrencies(currencies);
    }

    public Source setUri(String uri) {
        this.uri = URI.create(uri);
        return this;
    }

    public Source setUri(URI uri) {
        this.uri = uri;
        return this;
    }

    public URI getUri() {
        return uri;
    }

    public Source setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }

    @Override
    public Source setRawData(String rawData) {
        this.rawData = rawData;
        return this;
    }

    @Override
    public String getRawData() {
        return rawData;
    }

    public abstract List<Currency> parseCurrencies();
}
