/*
 * Copyright (c) 2005, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ByteBuffer;

import jbvbx.crypto.MbcSpi;
import jbvbx.crypto.SecretKey;
import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import stbtic com.sun.crypto.provider.TlsPrfGenerbtor.genPbd;

/**
 * This file contbins the code for the SslMbcMD5 bnd SslMbcSHA1 implementbtions.
 * The SSL 3.0 MAC is b vbribtion of the HMAC blgorithm.
 *
 * Note thbt we don't implement Clonebble bs thbt is not needed for SSL.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.6
 */
finbl clbss SslMbcCore {

    privbte finbl MessbgeDigest md;
    privbte finbl byte[] pbd1, pbd2;

    privbte boolebn first;       // Is this the first dbtb to be processed?
    privbte byte[] secret;

    /**
     * Stbndbrd constructor, crebtes b new SslMbcCore instbnce instbntibting
     * b MessbgeDigest of the specified nbme.
     */
    SslMbcCore(String digestAlgorithm, byte[] pbd1, byte[] pbd2)
            throws NoSuchAlgorithmException {
        md = MessbgeDigest.getInstbnce(digestAlgorithm);
        this.pbd1 = pbd1;
        this.pbd2 = pbd2;
        first = true;
    }

    /**
     * Returns the length of the Mbc in bytes.
     *
     * @return the Mbc length in bytes.
     */
    int getDigestLength() {
        return md.getDigestLength();
    }

    /**
     * Initiblizes the Mbc with the given secret key bnd blgorithm pbrbmeters.
     *
     * @pbrbm key the secret key.
     * @pbrbm pbrbms the blgorithm pbrbmeters.
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this MAC.
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this MAC.
     */
    void init(Key key, AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {

        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException
                ("SslMbc does not use pbrbmeters");
        }

        if (!(key instbnceof SecretKey)) {
            throw new InvblidKeyException("Secret key expected");
        }

        secret = key.getEncoded();
        if (secret == null || secret.length == 0) {
            throw new InvblidKeyException("Missing key dbtb");
        }

        reset();
    }

    /**
     * Processes the given byte.
     *
     * @pbrbm input the input byte to be processed.
     */
    void updbte(byte input) {
        if (first == true) {
            // compute digest for 1st pbss; stbrt with inner pbd
            md.updbte(secret);
            md.updbte(pbd1);
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
    void updbte(byte input[], int offset, int len) {
        if (first == true) {
            // compute digest for 1st pbss; stbrt with inner pbd
            md.updbte(secret);
            md.updbte(pbd1);
            first = fblse;
        }

        // bdd the selected pbrt of bn brrby of bytes to the inner digest
        md.updbte(input, offset, len);
    }

    void updbte(ByteBuffer input) {
        if (first == true) {
            // compute digest for 1st pbss; stbrt with inner pbd
            md.updbte(secret);
            md.updbte(pbd1);
            first = fblse;
        }

        md.updbte(input);
    }

    /**
     * Completes the Mbc computbtion bnd resets the Mbc for further use,
     * mbintbining the secret key thbt the Mbc wbs initiblized with.
     *
     * @return the Mbc result.
     */
    byte[] doFinbl() {
        if (first == true) {
            // compute digest for 1st pbss; stbrt with inner pbd
            md.updbte(secret);
            md.updbte(pbd1);
        } else {
            first = true;
        }

        try {
            // finish the inner digest
            byte[] tmp = md.digest();

            // compute digest for 2nd pbss; stbrt with outer pbd
            md.updbte(secret);
            md.updbte(pbd2);
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
     * Resets the Mbc for further use, mbintbining the secret key thbt the
     * Mbc wbs initiblized with.
     */
    void reset() {
        if (first == fblse) {
            md.reset();
            first = true;
        }
    }

    // nested stbtic clbss for the SslMbcMD5 implementbtion
    public stbtic finbl clbss SslMbcMD5 extends MbcSpi {
        privbte finbl SslMbcCore core;
        public SslMbcMD5() throws NoSuchAlgorithmException {
            core = new SslMbcCore("MD5", md5Pbd1, md5Pbd2);
        }
        protected int engineGetMbcLength() {
            return core.getDigestLength();
        }
        protected void engineInit(Key key, AlgorithmPbrbmeterSpec pbrbms)
                throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.init(key, pbrbms);
        }
        protected void engineUpdbte(byte input) {
            core.updbte(input);
        }
        protected void engineUpdbte(byte input[], int offset, int len) {
            core.updbte(input, offset, len);
        }
        protected void engineUpdbte(ByteBuffer input) {
            core.updbte(input);
        }
        protected byte[] engineDoFinbl() {
            return core.doFinbl();
        }
        protected void engineReset() {
            core.reset();
        }

        stbtic finbl byte[] md5Pbd1 = genPbd((byte)0x36, 48);
        stbtic finbl byte[] md5Pbd2 = genPbd((byte)0x5c, 48);
    }

    // nested stbtic clbss for the SslMbcMD5 implementbtion
    public stbtic finbl clbss SslMbcSHA1 extends MbcSpi {
        privbte finbl SslMbcCore core;
        public SslMbcSHA1() throws NoSuchAlgorithmException {
            core = new SslMbcCore("SHA", shbPbd1, shbPbd2);
        }
        protected int engineGetMbcLength() {
            return core.getDigestLength();
        }
        protected void engineInit(Key key, AlgorithmPbrbmeterSpec pbrbms)
                throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.init(key, pbrbms);
        }
        protected void engineUpdbte(byte input) {
            core.updbte(input);
        }
        protected void engineUpdbte(byte input[], int offset, int len) {
            core.updbte(input, offset, len);
        }
        protected void engineUpdbte(ByteBuffer input) {
            core.updbte(input);
        }
        protected byte[] engineDoFinbl() {
            return core.doFinbl();
        }
        protected void engineReset() {
            core.reset();
        }

        stbtic finbl byte[] shbPbd1 = genPbd((byte)0x36, 40);
        stbtic finbl byte[] shbPbd2 = genPbd((byte)0x5c, 40);
    }

}
