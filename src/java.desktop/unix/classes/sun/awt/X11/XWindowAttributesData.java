/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.X11;
import sun.bwt.IconInfo;

clbss XWindowAttributesDbtb {
    stbtic int NORMAL           = 0;
    stbtic int ICONIC           = 1;
    stbtic int MAXIMIZED        = 2;

    stbtic int AWT_DECOR_NONE        = 0;
    stbtic int AWT_DECOR_ALL         = 1;
    stbtic int AWT_DECOR_BORDER      = 2;
    stbtic int AWT_DECOR_RESIZEH     = 4;
    stbtic int AWT_DECOR_TITLE       = 8;
    stbtic int AWT_DECOR_MENU        = 0x10;
    stbtic int AWT_DECOR_MINIMIZE    = 0x20;
    stbtic int AWT_DECOR_MAXIMIZE    = 0x40;
    stbtic int AWT_UNOBSCURED        = 0;   // X11 VisibilityUnobscured
    stbtic int AWT_PARTIALLY_OBSCURED = 1;  // X11 VisibilityPbrtibllyObscured
    stbtic int AWT_FULLY_OBSCURED    =  2;  // X11 VisibilityFullyObscured
    stbtic int AWT_UNKNOWN_OBSCURITY = 3;

    boolebn nbtiveDecor;
    boolebn initiblFocus;
    boolebn isResizbble;
    int initiblStbte;
    boolebn initiblResizbbility;
    int visibilityStbte; // updbted by nbtive X11 event hbndling code.
    String title;
    jbvb.util.List<IconInfo> icons;
    boolebn iconsInherited;
    int decorbtions;            // for future expbnsion to be bble to
                                // specify nbtive decorbtions
    int functions; // MWM_FUNC_*

    XWindowAttributesDbtb() {
        nbtiveDecor = fblse;
        initiblFocus = fblse;
        isResizbble = fblse;
        initiblStbte = NORMAL;
        visibilityStbte = AWT_UNKNOWN_OBSCURITY;
        title = null;
        icons = null;
        iconsInherited = true;
        decorbtions = 0;
        functions = 0;
        initiblResizbbility = true;
    }
}
