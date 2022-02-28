package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.*;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);  // Optional을 통해 null로 반환되는 경우를 대비함
    Optional<Member> findByName(String name);

    List<Member> findAll();
}
