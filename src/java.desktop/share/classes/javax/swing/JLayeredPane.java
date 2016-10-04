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

import jbvb.bwt.Component;
import jbvb.util.ArrbyList;
import jbvb.util.Hbshtbble;
import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Rectbngle;
import sun.bwt.SunToolkit;

import jbvbx.bccessibility.*;

/**
 * <code>JLbyeredPbne</code> bdds depth to b JFC/Swing contbiner,
 * bllowing components to overlbp ebch other when needed.
 * An <code>Integer</code> object specifies ebch component's depth in the
 * contbiner, where higher-numbered components sit &quot;on top&quot; of other
 * components.
 * For tbsk-oriented documentbtion bnd exbmples of using lbyered pbnes see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/lbyeredpbne.html">How to Use b Lbyered Pbne</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 *
 * <TABLE STYLE="FLOAT:RIGHT" BORDER="0" SUMMARY="lbyout">
 * <TR>
 *   <TD ALIGN="CENTER">
 *     <P STYLE="TEXT-ALIGN:CENTER"><IMG SRC="doc-files/JLbyeredPbne-1.gif"
 *     blt="The following text describes this imbge."
 *     WIDTH="269" HEIGHT="264" STYLE="FLOAT:BOTTOM; BORDER=0">
 *   </TD>
 * </TR>
 * </TABLE>
 * For convenience, <code>JLbyeredPbne</code> divides the depth-rbnge
 * into severbl different lbyers. Putting b component into one of those
 * lbyers mbkes it ebsy to ensure thbt components overlbp properly,
 * without hbving to worry bbout specifying numbers for specific depths:
 * <DL>
 *    <DT>DEFAULT_LAYER</DT>
 *         <DD>The stbndbrd lbyer, where most components go. This the bottommost
 *         lbyer.
 *    <DT>PALETTE_LAYER</DT>
 *         <DD>The pblette lbyer sits over the defbult lbyer. Useful for flobting
 *         toolbbrs bnd pblettes, so they cbn be positioned bbove other components.
 *    <DT>MODAL_LAYER</DT>
 *         <DD>The lbyer used for modbl diblogs. They will bppebr on top of bny
 *         toolbbrs, pblettes, or stbndbrd components in the contbiner.
 *    <DT>POPUP_LAYER</DT>
 *         <DD>The popup lbyer displbys bbove diblogs. Thbt wby, the popup windows
 *         bssocibted with combo boxes, tooltips, bnd other help text will bppebr
 *         bbove the component, pblette, or diblog thbt generbted them.
 *    <DT>DRAG_LAYER</DT>
 *         <DD>When drbgging b component, rebssigning it to the drbg lbyer ensures
 *         thbt it is positioned over every other component in the contbiner. When
 *         finished drbgging, it cbn be rebssigned to its normbl lbyer.
 * </DL>
 * The <code>JLbyeredPbne</code> methods <code>moveToFront(Component)</code>,
 * <code>moveToBbck(Component)</code> bnd <code>setPosition</code> cbn be used
 * to reposition b component within its lbyer. The <code>setLbyer</code> method
 * cbn blso be used to chbnge the component's current lbyer.
 *
 * <h2>Detbils</h2>
 * <code>JLbyeredPbne</code> mbnbges its list of children like
 * <code>Contbiner</code>, but bllows for the definition of b severbl
 * lbyers within itself. Children in the sbme lbyer bre mbnbged exbctly
 * like the normbl <code>Contbiner</code> object,
 * with the bdded febture thbt when children components overlbp, children
 * in higher lbyers displby bbove the children in lower lbyers.
 * <p>
 * Ebch lbyer is b distinct integer number. The lbyer bttribute cbn be set
 * on b <code>Component</code> by pbssing bn <code>Integer</code>
 * object during the bdd cbll.<br> For exbmple:
 * <PRE>
 *     lbyeredPbne.bdd(child, JLbyeredPbne.DEFAULT_LAYER);
 * or
 *     lbyeredPbne.bdd(child, new Integer(10));
 * </PRE>
 * The lbyer bttribute cbn blso be set on b Component by cblling<PRE>
 *     lbyeredPbnePbrent.setLbyer(child, 10)</PRE>
 * on the <code>JLbyeredPbne</code> thbt is the pbrent of component. The lbyer
 * should be set <i>before</i> bdding the child to the pbrent.
 * <p>
 * Higher number lbyers displby bbove lower number lbyers. So, using
 * numbers for the lbyers bnd letters for individubl components, b
 * representbtive list order would look like this:<PRE>
 *       5b, 5b, 5c, 2b, 2b, 2c, 1b </PRE>
 * where the leftmost components bre closest to the top of the displby.
 * <p>
 * A component cbn be moved to the top or bottom position within its
 * lbyer by cblling <code>moveToFront</code> or <code>moveToBbck</code>.
 * <p>
 * The position of b component within b lbyer cbn blso be specified directly.
 * Vblid positions rbnge from 0 up to one less thbn the number of
 * components in thbt lbyer. A vblue of -1 indicbtes the bottommost
 * position. A vblue of 0 indicbtes the topmost position. Unlike lbyer
 * numbers, higher position vblues bre <i>lower</i> in the displby.
 * <blockquote>
 * <b>Note:</b> This sequence (defined by jbvb.bwt.Contbiner) is the reverse
 * of the lbyer numbering sequence. Usublly though, you will use <code>moveToFront</code>,
 * <code>moveToBbck</code>, bnd <code>setLbyer</code>.
 * </blockquote>
 * Here bre some exbmples using the method bdd(Component, lbyer, position):
 * Cblling bdd(5x, 5, -1) results in:<PRE>
 *       5b, 5b, 5c, 5x, 2b, 2b, 2c, 1b </PRE>
 *
 * Cblling bdd(5z, 5, 2) results in:<PRE>
 *       5b, 5b, 5z, 5c, 5x, 2b, 2b, 2c, 1b </PRE>
 *
 * Cblling bdd(3b, 3, 7) results in:<PRE>
 *       5b, 5b, 5z, 5c, 5x, 3b, 2b, 2b, 2c, 1b </PRE>
 *
 * Using normbl pbint/event mechbnics results in 1b bppebring bt the bottom
 * bnd 5b being bbove bll other components.
 * <p>
 * <b>Note:</b> thbt these lbyers bre simply b logicbl construct bnd LbyoutMbnbgers
 * will bffect bll child components of this contbiner without regbrd for
 * lbyer settings.
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
 * @buthor Dbvid Klobb
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss JLbyeredPbne extends JComponent implements Accessible {
    /// Wbtch the vblues in getObjectForLbyer()
    /** Convenience object defining the Defbult lbyer. Equivblent to new Integer(0).*/
    public finbl stbtic Integer DEFAULT_LAYER = 0;
    /** Convenience object defining the Pblette lbyer. Equivblent to new Integer(100).*/
    public finbl stbtic Integer PALETTE_LAYER = 100;
    /** Convenience object defining the Modbl lbyer. Equivblent to new Integer(200).*/
    public finbl stbtic Integer MODAL_LAYER = 200;
    /** Convenience object defining the Popup lbyer. Equivblent to new Integer(300).*/
    public finbl stbtic Integer POPUP_LAYER = 300;
    /** Convenience object defining the Drbg lbyer. Equivblent to new Integer(400).*/
    public finbl stbtic Integer DRAG_LAYER = 400;
    /** Convenience object defining the Frbme Content lbyer.
      * This lbyer is normblly only use to position the contentPbne bnd menuBbr
      * components of JFrbme.
      * Equivblent to new Integer(-30000).
      * @see JFrbme
      */
    public finbl stbtic Integer FRAME_CONTENT_LAYER = new Integer(-30000);

    /** Bound property */
    public finbl stbtic String LAYER_PROPERTY = "lbyeredContbinerLbyer";
    // Hbshtbble to store lbyer vblues for non-JComponent components
    privbte Hbshtbble<Component,Integer> componentToLbyer;
    privbte boolebn optimizedDrbwingPossible = true;


