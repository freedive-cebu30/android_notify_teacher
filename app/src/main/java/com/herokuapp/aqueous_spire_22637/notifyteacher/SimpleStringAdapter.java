package com.herokuapp.aqueous_spire_22637.notifyteacher;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by joji on 2017/03/08.
 */

public class SimpleStringAdapter extends RecyclerView.Adapter<SimpleStringAdapter.ViewHolder>{
    private List<Teacher> teachers;

    public SimpleStringAdapter() {
    }
    // 新しいViewHolderを作成する(LayoutManagerから呼び出される)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_row, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Viewの中のデータを変更する(LayoutManagerから呼び出される)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = teachers.get(position).name;
        String online_teacher_id = teachers.get(position).online_teacher_id;
        String teacher_service_name = teachers.get(position).service_name;

        holder.teacherName.setText(name);
        holder.teacherOnlineTeacherId.setText(online_teacher_id);
        holder.teacherServiceName.setText(teacher_service_name);
    }

    //データの数を返す (LayoutManagerから呼び出される)
    @Override
    public int getItemCount() {
        if (teachers == null) {
            return 0;
        }
        return teachers.size();
    }

    /**
     * リポジトリのデータをセットして更新する
     * @param teachers
     */
    public void setItemsAndRefresh(List<Teacher> teachers) {
        this.teachers = teachers;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView teacherName;
        public final TextView teacherOnlineTeacherId;
        public final TextView teacherServiceName;

        public ViewHolder(View v) {
            super(v);
            teacherName = (TextView) v.findViewById(R.id.teacher_name);
            teacherOnlineTeacherId = (TextView) v.findViewById(R.id.teacher_online_teacher_id);
            teacherServiceName = (TextView) v.findViewById(R.id.teacher_service_name);
        }
    }
}
