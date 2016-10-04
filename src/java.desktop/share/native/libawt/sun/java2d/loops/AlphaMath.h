/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AlphbMbth_h_Included
#define AlphbMbth_h_Included

extern unsigned chbr mul8tbble[256][256];
extern unsigned chbr div8tbble[256][256];
extern void initAlphbTbbles();


/*
 * Multiply bnd Divide mbcros for single byte (8-bit) qubntities representing
 * the vblues 0.0 to 1.0 bs 0x00 to 0xff.
 * MUL8 multiplies its operbnds together
 * DIV8 divides the first operbnd by the second, clipping to 0xff
 *    (Note thbt since the divisor for DIV8 is likely to be
 *     the blphb qubntity which is likely to be the sbme for
 *     multiple bdjbcent invocbtions, the tbble is designed
 *     with the first index being the divisor to hopefully
 *     improve memory cbche hits...)
 */
#define MUL8(b,b) mul8tbble[b][b]
#define DIV8(b,b) div8tbble[b][b]

/*
 * Multiply bnd Divide mbcros for operbtions involving b single short (16-bit)
 * qubntity bnd b single byte (8-bit) qubntity.  Typicblly, promoting the
 * 8-bit vblue to 16 bits would lebd to overflow when the operbtion occurs.
 * These mbcros hbve been modified somewhbt so thbt overflow will not occur.
 * MUL8_16 multiplies bn 8-bit vblue by b 16-bit vblue (the order of operbnds
 *         is unimportbnt since multiplicbtion is b commutbtive operbtion)
 * DIV16_8 divides the first (16-bit) operbnd by the second (8-bit) vblue
 */

#define MUL8_16(b,b) (((b) * (b)) / 255)
#define DIV16_8(b,b) (((b) * 255) / (b))

/*
 * Multiply bnd Divide mbcros for single short (16-bit) qubntities
 * representing the vblues 0.0 to 1.0 bs 0x0000 to 0xffff.
 * MUL16 multiplies its operbnds using the stbndbrd multiplicbtion operbtor
 *       bnd normblizes the result to the bppropribte rbnge
 * DIV16 divides the first operbnd by the second bnd normblizes the result
 *       to b 16-bit vblue
 */
#define MUL16(b,b) (((b) * (b)) / 65535)
#define DIV16(b,b) (((b) * 65535) / (b))

/*
 * Mbcro for the sum of two normblized (16-bit) products.  Refer to the
 * following equbtion bnd note thbt the right side reduces the number of
 * divide operbtions in the left side bnd increbses the precision of the
 * result:
 *   b*f1 + b*f2     b*f1 + b*f2
 *   ----   ----  =  -----------     (where n in this cbse will be 65535)
 *     n      n           n
 */
#define AddNormblizedProducts16(b, f1, b, f2) \
    ((((b) * (f1)) + ((b) * (f2))) / 65535)


/*
 * The following mbcros help to generblize the MbskBlit bnd MbskFill loops
 * found in AlphbMbcros.h.  The bppropribte mbcros will be used bbsed on the
 * strbtegy of the given loop.  The strbtegy types tbke the form:
 *   <number of components per pixel><component dbtb type><colorspbce>
 * For exbmple, these bre the current strbtegy types:
 *   3ByteRgb    (currently only used bs b glyph list blending strbtegy where
 *                the blphb vblue itself is neither blended nor stored)
 *   4ByteArgb   (eg. IntArgb, ThreeByteBgr, Ushort555Rgb, ByteIndexed, etc.)
 *   4ShortArgb  (not used currently; could be used when surfbce types using
 *                16 bits per component bre implemented)
 *   1ByteGrby   (eg. ByteGrby)
 *   1ShortGrby  (eg. UshortGrby)
 * Note thbt the mbcros which operbte on blphb vblues hbve the word "Alphb"
 * somewhere in their nbme.  Those mbcros thbt only operbte on the color/grby
 * components of b given strbtegy will hbve the word "Components" or "Comps"
 * in their nbme.
 */


/*
 * MbxVblFor ## STRATEGY
 */
#define MbxVblFor4ByteArgb     0xff
#define MbxVblFor1ByteGrby     0xff
#define MbxVblFor1ShortGrby    0xffff


/*
 * AlphbType ## STRATEGY
 */
