package com.example.sjavaherian.myapp.task.tasks;

import android.databinding.BindingAdapter;
import android.widget.TextView;
import com.example.sjavaherian.myapp.task.TasksFilterType;

public class TaskFilterBinding {

    @BindingAdapter(value = "filterType")
    public static void setFilter(TextView textView, TasksFilterType filterType) {
        String text;
        switch (filterType) {
            case ALL_TASKS:
                text = "All tasks";
                break;
            case ACTIVE:
                text = "Active tasks";
                break;
            case COMPLETED:
                text = "Completed task";
                break;
            default:
                text = "What happened?";
                break;
        }
        textView.setText(text);
    }
}
