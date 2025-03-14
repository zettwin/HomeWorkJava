public class MaybeError {
    public static void doIt() throws MyException {
        throw new MyException("Что-то пошло не так! Возможно...");
    }
}
