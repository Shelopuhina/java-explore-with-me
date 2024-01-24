package ru.practicum.statsDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewStatResponseDto {
    private String app;
    private String uri;
    private Integer hits;
}

