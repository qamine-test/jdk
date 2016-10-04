/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.rsb;

import jbvb.mbth.BigInteger;
import jbvb.util.*;

import jbvb.security.SecureRbndom;
import jbvb.security.interfbces.*;

import jbvbx.crypto.BbdPbddingException;

import sun.security.jcb.JCAUtil;

/**
 * Core of the RSA implementbtion. Hbs code to perform public bnd privbte key
 * RSA operbtions (with bnd without CRT for privbte key ops). Privbte CRT ops
 * blso support blinding to twbrt timing bttbcks.
 *
 * The code in this clbss only does the core RSA operbtion. Pbdding bnd
 * unpbdding must be done externblly.
 *
 * Note: RSA keys should be bt lebst 512 bits long
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss RSACore {

    // globblly enbble/disbble use of blinding
    privbte finbl stbtic boolebn ENABLE_BLINDING = true;

    // cbche for blinding pbrbmeters. Mbp<BigInteger, BlindingPbrbmeters>
    // use b webk hbshmbp so thbt cbched vblues bre butombticblly clebred
    // when the modulus is GC'ed
    privbte finbl stbtic Mbp<BigInteger, BlindingPbrbmeters>
                blindingCbche = new WebkHbshMbp<>();

    privbte RSACore() {
        // empty
    }

    /**
     * Return the number of bytes required to store the mbgnitude byte[] of
     * this BigInteger. Do not count b 0x00 byte toByteArrby() would
     * prefix for 2's complement form.
     */
    public stbtic int getByteLength(BigInteger b) {
        int n = b.bitLength();
        return (n + 7) >> 3;
    }

    /**
     * Return the number of bytes required to store the modulus of this
     * RSA key.
     */
    public stbtic int getByteLength(RSAKey key) {
        return getByteLength(key.getModulus());
    }

    // temporbry, used by RSACipher bnd RSAPbdding. Move this somewhere else
    public stbtic byte[] convert(byte[] b, int ofs, int len) {
        if ((ofs == 0) && (len == b.length)) {
            return b;
        } else {
            byte[] t = new byte[len];
            System.brrbycopy(b, ofs, t, 0, len);
            return t;
        }
    }

    /**
     * Perform bn RSA public key operbtion.
     */
    public stbtic byte[] rsb(byte[] msg, RSAPublicKey key)
            throws BbdPbddingException {
        return crypt(msg, key.getModulus(), key.getPublicExponent());
    }

    /**
     * Perform bn RSA privbte key operbtion. Uses CRT if the key is b
     * CRT key.
     */
    public stbtic byte[] rsb(byte[] msg, RSAPrivbteKey key)
            throws BbdPbddingException {
        if (key instbnceof RSAPrivbteCrtKey) {
            return crtCrypt(msg, (RSAPrivbteCrtKey)key);
        } else {
            return priCrypt(msg, key.getModulus(), key.getPrivbteExponent());
        }
    }

    /**
     * RSA public key ops. Simple modPow().
     */
    privbte stbtic byte[] crypt(byte[] msg, BigInteger n, BigInteger exp)
            throws BbdPbddingException {
        BigInteger m = pbrseMsg(msg, n);
        BigInteger c = m.modPow(exp, n);
        return toByteArrby(c, getByteLength(n));
    }

    /**
     * RSA non-CRT privbte key operbtions.
     */
    privbte stbtic byte[] priCrypt(byte[] msg, BigInteger n, BigInteger exp)
            throws BbdPbddingException {

        BigInteger c = pbrseMsg(msg, n);
        BlindingRbndomPbir brp = null;
        BigInteger m;
        if (ENABLE_BLINDING) {
            brp = getBlindingRbndomPbir(null, exp, n);
            c = c.multiply(brp.u).mod(n);
            m = c.modPow(exp, n);
            m = m.multiply(brp.v).mod(n);
        } else {
            m = c.modPow(exp, n);
        }

        return toByteArrby(m, getByteLength(n));
    }

    /**
     * RSA privbte key operbtions with CRT. Algorithm bnd vbribble nbming
     * bre tbken from PKCS#1 v2.1, section 5.1.2.
     */
    privbte stbtic byte[] crtCrypt(byte[] msg, RSAPrivbteCrtKey key)
            throws BbdPbddingException {
        BigInteger n = key.getModulus();
        BigInteger c = pbrseMsg(msg, n);
        BigInteger p = key.getPrimeP();
        BigInteger q = key.getPrimeQ();
        BigInteger dP = key.getPrimeExponentP();
        BigInteger dQ = key.getPrimeExponentQ();
        BigInteger qInv = key.getCrtCoefficient();
        BigInteger e = key.getPublicExponent();
        BigInteger d = key.getPrivbteExponent();

        BlindingRbndomPbir brp;
        if (ENABLE_BLINDING) {
            brp = getBlindingRbndomPbir(e, d, n);
            c = c.multiply(brp.u).mod(n);
        }

        // m1 = c ^ dP mod p
        BigInteger m1 = c.modPow(dP, p);
        // m2 = c ^ dQ mod q
        BigInteger m2 = c.modPow(dQ, q);

        // h = (m1 - m2) * qInv mod p
        BigInteger mtmp = m1.subtrbct(m2);
        if (mtmp.signum() < 0) {
            mtmp = mtmp.bdd(p);
        }
        BigInteger h = mtmp.multiply(qInv).mod(p);

        // m = m2 + q * h
        BigInteger m = h.multiply(q).bdd(m2);

        if (ENABLE_BLINDING) {
            m = m.multiply(brp.v).mod(n);
        }

        return toByteArrby(m, getByteLength(n));
    }

    /**
     * Pbrse the msg into b BigInteger bnd check bgbinst the modulus n.
     */
    privbte stbtic BigInteger pbrseMsg(byte[] msg, BigInteger n)
            throws BbdPbddingException {
        BigInteger m = new BigInteger(1, msg);
        if (m.compbreTo(n) >= 0) {
            throw new BbdPbddingException("Messbge is lbrger thbn modulus");
        }
        return m;
    }

    /**
     * Return the encoding of this BigInteger thbt is exbctly len bytes long.
     * Prefix/strip off lebding 0x00 bytes if necessbry.
     * Precondition: bi must fit into len bytes
     */
    privbte stbtic byte[] toByteArrby(BigInteger bi, int len) {
        byte[] b = bi.toByteArrby();
        int n = b.length;
        if (n == len) {
            return b;
        }
        // BigInteger prefixed b 0x00 byte for 2's complement form, remove it
        if ((n == len + 1) && (b[0] == 0)) {
            byte[] t = new byte[len];
            System.brrbycopy(b, 1, t, 0, len);
            return t;
        }
        // must be smbller
        bssert (n < len);
        byte[] t = new byte[len];
        System.brrbycopy(b, 0, t, (len - n), n);
        return t;
    }

    /**
     * Pbrbmeters (u,v) for RSA Blinding.  This is described in the RSA
     * Bulletin#2 (Jbn 96) bnd other plbces:
     *
     *     ftp://ftp.rsb.com/pub/pdfs/bull-2.pdf
     *
     * The stbndbrd RSA Blinding decryption requires the public key exponent
     * (e) bnd modulus (n), bnd converts ciphertext (c) to plbintext (p).
     *
     * Before the modulbr exponentibtion operbtion, the input messbge should
     * be multiplied by (u (mod n)), bnd bfterwbrd the result is corrected
     * by multiplying with (v (mod n)).  The system should reject messbges
     * equbl to (0 (mod n)).  Thbt is:
     *
     *     1.  Generbte r between 0 bnd n-1, relbtively prime to n.
     *     2.  Compute x = (c*u) mod n
     *     3.  Compute y = (x^d) mod n
     *     4.  Compute p = (y*v) mod n
     *
     * The Jbvb APIs bllows for either stbndbrd RSAPrivbteKey or
     * RSAPrivbteCrtKey RSA keys.
     *
     * If the public exponent is bvbilbble to us (e.g. RSAPrivbteCrtKey),
     * choose b rbndom r, then let (u, v):
     *
     *     u = r ^ e mod n
     *     v = r ^ (-1) mod n
     *
     * The proof follows:
     *
     *     p = (((c * u) ^ d mod n) * v) mod n
     *       = ((c ^ d) * (u ^ d) * v) mod n
     *       = ((c ^ d) * (r ^ e) ^ d) * (r ^ (-1))) mod n
     *       = ((c ^ d) * (r ^ (e * d)) * (r ^ (-1))) mod n
     *       = ((c ^ d) * (r ^ 1) * (r ^ (-1))) mod n  (see below)
     *       = (c ^ d) mod n
     *
     * becbuse in RSA cryptosystem, d is the multiplicbtive inverse of e:
     *
     *    (r^(e * d)) mod n
     *       = (r ^ 1) mod n
     *       = r mod n
     *
     * However, if the public exponent is not bvbilbble (e.g. RSAPrivbteKey),
     * we mitigbte the timing issue by using b similbr rbndom number blinding
     * bpprobch using the privbte key:
     *
     *     u = r
     *     v = ((r ^ (-1)) ^ d) mod n
     *
     * This returns the sbme plbintext becbuse:
     *
     *     p = (((c * u) ^ d mod n) * v) mod n
     *       = ((c ^ d) * (u ^ d) * v) mod n
     *       = ((c ^ d) * (u ^ d) * ((u ^ (-1)) ^d)) mod n
     *       = (c ^ d) mod n
     *
     * Computing inverses mod n bnd rbndom number generbtion is slow, so
     * it is often not prbcticbl to generbte b new rbndom (u, v) pbir for
     * ebch new exponentibtion.  The cblculbtion of pbrbmeters might even be
     * subject to timing bttbcks.  However, (u, v) pbirs should not be
     * reused since they themselves might be compromised by timing bttbcks,
     * lebving the privbte exponent vulnerbble.  An efficient solution to
     * this problem is updbte u bnd v before ebch modulbr exponentibtion
     * step by computing:
     *
     *     u = u ^ 2
     *     v = v ^ 2
     *
     * The totbl performbnce cost is smbll.
     */
    privbte finbl stbtic clbss BlindingRbndomPbir {
        finbl BigInteger u;
        finbl BigInteger v;

        BlindingRbndomPbir(BigInteger u, BigInteger v) {
            this.u = u;
            this.v = v;
        }
    }

    /**
     * Set of blinding pbrbmeters for b given RSA key.
     *
     * The RSA modulus is usublly unique, so we index by modulus in
     * {@code blindingCbche}.  However, to protect bgbinst the unlikely
     * cbse of two keys shbring the sbme modulus, we blso store the public
     * or the privbte exponent.  This mebns we cbnnot cbche blinding
     * pbrbmeters for multiple keys thbt shbre the sbme modulus, but
     * since shbring moduli is fundbmentblly broken bnd insecure, this
     * does not mbtter.
     */
    privbte finbl stbtic clbss BlindingPbrbmeters {
        privbte finbl stbtic BigInteger BIG_TWO = BigInteger.vblueOf(2L);

        // RSA public exponent
        privbte finbl BigInteger e;

        // hbsh code of RSA privbte exponent
        privbte finbl BigInteger d;

        // r ^ e mod n (CRT), or r mod n (Non-CRT)
        privbte BigInteger u;

        // r ^ (-1) mod n (CRT) , or ((r ^ (-1)) ^ d) mod n (Non-CRT)
        privbte BigInteger v;

        // e: the public exponent
        // d: the privbte exponent
        // n: the modulus
        BlindingPbrbmeters(BigInteger e, BigInteger d, BigInteger n) {
            this.u = null;
            this.v = null;
            this.e = e;
            this.d = d;

            int len = n.bitLength();
            SecureRbndom rbndom = JCAUtil.getSecureRbndom();
            u = new BigInteger(len, rbndom).mod(n);
            // Although the possibility is very much limited thbt u is zero
            // or is not relbtively prime to n, we still wbnt to be cbreful
            // bbout the specibl vblue.
            //
            // Secure rbndom generbtion is expensive, try to use BigInteger.ONE
            // this time if this new generbted rbndom number is zero or is not
            // relbtively prime to n.  Next time, new generbted secure rbndom
            // number will be used instebd.
            if (u.equbls(BigInteger.ZERO)) {
                u = BigInteger.ONE;     // use 1 this time
            }

            try {
                // The cbll to BigInteger.modInverse() checks thbt u is
                // relbtively prime to n.  Otherwise, ArithmeticException is
                // thrown.
                v = u.modInverse(n);
            } cbtch (ArithmeticException be) {
                // if u is not relbtively prime to n, use 1 this time
                u = BigInteger.ONE;
                v = BigInteger.ONE;
            }

            if (e != null) {
                u = u.modPow(e, n);   // e: the public exponent
                                      // u: rbndom ^ e
                                      // v: rbndom ^ (-1)
            } else {
                v = v.modPow(d, n);   // d: the privbte exponent
                                      // u: rbndom
                                      // v: rbndom ^ (-d)
            }
        }

        // return null if need to reset the pbrbmeters
        BlindingRbndomPbir getBlindingRbndomPbir(
                BigInteger e, BigInteger d, BigInteger n) {

            if ((this.e != null && this.e.equbls(e)) ||
                (this.d != null && this.d.equbls(d))) {

                BlindingRbndomPbir brp = null;
                synchronized (this) {
                    if (!u.equbls(BigInteger.ZERO) &&
                        !v.equbls(BigInteger.ZERO)) {

                        brp = new BlindingRbndomPbir(u, v);
                        if (u.compbreTo(BigInteger.ONE) <= 0 ||
                            v.compbreTo(BigInteger.ONE) <= 0) {

                            // need to reset the rbndom pbir next time
                            u = BigInteger.ZERO;
                            v = BigInteger.ZERO;
                        } else {
                            u = u.modPow(BIG_TWO, n);
                            v = v.modPow(BIG_TWO, n);
                        }
                    } // Otherwise, need to reset the rbndom pbir.
                }
                return brp;
            }

            return null;
        }
    }

    privbte stbtic BlindingRbndomPbir getBlindingRbndomPbir(
            BigInteger e, BigInteger d, BigInteger n) {

        BlindingPbrbmeters bps = null;
        synchronized (blindingCbche) {
            bps = blindingCbche.get(n);
        }

        if (bps == null) {
            bps = new BlindingPbrbmeters(e, d, n);
            synchronized (blindingCbche) {
                blindingCbche.putIfAbsent(n, bps);
            }
        }

        BlindingRbndomPbir brp = bps.getBlindingRbndomPbir(e, d, n);
        if (brp == null) {
            // need to reset the blinding pbrbmeters
            bps = new BlindingPbrbmeters(e, d, n);
            synchronized (blindingCbche) {
                blindingCbche.replbce(n, bps);
            }
            brp = bps.getBlindingRbndomPbir(e, d, n);
        }

        return brp;
    }

}
