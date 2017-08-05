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
import com.schoolsign.user.bean.Lesson;
import com.schoolsign.utils.Utils;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tctctc on 2017/6/21.
 * Function:
 */

public class TeaLessonAdapter extends RecyclerArrayAdapter<Lesson> {

    private LayoutInflater mInflater;
    private DecimalFormat mFormat = new DecimalFormat("#0.00");
    private boolean isPublish;

    public TeaLessonAdapter(Context context,boolean isPublish) {
        super(context);
        mInflater = LayoutInflater.from(context);
        this.isPublish = isPublish;
    }

    @Override
    public LessonViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.tea_lesson_list_item, parent, false);
        return new LessonViewHolder(view);
    }


    class LessonViewHolder extends BaseViewHolder<Lesson> {
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.state)
        TextView mState;
        @BindView(R.id.sign_per)
        TextView mSignPer;
        @BindView(R.id.startTime)
        TextView mStartTime;

        public LessonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(Lesson lesson) {
            mName.setText(lesson.getLname());
            if(isPublish){
                mStartTime.setText(Utils.formatUTC(Long.valueOf(lesson.getStartTime()),"").substring(0,10));
            }else{
                mStartTime.setText("持续时间 : "+lesson.getDuration()+"分钟");
            }
            switch (lesson.getState()) {
                case NetCons.UNPUBLISH:
                    mState.setText("未发布");
                    mState.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                    break;
                case NetCons.FINISHED:
                case NetCons.SIGNNING:
                    if (lesson.getState() == NetCons.FINISHED) {
                        mState.setText("已结束");
                        mState.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                    } else {
                        final long duration = Long.valueOf(lesson.getEndTime()) - System.currentTimeMillis();
                        if(duration<=0){
                            mState.setText("已结束");
                            mState.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                        }else{
                            mState.setText("签到中");
                            mState.setTextColor(getContext().getResources().getColor(R.color.colorAccent));
                        }
                    }
                    float per = (float) lesson.getSignedNum() / (float) lesson.getTotalNum();
                    Log.i("AAA","per:" +per);
                    Log.i("AAA","lesson.getTotalNum():" +lesson.getTotalNum());
                    mSignPer.setText("签到率 : " + mFormat.format(per*100) + "%");
                    break;
            }
        }
    }
}
