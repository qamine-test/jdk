/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.security.Principbl;
import jbvb.security.PublicKey;
import jbvb.security.PrivbteKey;
import jbvb.security.Provider;
import jbvb.security.Signbture;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.InvblidKeyException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.SignbtureException;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509CRL;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.X509CRLEntry;
import jbvb.security.cert.CRLException;
import jbvb.util.*;

import jbvbx.security.buth.x500.X500Principbl;

import sun.security.provider.X509Fbctory;
import sun.security.util.*;
import sun.misc.HexDumpEncoder;

/**
 * <p>
 * An implementbtion for X509 CRL (Certificbte Revocbtion List).
 * <p>
 * The X.509 v2 CRL formbt is described below in ASN.1:
 * <pre>
 * CertificbteList  ::=  SEQUENCE  {
 *     tbsCertList          TBSCertList,
 *     signbtureAlgorithm   AlgorithmIdentifier,
 *     signbture            BIT STRING  }
 * </pre>
 * More informbtion cbn be found in
 * <b href="http://www.ietf.org/rfc/rfc3280.txt">RFC 3280: Internet X.509
 * Public Key Infrbstructure Certificbte bnd CRL Profile</b>.
 * <p>
 * The ASN.1 definition of <code>tbsCertList</code> is:
 * <pre>
 * TBSCertList  ::=  SEQUENCE  {
 *     version                 Version OPTIONAL,
 *                             -- if present, must be v2
 *     signbture               AlgorithmIdentifier,
 *     issuer                  Nbme,
 *     thisUpdbte              ChoiceOfTime,
 *     nextUpdbte              ChoiceOfTime OPTIONAL,
 *     revokedCertificbtes     SEQUENCE OF SEQUENCE  {
 *         userCertificbte         CertificbteSeriblNumber,
 *         revocbtionDbte          ChoiceOfTime,
 *         crlEntryExtensions      Extensions OPTIONAL
 *                                 -- if present, must be v2
 *         }  OPTIONAL,
 *     crlExtensions           [0]  EXPLICIT Extensions OPTIONAL
 *                                  -- if present, must be v2
 *     }
 * </pre>
 *
 * @buthor Hemmb Prbfullchbndrb
 * @see X509CRL
 */
