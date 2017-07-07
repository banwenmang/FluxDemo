package com.shouyiren.fluxdemo.actions;

import com.shouyiren.fluxdemo.dispatcher.Dispatcher;
import com.shouyiren.fluxdemo.model.Todo;

/**
 * 作者：ZhouJianxing on 2017/6/28 11:30
 * email:727933147@qq.com
 */

public class ActionCreator {
    private static ActionCreator mInstance;
    final Dispatcher mDispatcher;

    ActionCreator(Dispatcher dispatcher) {
        this.mDispatcher = dispatcher;
    }

    public static ActionCreator get(Dispatcher dispatcher) {
        if (mInstance == null) {
            mInstance = new ActionCreator(dispatcher);
        }
        return mInstance;
    }

    public void create(String text) {
        mDispatcher.dispatch(TodoActions.TODO_CREATE, TodoActions.KEY_TEXT, text);
    }

    public void destroy(long id) {
        mDispatcher.dispatch(TodoActions.TODO_DESTROY, TodoActions.KEY_ID, id);
    }

    public void undoDestroy() {
        mDispatcher.dispatch(TodoActions.TODO_UNDO_DESTROY);
    }

    public void toggleComplete(Todo todo) {
        long id = todo.getId();
        String actionType = todo.isComplete() ? TodoActions.TODO_UNDO_COMPLETE : TodoActions.TODO_COMPLETE;

        mDispatcher.dispatch(actionType, TodoActions.KEY_ID, id);
    }

    public void toggleCompleteAll() {
        mDispatcher.dispatch(TodoActions.TODO_TOGGLE_COMPLETE_ALL);
    }

    public void destroyCompleted() {
        mDispatcher.dispatch(TodoActions.TODO_DESTROY_COMPLETED);
    }
}
