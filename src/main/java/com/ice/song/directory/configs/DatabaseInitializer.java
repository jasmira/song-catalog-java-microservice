package com.ice.song.directory.configs;

import com.ice.song.directory.entities.Song;
import com.ice.song.directory.repositories.SongRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseInitializer {
    @Autowired
    private SongRepository songRepository;

    @PostConstruct
    public void init() {
        InputStream inputStream = getClass().getResourceAsStream("/songs.csv");
        BufferedReader reader = null;
        if (inputStream != null) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
        }

        // Skip the header line
        List<String> lines = null;
        if (reader != null) {
            lines = reader.lines().skip(1).collect(Collectors.toList());
        }

        List<Song> songs = new ArrayList<>();
        if (lines != null) {
            for (String line : lines) {
                String[] fields = line.split(",");
                Song song = new Song();
                song.setName(fields[0]);
                song.setArtist(fields[1]);
                song.setAlbum(fields[2]);
                song.setReleaseYear(Integer.parseInt(fields[3]));
                song.setLength(fields[4]);
                song.setGenre(fields[5]);
                songs.add(song);
            }
        }
        songRepository.saveAll(songs);
        System.out.println("Songs saved to database!");
    }
}
