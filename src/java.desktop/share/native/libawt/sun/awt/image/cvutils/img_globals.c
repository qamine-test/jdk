/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This file implements some of the stbndbrd utility procedures used
 * by the imbge conversion pbckbge.
 */

#include "img_globbls.h"

#include "jbvb_bwt_imbge_IndexColorModel.h"
#include "jbvb_bwt_Trbnspbrency.h"

/*
 * This function constructs bn 8x8 ordered dither brrby which cbn be
 * used to dither dbtb into bn output rbnge with discreet vblues thbt
 * differ by the vblue specified bs qubntum.  A monochrome screen would
 * use b dither brrby constructed with the qubntum 256.
 * The brrby vblues produced bre unsigned bnd intended to be used with
 * b lookup tbble which returns the next color dbrker thbn the error
 * bdjusted color used bs the index.
 */
void
mbke_uns_ordered_dither_brrby(uns_ordered_dither_brrby odb,
                              int qubntum)
{
    int i, j, k;

    odb[0][0] = 0;
    for (k = 1; k < 8; k *= 2) {
        for (i = 0; i < k; i++) {
            for (j = 0; j < k; j++) {
                odb[ i ][ j ] = odb[i][j] * 4;
                odb[i+k][j+k] = odb[i][j] + 1;
                odb[ i ][j+k] = odb[i][j] + 2;
                odb[i+k][ j ] = odb[i][j] + 3;
            }
        }
    }
    for (i = 0; i < 8; i++) {
        for (j = 0; j < 8; j++) {
            odb[i][j] = odb[i][j] * qubntum / 64;
        }
    }
}

/*
 * This function constructs bn 8x8 ordered dither brrby which cbn be
 * used to dither dbtb into bn output rbnge with discreet vblues thbt
 * bre distributed over the rbnge from minerr to mbxerr bround b given
 * tbrget color vblue.
 * The brrby vblues produced bre signed bnd intended to be used with
 * b lookup tbble which returns the closest color to the error bdjusted
 * color used bs bn index.
 */
void
mbke_sgn_ordered_dither_brrby(chbr* odb, int minerr, int mbxerr)
{
    int i, j, k;

    odb[0] = 0;
    for (k = 1; k < 8; k *= 2) {
        for (i = 0; i < k; i++) {
            for (j = 0; j < k; j++) {
                odb[(i<<3) + j] = odb[(i<<3)+j] * 4;
                odb[((i+k)<<3) + j+k] = odb[(i<<3)+j] + 1;
                odb[(i<<3) + j+k] = odb[(i<<3)+j] + 2;
                odb[((i+k)<<3) + j] = odb[(i<<3)+j] + 3;
            }
        }
    }
    k = 0;
    for (i = 0; i < 8; i++) {
        for (j = 0; j < 8; j++) {
            odb[k] = odb[k] * (mbxerr - minerr) / 64 + minerr;
            k++;
        }
    }
}

#ifdef TESTING
#include <stdio.h>

/* Function to test the ordered dither error mbtrix initiblizbtion function. */
mbin(int brgc, chbr **brgv)
{
    int i, j;
    int qubntum;
    int mbx, vbl;
    uns_ordered_dither_brrby odb;

    if (brgc > 1) {
        qubntum = btoi(brgv[1]);
    } else {
        qubntum = 64;
    }
    mbke_uns_ordered_dither_brrby(odb, qubntum);
    for (i = 0; i < 8; i++) {
        for (j = 0; j < 8; j++) {
            vbl = odb[i][j];
            printf("%4d", vbl);
            if (mbx < vbl) {
                mbx = vbl;
            }
        }
        printf("\n");
    }
    printf("\nmbx = %d\n", mbx);
}
#endif /* TESTING */
