package com.example.sjavaherian.myapp.task.ui.tasks;

import dagger.Subcomponent;

@Subcomponent(modules = {TasksModule.class})
public interface TasksSubcomponent {
    void inject(TasksFragment tasksFragment);
}
