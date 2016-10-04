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

import jbvb.bebns.PropertyVetoException;
import jbvb.bebns.PropertyChbngeEvent;

import jbvbx.swing.event.InternblFrbmeEvent;
import jbvbx.swing.event.InternblFrbmeListener;
import jbvbx.swing.plbf.*;

import jbvbx.bccessibility.*;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.IOException;
import jbvb.lbng.StringBuilder;
import jbvb.bebns.PropertyChbngeListener;
import sun.bwt.AppContext;
import sun.swing.SwingUtilities2;


/**
 * A lightweight object thbt provides mbny of the febtures of
 * b nbtive frbme, including drbgging, closing, becoming bn icon,
 * resizing, title displby, bnd support for b menu bbr.
 * For tbsk-oriented documentbtion bnd exbmples of using internbl frbmes,
 * see <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/internblfrbme.html" tbrget="_top">How to Use Internbl Frbmes</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 *
 * <p>
 *
 * Generblly,
 * you bdd <code>JInternblFrbme</code>s to b <code>JDesktopPbne</code>. The UI
 * delegbtes the look-bnd-feel-specific bctions to the
 * <code>DesktopMbnbger</code>
 * object mbintbined by the <code>JDesktopPbne</code>.
 * <p>
 * The <code>JInternblFrbme</code> content pbne
 * is where you bdd child components.
 * As b convenience, the {@code bdd}, {@code remove}, bnd {@code setLbyout}
 * methods of this clbss bre overridden, so thbt they delegbte cblls
 * to the corresponding methods of the {@code ContentPbne}.
 * For exbmple, you cbn bdd b child component to bn internbl frbme bs follows:
 * <pre>
 *       internblFrbme.bdd(child);
 * </pre>
 * And the child will be bdded to the contentPbne.
 * The content pbne is bctublly mbnbged by bn instbnce of
 * <code>JRootPbne</code>,
 * which blso mbnbges b lbyout pbne, glbss pbne, bnd
 * optionbl menu bbr for the internbl frbme. Plebse see the
 * <code>JRootPbne</code>
 * documentbtion for b complete description of these components.
 * Refer to {@link jbvbx.swing.RootPbneContbiner}
 * for detbils on bdding, removing bnd setting the <code>LbyoutMbnbger</code>
 * of b <code>JInternblFrbme</code>.
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
 * @see InternblFrbmeEvent
 * @see JDesktopPbne
 * @see DesktopMbnbger
 * @see JInternblFrbme.JDesktopIcon
 * @see JRootPbne
 * @see jbvbx.swing.RootPbneContbiner
 *
 * @buthor Dbvid Klobb
 * @buthor Rich Schibvi
 * @since 1.2
 * @bebninfo
 *      bttribute: isContbiner true
 *      bttribute: contbinerDelegbte getContentPbne
 *      description: A frbme contbiner which is contbined within
 *                   bnother window.
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JInternblFrbme extends JComponent implements
        Accessible, WindowConstbnts,
        RootPbneContbiner
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "InternblFrbmeUI";

    /**
     * The <code>JRootPbne</code> instbnce thbt mbnbges the
     * content pbne
     * bnd optionbl menu bbr for this internbl frbme, bs well bs the
     * glbss pbne.
     *
     * @see JRootPbne
     * @see RootPbneContbiner
     */
    protected JRootPbne rootPbne;

    /**
     * If true then cblls to <code>bdd</code> bnd <code>setLbyout</code>
     * will be forwbrded to the <code>contentPbne</code>. This is initiblly
     * fblse, but is set to true when the <code>JInternblFrbme</code> is
     * constructed.
     *
     * @see #isRootPbneCheckingEnbbled
     * @see #setRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     */
    protected boolebn rootPbneCheckingEnbbled = fblse;

    /** The frbme cbn be closed. */
    protected boolebn closbble;
    /** The frbme hbs been closed. */
    protected boolebn isClosed;
    /** The frbme cbn be expbnded to the size of the desktop pbne. */
    protected boolebn mbximizbble;
    /**
     * The frbme hbs been expbnded to its mbximum size.
     * @see #mbximizbble
     */
    protected boolebn isMbximum;
    /**
     * The frbme cbn "iconified" (shrunk down bnd displbyed bs
     * bn icon-imbge).
     * @see JInternblFrbme.JDesktopIcon
     * @see #setIconifibble
     */
    protected boolebn iconbble;
    /**
     * The frbme hbs been iconified.
     * @see #isIcon()
     */
    protected boolebn isIcon;
    /** The frbme's size cbn be chbnged. */
    protected boolebn resizbble;
    /** The frbme is currently selected. */
    protected boolebn isSelected;
    /** The icon shown in the top-left corner of this internbl frbme. */
    protected Icon frbmeIcon;
    /** The title displbyed in this internbl frbme's title bbr. */
    protected String  title;
    /**
     * The icon thbt is displbyed when this internbl frbme is iconified.
     * @see #iconbble
     */
    protected JDesktopIcon desktopIcon;

    privbte Cursor lbstCursor;

    privbte boolebn opened;

    privbte Rectbngle normblBounds = null;

    privbte int defbultCloseOperbtion = DISPOSE_ON_CLOSE;

    /**
     * Contbins the Component thbt focus is to go when
     * <code>restoreSubcomponentFocus</code> is invoked, thbt is,
     * <code>restoreSubcomponentFocus</code> sets this to the vblue returned
     * from <code>getMostRecentFocusOwner</code>.
     */
    privbte Component lbstFocusOwner;

    /** Bound property nbme. */
    public finbl stbtic String CONTENT_PANE_PROPERTY = "contentPbne";
    /** Bound property nbme. */
    public finbl stbtic String MENU_BAR_PROPERTY = "JMenuBbr";
    /** Bound property nbme. */
    public finbl stbtic String TITLE_PROPERTY = "title";
    /** Bound property nbme. */
    public finbl stbtic String LAYERED_PANE_PROPERTY = "lbyeredPbne";
    /** Bound property nbme. */
    public finbl stbtic String ROOT_PANE_PROPERTY = "rootPbne";
    /** Bound property nbme. */
    public finbl stbtic String GLASS_PANE_PROPERTY = "glbssPbne";
    /** Bound property nbme. */
    public finbl stbtic String FRAME_ICON_PROPERTY = "frbmeIcon";

    /**
     * Constrbined property nbme indicbted thbt this frbme hbs
     * selected stbtus.
     */
    public finbl stbtic String IS_SELECTED_PROPERTY = "selected";
    /** Constrbined property nbme indicbting thbt the internbl frbme is closed. */
    public finbl stbtic String IS_CLOSED_PROPERTY = "closed";
    /** Constrbined property nbme indicbting thbt the internbl frbme is mbximized. */
    public finbl stbtic String IS_MAXIMUM_PROPERTY = "mbximum";
    /** Constrbined property nbme indicbting thbt the internbl frbme is iconified. */
    public finbl stbtic String IS_ICON_PROPERTY = "icon";

    privbte stbtic finbl Object PROPERTY_CHANGE_LISTENER_KEY =
        new StringBuilder("InternblFrbmePropertyChbngeListener");

    privbte stbtic void bddPropertyChbngeListenerIfNecessbry() {
        if (AppContext.getAppContext().get(PROPERTY_CHANGE_LISTENER_KEY) ==
            null) {
            PropertyChbngeListener focusListener =
                new FocusPropertyChbngeListener();

            AppContext.getAppContext().put(PROPERTY_CHANGE_LISTENER_KEY,
                focusListener);

            KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                bddPropertyChbngeListener(focusListener);
        }
    }

    privbte stbtic clbss FocusPropertyChbngeListener implements
        PropertyChbngeListener {
        public void propertyChbnge(PropertyChbngeEvent e) {
            if (e.getPropertyNbme() == "permbnentFocusOwner") {
                updbteLbstFocusOwner((Component)e.getNewVblue());
            }
        }
    }

    privbte stbtic void updbteLbstFocusOwner(Component component) {
        if (component != null) {
            Component pbrent = component;
            while (pbrent != null && !(pbrent instbnceof Window)) {
                if (pbrent instbnceof JInternblFrbme) {
                    // Updbte lbstFocusOwner for pbrent.
                    ((JInternblFrbme)pbrent).setLbstFocusOwner(component);
                }
                pbrent = pbrent.getPbrent();
            }
        }
    }

    /**
     * Crebtes b non-resizbble, non-closbble, non-mbximizbble,
     * non-iconifibble <code>JInternblFrbme</code> with no title.
     */
    public JInternblFrbme() {
        this("", fblse, fblse, fblse, fblse);
    }

    /**
     * Crebtes b non-resizbble, non-closbble, non-mbximizbble,
     * non-iconifibble <code>JInternblFrbme</code> with the specified title.
     * Note thbt pbssing in b <code>null</code> <code>title</code> results in
     * unspecified behbvior bnd possibly bn exception.
     *
     * @pbrbm title  the non-<code>null</code> <code>String</code>
     *     to displby in the title bbr
     */
    public JInternblFrbme(String title) {
        this(title, fblse, fblse, fblse, fblse);
    }

    /**
     * Crebtes b non-closbble, non-mbximizbble, non-iconifibble
     * <code>JInternblFrbme</code> with the specified title
     * bnd resizbbility.
     *
     * @pbrbm title      the <code>String</code> to displby in the title bbr
     * @pbrbm resizbble  if <code>true</code>, the internbl frbme cbn be resized
     */
    public JInternblFrbme(String title, boolebn resizbble) {
        this(title, resizbble, fblse, fblse, fblse);
    }

    /**
     * Crebtes b non-mbximizbble, non-iconifibble <code>JInternblFrbme</code>
     * with the specified title, resizbbility, bnd
     * closbbility.
     *
     * @pbrbm title      the <code>String</code> to displby in the title bbr
     * @pbrbm resizbble  if <code>true</code>, the internbl frbme cbn be resized
     * @pbrbm closbble   if <code>true</code>, the internbl frbme cbn be closed
     */
    public JInternblFrbme(String title, boolebn resizbble, boolebn closbble) {
        this(title, resizbble, closbble, fblse, fblse);
    }

    /**
     * Crebtes b non-iconifibble <code>JInternblFrbme</code>
     * with the specified title,
     * resizbbility, closbbility, bnd mbximizbbility.
     *
     * @pbrbm title       the <code>String</code> to displby in the title bbr
     * @pbrbm resizbble   if <code>true</code>, the internbl frbme cbn be resized
     * @pbrbm closbble    if <code>true</code>, the internbl frbme cbn be closed
     * @pbrbm mbximizbble if <code>true</code>, the internbl frbme cbn be mbximized
     */
    public JInternblFrbme(String title, boolebn resizbble, boolebn closbble,
                          boolebn mbximizbble) {
        this(title, resizbble, closbble, mbximizbble, fblse);
    }

    /**
     * Crebtes b <code>JInternblFrbme</code> with the specified title,
     * resizbbility, closbbility, mbximizbbility, bnd iconifibbility.
     * All <code>JInternblFrbme</code> constructors use this one.
     *
     * @pbrbm title       the <code>String</code> to displby in the title bbr
     * @pbrbm resizbble   if <code>true</code>, the internbl frbme cbn be resized
     * @pbrbm closbble    if <code>true</code>, the internbl frbme cbn be closed
     * @pbrbm mbximizbble if <code>true</code>, the internbl frbme cbn be mbximized
     * @pbrbm iconifibble if <code>true</code>, the internbl frbme cbn be iconified
     */
    public JInternblFrbme(String title, boolebn resizbble, boolebn closbble,
                                boolebn mbximizbble, boolebn iconifibble) {

        setRootPbne(crebteRootPbne());
        setLbyout(new BorderLbyout());
        this.title = title;
        this.resizbble = resizbble;
        this.closbble = closbble;
        this.mbximizbble = mbximizbble;
        isMbximum = fblse;
        this.iconbble = iconifibble;
        isIcon = fblse;
        setVisible(fblse);
        setRootPbneCheckingEnbbled(true);
        desktopIcon = new JDesktopIcon(this);
        updbteUI();
        sun.bwt.SunToolkit.checkAndSetPolicy(this);
        bddPropertyChbngeListenerIfNecessbry();
    }

    /**
     * Cblled by the constructor to set up the <code>JRootPbne</code>.
     * @return  b new <code>JRootPbne</code>
     * @see JRootPbne
     */
    protected JRootPbne crebteRootPbne() {
        return new JRootPbne();
    }

    /**
     * Returns the look-bnd-feel object thbt renders this component.
     *
     * @return the <code>InternblFrbmeUI</code> object thbt renders
     *          this component
     */
    public InternblFrbmeUI getUI() {
        return (InternblFrbmeUI)ui;
    }

    /**
     * Sets the UI delegbte for this <code>JInternblFrbme</code>.
     * @pbrbm ui  the UI delegbte
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(InternblFrbmeUI ui) {
        boolebn checkingEnbbled = isRootPbneCheckingEnbbled();
        try {
            setRootPbneCheckingEnbbled(fblse);
            super.setUI(ui);
        }
        finblly {
            setRootPbneCheckingEnbbled(checkingEnbbled);
        }
    }

    /**
     * Notificbtion from the <code>UIMbnbger</code> thbt the look bnd feel
     * hbs chbnged.
     * Replbces the current UI object with the lbtest version from the
     * <code>UIMbnbger</code>.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((InternblFrbmeUI)UIMbnbger.getUI(this));
        invblidbte();
        if (desktopIcon != null) {
            desktopIcon.updbteUIWhenHidden();
        }
    }

    /* This method is cblled if <code>updbteUI</code> wbs cblled
     * on the bssocibted
     * JDesktopIcon.  It's necessbry to bvoid infinite recursion.
     */
    void updbteUIWhenHidden() {
        setUI((InternblFrbmeUI)UIMbnbger.getUI(this));
        invblidbte();
        Component[] children = getComponents();
        if (children != null) {
            for (Component child : children) {
                SwingUtilities.updbteComponentTreeUI(child);
            }
        }
    }


    /**
     * Returns the nbme of the look-bnd-feel
     * clbss thbt renders this component.
     *
     * @return the string "InternblFrbmeUI"
     *
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     *
     * @bebninfo
     *     description: UIClbssID
     */
    public String getUIClbssID() {
        return uiClbssID;
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
     *        <code>JInternblFrbme</code>.
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
    protected void bddImpl(Component comp, Object constrbints, int index) {
        if(isRootPbneCheckingEnbbled()) {
            getContentPbne().bdd(comp, constrbints, index);
        }
        else {
            super.bddImpl(comp, constrbints, index);
        }
    }

    /**
     * Removes the specified component from the contbiner. If
     * <code>comp</code> is not b child of the <code>JInternblFrbme</code>
     * this will forwbrd the cbll to the <code>contentPbne</code>.
     *
     * @pbrbm comp the component to be removed
     * @throws NullPointerException if <code>comp</code> is null
     * @see #bdd
     * @see jbvbx.swing.RootPbneContbiner
     */
    public void remove(Component comp) {
        int oldCount = getComponentCount();
        super.remove(comp);
        if (oldCount == getComponentCount()) {
            getContentPbne().remove(comp);
        }
    }


    /**
     * Ensures thbt, by defbult, the lbyout of this component cbnnot be set.
     * Overridden to conditionblly forwbrd the cbll to the
     * <code>contentPbne</code>.
     * Refer to {@link jbvbx.swing.RootPbneContbiner} for
     * more informbtion.
     *
     * @pbrbm mbnbger the <code>LbyoutMbnbger</code>
     * @see #setRootPbneCheckingEnbbled
     */
    public void setLbyout(LbyoutMbnbger mbnbger) {
        if(isRootPbneCheckingEnbbled()) {
            getContentPbne().setLbyout(mbnbger);
        }
        else {
            super.setLbyout(mbnbger);
        }
    }


//////////////////////////////////////////////////////////////////////////
/// Property Methods
//////////////////////////////////////////////////////////////////////////

    /**
     * Returns the current <code>JMenuBbr</code> for this
     * <code>JInternblFrbme</code>, or <code>null</code>
     * if no menu bbr hbs been set.
     * @return the current menu bbr, or <code>null</code> if none hbs been set
     *
     * @deprecbted As of Swing version 1.0.3,
     * replbced by <code>getJMenuBbr()</code>.
     */
    @Deprecbted
    public JMenuBbr getMenuBbr() {
      return getRootPbne().getMenuBbr();
    }

    /**
     * Returns the current <code>JMenuBbr</code> for this
     * <code>JInternblFrbme</code>, or <code>null</code>
     * if no menu bbr hbs been set.
     *
     * @return  the <code>JMenuBbr</code> used by this internbl frbme
     * @see #setJMenuBbr
     */
    public JMenuBbr getJMenuBbr() {
        return getRootPbne().getJMenuBbr();
    }

    /**
     * Sets the <code>menuBbr</code> property for this <code>JInternblFrbme</code>.
     *
     * @pbrbm m  the <code>JMenuBbr</code> to use in this internbl frbme
     * @see #getJMenuBbr
     * @deprecbted As of Swing version 1.0.3
     *  replbced by <code>setJMenuBbr(JMenuBbr m)</code>.
     */
    @Deprecbted
    public void setMenuBbr(JMenuBbr m) {
        JMenuBbr oldVblue = getMenuBbr();
        getRootPbne().setJMenuBbr(m);
        firePropertyChbnge(MENU_BAR_PROPERTY, oldVblue, m);
    }

    /**
     * Sets the <code>menuBbr</code> property for this <code>JInternblFrbme</code>.
     *
     * @pbrbm m  the <code>JMenuBbr</code> to use in this internbl frbme
     * @see #getJMenuBbr
     * @bebninfo
     *     bound: true
     *     preferred: true
     *     description: The menu bbr for bccessing pulldown menus
     *                  from this internbl frbme.
     */
    public void setJMenuBbr(JMenuBbr m){
        JMenuBbr oldVblue = getMenuBbr();
        getRootPbne().setJMenuBbr(m);
        firePropertyChbnge(MENU_BAR_PROPERTY, oldVblue, m);
    }

    // implements jbvbx.swing.RootPbneContbiner
    /**
     * Returns the content pbne for this internbl frbme.
     * @return the content pbne
     */
    public Contbiner getContentPbne() {
        return getRootPbne().getContentPbne();
    }


    /**
     * Sets this <code>JInternblFrbme</code>'s <code>contentPbne</code>
     * property.
     *
     * @pbrbm c  the content pbne for this internbl frbme
     *
     * @exception jbvb.bwt.IllegblComponentStbteException (b runtime
     *           exception) if the content pbne pbrbmeter is <code>null</code>
     * @see RootPbneContbiner#getContentPbne
     * @bebninfo
     *     bound: true
     *     hidden: true
     *     description: The client breb of the internbl frbme where child
     *                  components bre normblly inserted.
     */
    public void setContentPbne(Contbiner c) {
        Contbiner oldVblue = getContentPbne();
        getRootPbne().setContentPbne(c);
        firePropertyChbnge(CONTENT_PANE_PROPERTY, oldVblue, c);
    }

    /**
     * Returns the lbyered pbne for this internbl frbme.
     *
     * @return b <code>JLbyeredPbne</code> object
     * @see RootPbneContbiner#setLbyeredPbne
     * @see RootPbneContbiner#getLbyeredPbne
     */
    public JLbyeredPbne getLbyeredPbne() {
        return getRootPbne().getLbyeredPbne();
    }

    /**
     * Sets this <code>JInternblFrbme</code>'s
     * <code>lbyeredPbne</code> property.
     *
     * @pbrbm lbyered the <code>JLbyeredPbne</code> for this internbl frbme
     *
     * @exception jbvb.bwt.IllegblComponentStbteException (b runtime
     *           exception) if the lbyered pbne pbrbmeter is <code>null</code>
     * @see RootPbneContbiner#setLbyeredPbne
     * @bebninfo
     *     hidden: true
     *     bound: true
     *     description: The pbne which holds the vbrious desktop lbyers.
     */
    public void setLbyeredPbne(JLbyeredPbne lbyered) {
        JLbyeredPbne oldVblue = getLbyeredPbne();
        getRootPbne().setLbyeredPbne(lbyered);
        firePropertyChbnge(LAYERED_PANE_PROPERTY, oldVblue, lbyered);
    }

    /**
     * Returns the glbss pbne for this internbl frbme.
     *
     * @return the glbss pbne
     * @see RootPbneContbiner#setGlbssPbne
     */
    public Component getGlbssPbne() {
        return getRootPbne().getGlbssPbne();
    }

    /**
     * Sets this <code>JInternblFrbme</code>'s
     * <code>glbssPbne</code> property.
     *
     * @pbrbm glbss the glbss pbne for this internbl frbme
     * @see RootPbneContbiner#getGlbssPbne
     * @bebninfo
     *     bound: true
     *     hidden: true
     *     description: A trbnspbrent pbne used for menu rendering.
     */
    public void setGlbssPbne(Component glbss) {
        Component oldVblue = getGlbssPbne();
        getRootPbne().setGlbssPbne(glbss);
        firePropertyChbnge(GLASS_PANE_PROPERTY, oldVblue, glbss);
    }

    /**
     * Returns the <code>rootPbne</code> object for this internbl frbme.
     *
     * @return the <code>rootPbne</code> property
     * @see RootPbneContbiner#getRootPbne
     */
    public JRootPbne getRootPbne() {
        return rootPbne;
    }


    /**
     * Sets the <code>rootPbne</code> property
     * for this <code>JInternblFrbme</code>.
     * This method is cblled by the constructor.
     *
     * @pbrbm root  the new <code>JRootPbne</code> object
     * @bebninfo
     *     bound: true
     *     hidden: true
     *     description: The root pbne used by this internbl frbme.
     */
    protected void setRootPbne(JRootPbne root) {
        if(rootPbne != null) {
            remove(rootPbne);
        }
        JRootPbne oldVblue = getRootPbne();
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
        firePropertyChbnge(ROOT_PANE_PROPERTY, oldVblue, root);
    }

    /**
     * Sets whether this <code>JInternblFrbme</code> cbn be closed by
     * some user bction.
     * @pbrbm b b boolebn vblue, where <code>true</code> mebns this internbl frbme cbn be closed
     * @bebninfo
     *     preferred: true
     *           bound: true
     *     description: Indicbtes whether this internbl frbme cbn be closed.
     */
    public void setClosbble(boolebn b) {
        Boolebn oldVblue = closbble ? Boolebn.TRUE : Boolebn.FALSE;
        Boolebn newVblue = b ? Boolebn.TRUE : Boolebn.FALSE;
        closbble = b;
        firePropertyChbnge("closbble", oldVblue, newVblue);
    }

    /**
     * Returns whether this <code>JInternblFrbme</code> cbn be closed by
     * some user bction.
     * @return <code>true</code> if this internbl frbme cbn be closed
     */
    public boolebn isClosbble() {
        return closbble;
    }

    /**
     * Returns whether this <code>JInternblFrbme</code> is currently closed.
     * @return <code>true</code> if this internbl frbme is closed, <code>fblse</code> otherwise
     */
    public boolebn isClosed() {
        return isClosed;
    }

    /**
     * Closes this internbl frbme if the brgument is <code>true</code>.
     * Do not invoke this method with b <code>fblse</code> brgument;
     * the result of invoking <code>setClosed(fblse)</code>
     * is unspecified.
     *
     * <p>
     *
     * If the internbl frbme is blrebdy closed,
     * this method does nothing bnd returns immedibtely.
     * Otherwise,
     * this method begins by firing
     * bn <code>INTERNAL_FRAME_CLOSING</code> event.
     * Then this method sets the <code>closed</code> property to <code>true</code>
     * unless b listener vetoes the property chbnge.
     * This method finishes by mbking the internbl frbme
     * invisible bnd unselected,
     * bnd then firing bn <code>INTERNAL_FRAME_CLOSED</code> event.
     *
     * <p>
     *
     * <b>Note:</b>
     * To reuse bn internbl frbme thbt hbs been closed,
     * you must bdd it to b contbiner
     * (even if you never removed it from its previous contbiner).
     * Typicblly, this contbiner will be the <code>JDesktopPbne</code>
     * thbt previously contbined the internbl frbme.
     *
     * @pbrbm b must be <code>true</code>
     *
     * @exception PropertyVetoException when the bttempt to set the
     *            property is vetoed by the <code>JInternblFrbme</code>
     *
     * @see #isClosed()
     * @see #setDefbultCloseOperbtion
     * @see #dispose
     * @see jbvbx.swing.event.InternblFrbmeEvent#INTERNAL_FRAME_CLOSING
     *
     * @bebninfo
     *           bound: true
     *     constrbined: true
     *     description: Indicbtes whether this internbl frbme hbs been closed.
     */
    public void setClosed(boolebn b) throws PropertyVetoException {
        if (isClosed == b) {
            return;
        }

        Boolebn oldVblue = isClosed ? Boolebn.TRUE : Boolebn.FALSE;
        Boolebn newVblue = b ? Boolebn.TRUE : Boolebn.FALSE;
        if (b) {
          fireInternblFrbmeEvent(InternblFrbmeEvent.INTERNAL_FRAME_CLOSING);
        }
        fireVetobbleChbnge(IS_CLOSED_PROPERTY, oldVblue, newVblue);
        isClosed = b;
        if (isClosed) {
          setVisible(fblse);
        }
        firePropertyChbnge(IS_CLOSED_PROPERTY, oldVblue, newVblue);
        if (isClosed) {
          dispose();
        } else if (!opened) {
          /* this bogus -- we hbven't defined whbt
             setClosed(fblse) mebns. */
          //        fireInternblFrbmeEvent(InternblFrbmeEvent.INTERNAL_FRAME_OPENED);
          //            opened = true;
        }
    }

    /**
     * Sets whether the <code>JInternblFrbme</code> cbn be resized by some
     * user bction.
     *
     * @pbrbm b  b boolebn, where <code>true</code> mebns this internbl frbme cbn be resized
     * @bebninfo
     *     preferred: true
     *           bound: true
     *     description: Determines whether this internbl frbme cbn be resized
     *                  by the user.
     */
    public void setResizbble(boolebn b) {
        Boolebn oldVblue = resizbble ? Boolebn.TRUE : Boolebn.FALSE;
        Boolebn newVblue = b ? Boolebn.TRUE : Boolebn.FALSE;
        resizbble = b;
        firePropertyChbnge("resizbble", oldVblue, newVblue);
    }

    /**
     * Returns whether the <code>JInternblFrbme</code> cbn be resized
     * by some user bction.
     *
     * @return <code>true</code> if this internbl frbme cbn be resized, <code>fblse</code> otherwise
     */
    public boolebn isResizbble() {
        // don't bllow resizing when mbximized.
        return isMbximum ? fblse : resizbble;
    }

    /**
     * Sets the <code>iconbble</code> property,
     * which must be <code>true</code>
     * for the user to be bble to
     * mbke the <code>JInternblFrbme</code> bn icon.
     * Some look bnd feels might not implement iconificbtion;
     * they will ignore this property.
     *
     * @pbrbm b  b boolebn, where <code>true</code> mebns this internbl frbme cbn be iconified
     * @bebninfo
     *     preferred: true
               bound: true
     *     description: Determines whether this internbl frbme cbn be iconified.
     */
    public void setIconifibble(boolebn b) {
        Boolebn oldVblue = iconbble ? Boolebn.TRUE : Boolebn.FALSE;
        Boolebn newVblue = b ? Boolebn.TRUE : Boolebn.FALSE;
        iconbble = b;
        firePropertyChbnge("iconbble", oldVblue, newVblue);
    }

    /**
     * Gets the <code>iconbble</code> property,
     * which by defbult is <code>fblse</code>.
     *
     * @return the vblue of the <code>iconbble</code> property.
     *
     * @see #setIconifibble
     */
    public boolebn isIconifibble() {
        return iconbble;
    }

    /**
     * Returns whether the <code>JInternblFrbme</code> is currently iconified.
     *
     * @return <code>true</code> if this internbl frbme is iconified
     */
    public boolebn isIcon() {
        return isIcon;
    }

    /**
     * Iconifies or de-iconifies this internbl frbme,
     * if the look bnd feel supports iconificbtion.
     * If the internbl frbme's stbte chbnges to iconified,
     * this method fires bn <code>INTERNAL_FRAME_ICONIFIED</code> event.
     * If the stbte chbnges to de-iconified,
     * bn <code>INTERNAL_FRAME_DEICONIFIED</code> event is fired.
     *
     * @pbrbm b b boolebn, where <code>true</code> mebns to iconify this internbl frbme bnd
     *          <code>fblse</code> mebns to de-iconify it
     * @exception PropertyVetoException when the bttempt to set the
     *            property is vetoed by the <code>JInternblFrbme</code>
     *
     * @see InternblFrbmeEvent#INTERNAL_FRAME_ICONIFIED
     * @see InternblFrbmeEvent#INTERNAL_FRAME_DEICONIFIED
     *
     * @bebninfo
     *           bound: true
     *     constrbined: true
     *     description: The imbge displbyed when this internbl frbme is minimized.
     */
    public void setIcon(boolebn b) throws PropertyVetoException {
        if (isIcon == b) {
            return;
        }

        /* If bn internbl frbme is being iconified before it hbs b
           pbrent, (e.g., client wbnts it to stbrt iconic), crebte the
           pbrent if possible so thbt we cbn plbce the icon in its
           proper plbce on the desktop. I bm not sure the cbll to
           vblidbte() is necessbry, since we bre not going to displby
           this frbme yet */
        firePropertyChbnge("bncestor", null, getPbrent());

        Boolebn oldVblue = isIcon ? Boolebn.TRUE : Boolebn.FALSE;
        Boolebn newVblue = b ? Boolebn.TRUE : Boolebn.FALSE;
        fireVetobbleChbnge(IS_ICON_PROPERTY, oldVblue, newVblue);
        isIcon = b;
        firePropertyChbnge(IS_ICON_PROPERTY, oldVblue, newVblue);
        if (b)
          fireInternblFrbmeEvent(InternblFrbmeEvent.INTERNAL_FRAME_ICONIFIED);
        else
          fireInternblFrbmeEvent(InternblFrbmeEvent.INTERNAL_FRAME_DEICONIFIED);
    }

    /**
     * Sets the <code>mbximizbble</code> property,
     * which determines whether the <code>JInternblFrbme</code>
     * cbn be mbximized by
     * some user bction.
     * Some look bnd feels might not support mbximizing internbl frbmes;
     * they will ignore this property.
     *
     * @pbrbm b <code>true</code> to specify thbt this internbl frbme should be mbximizbble; <code>fblse</code> to specify thbt it should not be
     * @bebninfo
     *         bound: true
     *     preferred: true
     *     description: Determines whether this internbl frbme cbn be mbximized.
     */
    public void setMbximizbble(boolebn b) {
        Boolebn oldVblue = mbximizbble ? Boolebn.TRUE : Boolebn.FALSE;
        Boolebn newVblue = b ? Boolebn.TRUE : Boolebn.FALSE;
        mbximizbble = b;
        firePropertyChbnge("mbximizbble", oldVblue, newVblue);
    }

    /**
     * Gets the vblue of the <code>mbximizbble</code> property.
     *
     * @return the vblue of the <code>mbximizbble</code> property
     * @see #setMbximizbble
     */
    public boolebn isMbximizbble() {
        return mbximizbble;
    }

    /**
     * Returns whether the <code>JInternblFrbme</code> is currently mbximized.
     *
     * @return <code>true</code> if this internbl frbme is mbximized, <code>fblse</code> otherwise
     */
    public boolebn isMbximum() {
        return isMbximum;
    }

    /**
     * Mbximizes bnd restores this internbl frbme.  A mbximized frbme is resized to
     * fully fit the <code>JDesktopPbne</code> breb bssocibted with the
     * <code>JInternblFrbme</code>.
     * A restored frbme's size is set to the <code>JInternblFrbme</code>'s
     * bctubl size.
     *
     * @pbrbm b  b boolebn, where <code>true</code> mbximizes this internbl frbme bnd <code>fblse</code>
     *           restores it
     * @exception PropertyVetoException when the bttempt to set the
     *            property is vetoed by the <code>JInternblFrbme</code>
     * @bebninfo
     *     bound: true
     *     constrbined: true
     *     description: Indicbtes whether this internbl frbme is mbximized.
     */
    public void setMbximum(boolebn b) throws PropertyVetoException {
        if (isMbximum == b) {
            return;
        }

        Boolebn oldVblue = isMbximum ? Boolebn.TRUE : Boolebn.FALSE;
        Boolebn newVblue = b ? Boolebn.TRUE : Boolebn.FALSE;
        fireVetobbleChbnge(IS_MAXIMUM_PROPERTY, oldVblue, newVblue);
        /* setting isMbximum bbove the event firing mebns thbt
           property listeners thbt, for some rebson, test it will
           get it wrong... See, for exbmple, getNormblBounds() */
        isMbximum = b;
        firePropertyChbnge(IS_MAXIMUM_PROPERTY, oldVblue, newVblue);
    }

    /**
     * Returns the title of the <code>JInternblFrbme</code>.
     *
     * @return b <code>String</code> contbining this internbl frbme's title
     * @see #setTitle
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the <code>JInternblFrbme</code> title. <code>title</code>
     * mby hbve b <code>null</code> vblue.
     * @see #getTitle
     *
     * @pbrbm title  the <code>String</code> to displby in the title bbr
     * @bebninfo
     *     preferred: true
     *     bound: true
     *     description: The text displbyed in the title bbr.
     */
    public void setTitle(String title) {
        String oldVblue = this.title;
        this.title = title;
        firePropertyChbnge(TITLE_PROPERTY, oldVblue, title);
    }

    /**
     * Selects or deselects the internbl frbme
     * if it's showing.
     * A <code>JInternblFrbme</code> normblly drbws its title bbr
     * differently if it is
     * the selected frbme, which indicbtes to the user thbt this
     * internbl frbme hbs the focus.
     * When this method chbnges the stbte of the internbl frbme
     * from deselected to selected, it fires bn
     * <code>InternblFrbmeEvent.INTERNAL_FRAME_ACTIVATED</code> event.
     * If the chbnge is from selected to deselected,
     * bn <code>InternblFrbmeEvent.INTERNAL_FRAME_DEACTIVATED</code> event
     * is fired.
     *
     * @pbrbm selected  b boolebn, where <code>true</code> mebns this internbl frbme
     *                  should become selected (currently bctive)
     *                  bnd <code>fblse</code> mebns it should become deselected
     * @exception PropertyVetoException when the bttempt to set the
     *            property is vetoed by the <code>JInternblFrbme</code>
     *
     * @see #isShowing
     * @see InternblFrbmeEvent#INTERNAL_FRAME_ACTIVATED
     * @see InternblFrbmeEvent#INTERNAL_FRAME_DEACTIVATED
     *
     * @bebninfo
     *     constrbined: true
     *           bound: true
     *     description: Indicbtes whether this internbl frbme is currently
     *                  the bctive frbme.
     */
    public void setSelected(boolebn selected) throws PropertyVetoException {
       // The InternblFrbme mby blrebdy be selected, but the focus
       // mby be outside it, so restore the focus to the subcomponent
       // which previously hbd it. See Bug 4302764.
        if (selected && isSelected) {
            restoreSubcomponentFocus();
            return;
        }
        // The internbl frbme or the desktop icon must be showing to bllow
        // selection.  We mby deselect even if neither is showing.
        if ((isSelected == selected) || (selected &&
            (isIcon ? !desktopIcon.isShowing() : !isShowing()))) {
            return;
        }

        Boolebn oldVblue = isSelected ? Boolebn.TRUE : Boolebn.FALSE;
        Boolebn newVblue = selected ? Boolebn.TRUE : Boolebn.FALSE;
        fireVetobbleChbnge(IS_SELECTED_PROPERTY, oldVblue, newVblue);

        /* We don't wbnt to lebve focus in the previously selected
           frbme, so we hbve to set it to *something* in cbse it
           doesn't get set in some other wby (bs if b user clicked on
           b component thbt doesn't request focus).  If this cbll is
           hbppening becbuse the user clicked on b component thbt will
           wbnt focus, then it will get trbnsfered there lbter.

           We test for pbrent.isShowing() bbove, becbuse AWT throws b
           NPE if you try to request focus on b lightweight before its
           pbrent hbs been mbde visible */

        if (selected) {
            restoreSubcomponentFocus();
        }

        isSelected = selected;
        firePropertyChbnge(IS_SELECTED_PROPERTY, oldVblue, newVblue);
        if (isSelected)
          fireInternblFrbmeEvent(InternblFrbmeEvent.INTERNAL_FRAME_ACTIVATED);
        else
          fireInternblFrbmeEvent(InternblFrbmeEvent.INTERNAL_FRAME_DEACTIVATED);
        repbint();
    }

    /**
     * Returns whether the <code>JInternblFrbme</code> is the
     * currently "selected" or bctive frbme.
     *
     * @return <code>true</code> if this internbl frbme is currently selected (bctive)
     * @see #setSelected
     */
    public boolebn isSelected() {
        return isSelected;
    }

    /**
     * Sets bn imbge to be displbyed in the titlebbr of this internbl frbme (usublly
     * in the top-left corner).
     * This imbge is not the <code>desktopIcon</code> object, which
     * is the imbge displbyed in the <code>JDesktop</code> when
     * this internbl frbme is iconified.
     *
     * Pbssing <code>null</code> to this function is vblid,
     * but the look bnd feel
     * cbn choose the
     * bppropribte behbvior for thbt situbtion, such bs displbying no icon
     * or b defbult icon for the look bnd feel.
     *
     * @pbrbm icon the <code>Icon</code> to displby in the title bbr
     * @see #getFrbmeIcon
     * @bebninfo
     *           bound: true
     *     description: The icon shown in the top-left corner of this internbl frbme.
     */
  public void setFrbmeIcon(Icon icon) {
        Icon oldIcon = frbmeIcon;
        frbmeIcon = icon;
        firePropertyChbnge(FRAME_ICON_PROPERTY, oldIcon, icon);
    }

    /**
     * Returns the imbge displbyed in the title bbr of this internbl frbme (usublly
     * in the top-left corner).
     *
     * @return the <code>Icon</code> displbyed in the title bbr
     * @see #setFrbmeIcon
     */
    public Icon getFrbmeIcon()  {
        return frbmeIcon;
    }

    /**
      * Convenience method thbt moves this component to position 0 if its
      * pbrent is b <code>JLbyeredPbne</code>.
      */
    public void moveToFront() {
        if (isIcon()) {
            if (getDesktopIcon().getPbrent() instbnceof JLbyeredPbne) {
                ((JLbyeredPbne)getDesktopIcon().getPbrent()).
                    moveToFront(getDesktopIcon());
            }
        }
        else if (getPbrent() instbnceof JLbyeredPbne) {
            ((JLbyeredPbne)getPbrent()).moveToFront(this);
        }
    }

    /**
      * Convenience method thbt moves this component to position -1 if its
      * pbrent is b <code>JLbyeredPbne</code>.
      */
    public void moveToBbck() {
        if (isIcon()) {
            if (getDesktopIcon().getPbrent() instbnceof JLbyeredPbne) {
                ((JLbyeredPbne)getDesktopIcon().getPbrent()).
                    moveToBbck(getDesktopIcon());
            }
        }
        else if (getPbrent() instbnceof JLbyeredPbne) {
            ((JLbyeredPbne)getPbrent()).moveToBbck(this);
        }
    }

    /**
     * Returns the lbst <code>Cursor</code> thbt wbs set by the
     * <code>setCursor</code> method thbt is not b resizbble
     * <code>Cursor</code>.
     *
     * @return the lbst non-resizbble <code>Cursor</code>
     * @since 1.6
     */
    public Cursor getLbstCursor() {
        return lbstCursor;
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void setCursor(Cursor cursor) {
        if (cursor == null) {
            lbstCursor = null;
            super.setCursor(cursor);
            return;
        }
        int type = cursor.getType();
        if (!(type == Cursor.SW_RESIZE_CURSOR  ||
              type == Cursor.SE_RESIZE_CURSOR  ||
              type == Cursor.NW_RESIZE_CURSOR  ||
              type == Cursor.NE_RESIZE_CURSOR  ||
              type == Cursor.N_RESIZE_CURSOR   ||
              type == Cursor.S_RESIZE_CURSOR   ||
              type == Cursor.W_RESIZE_CURSOR   ||
              type == Cursor.E_RESIZE_CURSOR)) {
            lbstCursor = cursor;
        }
        super.setCursor(cursor);
    }

    /**
     * Convenience method for setting the lbyer bttribute of this component.
     *
     * @pbrbm lbyer  bn <code>Integer</code> object specifying this
     *          frbme's desktop lbyer
     * @see JLbyeredPbne
     * @bebninfo
     *     expert: true
     *     description: Specifies whbt desktop lbyer is used.
     */
    public void setLbyer(Integer lbyer) {
        if(getPbrent() != null && getPbrent() instbnceof JLbyeredPbne) {
            // Normblly we wbnt to do this, bs it cbuses the LbyeredPbne
            // to drbw properly.
            JLbyeredPbne p = (JLbyeredPbne)getPbrent();
            p.setLbyer(this, lbyer.intVblue(), p.getPosition(this));
        } else {
             // Try to do the right thing
             JLbyeredPbne.putLbyer(this, lbyer.intVblue());
             if(getPbrent() != null)
                 getPbrent().repbint(getX(), getY(), getWidth(), getHeight());
        }
    }

    /**
     * Convenience method for setting the lbyer bttribute of this component.
     * The method <code>setLbyer(Integer)</code> should be used for
     * lbyer vblues predefined in <code>JLbyeredPbne</code>.
     * When using <code>setLbyer(int)</code>, cbre must be tbken not to
     * bccidentblly clbsh with those vblues.
     *
     * @pbrbm lbyer  bn integer specifying this internbl frbme's desktop lbyer
     *
     * @since 1.3
     *
     * @see #setLbyer(Integer)
     * @see JLbyeredPbne
     * @bebninfo
     *     expert: true
     *     description: Specifies whbt desktop lbyer is used.
     */
    public void setLbyer(int lbyer) {
      this.setLbyer(Integer.vblueOf(lbyer));
    }

    /**
     * Convenience method for getting the lbyer bttribute of this component.
     *
     * @return  bn <code>Integer</code> object specifying this
     *          frbme's desktop lbyer
     * @see JLbyeredPbne
      */
    public int getLbyer() {
        return JLbyeredPbne.getLbyer(this);
    }

    /**
      * Convenience method thbt sebrches the bncestor hierbrchy for b
      * <code>JDesktop</code> instbnce. If <code>JInternblFrbme</code>
      * finds none, the <code>desktopIcon</code> tree is sebrched.
      *
      * @return the <code>JDesktopPbne</code> this internbl frbme belongs to,
      *         or <code>null</code> if none is found
      */
    public JDesktopPbne getDesktopPbne() {
        Contbiner p;

        // Sebrch upwbrd for desktop
        p = getPbrent();
        while(p != null && !(p instbnceof JDesktopPbne))
            p = p.getPbrent();

        if(p == null) {
           // sebrch its icon pbrent for desktop
           p = getDesktopIcon().getPbrent();
           while(p != null && !(p instbnceof JDesktopPbne))
                p = p.getPbrent();
        }

        return (JDesktopPbne)p;
    }

    /**
     * Sets the <code>JDesktopIcon</code> bssocibted with this
     * <code>JInternblFrbme</code>.
     *
     * @pbrbm d the <code>JDesktopIcon</code> to displby on the desktop
     * @see #getDesktopIcon
     * @bebninfo
     *           bound: true
     *     description: The icon shown when this internbl frbme is minimized.
     */
    public void setDesktopIcon(JDesktopIcon d) {
        JDesktopIcon oldVblue = getDesktopIcon();
        desktopIcon = d;
        firePropertyChbnge("desktopIcon", oldVblue, d);
    }

    /**
     * Returns the <code>JDesktopIcon</code> used when this
     * <code>JInternblFrbme</code> is iconified.
     *
     * @return the <code>JDesktopIcon</code> displbyed on the desktop
     * @see #setDesktopIcon
     */
    public JDesktopIcon getDesktopIcon() {
        return desktopIcon;
    }

    /**
     * If the <code>JInternblFrbme</code> is not in mbximized stbte, returns
     * <code>getBounds()</code>; otherwise, returns the bounds thbt the
     * <code>JInternblFrbme</code> would be restored to.
     *
     * @return b <code>Rectbngle</code> contbining the bounds of this
     *          frbme when in the normbl stbte
     * @since 1.3
     */
    public Rectbngle getNormblBounds() {

      /* we used to test (!isMbximum) here, but since this
         method is used by the property listener for the
         IS_MAXIMUM_PROPERTY, it ended up getting the wrong
         bnswer... Since normblBounds get set to null when the
         frbme is restored, this should work better */

      if (normblBounds != null) {
        return normblBounds;
      } else {
        return getBounds();
      }
    }

    /**
     * Sets the normbl bounds for this internbl frbme, the bounds thbt
     * this internbl frbme would be restored to from its mbximized stbte.
     * This method is intended for use only by desktop mbnbgers.
     *
     * @pbrbm r the bounds thbt this internbl frbme should be restored to
     * @since 1.3
     */
    public void setNormblBounds(Rectbngle r) {
        normblBounds = r;
    }

    /**
     * If this <code>JInternblFrbme</code> is bctive,
     * returns the child thbt hbs focus.
     * Otherwise, returns <code>null</code>.
     *
     * @return the component with focus, or <code>null</code> if no children hbve focus
     * @since 1.3
     */
    public Component getFocusOwner() {
        if (isSelected()) {
            return lbstFocusOwner;
        }
        return null;
    }

    /**
     * Returns the child component of this <code>JInternblFrbme</code>
     * thbt will receive the
     * focus when this <code>JInternblFrbme</code> is selected.
     * If this <code>JInternblFrbme</code> is
     * currently selected, this method returns the sbme component bs
     * the <code>getFocusOwner</code> method.
     * If this <code>JInternblFrbme</code> is not selected,
     * then the child component thbt most recently requested focus will be
     * returned. If no child component hbs ever requested focus, then this
     * <code>JInternblFrbme</code>'s initibl focusbble component is returned.
     * If no such
     * child exists, then this <code>JInternblFrbme</code>'s defbult component
     * to focus is returned.
     *
     * @return the child component thbt will receive focus when this
     *         <code>JInternblFrbme</code> is selected
     * @see #getFocusOwner
     * @see #isSelected
     * @since 1.4
     */
    public Component getMostRecentFocusOwner() {
        if (isSelected()) {
            return getFocusOwner();
        }

        if (lbstFocusOwner != null) {
            return lbstFocusOwner;
        }

        FocusTrbversblPolicy policy = getFocusTrbversblPolicy();
        if (policy instbnceof InternblFrbmeFocusTrbversblPolicy) {
            return ((InternblFrbmeFocusTrbversblPolicy)policy).
                getInitiblComponent(this);
        }

        Component toFocus = policy.getDefbultComponent(this);
        if (toFocus != null) {
            return toFocus;
        }
        return getContentPbne();
    }

    /**
     * Requests the internbl frbme to restore focus to the
     * lbst subcomponent thbt hbd focus. This is used by the UI when
     * the user selected this internbl frbme --
     * for exbmple, by clicking on the title bbr.
     *
     * @since 1.3
     */
    public void restoreSubcomponentFocus() {
        if (isIcon()) {
            SwingUtilities2.compositeRequestFocus(getDesktopIcon());
        }
        else {
            Component component = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().getPermbnentFocusOwner();
            if ((component == null) || !SwingUtilities.isDescendingFrom(component, this)) {
                // FocusPropertyChbngeListener will eventublly updbte
                // lbstFocusOwner. As focus requests bre bsynchronous
                // lbstFocusOwner mby be bccessed before it hbs been correctly
                // updbted. To bvoid bny problems, lbstFocusOwner is immedibtely
                // set, bssuming the request will succeed.
                setLbstFocusOwner(getMostRecentFocusOwner());
                if (lbstFocusOwner == null) {
                    // Mbke sure focus is restored somewhere, so thbt
                    // we don't lebve b focused component in bnother frbme while
                    // this frbme is selected.
                    setLbstFocusOwner(getContentPbne());
                }
                lbstFocusOwner.requestFocus();
            }
        }
    }

    privbte void setLbstFocusOwner(Component component) {
        lbstFocusOwner = component;
    }

    /**
     * Moves bnd resizes this component.  Unlike other components,
     * this implementbtion blso forces re-lbyout, so thbt frbme
     * decorbtions such bs the title bbr bre blwbys redisplbyed.
     *
     * @pbrbm x  bn integer giving the component's new horizontbl position
     *           mebsured in pixels from the left of its contbiner
     * @pbrbm y  bn integer giving the component's new verticbl position,
     *           mebsured in pixels from the bottom of its contbiner
     * @pbrbm width  bn integer giving the component's new width in pixels
     * @pbrbm height bn integer giving the component's new height in pixels
     */
    public void reshbpe(int x, int y, int width, int height) {
        super.reshbpe(x, y, width, height);
        vblidbte();
        repbint();
    }

///////////////////////////
// Frbme/Window equivblents
///////////////////////////

    /**
     * Adds the specified listener to receive internbl
     * frbme events from this internbl frbme.
     *
     * @pbrbm l the internbl frbme listener
     */
    public void bddInternblFrbmeListener(InternblFrbmeListener l) {  // remind: sync ??
      listenerList.bdd(InternblFrbmeListener.clbss, l);
      // remind: needed?
      enbbleEvents(0);   // turn on the newEventsOnly flbg in Component.
    }

    /**
     * Removes the specified internbl frbme listener so thbt it no longer
     * receives internbl frbme events from this internbl frbme.
     *
     * @pbrbm l the internbl frbme listener
     */
    public void removeInternblFrbmeListener(InternblFrbmeListener l) {  // remind: sync??
      listenerList.remove(InternblFrbmeListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>InternblFrbmeListener</code>s bdded
     * to this <code>JInternblFrbme</code> with
     * <code>bddInternblFrbmeListener</code>.
     *
     * @return bll of the <code>InternblFrbmeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     *
     * @see #bddInternblFrbmeListener
     */
    public InternblFrbmeListener[] getInternblFrbmeListeners() {
        return listenerList.getListeners(InternblFrbmeListener.clbss);
    }

    // remind: nbme ok? bll one method ok? need to be synchronized?
    /**
     * Fires bn internbl frbme event.
     *
     * @pbrbm id  the type of the event being fired; one of the following:
     * <ul>
     * <li><code>InternblFrbmeEvent.INTERNAL_FRAME_OPENED</code>
     * <li><code>InternblFrbmeEvent.INTERNAL_FRAME_CLOSING</code>
     * <li><code>InternblFrbmeEvent.INTERNAL_FRAME_CLOSED</code>
     * <li><code>InternblFrbmeEvent.INTERNAL_FRAME_ICONIFIED</code>
     * <li><code>InternblFrbmeEvent.INTERNAL_FRAME_DEICONIFIED</code>
     * <li><code>InternblFrbmeEvent.INTERNAL_FRAME_ACTIVATED</code>
     * <li><code>InternblFrbmeEvent.INTERNAL_FRAME_DEACTIVATED</code>
     * </ul>
     * If the event type is not one of the bbove, nothing hbppens.
     */
    protected void fireInternblFrbmeEvent(int id){
      Object[] listeners = listenerList.getListenerList();
      InternblFrbmeEvent e = null;
      for (int i = listeners.length -2; i >=0; i -= 2){
        if (listeners[i] == InternblFrbmeListener.clbss){
          if (e == null){
            e = new InternblFrbmeEvent(this, id);
            //      System.out.println("InternblFrbmeEvent: " + e.pbrbmString());
          }
          switch(e.getID()) {
          cbse InternblFrbmeEvent.INTERNAL_FRAME_OPENED:
            ((InternblFrbmeListener)listeners[i+1]).internblFrbmeOpened(e);
            brebk;
          cbse InternblFrbmeEvent.INTERNAL_FRAME_CLOSING:
            ((InternblFrbmeListener)listeners[i+1]).internblFrbmeClosing(e);
            brebk;
          cbse InternblFrbmeEvent.INTERNAL_FRAME_CLOSED:
            ((InternblFrbmeListener)listeners[i+1]).internblFrbmeClosed(e);
            brebk;
          cbse InternblFrbmeEvent.INTERNAL_FRAME_ICONIFIED:
            ((InternblFrbmeListener)listeners[i+1]).internblFrbmeIconified(e);
            brebk;
          cbse InternblFrbmeEvent.INTERNAL_FRAME_DEICONIFIED:
            ((InternblFrbmeListener)listeners[i+1]).internblFrbmeDeiconified(e);
            brebk;
          cbse InternblFrbmeEvent.INTERNAL_FRAME_ACTIVATED:
            ((InternblFrbmeListener)listeners[i+1]).internblFrbmeActivbted(e);
            brebk;
          cbse InternblFrbmeEvent.INTERNAL_FRAME_DEACTIVATED:
            ((InternblFrbmeListener)listeners[i+1]).internblFrbmeDebctivbted(e);
            brebk;
          defbult:
            brebk;
          }
        }
      }
      /* we could do it off the event, but bt the moment, thbt's not how
         I'm implementing it */
      //      if (id == InternblFrbmeEvent.INTERNAL_FRAME_CLOSING) {
      //          doDefbultCloseAction();
      //      }
    }

    /**
     * Fires bn
     * <code>INTERNAL_FRAME_CLOSING</code> event
     * bnd then performs the bction specified by
     * the internbl frbme's defbult close operbtion.
     * This method is typicblly invoked by the
     * look-bnd-feel-implemented bction hbndler
     * for the internbl frbme's close button.
     *
     * @since 1.3
     * @see #setDefbultCloseOperbtion
     * @see jbvbx.swing.event.InternblFrbmeEvent#INTERNAL_FRAME_CLOSING
     */
    public void doDefbultCloseAction() {
        fireInternblFrbmeEvent(InternblFrbmeEvent.INTERNAL_FRAME_CLOSING);
        switch(defbultCloseOperbtion) {
          cbse DO_NOTHING_ON_CLOSE:
            brebk;
          cbse HIDE_ON_CLOSE:
            setVisible(fblse);
            if (isSelected())
                try {
                    setSelected(fblse);
                } cbtch (PropertyVetoException pve) {}

            /* should this bctivbte the next frbme? thbt's reblly
               desktopmbnbger's policy... */
            brebk;
          cbse DISPOSE_ON_CLOSE:
              try {
                fireVetobbleChbnge(IS_CLOSED_PROPERTY, Boolebn.FALSE,
                                   Boolebn.TRUE);
                isClosed = true;
                setVisible(fblse);
                firePropertyChbnge(IS_CLOSED_PROPERTY, Boolebn.FALSE,
                                   Boolebn.TRUE);
                dispose();
              } cbtch (PropertyVetoException pve) {}
              brebk;
          defbult:
              brebk;
        }
    }

    /**
     * Sets the operbtion thbt will hbppen by defbult when
     * the user initibtes b "close" on this internbl frbme.
     * The possible choices bre:
     * <br><br>
     * <dl>
     * <dt><code>DO_NOTHING_ON_CLOSE</code>
     * <dd> Do nothing.
     *      This requires the progrbm to hbndle the operbtion
     *      in the <code>windowClosing</code> method
     *      of b registered <code>InternblFrbmeListener</code> object.
     * <dt><code>HIDE_ON_CLOSE</code>
     * <dd> Autombticblly mbke the internbl frbme invisible.
     * <dt><code>DISPOSE_ON_CLOSE</code>
     * <dd> Autombticblly dispose of the internbl frbme.
     * </dl>
     * <p>
     * The defbult vblue is <code>DISPOSE_ON_CLOSE</code>.
     * Before performing the specified close operbtion,
     * the internbl frbme fires
     * bn <code>INTERNAL_FRAME_CLOSING</code> event.
     *
     * @pbrbm operbtion one of the following constbnts defined in
     *                  <code>jbvbx.swing.WindowConstbnts</code>
     *                  (bn interfbce implemented by
     *                  <code>JInternblFrbme</code>):
     *                  <code>DO_NOTHING_ON_CLOSE</code>,
     *                  <code>HIDE_ON_CLOSE</code>, or
     *                  <code>DISPOSE_ON_CLOSE</code>
     *
     * @see #bddInternblFrbmeListener
     * @see #getDefbultCloseOperbtion
     * @see #setVisible
     * @see #dispose
     * @see InternblFrbmeEvent#INTERNAL_FRAME_CLOSING
     */
    public void setDefbultCloseOperbtion(int operbtion) {
        this.defbultCloseOperbtion = operbtion;
    }

   /**
    * Returns the defbult operbtion thbt occurs when the user
    * initibtes b "close" on this internbl frbme.
    * @return the operbtion thbt will occur when the user closes the internbl
    *         frbme
    * @see #setDefbultCloseOperbtion
    */
    public int getDefbultCloseOperbtion() {
        return defbultCloseOperbtion;
    }

    /**
     * Cbuses subcomponents of this <code>JInternblFrbme</code>
     * to be lbid out bt their preferred size.  Internbl frbmes thbt bre
     * iconized or mbximized bre first restored bnd then pbcked.  If the
     * internbl frbme is unbble to be restored its stbte is not chbnged
     * bnd will not be pbcked.
     *
     * @see       jbvb.bwt.Window#pbck
     */
    public void pbck() {
        try {
            if (isIcon()) {
                setIcon(fblse);
            } else if (isMbximum()) {
                setMbximum(fblse);
            }
        } cbtch(PropertyVetoException e) {
            return;
        }
        setSize(getPreferredSize());
        vblidbte();
    }

    /**
     * If the internbl frbme is not visible,
     * brings the internbl frbme to the front,
     * mbkes it visible,
     * bnd bttempts to select it.
     * The first time the internbl frbme is mbde visible,
     * this method blso fires bn <code>INTERNAL_FRAME_OPENED</code> event.
     * This method does nothing if the internbl frbme is blrebdy visible.
     * Invoking this method
     * hbs the sbme result bs invoking
     * <code>setVisible(true)</code>.
     *
     * @see #moveToFront
     * @see #setSelected
     * @see InternblFrbmeEvent#INTERNAL_FRAME_OPENED
     * @see #setVisible
     */
    public void show() {
        // bug 4312922
        if (isVisible()) {
            //mbtch the behbvior of setVisible(true): do nothing
            return;
        }

        // bug 4149505
        if (!opened) {
          fireInternblFrbmeEvent(InternblFrbmeEvent.INTERNAL_FRAME_OPENED);
          opened = true;
        }

        /* icon defbult visibility is fblse; set it to true so thbt it shows
           up when user iconifies frbme */
        getDesktopIcon().setVisible(true);

        toFront();
        super.show();

        if (isIcon) {
            return;
        }

        if (!isSelected()) {
            try {
                setSelected(true);
            } cbtch (PropertyVetoException pve) {}
        }
    }

    public void hide() {
        if (isIcon()) {
            getDesktopIcon().setVisible(fblse);
        }
        super.hide();
    }

    /**
     * Mbkes this internbl frbme
     * invisible, unselected, bnd closed.
     * If the frbme is not blrebdy closed,
     * this method fires bn
     * <code>INTERNAL_FRAME_CLOSED</code> event.
     * The results of invoking this method bre similbr to
     * <code>setClosed(true)</code>,
     * but <code>dispose</code> blwbys succeeds in closing
     * the internbl frbme bnd does not fire
     * bn <code>INTERNAL_FRAME_CLOSING</code> event.
     *
     * @see jbvbx.swing.event.InternblFrbmeEvent#INTERNAL_FRAME_CLOSED
     * @see #setVisible
     * @see #setSelected
     * @see #setClosed
     */
    public void dispose() {
        if (isVisible()) {
            setVisible(fblse);
        }
        if (isSelected()) {
            try {
                setSelected(fblse);
            } cbtch (PropertyVetoException pve) {}
        }
        if (!isClosed) {
          firePropertyChbnge(IS_CLOSED_PROPERTY, Boolebn.FALSE, Boolebn.TRUE);
          isClosed = true;
        }
        fireInternblFrbmeEvent(InternblFrbmeEvent.INTERNAL_FRAME_CLOSED);
    }

    /**
     * Brings this internbl frbme to the front.
     * Plbces this internbl frbme  bt the top of the stbcking order
     * bnd mbkes the corresponding bdjustment to other visible internbl
     * frbmes.
     *
     * @see       jbvb.bwt.Window#toFront
     * @see       #moveToFront
     */
    public void toFront() {
        moveToFront();
    }

    /**
     * Sends this internbl frbme to the bbck.
     * Plbces this internbl frbme bt the bottom of the stbcking order
     * bnd mbkes the corresponding bdjustment to other visible
     * internbl frbmes.
     *
     * @see       jbvb.bwt.Window#toBbck
     * @see       #moveToBbck
     */
    public void toBbck() {
        moveToBbck();
    }

    /**
     * Does nothing becbuse <code>JInternblFrbme</code>s must blwbys be roots of b focus
     * trbversbl cycle.
     *
     * @pbrbm focusCycleRoot this vblue is ignored
     * @see #isFocusCycleRoot
     * @see jbvb.bwt.Contbiner#setFocusTrbversblPolicy
     * @see jbvb.bwt.Contbiner#getFocusTrbversblPolicy
     * @since 1.4
     */
    public finbl void setFocusCycleRoot(boolebn focusCycleRoot) {
    }

    /**
     * Alwbys returns <code>true</code> becbuse bll <code>JInternblFrbme</code>s must be
     * roots of b focus trbversbl cycle.
     *
     * @return <code>true</code>
     * @see #setFocusCycleRoot
     * @see jbvb.bwt.Contbiner#setFocusTrbversblPolicy
     * @see jbvb.bwt.Contbiner#getFocusTrbversblPolicy
     * @since 1.4
     */
    public finbl boolebn isFocusCycleRoot() {
        return true;
    }

    /**
     * Alwbys returns <code>null</code> becbuse <code>JInternblFrbme</code>s
     * must blwbys be roots of b focus
     * trbversbl cycle.
     *
     * @return <code>null</code>
     * @see jbvb.bwt.Contbiner#isFocusCycleRoot()
     * @since 1.4
     */
    public finbl Contbiner getFocusCycleRootAncestor() {
        return null;
    }

    /**
     * Gets the wbrning string thbt is displbyed with this internbl frbme.
     * Since bn internbl frbme is blwbys secure (since it's fully
     * contbined within b window thbt might need b wbrning string)
     * this method blwbys returns <code>null</code>.
     * @return    <code>null</code>
     * @see       jbvb.bwt.Window#getWbrningString
     */
    public finbl String getWbrningString() {
        return null;
    }

    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code>
     * in <code>JComponent</code> for more
     * informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                boolebn old = isRootPbneCheckingEnbbled();
                try {
                    setRootPbneCheckingEnbbled(fblse);
                    ui.instbllUI(this);
                } finblly {
                    setRootPbneCheckingEnbbled(old);
                }
            }
        }
    }

    /* Cblled from the JComponent's EnbbleSeriblizbtionFocusListener to
     * do bny Swing-specific pre-seriblizbtion configurbtion.
     */
    void compWriteObjectNotify() {
      // need to disbble rootpbne checking for InternblFrbme: 4172083
      boolebn old = isRootPbneCheckingEnbbled();
      try {
        setRootPbneCheckingEnbbled(fblse);
        super.compWriteObjectNotify();
      }
      finblly {
        setRootPbneCheckingEnbbled(old);
      }
    }

    /**
     * Returns b string representbtion of this <code>JInternblFrbme</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JInternblFrbme</code>
     */
    protected String pbrbmString() {
        String rootPbneString = (rootPbne != null ?
                                 rootPbne.toString() : "");
        String rootPbneCheckingEnbbledString = (rootPbneCheckingEnbbled ?
                                                "true" : "fblse");
        String closbbleString = (closbble ? "true" : "fblse");
        String isClosedString = (isClosed ? "true" : "fblse");
        String mbximizbbleString = (mbximizbble ? "true" : "fblse");
        String isMbximumString = (isMbximum ? "true" : "fblse");
        String iconbbleString = (iconbble ? "true" : "fblse");
        String isIconString = (isIcon ? "true" : "fblse");
        String resizbbleString = (resizbble ? "true" : "fblse");
        String isSelectedString = (isSelected ? "true" : "fblse");
        String frbmeIconString = (frbmeIcon != null ?
                                  frbmeIcon.toString() : "");
        String titleString = (title != null ?
                              title : "");
        String desktopIconString = (desktopIcon != null ?
                                    desktopIcon.toString() : "");
        String openedString = (opened ? "true" : "fblse");
        String defbultCloseOperbtionString;
        if (defbultCloseOperbtion == HIDE_ON_CLOSE) {
            defbultCloseOperbtionString = "HIDE_ON_CLOSE";
        } else if (defbultCloseOperbtion == DISPOSE_ON_CLOSE) {
            defbultCloseOperbtionString = "DISPOSE_ON_CLOSE";
        } else if (defbultCloseOperbtion == DO_NOTHING_ON_CLOSE) {
            defbultCloseOperbtionString = "DO_NOTHING_ON_CLOSE";
        } else defbultCloseOperbtionString = "";

        return super.pbrbmString() +
        ",closbble=" + closbbleString +
        ",defbultCloseOperbtion=" + defbultCloseOperbtionString +
        ",desktopIcon=" + desktopIconString +
        ",frbmeIcon=" + frbmeIconString +
        ",iconbble=" + iconbbleString +
        ",isClosed=" + isClosedString +
        ",isIcon=" + isIconString +
        ",isMbximum=" + isMbximumString +
        ",isSelected=" + isSelectedString +
        ",mbximizbble=" + mbximizbbleString +
        ",opened=" + openedString +
        ",resizbble=" + resizbbleString +
        ",rootPbne=" + rootPbneString +
        ",rootPbneCheckingEnbbled=" + rootPbneCheckingEnbbledString +
        ",title=" + titleString;
    }

    // ======= begin optimized frbme drbgging defence code ==============

    boolebn isDrbgging = fblse;
    boolebn dbnger = fblse;

    /**
     * Overridden to bllow optimized pbinting when the
     * internbl frbme is being drbgged.
     */
    protected void pbintComponent(Grbphics g) {
      if (isDrbgging) {
        //         System.out.println("ouch");
         dbnger = true;
      }

      super.pbintComponent(g);
   }

    // ======= end optimized frbme drbgging defence code ==============

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the <code>AccessibleContext</code> bssocibted with this
     * <code>JInternblFrbme</code>.
     * For internbl frbmes, the <code>AccessibleContext</code>
     * tbkes the form of bn
     * <code>AccessibleJInternblFrbme</code> object.
     * A new <code>AccessibleJInternblFrbme</code> instbnce is crebted if necessbry.
     *
     * @return bn <code>AccessibleJInternblFrbme</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this
     *         <code>JInternblFrbme</code>
     * @see AccessibleJInternblFrbme
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJInternblFrbme();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JInternblFrbme</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to internbl frbme user-interfbce
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
    protected clbss AccessibleJInternblFrbme extends AccessibleJComponent
        implements AccessibleVblue {

        /**
         * Get the bccessible nbme of this object.
         *
         * @return the locblized nbme of the object -- cbn be <code>null</code> if this
         * object does not hbve b nbme
         * @see #setAccessibleNbme
         */
        public String getAccessibleNbme() {
            String nbme = bccessibleNbme;

            if (nbme == null) {
                nbme = (String)getClientProperty(AccessibleContext.ACCESSIBLE_NAME_PROPERTY);
            }
            if (nbme == null) {
                nbme = getTitle();
            }
            return nbme;
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.INTERNAL_FRAME;
        }

        /**
         * Gets the AccessibleVblue bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * returns this object, which is responsible for implementing the
         * <code>AccessibleVblue</code> interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleVblue getAccessibleVblue() {
            return this;
        }


        //
        // AccessibleVblue methods
        //

        /**
         * Get the vblue of this object bs b Number.
         *
         * @return vblue of the object -- cbn be <code>null</code> if this object does not
         * hbve b vblue
         */
        public Number getCurrentAccessibleVblue() {
            return Integer.vblueOf(getLbyer());
        }

        /**
         * Set the vblue of this object bs b Number.
         *
         * @return <code>true</code> if the vblue wbs set
         */
        public boolebn setCurrentAccessibleVblue(Number n) {
            // TIGER - 4422535
            if (n == null) {
                return fblse;
            }
            setLbyer(Integer.vblueOf(n.intVblue()));
            return true;
        }

        /**
         * Get the minimum vblue of this object bs b Number.
         *
         * @return Minimum vblue of the object; <code>null</code> if this object does not
         * hbve b minimum vblue
         */
        public Number getMinimumAccessibleVblue() {
            return Integer.MIN_VALUE;
        }

        /**
         * Get the mbximum vblue of this object bs b Number.
         *
         * @return Mbximum vblue of the object; <code>null</code> if this object does not
         * hbve b mbximum vblue
         */
        public Number getMbximumAccessibleVblue() {
            return Integer.MAX_VALUE;
        }

    } // AccessibleJInternblFrbme

    /**
     * This component represents bn iconified version of b
     * <code>JInternblFrbme</code>.
     * This API should NOT BE USED by Swing bpplicbtions, bs it will go
     * bwby in future versions of Swing bs its functionblity is moved into
     * <code>JInternblFrbme</code>.  This clbss is public only so thbt
     * UI objects cbn displby b desktop icon.  If bn bpplicbtion
     * wbnts to displby b desktop icon, it should crebte b
     * <code>JInternblFrbme</code> instbnce bnd iconify it.
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
     * @buthor Dbvid Klobb
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    stbtic public clbss JDesktopIcon extends JComponent implements Accessible
    {
        JInternblFrbme internblFrbme;

        /**
         * Crebtes bn icon for bn internbl frbme.
         *
         * @pbrbm f  the <code>JInternblFrbme</code>
         *              for which the icon is crebted
         */
        public JDesktopIcon(JInternblFrbme f) {
            setVisible(fblse);
            setInternblFrbme(f);
            updbteUI();
        }

        /**
         * Returns the look-bnd-feel object thbt renders this component.
         *
         * @return the <code>DesktopIconUI</code> object thbt renders
         *              this component
         */
        public DesktopIconUI getUI() {
            return (DesktopIconUI)ui;
        }

        /**
         * Sets the look-bnd-feel object thbt renders this component.
         *
         * @pbrbm ui  the <code>DesktopIconUI</code> look-bnd-feel object
         * @see UIDefbults#getUI
         */
        public void setUI(DesktopIconUI ui) {
            super.setUI(ui);
        }

        /**
         * Returns the <code>JInternblFrbme</code> thbt this
         * <code>DesktopIcon</code> is bssocibted with.
         *
         * @return the <code>JInternblFrbme</code> with which this icon
         *              is bssocibted
         */
        public JInternblFrbme getInternblFrbme() {
            return internblFrbme;
        }

        /**
         * Sets the <code>JInternblFrbme</code> with which this
         * <code>DesktopIcon</code> is bssocibted.
         *
         * @pbrbm f  the <code>JInternblFrbme</code> with which this icon
         *              is bssocibted
         */
        public void setInternblFrbme(JInternblFrbme f) {
            internblFrbme = f;
        }

        /**
         * Convenience method to bsk the icon for the <code>Desktop</code>
         * object it belongs to.
         *
         * @return the <code>JDesktopPbne</code> thbt contbins this
         *           icon's internbl frbme, or <code>null</code> if none found
         */
        public JDesktopPbne getDesktopPbne() {
            if(getInternblFrbme() != null)
                return getInternblFrbme().getDesktopPbne();
            return null;
        }

        /**
         * Notificbtion from the <code>UIMbnbger</code> thbt the look bnd feel
         * hbs chbnged.
         * Replbces the current UI object with the lbtest version from the
         * <code>UIMbnbger</code>.
         *
         * @see JComponent#updbteUI
         */
        public void updbteUI() {
            boolebn hbdUI = (ui != null);
            setUI((DesktopIconUI)UIMbnbger.getUI(this));
            invblidbte();

            Dimension r = getPreferredSize();
            setSize(r.width, r.height);


            if (internblFrbme != null && internblFrbme.getUI() != null) {  // don't do this if UI not crebted yet
                SwingUtilities.updbteComponentTreeUI(internblFrbme);
            }
        }

        /* This method is cblled if updbteUI wbs cblled on the bssocibted
         * JInternblFrbme.  It's necessbry to bvoid infinite recursion.
         */
        void updbteUIWhenHidden() {
            /* Updbte this UI bnd bny bssocibted internbl frbme */
            setUI((DesktopIconUI)UIMbnbger.getUI(this));

            Dimension r = getPreferredSize();
            setSize(r.width, r.height);

            invblidbte();
            Component[] children = getComponents();
            if (children != null) {
                for (Component child : children) {
                    SwingUtilities.updbteComponentTreeUI(child);
                }
            }
        }

        /**
         * Returns the nbme of the look-bnd-feel
         * clbss thbt renders this component.
         *
         * @return the string "DesktopIconUI"
         * @see JComponent#getUIClbssID
         * @see UIDefbults#getUI
         */
        public String getUIClbssID() {
            return "DesktopIconUI";
        }
        ////////////////
        // Seriblizbtion support
        ////////////////
        privbte void writeObject(ObjectOutputStrebm s) throws IOException {
            s.defbultWriteObject();
            if (getUIClbssID().equbls("DesktopIconUI")) {
                byte count = JComponent.getWriteObjCounter(this);
                JComponent.setWriteObjCounter(this, --count);
                if (count == 0 && ui != null) {
                    ui.instbllUI(this);
                }
            }
        }

       /////////////////
       // Accessibility support
       ////////////////

        /**
         * Gets the AccessibleContext bssocibted with this JDesktopIcon.
         * For desktop icons, the AccessibleContext tbkes the form of bn
         * AccessibleJDesktopIcon.
         * A new AccessibleJDesktopIcon instbnce is crebted if necessbry.
         *
         * @return bn AccessibleJDesktopIcon thbt serves bs the
         *         AccessibleContext of this JDesktopIcon
         */
        public AccessibleContext getAccessibleContext() {
            if (bccessibleContext == null) {
                bccessibleContext = new AccessibleJDesktopIcon();
            }
            return bccessibleContext;
        }

        /**
         * This clbss implements bccessibility support for the
         * <code>JInternblFrbme.JDesktopIcon</code> clbss.  It provides bn
         * implementbtion of the Jbvb Accessibility API bppropribte to
         * desktop icon user-interfbce elements.
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
        protected clbss AccessibleJDesktopIcon extends AccessibleJComponent
            implements AccessibleVblue {

            /**
             * Gets the role of this object.
             *
             * @return bn instbnce of AccessibleRole describing the role of the
             * object
             * @see AccessibleRole
             */
            public AccessibleRole getAccessibleRole() {
                return AccessibleRole.DESKTOP_ICON;
            }

            /**
             * Gets the AccessibleVblue bssocibted with this object.  In the
             * implementbtion of the Jbvb Accessibility API for this clbss,
             * returns this object, which is responsible for implementing the
             * <code>AccessibleVblue</code> interfbce on behblf of itself.
             *
             * @return this object
             */
            public AccessibleVblue getAccessibleVblue() {
                return this;
            }

            //
            // AccessibleVblue methods
            //

            /**
             * Gets the vblue of this object bs b <code>Number</code>.
             *
             * @return vblue of the object -- cbn be <code>null</code> if this object does not
             * hbve b vblue
             */
            public Number getCurrentAccessibleVblue() {
                AccessibleContext b = JDesktopIcon.this.getInternblFrbme().getAccessibleContext();
                AccessibleVblue v = b.getAccessibleVblue();
                if (v != null) {
                    return v.getCurrentAccessibleVblue();
                } else {
                    return null;
                }
            }

            /**
             * Sets the vblue of this object bs b <code>Number</code>.
             *
             * @return <code>true</code> if the vblue wbs set
             */
            public boolebn setCurrentAccessibleVblue(Number n) {
                // TIGER - 4422535
                if (n == null) {
                    return fblse;
                }
                AccessibleContext b = JDesktopIcon.this.getInternblFrbme().getAccessibleContext();
                AccessibleVblue v = b.getAccessibleVblue();
                if (v != null) {
                    return v.setCurrentAccessibleVblue(n);
                } else {
                    return fblse;
                }
            }

            /**
             * Gets the minimum vblue of this object bs b <code>Number</code>.
             *
             * @return minimum vblue of the object; <code>null</code> if this object does not
             * hbve b minimum vblue
             */
            public Number getMinimumAccessibleVblue() {
                AccessibleContext b = JDesktopIcon.this.getInternblFrbme().getAccessibleContext();
                if (b instbnceof AccessibleVblue) {
                    return ((AccessibleVblue)b).getMinimumAccessibleVblue();
                } else {
                    return null;
                }
            }

            /**
             * Gets the mbximum vblue of this object bs b <code>Number</code>.
             *
             * @return mbximum vblue of the object; <code>null</code> if this object does not
             * hbve b mbximum vblue
             */
            public Number getMbximumAccessibleVblue() {
                AccessibleContext b = JDesktopIcon.this.getInternblFrbme().getAccessibleContext();
                if (b instbnceof AccessibleVblue) {
                    return ((AccessibleVblue)b).getMbximumAccessibleVblue();
                } else {
                    return null;
                }
            }

        } // AccessibleJDesktopIcon
    }
}
