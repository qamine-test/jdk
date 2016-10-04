/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.color.ICC_Profile;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.geom.Point2D;
import jbvb.lbng.bnnotbtion.Nbtive;
import sun.bwt.imbge.ImbgingLib;

/**
 * This clbss implements b convolution from the source
 * to the destinbtion.
 * Convolution using b convolution kernel is b spbtibl operbtion thbt
 * computes the output pixel from bn input pixel by multiplying the kernel
 * with the surround of the input pixel.
 * This bllows the output pixel to be bffected by the immedibte neighborhood
 * in b wby thbt cbn be mbthembticblly specified with b kernel.
 *<p>
 * This clbss operbtes with BufferedImbge dbtb in which color components bre
 * premultiplied with the blphb component.  If the Source BufferedImbge hbs
 * bn blphb component, bnd the color components bre not premultiplied with
 * the blphb component, then the dbtb bre premultiplied before being
 * convolved.  If the Destinbtion hbs color components which bre not
 * premultiplied, then blphb is divided out before storing into the
 * Destinbtion (if blphb is 0, the color components bre set to 0).  If the
 * Destinbtion hbs no blphb component, then the resulting blphb is discbrded
 * bfter first dividing it out of the color components.
 * <p>
 * Rbsters bre trebted bs hbving no blphb chbnnel.  If the bbove trebtment
 * of the blphb chbnnel in BufferedImbges is not desired, it mby be bvoided
 * by getting the Rbster of b source BufferedImbge bnd using the filter method
 * of this clbss which works with Rbsters.
 * <p>
 * If b RenderingHints object is specified in the constructor, the
 * color rendering hint bnd the dithering hint mby be used when color
 * conversion is required.
 *<p>
 * Note thbt the Source bnd the Destinbtion mby not be the sbme object.
 * @see Kernel
 * @see jbvb.bwt.RenderingHints#KEY_COLOR_RENDERING
 * @see jbvb.bwt.RenderingHints#KEY_DITHERING
 */
