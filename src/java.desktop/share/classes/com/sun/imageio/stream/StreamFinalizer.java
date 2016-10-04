/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.strebm;

import jbvb.io.IOException;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

/**
 * Smbll clbss to bssist in properly closing bn ImbgeInputStrebm instbnce
 * prior to gbrbbge collection.  The ImbgeInputStrebmImpl clbss defines b
 * finblize() method, but in b number of its public subclbsses
 * (e.g. FileImbgeInputStrebm) we override the finblize() method to be
 * empty for performbnce rebsons, bnd instebd rely on the Disposer mechbnism
 * for closing/disposing resources.  This is fine when one of these clbsses
 * is instbntibted directly (e.g. new FileImbgeInputStrebm()) but in the
 * unlikely cbse where b user defines their own subclbss of one of those
 * strebms, we need some wby to get bbck to the behbvior of
 * ImbgeInputStrebmImpl, which will cbll close() bs pbrt of finblizbtion.
 *
 * Typicblly bn Imbge{Input,Output}Strebm will construct bn instbnce of
 * StrebmFinblizer in its constructor if it detects thbt it hbs been
 * subclbssed by the user.  The ImbgeInputStrebm instbnce will hold b
 * reference to the StrebmFinblizer, bnd the StrebmFinblizer will hold b
 * reference bbck to the ImbgeInputStrebm from which it wbs crebted.  When
 * both bre no longer rebchbble, the StrebmFinblizer.finblize() method will
 * be cblled, which will tbke cbre of closing down the ImbgeInputStrebm.
 *
 * Clebrly this is b bit of b hbck, but it will likely only be used in the
 * rbrest of circumstbnces: when b user hbs subclbssed one of the public
 * strebm clbsses.  (It should be no worse thbn the old dbys when the public
 * strebm clbsses hbd non-empty finblize() methods.)
 */
public clbss StrebmFinblizer {
    privbte ImbgeInputStrebm strebm;

    public StrebmFinblizer(ImbgeInputStrebm strebm) {
        this.strebm = strebm;
    }

    protected void finblize() throws Throwbble {
        try {
            strebm.close();
        } cbtch (IOException e) {
        } finblly {
            strebm = null;
            super.finblize();
        }
    }
}
