package com.ice.song.directory.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SongControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllSongs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/songs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void testGetSongById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/song/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void testSortSongsByYear() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/songs?sortByReleaseYear=true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void testSortSongsBySongName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/songs?sortBySongName=true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void testFilterSongsByYearAndArtist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/songs/filter?releaseYear=2000&artist=Artist"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void testAddSong() throws Exception {
        String songJson = "{\"name\":\"Test Song\",\"artist\":\"Test Artist\",\"album\":\"Test Album\",\"releaseYear\":2022,\"length\":\"4:30\",\"genre\":\"Pop\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/song")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(songJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}
