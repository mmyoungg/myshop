package com.myshop.service.posts;

import com.myshop.domain.posts.PostsRepository;
import com.myshop.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();

    }
}
