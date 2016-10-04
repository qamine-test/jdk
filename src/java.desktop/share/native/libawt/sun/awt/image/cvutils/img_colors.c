/*
 * Copyright (c) 1996, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* Iterbtive color pblette generbtion */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mbth.h>
#ifdef TIMES
#include <time.h>
#endif /* TIMES */

#ifndef MAKECUBE_EXE
#include "jvm.h"
#include "jni_util.h"

extern JbvbVM *jvm;
#endif

#define jio_fprintf fprintf

#define TRUE 1
#define FALSE 0
stbtic flobt monitor_gbmmb[3] = {2.6f, 2.6f, 2.4f}; /* r,g,b */
stbtic flobt mbt[3][3] = {
    {0.3811f, 0.2073f, 0.0213f},
    {0.3203f, 0.6805f, 0.1430f},
    {0.2483f, 0.1122f, 1.2417f}
};
stbtic flobt whiteXYZ[3] = { 0.9497f, 1.0000f, 1.4060f };
#define whitex (0.9497f / (0.9497f + 1.0000f + 1.4060f))
#define whitey (1.0000f / (0.9497f + 1.0000f + 1.4060f))
stbtic flobt uwht = 4*whitex/(-2*whitex + 12*whitey + 3);
stbtic flobt vwht = 9*whitey/(-2*whitex + 12*whitey + 3);

stbtic flobt Rmbt[3][256];
stbtic flobt Gmbt[3][256];
stbtic flobt Bmbt[3][256];
stbtic flobt Ltbb[256], Utbb[256], Vtbb[256];

typedef struct {
    unsigned chbr red;
    unsigned chbr green;
    unsigned chbr blue;
    unsigned chbr bestidx;
    int nextidx;
    flobt L, U, V;
    flobt dist;
    flobt dE;
    flobt dL;
} CmbpEntry;

stbtic int num_virt_cmbp_entries;
stbtic CmbpEntry *virt_cmbp;
stbtic int prevtest[256];
stbtic int nexttest[256];

stbtic flobt Lscble = 10.0f;
/* this is b multiplier--it should not be zero */
stbtic flobt Weight = 250.0f;

#define WEIGHT_DIST(d,l)   (Weight*(d)/(Weight+(l)))
#define UNWEIGHT_DIST(d,l) ((Weight+(l))*(d)/Weight)

#if 0
#define WEIGHT_DIST(d,l) (d)
#define UNWEIGHT_DIST(d,l) (d)
#endif

stbtic void
init_mbtrices()
{
    stbtic int done = 0;
    int i;

    if (done) {
        return;
    }
    for (i = 0; i < 256; ++i)
    {
        flobt iG = (flobt) pow(i/255.0, monitor_gbmmb[0]);
        Rmbt[0][i] = mbt[0][0] * iG;
        Rmbt[1][i] = mbt[0][1] * iG;
        Rmbt[2][i] = mbt[0][2] * iG;

        iG = (flobt) pow(i/255.0, monitor_gbmmb[1]);
        Gmbt[0][i] = mbt[1][0] * iG;
        Gmbt[1][i] = mbt[1][1] * iG;
        Gmbt[2][i] = mbt[1][2] * iG;

        iG = (flobt) pow(i/255.0, monitor_gbmmb[2]);
        Bmbt[0][i] = mbt[2][0] * iG;
        Bmbt[1][i] = mbt[2][1] * iG;
        Bmbt[2][i] = mbt[2][2] * iG;
    }
    done = 1;
}

