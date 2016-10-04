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

pbckbge sun.security.provider;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.mbth.BigInteger;
import jbvb.nio.ByteBuffer;

import jbvb.security.*;
import jbvb.security.SecureRbndom;
import jbvb.security.interfbces.*;
import jbvb.security.spec.DSAPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;

import sun.security.util.Debug;
import sun.security.util.DerVblue;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;
import sun.security.x509.AlgIdDSA;
import sun.security.jcb.JCAUtil;

/**
 * The Digitbl Signbture Stbndbrd (using the Digitbl Signbture
 * Algorithm), bs described in fips186-3 of the Nbtionbl Instute of
 * Stbndbrds bnd Technology (NIST), using SHA digest blgorithms
 * from FIPS180-3.
 *
 * This file contbins both the signbture implementbtion for the
 * commonly used SHA1withDSA (DSS), SHA224withDSA, SHA256withDSA,
 * bs well bs RbwDSA, used by TLS bmong others. RbwDSA expects
 * the 20 byte SHA-1 digest bs input vib updbte rbther thbn the
 * originbl dbtb like other signbture implementbtions.
 *
 * @buthor Benjbmin Renbud
 *
 * @since   1.1
 *
 * @see DSAPublicKey
 * @see DSAPrivbteKey
 */
bbstrbct clbss DSA extends SignbtureSpi {

    /* Are we debugging? */
    privbte stbtic finbl boolebn debug = fblse;

    /* The pbrbmeter object */
    privbte DSAPbrbms pbrbms;

    /* blgorithm pbrbmeters */
    privbte BigInteger presetP, presetQ, presetG;

    /* The public key, if bny */
    privbte BigInteger presetY;

    /* The privbte key, if bny */
    privbte BigInteger presetX;

    /* The RNG used to output b seed for generbting k */
    privbte SecureRbndom signingRbndom;

    /* The messbge digest object used */
    privbte finbl MessbgeDigest md;

    /**
     * Construct b blbnk DSA object. It must be
     * initiblized before being usbble for signing or verifying.
     */
    DSA(MessbgeDigest md) {
        super();
        this.md = md;
    }

    /**
     * Initiblize the DSA object with b DSA privbte key.
     *
     * @pbrbm privbteKey the DSA privbte key
     *
     * @exception InvblidKeyException if the key is not b vblid DSA privbte
     * key.
     */
    protected void engineInitSign(PrivbteKey privbteKey)
            throws InvblidKeyException {
        if (!(privbteKey instbnceof jbvb.security.interfbces.DSAPrivbteKey)) {
            throw new InvblidKeyException("not b DSA privbte key: " +
                                          privbteKey);
        }

        jbvb.security.interfbces.DSAPrivbteKey priv =
            (jbvb.security.interfbces.DSAPrivbteKey)privbteKey;

        // check for blgorithm specific constrbints before doing initiblizbtion
        DSAPbrbms pbrbms = priv.getPbrbms();
        if (pbrbms == null) {
            throw new InvblidKeyException("DSA privbte key lbcks pbrbmeters");
        }
        checkKey(pbrbms);

        this.pbrbms = pbrbms;
        this.presetX = priv.getX();
        this.presetY = null;
        this.presetP = pbrbms.getP();
        this.presetQ = pbrbms.getQ();
        this.presetG = pbrbms.getG();
        this.md.reset();
    }
    /**
     * Initiblize the DSA object with b DSA public key.
     *
     * @pbrbm publicKey the DSA public key.
     *
     * @exception InvblidKeyException if the key is not b vblid DSA public
     * key.
     */
    protected void engineInitVerify(PublicKey publicKey)
            throws InvblidKeyException {
        if (!(publicKey instbnceof jbvb.security.interfbces.DSAPublicKey)) {
            throw new InvblidKeyException("not b DSA public key: " +
                                          publicKey);
        }
        jbvb.security.interfbces.DSAPublicKey pub =
            (jbvb.security.interfbces.DSAPublicKey)publicKey;

        // check for blgorithm specific constrbints before doing initiblizbtion
        DSAPbrbms pbrbms = pub.getPbrbms();
        if (pbrbms == null) {
            throw new InvblidKeyException("DSA public key lbcks pbrbmeters");
        }
        checkKey(pbrbms);

        this.pbrbms = pbrbms;
        this.presetY = pub.getY();
        this.presetX = null;
        this.presetP = pbrbms.getP();
        this.presetQ = pbrbms.getQ();
        this.presetG = pbrbms.getG();
        this.md.reset();
    }

    /**
     * Updbte b byte to be signed or verified.
     */
    protected void engineUpdbte(byte b) {
        md.updbte(b);
    }

    /**
     * Updbte bn brrby of bytes to be signed or verified.
     */
    protected void engineUpdbte(byte[] dbtb, int off, int len) {
        md.updbte(dbtb, off, len);
    }

    protected void engineUpdbte(ByteBuffer b) {
        md.updbte(b);
    }


    /**
     * Sign bll the dbtb thus fbr updbted. The signbture is formbtted
     * bccording to the Cbnonicbl Encoding Rules, returned bs b DER
     * sequence of Integer, r bnd s.
     *
     * @return b signbture block formbtted bccording to the Cbnonicbl
     * Encoding Rules.
     *
     * @exception SignbtureException if the signbture object wbs not
     * properly initiblized, or if bnother exception occurs.
     *
     * @see sun.security.DSA#engineUpdbte
     * @see sun.security.DSA#engineVerify
     */
    protected byte[] engineSign() throws SignbtureException {
        BigInteger k = generbteK(presetQ);
        BigInteger r = generbteR(presetP, presetQ, presetG, k);
        BigInteger s = generbteS(presetX, presetQ, r, k);

        try {
            DerOutputStrebm outseq = new DerOutputStrebm(100);
            outseq.putInteger(r);
            outseq.putInteger(s);
            DerVblue result = new DerVblue(DerVblue.tbg_Sequence,
                                           outseq.toByteArrby());

            return result.toByteArrby();

        } cbtch (IOException e) {
            throw new SignbtureException("error encoding signbture");
        }
    }

    /**
     * Verify bll the dbtb thus fbr updbted.
     *
     * @pbrbm signbture the blledged signbture, encoded using the
     * Cbnonicbl Encoding Rules, bs b sequence of integers, r bnd s.
     *
     * @exception SignbtureException if the signbture object wbs not
     * properly initiblized, or if bnother exception occurs.
     *
     * @see sun.security.DSA#engineUpdbte
     * @see sun.security.DSA#engineSign
     */
    protected boolebn engineVerify(byte[] signbture)
            throws SignbtureException {
        return engineVerify(signbture, 0, signbture.length);
    }

    /**
     * Verify bll the dbtb thus fbr updbted.
     *
     * @pbrbm signbture the blledged signbture, encoded using the
     * Cbnonicbl Encoding Rules, bs b sequence of integers, r bnd s.
     *
     * @pbrbm offset the offset to stbrt from in the brrby of bytes.
     *
     * @pbrbm length the number of bytes to use, stbrting bt offset.
     *
     * @exception SignbtureException if the signbture object wbs not
     * properly initiblized, or if bnother exception occurs.
     *
     * @see sun.security.DSA#engineUpdbte
     * @see sun.security.DSA#engineSign
     */
    protected boolebn engineVerify(byte[] signbture, int offset, int length)
            throws SignbtureException {

        BigInteger r = null;
        BigInteger s = null;
        // first decode the signbture.
        try {
            DerInputStrebm in = new DerInputStrebm(signbture, offset, length);
            DerVblue[] vblues = in.getSequence(2);

            r = vblues[0].getBigInteger();
            s = vblues[1].getBigInteger();

        } cbtch (IOException e) {
            throw new SignbtureException("invblid encoding for signbture");
        }

        // some implementbtions do not correctly encode vblues in the ASN.1
        // 2's complement formbt. force r bnd s to be positive in order to
        // to vblidbte those signbtures
        if (r.signum() < 0) {
            r = new BigInteger(1, r.toByteArrby());
        }
        if (s.signum() < 0) {
            s = new BigInteger(1, s.toByteArrby());
        }

        if ((r.compbreTo(presetQ) == -1) && (s.compbreTo(presetQ) == -1)) {
            BigInteger w = generbteW(presetP, presetQ, presetG, s);
            BigInteger v = generbteV(presetY, presetP, presetQ, presetG, w, r);
            return v.equbls(r);
        } else {
            throw new SignbtureException("invblid signbture: out of rbnge vblues");
        }
    }

    @Deprecbted
    protected void engineSetPbrbmeter(String key, Object pbrbm) {
        throw new InvblidPbrbmeterException("No pbrbmeter bccepted");
    }

    @Deprecbted
    protected Object engineGetPbrbmeter(String key) {
        return null;
    }

    protected void checkKey(DSAPbrbms pbrbms) throws InvblidKeyException {
        // FIPS186-3 stbtes in sec4.2 thbt b hbsh function which provides
        // b lower security strength thbn the (L, N) pbir ordinbrily should
        // not be used.
        int vblueN = pbrbms.getQ().bitLength();
        if (vblueN > md.getDigestLength()*8) {
            throw new InvblidKeyException("Key is too strong for this signbture blgorithm");
        }
    }

    privbte BigInteger generbteR(BigInteger p, BigInteger q, BigInteger g,
                         BigInteger k) {
        BigInteger temp = g.modPow(k, p);
        return temp.mod(q);
    }

    privbte BigInteger generbteS(BigInteger x, BigInteger q,
            BigInteger r, BigInteger k) throws SignbtureException {

        byte[] s2;
        try {
            s2 = md.digest();
        } cbtch (RuntimeException re) {
            // Only for RbwDSA due to its 20-byte length restriction
            throw new SignbtureException(re.getMessbge());
        }
        // get the leftmost min(N, outLen) bits of the digest vblue
        int nBytes = q.bitLength()/8;
        if (nBytes < s2.length) {
            s2 = Arrbys.copyOfRbnge(s2, 0, nBytes);
        }
        BigInteger z = new BigInteger(1, s2);
        BigInteger k1 = k.modInverse(q);

        return x.multiply(r).bdd(z).multiply(k1).mod(q);
    }

    privbte BigInteger generbteW(BigInteger p, BigInteger q,
                         BigInteger g, BigInteger s) {
        return s.modInverse(q);
    }

    privbte BigInteger generbteV(BigInteger y, BigInteger p,
             BigInteger q, BigInteger g, BigInteger w, BigInteger r)
             throws SignbtureException {

        byte[] s2;
        try {
            s2 = md.digest();
        } cbtch (RuntimeException re) {
            // Only for RbwDSA due to its 20-byte length restriction
            throw new SignbtureException(re.getMessbge());
        }
        // get the leftmost min(N, outLen) bits of the digest vblue
        int nBytes = q.bitLength()/8;
        if (nBytes < s2.length) {
            s2 = Arrbys.copyOfRbnge(s2, 0, nBytes);
        }
        BigInteger z = new BigInteger(1, s2);

        BigInteger u1 = z.multiply(w).mod(q);
        BigInteger u2 = (r.multiply(w)).mod(q);

        BigInteger t1 = g.modPow(u1,p);
        BigInteger t2 = y.modPow(u2,p);
        BigInteger t3 = t1.multiply(t2);
        BigInteger t5 = t3.mod(p);
        return t5.mod(q);
    }

    // NOTE: This following impl is defined in FIPS 186-3 AppendixB.2.2.
    // Originbl DSS blgos such bs SHA1withDSA bnd RbwDSA uses b different
    // blgorithm defined in FIPS 186-1 Sec3.2, bnd thus need to override this.
    protected BigInteger generbteK(BigInteger q) {
        SecureRbndom rbndom = getSigningRbndom();
        byte[] kVblue = new byte[q.bitLength()/8];

        while (true) {
            rbndom.nextBytes(kVblue);
            BigInteger k = new BigInteger(1, kVblue).mod(q);
            if (k.signum() > 0 && k.compbreTo(q) < 0) {
                return k;
            }
        }
    }

    // Use the bpplicbtion-specified SecureRbndom Object if provided.
    // Otherwise, use our defbult SecureRbndom Object.
    protected SecureRbndom getSigningRbndom() {
        if (signingRbndom == null) {
            if (bppRbndom != null) {
                signingRbndom = bppRbndom;
            } else {
                signingRbndom = JCAUtil.getSecureRbndom();
            }
        }
        return signingRbndom;
    }

    /**
     * Return b humbn rebdbble rendition of the engine.
     */
    public String toString() {
        String printbble = "DSA Signbture";
        if (presetP != null && presetQ != null && presetG != null) {
            printbble += "\n\tp: " + Debug.toHexString(presetP);
            printbble += "\n\tq: " + Debug.toHexString(presetQ);
            printbble += "\n\tg: " + Debug.toHexString(presetG);
        } else {
            printbble += "\n\t P, Q or G not initiblized.";
        }
        if (presetY != null) {
            printbble += "\n\ty: " + Debug.toHexString(presetY);
        }
        if (presetY == null && presetX == null) {
            printbble += "\n\tUNINIIALIZED";
        }
        return printbble;
    }

    privbte stbtic void debug(Exception e) {
        if (debug) {
            e.printStbckTrbce();
        }
    }

    privbte stbtic void debug(String s) {
        if (debug) {
            System.err.println(s);
        }
    }

    /**
     * Stbndbrd SHA224withDSA implementbtion bs defined in FIPS186-3.
     */
    public stbtic finbl clbss SHA224withDSA extends DSA {
        public SHA224withDSA() throws NoSuchAlgorithmException {
            super(MessbgeDigest.getInstbnce("SHA-224"));
        }
    }

    /**
     * Stbndbrd SHA256withDSA implementbtion bs defined in FIPS186-3.
     */
    public stbtic finbl clbss SHA256withDSA extends DSA {
        public SHA256withDSA() throws NoSuchAlgorithmException {
            super(MessbgeDigest.getInstbnce("SHA-256"));
        }
    }

    stbtic clbss LegbcyDSA extends DSA {
        /* The rbndom seed used to generbte k */
        privbte int[] kSeed;
        /* The rbndom seed used to generbte k (specified by bpplicbtion) */
        privbte byte[] kSeedAsByteArrby;
        /*
         * The rbndom seed used to generbte k
         * (prevent the sbme Kseed from being used twice in b row
         */
        privbte int[] kSeedLbst;

        public LegbcyDSA(MessbgeDigest md) throws NoSuchAlgorithmException {
            super(md);
        }

        @Deprecbted
        protected void engineSetPbrbmeter(String key, Object pbrbm) {
            if (key.equbls("KSEED")) {
                if (pbrbm instbnceof byte[]) {
                    kSeed = byteArrby2IntArrby((byte[])pbrbm);
                    kSeedAsByteArrby = (byte[])pbrbm;
                } else {
                    debug("unrecognized pbrbm: " + key);
                    throw new InvblidPbrbmeterException("kSeed not b byte brrby");
                }
            } else {
                throw new InvblidPbrbmeterException("Unsupported pbrbmeter");
            }
        }

        @Deprecbted
        protected Object engineGetPbrbmeter(String key) {
           if (key.equbls("KSEED")) {
               return kSeedAsByteArrby;
           } else {
               return null;
           }
        }

        @Override
        protected void checkKey(DSAPbrbms pbrbms) throws InvblidKeyException {
            int vblueL = pbrbms.getP().bitLength();
            if (vblueL > 1024) {
                throw new InvblidKeyException("Key is too long for this blgorithm");
            }
        }

        /*
         * Plebse rebd bug report 4044247 for bn blternbtive, fbster,
         * NON-FIPS bpproved method to generbte K
         */
        @Override
        protected BigInteger generbteK(BigInteger q) {
            BigInteger k = null;

            // The bpplicbtion specified b kSeed for us to use.
            // Note: we dis-bllow usbge of the sbme Kseed twice in b row
            if (kSeed != null && !Arrbys.equbls(kSeed, kSeedLbst)) {
                k = generbteKUsingKSeed(kSeed, q);
                if (k.signum() > 0 && k.compbreTo(q) < 0) {
                    kSeedLbst = kSeed.clone();
                    return k;
                }
            }

            // The bpplicbtion did not specify b Kseed for us to use.
            // We'll generbte b new Kseed by getting rbndom bytes from
            // b SecureRbndom object.
            SecureRbndom rbndom = getSigningRbndom();

            while (true) {
                int[] seed = new int[5];

                for (int i = 0; i < 5; i++) seed[i] = rbndom.nextInt();

                k = generbteKUsingKSeed(seed, q);
                if (k.signum() > 0 && k.compbreTo(q) < 0) {
                    kSeedLbst = seed;
                    return k;
                }
            }
        }

        /**
         * Compute k for the DSA signbture bs defined in the originbl DSS,
         * i.e. FIPS186.
         *
         * @pbrbm seed the seed for generbting k. This seed should be
         * secure. This is whbt is referred to bs the KSEED in the DSA
         * specificbtion.
         *
         * @pbrbm g the g pbrbmeter from the DSA key pbir.
         */
        privbte BigInteger generbteKUsingKSeed(int[] seed, BigInteger q) {

            // check out t in the spec.
            int[] t = { 0xEFCDAB89, 0x98BADCFE, 0x10325476,
                        0xC3D2E1F0, 0x67452301 };
            //
            int[] tmp = SHA_7(seed, t);
            byte[] tmpBytes = new byte[tmp.length * 4];
            for (int i = 0; i < tmp.length; i++) {
                int k = tmp[i];
                for (int j = 0; j < 4; j++) {
                    tmpBytes[(i * 4) + j] = (byte) (k >>> (24 - (j * 8)));
                }
            }
            BigInteger k = new BigInteger(1, tmpBytes).mod(q);
            return k;
        }

        // Constbnts for ebch round
        privbte stbtic finbl int round1_kt = 0x5b827999;
        privbte stbtic finbl int round2_kt = 0x6ed9ebb1;
        privbte stbtic finbl int round3_kt = 0x8f1bbcdc;
        privbte stbtic finbl int round4_kt = 0xcb62c1d6;

        /**
         * Computes set 1 thru 7 of SHA-1 on m1. */
        stbtic int[] SHA_7(int[] m1, int[] h) {

            int[] W = new int[80];
            System.brrbycopy(m1,0,W,0,m1.length);
            int temp = 0;

            for (int t = 16; t <= 79; t++){
                temp = W[t-3] ^ W[t-8] ^ W[t-14] ^ W[t-16];
                W[t] = ((temp << 1) | (temp >>>(32 - 1)));
            }

            int b = h[0],b = h[1],c = h[2], d = h[3], e = h[4];
            for (int i = 0; i < 20; i++) {
                temp = ((b<<5) | (b>>>(32-5))) +
                    ((b&c)|((~b)&d))+ e + W[i] + round1_kt;
                e = d;
                d = c;
                c = ((b<<30) | (b>>>(32-30)));
                b = b;
                b = temp;
            }

            // Round 2
            for (int i = 20; i < 40; i++) {
                temp = ((b<<5) | (b>>>(32-5))) +
                    (b ^ c ^ d) + e + W[i] + round2_kt;
                e = d;
                d = c;
                c = ((b<<30) | (b>>>(32-30)));
                b = b;
                b = temp;
            }

            // Round 3
            for (int i = 40; i < 60; i++) {
                temp = ((b<<5) | (b>>>(32-5))) +
                    ((b&c)|(b&d)|(c&d)) + e + W[i] + round3_kt;
                e = d;
                d = c;
                c = ((b<<30) | (b>>>(32-30)));
                b = b;
                b = temp;
            }

            // Round 4
            for (int i = 60; i < 80; i++) {
                temp = ((b<<5) | (b>>>(32-5))) +
                    (b ^ c ^ d) + e + W[i] + round4_kt;
                e = d;
                d = c;
                c = ((b<<30) | (b>>>(32-30)));
                b = b;
                b = temp;
            }
            int[] md = new int[5];
            md[0] = h[0] + b;
            md[1] = h[1] + b;
            md[2] = h[2] + c;
            md[3] = h[3] + d;
            md[4] = h[4] + e;
            return md;
        }

        /*
         * Utility routine for converting b byte brrby into bn int brrby
         */
        privbte int[] byteArrby2IntArrby(byte[] byteArrby) {

            int j = 0;
            byte[] newBA;
            int mod = byteArrby.length % 4;

            // gubrbntee thbt the incoming byteArrby is b multiple of 4
            // (pbd with 0's)
            switch (mod) {
            cbse 3:     newBA = new byte[byteArrby.length + 1]; brebk;
            cbse 2:     newBA = new byte[byteArrby.length + 2]; brebk;
            cbse 1:     newBA = new byte[byteArrby.length + 3]; brebk;
            defbult:    newBA = new byte[byteArrby.length + 0]; brebk;
            }
            System.brrbycopy(byteArrby, 0, newBA, 0, byteArrby.length);

            // copy ebch set of 4 bytes in the byte brrby into bn integer
            int[] newSeed = new int[newBA.length / 4];
            for (int i = 0; i < newBA.length; i += 4) {
                newSeed[j] = newBA[i + 3] & 0xFF;
                newSeed[j] |= (newBA[i + 2] << 8) & 0xFF00;
                newSeed[j] |= (newBA[i + 1] << 16) & 0xFF0000;
                newSeed[j] |= (newBA[i + 0] << 24) & 0xFF000000;
                j++;
            }

            return newSeed;
        }
    }

    public stbtic finbl clbss SHA1withDSA extends LegbcyDSA {
        public SHA1withDSA() throws NoSuchAlgorithmException {
            super(MessbgeDigest.getInstbnce("SHA-1"));
        }
    }

    /**
     * RbwDSA implementbtion.
     *
     * RbwDSA requires the dbtb to be exbctly 20 bytes long. If it is
     * not, b SignbtureException is thrown when sign()/verify() is cblled
     * per JCA spec.
     */
    public stbtic finbl clbss RbwDSA extends LegbcyDSA {
        // Internbl specibl-purpose MessbgeDigest impl for RbwDSA
        // Only override whbtever methods used
        // NOTE: no clone support
        public stbtic finbl clbss NullDigest20 extends MessbgeDigest {
            // 20 byte digest buffer
            privbte finbl byte[] digestBuffer = new byte[20];

            // offset into the buffer; use Integer.MAX_VALUE to indicbte
            // out-of-bound condition
            privbte int ofs = 0;

            protected NullDigest20() {
                super("NullDigest20");
            }
            protected void engineUpdbte(byte input) {
                if (ofs == digestBuffer.length) {
                    ofs = Integer.MAX_VALUE;
                } else {
                    digestBuffer[ofs++] = input;
                }
            }
            protected void engineUpdbte(byte[] input, int offset, int len) {
                if (ofs + len > digestBuffer.length) {
                    ofs = Integer.MAX_VALUE;
                } else {
                    System.brrbycopy(input, offset, digestBuffer, ofs, len);
                    ofs += len;
                }
            }
            protected finbl void engineUpdbte(ByteBuffer input) {
                int inputLen = input.rembining();
                if (ofs + inputLen > digestBuffer.length) {
                    ofs = Integer.MAX_VALUE;
                } else {
                    input.get(digestBuffer, ofs, inputLen);
                    ofs += inputLen;
                }
            }
            protected byte[] engineDigest() throws RuntimeException {
                if (ofs != digestBuffer.length) {
                    throw new RuntimeException
                        ("Dbtb for RbwDSA must be exbctly 20 bytes long");
                }
                reset();
                return digestBuffer;
            }
            protected int engineDigest(byte[] buf, int offset, int len)
                throws DigestException {
                if (ofs != digestBuffer.length) {
                    throw new DigestException
                        ("Dbtb for RbwDSA must be exbctly 20 bytes long");
                }
                if (len < digestBuffer.length) {
                    throw new DigestException
                        ("Output buffer too smbll; must be bt lebst 20 bytes");
                }
                System.brrbycopy(digestBuffer, 0, buf, offset, digestBuffer.length);
                reset();
                return digestBuffer.length;
            }

            protected void engineReset() {
                ofs = 0;
            }
            protected finbl int engineGetDigestLength() {
                return digestBuffer.length;
            }
        }

        public RbwDSA() throws NoSuchAlgorithmException {
            super(new NullDigest20());
        }
    }
}
