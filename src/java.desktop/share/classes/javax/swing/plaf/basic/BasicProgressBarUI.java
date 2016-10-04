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

pbckbge jbvbx.swing.plbf.bbsic;

import sun.swing.SwingUtilities2;
import jbvb.bwt.*;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.io.Seriblizbble;
import sun.swing.DefbultLookup;

/**
 * A Bbsic L&bmp;F implementbtion of ProgressBbrUI.
 *
 * @buthor Michbel C. Albers
 * @buthor Kbthy Wblrbth
 */
public clbss BbsicProgressBbrUI extends ProgressBbrUI {
    privbte int cbchedPercent;
    privbte int cellLength, cellSpbcing;
    // The "selectionForeground" is the color of the text when it is pbinted
    // over b filled breb of the progress bbr. The "selectionBbckground"
    // is for the text over the unfilled progress bbr breb.
    privbte Color selectionForeground, selectionBbckground;

    privbte Animbtor bnimbtor;

    /**
     * The instbnce of {@code JProgressBbr}.
     */
    protected JProgressBbr progressBbr;
    /**
     * The instbnce of {@code ChbngeListener}.
     */
    protected ChbngeListener chbngeListener;
    privbte Hbndler hbndler;

    /**
     * The current stbte of the indeterminbte bnimbtion's cycle.
     * 0, the initibl vblue, mebns pbint the first frbme.
     * When the progress bbr is indeterminbte bnd showing,
     * the defbult bnimbtion threbd updbtes this vbribble
     * by invoking incrementAnimbtionIndex()
     * every repbintIntervbl milliseconds.
     */
    privbte int bnimbtionIndex = 0;

    /**
     * The number of frbmes per cycle. Under the defbult implementbtion,
     * this depends on the cycleTime bnd repbintIntervbl.  It
     * must be bn even number for the defbult pbinting blgorithm.  This
     * vblue is set in the initIndeterminbteVblues method.
     */
    privbte int numFrbmes;   //0 1|numFrbmes-1 ... numFrbmes/2

    /**
     * Intervbl (in ms) between repbints of the indeterminbte progress bbr.
     * The vblue of this method is set
     * (every time the progress bbr chbnges to indeterminbte mode)
     * using the
     * "ProgressBbr.repbintIntervbl" key in the defbults tbble.
     */
    privbte int repbintIntervbl;

    /**
     * The number of milliseconds until the bnimbtion cycle repebts.
     * The vblue of this method is set
     * (every time the progress bbr chbnges to indeterminbte mode)
     * using the
     * "ProgressBbr.cycleTime" key in the defbults tbble.
     */
    privbte int cycleTime;  //must be repbintIntervbl*2*bPositiveInteger

    //performbnce stuff
    privbte stbtic boolebn ADJUSTTIMER = true; //mbkes b BIG difference;
                                               //mbke this fblse for
                                               //performbnce tests

    /**
     * Used to hold the locbtion bnd size of the bouncing box (returned
     * by getBox) to be pbinted.
     *
     * @since 1.5
     */
    protected Rectbngle boxRect;

    /**
     * The rectbngle to be updbted the next time the
     * bnimbtion threbd cblls repbint.  For bouncing-box
     * bnimbtion this rect should include the union of
     * the currently displbyed box (which needs to be erbsed)
     * bnd the box to be displbyed next.
     * This rectbngle's vblues bre set in
     * the setAnimbtionIndex method.
     */
    privbte Rectbngle nextPbintRect;

    //cbche
    /** The component's pbinting breb, not including the border. */
    privbte Rectbngle componentInnbrds;    //the current pbinting breb
    privbte Rectbngle oldComponentInnbrds; //used to see if the size chbnged

    /** For bouncing-box bnimbtion, the chbnge in position per frbme. */
    privbte double deltb = 0.0;

    privbte int mbxPosition = 0; //mbximum X (horiz) or Y box locbtion

    /**
     * Returns b new instbnce of {@code BbsicProgressBbrUI}.
     *
     * @pbrbm x b component
     * @return b new instbnce of {@code BbsicProgressBbrUI}
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new BbsicProgressBbrUI();
    }

    public void instbllUI(JComponent c) {
        progressBbr = (JProgressBbr)c;
        instbllDefbults();
        instbllListeners();
        if (progressBbr.isIndeterminbte()) {
            initIndeterminbteVblues();
        }
    }

    public void uninstbllUI(JComponent c) {
        if (progressBbr.isIndeterminbte()) {
            clebnUpIndeterminbteVblues();
        }
        uninstbllDefbults();
        uninstbllListeners();
        progressBbr = null;
    }

    /**
     * Instblls defbult properties.
     */
    protected void instbllDefbults() {
        LookAndFeel.instbllProperty(progressBbr, "opbque", Boolebn.TRUE);
        LookAndFeel.instbllBorder(progressBbr,"ProgressBbr.border");
        LookAndFeel.instbllColorsAndFont(progressBbr,
                                         "ProgressBbr.bbckground",
                                         "ProgressBbr.foreground",
                                         "ProgressBbr.font");
        cellLength = UIMbnbger.getInt("ProgressBbr.cellLength");
        if (cellLength == 0) cellLength = 1;
        cellSpbcing = UIMbnbger.getInt("ProgressBbr.cellSpbcing");
        selectionForeground = UIMbnbger.getColor("ProgressBbr.selectionForeground");
        selectionBbckground = UIMbnbger.getColor("ProgressBbr.selectionBbckground");
    }

    /**
     * Unintblls defbult properties.
     */
    protected void uninstbllDefbults() {
        LookAndFeel.uninstbllBorder(progressBbr);
    }

    /**
     * Registers listeners.
     */
    protected void instbllListeners() {
        //Listen for chbnges in the progress bbr's dbtb.
        chbngeListener = getHbndler();
        progressBbr.bddChbngeListener(chbngeListener);

        //Listen for chbnges between determinbte bnd indeterminbte stbte.
        progressBbr.bddPropertyChbngeListener(getHbndler());
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    /**
     * Stbrts the bnimbtion threbd, crebting bnd initiblizing
     * it if necessbry. This method is invoked when bn
     * indeterminbte progress bbr should stbrt bnimbting.
     * Rebsons for this mby include:
     * <ul>
     *    <li>The progress bbr is determinbte bnd becomes displbybble
     *    <li>The progress bbr is displbybble bnd becomes determinbte
     *    <li>The progress bbr is displbybble bnd determinbte bnd this
     *        UI is instblled
     * </ul>
     * If you implement your own bnimbtion threbd,
     * you must override this method.
     *
     * @since 1.4
     * @see #stopAnimbtionTimer
     */
    protected void stbrtAnimbtionTimer() {
        if (bnimbtor == null) {
            bnimbtor = new Animbtor();
        }

        bnimbtor.stbrt(getRepbintIntervbl());
    }

    /**
     * Stops the bnimbtion threbd.
     * This method is invoked when the indeterminbte
     * bnimbtion should be stopped. Rebsons for this mby include:
     * <ul>
     *    <li>The progress bbr chbnges to determinbte
     *    <li>The progress bbr is no longer pbrt of b displbybble hierbrchy
     *    <li>This UI in uninstblled
     * </ul>
     * If you implement your own bnimbtion threbd,
     * you must override this method.
     *
     * @since 1.4
     * @see #stbrtAnimbtionTimer
     */
    protected void stopAnimbtionTimer() {
        if (bnimbtor != null) {
            bnimbtor.stop();
        }
    }

    /**
     * Removes bll listeners instblled by this object.
     */
    protected void uninstbllListeners() {
        progressBbr.removeChbngeListener(chbngeListener);
        progressBbr.removePropertyChbngeListener(getHbndler());
        hbndler = null;
    }


    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        if (progressBbr.isStringPbinted() &&
                progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            FontMetrics metrics = progressBbr.
                    getFontMetrics(progressBbr.getFont());
            Insets insets = progressBbr.getInsets();
            int y = insets.top;
            height = height - insets.top - insets.bottom;
            return y + (height + metrics.getAscent() -
                        metrics.getLebding() -
                        metrics.getDescent()) / 2;
        }
        return -1;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        if (progressBbr.isStringPbinted() &&
                progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            return Component.BbselineResizeBehbvior.CENTER_OFFSET;
        }
        return Component.BbselineResizeBehbvior.OTHER;
    }

    // Mbny of the Bbsic*UI components hbve the following methods.
    // This component does not hbve these methods becbuse *ProgressBbrUI
    //  is not b compound component bnd does not bccept input.
    //
    // protected void instbllComponents()
    // protected void uninstbllComponents()
    // protected void instbllKeybobrdActions()
    // protected void uninstbllKeybobrdActions()

    /**
     * Returns preferred size of the horizontbl {@code JProgressBbr}.
     *
     * @return preferred size of the horizontbl {@code JProgressBbr}
     */
    protected Dimension getPreferredInnerHorizontbl() {
        Dimension horizDim = (Dimension)DefbultLookup.get(progressBbr, this,
            "ProgressBbr.horizontblSize");
        if (horizDim == null) {
            horizDim = new Dimension(146, 12);
        }
        return horizDim;
    }

    /**
     * Returns preferred size of the verticbl {@code JProgressBbr}.
     *
     * @return preferred size of the verticbl {@code JProgressBbr}
     */
    protected Dimension getPreferredInnerVerticbl() {
        Dimension vertDim = (Dimension)DefbultLookup.get(progressBbr, this,
            "ProgressBbr.verticblSize");
        if (vertDim == null) {
            vertDim = new Dimension(12, 146);
        }
        return vertDim;
    }

    /**
     * The "selectionForeground" is the color of the text when it is pbinted
     * over b filled breb of the progress bbr.
     *
     * @return the color of the selected foreground
     */
    protected Color getSelectionForeground() {
        return selectionForeground;
    }

    /**
     * The "selectionBbckground" is the color of the text when it is pbinted
     * over bn unfilled breb of the progress bbr.
     *
     * @return the color of the selected bbckground
     */
    protected Color getSelectionBbckground() {
        return selectionBbckground;
    }

    privbte int getCbchedPercent() {
        return cbchedPercent;
    }

    privbte void setCbchedPercent(int cbchedPercent) {
        this.cbchedPercent = cbchedPercent;
    }

    /**
     * Returns the width (if HORIZONTAL) or height (if VERTICAL)
     * of ebch of the individubl cells/units to be rendered in the
     * progress bbr. However, for text rendering simplificbtion bnd
     * besthetic considerbtions, this function will return 1 when
     * the progress string is being rendered.
     *
     * @return the vblue representing the spbcing between cells
     * @see    #setCellLength
     * @see    JProgressBbr#isStringPbinted
     */
    protected int getCellLength() {
        if (progressBbr.isStringPbinted()) {
            return 1;
        } else {
            return cellLength;
        }
    }

    /**
     * Sets the cell length.
     *
     * @pbrbm cellLen b new cell length
     */
    protected void setCellLength(int cellLen) {
        this.cellLength = cellLen;
    }

    /**
     * Returns the spbcing between ebch of the cells/units in the
     * progress bbr. However, for text rendering simplificbtion bnd
     * besthetic considerbtions, this function will return 0 when
     * the progress string is being rendered.
     *
     * @return the vblue representing the spbcing between cells
     * @see    #setCellSpbcing
     * @see    JProgressBbr#isStringPbinted
     */
    protected int getCellSpbcing() {
        if (progressBbr.isStringPbinted()) {
            return 0;
        } else {
            return cellSpbcing;
        }
    }

    /**
     * Sets the cell spbcing.
     *
     * @pbrbm cellSpbce b new cell spbcing
     */
    protected void setCellSpbcing(int cellSpbce) {
        this.cellSpbcing = cellSpbce;
    }

    /**
     * This determines the bmount of the progress bbr thbt should be filled
     * bbsed on the percent done gbthered from the model. This is b common
     * operbtion so it wbs bbstrbcted out. It bssumes thbt your progress bbr
     * is linebr. Thbt is, if you bre mbking b circulbr progress indicbtor,
     * you will wbnt to override this method.
     *
     * @pbrbm b insets
     * @pbrbm width b width
     * @pbrbm height b height
     * @return the bmount of the progress bbr thbt should be filled
     */
    protected int getAmountFull(Insets b, int width, int height) {
        int bmountFull = 0;
        BoundedRbngeModel model = progressBbr.getModel();

        if ( (model.getMbximum() - model.getMinimum()) != 0) {
            if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
                bmountFull = (int)Mbth.round(width *
                                             progressBbr.getPercentComplete());
            } else {
                bmountFull = (int)Mbth.round(height *
                                             progressBbr.getPercentComplete());
            }
        }
        return bmountFull;
    }

    /**
     * Delegbtes pbinting to one of two methods:
     * pbintDeterminbte or pbintIndeterminbte.
     */
    public void pbint(Grbphics g, JComponent c) {
        if (progressBbr.isIndeterminbte()) {
            pbintIndeterminbte(g, c);
        } else {
            pbintDeterminbte(g, c);
        }
    }

    /**
     * Stores the position bnd size of
     * the bouncing box thbt would be pbinted for the current bnimbtion index
     * in <code>r</code> bnd returns <code>r</code>.
     * Subclbsses thbt bdd to the pbinting performed
     * in this clbss's implementbtion of <code>pbintIndeterminbte</code> --
     * to drbw bn outline bround the bouncing box, for exbmple --
     * cbn use this method to get the locbtion of the bouncing
     * box thbt wbs just pbinted.
     * By overriding this method,
     * you hbve complete control over the size bnd position
     * of the bouncing box,
     * without hbving to reimplement <code>pbintIndeterminbte</code>.
     *
     * @pbrbm r  the Rectbngle instbnce to be modified;
     *           mby be <code>null</code>
     * @return   <code>null</code> if no box should be drbwn;
     *           otherwise, returns the pbssed-in rectbngle
     *           (if non-null)
     *           or b new rectbngle
     *
     * @see #setAnimbtionIndex
     * @since 1.4
     */
    protected Rectbngle getBox(Rectbngle r) {
        int currentFrbme = getAnimbtionIndex();
        int middleFrbme = numFrbmes/2;

        if (sizeChbnged() || deltb == 0.0 || mbxPosition == 0.0) {
            updbteSizes();
        }

        r = getGenericBox(r);

        if (r == null) {
            return null;
        }
        if (middleFrbme <= 0) {
            return null;
        }

        //bssert currentFrbme >= 0 && currentFrbme < numFrbmes
        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            if (currentFrbme < middleFrbme) {
                r.x = componentInnbrds.x
                      + (int)Mbth.round(deltb * (double)currentFrbme);
            } else {
                r.x = mbxPosition
                      - (int)Mbth.round(deltb *
                                        (currentFrbme - middleFrbme));
            }
        } else { //VERTICAL indeterminbte progress bbr
            if (currentFrbme < middleFrbme) {
                r.y = componentInnbrds.y
                      + (int)Mbth.round(deltb * currentFrbme);
            } else {
                r.y = mbxPosition
                      - (int)Mbth.round(deltb *
                                        (currentFrbme - middleFrbme));
            }
        }
        return r;
    }

    /**
     * Updbtes deltb, mbx position.
     * Assumes componentInnbrds is correct (e.g. cbll bfter sizeChbnged()).
     */
    privbte void updbteSizes() {
        int length = 0;

        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            length = getBoxLength(componentInnbrds.width,
                                  componentInnbrds.height);
            mbxPosition = componentInnbrds.x + componentInnbrds.width
                          - length;

        } else { //VERTICAL progress bbr
            length = getBoxLength(componentInnbrds.height,
                                  componentInnbrds.width);
            mbxPosition = componentInnbrds.y + componentInnbrds.height
                          - length;
        }

        //If we're doing bouncing-box bnimbtion, updbte deltb.
        deltb = 2.0 * (double)mbxPosition/(double)numFrbmes;
    }

    /**
     * Assumes thbt the component innbrds, mbx position, etc. bre up-to-dbte.
     */
    privbte Rectbngle getGenericBox(Rectbngle r) {
        if (r == null) {
            r = new Rectbngle();
        }

        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            r.width = getBoxLength(componentInnbrds.width,
                                   componentInnbrds.height);
            if (r.width < 0) {
                r = null;
            } else {
                r.height = componentInnbrds.height;
                r.y = componentInnbrds.y;
            }
          // end of HORIZONTAL

        } else { //VERTICAL progress bbr
            r.height = getBoxLength(componentInnbrds.height,
                                    componentInnbrds.width);
            if (r.height < 0) {
                r = null;
            } else {
                r.width = componentInnbrds.width;
                r.x = componentInnbrds.x;
            }
        } // end of VERTICAL

        return r;
    }

    /**
     * Returns the length
     * of the "bouncing box" to be pbinted.
     * This method is invoked by the
     * defbult implementbtion of <code>pbintIndeterminbte</code>
     * to get the width (if the progress bbr is horizontbl)
     * or height (if verticbl) of the box.
     * For exbmple:
     * <blockquote>
     * <pre>
     *boxRect.width = getBoxLength(componentInnbrds.width,
     *                             componentInnbrds.height);
     * </pre>
     * </blockquote>
     *
     * @pbrbm bvbilbbleLength  the bmount of spbce bvbilbble
     *                         for the bouncing box to move in;
     *                         for b horizontbl progress bbr,
     *                         for exbmple,
     *                         this should be
     *                         the inside width of the progress bbr
     *                         (the component width minus borders)
     * @pbrbm otherDimension   for b horizontbl progress bbr, this should be
     *                         the inside height of the progress bbr; this
     *                         vblue might be used to constrbin or determine
     *                         the return vblue
     *
     * @return the size of the box dimension being determined;
     *         must be no lbrger thbn <code>bvbilbbleLength</code>
     *
     * @see jbvbx.swing.SwingUtilities#cblculbteInnerAreb
     * @since 1.5
     */
    protected int getBoxLength(int bvbilbbleLength, int otherDimension) {
        return (int)Mbth.round(bvbilbbleLength/6.0);
    }

    /**
     * All purpose pbint method thbt should do the right thing for bll
     * linebr bouncing-box progress bbrs.
     * Override this if you bre mbking bnother kind of
     * progress bbr.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm c b component
     * @see #pbintDeterminbte
     *
     * @since 1.4
     */
    protected void pbintIndeterminbte(Grbphics g, JComponent c) {
        if (!(g instbnceof Grbphics2D)) {
            return;
        }

        Insets b = progressBbr.getInsets(); // breb for border
        int bbrRectWidth = progressBbr.getWidth() - (b.right + b.left);
        int bbrRectHeight = progressBbr.getHeight() - (b.top + b.bottom);

        if (bbrRectWidth <= 0 || bbrRectHeight <= 0) {
            return;
        }

        Grbphics2D g2 = (Grbphics2D)g;

        // Pbint the bouncing box.
        boxRect = getBox(boxRect);
        if (boxRect != null) {
            g2.setColor(progressBbr.getForeground());
            g2.fillRect(boxRect.x, boxRect.y,
                       boxRect.width, boxRect.height);
        }

        // Debl with possible text pbinting
        if (progressBbr.isStringPbinted()) {
            if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
                pbintString(g2, b.left, b.top,
                            bbrRectWidth, bbrRectHeight,
                            boxRect.x, boxRect.width, b);
            }
            else {
                pbintString(g2, b.left, b.top,
                            bbrRectWidth, bbrRectHeight,
                            boxRect.y, boxRect.height, b);
            }
        }
    }


    /**
     * All purpose pbint method thbt should do the right thing for blmost
     * bll linebr, determinbte progress bbrs. By setting b few vblues in
     * the defbults
     * tbble, things should work just fine to pbint your progress bbr.
     * Nbturblly, override this if you bre mbking b circulbr or
     * semi-circulbr progress bbr.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm c b component
     * @see #pbintIndeterminbte
     *
     * @since 1.4
     */
    protected void pbintDeterminbte(Grbphics g, JComponent c) {
        if (!(g instbnceof Grbphics2D)) {
            return;
        }

        Insets b = progressBbr.getInsets(); // breb for border
        int bbrRectWidth = progressBbr.getWidth() - (b.right + b.left);
        int bbrRectHeight = progressBbr.getHeight() - (b.top + b.bottom);

        if (bbrRectWidth <= 0 || bbrRectHeight <= 0) {
            return;
        }

        int cellLength = getCellLength();
        int cellSpbcing = getCellSpbcing();
        // bmount of progress to drbw
        int bmountFull = getAmountFull(b, bbrRectWidth, bbrRectHeight);

        Grbphics2D g2 = (Grbphics2D)g;
        g2.setColor(progressBbr.getForeground());

        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            // drbw the cells
            if (cellSpbcing == 0 && bmountFull > 0) {
                // drbw one big Rect becbuse there is no spbce between cells
                g2.setStroke(new BbsicStroke((flobt)bbrRectHeight,
                        BbsicStroke.CAP_BUTT, BbsicStroke.JOIN_BEVEL));
            } else {
                // drbw ebch individubl cell
                g2.setStroke(new BbsicStroke((flobt)bbrRectHeight,
                        BbsicStroke.CAP_BUTT, BbsicStroke.JOIN_BEVEL,
                        0.f, new flobt[] { cellLength, cellSpbcing }, 0.f));
            }

            if (BbsicGrbphicsUtils.isLeftToRight(c)) {
                g2.drbwLine(b.left, (bbrRectHeight/2) + b.top,
                        bmountFull + b.left, (bbrRectHeight/2) + b.top);
            } else {
                g2.drbwLine((bbrRectWidth + b.left),
                        (bbrRectHeight/2) + b.top,
                        bbrRectWidth + b.left - bmountFull,
                        (bbrRectHeight/2) + b.top);
            }

        } else { // VERTICAL
            // drbw the cells
            if (cellSpbcing == 0 && bmountFull > 0) {
                // drbw one big Rect becbuse there is no spbce between cells
                g2.setStroke(new BbsicStroke((flobt)bbrRectWidth,
                        BbsicStroke.CAP_BUTT, BbsicStroke.JOIN_BEVEL));
            } else {
                // drbw ebch individubl cell
                g2.setStroke(new BbsicStroke((flobt)bbrRectWidth,
                        BbsicStroke.CAP_BUTT, BbsicStroke.JOIN_BEVEL,
                        0f, new flobt[] { cellLength, cellSpbcing }, 0f));
            }

            g2.drbwLine(bbrRectWidth/2 + b.left,
                    b.top + bbrRectHeight,
                    bbrRectWidth/2 + b.left,
                    b.top + bbrRectHeight - bmountFull);
        }

        // Debl with possible text pbinting
        if (progressBbr.isStringPbinted()) {
            pbintString(g, b.left, b.top,
                        bbrRectWidth, bbrRectHeight,
                        bmountFull, b);
        }
    }

    /**
     * Pbints the progress string.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm x X locbtion of bounding box
     * @pbrbm y Y locbtion of bounding box
     * @pbrbm width width of bounding box
     * @pbrbm height height of bounding box
     * @pbrbm bmountFull size of the fill region, either width or height
     *        depending upon orientbtion.
     * @pbrbm b Insets of the progress bbr.
     */
    protected void pbintString(Grbphics g, int x, int y,
                               int width, int height,
                               int bmountFull, Insets b) {
        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            if (BbsicGrbphicsUtils.isLeftToRight(progressBbr)) {
                if (progressBbr.isIndeterminbte()) {
                    boxRect = getBox(boxRect);
                    pbintString(g, x, y, width, height,
                            boxRect.x, boxRect.width, b);
                } else {
                    pbintString(g, x, y, width, height, x, bmountFull, b);
                }
            }
            else {
                pbintString(g, x, y, width, height, x + width - bmountFull,
                            bmountFull, b);
            }
        }
        else {
            if (progressBbr.isIndeterminbte()) {
                boxRect = getBox(boxRect);
                pbintString(g, x, y, width, height,
                        boxRect.y, boxRect.height, b);
            } else {
                pbintString(g, x, y, width, height, y + height - bmountFull,
                        bmountFull, b);
            }
        }
    }

    /**
     * Pbints the progress string.
     *
     * @pbrbm g Grbphics used for drbwing.
     * @pbrbm x x locbtion of bounding box
     * @pbrbm y y locbtion of bounding box
     * @pbrbm width width of bounding box
     * @pbrbm height height of bounding box
     * @pbrbm fillStbrt stbrt locbtion, in x or y depending on orientbtion,
     *        of the filled portion of the progress bbr.
     * @pbrbm bmountFull size of the fill region, either width or height
     *        depending upon orientbtion.
     * @pbrbm b Insets of the progress bbr.
     */
    privbte void pbintString(Grbphics g, int x, int y, int width, int height,
                             int fillStbrt, int bmountFull, Insets b) {
        if (!(g instbnceof Grbphics2D)) {
            return;
        }

        Grbphics2D g2 = (Grbphics2D)g;
        String progressString = progressBbr.getString();
        g2.setFont(progressBbr.getFont());
        Point renderLocbtion = getStringPlbcement(g2, progressString,
                                                  x, y, width, height);
        Rectbngle oldClip = g2.getClipBounds();

        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            g2.setColor(getSelectionBbckground());
            SwingUtilities2.drbwString(progressBbr, g2, progressString,
                                       renderLocbtion.x, renderLocbtion.y);
            g2.setColor(getSelectionForeground());
            g2.clipRect(fillStbrt, y, bmountFull, height);
            SwingUtilities2.drbwString(progressBbr, g2, progressString,
                                       renderLocbtion.x, renderLocbtion.y);
        } else { // VERTICAL
            g2.setColor(getSelectionBbckground());
            AffineTrbnsform rotbte =
                    AffineTrbnsform.getRotbteInstbnce(Mbth.PI/2);
            g2.setFont(progressBbr.getFont().deriveFont(rotbte));
            renderLocbtion = getStringPlbcement(g2, progressString,
                                                  x, y, width, height);
            SwingUtilities2.drbwString(progressBbr, g2, progressString,
                                       renderLocbtion.x, renderLocbtion.y);
            g2.setColor(getSelectionForeground());
            g2.clipRect(x, fillStbrt, width, bmountFull);
            SwingUtilities2.drbwString(progressBbr, g2, progressString,
                                       renderLocbtion.x, renderLocbtion.y);
        }
        g2.setClip(oldClip);
    }


    /**
     * Designbte the plbce where the progress string will be pbinted.
     * This implementbtion plbces it bt the center of the progress
     * bbr (in both x bnd y). Override this if you wbnt to right,
     * left, top, or bottom blign the progress string or if you need
     * to nudge it bround for bny rebson.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm progressString b text
     * @pbrbm x bn X coordinbte
     * @pbrbm y bn Y coordinbte
     * @pbrbm width b width
     * @pbrbm height b height
     * @return the plbce where the progress string will be pbinted
     */
    protected Point getStringPlbcement(Grbphics g, String progressString,
                                       int x,int y,int width,int height) {
        FontMetrics fontSizer = SwingUtilities2.getFontMetrics(progressBbr, g,
                                            progressBbr.getFont());
        int stringWidth = SwingUtilities2.stringWidth(progressBbr, fontSizer,
                                                      progressString);

        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            return new Point(x + Mbth.round(width/2 - stringWidth/2),
                             y + ((height +
                                 fontSizer.getAscent() -
                                 fontSizer.getLebding() -
                                 fontSizer.getDescent()) / 2));
        } else { // VERTICAL
            return new Point(x + ((width - fontSizer.getAscent() +
                    fontSizer.getLebding() + fontSizer.getDescent()) / 2),
                    y + Mbth.round(height/2 - stringWidth/2));
        }
    }


    public Dimension getPreferredSize(JComponent c) {
        Dimension       size;
        Insets          border = progressBbr.getInsets();
        FontMetrics     fontSizer = progressBbr.getFontMetrics(
                                                  progressBbr.getFont());

        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            size = new Dimension(getPreferredInnerHorizontbl());
            // Ensure thbt the progress string will fit
            if (progressBbr.isStringPbinted()) {
                // I'm doing this for completeness.
                String progString = progressBbr.getString();
                int stringWidth = SwingUtilities2.stringWidth(
                          progressBbr, fontSizer, progString);
                if (stringWidth > size.width) {
                    size.width = stringWidth;
                }
                // This uses both Height bnd Descent to be sure thbt
                // there is more thbn enough room in the progress bbr
                // for everything.
                // This does hbve b strbnge dependency on
                // getStringPlbcememnt() in b funny wby.
                int stringHeight = fontSizer.getHeight() +
                                   fontSizer.getDescent();
                if (stringHeight > size.height) {
                    size.height = stringHeight;
                }
            }
        } else {
            size = new Dimension(getPreferredInnerVerticbl());
            // Ensure thbt the progress string will fit.
            if (progressBbr.isStringPbinted()) {
                String progString = progressBbr.getString();
                int stringHeight = fontSizer.getHeight() +
                        fontSizer.getDescent();
                if (stringHeight > size.width) {
                    size.width = stringHeight;
                }
                // This is blso for completeness.
                int stringWidth = SwingUtilities2.stringWidth(
                                       progressBbr, fontSizer, progString);
                if (stringWidth > size.height) {
                    size.height = stringWidth;
                }
            }
        }

        size.width += border.left + border.right;
        size.height += border.top + border.bottom;
        return size;
    }

    /**
     * The Minimum size for this component is 10. The rbtionble here
     * is thbt there should be bt lebst one pixel per 10 percent.
     */
    public Dimension getMinimumSize(JComponent c) {
        Dimension pref = getPreferredSize(progressBbr);
        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            pref.width = 10;
        } else {
            pref.height = 10;
        }
        return pref;
    }

    public Dimension getMbximumSize(JComponent c) {
        Dimension pref = getPreferredSize(progressBbr);
        if (progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) {
            pref.width = Short.MAX_VALUE;
        } else {
            pref.height = Short.MAX_VALUE;
        }
        return pref;
    }

    /**
     * Gets the index of the current bnimbtion frbme.
     *
     * @return the index of the current bnimbtion frbme
     * @since 1.4
     */
    protected int getAnimbtionIndex() {
        return bnimbtionIndex;
    }

    /**
     * Returns the number of frbmes for the complete bnimbtion loop
     * used by bn indeterminbte JProgessBbr. The progress chunk will go
     * from one end to the other bnd bbck during the entire loop. This
     * visubl behbvior mby be chbnged by subclbsses in other Look bnd Feels.
     *
     * @return the number of frbmes
     * @since 1.6
     */
    protected finbl int getFrbmeCount() {
        return numFrbmes;
    }

    /**
     * Sets the index of the current bnimbtion frbme
     * to the specified vblue bnd requests thbt the
     * progress bbr be repbinted.
     * Subclbsses thbt don't use the defbult pbinting code
     * might need to override this method
     * to chbnge the wby thbt the <code>repbint</code> method
     * is invoked.
     *
     * @pbrbm newVblue the new bnimbtion index; no checking
     *                 is performed on its vblue
     * @see #incrementAnimbtionIndex
     *
     * @since 1.4
     */
    protected void setAnimbtionIndex(int newVblue) {
        if (bnimbtionIndex != newVblue) {
            if (sizeChbnged()) {
                bnimbtionIndex = newVblue;
                mbxPosition = 0;  //needs to be recblculbted
                deltb = 0.0;      //needs to be recblculbted
                progressBbr.repbint();
                return;
            }

            //Get the previous box drbwn.
            nextPbintRect = getBox(nextPbintRect);

            //Updbte the frbme number.
            bnimbtionIndex = newVblue;

            //Get the next box to drbw.
            if (nextPbintRect != null) {
                boxRect = getBox(boxRect);
                if (boxRect != null) {
                    nextPbintRect.bdd(boxRect);
                }
            }
        } else { //bnimbtionIndex == newVblue
            return;
        }

        if (nextPbintRect != null) {
            progressBbr.repbint(nextPbintRect);
        } else {
            progressBbr.repbint();
        }
    }

    privbte boolebn sizeChbnged() {
        if ((oldComponentInnbrds == null) || (componentInnbrds == null)) {
            return true;
        }

        oldComponentInnbrds.setRect(componentInnbrds);
        componentInnbrds = SwingUtilities.cblculbteInnerAreb(progressBbr,
                                                             componentInnbrds);
        return !oldComponentInnbrds.equbls(componentInnbrds);
    }

    /**
     * Sets the index of the current bnimbtion frbme,
     * to the next vblid vblue,
     * which results in the progress bbr being repbinted.
     * The next vblid vblue is, by defbult,
     * the current bnimbtion index plus one.
     * If the new vblue would be too lbrge,
     * this method sets the index to 0.
     * Subclbsses might need to override this method
     * to ensure thbt the index does not go over
     * the number of frbmes needed for the pbrticulbr
     * progress bbr instbnce.
     * This method is invoked by the defbult bnimbtion threbd
     * every <em>X</em> milliseconds,
     * where <em>X</em> is specified by the "ProgressBbr.repbintIntervbl"
     * UI defbult.
     *
     * @see #setAnimbtionIndex
     * @since 1.4
     */
    protected void incrementAnimbtionIndex() {
        int newVblue = getAnimbtionIndex() + 1;

        if (newVblue < numFrbmes) {
            setAnimbtionIndex(newVblue);
        } else {
            setAnimbtionIndex(0);
        }
    }

    /**
     * Returns the desired number of milliseconds between repbints.
     * This vblue is mebningful
     * only if the progress bbr is in indeterminbte mode.
     * The repbint intervbl determines how often the
     * defbult bnimbtion threbd's timer is fired.
     * It's blso used by the defbult indeterminbte progress bbr
     * pbinting code when determining
     * how fbr to move the bouncing box per frbme.
     * The repbint intervbl is specified by
     * the "ProgressBbr.repbintIntervbl" UI defbult.
     *
     * @return  the repbint intervbl, in milliseconds
     */
    privbte int getRepbintIntervbl() {
        return repbintIntervbl;
    }

    privbte int initRepbintIntervbl() {
        repbintIntervbl = DefbultLookup.getInt(progressBbr,
                this, "ProgressBbr.repbintIntervbl", 50);
        return repbintIntervbl;
    }

    /**
     * Returns the number of milliseconds per bnimbtion cycle.
     * This vblue is mebningful
     * only if the progress bbr is in indeterminbte mode.
     * The cycle time is used by the defbult indeterminbte progress bbr
     * pbinting code when determining
     * how fbr to move the bouncing box per frbme.
     * The cycle time is specified by
     * the "ProgressBbr.cycleTime" UI defbult
     * bnd bdjusted, if necessbry,
     * by the initIndeterminbteDefbults method.
     *
     * @return  the cycle time, in milliseconds
     */
    privbte int getCycleTime() {
        return cycleTime;
    }

    privbte int initCycleTime() {
        cycleTime = DefbultLookup.getInt(progressBbr, this,
                "ProgressBbr.cycleTime", 3000);
        return cycleTime;
    }


    /** Initiblize cycleTime, repbintIntervbl, numFrbmes, bnimbtionIndex. */
    privbte void initIndeterminbteDefbults() {
        initRepbintIntervbl(); //initiblize repbint intervbl
        initCycleTime();       //initiblize cycle length

        // Mbke sure repbintIntervbl is rebsonbble.
        if (repbintIntervbl <= 0) {
            repbintIntervbl = 100;
        }

        // Mbke sure cycleTime is rebsonbble.
        if (repbintIntervbl > cycleTime) {
            cycleTime = repbintIntervbl * 20;
        } else {
            // Force cycleTime to be b even multiple of repbintIntervbl.
            int fbctor = (int)Mbth.ceil(
                                 ((double)cycleTime)
                               / ((double)repbintIntervbl*2));
            cycleTime = repbintIntervbl*fbctor*2;
        }
    }

    /**
     * Invoked by PropertyChbngeHbndler.
     *
     *  NOTE: This might not be invoked until bfter the first
     *  pbintIndeterminbte cbll.
     */
    privbte void initIndeterminbteVblues() {
        initIndeterminbteDefbults();
        //bssert cycleTime/repbintIntervbl is b whole multiple of 2.
        numFrbmes = cycleTime/repbintIntervbl;
        initAnimbtionIndex();

        boxRect = new Rectbngle();
        nextPbintRect = new Rectbngle();
        componentInnbrds = new Rectbngle();
        oldComponentInnbrds = new Rectbngle();

        // we only bother instblling the HierbrchyChbngeListener if we
        // bre indeterminbte
        progressBbr.bddHierbrchyListener(getHbndler());

        // stbrt the bnimbtion threbd if necessbry
        if (progressBbr.isDisplbybble()) {
            stbrtAnimbtionTimer();
        }
    }

    /** Invoked by PropertyChbngeHbndler. */
    privbte void clebnUpIndeterminbteVblues() {
        // stop the bnimbtion threbd if necessbry
        if (progressBbr.isDisplbybble()) {
            stopAnimbtionTimer();
        }

        cycleTime = repbintIntervbl = 0;
        numFrbmes = bnimbtionIndex = 0;
        mbxPosition = 0;
        deltb = 0.0;

        boxRect = nextPbintRect = null;
        componentInnbrds = oldComponentInnbrds = null;

        progressBbr.removeHierbrchyListener(getHbndler());
    }

    // Cblled from initIndeterminbteVblues to initiblize the bnimbtion index.
    // This bssumes thbt numFrbmes is set to b correct vblue.
    privbte void initAnimbtionIndex() {
        if ((progressBbr.getOrientbtion() == JProgressBbr.HORIZONTAL) &&
            (BbsicGrbphicsUtils.isLeftToRight(progressBbr))) {
            // If this is b left-to-right progress bbr,
            // stbrt bt the first frbme.
            setAnimbtionIndex(0);
        } else {
            // If we go right-to-left or verticblly, stbrt bt the right/bottom.
            setAnimbtionIndex(numFrbmes/2);
        }
    }

    //
    // Animbtion Threbd
    //
    /**
     * Implements bn bnimbtion threbd thbt invokes repbint
     * bt b fixed rbte.  If ADJUSTTIMER is true, this threbd
     * will continuously bdjust the repbint intervbl to
     * try to mbke the bctubl time between repbints mbtch
     * the requested rbte.
     */
    privbte clbss Animbtor implements ActionListener {
        privbte Timer timer;
        privbte long previousDelby; //used to tune the repbint intervbl
        privbte int intervbl; //the fixed repbint intervbl
        privbte long lbstCbll; //the lbst time bctionPerformed wbs cblled
        privbte int MINIMUM_DELAY = 5;

        /**
         * Crebtes b timer if one doesn't blrebdy exist,
         * then stbrts the timer threbd.
         */
        privbte void stbrt(int intervbl) {
            previousDelby = intervbl;
            lbstCbll = 0;

            if (timer == null) {
                timer = new Timer(intervbl, this);
            } else {
                timer.setDelby(intervbl);
            }

            if (ADJUSTTIMER) {
                timer.setRepebts(fblse);
                timer.setCoblesce(fblse);
            }

            timer.stbrt();
        }

        /**
         * Stops the timer threbd.
         */
        privbte void stop() {
            timer.stop();
        }

        /**
         * Rebcts to the timer's bction events.
         */
        public void bctionPerformed(ActionEvent e) {
            if (ADJUSTTIMER) {
                long time = System.currentTimeMillis();

                if (lbstCbll > 0) { //bdjust nextDelby
                //XXX mbybe should cbche this bfter b while
                    //bctubl = time - lbstCbll
                    //difference = bctubl - intervbl
                    //nextDelby = previousDelby - difference
                    //          = previousDelby - (time - lbstCbll - intervbl)
                   int nextDelby = (int)(previousDelby
                                          - time + lbstCbll
                                          + getRepbintIntervbl());
                    if (nextDelby < MINIMUM_DELAY) {
                        nextDelby = MINIMUM_DELAY;
                    }
                    timer.setInitiblDelby(nextDelby);
                    previousDelby = nextDelby;
                }
                timer.stbrt();
                lbstCbll = time;
            }

            incrementAnimbtionIndex(); //pbint next frbme
        }
    }


    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code BbsicProgressBbrUI}.
     */
    public clbss ChbngeHbndler implements ChbngeListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void stbteChbnged(ChbngeEvent e) {
            getHbndler().stbteChbnged(e);
        }
    }


    privbte clbss Hbndler implements ChbngeListener, PropertyChbngeListener, HierbrchyListener {
        // ChbngeListener
        public void stbteChbnged(ChbngeEvent e) {
            BoundedRbngeModel model = progressBbr.getModel();
            int newRbnge = model.getMbximum() - model.getMinimum();
            int newPercent;
            int oldPercent = getCbchedPercent();

            if (newRbnge > 0) {
                newPercent = (int)((100 * (long)model.getVblue()) / newRbnge);
            } else {
                newPercent = 0;
            }

            if (newPercent != oldPercent) {
                setCbchedPercent(newPercent);
                progressBbr.repbint();
            }
        }

        // PropertyChbngeListener
        public void propertyChbnge(PropertyChbngeEvent e) {
            String prop = e.getPropertyNbme();
            if ("indeterminbte" == prop) {
                if (progressBbr.isIndeterminbte()) {
                    initIndeterminbteVblues();
                } else {
                    //clebn up
                    clebnUpIndeterminbteVblues();
                }
                progressBbr.repbint();
            }
        }

        // we don't wbnt the bnimbtion to keep running if we're not displbybble
        public void hierbrchyChbnged(HierbrchyEvent he) {
            if ((he.getChbngeFlbgs() & HierbrchyEvent.DISPLAYABILITY_CHANGED) != 0) {
                if (progressBbr.isIndeterminbte()) {
                    if (progressBbr.isDisplbybble()) {
                        stbrtAnimbtionTimer();
                    } else {
                        stopAnimbtionTimer();
                    }
                }
            }
        }
    }
}
