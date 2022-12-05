package com.library.modules.home;

import com.library.mvputils.BaseView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
@Route("home")
public class HomeView extends BaseView<HomePresenter> {

    @Override
    protected void init() {

    }
}
