package com.ice.song.directory.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SongDTO {
    private Long id;
    private String name;
    private String artist;
    private String album;
    private int releaseYear;
    private String length;
    private String genre;
}
