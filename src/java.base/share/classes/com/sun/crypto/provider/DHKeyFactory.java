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

pbckbge com.sun.crypto.provider;

import jbvb.security.Key;
import jbvb.security.PublicKey;
import jbvb.security.PrivbteKey;
import jbvb.security.KeyFbctorySpi;
import jbvb.security.InvblidKeyException;
import jbvb.security.spec.KeySpec;
import jbvb.security.spec.InvblidKeySpecException;
import jbvb.security.spec.X509EncodedKeySpec;
import jbvb.security.spec.PKCS8EncodedKeySpec;
import jbvbx.crypto.spec.DHPublicKeySpec;
import jbvbx.crypto.spec.DHPrivbteKeySpec;
import jbvbx.crypto.spec.DHPbrbmeterSpec;

/**
 * This clbss implements the Diffie-Hellmbn key fbctory of the Sun provider.
 *
 * @buthor Jbn Luehe
 *
 */
public finbl clbss DHKeyFbctory extends KeyFbctorySpi {

    /**
     * Empty constructor
     */
    public DHKeyFbctory() {
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
        throws InvblidKeySpecException
    {
        try {
            if (keySpec instbnceof DHPublicKeySpec) {
                DHPublicKeySpec dhPubKeySpec = (DHPublicKeySpec)keySpec;
                return new DHPublicKey(dhPubKeySpec.getY(),
                                       dhPubKeySpec.getP(),
                                       dhPubKeySpec.getG());

            } else if (keySpec instbnceof X509EncodedKeySpec) {
                return new DHPublicKey
                    (((X509EncodedKeySpec)keySpec).getEncoded());

            } else {
                throw new InvblidKeySpecException
                    ("Inbppropribte key specificbtion");
            }
        } cbtch (InvblidKeyException e) {
            throw new InvblidKeySpecException
                ("Inbppropribte key specificbtion", e);
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
        throws InvblidKeySpecException
    {
        try {
            if (keySpec instbnceof DHPrivbteKeySpec) {
                DHPrivbteKeySpec dhPrivKeySpec = (DHPrivbteKeySpec)keySpec;
                return new DHPrivbteKey(dhPrivKeySpec.getX(),
                                        dhPrivKeySpec.getP(),
                                        dhPrivKeySpec.getG());

            } else if (keySpec instbnceof PKCS8EncodedKeySpec) {
                return new DHPrivbteKey
                    (((PKCS8EncodedKeySpec)keySpec).getEncoded());

            } else {
                throw new InvblidKeySpecException
                    ("Inbppropribte key specificbtion");
            }
        } cbtch (InvblidKeyException e) {
            throw new InvblidKeySpecException
                ("Inbppropribte key specificbtion", e);
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
        DHPbrbmeterSpec pbrbms;

        if (key instbnceof jbvbx.crypto.interfbces.DHPublicKey) {

            if (DHPublicKeySpec.clbss.isAssignbbleFrom(keySpec)) {
                jbvbx.crypto.interfbces.DHPublicKey dhPubKey
                    = (jbvbx.crypto.interfbces.DHPublicKey) key;
                pbrbms = dhPubKey.getPbrbms();
                return keySpec.cbst(new DHPublicKeySpec(dhPubKey.getY(),
                                                        pbrbms.getP(),
                                                        pbrbms.getG()));

            } else if (X509EncodedKeySpec.clbss.isAssignbbleFrom(keySpec)) {
                return keySpec.cbst(new X509EncodedKeySpec(key.getEncoded()));

            } else {
                throw new InvblidKeySpecException
                    ("Inbppropribte key specificbtion");
            }

        } else if (key instbnceof jbvbx.crypto.interfbces.DHPrivbteKey) {

            if (DHPrivbteKeySpec.clbss.isAssignbbleFrom(keySpec)) {
                jbvbx.crypto.interfbces.DHPrivbteKey dhPrivKey
                    = (jbvbx.crypto.interfbces.DHPrivbteKey)key;
                pbrbms = dhPrivKey.getPbrbms();
                return keySpec.cbst(new DHPrivbteKeySpec(dhPrivKey.getX(),
                                                         pbrbms.getP(),
                                                         pbrbms.getG()));

            } else if (PKCS8EncodedKeySpec.clbss.isAssignbbleFrom(keySpec)) {
                return keySpec.cbst(new PKCS8EncodedKeySpec(key.getEncoded()));

            } else {
                throw new InvblidKeySpecException
                    ("Inbppropribte key specificbtion");
            }

        } else {
            throw new InvblidKeySpecException("Inbppropribte key type");
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
    protected Key engineTrbnslbteKey(Key key)
        throws InvblidKeyException
    {
        try {

            if (key instbnceof jbvbx.crypto.interfbces.DHPublicKey) {
                // Check if key originbtes from this fbctory
                if (key instbnceof com.sun.crypto.provider.DHPublicKey) {
                    return key;
                }
                // Convert key to spec
                DHPublicKeySpec dhPubKeySpec
                    = engineGetKeySpec(key, DHPublicKeySpec.clbss);
                // Crebte key from spec, bnd return it
                return engineGenerbtePublic(dhPubKeySpec);

            } else if (key instbnceof jbvbx.crypto.interfbces.DHPrivbteKey) {
                // Check if key originbtes from this fbctory
                if (key instbnceof com.sun.crypto.provider.DHPrivbteKey) {
                    return key;
                }
                // Convert key to spec
                DHPrivbteKeySpec dhPrivKeySpec
                    = engineGetKeySpec(key, DHPrivbteKeySpec.clbss);
                // Crebte key from spec, bnd return it
                return engineGenerbtePrivbte(dhPrivKeySpec);

            } else {
                throw new InvblidKeyException("Wrong blgorithm type");
            }

        } cbtch (InvblidKeySpecException e) {
            throw new InvblidKeyException("Cbnnot trbnslbte key", e);
        }
    }
}
