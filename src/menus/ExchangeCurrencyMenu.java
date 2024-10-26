package menus;

import api.request.APIRequests;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class ExchangeCurrencyMenu {



    public String show_menu(CurrencyList currency_list, APIRequests api_request){
        Scanner scanner=new Scanner(System.in);
        String user_input;
        String base_currency="";
        String target_currency="";
        double conversion_rate=0;
        String s_conversion_rate="";
        SearchCurrencyResult search_result;
        History history=new History("history.json");

        while (true){
            System.out.println("================================================");
            System.out.println("[F]ind/[M]odify Selections/[E]xchange/[R]eturn");
            System.out.println("Base Currency: "+base_currency+" / Target Currency: "+target_currency+" @ "+s_conversion_rate);
            System.out.println("================================================");
            System.out.print(":>");
            user_input=scanner.nextLine();
            if(user_input.equalsIgnoreCase("r"))break;
            else if(user_input.equalsIgnoreCase("f")) {
                System.out.print("Filter :>");
                user_input = scanner.nextLine();
                CurrencyList filtered_currencies = new CurrencyList(currency_list.get_currencies_id(user_input));
                if (filtered_currencies.is_empty()) System.out.println("No matching currencies");
                else {
                    search_result = show_results(filtered_currencies);
                    if (search_result.is_valid()) {
                        if(search_result.get_selection_type().equalsIgnoreCase("base")){
                            base_currency=search_result.get_search_result();
                        }
                        else{
                            target_currency=search_result.get_search_result();
                        }
                        if(!base_currency.isBlank() && !target_currency.isEmpty()){
                            try {
                                String json=api_request.request("latest",base_currency);
                                Gson gson = new GsonBuilder()
                                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                                        .setPrettyPrinting()
                                        .create();
                                ExchangerateExchange exchangerate_exchange=gson.fromJson(json,ExchangerateExchange.class);
                                conversion_rate=exchangerate_exchange.conversion_rates().get(target_currency);
                                s_conversion_rate= String.valueOf(conversion_rate);
                            } catch (IOException | InterruptedException e) {
                                System.out.println("Error fetching data from API");
                            }
                        }
                    }
                }
            }
            else if(user_input.equalsIgnoreCase("e")){
                System.out.print("Amount:>");
                user_input=scanner.nextLine();
                try{
                    DecimalFormatSymbols symbols=new DecimalFormatSymbols(Locale.getDefault());
                    symbols.setDecimalSeparator('.');
                    DecimalFormat df=new DecimalFormat("#0.00",symbols);

                    double amount=Double.parseDouble(user_input);
                    if(amount>0){
                        System.out.println(amount+" "+base_currency+" > "+
                                df.format(amount*conversion_rate)+
                                " "+target_currency+" @ "+conversion_rate);

                        Exchange exchange=new Exchange(base_currency,target_currency,conversion_rate,amount);
                        history.add(exchange);
                        history.save_history();
                    }
                    else System.out.println("Invalid amount.");

                }catch (NumberFormatException e){
                    System.out.println("Invalid amount.");
                }
            }
        }
        return user_input;
    }

    private static SearchCurrencyResult show_results(CurrencyList currencyList){
        int page=0;
        int pages=(int)Math.ceil(currencyList.size()/5.0);

        while(true) {
            int max_value=page!=pages-1?5:currencyList.size()-(5*(page));
            System.out.println("=============================================");
            System.out.println("[S]elect/[N]ext page/[P]revious page/[R]eturn");
            System.out.println("Page "+(page+1)+"/"+pages);
            System.out.println("=============================================");

            for (int i = 0; i < max_value; i++)
                System.out.println("[" + (i + 1) + "] " + currencyList.get_currency((page*5) + i));
            System.out.print(":>");
            Scanner scanner = new Scanner(System.in);
            String user_input = scanner.nextLine();
            if (user_input.equalsIgnoreCase("r")) break;
            else if(user_input.equalsIgnoreCase("n")){
                page++;
                if(page==pages)page=0;
            }
            else if(user_input.equalsIgnoreCase("p")){
                page--;
                if(page<0)page=pages-1;
            }
            else if(user_input.equalsIgnoreCase("s")){
                System.out.print("Selection:>");
                user_input=scanner.nextLine();
                try{
                    int selection=Integer.parseInt(user_input);
                    if(selection>0 && selection<=max_value){
                        System.out.println("Select as [B]ase/[T]arget currency/[R]eturn");
                        System.out.print(":>");
                        user_input=scanner.nextLine();
                        if(user_input.equalsIgnoreCase("r"))continue;
                        else if(user_input.equalsIgnoreCase("b"))
                            return new SearchCurrencyResult(true,currencyList.get_currency_id((page*5)+selection-1),"BASE");
                        else if(user_input.equalsIgnoreCase("t"))
                            return new SearchCurrencyResult(true,currencyList.get_currency_id((page*5)+selection-1),"TARGET");
                        else System.out.println("Invalid selection.");
                    }

                }catch (NumberFormatException e){
                    System.out.println("Invalid selection");
                }
            }
            else System.out.println("Invalid selection.");
        }
        return new SearchCurrencyResult(false,"","");
    }


}
