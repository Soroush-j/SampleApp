package com.example.sjavaherian.myapp;

import com.sjavaherian.movie.main.network.pojo.GenreRetro;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class test {

    @Test
    public void mm() {

        List<GenreRetro> genre = new ArrayList<>();
        genre.add(new GenreRetro(1, "Crime"));
       genre.add(new GenreRetro(2,     " Drama"));
       genre.add(new GenreRetro( 3,    " Action"));
       genre.add(new GenreRetro( 4,    " Biography"));
       genre.add(new GenreRetro( 5,    " History"));
       genre.add(new GenreRetro( 6,    " Adventure"));
       genre.add(new GenreRetro( 7,    " Fantasy"));
       genre.add(new GenreRetro( 8,    " Western"));
       genre.add(new GenreRetro( 9,    " Comedy"));
       genre.add(new GenreRetro( 10, "Sci - Fi"));
       genre.add(new GenreRetro( 11,   "  Mystery"));
       genre.add(new GenreRetro( 12,   "  Thriller"));
       genre.add(new GenreRetro( 13,   "  Family"));
       genre.add(new GenreRetro( 14,   "  War"));
       genre.add(new GenreRetro( 15,   "  Animation"));
       genre.add(new GenreRetro( 16,   "  Romance"));
       genre.add(new GenreRetro( 17,   "  Horror"));
       genre.add(new GenreRetro( 18,   "  Music"));
       genre.add(new GenreRetro( 19,   "  Film - Noir"));
       genre.add(new GenreRetro( 20,   "  Musical"));
       genre.add(new GenreRetro( 21,   "  Sport"));

       Gson gson = new Gson();
       System.out.print(gson.toJson(genre));
    }

}
