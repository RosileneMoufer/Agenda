package com.example.agenda.constants

enum class TaskStatus(val value:String) {
    PENDING("Pendente"),
    IN_PROGRESS("Em progresso"),
    FINISHED("Terminado")
}

enum class ItemsSubMenu(val value:String) {
    PENDING("Pendente"),
    IN_PROGRESS("Em progresso"),
    FINISHED("Terminado")
}

val itemsSubMenu = arrayOf("Pendentes", "Em progresso", "Terminados")



