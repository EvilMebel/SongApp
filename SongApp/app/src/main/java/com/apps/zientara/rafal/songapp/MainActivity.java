package com.apps.zientara.rafal.songapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.apps.zientara.rafal.songapp.fragments.SongsFragment;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mainActivity_outputText)
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        openFragment(new SongsFragment(), false);
//        outputText.setText(loadJSONFromAsset());
    }
/*
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("songs-list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }*/
}
