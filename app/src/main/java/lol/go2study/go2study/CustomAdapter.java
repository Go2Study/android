package lol.go2study.go2study;

/**
 * Created by Todor on 11/16/2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import FontysICT.Models.Person;

public class CustomAdapter extends  ArrayAdapter<Person>
{

    //Used to display People in the UI
    public CustomAdapter(Context context, Person[] people)
    {
        super(context,R.layout.custom_row ,people);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customViewRow = inflater.inflate(R.layout.custom_row, parent, false);

        Person singleItem = getItem(position);
        TextView textView =(TextView)customViewRow.findViewById(R.id.textView);
        //ImageView imageView = (ImageView)customViewRow.findViewById(R.id.);

        textView.setText(singleItem.toString());
        //imageView.setImageResource(R.drawable.boy);
        return customViewRow;

    }


}


