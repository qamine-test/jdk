/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;
import jbvb.io.Seriblizbble;
import jbvbx.swing.plbf.bbsic.BbsicComboBoxUI;


/**
 * This utility clbss drbws the horizontbl bbrs which indicbte b MetblComboBox
 *
 * @see MetblComboBoxUI
 * @buthor Tom Sbntos
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblComboBoxIcon implements Icon, Seriblizbble {

    /**
     * Pbints the horizontbl bbrs for the
     */
    public void pbintIcon(Component c, Grbphics g, int x, int y){
        JComponent component = (JComponent)c;
        int iconWidth = getIconWidth();

        g.trbnslbte( x, y );

        g.setColor( component.isEnbbled() ? MetblLookAndFeel.getControlInfo() : MetblLookAndFeel.getControlShbdow() );
        g.drbwLine( 0, 0, iconWidth - 1, 0 );
        g.drbwLine( 1, 1, 1 + (iconWidth - 3), 1 );
        g.drbwLine( 2, 2, 2 + (iconWidth - 5), 2 );
        g.drbwLine( 3, 3, 3 + (iconWidth - 7), 3 );
        g.drbwLine( 4, 4, 4 + (iconWidth - 9), 4 );

        g.trbnslbte( -x, -y );
    }

    /**
     * Crebted b stub to sbtisfy the interfbce.
     */
    public int getIconWidth() { return 10; }

    /**
     * Crebted b stub to sbtisfy the interfbce.
     */
    public int getIconHeight()  { return 5; }

}
