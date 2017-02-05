package com.tmasuda.fc.ctrl;

import org.springframework.stereotype.Controller;

import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CurrencyCtrl {

    public Set<Currency> getList() {
        HashSet<Currency> availableCurrencies = new HashSet<Currency>();
        availableCurrencies.add(Currency.getInstance("EUR"));
        availableCurrencies.add(Currency.getInstance("USD"));
        availableCurrencies.add(Currency.getInstance("JPY"));
        return availableCurrencies;
    }

}
