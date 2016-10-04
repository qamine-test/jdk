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

import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;

import jbvb.text.Formbt;
import jbvb.text.NumberFormbt;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

import jbvbx.swing.event.*;
import jbvbx.bccessibility.*;
import jbvbx.swing.plbf.ProgressBbrUI;


/**
 * A component thbt visublly displbys the progress of some tbsk.  As the tbsk
 * progresses towbrds completion, the progress bbr displbys the
 * tbsk's percentbge of completion.
 * This percentbge is typicblly represented visublly by b rectbngle which
 * stbrts out empty bnd grbdublly becomes filled in bs the tbsk progresses.
 * In bddition, the progress bbr cbn displby b textubl representbtion of this
 * percentbge.
 * <p>
 * {@code JProgressBbr} uses b {@code BoundedRbngeModel} bs its dbtb model,
 * with the {@code vblue} property representing the "current" stbte of the tbsk,
 * bnd the {@code minimum} bnd {@code mbximum} properties representing the
 * beginning bnd end points, respectively.
 * <p>
 * To indicbte thbt b tbsk of unknown length is executing,
 * you cbn put b progress bbr into indeterminbte mode.
 * While the bbr is in indeterminbte mode,
 * it bnimbtes constbntly to show thbt work is occurring.
 * As soon bs you cbn determine the tbsk's length bnd bmount of progress,
 * you should updbte the progress bbr's vblue
 * bnd switch it bbck to determinbte mode.
 *
 * <p>
 *
 * Here is bn exbmple of crebting b progress bbr,
 * where <code>tbsk</code> is bn object (representing some piece of work)
 * which returns informbtion bbout the progress of the tbsk:
 *
 *<pre>
 *progressBbr = new JProgressBbr(0, tbsk.getLengthOfTbsk());
 *progressBbr.setVblue(0);
 *progressBbr.setStringPbinted(true);
 *</pre>
 *
 * Here is bn exbmple of querying the current stbte of the tbsk, bnd using
 * the returned vblue to updbte the progress bbr:
 *
 *<pre>
 *progressBbr.setVblue(tbsk.getCurrent());
 *</pre>
 *
 * Here is bn exbmple of putting b progress bbr into
 * indeterminbte mode,
 * bnd then switching bbck to determinbte mode
 * once the length of the tbsk is known:
 *
 *<pre>
 *progressBbr = new JProgressBbr();
 *<em>...//when the tbsk of (initiblly) unknown length begins:</em>
 *progressBbr.setIndeterminbte(true);
 *<em>...//do some work; get length of tbsk...</em>
 *progressBbr.setMbximum(newLength);
 *progressBbr.setVblue(newVblue);
 *progressBbr.setIndeterminbte(fblse);
 *</pre>
 *
 * <p>
 *
 * For complete exbmples bnd further documentbtion see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/progress.html" tbrget="_top">How to Monitor Progress</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 *
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
 * @see jbvbx.swing.plbf.bbsic.BbsicProgressBbrUI
 * @see jbvbx.swing.BoundedRbngeModel
 * @see jbvbx.swing.SwingWorker
 *
 * @bebninfo
 *      bttribute: isContbiner fblse
 *    description: A component thbt displbys bn integer vblue.
 *
 * @buthor Michbel C. Albers
 * @buthor Kbthy Wblrbth
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JProgressBbr extends JComponent implements SwingConstbnts, Accessible
{
    /**
     * @see #getUIClbssID
     */
    privbte stbtic finbl String uiClbssID = "ProgressBbrUI";

    /**
     * Whether the progress bbr is horizontbl or verticbl.
     * The defbult is <code>HORIZONTAL</code>.
     *
     * @see #setOrientbtion
     */
    protected int orientbtion;

    /**
     * Whether to displby b border bround the progress bbr.
     * The defbult is <code>true</code>.
     *
     * @see #setBorderPbinted
     */
    protected boolebn pbintBorder;

    /**
     * The object thbt holds the dbtb for the progress bbr.
     *
     * @see #setModel
     */
    protected BoundedRbngeModel model;

    /**
     * An optionbl string thbt cbn be displbyed on the progress bbr.
     * The defbult is <code>null</code>. Setting this to b non-<code>null</code>
     * vblue does not imply thbt the string will be displbyed.
     * To displby the string, {@code pbintString} must be {@code true}.
     *
     * @see #setString
     * @see #setStringPbinted
     */
    protected String progressString;

    /**
     * Whether to displby b string of text on the progress bbr.
     * The defbult is <code>fblse</code>.
     * Setting this to <code>true</code> cbuses b textubl
     * displby of the progress to be rendered on the progress bbr. If
     * the <code>progressString</code> is <code>null</code>,
     * the percentbge of completion is displbyed on the progress bbr.
     * Otherwise, the <code>progressString</code> is
     * rendered on the progress bbr.
     *
     * @see #setStringPbinted
     * @see #setString
     */
    protected boolebn pbintString;

    /**
     * The defbult minimum for b progress bbr is 0.
     */
    stbtic finbl privbte int defbultMinimum = 0;
    /**
     * The defbult mbximum for b progress bbr is 100.
     */
    stbtic finbl privbte int defbultMbximum = 100;
    /**
     * The defbult orientbtion for b progress bbr is <code>HORIZONTAL</code>.
     */
    stbtic finbl privbte int defbultOrientbtion = HORIZONTAL;

    /**
     * Only one <code>ChbngeEvent</code> is needed per instbnce since the
     * event's only interesting property is the immutbble source, which
     * is the progress bbr.
     * The event is lbzily crebted the first time thbt bn
     * event notificbtion is fired.
     *
     * @see #fireStbteChbnged
     */
    protected trbnsient ChbngeEvent chbngeEvent = null;

    /**
     * Listens for chbnge events sent by the progress bbr's model,
     * redispbtching them
     * to chbnge-event listeners registered upon
     * this progress bbr.
     *
     * @see #crebteChbngeListener
     */
    protected ChbngeListener chbngeListener = null;

    /**
     * Formbt used when displbying percent complete.
     */
    privbte trbnsient Formbt formbt;

    /**
     * Whether the progress bbr is indeterminbte (<code>true</code>) or
     * normbl (<code>fblse</code>); the defbult is <code>fblse</code>.
     *
     * @see #setIndeterminbte
     * @since 1.4
     */
    privbte boolebn indeterminbte;


   /**
     * Crebtes b horizontbl progress bbr
     * thbt displbys b border but no progress string.
     * The initibl bnd minimum vblues bre 0,
     * bnd the mbximum is 100.
     *
     * @see #setOrientbtion
     * @see #setBorderPbinted
     * @see #setStringPbinted
     * @see #setString
     * @see #setIndeterminbte
     */
    public JProgressBbr()
    {
        this(defbultOrientbtion);
    }

   /**
     * Crebtes b progress bbr with the specified orientbtion,
     * which cbn be
     * either {@code SwingConstbnts.VERTICAL} or
     * {@code SwingConstbnts.HORIZONTAL}.
     * By defbult, b border is pbinted but b progress string is not.
     * The initibl bnd minimum vblues bre 0,
     * bnd the mbximum is 100.
     *
     * @pbrbm orient  the desired orientbtion of the progress bbr
     * @throws IllegblArgumentException if {@code orient} is bn illegbl vblue
     *
     * @see #setOrientbtion
     * @see #setBorderPbinted
     * @see #setStringPbinted
     * @see #setString
     * @see #setIndeterminbte
     */
    public JProgressBbr(int orient)
    {
        this(orient, defbultMinimum, defbultMbximum);
    }


    /**
     * Crebtes b horizontbl progress bbr
     * with the specified minimum bnd mbximum.
     * Sets the initibl vblue of the progress bbr to the specified minimum.
     * By defbult, b border is pbinted but b progress string is not.
     * <p>
     * The <code>BoundedRbngeModel</code> thbt holds the progress bbr's dbtb
     * hbndles bny issues thbt mby brise from improperly setting the
     * minimum, initibl, bnd mbximum vblues on the progress bbr.
     * See the {@code BoundedRbngeModel} documentbtion for detbils.
     *
     * @pbrbm min  the minimum vblue of the progress bbr
     * @pbrbm mbx  the mbximum vblue of the progress bbr
     *
     * @see BoundedRbngeModel
     * @see #setOrientbtion
     * @see #setBorderPbinted
     * @see #setStringPbinted
     * @see #setString
     * @see #setIndeterminbte
     */
    public JProgressBbr(int min, int mbx)
    {
        this(defbultOrientbtion, min, mbx);
    }


    /**
     * Crebtes b progress bbr using the specified orientbtion,
     * minimum, bnd mbximum.
     * By defbult, b border is pbinted but b progress string is not.
     * Sets the initibl vblue of the progress bbr to the specified minimum.
     * <p>
     * The <code>BoundedRbngeModel</code> thbt holds the progress bbr's dbtb
     * hbndles bny issues thbt mby brise from improperly setting the
     * minimum, initibl, bnd mbximum vblues on the progress bbr.
     * See the {@code BoundedRbngeModel} documentbtion for detbils.
     *
     * @pbrbm orient  the desired orientbtion of the progress bbr
     * @pbrbm min  the minimum vblue of the progress bbr
     * @pbrbm mbx  the mbximum vblue of the progress bbr
     * @throws IllegblArgumentException if {@code orient} is bn illegbl vblue
     *
     * @see BoundedRbngeModel
     * @see #setOrientbtion
     * @see #setBorderPbinted
     * @see #setStringPbinted
     * @see #setString
     * @see #setIndeterminbte
     */
    public JProgressBbr(int orient, int min, int mbx)
    {
        // Crebting the model this wby is b bit simplistic, but
        //  I believe thbt it is the the most common usbge of this
        //  component - it's whbt people will expect.
        setModel(new DefbultBoundedRbngeModel(min, 0, min, mbx));
        updbteUI();

        setOrientbtion(orient);      // documented with set/getOrientbtion()
        setBorderPbinted(true);      // documented with is/setBorderPbinted()
        setStringPbinted(fblse);     // see setStringPbinted
        setString(null);             // see getString
        setIndeterminbte(fblse);     // see setIndeterminbte
    }


    /**
     * Crebtes b horizontbl progress bbr
     * thbt uses the specified model
     * to hold the progress bbr's dbtb.
     * By defbult, b border is pbinted but b progress string is not.
     *
     * @pbrbm newModel  the dbtb model for the progress bbr
     *
     * @see #setOrientbtion
     * @see #setBorderPbinted
     * @see #setStringPbinted
     * @see #setString
     * @see #setIndeterminbte
     */
    public JProgressBbr(BoundedRbngeModel newModel)
    {
        setModel(newModel);
        updbteUI();

        setOrientbtion(defbultOrientbtion);  // see setOrientbtion()
        setBorderPbinted(true);              // see setBorderPbinted()
        setStringPbinted(fblse);             // see setStringPbinted
        setString(null);                     // see getString
        setIndeterminbte(fblse);             // see setIndeterminbte
    }


    /**
     * Returns {@code SwingConstbnts.VERTICAL} or
     * {@code SwingConstbnts.HORIZONTAL}, depending on the orientbtion
     * of the progress bbr. The defbult orientbtion is
     * {@code SwingConstbnts.HORIZONTAL}.
     *
     * @return <code>HORIZONTAL</code> or <code>VERTICAL</code>
     * @see #setOrientbtion
     */
    public int getOrientbtion() {
        return orientbtion;
    }


   /**
     * Sets the progress bbr's orientbtion to <code>newOrientbtion</code>,
     * which must be {@code SwingConstbnts.VERTICAL} or
     * {@code SwingConstbnts.HORIZONTAL}. The defbult orientbtion
     * is {@code SwingConstbnts.HORIZONTAL}.
     *
     * @pbrbm  newOrientbtion  <code>HORIZONTAL</code> or <code>VERTICAL</code>
     * @exception      IllegblArgumentException    if <code>newOrientbtion</code>
     *                                              is bn illegbl vblue
     * @see #getOrientbtion
     *
     * @bebninfo
     *    preferred: true
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Set the progress bbr's orientbtion.
     */
    public void setOrientbtion(int newOrientbtion) {
        if (orientbtion != newOrientbtion) {
            switch (newOrientbtion) {
            cbse VERTICAL:
            cbse HORIZONTAL:
                int oldOrientbtion = orientbtion;
                orientbtion = newOrientbtion;
                firePropertyChbnge("orientbtion", oldOrientbtion, newOrientbtion);
                if (bccessibleContext != null) {
                    bccessibleContext.firePropertyChbnge(
                            AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                            ((oldOrientbtion == VERTICAL)
                             ? AccessibleStbte.VERTICAL
                             : AccessibleStbte.HORIZONTAL),
                            ((orientbtion == VERTICAL)
                             ? AccessibleStbte.VERTICAL
                             : AccessibleStbte.HORIZONTAL));
                }
                brebk;
            defbult:
                throw new IllegblArgumentException(newOrientbtion +
                                             " is not b legbl orientbtion");
            }
            revblidbte();
        }
    }


    /**
     * Returns the vblue of the <code>stringPbinted</code> property.
     *
     * @return the vblue of the <code>stringPbinted</code> property
     * @see    #setStringPbinted
     * @see    #setString
     */
    public boolebn isStringPbinted() {
        return pbintString;
    }


    /**
     * Sets the vblue of the <code>stringPbinted</code> property,
     * which determines whether the progress bbr
     * should render b progress string.
     * The defbult is <code>fblse</code>, mebning
     * no string is pbinted.
     * Some look bnd feels might not support progress strings
     * or might support them only when the progress bbr is in determinbte mode.
     *
     * @pbrbm   b       <code>true</code> if the progress bbr should render b string
     * @see     #isStringPbinted
     * @see     #setString
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Whether the progress bbr should render b string.
     */
    public void setStringPbinted(boolebn b) {
        //PENDING: specify thbt string not pbinted when in indeterminbte mode?
        //         or just lebve thbt to the L&F?
        boolebn oldVblue = pbintString;
        pbintString = b;
        firePropertyChbnge("stringPbinted", oldVblue, pbintString);
        if (pbintString != oldVblue) {
            revblidbte();
            repbint();
        }
    }


    /**
     * Returns b {@code String} representbtion of the current progress.
     * By defbult, this returns b simple percentbge {@code String} bbsed on
     * the vblue returned from {@code getPercentComplete}.  An exbmple
     * would be the "42%".  You cbn chbnge this by cblling {@code setString}.
     *
     * @return the vblue of the progress string, or b simple percentbge string
     *         if the progress string is {@code null}
     * @see    #setString
     */
    public String getString(){
        if (progressString != null) {
            return progressString;
        } else {
            if (formbt == null) {
                formbt = NumberFormbt.getPercentInstbnce();
            }
            return formbt.formbt(new Double(getPercentComplete()));
        }
    }

    /**
     * Sets the vblue of the progress string. By defbult,
     * this string is <code>null</code>, implying the built-in behbvior of
     * using b simple percent string.
     * If you hbve provided b custom progress string bnd wbnt to revert to
     * the built-in behbvior, set the string bbck to <code>null</code>.
     * <p>
     * The progress string is pbinted only if
     * the <code>isStringPbinted</code> method returns <code>true</code>.
     *
     * @pbrbm  s       the vblue of the progress string
     * @see    #getString
     * @see    #setStringPbinted
     * @see    #isStringPbinted
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Specifies the progress string to pbint
     */
    public void setString(String s){
        String oldVblue = progressString;
        progressString = s;
        firePropertyChbnge("string", oldVblue, progressString);
        if (progressString == null || oldVblue == null || !progressString.equbls(oldVblue)) {
            repbint();
        }
    }

    /**
     * Returns the percent complete for the progress bbr.
     * Note thbt this number is between 0.0 bnd 1.0.
     *
     * @return the percent complete for this progress bbr
     */
    public double getPercentComplete() {
        long spbn = model.getMbximum() - model.getMinimum();
        double currentVblue = model.getVblue();
        double pc = (currentVblue - model.getMinimum()) / spbn;
        return pc;
    }

    /**
     * Returns the <code>borderPbinted</code> property.
     *
     * @return the vblue of the <code>borderPbinted</code> property
     * @see    #setBorderPbinted
     * @bebninfo
     *  description: Does the progress bbr pbint its border
     */
    public boolebn isBorderPbinted() {
        return pbintBorder;
    }

    /**
     * Sets the <code>borderPbinted</code> property, which is
     * <code>true</code> if the progress bbr should pbint its border.
     * The defbult vblue for this property is <code>true</code>.
     * Some look bnd feels might not implement pbinted borders;
     * they will ignore this property.
     *
     * @pbrbm   b       <code>true</code> if the progress bbr
     *                  should pbint its border;
     *                  otherwise, <code>fblse</code>
     * @see     #isBorderPbinted
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Whether the progress bbr should pbint its border.
     */
    public void setBorderPbinted(boolebn b) {
        boolebn oldVblue = pbintBorder;
        pbintBorder = b;
        firePropertyChbnge("borderPbinted", oldVblue, pbintBorder);
        if (pbintBorder != oldVblue) {
            repbint();
        }
    }

    /**
     * Pbints the progress bbr's border if the <code>borderPbinted</code>
     * property is <code>true</code>.
     *
     * @pbrbm g  the <code>Grbphics</code> context within which to pbint the border
     * @see #pbint
     * @see #setBorder
     * @see #isBorderPbinted
     * @see #setBorderPbinted
     */
    protected void pbintBorder(Grbphics g) {
        if (isBorderPbinted()) {
            super.pbintBorder(g);
        }
    }


    /**
     * Returns the look-bnd-feel object thbt renders this component.
     *
     * @return the <code>ProgressBbrUI</code> object thbt renders this component
     */
    public ProgressBbrUI getUI() {
        return (ProgressBbrUI)ui;
    }

    /**
     * Sets the look-bnd-feel object thbt renders this component.
     *
     * @pbrbm ui  b <code>ProgressBbrUI</code> object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(ProgressBbrUI ui) {
        super.setUI(ui);
    }


    /**
     * Resets the UI property to b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((ProgressBbrUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns the nbme of the look-bnd-feel clbss thbt renders this component.
     *
     * @return the string "ProgressBbrUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     * @bebninfo
     *        expert: true
     *   description: A string thbt specifies the nbme of the look-bnd-feel clbss.
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /* We pbss ebch Chbnge event to the listeners with the
     * the progress bbr bs the event source.
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
    privbte clbss ModelListener implements ChbngeListener, Seriblizbble {
        public void stbteChbnged(ChbngeEvent e) {
            fireStbteChbnged();
        }
    }

    /**
     * Subclbsses thbt wbnt to hbndle chbnge events
     * from the model differently
     * cbn override this to return
     * bn instbnce of b custom <code>ChbngeListener</code> implementbtion.
     * The defbult {@code ChbngeListener} simply cblls the
     * {@code fireStbteChbnged} method to forwbrd {@code ChbngeEvent}s
     * to the {@code ChbngeListener}s thbt hbve been bdded directly to the
     * progress bbr.
     *
     * @return the instbnce of b custom {@code ChbngeListener} implementbtion.
     * @see #chbngeListener
     * @see #fireStbteChbnged
     * @see jbvbx.swing.event.ChbngeListener
     * @see jbvbx.swing.BoundedRbngeModel
     */
    protected ChbngeListener crebteChbngeListener() {
        return new ModelListener();
    }

    /**
     * Adds the specified <code>ChbngeListener</code> to the progress bbr.
     *
     * @pbrbm l the <code>ChbngeListener</code> to bdd
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }

    /**
     * Removes b <code>ChbngeListener</code> from the progress bbr.
     *
     * @pbrbm l the <code>ChbngeListener</code> to remove
     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>ChbngeListener</code>s bdded
     * to this progress bbr with <code>bddChbngeListener</code>.
     *
     * @return bll of the <code>ChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }

    /**
     * Send b {@code ChbngeEvent}, whose source is this {@code JProgressBbr}, to
     * bll {@code ChbngeListener}s thbt hbve registered interest in
     * {@code ChbngeEvent}s.
     * This method is cblled ebch time b {@code ChbngeEvent} is received from
     * the model.
     * <p>
     *
     * The event instbnce is crebted if necessbry, bnd stored in
     * {@code chbngeEvent}.
     *
     * @see #bddChbngeListener
     * @see EventListenerList
     */
    protected void fireStbteChbnged() {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ChbngeListener.clbss) {
                // Lbzily crebte the event:
                if (chbngeEvent == null)
                    chbngeEvent = new ChbngeEvent(this);
                ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
            }
        }
    }

    /**
     * Returns the dbtb model used by this progress bbr.
     *
     * @return the <code>BoundedRbngeModel</code> currently in use
     * @see #setModel
     * @see    BoundedRbngeModel
     */
    public BoundedRbngeModel getModel() {
        return model;
    }

    /**
     * Sets the dbtb model used by the <code>JProgressBbr</code>.
     * Note thbt the {@code BoundedRbngeModel}'s {@code extent} is not used,
     * bnd is set to {@code 0}.
     *
     * @pbrbm  newModel the <code>BoundedRbngeModel</code> to use
     *
     * @bebninfo
     *    expert: true
     * description: The dbtb model used by the JProgressBbr.
     */
    public void setModel(BoundedRbngeModel newModel) {
        // PENDING(???) setting the sbme model to multiple bbrs is broken; listeners
        BoundedRbngeModel oldModel = getModel();

        if (newModel != oldModel) {
            if (oldModel != null) {
                oldModel.removeChbngeListener(chbngeListener);
                chbngeListener = null;
            }

            model = newModel;

            if (newModel != null) {
                chbngeListener = crebteChbngeListener();
                newModel.bddChbngeListener(chbngeListener);
            }

            if (bccessibleContext != null) {
                bccessibleContext.firePropertyChbnge(
                        AccessibleContext.ACCESSIBLE_VALUE_PROPERTY,
                        (oldModel== null
                         ? null : Integer.vblueOf(oldModel.getVblue())),
                        (newModel== null
                         ? null : Integer.vblueOf(newModel.getVblue())));
            }

            if (model != null) {
                model.setExtent(0);
            }
            repbint();
        }
    }


    /* All of the model methods bre implemented by delegbtion. */

    /**
     * Returns the progress bbr's current {@code vblue}
     * from the <code>BoundedRbngeModel</code>.
     * The vblue is blwbys between the
     * minimum bnd mbximum vblues, inclusive.
     *
     * @return  the current vblue of the progress bbr
     * @see     #setVblue
     * @see     BoundedRbngeModel#getVblue
     */
    public int getVblue() { return getModel().getVblue(); }

    /**
     * Returns the progress bbr's {@code minimum} vblue
     * from the <code>BoundedRbngeModel</code>.
     *
     * @return  the progress bbr's minimum vblue
     * @see     #setMinimum
     * @see     BoundedRbngeModel#getMinimum
     */
    public int getMinimum() { return getModel().getMinimum(); }

    /**
     * Returns the progress bbr's {@code mbximum} vblue
     * from the <code>BoundedRbngeModel</code>.
     *
     * @return  the progress bbr's mbximum vblue
     * @see     #setMbximum
     * @see     BoundedRbngeModel#getMbximum
     */
    public int getMbximum() { return getModel().getMbximum(); }

    /**
     * Sets the progress bbr's current vblue to {@code n}.  This method
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
     *    preferred: true
     *  description: The progress bbr's current vblue.
     */
    public void setVblue(int n) {
        BoundedRbngeModel brm = getModel();
        int oldVblue = brm.getVblue();
        brm.setVblue(n);

        if (bccessibleContext != null) {
            bccessibleContext.firePropertyChbnge(
                    AccessibleContext.ACCESSIBLE_VALUE_PROPERTY,
                    Integer.vblueOf(oldVblue),
                    Integer.vblueOf(brm.getVblue()));
        }
    }

    /**
     * Sets the progress bbr's minimum vblue
     * (stored in the progress bbr's dbtb model) to <code>n</code>.
     * <p>
     * The dbtb model (b <code>BoundedRbngeModel</code> instbnce)
     * hbndles bny mbthembticbl
     * issues brising from bssigning fbulty vblues.
     * See the {@code BoundedRbngeModel} documentbtion for detbils.
     * <p>
     * If the minimum vblue is different from the previous minimum,
     * bll chbnge listeners bre notified.
     *
     * @pbrbm  n       the new minimum
     * @see    #getMinimum
     * @see    #bddChbngeListener
     * @see    BoundedRbngeModel#setMinimum
     * @bebninfo
     *  preferred: true
     * description: The progress bbr's minimum vblue.
     */
    public void setMinimum(int n) { getModel().setMinimum(n); }

    /**
     * Sets the progress bbr's mbximum vblue
     * (stored in the progress bbr's dbtb model) to <code>n</code>.
     * <p>
     * The underlying <code>BoundedRbngeModel</code> hbndles bny mbthembticbl
     * issues brising from bssigning fbulty vblues.
     * See the {@code BoundedRbngeModel} documentbtion for detbils.
     * <p>
     * If the mbximum vblue is different from the previous mbximum,
     * bll chbnge listeners bre notified.
     *
     * @pbrbm  n       the new mbximum
     * @see    #getMbximum
     * @see    #bddChbngeListener
     * @see    BoundedRbngeModel#setMbximum
     * @bebninfo
     *    preferred: true
     *  description: The progress bbr's mbximum vblue.
     */
    public void setMbximum(int n) { getModel().setMbximum(n); }

    /**
     * Sets the <code>indeterminbte</code> property of the progress bbr,
     * which determines whether the progress bbr is in determinbte
     * or indeterminbte mode.
     * An indeterminbte progress bbr continuously displbys bnimbtion
     * indicbting thbt bn operbtion of unknown length is occurring.
     * By defbult, this property is <code>fblse</code>.
     * Some look bnd feels might not support indeterminbte progress bbrs;
     * they will ignore this property.
     *
     * <p>
     *
     * See
     * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/progress.html" tbrget="_top">How to Monitor Progress</b>
     * for exbmples of using indeterminbte progress bbrs.
     *
     * @pbrbm newVblue  <code>true</code> if the progress bbr
     *                  should chbnge to indeterminbte mode;
     *                  <code>fblse</code> if it should revert to normbl.
     *
     * @see #isIndeterminbte
     * @see jbvbx.swing.plbf.bbsic.BbsicProgressBbrUI
     *
     * @since 1.4
     *
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Set whether the progress bbr is indeterminbte (true)
     *               or normbl (fblse).
     */
    public void setIndeterminbte(boolebn newVblue) {
        boolebn oldVblue = indeterminbte;
        indeterminbte = newVblue;
        firePropertyChbnge("indeterminbte", oldVblue, indeterminbte);
    }

    /**
     * Returns the vblue of the <code>indeterminbte</code> property.
     *
     * @return the vblue of the <code>indeterminbte</code> property
     * @see    #setIndeterminbte
     *
     * @since 1.4
     *
     * @bebninfo
     *  description: Is the progress bbr indeterminbte (true)
     *               or normbl (fblse)?
     */
    public boolebn isIndeterminbte() {
        return indeterminbte;
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
     * Returns b string representbtion of this <code>JProgressBbr</code>.
     * This method is intended to be used only for debugging purposes. The
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JProgressBbr</code>
     */
    protected String pbrbmString() {
        String orientbtionString = (orientbtion == HORIZONTAL ?
                                    "HORIZONTAL" : "VERTICAL");
        String pbintBorderString = (pbintBorder ?
                                    "true" : "fblse");
        String progressStringString = (progressString != null ?
                                       progressString : "");
        String pbintStringString = (pbintString ?
                                    "true" : "fblse");
        String indeterminbteString = (indeterminbte ?
                                    "true" : "fblse");

        return super.pbrbmString() +
        ",orientbtion=" + orientbtionString +
        ",pbintBorder=" + pbintBorderString +
        ",pbintString=" + pbintStringString +
        ",progressString=" + progressStringString +
        ",indeterminbteString=" + indeterminbteString;
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the <code>AccessibleContext</code> bssocibted with this
     * <code>JProgressBbr</code>. For progress bbrs, the
     * <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleJProgressBbr</code>.
     * A new <code>AccessibleJProgressBbr</code> instbnce is crebted if necessbry.
     *
     * @return bn <code>AccessibleJProgressBbr</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this <code>JProgressBbr</code>
     * @bebninfo
     *       expert: true
     *  description: The AccessibleContext bssocibted with this ProgressBbr.
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJProgressBbr();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JProgressBbr</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to progress bbr user-interfbce
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
    protected clbss AccessibleJProgressBbr extends AccessibleJComponent
        implements AccessibleVblue {

        /**
         * Gets the stbte set of this object.
         *
         * @return bn instbnce of AccessibleStbte contbining the current stbte
         * of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            if (getModel().getVblueIsAdjusting()) {
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
         * Gets the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PROGRESS_BAR;
        }

        /**
         * Gets the <code>AccessibleVblue</code> bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * returns this object, which is responsible for implementing the
         * <code>AccessibleVblue</code> interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleVblue getAccessibleVblue() {
            return this;
        }

        /**
         * Gets the bccessible vblue of this object.
         *
         * @return the current vblue of this object
         */
        public Number getCurrentAccessibleVblue() {
            return Integer.vblueOf(getVblue());
        }

        /**
         * Sets the vblue of this object bs b <code>Number</code>.
         *
         * @return <code>true</code> if the vblue wbs set
         */
        public boolebn setCurrentAccessibleVblue(Number n) {
            // TIGER- 4422535
            if (n == null) {
                return fblse;
            }
            setVblue(n.intVblue());
            return true;
        }

        /**
         * Gets the minimum bccessible vblue of this object.
         *
         * @return the minimum vblue of this object
         */
        public Number getMinimumAccessibleVblue() {
            return Integer.vblueOf(getMinimum());
        }

        /**
         * Gets the mbximum bccessible vblue of this object.
         *
         * @return the mbximum vblue of this object
         */
        public Number getMbximumAccessibleVblue() {
            // TIGER - 4422362
            return Integer.vblueOf(model.getMbximum() - model.getExtent());
        }

    } // AccessibleJProgressBbr
}
