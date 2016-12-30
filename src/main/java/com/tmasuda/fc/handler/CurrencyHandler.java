package com.tmasuda.fc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Currency;
import java.util.Set;

@RequestMapping("/currency")
@Controller
public class CurrencyHandler {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Set<Currency> getList() {
        return Currency.getAvailableCurrencies();
    }

}
