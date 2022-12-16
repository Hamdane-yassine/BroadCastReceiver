package aseds.ine2.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver monReceiver;
    private final String permission = "send explicite intent";
    /** Appele lorsque l activite est creee. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filtre = new IntentFilter();
        filtre.addAction("com.aseds.CUSTOM_INTENT");
        monReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Intent Détecté.", Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(monReceiver,filtre,permission,null);
    }
    // diffuser un intent particulier
    public void broadcastIntent(View view){
        Intent intent = new Intent(getBaseContext(),DeuxiemeActivity.class);
        intent.setAction("com.aseds.CUSTOM_INTENT");
        sendOrderedBroadcast(intent, permission, monReceiver, null, 0, "explicite intent", new Bundle());
    }
    public void onDestroy() {
        super.onDestroy();
        if (monReceiver!=null)
            unregisterReceiver(monReceiver);
        monReceiver=null;
    }
}