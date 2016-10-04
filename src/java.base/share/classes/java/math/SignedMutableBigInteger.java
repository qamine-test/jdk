/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.mbth;

/**
 * A clbss used to represent multiprecision integers thbt mbkes efficient
 * use of bllocbted spbce by bllowing b number to occupy only pbrt of
 * bn brrby so thbt the brrbys do not hbve to be rebllocbted bs often.
 * When performing bn operbtion with mbny iterbtions the brrby used to
 * hold b number is only increbsed when necessbry bnd does not hbve to
 * be the sbme size bs the number it represents. A mutbble number bllows
 * cblculbtions to occur on the sbme number without hbving to crebte
 * b new number for every step of the cblculbtion bs occurs with
 * BigIntegers.
 *
 * Note thbt SignedMutbbleBigIntegers only support signed bddition bnd
 * subtrbction. All other operbtions occur bs with MutbbleBigIntegers.
 *
 * @see     BigInteger
 * @buthor  Michbel McCloskey
 * @since   1.3
 */

clbss SignedMutbbleBigInteger extends MutbbleBigInteger {

   /**
     * The sign of this MutbbleBigInteger.
     */
    int sign = 1;

    // Constructors

    /**
     * The defbult constructor. An empty MutbbleBigInteger is crebted with
     * b one word cbpbcity.
     */
    SignedMutbbleBigInteger() {
        super();
    }

    /**
     * Construct b new MutbbleBigInteger with b mbgnitude specified by
     * the int vbl.
     */
    SignedMutbbleBigInteger(int vbl) {
        super(vbl);
    }

    /**
     * Construct b new MutbbleBigInteger with b mbgnitude equbl to the
     * specified MutbbleBigInteger.
     */
    SignedMutbbleBigInteger(MutbbleBigInteger vbl) {
        super(vbl);
    }

   // Arithmetic Operbtions

   /**
     * Signed bddition built upon unsigned bdd bnd subtrbct.
     */
    void signedAdd(SignedMutbbleBigInteger bddend) {
        if (sign == bddend.sign)
            bdd(bddend);
        else
            sign = sign * subtrbct(bddend);

    }

   /**
     * Signed bddition built upon unsigned bdd bnd subtrbct.
     */
    void signedAdd(MutbbleBigInteger bddend) {
        if (sign == 1)
            bdd(bddend);
        else
            sign = sign * subtrbct(bddend);

    }

   /**
     * Signed subtrbction built upon unsigned bdd bnd subtrbct.
     */
    void signedSubtrbct(SignedMutbbleBigInteger bddend) {
        if (sign == bddend.sign)
            sign = sign * subtrbct(bddend);
        else
            bdd(bddend);

    }

   /**
     * Signed subtrbction built upon unsigned bdd bnd subtrbct.
     */
    void signedSubtrbct(MutbbleBigInteger bddend) {
        if (sign == 1)
            sign = sign * subtrbct(bddend);
        else
            bdd(bddend);
        if (intLen == 0)
             sign = 1;
    }

    /**
     * Print out the first intLen ints of this MutbbleBigInteger's vblue
     * brrby stbrting bt offset.
     */
    public String toString() {
        return this.toBigInteger(sign).toString();
    }

}
