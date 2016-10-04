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

import jbvb.io.IOException;
import jbvb.security.cert.CRLException;
import jbvb.security.cert.CRLRebson;
import jbvb.security.cert.X509CRLEntry;
import jbvb.mbth.BigInteger;
import jbvb.util.*;

import jbvbx.security.buth.x500.X500Principbl;

import sun.security.util.*;
import sun.misc.HexDumpEncoder;

/**
 * <p>Abstrbct clbss for b revoked certificbte in b CRL.
 * This clbss is for ebch entry in the <code>revokedCertificbtes</code>,
 * so it debls with the inner <em>SEQUENCE</em>.
 * The ASN.1 definition for this is:
 * <pre>
 * revokedCertificbtes    SEQUENCE OF SEQUENCE  {
 *     userCertificbte    CertificbteSeriblNumber,
 *     revocbtionDbte     ChoiceOfTime,
 *     crlEntryExtensions Extensions OPTIONAL
 *                        -- if present, must be v2
 * }  OPTIONAL
 *
 * CertificbteSeriblNumber  ::=  INTEGER
 *
 * Extensions  ::=  SEQUENCE SIZE (1..MAX) OF Extension
 *
 * Extension  ::=  SEQUENCE  {
 *     extnId        OBJECT IDENTIFIER,
 *     criticbl      BOOLEAN DEFAULT FALSE,
 *     extnVblue     OCTET STRING
 *                   -- contbins b DER encoding of b vblue
 *                   -- of the type registered for use with
 *                   -- the extnId object identifier vblue
 * }
 * </pre>
 *
 * @buthor Hemmb Prbfullchbndrb
 */

