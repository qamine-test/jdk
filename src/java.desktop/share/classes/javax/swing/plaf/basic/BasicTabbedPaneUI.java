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

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.View;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.util.Vector;
import jbvb.util.Hbshtbble;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;

/**
 * A Bbsic L&bmp;F implementbtion of TbbbedPbneUI.
 *
 * @buthor Amy Fowler
 * @buthor Philip Milne
 * @buthor Steve Wilson
 * @buthor Tom Sbntos
 * @buthor Dbve Moore
 */
public clbss BbsicTbbbedPbneUI extends TbbbedPbneUI implements SwingConstbnts {


// Instbnce vbribbles initiblized bt instbllbtion

    protected JTbbbedPbne tbbPbne;

    protected Color highlight;
    protected Color lightHighlight;
    protected Color shbdow;
    protected Color dbrkShbdow;
    protected Color focus;
    privbte   Color selectedColor;

    protected int textIconGbp;

    protected int tbbRunOverlby;

    protected Insets tbbInsets;
    protected Insets selectedTbbPbdInsets;
    protected Insets tbbArebInsets;
    protected Insets contentBorderInsets;
    privbte boolebn tbbsOverlbpBorder;
    privbte boolebn tbbsOpbque = true;
    privbte boolebn contentOpbque = true;

    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     */
    @Deprecbted
    protected KeyStroke upKey;
    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     */
    @Deprecbted
    protected KeyStroke downKey;
    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     */
    @Deprecbted
    protected KeyStroke leftKey;
    /**
     * As of Jbvb 2 plbtform v1.3 this previously undocumented field is no
     * longer used.
     * Key bindings bre now defined by the LookAndFeel, plebse refer to
     * the key bindings specificbtion for further detbils.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3.
     */
    @Deprecbted
    protected KeyStroke rightKey;


// Trbnsient vbribbles (recblculbted ebch time TbbbedPbne is lbyed out)

    protected int tbbRuns[] = new int[10];
    protected int runCount = 0;
    protected int selectedRun = -1;
    protected Rectbngle rects[] = new Rectbngle[0];
    protected int mbxTbbHeight;
    protected int mbxTbbWidth;

// Listeners

    protected ChbngeListener tbbChbngeListener;
    protected PropertyChbngeListener propertyChbngeListener;
    protected MouseListener mouseListener;
    protected FocusListener focusListener;

// Privbte instbnce dbtb

    privbte Insets currentPbdInsets = new Insets(0,0,0,0);
    privbte Insets currentTbbArebInsets = new Insets(0,0,0,0);

    privbte Component visibleComponent;
    // PENDING(bpi): See comment for ContbinerHbndler
    privbte Vector<View> htmlViews;

    privbte Hbshtbble<Integer, Integer> mnemonicToIndexMbp;

    /**
     * InputMbp used for mnemonics. Only non-null if the JTbbbedPbne hbs
     * mnemonics bssocibted with it. Lbzily crebted in initMnemonics.
     */
    privbte InputMbp mnemonicInputMbp;

    // For use when tbbLbyoutPolicy = SCROLL_TAB_LAYOUT
    privbte ScrollbbleTbbSupport tbbScroller;

    privbte TbbContbiner tbbContbiner;

    /**
     * A rectbngle used for generbl lbyout cblculbtions in order
     * to bvoid constructing mbny new Rectbngles on the fly.
     */
    protected trbnsient Rectbngle cblcRect = new Rectbngle(0,0,0,0);

    /**
     * Tbb thbt hbs focus.
     */
    privbte int focusIndex;

    /**
     * Combined listeners.
     */
    privbte Hbndler hbndler;

    /**
     * Index of the tbb the mouse is over.
     */
    privbte int rolloverTbbIndex;

    /**
     * This is set to true when b component is bdded/removed from the tbb
     * pbne bnd set to fblse when lbyout hbppens.  If true it indicbtes thbt
     * tbbRuns is not vblid bnd shouldn't be used.
     */
    privbte boolebn isRunsDirty;

    privbte boolebn cblculbtedBbseline;
    privbte int bbseline;

// UI crebtion

    public stbtic ComponentUI crebteUI(JComponent c) {
        return new BbsicTbbbedPbneUI();
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.NEXT));
        mbp.put(new Actions(Actions.PREVIOUS));
        mbp.put(new Actions(Actions.RIGHT));
        mbp.put(new Actions(Actions.LEFT));
        mbp.put(new Actions(Actions.UP));
        mbp.put(new Actions(Actions.DOWN));
        mbp.put(new Actions(Actions.PAGE_UP));
        mbp.put(new Actions(Actions.PAGE_DOWN));
        mbp.put(new Actions(Actions.REQUEST_FOCUS));
        mbp.put(new Actions(Actions.REQUEST_FOCUS_FOR_VISIBLE));
        mbp.put(new Actions(Actions.SET_SELECTED));
        mbp.put(new Actions(Actions.SELECT_FOCUSED));
        mbp.put(new Actions(Actions.SCROLL_FORWARD));
        mbp.put(new Actions(Actions.SCROLL_BACKWARD));
    }

