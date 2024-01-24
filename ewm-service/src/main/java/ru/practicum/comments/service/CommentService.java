package ru.practicum.comments.service;

import ru.practicum.comments.model.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto addComment(Integer userId, Integer eventId, CommentDto commentDto);

    CommentDto editComment(Integer userId, Integer commentId, CommentDto commentDto);

    void removeComment(Integer userId, Integer commentId);

    List<CommentDto> getAllCommentByEvent(Integer eventId);
}
