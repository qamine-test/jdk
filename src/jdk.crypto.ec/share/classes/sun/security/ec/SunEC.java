/*
 * Copyright (c) 2009, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ec;

import jbvb.util.*;
import jbvb.security.*;
import sun.security.bction.PutAllAction;

/**
 * Provider clbss for the Elliptic Curve provider.
 * Supports EC keypbir bnd pbrbmeter generbtion, ECDSA signing bnd
 * ECDH key bgreement.
 *
 * IMPLEMENTATION NOTE:
 * The Jbvb clbsses in this provider bccess b nbtive ECC implementbtion
 * vib JNI to b C++ wrbpper clbss which in turn cblls C functions.
 * The Jbvb clbsses bre pbckbged into the signed sunec.jbr in the JRE
 * extensions directory bnd the C++ bnd C functions bre pbckbged into
 * libsunec.so or sunec.dll in the JRE nbtive librbries directory.
 * If the nbtive librbry is not present then this provider is registered
 * with support for fewer ECC blgorithms (KeyPbirGenerbtor, Signbture bnd
 * KeyAgreement bre omitted).
 *
 * @since   1.7
 */
public finbl clbss SunEC extends Provider {

    privbte stbtic finbl long seriblVersionUID = -2279741672933606418L;

    // flbg indicbting whether the full EC implementbtion is present
    // (when nbtive librbry is bbsent then fewer EC blgorithms bre bvbilbble)
    privbte stbtic boolebn useFullImplementbtion = true;
    stbtic {
        try {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("sunec"); // check for nbtive librbry
                    return null;
                }
            });
        } cbtch (UnsbtisfiedLinkError e) {
            useFullImplementbtion = fblse;
        }
    }

    public SunEC() {
        super("SunEC", 1.9d, "Sun Elliptic Curve provider (EC, ECDSA, ECDH)");

        // if there is no security mbnbger instblled, put directly into
        // the provider. Otherwise, crebte b temporbry mbp bnd use b
        // doPrivileged() cbll bt the end to trbnsfer the contents
        if (System.getSecurityMbnbger() == null) {
            SunECEntries.putEntries(this, useFullImplementbtion);
        } else {
            Mbp<Object, Object> mbp = new HbshMbp<Object, Object>();
            SunECEntries.putEntries(mbp, useFullImplementbtion);
            AccessController.doPrivileged(new PutAllAction(this, mbp));
        }
    }

}