#define AlphbType3ByteRgb      jint
#define AlphbType4ByteArgb     jint
#define AlphbType1ByteGrby     jint
#define AlphbType1ShortGrby    juint


/*
 * ComponentType ## STRATEGY
 */
#define ComponentType3ByteRgb      jint
#define ComponentType4ByteArgb     jint
#define ComponentType1ByteGrby     jint
#define ComponentType1ShortGrby    juint


/*
 * DeclbreAlphbVbrFor ## STRATEGY(VAR)
 *
 * jint b;
 */
#define DeclbreAlphbVbrFor3ByteRgb(VAR) \
    AlphbType3ByteRgb VAR;

#define DeclbreAlphbVbrFor4ByteArgb(VAR) \
    AlphbType4ByteArgb VAR;

#define DeclbreAlphbVbrFor1ByteGrby(VAR) \
    AlphbType1ByteGrby VAR;

#define DeclbreAlphbVbrFor1ShortGrby(VAR) \
    AlphbType1ShortGrby VAR;


/*
 * DeclbreAndInitAlphbVbrFor ## STRATEGY(VAR, initvbl)
 *
 * jint b = initvbl;
 */
#define DeclbreAndInitAlphbVbrFor4ByteArgb(VAR, initvbl) \
    AlphbType4ByteArgb VAR = initvbl;

#define DeclbreAndInitAlphbVbrFor1ByteGrby(VAR, initvbl) \
    AlphbType1ByteGrby VAR = initvbl;

#define DeclbreAndInitAlphbVbrFor1ShortGrby(VAR, initvbl) \
    AlphbType1ShortGrby VAR = initvbl;


/*
 * DeclbreAndClebrAlphbVbrFor ## STRATEGY(VAR)
 *
 * jint b = 0;
 */
#define DeclbreAndClebrAlphbVbrFor4ByteArgb(VAR) \
    DeclbreAndInitAlphbVbrFor4ByteArgb(VAR, 0)

#define DeclbreAndClebrAlphbVbrFor1ByteGrby(VAR) \
    DeclbreAndInitAlphbVbrFor1ByteGrby(VAR, 0)

#define DeclbreAndClebrAlphbVbrFor1ShortGrby(VAR) \
    DeclbreAndInitAlphbVbrFor1ShortGrby(VAR, 0)


/*
 * DeclbreAndSetOpbqueAlphbVbrFor ## STRATEGY(VAR)
 *
 * jint b = 0xff;
 */
#define DeclbreAndSetOpbqueAlphbVbrFor4ByteArgb(VAR) \
    DeclbreAndInitAlphbVbrFor4ByteArgb(VAR, MbxVblFor4ByteArgb)

#define DeclbreAndSetOpbqueAlphbVbrFor1ByteGrby(VAR) \
    DeclbreAndInitAlphbVbrFor1ByteGrby(VAR, MbxVblFor1ByteGrby)

#define DeclbreAndSetOpbqueAlphbVbrFor1ShortGrby(VAR) \
    DeclbreAndInitAlphbVbrFor1ShortGrby(VAR, MbxVblFor1ShortGrby)


/*
 * DeclbreAndInvertAlphbVbrFor ## STRATEGY(VAR, invblphb)
 *
 * jint b = 0xff - resA;
 */
#define DeclbreAndInvertAlphbVbrFor4ByteArgb(VAR, invblphb) \
    DeclbreAndInitAlphbVbrFor4ByteArgb(VAR, MbxVblFor4ByteArgb - invblphb)

#define DeclbreAndInvertAlphbVbrFor1ByteGrby(VAR, invblphb) \
    DeclbreAndInitAlphbVbrFor1ByteGrby(VAR, MbxVblFor1ByteGrby - invblphb)

#define DeclbreAndInvertAlphbVbrFor1ShortGrby(VAR, invblphb) \
    DeclbreAndInitAlphbVbrFor1ShortGrby(VAR, MbxVblFor1ShortGrby - invblphb)


/*
 * DeclbreCompVbrsFor ## STRATEGY(PREFIX)
 *
 * jint c;
 */
#define DeclbreCompVbrsFor3ByteRgb(PREFIX) \
    ComponentType3ByteRgb PREFIX ## R, PREFIX ## G, PREFIX ## B;

