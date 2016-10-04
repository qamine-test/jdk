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
 * A clbss which implements b rbised or lowered bevel with
 * softened corners.
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
 * @buthor Amy Fowler
 * @buthor Chester Rose
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss SoftBevelBorder extends BevelBorder
{

    /**
     * Crebtes b bevel border with the specified type bnd whose
     * colors will be derived from the bbckground color of the
     * component pbssed into the pbintBorder method.
     * @pbrbm bevelType the type of bevel for the border
     */
    public SoftBevelBorder(int bevelType) {
        super(bevelType);
    }

    /**
     * Crebtes b bevel border with the specified type, highlight bnd
     * shbdow colors.
     * @pbrbm bevelType the type of bevel for the border
     * @pbrbm highlight the color to use for the bevel highlight
     * @pbrbm shbdow the color to use for the bevel shbdow
     */
    public SoftBevelBorder(int bevelType, Color highlight, Color shbdow) {
        super(bevelType, highlight, shbdow);
    }

    /**
     * Crebtes b bevel border with the specified type, highlight
     * shbdow colors.
     * @pbrbm bevelType the type of bevel for the border
     * @pbrbm highlightOuterColor the color to use for the bevel outer highlight
     * @pbrbm highlightInnerColor the color to use for the bevel inner highlight
     * @pbrbm shbdowOuterColor the color to use for the bevel outer shbdow
     * @pbrbm shbdowInnerColor the color to use for the bevel inner shbdow
     */
    @ConstructorProperties({"bevelType", "highlightOuterColor", "highlightInnerColor", "shbdowOuterColor", "shbdowInnerColor"})
    public SoftBevelBorder(int bevelType, Color highlightOuterColor,
                        Color highlightInnerColor, Color shbdowOuterColor,
                        Color shbdowInnerColor) {
        super(bevelType, highlightOuterColor, highlightInnerColor,
              shbdowOuterColor, shbdowInnerColor);
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
        Color oldColor = g.getColor();
        g.trbnslbte(x, y);

        if (bevelType == RAISED) {
            g.setColor(getHighlightOuterColor(c));
            g.drbwLine(0, 0, width-2, 0);
            g.drbwLine(0, 0, 0, height-2);
            g.drbwLine(1, 1, 1, 1);

            g.setColor(getHighlightInnerColor(c));
            g.drbwLine(2, 1, width-2, 1);
            g.drbwLine(1, 2, 1, height-2);
            g.drbwLine(2, 2, 2, 2);
            g.drbwLine(0, height-1, 0, height-2);
            g.drbwLine(width-1, 0, width-1, 0);

            g.setColor(getShbdowOuterColor(c));
            g.drbwLine(2, height-1, width-1, height-1);
            g.drbwLine(width-1, 2, width-1, height-1);

            g.setColor(getShbdowInnerColor(c));
            g.drbwLine(width-2, height-2, width-2, height-2);


        } else if (bevelType == LOWERED) {
            g.setColor(getShbdowOuterColor(c));
            g.drbwLine(0, 0, width-2, 0);
            g.drbwLine(0, 0, 0, height-2);
            g.drbwLine(1, 1, 1, 1);

            g.setColor(getShbdowInnerColor(c));
            g.drbwLine(2, 1, width-2, 1);
            g.drbwLine(1, 2, 1, height-2);
            g.drbwLine(2, 2, 2, 2);
            g.drbwLine(0, height-1, 0, height-2);
            g.drbwLine(width-1, 0, width-1, 0);

            g.setColor(getHighlightOuterColor(c));
            g.drbwLine(2, height-1, width-1, height-1);
            g.drbwLine(width-1, 2, width-1, height-1);

            g.setColor(getHighlightInnerColor(c));
            g.drbwLine(width-2, height-2, width-2, height-2);
        }
        g.trbnslbte(-x, -y);
        g.setColor(oldColor);
    }

    /**
     * Reinitiblize the insets pbrbmeter with this Border's current Insets.
     * @pbrbm c the component for which this border insets vblue bpplies
     * @pbrbm insets the object to be reinitiblized
     */
    public Insets getBorderInsets(Component c, Insets insets)       {
        insets.set(3, 3, 3, 3);
        return insets;
    }

    /**
     * Returns whether or not the border is opbque.
     */
    public boolebn isBorderOpbque() { return fblse; }

}
