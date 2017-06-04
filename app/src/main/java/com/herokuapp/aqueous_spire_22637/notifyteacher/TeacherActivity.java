package com.herokuapp.aqueous_spire_22637.notifyteacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by joji on 2017/04/22.
 */

public class TeacherActivity extends AppCompatActivity {

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, TeacherActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher);
    }

    public void registerTeacher(View view){
        TextView teacher_name = (TextView)findViewById(R.id.register_teacher_name);
        TextView teacher_number = (TextView)findViewById(R.id.register_teacher_number);
        Spinner teacher_service = (Spinner)findViewById(R.id.register_teacher_service);
        Log.d("test1", teacher_name.getText().toString());
        Log.d("test2", teacher_number.getText().toString());
        Log.d("test3", teacher_service.getSelectedItem().toString());
    }
}