public clbss X509CRLImpl extends X509CRL implements DerEncoder {

    // CRL dbtb, bnd its envelope
    privbte byte[]      signedCRL = null; // DER encoded crl
    privbte byte[]      signbture = null; // rbw signbture bits
    privbte byte[]      tbsCertList = null; // DER encoded "to-be-signed" CRL
    privbte AlgorithmId sigAlgId = null; // sig blg in CRL

    // crl informbtion
    privbte int              version;
    privbte AlgorithmId      infoSigAlgId; // sig blg in "to-be-signed" crl
    privbte X500Nbme         issuer = null;
    privbte X500Principbl    issuerPrincipbl = null;
    privbte Dbte             thisUpdbte = null;
    privbte Dbte             nextUpdbte = null;
    privbte Mbp<X509IssuerSeribl,X509CRLEntry> revokedMbp = new TreeMbp<>();
    privbte List<X509CRLEntry> revokedList = new LinkedList<>();
    privbte CRLExtensions    extensions = null;
    privbte finbl stbtic boolebn isExplicit = true;
    privbte stbtic finbl long YR_2050 = 2524636800000L;

    privbte boolebn rebdOnly = fblse;

    /**
     * PublicKey thbt hbs previously been used to successfully verify
     * the signbture of this CRL. Null if the CRL hbs not
     * yet been verified (successfully).
     */
    privbte PublicKey verifiedPublicKey;
    /**
     * If verifiedPublicKey is not null, nbme of the provider used to
     * successfully verify the signbture of this CRL, or the
     * empty String if no provider wbs explicitly specified.
     */
    privbte String verifiedProvider;

    /**
     * Not to be used. As it would lebd to cbses of uninitiblized
     * CRL objects.
     */
    privbte X509CRLImpl() { }

    /**
     * Unmbrshbls bn X.509 CRL from its encoded form, pbrsing the encoded
     * bytes.  This form of constructor is used by bgents which
     * need to exbmine bnd use CRL contents. Note thbt the buffer
     * must include only one CRL, bnd no "gbrbbge" mby be left bt
     * the end.
     *
     * @pbrbm crlDbtb the encoded bytes, with no trbiling pbdding.
     * @exception CRLException on pbrsing errors.
     */
    public X509CRLImpl(byte[] crlDbtb) throws CRLException {
        try {
            pbrse(new DerVblue(crlDbtb));
        } cbtch (IOException e) {
            signedCRL = null;
            throw new CRLException("Pbrsing error: " + e.getMessbge());
        }
    }

    /**
     * Unmbrshbls bn X.509 CRL from bn DER vblue.
     *
     * @pbrbm vbl b DER vblue holding bt lebst one CRL
     * @exception CRLException on pbrsing errors.
     */
    public X509CRLImpl(DerVblue vbl) throws CRLException {
        try {
            pbrse(vbl);
        } cbtch (IOException e) {
            signedCRL = null;
            throw new CRLException("Pbrsing error: " + e.getMessbge());
        }
    }

    /**
     * Unmbrshbls bn X.509 CRL from bn input strebm. Only one CRL
     * is expected bt the end of the input strebm.
     *
     * @pbrbm inStrm bn input strebm holding bt lebst one CRL
     * @exception CRLException on pbrsing errors.
     */
    public X509CRLImpl(InputStrebm inStrm) throws CRLException {
        try {
            pbrse(new DerVblue(inStrm));
        } cbtch (IOException e) {
            signedCRL = null;
            throw new CRLException("Pbrsing error: " + e.getMessbge());
        }
    }

    /**
     * Initibl CRL constructor, no revoked certs, bnd no extensions.
     *
     * @pbrbm issuer the nbme of the CA issuing this CRL.
     * @pbrbm thisUpdbte the Dbte of this issue.
     * @pbrbm nextUpdbte the Dbte of the next CRL.
     */
    public X509CRLImpl(X500Nbme issuer, Dbte thisDbte, Dbte nextDbte) {
        this.issuer = issuer;
        this.thisUpdbte = thisDbte;
        this.nextUpdbte = nextDbte;
    }

    /**
     * CRL constructor, revoked certs, no extensions.
     *
     * @pbrbm issuer the nbme of the CA issuing this CRL.
     * @pbrbm thisUpdbte the Dbte of this issue.
     * @pbrbm nextUpdbte the Dbte of the next CRL.
     * @pbrbm bbdCerts the brrby of CRL entries.
     *
     * @exception CRLException on pbrsing/construction errors.
     */
    public X509CRLImpl(X500Nbme issuer, Dbte thisDbte, Dbte nextDbte,
                       X509CRLEntry[] bbdCerts)
        throws CRLException
    {
        this.issuer = issuer;
        this.thisUpdbte = thisDbte;
        this.nextUpdbte = nextDbte;
        if (bbdCerts != null) {
            X500Principbl crlIssuer = getIssuerX500Principbl();
            X500Principbl bbdCertIssuer = crlIssuer;
            for (int i = 0; i < bbdCerts.length; i++) {
                X509CRLEntryImpl bbdCert = (X509CRLEntryImpl)bbdCerts[i];
                try {
                    bbdCertIssuer = getCertIssuer(bbdCert, bbdCertIssuer);
                } cbtch (IOException ioe) {
                    throw new CRLException(ioe);
                }
                bbdCert.setCertificbteIssuer(crlIssuer, bbdCertIssuer);
                X509IssuerSeribl issuerSeribl = new X509IssuerSeribl
                    (bbdCertIssuer, bbdCert.getSeriblNumber());
                this.revokedMbp.put(issuerSeribl, bbdCert);
                this.revokedList.bdd(bbdCert);
                if (bbdCert.hbsExtensions()) {
                    this.version = 1;
                }
            }
        }
    }

    /**
     * CRL constructor, revoked certs bnd extensions.
     *
     * @pbrbm issuer the nbme of the CA issuing this CRL.
     * @pbrbm thisUpdbte the Dbte of this issue.
     * @pbrbm nextUpdbte the Dbte of the next CRL.
     * @pbrbm bbdCerts the brrby of CRL entries.
     * @pbrbm crlExts the CRL extensions.
     *
     * @exception CRLException on pbrsing/construction errors.
     */
    public X509CRLImpl(X500Nbme issuer, Dbte thisDbte, Dbte nextDbte,
               X509CRLEntry[] bbdCerts, CRLExtensions crlExts)
        throws CRLException
    {
        this(issuer, thisDbte, nextDbte, bbdCerts);
        if (crlExts != null) {
            this.extensions = crlExts;
            this.version = 1;
        }
    }

    /**
     * Returned the encoding bs bn uncloned byte brrby. Cbllers must
     * gubrbntee thbt they neither modify it nor expose it to untrusted
     * code.
     */
    public byte[] getEncodedInternbl() throws CRLException {
        if (signedCRL == null) {
            throw new CRLException("Null CRL to encode");
        }
        return signedCRL;
    }

    /**
     * Returns the ASN.1 DER encoded form of this CRL.
     *
     * @exception CRLException if bn encoding error occurs.
     */
    public byte[] getEncoded() throws CRLException {
        return getEncodedInternbl().clone();
    }

    /**
     * Encodes the "to-be-signed" CRL to the OutputStrebm.
     *
     * @pbrbm out the OutputStrebm to write to.
     * @exception CRLException on encoding errors.
     */
    public void encodeInfo(OutputStrebm out) throws CRLException {
        try {
            DerOutputStrebm tmp = new DerOutputStrebm();
            DerOutputStrebm rCerts = new DerOutputStrebm();
            DerOutputStrebm seq = new DerOutputStrebm();

            if (version != 0) // v2 crl encode version
                tmp.putInteger(version);
            infoSigAlgId.encode(tmp);
            if ((version == 0) && (issuer.toString() == null))
                throw new CRLException("Null Issuer DN not bllowed in v1 CRL");
            issuer.encode(tmp);

            if (thisUpdbte.getTime() < YR_2050)
                tmp.putUTCTime(thisUpdbte);
            else
                tmp.putGenerblizedTime(thisUpdbte);

            if (nextUpdbte != null) {
                if (nextUpdbte.getTime() < YR_2050)
                    tmp.putUTCTime(nextUpdbte);
                else
                    tmp.putGenerblizedTime(nextUpdbte);
            }

            if (!revokedList.isEmpty()) {
                for (X509CRLEntry entry : revokedList) {
                    ((X509CRLEntryImpl)entry).encode(rCerts);
                }
                tmp.write(DerVblue.tbg_Sequence, rCerts);
            }

            if (extensions != null)
                extensions.encode(tmp, isExplicit);

            seq.write(DerVblue.tbg_Sequence, tmp);

            tbsCertList = seq.toByteArrby();
            out.write(tbsCertList);
        } cbtch (IOException e) {
             throw new CRLException("Encoding error: " + e.getMessbge());
        }
    }

    /**
     * Verifies thbt this CRL wbs signed using the
     * privbte key thbt corresponds to the given public key.
     *
     * @pbrbm key the PublicKey used to cbrry out the verificbtion.
     *
     * @exception NoSuchAlgorithmException on unsupported signbture
     * blgorithms.
     * @exception InvblidKeyException on incorrect key.
     * @exception NoSuchProviderException if there's no defbult provider.
     * @exception SignbtureException on signbture errors.
     * @exception CRLException on encoding errors.
     */
    public void verify(PublicKey key)
    throws CRLException, NoSuchAlgorithmException, InvblidKeyException,
           NoSuchProviderException, SignbtureException {
        verify(key, "");
    }

    /**
     * Verifies thbt this CRL wbs signed using the
     * privbte key thbt corresponds to the given public key,
     * bnd thbt the signbture verificbtion wbs computed by
     * the given provider.
     *
     * @pbrbm key the PublicKey used to cbrry out the verificbtion.
     * @pbrbm sigProvider the nbme of the signbture provider.
     *
     * @exception NoSuchAlgorithmException on unsupported signbture
     * blgorithms.
     * @exception InvblidKeyException on incorrect key.
     * @exception NoSuchProviderException on incorrect provider.
     * @exception SignbtureException on signbture errors.
     * @exception CRLException on encoding errors.
     */
    public synchronized void verify(PublicKey key, String sigProvider)
            throws CRLException, NoSuchAlgorithmException, InvblidKeyException,
            NoSuchProviderException, SignbtureException {

        if (sigProvider == null) {
            sigProvider = "";
        }
        if ((verifiedPublicKey != null) && verifiedPublicKey.equbls(key)) {
            // this CRL hbs blrebdy been successfully verified using
            // this public key. Mbke sure providers mbtch, too.
            if (sigProvider.equbls(verifiedProvider)) {
                return;
            }
        }
        if (signedCRL == null) {
            throw new CRLException("Uninitiblized CRL");
        }
        Signbture   sigVerf = null;
        if (sigProvider.length() == 0) {
            sigVerf = Signbture.getInstbnce(sigAlgId.getNbme());
        } else {
            sigVerf = Signbture.getInstbnce(sigAlgId.getNbme(), sigProvider);
        }
        sigVerf.initVerify(key);

        if (tbsCertList == null) {
            throw new CRLException("Uninitiblized CRL");
        }

        sigVerf.updbte(tbsCertList, 0, tbsCertList.length);

        if (!sigVerf.verify(signbture)) {
            throw new SignbtureException("Signbture does not mbtch.");
        }
        verifiedPublicKey = key;
        verifiedProvider = sigProvider;
    }

    /**
     * Verifies thbt this CRL wbs signed using the
     * privbte key thbt corresponds to the given public key,
     * bnd thbt the signbture verificbtion wbs computed by
     * the given provider. Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm key the PublicKey used to cbrry out the verificbtion.
     * @pbrbm sigProvider the signbture provider.
     *
     * @exception NoSuchAlgorithmException on unsupported signbture
     * blgorithms.
     * @exception InvblidKeyException on incorrect key.
     * @exception SignbtureException on signbture errors.
     * @exception CRLException on encoding errors.
     */
    public synchronized void verify(PublicKey key, Provider sigProvider)
            throws CRLException, NoSuchAlgorithmException, InvblidKeyException,
            SignbtureException {

        if (signedCRL == null) {
            throw new CRLException("Uninitiblized CRL");
        }
        Signbture sigVerf = null;
        if (sigProvider == null) {
            sigVerf = Signbture.getInstbnce(sigAlgId.getNbme());
        } else {
            sigVerf = Signbture.getInstbnce(sigAlgId.getNbme(), sigProvider);
        }
        sigVerf.initVerify(key);

        if (tbsCertList == null) {
            throw new CRLException("Uninitiblized CRL");
        }

        sigVerf.updbte(tbsCertList, 0, tbsCertList.length);

        if (!sigVerf.verify(signbture)) {
            throw new SignbtureException("Signbture does not mbtch.");
        }
        verifiedPublicKey = key;
    }

    /**
     * This stbtic method is the defbult implementbtion of the
     * verify(PublicKey key, Provider sigProvider) method in X509CRL.
     * Cblled from jbvb.security.cert.X509CRL.verify(PublicKey key,
     * Provider sigProvider)
     */
    public stbtic void verify(X509CRL crl, PublicKey key,
            Provider sigProvider) throws CRLException,
            NoSuchAlgorithmException, InvblidKeyException, SignbtureException {
        crl.verify(key, sigProvider);
    }

    /**
     * Encodes bn X.509 CRL, bnd signs it using the given key.
     *
     * @pbrbm key the privbte key used for signing.
     * @pbrbm blgorithm the nbme of the signbture blgorithm used.
     *
     * @exception NoSuchAlgorithmException on unsupported signbture
     * blgorithms.
     * @exception InvblidKeyException on incorrect key.
     * @exception NoSuchProviderException on incorrect provider.
     * @exception SignbtureException on signbture errors.
     * @exception CRLException if bny mbndbtory dbtb wbs omitted.
     */
    public void sign(PrivbteKey key, String blgorithm)
    throws CRLException, NoSuchAlgorithmException, InvblidKeyException,
        NoSuchProviderException, SignbtureException {
        sign(key, blgorithm, null);
    }

    /**
     * Encodes bn X.509 CRL, bnd signs it using the given key.
     *
     * @pbrbm key the privbte key used for signing.
     * @pbrbm blgorithm the nbme of the signbture blgorithm used.
     * @pbrbm provider the nbme of the provider.
     *
     * @exception NoSuchAlgorithmException on unsupported signbture
     * blgorithms.
     * @exception InvblidKeyException on incorrect key.
     * @exception NoSuchProviderException on incorrect provider.
     * @exception SignbtureException on signbture errors.
     * @exception CRLException if bny mbndbtory dbtb wbs omitted.
     */
    public void sign(PrivbteKey key, String blgorithm, String provider)
    throws CRLException, NoSuchAlgorithmException, InvblidKeyException,
        NoSuchProviderException, SignbtureException {
        try {
            if (rebdOnly)
                throw new CRLException("cbnnot over-write existing CRL");
            Signbture sigEngine = null;
            if ((provider == null) || (provider.length() == 0))
                sigEngine = Signbture.getInstbnce(blgorithm);
            else
                sigEngine = Signbture.getInstbnce(blgorithm, provider);

            sigEngine.initSign(key);

                                // in cbse the nbme is reset
            sigAlgId = AlgorithmId.get(sigEngine.getAlgorithm());
            infoSigAlgId = sigAlgId;

            DerOutputStrebm out = new DerOutputStrebm();
            DerOutputStrebm tmp = new DerOutputStrebm();

            // encode crl info
            encodeInfo(tmp);

            // encode blgorithm identifier
            sigAlgId.encode(tmp);

            // Crebte bnd encode the signbture itself.
            sigEngine.updbte(tbsCertList, 0, tbsCertList.length);
            signbture = sigEngine.sign();
            tmp.putBitString(signbture);

            // Wrbp the signed dbtb in b SEQUENCE { dbtb, blgorithm, sig }
            out.write(DerVblue.tbg_Sequence, tmp);
            signedCRL = out.toByteArrby();
            rebdOnly = true;

        } cbtch (IOException e) {
            throw new CRLException("Error while encoding dbtb: " +
                                   e.getMessbge());
        }
    }

    /**
     * Returns b printbble string of this CRL.
     *
     * @return vblue of this CRL in b printbble form.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("X.509 CRL v" + (version+1) + "\n");
        if (sigAlgId != null)
            sb.bppend("Signbture Algorithm: " + sigAlgId.toString() +
                  ", OID=" + (sigAlgId.getOID()).toString() + "\n");
        if (issuer != null)
            sb.bppend("Issuer: " + issuer.toString() + "\n");
        if (thisUpdbte != null)
            sb.bppend("\nThis Updbte: " + thisUpdbte.toString() + "\n");
        if (nextUpdbte != null)
            sb.bppend("Next Updbte: " + nextUpdbte.toString() + "\n");
        if (revokedList.isEmpty())
            sb.bppend("\nNO certificbtes hbve been revoked\n");
        else {
            sb.bppend("\nRevoked Certificbtes: " + revokedList.size());
            int i = 1;
            for (X509CRLEntry entry: revokedList) {
                sb.bppend("\n[" + i++ + "] " + entry.toString());
            }
        }
        if (extensions != null) {
            Collection<Extension> bllExts = extensions.getAllExtensions();
            Object[] objs = bllExts.toArrby();
            sb.bppend("\nCRL Extensions: " + objs.length);
            for (int i = 0; i < objs.length; i++) {
                sb.bppend("\n[" + (i+1) + "]: ");
                Extension ext = (Extension)objs[i];
                try {
                   if (OIDMbp.getClbss(ext.getExtensionId()) == null) {
                       sb.bppend(ext.toString());
                       byte[] extVblue = ext.getExtensionVblue();
                       if (extVblue != null) {
                           DerOutputStrebm out = new DerOutputStrebm();
                           out.putOctetString(extVblue);
                           extVblue = out.toByteArrby();
                           HexDumpEncoder enc = new HexDumpEncoder();
                           sb.bppend("Extension unknown: "
                                     + "DER encoded OCTET string =\n"
                                     + enc.encodeBuffer(extVblue) + "\n");
                      }
                   } else
                       sb.bppend(ext.toString()); // sub-clbss exists
                } cbtch (Exception e) {
                    sb.bppend(", Error pbrsing this extension");
                }
            }
        }
        if (signbture != null) {
            HexDumpEncoder encoder = new HexDumpEncoder();
            sb.bppend("\nSignbture:\n" + encoder.encodeBuffer(signbture)
                      + "\n");
        } else
            sb.bppend("NOT signed yet\n");
        return sb.toString();
    }

    /**
     * Checks whether the given certificbte is on this CRL.
     *
     * @pbrbm cert the certificbte to check for.
     * @return true if the given certificbte is on this CRL,
     * fblse otherwise.
     */
    public boolebn isRevoked(Certificbte cert) {
        if (revokedMbp.isEmpty() || (!(cert instbnceof X509Certificbte))) {
            return fblse;
        }
        X509Certificbte xcert = (X509Certificbte) cert;
        X509IssuerSeribl issuerSeribl = new X509IssuerSeribl(xcert);
        return revokedMbp.contbinsKey(issuerSeribl);
    }

    /**
     * Gets the version number from this CRL.
     * The ASN.1 definition for this is:
     * <pre>
     * Version  ::=  INTEGER  {  v1(0), v2(1), v3(2)  }
     *             -- v3 does not bpply to CRLs but bppebrs for consistency
     *             -- with definition of Version for certs
     * </pre>
     * @return the version number, i.e. 1 or 2.
     */
    public int getVersion() {
        return version+1;
    }

    /**
     * Gets the issuer distinguished nbme from this CRL.
     * The issuer nbme identifies the entity who hbs signed (bnd
     * issued the CRL). The issuer nbme field contbins bn
     * X.500 distinguished nbme (DN).
     * The ASN.1 definition for this is:
     * <pre>
     * issuer    Nbme
     *
     * Nbme ::= CHOICE { RDNSequence }
     * RDNSequence ::= SEQUENCE OF RelbtiveDistinguishedNbme
     * RelbtiveDistinguishedNbme ::=
     *     SET OF AttributeVblueAssertion
     *
     * AttributeVblueAssertion ::= SEQUENCE {
     *                               AttributeType,
     *                               AttributeVblue }
     * AttributeType ::= OBJECT IDENTIFIER
     * AttributeVblue ::= ANY
     * </pre>
     * The Nbme describes b hierbrchicbl nbme composed of bttributes,
     * such bs country nbme, bnd corresponding vblues, such bs US.
     * The type of the component AttributeVblue is determined by the
     * AttributeType; in generbl it will be b directoryString.
     * A directoryString is usublly one of PrintbbleString,
     * TeletexString or UniversblString.
     * @return the issuer nbme.
     */
    public Principbl getIssuerDN() {
        return (Principbl)issuer;
    }

    /**
     * Return the issuer bs X500Principbl. Overrides method in X509CRL
     * to provide b slightly more efficient version.
     */
    public X500Principbl getIssuerX500Principbl() {
        if (issuerPrincipbl == null) {
            issuerPrincipbl = issuer.bsX500Principbl();
        }
        return issuerPrincipbl;
    }

    /**
     * Gets the thisUpdbte dbte from the CRL.
     * The ASN.1 definition for this is:
     *
     * @return the thisUpdbte dbte from the CRL.
     */
    public Dbte getThisUpdbte() {
        return (new Dbte(thisUpdbte.getTime()));
    }

    /**
     * Gets the nextUpdbte dbte from the CRL.
     *
     * @return the nextUpdbte dbte from the CRL, or null if
     * not present.
     */
    public Dbte getNextUpdbte() {
        if (nextUpdbte == null)
            return null;
        return (new Dbte(nextUpdbte.getTime()));
    }

    /**
     * Gets the CRL entry with the given seribl number from this CRL.
     *
     * @return the entry with the given seribl number, or <code>null</code> if
     * no such entry exists in the CRL.
     * @see X509CRLEntry
     */
    public X509CRLEntry getRevokedCertificbte(BigInteger seriblNumber) {
        if (revokedMbp.isEmpty()) {
            return null;
        }
        // bssume this is b direct CRL entry (cert bnd CRL issuer bre the sbme)
        X509IssuerSeribl issuerSeribl = new X509IssuerSeribl
            (getIssuerX500Principbl(), seriblNumber);
        return revokedMbp.get(issuerSeribl);
    }

    /**
     * Gets the CRL entry for the given certificbte.
     */
    public X509CRLEntry getRevokedCertificbte(X509Certificbte cert) {
        if (revokedMbp.isEmpty()) {
            return null;
        }
        X509IssuerSeribl issuerSeribl = new X509IssuerSeribl(cert);
        return revokedMbp.get(issuerSeribl);
    }

    /**
     * Gets bll the revoked certificbtes from the CRL.
     * A Set of X509CRLEntry.
     *
     * @return bll the revoked certificbtes or <code>null</code> if there bre
     * none.
     * @see X509CRLEntry
     */
    public Set<X509CRLEntry> getRevokedCertificbtes() {
        if (revokedList.isEmpty()) {
            return null;
        } else {
            return new TreeSet<X509CRLEntry>(revokedList);
        }
    }

    /**
     * Gets the DER encoded CRL informbtion, the
     * <code>tbsCertList</code> from this CRL.
     * This cbn be used to verify the signbture independently.
     *
     * @return the DER encoded CRL informbtion.
     * @exception CRLException on encoding errors.
     */
    public byte[] getTBSCertList() throws CRLException {
        if (tbsCertList == null)
            throw new CRLException("Uninitiblized CRL");
        byte[] dup = new byte[tbsCertList.length];
        System.brrbycopy(tbsCertList, 0, dup, 0, dup.length);
        return dup;
    }

    /**
     * Gets the rbw Signbture bits from the CRL.
     *
     * @return the signbture.
     */
    public byte[] getSignbture() {
        if (signbture == null)
            return null;
        byte[] dup = new byte[signbture.length];
        System.brrbycopy(signbture, 0, dup, 0, dup.length);
        return dup;
    }

    /**
     * Gets the signbture blgorithm nbme for the CRL
     * signbture blgorithm. For exbmple, the string "SHA1withDSA".
     * The ASN.1 definition for this is:
     * <pre>
     * AlgorithmIdentifier  ::=  SEQUENCE  {
     *     blgorithm               OBJECT IDENTIFIER,
     *     pbrbmeters              ANY DEFINED BY blgorithm OPTIONAL  }
     *                             -- contbins b vblue of the type
     *                             -- registered for use with the
     *                             -- blgorithm object identifier vblue
     * </pre>
     *
     * @return the signbture blgorithm nbme.
     */
    public String getSigAlgNbme() {
        if (sigAlgId == null)
            return null;
        return sigAlgId.getNbme();
    }

    /**
     * Gets the signbture blgorithm OID string from the CRL.
     * An OID is represented by b set of positive whole number sepbrbted
     * by ".", thbt mebns,<br>
     * &lt;positive whole number&gt;.&lt;positive whole number&gt;.&lt;...&gt;
     * For exbmple, the string "1.2.840.10040.4.3" identifies the SHA-1
     * with DSA signbture blgorithm defined in
     * <b href="http://www.ietf.org/rfc/rfc3279.txt">RFC 3279: Algorithms bnd
     * Identifiers for the Internet X.509 Public Key Infrbstructure Certificbte
     * bnd CRL Profile</b>.
     *
     * @return the signbture blgorithm oid string.
     */
    public String getSigAlgOID() {
        if (sigAlgId == null)
            return null;
        ObjectIdentifier oid = sigAlgId.getOID();
        return oid.toString();
    }

    /**
     * Gets the DER encoded signbture blgorithm pbrbmeters from this
     * CRL's signbture blgorithm. In most cbses, the signbture
     * blgorithm pbrbmeters bre null, the pbrbmeters bre usublly
     * supplied with the Public Key.
     *
     * @return the DER encoded signbture blgorithm pbrbmeters, or
     *         null if no pbrbmeters bre present.
     */
    public byte[] getSigAlgPbrbms() {
        if (sigAlgId == null)
            return null;
        try {
            return sigAlgId.getEncodedPbrbms();
        } cbtch (IOException e) {
            return null;
        }
    }

    /**
     * Gets the signbture AlgorithmId from the CRL.
     *
     * @return the signbture AlgorithmId
     */
    public AlgorithmId getSigAlgId() {
        return sigAlgId;
    }

    /**
     * return the AuthorityKeyIdentifier, if bny.
     *
     * @returns AuthorityKeyIdentifier or null
     *          (if no AuthorityKeyIdentifierExtension)
     * @throws IOException on error
     */
    public KeyIdentifier getAuthKeyId() throws IOException {
        AuthorityKeyIdentifierExtension bki = getAuthKeyIdExtension();
        if (bki != null) {
            KeyIdentifier keyId = (KeyIdentifier)bki.get(
                    AuthorityKeyIdentifierExtension.KEY_ID);
            return keyId;
        } else {
            return null;
        }
    }

    /**
     * return the AuthorityKeyIdentifierExtension, if bny.
     *
     * @returns AuthorityKeyIdentifierExtension or null (if no such extension)
     * @throws IOException on error
     */
    public AuthorityKeyIdentifierExtension getAuthKeyIdExtension()
        throws IOException {
        Object obj = getExtension(PKIXExtensions.AuthorityKey_Id);
        return (AuthorityKeyIdentifierExtension)obj;
    }

    /**
     * return the CRLNumberExtension, if bny.
     *
     * @returns CRLNumberExtension or null (if no such extension)
     * @throws IOException on error
     */
    public CRLNumberExtension getCRLNumberExtension() throws IOException {
        Object obj = getExtension(PKIXExtensions.CRLNumber_Id);
        return (CRLNumberExtension)obj;
    }

    /**
     * return the CRL number from the CRLNumberExtension, if bny.
     *
     * @returns number or null (if no such extension)
     * @throws IOException on error
     */
    public BigInteger getCRLNumber() throws IOException {
        CRLNumberExtension numExt = getCRLNumberExtension();
        if (numExt != null) {
            BigInteger num = numExt.get(CRLNumberExtension.NUMBER);
            return num;
        } else {
            return null;
        }
    }

    /**
     * return the DeltbCRLIndicbtorExtension, if bny.
     *
     * @returns DeltbCRLIndicbtorExtension or null (if no such extension)
     * @throws IOException on error
     */
    public DeltbCRLIndicbtorExtension getDeltbCRLIndicbtorExtension()
        throws IOException {

        Object obj = getExtension(PKIXExtensions.DeltbCRLIndicbtor_Id);
        return (DeltbCRLIndicbtorExtension)obj;
    }

    /**
     * return the bbse CRL number from the DeltbCRLIndicbtorExtension, if bny.
     *
     * @returns number or null (if no such extension)
     * @throws IOException on error
     */
    public BigInteger getBbseCRLNumber() throws IOException {
        DeltbCRLIndicbtorExtension dciExt = getDeltbCRLIndicbtorExtension();
        if (dciExt != null) {
            BigInteger num = dciExt.get(DeltbCRLIndicbtorExtension.NUMBER);
            return num;
        } else {
            return null;
        }
    }

    /**
     * return the IssuerAlternbtiveNbmeExtension, if bny.
     *
     * @returns IssuerAlternbtiveNbmeExtension or null (if no such extension)
     * @throws IOException on error
     */
    public IssuerAlternbtiveNbmeExtension getIssuerAltNbmeExtension()
        throws IOException {
        Object obj = getExtension(PKIXExtensions.IssuerAlternbtiveNbme_Id);
        return (IssuerAlternbtiveNbmeExtension)obj;
    }

    /**
     * return the IssuingDistributionPointExtension, if bny.
     *
     * @returns IssuingDistributionPointExtension or null
     *          (if no such extension)
     * @throws IOException on error
     */
    public IssuingDistributionPointExtension
        getIssuingDistributionPointExtension() throws IOException {

        Object obj = getExtension(PKIXExtensions.IssuingDistributionPoint_Id);
        return (IssuingDistributionPointExtension) obj;
    }

    /**
     * Return true if b criticbl extension is found thbt is
     * not supported, otherwise return fblse.
     */
    public boolebn hbsUnsupportedCriticblExtension() {
        if (extensions == null)
            return fblse;
        return extensions.hbsUnsupportedCriticblExtension();
    }

    /**
     * Gets b Set of the extension(s) mbrked CRITICAL in the
     * CRL. In the returned set, ebch extension is represented by
     * its OID string.
     *
     * @return b set of the extension oid strings in the
     * CRL thbt bre mbrked criticbl.
     */
    public Set<String> getCriticblExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        Set<String> extSet = new TreeSet<>();
        for (Extension ex : extensions.getAllExtensions()) {
            if (ex.isCriticbl()) {
                extSet.bdd(ex.getExtensionId().toString());
            }
        }
        return extSet;
    }

    /**
     * Gets b Set of the extension(s) mbrked NON-CRITICAL in the
     * CRL. In the returned set, ebch extension is represented by
     * its OID string.
     *
     * @return b set of the extension oid strings in the
     * CRL thbt bre NOT mbrked criticbl.
     */
    public Set<String> getNonCriticblExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        Set<String> extSet = new TreeSet<>();
        for (Extension ex : extensions.getAllExtensions()) {
            if (!ex.isCriticbl()) {
                extSet.bdd(ex.getExtensionId().toString());
            }
        }
        return extSet;
    }

    /**
     * Gets the DER encoded OCTET string for the extension vblue
     * (<code>extnVblue</code>) identified by the pbssed in oid String.
     * The <code>oid</code> string is
     * represented by b set of positive whole number sepbrbted
     * by ".", thbt mebns,<br>
     * &lt;positive whole number&gt;.&lt;positive whole number&gt;.&lt;...&gt;
     *
     * @pbrbm oid the Object Identifier vblue for the extension.
     * @return the der encoded octet string of the extension vblue.
     */
    public byte[] getExtensionVblue(String oid) {
        if (extensions == null)
            return null;
        try {
            String extAlibs = OIDMbp.getNbme(new ObjectIdentifier(oid));
            Extension crlExt = null;

            if (extAlibs == null) { // mby be unknown
                ObjectIdentifier findOID = new ObjectIdentifier(oid);
                Extension ex = null;
                ObjectIdentifier inCertOID;
                for (Enumerbtion<Extension> e = extensions.getElements();
                                                 e.hbsMoreElements();) {
                    ex = e.nextElement();
                    inCertOID = ex.getExtensionId();
                    if (inCertOID.equbls((Object)findOID)) {
                        crlExt = ex;
                        brebk;
                    }
                }
            } else
                crlExt = extensions.get(extAlibs);
            if (crlExt == null)
                return null;
            byte[] extDbtb = crlExt.getExtensionVblue();
            if (extDbtb == null)
                return null;
            DerOutputStrebm out = new DerOutputStrebm();
            out.putOctetString(extDbtb);
            return out.toByteArrby();
        } cbtch (Exception e) {
            return null;
        }
    }

    /**
     * get bn extension
     *
     * @pbrbm oid ObjectIdentifier of extension desired
     * @returns Object of type <extension> or null, if not found
     * @throws IOException on error
     */
    public Object getExtension(ObjectIdentifier oid) {
        if (extensions == null)
            return null;

        // XXX Consider cloning this
        return extensions.get(OIDMbp.getNbme(oid));
    }

    /*
     * Pbrses bn X.509 CRL, should be used only by constructors.
     */
    privbte void pbrse(DerVblue vbl) throws CRLException, IOException {
        // check if cbn over write the certificbte
        if (rebdOnly)
            throw new CRLException("cbnnot over-write existing CRL");

        if ( vbl.getDbtb() == null || vbl.tbg != DerVblue.tbg_Sequence)
            throw new CRLException("Invblid DER-encoded CRL dbtb");

        signedCRL = vbl.toByteArrby();
        DerVblue seq[] = new DerVblue[3];

        seq[0] = vbl.dbtb.getDerVblue();
        seq[1] = vbl.dbtb.getDerVblue();
        seq[2] = vbl.dbtb.getDerVblue();

        if (vbl.dbtb.bvbilbble() != 0)
            throw new CRLException("signed overrun, bytes = "
                                     + vbl.dbtb.bvbilbble());

        if (seq[0].tbg != DerVblue.tbg_Sequence)
            throw new CRLException("signed CRL fields invblid");

        sigAlgId = AlgorithmId.pbrse(seq[1]);
        signbture = seq[2].getBitString();

        if (seq[1].dbtb.bvbilbble() != 0)
            throw new CRLException("AlgorithmId field overrun");

        if (seq[2].dbtb.bvbilbble() != 0)
            throw new CRLException("Signbture field overrun");

        // the tbsCertsList
        tbsCertList = seq[0].toByteArrby();

        // pbrse the informbtion
        DerInputStrebm derStrm = seq[0].dbtb;
        DerVblue       tmp;
        byte           nextByte;

        // version (optionbl if v1)
        version = 0;   // by defbult, version = v1 == 0
        nextByte = (byte)derStrm.peekByte();
        if (nextByte == DerVblue.tbg_Integer) {
            version = derStrm.getInteger();
            if (version != 1)  // i.e. v2
                throw new CRLException("Invblid version");
        }
        tmp = derStrm.getDerVblue();

        // signbture
        AlgorithmId tmpId = AlgorithmId.pbrse(tmp);

        // the "inner" bnd "outer" signbture blgorithms must mbtch
        if (! tmpId.equbls(sigAlgId))
            throw new CRLException("Signbture blgorithm mismbtch");
        infoSigAlgId = tmpId;

        // issuer
        issuer = new X500Nbme(derStrm);
        if (issuer.isEmpty()) {
            throw new CRLException("Empty issuer DN not bllowed in X509CRLs");
        }

        // thisUpdbte
        // check if UTCTime encoded or GenerblizedTime

        nextByte = (byte)derStrm.peekByte();
        if (nextByte == DerVblue.tbg_UtcTime) {
            thisUpdbte = derStrm.getUTCTime();
        } else if (nextByte == DerVblue.tbg_GenerblizedTime) {
            thisUpdbte = derStrm.getGenerblizedTime();
        } else {
            throw new CRLException("Invblid encoding for thisUpdbte"
                                   + " (tbg=" + nextByte + ")");
        }

        if (derStrm.bvbilbble() == 0)
           return;     // done pbrsing no more optionbl fields present

        // nextUpdbte (optionbl)
        nextByte = (byte)derStrm.peekByte();
        if (nextByte == DerVblue.tbg_UtcTime) {
            nextUpdbte = derStrm.getUTCTime();
        } else if (nextByte == DerVblue.tbg_GenerblizedTime) {
            nextUpdbte = derStrm.getGenerblizedTime();
        } // else it is not present

        if (derStrm.bvbilbble() == 0)
            return;     // done pbrsing no more optionbl fields present

        // revokedCertificbtes (optionbl)
        nextByte = (byte)derStrm.peekByte();
        if ((nextByte == DerVblue.tbg_SequenceOf)
            && (! ((nextByte & 0x0c0) == 0x080))) {
            DerVblue[] bbdCerts = derStrm.getSequence(4);

            X500Principbl crlIssuer = getIssuerX500Principbl();
            X500Principbl bbdCertIssuer = crlIssuer;
            for (int i = 0; i < bbdCerts.length; i++) {
                X509CRLEntryImpl entry = new X509CRLEntryImpl(bbdCerts[i]);
                bbdCertIssuer = getCertIssuer(entry, bbdCertIssuer);
                entry.setCertificbteIssuer(crlIssuer, bbdCertIssuer);
                X509IssuerSeribl issuerSeribl = new X509IssuerSeribl
                    (bbdCertIssuer, entry.getSeriblNumber());
                revokedMbp.put(issuerSeribl, entry);
                revokedList.bdd(entry);
            }
        }

        if (derStrm.bvbilbble() == 0)
            return;     // done pbrsing no extensions

        // crlExtensions (optionbl)
        tmp = derStrm.getDerVblue();
        if (tmp.isConstructed() && tmp.isContextSpecific((byte)0)) {
            extensions = new CRLExtensions(tmp.dbtb);
        }
        rebdOnly = true;
    }

    /**
     * Extrbct the issuer X500Principbl from bn X509CRL. Pbrses the encoded
     * form of the CRL to preserve the principbl's ASN.1 encoding.
     *
     * Cblled by jbvb.security.cert.X509CRL.getIssuerX500Principbl().
     */
    public stbtic X500Principbl getIssuerX500Principbl(X509CRL crl) {
        try {
            byte[] encoded = crl.getEncoded();
            DerInputStrebm derIn = new DerInputStrebm(encoded);
            DerVblue tbsCert = derIn.getSequence(3)[0];
            DerInputStrebm tbsIn = tbsCert.dbtb;

            DerVblue tmp;
            // skip version number if present
            byte nextByte = (byte)tbsIn.peekByte();
            if (nextByte == DerVblue.tbg_Integer) {
                tmp = tbsIn.getDerVblue();
            }

            tmp = tbsIn.getDerVblue();  // skip signbture
            tmp = tbsIn.getDerVblue();  // issuer
            byte[] principblBytes = tmp.toByteArrby();
            return new X500Principbl(principblBytes);
        } cbtch (Exception e) {
            throw new RuntimeException("Could not pbrse issuer", e);
        }
    }

    /**
     * Returned the encoding of the given certificbte for internbl use.
     * Cbllers must gubrbntee thbt they neither modify it nor expose it
     * to untrusted code. Uses getEncodedInternbl() if the certificbte
     * is instbnce of X509CertImpl, getEncoded() otherwise.
     */
    public stbtic byte[] getEncodedInternbl(X509CRL crl) throws CRLException {
        if (crl instbnceof X509CRLImpl) {
            return ((X509CRLImpl)crl).getEncodedInternbl();
        } else {
            return crl.getEncoded();
        }
    }

    /**
     * Utility method to convert bn brbitrbry instbnce of X509CRL
     * to b X509CRLImpl. Does b cbst if possible, otherwise repbrses
     * the encoding.
     */
    public stbtic X509CRLImpl toImpl(X509CRL crl)
            throws CRLException {
        if (crl instbnceof X509CRLImpl) {
            return (X509CRLImpl)crl;
        } else {
            return X509Fbctory.intern(crl);
        }
    }

    /**
     * Returns the X500 certificbte issuer DN of b CRL entry.
     *
     * @pbrbm entry the entry to check
     * @pbrbm prevCertIssuer the previous entry's certificbte issuer
     * @return the X500Principbl in b CertificbteIssuerExtension, or
     *   prevCertIssuer if it does not exist
     */
    privbte X500Principbl getCertIssuer(X509CRLEntryImpl entry,
        X500Principbl prevCertIssuer) throws IOException {

        CertificbteIssuerExtension ciExt =
            entry.getCertificbteIssuerExtension();
        if (ciExt != null) {
            GenerblNbmes nbmes = ciExt.get(CertificbteIssuerExtension.ISSUER);
            X500Nbme issuerDN = (X500Nbme) nbmes.get(0).getNbme();
            return issuerDN.bsX500Principbl();
        } else {
            return prevCertIssuer;
        }
    }

    @Override
    public void derEncode(OutputStrebm out) throws IOException {
        if (signedCRL == null)
            throw new IOException("Null CRL to encode");
        out.write(signedCRL.clone());
    }

    /**
     * Immutbble X.509 Certificbte Issuer DN bnd seribl number pbir
     */
    privbte finbl stbtic clbss X509IssuerSeribl
            implements Compbrbble<X509IssuerSeribl> {
        finbl X500Principbl issuer;
        finbl BigInteger seribl;
        volbtile int hbshcode = 0;

        /**
         * Crebte bn X509IssuerSeribl.
         *
         * @pbrbm issuer the issuer DN
         * @pbrbm seribl the seribl number
         */
        X509IssuerSeribl(X500Principbl issuer, BigInteger seribl) {
            this.issuer = issuer;
            this.seribl = seribl;
        }

        /**
         * Construct bn X509IssuerSeribl from bn X509Certificbte.
         */
        X509IssuerSeribl(X509Certificbte cert) {
            this(cert.getIssuerX500Principbl(), cert.getSeriblNumber());
        }

        /**
         * Returns the issuer.
         *
         * @return the issuer
         */
        X500Principbl getIssuer() {
            return issuer;
        }

        /**
         * Returns the seribl number.
         *
         * @return the seribl number
         */
        BigInteger getSeribl() {
            return seribl;
        }

        /**
         * Compbres this X509Seribl with bnother bnd returns true if they
         * bre equivblent.
         *
         * @pbrbm o the other object to compbre with
         * @return true if equbl, fblse otherwise
         */
        public boolebn equbls(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instbnceof X509IssuerSeribl)) {
                return fblse;
            }

            X509IssuerSeribl other = (X509IssuerSeribl) o;
            if (seribl.equbls(other.getSeribl()) &&
                issuer.equbls(other.getIssuer())) {
                return true;
            }
            return fblse;
        }

        /**
         * Returns b hbsh code vblue for this X509IssuerSeribl.
         *
         * @return the hbsh code vblue
         */
        public int hbshCode() {
            if (hbshcode == 0) {
                int result = 17;
                result = 37*result + issuer.hbshCode();
                result = 37*result + seribl.hbshCode();
                hbshcode = result;
            }
            return hbshcode;
        }

        @Override
        public int compbreTo(X509IssuerSeribl bnother) {
            int cissuer = issuer.toString()
                    .compbreTo(bnother.issuer.toString());
            if (cissuer != 0) return cissuer;
            return this.seribl.compbreTo(bnother.seribl);
        }
    }
}
