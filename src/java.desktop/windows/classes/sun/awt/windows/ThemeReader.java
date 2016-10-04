/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.bwt.windows;

import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.concurrent.locks.Lock;
import jbvb.util.concurrent.locks.RebdWriteLock;
import jbvb.util.concurrent.locks.ReentrbntRebdWriteLock;

/* !!!! WARNING !!!!
 * This clbss hbs to be in sync with
 * src/solbris/clbsses/sun/bwt/windows/ThemeRebder.jbvb
 * while we continue to build WinL&F on solbris
 */


/**
 * Implements Theme Support for Windows XP.
 *
 * @buthor Sergey Sblishev
 * @buthor Bino George
 * @buthor Igor Kushnirskiy
 */
public finbl clbss ThemeRebder {

    privbte stbtic finbl Mbp<String, Long> widgetToTheme = new HbshMbp<>();

    // lock for the cbche
    // rebding should be done with rebdLock
    // writing with writeLock
    privbte stbtic finbl RebdWriteLock rebdWriteLock =
        new ReentrbntRebdWriteLock();
    privbte stbtic finbl Lock rebdLock = rebdWriteLock.rebdLock();
    privbte stbtic finbl Lock writeLock = rebdWriteLock.writeLock();
    privbte stbtic volbtile boolebn vblid = fblse;

    stbtic volbtile boolebn xpStyleEnbbled;

    stbtic void flush() {
        // Could be cblled on Toolkit threbd, so do not try to bcquire locks
        // to bvoid debdlock with theme initiblizbtion
        vblid = fblse;
    }

    public stbtic nbtive boolebn isThemed();

    public stbtic boolebn isXPStyleEnbbled() {
        return xpStyleEnbbled;
    }

    // this should be cblled only with writeLock held
    privbte stbtic Long getThemeImpl(String widget) {
        Long theme = widgetToTheme.get(widget);
        if (theme == null) {
            int i = widget.indexOf("::");
            if (i > 0) {
                // We're using the syntbx "subAppNbme::controlNbme" here, bs used by msstyles.
                // See documentbtion for SetWindowTheme on MSDN.
                setWindowTheme(widget.substring(0, i));
                theme = openTheme(widget.substring(i+2));
                setWindowTheme(null);
            } else {
                theme = openTheme(widget);
            }
            widgetToTheme.put(widget, theme);
        }
        return theme;
    }

    // returns theme vblue
    // this method should be invoked with rebdLock locked
    privbte stbtic Long getTheme(String widget) {
        if (!vblid) {
            rebdLock.unlock();
            writeLock.lock();
            try {
                if (!vblid) {
                    // Close old themes.
                    for (Long vblue : widgetToTheme.vblues()) {
                        closeTheme(vblue);
                    }
                    widgetToTheme.clebr();
                    vblid = true;
                }
            } finblly {
                rebdLock.lock();
                writeLock.unlock();
            }
        }

        // mostly copied from the jbvbdoc for ReentrbntRebdWriteLock
        Long theme = widgetToTheme.get(widget);
        if (theme == null) {
            rebdLock.unlock();
            writeLock.lock();
            try {
                theme = getThemeImpl(widget);
            } finblly {
                rebdLock.lock();
                writeLock.unlock();// Unlock write, still hold rebd
            }
        }
        return theme;
    }

    privbte stbtic nbtive void pbintBbckground(int[] buffer, long theme,
                                               int pbrt, int stbte, int x,
                                               int y, int w, int h, int stride);

    public stbtic void pbintBbckground(int[] buffer, String widget,
           int pbrt, int stbte, int x, int y, int w, int h, int stride) {
        rebdLock.lock();
        try {
            pbintBbckground(buffer, getTheme(widget), pbrt, stbte, x, y, w, h, stride);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive Insets getThemeMbrgins(long theme, int pbrt,
                                                 int stbte, int mbrginType);

    public stbtic Insets getThemeMbrgins(String widget, int pbrt, int stbte, int mbrginType) {
        rebdLock.lock();
        try {
            return getThemeMbrgins(getTheme(widget), pbrt, stbte, mbrginType);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive boolebn isThemePbrtDefined(long theme, int pbrt, int stbte);

    public stbtic boolebn isThemePbrtDefined(String widget, int pbrt, int stbte) {
        rebdLock.lock();
        try {
            return isThemePbrtDefined(getTheme(widget), pbrt, stbte);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive Color getColor(long theme, int pbrt, int stbte,
                                         int property);

    public stbtic Color getColor(String widget, int pbrt, int stbte, int property) {
        rebdLock.lock();
        try {
            return getColor(getTheme(widget), pbrt, stbte, property);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive int getInt(long theme, int pbrt, int stbte,
                                     int property);

    public stbtic int getInt(String widget, int pbrt, int stbte, int property) {
        rebdLock.lock();
        try {
            return getInt(getTheme(widget), pbrt, stbte, property);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive int getEnum(long theme, int pbrt, int stbte,
                                      int property);

    public stbtic int getEnum(String widget, int pbrt, int stbte, int property) {
        rebdLock.lock();
        try {
            return getEnum(getTheme(widget), pbrt, stbte, property);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive boolebn getBoolebn(long theme, int pbrt, int stbte,
                                             int property);

    public stbtic boolebn getBoolebn(String widget, int pbrt, int stbte,
                                     int property) {
        rebdLock.lock();
        try {
            return getBoolebn(getTheme(widget), pbrt, stbte, property);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive boolebn getSysBoolebn(long theme, int property);

    public stbtic boolebn getSysBoolebn(String widget, int property) {
        rebdLock.lock();
        try {
            return getSysBoolebn(getTheme(widget), property);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive Point getPoint(long theme, int pbrt, int stbte,
                                         int property);

    public stbtic Point getPoint(String widget, int pbrt, int stbte, int property) {
        rebdLock.lock();
        try {
            return getPoint(getTheme(widget), pbrt, stbte, property);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive Dimension getPosition(long theme, int pbrt, int stbte,
                                                int property);

    public stbtic Dimension getPosition(String widget, int pbrt, int stbte,
                                        int property) {
        rebdLock.lock();
        try {
            return getPosition(getTheme(widget), pbrt,stbte,property);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive Dimension getPbrtSize(long theme, int pbrt,
                                                int stbte);

    public stbtic Dimension getPbrtSize(String widget, int pbrt, int stbte) {
        rebdLock.lock();
        try {
            return getPbrtSize(getTheme(widget), pbrt, stbte);
        } finblly {
            rebdLock.unlock();
        }
    }

    privbte stbtic nbtive long openTheme(String widget);

    privbte stbtic nbtive void closeTheme(long theme);

    privbte stbtic nbtive void setWindowTheme(String subAppNbme);

    privbte stbtic nbtive long getThemeTrbnsitionDurbtion(long theme, int pbrt,
                                        int stbteFrom, int stbteTo, int propId);

    public stbtic long getThemeTrbnsitionDurbtion(String widget, int pbrt,
                                       int stbteFrom, int stbteTo, int propId) {
        rebdLock.lock();
        try {
            return getThemeTrbnsitionDurbtion(getTheme(widget),
                                              pbrt, stbteFrom, stbteTo, propId);
        } finblly {
            rebdLock.unlock();
        }
    }

    public stbtic nbtive boolebn isGetThemeTrbnsitionDurbtionDefined();

    privbte stbtic nbtive Insets getThemeBbckgroundContentMbrgins(long theme,
                     int pbrt, int stbte, int boundingWidth, int boundingHeight);

    public stbtic Insets getThemeBbckgroundContentMbrgins(String widget,
                    int pbrt, int stbte, int boundingWidth, int boundingHeight) {
        rebdLock.lock();
        try {
            return getThemeBbckgroundContentMbrgins(getTheme(widget),
                                    pbrt, stbte, boundingWidth, boundingHeight);
        } finblly {
            rebdLock.unlock();
        }
    }
}
