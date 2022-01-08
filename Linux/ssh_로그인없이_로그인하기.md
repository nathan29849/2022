# ssh key를 이용하여 로그인하기
- ssh key를 이용하여 로그인 없이 로그인이 가능하다.
- 이 방법이 패스워드를 입력하여 로그인 하는 방법보다 보안상으로 덜 안전할 것 같지만, 실상은 훨씬 더 안전하다.

## 실행 환경
- Intel Mac
- VirtualBox
- Ubuntu 20.04.3

## 공개키, 비공개키 만들기
```
$ ssh-keygen
```

- 우선 로컬 클라이언트 계정에서 위 명령어를 통해 key pair를 생성한다.
![](https://images.velog.io/images/nathan29849/post/7532ff75-6938-4bb1-b1c8-db17861de989/image.png)


- ssh라는 디렉토리로 들어가보자.
![](https://images.velog.io/images/nathan29849/post/64602726-b7ae-40d6-a354-fe75ee338d50/image.png)

- `id_rsa`는 private key이다. 소유자만 읽고 쓸 수 있다.(외부인은 읽을 수 없음)
-`id_rsa.pub`는 public key이다. 소유자 뿐만아니라 외부인도 읽을 수 있다.
- 현재 `.ssh` 디렉토리의 권한은 `drwx------`이다. 따라서 소유자만 실행하고, 읽고 쓰기가 가능하다. 

### 여기서 잠깐!
- ssh key는 rsa, dsa 방식등 다양한 방식이 존재한다.(키 알고리즘에 따라 다름)
- 가장 사용이 많이 되는건 rsa와 dsa 방식이므로 이에 대해 간략히 정리해보자.

> - rsa 방식 : rsa는 암호화 속도가 빠르고, 복호화 속도가 느리다.
> - dsa 방식 : dsa는 암호화 속도는 느리지만, 복호화 속도가 빠르다.

## 리모트(Remote)에 공개키 복사하기
- 로컬(local) = 개인 컴퓨터(현재 key pair가 저장된 공간)
- 리모트(remote) = 서버(공개키를 복사하여 저장할 공간)

- 서버(user)의 `~/.ssh` 경로에 authorized_keys로 저장된다.
```
$ ssh-copy-id [username]@[ip] -p 2222
```


- 이후 다음 명령어를 통해 ssh에 password 없이 로그인 됨을 확인할 수 있다.
```
$ ssh [username]@[ip] -p 2222
```

![](https://images.velog.io/images/nathan29849/post/2ac81042-bcab-41b7-8078-8e0841552934/image.png)

- 만약 이 과정에서 Connection refused 또는 Operation timed out 오류가 뜬다면 포트 포워딩을 다시 확인해봐야한다. (ubuntu : `ifconfig`, mac : `ipconfig getifaddr en0` 을 통해 ip 확인 가능)
- 나는 재부팅하고 다시 똑같은 ip로 돌렸는데, 아마 재부팅을 하면 연결상태도 다시 잡히면서 local의 en0 ip 주소가 달라지는 것 같다.
- 만약 포트 포워딩이 안된다면 `가상머신 설정 > 네트워크 > 어댑터에 브릿지 연결`을 진행해줘야 한다.
   - 자세한건 이 링크를 타고 들어가서 읽어보자.
   - https://hahaite.tistory.com/283