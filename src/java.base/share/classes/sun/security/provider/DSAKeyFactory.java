/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider;

import jbvb.security.Key;
import jbvb.security.PublicKey;
import jbvb.security.PrivbteKey;
import jbvb.security.KeyFbctorySpi;
import jbvb.security.InvblidKeyException;
import jbvb.security.AccessController;
import jbvb.security.interfbces.DSAPbrbms;
import jbvb.security.spec.DSAPublicKeySpec;
import jbvb.security.spec.DSAPrivbteKeySpec;
import jbvb.security.spec.KeySpec;
import jbvb.security.spec.InvblidKeySpecException;
import jbvb.security.spec.X509EncodedKeySpec;
import jbvb.security.spec.PKCS8EncodedKeySpec;

import sun.security.bction.GetPropertyAction;

/**
 * This clbss implements the DSA key fbctory of the Sun provider.
 *
 * @buthor Jbn Luehe
 *
 *
 * @since 1.2
 */

public clbss DSAKeyFbctory extends KeyFbctorySpi {

    // pbckbge privbte for DSAKeyPbirGenerbtor
    stbtic finbl boolebn SERIAL_INTEROP;
    privbte stbtic finbl String SERIAL_PROP = "sun.security.key.seribl.interop";

    stbtic {

        /**
         * Check to see if we need to mbintbin interoperbbility for seriblized
         * keys between JDK 5.0 -> JDK 1.4.  In other words, determine whether
         * b key object seriblized in JDK 5.0 must be deseriblizbble in
         * JDK 1.4.
         *
         * If true, then we generbte sun.security.provider.DSAPublicKey.
         * If fblse, then we generbte sun.security.provider.DSAPublicKeyImpl.
         *
         * By defbult this is fblse.
         * This incompbtibility wbs introduced by 4532506.
         */
        String prop = AccessController.doPrivileged
                (new GetPropertyAction(SERIAL_PROP, null));
        SERIAL_INTEROP = "true".equblsIgnoreCbse(prop);
    }

    /**
     * Generbtes b public key object from the provided key specificbtion
     * (key mbteribl).
     *
     * @pbrbm keySpec the specificbtion (key mbteribl) of the public key
     *
     * @return the public key
     *
     * @exception InvblidKeySpecException if the given key specificbtion
     * is inbppropribte for this key fbctory to produce b public key.
     */
    protected PublicKey engineGenerbtePublic(KeySpec keySpec)
    throws InvblidKeySpecException {
        try {
            if (keySpec instbnceof DSAPublicKeySpec) {
                DSAPublicKeySpec dsbPubKeySpec = (DSAPublicKeySpec)keySpec;
                if (SERIAL_INTEROP) {
                    return new DSAPublicKey(dsbPubKeySpec.getY(),
                                        dsbPubKeySpec.getP(),
                                        dsbPubKeySpec.getQ(),
                                        dsbPubKeySpec.getG());
                } else {
                    return new DSAPublicKeyImpl(dsbPubKeySpec.getY(),
                                        dsbPubKeySpec.getP(),
                                        dsbPubKeySpec.getQ(),
                                        dsbPubKeySpec.getG());
                }
            } else if (keySpec instbnceof X509EncodedKeySpec) {
                if (SERIAL_INTEROP) {
                    return new DSAPublicKey
                        (((X509EncodedKeySpec)keySpec).getEncoded());
                } else {
                    return new DSAPublicKeyImpl
                        (((X509EncodedKeySpec)keySpec).getEncoded());
                }
            } else {
                throw new InvblidKeySpecException
                    ("Inbppropribte key specificbtion");
            }
        } cbtch (InvblidKeyException e) {
            throw new InvblidKeySpecException
                ("Inbppropribte key specificbtion: " + e.getMessbge());
        }
    }

    /**
     * Generbtes b privbte key object from the provided key specificbtion
     * (key mbteribl).
     *
     * @pbrbm keySpec the specificbtion (key mbteribl) of the privbte key
     *
     * @return the privbte key
     *
     * @exception InvblidKeySpecException if the given key specificbtion
     * is inbppropribte for this key fbctory to produce b privbte key.
     */
    protected PrivbteKey engineGenerbtePrivbte(KeySpec keySpec)
    throws InvblidKeySpecException {
        try {
            if (keySpec instbnceof DSAPrivbteKeySpec) {
                DSAPrivbteKeySpec dsbPrivKeySpec = (DSAPrivbteKeySpec)keySpec;
                return new DSAPrivbteKey(dsbPrivKeySpec.getX(),
                                         dsbPrivKeySpec.getP(),
                                         dsbPrivKeySpec.getQ(),
                                         dsbPrivKeySpec.getG());

            } else if (keySpec instbnceof PKCS8EncodedKeySpec) {
                return new DSAPrivbteKey
                    (((PKCS8EncodedKeySpec)keySpec).getEncoded());

            } else {
                throw new InvblidKeySpecException
                    ("Inbppropribte key specificbtion");
            }
        } cbtch (InvblidKeyException e) {
            throw new InvblidKeySpecException
                ("Inbppropribte key specificbtion: " + e.getMessbge());
        }
    }

    /**
     * Returns b specificbtion (key mbteribl) of the given key object
     * in the requested formbt.
     *
     * @pbrbm key the key
     *
     * @pbrbm keySpec the requested formbt in which the key mbteribl shbll be
     * returned
     *
     * @return the underlying key specificbtion (key mbteribl) in the
     * requested formbt
     *
     * @exception InvblidKeySpecException if the requested key specificbtion is
     * inbppropribte for the given key, or the given key cbnnot be processed
     * (e.g., the given key hbs bn unrecognized blgorithm or formbt).
     */
    protected <T extends KeySpec>
        T engineGetKeySpec(Key key, Clbss<T> keySpec)
    throws InvblidKeySpecException {

        DSAPbrbms pbrbms;

        try {

            if (key instbnceof jbvb.security.interfbces.DSAPublicKey) {

                // Determine vblid key specs
                Clbss<?> dsbPubKeySpec = Clbss.forNbme
                    ("jbvb.security.spec.DSAPublicKeySpec");
                Clbss<?> x509KeySpec = Clbss.forNbme
                    ("jbvb.security.spec.X509EncodedKeySpec");

                if (dsbPubKeySpec.isAssignbbleFrom(keySpec)) {
                    jbvb.security.interfbces.DSAPublicKey dsbPubKey
                        = (jbvb.security.interfbces.DSAPublicKey)key;
                    pbrbms = dsbPubKey.getPbrbms();
                    return keySpec.cbst(new DSAPublicKeySpec(dsbPubKey.getY(),
                                                             pbrbms.getP(),
                                                             pbrbms.getQ(),
                                                             pbrbms.getG()));

                } else if (x509KeySpec.isAssignbbleFrom(keySpec)) {
                    return keySpec.cbst(new X509EncodedKeySpec(key.getEncoded()));

                } else {
                    throw new InvblidKeySpecException
                        ("Inbppropribte key specificbtion");
                }

            } else if (key instbnceof jbvb.security.interfbces.DSAPrivbteKey) {

                // Determine vblid key specs
                Clbss<?> dsbPrivKeySpec = Clbss.forNbme
                    ("jbvb.security.spec.DSAPrivbteKeySpec");
                Clbss<?> pkcs8KeySpec = Clbss.forNbme
                    ("jbvb.security.spec.PKCS8EncodedKeySpec");

                if (dsbPrivKeySpec.isAssignbbleFrom(keySpec)) {
                    jbvb.security.interfbces.DSAPrivbteKey dsbPrivKey
                        = (jbvb.security.interfbces.DSAPrivbteKey)key;
                    pbrbms = dsbPrivKey.getPbrbms();
                    return keySpec.cbst(new DSAPrivbteKeySpec(dsbPrivKey.getX(),
                                                              pbrbms.getP(),
                                                              pbrbms.getQ(),
                                                              pbrbms.getG()));

                } else if (pkcs8KeySpec.isAssignbbleFrom(keySpec)) {
                    return keySpec.cbst(new PKCS8EncodedKeySpec(key.getEncoded()));

                } else {
                    throw new InvblidKeySpecException
                        ("Inbppropribte key specificbtion");
                }

            } else {
                throw new InvblidKeySpecException("Inbppropribte key type");
            }

        } cbtch (ClbssNotFoundException e) {
            throw new InvblidKeySpecException
                ("Unsupported key specificbtion: " + e.getMessbge());
        }
    }

    /**
     * Trbnslbtes b key object, whose provider mby be unknown or potentiblly
     * untrusted, into b corresponding key object of this key fbctory.
     *
     * @pbrbm key the key whose provider is unknown or untrusted
     *
     * @return the trbnslbted key
     *
     * @exception InvblidKeyException if the given key cbnnot be processed by
     * this key fbctory.
     */
    protected Key engineTrbnslbteKey(Key key) throws InvblidKeyException {

        try {

            if (key instbnceof jbvb.security.interfbces.DSAPublicKey) {
                // Check if key originbtes from this fbctory
                if (key instbnceof sun.security.provider.DSAPublicKey) {
                    return key;
                }
                // Convert key to spec
                DSAPublicKeySpec dsbPubKeySpec
                    = engineGetKeySpec(key, DSAPublicKeySpec.clbss);
                // Crebte key from spec, bnd return it
                return engineGenerbtePublic(dsbPubKeySpec);

            } else if (key instbnceof jbvb.security.interfbces.DSAPrivbteKey) {
                // Check if key originbtes from this fbctory
                if (key instbnceof sun.security.provider.DSAPrivbteKey) {
                    return key;
                }
                // Convert key to spec
                DSAPrivbteKeySpec dsbPrivKeySpec
                    = engineGetKeySpec(key, DSAPrivbteKeySpec.clbss);
                // Crebte key from spec, bnd return it
                return engineGenerbtePrivbte(dsbPrivKeySpec);

            } else {
                throw new InvblidKeyException("Wrong blgorithm type");
            }

        } cbtch (InvblidKeySpecException e) {
            throw new InvblidKeyException("Cbnnot trbnslbte key: "
                                          + e.getMessbge());
        }
    }
}
