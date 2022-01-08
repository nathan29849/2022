# Ubuntu와 VirtualBox 설치하기
- 모든 과정은 Intel Mac으로 진행됩니다.

> 1. Ubuntu 20.04.3을 설치한다.
> 2. Ubuntu를 돌리기 위한 가상머신인 VirtualBox를 설치한다.


## 1. Ubuntu 20.04.3 (Desktop ver) 설치하기
- Ubuntu 설치가 생각보다 오래걸리기 때문에 먼저 설치를 한 뒤 Virtual Box를 설치해보자.

- google에 ubuntu 검색 후 `Download Ubuntu Desktop` 링크 눌러 들어가기
  ![image](https://user-images.githubusercontent.com/67811880/148332894-8cbef44d-cb7c-468c-8ce5-1a90b5a78790.png)


- 초록색 Download 버튼 클릭(생각보다 오래걸릴 수 있으니 VirtualBox를 설치하러 가보자..)
  ![image](https://user-images.githubusercontent.com/67811880/148333076-19eb68be-de24-4067-8b49-61f48db8cd85.png)



## 2. 가상머신 VirtualBox 설치하기
- [VirtualBox 다운로드 페이지 바로가기](https://www.virtualbox.org/wiki/Downloads)
- 위 링크를 눌러 VirtualBox 다운로드를 진행한다. (`OS X hosts` 링크 클릭!)
- 다운로드가 완료되어 dmg 파일이 생기면 더블클릭! (그럼 아래와 같은 모습이 나타난다.)
- 여기서 VirtualBox.pkg를 더블클릭하여 실행시킨다.
  ![스크린샷 2022-01-06 오전 11 16 14](https://user-images.githubusercontent.com/67811880/148333220-91a3a599-58c4-4789-8000-f2f010dcee25.png)

- 만약 설치에 실패했다고 뜬다면, `환경설정 -> 보안 및 개인정보 보호 -> 일반`에서 차단된 Oracle 시스템 소프트웨어를 허용해야 한다.
  ![image](https://user-images.githubusercontent.com/67811880/148333469-51df8c9e-2016-43b8-93c3-0a45e5138b74.png)

- 설치가 정상적으로 완료가 되면 아래의 귀여운 TUX가 도구 박스를 바라보는 그림이 나온다!
  ![image](https://user-images.githubusercontent.com/67811880/148333596-6446d652-3b7f-4a36-9641-c4f472cc28fb.png)


## 3. VirtualBox에 Ubuntu 세팅하기
- 여기까지 왔다면 local에 ubuntu와 VirtualBox가 정상적으로 설치되어 있을 것이다.
- 이제, VirtualBox에 ubuntu를 세팅해보자.
- VirtualBox가 켜진 상태에서 `새로 만들기(N)`를 클릭한다.
- 이름을 입력하고 버전은 `Ubuntu (64-bit)`로 정한뒤 확인을 누른다.
  ![image](https://user-images.githubusercontent.com/67811880/148334114-20d7da0d-bc34-4c00-9876-91fb22589376.png)


- 나머지 세팅 정보는 기본에 맞추어 진행한다.(확인을 눌러 진행 ㄱㄱ! - 잘모르겠다면 아래 이미지를 보고 선택하자.)
- 그러면 아래와 같이 가상머신이 생긴 것을 확인할 수 있다.
  ![image](https://user-images.githubusercontent.com/67811880/148334390-56376ed5-3765-4485-93db-3b6517a6a4bf.png)

- 위의 창에서 `설정(S)`을 눌러 아까 다운 받은 ubuntu iso 파일을 `컨트롤러:IDE`로 지정해준다.
  ![image](https://user-images.githubusercontent.com/67811880/148334618-589b511b-6326-4724-861e-e425fe8eda01.png)

- 그리고 확인을 누른 뒤, 시작버튼을 누르면!!!! 아래와 같이 설치화면이 뜬다!!
- 한국어를 누르고 `Ubuntu 설치` 버튼을 누르자
  ![image](https://user-images.githubusercontent.com/67811880/148334852-1fd2d00f-199d-4ebe-8840-8df92c5a282b.png)

- 그런데 창이 너무 커서 다음으로 넘기는 버튼이 안보인다 ㅠㅠ(캡쳐를 못했습니다 ..)

- 1) 다시 VirtualBox 창으로 돌아가서 `설정->디스플레이->그래픽 컨트롤러`에서 `SVGA`로 되어있는 것을 `VBoxSVGA`로 바꿔보자.

- 2) `Devices->Insert Guest Additions CD image`를 눌러 OK를 누르면 프로그램이 설치되어 창의 크기 변경할 때 자동으로 창 변경이 된다.
- 이렇게 하고 다시 VirtualBox를 실행시킨 뒤 가상환경을 시작하면, 창에 맞게 화면이 변하고, 다음 버튼도 잘 보인다!!
  ![image](https://user-images.githubusercontent.com/67811880/148335200-de949d84-3e5e-4634-bcfc-b581d5999b52.png)

- ![image](https://user-images.githubusercontent.com/67811880/148335518-78bab6f3-7821-49d8-9f10-a46677bc3906.png)

- 그런데 터미널에서 한글이 안쳐진다 ㅠㅠ 왼쪽 아래 점 9개가 있는 버튼을 클릭하여 `설정`을 검색하고 왼쪽 배너에서 `지역 및 언어`를 눌러보자.
  ![image](https://user-images.githubusercontent.com/67811880/148335667-4ec6e43e-b6c9-4959-9b08-dd0c345a13ee.png)

- 아래와 같이 `한국어 (Hangul)`말고 위에 다른 한국어가 있다면 제거하자. 그리고 `한국어 (Hangul)`의 설정을 클릭해보면 `Shift + Space`를 통해 한영 전환이 이루어짐을 알 수 있다.
  ![image](https://user-images.githubusercontent.com/67811880/148335741-43a8b9ed-5911-45c2-8fc2-3c0a195c06da.png)

---

## References
- [맥(macOS)에 VirtualBox 설치하고 Ubuntu 세팅하기](https://popcorn16.tistory.com/85)
- [[Linux] 우분투(Ubuntu) VirtualBox 벌쳐박스 한글 입력하기 한/영 전환하기](https://xd-jaewon.tistory.com/98)
- [맥에 가상환경 (Virtual Box) 설치 & 우분투 서버 접속](https://velog.io/@kyukim/1-akt9nh21)






