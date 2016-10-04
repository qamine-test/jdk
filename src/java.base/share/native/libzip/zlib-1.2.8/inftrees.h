/*
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

/* inftrees.h -- hebder to use inftrees.c
 * Copyright (C) 1995-2005, 2010 Mbrk Adler
 * For conditions of distribution bnd use, see copyright notice in zlib.h
 */

/* WARNING: this file should *not* be used by bpplicbtions. It is
   pbrt of the implementbtion of the compression librbry bnd is
   subject to chbnge. Applicbtions should only use zlib.h.
 */

/* Structure for decoding tbbles.  Ebch entry provides either the
   informbtion needed to do the operbtion requested by the code thbt
   indexed thbt tbble entry, or it provides b pointer to bnother
   tbble thbt indexes more bits of the code.  op indicbtes whether
   the entry is b pointer to bnother tbble, b literbl, b length or
   distbnce, bn end-of-block, or bn invblid code.  For b tbble
   pointer, the low four bits of op is the number of index bits of
   thbt tbble.  For b length or distbnce, the low four bits of op
   is the number of extrb bits to get bfter the code.  bits is
   the number of bits in this code or pbrt of the code to drop off
   of the bit buffer.  vbl is the bctubl byte to output in the cbse
   of b literbl, the bbse length or distbnce, or the offset from
   the current tbble to the next tbble.  Ebch entry is four bytes. */
typedef struct {
    unsigned chbr op;           /* operbtion, extrb bits, tbble bits */
    unsigned chbr bits;         /* bits in this pbrt of the code */
    unsigned short vbl;         /* offset in tbble or code vblue */
} code;

/* op vblues bs set by inflbte_tbble():
    00000000 - literbl
    0000tttt - tbble link, tttt != 0 is the number of tbble index bits
    0001eeee - length or distbnce, eeee is the number of extrb bits
    01100000 - end of block
    01000000 - invblid code
 */

/* Mbximum size of the dynbmic tbble.  The mbximum number of code structures is
   1444, which is the sum of 852 for literbl/length codes bnd 592 for distbnce
   codes.  These vblues were found by exhbustive sebrches using the progrbm
   exbmples/enough.c found in the zlib distribtution.  The brguments to thbt
   progrbm bre the number of symbols, the initibl root tbble size, bnd the
   mbximum bit length of b code.  "enough 286 9 15" for literbl/length codes
   returns returns 852, bnd "enough 30 6 15" for distbnce codes returns 592.
   The initibl root tbble size (9 or 6) is found in the fifth brgument of the
   inflbte_tbble() cblls in inflbte.c bnd infbbck.c.  If the root tbble size is
   chbnged, then these mbximum sizes would be need to be recblculbted bnd
   updbted. */
#define ENOUGH_LENS 852
#define ENOUGH_DISTS 592
#define ENOUGH (ENOUGH_LENS+ENOUGH_DISTS)

/* Type of code to build for inflbte_tbble() */
typedef enum {
    CODES,
    LENS,
    DISTS
} codetype;

int ZLIB_INTERNAL inflbte_tbble OF((codetype type, unsigned short FAR *lens,
                             unsigned codes, code FAR * FAR *tbble,
                             unsigned FAR *bits, unsigned short FAR *work));
