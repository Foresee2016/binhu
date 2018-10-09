package org.foresee.binhu.web;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.foresee.binhu.R;
import org.foresee.binhu.data.MedicineLab;
import org.foresee.binhu.data.UpdateInfoLab;
import org.foresee.binhu.databinding.UpdateInfoCardBinding;
import org.foresee.binhu.model.Medicine;
import org.foresee.binhu.model.UpdateInfo;
import org.foresee.binhu.model.UpdateInfoViewModel;
import org.foresee.binhu.share.BackNavActivity;
import org.foresee.binhu.share.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateInfoActivity extends BackNavActivity {
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, UpdateInfoActivity.class);
        return intent;
    }

    private static final String TAG = "UpdateInfoActivity";
    private TextView mLoadingText;
    private RecyclerView mRecyclerView;
    private UpdateInfoAdapter mAdapter;
    private UpdateInfoLab mUpdateInfoLab;
    private MedicineLab mMedicineLab;
    private boolean hasNewData=false;
    private static final String EXTRA_HAS_NEW_DATA="hasNewData";
    @Override
    public int getLayout() {
        return R.layout.activity_update_info;
    }

    @Override
    public int getToolbarTitleResId() {
        return R.string.update_info_title;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingText = findViewById(R.id.tip_loading);
        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new UpdateInfoAdapter(new ArrayList<UpdateInfo>());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mUpdateInfoLab = UpdateInfoLab.getMedicineLab(this);
        mMedicineLab = MedicineLab.getMedicineLab(this);
        if(Utils.isNetworkAvailableAndConnected(this)) {
            new UpdateInfoFethr().execute();
        }else{
            Log.d(TAG, "onCreate: 网络连接不可用，不能发起请求，设置result");
            sendResult();
        }
    }

    private class UpdateInfoFethr extends AsyncTask<Date, Void, List<UpdateInfo>> {

        @Override
        protected List<UpdateInfo> doInBackground(Date... dates) {
            List<UpdateInfo> infos = new ArrayList<>();
            Log.i(TAG, "doInBackground: 下载所有。");
            try {
                String result = new Fetchr().getUrlString("http://47.93.12.228:8080/binhu/api/UpdateInfo");
                Log.i(TAG, "doInBackground: 结果：" + result);
                try {
                    JSONObject retObj = new JSONObject(result);
                    JSONArray jsonInfos = retObj.getJSONObject("_embedded").getJSONArray("updateInfoes");
                    for (int i = 0; i < jsonInfos.length(); i++) {
                        JSONObject jsonInfo = jsonInfos.getJSONObject(i);
                        UpdateInfo info = new UpdateInfo();
                        info.setPart(jsonInfo.getString("part"));
                        info.setDataCount(jsonInfo.getInt("dataCount"));
                        info.setDataSize(jsonInfo.getInt("dataSize"));
                        try {
                            info.setUpdateTime(Utils.parseDate(jsonInfo.getString("updateTime")));
                        } catch (ParseException e) {
                            e.printStackTrace();
                            info.setUpdateTime(Utils.DEFAULT_DATE);
                        }
                        infos.add(info);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return infos;
        }

        @Override
        protected void onPostExecute(List<UpdateInfo> infos) {
            super.onPostExecute(infos);
            mUpdateInfoLab.saveNewUpdateInfo(infos);
            mAdapter.mUpdateInfos = mUpdateInfoLab.getUpdateInfos();
            mAdapter.notifyDataSetChanged();
        }
    }
    private void downloadMedicine(String updateTime) {
        Log.i(TAG, "downloadMedicine: 此处应下载对应日期的，服务器还没做筛选查询，这里暂时下载全部中药。updateTime = " + updateTime);
        if(Utils.isNetworkAvailableAndConnected(this)) {
            new MedicineFetchr().execute(updateTime);
        }else{
            Log.d(TAG, "downloadMedicine: 无网络连接，不能下载");
        }
    }
    private void sendResult(){
        Log.i(TAG, "sendResult: 设置返回结果，是否下载了新数据");
        Intent data=new Intent();
        data.putExtra(EXTRA_HAS_NEW_DATA, hasNewData);
        setResult(RESULT_OK, data);
    }
    public static boolean hasNewData(Intent intent){
        return intent.getBooleanExtra(EXTRA_HAS_NEW_DATA, false);
    }
    private class MedicineFetchr extends AsyncTask<String, Void, List<Medicine>> {

        @Override
        protected List<Medicine> doInBackground(String... strings) {
            List<Medicine> medicines = new ArrayList<>();
            String result = null;
            try {
                result = new Fetchr().getUrlString("http://47.93.12.228:8080/binhu/api/medicine");
                Log.i(TAG, "doInBackground: 结果：" + result);
                try {
                    JSONObject retObj = new JSONObject(result);
                    JSONArray jsonArray = retObj.getJSONObject("_embedded").getJSONArray("medicines");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonInfo = jsonArray.getJSONObject(i);
                        Medicine medicine = new Medicine();
                        medicine.setName(jsonInfo.getString("name"));
                        medicine.setThumbnail(jsonInfo.getString("thumbnail"));
                        medicine.setTaste(jsonInfo.getString("taste"));
                        medicine.setFunc(jsonInfo.getString("func"));
                        medicine.setImages(jsonInfo.getString("images"));
                        try {
                            medicine.setUpdateTime(Utils.parseDate(jsonInfo.getString("updateTime")));
                        } catch (ParseException e) {
                            e.printStackTrace();
                            medicine.setUpdateTime(Utils.DEFAULT_DATE);
                        }
                        medicines.add(medicine);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return medicines;
        }

        @Override
        protected void onPostExecute(List<Medicine> medicines) {
            super.onPostExecute(medicines);
            mMedicineLab.saveNewMedicine(medicines);
            hasNewData=true;
            sendResult();
        }
    }

    private void downloadPrescript(String updateTime) {
        Log.i(TAG, "downloadPrescript: 此处应下载对应日期的，服务器还没做筛选查询，这里暂时下载全部方剂。updateTime = " + updateTime);
    }

    private class UpdateInfoHolder extends RecyclerView.ViewHolder {
        private UpdateInfoCardBinding mBinding;
        private Button mDownloadBtn;

        public UpdateInfoHolder(UpdateInfoCardBinding binding, UpdateInfo updateInfo) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new UpdateInfoViewModel(updateInfo));
            mDownloadBtn = itemView.findViewById(R.id.btn_download);
            mDownloadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String updateTime = mBinding.getViewModel().getUpdateTime();
                    Log.i(TAG, "download: 下载UpdateInfo：id = " + updateTime);
                    mDownloadBtn.setText(R.string.downloading);
                    String part=mBinding.getViewModel().getPart();
                    if (part.equals("中药")) {
                        downloadMedicine(updateTime);
                    }else if(part.equals("方剂")){
                        downloadPrescript(updateTime);
                    }else{
                        Log.w(TAG, "onClick: Unknown part "+part);
                    }
                }
            });
        }

        public void bind(UpdateInfo updateInfo) {
            mBinding.getViewModel().setUpdateInfo(updateInfo);
            mBinding.executePendingBindings();
        }
    }

    private class UpdateInfoAdapter extends RecyclerView.Adapter<UpdateInfoHolder> {
        private List<UpdateInfo> mUpdateInfos;

        public UpdateInfoAdapter(List<UpdateInfo> updateInfos) {
            mUpdateInfos = updateInfos;
        }

        @NonNull
        @Override
        public UpdateInfoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = getLayoutInflater();
            UpdateInfoCardBinding binding = DataBindingUtil.inflate(inflater, R.layout.update_info_card, viewGroup, false);
            return new UpdateInfoHolder(binding, mUpdateInfos.get(i));
        }

        @Override
        public void onBindViewHolder(@NonNull UpdateInfoHolder updateInfoHolder, int i) {
            updateInfoHolder.bind(mUpdateInfos.get(i));
        }

        @Override
        public int getItemCount() {
            return mUpdateInfos.size();
        }
    }
}
