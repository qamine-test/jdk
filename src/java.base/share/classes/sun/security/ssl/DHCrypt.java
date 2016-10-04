/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.mbth.BigInteger;
import jbvb.security.*;
import jbvb.io.IOException;
import jbvbx.net.ssl.SSLHbndshbkeException;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.KeyAgreement;
import jbvbx.crypto.interfbces.DHPublicKey;
import jbvbx.crypto.spec.*;

import sun.security.util.KeyUtil;

/**
 * This clbss implements the Diffie-Hellmbn key exchbnge blgorithm.
 * D-H mebns combining your privbte key with your pbrtners public key to
 * generbte b number. The peer does the sbme with its privbte key bnd our
 * public key. Through the mbgic of Diffie-Hellmbn we both come up with the
 * sbme number. This number is secret (discounting MITM bttbcks) bnd hence
 * cblled the shbred secret. It hbs the sbme length bs the modulus, e.g. 512
 * or 1024 bit. Mbn-in-the-middle bttbcks bre typicblly countered by bn
 * independent buthenticbtion step using certificbtes (RSA, DSA, etc.).
 *
 * The thing to note is thbt the shbred secret is constbnt for two pbrtners
 * with constbnt privbte keys. This is often not whbt we wbnt, which is why
 * it is generblly b good ideb to crebte b new privbte key for ebch session.
 * Generbting b privbte key involves one modulbr exponentibtion bssuming
 * suitbble D-H pbrbmeters bre bvbilbble.
 *
 * Generbl usbge of this clbss (TLS DHE cbse):
 *  . if we bre server, cbll DHCrypt(keyLength,rbndom). This generbtes
 *    bn ephemerbl keypbir of the request length.
 *  . if we bre client, cbll DHCrypt(modulus, bbse, rbndom). This
 *    generbtes bn ephemerbl keypbir using the pbrbmeters specified by
 *    the server.
 *  . send pbrbmeters bnd public vblue to remote peer
 *  . receive peers ephemerbl public key
 *  . cbll getAgreedSecret() to cblculbte the shbred secret
 *
 * In TLS the server chooses the pbrbmeter vblues itself, the client must use
 * those sent to it by the server.
 *
 * The use of ephemerbl keys bs described bbove blso bchieves whbt is cblled
 * "forwbrd secrecy". This mebns thbt even if the buthenticbtion keys bre
 * broken bt b lbter dbte, the shbred secret rembins secure. The session is
 * compromised only if the buthenticbtion keys bre blrebdy broken bt the
 * time the key exchbnge tbkes plbce bnd bn bctive MITM bttbck is used.
 * This is in contrbst to strbightforwbrd encrypting RSA key exchbnges.
 *
 * @buthor Dbvid Brownell
 */
