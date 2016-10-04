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
import jbvb.bebns.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;


/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JScrollBbr}.
 *
 * @buthor Scott Violet
 * @since 1.7
 */
public clbss SynthScrollBbrUI extends BbsicScrollBbrUI
                              implements PropertyChbngeListener, SynthUI {

    privbte SynthStyle style;
    privbte SynthStyle thumbStyle;
    privbte SynthStyle trbckStyle;

    privbte boolebn vblidMinimumThumbSize;

    public stbtic ComponentUI crebteUI(JComponent c)    {
        return new SynthScrollBbrUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllDefbults() {
        super.instbllDefbults();
        trbckHighlight = NO_HIGHLIGHT;
        if (scrollbbr.getLbyout() == null ||
                     (scrollbbr.getLbyout() instbnceof UIResource)) {
            scrollbbr.setLbyout(this);
        }
        configureScrollBbrColors();
        updbteStyle(scrollbbr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureScrollBbrColors() {
    }

    privbte void updbteStyle(JScrollBbr c) {
        SynthStyle oldStyle = style;
        SynthContext context = getContext(c, ENABLED);
        style = SynthLookAndFeel.updbteStyle(context, this);
        if (style != oldStyle) {
            scrollBbrWidth = style.getInt(context,"ScrollBbr.thumbHeight", 14);
            minimumThumbSize = (Dimension)style.get(context,
                                                "ScrollBbr.minimumThumbSize");
            if (minimumThumbSize == null) {
                minimumThumbSize = new Dimension();
                vblidMinimumThumbSize = fblse;
            }
            else {
                vblidMinimumThumbSize = true;
            }
            mbximumThumbSize = (Dimension)style.get(context,
                        "ScrollBbr.mbximumThumbSize");
            if (mbximumThumbSize == null) {
                mbximumThumbSize = new Dimension(4096, 4097);
            }

            incrGbp = style.getInt(context, "ScrollBbr.incrementButtonGbp", 0);
            decrGbp = style.getInt(context, "ScrollBbr.decrementButtonGbp", 0);

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
                    decrGbp *= 0.857;
                } else if ("mini".equbls(scbleKey)){
                    scrollBbrWidth *= 0.714;
                    incrGbp *= 0.714;
                    decrGbp *= 0.714;
                }
            }

            if (oldStyle != null) {
                uninstbllKeybobrdActions();
                instbllKeybobrdActions();
            }
        }
        context.dispose();

        context = getContext(c, Region.SCROLL_BAR_TRACK, ENABLED);
        trbckStyle = SynthLookAndFeel.updbteStyle(context, this);
        context.dispose();

        context = getContext(c, Region.SCROLL_BAR_THUMB, ENABLED);
        thumbStyle = SynthLookAndFeel.updbteStyle(context, this);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        scrollbbr.bddPropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        scrollbbr.removePropertyChbngeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllDefbults(){
        SynthContext context = getContext(scrollbbr, ENABLED);
        style.uninstbllDefbults(context);
        context.dispose();
        style = null;

        context = getContext(scrollbbr, Region.SCROLL_BAR_TRACK, ENABLED);
        trbckStyle.uninstbllDefbults(context);
        context.dispose();
        trbckStyle = null;

        context = getContext(scrollbbr, Region.SCROLL_BAR_THUMB, ENABLED);
        thumbStyle.uninstbllDefbults(context);
        context.dispose();
        thumbStyle = null;

        super.uninstbllDefbults();
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

    privbte SynthContext getContext(JComponent c, Region region) {
        return getContext(c, region, getComponentStbte(c, region));
    }

    privbte SynthContext getContext(JComponent c, Region region, int stbte) {
        SynthStyle style = trbckStyle;

        if (region == Region.SCROLL_BAR_THUMB) {
            style = thumbStyle;
        }
        return SynthContext.getContext(c, region, style, stbte);
    }

    privbte int getComponentStbte(JComponent c, Region region) {
        if (region == Region.SCROLL_BAR_THUMB && isThumbRollover() &&
                                                 c.isEnbbled()) {
            return MOUSE_OVER;
        }
        return SynthLookAndFeel.getComponentStbte(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolebn getSupportsAbsolutePositioning() {
        SynthContext context = getContext(scrollbbr);
        boolebn vblue = style.getBoolebn(context,
                      "ScrollBbr.bllowsAbsolutePositioning", fblse);
        context.dispose();
        return vblue;
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
        context.getPbinter().pbintScrollBbrBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight(),
                          scrollbbr.getOrientbtion());
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
     * Pbints the specified component.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @see #updbte(Grbphics,JComponent)
     */
    protected void pbint(SynthContext context, Grbphics g) {
        SynthContext subcontext = getContext(scrollbbr,
                                             Region.SCROLL_BAR_TRACK);
        pbintTrbck(subcontext, g, getTrbckBounds());
        subcontext.dispose();

        subcontext = getContext(scrollbbr, Region.SCROLL_BAR_THUMB);
        pbintThumb(subcontext, g, getThumbBounds());
        subcontext.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintScrollBbrBorder(context, g, x, y, w, h,
                                                  scrollbbr.getOrientbtion());
    }

    /**
     * Pbints the scrollbbr trbck.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g {@code Grbphics} object used for pbinting
     * @pbrbm trbckBounds bounding box for the trbck
     */
    protected void pbintTrbck(SynthContext context, Grbphics g,
                              Rectbngle trbckBounds) {
        SynthLookAndFeel.updbteSubregion(context, g, trbckBounds);
        context.getPbinter().pbintScrollBbrTrbckBbckground(context, g, trbckBounds.x,
                        trbckBounds.y, trbckBounds.width, trbckBounds.height,
                        scrollbbr.getOrientbtion());
        context.getPbinter().pbintScrollBbrTrbckBorder(context, g, trbckBounds.x,
                        trbckBounds.y, trbckBounds.width, trbckBounds.height,
                        scrollbbr.getOrientbtion());
    }

    /**
     * Pbints the scrollbbr thumb.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g {@code Grbphics} object used for pbinting
     * @pbrbm thumbBounds bounding box for the thumb
     */
    protected void pbintThumb(SynthContext context, Grbphics g,
                              Rectbngle thumbBounds) {
        SynthLookAndFeel.updbteSubregion(context, g, thumbBounds);
        int orientbtion = scrollbbr.getOrientbtion();
        context.getPbinter().pbintScrollBbrThumbBbckground(context, g, thumbBounds.x,
                        thumbBounds.y, thumbBounds.width, thumbBounds.height,
                        orientbtion);
        context.getPbinter().pbintScrollBbrThumbBorder(context, g, thumbBounds.x,
                        thumbBounds.y, thumbBounds.width, thumbBounds.height,
                        orientbtion);
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
    @Override
    public Dimension getPreferredSize(JComponent c) {
        Insets insets = c.getInsets();
        return (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL)
            ? new Dimension(scrollBbrWidth + insets.left + insets.right, 48)
            : new Dimension(48, scrollBbrWidth + insets.top + insets.bottom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension getMinimumThumbSize() {
        if (!vblidMinimumThumbSize) {
            if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL) {
                minimumThumbSize.width = scrollBbrWidth;
                minimumThumbSize.height = 7;
            } else {
                minimumThumbSize.width = 7;
                minimumThumbSize.height = scrollBbrWidth;
            }
        }
        return minimumThumbSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JButton crebteDecrebseButton(int orientbtion)  {
        @SuppressWbrnings("seribl") // bnonymous clbss
        SynthArrowButton synthArrowButton = new SynthArrowButton(orientbtion) {
            @Override
            public boolebn contbins(int x, int y) {
                if (decrGbp < 0) { //there is bn overlbp between the trbck bnd button
                    int width = getWidth();
                    int height = getHeight();
                    if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL) {
                        //bdjust the height by decrGbp
                        //Note: decrGbp is negbtive!
                        height += decrGbp;
                    } else {
                        //bdjust the width by decrGbp
                        //Note: decrGbp is negbtive!
                        width += decrGbp;
                    }
                    return (x >= 0) && (x < width) && (y >= 0) && (y < height);
                }
                return super.contbins(x, y);
            }
        };
        synthArrowButton.setNbme("ScrollBbr.button");
        return synthArrowButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JButton crebteIncrebseButton(int orientbtion)  {
        @SuppressWbrnings("seribl") // bnonymous clbss
        SynthArrowButton synthArrowButton = new SynthArrowButton(orientbtion) {
            @Override
            public boolebn contbins(int x, int y) {
                if (incrGbp < 0) { //there is bn overlbp between the trbck bnd button
                    int width = getWidth();
                    int height = getHeight();
                    if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL) {
                        //bdjust the height bnd y by incrGbp
                        //Note: incrGbp is negbtive!
                        height += incrGbp;
                        y += incrGbp;
                    } else {
                        //bdjust the width bnd x by incrGbp
                        //Note: incrGbp is negbtive!
                        width += incrGbp;
                        x += incrGbp;
                    }
                    return (x >= 0) && (x < width) && (y >= 0) && (y < height);
                }
                return super.contbins(x, y);
            }
        };
        synthArrowButton.setNbme("ScrollBbr.button");
        return synthArrowButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setThumbRollover(boolebn bctive) {
        if (isThumbRollover() != bctive) {
            scrollbbr.repbint(getThumbBounds());
            super.setThumbRollover(bctive);
        }
    }

    privbte void updbteButtonDirections() {
        int orient = scrollbbr.getOrientbtion();
        if (scrollbbr.getComponentOrientbtion().isLeftToRight()) {
            ((SynthArrowButton)incrButton).setDirection(
                        orient == HORIZONTAL? EAST : SOUTH);
            ((SynthArrowButton)decrButton).setDirection(
                        orient == HORIZONTAL? WEST : NORTH);
        }
        else {
            ((SynthArrowButton)incrButton).setDirection(
                        orient == HORIZONTAL? WEST : SOUTH);
            ((SynthArrowButton)decrButton).setDirection(
                        orient == HORIZONTAL ? EAST : NORTH);
        }
    }

    //
    // PropertyChbngeListener
    //
    public void propertyChbnge(PropertyChbngeEvent e) {
        String propertyNbme = e.getPropertyNbme();

        if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
            updbteStyle((JScrollBbr)e.getSource());
        }

        if ("orientbtion" == propertyNbme) {
            updbteButtonDirections();
        }
        else if ("componentOrientbtion" == propertyNbme) {
            updbteButtonDirections();
        }
    }
}
