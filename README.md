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

---

## CH 09. java.lang package

### Object 클래스

- equals()
객체의 참조변수를 받아서 비교하여 그 결과를 반환하는 역할

```java
public boolean equals(Object obj) {
    return (this == obj);
}
```

위의 코드를 보면 알 수 있듯이 두 객체의 같고 다름을 참조변수의 값으로 판단한다.

> 객체를 생성할 때 메모리의 비어있는 공간을 찾아 생성하기 때문에 서로 다른 두 개의 객체가 같은 주소를 갖는 일은 있을 수 없다.
> 두 개 이상의 참조변수가 같은 주소값을 갖는 경우는 가능하다.

그래서 보통 흔히 객체 비교시 각 필드 값을 비교하도록 equals 메서드를 오버라이딩한다.

- hashCode()

해싱 기법에 사용되는 해시함수의 역할을 한다.
해싱은 데이터 관리 기법 중에 하나인데, 다량의 데이터를 저장하고 검색하는 데 유용하다.

해시 함수는 찾고자하는 값을 입력하면, 그 값이 저장된 위치를 알려주는 해시코드를 반환한다.

hashCode는 객체의 주소값으로 해시코드를 만들어 반환하기 때문에 절대 같은 해시 코드를 가질 수 없지만, 64bit JVM에서는 32bit 해시 코드를 만들기 때문에
중복될 수 있다. 그렇기 때문에 클래스의 인스턴수 변수 값으로 객체의 같고 다름을 판단하는 경우 equals와 hashCode를 적절히 함께 사용해야한다.

위의 내용을 Collection 챕터에서 보다 자세히 다룰 예정이다.

### String 클래스
- immutable 클래스
- 리터럴로 중복으로 문자열을 만든 경우 String pool에서 공유가 되서 같은 참조를 가지게 된다.

```java
String a = "a"; // "a" 인스턴스 생성
String b = "b"; // "b" 인스턴스 생성
String c = "a"; // 변수 a와 같은 참조값을 가지고 있음        
a = a + b;  // 변수 a의 인스턴스가 변경되는 것이 아닌 새로운 "ab" String 인스턴스가 생성
```

즉, 문자열간의 결합과 추출 등 복잡한 연산이 필요한 경우 mutable한 `StringBuffer`를 사용하는 것이 좋다.

### StringBuffer와 StringBuilder 클래스
String 클래스와는 다르게 StringBuffer와 StringBuilder는 지정된 문자열을 변경할 수 있다.

StringBuffer와 StringBuilder의 차이는 멀티 스레드 환경에서의 동기화 그리고 toString 메서드를 위한 캐시 여부의 차이가 있고 다른 차이는 크게 없으므로 StringBuffer를 중심으로 설명한다.

StringBuffer는 내부적으로 문자열을 편집하기 위한 버퍼를 가지고 있다. 인스턴스를 생성할 때 그 크기를 지정할 수 있다.

편집할 문자열이 버퍼보다 크면 버퍼의 크기를 늘리는 작업이 있기 때문에 넉넉히 잡아주는 것이 효율적이다.
(버퍼의 크기보다 크면 내부적으로 새로운 char 배열을 만들고 System.copyarray로 복사하는 과정이 추가 된다.)

주의할 점은 StringBuffer와 StringBuilder 클래스의 equals()는 오버라이딩되어 있기 때문에 등가비교연산자(==)와 같은 결과를 얻는다.
그렇기 때문에 toString() 메서드로 String으로 타입 변환 후에 equals를 수행해야한다.
```java
StringBuffer sb = new StringBuffer("abc");
StringBuffer sb2 = new StringBuffer("abc");

System.out.println(sb == sb2); // false
System.out.println(sb.equals(sb2)); // false

```
---

## CH 11. Collections Framework

### 정의
데이터 군을 다루고 표현하기 위한 단일화된 아키텍처

### 핵심 인터페이스
Collections Framework에서는 컬렉션 데이터 그룹을 3가지의 인터페이스로 정의했다.

그 인터페이스는 `List`, `Set`, `Map` 으로 구분되며, `List`와 `Set` 컬렉션 클래스는 많은 공통 부분이 존재하기 때문에 공통된 부분을 추출하여 다시 `Collection` 인터페이스를 정의했다.

### 핵심 인터페이스에 대한 기능
- List : 순서가 있는 데이터의 집합으로 데이터 중복을 허용한다.
  - 구현 클래스 예시 : ArrayList, LinkedList, Stack, Vector 등
- Set : 순서가 없는 데이터의 집합으로 데이터 중복을 허용하지 않는다.
  - 구현 클래스 예시 : HashSet, TreeSet, LinkedHashSet
- Map : key, value 쌍으로 이루어진 순서없는 데이터 집합으로, key 중복을 허용하지 않는다.
  - 구현 클래스 예시 : HashMap, TreeMap, Hashtable, Properties 등

주의할 점은 Vector, Stack, Hashtable, Properties는 컬렉션 프레임웍이 만들어지기 전부터 존재하던 클래스였기 때문에 컬렉션 프레임웍의 명명법을 따르지 않는다.
위와 같은 컬렉션 클래스들은 호환을 위해 설계를 변경해서 남겨두었으나 사용하지 않는게 좋다.

### 핵심 인터페이스별 상속도
![상속도](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FCiJc0%2Fbtq8wmvLAc0%2FSazMn8zAYuSFoQsY1N3CH0%2Fimg.jpg)

### List

#### ArrayList와 LinkedList

> Vector 클래스는 과거 작성된 소스와 호환을 위해서 남겨진 콜렉션 클래스이므로, ArrayList를 사용하도록 한다.

ArrayList는 데이터가 순차적으로 저장되는 자료구조인데, 배열 구조와 같기 때문에 메모리에 담기는 주소 또한 순차적이다.

주의할 점은 클래스의 크기를 지정해서 초기화할 때, 사용할 데이터의 개수보다 더 넉넉하게 지정하는 것이 좋다. 그 이유는 용량이 변경되야할 때는 새로운 배열을 생성한 후 기존의 배열 값들을 복사해야하는 과정이 필요해 속도가 느려질 수 있다.

LinkedList도 ArrayList와 마찬가지로 데이터가 순차적으로 저장되는 자료구조인데, 다른 점은 연결 리스트 구조로 되어있어 값이 담겨있는 주소가 순차적이지 않다. 

알아둘 점은 이름이 LinkedList지만, 실제 내부 구조는 Doubly Linked List로 되어 있다. 기존 링크드 리스트의 단방향 검색이라는 낮은 접근성을 개선하기 위해 이러한 설계를 가져갔다.

#### 속도 비교
- 데이터 접근 시간: ArrayList > LinkedList
- 순차적으로 데이터를 추가/삭제하는 경우: ArrayList > LinkedList
- 중간 데이터를 추가/삭제하는 경우: ArrayList < LinkedList

### Iterator, ListIterator, Enumeration
위 인터페이스들은 모두 컬렉션에 저장된 요소를 접근하는데 사용되는 인터페이스이다.

Enumeration은 Iterator의 구버전이며, ListIterator는 Iterator의 기능을 향상 시킨 것이다.

Enumeration은 Iterator와 이름만 다를뿐 기능은 같고, ListIterator는 Iterator의 기능에서 이전 방향으로의 접근 기능을 추가한 것이다.


# 2권의 내용부터는 블로그에 정리하고, 실습 코드만 작성한다.