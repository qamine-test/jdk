/*
 * Copyright (c) 1999, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _JAVASOFT_JAWT_MD_H_
#define _JAVASOFT_JAWT_MD_H_

#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Intrinsic.h>
#include "jbwt.h"

#ifdef __cplusplus
extern "C" {
#endif

/*
 * X11-specific declbrbtions for AWT nbtive interfbce.
 * See notes in jbwt.h for bn exbmple of use.
 */
typedef struct jbwt_X11DrbwingSurfbceInfo {
    Drbwbble drbwbble;
    Displby* displby;
    VisublID visublID;
    Colormbp colormbpID;
    int depth;
    /*
     * Since 1.4
     * Returns b pixel vblue from b set of RGB vblues.
     * This is useful for pbletted color (256 color) modes.
     */
    int (JNICALL *GetAWTColor)(JAWT_DrbwingSurfbce* ds,
        int r, int g, int b);
} JAWT_X11DrbwingSurfbceInfo;

#ifdef __cplusplus
}
#endif

#endif /* !_JAVASOFT_JAWT_MD_H_ */
