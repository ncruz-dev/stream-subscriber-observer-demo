package com.ncruz;

public interface Subscriber<T> {
    // Gard <-- Client1, Client2, Client3, ...
    void onNext(T next); // en el subscriber me permite procesar el siguiente elemento

    String getName(); // en el subscriber me permite identificar al cliente

    //Project Reactor Call backs
    /*void onError(Throwable throwable);
    void onCancel();
    void onComplete();*/

}
