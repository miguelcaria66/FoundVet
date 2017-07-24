package com.example.android.foundvet.pets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.foundvet.R;

import java.util.ArrayList;

public class MyPetsAdapter extends ArrayAdapter<PetData> implements View.OnClickListener{

    private ArrayList<PetData> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView petPicture;
        TextView txtPetName;
        TextView txtPetSpecies;
        TextView txtPetAge;
    }

    public MyPetsAdapter(ArrayList<PetData> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        PetData dataModel=(PetData)object;
        mContext.startActivity(new Intent(getContext(), MyPetsActivity.class));
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PetData petData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtPetName = (TextView) convertView.findViewById(R.id.tv_petName);
            viewHolder.txtPetSpecies = (TextView) convertView.findViewById(R.id.tv_petSpecies);
            viewHolder.txtPetAge = (TextView) convertView.findViewById(R.id.tv_petAge);
            viewHolder.petPicture = (ImageView) convertView.findViewById(R.id.iv_petPicture);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.animator.up_from_bottom : R.animator.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtPetName.setText(petData.getPetName());
        viewHolder.txtPetSpecies.setText(petData.getPetSpecies());
        viewHolder.txtPetAge.setText(petData.getPetAge());
        viewHolder.petPicture.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }
}