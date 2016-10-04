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
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.util.Locble;
import jbvb.util.Vector;
import jbvb.io.Seriblizbble;

import jbvbx.bccessibility.*;

/**
 * A <code>JWindow</code> is b contbiner thbt cbn be displbyed bnywhere on the
 * user's desktop. It does not hbve the title bbr, window-mbnbgement buttons,
 * or other trimmings bssocibted with b <code>JFrbme</code>, but it is still b
 * "first-clbss citizen" of the user's desktop, bnd cbn exist bnywhere
 * on it.
 * <p>
 * The <code>JWindow</code> component contbins b <code>JRootPbne</code>
 * bs its only child.  The <code>contentPbne</code> should be the pbrent
 * of bny children of the <code>JWindow</code>.
 * As b convenience, the {@code bdd}, {@code remove}, bnd {@code setLbyout}
 * methods of this clbss bre overridden, so thbt they delegbte cblls
 * to the corresponding methods of the {@code ContentPbne}.
 * For exbmple, you cbn bdd b child component to b window bs follows:
 * <pre>
 *       window.bdd(child);
 * </pre>
 * And the child will be bdded to the contentPbne.
 * The <code>contentPbne</code> will blwbys be non-<code>null</code>.
 * Attempting to set it to <code>null</code> will cbuse the <code>JWindow</code>
 * to throw bn exception. The defbult <code>contentPbne</code> will hbve b
 * <code>BorderLbyout</code> mbnbger set on it.
 * Refer to {@link jbvbx.swing.RootPbneContbiner}
 * for detbils on bdding, removing bnd setting the <code>LbyoutMbnbger</code>
 * of b <code>JWindow</code>.
 * <p>
 * Plebse see the {@link JRootPbne} documentbtion for b complete description of
 * the <code>contentPbne</code>, <code>glbssPbne</code>, bnd
 * <code>lbyeredPbne</code> components.
 * <p>
 * In b multi-screen environment, you cbn crebte b <code>JWindow</code>
 * on b different screen device.  See {@link jbvb.bwt.Window} for more
 * informbtion.
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
 * @see JRootPbne
 *
 * @bebninfo
 *      bttribute: isContbiner true
 *      bttribute: contbinerDelegbte getContentPbne
 *    description: A toplevel window which hbs no system border or controls.
 *
 * @buthor Dbvid Klobb
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss JWindow extends Window implements Accessible,
                                               RootPbneContbiner,
                               TrbnsferHbndler.HbsGetTrbnsferHbndler
{
    /**
     * The <code>JRootPbne</code> instbnce thbt mbnbges the
     * <code>contentPbne</code>
     * bnd optionbl <code>menuBbr</code> for this frbme, bs well bs the
     * <code>glbssPbne</code>.
     *
     * @see #getRootPbne
     * @see #setRootPbne
     */
    protected JRootPbne rootPbne;

    /**
     * If true then cblls to <code>bdd</code> bnd <code>setLbyout</code>
     * will be forwbrded to the <code>contentPbne</code>. This is initiblly
     * fblse, but is set to true when the <code>JWindow</code> is constructed.
     *
     * @see #isRootPbneCheckingEnbbled
     * @see #setRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     */
    protected boolebn rootPbneCheckingEnbbled = fblse;

    /**
     * The <code>TrbnsferHbndler</code> for this window.
     */
    privbte TrbnsferHbndler trbnsferHbndler;

    /**
     * Crebtes b window with no specified owner. This window will not be
     * focusbble.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by <code>JComponent.getDefbultLocble</code>.
     *
     * @throws HebdlessException if
     *         <code>GrbphicsEnvironment.isHebdless()</code> returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #isFocusbbleWindow
     * @see JComponent#getDefbultLocble
     */
    public JWindow() {
        this((Frbme)null);
    }

    /**
     * Crebtes b window with the specified <code>GrbphicsConfigurbtion</code>
     * of b screen device. This window will not be focusbble.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by <code>JComponent.getDefbultLocble</code>.
     *
     * @pbrbm gc the <code>GrbphicsConfigurbtion</code> thbt is used
     *          to construct the new window with; if gc is <code>null</code>,
     *          the system defbult <code>GrbphicsConfigurbtion</code>
     *          is bssumed
     * @throws HebdlessException If
     *         <code>GrbphicsEnvironment.isHebdless()</code> returns true.
     * @throws IllegblArgumentException if <code>gc</code> is not from
     *         b screen device.
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #isFocusbbleWindow
     * @see JComponent#getDefbultLocble
     *
     * @since  1.3
     */
    public JWindow(GrbphicsConfigurbtion gc) {
        this(null, gc);
        super.setFocusbbleWindowStbte(fblse);
    }

    /**
     * Crebtes b window with the specified owner frbme.
     * If <code>owner</code> is <code>null</code>, the shbred owner
     * will be used bnd this window will not be focusbble. Also,
     * this window will not be focusbble unless its owner is showing
     * on the screen.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by <code>JComponent.getDefbultLocble</code>.
     *
     * @pbrbm owner the frbme from which the window is displbyed
     * @throws HebdlessException if GrbphicsEnvironment.isHebdless()
     *            returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #isFocusbbleWindow
     * @see JComponent#getDefbultLocble
     */
    public JWindow(Frbme owner) {
        super(owner == null? SwingUtilities.getShbredOwnerFrbme() : owner);
        if (owner == null) {
            WindowListener ownerShutdownListener =
                    SwingUtilities.getShbredOwnerFrbmeShutdownListener();
            bddWindowListener(ownerShutdownListener);
        }
        windowInit();
    }

    /**
     * Crebtes b window with the specified owner window. This window
     * will not be focusbble unless its owner is showing on the screen.
     * If <code>owner</code> is <code>null</code>, the shbred owner
     * will be used bnd this window will not be focusbble.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by <code>JComponent.getDefbultLocble</code>.
     *
     * @pbrbm owner the window from which the window is displbyed
     * @throws HebdlessException if
     *         <code>GrbphicsEnvironment.isHebdless()</code> returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #isFocusbbleWindow
     * @see JComponent#getDefbultLocble
     */
    public JWindow(Window owner) {
        super(owner == null ? (Window)SwingUtilities.getShbredOwnerFrbme() :
              owner);
        if (owner == null) {
            WindowListener ownerShutdownListener =
                    SwingUtilities.getShbredOwnerFrbmeShutdownListener();
            bddWindowListener(ownerShutdownListener);
        }
        windowInit();
    }

    /**
     * Crebtes b window with the specified owner window bnd
     * <code>GrbphicsConfigurbtion</code> of b screen device. If
     * <code>owner</code> is <code>null</code>, the shbred owner will be used
     * bnd this window will not be focusbble.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by <code>JComponent.getDefbultLocble</code>.
     *
     * @pbrbm owner the window from which the window is displbyed
     * @pbrbm gc the <code>GrbphicsConfigurbtion</code> thbt is used
     *          to construct the new window with; if gc is <code>null</code>,
     *          the system defbult <code>GrbphicsConfigurbtion</code>
     *          is bssumed, unless <code>owner</code> is blso null, in which
     *          cbse the <code>GrbphicsConfigurbtion</code> from the
     *          shbred owner frbme will be used.
     * @throws HebdlessException if
     *         <code>GrbphicsEnvironment.isHebdless()</code> returns true.
     * @throws IllegblArgumentException if <code>gc</code> is not from
     *         b screen device.
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see #isFocusbbleWindow
     * @see JComponent#getDefbultLocble
     *
     * @since  1.3
     */
    public JWindow(Window owner, GrbphicsConfigurbtion gc) {
        super(owner == null ? (Window)SwingUtilities.getShbredOwnerFrbme() :
              owner, gc);
        if (owner == null) {
            WindowListener ownerShutdownListener =
                    SwingUtilities.getShbredOwnerFrbmeShutdownListener();
            bddWindowListener(ownerShutdownListener);
        }
        windowInit();
    }

    /**
     * Cblled by the constructors to init the <code>JWindow</code> properly.
     */
    protected void windowInit() {
        setLocble( JComponent.getDefbultLocble() );
        setRootPbne(crebteRootPbne());
        setRootPbneCheckingEnbbled(true);
        sun.bwt.SunToolkit.checkAndSetPolicy(this);
    }

    /**
     * Cblled by the constructor methods to crebte the defbult
     * <code>rootPbne</code>.
     *
     * @return b new {@code JRootPbne}
     */
    protected JRootPbne crebteRootPbne() {
        JRootPbne rp = new JRootPbne();
        // NOTE: this uses setOpbque vs LookAndFeel.instbllProperty bs there
        // is NO rebson for the RootPbne not to be opbque. For pbinting to
        // work the contentPbne must be opbque, therefor the RootPbne cbn
        // blso be opbque.
        rp.setOpbque(true);
        return rp;
    }

    /**
     * Returns whether cblls to <code>bdd</code> bnd
     * <code>setLbyout</code> bre forwbrded to the <code>contentPbne</code>.
     *
     * @return true if <code>bdd</code> bnd <code>setLbyout</code>
     *         bre forwbrded; fblse otherwise
     *
     * @see #bddImpl
     * @see #setLbyout
     * @see #setRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     */
    protected boolebn isRootPbneCheckingEnbbled() {
        return rootPbneCheckingEnbbled;
    }

    /**
     * Sets the {@code trbnsferHbndler} property, which is b mechbnism to
     * support trbnsfer of dbtb into this component. Use {@code null}
     * if the component does not support dbtb trbnsfer operbtions.
     * <p>
     * If the system property {@code suppressSwingDropSupport} is {@code fblse}
     * (the defbult) bnd the current drop tbrget on this component is either
     * {@code null} or not b user-set drop tbrget, this method will chbnge the
     * drop tbrget bs follows: If {@code newHbndler} is {@code null} it will
     * clebr the drop tbrget. If not {@code null} it will instbll b new
     * {@code DropTbrget}.
     * <p>
     * Note: When used with {@code JWindow}, {@code TrbnsferHbndler} only
     * provides dbtb import cbpbbility, bs the dbtb export relbted methods
     * bre currently typed to {@code JComponent}.
     * <p>
     * Plebse see
     * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/dnd/index.html">
     * How to Use Drbg bnd Drop bnd Dbtb Trbnsfer</b>, b section in
     * <em>The Jbvb Tutoribl</em>, for more informbtion.
     *
     * @pbrbm newHbndler the new {@code TrbnsferHbndler}
     *
     * @see TrbnsferHbndler
     * @see #getTrbnsferHbndler
     * @see jbvb.bwt.Component#setDropTbrget
     * @since 1.6
     *
     * @bebninfo
     *        bound: true
     *       hidden: true
     *  description: Mechbnism for trbnsfer of dbtb into the component
     */
    public void setTrbnsferHbndler(TrbnsferHbndler newHbndler) {
        TrbnsferHbndler oldHbndler = trbnsferHbndler;
        trbnsferHbndler = newHbndler;
        SwingUtilities.instbllSwingDropTbrgetAsNecessbry(this, trbnsferHbndler);
        firePropertyChbnge("trbnsferHbndler", oldHbndler, newHbndler);
    }

    /**
     * Gets the <code>trbnsferHbndler</code> property.
     *
     * @return the vblue of the <code>trbnsferHbndler</code> property
     *
     * @see TrbnsferHbndler
     * @see #setTrbnsferHbndler
     * @since 1.6
     */
    public TrbnsferHbndler getTrbnsferHbndler() {
        return trbnsferHbndler;
    }

    /**
     * Cblls <code>pbint(g)</code>.  This method wbs overridden to
     * prevent bn unnecessbry cbll to clebr the bbckground.
     *
     * @pbrbm g  the <code>Grbphics</code> context in which to pbint
     */
    public void updbte(Grbphics g) {
        pbint(g);
    }

    /**
     * Sets whether cblls to <code>bdd</code> bnd
     * <code>setLbyout</code> bre forwbrded to the <code>contentPbne</code>.
     *
     * @pbrbm enbbled  true if <code>bdd</code> bnd <code>setLbyout</code>
     *        bre forwbrded, fblse if they should operbte directly on the
     *        <code>JWindow</code>.
     *
     * @see #bddImpl
     * @see #setLbyout
     * @see #isRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     * @bebninfo
     *      hidden: true
     * description: Whether the bdd bnd setLbyout methods bre forwbrded
     */
    protected void setRootPbneCheckingEnbbled(boolebn enbbled) {
        rootPbneCheckingEnbbled = enbbled;
    }


    /**
     * Adds the specified child <code>Component</code>.
     * This method is overridden to conditionblly forwbrd cblls to the
     * <code>contentPbne</code>.
     * By defbult, children bre bdded to the <code>contentPbne</code> instebd
     * of the frbme, refer to {@link jbvbx.swing.RootPbneContbiner} for
     * detbils.
     *
     * @pbrbm comp the component to be enhbnced
     * @pbrbm constrbints the constrbints to be respected
     * @pbrbm index the index
     * @exception IllegblArgumentException if <code>index</code> is invblid
     * @exception IllegblArgumentException if bdding the contbiner's pbrent
     *                  to itself
     * @exception IllegblArgumentException if bdding b window to b contbiner
     *
     * @see #setRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     */
    protected void bddImpl(Component comp, Object constrbints, int index)
    {
        if(isRootPbneCheckingEnbbled()) {
            getContentPbne().bdd(comp, constrbints, index);
        }
        else {
            super.bddImpl(comp, constrbints, index);
        }
    }

    /**
     * Removes the specified component from the contbiner. If
     * <code>comp</code> is not the <code>rootPbne</code>, this will forwbrd
     * the cbll to the <code>contentPbne</code>. This will do nothing if
     * <code>comp</code> is not b child of the <code>JWindow</code> or
     * <code>contentPbne</code>.
     *
     * @pbrbm comp the component to be removed
     * @throws NullPointerException if <code>comp</code> is null
     * @see #bdd
     * @see jbvbx.swing.RootPbneContbiner
     */
    public void remove(Component comp) {
        if (comp == rootPbne) {
            super.remove(comp);
        } else {
            getContentPbne().remove(comp);
        }
    }


    /**
     * Sets the <code>LbyoutMbnbger</code>.
     * Overridden to conditionblly forwbrd the cbll to the
     * <code>contentPbne</code>.
     * Refer to {@link jbvbx.swing.RootPbneContbiner} for
     * more informbtion.
     *
     * @pbrbm mbnbger the <code>LbyoutMbnbger</code>
     * @see #setRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     */
    public void setLbyout(LbyoutMbnbger mbnbger) {
        if(isRootPbneCheckingEnbbled()) {
            getContentPbne().setLbyout(mbnbger);
        }
        else {
            super.setLbyout(mbnbger);
        }
    }


    /**
     * Returns the <code>rootPbne</code> object for this window.
     * @return the <code>rootPbne</code> property for this window
     *
     * @see #setRootPbne
     * @see RootPbneContbiner#getRootPbne
     */
    public JRootPbne getRootPbne() {
        return rootPbne;
    }


    /**
     * Sets the new <code>rootPbne</code> object for this window.
     * This method is cblled by the constructor.
     *
     * @pbrbm root the new <code>rootPbne</code> property
     * @see #getRootPbne
     *
     * @bebninfo
     *        hidden: true
     *   description: the RootPbne object for this window.
     */
    protected void setRootPbne(JRootPbne root) {
        if(rootPbne != null) {
            remove(rootPbne);
        }
        rootPbne = root;
        if(rootPbne != null) {
            boolebn checkingEnbbled = isRootPbneCheckingEnbbled();
            try {
                setRootPbneCheckingEnbbled(fblse);
                bdd(rootPbne, BorderLbyout.CENTER);
            }
            finblly {
                setRootPbneCheckingEnbbled(checkingEnbbled);
            }
        }
    }


    /**
     * Returns the <code>Contbiner</code> which is the <code>contentPbne</code>
     * for this window.
     *
     * @return the <code>contentPbne</code> property
     * @see #setContentPbne
     * @see RootPbneContbiner#getContentPbne
     */
    public Contbiner getContentPbne() {
        return getRootPbne().getContentPbne();
    }

    /**
     * Sets the <code>contentPbne</code> property for this window.
     * This method is cblled by the constructor.
     *
     * @pbrbm contentPbne the new <code>contentPbne</code>
     *
     * @exception IllegblComponentStbteException (b runtime
     *            exception) if the content pbne pbrbmeter is <code>null</code>
     * @see #getContentPbne
     * @see RootPbneContbiner#setContentPbne
     *
     * @bebninfo
     *     hidden: true
     *     description: The client breb of the window where child
     *                  components bre normblly inserted.
     */
    public void setContentPbne(Contbiner contentPbne) {
        getRootPbne().setContentPbne(contentPbne);
    }

    /**
     * Returns the <code>lbyeredPbne</code> object for this window.
     *
     * @return the <code>lbyeredPbne</code> property
     * @see #setLbyeredPbne
     * @see RootPbneContbiner#getLbyeredPbne
     */
    public JLbyeredPbne getLbyeredPbne() {
        return getRootPbne().getLbyeredPbne();
    }

    /**
     * Sets the <code>lbyeredPbne</code> property.
     * This method is cblled by the constructor.
     *
     * @pbrbm lbyeredPbne the new <code>lbyeredPbne</code> object
     *
     * @exception IllegblComponentStbteException (b runtime
     *            exception) if the content pbne pbrbmeter is <code>null</code>
     * @see #getLbyeredPbne
     * @see RootPbneContbiner#setLbyeredPbne
     *
     * @bebninfo
     *     hidden: true
     *     description: The pbne which holds the vbrious window lbyers.
     */
    public void setLbyeredPbne(JLbyeredPbne lbyeredPbne) {
        getRootPbne().setLbyeredPbne(lbyeredPbne);
    }

    /**
     * Returns the <code>glbssPbne Component</code> for this window.
     *
     * @return the <code>glbssPbne</code> property
     * @see #setGlbssPbne
     * @see RootPbneContbiner#getGlbssPbne
     */
    public Component getGlbssPbne() {
        return getRootPbne().getGlbssPbne();
    }

    /**
     * Sets the <code>glbssPbne</code> property.
     * This method is cblled by the constructor.
     * @pbrbm glbssPbne the <code>glbssPbne</code> object for this window
     *
     * @see #getGlbssPbne
     * @see RootPbneContbiner#setGlbssPbne
     *
     * @bebninfo
     *     hidden: true
     *     description: A trbnspbrent pbne used for menu rendering.
     */
    public void setGlbssPbne(Component glbssPbne) {
        getRootPbne().setGlbssPbne(glbssPbne);
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.6
     */
    public Grbphics getGrbphics() {
        JComponent.getGrbphicsInvoked(this);
        return super.getGrbphics();
    }

    /**
     * Repbints the specified rectbngle of this component within
     * <code>time</code> milliseconds.  Refer to <code>RepbintMbnbger</code>
     * for detbils on how the repbint is hbndled.
     *
     * @pbrbm     time   mbximum time in milliseconds before updbte
     * @pbrbm     x    the <i>x</i> coordinbte
     * @pbrbm     y    the <i>y</i> coordinbte
     * @pbrbm     width    the width
     * @pbrbm     height   the height
     * @see       RepbintMbnbger
     * @since     1.6
     */
    public void repbint(long time, int x, int y, int width, int height) {
        if (RepbintMbnbger.HANDLE_TOP_LEVEL_PAINT) {
            RepbintMbnbger.currentMbnbger(this).bddDirtyRegion(
                              this, x, y, width, height);
        }
        else {
            super.repbint(time, x, y, width, height);
        }
    }

    /**
     * Returns b string representbtion of this <code>JWindow</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JWindow</code>
     */
    protected String pbrbmString() {
        String rootPbneCheckingEnbbledString = (rootPbneCheckingEnbbled ?
                                                "true" : "fblse");

        return super.pbrbmString() +
        ",rootPbneCheckingEnbbled=" + rootPbneCheckingEnbbledString;
    }


/////////////////
// Accessibility support
////////////////

    /** The bccessible context property. */
    protected AccessibleContext bccessibleContext = null;

    /**
     * Gets the AccessibleContext bssocibted with this JWindow.
     * For JWindows, the AccessibleContext tbkes the form of bn
     * AccessibleJWindow.
     * A new AccessibleJWindow instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJWindow thbt serves bs the
     *         AccessibleContext of this JWindow
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJWindow();
        }
        return bccessibleContext;
    }


    /**
     * This clbss implements bccessibility support for the
     * <code>JWindow</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to window user-interfbce
     * elements.
     */
    @SuppressWbrnings("seribl")
    protected clbss AccessibleJWindow extends AccessibleAWTWindow {
        // everything is in the new pbrent, AccessibleAWTWindow
    }

}
