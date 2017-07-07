package com.shouyiren.fluxdemo.actions;

import java.util.HashMap;

/**
 * 作者：ZhouJianxing on 2017/6/28 11:30
 * email:727933147@qq.com
 */

public class Action {
    private final String mType;
    private final HashMap<String, Object> mData;

    Action(String type, HashMap<String, Object> data) {
        this.mType = type;
        this.mData = data;
    }

    public static Builder type(String type) {
        return new Builder().with(type);
    }

    public String getType(){
        return mType;
    }

    public HashMap getData(){
        return mData;
    }

    public static class Builder {
        private String type;
        private HashMap<String, Object> data;

        Builder with(String type) {
            if (type == null) {
                throw new IllegalArgumentException("Type may not be null.");
            }

            this.type = type;
            this.data = new HashMap<>();
            return this;
        }

        public Builder bundle(String key, Object value) {
            if (key == null) {
                throw new IllegalArgumentException("Type may not be null.");
            }

            if (value == null) {
                throw new IllegalArgumentException("Value may not be null.");
            }
            data.put(key, value);
            return this;
        }

        public Action build() {
            if (type == null || type.isEmpty()) {
                throw new IllegalArgumentException("At least one key is required");
            }
            return new Action(type, data);
        }
    }
}
