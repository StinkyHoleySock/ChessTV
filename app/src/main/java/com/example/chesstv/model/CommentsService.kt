package com.example.chesstv.model

import com.example.chesstv.tasks.Task
import com.github.javafaker.Faker

typealias CommentsListener = (comment: List<Comment>) -> Unit

class CommentsService {

    private var users = mutableListOf<Comment>()
    private var isLoaded = false
    private val listeners = mutableSetOf<CommentsListener>()

    fun loadComments(): Task<Unit> = Task.Base {
        val faker = Faker.instance()
        IMAGES.shuffle()
        users = (1..100).map {
            Comment(
                id = it,
                name = faker.name().name(),
                photo = IMAGES[it % IMAGES.size],
                content = faker.yoda().quote()
            )
        }.toMutableList()
        isLoaded = true
        notifyChanges()
    }

    fun deleteComment(comment: Comment): Task<Unit> = Task.Base {
        val indexToDelete = users.indexOfFirst { it.id == comment.id }
        if (indexToDelete != -1) {
            users.removeAt(indexToDelete)
        }

        notifyChanges()
    }

    fun addListener(listener: CommentsListener) {
        listeners.add(listener)
        if (isLoaded)
            listener.invoke(users)
    }

    fun removeListener(listener: CommentsListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        if (!isLoaded) return
        listeners.forEach { it.invoke(users)}
    }

    companion object {
        private val IMAGES = mutableListOf(
            "https://avatarko.ru/img/kartinka/21/film_mech_gnom_20315.jpg",
            "https://avatarko.ru/img/kartinka/15/film_ulybka_14475.jpg",
            "https://avatarko.ru/img/kartinka/16/muzhchina_serial_ulybka_15941.jpg",
            "https://avatarko.ru/img/kartinka/29/computer_obezyana_28703.jpg",
            "https://avatarko.ru/img/kartinka/25/deti_bozhi_korovki_24954.jpg",
            "https://avatarko.ru/img/kartinka/20/ochki_deti_computer_19977.jpg",
            "https://avatarko.ru/img/kartinka/10/ochki_palec_9034.jpg",
            "https://avatarko.ru/img/kartinka/12/zhivotnye_kot_ochki_11485.jpg",
            "https://avatarko.ru/img/kartinka/9/serial_8759.jpg",
            "https://avatarko.ru/img/kartinka/29/serial_Game_of_Thrones_Daenerys_28700.jpg",
            "https://avatarko.ru/img/kartinka/25/muzhchina_serial_dengi_24933.jpg",
            "https://avatarko.ru/img/kartinka/14/serial_13916.jpg",
            "https://avatarko.ru/img/kartinka/16/film_gnom_15452.jpg",
            "https://avatarko.ru/img/kartinka/15/film_gnom_14558.jpg",
            "https://avatarko.ru/img/kartinka/11/film_muzhchina_galstuk_10235.jpg",
            "https://avatarko.ru/img/kartinka/11/film_elf_Middle-earth_Arwen_10847.jpg",
            ""
        )
    }
}