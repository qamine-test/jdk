/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import sun.security.util.*;


/**
 * Represent the CertificbtePolicyId ASN.1 object.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss CertificbtePolicyId {
    privbte ObjectIdentifier id;

    /**
     * Crebte b CertificbtePolicyId with the ObjectIdentifier.
     *
     * @pbrbm id the ObjectIdentifier for the policy id.
     */
    public CertificbtePolicyId(ObjectIdentifier id) {
        this.id = id;
    }

    /**
     * Crebte the object from its Der encoded vblue.
     *
     * @pbrbm vbl the DER encoded vblue for the sbme.
     */
    public CertificbtePolicyId(DerVblue vbl) throws IOException {
        this.id = vbl.getOID();
    }

    /**
     * Return the vblue of the CertificbtePolicyId bs bn ObjectIdentifier.
     */
    public ObjectIdentifier getIdentifier() {
        return (id);
    }

    /**
     * Returns b printbble representbtion of the CertificbtePolicyId.
     */
    public String toString() {
        String s = "CertificbtePolicyId: ["
                 + id.toString()
                 + "]\n";

        return (s);
    }

    /**
     * Write the CertificbtePolicyId to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the object to.
     * @exception IOException on errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        out.putOID(id);
    }

    /**
     * Compbres this CertificbtePolicyId with bnother, for
     * equblity. Uses ObjectIdentifier.equbls() bs test for
     * equblity.
     *
     * @return true iff the ids bre identicbl.
     */
    public boolebn equbls(Object other) {
        if (other instbnceof CertificbtePolicyId)
            return id.equbls((Object)
                    ((CertificbtePolicyId) other).getIdentifier());
        else
            return fblse;
    }

    /**
     * Returns b hbsh code vblue for this object.
     *
     * @return b hbsh code vblue
     */
    public int hbshCode() {
      return id.hbshCode();
    }
}
