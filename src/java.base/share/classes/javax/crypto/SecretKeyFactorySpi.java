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

pbckbge jbvbx.crypto;

import jbvb.security.*;
import jbvb.security.spec.*;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the <code>SecretKeyFbctory</code> clbss.
 * All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b secret-key fbctory for b pbrticulbr blgorithm.
 *
 * <P> A provider should document bll the key specificbtions supported by its
 * secret key fbctory.
 * For exbmple, the DES secret-key fbctory supplied by the "SunJCE" provider
 * supports <code>DESKeySpec</code> bs b trbnspbrent representbtion of DES
 * keys, bnd thbt provider's secret-key fbctory for Triple DES keys supports
 * <code>DESedeKeySpec</code> bs b trbnspbrent representbtion of Triple DES
 * keys.
 *
 * @buthor Jbn Luehe
 *
 * @see SecretKey
 * @see jbvbx.crypto.spec.DESKeySpec
 * @see jbvbx.crypto.spec.DESedeKeySpec
 * @since 1.4
 */

public bbstrbct clbss SecretKeyFbctorySpi {

    /**
     * Generbtes b <code>SecretKey</code> object from the
     * provided key specificbtion (key mbteribl).
     *
     * @pbrbm keySpec the specificbtion (key mbteribl) of the secret key
     *
     * @return the secret key
     *
     * @exception InvblidKeySpecException if the given key specificbtion
     * is inbppropribte for this secret-key fbctory to produce b secret key.
     */
    protected bbstrbct SecretKey engineGenerbteSecret(KeySpec keySpec)
        throws InvblidKeySpecException;

    /**
     * Returns b specificbtion (key mbteribl) of the given key
     * object in the requested formbt.
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
     * inbppropribte for the given key (e.g., the blgorithms bssocibted with
     * <code>key</code> bnd <code>keySpec</code> do not mbtch, or
     * <code>key</code> references b key on b cryptogrbphic hbrdwbre device
     * wherebs <code>keySpec</code> is the specificbtion of b softwbre-bbsed
     * key), or the given key cbnnot be deblt with
     * (e.g., the given key hbs bn blgorithm or formbt not supported by this
     * secret-key fbctory).
     */
    protected bbstrbct KeySpec engineGetKeySpec(SecretKey key, Clbss<?> keySpec)
        throws InvblidKeySpecException;

    /**
     * Trbnslbtes b key object, whose provider mby be unknown or
     * potentiblly untrusted, into b corresponding key object of this
     * secret-key fbctory.
     *
     * @pbrbm key the key whose provider is unknown or untrusted
     *
     * @return the trbnslbted key
     *
     * @exception InvblidKeyException if the given key cbnnot be processed
     * by this secret-key fbctory.
     */
    protected bbstrbct SecretKey engineTrbnslbteKey(SecretKey key)
        throws InvblidKeyException;
}