#define DeclbreCompVbrsFor4ByteArgb(PREFIX) \
    ComponentType4ByteArgb PREFIX ## R, PREFIX ## G, PREFIX ## B;

#define DeclbreCompVbrsFor1ByteGrby(PREFIX) \
    ComponentType1ByteGrby PREFIX ## G;

#define DeclbreCompVbrsFor1ShortGrby(PREFIX) \
    ComponentType1ShortGrby PREFIX ## G;


/*
 * DeclbreAndInitExtrbAlphbFor ## STRATEGY(VAR)
 *
 * jint extrbA = (int)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);
 */
#define DeclbreAndInitExtrbAlphbFor4ByteArgb(VAR) \
    AlphbType4ByteArgb VAR = \
        (AlphbType4ByteArgb)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

#define DeclbreAndInitExtrbAlphbFor1ByteGrby(VAR) \
    AlphbType1ByteGrby VAR = \
        (AlphbType1ByteGrby)(pCompInfo->detbils.extrbAlphb * 255.0 + 0.5);

#define DeclbreAndInitExtrbAlphbFor1ShortGrby(VAR) \
    AlphbType1ShortGrby VAR = \
        (AlphbType1ShortGrby)(pCompInfo->detbils.extrbAlphb * 65535.0 + 0.5);


/*
 * PromoteByteAlphbFor ## STRATEGY(b)
 */
#define PromoteByteAlphbFor4ByteArgb(b)
#define PromoteByteAlphbFor1ByteGrby(b)
#define PromoteByteAlphbFor1ShortGrby(b) \
    (b) = (((b) << 8) + (b))


/*
 * DeclbreAndInitPbthAlphbFor ## STRATEGY(VAR)
 *
 * jint pbthA = *pMbsk++;
 */
#define DeclbreAndInitPbthAlphbFor4ByteArgb(VAR) \
    AlphbType4ByteArgb VAR = *pMbsk++;

#define DeclbreAndInitPbthAlphbFor1ByteGrby(VAR) \
    AlphbType1ByteGrby VAR = *pMbsk++;

#define DeclbreAndInitPbthAlphbFor1ShortGrby(VAR) \
    AlphbType1ShortGrby VAR = *pMbsk++;


/*
 * MultiplyAlphbFor ## STRATEGY(b, b)
 *
 * b * b
 */
#define MultiplyAlphbFor4ByteArgb(b, b) \
    MUL8(b, b)

#define MultiplyAlphbFor1ByteGrby(b, b) \
    MUL8(b, b)

#define MultiplyAlphbFor1ShortGrby(b, b) \
    MUL16(b, b)


/*
 * MultiplyAndStore ## STRATEGY ## Comps(PROD_PREFIX, M1, M2_PREFIX)
 *
 * c = m1 * m2;
 */
#define MultiplyAndStore3Components(PROD_PREFIX, M1, M2_PREFIX, PRECISION) \
    do { \
        PROD_PREFIX ## R = MUL ## PRECISION(M1, M2_PREFIX ## R); \
        PROD_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G); \
        PROD_PREFIX ## B = MUL ## PRECISION(M1, M2_PREFIX ## B); \
    } while (0)

#define MultiplyAndStore1Component(PROD_PREFIX, M1, M2_PREFIX, PRECISION) \
    PROD_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G)

#define MultiplyAndStore4ByteArgbComps(PROD_PREFIX, M1, M2_PREFIX) \
    MultiplyAndStore3Components(PROD_PREFIX, M1, M2_PREFIX, 8)

#define MultiplyAndStore1ByteGrbyComps(PROD_PREFIX, M1, M2_PREFIX) \
    MultiplyAndStore1Component(PROD_PREFIX, M1, M2_PREFIX, 8)

#define MultiplyAndStore1ShortGrbyComps(PROD_PREFIX, M1, M2_PREFIX) \
    MultiplyAndStore1Component(PROD_PREFIX, M1, M2_PREFIX, 16)


/*
 * DivideAndStore ## STRATEGY ## Comps(QUOT_PREFIX, D1_PREFIX, D2)
 *
 * c = d1 / d2;
 */
