package com.example.lab3.ui.theme.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab3.model.ToDo
import com.example.lab3.R
import com.example.lab3.model.ToDoViewModel
import com.example.lab3.ui.theme.Lab3Theme
import java.util.*

@Composable
fun TodoScreen(){
    val viewModel: ToDoViewModel = viewModel()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {showDialog = true}
            )
            {
                Icon(Icons.Filled.Add, contentDescription = "", tint = Color.White)
            }
        },
        content = {
            if (showDialog){
                addTodoDialog(context, dismissDialog = {showDialog = false}, {viewModel.add(it)})
            }
            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 8.dp
                )
            )
            {
                items(viewModel.todoList, key={todo -> todo.id}) { todo ->
                    TodoItem(item = todo, context, {viewModel.delete(it)})
                }
            }
        }
    )
}

@Composable
fun addTodoDialog(context: Context, dismissDialog:() -> Unit, addTodo: (ToDo) -> Unit){
    var todoTextField by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = { dismissDialog},
        title={ Text(text = stringResource(id = R.string.addTodo), style = MaterialTheme.typography.h6) },
        text = {
            Column(modifier = Modifier.padding(top=20.dp)) {
                TextField(label = { Text(text= stringResource(id = R.string.todoName)) }, value = todoTextField, onValueChange = {todoTextField=it})
                Spacer(modifier = Modifier.height(10.dp))
            }
        },
        confirmButton = {
            Button(onClick = {
                if(todoTextField.isNotEmpty()) {
                    val newID = UUID.randomUUID().toString();
                    addTodo(ToDo(newID, todoTextField))
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.todoAdded),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                dismissDialog()
            })
            {
                Text(text = stringResource(id = R.string.addTask))
            }
        },dismissButton = {
            Button(onClick = {
                dismissDialog()
            }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}

@Composable
fun deleteTodoDialog(context: Context, dismissDialog:() -> Unit, item: ToDo, deleteBook: (ToDo) -> Unit){
    AlertDialog(
        onDismissRequest = { dismissDialog},
        title={ Text(text = stringResource(id = R.string.delete), style = MaterialTheme.typography.h6) },
        confirmButton = {
            Button(onClick = {
                deleteBook(item)
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.deleteTodo),
                    Toast.LENGTH_SHORT
                ).show()
                dismissDialog()
            })
            {
                Text(text = stringResource(id = R.string.yes))
            }
        },dismissButton = {
            Button(onClick = {
                dismissDialog()
            }) {
                Text(text = stringResource(id = R.string.no))
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoItem(item: ToDo, context: Context, deleteTodo: (ToDo) -> Unit) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(2.dp, color = MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    showDeleteDialog = true
                }
            )

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(8.dp)
            ) {
                val checkedState = remember { mutableStateOf(false) }
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it}
                )

                Text(text = item.description, style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 10.dp)
                )

                if (checkedState.value == true){
                    Toast
                        .makeText(
                            context,
                            context.resources.getString(R.string.completed),
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            }

        }

    }
    if (showDeleteDialog){
        deleteTodoDialog(context, dismissDialog = {showDeleteDialog = false}, item, deleteTodo)
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab3Theme {
        TodoScreen()
    }
}