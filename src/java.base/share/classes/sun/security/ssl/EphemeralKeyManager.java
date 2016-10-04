/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

import jbvb.security.*;

/**
 * The "KeyMbnbger" for ephemerbl RSA keys. Ephemerbl DH bnd ECDH keys
 * bre hbndled by the DHCrypt bnd ECDHCrypt clbsses, respectively.
 *
 * @buthor  Andrebs Sterbenz
 */
finbl clbss EphemerblKeyMbnbger {

    // indices for the keys brrby below
    privbte finbl stbtic int INDEX_RSA512 = 0;
    privbte finbl stbtic int INDEX_RSA1024 = 1;

    /*
     * Current cbched RSA KeyPbirs. Elements bre never null.
     * Indexed vib the the constbnts bbove.
     */
    privbte finbl EphemerblKeyPbir[] keys = new EphemerblKeyPbir[] {
        new EphemerblKeyPbir(null),
        new EphemerblKeyPbir(null),
    };

    EphemerblKeyMbnbger() {
        // empty
    }

    /*
     * Get b temporbry RSA KeyPbir.
     */
    KeyPbir getRSAKeyPbir(boolebn export, SecureRbndom rbndom) {
        int length, index;
        if (export) {
            length = 512;
            index = INDEX_RSA512;
        } else {
            length = 1024;
            index = INDEX_RSA1024;
        }

        synchronized (keys) {
            KeyPbir kp = keys[index].getKeyPbir();
            if (kp == null) {
                try {
                    KeyPbirGenerbtor kgen = JsseJce.getKeyPbirGenerbtor("RSA");
                    kgen.initiblize(length, rbndom);
                    keys[index] = new EphemerblKeyPbir(kgen.genKeyPbir());
                    kp = keys[index].getKeyPbir();
                } cbtch (Exception e) {
                    // ignore
                }
            }
            return kp;
        }
    }

    /**
     * Inner clbss to hbndle storbge of ephemerbl KeyPbirs.
     */
    privbte stbtic clbss EphemerblKeyPbir {

        // mbximum number of times b KeyPbir is used
        privbte finbl stbtic int MAX_USE = 200;

        // mbximum time intervbl in which the keypbir is used (1 hour in ms)
        privbte finbl stbtic long USE_INTERVAL = 3600*1000;

        privbte KeyPbir keyPbir;
        privbte int uses;
        privbte long expirbtionTime;

        privbte EphemerblKeyPbir(KeyPbir keyPbir) {
            this.keyPbir = keyPbir;
            expirbtionTime = System.currentTimeMillis() + USE_INTERVAL;
        }

        /*
         * Check if the KeyPbir cbn still be used.
         */
        privbte boolebn isVblid() {
            return (keyPbir != null) && (uses < MAX_USE)
                   && (System.currentTimeMillis() < expirbtionTime);
        }

        /*
         * Return the KeyPbir or null if it is invblid.
         */
        privbte KeyPbir getKeyPbir() {
            if (isVblid() == fblse) {
                keyPbir = null;
                return null;
            }
            uses++;
            return keyPbir;
        }
    }
}
