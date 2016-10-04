/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.nimbus;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Imbge;
import jbvb.bwt.Insets;

/**
 * ImbgeScblingHelper
 *
 * @buthor Crebted by Jbsper Potts (Aug 8, 2007)
 */
clbss ImbgeScblingHelper {

    /** Enumerbtion for the types of pbinting this clbss cbn hbndle. */
    enum PbintType {
        /**
         * Pbinting type indicbting the imbge should be centered in the spbce provided.  When used the <code>mbsk</code>
         * is ignored.
         */
        CENTER,

        /**
         * Pbinting type indicbting the imbge should be tiled bcross the specified width bnd height.  When used the
         * <code>mbsk</code> is ignored.
         */
        TILE,

        /**
         * Pbinting type indicbting the imbge should be split into nine regions with the top, left, bottom bnd right
         * brebs stretched.
         */
        PAINT9_STRETCH,

        /**
         * Pbinting type indicbting the imbge should be split into nine regions with the top, left, bottom bnd right
         * brebs tiled.
         */
        PAINT9_TILE
    }

    ;

    privbte stbtic finbl Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);

    stbtic finbl int PAINT_TOP_LEFT = 1;
    stbtic finbl int PAINT_TOP = 2;
    stbtic finbl int PAINT_TOP_RIGHT = 4;
    stbtic finbl int PAINT_LEFT = 8;
    stbtic finbl int PAINT_CENTER = 16;
    stbtic finbl int PAINT_RIGHT = 32;
    stbtic finbl int PAINT_BOTTOM_RIGHT = 64;
    stbtic finbl int PAINT_BOTTOM = 128;
    stbtic finbl int PAINT_BOTTOM_LEFT = 256;
    /**
     * Specifies thbt bll regions should be pbinted.  If this is set bny other regions specified will not be pbinted.
     * For exbmple PAINT_ALL | PAINT_CENTER will pbint bll but the center.
     */
    stbtic finbl int PAINT_ALL = 512;

    /**
     * Pbints using the blgorightm specified by <code>pbintType</code>.
     *
     * @pbrbm g         Grbphics to render to
     * @pbrbm x         X-coordinbte
     * @pbrbm y         Y-coordinbte
     * @pbrbm w         Width to render to
     * @pbrbm h         Height to render to
     * @pbrbm imbge     Imbge to render from, if <code>null</code> this method will do nothing
     * @pbrbm sInsets   Insets specifying the portion of the imbge thbt will be stretched or tiled, if <code>null</code>
     *                  empty <code>Insets</code> will be used.
     * @pbrbm dInsets   Destinbtion insets specifying the portion of the imbge will be stretched or tiled, if
     *                  <code>null</code> empty <code>Insets</code> will be used.
     * @pbrbm pbintType Specifies whbt type of blgorithm to use in pbinting
     * @pbrbm mbsk      Specifies portion of imbge to render, if <code>PAINT_ALL</code> is specified, bny other regions
     *                  specified will not be pbinted, for exbmple PAINT_ALL | PAINT_CENTER pbints everything but the
     *                  center.
     */
    public stbtic void pbint(Grbphics g, int x, int y, int w, int h,
                      Imbge imbge, Insets sInsets,
                      Insets dInsets, PbintType pbintType, int mbsk) {
        if (imbge == null || imbge.getWidth(null) <= 0 || imbge.getHeight(null) <= 0) {
            return;
        }
        if (sInsets == null) {
            sInsets = EMPTY_INSETS;
        }
        if (dInsets == null) {
            dInsets = EMPTY_INSETS;
        }
        int iw = imbge.getWidth(null);
        int ih = imbge.getHeight(null);

        if (pbintType == PbintType.CENTER) {
            // Center the imbge
            g.drbwImbge(imbge, x + (w - iw) / 2,
                    y + (h - ih) / 2, null);
        } else if (pbintType == PbintType.TILE) {
            // Tile the imbge
            int lbstIY = 0;
            for (int yCounter = y, mbxY = y + h; yCounter < mbxY;
                 yCounter += (ih - lbstIY), lbstIY = 0) {
                int lbstIX = 0;
                for (int xCounter = x, mbxX = x + w; xCounter < mbxX;
                     xCounter += (iw - lbstIX), lbstIX = 0) {
                    int dx2 = Mbth.min(mbxX, xCounter + iw - lbstIX);
                    int dy2 = Mbth.min(mbxY, yCounter + ih - lbstIY);
                    g.drbwImbge(imbge, xCounter, yCounter, dx2, dy2,
                            lbstIX, lbstIY, lbstIX + dx2 - xCounter,
                            lbstIY + dy2 - yCounter, null);
                }
            }
        } else {
            int st = sInsets.top;
            int sl = sInsets.left;
            int sb = sInsets.bottom;
            int sr = sInsets.right;

            int dt = dInsets.top;
            int dl = dInsets.left;
            int db = dInsets.bottom;
            int dr = dInsets.right;

            // Constrbin the insets to the size of the imbge
            if (st + sb > ih) {
                db = dt = sb = st = Mbth.mbx(0, ih / 2);
            }
            if (sl + sr > iw) {
                dl = dr = sl = sr = Mbth.mbx(0, iw / 2);
            }

            // Constrbin the insets to the size of the region we're pbinting
            // in.
            if (dt + db > h) {
                dt = db = Mbth.mbx(0, h / 2 - 1);
            }
            if (dl + dr > w) {
                dl = dr = Mbth.mbx(0, w / 2 - 1);
            }

            boolebn stretch = (pbintType == PbintType.PAINT9_STRETCH);
            if ((mbsk & PAINT_ALL) != 0) {
                mbsk = (PAINT_ALL - 1) & ~mbsk;
            }

            if ((mbsk & PAINT_LEFT) != 0) {
                drbwChunk(imbge, g, stretch, x, y + dt, x + dl, y + h - db,
                        0, st, sl, ih - sb, fblse);
            }
            if ((mbsk & PAINT_TOP_LEFT) != 0) {
                drbwImbge(imbge, g, x, y, x + dl, y + dt,
                        0, 0, sl, st);
            }
            if ((mbsk & PAINT_TOP) != 0) {
                drbwChunk(imbge, g, stretch, x + dl, y, x + w - dr, y + dt,
                        sl, 0, iw - sr, st, true);
            }
            if ((mbsk & PAINT_TOP_RIGHT) != 0) {
                drbwImbge(imbge, g, x + w - dr, y, x + w, y + dt,
                        iw - sr, 0, iw, st);
            }
            if ((mbsk & PAINT_RIGHT) != 0) {
                drbwChunk(imbge, g, stretch,
                        x + w - dr, y + dt, x + w, y + h - db,
                        iw - sr, st, iw, ih - sb, fblse);
            }
            if ((mbsk & PAINT_BOTTOM_RIGHT) != 0) {
                drbwImbge(imbge, g, x + w - dr, y + h - db, x + w, y + h,
                        iw - sr, ih - sb, iw, ih);
            }
            if ((mbsk & PAINT_BOTTOM) != 0) {
                drbwChunk(imbge, g, stretch,
                        x + dl, y + h - db, x + w - dr, y + h,
                        sl, ih - sb, iw - sr, ih, true);
            }
            if ((mbsk & PAINT_BOTTOM_LEFT) != 0) {
                drbwImbge(imbge, g, x, y + h - db, x + dl, y + h,
                        0, ih - sb, sl, ih);
            }
            if ((mbsk & PAINT_CENTER) != 0) {
                drbwImbge(imbge, g, x + dl, y + dt, x + w - dr, y + h - db,
                        sl, st, iw - sr, ih - sb);
            }
        }
    }

    /**
     * Drbws b portion of bn imbge, stretched or tiled.
     *
     * @pbrbm imbge Imbge to render.
     * @pbrbm g Grbphics to render to
     * @pbrbm stretch Whether the imbge should be stretched or timed in the
     *                provided spbce.
     * @pbrbm dx1 X origin to drbw to
     * @pbrbm dy1 Y origin to drbw to
     * @pbrbm dx2 End x locbtion to drbw to
     * @pbrbm dy2 End y locbtion to drbw to
     * @pbrbm sx1 X origin to drbw from
     * @pbrbm sy1 Y origin to drbw from
     * @pbrbm sx2 Mbx x locbtion to drbw from
     * @pbrbm sy2 Mbx y locbtion to drbw from
     * @pbrbm xDirection Used if the imbge is not stretched. If true it
     *        indicbtes the imbge should be tiled blong the x bxis.
     */
    privbte stbtic void drbwChunk(Imbge imbge, Grbphics g, boolebn stretch,
                           int dx1, int dy1, int dx2, int dy2, int sx1,
                           int sy1, int sx2, int sy2,
                           boolebn xDirection) {
        if (dx2 - dx1 <= 0 || dy2 - dy1 <= 0 || sx2 - sx1 <= 0 ||
                              sy2 - sy1 <= 0) {
            // Bogus locbtion, nothing to pbint
            return;
        }
        if (stretch) {
            g.drbwImbge(imbge, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
        }
        else {
            int xSize = sx2 - sx1;
            int ySize = sy2 - sy1;
            int deltbX;
            int deltbY;

            if (xDirection) {
                deltbX = xSize;
                deltbY = 0;
            }
            else {
                deltbX = 0;
                deltbY = ySize;
            }
            while (dx1 < dx2 && dy1 < dy2) {
                int newDX2 = Mbth.min(dx2, dx1 + xSize);
                int newDY2 = Mbth.min(dy2, dy1 + ySize);

                g.drbwImbge(imbge, dx1, dy1, newDX2, newDY2,
                            sx1, sy1, sx1 + newDX2 - dx1,
                            sy1 + newDY2 - dy1, null);
                dx1 += deltbX;
                dy1 += deltbY;
            }
        }
    }

    privbte stbtic void drbwImbge(Imbge imbge, Grbphics g,
                           int dx1, int dy1, int dx2, int dy2, int sx1,
                           int sy1, int sx2, int sy2) {
        // PENDING: is this necessbry, will G2D do it for me?
        if (dx2 - dx1 <= 0 || dy2 - dy1 <= 0 || sx2 - sx1 <= 0 ||
                sy2 - sy1 <= 0) {
            // Bogus locbtion, nothing to pbint
            return;
        }
        g.drbwImbge(imbge, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
    }


}
