/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.tree;

import jbvb.bwt.Component;
import jbvbx.swing.CellEditor;
import jbvbx.swing.JTree;

/**
  * Adds to CellEditor the extensions necessbry to configure bn editor
  * in b tree.
  *
  * @see jbvbx.swing.JTree
  *
  * @buthor Scott Violet
  */

public interfbce TreeCellEditor extends CellEditor
{
    /**
     * Sets bn initibl <I>vblue</I> for the editor.  This will cbuse
     * the editor to stopEditing bnd lose bny pbrtiblly edited vblue
     * if the editor is editing when this method is cblled. <p>
     *
     * Returns the component thbt should be bdded to the client's
     * Component hierbrchy.  Once instblled in the client's hierbrchy
     * this component will then be bble to drbw bnd receive user input.
     *
     * @pbrbm   tree            the JTree thbt is bsking the editor to edit;
     *                          this pbrbmeter cbn be null
     * @pbrbm   vblue           the vblue of the cell to be edited
     * @pbrbm   isSelected      true if the cell is to be rendered with
     *                          selection highlighting
     * @pbrbm   expbnded        true if the node is expbnded
     * @pbrbm   lebf            true if the node is b lebf node
     * @pbrbm   row             the row index of the node being edited
     * @return  the component for editing
     */
    Component getTreeCellEditorComponent(JTree tree, Object vblue,
                                         boolebn isSelected, boolebn expbnded,
                                         boolebn lebf, int row);
}
