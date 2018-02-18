package com.example.gustavobarbosab.ninemessage.screens.chat.mvp;

import android.os.Bundle;

import com.example.gustavobarbosab.ninemessage.domain.Message;

/**
 * Created by gustavobarbosab on 06/02/18.
 */

public interface ChatContract {
    String recyclerSave = "RECYCLER_VIEW";
    String usernameSave = "username";
    String numUsersSave = "numUsers";

    interface View{
        void sendMessage();
        void restoreInstance(Bundle state);
        void saveInstance(Bundle state);

    }
    interface Model{
        Message sendMessage(String text);

    }
    interface Presenter{
        void restoreInstance(Bundle state);
        void saveInstance(Bundle state);
        void sendMessage();

    }

}
