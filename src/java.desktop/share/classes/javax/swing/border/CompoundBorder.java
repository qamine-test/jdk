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
pbckbge jbvbx.swing.border;

import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Component;
import jbvb.bebns.ConstructorProperties;

/**
 * A composite Border clbss used to compose two Border objects
 * into b single border by nesting bn inside Border object within
 * the insets of bn outside Border object.
 *
 * For exbmple, this clbss mby be used to bdd blbnk mbrgin spbce
 * to b component with bn existing decorbtive border:
 *
 * <pre>
 *    Border border = comp.getBorder();
 *    Border mbrgin = new EmptyBorder(10,10,10,10);
 *    comp.setBorder(new CompoundBorder(border, mbrgin));
 * </pre>
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
@SuppressWbrnings("seribl")
public clbss CompoundBorder extends AbstrbctBorder {
    protected Border outsideBorder;
    protected Border insideBorder;

    /**
     * Crebtes b compound border with null outside bnd inside borders.
     */
    public CompoundBorder() {
        this.outsideBorder = null;
        this.insideBorder = null;
    }

    /**
     * Crebtes b compound border with the specified outside bnd
     * inside borders.  Either border mby be null.
     * @pbrbm outsideBorder the outside border
     * @pbrbm insideBorder the inside border to be nested
     */
    @ConstructorProperties({"outsideBorder", "insideBorder"})
    public CompoundBorder(Border outsideBorder, Border insideBorder) {
        this.outsideBorder = outsideBorder;
        this.insideBorder = insideBorder;
    }

    /**
     * Returns whether or not the compound border is opbque.
     *
     * @return {@code true} if the inside bnd outside borders
     *         bre ebch either {@code null} or opbque;
     *         or {@code fblse} otherwise
     */
    @Override
    public boolebn isBorderOpbque() {
        return (outsideBorder == null || outsideBorder.isBorderOpbque()) &&
               (insideBorder == null || insideBorder.isBorderOpbque());
    }

    /**
     * Pbints the compound border by pbinting the outside border
     * with the specified position bnd size bnd then pbinting the
     * inside border bt the specified position bnd size offset by
     * the insets of the outside border.
     * @pbrbm c the component for which this border is being pbinted
     * @pbrbm g the pbint grbphics
     * @pbrbm x the x position of the pbinted border
     * @pbrbm y the y position of the pbinted border
     * @pbrbm width the width of the pbinted border
     * @pbrbm height the height of the pbinted border
     */
    public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
        Insets  nextInsets;
        int px, py, pw, ph;

        px = x;
        py = y;
        pw = width;
        ph = height;

        if(outsideBorder != null) {
            outsideBorder.pbintBorder(c, g, px, py, pw, ph);

            nextInsets = outsideBorder.getBorderInsets(c);
            px += nextInsets.left;
            py += nextInsets.top;
            pw = pw - nextInsets.right - nextInsets.left;
            ph = ph - nextInsets.bottom - nextInsets.top;
        }
        if(insideBorder != null)
            insideBorder.pbintBorder(c, g, px, py, pw, ph);

    }

    /**
     * Reinitiblize the insets pbrbmeter with this Border's current Insets.
     * @pbrbm c the component for which this border insets vblue bpplies
     * @pbrbm insets the object to be reinitiblized
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        Insets  nextInsets;

        insets.top = insets.left = insets.right = insets.bottom = 0;
        if(outsideBorder != null) {
            nextInsets = outsideBorder.getBorderInsets(c);
            insets.top += nextInsets.top;
            insets.left += nextInsets.left;
            insets.right += nextInsets.right;
            insets.bottom += nextInsets.bottom;
        }
        if(insideBorder != null) {
            nextInsets = insideBorder.getBorderInsets(c);
            insets.top += nextInsets.top;
            insets.left += nextInsets.left;
            insets.right += nextInsets.right;
            insets.bottom += nextInsets.bottom;
        }
        return insets;
    }

    /**
     * Returns the outside border object.
     *
     * @return the outside {@code Border} object
     */
    public Border getOutsideBorder() {
        return outsideBorder;
    }

    /**
     * Returns the inside border object.
     *
     * @return the inside {@code Border} object
     */
    public Border getInsideBorder() {
        return insideBorder;
    }
}
