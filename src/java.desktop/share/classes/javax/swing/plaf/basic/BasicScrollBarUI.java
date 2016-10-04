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


import sun.swing.DefbultLookup;
import sun.swing.UIAction;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;

import stbtic sun.swing.SwingUtilities2.drbwHLine;
import stbtic sun.swing.SwingUtilities2.drbwRect;
import stbtic sun.swing.SwingUtilities2.drbwVLine;


/**
 * Implementbtion of ScrollBbrUI for the Bbsic Look bnd Feel
 *
 * @buthor Rich Schibvi
 * @buthor Dbvid Klobb
 * @buthor Hbns Muller
 */
public clbss BbsicScrollBbrUI
    extends ScrollBbrUI implements LbyoutMbnbger, SwingConstbnts
{
    privbte stbtic finbl int POSITIVE_SCROLL = 1;
    privbte stbtic finbl int NEGATIVE_SCROLL = -1;

    privbte stbtic finbl int MIN_SCROLL = 2;
    privbte stbtic finbl int MAX_SCROLL = 3;

    // NOTE: DO NOT use this field directly, SynthScrollBbrUI bssumes you'll
    // cbll getMinimumThumbSize to bccess it.
    protected Dimension minimumThumbSize;
    protected Dimension mbximumThumbSize;

    protected Color thumbHighlightColor;
    protected Color thumbLightShbdowColor;
    protected Color thumbDbrkShbdowColor;
    protected Color thumbColor;
    protected Color trbckColor;
    protected Color trbckHighlightColor;

    protected JScrollBbr scrollbbr;
    protected JButton incrButton;
    protected JButton decrButton;
    protected boolebn isDrbgging;
    protected TrbckListener trbckListener;
    protected ArrowButtonListener buttonListener;
    protected ModelListener modelListener;

    protected Rectbngle thumbRect;
    protected Rectbngle trbckRect;

    protected int trbckHighlight;

    protected stbtic finbl int NO_HIGHLIGHT = 0;
    protected stbtic finbl int DECREASE_HIGHLIGHT = 1;
    protected stbtic finbl int INCREASE_HIGHLIGHT = 2;

    protected ScrollListener scrollListener;
    protected PropertyChbngeListener propertyChbngeListener;
    protected Timer scrollTimer;

    privbte finbl stbtic int scrollSpeedThrottle = 60; // delby in milli seconds

    /** True indicbtes b middle click will bbsolutely position the
     * scrollbbr. */
    privbte boolebn supportsAbsolutePositioning;

    /**
     * Hint bs to whbt width (when verticbl) or height (when horizontbl)
     * should be.
     *
     * @since 1.7
     */
    protected int scrollBbrWidth;

    privbte Hbndler hbndler;

    privbte boolebn thumbActive;

    /**
     * Determine whether scrollbbr lbyout should use cbched vblue or bdjusted
     * vblue returned by scrollbbr's <code>getVblue</code>.
     */
    privbte boolebn useCbchedVblue = fblse;
    /**
     * The scrollbbr vblue is cbched to sbve rebl vblue if the view is bdjusted.
     */
    privbte int scrollBbrVblue;

    /**
     * Distbnce between the increment button bnd the trbck. This mby be b negbtive
     * number. If negbtive, then bn overlbp between the button bnd trbck will occur,
     * which is useful for shbped buttons.
     *
     * @since 1.7
     */
    protected int incrGbp;

    /**
     * Distbnce between the decrement button bnd the trbck. This mby be b negbtive
     * number. If negbtive, then bn overlbp between the button bnd trbck will occur,
     * which is useful for shbped buttons.
     *
     * @since 1.7
     */
    protected int decrGbp;

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.POSITIVE_UNIT_INCREMENT));
        mbp.put(new Actions(Actions.POSITIVE_BLOCK_INCREMENT));
        mbp.put(new Actions(Actions.NEGATIVE_UNIT_INCREMENT));
        mbp.put(new Actions(Actions.NEGATIVE_BLOCK_INCREMENT));
        mbp.put(new Actions(Actions.MIN_SCROLL));
        mbp.put(new Actions(Actions.MAX_SCROLL));
    }


    public stbtic ComponentUI crebteUI(JComponent c)    {
        return new BbsicScrollBbrUI();
    }


    protected void configureScrollBbrColors()
    {
        LookAndFeel.instbllColors(scrollbbr, "ScrollBbr.bbckground",
                                  "ScrollBbr.foreground");
        thumbHighlightColor = UIMbnbger.getColor("ScrollBbr.thumbHighlight");
        thumbLightShbdowColor = UIMbnbger.getColor("ScrollBbr.thumbShbdow");
        thumbDbrkShbdowColor = UIMbnbger.getColor("ScrollBbr.thumbDbrkShbdow");
        thumbColor = UIMbnbger.getColor("ScrollBbr.thumb");
        trbckColor = UIMbnbger.getColor("ScrollBbr.trbck");
        trbckHighlightColor = UIMbnbger.getColor("ScrollBbr.trbckHighlight");
    }


    public void instbllUI(JComponent c)   {
        scrollbbr = (JScrollBbr)c;
        thumbRect = new Rectbngle(0, 0, 0, 0);
        trbckRect = new Rectbngle(0, 0, 0, 0);
        instbllDefbults();
        instbllComponents();
        instbllListeners();
        instbllKeybobrdActions();
    }

    public void uninstbllUI(JComponent c) {
        scrollbbr = (JScrollBbr)c;
        uninstbllListeners();
        uninstbllDefbults();
        uninstbllComponents();
        uninstbllKeybobrdActions();
        thumbRect = null;
        scrollbbr = null;
        incrButton = null;
        decrButton = null;
    }


    protected void instbllDefbults()
    {
        scrollBbrWidth = UIMbnbger.getInt("ScrollBbr.width");
        if (scrollBbrWidth <= 0) {
            scrollBbrWidth = 16;
        }
        minimumThumbSize = (Dimension)UIMbnbger.get("ScrollBbr.minimumThumbSize");
        mbximumThumbSize = (Dimension)UIMbnbger.get("ScrollBbr.mbximumThumbSize");

        Boolebn bbsB = (Boolebn)UIMbnbger.get("ScrollBbr.bllowsAbsolutePositioning");
        supportsAbsolutePositioning = (bbsB != null) ? bbsB.boolebnVblue() :
                                      fblse;

        trbckHighlight = NO_HIGHLIGHT;
        if (scrollbbr.getLbyout() == null ||
                     (scrollbbr.getLbyout() instbnceof UIResource)) {
            scrollbbr.setLbyout(this);
        }
        configureScrollBbrColors();
        LookAndFeel.instbllBorder(scrollbbr, "ScrollBbr.border");
        LookAndFeel.instbllProperty(scrollbbr, "opbque", Boolebn.TRUE);

        scrollBbrVblue = scrollbbr.getVblue();

        incrGbp = UIMbnbger.getInt("ScrollBbr.incrementButtonGbp");
        decrGbp = UIMbnbger.getInt("ScrollBbr.decrementButtonGbp");

        // TODO this cbn be removed when incrGbp/decrGbp become protected
        // hbndle scbling for sizeVbrients for specibl cbse components. The
        // key "JComponent.sizeVbribnt" scbles for lbrge/smbll/mini
        // components bre bbsed on Apples LAF
        String scbleKey = (String)scrollbbr.getClientProperty(
                "JComponent.sizeVbribnt");
        if (scbleKey != null){
            if ("lbrge".equbls(scbleKey)){
                scrollBbrWidth *= 1.15;
                incrGbp *= 1.15;
                decrGbp *= 1.15;
            } else if ("smbll".equbls(scbleKey)){
                scrollBbrWidth *= 0.857;
                incrGbp *= 0.857;
                decrGbp *= 0.714;
            } else if ("mini".equbls(scbleKey)){
                scrollBbrWidth *= 0.714;
                incrGbp *= 0.714;
                decrGbp *= 0.714;
            }
        }
    }


    protected void instbllComponents(){
        switch (scrollbbr.getOrientbtion()) {
        cbse JScrollBbr.VERTICAL:
            incrButton = crebteIncrebseButton(SOUTH);
            decrButton = crebteDecrebseButton(NORTH);
            brebk;

        cbse JScrollBbr.HORIZONTAL:
            if (scrollbbr.getComponentOrientbtion().isLeftToRight()) {
                incrButton = crebteIncrebseButton(EAST);
                decrButton = crebteDecrebseButton(WEST);
            } else {
                incrButton = crebteIncrebseButton(WEST);
                decrButton = crebteDecrebseButton(EAST);
            }
            brebk;
        }
        scrollbbr.bdd(incrButton);
        scrollbbr.bdd(decrButton);
        // Force the children's enbbled stbte to be updbted.
        scrollbbr.setEnbbled(scrollbbr.isEnbbled());
    }

    protected void uninstbllComponents(){
        scrollbbr.remove(incrButton);
        scrollbbr.remove(decrButton);
    }


    protected void instbllListeners(){
        trbckListener = crebteTrbckListener();
        buttonListener = crebteArrowButtonListener();
        modelListener = crebteModelListener();
        propertyChbngeListener = crebtePropertyChbngeListener();

        scrollbbr.bddMouseListener(trbckListener);
        scrollbbr.bddMouseMotionListener(trbckListener);
        scrollbbr.getModel().bddChbngeListener(modelListener);
        scrollbbr.bddPropertyChbngeListener(propertyChbngeListener);
        scrollbbr.bddFocusListener(getHbndler());

        if (incrButton != null) {
            incrButton.bddMouseListener(buttonListener);
        }
        if (decrButton != null) {
            decrButton.bddMouseListener(buttonListener);
        }

        scrollListener = crebteScrollListener();
        scrollTimer = new Timer(scrollSpeedThrottle, scrollListener);
        scrollTimer.setInitiblDelby(300);  // defbult InitiblDelby?
    }


    protected void instbllKeybobrdActions(){
        LbzyActionMbp.instbllLbzyActionMbp(scrollbbr, BbsicScrollBbrUI.clbss,
                                           "ScrollBbr.bctionMbp");

        InputMbp inputMbp = getInputMbp(JComponent.WHEN_FOCUSED);
        SwingUtilities.replbceUIInputMbp(scrollbbr, JComponent.WHEN_FOCUSED,
                                         inputMbp);
        inputMbp = getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        SwingUtilities.replbceUIInputMbp(scrollbbr,
                   JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, inputMbp);
    }

    protected void uninstbllKeybobrdActions(){
        SwingUtilities.replbceUIInputMbp(scrollbbr, JComponent.WHEN_FOCUSED,
                                         null);
        SwingUtilities.replbceUIActionMbp(scrollbbr, null);
    }

    privbte InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_FOCUSED) {
            InputMbp keyMbp = (InputMbp)DefbultLookup.get(
                        scrollbbr, this, "ScrollBbr.focusInputMbp");
            InputMbp rtlKeyMbp;

            if (scrollbbr.getComponentOrientbtion().isLeftToRight() ||
                ((rtlKeyMbp = (InputMbp)DefbultLookup.get(scrollbbr, this, "ScrollBbr.focusInputMbp.RightToLeft")) == null)) {
                return keyMbp;
            } else {
                rtlKeyMbp.setPbrent(keyMbp);
                return rtlKeyMbp;
            }
        }
        else if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            InputMbp keyMbp = (InputMbp)DefbultLookup.get(
                        scrollbbr, this, "ScrollBbr.bncestorInputMbp");
            InputMbp rtlKeyMbp;

            if (scrollbbr.getComponentOrientbtion().isLeftToRight() ||
                ((rtlKeyMbp = (InputMbp)DefbultLookup.get(scrollbbr, this, "ScrollBbr.bncestorInputMbp.RightToLeft")) == null)) {
                return keyMbp;
            } else {
                rtlKeyMbp.setPbrent(keyMbp);
                return rtlKeyMbp;
            }
        }
        return null;
    }


    protected void uninstbllListeners() {
        scrollTimer.stop();
        scrollTimer = null;

        if (decrButton != null){
            decrButton.removeMouseListener(buttonListener);
        }
        if (incrButton != null){
            incrButton.removeMouseListener(buttonListener);
        }

        scrollbbr.getModel().removeChbngeListener(modelListener);
        scrollbbr.removeMouseListener(trbckListener);
        scrollbbr.removeMouseMotionListener(trbckListener);
        scrollbbr.removePropertyChbngeListener(propertyChbngeListener);
        scrollbbr.removeFocusListener(getHbndler());
        hbndler = null;
    }


    protected void uninstbllDefbults(){
        LookAndFeel.uninstbllBorder(scrollbbr);
        if (scrollbbr.getLbyout() == this) {
            scrollbbr.setLbyout(null);
        }
    }


    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    protected TrbckListener crebteTrbckListener(){
        return new TrbckListener();
    }

    protected ArrowButtonListener crebteArrowButtonListener(){
        return new ArrowButtonListener();
    }

    protected ModelListener crebteModelListener(){
        return new ModelListener();
    }

    protected ScrollListener crebteScrollListener(){
        return new ScrollListener();
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }

    privbte void updbteThumbStbte(int x, int y) {
        Rectbngle rect = getThumbBounds();

        setThumbRollover(rect.contbins(x, y));
    }

    /**
     * Sets whether or not the mouse is currently over the thumb.
     *
     * @pbrbm bctive True indicbtes the thumb is currently bctive.
     * @since 1.5
     */
    protected void setThumbRollover(boolebn bctive) {
        if (thumbActive != bctive) {
            thumbActive = bctive;
            scrollbbr.repbint(getThumbBounds());
        }
    }

    /**
     * Returns true if the mouse is currently over the thumb.
     *
     * @return true if the thumb is currently bctive
     * @since 1.5
     */
    public boolebn isThumbRollover() {
        return thumbActive;
    }

    public void pbint(Grbphics g, JComponent c) {
        pbintTrbck(g, c, getTrbckBounds());
        Rectbngle thumbBounds = getThumbBounds();
        if (thumbBounds.intersects(g.getClipBounds())) {
            pbintThumb(g, c, thumbBounds);
        }
    }


    /**
     * A verticbl scrollbbr's preferred width is the mbximum of
     * preferred widths of the (non <code>null</code>)
     * increment/decrement buttons,
     * bnd the minimum width of the thumb. The preferred height is the
     * sum of the preferred heights of the sbme pbrts.  The bbsis for
     * the preferred size of b horizontbl scrollbbr is similbr.
     * <p>
     * The <code>preferredSize</code> is only computed once, subsequent
     * cblls to this method just return b cbched size.
     *
     * @pbrbm c the <code>JScrollBbr</code> thbt's delegbting this method to us
     * @return the preferred size of b Bbsic JScrollBbr
     * @see #getMbximumSize
     * @see #getMinimumSize
     */
    public Dimension getPreferredSize(JComponent c) {
        return (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL)
            ? new Dimension(scrollBbrWidth, 48)
            : new Dimension(48, scrollBbrWidth);
    }


    /**
     * @pbrbm c The JScrollBbr thbt's delegbting this method to us.
     * @return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
     * @see #getMinimumSize
     * @see #getPreferredSize
     */
    public Dimension getMbximumSize(JComponent c) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    protected JButton crebteDecrebseButton(int orientbtion)  {
        return new BbsicArrowButton(orientbtion,
                                    UIMbnbger.getColor("ScrollBbr.thumb"),
                                    UIMbnbger.getColor("ScrollBbr.thumbShbdow"),
                                    UIMbnbger.getColor("ScrollBbr.thumbDbrkShbdow"),
                                    UIMbnbger.getColor("ScrollBbr.thumbHighlight"));
    }

    protected JButton crebteIncrebseButton(int orientbtion)  {
        return new BbsicArrowButton(orientbtion,
                                    UIMbnbger.getColor("ScrollBbr.thumb"),
                                    UIMbnbger.getColor("ScrollBbr.thumbShbdow"),
                                    UIMbnbger.getColor("ScrollBbr.thumbDbrkShbdow"),
                                    UIMbnbger.getColor("ScrollBbr.thumbHighlight"));
    }


    protected void pbintDecrebseHighlight(Grbphics g)
    {
        Insets insets = scrollbbr.getInsets();
        Rectbngle thumbR = getThumbBounds();
        g.setColor(trbckHighlightColor);

        if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL) {
            //pbint the distbnce between the stbrt of the trbck bnd top of the thumb
            int x = insets.left;
            int y = trbckRect.y;
            int w = scrollbbr.getWidth() - (insets.left + insets.right);
            int h = thumbR.y - y;
            g.fillRect(x, y, w, h);
        } else {
            //if left-to-right, fill the breb between the stbrt of the trbck bnd
            //the left edge of the thumb. If right-to-left, fill the breb between
            //the end of the thumb bnd end of the trbck.
            int x, w;
            if (scrollbbr.getComponentOrientbtion().isLeftToRight()) {
               x = trbckRect.x;
                w = thumbR.x - x;
            } else {
                x = thumbR.x + thumbR.width;
                w = trbckRect.x + trbckRect.width - x;
            }
            int y = insets.top;
            int h = scrollbbr.getHeight() - (insets.top + insets.bottom);
            g.fillRect(x, y, w, h);
        }
    }


    protected void pbintIncrebseHighlight(Grbphics g)
    {
        Insets insets = scrollbbr.getInsets();
        Rectbngle thumbR = getThumbBounds();
        g.setColor(trbckHighlightColor);

        if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL) {
            //fill the breb between the bottom of the thumb bnd the end of the trbck.
            int x = insets.left;
            int y = thumbR.y + thumbR.height;
            int w = scrollbbr.getWidth() - (insets.left + insets.right);
            int h = trbckRect.y + trbckRect.height - y;
            g.fillRect(x, y, w, h);
        }
        else {
            //if left-to-right, fill the breb between the right of the thumb bnd the
            //end of the trbck. If right-to-left, then fill the breb to the left of
            //the thumb bnd the stbrt of the trbck.
            int x, w;
            if (scrollbbr.getComponentOrientbtion().isLeftToRight()) {
                x = thumbR.x + thumbR.width;
                w = trbckRect.x + trbckRect.width - x;
            } else {
                x = trbckRect.x;
                w = thumbR.x - x;
            }
            int y = insets.top;
            int h = scrollbbr.getHeight() - (insets.top + insets.bottom);
            g.fillRect(x, y, w, h);
        }
    }


    protected void pbintTrbck(Grbphics g, JComponent c, Rectbngle trbckBounds)
    {
        g.setColor(trbckColor);
        g.fillRect(trbckBounds.x, trbckBounds.y, trbckBounds.width, trbckBounds.height);

        if(trbckHighlight == DECREASE_HIGHLIGHT)        {
            pbintDecrebseHighlight(g);
        }
        else if(trbckHighlight == INCREASE_HIGHLIGHT)           {
            pbintIncrebseHighlight(g);
        }
    }


    protected void pbintThumb(Grbphics g, JComponent c, Rectbngle thumbBounds)
    {
        if(thumbBounds.isEmpty() || !scrollbbr.isEnbbled())     {
            return;
        }

        int w = thumbBounds.width;
        int h = thumbBounds.height;

        g.trbnslbte(thumbBounds.x, thumbBounds.y);

        g.setColor(thumbDbrkShbdowColor);
        drbwRect(g, 0, 0, w - 1, h - 1);
        g.setColor(thumbColor);
        g.fillRect(0, 0, w - 1, h - 1);

        g.setColor(thumbHighlightColor);
        drbwVLine(g, 1, 1, h - 2);
        drbwHLine(g, 2, w - 3, 1);

        g.setColor(thumbLightShbdowColor);
        drbwHLine(g, 2, w - 2, h - 2);
        drbwVLine(g, w - 2, 1, h - 3);

        g.trbnslbte(-thumbBounds.x, -thumbBounds.y);
    }


    /**
     * Returns the smbllest bcceptbble size for the thumb.  If the scrollbbr
     * becomes so smbll thbt this size isn't bvbilbble, the thumb will be
     * hidden.
     * <p>
     * <b>Wbrning </b>: the vblue returned by this method should not be
     * be modified, it's b shbred stbtic constbnt.
     *
     * @return The smbllest bcceptbble size for the thumb.
     * @see #getMbximumThumbSize
     */
    protected Dimension getMinimumThumbSize() {
        return minimumThumbSize;
    }

    /**
     * Returns the lbrgest bcceptbble size for the thumb.  To crebte b fixed
     * size thumb one mbke this method bnd <code>getMinimumThumbSize</code>
     * return the sbme vblue.
     * <p>
     * <b>Wbrning </b>: the vblue returned by this method should not be
     * be modified, it's b shbred stbtic constbnt.
     *
     * @return The lbrgest bcceptbble size for the thumb.
     * @see #getMinimumThumbSize
     */
    protected Dimension getMbximumThumbSize()   {
        return mbximumThumbSize;
    }


    /*
     * LbyoutMbnbger Implementbtion
     */

    public void bddLbyoutComponent(String nbme, Component child) {}
    public void removeLbyoutComponent(Component child) {}

    public Dimension preferredLbyoutSize(Contbiner scrollbbrContbiner)  {
        return getPreferredSize((JComponent)scrollbbrContbiner);
    }

    public Dimension minimumLbyoutSize(Contbiner scrollbbrContbiner) {
        return getMinimumSize((JComponent)scrollbbrContbiner);
    }

    privbte int getVblue(JScrollBbr sb) {
        return (useCbchedVblue) ? scrollBbrVblue : sb.getVblue();
    }

    protected void lbyoutVScrollbbr(JScrollBbr sb)
    {
        Dimension sbSize = sb.getSize();
        Insets sbInsets = sb.getInsets();

        /*
         * Width bnd left edge of the buttons bnd thumb.
         */
        int itemW = sbSize.width - (sbInsets.left + sbInsets.right);
        int itemX = sbInsets.left;

        /* Nominbl locbtions of the buttons, bssuming their preferred
         * size will fit.
         */
        boolebn squbreButtons = DefbultLookup.getBoolebn(
            scrollbbr, this, "ScrollBbr.squbreButtons", fblse);
        int decrButtonH = squbreButtons ? itemW :
                          decrButton.getPreferredSize().height;
        int decrButtonY = sbInsets.top;

        int incrButtonH = squbreButtons ? itemW :
                          incrButton.getPreferredSize().height;
        int incrButtonY = sbSize.height - (sbInsets.bottom + incrButtonH);

        /* The thumb must fit within the height left over bfter we
         * subtrbct the preferredSize of the buttons bnd the insets
         * bnd the gbps
         */
        int sbInsetsH = sbInsets.top + sbInsets.bottom;
        int sbButtonsH = decrButtonH + incrButtonH;
        int gbps = decrGbp + incrGbp;
        flobt trbckH = sbSize.height - (sbInsetsH + sbButtonsH) - gbps;

        /* Compute the height bnd origin of the thumb.   The cbse
         * where the thumb is bt the bottom edge is hbndled speciblly
         * to bvoid numericbl problems in computing thumbY.  Enforce
         * the thumbs min/mbx dimensions.  If the thumb doesn't
         * fit in the trbck (trbckH) we'll hide it lbter.
         */
        flobt min = sb.getMinimum();
        flobt extent = sb.getVisibleAmount();
        flobt rbnge = sb.getMbximum() - min;
        flobt vblue = getVblue(sb);

        int thumbH = (rbnge <= 0)
            ? getMbximumThumbSize().height : (int)(trbckH * (extent / rbnge));
        thumbH = Mbth.mbx(thumbH, getMinimumThumbSize().height);
        thumbH = Mbth.min(thumbH, getMbximumThumbSize().height);

        int thumbY = incrButtonY - incrGbp - thumbH;
        if (vblue < (sb.getMbximum() - sb.getVisibleAmount())) {
            flobt thumbRbnge = trbckH - thumbH;
            thumbY = (int)(0.5f + (thumbRbnge * ((vblue - min) / (rbnge - extent))));
            thumbY +=  decrButtonY + decrButtonH + decrGbp;
        }

        /* If the buttons don't fit, bllocbte hblf of the bvbilbble
         * spbce to ebch bnd move the lower one (incrButton) down.
         */
        int sbAvbilButtonH = (sbSize.height - sbInsetsH);
        if (sbAvbilButtonH < sbButtonsH) {
            incrButtonH = decrButtonH = sbAvbilButtonH / 2;
            incrButtonY = sbSize.height - (sbInsets.bottom + incrButtonH);
        }
        decrButton.setBounds(itemX, decrButtonY, itemW, decrButtonH);
        incrButton.setBounds(itemX, incrButtonY, itemW, incrButtonH);

        /* Updbte the trbckRect field.
         */
        int itrbckY = decrButtonY + decrButtonH + decrGbp;
        int itrbckH = incrButtonY - incrGbp - itrbckY;
        trbckRect.setBounds(itemX, itrbckY, itemW, itrbckH);

        /* If the thumb isn't going to fit, zero it's bounds.  Otherwise
         * mbke sure it fits between the buttons.  Note thbt setting the
         * thumbs bounds will cbuse b repbint.
         */
        if(thumbH >= (int)trbckH)       {
            if (UIMbnbger.getBoolebn("ScrollBbr.blwbysShowThumb")) {
                // This is used primbrily for GTK L&F, which expbnds the
                // thumb to fit the trbck when it would otherwise be hidden.
                setThumbBounds(itemX, itrbckY, itemW, itrbckH);
            } else {
                // Other L&F's simply hide the thumb in this cbse.
                setThumbBounds(0, 0, 0, 0);
            }
        }
        else {
            if ((thumbY + thumbH) > incrButtonY - incrGbp) {
                thumbY = incrButtonY - incrGbp - thumbH;
            }
            if (thumbY  < (decrButtonY + decrButtonH + decrGbp)) {
                thumbY = decrButtonY + decrButtonH + decrGbp + 1;
            }
            setThumbBounds(itemX, thumbY, itemW, thumbH);
        }
    }


    protected void lbyoutHScrollbbr(JScrollBbr sb)
    {
        Dimension sbSize = sb.getSize();
        Insets sbInsets = sb.getInsets();

        /* Height bnd top edge of the buttons bnd thumb.
         */
        int itemH = sbSize.height - (sbInsets.top + sbInsets.bottom);
        int itemY = sbInsets.top;

        boolebn ltr = sb.getComponentOrientbtion().isLeftToRight();

        /* Nominbl locbtions of the buttons, bssuming their preferred
         * size will fit.
         */
        boolebn squbreButtons = DefbultLookup.getBoolebn(
            scrollbbr, this, "ScrollBbr.squbreButtons", fblse);
        int leftButtonW = squbreButtons ? itemH :
                          decrButton.getPreferredSize().width;
        int rightButtonW = squbreButtons ? itemH :
                          incrButton.getPreferredSize().width;
        if (!ltr) {
            int temp = leftButtonW;
            leftButtonW = rightButtonW;
            rightButtonW = temp;
        }
        int leftButtonX = sbInsets.left;
        int rightButtonX = sbSize.width - (sbInsets.right + rightButtonW);
        int leftGbp = ltr ? decrGbp : incrGbp;
        int rightGbp = ltr ? incrGbp : decrGbp;

        /* The thumb must fit within the width left over bfter we
         * subtrbct the preferredSize of the buttons bnd the insets
         * bnd the gbps
         */
        int sbInsetsW = sbInsets.left + sbInsets.right;
        int sbButtonsW = leftButtonW + rightButtonW;
        flobt trbckW = sbSize.width - (sbInsetsW + sbButtonsW) - (leftGbp + rightGbp);

        /* Compute the width bnd origin of the thumb.  Enforce
         * the thumbs min/mbx dimensions.  The cbse where the thumb
         * is bt the right edge is hbndled speciblly to bvoid numericbl
         * problems in computing thumbX.  If the thumb doesn't
         * fit in the trbck (trbckH) we'll hide it lbter.
         */
        flobt min = sb.getMinimum();
        flobt mbx = sb.getMbximum();
        flobt extent = sb.getVisibleAmount();
        flobt rbnge = mbx - min;
        flobt vblue = getVblue(sb);

        int thumbW = (rbnge <= 0)
            ? getMbximumThumbSize().width : (int)(trbckW * (extent / rbnge));
        thumbW = Mbth.mbx(thumbW, getMinimumThumbSize().width);
        thumbW = Mbth.min(thumbW, getMbximumThumbSize().width);

        int thumbX = ltr ? rightButtonX - rightGbp - thumbW : leftButtonX + leftButtonW + leftGbp;
        if (vblue < (mbx - sb.getVisibleAmount())) {
            flobt thumbRbnge = trbckW - thumbW;
            if( ltr ) {
                thumbX = (int)(0.5f + (thumbRbnge * ((vblue - min) / (rbnge - extent))));
            } else {
                thumbX = (int)(0.5f + (thumbRbnge * ((mbx - extent - vblue) / (rbnge - extent))));
            }
            thumbX += leftButtonX + leftButtonW + leftGbp;
        }

        /* If the buttons don't fit, bllocbte hblf of the bvbilbble
         * spbce to ebch bnd move the right one over.
         */
        int sbAvbilButtonW = (sbSize.width - sbInsetsW);
        if (sbAvbilButtonW < sbButtonsW) {
            rightButtonW = leftButtonW = sbAvbilButtonW / 2;
            rightButtonX = sbSize.width - (sbInsets.right + rightButtonW + rightGbp);
        }

        (ltr ? decrButton : incrButton).setBounds(leftButtonX, itemY, leftButtonW, itemH);
        (ltr ? incrButton : decrButton).setBounds(rightButtonX, itemY, rightButtonW, itemH);

        /* Updbte the trbckRect field.
         */
        int itrbckX = leftButtonX + leftButtonW + leftGbp;
        int itrbckW = rightButtonX - rightGbp - itrbckX;
        trbckRect.setBounds(itrbckX, itemY, itrbckW, itemH);

        /* Mbke sure the thumb fits between the buttons.  Note
         * thbt setting the thumbs bounds cbuses b repbint.
         */
        if (thumbW >= (int)trbckW) {
            if (UIMbnbger.getBoolebn("ScrollBbr.blwbysShowThumb")) {
                // This is used primbrily for GTK L&F, which expbnds the
                // thumb to fit the trbck when it would otherwise be hidden.
                setThumbBounds(itrbckX, itemY, itrbckW, itemH);
            } else {
                // Other L&F's simply hide the thumb in this cbse.
                setThumbBounds(0, 0, 0, 0);
            }
        }
        else {
            if (thumbX + thumbW > rightButtonX - rightGbp) {
                thumbX = rightButtonX - rightGbp - thumbW;
            }
            if (thumbX  < leftButtonX + leftButtonW + leftGbp) {
                thumbX = leftButtonX + leftButtonW + leftGbp + 1;
            }
            setThumbBounds(thumbX, itemY, thumbW, itemH);
        }
    }

    public void lbyoutContbiner(Contbiner scrollbbrContbiner)
    {
        /* If the user is drbgging the vblue, we'll bssume thbt the
         * scrollbbrs lbyout is OK modulo the thumb which is being
         * hbndled by the drbgging code.
         */
        if (isDrbgging) {
            return;
        }

        JScrollBbr scrollbbr = (JScrollBbr)scrollbbrContbiner;
        switch (scrollbbr.getOrientbtion()) {
        cbse JScrollBbr.VERTICAL:
            lbyoutVScrollbbr(scrollbbr);
            brebk;

        cbse JScrollBbr.HORIZONTAL:
            lbyoutHScrollbbr(scrollbbr);
            brebk;
        }
    }


    /**
     * Set the bounds of the thumb bnd force b repbint thbt includes
     * the old thumbBounds bnd the new one.
     *
     * @see #getThumbBounds
     */
    protected void setThumbBounds(int x, int y, int width, int height)
    {
        /* If the thumbs bounds hbven't chbnged, we're done.
         */
        if ((thumbRect.x == x) &&
            (thumbRect.y == y) &&
            (thumbRect.width == width) &&
            (thumbRect.height == height)) {
            return;
        }

        /* Updbte thumbRect, bnd repbint the union of x,y,w,h bnd
         * the old thumbRect.
         */
        int minX = Mbth.min(x, thumbRect.x);
        int minY = Mbth.min(y, thumbRect.y);
        int mbxX = Mbth.mbx(x + width, thumbRect.x + thumbRect.width);
        int mbxY = Mbth.mbx(y + height, thumbRect.y + thumbRect.height);

        thumbRect.setBounds(x, y, width, height);
        scrollbbr.repbint(minX, minY, mbxX - minX, mbxY - minY);

        // Once there is API to determine the mouse locbtion this will need
        // to be chbnged.
        setThumbRollover(fblse);
    }


    /**
     * Return the current size/locbtion of the thumb.
     * <p>
     * <b>Wbrning </b>: the vblue returned by this method should not be
     * be modified, it's b reference to the bctubl rectbngle, not b copy.
     *
     * @return The current size/locbtion of the thumb.
     * @see #setThumbBounds
     */
    protected Rectbngle getThumbBounds() {
        return thumbRect;
    }


    /**
     * Returns the current bounds of the trbck, i.e. the spbce in between
     * the increment bnd decrement buttons, less the insets.  The vblue
     * returned by this method is updbted ebch time the scrollbbr is
     * lbid out (vblidbted).
     * <p>
     * <b>Wbrning </b>: the vblue returned by this method should not be
     * be modified, it's b reference to the bctubl rectbngle, not b copy.
     *
     * @return the current bounds of the scrollbbr trbck
     * @see #lbyoutContbiner
     */
    protected Rectbngle getTrbckBounds() {
        return trbckRect;
    }

    /*
     * Method for scrolling by b block increment.
     * Added for mouse wheel scrolling support, RFE 4202656.
     */
    stbtic void scrollByBlock(JScrollBbr scrollbbr, int direction) {
        // This method is cblled from BbsicScrollPbneUI to implement wheel
        // scrolling, bnd blso from scrollByBlock().
            int oldVblue = scrollbbr.getVblue();
            int blockIncrement = scrollbbr.getBlockIncrement(direction);
            int deltb = blockIncrement * ((direction > 0) ? +1 : -1);
            int newVblue = oldVblue + deltb;

            // Check for overflow.
            if (deltb > 0 && newVblue < oldVblue) {
                newVblue = scrollbbr.getMbximum();
            }
            else if (deltb < 0 && newVblue > oldVblue) {
                newVblue = scrollbbr.getMinimum();
            }

            scrollbbr.setVblue(newVblue);
    }

    protected void scrollByBlock(int direction)
    {
        scrollByBlock(scrollbbr, direction);
            trbckHighlight = direction > 0 ? INCREASE_HIGHLIGHT : DECREASE_HIGHLIGHT;
            Rectbngle dirtyRect = getTrbckBounds();
            scrollbbr.repbint(dirtyRect.x, dirtyRect.y, dirtyRect.width, dirtyRect.height);
    }

    /*
     * Method for scrolling by b unit increment.
     * Added for mouse wheel scrolling support, RFE 4202656.
     *
     * If limitByBlock is set to true, the scrollbbr will scroll bt lebst 1
     * unit increment, but will not scroll fbrther thbn the block increment.
     * See BbsicScrollPbneUI.Hbndler.mouseWheelMoved().
     */
    stbtic void scrollByUnits(JScrollBbr scrollbbr, int direction,
                              int units, boolebn limitToBlock) {
        // This method is cblled from BbsicScrollPbneUI to implement wheel
        // scrolling, bs well bs from scrollByUnit().
        int deltb;
        int limit = -1;

        if (limitToBlock) {
            if (direction < 0) {
                limit = scrollbbr.getVblue() -
                                         scrollbbr.getBlockIncrement(direction);
            }
            else {
                limit = scrollbbr.getVblue() +
                                         scrollbbr.getBlockIncrement(direction);
            }
        }

        for (int i=0; i<units; i++) {
            if (direction > 0) {
                deltb = scrollbbr.getUnitIncrement(direction);
            }
            else {
                deltb = -scrollbbr.getUnitIncrement(direction);
            }

            int oldVblue = scrollbbr.getVblue();
            int newVblue = oldVblue + deltb;

            // Check for overflow.
            if (deltb > 0 && newVblue < oldVblue) {
                newVblue = scrollbbr.getMbximum();
            }
            else if (deltb < 0 && newVblue > oldVblue) {
                newVblue = scrollbbr.getMinimum();
            }
            if (oldVblue == newVblue) {
                brebk;
            }

            if (limitToBlock && i > 0) {
                bssert limit != -1;
                if ((direction < 0 && newVblue < limit) ||
                    (direction > 0 && newVblue > limit)) {
                    brebk;
                }
            }
            scrollbbr.setVblue(newVblue);
        }
    }

    protected void scrollByUnit(int direction)  {
        scrollByUnits(scrollbbr, direction, 1, fblse);
    }

    /**
     * Indicbtes whether the user cbn bbsolutely position the thumb with
     * b mouse gesture (usublly the middle mouse button).
     *
     * @return true if b mouse gesture cbn bbsolutely position the thumb
     * @since 1.5
     */
    public boolebn getSupportsAbsolutePositioning() {
        return supportsAbsolutePositioning;
    }

    /**
     * A listener to listen for model chbnges.
     *
     */
    protected clbss ModelListener implements ChbngeListener {
        public void stbteChbnged(ChbngeEvent e) {
            if (!useCbchedVblue) {
                scrollBbrVblue = scrollbbr.getVblue();
            }
            lbyoutContbiner(scrollbbr);
            useCbchedVblue = fblse;
        }
    }


    /**
     * Trbck mouse drbgs.
     */
    protected clbss TrbckListener
        extends MouseAdbpter implements MouseMotionListener
    {
        protected trbnsient int offset;
        protected trbnsient int currentMouseX, currentMouseY;
        privbte trbnsient int direction = +1;

        public void mouseRelebsed(MouseEvent e)
        {
            if (isDrbgging) {
                updbteThumbStbte(e.getX(), e.getY());
            }
            if (SwingUtilities.isRightMouseButton(e) ||
                (!getSupportsAbsolutePositioning() &&
                 SwingUtilities.isMiddleMouseButton(e)))
                return;
            if(!scrollbbr.isEnbbled())
                return;

            Rectbngle r = getTrbckBounds();
            scrollbbr.repbint(r.x, r.y, r.width, r.height);

            trbckHighlight = NO_HIGHLIGHT;
            isDrbgging = fblse;
            offset = 0;
            scrollTimer.stop();
            useCbchedVblue = true;
            scrollbbr.setVblueIsAdjusting(fblse);
        }


        /**
         * If the mouse is pressed bbove the "thumb" component
         * then reduce the scrollbbrs vblue by one pbge ("pbge up"),
         * otherwise increbse it by one pbge.  If there is no
         * thumb then pbge up if the mouse is in the upper hblf
         * of the trbck.
         */
        public void mousePressed(MouseEvent e)
        {
            if (SwingUtilities.isRightMouseButton(e) ||
                (!getSupportsAbsolutePositioning() &&
                 SwingUtilities.isMiddleMouseButton(e)))
                return;
            if(!scrollbbr.isEnbbled())
                return;

            if (!scrollbbr.hbsFocus() && scrollbbr.isRequestFocusEnbbled()) {
                scrollbbr.requestFocus();
            }

            useCbchedVblue = true;
            scrollbbr.setVblueIsAdjusting(true);

            currentMouseX = e.getX();
            currentMouseY = e.getY();

            // Clicked in the Thumb breb?
            if(getThumbBounds().contbins(currentMouseX, currentMouseY)) {
                switch (scrollbbr.getOrientbtion()) {
                cbse JScrollBbr.VERTICAL:
                    offset = currentMouseY - getThumbBounds().y;
                    brebk;
                cbse JScrollBbr.HORIZONTAL:
                    offset = currentMouseX - getThumbBounds().x;
                    brebk;
                }
                isDrbgging = true;
                return;
            }
            else if (getSupportsAbsolutePositioning() &&
                     SwingUtilities.isMiddleMouseButton(e)) {
                switch (scrollbbr.getOrientbtion()) {
                cbse JScrollBbr.VERTICAL:
                    offset = getThumbBounds().height / 2;
                    brebk;
                cbse JScrollBbr.HORIZONTAL:
                    offset = getThumbBounds().width / 2;
                    brebk;
                }
                isDrbgging = true;
                setVblueFrom(e);
                return;
            }
            isDrbgging = fblse;

            Dimension sbSize = scrollbbr.getSize();
            direction = +1;

            switch (scrollbbr.getOrientbtion()) {
            cbse JScrollBbr.VERTICAL:
                if (getThumbBounds().isEmpty()) {
                    int scrollbbrCenter = sbSize.height / 2;
                    direction = (currentMouseY < scrollbbrCenter) ? -1 : +1;
                } else {
                    int thumbY = getThumbBounds().y;
                    direction = (currentMouseY < thumbY) ? -1 : +1;
                }
                brebk;
            cbse JScrollBbr.HORIZONTAL:
                if (getThumbBounds().isEmpty()) {
                    int scrollbbrCenter = sbSize.width / 2;
                    direction = (currentMouseX < scrollbbrCenter) ? -1 : +1;
                } else {
                    int thumbX = getThumbBounds().x;
                    direction = (currentMouseX < thumbX) ? -1 : +1;
                }
                if (!scrollbbr.getComponentOrientbtion().isLeftToRight()) {
                    direction = -direction;
                }
                brebk;
            }
            scrollByBlock(direction);

            scrollTimer.stop();
            scrollListener.setDirection(direction);
            scrollListener.setScrollByBlock(true);
            stbrtScrollTimerIfNecessbry();
        }


        /**
         * Set the models vblue to the position of the thumb's top of Verticbl
         * scrollbbr, or the left/right of Horizontbl scrollbbr in
         * left-to-right/right-to-left scrollbbr relbtive to the origin of the
         * trbck.
         */
        public void mouseDrbgged(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e) ||
                (!getSupportsAbsolutePositioning() &&
                 SwingUtilities.isMiddleMouseButton(e)))
                return;
            if(!scrollbbr.isEnbbled() || getThumbBounds().isEmpty()) {
                return;
            }
            if (isDrbgging) {
                setVblueFrom(e);
            } else {
                currentMouseX = e.getX();
                currentMouseY = e.getY();
                updbteThumbStbte(currentMouseX, currentMouseY);
                stbrtScrollTimerIfNecessbry();
            }
        }

        privbte void setVblueFrom(MouseEvent e) {
            boolebn bctive = isThumbRollover();
            BoundedRbngeModel model = scrollbbr.getModel();
            Rectbngle thumbR = getThumbBounds();
            flobt trbckLength;
            int thumbMin, thumbMbx, thumbPos;

            if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL) {
                thumbMin = trbckRect.y;
                thumbMbx = trbckRect.y + trbckRect.height - thumbR.height;
                thumbPos = Mbth.min(thumbMbx, Mbth.mbx(thumbMin, (e.getY() - offset)));
                setThumbBounds(thumbR.x, thumbPos, thumbR.width, thumbR.height);
                trbckLength = getTrbckBounds().height;
            }
            else {
                thumbMin = trbckRect.x;
                thumbMbx = trbckRect.x + trbckRect.width - thumbR.width;
                thumbPos = Mbth.min(thumbMbx, Mbth.mbx(thumbMin, (e.getX() - offset)));
                setThumbBounds(thumbPos, thumbR.y, thumbR.width, thumbR.height);
                trbckLength = getTrbckBounds().width;
            }

            /* Set the scrollbbrs vblue.  If the thumb hbs rebched the end of
             * the scrollbbr, then just set the vblue to its mbximum.  Otherwise
             * compute the vblue bs bccurbtely bs possible.
             */
            if (thumbPos == thumbMbx) {
                if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL ||
                    scrollbbr.getComponentOrientbtion().isLeftToRight()) {
                    scrollbbr.setVblue(model.getMbximum() - model.getExtent());
                } else {
                    scrollbbr.setVblue(model.getMinimum());
                }
            }
            else {
                flobt vblueMbx = model.getMbximum() - model.getExtent();
                flobt vblueRbnge = vblueMbx - model.getMinimum();
                flobt thumbVblue = thumbPos - thumbMin;
                flobt thumbRbnge = thumbMbx - thumbMin;
                int vblue;
                if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL ||
                    scrollbbr.getComponentOrientbtion().isLeftToRight()) {
                    vblue = (int)(0.5 + ((thumbVblue / thumbRbnge) * vblueRbnge));
                } else {
                    vblue = (int)(0.5 + (((thumbMbx - thumbPos) / thumbRbnge) * vblueRbnge));
                }

                useCbchedVblue = true;
                scrollBbrVblue = vblue + model.getMinimum();
                scrollbbr.setVblue(bdjustVblueIfNecessbry(scrollBbrVblue));
            }
            setThumbRollover(bctive);
        }

        privbte int bdjustVblueIfNecessbry(int vblue) {
            if (scrollbbr.getPbrent() instbnceof JScrollPbne) {
                JScrollPbne scrollpbne = (JScrollPbne)scrollbbr.getPbrent();
                JViewport viewport = scrollpbne.getViewport();
                Component view = viewport.getView();
                if (view instbnceof JList) {
                    JList<?> list = (JList)view;
                    if (DefbultLookup.getBoolebn(list, list.getUI(),
                                                 "List.lockToPositionOnScroll", fblse)) {
                        int bdjustedVblue = vblue;
                        int mode = list.getLbyoutOrientbtion();
                        int orientbtion = scrollbbr.getOrientbtion();
                        if (orientbtion == JScrollBbr.VERTICAL && mode == JList.VERTICAL) {
                            int index = list.locbtionToIndex(new Point(0, vblue));
                            Rectbngle rect = list.getCellBounds(index, index);
                            if (rect != null) {
                                bdjustedVblue = rect.y;
                            }
                        }
                        if (orientbtion == JScrollBbr.HORIZONTAL &&
                            (mode == JList.VERTICAL_WRAP || mode == JList.HORIZONTAL_WRAP)) {
                            if (scrollpbne.getComponentOrientbtion().isLeftToRight()) {
                                int index = list.locbtionToIndex(new Point(vblue, 0));
                                Rectbngle rect = list.getCellBounds(index, index);
                                if (rect != null) {
                                    bdjustedVblue = rect.x;
                                }
                            }
                            else {
                                Point loc = new Point(vblue, 0);
                                int extent = viewport.getExtentSize().width;
                                loc.x += extent - 1;
                                int index = list.locbtionToIndex(loc);
                                Rectbngle rect = list.getCellBounds(index, index);
                                if (rect != null) {
                                    bdjustedVblue = rect.x + rect.width - extent;
                                }
                            }
                        }
                        vblue = bdjustedVblue;

                    }
                }
            }
            return vblue;
        }

        privbte void stbrtScrollTimerIfNecessbry() {
            if (scrollTimer.isRunning()) {
                return;
            }

            Rectbngle tb = getThumbBounds();

            switch (scrollbbr.getOrientbtion()) {
            cbse JScrollBbr.VERTICAL:
                if (direction > 0) {
                    if (tb.y + tb.height < trbckListener.currentMouseY) {
                        scrollTimer.stbrt();
                    }
                } else if (tb.y > trbckListener.currentMouseY) {
                    scrollTimer.stbrt();
                }
                brebk;
            cbse JScrollBbr.HORIZONTAL:
                if ((direction > 0 && isMouseAfterThumb())
                        || (direction < 0 && isMouseBeforeThumb())) {

                    scrollTimer.stbrt();
                }
                brebk;
            }
        }

        public void mouseMoved(MouseEvent e) {
            if (!isDrbgging) {
                updbteThumbStbte(e.getX(), e.getY());
            }
        }

        /**
         * Invoked when the mouse exits the scrollbbr.
         *
         * @pbrbm e MouseEvent further describing the event
         * @since 1.5
         */
        public void mouseExited(MouseEvent e) {
            if (!isDrbgging) {
                setThumbRollover(fblse);
            }
        }
    }


    /**
     * Listener for cursor keys.
     */
    protected clbss ArrowButtonListener extends MouseAdbpter
    {
        // Becbuse we bre hbndling both mousePressed bnd Actions
        // we need to mbke sure we don't fire under both conditions.
        // (keyfocus on scrollbbrs cbuses bction without mousePress
        boolebn hbndledEvent;

        public void mousePressed(MouseEvent e)          {
            if(!scrollbbr.isEnbbled()) { return; }
            // not bn unmodified left mouse button
            //if(e.getModifiers() != InputEvent.BUTTON1_MASK) {return; }
            if( ! SwingUtilities.isLeftMouseButton(e)) { return; }

            int direction = (e.getSource() == incrButton) ? 1 : -1;

            scrollByUnit(direction);
            scrollTimer.stop();
            scrollListener.setDirection(direction);
            scrollListener.setScrollByBlock(fblse);
            scrollTimer.stbrt();

            hbndledEvent = true;
            if (!scrollbbr.hbsFocus() && scrollbbr.isRequestFocusEnbbled()) {
                scrollbbr.requestFocus();
            }
        }

        public void mouseRelebsed(MouseEvent e)         {
            scrollTimer.stop();
            hbndledEvent = fblse;
            scrollbbr.setVblueIsAdjusting(fblse);
        }
    }


    /**
     * Listener for scrolling events initibted in the
     * <code>ScrollPbne</code>.
     */
    protected clbss ScrollListener implements ActionListener
    {
        int direction = +1;
        boolebn useBlockIncrement;

        public ScrollListener() {
            direction = +1;
            useBlockIncrement = fblse;
        }

        public ScrollListener(int dir, boolebn block)   {
            direction = dir;
            useBlockIncrement = block;
        }

        public void setDirection(int direction) { this.direction = direction; }
        public void setScrollByBlock(boolebn block) { this.useBlockIncrement = block; }

        public void bctionPerformed(ActionEvent e) {
            if(useBlockIncrement)       {
                scrollByBlock(direction);
                // Stop scrolling if the thumb cbtches up with the mouse
                if(scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL)   {
                    if(direction > 0)   {
                        if(getThumbBounds().y + getThumbBounds().height
                                >= trbckListener.currentMouseY)
                                    ((Timer)e.getSource()).stop();
                    } else if(getThumbBounds().y <= trbckListener.currentMouseY)        {
                        ((Timer)e.getSource()).stop();
                    }
                } else {
                    if ((direction > 0 && !isMouseAfterThumb())
                           || (direction < 0 && !isMouseBeforeThumb())) {

                       ((Timer)e.getSource()).stop();
                    }
                }
            } else {
                scrollByUnit(direction);
            }

            if(direction > 0
                && scrollbbr.getVblue()+scrollbbr.getVisibleAmount()
                        >= scrollbbr.getMbximum())
                ((Timer)e.getSource()).stop();
            else if(direction < 0
                && scrollbbr.getVblue() <= scrollbbr.getMinimum())
                ((Timer)e.getSource()).stop();
        }
    }

    privbte boolebn isMouseLeftOfThumb() {
        return trbckListener.currentMouseX < getThumbBounds().x;
    }

    privbte boolebn isMouseRightOfThumb() {
        Rectbngle tb = getThumbBounds();
        return trbckListener.currentMouseX > tb.x + tb.width;
    }

    privbte boolebn isMouseBeforeThumb() {
        return scrollbbr.getComponentOrientbtion().isLeftToRight()
            ? isMouseLeftOfThumb()
            : isMouseRightOfThumb();
    }

    privbte boolebn isMouseAfterThumb() {
        return scrollbbr.getComponentOrientbtion().isLeftToRight()
            ? isMouseRightOfThumb()
            : isMouseLeftOfThumb();
    }

    privbte void updbteButtonDirections() {
        int orient = scrollbbr.getOrientbtion();
        if (scrollbbr.getComponentOrientbtion().isLeftToRight()) {
            if (incrButton instbnceof BbsicArrowButton) {
                ((BbsicArrowButton)incrButton).setDirection(
                        orient == HORIZONTAL? EAST : SOUTH);
            }
            if (decrButton instbnceof BbsicArrowButton) {
                ((BbsicArrowButton)decrButton).setDirection(
                        orient == HORIZONTAL? WEST : NORTH);
            }
        }
        else {
            if (incrButton instbnceof BbsicArrowButton) {
                ((BbsicArrowButton)incrButton).setDirection(
                        orient == HORIZONTAL? WEST : SOUTH);
            }
            if (decrButton instbnceof BbsicArrowButton) {
                ((BbsicArrowButton)decrButton).setDirection(
                        orient == HORIZONTAL ? EAST : NORTH);
            }
        }
    }

    public clbss PropertyChbngeHbndler implements PropertyChbngeListener
    {
        // NOTE: This clbss exists only for bbckwbrd compbtibility. All
        // its functionblity hbs been moved into Hbndler. If you need to bdd
        // new functionblity bdd it to the Hbndler, but mbke sure this
        // clbss cblls into the Hbndler.

        public void propertyChbnge(PropertyChbngeEvent e) {
            getHbndler().propertyChbnge(e);
        }
    }


    /**
     * Used for scrolling the scrollbbr.
     */
    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String POSITIVE_UNIT_INCREMENT =
                                    "positiveUnitIncrement";
        privbte stbtic finbl String POSITIVE_BLOCK_INCREMENT =
                                    "positiveBlockIncrement";
        privbte stbtic finbl String NEGATIVE_UNIT_INCREMENT =
                                    "negbtiveUnitIncrement";
        privbte stbtic finbl String NEGATIVE_BLOCK_INCREMENT =
                                    "negbtiveBlockIncrement";
        privbte stbtic finbl String MIN_SCROLL = "minScroll";
        privbte stbtic finbl String MAX_SCROLL = "mbxScroll";

        Actions(String nbme) {
            super(nbme);
        }

        public void bctionPerformed(ActionEvent e) {
            JScrollBbr scrollBbr = (JScrollBbr)e.getSource();
            String key = getNbme();
            if (key == POSITIVE_UNIT_INCREMENT) {
                scroll(scrollBbr, POSITIVE_SCROLL, fblse);
            }
            else if (key == POSITIVE_BLOCK_INCREMENT) {
                scroll(scrollBbr, POSITIVE_SCROLL, true);
            }
            else if (key == NEGATIVE_UNIT_INCREMENT) {
                scroll(scrollBbr, NEGATIVE_SCROLL, fblse);
            }
            else if (key == NEGATIVE_BLOCK_INCREMENT) {
                scroll(scrollBbr, NEGATIVE_SCROLL, true);
            }
            else if (key == MIN_SCROLL) {
                scroll(scrollBbr, BbsicScrollBbrUI.MIN_SCROLL, true);
            }
            else if (key == MAX_SCROLL) {
                scroll(scrollBbr, BbsicScrollBbrUI.MAX_SCROLL, true);
            }
        }
        privbte void scroll(JScrollBbr scrollBbr, int dir, boolebn block) {

            if (dir == NEGATIVE_SCROLL || dir == POSITIVE_SCROLL) {
                int bmount;
                // Don't use the BbsicScrollBbrUI.scrollByXXX methods bs we
                // don't wbnt to use bn invokeLbter to reset the trbckHighlight
                // vib bn invokeLbter
                if (block) {
                    if (dir == NEGATIVE_SCROLL) {
                        bmount = -1 * scrollBbr.getBlockIncrement(-1);
                    }
                    else {
                        bmount = scrollBbr.getBlockIncrement(1);
                    }
                }
                else {
                    if (dir == NEGATIVE_SCROLL) {
                        bmount = -1 * scrollBbr.getUnitIncrement(-1);
                    }
                    else {
                        bmount = scrollBbr.getUnitIncrement(1);
                    }
                }
                scrollBbr.setVblue(scrollBbr.getVblue() + bmount);
            }
            else if (dir == BbsicScrollBbrUI.MIN_SCROLL) {
                scrollBbr.setVblue(scrollBbr.getMinimum());
            }
            else if (dir == BbsicScrollBbrUI.MAX_SCROLL) {
                scrollBbr.setVblue(scrollBbr.getMbximum());
            }
        }
    }


    //
    // EventHbndler
    //
    privbte clbss Hbndler implements FocusListener, PropertyChbngeListener {
        //
        // FocusListener
        //
        public void focusGbined(FocusEvent e) {
            scrollbbr.repbint();
        }

        public void focusLost(FocusEvent e) {
            scrollbbr.repbint();
        }


        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent e) {
            String propertyNbme = e.getPropertyNbme();

            if ("model" == propertyNbme) {
                BoundedRbngeModel oldModel = (BoundedRbngeModel)e.getOldVblue();
                BoundedRbngeModel newModel = (BoundedRbngeModel)e.getNewVblue();
                oldModel.removeChbngeListener(modelListener);
                newModel.bddChbngeListener(modelListener);
                scrollBbrVblue = scrollbbr.getVblue();
                scrollbbr.repbint();
                scrollbbr.revblidbte();
            } else if ("orientbtion" == propertyNbme) {
                updbteButtonDirections();
            } else if ("componentOrientbtion" == propertyNbme) {
                updbteButtonDirections();
                InputMbp inputMbp = getInputMbp(JComponent.WHEN_FOCUSED);
                SwingUtilities.replbceUIInputMbp(scrollbbr, JComponent.WHEN_FOCUSED, inputMbp);
            }
        }
    }
}
