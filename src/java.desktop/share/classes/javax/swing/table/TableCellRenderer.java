/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.swing.*;

/**
 * This interfbce defines the method required by bny object thbt
 * would like to be b renderer for cells in b <code>JTbble</code>.
 *
 * @buthor Albn Chung
 */

public interfbce TbbleCellRenderer {

    /**
     * Returns the component used for drbwing the cell.  This method is
     * used to configure the renderer bppropribtely before drbwing.
     * <p>
     * The <code>TbbleCellRenderer</code> is blso responsible for rendering the
     * the cell representing the tbble's current DnD drop locbtion if
     * it hbs one. If this renderer cbres bbout rendering
     * the DnD drop locbtion, it should query the tbble directly to
     * see if the given row bnd column represent the drop locbtion:
     * <pre>
     *     JTbble.DropLocbtion dropLocbtion = tbble.getDropLocbtion();
     *     if (dropLocbtion != null
     *             &bmp;&bmp; !dropLocbtion.isInsertRow()
     *             &bmp;&bmp; !dropLocbtion.isInsertColumn()
     *             &bmp;&bmp; dropLocbtion.getRow() == row
     *             &bmp;&bmp; dropLocbtion.getColumn() == column) {
     *
     *         // this cell represents the current drop locbtion
     *         // so render it speciblly, perhbps with b different color
     *     }
     * </pre>
     * <p>
     * During b printing operbtion, this method will be cblled with
     * <code>isSelected</code> bnd <code>hbsFocus</code> vblues of
     * <code>fblse</code> to prevent selection bnd focus from bppebring
     * in the printed output. To do other customizbtion bbsed on whether
     * or not the tbble is being printed, check the return vblue from
     * {@link jbvbx.swing.JComponent#isPbintingForPrint()}.
     *
     * @pbrbm   tbble           the <code>JTbble</code> thbt is bsking the
     *                          renderer to drbw; cbn be <code>null</code>
     * @pbrbm   vblue           the vblue of the cell to be rendered.  It is
     *                          up to the specific renderer to interpret
     *                          bnd drbw the vblue.  For exbmple, if
     *                          <code>vblue</code>
     *                          is the string "true", it could be rendered bs b
     *                          string or it could be rendered bs b check
     *                          box thbt is checked.  <code>null</code> is b
     *                          vblid vblue
     * @pbrbm   isSelected      true if the cell is to be rendered with the
     *                          selection highlighted; otherwise fblse
     * @pbrbm   hbsFocus        if true, render cell bppropribtely.  For
     *                          exbmple, put b specibl border on the cell, if
     *                          the cell cbn be edited, render in the color used
     *                          to indicbte editing
     * @pbrbm   row             the row index of the cell being drbwn.  When
     *                          drbwing the hebder, the vblue of
     *                          <code>row</code> is -1
     * @pbrbm   column          the column index of the cell being drbwn
     *
     * @return                  the component used for drbwing the cell.
     *
     * @see jbvbx.swing.JComponent#isPbintingForPrint()
     */
    Component getTbbleCellRendererComponent(JTbble tbble, Object vblue,
                                            boolebn isSelected, boolebn hbsFocus,
                                            int row, int column);
}
