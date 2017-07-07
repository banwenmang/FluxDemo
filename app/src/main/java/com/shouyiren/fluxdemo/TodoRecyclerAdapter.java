package com.shouyiren.fluxdemo;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shouyiren.fluxdemo.actions.ActionCreator;
import com.shouyiren.fluxdemo.model.Todo;

/**
 * 作者：ZhouJianxing on 2017/6/28 11:31
 * email:727933147@qq.com
 */

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {

    private static ActionCreator mActionCreator;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTodoText;
        CheckBox mTodoCheck;
        Button mTodoDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            mTodoText = itemView.findViewById(R.id.row_text);
            mTodoCheck = itemView.findViewById(R.id.row_checkbox);
            mTodoDelete = itemView.findViewById(R.id.row_delete);
        }

        public void bindView(final Todo todo) {
            if (todo.isComplete()) {
                SpannableString spannableString = new SpannableString(todo.getText());
                spannableString.setSpan(new StrikethroughSpan(), 0, spannableString.length(), 0);
                mTodoText.setText(spannableString);
            } else {
                mTodoText.setText(todo.getText());
            }

            mTodoCheck.setChecked(todo.isComplete());
            mTodoCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActionCreator.toggleComplete(todo);
                }
            });

            mTodoDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActionCreator.destroy(todo.getId());
                }
            });
        }
    }
}
