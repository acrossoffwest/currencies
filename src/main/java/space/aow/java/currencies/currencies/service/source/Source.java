package space.aow.java.currencies.currencies.service.source;

import space.aow.java.currencies.currencies.model.Currency;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public interface Source {
    Source setUri(String uri);
    Source setUri(URI uri);
    URI getUri();
    Source setRawData(String rawData);
    String getRawData();
    Source setCurrencies(List<Currency> currencies);

    default Source loadRawData() throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(this.getUri())
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Error: Source loading were unsuccessful. Status code: " + response.statusCode() + ";\nRequest body:\n" + response.body());
        }

        setRawData(response.body());

        return this;
    }


    List<Currency> parseCurrencies();
}
