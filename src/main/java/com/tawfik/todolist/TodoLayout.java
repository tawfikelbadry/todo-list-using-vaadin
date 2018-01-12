package com.tawfik.todolist;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Arrays;

public class TodoLayout extends HorizontalLayout{

    private TextField text;
    private CheckBox done;

    public TodoLayout(Todo todo,TodoChangedListener listener) {
        setSpacing(true);
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        done = new CheckBox();
        text = new TextField();

        text.setWidth("100%");
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

        done.setValue(todo.isDone());
        text.setValue(todo.getText());


        addComponents(done,text);
        setExpandRatio(text,1);

        done.addValueChangeListener(change->{
            todo.setDone(done.getValue());
            listener.todoChanged(todo);
        });

        text.addBlurListener(change->{
            todo.setText(text.getValue());
            listener.todoChanged(todo);
        });



    }


}
