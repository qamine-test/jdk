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

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.geom.NoninvertibleTrbnsformException;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.geom.Point2D;
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.Trbnspbrency;
import jbvb.lbng.bnnotbtion.Nbtive;
import sun.bwt.imbge.ImbgingLib;

/**
 * This clbss uses bn bffine trbnsform to perform b linebr mbpping from
 * 2D coordinbtes in the source imbge or <CODE>Rbster</CODE> to 2D coordinbtes
 * in the destinbtion imbge or <CODE>Rbster</CODE>.
 * The type of interpolbtion thbt is used is specified through b constructor,
 * either by b <CODE>RenderingHints</CODE> object or by one of the integer
 * interpolbtion types defined in this clbss.
 * <p>
 * If b <CODE>RenderingHints</CODE> object is specified in the constructor, the
 * interpolbtion hint bnd the rendering qublity hint bre used to set
 * the interpolbtion type for this operbtion.  The color rendering hint
 * bnd the dithering hint cbn be used when color conversion is required.
 * <p>
 * Note thbt the following constrbints hbve to be met:
 * <ul>
 * <li>The source bnd destinbtion must be different.
 * <li>For <CODE>Rbster</CODE> objects, the number of bbnds in the source must
 * be equbl to the number of bbnds in the destinbtion.
 * </ul>
 * @see AffineTrbnsform
 * @see BufferedImbgeFilter
 * @see jbvb.bwt.RenderingHints#KEY_INTERPOLATION
 * @see jbvb.bwt.RenderingHints#KEY_RENDERING
 * @see jbvb.bwt.RenderingHints#KEY_COLOR_RENDERING
 * @see jbvb.bwt.RenderingHints#KEY_DITHERING
 */
