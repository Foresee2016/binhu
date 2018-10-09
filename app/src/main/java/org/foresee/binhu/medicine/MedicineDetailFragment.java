package org.foresee.binhu.medicine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import org.foresee.binhu.R;
import org.foresee.binhu.model.Medicine;
import org.foresee.binhu.share.Utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MedicineDetailFragment extends Fragment {
    private static final String TAG = "MedicineDetailFragment";

    public static MedicineDetailFragment newInstance(Medicine medicine){
        MedicineDetailFragment fragment=new MedicineDetailFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(ARG_MEDICINE, medicine);
        fragment.setArguments(bundle);
        return fragment;
    }
    private static final String ARG_MEDICINE="medicine";
    private Medicine mMedicine;
    private ImageView mThumbnail;
    private TextView mName, mTaste, mFunc;
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Serializable s=getArguments().getSerializable(ARG_MEDICINE);
        if(!(s instanceof Medicine)){
            return super.onCreateView(inflater,container,savedInstanceState);
        }
        mMedicine= (Medicine) s;
        View view=inflater.inflate(R.layout.fragment_medicine_detail, container, false);
        mThumbnail=view.findViewById(R.id.thumbnail);
        mName=view.findViewById(R.id.medicine_name);
        mTaste=view.findViewById(R.id.taste);
        mFunc=view.findViewById(R.id.func);
        mRecyclerView=view.findViewById(R.id.recycler_view);
        try {
            String encode=URLEncoder.encode(mMedicine.getThumbnail(), "utf8");
            //下载缩略图
            Glide.with(this)
                    .load(Utils.URL_PREFIX+ encode)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(Utils.thumbnailOptions())
                    .into(mThumbnail);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mName.setText(mMedicine.getName());
        mTaste.setText(mMedicine.getTaste());
        mFunc.setText(mMedicine.getFunc());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new ImageCardAdapter(mMedicine.getImages()));
        return view;
    }
    private class ImageCardHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mLabel;
        public ImageCardHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.image);
            mLabel=itemView.findViewById(R.id.label);
        }
        public void bind(String imageName){
            //下载其它图片
            Glide.with(mImageView)
                    .load(Utils.URL_PREFIX+imageName)
                    .apply(Utils.thumbnailOptions())
                    .into(mImageView);
            mLabel.setText(imageName);
        }
    }
    private class ImageCardAdapter extends RecyclerView.Adapter<ImageCardHolder>{
        private String mImages;
        private String[] mImageArr;
        public ImageCardAdapter(String images) {
            Log.i(TAG, "ImageCardAdapter: 此中药其它图片："+images);
            mImages = images;
            if(images!=null && !images.trim().equals("")){
                mImageArr=mImages.split(",");
            }else {
                mImageArr=new String[0];
            }
        }

        @NonNull
        @Override
        public ImageCardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            View view=inflater.inflate(R.layout.image_card, viewGroup, false);
            return new ImageCardHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageCardHolder viewHolder, int i) {
            viewHolder.bind(mImageArr[i]);
        }

        @Override
        public int getItemCount() {
            return mImageArr.length;
        }
    }
}
