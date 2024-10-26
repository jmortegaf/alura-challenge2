package main;

import api.request.APIRequests;
import io.github.cdimascio.dotenv.Dotenv;

import menus.MenuManager;
import models.CurrencyList;


public class Main {

    public static void main(String[] args) throws Exception{

        Dotenv dotenv = Dotenv.load();
        String API_KEY=dotenv.get("API_KEY");

        String api_url="https://v6.exchangerate-api.com/v6/";
        APIRequests api_requests=new APIRequests(api_url,API_KEY);

        MenuManager menu_manager=new MenuManager();

        System.out.println("Loading Currency List...");
        CurrencyList currency_list=new CurrencyList(api_requests.request("codes",""));

        menu_manager.run(currency_list,api_requests);


    }


}
