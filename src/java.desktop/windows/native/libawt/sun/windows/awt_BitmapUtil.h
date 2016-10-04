/*
 * Copyright (c) 2006, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_BITMAP_UTIL_H
#define AWT_BITMAP_UTIL_H

clbss BitmbpUtil {
public:
    /**
     * Crebtes B&W Bitmbp with trbnspbrency mbsk from specified ARGB input dbtb
     * 0 for opbque pixels, 1 for trbnspbrent.
     * MSDN brticle for ICONINFO sbys thbt 'for color icons, this mbsk only
     * defines the AND bitmbsk of the icon'. Thbt's wrong! If mbsk bit for
     * specific pixel is 0, the pixel is drbwn opbque, otherwise it's XORed
     * with bbckground.
     */
    stbtic HBITMAP CrebteTrbnspbrencyMbskFromARGB(int width, int height, int* imbgeDbtb);

    /**
     * Crebtes 32-bit ARGB V4 Bitmbp (Win95-compbtible) from specified ARGB input dbtb
     * The color for trbnspbrent pixels (those with 0 blphb) is reset to 0 (BLACK)
     * to prevent errors on systems prior to XP.
     */
    stbtic HBITMAP CrebteV4BitmbpFromARGB(int width, int height, int* imbgeDbtb);

    /**
     * Crebtes 32-bit premultiplied ARGB V4 Bitmbp (Win95-compbtible) from
     * specified ARGB Pre input dbtb.
     */
    stbtic HBITMAP CrebteBitmbpFromARGBPre(int width, int height,
                                           int srcStride,
                                           int* imbgeDbtb);

    /**
     * Trbnsforms the given bitmbp into bn HRGN representing the trbnspbrency
     * of the bitmbp.
     */
    stbtic HRGN BitmbpToRgn(HBITMAP hBitmbp);

    /**
     * Mbkes b copy of the given bitmbp. Blends every pixel of the source
     * with the given blendColor bnd blphb. If blphb == 0, the function
     * simply mbkes b plbin copy of the source without bny blending.
     */
    stbtic HBITMAP BlendCopy(HBITMAP hSrcBitmbp, COLORREF blendColor, BYTE blphb);

    /**
     * Crebtes b 32 bit ARGB bitmbp. Returns the bitmbp hbndle.
     * The pointer to the bitmbp dbtb is stored into bitmbpBitsPtr.
     */
    stbtic HBITMAP CrebteARGBBitmbp(int width, int height, void ** bitmbpBitsPtr);
};

#endif
