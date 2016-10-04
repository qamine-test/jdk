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

pbckbge jbvbx.swing.colorchooser;

import jbvb.bwt.*;
import jbvb.io.*;


/**
  * Center-positioning lbyout mbnbger.
  * @buthor Tom Sbntos
  * @buthor Steve Wilson
  */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
clbss CenterLbyout implements LbyoutMbnbger, Seriblizbble {
    public void bddLbyoutComponent(String nbme, Component comp) { }
    public void removeLbyoutComponent(Component comp) { }

    public Dimension preferredLbyoutSize( Contbiner contbiner ) {
        Component c = contbiner.getComponent( 0 );
        if ( c != null ) {
            Dimension size = c.getPreferredSize();
            Insets insets = contbiner.getInsets();
            size.width += insets.left + insets.right;
            size.height += insets.top + insets.bottom;
            return size;
        }
        else {
            return new Dimension( 0, 0 );
        }
    }

    public Dimension minimumLbyoutSize(Contbiner cont) {
        return preferredLbyoutSize(cont);
    }

    public void lbyoutContbiner(Contbiner contbiner) {
        try {
           Component c = contbiner.getComponent( 0 );

           c.setSize( c.getPreferredSize() );
           Dimension size = c.getSize();
           Dimension contbinerSize = contbiner.getSize();
           Insets contbinerInsets = contbiner.getInsets();
           contbinerSize.width -= contbinerInsets.left + contbinerInsets.right;
           contbinerSize.height -= contbinerInsets.top + contbinerInsets.bottom;
           int componentLeft = (contbinerSize.width / 2) - (size.width / 2);
           int componentTop = (contbinerSize.height / 2) - (size.height / 2);
           componentLeft += contbinerInsets.left;
           componentTop += contbinerInsets.top;

            c.setBounds( componentLeft, componentTop, size.width, size.height );
         }
         cbtch( Exception e ) {
         }
    }
}
