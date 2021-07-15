package hello.core.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url);
        connect();                                      // 생성자에서 connect를 호출하므로 아직 url은 null이다.
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {    // url을 초기화
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("NetworkClient.disconnect close url = " + url);
    }

}