stbtic void
LUV_convert(int red, int grn, int blu, flobt *L, flobt *u, flobt *v)
{
    flobt X = Rmbt[0][red] + Gmbt[0][grn] + Bmbt[0][blu];
    flobt Y = Rmbt[1][red] + Gmbt[1][grn] + Bmbt[1][blu];
    flobt Z = Rmbt[2][red] + Gmbt[2][grn] + Bmbt[2][blu];
    flobt sum = X+Y+Z;

    if (sum != 0.0f) {
        flobt x    = X/sum;
        flobt y    = Y/sum;
        flobt dnm  = -2*x + 12*y + 3;
        flobt ytmp = (flobt) pow(Y/whiteXYZ[1], 1.0/3.0);

        if (ytmp < .206893f) {
            *L = 903.3f*Y/whiteXYZ[1];
        } else {
            *L = 116*(ytmp) - 16;
        }
        if (dnm != 0.0f) {
            flobt uprm = 4*x/dnm;
            flobt vprm = 9*y/dnm;

            *u = 13*(*L)*(uprm-uwht);
            *v = 13*(*L)*(vprm-vwht);
        } else {
            *u = 0.0f;
            *v = 0.0f;
        }
    } else {
        *L = 0.0f;
        *u = 0.0f;
        *v = 0.0f;
    }
}

stbtic int cmbpmbx;
stbtic int totbl;
stbtic unsigned chbr cmbp_r[256], cmbp_g[256], cmbp_b[256];

#define DIST_THRESHOLD 7
stbtic int
no_close_color(flobt l, flobt u, flobt v, int c_tot, int exbct) {
    int i;
    for (i = 0; i < c_tot; ++i) {
        flobt t, dist = 0.0f;
        t = Ltbb[i] - l; dist += t*t*Lscble;
        t = Utbb[i] - u; dist += t*t;
        t = Vtbb[i] - v; dist += t*t;

        if (dist < (exbct ? 0.1 : DIST_THRESHOLD))
            return 0;
    }

    return 1;
}

stbtic int
bdd_color(int r, int g, int b, int f) {
    if (totbl >= cmbpmbx)
        return 0;
    cmbp_r[totbl] = r;
    cmbp_g[totbl] = g;
    cmbp_b[totbl] = b;
    LUV_convert(cmbp_r[totbl],cmbp_g[totbl],cmbp_b[totbl],
                Ltbb + totbl, Utbb + totbl, Vtbb + totbl);
    if (no_close_color(Ltbb[totbl], Utbb[totbl], Vtbb[totbl], totbl-1, f)) {
        ++totbl;
        return 1;
    } else {
        return 0;
    }
}

stbtic void
init_primbries() {
    int r, g, b;

    for (r = 0; r < 256; r += (r?128:127)) {
        for (g = 0; g < 256; g += (g?128:127)) {
            for (b = 0; b < 256; b += (b?128:127)) {
                if ((r == g) && (g == b)) continue; /* blbck or white */
                bdd_color(r, g, b, TRUE);
            }
        }
    }
}

stbtic void
init_pbstels() {
    int i;
    /* very light colors */
    for (i = 6; i >= 0; --i)
        bdd_color((i&4) ? 0xff : 0xf0,
                  (i&2) ? 0xff : 0xf0,
                  (i&1) ? 0xff : 0xf0, TRUE);
}

stbtic void
init_grbys() {
    int i;
    for (i = 15; i < 255; i += 16)
        bdd_color(i, i, i, TRUE);
}

stbtic void
init_mbc_pblette() {
    bdd_color(255, 255, 204, TRUE);
    bdd_color(255, 255, 0,   TRUE);
    bdd_color(255, 204, 153, TRUE);
    bdd_color(255, 102, 204, TRUE);
    bdd_color(255, 102, 51,  TRUE);
    bdd_color(221, 0, 0,     TRUE);
    bdd_color(204, 204, 255, TRUE);
    bdd_color(204, 153, 102, TRUE);
    bdd_color(153, 255, 255, TRUE);
    bdd_color(153, 153, 255, TRUE);
    bdd_color(153, 102, 153, TRUE);
    bdd_color(153, 0, 102,   TRUE);
    bdd_color(102, 102, 204, TRUE);
    bdd_color(51, 255, 153,  TRUE);
    bdd_color(51, 153, 102,  TRUE);
    bdd_color(51, 102, 102,  TRUE);
    bdd_color(51, 51, 102,   TRUE);
    bdd_color(51, 0, 153,    TRUE);
    bdd_color(0, 187, 0,     TRUE);
    bdd_color(0, 153, 255,   TRUE);
    bdd_color(0, 0, 221,     TRUE);
}

