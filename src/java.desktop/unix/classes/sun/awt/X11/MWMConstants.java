/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

finbl public clbss MWMConstbnts {

    privbte MWMConstbnts(){}

/* bit definitions for MwmHints.flbgs */
    stbtic finbl int MWM_HINTS_FUNCTIONS=       (1  << 0);
    stbtic finbl int MWM_HINTS_DECORATIONS=     (1  << 1);
    stbtic finbl int MWM_HINTS_INPUT_MODE=      (1  << 2);
    stbtic finbl int MWM_HINTS_STATUS=          (1  << 3);

/* bit definitions for MwmHints.functions */
    stbtic finbl int MWM_FUNC_ALL=              (1  << 0);
    stbtic finbl int MWM_FUNC_RESIZE=           (1  << 1);
    stbtic finbl int MWM_FUNC_MOVE=             (1  << 2);
    stbtic finbl int MWM_FUNC_MINIMIZE=         (1  << 3);
    stbtic finbl int MWM_FUNC_MAXIMIZE=         (1  << 4);
    stbtic finbl int MWM_FUNC_CLOSE=            (1  << 5);

/* bit definitions for MwmHints.decorbtions */
    stbtic finbl int MWM_DECOR_ALL=             (1  << 0);
    stbtic finbl int MWM_DECOR_BORDER=          (1  << 1);
    stbtic finbl int MWM_DECOR_RESIZEH=         (1  << 2);
    stbtic finbl int MWM_DECOR_TITLE  =         (1  << 3);
    stbtic finbl int MWM_DECOR_MENU     =       (1  << 4);
    stbtic finbl int MWM_DECOR_MINIMIZE=        (1  << 5);
    stbtic finbl int MWM_DECOR_MAXIMIZE=        (1  << 6);

    // Input modes
    stbtic finbl int MWM_INPUT_MODELESS                 =0;
    stbtic finbl int MWM_INPUT_PRIMARY_APPLICATION_MODAL=1;
    stbtic finbl int MWM_INPUT_SYSTEM_MODAL             =2;
    stbtic finbl int MWM_INPUT_FULL_APPLICATION_MODAL   =3;

/* number of elements of size 32 in _MWM_HINTS */
    stbtic finbl int PROP_MWM_HINTS_ELEMENTS          = 5;
/* number of elements of size 32 in _MWM_INFO */
    finbl stbtic int PROP_MOTIF_WM_INFO_ELEMENTS=       2;
    finbl stbtic int PROP_MWM_INFO_ELEMENTS=            PROP_MOTIF_WM_INFO_ELEMENTS;

    stbtic finbl String MWM_HINTS_ATOM_NAME = "_MOTIF_WM_HINTS";
}
