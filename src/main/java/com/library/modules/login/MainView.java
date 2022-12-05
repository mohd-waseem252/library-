package com.library.modules.login;

import com.library.entity.User;
import com.library.mvputils.BaseView;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringComponent
@UIScope
@Route("")
public class MainView extends BaseView<LoginPresenter> {

    @Autowired
    private LoginPresenter loginPresenter;

    private H1 heading;

    private Dialog loginForm;

    private Dialog signupForm;

    private Button loginButton;

    private Button signupButton;

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField contactNoField;
    private EmailField emailField;
    private TextField passwordField;

    private DatePicker dateField;

    private Button submitButton;

    private Binder<User> userBinder;


    @Override
    @PostConstruct
    protected void init() {
        setAlignItems(Alignment.CENTER);
        initializeMainFields();
        initializeSignupFields();
        setUpLoginInDialog();

    }

    private void initializeSignupFields() {
        User user=new User();
        firstNameField=new TextField("First Name","Enter your first name..");

        lastNameField=new TextField("Last Name","Enter your last name..");

        contactNoField=new TextField("Contact No");

        emailField=new EmailField("Email ID");

        dateField=new DatePicker("Date of Joining");
        dateField.setValue(LocalDate.now());
        dateField.setEnabled(false);

        passwordField=new TextField("Password");

        submitButton=new Button("Submit");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.addClickListener(event -> {
            if(userBinder.isValid()){
                try {
                    userBinder.writeBean(user);
                    loginPresenter.addUser(user);

                    signupForm.close();
                    Notification.show("Successfully Added Please Login",2000, Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    loginForm.open();
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void setUpLoginInDialog() {
        loginForm=new Dialog(loginDialogLayout());
        loginForm.setDraggable(false);
        loginForm.setWidth(30,Unit.PERCENTAGE);
        loginForm.setHeight(50,Unit.PERCENTAGE);


    }

    private void setUpSignUpDialog(){
        signupForm=new Dialog(signupDialogLayout());
        signupForm.setDraggable(false);
        signupForm.setWidth(25,Unit.PERCENTAGE);
        signupForm.setHeight(60,Unit.PERCENTAGE);
    }
    private VerticalLayout loginDialogLayout() {

        LoginForm form = new LoginForm();
        form.addLoginListener(event -> {
            if(loginPresenter.userExists(event.getUsername(),event.getPassword())) {
                loginForm.close();
                Notification.show("Successfully LoggedIn",3000, Notification.Position.TOP_END).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }else{
                Notification.show("Incorrect Username or Password",3000, Notification.Position.MIDDLE).addThemeVariants(NotificationVariant.LUMO_ERROR);
                form.setEnabled(true);
            }
        });
        Div div=new Div(form);
        div.getStyle().set("margin","auto");

        VerticalLayout layout=new VerticalLayout(div);
        return layout;
    }

    private Div signupDialogLayout(){
        Label label=new Label("SIGN UP");
        label.getStyle().set("font-size","20px");
        FormLayout formLayout=new FormLayout();
        formLayout.add(label,firstNameField,lastNameField,dateField,contactNoField,emailField,passwordField,submitButton);

        Div div=new Div(formLayout);
        div.getStyle().set("margin","auto");
        return div;

    }

    private void setUserBinder(){
        userBinder=new Binder<>();
        userBinder.forField(firstNameField).withNullRepresentation("")
                .withValidator(s -> s.length()>=3,"must be 3 characters" )
                .bind(User::getFirstName,User::setFirstName);
        userBinder.forField(lastNameField).bind(User::getLastName,User::setLastName);
        userBinder.forField(dateField).bind(User::getJoinedDate,User::setJoinedDate);
        userBinder.forField(contactNoField).withNullRepresentation("")
                .withConverter(new StringToLongConverter("Not A Number"))
                .withValidator(s -> s!=null?s.toString().length()==10:false,"must be 10 Digit Long")
                .withValidator(s -> {
                   if(loginPresenter.findUserByContactNo(s.toString())){
                       return false;
                   }else{
                       return true;
                   }
                },"Already Registered")
                .bind(user -> user.getContactNo()==null?0:Long.valueOf(user.getContactNo()),(user, aLong) -> user.setContactNo(aLong.toString()));
        userBinder.forField(emailField).withValidator(new EmailValidator("Invalid Email Id"))
                .withValidator(email -> {
                    if(loginPresenter.findUserByEmailId(email)){
                        return false;
                    }else{
                        return true;
                    }
                },"Already Registered")
                .bind(User::getEmailId,User::setEmailId);
        userBinder.forField(passwordField).bind(User::getPassword,User::setPassword);

    }

    private void initializeMainFields() {
        getStyle().set("background-image","url('images/library-image.jpg')").set("background-size","cover");
        setSizeFull();
        setPadding(false);
        heading=new H1("Welcome To My Library");
        heading.getStyle().set("color","white")
                            .set("font-weight","bold").set("font-size","40px")
                            .set("font-style","italic");

        loginButton=new Button("Login");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_LARGE);
        loginButton.setWidth(10,Unit.PERCENTAGE);
        loginButton.getStyle().set("margin-top","15%").set("font-size","25px");
        loginButton.addClickListener(event -> {
           loginForm.open();
        });

        signupButton=new Button("Signup");
        signupButton.addThemeVariants(ButtonVariant.LUMO_LARGE);
        signupButton.setWidth(10,Unit.PERCENTAGE);
        signupButton.getStyle().set("margin-top","5%").set("background-color","green").set("color","white").set("font-size","25px");
        signupButton.addClickListener(event -> {setUserBinder();
           setUpSignUpDialog();
           signupForm.open();
        });


        add(heading,loginButton,signupButton);
    }
}
