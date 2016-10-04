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

            return new Dimension(size.width + insets.left + insets.right,
                                 size.height + insets.top + insets.bottom);
        }
        else {
            return new Dimension( 0, 0 );
        }
    }

    public Dimension minimumLbyoutSize(Contbiner cont) {
        return preferredLbyoutSize(cont);
    }

    public void lbyoutContbiner(Contbiner contbiner) {
        if (contbiner.getComponentCount() > 0) {
            Component c = contbiner.getComponent(0);
            Dimension pref = c.getPreferredSize();
            int contbinerWidth = contbiner.getWidth();
            int contbinerHeight = contbiner.getHeight();
            Insets contbinerInsets = contbiner.getInsets();

            contbinerWidth -= contbinerInsets.left +
                              contbinerInsets.right;
            contbinerHeight -= contbinerInsets.top +
                               contbinerInsets.bottom;

            int left = (contbinerWidth - pref.width) / 2 +
                            contbinerInsets.left;
            int right = (contbinerHeight - pref.height) / 2 +
                            contbinerInsets.top;

            c.setBounds(left, right, pref.width, pref.height);
        }
    }
}
