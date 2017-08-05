package com.schoolsign.app;

public class NetCons {
    public static final String CODE = "code";
    public static final String RESULT = "result";
    public static final String MESSAGE = "message";

    /****************响应码 **************/

    //通用

    //ok
    public static final int SECCESS = 1;
    //失败
    public static final int FAILD = 2;
    // 系统错误
    public static final int SYSTEM_ERROR = 3;


    // 用户名或密码错误
    public static final int DATA_ERROR = 10;

    // 手机号已绑定
    public static final int PHONE_EXIST = 11;


    //已加入
    public static final int JOINED = 15;
    //未加入(申请中,无记录)
    public static final int UN_JOIN = 16;

    //课程不存在(查找)
    public static final int COURSE_NOT_EXIST = 20;

    //课程已存在(创建)
    public static final int MY_COURSE_EXIST = 21;

    //已有正在签到课次(创建)
    public static final int EXIST_SIGN_LESSON = 24;

    //课次已经结束
    public static final int LESSON_FINISHED = 30;


    /****************类型选择 **************/

    // 注册类型 手机
    public static final int REGISTER_PHONE = 100;
    // 注册类型 其他
    public static final int REGISTER_OTHER = 101;

    //用户身份 老师
    public static final int TEACHER = 105;
    //用户身份 学生
    public static final int STUDENT = 106;

    //申请中
    public static final int APPLYING = 110;
    //已加入
    public static final int JOIN = 111;

    //同意
    public static final int AGREE = 115;
    //忽略
    public static final int DISAGREE = 116;

    //未发布
    public static final int UNPUBLISH = 120;
    //正在签到
    public static final int SIGNNING = 121;
    //已结束
    public static final int FINISHED = 122;

    //已签到
    public static final int SIGNED = 127;
    //未签到
    public static final int UN_SIGNED = 128;
}
