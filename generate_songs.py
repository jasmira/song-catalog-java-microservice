import csv
import random

# Function to generate random song data
def generate_song():
    name = f"Song {random.randint(1, 1000)}"
    artist = f"Artist {random.randint(1, 100)}"
    album = f"Album {random.randint(1, 50)}"
    release_year = random.randint(1950, 2024)
    length_minutes = random.randint(1, 10)
    length_seconds = random.randint(0, 59)
    length = f"{length_minutes}:{length_seconds:02}"
    genre = random.choice(["Rock", "Pop", "Jazz", "Electronic", "Blues", "Classical"])
    return [name, artist, album, release_year, length, genre]

# Generate 1000 songs
songs = [generate_song() for _ in range(1000)]

# Write to CSV file
with open('songs.csv', 'w', newline='') as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(["Name", "Artist", "Album", "ReleaseYear", "Length", "Genre"])
    writer.writerows(songs)
