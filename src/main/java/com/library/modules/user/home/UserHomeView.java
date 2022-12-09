package com.library.modules.user.home;

import com.library.modules.template.UserTemplate;
import com.library.mvputils.BaseView;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
@Route(value = "user/home",layout = UserTemplate.class)
public class UserHomeView extends BaseView<UserHomePresenter> {


    @Override
    protected void init() {

    }
}
