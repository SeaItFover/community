package life.heart.community.mapper;

import life.heart.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}