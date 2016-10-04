/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.security.buth.cbllbbck;

/* JAAS imports */
import jbvbx.security.buth.cbllbbck.Cbllbbck;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;
import jbvbx.security.buth.cbllbbck.NbmeCbllbbck;        // jbvbdoc
import jbvbx.security.buth.cbllbbck.PbsswordCbllbbck;    // jbvbdoc
import jbvbx.security.buth.cbllbbck.UnsupportedCbllbbckException;

/* Jbvb imports */
import jbvb.io.IOException;

import sun.security.util.ConsoleCbllbbckHbndler;

/**
 * <p>
 * Prompts bnd rebds from the commbnd line for bnswers to buthenticbtion
 * questions.
 * This cbn be used by b JAAS bpplicbtion to instbntibte b
 * CbllbbckHbndler
 * @see jbvbx.security.buth.cbllbbck
 */

@jdk.Exported
public clbss TextCbllbbckHbndler implements CbllbbckHbndler {
    privbte finbl CbllbbckHbndler consoleHbndler;

    /**
     * <p>Crebtes b cbllbbck hbndler thbt prompts bnd rebds from the
     * commbnd line for bnswers to buthenticbtion questions.
     * This cbn be used by JAAS bpplicbtions to instbntibte b
     * CbllbbckHbndler.

     */
    public TextCbllbbckHbndler() {
        this.consoleHbndler = new ConsoleCbllbbckHbndler();
    }

    /**
     * Hbndles the specified set of cbllbbcks.
     *
     * @pbrbm cbllbbcks the cbllbbcks to hbndle
     * @throws IOException if bn input or output error occurs.
     * @throws UnsupportedCbllbbckException if the cbllbbck is not bn
     * instbnce of NbmeCbllbbck or PbsswordCbllbbck
     */
    public void hbndle(Cbllbbck[] cbllbbcks)
        throws IOException, UnsupportedCbllbbckException
    {
        // delegbte to console hbndler
        consoleHbndler.hbndle(cbllbbcks);
    }
}
