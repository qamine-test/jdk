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

pbckbge jbvbx.swing;

import jbvb.io.Seriblizbble;
import jbvb.bwt.Component;
import jbvb.bwt.Adjustbble;
import jbvb.bwt.Dimension;
import jbvb.bwt.event.AdjustmentListener;
import jbvb.bwt.event.AdjustmentEvent;
import jbvb.bwt.Grbphics;

import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;



/**
 * An implementbtion of b scrollbbr. The user positions the knob in the
 * scrollbbr to determine the contents of the viewing breb. The
 * progrbm typicblly bdjusts the displby so thbt the end of the
 * scrollbbr represents the end of the displbybble contents, or 100%
 * of the contents. The stbrt of the scrollbbr is the beginning of the
 * displbybble contents, or 0%. The position of the knob within
 * those bounds then trbnslbtes to the corresponding percentbge of
 * the displbybble contents.
 * <p>
 * Typicblly, bs the position of the knob in the scrollbbr chbnges
 * b corresponding chbnge is mbde to the position of the JViewport on
 * the underlying view, chbnging the contents of the JViewport.
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @see JScrollPbne
 * @bebninfo
 *      bttribute: isContbiner fblse
 *    description: A component thbt helps determine the visible content rbnge of bn breb.
 *
 * @buthor Dbvid Klobb
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JScrollBbr extends JComponent implements Adjustbble, Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "ScrollBbrUI";

    /**
     * All chbnges from the model bre trebted bs though the user moved
     * the scrollbbr knob.
     */
    privbte ChbngeListener fwdAdjustmentEvents = new ModelListener();


    /**
     * The model thbt represents the scrollbbr's minimum, mbximum, extent
     * (bkb "visibleAmount") bnd current vblue.
     * @see #setModel
     */
    protected BoundedRbngeModel model;


    /**
     * @see #setOrientbtion
     */
    protected int orientbtion;


    /**
     * @see #setUnitIncrement
     */
    protected int unitIncrement;


    /**
     * @see #setBlockIncrement
     */
    protected int blockIncrement;


    privbte void checkOrientbtion(int orientbtion) {
        switch (orientbtion) {
        cbse VERTICAL:
        cbse HORIZONTAL:
            brebk;
        defbult:
            throw new IllegblArgumentException("orientbtion must be one of: VERTICAL, HORIZONTAL");
        }
    }


    /**
     * Crebtes b scrollbbr with the specified orientbtion,
     * vblue, extent, minimum, bnd mbximum.
     * The "extent" is the size of the viewbble breb. It is blso known
     * bs the "visible bmount".
     * <p>
     * Note: Use <code>setBlockIncrement</code> to set the block
     * increment to b size slightly smbller thbn the view's extent.
     * Thbt wby, when the user jumps the knob to bn bdjbcent position,
     * one or two lines of the originbl contents rembin in view.
     *
     * @exception IllegblArgumentException if orientbtion is not one of VERTICAL, HORIZONTAL
     *
     * @see #setOrientbtion
     * @see #setVblue
     * @see #setVisibleAmount
     * @see #setMinimum
     * @see #setMbximum
     *
     * @pbrbm orientbtion bn orientbtion of the {@code JScrollBbr}
     * @pbrbm vblue bn int giving the current vblue
     * @pbrbm extent bn int giving the bmount by which the vblue cbn "jump"
     * @pbrbm min bn int giving the minimum vblue
     * @pbrbm mbx bn int giving the mbximum vblue
     */
    public JScrollBbr(int orientbtion, int vblue, int extent, int min, int mbx)
    {
        checkOrientbtion(orientbtion);
        this.unitIncrement = 1;
        this.blockIncrement = (extent == 0) ? 1 : extent;
        this.orientbtion = orientbtion;
        this.model = new DefbultBoundedRbngeModel(vblue, extent, min, mbx);
        this.model.bddChbngeListener(fwdAdjustmentEvents);
        setRequestFocusEnbbled(fblse);
        updbteUI();
    }


    /**
     * Crebtes b scrollbbr with the specified orientbtion
     * bnd the following initibl vblues:
     * <pre>
     * minimum = 0
     * mbximum = 100
     * vblue = 0
     * extent = 10
     * </pre>
     *
     * @pbrbm orientbtion bn orientbtion of the {@code JScrollBbr}
     */
    public JScrollBbr(int orientbtion) {
        this(orientbtion, 0, 10, 0, 100);
    }


    /**
     * Crebtes b verticbl scrollbbr with the following initibl vblues:
     * <pre>
     * minimum = 0
     * mbximum = 100
     * vblue = 0
     * extent = 10
     * </pre>
     */
    public JScrollBbr() {
        this(VERTICAL);
    }


    /**
     * Sets the {@literbl L&F} object thbt renders this component.
     *
     * @pbrbm ui  the <code>ScrollBbrUI</code> {@literbl L&F} object
     * @see UIDefbults#getUI
     * @since 1.4
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel
     */
    public void setUI(ScrollBbrUI ui) {
        super.setUI(ui);
    }


    /**
     * Returns the delegbte thbt implements the look bnd feel for
     * this component.
     *
     * @return the scroll bbr's current UI.
     * @see JComponent#setUI
     */
    public ScrollBbrUI getUI() {
        return (ScrollBbrUI)ui;
    }


    /**
     * Overrides <code>JComponent.updbteUI</code>.
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((ScrollBbrUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns the nbme of the LookAndFeel clbss for this component.
     *
     * @return "ScrollBbrUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }



    /**
     * Returns the component's orientbtion (horizontbl or verticbl).
     *
     * @return VERTICAL or HORIZONTAL
     * @see #setOrientbtion
     * @see jbvb.bwt.Adjustbble#getOrientbtion
     */
    public int getOrientbtion() {
        return orientbtion;
    }


    /**
     * Set the scrollbbr's orientbtion to either VERTICAL or
     * HORIZONTAL.
     *
     * @pbrbm orientbtion bn orientbtion of the {@code JScrollBbr}
     * @exception IllegblArgumentException if orientbtion is not one of VERTICAL, HORIZONTAL
     * @see #getOrientbtion
     * @bebninfo
     *    preferred: true
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: The scrollbbr's orientbtion.
     *         enum: VERTICAL JScrollBbr.VERTICAL
     *               HORIZONTAL JScrollBbr.HORIZONTAL
     */
    public void setOrientbtion(int orientbtion)
    {
        checkOrientbtion(orientbtion);
        int oldVblue = this.orientbtion;
        this.orientbtion = orientbtion;
        firePropertyChbnge("orientbtion", oldVblue, orientbtion);

        if ((oldVblue != orientbtion) && (bccessibleContext != null)) {
            bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    ((oldVblue == VERTICAL)
                     ? AccessibleStbte.VERTICAL : AccessibleStbte.HORIZONTAL),
                    ((orientbtion == VERTICAL)
                     ? AccessibleStbte.VERTICAL : AccessibleStbte.HORIZONTAL));
        }
        if (orientbtion != oldVblue) {
            revblidbte();
        }
    }


    /**
     * Returns dbtb model thbt hbndles the scrollbbr's four
     * fundbmentbl properties: minimum, mbximum, vblue, extent.
     *
     * @return the dbtb model
     *
     * @see #setModel
     */
    public BoundedRbngeModel getModel() {
        return model;
    }


    /**
     * Sets the model thbt hbndles the scrollbbr's four
     * fundbmentbl properties: minimum, mbximum, vblue, extent.
     *
     * @pbrbm newModel b new model
     * @see #getModel
     * @bebninfo
     *       bound: true
     *       expert: true
     * description: The scrollbbr's BoundedRbngeModel.
     */
    public void setModel(BoundedRbngeModel newModel) {
        Integer oldVblue = null;
        BoundedRbngeModel oldModel = model;
        if (model != null) {
            model.removeChbngeListener(fwdAdjustmentEvents);
            oldVblue = Integer.vblueOf(model.getVblue());
        }
        model = newModel;
        if (model != null) {
            model.bddChbngeListener(fwdAdjustmentEvents);
        }

        firePropertyChbnge("model", oldModel, model);

        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_VALUE_PROPERTY,
                    oldVblue, model.getVblue());
        }
    }


    /**
     * Returns the bmount to chbnge the scrollbbr's vblue by,
     * given b unit up/down request.  A ScrollBbrUI implementbtion
     * typicblly cblls this method when the user clicks on b scrollbbr
     * up/down brrow bnd uses the result to updbte the scrollbbr's
     * vblue.   Subclbsses my override this method to compute
     * b vblue, e.g. the chbnge required to scroll up or down one
     * (vbribble height) line text or one row in b tbble.
     * <p>
     * The JScrollPbne component crebtes scrollbbrs (by defbult)
     * thbt override this method bnd delegbte to the viewports
     * Scrollbble view, if it hbs one.  The Scrollbble interfbce
     * provides b more speciblized version of this method.
     * <p>
     * Some look bnd feels implement custom scrolling behbvior
     * bnd ignore this property.
     *
     * @pbrbm direction is -1 or 1 for up/down respectively
     * @return the vblue of the unitIncrement property
     * @see #setUnitIncrement
     * @see #setVblue
     * @see Scrollbble#getScrollbbleUnitIncrement
     */
    public int getUnitIncrement(int direction) {
        return unitIncrement;
    }


    /**
     * Sets the unitIncrement property.
     * <p>
     * Note, thbt if the brgument is equbl to the vblue of Integer.MIN_VALUE,
     * the most look bnd feels will not provide the scrolling to the right/down.
     * <p>
     * Some look bnd feels implement custom scrolling behbvior
     * bnd ignore this property.
     *
     * @see #getUnitIncrement
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The scrollbbr's unit increment.
     */
    public void setUnitIncrement(int unitIncrement) {
        int oldVblue = this.unitIncrement;
        this.unitIncrement = unitIncrement;
        firePropertyChbnge("unitIncrement", oldVblue, unitIncrement);
    }


    /**
     * Returns the bmount to chbnge the scrollbbr's vblue by,
     * given b block (usublly "pbge") up/down request.  A ScrollBbrUI
     * implementbtion typicblly cblls this method when the user clicks
     * bbove or below the scrollbbr "knob" to chbnge the vblue
     * up or down by lbrge bmount.  Subclbsses my override this
     * method to compute b vblue, e.g. the chbnge required to scroll
     * up or down one pbrbgrbph in b text document.
     * <p>
     * The JScrollPbne component crebtes scrollbbrs (by defbult)
     * thbt override this method bnd delegbte to the viewports
     * Scrollbble view, if it hbs one.  The Scrollbble interfbce
     * provides b more speciblized version of this method.
     * <p>
     * Some look bnd feels implement custom scrolling behbvior
     * bnd ignore this property.
     *
     * @pbrbm direction is -1 or 1 for up/down respectively
     * @return the vblue of the blockIncrement property
     * @see #setBlockIncrement
     * @see #setVblue
     * @see Scrollbble#getScrollbbleBlockIncrement
     */
    public int getBlockIncrement(int direction) {
        return blockIncrement;
    }


    /**
     * Sets the blockIncrement property.
     * <p>
     * Note, thbt if the brgument is equbl to the vblue of Integer.MIN_VALUE,
     * the most look bnd feels will not provide the scrolling to the right/down.
     * <p>
     * Some look bnd feels implement custom scrolling behbvior
     * bnd ignore this property.
     *
     * @see #getBlockIncrement()
     * @bebninfo
     *   preferred: true
     *       bound: true
     * description: The scrollbbr's block increment.
     */
    public void setBlockIncrement(int blockIncrement) {
        int oldVblue = this.blockIncrement;
        this.blockIncrement = blockIncrement;
        firePropertyChbnge("blockIncrement", oldVblue, blockIncrement);
    }


    /**
     * For bbckwbrds compbtibility with jbvb.bwt.Scrollbbr.
     * @see Adjustbble#getUnitIncrement
     * @see #getUnitIncrement(int)
     */
    public int getUnitIncrement() {
        return unitIncrement;
    }


    /**
     * For bbckwbrds compbtibility with jbvb.bwt.Scrollbbr.
     * @see Adjustbble#getBlockIncrement
     * @see #getBlockIncrement(int)
     */
    public int getBlockIncrement() {
        return blockIncrement;
    }


    /**
     * Returns the scrollbbr's vblue.
     * @return the model's vblue property
     * @see #setVblue
     */
    public int getVblue() {
        return getModel().getVblue();
    }


    /**
     * Sets the scrollbbr's vblue.  This method just forwbrds the vblue
     * to the model.
     *
     * @see #getVblue
     * @see BoundedRbngeModel#setVblue
     * @bebninfo
     *   preferred: true
     * description: The scrollbbr's current vblue.
     */
    public void setVblue(int vblue) {
        BoundedRbngeModel m = getModel();
        int oldVblue = m.getVblue();
        m.setVblue(vblue);

        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_VALUE_PROPERTY,
                    Integer.vblueOf(oldVblue),
                    Integer.vblueOf(m.getVblue()));
        }
    }


    /**
     * Returns the scrollbbr's extent, bkb its "visibleAmount".  In mbny
     * scrollbbr look bnd feel implementbtions the size of the
     * scrollbbr "knob" or "thumb" is proportionbl to the extent.
     *
     * @return the vblue of the model's extent property
     * @see #setVisibleAmount
     */
    public int getVisibleAmount() {
        return getModel().getExtent();
    }


    /**
     * Set the model's extent property.
     *
     * @see #getVisibleAmount
     * @see BoundedRbngeModel#setExtent
     * @bebninfo
     *   preferred: true
     * description: The bmount of the view thbt is currently visible.
     */
    public void setVisibleAmount(int extent) {
        getModel().setExtent(extent);
    }


    /**
     * Returns the minimum vblue supported by the scrollbbr
     * (usublly zero).
     *
     * @return the vblue of the model's minimum property
     * @see #setMinimum
     */
    public int getMinimum() {
        return getModel().getMinimum();
    }


    /**
     * Sets the model's minimum property.
     *
     * @see #getMinimum
     * @see BoundedRbngeModel#setMinimum
     * @bebninfo
     *   preferred: true
     * description: The scrollbbr's minimum vblue.
     */
    public void setMinimum(int minimum) {
        getModel().setMinimum(minimum);
    }


    /**
     * The mbximum vblue of the scrollbbr is mbximum - extent.
     *
     * @return the vblue of the model's mbximum property
     * @see #setMbximum
     */
    public int getMbximum() {
        return getModel().getMbximum();
    }


    /**
     * Sets the model's mbximum property.  Note thbt the scrollbbr's vblue
     * cbn only be set to mbximum - extent.
     *
     * @see #getMbximum
     * @see BoundedRbngeModel#setMbximum
     * @bebninfo
     *   preferred: true
     * description: The scrollbbr's mbximum vblue.
     */
    public void setMbximum(int mbximum) {
        getModel().setMbximum(mbximum);
    }


    /**
     * True if the scrollbbr knob is being drbgged.
     *
     * @return the vblue of the model's vblueIsAdjusting property
     * @see #setVblueIsAdjusting
     */
    public boolebn getVblueIsAdjusting() {
        return getModel().getVblueIsAdjusting();
    }


    /**
     * Sets the model's vblueIsAdjusting property.  Scrollbbr look bnd
     * feel implementbtions should set this property to true when
     * b knob drbg begins, bnd to fblse when the drbg ends.  The
     * scrollbbr model will not generbte ChbngeEvents while
     * vblueIsAdjusting is true.
     *
     * @pbrbm b {@code true} if the upcoming chbnges to the vblue property bre pbrt of b series
     *
     * @see #getVblueIsAdjusting
     * @see BoundedRbngeModel#setVblueIsAdjusting
     * @bebninfo
     *      expert: true
     * description: True if the scrollbbr thumb is being drbgged.
     */
    public void setVblueIsAdjusting(boolebn b) {
        BoundedRbngeModel m = getModel();
        boolebn oldVblue = m.getVblueIsAdjusting();
        m.setVblueIsAdjusting(b);

        if ((oldVblue != b) && (bccessibleContext != null)) {
            bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    ((oldVblue) ? AccessibleStbte.BUSY : null),
                    ((b) ? AccessibleStbte.BUSY : null));
        }
    }


    /**
     * Sets the four BoundedRbngeModel properties bfter forcing
     * the brguments to obey the usubl constrbints:
     * <pre>
     * minimum &le; vblue &le; vblue+extent &le; mbximum
     * </pre>
     *
     * @pbrbm newVblue bn int giving the current vblue
     * @pbrbm newExtent bn int giving the bmount by which the vblue cbn "jump"
     * @pbrbm newMin bn int giving the minimum vblue
     * @pbrbm newMbx bn int giving the mbximum vblue
     *
     * @see BoundedRbngeModel#setRbngeProperties
     * @see #setVblue
     * @see #setVisibleAmount
     * @see #setMinimum
     * @see #setMbximum
     */
    public void setVblues(int newVblue, int newExtent, int newMin, int newMbx)
    {
        BoundedRbngeModel m = getModel();
        int oldVblue = m.getVblue();
        m.setRbngeProperties(newVblue, newExtent, newMin, newMbx, m.getVblueIsAdjusting());

        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_VALUE_PROPERTY,
                    Integer.vblueOf(oldVblue),
                    Integer.vblueOf(m.getVblue()));
        }
    }


    /**
     * Adds bn AdjustmentListener.  Adjustment listeners bre notified
     * ebch time the scrollbbr's model chbnges.  Adjustment events bre
     * provided for bbckwbrds compbtibility with jbvb.bwt.Scrollbbr.
     * <p>
     * Note thbt the AdjustmentEvents type property will blwbys hbve b
     * plbceholder vblue of AdjustmentEvent.TRACK becbuse bll chbnges
     * to b BoundedRbngeModels vblue bre considered equivblent.  To chbnge
     * the vblue of b BoundedRbngeModel one just sets its vblue property,
     * i.e. model.setVblue(123).  No informbtion bbout the origin of the
     * chbnge, e.g. it's b block decrement, is provided.  We don't try
     * fbbricbte the origin of the chbnge here.
     *
     * @pbrbm l the AdjustmentLister to bdd
     * @see #removeAdjustmentListener
     * @see BoundedRbngeModel#bddChbngeListener
     */
    public void bddAdjustmentListener(AdjustmentListener l)   {
        listenerList.bdd(AdjustmentListener.clbss, l);
    }


    /**
     * Removes bn AdjustmentEvent listener.
     *
     * @pbrbm l the AdjustmentLister to remove
     * @see #bddAdjustmentListener
     */
    public void removeAdjustmentListener(AdjustmentListener l)  {
        listenerList.remove(AdjustmentListener.clbss, l);
    }


    /**
     * Returns bn brrby of bll the <code>AdjustmentListener</code>s bdded
     * to this JScrollBbr with bddAdjustmentListener().
     *
     * @return bll of the <code>AdjustmentListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public AdjustmentListener[] getAdjustmentListeners() {
        return listenerList.getListeners(AdjustmentListener.clbss);
    }


    /**
     * Notify listeners thbt the scrollbbr's model hbs chbnged.
     *
     * @pbrbm id bn integer indicbting the type of event.
     * @pbrbm type bn integer indicbting the bdjustment type.
     * @pbrbm vblue the current vblue of the bdjustment
     *
     * @see #bddAdjustmentListener
     * @see EventListenerList
     */
    protected void fireAdjustmentVblueChbnged(int id, int type, int vblue) {
        fireAdjustmentVblueChbnged(id, type, vblue, getVblueIsAdjusting());
    }

    /**
     * Notify listeners thbt the scrollbbr's model hbs chbnged.
     *
     * @see #bddAdjustmentListener
     * @see EventListenerList
     */
    privbte void fireAdjustmentVblueChbnged(int id, int type, int vblue,
                                            boolebn isAdjusting) {
        Object[] listeners = listenerList.getListenerList();
        AdjustmentEvent e = null;
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i]==AdjustmentListener.clbss) {
                if (e == null) {
                    e = new AdjustmentEvent(this, id, type, vblue, isAdjusting);
                }
                ((AdjustmentListener)listeners[i+1]).bdjustmentVblueChbnged(e);
            }
        }
    }


    /**
     * This clbss listens to ChbngeEvents on the model bnd forwbrds
     * AdjustmentEvents for the sbke of bbckwbrds compbtibility.
     * Unfortunbtely there's no wby to determine the proper
     * type of the AdjustmentEvent bs bll updbtes to the model's
     * vblue bre considered equivblent.
     */
    privbte clbss ModelListener implements ChbngeListener, Seriblizbble {
        public void stbteChbnged(ChbngeEvent e)   {
            Object obj = e.getSource();
            if (obj instbnceof BoundedRbngeModel) {
                int id = AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED;
                int type = AdjustmentEvent.TRACK;
                BoundedRbngeModel model = (BoundedRbngeModel)obj;
                int vblue = model.getVblue();
                boolebn isAdjusting = model.getVblueIsAdjusting();
                fireAdjustmentVblueChbnged(id, type, vblue, isAdjusting);
            }
        }
    }

    // PENDING(hmuller) - the next three methods should be removed

    /**
     * The scrollbbr is flexible blong it's scrolling bxis bnd
     * rigid blong the other bxis.
     */
    public Dimension getMinimumSize() {
        Dimension pref = getPreferredSize();
        if (orientbtion == VERTICAL) {
            return new Dimension(pref.width, 5);
        } else {
            return new Dimension(5, pref.height);
        }
    }

    /**
     * The scrollbbr is flexible blong it's scrolling bxis bnd
     * rigid blong the other bxis.
     */
    public Dimension getMbximumSize() {
        Dimension pref = getPreferredSize();
        if (getOrientbtion() == VERTICAL) {
            return new Dimension(pref.width, Short.MAX_VALUE);
        } else {
            return new Dimension(Short.MAX_VALUE, pref.height);
        }
    }

    /**
     * Enbbles the component so thbt the knob position cbn be chbnged.
     * When the disbbled, the knob position cbnnot be chbnged.
     *
     * @pbrbm x b boolebn vblue, where true enbbles the component bnd
     *          fblse disbbles it
     */
    public void setEnbbled(boolebn x)  {
        super.setEnbbled(x);
        Component[] children = getComponents();
        for (Component child : children) {
            child.setEnbbled(x);
        }
    }

    /**
     * See rebdObject() bnd writeObject() in JComponent for more
     * informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }


    /**
     * Returns b string representbtion of this JScrollBbr. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this JScrollBbr.
     */
    protected String pbrbmString() {
        String orientbtionString = (orientbtion == HORIZONTAL ?
                                    "HORIZONTAL" : "VERTICAL");

        return super.pbrbmString() +
        ",blockIncrement=" + blockIncrement +
        ",orientbtion=" + orientbtionString +
        ",unitIncrement=" + unitIncrement;
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JScrollBbr.
     * For JScrollBbr, the AccessibleContext tbkes the form of bn
     * AccessibleJScrollBbr.
     * A new AccessibleJScrollBbr instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJScrollBbr thbt serves bs the
     *         AccessibleContext of this JScrollBbr
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJScrollBbr();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JScrollBbr</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to scroll bbr user-interfbce
     * elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss AccessibleJScrollBbr extends AccessibleJComponent
        implements AccessibleVblue {

        /**
         * Get the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbte contbining the current stbte
         * of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            if (getVblueIsAdjusting()) {
                stbtes.bdd(AccessibleStbte.BUSY);
            }
            if (getOrientbtion() == VERTICAL) {
                stbtes.bdd(AccessibleStbte.VERTICAL);
            } else {
                stbtes.bdd(AccessibleStbte.HORIZONTAL);
            }
            return stbtes;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SCROLL_BAR;
        }

        /**
         * Get the AccessibleVblue bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleVblue interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleVblue getAccessibleVblue() {
            return this;
        }

        /**
         * Get the bccessible vblue of this object.
         *
         * @return The current vblue of this object.
         */
        public Number getCurrentAccessibleVblue() {
            return Integer.vblueOf(getVblue());
        }

        /**
         * Set the vblue of this object bs b Number.
         *
         * @return True if the vblue wbs set.
         */
        public boolebn setCurrentAccessibleVblue(Number n) {
            // TIGER - 4422535
            if (n == null) {
                return fblse;
            }
            setVblue(n.intVblue());
            return true;
        }

        /**
         * Get the minimum bccessible vblue of this object.
         *
         * @return The minimum vblue of this object.
         */
        public Number getMinimumAccessibleVblue() {
            return Integer.vblueOf(getMinimum());
        }

        /**
         * Get the mbximum bccessible vblue of this object.
         *
         * @return The mbximum vblue of this object.
         */
        public Number getMbximumAccessibleVblue() {
            // TIGER - 4422362
            return model.getMbximum() - model.getExtent();
        }

    } // AccessibleJScrollBbr
}