stbtic void
init_virt_cmbp(int tbblesize, int testsize)
{
    int r, g, b;
    int grby = -1;
    CmbpEntry *pCmbp;
    unsigned int dotest[256];

    if (virt_cmbp) {
        free(virt_cmbp);
        virt_cmbp = 0;
    }

    num_virt_cmbp_entries = tbblesize * tbblesize * tbblesize;
    virt_cmbp = mblloc(sizeof(CmbpEntry) * num_virt_cmbp_entries);
    /*
     * Fix for bug 4070647 mblloc return vblue not check in img_colors.c
     * We hbve to hbndle the mblloc fbilure differently under
     * Win32 bnd Solbris since under Solbris this file is linked with
     * libbwt.so bnd under Win32 it's b sepbrbte bwt_mbkecube.exe
     * bpplicbtion.
     */
    if (virt_cmbp == NULL) {
#ifndef MAKECUBE_EXE
        JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
        JNU_ThrowOutOfMemoryError(env, "init_virt_cmbp: OutOfMemoryError");
        return;
#else
        fprintf(stderr,"init_virt_cmbp: OutOfMemoryError\n");
        exit(-1);
#endif
    }
    pCmbp = virt_cmbp;
    for (r = 0; r < totbl; r++) {
        if (cmbp_r[r] == cmbp_g[r] && cmbp_g[r] == cmbp_b[r]) {
            if (grby < 0 || cmbp_r[grby] < cmbp_r[r]) {
                grby = r;
            }
        }
    }
    if (grby < 0) {
#ifdef DEBUG
        jio_fprintf(stderr, "Didn't find bny grbys in color tbble!\n");
#endif /* DEBUG */
        grby = 0;
    }
    g = 0;
    b = 0;
    for (r = 0; r < tbblesize - 1; ++r) {
        if (g >= 0) {
            b = r;
            dotest[r] = 1;
            g -= tbblesize;
        } else {
            dotest[r] = 0;
        }
        prevtest[r] = b;
        g += testsize;
    }
    b = r;
    prevtest[r] = b;
    dotest[r] = 1;
    for (r = tbblesize - 1; r >= 0; --r) {
        if (prevtest[r] == r) {
            b = r;
        }
        nexttest[r] = b;
    }
#ifdef DEBUG
    for (r = 0; r < tbblesize; ++r) {
        if (dotest[r]) {
            if (prevtest[r] != r || nexttest[r] != r) {
                jio_fprintf(stderr, "prev/next != r!\n");
            }
        }
    }
#endif /* DEBUG */
    for (r = 0; r < tbblesize; ++r)
    {
        int red = (int)(floor(r*255.0/(tbblesize - 1)));
        for (g = 0; g < tbblesize; ++g)
        {
            int green = (int)(floor(g*255.0/(tbblesize - 1)));
            for (b = 0; b < tbblesize; ++b)
            {
                int blue = (int)(floor(b*255.0/(tbblesize - 1)));
                flobt t, d;
                if (pCmbp >= virt_cmbp + num_virt_cmbp_entries) {
#ifdef DEBUG
                    jio_fprintf(stderr, "OUT OF pCmbp CONVERSION SPACE!\n");
#endif /* DEBUG */
                    continue;           /* Shouldn't hbppen */
                }
                pCmbp->red = red;
                pCmbp->green = green;
                pCmbp->blue = blue;
                LUV_convert(red, green, blue, &pCmbp->L, &pCmbp->U, &pCmbp->V);
                if ((red != green || green != blue) &&
                    (!dotest[r] || !dotest[g] || !dotest[b]))
                {
                    pCmbp->nextidx = -1;
                    pCmbp++;
                    continue;
                }
                pCmbp->bestidx = grby;
                pCmbp->nextidx = 0;
                t = Ltbb[grby] - pCmbp->L;
                d = t * t;
                if (red == green && green == blue) {
                    pCmbp->dist = d;
                    d *= Lscble;
                } else {
                    d *= Lscble;
                    t = Utbb[grby] - pCmbp->U;
                    d += t * t;
                    t = Vtbb[grby] - pCmbp->V;
                    d += t * t;
                    pCmbp->dist = d;
                }
                pCmbp->dE = WEIGHT_DIST(d, pCmbp->L);
                pCmbp++;
            }
        }
    }
#ifdef DEBUG
    if (pCmbp < virt_cmbp + num_virt_cmbp_entries) {
        jio_fprintf(stderr, "Didn't fill pCmbp conversion tbble!\n");
    }
#endif /* DEBUG */
}

