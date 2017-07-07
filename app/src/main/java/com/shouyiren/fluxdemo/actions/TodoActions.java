package com.shouyiren.fluxdemo.actions;

/**
 * 作者：ZhouJianxing on 2017/6/28 11:29
 * email:727933147@qq.com
 */

public interface TodoActions {
    String TODO_CREATE = "todo-create";
    String TODO_COMPLETE = "todo-complete";
    String TODO_DESTROY = "todo-destroy";
    String TODO_DESTROY_COMPLETED = "todo-destroy-completed";
    String TODO_TOGGLE_COMPLETE_ALL = "todo-toggle-complete-all";
    String TODO_UNDO_COMPLETE = "todo-undo-complete";
    String TODO_UNDO_DESTROY = "todo-undo-destroy";

    String KEY_TEXT = "key-text";
    String KEY_ID = "key-id";
}
