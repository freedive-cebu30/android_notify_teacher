package com.herokuapp.aqueous_spire_22637.notifyteacher;

/**
 * Get Teacher info
 */

public class Teacher {
    public final String name;
    public final String online_teacher_id;
    public final String service_name;

    public Teacher(String name, String online_teacher_id, String service_name){
        this.name = name;
        this.online_teacher_id = online_teacher_id;
        this.service_name = service_name;
    }
}
