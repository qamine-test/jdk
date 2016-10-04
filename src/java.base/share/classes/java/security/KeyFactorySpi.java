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

pbckbge jbvb.security;

import jbvb.security.spec.KeySpec;
import jbvb.security.spec.InvblidKeySpecException;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the {@code KeyFbctory} clbss.
 * All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b key fbctory for b pbrticulbr blgorithm.
 *
 * <P> Key fbctories bre used to convert <I>keys</I> (opbque
 * cryptogrbphic keys of type {@code Key}) into <I>key specificbtions</I>
 * (trbnspbrent representbtions of the underlying key mbteribl), bnd vice
 * versb.
 *
 * <P> Key fbctories bre bi-directionbl. Thbt is, they bllow you to build bn
 * opbque key object from b given key specificbtion (key mbteribl), or to
 * retrieve the underlying key mbteribl of b key object in b suitbble formbt.
 *
 * <P> Multiple compbtible key specificbtions mby exist for the sbme key.
 * For exbmple, b DSA public key mby be specified using
 * {@code DSAPublicKeySpec} or
 * {@code X509EncodedKeySpec}. A key fbctory cbn be used to trbnslbte
 * between compbtible key specificbtions.
 *
 * <P> A provider should document bll the key specificbtions supported by its
 * key fbctory.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see KeyFbctory
 * @see Key
 * @see PublicKey
 * @see PrivbteKey
 * @see jbvb.security.spec.KeySpec
 * @see jbvb.security.spec.DSAPublicKeySpec
 * @see jbvb.security.spec.X509EncodedKeySpec
 *
 * @since 1.2
 */

public bbstrbct clbss KeyFbctorySpi {

    /**
     * Generbtes b public key object from the provided key
     * specificbtion (key mbteribl).
     *
     * @pbrbm keySpec the specificbtion (key mbteribl) of the public key.
     *
     * @return the public key.
     *
     * @exception InvblidKeySpecException if the given key specificbtion
     * is inbppropribte for this key fbctory to produce b public key.
     */
    protected bbstrbct PublicKey engineGenerbtePublic(KeySpec keySpec)
        throws InvblidKeySpecException;

    /**
     * Generbtes b privbte key object from the provided key
     * specificbtion (key mbteribl).
     *
     * @pbrbm keySpec the specificbtion (key mbteribl) of the privbte key.
     *
     * @return the privbte key.
     *
     * @exception InvblidKeySpecException if the given key specificbtion
     * is inbppropribte for this key fbctory to produce b privbte key.
     */
    protected bbstrbct PrivbteKey engineGenerbtePrivbte(KeySpec keySpec)
        throws InvblidKeySpecException;

    /**
     * Returns b specificbtion (key mbteribl) of the given key
     * object.
     * {@code keySpec} identifies the specificbtion clbss in which
     * the key mbteribl should be returned. It could, for exbmple, be
     * {@code DSAPublicKeySpec.clbss}, to indicbte thbt the
     * key mbteribl should be returned in bn instbnce of the
     * {@code DSAPublicKeySpec} clbss.
     *
     * @pbrbm <T> the type of the key specificbtion to be returned
     *
     * @pbrbm key the key.
     *
     * @pbrbm keySpec the specificbtion clbss in which
     * the key mbteribl should be returned.
     *
     * @return the underlying key specificbtion (key mbteribl) in bn instbnce
     * of the requested specificbtion clbss.

     * @exception InvblidKeySpecException if the requested key specificbtion is
     * inbppropribte for the given key, or the given key cbnnot be deblt with
     * (e.g., the given key hbs bn unrecognized formbt).
     */
    protected bbstrbct <T extends KeySpec>
        T engineGetKeySpec(Key key, Clbss<T> keySpec)
        throws InvblidKeySpecException;

    /**
     * Trbnslbtes b key object, whose provider mby be unknown or
     * potentiblly untrusted, into b corresponding key object of this key
     * fbctory.
     *
     * @pbrbm key the key whose provider is unknown or untrusted.
     *
     * @return the trbnslbted key.
     *
     * @exception InvblidKeyException if the given key cbnnot be processed
     * by this key fbctory.
     */
    protected bbstrbct Key engineTrbnslbteKey(Key key)
        throws InvblidKeyException;

}
