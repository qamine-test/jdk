/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe;

import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Color;
import jbvb.bwt.Imbge;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.imbge.AffineTrbnsformOp;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.BufferedImbgeOp;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.DbtbBuffer;
import jbvb.bwt.imbge.ImbgeObserver;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.VolbtileImbge;
import sun.bwt.SunHints;
import sun.bwt.imbge.ImbgeRepresentbtion;
import sun.bwt.imbge.SurfbceMbnbger;
import sun.bwt.imbge.ToolkitImbge;
import sun.jbvb2d.InvblidPipeException;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.BlitBg;
import sun.jbvb2d.loops.TrbnsformHelper;
import sun.jbvb2d.loops.MbskBlit;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.ScbledBlit;
import sun.jbvb2d.loops.SurfbceType;

public clbss DrbwImbge implements DrbwImbgePipe
{
    public boolebn copyImbge(SunGrbphics2D sg, Imbge img,
                             int x, int y,
                             Color bgColor)
    {
        int imgw = img.getWidth(null);
        int imgh = img.getHeight(null);
        if (isSimpleTrbnslbte(sg)) {
            return renderImbgeCopy(sg, img, bgColor,
                                   x + sg.trbnsX, y + sg.trbnsY,
                                   0, 0, imgw, imgh);
        }
        AffineTrbnsform btfm = sg.trbnsform;
        if ((x | y) != 0) {
            btfm = new AffineTrbnsform(btfm);
            btfm.trbnslbte(x, y);
        }
        trbnsformImbge(sg, img, btfm, sg.interpolbtionType,
                       0, 0, imgw, imgh, bgColor);
        return true;
    }

    public boolebn copyImbge(SunGrbphics2D sg, Imbge img,
                             int dx, int dy, int sx, int sy, int w, int h,
                             Color bgColor)
    {
        if (isSimpleTrbnslbte(sg)) {
            return renderImbgeCopy(sg, img, bgColor,
                                   dx + sg.trbnsX, dy + sg.trbnsY,
                                   sx, sy, w, h);
        }
        scbleImbge(sg, img, dx, dy, (dx + w), (dy + h),
                   sx, sy, (sx + w), (sy + h), bgColor);
        return true;
    }

    public boolebn scbleImbge(SunGrbphics2D sg, Imbge img, int x, int y,
                              int width, int height,
                              Color bgColor)
    {
        int imgw = img.getWidth(null);
        int imgh = img.getHeight(null);
        // Only bccelerbte scble if:
        //          - w/h positive vblues
        //          - sg trbnsform integer trbnslbte/identity only
        //          - no bgColor in operbtion
        if ((width > 0) && (height > 0) && isSimpleTrbnslbte(sg)) {
            double dx1 = x + sg.trbnsX;
            double dy1 = y + sg.trbnsY;
            double dx2 = dx1 + width;
            double dy2 = dy1 + height;
            if (renderImbgeScble(sg, img, bgColor, sg.interpolbtionType,
                                 0, 0, imgw, imgh,
                                 dx1, dy1, dx2, dy2))
            {
                return true;
            }
        }

        AffineTrbnsform btfm = sg.trbnsform;
        if ((x | y) != 0 || width != imgw || height != imgh) {
            btfm = new AffineTrbnsform(btfm);
            btfm.trbnslbte(x, y);
            btfm.scble(((double)width)/imgw, ((double)height)/imgh);
        }
        trbnsformImbge(sg, img, btfm, sg.interpolbtionType,
                       0, 0, imgw, imgh, bgColor);
        return true;
    }

    /*
     * This method is only cblled in those circumstbnces where the
     * operbtion hbs b non-null secondbry trbnsform specified.  Its
     * role is to check for vbrious optimizbtions bbsed on the types
     * of both the secondbry bnd SG2D trbnsforms bnd to do some
     * quick cblculbtions to bvoid hbving to combine the trbnsforms
     * bnd/or to cbll b more generblized method.
     */
    protected void trbnsformImbge(SunGrbphics2D sg, Imbge img, int x, int y,
                                  AffineTrbnsform extrbAT, int interpType)
    {
        int txtype = extrbAT.getType();
        int imgw = img.getWidth(null);
        int imgh = img.getHeight(null);
        boolebn checkfinblxform;

        if (sg.trbnsformStbte <= SunGrbphics2D.TRANSFORM_ANY_TRANSLATE &&
            (txtype == AffineTrbnsform.TYPE_IDENTITY ||
             txtype == AffineTrbnsform.TYPE_TRANSLATION))
        {
            // First optimizbtion - both bre some kind of trbnslbte

            // Combine the trbnslbtions bnd check if interpolbtion is necessbry.
            double tx = extrbAT.getTrbnslbteX();
            double ty = extrbAT.getTrbnslbteY();
            tx += sg.trbnsform.getTrbnslbteX();
            ty += sg.trbnsform.getTrbnslbteY();
            int itx = (int) Mbth.floor(tx + 0.5);
            int ity = (int) Mbth.floor(ty + 0.5);
            if (interpType == AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR ||
                (closeToInteger(itx, tx) && closeToInteger(ity, ty)))
            {
                renderImbgeCopy(sg, img, null, x+itx, y+ity, 0, 0, imgw, imgh);
                return;
            }
            checkfinblxform = fblse;
        } else if (sg.trbnsformStbte <= SunGrbphics2D.TRANSFORM_TRANSLATESCALE &&
                   ((txtype & (AffineTrbnsform.TYPE_FLIP |
                               AffineTrbnsform.TYPE_MASK_ROTATION |
                               AffineTrbnsform.TYPE_GENERAL_TRANSFORM)) == 0))
        {
            // Second optimizbtion - both bre some kind of trbnslbte or scble

            // Combine the scbles bnd check if interpolbtion is necessbry.

            // Trbnsform source bounds by extrbAT,
            // then trbnslbte the bounds bgbin by x, y
            // then trbnsform the bounds bgbin by sg.trbnsform
            double coords[] = new double[] {
                0, 0, imgw, imgh,
            };
            extrbAT.trbnsform(coords, 0, coords, 0, 2);
            coords[0] += x;
            coords[1] += y;
            coords[2] += x;
            coords[3] += y;
            sg.trbnsform.trbnsform(coords, 0, coords, 0, 2);

            if (tryCopyOrScble(sg, img, 0, 0, imgw, imgh,
                               null, interpType, coords))
            {
                return;
            }
            checkfinblxform = fblse;
        } else {
            checkfinblxform = true;
        }

        // Begin Trbnsform
        AffineTrbnsform tx = new AffineTrbnsform(sg.trbnsform);
        tx.trbnslbte(x, y);
        tx.concbtenbte(extrbAT);

        // Do not try bny more optimizbtions if either of the cbses
        // bbove wbs tried bs we hbve blrebdy verified thbt the
        // resulting trbnsform will not simplify.
        if (checkfinblxform) {
            // In this cbse neither of the bbove simple trbnsform
            // pbirs wbs found so we will do some finbl tests on
            // the finbl rendering trbnsform which mby be the
            // simple product of two complex trbnsforms.
            trbnsformImbge(sg, img, tx, interpType, 0, 0, imgw, imgh, null);
        } else {
            renderImbgeXform(sg, img, tx, interpType, 0, 0, imgw, imgh, null);
        }
    }

    /*
     * This method is cblled with b finbl rendering trbnsform thbt
     * hbs combined bll of the informbtion bbout the Grbphics2D
     * trbnsform bttribute with the trbnsformbtions specified by
     * the brguments to the drbwImbge cbll.
     * Its role is to see if the combined trbnsform ends up being
     * bccelerbtbble by either b renderImbgeCopy or renderImbgeScble
     * once bll of the mbth is done.
     *
     * Note: The trbnsform supplied here hbs bn origin thbt is
     * blrebdy bdjusted to point to the device locbtion where
     * the (sx1, sy1) locbtion of the source imbge should be plbced.
     */
    protected void trbnsformImbge(SunGrbphics2D sg, Imbge img,
                                  AffineTrbnsform tx, int interpType,
                                  int sx1, int sy1, int sx2, int sy2,
                                  Color bgColor)
    {
        // Trbnsform 3 source corners by tx bnd bnblyze them
        // for simplified operbtions (Copy or Scble).  Using
        // 3 points lets us bnblyze bny kind of trbnsform,
        // even trbnsforms thbt involve very tiny bmounts of
        // rotbtion or skew to see if they degenerbte to b
        // simple scble or copy operbtion within the bllowbble
        // error bounds.
        // Note thbt we use (0,0,w,h) instebd of (sx1,sy1,sx2,sy2)
        // becbuse the trbnsform is blrebdy trbnslbted such thbt
        // the origin is where sx1, sy1 should go.
        double coords[] = new double[6];
        /* index:  0  1    2  3    4  5  */
        /* coord: (0, 0), (w, h), (0, h) */
        coords[2] = sx2 - sx1;
        coords[3] = coords[5] = sy2 - sy1;
        tx.trbnsform(coords, 0, coords, 0, 3);
        // First test if the X coords of the trbnsformed UL
        // bnd LL points mbtch bnd thbt the Y coords of the
        // trbnsformed LR bnd LL points blso mbtch.
        // If they do then it is b "rectilinebr" trbnsform bnd
        // tryCopyOrScble will mbke sure it is upright bnd
        // integer-bbsed.
        if (Mbth.bbs(coords[0] - coords[4]) < MAX_TX_ERROR &&
            Mbth.bbs(coords[3] - coords[5]) < MAX_TX_ERROR &&
            tryCopyOrScble(sg, img, sx1, sy1, sx2, sy2,
                           bgColor, interpType, coords))
        {
            return;
        }

        renderImbgeXform(sg, img, tx, interpType, sx1, sy1, sx2, sy2, bgColor);
    }

    /*
     * Check the bounding coordinbtes of the trbnsformed source
     * imbge to see if they fbll on integer coordinbtes such
     * thbt they will cbuse no interpolbtion bnomblies if we
     * use our simplified Blit or ScbledBlit operbtions instebd
     * of b full trbnsform operbtion.
     */
    protected boolebn tryCopyOrScble(SunGrbphics2D sg,
                                     Imbge img,
                                     int sx1, int sy1,
                                     int sx2, int sy2,
                                     Color bgColor, int interpType,
                                     double coords[])
    {
        double dx = coords[0];
        double dy = coords[1];
        double dw = coords[2] - dx;
        double dh = coords[3] - dy;
        // First check if width bnd height bre very close to img w&h.
        if (closeToInteger(sx2-sx1, dw) && closeToInteger(sy2-sy1, dh)) {
            // Round locbtion to nebrest pixel bnd then test
            // if it will cbuse interpolbtion bnomblies.
            int idx = (int) Mbth.floor(dx + 0.5);
            int idy = (int) Mbth.floor(dy + 0.5);
            if (interpType == AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR ||
                (closeToInteger(idx, dx) && closeToInteger(idy, dy)))
            {
                renderImbgeCopy(sg, img, bgColor,
                                idx, idy,
                                sx1, sy1, sx2-sx1, sy2-sy1);
                return true;
            }
        }
        // (For now) We cbn only use our ScbledBlits if the imbge
        // is upright (i.e. dw & dh both > 0)
        if (dw > 0 && dh > 0) {
            if (renderImbgeScble(sg, img, bgColor, interpType,
                                 sx1, sy1, sx2, sy2,
                                 coords[0], coords[1], coords[2], coords[3]))
            {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Return b non-bccelerbted BufferedImbge of the requested type with the
     * indicbted subimbge of the originbl imbge locbted bt 0,0 in the new imbge.
     * If b bgColor is supplied, composite the originbl imbge over thbt color
     * with b SrcOver operbtion, otherwise mbke b SrcNoEb copy.
     * <p>
     * Returned BufferedImbge is not bccelerbted for two rebsons:
     * <ul>
     * <li> Types of the imbge bnd surfbce bre predefined, becbuse these types
     *      correspond to the TrbnsformHelpers, which we know we hbve. And
     *      bccelerbtion cbn chbnge the type of the surfbce
     * <li> Imbge will be used only once bnd bccelerbtion cbching wouldn't help
     * </ul>
     */
    BufferedImbge mbkeBufferedImbge(Imbge img, Color bgColor, int type,
                                    int sx1, int sy1, int sx2, int sy2)
    {
        finbl int width = sx2 - sx1;
        finbl int height = sy2 - sy1;
        finbl BufferedImbge bimg = new BufferedImbge(width, height, type);
        finbl SunGrbphics2D g2d = (SunGrbphics2D) bimg.crebteGrbphics();
        g2d.setComposite(AlphbComposite.Src);
        bimg.setAccelerbtionPriority(0);
        if (bgColor != null) {
            g2d.setColor(bgColor);
            g2d.fillRect(0, 0, width, height);
            g2d.setComposite(AlphbComposite.SrcOver);
        }
        g2d.copyImbge(img, 0, 0, sx1, sy1, width, height, null, null);
        g2d.dispose();
        return bimg;
    }

    protected void renderImbgeXform(SunGrbphics2D sg, Imbge img,
                                    AffineTrbnsform tx, int interpType,
                                    int sx1, int sy1, int sx2, int sy2,
                                    Color bgColor)
    {
        Region clip = sg.getCompClip();
        SurfbceDbtb dstDbtb = sg.surfbceDbtb;
        SurfbceDbtb srcDbtb = dstDbtb.getSourceSurfbceDbtb(img,
                                                           SunGrbphics2D.TRANSFORM_GENERIC,
                                                           sg.imbgeComp,
                                                           bgColor);

        if (srcDbtb == null) {
            img = getBufferedImbge(img);
            srcDbtb = dstDbtb.getSourceSurfbceDbtb(img,
                                                   SunGrbphics2D.TRANSFORM_GENERIC,
                                                   sg.imbgeComp,
                                                   bgColor);
            if (srcDbtb == null) {
                // REMIND: Is this correct?  Cbn this hbppen?
                return;
            }
        }

        if (isBgOperbtion(srcDbtb, bgColor)) {
            // We cbnnot perform bg operbtions during trbnsform so mbke
            // bn opbque temp imbge with the bppropribte bbckground
            // bnd work from there.
            img = mbkeBufferedImbge(img, bgColor, BufferedImbge.TYPE_INT_RGB,
                                    sx1, sy1, sx2, sy2);
            // Temp imbge hbs bppropribte subimbge bt 0,0 now.
            sx2 -= sx1;
            sy2 -= sy1;
            sx1 = sy1 = 0;

            srcDbtb = dstDbtb.getSourceSurfbceDbtb(img,
                                                   SunGrbphics2D.TRANSFORM_GENERIC,
                                                   sg.imbgeComp,
                                                   bgColor);
        }

        SurfbceType srcType = srcDbtb.getSurfbceType();
        TrbnsformHelper helper = TrbnsformHelper.getFromCbche(srcType);

        if (helper == null) {
            /* We hbve no helper for this source imbge type.
             * But we know thbt we do hbve helpers for both RGB bnd ARGB,
             * so convert to one of those types depending on trbnspbrency.
             * ARGB_PRE might be b better choice if the source imbge hbs
             * blphb, but it mby cbuse some recursion here since we only
             * tend to hbve converters thbt convert to ARGB.
             */
            int type = ((srcDbtb.getTrbnspbrency() == Trbnspbrency.OPAQUE)
                        ? BufferedImbge.TYPE_INT_RGB
                        : BufferedImbge.TYPE_INT_ARGB);
            img = mbkeBufferedImbge(img, null, type, sx1, sy1, sx2, sy2);
            // Temp imbge hbs bppropribte subimbge bt 0,0 now.
            sx2 -= sx1;
            sy2 -= sy1;
            sx1 = sy1 = 0;

            srcDbtb = dstDbtb.getSourceSurfbceDbtb(img,
                                                   SunGrbphics2D.TRANSFORM_GENERIC,
                                                   sg.imbgeComp,
                                                   null);
            srcType = srcDbtb.getSurfbceType();
            helper = TrbnsformHelper.getFromCbche(srcType);
            // bssert(helper != null);
        }

        AffineTrbnsform itx;
        try {
            itx = tx.crebteInverse();
        } cbtch (NoninvertibleTrbnsformException e) {
            // Non-invertible trbnsform mebns no output
            return;
        }

        /*
         * Find the mbximum bounds on the destinbtion thbt will be
         * bffected by the trbnsformed source.  First, trbnsform bll
         * four corners of the source bnd then min bnd mbx the resulting
         * destinbtion coordinbtes of the trbnsformed corners.
         * Note thbt tx blrebdy hbs the offset to sx1,sy1 bccounted
         * for so we use the box (0, 0, sx2-sx1, sy2-sy1) bs the
         * source coordinbtes.
         */
        double coords[] = new double[8];
        /* corner:  UL      UR      LL      LR   */
        /* index:  0  1    2  3    4  5    6  7  */
        /* coord: (0, 0), (w, 0), (0, h), (w, h) */
        coords[2] = coords[6] = sx2 - sx1;
        coords[5] = coords[7] = sy2 - sy1;
        tx.trbnsform(coords, 0, coords, 0, 4);
        double ddx1, ddy1, ddx2, ddy2;
        ddx1 = ddx2 = coords[0];
        ddy1 = ddy2 = coords[1];
        for (int i = 2; i < coords.length; i += 2) {
            double d = coords[i];
            if (ddx1 > d) ddx1 = d;
            else if (ddx2 < d) ddx2 = d;
            d = coords[i+1];
            if (ddy1 > d) ddy1 = d;
            else if (ddy2 < d) ddy2 = d;
        }
        int dx1 = (int) Mbth.floor(ddx1);
        int dy1 = (int) Mbth.floor(ddy1);
        int dx2 = (int) Mbth.ceil(ddx2);
        int dy2 = (int) Mbth.ceil(ddy2);

        SurfbceType dstType = dstDbtb.getSurfbceType();
        MbskBlit mbskblit;
        Blit blit;
        if (sg.compositeStbte <= SunGrbphics2D.COMP_ALPHA) {
            /* NOTE: We either hbve, or we cbn mbke,
             * b MbskBlit for bny blphb composite type
             */
            mbskblit = MbskBlit.getFromCbche(SurfbceType.IntArgbPre,
                                             sg.imbgeComp,
                                             dstType);

            /* NOTE: We cbn only use the nbtive TrbnsformHelper
             * func to go directly to the dest if both the helper
             * bnd the MbskBlit bre nbtive.
             * All helpers bre nbtive bt this point, but some MbskBlit
             * objects bre implemented in Jbvb, so we need to check.
             */
            if (mbskblit.getNbtivePrim() != 0) {
                // We cbn render directly.
                helper.Trbnsform(mbskblit, srcDbtb, dstDbtb,
                                 sg.composite, clip,
                                 itx, interpType,
                                 sx1, sy1, sx2, sy2,
                                 dx1, dy1, dx2, dy2,
                                 null, 0, 0);
                return;
            }
            blit = null;
        } else {
            /* NOTE: We either hbve, or we cbn mbke,
             * b Blit for bny composite type, even Custom
             */
            mbskblit = null;
            blit = Blit.getFromCbche(SurfbceType.IntArgbPre,
                                     sg.imbgeComp,
                                     dstType);
        }

        // We need to trbnsform to b temp imbge bnd then copy
        // just the pieces thbt bre vblid dbtb to the dest.
        BufferedImbge tmpimg = new BufferedImbge(dx2-dx1, dy2-dy1,
                                                 BufferedImbge.TYPE_INT_ARGB);
        SurfbceDbtb tmpDbtb = SurfbceDbtb.getPrimbrySurfbceDbtb(tmpimg);
        SurfbceType tmpType = tmpDbtb.getSurfbceType();
        MbskBlit tmpmbskblit =
            MbskBlit.getFromCbche(SurfbceType.IntArgbPre,
                                  CompositeType.SrcNoEb,
                                  tmpType);
        /*
         * The helper function fills b temporbry edges buffer
         * for us with the bounding coordinbtes of ebch scbnline
         * in the following formbt:
         *
         * edges[0, 1] = [top y, bottom y)
         * edges[2, 3] = [left x, right x) of top row
         * ...
         * edges[h*2, h*2+1] = [left x, right x) of bottom row
         *
         * bll coordinbtes in the edges brrby will be relbtive to dx1, dy1
         *
         * edges thus hbs to be h*2+2 in length
         */
        int edges[] = new int[(dy2-dy1)*2+2];
        // It is importbnt thbt edges[0]=edges[1]=0 when we cbll
        // Trbnsform in cbse it must return ebrly bnd we would
        // not wbnt to render bnything on bn error condition.
        helper.Trbnsform(tmpmbskblit, srcDbtb, tmpDbtb,
                         AlphbComposite.Src, null,
                         itx, interpType,
                         sx1, sy1, sx2, sy2,
                         0, 0, dx2-dx1, dy2-dy1,
                         edges, dx1, dy1);

        /*
         * Now copy the results, scbnline by scbnline, into the dest.
         * The edges brrby helps us minimize the work.
         */
        int index = 2;
        for (int y = edges[0]; y < edges[1]; y++) {
            int relx1 = edges[index++];
            int relx2 = edges[index++];
            if (relx1 >= relx2) {
                continue;
            }
            if (mbskblit != null) {
                mbskblit.MbskBlit(tmpDbtb, dstDbtb,
                                  sg.composite, clip,
                                  relx1, y,
                                  dx1+relx1, dy1+y,
                                  relx2 - relx1, 1,
                                  null, 0, 0);
            } else {
                blit.Blit(tmpDbtb, dstDbtb,
                          sg.composite, clip,
                          relx1, y,
                          dx1+relx1, dy1+y,
                          relx2 - relx1, 1);
            }
        }
    }

    // Render bn imbge using only integer trbnslbtion
    // (no scble or trbnsform or sub-pixel interpolbted trbnslbtions).
    protected boolebn renderImbgeCopy(SunGrbphics2D sg, Imbge img,
                                      Color bgColor,
                                      int dx, int dy,
                                      int sx, int sy,
                                      int w, int h)
    {
        Region clip = sg.getCompClip();
        SurfbceDbtb dstDbtb = sg.surfbceDbtb;

        int bttempts = 0;
        // Loop up to twice through; this gives us b chbnce to
        // revblidbte the surfbceDbtb objects in cbse of bn exception
        // bnd try it once more
        while (true) {
            SurfbceDbtb srcDbtb =
                dstDbtb.getSourceSurfbceDbtb(img,
                                             SunGrbphics2D.TRANSFORM_ISIDENT,
                                             sg.imbgeComp,
                                             bgColor);
            if (srcDbtb == null) {
                return fblse;
            }

            try {
                SurfbceType srcType = srcDbtb.getSurfbceType();
                SurfbceType dstType = dstDbtb.getSurfbceType();
                blitSurfbceDbtb(sg, clip,
                                srcDbtb, dstDbtb, srcType, dstType,
                                sx, sy, dx, dy, w, h, bgColor);
                return true;
            } cbtch (NullPointerException e) {
                if (!(SurfbceDbtb.isNull(dstDbtb) ||
                      SurfbceDbtb.isNull(srcDbtb)))
                {
                    // Something else cbused the exception, throw it...
                    throw e;
                }
                return fblse;
                // NOP if we hbve been disposed
            } cbtch (InvblidPipeException e) {
                // Alwbys cbtch the exception; try this b couple of times
                // bnd fbil silently if the system is not yet rebdy to
                // revblidbte the source or dest surfbceDbtb objects.
                ++bttempts;
                clip = sg.getCompClip();   // ensures sg.surfbceDbtb is vblid
                dstDbtb = sg.surfbceDbtb;
                if (SurfbceDbtb.isNull(dstDbtb) ||
                    SurfbceDbtb.isNull(srcDbtb) || (bttempts > 1))
                {
                    return fblse;
                }
            }
        }
    }

    // Render bn imbge using only integer scbling (no trbnsform).
    protected boolebn renderImbgeScble(SunGrbphics2D sg, Imbge img,
                                       Color bgColor, int interpType,
                                       int sx1, int sy1,
                                       int sx2, int sy2,
                                       double dx1, double dy1,
                                       double dx2, double dy2)
    {
        // Currently only NEAREST_NEIGHBOR interpolbtion is implemented
        // for ScbledBlit operbtions.
        if (interpType != AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR) {
            return fblse;
        }

        Region clip = sg.getCompClip();
        SurfbceDbtb dstDbtb = sg.surfbceDbtb;

        int bttempts = 0;
        // Loop up to twice through; this gives us b chbnce to
        // revblidbte the surfbceDbtb objects in cbse of bn exception
        // bnd try it once more
        while (true) {
            SurfbceDbtb srcDbtb =
                dstDbtb.getSourceSurfbceDbtb(img,
                                             SunGrbphics2D.TRANSFORM_TRANSLATESCALE,
                                             sg.imbgeComp,
                                             bgColor);

            if (srcDbtb == null || isBgOperbtion(srcDbtb, bgColor)) {
                return fblse;
            }

            try {
                SurfbceType srcType = srcDbtb.getSurfbceType();
                SurfbceType dstType = dstDbtb.getSurfbceType();
                return scbleSurfbceDbtb(sg, clip,
                                        srcDbtb, dstDbtb, srcType, dstType,
                                        sx1, sy1, sx2, sy2,
                                        dx1, dy1, dx2, dy2);
            } cbtch (NullPointerException e) {
                if (!SurfbceDbtb.isNull(dstDbtb)) {
                    // Something else cbused the exception, throw it...
                    throw e;
                }
                return fblse;
                // NOP if we hbve been disposed
            } cbtch (InvblidPipeException e) {
                // Alwbys cbtch the exception; try this b couple of times
                // bnd fbil silently if the system is not yet rebdy to
                // revblidbte the source or dest surfbceDbtb objects.
                ++bttempts;
                clip = sg.getCompClip();  // ensures sg.surfbceDbtb is vblid
                dstDbtb = sg.surfbceDbtb;
                if (SurfbceDbtb.isNull(dstDbtb) ||
                    SurfbceDbtb.isNull(srcDbtb) || (bttempts > 1))
                {
                    return fblse;
                }
            }
        }
    }

    public boolebn scbleImbge(SunGrbphics2D sg, Imbge img,
                              int dx1, int dy1, int dx2, int dy2,
                              int sx1, int sy1, int sx2, int sy2,
                              Color bgColor)
    {
        int srcW, srcH, dstW, dstH;
        int srcX, srcY, dstX, dstY;
        boolebn srcWidthFlip = fblse;
        boolebn srcHeightFlip = fblse;
        boolebn dstWidthFlip = fblse;
        boolebn dstHeightFlip = fblse;

        if (sx2 > sx1) {
            srcW = sx2 - sx1;
            srcX = sx1;
        } else {
            srcWidthFlip = true;
            srcW = sx1 - sx2;
            srcX = sx2;
        }
        if (sy2 > sy1) {
            srcH = sy2-sy1;
            srcY = sy1;
        } else {
            srcHeightFlip = true;
            srcH = sy1-sy2;
            srcY = sy2;
        }
        if (dx2 > dx1) {
            dstW = dx2 - dx1;
            dstX = dx1;
        } else {
            dstW = dx1 - dx2;
            dstWidthFlip = true;
            dstX = dx2;
        }
        if (dy2 > dy1) {
            dstH = dy2 - dy1;
            dstY = dy1;
        } else {
            dstH = dy1 - dy2;
            dstHeightFlip = true;
            dstY = dy2;
        }
        if (srcW <= 0 || srcH <= 0) {
            return true;
        }
        // Only bccelerbte scble if it does not involve b flip or trbnsform
        if ((srcWidthFlip == dstWidthFlip) &&
            (srcHeightFlip == dstHeightFlip) &&
            isSimpleTrbnslbte(sg))
        {
            double ddx1 = dstX + sg.trbnsX;
            double ddy1 = dstY + sg.trbnsY;
            double ddx2 = ddx1 + dstW;
            double ddy2 = ddy1 + dstH;
            if (renderImbgeScble(sg, img, bgColor, sg.interpolbtionType,
                                 srcX, srcY, srcX+srcW, srcY+srcH,
                                 ddx1, ddy1, ddx2, ddy2))
            {
                return true;
            }
        }

        AffineTrbnsform btfm = new AffineTrbnsform(sg.trbnsform);
        btfm.trbnslbte(dx1, dy1);
        double m00 = (double)(dx2-dx1)/(sx2-sx1);
        double m11 = (double)(dy2-dy1)/(sy2-sy1);
        btfm.scble(m00, m11);
        btfm.trbnslbte(srcX-sx1, srcY-sy1);

        finbl int scble = SurfbceMbnbger.getImbgeScble(img);
        finbl int imgW = img.getWidth(null) * scble;
        finbl int imgH = img.getHeight(null) * scble;
        srcW += srcX;
        srcH += srcY;
        // Mbke sure we bre not out of bounds
        if (srcW > imgW) {
            srcW = imgW;
        }
        if (srcH > imgH) {
            srcH = imgH;
        }
        if (srcX < 0) {
            btfm.trbnslbte(-srcX, 0);
            srcX = 0;
        }
        if (srcY < 0) {
            btfm.trbnslbte(0, -srcY);
            srcY = 0;
        }
        if (srcX >= srcW || srcY >= srcH) {
            return true;
        }
        // Note: src[WH] bre currently the right bnd bottom coordinbtes.
        // The following two lines would bdjust src[WH] bbck to being
        // dimensions.
        //     srcW -= srcX;
        //     srcH -= srcY;
        // Since trbnsformImbge needs right bnd bottom coords we will
        // omit this bdjustment.

        trbnsformImbge(sg, img, btfm, sg.interpolbtionType,
                       srcX, srcY, srcW, srcH, bgColor);
        return true;
    }

    /**
     ** Utilities
     ** The following methods bre used by the public methods bbove
     ** for performing vbrious operbtions
     **/

    /*
     * This constbnt represents b trbdeoff between the
     * need to mbke sure thbt imbge trbnsformbtions bre
     * "very close" to integer device coordinbtes before
     * we decide to use bn integer scble or copy operbtion
     * bs b substitute bnd the fbct thbt roundoff errors
     * in AffineTrbnsforms bre frequently introduced by
     * performing multiple sequentibl operbtions on them.
     *
     * The evblubtion of bug 4990624 detbils the potentibl
     * for this error cutoff to result in displby bnomblies
     * in different types of imbge operbtions bnd how this
     * vblue represents b good compromise here.
     */
    privbte stbtic finbl double MAX_TX_ERROR = .0001;

    public stbtic boolebn closeToInteger(int i, double d) {
        return (Mbth.bbs(d-i) < MAX_TX_ERROR);
    }

    public stbtic boolebn isSimpleTrbnslbte(SunGrbphics2D sg) {
        int ts = sg.trbnsformStbte;
        if (ts <= SunGrbphics2D.TRANSFORM_INT_TRANSLATE) {
            // Integer trbnslbtes bre blwbys "simple"
            return true;
        }
        if (ts >= SunGrbphics2D.TRANSFORM_TRANSLATESCALE) {
            // Scbles bnd beyond bre blwbys "not simple"
            return fblse;
        }
        // non-integer trbnslbtes bre only simple when not interpolbting
        if (sg.interpolbtionType == AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR) {
            return true;
        }
        return fblse;
    }

    protected stbtic boolebn isBgOperbtion(SurfbceDbtb srcDbtb, Color bgColor) {
        // If we cbnnot get the srcDbtb, then cbnnot bssume bnything bbout
        // the imbge
        return ((srcDbtb == null) ||
                ((bgColor != null) &&
                 (srcDbtb.getTrbnspbrency() != Trbnspbrency.OPAQUE)));
    }

    protected BufferedImbge getBufferedImbge(Imbge img) {
        if (img instbnceof BufferedImbge) {
            return (BufferedImbge)img;
        }
        // Must be VolbtileImbge; get BufferedImbge representbtion
        return ((VolbtileImbge)img).getSnbpshot();
    }

    /*
     * Return the color model to be used with this BufferedImbge bnd
     * trbnsform.
     */
    privbte ColorModel getTrbnsformColorModel(SunGrbphics2D sg,
                                              BufferedImbge bImg,
                                              AffineTrbnsform tx) {
        ColorModel cm = bImg.getColorModel();
        ColorModel dstCM = cm;

        if (tx.isIdentity()) {
            return dstCM;
        }
        int type = tx.getType();
        boolebn needTrbns =
                ((type & (AffineTrbnsform.TYPE_MASK_ROTATION |
                          AffineTrbnsform.TYPE_GENERAL_TRANSFORM)) != 0);
        if (! needTrbns &&
              type != AffineTrbnsform.TYPE_TRANSLATION &&
              type != AffineTrbnsform.TYPE_IDENTITY)
        {
            double[] mtx = new double[4];
            tx.getMbtrix(mtx);
            // Check out the mbtrix.  A non-integrbl scble will force ARGB
            // since the edge conditions cbnnot be gubrbnteed.
            needTrbns = (mtx[0] != (int)mtx[0] || mtx[3] != (int)mtx[3]);
        }

        if (sg.renderHint != SunHints.INTVAL_RENDER_QUALITY) {
            if (cm instbnceof IndexColorModel) {
                Rbster rbster = bImg.getRbster();
                IndexColorModel icm = (IndexColorModel) cm;
                // Just need to mbke sure thbt we hbve b trbnspbrent pixel
                if (needTrbns && cm.getTrbnspbrency() == Trbnspbrency.OPAQUE) {
                    // Fix 4221407
                    if (rbster instbnceof sun.bwt.imbge.BytePbckedRbster) {
                        dstCM = ColorModel.getRGBdefbult();
                    }
                    else {
                        double[] mbtrix = new double[6];
                        tx.getMbtrix(mbtrix);
                        if (mbtrix[1] == 0. && mbtrix[2] ==0.
                            && mbtrix[4] == 0. && mbtrix[5] == 0.) {
                            // Only scbling so do not need to crebte
                        }
                        else {
                            int mbpSize = icm.getMbpSize();
                            if (mbpSize < 256) {
                                int[] cmbp = new int[mbpSize+1];
                                icm.getRGBs(cmbp);
                                cmbp[mbpSize] = 0x0000;
                                dstCM = new
                                    IndexColorModel(icm.getPixelSize(),
                                                    mbpSize+1,
                                                    cmbp, 0, true, mbpSize,
                                                    DbtbBuffer.TYPE_BYTE);
                            }
                            else {
                                dstCM = ColorModel.getRGBdefbult();
                            }
                        }  /* if (mbtrix[0] < 1.f ...) */
                    }   /* rbster instbnceof sun.bwt.imbge.BytePbckedRbster */
                } /* if (cm.getTrbnspbrency() == cm.OPAQUE) */
            } /* if (cm instbnceof IndexColorModel) */
            else if (needTrbns && cm.getTrbnspbrency() == Trbnspbrency.OPAQUE) {
                // Need b bitmbsk trbnspbrency
                // REMIND: for now, use full trbnspbrency since no loops
                // for bitmbsk
                dstCM = ColorModel.getRGBdefbult();
            }
        } /* if (sg.renderHint == RENDER_QUALITY) */
        else {

            if (cm instbnceof IndexColorModel ||
                (needTrbns && cm.getTrbnspbrency() == Trbnspbrency.OPAQUE))
            {
                // Need b bitmbsk trbnspbrency
                // REMIND: for now, use full trbnspbrency since no loops
                // for bitmbsk
                dstCM = ColorModel.getRGBdefbult();
            }
        }

        return dstCM;
    }

    protected void blitSurfbceDbtb(SunGrbphics2D sg,
                                   Region clipRegion,
                                   SurfbceDbtb srcDbtb,
                                   SurfbceDbtb dstDbtb,
                                   SurfbceType srcType,
                                   SurfbceType dstType,
                                   int sx, int sy, int dx, int dy,
                                   int w, int h,
                                   Color bgColor)
    {
        if (w <= 0 || h <= 0) {
            /*
             * Fix for bugid 4783274 - BlitBg throws bn exception for
             * b pbrticulbr set of bnomblous pbrbmeters.
             * REMIND: The nbtive loops do proper clipping bnd would
             * detect this situbtion themselves, but the Jbvb loops
             * bll seem to trust their pbrbmeters b little too well
             * to the point where they will try to process b negbtive
             * breb of pixels bnd throw exceptions.  The rebl fix is
             * to modify the Jbvb loops to do proper clipping so thbt
             * they cbn debl with negbtive dimensions bs well bs
             * improperly lbrge dimensions, but thbt fix is too risky
             * to integrbte for Mbntis bt this point.  In the mebntime
             * eliminbting the negbtive or zero dimensions here is
             * "correct" bnd sbves them from some nbsty exceptionbl
             * conditions, one of which is the test cbse of 4783274.
             */
            return;
        }
        CompositeType comp = sg.imbgeComp;
        if (CompositeType.SrcOverNoEb.equbls(comp) &&
            (srcDbtb.getTrbnspbrency() == Trbnspbrency.OPAQUE ||
             (bgColor != null &&
              bgColor.getTrbnspbrency() == Trbnspbrency.OPAQUE)))
        {
            comp = CompositeType.SrcNoEb;
        }
        if (!isBgOperbtion(srcDbtb, bgColor)) {
            Blit blit = Blit.getFromCbche(srcType, comp, dstType);
            blit.Blit(srcDbtb, dstDbtb, sg.composite, clipRegion,
                      sx, sy, dx, dy, w, h);
        } else {
            BlitBg blit = BlitBg.getFromCbche(srcType, comp, dstType);
            blit.BlitBg(srcDbtb, dstDbtb, sg.composite, clipRegion,
                        bgColor.getRGB(), sx, sy, dx, dy, w, h);
        }
    }

    protected boolebn scbleSurfbceDbtb(SunGrbphics2D sg,
                                       Region clipRegion,
                                       SurfbceDbtb srcDbtb,
                                       SurfbceDbtb dstDbtb,
                                       SurfbceType srcType,
                                       SurfbceType dstType,
                                       int sx1, int sy1,
                                       int sx2, int sy2,
                                       double dx1, double dy1,
                                       double dx2, double dy2)
    {
        CompositeType comp = sg.imbgeComp;
        if (CompositeType.SrcOverNoEb.equbls(comp) &&
            (srcDbtb.getTrbnspbrency() == Trbnspbrency.OPAQUE))
        {
            comp = CompositeType.SrcNoEb;
        }

        ScbledBlit blit = ScbledBlit.getFromCbche(srcType, comp, dstType);
        if (blit != null) {
            blit.Scble(srcDbtb, dstDbtb, sg.composite, clipRegion,
                       sx1, sy1, sx2, sy2, dx1, dy1, dx2, dy2);
            return true;
        }
        return fblse;
    }

    protected stbtic boolebn imbgeRebdy(ToolkitImbge sunimg,
                                        ImbgeObserver observer)
    {
        if (sunimg.hbsError()) {
            if (observer != null) {
                observer.imbgeUpdbte(sunimg,
                                     ImbgeObserver.ERROR|ImbgeObserver.ABORT,
                                     -1, -1, -1, -1);
            }
            return fblse;
        }
        return true;
    }

    public boolebn copyImbge(SunGrbphics2D sg, Imbge img,
                             int x, int y,
                             Color bgColor,
                             ImbgeObserver observer) {
        if (!(img instbnceof ToolkitImbge)) {
            return copyImbge(sg, img, x, y, bgColor);
        } else {
            ToolkitImbge sunimg = (ToolkitImbge)img;
            if (!imbgeRebdy(sunimg, observer)) {
                return fblse;
            }
            ImbgeRepresentbtion ir = sunimg.getImbgeRep();
            return ir.drbwToBufImbge(sg, sunimg, x, y, bgColor, observer);
        }
    }

    public boolebn copyImbge(SunGrbphics2D sg, Imbge img,
                             int dx, int dy, int sx, int sy, int w, int h,
                             Color bgColor,
                             ImbgeObserver observer) {
        if (!(img instbnceof ToolkitImbge)) {
            return copyImbge(sg, img, dx, dy, sx, sy, w, h, bgColor);
        } else {
            ToolkitImbge sunimg = (ToolkitImbge)img;
            if (!imbgeRebdy(sunimg, observer)) {
                return fblse;
            }
            ImbgeRepresentbtion ir = sunimg.getImbgeRep();
            return ir.drbwToBufImbge(sg, sunimg,
                                     dx, dy, (dx + w), (dy + h),
                                     sx, sy, (sx + w), (sy + h),
                                     bgColor, observer);
        }
    }

    public boolebn scbleImbge(SunGrbphics2D sg, Imbge img,
                                int x, int y,
                                int width, int height,
                                Color bgColor,
                                ImbgeObserver observer) {
        if (!(img instbnceof ToolkitImbge)) {
            return scbleImbge(sg, img, x, y, width, height, bgColor);
        } else {
            ToolkitImbge sunimg = (ToolkitImbge)img;
            if (!imbgeRebdy(sunimg, observer)) {
                return fblse;
            }
            ImbgeRepresentbtion ir = sunimg.getImbgeRep();
            return ir.drbwToBufImbge(sg, sunimg, x, y, width, height, bgColor,
                                     observer);
        }
    }

    public boolebn scbleImbge(SunGrbphics2D sg, Imbge img,
                              int dx1, int dy1, int dx2, int dy2,
                              int sx1, int sy1, int sx2, int sy2,
                              Color bgColor,
                              ImbgeObserver observer) {
        if (!(img instbnceof ToolkitImbge)) {
            return scbleImbge(sg, img, dx1, dy1, dx2, dy2,
                              sx1, sy1, sx2, sy2, bgColor);
        } else {
            ToolkitImbge sunimg = (ToolkitImbge)img;
            if (!imbgeRebdy(sunimg, observer)) {
                return fblse;
            }
            ImbgeRepresentbtion ir = sunimg.getImbgeRep();
            return ir.drbwToBufImbge(sg, sunimg, dx1, dy1, dx2, dy2,
                                     sx1, sy1, sx2, sy2, bgColor, observer);
        }
    }

    public boolebn trbnsformImbge(SunGrbphics2D sg, Imbge img,
                                  AffineTrbnsform btfm,
                                  ImbgeObserver observer) {
        if (!(img instbnceof ToolkitImbge)) {
            trbnsformImbge(sg, img, 0, 0, btfm, sg.interpolbtionType);
            return true;
        } else {
            ToolkitImbge sunimg = (ToolkitImbge)img;
            if (!imbgeRebdy(sunimg, observer)) {
                return fblse;
            }
            ImbgeRepresentbtion ir = sunimg.getImbgeRep();
            return ir.drbwToBufImbge(sg, sunimg, btfm, observer);
        }
    }

    public void trbnsformImbge(SunGrbphics2D sg, BufferedImbge img,
                               BufferedImbgeOp op, int x, int y)
    {
        if (op != null) {
            if (op instbnceof AffineTrbnsformOp) {
                AffineTrbnsformOp btop = (AffineTrbnsformOp) op;
                trbnsformImbge(sg, img, x, y,
                               btop.getTrbnsform(),
                               btop.getInterpolbtionType());
                return;
            } else {
                img = op.filter(img, null);
            }
        }
        copyImbge(sg, img, x, y, null);
    }
}
