import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Deque<String> stack = new ArrayDeque();
        boolean correct = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(String.valueOf(c));
            }

            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    System.out.println("false");
                    return;
                }

                String pop = stack.pop();
                if (!pop.equals("(") && c == ')') {
                    System.out.println("false");
                    return;
                }
                if (!pop.equals("{") && c == '}') {
                    System.out.println("false");
                    return;
                }
                if (!pop.equals("[") && c == ']') {
                    System.out.println("false");
                    return;
                }
            }
        }
        if (!stack.isEmpty()) {
            System.out.print("false");
            return;
        }
        System.out.print("true");
    }
}