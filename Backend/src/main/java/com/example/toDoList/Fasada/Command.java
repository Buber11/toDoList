package com.example.toDoList.Fasada;


public interface Command<T,U> {
    T execute(U u);
}