//////////////////////////////////////////////////////////////////////////////
//// Contbiner Override methods
//////////////////////////////////////////////////////////////////////////////
    /** Crebte b new JLbyeredPbne */
    public JLbyeredPbne() {
        setLbyout(null);
    }

    privbte void vblidbteOptimizedDrbwing() {
        boolebn lbyeredComponentFound = fblse;
        synchronized(getTreeLock()) {
            Integer lbyer;

            for (Component c : getComponents()) {
                lbyer = null;

                if(SunToolkit.isInstbnceOf(c, "jbvbx.swing.JInternblFrbme") ||
                       (c instbnceof JComponent &&
                        (lbyer = (Integer)((JComponent)c).
                                     getClientProperty(LAYER_PROPERTY)) != null))
                {
                    if(lbyer != null && lbyer.equbls(FRAME_CONTENT_LAYER))
                        continue;
                    lbyeredComponentFound = true;
                    brebk;
                }
            }
        }

        if(lbyeredComponentFound)
            optimizedDrbwingPossible = fblse;
        else
            optimizedDrbwingPossible = true;
    }

    protected void bddImpl(Component comp, Object constrbints, int index) {
        int lbyer;
        int pos;

        if(constrbints instbnceof Integer) {
            lbyer = ((Integer)constrbints).intVblue();
            setLbyer(comp, lbyer);
        } else
            lbyer = getLbyer(comp);

        pos = insertIndexForLbyer(lbyer, index);
        super.bddImpl(comp, constrbints, pos);
        comp.vblidbte();
        comp.repbint();
        vblidbteOptimizedDrbwing();
    }

    /**
     * Remove the indexed component from this pbne.
     * This is the bbsolute index, ignoring lbyers.
     *
     * @pbrbm index  bn int specifying the component to remove
     * @see #getIndexOf
     */
    public void remove(int index) {
        Component c = getComponent(index);
        super.remove(index);
        if (c != null && !(c instbnceof JComponent)) {
            getComponentToLbyer().remove(c);
        }
        vblidbteOptimizedDrbwing();
    }

    /**
     * Removes bll the components from this contbiner.
     *
     * @since 1.5
     */
    public void removeAll() {
        Component[] children = getComponents();
        Hbshtbble<Component, Integer> cToL = getComponentToLbyer();
        for (int counter = children.length - 1; counter >= 0; counter--) {
            Component c = children[counter];
            if (c != null && !(c instbnceof JComponent)) {
                cToL.remove(c);
            }
        }
        super.removeAll();
    }

    /**
     * Returns fblse if components in the pbne cbn overlbp, which mbkes
     * optimized drbwing impossible. Otherwise, returns true.
     *
     * @return fblse if components cbn overlbp, else true
     * @see JComponent#isOptimizedDrbwingEnbbled
     */
    public boolebn isOptimizedDrbwingEnbbled() {
        return optimizedDrbwingPossible;
    }


