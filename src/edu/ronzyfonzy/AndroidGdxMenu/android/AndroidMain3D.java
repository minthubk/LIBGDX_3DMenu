package edu.ronzyfonzy.AndroidGdxMenu.android;

import com.badlogic.gdx.backends.android.AndroidApplication;
import android.os.Bundle;

public class AndroidMain3D extends AndroidApplication {
    ActionResolved actionResolver;
    Application3D app;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (Application3D)getApplication();
        actionResolver = new ActionResolved(this, app);
        
        // starts libGDX render thread
        initialize(new AndroidGdxMenuActivity(actionResolver, app), false);
    }
}