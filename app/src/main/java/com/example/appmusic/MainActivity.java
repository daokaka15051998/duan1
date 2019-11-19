package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtTitle, txtTimeSong,txtTotal;
    SeekBar skSong;
    ImageButton btnNext,btnBack,btnPlay,btnPause;


    ArrayList<Song> arraySong;
    int position = 1;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AnhXa();
        AddSong();

        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    // nếu đang phát -> pause -> đổi hình play
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.pause_button);
                }else {
                    // đang ngừng phát -> đổi hình pause
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.play_arrow);
                }
            }
        });
    }
    private void AddSong(){
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Chúng ta không thuộc về nhau",R.raw.chung_ta_khong_thuoc_ve_nhau));
        arraySong.add(new Song("Em của ngày hôm qua",R.raw.em_cua_ngay_hom_qua));
        arraySong.add(new Song("Như ngày hôm qua",R.raw.nhu_ngay_hom_qua));

    }
    private void AnhXa(){
        txtTimeSong = (TextView) findViewById(R.id.tv1);
        txtTimeSong = (TextView) findViewById(R.id.tv2);
        txtTitle = (TextView) findViewById(R.id.tvTenBaiHat);
        skSong = (SeekBar) findViewById(R.id.seeBarSong);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnPause = (ImageButton) findViewById(R.id.btnPause);

    }
}
