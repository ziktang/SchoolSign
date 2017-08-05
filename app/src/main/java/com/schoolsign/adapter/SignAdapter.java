package com.schoolsign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.schoolsign.R;
import com.schoolsign.app.NetCons;
import com.schoolsign.user.bean.Record;
import com.schoolsign.utils.FontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/21.
 */
public class SignAdapter extends RecyclerArrayAdapter<Record> {

    public SignAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.lv_sign_item, parent, false);
        return new SignViewHolder(view);
    }


    class SignViewHolder extends BaseViewHolder<Record> {

        @BindView(R.id.uid)
        TextView mUid;
        @BindView(R.id.uname)
        TextView mUname;
        @BindView(R.id.sign_state)
        TextView mSignState;

        public SignViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


        @Override
        public void setData(Record data) {
            mUid.setText(data.getUid()+"");
            mUname.setText(data.getUname());
            if(data.getState() == NetCons.SIGNED){
                mSignState.setText("\ue60e");
            }else{
                mSignState.setText("");
            }
        }
    }
}
