package kaist.homingpigeon_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;

public class socketIoStuff extends AppCompatActivity {

    private Socket mSocket;
    {
        try{
            mSocket = IO.socket("http://chat.socket.io");  //creation of the new socket
        } catch (URISyntaxException e){}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_io_stuff);
        mSocket.connect();

        //try activity transition
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_socket_io_stuff);
        layout.addView(textView);
        //

    }
}
