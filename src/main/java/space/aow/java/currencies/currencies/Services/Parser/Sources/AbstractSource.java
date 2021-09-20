package space.aow.java.currencies.currencies.Services.Parser.Sources;

import space.aow.java.currencies.currencies.Services.Parser.Models.Currency;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public abstract class AbstractSource implements Source {
    private URI uri;
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

    public String getUri() {
        return uri.toString();
    }

    public Source setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }

    public Source loadRawData() throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(this.uri)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Error: Source loading were unsuccessful. Status code: " + String.valueOf(response.statusCode()) + ";\nRequest body:\n" + response.body());
        }

        rawData = response.body();

        return this;
    }

    public abstract List<Currency> parseCurrencies();
}
