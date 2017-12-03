package test.example.com.stockapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity
{
    private RecyclerView stockRecyclerView;
    private EditText searchEditText;
    private ProgressBar loadingWheel;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.searchEditText = findViewById(R.id.et_search);
        this.loadingWheel = findViewById(R.id.pb_loading);
        this.goButton = findViewById(R.id.btn_go);

        this.stockRecyclerView = findViewById(R.id.rv_stocks);

        // TODO: adapter

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

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                toggleLoading(false);
            }
        }, 2000);


        // TODO: do actual lookup
    }


}
