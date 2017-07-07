package com.shouyiren.fluxdemo.stores;

import com.shouyiren.fluxdemo.actions.Action;
import com.shouyiren.fluxdemo.dispatcher.Dispatcher;

/**
 * 作者：ZhouJianxing on 2017/6/28 11:31
 * email:727933147@qq.com
 */

public abstract class Store {
    final Dispatcher mDispatcher;

    protected Store(Dispatcher dispatcher) {
        this.mDispatcher = dispatcher;
    }

    void emitSoreChange() {
        mDispatcher.emitChange(changeEvent());
    }

    abstract StoreChangeEvent changeEvent();

    public abstract void onAction(Action action);

    public interface StoreChangeEvent {
    }
}
