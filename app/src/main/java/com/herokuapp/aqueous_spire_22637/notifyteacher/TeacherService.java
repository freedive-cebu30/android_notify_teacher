package com.herokuapp.aqueous_spire_22637.notifyteacher;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by joji on 2017/03/06.
 */

public interface TeacherService {
    @GET("api/teachers")
    Observable<TeacherList> getListTeacher();


    class TeacherList {
        public final List<Teacher> teachers;

        public TeacherList(List<Teacher> teachers){
            this.teachers = teachers;
        }
    }
}
