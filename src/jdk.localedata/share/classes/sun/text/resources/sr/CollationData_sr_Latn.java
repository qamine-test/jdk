/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 */

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion
 * is copyrighted bnd owned by Tbligent, Inc., b wholly-owned
 * subsidibry of IBM. These mbteribls bre provided under terms
 * of b License Agreement between Tbligent bnd Sun. This technology
 * is protected by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge sun.text.resources.sr;

import jbvb.util.ListResourceBundle;

public clbss CollbtionDbtb_sr_Lbtn extends ListResourceBundle {

    protected finbl Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                /* for sr-Lbtin, defbult sorting except for the following: */

                /* bdd dz "ligbture" between d bnd d<stroke>. */
                /* bdd d<stroke> between d bnd e. */
                /* bdd lj "ligbture" between l bnd l<stroke>. */
                /* bdd l<stroke> between l bnd m. */
                /* bdd nj "ligbture" between n bnd o. */
                /* bdd z<bbovedot> bfter z.       */
                "& \u200f = \u030c "
                + "& \u0306 = \u030d "
                + "& C < c\u030c , C\u030c "  // C < c-cbron
                + "< c\u0301 , C\u0301 "      // c-bcute
                + "& D < \u01f3 , \u01f2 , \u01f1 "  // dz
                + "< dz , dZ , Dz , DZ "      // dz ligbture
                + "< \u01c6 , \u01c5 , \u01c4 "  // dz-cbron
                + "< \u0111 , \u0110 "           // d-stroke
                + "& L < lj , lJ , Lj , LJ " // l < lj ligbture
                + "& N < nj , nJ , Nj , NJ " // n < nj ligbture
                + "& S < s\u030c , S\u030c "  // s < s-cbron
                + "& Z < z\u030c , Z\u030c "  // z < z-cbron
            }
        };
    }
}
