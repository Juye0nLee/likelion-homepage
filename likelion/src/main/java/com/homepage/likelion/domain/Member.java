package com.homepage.likelion.domain;


import com.homepage.likelion.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(
        name = "ID", //식별자 생성기 이름
        sequenceName = "ID_SEQ", //DB에 등록되어 있는 Sequence 이름
        initialValue = 1, //처음 시작 value 설정
        allocationSize = 1 //Sequence 한번 호출 시 증가하는 수
        //allocationSize가 기본값이 50이므로 1로 설정하지 않을 시, sequence 호출 시 마다 50씩 증가 -> 내가 마주한 이슈
)
@Table(name="MEMBERS")
@Builder
public class Member extends BaseEntity
{
    @Id
    //기본키 생성
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ID"
    )
    @Column(name="ID")
    private Long id;

    @Column(name="USER_ID")
    private String userId;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PHONE")
    private String phone;

}
