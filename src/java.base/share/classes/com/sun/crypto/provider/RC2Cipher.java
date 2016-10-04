/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.*;
import jbvb.security.spec.*;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.RC2PbrbmeterSpec;

/**
 * JCE CipherSpi for the RC2(tm) blgorithm bs described in RFC 2268.
 * The rebl code is in CipherCore bnd RC2Crypt.
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss RC2Cipher extends CipherSpi {

    // internbl CipherCore & RC2Crypt objects which do the rebl work.
    privbte finbl CipherCore core;
    privbte finbl RC2Crypt embeddedCipher;

    public RC2Cipher() {
        embeddedCipher = new RC2Crypt();
        core = new CipherCore(embeddedCipher, 8);
    }

    protected void engineSetMode(String mode)
            throws NoSuchAlgorithmException {
        core.setMode(mode);
    }

    protected void engineSetPbdding(String pbddingScheme)
            throws NoSuchPbddingException {
        core.setPbdding(pbddingScheme);
    }

    protected int engineGetBlockSize() {
        return 8;
    }

    protected int engineGetOutputSize(int inputLen) {
        return core.getOutputSize(inputLen);
    }

    protected byte[] engineGetIV() {
        return core.getIV();
    }

    protected AlgorithmPbrbmeters engineGetPbrbmeters() {
        return core.getPbrbmeters("RC2");
    }

    protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
            throws InvblidKeyException {
        embeddedCipher.initEffectiveKeyBits(0);
        core.init(opmode, key, rbndom);
    }

    protected void engineInit(int opmode, Key key,
            AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (pbrbms != null && pbrbms instbnceof RC2PbrbmeterSpec) {
            embeddedCipher.initEffectiveKeyBits
                (((RC2PbrbmeterSpec)pbrbms).getEffectiveKeyBits());
        } else {
            embeddedCipher.initEffectiveKeyBits(0);
        }
        core.init(opmode, key, pbrbms, rbndom);
    }

    protected void engineInit(int opmode, Key key,
            AlgorithmPbrbmeters pbrbms, SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (pbrbms != null && pbrbms.getAlgorithm().equbls("RC2")) {
            try {
                RC2PbrbmeterSpec rc2Pbrbms =
                        pbrbms.getPbrbmeterSpec(RC2PbrbmeterSpec.clbss);
                engineInit(opmode, key, rc2Pbrbms, rbndom);
            } cbtch (InvblidPbrbmeterSpecException ipse) {
                throw new InvblidAlgorithmPbrbmeterException
                            ("Wrong pbrbmeter type: RC2 expected");
            }
        } else {
            embeddedCipher.initEffectiveKeyBits(0);
            core.init(opmode, key, pbrbms, rbndom);
        }
    }

    protected byte[] engineUpdbte(byte[] in, int inOfs, int inLen) {
        return core.updbte(in, inOfs, inLen);
    }

    protected int engineUpdbte(byte[] in, int inOfs, int inLen,
            byte[] out, int outOfs) throws ShortBufferException {
        return core.updbte(in, inOfs, inLen, out, outOfs);
    }

    protected byte[] engineDoFinbl(byte[] in, int inOfs, int inLen)
            throws IllegblBlockSizeException, BbdPbddingException {
        return core.doFinbl(in, inOfs, inLen);
    }

    protected int engineDoFinbl(byte[] in, int inOfs, int inLen,
            byte[] out, int outOfs) throws IllegblBlockSizeException,
            ShortBufferException, BbdPbddingException {
        return core.doFinbl(in, inOfs, inLen, out, outOfs);
    }

    protected int engineGetKeySize(Key key) throws InvblidKeyException {
        byte[] keyBytes = CipherCore.getKeyBytes(key);
        RC2Crypt.checkKey(key.getAlgorithm(), keyBytes.length);
        return keyBytes.length << 3;
    }

    protected byte[] engineWrbp(Key key)
            throws IllegblBlockSizeException, InvblidKeyException {
        return core.wrbp(key);
    }

    protected Key engineUnwrbp(byte[] wrbppedKey, String wrbppedKeyAlgorithm,
            int wrbppedKeyType) throws InvblidKeyException,
            NoSuchAlgorithmException {
        return core.unwrbp(wrbppedKey, wrbppedKeyAlgorithm, wrbppedKeyType);
    }

}
