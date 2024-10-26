package models;

import java.util.Map;

public record ExchangerateExchange(String result, Map<String, Double> conversion_rates) {
}
