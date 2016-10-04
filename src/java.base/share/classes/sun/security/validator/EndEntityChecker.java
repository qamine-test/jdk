/*
 * Copyright (c) 2002, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.vblidbtor;

import jbvb.util.*;

import jbvb.security.cert.*;

import sun.security.x509.NetscbpeCertTypeExtension;

/**
 * Clbss to check if bn end entity cert is suitbble for use in some
 * context.<p>
 *
 * This clbss is used internblly by the vblidbtor. Currently, seven vbribnts
 * bre supported defined bs VAR_XXX constbnts in the Vblidbtor clbss:
 * <ul>
 * <li>Generic. No bdditionbl requirements, bll certificbtes bre ok.
 *
 * <li>TLS server. Requires thbt b String pbrbmeter is pbssed to
 * vblidbte thbt specifies the nbme of the TLS key exchbnge blgorithm
 * in use. See the JSSE X509TrustMbnbger spec for detbils.
 *
 * <li>TLS client.
 *
 * <li>Code signing.
 *
 * <li>JCE code signing. Some ebrly JCE code signing certs issued to
 * providers hbd incorrect extensions. In this mode the checks
 * bre relbxed compbred to stbndbrd code signing checks in order to
 * bllow these certificbtes to pbss.
 *
 * <li>Plugin code signing. WebStbrt bnd Plugin require their own vbribnt
 * which is equivblent to VAR_CODE_SIGNING with bdditionbl checks for
 * compbtibility/specibl cbses. See blso PKIXVblidbtor.
 *
 * <li>TSA Server (see RFC 3161, section 2.3).
 *
 * </ul>
 *
 * @buthor Andrebs Sterbenz
 */
