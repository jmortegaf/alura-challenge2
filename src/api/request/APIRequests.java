package api.request;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIRequests {

    private final String api_url;
    private final String api_key;

    public APIRequests(String api_url, String api_key) {
        this.api_url = api_url;
        this.api_key = api_key;
    }

    public String request(String api_endpoint,String data) throws IOException, InterruptedException {
        String request_uri=api_url+api_key+"/"+api_endpoint+"/"+data;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(request_uri))
                .build();
        HttpResponse<String> response= client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
