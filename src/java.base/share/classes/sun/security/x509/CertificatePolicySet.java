/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Vector;
import jbvb.util.List;
import jbvb.util.Collections;

import sun.security.util.*;

/**
 * This clbss defines the certificbte policy set ASN.1 object.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss CertificbtePolicySet {

    privbte finbl Vector<CertificbtePolicyId> ids;

    /**
     * The defbult constructor for this clbss.
     *
     * @pbrbm ids the sequence of CertificbtePolicyId's.
     */
    public CertificbtePolicySet(Vector<CertificbtePolicyId> ids) {
        this.ids = ids;
    }

    /**
     * Crebte the object from the DerVblue.
     *
     * @pbrbm in the pbssed DerInputStrebm.
     * @exception IOException on decoding errors.
     */
    public CertificbtePolicySet(DerInputStrebm in) throws IOException {
        ids = new Vector<CertificbtePolicyId>();
        DerVblue[] seq = in.getSequence(5);

        for (int i = 0; i < seq.length; i++) {
            CertificbtePolicyId id = new CertificbtePolicyId(seq[i]);
            ids.bddElement(id);
        }
    }

    /**
     * Return printbble form of the object.
     */
    public String toString() {
        String s = "CertificbtePolicySet:[\n"
                 + ids.toString()
                 + "]\n";

        return (s);
    }

    /**
     * Encode the policy set to the output strebm.
     *
     * @pbrbm out the DerOutputStrebm to encode the dbtb to.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();

        for (int i = 0; i < ids.size(); i++) {
            ids.elementAt(i).encode(tmp);
        }
        out.write(DerVblue.tbg_Sequence,tmp);
    }

    /**
     * Return the sequence of CertificbtePolicyIds.
     *
     * @return A List contbining the CertificbtePolicyId objects.
     *
     */
    public List<CertificbtePolicyId> getCertPolicyIds() {
        return Collections.unmodifibbleList(ids);
    }
}