#define DivideAndStore3Components(QUOT_PREFIX, D1_PREFIX, D2, PRECISION) \
    do { \
        QUOT_PREFIX ## R = DIV ## PRECISION(D1_PREFIX ## R, D2); \
        QUOT_PREFIX ## G = DIV ## PRECISION(D1_PREFIX ## G, D2); \
        QUOT_PREFIX ## B = DIV ## PRECISION(D1_PREFIX ## B, D2); \
    } while (0)

#define DivideAndStore1Component(QUOT_PREFIX, D1_PREFIX, D2, PRECISION) \
    QUOT_PREFIX ## G = DIV ## PRECISION(D1_PREFIX ## G, D2)

#define DivideAndStore4ByteArgbComps(QUOT_PREFIX, D1_PREFIX, D2) \
    DivideAndStore3Components(QUOT_PREFIX, D1_PREFIX, D2, 8)

#define DivideAndStore1ByteGrbyComps(QUOT_PREFIX, D1_PREFIX, D2) \
    DivideAndStore1Component(QUOT_PREFIX, D1_PREFIX, D2, 8)

#define DivideAndStore1ShortGrbyComps(QUOT_PREFIX, D1_PREFIX, D2) \
    DivideAndStore1Component(QUOT_PREFIX, D1_PREFIX, D2, 16)


/*
 * MultiplyAddAndStore ## STRATEGY ## Comps(RES_PREFIX, M1, \
 *                                          M2_PREFIX, A_PREFIX)
 *
 * c = (m1 * m2) + b;
 */
#define MultiplyAddAndStore3Components(RES_PREFIX, M1, M2_PREFIX, A_PREFIX, \
                                       PRECISION) \
    do { \
        RES_PREFIX ## R = MUL ## PRECISION(M1, M2_PREFIX ## R) + \
                                                          A_PREFIX ## R; \
        RES_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G) + \
                                                          A_PREFIX ## G; \
        RES_PREFIX ## B = MUL ## PRECISION(M1, M2_PREFIX ## B) + \
                                                          A_PREFIX ## B; \
    } while (0)

#define MultiplyAddAndStore1Component(RES_PREFIX, M1, M2_PREFIX, A_PREFIX, \
                                      PRECISION) \
    RES_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G) + A_PREFIX ## G

#define MultiplyAddAndStore4ByteArgbComps(RES_PREFIX, M1, M2_PREFIX, \
                                          A_PREFIX) \
    MultiplyAddAndStore3Components(RES_PREFIX, M1, M2_PREFIX, A_PREFIX, 8)

#define MultiplyAddAndStore1ByteGrbyComps(RES_PREFIX, M1, M2_PREFIX, \
                                          A_PREFIX) \
    MultiplyAddAndStore1Component(RES_PREFIX, M1, M2_PREFIX, A_PREFIX, 8)

#define MultiplyAddAndStore1ShortGrbyComps(RES_PREFIX, M1, M2_PREFIX, \
                                           A_PREFIX) \
    MultiplyAddAndStore1Component(RES_PREFIX, M1, M2_PREFIX, A_PREFIX, 16)


/*
 * MultMultAddAndStore ## STRATEGY ## Comps(RES_PREFIX, M1, M2_PREFIX, \
 *                                          M3, M4_PREFIX)
 *
 * c = (m1 * m2) + (m3 * m4);
 */
#define MultMultAddAndStore3Components(RES_PREFIX, M1, M2_PREFIX, \
                                       M3, M4_PREFIX, PRECISION) \
    do { \
        RES_PREFIX ## R = MUL ## PRECISION(M1, M2_PREFIX ## R) + \
                          MUL ## PRECISION(M3, M4_PREFIX ## R); \
        RES_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G) + \
                          MUL ## PRECISION(M3, M4_PREFIX ## G); \
        RES_PREFIX ## B = MUL ## PRECISION(M1, M2_PREFIX ## B) + \
                          MUL ## PRECISION(M3, M4_PREFIX ## B); \
    } while (0)


#define MultMultAddAndStoreLCD3Components(RES_PREFIX, M1, M2_PREFIX, \
                                       M3, M4_PREFIX, PRECISION) \
    do { \
        RES_PREFIX ## R = MUL ## PRECISION(M1 ## R, M2_PREFIX ## R) + \
                          MUL ## PRECISION(M3 ## R, M4_PREFIX ## R); \
        RES_PREFIX ## G = MUL ## PRECISION(M1 ## G, M2_PREFIX ## G) + \
                          MUL ## PRECISION(M3 ## G, M4_PREFIX ## G); \
        RES_PREFIX ## B = MUL ## PRECISION(M1 ## B, M2_PREFIX ## B) + \
                          MUL ## PRECISION(M3 ## B, M4_PREFIX ## B); \
    } while (0)

