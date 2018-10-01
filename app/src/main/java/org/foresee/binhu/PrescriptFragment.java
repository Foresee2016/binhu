package org.foresee.binhu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.foresee.binhu.data.PrescriptLab;
import org.foresee.binhu.model.Prescript;

import java.util.List;

public class PrescriptFragment extends Fragment {
    public static PrescriptFragment newInstance(){
        return new PrescriptFragment();
    }
    private RecyclerView mRecyclerView;
    private PrescriptAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.recycler_view, container,false);
        mRecyclerView=v.findViewById(R.id.recycler_view);
        mAdapter=new PrescriptAdapter(PrescriptLab.getPrescriptLab().getPrescripts());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return v;
    }
    private class PrescriptHolder extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mFunc;
        public PrescriptHolder(@NonNull View itemView) {
            super(itemView);
            mName=itemView.findViewById(R.id.prescript_name);
            mFunc=itemView.findViewById(R.id.prescript_func);
        }
        public void bind(Prescript prescript){
            mName.setText(prescript.getName());
            mFunc.setText(prescript.getFunc());
        }
    }
    private class PrescriptAdapter extends RecyclerView.Adapter<PrescriptHolder>{
        private List<Prescript> mPrescripts;

        public PrescriptAdapter(List<Prescript> prescripts) {
            mPrescripts = prescripts;
        }

        @NonNull
        @Override
        public PrescriptHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            View view=inflater.inflate(R.layout.presript_card, viewGroup, false);
            return new PrescriptHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PrescriptHolder prescriptHolder, int i) {
            prescriptHolder.bind(mPrescripts.get(i));
        }

        @Override
        public int getItemCount() {
            return mPrescripts.size();
        }
    }
}
