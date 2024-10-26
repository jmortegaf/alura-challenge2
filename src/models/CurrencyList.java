package models;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrencyList {

    private List<String[]> list;

    public CurrencyList(ExchangerateCurrencyList exchangerate_currency_list){
        list=new ArrayList<>(Arrays.asList(exchangerate_currency_list.supported_codes()));
    }

    public CurrencyList(String json_request){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .create();

        ExchangerateCurrencyList exchangerate_currency_list=gson.fromJson(json_request, ExchangerateCurrencyList.class);
        list=new ArrayList<>(Arrays.asList(exchangerate_currency_list.supported_codes()));
    }
    public CurrencyList(List<String[]> list){
        this.list=list;
    }
    public boolean is_empty(){
        return this.list.isEmpty();
    }
    public int size(){
        return this.list.size();
    }

    public String get_currency_id(int index){
        return list.get(index)[0];
    }

    public List<String[]> get_currencies_id(String filter){
        List<String[]> output_list=new ArrayList<>();
        for (String[] currency : list) {
            if (currency[1].toLowerCase().contains(filter.toLowerCase()))
                output_list.add(currency);
        }
        return output_list;
    }

    public String get_currency(int index){
        return "["+list.get(index)[0]+"]"+list.get(index)[1]+".";
    }

}
