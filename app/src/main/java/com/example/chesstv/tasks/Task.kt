package com.example.chesstv.tasks

import android.os.Looper
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

typealias Callback<T> = (T) -> Unit

private val executorService = Executors.newCachedThreadPool()
private val handler = android.os.Handler(Looper.getMainLooper())


/*
Абстракция задачи, которая может выдать результат, ошибку,
задачу можно отменить, а также вызвать метод await, чтобы
дождать результат асинхронно
 */

interface Task<T> {

    fun onSuccess(callback: Callback<T>): Task<T>

    fun onError(callback: Callback<Throwable>): Task<T>

    fun cancel()

    fun await(): T

    class Base<T>(private val callable: Callable<T>): Task<T> {

        private var result: Result<T> = PendingResult()
        private val future: Future<*>

        init {
            future = executorService.submit {
                result = try {
                    SuccessResult(callable.call())
                } catch (e: Throwable) {
                    ErrorResult(e)
                }
                notifyListeners()
            }
        }

        private var valueCallback:  Callback<T>? = null
        private var errorCallback:  Callback<Throwable>? = null

        override fun onSuccess(callback: Callback<T>): Task<T> {
            this.valueCallback = callback
            notifyListeners()
            return this
        }

        override fun onError(callback: Callback<Throwable>): Task<T> {
            this.errorCallback = callback
            notifyListeners()
            return this
        }

        override fun cancel() {
            clear()
            future.cancel(true)
        }

        override fun await(): T {
            future.get()
            if (this.result is SuccessResult) return (this.result as SuccessResult<T>).data
            else throw (this.result as ErrorResult).error
        }

        private fun notifyListeners() {
            handler.post {
                when {
                    (this.result is SuccessResult && this.valueCallback != null) -> {
                        this.valueCallback?.let { it((result as SuccessResult<T>).data) }
                        clear()
                    }
                    (this.result is ErrorResult && this.errorCallback != null) -> {
                        this.errorCallback!!.invoke((result as ErrorResult<T>).error)
                        clear()
                    }
                }
            }
        }

        private fun clear() {
            valueCallback = null
            errorCallback = null
        }
    }
}

