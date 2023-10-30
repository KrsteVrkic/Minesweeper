import java.util.Scanner;
import java.util.Stack;

class Main {

    // main stack
    static Stack<Integer> mainStack = new Stack<>();

    //track stack
    static Stack<Integer> trackStack = new Stack<>();

    public static void main(String[] args) {

        //it's over 9000
        Scanner scanner = new Scanner(System.in);

        //n = numOps
        int n = scanner.nextInt();
        scanner.nextLine();
        int number = 0;

        //loop n times
        for (int i = 0; i < n; i++) {

            //read + parse
            String input = scanner.nextLine();
            String[] inputs = input.split(" ");
            if (inputs.length > 1) {
                number = Integer.parseInt(inputs[1]);
            }
            String inputString = inputs[0];




            switch (inputString) {
                case "push":
                    push(number);
                    break;
                case "max":
                    System.out.println(trackMax());
                    break;
                case "pop":
                    pop();


            }


        }
    }

    static private void push(int x) {

        mainStack.push(x);
        if (mainStack.size() == 1) {
            trackStack.push(x);
            return;
        }

         /*If current element is greater than
         the top element of track stack, push
         the current element to track stack
         otherwise push the element at top of
         track stack again into it. */

        if (x > trackStack.peek())
            trackStack.push(x);
        else
            trackStack.push(trackStack.peek());
    }

    static void pop()
    {
        mainStack.pop();
        trackStack.pop();
    }

    static int trackMax() {
        return trackStack.peek();
    }




}