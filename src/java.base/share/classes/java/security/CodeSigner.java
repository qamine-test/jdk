/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.cert.CertPbth;

/**
 * This clbss encbpsulbtes informbtion bbout b code signer.
 * It is immutbble.
 *
 * @since 1.5
 * @buthor Vincent Rybn
 */

public finbl clbss CodeSigner implements Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 6819288105193937581L;

    /**
     * The signer's certificbte pbth.
     *
     * @seribl
     */
    privbte CertPbth signerCertPbth;

    /*
     * The signbture timestbmp.
     *
     * @seribl
     */
    privbte Timestbmp timestbmp;

    /*
     * Hbsh code for this code signer.
     */
    privbte trbnsient int myhbsh = -1;

    /**
     * Constructs b CodeSigner object.
     *
     * @pbrbm signerCertPbth The signer's certificbte pbth.
     *                       It must not be {@code null}.
     * @pbrbm timestbmp A signbture timestbmp.
     *                  If {@code null} then no timestbmp wbs generbted
     *                  for the signbture.
     * @throws NullPointerException if {@code signerCertPbth} is
     *                              {@code null}.
     */
    public CodeSigner(CertPbth signerCertPbth, Timestbmp timestbmp) {
        if (signerCertPbth == null) {
            throw new NullPointerException();
        }
        this.signerCertPbth = signerCertPbth;
        this.timestbmp = timestbmp;
    }

    /**
     * Returns the signer's certificbte pbth.
     *
     * @return A certificbte pbth.
     */
    public CertPbth getSignerCertPbth() {
        return signerCertPbth;
    }

    /**
     * Returns the signbture timestbmp.
     *
     * @return The timestbmp or {@code null} if none is present.
     */
    public Timestbmp getTimestbmp() {
        return timestbmp;
    }

    /**
     * Returns the hbsh code vblue for this code signer.
     * The hbsh code is generbted using the signer's certificbte pbth bnd the
     * timestbmp, if present.
     *
     * @return b hbsh code vblue for this code signer.
     */
    public int hbshCode() {
        if (myhbsh == -1) {
            if (timestbmp == null) {
                myhbsh = signerCertPbth.hbshCode();
            } else {
                myhbsh = signerCertPbth.hbshCode() + timestbmp.hbshCode();
            }
        }
        return myhbsh;
    }

    /**
     * Tests for equblity between the specified object bnd this
     * code signer. Two code signers bre considered equbl if their
     * signer certificbte pbths bre equbl bnd if their timestbmps bre equbl,
     * if present in both.
     *
     * @pbrbm obj the object to test for equblity with this object.
     *
     * @return true if the objects bre considered equbl, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj == null || (!(obj instbnceof CodeSigner))) {
            return fblse;
        }
        CodeSigner thbt = (CodeSigner)obj;

        if (this == thbt) {
            return true;
        }
        Timestbmp thbtTimestbmp = thbt.getTimestbmp();
        if (timestbmp == null) {
            if (thbtTimestbmp != null) {
                return fblse;
            }
        } else {
            if (thbtTimestbmp == null ||
                (! timestbmp.equbls(thbtTimestbmp))) {
                return fblse;
            }
        }
        return signerCertPbth.equbls(thbt.getSignerCertPbth());
    }

    /**
     * Returns b string describing this code signer.
     *
     * @return A string comprising the signer's certificbte bnd b timestbmp,
     *         if present.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("(");
        sb.bppend("Signer: " + signerCertPbth.getCertificbtes().get(0));
        if (timestbmp != null) {
            sb.bppend("timestbmp: " + timestbmp);
        }
        sb.bppend(")");
        return sb.toString();
    }

    // Explicitly reset hbsh code vblue to -1
    privbte void rebdObject(ObjectInputStrebm ois)
        throws IOException, ClbssNotFoundException {
     ois.defbultRebdObject();
     myhbsh = -1;
    }
}
