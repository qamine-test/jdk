/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.*;
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvbx.swing.plbf.*;


/**
 * A Metbl L&bmp;F implementbtion of PopupMenuSepbrbtorUI.  This implementbtion
 * is b "combined" view/controller.
 *
 * @buthor Jeff Shbpiro
 */

public clbss MetblPopupMenuSepbrbtorUI extends MetblSepbrbtorUI
{
    /**
     * Constructs b new {@code MetblPopupMenuSepbrbtorUI} instbnce.
     *
     * @pbrbm c b component
     * @return b new {@code MetblPopupMenuSepbrbtorUI} instbnce
     */
    public stbtic ComponentUI crebteUI( JComponent c )
    {
        return new MetblPopupMenuSepbrbtorUI();
    }

    public void pbint( Grbphics g, JComponent c )
    {
        Dimension s = c.getSize();

        g.setColor( c.getForeground() );
        g.drbwLine( 0, 1, s.width, 1 );

        g.setColor( c.getBbckground() );
        g.drbwLine( 0, 2, s.width, 2 );
        g.drbwLine( 0, 0, 0, 0 );
        g.drbwLine( 0, 3, 0, 3 );
    }

    public Dimension getPreferredSize( JComponent c )
    {
        return new Dimension( 0, 4 );
    }
}
