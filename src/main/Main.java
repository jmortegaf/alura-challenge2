package main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input_text = new Scanner(System.in);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        Gson output_gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .setPrettyPrinting()
                .create();

        String request_uri = "http://www.omdbapi.com/?t=" +
                URLEncoder.encode( movie_name,"UTF-8");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(request_uri))
                .build();
        HttpResponse<String> response= client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        OMDBMediaItem omdb_media_item = gson.fromJson(json, OMDBMediaItem.class);
        System.out.println(omdb_media_item);
        try {
            MediaItem searched_media_item = new MediaItem(omdb_media_item);
            System.out.println(searched_media_item);
            media_item_list.add(searched_media_item);

        }catch (MediaItemRuntimeErrorException e){
            System.out.println(e.get_message());
        }



    }


    public void print_main_menu(boolean first_run){
        if(first_run)System.out.println("Welcome to Alura Currency Exchange");
    }

}
