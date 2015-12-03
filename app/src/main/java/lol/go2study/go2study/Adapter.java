package lol.go2study.go2study;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    int position;
    private  LayoutInflater inflater;
    List<Data> data = Collections.emptyList();
    Context context;
    public Adapter(Context context, List<Data> data) {

        this.context = context;
        inflater =   LayoutInflater.from(context);
        this.data = data;


    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View view=  inflater.inflate(R.layout.custom_row,parent, false);
        Log.v("ADAPTER", "onCreateHolder called");
        MyViewHolder holder= new MyViewHolder(view);
        return  holder;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        this.position = position;
        Data currentData = data.get(position);
        Log.v("ADAPTER", "onBindingViewHolder called");
        holder.title.setText(currentData.title);
        holder.icon.setImageResource(currentData.iconId);


        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item clicked at " + position, Toast.LENGTH_SHORT ).show();
                Log.v("YES YES ", "");
                context.startActivity(new Intent());
            }
        });
        //item click
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item clicked at " + position, Toast.LENGTH_SHORT ).show();
                Log.v("YES YES ", "");
                //context.startActivity(new Intent(context.getClass().this, PeopleActivity.class));
                //startActivity(new Intent(, HomeActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.nameTextView);
            icon = (ImageView)itemView.findViewById(R.id.list_item);
            icon.setOnClickListener(this);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Item clicked at " + getPosition(), Toast.LENGTH_SHORT ).show();

        }
    }
}
