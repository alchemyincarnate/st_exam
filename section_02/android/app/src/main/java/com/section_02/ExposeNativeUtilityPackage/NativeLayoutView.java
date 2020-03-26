package com.section_02.ExposeNativeUtilityPackage;
import com.section_02.R;

import android.content.Context;
import android.widget.LinearLayout;

// Unfinished, intended to create the RN view from layout.xml
public class NativeLayoutView extends LinearLayout {

    private Context context;
    public NativeLayoutView( Context context ) {
        super( context );
        this.context = context;
    }

    public void init() {
        inflate( context, R.layout.native_layout_view, this );
    }
}
