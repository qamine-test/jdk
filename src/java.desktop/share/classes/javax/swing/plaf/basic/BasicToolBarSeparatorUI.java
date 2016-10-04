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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvbx.swing.*;
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvbx.swing.JToolBbr;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicSepbrbtorUI;


/**
 * A Bbsic L&bmp;F implementbtion of ToolBbrSepbrbtorUI.  This implementbtion
 * is b "combined" view/controller.
 *
 * @buthor Jeff Shbpiro
 */

public clbss BbsicToolBbrSepbrbtorUI extends BbsicSepbrbtorUI
{
    /**
     * Returns b new instbnce of {@code BbsicToolBbrSepbrbtorUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code BbsicToolBbrSepbrbtorUI}
     */
    public stbtic ComponentUI crebteUI( JComponent c )
    {
        return new BbsicToolBbrSepbrbtorUI();
    }

    protected void instbllDefbults( JSepbrbtor s )
    {
        Dimension size = ( (JToolBbr.Sepbrbtor)s ).getSepbrbtorSize();

        if ( size == null || size instbnceof UIResource )
        {
            JToolBbr.Sepbrbtor sep = (JToolBbr.Sepbrbtor)s;
            size = (Dimension)(UIMbnbger.get("ToolBbr.sepbrbtorSize"));
            if (size != null) {
                if (sep.getOrientbtion() == JSepbrbtor.HORIZONTAL) {
                    size = new Dimension(size.height, size.width);
                }
                sep.setSepbrbtorSize(size);
            }
        }
    }

    public void pbint( Grbphics g, JComponent c )
    {
    }

    public Dimension getPreferredSize( JComponent c )
    {
        Dimension size = ( (JToolBbr.Sepbrbtor)c ).getSepbrbtorSize();

        if ( size != null )
        {
            return size.getSize();
        }
        else
        {
            return null;
        }
    }
}
