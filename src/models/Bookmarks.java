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

public class Bookmarks {

    private List<Bookmark> bookmarks;

    public Bookmarks(String file_path){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .setPrettyPrinting()
                .create();
        FileReader input = null;
        try {
            input = new FileReader("bookmarks.json");
            JsonReader reader=new JsonReader(input);
            Type BOOKMARKS=new TypeToken<List<Bookmark>>(){}.getType();
            bookmarks=gson.fromJson(reader,BOOKMARKS);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public int size(){
        if(bookmarks!=null)
            return bookmarks.size();
        return 0;
    }

    public void add(Bookmark bookmark){
        if(bookmarks==null)
            bookmarks=new ArrayList<>();
        bookmarks.add(bookmark);
    }
    public Bookmark get_bookmark(int index){
        return bookmarks.get(index);
    }

    public void save_bookmarks(){
        FileWriter output= null;
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .setPrettyPrinting()
                .create();
        try {
            output = new FileWriter("bookmarks.json");
            output.write(gson.toJson(bookmarks));
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
