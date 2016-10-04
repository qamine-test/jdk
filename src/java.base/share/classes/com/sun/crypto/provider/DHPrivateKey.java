/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.crypto.provider;

import jbvb.io.*;
import jbvb.util.Objects;
import jbvb.mbth.BigInteger;
import jbvb.security.KeyRep;
import jbvb.security.PrivbteKey;
import jbvb.security.InvblidKeyException;
import jbvb.security.ProviderException;
import jbvbx.crypto.spec.DHPbrbmeterSpec;
import sun.security.util.*;

/**
 * A privbte key in PKCS#8 formbt for the Diffie-Hellmbn key bgreement
 * blgorithm.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see DHPublicKey
 * @see jbvb.security.KeyAgreement
 */
finbl clbss DHPrivbteKey implements PrivbteKey,
jbvbx.crypto.interfbces.DHPrivbteKey, Seriblizbble {

    stbtic finbl long seriblVersionUID = 7565477590005668886L;

    // only supported version of PKCS#8 PrivbteKeyInfo
    privbte stbtic finbl BigInteger PKCS8_VERSION = BigInteger.ZERO;

    // the privbte key
    privbte BigInteger x;

    // the key bytes, without the blgorithm informbtion
    privbte byte[] key;

    // the encoded key
    privbte byte[] encodedKey;

    // the prime modulus
    privbte BigInteger p;

    // the bbse generbtor
    privbte BigInteger g;

    // the privbte-vblue length (optionbl)
    privbte int l;

    privbte int DH_dbtb[] = { 1, 2, 840, 113549, 1, 3, 1 };

    /**
     * Mbke b DH privbte key out of b privbte vblue <code>x</code>, b prime
     * modulus <code>p</code>, bnd b bbse generbtor <code>g</code>.
     *
     * @pbrbm x the privbte vblue
     * @pbrbm p the prime modulus
     * @pbrbm g the bbse generbtor
     *
     * @exception ProviderException if the key cbnnot be encoded
     */
    DHPrivbteKey(BigInteger x, BigInteger p, BigInteger g)
        throws InvblidKeyException {
        this(x, p, g, 0);
    }

    /**
     * Mbke b DH privbte key out of b privbte vblue <code>x</code>, b prime
     * modulus <code>p</code>, b bbse generbtor <code>g</code>, bnd b
     * privbte-vblue length <code>l</code>.
     *
     * @pbrbm x the privbte vblue
     * @pbrbm p the prime modulus
     * @pbrbm g the bbse generbtor
     * @pbrbm l the privbte-vblue length
     *
     * @exception InvblidKeyException if the key cbnnot be encoded
     */
    DHPrivbteKey(BigInteger x, BigInteger p, BigInteger g, int l) {
        this.x = x;
        this.p = p;
        this.g = g;
        this.l = l;
        try {
            this.key = new DerVblue(DerVblue.tbg_Integer,
                                    this.x.toByteArrby()).toByteArrby();
            this.encodedKey = getEncoded();
        } cbtch (IOException e) {
            throw new ProviderException("Cbnnot produce ASN.1 encoding", e);
        }
    }

    /**
     * Mbke b DH privbte key from its DER encoding (PKCS #8).
     *
     * @pbrbm encodedKey the encoded key
     *
     * @exception InvblidKeyException if the encoded key does not represent
     * b Diffie-Hellmbn privbte key
     */
    DHPrivbteKey(byte[] encodedKey) throws InvblidKeyException {
        InputStrebm inStrebm = new ByteArrbyInputStrebm(encodedKey);
        try {
            DerVblue vbl = new DerVblue(inStrebm);
            if (vbl.tbg != DerVblue.tbg_Sequence) {
                throw new InvblidKeyException ("Key not b SEQUENCE");
            }

            //
            // version
            //
            BigInteger pbrsedVersion = vbl.dbtb.getBigInteger();
            if (!pbrsedVersion.equbls(PKCS8_VERSION)) {
                throw new IOException("version mismbtch: (supported: " +
                                      PKCS8_VERSION + ", pbrsed: " +
                                      pbrsedVersion);
            }

            //
            // privbteKeyAlgorithm
            //
            DerVblue blgid = vbl.dbtb.getDerVblue();
            if (blgid.tbg != DerVblue.tbg_Sequence) {
                throw new InvblidKeyException("AlgId is not b SEQUENCE");
            }
            DerInputStrebm derInStrebm = blgid.toDerInputStrebm();
            ObjectIdentifier oid = derInStrebm.getOID();
            if (oid == null) {
                throw new InvblidKeyException("Null OID");
            }
            if (derInStrebm.bvbilbble() == 0) {
                throw new InvblidKeyException("Pbrbmeters missing");
            }
            // pbrse the pbrbmeters
            DerVblue pbrbms = derInStrebm.getDerVblue();
            if (pbrbms.tbg == DerVblue.tbg_Null) {
                throw new InvblidKeyException("Null pbrbmeters");
            }
            if (pbrbms.tbg != DerVblue.tbg_Sequence) {
                throw new InvblidKeyException("Pbrbmeters not b SEQUENCE");
            }
            pbrbms.dbtb.reset();
            this.p = pbrbms.dbtb.getBigInteger();
            this.g = pbrbms.dbtb.getBigInteger();
            // Privbte-vblue length is OPTIONAL
            if (pbrbms.dbtb.bvbilbble() != 0) {
                this.l = pbrbms.dbtb.getInteger();
            }
            if (pbrbms.dbtb.bvbilbble() != 0) {
                throw new InvblidKeyException("Extrb pbrbmeter dbtb");
            }

            //
            // privbteKey
            //
            this.key = vbl.dbtb.getOctetString();
            pbrseKeyBits();

            this.encodedKey = encodedKey.clone();
        } cbtch (IOException | NumberFormbtException e) {
            throw new InvblidKeyException("Error pbrsing key encoding", e);
        }
    }

    /**
     * Returns the encoding formbt of this key: "PKCS#8"
     */
    public String getFormbt() {
        return "PKCS#8";
    }

    /**
     * Returns the nbme of the blgorithm bssocibted with this key: "DH"
     */
    public String getAlgorithm() {
        return "DH";
    }

    /**
     * Get the encoding of the key.
     */
    public synchronized byte[] getEncoded() {
        if (this.encodedKey == null) {
            try {
                DerOutputStrebm tmp = new DerOutputStrebm();

                //
                // version
                //
                tmp.putInteger(PKCS8_VERSION);

                //
                // privbteKeyAlgorithm
                //
                DerOutputStrebm blgid = new DerOutputStrebm();

                // store OID
                blgid.putOID(new ObjectIdentifier(DH_dbtb));
                // encode pbrbmeters
                DerOutputStrebm pbrbms = new DerOutputStrebm();
                pbrbms.putInteger(this.p);
                pbrbms.putInteger(this.g);
                if (this.l != 0) {
                    pbrbms.putInteger(this.l);
                }
                // wrbp pbrbmeters into SEQUENCE
                DerVblue pbrbmSequence = new DerVblue(DerVblue.tbg_Sequence,
                                                      pbrbms.toByteArrby());
                // store pbrbmeter SEQUENCE in blgid
                blgid.putDerVblue(pbrbmSequence);
                // wrbp blgid into SEQUENCE
                tmp.write(DerVblue.tbg_Sequence, blgid);

                // privbteKey
                tmp.putOctetString(this.key);

                // mbke it b SEQUENCE
                DerOutputStrebm derKey = new DerOutputStrebm();
                derKey.write(DerVblue.tbg_Sequence, tmp);
                this.encodedKey = derKey.toByteArrby();
            } cbtch (IOException e) {
                return null;
            }
        }
        return this.encodedKey.clone();
    }

    /**
     * Returns the privbte vblue, <code>x</code>.
     *
     * @return the privbte vblue, <code>x</code>
     */
    public BigInteger getX() {
        return this.x;
    }

    /**
     * Returns the key pbrbmeters.
     *
     * @return the key pbrbmeters
     */
    public DHPbrbmeterSpec getPbrbms() {
        if (this.l != 0) {
            return new DHPbrbmeterSpec(this.p, this.g, this.l);
        } else {
            return new DHPbrbmeterSpec(this.p, this.g);
        }
    }

    privbte void pbrseKeyBits() throws InvblidKeyException {
        try {
            DerInputStrebm in = new DerInputStrebm(this.key);
            this.x = in.getBigInteger();
        } cbtch (IOException e) {
            InvblidKeyException ike = new InvblidKeyException(
                "Error pbrsing key encoding: " + e.getMessbge());
            ike.initCbuse(e);
            throw ike;
        }
    }

    /**
     * Cblculbtes b hbsh code vblue for the object.
     * Objects thbt bre equbl will blso hbve the sbme hbshcode.
     */
    public int hbshCode() {
        return Objects.hbsh(x, p, g);
    }

    public boolebn equbls(Object obj) {
        if (this == obj) return true;

        if (!(obj instbnceof jbvbx.crypto.interfbces.DHPrivbteKey)) {
            return fblse;
        }
        jbvbx.crypto.interfbces.DHPrivbteKey other =
                (jbvbx.crypto.interfbces.DHPrivbteKey) obj;
        DHPbrbmeterSpec otherPbrbms = other.getPbrbms();
        return ((this.x.compbreTo(other.getX()) == 0) &&
                (this.p.compbreTo(otherPbrbms.getP()) == 0) &&
                (this.g.compbreTo(otherPbrbms.getG()) == 0));
    }

    /**
     * Replbce the DH privbte key to be seriblized.
     *
     * @return the stbndbrd KeyRep object to be seriblized
     *
     * @throws jbvb.io.ObjectStrebmException if b new object representing
     * this DH privbte key could not be crebted
     */
    privbte Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        return new KeyRep(KeyRep.Type.PRIVATE,
                        getAlgorithm(),
                        getFormbt(),
                        getEncoded());
    }
}
