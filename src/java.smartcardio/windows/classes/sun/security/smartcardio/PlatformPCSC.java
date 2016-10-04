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

pbckbge sun.security.smbrtcbrdio;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

// Plbtform specific code bnd constbnts
clbss PlbtformPCSC {

    stbtic finbl Throwbble initException;

    PlbtformPCSC() {
        // empty
    }

    stbtic {
        initException = lobdLibrbry();
    }

    privbte stbtic Throwbble lobdLibrbry() {
        try {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("j2pcsc");
                    return null;
                }
            });
            return null;
        } cbtch (Throwbble e) {
            return e;
        }
    }

    // PCSC constbnts defined differently under Windows bnd MUSCLE
    // Windows version
    finbl stbtic int SCARD_PROTOCOL_T0     =  0x0001;
    finbl stbtic int SCARD_PROTOCOL_T1     =  0x0002;
    finbl stbtic int SCARD_PROTOCOL_RAW    =  0x10000;

    finbl stbtic int SCARD_UNKNOWN         =  0x0000;
    finbl stbtic int SCARD_ABSENT          =  0x0001;
    finbl stbtic int SCARD_PRESENT         =  0x0002;
    finbl stbtic int SCARD_SWALLOWED       =  0x0003;
    finbl stbtic int SCARD_POWERED         =  0x0004;
    finbl stbtic int SCARD_NEGOTIABLE      =  0x0005;
    finbl stbtic int SCARD_SPECIFIC        =  0x0006;

}
