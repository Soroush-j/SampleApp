package com.example.sjavaherian.myapp.task.ui.tasks;

import android.arch.lifecycle.ViewModelProviders;
import com.example.sjavaherian.myapp.task.ViewModelFactoryTask;
import com.example.sjavaherian.myapp.main.MainActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class TasksModule {

    private TasksFragment mTasksFragment;
    private MainActivity mMainActivity;

    public TasksModule(MainActivity mainActivity, TasksFragment tasksFragment) {
        mTasksFragment = tasksFragment;
        mMainActivity = mainActivity;
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