package com.ncruz;

import lombok.extern.java.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * En esta clase estamos implementando el patrón de diseño Publisher-Subscriber, donde ReactiveStream actúa como el Publisher
 * que mantiene una lista de suscriptores y notifica a todos ellos cuando hay un nuevo valor emitido.
 * Los suscriptores implementan la interfaz Subscriber, que define cómo procesar los valores recibidos.
 */
@Log
public class ReactiveStream<T> {

    private final List<Subscriber<T>> subscribers = new LinkedList<>();//los subscriptores van agregandose y borrandose una LinkedList es más optimo

    /**
     * El metodo subscribe permite a los suscriptores registrarse para recibir notificaciones de nuevos valores emitidos por el ReactiveStream.
     */
    public ReactiveStream<T> subscribe (Subscriber<T> subscriber) {
        this.subscribers.add(subscriber);
        log.info("[Subscriber added]: " + subscriber.getName());

        return this;//Patron de Change!!! estoy regresando el objeto para seguir modificandolo
    }

    /**
     * El metodo unsubscribe permite a los suscriptores eliminarse de la lista de suscriptores,
     * dejando de recibir notificaciones de nuevos valores emitidos por el ReactiveStream.
     */
    public void unsubscribe(Subscriber<T> subscriber) {
        this.subscribers.remove(subscriber);
        log.info("[Subscriber removed]: " + subscriber.getName());
    }

    /**
     * Emite un nuevo valor a todos los suscriptores registrados.
     * @param value el valor a emitir
     */
    public void emit(T value){ // notifica a todos los subscriptores que hay un nuevo valor
        this.subscribers.forEach(sub-> sub.onNext(value));
    }
}