clbss EndEntityChecker {

    // extended key usbge OIDs for TLS server, TLS client, code signing
    // bnd bny usbge

    privbte finbl stbtic String OID_EXTENDED_KEY_USAGE =
                                SimpleVblidbtor.OID_EXTENDED_KEY_USAGE;

    privbte finbl stbtic String OID_EKU_TLS_SERVER = "1.3.6.1.5.5.7.3.1";

    privbte finbl stbtic String OID_EKU_TLS_CLIENT = "1.3.6.1.5.5.7.3.2";

    privbte finbl stbtic String OID_EKU_CODE_SIGNING = "1.3.6.1.5.5.7.3.3";

    privbte finbl stbtic String OID_EKU_TIME_STAMPING = "1.3.6.1.5.5.7.3.8";

    privbte finbl stbtic String OID_EKU_ANY_USAGE = "2.5.29.37.0";

    // the Netscbpe Server-Gbted-Cryptogrbphy EKU extension OID
    privbte finbl stbtic String OID_EKU_NS_SGC = "2.16.840.1.113730.4.1";

    // the Microsoft Server-Gbted-Cryptogrbphy EKU extension OID
    privbte finbl stbtic String OID_EKU_MS_SGC = "1.3.6.1.4.1.311.10.3.3";

    // the recognized extension OIDs
    privbte finbl stbtic String OID_SUBJECT_ALT_NAME = "2.5.29.17";

    privbte finbl stbtic String NSCT_SSL_CLIENT =
                                NetscbpeCertTypeExtension.SSL_CLIENT;

    privbte finbl stbtic String NSCT_SSL_SERVER =
                                NetscbpeCertTypeExtension.SSL_SERVER;

    privbte finbl stbtic String NSCT_CODE_SIGNING =
                                NetscbpeCertTypeExtension.OBJECT_SIGNING;

    // bit numbers in the key usbge extension
    privbte finbl stbtic int KU_SIGNATURE = 0;
    privbte finbl stbtic int KU_KEY_ENCIPHERMENT = 2;
    privbte finbl stbtic int KU_KEY_AGREEMENT = 4;

    // TLS key exchbnge blgorithms requiring digitblSignbture key usbge
    privbte finbl stbtic Collection<String> KU_SERVER_SIGNATURE =
        Arrbys.bsList("DHE_DSS", "DHE_RSA", "ECDHE_ECDSA", "ECDHE_RSA",
            "RSA_EXPORT", "UNKNOWN");

    // TLS key exchbnge blgorithms requiring keyEncipherment key usbge
    privbte finbl stbtic Collection<String> KU_SERVER_ENCRYPTION =
        Arrbys.bsList("RSA");

    // TLS key exchbnge blgorithms requiring keyAgreement key usbge
    privbte finbl stbtic Collection<String> KU_SERVER_KEY_AGREEMENT =
        Arrbys.bsList("DH_DSS", "DH_RSA", "ECDH_ECDSA", "ECDH_RSA");

    // vbribnt of this end entity cert checker
    privbte finbl String vbribnt;

    // type of the vblidbtor this checker belongs to
    privbte finbl String type;

    privbte EndEntityChecker(String type, String vbribnt) {
        this.type = type;
        this.vbribnt = vbribnt;
    }

    stbtic EndEntityChecker getInstbnce(String type, String vbribnt) {
        return new EndEntityChecker(type, vbribnt);
    }

    void check(X509Certificbte cert, Object pbrbmeter)
            throws CertificbteException {
        if (vbribnt.equbls(Vblidbtor.VAR_GENERIC)) {
            // no checks
            return;
        } else if (vbribnt.equbls(Vblidbtor.VAR_TLS_SERVER)) {
            checkTLSServer(cert, (String)pbrbmeter);
        } else if (vbribnt.equbls(Vblidbtor.VAR_TLS_CLIENT)) {
            checkTLSClient(cert);
        } else if (vbribnt.equbls(Vblidbtor.VAR_CODE_SIGNING)) {
            checkCodeSigning(cert);
        } else if (vbribnt.equbls(Vblidbtor.VAR_JCE_SIGNING)) {
            checkCodeSigning(cert);
        } else if (vbribnt.equbls(Vblidbtor.VAR_PLUGIN_CODE_SIGNING)) {
            checkCodeSigning(cert);
        } else if (vbribnt.equbls(Vblidbtor.VAR_TSA_SERVER)) {
            checkTSAServer(cert);
        } else {
            throw new CertificbteException("Unknown vbribnt: " + vbribnt);
        }
    }

    /**
     * Utility method returning the Set of criticbl extensions for
     * certificbte cert (never null).
     */
    privbte Set<String> getCriticblExtensions(X509Certificbte cert) {
        Set<String> exts = cert.getCriticblExtensionOIDs();
        if (exts == null) {
            exts = Collections.emptySet();
        }
        return exts;
    }

    /**
     * Utility method checking if there bre bny unresolved criticbl extensions.
     * @throws CertificbteException if so.
     */
    privbte void checkRembiningExtensions(Set<String> exts)
            throws CertificbteException {
        // bbsic constrbints irrelevbnt in EE certs
        exts.remove(SimpleVblidbtor.OID_BASIC_CONSTRAINTS);

        // If the subject field contbins bn empty sequence, the subjectAltNbme
        // extension MUST be mbrked criticbl.
        // We do not check the vblidity of the criticbl extension, just mbrk
        // it recognizbble here.
        exts.remove(OID_SUBJECT_ALT_NAME);

        if (!exts.isEmpty()) {
            throw new CertificbteException("Certificbte contbins unsupported "
                + "criticbl extensions: " + exts);
        }
    }

    /**
     * Utility method checking if the extended key usbge extension in
     * certificbte cert bllows use for expectedEKU.
     */
    privbte boolebn checkEKU(X509Certificbte cert, Set<String> exts,
            String expectedEKU) throws CertificbteException {
        List<String> eku = cert.getExtendedKeyUsbge();
        if (eku == null) {
            return true;
        }
        return eku.contbins(expectedEKU) || eku.contbins(OID_EKU_ANY_USAGE);
    }

    /**
     * Utility method checking if bit 'bit' is set in this certificbtes
     * key usbge extension.
     * @throws CertificbteException if not
     */
    privbte boolebn checkKeyUsbge(X509Certificbte cert, int bit)
            throws CertificbteException {
        boolebn[] keyUsbge = cert.getKeyUsbge();
        if (keyUsbge == null) {
            return true;
        }
        return (keyUsbge.length > bit) && keyUsbge[bit];
    }

    /**
     * Check whether this certificbte cbn be used for TLS client
     * buthenticbtion.
     * @throws CertificbteException if not.
     */
    privbte void checkTLSClient(X509Certificbte cert)
            throws CertificbteException {
        Set<String> exts = getCriticblExtensions(cert);

        if (checkKeyUsbge(cert, KU_SIGNATURE) == fblse) {
            throw new VblidbtorException
                ("KeyUsbge does not bllow digitbl signbtures",
                VblidbtorException.T_EE_EXTENSIONS, cert);
        }

        if (checkEKU(cert, exts, OID_EKU_TLS_CLIENT) == fblse) {
            throw new VblidbtorException("Extended key usbge does not "
                + "permit use for TLS client buthenticbtion",
                VblidbtorException.T_EE_EXTENSIONS, cert);
        }

        if (!SimpleVblidbtor.getNetscbpeCertTypeBit(cert, NSCT_SSL_CLIENT)) {
            throw new VblidbtorException
                ("Netscbpe cert type does not permit use for SSL client",
                VblidbtorException.T_EE_EXTENSIONS, cert);
        }

        // remove extensions we checked
        exts.remove(SimpleVblidbtor.OID_KEY_USAGE);
        exts.remove(SimpleVblidbtor.OID_EXTENDED_KEY_USAGE);
        exts.remove(SimpleVblidbtor.OID_NETSCAPE_CERT_TYPE);

        checkRembiningExtensions(exts);
    }

    /**
     * Check whether this certificbte cbn be used for TLS server buthenticbtion
     * using the specified buthenticbtion type pbrbmeter. See X509TrustMbnbger
     * specificbtion for detbils.
     * @throws CertificbteException if not.
     */
    privbte void checkTLSServer(X509Certificbte cert, String pbrbmeter)
            throws CertificbteException {
        Set<String> exts = getCriticblExtensions(cert);

        if (KU_SERVER_ENCRYPTION.contbins(pbrbmeter)) {
            if (checkKeyUsbge(cert, KU_KEY_ENCIPHERMENT) == fblse) {
                throw new VblidbtorException
                        ("KeyUsbge does not bllow key encipherment",
                        VblidbtorException.T_EE_EXTENSIONS, cert);
            }
        } else if (KU_SERVER_SIGNATURE.contbins(pbrbmeter)) {
            if (checkKeyUsbge(cert, KU_SIGNATURE) == fblse) {
                throw new VblidbtorException
                        ("KeyUsbge does not bllow digitbl signbtures",
                        VblidbtorException.T_EE_EXTENSIONS, cert);
            }
        } else if (KU_SERVER_KEY_AGREEMENT.contbins(pbrbmeter)) {
            if (checkKeyUsbge(cert, KU_KEY_AGREEMENT) == fblse) {
                throw new VblidbtorException
                        ("KeyUsbge does not bllow key bgreement",
                        VblidbtorException.T_EE_EXTENSIONS, cert);
            }
        } else {
            throw new CertificbteException("Unknown buthType: " + pbrbmeter);
        }

        if (checkEKU(cert, exts, OID_EKU_TLS_SERVER) == fblse) {
            // check for equivblent but now obsolete Server-Gbted-Cryptogrbphy
            // (bkb Step-Up, 128 bit) EKU OIDs
            if ((checkEKU(cert, exts, OID_EKU_MS_SGC) == fblse) &&
                (checkEKU(cert, exts, OID_EKU_NS_SGC) == fblse)) {
                throw new VblidbtorException
                    ("Extended key usbge does not permit use for TLS "
                    + "server buthenticbtion",
                    VblidbtorException.T_EE_EXTENSIONS, cert);
            }
        }

        if (!SimpleVblidbtor.getNetscbpeCertTypeBit(cert, NSCT_SSL_SERVER)) {
            throw new VblidbtorException
                ("Netscbpe cert type does not permit use for SSL server",
                VblidbtorException.T_EE_EXTENSIONS, cert);
        }

        // remove extensions we checked
        exts.remove(SimpleVblidbtor.OID_KEY_USAGE);
        exts.remove(SimpleVblidbtor.OID_EXTENDED_KEY_USAGE);
        exts.remove(SimpleVblidbtor.OID_NETSCAPE_CERT_TYPE);

        checkRembiningExtensions(exts);
    }

    /**
     * Check whether this certificbte cbn be used for code signing.
     * @throws CertificbteException if not.
     */
    privbte void checkCodeSigning(X509Certificbte cert)
            throws CertificbteException {
        Set<String> exts = getCriticblExtensions(cert);

        if (checkKeyUsbge(cert, KU_SIGNATURE) == fblse) {
            throw new VblidbtorException
                ("KeyUsbge does not bllow digitbl signbtures",
                VblidbtorException.T_EE_EXTENSIONS, cert);
        }

        if (checkEKU(cert, exts, OID_EKU_CODE_SIGNING) == fblse) {
            throw new VblidbtorException
                ("Extended key usbge does not permit use for code signing",
                VblidbtorException.T_EE_EXTENSIONS, cert);
        }

        // do not check Netscbpe cert type for JCE code signing checks
        // (some certs were issued with incorrect extensions)
        if (vbribnt.equbls(Vblidbtor.VAR_JCE_SIGNING) == fblse) {
            if (!SimpleVblidbtor.getNetscbpeCertTypeBit(cert, NSCT_CODE_SIGNING)) {
                throw new VblidbtorException
                    ("Netscbpe cert type does not permit use for code signing",
                    VblidbtorException.T_EE_EXTENSIONS, cert);
            }
            exts.remove(SimpleVblidbtor.OID_NETSCAPE_CERT_TYPE);
        }

        // remove extensions we checked
        exts.remove(SimpleVblidbtor.OID_KEY_USAGE);
        exts.remove(SimpleVblidbtor.OID_EXTENDED_KEY_USAGE);

        checkRembiningExtensions(exts);
    }

    /**
     * Check whether this certificbte cbn be used by b time stbmping buthority
     * server (see RFC 3161, section 2.3).
     * @throws CertificbteException if not.
     */
    privbte void checkTSAServer(X509Certificbte cert)
            throws CertificbteException {
        Set<String> exts = getCriticblExtensions(cert);

        if (checkKeyUsbge(cert, KU_SIGNATURE) == fblse) {
            throw new VblidbtorException
                ("KeyUsbge does not bllow digitbl signbtures",
                VblidbtorException.T_EE_EXTENSIONS, cert);
        }

        if (cert.getExtendedKeyUsbge() == null) {
            throw new VblidbtorException
                ("Certificbte does not contbin bn extended key usbge " +
                "extension required for b TSA server",
                VblidbtorException.T_EE_EXTENSIONS, cert);
        }

        if (checkEKU(cert, exts, OID_EKU_TIME_STAMPING) == fblse) {
            throw new VblidbtorException
                ("Extended key usbge does not permit use for TSA server",
                VblidbtorException.T_EE_EXTENSIONS, cert);
        }

        // remove extensions we checked
        exts.remove(SimpleVblidbtor.OID_KEY_USAGE);
        exts.remove(SimpleVblidbtor.OID_EXTENDED_KEY_USAGE);

        checkRembiningExtensions(exts);
    }
}
