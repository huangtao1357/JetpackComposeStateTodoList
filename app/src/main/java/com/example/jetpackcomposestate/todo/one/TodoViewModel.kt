package com.example.jetpackcomposestate.todo.one

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposestate.todo.TodoItem

/**
 * 状态容器
 */
class TodoViewModel : ViewModel() {

    var todoItems = mutableStateListOf<TodoItem>()
        private set

    //当前正在编辑的TodoItem的索引位置
    private var currentEditPosition by mutableStateOf(-1)

    //当前正在编辑的TodoItem对象
    val currentEditItem: TodoItem?
        get() = todoItems.getOrNull(currentEditPosition)

    fun addItem(item: TodoItem) {
        todoItems.add(item)
    }

    fun removeItem(item: TodoItem) {
        todoItems.remove(item)
        onEditDone()
    }

    fun onEditDone() {
        currentEditPosition = -1
    }

    //当TodoItem列表中的条目被选中时，传入该对象，获取它在列表中的索引位置
    fun onEditItemSelected(item: TodoItem) {
        currentEditPosition = todoItems.indexOf(item)
    }

    // TodoItem编辑完成，重新给集合中的TodoItem赋值
    // id属性值不能修改，进行校验
    fun onEditItemChange(item: TodoItem) {
        val currentItem = requireNotNull(currentEditItem)
        require(currentItem.id == item.id) {
            "You can only change an item with the same id as currentEditItem"
        }
        todoItems[currentEditPosition] = item
    }

}