package com.example.sjavaherian.myapp.task.tasks;

import android.arch.lifecycle.ViewModelProviders;
import com.example.sjavaherian.myapp.task.ViewModelFactoryTask;
import com.example.sjavaherian.myapp.main.MainActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class TasksModule {

    private TasksFragment mTasksFragment;

    public TasksModule(TasksFragment tasksFragment) {
        mTasksFragment = tasksFragment;
    }

    @Provides
    TasksViewModel providesTasksViewModel(ViewModelFactoryTask factory) {
        return ViewModelProviders.of(mTasksFragment, factory).get(TasksViewModel.class);
    }

    @Provides
    TasksAdapter providesTaskAdapter(TasksViewModel viewModel) {
        return new TasksAdapter( viewModel);
    }
}