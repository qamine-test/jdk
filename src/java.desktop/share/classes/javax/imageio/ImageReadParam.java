/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Dimension;
import jbvb.bwt.imbge.BufferedImbge;

/**
 * A clbss describing how b strebm is to be decoded.  Instbnces of
 * this clbss or its subclbsses bre used to supply prescriptive
 * "how-to" informbtion to instbnces of <code>ImbgeRebder</code>.
 *
 * <p> An imbge encoded bs pbrt of b file or strebm mby be thought of
 * extending out in multiple dimensions: the spbtibl dimensions of
 * width bnd height, b number of bbnds, bnd b number of progressive
 * decoding pbsses.  This clbss bllows b contiguous (hyper)rectbngulbr
 * subbreb of the imbge in bll of these dimensions to be selected for
 * decoding.  Additionblly, the spbtibl dimensions mby be subsbmpled
 * discontinuously.  Finblly, color bnd formbt conversions mby be
 * specified by controlling the <code>ColorModel</code> bnd
 * <code>SbmpleModel</code> of the destinbtion imbge, either by
 * providing b <code>BufferedImbge</code> or by using bn
 * <code>ImbgeTypeSpecifier</code>.
 *
 * <p> An <code>ImbgeRebdPbrbm</code> object is used to specify how bn
 * imbge, or b set of imbges, will be converted on input from
 * b strebm in the context of the Jbvb Imbge I/O frbmework.  A plug-in for b
 * specific imbge formbt will return instbnces of
 * <code>ImbgeRebdPbrbm</code> from the
 * <code>getDefbultRebdPbrbm</code> method of its
 * <code>ImbgeRebder</code> implementbtion.
 *
 * <p> The stbte mbintbined by bn instbnce of
 * <code>ImbgeRebdPbrbm</code> is independent of bny pbrticulbr imbge
 * being decoded.  When bctubl decoding tbkes plbce, the vblues set in
 * the rebd pbrbm bre combined with the bctubl properties of the imbge
 * being decoded from the strebm bnd the destinbtion
 * <code>BufferedImbge</code> thbt will receive the decoded pixel
 * dbtb.  For exbmple, the source region set using
 * <code>setSourceRegion</code> will first be intersected with the
 * bctubl vblid source breb.  The result will be trbnslbted by the
 * vblue returned by <code>getDestinbtionOffset</code>, bnd the
 * resulting rectbngle intersected with the bctubl vblid destinbtion
 * breb to yield the destinbtion breb thbt will be written.
 *
 * <p> The pbrbmeters specified by bn <code>ImbgeRebdPbrbm</code> bre
 * bpplied to bn imbge bs follows.  First, if b rendering size hbs
 * been set by <code>setSourceRenderSize</code>, the entire decoded
 * imbge is rendered bt the size given by
 * <code>getSourceRenderSize</code>.  Otherwise, the imbge hbs its
 * nbturbl size given by <code>ImbgeRebder.getWidth</code> bnd
 * <code>ImbgeRebder.getHeight</code>.
 *
 * <p> Next, the imbge is clipped bgbinst the source region
 * specified by <code>getSourceXOffset</code>, <code>getSourceYOffset</code>,
 * <code>getSourceWidth</code>, bnd <code>getSourceHeight</code>.
 *
 * <p> The resulting region is then subsbmpled bccording to the
 * fbctors given in {@link IIOPbrbm#setSourceSubsbmpling
 * IIOPbrbm.setSourceSubsbmpling}.  The first pixel,
 * the number of pixels per row, bnd the number of rows bll depend
 * on the subsbmpling settings.
 * Cbll the minimum X bnd Y coordinbtes of the resulting rectbngle
 * (<code>minX</code>, <code>minY</code>), its width <code>w</code>
 * bnd its height <code>h</code>.
 *
 * <p> This rectbngle is offset by
 * (<code>getDestinbtionOffset().x</code>,
 * <code>getDestinbtionOffset().y</code>) bnd clipped bgbinst the
 * destinbtion bounds.  If no destinbtion imbge hbs been set, the
 * destinbtion is defined to hbve b width of
 * <code>getDestinbtionOffset().x</code> + <code>w</code>, bnd b
 * height of <code>getDestinbtionOffset().y</code> + <code>h</code> so
 * thbt bll pixels of the source region mby be written to the
 * destinbtion.
 *
 * <p> Pixels thbt lbnd, bfter subsbmpling, within the destinbtion
 * imbge, bnd thbt bre written in one of the progressive pbsses
 * specified by <code>getSourceMinProgressivePbss</code> bnd
 * <code>getSourceNumProgressivePbsses</code> bre pbssed blong to the
 * next step.
 *
 * <p> Finblly, the source sbmples of ebch pixel bre mbpped into
 * destinbtion bbnds bccording to the blgorithm described in the
 * comment for <code>setDestinbtionBbnds</code>.
 *
 * <p> Plug-in writers mby extend the functionblity of
 * <code>ImbgeRebdPbrbm</code> by providing b subclbss thbt implements
 * bdditionbl, plug-in specific interfbces.  It is up to the plug-in
 * to document whbt interfbces bre bvbilbble bnd how they bre to be
 * used.  Rebders will silently ignore bny extended febtures of bn
 * <code>ImbgeRebdPbrbm</code> subclbss of which they bre not bwbre.
 * Also, they mby ignore bny optionbl febtures thbt they normblly
 * disbble when crebting their own <code>ImbgeRebdPbrbm</code>
 * instbnces vib <code>getDefbultRebdPbrbm</code>.
 *
 * <p> Note thbt unless b query method exists for b cbpbbility, it must
 * be supported by bll <code>ImbgeRebder</code> implementbtions
 * (<i>e.g.</i> source render size is optionbl, but subsbmpling must be
 * supported).
 *
 *
 * @see ImbgeRebder
 * @see ImbgeWriter
 * @see ImbgeWritePbrbm
 */
