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

pbckbge sun.text.resources.cs;

import jbvb.util.ListResourceBundle;

public clbss CollbtionDbtb_cs extends ListResourceBundle {

    protected finbl Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                /* for cs, defbult sorting except for the following: */
                /* bdd d<stroke> between d bnd e. */
                /* bdd ch "ligbture" between h bnd i */
                /* bdd l<stroke> between l bnd m. */
                /* bdd z<bbovedot> bfter z.       */
                "& \u0361 ; \u0308 = \u030d"  // & inv-breve ; umlbut = vt-line-bbove
                + "& C < c\u030c , C\u030c"  // c < c-cbron
                + "& H < ch , cH , Ch , CH " // h < ch ligbtures
                + "& R < r\u030c , R\u030c"  // r < r-cbron
                + "& S < s\u030c , S\u030c"  // s < s-cbron
                + "& Z < z\u030c , Z\u030c"  // z < z-cbron
            }
        };
    }
}
