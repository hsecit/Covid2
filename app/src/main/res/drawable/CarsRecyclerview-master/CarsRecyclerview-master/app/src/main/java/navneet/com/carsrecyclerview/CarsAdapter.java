package navneet.com.carsrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Navneet Krishna on 11/05/19.
 */
public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    private ArrayList<CarsModel> carsModels=new ArrayList<>();
    private Context context;


    public CarsAdapter(Context context, ArrayList<CarsModel> carsModels) {
        this.carsModels=carsModels;
        this.context=context;
    }

    @NonNull
    @Override
    public CarsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cars_list_item,viewGroup,false);
        return new CarsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.car_name.setText(carsModels.get(i).getName());
        viewHolder.car_desc.setText(carsModels.get(i).getDesc());

        Picasso.get().load(carsModels.get(i).getImage()).into(viewHolder.car_image);
    }

    @Override
    public int getItemCount() {
        return carsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView car_image;
        private TextView car_name,car_desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            car_image=(ImageView)itemView.findViewById(R.id.car_image);
            car_name=(TextView) itemView.findViewById(R.id.car_name);
            car_desc=(TextView)itemView.findViewById(R.id.car_desc);
        }
    }
}
