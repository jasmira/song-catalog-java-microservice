package com.ice.song.directory.repositories;

import com.ice.song.directory.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByReleaseYearAndArtist(Integer releaseYear, String artist);
    List<Song> findByReleaseYear(Integer year);
    List<Song> findByArtist(String artist);
    List<Song> findAllByOrderByReleaseYear();
    List<Song> findAllByOrderByName();
}
