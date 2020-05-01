package com.example.firebasetutorial.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasetutorial.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Student> arrayList;

    public CustomAdapter(ArrayList<Student> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student=(Student) arrayList.get(position);
        holder.nameText.setText(student.getName());
        holder.emailText.setText(student.getEmail());
        holder.regText.setText(student.getRegNumber()+"");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameText,emailText,regText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
                nameText=itemView.findViewById(R.id.name_id);
                emailText=itemView.findViewById(R.id.email_id);
                regText=itemView.findViewById(R.id.registration_id);

        }
    }
}
