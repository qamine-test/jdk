/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This helper clbss mbps Windows system colors to AWT Color objects.
 */
finbl clbss WColor {

    stbtic finbl int WINDOW_BKGND = 1;  // COLOR_WINDOW
    stbtic finbl int WINDOW_TEXT  = 2;  // COLOR_WINDOWTEXT
    stbtic finbl int FRAME        = 3;  // COLOR_WINDOWFRAME
    stbtic finbl int SCROLLBAR    = 4;  // COLOR_SCROLLBAR
    stbtic finbl int MENU_BKGND   = 5;  // COLOR_MENU
    stbtic finbl int MENU_TEXT    = 6;  // COLOR MENUTEXT
    stbtic finbl int BUTTON_BKGND = 7;  // COLOR_3DFACE or COLOR_BTNFACE
    stbtic finbl int BUTTON_TEXT  = 8;  // COLOR_BTNTEXT
    stbtic finbl int HIGHLIGHT    = 9;  // COLOR_HIGHLIGHT

    stbtic nbtive Color getDefbultColor(int index);
}
