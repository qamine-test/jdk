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

import jbvb.util.*;
import jbvb.io.*;
import jbvb.mbth.BigInteger;
import jbvb.security.InvblidKeyException;
import jbvb.security.ProviderException;
import jbvb.security.AlgorithmPbrbmeters;
import jbvb.security.spec.DSAPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvb.security.interfbces.DSAPbrbms;

import sun.security.x509.X509Key;
import sun.security.x509.AlgIdDSA;
import sun.security.util.BitArrby;
import sun.security.util.Debug;
import sun.security.util.DerVblue;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;

/**
 * An X.509 public key for the Digitbl Signbture Algorithm.
 *
 * @buthor Benjbmin Renbud
 *
 *
 * @see DSAPrivbteKey
 * @see AlgIdDSA
 * @see DSA
 */

public clbss DSAPublicKey extends X509Key
implements jbvb.security.interfbces.DSAPublicKey, Seriblizbble {

    /** use seriblVersionUID from JDK 1.1. for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -2994193307391104133L;

    /* the public key */
    privbte BigInteger y;

    /*
     * Keep this constructor for bbckwbrds compbtibility with JDK1.1.
     */
    public DSAPublicKey() {
    }

    /**
     * Mbke b DSA public key out of b public key bnd three pbrbmeters.
     * The p, q, bnd g pbrbmeters mby be null, but if so, pbrbmeters will need
     * to be supplied from some other source before this key cbn be used in
     * cryptogrbphic operbtions.  PKIX RFC2459bis explicitly bllows DSA public
     * keys without pbrbmeters, where the pbrbmeters bre provided in the
     * issuer's DSA public key.
     *
     * @pbrbm y the bctubl key bits
     * @pbrbm p DSA pbrbmeter p, mby be null if bll of p, q, bnd g bre null.
     * @pbrbm q DSA pbrbmeter q, mby be null if bll of p, q, bnd g bre null.
     * @pbrbm g DSA pbrbmeter g, mby be null if bll of p, q, bnd g bre null.
     */
    public DSAPublicKey(BigInteger y, BigInteger p, BigInteger q,
                        BigInteger g)
    throws InvblidKeyException {
        this.y = y;
        blgid = new AlgIdDSA(p, q, g);

        try {
            byte[] keyArrby = new DerVblue(DerVblue.tbg_Integer,
                               y.toByteArrby()).toByteArrby();
            setKey(new BitArrby(keyArrby.length*8, keyArrby));
            encode();
        } cbtch (IOException e) {
            throw new InvblidKeyException("could not DER encode y: " +
                                          e.getMessbge());
        }
    }

    /**
     * Mbke b DSA public key from its DER encoding (X.509).
     */
    public DSAPublicKey(byte[] encoded) throws InvblidKeyException {
        decode(encoded);
    }

    /**
     * Returns the DSA pbrbmeters bssocibted with this key, or null if the
     * pbrbmeters could not be pbrsed.
     */
    public DSAPbrbms getPbrbms() {
        try {
            if (blgid instbnceof DSAPbrbms) {
                return (DSAPbrbms)blgid;
            } else {
                DSAPbrbmeterSpec pbrbmSpec;
                AlgorithmPbrbmeters blgPbrbms = blgid.getPbrbmeters();
                if (blgPbrbms == null) {
                    return null;
                }
                pbrbmSpec = blgPbrbms.getPbrbmeterSpec(DSAPbrbmeterSpec.clbss);
                return (DSAPbrbms)pbrbmSpec;
            }
        } cbtch (InvblidPbrbmeterSpecException e) {
            return null;
        }
    }

    /**
     * Get the rbw public vblue, y, without the pbrbmeters.
     *
     * @see getPbrbmeters
     */
    public BigInteger getY() {
        return y;
    }

    public String toString() {
        return "Sun DSA Public Key\n    Pbrbmeters:" + blgid
            + "\n  y:\n" + Debug.toHexString(y) + "\n";
    }

    protected void pbrseKeyBits() throws InvblidKeyException {
        try {
            DerInputStrebm in = new DerInputStrebm(getKey().toByteArrby());
            y = in.getBigInteger();
        } cbtch (IOException e) {
            throw new InvblidKeyException("Invblid key: y vblue\n" +
                                          e.getMessbge());
        }
    }
}
