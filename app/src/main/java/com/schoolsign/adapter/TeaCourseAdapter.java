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
import com.schoolsign.user.bean.Course;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tctctc on 2017/6/21.
 * Function:
 */

public class TeaCourseAdapter extends RecyclerArrayAdapter<Course> {

    private LayoutInflater mInflater;

    public TeaCourseAdapter(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CourseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(view);
    }



    class CourseViewHolder extends BaseViewHolder<Course> {
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.num)
        TextView mNum;
        @BindView(R.id.tea_name)
        TextView mTeaName;

        public CourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void setData(Course course) {
            mName.setText(course.getCname());
            mNum.setText("课程编号:"+course.getCid());
            mTeaName.setText("上课学生:"+course.getStuNum());
        }
    }
}
