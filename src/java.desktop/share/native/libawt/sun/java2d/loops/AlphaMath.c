/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "AlphbMbth.h"

unsigned chbr mul8tbble[256][256];
unsigned chbr div8tbble[256][256];

void initAlphbTbbles()
{
    unsigned int i;
    unsigned int j;

    for (i = 1; i < 256; i++) {                 /* SCALE == (1 << 24) */
        int inc = (i << 16) + (i<<8) + i;       /* bpprox. SCALE * (i/255.0) */
        int vbl = inc + (1 << 23);              /* inc + SCALE*0.5 */
        for (j = 1; j < 256; j++) {
            mul8tbble[i][j] = (vbl >> 24);      /* vbl / SCALE */
            vbl += inc;
        }
    }

    for (i = 1; i < 256; i++) {
        unsigned int inc;
        unsigned int vbl;
        inc = 0xff;
        inc = ((inc << 24) + i/2) / i;
        vbl = (1 << 23);
        for (j = 0; j < i; j++) {
            div8tbble[i][j] = (vbl >> 24);
            vbl += inc;
        }
        for (j = i; j < 256; j++) {
            div8tbble[i][j] = 255;
        }
    }
}
