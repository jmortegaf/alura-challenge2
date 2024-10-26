package models;

public class SearchCurrencyResult {

    private final boolean valid_search;
    private final String search_result;
    private final String selection_type;

    public SearchCurrencyResult(boolean valid_search, String search_result,String selection_type) {
        this.valid_search = valid_search;
        this.search_result = search_result;
        this.selection_type = selection_type;
    }
    public boolean is_valid(){
        return valid_search;
    }
    public String get_search_result() {
        return search_result;
    }
    public String get_selection_type(){
        return selection_type;
    }
}
