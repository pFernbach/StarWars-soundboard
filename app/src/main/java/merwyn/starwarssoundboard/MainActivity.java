package merwyn.starwarssoundboard;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.system.Os;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.Button;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;






public class MainActivity extends Activity {

    private ShakeDetector mShakeDetector;
    private Sensor mAccelerometer;
    private SensorManager mSensorManager;
    private Toast toast;
    private AudioManager audioManager;
    private SoundPool soundPool;
    public int loadingSounds;
    public int currentStreamID;
    public GridLayout myGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toast =Toast.makeText(this,"",Toast.LENGTH_LONG);
        myGridLayout = (GridLayout)findViewById(R.id.gridView);
        // AudioManager audio settings for adjusting the volume
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //Hardware buttons setting to adjust the media sound
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        // create soundPool with onLoad listener
        loadingSounds = 0 ;
        currentStreamID = 0 ;
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loadingSounds--;
                if (loadingSounds == 0) {
                    StringBuilder message = new StringBuilder();
                    message.append("  All sound loaded");
                    toast.setText(message.toString());
                    toast.show();
                }
            }
        });

        // button stop
        Button but = (Button) this.findViewById(R.id.b_Stop);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.stop(currentStreamID);
            }
        });

        
        // create view component
        addTextView("Weapons");
        addButton(R.raw.battledroid_blaster, "Battledroid");
        addButton(R.raw.blaster_pistol, "Blaster pistol");
        addButton(R.raw.boba_blaster, "Boba's blaster");
        addButton(R.raw.chewie_bowcaster, "Chewie's Bowcaster");
        addButton(R.raw.clone_trooper_blaster, "Clone's blaster");
        addButton(R.raw.droideka_blaster, "Droideka");
        addButton(R.raw.han_blaster, "Han's blaster");
        addButton(R.raw.ion_blaster, "Ion blaster");
        addButton(R.raw.jango_blasters, "Jango's blaster");
        addButton(R.raw.planetary_ion_cannon, "Planetary Ion cannon");
        addButton(R.raw.salve_blaster, "Shootout (small)");
        addButton(R.raw.big_shootout, "Shootout (big)");
        addButton(R.raw.geonosian_sonic_blaster, "Sonic blaster");
        addButton(R.raw.stormtrooper_blaster, "Stormtrooper's blaster");
        addButton(R.raw.stun_blast, "Stun blaster");
        addButton(R.raw.tusken_rifle, "Tusken rifle");


        addTextView("Ships");
        addButton(R.raw.at_at_fire, "AT-AT fire");
        addButton(R.raw.at_at_walk, "AT-AT walk");
        addButton(R.raw.alarm_shield, "Alarm shield");
        addButton(R.raw.electrical_trouble, "Electric trouble");
        addButton(R.raw.engine_trouble, "Engine trouble");
        addButton(R.raw.falcon_flyby, "Falcon flyby");
        addButton(R.raw.hyperdrive_fail, "Hyperdrive fail");
        addButton(R.raw.lightspeed_jump, "Lighspeed jump");
        addButton(R.raw.quad_laser, "Quad-laser turret");
        addButton(R.raw.slave_flyby, "Slave II flyby");
        addButton(R.raw.ship_damage, "Ship damage");
        addButton(R.raw.landspeeder_approach, "Speeder approach");
        addButton(R.raw.landspeeder_accelerate, "Speeder accelerate");
        addButton(R.raw.speeder_bike, "Speeder bike");
        addButton(R.raw.tie_fighter_fire, "TIE fire");
        addButton(R.raw.tie_fighter_flyby, "TIE flyby");
        addButton(R.raw.walker, "Walker");
        addButton(R.raw.x_wing_fire, "X-WING fire");
        addButton(R.raw.x_wing_flyby, "X-WING flyby");

        addTextView("Aliens");
        addButton(R.raw.ewoks_1, "Ewoks");
        addButton(R.raw.ewoks_happy, "Ewoks (happy)");
        addButton(R.raw.gamorrean_grunt, "Gamorean grunt");
        addButton(R.raw.gamorrean_snort, "Gamorean snort");
        addButton(R.raw.hutt, "Hutt");
        addButton(R.raw.hutt2, "Hutt2");
        addButton(R.raw.hutt_rire, "Hutt (laugh)");
        addButton(R.raw.jawa_01, "Jawa ");
        addButton(R.raw.jawa_colere, "Jawa (angry)");
        addButton(R.raw.salacious_crumb1, "Salacious1");
        addButton(R.raw.salacious_crumb2, "Salacious2");
        addButton(R.raw.trandoshan_clicking, "Trandoshan (clicking)");
        addButton(R.raw.trandoshan_roar, "Trandoshan (roar)");
        addButton(R.raw.trandoshan_signal, "Trandoshan (signal)");
        addButton(R.raw.wookie, "Wookie");
        addButton(R.raw.wookie_sad, "Wookie (sad)");
        addButton(R.raw.angry_wookie, "Wookie (angry)");

        addTextView("Droids");
        addButton(R.raw.buzz_droid, "Buzz droid");
        addButton(R.raw.gonk_droid, "Gonk droid");
        addButton(R.raw.imperial_probe_droid, "Imperial probe");
        addButton(R.raw.r2d2_13, "R2-D2 1");
        addButton(R.raw.r2d2_14, "R2-D2 2");
        addButton(R.raw.r2d2_17, "R2-D2 3");
        addButton(R.raw.treadwell_droid, "Treadwell droid");

        addTextView("Creatures");
        addButton(R.raw.acklay, "Acklay");
        addButton(R.raw.aiwha, "Aiwha");
        addButton(R.raw.bantha, "Bantha");
        addButton(R.raw.boga, "Boga");
        addButton(R.raw.dewback, "Dewback");
        addButton(R.raw.eopie, "Eopie");
        addButton(R.raw.krayt_dragon, "Krayt dragon");
        addButton(R.raw.mynock, "Mynock");
        addButton(R.raw.nexu, "Nexu");
        addButton(R.raw.peko_peko, "Peko-peko");
        addButton(R.raw.rancor_roar, "Rancor roar");
        addButton(R.raw.rancor_snack, "Rancor snack");
        addButton(R.raw.reek, "Reek");
        addButton(R.raw.ronto, "Ronto");
        addButton(R.raw.sando_aqua_monster, "Sando aqua monster");
        addButton(R.raw.sarlacc, "Sarlacc");
        addButton(R.raw.tauntaun, "Tauntaun");
        addButton(R.raw.tusken, "Tusken");
        addButton(R.raw.wampa, "Wampa");
        addButton(R.raw.worrt, "Worrt");

        addTextView("Misc");
        addButton(R.raw.computer_bip, "Computer (dialoguing)");
        addButton(R.raw.bips_connecting, "Computer (connection)");
        addButton(R.raw.bips_success, "Computer (success)");
        addButton(R.raw.computer_alarm, "Computer (alarm)");
        addButton(R.raw.computer_processing, "Computer (processing)");
        //addButton(R.raw.computer_turn_on, "Computer (turn on)");
        addButton(R.raw.iobot_10, "Computer (turn on)");
        addButton(R.raw.cell_door, "Door");
        addButton(R.raw.blast_door, "Door (blast)");
        addButton(R.raw.door_old, "Door (old)");
        addButton(R.raw.drill, "Drill");
        addButton(R.raw.small_explosion, "Explosion (small)");
        addButton(R.raw.explosion_space, "Explosion (space)");
        addButton(R.raw.big_explosion, "Explosion (big)");
        addButton(R.raw.force_lightning, "Force lightning");
        addButton(R.raw.generator_buzz, "Generator buzz");
        addButton(R.raw.hammer, "Hammer");
        addButton(R.raw.imperial_battle_alarm, "Imperial alarm");
        addButton(R.raw.jammed_coms, "Jammed comlink");
        addButton(R.raw.kick, "Kick");
        addButton(R.raw.punch, "Punch");
        addButton(R.raw.slit_throat,"Slit throat");
        addButton(R.raw.stormtroopers_on_the_move, "Stormtroopers running");
        addButton(R.raw.tow_cable, "Tow cable");


        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                randomSound();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void linkSoundToButton(int ressourceId, int buttonId){
        Button but = (Button) this.findViewById(buttonId);
        loadingSounds++;
        final int soundId = soundPool.load(this,ressourceId,1);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStreamID = soundPool.play(soundId,1.f,1.f,1,0,1.f);
            }
        });
    }

    public void randomSound(){
        //TODO
        StringBuilder message = new StringBuilder();

        message.append("  test ");
        toast.setText(message.toString());
        toast.show();
    }


    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        soundPool.stop(currentStreamID);
        super.onPause();
    }

    public void addTextView(String text){
        TextView textview = new TextView(this);
        textview.setText(text);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.columnSpec = GridLayout.spec(0,2);
        layoutParams.setGravity(Gravity.CENTER);
        textview.setLayoutParams(layoutParams);
        if (Build.VERSION.SDK_INT < 23) {
            textview.setTextAppearance(getApplicationContext(), R.style.boldText);
        } else {
           // textview.setTextAppearance(R.style.boldText);
        }

        myGridLayout.addView(textview);
    }

    public void addButton(int ressourceId, String title){
        Button but = new Button(this);
        but.setText(title);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.setGravity(Gravity.FILL);
        but.setLayoutParams(layoutParams);
        myGridLayout.addView(but);
        loadingSounds++;
        final int soundId = soundPool.load(this,ressourceId,1);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStreamID = soundPool.play(soundId,1.f,1.f,1,0,1.f);
            }
        });
    }
}
