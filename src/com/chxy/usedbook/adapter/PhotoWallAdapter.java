package com.chxy.usedbook.adapter;


import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import java.util.ArrayList;


import com.chxy.usedbook.activity.R;
import com.chxy.usedbook.utils.SDCardImageLoader;
import com.chxy.usedbook.utils.ScreenUtils;

/*import com.example.myxutils.R;
import com.zfang.utils.SDCardImageLoader;
import com.zfang.utils.ScreenUtils;*/

/**
 * PhotoWall��GridView��������
 *
 * @author zfang
 */
public class PhotoWallAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> imagePathList = null;

    private SDCardImageLoader loader;

   //��¼�Ƿ�ѡ��
    private SparseBooleanArray selectionMap;

    public PhotoWallAdapter(Context context, ArrayList<String> imagePathList) {
        this.context = context;
        this.imagePathList = imagePathList;

        loader = new SDCardImageLoader(ScreenUtils.getScreenW(), ScreenUtils.getScreenH());
        selectionMap = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return imagePathList == null ? 0 : imagePathList.size();
    }

    @Override
    public Object getItem(int position) {
        return imagePathList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String filePath = (String) getItem(position);

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.photo_wall_item, null);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.photo_wall_item_photo);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.photo_wall_item_cb);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //tag��key����ʹ��id�ķ�ʽ�����Ա�֤Ψһ����������IllegalArgumentException.
        holder.checkBox.setTag(R.id.tag_first, position);
        holder.checkBox.setTag(R.id.tag_second, holder.imageView);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer position = (Integer) buttonView.getTag(R.id.tag_first);
                ImageView image = (ImageView) buttonView.getTag(R.id.tag_second);

                selectionMap.put(position, isChecked);
                if (isChecked) {
                    image.setColorFilter(context.getResources().getColor(R.color.image_checked_bg));
                } else {
                    image.setColorFilter(null);
                }
            }
        });

        holder.checkBox.setChecked(selectionMap.get(position));
        holder.imageView.setTag(filePath);
        loader.loadImage(4, filePath, holder.imageView);
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        CheckBox checkBox;
    }

    public SparseBooleanArray getSelectionMap() {
        return selectionMap;
    }

    public void clearSelectionMap() {
        selectionMap.clear();
    }
}
