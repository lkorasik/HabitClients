package com.lkorasik.habitclients

fun interface OnItemClicked {
    fun onClick(habit: HabitDTO, position: Int)
}