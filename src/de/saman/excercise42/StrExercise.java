package de.saman.excercise42;

public class StrExercise {

	
	public static void main(String[] args) {
		String test = "That’s it! You people have stood in my way long enough. I’m going to clown college!";
		System.out.println(test.length());
		System.out.println(test.charAt(5));
		System.out.println(test.contains("way"));
		System.out.println(test.indexOf("You"));
		System.out.println(test.equals("That’s it! You people have stood in my way long enough. I’m going to clown college!"));
		System.out.println(test==new String("That’s it! You people have stood in my way long enough. I’m going to clown college!"));
	}
	
}
