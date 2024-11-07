package io.rushi.reactiveworkshop;

public class Exercise1 {

	public static void main(String[] args) {

		// Use StreamSources.intNumbersStream() and StreamSources.userStream()

		// Print all numbers in the intNumbersStream stream
		
		StreamSources.intNumbersStream()
			.forEach(number -> System.out.println(number));

		// Print numbers from intNumbersStream that are less than 5
		
		StreamSources.intNumbersStream()
			.filter(number -> number < 5)
			.forEach(e -> System.out.println(e));

		// Print the second and third numbers in intNumbersStream that's greater than 5
		
		StreamSources.intNumbersStream()
			.filter(number -> number < 5).skip(1).limit(2)
			.forEach(number -> System.out.println(number));

		// Print the first number in intNumbersStream that's greater than 5.
		// If nothing is found, print -1
		
		Integer value = StreamSources.intNumbersStream()
										.filter(number -> number > 5).
										findFirst().orElse(-1);
		System.out.println(value);

		// Print first names of all users in userStream
		
		StreamSources.userStream()
			.forEach(user -> System.out.println(user.getFirstName()));
										
											//or
		
		StreamSources.userStream()
			.map(user->user.getFirstName())
			.forEach(user -> System.out.println(user));

		// Print first names in userStream for users that have IDs from number stream
		
		System.out.println("Rushi");
		
		StreamSources.userStream()
			.filter(u -> StreamSources.intNumbersStream().anyMatch(i-> i==u.getId()))
			.forEach(user->System.out.println(user.getFirstName()));
		
											//or
		
		StreamSources.intNumbersStream()
		.flatMap(id -> StreamSources.userStream().filter(user-> user.getId() == id) )
		.forEach(userName->System.out.println(userName.getFirstName()));
		

	}

}
