/*
 * Copyright (C) 2011-2012 Dominik Schürmann <dominik@dominikschuermann.de>
 *
 * This file is part of AdAway.
 * 
 * AdAway is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AdAway is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AdAway.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.adawaycn.ui;

import org.adawaycn.helper.PreferenceHelper;
import org.adawaycn.util.Constants;
import org.adawaycn.util.Log;
import org.sufficientlysecure.rootcommands.RootCommands;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

public class AdAwayApplication extends Application {

    @Override
    public void onCreate() {

        // workaround for http://code.google.com/p/android/issues/detail?id=20915
        try {
            org.adawaycn.util.Log.d(org.adawaycn.util.Constants.TAG, "Setting workaround for AsyncTask...");
            Class.forName("android.os.AsyncTask");
        } catch (Exception e) { // silently catch all
        }

        // Set Debug level based on preference
        if (org.adawaycn.helper.PreferenceHelper.getDebugEnabled(this)) {
            org.adawaycn.util.Constants.DEBUG = true;
            org.adawaycn.util.Log.d(org.adawaycn.util.Constants.TAG, "Debug set to true by preference!");
            // set RootCommands to debug mode based on AdAway
            RootCommands.DEBUG = org.adawaycn.util.Constants.DEBUG;
        } else {
            org.adawaycn.util.Constants.DEBUG = false;
            RootCommands.DEBUG = org.adawaycn.util.Constants.DEBUG;
        }

        super.onCreate();
        JPushInterface.init(this);
    }

}
