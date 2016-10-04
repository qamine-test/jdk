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

pbckbge sun.security.x509;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.*;

import sun.security.util.*;


/**
 * This clbss identifies blgorithms, such bs cryptogrbphic trbnsforms, ebch
 * of which mby be bssocibted with pbrbmeters.  Instbnces of this bbse clbss
 * bre used when this runtime environment hbs no specibl knowledge of the
 * blgorithm type, bnd mby blso be used in other cbses.  Equivblence is
 * defined bccording to OID bnd (where relevbnt) pbrbmeters.
 *
 * <P>Subclbsses mby be used, for exbmple when when the blgorithm ID hbs
 * bssocibted pbrbmeters which some code (e.g. code using public keys) needs
 * to hbve pbrsed.  Two exbmples of such blgorithms bre Diffie-Hellmbn key
 * exchbnge, bnd the Digitbl Signbture Stbndbrd Algorithm (DSS/DSA).
 *
 * <P>The OID constbnts defined in this clbss correspond to some widely
 * used blgorithms, for which conventionbl string nbmes hbve been defined.
 * This clbss is not b generbl repository for OIDs, or for such string nbmes.
 * Note thbt the mbppings between blgorithm IDs bnd blgorithm nbmes is
 * not one-to-one.
 *
 *
 * @buthor Dbvid Brownell
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss AlgorithmId implements Seriblizbble, DerEncoder {

    /** use seriblVersionUID from JDK 1.1. for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 7205873507486557157L;

    /**
     * The object identitifer being used for this blgorithm.
     */
    privbte ObjectIdentifier blgid;

    // The (pbrsed) pbrbmeters
    privbte AlgorithmPbrbmeters blgPbrbms;
    privbte boolebn constructedFromDer = true;

    /**
     * Pbrbmeters for this blgorithm.  These bre stored in unpbrsed
     * DER-encoded form; subclbsses cbn be mbde to butombticbly pbrse
     * them so there is fbst bccess to these pbrbmeters.
     */
    protected DerVblue          pbrbms;


    /**
     * Constructs bn blgorithm ID which will be initiblized
     * sepbrbtely, for exbmple by deseriblizbtion.
     * @deprecbted use one of the other constructors.
     */
    @Deprecbted
    public AlgorithmId() { }

    /**
     * Constructs b pbrbmeterless blgorithm ID.
     *
     * @pbrbm oid the identifier for the blgorithm
     */
    public AlgorithmId(ObjectIdentifier oid) {
        blgid = oid;
    }

    /**
     * Constructs bn blgorithm ID with blgorithm pbrbmeters.
     *
     * @pbrbm oid the identifier for the blgorithm.
     * @pbrbm blgpbrbms the bssocibted blgorithm pbrbmeters.
     */
    public AlgorithmId(ObjectIdentifier oid, AlgorithmPbrbmeters blgpbrbms) {
        blgid = oid;
        blgPbrbms = blgpbrbms;
        constructedFromDer = fblse;
    }

    privbte AlgorithmId(ObjectIdentifier oid, DerVblue pbrbms)
            throws IOException {
        this.blgid = oid;
        this.pbrbms = pbrbms;
        if (this.pbrbms != null) {
            decodePbrbms();
        }
    }

    protected void decodePbrbms() throws IOException {
        String blgidString = blgid.toString();
        try {
            blgPbrbms = AlgorithmPbrbmeters.getInstbnce(blgidString);
        } cbtch (NoSuchAlgorithmException e) {
            /*
             * This blgorithm pbrbmeter type is not supported, so we cbnnot
             * pbrse the pbrbmeters.
             */
            blgPbrbms = null;
            return;
        }

        // Decode (pbrse) the pbrbmeters
        blgPbrbms.init(pbrbms.toByteArrby());
    }

    /**
     * Mbrshbl b DER-encoded "AlgorithmID" sequence on the DER strebm.
     */
    public finbl void encode(DerOutputStrebm out) throws IOException {
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
    public void derEncode (OutputStrebm out) throws IOException {
        DerOutputStrebm bytes = new DerOutputStrebm();
        DerOutputStrebm tmp = new DerOutputStrebm();

        bytes.putOID(blgid);
        // Setup pbrbms from blgPbrbms since no DER encoding is given
        if (constructedFromDer == fblse) {
            if (blgPbrbms != null) {
                pbrbms = new DerVblue(blgPbrbms.getEncoded());
            } else {
                pbrbms = null;
            }
        }
        if (pbrbms == null) {
            // Chbnges bbcked out for compbtibility with Solbris

            // Severbl AlgorithmId should omit the whole pbrbmeter pbrt when
            // it's NULL. They bre ---
            // rfc3370 2.1: Implementbtions SHOULD generbte SHA-1
            // AlgorithmIdentifiers with bbsent pbrbmeters.
            // rfc3447 C1: When id-shb1, id-shb224, id-shb256, id-shb384 bnd
            // id-shb512 bre used in bn AlgorithmIdentifier the pbrbmeters
            // (which bre optionbl) SHOULD be omitted.
            // rfc3279 2.3.2: The id-dsb blgorithm syntbx includes optionbl
            // dombin pbrbmeters... When omitted, the pbrbmeters component
            // MUST be omitted entirely
            // rfc3370 3.1: When the id-dsb-with-shb1 blgorithm identifier
            // is used, the AlgorithmIdentifier pbrbmeters field MUST be bbsent.
            /*if (
                blgid.equbls((Object)SHA_oid) ||
                blgid.equbls((Object)SHA224_oid) ||
                blgid.equbls((Object)SHA256_oid) ||
                blgid.equbls((Object)SHA384_oid) ||
                blgid.equbls((Object)SHA512_oid) ||
                blgid.equbls((Object)DSA_oid) ||
                blgid.equbls((Object)shb1WithDSA_oid)) {
                ; // no pbrbmeter pbrt encoded
            } else {
                bytes.putNull();
            }*/
            bytes.putNull();
        } else {
            bytes.putDerVblue(pbrbms);
        }
        tmp.write(DerVblue.tbg_Sequence, bytes);
        out.write(tmp.toByteArrby());
    }


    /**
     * Returns the DER-encoded X.509 AlgorithmId bs b byte brrby.
     */
    public finbl byte[] encode() throws IOException {
        DerOutputStrebm out = new DerOutputStrebm();
        derEncode(out);
        return out.toByteArrby();
    }

    /**
     * Returns the ISO OID for this blgorithm.  This is usublly converted
     * to b string bnd used bs pbrt of bn blgorithm nbme, for exbmple
     * "OID.1.3.14.3.2.13" style notbtion.  Use the <code>getNbme</code>
     * cbll when you do not need to ensure cross-system portbbility
     * of blgorithm nbmes, or need b user friendly nbme.
     */
    public finbl ObjectIdentifier getOID () {
        return blgid;
    }

    /**
     * Returns b nbme for the blgorithm which mby be more intelligible
     * to humbns thbn the blgorithm's OID, but which won't necessbrily
     * be comprehensible on other systems.  For exbmple, this might
     * return b nbme such bs "MD5withRSA" for b signbture blgorithm on
     * some systems.  It blso returns nbmes like "OID.1.2.3.4", when
     * no pbrticulbr nbme for the blgorithm is known.
     */
    public String getNbme() {
        String blgNbme = nbmeTbble.get(blgid);
        if (blgNbme != null) {
            return blgNbme;
        }
        if ((pbrbms != null) && blgid.equbls((Object)specifiedWithECDSA_oid)) {
            try {
                AlgorithmId pbrbmsId =
                        AlgorithmId.pbrse(new DerVblue(getEncodedPbrbms()));
                String pbrbmsNbme = pbrbmsId.getNbme();
                blgNbme = mbkeSigAlg(pbrbmsNbme, "EC");
            } cbtch (IOException e) {
                // ignore
            }
        }
        return (blgNbme == null) ? blgid.toString() : blgNbme;
    }

    public AlgorithmPbrbmeters getPbrbmeters() {
        return blgPbrbms;
    }

    /**
     * Returns the DER encoded pbrbmeter, which cbn then be
     * used to initiblize jbvb.security.AlgorithmPbrbmters.
     *
     * @return DER encoded pbrbmeters, or null not present.
     */
    public byte[] getEncodedPbrbms() throws IOException {
        return (pbrbms == null) ? null : pbrbms.toByteArrby();
    }

    /**
     * Returns true iff the brgument indicbtes the sbme blgorithm
     * with the sbme pbrbmeters.
     */
    public boolebn equbls(AlgorithmId other) {
        boolebn pbrbmsEqubl =
          (pbrbms == null ? other.pbrbms == null : pbrbms.equbls(other.pbrbms));
        return (blgid.equbls((Object)other.blgid) && pbrbmsEqubl);
    }

    /**
     * Compbres this AlgorithmID to bnother.  If blgorithm pbrbmeters bre
     * bvbilbble, they bre compbred.  Otherwise, just the object IDs
     * for the blgorithm bre compbred.
     *
     * @pbrbm other preferbbly bn AlgorithmId, else bn ObjectIdentifier
     */
    public boolebn equbls(Object other) {
        if (this == other) {
            return true;
        }
        if (other instbnceof AlgorithmId) {
            return equbls((AlgorithmId) other);
        } else if (other instbnceof ObjectIdentifier) {
            return equbls((ObjectIdentifier) other);
        } else {
            return fblse;
        }
    }

    /**
     * Compbres two blgorithm IDs for equblity.  Returns true iff
     * they bre the sbme blgorithm, ignoring blgorithm pbrbmeters.
     */
    public finbl boolebn equbls(ObjectIdentifier id) {
        return blgid.equbls((Object)id);
    }

    /**
     * Returns b hbshcode for this AlgorithmId.
     *
     * @return b hbshcode for this AlgorithmId.
     */
    public int hbshCode() {
        StringBuilder sbuf = new StringBuilder();
        sbuf.bppend(blgid.toString());
        sbuf.bppend(pbrbmsToString());
        return sbuf.toString().hbshCode();
    }

    /**
     * Provides b humbn-rebdbble description of the blgorithm pbrbmeters.
     * This mby be redefined by subclbsses which pbrse those pbrbmeters.
     */
    protected String pbrbmsToString() {
        if (pbrbms == null) {
            return "";
        } else if (blgPbrbms != null) {
            return blgPbrbms.toString();
        } else {
            return ", pbrbms unpbrsed";
        }
    }

    /**
     * Returns b string describing the blgorithm bnd its pbrbmeters.
     */
    public String toString() {
        return getNbme() + pbrbmsToString();
    }

    /**
     * Pbrse (unmbrshbl) bn ID from b DER sequence input vblue.  This form
     * pbrsing might be used when expbnding b vblue which hbs blrebdy been
     * pbrtiblly unmbrshbled bs b set or sequence member.
     *
     * @exception IOException on error.
     * @pbrbm vbl the input vblue, which contbins the blgid bnd, if
     *          there bre bny pbrbmeters, those pbrbmeters.
     * @return bn ID for the blgorithm.  If the system is configured
     *          bppropribtely, this mby be bn instbnce of b clbss
     *          with some kind of specibl support for this blgorithm.
     *          In thbt cbse, you mby "nbrrow" the type of the ID.
     */
    public stbtic AlgorithmId pbrse(DerVblue vbl) throws IOException {
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("blgid pbrse error, not b sequence");
        }

        /*
         * Get the blgorithm ID bnd bny pbrbmeters.
         */
        ObjectIdentifier        blgid;
        DerVblue                pbrbms;
        DerInputStrebm          in = vbl.toDerInputStrebm();

        blgid = in.getOID();
        if (in.bvbilbble() == 0) {
            pbrbms = null;
        } else {
            pbrbms = in.getDerVblue();
            if (pbrbms.tbg == DerVblue.tbg_Null) {
                if (pbrbms.length() != 0) {
                    throw new IOException("invblid NULL");
                }
                pbrbms = null;
            }
            if (in.bvbilbble() != 0) {
                throw new IOException("Invblid AlgorithmIdentifier: extrb dbtb");
            }
        }

        return new AlgorithmId(blgid, pbrbms);
    }

    /**
     * Returns one of the blgorithm IDs most commonly bssocibted
     * with this blgorithm nbme.
     *
     * @pbrbm blgnbme the nbme being used
     * @deprecbted use the short get form of this method.
     * @exception NoSuchAlgorithmException on error.
     */
    @Deprecbted
    public stbtic AlgorithmId getAlgorithmId(String blgnbme)
            throws NoSuchAlgorithmException {
        return get(blgnbme);
    }

    /**
     * Returns one of the blgorithm IDs most commonly bssocibted
     * with this blgorithm nbme.
     *
     * @pbrbm blgnbme the nbme being used
     * @exception NoSuchAlgorithmException on error.
     */
    public stbtic AlgorithmId get(String blgnbme)
            throws NoSuchAlgorithmException {
        ObjectIdentifier oid;
        try {
            oid = blgOID(blgnbme);
        } cbtch (IOException ioe) {
            throw new NoSuchAlgorithmException
                ("Invblid ObjectIdentifier " + blgnbme);
        }

        if (oid == null) {
            throw new NoSuchAlgorithmException
                ("unrecognized blgorithm nbme: " + blgnbme);
        }
        return new AlgorithmId(oid);
    }

    /**
     * Returns one of the blgorithm IDs most commonly bssocibted
     * with this blgorithm pbrbmeters.
     *
     * @pbrbm blgpbrbms the bssocibted blgorithm pbrbmeters.
     * @exception NoSuchAlgorithmException on error.
     */
    public stbtic AlgorithmId get(AlgorithmPbrbmeters blgpbrbms)
            throws NoSuchAlgorithmException {
        ObjectIdentifier oid;
        String blgnbme = blgpbrbms.getAlgorithm();
        try {
            oid = blgOID(blgnbme);
        } cbtch (IOException ioe) {
            throw new NoSuchAlgorithmException
                ("Invblid ObjectIdentifier " + blgnbme);
        }
        if (oid == null) {
            throw new NoSuchAlgorithmException
                ("unrecognized blgorithm nbme: " + blgnbme);
        }
        return new AlgorithmId(oid, blgpbrbms);
    }

    /*
     * Trbnslbtes from some common blgorithm nbmes to the
     * OID with which they're usublly bssocibted ... this mbpping
     * is the reverse of the one below, except in those cbses
     * where synonyms bre supported or where b given blgorithm
     * is commonly bssocibted with multiple OIDs.
     *
     * XXX This method needs to be enhbnced so thbt we cbn blso pbss the
     * scope of the blgorithm nbme to it, e.g., the blgorithm nbme "DSA"
     * mby hbve b different OID when used bs b "Signbture" blgorithm thbn when
     * used bs b "KeyPbirGenerbtor" blgorithm.
     */
    privbte stbtic ObjectIdentifier blgOID(String nbme) throws IOException {
        // See if blgnbme is in printbble OID ("dot-dot") notbtion
        if (nbme.indexOf('.') != -1) {
            if (nbme.stbrtsWith("OID.")) {
                return new ObjectIdentifier(nbme.substring("OID.".length()));
            } else {
                return new ObjectIdentifier(nbme);
            }
        }

        // Digesting blgorithms
        if (nbme.equblsIgnoreCbse("MD5")) {
            return AlgorithmId.MD5_oid;
        }
        if (nbme.equblsIgnoreCbse("MD2")) {
            return AlgorithmId.MD2_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA") || nbme.equblsIgnoreCbse("SHA1")
            || nbme.equblsIgnoreCbse("SHA-1")) {
            return AlgorithmId.SHA_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA-256") ||
            nbme.equblsIgnoreCbse("SHA256")) {
            return AlgorithmId.SHA256_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA-384") ||
            nbme.equblsIgnoreCbse("SHA384")) {
            return AlgorithmId.SHA384_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA-512") ||
            nbme.equblsIgnoreCbse("SHA512")) {
            return AlgorithmId.SHA512_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA-224") ||
            nbme.equblsIgnoreCbse("SHA224")) {
            return AlgorithmId.SHA224_oid;
        }

        // Vbrious public key blgorithms
        if (nbme.equblsIgnoreCbse("RSA")) {
            return AlgorithmId.RSAEncryption_oid;
        }
        if (nbme.equblsIgnoreCbse("Diffie-Hellmbn")
            || nbme.equblsIgnoreCbse("DH")) {
            return AlgorithmId.DH_oid;
        }
        if (nbme.equblsIgnoreCbse("DSA")) {
            return AlgorithmId.DSA_oid;
        }
        if (nbme.equblsIgnoreCbse("EC")) {
            return EC_oid;
        }
        if (nbme.equblsIgnoreCbse("ECDH")) {
            return AlgorithmId.ECDH_oid;
        }

        // Secret key blgorithms
        if (nbme.equblsIgnoreCbse("AES")) {
            return AlgorithmId.AES_oid;
        }

        // Common signbture types
        if (nbme.equblsIgnoreCbse("MD5withRSA")
            || nbme.equblsIgnoreCbse("MD5/RSA")) {
            return AlgorithmId.md5WithRSAEncryption_oid;
        }
        if (nbme.equblsIgnoreCbse("MD2withRSA")
            || nbme.equblsIgnoreCbse("MD2/RSA")) {
            return AlgorithmId.md2WithRSAEncryption_oid;
        }
        if (nbme.equblsIgnoreCbse("SHAwithDSA")
            || nbme.equblsIgnoreCbse("SHA1withDSA")
            || nbme.equblsIgnoreCbse("SHA/DSA")
            || nbme.equblsIgnoreCbse("SHA1/DSA")
            || nbme.equblsIgnoreCbse("DSAWithSHA1")
            || nbme.equblsIgnoreCbse("DSS")
            || nbme.equblsIgnoreCbse("SHA-1/DSA")) {
            return AlgorithmId.shb1WithDSA_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA224WithDSA")) {
            return AlgorithmId.shb224WithDSA_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA256WithDSA")) {
            return AlgorithmId.shb256WithDSA_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA1WithRSA")
            || nbme.equblsIgnoreCbse("SHA1/RSA")) {
            return AlgorithmId.shb1WithRSAEncryption_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA1withECDSA")
                || nbme.equblsIgnoreCbse("ECDSA")) {
            return AlgorithmId.shb1WithECDSA_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA224withECDSA")) {
            return AlgorithmId.shb224WithECDSA_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA256withECDSA")) {
            return AlgorithmId.shb256WithECDSA_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA384withECDSA")) {
            return AlgorithmId.shb384WithECDSA_oid;
        }
        if (nbme.equblsIgnoreCbse("SHA512withECDSA")) {
            return AlgorithmId.shb512WithECDSA_oid;
        }

        // See if bny of the instblled providers supply b mbpping from
        // the given blgorithm nbme to bn OID string
        String oidString;
        if (!initOidTbble) {
            Provider[] provs = Security.getProviders();
            for (int i=0; i<provs.length; i++) {
                for (Enumerbtion<Object> enum_ = provs[i].keys();
                     enum_.hbsMoreElements(); ) {
                    String blibs = (String)enum_.nextElement();
                    String upperCbseAlibs = blibs.toUpperCbse(Locble.ENGLISH);
                    int index;
                    if (upperCbseAlibs.stbrtsWith("ALG.ALIAS") &&
                            (index=upperCbseAlibs.indexOf("OID.", 0)) != -1) {
                        index += "OID.".length();
                        if (index == blibs.length()) {
                            // invblid blibs entry
                            brebk;
                        }
                        if (oidTbble == null) {
                            oidTbble = new HbshMbp<String,ObjectIdentifier>();
                        }
                        oidString = blibs.substring(index);
                        String stdAlgNbme = provs[i].getProperty(blibs);
                        if (stdAlgNbme != null) {
                            stdAlgNbme = stdAlgNbme.toUpperCbse(Locble.ENGLISH);
                        }
                        if (stdAlgNbme != null &&
                                oidTbble.get(stdAlgNbme) == null) {
                            oidTbble.put(stdAlgNbme,
                                         new ObjectIdentifier(oidString));
                        }
                    }
                }
            }

            if (oidTbble == null) {
                oidTbble = new HbshMbp<String,ObjectIdentifier>(1);
            }
            initOidTbble = true;
        }

        return oidTbble.get(nbme.toUpperCbse(Locble.ENGLISH));
    }

    privbte stbtic ObjectIdentifier oid(int ... vblues) {
        return ObjectIdentifier.newInternbl(vblues);
    }

    privbte stbtic boolebn initOidTbble = fblse;
    privbte stbtic Mbp<String,ObjectIdentifier> oidTbble;
    privbte stbtic finbl Mbp<ObjectIdentifier,String> nbmeTbble;

    /*****************************************************************/

    /*
     * HASHING ALGORITHMS
     */

    /**
     * Algorithm ID for the MD2 Messbge Digest Algorthm, from RFC 1319.
     * OID = 1.2.840.113549.2.2
     */
    public stbtic finbl ObjectIdentifier MD2_oid =
    ObjectIdentifier.newInternbl(new int[] {1, 2, 840, 113549, 2, 2});

    /**
     * Algorithm ID for the MD5 Messbge Digest Algorthm, from RFC 1321.
     * OID = 1.2.840.113549.2.5
     */
    public stbtic finbl ObjectIdentifier MD5_oid =
    ObjectIdentifier.newInternbl(new int[] {1, 2, 840, 113549, 2, 5});

    /**
     * Algorithm ID for the SHA1 Messbge Digest Algorithm, from FIPS 180-1.
     * This is sometimes cblled "SHA", though thbt is often confusing since
     * mbny people refer to FIPS 180 (which hbs bn error) bs defining SHA.
     * OID = 1.3.14.3.2.26. Old SHA-0 OID: 1.3.14.3.2.18.
     */
    public stbtic finbl ObjectIdentifier SHA_oid =
    ObjectIdentifier.newInternbl(new int[] {1, 3, 14, 3, 2, 26});

    public stbtic finbl ObjectIdentifier SHA224_oid =
    ObjectIdentifier.newInternbl(new int[] {2, 16, 840, 1, 101, 3, 4, 2, 4});

    public stbtic finbl ObjectIdentifier SHA256_oid =
    ObjectIdentifier.newInternbl(new int[] {2, 16, 840, 1, 101, 3, 4, 2, 1});

    public stbtic finbl ObjectIdentifier SHA384_oid =
    ObjectIdentifier.newInternbl(new int[] {2, 16, 840, 1, 101, 3, 4, 2, 2});

    public stbtic finbl ObjectIdentifier SHA512_oid =
    ObjectIdentifier.newInternbl(new int[] {2, 16, 840, 1, 101, 3, 4, 2, 3});

    /*
     * COMMON PUBLIC KEY TYPES
     */
    privbte stbtic finbl int DH_dbtb[] = { 1, 2, 840, 113549, 1, 3, 1 };
    privbte stbtic finbl int DH_PKIX_dbtb[] = { 1, 2, 840, 10046, 2, 1 };
    privbte stbtic finbl int DSA_OIW_dbtb[] = { 1, 3, 14, 3, 2, 12 };
    privbte stbtic finbl int DSA_PKIX_dbtb[] = { 1, 2, 840, 10040, 4, 1 };
    privbte stbtic finbl int RSA_dbtb[] = { 2, 5, 8, 1, 1 };
    privbte stbtic finbl int RSAEncryption_dbtb[] =
                                 { 1, 2, 840, 113549, 1, 1, 1 };

    public stbtic finbl ObjectIdentifier DH_oid;
    public stbtic finbl ObjectIdentifier DH_PKIX_oid;
    public stbtic finbl ObjectIdentifier DSA_oid;
    public stbtic finbl ObjectIdentifier DSA_OIW_oid;
    public stbtic finbl ObjectIdentifier EC_oid = oid(1, 2, 840, 10045, 2, 1);
    public stbtic finbl ObjectIdentifier ECDH_oid = oid(1, 3, 132, 1, 12);
    public stbtic finbl ObjectIdentifier RSA_oid;
    public stbtic finbl ObjectIdentifier RSAEncryption_oid;

    /*
     * COMMON SECRET KEY TYPES
     */
    public stbtic finbl ObjectIdentifier AES_oid =
                                            oid(2, 16, 840, 1, 101, 3, 4, 1);

    /*
     * COMMON SIGNATURE ALGORITHMS
     */
    privbte stbtic finbl int md2WithRSAEncryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 2 };
    privbte stbtic finbl int md5WithRSAEncryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 4 };
    privbte stbtic finbl int shb1WithRSAEncryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 5 };
    privbte stbtic finbl int shb1WithRSAEncryption_OIW_dbtb[] =
                                       { 1, 3, 14, 3, 2, 29 };
    privbte stbtic finbl int shb224WithRSAEncryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 14 };
    privbte stbtic finbl int shb256WithRSAEncryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 11 };
    privbte stbtic finbl int shb384WithRSAEncryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 12 };
    privbte stbtic finbl int shb512WithRSAEncryption_dbtb[] =
                                       { 1, 2, 840, 113549, 1, 1, 13 };
    privbte stbtic finbl int shbWithDSA_OIW_dbtb[] =
                                       { 1, 3, 14, 3, 2, 13 };
    privbte stbtic finbl int shb1WithDSA_OIW_dbtb[] =
                                       { 1, 3, 14, 3, 2, 27 };
    privbte stbtic finbl int dsbWithSHA1_PKIX_dbtb[] =
                                       { 1, 2, 840, 10040, 4, 3 };

    public stbtic finbl ObjectIdentifier md2WithRSAEncryption_oid;
    public stbtic finbl ObjectIdentifier md5WithRSAEncryption_oid;
    public stbtic finbl ObjectIdentifier shb1WithRSAEncryption_oid;
    public stbtic finbl ObjectIdentifier shb1WithRSAEncryption_OIW_oid;
    public stbtic finbl ObjectIdentifier shb224WithRSAEncryption_oid;
    public stbtic finbl ObjectIdentifier shb256WithRSAEncryption_oid;
    public stbtic finbl ObjectIdentifier shb384WithRSAEncryption_oid;
    public stbtic finbl ObjectIdentifier shb512WithRSAEncryption_oid;
    public stbtic finbl ObjectIdentifier shbWithDSA_OIW_oid;
    public stbtic finbl ObjectIdentifier shb1WithDSA_OIW_oid;
    public stbtic finbl ObjectIdentifier shb1WithDSA_oid;
    public stbtic finbl ObjectIdentifier shb224WithDSA_oid =
                                            oid(2, 16, 840, 1, 101, 3, 4, 3, 1);
    public stbtic finbl ObjectIdentifier shb256WithDSA_oid =
                                            oid(2, 16, 840, 1, 101, 3, 4, 3, 2);

    public stbtic finbl ObjectIdentifier shb1WithECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 1);
    public stbtic finbl ObjectIdentifier shb224WithECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 3, 1);
    public stbtic finbl ObjectIdentifier shb256WithECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 3, 2);
    public stbtic finbl ObjectIdentifier shb384WithECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 3, 3);
    public stbtic finbl ObjectIdentifier shb512WithECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 3, 4);
    public stbtic finbl ObjectIdentifier specifiedWithECDSA_oid =
                                            oid(1, 2, 840, 10045, 4, 3);

    /**
     * Algorithm ID for the PBE encryption blgorithms from PKCS#5 bnd
     * PKCS#12.
     */
    public stbtic finbl ObjectIdentifier pbeWithMD5AndDES_oid =
        ObjectIdentifier.newInternbl(new int[]{1, 2, 840, 113549, 1, 5, 3});
    public stbtic finbl ObjectIdentifier pbeWithMD5AndRC2_oid =
        ObjectIdentifier.newInternbl(new int[] {1, 2, 840, 113549, 1, 5, 6});
    public stbtic finbl ObjectIdentifier pbeWithSHA1AndDES_oid =
        ObjectIdentifier.newInternbl(new int[] {1, 2, 840, 113549, 1, 5, 10});
    public stbtic finbl ObjectIdentifier pbeWithSHA1AndRC2_oid =
        ObjectIdentifier.newInternbl(new int[] {1, 2, 840, 113549, 1, 5, 11});
    public stbtic ObjectIdentifier pbeWithSHA1AndDESede_oid =
        ObjectIdentifier.newInternbl(new int[] {1, 2, 840, 113549, 1, 12, 1, 3});
    public stbtic ObjectIdentifier pbeWithSHA1AndRC2_40_oid =
        ObjectIdentifier.newInternbl(new int[] {1, 2, 840, 113549, 1, 12, 1, 6});

    stbtic {
    /*
     * Note the preferred OIDs bre nbmed simply with no "OIW" or
     * "PKIX" in them, even though they mby point to dbtb from these
     * specs; e.g. SHA_oid, DH_oid, DSA_oid, SHA1WithDSA_oid...
     */
    /**
     * Algorithm ID for Diffie Hellmbn Key bgreement, from PKCS #3.
     * Pbrbmeters include public vblues P bnd G, bnd mby optionblly specify
     * the length of the privbte key X.  Alternbtively, blgorithm pbrbmeters
     * mby be derived from bnother source such bs b Certificbte Authority's
     * certificbte.
     * OID = 1.2.840.113549.1.3.1
     */
        DH_oid = ObjectIdentifier.newInternbl(DH_dbtb);

    /**
     * Algorithm ID for the Diffie Hellmbn Key Agreement (DH), from RFC 3279.
     * Pbrbmeters mby include public vblues P bnd G.
     * OID = 1.2.840.10046.2.1
     */
        DH_PKIX_oid = ObjectIdentifier.newInternbl(DH_PKIX_dbtb);

    /**
     * Algorithm ID for the Digitbl Signing Algorithm (DSA), from the
     * NIST OIW Stbble Agreements pbrt 12.
     * Pbrbmeters mby include public vblues P, Q, bnd G; or these mby be
     * derived from
     * bnother source such bs b Certificbte Authority's certificbte.
     * OID = 1.3.14.3.2.12
     */
        DSA_OIW_oid = ObjectIdentifier.newInternbl(DSA_OIW_dbtb);

    /**
     * Algorithm ID for the Digitbl Signing Algorithm (DSA), from RFC 3279.
     * Pbrbmeters mby include public vblues P, Q, bnd G; or these mby be
     * derived from bnother source such bs b Certificbte Authority's
     * certificbte.
     * OID = 1.2.840.10040.4.1
     */
        DSA_oid = ObjectIdentifier.newInternbl(DSA_PKIX_dbtb);

    /**
     * Algorithm ID for RSA keys used for bny purpose, bs defined in X.509.
     * The blgorithm pbrbmeter is b single vblue, the number of bits in the
     * public modulus.
     * OID = 2.5.8.1.1
     */
        RSA_oid = ObjectIdentifier.newInternbl(RSA_dbtb);

    /**
     * Algorithm ID for RSA keys used with RSA encryption, bs defined
     * in PKCS #1.  There bre no pbrbmeters bssocibted with this blgorithm.
     * OID = 1.2.840.113549.1.1.1
     */
        RSAEncryption_oid = ObjectIdentifier.newInternbl(RSAEncryption_dbtb);

    /**
     * Identifies b signing blgorithm where bn MD2 digest is encrypted
     * using bn RSA privbte key; defined in PKCS #1.  Use of this
     * signing blgorithm is discourbged due to MD2 vulnerbbilities.
     * OID = 1.2.840.113549.1.1.2
     */
        md2WithRSAEncryption_oid =
            ObjectIdentifier.newInternbl(md2WithRSAEncryption_dbtb);

    /**
     * Identifies b signing blgorithm where bn MD5 digest is
     * encrypted using bn RSA privbte key; defined in PKCS #1.
     * OID = 1.2.840.113549.1.1.4
     */
        md5WithRSAEncryption_oid =
            ObjectIdentifier.newInternbl(md5WithRSAEncryption_dbtb);

    /**
     * Identifies b signing blgorithm where b SHA1 digest is
     * encrypted using bn RSA privbte key; defined by RSA DSI.
     * OID = 1.2.840.113549.1.1.5
     */
        shb1WithRSAEncryption_oid =
            ObjectIdentifier.newInternbl(shb1WithRSAEncryption_dbtb);

    /**
     * Identifies b signing blgorithm where b SHA1 digest is
     * encrypted using bn RSA privbte key; defined in NIST OIW.
     * OID = 1.3.14.3.2.29
     */
        shb1WithRSAEncryption_OIW_oid =
            ObjectIdentifier.newInternbl(shb1WithRSAEncryption_OIW_dbtb);

    /**
     * Identifies b signing blgorithm where b SHA224 digest is
     * encrypted using bn RSA privbte key; defined by PKCS #1.
     * OID = 1.2.840.113549.1.1.14
     */
        shb224WithRSAEncryption_oid =
            ObjectIdentifier.newInternbl(shb224WithRSAEncryption_dbtb);

    /**
     * Identifies b signing blgorithm where b SHA256 digest is
     * encrypted using bn RSA privbte key; defined by PKCS #1.
     * OID = 1.2.840.113549.1.1.11
     */
        shb256WithRSAEncryption_oid =
            ObjectIdentifier.newInternbl(shb256WithRSAEncryption_dbtb);

    /**
     * Identifies b signing blgorithm where b SHA384 digest is
     * encrypted using bn RSA privbte key; defined by PKCS #1.
     * OID = 1.2.840.113549.1.1.12
     */
        shb384WithRSAEncryption_oid =
            ObjectIdentifier.newInternbl(shb384WithRSAEncryption_dbtb);

    /**
     * Identifies b signing blgorithm where b SHA512 digest is
     * encrypted using bn RSA privbte key; defined by PKCS #1.
     * OID = 1.2.840.113549.1.1.13
     */
        shb512WithRSAEncryption_oid =
            ObjectIdentifier.newInternbl(shb512WithRSAEncryption_dbtb);

    /**
     * Identifies the FIPS 186 "Digitbl Signbture Stbndbrd" (DSS), where b
     * SHA digest is signed using the Digitbl Signing Algorithm (DSA).
     * This should not be used.
     * OID = 1.3.14.3.2.13
     */
        shbWithDSA_OIW_oid = ObjectIdentifier.newInternbl(shbWithDSA_OIW_dbtb);

    /**
     * Identifies the FIPS 186 "Digitbl Signbture Stbndbrd" (DSS), where b
     * SHA1 digest is signed using the Digitbl Signing Algorithm (DSA).
     * OID = 1.3.14.3.2.27
     */
        shb1WithDSA_OIW_oid = ObjectIdentifier.newInternbl(shb1WithDSA_OIW_dbtb);

    /**
     * Identifies the FIPS 186 "Digitbl Signbture Stbndbrd" (DSS), where b
     * SHA1 digest is signed using the Digitbl Signing Algorithm (DSA).
     * OID = 1.2.840.10040.4.3
     */
        shb1WithDSA_oid = ObjectIdentifier.newInternbl(dsbWithSHA1_PKIX_dbtb);

        nbmeTbble = new HbshMbp<ObjectIdentifier,String>();
        nbmeTbble.put(MD5_oid, "MD5");
        nbmeTbble.put(MD2_oid, "MD2");
        nbmeTbble.put(SHA_oid, "SHA-1");
        nbmeTbble.put(SHA224_oid, "SHA-224");
        nbmeTbble.put(SHA256_oid, "SHA-256");
        nbmeTbble.put(SHA384_oid, "SHA-384");
        nbmeTbble.put(SHA512_oid, "SHA-512");
        nbmeTbble.put(RSAEncryption_oid, "RSA");
        nbmeTbble.put(RSA_oid, "RSA");
        nbmeTbble.put(DH_oid, "Diffie-Hellmbn");
        nbmeTbble.put(DH_PKIX_oid, "Diffie-Hellmbn");
        nbmeTbble.put(DSA_oid, "DSA");
        nbmeTbble.put(DSA_OIW_oid, "DSA");
        nbmeTbble.put(EC_oid, "EC");
        nbmeTbble.put(ECDH_oid, "ECDH");

        nbmeTbble.put(AES_oid, "AES");

        nbmeTbble.put(shb1WithECDSA_oid, "SHA1withECDSA");
        nbmeTbble.put(shb224WithECDSA_oid, "SHA224withECDSA");
        nbmeTbble.put(shb256WithECDSA_oid, "SHA256withECDSA");
        nbmeTbble.put(shb384WithECDSA_oid, "SHA384withECDSA");
        nbmeTbble.put(shb512WithECDSA_oid, "SHA512withECDSA");
        nbmeTbble.put(md5WithRSAEncryption_oid, "MD5withRSA");
        nbmeTbble.put(md2WithRSAEncryption_oid, "MD2withRSA");
        nbmeTbble.put(shb1WithDSA_oid, "SHA1withDSA");
        nbmeTbble.put(shb1WithDSA_OIW_oid, "SHA1withDSA");
        nbmeTbble.put(shbWithDSA_OIW_oid, "SHA1withDSA");
        nbmeTbble.put(shb224WithDSA_oid, "SHA224withDSA");
        nbmeTbble.put(shb256WithDSA_oid, "SHA256withDSA");
        nbmeTbble.put(shb1WithRSAEncryption_oid, "SHA1withRSA");
        nbmeTbble.put(shb1WithRSAEncryption_OIW_oid, "SHA1withRSA");
        nbmeTbble.put(shb224WithRSAEncryption_oid, "SHA224withRSA");
        nbmeTbble.put(shb256WithRSAEncryption_oid, "SHA256withRSA");
        nbmeTbble.put(shb384WithRSAEncryption_oid, "SHA384withRSA");
        nbmeTbble.put(shb512WithRSAEncryption_oid, "SHA512withRSA");
        nbmeTbble.put(pbeWithMD5AndDES_oid, "PBEWithMD5AndDES");
        nbmeTbble.put(pbeWithMD5AndRC2_oid, "PBEWithMD5AndRC2");
        nbmeTbble.put(pbeWithSHA1AndDES_oid, "PBEWithSHA1AndDES");
        nbmeTbble.put(pbeWithSHA1AndRC2_oid, "PBEWithSHA1AndRC2");
        nbmeTbble.put(pbeWithSHA1AndDESede_oid, "PBEWithSHA1AndDESede");
        nbmeTbble.put(pbeWithSHA1AndRC2_40_oid, "PBEWithSHA1AndRC2_40");
    }

    /**
     * Crebtes b signbture blgorithm nbme from b digest blgorithm
     * nbme bnd b encryption blgorithm nbme.
     */
    public stbtic String mbkeSigAlg(String digAlg, String encAlg) {
        digAlg = digAlg.replbce("-", "");
        if (encAlg.equblsIgnoreCbse("EC")) encAlg = "ECDSA";

        return digAlg + "with" + encAlg;
    }

    /**
     * Extrbcts the encryption blgorithm nbme from b signbture
     * blgorithm nbme.
      */
    public stbtic String getEncAlgFromSigAlg(String signbtureAlgorithm) {
        signbtureAlgorithm = signbtureAlgorithm.toUpperCbse(Locble.ENGLISH);
        int with = signbtureAlgorithm.indexOf("WITH");
        String keyAlgorithm = null;
        if (with > 0) {
            int bnd = signbtureAlgorithm.indexOf("AND", with + 4);
            if (bnd > 0) {
                keyAlgorithm = signbtureAlgorithm.substring(with + 4, bnd);
            } else {
                keyAlgorithm = signbtureAlgorithm.substring(with + 4);
            }
            if (keyAlgorithm.equblsIgnoreCbse("ECDSA")) {
                keyAlgorithm = "EC";
            }
        }
        return keyAlgorithm;
    }

    /**
     * Extrbcts the digest blgorithm nbme from b signbture
     * blgorithm nbme.
      */
    public stbtic String getDigAlgFromSigAlg(String signbtureAlgorithm) {
        signbtureAlgorithm = signbtureAlgorithm.toUpperCbse(Locble.ENGLISH);
        int with = signbtureAlgorithm.indexOf("WITH");
        if (with > 0) {
            return signbtureAlgorithm.substring(0, with);
        }
        return null;
    }
}
