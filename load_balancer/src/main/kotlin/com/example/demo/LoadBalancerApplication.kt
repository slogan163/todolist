package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoadBalancerApplication

fun main(args: Array<String>) {
    runApplication<LoadBalancerApplication>(*args)
}

/*
* логин email + password > token,
* User id, email, password_hash, token
*
* если нет токена, 401
* если есть, то проксируем запрос
*
*
* Логин принимает mail,password > провераяем есть ли user
* если есть, генерим токен и возвращаем на фронт
* все запросы с этим токенов
*
* 3 сервиса - фронт, таск, ауф
* обнулять токен раз в день
*
*
*
* sign up -
* email, password
* пользователь в БД, генерим ид > log in
*
*
*
* loadbalancer ???
*
*
*
*
* */