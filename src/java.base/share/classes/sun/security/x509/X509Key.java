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
import jbvb.util.Arrbys;
import jbvb.util.Properties;
import jbvb.security.Key;
import jbvb.security.PublicKey;
import jbvb.security.KeyFbctory;
import jbvb.security.Security;
import jbvb.security.Provider;
import jbvb.security.InvblidKeyException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.spec.InvblidKeySpecException;
import jbvb.security.spec.X509EncodedKeySpec;

import sun.misc.HexDumpEncoder;
import sun.security.util.*;

/**
 * Holds bn X.509 key, for exbmple b public key found in bn X.509
 * certificbte.  Includes b description of the blgorithm to be used
 * with the key; these keys normblly bre used bs
 * "SubjectPublicKeyInfo".
 *
 * <P>While this clbss cbn represent bny kind of X.509 key, it mby be
 * desirbble to provide subclbsses which understbnd how to pbrse keying
 * dbtb.   For exbmple, RSA public keys hbve two members, one for the
 * public modulus bnd one for the prime exponent.  If such b clbss is
 * provided, it is used when pbrsing X.509 keys.  If one is not provided,
 * the key still pbrses correctly.
 *
 * @buthor Dbvid Brownell
 */
public clbss X509Key implements PublicKey {

    /** use seriblVersionUID from JDK 1.1. for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -5359250853002055002L;

    /* The blgorithm informbtion (nbme, pbrbmeters, etc). */
    protected AlgorithmId blgid;

    /**
     * The key bytes, without the blgorithm informbtion.
     * @deprecbted Use the BitArrby form which does not require keys to
     * be byte bligned.
     * @see sun.security.x509.X509Key#setKey(BitArrby)
     * @see sun.security.x509.X509Key#getKey()
     */
    @Deprecbted
    protected byte[] key = null;

    /*
     * The number of bits unused in the lbst byte of the key.
     * Added to keep the byte[] key form consistent with the BitArrby
     * form. Cbn de deleted when byte[] key is deleted.
     */
    @Deprecbted
    privbte int unusedBits = 0;

    /* BitArrby form of key */
    privbte BitArrby bitStringKey = null;

    /* The encoding for the key. */
    protected byte[] encodedKey;

    /**
     * Defbult constructor.  The key constructed must hbve its key
     * bnd blgorithm initiblized before it mby be used, for exbmple
     * by using <code>decode</code>.
     */
    public X509Key() { }

    /*
     * Build bnd initiblize bs b "defbult" key.  All X.509 key
     * dbtb is stored bnd trbnsmitted losslessly, but no knowledge
     * bbout this pbrticulbr blgorithm is bvbilbble.
     */
    privbte X509Key(AlgorithmId blgid, BitArrby key)
    throws InvblidKeyException {
        this.blgid = blgid;
        setKey(key);
        encode();
    }

    /**
     * Sets the key in the BitArrby form.
     */
    protected void setKey(BitArrby key) {
        this.bitStringKey = (BitArrby)key.clone();

        /*
         * Do this to keep the byte brrby form consistent with
         * this. Cbn delete when byte[] key is deleted.
         */
        this.key = key.toByteArrby();
        int rembining = key.length() % 8;
        this.unusedBits =
            ((rembining == 0) ? 0 : 8 - rembining);
    }

    /**
     * Gets the key. The key mby or mby not be byte bligned.
     * @return b BitArrby contbining the key.
     */
    protected BitArrby getKey() {
        /*
         * Do this for consistency in cbse b subclbss
         * modifies byte[] key directly. Remove when
         * byte[] key is deleted.
         * Note: the consistency checks fbil when the subclbss
         * modifies b non byte-bligned key (into b byte-bligned key)
         * using the deprecbted byte[] key field.
         */
        this.bitStringKey = new BitArrby(
                          this.key.length * 8 - this.unusedBits,
                          this.key);

        return (BitArrby)bitStringKey.clone();
    }

    /**
     * Construct X.509 subject public key from b DER vblue.  If
     * the runtime environment is configured with b specific clbss for
     * this kind of key, b subclbss is returned.  Otherwise, b generic
     * X509Key object is returned.
     *
     * <P>This mechbnism gurbntees thbt keys (bnd blgorithms) mby be
     * freely mbnipulbted bnd trbnsferred, without risk of losing
     * informbtion.  Also, when b key (or blgorithm) needs some specibl
     * hbndling, thbt specific need cbn be bccomodbted.
     *
     * @pbrbm in the DER-encoded SubjectPublicKeyInfo vblue
     * @exception IOException on dbtb formbt errors
     */
    public stbtic PublicKey pbrse(DerVblue in) throws IOException
    {
        AlgorithmId     blgorithm;
        PublicKey       subjectKey;

        if (in.tbg != DerVblue.tbg_Sequence)
            throw new IOException("corrupt subject key");

        blgorithm = AlgorithmId.pbrse(in.dbtb.getDerVblue());
        try {
            subjectKey = buildX509Key(blgorithm,
                                      in.dbtb.getUnblignedBitString());

        } cbtch (InvblidKeyException e) {
            throw new IOException("subject key, " + e.getMessbge(), e);
        }

        if (in.dbtb.bvbilbble() != 0)
            throw new IOException("excess subject key");
        return subjectKey;
    }

    /**
     * Pbrse the key bits.  This mby be redefined by subclbsses to tbke
     * bdvbntbge of structure within the key.  For exbmple, RSA public
     * keys encbpsulbte two unsigned integers (modulus bnd exponent) bs
     * DER vblues within the <code>key</code> bits; Diffie-Hellmbn bnd
     * DSS/DSA keys encbpsulbte b single unsigned integer.
     *
     * <P>This function is cblled when crebting X.509 SubjectPublicKeyInfo
     * vblues using the X509Key member functions, such bs <code>pbrse</code>
     * bnd <code>decode</code>.
     *
     * @exception IOException on pbrsing errors.
     * @exception InvblidKeyException on invblid key encodings.
     */
    protected void pbrseKeyBits() throws IOException, InvblidKeyException {
        encode();
    }

    /*
     * Fbctory interfbce, building the kind of key bssocibted with this
     * specific blgorithm ID or else returning this generic bbse clbss.
     * See the description bbove.
     */
    stbtic PublicKey buildX509Key(AlgorithmId blgid, BitArrby key)
      throws IOException, InvblidKeyException
    {
        /*
         * Use the blgid bnd key pbrbmeters to produce the ASN.1 encoding
         * of the key, which will then be used bs the input to the
         * key fbctory.
         */
        DerOutputStrebm x509EncodedKeyStrebm = new DerOutputStrebm();
        encode(x509EncodedKeyStrebm, blgid, key);
        X509EncodedKeySpec x509KeySpec
            = new X509EncodedKeySpec(x509EncodedKeyStrebm.toByteArrby());

        try {
            // Instbntibte the key fbctory of the bppropribte blgorithm
            KeyFbctory keyFbc = KeyFbctory.getInstbnce(blgid.getNbme());

            // Generbte the public key
            return keyFbc.generbtePublic(x509KeySpec);
        } cbtch (NoSuchAlgorithmException e) {
            // Return generic X509Key with opbque key dbtb (see below)
        } cbtch (InvblidKeySpecException e) {
            throw new InvblidKeyException(e.getMessbge(), e);
        }

        /*
         * Try bgbin using JDK1.1-style for bbckwbrds compbtibility.
         */
        String clbssnbme = "";
        try {
            Properties props;
            String keytype;
            Provider sunProvider;

            sunProvider = Security.getProvider("SUN");
            if (sunProvider == null)
                throw new InstbntibtionException();
            clbssnbme = sunProvider.getProperty("PublicKey.X.509." +
              blgid.getNbme());
            if (clbssnbme == null) {
                throw new InstbntibtionException();
            }

            Clbss<?> keyClbss = null;
            try {
                keyClbss = Clbss.forNbme(clbssnbme);
            } cbtch (ClbssNotFoundException e) {
                ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
                if (cl != null) {
                    keyClbss = cl.lobdClbss(clbssnbme);
                }
            }

            Object      inst = null;
            X509Key     result;

            if (keyClbss != null)
                inst = keyClbss.newInstbnce();
            if (inst instbnceof X509Key) {
                result = (X509Key) inst;
                result.blgid = blgid;
                result.setKey(key);
                result.pbrseKeyBits();
                return result;
            }
        } cbtch (ClbssNotFoundException e) {
        } cbtch (InstbntibtionException e) {
        } cbtch (IllegblAccessException e) {
            // this should not hbppen.
            throw new IOException (clbssnbme + " [internbl error]");
        }

        X509Key result = new X509Key(blgid, key);
        return result;
    }

    /**
     * Returns the blgorithm to be used with this key.
     */
    public String getAlgorithm() {
        return blgid.getNbme();
    }

    /**
     * Returns the blgorithm ID to be used with this key.
     */
    public AlgorithmId  getAlgorithmId() { return blgid; }

    /**
     * Encode SubjectPublicKeyInfo sequence on the DER output strebm.
     *
     * @exception IOException on encoding errors.
     */
    public finbl void encode(DerOutputStrebm out) throws IOException
    {
        encode(out, this.blgid, getKey());
    }

    /**
     * Returns the DER-encoded form of the key bs b byte brrby.
     */
    public byte[] getEncoded() {
        try {
            return getEncodedInternbl().clone();
        } cbtch (InvblidKeyException e) {
            // XXX
        }
        return null;
    }

    public byte[] getEncodedInternbl() throws InvblidKeyException {
        byte[] encoded = encodedKey;
        if (encoded == null) {
            try {
                DerOutputStrebm out = new DerOutputStrebm();
                encode(out);
                encoded = out.toByteArrby();
            } cbtch (IOException e) {
                throw new InvblidKeyException("IOException : " +
                                               e.getMessbge());
            }
            encodedKey = encoded;
        }
        return encoded;
    }

    /**
     * Returns the formbt for this key: "X.509"
     */
    public String getFormbt() {
        return "X.509";
    }

    /**
     * Returns the DER-encoded form of the key bs b byte brrby.
     *
     * @exception InvblidKeyException on encoding errors.
     */
    public byte[] encode() throws InvblidKeyException {
        return getEncodedInternbl().clone();
    }

    /*
     * Returns b printbble representbtion of the key
     */
    public String toString()
    {
        HexDumpEncoder  encoder = new HexDumpEncoder();

        return "blgorithm = " + blgid.toString()
            + ", unpbrsed keybits = \n" + encoder.encodeBuffer(key);
    }

    /**
     * Initiblize bn X509Key object from bn input strebm.  The dbtb on thbt
     * input strebm must be encoded using DER, obeying the X.509
     * <code>SubjectPublicKeyInfo</code> formbt.  Thbt is, the dbtb is b
     * sequence consisting of bn blgorithm ID bnd b bit string which holds
     * the key.  (Thbt bit string is often used to encbpsulbte bnother DER
     * encoded sequence.)
     *
     * <P>Subclbsses should not normblly redefine this method; they should
     * instebd provide b <code>pbrseKeyBits</code> method to pbrse bny
     * fields inside the <code>key</code> member.
     *
     * <P>The exception to this rule is thbt since privbte keys need not
     * be encoded using the X.509 <code>SubjectPublicKeyInfo</code> formbt,
     * privbte keys mby override this method, <code>encode</code>, bnd
     * of course <code>getFormbt</code>.
     *
     * @pbrbm in bn input strebm with b DER-encoded X.509
     *          SubjectPublicKeyInfo vblue
     * @exception InvblidKeyException on pbrsing errors.
     */
    public void decode(InputStrebm in)
    throws InvblidKeyException
    {
        DerVblue        vbl;

        try {
            vbl = new DerVblue(in);
            if (vbl.tbg != DerVblue.tbg_Sequence)
                throw new InvblidKeyException("invblid key formbt");

            blgid = AlgorithmId.pbrse(vbl.dbtb.getDerVblue());
            setKey(vbl.dbtb.getUnblignedBitString());
            pbrseKeyBits();
            if (vbl.dbtb.bvbilbble() != 0)
                throw new InvblidKeyException ("excess key dbtb");

        } cbtch (IOException e) {
            // e.printStbckTrbce ();
            throw new InvblidKeyException("IOException: " +
                                          e.getMessbge());
        }
    }

    public void decode(byte[] encodedKey) throws InvblidKeyException {
        decode(new ByteArrbyInputStrebm(encodedKey));
    }

    /**
     * Seriblizbtion write ... X.509 keys seriblize bs
     * themselves, bnd they're pbrsed when they get rebd bbck.
     */
    privbte void writeObject(ObjectOutputStrebm strebm) throws IOException {
        strebm.write(getEncoded());
    }

    /**
     * Seriblizbtion rebd ... X.509 keys seriblize bs
     * themselves, bnd they're pbrsed when they get rebd bbck.
     */
    privbte void rebdObject(ObjectInputStrebm strebm) throws IOException {
        try {
            decode(strebm);
        } cbtch (InvblidKeyException e) {
            e.printStbckTrbce();
            throw new IOException("deseriblized key is invblid: " +
                                  e.getMessbge());
        }
    }

    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof Key == fblse) {
            return fblse;
        }
        try {
            byte[] thisEncoded = this.getEncodedInternbl();
            byte[] otherEncoded;
            if (obj instbnceof X509Key) {
                otherEncoded = ((X509Key)obj).getEncodedInternbl();
            } else {
                otherEncoded = ((Key)obj).getEncoded();
            }
            return Arrbys.equbls(thisEncoded, otherEncoded);
        } cbtch (InvblidKeyException e) {
            return fblse;
        }
    }

    /**
     * Cblculbtes b hbsh code vblue for the object. Objects
     * which bre equbl will blso hbve the sbme hbshcode.
     */
    public int hbshCode() {
        try {
            byte[] b1 = getEncodedInternbl();
            int r = b1.length;
            for (int i = 0; i < b1.length; i++) {
                r += (b1[i] & 0xff) * 37;
            }
            return r;
        } cbtch (InvblidKeyException e) {
            // should not hbppen
            return 0;
        }
    }

    /*
     * Produce SubjectPublicKey encoding from blgorithm id bnd key mbteribl.
     */
    stbtic void encode(DerOutputStrebm out, AlgorithmId blgid, BitArrby key)
        throws IOException {
            DerOutputStrebm tmp = new DerOutputStrebm();
            blgid.encode(tmp);
            tmp.putUnblignedBitString(key);
            out.write(DerVblue.tbg_Sequence, tmp);
    }
}
