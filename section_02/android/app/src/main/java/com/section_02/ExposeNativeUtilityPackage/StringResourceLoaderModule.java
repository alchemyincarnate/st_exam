package com.section_02.ExposeNativeUtilityPackage;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

import java.util.Locale;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * Static class to load strings from strings.xml to react-native
 */
public class StringResourceLoaderModule extends ReactContextBaseJavaModule {

    public StringResourceLoaderModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "StringResourceLoader";
    }

    /**
     *  This loads a string from strings.xml when it is unavailable from the hosted file
     * @param languageId language code
     * @param resourceName the ID of the string
     * @param context react application context
     * @return the value of the string after localization
     */
    public static String getLocaleStringResource(String languageId, String resourceName, Context context) {
        // https://stackoverflow.com/questions/9475589/how-to-get-string-from-different-locales-in-android
        String result;
        Resources resources = context.getResources();
        Locale requestedLocale = new Locale( languageId );

        // change locale
        Configuration conf = resources.getConfiguration();
        Locale savedLocale = conf.locale;
        conf.locale = requestedLocale;
        resources.updateConfiguration(conf, null);

        // retrieve resources from desired locale
        int id = resources.getIdentifier( resourceName, "string", context.getPackageName());
        result = resources.getString( id );

        // restore original locale
        conf.locale = savedLocale;
        resources.updateConfiguration(conf, null);

        return result;
    }

    // React Methods

    /**
     *  Wrapper of getLocaleStringResources to expose it in react-native
     */
    @ReactMethod
    public void getLocalizedString( String languageId, String resourceName, Callback errorCallback, Callback successCallback )
    {
        try {
            String result = getLocaleStringResource( languageId, resourceName, getReactApplicationContext() );
            successCallback.invoke( result );
        } catch (Exception e) {
            errorCallback.invoke( e.getMessage() );
        }
    }
}