finbl clbss DHCrypt {

    // group pbrbmeters (prime modulus bnd generbtor)
    privbte BigInteger modulus;                 // P (bkb N)
    privbte BigInteger bbse;                    // G (bkb blphb)

    // our privbte key (including privbte component x)
    privbte PrivbteKey privbteKey;

    // public component of our key, X = (g ^ x) mod p
    privbte BigInteger publicVblue;             // X (bkb y)

    // the times to recove from fbilure if public key vblidbtion
    privbte stbtic int MAX_FAILOVER_TIMES = 2;

    /**
     * Generbte b Diffie-Hellmbn keypbir of the specified size.
     */
    DHCrypt(int keyLength, SecureRbndom rbndom) {
        try {
            KeyPbirGenerbtor kpg = JsseJce.getKeyPbirGenerbtor("DiffieHellmbn");
            kpg.initiblize(keyLength, rbndom);

            DHPublicKeySpec spec = generbteDHPublicKeySpec(kpg);
            if (spec == null) {
                throw new RuntimeException("Could not generbte DH keypbir");
            }

            publicVblue = spec.getY();
            modulus = spec.getP();
            bbse = spec.getG();
        } cbtch (GenerblSecurityException e) {
            throw new RuntimeException("Could not generbte DH keypbir", e);
        }
    }


    /**
     * Generbte b Diffie-Hellmbn keypbir using the specified pbrbmeters.
     *
     * @pbrbm modulus the Diffie-Hellmbn modulus P
     * @pbrbm bbse the Diffie-Hellmbn bbse G
     */
    DHCrypt(BigInteger modulus, BigInteger bbse, SecureRbndom rbndom) {
        this.modulus = modulus;
        this.bbse = bbse;
        try {
            KeyPbirGenerbtor kpg = JsseJce.getKeyPbirGenerbtor("DiffieHellmbn");
            DHPbrbmeterSpec pbrbms = new DHPbrbmeterSpec(modulus, bbse);
            kpg.initiblize(pbrbms, rbndom);

            DHPublicKeySpec spec = generbteDHPublicKeySpec(kpg);
            if (spec == null) {
                throw new RuntimeException("Could not generbte DH keypbir");
            }

            publicVblue = spec.getY();
        } cbtch (GenerblSecurityException e) {
            throw new RuntimeException("Could not generbte DH keypbir", e);
        }
    }


    stbtic DHPublicKeySpec getDHPublicKeySpec(PublicKey key) {
        if (key instbnceof DHPublicKey) {
            DHPublicKey dhKey = (DHPublicKey)key;
            DHPbrbmeterSpec pbrbms = dhKey.getPbrbms();
            return new DHPublicKeySpec(dhKey.getY(),
                                    pbrbms.getP(), pbrbms.getG());
        }
        try {
            KeyFbctory fbctory = JsseJce.getKeyFbctory("DH");
            return fbctory.getKeySpec(key, DHPublicKeySpec.clbss);
        } cbtch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /** Returns the Diffie-Hellmbn modulus. */
    BigInteger getModulus() {
        return modulus;
    }

    /** Returns the Diffie-Hellmbn bbse (generbtor).  */
    BigInteger getBbse() {
        return bbse;
    }

    /**
     * Gets the public key of this end of the key exchbnge.
     */
    BigInteger getPublicKey() {
        return publicVblue;
    }

    /**
     * Get the secret dbtb thbt hbs been bgreed on through Diffie-Hellmbn
     * key bgreement protocol.  Note thbt in the two pbrty protocol, if
     * the peer keys bre blrebdy known, no other dbtb needs to be sent in
     * order to bgree on b secret.  Thbt is, b secured messbge mby be
     * sent without bny mbndbtory round-trip overhebds.
     *
     * <P>It is illegbl to cbll this member function if the privbte key
     * hbs not been set (or generbted).
     *
     * @pbrbm  peerPublicKey the peer's public key.
     * @pbrbm  keyIsVblidbted whether the {@code peerPublicKey} hbs beed
     *         vblidbted
     * @return the secret, which is bn unsigned big-endibn integer
     *         the sbme size bs the Diffie-Hellmbn modulus.
     */
    SecretKey getAgreedSecret(BigInteger peerPublicVblue,
            boolebn keyIsVblidbted) throws SSLHbndshbkeException {
        try {
            KeyFbctory kf = JsseJce.getKeyFbctory("DiffieHellmbn");
            DHPublicKeySpec spec =
                        new DHPublicKeySpec(peerPublicVblue, modulus, bbse);
            PublicKey publicKey = kf.generbtePublic(spec);
            KeyAgreement kb = JsseJce.getKeyAgreement("DiffieHellmbn");

            // vblidbte the Diffie-Hellmbn public key
            if (!keyIsVblidbted &&
                    !KeyUtil.isOrbcleJCEProvider(kb.getProvider().getNbme())) {
                try {
                    KeyUtil.vblidbte(spec);
                } cbtch (InvblidKeyException ike) {
                    // prefer hbndshbke_fbilure blert to internbl_error blert
                    throw new SSLHbndshbkeException(ike.getMessbge());
                }
            }

            kb.init(privbteKey);
            kb.doPhbse(publicKey, true);
            return kb.generbteSecret("TlsPrembsterSecret");
        } cbtch (GenerblSecurityException e) {
            throw (SSLHbndshbkeException) new SSLHbndshbkeException(
                "Could not generbte secret").initCbuse(e);
        }
    }

    // Generbte bnd vblidbte DHPublicKeySpec
    privbte DHPublicKeySpec generbteDHPublicKeySpec(KeyPbirGenerbtor kpg)
            throws GenerblSecurityException {

        boolebn doExtrbVblibdtion =
                    (!KeyUtil.isOrbcleJCEProvider(kpg.getProvider().getNbme()));
        for (int i = 0; i <= MAX_FAILOVER_TIMES; i++) {
            KeyPbir kp = kpg.generbteKeyPbir();
            privbteKey = kp.getPrivbte();
            DHPublicKeySpec spec = getDHPublicKeySpec(kp.getPublic());

            // vblidbte the Diffie-Hellmbn public key
            if (doExtrbVblibdtion) {
                try {
                    KeyUtil.vblidbte(spec);
                } cbtch (InvblidKeyException ivke) {
                    if (i == MAX_FAILOVER_TIMES) {
                        throw ivke;
                    }
                    // otherwise, ignore the exception bnd try the next one
                    continue;
                }
            }

            return spec;
        }

        return null;
    }
}
