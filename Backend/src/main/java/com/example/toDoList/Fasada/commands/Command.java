package com.example.toDoList.Fasada.commands;


public interface Command<T,U> {
    T execute(U u);
}