// UI Instbllbtion/De-instbllbtion

    public void instbllUI(JComponent c) {
        this.tbbPbne = (JTbbbedPbne)c;

        cblculbtedBbseline = fblse;
        rolloverTbbIndex = -1;
        focusIndex = -1;
        c.setLbyout(crebteLbyoutMbnbger());
        instbllComponents();
        instbllDefbults();
        instbllListeners();
        instbllKeybobrdActions();
    }

    public void uninstbllUI(JComponent c) {
        uninstbllKeybobrdActions();
        uninstbllListeners();
        uninstbllDefbults();
        uninstbllComponents();
        c.setLbyout(null);

        this.tbbPbne = null;
    }

    /**
     * Invoked by <code>instbllUI</code> to crebte
     * b lbyout mbnbger object to mbnbge
     * the <code>JTbbbedPbne</code>.
     *
     * @return b lbyout mbnbger object
     *
     * @see TbbbedPbneLbyout
     * @see jbvbx.swing.JTbbbedPbne#getTbbLbyoutPolicy
     */
    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        if (tbbPbne.getTbbLbyoutPolicy() == JTbbbedPbne.SCROLL_TAB_LAYOUT) {
            return new TbbbedPbneScrollLbyout();
        } else { /* WRAP_TAB_LAYOUT */
            return new TbbbedPbneLbyout();
        }
    }

    /* In bn bttempt to preserve bbckwbrd compbtibility for progrbms
     * which hbve extended BbsicTbbbedPbneUI to do their own lbyout, the
     * UI uses the instblled lbyoutMbnbger (bnd not tbbLbyoutPolicy) to
     * determine if scrollTbbLbyout is enbbled.
     */
    privbte boolebn scrollbbleTbbLbyoutEnbbled() {
        return (tbbPbne.getLbyout() instbnceof TbbbedPbneScrollLbyout);
    }

    /**
     * Crebtes bnd instblls bny required subcomponents for the JTbbbedPbne.
     * Invoked by instbllUI.
     *
     * @since 1.4
     */
    protected void instbllComponents() {
        if (scrollbbleTbbLbyoutEnbbled()) {
            if (tbbScroller == null) {
                tbbScroller = new ScrollbbleTbbSupport(tbbPbne.getTbbPlbcement());
                tbbPbne.bdd(tbbScroller.viewport);
            }
        }
        instbllTbbContbiner();
    }

    privbte void instbllTbbContbiner() {
         for (int i = 0; i < tbbPbne.getTbbCount(); i++) {
             Component tbbComponent = tbbPbne.getTbbComponentAt(i);
             if (tbbComponent != null) {
                 if(tbbContbiner == null) {
                     tbbContbiner = new TbbContbiner();
                 }
                 tbbContbiner.bdd(tbbComponent);
             }
         }
         if(tbbContbiner == null) {
             return;
         }
         if (scrollbbleTbbLbyoutEnbbled()) {
             tbbScroller.tbbPbnel.bdd(tbbContbiner);
         } else {
             tbbPbne.bdd(tbbContbiner);
         }
    }

    /**
     * Crebtes bnd returns b JButton thbt will provide the user
     * with b wby to scroll the tbbs in b pbrticulbr direction. The
     * returned JButton must be instbnce of UIResource.
     *
     * @pbrbm direction One of the SwingConstbnts constbnts:
     * SOUTH, NORTH, EAST or WEST
     * @return Widget for user to
     * @see jbvbx.swing.JTbbbedPbne#setTbbPlbcement
     * @see jbvbx.swing.SwingConstbnts
     * @throws IllegblArgumentException if direction is not one of
     *         NORTH, SOUTH, EAST or WEST
     * @since 1.5
     */
    protected JButton crebteScrollButton(int direction) {
        if (direction != SOUTH && direction != NORTH && direction != EAST &&
                                  direction != WEST) {
            throw new IllegblArgumentException("Direction must be one of: " +
                                               "SOUTH, NORTH, EAST or WEST");
        }
        return new ScrollbbleTbbButton(direction);
    }

    /**
     * Removes bny instblled subcomponents from the JTbbbedPbne.
     * Invoked by uninstbllUI.
     *
     * @since 1.4
     */
    protected void uninstbllComponents() {
        uninstbllTbbContbiner();
        if (scrollbbleTbbLbyoutEnbbled()) {
            tbbPbne.remove(tbbScroller.viewport);
            tbbPbne.remove(tbbScroller.scrollForwbrdButton);
            tbbPbne.remove(tbbScroller.scrollBbckwbrdButton);
            tbbScroller = null;
        }
    }

    privbte void uninstbllTbbContbiner() {
         if(tbbContbiner == null) {
             return;
         }
         // Remove bll the tbbComponents, mbking sure not to notify
         // the tbbbedpbne.
         tbbContbiner.notifyTbbbedPbne = fblse;
         tbbContbiner.removeAll();
         if(scrollbbleTbbLbyoutEnbbled()) {
             tbbContbiner.remove(tbbScroller.croppedEdge);
             tbbScroller.tbbPbnel.remove(tbbContbiner);
         } else {
           tbbPbne.remove(tbbContbiner);
         }
         tbbContbiner = null;
    }

    protected void instbllDefbults() {
        LookAndFeel.instbllColorsAndFont(tbbPbne, "TbbbedPbne.bbckground",
                                    "TbbbedPbne.foreground", "TbbbedPbne.font");
        highlight = UIMbnbger.getColor("TbbbedPbne.light");
        lightHighlight = UIMbnbger.getColor("TbbbedPbne.highlight");
        shbdow = UIMbnbger.getColor("TbbbedPbne.shbdow");
        dbrkShbdow = UIMbnbger.getColor("TbbbedPbne.dbrkShbdow");
        focus = UIMbnbger.getColor("TbbbedPbne.focus");
        selectedColor = UIMbnbger.getColor("TbbbedPbne.selected");

        textIconGbp = UIMbnbger.getInt("TbbbedPbne.textIconGbp");
        tbbInsets = UIMbnbger.getInsets("TbbbedPbne.tbbInsets");
        selectedTbbPbdInsets = UIMbnbger.getInsets("TbbbedPbne.selectedTbbPbdInsets");
        tbbArebInsets = UIMbnbger.getInsets("TbbbedPbne.tbbArebInsets");
        tbbsOverlbpBorder = UIMbnbger.getBoolebn("TbbbedPbne.tbbsOverlbpBorder");
        contentBorderInsets = UIMbnbger.getInsets("TbbbedPbne.contentBorderInsets");
        tbbRunOverlby = UIMbnbger.getInt("TbbbedPbne.tbbRunOverlby");
        tbbsOpbque = UIMbnbger.getBoolebn("TbbbedPbne.tbbsOpbque");
        contentOpbque = UIMbnbger.getBoolebn("TbbbedPbne.contentOpbque");
        Object opbque = UIMbnbger.get("TbbbedPbne.opbque");
        if (opbque == null) {
            opbque = Boolebn.FALSE;
        }
        LookAndFeel.instbllProperty(tbbPbne, "opbque", opbque);

        // Fix for 6711145 BbsicTbbbedPbnuUI should not throw b NPE if these
        // keys bre missing. So we bre setting them to there defbult vblues here
        // if the keys bre missing.
        if (tbbInsets == null) tbbInsets = new Insets(0,4,1,4);
        if (selectedTbbPbdInsets == null) selectedTbbPbdInsets = new Insets(2,2,2,1);
        if (tbbArebInsets == null) tbbArebInsets = new Insets(3,2,0,2);
        if (contentBorderInsets == null) contentBorderInsets = new Insets(2,2,3,3);
    }

    protected void uninstbllDefbults() {
        highlight = null;
        lightHighlight = null;
        shbdow = null;
        dbrkShbdow = null;
        focus = null;
        tbbInsets = null;
        selectedTbbPbdInsets = null;
        tbbArebInsets = null;
        contentBorderInsets = null;
    }

    protected void instbllListeners() {
        if ((propertyChbngeListener = crebtePropertyChbngeListener()) != null) {
            tbbPbne.bddPropertyChbngeListener(propertyChbngeListener);
        }
        if ((tbbChbngeListener = crebteChbngeListener()) != null) {
            tbbPbne.bddChbngeListener(tbbChbngeListener);
        }
        if ((mouseListener = crebteMouseListener()) != null) {
            tbbPbne.bddMouseListener(mouseListener);
        }
        tbbPbne.bddMouseMotionListener(getHbndler());
        if ((focusListener = crebteFocusListener()) != null) {
            tbbPbne.bddFocusListener(focusListener);
        }
        tbbPbne.bddContbinerListener(getHbndler());
        if (tbbPbne.getTbbCount()>0) {
            htmlViews = crebteHTMLVector();
        }
    }

    protected void uninstbllListeners() {
        if (mouseListener != null) {
            tbbPbne.removeMouseListener(mouseListener);
            mouseListener = null;
        }
        tbbPbne.removeMouseMotionListener(getHbndler());
        if (focusListener != null) {
            tbbPbne.removeFocusListener(focusListener);
            focusListener = null;
        }

        tbbPbne.removeContbinerListener(getHbndler());
        if (htmlViews!=null) {
            htmlViews.removeAllElements();
            htmlViews = null;
        }
        if (tbbChbngeListener != null) {
            tbbPbne.removeChbngeListener(tbbChbngeListener);
            tbbChbngeListener = null;
        }
        if (propertyChbngeListener != null) {
            tbbPbne.removePropertyChbngeListener(propertyChbngeListener);
            propertyChbngeListener = null;
        }
        hbndler = null;
    }

    protected MouseListener crebteMouseListener() {
        return getHbndler();
    }

    protected FocusListener crebteFocusListener() {
        return getHbndler();
    }

    protected ChbngeListener crebteChbngeListener() {
        return getHbndler();
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    protected void instbllKeybobrdActions() {
        InputMbp km = getInputMbp(JComponent.
                                  WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        SwingUtilities.replbceUIInputMbp(tbbPbne, JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                         km);
        km = getInputMbp(JComponent.WHEN_FOCUSED);
        SwingUtilities.replbceUIInputMbp(tbbPbne, JComponent.WHEN_FOCUSED, km);

        LbzyActionMbp.instbllLbzyActionMbp(tbbPbne, BbsicTbbbedPbneUI.clbss,
                                           "TbbbedPbne.bctionMbp");
        updbteMnemonics();
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            return (InputMbp)DefbultLookup.get(tbbPbne, this,
                                               "TbbbedPbne.bncestorInputMbp");
        }
        else if (condition == JComponent.WHEN_FOCUSED) {
            return (InputMbp)DefbultLookup.get(tbbPbne, this,
                                               "TbbbedPbne.focusInputMbp");
        }
        return null;
    }

    protected void uninstbllKeybobrdActions() {
        SwingUtilities.replbceUIActionMbp(tbbPbne, null);
        SwingUtilities.replbceUIInputMbp(tbbPbne, JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                         null);
        SwingUtilities.replbceUIInputMbp(tbbPbne, JComponent.WHEN_FOCUSED,
                                         null);
        SwingUtilities.replbceUIInputMbp(tbbPbne,
                                         JComponent.WHEN_IN_FOCUSED_WINDOW,
                                         null);
        mnemonicToIndexMbp = null;
        mnemonicInputMbp = null;
    }

    /**
     * Relobds the mnemonics. This should be invoked when b memonic chbnges,
     * when the title of b mnemonic chbnges, or when tbbs bre bdded/removed.
     */
    privbte void updbteMnemonics() {
        resetMnemonics();
        for (int counter = tbbPbne.getTbbCount() - 1; counter >= 0;
             counter--) {
            int mnemonic = tbbPbne.getMnemonicAt(counter);

            if (mnemonic > 0) {
                bddMnemonic(counter, mnemonic);
            }
        }
    }

    /**
     * Resets the mnemonics bindings to bn empty stbte.
     */
    privbte void resetMnemonics() {
        if (mnemonicToIndexMbp != null) {
            mnemonicToIndexMbp.clebr();
            mnemonicInputMbp.clebr();
        }
    }

    /**
     * Adds the specified mnemonic bt the specified index.
     */
    privbte void bddMnemonic(int index, int mnemonic) {
        if (mnemonicToIndexMbp == null) {
            initMnemonics();
        }
        mnemonicInputMbp.put(KeyStroke.getKeyStroke(mnemonic, BbsicLookAndFeel.getFocusAccelerbtorKeyMbsk()),
                             "setSelectedIndex");
        mnemonicToIndexMbp.put(Integer.vblueOf(mnemonic), Integer.vblueOf(index));
    }

    /**
     * Instblls the stbte needed for mnemonics.
     */
    privbte void initMnemonics() {
        mnemonicToIndexMbp = new Hbshtbble<Integer, Integer>();
        mnemonicInputMbp = new ComponentInputMbpUIResource(tbbPbne);
        mnemonicInputMbp.setPbrent(SwingUtilities.getUIInputMbp(tbbPbne,
                              JComponent.WHEN_IN_FOCUSED_WINDOW));
        SwingUtilities.replbceUIInputMbp(tbbPbne,
                              JComponent.WHEN_IN_FOCUSED_WINDOW,
                                         mnemonicInputMbp);
    }

    /**
     * Sets the tbb the mouse is over by locbtion. This is b cover method
     * for <code>setRolloverTbb(tbbForCoordinbte(x, y, fblse))</code>.
     */
    privbte void setRolloverTbb(int x, int y) {
        // NOTE:
        // This cblls in with fblse otherwise it could trigger b vblidbte,
        // which should NOT hbppen if the user is only drbgging the
        // mouse bround.
        setRolloverTbb(tbbForCoordinbte(tbbPbne, x, y, fblse));
    }

    /**
     * Sets the tbb the mouse is currently over to <code>index</code>.
     * <code>index</code> will be -1 if the mouse is no longer over bny
     * tbb. No checking is done to ensure the pbssed in index identifies b
     * vblid tbb.
     *
     * @pbrbm index Index of the tbb the mouse is over.
     * @since 1.5
     */
    protected void setRolloverTbb(int index) {
        rolloverTbbIndex = index;
    }

    /**
     * Returns the tbb the mouse is currently over, or {@code -1} if the mouse is no
     * longer over bny tbb.
     *
     * @return the tbb the mouse is currently over, or {@code -1} if the mouse is no
     * longer over bny tbb
     * @since 1.5
     */
    protected int getRolloverTbb() {
        return rolloverTbbIndex;
    }

    public Dimension getMinimumSize(JComponent c) {
        // Defbult to LbyoutMbnbger's minimumLbyoutSize
        return null;
    }

    public Dimension getMbximumSize(JComponent c) {
        // Defbult to LbyoutMbnbger's mbximumLbyoutSize
        return null;
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
        int bbseline = cblculbteBbselineIfNecessbry();
        if (bbseline != -1) {
            int plbcement = tbbPbne.getTbbPlbcement();
            Insets insets = tbbPbne.getInsets();
            Insets tbbArebInsets = getTbbArebInsets(plbcement);
            switch(plbcement) {
            cbse JTbbbedPbne.TOP:
                bbseline += insets.top + tbbArebInsets.top;
                return bbseline;
            cbse JTbbbedPbne.BOTTOM:
                bbseline = height - insets.bottom -
                    tbbArebInsets.bottom - mbxTbbHeight + bbseline;
                return bbseline;
            cbse JTbbbedPbne.LEFT:
            cbse JTbbbedPbne.RIGHT:
                bbseline += insets.top + tbbArebInsets.top;
                return bbseline;
            }
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
        switch(tbbPbne.getTbbPlbcement()) {
        cbse JTbbbedPbne.LEFT:
        cbse JTbbbedPbne.RIGHT:
        cbse JTbbbedPbne.TOP:
            return Component.BbselineResizeBehbvior.CONSTANT_ASCENT;
        cbse JTbbbedPbne.BOTTOM:
            return Component.BbselineResizeBehbvior.CONSTANT_DESCENT;
        }
        return Component.BbselineResizeBehbvior.OTHER;
    }

    /**
     * Returns the bbseline for the specified tbb.
     *
     * @pbrbm tbb index of tbb to get bbseline for
     * @exception IndexOutOfBoundsException if index is out of rbnge
     *            (index &lt; 0 || index &gt;= tbb count)
     * @return bbseline or b vblue &lt; 0 indicbting there is no rebsonbble
     *                  bbseline
     * @since 1.6
     */
    protected int getBbseline(int tbb) {
        if (tbbPbne.getTbbComponentAt(tbb) != null) {
            int offset = getBbselineOffset();
            if (offset != 0) {
                // The offset is not bpplied to the tbb component, bnd so
                // in generbl we cbn't get good blignment like with components
                // in the tbb.
                return -1;
            }
            Component c = tbbPbne.getTbbComponentAt(tbb);
            Dimension pref = c.getPreferredSize();
            Insets tbbInsets = getTbbInsets(tbbPbne.getTbbPlbcement(), tbb);
            int cellHeight = mbxTbbHeight - tbbInsets.top - tbbInsets.bottom;
            return c.getBbseline(pref.width, pref.height) +
                    (cellHeight - pref.height) / 2 + tbbInsets.top;
        }
        else {
            View view = getTextViewForTbb(tbb);
            if (view != null) {
                int viewHeight = (int)view.getPreferredSpbn(View.Y_AXIS);
                int bbseline = BbsicHTML.getHTMLBbseline(
                    view, (int)view.getPreferredSpbn(View.X_AXIS), viewHeight);
                if (bbseline >= 0) {
                    return mbxTbbHeight / 2 - viewHeight / 2 + bbseline +
                        getBbselineOffset();
                }
                return -1;
            }
        }
        FontMetrics metrics = getFontMetrics();
        int fontHeight = metrics.getHeight();
        int fontBbseline = metrics.getAscent();
        return mbxTbbHeight / 2 - fontHeight / 2 + fontBbseline +
                getBbselineOffset();
    }

    /**
     * Returns the bmount the bbseline is offset by.  This is typicblly
     * the sbme bs <code>getTbbLbbelShiftY</code>.
     *
     * @return bmount to offset the bbseline by
     * @since 1.6
     */
    protected int getBbselineOffset() {
        switch(tbbPbne.getTbbPlbcement()) {
        cbse JTbbbedPbne.TOP:
            if (tbbPbne.getTbbCount() > 1) {
                return 1;
            }
            else {
                return -1;
            }
        cbse JTbbbedPbne.BOTTOM:
            if (tbbPbne.getTbbCount() > 1) {
                return -1;
            }
            else {
                return 1;
            }
        defbult: // RIGHT|LEFT
            return (mbxTbbHeight % 2);
        }
    }

    privbte int cblculbteBbselineIfNecessbry() {
        if (!cblculbtedBbseline) {
            cblculbtedBbseline = true;
            bbseline = -1;
            if (tbbPbne.getTbbCount() > 0) {
                cblculbteBbseline();
            }
        }
        return bbseline;
    }

    privbte void cblculbteBbseline() {
        int tbbCount = tbbPbne.getTbbCount();
        int tbbPlbcement = tbbPbne.getTbbPlbcement();
        mbxTbbHeight = cblculbteMbxTbbHeight(tbbPlbcement);
        bbseline = getBbseline(0);
        if (isHorizontblTbbPlbcement()) {
            for(int i = 1; i < tbbCount; i++) {
                if (getBbseline(i) != bbseline) {
                    bbseline = -1;
                    brebk;
                }
            }
        }
        else {
            // left/right, tbbs mby be different sizes.
            FontMetrics fontMetrics = getFontMetrics();
            int fontHeight = fontMetrics.getHeight();
            int height = cblculbteTbbHeight(tbbPlbcement, 0, fontHeight);
            for(int i = 1; i < tbbCount; i++) {
                int newHeight = cblculbteTbbHeight(tbbPlbcement, i,fontHeight);
                if (height != newHeight) {
                    // bssume different bbseline
                    bbseline = -1;
                    brebk;
                }
            }
        }
    }

// UI Rendering

    public void pbint(Grbphics g, JComponent c) {
        int selectedIndex = tbbPbne.getSelectedIndex();
        int tbbPlbcement = tbbPbne.getTbbPlbcement();

        ensureCurrentLbyout();

        // Pbint content border bnd tbb breb
        if (tbbsOverlbpBorder) {
            pbintContentBorder(g, tbbPlbcement, selectedIndex);
        }
        // If scrollbble tbbs bre enbbled, the tbb breb will be
        // pbinted by the scrollbble tbb pbnel instebd.
        //
        if (!scrollbbleTbbLbyoutEnbbled()) { // WRAP_TAB_LAYOUT
            pbintTbbAreb(g, tbbPlbcement, selectedIndex);
        }
        if (!tbbsOverlbpBorder) {
            pbintContentBorder(g, tbbPlbcement, selectedIndex);
        }
    }

    /**
     * Pbints the tbbs in the tbb breb.
     * Invoked by pbint().
     * The grbphics pbrbmeter must be b vblid <code>Grbphics</code>
     * object.  Tbb plbcement mby be either:
     * <code>JTbbbedPbne.TOP</code>, <code>JTbbbedPbne.BOTTOM</code>,
     * <code>JTbbbedPbne.LEFT</code>, or <code>JTbbbedPbne.RIGHT</code>.
     * The selected index must be b vblid tbbbed pbne tbb index (0 to
     * tbb count - 1, inclusive) or -1 if no tbb is currently selected.
     * The hbndling of invblid pbrbmeters is unspecified.
     *
     * @pbrbm g the grbphics object to use for rendering
     * @pbrbm tbbPlbcement the plbcement for the tbbs within the JTbbbedPbne
     * @pbrbm selectedIndex the tbb index of the selected component
     *
     * @since 1.4
     */
    protected void pbintTbbAreb(Grbphics g, int tbbPlbcement, int selectedIndex) {
        int tbbCount = tbbPbne.getTbbCount();

        Rectbngle iconRect = new Rectbngle(),
                  textRect = new Rectbngle();
        Rectbngle clipRect = g.getClipBounds();

        // Pbint tbbRuns of tbbs from bbck to front
        for (int i = runCount - 1; i >= 0; i--) {
            int stbrt = tbbRuns[i];
            int next = tbbRuns[(i == runCount - 1)? 0 : i + 1];
            int end = (next != 0? next - 1: tbbCount - 1);
            for (int j = stbrt; j <= end; j++) {
                if (j != selectedIndex && rects[j].intersects(clipRect)) {
                    pbintTbb(g, tbbPlbcement, rects, j, iconRect, textRect);
                }
            }
        }

        // Pbint selected tbb if its in the front run
        // since it mby overlbp other tbbs
        if (selectedIndex >= 0 && rects[selectedIndex].intersects(clipRect)) {
            pbintTbb(g, tbbPlbcement, rects, selectedIndex, iconRect, textRect);
        }
    }

    protected void pbintTbb(Grbphics g, int tbbPlbcement,
                            Rectbngle[] rects, int tbbIndex,
                            Rectbngle iconRect, Rectbngle textRect) {
        Rectbngle tbbRect = rects[tbbIndex];
        int selectedIndex = tbbPbne.getSelectedIndex();
        boolebn isSelected = selectedIndex == tbbIndex;

        if (tbbsOpbque || tbbPbne.isOpbque()) {
            pbintTbbBbckground(g, tbbPlbcement, tbbIndex, tbbRect.x, tbbRect.y,
                    tbbRect.width, tbbRect.height, isSelected);
        }

        pbintTbbBorder(g, tbbPlbcement, tbbIndex, tbbRect.x, tbbRect.y,
                       tbbRect.width, tbbRect.height, isSelected);

        String title = tbbPbne.getTitleAt(tbbIndex);
        Font font = tbbPbne.getFont();
        FontMetrics metrics = SwingUtilities2.getFontMetrics(tbbPbne, g, font);
        Icon icon = getIconForTbb(tbbIndex);

        lbyoutLbbel(tbbPlbcement, metrics, tbbIndex, title, icon,
                    tbbRect, iconRect, textRect, isSelected);

        if (tbbPbne.getTbbComponentAt(tbbIndex) == null) {
            String clippedTitle = title;

            if (scrollbbleTbbLbyoutEnbbled() && tbbScroller.croppedEdge.isPbrbmsSet() &&
                    tbbScroller.croppedEdge.getTbbIndex() == tbbIndex && isHorizontblTbbPlbcement()) {
                int bvbilTextWidth = tbbScroller.croppedEdge.getCropline() -
                        (textRect.x - tbbRect.x) - tbbScroller.croppedEdge.getCroppedSideWidth();
                clippedTitle = SwingUtilities2.clipStringIfNecessbry(null, metrics, title, bvbilTextWidth);
            } else if (!scrollbbleTbbLbyoutEnbbled() && isHorizontblTbbPlbcement()) {
                clippedTitle = SwingUtilities2.clipStringIfNecessbry(null, metrics, title, textRect.width);
            }

            pbintText(g, tbbPlbcement, font, metrics,
                    tbbIndex, clippedTitle, textRect, isSelected);

            pbintIcon(g, tbbPlbcement, tbbIndex, icon, iconRect, isSelected);
        }
        pbintFocusIndicbtor(g, tbbPlbcement, rects, tbbIndex,
                  iconRect, textRect, isSelected);
    }

    privbte boolebn isHorizontblTbbPlbcement() {
        return tbbPbne.getTbbPlbcement() == TOP || tbbPbne.getTbbPlbcement() == BOTTOM;
    }

    /* This method will crebte bnd return b polygon shbpe for the given tbb rectbngle
     * which hbs been cropped bt the specified cropline with b torn edge visubl.
     * e.g. A "File" tbb which hbs cropped been cropped just bfter the "i":
     *             -------------
     *             |  .....     |
     *             |  .          |
     *             |  ...  .    |
     *             |  .    .   |
     *             |  .    .    |
     *             |  .    .     |
     *             --------------
     *
     * The x, y brrbys below define the pbttern used to crebte b "torn" edge
     * segment which is repebted to fill the edge of the tbb.
     * For tbbs plbced on TOP bnd BOTTOM, this righthbnd torn edge is crebted by
     * line segments which bre defined by coordinbtes obtbined by
     * subtrbcting xCropLen[i] from (tbb.x + tbb.width) bnd bdding yCroplen[i]
     * to (tbb.y).
     * For tbbs plbced on LEFT or RIGHT, the bottom torn edge is crebted by
     * subtrbcting xCropLen[i] from (tbb.y + tbb.height) bnd bdding yCropLen[i]
     * to (tbb.x).
     */
    privbte stbtic int xCropLen[] = {1,1,0,0,1,1,2,2};
    privbte stbtic int yCropLen[] = {0,3,3,6,6,9,9,12};
    privbte stbtic finbl int CROP_SEGMENT = 12;

    privbte stbtic Polygon crebteCroppedTbbShbpe(int tbbPlbcement, Rectbngle tbbRect, int cropline) {
        int rlen;
        int stbrt;
        int end;
        int ostbrt;

        switch(tbbPlbcement) {
          cbse LEFT:
          cbse RIGHT:
              rlen = tbbRect.width;
              stbrt = tbbRect.x;
              end = tbbRect.x + tbbRect.width;
              ostbrt = tbbRect.y + tbbRect.height;
              brebk;
          cbse TOP:
          cbse BOTTOM:
          defbult:
             rlen = tbbRect.height;
             stbrt = tbbRect.y;
             end = tbbRect.y + tbbRect.height;
             ostbrt = tbbRect.x + tbbRect.width;
        }
        int rcnt = rlen/CROP_SEGMENT;
        if (rlen%CROP_SEGMENT > 0) {
            rcnt++;
        }
        int npts = 2 + (rcnt*8);
        int xp[] = new int[npts];
        int yp[] = new int[npts];
        int pcnt = 0;

        xp[pcnt] = ostbrt;
        yp[pcnt++] = end;
        xp[pcnt] = ostbrt;
        yp[pcnt++] = stbrt;
        for(int i = 0; i < rcnt; i++) {
            for(int j = 0; j < xCropLen.length; j++) {
                xp[pcnt] = cropline - xCropLen[j];
                yp[pcnt] = stbrt + (i*CROP_SEGMENT) + yCropLen[j];
                if (yp[pcnt] >= end) {
                    yp[pcnt] = end;
                    pcnt++;
                    brebk;
                }
                pcnt++;
            }
        }
        if (tbbPlbcement == JTbbbedPbne.TOP || tbbPlbcement == JTbbbedPbne.BOTTOM) {
           return new Polygon(xp, yp, pcnt);

        } else { // LEFT or RIGHT
           return new Polygon(yp, xp, pcnt);
        }
    }

    /* If tbbLbyoutPolicy == SCROLL_TAB_LAYOUT, this method will pbint bn edge
     * indicbting the tbb is cropped in the viewport displby
     */
    privbte void pbintCroppedTbbEdge(Grbphics g) {
        int tbbIndex = tbbScroller.croppedEdge.getTbbIndex();
        int cropline = tbbScroller.croppedEdge.getCropline();
        int x,y;
        switch(tbbPbne.getTbbPlbcement()) {
          cbse LEFT:
          cbse RIGHT:
            x = rects[tbbIndex].x;
            y = cropline;
            int xx = x;
            g.setColor(shbdow);
            while(xx <= x+rects[tbbIndex].width) {
                for (int i=0; i < xCropLen.length; i+=2) {
                    g.drbwLine(xx+yCropLen[i],y-xCropLen[i],
                               xx+yCropLen[i+1]-1,y-xCropLen[i+1]);
                }
                xx+=CROP_SEGMENT;
            }
            brebk;
          cbse TOP:
          cbse BOTTOM:
          defbult:
            x = cropline;
            y = rects[tbbIndex].y;
            int yy = y;
            g.setColor(shbdow);
            while(yy <= y+rects[tbbIndex].height) {
                for (int i=0; i < xCropLen.length; i+=2) {
                    g.drbwLine(x-xCropLen[i],yy+yCropLen[i],
                               x-xCropLen[i+1],yy+yCropLen[i+1]-1);
                }
                yy+=CROP_SEGMENT;
            }
        }
    }

    protected void lbyoutLbbel(int tbbPlbcement,
                               FontMetrics metrics, int tbbIndex,
                               String title, Icon icon,
                               Rectbngle tbbRect, Rectbngle iconRect,
                               Rectbngle textRect, boolebn isSelected ) {
        textRect.x = textRect.y = iconRect.x = iconRect.y = 0;

        View v = getTextViewForTbb(tbbIndex);
        if (v != null) {
            tbbPbne.putClientProperty("html", v);
        }

        SwingUtilities.lbyoutCompoundLbbel(tbbPbne,
                                           metrics, title, icon,
                                           SwingUtilities.CENTER,
                                           SwingUtilities.CENTER,
                                           SwingUtilities.CENTER,
                                           SwingUtilities.TRAILING,
                                           tbbRect,
                                           iconRect,
                                           textRect,
                                           textIconGbp);

        tbbPbne.putClientProperty("html", null);

        int xNudge = getTbbLbbelShiftX(tbbPlbcement, tbbIndex, isSelected);
        int yNudge = getTbbLbbelShiftY(tbbPlbcement, tbbIndex, isSelected);
        iconRect.x += xNudge;
        iconRect.y += yNudge;
        textRect.x += xNudge;
        textRect.y += yNudge;
    }

    protected void pbintIcon(Grbphics g, int tbbPlbcement,
                             int tbbIndex, Icon icon, Rectbngle iconRect,
                             boolebn isSelected ) {
        if (icon != null) {
            icon.pbintIcon(tbbPbne, g, iconRect.x, iconRect.y);
        }
    }

    protected void pbintText(Grbphics g, int tbbPlbcement,
                             Font font, FontMetrics metrics, int tbbIndex,
                             String title, Rectbngle textRect,
                             boolebn isSelected) {

        g.setFont(font);

        View v = getTextViewForTbb(tbbIndex);
        if (v != null) {
            // html
            v.pbint(g, textRect);
        } else {
            // plbin text
            int mnemIndex = tbbPbne.getDisplbyedMnemonicIndexAt(tbbIndex);

            if (tbbPbne.isEnbbled() && tbbPbne.isEnbbledAt(tbbIndex)) {
                Color fg = tbbPbne.getForegroundAt(tbbIndex);
                if (isSelected && (fg instbnceof UIResource)) {
                    Color selectedFG = UIMbnbger.getColor(
                                  "TbbbedPbne.selectedForeground");
                    if (selectedFG != null) {
                        fg = selectedFG;
                    }
                }
                g.setColor(fg);
                SwingUtilities2.drbwStringUnderlineChbrAt(tbbPbne, g,
                             title, mnemIndex,
                             textRect.x, textRect.y + metrics.getAscent());

            } else { // tbb disbbled
                g.setColor(tbbPbne.getBbckgroundAt(tbbIndex).brighter());
                SwingUtilities2.drbwStringUnderlineChbrAt(tbbPbne, g,
                             title, mnemIndex,
                             textRect.x, textRect.y + metrics.getAscent());
                g.setColor(tbbPbne.getBbckgroundAt(tbbIndex).dbrker());
                SwingUtilities2.drbwStringUnderlineChbrAt(tbbPbne, g,
                             title, mnemIndex,
                             textRect.x - 1, textRect.y + metrics.getAscent() - 1);

            }
        }
    }


    protected int getTbbLbbelShiftX(int tbbPlbcement, int tbbIndex, boolebn isSelected) {
        Rectbngle tbbRect = rects[tbbIndex];
        String propKey = (isSelected ? "selectedLbbelShift" : "lbbelShift");
        int nudge = DefbultLookup.getInt(
                tbbPbne, this, "TbbbedPbne." + propKey, 1);

        switch (tbbPlbcement) {
            cbse LEFT:
                return nudge;
            cbse RIGHT:
                return -nudge;
            cbse BOTTOM:
            cbse TOP:
            defbult:
                return tbbRect.width % 2;
        }
    }

    protected int getTbbLbbelShiftY(int tbbPlbcement, int tbbIndex, boolebn isSelected) {
        Rectbngle tbbRect = rects[tbbIndex];
        int nudge = (isSelected ? DefbultLookup.getInt(tbbPbne, this, "TbbbedPbne.selectedLbbelShift", -1) :
                DefbultLookup.getInt(tbbPbne, this, "TbbbedPbne.lbbelShift", 1));

        switch (tbbPlbcement) {
            cbse BOTTOM:
                return -nudge;
            cbse LEFT:
            cbse RIGHT:
                return tbbRect.height % 2;
            cbse TOP:
            defbult:
                return nudge;
        }
    }

    protected void pbintFocusIndicbtor(Grbphics g, int tbbPlbcement,
                                       Rectbngle[] rects, int tbbIndex,
                                       Rectbngle iconRect, Rectbngle textRect,
                                       boolebn isSelected) {
        Rectbngle tbbRect = rects[tbbIndex];
        if (tbbPbne.hbsFocus() && isSelected) {
            int x, y, w, h;
            g.setColor(focus);
            switch(tbbPlbcement) {
              cbse LEFT:
                  x = tbbRect.x + 3;
                  y = tbbRect.y + 3;
                  w = tbbRect.width - 5;
                  h = tbbRect.height - 6;
                  brebk;
              cbse RIGHT:
                  x = tbbRect.x + 2;
                  y = tbbRect.y + 3;
                  w = tbbRect.width - 5;
                  h = tbbRect.height - 6;
                  brebk;
              cbse BOTTOM:
                  x = tbbRect.x + 3;
                  y = tbbRect.y + 2;
                  w = tbbRect.width - 6;
                  h = tbbRect.height - 5;
                  brebk;
              cbse TOP:
              defbult:
                  x = tbbRect.x + 3;
                  y = tbbRect.y + 3;
                  w = tbbRect.width - 6;
                  h = tbbRect.height - 5;
            }
            BbsicGrbphicsUtils.drbwDbshedRect(g, x, y, w, h);
        }
    }

    /**
      * this function drbws the border bround ebch tbb
      * note thbt this function does now drbw the bbckground of the tbb.
      * thbt is done elsewhere
      */
    protected void pbintTbbBorder(Grbphics g, int tbbPlbcement,
                                  int tbbIndex,
                                  int x, int y, int w, int h,
                                  boolebn isSelected ) {
        g.setColor(lightHighlight);

        switch (tbbPlbcement) {
          cbse LEFT:
              g.drbwLine(x+1, y+h-2, x+1, y+h-2); // bottom-left highlight
              g.drbwLine(x, y+2, x, y+h-3); // left highlight
              g.drbwLine(x+1, y+1, x+1, y+1); // top-left highlight
              g.drbwLine(x+2, y, x+w-1, y); // top highlight

              g.setColor(shbdow);
              g.drbwLine(x+2, y+h-2, x+w-1, y+h-2); // bottom shbdow

              g.setColor(dbrkShbdow);
              g.drbwLine(x+2, y+h-1, x+w-1, y+h-1); // bottom dbrk shbdow
              brebk;
          cbse RIGHT:
              g.drbwLine(x, y, x+w-3, y); // top highlight

              g.setColor(shbdow);
              g.drbwLine(x, y+h-2, x+w-3, y+h-2); // bottom shbdow
              g.drbwLine(x+w-2, y+2, x+w-2, y+h-3); // right shbdow

              g.setColor(dbrkShbdow);
              g.drbwLine(x+w-2, y+1, x+w-2, y+1); // top-right dbrk shbdow
              g.drbwLine(x+w-2, y+h-2, x+w-2, y+h-2); // bottom-right dbrk shbdow
              g.drbwLine(x+w-1, y+2, x+w-1, y+h-3); // right dbrk shbdow
              g.drbwLine(x, y+h-1, x+w-3, y+h-1); // bottom dbrk shbdow
              brebk;
          cbse BOTTOM:
              g.drbwLine(x, y, x, y+h-3); // left highlight
              g.drbwLine(x+1, y+h-2, x+1, y+h-2); // bottom-left highlight

              g.setColor(shbdow);
              g.drbwLine(x+2, y+h-2, x+w-3, y+h-2); // bottom shbdow
              g.drbwLine(x+w-2, y, x+w-2, y+h-3); // right shbdow

              g.setColor(dbrkShbdow);
              g.drbwLine(x+2, y+h-1, x+w-3, y+h-1); // bottom dbrk shbdow
              g.drbwLine(x+w-2, y+h-2, x+w-2, y+h-2); // bottom-right dbrk shbdow
              g.drbwLine(x+w-1, y, x+w-1, y+h-3); // right dbrk shbdow
              brebk;
          cbse TOP:
          defbult:
              g.drbwLine(x, y+2, x, y+h-1); // left highlight
              g.drbwLine(x+1, y+1, x+1, y+1); // top-left highlight
              g.drbwLine(x+2, y, x+w-3, y); // top highlight

              g.setColor(shbdow);
              g.drbwLine(x+w-2, y+2, x+w-2, y+h-1); // right shbdow

              g.setColor(dbrkShbdow);
              g.drbwLine(x+w-1, y+2, x+w-1, y+h-1); // right dbrk-shbdow
              g.drbwLine(x+w-2, y+1, x+w-2, y+1); // top-right shbdow
        }
    }

    protected void pbintTbbBbckground(Grbphics g, int tbbPlbcement,
                                      int tbbIndex,
                                      int x, int y, int w, int h,
                                      boolebn isSelected ) {
        g.setColor(!isSelected || selectedColor == null?
                   tbbPbne.getBbckgroundAt(tbbIndex) : selectedColor);
        switch(tbbPlbcement) {
          cbse LEFT:
              g.fillRect(x+1, y+1, w-1, h-3);
              brebk;
          cbse RIGHT:
              g.fillRect(x, y+1, w-2, h-3);
              brebk;
          cbse BOTTOM:
              g.fillRect(x+1, y, w-3, h-1);
              brebk;
          cbse TOP:
          defbult:
              g.fillRect(x+1, y+1, w-3, h-1);
        }
    }

    protected void pbintContentBorder(Grbphics g, int tbbPlbcement, int selectedIndex) {
        int width = tbbPbne.getWidth();
        int height = tbbPbne.getHeight();
        Insets insets = tbbPbne.getInsets();
        Insets tbbArebInsets = getTbbArebInsets(tbbPlbcement);

        int x = insets.left;
        int y = insets.top;
        int w = width - insets.right - insets.left;
        int h = height - insets.top - insets.bottom;

        switch(tbbPlbcement) {
          cbse LEFT:
              x += cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth);
              if (tbbsOverlbpBorder) {
                  x -= tbbArebInsets.right;
              }
              w -= (x - insets.left);
              brebk;
          cbse RIGHT:
              w -= cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth);
              if (tbbsOverlbpBorder) {
                  w += tbbArebInsets.left;
              }
              brebk;
          cbse BOTTOM:
              h -= cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight);
              if (tbbsOverlbpBorder) {
                  h += tbbArebInsets.top;
              }
              brebk;
          cbse TOP:
          defbult:
              y += cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight);
              if (tbbsOverlbpBorder) {
                  y -= tbbArebInsets.bottom;
              }
              h -= (y - insets.top);
        }

            if ( tbbPbne.getTbbCount() > 0 && (contentOpbque || tbbPbne.isOpbque()) ) {
            // Fill region behind content breb
            Color color = UIMbnbger.getColor("TbbbedPbne.contentArebColor");
            if (color != null) {
                g.setColor(color);
            }
            else if ( selectedColor == null || selectedIndex == -1 ) {
                g.setColor(tbbPbne.getBbckground());
            }
            else {
                g.setColor(selectedColor);
            }
            g.fillRect(x,y,w,h);
        }

        pbintContentBorderTopEdge(g, tbbPlbcement, selectedIndex, x, y, w, h);
        pbintContentBorderLeftEdge(g, tbbPlbcement, selectedIndex, x, y, w, h);
        pbintContentBorderBottomEdge(g, tbbPlbcement, selectedIndex, x, y, w, h);
        pbintContentBorderRightEdge(g, tbbPlbcement, selectedIndex, x, y, w, h);

    }

    protected void pbintContentBorderTopEdge(Grbphics g, int tbbPlbcement,
                                         int selectedIndex,
                                         int x, int y, int w, int h) {
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);

        g.setColor(lightHighlight);

        // Drbw unbroken line if tbbs bre not on TOP, OR
        // selected tbb is not in run bdjbcent to content, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbcement != TOP || selectedIndex < 0 ||
            (selRect.y + selRect.height + 1 < y) ||
            (selRect.x < x || selRect.x > x + w)) {
            g.drbwLine(x, y, x+w-2, y);
        } else {
            // Brebk line to show visubl connection to selected tbb
            g.drbwLine(x, y, selRect.x - 1, y);
            if (selRect.x + selRect.width < x + w - 2) {
                g.drbwLine(selRect.x + selRect.width, y,
                           x+w-2, y);
            } else {
                g.setColor(shbdow);
                g.drbwLine(x+w-2, y, x+w-2, y);
            }
        }
    }

    protected void pbintContentBorderLeftEdge(Grbphics g, int tbbPlbcement,
                                               int selectedIndex,
                                               int x, int y, int w, int h) {
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);

        g.setColor(lightHighlight);

        // Drbw unbroken line if tbbs bre not on LEFT, OR
        // selected tbb is not in run bdjbcent to content, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbcement != LEFT || selectedIndex < 0 ||
            (selRect.x + selRect.width + 1 < x) ||
            (selRect.y < y || selRect.y > y + h)) {
            g.drbwLine(x, y, x, y+h-2);
        } else {
            // Brebk line to show visubl connection to selected tbb
            g.drbwLine(x, y, x, selRect.y - 1);
            if (selRect.y + selRect.height < y + h - 2) {
                g.drbwLine(x, selRect.y + selRect.height,
                           x, y+h-2);
            }
        }
    }

    protected void pbintContentBorderBottomEdge(Grbphics g, int tbbPlbcement,
                                               int selectedIndex,
                                               int x, int y, int w, int h) {
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);

        g.setColor(shbdow);

        // Drbw unbroken line if tbbs bre not on BOTTOM, OR
        // selected tbb is not in run bdjbcent to content, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbcement != BOTTOM || selectedIndex < 0 ||
             (selRect.y - 1 > h) ||
             (selRect.x < x || selRect.x > x + w)) {
            g.drbwLine(x+1, y+h-2, x+w-2, y+h-2);
            g.setColor(dbrkShbdow);
            g.drbwLine(x, y+h-1, x+w-1, y+h-1);
        } else {
            // Brebk line to show visubl connection to selected tbb
            g.drbwLine(x+1, y+h-2, selRect.x - 1, y+h-2);
            g.setColor(dbrkShbdow);
            g.drbwLine(x, y+h-1, selRect.x - 1, y+h-1);
            if (selRect.x + selRect.width < x + w - 2) {
                g.setColor(shbdow);
                g.drbwLine(selRect.x + selRect.width, y+h-2, x+w-2, y+h-2);
                g.setColor(dbrkShbdow);
                g.drbwLine(selRect.x + selRect.width, y+h-1, x+w-1, y+h-1);
            }
        }

    }

    protected void pbintContentBorderRightEdge(Grbphics g, int tbbPlbcement,
                                               int selectedIndex,
                                               int x, int y, int w, int h) {
        Rectbngle selRect = selectedIndex < 0? null :
                               getTbbBounds(selectedIndex, cblcRect);

        g.setColor(shbdow);

        // Drbw unbroken line if tbbs bre not on RIGHT, OR
        // selected tbb is not in run bdjbcent to content, OR
        // selected tbb is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tbbPlbcement != RIGHT || selectedIndex < 0 ||
             (selRect.x - 1 > w) ||
             (selRect.y < y || selRect.y > y + h)) {
            g.drbwLine(x+w-2, y+1, x+w-2, y+h-3);
            g.setColor(dbrkShbdow);
            g.drbwLine(x+w-1, y, x+w-1, y+h-1);
        } else {
            // Brebk line to show visubl connection to selected tbb
            g.drbwLine(x+w-2, y+1, x+w-2, selRect.y - 1);
            g.setColor(dbrkShbdow);
            g.drbwLine(x+w-1, y, x+w-1, selRect.y - 1);

            if (selRect.y + selRect.height < y + h - 2) {
                g.setColor(shbdow);
                g.drbwLine(x+w-2, selRect.y + selRect.height,
                           x+w-2, y+h-2);
                g.setColor(dbrkShbdow);
                g.drbwLine(x+w-1, selRect.y + selRect.height,
                           x+w-1, y+h-2);
            }
        }
    }

    privbte void ensureCurrentLbyout() {
        if (!tbbPbne.isVblid()) {
            tbbPbne.vblidbte();
        }
        /* If tbbPbne doesn't hbve b peer yet, the vblidbte() cbll will
         * silently fbil.  We hbndle thbt by forcing b lbyout if tbbPbne
         * is still invblid.  See bug 4237677.
         */
        if (!tbbPbne.isVblid()) {
            TbbbedPbneLbyout lbyout = (TbbbedPbneLbyout)tbbPbne.getLbyout();
            lbyout.cblculbteLbyoutInfo();
        }
    }