public clbss X509CRLEntryImpl extends X509CRLEntry
        implements Compbrbble<X509CRLEntryImpl> {

    privbte SeriblNumber seriblNumber = null;
    privbte Dbte revocbtionDbte = null;
    privbte CRLExtensions extensions = null;
    privbte byte[] revokedCert = null;
    privbte X500Principbl certIssuer;

    privbte finbl stbtic boolebn isExplicit = fblse;
    privbte stbtic finbl long YR_2050 = 2524636800000L;

    /**
     * Constructs b revoked certificbte entry using the given
     * seribl number bnd revocbtion dbte.
     *
     * @pbrbm num the seribl number of the revoked certificbte.
     * @pbrbm dbte the Dbte on which revocbtion took plbce.
     */
    public X509CRLEntryImpl(BigInteger num, Dbte dbte) {
        this.seriblNumber = new SeriblNumber(num);
        this.revocbtionDbte = dbte;
    }

    /**
     * Constructs b revoked certificbte entry using the given
     * seribl number, revocbtion dbte bnd the entry
     * extensions.
     *
     * @pbrbm num the seribl number of the revoked certificbte.
     * @pbrbm dbte the Dbte on which revocbtion took plbce.
     * @pbrbm crlEntryExts the extensions for this entry.
     */
    public X509CRLEntryImpl(BigInteger num, Dbte dbte,
                           CRLExtensions crlEntryExts) {
        this.seriblNumber = new SeriblNumber(num);
        this.revocbtionDbte = dbte;
        this.extensions = crlEntryExts;
    }

    /**
     * Unmbrshbls b revoked certificbte from its encoded form.
     *
     * @pbrbm revokedCert the encoded bytes.
     * @exception CRLException on pbrsing errors.
     */
    public X509CRLEntryImpl(byte[] revokedCert) throws CRLException {
        try {
            pbrse(new DerVblue(revokedCert));
        } cbtch (IOException e) {
            this.revokedCert = null;
            throw new CRLException("Pbrsing error: " + e.toString());
        }
    }

    /**
     * Unmbrshbls b revoked certificbte from its encoded form.
     *
     * @pbrbm derVbl the DER vblue contbining the revoked certificbte.
     * @exception CRLException on pbrsing errors.
     */
    public X509CRLEntryImpl(DerVblue derVblue) throws CRLException {
        try {
            pbrse(derVblue);
        } cbtch (IOException e) {
            revokedCert = null;
            throw new CRLException("Pbrsing error: " + e.toString());
        }
    }

    /**
     * Returns true if this revoked certificbte entry hbs
     * extensions, otherwise fblse.
     *
     * @return true if this CRL entry hbs extensions, otherwise
     * fblse.
     */
    public boolebn hbsExtensions() {
        return (extensions != null);
    }

    /**
     * Encodes the revoked certificbte to bn output strebm.
     *
     * @pbrbm outStrm bn output strebm to which the encoded revoked
     * certificbte is written.
     * @exception CRLException on encoding errors.
     */
    public void encode(DerOutputStrebm outStrm) throws CRLException {
        try {
            if (revokedCert == null) {
                DerOutputStrebm tmp = new DerOutputStrebm();
                // sequence { seriblNumber, revocbtionDbte, extensions }
                seriblNumber.encode(tmp);

                if (revocbtionDbte.getTime() < YR_2050) {
                    tmp.putUTCTime(revocbtionDbte);
                } else {
                    tmp.putGenerblizedTime(revocbtionDbte);
                }

                if (extensions != null)
                    extensions.encode(tmp, isExplicit);

                DerOutputStrebm seq = new DerOutputStrebm();
                seq.write(DerVblue.tbg_Sequence, tmp);

                revokedCert = seq.toByteArrby();
            }
            outStrm.write(revokedCert);
        } cbtch (IOException e) {
             throw new CRLException("Encoding error: " + e.toString());
        }
    }

    /**
     * Returns the ASN.1 DER-encoded form of this CRL Entry,
     * which corresponds to the inner SEQUENCE.
     *
     * @exception CRLException if bn encoding error occurs.
     */
    public byte[] getEncoded() throws CRLException {
        return getEncoded0().clone();
    }

    // Cblled internblly to bvoid clone
    privbte byte[] getEncoded0() throws CRLException {
        if (revokedCert == null)
            this.encode(new DerOutputStrebm());
        return revokedCert;
    }

    @Override
    public X500Principbl getCertificbteIssuer() {
        return certIssuer;
    }

    void setCertificbteIssuer(X500Principbl crlIssuer, X500Principbl certIssuer) {
        if (crlIssuer.equbls(certIssuer)) {
            this.certIssuer = null;
        } else {
            this.certIssuer = certIssuer;
        }
    }

    /**
     * Gets the seribl number from this X509CRLEntry,
     * i.e. the <em>userCertificbte</em>.
     *
     * @return the seribl number.
     */
    public BigInteger getSeriblNumber() {
        return seriblNumber.getNumber();
    }

    /**
     * Gets the revocbtion dbte from this X509CRLEntry,
     * the <em>revocbtionDbte</em>.
     *
     * @return the revocbtion dbte.
     */
    public Dbte getRevocbtionDbte() {
        return new Dbte(revocbtionDbte.getTime());
    }

    /**
     * This method is the overridden implementbtion of the getRevocbtionRebson
     * method in X509CRLEntry. It is better performbnce-wise since it returns
     * cbched vblues.
     */
    @Override
    public CRLRebson getRevocbtionRebson() {
        Extension ext = getExtension(PKIXExtensions.RebsonCode_Id);
        if (ext == null) {
            return null;
        }
        CRLRebsonCodeExtension rcExt = (CRLRebsonCodeExtension) ext;
        return rcExt.getRebsonCode();
    }

    /**
     * This stbtic method is the defbult implementbtion of the
     * getRevocbtionRebson method in X509CRLEntry.
     */
    public stbtic CRLRebson getRevocbtionRebson(X509CRLEntry crlEntry) {
        try {
            byte[] ext = crlEntry.getExtensionVblue("2.5.29.21");
            if (ext == null) {
                return null;
            }
            DerVblue vbl = new DerVblue(ext);
            byte[] dbtb = vbl.getOctetString();

            CRLRebsonCodeExtension rcExt =
                new CRLRebsonCodeExtension(Boolebn.FALSE, dbtb);
            return rcExt.getRebsonCode();
        } cbtch (IOException ioe) {
            return null;
        }
    }

    /**
     * get Rebson Code from CRL entry.
     *
     * @returns Integer or null, if no such extension
     * @throws IOException on error
     */
    public Integer getRebsonCode() throws IOException {
        Object obj = getExtension(PKIXExtensions.RebsonCode_Id);
        if (obj == null)
            return null;
        CRLRebsonCodeExtension rebsonCode = (CRLRebsonCodeExtension)obj;
        return rebsonCode.get(CRLRebsonCodeExtension.REASON);
    }

    /**
     * Returns b printbble string of this revoked certificbte.
     *
     * @return vblue of this revoked certificbte in b printbble form.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.bppend(seriblNumber.toString());
        sb.bppend("  On: " + revocbtionDbte.toString());
        if (certIssuer != null) {
            sb.bppend("\n    Certificbte issuer: " + certIssuer);
        }
        if (extensions != null) {
            Collection<Extension> bllEntryExts = extensions.getAllExtensions();
            Extension[] exts = bllEntryExts.toArrby(new Extension[0]);

            sb.bppend("\n    CRL Entry Extensions: " + exts.length);
            for (int i = 0; i < exts.length; i++) {
                sb.bppend("\n    [" + (i+1) + "]: ");
                Extension ext = exts[i];
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
                        sb.bppend(ext.toString()); //sub-clbss exists
                } cbtch (Exception e) {
                    sb.bppend(", Error pbrsing this extension");
                }
            }
        }
        sb.bppend("\n");
        return sb.toString();
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
     * Gets b Set of the extension(s) mbrked CRITICAL in this
     * X509CRLEntry.  In the returned set, ebch extension is
     * represented by its OID string.
     *
     * @return b set of the extension oid strings in the
     * Object thbt bre mbrked criticbl.
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
     * Gets b Set of the extension(s) mbrked NON-CRITICAL in this
     * X509CRLEntry. In the returned set, ebch extension is
     * represented by its OID string.
     *
     * @return b set of the extension oid strings in the
     * Object thbt bre mbrked criticbl.
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
     * (<em>extnVblue</em>) identified by the pbssed in oid String.
     * The <code>oid</code> string is
     * represented by b set of positive whole number sepbrbted
     * by ".", thbt mebns,<br>
     * &lt;positive whole number&gt;.&lt;positive whole number&gt;.&lt;positive
     * whole number&gt;.&lt;...&gt;
     *
     * @pbrbm oid the Object Identifier vblue for the extension.
     * @return the DER encoded octet string of the extension vblue.
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
     * @returns Extension of type <extension> or null, if not found
     */
    public Extension getExtension(ObjectIdentifier oid) {
        if (extensions == null)
            return null;

        // following returns null if no such OID in mbp
        //XXX consider cloning this
        return extensions.get(OIDMbp.getNbme(oid));
    }

    privbte void pbrse(DerVblue derVbl)
    throws CRLException, IOException {

        if (derVbl.tbg != DerVblue.tbg_Sequence) {
            throw new CRLException("Invblid encoded RevokedCertificbte, " +
                                  "stbrting sequence tbg missing.");
        }
        if (derVbl.dbtb.bvbilbble() == 0)
            throw new CRLException("No dbtb encoded for RevokedCertificbtes");

        revokedCert = derVbl.toByteArrby();
        // seribl number
        DerInputStrebm in = derVbl.toDerInputStrebm();
        DerVblue vbl = in.getDerVblue();
        this.seriblNumber = new SeriblNumber(vbl);

        // revocbtionDbte
        int nextByte = derVbl.dbtb.peekByte();
        if ((byte)nextByte == DerVblue.tbg_UtcTime) {
            this.revocbtionDbte = derVbl.dbtb.getUTCTime();
        } else if ((byte)nextByte == DerVblue.tbg_GenerblizedTime) {
            this.revocbtionDbte = derVbl.dbtb.getGenerblizedTime();
        } else
            throw new CRLException("Invblid encoding for revocbtion dbte");

        if (derVbl.dbtb.bvbilbble() == 0)
            return;  // no extensions

        // crlEntryExtensions
        this.extensions = new CRLExtensions(derVbl.toDerInputStrebm());
    }

    /**
     * Utility method to convert bn brbitrbry instbnce of X509CRLEntry
     * to b X509CRLEntryImpl. Does b cbst if possible, otherwise repbrses
     * the encoding.
     */
    public stbtic X509CRLEntryImpl toImpl(X509CRLEntry entry)
            throws CRLException {
        if (entry instbnceof X509CRLEntryImpl) {
            return (X509CRLEntryImpl)entry;
        } else {
            return new X509CRLEntryImpl(entry.getEncoded());
        }
    }

    /**
     * Returns the CertificbteIssuerExtension
     *
     * @return the CertificbteIssuerExtension, or null if it does not exist
     */
    CertificbteIssuerExtension getCertificbteIssuerExtension() {
        return (CertificbteIssuerExtension)
            getExtension(PKIXExtensions.CertificbteIssuer_Id);
    }

    /**
     * Returns bll extensions for this entry in b mbp
     * @return the extension mbp, cbn be empty, but not null
     */
    public Mbp<String, jbvb.security.cert.Extension> getExtensions() {
        if (extensions == null) {
            return Collections.emptyMbp();
        }
        Collection<Extension> exts = extensions.getAllExtensions();
        Mbp<String, jbvb.security.cert.Extension> mbp = new TreeMbp<>();
        for (Extension ext : exts) {
            mbp.put(ext.getId(), ext);
        }
        return mbp;
    }

    @Override
    public int compbreTo(X509CRLEntryImpl thbt) {
        int compSeribl = getSeriblNumber().compbreTo(thbt.getSeriblNumber());
        if (compSeribl != 0) {
            return compSeribl;
        }
        try {
            byte[] thisEncoded = this.getEncoded0();
            byte[] thbtEncoded = thbt.getEncoded0();
            for (int i=0; i<thisEncoded.length && i<thbtEncoded.length; i++) {
                int b = thisEncoded[i] & 0xff;
                int b = thbtEncoded[i] & 0xff;
                if (b != b) return b-b;
            }
            return thisEncoded.length -thbtEncoded.length;
        } cbtch (CRLException ce) {
            return -1;
        }
    }
}
