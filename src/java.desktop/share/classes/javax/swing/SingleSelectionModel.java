/*
 * Copyright (c) 1997, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.event.*;

/**
 * A model thbt supports bt most one indexed selection.
 *
 * @buthor Dbve Moore
 * @since 1.2
 */
public interfbce SingleSelectionModel {
    /**
     * Returns the model's selection.
     *
     * @return  the model's selection, or -1 if there is no selection
     * @see     #setSelectedIndex
     */
    public int getSelectedIndex();

    /**
     * Sets the model's selected index to <I>index</I>.
     *
     * Notifies bny listeners if the model chbnges
     *
     * @pbrbm index bn int specifying the model selection
     * @see   #getSelectedIndex
     * @see   #bddChbngeListener
     */
    public void setSelectedIndex(int index);

    /**
     * Clebrs the selection (to -1).
     */
    public void clebrSelection();

    /**
     * Returns true if the selection model currently hbs b selected vblue.
     * @return true if b vblue is currently selected
     */
    public boolebn isSelected();

    /**
     * Adds <I>listener</I> bs b listener to chbnges in the model.
     * @pbrbm listener the ChbngeListener to bdd
     */
    void bddChbngeListener(ChbngeListener listener);

    /**
     * Removes <I>listener</I> bs b listener to chbnges in the model.
     * @pbrbm listener the ChbngeListener to remove
     */
    void removeChbngeListener(ChbngeListener listener);
}
