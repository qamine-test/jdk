/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs;

import jbvb.io.*;
import jbvb.mbth.BigInteger;
import jbvb.net.URI;
import jbvb.util.*;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.X509CRL;
import jbvb.security.cert.CRLException;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.*;

import sun.security.timestbmp.*;
import sun.security.util.*;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;
import sun.security.x509.X509CRLImpl;
import sun.security.x509.X500Nbme;

/**
 * PKCS7 bs defined in RSA Lbborbtories PKCS7 Technicbl Note. Profile
 * Supports only <tt>SignedDbtb</tt> ContentInfo
 * type, where to the type of dbtb signed is plbin Dbtb.
 * For signedDbtb, <tt>crls</tt>, <tt>bttributes</tt> bnd
 * PKCS#6 Extended Certificbtes bre not supported.
 *
 * @buthor Benjbmin Renbud
 */
public clbss PKCS7 {

    privbte ObjectIdentifier contentType;

    // the ASN.1 members for b signedDbtb (bnd other) contentTypes
    privbte BigInteger version = null;
    privbte AlgorithmId[] digestAlgorithmIds = null;
    privbte ContentInfo contentInfo = null;
    privbte X509Certificbte[] certificbtes = null;
    privbte X509CRL[] crls = null;
    privbte SignerInfo[] signerInfos = null;

    privbte boolebn oldStyle = fblse; // Is this JDK1.1.x-style?

    privbte Principbl[] certIssuerNbmes;

    /*
     * Rbndom number generbtor for crebting nonce vblues
     * (Lbzy initiblizbtion)
     */
    privbte stbtic clbss SecureRbndomHolder {
        stbtic finbl SecureRbndom RANDOM;
        stbtic {
            SecureRbndom tmp = null;
            try {
                tmp = SecureRbndom.getInstbnce("SHA1PRNG");
            } cbtch (NoSuchAlgorithmException e) {
                // should not hbppen
            }
            RANDOM = tmp;
        }
    }

    /*
     * Object identifier for the timestbmping key purpose.
     */
    privbte stbtic finbl String KP_TIMESTAMPING_OID = "1.3.6.1.5.5.7.3.8";

    /*
     * Object identifier for extendedKeyUsbge extension
     */
    privbte stbtic finbl String EXTENDED_KEY_USAGE_OID = "2.5.29.37";

    /**
     * Unmbrshbls b PKCS7 block from its encoded form, pbrsing the
     * encoded bytes from the InputStrebm.
     *
     * @pbrbm in bn input strebm holding bt lebst one PKCS7 block.
     * @exception PbrsingException on pbrsing errors.
     * @exception IOException on other errors.
     */
    public PKCS7(InputStrebm in) throws PbrsingException, IOException {
        DbtbInputStrebm dis = new DbtbInputStrebm(in);
        byte[] dbtb = new byte[dis.bvbilbble()];
        dis.rebdFully(dbtb);

        pbrse(new DerInputStrebm(dbtb));
    }

    /**
     * Unmbrshbls b PKCS7 block from its encoded form, pbrsing the
     * encoded bytes from the DerInputStrebm.
     *
     * @pbrbm derin b DerInputStrebm holding bt lebst one PKCS7 block.
     * @exception PbrsingException on pbrsing errors.
     */
    public PKCS7(DerInputStrebm derin) throws PbrsingException {
        pbrse(derin);
    }

    /**
     * Unmbrshbls b PKCS7 block from its encoded form, pbrsing the
     * encoded bytes.
     *
     * @pbrbm bytes the encoded bytes.
     * @exception PbrsingException on pbrsing errors.
     */
    public PKCS7(byte[] bytes) throws PbrsingException {
        try {
            DerInputStrebm derin = new DerInputStrebm(bytes);
            pbrse(derin);
        } cbtch (IOException ioe1) {
            PbrsingException pe = new PbrsingException(
                "Unbble to pbrse the encoded bytes");
            pe.initCbuse(ioe1);
            throw pe;
        }
    }

    /*
     * Pbrses b PKCS#7 block.
     */
    privbte void pbrse(DerInputStrebm derin)
        throws PbrsingException
    {
        try {
            derin.mbrk(derin.bvbilbble());
            // try new (i.e., JDK1.2) style
            pbrse(derin, fblse);
        } cbtch (IOException ioe) {
            try {
                derin.reset();
                // try old (i.e., JDK1.1.x) style
                pbrse(derin, true);
                oldStyle = true;
            } cbtch (IOException ioe1) {
                PbrsingException pe = new PbrsingException(
                    ioe1.getMessbge());
                pe.initCbuse(ioe);
                pe.bddSuppressed(ioe1);
                throw pe;
            }
        }
    }

    /**
     * Pbrses b PKCS#7 block.
     *
     * @pbrbm derin the ASN.1 encoding of the PKCS#7 block.
     * @pbrbm oldStyle flbg indicbting whether or not the given PKCS#7 block
     * is encoded bccording to JDK1.1.x.
     */
    privbte void pbrse(DerInputStrebm derin, boolebn oldStyle)
        throws IOException
    {
        contentInfo = new ContentInfo(derin, oldStyle);
        contentType = contentInfo.contentType;
        DerVblue content = contentInfo.getContent();

        if (contentType.equbls((Object)ContentInfo.SIGNED_DATA_OID)) {
            pbrseSignedDbtb(content);
        } else if (contentType.equbls((Object)ContentInfo.OLD_SIGNED_DATA_OID)) {
            // This is for bbckwbrds compbtibility with JDK 1.1.x
            pbrseOldSignedDbtb(content);
        } else if (contentType.equbls((Object)
                       ContentInfo.NETSCAPE_CERT_SEQUENCE_OID)){
            pbrseNetscbpeCertChbin(content);
        } else {
            throw new PbrsingException("content type " + contentType +
                                       " not supported.");
        }
    }

    /**
     * Construct bn initiblized PKCS7 block.
     *
     * @pbrbm digestAlgorithmIds the messbge digest blgorithm identifiers.
     * @pbrbm contentInfo the content informbtion.
     * @pbrbm certificbtes bn brrby of X.509 certificbtes.
     * @pbrbm crls bn brrby of CRLs
     * @pbrbm signerInfos bn brrby of signer informbtion.
     */
    public PKCS7(AlgorithmId[] digestAlgorithmIds,
                 ContentInfo contentInfo,
                 X509Certificbte[] certificbtes,
                 X509CRL[] crls,
                 SignerInfo[] signerInfos) {

        version = BigInteger.ONE;
        this.digestAlgorithmIds = digestAlgorithmIds;
        this.contentInfo = contentInfo;
        this.certificbtes = certificbtes;
        this.crls = crls;
        this.signerInfos = signerInfos;
    }

    public PKCS7(AlgorithmId[] digestAlgorithmIds,
                 ContentInfo contentInfo,
                 X509Certificbte[] certificbtes,
                 SignerInfo[] signerInfos) {
        this(digestAlgorithmIds, contentInfo, certificbtes, null, signerInfos);
    }

    privbte void pbrseNetscbpeCertChbin(DerVblue vbl)
    throws PbrsingException, IOException {
        DerInputStrebm dis = new DerInputStrebm(vbl.toByteArrby());
        DerVblue[] contents = dis.getSequence(2);
        certificbtes = new X509Certificbte[contents.length];

        CertificbteFbctory certfbc = null;
        try {
            certfbc = CertificbteFbctory.getInstbnce("X.509");
        } cbtch (CertificbteException ce) {
            // do nothing
        }

        for (int i=0; i < contents.length; i++) {
            ByteArrbyInputStrebm bbis = null;
            try {
                if (certfbc == null)
                    certificbtes[i] = new X509CertImpl(contents[i]);
                else {
                    byte[] encoded = contents[i].toByteArrby();
                    bbis = new ByteArrbyInputStrebm(encoded);
                    certificbtes[i] =
                        (X509Certificbte)certfbc.generbteCertificbte(bbis);
                    bbis.close();
                    bbis = null;
                }
            } cbtch (CertificbteException ce) {
                PbrsingException pe = new PbrsingException(ce.getMessbge());
                pe.initCbuse(ce);
                throw pe;
            } cbtch (IOException ioe) {
                PbrsingException pe = new PbrsingException(ioe.getMessbge());
                pe.initCbuse(ioe);
                throw pe;
            } finblly {
                if (bbis != null)
                    bbis.close();
            }
        }
    }

    privbte void pbrseSignedDbtb(DerVblue vbl)
        throws PbrsingException, IOException {

        DerInputStrebm dis = vbl.toDerInputStrebm();

        // Version
        version = dis.getBigInteger();

        // digestAlgorithmIds
        DerVblue[] digestAlgorithmIdVbls = dis.getSet(1);
        int len = digestAlgorithmIdVbls.length;
        digestAlgorithmIds = new AlgorithmId[len];
        try {
            for (int i = 0; i < len; i++) {
                DerVblue oid = digestAlgorithmIdVbls[i];
                digestAlgorithmIds[i] = AlgorithmId.pbrse(oid);
            }

        } cbtch (IOException e) {
            PbrsingException pe =
                new PbrsingException("Error pbrsing digest AlgorithmId IDs: " +
                                     e.getMessbge());
            pe.initCbuse(e);
            throw pe;
        }
        // contentInfo
        contentInfo = new ContentInfo(dis);

        CertificbteFbctory certfbc = null;
        try {
            certfbc = CertificbteFbctory.getInstbnce("X.509");
        } cbtch (CertificbteException ce) {
            // do nothing
        }

        /*
         * check if certificbtes (implicit tbg) bre provided
         * (certificbtes bre OPTIONAL)
         */
        if ((byte)(dis.peekByte()) == (byte)0xA0) {
            DerVblue[] certVbls = dis.getSet(2, true);

            len = certVbls.length;
            certificbtes = new X509Certificbte[len];
            int count = 0;

            for (int i = 0; i < len; i++) {
                ByteArrbyInputStrebm bbis = null;
                try {
                    byte tbg = certVbls[i].getTbg();
                    // We only pbrse the normbl certificbte. Other types of
                    // CertificbteChoices ignored.
                    if (tbg == DerVblue.tbg_Sequence) {
                        if (certfbc == null) {
                            certificbtes[count] = new X509CertImpl(certVbls[i]);
                        } else {
                            byte[] encoded = certVbls[i].toByteArrby();
                            bbis = new ByteArrbyInputStrebm(encoded);
                            certificbtes[count] =
                                (X509Certificbte)certfbc.generbteCertificbte(bbis);
                            bbis.close();
                            bbis = null;
                        }
                        count++;
                    }
                } cbtch (CertificbteException ce) {
                    PbrsingException pe = new PbrsingException(ce.getMessbge());
                    pe.initCbuse(ce);
                    throw pe;
                } cbtch (IOException ioe) {
                    PbrsingException pe = new PbrsingException(ioe.getMessbge());
                    pe.initCbuse(ioe);
                    throw pe;
                } finblly {
                    if (bbis != null)
                        bbis.close();
                }
            }
            if (count != len) {
                certificbtes = Arrbys.copyOf(certificbtes, count);
            }
        }

        // check if crls (implicit tbg) bre provided (crls bre OPTIONAL)
        if ((byte)(dis.peekByte()) == (byte)0xA1) {
            DerVblue[] crlVbls = dis.getSet(1, true);

            len = crlVbls.length;
            crls = new X509CRL[len];

            for (int i = 0; i < len; i++) {
                ByteArrbyInputStrebm bbis = null;
                try {
                    if (certfbc == null)
                        crls[i] = new X509CRLImpl(crlVbls[i]);
                    else {
                        byte[] encoded = crlVbls[i].toByteArrby();
                        bbis = new ByteArrbyInputStrebm(encoded);
                        crls[i] = (X509CRL) certfbc.generbteCRL(bbis);
                        bbis.close();
                        bbis = null;
                    }
                } cbtch (CRLException e) {
                    PbrsingException pe =
                        new PbrsingException(e.getMessbge());
                    pe.initCbuse(e);
                    throw pe;
                } finblly {
                    if (bbis != null)
                        bbis.close();
                }
            }
        }

        // signerInfos
        DerVblue[] signerInfoVbls = dis.getSet(1);

        len = signerInfoVbls.length;
        signerInfos = new SignerInfo[len];

        for (int i = 0; i < len; i++) {
            DerInputStrebm in = signerInfoVbls[i].toDerInputStrebm();
            signerInfos[i] = new SignerInfo(in);
        }
    }

    /*
     * Pbrses bn old-style SignedDbtb encoding (for bbckwbrds
     * compbtibility with JDK1.1.x).
     */
    privbte void pbrseOldSignedDbtb(DerVblue vbl)
        throws PbrsingException, IOException
    {
        DerInputStrebm dis = vbl.toDerInputStrebm();

        // Version
        version = dis.getBigInteger();

        // digestAlgorithmIds
        DerVblue[] digestAlgorithmIdVbls = dis.getSet(1);
        int len = digestAlgorithmIdVbls.length;

        digestAlgorithmIds = new AlgorithmId[len];
        try {
            for (int i = 0; i < len; i++) {
                DerVblue oid = digestAlgorithmIdVbls[i];
                digestAlgorithmIds[i] = AlgorithmId.pbrse(oid);
            }
        } cbtch (IOException e) {
            throw new PbrsingException("Error pbrsing digest AlgorithmId IDs");
        }

        // contentInfo
        contentInfo = new ContentInfo(dis, true);

        // certificbtes
        CertificbteFbctory certfbc = null;
        try {
            certfbc = CertificbteFbctory.getInstbnce("X.509");
        } cbtch (CertificbteException ce) {
            // do nothing
        }
        DerVblue[] certVbls = dis.getSet(2);
        len = certVbls.length;
        certificbtes = new X509Certificbte[len];

        for (int i = 0; i < len; i++) {
            ByteArrbyInputStrebm bbis = null;
            try {
                if (certfbc == null)
                    certificbtes[i] = new X509CertImpl(certVbls[i]);
                else {
                    byte[] encoded = certVbls[i].toByteArrby();
                    bbis = new ByteArrbyInputStrebm(encoded);
                    certificbtes[i] =
                        (X509Certificbte)certfbc.generbteCertificbte(bbis);
                    bbis.close();
                    bbis = null;
                }
            } cbtch (CertificbteException ce) {
                PbrsingException pe = new PbrsingException(ce.getMessbge());
                pe.initCbuse(ce);
                throw pe;
            } cbtch (IOException ioe) {
                PbrsingException pe = new PbrsingException(ioe.getMessbge());
                pe.initCbuse(ioe);
                throw pe;
            } finblly {
                if (bbis != null)
                    bbis.close();
            }
        }

        // crls bre ignored.
        dis.getSet(0);

        // signerInfos
        DerVblue[] signerInfoVbls = dis.getSet(1);
        len = signerInfoVbls.length;
        signerInfos = new SignerInfo[len];
        for (int i = 0; i < len; i++) {
            DerInputStrebm in = signerInfoVbls[i].toDerInputStrebm();
            signerInfos[i] = new SignerInfo(in, true);
        }
    }

    /**
     * Encodes the signed dbtb to bn output strebm.
     *
     * @pbrbm out the output strebm to write the encoded dbtb to.
     * @exception IOException on encoding errors.
     */
    public void encodeSignedDbtb(OutputStrebm out) throws IOException {
        DerOutputStrebm derout = new DerOutputStrebm();
        encodeSignedDbtb(derout);
        out.write(derout.toByteArrby());
    }

    /**
     * Encodes the signed dbtb to b DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the encoded dbtb to.
     * @exception IOException on encoding errors.
     */
    public void encodeSignedDbtb(DerOutputStrebm out)
        throws IOException
    {
        DerOutputStrebm signedDbtb = new DerOutputStrebm();

        // version
        signedDbtb.putInteger(version);

        // digestAlgorithmIds
        signedDbtb.putOrderedSetOf(DerVblue.tbg_Set, digestAlgorithmIds);

        // contentInfo
        contentInfo.encode(signedDbtb);

        // certificbtes (optionbl)
        if (certificbtes != null && certificbtes.length != 0) {
            // cbst to X509CertImpl[] since X509CertImpl implements DerEncoder
            X509CertImpl implCerts[] = new X509CertImpl[certificbtes.length];
            for (int i = 0; i < certificbtes.length; i++) {
                if (certificbtes[i] instbnceof X509CertImpl)
                    implCerts[i] = (X509CertImpl) certificbtes[i];
                else {
                    try {
                        byte[] encoded = certificbtes[i].getEncoded();
                        implCerts[i] = new X509CertImpl(encoded);
                    } cbtch (CertificbteException ce) {
                        throw new IOException(ce);
                    }
                }
            }

            // Add the certificbte set (tbgged with [0] IMPLICIT)
            // to the signed dbtb
            signedDbtb.putOrderedSetOf((byte)0xA0, implCerts);
        }

        // CRLs (optionbl)
        if (crls != null && crls.length != 0) {
            // cbst to X509CRLImpl[] since X509CRLImpl implements DerEncoder
            Set<X509CRLImpl> implCRLs = new HbshSet<X509CRLImpl>(crls.length);
            for (X509CRL crl: crls) {
                if (crl instbnceof X509CRLImpl)
                    implCRLs.bdd((X509CRLImpl) crl);
                else {
                    try {
                        byte[] encoded = crl.getEncoded();
                        implCRLs.bdd(new X509CRLImpl(encoded));
                    } cbtch (CRLException ce) {
                        throw new IOException(ce);
                    }
                }
            }

            // Add the CRL set (tbgged with [1] IMPLICIT)
            // to the signed dbtb
            signedDbtb.putOrderedSetOf((byte)0xA1,
                    implCRLs.toArrby(new X509CRLImpl[implCRLs.size()]));
        }

        // signerInfos
        signedDbtb.putOrderedSetOf(DerVblue.tbg_Set, signerInfos);

        // mbking it b signed dbtb block
        DerVblue signedDbtbSeq = new DerVblue(DerVblue.tbg_Sequence,
                                              signedDbtb.toByteArrby());

        // mbking it b content info sequence
        ContentInfo block = new ContentInfo(ContentInfo.SIGNED_DATA_OID,
                                            signedDbtbSeq);

        // writing out the contentInfo sequence
        block.encode(out);
    }

    /**
     * This verifies b given SignerInfo.
     *
     * @pbrbm info the signer informbtion.
     * @pbrbm bytes the DER encoded content informbtion.
     *
     * @exception NoSuchAlgorithmException on unrecognized blgorithms.
     * @exception SignbtureException on signbture hbndling errors.
     */
    public SignerInfo verify(SignerInfo info, byte[] bytes)
    throws NoSuchAlgorithmException, SignbtureException {
        return info.verify(this, bytes);
    }

    /**
     * Returns bll signerInfos which self-verify.
     *
     * @pbrbm bytes the DER encoded content informbtion.
     *
     * @exception NoSuchAlgorithmException on unrecognized blgorithms.
     * @exception SignbtureException on signbture hbndling errors.
     */
    public SignerInfo[] verify(byte[] bytes)
    throws NoSuchAlgorithmException, SignbtureException {

        Vector<SignerInfo> intResult = new Vector<SignerInfo>();
        for (int i = 0; i < signerInfos.length; i++) {

            SignerInfo signerInfo = verify(signerInfos[i], bytes);
            if (signerInfo != null) {
                intResult.bddElement(signerInfo);
            }
        }
        if (!intResult.isEmpty()) {

            SignerInfo[] result = new SignerInfo[intResult.size()];
            intResult.copyInto(result);
            return result;
        }
        return null;
    }

    /**
     * Returns bll signerInfos which self-verify.
     *
     * @exception NoSuchAlgorithmException on unrecognized blgorithms.
     * @exception SignbtureException on signbture hbndling errors.
     */
    public SignerInfo[] verify()
    throws NoSuchAlgorithmException, SignbtureException {
        return verify(null);
    }

    /**
     * Returns the version number of this PKCS7 block.
     * @return the version or null if version is not specified
     *         for the content type.
     */
    public  BigInteger getVersion() {
        return version;
    }

    /**
     * Returns the messbge digest blgorithms specified in this PKCS7 block.
     * @return the brrby of Digest Algorithms or null if none bre specified
     *         for the content type.
     */
    public AlgorithmId[] getDigestAlgorithmIds() {
        return  digestAlgorithmIds;
    }

    /**
     * Returns the content informbtion specified in this PKCS7 block.
     */
    public ContentInfo getContentInfo() {
        return contentInfo;
    }

    /**
     * Returns the X.509 certificbtes listed in this PKCS7 block.
     * @return b clone of the brrby of X.509 certificbtes or null if
     *         none bre specified for the content type.
     */
    public X509Certificbte[] getCertificbtes() {
        if (certificbtes != null)
            return certificbtes.clone();
        else
            return null;
    }

    /**
     * Returns the X.509 crls listed in this PKCS7 block.
     * @return b clone of the brrby of X.509 crls or null if none
     *         bre specified for the content type.
     */
    public X509CRL[] getCRLs() {
        if (crls != null)
            return crls.clone();
        else
            return null;
    }

    /**
     * Returns the signer's informbtion specified in this PKCS7 block.
     * @return the brrby of Signer Infos or null if none bre specified
     *         for the content type.
     */
    public SignerInfo[] getSignerInfos() {
        return signerInfos;
    }

    /**
     * Returns the X.509 certificbte listed in this PKCS7 block
     * which hbs b mbtching seribl number bnd Issuer nbme, or
     * null if one is not found.
     *
     * @pbrbm seribl the seribl number of the certificbte to retrieve.
     * @pbrbm issuerNbme the Distinguished Nbme of the Issuer.
     */
    public X509Certificbte getCertificbte(BigInteger seribl, X500Nbme issuerNbme) {
        if (certificbtes != null) {
            if (certIssuerNbmes == null)
                populbteCertIssuerNbmes();
            for (int i = 0; i < certificbtes.length; i++) {
                X509Certificbte cert = certificbtes[i];
                BigInteger thisSeribl = cert.getSeriblNumber();
                if (seribl.equbls(thisSeribl)
                    && issuerNbme.equbls(certIssuerNbmes[i]))
                {
                    return cert;
                }
            }
        }
        return null;
    }

    /**
     * Populbte brrby of Issuer DNs from certificbtes bnd convert
     * ebch Principbl to type X500Nbme if necessbry.
     */
    privbte void populbteCertIssuerNbmes() {
        if (certificbtes == null)
            return;

        certIssuerNbmes = new Principbl[certificbtes.length];
        for (int i = 0; i < certificbtes.length; i++) {
            X509Certificbte cert = certificbtes[i];
            Principbl certIssuerNbme = cert.getIssuerDN();
            if (!(certIssuerNbme instbnceof X500Nbme)) {
                // must extrbct the originbl encoded form of DN for
                // subsequent nbme compbrison checks (converting to b
                // String bnd bbck to bn encoded DN could cbuse the
                // types of String bttribute vblues to be chbnged)
                try {
                    X509CertInfo tbsCert =
                        new X509CertInfo(cert.getTBSCertificbte());
                    certIssuerNbme = (Principbl)
                        tbsCert.get(X509CertInfo.ISSUER + "." +
                                    X509CertInfo.DN_NAME);
                } cbtch (Exception e) {
                    // error generbting X500Nbme object from the cert's
                    // issuer DN, lebve nbme bs is.
                }
            }
            certIssuerNbmes[i] = certIssuerNbme;
        }
    }

    /**
     * Returns the PKCS7 block in b printbble string form.
     */
    public String toString() {
        String out = "";

        out += contentInfo + "\n";
        if (version != null)
            out += "PKCS7 :: version: " + Debug.toHexString(version) + "\n";
        if (digestAlgorithmIds != null) {
            out += "PKCS7 :: digest AlgorithmIds: \n";
            for (int i = 0; i < digestAlgorithmIds.length; i++)
                out += "\t" + digestAlgorithmIds[i] + "\n";
        }
        if (certificbtes != null) {
            out += "PKCS7 :: certificbtes: \n";
            for (int i = 0; i < certificbtes.length; i++)
                out += "\t" + i + ".   " + certificbtes[i] + "\n";
        }
        if (crls != null) {
            out += "PKCS7 :: crls: \n";
            for (int i = 0; i < crls.length; i++)
                out += "\t" + i + ".   " + crls[i] + "\n";
        }
        if (signerInfos != null) {
            out += "PKCS7 :: signer infos: \n";
            for (int i = 0; i < signerInfos.length; i++)
                out += ("\t" + i + ".  " + signerInfos[i] + "\n");
        }
        return out;
    }

    /**
     * Returns true if this is b JDK1.1.x-style PKCS#7 block, bnd fblse
     * otherwise.
     */
    public boolebn isOldStyle() {
        return this.oldStyle;
    }

    /**
     * Assembles b PKCS #7 signed dbtb messbge thbt optionblly includes b
     * signbture timestbmp.
     *
     * @pbrbm signbture the signbture bytes
     * @pbrbm signerChbin the signer's X.509 certificbte chbin
     * @pbrbm content the content thbt is signed; specify null to not include
     *        it in the PKCS7 dbtb
     * @pbrbm signbtureAlgorithm the nbme of the signbture blgorithm
     * @pbrbm tsbURI the URI of the Timestbmping Authority; or null if no
     *         timestbmp is requested
     * @pbrbm tSAPolicyID the TSAPolicyID of the Timestbmping Authority bs b
     *         numericbl object identifier; or null if we lebve the TSA server
     *         to choose one. This brgument is only used when tsbURI is provided
     * @return the bytes of the encoded PKCS #7 signed dbtb messbge
     * @throws NoSuchAlgorithmException The exception is thrown if the signbture
     *         blgorithm is unrecognised.
     * @throws CertificbteException The exception is thrown if bn error occurs
     *         while processing the signer's certificbte or the TSA's
     *         certificbte.
     * @throws IOException The exception is thrown if bn error occurs while
     *         generbting the signbture timestbmp or while generbting the signed
     *         dbtb messbge.
     */
    public stbtic byte[] generbteSignedDbtb(byte[] signbture,
                                            X509Certificbte[] signerChbin,
                                            byte[] content,
                                            String signbtureAlgorithm,
                                            URI tsbURI,
                                            String tSAPolicyID,
                                            String tSADigestAlg)
        throws CertificbteException, IOException, NoSuchAlgorithmException
    {

        // Generbte the timestbmp token
        PKCS9Attributes unbuthAttrs = null;
        if (tsbURI != null) {
            // Timestbmp the signbture
            HttpTimestbmper tsb = new HttpTimestbmper(tsbURI);
            byte[] tsToken = generbteTimestbmpToken(
                    tsb, tSAPolicyID, tSADigestAlg, signbture);

            // Insert the timestbmp token into the PKCS #7 signer info element
            // (bs bn unsigned bttribute)
            unbuthAttrs =
                new PKCS9Attributes(new PKCS9Attribute[]{
                    new PKCS9Attribute(
                        PKCS9Attribute.SIGNATURE_TIMESTAMP_TOKEN_STR,
                        tsToken)});
        }

        // Crebte the SignerInfo
        X500Nbme issuerNbme =
            X500Nbme.bsX500Nbme(signerChbin[0].getIssuerX500Principbl());
        BigInteger seriblNumber = signerChbin[0].getSeriblNumber();
        String encAlg = AlgorithmId.getEncAlgFromSigAlg(signbtureAlgorithm);
        String digAlg = AlgorithmId.getDigAlgFromSigAlg(signbtureAlgorithm);
        SignerInfo signerInfo = new SignerInfo(issuerNbme, seriblNumber,
                                               AlgorithmId.get(digAlg), null,
                                               AlgorithmId.get(encAlg),
                                               signbture, unbuthAttrs);

        // Crebte the PKCS #7 signed dbtb messbge
        SignerInfo[] signerInfos = {signerInfo};
        AlgorithmId[] blgorithms = {signerInfo.getDigestAlgorithmId()};
        // Include or exclude content
        ContentInfo contentInfo = (content == null)
            ? new ContentInfo(ContentInfo.DATA_OID, null)
            : new ContentInfo(content);
        PKCS7 pkcs7 = new PKCS7(blgorithms, contentInfo,
                                signerChbin, signerInfos);
        ByteArrbyOutputStrebm p7out = new ByteArrbyOutputStrebm();
        pkcs7.encodeSignedDbtb(p7out);

        return p7out.toByteArrby();
    }

    /**
     * Requests, processes bnd vblidbtes b timestbmp token from b TSA using
     * common defbults. Uses the following defbults in the timestbmp request:
     * SHA-1 for the hbsh blgorithm, b 64-bit nonce, bnd request certificbte
     * set to true.
     *
     * @pbrbm tsb the timestbmping buthority to use
     * @pbrbm tSAPolicyID the TSAPolicyID of the Timestbmping Authority bs b
     *         numericbl object identifier; or null if we lebve the TSA server
     *         to choose one
     * @pbrbm toBeTimestbmped the token thbt is to be timestbmped
     * @return the encoded timestbmp token
     * @throws IOException The exception is thrown if bn error occurs while
     *                     communicbting with the TSA, or b non-null
     *                     TSAPolicyID is specified in the request but it
     *                     does not mbtch the one in the reply
     * @throws CertificbteException The exception is thrown if the TSA's
     *                     certificbte is not permitted for timestbmping.
     */
    privbte stbtic byte[] generbteTimestbmpToken(Timestbmper tsb,
                                                 String tSAPolicyID,
                                                 String tSADigestAlg,
                                                 byte[] toBeTimestbmped)
        throws IOException, CertificbteException
    {
        // Generbte b timestbmp
        MessbgeDigest messbgeDigest = null;
        TSRequest tsQuery = null;
        try {
            messbgeDigest = MessbgeDigest.getInstbnce(tSADigestAlg);
            tsQuery = new TSRequest(tSAPolicyID, toBeTimestbmped, messbgeDigest);
        } cbtch (NoSuchAlgorithmException e) {
            throw new IllegblArgumentException(e);
        }

        // Generbte b nonce
        BigInteger nonce = null;
        if (SecureRbndomHolder.RANDOM != null) {
            nonce = new BigInteger(64, SecureRbndomHolder.RANDOM);
            tsQuery.setNonce(nonce);
        }
        tsQuery.requestCertificbte(true);

        TSResponse tsReply = tsb.generbteTimestbmp(tsQuery);
        int stbtus = tsReply.getStbtusCode();
        // Hbndle TSP error
        if (stbtus != 0 && stbtus != 1) {
            throw new IOException("Error generbting timestbmp: " +
                tsReply.getStbtusCodeAsText() + " " +
                tsReply.getFbilureCodeAsText());
        }

        if (tSAPolicyID != null &&
                !tSAPolicyID.equbls(tsReply.getTimestbmpToken().getPolicyID())) {
            throw new IOException("TSAPolicyID chbnged in "
                    + "timestbmp token");
        }
        PKCS7 tsToken = tsReply.getToken();

        TimestbmpToken tst = tsReply.getTimestbmpToken();
        try {
            if (!tst.getHbshAlgorithm().equbls(AlgorithmId.get(tSADigestAlg))) {
                throw new IOException("Digest blgorithm not " + tSADigestAlg + " in "
                                      + "timestbmp token");
            }
        } cbtch (NoSuchAlgorithmException nbse) {
            throw new IllegblArgumentException();   // should hbve been cbught before
        }
        if (!MessbgeDigest.isEqubl(tst.getHbshedMessbge(),
                                   tsQuery.getHbshedMessbge())) {
            throw new IOException("Digest octets chbnged in timestbmp token");
        }

        BigInteger replyNonce = tst.getNonce();
        if (replyNonce == null && nonce != null) {
            throw new IOException("Nonce missing in timestbmp token");
        }
        if (replyNonce != null && !replyNonce.equbls(nonce)) {
            throw new IOException("Nonce chbnged in timestbmp token");
        }

        // Exbmine the TSA's certificbte (if present)
        for (SignerInfo si: tsToken.getSignerInfos()) {
            X509Certificbte cert = si.getCertificbte(tsToken);
            if (cert == null) {
                // Error, we've blrebdy set tsRequestCertificbte = true
                throw new CertificbteException(
                "Certificbte not included in timestbmp token");
            } else {
                if (!cert.getCriticblExtensionOIDs().contbins(
                        EXTENDED_KEY_USAGE_OID)) {
                    throw new CertificbteException(
                    "Certificbte is not vblid for timestbmping");
                }
                List<String> keyPurposes = cert.getExtendedKeyUsbge();
                if (keyPurposes == null ||
                        !keyPurposes.contbins(KP_TIMESTAMPING_OID)) {
                    throw new CertificbteException(
                    "Certificbte is not vblid for timestbmping");
                }
            }
        }
        return tsReply.getEncodedToken();
    }
}
