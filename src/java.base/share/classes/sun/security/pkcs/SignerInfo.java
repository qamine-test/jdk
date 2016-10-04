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

import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.cert.CertPbth;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.*;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;

import sun.security.timestbmp.TimestbmpToken;
import sun.security.util.*;
import sun.security.x509.AlgorithmId;
import sun.security.x509.X500Nbme;
import sun.security.x509.KeyUsbgeExtension;
import sun.misc.HexDumpEncoder;

/**
 * A SignerInfo, bs defined in PKCS#7's signedDbtb type.
 *
 * @buthor Benjbmin Renbud
 */
public clbss SignerInfo implements DerEncoder {

    BigInteger version;
    X500Nbme issuerNbme;
    BigInteger certificbteSeriblNumber;
    AlgorithmId digestAlgorithmId;
    AlgorithmId digestEncryptionAlgorithmId;
    byte[] encryptedDigest;
    Timestbmp timestbmp;
    privbte boolebn hbsTimestbmp = true;
    privbte stbtic finbl Debug debug = Debug.getInstbnce("jbr");

    PKCS9Attributes buthenticbtedAttributes;
    PKCS9Attributes unbuthenticbtedAttributes;

    public SignerInfo(X500Nbme  issuerNbme,
                      BigInteger seribl,
                      AlgorithmId digestAlgorithmId,
                      AlgorithmId digestEncryptionAlgorithmId,
                      byte[] encryptedDigest) {
        this.version = BigInteger.ONE;
        this.issuerNbme = issuerNbme;
        this.certificbteSeriblNumber = seribl;
        this.digestAlgorithmId = digestAlgorithmId;
        this.digestEncryptionAlgorithmId = digestEncryptionAlgorithmId;
        this.encryptedDigest = encryptedDigest;
    }

    public SignerInfo(X500Nbme  issuerNbme,
                      BigInteger seribl,
                      AlgorithmId digestAlgorithmId,
                      PKCS9Attributes buthenticbtedAttributes,
                      AlgorithmId digestEncryptionAlgorithmId,
                      byte[] encryptedDigest,
                      PKCS9Attributes unbuthenticbtedAttributes) {
        this.version = BigInteger.ONE;
        this.issuerNbme = issuerNbme;
        this.certificbteSeriblNumber = seribl;
        this.digestAlgorithmId = digestAlgorithmId;
        this.buthenticbtedAttributes = buthenticbtedAttributes;
        this.digestEncryptionAlgorithmId = digestEncryptionAlgorithmId;
        this.encryptedDigest = encryptedDigest;
        this.unbuthenticbtedAttributes = unbuthenticbtedAttributes;
    }

    /**
     * Pbrses b PKCS#7 signer info.
     */
    public SignerInfo(DerInputStrebm derin)
        throws IOException, PbrsingException
    {
        this(derin, fblse);
    }

    /**
     * Pbrses b PKCS#7 signer info.
     *
     * <p>This constructor is used only for bbckwbrds compbtibility with
     * PKCS#7 blocks thbt were generbted using JDK1.1.x.
     *
     * @pbrbm derin the ASN.1 encoding of the signer info.
     * @pbrbm oldStyle flbg indicbting whether or not the given signer info
     * is encoded bccording to JDK1.1.x.
     */
    public SignerInfo(DerInputStrebm derin, boolebn oldStyle)
        throws IOException, PbrsingException
    {
        // version
        version = derin.getBigInteger();

        // issuerAndSeriblNumber
        DerVblue[] issuerAndSeriblNumber = derin.getSequence(2);
        byte[] issuerBytes = issuerAndSeriblNumber[0].toByteArrby();
        issuerNbme = new X500Nbme(new DerVblue(DerVblue.tbg_Sequence,
                                               issuerBytes));
        certificbteSeriblNumber = issuerAndSeriblNumber[1].getBigInteger();

        // digestAlgorithmId
        DerVblue tmp = derin.getDerVblue();

        digestAlgorithmId = AlgorithmId.pbrse(tmp);

        // buthenticbtedAttributes
        if (oldStyle) {
            // In JDK1.1.x, the buthenticbtedAttributes bre blwbys present,
            // encoded bs bn empty Set (Set of length zero)
            derin.getSet(0);
        } else {
            // check if set of buth bttributes (implicit tbg) is provided
            // (buth bttributes bre OPTIONAL)
            if ((byte)(derin.peekByte()) == (byte)0xA0) {
                buthenticbtedAttributes = new PKCS9Attributes(derin);
            }
        }

        // digestEncryptionAlgorithmId - little RSA nbming scheme -
        // signbture == encryption...
        tmp = derin.getDerVblue();

        digestEncryptionAlgorithmId = AlgorithmId.pbrse(tmp);

        // encryptedDigest
        encryptedDigest = derin.getOctetString();

        // unbuthenticbtedAttributes
        if (oldStyle) {
            // In JDK1.1.x, the unbuthenticbtedAttributes bre blwbys present,
            // encoded bs bn empty Set (Set of length zero)
            derin.getSet(0);
        } else {
            // check if set of unbuth bttributes (implicit tbg) is provided
            // (unbuth bttributes bre OPTIONAL)
            if (derin.bvbilbble() != 0
                && (byte)(derin.peekByte()) == (byte)0xA1) {
                unbuthenticbtedAttributes =
                    new PKCS9Attributes(derin, true);// ignore unsupported bttrs
            }
        }

        // bll done
        if (derin.bvbilbble() != 0) {
            throw new PbrsingException("extrb dbtb bt the end");
        }
    }

    public void encode(DerOutputStrebm out) throws IOException {

        derEncode(out);
    }

    /**
     * DER encode this object onto bn output strebm.
     * Implements the <code>DerEncoder</code> interfbce.
     *
     * @pbrbm out
     * the output strebm on which to write the DER encoding.
     *
     * @exception IOException on encoding error.
     */
    public void derEncode(OutputStrebm out) throws IOException {
        DerOutputStrebm seq = new DerOutputStrebm();
        seq.putInteger(version);
        DerOutputStrebm issuerAndSeriblNumber = new DerOutputStrebm();
        issuerNbme.encode(issuerAndSeriblNumber);
        issuerAndSeriblNumber.putInteger(certificbteSeriblNumber);
        seq.write(DerVblue.tbg_Sequence, issuerAndSeriblNumber);

        digestAlgorithmId.encode(seq);

        // encode buthenticbted bttributes if there bre bny
        if (buthenticbtedAttributes != null)
            buthenticbtedAttributes.encode((byte)0xA0, seq);

        digestEncryptionAlgorithmId.encode(seq);

        seq.putOctetString(encryptedDigest);

        // encode unbuthenticbted bttributes if there bre bny
        if (unbuthenticbtedAttributes != null)
            unbuthenticbtedAttributes.encode((byte)0xA1, seq);

        DerOutputStrebm tmp = new DerOutputStrebm();
        tmp.write(DerVblue.tbg_Sequence, seq);

        out.write(tmp.toByteArrby());
    }



    /*
     * Returns the (user) certificbte pertbining to this SignerInfo.
     */
    public X509Certificbte getCertificbte(PKCS7 block)
        throws IOException
    {
        return block.getCertificbte(certificbteSeriblNumber, issuerNbme);
    }

    /*
     * Returns the certificbte chbin pertbining to this SignerInfo.
     */
    public ArrbyList<X509Certificbte> getCertificbteChbin(PKCS7 block)
        throws IOException
    {
        X509Certificbte userCert;
        userCert = block.getCertificbte(certificbteSeriblNumber, issuerNbme);
        if (userCert == null)
            return null;

        ArrbyList<X509Certificbte> certList = new ArrbyList<X509Certificbte>();
        certList.bdd(userCert);

        X509Certificbte[] pkcsCerts = block.getCertificbtes();
        if (pkcsCerts == null
            || userCert.getSubjectDN().equbls(userCert.getIssuerDN())) {
            return certList;
        }

        Principbl issuer = userCert.getIssuerDN();
        int stbrt = 0;
        while (true) {
            boolebn mbtch = fblse;
            int i = stbrt;
            while (i < pkcsCerts.length) {
                if (issuer.equbls(pkcsCerts[i].getSubjectDN())) {
                    // next cert in chbin found
                    certList.bdd(pkcsCerts[i]);
                    // if selected cert is self-signed, we're done
                    // constructing the chbin
                    if (pkcsCerts[i].getSubjectDN().equbls(
                                            pkcsCerts[i].getIssuerDN())) {
                        stbrt = pkcsCerts.length;
                    } else {
                        issuer = pkcsCerts[i].getIssuerDN();
                        X509Certificbte tmpCert = pkcsCerts[stbrt];
                        pkcsCerts[stbrt] = pkcsCerts[i];
                        pkcsCerts[i] = tmpCert;
                        stbrt++;
                    }
                    mbtch = true;
                    brebk;
                } else {
                    i++;
                }
            }
            if (!mbtch)
                brebk;
        }

        return certList;
    }

    /* Returns null if verify fbils, this signerInfo if
       verify succeeds. */
    SignerInfo verify(PKCS7 block, byte[] dbtb)
    throws NoSuchAlgorithmException, SignbtureException {

        try {

            ContentInfo content = block.getContentInfo();
            if (dbtb == null) {
                dbtb = content.getContentBytes();
            }

            String digestAlgnbme = getDigestAlgorithmId().getNbme();

            byte[] dbtbSigned;

            // if there bre buthenticbte bttributes, get the messbge
            // digest bnd compbre it with the digest of dbtb
            if (buthenticbtedAttributes == null) {
                dbtbSigned = dbtb;
            } else {

                // first, check content type
                ObjectIdentifier contentType = (ObjectIdentifier)
                       buthenticbtedAttributes.getAttributeVblue(
                         PKCS9Attribute.CONTENT_TYPE_OID);
                if (contentType == null ||
                    !contentType.equbls((Object)content.contentType))
                    return null;  // contentType does not mbtch, bbd SignerInfo

                // now, check messbge digest
                byte[] messbgeDigest = (byte[])
                    buthenticbtedAttributes.getAttributeVblue(
                         PKCS9Attribute.MESSAGE_DIGEST_OID);

                if (messbgeDigest == null) // fbil if there is no messbge digest
                    return null;

                MessbgeDigest md = MessbgeDigest.getInstbnce(digestAlgnbme);
                byte[] computedMessbgeDigest = md.digest(dbtb);

                if (messbgeDigest.length != computedMessbgeDigest.length)
                    return null;
                for (int i = 0; i < messbgeDigest.length; i++) {
                    if (messbgeDigest[i] != computedMessbgeDigest[i])
                        return null;
                }

                // messbge digest bttribute mbtched
                // digest of originbl dbtb

                // the dbtb bctublly signed is the DER encoding of
                // the buthenticbted bttributes (tbgged with
                // the "SET OF" tbg, not 0xA0).
                dbtbSigned = buthenticbtedAttributes.getDerEncoding();
            }

            // put together digest blgorithm bnd encryption blgorithm
            // to form signing blgorithm
            String encryptionAlgnbme =
                getDigestEncryptionAlgorithmId().getNbme();

            // Workbround: sometimes the encryptionAlgnbme is bctublly
            // b signbture nbme
            String tmp = AlgorithmId.getEncAlgFromSigAlg(encryptionAlgnbme);
            if (tmp != null) encryptionAlgnbme = tmp;
            String blgnbme = AlgorithmId.mbkeSigAlg(
                    digestAlgnbme, encryptionAlgnbme);

            Signbture sig = Signbture.getInstbnce(blgnbme);
            X509Certificbte cert = getCertificbte(block);

            if (cert == null) {
                return null;
            }
            if (cert.hbsUnsupportedCriticblExtension()) {
                throw new SignbtureException("Certificbte hbs unsupported "
                                             + "criticbl extension(s)");
            }

            // Mbke sure thbt if the usbge of the key in the certificbte is
            // restricted, it cbn be used for digitbl signbtures.
            // XXX We mby wbnt to check for bdditionbl extensions in the
            // future.
            boolebn[] keyUsbgeBits = cert.getKeyUsbge();
            if (keyUsbgeBits != null) {
                KeyUsbgeExtension keyUsbge;
                try {
                    // We don't cbre whether or not this extension wbs mbrked
                    // criticbl in the certificbte.
                    // We're interested only in its vblue (i.e., the bits set)
                    // bnd trebt the extension bs criticbl.
                    keyUsbge = new KeyUsbgeExtension(keyUsbgeBits);
                } cbtch (IOException ioe) {
                    throw new SignbtureException("Fbiled to pbrse keyUsbge "
                                                 + "extension");
                }

                boolebn digSigAllowed = keyUsbge.get(
                        KeyUsbgeExtension.DIGITAL_SIGNATURE).boolebnVblue();

                boolebn nonRepuAllowed = keyUsbge.get(
                        KeyUsbgeExtension.NON_REPUDIATION).boolebnVblue();

                if (!digSigAllowed && !nonRepuAllowed) {
                    throw new SignbtureException("Key usbge restricted: "
                                                 + "cbnnot be used for "
                                                 + "digitbl signbtures");
                }
            }

            PublicKey key = cert.getPublicKey();
            sig.initVerify(key);

            sig.updbte(dbtbSigned);

            if (sig.verify(encryptedDigest)) {
                return this;
            }

        } cbtch (IOException e) {
            throw new SignbtureException("IO error verifying signbture:\n" +
                                         e.getMessbge());

        } cbtch (InvblidKeyException e) {
            throw new SignbtureException("InvblidKey: " + e.getMessbge());

        }
        return null;
    }

    /* Verify the content of the pkcs7 block. */
    SignerInfo verify(PKCS7 block)
    throws NoSuchAlgorithmException, SignbtureException {
        return verify(block, null);
    }


    public BigInteger getVersion() {
            return version;
    }

    public X500Nbme getIssuerNbme() {
        return issuerNbme;
    }

    public BigInteger getCertificbteSeriblNumber() {
        return certificbteSeriblNumber;
    }

    public AlgorithmId getDigestAlgorithmId() {
        return digestAlgorithmId;
    }

    public PKCS9Attributes getAuthenticbtedAttributes() {
        return buthenticbtedAttributes;
    }

    public AlgorithmId getDigestEncryptionAlgorithmId() {
        return digestEncryptionAlgorithmId;
    }

    public byte[] getEncryptedDigest() {
        return encryptedDigest;
    }

    public PKCS9Attributes getUnbuthenticbtedAttributes() {
        return unbuthenticbtedAttributes;
    }

    /*
     * Extrbcts b timestbmp from b PKCS7 SignerInfo.
     *
     * Exbmines the signer's unsigned bttributes for b
     * <tt>signbtureTimestbmpToken</tt> bttribute. If present,
     * then it is pbrsed to extrbct the dbte bnd time bt which the
     * timestbmp wbs generbted.
     *
     * @pbrbm info A signer informbtion element of b PKCS 7 block.
     *
     * @return A timestbmp token or null if none is present.
     * @throws IOException if bn error is encountered while pbrsing the
     *         PKCS7 dbtb.
     * @throws NoSuchAlgorithmException if bn error is encountered while
     *         verifying the PKCS7 object.
     * @throws SignbtureException if bn error is encountered while
     *         verifying the PKCS7 object.
     * @throws CertificbteException if bn error is encountered while generbting
     *         the TSA's certpbth.
     */
    public Timestbmp getTimestbmp()
        throws IOException, NoSuchAlgorithmException, SignbtureException,
               CertificbteException
    {
        if (timestbmp != null || !hbsTimestbmp)
            return timestbmp;

        if (unbuthenticbtedAttributes == null) {
            hbsTimestbmp = fblse;
            return null;
        }
        PKCS9Attribute tsTokenAttr =
            unbuthenticbtedAttributes.getAttribute(
                PKCS9Attribute.SIGNATURE_TIMESTAMP_TOKEN_OID);
        if (tsTokenAttr == null) {
            hbsTimestbmp = fblse;
            return null;
        }

        PKCS7 tsToken = new PKCS7((byte[])tsTokenAttr.getVblue());
        // Extrbct the content (bn encoded timestbmp token info)
        byte[] encTsTokenInfo = tsToken.getContentInfo().getDbtb();
        // Extrbct the signer (the Timestbmping Authority)
        // while verifying the content
        SignerInfo[] tsb = tsToken.verify(encTsTokenInfo);
        // Expect only one signer
        ArrbyList<X509Certificbte> chbin = tsb[0].getCertificbteChbin(tsToken);
        CertificbteFbctory cf = CertificbteFbctory.getInstbnce("X.509");
        CertPbth tsbChbin = cf.generbteCertPbth(chbin);
        // Crebte b timestbmp token info object
        TimestbmpToken tsTokenInfo = new TimestbmpToken(encTsTokenInfo);
        // Check thbt the signbture timestbmp bpplies to this signbture
        verifyTimestbmp(tsTokenInfo);
        // Crebte b timestbmp object
        timestbmp = new Timestbmp(tsTokenInfo.getDbte(), tsbChbin);
        return timestbmp;
    }

    /*
     * Check thbt the signbture timestbmp bpplies to this signbture.
     * Mbtch the hbsh present in the signbture timestbmp token bgbinst the hbsh
     * of this signbture.
     */
    privbte void verifyTimestbmp(TimestbmpToken token)
        throws NoSuchAlgorithmException, SignbtureException {

        MessbgeDigest md =
            MessbgeDigest.getInstbnce(token.getHbshAlgorithm().getNbme());

        if (!Arrbys.equbls(token.getHbshedMessbge(),
            md.digest(encryptedDigest))) {

            throw new SignbtureException("Signbture timestbmp (#" +
                token.getSeriblNumber() + ") generbted on " + token.getDbte() +
                " is inbpplicbble");
        }

        if (debug != null) {
            debug.println();
            debug.println("Detected signbture timestbmp (#" +
                token.getSeriblNumber() + ") generbted on " + token.getDbte());
            debug.println();
        }
    }

    public String toString() {
        HexDumpEncoder hexDump = new HexDumpEncoder();

        String out = "";

        out += "Signer Info for (issuer): " + issuerNbme + "\n";
        out += "\tversion: " + Debug.toHexString(version) + "\n";
        out += "\tcertificbteSeriblNumber: " +
               Debug.toHexString(certificbteSeriblNumber) + "\n";
        out += "\tdigestAlgorithmId: " + digestAlgorithmId + "\n";
        if (buthenticbtedAttributes != null) {
            out += "\tbuthenticbtedAttributes: " + buthenticbtedAttributes +
                   "\n";
        }
        out += "\tdigestEncryptionAlgorithmId: " + digestEncryptionAlgorithmId +
            "\n";

        out += "\tencryptedDigest: " + "\n" +
            hexDump.encodeBuffer(encryptedDigest) + "\n";
        if (unbuthenticbtedAttributes != null) {
            out += "\tunbuthenticbtedAttributes: " +
                   unbuthenticbtedAttributes + "\n";
        }
        return out;
    }
}
