package com.lkorasik.habitclients

data class HabitDTO(
    val title: String,
    val description:String,
    val priority: Priority,
    val  color: Color,
    val  repeats: Long,
    val done_dates: List<Long>
)