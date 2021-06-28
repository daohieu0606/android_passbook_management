package com.example.passbook.activities.form;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.passbook.activities.base.BasePresenter;
import com.example.passbook.services.AppDatabase;

public abstract class FormPresenter extends BasePresenter implements FormContract.Presenter {
    private FormContract.View view;

    public FormPresenter(FormContract.View view) {
       super(view);
       this.view = view;
    }

    @Override
    public void handleSubmit(Object... objects) {
        if(manualCheck(objects)) {
            if(saveData(objects)) {
                view.handleSuccess();
            }
        } else {
            view.handleFailed();
        }
    }

    protected abstract boolean saveData(Object... objects);

    protected abstract boolean manualCheck(Object... objects);
}
