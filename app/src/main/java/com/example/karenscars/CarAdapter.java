package com.example.karenscars;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {
    private List<Car> Cars;
    public CarAdapter(List<Car> cars) {
        this.Cars = cars;
    }
    public void AddCar(Car car) {
        Cars.add(car);
        notifyDataSetChanged();
        DataPersistencyHelper.StoreData(Cars);
    }
    public void DeleteCar(int position) {
        Cars.remove(position);
        notifyDataSetChanged();
        DataPersistencyHelper.StoreData(Cars);
    }
    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car,parent,false);
        CarViewHolder vh = new CarViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = Cars.get(position);
        holder.avatar.setImageResource(car.getImage());
        holder.brand.setText(car.getBrand());
        holder.model.setText(car.getModel());
        holder.year.setText(car.getYear());
        holder.price.setText(car.getPrice());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity2.class);
                i.putExtra("car", car);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(),
                                holder.avatar,
                                "avatarTransition"
                        );
                v.getContext().startActivity(i,options.toBundle());
            }
        });
    }
    @Override
    public int getItemCount() {
        return Cars.size();
    }
}
