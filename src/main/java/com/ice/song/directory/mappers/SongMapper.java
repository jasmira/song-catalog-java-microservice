package com.ice.song.directory.mappers;

import com.ice.song.directory.dto.SongDTO;
import com.ice.song.directory.entities.Song;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SongMapper {
    public Song toEntity(SongDTO dto) {
        Song song = new Song();
        song.setName(dto.getName());
        song.setArtist(dto.getArtist());
        song.setAlbum(dto.getAlbum());
        song.setReleaseYear(dto.getReleaseYear());
        song.setLength(dto.getLength());
        song.setGenre(dto.getGenre());
        return song;
    }

    public SongDTO toDTO(Song song) {
        SongDTO dto = new SongDTO();
        dto.setId(song.getId());
        dto.setName(song.getName());
        dto.setArtist(song.getArtist());
        dto.setAlbum(song.getAlbum());
        dto.setReleaseYear(song.getReleaseYear());
        dto.setLength(song.getLength());
        dto.setGenre(song.getGenre());
        return dto;
    }

    public List<SongDTO> toDTOList(List<Song> songs) {
        return songs.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
