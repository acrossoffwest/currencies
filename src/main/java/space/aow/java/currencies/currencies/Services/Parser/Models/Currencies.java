package space.aow.java.currencies.currencies.Services.Parser.Models;

import java.time.LocalDateTime;
import java.util.List;

public class Currencies {
    private List<Currency> currencies;
    private LocalDateTime updatedAt;

    public Currencies(List<Currency> currencies) {
        setCurrencies(currencies);
        setUpdatedAt(LocalDateTime.now());
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public int getTotal() {
        return currencies.size();
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
