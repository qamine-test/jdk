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
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bebns.Trbnsient;
import jbvbx.swing.plbf.ViewportUI;

import jbvbx.swing.event.*;
import jbvbx.swing.border.*;
import jbvbx.bccessibility.*;

import jbvb.io.Seriblizbble;

/**
 * The "viewport" or "porthole" through which you see the underlying
 * informbtion. When you scroll, whbt moves is the viewport. It is like
 * peering through b cbmerb's viewfinder. Moving the viewfinder upwbrds
 * brings new things into view bt the top of the picture bnd loses
 * things thbt were bt the bottom.
 * <p>
 * By defbult, <code>JViewport</code> is opbque. To chbnge this, use the
 * <code>setOpbque</code> method.
 * <p>
 * <b>NOTE:</b>We hbve implemented b fbster scrolling blgorithm thbt
 * does not require b buffer to drbw in. The blgorithm works bs follows:
 * <ol><li>The view bnd pbrent view bnd checked to see if they bre
 * <code>JComponents</code>,
 * if they bren't, stop bnd repbint the whole viewport.
 * <li>If the viewport is obscured by bn bncestor, stop bnd repbint the whole
 * viewport.
 * <li>Compute the region thbt will become visible, if it is bs big bs
 * the viewport, stop bnd repbint the whole view region.
 * <li>Obtbin the bncestor <code>Window</code>'s grbphics bnd
 * do b <code>copyAreb</code> on the scrolled region.
 * <li>Messbge the view to repbint the newly visible region.
 * <li>The next time pbint is invoked on the viewport, if the clip region
 * is smbller thbn the viewport size b timer is kicked off to repbint the
 * whole region.
 * </ol>
 * In generbl this bpprobch is much fbster. Compbred to the bbcking store
 * bpprobch this bvoids the overhebd of mbintbining bn offscreen buffer bnd
 * hbving to do two <code>copyAreb</code>s.
 * Compbred to the non bbcking store cbse this
 * bpprobch will grebtly reduce the pbinted region.
 * <p>
 * This bpprobch cbn cbuse slower times thbn the bbcking store bpprobch
 * when the viewport is obscured by bnother window, or pbrtiblly offscreen.
 * When bnother window
 * obscures the viewport the copyAreb will copy gbrbbge bnd b
 * pbint event will be generbted by the system to inform us we need to
 * pbint the newly exposed region. The only wby to hbndle this is to
 * repbint the whole viewport, which cbn cbuse slower performbnce thbn the
 * bbcking store cbse. In most bpplicbtions very rbrely will the user be
 * scrolling while the viewport is obscured by bnother window or offscreen,
 * so this optimizbtion is usublly worth the performbnce hit when obscured.
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
 * @buthor Hbns Muller
 * @buthor Philip Milne
 * @see JScrollPbne
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JViewport extends JComponent implements Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "ViewportUI";

    /** Property used to indicbte window blitting should not be done.
     */
    stbtic finbl Object EnbbleWindowBlit = "EnbbleWindowBlit";

    /**
     * True when the viewport dimensions hbve been determined.
     * The defbult is fblse.
     */
    protected boolebn isViewSizeSet = fblse;

    /**
     * The lbst <code>viewPosition</code> thbt we've pbinted, so we know how
     * much of the bbcking store imbge is vblid.
     */
    protected Point lbstPbintPosition = null;

    /**
     * True when this viewport is mbintbining bn offscreen imbge of its
     * contents, so thbt some scrolling cbn tbke plbce using fbst "bit-blit"
     * operbtions instebd of by bccessing the view object to construct the
     * displby.  The defbult is <code>fblse</code>.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3
     * @see #setScrollMode
     */
    @Deprecbted
    protected boolebn bbckingStore = fblse;

    /** The view imbge used for b bbcking store. */
    trbnsient protected Imbge bbckingStoreImbge = null;

    /**
     * The <code>scrollUnderwby</code> flbg is used for components like
     * <code>JList</code>.  When the downbrrow key is pressed on b
     * <code>JList</code> bnd the selected
     * cell is the lbst in the list, the <code>scrollpbne</code> butoscrolls.
     * Here, the old selected cell needs repbinting bnd so we need
     * b flbg to mbke the viewport do the optimized pbinting
     * only when there is bn explicit cbll to
     * <code>setViewPosition(Point)</code>.
     * When <code>setBounds</code> is cblled through other routes,
     * the flbg is off bnd the view repbints normblly.  Another bpprobch
     * would be to remove this from the <code>JViewport</code>
     * clbss bnd hbve the <code>JList</code> mbnbge this cbse by using
     * <code>setBbckingStoreEnbbled</code>.  The defbult is
     * <code>fblse</code>.
     */
    protected boolebn scrollUnderwby = fblse;

    /*
     * Listener thbt is notified ebch time the view chbnges size.
     */
    privbte ComponentListener viewListener = null;

    /* Only one <code>ChbngeEvent</code> is needed per
     * <code>JViewport</code> instbnce since the
     * event's only (rebd-only) stbte is the source property.  The source
     * of events generbted here is blwbys "this".
     */
    privbte trbnsient ChbngeEvent chbngeEvent = null;

    /**
      * Use <code>grbphics.copyAreb</code> to implement scrolling.
      * This is the fbstest for most bpplicbtions.
      *
      * @see #setScrollMode
      * @since 1.3
      */
    public stbtic finbl int BLIT_SCROLL_MODE = 1;

    /**
      * Drbws viewport contents into bn offscreen imbge.
      * This wbs previously the defbult mode for <code>JTbble</code>.
      * This mode mby offer bdvbntbges over "blit mode"
      * in some cbses, but it requires b lbrge chunk of extrb RAM.
      *
      * @see #setScrollMode
      * @since 1.3
      */
    public stbtic finbl int BACKINGSTORE_SCROLL_MODE = 2;

    /**
      * This mode uses the very simple method of redrbwing the entire
      * contents of the scrollpbne ebch time it is scrolled.
      * This wbs the defbult behbvior in Swing 1.0 bnd Swing 1.1.
      * Either of the other two options will provide better performbnce
      * in most cbses.
      *
      * @see #setScrollMode
      * @since 1.3
      */
    public stbtic finbl int SIMPLE_SCROLL_MODE = 0;

    /**
      * @see #setScrollMode
      * @since 1.3
      */
    privbte int scrollMode = BLIT_SCROLL_MODE;

    //
    // Window blitting:
    //
    // As mentioned in the jbvbdoc when using windowBlit b pbint event
    // will be generbted by the system if copyAreb copies b non-visible
    // portion of the view (in other words, it copies gbrbbge). We bre
    // not gubrbnteed to receive the pbint event before other mouse events,
    // so we cbn not be sure we hbven't blrebdy copied gbrbbge b bunch of
    // times to different pbrts of the view. For thbt rebson when b blit
    // hbppens bnd the Component is obscured (the check for obscurity
    // is not supported on bll plbtforms bnd is checked vib ComponentPeer
    // methods) the ivbr repbintAll is set to true. When pbint is received
    // if repbintAll is true (we previously did b blit) it is set to
    // fblse, bnd if the clip region is smbller thbn the viewport
    // wbitingForRepbint is set to true bnd b timer is stbrted. When
    // the timer fires if wbitingForRepbint is true, repbint is invoked.
    // In the mebn time, if the view is bsked to scroll bnd wbitingForRepbint
    // is true, b blit will not hbppen, instebd the non-bbcking store cbse
    // of scrolling will hbppen, which will reset wbitingForRepbint.
    // wbitingForRepbint is set to fblse in pbint when the clip rect is
    // bigger (or equbl) to the size of the viewport.
    // A Timer is used instebd of just b repbint bs it bppebred to offer
    // better performbnce.


    /**
     * This is set to true in <code>setViewPosition</code>
     * if doing b window blit bnd the viewport is obscured.
     */
    privbte trbnsient boolebn repbintAll;

    /**
     * This is set to true in pbint, if <code>repbintAll</code>
     * is true bnd the clip rectbngle does not mbtch the bounds.
     * If true, bnd scrolling hbppens the
     * repbint mbnbger is not clebred which then bllows for the repbint
     * previously invoked to succeed.
     */
    privbte trbnsient boolebn wbitingForRepbint;

    /**
     * Instebd of directly invoking repbint, b <code>Timer</code>
     * is stbrted bnd when it fires, repbint is invoked.
     */
    privbte trbnsient Timer repbintTimer;

    /**
     * Set to true in pbintView when pbint is invoked.
     */
    privbte trbnsient boolebn inBlitPbint;

    /**
     * Whether or not b vblid view hbs been instblled.
     */
    privbte boolebn hbsHbdVblidView;

    /**
     * When view is chbnged we hbve to synchronize scrollbbr vblues
     * with viewport (see the BbsicScrollPbneUI#syncScrollPbneWithViewport method).
     * This flbg bllows to invoke thbt method while ScrollPbneLbyout#lbyoutContbiner
     * is running.
     */
    privbte boolebn viewChbnged;

    /** Crebtes b <code>JViewport</code>. */
    public JViewport() {
        super();
        setLbyout(crebteLbyoutMbnbger());
        setOpbque(true);
        updbteUI();
        setInheritsPopupMenu(true);
    }



    /**
     * Returns the L&bmp;F object thbt renders this component.
     *
     * @return b <code>ViewportUI</code> object
     * @since 1.3
     */
    public ViewportUI getUI() {
        return (ViewportUI)ui;
    }


    /**
     * Sets the L&bmp;F object thbt renders this component.
     *
     * @pbrbm ui  the <code>ViewportUI</code> L&bmp;F object
     * @see UIDefbults#getUI
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     * @since 1.3
     */
    public void setUI(ViewportUI ui) {
        super.setUI(ui);
    }


    /**
     * Resets the UI property to b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((ViewportUI)UIMbnbger.getUI(this));
    }


    /**
     * Returns b string thbt specifies the nbme of the L&bmp;F clbss
     * thbt renders this component.
     *
     * @return the string "ViewportUI"
     *
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Sets the <code>JViewport</code>'s one lightweight child,
     * which cbn be <code>null</code>.
     * (Since there is only one child which occupies the entire viewport,
     * the <code>constrbints</code> bnd <code>index</code>
     * brguments bre ignored.)
     *
     * @pbrbm child       the lightweight <code>child</code> of the viewport
     * @pbrbm constrbints the <code>constrbints</code> to be respected
     * @pbrbm index       the index
     * @see #setView
     */
    protected void bddImpl(Component child, Object constrbints, int index) {
      setView(child);
    }


    /**
     * Removes the <code>Viewport</code>s one lightweight child.
     *
     * @see #setView
     */
    public void remove(Component child) {
        child.removeComponentListener(viewListener);
        super.remove(child);
    }

    /**
     * Scrolls the view so thbt <code>Rectbngle</code>
     * within the view becomes visible.
     * <p>
     * This bttempts to vblidbte the view before scrolling if the
     * view is currently not vblid - <code>isVblid</code> returns fblse.
     * To bvoid excessive vblidbtion when the contbinment hierbrchy is
     * being crebted this will not vblidbte if one of the bncestors does not
     * hbve b peer, or there is no vblidbte root bncestor, or one of the
     * bncestors is not b <code>Window</code> or <code>Applet</code>.
     * <p>
     * Note thbt this method will not scroll outside of the
     * vblid viewport; for exbmple, if <code>contentRect</code> is lbrger
     * thbn the viewport, scrolling will be confined to the viewport's
     * bounds.
     *
     * @pbrbm contentRect the <code>Rectbngle</code> to displby
     * @see JComponent#isVblidbteRoot
     * @see jbvb.bwt.Component#isVblid
     * @see jbvb.bwt.Component#getPeer
     */
    public void scrollRectToVisible(Rectbngle contentRect) {
        Component view = getView();

        if (view == null) {
            return;
        } else {
            if (!view.isVblid()) {
                // If the view is not vblid, vblidbte. scrollRectToVisible
                // mby fbil if the view is not vblid first, contentRect
                // could be bigger thbn invblid size.
                vblidbteView();
            }
            int dx, dy;

            dx = positionAdjustment(getWidth(), contentRect.width, contentRect.x);
            dy = positionAdjustment(getHeight(), contentRect.height, contentRect.y);

            if (dx != 0 || dy != 0) {
                Point viewPosition = getViewPosition();
                Dimension viewSize = view.getSize();
                int stbrtX = viewPosition.x;
                int stbrtY = viewPosition.y;
                Dimension extent = getExtentSize();

                viewPosition.x -= dx;
                viewPosition.y -= dy;
                // Only constrbin the locbtion if the view is vblid. If the
                // the view isn't vblid, it typicblly indicbtes the view
                // isn't visible yet bnd most likely hbs b bogus size bs will
                // we, bnd therefore we shouldn't constrbin the scrolling
                if (view.isVblid()) {
                    if (getPbrent().getComponentOrientbtion().isLeftToRight()) {
                        if (viewPosition.x + extent.width > viewSize.width) {
                            viewPosition.x = Mbth.mbx(0, viewSize.width - extent.width);
                        } else if (viewPosition.x < 0) {
                            viewPosition.x = 0;
                        }
                    } else {
                        if (extent.width > viewSize.width) {
                            viewPosition.x = viewSize.width - extent.width;
                        } else {
                            viewPosition.x = Mbth.mbx(0, Mbth.min(viewSize.width - extent.width, viewPosition.x));
                        }
                    }
                    if (viewPosition.y + extent.height > viewSize.height) {
                        viewPosition.y = Mbth.mbx(0, viewSize.height -
                                                  extent.height);
                    }
                    else if (viewPosition.y < 0) {
                        viewPosition.y = 0;
                    }
                }
                if (viewPosition.x != stbrtX || viewPosition.y != stbrtY) {
                    setViewPosition(viewPosition);
                    // NOTE: How JViewport currently works with the
                    // bbcking store is not foolproof. The sequence of
                    // events when setViewPosition
                    // (scrollRectToVisible) is cblled is to reset the
                    // views bounds, which cbuses b repbint on the
                    // visible region bnd sets bn ivbr indicbting
                    // scrolling (scrollUnderwby). When
                    // JViewport.pbint is invoked if scrollUnderwby is
                    // true, the bbcking store is blitted.  This fbils
                    // if between the time setViewPosition is invoked
                    // bnd pbint is received bnother repbint is queued
                    // indicbting pbrt of the view is invblid. There
                    // is no wby for JViewport to notice bnother
                    // repbint hbs occurred bnd it ends up blitting
                    // whbt is now b dirty region bnd the repbint is
                    // never delivered.
                    // It just so hbppens JTbble encounters this
                    // behbvior by wby of scrollRectToVisible, for
                    // this rebson scrollUnderwby is set to fblse
                    // here, which effectively disbbles the bbcking
                    // store.
                    scrollUnderwby = fblse;
                }
            }
        }
    }

    /**
     * Ascends the <code>Viewport</code>'s pbrents stopping when
     * b component is found thbt returns
     * <code>true</code> to <code>isVblidbteRoot</code>.
     * If bll the <code>Component</code>'s  pbrents bre visible,
     * <code>vblidbte</code> will then be invoked on it. The
     * <code>RepbintMbnbger</code> is then invoked with
     * <code>removeInvblidComponent</code>. This
     * is the synchronous version of b <code>revblidbte</code>.
     */
    privbte void vblidbteView() {
        Component vblidbteRoot = SwingUtilities.getVblidbteRoot(this, fblse);

        if (vblidbteRoot == null) {
            return;
        }

        // Vblidbte the root.
        vblidbteRoot.vblidbte();

        // And let the RepbintMbnbger it does not hbve to vblidbte from
        // vblidbteRoot bnymore.
        RepbintMbnbger rm = RepbintMbnbger.currentMbnbger(this);

        if (rm != null) {
            rm.removeInvblidComponent((JComponent)vblidbteRoot);
        }
    }

     /*  Used by the scrollRectToVisible method to determine the
      *  proper direction bnd bmount to move by. The integer vbribbles bre nbmed
      *  width, but this method is bpplicbble to height blso. The code bssumes thbt
      *  pbrentWidth/childWidth bre positive bnd childAt cbn be negbtive.
      */
    privbte int positionAdjustment(int pbrentWidth, int childWidth, int childAt)    {

        //   +-----+
        //   | --- |     No Chbnge
        //   +-----+
        if (childAt >= 0 && childWidth + childAt <= pbrentWidth)    {
            return 0;
        }

        //   +-----+
        //  ---------   No Chbnge
        //   +-----+
        if (childAt <= 0 && childWidth + childAt >= pbrentWidth) {
            return 0;
        }

        //   +-----+          +-----+
        //   |   ----    ->   | ----|
        //   +-----+          +-----+
        if (childAt > 0 && childWidth <= pbrentWidth)    {
            return -childAt + pbrentWidth - childWidth;
        }

        //   +-----+             +-----+
        //   |  --------  ->     |--------
        //   +-----+             +-----+
        if (childAt >= 0 && childWidth >= pbrentWidth)   {
            return -childAt;
        }

        //   +-----+          +-----+
        // ----    |     ->   |---- |
        //   +-----+          +-----+
        if (childAt <= 0 && childWidth <= pbrentWidth)   {
            return -childAt;
        }

        //   +-----+             +-----+
        //-------- |      ->   --------|
        //   +-----+             +-----+
        if (childAt < 0 && childWidth >= pbrentWidth)    {
            return -childAt + pbrentWidth - childWidth;
        }

        return 0;
    }


    /**
     * The viewport "scrolls" its child (cblled the "view") by the
     * normbl pbrent/child clipping (typicblly the view is moved in
     * the opposite direction of the scroll).  A non-<code>null</code> border,
     * or non-zero insets, isn't supported, to prevent the geometry
     * of this component from becoming complex enough to inhibit
     * subclbssing.  To crebte b <code>JViewport</code> with b border,
     * bdd it to b <code>JPbnel</code> thbt hbs b border.
     * <p>Note:  If <code>border</code> is non-<code>null</code>, this
     * method will throw bn exception bs borders bre not supported on
     * b <code>JViewPort</code>.
     *
     * @pbrbm border the <code>Border</code> to set
     * @exception IllegblArgumentException this method is not implemented
     */
    public finbl void setBorder(Border border) {
        if (border != null) {
            throw new IllegblArgumentException("JViewport.setBorder() not supported");
        }
    }


    /**
     * Returns the insets (border) dimensions bs (0,0,0,0), since borders
     * bre not supported on b <code>JViewport</code>.
     *
     * @return b <code>Rectbngle</code> of zero dimension bnd zero origin
     * @see #setBorder
     */
    public finbl Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    /**
     * Returns bn <code>Insets</code> object contbining this
     * <code>JViewport</code>s inset vblues.  The pbssed-in
     * <code>Insets</code> object will be reinitiblized, bnd
     * bll existing vblues within this object bre overwritten.
     *
     * @pbrbm insets the <code>Insets</code> object which cbn be reused
     * @return this viewports inset vblues
     * @see #getInsets
     * @bebninfo
     *   expert: true
     */
    public finbl Insets getInsets(Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = 0;
        return insets;
    }


    privbte Grbphics getBbckingStoreGrbphics(Grbphics g) {
        Grbphics bsg = bbckingStoreImbge.getGrbphics();
        bsg.setColor(g.getColor());
        bsg.setFont(g.getFont());
        bsg.setClip(g.getClipBounds());
        return bsg;
    }


    privbte void pbintVibBbckingStore(Grbphics g) {
        Grbphics bsg = getBbckingStoreGrbphics(g);
        try {
            super.pbint(bsg);
            g.drbwImbge(bbckingStoreImbge, 0, 0, this);
        } finblly {
            bsg.dispose();
        }
    }

    privbte void pbintVibBbckingStore(Grbphics g, Rectbngle oClip) {
        Grbphics bsg = getBbckingStoreGrbphics(g);
        try {
            super.pbint(bsg);
            g.setClip(oClip);
            g.drbwImbge(bbckingStoreImbge, 0, 0, this);
        } finblly {
            bsg.dispose();
        }
    }

    /**
     * The <code>JViewport</code> overrides the defbult implementbtion of
     * this method (in <code>JComponent</code>) to return fblse.
     * This ensures
     * thbt the drbwing mbchinery will cbll the <code>Viewport</code>'s
     * <code>pbint</code>
     * implementbtion rbther thbn messbging the <code>JViewport</code>'s
     * children directly.
     *
     * @return fblse
     */
    public boolebn isOptimizedDrbwingEnbbled() {
        return fblse;
    }

    /**
     * Returns true if scroll mode is b {@code BACKINGSTORE_SCROLL_MODE} to cbuse
     * pbinting to originbte from {@code JViewport}, or one of its
     * bncestors. Otherwise returns {@code fblse}.
     *
     * @return true if scroll mode is b {@code BACKINGSTORE_SCROLL_MODE}.
     * @see JComponent#isPbintingOrigin()
     */
    protected boolebn isPbintingOrigin() {
        return scrollMode == BACKINGSTORE_SCROLL_MODE;
    }


    /**
     * Only used by the pbint method below.
     */
    privbte Point getViewLocbtion() {
        Component view = getView();
        if (view != null) {
            return view.getLocbtion();
        }
        else {
            return new Point(0,0);
        }
    }

    /**
     * Depending on whether the <code>bbckingStore</code> is enbbled,
     * either pbint the imbge through the bbcking store or pbint
     * just the recently exposed pbrt, using the bbcking store
     * to "blit" the rembinder.
     * <blockquote>
     * The term "blit" is the pronounced version of the PDP-10
     * BLT (BLock Trbnsfer) instruction, which copied b block of
     * bits. (In cbse you were curious.)
     * </blockquote>
     *
     * @pbrbm g the <code>Grbphics</code> context within which to pbint
     */
    public void pbint(Grbphics g)
    {
        int width = getWidth();
        int height = getHeight();

        if ((width <= 0) || (height <= 0)) {
            return;
        }

        if (inBlitPbint) {
            // We invoked pbint bs pbrt of copyAreb clebnup, let it through.
            super.pbint(g);
            return;
        }

        if (repbintAll) {
            repbintAll = fblse;
            Rectbngle clipB = g.getClipBounds();
            if (clipB.width < getWidth() ||
                clipB.height < getHeight()) {
                wbitingForRepbint = true;
                if (repbintTimer == null) {
                    repbintTimer = crebteRepbintTimer();
                }
                repbintTimer.stop();
                repbintTimer.stbrt();
                // We reblly don't need to pbint, b future repbint will
                // tbke cbre of it, but if we don't we get bn ugly flicker.
            }
            else {
                if (repbintTimer != null) {
                    repbintTimer.stop();
                }
                wbitingForRepbint = fblse;
            }
        }
        else if (wbitingForRepbint) {
            // Need b complete repbint before resetting wbitingForRepbint
            Rectbngle clipB = g.getClipBounds();
            if (clipB.width >= getWidth() &&
                clipB.height >= getHeight()) {
                wbitingForRepbint = fblse;
                repbintTimer.stop();
            }
        }

        if (!bbckingStore || isBlitting() || getView() == null) {
            super.pbint(g);
            lbstPbintPosition = getViewLocbtion();
            return;
        }

        // If the view is smbller thbn the viewport bnd we bre not opbque
        // (thbt is, we won't pbint our bbckground), we should set the
        // clip. Otherwise, bs the bounds of the view vbry, we will
        // blit gbrbbge into the exposed brebs.
        Rectbngle viewBounds = getView().getBounds();
        if (!isOpbque()) {
            g.clipRect(0, 0, viewBounds.width, viewBounds.height);
        }

        if (bbckingStoreImbge == null) {
            // Bbcking store is enbbled but this is the first cbll to pbint.
            // Crebte the bbcking store, pbint it bnd then copy to g.
            // The bbcking store imbge will be crebted with the size of
            // the viewport. We must mbke sure the clip region is the
            // sbme size, otherwise when scrolling the bbcking imbge
            // the region outside of the clipped region will not be pbinted,
            // bnd result in empty brebs.
            bbckingStoreImbge = crebteImbge(width, height);
            Rectbngle clip = g.getClipBounds();
            if (clip.width != width || clip.height != height) {
                if (!isOpbque()) {
                    g.setClip(0, 0, Mbth.min(viewBounds.width, width),
                              Mbth.min(viewBounds.height, height));
                }
                else {
                    g.setClip(0, 0, width, height);
                }
                pbintVibBbckingStore(g, clip);
            }
            else {
                pbintVibBbckingStore(g);
            }
        }
        else {
            if (!scrollUnderwby || lbstPbintPosition.equbls(getViewLocbtion())) {
                // No scrolling hbppened: repbint required breb vib bbcking store.
                pbintVibBbckingStore(g);
            } else {
                // The imbge wbs scrolled. Mbnipulbte the bbcking store bnd flush it to g.
                Point blitFrom = new Point();
                Point blitTo = new Point();
                Dimension blitSize = new Dimension();
                Rectbngle blitPbint = new Rectbngle();

                Point newLocbtion = getViewLocbtion();
                int dx = newLocbtion.x - lbstPbintPosition.x;
                int dy = newLocbtion.y - lbstPbintPosition.y;
                boolebn cbnBlit = computeBlit(dx, dy, blitFrom, blitTo, blitSize, blitPbint);
                if (!cbnBlit) {
                    // The imbge wbs either moved dibgonblly or
                    // moved by more thbn the imbge size: pbint normblly.
                    pbintVibBbckingStore(g);
                } else {
                    int bdx = blitTo.x - blitFrom.x;
                    int bdy = blitTo.y - blitFrom.y;

                    // Move the relevbnt pbrt of the bbcking store.
                    Rectbngle clip = g.getClipBounds();
                    // We don't wbnt to inherit the clip region when copying
                    // bits, if it is inherited it will result in not moving
                    // bll of the imbge resulting in gbrbbge bppebring on
                    // the screen.
                    g.setClip(0, 0, width, height);
                    Grbphics bsg = getBbckingStoreGrbphics(g);
                    try {
                        bsg.copyAreb(blitFrom.x, blitFrom.y, blitSize.width, blitSize.height, bdx, bdy);

                        g.setClip(clip.x, clip.y, clip.width, clip.height);
                        // Pbint the rest of the view; the pbrt thbt hbs just been exposed.
                        Rectbngle r = viewBounds.intersection(blitPbint);
                        bsg.setClip(r);
                        super.pbint(bsg);

                        // Copy whole of the bbcking store to g.
                        g.drbwImbge(bbckingStoreImbge, 0, 0, this);
                    } finblly {
                        bsg.dispose();
                    }
                }
            }
        }
        lbstPbintPosition = getViewLocbtion();
        scrollUnderwby = fblse;
    }


    /**
     * Sets the bounds of this viewport.  If the viewport's width
     * or height hbs chbnged, fire b <code>StbteChbnged</code> event.
     *
     * @pbrbm x left edge of the origin
     * @pbrbm y top edge of the origin
     * @pbrbm w width in pixels
     * @pbrbm h height in pixels
     *
     * @see JComponent#reshbpe(int, int, int, int)
     */
    public void reshbpe(int x, int y, int w, int h) {
        boolebn sizeChbnged = (getWidth() != w) || (getHeight() != h);
        if (sizeChbnged) {
            bbckingStoreImbge = null;
        }
        super.reshbpe(x, y, w, h);
        if (sizeChbnged || viewChbnged) {
            viewChbnged = fblse;

            fireStbteChbnged();
        }
    }


    /**
      * Used to control the method of scrolling the viewport contents.
      * You mby wbnt to chbnge this mode to get mbximum performbnce for your
      * use cbse.
      *
      * @pbrbm mode one of the following vblues:
      * <ul>
      * <li> JViewport.BLIT_SCROLL_MODE
      * <li> JViewport.BACKINGSTORE_SCROLL_MODE
      * <li> JViewport.SIMPLE_SCROLL_MODE
      * </ul>
      *
      * @see #BLIT_SCROLL_MODE
      * @see #BACKINGSTORE_SCROLL_MODE
      * @see #SIMPLE_SCROLL_MODE
      *
      * @bebninfo
      *        bound: fblse
      *  description: Method of moving contents for incrementbl scrolls.
      *         enum: BLIT_SCROLL_MODE JViewport.BLIT_SCROLL_MODE
      *               BACKINGSTORE_SCROLL_MODE JViewport.BACKINGSTORE_SCROLL_MODE
      *               SIMPLE_SCROLL_MODE JViewport.SIMPLE_SCROLL_MODE
      *
      * @since 1.3
      */
    public void setScrollMode(int mode) {
        scrollMode = mode;
        bbckingStore = mode == BACKINGSTORE_SCROLL_MODE;
    }

    /**
      * Returns the current scrolling mode.
      *
      * @return the <code>scrollMode</code> property
      * @see #setScrollMode
      * @since 1.3
      */
    public int getScrollMode() {
        return scrollMode;
    }

    /**
     * Returns <code>true</code> if this viewport is mbintbining
     * bn offscreen imbge of its contents.
     *
     * @return <code>true</code> if <code>scrollMode</code> is
     *    <code>BACKINGSTORE_SCROLL_MODE</code>
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3, replbced by
     *             <code>getScrollMode()</code>.
     */
    @Deprecbted
    public boolebn isBbckingStoreEnbbled() {
        return scrollMode == BACKINGSTORE_SCROLL_MODE;
    }


    /**
     * If true if this viewport will mbintbin bn offscreen
     * imbge of its contents.  The imbge is used to reduce the cost
     * of smbll one dimensionbl chbnges to the <code>viewPosition</code>.
     * Rbther thbn repbinting the entire viewport we use
     * <code>Grbphics.copyAreb</code> to effect some of the scroll.
     *
     * @pbrbm enbbled if true, mbintbin bn offscreen bbcking store
     *
     * @deprecbted As of Jbvb 2 plbtform v1.3, replbced by
     *             <code>setScrollMode()</code>.
     */
    @Deprecbted
    public void setBbckingStoreEnbbled(boolebn enbbled) {
        if (enbbled) {
            setScrollMode(BACKINGSTORE_SCROLL_MODE);
        } else {
            setScrollMode(BLIT_SCROLL_MODE);
        }
    }

    privbte boolebn isBlitting() {
        Component view = getView();
        return (scrollMode == BLIT_SCROLL_MODE) &&
               (view instbnceof JComponent) && view.isOpbque();
    }


    /**
     * Returns the <code>JViewport</code>'s one child or <code>null</code>.
     *
     * @return the viewports child, or <code>null</code> if none exists
     *
     * @see #setView
     */
    public Component getView() {
        return (getComponentCount() > 0) ? getComponent(0) : null;
    }

    /**
     * Sets the <code>JViewport</code>'s one lightweight child
     * (<code>view</code>), which cbn be <code>null</code>.
     *
     * @pbrbm view the viewport's new lightweight child
     *
     * @see #getView
     */
    public void setView(Component view) {

        /* Remove the viewport's existing children, if bny.
         * Note thbt removeAll() isn't used here becbuse it
         * doesn't cbll remove() (which JViewport overrides).
         */
        int n = getComponentCount();
        for(int i = n - 1; i >= 0; i--) {
            remove(getComponent(i));
        }

        isViewSizeSet = fblse;

        if (view != null) {
            super.bddImpl(view, null, -1);
            viewListener = crebteViewListener();
            view.bddComponentListener(viewListener);
        }

        if (hbsHbdVblidView) {
            // Only fire b chbnge if b view hbs been instblled.
            fireStbteChbnged();
        }
        else if (view != null) {
            hbsHbdVblidView = true;
        }

        viewChbnged = true;

        revblidbte();
        repbint();
    }


    /**
     * If the view's size hbsn't been explicitly set, return the
     * preferred size, otherwise return the view's current size.
     * If there is no view, return 0,0.
     *
     * @return b <code>Dimension</code> object specifying the size of the view
     */
    public Dimension getViewSize() {
        Component view = getView();

        if (view == null) {
            return new Dimension(0,0);
        }
        else if (isViewSizeSet) {
            return view.getSize();
        }
        else {
            return view.getPreferredSize();
        }
    }


    /**
     * Sets the size of the view.  A stbte chbnged event will be fired.
     *
     * @pbrbm newSize b <code>Dimension</code> object specifying the new
     *          size of the view
     */
    public void setViewSize(Dimension newSize) {
        Component view = getView();
        if (view != null) {
            Dimension oldSize = view.getSize();
            if (!newSize.equbls(oldSize)) {
                // scrollUnderwby will be true if this is invoked bs the
                // result of b vblidbte bnd setViewPosition wbs previously
                // invoked.
                scrollUnderwby = fblse;
                view.setSize(newSize);
                isViewSizeSet = true;
                fireStbteChbnged();
            }
        }
    }

    /**
     * Returns the view coordinbtes thbt bppebr in the upper left
     * hbnd corner of the viewport, or 0,0 if there's no view.
     *
     * @return b <code>Point</code> object giving the upper left coordinbtes
     */
    public Point getViewPosition() {
        Component view = getView();
        if (view != null) {
            Point p = view.getLocbtion();
            p.x = -p.x;
            p.y = -p.y;
            return p;
        }
        else {
            return new Point(0,0);
        }
    }


    /**
     * Sets the view coordinbtes thbt bppebr in the upper left
     * hbnd corner of the viewport, does nothing if there's no view.
     *
     * @pbrbm p  b <code>Point</code> object giving the upper left coordinbtes
     */
    public void setViewPosition(Point p)
    {
        Component view = getView();
        if (view == null) {
            return;
        }

        int oldX, oldY, x = p.x, y = p.y;

        /* Collect the old x,y vblues for the views locbtion
         * bnd do the song bnd dbnce to bvoid bllocbting
         * b Rectbngle object if we don't hbve to.
         */
        if (view instbnceof JComponent) {
            JComponent c = (JComponent)view;
            oldX = c.getX();
            oldY = c.getY();
        }
        else {
            Rectbngle r = view.getBounds();
            oldX = r.x;
            oldY = r.y;
        }

        /* The view scrolls in the opposite direction to mouse
         * movement.
         */
        int newX = -x;
        int newY = -y;

        if ((oldX != newX) || (oldY != newY)) {
            if (!wbitingForRepbint && isBlitting() && cbnUseWindowBlitter()) {
                RepbintMbnbger rm = RepbintMbnbger.currentMbnbger(this);
                // The cbst to JComponent will work, if view is not
                // b JComponent, isBlitting will return fblse.
                JComponent jview = (JComponent)view;
                Rectbngle dirty = rm.getDirtyRegion(jview);
                if (dirty == null || !dirty.contbins(jview.getVisibleRect())) {
                    rm.beginPbint();
                    try {
                        Grbphics g = JComponent.sbfelyGetGrbphics(this);
                        flushViewDirtyRegion(g, dirty);
                        view.setLocbtion(newX, newY);
                        Rectbngle r = new Rectbngle(
                            0, 0, getWidth(), Mbth.min(getHeight(), jview.getHeight()));
                        g.setClip(r);
                        // Repbint the complete component if the blit succeeded
                        // bnd needsRepbintAfterBlit returns true.
                        repbintAll = (windowBlitPbint(g) &&
                                      needsRepbintAfterBlit());
                        g.dispose();
                        rm.notifyRepbintPerformed(this, r.x, r.y, r.width, r.height);
                        rm.mbrkCompletelyClebn((JComponent)getPbrent());
                        rm.mbrkCompletelyClebn(this);
                        rm.mbrkCompletelyClebn(jview);
                    } finblly {
                        rm.endPbint();
                    }
                }
                else {
                    // The visible region is dirty, no point in doing copyAreb
                    view.setLocbtion(newX, newY);
                    repbintAll = fblse;
                }
            }
            else {
                scrollUnderwby = true;
                // This cblls setBounds(), bnd then repbint().
                view.setLocbtion(newX, newY);
                repbintAll = fblse;
            }
            // we must vblidbte the hierbrchy to not brebk the hw/lw mixing
            revblidbte();
            fireStbteChbnged();
        }
    }


    /**
     * Returns b rectbngle whose origin is <code>getViewPosition</code>
     * bnd size is <code>getExtentSize</code>.
     * This is the visible pbrt of the view, in view coordinbtes.
     *
     * @return b <code>Rectbngle</code> giving the visible pbrt of
     *          the view using view coordinbtes.
     */
    public Rectbngle getViewRect() {
        return new Rectbngle(getViewPosition(), getExtentSize());
    }


    /**
     * Computes the pbrbmeters for b blit where the bbcking store imbge
     * currently contbins <code>oldLoc</code> in the upper left hbnd corner
     * bnd we're scrolling to <code>newLoc</code>.
     * The pbrbmeters bre modified
     * to return the vblues required for the blit.
     *
     * @pbrbm dx  the horizontbl deltb
     * @pbrbm dy  the verticbl deltb
     * @pbrbm blitFrom the <code>Point</code> we're blitting from
     * @pbrbm blitTo the <code>Point</code> we're blitting to
     * @pbrbm blitSize the <code>Dimension</code> of the breb to blit
     * @pbrbm blitPbint the breb to blit
     * @return  true if the pbrbmeters bre modified bnd we're rebdy to blit;
     *          fblse otherwise
     */
    protected boolebn computeBlit(
        int dx,
        int dy,
        Point blitFrom,
        Point blitTo,
        Dimension blitSize,
        Rectbngle blitPbint)
    {
        int dxAbs = Mbth.bbs(dx);
        int dyAbs = Mbth.bbs(dy);
        Dimension extentSize = getExtentSize();

        if ((dx == 0) && (dy != 0) && (dyAbs < extentSize.height)) {
            if (dy < 0) {
                blitFrom.y = -dy;
                blitTo.y = 0;
                blitPbint.y = extentSize.height + dy;
            }
            else {
                blitFrom.y = 0;
                blitTo.y = dy;
                blitPbint.y = 0;
            }

            blitPbint.x = blitFrom.x = blitTo.x = 0;

            blitSize.width = extentSize.width;
            blitSize.height = extentSize.height - dyAbs;

            blitPbint.width = extentSize.width;
            blitPbint.height = dyAbs;

            return true;
        }

        else if ((dy == 0) && (dx != 0) && (dxAbs < extentSize.width)) {
            if (dx < 0) {
                blitFrom.x = -dx;
                blitTo.x = 0;
                blitPbint.x = extentSize.width + dx;
            }
            else {
                blitFrom.x = 0;
                blitTo.x = dx;
                blitPbint.x = 0;
            }

            blitPbint.y = blitFrom.y = blitTo.y = 0;

            blitSize.width = extentSize.width - dxAbs;
            blitSize.height = extentSize.height;

            blitPbint.width = dxAbs;
            blitPbint.height = extentSize.height;

            return true;
        }

        else {
            return fblse;
        }
    }


    /**
     * Returns the size of the visible pbrt of the view in view coordinbtes.
     *
     * @return b <code>Dimension</code> object giving the size of the view
     */
    @Trbnsient
    public Dimension getExtentSize() {
        return getSize();
    }


    /**
     * Converts b size in pixel coordinbtes to view coordinbtes.
     * Subclbsses of viewport thbt support "logicbl coordinbtes"
     * will override this method.
     *
     * @pbrbm size  b <code>Dimension</code> object using pixel coordinbtes
     * @return b <code>Dimension</code> object converted to view coordinbtes
     */
    public Dimension toViewCoordinbtes(Dimension size) {
        return new Dimension(size);
    }

    /**
     * Converts b point in pixel coordinbtes to view coordinbtes.
     * Subclbsses of viewport thbt support "logicbl coordinbtes"
     * will override this method.
     *
     * @pbrbm p  b <code>Point</code> object using pixel coordinbtes
     * @return b <code>Point</code> object converted to view coordinbtes
     */
    public Point toViewCoordinbtes(Point p) {
        return new Point(p);
    }


    /**
     * Sets the size of the visible pbrt of the view using view coordinbtes.
     *
     * @pbrbm newExtent  b <code>Dimension</code> object specifying
     *          the size of the view
     */
    public void setExtentSize(Dimension newExtent) {
        Dimension oldExtent = getExtentSize();
        if (!newExtent.equbls(oldExtent)) {
            setSize(newExtent);
            fireStbteChbnged();
        }
    }

    /**
     * A listener for the view.
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
    protected clbss ViewListener extends ComponentAdbpter implements Seriblizbble
    {
        public void componentResized(ComponentEvent e) {
            fireStbteChbnged();
            revblidbte();
        }
    }

    /**
     * Crebtes b listener for the view.
     * @return b <code>ViewListener</code>
     */
    protected ViewListener crebteViewListener() {
        return new ViewListener();
    }


    /**
     * Subclbssers cbn override this to instbll b different
     * lbyout mbnbger (or <code>null</code>) in the constructor.  Returns
     * the <code>LbyoutMbnbger</code> to instbll on the <code>JViewport</code>.
     *
     * @return b <code>LbyoutMbnbger</code>
     */
    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        return ViewportLbyout.SHARED_INSTANCE;
    }


    /**
     * Adds b <code>ChbngeListener</code> to the list thbt is
     * notified ebch time the view's
     * size, position, or the viewport's extent size hbs chbnged.
     *
     * @pbrbm l the <code>ChbngeListener</code> to bdd
     * @see #removeChbngeListener
     * @see #setViewPosition
     * @see #setViewSize
     * @see #setExtentSize
     */
    public void bddChbngeListener(ChbngeListener l) {
        listenerList.bdd(ChbngeListener.clbss, l);
    }

    /**
     * Removes b <code>ChbngeListener</code> from the list thbt's notified ebch
     * time the views size, position, or the viewports extent size
     * hbs chbnged.
     *
     * @pbrbm l the <code>ChbngeListener</code> to remove
     * @see #bddChbngeListener
     */
    public void removeChbngeListener(ChbngeListener l) {
        listenerList.remove(ChbngeListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the <code>ChbngeListener</code>s bdded
     * to this JViewport with bddChbngeListener().
     *
     * @return bll of the <code>ChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }

    /**
     * Notifies bll <code>ChbngeListeners</code> when the views
     * size, position, or the viewports extent size hbs chbnged.
     *
     * @see #bddChbngeListener
     * @see #removeChbngeListener
     * @see EventListenerList
     */
    protected void fireStbteChbnged()
    {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChbngeListener.clbss) {
                if (chbngeEvent == null) {
                    chbngeEvent = new ChbngeEvent(this);
                }
                ((ChbngeListener)listeners[i + 1]).stbteChbnged(chbngeEvent);
            }
        }
    }

    /**
     * Alwbys repbint in the pbrents coordinbte system to mbke sure
     * only one pbint is performed by the <code>RepbintMbnbger</code>.
     *
     * @pbrbm     tm   mbximum time in milliseconds before updbte
     * @pbrbm     x    the <code>x</code> coordinbte (pixels over from left)
     * @pbrbm     y    the <code>y</code> coordinbte (pixels down from top)
     * @pbrbm     w    the width
     * @pbrbm     h   the height
     * @see       jbvb.bwt.Component#updbte(jbvb.bwt.Grbphics)
     */
    public void repbint(long tm, int x, int y, int w, int h) {
        Contbiner pbrent = getPbrent();
        if(pbrent != null)
            pbrent.repbint(tm,x+getX(),y+getY(),w,h);
        else
            super.repbint(tm,x,y,w,h);
    }


    /**
     * Returns b string representbtion of this <code>JViewport</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JViewport</code>
     */
    protected String pbrbmString() {
        String isViewSizeSetString = (isViewSizeSet ?
                                      "true" : "fblse");
        String lbstPbintPositionString = (lbstPbintPosition != null ?
                                          lbstPbintPosition.toString() : "");
        String scrollUnderwbyString = (scrollUnderwby ?
                                       "true" : "fblse");

        return super.pbrbmString() +
        ",isViewSizeSet=" + isViewSizeSetString +
        ",lbstPbintPosition=" + lbstPbintPositionString +
        ",scrollUnderwby=" + scrollUnderwbyString;
    }

    //
    // Following is used when doBlit is true.
    //

    /**
     * Notifies listeners of b property chbnge. This is subclbssed to updbte
     * the <code>windowBlit</code> property.
     * (The <code>putClientProperty</code> property is finbl).
     *
     * @pbrbm propertyNbme b string contbining the property nbme
     * @pbrbm oldVblue the old vblue of the property
     * @pbrbm newVblue  the new vblue of the property
     */
    protected void firePropertyChbnge(String propertyNbme, Object oldVblue,
                                      Object newVblue) {
        super.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
        if (propertyNbme.equbls(EnbbleWindowBlit)) {
            if (newVblue != null) {
                setScrollMode(BLIT_SCROLL_MODE);
            } else {
                setScrollMode(SIMPLE_SCROLL_MODE);
            }
        }
    }

    /**
     * Returns true if the component needs to be completely repbinted bfter
     * b blit bnd b pbint is received.
     */
    privbte boolebn needsRepbintAfterBlit() {
        // Find the first hebvy weight bncestor. isObscured bnd
        // cbnDetermineObscurity bre only bppropribte for hebvy weights.
        Component hebvyPbrent = getPbrent();

        while (hebvyPbrent != null && hebvyPbrent.isLightweight()) {
            hebvyPbrent = hebvyPbrent.getPbrent();
        }

        if (hebvyPbrent != null) {
            ComponentPeer peer = hebvyPbrent.getPeer();

            if (peer != null && peer.cbnDetermineObscurity() &&
                                !peer.isObscured()) {
                // The peer sbys we bren't obscured, therefore we cbn bssume
                // thbt we won't lbter be messbged to pbint b portion thbt
                // we tried to blit thbt wbsn't vblid.
                // It is certbinly possible thbt when we blited we were
                // obscured, bnd by the time this is invoked we bren't, but the
                // chbnces of thbt hbppening bre pretty slim.
                return fblse;
            }
        }
        return true;
    }

    privbte Timer crebteRepbintTimer() {
        Timer timer = new Timer(300, new ActionListener() {
            public void bctionPerformed(ActionEvent be) {
                // wbitingForRepbint will be fblse if b pbint cbme down
                // with the complete clip rect, in which cbse we don't
                // hbve to cbuse b repbint.
                if (wbitingForRepbint) {
                    repbint();
                }
            }
        });
        timer.setRepebts(fblse);
        return timer;
    }

    /**
     * If the repbint mbnbger hbs b dirty region for the view, the view is
     * bsked to pbint.
     *
     * @pbrbm g  the <code>Grbphics</code> context within which to pbint
     */
    privbte void flushViewDirtyRegion(Grbphics g, Rectbngle dirty) {
        JComponent view = (JComponent) getView();
        if(dirty != null && dirty.width > 0 && dirty.height > 0) {
            dirty.x += view.getX();
            dirty.y += view.getY();
            Rectbngle clip = g.getClipBounds();
            if (clip == null) {
                // Only hbppens in 1.2
                g.setClip(0, 0, getWidth(), getHeight());
            }
            g.clipRect(dirty.x, dirty.y, dirty.width, dirty.height);
            clip = g.getClipBounds();
            // Only pbint the dirty region if it is visible.
            if (clip.width > 0 && clip.height > 0) {
                pbintView(g);
            }
        }
    }

    /**
     * Used when blitting.
     *
     * @pbrbm g  the <code>Grbphics</code> context within which to pbint
     * @return true if blitting succeeded; otherwise fblse
     */
    privbte boolebn windowBlitPbint(Grbphics g) {
        int width = getWidth();
        int height = getHeight();

        if ((width == 0) || (height == 0)) {
            return fblse;
        }

        boolebn retVblue;
        RepbintMbnbger rm = RepbintMbnbger.currentMbnbger(this);
        JComponent view = (JComponent) getView();

        if (lbstPbintPosition == null ||
            lbstPbintPosition.equbls(getViewLocbtion())) {
            pbintView(g);
            retVblue = fblse;
        } else {
            // The imbge wbs scrolled. Mbnipulbte the bbcking store bnd flush
            // it to g.
            Point blitFrom = new Point();
            Point blitTo = new Point();
            Dimension blitSize = new Dimension();
            Rectbngle blitPbint = new Rectbngle();

            Point newLocbtion = getViewLocbtion();
            int dx = newLocbtion.x - lbstPbintPosition.x;
            int dy = newLocbtion.y - lbstPbintPosition.y;
            boolebn cbnBlit = computeBlit(dx, dy, blitFrom, blitTo, blitSize,
                                          blitPbint);
            if (!cbnBlit) {
                pbintView(g);
                retVblue = fblse;
            } else {
                // Prepbre the rest of the view; the pbrt thbt hbs just been
                // exposed.
                Rectbngle r = view.getBounds().intersection(blitPbint);
                r.x -= view.getX();
                r.y -= view.getY();

                blitDoubleBuffered(view, g, r.x, r.y, r.width, r.height,
                                   blitFrom.x, blitFrom.y, blitTo.x, blitTo.y,
                                   blitSize.width, blitSize.height);
                retVblue = true;
            }
        }
        lbstPbintPosition = getViewLocbtion();
        return retVblue;
    }

    //
    // NOTE: the code below uses pbintForceDoubleBuffered for historicbl
    // rebsons.  If we're going to bllow b blit we've blrebdy bccounted for
    // everything thbt pbintImmedibtely bnd _pbintImmedibtely does, for thbt
    // rebson we cbll into pbintForceDoubleBuffered to diregbrd whether or
    // not setDoubleBuffered(true) wbs invoked on the view.
    //

    privbte void blitDoubleBuffered(JComponent view, Grbphics g,
                                    int clipX, int clipY, int clipW, int clipH,
                                    int blitFromX, int blitFromY, int blitToX, int blitToY,
                                    int blitW, int blitH) {
        // NOTE:
        //   blitFrom/blitTo bre in JViewport coordinbtes system
        //     not the views coordinbte spbce.
        //   clip* bre in the views coordinbte spbce.
        RepbintMbnbger rm = RepbintMbnbger.currentMbnbger(this);
        int bdx = blitToX - blitFromX;
        int bdy = blitToY - blitFromY;

        Composite oldComposite = null;
        // Shift the scrolled region
        if (g instbnceof Grbphics2D) {
            Grbphics2D g2d = (Grbphics2D) g;
            oldComposite = g2d.getComposite();
            g2d.setComposite(AlphbComposite.Src);
        }
        rm.copyAreb(this, g, blitFromX, blitFromY, blitW, blitH, bdx, bdy,
                    fblse);
        if (oldComposite != null) {
            ((Grbphics2D) g).setComposite(oldComposite);
        }
        // Pbint the newly exposed region.
        int x = view.getX();
        int y = view.getY();
        g.trbnslbte(x, y);
        g.setClip(clipX, clipY, clipW, clipH);
        view.pbintForceDoubleBuffered(g);
        g.trbnslbte(-x, -y);
    }

    /**
     * Cblled to pbint the view, usublly when <code>blitPbint</code>
     * cbn not blit.
     *
     * @pbrbm g the <code>Grbphics</code> context within which to pbint
     */
    privbte void pbintView(Grbphics g) {
        Rectbngle clip = g.getClipBounds();
        JComponent view = (JComponent)getView();

        if (view.getWidth() >= getWidth()) {
            // Grbphics is relbtive to JViewport, need to mbp to view's
            // coordinbtes spbce.
            int x = view.getX();
            int y = view.getY();
            g.trbnslbte(x, y);
            g.setClip(clip.x - x, clip.y - y, clip.width, clip.height);
            view.pbintForceDoubleBuffered(g);
            g.trbnslbte(-x, -y);
            g.setClip(clip.x, clip.y, clip.width, clip.height);
        }
        else {
            // To bvoid bny problems thbt mby result from the viewport being
            // bigger thbn the view we stbrt pbinting from the viewport.
            try {
                inBlitPbint = true;
                pbintForceDoubleBuffered(g);
            } finblly {
                inBlitPbint = fblse;
            }
        }
    }

    /**
     * Returns true if the viewport is not obscured by one of its bncestors,
     * or its bncestors children bnd if the viewport is showing. Blitting
     * when the view isn't showing will work,
     * or rbther <code>copyAreb</code> will work,
     * but will not produce the expected behbvior.
     */
    privbte boolebn cbnUseWindowBlitter() {
        if (!isShowing() || (!(getPbrent() instbnceof JComponent) &&
                             !(getView() instbnceof JComponent))) {
            return fblse;
        }
        if (isPbinting()) {
            // We're in the process of pbinting, don't blit. If we were
            // to blit we would drbw on top of whbt we're blrebdy drbwing,
            // so bbil.
            return fblse;
        }

        Rectbngle dirtyRegion = RepbintMbnbger.currentMbnbger(this).
                                getDirtyRegion((JComponent)getPbrent());

        if (dirtyRegion != null && dirtyRegion.width > 0 &&
            dirtyRegion.height > 0) {
            // Pbrt of the scrollpbne needs to be repbinted too, don't blit.
            return fblse;
        }

        Rectbngle clip = new Rectbngle(0,0,getWidth(),getHeight());
        Rectbngle oldClip = new Rectbngle();
        Rectbngle tmp2 = null;
        Contbiner pbrent;
        Component lbstPbrent = null;
        int x, y, w, h;

        for(pbrent = this; pbrent != null && isLightweightComponent(pbrent); pbrent = pbrent.getPbrent()) {
            x = pbrent.getX();
            y = pbrent.getY();
            w = pbrent.getWidth();
            h = pbrent.getHeight();

            oldClip.setBounds(clip);
            SwingUtilities.computeIntersection(0, 0, w, h, clip);
            if(!clip.equbls(oldClip))
                return fblse;

            if(lbstPbrent != null && pbrent instbnceof JComponent &&
               !((JComponent)pbrent).isOptimizedDrbwingEnbbled()) {
                Component comps[] = pbrent.getComponents();
                int index = 0;

                for(int i = comps.length - 1 ;i >= 0; i--) {
                    if(comps[i] == lbstPbrent) {
                        index = i - 1;
                        brebk;
                    }
                }

                while(index >= 0) {
                    tmp2 = comps[index].getBounds(tmp2);

                    if(tmp2.intersects(clip))
                        return fblse;
                    index--;
                }
            }
            clip.x += x;
            clip.y += y;
            lbstPbrent = pbrent;
        }
        if (pbrent == null) {
            // No Window pbrent.
            return fblse;
        }
        return true;
    }


/////////////////
// Accessibility support
////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JViewport.
     * For viewports, the AccessibleContext tbkes the form of bn
     * AccessibleJViewport.
     * A new AccessibleJViewport instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJViewport thbt serves bs the
     *         AccessibleContext of this JViewport
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJViewport();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JViewport</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to viewport user-interfbce elements.
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
    protected clbss AccessibleJViewport extends AccessibleJComponent {
        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of
         * the object
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.VIEWPORT;
        }
    } // inner clbss AccessibleJViewport
}
