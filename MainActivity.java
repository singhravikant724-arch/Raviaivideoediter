package com.ravi.aivideoeditor;

import android.app.*;
import android.os.*;
import android.content.*;
import android.net.Uri;
import android.view.*;
import android.widget.*;
import android.graphics.Color;

public class MainActivity extends Activity {
    LinearLayout box; TextView selected;
    public void onCreate(Bundle b){
        super.onCreate(b);
        box=new LinearLayout(this); box.setOrientation(LinearLayout.VERTICAL); box.setPadding(32,40,32,32);
        TextView title=new TextView(this); title.setText("Ravi AI Video Editor"); title.setTextSize(28); title.setTextColor(Color.BLACK);
        box.addView(title);
        TextView sub=new TextView(this); sub.setText("Personal mobile video editor • Version 1.0"); sub.setTextSize(16); box.addView(sub);
        selected=new TextView(this); selected.setText("No video selected"); selected.setPadding(0,30,0,20); box.addView(selected);
        add("🎬  Select Video", v->pick());
        add("✂️  Trim / Cut", v->msg("Trim editor will be added in the next build."));
        add("📝  Add Text / Captions", v->msg("Caption editor ready for the next module."));
        add("🎵  Add Music", v->msg("Music picker will be connected in the next module."));
        add("🔇  Mute Audio", v->msg("Audio mute processing will be connected to FFmpeg."));
        add("🤖  AI Edit", v->msg("AI Edit: automatic captions, highlights and cut suggestions."));
        setContentView(box);
    }
    void add(String s, View.OnClickListener l){ Button x=new Button(this); x.setText(s); x.setTextSize(16); x.setOnClickListener(l); box.addView(x,new LinearLayout.LayoutParams(-1,65)); }
    void pick(){ Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT); i.setType("video/*"); i.addCategory(Intent.CATEGORY_OPENABLE); startActivityForResult(i,10); }
    protected void onActivityResult(int r,int c,Intent d){super.onActivityResult(r,c,d); if(r==10&&c==RESULT_OK&&d!=null){ Uri u=d.getData(); selected.setText("Selected video:\n"+u.toString()); }}
    void msg(String s){ Toast.makeText(this,s,Toast.LENGTH_LONG).show(); }
}
