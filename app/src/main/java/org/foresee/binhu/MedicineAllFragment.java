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

import org.foresee.binhu.data.MedicineLab;
import org.foresee.binhu.model.Medicine;

import java.util.List;

public class MedicineAllFragment extends Fragment {
    public static MedicineAllFragment newInstance(){
        return new MedicineAllFragment();
    }
//    private Context mContext;
    private List<Medicine> mMedicines;
    private RecyclerView mRecyclerView;
    private MedicineCardAdapter mMedicineCardAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.recycler_view, container, false);
        mMedicines=MedicineLab.getMedicineLab(getActivity()).getMedicines();
        mRecyclerView=view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMedicineCardAdapter=new MedicineCardAdapter(mMedicines);
        mRecyclerView.setAdapter(mMedicineCardAdapter);
        return view;
    }
    public void freshDbToUI(){
        mMedicineCardAdapter.notifyDataSetChanged();
    }
    private class MedicineCardHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;
        public MedicineCardHolder(@NonNull View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.medicine_name);
        }
        public void bind(Medicine medicine){
            mTextView.setText(medicine.getName());
        }
    }
    private class MedicineCardAdapter extends RecyclerView.Adapter<MedicineCardHolder>{
        private List<Medicine> mMedicines;

        public MedicineCardAdapter(List<Medicine> medicines) {
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
        }

        @Override
        public int getItemCount() {
            return mMedicines.size();
        }
    }
}
