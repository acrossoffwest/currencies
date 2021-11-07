package space.aow.java.currencies.currencies.service.source;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import space.aow.java.currencies.currencies.model.Currency;
import space.aow.java.currencies.currencies.model.Exchange;

import java.sql.Timestamp;
import java.util.ArrayList;
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
                List<Exchange> exchanges = new ArrayList<>();
                Exchange exchange = new Exchange(Double.parseDouble(currencyCellsElements.get(4).text().replace(',', '.')), new Timestamp(System.currentTimeMillis()));
                exchanges.add(exchange);
                currency.setExchanges(exchanges);
            }
        }


        return currencies;
    }
}
