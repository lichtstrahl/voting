package root.iv.voting;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewText)
    protected TextView view;
    private MessageGenerator generator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        view.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        generator = new MessageGenerator(new Random());
    }

    @OnClick(R.id.viewText)
    public void clickText() {
        Toast.makeText(this, generator.getRandomString(5), Toast.LENGTH_SHORT).show();
    }
}
