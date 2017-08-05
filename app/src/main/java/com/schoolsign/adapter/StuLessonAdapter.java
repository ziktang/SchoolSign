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
import com.schoolsign.user.bean.StuLessonResult;
import com.schoolsign.utils.Utils;
import com.schoolsign.views.IconFontTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tctctc on 2017/6/24.
 * Function:
 */

public class StuLessonAdapter extends RecyclerArrayAdapter<StuLessonResult> {

    public StuLessonAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.stu_lesson_list_item, parent, false);
        return new StuLessonViewHolder(view);
    }

    class StuLessonViewHolder extends BaseViewHolder<StuLessonResult> {

        @BindView(R.id.lessonName)
        TextView mLessonName;
        @BindView(R.id.lesson_date)
        TextView mLessonDate;
        @BindView(R.id.sign_state)
        IconFontTextView mSignState;
        @BindView(R.id.lesson_state)
        TextView mLessonState;

        public StuLessonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(StuLessonResult data) {
            super.setData(data);
            mLessonName.setText(data.getLname());
            String date = Utils.formatUTC(Long.valueOf(data.getStartTime()),"").substring(0,10);

            mLessonDate.setText(date);
            if(data.getSignState() == NetCons.SIGNED){
                mSignState.setText("\ue60e");
            }else{
                mSignState.setText("");
            }

            if(data.getLessonState() == NetCons.SIGNNING){
                mLessonState.setText("正在签到");
                mLessonState.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
            }else{
                mLessonState.setText("已结束");
                mLessonState.setTextColor(getContext().getResources().getColor(R.color.black_light));
            }
        }
    }

}
