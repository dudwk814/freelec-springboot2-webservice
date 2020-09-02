package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity //테이블과 링크될 클래스임을 나타냄 카멜케이스 이름을 언더스코어 네이밍으로 매칭 ex) SalesManager -> sales_manager table
public class Posts extends BaseTimeEntity {

    //@Id 기본키 필드
    @Id
    //@GeneratedValue 기본키 생성 규칙을 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column 컴럼을 나타내며 굳이 선언하지 않아도 필드는 모두 컬럼이 된다. length = 최대길이, nullable = null 허용 여부
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}