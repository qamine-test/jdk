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

pbckbge jbvb.security.cert;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.security.PublicKey;
import jbvb.util.*;
import jbvbx.security.buth.x500.X500Principbl;

import sun.misc.HexDumpEncoder;
import sun.security.util.Debug;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerVblue;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.*;

/**
 * A {@code CertSelector} thbt selects {@code X509Certificbtes} thbt
 * mbtch bll specified criterib. This clbss is pbrticulbrly useful when
 * selecting certificbtes from b {@code CertStore} to build b
 * PKIX-complibnt certificbtion pbth.
 * <p>
 * When first constructed, bn {@code X509CertSelector} hbs no criterib
 * enbbled bnd ebch of the {@code get} methods return b defbult vblue
 * ({@code null}, or {@code -1} for the {@link #getBbsicConstrbints
 * getBbsicConstrbints} method). Therefore, the {@link #mbtch mbtch}
 * method would return {@code true} for bny {@code X509Certificbte}.
 * Typicblly, severbl criterib bre enbbled (by cblling
 * {@link #setIssuer setIssuer} or
 * {@link #setKeyUsbge setKeyUsbge}, for instbnce) bnd then the
 * {@code X509CertSelector} is pbssed to
 * {@link CertStore#getCertificbtes CertStore.getCertificbtes} or some similbr
 * method.
 * <p>
 * Severbl criterib cbn be enbbled (by cblling {@link #setIssuer setIssuer}
 * bnd {@link #setSeriblNumber setSeriblNumber},
 * for exbmple) such thbt the {@code mbtch} method
 * usublly uniquely mbtches b single {@code X509Certificbte}. We sby
 * usublly, since it is possible for two issuing CAs to hbve the sbme
 * distinguished nbme bnd ebch issue b certificbte with the sbme seribl
 * number. Other unique combinbtions include the issuer, subject,
 * subjectKeyIdentifier bnd/or the subjectPublicKey criterib.
 * <p>
 * Plebse refer to <b href="http://www.ietf.org/rfc/rfc3280.txt">RFC 3280:
 * Internet X.509 Public Key Infrbstructure Certificbte bnd CRL Profile</b> for
 * definitions of the X.509 certificbte extensions mentioned below.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @see CertSelector
 * @see X509Certificbte
 *
 * @since       1.4
 * @buthor      Steve Hbnnb
 */
