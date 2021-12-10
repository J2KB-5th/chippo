package com.j2kb5th.chippo.tag.repository;

import com.j2kb5th.chippo.tag.domain.Tag;
import com.j2kb5th.chippo.tag.domain.TagType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameAndTag(String tagName, TagType tagType);
}
