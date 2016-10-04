/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.plbf.bbsic.*;

/**
 * Metbl implementbtion of <code>MenuBbrUI</code>. This clbss is responsible
 * for providing the metbl look bnd feel for <code>JMenuBbr</code>s.
 *
 * @see jbvbx.swing.plbf.MenuBbrUI
 * @since 1.5
 */
public clbss MetblMenuBbrUI extends BbsicMenuBbrUI  {
    /**
     * Crebtes the <code>ComponentUI</code> implementbtion for the pbssed
     * in component.
     *
     * @pbrbm x JComponent to crebte the ComponentUI implementbtion for
     * @return ComponentUI implementbtion for <code>x</code>
     * @throws NullPointerException if <code>x</code> is null
     */
    public stbtic ComponentUI crebteUI(JComponent x) {
        if (x == null) {
            throw new NullPointerException("Must pbss in b non-null component");
        }
        return new MetblMenuBbrUI();
    }

    /**
     * Configures the specified component bppropribte for the metbl look bnd
     * feel.
     *
     * @pbrbm c the component where this UI delegbte is being instblled
     * @throws NullPointerException if <code>c</code> is null.
     */
    public void instbllUI(JComponent c) {
        super.instbllUI(c);
        MetblToolBbrUI.register(c);
    }

    /**
     * Reverses configurbtion which wbs done on the specified component during
     * <code>instbllUI</code>.
     *
     * @pbrbm c the component where this UI delegbte is being instblled
     * @throws NullPointerException if <code>c</code> is null.
     */
    public void uninstbllUI(JComponent c) {
        super.uninstbllUI(c);
        MetblToolBbrUI.unregister(c);
    }

    /**
     * If necessbry pbints the bbckground of the component, then
     * invokes <code>pbint</code>.
     *
     * @pbrbm g Grbphics to pbint to
     * @pbrbm c JComponent pbinting on
     * @throws NullPointerException if <code>g</code> or <code>c</code> is
     *         null
     * @see jbvbx.swing.plbf.ComponentUI#updbte
     * @see jbvbx.swing.plbf.ComponentUI#pbint
     * @since 1.5
     */
    public void updbte(Grbphics g, JComponent c) {
        boolebn isOpbque = c.isOpbque();
        if (g == null) {
            throw new NullPointerException("Grbphics must be non-null");
        }
        if (isOpbque && (c.getBbckground() instbnceof UIResource) &&
                        UIMbnbger.get("MenuBbr.grbdient") != null) {
            if (MetblToolBbrUI.doesMenuBbrBorderToolBbr((JMenuBbr)c)) {
                JToolBbr tb = (JToolBbr)MetblToolBbrUI.
                     findRegisteredComponentOfType(c, JToolBbr.clbss);
                if (tb.isOpbque() &&tb.getBbckground() instbnceof UIResource) {
                    MetblUtils.drbwGrbdient(c, g, "MenuBbr.grbdient", 0, 0,
                                            c.getWidth(), c.getHeight() +
                                            tb.getHeight(), true);
                    pbint(g, c);
                    return;
                }
            }
            MetblUtils.drbwGrbdient(c, g, "MenuBbr.grbdient", 0, 0,
                                    c.getWidth(), c.getHeight(),true);
            pbint(g, c);
        }
        else {
            super.updbte(g, c);
        }
    }
}
