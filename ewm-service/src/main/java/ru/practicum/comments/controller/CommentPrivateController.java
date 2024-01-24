package ru.practicum.comments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.model.dto.CommentDto;
import ru.practicum.comments.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class CommentPrivateController {
    private final CommentService service;

    @PostMapping("/{userId}/event/{eventId}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@PathVariable Integer userId, @PathVariable Integer eventId, @RequestBody @Valid CommentDto commentDtoIn) {
        return service.addComment(userId, eventId, commentDtoIn);
    }

    @PatchMapping("/{userId}/comment/{commentId}")
    public CommentDto editComment(@PathVariable Integer userId, @PathVariable Integer commentId, @RequestBody @Valid CommentDto commentDtoIn) {
        return service.editComment(userId, commentId, commentDtoIn);
    }

    @GetMapping("/comment/{eventId}")
    public List<CommentDto> getAllCommitByEvent(@PathVariable Integer eventId) {
        return service.getAllCommentByEvent(eventId);
    }

    @DeleteMapping("/{userId}/comment/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeComment(@PathVariable Integer userId, @PathVariable Integer commentId) {
        service.removeComment(userId, commentId);
    }
}
