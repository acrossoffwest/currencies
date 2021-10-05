package space.aow.java.currencies.currencies.model;

import lombok.Data;
import java.util.List;

@Data
public class Currency {
    private Long id;
    private String code;
    private String name;
    private List<Exchange> exchanges;

    public Currency(String code, String name, List<Exchange> exchanges) {
        setCode(code);
        setName(name);
        setExchanges(exchanges);
    }
}
