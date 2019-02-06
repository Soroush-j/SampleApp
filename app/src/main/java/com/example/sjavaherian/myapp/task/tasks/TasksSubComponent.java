package com.example.sjavaherian.myapp.task.tasks;

import dagger.Subcomponent;

@Subcomponent(modules = {TasksModule.class})
public interface TasksSubComponent {
    void inject(TasksFragment tasksFragment);

//    @Subcomponent.Builder
//    interface Builder{
//        TasksSubComponent build();
//        Builder bindTasksFragment(TasksFragment tasksFragment);
//    }
}
