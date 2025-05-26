package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import dto.MovieDTO;
import util.PropertyLoader;



public class MovieDAO {
	
	private static final String API_KEY = PropertyLoader.get("TMDB_API_KEY");
    private static final String BASE_URL = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=";

    //private static final String API_KEY = "d96552d5199f724209740e154efa276b"; //取得したTMDb APIキー
    //private static final String BASE_URL = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY + "&query=";
    
    //ジャンルのマッピング
    private static final Map<Integer, String> GENRE_MAP = new HashMap<>();
    static {
        GENRE_MAP.put(28, "Action");
        GENRE_MAP.put(12, "Adventure");
        GENRE_MAP.put(16, "Animation");
        GENRE_MAP.put(35, "Comedy");
        GENRE_MAP.put(80, "Crime");
        GENRE_MAP.put(99, "Documentary");
        GENRE_MAP.put(18, "Drama");
        GENRE_MAP.put(10751, "Family");
        GENRE_MAP.put(14, "Fantasy");
        GENRE_MAP.put(36, "History");
        GENRE_MAP.put(27, "Horror");
        GENRE_MAP.put(10402, "Music");
        GENRE_MAP.put(9648, "Mystery");
        GENRE_MAP.put(10749, "Romance");
        GENRE_MAP.put(878, "Science Fiction");
        GENRE_MAP.put(10770, "TV Movie");
        GENRE_MAP.put(53, "Thriller");
        GENRE_MAP.put(10752, "War");
        GENRE_MAP.put(37, "Western");
    }

    public List<MovieDTO> searchMovies(String movieTitle) {
        List<MovieDTO> movieList = new ArrayList<>();

        try {
            String encodedTitle = java.net.URLEncoder.encode(movieTitle, "UTF-8");
            for (int page = 1; page <= 2; page++) {
                String urlString = BASE_URL + encodedTitle + "&page=" + page;
                String jsonResponse = makeApiCall(urlString);
                movieList.addAll(parseMovieJsonList(jsonResponse));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieList;
    }



    //入力したタイトルで、TMDBのデータベース内を検索し、その結果を返す
    private String makeApiCall(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    //返ってきたJSONの情報を、MovieDTO型に直す
    private List<MovieDTO> parseMovieJsonList(String jsonResponse) {
        List<MovieDTO> movieList = new ArrayList<>();

        // JSONが空かどうかチェック（防御コード）
        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
            System.out.println("空のレスポンスです。APIに失敗している可能性があります。");
            return movieList;
        }

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray results = jsonObject.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject movie = results.getJSONObject(i);

            String title = movie.optString("title", "タイトル未定");
            String description = movie.optString("overview", "概要なし");
            int tmdbId = movie.optInt("id", -1);
            String thumbnailUrl = "https://image.tmdb.org/t/p/w500" + movie.optString("poster_path", "");

            // ジャンルIDをジャンル名に変換（1つ目のジャンルのみ）
            JSONArray genreArray = movie.optJSONArray("genre_ids");
            String genre = "ジャンル不明";
            if (genreArray != null && genreArray.length() > 0) {
                int genreId = genreArray.getInt(0);
                genre = GENRE_MAP.getOrDefault(genreId, "ジャンル不明");
            }

            movieList.add(new MovieDTO(title, genre, description, tmdbId, thumbnailUrl));
        }

        return movieList;
    }
    
    public MovieDTO getMovieDetailsById(int tmdbId) {
        String urlString = "https://api.themoviedb.org/3/movie/" + tmdbId + "?api_key=" + API_KEY;
        String jsonResponse = makeApiCall(urlString);

        JSONObject movie = new JSONObject(jsonResponse);

        String title = movie.optString("title", "タイトル未定");
        String description = movie.optString("overview", "概要なし");
        String thumbnailUrl = "https://image.tmdb.org/t/p/w500" + movie.optString("poster_path", "");

        // ジャンルは配列で返ってくるので先頭を表示
        String genre = "ジャンル不明";
        JSONArray genreArray = movie.optJSONArray("genres");
        if (genreArray != null && genreArray.length() > 0) {
            genre = genreArray.getJSONObject(0).optString("name", "ジャンル不明");
        }

        return new MovieDTO(title, genre, description, tmdbId, thumbnailUrl);
    }

}

