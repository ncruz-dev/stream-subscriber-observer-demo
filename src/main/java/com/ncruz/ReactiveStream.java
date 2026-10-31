package com.ncruz;

import lombok.extern.java.Log;

import java.util.LinkedList;
import java.util.List;

@Log
public class ReactiveStream<T> {

    private final List<Subscriber<T>> subscribers = new LinkedList<>();//los subscriptores van agregandose y borrandose una LinkedList es más optimo

    public ReactiveStream<T> subscribe (Subscriber<T> subscriber) {
        this.subscribers.add(subscriber);
        log.info("[Subscriber added]: " + subscriber.getName());

        return this;//Patron de Change!!! estoy regresando el objeto para seguir modificandolo
    }

    public void unsubscribe(Subscriber<T> subscriber) {
        this.subscribers.remove(subscriber);
        log.info("[Subscriber removed]: " + subscriber.getName());
    }

    public void emit(T value){ // notifica a todos los subscriptores que hay un nuevo valor
        this.subscribers.forEach(sub-> sub.onNext(value));
    }
}
