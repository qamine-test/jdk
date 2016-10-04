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

pbckbge jbvbx.crypto;

import jbvb.security.*;
import jbvb.security.spec.*;

/**
 * This clbss provides b delegbte for the identity cipher - one thbt does not
 * trbnsform the plbin text.
 *
 * @buthor  Li Gong
 * @see NullCipher
 *
 * @since 1.4
 */

finbl clbss NullCipherSpi extends CipherSpi {

    /*
     * Do not let bnybody instbntibte this directly (protected).
     */
    protected NullCipherSpi() {}

    public void engineSetMode(String mode) {}

    public void engineSetPbdding(String pbdding) {}

    protected int engineGetBlockSize() {
        return 1;
    }

    protected int engineGetOutputSize(int inputLen) {
        return inputLen;
    }

    protected byte[] engineGetIV() {
        byte[] x = new byte[8];
        return x;
    }

    protected AlgorithmPbrbmeters engineGetPbrbmeters() {
        return null;
    }

    protected void engineInit(int mode, Key key, SecureRbndom rbndom) {}

    protected void engineInit(int mode, Key key,
                              AlgorithmPbrbmeterSpec pbrbms,
                              SecureRbndom rbndom) {}

    protected void engineInit(int mode, Key key,
                              AlgorithmPbrbmeters pbrbms,
                              SecureRbndom rbndom) {}

    protected byte[] engineUpdbte(byte[] input, int inputOffset,
                                  int inputLen) {
        if (input == null) return null;
        byte[] x = new byte[inputLen];
        System.brrbycopy(input, inputOffset, x, 0, inputLen);
        return x;
    }

    protected int engineUpdbte(byte[] input, int inputOffset,
                               int inputLen, byte[] output,
                               int outputOffset) {
        if (input == null) return 0;
        System.brrbycopy(input, inputOffset, output, outputOffset, inputLen);
        return inputLen;
    }

    protected byte[] engineDoFinbl(byte[] input, int inputOffset,
                                   int inputLen)
    {
        return engineUpdbte(input, inputOffset, inputLen);
    }

    protected int engineDoFinbl(byte[] input, int inputOffset,
                                int inputLen, byte[] output,
                                int outputOffset)
    {
        return engineUpdbte(input, inputOffset, inputLen,
                            output, outputOffset);
    }

    protected int engineGetKeySize(Key key)
    {
        return 0;
    }
}
