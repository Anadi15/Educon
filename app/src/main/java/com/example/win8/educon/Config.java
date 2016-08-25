package com.example.win8.educon;

/**
 * Created by win8 on 7/29/2016.
 */
public class Config {



    public static final String URL_GET_ALL_TEACHERS = "http://anadi.esy.es/getAllTeacher.php";
    public static final String URL_GET_TEACHERS = "http://anadi.esy.es/getTeacher.php?id=";
    public static final String URL_UPDATE_TEACHERS = "http://anadi.esy.es/updateTeachers.php";
    public static final String URL_DELETE_TEACHERS = "http://anadi.esy.es/deleteTeacher.php?id=";

    public static final String URL_GET_ALL_STUD = "http://anadi.esy.es/getAllStudent.php";
    public static final String URL_GET_STUD = "http://anadi.esy.es/getStudent.php?id=";
    public static final String URL_UPDATE_STUD = "http://anadi.esy.es/updateStudent.php";
    public static final String URL_DELETE_STUD = "http://anadi.esy.es/deleteStudent.php?id=";

    public static final String URL_ADD_NOTICE="http://anadi.esy.es/noticeadd.php";



    public static final String URL_ADDTEACHER="http://anadi.esy.es/teacher_reg.php";

    public static final String URL_ADD="http://anadi.esy.es/register.php";
    public static final String URL_GET_ALL = "http://anadi.esy.es/getallstud.php";

    public static final String URL_GET_ALL_NOTICES="http://anadi.esy.es/getallnotice.php";


    public static final String URL_GET_ALL_TEACHER = "http://anadi.esy.es/getallTeach.php";

    public static final String URL_GET_EMP = "http://anadi.esy.es/getStud.php?id=";

    public static final String URL_GET_TEACHER = "http://anadi.esy.es/getTeach.php?id=";

    public static final String URL_UPDATE_EMP = "http://anadi.esy.es/updateStud.php";

    public static final String URL_UPDATE_TEACHER = "http://anadi.esy.es/updateTeacher.php";

    public static final String URL_CHANGE_STUDENTPASS  = "http://anadi.esy.es/changepass.php";
    public static final String URL_DELETE_TEACHER = "http://anadi.esy.es/deleteTeach.php?id=";
    public static final String URL_DELETE_EMP = "http://anadi.esy.es/deleteStud.php?id=";

    public static final String URL_CHANGE_TEACHERPASS  = "http://anadi.esy.es/changeTeacherPass.php";


/*
    public static final String URL_GET_ALL_TEACHERS = "http://10.0.2.2/Educondb/getAllTeacher.php";
    public static final String URL_GET_TEACHERS = "http://10.0.2.2/Educondb/getTeacher.php?id=";
    public static final String URL_UPDATE_TEACHERS = "http://10.0.2.2/Educondb/updateTeachers.php";
    public static final String URL_DELETE_TEACHERS = "http://10.0.2.2/Educondb/deleteTeacher.php?id=";







    public static final String URL_GET_ALL_STUD = "http://10.0.2.2/Educondb/getAllStudent.php";
    public static final String URL_GET_STUD = "http://10.0.2.2/Educondb/getStudent.php?id=";
    public static final String URL_UPDATE_STUD = "http://10.0.2.2/Educondb/updateStudent.php";
    public static final String URL_DELETE_STUD = "http://10.0.2.2/Educondb/deleteStudent.php?id=";



    public static final String URL_ADD_NOTICE="http://10.0.2.2/Educondb/noticeadd.php";



    public static final String URL_ADDTEACHER="http://10.0.2.2/Educondb/teacher_reg.php";

    public static final String URL_ADD="http://10.0.2.2/Educondb/register.php";
    public static final String URL_GET_ALL = "http://10.0.2.2/Educondb/getallstud.php";

    public static final String URL_GET_ALL_NOTICES="http://10.0.2.2/Educondb/getallnotice.php";


    public static final String URL_GET_ALL_TEACHER = "http://10.0.2.2/Educondb/getallTeach.php";

    public static final String URL_GET_EMP = "http://10.0.2.2/Educondb/getStud.php?id=";

    public static final String URL_GET_TEACHER = "http://10.0.2.2/Educondb/getTeach.php?id=";

    public static final String URL_UPDATE_EMP = "http://10.0.2.2/Educondb/updateStud.php";

    public static final String URL_UPDATE_TEACHER = "http://10.0.2.2/Educondb/updateTeacher.php";

    public static final String URL_CHANGE_STUDENTPASS  = "http://10.0.2.2/Educondb/changepass.php";
    public static final String URL_DELETE_TEACHER = "http://10.0.2.2/Educondb/deleteTeach.php?id=";
    public static final String URL_DELETE_EMP = "http://10.0.2.2/Educondb/deleteStud.php?id=";

    public static final String URL_CHANGE_TEACHERPASS  = "http://10.0.2.2/Educondb/changeTeacherPass.php";
*/
    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_NOTICE = "message";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_USERNAME = "username";
    public static final String KEY_EMP_PASSWORD = "password";
    public static final String KEY_EMP_EMAIL = "email";


    public static final String KEY_TEACHER_OLDPASSWORD = "oldpassword";
    public static final String KEY_TEACHER_NEWPASSWORD = "newpassword";


    public static final String KEY_STUDENT_OLDPASSWORD = "oldpassword";
    public static final String KEY_STUDENT_NEWPASSWORD = "newpassword";
    //JSON Tags

    public static final String TAG_USERNAME = "username";
    public static final String TAG_PASSWORD = "password";
    //   public static final String TAG_DESG = "desg";
    //   public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String TEACHER_ID = "teacher_id";

    public static final String STUD_ID = "stud_id";

    public static final String EMP_ID = "emp_id";

    public static final String NOTICE_ID = "notice_id";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_MESSAGE = "message";

    public static final String TAG_EMAIL = "email";
    public static final String TAG_FATHERNAME = "father_name";
    public static final String TAG_MOTHERNAME = "mother_name";
    public static final String TAG_GENDER = "gender";
    public static final String TAG_DATE = "date";
    public static final String TAG_MONTH = "month";
    public static final String TAG_YEAR = "year";
    public static final String TAG_ADDRESS = "address";
    public static final String TAG_ENROLLMENT = "enrollment";

    public static final String KEY_STUD_ID = "id";
    public static final String KEY_STUD_NAME = "name";
    public static final String KEY_TEACHER_ID = "id";
    public static final String KEY_TEACHER_NAME = "name";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_FATHERNAME = "father_name";
    public static final String KEY_MOTHERNAME = "mother_name";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DATE = "date";
    public static final String KEY_MONTH = "month";
    public static final String KEY_YEAR = "year";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_ENROLLMENT = "enrollment";






}