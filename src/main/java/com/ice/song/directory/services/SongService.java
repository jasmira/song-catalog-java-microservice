package com.ice.song.directory.services;

import com.ice.song.directory.entities.Song;
import com.ice.song.directory.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public List<Song> getAllSongsSortedByReleaseYear() {
        return songRepository.findAllByOrderByReleaseYear();
    }

    public List<Song> getAllSongsSortedBySongName() {
        return songRepository.findAllByOrderByName();
    }

    public Optional<Song> getSongById(Long id) {
        return songRepository.findById(id);
    }

    public List<Song> filterSongsByReleaseYearAndArtist(Integer year, String artist) {
        if (year != null && artist != null) {
            return songRepository.findByReleaseYearAndArtist(year, artist);
        } else if (year != null) {
            return songRepository.findByReleaseYear(year);
        } else if (artist != null) {
            return songRepository.findByArtist(artist);
        } else {
            return getAllSongs();
        }
    }

    public Song addSong(Song song) {
        return songRepository.save(song);
    }
}
