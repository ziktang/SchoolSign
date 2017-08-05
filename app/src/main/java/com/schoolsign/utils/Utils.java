package com.schoolsign.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.schoolsign.adapter.TimestampTypeAdapter;
import com.schoolsign.user.bean.Lesson;
import com.schoolsign.user.bean.User;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : Utils初始化相关
 * </pre>
 */
public final class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static Gson sGson;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    private static SimpleDateFormat sdf = null;

    public static String formatUTC(long l, String strPattern) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm";
        }
        if (sdf == null) {
            try {
                sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
            } catch (Throwable e) {
            }
        } else {
            sdf.applyPattern(strPattern);
        }
        return sdf == null ? "NULL" : sdf.format(l);
    }


    //时间戳Gson构造器
    public static Gson getTimeGson() {
        if (sGson == null) {
            GsonBuilder builder = new GsonBuilder();
//            builder.registerTypeAdapter(Timestamp.class, new JsonDeserializer<Timestamp>() {
//                public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                    return new Timestamp(json.getAsJsonPrimitive().getAsLong());
//                }
//            });
            builder.registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter());
            sGson = builder.create();
        }
        return sGson;
    }


    public static <T> RequestBody getRequestBody(T data, Class<T> aClass) {
        return RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), getTimeGson().toJson(data, aClass));
    }


    public static String getTime(Lesson lesson){
        return Utils.formatUTC(Long.valueOf(lesson.getStartTime()), null) +
                " - " + Utils.formatUTC(Long.valueOf(lesson.getEndTime()), null);
    }
}