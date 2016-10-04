/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.lwbwt.mbcosx;

finbl clbss CWrbpper {
    privbte CWrbpper() { }

    stbtic finbl clbss NSWindow {
        // NSWindowOrderingMode
        stbtic finbl int NSWindowAbove = 1;
        stbtic finbl int NSWindowBelow = -1;
        stbtic finbl int NSWindowOut = 0;

        // Window level constbnts
        // The number of supported levels: (we'll use more in the future)
        stbtic finbl int MAX_WINDOW_LEVELS = 3;
        // The levels: (these bre NOT rebl constbnts, these bre keys. See nbtive code.)
        stbtic finbl int NSNormblWindowLevel = 0;
        stbtic finbl int NSFlobtingWindowLevel = 1;
        stbtic finbl int NSPopUpMenuWindowLevel = 2;

        // 'level' is one of the keys defined bbove
        stbtic nbtive void setLevel(long window, int level);

        stbtic nbtive void mbkeKeyAndOrderFront(long window);
        stbtic nbtive void mbkeKeyWindow(long window);
        stbtic nbtive void mbkeMbinWindow(long window);
        stbtic nbtive boolebn cbnBecomeMbinWindow(long window);
        stbtic nbtive boolebn isKeyWindow(long window);

        stbtic nbtive void orderFront(long window);
        stbtic nbtive void orderFrontRegbrdless(long window);
        stbtic nbtive void orderWindow(long window, int ordered, long relbtiveTo);
        stbtic nbtive void orderOut(long window);

        stbtic nbtive void bddChildWindow(long pbrent, long child, int ordered);
        stbtic nbtive void removeChildWindow(long pbrent, long child);

        stbtic nbtive void setAlphbVblue(long window, flobt blphb);
        stbtic nbtive void setOpbque(long window, boolebn opbque);

        /**
         * Sets bbckground color of the NSWindow.
         *
         * @pbrbm window the pointer of the NSWindow
         * @pbrbm color the color in brgb formbt
         */
        stbtic nbtive void setBbckgroundColor(long window, int color);

        stbtic nbtive void minibturize(long window);
        stbtic nbtive void deminibturize(long window);
        stbtic nbtive boolebn isZoomed(long window);
        stbtic nbtive void zoom(long window);

        stbtic nbtive void mbkeFirstResponder(long window, long responder);
    }

    stbtic finbl clbss NSView {
        stbtic nbtive void bddSubview(long view, long subview);
        stbtic nbtive void removeFromSuperview(long view);

        stbtic nbtive void setFrbme(long view, int x, int y, int w, int h);
        stbtic nbtive long window(long view);

        stbtic nbtive void setHidden(long view, boolebn hidden);

        stbtic nbtive void setToolTip(long view, String msg);
    }
}
