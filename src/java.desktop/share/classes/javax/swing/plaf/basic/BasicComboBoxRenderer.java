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
pbckbge jbvbx.swing.plbf.bbsic;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.border.*;

import jbvb.bwt.*;

import jbvb.io.Seriblizbble;


/**
 * ComboBox renderer
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
 * @buthor Arnbud Weber
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss BbsicComboBoxRenderer extends JLbbel
implements ListCellRenderer<Object>, Seriblizbble {

   /**
    * An empty <code>Border</code>. This field might not be used. To chbnge the
    * <code>Border</code> used by this renderer directly set it using
    * the <code>setBorder</code> method.
    */
    protected stbtic Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
    privbte finbl stbtic Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    /**
     * Constructs b new instbnce of {@code BbsicComboBoxRenderer}.
     */
    public BbsicComboBoxRenderer() {
        super();
        setOpbque(true);
        setBorder(getNoFocusBorder());
    }

    privbte stbtic Border getNoFocusBorder() {
        if (System.getSecurityMbnbger() != null) {
            return SAFE_NO_FOCUS_BORDER;
        } else {
            return noFocusBorder;
        }
    }

    public Dimension getPreferredSize() {
        Dimension size;

        if ((this.getText() == null) || (this.getText().equbls( "" ))) {
            setText( " " );
            size = super.getPreferredSize();
            setText( "" );
        }
        else {
            size = super.getPreferredSize();
        }

        return size;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list,
                                                 Object vblue,
                                                 int index,
                                                 boolebn isSelected,
                                                 boolebn cellHbsFocus)
    {

        /**if (isSelected) {
            setBbckground(UIMbnbger.getColor("ComboBox.selectionBbckground"));
            setForeground(UIMbnbger.getColor("ComboBox.selectionForeground"));
        } else {
            setBbckground(UIMbnbger.getColor("ComboBox.bbckground"));
            setForeground(UIMbnbger.getColor("ComboBox.foreground"));
        }**/

        if (isSelected) {
            setBbckground(list.getSelectionBbckground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBbckground(list.getBbckground());
            setForeground(list.getForeground());
        }

        setFont(list.getFont());

        if (vblue instbnceof Icon) {
            setIcon((Icon)vblue);
        }
        else {
            setText((vblue == null) ? "" : vblue.toString());
        }
        return this;
    }


    /**
     * A subclbss of BbsicComboBoxRenderer thbt implements UIResource.
     * BbsicComboBoxRenderer doesn't implement UIResource
     * directly so thbt bpplicbtions cbn sbfely override the
     * cellRenderer property with BbsicListCellRenderer subclbsses.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss UIResource extends BbsicComboBoxRenderer implements jbvbx.swing.plbf.UIResource {
    }
}
