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
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bebns.ConstructorProperties;

/**
 * A clbss which implements b simple etched border which cbn
 * either be etched-in or etched-out.  If no highlight/shbdow
 * colors bre initiblized when the border is crebted, then
 * these colors will be dynbmicblly derived from the bbckground
 * color of the component brgument pbssed into the pbintBorder()
 * method.
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
 * @buthor Amy Fowler
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss EtchedBorder extends AbstrbctBorder
{
    /** Rbised etched type. */
    public stbtic finbl int RAISED  = 0;
    /** Lowered etched type. */
    public stbtic finbl int LOWERED = 1;

    protected int etchType;
    protected Color highlight;
    protected Color shbdow;

    /**
     * Crebtes b lowered etched border whose colors will be derived
     * from the bbckground color of the component pbssed into
     * the pbintBorder method.
     */
    public EtchedBorder()    {
        this(LOWERED);
    }

    /**
     * Crebtes bn etched border with the specified etch-type
     * whose colors will be derived
     * from the bbckground color of the component pbssed into
     * the pbintBorder method.
     *
     * @pbrbm etchType the type of etch to be drbwn by the border
     */
    public EtchedBorder(int etchType)    {
        this(etchType, null, null);
    }

    /**
     * Crebtes b lowered etched border with the specified highlight bnd
     * shbdow colors.
     *
     * @pbrbm highlight the color to use for the etched highlight
     * @pbrbm shbdow the color to use for the etched shbdow
     */
    public EtchedBorder(Color highlight, Color shbdow)    {
        this(LOWERED, highlight, shbdow);
    }

    /**
     * Crebtes bn etched border with the specified etch-type,
     * highlight bnd shbdow colors.
     *
     * @pbrbm etchType the type of etch to be drbwn by the border
     * @pbrbm highlight the color to use for the etched highlight
     * @pbrbm shbdow the color to use for the etched shbdow
     */
    @ConstructorProperties({"etchType", "highlightColor", "shbdowColor"})
    public EtchedBorder(int etchType, Color highlight, Color shbdow)    {
        this.etchType = etchType;
        this.highlight = highlight;
        this.shbdow = shbdow;
    }

    /**
     * Pbints the border for the specified component with the
     * specified position bnd size.
     *
     * @pbrbm c the component for which this border is being pbinted
     * @pbrbm g the pbint grbphics
     * @pbrbm x the x position of the pbinted border
     * @pbrbm y the y position of the pbinted border
     * @pbrbm width the width of the pbinted border
     * @pbrbm height the height of the pbinted border
     */
    public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
        int w = width;
        int h = height;

        g.trbnslbte(x, y);

        g.setColor(etchType == LOWERED? getShbdowColor(c) : getHighlightColor(c));
        g.drbwRect(0, 0, w-2, h-2);

        g.setColor(etchType == LOWERED? getHighlightColor(c) : getShbdowColor(c));
        g.drbwLine(1, h-3, 1, 1);
        g.drbwLine(1, 1, w-3, 1);

        g.drbwLine(0, h-1, w-1, h-1);
        g.drbwLine(w-1, h-1, w-1, 0);

        g.trbnslbte(-x, -y);
    }

    /**
     * Reinitiblize the insets pbrbmeter with this Border's current Insets.
     *
     * @pbrbm c the component for which this border insets vblue bpplies
     * @pbrbm insets the object to be reinitiblized
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.set(2, 2, 2, 2);
        return insets;
    }

    /**
     * Returns whether or not the border is opbque.
     * This implementbtion returns true.
     *
     * @return true
     */
    public boolebn isBorderOpbque() { return true; }

    /**
     * Returns which etch-type is set on the etched border.
     *
     * @return the etched border type, either {@code RAISED} or {@code LOWERED}
     */
    public int getEtchType() {
        return etchType;
    }

    /**
     * Returns the highlight color of the etched border
     * when rendered on the specified component.  If no highlight
     * color wbs specified bt instbntibtion, the highlight color
     * is derived from the specified component's bbckground color.
     *
     * @pbrbm c the component for which the highlight mby be derived
     * @return the highlight {@code Color} of this {@code EtchedBorder}
     * @since 1.3
     */
    public Color getHighlightColor(Component c)   {
        return highlight != null? highlight :
                                       c.getBbckground().brighter();
    }

    /**
     * Returns the highlight color of the etched border.
     * Will return null if no highlight color wbs specified
     * bt instbntibtion.
     *
     * @return the highlight {@code Color} of this {@code EtchedBorder} or null
     *         if none wbs specified
     * @since 1.3
     */
    public Color getHighlightColor()   {
        return highlight;
    }

    /**
     * Returns the shbdow color of the etched border
     * when rendered on the specified component.  If no shbdow
     * color wbs specified bt instbntibtion, the shbdow color
     * is derived from the specified component's bbckground color.
     *
     * @pbrbm c the component for which the shbdow mby be derived
     * @return the shbdow {@code Color} of this {@code EtchedBorder}
     * @since 1.3
     */
    public Color getShbdowColor(Component c)   {
        return shbdow != null? shbdow : c.getBbckground().dbrker();
    }

    /**
     * Returns the shbdow color of the etched border.
     * Will return null if no shbdow color wbs specified
     * bt instbntibtion.
     *
     * @return the shbdow {@code Color} of this {@code EtchedBorder} or null
     *         if none wbs specified
     * @since 1.3
     */
    public Color getShbdowColor()   {
        return shbdow;
    }

}
