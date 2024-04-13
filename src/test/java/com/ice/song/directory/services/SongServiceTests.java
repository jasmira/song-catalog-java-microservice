package com.ice.song.directory.services;

import com.ice.song.directory.entities.Song;
import com.ice.song.directory.repositories.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SongServiceTests {
    @InjectMocks
    private SongService songService;

    @Mock
    private SongRepository songRepository;

    @BeforeEach
    public void setUp() {
        // Reset mocks before each test
        reset(songRepository);
    }

    @Test
    public void testGetAllSongs() {
        // Mocking the repository method
        List<Song> songs = Arrays.asList(new Song(), new Song());
        when(songRepository.findAll()).thenReturn(songs);

        // Calling the service method
        List<Song> result = songService.getAllSongs();

        // Asserting the result
        assertEquals(songs, result);
    }

    @Test
    public void testGetAllSongsSortedByReleaseYear() {
        // Mocking the repository method
        List<Song> songs = Arrays.asList(new Song(), new Song());
        when(songRepository.findAllByOrderByReleaseYear()).thenReturn(songs);

        // Calling the service method
        List<Song> result = songService.getAllSongsSortedByReleaseYear();

        // Asserting the result
        assertEquals(songs, result);
    }

    @Test
    public void testFilterSongsByYearAndArtist() {
        // Mocking the repository method
        List<Song> songs = Arrays.asList(new Song(), new Song());
        when(songRepository.findByReleaseYearAndArtist(anyInt(), anyString())).thenReturn(songs);

        // Calling the service method
        List<Song> result = songService.filterSongsByReleaseYearAndArtist(2000, "Artist");

        // Asserting the result
        assertEquals(songs, result);
    }

    @Test
    public void testAddSong() {
        // Mocking the repository method
        Song song = new Song();
        when(songRepository.save(any(Song.class))).thenReturn(song);

        // Calling the service method
        Song result = songService.addSong(new Song());

        // Asserting the result
        assertEquals(song, result);
    }
}
