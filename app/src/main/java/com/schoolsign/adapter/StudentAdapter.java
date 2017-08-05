package com.schoolsign.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.schoolsign.R;
import com.schoolsign.app.NetCons;
import com.schoolsign.user.bean.StudentResult;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tctctc on 2017/6/23.
 * Function:
 */

public class StudentAdapter extends RecyclerArrayAdapter<StudentResult> {

    private RequireListener mListener;


    public void setListener(RequireListener listener) {
        mListener = listener;
    }

    public interface RequireListener {
        public void dealRequire(int position, boolean agree);
    }

    public StudentAdapter(Context context) {
        super(context);
    }


    @Override
    public int getViewType(int position) {
        return getItem(position).getJoinState();
    }

    @Override
    public BaseViewHolder<StudentResult> OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == NetCons.APPLYING) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.student_list_item_apply, parent, false);
            return new StudentApplyViewHolder(view);
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.student_list_item_join, parent, false);
            return new StudentJoinViewHolder(view);
        }
    }

    class StudentJoinViewHolder extends BaseViewHolder<StudentResult> {

        @BindView(R.id.student_name)
        TextView mStudentName;

        public StudentJoinViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(StudentResult data) {
            mStudentName.setText(data.getUname()+"("+data.getUid()+")");
        }
    }

    class StudentApplyViewHolder extends BaseViewHolder<StudentResult> {

        @BindView(R.id.require_stu)
        TextView mRequireStu;
        @BindView(R.id.require_info)
        TextView mRequireInfo;
        @BindView(R.id.agree)
        TextView mAgree;
        @BindView(R.id.disagree)
        TextView mDisagree;

        public StudentApplyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mAgree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.dealRequire(getAdapterPosition(), true);
                }
            });
            mDisagree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.dealRequire(getAdapterPosition(), false);
                }
            });
        }

        @Override
        public void setData(StudentResult data) {
            super.setData(data);
            mRequireStu.setText(data.getUname()+"("+data.getUid()+")");
            mRequireInfo.setText(data.getRequire());
        }
    }


}
