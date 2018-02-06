package com.example.gustavobarbosab.ninemessage.screens.chat.mvp;

import android.util.Log;

import com.example.gustavobarbosab.ninemessage.R;
import com.example.gustavobarbosab.ninemessage.domain.*;
import com.example.gustavobarbosab.ninemessage.domain.events.ErrorEvent;
import com.example.gustavobarbosab.ninemessage.domain.events.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gustavobarbosab on 26/01/18.
 */
public class ChatPresenter{

    EventBus eventBus;
    ChatView view;
    ChatModel model;
    CompositeDisposable compositeDisposable;
    List<Message> messages = new ArrayList<>();

    public ChatPresenter(ChatView view, ChatModel model, CompositeDisposable compositeDisposable, EventBus eventBus) {
        this.view = view;
        this.model = model;
        this.eventBus = eventBus;
        this.compositeDisposable = compositeDisposable;
        view.setmAdapter(messages);
        eventBus.register(this);
        ButterKnife.bind(this,view.view());
    }

    public void onCreate() {
        Log.d("entrando no presenter", "tudo ok");
    }

    public void onDestroy(){
        compositeDisposable.clear();
    }


    @Subscribe
    public void onMessageEvent(MessageEvent messageEvent){
        this.messages.add(messageEvent.getMessage());
        view.notifyDataChanged();
    }

    @Subscribe
    public void onError(ErrorEvent error){
        view.toastMessage(error.getError());
    }

    @OnClick(R.id.sendButton)
    void sendMessage(){
        messages.add(model.fakeMessage(view.getMessageText()));
        view.notifyDataChanged();
    }

}