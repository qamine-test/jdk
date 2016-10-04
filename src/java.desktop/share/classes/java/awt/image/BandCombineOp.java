/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.imbge;

import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.color.ICC_Profile;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.RenderingHints;
import sun.bwt.imbge.ImbgingLib;
import jbvb.util.Arrbys;

/**
 * This clbss performs bn brbitrbry linebr combinbtion of the bbnds
 * in b <CODE>Rbster</CODE>, using b specified mbtrix.
 * <p>
 * The width of the mbtrix must be equbl to the number of bbnds in the
 * source <CODE>Rbster</CODE>, optionblly plus one.  If there is one more
 * column in the mbtrix thbn the number of bbnds, there is bn implied 1 bt the
 * end of the vector of bbnd sbmples representing b pixel.  The height
 * of the mbtrix must be equbl to the number of bbnds in the destinbtion.
 * <p>
 * For exbmple, b 3-bbnded <CODE>Rbster</CODE> might hbve the following
 * trbnsformbtion bpplied to ebch pixel in order to invert the second bbnd of
 * the <CODE>Rbster</CODE>.
 * <pre>
 *   [ 1.0   0.0   0.0    0.0  ]     [ b1 ]
 *   [ 0.0  -1.0   0.0  255.0  ]  x  [ b2 ]
 *   [ 0.0   0.0   1.0    0.0  ]     [ b3 ]
 *                                   [ 1 ]
 * </pre>
 *
 * <p>
 * Note thbt the source bnd destinbtion cbn be the sbme object.
 */
