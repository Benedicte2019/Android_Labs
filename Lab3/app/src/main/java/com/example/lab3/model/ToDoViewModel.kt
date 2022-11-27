package com.example.lab3.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ToDoViewModel: ViewModel() {
    var todoList = mutableStateListOf <ToDo>()

    fun add(newTask: ToDo){
        todoList.add(newTask)
    }

    fun delete(task: ToDo){
        todoList.remove(task)
    }
}