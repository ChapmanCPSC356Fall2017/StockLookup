package test.example.com.stockapplication;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class StockAPI
{
    private static final String LOGTAG = "StockAPI";
    private static final String BASE_URL = "http://dev.markitondemand.com/MODApis/Api";

    public static void Lookup(String input)
    {
        String url = BASE_URL + "/Lookup/json?input=" + input;

        OkHttpClient client = new OkHttpClient();

        Request req = new Request.Builder()
                .url(url)
                .build();


        client.newCall(req).enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
                Log.e(LOGTAG, "uh oh something happened", e);
            }

            @Override
            public void onResponse(Response response) throws IOException
            {
                Log.i(LOGTAG, "Got response: " + response.body().string());
            }
        });

        Log.i(LOGTAG, "test");
    }
}
