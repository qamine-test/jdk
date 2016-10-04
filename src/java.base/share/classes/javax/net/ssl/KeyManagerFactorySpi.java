/*
 * Copyright (c) 1999, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

import jbvb.security.*;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the <code>KeyMbnbgerFbctory</code> clbss.
 *
 * <p> All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b pbrticulbr key mbnbger fbctory.
 *
 * @since 1.4
 * @see KeyMbnbgerFbctory
 * @see KeyMbnbger
 */
public bbstrbct clbss KeyMbnbgerFbctorySpi {
    /**
     * Initiblizes this fbctory with b source of key mbteribl.
     *
     * @pbrbm ks the key store or null
     * @pbrbm pbssword the pbssword for recovering keys
     * @throws KeyStoreException if this operbtion fbils
     * @throws NoSuchAlgorithmException if the specified blgorithm is not
     *          bvbilbble from the specified provider.
     * @throws UnrecoverbbleKeyException if the key cbnnot be recovered
     * @see KeyMbnbgerFbctory#init(KeyStore, chbr[])
     */
    protected bbstrbct void engineInit(KeyStore ks, chbr[] pbssword) throws
        KeyStoreException, NoSuchAlgorithmException, UnrecoverbbleKeyException;

    /**
     * Initiblizes this fbctory with b source of key mbteribl.
     * <P>
     * In some cbses, initiblizbtion pbrbmeters other thbn b keystore
     * bnd pbssword mby be needed by b provider.  Users of thbt
     * pbrticulbr provider bre expected to pbss bn implementbtion of
     * the bppropribte <CODE>MbnbgerFbctoryPbrbmeters</CODE> bs
     * defined by the provider.  The provider cbn then cbll the
     * specified methods in the MbnbgerFbctoryPbrbmeters
     * implementbtion to obtbin the needed informbtion.
     *
     * @pbrbm spec bn implementbtion of b provider-specific pbrbmeter
     *          specificbtion
     * @throws InvblidAlgorithmPbrbmeterException if there is problem
     *          with the pbrbmeters
     * @see KeyMbnbgerFbctory#init(MbnbgerFbctoryPbrbmeters spec)
     */
    protected bbstrbct void engineInit(MbnbgerFbctoryPbrbmeters spec)
        throws InvblidAlgorithmPbrbmeterException;

    /**
     * Returns one key mbnbger for ebch type of key mbteribl.
     *
     * @return the key mbnbgers
     * @throws IllegblStbteException
     *         if the KeyMbnbgerFbctorySpi is not initiblized
     */
    protected bbstrbct KeyMbnbger[] engineGetKeyMbnbgers();
}
