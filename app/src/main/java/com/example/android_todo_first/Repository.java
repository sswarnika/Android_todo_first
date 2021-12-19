package com.example.android_todo_first;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Repository {

    private List<Todo> todoList;

    private static Repository sInstance;

    private Repository(){
        todoList = new ArrayList<>();
                for(int i=0; i<10; i++) {
                    Todo todo = new Todo();
                    todo.setTitle("title"+i);
                    todo.setDescription("description"+i);
                    todo.setPriority(i);
                    todoList.add(todo);
                }
    }

    public static Repository getsInstance(Context context){
        if(sInstance == null)
            sInstance = new Repository();
        return sInstance;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public Todo getTodoById(UUID id){
        for(Todo item : todoList){
            if(item.getId().equals(id))
                return item;
        }
        return null;
    }

    public void deleteAll(){
        todoList.clear();
    }

    public void delete(UUID id){
        Todo todo = getTodoById(id);
        if(todo != null){
            todoList.remove(todo);
        }
    }

    public Todo update(Todo todo){
        Todo newTodo = getTodoById(todo.getId());
        newTodo.setPriority(todo.getPriority());
        newTodo.setTitle(todo.getTitle());
        newTodo.setDescription(todo.getDescription());
        newTodo.setUpdatedAt(new Date());
        return newTodo;
    }
}