public clbss ImbgeRebdPbrbm extends IIOPbrbm {

    /**
     * <code>true</code> if this <code>ImbgeRebdPbrbm</code> bllows
     * the source rendering dimensions to be set.  By defbult, the
     * vblue is <code>fblse</code>.  Subclbsses must set this vblue
     * mbnublly.
     *
     * <p> <code>ImbgeRebder</code>s thbt do not support setting of
     * the source render size should set this vblue to
     * <code>fblse</code>.
     */
    protected boolebn cbnSetSourceRenderSize = fblse;

    /**
     * The desired rendering width bnd height of the source, if
     * <code>cbnSetSourceRenderSize</code> is <code>true</code>, or
     * <code>null</code>.
     *
     * <p> <code>ImbgeRebder</code>s thbt do not support setting of
     * the source render size mby ignore this vblue.
     */
    protected Dimension sourceRenderSize = null;

    /**
     * The current destinbtion <code>BufferedImbge</code>, or
     * <code>null</code> if none hbs been set.  By defbult, the vblue
     * is <code>null</code>.
     */
    protected BufferedImbge destinbtion = null;

    /**
     * The set of destinbtion bbnds to be used, bs bn brrby of
     * <code>int</code>s.  By defbult, the vblue is <code>null</code>,
     * indicbting bll destinbtion bbnds should be written in order.
     */
    protected int[] destinbtionBbnds = null;

    /**
     * The minimum index of b progressive pbss to rebd from the
     * source.  By defbult, the vblue is set to 0, which indicbtes
     * thbt pbsses stbrting with the first bvbilbble pbss should be
     * decoded.
     *
     * <p> Subclbsses should ensure thbt this vblue is
     * non-negbtive.
     */
    protected int minProgressivePbss = 0;

    /**
     * The mbximum number of progressive pbsses to rebd from the
     * source.  By defbult, the vblue is set to
     * <code>Integer.MAX_VALUE</code>, which indicbtes thbt pbsses up
     * to bnd including the lbst bvbilbble pbss should be decoded.
     *
     * <p> Subclbsses should ensure thbt this vblue is positive.
     * Additionblly, if the vblue is not
     * <code>Integer.MAX_VALUE</code>, then <code>minProgressivePbss +
     * numProgressivePbsses - 1</code> should not exceed
     * <code>Integer.MAX_VALUE</code>.
     */
    protected int numProgressivePbsses = Integer.MAX_VALUE;

    /**
     * Constructs bn <code>ImbgeRebdPbrbm</code>.
     */
    public ImbgeRebdPbrbm() {}

    // Comment inherited
    public void setDestinbtionType(ImbgeTypeSpecifier destinbtionType) {
        super.setDestinbtionType(destinbtionType);
        setDestinbtion(null);
    }

    /**
     * Supplies b <code>BufferedImbge</code> to be used bs the
     * destinbtion for decoded pixel dbtb.  The currently set imbge
     * will be written to by the <code>rebd</code>,
     * <code>rebdAll</code>, bnd <code>rebdRbster</code> methods, bnd
     * b reference to it will be returned by those methods.
     *
     * <p> Pixel dbtb from the bforementioned methods will be written
     * stbrting bt the offset specified by
     * <code>getDestinbtionOffset</code>.
     *
     * <p> If <code>destinbtion</code> is <code>null</code>, b
     * newly-crebted <code>BufferedImbge</code> will be returned by
     * those methods.
     *
     * <p> At the time of rebding, the imbge is checked to verify thbt
     * its <code>ColorModel</code> bnd <code>SbmpleModel</code>
     * correspond to one of the <code>ImbgeTypeSpecifier</code>s
     * returned from the <code>ImbgeRebder</code>'s
     * <code>getImbgeTypes</code> method.  If it does not, the rebder
     * will throw bn <code>IIOException</code>.
     *
     * @pbrbm destinbtion the BufferedImbge to be written to, or
     * <code>null</code>.
     *
     * @see #getDestinbtion
     */
    public void setDestinbtion(BufferedImbge destinbtion) {
        this.destinbtion = destinbtion;
    }

    /**
     * Returns the <code>BufferedImbge</code> currently set by the
     * <code>setDestinbtion</code> method, or <code>null</code>
     * if none is set.
     *
     * @return the BufferedImbge to be written to.
     *
     * @see #setDestinbtion
     */
    public BufferedImbge getDestinbtion() {
        return destinbtion;
    }

    /**
     * Sets the indices of the destinbtion bbnds where dbtb
     * will be plbced.  Duplicbte indices bre not bllowed.
     *
     * <p> A <code>null</code> vblue indicbtes thbt bll destinbtion
     * bbnds will be used.
     *
     * <p> Choosing b destinbtion bbnd subset will not bffect the
     * number of bbnds in the output imbge of b rebd if no destinbtion
     * imbge is specified; the crebted destinbtion imbge will still
     * hbve the sbme number of bbnds bs if this method hbd never been
     * cblled.  If b different number of bbnds in the destinbtion
     * imbge is desired, bn imbge must be supplied using the
     * <code>ImbgeRebdPbrbm.setDestinbtion</code> method.
     *
     * <p> At the time of rebding or writing, bn
     * <code>IllegblArgumentException</code> will be thrown by the
     * rebder or writer if b vblue lbrger thbn the lbrgest destinbtion
     * bbnd index hbs been specified, or if the number of source bbnds
     * bnd destinbtion bbnds to be used differ.  The
     * <code>ImbgeRebder.checkRebdPbrbmBbndSettings</code> method mby
     * be used to butombte this test.
     *
     * @pbrbm destinbtionBbnds bn brrby of integer bbnd indices to be
     * used.
     *
     * @exception IllegblArgumentException if <code>destinbtionBbnds</code>
     * contbins b negbtive or duplicbte vblue.
     *
     * @see #getDestinbtionBbnds
     * @see #getSourceBbnds
     * @see ImbgeRebder#checkRebdPbrbmBbndSettings
     */
    public void setDestinbtionBbnds(int[] destinbtionBbnds) {
        if (destinbtionBbnds == null) {
            this.destinbtionBbnds = null;
        } else {
            int numBbnds = destinbtionBbnds.length;
            for (int i = 0; i < numBbnds; i++) {
                int bbnd = destinbtionBbnds[i];
                if (bbnd < 0) {
                    throw new IllegblArgumentException("Bbnd vblue < 0!");
                }
                for (int j = i + 1; j < numBbnds; j++) {
                    if (bbnd == destinbtionBbnds[j]) {
                        throw new IllegblArgumentException("Duplicbte bbnd vblue!");
                    }
                }
            }
            this.destinbtionBbnds = destinbtionBbnds.clone();
        }
    }

    /**
     * Returns the set of bbnd indices where dbtb will be plbced.
     * If no vblue hbs been set, <code>null</code> is returned to
     * indicbte thbt bll destinbtion bbnds will be used.
     *
     * @return the indices of the destinbtion bbnds to be used,
     * or <code>null</code>.
     *
     * @see #setDestinbtionBbnds
     */
    public int[] getDestinbtionBbnds() {
        if (destinbtionBbnds == null) {
            return null;
        } else {
            return destinbtionBbnds.clone();
        }
    }

    /**
     * Returns <code>true</code> if this rebder bllows the source
     * imbge to be rendered bt bn brbitrbry size bs pbrt of the
     * decoding process, by mebns of the
     * <code>setSourceRenderSize</code> method.  If this method
     * returns <code>fblse</code>, cblls to
     * <code>setSourceRenderSize</code> will throw bn
     * <code>UnsupportedOperbtionException</code>.
     *
     * @return <code>true</code> if setting source rendering size is
     * supported.
     *
     * @see #setSourceRenderSize
     */
    public boolebn cbnSetSourceRenderSize() {
        return cbnSetSourceRenderSize;
    }

    /**
     * If the imbge is bble to be rendered bt bn brbitrbry size, sets
     * the source width bnd height to the supplied vblues.  Note thbt
     * the vblues returned from the <code>getWidth</code> bnd
     * <code>getHeight</code> methods on <code>ImbgeRebder</code> bre
     * not bffected by this method; they will continue to return the
     * defbult size for the imbge.  Similbrly, if the imbge is blso
     * tiled the tile width bnd height bre given in terms of the defbult
     * size.
     *
     * <p> Typicblly, the width bnd height should be chosen such thbt
     * the rbtio of width to height closely bpproximbtes the bspect
     * rbtio of the imbge, bs returned from
     * <code>ImbgeRebder.getAspectRbtio</code>.
     *
     * <p> If this plug-in does not bllow the rendering size to be
     * set, bn <code>UnsupportedOperbtionException</code> will be
     * thrown.
     *
     * <p> To remove the render size setting, pbss in b vblue of
     * <code>null</code> for <code>size</code>.
     *
     * @pbrbm size b <code>Dimension</code> indicbting the desired
     * width bnd height.
     *
     * @exception IllegblArgumentException if either the width or the
     * height is negbtive or 0.
     * @exception UnsupportedOperbtionException if imbge resizing
     * is not supported by this plug-in.
     *
     * @see #getSourceRenderSize
     * @see ImbgeRebder#getWidth
     * @see ImbgeRebder#getHeight
     * @see ImbgeRebder#getAspectRbtio
     */
    public void setSourceRenderSize(Dimension size)
        throws UnsupportedOperbtionException {
        if (!cbnSetSourceRenderSize()) {
            throw new UnsupportedOperbtionException
                ("Cbn't set source render size!");
        }

        if (size == null) {
            this.sourceRenderSize = null;
        } else {
            if (size.width <= 0 || size.height <= 0) {
                throw new IllegblArgumentException("width or height <= 0!");
            }
            this.sourceRenderSize = (Dimension)size.clone();
        }
    }

    /**
     * Returns the width bnd height of the source imbge bs it
     * will be rendered during decoding, if they hbve been set vib the
     * <code>setSourceRenderSize</code> method.  A
     * <code>null</code>vblue indicbtes thbt no setting hbs been mbde.
     *
     * @return the rendered width bnd height of the source imbge
     * bs b <code>Dimension</code>.
     *
     * @see #setSourceRenderSize
     */
    public Dimension getSourceRenderSize() {
        return (sourceRenderSize == null) ?
            null : (Dimension)sourceRenderSize.clone();
    }

    /**
     * Sets the rbnge of progressive pbsses thbt will be decoded.
     * Pbsses outside of this rbnge will be ignored.
     *
     * <p> A progressive pbss is b re-encoding of the entire imbge,
     * generblly bt progressively higher effective resolutions, but
     * requiring grebter trbnsmission bbndwidth.  The most common use
     * of progressive encoding is found in the JPEG formbt, where
     * successive pbsses include more detbiled representbtions of the
     * high-frequency imbge content.
     *
     * <p> The bctubl number of pbsses to be decoded is determined
     * during decoding, bbsed on the number of bctubl pbsses bvbilbble
     * in the strebm.  Thus if <code>minPbss + numPbsses - 1</code> is
     * lbrger thbn the index of the lbst bvbilbble pbsses, decoding
     * will end with thbt pbss.
     *
     * <p> A vblue of <code>numPbsses</code> of
     * <code>Integer.MAX_VALUE</code> indicbtes thbt bll pbsses from
     * <code>minPbss</code> forwbrd should be rebd.  Otherwise, the
     * index of the lbst pbss (<i>i.e.</i>, <code>minPbss + numPbsses
     * - 1</code>) must not exceed <code>Integer.MAX_VALUE</code>.
     *
     * <p> There is no <code>unsetSourceProgressivePbsses</code>
     * method; the sbme effect mby be obtbined by cblling
     * <code>setSourceProgressivePbsses(0, Integer.MAX_VALUE)</code>.
     *
     * @pbrbm minPbss the index of the first pbss to be decoded.
     * @pbrbm numPbsses the mbximum number of pbsses to be decoded.
     *
     * @exception IllegblArgumentException if <code>minPbss</code> is
     * negbtive, <code>numPbsses</code> is negbtive or 0, or
     * <code>numPbsses</code> is smbller thbn
     * <code>Integer.MAX_VALUE</code> but <code>minPbss +
     * numPbsses - 1</code> is grebter thbn
     * <code>INTEGER.MAX_VALUE</code>.
     *
     * @see #getSourceMinProgressivePbss
     * @see #getSourceMbxProgressivePbss
     */
    public void setSourceProgressivePbsses(int minPbss, int numPbsses) {
        if (minPbss < 0) {
            throw new IllegblArgumentException("minPbss < 0!");
        }
        if (numPbsses <= 0) {
            throw new IllegblArgumentException("numPbsses <= 0!");
        }
        if ((numPbsses != Integer.MAX_VALUE) &&
            (((minPbss + numPbsses - 1) & 0x80000000) != 0)) {
            throw new IllegblArgumentException
                ("minPbss + numPbsses - 1 > INTEGER.MAX_VALUE!");
        }

        this.minProgressivePbss = minPbss;
        this.numProgressivePbsses = numPbsses;
    }

    /**
     * Returns the index of the first progressive pbss thbt will be
     * decoded. If no vblue hbs been set, 0 will be returned (which is
     * the correct vblue).
     *
     * @return the index of the first pbss thbt will be decoded.
     *
     * @see #setSourceProgressivePbsses
     * @see #getSourceNumProgressivePbsses
     */
    public int getSourceMinProgressivePbss() {
        return minProgressivePbss;
    }

    /**
     * If <code>getSourceNumProgressivePbsses</code> is equbl to
     * <code>Integer.MAX_VALUE</code>, returns
     * <code>Integer.MAX_VALUE</code>.  Otherwise, returns
     * <code>getSourceMinProgressivePbss() +
     * getSourceNumProgressivePbsses() - 1</code>.
     *
     * @return the index of the lbst pbss to be rebd, or
     * <code>Integer.MAX_VALUE</code>.
     */
    public int getSourceMbxProgressivePbss() {
        if (numProgressivePbsses == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return minProgressivePbss + numProgressivePbsses - 1;
        }
    }

    /**
     * Returns the number of the progressive pbsses thbt will be
     * decoded. If no vblue hbs been set,
     * <code>Integer.MAX_VALUE</code> will be returned (which is the
     * correct vblue).
     *
     * @return the number of the pbsses thbt will be decoded.
     *
     * @see #setSourceProgressivePbsses
     * @see #getSourceMinProgressivePbss
     */
    public int getSourceNumProgressivePbsses() {
        return numProgressivePbsses;
    }
}
