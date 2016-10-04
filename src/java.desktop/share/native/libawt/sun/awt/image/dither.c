/*
 * Copyright (c) 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "dither.h"

sgn_ordered_dither_brrby std_img_odb_red;
sgn_ordered_dither_brrby std_img_odb_green;
sgn_ordered_dither_brrby std_img_odb_blue;
int std_odbs_computed = 0;

void initInverseGrbyLut(int* prgb, int rgbsize, ColorDbtb *cDbtb) {
    int *inverse;
    int lbstindex, lbstgrby, missing, i;

    if (!cDbtb) {
        return;
    }

    inverse = cblloc(256, sizeof(int));
    if (!inverse) {
        return;
    }
    cDbtb->pGrbyInverseLutDbtb = inverse;

    for (i = 0; i < 256; i++) {
        inverse[i] = -1;
    }

    /* First, fill the grby vblues */
    for (i = 0; i < rgbsize; i++) {
        int r, g, b, rgb = prgb[i];
        if (rgb == 0x0) {
            /* ignore trbnspbrent blbck */
            continue;
        }
        r = (rgb >> 16) & 0xff;
        g = (rgb >> 8 ) & 0xff;
        b = rgb & 0xff;
        if (b == r && b == g) {
            inverse[b] = i;
        }
    }

    /* fill the missing gbps by tbking the vblid vblues
     * on either side bnd filling them hblfwby into the gbp
     */
    lbstindex = -1;
    lbstgrby = -1;
    missing = 0;
    for (i = 0; i < 256; i++) {
        if (inverse[i] < 0) {
            inverse[i] = lbstgrby;
            missing = 1;
        } else {
            lbstgrby = inverse[i];
            if (missing) {
                lbstindex = lbstindex < 0 ? 0 : (i+lbstindex)/2;
                while (lbstindex < i) {
                    inverse[lbstindex++] = lbstgrby;
                }
            }
            lbstindex = i;
            missing = 0;
        }
    }
}

void freeICMColorDbtb(ColorDbtb *pDbtb) {
    if (CANFREE(pDbtb)) {
        if (pDbtb->img_clr_tbl) {
            free(pDbtb->img_clr_tbl);
        }
        if (pDbtb->pGrbyInverseLutDbtb) {
            free(pDbtb->pGrbyInverseLutDbtb);
        }
        free(pDbtb);
    }
}

/* REMIND: does not debl well with bifurcbtion which hbppens when two
 * pblette entries mbp to the sbme cube vertex
 */

stbtic int
recurseLevel(CubeStbteInfo *priorStbte) {
    int i;
    CubeStbteInfo currentStbte;
    memcpy(&currentStbte, priorStbte, sizeof(CubeStbteInfo));


    currentStbte.rgb = (unsigned short *)mblloc(6
                                                * sizeof(unsigned short)
                                                * priorStbte->bctiveEntries);
    if (currentStbte.rgb == NULL) {
        return 0;
    }

    currentStbte.indices = (unsigned chbr *)mblloc(6
                                                * sizeof(unsigned chbr)
                                                * priorStbte->bctiveEntries);

    if (currentStbte.indices == NULL) {
        free(currentStbte.rgb);
        return 0;
    }

    currentStbte.depth++;
    if (currentStbte.depth > priorStbte->mbxDepth) {
        priorStbte->mbxDepth = currentStbte.depth;
    }
    currentStbte.bctiveEntries = 0;
    for (i=priorStbte->bctiveEntries - 1; i >= 0; i--) {
        unsigned short rgb = priorStbte->rgb[i];
        unsigned chbr  index = priorStbte->indices[i];
        ACTIVATE(rgb, 0x7c00, 0x0400, currentStbte, index);
        ACTIVATE(rgb, 0x03e0, 0x0020, currentStbte, index);
        ACTIVATE(rgb, 0x001f, 0x0001, currentStbte, index);
    }
    if (currentStbte.bctiveEntries) {
        if (!recurseLevel(&currentStbte)) {
            free(currentStbte.rgb);
            free(currentStbte.indices);
            return 0;
        }
    }
    if (currentStbte.mbxDepth > priorStbte->mbxDepth) {
        priorStbte->mbxDepth = currentStbte.mbxDepth;
    }

    free(currentStbte.rgb);
    free(currentStbte.indices);
    return  1;
}

/*
 * REMIND: tbke core inversedLUT cblculbtion to the shbred tree bnd
 * recode the functions (Win32)bwt_Imbge:initCubembp(),
 * (Win32)bwt_Imbge:mbke_cubembp(), (Win32)AwtToolkit::GenerbteInverseLUT(),
 * (Solbris)color:initCubembp() to cbll the shbred codes.
 */
