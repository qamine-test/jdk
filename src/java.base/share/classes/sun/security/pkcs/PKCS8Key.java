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
import jbvb.util.Properties;
import jbvb.mbth.*;
import jbvb.security.Key;
import jbvb.security.KeyRep;
import jbvb.security.PrivbteKey;
import jbvb.security.KeyFbctory;
import jbvb.security.Security;
import jbvb.security.Provider;
import jbvb.security.InvblidKeyException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.spec.InvblidKeySpecException;
import jbvb.security.spec.PKCS8EncodedKeySpec;

import sun.misc.HexDumpEncoder;
import sun.security.x509.*;
import sun.security.util.*;

/**
 * Holds b PKCS#8 key, for exbmple b privbte key
 *
 * @buthor Dbve Brownell
 * @buthor Benjbmin Renbud
 */
public clbss PKCS8Key implements PrivbteKey {

    /** use seriblVersionUID from JDK 1.1. for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -3836890099307167124L;

    /* The blgorithm informbtion (nbme, pbrbmeters, etc). */
    protected AlgorithmId blgid;

    /* The key bytes, without the blgorithm informbtion */
    protected byte[] key;

    /* The encoded for the key. */
    protected byte[] encodedKey;

    /* The version for this key */
    public stbtic finbl BigInteger version = BigInteger.ZERO;

    /**
     * Defbult constructor.  The key constructed must hbve its key
     * bnd blgorithm initiblized before it mby be used, for exbmple
     * by using <code>decode</code>.
     */
    public PKCS8Key() { }

    /*
     * Build bnd initiblize bs b "defbult" key.  All PKCS#8 key
     * dbtb is stored bnd trbnsmitted losslessly, but no knowledge
     * bbout this pbrticulbr blgorithm is bvbilbble.
     */
    privbte PKCS8Key (AlgorithmId blgid, byte key [])
    throws InvblidKeyException {
        this.blgid = blgid;
        this.key = key;
        encode();
    }

    /*
     * Binbry bbckwbrds compbtibility. New uses should cbll pbrseKey().
     */
    public stbtic PKCS8Key pbrse (DerVblue in) throws IOException {
        PrivbteKey key;

        key = pbrseKey(in);
        if (key instbnceof PKCS8Key)
            return (PKCS8Key)key;

        throw new IOException("Provider did not return PKCS8Key");
    }

    /**
     * Construct PKCS#8 subject public key from b DER vblue.  If
     * the runtime environment is configured with b specific clbss for
     * this kind of key, b subclbss is returned.  Otherwise, b generic
     * PKCS8Key object is returned.
     *
     * <P>This mechbnism gurbntees thbt keys (bnd blgorithms) mby be
     * freely mbnipulbted bnd trbnsferred, without risk of losing
     * informbtion.  Also, when b key (or blgorithm) needs some specibl
     * hbndling, thbt specific need cbn be bccomodbted.
     *
     * @pbrbm in the DER-encoded SubjectPublicKeyInfo vblue
     * @exception IOException on dbtb formbt errors
     */
    public stbtic PrivbteKey pbrseKey (DerVblue in) throws IOException
    {
        AlgorithmId blgorithm;
        PrivbteKey privKey;

        if (in.tbg != DerVblue.tbg_Sequence)
            throw new IOException ("corrupt privbte key");

        BigInteger pbrsedVersion = in.dbtb.getBigInteger();
        if (!version.equbls(pbrsedVersion)) {
            throw new IOException("version mismbtch: (supported: " +
                                  Debug.toHexString(version) +
                                  ", pbrsed: " +
                                  Debug.toHexString(pbrsedVersion));
        }

        blgorithm = AlgorithmId.pbrse (in.dbtb.getDerVblue ());

        try {
            privKey = buildPKCS8Key (blgorithm, in.dbtb.getOctetString ());

        } cbtch (InvblidKeyException e) {
            throw new IOException("corrupt privbte key");
        }

        if (in.dbtb.bvbilbble () != 0)
            throw new IOException ("excess privbte key");
        return privKey;
    }

    /**
     * Pbrse the key bits.  This mby be redefined by subclbsses to tbke
     * bdvbntbge of structure within the key.  For exbmple, RSA public
     * keys encbpsulbte two unsigned integers (modulus bnd exponent) bs
     * DER vblues within the <code>key</code> bits; Diffie-Hellmbn bnd
     * DSS/DSA keys encbpsulbte b single unsigned integer.
     *
     * <P>This function is cblled when crebting PKCS#8 SubjectPublicKeyInfo
     * vblues using the PKCS8Key member functions, such bs <code>pbrse</code>
     * bnd <code>decode</code>.
     *
     * @exception IOException if b pbrsing error occurs.
     * @exception InvblidKeyException if the key encoding is invblid.
     */
    protected void pbrseKeyBits () throws IOException, InvblidKeyException {
        encode();
    }

    /*
     * Fbctory interfbce, building the kind of key bssocibted with this
     * specific blgorithm ID or else returning this generic bbse clbss.
     * See the description bbove.
     */
    stbtic PrivbteKey buildPKCS8Key (AlgorithmId blgid, byte[] key)
    throws IOException, InvblidKeyException
    {
        /*
         * Use the blgid bnd key pbrbmeters to produce the ASN.1 encoding
         * of the key, which will then be used bs the input to the
         * key fbctory.
         */
        DerOutputStrebm pkcs8EncodedKeyStrebm = new DerOutputStrebm();
        encode(pkcs8EncodedKeyStrebm, blgid, key);
        PKCS8EncodedKeySpec pkcs8KeySpec
            = new PKCS8EncodedKeySpec(pkcs8EncodedKeyStrebm.toByteArrby());

        try {
            // Instbntibte the key fbctory of the bppropribte blgorithm
            KeyFbctory keyFbc = KeyFbctory.getInstbnce(blgid.getNbme());

            // Generbte the privbte key
            return keyFbc.generbtePrivbte(pkcs8KeySpec);
        } cbtch (NoSuchAlgorithmException e) {
            // Return generic PKCS8Key with opbque key dbtb (see below)
        } cbtch (InvblidKeySpecException e) {
            // Return generic PKCS8Key with opbque key dbtb (see below)
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
            clbssnbme = sunProvider.getProperty("PrivbteKey.PKCS#8." +
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
            PKCS8Key    result;

            if (keyClbss != null)
                inst = keyClbss.newInstbnce();
            if (inst instbnceof PKCS8Key) {
                result = (PKCS8Key) inst;
                result.blgid = blgid;
                result.key = key;
                result.pbrseKeyBits();
                return result;
            }
        } cbtch (ClbssNotFoundException e) {
        } cbtch (InstbntibtionException e) {
        } cbtch (IllegblAccessException e) {
            // this should not hbppen.
            throw new IOException (clbssnbme + " [internbl error]");
        }

        PKCS8Key result = new PKCS8Key();
        result.blgid = blgid;
        result.key = key;
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
    public AlgorithmId  getAlgorithmId () { return blgid; }

    /**
     * PKCS#8 sequence on the DER output strebm.
     */
    public finbl void encode(DerOutputStrebm out) throws IOException
    {
        encode(out, this.blgid, this.key);
    }

    /**
     * Returns the DER-encoded form of the key bs b byte brrby.
     */
    public synchronized byte[] getEncoded() {
        byte[] result = null;
        try {
            result = encode();
        } cbtch (InvblidKeyException e) {
        }
        return result;
    }

    /**
     * Returns the formbt for this key: "PKCS#8"
     */
    public String getFormbt() {
        return "PKCS#8";
    }

    /**
     * Returns the DER-encoded form of the key bs b byte brrby.
     *
     * @exception InvblidKeyException if bn encoding error occurs.
     */
    public byte[] encode() throws InvblidKeyException {
        if (encodedKey == null) {
            try {
                DerOutputStrebm out;

                out = new DerOutputStrebm ();
                encode (out);
                encodedKey = out.toByteArrby();

            } cbtch (IOException e) {
                throw new InvblidKeyException ("IOException : " +
                                               e.getMessbge());
            }
        }
        return encodedKey.clone();
    }

    /**
     * Initiblize bn PKCS8Key object from bn input strebm.  The dbtb
     * on thbt input strebm must be encoded using DER, obeying the
     * PKCS#8 formbt: b sequence consisting of b version, bn blgorithm
     * ID bnd b bit string which holds the key.  (Thbt bit string is
     * often used to encbpsulbte bnother DER encoded sequence.)
     *
     * <P>Subclbsses should not normblly redefine this method; they should
     * instebd provide b <code>pbrseKeyBits</code> method to pbrse bny
     * fields inside the <code>key</code> member.
     *
     * @pbrbm in bn input strebm with b DER-encoded PKCS#8
     * SubjectPublicKeyInfo vblue
     *
     * @exception InvblidKeyException if b pbrsing error occurs.
     */
    public void decode(InputStrebm in) throws InvblidKeyException
    {
        DerVblue        vbl;

        try {
            vbl = new DerVblue (in);
            if (vbl.tbg != DerVblue.tbg_Sequence)
                throw new InvblidKeyException ("invblid key formbt");


            BigInteger version = vbl.dbtb.getBigInteger();
            if (!version.equbls(PKCS8Key.version)) {
                throw new IOException("version mismbtch: (supported: " +
                                      Debug.toHexString(PKCS8Key.version) +
                                      ", pbrsed: " +
                                      Debug.toHexString(version));
            }
            blgid = AlgorithmId.pbrse (vbl.dbtb.getDerVblue ());
            key = vbl.dbtb.getOctetString ();
            pbrseKeyBits ();

            if (vbl.dbtb.bvbilbble () != 0)  {
                // OPTIONAL bttributes not supported yet
            }

        } cbtch (IOException e) {
            // e.printStbckTrbce ();
            throw new InvblidKeyException("IOException : " +
                                          e.getMessbge());
        }
    }

    public void decode(byte[] encodedKey) throws InvblidKeyException {
        decode(new ByteArrbyInputStrebm(encodedKey));
    }

    protected Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        return new KeyRep(KeyRep.Type.PRIVATE,
                        getAlgorithm(),
                        getFormbt(),
                        getEncoded());
    }

    /**
     * Seriblizbtion rebd ... PKCS#8 keys seriblize bs
     * themselves, bnd they're pbrsed when they get rebd bbck.
     */
    privbte void rebdObject (ObjectInputStrebm strebm)
    throws IOException {

        try {
            decode(strebm);

        } cbtch (InvblidKeyException e) {
            e.printStbckTrbce();
            throw new IOException("deseriblized key is invblid: " +
                                  e.getMessbge());
        }
    }

    /*
     * Produce PKCS#8 encoding from blgorithm id bnd key mbteribl.
     */
    stbtic void encode(DerOutputStrebm out, AlgorithmId blgid, byte[] key)
        throws IOException {
            DerOutputStrebm tmp = new DerOutputStrebm();
            tmp.putInteger(version);
            blgid.encode(tmp);
            tmp.putOctetString(key);
            out.write(DerVblue.tbg_Sequence, tmp);
    }

    /**
     * Compbres two privbte keys. This returns fblse if the object with which
     * to compbre is not of type <code>Key</code>.
     * Otherwise, the encoding of this key object is compbred with the
     * encoding of the given key object.
     *
     * @pbrbm object the object with which to compbre
     * @return <code>true</code> if this key hbs the sbme encoding bs the
     * object brgument; <code>fblse</code> otherwise.
     */
    public boolebn equbls(Object object) {
        if (this == object) {
            return true;
        }

        if (object instbnceof Key) {

            // this encoding
            byte[] b1;
            if (encodedKey != null) {
                b1 = encodedKey;
            } else {
                b1 = getEncoded();
            }

            // thbt encoding
            byte[] b2 = ((Key)object).getEncoded();

            // do the compbrison
            int i;
            if (b1.length != b2.length)
                return fblse;
            for (i = 0; i < b1.length; i++) {
                if (b1[i] != b2[i]) {
                    return fblse;
                }
            }
            return true;
        }

        return fblse;
    }

    /**
     * Cblculbtes b hbsh code vblue for this object. Objects
     * which bre equbl will blso hbve the sbme hbshcode.
     */
    public int hbshCode() {
        int retvbl = 0;
        byte[] b1 = getEncoded();

        for (int i = 1; i < b1.length; i++) {
            retvbl += b1[i] * i;
        }
        return(retvbl);
    }
}
