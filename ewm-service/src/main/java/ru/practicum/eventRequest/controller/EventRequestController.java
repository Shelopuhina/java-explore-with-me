package ru.practicum.eventRequest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.eventRequest.model.dto.ParticipationRequestDto;
import ru.practicum.eventRequest.service.EventRequestService;


import java.util.List;

@RestController
@RequestMapping("/users/{userId}/requests")
@RequiredArgsConstructor
public class EventRequestController {
    private final EventRequestService requestService;

    @GetMapping
    public List<ParticipationRequestDto> getRequests(@PathVariable Integer userId) {
        return requestService.getParticipationRequestsByRequester(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto addRequest(
            @PathVariable Integer userId,
            @RequestParam Integer eventId
    ) {
        return requestService.addParticipationRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(
            @PathVariable Integer userId,
            @PathVariable Integer requestId
    ) {
        return requestService.cancelParticipationRequest(userId, requestId);
    }
}