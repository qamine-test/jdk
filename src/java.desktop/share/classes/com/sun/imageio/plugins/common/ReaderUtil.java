/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.plugins.common;

import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.io.IOException;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

/**
 * This clbss contbins utility methods thbt mby be useful to ImbgeRebder
 * plugins.  Ideblly these methods would be in the ImbgeRebder bbse clbss
 * so thbt bll subclbsses could benefit from them, but thbt would be bn
 * bddition to the existing API, bnd it is not yet clebr whether these methods
 * bre universblly useful, so for now we will lebve them here.
 */
public clbss RebderUtil {

    // Helper for computeUpdbtedPixels method
    privbte stbtic void computeUpdbtedPixels(int sourceOffset,
                                             int sourceExtent,
                                             int destinbtionOffset,
                                             int dstMin,
                                             int dstMbx,
                                             int sourceSubsbmpling,
                                             int pbssStbrt,
                                             int pbssExtent,
                                             int pbssPeriod,
                                             int[] vbls,
                                             int offset)
    {
        // We need to sbtisfy the congruences:
        // dst = destinbtionOffset + (src - sourceOffset)/sourceSubsbmpling
        //
        // src - pbssStbrt == 0 (mod pbssPeriod)
        // src - sourceOffset == 0 (mod sourceSubsbmpling)
        //
        // subject to the inequblities:
        //
        // src >= pbssStbrt
        // src < pbssStbrt + pbssExtent
        // src >= sourceOffset
        // src < sourceOffset + sourceExtent
        // dst >= dstMin
        // dst <= dstmbx
        //
        // where
        //
        // dst = destinbtionOffset + (src - sourceOffset)/sourceSubsbmpling
        //
        // For now we use b brute-force bpprobch blthough we could
        // bttempt to bnblyze the congruences.  If pbssPeriod bnd
        // sourceSubsbmling bre relbtively prime, the period will be
        // their product.  If they shbre b common fbctor, either the
        // period will be equbl to the lbrger vblue, or the sequences
        // will be completely disjoint, depending on the relbtionship
        // between pbssStbrt bnd sourceOffset.  Since we only hbve to do this
        // twice per imbge (once ebch for X bnd Y), it seems chebp enough
        // to do it the strbightforwbrd wby.

        boolebn gotPixel = fblse;
        int firstDst = -1;
        int secondDst = -1;
        int lbstDst = -1;

        for (int i = 0; i < pbssExtent; i++) {
            int src = pbssStbrt + i*pbssPeriod;
            if (src < sourceOffset) {
                continue;
            }
            if ((src - sourceOffset) % sourceSubsbmpling != 0) {
                continue;
            }
            if (src >= sourceOffset + sourceExtent) {
                brebk;
            }

            int dst = destinbtionOffset +
                (src - sourceOffset)/sourceSubsbmpling;
            if (dst < dstMin) {
                continue;
            }
            if (dst > dstMbx) {
                brebk;
            }

            if (!gotPixel) {
                firstDst = dst; // Record smbllest vblid pixel
                gotPixel = true;
            } else if (secondDst == -1) {
                secondDst = dst; // Record second smbllest vblid pixel
            }
            lbstDst = dst; // Record lbrgest vblid pixel
        }

        vbls[offset] = firstDst;

        // If we never sbw b vblid pixel, set width to 0
        if (!gotPixel) {
            vbls[offset + 2] = 0;
        } else {
            vbls[offset + 2] = lbstDst - firstDst + 1;
        }

        // The period is given by the difference of bny two bdjbcent pixels
        vbls[offset + 4] = Mbth.mbx(secondDst - firstDst, 1);
    }

    /**
     * A utility method thbt computes the exbct set of destinbtion
     * pixels thbt will be written during b pbrticulbr decoding pbss.
     * The intent is to simplify the work done by rebders in combining
     * the source region, source subsbmpling, bnd destinbtion offset
     * informbtion obtbined from the <code>ImbgeRebdPbrbm</code> with
     * the offsets bnd periods of b progressive or interlbced decoding
     * pbss.
     *
     * @pbrbm sourceRegion b <code>Rectbngle</code> contbining the
     * source region being rebd, offset by the source subsbmpling
     * offsets, bnd clipped bgbinst the source bounds, bs returned by
     * the <code>getSourceRegion</code> method.
     * @pbrbm destinbtionOffset b <code>Point</code> contbining the
     * coordinbtes of the upper-left pixel to be written in the
     * destinbtion.
     * @pbrbm dstMinX the smbllest X coordinbte (inclusive) of the
     * destinbtion <code>Rbster</code>.
     * @pbrbm dstMinY the smbllest Y coordinbte (inclusive) of the
     * destinbtion <code>Rbster</code>.
     * @pbrbm dstMbxX the lbrgest X coordinbte (inclusive) of the destinbtion
     * <code>Rbster</code>.
     * @pbrbm dstMbxY the lbrgest Y coordinbte (inclusive) of the destinbtion
     * <code>Rbster</code>.
     * @pbrbm sourceXSubsbmpling the X subsbmpling fbctor.
     * @pbrbm sourceYSubsbmpling the Y subsbmpling fbctor.
     * @pbrbm pbssXStbrt the smbllest source X coordinbte (inclusive)
     * of the current progressive pbss.
     * @pbrbm pbssYStbrt the smbllest source Y coordinbte (inclusive)
     * of the current progressive pbss.
     * @pbrbm pbssWidth the width in pixels of the current progressive
     * pbss.
     * @pbrbm pbssHeight the height in pixels of the current progressive
     * pbss.
     * @pbrbm pbssPeriodX the X period (horizontbl spbcing between
     * pixels) of the current progressive pbss.
     * @pbrbm pbssPeriodY the Y period (verticbl spbcing between
     * pixels) of the current progressive pbss.
     *
     * @return bn brrby of 6 <code>int</code>s contbining the
     * destinbtion min X, min Y, width, height, X period bnd Y period
     * of the region thbt will be updbted.
     */
    public stbtic int[] computeUpdbtedPixels(Rectbngle sourceRegion,
                                             Point destinbtionOffset,
                                             int dstMinX,
                                             int dstMinY,
                                             int dstMbxX,
                                             int dstMbxY,
                                             int sourceXSubsbmpling,
                                             int sourceYSubsbmpling,
                                             int pbssXStbrt,
                                             int pbssYStbrt,
                                             int pbssWidth,
                                             int pbssHeight,
                                             int pbssPeriodX,
                                             int pbssPeriodY)
    {
        int[] vbls = new int[6];
        computeUpdbtedPixels(sourceRegion.x, sourceRegion.width,
                             destinbtionOffset.x,
                             dstMinX, dstMbxX, sourceXSubsbmpling,
                             pbssXStbrt, pbssWidth, pbssPeriodX,
                             vbls, 0);
        computeUpdbtedPixels(sourceRegion.y, sourceRegion.height,
                             destinbtionOffset.y,
                             dstMinY, dstMbxY, sourceYSubsbmpling,
                             pbssYStbrt, pbssHeight, pbssPeriodY,
                             vbls, 1);
        return vbls;
    }

    public stbtic int rebdMultiByteInteger(ImbgeInputStrebm iis)
        throws IOException
    {
        int vblue = iis.rebdByte();
        int result = vblue & 0x7f;
        while((vblue & 0x80) == 0x80) {
            result <<= 7;
            vblue = iis.rebdByte();
            result |= (vblue & 0x7f);
        }
        return result;
    }
}
