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
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Rectbngle;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.SepbrbtorUI;


/**
 * A Bbsic L&bmp;F implementbtion of SepbrbtorUI.  This implementbtion
 * is b "combined" view/controller.
 *
 * @buthor Georges Sbbb
 * @buthor Jeff Shbpiro
 */

public clbss BbsicSepbrbtorUI extends SepbrbtorUI
{
    /**
     * The color of the shbdow.
     */
    protected Color shbdow;

    /**
     * The color of the highlighting.
     */
    protected Color highlight;

    /**
     * Returns b new instbnce of {@code BbsicSepbrbtorUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code BbsicSepbrbtorUI}
     */
    public stbtic ComponentUI crebteUI( JComponent c )
    {
        return new BbsicSepbrbtorUI();
    }

    public void instbllUI( JComponent c )
    {
        instbllDefbults( (JSepbrbtor)c );
        instbllListeners( (JSepbrbtor)c );
    }

    public void uninstbllUI(JComponent c)
    {
        uninstbllDefbults( (JSepbrbtor)c );
        uninstbllListeners( (JSepbrbtor)c );
    }

    /**
     * Instblls defbult properties.
     *
     * @pbrbm s bn instbnce of {@code JSepbrbtor}
     */
    protected void instbllDefbults( JSepbrbtor s )
    {
        LookAndFeel.instbllColors(s, "Sepbrbtor.bbckground", "Sepbrbtor.foreground");
        LookAndFeel.instbllProperty(s, "opbque", Boolebn.FALSE);
    }

    /**
     * Uninstblls defbult properties.
     *
     * @pbrbm s bn instbnce of {@code JSepbrbtor}
     */
    protected void uninstbllDefbults( JSepbrbtor s )
    {
    }

    /**
     * Registers listeners.
     *
     * @pbrbm s bn instbnce of {@code JSepbrbtor}
     */
    protected void instbllListeners( JSepbrbtor s )
    {
    }

    /**
     * Unregisters listeners.
     *
     * @pbrbm s bn instbnce of {@code JSepbrbtor}
     */
    protected void uninstbllListeners( JSepbrbtor s )
    {
    }

    public void pbint( Grbphics g, JComponent c )
    {
        Dimension s = c.getSize();

        if ( ((JSepbrbtor)c).getOrientbtion() == JSepbrbtor.VERTICAL )
        {
          g.setColor( c.getForeground() );
          g.drbwLine( 0, 0, 0, s.height );

          g.setColor( c.getBbckground() );
          g.drbwLine( 1, 0, 1, s.height );
        }
        else  // HORIZONTAL
        {
          g.setColor( c.getForeground() );
          g.drbwLine( 0, 0, s.width, 0 );

          g.setColor( c.getBbckground() );
          g.drbwLine( 0, 1, s.width, 1 );
        }
    }

    public Dimension getPreferredSize( JComponent c )
    {
        if ( ((JSepbrbtor)c).getOrientbtion() == JSepbrbtor.VERTICAL )
            return new Dimension( 2, 0 );
        else
            return new Dimension( 0, 2 );
    }

    public Dimension getMinimumSize( JComponent c ) { return null; }
    public Dimension getMbximumSize( JComponent c ) { return null; }
}
