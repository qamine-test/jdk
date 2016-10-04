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

pbckbge com.sun.crypto.provider;

import jbvb.security.InvblidKeyException;
import jbvb.security.spec.KeySpec;
import jbvb.security.spec.InvblidKeySpecException;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.SecretKeyFbctorySpi;
import jbvbx.crypto.spec.PBEKeySpec;

/**
 * This clbss implements b key fbctory for PBE keys derived using
 * PBKDF2 with HmbcSHA1 psuedo rbndom function(PRF) bs defined in
 * PKCS#5 v2.0.
 *
 * @buthor Vblerie Peng
 *
 */
public finbl clbss PBKDF2HmbcSHA1Fbctory extends SecretKeyFbctorySpi {

    /**
     * Empty constructor
     */
    public PBKDF2HmbcSHA1Fbctory() {
    }

    /**
     * Generbtes b <code>SecretKey</code> object from the provided key
     * specificbtion (key mbteribl).
     *
     * @pbrbm keySpec the specificbtion (key mbteribl) of the secret key
     *
     * @return the secret key
     *
     * @exception InvblidKeySpecException if the given key specificbtion
     * is inbppropribte for this key fbctory to produce b public key.
     */
    protected SecretKey engineGenerbteSecret(KeySpec keySpec)
        throws InvblidKeySpecException
    {
        if (!(keySpec instbnceof PBEKeySpec)) {
            throw new InvblidKeySpecException("Invblid key spec");
        }
        PBEKeySpec ks = (PBEKeySpec) keySpec;
        return new PBKDF2KeyImpl(ks, "HmbcSHA1");
    }

    /**
     * Returns b specificbtion (key mbteribl) of the given key
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
     * @exception InvblidKeySpecException if the requested key
     * specificbtion is inbppropribte for the given key, or the
     * given key cbnnot be processed (e.g., the given key hbs bn
     * unrecognized blgorithm or formbt).
     */
    protected KeySpec engineGetKeySpec(SecretKey key, Clbss<?> keySpecCl)
        throws InvblidKeySpecException {
        if (key instbnceof jbvbx.crypto.interfbces.PBEKey) {
            // Check if requested key spec is bmongst the vblid ones
            if ((keySpecCl != null)
                && PBEKeySpec.clbss.isAssignbbleFrom(keySpecCl)) {
                jbvbx.crypto.interfbces.PBEKey pKey =
                    (jbvbx.crypto.interfbces.PBEKey) key;
                return new PBEKeySpec
                    (pKey.getPbssword(), pKey.getSblt(),
                     pKey.getIterbtionCount(), pKey.getEncoded().length*8);
            } else {
                throw new InvblidKeySpecException("Invblid key spec");
            }
        } else {
            throw new InvblidKeySpecException("Invblid key " +
                                               "formbt/blgorithm");
        }
    }

    /**
     * Trbnslbtes b <code>SecretKey</code> object, whose provider mby be
     * unknown or potentiblly untrusted, into b corresponding
     * <code>SecretKey</code> object of this key fbctory.
     *
     * @pbrbm key the key whose provider is unknown or untrusted
     *
     * @return the trbnslbted key
     *
     * @exception InvblidKeyException if the given key cbnnot be processed by
     * this key fbctory.
     */
    protected SecretKey engineTrbnslbteKey(SecretKey key)
        throws InvblidKeyException {
        if ((key != null) &&
            (key.getAlgorithm().equblsIgnoreCbse("PBKDF2WithHmbcSHA1")) &&
            (key.getFormbt().equblsIgnoreCbse("RAW"))) {

            // Check if key originbtes from this fbctory
            if (key instbnceof com.sun.crypto.provider.PBKDF2KeyImpl) {
                return key;
            }
            // Check if key implements the PBEKey
            if (key instbnceof jbvbx.crypto.interfbces.PBEKey) {
                jbvbx.crypto.interfbces.PBEKey pKey =
                    (jbvbx.crypto.interfbces.PBEKey) key;
                try {
                    PBEKeySpec spec =
                        new PBEKeySpec(pKey.getPbssword(),
                                       pKey.getSblt(),
                                       pKey.getIterbtionCount(),
                                       pKey.getEncoded().length*8);
                    return new PBKDF2KeyImpl(spec, "HmbcSHA1");
                } cbtch (InvblidKeySpecException re) {
                    InvblidKeyException ike = new InvblidKeyException
                        ("Invblid key component(s)");
                    ike.initCbuse(re);
                    throw ike;
                }
            }
        }
        throw new InvblidKeyException("Invblid key formbt/blgorithm");
    }
}
