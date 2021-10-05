package space.aow.java.currencies.currencies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.aow.java.currencies.currencies.model.Currency;
import space.aow.java.currencies.currencies.service.CurrencyService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
	@Autowired
	private CurrencyService currenciesService;

	@GetMapping("")
	public List<Currency> index() throws Exception {
		return currenciesService.getAll();
	}

	@GetMapping("/history/{start}/{end}")
	public String currenciesExchangeHistory(@PathVariable String start, @PathVariable String end) throws Exception {
		String uri = "http://localhost:8081/currencies-storage/history/" + start.replace(" ", "%20") + "/" + end.replace(" ", "%20");
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(uri))
				.GET()
				.build();
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
}
