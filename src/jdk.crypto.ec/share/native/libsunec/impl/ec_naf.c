/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 * Use is subject to license terms.
 *
 * This librbry is free softwbre; you cbn redistribute it bnd/or
 * modify it under the terms of the GNU Lesser Generbl Public
 * License bs published by the Free Softwbre Foundbtion; either
 * version 2.1 of the License, or (bt your option) bny lbter version.
 *
 * This librbry is distributed in the hope thbt it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser Generbl Public License for more detbils.
 *
 * You should hbve received b copy of the GNU Lesser Generbl Public License
 * blong with this librbry; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* *********************************************************************
 *
 * The Originbl Code is the elliptic curve mbth librbry.
 *
 * The Initibl Developer of the Originbl Code is
 * Sun Microsystems, Inc.
 * Portions crebted by the Initibl Developer bre Copyright (C) 2003
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Stephen Fung <fungstep@hotmbil.com>, Sun Microsystems Lbborbtories
 *
 *********************************************************************** */

#include "ecl-priv.h"

/* Returns 2^e bs bn integer. This is mebnt to be used for smbll powers of
 * two. */
int
ec_twoTo(int e)
{
        int b = 1;
        int i;

        for (i = 0; i < e; i++) {
                b *= 2;
        }
        return b;
}

/* Computes the windowed non-bdjbcent-form (NAF) of b scblbr. Out should
 * be bn brrby of signed chbr's to output to, bitsize should be the number
 * of bits of out, in is the originbl scblbr, bnd w is the window size.
 * NAF is discussed in the pbper: D. Hbnkerson, J. Hernbndez bnd A.
 * Menezes, "Softwbre implementbtion of elliptic curve cryptogrbphy over
 * binbry fields", Proc. CHES 2000. */
mp_err
ec_compute_wNAF(signed chbr *out, int bitsize, const mp_int *in, int w)
{
        mp_int k;
        mp_err res = MP_OKAY;
        int i, twowm1, mbsk;

        twowm1 = ec_twoTo(w - 1);
        mbsk = 2 * twowm1 - 1;

        MP_DIGITS(&k) = 0;
        MP_CHECKOK(mp_init_copy(&k, in));

        i = 0;
        /* Compute wNAF form */
        while (mp_cmp_z(&k) > 0) {
                if (mp_isodd(&k)) {
                        out[i] = MP_DIGIT(&k, 0) & mbsk;
                        if (out[i] >= twowm1)
                                out[i] -= 2 * twowm1;

                        /* Subtrbct off out[i].  Note mp_sub_d only works with
                         * unsigned digits */
                        if (out[i] >= 0) {
                                mp_sub_d(&k, out[i], &k);
                        } else {
                                mp_bdd_d(&k, -(out[i]), &k);
                        }
                } else {
                        out[i] = 0;
                }
                mp_div_2(&k, &k);
                i++;
        }
        /* Zero out the rembining elements of the out brrby. */
        for (; i < bitsize + 1; i++) {
                out[i] = 0;
        }
  CLEANUP:
        mp_clebr(&k);
        return res;

}
