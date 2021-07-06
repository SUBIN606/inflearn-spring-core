package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 내부에 static final로 가지고 있음
    private static final SingletonService instance = new SingletonService();

    // 인스턴스 객체를 꺼낼 수 있는 유일한 방법
    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자 => 외부에서 생성할 수 없도록 함
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
