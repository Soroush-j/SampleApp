package com.sjavaherian.task.main;

import android.databinding.BindingAdapter;
import android.widget.TextView;
import com.sjavaherian.task.common.TasksFilterType;

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
