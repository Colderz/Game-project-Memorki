package pakiet.arkadiuszzimny.memorkiaplikacja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class StepActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnStart;
    Animation btnAnimation;
    int start = 0;
    NumberPicker numberPicker;
    NumberPicker numberPicker2;
    String nick2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        String poziomy[] = {"Łatwy","Średni","Trudny"};
        String tematy[] = {"Owoce i warzywa", "Motoryzacja", "Informatyka"};

        tabIndicator = findViewById(R.id.tabIndicator);
        btnNext = findViewById(R.id.btn_next);
        btnStart = findViewById(R.id.btn_rozpocznij);
        btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btn_animation);

        numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(poziomy.length-1);
        numberPicker.setDisplayedValues(poziomy);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setOnValueChangedListener(this);

        numberPicker2 = findViewById(R.id.numberPicker2);
        numberPicker2.setMinValue(0);
        numberPicker2.setMaxValue(tematy.length-1);
        numberPicker2.setDisplayedValues(tematy);
        numberPicker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker2.setOnValueChangedListener(this);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameBoard();
            }
        });

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Poziom","Dopasuj poziom trudności do poziomu zaawansowania. Dobierz liczbę punktów otrzymanych na starcie i zasady rozgrywki. Sprawdź na jakim poziomie jest Twoja pamięć!", R.drawable.poziompng));
        mList.add(new ScreenItem("Tematyka","Nudzi Cię jedna tematyka kart? Do wyboru zostało udostępnionych wiele tematyk kart, od prostych po te bardziej specjalistyczne! Pozwoli Ci to lepiej zapamiętywać pary kart!", R.drawable.tempng));
        mList.add(new ScreenItem("Gotowy?","To ten moment! Twoja pamięć zostaje wystawiona na próbę. Tylko od Ciebie i Twojej pamięci zależy liczba punktów. Powodzenia!", R.drawable.startpng));
        if(start==0) {
            numberPicker.setVisibility(View.VISIBLE);
        }
        start++;
        screenPager = findViewById(R.id.viewPager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);
        tabIndicator.setupWithViewPager(screenPager);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPicker.setVisibility(View.INVISIBLE);
                position = screenPager.getCurrentItem();

                if(position<mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);
                }

                if(position == mList.size()) {
                    loadLastScreen();
                }

            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1) {
                    loadSecondScreen();
                }
                if(tab.getPosition()==0) {
                    loadFirstScreen();
                }
                if(tab.getPosition() == mList.size()-1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void loadLastScreen() {
        numberPicker.setVisibility(View.INVISIBLE);
        numberPicker2.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        btnStart.setAnimation(btnAnimation);
    }

    private void loadFirstScreen() {
        numberPicker2.setVisibility(View.INVISIBLE);
        numberPicker.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.VISIBLE);
    }

    private void loadSecondScreen() {
        numberPicker2.setVisibility(View.VISIBLE);
        numberPicker.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.VISIBLE);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().hasExtra("nick")) {
            nick2 = (String) getIntent().getCharSequenceExtra("nick");
        }
    }

    public void openGameBoard() {
        Intent intent2 = new Intent(this, GameActivity.class);
        intent2.putExtra("nick",nick2);
        startActivity(intent2);
    }
}