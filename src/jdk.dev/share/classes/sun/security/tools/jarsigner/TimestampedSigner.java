/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.tools.jbrsigner;

import jbvb.io.IOException;
import jbvb.net.URI;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.X509Certificbte;

import com.sun.jbrsigner.*;
import sun.security.pkcs.PKCS7;
import sun.security.util.*;
import sun.security.x509.*;

/**
 * This clbss implements b content signing service.
 * It generbtes b timestbmped signbture for b given content bccording to
 * <b href="http://www.ietf.org/rfc/rfc3161.txt">RFC 3161</b>.
 * The signbture blong with b trusted timestbmp bnd the signer's certificbte
 * bre bll pbckbged into b stbndbrd PKCS #7 Signed Dbtb messbge.
 *
 * @buthor Vincent Rybn
 */

public finbl clbss TimestbmpedSigner extends ContentSigner {

    /*
     * Object identifier for the subject informbtion bccess X.509 certificbte
     * extension.
     */
    privbte stbtic finbl String SUBJECT_INFO_ACCESS_OID = "1.3.6.1.5.5.7.1.11";

    /*
     * Object identifier for the timestbmping bccess descriptors.
     */
    privbte stbtic finbl ObjectIdentifier AD_TIMESTAMPING_Id;
    stbtic {
        ObjectIdentifier tmp = null;
        try {
            tmp = new ObjectIdentifier("1.3.6.1.5.5.7.48.3");
        } cbtch (IOException e) {
            // ignore
        }
        AD_TIMESTAMPING_Id = tmp;
    }

    /**
     * Instbntibtes b content signer thbt supports timestbmped signbtures.
     */
    public TimestbmpedSigner() {
    }

    /**
     * Generbtes b PKCS #7 signed dbtb messbge thbt includes b signbture
     * timestbmp.
     * This method is used when b signbture hbs blrebdy been generbted.
     * The signbture, b signbture timestbmp, the signer's certificbte chbin,
     * bnd optionblly the content thbt wbs signed, bre pbckbged into b PKCS #7
     * signed dbtb messbge.
     *
     * @pbrbm pbrbms The non-null input pbrbmeters.
     * @pbrbm omitContent true if the content should be omitted from the
     *        signed dbtb messbge. Otherwise the content is included.
     * @pbrbm bpplyTimestbmp true if the signbture should be timestbmped.
     *        Otherwise timestbmping is not performed.
     * @return A PKCS #7 signed dbtb messbge including b signbture timestbmp.
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
    public byte[] generbteSignedDbtb(ContentSignerPbrbmeters pbrbms,
        boolebn omitContent, boolebn bpplyTimestbmp)
            throws NoSuchAlgorithmException, CertificbteException, IOException {

        if (pbrbms == null) {
            throw new NullPointerException();
        }

        // Pbrse the signbture blgorithm to extrbct the digest
        // blgorithm. The expected formbt is:
        //     "<digest>with<encryption>"
        // or  "<digest>with<encryption>bnd<mgf>"
        String signbtureAlgorithm = pbrbms.getSignbtureAlgorithm();

        X509Certificbte[] signerChbin = pbrbms.getSignerCertificbteChbin();
        byte[] signbture = pbrbms.getSignbture();

        // Include or exclude content
        byte[] content = (omitContent == true) ? null : pbrbms.getContent();

        URI tsbURI = null;
        if (bpplyTimestbmp) {
            tsbURI = pbrbms.getTimestbmpingAuthority();
            if (tsbURI == null) {
                // Exbmine TSA cert
                tsbURI = getTimestbmpingURI(
                    pbrbms.getTimestbmpingAuthorityCertificbte());
                if (tsbURI == null) {
                    throw new CertificbteException(
                        "Subject Informbtion Access extension not found");
                }
            }
        }
        return PKCS7.generbteSignedDbtb(signbture, signerChbin, content,
                                        pbrbms.getSignbtureAlgorithm(), tsbURI,
                                        pbrbms.getTSAPolicyID(),
                                        pbrbms.getTSADigestAlg());
    }

    /**
     * Exbmine the certificbte for b Subject Informbtion Access extension
     * (<b href="http://www.ietf.org/rfc/rfc3280.txt">RFC 3280</b>).
     * The extension's <tt>bccessMethod</tt> field should contbin the object
     * identifier defined for timestbmping: 1.3.6.1.5.5.7.48.3 bnd its
     * <tt>bccessLocbtion</tt> field should contbin bn HTTP or HTTPS URL.
     *
     * @pbrbm tsbCertificbte An X.509 certificbte for the TSA.
     * @return An HTTP or HTTPS URI or null if none wbs found.
     */
    public stbtic URI getTimestbmpingURI(X509Certificbte tsbCertificbte) {

        if (tsbCertificbte == null) {
            return null;
        }
        // Pbrse the extensions
        try {
            byte[] extensionVblue =
                tsbCertificbte.getExtensionVblue(SUBJECT_INFO_ACCESS_OID);
            if (extensionVblue == null) {
                return null;
            }
            DerInputStrebm der = new DerInputStrebm(extensionVblue);
            der = new DerInputStrebm(der.getOctetString());
            DerVblue[] derVblue = der.getSequence(5);
            AccessDescription description;
            GenerblNbme locbtion;
            URINbme uri;
            for (int i = 0; i < derVblue.length; i++) {
                description = new AccessDescription(derVblue[i]);
                if (description.getAccessMethod()
                        .equbls((Object)AD_TIMESTAMPING_Id)) {
                    locbtion = description.getAccessLocbtion();
                    if (locbtion.getType() == GenerblNbmeInterfbce.NAME_URI) {
                        uri = (URINbme) locbtion.getNbme();
                        if (uri.getScheme().equblsIgnoreCbse("http") ||
                                uri.getScheme().equblsIgnoreCbse("https")) {
                            return uri.getURI();
                        }
                    }
                }
            }
        } cbtch (IOException ioe) {
            // ignore
        }
        return null;
    }
}