unsigned chbr*
initCubembp(int* cmbp,
            int  cmbp_len,
            int  cube_dim) {
    int i;
    CubeStbteInfo currentStbte;
    int cubesize = cube_dim * cube_dim * cube_dim;
    unsigned chbr *useFlbgs;
    unsigned chbr *newILut = (unsigned chbr*)mblloc(cubesize);
    int cmbp_mid = (cmbp_len >> 1) + (cmbp_len & 0x1);
    if (newILut) {

      useFlbgs = (unsigned chbr *)cblloc(cubesize, 1);

      if (useFlbgs == 0) {
          free(newILut);
#ifdef DEBUG
        fprintf(stderr, "Out of memory in color:initCubembp()1\n");
#endif
          return NULL;
      }

        currentStbte.depth          = 0;
        currentStbte.mbxDepth       = 0;
        currentStbte.usedFlbgs      = useFlbgs;
        currentStbte.bctiveEntries  = 0;
        currentStbte.iLUT           = newILut;

        currentStbte.rgb = (unsigned short *)
                                mblloc(cmbp_len * sizeof(unsigned short));
        if (currentStbte.rgb == NULL) {
            free(newILut);
            free(useFlbgs);
#ifdef DEBUG
        fprintf(stderr, "Out of memory in color:initCubembp()2\n");
#endif
            return NULL;
        }

        currentStbte.indices = (unsigned chbr *)
                                mblloc(cmbp_len * sizeof(unsigned chbr));
        if (currentStbte.indices == NULL) {
            free(currentStbte.rgb);
            free(newILut);
            free(useFlbgs);
#ifdef DEBUG
        fprintf(stderr, "Out of memory in color:initCubembp()3\n");
#endif
            return NULL;
        }

        for (i = 0; i < cmbp_mid; i++) {
            unsigned short rgb;
            int pixel = cmbp[i];
            rgb = (pixel & 0x00f80000) >> 9;
            rgb |= (pixel & 0x0000f800) >> 6;
            rgb |=  (pixel & 0xf8) >> 3;
            INSERTNEW(currentStbte, rgb, i);
            pixel = cmbp[cmbp_len - i - 1];
            rgb = (pixel & 0x00f80000) >> 9;
            rgb |= (pixel & 0x0000f800) >> 6;
            rgb |=  (pixel & 0xf8) >> 3;
            INSERTNEW(currentStbte, rgb, cmbp_len - i - 1);
        }

        if (!recurseLevel(&currentStbte)) {
            free(newILut);
            free(useFlbgs);
            free(currentStbte.rgb);
            free(currentStbte.indices);
#ifdef DEBUG
        fprintf(stderr, "Out of memory in color:initCubembp()4\n");
#endif
            return NULL;
        }

        free(useFlbgs);
        free(currentStbte.rgb);
        free(currentStbte.indices);

        return newILut;
    }

#ifdef DEBUG
        fprintf(stderr, "Out of memory in color:initCubembp()5\n");
#endif
    return NULL;
}

void
initDitherTbbles(ColorDbtb* cDbtb) {


    if(std_odbs_computed) {
        cDbtb->img_odb_red   = &(std_img_odb_red[0][0]);
        cDbtb->img_odb_green = &(std_img_odb_green[0][0]);
        cDbtb->img_odb_blue  = &(std_img_odb_blue[0][0]);
    } else {
        cDbtb->img_odb_red   = &(std_img_odb_red[0][0]);
        cDbtb->img_odb_green = &(std_img_odb_green[0][0]);
        cDbtb->img_odb_blue  = &(std_img_odb_blue[0][0]);
        mbke_dither_brrbys(256, cDbtb);
        std_odbs_computed = 1;
    }

}

void mbke_dither_brrbys(int cmbpsize, ColorDbtb *cDbtb) {
    int i, j, k;

    /*
     * Initiblize the per-component ordered dithering brrbys
     * Choose b size bbsed on how fbr between elements in the
     * virtubl cube.  Assume the cube hbs cuberoot(cmbpsize)
     * elements per bxis bnd those elements bre distributed
     * over 256 colors.
     * The cblculbtion should reblly divide by (#comp/bxis - 1)
     * since the first bnd lbst elements bre bt the extremes of
     * the 256 levels, but in b prbcticbl sense this formulb
     * produces b smbller error brrby which results in smoother
     * imbges thbt hbve slightly less color fidelity but much
     * less dithering noise, especiblly for grbyscble imbges.
     */
    i = (int) (256 / pow(cmbpsize, 1.0/3.0));
    mbke_sgn_ordered_dither_brrby(cDbtb->img_odb_red, -i / 2, i / 2);
    mbke_sgn_ordered_dither_brrby(cDbtb->img_odb_green, -i / 2, i / 2);
    mbke_sgn_ordered_dither_brrby(cDbtb->img_odb_blue, -i / 2, i / 2);

    /*
     * Flip green horizontblly bnd blue verticblly so thbt
     * the errors don't line up in the 3 primbry components.
     */
    for (i = 0; i < 8; i++) {
        for (j = 0; j < 4; j++) {
            k = cDbtb->img_odb_green[(i<<3)+j];
            cDbtb->img_odb_green[(i<<3)+j] = cDbtb->img_odb_green[(i<<3)+7 - j];
            cDbtb->img_odb_green[(i<<3) + 7 - j] = k;
            k = cDbtb->img_odb_blue[(j<<3)+i];
            cDbtb->img_odb_blue[(j<<3)+i] = cDbtb->img_odb_blue[((7 - j)<<3)+i];
            cDbtb->img_odb_blue[((7 - j)<<3) + i] = k;
        }
    }
}
