package com.myshop.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정 없이 이 어노테이션을 사용할 경우 H2 데이터베이스를 자동으로 실행해줌
@EnableJpaAuditing
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void readPost() {
        String title = "테시트 게시글 제목";
        String content = "테스트 게시글 본문";

        // posts 테이블에 insert/update 쿼리를 실행해준다.
        // id가 없다면 insert, 있다면 update 쿼리가 실행된다.
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("저자")
                .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntityTest() {

        //given
        LocalDateTime now = LocalDateTime.of(2023,3,19,0,0,0);
        postsRepository.save(Posts.builder()
                .title("생성일 제목")
                .content("생성일 내용")
                .author("테스트~")
                .build()
        );

        //when
        List<Posts> postsLists = postsRepository.findAll();

        //then
        Posts posts = postsLists.get(0);

        System.out.println(">>>> createdDate=" + posts.getCreatedDate()+", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
