package models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Exchange {

    private String base_currency;
    private String target_currency;
    private double exchange_rate;
    private double exchange_amount;
    private String exchange_date;

    public Exchange(String base_currency, String target_currency, double exchange_rate, double exchange_amount) {
        this.base_currency = base_currency;
        this.target_currency = target_currency;
        this.exchange_rate = exchange_rate;
        this.exchange_amount = exchange_amount;

        DateTimeFormatter dft=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        this.exchange_date=dft.format(now);
    }

    @Override
    public String toString() {
        DecimalFormatSymbols symbols=new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df=new DecimalFormat("#0.00",symbols);

        return "["+exchange_date+"] "+exchange_amount+" "+base_currency
                +" > "+df.format(exchange_amount*exchange_rate)+" "+target_currency+" @ "+exchange_rate;
    }
}
