import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class UniqueNumberGuessingGame {
    public static void main(String[] args) {
        final int MIN = 1;
        final int MAX = 100;
        final int MAX_TRIES = 6;

        int secret = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
        int lowHint = MIN;
        int highHint = MAX;
        int tries = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("** Unique Number Guessing Game **");
        System.out.println("Try to guess the number between " + MIN + " and " + MAX + ".");

        while (tries < MAX_TRIES) {
            System.out.print("Guess (" + (tries + 1) + "/" + MAX_TRIES + "): ");
            int guess;

            try {
                guess = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a whole number.");
                continue;
            }

            if (guess < lowHint || guess > highHint) {
                System.out.println("Out of the current hint range! Try between " + lowHint + " and " + highHint);
                continue;
            }

            tries++;

            if (guess == secret) {
                System.out.println("Correct! You found the number in " + tries + " tries.");
                return;
            } else if (guess < secret) {
                System.out.println("Too low!");
                if (guess + 1 > lowHint) lowHint = guess + 1;
            } else {
                System.out.println("Too high!");
                if (guess - 1 < highHint) highHint = guess - 1;
            }

            System.out.println("Hint range: " + lowHint + " to " + highHint);
        }

        System.out.println("Game over! The number was: " + secret);
        input.close();
    }
}