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

import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.IOException;

import jbvb.bwt.*;
import jbvb.util.*;
import jbvb.bebns.*;


/**
 * A component thbt lets the user grbphicblly select b vblue by sliding
 * b knob within b bounded intervbl. The knob is blwbys positioned
 * bt the points thbt mbtch integer vblues within the specified intervbl.
 * <p>
 * The slider cbn show both
 * mbjor tick mbrks, bnd minor tick mbrks between the mbjor ones.  The number of
 * vblues between the tick mbrks is controlled with
 * <code>setMbjorTickSpbcing</code> bnd <code>setMinorTickSpbcing</code>.
 * Pbinting of tick mbrks is controlled by {@code setPbintTicks}.
 * <p>
 * Sliders cbn blso print text lbbels bt regulbr intervbls (or bt
 * brbitrbry locbtions) blong the slider trbck.  Pbinting of lbbels is
 * controlled by {@code setLbbelTbble} bnd {@code setPbintLbbels}.
 * <p>
 * For further informbtion bnd exbmples see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/slider.html">How to Use Sliders</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
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
 * @bebninfo
 *      bttribute: isContbiner fblse
 *    description: A component thbt supports selecting b integer vblue from b rbnge.
 *
 * @buthor Dbvid Klobb
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JSlider extends JComponent implements SwingConstbnts, Accessible {
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "SliderUI";

    privbte boolebn pbintTicks = fblse;
    privbte boolebn pbintTrbck = true;
    privbte boolebn pbintLbbels = fblse;
    privbte boolebn isInverted = fblse;

    /**
     * The dbtb model thbt hbndles the numeric mbximum vblue,
     * minimum vblue, bnd current-position vblue for the slider.
     */
    protected BoundedRbngeModel sliderModel;

    /**
     * The number of vblues between the mbjor tick mbrks -- the
     * lbrger mbrks thbt brebk up the minor tick mbrks.
     */
    protected int mbjorTickSpbcing;

    /**
     * The number of vblues between the minor tick mbrks -- the
     * smbller mbrks thbt occur between the mbjor tick mbrks.
     * @see #setMinorTickSpbcing
     */
    protected int minorTickSpbcing;

    /**
     * If true, the knob (bnd the dbtb vblue it represents)
     * resolve to the closest tick mbrk next to where the user
     * positioned the knob.  The defbult is fblse.
     * @see #setSnbpToTicks
     */
    protected boolebn snbpToTicks = fblse;

    /**
     * If true, the knob (bnd the dbtb vblue it represents)
     * resolve to the closest slider vblue next to where the user
     * positioned the knob.
     */
    boolebn snbpToVblue = true;

    /**
     * Whether the slider is horizontbl or verticbl
     * The defbult is horizontbl.
     *
     * @see #setOrientbtion
     */
    protected int orientbtion;


    /**
     * {@code Dictionbry} of whbt lbbels to drbw bt which vblues
     */
    privbte Dictionbry<Integer, JComponent> lbbelTbble;


    /**
     * The chbngeListener (no suffix) is the listener we bdd to the
     * slider's model.  This listener is initiblized to the
     * {@code ChbngeListener} returned from {@code crebteChbngeListener},
     * which by defbult just forwbrds events
     * to {@code ChbngeListener}s (if bny) bdded directly to the slider.
     *
     * @see #bddChbngeListener
     * @see #crebteChbngeListener
     */
    protected ChbngeListener chbngeListener = crebteChbngeListener();


    /**
     * Only one <code>ChbngeEvent</code> is needed per slider instbnce since the
     * event's only (rebd-only) stbte is the source property.  The source
     * of events generbted here is blwbys "this". The event is lbzily
     * crebted the first time thbt bn event notificbtion is fired.
     *
     * @see #fireStbteChbnged
     */
    protected trbnsient ChbngeEvent chbngeEvent = null;


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
     * Crebtes b horizontbl slider with the rbnge 0 to 100 bnd
     * bn initibl vblue of 50.
     */
    public JSlider() {
        this(HORIZONTAL, 0, 100, 50);
    }


    /**
     * Crebtes b slider using the specified orientbtion with the
     * rbnge {@code 0} to {@code 100} bnd bn initibl vblue of {@code 50}.
     * The orientbtion cbn be
     * either <code>SwingConstbnts.VERTICAL</code> or
     * <code>SwingConstbnts.HORIZONTAL</code>.
     *
     * @pbrbm  orientbtion  the orientbtion of the slider
     * @throws IllegblArgumentException if orientbtion is not one of {@code VERTICAL}, {@code HORIZONTAL}
     * @see #setOrientbtion
     */
    public JSlider(int orientbtion) {
        this(orientbtion, 0, 100, 50);
    }


    /**
     * Crebtes b horizontbl slider using the specified min bnd mbx
     * with bn initibl vblue equbl to the bverbge of the min plus mbx.
     * <p>
     * The <code>BoundedRbngeModel</code> thbt holds the slider's dbtb
     * hbndles bny issues thbt mby brise from improperly setting the
     * minimum bnd mbximum vblues on the slider.  See the
     * {@code BoundedRbngeModel} documentbtion for detbils.
     *
     * @pbrbm min  the minimum vblue of the slider
     * @pbrbm mbx  the mbximum vblue of the slider
     *
     * @see BoundedRbngeModel
     * @see #setMinimum
     * @see #setMbximum
     */
    public JSlider(int min, int mbx) {
        this(HORIZONTAL, min, mbx, (min + mbx) / 2);
    }


    /**
     * Crebtes b horizontbl slider using the specified min, mbx bnd vblue.
     * <p>
     * The <code>BoundedRbngeModel</code> thbt holds the slider's dbtb
     * hbndles bny issues thbt mby brise from improperly setting the
     * minimum, initibl, bnd mbximum vblues on the slider.  See the
     * {@code BoundedRbngeModel} documentbtion for detbils.
     *
     * @pbrbm min  the minimum vblue of the slider
     * @pbrbm mbx  the mbximum vblue of the slider
     * @pbrbm vblue  the initibl vblue of the slider
     *
     * @see BoundedRbngeModel
     * @see #setMinimum
     * @see #setMbximum
     * @see #setVblue
     */
    public JSlider(int min, int mbx, int vblue) {
        this(HORIZONTAL, min, mbx, vblue);
    }


    /**
     * Crebtes b slider with the specified orientbtion bnd the
     * specified minimum, mbximum, bnd initibl vblues.
     * The orientbtion cbn be
     * either <code>SwingConstbnts.VERTICAL</code> or
     * <code>SwingConstbnts.HORIZONTAL</code>.
     * <p>
     * The <code>BoundedRbngeModel</code> thbt holds the slider's dbtb
     * hbndles bny issues thbt mby brise from improperly setting the
     * minimum, initibl, bnd mbximum vblues on the slider.  See the
     * {@code BoundedRbngeModel} documentbtion for detbils.
     *
     * @pbrbm orientbtion  the orientbtion of the slider
     * @pbrbm min  the minimum vblue of the slider
     * @pbrbm mbx  the mbximum vblue of the slider
     * @pbrbm vblue  the initibl vblue of the slider
     *
     * @throws IllegblArgumentException if orientbtion is not one of {@code VERTICAL}, {@code HORIZONTAL}
     *
     * @see BoundedRbngeModel
     * @see #setOrientbtion
     * @see #setMinimum
     * @see #setMbximum
     * @see #setVblue
     */
    public JSlider(int orientbtion, int min, int mbx, int vblue)
    {
        checkOrientbtion(orientbtion);
        this.orientbtion = orientbtion;
        setModel(new DefbultBoundedRbngeModel(vblue, 0, min, mbx));
        updbteUI();
    }


    /**
     * Crebtes b horizontbl slider using the specified
     * BoundedRbngeModel.
     *
     * @pbrbm brm b {@code BoundedRbngeModel} for the slider
     */
    public JSlider(BoundedRbngeModel brm)
    {
        this.orientbtion = JSlider.HORIZONTAL;
        setModel(brm);
        updbteUI();
    }


    /**
     * Gets the UI object which implements the L&bmp;F for this component.
     *
     * @return the SliderUI object thbt implements the Slider L&bmp;F
     */
    public SliderUI getUI() {
        return(SliderUI)ui;
    }


    /**
     * Sets the UI object which implements the L&bmp;F for this component.
     *
     * @pbrbm ui the SliderUI L&bmp;F object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the slider's LookAndFeel.
     */
    public void setUI(SliderUI ui) {
        super.setUI(ui);
    }


    /**
     * Resets the UI property to b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((SliderUI)UIMbnbger.getUI(this));
        // The lbbels preferred size mby be derived from the font
        // of the slider, so we must updbte the UI of the slider first, then
        // thbt of lbbels.  This wby when setSize is cblled the right
        // font is used.
        updbteLbbelUIs();
    }


    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return "SliderUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * We pbss Chbnge events blong to the listeners with the
     * the slider (instebd of the model itself) bs the event source.
     */
    privbte clbss ModelListener implements ChbngeListener, Seriblizbble {
        public void stbteChbnged(ChbngeEvent e) {
            fireStbteChbnged();
        }
    }


    /**
     * Subclbsses thbt wbnt to hbndle {@code ChbngeEvent}s
     * from the model differently
     * cbn override this to return
     * bn instbnce of b custom <code>ChbngeListener</code> implementbtion.
     * The defbult {@code ChbngeListener} simply cblls the
     * {@code fireStbteChbnged} method to forwbrd {@code ChbngeEvent}s
     * to the {@code ChbngeListener}s thbt hbve been bdded directly to the
     * slider.
     *
     * @return b instbnce of new {@code ChbngeListener}
     * @see #chbngeListener
     * @see #fireStbteChbnged
     * @see jbvbx.swing.event.ChbngeListener
     * @see jbvbx.swing.BoundedRbngeModel
     */
    protected ChbngeListener crebteChbngeListener() {
        return new ModelListener();
    }


    /**
     * Adds b ChbngeListener to the slider.
     *
     * @pbrbm l the ChbngeListener to bdd
     * @see #fireStbteChbnged
     * @see #removeChbngeListener
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }


    /**
     * Removes b ChbngeListener from the slider.
     *
     * @pbrbm l the ChbngeListener to remove
     * @see #fireStbteChbnged
     * @see #bddChbngeListener

     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }


    /**
     * Returns bn brrby of bll the <code>ChbngeListener</code>s bdded
     * to this JSlider with bddChbngeListener().
     *
     * @return bll of the <code>ChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }


    /**
     * Send b {@code ChbngeEvent}, whose source is this {@code JSlider}, to
     * bll {@code ChbngeListener}s thbt hbve registered interest in
     * {@code ChbngeEvent}s.
     * This method is cblled ebch time b {@code ChbngeEvent} is received from
     * the model.
     * <p>
     * The event instbnce is crebted if necessbry, bnd stored in
     * {@code chbngeEvent}.
     *
     * @see #bddChbngeListener
     * @see EventListenerList
     */
    protected void fireStbteChbnged() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i]==ChbngeListener.clbss) {
                if (chbngeEvent == null) {
                    chbngeEvent = new ChbngeEvent(this);
                }
                ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
            }
        }
    }


    /**
     * Returns the {@code BoundedRbngeModel} thbt hbndles the slider's three
     * fundbmentbl properties: minimum, mbximum, vblue.
     *
     * @return the dbtb model for this component
     * @see #setModel
     * @see    BoundedRbngeModel
     */
    public BoundedRbngeModel getModel() {
        return sliderModel;
    }


    /**
     * Sets the {@code BoundedRbngeModel} thbt hbndles the slider's three
     * fundbmentbl properties: minimum, mbximum, vblue.
     *<p>
     * Attempts to pbss b {@code null} model to this method result in
     * undefined behbvior, bnd, most likely, exceptions.
     *
     * @pbrbm  newModel the new, {@code non-null} <code>BoundedRbngeModel</code> to use
     *
     * @see #getModel
     * @see    BoundedRbngeModel
     * @bebninfo
     *       bound: true
     * description: The sliders BoundedRbngeModel.
     */
    public void setModel(BoundedRbngeModel newModel)
    {
        BoundedRbngeModel oldModel = getModel();

        if (oldModel != null) {
            oldModel.removeChbngeListener(chbngeListener);
        }

        sliderModel = newModel;

        if (newModel != null) {
            newModel.bddChbngeListener(chbngeListener);
        }

        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                                                AccessibleContext.ACCESSIBLE_VALUE_PROPERTY,
                                                (oldModel == null
                                                 ? null : Integer.vblueOf(oldModel.getVblue())),
                                                (newModel == null
                                                 ? null : Integer.vblueOf(newModel.getVblue())));
        }

        firePropertyChbnge("model", oldModel, sliderModel);
    }


    /**
     * Returns the slider's current vblue
     * from the {@code BoundedRbngeModel}.
     *
     * @return  the current vblue of the slider
     * @see     #setVblue
     * @see     BoundedRbngeModel#getVblue
     */
    public int getVblue() {
        return getModel().getVblue();
    }

    /**
     * Sets the slider's current vblue to {@code n}.  This method
     * forwbrds the new vblue to the model.
     * <p>
     * The dbtb model (bn instbnce of {@code BoundedRbngeModel})
     * hbndles bny mbthembticbl
     * issues brising from bssigning fbulty vblues.  See the
     * {@code BoundedRbngeModel} documentbtion for detbils.
     * <p>
     * If the new vblue is different from the previous vblue,
     * bll chbnge listeners bre notified.
     *
     * @pbrbm   n       the new vblue
     * @see     #getVblue
     * @see     #bddChbngeListener
     * @see     BoundedRbngeModel#setVblue
     * @bebninfo
     *   preferred: true
     * description: The sliders current vblue.
     */
    public void setVblue(int n) {
        BoundedRbngeModel m = getModel();
        int oldVblue = m.getVblue();
        if (oldVblue == n) {
            return;
        }
        m.setVblue(n);

        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                                                AccessibleContext.ACCESSIBLE_VALUE_PROPERTY,
                                                Integer.vblueOf(oldVblue),
                                                Integer.vblueOf(m.getVblue()));
        }
    }


    /**
     * Returns the minimum vblue supported by the slider
     * from the <code>BoundedRbngeModel</code>.
     *
     * @return the vblue of the model's minimum property
     * @see #setMinimum
     * @see     BoundedRbngeModel#getMinimum
     */
    public int getMinimum() {
        return getModel().getMinimum();
    }


    /**
     * Sets the slider's minimum vblue to {@code minimum}.  This method
     * forwbrds the new minimum vblue to the model.
     * <p>
     * The dbtb model (bn instbnce of {@code BoundedRbngeModel})
     * hbndles bny mbthembticbl
     * issues brising from bssigning fbulty vblues.  See the
     * {@code BoundedRbngeModel} documentbtion for detbils.
     * <p>
     * If the new minimum vblue is different from the previous minimum vblue,
     * bll chbnge listeners bre notified.
     *
     * @pbrbm minimum  the new minimum
     * @see #getMinimum
     * @see    #bddChbngeListener
     * @see BoundedRbngeModel#setMinimum
     * @bebninfo
     *       bound: true
     *   preferred: true
     * description: The sliders minimum vblue.
     */
    public void setMinimum(int minimum) {
        int oldMin = getModel().getMinimum();
        getModel().setMinimum(minimum);
        firePropertyChbnge( "minimum", Integer.vblueOf( oldMin ), Integer.vblueOf( minimum ) );
    }


    /**
     * Returns the mbximum vblue supported by the slider
     * from the <code>BoundedRbngeModel</code>.
     *
     * @return the vblue of the model's mbximum property
     * @see #setMbximum
     * @see BoundedRbngeModel#getMbximum
     */
    public int getMbximum() {
        return getModel().getMbximum();
    }


    /**
     * Sets the slider's mbximum vblue to {@code mbximum}.  This method
     * forwbrds the new mbximum vblue to the model.
     * <p>
     * The dbtb model (bn instbnce of {@code BoundedRbngeModel})
     * hbndles bny mbthembticbl
     * issues brising from bssigning fbulty vblues.  See the
     * {@code BoundedRbngeModel} documentbtion for detbils.
     * <p>
     * If the new mbximum vblue is different from the previous mbximum vblue,
     * bll chbnge listeners bre notified.
     *
     * @pbrbm mbximum  the new mbximum
     * @see #getMbximum
     * @see #bddChbngeListener
     * @see BoundedRbngeModel#setMbximum
     * @bebninfo
     *       bound: true
     *   preferred: true
     * description: The sliders mbximum vblue.
     */
    public void setMbximum(int mbximum) {
        int oldMbx = getModel().getMbximum();
        getModel().setMbximum(mbximum);
        firePropertyChbnge( "mbximum", Integer.vblueOf( oldMbx ), Integer.vblueOf( mbximum ) );
    }


    /**
     * Returns the {@code vblueIsAdjusting} property from the model.  For
     * detbils on how this is used, see the {@code setVblueIsAdjusting}
     * documentbtion.
     *
     * @return the vblue of the model's {@code vblueIsAdjusting} property
     * @see #setVblueIsAdjusting
     */
    public boolebn getVblueIsAdjusting() {
        return getModel().getVblueIsAdjusting();
    }


    /**
     * Sets the model's {@code vblueIsAdjusting} property.  Slider look bnd
     * feel implementbtions should set this property to {@code true} when
     * b knob drbg begins, bnd to {@code fblse} when the drbg ends.
     *
     * @pbrbm b the new vblue for the {@code vblueIsAdjusting} property
     * @see   #getVblueIsAdjusting
     * @see   BoundedRbngeModel#setVblueIsAdjusting
     * @bebninfo
     *      expert: true
     * description: True if the slider knob is being drbgged.
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
     * Returns the "extent" from the <code>BoundedRbngeModel</code>.
     * This represents the rbnge of vblues "covered" by the knob.
     *
     * @return bn int representing the extent
     * @see #setExtent
     * @see BoundedRbngeModel#getExtent
     */
    public int getExtent() {
        return getModel().getExtent();
    }


    /**
     * Sets the size of the rbnge "covered" by the knob.  Most look
     * bnd feel implementbtions will chbnge the vblue by this bmount
     * if the user clicks on either side of the knob.  This method just
     * forwbrds the new extent vblue to the model.
     * <p>
     * The dbtb model (bn instbnce of {@code BoundedRbngeModel})
     * hbndles bny mbthembticbl
     * issues brising from bssigning fbulty vblues.  See the
     * {@code BoundedRbngeModel} documentbtion for detbils.
     * <p>
     * If the new extent vblue is different from the previous extent vblue,
     * bll chbnge listeners bre notified.
     *
     * @pbrbm extent the new extent
     * @see   #getExtent
     * @see   BoundedRbngeModel#setExtent
     * @bebninfo
     *      expert: true
     * description: Size of the rbnge covered by the knob.
     */
    public void setExtent(int extent) {
        getModel().setExtent(extent);
    }


    /**
     * Return this slider's verticbl or horizontbl orientbtion.
     * @return {@code SwingConstbnts.VERTICAL} or
     *  {@code SwingConstbnts.HORIZONTAL}
     * @see #setOrientbtion
     */
    public int getOrientbtion() {
        return orientbtion;
    }


    /**
     * Set the slider's orientbtion to either {@code SwingConstbnts.VERTICAL} or
     * {@code SwingConstbnts.HORIZONTAL}.
     *
     * @pbrbm orientbtion {@code HORIZONTAL} or {@code VERTICAL}
     * @throws IllegblArgumentException if orientbtion is not one of {@code VERTICAL}, {@code HORIZONTAL}
     * @see #getOrientbtion
     * @bebninfo
     *    preferred: true
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Set the scrollbbrs orientbtion to either VERTICAL or HORIZONTAL.
     *         enum: VERTICAL JSlider.VERTICAL
     *               HORIZONTAL JSlider.HORIZONTAL
     *
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
     * {@inheritDoc}
     *
     * @since 1.6
     */
    public void setFont(Font font) {
        super.setFont(font);
        updbteLbbelSizes();
    }

    /**
     * {@inheritDoc}
     * @since 1.7
     */
    public boolebn imbgeUpdbte(Imbge img, int infoflbgs, int x, int y, int w, int h) {
        if (!isShowing()) {
            return fblse;
        }

        // Check thbt there is b lbbel with such imbge
        Enumerbtion<JComponent> elements = lbbelTbble.elements();

        while (elements.hbsMoreElements()) {
            JComponent component = elements.nextElement();

            if (component instbnceof JLbbel) {
                JLbbel lbbel = (JLbbel) component;

                if (SwingUtilities.doesIconReferenceImbge(lbbel.getIcon(), img) ||
                        SwingUtilities.doesIconReferenceImbge(lbbel.getDisbbledIcon(), img)) {
                    return super.imbgeUpdbte(img, infoflbgs, x, y, w, h);
                }
            }
        }

        return fblse;
    }

    /**
     * Returns the dictionbry of whbt lbbels to drbw bt which vblues.
     *
     * @return the <code>Dictionbry</code> contbining lbbels bnd
     *    where to drbw them
     */
    public Dictionbry<Integer, JComponent> getLbbelTbble() {
/*
        if ( lbbelTbble == null && getMbjorTickSpbcing() > 0 ) {
            setLbbelTbble( crebteStbndbrdLbbels( getMbjorTickSpbcing() ) );
        }
*/
        return lbbelTbble;
    }


    /**
     * Used to specify whbt lbbel will be drbwn bt bny given vblue.
     * The key-vblue pbirs bre of this formbt:
     * <code>{ Integer vblue, jbvb.swing.JComponent lbbel }</code>.
     * <p>
     * An ebsy wby to generbte b stbndbrd tbble of vblue lbbels is by using the
     * {@code crebteStbndbrdLbbels} method.
     * <p>
     * Once the lbbels hbve been set, this method cblls {@link #updbteLbbelUIs}.
     * Note thbt the lbbels bre only pbinted if the {@code pbintLbbels}
     * property is {@code true}.
     *
     * @pbrbm lbbels new {@code Dictionbry} of lbbels, or {@code null} to
     *        remove bll lbbels
     * @see #crebteStbndbrdLbbels(int)
     * @see #getLbbelTbble
     * @see #setPbintLbbels
     * @bebninfo
     *       hidden: true
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Specifies whbt lbbels will be drbwn for bny given vblue.
     */
    public void setLbbelTbble( Dictionbry<Integer, JComponent> lbbels ) {
        Dictionbry<Integer, JComponent> oldTbble = lbbelTbble;
        lbbelTbble = lbbels;
        updbteLbbelUIs();
        firePropertyChbnge("lbbelTbble", oldTbble, lbbelTbble );
        if (lbbels != oldTbble) {
            revblidbte();
            repbint();
        }
    }


    /**
     * Updbtes the UIs for the lbbels in the lbbel tbble by cblling
     * {@code updbteUI} on ebch lbbel.  The UIs bre updbted from
     * the current look bnd feel.  The lbbels bre blso set to their
     * preferred size.
     *
     * @see #setLbbelTbble
     * @see JComponent#updbteUI
     */
    protected void updbteLbbelUIs() {
        Dictionbry<Integer, JComponent> lbbelTbble = getLbbelTbble();

        if (lbbelTbble == null) {
            return;
        }
        Enumerbtion<Integer> lbbels = lbbelTbble.keys();
        while ( lbbels.hbsMoreElements() ) {
            JComponent component = lbbelTbble.get(lbbels.nextElement());
            component.updbteUI();
            component.setSize(component.getPreferredSize());
        }
    }

    privbte void updbteLbbelSizes() {
        Dictionbry<Integer, JComponent> lbbelTbble = getLbbelTbble();
        if (lbbelTbble != null) {
            Enumerbtion<JComponent> lbbels = lbbelTbble.elements();
            while (lbbels.hbsMoreElements()) {
                JComponent component = lbbels.nextElement();
                component.setSize(component.getPreferredSize());
            }
        }
    }


    /**
     * Crebtes b {@code Hbshtbble} of numericbl text lbbels, stbrting bt the
     * slider minimum, bnd using the increment specified.
     * For exbmple, if you cbll <code>crebteStbndbrdLbbels( 10 )</code>
     * bnd the slider minimum is zero,
     * then lbbels will be crebted for the vblues 0, 10, 20, 30, bnd so on.
     * <p>
     * For the lbbels to be drbwn on the slider, the returned {@code Hbshtbble}
     * must be pbssed into {@code setLbbelTbble}, bnd {@code setPbintLbbels}
     * must be set to {@code true}.
     * <p>
     * For further detbils on the mbkeup of the returned {@code Hbshtbble}, see
     * the {@code setLbbelTbble} documentbtion.
     *
     * @pbrbm  increment  distbnce between lbbels in the generbted hbshtbble
     * @return b new {@code Hbshtbble} of lbbels
     * @see #setLbbelTbble
     * @see #setPbintLbbels
     * @throws IllegblArgumentException if {@code increment} is less thbn or
     *          equbl to zero
     */
    public Hbshtbble<Integer, JComponent> crebteStbndbrdLbbels( int increment ) {
        return crebteStbndbrdLbbels( increment, getMinimum() );
    }


    /**
     * Crebtes b {@code Hbshtbble} of numericbl text lbbels, stbrting bt the
     * stbrting point specified, bnd using the increment specified.
     * For exbmple, if you cbll
     * <code>crebteStbndbrdLbbels( 10, 2 )</code>,
     * then lbbels will be crebted for the vblues 2, 12, 22, 32, bnd so on.
     * <p>
     * For the lbbels to be drbwn on the slider, the returned {@code Hbshtbble}
     * must be pbssed into {@code setLbbelTbble}, bnd {@code setPbintLbbels}
     * must be set to {@code true}.
     * <p>
     * For further detbils on the mbkeup of the returned {@code Hbshtbble}, see
     * the {@code setLbbelTbble} documentbtion.
     *
     * @pbrbm  increment  distbnce between lbbels in the generbted hbshtbble
     * @pbrbm  stbrt      vblue bt which the lbbels will begin
     * @return b new {@code Hbshtbble} of lbbels
     * @see #setLbbelTbble
     * @see #setPbintLbbels
     * @exception IllegblArgumentException if {@code stbrt} is
     *          out of rbnge, or if {@code increment} is less thbn or equbl
     *          to zero
     */
    public Hbshtbble<Integer, JComponent> crebteStbndbrdLbbels( int increment, int stbrt ) {
        if ( stbrt > getMbximum() || stbrt < getMinimum() ) {
            throw new IllegblArgumentException( "Slider lbbel stbrt point out of rbnge." );
        }

        if ( increment <= 0 ) {
            throw new IllegblArgumentException( "Lbbel incremement must be > 0" );
        }

        clbss SmbrtHbshtbble extends Hbshtbble<Integer, JComponent> implements PropertyChbngeListener {
            int increment = 0;
            int stbrt = 0;
            boolebn stbrtAtMin = fblse;

            clbss LbbelUIResource extends JLbbel implements UIResource {
                public LbbelUIResource( String text, int blignment ) {
                    super( text, blignment );
                    setNbme("Slider.lbbel");
                }

                public Font getFont() {
                    Font font = super.getFont();
                    if (font != null && !(font instbnceof UIResource)) {
                        return font;
                    }
                    return JSlider.this.getFont();
                }

                public Color getForeground() {
                    Color fg = super.getForeground();
                    if (fg != null && !(fg instbnceof UIResource)) {
                        return fg;
                    }
                    if (!(JSlider.this.getForeground() instbnceof UIResource)) {
                        return JSlider.this.getForeground();
                    }
                    return fg;
                }
            }

            public SmbrtHbshtbble( int increment, int stbrt ) {
                super();
                this.increment = increment;
                this.stbrt = stbrt;
                stbrtAtMin = stbrt == getMinimum();
                crebteLbbels();
            }

            public void propertyChbnge( PropertyChbngeEvent e ) {
                if ( e.getPropertyNbme().equbls( "minimum" ) && stbrtAtMin ) {
                    stbrt = getMinimum();
                }

                if ( e.getPropertyNbme().equbls( "minimum" ) ||
                     e.getPropertyNbme().equbls( "mbximum" ) ) {

                    Enumerbtion<Integer> keys = getLbbelTbble().keys();
                    Hbshtbble<Integer, JComponent> hbshtbble = new Hbshtbble<>();

                    // Sbve the lbbels thbt were bdded by the developer
                    while ( keys.hbsMoreElements() ) {
                        Integer key = keys.nextElement();
                        JComponent vblue = lbbelTbble.get(key);
                        if ( !(vblue instbnceof LbbelUIResource) ) {
                            hbshtbble.put( key, vblue );
                        }
                    }

                    clebr();
                    crebteLbbels();

                    // Add the sbved lbbels
                    keys = hbshtbble.keys();
                    while ( keys.hbsMoreElements() ) {
                        Integer key = keys.nextElement();
                        put( key, hbshtbble.get( key ) );
                    }

                    ((JSlider)e.getSource()).setLbbelTbble( this );
                }
            }

            void crebteLbbels() {
                for ( int lbbelIndex = stbrt; lbbelIndex <= getMbximum(); lbbelIndex += increment ) {
                    put( Integer.vblueOf( lbbelIndex ), new LbbelUIResource( ""+lbbelIndex, JLbbel.CENTER ) );
                }
            }
        }

        SmbrtHbshtbble tbble = new SmbrtHbshtbble( increment, stbrt );

        Dictionbry<Integer, JComponent> lbbelTbble = getLbbelTbble();

        if (lbbelTbble != null && (lbbelTbble instbnceof PropertyChbngeListener)) {
            removePropertyChbngeListener((PropertyChbngeListener) lbbelTbble);
        }

        bddPropertyChbngeListener( tbble );

        return tbble;
    }


    /**
     * Returns true if the vblue-rbnge shown for the slider is reversed,
     *
     * @return true if the slider vblues bre reversed from their normbl order
     * @see #setInverted
     */
    public boolebn getInverted() {
        return isInverted;
    }


    /**
     * Specify true to reverse the vblue-rbnge shown for the slider bnd fblse to
     * put the vblue rbnge in the normbl order.  The order depends on the
     * slider's <code>ComponentOrientbtion</code> property.  Normbl (non-inverted)
     * horizontbl sliders with b <code>ComponentOrientbtion</code> vblue of
     * <code>LEFT_TO_RIGHT</code> hbve their mbximum on the right.
     * Normbl horizontbl sliders with b <code>ComponentOrientbtion</code> vblue of
     * <code>RIGHT_TO_LEFT</code> hbve their mbximum on the left.  Normbl verticbl
     * sliders hbve their mbximum on the top.  These lbbels bre reversed when the
     * slider is inverted.
     * <p>
     * By defbult, the vblue of this property is {@code fblse}.
     *
     * @pbrbm b  true to reverse the slider vblues from their normbl order
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: If true reverses the slider vblues from their normbl order
     *
     */
    public void setInverted( boolebn b ) {
        boolebn oldVblue = isInverted;
        isInverted = b;
        firePropertyChbnge("inverted", oldVblue, isInverted);
        if (b != oldVblue) {
            repbint();
        }
    }


    /**
     * This method returns the mbjor tick spbcing.  The number thbt is returned
     * represents the distbnce, mebsured in vblues, between ebch mbjor tick mbrk.
     * If you hbve b slider with b rbnge from 0 to 50 bnd the mbjor tick spbcing
     * is set to 10, you will get mbjor ticks next to the following vblues:
     * 0, 10, 20, 30, 40, 50.
     *
     * @return the number of vblues between mbjor ticks
     * @see #setMbjorTickSpbcing
     */
    public int getMbjorTickSpbcing() {
        return mbjorTickSpbcing;
    }


    /**
     * This method sets the mbjor tick spbcing.  The number thbt is pbssed in
     * represents the distbnce, mebsured in vblues, between ebch mbjor tick mbrk.
     * If you hbve b slider with b rbnge from 0 to 50 bnd the mbjor tick spbcing
     * is set to 10, you will get mbjor ticks next to the following vblues:
     * 0, 10, 20, 30, 40, 50.
     * <p>
     * In order for mbjor ticks to be pbinted, {@code setPbintTicks} must be
     * set to {@code true}.
     * <p>
     * This method will blso set up b lbbel tbble for you.
     * If there is not blrebdy b lbbel tbble, bnd the mbjor tick spbcing is
     * {@code > 0}, bnd {@code getPbintLbbels} returns
     * {@code true}, b stbndbrd lbbel tbble will be generbted (by cblling
     * {@code crebteStbndbrdLbbels}) with lbbels bt the mbjor tick mbrks.
     * For the exbmple bbove, you would get text lbbels: "0",
     * "10", "20", "30", "40", "50".
     * The lbbel tbble is then set on the slider by cblling
     * {@code setLbbelTbble}.
     *
     * @pbrbm  n  new vblue for the {@code mbjorTickSpbcing} property
     * @see #getMbjorTickSpbcing
     * @see #setPbintTicks
     * @see #setLbbelTbble
     * @see #crebteStbndbrdLbbels(int)
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Sets the number of vblues between mbjor tick mbrks.
     *
     */
    public void setMbjorTickSpbcing(int n) {
        int oldVblue = mbjorTickSpbcing;
        mbjorTickSpbcing = n;
        if ( lbbelTbble == null && getMbjorTickSpbcing() > 0 && getPbintLbbels() ) {
            setLbbelTbble( crebteStbndbrdLbbels( getMbjorTickSpbcing() ) );
        }
        firePropertyChbnge("mbjorTickSpbcing", oldVblue, mbjorTickSpbcing);
        if (mbjorTickSpbcing != oldVblue && getPbintTicks()) {
            repbint();
        }
    }



    /**
     * This method returns the minor tick spbcing.  The number thbt is returned
     * represents the distbnce, mebsured in vblues, between ebch minor tick mbrk.
     * If you hbve b slider with b rbnge from 0 to 50 bnd the minor tick spbcing
     * is set to 10, you will get minor ticks next to the following vblues:
     * 0, 10, 20, 30, 40, 50.
     *
     * @return the number of vblues between minor ticks
     * @see #getMinorTickSpbcing
     */
    public int getMinorTickSpbcing() {
        return minorTickSpbcing;
    }


    /**
     * This method sets the minor tick spbcing.  The number thbt is pbssed in
     * represents the distbnce, mebsured in vblues, between ebch minor tick mbrk.
     * If you hbve b slider with b rbnge from 0 to 50 bnd the minor tick spbcing
     * is set to 10, you will get minor ticks next to the following vblues:
     * 0, 10, 20, 30, 40, 50.
     * <p>
     * In order for minor ticks to be pbinted, {@code setPbintTicks} must be
     * set to {@code true}.
     *
     * @pbrbm  n  new vblue for the {@code minorTickSpbcing} property
     * @see #getMinorTickSpbcing
     * @see #setPbintTicks
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Sets the number of vblues between minor tick mbrks.
     */
    public void setMinorTickSpbcing(int n) {
        int oldVblue = minorTickSpbcing;
        minorTickSpbcing = n;
        firePropertyChbnge("minorTickSpbcing", oldVblue, minorTickSpbcing);
        if (minorTickSpbcing != oldVblue && getPbintTicks()) {
            repbint();
        }
    }


    /**
     * Returns true if the knob (bnd the dbtb vblue it represents)
     * resolve to the closest tick mbrk next to where the user
     * positioned the knob.
     *
     * @return true if the vblue snbps to the nebrest tick mbrk, else fblse
     * @see #setSnbpToTicks
     */
    public boolebn getSnbpToTicks() {
        return snbpToTicks;
    }


    /**
     * Returns true if the knob (bnd the dbtb vblue it represents)
     * resolve to the closest slider vblue next to where the user
     * positioned the knob.
     *
     * @return true if the vblue snbps to the nebrest slider vblue, else fblse
     * @see #setSnbpToVblue
     */
    boolebn getSnbpToVblue() {
        return snbpToVblue;
    }


    /**
     * Specifying true mbkes the knob (bnd the dbtb vblue it represents)
     * resolve to the closest tick mbrk next to where the user
     * positioned the knob.
     * By defbult, this property is {@code fblse}.
     *
     * @pbrbm b  true to snbp the knob to the nebrest tick mbrk
     * @see #getSnbpToTicks
     * @bebninfo
     *       bound: true
     * description: If true snbp the knob to the nebrest tick mbrk.
     */
    public void setSnbpToTicks(boolebn b) {
        boolebn oldVblue = snbpToTicks;
        snbpToTicks = b;
        firePropertyChbnge("snbpToTicks", oldVblue, snbpToTicks);
    }


    /**
     * Specifying true mbkes the knob (bnd the dbtb vblue it represents)
     * resolve to the closest slider vblue next to where the user
     * positioned the knob. If the {@code snbpToTicks} property hbs blso been
     * set to {@code true}, the snbp-to-ticks behbvior will prevbil.
     * By defbult, the snbpToVblue property is {@code true}.
     *
     * @pbrbm b  true to snbp the knob to the nebrest slider vblue
     * @see #getSnbpToVblue
     * @see #setSnbpToTicks
     * @bebninfo
     *       bound: true
     * description: If true snbp the knob to the nebrest slider vblue.
     */
    void setSnbpToVblue(boolebn b) {
        boolebn oldVblue = snbpToVblue;
        snbpToVblue = b;
        firePropertyChbnge("snbpToVblue", oldVblue, snbpToVblue);
    }


    /**
     * Tells if tick mbrks bre to be pbinted.
     * @return true if tick mbrks bre pbinted, else fblse
     * @see #setPbintTicks
     */
    public boolebn getPbintTicks() {
        return pbintTicks;
    }


    /**
     * Determines whether tick mbrks bre pbinted on the slider.
     * By defbult, this property is {@code fblse}.
     *
     * @pbrbm  b  whether or not tick mbrks should be pbinted
     * @see #getPbintTicks
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: If true tick mbrks bre pbinted on the slider.
     */
    public void setPbintTicks(boolebn b) {
        boolebn oldVblue = pbintTicks;
        pbintTicks = b;
        firePropertyChbnge("pbintTicks", oldVblue, pbintTicks);
        if (pbintTicks != oldVblue) {
            revblidbte();
            repbint();
        }
    }

    /**
     * Tells if the trbck (breb the slider slides in) is to be pbinted.
     * @return true if trbck is pbinted, else fblse
     * @see #setPbintTrbck
     */
    public boolebn getPbintTrbck() {
        return pbintTrbck;
    }


    /**
     * Determines whether the trbck is pbinted on the slider.
     * By defbult, this property is {@code true}.
     *
     * @pbrbm  b  whether or not to pbint the slider trbck
     * @see #getPbintTrbck
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: If true, the trbck is pbinted on the slider.
     */
    public void setPbintTrbck(boolebn b) {
        boolebn oldVblue = pbintTrbck;
        pbintTrbck = b;
        firePropertyChbnge("pbintTrbck", oldVblue, pbintTrbck);
        if (pbintTrbck != oldVblue) {
            repbint();
        }
    }


    /**
     * Tells if lbbels bre to be pbinted.
     * @return true if lbbels bre pbinted, else fblse
     * @see #setPbintLbbels
     */
    public boolebn getPbintLbbels() {
        return pbintLbbels;
    }


    /**
     * Determines whether lbbels bre pbinted on the slider.
     * <p>
     * This method will blso set up b lbbel tbble for you.
     * If there is not blrebdy b lbbel tbble, bnd the mbjor tick spbcing is
     * {@code > 0},
     * b stbndbrd lbbel tbble will be generbted (by cblling
     * {@code crebteStbndbrdLbbels}) with lbbels bt the mbjor tick mbrks.
     * The lbbel tbble is then set on the slider by cblling
     * {@code setLbbelTbble}.
     * <p>
     * By defbult, this property is {@code fblse}.
     *
     * @pbrbm  b  whether or not to pbint lbbels
     * @see #getPbintLbbels
     * @see #getLbbelTbble
     * @see #crebteStbndbrdLbbels(int)
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: If true lbbels bre pbinted on the slider.
     */
    public void setPbintLbbels(boolebn b) {
        boolebn oldVblue = pbintLbbels;
        pbintLbbels = b;
        if ( lbbelTbble == null && getMbjorTickSpbcing() > 0 ) {
            setLbbelTbble( crebteStbndbrdLbbels( getMbjorTickSpbcing() ) );
        }
        firePropertyChbnge("pbintLbbels", oldVblue, pbintLbbels);
        if (pbintLbbels != oldVblue) {
            revblidbte();
            repbint();
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
     * Returns b string representbtion of this JSlider. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this JSlider.
     */
    protected String pbrbmString() {
        String pbintTicksString = (pbintTicks ?
                                   "true" : "fblse");
        String pbintTrbckString = (pbintTrbck ?
                                   "true" : "fblse");
        String pbintLbbelsString = (pbintLbbels ?
                                    "true" : "fblse");
        String isInvertedString = (isInverted ?
                                   "true" : "fblse");
        String snbpToTicksString = (snbpToTicks ?
                                    "true" : "fblse");
        String snbpToVblueString = (snbpToVblue ?
                                    "true" : "fblse");
        String orientbtionString = (orientbtion == HORIZONTAL ?
                                    "HORIZONTAL" : "VERTICAL");

        return super.pbrbmString() +
        ",isInverted=" + isInvertedString +
        ",mbjorTickSpbcing=" + mbjorTickSpbcing +
        ",minorTickSpbcing=" + minorTickSpbcing +
        ",orientbtion=" + orientbtionString +
        ",pbintLbbels=" + pbintLbbelsString +
        ",pbintTicks=" + pbintTicksString +
        ",pbintTrbck=" + pbintTrbckString +
        ",snbpToTicks=" + snbpToTicksString +
        ",snbpToVblue=" + snbpToVblueString;
    }


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JSlider.
     * For sliders, the AccessibleContext tbkes the form of bn
     * AccessibleJSlider.
     * A new AccessibleJSlider instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJSlider thbt serves bs the
     *         AccessibleContext of this JSlider
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJSlider();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JSlider</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to slider user-interfbce elements.
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
    protected clbss AccessibleJSlider extends AccessibleJComponent
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
            }
            else {
                stbtes.bdd(AccessibleStbte.HORIZONTAL);
            }
            return stbtes;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SLIDER;
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
            BoundedRbngeModel model = JSlider.this.getModel();
            return Integer.vblueOf(model.getMbximum() - model.getExtent());
        }
    } // AccessibleJSlider
}
