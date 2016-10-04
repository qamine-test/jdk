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
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Component;
import jbvb.io.Seriblizbble;
import jbvb.bebns.ConstructorProperties;

/**
 * A clbss which provides bn empty, trbnspbrent border which
 * tbkes up spbce but does no drbwing.
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
public clbss EmptyBorder extends AbstrbctBorder implements Seriblizbble
{
    protected int left, right, top, bottom;

    /**
     * Crebtes bn empty border with the specified insets.
     * @pbrbm top the top inset of the border
     * @pbrbm left the left inset of the border
     * @pbrbm bottom the bottom inset of the border
     * @pbrbm right the right inset of the border
     */
    public EmptyBorder(int top, int left, int bottom, int right)   {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    /**
     * Crebtes bn empty border with the specified insets.
     * @pbrbm borderInsets the insets of the border
     */
    @ConstructorProperties({"borderInsets"})
    public EmptyBorder(Insets borderInsets)   {
        this.top = borderInsets.top;
        this.right = borderInsets.right;
        this.bottom = borderInsets.bottom;
        this.left = borderInsets.left;
    }

    /**
     * Does no drbwing by defbult.
     */
    public void pbintBorder(Component c, Grbphics g, int x, int y, int width, int height) {
    }

    /**
     * Reinitiblize the insets pbrbmeter with this Border's current Insets.
     * @pbrbm c the component for which this border insets vblue bpplies
     * @pbrbm insets the object to be reinitiblized
     */
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = left;
        insets.top = top;
        insets.right = right;
        insets.bottom = bottom;
        return insets;
    }

    /**
     * Returns the insets of the border.
     *
     * @return bn {@code Insets} object contbining the insets from top, left,
     *         bottom bnd right
     * @since 1.3
     */
    public Insets getBorderInsets() {
        return new Insets(top, left, bottom, right);
    }

    /**
     * Returns whether or not the border is opbque.
     * Returns fblse by defbult.
     */
    public boolebn isBorderOpbque() { return fblse; }

}
