package co.ingdjason.youtubeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import co.ingdjason.youtubeapp.Model.Video;
import co.ingdjason.youtubeapp.R;

/**
 * Created by ingdjason on 2/15/18.
 */

public class VideoArrayAdapter extends ArrayAdapter<Video> {


    public VideoArrayAdapter(Context context, ArrayList<Video> vdeos) {
        super(context, android.R.layout.simple_list_item_1, vdeos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the Showbiz
        Video infoItem = getItem(position);

        // find or inflate the template
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_list, parent, false);
        }

        //nom bank
        TextView tvTitle =  convertView.findViewById(R.id.tvTitle);
        TextView tvDesc = convertView.findViewById(R.id.tvDesc);
        ImageView ivThumb = convertView.findViewById(R.id.ivThumb);

        tvTitle.setText(infoItem.getTitle());
        tvDesc.setText(infoItem.getDescription());

        //https://guides.codepath.com/android/Displaying-Images-with-the-Glide-Library
        Glide.with(getContext())
                .load(infoItem.getThumbnailsMedium())
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter() // scale to fit entire image within ImageView
                .into(ivThumb);


        return convertView;
    }
}
