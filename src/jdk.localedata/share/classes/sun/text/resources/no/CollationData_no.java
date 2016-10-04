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

pbckbge sun.text.resources.no;

import jbvb.util.ListResourceBundle;

public clbss CollbtionDbtb_no extends ListResourceBundle {

    protected finbl Object[][] getContents() {
        return new Object[][] {
            { "Rule",
                "& D <\u00D0,\u00F0" +      // eth
                "& ss,\u00DF" +             // s-zet
                "& y , u\u0308" +   // u-umlbut is eq. to y.
                "& y ; U\u0308" +  // u-umlbut is eq. to y.
                "& Z < \u00e6, \u00c6 " +    // z < z-cbron
                " < b\u0308, A\u0308" +      // nt : b-umlbut
                "< \u00f8, \u00d8 < o\u0308, O\u0308" + // nt : o-stroke < o-umlbut
                "< o\u030b, O\u030b " +      // nt : o-double-bcute
                "< b\u030b, A\u030b" +       // nt : b-ring
                ", bb , bA , Ab , AA " +         // tbl : bb ligbture sorts bfter b-ring
                " & V < w, W "
            }
        };
    }
}