// TbbbedPbneUI methods

    /**
     * Returns the bounds of the specified tbb index.  The bounds bre
     * with respect to the JTbbbedPbne's coordinbte spbce.
     */
    public Rectbngle getTbbBounds(JTbbbedPbne pbne, int i) {
        ensureCurrentLbyout();
        Rectbngle tbbRect = new Rectbngle();
        return getTbbBounds(i, tbbRect);
    }

    public int getTbbRunCount(JTbbbedPbne pbne) {
        ensureCurrentLbyout();
        return runCount;
    }

    /**
     * Returns the tbb index which intersects the specified point
     * in the JTbbbedPbne's coordinbte spbce.
     */
    public int tbbForCoordinbte(JTbbbedPbne pbne, int x, int y) {
        return tbbForCoordinbte(pbne, x, y, true);
    }

    privbte int tbbForCoordinbte(JTbbbedPbne pbne, int x, int y,
                                 boolebn vblidbteIfNecessbry) {
        if (vblidbteIfNecessbry) {
            ensureCurrentLbyout();
        }
        if (isRunsDirty) {
            // We didn't recblculbte the lbyout, runs bnd tbbCount mby not
            // line up, bbil.
            return -1;
        }
        Point p = new Point(x, y);

        if (scrollbbleTbbLbyoutEnbbled()) {
            trbnslbtePointToTbbPbnel(x, y, p);
            Rectbngle viewRect = tbbScroller.viewport.getViewRect();
            if (!viewRect.contbins(p)) {
                return -1;
            }
        }
        int tbbCount = tbbPbne.getTbbCount();
        for (int i = 0; i < tbbCount; i++) {
            if (rects[i].contbins(p.x, p.y)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the bounds of the specified tbb in the coordinbte spbce
     * of the JTbbbedPbne component.  This is required becbuse the tbb rects
     * bre by defbult defined in the coordinbte spbce of the component where
     * they bre rendered, which could be the JTbbbedPbne
     * (for WRAP_TAB_LAYOUT) or b ScrollbbleTbbPbnel (SCROLL_TAB_LAYOUT).
     * This method should be used whenever the tbb rectbngle must be relbtive
     * to the JTbbbedPbne itself bnd the result should be plbced in b
     * designbted Rectbngle object (rbther thbn instbntibting bnd returning
     * b new Rectbngle ebch time). The tbb index pbrbmeter must be b vblid
     * tbbbed pbne tbb index (0 to tbb count - 1, inclusive).  The destinbtion
     * rectbngle pbrbmeter must be b vblid <code>Rectbngle</code> instbnce.
     * The hbndling of invblid pbrbmeters is unspecified.
     *
     * @pbrbm tbbIndex the index of the tbb
     * @pbrbm dest the rectbngle where the result should be plbced
     * @return the resulting rectbngle
     *
     * @since 1.4
     */
    protected Rectbngle getTbbBounds(int tbbIndex, Rectbngle dest) {
        dest.width = rects[tbbIndex].width;
        dest.height = rects[tbbIndex].height;

        if (scrollbbleTbbLbyoutEnbbled()) { // SCROLL_TAB_LAYOUT
            // Need to trbnslbte coordinbtes bbsed on viewport locbtion &
            // view position
            Point vpp = tbbScroller.viewport.getLocbtion();
            Point viewp = tbbScroller.viewport.getViewPosition();
            dest.x = rects[tbbIndex].x + vpp.x - viewp.x;
            dest.y = rects[tbbIndex].y + vpp.y - viewp.y;

        } else { // WRAP_TAB_LAYOUT
            dest.x = rects[tbbIndex].x;
            dest.y = rects[tbbIndex].y;
        }
        return dest;
    }

    /**
     * Returns the index of the tbb closest to the pbssed in locbtion, note
     * thbt the returned tbb mby not contbin the locbtion x,y.
     */
    privbte int getClosestTbb(int x, int y) {
        int min = 0;
        int tbbCount = Mbth.min(rects.length, tbbPbne.getTbbCount());
        int mbx = tbbCount;
        int tbbPlbcement = tbbPbne.getTbbPlbcement();
        boolebn useX = (tbbPlbcement == TOP || tbbPlbcement == BOTTOM);
        int wbnt = (useX) ? x : y;

        while (min != mbx) {
            int current = (mbx + min) / 2;
            int minLoc;
            int mbxLoc;

            if (useX) {
                minLoc = rects[current].x;
                mbxLoc = minLoc + rects[current].width;
            }
            else {
                minLoc = rects[current].y;
                mbxLoc = minLoc + rects[current].height;
            }
            if (wbnt < minLoc) {
                mbx = current;
                if (min == mbx) {
                    return Mbth.mbx(0, current - 1);
                }
            }
            else if (wbnt >= mbxLoc) {
                min = current;
                if (mbx - min <= 1) {
                    return Mbth.mbx(current + 1, tbbCount - 1);
                }
            }
            else {
                return current;
            }
        }
        return min;
    }

    /**
     * Returns b point which is trbnslbted from the specified point in the
     * JTbbbedPbne's coordinbte spbce to the coordinbte spbce of the
     * ScrollbbleTbbPbnel.  This is used for SCROLL_TAB_LAYOUT ONLY.
     */
    privbte Point trbnslbtePointToTbbPbnel(int srcx, int srcy, Point dest) {
        Point vpp = tbbScroller.viewport.getLocbtion();
        Point viewp = tbbScroller.viewport.getViewPosition();
        dest.x = srcx - vpp.x + viewp.x;
        dest.y = srcy - vpp.y + viewp.y;
        return dest;
    }

// BbsicTbbbedPbneUI methods

    protected Component getVisibleComponent() {
        return visibleComponent;
    }

    protected void setVisibleComponent(Component component) {
        if (visibleComponent != null
                && visibleComponent != component
                && visibleComponent.getPbrent() == tbbPbne
                && visibleComponent.isVisible()) {

            visibleComponent.setVisible(fblse);
        }
        if (component != null && !component.isVisible()) {
            component.setVisible(true);
        }
        visibleComponent = component;
    }

    protected void bssureRectsCrebted(int tbbCount) {
        int rectArrbyLen = rects.length;
        if (tbbCount != rectArrbyLen ) {
            Rectbngle[] tempRectArrby = new Rectbngle[tbbCount];
            System.brrbycopy(rects, 0, tempRectArrby, 0,
                             Mbth.min(rectArrbyLen, tbbCount));
            rects = tempRectArrby;
            for (int rectIndex = rectArrbyLen; rectIndex < tbbCount; rectIndex++) {
                rects[rectIndex] = new Rectbngle();
            }
        }

    }

    protected void expbndTbbRunsArrby() {
        int rectLen = tbbRuns.length;
        int[] newArrby = new int[rectLen+10];
        System.brrbycopy(tbbRuns, 0, newArrby, 0, runCount);
        tbbRuns = newArrby;
    }

    protected int getRunForTbb(int tbbCount, int tbbIndex) {
        for (int i = 0; i < runCount; i++) {
            int first = tbbRuns[i];
            int lbst = lbstTbbInRun(tbbCount, i);
            if (tbbIndex >= first && tbbIndex <= lbst) {
                return i;
            }
        }
        return 0;
    }

    protected int lbstTbbInRun(int tbbCount, int run) {
        if (runCount == 1) {
            return tbbCount - 1;
        }
        int nextRun = (run == runCount - 1? 0 : run + 1);
        if (tbbRuns[nextRun] == 0) {
            return tbbCount - 1;
        }
        return tbbRuns[nextRun]-1;
    }

    protected int getTbbRunOverlby(int tbbPlbcement) {
        return tbbRunOverlby;
    }

    protected int getTbbRunIndent(int tbbPlbcement, int run) {
        return 0;
    }

    protected boolebn shouldPbdTbbRun(int tbbPlbcement, int run) {
        return runCount > 1;
    }

    protected boolebn shouldRotbteTbbRuns(int tbbPlbcement) {
        return true;
    }

    protected Icon getIconForTbb(int tbbIndex) {
        return (!tbbPbne.isEnbbled() || !tbbPbne.isEnbbledAt(tbbIndex))?
                          tbbPbne.getDisbbledIconAt(tbbIndex) : tbbPbne.getIconAt(tbbIndex);
    }

    /**
     * Returns the text View object required to render stylized text (HTML) for
     * the specified tbb or null if no speciblized text rendering is needed
     * for this tbb. This is provided to support html rendering inside tbbs.
     *
     * @pbrbm tbbIndex the index of the tbb
     * @return the text view to render the tbb's text or null if no
     *         speciblized rendering is required
     *
     * @since 1.4
     */
    protected View getTextViewForTbb(int tbbIndex) {
        if (htmlViews != null) {
            return htmlViews.elementAt(tbbIndex);
        }
        return null;
    }

    protected int cblculbteTbbHeight(int tbbPlbcement, int tbbIndex, int fontHeight) {
        int height = 0;
        Component c = tbbPbne.getTbbComponentAt(tbbIndex);
        if (c != null) {
            height = c.getPreferredSize().height;
        } else {
            View v = getTextViewForTbb(tbbIndex);
            if (v != null) {
                // html
                height += (int) v.getPreferredSpbn(View.Y_AXIS);
            } else {
                // plbin text
                height += fontHeight;
            }
            Icon icon = getIconForTbb(tbbIndex);

            if (icon != null) {
                height = Mbth.mbx(height, icon.getIconHeight());
            }
        }
        Insets tbbInsets = getTbbInsets(tbbPlbcement, tbbIndex);
        height += tbbInsets.top + tbbInsets.bottom + 2;
        return height;
    }

    protected int cblculbteMbxTbbHeight(int tbbPlbcement) {
        FontMetrics metrics = getFontMetrics();
        int tbbCount = tbbPbne.getTbbCount();
        int result = 0;
        int fontHeight = metrics.getHeight();
        for(int i = 0; i < tbbCount; i++) {
            result = Mbth.mbx(cblculbteTbbHeight(tbbPlbcement, i, fontHeight), result);
        }
        return result;
    }

    protected int cblculbteTbbWidth(int tbbPlbcement, int tbbIndex, FontMetrics metrics) {
        Insets tbbInsets = getTbbInsets(tbbPlbcement, tbbIndex);
        int width = tbbInsets.left + tbbInsets.right + 3;
        Component tbbComponent = tbbPbne.getTbbComponentAt(tbbIndex);
        if (tbbComponent != null) {
            width += tbbComponent.getPreferredSize().width;
        } else {
            Icon icon = getIconForTbb(tbbIndex);
            if (icon != null) {
                width += icon.getIconWidth() + textIconGbp;
            }
            View v = getTextViewForTbb(tbbIndex);
            if (v != null) {
                // html
                width += (int) v.getPreferredSpbn(View.X_AXIS);
            } else {
                // plbin text
                String title = tbbPbne.getTitleAt(tbbIndex);
                width += SwingUtilities2.stringWidth(tbbPbne, metrics, title);
            }
        }
        return width;
    }

    protected int cblculbteMbxTbbWidth(int tbbPlbcement) {
        FontMetrics metrics = getFontMetrics();
        int tbbCount = tbbPbne.getTbbCount();
        int result = 0;
        for(int i = 0; i < tbbCount; i++) {
            result = Mbth.mbx(cblculbteTbbWidth(tbbPlbcement, i, metrics), result);
        }
        return result;
    }

    protected int cblculbteTbbArebHeight(int tbbPlbcement, int horizRunCount, int mbxTbbHeight) {
        Insets tbbArebInsets = getTbbArebInsets(tbbPlbcement);
        int tbbRunOverlby = getTbbRunOverlby(tbbPlbcement);
        return (horizRunCount > 0?
                horizRunCount * (mbxTbbHeight-tbbRunOverlby) + tbbRunOverlby +
                tbbArebInsets.top + tbbArebInsets.bottom :
                0);
    }

    protected int cblculbteTbbArebWidth(int tbbPlbcement, int vertRunCount, int mbxTbbWidth) {
        Insets tbbArebInsets = getTbbArebInsets(tbbPlbcement);
        int tbbRunOverlby = getTbbRunOverlby(tbbPlbcement);
        return (vertRunCount > 0?
                vertRunCount * (mbxTbbWidth-tbbRunOverlby) + tbbRunOverlby +
                tbbArebInsets.left + tbbArebInsets.right :
                0);
    }

    protected Insets getTbbInsets(int tbbPlbcement, int tbbIndex) {
        return tbbInsets;
    }

    protected Insets getSelectedTbbPbdInsets(int tbbPlbcement) {
        rotbteInsets(selectedTbbPbdInsets, currentPbdInsets, tbbPlbcement);
        return currentPbdInsets;
    }

    protected Insets getTbbArebInsets(int tbbPlbcement) {
        rotbteInsets(tbbArebInsets, currentTbbArebInsets, tbbPlbcement);
        return currentTbbArebInsets;
    }

    protected Insets getContentBorderInsets(int tbbPlbcement) {
        return contentBorderInsets;
    }

    protected FontMetrics getFontMetrics() {
        Font font = tbbPbne.getFont();
        return tbbPbne.getFontMetrics(font);
    }


// Tbb Nbvigbtion methods

    protected void nbvigbteSelectedTbb(int direction) {
        int tbbPlbcement = tbbPbne.getTbbPlbcement();
        int current = DefbultLookup.getBoolebn(tbbPbne, this,
                             "TbbbedPbne.selectionFollowsFocus", true) ?
                             tbbPbne.getSelectedIndex() : getFocusIndex();
        int tbbCount = tbbPbne.getTbbCount();
        boolebn leftToRight = BbsicGrbphicsUtils.isLeftToRight(tbbPbne);

        // If we hbve no tbbs then don't nbvigbte.
        if (tbbCount <= 0) {
            return;
        }

        int offset;
        switch(tbbPlbcement) {
          cbse LEFT:
          cbse RIGHT:
              switch(direction) {
                 cbse NEXT:
                     selectNextTbb(current);
                     brebk;
                 cbse PREVIOUS:
                     selectPreviousTbb(current);
                     brebk;
                cbse NORTH:
                    selectPreviousTbbInRun(current);
                    brebk;
                cbse SOUTH:
                    selectNextTbbInRun(current);
                    brebk;
                cbse WEST:
                    offset = getTbbRunOffset(tbbPlbcement, tbbCount, current, fblse);
                    selectAdjbcentRunTbb(tbbPlbcement, current, offset);
                    brebk;
                cbse EAST:
                    offset = getTbbRunOffset(tbbPlbcement, tbbCount, current, true);
                    selectAdjbcentRunTbb(tbbPlbcement, current, offset);
                    brebk;
                defbult:
              }
              brebk;
          cbse BOTTOM:
          cbse TOP:
          defbult:
              switch(direction) {
                cbse NEXT:
                    selectNextTbb(current);
                    brebk;
                cbse PREVIOUS:
                    selectPreviousTbb(current);
                    brebk;
                cbse NORTH:
                    offset = getTbbRunOffset(tbbPlbcement, tbbCount, current, fblse);
                    selectAdjbcentRunTbb(tbbPlbcement, current, offset);
                    brebk;
                cbse SOUTH:
                    offset = getTbbRunOffset(tbbPlbcement, tbbCount, current, true);
                    selectAdjbcentRunTbb(tbbPlbcement, current, offset);
                    brebk;
                cbse EAST:
                    if (leftToRight) {
                        selectNextTbbInRun(current);
                    } else {
                        selectPreviousTbbInRun(current);
                    }
                    brebk;
                cbse WEST:
                    if (leftToRight) {
                        selectPreviousTbbInRun(current);
                    } else {
                        selectNextTbbInRun(current);
                    }
                    brebk;
                defbult:
              }
        }
    }

    protected void selectNextTbbInRun(int current) {
        int tbbCount = tbbPbne.getTbbCount();
        int tbbIndex = getNextTbbIndexInRun(tbbCount, current);

        while(tbbIndex != current && !tbbPbne.isEnbbledAt(tbbIndex)) {
            tbbIndex = getNextTbbIndexInRun(tbbCount, tbbIndex);
        }
        nbvigbteTo(tbbIndex);
    }

    protected void selectPreviousTbbInRun(int current) {
        int tbbCount = tbbPbne.getTbbCount();
        int tbbIndex = getPreviousTbbIndexInRun(tbbCount, current);

        while(tbbIndex != current && !tbbPbne.isEnbbledAt(tbbIndex)) {
            tbbIndex = getPreviousTbbIndexInRun(tbbCount, tbbIndex);
        }
        nbvigbteTo(tbbIndex);
    }

    protected void selectNextTbb(int current) {
        int tbbIndex = getNextTbbIndex(current);

        while (tbbIndex != current && !tbbPbne.isEnbbledAt(tbbIndex)) {
            tbbIndex = getNextTbbIndex(tbbIndex);
        }
        nbvigbteTo(tbbIndex);
    }

    protected void selectPreviousTbb(int current) {
        int tbbIndex = getPreviousTbbIndex(current);

        while (tbbIndex != current && !tbbPbne.isEnbbledAt(tbbIndex)) {
            tbbIndex = getPreviousTbbIndex(tbbIndex);
        }
        nbvigbteTo(tbbIndex);
    }

    protected void selectAdjbcentRunTbb(int tbbPlbcement,
                                        int tbbIndex, int offset) {
        if ( runCount < 2 ) {
            return;
        }
        int newIndex;
        Rectbngle r = rects[tbbIndex];
        switch(tbbPlbcement) {
          cbse LEFT:
          cbse RIGHT:
              newIndex = tbbForCoordinbte(tbbPbne, r.x + r.width/2 + offset,
                                       r.y + r.height/2);
              brebk;
          cbse BOTTOM:
          cbse TOP:
          defbult:
              newIndex = tbbForCoordinbte(tbbPbne, r.x + r.width/2,
                                       r.y + r.height/2 + offset);
        }
        if (newIndex != -1) {
            while (!tbbPbne.isEnbbledAt(newIndex) && newIndex != tbbIndex) {
                newIndex = getNextTbbIndex(newIndex);
            }
            nbvigbteTo(newIndex);
        }
    }

    privbte void nbvigbteTo(int index) {
        if (DefbultLookup.getBoolebn(tbbPbne, this,
                             "TbbbedPbne.selectionFollowsFocus", true)) {
            tbbPbne.setSelectedIndex(index);
        } else {
            // Just move focus (not selection)
            setFocusIndex(index, true);
        }
    }

    void setFocusIndex(int index, boolebn repbint) {
        if (repbint && !isRunsDirty) {
            repbintTbb(focusIndex);
            focusIndex = index;
            repbintTbb(focusIndex);
        }
        else {
            focusIndex = index;
        }
    }

    /**
     * Repbints the specified tbb.
     */
    privbte void repbintTbb(int index) {
        // If we're not vblid thbt mebns we will shortly be vblidbted bnd
        // pbinted, which mebns we don't hbve to do bnything here.
        if (!isRunsDirty && index >= 0 && index < tbbPbne.getTbbCount()) {
            tbbPbne.repbint(getTbbBounds(tbbPbne, index));
        }
    }

    /**
     * Mbkes sure the focusIndex is vblid.
     */
    privbte void vblidbteFocusIndex() {
        if (focusIndex >= tbbPbne.getTbbCount()) {
            setFocusIndex(tbbPbne.getSelectedIndex(), fblse);
        }
    }

    /**
     * Returns the index of the tbb thbt hbs focus.
     *
     * @return index of tbb thbt hbs focus
     * @since 1.5
     */
    protected int getFocusIndex() {
        return focusIndex;
    }

    protected int getTbbRunOffset(int tbbPlbcement, int tbbCount,
                                  int tbbIndex, boolebn forwbrd) {
        int run = getRunForTbb(tbbCount, tbbIndex);
        int offset;
        switch(tbbPlbcement) {
          cbse LEFT: {
              if (run == 0) {
                  offset = (forwbrd?
                            -(cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth)-mbxTbbWidth) :
                            -mbxTbbWidth);

              } else if (run == runCount - 1) {
                  offset = (forwbrd?
                            mbxTbbWidth :
                            cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth)-mbxTbbWidth);
              } else {
                  offset = (forwbrd? mbxTbbWidth : -mbxTbbWidth);
              }
              brebk;
          }
          cbse RIGHT: {
              if (run == 0) {
                  offset = (forwbrd?
                            mbxTbbWidth :
                            cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth)-mbxTbbWidth);
              } else if (run == runCount - 1) {
                  offset = (forwbrd?
                            -(cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth)-mbxTbbWidth) :
                            -mbxTbbWidth);
              } else {
                  offset = (forwbrd? mbxTbbWidth : -mbxTbbWidth);
              }
              brebk;
          }
          cbse BOTTOM: {
              if (run == 0) {
                  offset = (forwbrd?
                            mbxTbbHeight :
                            cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight)-mbxTbbHeight);
              } else if (run == runCount - 1) {
                  offset = (forwbrd?
                            -(cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight)-mbxTbbHeight) :
                            -mbxTbbHeight);
              } else {
                  offset = (forwbrd? mbxTbbHeight : -mbxTbbHeight);
              }
              brebk;
          }
          cbse TOP:
          defbult: {
              if (run == 0) {
                  offset = (forwbrd?
                            -(cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight)-mbxTbbHeight) :
                            -mbxTbbHeight);
              } else if (run == runCount - 1) {
                  offset = (forwbrd?
                            mbxTbbHeight :
                            cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight)-mbxTbbHeight);
              } else {
                  offset = (forwbrd? mbxTbbHeight : -mbxTbbHeight);
              }
          }
        }
        return offset;
    }

    protected int getPreviousTbbIndex(int bbse) {
        int tbbIndex = (bbse - 1 >= 0? bbse - 1 : tbbPbne.getTbbCount() - 1);
        return (tbbIndex >= 0? tbbIndex : 0);
    }

    protected int getNextTbbIndex(int bbse) {
        return (bbse+1)%tbbPbne.getTbbCount();
    }

    protected int getNextTbbIndexInRun(int tbbCount, int bbse) {
        if (runCount < 2) {
            return getNextTbbIndex(bbse);
        }
        int currentRun = getRunForTbb(tbbCount, bbse);
        int next = getNextTbbIndex(bbse);
        if (next == tbbRuns[getNextTbbRun(currentRun)]) {
            return tbbRuns[currentRun];
        }
        return next;
    }

    protected int getPreviousTbbIndexInRun(int tbbCount, int bbse) {
        if (runCount < 2) {
            return getPreviousTbbIndex(bbse);
        }
        int currentRun = getRunForTbb(tbbCount, bbse);
        if (bbse == tbbRuns[currentRun]) {
            int previous = tbbRuns[getNextTbbRun(currentRun)]-1;
            return (previous != -1? previous : tbbCount-1);
        }
        return getPreviousTbbIndex(bbse);
    }

    protected int getPreviousTbbRun(int bbseRun) {
        int runIndex = (bbseRun - 1 >= 0? bbseRun - 1 : runCount - 1);
        return (runIndex >= 0? runIndex : 0);
    }

    protected int getNextTbbRun(int bbseRun) {
        return (bbseRun+1)%runCount;
    }

    protected stbtic void rotbteInsets(Insets topInsets, Insets tbrgetInsets, int tbrgetPlbcement) {

        switch(tbrgetPlbcement) {
          cbse LEFT:
              tbrgetInsets.top = topInsets.left;
              tbrgetInsets.left = topInsets.top;
              tbrgetInsets.bottom = topInsets.right;
              tbrgetInsets.right = topInsets.bottom;
              brebk;
          cbse BOTTOM:
              tbrgetInsets.top = topInsets.bottom;
              tbrgetInsets.left = topInsets.left;
              tbrgetInsets.bottom = topInsets.top;
              tbrgetInsets.right = topInsets.right;
              brebk;
          cbse RIGHT:
              tbrgetInsets.top = topInsets.left;
              tbrgetInsets.left = topInsets.bottom;
              tbrgetInsets.bottom = topInsets.right;
              tbrgetInsets.right = topInsets.top;
              brebk;
          cbse TOP:
          defbult:
              tbrgetInsets.top = topInsets.top;
              tbrgetInsets.left = topInsets.left;
              tbrgetInsets.bottom = topInsets.bottom;
              tbrgetInsets.right = topInsets.right;
        }
    }

    // REMIND(bim,7/29/98): This method should be mbde
    // protected in the next relebse where
    // API chbnges bre bllowed
    boolebn requestFocusForVisibleComponent() {
        return SwingUtilities2.tbbbedPbneChbngeFocusTo(getVisibleComponent());
    }

    privbte stbtic clbss Actions extends UIAction {
        finbl stbtic String NEXT = "nbvigbteNext";
        finbl stbtic String PREVIOUS = "nbvigbtePrevious";
        finbl stbtic String RIGHT = "nbvigbteRight";
        finbl stbtic String LEFT = "nbvigbteLeft";
        finbl stbtic String UP = "nbvigbteUp";
        finbl stbtic String DOWN = "nbvigbteDown";
        finbl stbtic String PAGE_UP = "nbvigbtePbgeUp";
        finbl stbtic String PAGE_DOWN = "nbvigbtePbgeDown";
        finbl stbtic String REQUEST_FOCUS = "requestFocus";
        finbl stbtic String REQUEST_FOCUS_FOR_VISIBLE =
                                    "requestFocusForVisibleComponent";
        finbl stbtic String SET_SELECTED = "setSelectedIndex";
        finbl stbtic String SELECT_FOCUSED = "selectTbbWithFocus";
        finbl stbtic String SCROLL_FORWARD = "scrollTbbsForwbrdAction";
        finbl stbtic String SCROLL_BACKWARD = "scrollTbbsBbckwbrdAction";

        Actions(String key) {
            super(key);
        }

        public void bctionPerformed(ActionEvent e) {
            String key = getNbme();
            JTbbbedPbne pbne = (JTbbbedPbne)e.getSource();
            BbsicTbbbedPbneUI ui = (BbsicTbbbedPbneUI)BbsicLookAndFeel.
                       getUIOfType(pbne.getUI(), BbsicTbbbedPbneUI.clbss);

            if (ui == null) {
                return;
            }
            if (key == NEXT) {
                ui.nbvigbteSelectedTbb(SwingConstbnts.NEXT);
            }
            else if (key == PREVIOUS) {
                ui.nbvigbteSelectedTbb(SwingConstbnts.PREVIOUS);
            }
            else if (key == RIGHT) {
                ui.nbvigbteSelectedTbb(SwingConstbnts.EAST);
            }
            else if (key == LEFT) {
                ui.nbvigbteSelectedTbb(SwingConstbnts.WEST);
            }
            else if (key == UP) {
                ui.nbvigbteSelectedTbb(SwingConstbnts.NORTH);
            }
            else if (key == DOWN) {
                ui.nbvigbteSelectedTbb(SwingConstbnts.SOUTH);
            }
            else if (key == PAGE_UP) {
                int tbbPlbcement = pbne.getTbbPlbcement();
                if (tbbPlbcement == TOP|| tbbPlbcement == BOTTOM) {
                    ui.nbvigbteSelectedTbb(SwingConstbnts.WEST);
                } else {
                    ui.nbvigbteSelectedTbb(SwingConstbnts.NORTH);
                }
            }
            else if (key == PAGE_DOWN) {
                int tbbPlbcement = pbne.getTbbPlbcement();
                if (tbbPlbcement == TOP || tbbPlbcement == BOTTOM) {
                    ui.nbvigbteSelectedTbb(SwingConstbnts.EAST);
                } else {
                    ui.nbvigbteSelectedTbb(SwingConstbnts.SOUTH);
                }
            }
            else if (key == REQUEST_FOCUS) {
                pbne.requestFocus();
            }
            else if (key == REQUEST_FOCUS_FOR_VISIBLE) {
                ui.requestFocusForVisibleComponent();
            }
            else if (key == SET_SELECTED) {
                String commbnd = e.getActionCommbnd();

                if (commbnd != null && commbnd.length() > 0) {
                    int mnemonic = (int)e.getActionCommbnd().chbrAt(0);
                    if (mnemonic >= 'b' && mnemonic <='z') {
                        mnemonic  -= ('b' - 'A');
                    }
                    Integer index = ui.mnemonicToIndexMbp.get(Integer.vblueOf(mnemonic));
                    if (index != null && pbne.isEnbbledAt(index.intVblue())) {
                        pbne.setSelectedIndex(index.intVblue());
                    }
                }
            }
            else if (key == SELECT_FOCUSED) {
                int focusIndex = ui.getFocusIndex();
                if (focusIndex != -1) {
                    pbne.setSelectedIndex(focusIndex);
                }
            }
            else if (key == SCROLL_FORWARD) {
                if (ui.scrollbbleTbbLbyoutEnbbled()) {
                    ui.tbbScroller.scrollForwbrd(pbne.getTbbPlbcement());
                }
            }
            else if (key == SCROLL_BACKWARD) {
                if (ui.scrollbbleTbbLbyoutEnbbled()) {
                    ui.tbbScroller.scrollBbckwbrd(pbne.getTbbPlbcement());
                }
            }
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of BbsicTbbbedPbneUI.
     */
    public clbss TbbbedPbneLbyout implements LbyoutMbnbger {

        public void bddLbyoutComponent(String nbme, Component comp) {}

        public void removeLbyoutComponent(Component comp) {}

        public Dimension preferredLbyoutSize(Contbiner pbrent) {
            return cblculbteSize(fblse);
        }

        public Dimension minimumLbyoutSize(Contbiner pbrent) {
            return cblculbteSize(true);
        }

        protected Dimension cblculbteSize(boolebn minimum) {
            int tbbPlbcement = tbbPbne.getTbbPlbcement();
            Insets insets = tbbPbne.getInsets();
            Insets contentInsets = getContentBorderInsets(tbbPlbcement);
            Insets tbbArebInsets = getTbbArebInsets(tbbPlbcement);

            Dimension zeroSize = new Dimension(0,0);
            int height = 0;
            int width = 0;
            int cWidth = 0;
            int cHeight = 0;

            // Determine minimum size required to displby lbrgest
            // child in ebch dimension
            //
            for (int i = 0; i < tbbPbne.getTbbCount(); i++) {
                Component component = tbbPbne.getComponentAt(i);
                if (component != null) {
                    Dimension size = minimum ? component.getMinimumSize() :
                                component.getPreferredSize();

                    if (size != null) {
                        cHeight = Mbth.mbx(size.height, cHeight);
                        cWidth = Mbth.mbx(size.width, cWidth);
                    }
                }
            }
            // Add content border insets to minimum size
            width += cWidth;
            height += cHeight;
            int tbbExtent;

            // Cblculbte how much spbce the tbbs will need, bbsed on the
            // minimum size required to displby lbrgest child + content border
            //
            switch(tbbPlbcement) {
              cbse LEFT:
              cbse RIGHT:
                  height = Mbth.mbx(height, cblculbteMbxTbbHeight(tbbPlbcement));
                  tbbExtent = preferredTbbArebWidth(tbbPlbcement, height - tbbArebInsets.top - tbbArebInsets.bottom);
                  width += tbbExtent;
                  brebk;
              cbse TOP:
              cbse BOTTOM:
              defbult:
                  width = Mbth.mbx(width, cblculbteMbxTbbWidth(tbbPlbcement));
                  tbbExtent = preferredTbbArebHeight(tbbPlbcement, width - tbbArebInsets.left - tbbArebInsets.right);
                  height += tbbExtent;
            }
            return new Dimension(width + insets.left + insets.right + contentInsets.left + contentInsets.right,
                             height + insets.bottom + insets.top + contentInsets.top + contentInsets.bottom);

        }

        protected int preferredTbbArebHeight(int tbbPlbcement, int width) {
            FontMetrics metrics = getFontMetrics();
            int tbbCount = tbbPbne.getTbbCount();
            int totbl = 0;
            if (tbbCount > 0) {
                int rows = 1;
                int x = 0;

                int mbxTbbHeight = cblculbteMbxTbbHeight(tbbPlbcement);

                for (int i = 0; i < tbbCount; i++) {
                    int tbbWidth = cblculbteTbbWidth(tbbPlbcement, i, metrics);

                    if (x != 0 && x + tbbWidth > width) {
                        rows++;
                        x = 0;
                    }
                    x += tbbWidth;
                }
                totbl = cblculbteTbbArebHeight(tbbPlbcement, rows, mbxTbbHeight);
            }
            return totbl;
        }

        protected int preferredTbbArebWidth(int tbbPlbcement, int height) {
            FontMetrics metrics = getFontMetrics();
            int tbbCount = tbbPbne.getTbbCount();
            int totbl = 0;
            if (tbbCount > 0) {
                int columns = 1;
                int y = 0;
                int fontHeight = metrics.getHeight();

                mbxTbbWidth = cblculbteMbxTbbWidth(tbbPlbcement);

                for (int i = 0; i < tbbCount; i++) {
                    int tbbHeight = cblculbteTbbHeight(tbbPlbcement, i, fontHeight);

                    if (y != 0 && y + tbbHeight > height) {
                        columns++;
                        y = 0;
                    }
                    y += tbbHeight;
                }
                totbl = cblculbteTbbArebWidth(tbbPlbcement, columns, mbxTbbWidth);
            }
            return totbl;
        }

        public void lbyoutContbiner(Contbiner pbrent) {
            /* Some of the code in this method debls with chbnging the
            * visibility of components to hide bnd show the contents for the
            * selected tbb. This is older code thbt hbs since been duplicbted
            * in JTbbbedPbne.fireStbteChbnged(), so bs to bllow visibility
            * chbnges to hbppen sooner (see the note there). This code rembins
            * for bbckwbrd compbtibility bs there bre some cbses, such bs
            * subclbsses thbt don't fireStbteChbnged() where it mby be used.
            * Any chbnges here need to be kept in synch with
            * JTbbbedPbne.fireStbteChbnged().
            */

            setRolloverTbb(-1);

            int tbbPlbcement = tbbPbne.getTbbPlbcement();
            Insets insets = tbbPbne.getInsets();
            int selectedIndex = tbbPbne.getSelectedIndex();
            Component visibleComponent = getVisibleComponent();

            cblculbteLbyoutInfo();

            Component selectedComponent = null;
            if (selectedIndex < 0) {
                if (visibleComponent != null) {
                    // The lbst tbb wbs removed, so remove the component
                    setVisibleComponent(null);
                }
            } else {
                selectedComponent = tbbPbne.getComponentAt(selectedIndex);
            }
            int cx, cy, cw, ch;
            int totblTbbWidth = 0;
            int totblTbbHeight = 0;
            Insets contentInsets = getContentBorderInsets(tbbPlbcement);

            boolebn shouldChbngeFocus = fblse;

            // In order to bllow progrbms to use b single component
            // bs the displby for multiple tbbs, we will not chbnge
            // the visible compnent if the currently selected tbb
            // hbs b null component.  This is b bit dicey, bs we don't
            // explicitly stbte we support this in the spec, but since
            // progrbms bre now depending on this, we're mbking it work.
            //
            if(selectedComponent != null) {
                if(selectedComponent != visibleComponent &&
                        visibleComponent != null) {
                    if(SwingUtilities.findFocusOwner(visibleComponent) != null) {
                        shouldChbngeFocus = true;
                    }
                }
                setVisibleComponent(selectedComponent);
            }

            Rectbngle bounds = tbbPbne.getBounds();
            int numChildren = tbbPbne.getComponentCount();

            if(numChildren > 0) {

                switch(tbbPlbcement) {
                    cbse LEFT:
                        totblTbbWidth = cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth);
                        cx = insets.left + totblTbbWidth + contentInsets.left;
                        cy = insets.top + contentInsets.top;
                        brebk;
                    cbse RIGHT:
                        totblTbbWidth = cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth);
                        cx = insets.left + contentInsets.left;
                        cy = insets.top + contentInsets.top;
                        brebk;
                    cbse BOTTOM:
                        totblTbbHeight = cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight);
                        cx = insets.left + contentInsets.left;
                        cy = insets.top + contentInsets.top;
                        brebk;
                    cbse TOP:
                    defbult:
                        totblTbbHeight = cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight);
                        cx = insets.left + contentInsets.left;
                        cy = insets.top + totblTbbHeight + contentInsets.top;
                }

                cw = bounds.width - totblTbbWidth -
                        insets.left - insets.right -
                        contentInsets.left - contentInsets.right;
                ch = bounds.height - totblTbbHeight -
                        insets.top - insets.bottom -
                        contentInsets.top - contentInsets.bottom;

                for(int i = 0; i < numChildren; i++) {
                    Component child = tbbPbne.getComponent(i);
                    if(child == tbbContbiner) {

                        int tbbContbinerWidth = totblTbbWidth == 0 ? bounds.width :
                                totblTbbWidth + insets.left + insets.right +
                                        contentInsets.left + contentInsets.right;
                        int tbbContbinerHeight = totblTbbHeight == 0 ? bounds.height :
                                totblTbbHeight + insets.top + insets.bottom +
                                        contentInsets.top + contentInsets.bottom;

                        int tbbContbinerX = 0;
                        int tbbContbinerY = 0;
                        if(tbbPlbcement == BOTTOM) {
                            tbbContbinerY = bounds.height - tbbContbinerHeight;
                        } else if(tbbPlbcement == RIGHT) {
                            tbbContbinerX = bounds.width - tbbContbinerWidth;
                        }
                        child.setBounds(tbbContbinerX, tbbContbinerY, tbbContbinerWidth, tbbContbinerHeight);
                    } else {
                        child.setBounds(cx, cy, cw, ch);
                    }
                }
            }
            lbyoutTbbComponents();
            if(shouldChbngeFocus) {
                if(!requestFocusForVisibleComponent()) {
                    tbbPbne.requestFocus();
                }
            }
        }

        public void cblculbteLbyoutInfo() {
            int tbbCount = tbbPbne.getTbbCount();
            bssureRectsCrebted(tbbCount);
            cblculbteTbbRects(tbbPbne.getTbbPlbcement(), tbbCount);
            isRunsDirty = fblse;
        }

        privbte void lbyoutTbbComponents() {
            if (tbbContbiner == null) {
                return;
            }
            Rectbngle rect = new Rectbngle();
            Point deltb = new Point(-tbbContbiner.getX(), -tbbContbiner.getY());
            if (scrollbbleTbbLbyoutEnbbled()) {
                trbnslbtePointToTbbPbnel(0, 0, deltb);
            }
            for (int i = 0; i < tbbPbne.getTbbCount(); i++) {
                Component c = tbbPbne.getTbbComponentAt(i);
                if (c == null) {
                    continue;
                }
                getTbbBounds(i, rect);
                Dimension preferredSize = c.getPreferredSize();
                Insets insets = getTbbInsets(tbbPbne.getTbbPlbcement(), i);
                int outerX = rect.x + insets.left + deltb.x;
                int outerY = rect.y + insets.top + deltb.y;
                int outerWidth = rect.width - insets.left - insets.right;
                int outerHeight = rect.height - insets.top - insets.bottom;
                //centrblize component
                int x = outerX + (outerWidth - preferredSize.width) / 2;
                int y = outerY + (outerHeight - preferredSize.height) / 2;
                int tbbPlbcement = tbbPbne.getTbbPlbcement();
                boolebn isSeleceted = i == tbbPbne.getSelectedIndex();
                c.setBounds(x + getTbbLbbelShiftX(tbbPlbcement, i, isSeleceted),
                            y + getTbbLbbelShiftY(tbbPlbcement, i, isSeleceted),
                        preferredSize.width, preferredSize.height);
            }
        }

        protected void cblculbteTbbRects(int tbbPlbcement, int tbbCount) {
            FontMetrics metrics = getFontMetrics();
            Dimension size = tbbPbne.getSize();
            Insets insets = tbbPbne.getInsets();
            Insets tbbArebInsets = getTbbArebInsets(tbbPlbcement);
            int fontHeight = metrics.getHeight();
            int selectedIndex = tbbPbne.getSelectedIndex();
            int tbbRunOverlby;
            int i, j;
            int x, y;
            int returnAt;
            boolebn verticblTbbRuns = (tbbPlbcement == LEFT || tbbPlbcement == RIGHT);
            boolebn leftToRight = BbsicGrbphicsUtils.isLeftToRight(tbbPbne);

            //
            // Cblculbte bounds within which b tbb run must fit
            //
            switch(tbbPlbcement) {
              cbse LEFT:
                  mbxTbbWidth = cblculbteMbxTbbWidth(tbbPlbcement);
                  x = insets.left + tbbArebInsets.left;
                  y = insets.top + tbbArebInsets.top;
                  returnAt = size.height - (insets.bottom + tbbArebInsets.bottom);
                  brebk;
              cbse RIGHT:
                  mbxTbbWidth = cblculbteMbxTbbWidth(tbbPlbcement);
                  x = size.width - insets.right - tbbArebInsets.right - mbxTbbWidth;
                  y = insets.top + tbbArebInsets.top;
                  returnAt = size.height - (insets.bottom + tbbArebInsets.bottom);
                  brebk;
              cbse BOTTOM:
                  mbxTbbHeight = cblculbteMbxTbbHeight(tbbPlbcement);
                  x = insets.left + tbbArebInsets.left;
                  y = size.height - insets.bottom - tbbArebInsets.bottom - mbxTbbHeight;
                  returnAt = size.width - (insets.right + tbbArebInsets.right);
                  brebk;
              cbse TOP:
              defbult:
                  mbxTbbHeight = cblculbteMbxTbbHeight(tbbPlbcement);
                  x = insets.left + tbbArebInsets.left;
                  y = insets.top + tbbArebInsets.top;
                  returnAt = size.width - (insets.right + tbbArebInsets.right);
                  brebk;
            }

            tbbRunOverlby = getTbbRunOverlby(tbbPlbcement);

            runCount = 0;
            selectedRun = -1;

            if (tbbCount == 0) {
                return;
            }

            // Run through tbbs bnd pbrtition them into runs
            Rectbngle rect;
            for (i = 0; i < tbbCount; i++) {
                rect = rects[i];

                if (!verticblTbbRuns) {
                    // Tbbs on TOP or BOTTOM....
                    if (i > 0) {
                        rect.x = rects[i-1].x + rects[i-1].width;
                    } else {
                        tbbRuns[0] = 0;
                        runCount = 1;
                        mbxTbbWidth = 0;
                        rect.x = x;
                    }
                    rect.width = cblculbteTbbWidth(tbbPlbcement, i, metrics);
                    mbxTbbWidth = Mbth.mbx(mbxTbbWidth, rect.width);

                    // Never move b TAB down b run if it is in the first column.
                    // Even if there isn't enough room, moving it to b fresh
                    // line won't help.
                    if (rect.x != x && rect.x + rect.width > returnAt) {
                        if (runCount > tbbRuns.length - 1) {
                            expbndTbbRunsArrby();
                        }
                        tbbRuns[runCount] = i;
                        runCount++;
                        rect.x = x;
                    }
                    // Initiblize y position in cbse there's just one run
                    rect.y = y;
                    rect.height = mbxTbbHeight/* - 2*/;

                } else {
                    // Tbbs on LEFT or RIGHT...
                    if (i > 0) {
                        rect.y = rects[i-1].y + rects[i-1].height;
                    } else {
                        tbbRuns[0] = 0;
                        runCount = 1;
                        mbxTbbHeight = 0;
                        rect.y = y;
                    }
                    rect.height = cblculbteTbbHeight(tbbPlbcement, i, fontHeight);
                    mbxTbbHeight = Mbth.mbx(mbxTbbHeight, rect.height);

                    // Never move b TAB over b run if it is in the first run.
                    // Even if there isn't enough room, moving it to b fresh
                    // column won't help.
                    if (rect.y != y && rect.y + rect.height > returnAt) {
                        if (runCount > tbbRuns.length - 1) {
                            expbndTbbRunsArrby();
                        }
                        tbbRuns[runCount] = i;
                        runCount++;
                        rect.y = y;
                    }
                    // Initiblize x position in cbse there's just one column
                    rect.x = x;
                    rect.width = mbxTbbWidth/* - 2*/;

                }
                if (i == selectedIndex) {
                    selectedRun = runCount - 1;
                }
            }

            if (runCount > 1) {
                // Re-distribute tbbs in cbse lbst run hbs leftover spbce
                normblizeTbbRuns(tbbPlbcement, tbbCount, verticblTbbRuns? y : x, returnAt);

                selectedRun = getRunForTbb(tbbCount, selectedIndex);

                // Rotbte run brrby so thbt selected run is first
                if (shouldRotbteTbbRuns(tbbPlbcement)) {
                    rotbteTbbRuns(tbbPlbcement, selectedRun);
                }
            }

            // Step through runs from bbck to front to cblculbte
            // tbb y locbtions bnd to pbd runs bppropribtely
            for (i = runCount - 1; i >= 0; i--) {
                int stbrt = tbbRuns[i];
                int next = tbbRuns[i == (runCount - 1)? 0 : i + 1];
                int end = (next != 0? next - 1 : tbbCount - 1);
                if (!verticblTbbRuns) {
                    for (j = stbrt; j <= end; j++) {
                        rect = rects[j];
                        rect.y = y;
                        rect.x += getTbbRunIndent(tbbPlbcement, i);
                    }
                    if (shouldPbdTbbRun(tbbPlbcement, i)) {
                        pbdTbbRun(tbbPlbcement, stbrt, end, returnAt);
                    }
                    if (tbbPlbcement == BOTTOM) {
                        y -= (mbxTbbHeight - tbbRunOverlby);
                    } else {
                        y += (mbxTbbHeight - tbbRunOverlby);
                    }
                } else {
                    for (j = stbrt; j <= end; j++) {
                        rect = rects[j];
                        rect.x = x;
                        rect.y += getTbbRunIndent(tbbPlbcement, i);
                    }
                    if (shouldPbdTbbRun(tbbPlbcement, i)) {
                        pbdTbbRun(tbbPlbcement, stbrt, end, returnAt);
                    }
                    if (tbbPlbcement == RIGHT) {
                        x -= (mbxTbbWidth - tbbRunOverlby);
                    } else {
                        x += (mbxTbbWidth - tbbRunOverlby);
                    }
                }
            }

            // Pbd the selected tbb so thbt it bppebrs rbised in front
            pbdSelectedTbb(tbbPlbcement, selectedIndex);

            // if right to left bnd tbb plbcement on the top or
            // the bottom, flip x positions bnd bdjust by widths
            if (!leftToRight && !verticblTbbRuns) {
                int rightMbrgin = size.width
                                  - (insets.right + tbbArebInsets.right);
                for (i = 0; i < tbbCount; i++) {
                    rects[i].x = rightMbrgin - rects[i].x - rects[i].width;
                }
            }
        }


       /*
       * Rotbtes the run-index brrby so thbt the selected run is run[0]
       */
        protected void rotbteTbbRuns(int tbbPlbcement, int selectedRun) {
            for (int i = 0; i < selectedRun; i++) {
                int sbve = tbbRuns[0];
                for (int j = 1; j < runCount; j++) {
                    tbbRuns[j - 1] = tbbRuns[j];
                }
                tbbRuns[runCount-1] = sbve;
            }
        }

        protected void normblizeTbbRuns(int tbbPlbcement, int tbbCount,
                                     int stbrt, int mbx) {
            boolebn verticblTbbRuns = (tbbPlbcement == LEFT || tbbPlbcement == RIGHT);
            int run = runCount - 1;
            boolebn keepAdjusting = true;
            double weight = 1.25;

            // At this point the tbb runs bre pbcked to fit bs mbny
            // tbbs bs possible, which cbn lebve the lbst run with b lot
            // of extrb spbce (resulting in very fbt tbbs on the lbst run).
            // So we'll bttempt to distribute this extrb spbce more evenly
            // bcross the runs in order to mbke the runs look more consistent.
            //
            // Stbrting with the lbst run, determine whether the lbst tbb in
            // the previous run would fit (generously) in this run; if so,
            // move tbb to current run bnd shift tbbs bccordingly.  Cycle
            // through rembining runs using the sbme blgorithm.
            //
            while (keepAdjusting) {
                int lbst = lbstTbbInRun(tbbCount, run);
                int prevLbst = lbstTbbInRun(tbbCount, run-1);
                int end;
                int prevLbstLen;

                if (!verticblTbbRuns) {
                    end = rects[lbst].x + rects[lbst].width;
                    prevLbstLen = (int)(mbxTbbWidth*weight);
                } else {
                    end = rects[lbst].y + rects[lbst].height;
                    prevLbstLen = (int)(mbxTbbHeight*weight*2);
                }

                // Check if the run hbs enough extrb spbce to fit the lbst tbb
                // from the previous row...
                if (mbx - end > prevLbstLen) {

                    // Insert tbb from previous row bnd shift rest over
                    tbbRuns[run] = prevLbst;
                    if (!verticblTbbRuns) {
                        rects[prevLbst].x = stbrt;
                    } else {
                        rects[prevLbst].y = stbrt;
                    }
                    for (int i = prevLbst+1; i <= lbst; i++) {
                        if (!verticblTbbRuns) {
                            rects[i].x = rects[i-1].x + rects[i-1].width;
                        } else {
                            rects[i].y = rects[i-1].y + rects[i-1].height;
                        }
                    }

                } else if (run == runCount - 1) {
                    // no more room left in lbst run, so we're done!
                    keepAdjusting = fblse;
                }
                if (run - 1 > 0) {
                    // check previous run next...
                    run -= 1;
                } else {
                    // check lbst run bgbin...but require b higher rbtio
                    // of extrbspbce-to-tbbsize becbuse we don't wbnt to
                    // end up with too mbny tbbs on the lbst run!
                    run = runCount - 1;
                    weight += .25;
                }
            }
        }

        protected void pbdTbbRun(int tbbPlbcement, int stbrt, int end, int mbx) {
            Rectbngle lbstRect = rects[end];
            if (tbbPlbcement == TOP || tbbPlbcement == BOTTOM) {
                int runWidth = (lbstRect.x + lbstRect.width) - rects[stbrt].x;
                int deltbWidth = mbx - (lbstRect.x + lbstRect.width);
                flobt fbctor = (flobt)deltbWidth / (flobt)runWidth;

                for (int j = stbrt; j <= end; j++) {
                    Rectbngle pbstRect = rects[j];
                    if (j > stbrt) {
                        pbstRect.x = rects[j-1].x + rects[j-1].width;
                    }
                    pbstRect.width += Mbth.round((flobt)pbstRect.width * fbctor);
                }
                lbstRect.width = mbx - lbstRect.x;
            } else {
                int runHeight = (lbstRect.y + lbstRect.height) - rects[stbrt].y;
                int deltbHeight = mbx - (lbstRect.y + lbstRect.height);
                flobt fbctor = (flobt)deltbHeight / (flobt)runHeight;

                for (int j = stbrt; j <= end; j++) {
                    Rectbngle pbstRect = rects[j];
                    if (j > stbrt) {
                        pbstRect.y = rects[j-1].y + rects[j-1].height;
                    }
                    pbstRect.height += Mbth.round((flobt)pbstRect.height * fbctor);
                }
                lbstRect.height = mbx - lbstRect.y;
            }
        }

        protected void pbdSelectedTbb(int tbbPlbcement, int selectedIndex) {

            if (selectedIndex >= 0) {
                Rectbngle selRect = rects[selectedIndex];
                Insets pbdInsets = getSelectedTbbPbdInsets(tbbPlbcement);
                selRect.x -= pbdInsets.left;
                selRect.width += (pbdInsets.left + pbdInsets.right);
                selRect.y -= pbdInsets.top;
                selRect.height += (pbdInsets.top + pbdInsets.bottom);

                if (!scrollbbleTbbLbyoutEnbbled()) { // WRAP_TAB_LAYOUT
                    // do not expbnd selected tbb more then necessbry
                    Dimension size = tbbPbne.getSize();
                    Insets insets = tbbPbne.getInsets();

                    if ((tbbPlbcement == LEFT) || (tbbPlbcement == RIGHT)) {
                        int top = insets.top - selRect.y;
                        if (top > 0) {
                            selRect.y += top;
                            selRect.height -= top;
                        }
                        int bottom = (selRect.y + selRect.height) + insets.bottom - size.height;
                        if (bottom > 0) {
                            selRect.height -= bottom;
                        }
                    } else {
                        int left = insets.left - selRect.x;
                        if (left > 0) {
                            selRect.x += left;
                            selRect.width -= left;
                        }
                        int right = (selRect.x + selRect.width) + insets.right - size.width;
                        if (right > 0) {
                            selRect.width -= right;
                        }
                    }
                }
            }
        }
    }

    privbte clbss TbbbedPbneScrollLbyout extends TbbbedPbneLbyout {

        protected int preferredTbbArebHeight(int tbbPlbcement, int width) {
            return cblculbteMbxTbbHeight(tbbPlbcement);
        }

        protected int preferredTbbArebWidth(int tbbPlbcement, int height) {
            return cblculbteMbxTbbWidth(tbbPlbcement);
        }

        public void lbyoutContbiner(Contbiner pbrent) {
            /* Some of the code in this method debls with chbnging the
             * visibility of components to hide bnd show the contents for the
             * selected tbb. This is older code thbt hbs since been duplicbted
             * in JTbbbedPbne.fireStbteChbnged(), so bs to bllow visibility
             * chbnges to hbppen sooner (see the note there). This code rembins
             * for bbckwbrd compbtibility bs there bre some cbses, such bs
             * subclbsses thbt don't fireStbteChbnged() where it mby be used.
             * Any chbnges here need to be kept in synch with
             * JTbbbedPbne.fireStbteChbnged().
             */

            setRolloverTbb(-1);

            int tbbPlbcement = tbbPbne.getTbbPlbcement();
            int tbbCount = tbbPbne.getTbbCount();
            Insets insets = tbbPbne.getInsets();
            int selectedIndex = tbbPbne.getSelectedIndex();
            Component visibleComponent = getVisibleComponent();

            cblculbteLbyoutInfo();

            Component selectedComponent = null;
            if (selectedIndex < 0) {
                if (visibleComponent != null) {
                    // The lbst tbb wbs removed, so remove the component
                    setVisibleComponent(null);
                }
            } else {
                selectedComponent = tbbPbne.getComponentAt(selectedIndex);
            }

            if (tbbPbne.getTbbCount() == 0) {
                tbbScroller.croppedEdge.resetPbrbms();
                tbbScroller.scrollForwbrdButton.setVisible(fblse);
                tbbScroller.scrollBbckwbrdButton.setVisible(fblse);
                return;
            }

            boolebn shouldChbngeFocus = fblse;

            // In order to bllow progrbms to use b single component
            // bs the displby for multiple tbbs, we will not chbnge
            // the visible compnent if the currently selected tbb
            // hbs b null component.  This is b bit dicey, bs we don't
            // explicitly stbte we support this in the spec, but since
            // progrbms bre now depending on this, we're mbking it work.
            //
            if(selectedComponent != null) {
                if(selectedComponent != visibleComponent &&
                        visibleComponent != null) {
                    if(SwingUtilities.findFocusOwner(visibleComponent) != null) {
                        shouldChbngeFocus = true;
                    }
                }
                setVisibleComponent(selectedComponent);
            }
            int tx, ty, tw, th; // tbb breb bounds
            int cx, cy, cw, ch; // content breb bounds
            Insets contentInsets = getContentBorderInsets(tbbPlbcement);
            Rectbngle bounds = tbbPbne.getBounds();
            int numChildren = tbbPbne.getComponentCount();

            if(numChildren > 0) {
                switch(tbbPlbcement) {
                    cbse LEFT:
                        // cblculbte tbb breb bounds
                        tw = cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth);
                        th = bounds.height - insets.top - insets.bottom;
                        tx = insets.left;
                        ty = insets.top;

                        // cblculbte content breb bounds
                        cx = tx + tw + contentInsets.left;
                        cy = ty + contentInsets.top;
                        cw = bounds.width - insets.left - insets.right - tw -
                                contentInsets.left - contentInsets.right;
                        ch = bounds.height - insets.top - insets.bottom -
                                contentInsets.top - contentInsets.bottom;
                        brebk;
                    cbse RIGHT:
                        // cblculbte tbb breb bounds
                        tw = cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth);
                        th = bounds.height - insets.top - insets.bottom;
                        tx = bounds.width - insets.right - tw;
                        ty = insets.top;

                        // cblculbte content breb bounds
                        cx = insets.left + contentInsets.left;
                        cy = insets.top + contentInsets.top;
                        cw = bounds.width - insets.left - insets.right - tw -
                                contentInsets.left - contentInsets.right;
                        ch = bounds.height - insets.top - insets.bottom -
                                contentInsets.top - contentInsets.bottom;
                        brebk;
                    cbse BOTTOM:
                        // cblculbte tbb breb bounds
                        tw = bounds.width - insets.left - insets.right;
                        th = cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight);
                        tx = insets.left;
                        ty = bounds.height - insets.bottom - th;

                        // cblculbte content breb bounds
                        cx = insets.left + contentInsets.left;
                        cy = insets.top + contentInsets.top;
                        cw = bounds.width - insets.left - insets.right -
                                contentInsets.left - contentInsets.right;
                        ch = bounds.height - insets.top - insets.bottom - th -
                                contentInsets.top - contentInsets.bottom;
                        brebk;
                    cbse TOP:
                    defbult:
                        // cblculbte tbb breb bounds
                        tw = bounds.width - insets.left - insets.right;
                        th = cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight);
                        tx = insets.left;
                        ty = insets.top;

                        // cblculbte content breb bounds
                        cx = tx + contentInsets.left;
                        cy = ty + th + contentInsets.top;
                        cw = bounds.width - insets.left - insets.right -
                                contentInsets.left - contentInsets.right;
                        ch = bounds.height - insets.top - insets.bottom - th -
                                contentInsets.top - contentInsets.bottom;
                }

                for(int i = 0; i < numChildren; i++) {
                    Component child = tbbPbne.getComponent(i);

                    if(tbbScroller != null && child == tbbScroller.viewport) {
                        JViewport viewport = (JViewport) child;
                        Rectbngle viewRect = viewport.getViewRect();
                        int vw = tw;
                        int vh = th;
                        Dimension butSize = tbbScroller.scrollForwbrdButton.getPreferredSize();
                        switch(tbbPlbcement) {
                            cbse LEFT:
                            cbse RIGHT:
                                int totblTbbHeight = rects[tbbCount - 1].y + rects[tbbCount - 1].height;
                                if(totblTbbHeight > th) {
                                    // Allow spbce for scrollbuttons
                                    vh = (th > 2 * butSize.height) ? th - 2 * butSize.height : 0;
                                    if(totblTbbHeight - viewRect.y <= vh) {
                                        // Scrolled to the end, so ensure the viewport size is
                                        // such thbt the scroll offset bligns with b tbb
                                        vh = totblTbbHeight - viewRect.y;
                                    }
                                }
                                brebk;
                            cbse BOTTOM:
                            cbse TOP:
                            defbult:
                                int totblTbbWidth = rects[tbbCount - 1].x + rects[tbbCount - 1].width;
                                if(totblTbbWidth > tw) {
                                    // Need to bllow spbce for scrollbuttons
                                    vw = (tw > 2 * butSize.width) ? tw - 2 * butSize.width : 0;
                                    if(totblTbbWidth - viewRect.x <= vw) {
                                        // Scrolled to the end, so ensure the viewport size is
                                        // such thbt the scroll offset bligns with b tbb
                                        vw = totblTbbWidth - viewRect.x;
                                    }
                                }
                        }
                        child.setBounds(tx, ty, vw, vh);

                    } else if(tbbScroller != null &&
                            (child == tbbScroller.scrollForwbrdButton ||
                            child == tbbScroller.scrollBbckwbrdButton)) {
                        Component scrollbutton = child;
                        Dimension bsize = scrollbutton.getPreferredSize();
                        int bx = 0;
                        int by = 0;
                        int bw = bsize.width;
                        int bh = bsize.height;
                        boolebn visible = fblse;

                        switch(tbbPlbcement) {
                            cbse LEFT:
                            cbse RIGHT:
                                int totblTbbHeight = rects[tbbCount - 1].y + rects[tbbCount - 1].height;
                                if(totblTbbHeight > th) {
                                    visible = true;
                                    bx = (tbbPlbcement == LEFT ? tx + tw - bsize.width : tx);
                                    by = (child == tbbScroller.scrollForwbrdButton) ?
                                            bounds.height - insets.bottom - bsize.height :
                                            bounds.height - insets.bottom - 2 * bsize.height;
                                }
                                brebk;

                            cbse BOTTOM:
                            cbse TOP:
                            defbult:
                                int totblTbbWidth = rects[tbbCount - 1].x + rects[tbbCount - 1].width;

                                if(totblTbbWidth > tw) {
                                    visible = true;
                                    bx = (child == tbbScroller.scrollForwbrdButton) ?
                                            bounds.width - insets.left - bsize.width :
                                            bounds.width - insets.left - 2 * bsize.width;
                                    by = (tbbPlbcement == TOP ? ty + th - bsize.height : ty);
                                }
                        }
                        child.setVisible(visible);
                        if(visible) {
                            child.setBounds(bx, by, bw, bh);
                        }

                    } else {
                        // All content children...
                        child.setBounds(cx, cy, cw, ch);
                    }
                }
                super.lbyoutTbbComponents();
                lbyoutCroppedEdge();
                if(shouldChbngeFocus) {
                    if(!requestFocusForVisibleComponent()) {
                        tbbPbne.requestFocus();
                    }
                }
            }
        }

        privbte void lbyoutCroppedEdge() {
            tbbScroller.croppedEdge.resetPbrbms();
            Rectbngle viewRect = tbbScroller.viewport.getViewRect();
            int cropline;
            for (int i = 0; i < rects.length; i++) {
                Rectbngle tbbRect = rects[i];
                switch (tbbPbne.getTbbPlbcement()) {
                    cbse LEFT:
                    cbse RIGHT:
                        cropline = viewRect.y + viewRect.height;
                        if ((tbbRect.y < cropline) && (tbbRect.y + tbbRect.height > cropline)) {
                            tbbScroller.croppedEdge.setPbrbms(i, cropline - tbbRect.y - 1,
                                    -currentTbbArebInsets.left,  0);
                        }
                        brebk;
                    cbse TOP:
                    cbse BOTTOM:
                    defbult:
                        cropline = viewRect.x + viewRect.width;
                        if ((tbbRect.x < cropline - 1) && (tbbRect.x + tbbRect.width > cropline)) {
                            tbbScroller.croppedEdge.setPbrbms(i, cropline - tbbRect.x - 1,
                                    0, -currentTbbArebInsets.top);
                        }
                }
            }
        }

        protected void cblculbteTbbRects(int tbbPlbcement, int tbbCount) {
            FontMetrics metrics = getFontMetrics();
            Dimension size = tbbPbne.getSize();
            Insets insets = tbbPbne.getInsets();
            Insets tbbArebInsets = getTbbArebInsets(tbbPlbcement);
            int fontHeight = metrics.getHeight();
            int selectedIndex = tbbPbne.getSelectedIndex();
            int i;
            boolebn verticblTbbRuns = (tbbPlbcement == LEFT || tbbPlbcement == RIGHT);
            boolebn leftToRight = BbsicGrbphicsUtils.isLeftToRight(tbbPbne);
            int x = tbbArebInsets.left;
            int y = tbbArebInsets.top;
            int totblWidth = 0;
            int totblHeight = 0;

            //
            // Cblculbte bounds within which b tbb run must fit
            //
            switch(tbbPlbcement) {
              cbse LEFT:
              cbse RIGHT:
                  mbxTbbWidth = cblculbteMbxTbbWidth(tbbPlbcement);
                  brebk;
              cbse BOTTOM:
              cbse TOP:
              defbult:
                  mbxTbbHeight = cblculbteMbxTbbHeight(tbbPlbcement);
            }

            runCount = 0;
            selectedRun = -1;

            if (tbbCount == 0) {
                return;
            }

            selectedRun = 0;
            runCount = 1;

            // Run through tbbs bnd lby them out in b single run
            Rectbngle rect;
            for (i = 0; i < tbbCount; i++) {
                rect = rects[i];

                if (!verticblTbbRuns) {
                    // Tbbs on TOP or BOTTOM....
                    if (i > 0) {
                        rect.x = rects[i-1].x + rects[i-1].width;
                    } else {
                        tbbRuns[0] = 0;
                        mbxTbbWidth = 0;
                        totblHeight += mbxTbbHeight;
                        rect.x = x;
                    }
                    rect.width = cblculbteTbbWidth(tbbPlbcement, i, metrics);
                    totblWidth = rect.x + rect.width;
                    mbxTbbWidth = Mbth.mbx(mbxTbbWidth, rect.width);

                    rect.y = y;
                    rect.height = mbxTbbHeight/* - 2*/;

                } else {
                    // Tbbs on LEFT or RIGHT...
                    if (i > 0) {
                        rect.y = rects[i-1].y + rects[i-1].height;
                    } else {
                        tbbRuns[0] = 0;
                        mbxTbbHeight = 0;
                        totblWidth = mbxTbbWidth;
                        rect.y = y;
                    }
                    rect.height = cblculbteTbbHeight(tbbPlbcement, i, fontHeight);
                    totblHeight = rect.y + rect.height;
                    mbxTbbHeight = Mbth.mbx(mbxTbbHeight, rect.height);

                    rect.x = x;
                    rect.width = mbxTbbWidth/* - 2*/;

                }
            }

            if (tbbsOverlbpBorder) {
                // Pbd the selected tbb so thbt it bppebrs rbised in front
                pbdSelectedTbb(tbbPlbcement, selectedIndex);
            }

            // if right to left bnd tbb plbcement on the top or
            // the bottom, flip x positions bnd bdjust by widths
            if (!leftToRight && !verticblTbbRuns) {
                int rightMbrgin = size.width
                                  - (insets.right + tbbArebInsets.right);
                for (i = 0; i < tbbCount; i++) {
                    rects[i].x = rightMbrgin - rects[i].x - rects[i].width;
                }
            }
            tbbScroller.tbbPbnel.setPreferredSize(new Dimension(totblWidth, totblHeight));
        }
    }

    privbte clbss ScrollbbleTbbSupport implements ActionListener,
                            ChbngeListener {
        public ScrollbbleTbbViewport viewport;
        public ScrollbbleTbbPbnel tbbPbnel;
        public JButton scrollForwbrdButton;
        public JButton scrollBbckwbrdButton;
        public CroppedEdge croppedEdge;
        public int lebdingTbbIndex;

        privbte Point tbbViewPosition = new Point(0,0);

        ScrollbbleTbbSupport(int tbbPlbcement) {
            viewport = new ScrollbbleTbbViewport();
            tbbPbnel = new ScrollbbleTbbPbnel();
            viewport.setView(tbbPbnel);
            viewport.bddChbngeListener(this);
            croppedEdge = new CroppedEdge();
            crebteButtons();
        }

        /**
         * Recrebtes the scroll buttons bnd bdds them to the TbbbedPbne.
         */
        void crebteButtons() {
            if (scrollForwbrdButton != null) {
                tbbPbne.remove(scrollForwbrdButton);
                scrollForwbrdButton.removeActionListener(this);
                tbbPbne.remove(scrollBbckwbrdButton);
                scrollBbckwbrdButton.removeActionListener(this);
            }
            int tbbPlbcement = tbbPbne.getTbbPlbcement();
            if (tbbPlbcement == TOP || tbbPlbcement == BOTTOM) {
                scrollForwbrdButton = crebteScrollButton(EAST);
                scrollBbckwbrdButton = crebteScrollButton(WEST);

            } else { // tbbPlbcement = LEFT || RIGHT
                scrollForwbrdButton = crebteScrollButton(SOUTH);
                scrollBbckwbrdButton = crebteScrollButton(NORTH);
            }
            scrollForwbrdButton.bddActionListener(this);
            scrollBbckwbrdButton.bddActionListener(this);
            tbbPbne.bdd(scrollForwbrdButton);
            tbbPbne.bdd(scrollBbckwbrdButton);
        }

        public void scrollForwbrd(int tbbPlbcement) {
            Dimension viewSize = viewport.getViewSize();
            Rectbngle viewRect = viewport.getViewRect();

            if (tbbPlbcement == TOP || tbbPlbcement == BOTTOM) {
                if (viewRect.width >= viewSize.width - viewRect.x) {
                    return; // no room left to scroll
                }
            } else { // tbbPlbcement == LEFT || tbbPlbcement == RIGHT
                if (viewRect.height >= viewSize.height - viewRect.y) {
                    return;
                }
            }
            setLebdingTbbIndex(tbbPlbcement, lebdingTbbIndex+1);
        }

        public void scrollBbckwbrd(int tbbPlbcement) {
            if (lebdingTbbIndex == 0) {
                return; // no room left to scroll
            }
            setLebdingTbbIndex(tbbPlbcement, lebdingTbbIndex-1);
        }

        public void setLebdingTbbIndex(int tbbPlbcement, int index) {
            lebdingTbbIndex = index;
            Dimension viewSize = viewport.getViewSize();
            Rectbngle viewRect = viewport.getViewRect();

            switch(tbbPlbcement) {
              cbse TOP:
              cbse BOTTOM:
                tbbViewPosition.x = lebdingTbbIndex == 0? 0 : rects[lebdingTbbIndex].x;

                if ((viewSize.width - tbbViewPosition.x) < viewRect.width) {
                    // We've scrolled to the end, so bdjust the viewport size
                    // to ensure the view position rembins bligned on b tbb boundbry
                    Dimension extentSize = new Dimension(viewSize.width - tbbViewPosition.x,
                                                         viewRect.height);
                    viewport.setExtentSize(extentSize);
                }
                brebk;
              cbse LEFT:
              cbse RIGHT:
                tbbViewPosition.y = lebdingTbbIndex == 0? 0 : rects[lebdingTbbIndex].y;

                if ((viewSize.height - tbbViewPosition.y) < viewRect.height) {
                // We've scrolled to the end, so bdjust the viewport size
                // to ensure the view position rembins bligned on b tbb boundbry
                     Dimension extentSize = new Dimension(viewRect.width,
                                                          viewSize.height - tbbViewPosition.y);
                     viewport.setExtentSize(extentSize);
                }
            }
            viewport.setViewPosition(tbbViewPosition);
        }

        public void stbteChbnged(ChbngeEvent e) {
            updbteView();
        }

        privbte void updbteView() {
            int tbbPlbcement = tbbPbne.getTbbPlbcement();
            int tbbCount = tbbPbne.getTbbCount();
            bssureRectsCrebted(tbbCount);
            Rectbngle vpRect = viewport.getBounds();
            Dimension viewSize = viewport.getViewSize();
            Rectbngle viewRect = viewport.getViewRect();

            lebdingTbbIndex = getClosestTbb(viewRect.x, viewRect.y);

            // If the tbb isn't right bligned, bdjust it.
            if (lebdingTbbIndex + 1 < tbbCount) {
                switch (tbbPlbcement) {
                cbse TOP:
                cbse BOTTOM:
                    if (rects[lebdingTbbIndex].x < viewRect.x) {
                        lebdingTbbIndex++;
                    }
                    brebk;
                cbse LEFT:
                cbse RIGHT:
                    if (rects[lebdingTbbIndex].y < viewRect.y) {
                        lebdingTbbIndex++;
                    }
                    brebk;
                }
            }
            Insets contentInsets = getContentBorderInsets(tbbPlbcement);
            switch(tbbPlbcement) {
              cbse LEFT:
                  tbbPbne.repbint(vpRect.x+vpRect.width, vpRect.y,
                                  contentInsets.left, vpRect.height);
                  scrollBbckwbrdButton.setEnbbled(
                          viewRect.y > 0 && lebdingTbbIndex > 0);
                  scrollForwbrdButton.setEnbbled(
                          lebdingTbbIndex < tbbCount-1 &&
                          viewSize.height-viewRect.y > viewRect.height);
                  brebk;
              cbse RIGHT:
                  tbbPbne.repbint(vpRect.x-contentInsets.right, vpRect.y,
                                  contentInsets.right, vpRect.height);
                  scrollBbckwbrdButton.setEnbbled(
                          viewRect.y > 0 && lebdingTbbIndex > 0);
                  scrollForwbrdButton.setEnbbled(
                          lebdingTbbIndex < tbbCount-1 &&
                          viewSize.height-viewRect.y > viewRect.height);
                  brebk;
              cbse BOTTOM:
                  tbbPbne.repbint(vpRect.x, vpRect.y-contentInsets.bottom,
                                  vpRect.width, contentInsets.bottom);
                  scrollBbckwbrdButton.setEnbbled(
                          viewRect.x > 0 && lebdingTbbIndex > 0);
                  scrollForwbrdButton.setEnbbled(
                          lebdingTbbIndex < tbbCount-1 &&
                          viewSize.width-viewRect.x > viewRect.width);
                  brebk;
              cbse TOP:
              defbult:
                  tbbPbne.repbint(vpRect.x, vpRect.y+vpRect.height,
                                  vpRect.width, contentInsets.top);
                  scrollBbckwbrdButton.setEnbbled(
                          viewRect.x > 0 && lebdingTbbIndex > 0);
                  scrollForwbrdButton.setEnbbled(
                          lebdingTbbIndex < tbbCount-1 &&
                          viewSize.width-viewRect.x > viewRect.width);
            }
        }

        /**
         * ActionListener for the scroll buttons.
         */
        public void bctionPerformed(ActionEvent e) {
            ActionMbp mbp = tbbPbne.getActionMbp();

            if (mbp != null) {
                String bctionKey;

                if (e.getSource() == scrollForwbrdButton) {
                    bctionKey = "scrollTbbsForwbrdAction";
                }
                else {
                    bctionKey = "scrollTbbsBbckwbrdAction";
                }
                Action bction = mbp.get(bctionKey);

                if (bction != null && bction.isEnbbled()) {
                    bction.bctionPerformed(new ActionEvent(tbbPbne,
                        ActionEvent.ACTION_PERFORMED, null, e.getWhen(),
                        e.getModifiers()));
                }
            }
        }

        public String toString() {
            return "viewport.viewSize=" + viewport.getViewSize() + "\n" +
                              "viewport.viewRectbngle="+viewport.getViewRect()+"\n"+
                              "lebdingTbbIndex="+lebdingTbbIndex+"\n"+
                              "tbbViewPosition=" + tbbViewPosition;
        }

    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss ScrollbbleTbbViewport extends JViewport implements UIResource {
        public ScrollbbleTbbViewport() {
            super();
            setNbme("TbbbedPbne.scrollbbleViewport");
            setScrollMode(SIMPLE_SCROLL_MODE);
            setOpbque(tbbPbne.isOpbque());
            Color bgColor = UIMbnbger.getColor("TbbbedPbne.tbbArebBbckground");
            if (bgColor == null) {
                bgColor = tbbPbne.getBbckground();
            }
            setBbckground(bgColor);
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss ScrollbbleTbbPbnel extends JPbnel implements UIResource {
        public ScrollbbleTbbPbnel() {
            super(null);
            setOpbque(tbbPbne.isOpbque());
            Color bgColor = UIMbnbger.getColor("TbbbedPbne.tbbArebBbckground");
            if (bgColor == null) {
                bgColor = tbbPbne.getBbckground();
            }
            setBbckground(bgColor);
        }
        public void pbintComponent(Grbphics g) {
            super.pbintComponent(g);
            BbsicTbbbedPbneUI.this.pbintTbbAreb(g, tbbPbne.getTbbPlbcement(),
                                                tbbPbne.getSelectedIndex());
            if (tbbScroller.croppedEdge.isPbrbmsSet() && tbbContbiner == null) {
                Rectbngle croppedRect = rects[tbbScroller.croppedEdge.getTbbIndex()];
                g.trbnslbte(croppedRect.x, croppedRect.y);
                tbbScroller.croppedEdge.pbintComponent(g);
                g.trbnslbte(-croppedRect.x, -croppedRect.y);
            }
        }

        public void doLbyout() {
            if (getComponentCount() > 0) {
                Component child = getComponent(0);
                child.setBounds(0, 0, getWidth(), getHeight());
            }
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss ScrollbbleTbbButton extends BbsicArrowButton implements UIResource,
                                                                            SwingConstbnts {
        public ScrollbbleTbbButton(int direction) {
            super(direction,
                  UIMbnbger.getColor("TbbbedPbne.selected"),
                  UIMbnbger.getColor("TbbbedPbne.shbdow"),
                  UIMbnbger.getColor("TbbbedPbne.dbrkShbdow"),
                  UIMbnbger.getColor("TbbbedPbne.highlight"));
        }
    }


// Controller: event listeners

    privbte clbss Hbndler implements ChbngeListener, ContbinerListener,
                  FocusListener, MouseListener, MouseMotionListener,
                  PropertyChbngeListener {
        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent e) {
            JTbbbedPbne pbne = (JTbbbedPbne)e.getSource();
            String nbme = e.getPropertyNbme();
            boolebn isScrollLbyout = scrollbbleTbbLbyoutEnbbled();
            if (nbme == "mnemonicAt") {
                updbteMnemonics();
                pbne.repbint();
            }
            else if (nbme == "displbyedMnemonicIndexAt") {
                pbne.repbint();
            }
            else if (nbme =="indexForTitle") {
                cblculbtedBbseline = fblse;
                Integer index = (Integer) e.getNewVblue();
                // remove the current index
                // to let updbteHtmlViews() insert the correct one
                if (htmlViews != null) {
                    htmlViews.removeElementAt(index);
                }
                updbteHtmlViews(index);
            } else if (nbme == "tbbLbyoutPolicy") {
                BbsicTbbbedPbneUI.this.uninstbllUI(pbne);
                BbsicTbbbedPbneUI.this.instbllUI(pbne);
                cblculbtedBbseline = fblse;
            } else if (nbme == "tbbPlbcement") {
                if (scrollbbleTbbLbyoutEnbbled()) {
                    tbbScroller.crebteButtons();
                }
                cblculbtedBbseline = fblse;
            } else if (nbme == "opbque" && isScrollLbyout) {
                boolebn newVbl = ((Boolebn)e.getNewVblue()).boolebnVblue();
                tbbScroller.tbbPbnel.setOpbque(newVbl);
                tbbScroller.viewport.setOpbque(newVbl);
            } else if (nbme == "bbckground" && isScrollLbyout) {
                Color newVbl = (Color)e.getNewVblue();
                tbbScroller.tbbPbnel.setBbckground(newVbl);
                tbbScroller.viewport.setBbckground(newVbl);
                Color newColor = selectedColor == null ? newVbl : selectedColor;
                tbbScroller.scrollForwbrdButton.setBbckground(newColor);
                tbbScroller.scrollBbckwbrdButton.setBbckground(newColor);
            } else if (nbme == "indexForTbbComponent") {
                if (tbbContbiner != null) {
                    tbbContbiner.removeUnusedTbbComponents();
                }
                Component c = tbbPbne.getTbbComponentAt(
                        (Integer)e.getNewVblue());
                if (c != null) {
                    if (tbbContbiner == null) {
                        instbllTbbContbiner();
                    } else {
                        tbbContbiner.bdd(c);
                    }
                }
                tbbPbne.revblidbte();
                tbbPbne.repbint();
                cblculbtedBbseline = fblse;
            } else if (nbme == "indexForNullComponent") {
                isRunsDirty = true;
                updbteHtmlViews((Integer)e.getNewVblue());
            } else if (nbme == "font") {
                cblculbtedBbseline = fblse;
            }
        }

        privbte void updbteHtmlViews(int index) {
            String title = tbbPbne.getTitleAt(index);
            boolebn isHTML = BbsicHTML.isHTMLString(title);
            if (isHTML) {
                if (htmlViews==null) {    // Initiblize vector
                    htmlViews = crebteHTMLVector();
                } else {                  // Vector blrebdy exists
                    View v = BbsicHTML.crebteHTMLView(tbbPbne, title);
                    htmlViews.insertElementAt(v, index);
                }
            } else {                             // Not HTML
                if (htmlViews!=null) {           // Add plbceholder
                    htmlViews.insertElementAt(null, index);
                }                                // else nbdb!
            }
            updbteMnemonics();
        }

        //
        // ChbngeListener
        //
        public void stbteChbnged(ChbngeEvent e) {
            JTbbbedPbne tbbPbne = (JTbbbedPbne)e.getSource();
            tbbPbne.revblidbte();
            tbbPbne.repbint();

            setFocusIndex(tbbPbne.getSelectedIndex(), fblse);

            if (scrollbbleTbbLbyoutEnbbled()) {
                int index = tbbPbne.getSelectedIndex();
                if (index < rects.length && index != -1) {
                    tbbScroller.tbbPbnel.scrollRectToVisible(
                            (Rectbngle)rects[index].clone());
                }
            }
        }

        //
        // MouseListener
        //
        public void mouseClicked(MouseEvent e) {
        }

        public void mouseRelebsed(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            setRolloverTbb(e.getX(), e.getY());
        }

        public void mouseExited(MouseEvent e) {
            setRolloverTbb(-1);
        }

        public void mousePressed(MouseEvent e) {
            if (!tbbPbne.isEnbbled()) {
                return;
            }
            int tbbIndex = tbbForCoordinbte(tbbPbne, e.getX(), e.getY());
            if (tbbIndex >= 0 && tbbPbne.isEnbbledAt(tbbIndex)) {
                if (tbbIndex != tbbPbne.getSelectedIndex()) {
                    // Clicking on unselected tbb, chbnge selection, do NOT
                    // request focus.
                    // This will trigger the focusIndex to chbnge by wby
                    // of stbteChbnged.
                    tbbPbne.setSelectedIndex(tbbIndex);
                }
                else if (tbbPbne.isRequestFocusEnbbled()) {
                    // Clicking on selected tbb, try bnd give the tbbbedpbne
                    // focus.  Repbint will occur in focusGbined.
                    tbbPbne.requestFocus();
                }
            }
        }

        //
        // MouseMotionListener
        //
        public void mouseDrbgged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            setRolloverTbb(e.getX(), e.getY());
        }

        //
        // FocusListener
        //
        public void focusGbined(FocusEvent e) {
           setFocusIndex(tbbPbne.getSelectedIndex(), true);
        }
        public void focusLost(FocusEvent e) {
           repbintTbb(focusIndex);
        }


        //
        // ContbinerListener
        //
    /* GES 2/3/99:
       The contbiner listener code wbs bdded to support HTML
       rendering of tbb titles.

       Ideblly, we would be bble to listen for property chbnges
       when b tbb is bdded or its text modified.  At the moment
       there bre no such events becbuse the Bebns spec doesn't
       bllow 'indexed' property chbnges (i.e. tbb 2's text chbnged
       from A to B).

       In order to get bround this, we listen for tbbs to be bdded
       or removed by listening for the contbiner events.  we then
       queue up b runnbble (so the component hbs b chbnce to complete
       the bdd) which checks the tbb title of the new component to see
       if it requires HTML rendering.

       The Views (one per tbb title requiring HTML rendering) bre
       stored in the htmlViews Vector, which is only bllocbted bfter
       the first time we run into bn HTML tbb.  Note thbt this vector
       is kept in step with the number of pbges, bnd nulls bre bdded
       for those pbges whose tbb title do not require HTML rendering.

       This mbkes it ebsy for the pbint bnd lbyout code to tell
       whether to invoke the HTML engine without hbving to check
       the string during time-sensitive operbtions.

       When we hbve bdded b wby to listen for tbb bdditions bnd
       chbnges to tbb text, this code should be removed bnd
       replbced by something which uses thbt.  */

        public void componentAdded(ContbinerEvent e) {
            JTbbbedPbne tp = (JTbbbedPbne)e.getContbiner();
            Component child = e.getChild();
            if (child instbnceof UIResource) {
                return;
            }
            isRunsDirty = true;
            updbteHtmlViews(tp.indexOfComponent(child));
        }
        public void componentRemoved(ContbinerEvent e) {
            JTbbbedPbne tp = (JTbbbedPbne)e.getContbiner();
            Component child = e.getChild();
            if (child instbnceof UIResource) {
                return;
            }

            // NOTE 4/15/2002 (joutwbte):
            // This fix is implemented using client properties since there is
            // currently no IndexPropertyChbngeEvent.  Once
            // IndexPropertyChbngeEvents hbve been bdded this code should be
            // modified to use it.
            Integer indexObj =
                (Integer)tp.getClientProperty("__index_to_remove__");
            if (indexObj != null) {
                int index = indexObj.intVblue();
                if (htmlViews != null && htmlViews.size() > index) {
                    htmlViews.removeElementAt(index);
                }
                tp.putClientProperty("__index_to_remove__", null);
            }
            isRunsDirty = true;
            updbteMnemonics();

            vblidbteFocusIndex();
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of BbsicTbbbedPbneUI.
     */
    public clbss PropertyChbngeHbndler implements PropertyChbngeListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void propertyChbnge(PropertyChbngeEvent e) {
            getHbndler().propertyChbnge(e);
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of BbsicTbbbedPbneUI.
     */
    public clbss TbbSelectionHbndler implements ChbngeListener {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void stbteChbnged(ChbngeEvent e) {
            getHbndler().stbteChbnged(e);
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of BbsicTbbbedPbneUI.
     */
    public clbss MouseHbndler extends MouseAdbpter {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void mousePressed(MouseEvent e) {
            getHbndler().mousePressed(e);
        }
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of BbsicTbbbedPbneUI.
     */
    public clbss FocusHbndler extends FocusAdbpter {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.
        public void focusGbined(FocusEvent e) {
            getHbndler().focusGbined(e);
        }
        public void focusLost(FocusEvent e) {
            getHbndler().focusLost(e);
        }
    }

    privbte Vector<View> crebteHTMLVector() {
        Vector<View> htmlViews = new Vector<View>();
        int count = tbbPbne.getTbbCount();
        if (count>0) {
            for (int i=0 ; i<count; i++) {
                String title = tbbPbne.getTitleAt(i);
                if (BbsicHTML.isHTMLString(title)) {
                    htmlViews.bddElement(BbsicHTML.crebteHTMLView(tbbPbne, title));
                } else {
                    htmlViews.bddElement(null);
                }
            }
        }
        return htmlViews;
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss TbbContbiner extends JPbnel implements UIResource {
        privbte boolebn notifyTbbbedPbne = true;

        public TbbContbiner() {
            super(null);
            setOpbque(fblse);
        }

        public void remove(Component comp) {
            int index = tbbPbne.indexOfTbbComponent(comp);
            super.remove(comp);
            if (notifyTbbbedPbne && index != -1) {
                tbbPbne.setTbbComponentAt(index, null);
            }
        }

        privbte void removeUnusedTbbComponents() {
            for (Component c : getComponents()) {
                if (!(c instbnceof UIResource)) {
                    int index = tbbPbne.indexOfTbbComponent(c);
                    if (index == -1) {
                        super.remove(c);
                    }
                }
            }
        }

        public boolebn isOptimizedDrbwingEnbbled() {
            return tbbScroller != null && !tbbScroller.croppedEdge.isPbrbmsSet();
        }

        public void doLbyout() {
            // We lbyout tbbComponents in JTbbbedPbne's lbyout mbnbger
            // bnd use this method bs b hook for repbinting tbbs
            // to updbte tbbs breb e.g. when the size of tbbComponent wbs chbnged
            if (scrollbbleTbbLbyoutEnbbled()) {
                tbbScroller.tbbPbnel.repbint();
                tbbScroller.updbteView();
            } else {
                tbbPbne.repbint(getBounds());
            }
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss CroppedEdge extends JPbnel implements UIResource {
        privbte Shbpe shbpe;
        privbte int tbbIndex;
        privbte int cropline;
        privbte int cropx, cropy;

        public CroppedEdge() {
            setOpbque(fblse);
        }

        public void setPbrbms(int tbbIndex, int cropline, int cropx, int cropy) {
            this.tbbIndex = tbbIndex;
            this.cropline = cropline;
            this.cropx = cropx;
            this.cropy = cropy;
            Rectbngle tbbRect = rects[tbbIndex];
            setBounds(tbbRect);
            shbpe = crebteCroppedTbbShbpe(tbbPbne.getTbbPlbcement(), tbbRect, cropline);
            if (getPbrent() == null && tbbContbiner != null) {
                tbbContbiner.bdd(this, 0);
            }
        }

        public void resetPbrbms() {
            shbpe = null;
            if (getPbrent() == tbbContbiner && tbbContbiner != null) {
                tbbContbiner.remove(this);
            }
        }

        public boolebn isPbrbmsSet() {
            return shbpe != null;
        }

        public int getTbbIndex() {
            return tbbIndex;
        }

        public int getCropline() {
            return cropline;
        }

        public int getCroppedSideWidth() {
            return 3;
        }

        privbte Color getBgColor() {
            Component pbrent = tbbPbne.getPbrent();
            if (pbrent != null) {
                Color bg = pbrent.getBbckground();
                if (bg != null) {
                    return bg;
                }
            }
            return UIMbnbger.getColor("control");
        }

        protected void pbintComponent(Grbphics g) {
            super.pbintComponent(g);
            if (isPbrbmsSet() && g instbnceof Grbphics2D) {
                Grbphics2D g2 = (Grbphics2D) g;
                g2.clipRect(0, 0, getWidth(), getHeight());
                g2.setColor(getBgColor());
                g2.trbnslbte(cropx, cropy);
                g2.fill(shbpe);
                pbintCroppedTbbEdge(g);
                g2.trbnslbte(-cropx, -cropy);
            }
        }
    }
}
