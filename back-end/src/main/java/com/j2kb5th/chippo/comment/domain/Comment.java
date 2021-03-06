package com.j2kb5th.chippo.comment.domain;

import com.j2kb5th.chippo.global.domain.BaseTimeEntity;
import com.j2kb5th.chippo.interview.domain.Interview;
import com.j2kb5th.chippo.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id", nullable = false)
    private Interview interview;

    @Builder
    public Comment(Long id, User user, String content, Interview interview) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.interview = interview;
    }
}
