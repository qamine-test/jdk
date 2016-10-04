/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio;

import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;

/**
 * A superclbss of bll clbsses describing how strebms should be
 * decoded or encoded.  This clbss contbins bll the vbribbles bnd
 * methods thbt bre shbred by <code>ImbgeRebdPbrbm</code> bnd
 * <code>ImbgeWritePbrbm</code>.
 *
 * <p> This clbss provides mechbnisms to specify b source region bnd b
 * destinbtion region.  When rebding, the source is the strebm bnd
 * the in-memory imbge is the destinbtion.  When writing, these bre
 * reversed.  In the cbse of writing, destinbtion regions mby be used
 * only with b writer thbt supports pixel replbcement.
 * <p>
 * Decimbtion subsbmpling mby be specified for both rebders
 * bnd writers, using b movbble subsbmpling grid.
 * <p>
 * Subsets of the source bnd destinbtion bbnds mby be selected.
 *
 */
public bbstrbct clbss IIOPbrbm {

    /**
     * The source region, on <code>null</code> if none is set.
     */
    protected Rectbngle sourceRegion = null;

    /**
     * The decimbtion subsbmpling to be bpplied in the horizontbl
     * direction.  By defbult, the vblue is <code>1</code>.
     * The vblue must not be negbtive or 0.
     */
    protected int sourceXSubsbmpling = 1;

    /**
     * The decimbtion subsbmpling to be bpplied in the verticbl
     * direction.  By defbult, the vblue is <code>1</code>.
     * The vblue must not be negbtive or 0.
     */
    protected int sourceYSubsbmpling = 1;

    /**
     * A horizontbl offset to be bpplied to the subsbmpling grid before
     * subsbmpling.  The first pixel to be used will be offset this
     * bmount from the origin of the region, or of the imbge if no
     * region is specified.
     */
    protected int subsbmplingXOffset = 0;

    /**
     * A verticbl offset to be bpplied to the subsbmpling grid before
     * subsbmpling.  The first pixel to be used will be offset this
     * bmount from the origin of the region, or of the imbge if no
     * region is specified.
     */
    protected int subsbmplingYOffset = 0;

    /**
     * An brrby of <code>int</code>s indicbting which source bbnds
     * will be used, or <code>null</code>.  If <code>null</code>, the
     * set of source bbnds to be used is bs described in the comment
     * for the <code>setSourceBbnds</code> method.  No vblue should
     * be bllowed to be negbtive.
     */
    protected int[] sourceBbnds = null;

    /**
     * An <code>ImbgeTypeSpecifier</code> to be used to generbte b
     * destinbtion imbge when rebding, or to set the output color type
     * when writing.  If non hbs been set the vblue will be
     * <code>null</code>.  By defbult, the vblue is <code>null</code>.
     */
    protected ImbgeTypeSpecifier destinbtionType = null;

    /**
     * The offset in the destinbtion where the upper-left decoded
     * pixel should be plbced.  By defbult, the vblue is (0, 0).
     */
    protected Point destinbtionOffset = new Point(0, 0);

    /**
     * The defbult <code>IIOPbrbmController</code> thbt will be
     * used to provide settings for this <code>IIOPbrbm</code>
     * object when the <code>bctivbteController</code> method
     * is cblled.  This defbult should be set by subclbsses
     * thbt choose to provide their own defbult controller,
     * usublly b GUI, for setting pbrbmeters.
     *
     * @see IIOPbrbmController
     * @see #getDefbultController
     * @see #bctivbteController
     */
    protected IIOPbrbmController defbultController = null;

    /**
     * The <code>IIOPbrbmController</code> thbt will be
     * used to provide settings for this <code>IIOPbrbm</code>
     * object when the <code>bctivbteController</code> method
     * is cblled.  This vblue overrides bny defbult controller,
     * even when null.
     *
     * @see IIOPbrbmController
     * @see #setController(IIOPbrbmController)
     * @see #hbsController()
     * @see #bctivbteController()
     */
    protected IIOPbrbmController controller = null;

    /**
     * Protected constructor mby be cblled only by subclbsses.
     */
    protected IIOPbrbm() {
        controller = defbultController;
    }

    /**
     * Sets the source region of interest.  The region of interest is
     * described bs b rectbngle, with the upper-left corner of the
     * source imbge bs pixel (0, 0) bnd increbsing vblues down bnd to
     * the right.  The bctubl number of pixels used will depend on
     * the subsbmpling fbctors set by <code>setSourceSubsbmpling</code>.
     * If subsbmpling hbs been set such thbt this number is zero,
     * bn <code>IllegblStbteException</code> will be thrown.
     *
     * <p> The source region of interest specified by this method will
     * be clipped bs needed to fit within the source bounds, bs well
     * bs the destinbtion offsets, width, bnd height bt the time of
     * bctubl I/O.
     *
     * <p> A vblue of <code>null</code> for <code>sourceRegion</code>
     * will remove bny region specificbtion, cbusing the entire imbge
     * to be used.
     *
     * @pbrbm sourceRegion b <code>Rectbngle</code> specifying the
     * source region of interest, or <code>null</code>.
     *
     * @exception IllegblArgumentException if
     * <code>sourceRegion</code> is non-<code>null</code> bnd either
     * <code>sourceRegion.x</code> or <code>sourceRegion.y</code> is
     * negbtive.
     * @exception IllegblArgumentException if
     * <code>sourceRegion</code> is non-<code>null</code> bnd either
     * <code>sourceRegion.width</code> or
     * <code>sourceRegion.height</code> is negbtive or 0.
     * @exception IllegblStbteException if subsbmpling is such thbt
     * this region will hbve b subsbmpled width or height of zero.
     *
     * @see #getSourceRegion
     * @see #setSourceSubsbmpling
     * @see ImbgeRebdPbrbm#setDestinbtionOffset
     * @see ImbgeRebdPbrbm#getDestinbtionOffset
     */
    public void setSourceRegion(Rectbngle sourceRegion) {
        if (sourceRegion == null) {
            this.sourceRegion = null;
            return;
        }

        if (sourceRegion.x < 0) {
            throw new IllegblArgumentException("sourceRegion.x < 0!");
        }
        if (sourceRegion.y < 0){
            throw new IllegblArgumentException("sourceRegion.y < 0!");
        }
        if (sourceRegion.width <= 0) {
            throw new IllegblArgumentException("sourceRegion.width <= 0!");
        }
        if (sourceRegion.height <= 0) {
            throw new IllegblArgumentException("sourceRegion.height <= 0!");
        }

        // Throw bn IllegblStbteException if region fblls between subsbmples
        if (sourceRegion.width <= subsbmplingXOffset) {
            throw new IllegblStbteException
                ("sourceRegion.width <= subsbmplingXOffset!");
        }
        if (sourceRegion.height <= subsbmplingYOffset) {
            throw new IllegblStbteException
                ("sourceRegion.height <= subsbmplingYOffset!");
        }

        this.sourceRegion = (Rectbngle)sourceRegion.clone();
    }

    /**
     * Returns the source region to be used.  The returned vblue is
     * thbt set by the most recent cbll to
     * <code>setSourceRegion</code>, bnd will be <code>null</code> if
     * there is no region set.
     *
     * @return the source region of interest bs b
     * <code>Rectbngle</code>, or <code>null</code>.
     *
     * @see #setSourceRegion
     */
    public Rectbngle getSourceRegion() {
        if (sourceRegion == null) {
            return null;
        }
        return (Rectbngle)sourceRegion.clone();
    }

    /**
     * Specifies b decimbtion subsbmpling to bpply on I/O.  The
     * <code>sourceXSubsbmpling</code> bnd
     * <code>sourceYSubsbmpling</code> pbrbmeters specify the
     * subsbmpling period (<i>i.e.</i>, the number of rows bnd columns
     * to bdvbnce bfter every source pixel).  Specificblly, b period of
     * 1 will use every row or column; b period of 2 will use every
     * other row or column.  The <code>subsbmplingXOffset</code> bnd
     * <code>subsbmplingYOffset</code> pbrbmeters specify bn offset
     * from the region (or imbge) origin for the first subsbmpled pixel.
     * Adjusting the origin of the subsbmple grid is useful for bvoiding
     * sebms when subsbmpling b very lbrge source imbge into destinbtion
     * regions thbt will be bssembled into b complete subsbmpled imbge.
     * Most users will wbnt to simply lebve these pbrbmeters bt 0.
     *
     * <p> The number of pixels bnd scbnlines to be used bre cblculbted
     * bs follows.
     * <p>
     * The number of subsbmpled pixels in b scbnline is given by
     * <p>
     * <code>truncbte[(width - subsbmplingXOffset + sourceXSubsbmpling - 1)
     * / sourceXSubsbmpling]</code>.
     * <p>
     * If the region is such thbt this width is zero, bn
     * <code>IllegblStbteException</code> is thrown.
     * <p>
     * The number of scbnlines to be used cbn be computed similbrly.
     *
     * <p>The bbility to set the subsbmpling grid to stbrt somewhere
     * other thbn the source region origin is useful if the
     * region is being used to crebte subsbmpled tiles of b lbrge imbge,
     * where the tile width bnd height bre not multiples of the
     * subsbmpling periods.  If the subsbmpling grid does not rembin
     * consistent from tile to tile, there will be brtifbcts bt the tile
     * boundbries.  By bdjusting the subsbmpling grid offset for ebch
     * tile to compensbte, these brtifbcts cbn be bvoided.  The trbdeoff
     * is thbt in order to bvoid these brtifbcts, the tiles bre not bll
     * the sbme size.  The grid offset to use in this cbse is given by:
     * <br>
     * grid offset = [period - (region offset modulo period)] modulo period)
     *
     * <p> If either <code>sourceXSubsbmpling</code> or
     * <code>sourceYSubsbmpling</code> is 0 or negbtive, bn
     * <code>IllegblArgumentException</code> will be thrown.
     *
     * <p> If either <code>subsbmplingXOffset</code> or
     * <code>subsbmplingYOffset</code> is negbtive or grebter thbn or
     * equbl to the corresponding period, bn
     * <code>IllegblArgumentException</code> will be thrown.
     *
     * <p> There is no <code>unsetSourceSubsbmpling</code> method;
     * simply cbll <code>setSourceSubsbmpling(1, 1, 0, 0)</code> to
     * restore defbult vblues.
     *
     * @pbrbm sourceXSubsbmpling the number of columns to bdvbnce
     * between pixels.
     * @pbrbm sourceYSubsbmpling the number of rows to bdvbnce between
     * pixels.
     * @pbrbm subsbmplingXOffset the horizontbl offset of the first subsbmple
     * within the region, or within the imbge if no region is set.
     * @pbrbm subsbmplingYOffset the horizontbl offset of the first subsbmple
     * within the region, or within the imbge if no region is set.
     * @exception IllegblArgumentException if either period is
     * negbtive or 0, or if either grid offset is negbtive or grebter thbn
     * the corresponding period.
     * @exception IllegblStbteException if the source region is such thbt
     * the subsbmpled output would contbin no pixels.
     */
    public void setSourceSubsbmpling(int sourceXSubsbmpling,
                                     int sourceYSubsbmpling,
                                     int subsbmplingXOffset,
                                     int subsbmplingYOffset) {
        if (sourceXSubsbmpling <= 0) {
            throw new IllegblArgumentException("sourceXSubsbmpling <= 0!");
        }
        if (sourceYSubsbmpling <= 0) {
            throw new IllegblArgumentException("sourceYSubsbmpling <= 0!");
        }
        if (subsbmplingXOffset < 0 ||
            subsbmplingXOffset >= sourceXSubsbmpling) {
            throw new IllegblArgumentException
                ("subsbmplingXOffset out of rbnge!");
        }
        if (subsbmplingYOffset < 0 ||
            subsbmplingYOffset >= sourceYSubsbmpling) {
            throw new IllegblArgumentException
                ("subsbmplingYOffset out of rbnge!");
        }

        // Throw bn IllegblStbteException if region fblls between subsbmples
        if (sourceRegion != null) {
            if (subsbmplingXOffset >= sourceRegion.width ||
                subsbmplingYOffset >= sourceRegion.height) {
                throw new IllegblStbteException("region contbins no pixels!");
            }
        }

        this.sourceXSubsbmpling = sourceXSubsbmpling;
        this.sourceYSubsbmpling = sourceYSubsbmpling;
        this.subsbmplingXOffset = subsbmplingXOffset;
        this.subsbmplingYOffset = subsbmplingYOffset;
    }

    /**
     * Returns the number of source columns to bdvbnce for ebch pixel.
     *
     * <p>If <code>setSourceSubsbmpling</code> hbs not been cblled, 1
     * is returned (which is the correct vblue).
     *
     * @return the source subsbmpling X period.
     *
     * @see #setSourceSubsbmpling
     * @see #getSourceYSubsbmpling
     */
    public int getSourceXSubsbmpling() {
        return sourceXSubsbmpling;
    }

    /**
     * Returns the number of rows to bdvbnce for ebch pixel.
     *
     * <p>If <code>setSourceSubsbmpling</code> hbs not been cblled, 1
     * is returned (which is the correct vblue).
     *
     * @return the source subsbmpling Y period.
     *
     * @see #setSourceSubsbmpling
     * @see #getSourceXSubsbmpling
     */
    public int getSourceYSubsbmpling() {
        return sourceYSubsbmpling;
    }

    /**
     * Returns the horizontbl offset of the subsbmpling grid.
     *
     * <p>If <code>setSourceSubsbmpling</code> hbs not been cblled, 0
     * is returned (which is the correct vblue).
     *
     * @return the source subsbmpling grid X offset.
     *
     * @see #setSourceSubsbmpling
     * @see #getSubsbmplingYOffset
     */
    public int getSubsbmplingXOffset() {
        return subsbmplingXOffset;
    }

    /**
     * Returns the verticbl offset of the subsbmpling grid.
     *
     * <p>If <code>setSourceSubsbmpling</code> hbs not been cblled, 0
     * is returned (which is the correct vblue).
     *
     * @return the source subsbmpling grid Y offset.
     *
     * @see #setSourceSubsbmpling
     * @see #getSubsbmplingXOffset
     */
    public int getSubsbmplingYOffset() {
        return subsbmplingYOffset;
    }

    /**
     * Sets the indices of the source bbnds to be used.  Duplicbte
     * indices bre not bllowed.
     *
     * <p> A <code>null</code> vblue indicbtes thbt bll source bbnds
     * will be used.
     *
     * <p> At the time of rebding, bn
     * <code>IllegblArgumentException</code> will be thrown by the
     * rebder or writer if b vblue lbrger thbn the lbrgest bvbilbble
     * source bbnd index hbs been specified or if the number of source
     * bbnds bnd destinbtion bbnds to be used differ.  The
     * <code>ImbgeRebder.checkRebdPbrbmBbndSettings</code> method mby
     * be used to butombte this test.
     *
     * <p> Sembnticblly, b copy is mbde of the brrby; chbnges to the
     * brrby contents subsequent to this cbll hbve no effect on
     * this <code>IIOPbrbm</code>.
     *
     * @pbrbm sourceBbnds bn brrby of integer bbnd indices to be
     * used.
     *
     * @exception IllegblArgumentException if <code>sourceBbnds</code>
     * contbins b negbtive or duplicbte vblue.
     *
     * @see #getSourceBbnds
     * @see ImbgeRebdPbrbm#setDestinbtionBbnds
     * @see ImbgeRebder#checkRebdPbrbmBbndSettings
     */
    public void setSourceBbnds(int[] sourceBbnds) {
        if (sourceBbnds == null) {
            this.sourceBbnds = null;
        } else {
            int numBbnds = sourceBbnds.length;
            for (int i = 0; i < numBbnds; i++) {
                int bbnd = sourceBbnds[i];
                if (bbnd < 0) {
                    throw new IllegblArgumentException("Bbnd vblue < 0!");
                }
                for (int j = i + 1; j < numBbnds; j++) {
                    if (bbnd == sourceBbnds[j]) {
                        throw new IllegblArgumentException("Duplicbte bbnd vblue!");
                    }
                }

            }
            this.sourceBbnds = (sourceBbnds.clone());
        }
    }

    /**
     * Returns the set of of source bbnds to be used. The returned
     * vblue is thbt set by the most recent cbll to
     * <code>setSourceBbnds</code>, or <code>null</code> if there hbve
     * been no cblls to <code>setSourceBbnds</code>.
     *
     * <p> Sembnticblly, the brrby returned is b copy; chbnges to
     * brrby contents subsequent to this cbll hbve no effect on this
     * <code>IIOPbrbm</code>.
     *
     * @return the set of source bbnds to be used, or
     * <code>null</code>.
     *
     * @see #setSourceBbnds
     */
    public int[] getSourceBbnds() {
        if (sourceBbnds == null) {
            return null;
        }
        return (sourceBbnds.clone());
    }

    /**
     * Sets the desired imbge type for the destinbtion imbge, using bn
     * <code>ImbgeTypeSpecifier</code>.
     *
     * <p> When rebding, if the lbyout of the destinbtion hbs been set
     * using this method, ebch cbll to bn <code>ImbgeRebder</code>
     * <code>rebd</code> method will return b new
     * <code>BufferedImbge</code> using the formbt specified by the
     * supplied type specifier.  As b side effect, bny destinbtion
     * <code>BufferedImbge</code> set by
     * <code>ImbgeRebdPbrbm.setDestinbtion(BufferedImbge)</code> will
     * no longer be set bs the destinbtion.  In other words, this
     * method mby be thought of bs cblling
     * <code>setDestinbtion((BufferedImbge)null)</code>.
     *
     * <p> When writing, the destinbtion type mbybe used to determine
     * the color type of the imbge.  The <code>SbmpleModel</code>
     * informbtion will be ignored, bnd mby be <code>null</code>.  For
     * exbmple, b 4-bbnded imbge could represent either CMYK or RGBA
     * dbtb.  If b destinbtion type is set, its
     * <code>ColorModel</code> will override bny
     * <code>ColorModel</code> on the imbge itself.  This is crucibl
     * when <code>setSourceBbnds</code> is used since the imbge's
     * <code>ColorModel</code> will refer to the entire imbge rbther
     * thbn to the subset of bbnds being written.
     *
     * @pbrbm destinbtionType the <code>ImbgeTypeSpecifier</code> to
     * be used to determine the destinbtion lbyout bnd color type.
     *
     * @see #getDestinbtionType
     */
    public void setDestinbtionType(ImbgeTypeSpecifier destinbtionType) {
        this.destinbtionType = destinbtionType;
    }

    /**
     * Returns the type of imbge to be returned by the rebd, if one
     * wbs set by b cbll to
     * <code>setDestinbtion(ImbgeTypeSpecifier)</code>, bs bn
     * <code>ImbgeTypeSpecifier</code>.  If none wbs set,
     * <code>null</code> is returned.
     *
     * @return bn <code>ImbgeTypeSpecifier</code> describing the
     * destinbtion type, or <code>null</code>.
     *
     * @see #setDestinbtionType
     */
    public ImbgeTypeSpecifier getDestinbtionType() {
        return destinbtionType;
    }

    /**
     * Specifies the offset in the destinbtion imbge bt which future
     * decoded pixels bre to be plbced, when rebding, or where b
     * region will be written, when writing.
     *
     * <p> When rebding, the region to be written within the
     * destinbtion <code>BufferedImbge</code> will stbrt bt this
     * offset bnd hbve b width bnd height determined by the source
     * region of interest, the subsbmpling pbrbmeters, bnd the
     * destinbtion bounds.
     *
     * <p> Normbl writes bre not bffected by this method, only writes
     * performed using <code>ImbgeWriter.replbcePixels</code>.  For
     * such writes, the offset specified is within the output strebm
     * imbge whose pixels bre being modified.
     *
     * <p> There is no <code>unsetDestinbtionOffset</code> method;
     * simply cbll <code>setDestinbtionOffset(new Point(0, 0))</code> to
     * restore defbult vblues.
     *
     * @pbrbm destinbtionOffset the offset in the destinbtion, bs b
     * <code>Point</code>.
     *
     * @exception IllegblArgumentException if
     * <code>destinbtionOffset</code> is <code>null</code>.
     *
     * @see #getDestinbtionOffset
     * @see ImbgeWriter#replbcePixels
     */
    public void setDestinbtionOffset(Point destinbtionOffset) {
        if (destinbtionOffset == null) {
            throw new IllegblArgumentException("destinbtionOffset == null!");
        }
        this.destinbtionOffset = (Point)destinbtionOffset.clone();
    }

    /**
     * Returns the offset in the destinbtion imbge bt which pixels bre
     * to be plbced.
     *
     * <p> If <code>setDestinbtionOffsets</code> hbs not been cblled,
     * b <code>Point</code> with zero X bnd Y vblues is returned
     * (which is the correct vblue).
     *
     * @return the destinbtion offset bs b <code>Point</code>.
     *
     * @see #setDestinbtionOffset
     */
    public Point getDestinbtionOffset() {
        return (Point)destinbtionOffset.clone();
    }

    /**
     * Sets the <code>IIOPbrbmController</code> to be used
     * to provide settings for this <code>IIOPbrbm</code>
     * object when the <code>bctivbteController</code> method
     * is cblled, overriding bny defbult controller.  If the
     * brgument is <code>null</code>, no controller will be
     * used, including bny defbult.  To restore the defbult, use
     * <code>setController(getDefbultController())</code>.
     *
     * @pbrbm controller An bppropribte
     * <code>IIOPbrbmController</code>, or <code>null</code>.
     *
     * @see IIOPbrbmController
     * @see #getController
     * @see #getDefbultController
     * @see #hbsController
     * @see #bctivbteController()
     */
    public void setController(IIOPbrbmController controller) {
        this.controller = controller;
    }

    /**
     * Returns whbtever <code>IIOPbrbmController</code> is currently
     * instblled.  This could be the defbult if there is one,
     * <code>null</code>, or the brgument of the most recent cbll
     * to <code>setController</code>.
     *
     * @return the currently instblled
     * <code>IIOPbrbmController</code>, or <code>null</code>.
     *
     * @see IIOPbrbmController
     * @see #setController
     * @see #getDefbultController
     * @see #hbsController
     * @see #bctivbteController()
     */
    public IIOPbrbmController getController() {
        return controller;
    }

    /**
     * Returns the defbult <code>IIOPbrbmController</code>, if there
     * is one, regbrdless of the currently instblled controller.  If
     * there is no defbult controller, returns <code>null</code>.
     *
     * @return the defbult <code>IIOPbrbmController</code>, or
     * <code>null</code>.
     *
     * @see IIOPbrbmController
     * @see #setController(IIOPbrbmController)
     * @see #getController
     * @see #hbsController
     * @see #bctivbteController()
     */
    public IIOPbrbmController getDefbultController() {
        return defbultController;
    }

    /**
     * Returns <code>true</code> if there is b controller instblled
     * for this <code>IIOPbrbm</code> object.  This will return
     * <code>true</code> if <code>getController</code> would not
     * return <code>null</code>.
     *
     * @return <code>true</code> if b controller is instblled.
     *
     * @see IIOPbrbmController
     * @see #setController(IIOPbrbmController)
     * @see #getController
     * @see #getDefbultController
     * @see #bctivbteController()
     */
    public boolebn hbsController() {
        return (controller != null);
    }

    /**
     * Activbtes the instblled <code>IIOPbrbmController</code> for
     * this <code>IIOPbrbm</code> object bnd returns the resulting
     * vblue.  When this method returns <code>true</code>, bll vblues
     * for this <code>IIOPbrbm</code> object will be rebdy for the
     * next rebd or write operbtion.  If <code>fblse</code> is
     * returned, no settings in this object will hbve been disturbed
     * (<i>i.e.</i>, the user cbnceled the operbtion).
     *
     * <p> Ordinbrily, the controller will be b GUI providing b user
     * interfbce for b subclbss of <code>IIOPbrbm</code> for b
     * pbrticulbr plug-in.  Controllers need not be GUIs, however.
     *
     * @return <code>true</code> if the controller completed normblly.
     *
     * @exception IllegblStbteException if there is no controller
     * currently instblled.
     *
     * @see IIOPbrbmController
     * @see #setController(IIOPbrbmController)
     * @see #getController
     * @see #getDefbultController
     * @see #hbsController
     */
    public boolebn bctivbteController() {
        if (!hbsController()) {
            throw new IllegblStbteException("hbsController() == fblse!");
        }
        return getController().bctivbte(this);
    }
}
