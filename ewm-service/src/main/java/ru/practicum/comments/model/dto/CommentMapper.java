package ru.practicum.comments.model.dto;

import ru.practicum.comments.model.Comment;
import ru.practicum.event.model.Event;
import ru.practicum.event.model.dto.EventMapper;
import ru.practicum.user.model.User;
import ru.practicum.user.model.dto.UserMapper;

import java.time.LocalDateTime;

public class CommentMapper {
    public static Comment coommentDtoToComment(CommentDto commentDtoIn, Event event, User user) {
        return Comment.builder()
                .comment(commentDtoIn.getComment())
                .event(event)
                .commentator(user)
                .created(LocalDateTime.now())
                .build();
    }

    public static CommentDto commentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .event(EventMapper.eventToVeryShortDto(comment.getEvent()))
                .comment(comment.getComment())
                .commentator(UserMapper.userToShortDto(comment.getCommentator()))
                .created(comment.getCreated())
                .build();

    }

    public static Comment comentDtoUpdateComment(Comment comment, CommentDto commentDto) {
        return Comment.builder()
                .commentId(comment.getCommentId())
                .created(comment.getCreated())
                .commentator(comment.getCommentator())
                .event(comment.getEvent())
                .comment(commentDto.getComment())
                .build();
    }
}