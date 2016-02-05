/*
 * Copyright 2016 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.ehviewer.client;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;

import com.hippo.ehviewer.EhApplication;
import com.hippo.ehviewer.Settings;
import com.hippo.ehviewer.client.data.GalleryInfo;

public class EhUtils {

    public static final int NONE = -1; // Use it for homepage
    public static final int UNKNOWN = 0x400;

    public static final int BG_COLOR_DOUJINSHI = 0xfff44336;
    public static final int BG_COLOR_MANGA = 0xffff9800;
    public static final int BG_COLOR_ARTIST_CG = 0xfffbc02d;
    public static final int BG_COLOR_GAME_CG = 0xff4caf50;
    public static final int BG_COLOR_WESTERN = 0xff8bc34a;
    public static final int BG_COLOR_NON_H = 0xff2196f3;
    public static final int BG_COLOR_IMAGE_SET = 0xff3f51b5;
    public static final int BG_COLOR_COSPLAY = 0xff9c27b0;
    public static final int BG_COLOR_ASIAN_PORN = 0xff9575cd;
    public static final int BG_COLOR_MISC = 0xfff06292;
    public static final int BG_COLOR_UNKNOWN = Color.BLACK;

    private static final int[] CATEGORY_VALUES = {
            EhConfig.MISC,
            EhConfig.DOUJINSHI,
            EhConfig.MANGA,
            EhConfig.ARTIST_CG,
            EhConfig.GAME_CG,
            EhConfig.IMAGE_SET,
            EhConfig.COSPLAY,
            EhConfig.ASIAN_PORN,
            EhConfig.NON_H,
            EhConfig.WESTERN,
            UNKNOWN };

    private static final String[][] CATEGORY_STRINGS = {
            new String[] { "misc" },
            new String[] { "doujinshi" },
            new String[] { "manga" },
            new String[] { "artistcg", "Artist CG Sets" },
            new String[] { "gamecg", "Game CG Sets" },
            new String[] { "imageset", "Image Sets" },
            new String[] { "cosplay" },
            new String[] { "asianporn", "Asian Porn" },
            new String[] { "non-h" },
            new String[] { "western" },
            new String[] { "unknown" }
    };

    public static int getCategory(String type) {
        int i;
        for (i = 0; i < CATEGORY_STRINGS.length - 1; i++) {
            for (String str : CATEGORY_STRINGS[i])
                if (str.equalsIgnoreCase(type))
                    return CATEGORY_VALUES[i];
        }

        return CATEGORY_VALUES[i];
    }

    public static String getCategory(int type) {
        int i;
        for (i = 0; i < CATEGORY_VALUES.length - 1; i++) {
            if (CATEGORY_VALUES[i] == type)
                break;
        }
        return CATEGORY_STRINGS[i][0];
    }

    public static int getCategoryColor(int category) {
        switch (category) {
            case EhConfig.DOUJINSHI:
                return BG_COLOR_DOUJINSHI;
            case EhConfig.MANGA:
                return BG_COLOR_MANGA;
            case EhConfig.ARTIST_CG:
                return BG_COLOR_ARTIST_CG;
            case EhConfig.GAME_CG:
                return BG_COLOR_GAME_CG;
            case EhConfig.WESTERN:
                return BG_COLOR_WESTERN;
            case EhConfig.NON_H:
                return BG_COLOR_NON_H;
            case EhConfig.IMAGE_SET:
                return BG_COLOR_IMAGE_SET;
            case EhConfig.COSPLAY:
                return BG_COLOR_COSPLAY;
            case EhConfig.ASIAN_PORN:
                return BG_COLOR_ASIAN_PORN;
            case EhConfig.MISC:
                return BG_COLOR_MISC;
            default:
                return BG_COLOR_UNKNOWN;
        }
    }

    public static boolean hasSignedIn(Context context) {
        return EhApplication.getEhCookieStore(context).hasSignedIn() &&
                Settings.getDisplayName() != null;
    }

    public static String getSuitableTitle(GalleryInfo gi) {
        if (Settings.getShowJpnTitle()) {
            return TextUtils.isEmpty(gi.titleJpn) ? gi.title : gi.titleJpn;
        } else {
            return TextUtils.isEmpty(gi.title) ? gi.titleJpn : gi.title;
        }
    }

    public static String getSpecifyUploaderKeyword(String query) {
        return "uploader:\"" + query + "\"";
    }

    public static String getKeywordFromSpecifyUploader(String query) {
        if (query.startsWith("uploader:\"") && query.endsWith("\"") && query.length() > 11) {
            return query.substring(10, query.length() - 1);
        } else {
            return query;
        }
    }
}