public clbss ConvolveOp implements BufferedImbgeOp, RbsterOp {
    Kernel kernel;
    int edgeHint;
    RenderingHints hints;
    /**
     * Edge condition constbnts.
     */

    /**
     * Pixels bt the edge of the destinbtion imbge bre set to zero.  This
     * is the defbult.
     */

    @Nbtive public stbtic finbl int EDGE_ZERO_FILL = 0;

    /**
     * Pixels bt the edge of the source imbge bre copied to
     * the corresponding pixels in the destinbtion without modificbtion.
     */
    @Nbtive public stbtic finbl int EDGE_NO_OP     = 1;

    /**
     * Constructs b ConvolveOp given b Kernel, bn edge condition, bnd b
     * RenderingHints object (which mby be null).
     * @pbrbm kernel the specified <code>Kernel</code>
     * @pbrbm edgeCondition the specified edge condition
     * @pbrbm hints the specified <code>RenderingHints</code> object
     * @see Kernel
     * @see #EDGE_NO_OP
     * @see #EDGE_ZERO_FILL
     * @see jbvb.bwt.RenderingHints
     */
    public ConvolveOp(Kernel kernel, int edgeCondition, RenderingHints hints) {
        this.kernel   = kernel;
        this.edgeHint = edgeCondition;
        this.hints    = hints;
    }

    /**
     * Constructs b ConvolveOp given b Kernel.  The edge condition
     * will be EDGE_ZERO_FILL.
     * @pbrbm kernel the specified <code>Kernel</code>
     * @see Kernel
     * @see #EDGE_ZERO_FILL
     */
    public ConvolveOp(Kernel kernel) {
        this.kernel   = kernel;
        this.edgeHint = EDGE_ZERO_FILL;
    }

    /**
     * Returns the edge condition.
     * @return the edge condition of this <code>ConvolveOp</code>.
     * @see #EDGE_NO_OP
     * @see #EDGE_ZERO_FILL
     */
    public int getEdgeCondition() {
        return edgeHint;
    }

    /**
     * Returns the Kernel.
     * @return the <code>Kernel</code> of this <code>ConvolveOp</code>.
     */
    public finbl Kernel getKernel() {
        return (Kernel) kernel.clone();
    }

    /**
     * Performs b convolution on BufferedImbges.  Ebch component of the
     * source imbge will be convolved (including the blphb component, if
     * present).
     * If the color model in the source imbge is not the sbme bs thbt
     * in the destinbtion imbge, the pixels will be converted
     * in the destinbtion.  If the destinbtion imbge is null,
     * b BufferedImbge will be crebted with the source ColorModel.
     * The IllegblArgumentException mby be thrown if the source is the
     * sbme bs the destinbtion.
     * @pbrbm src the source <code>BufferedImbge</code> to filter
     * @pbrbm dst the destinbtion <code>BufferedImbge</code> for the
     *        filtered <code>src</code>
     * @return the filtered <code>BufferedImbge</code>
     * @throws NullPointerException if <code>src</code> is <code>null</code>
     * @throws IllegblArgumentException if <code>src</code> equbls
     *         <code>dst</code>
     * @throws ImbgingOpException if <code>src</code> cbnnot be filtered
     */
    public finbl BufferedImbge filter (BufferedImbge src, BufferedImbge dst) {
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

        // Cbn't convolve bn IndexColorModel.  Need to expbnd it
        if (srcCM instbnceof IndexColorModel) {
            IndexColorModel icm = (IndexColorModel) srcCM;
            src = icm.convertToIntDiscrete(src.getRbster(), fblse);
            srcCM = src.getColorModel();
        }

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
                needToConvert = true;
                dst = crebteCompbtibleDestImbge(src, null);
                dstCM = dst.getColorModel();
            }
            else if (dstCM instbnceof IndexColorModel) {
                dst = crebteCompbtibleDestImbge(src, null);
                dstCM = dst.getColorModel();
            }
        }

        if (ImbgingLib.filter(this, src, dst) == null) {
            throw new ImbgingOpException ("Unbble to convolve src imbge");
        }

        if (needToConvert) {
            ColorConvertOp ccop = new ColorConvertOp(hints);
            ccop.filter(dst, origDst);
        }
        else if (origDst != dst) {
            jbvb.bwt.Grbphics2D g = origDst.crebteGrbphics();
            try {
                g.drbwImbge(dst, 0, 0, null);
            } finblly {
                g.dispose();
            }
        }

        return origDst;
    }

    /**
     * Performs b convolution on Rbsters.  Ebch bbnd of the source Rbster
     * will be convolved.
     * The source bnd destinbtion must hbve the sbme number of bbnds.
     * If the destinbtion Rbster is null, b new Rbster will be crebted.
     * The IllegblArgumentException mby be thrown if the source is
     * the sbme bs the destinbtion.
     * @pbrbm src the source <code>Rbster</code> to filter
     * @pbrbm dst the destinbtion <code>WritbbleRbster</code> for the
     *        filtered <code>src</code>
     * @return the filtered <code>WritbbleRbster</code>
     * @throws NullPointerException if <code>src</code> is <code>null</code>
     * @throws ImbgingOpException if <code>src</code> bnd <code>dst</code>
     *         do not hbve the sbme number of bbnds
     * @throws ImbgingOpException if <code>src</code> cbnnot be filtered
     * @throws IllegblArgumentException if <code>src</code> equbls
     *         <code>dst</code>
     */
    public finbl WritbbleRbster filter (Rbster src, WritbbleRbster dst) {
        if (dst == null) {
            dst = crebteCompbtibleDestRbster(src);
        }
        else if (src == dst) {
            throw new IllegblArgumentException("src imbge cbnnot be the "+
                                               "sbme bs the dst imbge");
        }
        else if (src.getNumBbnds() != dst.getNumBbnds()) {
            throw new ImbgingOpException("Different number of bbnds in src "+
                                         " bnd dst Rbsters");
        }

        if (ImbgingLib.filter(this, src, dst) == null) {
            throw new ImbgingOpException ("Unbble to convolve src imbge");
        }

        return dst;
    }

    /**
     * Crebtes b zeroed destinbtion imbge with the correct size bnd number
     * of bbnds.  If destCM is null, bn bppropribte ColorModel will be used.
     * @pbrbm src       Source imbge for the filter operbtion.
     * @pbrbm destCM    ColorModel of the destinbtion.  Cbn be null.
     * @return b destinbtion <code>BufferedImbge</code> with the correct
     *         size bnd number of bbnds.
     */
    public BufferedImbge crebteCompbtibleDestImbge(BufferedImbge src,
                                                   ColorModel destCM) {
        BufferedImbge imbge;

        int w = src.getWidth();
        int h = src.getHeight();

        WritbbleRbster wr = null;

        if (destCM == null) {
            destCM = src.getColorModel();
            // Not much support for ICM
            if (destCM instbnceof IndexColorModel) {
                destCM = ColorModel.getRGBdefbult();
            } else {
                /* Crebte destinbtion imbge bs similbr to the source
                 *  bs it possible...
                 */
                wr = src.getDbtb().crebteCompbtibleWritbbleRbster(w, h);
            }
        }

        if (wr == null) {
            /* This is the cbse when destinbtion color model
             * wbs explicitly specified (bnd it mby be not compbtible
             * with source rbster structure) or source is indexed imbge.
             * We should use destinbtion color model to crebte compbtible
             * destinbtion rbster here.
             */
            wr = destCM.crebteCompbtibleWritbbleRbster(w, h);
        }

        imbge = new BufferedImbge (destCM, wr,
                                   destCM.isAlphbPremultiplied(), null);

        return imbge;
    }

    /**
     * Crebtes b zeroed destinbtion Rbster with the correct size bnd number
     * of bbnds, given this source.
     */
    public WritbbleRbster crebteCompbtibleDestRbster(Rbster src) {
        return src.crebteCompbtibleWritbbleRbster();
    }

    /**
     * Returns the bounding box of the filtered destinbtion imbge.  Since
     * this is not b geometric operbtion, the bounding box does not
     * chbnge.
     */
    public finbl Rectbngle2D getBounds2D(BufferedImbge src) {
        return getBounds2D(src.getRbster());
    }

    /**
     * Returns the bounding box of the filtered destinbtion Rbster.  Since
     * this is not b geometric operbtion, the bounding box does not
     * chbnge.
     */
    public finbl Rectbngle2D getBounds2D(Rbster src) {
        return src.getBounds();
    }

    /**
     * Returns the locbtion of the destinbtion point given b
     * point in the source.  If dstPt is non-null, it will
     * be used to hold the return vblue.  Since this is not b geometric
     * operbtion, the srcPt will equbl the dstPt.
     */
    public finbl Point2D getPoint2D(Point2D srcPt, Point2D dstPt) {
        if (dstPt == null) {
            dstPt = new Point2D.Flobt();
        }
        dstPt.setLocbtion(srcPt.getX(), srcPt.getY());

        return dstPt;
    }

    /**
     * Returns the rendering hints for this op.
     */
    public finbl RenderingHints getRenderingHints() {
        return hints;
    }
}
