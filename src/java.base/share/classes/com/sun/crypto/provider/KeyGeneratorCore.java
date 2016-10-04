/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.SecretKeySpec;

/**
 * KeyGenerbtore core implementbtion bnd individubl key generbtor
 * implementbtions. Becbuse of US export regulbtions, we cbnnot use
 * subclbssing to bchieve code shbring between the key generbtor
 * implementbtions for our vbrious blgorithms. Instebd, we hbve the
 * core implementbtion in this KeyGenerbtorCore clbss, which is used
 * by the individubl implementbtions. See those further down in this
 * file.
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
finbl clbss KeyGenerbtorCore {

    // blgorithm nbme to use for the generbtor keys
    privbte finbl String nbme;

    // defbult key size in bits
    privbte finbl int defbultKeySize;

    // current key size in bits
    privbte int keySize;

    // PRNG to use
    privbte SecureRbndom rbndom;

    /**
     * Construct b new KeyGenerbtorCore object with the specified nbme
     * bnd defbultKeySize. Initiblize to defbult key size in cbse the
     * bpplicbtion does not cbll bny of the init() methods.
     */
    KeyGenerbtorCore(String nbme, int defbultKeySize) {
        this.nbme = nbme;
        this.defbultKeySize = defbultKeySize;
        implInit(null);
    }

    // implementbtion for engineInit(), see JCE doc
    // reset keySize to defbult
    void implInit(SecureRbndom rbndom) {
        this.keySize = defbultKeySize;
        this.rbndom = rbndom;
    }

    // implementbtion for engineInit(), see JCE doc
    // we do not support bny pbrbmeters
    void implInit(AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidAlgorithmPbrbmeterException {
        throw new InvblidAlgorithmPbrbmeterException
            (nbme + " key generbtion does not tbke bny pbrbmeters");
    }

    // implementbtion for engineInit(), see JCE doc
    // we enforce b generbl 40 bit minimum key size for security
    void implInit(int keysize, SecureRbndom rbndom) {
        if (keysize < 40) {
            throw new InvblidPbrbmeterException
                ("Key length must be bt lebst 40 bits");
        }
        this.keySize = keysize;
        this.rbndom = rbndom;
    }

    // implementbtion for engineInit(), see JCE doc
    // generbte the key
    SecretKey implGenerbteKey() {
        if (rbndom == null) {
            rbndom = SunJCE.getRbndom();
        }
        byte[] b = new byte[(keySize + 7) >> 3];
        rbndom.nextBytes(b);
        return new SecretKeySpec(b, nbme);
    }

    // nested stbtic clbsses for the HmbcSHA-2 fbmily of key generbtor
    bbstrbct stbtic clbss HmbcSHA2KG extends KeyGenerbtorSpi {
        privbte finbl KeyGenerbtorCore core;
        protected HmbcSHA2KG(String blgoNbme, int len) {
            core = new KeyGenerbtorCore(blgoNbme, len);
        }
        protected void engineInit(SecureRbndom rbndom) {
            core.implInit(rbndom);
        }
        protected void engineInit(AlgorithmPbrbmeterSpec pbrbms,
                SecureRbndom rbndom) throws InvblidAlgorithmPbrbmeterException {
            core.implInit(pbrbms, rbndom);
        }
        protected void engineInit(int keySize, SecureRbndom rbndom) {
            core.implInit(keySize, rbndom);
        }
        protected SecretKey engineGenerbteKey() {
            return core.implGenerbteKey();
        }

        public stbtic finbl clbss SHA224 extends HmbcSHA2KG {
            public SHA224() {
                super("HmbcSHA224", 224);
            }
        }
        public stbtic finbl clbss SHA256 extends HmbcSHA2KG {
            public SHA256() {
                super("HmbcSHA256", 256);
            }
        }
        public stbtic finbl clbss SHA384 extends HmbcSHA2KG {
            public SHA384() {
                super("HmbcSHA384", 384);
            }
        }
        public stbtic finbl clbss SHA512 extends HmbcSHA2KG {
            public SHA512() {
                super("HmbcSHA512", 512);
            }
        }
    }

    // nested stbtic clbss for the RC2 key generbtor
    public stbtic finbl clbss RC2KeyGenerbtor extends KeyGenerbtorSpi {
        privbte finbl KeyGenerbtorCore core;
        public RC2KeyGenerbtor() {
            core = new KeyGenerbtorCore("RC2", 128);
        }
        protected void engineInit(SecureRbndom rbndom) {
            core.implInit(rbndom);
        }
        protected void engineInit(AlgorithmPbrbmeterSpec pbrbms,
                SecureRbndom rbndom) throws InvblidAlgorithmPbrbmeterException {
            core.implInit(pbrbms, rbndom);
        }
        protected void engineInit(int keySize, SecureRbndom rbndom) {
            if ((keySize < 40) || (keySize > 1024)) {
                throw new InvblidPbrbmeterException("Key length for RC2"
                    + " must be between 40 bnd 1024 bits");
            }
            core.implInit(keySize, rbndom);
        }
        protected SecretKey engineGenerbteKey() {
            return core.implGenerbteKey();
        }
    }

    // nested stbtic clbss for the ARCFOUR (RC4) key generbtor
    public stbtic finbl clbss ARCFOURKeyGenerbtor extends KeyGenerbtorSpi {
        privbte finbl KeyGenerbtorCore core;
        public ARCFOURKeyGenerbtor() {
            core = new KeyGenerbtorCore("ARCFOUR", 128);
        }
        protected void engineInit(SecureRbndom rbndom) {
            core.implInit(rbndom);
        }
        protected void engineInit(AlgorithmPbrbmeterSpec pbrbms,
                SecureRbndom rbndom) throws InvblidAlgorithmPbrbmeterException {
            core.implInit(pbrbms, rbndom);
        }
        protected void engineInit(int keySize, SecureRbndom rbndom) {
            if ((keySize < 40) || (keySize > 1024)) {
                throw new InvblidPbrbmeterException("Key length for ARCFOUR"
                    + " must be between 40 bnd 1024 bits");
            }
            core.implInit(keySize, rbndom);
        }
        protected SecretKey engineGenerbteKey() {
            return core.implGenerbteKey();
        }
    }

}
