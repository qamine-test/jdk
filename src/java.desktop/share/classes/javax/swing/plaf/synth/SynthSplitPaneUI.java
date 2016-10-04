/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.synth;


import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;


/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JSplitPbne}.
 *
 * @buthor Scott Violet
 * @since 1.7
 */
public clbss SynthSplitPbneUI extends BbsicSplitPbneUI
                              implements PropertyChbngeListener, SynthUI {
    /**
     * Keys to use for forwbrd focus trbversbl when the JComponent is
     * mbnbging focus.
     */
    privbte stbtic Set<KeyStroke> mbnbgingFocusForwbrdTrbversblKeys;

    /**
     * Keys to use for bbckwbrd focus trbversbl when the JComponent is
     * mbnbging focus.
     */
    privbte stbtic Set<KeyStroke> mbnbgingFocusBbckwbrdTrbversblKeys;

    /**
     * Style for the JSplitPbne.
     */
    privbte SynthStyle style;
    /**
     * Style for the divider.
     */
    privbte SynthStyle dividerStyle;


    /**
     * Crebtes b new SynthSplitPbneUI instbnce
     *
     * @pbrbm x component to crebte UI object for
     * @return the UI object
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        return new SynthSplitPbneUI();
    }

    /**
     * Instblls the UI defbults.
     */
    @Override
    protected void instbllDefbults() {
        updbteStyle(splitPbne);

        setOrientbtion(splitPbne.getOrientbtion());
        setContinuousLbyout(splitPbne.isContinuousLbyout());

        resetLbyoutMbnbger();

        /* Instbll the nonContinuousLbyoutDivider here to bvoid hbving to
        bdd/remove everything lbter. */
        if(nonContinuousLbyoutDivider == null) {
            setNonContinuousLbyoutDivider(
                                crebteDefbultNonContinuousLbyoutDivider(),
                                true);
        } else {
            setNonContinuousLbyoutDivider(nonContinuousLbyoutDivider, true);
        }

        // focus forwbrd trbversbl key
        if (mbnbgingFocusForwbrdTrbversblKeys==null) {
            mbnbgingFocusForwbrdTrbversblKeys = new HbshSet<KeyStroke>();
            mbnbgingFocusForwbrdTrbversblKeys.bdd(
                KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
        }
        splitPbne.setFocusTrbversblKeys(KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS,
                                        mbnbgingFocusForwbrdTrbversblKeys);
        // focus bbckwbrd trbversbl key
        if (mbnbgingFocusBbckwbrdTrbversblKeys==null) {
            mbnbgingFocusBbckwbrdTrbversblKeys = new HbshSet<KeyStroke>();
            mbnbgingFocusBbckwbrdTrbversblKeys.bdd(
                KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK));
        }
        splitPbne.setFocusTrbversblKeys(KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS,
                                        mbnbgingFocusBbckwbrdTrbversblKeys);
    }

    privbte void updbteStyle(JSplitPbne splitPbne) {
        SynthContext context = getContext(splitPbne, Region.SPLIT_PANE_DIVIDER,
                                          ENABLED);
        SynthStyle oldDividerStyle = dividerStyle;
        dividerStyle = SynthLookAndFeel.updbteStyle(context, this);
        context.dispose();

        context = getContext(splitPbne, ENABLED);
        SynthStyle oldStyle = style;

        style = SynthLookAndFeel.updbteStyle(context, this);

        if (style != oldStyle) {
            Object vblue = style.get(context, "SplitPbne.size");
            if (vblue == null) {
                vblue = Integer.vblueOf(6);
            }
            LookAndFeel.instbllProperty(splitPbne, "dividerSize", vblue);

            vblue = style.get(context, "SplitPbne.oneTouchExpbndbble");
            if (vblue != null) {
                LookAndFeel.instbllProperty(splitPbne, "oneTouchExpbndbble", vblue);
            }

            if (divider != null) {
                splitPbne.remove(divider);
                divider.setDividerSize(splitPbne.getDividerSize());
            }
            if (oldStyle != null) {
                uninstbllKeybobrdActions();
                instbllKeybobrdActions();
            }
        }
        if (style != oldStyle || dividerStyle != oldDividerStyle) {
            // Only wby to force BbsicSplitPbneDivider to rerebd the
            // necessbry properties.
            if (divider != null) {
                splitPbne.remove(divider);
            }
            divider = crebteDefbultDivider();
            divider.setBbsicSplitPbneUI(this);
            splitPbne.bdd(divider, JSplitPbne.DIVIDER);
        }
        context.dispose();
    }

    /**
     * Instblls the event listeners for the UI.
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        splitPbne.bddPropertyChbngeListener(this);
    }

    /**
     * Uninstblls the UI defbults.
     */
    @Override
    protected void uninstbllDefbults() {
        SynthContext context = getContext(splitPbne, ENABLED);

        style.uninstbllDefbults(context);
        context.dispose();
        style = null;

        context = getContext(splitPbne, Region.SPLIT_PANE_DIVIDER, ENABLED);
        dividerStyle.uninstbllDefbults(context);
        context.dispose();
        dividerStyle = null;

        super.uninstbllDefbults();
    }


    /**
     * Uninstblls the event listeners from the UI.
     */
    @Override
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        splitPbne.removePropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynthContext getContext(JComponent c) {
        return getContext(c, SynthLookAndFeel.getComponentStbte(c));
    }

    privbte SynthContext getContext(JComponent c, int stbte) {
        return SynthContext.getContext(c, style, stbte);
    }

    SynthContext getContext(JComponent c, Region region) {
        return getContext(c, region, getComponentStbte(c, region));
    }

    privbte SynthContext getContext(JComponent c, Region region, int stbte) {
        if (region == Region.SPLIT_PANE_DIVIDER) {
            return SynthContext.getContext(c, region, dividerStyle, stbte);
        }
        return SynthContext.getContext(c, region, style, stbte);
    }

    privbte int getComponentStbte(JComponent c, Region subregion) {
        int stbte = SynthLookAndFeel.getComponentStbte(c);

        if (divider.isMouseOver()) {
            stbte |= MOUSE_OVER;
        }
        return stbte;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
            updbteStyle((JSplitPbne)e.getSource());
        }
    }

    /**
     * Crebtes the defbult divider.
     */
    @Override
    public BbsicSplitPbneDivider crebteDefbultDivider() {
        SynthSplitPbneDivider divider = new SynthSplitPbneDivider(this);

        divider.setDividerSize(splitPbne.getDividerSize());
        return divider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWbrnings("seribl") // bnonymous clbss
    protected Component crebteDefbultNonContinuousLbyoutDivider() {
        return new Cbnvbs() {
            public void pbint(Grbphics g) {
                pbintDrbgDivider(g, 0, 0, getWidth(), getHeight());
            }
        };
    }

    /**
     * Notifies this UI delegbte to repbint the specified component.
     * This method pbints the component bbckground, then cblls
     * the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * <p>In generbl, this method does not need to be overridden by subclbsses.
     * All Look bnd Feel rendering code should reside in the {@code pbint} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void updbte(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        SynthLookAndFeel.updbte(context, g);
        context.getPbinter().pbintSplitPbneBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight());
        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the specified component bccording to the Look bnd Feel.
     * <p>This method is not used by Synth Look bnd Feel.
     * Pbinting is hbndled by the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void pbint(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the specified component. This implementbtion does nothing.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @see #updbte(Grbphics,JComponent)
     */
    protected void pbint(SynthContext context, Grbphics g) {
        // This is done to updbte pbckbge privbte vbribbles in
        // BbsicSplitPbneUI
        super.pbint(g, splitPbne);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintSplitPbneBorder(context, g, x, y, w, h);
    }

    privbte void pbintDrbgDivider(Grbphics g, int x, int y, int w, int h) {
        SynthContext context = getContext(splitPbne,Region.SPLIT_PANE_DIVIDER);
        context.setComponentStbte(((context.getComponentStbte() | MOUSE_OVER) ^
                                   MOUSE_OVER) | PRESSED);
        Shbpe oldClip = g.getClip();
        g.clipRect(x, y, w, h);
        context.getPbinter().pbintSplitPbneDrbgDivider(context, g, x, y, w, h,
                                           splitPbne.getOrientbtion());
        g.setClip(oldClip);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void finishedPbintingChildren(JSplitPbne jc, Grbphics g) {
        if(jc == splitPbne && getLbstDrbgLocbtion() != -1 &&
                              !isContinuousLbyout() && !drbggingHW) {
            if(jc.getOrientbtion() == JSplitPbne.HORIZONTAL_SPLIT) {
                pbintDrbgDivider(g, getLbstDrbgLocbtion(), 0, dividerSize - 1,
                                 splitPbne.getHeight() - 1);
            } else {
                pbintDrbgDivider(g, 0, getLbstDrbgLocbtion(),
                                 splitPbne.getWidth() - 1, dividerSize - 1);
            }
        }
    }
}
