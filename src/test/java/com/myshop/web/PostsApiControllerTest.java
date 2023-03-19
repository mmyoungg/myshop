package com.myshop.web;

import com.myshop.domain.posts.Posts;
import com.myshop.domain.posts.PostsRepository;
import com.myshop.web.dto.PostsSaveRequestDto;
import com.myshop.web.dto.PostsUpdateRequestDto;
import org.apache.coyote.Response;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
// SpringRunner를 사용하여 JUnit을 실행하도록 지정
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 랜덤포트를 사용하도록 지정
public class PostsApiControllerTest {

    // 테스트에서 사용할 로컬서버의 포트번호를 가져오는 어노테이션
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    public void PostsSaveTest() {

        String title = "title";
        String content = "content";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("저자")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // TestRestTemplate 객체를  사용하여 HTTP POST 요청을 보냄
        // TestRestTemplate은 스프링에서 제공하는 RestTemplate 클래스의 확장판이다.
        //  -> 테스트 환경에서 보다 간편하게 테스트를 수행할 수 있도록 도와줌
        // .postForEntity 메소드 : HTTP POST 요청을 보내고 ResponseEntity 객체를 반환하는 메소드
        //  -> 파라미터로 url(요청을 보낼 주소), requestDto(요청에 전달할 데이터를 담은 객체), Long.class(요청에 대한 응답을 Long타입으로 변환하는 클래스 타입)
        // ResponseEntity 클래스는 HTTP 응답(Response)을 나타내는 객체
        //  -> 반환된 HTTP 상태코드, 응답 본문 등을 포함한다.
        //  -> 이 코드에서는 응답 본문으로 Long 타입의 게시글 id를 반환하고 있기 때문에, Response<Long> 객체를 사용하여 해당 값을 검증할 수 있다.
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void postsUpdate() throws Exception {
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("제모오옥")
                .content("내요오옹")
                .author("저자아아")
                .build()
        );

        Long updateId = savedPosts.getId();
        String expectedTitle = "수정제목";
        String expectedContent = "수정내용";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/"+updateId;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);



    }
}
