## 📚 List 와 Optional (2024.05.11)
- ### List
  - 배열과 같이 객체를 일렬로 늘어놓은 구조를 가지고 있고, 객체를 **인덱스**로 관리하기 때문에 객체를 저장하면 자동으로 인덱스가 부여되고, 인덱스로 객체를 검색, 추가, 삭제할 수 있는 기능 제공
  - List 인터페이스를 구현한 클래스 : ArratList, Vector, LinkedList, Stack 등이 있다.
  - 가장 많이 사용 되는 건 ArrayList, LinkedList
  - 특징
    - 순서가 있고 중복을 허용
    - 인덱스로 관리하기 때문에 인덱스로 접근 가능
    - 크기가 가변적
 - Java의 NPE 예방
     - 자바 프로그램 코드를 작성하면 null 값에 대해 고려해야 하는 경우가 많음
     - null 값을 제대로 처리하지 않으면 NPE(Null Pointer Exception)을 만남
     - NPE가 발생하지 않도록 중간중간 null 체크를 해줘야 하는데, 이 과정에서 코드가 많이 복잡하고 더럽혀짐.
     - 예시
       ```
       List<String> items = getItems();
       System.out.println(items.size());
       ```
       만약 getItems()메소드가 null 값을 리턴하면 items.size()코드에서 NEP가 발생함
       따라서
       ```
       List<String> items = getItems();
       if(items != null){
         System.out.println(items.sizw());
       }
       ```
       이렇게 조건문을 이용하여 null일 때를 검사해줘야 하지만
       매번 이렇게 null을 검색하는 것이 코드를 지저분하게 만든다.

- ## Optional<T>
  - Java8 부터는 이런 null 값에 대한 처리를 깔끔하기 하기 위해 Optional 클래스가 추가됨
  - null 값일 수도 있는 변수를 감싸주는 '래퍼' 클래스.
  - 제너릭으로 값의 타입을 지정해줘야함
  - Optional 클래스는 여러가지 메소드를 통해 value값에 접근하여 NEP가 발생하지 않고, null일 수도 있는 값을 다루기 위한 다양한 메소드들을 저장한다.
  - 사용법
    -  생성
      - ``` Optional<String> optional = Optional.of(value) ```
        이 경우에는 value 변수의 값이 null인 경우에는 NEP 예외가 발생하기 때문에, of()메소드를 사용한다.
      - ``` Optinal<String> optional = Optional.ofNullable(value);```
        이 경우에는 value 변수의 값이 null일 수 있다. value 변수가 null인 경우 Optinal.empty()가 리턴된다.
      - ```Optional<String> optional = Optional.empty(); ```
        이 경우에는 빈 Optinal 객체를 생성한다.
    - 중간 처리
      - filter()
        - filter 메소드의 인자로 받은 람다식이 참이면, Optinal 객체를 그대로 통과시키고, 거짓이면 Optional.empty()를 반환해서 추가로 처리가 안되도록 한다.
        - ``` Optional.of("ABCD").filter(v->v.startWith("AB")).orElse("Not AB"); //ABCD 반환 ```
        - ``` Optional.of("XYZ").filter(v -> v.startWith("AB")).orElse("Not AB"); //Not AB 반환 ```
      - map()
        - Optinal 객체의 값에 어떤 수정을 가해서 다른 값으로 변경하는 메소드
          ``` Optional.of("XYZ").map(String::toLowerCase).orElse("Not AB");```
    - 값을 리턴하는 메소드
        - isPresent()
            - isPresent() 메소드는 Optional 객체의 값이 null인지 여부를 판단
        - ifPresent()
          - 람다식을 인자로 받아서, 값이 존재할 때 그 값에 람다식을 적용해줌
        - get()
          - Optional 객체가 가지고 있는 value 값을 꺼내온다.
          - 만약 Optional 객체에 값이 없다면 NoSuchElementException이 발생함
        - orElse()
          - 중간처리 메소드들을 거치면서 혹은 원래 Optional 객체가 비어있었다면, orElse() 메소드에 지정된 값이 기본적으로 리턴됨
        - orElseGet()
          - 중간처리 메소드들을 거치면서 혹은 원래 Optional 객체가 비어있었다면, orElseGet() 메소드들의 인자로 입력되어 있는 Supplier 함수를 적용하여 객체를 얻어
        

