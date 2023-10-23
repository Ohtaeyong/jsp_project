package commons;

public interface Validator<T> { // 다양한 형태의 검증을 위한 제네릭타입
    void check(T t);
}
