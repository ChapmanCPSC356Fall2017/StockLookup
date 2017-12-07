package test.example.com.stockapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LookupResultAdapter extends RecyclerView.Adapter<LookupResultAdapter.LookupResultViewHolder>
{
    private LookupResult[] results = new LookupResult[] {};

    @Override
    public LookupResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new LookupResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LookupResultViewHolder holder, int position)
    {
        LookupResult result = this.results[position];
        holder.init(result);
    }

    @Override
    public int getItemCount()
    {
        return this.results.length;
    }

    public void setResults(LookupResult[] results)
    {
        this.results = results;
        notifyDataSetChanged();
    }

    class LookupResultViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView;

        public LookupResultViewHolder(View itemView)
        {
            super(itemView);
            this.textView = (TextView) itemView;
        }

        public void init(LookupResult result)
        {
            this.textView.setText(result.name);
        }
    }
}
