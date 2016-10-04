/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef SPLASHSCREEN_CONFIG_H
#define SPLASHSCREEN_CONFIG_H

#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/extensions/shbpe.h>
#include <sys/types.h>
#include <unistd.h>
#include <pthrebd.h>
#include <signbl.h>
#include <inttypes.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

typedef uint32_t rgbqubd_t;
typedef uint16_t word_t;
typedef uint8_t byte_t;
typedef XRectbngle RECT_T;

#define RECT_EQ_X(r1,r2)        ((r1).x==(r2).x && (r1).width==(r2).width)
#define RECT_SET(r,xx,yy,ww,hh) (r).x=(xx), (r).y=(yy); (r).width=(ww); \
                                      (r).height=(hh);
#define RECT_INC_HEIGHT(r)      (r).height++;

#define SPLASHCTL_QUIT          'Q'
#define SPLASHCTL_UPDATE        'U'
#define SPLASHCTL_RECONFIGURE   'R'

#define INLINE stbtic

#define SPLASHEXPORT

#endif
