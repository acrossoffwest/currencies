package space.aow.java.currencies.currencies.service.source;

import space.aow.java.currencies.currencies.model.Currency;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.*;
import space.aow.java.currencies.currencies.model.Exchange;

public class SberSource extends  AbstractSource implements Source{

    public SberSource(String uri, List<Currency> currencies) {
        super(uri, currencies);
    }

    @Override
    public Source loadRawData() throws Exception {
        String uriStr = replaceGetParams(getUri());
        StringBuilder uri = new StringBuilder(uriStr);
        uri.append("?rateType=ERNP-2");

        for (Currency currency: currencies) {
            uri.append("&isoCodes[]=").append(currency.getCode());
        }
        setUri(uri.toString());
        return super.loadRawData();
    }

    private String replaceGetParams(String uriStr) {
        int questionMarkIndex = uriStr.indexOf("?");
        if (questionMarkIndex != -1) {
            uriStr = uriStr.substring(0, questionMarkIndex);
        }
        return uriStr;
    }

    @Override
    public List<Currency> parseCurrencies() {
        JSONObject cursObj = new JSONObject(rawData);
        for (Currency currency: currencies) {
            JSONArray rateList = cursObj.getJSONObject(currency.getCode().toUpperCase(Locale.ROOT))
                    .getJSONArray("rateList");

            List<Exchange> exchanges = new ArrayList<>();
            Exchange exchange = new Exchange(rateList.getJSONObject(0).getDouble("rateBuy"), new Timestamp(System.currentTimeMillis()));
            exchanges.add(exchange);
            currency.setExchanges(exchanges);
        }

        return currencies;
    }
}
