package kaist.homingpigeon_android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SuccessActivity extends AppCompatActivity {


    private Socket mSocket; //the socket
    {
        try{
            mSocket = IO.socket("https://vps332892.ovh.net:4000");  //creation of the new socket
            Log.d("SocketConnection","socket connection successful");
        } catch (URISyntaxException e){
            Log.d("SocketConnection","socket connection failed");
        }
    }


    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

//transition : we get the previous name
        Intent intent = getIntent();
        String message = "you are logged in : "+intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
//end of failed transition

//load the list in app/build/intermediates/assets/debug/recipes.json
        final Context context = this;
        // Get data to display
        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
        // Create adapter
        RecipeAdapter adapter = new RecipeAdapter(this, recipeList);
        // Create list view
        mListView = (ListView) findViewById(R.id.recipe_list_view);
        mListView.setAdapter(adapter);
//end of loaded list
    }




}