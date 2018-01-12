package com.tawfik.todolist;

import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoList extends VerticalLayout implements TodoChangedListener{

    @Autowired
    TodoRepository todoRepository;

    List<Todo> todos;

    @PostConstruct
    void init(){
        setSpacing(true);
        setTodos(todoRepository.findAll());

    }

    private void setTodos(List<Todo> all) {
        this.todos=all;
        removeAllComponents();
        all.forEach(todo->{
           addComponent(new TodoLayout(todo,this));

        });

    }

    public void addTodo(Todo todo) {
        todoRepository.save(todo);
        update();
    }

    private void update() {
        setTodos(todoRepository.findAll());
    }

    @Override
    public void todoChanged(Todo todo) {
        addTodo(todo);
    }

    public void deleteCompleted() {

        todoRepository.deleteInBatch(todos.stream().filter(Todo::isDone).collect(Collectors.toList()));
        update();
    }
}
