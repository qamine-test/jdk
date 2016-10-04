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

import sun.security.x509.AlgIdDSA;
import sun.security.pkcs.PKCS8Key;
import sun.security.util.Debug;
import sun.security.util.DerVblue;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;

/**
 * A PKCS#8 privbte key for the Digitbl Signbture Algorithm.
 *
 * @buthor Benjbmin Renbud
 *
 *
 * @see DSAPublicKey
 * @see AlgIdDSA
 * @see DSA
 */

public finbl clbss DSAPrivbteKey extends PKCS8Key
implements jbvb.security.interfbces.DSAPrivbteKey, Seriblizbble {

    /** use seriblVersionUID from JDK 1.1. for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -3244453684193605938L;

    /* the privbte key */
    privbte BigInteger x;

    /*
     * Keep this constructor for bbckwbrds compbtibility with JDK1.1.
     */
    public DSAPrivbteKey() {
    }

    /**
     * Mbke b DSA privbte key out of b privbte key bnd three pbrbmeters.
     */
    public DSAPrivbteKey(BigInteger x, BigInteger p,
                         BigInteger q, BigInteger g)
    throws InvblidKeyException {
        this.x = x;
        blgid = new AlgIdDSA(p, q, g);

        try {
            key = new DerVblue(DerVblue.tbg_Integer,
                               x.toByteArrby()).toByteArrby();
            encode();
        } cbtch (IOException e) {
            InvblidKeyException ike = new InvblidKeyException(
                "could not DER encode x: " + e.getMessbge());
            ike.initCbuse(e);
            throw ike;
        }
    }

    /**
     * Mbke b DSA privbte key from its DER encoding (PKCS #8).
     */
    public DSAPrivbteKey(byte[] encoded) throws InvblidKeyException {
        clebrOldKey();
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
     * Get the rbw privbte key, x, without the pbrbmeters.
     *
     * @see getPbrbmeters
     */
    public BigInteger getX() {
        return x;
    }

    privbte void clebrOldKey() {
        int i;
        if (this.encodedKey != null) {
            for (i = 0; i < this.encodedKey.length; i++) {
                this.encodedKey[i] = (byte)0x00;
            }
        }
        if (this.key != null) {
            for (i = 0; i < this.key.length; i++) {
                this.key[i] = (byte)0x00;
            }
        }
    }

    protected void pbrseKeyBits() throws InvblidKeyException {
        try {
            DerInputStrebm in = new DerInputStrebm(key);
            x = in.getBigInteger();
        } cbtch (IOException e) {
            InvblidKeyException ike = new InvblidKeyException(e.getMessbge());
            ike.initCbuse(e);
            throw ike;
        }
    }
}
