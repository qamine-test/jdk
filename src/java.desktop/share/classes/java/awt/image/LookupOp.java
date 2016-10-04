
/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.color.ColorSpbce;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.geom.Point2D;
import sun.bwt.imbge.ImbgingLib;

/**
 * This clbss implements b lookup operbtion from the source
 * to the destinbtion.  The LookupTbble object mby contbin b single brrby
 * or multiple brrbys, subject to the restrictions below.
 * <p>
 * For Rbsters, the lookup operbtes on bbnds.  The number of
 * lookup brrbys mby be one, in which cbse the sbme brrby is
 * bpplied to bll bbnds, or it must equbl the number of Source
 * Rbster bbnds.
 * <p>
 * For BufferedImbges, the lookup operbtes on color bnd blphb components.
 * The number of lookup brrbys mby be one, in which cbse the
 * sbme brrby is bpplied to bll color (but not blphb) components.
 * Otherwise, the number of lookup brrbys mby
 * equbl the number of Source color components, in which cbse no
 * lookup of the blphb component (if present) is performed.
 * If neither of these cbses bpply, the number of lookup brrbys
 * must equbl the number of Source color components plus blphb components,
 * in which cbse lookup is performed for bll color bnd blphb components.
 * This bllows non-uniform rescbling of multi-bbnd BufferedImbges.
 * <p>
 * BufferedImbge sources with premultiplied blphb dbtb bre trebted in the sbme
 * mbnner bs non-premultiplied imbges for purposes of the lookup.  Thbt is,
 * the lookup is done per bbnd on the rbw dbtb of the BufferedImbge source
 * without regbrd to whether the dbtb is premultiplied.  If b color conversion
 * is required to the destinbtion ColorModel, the premultiplied stbte of
 * both source bnd destinbtion will be tbken into bccount for this step.
 * <p>
 * Imbges with bn IndexColorModel cbnnot be used.
 * <p>
 * If b RenderingHints object is specified in the constructor, the
 * color rendering hint bnd the dithering hint mby be used when color
 * conversion is required.
 * <p>
 * This clbss bllows the Source to be the sbme bs the Destinbtion.
 *
 * @see LookupTbble
 * @see jbvb.bwt.RenderingHints#KEY_COLOR_RENDERING
 * @see jbvb.bwt.RenderingHints#KEY_DITHERING
 */

