# !/bin/bash와  !/bin/sh의 차이점

- 쉘 스크립트를 작성할 때 종종 `#!/bin/bash`와`#!/bin/sh`가 사용된다.

## sh란?
- sh는 bone shell이라고도 하며 가장 기본적인 쉘이다.
- 쉘이란 커널과 사용자를 연결해주는 하나의 매개체 역할을 한다.
- 쉘은 여러가지 형태로 만들어지지만 크게 csh 계열과 ksh 계열로 나뉜다.

> `csh` :  c 언어를 기초로 **관리자 중심**으로 만들어진 쉘이고
> `ksh` : korn shell이라고 불리며 **사용자 중심**으로 만들어진 쉘이다.

- `csh`는 후에 tcsh으로 확장된다.


## bash란?

- 리눅스는 bash라는 쉘을 사용하며 이 의미는 born again shell의 의미를 가지고 있다.
- bash는 csh의 관리적인 측면과 ksh의 사용자 편의성 측면을 모두 고려하여 만들어진 쉘이다.
- 리눅스는 대부분의 쉘을 호환하여 사용할 수 있다.
- 따라서 크게 sh가 있고, 그 안에 bash가 속한다고 볼 수 있다.

## 결론
- `/bin/sh`, `/bin/bash`는 쉘의 절대 경로 이다
- bash를 사용하면 프로그래밍을 현대적인 다른 프로그래밍 언어와 비슷하게 할 수 있다.
- 여기에는 지정된 지역 변수 및 배열과 같은 것이 포함된다.
- sh는 최소한의 프로그래밍 언어라고 보면 되겠다.


---
## Reference
[자기계발 - /bin/sh의 의미](https://m-youngzzang-for20.tistory.com/108)
[bash - sh와 bash의 차이점](https://pythonq.com/so/bash/761#:~:text=sh%20%EB%8A%94%20%EA%B5%AC%ED%98%84%EC%9D%B4%20%EC%95%84%EB%8B%8C,%EB%8C%80%ED%95%9C%20(%EB%98%90%EB%8A%94%20%ED%95%98%EB%93%9C%20%EB%A7%81%ED%81%AC).&text=bash%20%EB%8A%94%20%2D%2Dposix%20%EC%8A%A4%EC%9C%84%EC%B9%98,%EB%A5%BC%20%EB%AA%A8%EB%B0%A9%ED%95%98%EB%A0%A4%EA%B3%A0%20%EC%8B%9C%EB%8F%84%ED%95%A9%EB%8B%88%EB%8B%A4.)