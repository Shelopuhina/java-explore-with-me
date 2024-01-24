package ru.practicum.comments.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.comments.model.Comment;
import ru.practicum.comments.model.dto.CommentDto;
import ru.practicum.comments.model.dto.CommentMapper;
import ru.practicum.comments.repository.CommentRepository;
import ru.practicum.event.model.Event;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.exceptions.EntityNotFoundException;
import ru.practicum.exceptions.IncorrectOperationException;
import ru.practicum.user.model.User;
import ru.practicum.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentDto addComment(Integer userId, Integer eventId, CommentDto commentDto) {
        User commentator = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id=" + userId + " не найден"));
        Event events = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Событие с id=" + eventId + " не найдено"));
        Comment comment = commentRepository.save(CommentMapper.coommentDtoToComment(commentDto, events, commentator));
        return CommentMapper.commentToCommentDto(comment);
    }

    @Override
    public CommentDto editComment(Integer userId, Integer commentId, CommentDto commentDto) {
        User commentator = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id=" + userId + " не найден"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий с id=" + commentId + " не найден"));
        if (!comment.getCommentator().getId().equals(commentator.getId())) {
            throw new IncorrectOperationException("Пользователь id= " + userId + " не является владельцем комментария id=" + commentId);
        }
        Comment edited = CommentMapper.comentDtoUpdateComment(comment, commentDto);
        return CommentMapper.commentToCommentDto(edited);
    }

    @Override
    public void removeComment(Integer userId, Integer commentId) {
        User commentator = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id=" + userId + " не найден"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Комментарий с id=" + commentId + " не найден"));
        if (!comment.getCommentator().getId().equals(commentator.getId())) {
            throw new IncorrectOperationException("Пользователь id= " + userId + " не является владельцем комментария id=" + commentId);
        }
        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto> getAllCommentByEvent(Integer eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Событие с id=" + eventId + " не найдено"));
        List<CommentDto> commentsOfEvent = commentRepository.findAllByEventId(eventId).stream()
                .map(CommentMapper::commentToCommentDto)
                .collect(Collectors.toList());
        return commentsOfEvent;
    }
}
