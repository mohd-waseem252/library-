package com.library.mvputils;

public interface View<P extends Presenter> {

    P getPresenter();
}
