package com.ice.song.directory.controllers;

import com.ice.song.directory.dto.SongDTO;
import com.ice.song.directory.entities.Song;
import com.ice.song.directory.mappers.SongMapper;
import com.ice.song.directory.services.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SongController {

    private final Logger log = LoggerFactory.getLogger(SongController.class);

    @Autowired
    private SongService songService;

    @Autowired
    private SongMapper songMapper;

    @GetMapping("/songs")
    public ResponseEntity<List<SongDTO>> getAllSongs(
            @RequestParam(value = "sortByReleaseYear", required = false) Boolean sortByReleaseYear,
            @RequestParam(value = "sortBySongName", required = false) Boolean sortBySongName) {
        try {
            List<Song> songs;
            if (sortByReleaseYear != null && sortByReleaseYear) {
                log.info("Request to get all songs sorted by release year");
                songs = songService.getAllSongsSortedByReleaseYear();
            } else if (sortBySongName != null && sortBySongName) {
                log.info("Request to get all songs sorted by song name");
                songs = songService.getAllSongsSortedBySongName();
            } else {
                log.info("Request to get all songs");
                songs = songService.getAllSongs();
            }
            List<SongDTO> songDTOs = songMapper.toDTOList(songs);
            return new ResponseEntity<>(songDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable("id") Long id) {
        log.info("Request to get song: {}", id);
        try {
            Optional<Song> songData = songService.getSongById(id);
            if (songData.isPresent()) {
                SongDTO songDTO = songMapper.toDTO(songData.get());
                return new ResponseEntity<>(songDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/songs/filter")
    public ResponseEntity<List<SongDTO>> filterSongsByReleaseYearAndArtist(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "artist", required = false) String artist) {
        if (year != null && artist != null) {
            log.info("Request to filter songs by year: {} and artist: {}", year, artist);
        } else if (year != null) {
            log.info("Request to filter songs by year: {}", year);
        } else if (artist != null) {
            log.info("Request to filter songs by artist: {}", artist);
        } else {
            log.info("Request to filter songs with no filter parameters");
        }
        try {
            List<Song> filteredSongs = songService.filterSongsByReleaseYearAndArtist(year, artist);
            List<SongDTO> filteredSongDTOs = songMapper.toDTOList(filteredSongs);
            return new ResponseEntity<>(filteredSongDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/song")
    public ResponseEntity<SongDTO> addSong(@RequestBody SongDTO songDTO) {
        log.info("Request to add a song: {}", songDTO);
        try {
            Song song = songMapper.toEntity(songDTO);
            Song addedSong = songService.addSong(song);
            SongDTO addedSongDTO = songMapper.toDTO(addedSong);
            return new ResponseEntity<>(addedSongDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
