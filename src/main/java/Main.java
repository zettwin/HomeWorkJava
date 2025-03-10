import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            MaybeError.doIt();
        } catch (MyException e) {
            System.out.println("О нет, неожиданная ошибка: " + e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
