package space.aow.java.currencies.currencies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.aow.java.currencies.currencies.model.Currency;
import space.aow.java.currencies.currencies.service.CurrencyService;

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
}