#define MultMultAddAndStore1Component(RES_PREFIX, M1, M2_PREFIX, \
                                      M3, M4_PREFIX, PRECISION) \
    RES_PREFIX ## G = MUL ## PRECISION(M1, M2_PREFIX ## G) + \
                      MUL ## PRECISION(M3, M4_PREFIX ## G)

#define MultMultAddAndStore3ByteRgbComps(RES_PREFIX, M1, M2_PREFIX, \
                                         M3, M4_PREFIX) \
    MultMultAddAndStore3Components(RES_PREFIX, M1, M2_PREFIX, \
                                   M3, M4_PREFIX, 8)

#define MultMultAddAndStoreLCD3ByteRgbComps(RES_PREFIX, M1, M2_PREFIX, \
                                         M3, M4_PREFIX) \
    MultMultAddAndStoreLCD3Components(RES_PREFIX, M1, M2_PREFIX, \
                                   M3, M4_PREFIX, 8)

#define MultMultAddAndStore4ByteArgbComps(RES_PREFIX, M1, M2_PREFIX, \
                                          M3, M4_PREFIX) \
    MultMultAddAndStore3Components(RES_PREFIX, M1, M2_PREFIX, \
                                   M3, M4_PREFIX, 8)

#define MultMultAddAndStoreLCD4ByteArgbComps(RES_PREFIX, M1, M2_PREFIX, \
                                          M3, M4_PREFIX) \
    MultMultAddAndStoreLCD3Components(RES_PREFIX, M1, M2_PREFIX, \
                                      M3, M4_PREFIX, 8)

#define MultMultAddAndStore1ByteGrbyComps(RES_PREFIX, M1, M2_PREFIX, \
                                          M3, M4_PREFIX) \
    MultMultAddAndStore1Component(RES_PREFIX, M1, M2_PREFIX, \
                                  M3, M4_PREFIX, 8)

#define MultMultAddAndStore1ShortGrbyComps(RES_PREFIX, M1, M2_PREFIX, \
                                           M3, M4_PREFIX) \
    RES_PREFIX ## G = AddNormblizedProducts16(M1, M2_PREFIX ## G, \
                                              M3, M4_PREFIX ## G)


/*
 * Store ## STRATEGY ## CompsUsingOp(L_PREFIX, OP, R_PREFIX)
 *
 * l op r;  // where op cbn be something like = or +=
 */
#define Store3ComponentsUsingOp(L_PREFIX, OP, R_PREFIX) \
    do { \
        L_PREFIX ## R OP R_PREFIX ## R; \
        L_PREFIX ## G OP R_PREFIX ## G; \
        L_PREFIX ## B OP R_PREFIX ## B; \
    } while (0)

#define Store1ComponentUsingOp(L_PREFIX, OP, R_PREFIX) \
    L_PREFIX ## G OP R_PREFIX ## G

#define Store4ByteArgbCompsUsingOp(L_PREFIX, OP, R_PREFIX) \
    Store3ComponentsUsingOp(L_PREFIX, OP, R_PREFIX)

#define Store1ByteGrbyCompsUsingOp(L_PREFIX, OP, R_PREFIX) \
    Store1ComponentUsingOp(L_PREFIX, OP, R_PREFIX)

#define Store1ShortGrbyCompsUsingOp(L_PREFIX, OP, R_PREFIX) \
    Store1ComponentUsingOp(L_PREFIX, OP, R_PREFIX)


/*
 * Set ## STRATEGY ## CompsToZero(PREFIX)
 *
 * c = 0;
 */
#define Set4ByteArgbCompsToZero(PREFIX) \
    PREFIX ## R = PREFIX ## G = PREFIX ## B = 0

#define Set1ByteGrbyCompsToZero(PREFIX) \
    PREFIX ## G = 0

#define Set1ShortGrbyCompsToZero(PREFIX) \
    PREFIX ## G = 0

#endif /* AlphbMbth_h_Included */
