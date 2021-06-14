package com.prbansal.authenticationactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prbansal.authenticationactivity.databinding.ActivityMain2Binding;
import com.prbansal.authenticationactivity.databinding.SampleBinding;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.SampleVH> {
    Context context;
    ActivityMain2Binding activityMain2Binding;
    int count=1;
    public static final int PLACED = 1, DELIVERED = 0, DECLINED = -1;

    ArrayList<String> orderIDs;

    public Adapter(Context context, ArrayList<String> ordersIDs,ActivityMain2Binding activityMain2Binding) {
        this.context = context;
        this.orderIDs=ordersIDs;
        this.activityMain2Binding= activityMain2Binding;
    }

    @NonNull
    @Override
    public SampleVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SampleBinding sampleBinding= SampleBinding.inflate(LayoutInflater.from(context),parent,false);
        return new SampleVH(sampleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleVH holder, int position) {
        /*String orderId = orderIDs.get(position);

        SampleBinding binding = ((SampleVH) holder).sampleBinding;
        binding.textView.setText(orderId);

        activityMain2Binding.textView2.setText(position+"");*/
    }
    @Override
    public int getItemCount() {
        return orderIDs.size();
    }
    public class SampleVH extends RecyclerView.ViewHolder {
       SampleBinding sampleBinding;

        public SampleVH(SampleBinding sampleBinding) {
            super(sampleBinding.getRoot());
            this.sampleBinding = sampleBinding;
        }


    }

}
