package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//별다른 설정없이 @SpringBootTest를 사용할 경우 H2 데이터베이스를 자동으로 실행한다.
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    //junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정한다.
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void inser_selete() {

        //given
        String title = "테스트 게시글";
        String content = "테스트 내용";

        //postsRepository.save() : 테이블 posts에 insert/update 쿼리를 실행
        //id 값이 있다면 update가, 없다면 insert 쿼리가 실행 됨
        postsRepository.save(Posts.builder()
                            .title(title)
                            .content(content)
                            .author("kj99658103@gmail.com")
                            .build());

        //when
        //postsRepository.findAll() : 테이블 posts에 있는 모든 데이터를 조회해오는 메소드
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2020, 9, 1, 0, 0, 0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}