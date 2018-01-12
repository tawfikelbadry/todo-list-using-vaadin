package com.tawfik.todolist;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class ToDOUI extends UI{

    private VerticalLayout layout;

    @Autowired
    TodoList todoList;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setLayout();
        addHeader();
        addForm();
        addList();
        addActionButton();
    }

    private void setLayout() {
       layout= new VerticalLayout();
       layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

       setContent(layout);
    }

    private void addHeader() {
        Label header=new Label("TODO LIST");
        header.addStyleName(ValoTheme.LABEL_H1);
        header.setSizeUndefined();
        this.layout.addComponent(header);
    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setSpacing(true);
        formLayout.setWidth("80%");
        TextField taskFeild=new TextField();
        taskFeild.setWidth("100%");
        Button addButton = new Button("");
        addButton.setIcon(FontAwesome.PLUS);
        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        formLayout.addComponents(taskFeild,addButton);
        formLayout.setExpandRatio(taskFeild,1);
        this.layout.addComponent(formLayout);

        addButton.addClickListener(click->{
           todoList.addTodo(new Todo(taskFeild.getValue()));
           taskFeild.clear();
           taskFeild.focus();
        });
        taskFeild.focus();
        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    private void addList() {
        todoList.setWidth("80%");
        this.layout.addComponent(todoList);
    }

    private void addActionButton() {
        Button deleteButton = new Button("Delete completed");
        deleteButton.addClickListener(e->{
            todoList.deleteCompleted();

        });
        layout.addComponent(deleteButton);
    }
}
