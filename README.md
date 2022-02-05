# 자바의 정석 3판 다시 보기

## Ch 05. array

- 배열을 복사할 때 for문 보다 System.arraycopy()를 이용하자.
  - native code를 호출해서 더 빠르기 때문이다.

`System.arraycopy(srcArray, srcArrayStartIndex, desArray, desArrayStartIndex, CountOfSrcArray)`

```java
int[] src = {1, 2, 3};
int[] des = new int[5]; // des는 항상 src의 크기와 같거나 커야한다.

System.arraycopy(src, 0 , des, 0, src.length);

System.out.println(Arrays.toString(src)); // [1, 2, 3]
System.out.println(Arrays.toString(des)); // [1, 2, 3, 0, 0]
```

> array.clone() 과의 차이는 무엇일까?

- System.arraycopy는 JNI(Java Native Interface)를 사용해서 배열의 깊은 복사를 수행할 수 있다. 또한, 부분적으로 복사할 수도 있다.
- array.clone은 복사할 배열과 동일한 크기와 유형으로 `new` 형식으로 새로운 배열을 내부적으로 생성한다.

---

## CH 06. 객체지향 프로그래밍 1

### 변수의 종류

| 종류      | 선언 위치  | 생성 시기           |
|---------|--------|-----------------|
| 클래스 변수  | 클래스 영역 | 클래스가 메모리에 올라갈 때 |
| 인스턴스 변수 | 클래스 영역 | 인스턴스가 생성되었을 때   |
| 지역 변수   | 메서드 영역 | 변수 선언문을 만났을 때   |

- 클래스 변수 
  - static 키워드가 붙은 변수이다. 
  - 보통 클래스의 인스턴스마다 독립적인 저장 공간을 가지는 인스턴스 변수와는 달리, 클래스 변수는 모든 인스턴스가 공유하는 공통된 저장공간을 공유하게 된다.
- 인스턴스 변수
  - 클래스의 인스턴스를 생성할 때, 독립적인 저장 공간을 생성한다.
- 메서드 내에서 선언되며, 메서드가 종료되면 소멸되어 사용할 수 없게 된다.

### JVM 메모리 구조

![JVM 메모리 구조](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FKv8yC%2FbtqCzAk4Q6Q%2FOr5pifZLKgkQTMyi9klMmK%2Fimg.jpg
)
- 메서드 영역(method area)
   - 프로그램 실행 중 어떤 클래스가 사용되면, JVM은 해당 클래스의 클래스파일(*.class)을 읽어서 분석하여 클래스에 대한 정보(클래스 데이터)를 이곳에 저장. 이 때, 그 클래스의 클래스변수(class variable)도 이 영역에 함께 생성됨

- 힙(heap)
   - 인스턴스가 생성되는 공간. 프로그램 실행 중 생성되는 인스턴스는 모두 이곳에 생성됨. 인스턴스변수(instance variable)들이 생성되는 공간.

- 콜 스택(call stack)
   - 메서드가 호출되면 수행에 필요한 만큼의 메모리를 스택에 할당받음
   - 메서드가 수행을 마치고나면 사용했던 메모리를 반환하고 스택에서 제거됨
   - 호출스택의 제일 취에 있는 메서드가 현재 실행 중인 메서드임
   - 아래에 있는 메서드가 바로 위의 메서드를 호출한 메서드(호출된 메서드가 수행 종료될 때까지 대기상태)


### 가변 인자(varargs)

JDK 1.5 부터 동적으로 파라미터를 넘길 수 있는 기능을 지원하게 되었다.

문법은 다음과 같다.

`method(tpye... parameter)`

내부적으로 배열을 생성하는 원리인데, 메서드를 호출할 때마다 배열이 새로 생성되어진다. 그렇기 때문에 편리하더라도 비효율적인 측면이 숨어있기 때문에 필요한 경우에만 사용하는 것이 좋다. 

**주의할 점은 가변 인자 외에 매개변수가 더 있다면, 가변 인자를 매개변수 중에서 제일 마지막에 선언해줘야 한다.**

```java
int sumCalculator(int... numbers) {
    return Arrays.stream(numbers).sum();    
}
```

---

## CH 07. 객체지향 프로그래밍 2

### abstract와 interface 본질적인 차이

우리가 흔히 알고 있는 abstract와 interface의 문법적 차이 (다중 상속 여부, 필드 여부, 구현된 메소드 포함 여부) 외에 정말 프로그래밍 측면에서의 차이를 알기 위해서 검색해봤다.

