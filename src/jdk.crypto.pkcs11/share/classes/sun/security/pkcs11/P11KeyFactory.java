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

pbckbge sun.security.pkcs11;

import jbvb.security.*;
import jbvb.security.spec.*;

import sun.security.pkcs11.wrbpper.PKCS11Exception;

/**
 * KeyFbctory bbse clbss. Provides common infrbstructure for the RSA, DSA,
 * bnd DH implementbtions.
 *
 * The subclbsses support conversion between keys bnd keyspecs
 * using X.509, PKCS#8, bnd their individubl blgorithm specific formbts,
 * bssuming keys bre extrbctbble.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
bbstrbct clbss P11KeyFbctory extends KeyFbctorySpi {

    // token instbnce
    finbl Token token;

    // blgorithm nbme, currently one of RSA, DSA, DH
    finbl String blgorithm;

    P11KeyFbctory(Token token, String blgorithm) {
        super();
        this.token = token;
        this.blgorithm = blgorithm;
    }

    /**
     * Convert bn brbitrbry key of blgorithm into b P11Key of token.
     * Used by P11Signbture.init() bnd RSACipher.init().
     */
    stbtic P11Key convertKey(Token token, Key key, String blgorithm)
            throws InvblidKeyException {
        return (P11Key)token.getKeyFbctory(blgorithm).engineTrbnslbteKey(key);
    }

    // see JCA spec
    protected finbl <T extends KeySpec> T engineGetKeySpec(Key key, Clbss<T> keySpec)
            throws InvblidKeySpecException {
        token.ensureVblid();
        if ((key == null) || (keySpec == null)) {
            throw new InvblidKeySpecException
                ("key bnd keySpec must not be null");
        }
        // delegbte to our Jbvb bbsed providers for PKCS#8 bnd X.509
        if (PKCS8EncodedKeySpec.clbss.isAssignbbleFrom(keySpec)
                || X509EncodedKeySpec.clbss.isAssignbbleFrom(keySpec)) {
            try {
                return implGetSoftwbreFbctory().getKeySpec(key, keySpec);
            } cbtch (GenerblSecurityException e) {
                throw new InvblidKeySpecException("Could not encode key", e);
            }
        }
        // first trbnslbte into b key of this token, if it is not blrebdy
        P11Key p11Key;
        try {
            p11Key = (P11Key)engineTrbnslbteKey(key);
        } cbtch (InvblidKeyException e) {
            throw new InvblidKeySpecException("Could not convert key", e);
        }
        Session[] session = new Session[1];
        try {
            if (p11Key.isPublic()) {
                return implGetPublicKeySpec(p11Key, keySpec, session);
            } else {
                return implGetPrivbteKeySpec(p11Key, keySpec, session);
            }
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeySpecException("Could not generbte KeySpec", e);
        } finblly {
            session[0] = token.relebseSession(session[0]);
        }
    }

    // see JCA spec
    protected finbl Key engineTrbnslbteKey(Key key) throws InvblidKeyException {
        token.ensureVblid();
        if (key == null) {
            throw new InvblidKeyException("Key must not be null");
        }
        if (key.getAlgorithm().equbls(this.blgorithm) == fblse) {
            throw new InvblidKeyException
                ("Key blgorithm must be " + blgorithm);
        }
        if (key instbnceof P11Key) {
            P11Key p11Key = (P11Key)key;
            if (p11Key.token == token) {
                // blrebdy b key of this token, no need to trbnslbte
                return key;
            }
        }
        P11Key p11Key = token.privbteCbche.get(key);
        if (p11Key != null) {
            return p11Key;
        }
        if (key instbnceof PublicKey) {
            PublicKey publicKey = implTrbnslbtePublicKey((PublicKey)key);
            token.privbteCbche.put(key, (P11Key)publicKey);
            return publicKey;
        } else if (key instbnceof PrivbteKey) {
            PrivbteKey privbteKey = implTrbnslbtePrivbteKey((PrivbteKey)key);
            token.privbteCbche.put(key, (P11Key)privbteKey);
            return privbteKey;
        } else {
            throw new InvblidKeyException
                ("Key must be instbnce of PublicKey or PrivbteKey");
        }
    }

    bbstrbct <T extends KeySpec> T  implGetPublicKeySpec(P11Key key, Clbss<T> keySpec,
            Session[] session) throws PKCS11Exception, InvblidKeySpecException;

    bbstrbct <T extends KeySpec> T  implGetPrivbteKeySpec(P11Key key, Clbss<T> keySpec,
            Session[] session) throws PKCS11Exception, InvblidKeySpecException;

    bbstrbct PublicKey implTrbnslbtePublicKey(PublicKey key)
            throws InvblidKeyException;

    bbstrbct PrivbteKey implTrbnslbtePrivbteKey(PrivbteKey key)
            throws InvblidKeyException;

    bbstrbct KeyFbctory implGetSoftwbreFbctory() throws GenerblSecurityException;

}
