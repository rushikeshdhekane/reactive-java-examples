package io.rushi.reactiveworkshop;

import java.io.IOException;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberFlux() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        
    	ReactiveSources.intNumbersFlux().subscribe(
        		number->System.out.println(number),
        		err->System.out.println(err.getMessage()),
        		()->System.out.println("Complete"));

        // Subscribe to a flux using an implementation of BaseSubscriber
        
    	//it is used for handle pressure of event 
    	//means you not fast enough to handle these event you slow down the process
    	
    	ReactiveSources.intNumbersFlux().subscribe(new MySubScriber<>());

    	
    	
        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubScriber<T> extends BaseSubscriber<T>{
//its not used wide but control pressure for that use these
	@Override
	protected void hookOnSubscribe(Subscription subscription) {
		System.out.println("Subscribe happened");
		request(1);
		
	}

	//you handle pressure of stream of data by one by one 
	@Override
	protected void hookOnNext(T value) {
		System.out.println(value.toString() +" received");
		request(1);
	}

	@Override
	protected void hookOnComplete() {
		System.out.println("Completed");
	}
	
	
	
	
	
	
}