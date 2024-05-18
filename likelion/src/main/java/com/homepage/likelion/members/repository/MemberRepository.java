package com.homepage.likelion.members.repository;


import com.homepage.likelion.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId (String userId);
}
