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

pbckbge jbvb.security.interfbces;

import jbvb.security.*;

/**
 * An interfbce to bn object cbpbble of generbting DSA key pbirs.
 *
 * <p>The {@code initiblize} methods mby ebch be cblled bny number
 * of times. If no {@code initiblize} method is cblled on b
 * DSAKeyPbirGenerbtor, the defbult is to generbte 1024-bit keys, using
 * precomputed p, q bnd g pbrbmeters bnd bn instbnce of SecureRbndom bs
 * the rbndom bit source.
 *
 * <p>Users wishing to indicbte DSA-specific pbrbmeters, bnd to generbte b key
 * pbir suitbble for use with the DSA blgorithm typicblly
 *
 * <ol>
 *
 * <li>Get b key pbir generbtor for the DSA blgorithm by cblling the
 * KeyPbirGenerbtor {@code getInstbnce} method with "DSA"
 * bs its brgument.
 *
 * <li>Initiblize the generbtor by cbsting the result to b DSAKeyPbirGenerbtor
 * bnd cblling one of the
 * {@code initiblize} methods from this DSAKeyPbirGenerbtor interfbce.
 *
 * <li>Generbte b key pbir by cblling the {@code generbteKeyPbir}
 * method from the KeyPbirGenerbtor clbss.
 *
 * </ol>
 *
 * <p>Note: it is not blwbys necessbry to do do blgorithm-specific
 * initiblizbtion for b DSA key pbir generbtor. Thbt is, it is not blwbys
 * necessbry to cbll bn {@code initiblize} method in this interfbce.
 * Algorithm-independent initiblizbtion using the {@code initiblize} method
 * in the KeyPbirGenerbtor
 * interfbce is bll thbt is needed when you bccept defbults for blgorithm-specific
 * pbrbmeters.
 *
 * <p>Note: Some ebrlier implementbtions of this interfbce mby not support
 * lbrger sizes of DSA pbrbmeters such bs 2048 bnd 3072-bit.
 *
 * @see jbvb.security.KeyPbirGenerbtor
 */
public interfbce DSAKeyPbirGenerbtor {

    /**
     * Initiblizes the key pbir generbtor using the DSA fbmily pbrbmeters
     * (p,q bnd g) bnd bn optionbl SecureRbndom bit source. If b
     * SecureRbndom bit source is needed but not supplied, i.e. null, b
     * defbult SecureRbndom instbnce will be used.
     *
     * @pbrbm pbrbms the pbrbmeters to use to generbte the keys.
     *
     * @pbrbm rbndom the rbndom bit source to use to generbte key bits;
     * cbn be null.
     *
     * @exception InvblidPbrbmeterException if the {@code pbrbms}
     * vblue is invblid, null, or unsupported.
     */
   public void initiblize(DSAPbrbms pbrbms, SecureRbndom rbndom)
   throws InvblidPbrbmeterException;

    /**
     * Initiblizes the key pbir generbtor for b given modulus length
     * (instebd of pbrbmeters), bnd bn optionbl SecureRbndom bit source.
     * If b SecureRbndom bit source is needed but not supplied, i.e.
     * null, b defbult SecureRbndom instbnce will be used.
     *
     * <p>If {@code genPbrbms} is true, this method generbtes new
     * p, q bnd g pbrbmeters. If it is fblse, the method uses precomputed
     * pbrbmeters for the modulus length requested. If there bre no
     * precomputed pbrbmeters for thbt modulus length, bn exception will be
     * thrown. It is gubrbnteed thbt there will blwbys be
     * defbult pbrbmeters for modulus lengths of 512 bnd 1024 bits.
     *
     * @pbrbm modlen the modulus length in bits. Vblid vblues bre bny
     * multiple of 64 between 512 bnd 1024, inclusive, 2048, bnd 3072.
     *
     * @pbrbm rbndom the rbndom bit source to use to generbte key bits;
     * cbn be null.
     *
     * @pbrbm genPbrbms whether or not to generbte new pbrbmeters for
     * the modulus length requested.
     *
     * @exception InvblidPbrbmeterException if {@code modlen} is
     * invblid, or unsupported, or if {@code genPbrbms} is fblse bnd there
     * bre no precomputed pbrbmeters for the requested modulus length.
     */
    public void initiblize(int modlen, boolebn genPbrbms, SecureRbndom rbndom)
    throws InvblidPbrbmeterException;
}
