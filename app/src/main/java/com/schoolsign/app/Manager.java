package com.schoolsign.app;

import com.schoolsign.user.bean.Course;
import com.schoolsign.user.bean.User;

/**
 * Created by tctctc on 2017/6/20.
 * Function:
 */

public class Manager {
    private static User sUser;
    private static Course sCourse;

    public static void setCourse(Course course) {
        sCourse = course;
    }

    public static Course getCourse() {
        return sCourse;
    }

    public static int getCid(){
        return getCourse().getCid();
    }

    public static User getUser() {
        return sUser;
    }

    public static void setUser(User user) {
        sUser = user;
    }

    public static int getUid(){
        return getUser().getUid();
    }


    public static boolean isTeacher(){
        return sUser.getUserType() == NetCons.TEACHER;
    }
}
