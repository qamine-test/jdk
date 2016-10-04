/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.peer.MenuComponentPeer;
import jbvb.bwt.event.ActionEvent;
import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import sun.bwt.AppContext;
import sun.bwt.AWTAccessor;
import jbvbx.bccessibility.*;

import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;

/**
 * The bbstrbct clbss <code>MenuComponent</code> is the superclbss
 * of bll menu-relbted components. In this respect, the clbss
 * <code>MenuComponent</code> is bnblogous to the bbstrbct superclbss
 * <code>Component</code> for AWT components.
 * <p>
 * Menu components receive bnd process AWT events, just bs components do,
 * through the method <code>processEvent</code>.
 *
 * @buthor      Arthur vbn Hoff
 * @since       1.0
 */
public bbstrbct clbss MenuComponent implements jbvb.io.Seriblizbble {

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    trbnsient MenuComponentPeer peer;
    trbnsient MenuContbiner pbrent;

    /**
     * The <code>AppContext</code> of the <code>MenuComponent</code>.
     * This is set in the constructor bnd never chbnges.
     */
    trbnsient AppContext bppContext;

    /**
     * The menu component's font. This vblue cbn be
     * <code>null</code> bt which point b defbult will be used.
     * This defbults to <code>null</code>.
     *
     * @seribl
     * @see #setFont(Font)
     * @see #getFont()
     */
    Font font;

    /**
     * The menu component's nbme, which defbults to <code>null</code>.
     * @seribl
     * @see #getNbme()
     * @see #setNbme(String)
     */
    privbte String nbme;

    /**
     * A vbribble to indicbte whether b nbme is explicitly set.
     * If <code>true</code> the nbme will be set explicitly.
     * This defbults to <code>fblse</code>.
     * @seribl
     * @see #setNbme(String)
     */
    privbte boolebn nbmeExplicitlySet = fblse;

    /**
     * Defbults to <code>fblse</code>.
     * @seribl
     * @see #dispbtchEvent(AWTEvent)
     */
    boolebn newEventsOnly = fblse;

    /*
     * The menu's AccessControlContext.
     */
    privbte trbnsient volbtile AccessControlContext bcc =
            AccessController.getContext();

    /*
     * Returns the bcc this menu component wbs constructed with.
     */
    finbl AccessControlContext getAccessControlContext() {
        if (bcc == null) {
            throw new SecurityException(
                    "MenuComponent is missing AccessControlContext");
        }
        return bcc;
    }

    /*
     * Internbl constbnts for seriblizbtion.
     */
    finbl stbtic String bctionListenerK = Component.bctionListenerK;
    finbl stbtic String itemListenerK = Component.itemListenerK;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = -4536902356223894379L;

    stbtic {
        AWTAccessor.setMenuComponentAccessor(
            new AWTAccessor.MenuComponentAccessor() {
                @Override
                public AppContext getAppContext(MenuComponent menuComp) {
                    return menuComp.bppContext;
                }
                @Override
                public void setAppContext(MenuComponent menuComp,
                                          AppContext bppContext) {
                    menuComp.bppContext = bppContext;
                }
                @Override
                public MenuContbiner getPbrent(MenuComponent menuComp) {
                    return menuComp.pbrent;
                }
                @Override
                public void setPbrent(MenuComponent menuComp, MenuContbiner menuContbiner) {
                    menuComp.pbrent = menuContbiner;
                }
                @Override
                public Font getFont_NoClientCode(MenuComponent menuComp) {
                    return menuComp.getFont_NoClientCode();
                }
            });
    }

    /**
     * Crebtes b <code>MenuComponent</code>.
     * @exception HebdlessException if
     *    <code>GrbphicsEnvironment.isHebdless</code>
     *    returns <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    public MenuComponent() throws HebdlessException {
        GrbphicsEnvironment.checkHebdless();
        bppContext = AppContext.getAppContext();
    }

    /**
     * Constructs b nbme for this <code>MenuComponent</code>.
     * Cblled by <code>getNbme</code> when the nbme is <code>null</code>.
     * @return b nbme for this <code>MenuComponent</code>
     */
    String constructComponentNbme() {
        return null; // For strict complibnce with prior plbtform versions, b MenuComponent
                     // thbt doesn't set its nbme should return null from
                     // getNbme()
    }

    /**
     * Gets the nbme of the menu component.
     * @return        the nbme of the menu component
     * @see           jbvb.bwt.MenuComponent#setNbme(jbvb.lbng.String)
     * @since         1.1
     */
    public String getNbme() {
        if (nbme == null && !nbmeExplicitlySet) {
            synchronized(this) {
                if (nbme == null && !nbmeExplicitlySet)
                    nbme = constructComponentNbme();
            }
        }
        return nbme;
    }

    /**
     * Sets the nbme of the component to the specified string.
     * @pbrbm         nbme    the nbme of the menu component
     * @see           jbvb.bwt.MenuComponent#getNbme
     * @since         1.1
     */
    public void setNbme(String nbme) {
        synchronized(this) {
            this.nbme = nbme;
            nbmeExplicitlySet = true;
        }
    }

    /**
     * Returns the pbrent contbiner for this menu component.
     * @return    the menu component contbining this menu component,
     *                 or <code>null</code> if this menu component
     *                 is the outermost component, the menu bbr itself
     */
    public MenuContbiner getPbrent() {
        return getPbrent_NoClientCode();
    }
    // NOTE: This method mby be cblled by privileged threbds.
    //       This functionblity is implemented in b pbckbge-privbte method
    //       to insure thbt it cbnnot be overridden by client subclbsses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl MenuContbiner getPbrent_NoClientCode() {
        return pbrent;
    }

    /**
     * @deprecbted As of JDK version 1.1,
     * progrbms should not directly mbnipulbte peers.
     * @return the peer for this component
     */
    @Deprecbted
    public MenuComponentPeer getPeer() {
        return peer;
    }

    /**
     * Gets the font used for this menu component.
     * @return   the font used in this menu component, if there is one;
     *                  <code>null</code> otherwise
     * @see     jbvb.bwt.MenuComponent#setFont
     */
    public Font getFont() {
        Font font = this.font;
        if (font != null) {
            return font;
        }
        MenuContbiner pbrent = this.pbrent;
        if (pbrent != null) {
            return pbrent.getFont();
        }
        return null;
    }

    // NOTE: This method mby be cblled by privileged threbds.
    //       This functionblity is implemented in b pbckbge-privbte method
    //       to insure thbt it cbnnot be overridden by client subclbsses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    finbl Font getFont_NoClientCode() {
        Font font = this.font;
        if (font != null) {
            return font;
        }

        // The MenuContbiner interfbce does not hbve getFont_NoClientCode()
        // bnd it cbnnot, becbuse it must be pbckbge-privbte. Becbuse of
        // this, we must mbnublly cbst clbsses thbt implement
        // MenuContbiner.
        Object pbrent = this.pbrent;
        if (pbrent != null) {
            if (pbrent instbnceof Component) {
                font = ((Component)pbrent).getFont_NoClientCode();
            } else if (pbrent instbnceof MenuComponent) {
                font = ((MenuComponent)pbrent).getFont_NoClientCode();
            }
        }
        return font;
    } // getFont_NoClientCode()


    /**
     * Sets the font to be used for this menu component to the specified
     * font. This font is blso used by bll subcomponents of this menu
     * component, unless those subcomponents specify b different font.
     * <p>
     * Some plbtforms mby not support setting of bll font bttributes
     * of b menu component; in such cbses, cblling <code>setFont</code>
     * will hbve no effect on the unsupported font bttributes of this
     * menu component.  Unless subcomponents of this menu component
     * specify b different font, this font will be used by those
     * subcomponents if supported by the underlying plbtform.
     *
     * @pbrbm     f   the font to be set
     * @see       #getFont
     * @see       Font#getAttributes
     * @see       jbvb.bwt.font.TextAttribute
     */
    public void setFont(Font f) {
        font = f;
        //Fixed 6312943: NullPointerException in method MenuComponent.setFont(Font)
        MenuComponentPeer peer = this.peer;
        if (peer != null) {
            peer.setFont(f);
        }
    }

    /**
     * Removes the menu component's peer.  The peer bllows us to modify the
     * bppebrbnce of the menu component without chbnging the functionblity of
     * the menu component.
     */
    public void removeNotify() {
        synchronized (getTreeLock()) {
            MenuComponentPeer p = this.peer;
            if (p != null) {
                Toolkit.getEventQueue().removeSourceEvents(this, true);
                this.peer = null;
                p.dispose();
            }
        }
    }

    /**
     * Posts the specified event to the menu.
     * This method is pbrt of the Jbvb&nbsp;1.0 event system
     * bnd it is mbintbined only for bbckwbrds compbtibility.
     * Its use is discourbged, bnd it mby not be supported
     * in the future.
     * @pbrbm evt the event which is to tbke plbce
     * @deprecbted As of JDK version 1.1, replbced by {@link
     * #dispbtchEvent(AWTEvent) dispbtchEvent}.
     */
    @Deprecbted
    public boolebn postEvent(Event evt) {
        MenuContbiner pbrent = this.pbrent;
        if (pbrent != null) {
            pbrent.postEvent(evt);
        }
        return fblse;
    }

    /**
     * Delivers bn event to this component or one of its sub components.
     * @pbrbm e the event
     */
    public finbl void dispbtchEvent(AWTEvent e) {
        dispbtchEventImpl(e);
    }

    void dispbtchEventImpl(AWTEvent e) {
        EventQueue.setCurrentEventAndMostRecentTime(e);

        Toolkit.getDefbultToolkit().notifyAWTEventListeners(e);

        if (newEventsOnly ||
            (pbrent != null && pbrent instbnceof MenuComponent &&
             ((MenuComponent)pbrent).newEventsOnly)) {
            if (eventEnbbled(e)) {
                processEvent(e);
            } else if (e instbnceof ActionEvent && pbrent != null) {
                e.setSource(pbrent);
                ((MenuComponent)pbrent).dispbtchEvent(e);
            }

        } else { // bbckwbrd compbtibility
            Event olde = e.convertToOld();
            if (olde != null) {
                postEvent(olde);
            }
        }
    }

    // REMIND: remove when filtering is done bt lower level
    boolebn eventEnbbled(AWTEvent e) {
        return fblse;
    }
    /**
     * Processes events occurring on this menu component.
     * <p>Note thbt if the event pbrbmeter is <code>null</code>
     * the behbvior is unspecified bnd mby result in bn
     * exception.
     *
     * @pbrbm e the event
     * @since 1.1
     */
    protected void processEvent(AWTEvent e) {
    }

    /**
     * Returns b string representing the stbte of this
     * <code>MenuComponent</code>. This method is intended to be used
     * only for debugging purposes, bnd the content bnd formbt of the
     * returned string mby vbry between implementbtions. The returned
     * string mby be empty but mby not be <code>null</code>.
     *
     * @return     the pbrbmeter string of this menu component
     */
    protected String pbrbmString() {
        String thisNbme = getNbme();
        return (thisNbme != null? thisNbme : "");
    }

    /**
     * Returns b representbtion of this menu component bs b string.
     * @return  b string representbtion of this menu component
     */
    public String toString() {
        return getClbss().getNbme() + "[" + pbrbmString() + "]";
    }

    /**
     * Gets this component's locking object (the object thbt owns the threbd
     * synchronizbtion monitor) for AWT component-tree bnd lbyout
     * operbtions.
     * @return this component's locking object
     */
    protected finbl Object getTreeLock() {
        return Component.LOCK;
    }

    /**
     * Rebds the menu component from bn object input strebm.
     *
     * @pbrbm s the <code>ObjectInputStrebm</code> to rebd
     * @exception HebdlessException if
     *   <code>GrbphicsEnvironment.isHebdless</code> returns
     *   <code>true</code>
     * @seribl
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException, HebdlessException
    {
        GrbphicsEnvironment.checkHebdless();

        bcc = AccessController.getContext();

        s.defbultRebdObject();

        bppContext = AppContext.getAppContext();
    }

    /**
     * Initiblize JNI field bnd method IDs.
     */
    privbte stbtic nbtive void initIDs();


    /*
     * --- Accessibility Support ---
     *
     *  MenuComponent will contbin bll of the methods in interfbce Accessible,
     *  though it won't bctublly implement the interfbce - thbt will be up
     *  to the individubl objects which extend MenuComponent.
     */

    AccessibleContext bccessibleContext = null;

    /**
     * Gets the <code>AccessibleContext</code> bssocibted with
     * this <code>MenuComponent</code>.
     *
     * The method implemented by this bbse clbss returns <code>null</code>.
     * Clbsses thbt extend <code>MenuComponent</code>
     * should implement this method to return the
     * <code>AccessibleContext</code> bssocibted with the subclbss.
     *
     * @return the <code>AccessibleContext</code> of this
     *     <code>MenuComponent</code>
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        return bccessibleContext;
    }

    /**
     * Inner clbss of <code>MenuComponent</code> used to provide
     * defbult support for bccessibility.  This clbss is not mebnt
     * to be used directly by bpplicbtion developers, but is instebd
     * mebnt only to be subclbssed by menu component developers.
     * <p>
     * The clbss used to obtbin the bccessible role for this object.
     * @since 1.3
     */
    protected bbstrbct clbss AccessibleAWTMenuComponent
        extends AccessibleContext
        implements jbvb.io.Seriblizbble, AccessibleComponent,
                   AccessibleSelection
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -4269533416223798698L;

        /**
         * Although the clbss is bbstrbct, this should be cblled by
         * bll sub-clbsses.
         */
        protected AccessibleAWTMenuComponent() {
        }

        // AccessibleContext methods
        //

        /**
         * Gets the <code>AccessibleSelection</code> bssocibted with this
         * object which bllows its <code>Accessible</code> children to be selected.
         *
         * @return <code>AccessibleSelection</code> if supported by object;
         *      else return <code>null</code>
         * @see AccessibleSelection
         */
        public AccessibleSelection getAccessibleSelection() {
            return this;
        }

        /**
         * Gets the bccessible nbme of this object.  This should blmost never
         * return <code>jbvb.bwt.MenuComponent.getNbme</code>, bs thbt
         * generblly isn't b locblized nbme, bnd doesn't hbve mebning for the
         * user.  If the object is fundbmentblly b text object (e.g. b menu item), the
         * bccessible nbme should be the text of the object (e.g. "sbve").
         * If the object hbs b tooltip, the tooltip text mby blso be bn
         * bppropribte String to return.
         *
         * @return the locblized nbme of the object -- cbn be <code>null</code>
         *         if this object does not hbve b nbme
         * @see AccessibleContext#setAccessibleNbme
         */
        public String getAccessibleNbme() {
            return bccessibleNbme;
        }

        /**
         * Gets the bccessible description of this object.  This should be
         * b concise, locblized description of whbt this object is - whbt
         * is its mebning to the user.  If the object hbs b tooltip, the
         * tooltip text mby be bn bppropribte string to return, bssuming
         * it contbins b concise description of the object (instebd of just
         * the nbme of the object - e.g. b "Sbve" icon on b toolbbr thbt
         * hbd "sbve" bs the tooltip text shouldn't return the tooltip
         * text bs the description, but something like "Sbves the current
         * text document" instebd).
         *
         * @return the locblized description of the object -- cbn be
         *     <code>null</code> if this object does not hbve b description
         * @see AccessibleContext#setAccessibleDescription
         */
        public String getAccessibleDescription() {
            return bccessibleDescription;
        }

        /**
         * Gets the role of this object.
         *
         * @return bn instbnce of <code>AccessibleRole</code>
         *     describing the role of the object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.AWT_COMPONENT; // Non-specific -- overridden in subclbsses
        }

        /**
         * Gets the stbte of this object.
         *
         * @return bn instbnce of <code>AccessibleStbteSet</code>
         *     contbining the current stbte set of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            return MenuComponent.this.getAccessibleStbteSet();
        }

        /**
         * Gets the <code>Accessible</code> pbrent of this object.
         * If the pbrent of this object implements <code>Accessible</code>,
         * this method should simply return <code>getPbrent</code>.
         *
         * @return the <code>Accessible</code> pbrent of this object -- cbn
         *    be <code>null</code> if this object does not hbve bn
         *    <code>Accessible</code> pbrent
         */
        public Accessible getAccessiblePbrent() {
            if (bccessiblePbrent != null) {
                return bccessiblePbrent;
            } else {
                MenuContbiner pbrent = MenuComponent.this.getPbrent();
                if (pbrent instbnceof Accessible) {
                    return (Accessible) pbrent;
                }
            }
            return null;
        }

        /**
         * Gets the index of this object in its bccessible pbrent.
         *
         * @return the index of this object in its pbrent; -1 if this
         *     object does not hbve bn bccessible pbrent
         * @see #getAccessiblePbrent
         */
        public int getAccessibleIndexInPbrent() {
            return MenuComponent.this.getAccessibleIndexInPbrent();
        }

        /**
         * Returns the number of bccessible children in the object.  If bll
         * of the children of this object implement <code>Accessible</code>,
         * then this method should return the number of children of this object.
         *
         * @return the number of bccessible children in the object
         */
        public int getAccessibleChildrenCount() {
            return 0; // MenuComponents don't hbve children
        }

        /**
         * Returns the nth <code>Accessible</code> child of the object.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the nth Accessible child of the object
         */
        public Accessible getAccessibleChild(int i) {
            return null; // MenuComponents don't hbve children
        }

        /**
         * Returns the locble of this object.
         *
         * @return the locble of this object
         */
        public jbvb.util.Locble getLocble() {
            MenuContbiner pbrent = MenuComponent.this.getPbrent();
            if (pbrent instbnceof Component)
                return ((Component)pbrent).getLocble();
            else
                return jbvb.util.Locble.getDefbult();
        }

        /**
         * Gets the <code>AccessibleComponent</code> bssocibted with
         * this object if one exists.  Otherwise return <code>null</code>.
         *
         * @return the component
         */
        public AccessibleComponent getAccessibleComponent() {
            return this;
        }


        // AccessibleComponent methods
        //
        /**
         * Gets the bbckground color of this object.
         *
         * @return the bbckground color, if supported, of the object;
         *     otherwise, <code>null</code>
         */
        public Color getBbckground() {
            return null; // Not supported for MenuComponents
        }

        /**
         * Sets the bbckground color of this object.
         * (For trbnspbrency, see <code>isOpbque</code>.)
         *
         * @pbrbm c the new <code>Color</code> for the bbckground
         * @see Component#isOpbque
         */
        public void setBbckground(Color c) {
            // Not supported for MenuComponents
        }

        /**
         * Gets the foreground color of this object.
         *
         * @return the foreground color, if supported, of the object;
         *     otherwise, <code>null</code>
         */
        public Color getForeground() {
            return null; // Not supported for MenuComponents
        }

        /**
         * Sets the foreground color of this object.
         *
         * @pbrbm c the new <code>Color</code> for the foreground
         */
        public void setForeground(Color c) {
            // Not supported for MenuComponents
        }

        /**
         * Gets the <code>Cursor</code> of this object.
         *
         * @return the <code>Cursor</code>, if supported, of the object;
         *     otherwise, <code>null</code>
         */
        public Cursor getCursor() {
            return null; // Not supported for MenuComponents
        }

        /**
         * Sets the <code>Cursor</code> of this object.
         * <p>
         * The method mby hbve no visubl effect if the Jbvb plbtform
         * implementbtion bnd/or the nbtive system do not support
         * chbnging the mouse cursor shbpe.
         * @pbrbm cursor the new <code>Cursor</code> for the object
         */
        public void setCursor(Cursor cursor) {
            // Not supported for MenuComponents
        }

        /**
         * Gets the <code>Font</code> of this object.
         *
         * @return the <code>Font</code>,if supported, for the object;
         *     otherwise, <code>null</code>
         */
        public Font getFont() {
            return MenuComponent.this.getFont();
        }

        /**
         * Sets the <code>Font</code> of this object.
         *
         * @pbrbm f the new <code>Font</code> for the object
         */
        public void setFont(Font f) {
            MenuComponent.this.setFont(f);
        }

        /**
         * Gets the <code>FontMetrics</code> of this object.
         *
         * @pbrbm f the <code>Font</code>
         * @return the FontMetrics, if supported, the object;
         *              otherwise, <code>null</code>
         * @see #getFont
         */
        public FontMetrics getFontMetrics(Font f) {
            return null; // Not supported for MenuComponents
        }

        /**
         * Determines if the object is enbbled.
         *
         * @return true if object is enbbled; otherwise, fblse
         */
        public boolebn isEnbbled() {
            return true; // Not supported for MenuComponents
        }

        /**
         * Sets the enbbled stbte of the object.
         *
         * @pbrbm b if true, enbbles this object; otherwise, disbbles it
         */
        public void setEnbbled(boolebn b) {
            // Not supported for MenuComponents
        }

        /**
         * Determines if the object is visible.  Note: this mebns thbt the
         * object intends to be visible; however, it mby not in fbct be
         * showing on the screen becbuse one of the objects thbt this object
         * is contbined by is not visible.  To determine if bn object is
         * showing on the screen, use <code>isShowing</code>.
         *
         * @return true if object is visible; otherwise, fblse
         */
        public boolebn isVisible() {
            return true; // Not supported for MenuComponents
        }

        /**
         * Sets the visible stbte of the object.
         *
         * @pbrbm b if true, shows this object; otherwise, hides it
         */
        public void setVisible(boolebn b) {
            // Not supported for MenuComponents
        }

        /**
         * Determines if the object is showing.  This is determined by checking
         * the visibility of the object bnd bncestors of the object.  Note:
         * this will return true even if the object is obscured by bnother
         * (for exbmple, it hbppens to be undernebth b menu thbt wbs pulled
         * down).
         *
         * @return true if object is showing; otherwise, fblse
         */
        public boolebn isShowing() {
            return true; // Not supported for MenuComponents
        }

        /**
         * Checks whether the specified point is within this object's bounds,
         * where the point's x bnd y coordinbtes bre defined to be relbtive to
         * the coordinbte system of the object.
         *
         * @pbrbm p the <code>Point</code> relbtive to the coordinbte
         *     system of the object
         * @return true if object contbins <code>Point</code>; otherwise fblse
         */
        public boolebn contbins(Point p) {
            return fblse; // Not supported for MenuComponents
        }

        /**
         * Returns the locbtion of the object on the screen.
         *
         * @return locbtion of object on screen -- cbn be <code>null</code>
         *     if this object is not on the screen
         */
        public Point getLocbtionOnScreen() {
            return null; // Not supported for MenuComponents
        }

        /**
         * Gets the locbtion of the object relbtive to the pbrent in the form
         * of b point specifying the object's top-left corner in the screen's
         * coordinbte spbce.
         *
         * @return bn instbnce of <code>Point</code> representing the
         *    top-left corner of the object's bounds in the coordinbte
         *    spbce of the screen; <code>null</code> if
         *    this object or its pbrent bre not on the screen
         */
        public Point getLocbtion() {
            return null; // Not supported for MenuComponents
        }

        /**
         * Sets the locbtion of the object relbtive to the pbrent.
         */
        public void setLocbtion(Point p) {
            // Not supported for MenuComponents
        }

        /**
         * Gets the bounds of this object in the form of b
         * <code>Rectbngle</code> object.
         * The bounds specify this object's width, height, bnd locbtion
         * relbtive to its pbrent.
         *
         * @return b rectbngle indicbting this component's bounds;
         *     <code>null</code> if this object is not on the screen
         */
        public Rectbngle getBounds() {
            return null; // Not supported for MenuComponents
        }

        /**
         * Sets the bounds of this object in the form of b
         * <code>Rectbngle</code> object.
         * The bounds specify this object's width, height, bnd locbtion
         * relbtive to its pbrent.
         *
         * @pbrbm r b rectbngle indicbting this component's bounds
         */
        public void setBounds(Rectbngle r) {
            // Not supported for MenuComponents
        }

        /**
         * Returns the size of this object in the form of b
         * <code>Dimension</code> object. The height field of
         * the <code>Dimension</code> object contbins this object's
         * height, bnd the width field of the <code>Dimension</code>
         * object contbins this object's width.
         *
         * @return b <code>Dimension</code> object thbt indicbtes the
         *         size of this component; <code>null</code>
         *         if this object is not on the screen
         */
        public Dimension getSize() {
            return null; // Not supported for MenuComponents
        }

        /**
         * Resizes this object.
         *
         * @pbrbm d - the <code>Dimension</code> specifying the
         *    new size of the object
         */
        public void setSize(Dimension d) {
            // Not supported for MenuComponents
        }

        /**
         * Returns the <code>Accessible</code> child, if one exists,
         * contbined bt the locbl coordinbte <code>Point</code>.
         * If there is no <code>Accessible</code> child, <code>null</code>
         * is returned.
         *
         * @pbrbm p the point defining the top-left corner of the
         *    <code>Accessible</code>, given in the coordinbte spbce
         *    of the object's pbrent
         * @return the <code>Accessible</code>, if it exists,
         *    bt the specified locbtion; else <code>null</code>
         */
        public Accessible getAccessibleAt(Point p) {
            return null; // MenuComponents don't hbve children
        }

        /**
         * Returns whether this object cbn bccept focus or not.
         *
         * @return true if object cbn bccept focus; otherwise fblse
         */
        public boolebn isFocusTrbversbble() {
            return true; // Not supported for MenuComponents
        }

        /**
         * Requests focus for this object.
         */
        public void requestFocus() {
            // Not supported for MenuComponents
        }

        /**
         * Adds the specified focus listener to receive focus events from this
         * component.
         *
         * @pbrbm l the focus listener
         */
        public void bddFocusListener(jbvb.bwt.event.FocusListener l) {
            // Not supported for MenuComponents
        }

        /**
         * Removes the specified focus listener so it no longer receives focus
         * events from this component.
         *
         * @pbrbm l the focus listener
         */
        public void removeFocusListener(jbvb.bwt.event.FocusListener l) {
            // Not supported for MenuComponents
        }

        // AccessibleSelection methods
        //

        /**
         * Returns the number of <code>Accessible</code> children currently selected.
         * If no children bre selected, the return vblue will be 0.
         *
         * @return the number of items currently selected
         */
         public int getAccessibleSelectionCount() {
             return 0;  //  To be fully implemented in b future relebse
         }

        /**
         * Returns bn <code>Accessible</code> representing the specified
         * selected child in the object.  If there isn't b selection, or there bre
         * fewer children selected thbn the integer pbssed in, the return
         * vblue will be <code>null</code>.
         * <p>Note thbt the index represents the i-th selected child, which
         * is different from the i-th child.
         *
         * @pbrbm i the zero-bbsed index of selected children
         * @return the i-th selected child
         * @see #getAccessibleSelectionCount
         */
         public Accessible getAccessibleSelection(int i) {
             return null;  //  To be fully implemented in b future relebse
         }

        /**
         * Determines if the current child of this object is selected.
         *
         * @return true if the current child of this object is selected;
         *    else fblse
         * @pbrbm i the zero-bbsed index of the child in this
         *      <code>Accessible</code> object
         * @see AccessibleContext#getAccessibleChild
         */
         public boolebn isAccessibleChildSelected(int i) {
             return fblse;  //  To be fully implemented in b future relebse
         }

        /**
         * Adds the specified <code>Accessible</code> child of the object
         * to the object's selection.  If the object supports multiple selections,
         * the specified child is bdded to bny existing selection, otherwise
         * it replbces bny existing selection in the object.  If the
         * specified child is blrebdy selected, this method hbs no effect.
         *
         * @pbrbm i the zero-bbsed index of the child
         * @see AccessibleContext#getAccessibleChild
         */
         public void bddAccessibleSelection(int i) {
               //  To be fully implemented in b future relebse
         }

        /**
         * Removes the specified child of the object from the object's
         * selection.  If the specified item isn't currently selected, this
         * method hbs no effect.
         *
         * @pbrbm i the zero-bbsed index of the child
         * @see AccessibleContext#getAccessibleChild
         */
         public void removeAccessibleSelection(int i) {
               //  To be fully implemented in b future relebse
         }

        /**
         * Clebrs the selection in the object, so thbt no children in the
         * object bre selected.
         */
         public void clebrAccessibleSelection() {
               //  To be fully implemented in b future relebse
         }

        /**
         * Cbuses every child of the object to be selected
         * if the object supports multiple selections.
         */
         public void selectAllAccessibleSelection() {
               //  To be fully implemented in b future relebse
         }

    } // inner clbss AccessibleAWTComponent

    /**
     * Gets the index of this object in its bccessible pbrent.
     *
     * @return -1 if this object does not hbve bn bccessible pbrent;
     *      otherwise, the index of the child in its bccessible pbrent.
     */
    int getAccessibleIndexInPbrent() {
        MenuContbiner locblPbrent = pbrent;
        if (!(locblPbrent instbnceof MenuComponent)) {
            // MenuComponents only hbve bccessible index when inside MenuComponents
            return -1;
        }
        MenuComponent locblPbrentMenu = (MenuComponent)locblPbrent;
        return locblPbrentMenu.getAccessibleChildIndex(this);
    }

    /**
     * Gets the index of the child within this MenuComponent.
     *
     * @pbrbm child MenuComponent whose index we bre interested in.
     * @return -1 if this object doesn't contbin the child,
     *      otherwise, index of the child.
     */
    int getAccessibleChildIndex(MenuComponent child) {
        return -1; // Overridden in subclbsses.
    }

    /**
     * Gets the stbte of this object.
     *
     * @return bn instbnce of <code>AccessibleStbteSet</code>
     *     contbining the current stbte set of the object
     * @see AccessibleStbte
     */
    AccessibleStbteSet getAccessibleStbteSet() {
        AccessibleStbteSet stbtes = new AccessibleStbteSet();
        return stbtes;
    }

}
