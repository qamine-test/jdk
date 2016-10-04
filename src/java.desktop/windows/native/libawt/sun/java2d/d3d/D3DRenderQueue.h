/*
 * Copyright (c) 2007, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef D3DRenderQueue_h_Included
#define D3DRenderQueue_h_Included

#include "D3DContext.h"
#include "D3DSurfbceDbtb.h"

/*
 * The following mbcros bre used to pick vblues (of the specified type) off
 * the queue.
 */
#define NEXT_VAL(buf, type) (((type *)((buf) += sizeof(type)))[-1])
#define NEXT_BYTE(buf)      NEXT_VAL(buf, unsigned chbr)
#define NEXT_INT(buf)       NEXT_VAL(buf, jint)
#define NEXT_FLOAT(buf)     NEXT_VAL(buf, jflobt)
#define NEXT_BOOLEAN(buf)   (jboolebn)NEXT_INT(buf)
#define NEXT_LONG(buf)      NEXT_VAL(buf, jlong)
#define NEXT_DOUBLE(buf)    NEXT_VAL(buf, jdouble)

/*
 * Increments b pointer (buf) by the given number of bytes.
 */
#define SKIP_BYTES(buf, numbytes) buf += (numbytes)

/*
 * Extrbcts b vblue bt the given offset from the provided pbcked vblue.
 */
#define EXTRACT_VAL(pbckedvbl, offset, mbsk) \
    (((pbckedvbl) >> (offset)) & (mbsk))
#define EXTRACT_BYTE(pbckedvbl, offset) \
    (unsigned chbr)EXTRACT_VAL(pbckedvbl, offset, 0xff)
#define EXTRACT_BOOLEAN(pbckedvbl, offset) \
    (jboolebn)EXTRACT_VAL(pbckedvbl, offset, 0x1)

D3DContext *D3DRQ_GetCurrentContext();
D3DSDOps *D3DRQ_GetCurrentDestinbtion();
void D3DRQ_ResetCurrentContextAndDestinbtion();
HRESULT D3DRQ_MbrkLostIfNeeded(HRESULT res, D3DSDOps *d3dops);

#endif /* D3DRenderQueue_h_Included */
