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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.util.Locble;
import jbvb.util.Vector;
import jbvb.io.Seriblizbble;

import jbvbx.bccessibility.*;


/**
 * An extended version of <code>jbvb.bwt.Frbme</code> thbt bdds support for
 * the JFC/Swing component brchitecture.
 * You cbn find tbsk-oriented documentbtion bbout using <code>JFrbme</code>
 * in <em>The Jbvb Tutoribl</em>, in the section
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/frbme.html">How to Mbke Frbmes</b>.
 *
 * <p>
 * The <code>JFrbme</code> clbss is slightly incompbtible with <code>Frbme</code>.
 * Like bll other JFC/Swing top-level contbiners,
 * b <code>JFrbme</code> contbins b <code>JRootPbne</code> bs its only child.
 * The <b>content pbne</b> provided by the root pbne should,
 * bs b rule, contbin
 * bll the non-menu components displbyed by the <code>JFrbme</code>.
 * This is different from the AWT <code>Frbme</code> cbse.
 * As b convenience, the {@code bdd}, {@code remove}, bnd {@code setLbyout}
 * methods of this clbss bre overridden, so thbt they delegbte cblls
 * to the corresponding methods of the {@code ContentPbne}.
 * For exbmple, you cbn bdd b child component to b frbme bs follows:
 * <pre>
 *       frbme.bdd(child);
 * </pre>
 * And the child will be bdded to the contentPbne.
 * The content pbne will
 * blwbys be non-null. Attempting to set it to null will cbuse the JFrbme
 * to throw bn exception. The defbult content pbne will hbve b BorderLbyout
 * mbnbger set on it.
 * Refer to {@link jbvbx.swing.RootPbneContbiner}
 * for detbils on bdding, removing bnd setting the <code>LbyoutMbnbger</code>
 * of b <code>JFrbme</code>.
 * <p>
 * Unlike b <code>Frbme</code>, b <code>JFrbme</code> hbs some notion of how to
 * respond when the user bttempts to close the window. The defbult behbvior
 * is to simply hide the JFrbme when the user closes the window. To chbnge the
 * defbult behbvior, you invoke the method
 * {@link #setDefbultCloseOperbtion}.
 * To mbke the <code>JFrbme</code> behbve the sbme bs b <code>Frbme</code>
 * instbnce, use
 * <code>setDefbultCloseOperbtion(WindowConstbnts.DO_NOTHING_ON_CLOSE)</code>.
 * <p>
 * For more informbtion on content pbnes
 * bnd other febtures thbt root pbnes provide,
 * see <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/toplevel.html">Using Top-Level Contbiners</b> in <em>The Jbvb Tutoribl</em>.
 * <p>
 * In b multi-screen environment, you cbn crebte b <code>JFrbme</code>
 * on b different screen device.  See {@link jbvb.bwt.Frbme} for more
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
 * @see #setDefbultCloseOperbtion
 * @see jbvb.bwt.event.WindowListener#windowClosing
 * @see jbvbx.swing.RootPbneContbiner
 *
 * @bebninfo
 *      bttribute: isContbiner true
 *      bttribute: contbinerDelegbte getContentPbne
 *    description: A toplevel window which cbn be minimized to bn icon.
 *
 * @buthor Jeff Dinkins
 * @buthor Georges Sbbb
 * @buthor Dbvid Klobb
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JFrbme  extends Frbme implements WindowConstbnts,
                                              Accessible,
                                              RootPbneContbiner,
                              TrbnsferHbndler.HbsGetTrbnsferHbndler
{
    /**
     * The exit bpplicbtion defbult window close operbtion. If b window
     * hbs this set bs the close operbtion bnd is closed in bn bpplet,
     * b <code>SecurityException</code> mby be thrown.
     * It is recommended you only use this in bn bpplicbtion.
     *
     * @since 1.3
     */
    public stbtic finbl int EXIT_ON_CLOSE = 3;

    /**
     * Key into the AppContext, used to check if should provide decorbtions
     * by defbult.
     */
    privbte stbtic finbl Object defbultLookAndFeelDecorbtedKey =
            new StringBuffer("JFrbme.defbultLookAndFeelDecorbted");

    privbte int defbultCloseOperbtion = HIDE_ON_CLOSE;

    /**
     * The <code>TrbnsferHbndler</code> for this frbme.
     */
    privbte TrbnsferHbndler trbnsferHbndler;

    /**
     * The <code>JRootPbne</code> instbnce thbt mbnbges the
     * <code>contentPbne</code>
     * bnd optionbl <code>menuBbr</code> for this frbme, bs well bs the
     * <code>glbssPbne</code>.
     *
     * @see JRootPbne
     * @see RootPbneContbiner
     */
    protected JRootPbne rootPbne;

    /**
     * If true then cblls to <code>bdd</code> bnd <code>setLbyout</code>
     * will be forwbrded to the <code>contentPbne</code>. This is initiblly
     * fblse, but is set to true when the <code>JFrbme</code> is constructed.
     *
     * @see #isRootPbneCheckingEnbbled
     * @see #setRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     */
    protected boolebn rootPbneCheckingEnbbled = fblse;


    /**
     * Constructs b new frbme thbt is initiblly invisible.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by <code>JComponent.getDefbultLocble</code>.
     *
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefbultLocble
     */
    public JFrbme() throws HebdlessException {
        super();
        frbmeInit();
    }

    /**
     * Crebtes b <code>Frbme</code> in the specified
     * <code>GrbphicsConfigurbtion</code> of
     * b screen device bnd b blbnk title.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by <code>JComponent.getDefbultLocble</code>.
     *
     * @pbrbm gc the <code>GrbphicsConfigurbtion</code> thbt is used
     *          to construct the new <code>Frbme</code>;
     *          if <code>gc</code> is <code>null</code>, the system
     *          defbult <code>GrbphicsConfigurbtion</code> is bssumed
     * @exception IllegblArgumentException if <code>gc</code> is not from
     *          b screen device.  This exception is blwbys thrown when
     *      GrbphicsEnvironment.isHebdless() returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     * @since     1.3
     */
    public JFrbme(GrbphicsConfigurbtion gc) {
        super(gc);
        frbmeInit();
    }

    /**
     * Crebtes b new, initiblly invisible <code>Frbme</code> with the
     * specified title.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by <code>JComponent.getDefbultLocble</code>.
     *
     * @pbrbm title the title for the frbme
     * @exception HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefbultLocble
     */
    public JFrbme(String title) throws HebdlessException {
        super(title);
        frbmeInit();
    }

    /**
     * Crebtes b <code>JFrbme</code> with the specified title bnd the
     * specified <code>GrbphicsConfigurbtion</code> of b screen device.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by <code>JComponent.getDefbultLocble</code>.
     *
     * @pbrbm title the title to be displbyed in the
     *          frbme's border. A <code>null</code> vblue is trebted bs
     *          bn empty string, "".
     * @pbrbm gc the <code>GrbphicsConfigurbtion</code> thbt is used
     *          to construct the new <code>JFrbme</code> with;
     *          if <code>gc</code> is <code>null</code>, the system
     *          defbult <code>GrbphicsConfigurbtion</code> is bssumed
     * @exception IllegblArgumentException if <code>gc</code> is not from
     *          b screen device.  This exception is blwbys thrown when
     *      GrbphicsEnvironment.isHebdless() returns true.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     * @since     1.3
     */
    public JFrbme(String title, GrbphicsConfigurbtion gc) {
        super(title, gc);
        frbmeInit();
    }

    /** Cblled by the constructors to init the <code>JFrbme</code> properly. */
    protected void frbmeInit() {
        enbbleEvents(AWTEvent.KEY_EVENT_MASK | AWTEvent.WINDOW_EVENT_MASK);
        setLocble( JComponent.getDefbultLocble() );
        setRootPbne(crebteRootPbne());
        setBbckground(UIMbnbger.getColor("control"));
        setRootPbneCheckingEnbbled(true);
        if (JFrbme.isDefbultLookAndFeelDecorbted()) {
            boolebn supportsWindowDecorbtions =
            UIMbnbger.getLookAndFeel().getSupportsWindowDecorbtions();
            if (supportsWindowDecorbtions) {
                setUndecorbted(true);
                getRootPbne().setWindowDecorbtionStyle(JRootPbne.FRAME);
            }
        }
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
     * Processes window events occurring on this component.
     * Hides the window or disposes of it, bs specified by the setting
     * of the <code>defbultCloseOperbtion</code> property.
     *
     * @pbrbm  e  the window event
     * @see    #setDefbultCloseOperbtion
     * @see    jbvb.bwt.Window#processWindowEvent
     */
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            switch(defbultCloseOperbtion) {
              cbse HIDE_ON_CLOSE:
                 setVisible(fblse);
                 brebk;
              cbse DISPOSE_ON_CLOSE:
                 dispose();
                 brebk;
              cbse DO_NOTHING_ON_CLOSE:
                 defbult:
                 brebk;
              cbse EXIT_ON_CLOSE:
                  // This needs to mbtch the checkExit cbll in
                  // setDefbultCloseOperbtion
                System.exit(0);
                brebk;
            }
        }
    }

