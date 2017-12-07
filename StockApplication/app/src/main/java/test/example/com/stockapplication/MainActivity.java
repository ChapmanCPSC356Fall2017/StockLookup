package test.example.com.stockapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity
{
    private final String LOGTAG = "MainActivity";
    private RecyclerView stockRecyclerView;
    private EditText searchEditText;
    private ProgressBar loadingWheel;
    private Button goButton;

    private LookupResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.searchEditText = findViewById(R.id.et_search);
        this.loadingWheel = findViewById(R.id.pb_loading);
        this.goButton = findViewById(R.id.btn_go);

        this.stockRecyclerView = findViewById(R.id.rv_stocks);

        this.adapter = new LookupResultAdapter();
        this.stockRecyclerView.setAdapter(this.adapter);

        this.stockRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        doLookup("science");
    }

    public void onClickGo(View view)
    {
        String searchText = this.searchEditText.getText().toString();
        doLookup(searchText);
    }

    private void toggleLoading(boolean loading)
    {
        this.loadingWheel.setVisibility(loading ? View.VISIBLE : View.INVISIBLE);
        this.goButton.setVisibility(loading ? View.INVISIBLE : View.VISIBLE);
    }

    private void doLookup(String searchText)
    {
        toggleLoading(true);

        StockAPI.Lookup(searchText, new StockAPI.Callback()
        {
            @Override
            public void onFailure(Exception e)
            {
                Log.e(LOGTAG, "Something happened", e);

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        toggleLoading(false);
                    }
                });
            }

            @Override
            public void onResult(final LookupResult[] results)
            {
                Log.i(LOGTAG, "It works i promise");

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        adapter.setResults(results);
                        toggleLoading(false);
                    }
                });
            }
        });
    }


}
