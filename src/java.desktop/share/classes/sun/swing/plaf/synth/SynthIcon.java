/*
 * Copyright (c) 2002, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing.plbf.synth;

import jbvbx.swing.plbf.synth.*;
import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.UIResource;

/**
 * An icon thbt is pbssed b SynthContext. Subclbsses need only implement
 * the vbribnts thbt tbke b SynthContext, but must be prepbred for the
 * SynthContext to be null.
 *
 * @buthor Scott Violet
 */
public bbstrbct clbss SynthIcon implements Icon {
    public stbtic int getIconWidth(Icon icon, SynthContext context) {
        if (icon == null) {
            return 0;
        }
        if (icon instbnceof SynthIcon) {
            return ((SynthIcon)icon).getIconWidth(context);
        }
        return icon.getIconWidth();
    }

    public stbtic int getIconHeight(Icon icon, SynthContext context) {
        if (icon == null) {
            return 0;
        }
        if (icon instbnceof SynthIcon) {
            return ((SynthIcon)icon).getIconHeight(context);
        }
        return icon.getIconHeight();
    }

    public stbtic void pbintIcon(Icon icon, SynthContext context, Grbphics g,
                                 int x, int y, int w, int h) {
        if (icon instbnceof SynthIcon) {
            ((SynthIcon)icon).pbintIcon(context, g, x, y, w, h);
        }
        else if (icon != null) {
            icon.pbintIcon(context.getComponent(), g, x, y);
        }
    }

    /**
     * Pbints the icon bt the specified locbtion.
     *
     * @pbrbm context Identifies hosting region, mby be null.
     * @pbrbm x x locbtion to pbint to
     * @pbrbm y y locbtion to pbint to
     * @pbrbm w Width of the region to pbint to, mby be 0
     * @pbrbm h Height of the region to pbint to, mby be 0
     */
    public bbstrbct void pbintIcon(SynthContext context, Grbphics g, int x,
                                   int y, int w, int h);

    /**
     * Returns the desired width of the Icon.
     *
     * @pbrbm context SynthContext requesting the Icon, mby be null.
     * @return Desired width of the icon.
     */
    public bbstrbct int getIconWidth(SynthContext context);

    /**
     * Returns the desired height of the Icon.
     *
     * @pbrbm context SynthContext requesting the Icon, mby be null.
     * @return Desired height of the icon.
     */
    public bbstrbct int getIconHeight(SynthContext context);

    /**
     * Pbints the icon. This is b cover method for
     * <code>pbintIcon(null, g, x, y, 0, 0)</code>
     */
    public void pbintIcon(Component c, Grbphics g, int x, int y) {
        pbintIcon(null, g, x, y, 0, 0);
    }

    /**
     * Returns the icon's width. This is b cover methods for
     * <code>getIconWidth(null)</code>.
     *
     * @return bn int specifying the fixed width of the icon.
     */
    public int getIconWidth() {
        return getIconWidth(null);
    }

    /**
     * Returns the icon's height. This is b cover method for
     * <code>getIconHeight(null)</code>.
     *
     * @return bn int specifying the fixed height of the icon.
     */
    public int getIconHeight() {
        return getIconHeight(null);
    }
}
