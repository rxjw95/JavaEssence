# 자바의 정석 3판 다시 보기

## Ch 05. array

- 배열을 복사할 때 for문 보다 System.arraycopy()를 이용하자.
  - native code를 호출해서 더 빠르기 때문이다.

`System.arraycopy(srcArray, srcArrayStartIndex, desArray, desArrayStartIndex, CountOfSrcArray)`

```java
int[] a = new int[] {1, 2, 3};
int[] b = new int[5];

System.arraycopy(a, 0 , b, 0, a.length);

System.out.println(Arrays.toString(a)); // result: [1, 2, 3]
System.out.println(Arrays.toString(b)); // result: [1, 2, 3, 0, 0]
```

> array.clone() 과의 차이는 무엇일까?

System.arraycopy는 JNI(Java Native Interface)를 사용해서 배열의 깊은 복사를 수행할 수 있다. 또한, 부분적으로 복사할 수도 있다.
array.clone은 복사할 배열과 동일한 크기와 유형으로 `new` 형식으로 새로운 배열을 내부적으로 생성한다.




