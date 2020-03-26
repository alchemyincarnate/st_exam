package com.section_02.ExposeNativeUtilityPackage;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

// Unfinished, intended to create the RN view from layout.xml
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
