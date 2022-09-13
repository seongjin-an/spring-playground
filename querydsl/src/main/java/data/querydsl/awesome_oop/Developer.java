package data.querydsl.awesome_oop;

public class Developer<T extends Programming> implements Person<T>{
    @Override
    public String getResult(T t) {
        String lang = t.build();
        return lang;
    }
}
