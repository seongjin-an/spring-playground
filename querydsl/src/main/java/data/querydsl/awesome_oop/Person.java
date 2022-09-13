package data.querydsl.awesome_oop;

public interface Person<T extends Programming> {
    String getResult(T t);
}
