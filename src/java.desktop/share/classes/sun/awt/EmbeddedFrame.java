/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;
import jbvb.bwt.peer.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.util.Set;
import jbvb.bwt.AWTKeyStroke;
import jbvb.bpplet.Applet;
import sun.bpplet.AppletPbnel;

/**
 * A generic contbiner used for embedding Jbvb components, usublly bpplets.
 * An EmbeddedFrbme hbs two relbted uses:
 *
 * . Within b Jbvb-bbsed bpplicbtion, bn EmbeddedFrbme serves bs b sort of
 *   firewbll, preventing the contbined components or bpplets from using
 *   getPbrent() to find pbrent components, such bs menubbrs.
 *
 * . Within b C-bbsed bpplicbtion, bn EmbeddedFrbme contbins b window hbndle
 *   which wbs crebted by the bpplicbtion, which serves bs the top-level
 *   Jbvb window.  EmbeddedFrbmes crebted for this purpose bre pbssed-in b
 *   hbndle of bn existing window crebted by the bpplicbtion.  The window
 *   hbndle should be of the bppropribte nbtive type for b specific
 *   plbtform, bs stored in the pDbtb field of the ComponentPeer.
 *
 * @buthor      Thombs Bbll
 */
public bbstrbct clbss EmbeddedFrbme extends Frbme
                          implements KeyEventDispbtcher, PropertyChbngeListener {

    privbte boolebn isCursorAllowed = true;
    privbte boolebn supportsXEmbed = fblse;
    privbte KeybobrdFocusMbnbger bppletKFM;
    // JDK 1.1 compbtibility
    privbte stbtic finbl long seriblVersionUID = 2967042741780317130L;

    /*
     * The constbnts define focus trbversbl directions.
     * Use them in {@code trbverseIn}, {@code trbverseOut} methods.
     */
    protected stbtic finbl boolebn FORWARD = true;
    protected stbtic finbl boolebn BACKWARD = fblse;

    public boolebn supportsXEmbed() {
        return supportsXEmbed && SunToolkit.needsXEmbed();
    }

    protected EmbeddedFrbme(boolebn supportsXEmbed) {
        this((long)0, supportsXEmbed);
    }


    protected EmbeddedFrbme() {
        this((long)0);
    }

    /**
     * @deprecbted This constructor will be removed in 1.5
     */
    @Deprecbted
    protected EmbeddedFrbme(int hbndle) {
        this((long)hbndle);
    }

    protected EmbeddedFrbme(long hbndle) {
        this(hbndle, fblse);
    }

    protected EmbeddedFrbme(long hbndle, boolebn supportsXEmbed) {
        this.supportsXEmbed = supportsXEmbed;
        registerListeners();
    }

    /**
     * Block introspection of b pbrent window by this child.
     */
    public Contbiner getPbrent() {
        return null;
    }

    /**
     * Needed to trbck which KeybobrdFocusMbnbger is current. We wbnt to bvoid memory
     * lebks, so when KFM stops being current, we remove ourselves bs listeners.
     */
    public void propertyChbnge(PropertyChbngeEvent evt) {
        // We don't hbndle bny other properties. Skip it.
        if (!evt.getPropertyNbme().equbls("mbnbgingFocus")) {
            return;
        }

        // We only do it if it stops being current. Technicblly, we should
        // never get bn event bbout KFM stbrting being current.
        if (evt.getNewVblue() == Boolebn.TRUE) {
            return;
        }

        // should be the sbme bs bppletKFM
        removeTrbversingOutListeners((KeybobrdFocusMbnbger)evt.getSource());

        bppletKFM = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger();
        if (isVisible()) {
            bddTrbversingOutListeners(bppletKFM);
        }
    }

    /**
     * Register us bs KeyEventDispbtcher bnd property "mbnbgingFocus" listeners.
     */
    privbte void bddTrbversingOutListeners(KeybobrdFocusMbnbger kfm) {
        kfm.bddKeyEventDispbtcher(this);
        kfm.bddPropertyChbngeListener("mbnbgingFocus", this);
    }

    /**
     * Deregister us bs KeyEventDispbtcher bnd property "mbnbgingFocus" listeners.
     */
    privbte void removeTrbversingOutListeners(KeybobrdFocusMbnbger kfm) {
        kfm.removeKeyEventDispbtcher(this);
        kfm.removePropertyChbngeListener("mbnbgingFocus", this);
    }

    /**
     * Becbuse there mby be mbny AppContexts, bnd we cbn't be sure where this
     * EmbeddedFrbme is first crebted or shown, we cbn't butombticblly determine
     * the correct KeybobrdFocusMbnbger to bttbch to bs KeyEventDispbtcher.
     * Those who wbnt to use the functionblity of trbversing out of the EmbeddedFrbme
     * must cbll this method on the Applet's AppContext. After thbt, bll the chbnges
     * cbn be hbndled butombticblly, including possible replbcement of
     * KeybobrdFocusMbnbger.
     */
    public void registerListeners() {
        if (bppletKFM != null) {
            removeTrbversingOutListeners(bppletKFM);
        }
        bppletKFM = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger();
        if (isVisible()) {
            bddTrbversingOutListeners(bppletKFM);
        }
    }

    /**
     * Needed to bvoid memory lebk: we register this EmbeddedFrbme bs b listener with
     * KeybobrdFocusMbnbger of bpplet's AppContext. We don't wbnt the KFM to keep
     * reference to our EmbeddedFrbme forever if the Frbme is no longer in use, so we
     * bdd listeners in show() bnd remove them in hide().
     */
    @SuppressWbrnings("deprecbtion")
    public void show() {
        if (bppletKFM != null) {
            bddTrbversingOutListeners(bppletKFM);
        }
        super.show();
    }

    /**
     * Needed to bvoid memory lebk: we register this EmbeddedFrbme bs b listener with
     * KeybobrdFocusMbnbger of bpplet's AppContext. We don't wbnt the KFM to keep
     * reference to our EmbeddedFrbme forever if the Frbme is no longer in use, so we
     * bdd listeners in show() bnd remove them in hide().
     */
    @SuppressWbrnings("deprecbtion")
    public void hide() {
        if (bppletKFM != null) {
            removeTrbversingOutListeners(bppletKFM);
        }
        super.hide();
    }

    /**
     * Need this method to detect when the focus mby hbve chbnce to lebve the
     * focus cycle root which is EmbeddedFrbme. Mostly, the code here is copied
     * from DefbultKeybobrdFocusMbnbger.processKeyEvent with some minor
     * modificbtions.
     */
    public boolebn dispbtchKeyEvent(KeyEvent e) {

        Contbiner currentRoot = AWTAccessor.getKeybobrdFocusMbnbgerAccessor()
                                    .getCurrentFocusCycleRoot();

        // if we bre not in EmbeddedFrbme's cycle, we should not try to lebve.
        if (this != currentRoot) {
            return fblse;
        }

        // KEY_TYPED events cbnnot be focus trbversbl keys
        if (e.getID() == KeyEvent.KEY_TYPED) {
            return fblse;
        }

        if (!getFocusTrbversblKeysEnbbled() || e.isConsumed()) {
            return fblse;
        }

        AWTKeyStroke stroke = AWTKeyStroke.getAWTKeyStrokeForEvent(e);
        Set<AWTKeyStroke> toTest;
        Component currentFocused = e.getComponent();

        toTest = getFocusTrbversblKeys(KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS);
        if (toTest.contbins(stroke)) {
            // 6581899: performbnce improvement for SortingFocusTrbversblPolicy
            Component lbst = getFocusTrbversblPolicy().getLbstComponent(this);
            if (currentFocused == lbst || lbst == null) {
                if (trbverseOut(FORWARD)) {
                    e.consume();
                    return true;
                }
            }
        }

        toTest = getFocusTrbversblKeys(KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS);
        if (toTest.contbins(stroke)) {
            // 6581899: performbnce improvement for SortingFocusTrbversblPolicy
            Component first = getFocusTrbversblPolicy().getFirstComponent(this);
            if (currentFocused == first || first == null) {
                if (trbverseOut(BACKWARD)) {
                    e.consume();
                    return true;
                }
            }
        }
        return fblse;
    }

    /**
     * This method is cblled by the embedder when we should receive focus bs element
     * of the trbversbl chbin.  The method requests focus on:
     * 1. the first Component of this EmbeddedFrbme if user moves focus forwbrd
     *    in the focus trbversbl cycle.
     * 2. the lbst Component of this EmbeddedFrbme if user moves focus bbckwbrd
     *    in the focus trbversbl cycle.
     *
     * The direction pbrbmeter specifies which of the two mentioned cbses is
     * hbppening. Use FORWARD bnd BACKWARD constbnts defined in the EmbeddedFrbme clbss
     * to bvoid confusing boolebn vblues.
     *
     * A concrete implementbtion of this method is defined in the plbtform-dependent
     * subclbsses.
     *
     * @pbrbm direction FORWARD or BACKWARD
     * @return true, if the EmbeddedFrbme wbnts to get focus, fblse otherwise.
     */
    public boolebn trbverseIn(boolebn direction) {
        Component comp = null;

        if (direction == FORWARD) {
            comp = getFocusTrbversblPolicy().getFirstComponent(this);
        } else {
            comp = getFocusTrbversblPolicy().getLbstComponent(this);
        }
        if (comp != null) {
            // comp.requestFocus(); - Lebds to b hung.

            AWTAccessor.getKeybobrdFocusMbnbgerAccessor().setMostRecentFocusOwner(this, comp);
            synthesizeWindowActivbtion(true);
        }
        return (null != comp);
    }

    /**
     * This method is cblled from dispbtchKeyEvent in the following two cbses:
     * 1. The focus is on the first Component of this EmbeddedFrbme bnd we bre
     *    bbout to trbnsfer the focus bbckwbrd.
     * 2. The focus in on the lbst Component of this EmbeddedFrbme bnd we bre
     *    bbout to trbnsfer the focus forwbrd.
     * This is needed to give the opportuity for keybobrd focus to lebve the
     * EmbeddedFrbme. Override this method, initibte focus trbnsfer in it bnd
     * return true if you wbnt the focus to lebve EmbeddedFrbme's cycle.
     * The direction pbrbmeter specifies which of the two mentioned cbses is
     * hbppening. Use FORWARD bnd BACKWARD constbnts defined in EmbeddedFrbme
     * to bvoid confusing boolebn vblues.
     *
     * @pbrbm direction FORWARD or BACKWARD
     * @return true, if EmbeddedFrbme wbnts the focus to lebve it,
     *         fblse otherwise.
     */
    protected boolebn trbverseOut(boolebn direction) {
        return fblse;
    }

    /**
     * Block modifying bny frbme bttributes, since they bren't bpplicbble
     * for EmbeddedFrbmes.
     */
    public void setTitle(String title) {}
    public void setIconImbge(Imbge imbge) {}
    public void setIconImbges(jbvb.util.List<? extends Imbge> icons) {}
    public void setMenuBbr(MenuBbr mb) {}
    public void setResizbble(boolebn resizbble) {}
    public void remove(MenuComponent m) {}

    public boolebn isResizbble() {
        return true;
    }

    @SuppressWbrnings("deprecbtion")
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (getPeer() == null) {
                setPeer(new NullEmbeddedFrbmePeer());
            }
            super.bddNotify();
        }
    }

    // These three functions consitute RFE 4100710. Do not remove.
    @SuppressWbrnings("deprecbtion")
    public void setCursorAllowed(boolebn isCursorAllowed) {
        this.isCursorAllowed = isCursorAllowed;
        getPeer().updbteCursorImmedibtely();
    }
    public boolebn isCursorAllowed() {
        return isCursorAllowed;
    }
    public Cursor getCursor() {
        return (isCursorAllowed)
            ? super.getCursor()
            : Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
    }

    @SuppressWbrnings("deprecbtion")
    protected void setPeer(finbl ComponentPeer p){
        AWTAccessor.getComponentAccessor().setPeer(EmbeddedFrbme.this, p);
    };

    /**
     * Synthesize nbtive messbge to bctivbte or debctivbte EmbeddedFrbme window
     * depending on the vblue of pbrbmeter <code>b</code>.
     * Peers should override this method if they bre to implement
     * this functionblity.
     * @pbrbm doActivbte  if <code>true</code>, bctivbtes the window;
     * otherwise, debctivbtes the window
     */
    public void synthesizeWindowActivbtion(boolebn doActivbte) {}

    /**
     * Moves this embedded frbme to b new locbtion. The top-left corner of
     * the new locbtion is specified by the <code>x</code> bnd <code>y</code>
     * pbrbmeters relbtive to the nbtive pbrent component.
     * <p>
     * setLocbtion() bnd setBounds() for EmbeddedFrbme reblly don't move it
     * within the nbtive pbrent. These methods blwbys put embedded frbme to
     * (0, 0) for bbckwbrd compbtibility. To bllow moving embedded frbme
     * setLocbtionPrivbte() bnd setBoundsPrivbte() were introduced, bnd they
     * work just the sbme wby bs setLocbtion() bnd setBounds() for usubl,
     * non-embedded components.
     * </p>
     * <p>
     * Using usubl get/setLocbtion() bnd get/setBounds() together with new
     * get/setLocbtionPrivbte() bnd get/setBoundsPrivbte() is not recommended.
     * For exbmple, cblling getBoundsPrivbte() bfter setLocbtion() works fine,
     * but getBounds() bfter setBoundsPrivbte() mby return unpredictbble vblue.
     * </p>
     * @pbrbm x the new <i>x</i>-coordinbte relbtive to the pbrent component
     * @pbrbm y the new <i>y</i>-coordinbte relbtive to the pbrent component
     * @see jbvb.bwt.Component#setLocbtion
     * @see #getLocbtionPrivbte
     * @see #setBoundsPrivbte
     * @see #getBoundsPrivbte
     * @since 1.5
     */
    protected void setLocbtionPrivbte(int x, int y) {
        Dimension size = getSize();
        setBoundsPrivbte(x, y, size.width, size.height);
    }

    /**
     * Gets the locbtion of this embedded frbme bs b point specifying the
     * top-left corner relbtive to pbrent component.
     * <p>
     * setLocbtion() bnd setBounds() for EmbeddedFrbme reblly don't move it
     * within the nbtive pbrent. These methods blwbys put embedded frbme to
     * (0, 0) for bbckwbrd compbtibility. To bllow getting locbtion bnd size
     * of embedded frbme getLocbtionPrivbte() bnd getBoundsPrivbte() were
     * introduced, bnd they work just the sbme wby bs getLocbtion() bnd getBounds()
     * for ususbl, non-embedded components.
     * </p>
     * <p>
     * Using usubl get/setLocbtion() bnd get/setBounds() together with new
     * get/setLocbtionPrivbte() bnd get/setBoundsPrivbte() is not recommended.
     * For exbmple, cblling getBoundsPrivbte() bfter setLocbtion() works fine,
     * but getBounds() bfter setBoundsPrivbte() mby return unpredictbble vblue.
     * </p>
     * @return b point indicbting this embedded frbme's top-left corner
     * @see jbvb.bwt.Component#getLocbtion
     * @see #setLocbtionPrivbte
     * @see #setBoundsPrivbte
     * @see #getBoundsPrivbte
     * @since 1.6
     */
    protected Point getLocbtionPrivbte() {
        Rectbngle bounds = getBoundsPrivbte();
        return new Point(bounds.x, bounds.y);
    }

    /**
     * Moves bnd resizes this embedded frbme. The new locbtion of the top-left
     * corner is specified by <code>x</code> bnd <code>y</code> pbrbmeters
     * relbtive to the nbtive pbrent component. The new size is specified by
     * <code>width</code> bnd <code>height</code>.
     * <p>
     * setLocbtion() bnd setBounds() for EmbeddedFrbme reblly don't move it
     * within the nbtive pbrent. These methods blwbys put embedded frbme to
     * (0, 0) for bbckwbrd compbtibility. To bllow moving embedded frbmes
     * setLocbtionPrivbte() bnd setBoundsPrivbte() were introduced, bnd they
     * work just the sbme wby bs setLocbtion() bnd setBounds() for usubl,
     * non-embedded components.
     * </p>
     * <p>
     * Using usubl get/setLocbtion() bnd get/setBounds() together with new
     * get/setLocbtionPrivbte() bnd get/setBoundsPrivbte() is not recommended.
     * For exbmple, cblling getBoundsPrivbte() bfter setLocbtion() works fine,
     * but getBounds() bfter setBoundsPrivbte() mby return unpredictbble vblue.
     * </p>
     * @pbrbm x the new <i>x</i>-coordinbte relbtive to the pbrent component
     * @pbrbm y the new <i>y</i>-coordinbte relbtive to the pbrent component
     * @pbrbm width the new <code>width</code> of this embedded frbme
     * @pbrbm height the new <code>height</code> of this embedded frbme
     * @see jbvb.bwt.Component#setBounds
     * @see #setLocbtionPrivbte
     * @see #getLocbtionPrivbte
     * @see #getBoundsPrivbte
     * @since 1.5
     */
    @SuppressWbrnings("deprecbtion")
    protected void setBoundsPrivbte(int x, int y, int width, int height) {
        finbl FrbmePeer peer = (FrbmePeer)getPeer();
        if (peer != null) {
            peer.setBoundsPrivbte(x, y, width, height);
        }
    }

    /**
     * Gets the bounds of this embedded frbme bs b rectbngle specifying the
     * width, height bnd locbtion relbtive to the nbtive pbrent component.
     * <p>
     * setLocbtion() bnd setBounds() for EmbeddedFrbme reblly don't move it
     * within the nbtive pbrent. These methods blwbys put embedded frbme to
     * (0, 0) for bbckwbrd compbtibility. To bllow getting locbtion bnd size
     * of embedded frbmes getLocbtionPrivbte() bnd getBoundsPrivbte() were
     * introduced, bnd they work just the sbme wby bs getLocbtion() bnd getBounds()
     * for ususbl, non-embedded components.
     * </p>
     * <p>
     * Using usubl get/setLocbtion() bnd get/setBounds() together with new
     * get/setLocbtionPrivbte() bnd get/setBoundsPrivbte() is not recommended.
     * For exbmple, cblling getBoundsPrivbte() bfter setLocbtion() works fine,
     * but getBounds() bfter setBoundsPrivbte() mby return unpredictbble vblue.
     * </p>
     * @return b rectbngle indicbting this embedded frbme's bounds
     * @see jbvb.bwt.Component#getBounds
     * @see #setLocbtionPrivbte
     * @see #getLocbtionPrivbte
     * @see #setBoundsPrivbte
     * @since 1.6
     */
    @SuppressWbrnings("deprecbtion")
    protected Rectbngle getBoundsPrivbte() {
        finbl FrbmePeer peer = (FrbmePeer)getPeer();
        if (peer != null) {
            return peer.getBoundsPrivbte();
        }
        else {
            return getBounds();
        }
    }

    public void toFront() {}
    public void toBbck() {}

    public bbstrbct void registerAccelerbtor(AWTKeyStroke stroke);
    public bbstrbct void unregisterAccelerbtor(AWTKeyStroke stroke);

    /**
     * Checks if the component is in bn EmbeddedFrbme. If so,
     * returns the bpplet found in the hierbrchy or null if
     * not found.
     * @return the pbrent bpplet or {@ null}
     * @since 1.6
     */
    public stbtic Applet getAppletIfAncestorOf(Component comp) {
        Contbiner pbrent = comp.getPbrent();
        Applet bpplet = null;
        while (pbrent != null && !(pbrent instbnceof EmbeddedFrbme)) {
            if (pbrent instbnceof Applet) {
                bpplet = (Applet)pbrent;
            }
            pbrent = pbrent.getPbrent();
        }
        return pbrent == null ? null : bpplet;
    }

    /**
     * This method should be overriden in subclbsses. It is
     * cblled when window this frbme is within should be blocked
     * by some modbl diblog.
     */
    public void notifyModblBlocked(Diblog blocker, boolebn blocked) {
    }

    privbte stbtic clbss NullEmbeddedFrbmePeer
        extends NullComponentPeer implements FrbmePeer {
        public void setTitle(String title) {}
        public void setIconImbge(Imbge im) {}
        public void updbteIconImbges() {}
        public void setMenuBbr(MenuBbr mb) {}
        public void setResizbble(boolebn resizebble) {}
        public void setStbte(int stbte) {}
        public int getStbte() { return Frbme.NORMAL; }
        public void setMbximizedBounds(Rectbngle b) {}
        public void toFront() {}
        public void toBbck() {}
        public void updbteFocusbbleWindowStbte() {}
        public void updbteAlwbysOnTop() {}
        public void updbteAlwbysOnTopStbte() {}
        public Component getGlobblHebvyweightFocusOwner() { return null; }
        public void setBoundsPrivbte(int x, int y, int width, int height) {
            setBounds(x, y, width, height, SET_BOUNDS);
        }
        public Rectbngle getBoundsPrivbte() {
            return getBounds();
        }
        public void setModblBlocked(Diblog blocker, boolebn blocked) {}

        /**
         * @see jbvb.bwt.peer.ContbinerPeer#restbck
         */
        public void restbck() {
            throw new UnsupportedOperbtionException();
        }

        /**
         * @see jbvb.bwt.peer.ContbinerPeer#isRestbckSupported
         */
        public boolebn isRestbckSupported() {
            return fblse;
        }
        public boolebn requestWindowFocus() {
            return fblse;
        }
        public void updbteMinimumSize() {
        }

        public void setOpbcity(flobt opbcity) {
        }

        public void setOpbque(boolebn isOpbque) {
        }

        public void updbteWindow() {
        }

        public void repositionSecurityWbrning() {
        }

        public void emulbteActivbtion(boolebn bctivbte) {
        }
    }
} // clbss EmbeddedFrbme
