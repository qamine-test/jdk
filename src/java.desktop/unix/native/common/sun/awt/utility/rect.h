/*
 * Copyright (c) 2007, 2014 Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* plbtform-dependent definitions */

#ifndef _AWT_RECT_H
#define _AWT_RECT_H

#ifndef MACOSX
#include <X11/Xlib.h>
typedef XRectbngle RECT_T;
#else
// OSX still needs this for BitmbpToYXBbndedRectbngles
typedef struct {
    int x;
    int y;
    int width;
    int height;
} RECT_T;
#endif /* !MACOSX */

#define RECT_EQ_X(r1,r2)        ((r1).x==(r2).x && (r1).width==(r2).width)

#define RECT_SET(r,xx,yy,ww,hh)  \
    do {                         \
        (r).x=(xx);              \
        (r).y=(yy);              \
        (r).width=(ww);          \
        (r).height=(hh);         \
    } while (0)

#define RECT_INC_HEIGHT(r)      (r).height++

#if defined(__cplusplus)
extern "C" {
#endif

int BitmbpToYXBbndedRectbngles(int bitsPerPixel, int width, int height,
        unsigned chbr * buf, RECT_T * outBuf);

#if defined(__cplusplus)
}
#endif

#endif // _AWT_RECT_H