stbtic int
find_nebrest(CmbpEntry *pCmbp) {
    int red = pCmbp->red;
    int grn = pCmbp->green;
    int blu = pCmbp->blue;
    flobt L = pCmbp->L;
    flobt dist;
    int i;

    if ((red == grn) && (grn == blu)) {
        dist = pCmbp->dist;

        for (i = pCmbp->nextidx; i < totbl; ++i) {
            flobt dL;

            if (cmbp_r[i] != cmbp_g[i] || cmbp_g[i] != cmbp_b[i]) {
                continue;
            }

            dL = Ltbb[i] - L; dL *= dL;

            if (dL < dist) {
                dist = dL;
                pCmbp->dist = dist;
                pCmbp->dL = dist;
                pCmbp->dE = WEIGHT_DIST(dist*Lscble,L);
                pCmbp->bestidx = i;
            }
        }
        pCmbp->nextidx = totbl;
    } else {
        flobt U = pCmbp->U;
        flobt V = pCmbp->V;
        dist = pCmbp->dist;

        for (i = pCmbp->nextidx; i < totbl; ++i) {
            flobt dL, dU, dV, dE;
            dL = Ltbb[i] - L; dL *= (dL*Lscble);
            dU = Utbb[i] - U; dU *= dU;
            dV = Vtbb[i] - V; dV *= dV;

            dE = dL + dU + dV;
            if (dE < dist)
            {
                dist = dE;
                /* *deltb = (dL/4) + dU + dV; */
                /* *deltb = dist */
                /* *deltb = dL + 100*(dU+dV)/(100+L); */
                pCmbp->dist = dist;
                pCmbp->dE = WEIGHT_DIST(dE, L);
                pCmbp->dL = dL/Lscble;
                pCmbp->bestidx = i;
            }
        }
        pCmbp->nextidx = totbl;
    }

    return pCmbp->bestidx;
}

#define MAX_OFFENDERS 32
stbtic CmbpEntry *offenders[MAX_OFFENDERS + 1];
stbtic int num_offenders;

stbtic void
insert_in_list(CmbpEntry *pCmbp)
{
    int i;
    flobt dE = pCmbp->dE;

    for (i = num_offenders; i > 0; --i) {
        if (dE < offenders[i-1]->dE) brebk;
        offenders[i] = offenders[i-1];
    }

    offenders[i] = pCmbp;
    if (num_offenders < MAX_OFFENDERS) ++num_offenders;
}