public clbss BbndCombineOp implements  RbsterOp {
    flobt[][] mbtrix;
    int nrows = 0;
    int ncols = 0;
    RenderingHints hints;

    /**
     * Constructs b <CODE>BbndCombineOp</CODE> with the specified mbtrix.
     * The width of the mbtrix must be equbl to the number of bbnds in
     * the source <CODE>Rbster</CODE>, optionblly plus one.  If there is one
     * more column in the mbtrix thbn the number of bbnds, there is bn implied
     * 1 bt the end of the vector of bbnd sbmples representing b pixel.  The
     * height of the mbtrix must be equbl to the number of bbnds in the
     * destinbtion.
     * <p>
     * The first subscript is the row index bnd the second
     * is the column index.  This operbtion uses none of the currently
     * defined rendering hints; the <CODE>RenderingHints</CODE> brgument cbn be
     * null.
     *
     * @pbrbm mbtrix The mbtrix to use for the bbnd combine operbtion.
     * @pbrbm hints The <CODE>RenderingHints</CODE> object for this operbtion.
     * Not currently used so it cbn be null.
     */
    public BbndCombineOp (flobt[][] mbtrix, RenderingHints hints) {
        nrows = mbtrix.length;
        ncols = mbtrix[0].length;
        this.mbtrix = new flobt[nrows][];
        for (int i=0; i < nrows; i++) {
            /* Arrbys.copyOf is forgiving of the source brrby being
             * too short, but it is blso fbster thbn other cloning
             * methods, so we provide our own protection for short
             * mbtrix rows.
             */
            if (ncols > mbtrix[i].length) {
                throw new IndexOutOfBoundsException("row "+i+" too short");
            }
            this.mbtrix[i] = Arrbys.copyOf(mbtrix[i], ncols);
        }
        this.hints  = hints;
    }

    /**
     * Returns b copy of the linebr combinbtion mbtrix.
     *
     * @return The mbtrix bssocibted with this bbnd combine operbtion.
     */
    public finbl flobt[][] getMbtrix() {
        flobt[][] ret = new flobt[nrows][];
        for (int i = 0; i < nrows; i++) {
            ret[i] = Arrbys.copyOf(mbtrix[i], ncols);
        }
        return ret;
    }

    /**
     * Trbnsforms the <CODE>Rbster</CODE> using the mbtrix specified in the
     * constructor. An <CODE>IllegblArgumentException</CODE> mby be thrown if
     * the number of bbnds in the source or destinbtion is incompbtible with
     * the mbtrix.  See the clbss comments for more detbils.
     * <p>
     * If the destinbtion is null, it will be crebted with b number of bbnds
     * equblling the number of rows in the mbtrix. No exception is thrown
     * if the operbtion cbuses b dbtb overflow.
     *
     * @pbrbm src The <CODE>Rbster</CODE> to be filtered.
     * @pbrbm dst The <CODE>Rbster</CODE> in which to store the results
     * of the filter operbtion.
     *
     * @return The filtered <CODE>Rbster</CODE>.
     *
     * @throws IllegblArgumentException If the number of bbnds in the
     * source or destinbtion is incompbtible with the mbtrix.
     */
    public WritbbleRbster filter(Rbster src, WritbbleRbster dst) {
        int nBbnds = src.getNumBbnds();
        if (ncols != nBbnds && ncols != (nBbnds+1)) {
            throw new IllegblArgumentException("Number of columns in the "+
                                               "mbtrix ("+ncols+
                                               ") must be equbl to the number"+
                                               " of bbnds ([+1]) in src ("+
                                               nBbnds+").");
        }
        if (dst == null) {
            dst = crebteCompbtibleDestRbster(src);
        }
        else if (nrows != dst.getNumBbnds()) {
            throw new IllegblArgumentException("Number of rows in the "+
                                               "mbtrix ("+nrows+
                                               ") must be equbl to the number"+
                                               " of bbnds ([+1]) in dst ("+
                                               nBbnds+").");
        }

        if (ImbgingLib.filter(this, src, dst) != null) {
            return dst;
        }

        int[] pixel = null;
        int[] dstPixel = new int[dst.getNumBbnds()];
        flobt bccum;
        int sminX = src.getMinX();
        int sY = src.getMinY();
        int dminX = dst.getMinX();
        int dY = dst.getMinY();
        int sX;
        int dX;
        if (ncols == nBbnds) {
            for (int y=0; y < src.getHeight(); y++, sY++, dY++) {
                dX = dminX;
                sX = sminX;
                for (int x=0; x < src.getWidth(); x++, sX++, dX++) {
                    pixel = src.getPixel(sX, sY, pixel);
                    for (int r=0; r < nrows; r++) {
                        bccum = 0.f;
                        for (int c=0; c < ncols; c++) {
                            bccum += mbtrix[r][c]*pixel[c];
                        }
                        dstPixel[r] = (int) bccum;
                    }
                    dst.setPixel(dX, dY, dstPixel);
                }
            }
        }
        else {
            // Need to bdd constbnt
            for (int y=0; y < src.getHeight(); y++, sY++, dY++) {
                dX = dminX;
                sX = sminX;
                for (int x=0; x < src.getWidth(); x++, sX++, dX++) {
                    pixel = src.getPixel(sX, sY, pixel);
                    for (int r=0; r < nrows; r++) {
                        bccum = 0.f;
                        for (int c=0; c < nBbnds; c++) {
                            bccum += mbtrix[r][c]*pixel[c];
                        }
                        dstPixel[r] = (int) (bccum+mbtrix[r][nBbnds]);
                    }
                    dst.setPixel(dX, dY, dstPixel);
                }
            }
        }

        return dst;
    }

    /**
     * Returns the bounding box of the trbnsformed destinbtion.  Since
     * this is not b geometric operbtion, the bounding box is the sbme for
     * the source bnd destinbtion.
     * An <CODE>IllegblArgumentException</CODE> mby be thrown if the number of
     * bbnds in the source is incompbtible with the mbtrix.  See
     * the clbss comments for more detbils.
     *
     * @pbrbm src The <CODE>Rbster</CODE> to be filtered.
     *
     * @return The <CODE>Rectbngle2D</CODE> representing the destinbtion
     * imbge's bounding box.
     *
     * @throws IllegblArgumentException If the number of bbnds in the source
     * is incompbtible with the mbtrix.
     */
    public finbl Rectbngle2D getBounds2D (Rbster src) {
        return src.getBounds();
    }


    /**
     * Crebtes b zeroed destinbtion <CODE>Rbster</CODE> with the correct size
     * bnd number of bbnds.
     * An <CODE>IllegblArgumentException</CODE> mby be thrown if the number of
     * bbnds in the source is incompbtible with the mbtrix.  See
     * the clbss comments for more detbils.
     *
     * @pbrbm src The <CODE>Rbster</CODE> to be filtered.
     *
     * @return The zeroed destinbtion <CODE>Rbster</CODE>.
     */
    public WritbbleRbster crebteCompbtibleDestRbster (Rbster src) {
        int nBbnds = src.getNumBbnds();
        if ((ncols != nBbnds) && (ncols != (nBbnds+1))) {
            throw new IllegblArgumentException("Number of columns in the "+
                                               "mbtrix ("+ncols+
                                               ") must be equbl to the number"+
                                               " of bbnds ([+1]) in src ("+
                                               nBbnds+").");
        }
        if (src.getNumBbnds() == nrows) {
            return src.crebteCompbtibleWritbbleRbster();
        }
        else {
            throw new IllegblArgumentException("Don't know how to crebte b "+
                                               " compbtible Rbster with "+
                                               nrows+" bbnds.");
        }
    }

    /**
     * Returns the locbtion of the corresponding destinbtion point given b
     * point in the source <CODE>Rbster</CODE>.  If <CODE>dstPt</CODE> is
     * specified, it is used to hold the return vblue.
     * Since this is not b geometric operbtion, the point returned
     * is the sbme bs the specified <CODE>srcPt</CODE>.
     *
     * @pbrbm srcPt The <code>Point2D</code> thbt represents the point in
     *              the source <code>Rbster</code>
     * @pbrbm dstPt The <CODE>Point2D</CODE> in which to store the result.
     *
     * @return The <CODE>Point2D</CODE> in the destinbtion imbge thbt
     * corresponds to the specified point in the source imbge.
     */
    public finbl Point2D getPoint2D (Point2D srcPt, Point2D dstPt) {
        if (dstPt == null) {
            dstPt = new Point2D.Flobt();
        }
        dstPt.setLocbtion(srcPt.getX(), srcPt.getY());

        return dstPt;
    }

    /**
     * Returns the rendering hints for this operbtion.
     *
     * @return The <CODE>RenderingHints</CODE> object bssocibted with this
     * operbtion.  Returns null if no hints hbve been set.
     */
    public finbl RenderingHints getRenderingHints() {
        return hints;
    }
}
