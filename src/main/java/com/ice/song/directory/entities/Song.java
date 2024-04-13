package com.ice.song.directory.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SONG")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ARTIST")
    private String artist;

    @Column(name = "ALBUM")
    private String album;

    @Column(name = "RELEASE_YEAR")
    private int releaseYear;

    @Column(name = "LENGTH")
    private String length;

    @Column(name = "GENRE")
    private String genre;
}
