package com.example.assignment.di.base.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.example.assignment.data.remote.helper.Resource;

/**
 * A SingleLiveEvent used for messages. Like a {@link SingleLiveEvent} but also prevents
 * null messages and uses a custom observer.
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
public class SingleRequestEvent<T> extends SingleLiveEvent<Resource<T>> {
    public interface RequestObserver<T> {
        void onRequestReceived(@NonNull Resource<T> resource);
    }

    public void observe(LifecycleOwner owner, final RequestObserver<T> observer) {
        super.observe(owner, resource -> {
            if (resource == null) {
                return;
            }

            observer.onRequestReceived(resource);
        });
    }
}
