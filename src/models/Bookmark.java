package models;

import api.request.APIRequests;
import menus.BookmarksMenu;

public class Bookmark {

    private String base_currency;
    private String target_currency;
    private double exchange_rate;

    public Bookmark(String base_currency,String target_currency,double exchange_rate){
        this.base_currency=base_currency;
        this.target_currency=target_currency;
        this.exchange_rate=exchange_rate;
    }

    public boolean update(String json){
        return true;
    }
    @Override
    public String toString(){
        return base_currency+" > "+target_currency+" @ "+exchange_rate;
    }

}
