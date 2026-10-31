package com.ncruz;

import lombok.extern.java.Log;

@Log
public class Main {
    public static void main(String[] args) {

        final ReactiveStream<String> stringStream = new ReactiveStream<>();//Publisher
        final ReactiveStream<Integer> integerStream = new ReactiveStream<>();//Publisher

        final String subsName1 = "Subscriber1";
        final String subsName2 = "Subscriber2";
        final String subsName3 = "Subscriber3";
        final String subsName4 = "Subscriber4";

        // subscriber for stringStream
        final Subscriber<String> stringSubscriber1 = new SubscriberImpl<>(
                str -> "Length: " + str.length(),
                subsName1
        );

        // subscriber for stringStream
        final Subscriber<String> stringSubscriber2 = new SubscriberImpl<>(
                str -> "Uppercase: " + str.toUpperCase(),
                subsName2
        );

        // subscriber for integerStream
        final Subscriber<Integer> integerSubscriber1 = new SubscriberImpl<>(
                num -> "Value: " + num,
                subsName3
        );

        // subscriber for integerStream
        final Subscriber<Integer> integerSubscriber2 = new SubscriberImpl<>(
                num -> "Squared: " + (num * num),
                subsName4
        );

        //conectando los subscriptores con los publishers
        stringStream
                .subscribe(stringSubscriber1)
                .subscribe(stringSubscriber2);

        integerStream
                .subscribe(integerSubscriber1)
                .subscribe(integerSubscriber2);

        log.info("-------[Emitting values to stringStream]--------");
        stringStream.emit("Hello world");
        stringStream.emit("This is a Subscriber2");
        stringStream.emit("Ncruz-Dev");
        stringStream.emit("My hyrthday is Nov sixth");

        log.info("-------[Emitting values to integerStream]--------");
        integerStream.emit(5);
        integerStream.emit(10);

        log.info("-------[Unsubscribing subscribers]--------");
        stringStream.unsubscribe(stringSubscriber2);
        integerStream.unsubscribe(integerSubscriber1);
        integerStream.unsubscribe(integerSubscriber2);

    }
}