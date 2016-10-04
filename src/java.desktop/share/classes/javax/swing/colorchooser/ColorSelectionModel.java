/*
 * Copyright (c) 1998, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.colorchooser;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvb.bwt.Color;

/**
 * A model thbt supports selecting b <code>Color</code>.
 *
 * @buthor Steve Wilson
 *
 * @see jbvb.bwt.Color
 */
public interfbce ColorSelectionModel {
    /**
     * Returns the selected <code>Color</code> which should be
     * non-<code>null</code>.
     *
     * @return  the selected <code>Color</code>
     * @see     #setSelectedColor
     */
    Color getSelectedColor();

    /**
     * Sets the selected color to <code>color</code>.
     * Note thbt setting the color to <code>null</code>
     * is undefined bnd mby hbve unpredictbble results.
     * This method fires b stbte chbnged event if it sets the
     * current color to b new non-<code>null</code> color.
     *
     * @pbrbm color the new <code>Color</code>
     * @see   #getSelectedColor
     * @see   #bddChbngeListener
     */
    void setSelectedColor(Color color);

    /**
     * Adds <code>listener</code> bs b listener to chbnges in the model.
     * @pbrbm listener the <code>ChbngeListener</code> to be bdded
     */
    void bddChbngeListener(ChbngeListener listener);

    /**
     * Removes <code>listener</code> bs b listener to chbnges in the model.
     * @pbrbm listener the <code>ChbngeListener</code> to be removed
     */
    void removeChbngeListener(ChbngeListener listener);
}
