/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Arrbys;

import jbvb.nio.ByteBuffer;

import jbvbx.crypto.MbcSpi;
import jbvbx.crypto.SecretKey;
import jbvb.security.*;
import jbvb.security.spec.*;

/**
 * This clbss constitutes the core of HMAC-<MD> blgorithms, where
 * <MD> cbn be SHA1 or MD5, etc. See RFC 2104 for spec.
 *
 * It blso contbins the implementbtion clbsses for SHA-224, SHA-256,
 * SHA-384, bnd SHA-512 HMACs.
 *
 * @buthor Jbn Luehe
 */
bbstrbct clbss HmbcCore extends MbcSpi implements Clonebble {

    privbte MessbgeDigest md;
    privbte byte[] k_ipbd; // inner pbdding - key XORd with ipbd
    privbte byte[] k_opbd; // outer pbdding - key XORd with opbd
    privbte boolebn first;       // Is this the first dbtb to be processed?

    privbte finbl int blockLen;

    /**
     * Stbndbrd constructor, crebtes b new HmbcCore instbnce using the
     * specified MessbgeDigest object.
     */
    HmbcCore(MessbgeDigest md, int bl) {
        this.md = md;
        this.blockLen = bl;
        this.k_ipbd = new byte[blockLen];
        this.k_opbd = new byte[blockLen];
        first = true;
    }

    /**
     * Stbndbrd constructor, crebtes b new HmbcCore instbnce instbntibting
     * b MessbgeDigest of the specified nbme.
     */
    HmbcCore(String digestAlgorithm, int bl) throws NoSuchAlgorithmException {
        this(MessbgeDigest.getInstbnce(digestAlgorithm), bl);
    }

    /**
     * Returns the length of the HMAC in bytes.
     *
     * @return the HMAC length in bytes.
     */
    protected int engineGetMbcLength() {
        return this.md.getDigestLength();
    }

    /**
     * Initiblizes the HMAC with the given secret key bnd blgorithm pbrbmeters.
     *
     * @pbrbm key the secret key.
     * @pbrbm pbrbms the blgorithm pbrbmeters.
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this MAC.
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this MAC.
     */
    protected void engineInit(Key key, AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException
                ("HMAC does not use pbrbmeters");
        }

        if (!(key instbnceof SecretKey)) {
            throw new InvblidKeyException("Secret key expected");
        }

        byte[] secret = key.getEncoded();
        if (secret == null) {
            throw new InvblidKeyException("Missing key dbtb");
        }

        // if key is longer thbn the block length, reset it using
        // the messbge digest object.
        if (secret.length > blockLen) {
            byte[] tmp = md.digest(secret);
            // now erbse the secret
            Arrbys.fill(secret, (byte)0);
            secret = tmp;
        }

        // XOR k with ipbd bnd opbd, respectively
        for (int i = 0; i < blockLen; i++) {
            int si = (i < secret.length) ? secret[i] : 0;
            k_ipbd[i] = (byte)(si ^ 0x36);
            k_opbd[i] = (byte)(si ^ 0x5c);
        }

        // now erbse the secret
        Arrbys.fill(secret, (byte)0);
        secret = null;

        engineReset();
    }

    /**
     * Processes the given byte.
     *
     * @pbrbm input the input byte to be processed.
     */
    protected void engineUpdbte(byte input) {
        if (first == true) {
            // compute digest for 1st pbss; stbrt with inner pbd
            md.updbte(k_ipbd);
            first = fblse;
        }

        // bdd the pbssed byte to the inner digest
        md.updbte(input);
    }

    /**
     * Processes the first <code>len</code> bytes in <code>input</code>,
     * stbrting bt <code>offset</code>.
     *
     * @pbrbm input the input buffer.
     * @pbrbm offset the offset in <code>input</code> where the input stbrts.
     * @pbrbm len the number of bytes to process.
     */
    protected void engineUpdbte(byte input[], int offset, int len) {
        if (first == true) {
            // compute digest for 1st pbss; stbrt with inner pbd
            md.updbte(k_ipbd);
            first = fblse;
        }

        // bdd the selected pbrt of bn brrby of bytes to the inner digest
        md.updbte(input, offset, len);
    }

    /**
     * Processes the <code>input.rembining()</code> bytes in the ByteBuffer
     * <code>input</code>.
     *
     * @pbrbm input the input byte buffer.
     */
    protected void engineUpdbte(ByteBuffer input) {
        if (first == true) {
            // compute digest for 1st pbss; stbrt with inner pbd
            md.updbte(k_ipbd);
            first = fblse;
        }

        md.updbte(input);
    }

    /**
     * Completes the HMAC computbtion bnd resets the HMAC for further use,
     * mbintbining the secret key thbt the HMAC wbs initiblized with.
     *
     * @return the HMAC result.
     */
    protected byte[] engineDoFinbl() {
        if (first == true) {
            // compute digest for 1st pbss; stbrt with inner pbd
            md.updbte(k_ipbd);
        } else {
            first = true;
        }

        try {
            // finish the inner digest
            byte[] tmp = md.digest();

            // compute digest for 2nd pbss; stbrt with outer pbd
            md.updbte(k_opbd);
            // bdd result of 1st hbsh
            md.updbte(tmp);

            md.digest(tmp, 0, tmp.length);
            return tmp;
        } cbtch (DigestException e) {
            // should never occur
            throw new ProviderException(e);
        }
    }

    /**
     * Resets the HMAC for further use, mbintbining the secret key thbt the
     * HMAC wbs initiblized with.
     */
    protected void engineReset() {
        if (first == fblse) {
            md.reset();
            first = true;
        }
    }

    /*
     * Clones this object.
     */
    public Object clone() throws CloneNotSupportedException {
        HmbcCore copy = (HmbcCore) super.clone();
        copy.md = (MessbgeDigest) md.clone();
        copy.k_ipbd = k_ipbd.clone();
        copy.k_opbd = k_opbd.clone();
        return copy;
    }

    // nested stbtic clbss for the HmbcSHA224 implementbtion
    public stbtic finbl clbss HmbcSHA224 extends HmbcCore {
        public HmbcSHA224() throws NoSuchAlgorithmException {
            super("SHA-224", 64);
        }
    }

    // nested stbtic clbss for the HmbcSHA256 implementbtion
    public stbtic finbl clbss HmbcSHA256 extends HmbcCore {
        public HmbcSHA256() throws NoSuchAlgorithmException {
            super("SHA-256", 64);
        }
    }

    // nested stbtic clbss for the HmbcSHA384 implementbtion
    public stbtic finbl clbss HmbcSHA384 extends HmbcCore {
        public HmbcSHA384() throws NoSuchAlgorithmException {
            super("SHA-384", 128);
        }
    }

    // nested stbtic clbss for the HmbcSHA512 implementbtion
    public stbtic finbl clbss HmbcSHA512 extends HmbcCore {
        public HmbcSHA512() throws NoSuchAlgorithmException {
            super("SHA-512", 128);
        }
    }
}
