package pakiet.arkadiuszzimny.memorkiaplikacja;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout myLayout;
    AnimationDrawable animationDrawable;
    Button button;
    String nick;
    EditText editNick;
    AlertDialog.Builder mBuilder;
    View mView;
    ImageView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        editNick = (EditText) findViewById(R.id.editText);
        myLayout = (ConstraintLayout) findViewById(R.id.myLayout);
        info = findViewById(R.id.infoImage);
        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4200);
        animationDrawable.setExitFadeDuration(4200);
        animationDrawable.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nick = editNick.getText().toString();
                openMenuActivity();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBuilder = new AlertDialog.Builder(MainActivity.this);
                mView = getLayoutInflater().inflate(R.layout.author_dialog, null);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

    }

    public void openMenuActivity() {
        Intent intent = new Intent(this, StepActivity.class);
        intent.putExtra("nick",nick);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
