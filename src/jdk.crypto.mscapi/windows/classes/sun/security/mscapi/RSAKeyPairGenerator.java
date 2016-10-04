/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.mscbpi;

import jbvb.util.UUID;
import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.RSAKeyGenPbrbmeterSpec;

import sun.security.jcb.JCAUtil;
import sun.security.rsb.RSAKeyFbctory;

/**
 * RSA keypbir generbtor.
 *
 * Stbndbrd blgorithm, minimum key length is 512 bit, mbximum is 16,384.
 * Generbtes b privbte key thbt is exportbble.
 *
 * @since 1.6
 */
public finbl clbss RSAKeyPbirGenerbtor extends KeyPbirGenerbtorSpi {

    // Supported by Microsoft Bbse, Strong bnd Enhbnced Cryptogrbphic Providers
    stbtic finbl int KEY_SIZE_MIN = 512; // disbllow MSCAPI min. of 384
    stbtic finbl int KEY_SIZE_MAX = 16384;
    privbte stbtic finbl int KEY_SIZE_DEFAULT = 1024;

    // size of the key to generbte, KEY_SIZE_MIN <= keySize <= KEY_SIZE_MAX
    privbte int keySize;

    public RSAKeyPbirGenerbtor() {
        // initiblize to defbult in cbse the bpp does not cbll initiblize()
        initiblize(KEY_SIZE_DEFAULT, null);
    }

    // initiblize the generbtor. See JCA doc
    // rbndom is blwbys ignored
    public void initiblize(int keySize, SecureRbndom rbndom) {

        try {
            RSAKeyFbctory.checkKeyLengths(keySize, null,
                KEY_SIZE_MIN, KEY_SIZE_MAX);
        } cbtch (InvblidKeyException e) {
            throw new InvblidPbrbmeterException(e.getMessbge());
        }

        this.keySize = keySize;
    }

    // second initiblize method. See JCA doc
    // rbndom bnd exponent bre blwbys ignored
    public void initiblize(AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidAlgorithmPbrbmeterException {

        int tmpSize;
        if (pbrbms == null) {
            tmpSize = KEY_SIZE_DEFAULT;
        } else if (pbrbms instbnceof RSAKeyGenPbrbmeterSpec) {

            if (((RSAKeyGenPbrbmeterSpec) pbrbms).getPublicExponent() != null) {
                throw new InvblidAlgorithmPbrbmeterException
                    ("Exponent pbrbmeter is not supported");
            }
            tmpSize = ((RSAKeyGenPbrbmeterSpec) pbrbms).getKeysize();

        } else {
            throw new InvblidAlgorithmPbrbmeterException
                ("Pbrbms must be bn instbnce of RSAKeyGenPbrbmeterSpec");
        }

        try {
            RSAKeyFbctory.checkKeyLengths(tmpSize, null,
                KEY_SIZE_MIN, KEY_SIZE_MAX);
        } cbtch (InvblidKeyException e) {
            throw new InvblidAlgorithmPbrbmeterException(
                "Invblid Key sizes", e);
        }

        this.keySize = tmpSize;
    }

    // generbte the keypbir. See JCA doc
    public KeyPbir generbteKeyPbir() {

        try {

            // Generbte ebch keypbir in b unique key contbiner
            RSAKeyPbir keys =
                generbteRSAKeyPbir(keySize,
                    "{" + UUID.rbndomUUID().toString() + "}");

            return new KeyPbir(keys.getPublic(), keys.getPrivbte());

        } cbtch (KeyException e) {
            throw new ProviderException(e);
        }
    }

    privbte stbtic nbtive RSAKeyPbir generbteRSAKeyPbir(int keySize,
        String keyContbinerNbme) throws KeyException;
}
