package commons;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message); // super를 직접정의하지않으면 기본생성자만
    }
}
