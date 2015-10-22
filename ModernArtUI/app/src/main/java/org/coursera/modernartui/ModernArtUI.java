package org.coursera.modernartui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.app.DialogFragment;


public class ModernArtUI extends Activity {

    private SeekBar colorControl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art_ui);

        final LinearLayout blackBox = (LinearLayout) findViewById(R.id.blackBox);
        final LinearLayout whiteBox = (LinearLayout) findViewById(R.id.whiteBox);
        final LinearLayout blueBox = (LinearLayout) findViewById(R.id.blueBox);
        final LinearLayout redBox = (LinearLayout) findViewById(R.id.redBox);

        final int colorBlack = ((ColorDrawable) blackBox.getBackground()).getColor();
        final int colorRed = ((ColorDrawable) redBox.getBackground()).getColor();
        final int colorWhite = ((ColorDrawable) whiteBox.getBackground()).getColor();
        final int colorBlue = ((ColorDrawable) blueBox.getBackground()).getColor();

        colorControl = (SeekBar) findViewById(R.id.colorControl);

        colorControl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;

                setNewBackgroundColor(redBox,colorBlack);
                setNewBackgroundColor(blueBox,colorBlue);
                setNewBackgroundColor(whiteBox,colorWhite );
                setNewBackgroundColor(blackBox,colorBlack);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            private void setNewBackgroundColor(LinearLayout box, int boxColor)
            {
                float[] hsvColor = new float[3];

                Color.colorToHSV(boxColor, hsvColor);

                hsvColor[0] = hsvColor[0] + progressChanged;
                hsvColor[0] = hsvColor[0] % 360;

                box.setBackgroundColor(Color.HSVToColor(Color.alpha(boxColor), hsvColor));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modern_art_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_more_information) {
            DialogFragment moreInfoFragment = new MoreInfoDialogFragment();
            moreInfoFragment.show(getFragmentManager(), "moreInfo");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
