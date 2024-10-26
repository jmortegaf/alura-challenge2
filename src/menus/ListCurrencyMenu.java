package menus;

import api.request.APIRequests;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.CurrencyList;
import models.ExchangerateCurrencyList;

import java.io.IOException;
import java.util.Scanner;

public class ListCurrencyMenu {


    public void show_menu(CurrencyList currency_list){
        int page=0;
        Scanner scanner=new Scanner(System.in);

        while (true){
            System.out.println("=========================================");
            System.out.println(currency_list.get_currency(page));
            System.out.println(currency_list.get_currency(page+1));
            System.out.println(currency_list.get_currency(page+2));
            System.out.println(currency_list.get_currency(page+3));
            System.out.println(currency_list.get_currency(page+4));
            System.out.println("=========================================");
            System.out.println("[N]ext/[P]revious/[B]ack main menu");
            String user_input=scanner.nextLine();
            if(user_input.equalsIgnoreCase("b"))break;
            else if(user_input.equalsIgnoreCase("n"))page+=5;
            else if(user_input.equalsIgnoreCase("p"))page-=5;
        }
    }



    public String print_currency_list_menu(Scanner scanner, APIRequests api_requests) throws InterruptedException, IOException {
        int page=0;
        int user_selection;
        String selection="";

        String json=api_requests.request("codes","");

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .create();

        ExchangerateCurrencyList exchangerate_currency_list=gson.fromJson(json, ExchangerateCurrencyList.class);
        CurrencyList currency_list=new CurrencyList(exchangerate_currency_list);



        while (true){
            System.out.println("[N]ext/[P]revious/[S]elect Base Currency/S[e]lect Target Currency/[Q]uit");
            System.out.println(currency_list.get_currency(page));
            System.out.println(currency_list.get_currency(page+1));
            System.out.println(currency_list.get_currency(page+2));
            System.out.println(currency_list.get_currency(page+3));
            System.out.println(currency_list.get_currency(page+4));
            String user_input=scanner.nextLine();
            if(user_input.equalsIgnoreCase("q"))break;
            else if(user_input.equalsIgnoreCase("n"))page+=5;
            else if(user_input.equalsIgnoreCase("p"))page-=5;
            else if(user_input.equalsIgnoreCase("s")){
                System.out.print("Selection:");
                user_input=scanner.nextLine();
                user_selection=Integer.parseInt(user_input);
                System.out.println("You selected "+currency_list.get_currency_id(page+user_selection-1)+
                        ". Confirm [Y]es/[N]o?");
                user_input=scanner.nextLine();
                if(user_input.equalsIgnoreCase("y")){
                    selection=currency_list.get_currency_id(page+user_selection-1);
                    break;
                }
            }
        }
        return selection;
    }


}
