package com.example.appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
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
        KhoiTaoMediaPlayer();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arraySong.size() - 1){
                    position = 0 ;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position++;
                        if (position > arraySong.size() - 1){
                            position = 0 ;
                        }
                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause_button);
                        SetTimeTotal();
                    }
                });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0){
                    position = arraySong.size() -1;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                SetTimeTotal();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play_arrow);
                KhoiTaoMediaPlayer();
            }
        });

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

                }
                SetTimeTotal();
            }
        });

        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    }

    private void SetTimeTotal(){
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");

        txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getDuration())+ "");

        // gán max cảu skSong = mediaPlayer.getDuration()
        skSong.setMax(mediaPlayer.getDuration());
    }

    private void KhoiTaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());
    }

    private void AddSong(){
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Chúng ta không thuộc về nhau",R.raw.chung_ta_khong_thuoc_ve_nhau));
        arraySong.add(new Song("Cơn mưa ngang qua",R.raw.con_mua_ngang_qua));
        arraySong.add(new Song("Em của ngày hôm qua",R.raw.em_cua_ngay_hom_qua));
        arraySong.add(new Song("Mãi mãi bên nhau",R.raw.mai_mai_ben_nhau));
        arraySong.add(new Song("Nắng ấm xa dần",R.raw.nang_am_xa_dan));
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

    public void tvYeuThich(View view) {
        Intent intent = new Intent(MainActivity.this,YeuThichActivity.class);
        startActivity(intent);
    }

    public void tvCaSy(View view) {
        Intent intent = new Intent(MainActivity.this,CaSyActivity.class);
        startActivity(intent);
    }
}
