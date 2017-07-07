package com.shouyiren.fluxdemo.stores;

import com.shouyiren.fluxdemo.actions.Action;
import com.shouyiren.fluxdemo.actions.TodoActions;
import com.shouyiren.fluxdemo.dispatcher.Dispatcher;
import com.shouyiren.fluxdemo.model.Todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 作者：ZhouJianxing on 2017/6/28 11:31
 * email:727933147@qq.com
 */

public class TodoStore extends Store {
    private static TodoStore mInstance;
    private final List<Todo> mTodos;
    private Todo mLastDeleted;

    protected TodoStore(Dispatcher dispatcher) {
        super(dispatcher);
        mTodos = new ArrayList<>();
    }

    public static TodoStore get(Dispatcher dispatcher) {
        if (mInstance == null) {
            mInstance = new TodoStore(dispatcher);
        }

        return mInstance;
    }

    public List<Todo> getTodos() {
        return mTodos;
    }

    public boolean canUndo() {
        return mLastDeleted != null;
    }

    @Override
    StoreChangeEvent changeEvent() {
        return new TodoStoreChangeEvent();
    }

    @Override
    public void onAction(Action action) {
        long id;
        switch (action.getType()) {
            case TodoActions.TODO_CREATE:
                String text = (String) action.getData().get(TodoActions.KEY_TEXT);
                create(text);
                emitSoreChange();
                break;

            case TodoActions.TODO_DESTROY:
                id = (long) action.getData().get(TodoActions.KEY_ID);
                destory(id);
                emitSoreChange();
                break;

            case TodoActions.TODO_UNDO_DESTROY:
                undoDestory();
                emitSoreChange();
                break;

            case TodoActions.TODO_COMPLETE:
                id = (long) action.getData().get(TodoActions.KEY_ID);
                updateComplete(id, true);
                emitSoreChange();
                break;

            case TodoActions.TODO_UNDO_COMPLETE:
                id = (long) action.getData().get(TodoActions.KEY_ID);
                updateComplete(id, false);
                emitSoreChange();
                break;

            case TodoActions.TODO_DESTROY_COMPLETED:
                destroyCompleted();
                emitSoreChange();
                break;

            case TodoActions.TODO_TOGGLE_COMPLETE_ALL:
                updateCompleteAll();
                emitSoreChange();
                break;

            default:
                break;
        }
    }

    /**
     * 创建
     *
     * @param text
     */
    private void create(String text) {
        long id = System.currentTimeMillis();
        Todo todo = new Todo(id, text);
        addElement(todo);
        Collections.sort(mTodos);
    }

    /**
     * 清除
     *
     * @param id
     */
    private void destory(long id) {
        Iterator<Todo> iter = mTodos.iterator();
        while (iter.hasNext()) {
            Todo todo = iter.next();
            if (todo.getId() == id) {
                mLastDeleted = todo.clone();
                iter.remove();
                break;
            }
        }
    }

    /**
     * 恢复
     */
    private void undoDestory() {
        if (mLastDeleted != null) {
            addElement(mLastDeleted.clone());
            mLastDeleted = null;
        }
    }

    /**
     * 更新
     *
     * @param id
     * @param complete
     */
    private void updateComplete(long id, boolean complete) {
        Todo todo = getById(id);
        if (todo != null) {
            todo.setComplete(complete);
        }
    }

    /**
     * 清除
     */
    private void destroyCompleted() {
        Iterator<Todo> iter = mTodos.iterator();
        while (iter.hasNext()) {
            Todo todo = iter.next();
            if (todo.isComplete()) {
                iter.remove();
            }
        }
    }

    private void updateCompleteAll() {
        if (areAllComplete()) {
            updateAllComplete(false);
        } else {
            updateAllComplete(true);
        }
    }

    private boolean areAllComplete() {
        for (Todo todo : mTodos) {
            if (!todo.isComplete()) {
                return false;
            }
        }

        return true;
    }

    private void updateAllComplete(boolean complete) {
        for (Todo todo : mTodos) {
            todo.setComplete(complete);
        }
    }

    /**
     * 获取
     *
     * @param id
     * @return
     */
    private Todo getById(long id) {
        Iterator<Todo> iter = mTodos.iterator();
        while (iter.hasNext()) {
            Todo todo = iter.next();
            if (todo.getId() == id) {
                return todo;
            }
        }

        return null;
    }

    private void addElement(Todo clone) {
        mTodos.add(clone);
        Collections.sort(mTodos);
    }

    public class TodoStoreChangeEvent implements StoreChangeEvent {

    }
}