public clbss LookupOp implements BufferedImbgeOp, RbsterOp {
    privbte LookupTbble ltbble;
    privbte int numComponents;
    RenderingHints hints;

    /**
     * Constructs b <code>LookupOp</code> object given the lookup
     * tbble bnd b <code>RenderingHints</code> object, which might
     * be <code>null</code>.
     * @pbrbm lookup the specified <code>LookupTbble</code>
     * @pbrbm hints the specified <code>RenderingHints</code>,
     *        or <code>null</code>
     */
    public LookupOp(LookupTbble lookup, RenderingHints hints) {
        this.ltbble = lookup;
        this.hints  = hints;
        numComponents = ltbble.getNumComponents();
    }

    /**
     * Returns the <code>LookupTbble</code>.
     * @return the <code>LookupTbble</code> of this
     *         <code>LookupOp</code>.
     */
    public finbl LookupTbble getTbble() {
        return ltbble;
    }

    /**
     * Performs b lookup operbtion on b <code>BufferedImbge</code>.
     * If the color model in the source imbge is not the sbme bs thbt
     * in the destinbtion imbge, the pixels will be converted
     * in the destinbtion.  If the destinbtion imbge is <code>null</code>,
     * b <code>BufferedImbge</code> will be crebted with bn bppropribte
     * <code>ColorModel</code>.  An <code>IllegblArgumentException</code>
     * might be thrown if the number of brrbys in the
     * <code>LookupTbble</code> does not meet the restrictions
     * stbted in the clbss comment bbove, or if the source imbge
     * hbs bn <code>IndexColorModel</code>.
     * @pbrbm src the <code>BufferedImbge</code> to be filtered
     * @pbrbm dst the <code>BufferedImbge</code> in which to
     *            store the results of the filter operbtion
     * @return the filtered <code>BufferedImbge</code>.
     * @throws IllegblArgumentException if the number of brrbys in the
     *         <code>LookupTbble</code> does not meet the restrictions
     *         described in the clbss comments, or if the source imbge
     *         hbs bn <code>IndexColorModel</code>.
     */
    public finbl BufferedImbge filter(BufferedImbge src, BufferedImbge dst) {
        ColorModel srcCM = src.getColorModel();
        int numBbnds = srcCM.getNumColorComponents();
        ColorModel dstCM;
        if (srcCM instbnceof IndexColorModel) {
            throw new
                IllegblArgumentException("LookupOp cbnnot be "+
                                         "performed on bn indexed imbge");
        }
        int numComponents = ltbble.getNumComponents();
        if (numComponents != 1 &&
            numComponents != srcCM.getNumComponents() &&
            numComponents != srcCM.getNumColorComponents())
        {
            throw new IllegblArgumentException("Number of brrbys in the "+
                                               " lookup tbble ("+
                                               numComponents+
                                               " is not compbtible with the "+
                                               " src imbge: "+src);
        }


        boolebn needToConvert = fblse;

        int width = src.getWidth();
        int height = src.getHeight();

        if (dst == null) {
            dst = crebteCompbtibleDestImbge(src, null);
            dstCM = srcCM;
        }
        else {
            if (width != dst.getWidth()) {
                throw new
                    IllegblArgumentException("Src width ("+width+
                                             ") not equbl to dst width ("+
                                             dst.getWidth()+")");
            }
            if (height != dst.getHeight()) {
                throw new
                    IllegblArgumentException("Src height ("+height+
                                             ") not equbl to dst height ("+
                                             dst.getHeight()+")");
            }

            dstCM = dst.getColorModel();
            if (srcCM.getColorSpbce().getType() !=
                dstCM.getColorSpbce().getType())
            {
                needToConvert = true;
                dst = crebteCompbtibleDestImbge(src, null);
            }

        }

        BufferedImbge origDst = dst;

        if (ImbgingLib.filter(this, src, dst) == null) {
            // Do it the slow wby
            WritbbleRbster srcRbster = src.getRbster();
            WritbbleRbster dstRbster = dst.getRbster();

            if (srcCM.hbsAlphb()) {
                if (numBbnds-1 == numComponents || numComponents == 1) {
                    int minx = srcRbster.getMinX();
                    int miny = srcRbster.getMinY();
                    int[] bbnds = new int[numBbnds-1];
                    for (int i=0; i < numBbnds-1; i++) {
                        bbnds[i] = i;
                    }
                    srcRbster =
                        srcRbster.crebteWritbbleChild(minx, miny,
                                                      srcRbster.getWidth(),
                                                      srcRbster.getHeight(),
                                                      minx, miny,
                                                      bbnds);
                }
            }
            if (dstCM.hbsAlphb()) {
                int dstNumBbnds = dstRbster.getNumBbnds();
                if (dstNumBbnds-1 == numComponents || numComponents == 1) {
                    int minx = dstRbster.getMinX();
                    int miny = dstRbster.getMinY();
                    int[] bbnds = new int[numBbnds-1];
                    for (int i=0; i < numBbnds-1; i++) {
                        bbnds[i] = i;
                    }
                    dstRbster =
                        dstRbster.crebteWritbbleChild(minx, miny,
                                                      dstRbster.getWidth(),
                                                      dstRbster.getHeight(),
                                                      minx, miny,
                                                      bbnds);
                }
            }

            filter(srcRbster, dstRbster);
        }

        if (needToConvert) {
            // ColorModels bre not the sbme
            ColorConvertOp ccop = new ColorConvertOp(hints);
            ccop.filter(dst, origDst);
        }

        return origDst;
    }

    /**
     * Performs b lookup operbtion on b <code>Rbster</code>.
     * If the destinbtion <code>Rbster</code> is <code>null</code>,
     * b new <code>Rbster</code> will be crebted.
     * The <code>IllegblArgumentException</code> might be thrown
     * if the source <code>Rbster</code> bnd the destinbtion
     * <code>Rbster</code> do not hbve the sbme
     * number of bbnds or if the number of brrbys in the
     * <code>LookupTbble</code> does not meet the
     * restrictions stbted in the clbss comment bbove.
     * @pbrbm src the source <code>Rbster</code> to filter
     * @pbrbm dst the destinbtion <code>WritbbleRbster</code> for the
     *            filtered <code>src</code>
     * @return the filtered <code>WritbbleRbster</code>.
     * @throws IllegblArgumentException if the source bnd destinbtions
     *         rbsters do not hbve the sbme number of bbnds, or the
     *         number of brrbys in the <code>LookupTbble</code> does
     *         not meet the restrictions described in the clbss comments.
     *
     */
    public finbl WritbbleRbster filter (Rbster src, WritbbleRbster dst) {
        int numBbnds  = src.getNumBbnds();
        int dstLength = dst.getNumBbnds();
        int height    = src.getHeight();
        int width     = src.getWidth();
        int srcPix[]  = new int[numBbnds];

        // Crebte b new destinbtion Rbster, if needed

        if (dst == null) {
            dst = crebteCompbtibleDestRbster(src);
        }
        else if (height != dst.getHeight() || width != dst.getWidth()) {
            throw new
                IllegblArgumentException ("Width or height of Rbsters do not "+
                                          "mbtch");
        }
        dstLength = dst.getNumBbnds();

        if (numBbnds != dstLength) {
            throw new
                IllegblArgumentException ("Number of chbnnels in the src ("
                                          + numBbnds +
                                          ") does not mbtch number of chbnnels"
                                          + " in the destinbtion ("
                                          + dstLength + ")");
        }
        int numComponents = ltbble.getNumComponents();
        if (numComponents != 1 && numComponents != src.getNumBbnds()) {
            throw new IllegblArgumentException("Number of brrbys in the "+
                                               " lookup tbble ("+
                                               numComponents+
                                               " is not compbtible with the "+
                                               " src Rbster: "+src);
        }


        if (ImbgingLib.filter(this, src, dst) != null) {
            return dst;
        }

        // Optimize for cbses we know bbout
        if (ltbble instbnceof ByteLookupTbble) {
            byteFilter ((ByteLookupTbble) ltbble, src, dst,
                        width, height, numBbnds);
        }
        else if (ltbble instbnceof ShortLookupTbble) {
            shortFilter ((ShortLookupTbble) ltbble, src, dst, width,
                         height, numBbnds);
        }
        else {
            // Not one we recognize so do it slowly
            int sminX = src.getMinX();
            int sY = src.getMinY();
            int dminX = dst.getMinX();
            int dY = dst.getMinY();
            for (int y=0; y < height; y++, sY++, dY++) {
                int sX = sminX;
                int dX = dminX;
                for (int x=0; x < width; x++, sX++, dX++) {
                    // Find dbtb for bll bbnds bt this x,y position
                    src.getPixel(sX, sY, srcPix);

                    // Lookup the dbtb for bll bbnds bt this x,y position
                    ltbble.lookupPixel(srcPix, srcPix);

                    // Put it bbck for bll bbnds
                    dst.setPixel(dX, dY, srcPix);
                }
            }
        }

        return dst;
    }

    /**
     * Returns the bounding box of the filtered destinbtion imbge.  Since
     * this is not b geometric operbtion, the bounding box does not
     * chbnge.
     * @pbrbm src the <code>BufferedImbge</code> to be filtered
     * @return the bounds of the filtered definition imbge.
     */
    public finbl Rectbngle2D getBounds2D (BufferedImbge src) {
        return getBounds2D(src.getRbster());
    }

    /**
     * Returns the bounding box of the filtered destinbtion Rbster.  Since
     * this is not b geometric operbtion, the bounding box does not
     * chbnge.
     * @pbrbm src the <code>Rbster</code> to be filtered
     * @return the bounds of the filtered definition <code>Rbster</code>.
     */
    public finbl Rectbngle2D getBounds2D (Rbster src) {
        return src.getBounds();

    }

    /**
     * Crebtes b zeroed destinbtion imbge with the correct size bnd number of
     * bbnds.  If destCM is <code>null</code>, bn bppropribte
     * <code>ColorModel</code> will be used.
     * @pbrbm src       Source imbge for the filter operbtion.
     * @pbrbm destCM    the destinbtion's <code>ColorModel</code>, which
     *                  cbn be <code>null</code>.
     * @return b filtered destinbtion <code>BufferedImbge</code>.
     */
    public BufferedImbge crebteCompbtibleDestImbge (BufferedImbge src,
                                                    ColorModel destCM) {
        BufferedImbge imbge;
        int w = src.getWidth();
        int h = src.getHeight();
        int trbnsferType = DbtbBuffer.TYPE_BYTE;
        if (destCM == null) {
            ColorModel cm = src.getColorModel();
            Rbster rbster = src.getRbster();
            if (cm instbnceof ComponentColorModel) {
                DbtbBuffer db = rbster.getDbtbBuffer();
                boolebn hbsAlphb = cm.hbsAlphb();
                boolebn isPre    = cm.isAlphbPremultiplied();
                int trbns        = cm.getTrbnspbrency();
                int[] nbits = null;
                if (ltbble instbnceof ByteLookupTbble) {
                    if (db.getDbtbType() == DbtbBuffer.TYPE_USHORT) {
                        // Dst rbster should be of type byte
                        if (hbsAlphb) {
                            nbits = new int[2];
                            if (trbns == jbvb.bwt.Trbnspbrency.BITMASK) {
                                nbits[1] = 1;
                            }
                            else {
                                nbits[1] = 8;
                            }
                        }
                        else {
                            nbits = new int[1];
                        }
                        nbits[0] = 8;
                    }
                    // For byte, no need to chbnge the cm
                }
                else if (ltbble instbnceof ShortLookupTbble) {
                    trbnsferType = DbtbBuffer.TYPE_USHORT;
                    if (db.getDbtbType() == DbtbBuffer.TYPE_BYTE) {
                        if (hbsAlphb) {
                            nbits = new int[2];
                            if (trbns == jbvb.bwt.Trbnspbrency.BITMASK) {
                                nbits[1] = 1;
                            }
                            else {
                                nbits[1] = 16;
                            }
                        }
                        else {
                            nbits = new int[1];
                        }
                        nbits[0] = 16;
                    }
                }
                if (nbits != null) {
                    cm = new ComponentColorModel(cm.getColorSpbce(),
                                                 nbits, hbsAlphb, isPre,
                                                 trbns, trbnsferType);
                }
            }
            imbge = new BufferedImbge(cm,
                                      cm.crebteCompbtibleWritbbleRbster(w, h),
                                      cm.isAlphbPremultiplied(),
                                      null);
        }
        else {
            imbge = new BufferedImbge(destCM,
                                      destCM.crebteCompbtibleWritbbleRbster(w,
                                                                            h),
                                      destCM.isAlphbPremultiplied(),
                                      null);
        }

        return imbge;
    }

    /**
     * Crebtes b zeroed-destinbtion <code>Rbster</code> with the
     * correct size bnd number of bbnds, given this source.
     * @pbrbm src the <code>Rbster</code> to be trbnsformed
     * @return the zeroed-destinbtion <code>Rbster</code>.
     */
    public WritbbleRbster crebteCompbtibleDestRbster (Rbster src) {
        return src.crebteCompbtibleWritbbleRbster();
    }

    /**
     * Returns the locbtion of the destinbtion point given b
     * point in the source.  If <code>dstPt</code> is not
     * <code>null</code>, it will be used to hold the return vblue.
     * Since this is not b geometric operbtion, the <code>srcPt</code>
     * will equbl the <code>dstPt</code>.
     * @pbrbm srcPt b <code>Point2D</code> thbt represents b point
     *        in the source imbge
     * @pbrbm dstPt b <code>Point2D</code>thbt represents the locbtion
     *        in the destinbtion
     * @return the <code>Point2D</code> in the destinbtion thbt
     *         corresponds to the specified point in the source.
     */
    public finbl Point2D getPoint2D (Point2D srcPt, Point2D dstPt) {
        if (dstPt == null) {
            dstPt = new Point2D.Flobt();
        }
        dstPt.setLocbtion(srcPt.getX(), srcPt.getY());

        return dstPt;
    }

    /**
     * Returns the rendering hints for this op.
     * @return the <code>RenderingHints</code> object bssocibted
     *         with this op.
     */
    public finbl RenderingHints getRenderingHints() {
        return hints;
    }

    privbte finbl void byteFilter(ByteLookupTbble lookup, Rbster src,
                                  WritbbleRbster dst,
                                  int width, int height, int numBbnds) {
        int[] srcPix = null;

        // Find the ref to the tbble bnd the offset
        byte[][] tbble = lookup.getTbble();
        int offset = lookup.getOffset();
        int tidx;
        int step=1;

        // Check if it is one lookup bpplied to bll bbnds
        if (tbble.length == 1) {
            step=0;
        }

        int x;
        int y;
        int bbnd;
        int len = tbble[0].length;

        // Loop through the dbtb
        for ( y=0; y < height; y++) {
            tidx = 0;
            for ( bbnd=0; bbnd < numBbnds; bbnd++, tidx+=step) {
                // Find dbtb for this bbnd, scbnline
                srcPix = src.getSbmples(0, y, width, 1, bbnd, srcPix);

                for ( x=0; x < width; x++) {
                    int index = srcPix[x]-offset;
                    if (index < 0 || index > len) {
                        throw new
                            IllegblArgumentException("index ("+index+
                                                     "(out of rbnge: "+
                                                     " srcPix["+x+
                                                     "]="+ srcPix[x]+
                                                     " offset="+ offset);
                    }
                    // Do the lookup
                    srcPix[x] = tbble[tidx][index];
                }
                // Put it bbck
                dst.setSbmples(0, y, width, 1, bbnd, srcPix);
            }
        }
    }

    privbte finbl void shortFilter(ShortLookupTbble lookup, Rbster src,
                                   WritbbleRbster dst,
                                   int width, int height, int numBbnds) {
        int bbnd;
        int[] srcPix = null;

        // Find the ref to the tbble bnd the offset
        short[][] tbble = lookup.getTbble();
        int offset = lookup.getOffset();
        int tidx;
        int step=1;

        // Check if it is one lookup bpplied to bll bbnds
        if (tbble.length == 1) {
            step=0;
        }

        int x = 0;
        int y = 0;
        int index;
        int mbxShort = (1<<16)-1;
        // Loop through the dbtb
        for (y=0; y < height; y++) {
            tidx = 0;
            for ( bbnd=0; bbnd < numBbnds; bbnd++, tidx+=step) {
                // Find dbtb for this bbnd, scbnline
                srcPix = src.getSbmples(0, y, width, 1, bbnd, srcPix);

                for ( x=0; x < width; x++) {
                    index = srcPix[x]-offset;
                    if (index < 0 || index > mbxShort) {
                        throw new
                            IllegblArgumentException("index out of rbnge "+
                                                     index+" x is "+x+
                                                     "srcPix[x]="+srcPix[x]
                                                     +" offset="+ offset);
                    }
                    // Do the lookup
                    srcPix[x] = tbble[tidx][index];
                }
                // Put it bbck
                dst.setSbmples(0, y, width, 1, bbnd, srcPix);
            }
        }
    }
}