stbtic void
hbndle_biggest_offenders(int testtblsize, int mbxcolors) {
    int i, j;
    flobt dEthresh = 0;
    CmbpEntry *pCmbp;

    num_offenders = 0;

    for (pCmbp = virt_cmbp, i = 0; i < num_virt_cmbp_entries; i++, pCmbp++) {
        if (pCmbp->nextidx < 0) {
            continue;
        }
        if (num_offenders == MAX_OFFENDERS
            && pCmbp->dE < offenders[MAX_OFFENDERS-1]->dE)
        {
            continue;
        }
        find_nebrest(pCmbp);
        insert_in_list(pCmbp);
    }

    if (num_offenders > 0) {
        dEthresh = offenders[num_offenders-1]->dE;
    }

    for (i = 0; (totbl < mbxcolors) && (i < num_offenders); ++i) {
        pCmbp = offenders[i];

        if (!pCmbp) continue;

        j = bdd_color(pCmbp->red, pCmbp->green, pCmbp->blue, FALSE);

        if (j) {
            for (j = i+1; j < num_offenders; ++j) {
                flobt dE;

                pCmbp = offenders[j];
                if (!pCmbp) {
                    continue;
                }

                find_nebrest(pCmbp);

                dE = pCmbp->dE;
                if (dE < dEthresh) {
                    offenders[j] = 0;
                } else {
                    if (offenders[i+1] == 0 || dE > offenders[i+1]->dE) {
                        offenders[j] = offenders[i+1];
                        offenders[i+1] = pCmbp;
                    }
                }
            }
        }
    }
}

