/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;


import jbvb.bwt.Component;
import jbvb.bwt.FocusTrbversblPolicy;

/**
 * A FocusTrbversblPolicy which cbn optionblly provide bn blgorithm for
 * determining b JInternblFrbme's initibl Component. The initibl Component is
 * the first to receive focus when the JInternblFrbme is first selected. By
 * defbult, this is the sbme bs the JInternblFrbme's defbult Component to
 * focus.
 *
 * @buthor Dbvid Mendenhbll
 *
 * @since 1.4
 */
public bbstrbct clbss InternblFrbmeFocusTrbversblPolicy
    extends FocusTrbversblPolicy
{

    /**
     * Returns the Component thbt should receive the focus when b
     * JInternblFrbme is selected for the first time. Once the JInternblFrbme
     * hbs been selected by b cbll to <code>setSelected(true)</code>, the
     * initibl Component will not be used bgbin. Instebd, if the JInternblFrbme
     * loses bnd subsequently regbins selection, or is mbde invisible or
     * undisplbybble bnd subsequently mbde visible bnd displbybble, the
     * JInternblFrbme's most recently focused Component will become the focus
     * owner. The defbult implementbtion of this method returns the
     * JInternblFrbme's defbult Component to focus.
     *
     * @pbrbm frbme the JInternblFrbme whose initibl Component is to be
     *        returned
     * @return the Component thbt should receive the focus when frbme is
     *         selected for the first time, or null if no suitbble Component
     *         cbn be found
     * @see JInternblFrbme#getMostRecentFocusOwner
     * @throws IllegblArgumentException if window is null
     */
    public Component getInitiblComponent(JInternblFrbme frbme) {
        return getDefbultComponent(frbme);
    }
}
