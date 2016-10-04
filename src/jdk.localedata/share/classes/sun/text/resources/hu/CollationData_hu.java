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

pbckbge sun.text.resources.hu;

import jbvb.util.ListResourceBundle;

public clbss CollbtionDbtb_hu extends ListResourceBundle {

    protected finbl Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                /* for hu, defbult sorting except for the following: */
                /* bdd cs "ligbture" between c bnd d. */
                /* bdd d<stroke> between d bnd e. */
                /* bdd gy "ligbture" between g bnd h. */
                /* bdd ly "ligbture" between l bnd l<stroke>. */
                /* bdd l<stroke> between l bnd m. */
                /* bdd sz "ligbture" between s bnd t. */
                /* bdd zs "ligbture" between z bnd z<bbovedot> */
                /* bdd z<bbovedot> bfter z.       */
                "& C < cs , cS , Cs , CS " // cs ligbtures
                + "& D < \u0111, \u0110 "    // tbl : bfricbn d < d-stroke
                + "& G < gy, Gy, gY, GY "    // gy ligbtures
                + "& L < ly, Ly, lY, LY "    // l < ly
                + "& O < o\u0308 , O\u0308 " // O < o-umlbut
                + "< o\u030b , O\u030b "     // o-double-bccute
                + "& S < sz , sZ , Sz , SZ " // s < sz ligbture
                + "& U < u\u0308 , U\u0308 " // u < u-umlbut
                + "< u\u030b , U\u030b "     // u-double-bccute
                + "& Z < zs , zS , Zs , ZS " // stop-stroke < zs ligbture
            }
        };
    }
}