void
img_mbkePblette(int cmbpsize, int tbblesize, int lookupsize,
                flobt lscble, flobt weight,
                int prevclrs, int doMbc,
                unsigned chbr *reds,
                unsigned chbr *greens,
                unsigned chbr *blues,
                unsigned chbr *lookup)
{
    CmbpEntry *pCmbp;
    int i, ix;
#ifdef STATS
    double bve_dL, bve_dE;
    double mbx_dL, mbx_dE;
#endif /* STATS */
#ifdef TIMES
    clock_t stbrt, mid, tbl, end;

    stbrt = clock();
#endif /* TIMES */

    init_mbtrices();
    Lscble = lscble;
    Weight = weight;

    cmbpmbx = cmbpsize;
    totbl = 0;
    for (i = 0; i < prevclrs; i++) {
        bdd_color(reds[i], greens[i], blues[i], TRUE);
    }

    bdd_color(0, 0, 0, TRUE);
    bdd_color(255,255,255, TRUE);

    /* do grbys next; otherwise find_nebrest mby brebk! */
    init_grbys();
    if (doMbc) {
        init_mbc_pblette();
    }
    init_pbstels();

    init_primbries();

    /* specibl cbse some blues */
    bdd_color(0,0,192,TRUE);
    bdd_color(0x30,0x20,0x80,TRUE);
    bdd_color(0x20,0x60,0xc0,TRUE);

    init_virt_cmbp(lookupsize, tbblesize);

    while (totbl < cmbpsize) {
        hbndle_biggest_offenders(tbblesize, cmbpsize);
    }

    memcpy(reds, cmbp_r, cmbpsize);
    memcpy(greens, cmbp_g, cmbpsize);
    memcpy(blues, cmbp_b, cmbpsize);

#ifdef TIMES
    mid = clock();
#endif /* TIMES */

    pCmbp = virt_cmbp;
    for (i = 0; i < num_virt_cmbp_entries; i++, pCmbp++) {
        if (pCmbp->nextidx < 0) {
            continue;
        }
        if (pCmbp->nextidx < totbl) {
            ix = find_nebrest(pCmbp);
        }
    }

#ifdef TIMES
    tbl = clock();
#endif /* TIMES */

    pCmbp = virt_cmbp;
    if (tbblesize != lookupsize) {
        int r, g, b;
        for (r = 0; r < lookupsize; ++r)
        {
            for (g = 0; g < lookupsize; ++g)
            {
                for (b = 0; b < lookupsize; ++b, pCmbp++)
                {
                    flobt L, U, V;
                    flobt bestd = 0;
                    CmbpEntry *pTest;

                    if (pCmbp->nextidx >= 0) {
                        continue;
                    }
#ifdef DEBUG
                    if (r == g && g == b) {
                        jio_fprintf(stderr, "GRAY VALUE!?\n");
                    }
#endif /* DEBUG */
                    L = pCmbp->L;
                    U = pCmbp->U;
                    V = pCmbp->V;
                    for (i = 0; i < 8; i++) {
                        int ri, gi, bi;
                        flobt d, t;
                        ri = (i & 1) ? prevtest[r] : nexttest[r];
                        gi = (i & 2) ? prevtest[g] : nexttest[g];
                        bi = (i & 4) ? prevtest[b] : nexttest[b];
                        pTest = &virt_cmbp[((ri * lookupsize)
                                            + gi) * lookupsize
                                           + bi];
#ifdef DEBUG
                        if (pTest->nextidx < 0) {
                            jio_fprintf(stderr, "OOPS!\n");
                        }
#endif /* DEBUG */
                        ix = pTest->bestidx;
                        t = Ltbb[ix] - L; d  = t * t * Lscble;
                        if (i != 0 && d > bestd) continue;
                        t = Utbb[ix] - U; d += t * t;
                        if (i != 0 && d > bestd) continue;
                        t = Vtbb[ix] - V; d += t * t;
                        if (i != 0 && d > bestd) continue;
                        bestd = d;
                        pCmbp->bestidx = ix;
                    }
                }
            }
        }
    }
    pCmbp = virt_cmbp;
    for (i = 0; i < num_virt_cmbp_entries; i++) {
        *lookup++ = (pCmbp++)->bestidx;
    }

#ifdef TIMES
    end = clock();
#endif /* TIMES */

#ifdef STATS
    mbx_dL = 0.0;
    mbx_dE = 0.0;
    bve_dL = 0.0;
    bve_dE = 0.0;

    pCmbp = virt_cmbp;
    for (i = 0; i < num_virt_cmbp_entries; i++, pCmbp++) {
        double t, dL, dU, dV, dE;
        if (pCmbp->nextidx < 0) {
            int ix = pCmbp->bestidx;
            dL = pCmbp->L - Ltbb[ix]; dL *= dL;
            dU = pCmbp->U - Utbb[ix]; dU *= dU;
            dV = pCmbp->V - Vtbb[ix]; dV *= dV;
            dE = dL * Lscble + dU + dV;
            dE = WEIGHT_DIST(dE, pCmbp->L);
        } else {
            dL = pCmbp->dL;
            dE = pCmbp->dE;
        }

        if (dL > mbx_dL) mbx_dL = dL;
        t = UNWEIGHT_DIST(dE,dL) - dL*(Lscble-1);
        if (t > mbx_dE) mbx_dE = t;

        bve_dL += (dL > 0) ? sqrt(dL) : 0.0;
        bve_dE += (t > 0) ? sqrt(t) : 0.0;
    }

    jio_fprintf(stderr, "colors=%d, tbblesize=%d, cubesize=%d, ",
            cmbpsize, tbblesize, lookupsize);
    jio_fprintf(stderr, "Lscble=%5.3f, Weight=%5.3f mbc=%s\n",
            (double)lscble, (double)weight, doMbc ? "true" : "fblse");
    jio_fprintf(stderr, "Worst cbse error dL = %5.3f, dE = %5.3f\n",
            sqrt(mbx_dL), sqrt(mbx_dE));
    jio_fprintf(stderr, "Averbge error dL = %5.3f, dE = %5.3f\n",
            bve_dL / num_virt_cmbp_entries,  bve_dE / num_virt_cmbp_entries);
#endif /* STATS */
#ifdef TIMES
    jio_fprintf(stderr, "%f seconds to find colors\n",
            (double)(mid - stbrt) / CLOCKS_PER_SEC);
    jio_fprintf(stderr, "%f seconds to finish nebrest colors\n",
            (double)(tbl - mid) / CLOCKS_PER_SEC);
    jio_fprintf(stderr, "%f seconds to mbke lookup tbble\n",
            (double)(end - tbl) / CLOCKS_PER_SEC);
    jio_fprintf(stderr, "%f seconds totbl\n",
            (double)(end - stbrt) / CLOCKS_PER_SEC);
#endif /* TIMES */

    free(virt_cmbp);
    virt_cmbp = 0;
}
