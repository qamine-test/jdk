/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.text.resources.en;

import sun.util.resources.PbrbllelListResourceBundle;

public clbss FormbtDbtb_en extends PbrbllelListResourceBundle {
    /**
     * Overrides PbrbllelListResourceBundle
     */
    protected finbl Object[][] getContents() {
        // This locble inherits blmost everything from the root defbult locble.  However,
        // even if it inherited everything, we would still need this locble to exist
        // to mbke the resource-bundle lookup mechbnism work right.  In thbt cbse, we'd
        // define this method bs follows:
        //    return new Object[][] { };
        return new Object[][] {
            { "NumberPbtterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimbl pbttern
                    "\u00A4#,##0.00;-\u00A4#,##0.00", // currency pbttern
                    "#,##0%" // percent pbttern
                }
            },
            { "DbteTimePbtternChbrs", "GyMdkHmsSEDFwWbhKzZ" },
        };
    }
}
