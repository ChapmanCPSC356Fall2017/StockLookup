package test.example.com.stockapplication;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

public class StockAPI
{
    private static final String LOGTAG = "StockAPI";
    private static final String BASE_URL = "http://dev.markitondemand.com/MODApis/Api";

    public static void Lookup(String input, final Callback callback)
    {
        String url = BASE_URL + "/Lookup/json?input=" + input;

        OkHttpClient client = new OkHttpClient();

        Request req = new Request.Builder()
                .url(url)
                .build();

        client.newCall(req).enqueue(new com.squareup.okhttp.Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Response response) throws IOException
            {
                String json = response.body().string();

                Gson gson = new Gson();
                LookupResult[] lookupResults = gson.fromJson(json, LookupResult[].class);

                callback.onResult(lookupResults);
            }
        });
    }

    public interface Callback
    {
        void onFailure(Exception e);
        void onResult(LookupResult[] results);
    }
}
