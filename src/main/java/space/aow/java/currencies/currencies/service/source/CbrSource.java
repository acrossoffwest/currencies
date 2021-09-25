package space.aow.java.currencies.currencies.service.source;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import space.aow.java.currencies.currencies.model.Currency;

import java.util.List;

public class CbrSource extends  AbstractSource implements Source{
    public CbrSource(String uri, List<Currency> currencies) {
        super(uri, currencies);
    }

    @Override
    public List<Currency> parseCurrencies() {
        Document doc = Jsoup.parse(rawData);
        Elements currenciesRows = doc.select("#content table.data tr");

        for (Currency currency: currencies) {
            for (Element currencyElementRow: currenciesRows)  {
                Elements currencyCellsElements = currencyElementRow.select("td");
                if (currencyCellsElements.size() == 0) {
                    continue;
                }
                if (!currency.getCode().equals(currencyCellsElements.get(1).text())) {
                    continue;
                }
                currency.setExchange(Double.parseDouble(currencyCellsElements.get(4).text().replace(',', '.')));
            }
        }


        return currencies;
    }
}
