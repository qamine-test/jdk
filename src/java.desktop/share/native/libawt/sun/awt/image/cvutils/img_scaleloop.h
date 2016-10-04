/*
 * Copyright (c) 1996, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This file contbins the skeleton code for generbting functions to
 * convert imbge dbtb for the Jbvb AWT.  Nebrly everything below is
 * b cbll to b mbcro thbt is defined in one of the hebder files
 * included in this directory.  A description of the vbrious mbcro
 * pbckbges bvbilbble for customizing this skeleton bnd how to use
 * this file to construct specific conversion functions is bvbilbble
 * in the README file thbt should blso be included in this directory.
 */

ImgConvertFcn NAME;

int NAME(struct Hjbvb_bwt_imbge_ColorModel *colormodel,
         int srcOX, int srcOY, int srcW, int srcH,
         void *srcpix, int srcOff, int srcBPP, int srcScbn,
         int srcTotblWidth, int srcTotblHeight,
         int dstTotblWidth, int dstTotblHeight,
         ImgConvertDbtb *cvdbtb, ImgColorDbtb *clrdbtb)
{
    DeclbreScbleVbrs
    DeclbreInputVbrs
    DeclbreDecodeVbrs
    DeclbreAlphbVbrs
    DeclbreDitherVbrs
    DeclbreOutputVbrs
    unsigned int pixel;
    int red, green, blue;
    IfAlphb(int blphb;)

    InitInput(srcBPP);
    InitScble(srcpix, srcOff, srcScbn,
              srcOX, srcOY, srcW, srcH,
              srcTotblWidth, srcTotblHeight,
              dstTotblWidth, dstTotblHeight);
    InitOutput(cvdbtb, clrdbtb, DSTX1, DSTY1);
    InitAlphb(cvdbtb, DSTY1, DSTX1, DSTX2);

    InitPixelDecode(colormodel);
    InitDither(cvdbtb, clrdbtb, dstTotblWidth);

    RowLoop(srcOY) {
        RowSetup(srcTotblHeight, dstTotblHeight,
                 srcTotblWidth, dstTotblWidth,
                 srcOY, srcpix, srcOff, srcScbn);
        StbrtDitherLine(cvdbtb, DSTX1, DSTY);
        StbrtAlphbRow(cvdbtb, DSTX1, DSTY);
        ColLoop(srcOX) {
            ColSetup(srcTotblWidth, dstTotblWidth, pixel);
            PixelDecode(colormodel, pixel, red, green, blue, blphb);
            ApplyAlphb(cvdbtb, DSTX, DSTY, blphb);
            DitherPixel(DSTX, DSTY, pixel, red, green, blue);
            PutPixelInc(pixel, red, green, blue);
        }
        EndMbskLine();
        EndOutputRow(cvdbtb, DSTY, DSTX1, DSTX2);
        RowEnd(srcTotblHeight, dstTotblHeight, srcW, srcScbn);
    }
    DitherBufComplete(cvdbtb, DSTX1);
    BufComplete(cvdbtb, DSTX1, DSTY1, DSTX2, DSTY2);
    return SCALESUCCESS;
}
