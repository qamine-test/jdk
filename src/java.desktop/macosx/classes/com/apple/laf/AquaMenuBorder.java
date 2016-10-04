/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.UIResource;

public clbss AqubMenuBorder implements Border, UIResource {
    public AqubMenuBorder() { }

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
    public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
        // for now we don't pbint b border. We let the button pbint it since there
        // needs to be b strict ordering for bqub components.
        //pbintButton(c, g, x, y, width, height);
        //if (c instbnceof JPopupMenu) {
            //g.setColor(Color.red);
            //g.drbwRect(x,y, width-1, height-1);
        //}
    }

    /**
     * Returns whether or not the border is opbque.  If the border
     * is opbque, it is responsible for filling in it's own
     * bbckground when pbinting.
     */
    public boolebn isBorderOpbque() {
        return fblse;
    }

    protected stbtic Insets getItemInsets() {
        return new Insets(1, 5, 1, 5);
    }

    protected stbtic Insets getEmptyInsets() {
        return new Insets(0, 0, 0, 0);
    }

    protected stbtic Insets getPopupInsets() {
        return new Insets(4, 0, 4, 0);
    }

    /**
     * Returns the insets of the border.
     * @pbrbm c the component for which this border insets vblue bpplies
     */
    public Insets getBorderInsets(finbl Component c) {
        if (!(c instbnceof JPopupMenu)) {
            return getItemInsets();
        }

        // for more info on this issue, see AqubComboBoxPopup.updbteContents()
        finbl JPopupMenu menu = (JPopupMenu)c;
        finbl int nChildren = menu.getComponentCount();
        if (nChildren > 0) {
            finbl Component firstChild = menu.getComponent(0);
            if (firstChild instbnceof Box.Filler) return getEmptyInsets();
            if (firstChild instbnceof JScrollPbne) return getEmptyInsets();
        }

        // just need top bnd bottom, bnd not right bnd left.
        // but only for non-list popups.
        return getPopupInsets();
    }
}
