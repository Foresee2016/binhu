package org.foresee.binhu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class DeleteConfirmDialogFragment extends DialogFragment {
    private static final String ARGS_ALERT_MSG = "alert_msg";
    private DeleteConfirmDialogListener mDeleteConfirmDialogListener;
    public static DeleteConfirmDialogFragment newInstance(int alertMsgResId, DeleteConfirmDialogListener deleteConfirmDialogListener) {
        DeleteConfirmDialogFragment fragment = new DeleteConfirmDialogFragment();
        fragment.setDeleteConfirmDialogListener(deleteConfirmDialogListener);
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_ALERT_MSG, alertMsgResId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int alertMsgResId = getArguments().getInt(ARGS_ALERT_MSG, R.string.default_confirm_msg);
        //这里可以自己inflate view并调用AlertDialog的setView方法设置自定义内容，这里只是简单消息，用不到
        return new AlertDialog.Builder(getActivity())
                .setMessage(alertMsgResId)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDeleteConfirmDialogListener.onConfirmed();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    public void setDeleteConfirmDialogListener(DeleteConfirmDialogListener deleteConfirmDialogListener) {
        mDeleteConfirmDialogListener = deleteConfirmDialogListener;
    }

    public interface DeleteConfirmDialogListener{
        void onConfirmed();
    }
}
