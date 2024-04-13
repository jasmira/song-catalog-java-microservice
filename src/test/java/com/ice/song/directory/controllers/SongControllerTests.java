package com.ice.song.directory.controllers;

import com.ice.song.directory.dto.SongDTO;
import com.ice.song.directory.entities.Song;
import com.ice.song.directory.mappers.SongMapper;
import com.ice.song.directory.services.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SongControllerTests {

    @InjectMocks
    private SongController songController;

    @Mock
    private SongService songService;

    @Mock
    private SongMapper songMapper;

    @BeforeEach
    public void setUp() {
        // Reset mocks before each test
        reset(songService);
    }

    @Test
    public void testGetAllSongs() {
        // Mocking the service method
        List<SongDTO> songDTOs = Arrays.asList(new SongDTO(), new SongDTO());
        when(songService.getAllSongs()).thenReturn(Arrays.asList(new Song(), new Song()));
        when(songMapper.toDTOList(anyList())).thenReturn(songDTOs);

        // Calling the controller method
        ResponseEntity<List<SongDTO>> response = songController.getAllSongs(null, null);

        // Asserting the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(songDTOs, response.getBody());
    }

    @Test
    public void testGetSongById() {
        // Mocking the service method
        SongDTO songDTO = new SongDTO();
        when(songService.getSongById(anyLong())).thenReturn(Optional.of(new Song()));
        when(songMapper.toDTO(any(Song.class))).thenReturn(songDTO);

        // Calling the controller method
        ResponseEntity<SongDTO> response = songController.getSongById(1L);

        // Asserting the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(songDTO, response.getBody());
    }

    @Test
    public void testFilterSongsByYearAndArtist() {
        // Mocking the service method
        List<SongDTO> songDTOs = Arrays.asList(new SongDTO(), new SongDTO());
        when(songService.filterSongsByReleaseYearAndArtist(anyInt(), anyString())).thenReturn(Arrays.asList(new Song(), new Song()));
        when(songMapper.toDTOList(anyList())).thenReturn(songDTOs);

        // Calling the controller method
        ResponseEntity<List<SongDTO>> response = songController.filterSongsByReleaseYearAndArtist(2000, "Artist");

        // Asserting the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(songDTOs, response.getBody());
    }

    @Test
    public void testAddSong() {
        // Create a new SongDTO with some data
        SongDTO songDTO = new SongDTO();
        songDTO.setName("Song Name");
        songDTO.setAlbum("Album");
        songDTO.setArtist("Artist");
        songDTO.setLength("3:15");
        songDTO.setGenre("Pop");
        songDTO.setReleaseYear(2002);

        // Mocking the service method to return a Song with some data
        Song song = songMapper.toEntity(songDTO);
        when(songService.addSong(song)).thenReturn(song);
        when(songMapper.toDTO(song)).thenReturn(songDTO);

        // Calling the controller method
        ResponseEntity<SongDTO> response = songController.addSong(songDTO);

        // Asserting the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(songDTO, response.getBody());
    }
}
