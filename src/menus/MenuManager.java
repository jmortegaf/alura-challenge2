package menus;

import api.request.APIRequests;
import models.Bookmark;
import models.CurrencyList;

public class MenuManager {

    private MainMenu main_menu;
    private BookmarksMenu bookmarks_menu;
    private ExchangeCurrencyMenu exchange_currency_menu;
    private HistoryMenu history_menu;

    public MenuManager(){
        main_menu=new MainMenu();
        bookmarks_menu=new BookmarksMenu();
        exchange_currency_menu=new ExchangeCurrencyMenu();
        history_menu=new HistoryMenu();
    }

    public void run(CurrencyList currency_list, APIRequests api_request){
        String user_input;
        Bookmark bookmark;
        while (true){
            user_input = main_menu.show_menu();
            if(user_input.equalsIgnoreCase("q"))break;
            else if(user_input.equalsIgnoreCase("b")){
                bookmark=bookmarks_menu.show_menu();
                if(bookmark.is_valid()) {
                    exchange_currency_menu.exchange_from_bookmark(bookmark,api_request);
                }
            }
            else if(user_input.equalsIgnoreCase("e"))
                user_input=exchange_currency_menu.show_menu(currency_list,api_request);
            else if(user_input.equalsIgnoreCase("h"))
                history_menu.show_menu();
        }
    }

}
