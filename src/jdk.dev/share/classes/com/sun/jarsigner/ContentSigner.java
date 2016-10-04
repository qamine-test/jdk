/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbrsigner;

import jbvb.io.IOException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.cert.CertificbteException;

/**
 * This clbss defines b content signing service.
 * Implementbtions must be instbntibble using b zero-brgument constructor.
 *
 * @since 1.5
 * @buthor Vincent Rybn
 */

public bbstrbct clbss ContentSigner {

    /**
     * Generbtes b PKCS #7 signed dbtb messbge.
     * This method is used when the signbture hbs blrebdy been generbted.
     * The signbture, the signer's detbils, bnd optionblly b signbture
     * timestbmp bnd the content thbt wbs signed, bre bll pbckbged into b
     * signed dbtb messbge.
     *
     * @pbrbm pbrbmeters The non-null input pbrbmeters.
     * @pbrbm omitContent true if the content should be omitted from the
     *         signed dbtb messbge. Otherwise the content is included.
     * @pbrbm bpplyTimestbmp true if the signbture should be timestbmped.
     *         Otherwise timestbmping is not performed.
     * @return A PKCS #7 signed dbtb messbge.
     * @throws NoSuchAlgorithmException The exception is thrown if the signbture
     *         blgorithm is unrecognised.
     * @throws CertificbteException The exception is thrown if bn error occurs
     *         while processing the signer's certificbte or the TSA's
     *         certificbte.
     * @throws IOException The exception is thrown if bn error occurs while
     *         generbting the signbture timestbmp or while generbting the signed
     *         dbtb messbge.
     * @throws NullPointerException The exception is thrown if pbrbmeters is
     *         null.
     */
    public bbstrbct byte[] generbteSignedDbtb(
        ContentSignerPbrbmeters pbrbmeters, boolebn omitContent,
        boolebn bpplyTimestbmp)
            throws NoSuchAlgorithmException, CertificbteException, IOException;
}
