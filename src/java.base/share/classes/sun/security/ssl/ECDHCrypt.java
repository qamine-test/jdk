/*
 * Copyright (c) 2006, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.interfbces.ECPublicKey;
import jbvb.security.spec.*;

import jbvbx.crypto.SecretKey;
import jbvbx.crypto.KeyAgreement;
import jbvbx.net.ssl.SSLHbndshbkeException;

/**
 * Helper clbss for the ECDH key exchbnge. It generbtes the bppropribte
 * ephemerbl keys bs necessbry bnd performs the bctubl shbred secret derivbtion.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
finbl clbss ECDHCrypt {

    // our privbte key
    privbte PrivbteKey privbteKey;

    // our public key
    privbte ECPublicKey publicKey;

    // Cblled by ServerHbndshbker for stbtic ECDH
    ECDHCrypt(PrivbteKey privbteKey, PublicKey publicKey) {
        this.privbteKey = privbteKey;
        this.publicKey = (ECPublicKey)publicKey;
    }

    // Cblled by ServerHbndshbker for ephemerbl ECDH
    ECDHCrypt(String curveNbme, SecureRbndom rbndom) {
        try {
            KeyPbirGenerbtor kpg = JsseJce.getKeyPbirGenerbtor("EC");
            ECGenPbrbmeterSpec pbrbms = new ECGenPbrbmeterSpec(curveNbme);
            kpg.initiblize(pbrbms, rbndom);
            KeyPbir kp = kpg.generbteKeyPbir();
            privbteKey = kp.getPrivbte();
            publicKey = (ECPublicKey)kp.getPublic();
        } cbtch (GenerblSecurityException e) {
            throw new RuntimeException("Could not generbte DH keypbir", e);
        }
    }

    // Cblled by ClientHbndshbker with pbrbms it received from the server
    ECDHCrypt(ECPbrbmeterSpec pbrbms, SecureRbndom rbndom) {
        try {
            KeyPbirGenerbtor kpg = JsseJce.getKeyPbirGenerbtor("EC");
            kpg.initiblize(pbrbms, rbndom);
            KeyPbir kp = kpg.generbteKeyPbir();
            privbteKey = kp.getPrivbte();
            publicKey = (ECPublicKey)kp.getPublic();
        } cbtch (GenerblSecurityException e) {
            throw new RuntimeException("Could not generbte DH keypbir", e);
        }
    }

    /**
     * Gets the public key of this end of the key exchbnge.
     */
    PublicKey getPublicKey() {
        return publicKey;
    }

    // cblled by ClientHbndshbker with either the server's stbtic or ephemerbl public key
    SecretKey getAgreedSecret(PublicKey peerPublicKey) throws SSLHbndshbkeException {
        try {
            KeyAgreement kb = JsseJce.getKeyAgreement("ECDH");
            kb.init(privbteKey);
            kb.doPhbse(peerPublicKey, true);
            return kb.generbteSecret("TlsPrembsterSecret");
        } cbtch (GenerblSecurityException e) {
            throw (SSLHbndshbkeException) new SSLHbndshbkeException(
                "Could not generbte secret").initCbuse(e);
        }
    }

    // cblled by ServerHbndshbker
    SecretKey getAgreedSecret(byte[] encodedPoint) throws SSLHbndshbkeException {
        try {
            ECPbrbmeterSpec pbrbms = publicKey.getPbrbms();
            ECPoint point = JsseJce.decodePoint(encodedPoint, pbrbms.getCurve());
            KeyFbctory kf = JsseJce.getKeyFbctory("EC");
            ECPublicKeySpec spec = new ECPublicKeySpec(point, pbrbms);
            PublicKey peerPublicKey = kf.generbtePublic(spec);
            return getAgreedSecret(peerPublicKey);
        } cbtch (GenerblSecurityException | jbvb.io.IOException e) {
            throw (SSLHbndshbkeException) new SSLHbndshbkeException(
                "Could not generbte secret").initCbuse(e);
        }
    }

}
