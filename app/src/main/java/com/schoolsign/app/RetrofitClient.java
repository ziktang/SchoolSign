package com.schoolsign.app;

import android.util.Log;


import com.schoolsign.common.retrofit.HttpResult;
import com.schoolsign.user.bean.Course;
import com.schoolsign.user.bean.Lesson;
import com.schoolsign.user.bean.Record;
import com.schoolsign.user.bean.SchoolResult;
import com.schoolsign.user.bean.SignStuResult;
import com.schoolsign.user.bean.StuLessonResult;
import com.schoolsign.user.bean.StudentResult;
import com.schoolsign.user.bean.User;
import com.schoolsign.utils.Utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by tctctc on 2017/6/18.
 * Function:
 */

public class RetrofitClient {
    private static Retrofit sRetrofit;

    public static NetService sService;


    public static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .build();

            sRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(Utils.getTimeGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            Log.i("SchoolActivity", "getRetrofit");
        }

        return sRetrofit;
    }


    public interface NetService {
        /*****************学校***********/
        @FormUrlEncoded
        @POST("school/nearSchool")
        Observable<HttpResult<List<SchoolResult>>> getNearSchool(@Field("cname") String cname);

        @FormUrlEncoded
        @POST("school/searchSchool")
        Observable<HttpResult<List<SchoolResult>>> getSearchSchool(@Field("prefix") String pre);


        /**************登录注册**************/
        @POST("user/register")
        Observable<HttpResult<User>> register(@Body RequestBody body);

        @POST("user/login")
        Observable<HttpResult<User>> login(@Body RequestBody body);


        /**************老师**************/

        //获取老师所有课程
        @FormUrlEncoded
        @POST("teacher/allCourse")
        Observable<HttpResult<List<Course>>> allTeacherCourse(@Field("uid") int uid);

        //创建课程
        @FormUrlEncoded
        @POST("teacher/createCourse")
        Observable<HttpResult<Course>> createCourse(@Field("uid") int uid, @Field("cname") String cname, @Field("describe") String describe);

        //老师某课程所有课次
        @FormUrlEncoded
        @POST("lesson/teaAllLesson")
        Observable<HttpResult<List<Lesson>>> teaAllLesson(@Field("cid") int cid, @Field("state") int state);


        //老师处理课程加入请求
        @FormUrlEncoded
        @POST("teacher/dealJoin")
        Observable<HttpResult> dealJoin(@Field("uid") int uid, @Field("cid") int cid, @Field("isAgree") int isAgree);

        /**************学生**************/

        @FormUrlEncoded
        //获取学生所有课程
        @POST("student/allCourse")
        Observable<HttpResult<List<Course>>> allStudentCourse(@Field("uid") int uid);

        @FormUrlEncoded
        //查看课程
        @POST("student/lookCourse")
        Observable<HttpResult<Course>> lookCourse(@Field("uid") int uid, @Field("cid") int cid);

        @FormUrlEncoded
        //申请加入课程
        @POST("student/joinCourse")
        Observable<HttpResult> joinCourse(@Field("uid") int uid, @Field("cid") int cid, @Field("require") String require);



        /**************课次，课程**************/
        @FormUrlEncoded
        //查看课程
        @POST("lesson/allStudent")

        Observable<HttpResult<List<StudentResult>>> allStudent(@Field("uid") int uid, @Field("cid") int cid);

        //创建课次
        @POST("lesson/createLesson")
        Observable<HttpResult> createLesson(@Body RequestBody body);


        @FormUrlEncoded
        //某课程学生所有课次签到记录
        @POST("lesson/stuAllLesson")
        Observable<HttpResult<List<StuLessonResult>>> stuAllLesson(@Field("cid") int cid,@Field("uid") int uid);


        @FormUrlEncoded
        //开始签到
        @POST("lesson/startSign")
        Observable<HttpResult<Lesson>> startSign(@Field("lid") int lid,@Field("signCode") String signCode);

        @FormUrlEncoded
        //结束签到
        @POST("lesson/finishSign")
        Observable<HttpResult<Lesson>> finishSign(@Field("lid") int lid);

        @FormUrlEncoded
        //学生签到列表
        @POST("lesson/signStudents")
        Observable<HttpResult<SignStuResult>> signStudents(@Field("lid") int lid);

        @FormUrlEncoded
        //学生签到
        @POST("lesson/sign")
        Observable<HttpResult<StuLessonResult>> sign(@Field("lid") int lid,@Field("uid") int uid);

        @FormUrlEncoded
        //老师取消学生签到
        @POST("lesson/cancelSign")
        Observable<HttpResult<Record>> cancelSign(@Field("lid") int lid, @Field("uid") int uid);

        @FormUrlEncoded
        //老师代替学生签到
        @POST("lesson/doSign")
        Observable<HttpResult<Record>> doSign(@Field("lid") int lid,@Field("uid") int uid);
    }


    public static NetService getService() {
        if (sService == null) {
            sService = getRetrofit().create(NetService.class);
        }
        return sService;
    }
}
