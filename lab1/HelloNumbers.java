/*Revised for Exercise 1.1.2.
Modify HelloNumbers so that it prints out 
the cumulative sum of the integers from 0 to 9.
For example, your output should start with 0 1 3 6 10...
and should end with 45.*/
public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int i = 0;
        while (i <= 9) {
            x = x + i;
            i += 1;
            System.out.print(x + " ");
        }
    }
}

/*
1. All code in Java must be part of a class.
2. We delimit the beginning and end of segments of code
   using { and }.
3. All statements in Java must end in a semi-colon.
4. For code to run we need public static void main(String[] args)
*/