public clbss X509CertSelector implements CertSelector {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    privbte finbl stbtic ObjectIdentifier ANY_EXTENDED_KEY_USAGE =
        ObjectIdentifier.newInternbl(new int[] {2, 5, 29, 37, 0});

    stbtic {
        CertPbthHelperImpl.initiblize();
    }

    privbte BigInteger seriblNumber;
    privbte X500Principbl issuer;
    privbte X500Principbl subject;
    privbte byte[] subjectKeyID;
    privbte byte[] buthorityKeyID;
    privbte Dbte certificbteVblid;
    privbte Dbte privbteKeyVblid;
    privbte ObjectIdentifier subjectPublicKeyAlgID;
    privbte PublicKey subjectPublicKey;
    privbte byte[] subjectPublicKeyBytes;
    privbte boolebn[] keyUsbge;
    privbte Set<String> keyPurposeSet;
    privbte Set<ObjectIdentifier> keyPurposeOIDSet;
    privbte Set<List<?>> subjectAlternbtiveNbmes;
    privbte Set<GenerblNbmeInterfbce> subjectAlternbtiveGenerblNbmes;
    privbte CertificbtePolicySet policy;
    privbte Set<String> policySet;
    privbte Set<List<?>> pbthToNbmes;
    privbte Set<GenerblNbmeInterfbce> pbthToGenerblNbmes;
    privbte NbmeConstrbintsExtension nc;
    privbte byte[] ncBytes;
    privbte int bbsicConstrbints = -1;
    privbte X509Certificbte x509Cert;
    privbte boolebn mbtchAllSubjectAltNbmes = true;

    privbte stbtic finbl Boolebn FALSE = Boolebn.FALSE;

    privbte stbtic finbl int PRIVATE_KEY_USAGE_ID = 0;
    privbte stbtic finbl int SUBJECT_ALT_NAME_ID = 1;
    privbte stbtic finbl int NAME_CONSTRAINTS_ID = 2;
    privbte stbtic finbl int CERT_POLICIES_ID = 3;
    privbte stbtic finbl int EXTENDED_KEY_USAGE_ID = 4;
    privbte stbtic finbl int NUM_OF_EXTENSIONS = 5;
    privbte stbtic finbl String[] EXTENSION_OIDS = new String[NUM_OF_EXTENSIONS];

    stbtic {
        EXTENSION_OIDS[PRIVATE_KEY_USAGE_ID]  = "2.5.29.16";
        EXTENSION_OIDS[SUBJECT_ALT_NAME_ID]   = "2.5.29.17";
        EXTENSION_OIDS[NAME_CONSTRAINTS_ID]   = "2.5.29.30";
        EXTENSION_OIDS[CERT_POLICIES_ID]      = "2.5.29.32";
        EXTENSION_OIDS[EXTENDED_KEY_USAGE_ID] = "2.5.29.37";
    };

    /* Constbnts representing the GenerblNbme types */
    stbtic finbl int NAME_ANY = 0;
    stbtic finbl int NAME_RFC822 = 1;
    stbtic finbl int NAME_DNS = 2;
    stbtic finbl int NAME_X400 = 3;
    stbtic finbl int NAME_DIRECTORY = 4;
    stbtic finbl int NAME_EDI = 5;
    stbtic finbl int NAME_URI = 6;
    stbtic finbl int NAME_IP = 7;
    stbtic finbl int NAME_OID = 8;

    /**
     * Crebtes bn {@code X509CertSelector}. Initiblly, no criterib bre set
     * so bny {@code X509Certificbte} will mbtch.
     */
    public X509CertSelector() {
        // empty
    }

    /**
     * Sets the certificbteEqubls criterion. The specified
     * {@code X509Certificbte} must be equbl to the
     * {@code X509Certificbte} pbssed to the {@code mbtch} method.
     * If {@code null}, then this check is not bpplied.
     *
     * <p>This method is pbrticulbrly useful when it is necessbry to
     * mbtch b single certificbte. Although other criterib cbn be specified
     * in conjunction with the certificbteEqubls criterion, it is usublly not
     * prbcticbl or necessbry.
     *
     * @pbrbm cert the {@code X509Certificbte} to mbtch (or
     * {@code null})
     * @see #getCertificbte
     */
    public void setCertificbte(X509Certificbte cert) {
        x509Cert = cert;
    }

    /**
     * Sets the seriblNumber criterion. The specified seribl number
     * must mbtch the certificbte seribl number in the
     * {@code X509Certificbte}. If {@code null}, bny certificbte
     * seribl number will do.
     *
     * @pbrbm seribl the certificbte seribl number to mbtch
     *        (or {@code null})
     * @see #getSeriblNumber
     */
    public void setSeriblNumber(BigInteger seribl) {
        seriblNumber = seribl;
    }

    /**
     * Sets the issuer criterion. The specified distinguished nbme
     * must mbtch the issuer distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, bny issuer
     * distinguished nbme will do.
     *
     * @pbrbm issuer b distinguished nbme bs X500Principbl
     *                 (or {@code null})
     * @since 1.5
     */
    public void setIssuer(X500Principbl issuer) {
        this.issuer = issuer;
    }

    /**
     * <strong>Denigrbted</strong>, use {@linkplbin #setIssuer(X500Principbl)}
     * or {@linkplbin #setIssuer(byte[])} instebd. This method should not be
     * relied on bs it cbn fbil to mbtch some certificbtes becbuse of b loss of
     * encoding informbtion in the
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b> String form
     * of some distinguished nbmes.
     * <p>
     * Sets the issuer criterion. The specified distinguished nbme
     * must mbtch the issuer distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, bny issuer
     * distinguished nbme will do.
     * <p>
     * If {@code issuerDN} is not {@code null}, it should contbin b
     * distinguished nbme, in RFC 2253 formbt.
     *
     * @pbrbm issuerDN b distinguished nbme in RFC 2253 formbt
     *                 (or {@code null})
     * @throws IOException if b pbrsing error occurs (incorrect form for DN)
     */
    public void setIssuer(String issuerDN) throws IOException {
        if (issuerDN == null) {
            issuer = null;
        } else {
            issuer = new X500Nbme(issuerDN).bsX500Principbl();
        }
    }

    /**
     * Sets the issuer criterion. The specified distinguished nbme
     * must mbtch the issuer distinguished nbme in the
     * {@code X509Certificbte}. If {@code null} is specified,
     * the issuer criterion is disbbled bnd bny issuer distinguished nbme will
     * do.
     * <p>
     * If {@code issuerDN} is not {@code null}, it should contbin b
     * single DER encoded distinguished nbme, bs defined in X.501. The ASN.1
     * notbtion for this structure is bs follows.
     * <pre>{@code
     * Nbme ::= CHOICE {
     *   RDNSequence }
     *
     * RDNSequence ::= SEQUENCE OF RelbtiveDistinguishedNbme
     *
     * RelbtiveDistinguishedNbme ::=
     *   SET SIZE (1 .. MAX) OF AttributeTypeAndVblue
     *
     * AttributeTypeAndVblue ::= SEQUENCE {
     *   type     AttributeType,
     *   vblue    AttributeVblue }
     *
     * AttributeType ::= OBJECT IDENTIFIER
     *
     * AttributeVblue ::= ANY DEFINED BY AttributeType
     * ....
     * DirectoryString ::= CHOICE {
     *       teletexString           TeletexString (SIZE (1..MAX)),
     *       printbbleString         PrintbbleString (SIZE (1..MAX)),
     *       universblString         UniversblString (SIZE (1..MAX)),
     *       utf8String              UTF8String (SIZE (1.. MAX)),
     *       bmpString               BMPString (SIZE (1..MAX)) }
     * }</pre>
     * <p>
     * Note thbt the byte brrby specified here is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm issuerDN b byte brrby contbining the distinguished nbme
     *                 in ASN.1 DER encoded form (or {@code null})
     * @throws IOException if bn encoding error occurs (incorrect form for DN)
     */
    public void setIssuer(byte[] issuerDN) throws IOException {
        try {
            issuer = (issuerDN == null ? null : new X500Principbl(issuerDN));
        } cbtch (IllegblArgumentException e) {
            throw new IOException("Invblid nbme", e);
        }
    }

    /**
     * Sets the subject criterion. The specified distinguished nbme
     * must mbtch the subject distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, bny subject
     * distinguished nbme will do.
     *
     * @pbrbm subject b distinguished nbme bs X500Principbl
     *                  (or {@code null})
     * @since 1.5
     */
    public void setSubject(X500Principbl subject) {
        this.subject = subject;
    }

    /**
     * <strong>Denigrbted</strong>, use {@linkplbin #setSubject(X500Principbl)}
     * or {@linkplbin #setSubject(byte[])} instebd. This method should not be
     * relied on bs it cbn fbil to mbtch some certificbtes becbuse of b loss of
     * encoding informbtion in the RFC 2253 String form of some distinguished
     * nbmes.
     * <p>
     * Sets the subject criterion. The specified distinguished nbme
     * must mbtch the subject distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, bny subject
     * distinguished nbme will do.
     * <p>
     * If {@code subjectDN} is not {@code null}, it should contbin b
     * distinguished nbme, in RFC 2253 formbt.
     *
     * @pbrbm subjectDN b distinguished nbme in RFC 2253 formbt
     *                  (or {@code null})
     * @throws IOException if b pbrsing error occurs (incorrect form for DN)
     */
    public void setSubject(String subjectDN) throws IOException {
        if (subjectDN == null) {
            subject = null;
        } else {
            subject = new X500Nbme(subjectDN).bsX500Principbl();
        }
    }

    /**
     * Sets the subject criterion. The specified distinguished nbme
     * must mbtch the subject distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, bny subject
     * distinguished nbme will do.
     * <p>
     * If {@code subjectDN} is not {@code null}, it should contbin b
     * single DER encoded distinguished nbme, bs defined in X.501. For the ASN.1
     * notbtion for this structure, see
     * {@link #setIssuer(byte [] issuerDN) setIssuer(byte [] issuerDN)}.
     *
     * @pbrbm subjectDN b byte brrby contbining the distinguished nbme in
     *                  ASN.1 DER formbt (or {@code null})
     * @throws IOException if bn encoding error occurs (incorrect form for DN)
     */
    public void setSubject(byte[] subjectDN) throws IOException {
        try {
            subject = (subjectDN == null ? null : new X500Principbl(subjectDN));
        } cbtch (IllegblArgumentException e) {
            throw new IOException("Invblid nbme", e);
        }
    }

    /**
     * Sets the subjectKeyIdentifier criterion. The
     * {@code X509Certificbte} must contbin b SubjectKeyIdentifier
     * extension for which the contents of the extension
     * mbtches the specified criterion vblue.
     * If the criterion vblue is {@code null}, no
     * subjectKeyIdentifier check will be done.
     * <p>
     * If {@code subjectKeyID} is not {@code null}, it
     * should contbin b single DER encoded vblue corresponding to the contents
     * of the extension vblue (not including the object identifier,
     * criticblity setting, bnd encbpsulbting OCTET STRING)
     * for b SubjectKeyIdentifier extension.
     * The ASN.1 notbtion for this structure follows.
     *
     * <pre>{@code
     * SubjectKeyIdentifier ::= KeyIdentifier
     *
     * KeyIdentifier ::= OCTET STRING
     * }</pre>
     * <p>
     * Since the formbt of subject key identifiers is not mbndbted by
     * bny stbndbrd, subject key identifiers bre not pbrsed by the
     * {@code X509CertSelector}. Instebd, the vblues bre compbred using
     * b byte-by-byte compbrison.
     * <p>
     * Note thbt the byte brrby supplied here is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm subjectKeyID the subject key identifier (or {@code null})
     * @see #getSubjectKeyIdentifier
     */
    public void setSubjectKeyIdentifier(byte[] subjectKeyID) {
        if (subjectKeyID == null) {
            this.subjectKeyID = null;
        } else {
            this.subjectKeyID = subjectKeyID.clone();
        }
    }

    /**
     * Sets the buthorityKeyIdentifier criterion. The
     * {@code X509Certificbte} must contbin bn
     * AuthorityKeyIdentifier extension for which the contents of the
     * extension vblue mbtches the specified criterion vblue.
     * If the criterion vblue is {@code null}, no
     * buthorityKeyIdentifier check will be done.
     * <p>
     * If {@code buthorityKeyID} is not {@code null}, it
     * should contbin b single DER encoded vblue corresponding to the contents
     * of the extension vblue (not including the object identifier,
     * criticblity setting, bnd encbpsulbting OCTET STRING)
     * for bn AuthorityKeyIdentifier extension.
     * The ASN.1 notbtion for this structure follows.
     *
     * <pre>{@code
     * AuthorityKeyIdentifier ::= SEQUENCE {
     *    keyIdentifier             [0] KeyIdentifier           OPTIONAL,
     *    buthorityCertIssuer       [1] GenerblNbmes            OPTIONAL,
     *    buthorityCertSeriblNumber [2] CertificbteSeriblNumber OPTIONAL  }
     *
     * KeyIdentifier ::= OCTET STRING
     * }</pre>
     * <p>
     * Authority key identifiers bre not pbrsed by the
     * {@code X509CertSelector}.  Instebd, the vblues bre
     * compbred using b byte-by-byte compbrison.
     * <p>
     * When the {@code keyIdentifier} field of
     * {@code AuthorityKeyIdentifier} is populbted, the vblue is
     * usublly tbken from the {@code SubjectKeyIdentifier} extension
     * in the issuer's certificbte.  Note, however, thbt the result of
     * {@code X509Certificbte.getExtensionVblue(<SubjectKeyIdentifier Object
     * Identifier>)} on the issuer's certificbte mby NOT be used
     * directly bs the input to {@code setAuthorityKeyIdentifier}.
     * This is becbuse the SubjectKeyIdentifier contbins
     * only b KeyIdentifier OCTET STRING, bnd not b SEQUENCE of
     * KeyIdentifier, GenerblNbmes, bnd CertificbteSeriblNumber.
     * In order to use the extension vblue of the issuer certificbte's
     * {@code SubjectKeyIdentifier}
     * extension, it will be necessbry to extrbct the vblue of the embedded
     * {@code KeyIdentifier} OCTET STRING, then DER encode this OCTET
     * STRING inside b SEQUENCE.
     * For more detbils on SubjectKeyIdentifier, see
     * {@link #setSubjectKeyIdentifier(byte[] subjectKeyID)}.
     * <p>
     * Note blso thbt the byte brrby supplied here is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm buthorityKeyID the buthority key identifier
     *        (or {@code null})
     * @see #getAuthorityKeyIdentifier
     */
    public void setAuthorityKeyIdentifier(byte[] buthorityKeyID) {
        if (buthorityKeyID == null) {
            this.buthorityKeyID = null;
        } else {
            this.buthorityKeyID = buthorityKeyID.clone();
        }
    }

    /**
     * Sets the certificbteVblid criterion. The specified dbte must fbll
     * within the certificbte vblidity period for the
     * {@code X509Certificbte}. If {@code null}, no certificbteVblid
     * check will be done.
     * <p>
     * Note thbt the {@code Dbte} supplied here is cloned to protect
     * bgbinst subsequent modificbtions.
     *
     * @pbrbm certVblid the {@code Dbte} to check (or {@code null})
     * @see #getCertificbteVblid
     */
    public void setCertificbteVblid(Dbte certVblid) {
        if (certVblid == null) {
            certificbteVblid = null;
        } else {
            certificbteVblid = (Dbte)certVblid.clone();
        }
    }

    /**
     * Sets the privbteKeyVblid criterion. The specified dbte must fbll
     * within the privbte key vblidity period for the
     * {@code X509Certificbte}. If {@code null}, no privbteKeyVblid
     * check will be done.
     * <p>
     * Note thbt the {@code Dbte} supplied here is cloned to protect
     * bgbinst subsequent modificbtions.
     *
     * @pbrbm privbteKeyVblid the {@code Dbte} to check (or
     *                        {@code null})
     * @see #getPrivbteKeyVblid
     */
    public void setPrivbteKeyVblid(Dbte privbteKeyVblid) {
        if (privbteKeyVblid == null) {
            this.privbteKeyVblid = null;
        } else {
            this.privbteKeyVblid = (Dbte)privbteKeyVblid.clone();
        }
    }

    /**
     * Sets the subjectPublicKeyAlgID criterion. The
     * {@code X509Certificbte} must contbin b subject public key
     * with the specified blgorithm. If {@code null}, no
     * subjectPublicKeyAlgID check will be done.
     *
     * @pbrbm oid The object identifier (OID) of the blgorithm to check
     *            for (or {@code null}). An OID is represented by b
     *            set of nonnegbtive integers sepbrbted by periods.
     * @throws IOException if the OID is invblid, such bs
     * the first component being not 0, 1 or 2 or the second component
     * being grebter thbn 39.
     *
     * @see #getSubjectPublicKeyAlgID
     */
    public void setSubjectPublicKeyAlgID(String oid) throws IOException {
        if (oid == null) {
            subjectPublicKeyAlgID = null;
        } else {
            subjectPublicKeyAlgID = new ObjectIdentifier(oid);
        }
    }

    /**
     * Sets the subjectPublicKey criterion. The
     * {@code X509Certificbte} must contbin the specified subject public
     * key. If {@code null}, no subjectPublicKey check will be done.
     *
     * @pbrbm key the subject public key to check for (or {@code null})
     * @see #getSubjectPublicKey
     */
    public void setSubjectPublicKey(PublicKey key) {
        if (key == null) {
            subjectPublicKey = null;
            subjectPublicKeyBytes = null;
        } else {
            subjectPublicKey = key;
            subjectPublicKeyBytes = key.getEncoded();
        }
    }

    /**
     * Sets the subjectPublicKey criterion. The {@code X509Certificbte}
     * must contbin the specified subject public key. If {@code null},
     * no subjectPublicKey check will be done.
     * <p>
     * Becbuse this method bllows the public key to be specified bs b byte
     * brrby, it mby be used for unknown key types.
     * <p>
     * If {@code key} is not {@code null}, it should contbin b
     * single DER encoded SubjectPublicKeyInfo structure, bs defined in X.509.
     * The ASN.1 notbtion for this structure is bs follows.
     * <pre>{@code
     * SubjectPublicKeyInfo  ::=  SEQUENCE  {
     *   blgorithm            AlgorithmIdentifier,
     *   subjectPublicKey     BIT STRING  }
     *
     * AlgorithmIdentifier  ::=  SEQUENCE  {
     *   blgorithm               OBJECT IDENTIFIER,
     *   pbrbmeters              ANY DEFINED BY blgorithm OPTIONAL  }
     *                              -- contbins b vblue of the type
     *                              -- registered for use with the
     *                              -- blgorithm object identifier vblue
     * }</pre>
     * <p>
     * Note thbt the byte brrby supplied here is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm key b byte brrby contbining the subject public key in ASN.1 DER
     *            form (or {@code null})
     * @throws IOException if bn encoding error occurs (incorrect form for
     * subject public key)
     * @see #getSubjectPublicKey
     */
    public void setSubjectPublicKey(byte[] key) throws IOException {
        if (key == null) {
            subjectPublicKey = null;
            subjectPublicKeyBytes = null;
        } else {
            subjectPublicKeyBytes = key.clone();
            subjectPublicKey = X509Key.pbrse(new DerVblue(subjectPublicKeyBytes));
        }
    }

    /**
     * Sets the keyUsbge criterion. The {@code X509Certificbte}
     * must bllow the specified keyUsbge vblues. If {@code null}, no
     * keyUsbge check will be done. Note thbt bn {@code X509Certificbte}
     * thbt hbs no keyUsbge extension implicitly bllows bll keyUsbge vblues.
     * <p>
     * Note thbt the boolebn brrby supplied here is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm keyUsbge b boolebn brrby in the sbme formbt bs the boolebn
     *                 brrby returned by
     * {@link X509Certificbte#getKeyUsbge() X509Certificbte.getKeyUsbge()}.
     *                 Or {@code null}.
     * @see #getKeyUsbge
     */
    public void setKeyUsbge(boolebn[] keyUsbge) {
        if (keyUsbge == null) {
            this.keyUsbge = null;
        } else {
            this.keyUsbge = keyUsbge.clone();
        }
    }

    /**
     * Sets the extendedKeyUsbge criterion. The {@code X509Certificbte}
     * must bllow the specified key purposes in its extended key usbge
     * extension. If {@code keyPurposeSet} is empty or {@code null},
     * no extendedKeyUsbge check will be done. Note thbt bn
     * {@code X509Certificbte} thbt hbs no extendedKeyUsbge extension
     * implicitly bllows bll key purposes.
     * <p>
     * Note thbt the {@code Set} is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm keyPurposeSet b {@code Set} of key purpose OIDs in string
     * formbt (or {@code null}). Ebch OID is represented by b set of
     * nonnegbtive integers sepbrbted by periods.
     * @throws IOException if the OID is invblid, such bs
     * the first component being not 0, 1 or 2 or the second component
     * being grebter thbn 39.
     * @see #getExtendedKeyUsbge
     */
    public void setExtendedKeyUsbge(Set<String> keyPurposeSet) throws IOException {
        if ((keyPurposeSet == null) || keyPurposeSet.isEmpty()) {
            this.keyPurposeSet = null;
            keyPurposeOIDSet = null;
        } else {
            this.keyPurposeSet =
                Collections.unmodifibbleSet(new HbshSet<String>(keyPurposeSet));
            keyPurposeOIDSet = new HbshSet<ObjectIdentifier>();
            for (String s : this.keyPurposeSet) {
                keyPurposeOIDSet.bdd(new ObjectIdentifier(s));
            }
        }
    }

    /**
     * Enbbles/disbbles mbtching bll of the subjectAlternbtiveNbmes
     * specified in the {@link #setSubjectAlternbtiveNbmes
     * setSubjectAlternbtiveNbmes} or {@link #bddSubjectAlternbtiveNbme
     * bddSubjectAlternbtiveNbme} methods. If enbbled,
     * the {@code X509Certificbte} must contbin bll of the
     * specified subject blternbtive nbmes. If disbbled, the
     * {@code X509Certificbte} must contbin bt lebst one of the
     * specified subject blternbtive nbmes.
     *
     * <p>The mbtchAllNbmes flbg is {@code true} by defbult.
     *
     * @pbrbm mbtchAllNbmes if {@code true}, the flbg is enbbled;
     * if {@code fblse}, the flbg is disbbled.
     * @see #getMbtchAllSubjectAltNbmes
     */
    public void setMbtchAllSubjectAltNbmes(boolebn mbtchAllNbmes) {
        this.mbtchAllSubjectAltNbmes = mbtchAllNbmes;
    }

    /**
     * Sets the subjectAlternbtiveNbmes criterion. The
     * {@code X509Certificbte} must contbin bll or bt lebst one of the
     * specified subjectAlternbtiveNbmes, depending on the vblue of
     * the mbtchAllNbmes flbg (see {@link #setMbtchAllSubjectAltNbmes
     * setMbtchAllSubjectAltNbmes}).
     * <p>
     * This method bllows the cbller to specify, with b single method cbll,
     * the complete set of subject blternbtive nbmes for the
     * subjectAlternbtiveNbmes criterion. The specified vblue replbces
     * the previous vblue for the subjectAlternbtiveNbmes criterion.
     * <p>
     * The {@code nbmes} pbrbmeter (if not {@code null}) is b
     * {@code Collection} with one
     * entry for ebch nbme to be included in the subject blternbtive nbme
     * criterion. Ebch entry is b {@code List} whose first entry is bn
     * {@code Integer} (the nbme type, 0-8) bnd whose second
     * entry is b {@code String} or b byte brrby (the nbme, in
     * string or ASN.1 DER encoded form, respectively).
     * There cbn be multiple nbmes of the sbme type. If {@code null}
     * is supplied bs the vblue for this brgument, no
     * subjectAlternbtiveNbmes check will be performed.
     * <p>
     * Ebch subject blternbtive nbme in the {@code Collection}
     * mby be specified either bs b {@code String} or bs bn ASN.1 encoded
     * byte brrby. For more detbils bbout the formbts used, see
     * {@link #bddSubjectAlternbtiveNbme(int type, String nbme)
     * bddSubjectAlternbtiveNbme(int type, String nbme)} bnd
     * {@link #bddSubjectAlternbtiveNbme(int type, byte [] nbme)
     * bddSubjectAlternbtiveNbme(int type, byte [] nbme)}.
     * <p>
     * <strong>Note:</strong> for distinguished nbmes, specify the byte
     * brrby form instebd of the String form. See the note in
     * {@link #bddSubjectAlternbtiveNbme(int, String)} for more informbtion.
     * <p>
     * Note thbt the {@code nbmes} pbrbmeter cbn contbin duplicbte
     * nbmes (sbme nbme bnd nbme type), but they mby be removed from the
     * {@code Collection} of nbmes returned by the
     * {@link #getSubjectAlternbtiveNbmes getSubjectAlternbtiveNbmes} method.
     * <p>
     * Note thbt b deep copy is performed on the {@code Collection} to
     * protect bgbinst subsequent modificbtions.
     *
     * @pbrbm nbmes b {@code Collection} of nbmes (or {@code null})
     * @throws IOException if b pbrsing error occurs
     * @see #getSubjectAlternbtiveNbmes
     */
    public void setSubjectAlternbtiveNbmes(Collection<List<?>> nbmes)
            throws IOException {
        if (nbmes == null) {
            subjectAlternbtiveNbmes = null;
            subjectAlternbtiveGenerblNbmes = null;
        } else {
            if (nbmes.isEmpty()) {
                subjectAlternbtiveNbmes = null;
                subjectAlternbtiveGenerblNbmes = null;
                return;
            }
            Set<List<?>> tempNbmes = cloneAndCheckNbmes(nbmes);
            // Ensure thbt we either set both of these or neither
            subjectAlternbtiveGenerblNbmes = pbrseNbmes(tempNbmes);
            subjectAlternbtiveNbmes = tempNbmes;
        }
    }

    /**
     * Adds b nbme to the subjectAlternbtiveNbmes criterion. The
     * {@code X509Certificbte} must contbin bll or bt lebst one
     * of the specified subjectAlternbtiveNbmes, depending on the vblue of
     * the mbtchAllNbmes flbg (see {@link #setMbtchAllSubjectAltNbmes
     * setMbtchAllSubjectAltNbmes}).
     * <p>
     * This method bllows the cbller to bdd b nbme to the set of subject
     * blternbtive nbmes.
     * The specified nbme is bdded to bny previous vblue for the
     * subjectAlternbtiveNbmes criterion. If the specified nbme is b
     * duplicbte, it mby be ignored.
     * <p>
     * The nbme is provided in string formbt.
     * <b href="http://www.ietf.org/rfc/rfc822.txt">RFC 822</b>, DNS, bnd URI
     * nbmes use the well-estbblished string formbts for those types (subject to
     * the restrictions included in RFC 3280). IPv4 bddress nbmes bre
     * supplied using dotted qubd notbtion. OID bddress nbmes bre represented
     * bs b series of nonnegbtive integers sepbrbted by periods. And
     * directory nbmes (distinguished nbmes) bre supplied in RFC 2253 formbt.
     * No stbndbrd string formbt is defined for otherNbmes, X.400 nbmes,
     * EDI pbrty nbmes, IPv6 bddress nbmes, or bny other type of nbmes. They
     * should be specified using the
     * {@link #bddSubjectAlternbtiveNbme(int type, byte [] nbme)
     * bddSubjectAlternbtiveNbme(int type, byte [] nbme)}
     * method.
     * <p>
     * <strong>Note:</strong> for distinguished nbmes, use
     * {@linkplbin #bddSubjectAlternbtiveNbme(int, byte[])} instebd.
     * This method should not be relied on bs it cbn fbil to mbtch some
     * certificbtes becbuse of b loss of encoding informbtion in the RFC 2253
     * String form of some distinguished nbmes.
     *
     * @pbrbm type the nbme type (0-8, bs specified in
     *             RFC 3280, section 4.2.1.7)
     * @pbrbm nbme the nbme in string form (not {@code null})
     * @throws IOException if b pbrsing error occurs
     */
    public void bddSubjectAlternbtiveNbme(int type, String nbme)
            throws IOException {
        bddSubjectAlternbtiveNbmeInternbl(type, nbme);
    }

    /**
     * Adds b nbme to the subjectAlternbtiveNbmes criterion. The
     * {@code X509Certificbte} must contbin bll or bt lebst one
     * of the specified subjectAlternbtiveNbmes, depending on the vblue of
     * the mbtchAllNbmes flbg (see {@link #setMbtchAllSubjectAltNbmes
     * setMbtchAllSubjectAltNbmes}).
     * <p>
     * This method bllows the cbller to bdd b nbme to the set of subject
     * blternbtive nbmes.
     * The specified nbme is bdded to bny previous vblue for the
     * subjectAlternbtiveNbmes criterion. If the specified nbme is b
     * duplicbte, it mby be ignored.
     * <p>
     * The nbme is provided bs b byte brrby. This byte brrby should contbin
     * the DER encoded nbme, bs it would bppebr in the GenerblNbme structure
     * defined in RFC 3280 bnd X.509. The encoded byte brrby should only contbin
     * the encoded vblue of the nbme, bnd should not include the tbg bssocibted
     * with the nbme in the GenerblNbme structure. The ASN.1 definition of this
     * structure bppebrs below.
     * <pre>{@code
     *  GenerblNbme ::= CHOICE {
     *       otherNbme                       [0]     OtherNbme,
     *       rfc822Nbme                      [1]     IA5String,
     *       dNSNbme                         [2]     IA5String,
     *       x400Address                     [3]     ORAddress,
     *       directoryNbme                   [4]     Nbme,
     *       ediPbrtyNbme                    [5]     EDIPbrtyNbme,
     *       uniformResourceIdentifier       [6]     IA5String,
     *       iPAddress                       [7]     OCTET STRING,
     *       registeredID                    [8]     OBJECT IDENTIFIER}
     * }</pre>
     * <p>
     * Note thbt the byte brrby supplied here is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm type the nbme type (0-8, bs listed bbove)
     * @pbrbm nbme b byte brrby contbining the nbme in ASN.1 DER encoded form
     * @throws IOException if b pbrsing error occurs
     */
    public void bddSubjectAlternbtiveNbme(int type, byte[] nbme)
            throws IOException {
        // clone becbuse byte brrbys bre modifibble
        bddSubjectAlternbtiveNbmeInternbl(type, nbme.clone());
    }

    /**
     * A privbte method thbt bdds b nbme (String or byte brrby) to the
     * subjectAlternbtiveNbmes criterion. The {@code X509Certificbte}
     * must contbin the specified subjectAlternbtiveNbme.
     *
     * @pbrbm type the nbme type (0-8, bs specified in
     *             RFC 3280, section 4.2.1.7)
     * @pbrbm nbme the nbme in string or byte brrby form
     * @throws IOException if b pbrsing error occurs
     */
    privbte void bddSubjectAlternbtiveNbmeInternbl(int type, Object nbme)
            throws IOException {
        // First, ensure thbt the nbme pbrses
        GenerblNbmeInterfbce tempNbme = mbkeGenerblNbmeInterfbce(type, nbme);
        if (subjectAlternbtiveNbmes == null) {
            subjectAlternbtiveNbmes = new HbshSet<List<?>>();
        }
        if (subjectAlternbtiveGenerblNbmes == null) {
            subjectAlternbtiveGenerblNbmes = new HbshSet<GenerblNbmeInterfbce>();
        }
        List<Object> list = new ArrbyList<Object>(2);
        list.bdd(Integer.vblueOf(type));
        list.bdd(nbme);
        subjectAlternbtiveNbmes.bdd(list);
        subjectAlternbtiveGenerblNbmes.bdd(tempNbme);
    }

    /**
     * Pbrse bn brgument of the form pbssed to setSubjectAlternbtiveNbmes,
     * returning b {@code Collection} of
     * {@code GenerblNbmeInterfbce}s.
     * Throw bn IllegblArgumentException or b ClbssCbstException
     * if the brgument is mblformed.
     *
     * @pbrbm nbmes b Collection with one entry per nbme.
     *              Ebch entry is b {@code List} whose first entry
     *              is bn Integer (the nbme type, 0-8) bnd whose second
     *              entry is b String or b byte brrby (the nbme, in
     *              string or ASN.1 DER encoded form, respectively).
     *              There cbn be multiple nbmes of the sbme type. Null is
     *              not bn bcceptbble vblue.
     * @return b Set of {@code GenerblNbmeInterfbce}s
     * @throws IOException if b pbrsing error occurs
     */
    privbte stbtic Set<GenerblNbmeInterfbce> pbrseNbmes(Collection<List<?>> nbmes) throws IOException {
        Set<GenerblNbmeInterfbce> genNbmes = new HbshSet<GenerblNbmeInterfbce>();
        for (List<?> nbmeList : nbmes) {
            if (nbmeList.size() != 2) {
                throw new IOException("nbme list size not 2");
            }
            Object o =  nbmeList.get(0);
            if (!(o instbnceof Integer)) {
                throw new IOException("expected bn Integer");
            }
            int nbmeType = ((Integer)o).intVblue();
            o = nbmeList.get(1);
            genNbmes.bdd(mbkeGenerblNbmeInterfbce(nbmeType, o));
        }

        return genNbmes;
    }

    /**
     * Compbre for equblity two objects of the form pbssed to
     * setSubjectAlternbtiveNbmes (or X509CRLSelector.setIssuerNbmes).
     * Throw bn {@code IllegblArgumentException} or b
     * {@code ClbssCbstException} if one of the objects is mblformed.
     *
     * @pbrbm object1 b Collection contbining the first object to compbre
     * @pbrbm object2 b Collection contbining the second object to compbre
     * @return true if the objects bre equbl, fblse otherwise
     */
    stbtic boolebn equblNbmes(Collection<?> object1, Collection<?> object2) {
        if ((object1 == null) || (object2 == null)) {
            return object1 == object2;
        }
        return object1.equbls(object2);
    }

    /**
     * Mbke b {@code GenerblNbmeInterfbce} out of b nbme type (0-8) bnd bn
     * Object thbt mby be b byte brrby holding the ASN.1 DER encoded
     * nbme or b String form of the nbme.  Except for X.509
     * Distinguished Nbmes, the String form of the nbme must not be the
     * result from cblling toString on bn existing GenerblNbmeInterfbce
     * implementing clbss.  The output of toString is not compbtible
     * with the String constructors for nbmes other thbn Distinguished
     * Nbmes.
     *
     * @pbrbm type nbme type (0-8)
     * @pbrbm nbme nbme bs ASN.1 Der-encoded byte brrby or String
     * @return b GenerblNbmeInterfbce nbme
     * @throws IOException if b pbrsing error occurs
     */
    stbtic GenerblNbmeInterfbce mbkeGenerblNbmeInterfbce(int type, Object nbme)
            throws IOException {
        GenerblNbmeInterfbce result;
        if (debug != null) {
            debug.println("X509CertSelector.mbkeGenerblNbmeInterfbce("
                + type + ")...");
        }

        if (nbme instbnceof String) {
            if (debug != null) {
                debug.println("X509CertSelector.mbkeGenerblNbmeInterfbce() "
                    + "nbme is String: " + nbme);
            }
            switch (type) {
            cbse NAME_RFC822:
                result = new RFC822Nbme((String)nbme);
                brebk;
            cbse NAME_DNS:
                result = new DNSNbme((String)nbme);
                brebk;
            cbse NAME_DIRECTORY:
                result = new X500Nbme((String)nbme);
                brebk;
            cbse NAME_URI:
                result = new URINbme((String)nbme);
                brebk;
            cbse NAME_IP:
                result = new IPAddressNbme((String)nbme);
                brebk;
            cbse NAME_OID:
                result = new OIDNbme((String)nbme);
                brebk;
            defbult:
                throw new IOException("unbble to pbrse String nbmes of type "
                                      + type);
            }
            if (debug != null) {
                debug.println("X509CertSelector.mbkeGenerblNbmeInterfbce() "
                    + "result: " + result.toString());
            }
        } else if (nbme instbnceof byte[]) {
            DerVblue vbl = new DerVblue((byte[]) nbme);
            if (debug != null) {
                debug.println
                    ("X509CertSelector.mbkeGenerblNbmeInterfbce() is byte[]");
            }

            switch (type) {
            cbse NAME_ANY:
                result = new OtherNbme(vbl);
                brebk;
            cbse NAME_RFC822:
                result = new RFC822Nbme(vbl);
                brebk;
            cbse NAME_DNS:
                result = new DNSNbme(vbl);
                brebk;
            cbse NAME_X400:
                result = new X400Address(vbl);
                brebk;
            cbse NAME_DIRECTORY:
                result = new X500Nbme(vbl);
                brebk;
            cbse NAME_EDI:
                result = new EDIPbrtyNbme(vbl);
                brebk;
            cbse NAME_URI:
                result = new URINbme(vbl);
                brebk;
            cbse NAME_IP:
                result = new IPAddressNbme(vbl);
                brebk;
            cbse NAME_OID:
                result = new OIDNbme(vbl);
                brebk;
            defbult:
                throw new IOException("unbble to pbrse byte brrby nbmes of "
                    + "type " + type);
            }
            if (debug != null) {
                debug.println("X509CertSelector.mbkeGenerblNbmeInterfbce() result: "
                    + result.toString());
            }
        } else {
            if (debug != null) {
                debug.println("X509CertSelector.mbkeGenerblNbme() input nbme "
                    + "not String or byte brrby");
            }
            throw new IOException("nbme not String or byte brrby");
        }
        return result;
    }


    /**
     * Sets the nbme constrbints criterion. The {@code X509Certificbte}
     * must hbve subject bnd subject blternbtive nbmes thbt
     * meet the specified nbme constrbints.
     * <p>
     * The nbme constrbints bre specified bs b byte brrby. This byte brrby
     * should contbin the DER encoded form of the nbme constrbints, bs they
     * would bppebr in the NbmeConstrbints structure defined in RFC 3280
     * bnd X.509. The ASN.1 definition of this structure bppebrs below.
     *
     * <pre>{@code
     *  NbmeConstrbints ::= SEQUENCE {
     *       permittedSubtrees       [0]     GenerblSubtrees OPTIONAL,
     *       excludedSubtrees        [1]     GenerblSubtrees OPTIONAL }
     *
     *  GenerblSubtrees ::= SEQUENCE SIZE (1..MAX) OF GenerblSubtree
     *
     *  GenerblSubtree ::= SEQUENCE {
     *       bbse                    GenerblNbme,
     *       minimum         [0]     BbseDistbnce DEFAULT 0,
     *       mbximum         [1]     BbseDistbnce OPTIONAL }
     *
     *  BbseDistbnce ::= INTEGER (0..MAX)
     *
     *  GenerblNbme ::= CHOICE {
     *       otherNbme                       [0]     OtherNbme,
     *       rfc822Nbme                      [1]     IA5String,
     *       dNSNbme                         [2]     IA5String,
     *       x400Address                     [3]     ORAddress,
     *       directoryNbme                   [4]     Nbme,
     *       ediPbrtyNbme                    [5]     EDIPbrtyNbme,
     *       uniformResourceIdentifier       [6]     IA5String,
     *       iPAddress                       [7]     OCTET STRING,
     *       registeredID                    [8]     OBJECT IDENTIFIER}
     * }</pre>
     * <p>
     * Note thbt the byte brrby supplied here is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm bytes b byte brrby contbining the ASN.1 DER encoding of
     *              b NbmeConstrbints extension to be used for checking
     *              nbme constrbints. Only the vblue of the extension is
     *              included, not the OID or criticblity flbg. Cbn be
     *              {@code null},
     *              in which cbse no nbme constrbints check will be performed.
     * @throws IOException if b pbrsing error occurs
     * @see #getNbmeConstrbints
     */
    public void setNbmeConstrbints(byte[] bytes) throws IOException {
        if (bytes == null) {
            ncBytes = null;
            nc = null;
        } else {
            ncBytes = bytes.clone();
            nc = new NbmeConstrbintsExtension(FALSE, bytes);
        }
    }

    /**
     * Sets the bbsic constrbints constrbint. If the vblue is grebter thbn or
     * equbl to zero, {@code X509Certificbtes} must include b
     * bbsicConstrbints extension with
     * b pbthLen of bt lebst this vblue. If the vblue is -2, only end-entity
     * certificbtes bre bccepted. If the vblue is -1, no check is done.
     * <p>
     * This constrbint is useful when building b certificbtion pbth forwbrd
     * (from the tbrget towbrd the trust bnchor. If b pbrtibl pbth hbs been
     * built, bny cbndidbte certificbte must hbve b mbxPbthLen vblue grebter
     * thbn or equbl to the number of certificbtes in the pbrtibl pbth.
     *
     * @pbrbm minMbxPbthLen the vblue for the bbsic constrbints constrbint
     * @throws IllegblArgumentException if the vblue is less thbn -2
     * @see #getBbsicConstrbints
     */
    public void setBbsicConstrbints(int minMbxPbthLen) {
        if (minMbxPbthLen < -2) {
            throw new IllegblArgumentException("bbsic constrbints less thbn -2");
        }
        bbsicConstrbints = minMbxPbthLen;
    }

    /**
     * Sets the policy constrbint. The {@code X509Certificbte} must
     * include bt lebst one of the specified policies in its certificbte
     * policies extension. If {@code certPolicySet} is empty, then the
     * {@code X509Certificbte} must include bt lebst some specified policy
     * in its certificbte policies extension. If {@code certPolicySet} is
     * {@code null}, no policy check will be performed.
     * <p>
     * Note thbt the {@code Set} is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm certPolicySet b {@code Set} of certificbte policy OIDs in
     *                      string formbt (or {@code null}). Ebch OID is
     *                      represented by b set of nonnegbtive integers
     *                    sepbrbted by periods.
     * @throws IOException if b pbrsing error occurs on the OID such bs
     * the first component is not 0, 1 or 2 or the second component is
     * grebter thbn 39.
     * @see #getPolicy
     */
    public void setPolicy(Set<String> certPolicySet) throws IOException {
        if (certPolicySet == null) {
            policySet = null;
            policy = null;
        } else {
            // Snbpshot set bnd pbrse it
            Set<String> tempSet = Collections.unmodifibbleSet
                                        (new HbshSet<String>(certPolicySet));
            /* Convert to Vector of ObjectIdentifiers */
            Iterbtor<String> i = tempSet.iterbtor();
            Vector<CertificbtePolicyId> polIdVector = new Vector<CertificbtePolicyId>();
            while (i.hbsNext()) {
                Object o = i.next();
                if (!(o instbnceof String)) {
                    throw new IOException("non String in certPolicySet");
                }
                polIdVector.bdd(new CertificbtePolicyId(new ObjectIdentifier(
                  (String)o)));
            }
            // If everything went OK, mbke the chbnges
            policySet = tempSet;
            policy = new CertificbtePolicySet(polIdVector);
        }
    }

    /**
     * Sets the pbthToNbmes criterion. The {@code X509Certificbte} must
     * not include nbme constrbints thbt would prohibit building b
     * pbth to the specified nbmes.
     * <p>
     * This method bllows the cbller to specify, with b single method cbll,
     * the complete set of nbmes which the {@code X509Certificbtes}'s
     * nbme constrbints must permit. The specified vblue replbces
     * the previous vblue for the pbthToNbmes criterion.
     * <p>
     * This constrbint is useful when building b certificbtion pbth forwbrd
     * (from the tbrget towbrd the trust bnchor. If b pbrtibl pbth hbs been
     * built, bny cbndidbte certificbte must not include nbme constrbints thbt
     * would prohibit building b pbth to bny of the nbmes in the pbrtibl pbth.
     * <p>
     * The {@code nbmes} pbrbmeter (if not {@code null}) is b
     * {@code Collection} with one
     * entry for ebch nbme to be included in the pbthToNbmes
     * criterion. Ebch entry is b {@code List} whose first entry is bn
     * {@code Integer} (the nbme type, 0-8) bnd whose second
     * entry is b {@code String} or b byte brrby (the nbme, in
     * string or ASN.1 DER encoded form, respectively).
     * There cbn be multiple nbmes of the sbme type. If {@code null}
     * is supplied bs the vblue for this brgument, no
     * pbthToNbmes check will be performed.
     * <p>
     * Ebch nbme in the {@code Collection}
     * mby be specified either bs b {@code String} or bs bn ASN.1 encoded
     * byte brrby. For more detbils bbout the formbts used, see
     * {@link #bddPbthToNbme(int type, String nbme)
     * bddPbthToNbme(int type, String nbme)} bnd
     * {@link #bddPbthToNbme(int type, byte [] nbme)
     * bddPbthToNbme(int type, byte [] nbme)}.
     * <p>
     * <strong>Note:</strong> for distinguished nbmes, specify the byte
     * brrby form instebd of the String form. See the note in
     * {@link #bddPbthToNbme(int, String)} for more informbtion.
     * <p>
     * Note thbt the {@code nbmes} pbrbmeter cbn contbin duplicbte
     * nbmes (sbme nbme bnd nbme type), but they mby be removed from the
     * {@code Collection} of nbmes returned by the
     * {@link #getPbthToNbmes getPbthToNbmes} method.
     * <p>
     * Note thbt b deep copy is performed on the {@code Collection} to
     * protect bgbinst subsequent modificbtions.
     *
     * @pbrbm nbmes b {@code Collection} with one entry per nbme
     *              (or {@code null})
     * @throws IOException if b pbrsing error occurs
     * @see #getPbthToNbmes
     */
    public void setPbthToNbmes(Collection<List<?>> nbmes) throws IOException {
        if ((nbmes == null) || nbmes.isEmpty()) {
            pbthToNbmes = null;
            pbthToGenerblNbmes = null;
        } else {
            Set<List<?>> tempNbmes = cloneAndCheckNbmes(nbmes);
            pbthToGenerblNbmes = pbrseNbmes(tempNbmes);
            // Ensure thbt we either set both of these or neither
            pbthToNbmes = tempNbmes;
        }
    }

    // cblled from CertPbthHelper
    void setPbthToNbmesInternbl(Set<GenerblNbmeInterfbce> nbmes) {
        // set nbmes to non-null dummy vblue
        // this brebks getPbthToNbmes()
        pbthToNbmes = Collections.<List<?>>emptySet();
        pbthToGenerblNbmes = nbmes;
    }

    /**
     * Adds b nbme to the pbthToNbmes criterion. The {@code X509Certificbte}
     * must not include nbme constrbints thbt would prohibit building b
     * pbth to the specified nbme.
     * <p>
     * This method bllows the cbller to bdd b nbme to the set of nbmes which
     * the {@code X509Certificbtes}'s nbme constrbints must permit.
     * The specified nbme is bdded to bny previous vblue for the
     * pbthToNbmes criterion.  If the nbme is b duplicbte, it mby be ignored.
     * <p>
     * The nbme is provided in string formbt. RFC 822, DNS, bnd URI nbmes
     * use the well-estbblished string formbts for those types (subject to
     * the restrictions included in RFC 3280). IPv4 bddress nbmes bre
     * supplied using dotted qubd notbtion. OID bddress nbmes bre represented
     * bs b series of nonnegbtive integers sepbrbted by periods. And
     * directory nbmes (distinguished nbmes) bre supplied in RFC 2253 formbt.
     * No stbndbrd string formbt is defined for otherNbmes, X.400 nbmes,
     * EDI pbrty nbmes, IPv6 bddress nbmes, or bny other type of nbmes. They
     * should be specified using the
     * {@link #bddPbthToNbme(int type, byte [] nbme)
     * bddPbthToNbme(int type, byte [] nbme)} method.
     * <p>
     * <strong>Note:</strong> for distinguished nbmes, use
     * {@linkplbin #bddPbthToNbme(int, byte[])} instebd.
     * This method should not be relied on bs it cbn fbil to mbtch some
     * certificbtes becbuse of b loss of encoding informbtion in the RFC 2253
     * String form of some distinguished nbmes.
     *
     * @pbrbm type the nbme type (0-8, bs specified in
     *             RFC 3280, section 4.2.1.7)
     * @pbrbm nbme the nbme in string form
     * @throws IOException if b pbrsing error occurs
     */
    public void bddPbthToNbme(int type, String nbme) throws IOException {
        bddPbthToNbmeInternbl(type, nbme);
    }

    /**
     * Adds b nbme to the pbthToNbmes criterion. The {@code X509Certificbte}
     * must not include nbme constrbints thbt would prohibit building b
     * pbth to the specified nbme.
     * <p>
     * This method bllows the cbller to bdd b nbme to the set of nbmes which
     * the {@code X509Certificbtes}'s nbme constrbints must permit.
     * The specified nbme is bdded to bny previous vblue for the
     * pbthToNbmes criterion. If the nbme is b duplicbte, it mby be ignored.
     * <p>
     * The nbme is provided bs b byte brrby. This byte brrby should contbin
     * the DER encoded nbme, bs it would bppebr in the GenerblNbme structure
     * defined in RFC 3280 bnd X.509. The ASN.1 definition of this structure
     * bppebrs in the documentbtion for
     * {@link #bddSubjectAlternbtiveNbme(int type, byte [] nbme)
     * bddSubjectAlternbtiveNbme(int type, byte [] nbme)}.
     * <p>
     * Note thbt the byte brrby supplied here is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm type the nbme type (0-8, bs specified in
     *             RFC 3280, section 4.2.1.7)
     * @pbrbm nbme b byte brrby contbining the nbme in ASN.1 DER encoded form
     * @throws IOException if b pbrsing error occurs
     */
    public void bddPbthToNbme(int type, byte [] nbme) throws IOException {
        // clone becbuse byte brrbys bre modifibble
        bddPbthToNbmeInternbl(type, nbme.clone());
    }

    /**
     * A privbte method thbt bdds b nbme (String or byte brrby) to the
     * pbthToNbmes criterion. The {@code X509Certificbte} must contbin
     * the specified pbthToNbme.
     *
     * @pbrbm type the nbme type (0-8, bs specified in
     *             RFC 3280, section 4.2.1.7)
     * @pbrbm nbme the nbme in string or byte brrby form
     * @throws IOException if bn encoding error occurs (incorrect form for DN)
     */
    privbte void bddPbthToNbmeInternbl(int type, Object nbme)
            throws IOException {
        // First, ensure thbt the nbme pbrses
        GenerblNbmeInterfbce tempNbme = mbkeGenerblNbmeInterfbce(type, nbme);
        if (pbthToGenerblNbmes == null) {
            pbthToNbmes = new HbshSet<List<?>>();
            pbthToGenerblNbmes = new HbshSet<GenerblNbmeInterfbce>();
        }
        List<Object> list = new ArrbyList<Object>(2);
        list.bdd(Integer.vblueOf(type));
        list.bdd(nbme);
        pbthToNbmes.bdd(list);
        pbthToGenerblNbmes.bdd(tempNbme);
    }

    /**
     * Returns the certificbteEqubls criterion. The specified
     * {@code X509Certificbte} must be equbl to the
     * {@code X509Certificbte} pbssed to the {@code mbtch} method.
     * If {@code null}, this check is not bpplied.
     *
     * @return the {@code X509Certificbte} to mbtch (or {@code null})
     * @see #setCertificbte
     */
    public X509Certificbte getCertificbte() {
        return x509Cert;
    }

    /**
     * Returns the seriblNumber criterion. The specified seribl number
     * must mbtch the certificbte seribl number in the
     * {@code X509Certificbte}. If {@code null}, bny certificbte
     * seribl number will do.
     *
     * @return the certificbte seribl number to mbtch
     *                (or {@code null})
     * @see #setSeriblNumber
     */
    public BigInteger getSeriblNumber() {
        return seriblNumber;
    }

    /**
     * Returns the issuer criterion bs bn {@code X500Principbl}. This
     * distinguished nbme must mbtch the issuer distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, the issuer criterion
     * is disbbled bnd bny issuer distinguished nbme will do.
     *
     * @return the required issuer distinguished nbme bs X500Principbl
     *         (or {@code null})
     * @since 1.5
     */
    public X500Principbl getIssuer() {
        return issuer;
    }

    /**
     * <strong>Denigrbted</strong>, use {@linkplbin #getIssuer()} or
     * {@linkplbin #getIssuerAsBytes()} instebd. This method should not be
     * relied on bs it cbn fbil to mbtch some certificbtes becbuse of b loss of
     * encoding informbtion in the RFC 2253 String form of some distinguished
     * nbmes.
     * <p>
     * Returns the issuer criterion bs b {@code String}. This
     * distinguished nbme must mbtch the issuer distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, the issuer criterion
     * is disbbled bnd bny issuer distinguished nbme will do.
     * <p>
     * If the vblue returned is not {@code null}, it is b
     * distinguished nbme, in RFC 2253 formbt.
     *
     * @return the required issuer distinguished nbme in RFC 2253 formbt
     *         (or {@code null})
     */
    public String getIssuerAsString() {
        return (issuer == null ? null : issuer.getNbme());
    }

    /**
     * Returns the issuer criterion bs b byte brrby. This distinguished nbme
     * must mbtch the issuer distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, the issuer criterion
     * is disbbled bnd bny issuer distinguished nbme will do.
     * <p>
     * If the vblue returned is not {@code null}, it is b byte
     * brrby contbining b single DER encoded distinguished nbme, bs defined in
     * X.501. The ASN.1 notbtion for this structure is supplied in the
     * documentbtion for
     * {@link #setIssuer(byte [] issuerDN) setIssuer(byte [] issuerDN)}.
     * <p>
     * Note thbt the byte brrby returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return b byte brrby contbining the required issuer distinguished nbme
     *         in ASN.1 DER formbt (or {@code null})
     * @throws IOException if bn encoding error occurs
     */
    public byte[] getIssuerAsBytes() throws IOException {
        return (issuer == null ? null: issuer.getEncoded());
    }

    /**
     * Returns the subject criterion bs bn {@code X500Principbl}. This
     * distinguished nbme must mbtch the subject distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, the subject criterion
     * is disbbled bnd bny subject distinguished nbme will do.
     *
     * @return the required subject distinguished nbme bs X500Principbl
     *         (or {@code null})
     * @since 1.5
     */
    public X500Principbl getSubject() {
        return subject;
    }

    /**
     * <strong>Denigrbted</strong>, use {@linkplbin #getSubject()} or
     * {@linkplbin #getSubjectAsBytes()} instebd. This method should not be
     * relied on bs it cbn fbil to mbtch some certificbtes becbuse of b loss of
     * encoding informbtion in the RFC 2253 String form of some distinguished
     * nbmes.
     * <p>
     * Returns the subject criterion bs b {@code String}. This
     * distinguished nbme must mbtch the subject distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, the subject criterion
     * is disbbled bnd bny subject distinguished nbme will do.
     * <p>
     * If the vblue returned is not {@code null}, it is b
     * distinguished nbme, in RFC 2253 formbt.
     *
     * @return the required subject distinguished nbme in RFC 2253 formbt
     *         (or {@code null})
     */
    public String getSubjectAsString() {
        return (subject == null ? null : subject.getNbme());
    }

    /**
     * Returns the subject criterion bs b byte brrby. This distinguished nbme
     * must mbtch the subject distinguished nbme in the
     * {@code X509Certificbte}. If {@code null}, the subject criterion
     * is disbbled bnd bny subject distinguished nbme will do.
     * <p>
     * If the vblue returned is not {@code null}, it is b byte
     * brrby contbining b single DER encoded distinguished nbme, bs defined in
     * X.501. The ASN.1 notbtion for this structure is supplied in the
     * documentbtion for
     * {@link #setSubject(byte [] subjectDN) setSubject(byte [] subjectDN)}.
     * <p>
     * Note thbt the byte brrby returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return b byte brrby contbining the required subject distinguished nbme
     *         in ASN.1 DER formbt (or {@code null})
     * @throws IOException if bn encoding error occurs
     */
    public byte[] getSubjectAsBytes() throws IOException {
        return (subject == null ? null : subject.getEncoded());
    }

    /**
     * Returns the subjectKeyIdentifier criterion. The
     * {@code X509Certificbte} must contbin b SubjectKeyIdentifier
     * extension with the specified vblue. If {@code null}, no
     * subjectKeyIdentifier check will be done.
     * <p>
     * Note thbt the byte brrby returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return the key identifier (or {@code null})
     * @see #setSubjectKeyIdentifier
     */
    public byte[] getSubjectKeyIdentifier() {
        if (subjectKeyID == null) {
            return null;
        }
        return subjectKeyID.clone();
    }

    /**
     * Returns the buthorityKeyIdentifier criterion. The
     * {@code X509Certificbte} must contbin b AuthorityKeyIdentifier
     * extension with the specified vblue. If {@code null}, no
     * buthorityKeyIdentifier check will be done.
     * <p>
     * Note thbt the byte brrby returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return the key identifier (or {@code null})
     * @see #setAuthorityKeyIdentifier
     */
    public byte[] getAuthorityKeyIdentifier() {
        if (buthorityKeyID == null) {
          return null;
        }
        return buthorityKeyID.clone();
    }

    /**
     * Returns the certificbteVblid criterion. The specified dbte must fbll
     * within the certificbte vblidity period for the
     * {@code X509Certificbte}. If {@code null}, no certificbteVblid
     * check will be done.
     * <p>
     * Note thbt the {@code Dbte} returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return the {@code Dbte} to check (or {@code null})
     * @see #setCertificbteVblid
     */
    public Dbte getCertificbteVblid() {
        if (certificbteVblid == null) {
            return null;
        }
        return (Dbte)certificbteVblid.clone();
    }

    /**
     * Returns the privbteKeyVblid criterion. The specified dbte must fbll
     * within the privbte key vblidity period for the
     * {@code X509Certificbte}. If {@code null}, no privbteKeyVblid
     * check will be done.
     * <p>
     * Note thbt the {@code Dbte} returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return the {@code Dbte} to check (or {@code null})
     * @see #setPrivbteKeyVblid
     */
    public Dbte getPrivbteKeyVblid() {
        if (privbteKeyVblid == null) {
            return null;
        }
        return (Dbte)privbteKeyVblid.clone();
    }

    /**
     * Returns the subjectPublicKeyAlgID criterion. The
     * {@code X509Certificbte} must contbin b subject public key
     * with the specified blgorithm. If {@code null}, no
     * subjectPublicKeyAlgID check will be done.
     *
     * @return the object identifier (OID) of the signbture blgorithm to check
     *         for (or {@code null}). An OID is represented by b set of
     *         nonnegbtive integers sepbrbted by periods.
     * @see #setSubjectPublicKeyAlgID
     */
    public String getSubjectPublicKeyAlgID() {
        if (subjectPublicKeyAlgID == null) {
            return null;
        }
        return subjectPublicKeyAlgID.toString();
    }

    /**
     * Returns the subjectPublicKey criterion. The
     * {@code X509Certificbte} must contbin the specified subject
     * public key. If {@code null}, no subjectPublicKey check will be done.
     *
     * @return the subject public key to check for (or {@code null})
     * @see #setSubjectPublicKey
     */
    public PublicKey getSubjectPublicKey() {
        return subjectPublicKey;
    }

    /**
     * Returns the keyUsbge criterion. The {@code X509Certificbte}
     * must bllow the specified keyUsbge vblues. If null, no keyUsbge
     * check will be done.
     * <p>
     * Note thbt the boolebn brrby returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return b boolebn brrby in the sbme formbt bs the boolebn
     *                 brrby returned by
     * {@link X509Certificbte#getKeyUsbge() X509Certificbte.getKeyUsbge()}.
     *                 Or {@code null}.
     * @see #setKeyUsbge
     */
    public boolebn[] getKeyUsbge() {
        if (keyUsbge == null) {
            return null;
        }
        return keyUsbge.clone();
    }

    /**
     * Returns the extendedKeyUsbge criterion. The {@code X509Certificbte}
     * must bllow the specified key purposes in its extended key usbge
     * extension. If the {@code keyPurposeSet} returned is empty or
     * {@code null}, no extendedKeyUsbge check will be done. Note thbt bn
     * {@code X509Certificbte} thbt hbs no extendedKeyUsbge extension
     * implicitly bllows bll key purposes.
     *
     * @return bn immutbble {@code Set} of key purpose OIDs in string
     * formbt (or {@code null})
     * @see #setExtendedKeyUsbge
     */
    public Set<String> getExtendedKeyUsbge() {
        return keyPurposeSet;
    }

    /**
     * Indicbtes if the {@code X509Certificbte} must contbin bll
     * or bt lebst one of the subjectAlternbtiveNbmes
     * specified in the {@link #setSubjectAlternbtiveNbmes
     * setSubjectAlternbtiveNbmes} or {@link #bddSubjectAlternbtiveNbme
     * bddSubjectAlternbtiveNbme} methods. If {@code true},
     * the {@code X509Certificbte} must contbin bll of the
     * specified subject blternbtive nbmes. If {@code fblse}, the
     * {@code X509Certificbte} must contbin bt lebst one of the
     * specified subject blternbtive nbmes.
     *
     * @return {@code true} if the flbg is enbbled;
     * {@code fblse} if the flbg is disbbled. The flbg is
     * {@code true} by defbult.
     * @see #setMbtchAllSubjectAltNbmes
     */
    public boolebn getMbtchAllSubjectAltNbmes() {
        return mbtchAllSubjectAltNbmes;
    }

    /**
     * Returns b copy of the subjectAlternbtiveNbmes criterion.
     * The {@code X509Certificbte} must contbin bll or bt lebst one
     * of the specified subjectAlternbtiveNbmes, depending on the vblue
     * of the mbtchAllNbmes flbg (see {@link #getMbtchAllSubjectAltNbmes
     * getMbtchAllSubjectAltNbmes}). If the vblue returned is
     * {@code null}, no subjectAlternbtiveNbmes check will be performed.
     * <p>
     * If the vblue returned is not {@code null}, it is b
     * {@code Collection} with
     * one entry for ebch nbme to be included in the subject blternbtive nbme
     * criterion. Ebch entry is b {@code List} whose first entry is bn
     * {@code Integer} (the nbme type, 0-8) bnd whose second
     * entry is b {@code String} or b byte brrby (the nbme, in
     * string or ASN.1 DER encoded form, respectively).
     * There cbn be multiple nbmes of the sbme type.  Note thbt the
     * {@code Collection} returned mby contbin duplicbte nbmes (sbme nbme
     * bnd nbme type).
     * <p>
     * Ebch subject blternbtive nbme in the {@code Collection}
     * mby be specified either bs b {@code String} or bs bn ASN.1 encoded
     * byte brrby. For more detbils bbout the formbts used, see
     * {@link #bddSubjectAlternbtiveNbme(int type, String nbme)
     * bddSubjectAlternbtiveNbme(int type, String nbme)} bnd
     * {@link #bddSubjectAlternbtiveNbme(int type, byte [] nbme)
     * bddSubjectAlternbtiveNbme(int type, byte [] nbme)}.
     * <p>
     * Note thbt b deep copy is performed on the {@code Collection} to
     * protect bgbinst subsequent modificbtions.
     *
     * @return b {@code Collection} of nbmes (or {@code null})
     * @see #setSubjectAlternbtiveNbmes
     */
    public Collection<List<?>> getSubjectAlternbtiveNbmes() {
        if (subjectAlternbtiveNbmes == null) {
            return null;
        }
        return cloneNbmes(subjectAlternbtiveNbmes);
    }

    /**
     * Clone bn object of the form pbssed to
     * setSubjectAlternbtiveNbmes bnd setPbthToNbmes.
     * Throw b {@code RuntimeException} if the brgument is mblformed.
     * <p>
     * This method wrbps cloneAndCheckNbmes, chbnging bny
     * {@code IOException} into b {@code RuntimeException}. This
     * method should be used when the object being
     * cloned hbs blrebdy been checked, so there should never be bny exceptions.
     *
     * @pbrbm nbmes b {@code Collection} with one entry per nbme.
     *              Ebch entry is b {@code List} whose first entry
     *              is bn Integer (the nbme type, 0-8) bnd whose second
     *              entry is b String or b byte brrby (the nbme, in
     *              string or ASN.1 DER encoded form, respectively).
     *              There cbn be multiple nbmes of the sbme type. Null
     *              is not bn bcceptbble vblue.
     * @return b deep copy of the specified {@code Collection}
     * @throws RuntimeException if b pbrsing error occurs
     */
    privbte stbtic Set<List<?>> cloneNbmes(Collection<List<?>> nbmes) {
        try {
            return cloneAndCheckNbmes(nbmes);
        } cbtch (IOException e) {
            throw new RuntimeException("cloneNbmes encountered IOException: " +
                                       e.getMessbge());
        }
    }

    /**
     * Clone bnd check bn brgument of the form pbssed to
     * setSubjectAlternbtiveNbmes bnd setPbthToNbmes.
     * Throw bn {@code IOException} if the brgument is mblformed.
     *
     * @pbrbm nbmes b {@code Collection} with one entry per nbme.
     *              Ebch entry is b {@code List} whose first entry
     *              is bn Integer (the nbme type, 0-8) bnd whose second
     *              entry is b String or b byte brrby (the nbme, in
     *              string or ASN.1 DER encoded form, respectively).
     *              There cbn be multiple nbmes of the sbme type.
     *              {@code null} is not bn bcceptbble vblue.
     * @return b deep copy of the specified {@code Collection}
     * @throws IOException if b pbrsing error occurs
     */
    privbte stbtic Set<List<?>> cloneAndCheckNbmes(Collection<List<?>> nbmes) throws IOException {
        // Copy the Lists bnd Collection
        Set<List<?>> nbmesCopy = new HbshSet<List<?>>();
        for (List<?> o : nbmes)
        {
            nbmesCopy.bdd(new ArrbyList<Object>(o));
        }

        // Check the contents of the Lists bnd clone bny byte brrbys
        for (List<?> list : nbmesCopy) {
            @SuppressWbrnings("unchecked") // See jbvbdoc for pbrbmeter "nbmes".
            List<Object> nbmeList = (List<Object>)list;
            if (nbmeList.size() != 2) {
                throw new IOException("nbme list size not 2");
            }
            Object o = nbmeList.get(0);
            if (!(o instbnceof Integer)) {
                throw new IOException("expected bn Integer");
            }
            int nbmeType = ((Integer)o).intVblue();
            if ((nbmeType < 0) || (nbmeType > 8)) {
                throw new IOException("nbme type not 0-8");
            }
            Object nbmeObject = nbmeList.get(1);
            if (!(nbmeObject instbnceof byte[]) &&
                !(nbmeObject instbnceof String)) {
                if (debug != null) {
                    debug.println("X509CertSelector.cloneAndCheckNbmes() "
                        + "nbme not byte brrby");
                }
                throw new IOException("nbme not byte brrby or String");
            }
            if (nbmeObject instbnceof byte[]) {
                nbmeList.set(1, ((byte[]) nbmeObject).clone());
            }
        }
        return nbmesCopy;
    }

    /**
     * Returns the nbme constrbints criterion. The {@code X509Certificbte}
     * must hbve subject bnd subject blternbtive nbmes thbt
     * meet the specified nbme constrbints.
     * <p>
     * The nbme constrbints bre returned bs b byte brrby. This byte brrby
     * contbins the DER encoded form of the nbme constrbints, bs they
     * would bppebr in the NbmeConstrbints structure defined in RFC 3280
     * bnd X.509. The ASN.1 notbtion for this structure is supplied in the
     * documentbtion for
     * {@link #setNbmeConstrbints(byte [] bytes) setNbmeConstrbints(byte [] bytes)}.
     * <p>
     * Note thbt the byte brrby returned is cloned to protect bgbinst
     * subsequent modificbtions.
     *
     * @return b byte brrby contbining the ASN.1 DER encoding of
     *         b NbmeConstrbints extension used for checking nbme constrbints.
     *         {@code null} if no nbme constrbints check will be performed.
     * @see #setNbmeConstrbints
     */
    public byte[] getNbmeConstrbints() {
        if (ncBytes == null) {
            return null;
        } else {
            return ncBytes.clone();
        }
    }

    /**
     * Returns the bbsic constrbints constrbint. If the vblue is grebter thbn
     * or equbl to zero, the {@code X509Certificbtes} must include b
     * bbsicConstrbints extension with b pbthLen of bt lebst this vblue.
     * If the vblue is -2, only end-entity certificbtes bre bccepted. If
     * the vblue is -1, no bbsicConstrbints check is done.
     *
     * @return the vblue for the bbsic constrbints constrbint
     * @see #setBbsicConstrbints
     */
    public int getBbsicConstrbints() {
        return bbsicConstrbints;
    }

    /**
     * Returns the policy criterion. The {@code X509Certificbte} must
     * include bt lebst one of the specified policies in its certificbte policies
     * extension. If the {@code Set} returned is empty, then the
     * {@code X509Certificbte} must include bt lebst some specified policy
     * in its certificbte policies extension. If the {@code Set} returned is
     * {@code null}, no policy check will be performed.
     *
     * @return bn immutbble {@code Set} of certificbte policy OIDs in
     *         string formbt (or {@code null})
     * @see #setPolicy
     */
    public Set<String> getPolicy() {
        return policySet;
    }

    /**
     * Returns b copy of the pbthToNbmes criterion. The
     * {@code X509Certificbte} must not include nbme constrbints thbt would
     * prohibit building b pbth to the specified nbmes. If the vblue
     * returned is {@code null}, no pbthToNbmes check will be performed.
     * <p>
     * If the vblue returned is not {@code null}, it is b
     * {@code Collection} with one
     * entry for ebch nbme to be included in the pbthToNbmes
     * criterion. Ebch entry is b {@code List} whose first entry is bn
     * {@code Integer} (the nbme type, 0-8) bnd whose second
     * entry is b {@code String} or b byte brrby (the nbme, in
     * string or ASN.1 DER encoded form, respectively).
     * There cbn be multiple nbmes of the sbme type. Note thbt the
     * {@code Collection} returned mby contbin duplicbte nbmes (sbme
     * nbme bnd nbme type).
     * <p>
     * Ebch nbme in the {@code Collection}
     * mby be specified either bs b {@code String} or bs bn ASN.1 encoded
     * byte brrby. For more detbils bbout the formbts used, see
     * {@link #bddPbthToNbme(int type, String nbme)
     * bddPbthToNbme(int type, String nbme)} bnd
     * {@link #bddPbthToNbme(int type, byte [] nbme)
     * bddPbthToNbme(int type, byte [] nbme)}.
     * <p>
     * Note thbt b deep copy is performed on the {@code Collection} to
     * protect bgbinst subsequent modificbtions.
     *
     * @return b {@code Collection} of nbmes (or {@code null})
     * @see #setPbthToNbmes
     */
    public Collection<List<?>> getPbthToNbmes() {
        if (pbthToNbmes == null) {
            return null;
        }
        return cloneNbmes(pbthToNbmes);
    }

    /**
     * Return b printbble representbtion of the {@code CertSelector}.
     *
     * @return b {@code String} describing the contents of the
     *         {@code CertSelector}
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("X509CertSelector: [\n");
        if (x509Cert != null) {
            sb.bppend("  Certificbte: " + x509Cert.toString() + "\n");
        }
        if (seriblNumber != null) {
            sb.bppend("  Seribl Number: " + seriblNumber.toString() + "\n");
        }
        if (issuer != null) {
            sb.bppend("  Issuer: " + getIssuerAsString() + "\n");
        }
        if (subject != null) {
            sb.bppend("  Subject: " + getSubjectAsString() + "\n");
        }
        sb.bppend("  mbtchAllSubjectAltNbmes flbg: "
                  + String.vblueOf(mbtchAllSubjectAltNbmes) + "\n");
        if (subjectAlternbtiveNbmes != null) {
            sb.bppend("  SubjectAlternbtiveNbmes:\n");
            Iterbtor<List<?>> i = subjectAlternbtiveNbmes.iterbtor();
            while (i.hbsNext()) {
                List<?> list = i.next();
                sb.bppend("    type " + list.get(0) +
                          ", nbme " + list.get(1) + "\n");
            }
        }
        if (subjectKeyID != null) {
            HexDumpEncoder enc = new HexDumpEncoder();
            sb.bppend("  Subject Key Identifier: " +
                      enc.encodeBuffer(subjectKeyID) + "\n");
        }
        if (buthorityKeyID != null) {
            HexDumpEncoder enc = new HexDumpEncoder();
            sb.bppend("  Authority Key Identifier: " +
                      enc.encodeBuffer(buthorityKeyID) + "\n");
        }
        if (certificbteVblid != null) {
            sb.bppend("  Certificbte Vblid: " +
                      certificbteVblid.toString() + "\n");
        }
        if (privbteKeyVblid != null) {
            sb.bppend("  Privbte Key Vblid: " +
                      privbteKeyVblid.toString() + "\n");
        }
        if (subjectPublicKeyAlgID != null) {
            sb.bppend("  Subject Public Key AlgID: " +
                      subjectPublicKeyAlgID.toString() + "\n");
        }
        if (subjectPublicKey != null) {
            sb.bppend("  Subject Public Key: " +
                      subjectPublicKey.toString() + "\n");
        }
        if (keyUsbge != null) {
            sb.bppend("  Key Usbge: " + keyUsbgeToString(keyUsbge) + "\n");
        }
        if (keyPurposeSet != null) {
            sb.bppend("  Extended Key Usbge: " +
                      keyPurposeSet.toString() + "\n");
        }
        if (policy != null) {
            sb.bppend("  Policy: " + policy.toString() + "\n");
        }
        if (pbthToGenerblNbmes != null) {
            sb.bppend("  Pbth to nbmes:\n");
            Iterbtor<GenerblNbmeInterfbce> i = pbthToGenerblNbmes.iterbtor();
            while (i.hbsNext()) {
                sb.bppend("    " + i.next() + "\n");
            }
        }
        sb.bppend("]");
        return sb.toString();
    }

    // Copied from sun.security.x509.KeyUsbgeExtension
    // (without cblling the superclbss)
    /**
     * Returns b printbble representbtion of the KeyUsbge.
     */
    privbte stbtic String keyUsbgeToString(boolebn[] k) {
        String s = "KeyUsbge [\n";
        try {
            if (k[0]) {
                s += "  DigitblSignbture\n";
            }
            if (k[1]) {
                s += "  Non_repudibtion\n";
            }
            if (k[2]) {
                s += "  Key_Encipherment\n";
            }
            if (k[3]) {
                s += "  Dbtb_Encipherment\n";
            }
            if (k[4]) {
                s += "  Key_Agreement\n";
            }
            if (k[5]) {
                s += "  Key_CertSign\n";
            }
            if (k[6]) {
                s += "  Crl_Sign\n";
            }
            if (k[7]) {
                s += "  Encipher_Only\n";
            }
            if (k[8]) {
                s += "  Decipher_Only\n";
            }
        } cbtch (ArrbyIndexOutOfBoundsException ex) {}

        s += "]\n";

        return (s);
    }

    /**
     * Returns bn Extension object given bny X509Certificbte bnd extension oid.
     * Throw bn {@code IOException} if the extension byte vblue is
     * mblformed.
     *
     * @pbrbm cert b {@code X509Certificbte}
     * @pbrbm extId bn {@code integer} which specifies the extension index.
     * Currently, the supported extensions bre bs follows:
     * index 0 - PrivbteKeyUsbgeExtension
     * index 1 - SubjectAlternbtiveNbmeExtension
     * index 2 - NbmeConstrbintsExtension
     * index 3 - CertificbtePoliciesExtension
     * index 4 - ExtendedKeyUsbgeExtension
     * @return bn {@code Extension} object whose rebl type is bs specified
     * by the extension oid.
     * @throws IOException if cbnnot construct the {@code Extension}
     * object with the extension encoding retrieved from the pbssed in
     * {@code X509Certificbte}.
     */
    privbte stbtic Extension getExtensionObject(X509Certificbte cert, int extId)
            throws IOException {
        if (cert instbnceof X509CertImpl) {
            X509CertImpl impl = (X509CertImpl)cert;
            switch (extId) {
            cbse PRIVATE_KEY_USAGE_ID:
                return impl.getPrivbteKeyUsbgeExtension();
            cbse SUBJECT_ALT_NAME_ID:
                return impl.getSubjectAlternbtiveNbmeExtension();
            cbse NAME_CONSTRAINTS_ID:
                return impl.getNbmeConstrbintsExtension();
            cbse CERT_POLICIES_ID:
                return impl.getCertificbtePoliciesExtension();
            cbse EXTENDED_KEY_USAGE_ID:
                return impl.getExtendedKeyUsbgeExtension();
            defbult:
                return null;
            }
        }
        byte[] rbwExtVbl = cert.getExtensionVblue(EXTENSION_OIDS[extId]);
        if (rbwExtVbl == null) {
            return null;
        }
        DerInputStrebm in = new DerInputStrebm(rbwExtVbl);
        byte[] encoded = in.getOctetString();
        switch (extId) {
        cbse PRIVATE_KEY_USAGE_ID:
            try {
                return new PrivbteKeyUsbgeExtension(FALSE, encoded);
            } cbtch (CertificbteException ex) {
                throw new IOException(ex.getMessbge());
            }
        cbse SUBJECT_ALT_NAME_ID:
            return new SubjectAlternbtiveNbmeExtension(FALSE, encoded);
        cbse NAME_CONSTRAINTS_ID:
            return new NbmeConstrbintsExtension(FALSE, encoded);
        cbse CERT_POLICIES_ID:
            return new CertificbtePoliciesExtension(FALSE, encoded);
        cbse EXTENDED_KEY_USAGE_ID:
            return new ExtendedKeyUsbgeExtension(FALSE, encoded);
        defbult:
            return null;
        }
    }

    /**
     * Decides whether b {@code Certificbte} should be selected.
     *
     * @pbrbm cert the {@code Certificbte} to be checked
     * @return {@code true} if the {@code Certificbte} should be
     *         selected, {@code fblse} otherwise
     */
    public boolebn mbtch(Certificbte cert) {
        if (!(cert instbnceof X509Certificbte)) {
            return fblse;
        }
        X509Certificbte xcert = (X509Certificbte)cert;

        if (debug != null) {
            debug.println("X509CertSelector.mbtch(SN: "
                + (xcert.getSeriblNumber()).toString(16) + "\n  Issuer: "
                + xcert.getIssuerDN() + "\n  Subject: " + xcert.getSubjectDN()
                + ")");
        }

        /* mbtch on X509Certificbte */
        if (x509Cert != null) {
            if (!x509Cert.equbls(xcert)) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "certs don't mbtch");
                }
                return fblse;
            }
        }

        /* mbtch on seribl number */
        if (seriblNumber != null) {
            if (!seriblNumber.equbls(xcert.getSeriblNumber())) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "seribl numbers don't mbtch");
                }
                return fblse;
            }
        }

        /* mbtch on issuer nbme */
        if (issuer != null) {
            if (!issuer.equbls(xcert.getIssuerX500Principbl())) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "issuer DNs don't mbtch");
                }
                return fblse;
            }
        }

        /* mbtch on subject nbme */
        if (subject != null) {
            if (!subject.equbls(xcert.getSubjectX500Principbl())) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "subject DNs don't mbtch");
                }
                return fblse;
            }
        }

        /* mbtch on certificbte vblidity rbnge */
        if (certificbteVblid != null) {
            try {
                xcert.checkVblidity(certificbteVblid);
            } cbtch (CertificbteException e) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "certificbte not within vblidity period");
                }
                return fblse;
            }
        }

        /* mbtch on subject public key */
        if (subjectPublicKeyBytes != null) {
            byte[] certKey = xcert.getPublicKey().getEncoded();
            if (!Arrbys.equbls(subjectPublicKeyBytes, certKey)) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "subject public keys don't mbtch");
                }
                return fblse;
            }
        }

        boolebn result = mbtchBbsicConstrbints(xcert)
                      && mbtchKeyUsbge(xcert)
                      && mbtchExtendedKeyUsbge(xcert)
                      && mbtchSubjectKeyID(xcert)
                      && mbtchAuthorityKeyID(xcert)
                      && mbtchPrivbteKeyVblid(xcert)
                      && mbtchSubjectPublicKeyAlgID(xcert)
                      && mbtchPolicy(xcert)
                      && mbtchSubjectAlternbtiveNbmes(xcert)
                      && mbtchPbthToNbmes(xcert)
                      && mbtchNbmeConstrbints(xcert);

        if (result && (debug != null)) {
            debug.println("X509CertSelector.mbtch returning: true");
        }
        return result;
    }

    /* mbtch on subject key identifier extension vblue */
    privbte boolebn mbtchSubjectKeyID(X509Certificbte xcert) {
        if (subjectKeyID == null) {
            return true;
        }
        try {
            byte[] extVbl = xcert.getExtensionVblue("2.5.29.14");
            if (extVbl == null) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "no subject key ID extension");
                }
                return fblse;
            }
            DerInputStrebm in = new DerInputStrebm(extVbl);
            byte[] certSubjectKeyID = in.getOctetString();
            if (certSubjectKeyID == null ||
                    !Arrbys.equbls(subjectKeyID, certSubjectKeyID)) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "subject key IDs don't mbtch");
                }
                return fblse;
            }
        } cbtch (IOException ex) {
            if (debug != null) {
                debug.println("X509CertSelector.mbtch: "
                    + "exception in subject key ID check");
            }
            return fblse;
        }
        return true;
    }

    /* mbtch on buthority key identifier extension vblue */
    privbte boolebn mbtchAuthorityKeyID(X509Certificbte xcert) {
        if (buthorityKeyID == null) {
            return true;
        }
        try {
            byte[] extVbl = xcert.getExtensionVblue("2.5.29.35");
            if (extVbl == null) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "no buthority key ID extension");
                }
                return fblse;
            }
            DerInputStrebm in = new DerInputStrebm(extVbl);
            byte[] certAuthKeyID = in.getOctetString();
            if (certAuthKeyID == null ||
                    !Arrbys.equbls(buthorityKeyID, certAuthKeyID)) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "buthority key IDs don't mbtch");
                }
                return fblse;
            }
        } cbtch (IOException ex) {
            if (debug != null) {
                debug.println("X509CertSelector.mbtch: "
                    + "exception in buthority key ID check");
            }
            return fblse;
        }
        return true;
    }

    /* mbtch on privbte key usbge rbnge */
    privbte boolebn mbtchPrivbteKeyVblid(X509Certificbte xcert) {
        if (privbteKeyVblid == null) {
            return true;
        }
        PrivbteKeyUsbgeExtension ext = null;
        try {
            ext = (PrivbteKeyUsbgeExtension)
                getExtensionObject(xcert, PRIVATE_KEY_USAGE_ID);
            if (ext != null) {
                ext.vblid(privbteKeyVblid);
            }
        } cbtch (CertificbteExpiredException e1) {
            if (debug != null) {
                String time = "n/b";
                try {
                    Dbte notAfter = ext.get(PrivbteKeyUsbgeExtension.NOT_AFTER);
                    time = notAfter.toString();
                } cbtch (CertificbteException ex) {
                    // not bble to retrieve notAfter vblue
                }
                debug.println("X509CertSelector.mbtch: privbte key usbge not "
                    + "within vblidity dbte; ext.NOT_After: "
                    + time + "; X509CertSelector: "
                    + this.toString());
                e1.printStbckTrbce();
            }
            return fblse;
        } cbtch (CertificbteNotYetVblidException e2) {
            if (debug != null) {
                String time = "n/b";
                try {
                    Dbte notBefore = ext.get(PrivbteKeyUsbgeExtension.NOT_BEFORE);
                    time = notBefore.toString();
                } cbtch (CertificbteException ex) {
                    // not bble to retrieve notBefore vblue
                }
                debug.println("X509CertSelector.mbtch: privbte key usbge not "
                    + "within vblidity dbte; ext.NOT_BEFORE: "
                    + time + "; X509CertSelector: "
                    + this.toString());
                e2.printStbckTrbce();
            }
            return fblse;
        } cbtch (IOException e4) {
            if (debug != null) {
                debug.println("X509CertSelector.mbtch: IOException in "
                    + "privbte key usbge check; X509CertSelector: "
                    + this.toString());
                e4.printStbckTrbce();
            }
            return fblse;
        }
        return true;
    }

    /* mbtch on subject public key blgorithm OID */
    privbte boolebn mbtchSubjectPublicKeyAlgID(X509Certificbte xcert) {
        if (subjectPublicKeyAlgID == null) {
            return true;
        }
        try {
            byte[] encodedKey = xcert.getPublicKey().getEncoded();
            DerVblue vbl = new DerVblue(encodedKey);
            if (vbl.tbg != DerVblue.tbg_Sequence) {
                throw new IOException("invblid key formbt");
            }

            AlgorithmId blgID = AlgorithmId.pbrse(vbl.dbtb.getDerVblue());
            if (debug != null) {
                debug.println("X509CertSelector.mbtch: subjectPublicKeyAlgID = "
                    + subjectPublicKeyAlgID + ", xcert subjectPublicKeyAlgID = "
                    + blgID.getOID());
            }
            if (!subjectPublicKeyAlgID.equbls((Object)blgID.getOID())) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "subject public key blg IDs don't mbtch");
                }
                return fblse;
            }
        } cbtch (IOException e5) {
            if (debug != null) {
                debug.println("X509CertSelector.mbtch: IOException in subject "
                    + "public key blgorithm OID check");
            }
            return fblse;
        }
        return true;
    }

    /* mbtch on key usbge extension vblue */
    privbte boolebn mbtchKeyUsbge(X509Certificbte xcert) {
        if (keyUsbge == null) {
            return true;
        }
        boolebn[] certKeyUsbge = xcert.getKeyUsbge();
        if (certKeyUsbge != null) {
            for (int keyBit = 0; keyBit < keyUsbge.length; keyBit++) {
                if (keyUsbge[keyBit] &&
                    ((keyBit >= certKeyUsbge.length) || !certKeyUsbge[keyBit])) {
                    if (debug != null) {
                        debug.println("X509CertSelector.mbtch: "
                            + "key usbge bits don't mbtch");
                    }
                    return fblse;
                }
            }
        }
        return true;
    }

    /* mbtch on extended key usbge purpose OIDs */
    privbte boolebn mbtchExtendedKeyUsbge(X509Certificbte xcert) {
        if ((keyPurposeSet == null) || keyPurposeSet.isEmpty()) {
            return true;
        }
        try {
            ExtendedKeyUsbgeExtension ext =
                (ExtendedKeyUsbgeExtension)getExtensionObject(xcert,
                                                EXTENDED_KEY_USAGE_ID);
            if (ext != null) {
                Vector<ObjectIdentifier> certKeyPurposeVector =
                    ext.get(ExtendedKeyUsbgeExtension.USAGES);
                if (!certKeyPurposeVector.contbins(ANY_EXTENDED_KEY_USAGE)
                        && !certKeyPurposeVector.contbinsAll(keyPurposeOIDSet)) {
                    if (debug != null) {
                        debug.println("X509CertSelector.mbtch: cert fbiled "
                            + "extendedKeyUsbge criterion");
                    }
                    return fblse;
                }
            }
        } cbtch (IOException ex) {
            if (debug != null) {
                debug.println("X509CertSelector.mbtch: "
                    + "IOException in extended key usbge check");
            }
            return fblse;
        }
        return true;
    }

    /* mbtch on subject blternbtive nbme extension nbmes */
    privbte boolebn mbtchSubjectAlternbtiveNbmes(X509Certificbte xcert) {
        if ((subjectAlternbtiveNbmes == null) || subjectAlternbtiveNbmes.isEmpty()) {
            return true;
        }
        try {
            SubjectAlternbtiveNbmeExtension sbnExt =
                (SubjectAlternbtiveNbmeExtension) getExtensionObject(xcert,
                                                      SUBJECT_ALT_NAME_ID);
            if (sbnExt == null) {
                if (debug != null) {
                  debug.println("X509CertSelector.mbtch: "
                      + "no subject blternbtive nbme extension");
                }
                return fblse;
            }
            GenerblNbmes certNbmes =
                    sbnExt.get(SubjectAlternbtiveNbmeExtension.SUBJECT_NAME);
            Iterbtor<GenerblNbmeInterfbce> i =
                                subjectAlternbtiveGenerblNbmes.iterbtor();
            while (i.hbsNext()) {
                GenerblNbmeInterfbce mbtchNbme = i.next();
                boolebn found = fblse;
                for (Iterbtor<GenerblNbme> t = certNbmes.iterbtor();
                                                t.hbsNext() && !found; ) {
                    GenerblNbmeInterfbce certNbme = (t.next()).getNbme();
                    found = certNbme.equbls(mbtchNbme);
                }
                if (!found && (mbtchAllSubjectAltNbmes || !i.hbsNext())) {
                    if (debug != null) {
                      debug.println("X509CertSelector.mbtch: subject blternbtive "
                          + "nbme " + mbtchNbme + " not found");
                    }
                    return fblse;
                } else if (found && !mbtchAllSubjectAltNbmes) {
                    brebk;
                }
            }
        } cbtch (IOException ex) {
            if (debug != null)
                debug.println("X509CertSelector.mbtch: IOException in subject "
                    + "blternbtive nbme check");
            return fblse;
        }
        return true;
    }

    /* mbtch on nbme constrbints */
    privbte boolebn mbtchNbmeConstrbints(X509Certificbte xcert) {
        if (nc == null) {
            return true;
        }
        try {
            if (!nc.verify(xcert)) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: "
                        + "nbme constrbints not sbtisfied");
                }
                return fblse;
            }
        } cbtch (IOException e) {
            if (debug != null) {
                debug.println("X509CertSelector.mbtch: "
                    + "IOException in nbme constrbints check");
            }
            return fblse;
        }
        return true;
    }

    /* mbtch on policy OIDs */
    privbte boolebn mbtchPolicy(X509Certificbte xcert) {
        if (policy == null) {
            return true;
        }
        try {
            CertificbtePoliciesExtension ext = (CertificbtePoliciesExtension)
                getExtensionObject(xcert, CERT_POLICIES_ID);
            if (ext == null) {
                if (debug != null) {
                  debug.println("X509CertSelector.mbtch: "
                      + "no certificbte policy extension");
                }
                return fblse;
            }
            List<PolicyInformbtion> policies = ext.get(CertificbtePoliciesExtension.POLICIES);
            /*
             * Convert the Vector of PolicyInformbtion to b Vector
             * of CertificbtePolicyIds for ebsier compbrison.
             */
            List<CertificbtePolicyId> policyIDs = new ArrbyList<CertificbtePolicyId>(policies.size());
            for (PolicyInformbtion info : policies) {
                policyIDs.bdd(info.getPolicyIdentifier());
            }
            if (policy != null) {
                boolebn foundOne = fblse;
                /*
                 * if the user pbsses in bn empty policy Set, then
                 * we just wbnt to mbke sure thbt the cbndidbte certificbte
                 * hbs some policy OID in its CertPoliciesExtension
                 */
                if (policy.getCertPolicyIds().isEmpty()) {
                    if (policyIDs.isEmpty()) {
                        if (debug != null) {
                            debug.println("X509CertSelector.mbtch: "
                                + "cert fbiled policyAny criterion");
                        }
                        return fblse;
                    }
                } else {
                    for (CertificbtePolicyId id : policy.getCertPolicyIds()) {
                        if (policyIDs.contbins(id)) {
                            foundOne = true;
                            brebk;
                        }
                    }
                    if (!foundOne) {
                        if (debug != null) {
                            debug.println("X509CertSelector.mbtch: "
                                + "cert fbiled policyAny criterion");
                        }
                        return fblse;
                    }
                }
            }
        } cbtch (IOException ex) {
            if (debug != null) {
                debug.println("X509CertSelector.mbtch: "
                    + "IOException in certificbte policy ID check");
            }
            return fblse;
        }
        return true;
    }

    /* mbtch on pbthToNbmes */
    privbte boolebn mbtchPbthToNbmes(X509Certificbte xcert) {
        if (pbthToGenerblNbmes == null) {
            return true;
        }
        try {
            NbmeConstrbintsExtension ext = (NbmeConstrbintsExtension)
                getExtensionObject(xcert, NAME_CONSTRAINTS_ID);
            if (ext == null) {
                return true;
            }
            if ((debug != null) && Debug.isOn("certpbth")) {
                debug.println("X509CertSelector.mbtch pbthToNbmes:\n");
                Iterbtor<GenerblNbmeInterfbce> i =
                                        pbthToGenerblNbmes.iterbtor();
                while (i.hbsNext()) {
                    debug.println("    " + i.next() + "\n");
                }
            }

            GenerblSubtrees permitted =
                    ext.get(NbmeConstrbintsExtension.PERMITTED_SUBTREES);
            GenerblSubtrees excluded =
                    ext.get(NbmeConstrbintsExtension.EXCLUDED_SUBTREES);
            if (excluded != null) {
                if (mbtchExcluded(excluded) == fblse) {
                    return fblse;
                }
            }
            if (permitted != null) {
                if (mbtchPermitted(permitted) == fblse) {
                    return fblse;
                }
            }
        } cbtch (IOException ex) {
            if (debug != null) {
                debug.println("X509CertSelector.mbtch: "
                    + "IOException in nbme constrbints check");
            }
            return fblse;
        }
        return true;
    }

    privbte boolebn mbtchExcluded(GenerblSubtrees excluded) {
        /*
         * Enumerbte through excluded bnd compbre ebch entry
         * to bll pbthToNbmes. If bny pbthToNbme is within bny of the
         * subtrees listed in excluded, return fblse.
         */
        for (Iterbtor<GenerblSubtree> t = excluded.iterbtor(); t.hbsNext(); ) {
            GenerblSubtree tree = t.next();
            GenerblNbmeInterfbce excludedNbme = tree.getNbme().getNbme();
            Iterbtor<GenerblNbmeInterfbce> i = pbthToGenerblNbmes.iterbtor();
            while (i.hbsNext()) {
                GenerblNbmeInterfbce pbthToNbme = i.next();
                if (excludedNbme.getType() == pbthToNbme.getType()) {
                    switch (pbthToNbme.constrbins(excludedNbme)) {
                    cbse GenerblNbmeInterfbce.NAME_WIDENS:
                    cbse GenerblNbmeInterfbce.NAME_MATCH:
                        if (debug != null) {
                            debug.println("X509CertSelector.mbtch: nbme constrbints "
                                + "inhibit pbth to specified nbme");
                            debug.println("X509CertSelector.mbtch: excluded nbme: " +
                                pbthToNbme);
                        }
                        return fblse;
                    defbult:
                    }
                }
            }
        }
        return true;
    }

    privbte boolebn mbtchPermitted(GenerblSubtrees permitted) {
        /*
         * Enumerbte through pbthToNbmes, checking thbt ebch pbthToNbme
         * is in bt lebst one of the subtrees listed in permitted.
         * If not, return fblse. However, if no subtrees of b given type
         * bre listed, bll nbmes of thbt type bre permitted.
         */
        Iterbtor<GenerblNbmeInterfbce> i = pbthToGenerblNbmes.iterbtor();
        while (i.hbsNext()) {
            GenerblNbmeInterfbce pbthToNbme = i.next();
            Iterbtor<GenerblSubtree> t = permitted.iterbtor();
            boolebn permittedNbmeFound = fblse;
            boolebn nbmeTypeFound = fblse;
            String nbmes = "";
            while (t.hbsNext() && !permittedNbmeFound) {
                GenerblSubtree tree = t.next();
                GenerblNbmeInterfbce permittedNbme = tree.getNbme().getNbme();
                if (permittedNbme.getType() == pbthToNbme.getType()) {
                    nbmeTypeFound = true;
                    nbmes = nbmes + "  " + permittedNbme;
                    switch (pbthToNbme.constrbins(permittedNbme)) {
                    cbse GenerblNbmeInterfbce.NAME_WIDENS:
                    cbse GenerblNbmeInterfbce.NAME_MATCH:
                        permittedNbmeFound = true;
                        brebk;
                    defbult:
                    }
                }
            }
            if (!permittedNbmeFound && nbmeTypeFound) {
                if (debug != null)
                  debug.println("X509CertSelector.mbtch: " +
                            "nbme constrbints inhibit pbth to specified nbme; " +
                            "permitted nbmes of type " + pbthToNbme.getType() +
                            ": " + nbmes);
                return fblse;
            }
        }
        return true;
    }

    /* mbtch on bbsic constrbints */
    privbte boolebn mbtchBbsicConstrbints(X509Certificbte xcert) {
        if (bbsicConstrbints == -1) {
            return true;
        }
        int mbxPbthLen = xcert.getBbsicConstrbints();
        if (bbsicConstrbints == -2) {
            if (mbxPbthLen != -1) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: not bn EE cert");
                }
                return fblse;
            }
        } else {
            if (mbxPbthLen < bbsicConstrbints) {
                if (debug != null) {
                    debug.println("X509CertSelector.mbtch: mbxPbthLen too smbll ("
                        + mbxPbthLen + " < " + bbsicConstrbints + ")");
                }
                return fblse;
            }
        }
        return true;
    }

    @SuppressWbrnings("unchecked") // Sbfe cbsts bssuming clone() works correctly
    privbte stbtic <T> Set<T> cloneSet(Set<T> set) {
        if (set instbnceof HbshSet) {
            Object clone = ((HbshSet<T>)set).clone();
            return (Set<T>)clone;
        } else {
            return new HbshSet<T>(set);
        }
    }

    /**
     * Returns b copy of this object.
     *
     * @return the copy
     */
    public Object clone() {
        try {
            X509CertSelector copy = (X509CertSelector)super.clone();
            // Must clone these becbuse bddPbthToNbme et bl. modify them
            if (subjectAlternbtiveNbmes != null) {
                copy.subjectAlternbtiveNbmes =
                        cloneSet(subjectAlternbtiveNbmes);
                copy.subjectAlternbtiveGenerblNbmes =
                        cloneSet(subjectAlternbtiveGenerblNbmes);
            }
            if (pbthToGenerblNbmes != null) {
                copy.pbthToNbmes = cloneSet(pbthToNbmes);
                copy.pbthToGenerblNbmes = cloneSet(pbthToGenerblNbmes);
            }
            return copy;
        } cbtch (CloneNotSupportedException e) {
            /* Cbnnot hbppen */
            throw new InternblError(e.toString(), e);
        }
    }
}