[Stackoverflow](https://stackoverflow.com/questions/1913098/what-is-the-difference-between-an-interface-and-abstract-class) 의 답변 중 하나이다.

다음과 같은 경우 추상 클래스 사용을 고려해라

- 밀접하게 관련된 여러 클래스 간에 코드를 공유하려고 한다.
- 추상 클래스를 확장하는 클래스에 많은 공통 메서드 또는 필드가 있거나 public 이외의 접근 제어자가 필요할 때(예: protected 및 private).
- `non-static` 또는 `non-final field`를 선언하려고 한다.

다음과 같은 경우 인터페이스 사용을 고려해라

- 관련 없는 클래스가 인터페이스를 구현할 것으로 예상된다. 예를 들어, 관련 없는 많은 개체가 `Serializable` 인터페이스를 구현할 수 있다.
- 특정 데이터 유형의 동작을 지정하려고 하지만, 누가 해당 동작을 구현하는지는 중요하지 않다.
- 유형의 다중 상속을 활용하려고 합니다.

즉, 추상 클래스는 확장하는 클래스와 "is-a" 관계를 설정한다. 인터페이스는 클래스에 대해 "has-a" 관계를 설정한다.

java8부터 default 키워드로 `interface`에서도 메서드를 구현할 수 있게 되었고, `abstract`와 `interface`와의 명확한 구분이 점점 흐릿해져가고 있는 것 같다.    
하지만 각자의 키워드가 독립적으로 존재하는 것은 자체적으로 가지는 본질적인 차이가 있기 때문이라고 생각한다.

---

## CH 08. 예외 처리

### 예외 클래스의 계층 구조

![예외 클래스 계층 구조](https://www.nextree.co.kr/content/images/2021/01/Exception-Class.png)

모든 예외 클래스는 `Throwable` 객체를 상속을 받고 있고, 당연하게도 `Throwable`은 최상위 객체인 `Object` 객체를 상속받고 있는 구조다.

`Throwable`을 상속받고 있는 객체는 `Error`와 `Exception`이라는 두 객체로 나눌 수 있는데, `Erorr`는 시스템 레벨의 심각한 수준의 에러이기 때문에 시스템에 변화를 주어 문제를 해결하는 것이 일반적이다. (즉, 애플리케이션 단에서 Error를 처리할 방도가 없다.)
반면에, `Exception`은 개발자가 로직을 추가하여 핸들링할 수 있다.  

우리는 여기서 `Exception`에 대해 내용을 다뤄본다.

### Checked Exception과 Unchecked Exception
|      특징       |               Checked Exception               |        Unchecked Exception         |
|:-------------:|:---------------------------------------------:|:----------------------------------:|
|     처리 여부     |                   반드시 예외 처리                   |           예외 처리하지 않아도 됨            |
|  Rollback 여부  |                       X                       |                 O                  |
|     대표 예외     |  Exception 클래스 하위 RuntimeException 외의 모든 클래스  | RuntimeException을 포함한 하위 모든 자식 클래스 |


- Checked Exception
  반드시 명시적으로 예외를 처리해야 하기 때문에 `Checked Exception`이라고 하며, try/catch를 해서 에러를 잡든 throws를 통해서 호출한 메서드로 예외를 던져야 한다.

- Unchecked Exception
  명시적으로 예외 처리를 강제하지 않는 특징이 있기 때문에 `Unchecked Exception`이라 하며, try/catch로 잡거나 throw로 호출한 메서드로 예외를 던지지 않아도 상관없다.

### 예외 처리 전략

1. 예외 복구
   - 예외가 발생하면 다른 작업 흐름으로 유도하는 기법
   - 핵심은 예외가 발생하여도 애플리케이션은 정상적인 흐름으로 동작하는 것
2. 예외 처리 회피
   - 예외가 발생하면 처리하지 않고 호출한 쪽으로 예외를 던지는 기법
   - 무책임하게 던지는 것은 위험하다. 호출한 쪽에서 예외를 처리하거나 해당 메서드에서 예외를 던지는 것이 최선일 때만 사용
3. 예외 전환
   - 예외가 발생하면 새로운 다른 예외를 던지는 기법
   - 예외를 처리할 때 더 명확하게 예외의 종류를 인지할 수 있게 도와줌

> 현실은 우리는 예외가 발생했을 때 예외를 복구할 수 있는 방법은 많지 않다.

결국 예외 복구 전략이 명확하고 그것이 가능하다면 `Checked exception`을 try/catch로 잡고 해당 복구를 하는 것이 좋지만, 그렇지 않은 경우 `exception`을 발생시킬 때 명확하게 어떤 예외가 발생했는지 정보를 전달해주는 것이 최선이다.

