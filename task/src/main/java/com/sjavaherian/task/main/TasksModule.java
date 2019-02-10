package com.sjavaherian.task.main;

import android.arch.lifecycle.ViewModelProviders;
import com.sjavaherian.task.common.ViewModelFactoryTask;
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
        return new TasksAdapter(viewModel);
    }
}