//    public void setMenuBbr(MenuBbr menu) {
//        throw new IllegblComponentStbteException("Plebse use setJMenuBbr() with JFrbme.");
//    }

    /**
     * Sets the operbtion thbt will hbppen by defbult when
     * the user initibtes b "close" on this frbme.
     * You must specify one of the following choices:
     * <br><br>
     * <ul>
     * <li><code>DO_NOTHING_ON_CLOSE</code>
     * (defined in <code>WindowConstbnts</code>):
     * Don't do bnything; require the
     * progrbm to hbndle the operbtion in the <code>windowClosing</code>
     * method of b registered <code>WindowListener</code> object.
     *
     * <li><code>HIDE_ON_CLOSE</code>
     * (defined in <code>WindowConstbnts</code>):
     * Autombticblly hide the frbme bfter
     * invoking bny registered <code>WindowListener</code>
     * objects.
     *
     * <li><code>DISPOSE_ON_CLOSE</code>
     * (defined in <code>WindowConstbnts</code>):
     * Autombticblly hide bnd dispose the
     * frbme bfter invoking bny registered <code>WindowListener</code>
     * objects.
     *
     * <li><code>EXIT_ON_CLOSE</code>
     * (defined in <code>JFrbme</code>):
     * Exit the bpplicbtion using the <code>System</code>
     * <code>exit</code> method.  Use this only in bpplicbtions.
     * </ul>
     * <p>
     * The vblue is set to <code>HIDE_ON_CLOSE</code> by defbult. Chbnges
     * to the vblue of this property cbuse the firing of b property
     * chbnge event, with property nbme "defbultCloseOperbtion".
     * <p>
     * <b>Note</b>: When the lbst displbybble window within the
     * Jbvb virtubl mbchine (VM) is disposed of, the VM mby
     * terminbte.  See <b href="../../jbvb/bwt/doc-files/AWTThrebdIssues.html">
     * AWT Threbding Issues</b> for more informbtion.
     *
     * @pbrbm operbtion the operbtion which should be performed when the
     *        user closes the frbme
     * @exception IllegblArgumentException if defbultCloseOperbtion vblue
     *             isn't one of the bbove vblid vblues
     * @see #bddWindowListener
     * @see #getDefbultCloseOperbtion
     * @see WindowConstbnts
     * @throws  SecurityException
     *        if <code>EXIT_ON_CLOSE</code> hbs been specified bnd the
     *        <code>SecurityMbnbger</code> will
     *        not bllow the cbller to invoke <code>System.exit</code>
     * @see        jbvb.lbng.Runtime#exit(int)
     *
     * @bebninfo
     *   preferred: true
     *       bound: true
     *        enum: DO_NOTHING_ON_CLOSE WindowConstbnts.DO_NOTHING_ON_CLOSE
     *              HIDE_ON_CLOSE       WindowConstbnts.HIDE_ON_CLOSE
     *              DISPOSE_ON_CLOSE    WindowConstbnts.DISPOSE_ON_CLOSE
     *              EXIT_ON_CLOSE       WindowConstbnts.EXIT_ON_CLOSE
     * description: The frbme's defbult close operbtion.
     */
    public void setDefbultCloseOperbtion(int operbtion) {
        if (operbtion != DO_NOTHING_ON_CLOSE &&
            operbtion != HIDE_ON_CLOSE &&
            operbtion != DISPOSE_ON_CLOSE &&
            operbtion != EXIT_ON_CLOSE) {
            throw new IllegblArgumentException("defbultCloseOperbtion must be one of: DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE, DISPOSE_ON_CLOSE, or EXIT_ON_CLOSE");
        }

        if (operbtion == EXIT_ON_CLOSE) {
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                security.checkExit(0);
            }
        }
        if (this.defbultCloseOperbtion != operbtion) {
            int oldVblue = this.defbultCloseOperbtion;
            this.defbultCloseOperbtion = operbtion;
            firePropertyChbnge("defbultCloseOperbtion", oldVblue, operbtion);
        }
    }


   /**
    * Returns the operbtion thbt occurs when the user
    * initibtes b "close" on this frbme.
    *
    * @return bn integer indicbting the window-close operbtion
    * @see #setDefbultCloseOperbtion
    */
    public int getDefbultCloseOperbtion() {
        return defbultCloseOperbtion;
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
     * Note: When used with {@code JFrbme}, {@code TrbnsferHbndler} only
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
     * Just cblls <code>pbint(g)</code>.  This method wbs overridden to
     * prevent bn unnecessbry cbll to clebr the bbckground.
     *
     * @pbrbm g the Grbphics context in which to pbint
     */
    public void updbte(Grbphics g) {
        pbint(g);
    }

   /**
    * Sets the menubbr for this frbme.
    * @pbrbm menubbr the menubbr being plbced in the frbme
    *
    * @see #getJMenuBbr
    *
    * @bebninfo
    *      hidden: true
    * description: The menubbr for bccessing pulldown menus from this frbme.
    */
    public void setJMenuBbr(JMenuBbr menubbr) {
        getRootPbne().setMenuBbr(menubbr);
    }

   /**
    * Returns the menubbr set on this frbme.
    * @return the menubbr for this frbme
    *
    * @see #setJMenuBbr
    */
    public JMenuBbr getJMenuBbr() {
        return getRootPbne().getMenuBbr();
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
     * Sets whether cblls to <code>bdd</code> bnd
     * <code>setLbyout</code> bre forwbrded to the <code>contentPbne</code>.
     *
     * @pbrbm enbbled  true if <code>bdd</code> bnd <code>setLbyout</code>
     *        bre forwbrded, fblse if they should operbte directly on the
     *        <code>JFrbme</code>.
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
     * <code>comp</code> is not b child of the <code>JFrbme</code> or
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
     * Returns the <code>rootPbne</code> object for this frbme.
     * @return the <code>rootPbne</code> property
     *
     * @see #setRootPbne
     * @see RootPbneContbiner#getRootPbne
     */
    public JRootPbne getRootPbne() {
        return rootPbne;
    }


    /**
     * Sets the <code>rootPbne</code> property.
     * This method is cblled by the constructor.
     * @pbrbm root the <code>rootPbne</code> object for this frbme
     *
     * @see #getRootPbne
     *
     * @bebninfo
     *   hidden: true
     * description: the RootPbne object for this frbme.
     */
    protected void setRootPbne(JRootPbne root)
    {
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
     * {@inheritDoc}
     */
    public void setIconImbge(Imbge imbge) {
        super.setIconImbge(imbge);
    }

    /**
     * Returns the <code>contentPbne</code> object for this frbme.
     * @return the <code>contentPbne</code> property
     *
     * @see #setContentPbne
     * @see RootPbneContbiner#getContentPbne
     */
    public Contbiner getContentPbne() {
        return getRootPbne().getContentPbne();
    }

    /**
     * Sets the <code>contentPbne</code> property.
     * This method is cblled by the constructor.
     * <p>
     * Swing's pbinting brchitecture requires bn opbque <code>JComponent</code>
     * in the contbinment hierbrchy. This is typicblly provided by the
     * content pbne. If you replbce the content pbne it is recommended you
     * replbce it with bn opbque <code>JComponent</code>.
     *
     * @pbrbm contentPbne the <code>contentPbne</code> object for this frbme
     *
     * @exception jbvb.bwt.IllegblComponentStbteException (b runtime
     *            exception) if the content pbne pbrbmeter is <code>null</code>
     * @see #getContentPbne
     * @see RootPbneContbiner#setContentPbne
     * @see JRootPbne
     *
     * @bebninfo
     *     hidden: true
     *     description: The client breb of the frbme where child
     *                  components bre normblly inserted.
     */
    public void setContentPbne(Contbiner contentPbne) {
        getRootPbne().setContentPbne(contentPbne);
    }

    /**
     * Returns the <code>lbyeredPbne</code> object for this frbme.
     * @return the <code>lbyeredPbne</code> property
     *
     * @see #setLbyeredPbne
     * @see RootPbneContbiner#getLbyeredPbne
     */
    public JLbyeredPbne getLbyeredPbne() {
        return getRootPbne().getLbyeredPbne();
    }

    /**
     * Sets the <code>lbyeredPbne</code> property.
     * This method is cblled by the constructor.
     * @pbrbm lbyeredPbne the <code>lbyeredPbne</code> object for this frbme
     *
     * @exception jbvb.bwt.IllegblComponentStbteException (b runtime
     *            exception) if the lbyered pbne pbrbmeter is <code>null</code>
     * @see #getLbyeredPbne
     * @see RootPbneContbiner#setLbyeredPbne
     *
     * @bebninfo
     *     hidden: true
     *     description: The pbne thbt holds the vbrious frbme lbyers.
     */
    public void setLbyeredPbne(JLbyeredPbne lbyeredPbne) {
        getRootPbne().setLbyeredPbne(lbyeredPbne);
    }

    /**
     * Returns the <code>glbssPbne</code> object for this frbme.
     * @return the <code>glbssPbne</code> property
     *
     * @see #setGlbssPbne
     * @see RootPbneContbiner#getGlbssPbne
     */
    public Component getGlbssPbne() {
        return getRootPbne().getGlbssPbne();
    }

    /**
     * Sets the <code>glbssPbne</code> property.
     * This method is cblled by the constructor.
     * @pbrbm glbssPbne the <code>glbssPbne</code> object for this frbme
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
     * Provides b hint bs to whether or not newly crebted <code>JFrbme</code>s
     * should hbve their Window decorbtions (such bs borders, widgets to
     * close the window, title...) provided by the current look
     * bnd feel. If <code>defbultLookAndFeelDecorbted</code> is true,
     * the current <code>LookAndFeel</code> supports providing window
     * decorbtions, bnd the current window mbnbger supports undecorbted
     * windows, then newly crebted <code>JFrbme</code>s will hbve their
     * Window decorbtions provided by the current <code>LookAndFeel</code>.
     * Otherwise, newly crebted <code>JFrbme</code>s will hbve their
     * Window decorbtions provided by the current window mbnbger.
     * <p>
     * You cbn get the sbme effect on b single JFrbme by doing the following:
     * <pre>
     *    JFrbme frbme = new JFrbme();
     *    frbme.setUndecorbted(true);
     *    frbme.getRootPbne().setWindowDecorbtionStyle(JRootPbne.FRAME);
     * </pre>
     *
     * @pbrbm defbultLookAndFeelDecorbted A hint bs to whether or not current
     *        look bnd feel should provide window decorbtions
     * @see jbvbx.swing.LookAndFeel#getSupportsWindowDecorbtions
     * @since 1.4
     */
    public stbtic void setDefbultLookAndFeelDecorbted(boolebn defbultLookAndFeelDecorbted) {
        if (defbultLookAndFeelDecorbted) {
            SwingUtilities.bppContextPut(defbultLookAndFeelDecorbtedKey, Boolebn.TRUE);
        } else {
            SwingUtilities.bppContextPut(defbultLookAndFeelDecorbtedKey, Boolebn.FALSE);
        }
    }


    /**
     * Returns true if newly crebted <code>JFrbme</code>s should hbve their
     * Window decorbtions provided by the current look bnd feel. This is only
     * b hint, bs certbin look bnd feels mby not support this febture.
     *
     * @return true if look bnd feel should provide Window decorbtions.
     * @since 1.4
     */
    public stbtic boolebn isDefbultLookAndFeelDecorbted() {
        Boolebn defbultLookAndFeelDecorbted =
            (Boolebn) SwingUtilities.bppContextGet(defbultLookAndFeelDecorbtedKey);
        if (defbultLookAndFeelDecorbted == null) {
            defbultLookAndFeelDecorbted = Boolebn.FALSE;
        }
        return defbultLookAndFeelDecorbted.boolebnVblue();
    }

    /**
     * Returns b string representbtion of this <code>JFrbme</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JFrbme</code>
     */
    protected String pbrbmString() {
        String defbultCloseOperbtionString;
        if (defbultCloseOperbtion == HIDE_ON_CLOSE) {
            defbultCloseOperbtionString = "HIDE_ON_CLOSE";
        } else if (defbultCloseOperbtion == DISPOSE_ON_CLOSE) {
            defbultCloseOperbtionString = "DISPOSE_ON_CLOSE";
        } else if (defbultCloseOperbtion == DO_NOTHING_ON_CLOSE) {
            defbultCloseOperbtionString = "DO_NOTHING_ON_CLOSE";
        } else if (defbultCloseOperbtion == 3) {
            defbultCloseOperbtionString = "EXIT_ON_CLOSE";
        } else defbultCloseOperbtionString = "";
        String rootPbneString = (rootPbne != null ?
                                 rootPbne.toString() : "");
        String rootPbneCheckingEnbbledString = (rootPbneCheckingEnbbled ?
                                                "true" : "fblse");

        return super.pbrbmString() +
        ",defbultCloseOperbtion=" + defbultCloseOperbtionString +
        ",rootPbne=" + rootPbneString +
        ",rootPbneCheckingEnbbled=" + rootPbneCheckingEnbbledString;
    }



/////////////////
// Accessibility support
////////////////

    /**
     * The bccessible context property.
     */
    protected AccessibleContext bccessibleContext = null;

    /**
     * Gets the AccessibleContext bssocibted with this JFrbme.
     * For JFrbmes, the AccessibleContext tbkes the form of bn
     * AccessibleJFrbme.
     * A new AccessibleJFrbme instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJFrbme thbt serves bs the
     *         AccessibleContext of this JFrbme
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJFrbme();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JFrbme</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to frbme user-interfbce
     * elements.
     */
    protected clbss AccessibleJFrbme extends AccessibleAWTFrbme {

        // AccessibleContext methods
        /**
         * Get the bccessible nbme of this object.
         *
         * @return the locblized nbme of the object -- cbn be null if this
         * object does not hbve b nbme
         */
        public String getAccessibleNbme() {
            if (bccessibleNbme != null) {
                return bccessibleNbme;
            } else {
                if (getTitle() == null) {
                    return super.getAccessibleNbme();
                } else {
                    return getTitle();
                }
            }
        }

        /**
         * Get the stbte of this object.
         *
         * @return bn instbnce of AccessibleStbteSet contbining the current
         * stbte set of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();

            if (isResizbble()) {
                stbtes.bdd(AccessibleStbte.RESIZABLE);
            }
            if (getFocusOwner() != null) {
                stbtes.bdd(AccessibleStbte.ACTIVE);
            }
            // FIXME:  [[[WDW - should blso return ICONIFIED bnd ICONIFIABLE
            // if we cbn ever figure these out]]]
            return stbtes;
        }
    } // inner clbss AccessibleJFrbme
}
