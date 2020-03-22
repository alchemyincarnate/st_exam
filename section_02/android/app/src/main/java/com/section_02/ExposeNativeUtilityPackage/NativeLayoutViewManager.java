package com.section_02.ExposeNativeUtilityPackage;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class NativeLayoutViewManager extends SimpleViewManager<NativeLayoutView> {

    @Override
    public String getName() {
        return "NativeLayoutView";
    }

    @Override
    public NativeLayoutView createViewInstance(ThemedReactContext context) {
        return new NativeLayoutView( context );
    }
}
