package models;

public class Bookmark {

    private String base_currency;
    private String target_currency;

    public Bookmark(String base_currency,String target_currency){
        this.base_currency=base_currency;
        this.target_currency=target_currency;
    }

    public boolean update(String json){
        return true;
    }
    @Override
    public String toString(){
        return base_currency+" > "+target_currency;
    }
    public boolean equals(Bookmark bookmark){
         return bookmark.target_currency.equals(target_currency) && bookmark.base_currency.equals(base_currency);
    }

}