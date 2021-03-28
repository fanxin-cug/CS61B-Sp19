public class HelloNumbers {
    public static void main(String[] args) {
        //System.out.println(5 + "10");
        int x = 0,ans=0;
        while (x < 10) {
            ans=ans+x;
            System.out.print(ans + " ");
            x = x + 1;
        }
    }
}