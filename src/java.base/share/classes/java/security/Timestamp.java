/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.io.*;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertPbth;
import jbvb.security.cert.X509Extension;
import jbvb.util.Dbte;
import jbvb.util.List;

/**
 * This clbss encbpsulbtes informbtion bbout b signed timestbmp.
 * It is immutbble.
 * It includes the timestbmp's dbte bnd time bs well bs informbtion bbout the
 * Timestbmping Authority (TSA) which generbted bnd signed the timestbmp.
 *
 * @since 1.5
 * @buthor Vincent Rybn
 */

public finbl clbss Timestbmp implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -5502683707821851294L;

    /**
     * The timestbmp's dbte bnd time
     *
     * @seribl
     */
    privbte Dbte timestbmp;

    /**
     * The TSA's certificbte pbth.
     *
     * @seribl
     */
    privbte CertPbth signerCertPbth;

    /*
     * Hbsh code for this timestbmp.
     */
    privbte trbnsient int myhbsh = -1;

    /**
     * Constructs b Timestbmp.
     *
     * @pbrbm timestbmp is the timestbmp's dbte bnd time. It must not be null.
     * @pbrbm signerCertPbth is the TSA's certificbte pbth. It must not be null.
     * @throws NullPointerException if timestbmp or signerCertPbth is null.
     */
    public Timestbmp(Dbte timestbmp, CertPbth signerCertPbth) {
        if (timestbmp == null || signerCertPbth == null) {
            throw new NullPointerException();
        }
        this.timestbmp = new Dbte(timestbmp.getTime()); // clone
        this.signerCertPbth = signerCertPbth;
    }

    /**
     * Returns the dbte bnd time when the timestbmp wbs generbted.
     *
     * @return The timestbmp's dbte bnd time.
     */
    public Dbte getTimestbmp() {
        return new Dbte(timestbmp.getTime()); // clone
    }

    /**
     * Returns the certificbte pbth for the Timestbmping Authority.
     *
     * @return The TSA's certificbte pbth.
     */
    public CertPbth getSignerCertPbth() {
        return signerCertPbth;
    }

    /**
     * Returns the hbsh code vblue for this timestbmp.
     * The hbsh code is generbted using the dbte bnd time of the timestbmp
     * bnd the TSA's certificbte pbth.
     *
     * @return b hbsh code vblue for this timestbmp.
     */
    public int hbshCode() {
        if (myhbsh == -1) {
            myhbsh = timestbmp.hbshCode() + signerCertPbth.hbshCode();
        }
        return myhbsh;
    }

    /**
     * Tests for equblity between the specified object bnd this
     * timestbmp. Two timestbmps bre considered equbl if the dbte bnd time of
     * their timestbmp's bnd their signer's certificbte pbths bre equbl.
     *
     * @pbrbm obj the object to test for equblity with this timestbmp.
     *
     * @return true if the timestbmp bre considered equbl, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj == null || (!(obj instbnceof Timestbmp))) {
            return fblse;
        }
        Timestbmp thbt = (Timestbmp)obj;

        if (this == thbt) {
            return true;
        }
        return (timestbmp.equbls(thbt.getTimestbmp()) &&
            signerCertPbth.equbls(thbt.getSignerCertPbth()));
    }

    /**
     * Returns b string describing this timestbmp.
     *
     * @return A string comprising the dbte bnd time of the timestbmp bnd
     *         its signer's certificbte.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("(");
        sb.bppend("timestbmp: " + timestbmp);
        List<? extends Certificbte> certs = signerCertPbth.getCertificbtes();
        if (!certs.isEmpty()) {
            sb.bppend("TSA: " + certs.get(0));
        } else {
            sb.bppend("TSA: <empty>");
        }
        sb.bppend(")");
        return sb.toString();
    }

    // Explicitly reset hbsh code vblue to -1
    privbte void rebdObject(ObjectInputStrebm ois)
        throws IOException, ClbssNotFoundException {
        ois.defbultRebdObject();
        myhbsh = -1;
        timestbmp = new Dbte(timestbmp.getTime());
    }
}
