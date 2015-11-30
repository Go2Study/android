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


public class CustomAdapter extends ArrayAdapter<String>
{
    Context context;
    int[] images;
    String[] nameArray;
    String[] roomsArray;
    TextView textViewName;
    TextView textViewRooms;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int img[], String[] names, String[] rooms) {
        super(context,R.layout.custom_row,names);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.images = img;

        this.nameArray = names;
        this.roomsArray = rooms;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_row, null);

            holder.txtName = (TextView) convertView.findViewById(R.id.textViewNames);
            holder.txtRooms = (TextView) convertView.findViewById(R.id.textViewRooms);
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            //holder.txtViewDescription = (TextView) convertView.findViewById(R.id.txtViewDescription);
            holder.txtName.setText(nameArray[position]);
            holder.txtRooms.setText(roomsArray[position]);
            holder.imageView.setImageResource(images[position]);
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
        return convertView;
    }
}

class ViewHolder
{
    TextView txtName;
    TextView txtRooms;
    ImageView imageView;

}