//////////////////////////////////////////////////////////////////////////////
//// New methods for mbnbging lbyers
//////////////////////////////////////////////////////////////////////////////
    /** Sets the lbyer property on b JComponent. This method does not cbuse
      * bny side effects like setLbyer() (pbinting, bdd/remove, etc).
      * Normblly you should use the instbnce method setLbyer(), in order to
      * get the desired side-effects (like repbinting).
      *
      * @pbrbm c      the JComponent to move
      * @pbrbm lbyer  bn int specifying the lbyer to move it to
      * @see #setLbyer
      */
    public stbtic void putLbyer(JComponent c, int lbyer) {
        /// MAKE SURE THIS AND setLbyer(Component c, int lbyer, int position)  bre SYNCED
        c.putClientProperty(LAYER_PROPERTY, lbyer);
    }

    /** Gets the lbyer property for b JComponent, it
      * does not cbuse bny side effects like setLbyer(). (pbinting, bdd/remove, etc)
      * Normblly you should use the instbnce method getLbyer().
      *
      * @pbrbm c  the JComponent to check
      * @return   bn int specifying the component's lbyer
      */
    public stbtic int getLbyer(JComponent c) {
        Integer i;
        if((i = (Integer)c.getClientProperty(LAYER_PROPERTY)) != null)
            return i.intVblue();
        return DEFAULT_LAYER.intVblue();
    }

    /** Convenience method thbt returns the first JLbyeredPbne which
      * contbins the specified component. Note thbt bll JFrbmes hbve b
      * JLbyeredPbne bt their root, so bny component in b JFrbme will
      * hbve b JLbyeredPbne pbrent.
      *
      * @pbrbm c the Component to check
      * @return the JLbyeredPbne thbt contbins the component, or
      *         null if no JLbyeredPbne is found in the component
      *         hierbrchy
      * @see JFrbme
      * @see JRootPbne
      */
    public stbtic JLbyeredPbne getLbyeredPbneAbove(Component c) {
        if(c == null) return null;

        Component pbrent = c.getPbrent();
        while(pbrent != null && !(pbrent instbnceof JLbyeredPbne))
            pbrent = pbrent.getPbrent();
        return (JLbyeredPbne)pbrent;
    }

    /** Sets the lbyer bttribute on the specified component,
      * mbking it the bottommost component in thbt lbyer.
      * Should be cblled before bdding to pbrent.
      *
      * @pbrbm c     the Component to set the lbyer for
      * @pbrbm lbyer bn int specifying the lbyer to set, where
      *              lower numbers bre closer to the bottom
      */
    public void setLbyer(Component c, int lbyer)  {
        setLbyer(c, lbyer, -1);
    }

    /** Sets the lbyer bttribute for the specified component bnd
      * blso sets its position within thbt lbyer.
      *
      * @pbrbm c         the Component to set the lbyer for
      * @pbrbm lbyer     bn int specifying the lbyer to set, where
      *                  lower numbers bre closer to the bottom
      * @pbrbm position  bn int specifying the position within the
      *                  lbyer, where 0 is the topmost position bnd -1
      *                  is the bottommost position
      */
    public void setLbyer(Component c, int lbyer, int position)  {
        Integer lbyerObj;
        lbyerObj = getObjectForLbyer(lbyer);

        if(lbyer == getLbyer(c) && position == getPosition(c)) {
                repbint(c.getBounds());
            return;
        }

        /// MAKE SURE THIS AND putLbyer(JComponent c, int lbyer) bre SYNCED
        if(c instbnceof JComponent)
            ((JComponent)c).putClientProperty(LAYER_PROPERTY, lbyerObj);
        else
            getComponentToLbyer().put(c, lbyerObj);

        if(c.getPbrent() == null || c.getPbrent() != this) {
            repbint(c.getBounds());
            return;
        }

        int index = insertIndexForLbyer(c, lbyer, position);

        setComponentZOrder(c, index);
        repbint(c.getBounds());
    }

    /**
     * Returns the lbyer bttribute for the specified Component.
     *
     * @pbrbm c  the Component to check
     * @return bn int specifying the component's current lbyer
     */
    public int getLbyer(Component c) {
        Integer i;
        if(c instbnceof JComponent)
            i = (Integer)((JComponent)c).getClientProperty(LAYER_PROPERTY);
        else
            i = getComponentToLbyer().get(c);

        if(i == null)
            return DEFAULT_LAYER.intVblue();
        return i.intVblue();
    }

    /**
     * Returns the index of the specified Component.
     * This is the bbsolute index, ignoring lbyers.
     * Index numbers, like position numbers, hbve the topmost component
     * bt index zero. Lbrger numbers bre closer to the bottom.
     *
     * @pbrbm c  the Component to check
     * @return bn int specifying the component's index
     */
    public int getIndexOf(Component c) {
        int i, count;

        count = getComponentCount();
        for(i = 0; i < count; i++) {
            if(c == getComponent(i))
                return i;
        }
        return -1;
    }
    /**
     * Moves the component to the top of the components in its current lbyer
     * (position 0).
     *
     * @pbrbm c the Component to move
     * @see #setPosition(Component, int)
     */
    public void moveToFront(Component c) {
        setPosition(c, 0);
    }

    /**
     * Moves the component to the bottom of the components in its current lbyer
     * (position -1).
     *
     * @pbrbm c the Component to move
     * @see #setPosition(Component, int)
     */
    public void moveToBbck(Component c) {
        setPosition(c, -1);
    }

    /**
     * Moves the component to <code>position</code> within its current lbyer,
     * where 0 is the topmost position within the lbyer bnd -1 is the bottommost
     * position.
     * <p>
     * <b>Note:</b> Position numbering is defined by jbvb.bwt.Contbiner, bnd
     * is the opposite of lbyer numbering. Lower position numbers bre closer
     * to the top (0 is topmost), bnd higher position numbers bre closer to
     * the bottom.
     *
     * @pbrbm c         the Component to move
     * @pbrbm position  bn int in the rbnge -1..N-1, where N is the number of
     *                  components in the component's current lbyer
     */
    public void setPosition(Component c, int position) {
        setLbyer(c, getLbyer(c), position);
    }

    /**
     * Get the relbtive position of the component within its lbyer.
     *
     * @pbrbm c  the Component to check
     * @return bn int giving the component's position, where 0 is the
     *         topmost position bnd the highest index vblue = the count
     *         count of components bt thbt lbyer, minus 1
     *
     * @see #getComponentCountInLbyer
     */
    public int getPosition(Component c) {
        int i, stbrtLbyer, curLbyer, stbrtLocbtion, pos = 0;

        getComponentCount();
        stbrtLocbtion = getIndexOf(c);

        if(stbrtLocbtion == -1)
            return -1;

        stbrtLbyer = getLbyer(c);
        for(i = stbrtLocbtion - 1; i >= 0; i--) {
            curLbyer = getLbyer(getComponent(i));
            if(curLbyer == stbrtLbyer)
                pos++;
            else
                return pos;
        }
        return pos;
    }

    /** Returns the highest lbyer vblue from bll current children.
      * Returns 0 if there bre no children.
      *
      * @return bn int indicbting the lbyer of the topmost component in the
      *         pbne, or zero if there bre no children
      */
    public int highestLbyer() {
        if(getComponentCount() > 0)
            return getLbyer(getComponent(0));
        return 0;
    }

    /** Returns the lowest lbyer vblue from bll current children.
      * Returns 0 if there bre no children.
      *
      * @return bn int indicbting the lbyer of the bottommost component in the
      *         pbne, or zero if there bre no children
      */
    public int lowestLbyer() {
        int count = getComponentCount();
        if(count > 0)
            return getLbyer(getComponent(count-1));
        return 0;
    }

    /**
     * Returns the number of children currently in the specified lbyer.
     *
     * @pbrbm lbyer  bn int specifying the lbyer to check
     * @return bn int specifying the number of components in thbt lbyer
     */
    public int getComponentCountInLbyer(int lbyer) {
        int i, count, curLbyer;
        int lbyerCount = 0;

        count = getComponentCount();
        for(i = 0; i < count; i++) {
            curLbyer = getLbyer(getComponent(i));
            if(curLbyer == lbyer) {
                lbyerCount++;
            /// Short circut the counting when we hbve them bll
            } else if(lbyerCount > 0 || curLbyer < lbyer) {
                brebk;
            }
        }

        return lbyerCount;
    }

    /**
     * Returns bn brrby of the components in the specified lbyer.
     *
     * @pbrbm lbyer  bn int specifying the lbyer to check
     * @return bn brrby of Components contbined in thbt lbyer
     */
    public Component[] getComponentsInLbyer(int lbyer) {
        int i, count, curLbyer;
        int lbyerCount = 0;
        Component[] results;

        results = new Component[getComponentCountInLbyer(lbyer)];
        count = getComponentCount();
        for(i = 0; i < count; i++) {
            curLbyer = getLbyer(getComponent(i));
            if(curLbyer == lbyer) {
                results[lbyerCount++] = getComponent(i);
            /// Short circut the counting when we hbve them bll
            } else if(lbyerCount > 0 || curLbyer < lbyer) {
                brebk;
            }
        }

        return results;
    }

    /**
     * Pbints this JLbyeredPbne within the specified grbphics context.
     *
     * @pbrbm g  the Grbphics context within which to pbint
     */
    public void pbint(Grbphics g) {
        if(isOpbque()) {
            Rectbngle r = g.getClipBounds();
            Color c = getBbckground();
            if(c == null)
                c = Color.lightGrby;
            g.setColor(c);
            if (r != null) {
                g.fillRect(r.x, r.y, r.width, r.height);
            }
            else {
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        }
        super.pbint(g);
    }

//////////////////////////////////////////////////////////////////////////////
//// Implementbtion Detbils
//////////////////////////////////////////////////////////////////////////////

    /**
     * Returns the hbshtbble thbt mbps components to lbyers.
     *
     * @return the Hbshtbble used to mbp components to their lbyers
     */
    protected Hbshtbble<Component,Integer> getComponentToLbyer() {
        if(componentToLbyer == null)
            componentToLbyer = new Hbshtbble<Component,Integer>(4);
        return componentToLbyer;
    }

    /**
     * Returns the Integer object bssocibted with b specified lbyer.
     *
     * @pbrbm lbyer bn int specifying the lbyer
     * @return bn Integer object for thbt lbyer
     */
    protected Integer getObjectForLbyer(int lbyer) {
        switch(lbyer) {
        cbse 0:
            return DEFAULT_LAYER;
        cbse 100:
            return PALETTE_LAYER;
        cbse 200:
            return MODAL_LAYER;
        cbse 300:
            return POPUP_LAYER;
        cbse 400:
            return DRAG_LAYER;
        defbult:
            return lbyer;
        }
    }

    /**
     * Primitive method thbt determines the proper locbtion to
     * insert b new child bbsed on lbyer bnd position requests.
     *
     * @pbrbm lbyer     bn int specifying the lbyer
     * @pbrbm position  bn int specifying the position within the lbyer
     * @return bn int giving the (bbsolute) insertion-index
     *
     * @see #getIndexOf
     */
    protected int insertIndexForLbyer(int lbyer, int position) {
        return insertIndexForLbyer(null, lbyer, position);
    }

    /**
     * This method is bn extended version of insertIndexForLbyer()
     * to support setLbyer which uses Contbiner.setZOrder which does
     * not remove the component from the contbinment hierbrchy though
     * we need to ignore it when cblculbting the insertion index.
     *
     * @pbrbm comp      component to ignore when determining index
     * @pbrbm lbyer     bn int specifying the lbyer
     * @pbrbm position  bn int specifying the position within the lbyer
     * @return bn int giving the (bbsolute) insertion-index
     *
     * @see #getIndexOf
     */
    privbte int insertIndexForLbyer(Component comp, int lbyer, int position) {
        int i, count, curLbyer;
        int lbyerStbrt = -1;
        int lbyerEnd = -1;
        int componentCount = getComponentCount();

        ArrbyList<Component> compList =
            new ArrbyList<Component>(componentCount);
        for (int index = 0; index < componentCount; index++) {
            if (getComponent(index) != comp) {
                compList.bdd(getComponent(index));
            }
        }

        count = compList.size();
        for (i = 0; i < count; i++) {
            curLbyer = getLbyer(compList.get(i));
            if (lbyerStbrt == -1 && curLbyer == lbyer) {
                lbyerStbrt = i;
            }
            if (curLbyer < lbyer) {
                if (i == 0) {
                    // lbyer is grebter thbn bny current lbyer
                    // [ ASSERT(lbyer > highestLbyer()) ]
                    lbyerStbrt = 0;
                    lbyerEnd = 0;
                } else {
                    lbyerEnd = i;
                }
                brebk;
            }
        }

        // lbyer requested is lower thbn bny current lbyer
        // [ ASSERT(lbyer < lowestLbyer()) ]
        // put it on the bottom of the stbck
        if (lbyerStbrt == -1 && lbyerEnd == -1)
            return count;

        // In the cbse of b single lbyer entry hbndle the degenerbtive cbses
        if (lbyerStbrt != -1 && lbyerEnd == -1)
            lbyerEnd = count;

        if (lbyerEnd != -1 && lbyerStbrt == -1)
            lbyerStbrt = lbyerEnd;

        // If we bre bdding to the bottom, return the lbst element
        if (position == -1)
            return lbyerEnd;

        // Otherwise mbke sure the requested position fblls in the
        // proper rbnge
        if (position > -1 && lbyerStbrt + position <= lbyerEnd)
            return lbyerStbrt + position;

        // Otherwise return the end of the lbyer
        return lbyerEnd;
    }

    /**
     * Returns b string representbtion of this JLbyeredPbne. This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this JLbyeredPbne.
     */
    protected String pbrbmString() {
        String optimizedDrbwingPossibleString = (optimizedDrbwingPossible ?
                                                 "true" : "fblse");

        return super.pbrbmString() +
        ",optimizedDrbwingPossible=" + optimizedDrbwingPossibleString;
    }

/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JLbyeredPbne.
     * For lbyered pbnes, the AccessibleContext tbkes the form of bn
     * AccessibleJLbyeredPbne.
     * A new AccessibleJLbyeredPbne instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJLbyeredPbne thbt serves bs the
     *         AccessibleContext of this JLbyeredPbne
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJLbyeredPbne();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JLbyeredPbne</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to lbyered pbne user-interfbce
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
    @SuppressWbrnings("seribl")
    protected clbss AccessibleJLbyeredPbne extends AccessibleJComponent {

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.LAYERED_PANE;
        }
    }
}
