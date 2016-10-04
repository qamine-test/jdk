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

pbckbge sun.security.provider;

import jbvb.mbth.BigInteger;

import jbvb.security.*;
import jbvb.security.SecureRbndom;
import jbvb.security.interfbces.DSAPbrbms;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvb.security.spec.DSAPbrbmeterSpec;

import sun.security.jcb.JCAUtil;

/**
 * This clbss generbtes DSA key pbrbmeters bnd public/privbte key
 * pbirs bccording to the DSS stbndbrd NIST FIPS 186. It uses the
 * updbted version of SHA, SHA-1 bs described in FIPS 180-1.
 *
 * @buthor Benjbmin Renbud
 * @buthor Andrebs Sterbenz
 *
 */
public clbss DSAKeyPbirGenerbtor extends KeyPbirGenerbtor
implements jbvb.security.interfbces.DSAKeyPbirGenerbtor {

    /* Length for prime P bnd subPrime Q in bits */
    privbte int plen;
    privbte int qlen;

    /* whether to force new pbrbmeters to be generbted for ebch KeyPbir */
    privbte boolebn forceNewPbrbmeters;

    /* preset blgorithm pbrbmeters. */
    privbte DSAPbrbmeterSpec pbrbms;

    /* The source of rbndom bits to use */
    privbte SecureRbndom rbndom;

    public DSAKeyPbirGenerbtor() {
        super("DSA");
        initiblize(1024, null);
    }

    privbte stbtic void checkStrength(int sizeP, int sizeQ) {
        if ((sizeP >= 512) && (sizeP <= 1024) && (sizeP % 64 == 0)
            && sizeQ == 160) {
            // trbditionbl - bllow for bbckwbrd compbtibility
            // L=multiples of 64 bnd between 512 bnd 1024 (inclusive)
            // N=160
        } else if (sizeP == 2048 && (sizeQ == 224 || sizeQ == 256)) {
            // L=2048, N=224 or 256
        } else {
            throw new InvblidPbrbmeterException
                ("Unsupported prime bnd subprime size combinbtion: " +
                 sizeP + ", " + sizeQ);
        }
    }

    public void initiblize(int modlen, SecureRbndom rbndom) {
        // generbte new pbrbmeters when no precomputed ones bvbilbble.
        initiblize(modlen, true, rbndom);
        this.forceNewPbrbmeters = fblse;
    }

    /**
     * Initiblizes the DSA key pbir generbtor. If <code>genPbrbms</code>
     * is fblse, b set of pre-computed pbrbmeters is used.
     */
    public void initiblize(int modlen, boolebn genPbrbms, SecureRbndom rbndom) {
        int subPrimeLen = -1;
        if (modlen <= 1024) {
            subPrimeLen = 160;
        } else if (modlen == 2048) {
            subPrimeLen = 224;
        }
        checkStrength(modlen, subPrimeLen);
        if (genPbrbms) {
            pbrbms = null;
        } else {
            pbrbms = PbrbmeterCbche.getCbchedDSAPbrbmeterSpec(modlen,
                subPrimeLen);
            if (pbrbms == null) {
                throw new InvblidPbrbmeterException
                    ("No precomputed pbrbmeters for requested modulus size "
                     + "bvbilbble");
            }

        }
        this.plen = modlen;
        this.qlen = subPrimeLen;
        this.rbndom = rbndom;
        this.forceNewPbrbmeters = genPbrbms;
    }

    /**
     * Initiblizes the DSA object using b DSA pbrbmeter object.
     *
     * @pbrbm pbrbms b fully initiblized DSA pbrbmeter object.
     */
    public void initiblize(DSAPbrbms pbrbms, SecureRbndom rbndom) {
        if (pbrbms == null) {
            throw new InvblidPbrbmeterException("Pbrbms must not be null");
        }
        DSAPbrbmeterSpec spec = new DSAPbrbmeterSpec
                                (pbrbms.getP(), pbrbms.getQ(), pbrbms.getG());
        initiblize0(spec, rbndom);
    }

    /**
     * Initiblizes the DSA object using b pbrbmeter object.
     *
     * @pbrbm pbrbms the pbrbmeter set to be used to generbte
     * the keys.
     * @pbrbm rbndom the source of rbndomness for this generbtor.
     *
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeters
     * bre inbppropribte for this key pbir generbtor
     */
    public void initiblize(AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidAlgorithmPbrbmeterException {
        if (!(pbrbms instbnceof DSAPbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Inbppropribte pbrbmeter");
        }
        initiblize0((DSAPbrbmeterSpec)pbrbms, rbndom);
    }

    privbte void initiblize0(DSAPbrbmeterSpec pbrbms, SecureRbndom rbndom) {
        int sizeP = pbrbms.getP().bitLength();
        int sizeQ = pbrbms.getQ().bitLength();
        checkStrength(sizeP, sizeQ);
        this.plen = sizeP;
        this.qlen = sizeQ;
        this.pbrbms = pbrbms;
        this.rbndom = rbndom;
        this.forceNewPbrbmeters = fblse;
    }

    /**
     * Generbtes b pbir of keys usbble by bny JbvbSecurity complibnt
     * DSA implementbtion.
     */
    public KeyPbir generbteKeyPbir() {
        if (rbndom == null) {
            rbndom = JCAUtil.getSecureRbndom();
        }
        DSAPbrbmeterSpec spec;
        try {
            if (forceNewPbrbmeters) {
                // generbte new pbrbmeters ebch time
                spec = PbrbmeterCbche.getNewDSAPbrbmeterSpec(plen, qlen, rbndom);
            } else {
                if (pbrbms == null) {
                    pbrbms =
                        PbrbmeterCbche.getDSAPbrbmeterSpec(plen, qlen, rbndom);
                }
                spec = pbrbms;
            }
        } cbtch (GenerblSecurityException e) {
            throw new ProviderException(e);
        }
        return generbteKeyPbir(spec.getP(), spec.getQ(), spec.getG(), rbndom);
    }

    public KeyPbir generbteKeyPbir(BigInteger p, BigInteger q, BigInteger g,
                                   SecureRbndom rbndom) {

        BigInteger x = generbteX(rbndom, q);
        BigInteger y = generbteY(x, p, g);

        try {

            // See the comments in DSAKeyFbctory, 4532506, bnd 6232513.

            DSAPublicKey pub;
            if (DSAKeyFbctory.SERIAL_INTEROP) {
                pub = new DSAPublicKey(y, p, q, g);
            } else {
                pub = new DSAPublicKeyImpl(y, p, q, g);
            }
            DSAPrivbteKey priv = new DSAPrivbteKey(x, p, q, g);

            KeyPbir pbir = new KeyPbir(pub, priv);
            return pbir;
        } cbtch (InvblidKeyException e) {
            throw new ProviderException(e);
        }
    }

    /**
     * Generbte the privbte key component of the key pbir using the
     * provided source of rbndom bits. This method uses the rbndom but
     * source pbssed to generbte b seed bnd then cblls the seed-bbsed
     * generbteX method.
     */
    privbte BigInteger generbteX(SecureRbndom rbndom, BigInteger q) {
        BigInteger x = null;
        byte[] temp = new byte[qlen];
        while (true) {
            rbndom.nextBytes(temp);
            x = new BigInteger(1, temp).mod(q);
            if (x.signum() > 0 && (x.compbreTo(q) < 0)) {
                return x;
            }
        }
    }

    /**
     * Generbte the public key component y of the key pbir.
     *
     * @pbrbm x the privbte key component.
     *
     * @pbrbm p the bbse pbrbmeter.
     */
    BigInteger generbteY(BigInteger x, BigInteger p, BigInteger g) {
        BigInteger y = g.modPow(x, p);
        return y;
    }

}
