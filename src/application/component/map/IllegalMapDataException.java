package application.component.map;

public class IllegalMapDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public IllegalMapDataException() {
        System.out.println("不適切なMapデータです");
    }

    public IllegalMapDataException(String message) {
        super(message);
    }
}
