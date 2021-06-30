public class Palindrome {

    public static void main(String[] args) {

        int num = 121, reversedInteger = 0, remainder, originalInteger;originalInteger = num;System.out.println(" originalInteger : "+originalInteger);

        

        // reversed integer is stored in variable 
        while( num != 0 )
        {remainder = num % 10;System.out.println(" remainder : "+remainder);
            reversedInteger = reversedInteger * 10 + remainder;System.out.println(" reversedInteger : "+reversedInteger);
            
            num  /= 10;
        }

        // palindrome if orignalInteger and reversedInteger are equal
        if (originalInteger == reversedInteger)
            System.out.println(originalInteger + " is a palindrome.");
        else
            System.out.println(originalInteger + " is not a palindrome.");
    }
}