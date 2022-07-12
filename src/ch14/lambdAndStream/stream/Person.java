package ch14.lambdAndStream.stream;

import java.util.List;

public class Person {
    private String name;
    private int age;
    private String sex;

    public Person(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public static List<Person> makePeople() {
        return List.of(
          new Person("kim", 26, "남"),
          new Person("lee", 18, "여"),
          new Person("ryu", 18, "남"),
          new Person("park", 14, "여"),
          new Person("choi", 26, "여"),
          new Person("hong", 18, "여"),
          new Person("shin", 17, "남"),
          new Person("kang", 14, "남"),
          new Person("eun", 26, "남")
        );
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }
}
