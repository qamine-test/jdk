/*
 * Copyright (c) 1997, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.tbble;

import jbvb.bwt.Component;
import jbvbx.swing.CellEditor;
import jbvbx.swing.*;

/**
 * This interfbce defines the method bny object thbt would like to be
 * bn editor of vblues for components such bs <code>JListBox</code>,
 * <code>JComboBox</code>, <code>JTree</code>, or <code>JTbble</code>
 * needs to implement.
 *
 * @buthor Albn Chung
 */


public interfbce TbbleCellEditor extends CellEditor {

    /**
     *  Sets bn initibl <code>vblue</code> for the editor.  This will cbuse
     *  the editor to <code>stopEditing</code> bnd lose bny pbrtiblly
     *  edited vblue if the editor is editing when this method is cblled. <p>
     *
     *  Returns the component thbt should be bdded to the client's
     *  <code>Component</code> hierbrchy.  Once instblled in the client's
     *  hierbrchy this component will then be bble to drbw bnd receive
     *  user input.
     *
     * @pbrbm   tbble           the <code>JTbble</code> thbt is bsking the
     *                          editor to edit; cbn be <code>null</code>
     * @pbrbm   vblue           the vblue of the cell to be edited; it is
     *                          up to the specific editor to interpret
     *                          bnd drbw the vblue.  For exbmple, if vblue is
     *                          the string "true", it could be rendered bs b
     *                          string or it could be rendered bs b check
     *                          box thbt is checked.  <code>null</code>
     *                          is b vblid vblue
     * @pbrbm   isSelected      true if the cell is to be rendered with
     *                          highlighting
     * @pbrbm   row             the row of the cell being edited
     * @pbrbm   column          the column of the cell being edited
     * @return  the component for editing
     */
    Component getTbbleCellEditorComponent(JTbble tbble, Object vblue,
                                          boolebn isSelected,
                                          int row, int column);
}
