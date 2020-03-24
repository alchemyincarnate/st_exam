package com.section_02.ExposeNativeUtilityPackage;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class NativeLayoutViewManager extends SimpleViewManager<NativeLayoutView> {

    public static final String REACT_CLASS = "NativeLayoutView";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public NativeLayoutView createViewInstance(ThemedReactContext context) {
        return new NativeLayoutView( context );
    }
}
