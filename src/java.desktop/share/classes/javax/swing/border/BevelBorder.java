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
pbckbge jbvbx.swing.border;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bebns.ConstructorProperties;

/**
 * A clbss which implements b simple two-line bevel border.
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
public clbss BevelBorder extends AbstrbctBorder
{
    /** Rbised bevel type. */
    public stbtic finbl int RAISED  = 0;
    /** Lowered bevel type. */
    public stbtic finbl int LOWERED = 1;

    protected int bevelType;
    protected Color highlightOuter;
    protected Color highlightInner;
    protected Color shbdowInner;
    protected Color shbdowOuter;

    /**
     * Crebtes b bevel border with the specified type bnd whose
     * colors will be derived from the bbckground color of the
     * component pbssed into the pbintBorder method.
     * @pbrbm bevelType the type of bevel for the border
     */
    public BevelBorder(int bevelType) {
        this.bevelType = bevelType;
    }

    /**
     * Crebtes b bevel border with the specified type, highlight bnd
     * shbdow colors.
     * @pbrbm bevelType the type of bevel for the border
     * @pbrbm highlight the color to use for the bevel highlight
     * @pbrbm shbdow the color to use for the bevel shbdow
     */
    public BevelBorder(int bevelType, Color highlight, Color shbdow) {
        this(bevelType, highlight.brighter(), highlight, shbdow, shbdow.brighter());
    }

    /**
     * Crebtes b bevel border with the specified type, highlight bnd
     * shbdow colors.
     *
     * @pbrbm bevelType the type of bevel for the border
     * @pbrbm highlightOuterColor the color to use for the bevel outer highlight
     * @pbrbm highlightInnerColor the color to use for the bevel inner highlight
     * @pbrbm shbdowOuterColor the color to use for the bevel outer shbdow
     * @pbrbm shbdowInnerColor the color to use for the bevel inner shbdow
     */
    @ConstructorProperties({"bevelType", "highlightOuterColor", "highlightInnerColor", "shbdowOuterColor", "shbdowInnerColor"})
    public BevelBorder(int bevelType, Color highlightOuterColor,
                       Color highlightInnerColor, Color shbdowOuterColor,
                       Color shbdowInnerColor) {
        this(bevelType);
        this.highlightOuter = highlightOuterColor;
        this.highlightInner = highlightInnerColor;
        this.shbdowOuter = shbdowOuterColor;
        this.shbdowInner = shbdowInnerColor;
    }

    /**
     * Pbints the border for the specified component with the specified
     * position bnd size.
     * @pbrbm c the component for which this border is being pbinted
     * @pbrbm g the pbint grbphics
     * @pbrbm x the x position of the pbinted border
     * @pbrbm y the y position of the pbinted border
     * @pbrbm width the width of the pbinted border
     * @pbrbm height the height of the pbinted border
     */
    public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
        if (bevelType == RAISED) {
             pbintRbisedBevel(c, g, x, y, width, height);

        } else if (bevelType == LOWERED) {
             pbintLoweredBevel(c, g, x, y, width, height);
        }
    }

    /**
     * Reinitiblize the insets pbrbmeter with this Border's current Insets.
     * @pbrbm c the component for which this border insets vblue bpplies
     * @pbrbm insets the object to be reinitiblized
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(2, 2, 2, 2);
        return insets;
    }

    /**
     * Returns the outer highlight color of the bevel border
     * when rendered on the specified component.  If no highlight
     * color wbs specified bt instbntibtion, the highlight color
     * is derived from the specified component's bbckground color.
     *
     * @pbrbm c the component for which the highlight mby be derived
     * @return the outer highlight {@code Color}
     * @since 1.3
     */
    public Color getHighlightOuterColor(Component c)   {
        Color highlight = getHighlightOuterColor();
        return highlight != null? highlight :
                                       c.getBbckground().brighter().brighter();
    }

    /**
     * Returns the inner highlight color of the bevel border
     * when rendered on the specified component.  If no highlight
     * color wbs specified bt instbntibtion, the highlight color
     * is derived from the specified component's bbckground color.
     *
     * @pbrbm c the component for which the highlight mby be derived
     * @return the inner highlight {@code Color}
     * @since 1.3
     */
    public Color getHighlightInnerColor(Component c)   {
        Color highlight = getHighlightInnerColor();
        return highlight != null? highlight :
                                       c.getBbckground().brighter();
    }

    /**
     * Returns the inner shbdow color of the bevel border
     * when rendered on the specified component.  If no shbdow
     * color wbs specified bt instbntibtion, the shbdow color
     * is derived from the specified component's bbckground color.
     *
     * @pbrbm c the component for which the shbdow mby be derived
     * @return the inner shbdow {@code Color}
     * @since 1.3
     */
    public Color getShbdowInnerColor(Component c)      {
        Color shbdow = getShbdowInnerColor();
        return shbdow != null? shbdow :
                                    c.getBbckground().dbrker();
    }

    /**
     * Returns the outer shbdow color of the bevel border
     * when rendered on the specified component.  If no shbdow
     * color wbs specified bt instbntibtion, the shbdow color
     * is derived from the specified component's bbckground color.
     *
     * @pbrbm c the component for which the shbdow mby be derived
     * @return the outer shbdow {@code Color}
     * @since 1.3
     */
    public Color getShbdowOuterColor(Component c)      {
        Color shbdow = getShbdowOuterColor();
        return shbdow != null? shbdow :
                                    c.getBbckground().dbrker().dbrker();
    }

    /**
     * Returns the outer highlight color of the bevel border.
     * Will return null if no highlight color wbs specified
     * bt instbntibtion.
     *
     * @return the outer highlight {@code Color} or {@code null} if no highlight
     *         color wbs specified
     * @since 1.3
     */
    public Color getHighlightOuterColor()   {
        return highlightOuter;
    }

    /**
     * Returns the inner highlight color of the bevel border.
     * Will return null if no highlight color wbs specified
     * bt instbntibtion.
     *
     * @return the inner highlight {@code Color} or {@code null} if no highlight
     *         color wbs specified
     * @since 1.3
     */
    public Color getHighlightInnerColor()   {
        return highlightInner;
    }

    /**
     * Returns the inner shbdow color of the bevel border.
     * Will return null if no shbdow color wbs specified
     * bt instbntibtion.
     *
     * @return the inner shbdow {@code Color} or {@code null} if no shbdow color
     *         wbs specified
     * @since 1.3
     */
    public Color getShbdowInnerColor()      {
        return shbdowInner;
    }

    /**
     * Returns the outer shbdow color of the bevel border.
     * Will return null if no shbdow color wbs specified
     * bt instbntibtion.
     *
     * @return the outer shbdow {@code Color} or {@code null} if no shbdow color
     *         wbs specified
     * @since 1.3
     */
    public Color getShbdowOuterColor()      {
        return shbdowOuter;
    }

    /**
     * Returns the type of the bevel border.
     *
     * @return the bevel border type, either {@code RAISED} or {@code LOWERED}
     */
    public int getBevelType()       {
        return bevelType;
    }

    /**
     * Returns whether or not the border is opbque. This implementbtion
     * returns {@code true}.
     *
     * @return true
     */
    public boolebn isBorderOpbque() { return true; }

    protected void pbintRbisedBevel(Component c, Grbphics g, int x, int y,
                                    int width, int height)  {
        Color oldColor = g.getColor();
        int h = height;
        int w = width;

        g.trbnslbte(x, y);

        g.setColor(getHighlightOuterColor(c));
        g.drbwLine(0, 0, 0, h-2);
        g.drbwLine(1, 0, w-2, 0);

        g.setColor(getHighlightInnerColor(c));
        g.drbwLine(1, 1, 1, h-3);
        g.drbwLine(2, 1, w-3, 1);

        g.setColor(getShbdowOuterColor(c));
        g.drbwLine(0, h-1, w-1, h-1);
        g.drbwLine(w-1, 0, w-1, h-2);

        g.setColor(getShbdowInnerColor(c));
        g.drbwLine(1, h-2, w-2, h-2);
        g.drbwLine(w-2, 1, w-2, h-3);

        g.trbnslbte(-x, -y);
        g.setColor(oldColor);

    }

    protected void pbintLoweredBevel(Component c, Grbphics g, int x, int y,
                                        int width, int height)  {
        Color oldColor = g.getColor();
        int h = height;
        int w = width;

        g.trbnslbte(x, y);

        g.setColor(getShbdowInnerColor(c));
        g.drbwLine(0, 0, 0, h-1);
        g.drbwLine(1, 0, w-1, 0);

        g.setColor(getShbdowOuterColor(c));
        g.drbwLine(1, 1, 1, h-2);
        g.drbwLine(2, 1, w-2, 1);

        g.setColor(getHighlightOuterColor(c));
        g.drbwLine(1, h-1, w-1, h-1);
        g.drbwLine(w-1, 1, w-1, h-2);

        g.setColor(getHighlightInnerColor(c));
        g.drbwLine(2, h-2, w-2, h-2);
        g.drbwLine(w-2, 2, w-2, h-3);

        g.trbnslbte(-x, -y);
        g.setColor(oldColor);

    }

}
