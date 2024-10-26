package models;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class History {

    List<Exchange> exchange_history;

    public History(String file_path){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .setPrettyPrinting()
                .create();
        FileReader input = null;
        try {
            input = new FileReader("history.json");
            JsonReader reader=new JsonReader(input);
            Type history=new TypeToken<List<Exchange>>(){}.getType();
            exchange_history=gson.fromJson(reader,history);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public int size(){
        return exchange_history.size();
    }

    public void add(Exchange exchange){
        if(exchange_history==null)
            exchange_history=new ArrayList<>();
        exchange_history.add(exchange);
    }
    public Exchange get_exchange(int index){
        return exchange_history.get(index);
    }

    public void save_history(){
        FileWriter output= null;
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .setPrettyPrinting()
                .create();
        try {
            output = new FileWriter("history.json");
            output.write(gson.toJson(exchange_history));
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
