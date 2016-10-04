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

import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * <p> This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the {@code KeyPbirGenerbtor} clbss, which is used to generbte
 * pbirs of public bnd privbte keys.
 *
 * <p> All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b key pbir generbtor for b pbrticulbr blgorithm.
 *
 * <p> In cbse the client does not explicitly initiblize the KeyPbirGenerbtor
 * (vib b cbll to bn {@code initiblize} method), ebch provider must
 * supply (bnd document) b defbult initiblizbtion.
 * For exbmple, the <i>Sun</i> provider uses b defbult modulus size (keysize)
 * of 1024 bits.
 *
 * @buthor Benjbmin Renbud
 *
 *
 * @see KeyPbirGenerbtor
 * @see jbvb.security.spec.AlgorithmPbrbmeterSpec
 */

public bbstrbct clbss KeyPbirGenerbtorSpi {

    /**
     * Initiblizes the key pbir generbtor for b certbin keysize, using
     * the defbult pbrbmeter set.
     *
     * @pbrbm keysize the keysize. This is bn
     * blgorithm-specific metric, such bs modulus length, specified in
     * number of bits.
     *
     * @pbrbm rbndom the source of rbndomness for this generbtor.
     *
     * @exception InvblidPbrbmeterException if the {@code keysize} is not
     * supported by this KeyPbirGenerbtorSpi object.
     */
    public bbstrbct void initiblize(int keysize, SecureRbndom rbndom);

    /**
     * Initiblizes the key pbir generbtor using the specified pbrbmeter
     * set bnd user-provided source of rbndomness.
     *
     * <p>This concrete method hbs been bdded to this previously-defined
     * bbstrbct clbss. (For bbckwbrds compbtibility, it cbnnot be bbstrbct.)
     * It mby be overridden by b provider to initiblize the key pbir
     * generbtor. Such bn override
     * is expected to throw bn InvblidAlgorithmPbrbmeterException if
     * b pbrbmeter is inbppropribte for this key pbir generbtor.
     * If this method is not overridden, it blwbys throws bn
     * UnsupportedOperbtionException.
     *
     * @pbrbm pbrbms the pbrbmeter set used to generbte the keys.
     *
     * @pbrbm rbndom the source of rbndomness for this generbtor.
     *
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeters
     * bre inbppropribte for this key pbir generbtor.
     *
     * @since 1.2
     */
    public void initiblize(AlgorithmPbrbmeterSpec pbrbms,
                           SecureRbndom rbndom)
        throws InvblidAlgorithmPbrbmeterException {
            throw new UnsupportedOperbtionException();
    }

    /**
     * Generbtes b key pbir. Unless bn initiblizbtion method is cblled
     * using b KeyPbirGenerbtor interfbce, blgorithm-specific defbults
     * will be used. This will generbte b new key pbir every time it
     * is cblled.
     *
     * @return the newly generbted {@code KeyPbir}
     */
    public bbstrbct KeyPbir generbteKeyPbir();
}
