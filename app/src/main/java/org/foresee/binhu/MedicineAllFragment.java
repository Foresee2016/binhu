package org.foresee.binhu;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.foresee.binhu.data.MedicineLab;
import org.foresee.binhu.model.Medicine;
import org.foresee.binhu.web.ThumbnailDownloader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class MedicineAllFragment extends Fragment {
    private static final String TAG = "MedicineAllFragment";

    public static MedicineAllFragment newInstance(){
        return new MedicineAllFragment();
    }
//    private Context mContext;
    private List<Medicine> mMedicines;
    private RecyclerView mRecyclerView;
    private MedicineCardAdapter mMedicineCardAdapter;
    private ThumbnailDownloader<MedicineCardHolder> mThumbnailDownloader;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.recycler_view, container, false);
        mMedicines=MedicineLab.getMedicineLab(getActivity()).getMedicines();
        mRecyclerView=view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMedicineCardAdapter=new MedicineCardAdapter(mMedicines);
        mRecyclerView.setAdapter(mMedicineCardAdapter);

        Handler responseHandler = new Handler();
        mThumbnailDownloader = new ThumbnailDownloader<>(responseHandler);
        mThumbnailDownloader.setThumbnailDownloadListener(new ThumbnailDownloader.ThumbnailDownloadListener<MedicineCardHolder>() {
            @Override
            public void onThumbnailDownloaded(MedicineCardHolder obj, Bitmap thumbnail) {
                Drawable drawable=new BitmapDrawable(getResources(), thumbnail);
                obj.bindThumbnail(drawable);
            }

            @Override
            public void onError(MedicineCardHolder obj) {
                Drawable err=ResourcesCompat.getDrawable(getResources(),R.drawable.thumbnail_download_err, null);
                obj.bindThumbnail(err);
            }
        });
        mThumbnailDownloader.start();
        mThumbnailDownloader.getLooper();
        Log.i(TAG, "Background thread started");
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailDownloader.quit();
        Log.i(TAG, "Background thread destroyed");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailDownloader.clearQueue();
    }

    public void freshDbToUI(){
        mMedicines=MedicineLab.getMedicineLab(getActivity()).getMedicines();
        mMedicineCardAdapter.setMedicines(mMedicines);
        mMedicineCardAdapter.notifyDataSetChanged();
    }
    private class MedicineCardHolder extends RecyclerView.ViewHolder{
        private TextView mName, mTaste, mFunc;
        private ImageView mThumbnail;
        public MedicineCardHolder(@NonNull View itemView) {
            super(itemView);
            mName =itemView.findViewById(R.id.medicine_name);
            mTaste=itemView.findViewById(R.id.taste);
            mFunc=itemView.findViewById(R.id.func);
            mThumbnail=itemView.findViewById(R.id.thumbnail);
        }
        public void bind(Medicine medicine){
            mName.setText(medicine.getName());
            mTaste.setText(medicine.getTaste());
            mFunc.setText(medicine.getFunc());
        }
        public void bindThumbnail(Drawable drawable){
            mThumbnail.setImageDrawable(drawable);
        }
    }
    private static final String URL_PREFIX="http://47.93.12.228:8080/binhu/upload/files/";
    private class MedicineCardAdapter extends RecyclerView.Adapter<MedicineCardHolder>{
        private List<Medicine> mMedicines;

        public MedicineCardAdapter(List<Medicine> medicines) {
            mMedicines = medicines;
        }

        public void setMedicines(List<Medicine> medicines) {
            mMedicines = medicines;
        }

        @NonNull
        @Override
        public MedicineCardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            View view=inflater.inflate(R.layout.medicine_card, viewGroup, false);
            return new MedicineCardHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MedicineCardHolder medicineCardHolder, int i) {
            medicineCardHolder.bind(mMedicines.get(i));
            Drawable placeholder=ResourcesCompat.getDrawable(getResources(), R.drawable.medicine_img_holder, null);
            medicineCardHolder.bindThumbnail(placeholder);
//            mThumbnailDownloader.queueThumbnail(medicineCardHolder, URL_PREFIX+mMedicines.get(i).getThumbnail());
//            try {
//                mThumbnailDownloader.queueThumbnail(medicineCardHolder, URL_PREFIX+ URLEncoder.encode(mMedicines.get(i).getThumbnail(), "utf8"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            mThumbnailDownloader.queueThumbnail(medicineCardHolder, "http://47.93.12.228:8080/binhu/upload/桔梗缩略图.jpg");
            //Glide库，超级简单就实现了，真是666
            try {
                String encode=URLEncoder.encode(mMedicines.get(i).getThumbnail(), "utf8");

                Glide.with(MedicineAllFragment.this)
                        .load(URL_PREFIX+ encode)
                        .into(medicineCardHolder.mThumbnail);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return mMedicines.size();
        }
    }
}