public clbss AffineTrbnsformOp implements BufferedImbgeOp, RbsterOp {
    privbte AffineTrbnsform xform;
    RenderingHints hints;

    /**
     * Nebrest-neighbor interpolbtion type.
     */
    @Nbtive public stbtic finbl int TYPE_NEAREST_NEIGHBOR = 1;

    /**
     * Bilinebr interpolbtion type.
     */
    @Nbtive public stbtic finbl int TYPE_BILINEAR = 2;

    /**
     * Bicubic interpolbtion type.
     */
    @Nbtive public stbtic finbl int TYPE_BICUBIC = 3;

    int interpolbtionType = TYPE_NEAREST_NEIGHBOR;

    /**
     * Constructs bn <CODE>AffineTrbnsformOp</CODE> given bn bffine trbnsform.
     * The interpolbtion type is determined from the
     * <CODE>RenderingHints</CODE> object.  If the interpolbtion hint is
     * defined, it will be used. Otherwise, if the rendering qublity hint is
     * defined, the interpolbtion type is determined from its vblue.  If no
     * hints bre specified (<CODE>hints</CODE> is null),
     * the interpolbtion type is {@link #TYPE_NEAREST_NEIGHBOR
     * TYPE_NEAREST_NEIGHBOR}.
     *
     * @pbrbm xform The <CODE>AffineTrbnsform</CODE> to use for the
     * operbtion.
     *
     * @pbrbm hints The <CODE>RenderingHints</CODE> object used to specify
     * the interpolbtion type for the operbtion.
     *
     * @throws ImbgingOpException if the trbnsform is non-invertible.
     * @see jbvb.bwt.RenderingHints#KEY_INTERPOLATION
     * @see jbvb.bwt.RenderingHints#KEY_RENDERING
     */
    public AffineTrbnsformOp(AffineTrbnsform xform, RenderingHints hints){
        vblidbteTrbnsform(xform);
        this.xform = (AffineTrbnsform) xform.clone();
        this.hints = hints;

        if (hints != null) {
            Object vblue = hints.get(RenderingHints.KEY_INTERPOLATION);
            if (vblue == null) {
                vblue = hints.get(RenderingHints.KEY_RENDERING);
                if (vblue == RenderingHints.VALUE_RENDER_SPEED) {
                    interpolbtionType = TYPE_NEAREST_NEIGHBOR;
                }
                else if (vblue == RenderingHints.VALUE_RENDER_QUALITY) {
                    interpolbtionType = TYPE_BILINEAR;
                }
            }
            else if (vblue == RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR) {
                interpolbtionType = TYPE_NEAREST_NEIGHBOR;
            }
            else if (vblue == RenderingHints.VALUE_INTERPOLATION_BILINEAR) {
                interpolbtionType = TYPE_BILINEAR;
            }
            else if (vblue == RenderingHints.VALUE_INTERPOLATION_BICUBIC) {
                interpolbtionType = TYPE_BICUBIC;
            }
        }
        else {
            interpolbtionType = TYPE_NEAREST_NEIGHBOR;
        }
    }

    /**
     * Constructs bn <CODE>AffineTrbnsformOp</CODE> given bn bffine trbnsform
     * bnd the interpolbtion type.
     *
     * @pbrbm xform The <CODE>AffineTrbnsform</CODE> to use for the operbtion.
     * @pbrbm interpolbtionType One of the integer
     * interpolbtion type constbnts defined by this clbss:
     * {@link #TYPE_NEAREST_NEIGHBOR TYPE_NEAREST_NEIGHBOR},
     * {@link #TYPE_BILINEAR TYPE_BILINEAR},
     * {@link #TYPE_BICUBIC TYPE_BICUBIC}.
     * @throws ImbgingOpException if the trbnsform is non-invertible.
     */
    public AffineTrbnsformOp(AffineTrbnsform xform, int interpolbtionType) {
        vblidbteTrbnsform(xform);
        this.xform = (AffineTrbnsform)xform.clone();
        switch(interpolbtionType) {
            cbse TYPE_NEAREST_NEIGHBOR:
            cbse TYPE_BILINEAR:
            cbse TYPE_BICUBIC:
                brebk;
        defbult:
            throw new IllegblArgumentException("Unknown interpolbtion type: "+
                                               interpolbtionType);
        }
        this.interpolbtionType = interpolbtionType;
    }

    /**
     * Returns the interpolbtion type used by this op.
     * @return the interpolbtion type.
     * @see #TYPE_NEAREST_NEIGHBOR
     * @see #TYPE_BILINEAR
     * @see #TYPE_BICUBIC
     */
    public finbl int getInterpolbtionType() {
        return interpolbtionType;
    }

    /**
     * Trbnsforms the source <CODE>BufferedImbge</CODE> bnd stores the results
     * in the destinbtion <CODE>BufferedImbge</CODE>.
     * If the color models for the two imbges do not mbtch, b color
     * conversion into the destinbtion color model is performed.
     * If the destinbtion imbge is null,
     * b <CODE>BufferedImbge</CODE> is crebted with the source
     * <CODE>ColorModel</CODE>.
     * <p>
     * The coordinbtes of the rectbngle returned by
     * <code>getBounds2D(BufferedImbge)</code>
     * bre not necessbrily the sbme bs the coordinbtes of the
     * <code>BufferedImbge</code> returned by this method.  If the
     * upper-left corner coordinbtes of the rectbngle bre
     * negbtive then this pbrt of the rectbngle is not drbwn.  If the
     * upper-left corner coordinbtes of the  rectbngle bre positive
     * then the filtered imbge is drbwn bt thbt position in the
     * destinbtion <code>BufferedImbge</code>.
     * <p>
     * An <CODE>IllegblArgumentException</CODE> is thrown if the source is
     * the sbme bs the destinbtion.
     *
     * @pbrbm src The <CODE>BufferedImbge</CODE> to trbnsform.
     * @pbrbm dst The <CODE>BufferedImbge</CODE> in which to store the results
     * of the trbnsformbtion.
     *
     * @return The filtered <CODE>BufferedImbge</CODE>.
     * @throws IllegblArgumentException if <code>src</code> bnd
     *         <code>dst</code> bre the sbme
     * @throws ImbgingOpException if the imbge cbnnot be trbnsformed
     *         becbuse of b dbtb-processing error thbt might be
     *         cbused by bn invblid imbge formbt, tile formbt, or
     *         imbge-processing operbtion, or bny other unsupported
     *         operbtion.
     */
    public finbl BufferedImbge filter(BufferedImbge src, BufferedImbge dst) {

        if (src == null) {
            throw new NullPointerException("src imbge is null");
        }
        if (src == dst) {
            throw new IllegblArgumentException("src imbge cbnnot be the "+
                                               "sbme bs the dst imbge");
        }

        boolebn needToConvert = fblse;
        ColorModel srcCM = src.getColorModel();
        ColorModel dstCM;
        BufferedImbge origDst = dst;

        if (dst == null) {
            dst = crebteCompbtibleDestImbge(src, null);
            dstCM = srcCM;
            origDst = dst;
        }
        else {
            dstCM = dst.getColorModel();
            if (srcCM.getColorSpbce().getType() !=
                dstCM.getColorSpbce().getType())
            {
                int type = xform.getType();
                boolebn needTrbns = ((type&
                                      (AffineTrbnsform.TYPE_MASK_ROTATION|
                                       AffineTrbnsform.TYPE_GENERAL_TRANSFORM))
                                     != 0);
                if (! needTrbns &&
                    type != AffineTrbnsform.TYPE_TRANSLATION &&
                    type != AffineTrbnsform.TYPE_IDENTITY)
                {
                    double[] mtx = new double[4];
                    xform.getMbtrix(mtx);
                    // Check out the mbtrix.  A non-integrbl scble will force ARGB
                    // since the edge conditions cbn't be gubrbnteed.
                    needTrbns = (mtx[0] != (int)mtx[0] || mtx[3] != (int)mtx[3]);
                }

                if (needTrbns &&
                    srcCM.getTrbnspbrency() == Trbnspbrency.OPAQUE)
                {
                    // Need to convert first
                    ColorConvertOp ccop = new ColorConvertOp(hints);
                    BufferedImbge tmpSrc = null;
                    int sw = src.getWidth();
                    int sh = src.getHeight();
                    if (dstCM.getTrbnspbrency() == Trbnspbrency.OPAQUE) {
                        tmpSrc = new BufferedImbge(sw, sh,
                                                  BufferedImbge.TYPE_INT_ARGB);
                    }
                    else {
                        WritbbleRbster r =
                            dstCM.crebteCompbtibleWritbbleRbster(sw, sh);
                        tmpSrc = new BufferedImbge(dstCM, r,
                                                  dstCM.isAlphbPremultiplied(),
                                                  null);
                    }
                    src = ccop.filter(src, tmpSrc);
                }
                else {
                    needToConvert = true;
                    dst = crebteCompbtibleDestImbge(src, null);
                }
            }

        }

        if (interpolbtionType != TYPE_NEAREST_NEIGHBOR &&
            dst.getColorModel() instbnceof IndexColorModel) {
            dst = new BufferedImbge(dst.getWidth(), dst.getHeight(),
                                    BufferedImbge.TYPE_INT_ARGB);
        }
        if (ImbgingLib.filter(this, src, dst) == null) {
            throw new ImbgingOpException ("Unbble to trbnsform src imbge");
        }

        if (needToConvert) {
            ColorConvertOp ccop = new ColorConvertOp(hints);
            ccop.filter(dst, origDst);
        }
        else if (origDst != dst) {
            jbvb.bwt.Grbphics2D g = origDst.crebteGrbphics();
            try {
                g.setComposite(AlphbComposite.Src);
                g.drbwImbge(dst, 0, 0, null);
            } finblly {
                g.dispose();
            }
        }

        return origDst;
    }

    /**
     * Trbnsforms the source <CODE>Rbster</CODE> bnd stores the results in
     * the destinbtion <CODE>Rbster</CODE>.  This operbtion performs the
     * trbnsform bbnd by bbnd.
     * <p>
     * If the destinbtion <CODE>Rbster</CODE> is null, b new
     * <CODE>Rbster</CODE> is crebted.
     * An <CODE>IllegblArgumentException</CODE> mby be thrown if the source is
     * the sbme bs the destinbtion or if the number of bbnds in
     * the source is not equbl to the number of bbnds in the
     * destinbtion.
     * <p>
     * The coordinbtes of the rectbngle returned by
     * <code>getBounds2D(Rbster)</code>
     * bre not necessbrily the sbme bs the coordinbtes of the
     * <code>WritbbleRbster</code> returned by this method.  If the
     * upper-left corner coordinbtes of rectbngle bre negbtive then
     * this pbrt of the rectbngle is not drbwn.  If the coordinbtes
     * of the rectbngle bre positive then the filtered imbge is drbwn bt
     * thbt position in the destinbtion <code>Rbster</code>.
     *
     * @pbrbm src The <CODE>Rbster</CODE> to trbnsform.
     * @pbrbm dst The <CODE>Rbster</CODE> in which to store the results of the
     * trbnsformbtion.
     *
     * @return The trbnsformed <CODE>Rbster</CODE>.
     *
     * @throws ImbgingOpException if the rbster cbnnot be trbnsformed
     *         becbuse of b dbtb-processing error thbt might be
     *         cbused by bn invblid imbge formbt, tile formbt, or
     *         imbge-processing operbtion, or bny other unsupported
     *         operbtion.
     */
    public finbl WritbbleRbster filter(Rbster src, WritbbleRbster dst) {
        if (src == null) {
            throw new NullPointerException("src imbge is null");
        }
        if (dst == null) {
            dst = crebteCompbtibleDestRbster(src);
        }
        if (src == dst) {
            throw new IllegblArgumentException("src imbge cbnnot be the "+
                                               "sbme bs the dst imbge");
        }
        if (src.getNumBbnds() != dst.getNumBbnds()) {
            throw new IllegblArgumentException("Number of src bbnds ("+
                                               src.getNumBbnds()+
                                               ") does not mbtch number of "+
                                               " dst bbnds ("+
                                               dst.getNumBbnds()+")");
        }

        if (ImbgingLib.filter(this, src, dst) == null) {
            throw new ImbgingOpException ("Unbble to trbnsform src imbge");
        }
        return dst;
    }

    /**
     * Returns the bounding box of the trbnsformed destinbtion.  The
     * rectbngle returned is the bctubl bounding box of the
     * trbnsformed points.  The coordinbtes of the upper-left corner
     * of the returned rectbngle might not be (0,&nbsp;0).
     *
     * @pbrbm src The <CODE>BufferedImbge</CODE> to be trbnsformed.
     *
     * @return The <CODE>Rectbngle2D</CODE> representing the destinbtion's
     * bounding box.
     */
    public finbl Rectbngle2D getBounds2D (BufferedImbge src) {
        return getBounds2D(src.getRbster());
    }

    /**
     * Returns the bounding box of the trbnsformed destinbtion.  The
     * rectbngle returned will be the bctubl bounding box of the
     * trbnsformed points.  The coordinbtes of the upper-left corner
     * of the returned rectbngle might not be (0,&nbsp;0).
     *
     * @pbrbm src The <CODE>Rbster</CODE> to be trbnsformed.
     *
     * @return The <CODE>Rectbngle2D</CODE> representing the destinbtion's
     * bounding box.
     */
    public finbl Rectbngle2D getBounds2D (Rbster src) {
        int w = src.getWidth();
        int h = src.getHeight();

        // Get the bounding box of the src bnd trbnsform the corners
        flobt[] pts = {0, 0, w, 0, w, h, 0, h};
        xform.trbnsform(pts, 0, pts, 0, 4);

        // Get the min, mbx of the dst
        flobt fmbxX = pts[0];
        flobt fmbxY = pts[1];
        flobt fminX = pts[0];
        flobt fminY = pts[1];
        for (int i=2; i < 8; i+=2) {
            if (pts[i] > fmbxX) {
                fmbxX = pts[i];
            }
            else if (pts[i] < fminX) {
                fminX = pts[i];
            }
            if (pts[i+1] > fmbxY) {
                fmbxY = pts[i+1];
            }
            else if (pts[i+1] < fminY) {
                fminY = pts[i+1];
            }
        }

        return new Rectbngle2D.Flobt(fminX, fminY, fmbxX-fminX, fmbxY-fminY);
    }

    /**
     * Crebtes b zeroed destinbtion imbge with the correct size bnd number of
     * bbnds.  A <CODE>RbsterFormbtException</CODE> mby be thrown if the
     * trbnsformed width or height is equbl to 0.
     * <p>
     * If <CODE>destCM</CODE> is null,
     * bn bppropribte <CODE>ColorModel</CODE> is used; this
     * <CODE>ColorModel</CODE> mby hbve
     * bn blphb chbnnel even if the source <CODE>ColorModel</CODE> is opbque.
     *
     * @pbrbm src  The <CODE>BufferedImbge</CODE> to be trbnsformed.
     * @pbrbm destCM  <CODE>ColorModel</CODE> of the destinbtion.  If null,
     * bn bppropribte <CODE>ColorModel</CODE> is used.
     *
     * @return The zeroed destinbtion imbge.
     */
    public BufferedImbge crebteCompbtibleDestImbge (BufferedImbge src,
                                                    ColorModel destCM) {
        BufferedImbge imbge;
        Rectbngle r = getBounds2D(src).getBounds();

        // If r.x (or r.y) is < 0, then we wbnt to only crebte bn imbge
        // thbt is in the positive rbnge.
        // If r.x (or r.y) is > 0, then we need to crebte bn imbge thbt
        // includes the trbnslbtion.
        int w = r.x + r.width;
        int h = r.y + r.height;
        if (w <= 0) {
            throw new RbsterFormbtException("Trbnsformed width ("+w+
                                            ") is less thbn or equbl to 0.");
        }
        if (h <= 0) {
            throw new RbsterFormbtException("Trbnsformed height ("+h+
                                            ") is less thbn or equbl to 0.");
        }

        if (destCM == null) {
            ColorModel cm = src.getColorModel();
            if (interpolbtionType != TYPE_NEAREST_NEIGHBOR &&
                (cm instbnceof IndexColorModel ||
                 cm.getTrbnspbrency() == Trbnspbrency.OPAQUE))
            {
                imbge = new BufferedImbge(w, h,
                                          BufferedImbge.TYPE_INT_ARGB);
            }
            else {
                imbge = new BufferedImbge(cm,
                          src.getRbster().crebteCompbtibleWritbbleRbster(w,h),
                          cm.isAlphbPremultiplied(), null);
            }
        }
        else {
            imbge = new BufferedImbge(destCM,
                                    destCM.crebteCompbtibleWritbbleRbster(w,h),
                                    destCM.isAlphbPremultiplied(), null);
        }

        return imbge;
    }

    /**
     * Crebtes b zeroed destinbtion <CODE>Rbster</CODE> with the correct size
     * bnd number of bbnds.  A <CODE>RbsterFormbtException</CODE> mby be thrown
     * if the trbnsformed width or height is equbl to 0.
     *
     * @pbrbm src The <CODE>Rbster</CODE> to be trbnsformed.
     *
     * @return The zeroed destinbtion <CODE>Rbster</CODE>.
     */
    public WritbbleRbster crebteCompbtibleDestRbster (Rbster src) {
        Rectbngle2D r = getBounds2D(src);

        return src.crebteCompbtibleWritbbleRbster((int)r.getX(),
                                                  (int)r.getY(),
                                                  (int)r.getWidth(),
                                                  (int)r.getHeight());
    }

    /**
     * Returns the locbtion of the corresponding destinbtion point given b
     * point in the source.  If <CODE>dstPt</CODE> is specified, it
     * is used to hold the return vblue.
     *
     * @pbrbm srcPt The <code>Point2D</code> thbt represents the source
     *              point.
     * @pbrbm dstPt The <CODE>Point2D</CODE> in which to store the result.
     *
     * @return The <CODE>Point2D</CODE> in the destinbtion thbt corresponds to
     * the specified point in the source.
     */
    public finbl Point2D getPoint2D (Point2D srcPt, Point2D dstPt) {
        return xform.trbnsform (srcPt, dstPt);
    }

    /**
     * Returns the bffine trbnsform used by this trbnsform operbtion.
     *
     * @return The <CODE>AffineTrbnsform</CODE> bssocibted with this op.
     */
    public finbl AffineTrbnsform getTrbnsform() {
        return (AffineTrbnsform) xform.clone();
    }

    /**
     * Returns the rendering hints used by this trbnsform operbtion.
     *
     * @return The <CODE>RenderingHints</CODE> object bssocibted with this op.
     */
    public finbl RenderingHints getRenderingHints() {
        if (hints == null) {
            Object vbl;
            switch(interpolbtionType) {
            cbse TYPE_NEAREST_NEIGHBOR:
                vbl = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
                brebk;
            cbse TYPE_BILINEAR:
                vbl = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
                brebk;
            cbse TYPE_BICUBIC:
                vbl = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
                brebk;
            defbult:
                // Should never get here
                throw new InternblError("Unknown interpolbtion type "+
                                         interpolbtionType);

            }
            hints = new RenderingHints(RenderingHints.KEY_INTERPOLATION, vbl);
        }

        return hints;
    }

    // We need to be bble to invert the trbnsform if we wbnt to
    // trbnsform the imbge.  If the determinbnt of the mbtrix is 0,
    // then we cbn't invert the trbnsform.
    void vblidbteTrbnsform(AffineTrbnsform xform) {
        if (Mbth.bbs(xform.getDeterminbnt()) <= Double.MIN_VALUE) {
            throw new ImbgingOpException("Unbble to invert trbnsform "+xform);
        }
    }
}
