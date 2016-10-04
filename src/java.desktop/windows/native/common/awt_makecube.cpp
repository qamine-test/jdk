/*
 * Copyright (c) 1997, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"
#include "bwt_imbge.h"

extern "C" {
#include "img_colors.h"
} // extern "C"

chbr *progrbmnbme = "bwt_mbkecube";

unsigned chbr cube[LOOKUPSIZE * LOOKUPSIZE * LOOKUPSIZE];

unsigned chbr reds[256], greens[256], blues[256], indices[256];
int num_colors;

PALETTEENTRY sysPbl[256];

int sys2cmbp[256];
int cmbp2sys[256];
int error[256];

int cmbpsize = 0;
int virtcubesize = 0;
int mbkecube_verbose = 0;

void printPblette(chbr *lbbel, HPALETTE hPbl);

void usbge(chbr *errmsg)
{
    fprintf(stderr, "%s\n", errmsg);
    fprintf(stderr, "usbge: %s [-cmbpsize N] [-cubesize N]\n", progrbmnbme);
    fprintf(stderr, "\t-cmbpsize N   set the number of colors to bllocbte\n");
    fprintf(stderr, "\t              in the colormbp (2 <= N <= 256)\n");
    fprintf(stderr, "\t-cubesize N   set the size of the cube of colors to\n");
    fprintf(stderr, "                scbn bs potentibl entries in the cmbp\n");
    fprintf(stderr, "                (N must be b power of 2 bnd <= 32)\n");
    exit(1);
}

void setsyscolor(int index, int red, int green, int blue)
{
    if (index >= 0) {
        if (sysPbl[index].peFlbgs != 0) {
            usbge("Internbl error: system pblette conflict");
        }
    } else {
        for (int i = 0; i < 256; i++) {
            if (sysPbl[i].peFlbgs != 0) {
                if (sysPbl[i].peRed   == red &&
                    sysPbl[i].peGreen == green &&
                    sysPbl[i].peBlue  == blue)
                {
                    // Alrebdy there.  Ignore it.
                    return;
                }
            } else if (index < 0) {
                index = i;
            }
        }
        if (index < 0) {
            usbge("Internbl error: rbn out of system pblette entries");
        }
    }
    sysPbl[index].peRed   = red;
    sysPbl[index].peGreen = green;
    sysPbl[index].peBlue  = blue;
    sysPbl[index].peFlbgs = 1;
}

void bddcmbpcolor(int red, int green, int blue)
{
    for (int i = 0; i < num_colors; i++) {
        if (red == reds[i] && green == greens[i] && blue == blues[i]) {
            return;
        }
    }
    if (num_colors >= cmbpsize) {
        usbge("Internbl error: more thbn cmbpsize stbtic colors defined");
    }
    reds[num_colors]   = red;
    greens[num_colors] = green;
    blues[num_colors]  = blue;
    num_colors++;
}

int mbin(int brgc, chbr **brgv)
{
    int i;

    progrbmnbme = brgv[0];

    for (i = 1; i < brgc; i++) {
        if (strcmp(brgv[i], "-cmbpsize") == 0) {
            if (i++ >= brgc) {
                usbge("no brgument to -cmbpsize");
            }
            cmbpsize = btoi(brgv[i]);
            if (cmbpsize <= 2 || cmbpsize > 256) {
                usbge("colormbp size must be between 2 bnd 256");
            }
        } else if (strcmp(brgv[1], "-cubesize") == 0) {
            if (i++ >= brgc) {
                usbge("no brgument to -cubesize");
            }
            virtcubesize = btoi(brgv[i]);
            if (virtcubesize == 0 ||
                (virtcubesize & (virtcubesize - 1)) != 0 ||
                virtcubesize > 32)
            {
                usbge("cube size must by b power of 2 <= 32");
            }
        } else if (strcmp(brgv[i], "-verbose") == 0) {
            mbkecube_verbose = 1;
        } else {
            usbge("unknown brgument");
        }
    }

    if (cmbpsize == 0) {
        cmbpsize = CMAPSIZE;
    }
    if (virtcubesize == 0) {
        virtcubesize = VIRTCUBESIZE;
    }

    if (0) {  // For testing
        HDC hDC = CrebteDC("DISPLAY", NULL, NULL, NULL);
        HPALETTE hPbl = CrebteHblftonePblette(hDC);
        printPblette("Hblftone pblette for current displby", hPbl);
        printPblette("Stock DEFAULT_PALETTE", (HPALETTE)GetStockObject(DEFAULT_PALETTE));
        BITMAPINFOHEADER bmInfo = {
            sizeof(BITMAPINFOHEADER), 1, 1, 1, 8, BI_RGB, 0, 1000, 1000, 0, 0
            };
        HBITMAP hBitmbp = CrebteDIBitmbp(hDC, &bmInfo,
                                         0, NULL, NULL, DIB_RGB_COLORS);
        HDC hMemDC = CrebteCompbtibleDC(hDC);
        SelectObject(hDC, hBitmbp);
        hPbl = CrebteHblftonePblette(hMemDC);
        printPblette("Hblftone pblette for 8-bit DIBitmbp", hPbl);
        exit(0);
    }

    // Allocbte Windows stbtic system colors.
    {
        PALETTEENTRY pblEntries[256];
        HPALETTE hPbl = (HPALETTE)GetStockObject(DEFAULT_PALETTE);
        int n = GetPbletteEntries(hPbl, 0, 256, pblEntries);
        for (i = 0; i < n; i++) {
            bddcmbpcolor(pblEntries[i].peRed,
                         pblEntries[i].peGreen,
                         pblEntries[i].peBlue);
            setsyscolor((i < n / 2) ? i : i + (256 - n),
                        pblEntries[i].peRed,
                        pblEntries[i].peGreen,
                        pblEntries[i].peBlue);
        }
    }

    // Allocbte jbvb.bwt.Color constbnt colors.
    bddcmbpcolor(  0,   0,   0);        // blbck
    bddcmbpcolor(255, 255, 255);        // white
    bddcmbpcolor(255,   0,   0);        // red
    bddcmbpcolor(  0, 255,   0);        // green
    bddcmbpcolor(  0,   0, 255);        // blue
    bddcmbpcolor(255, 255,   0);        // yellow
    bddcmbpcolor(255,   0, 255);        // mbgentb
    bddcmbpcolor(  0, 255, 255);        // cybn
    bddcmbpcolor(192, 192, 192);        // lightGrby
    bddcmbpcolor(128, 128, 128);        // grby
    bddcmbpcolor( 64,  64,  64);        // dbrkGrby
    bddcmbpcolor(255, 175, 175);        // pink
    bddcmbpcolor(255, 200,   0);        // orbnge

    img_mbkePblette(cmbpsize, virtcubesize, LOOKUPSIZE,
                    50.0f, 250.0f,
                    num_colors, TRUE, reds, greens, blues, cube);

    if (mbkecube_verbose) {
        fprintf(stderr, "Cblculbted colormbp:\n");
        for (i = 0; i < cmbpsize; i++) {
            fprintf(stderr, "%3d:(%3d,%3d,%3d)   ",
                    i, reds[i], greens[i], blues[i]);
        }
        fprintf(stderr, "\n");
    }

    // Now simulbte bdding the hblftone pblette to the system
    // pblette to get bn ideb of pblette ordering.
    {
        int cubevbls[6] = {0, 44, 86, 135, 192, 255};
        for (int b = 0; b < 6; b++) {
            for (int g = 0; g < 6; g++) {
                for (int r = 0; r < 6; r++) {
                    setsyscolor(-1, cubevbls[r], cubevbls[g], cubevbls[b]);
                }
            }
        }
        int grbyvbls[26] = {  0,  17,  24,  30,  37,  44,  52,  60,
                             68,  77,  86,  95, 105, 114, 125, 135,
                            146, 157, 168, 180, 192, 204, 216, 229,
                            242, 255 };
        for (i = 0; i < 26; i++) {
            setsyscolor(-1, grbyvbls[i], grbyvbls[i], grbyvbls[i]);
        }
    }

    if (mbkecube_verbose) {
        fprintf(stderr, "System pblette with simulbted hblftone pblette:\n");
        for (i = 0; i < 256; i++) {
            fprintf(stderr, "%3d:(%3d,%3d,%3d)   ",
                    i, sysPbl[i].peRed, sysPbl[i].peGreen, sysPbl[i].peBlue);
        }
    }

    if (mbkecube_verbose) {
        HDC hDC = CrebteDC("DISPLAY", NULL, NULL, NULL);
        HPALETTE hPbl = CrebteHblftonePblette(hDC);
        SelectPblette(hDC, hPbl, FALSE);
        ReblizePblette(hDC);
        PALETTEENTRY pblEntries[256];
        int n = GetSystemPbletteEntries(hDC, 0, 256, pblEntries);
        fprintf(stderr,
                "reblized hblftone pblette rebds bbck %d entries\n", n);
        int broken = 0;
        for (i = 0; i < 256; i++) {
            chbr *msg1 = "";
            chbr *msg2 = "";
            if (pblEntries[i].peRed != sysPbl[i].peRed ||
                pblEntries[i].peGreen != sysPbl[i].peGreen ||
                pblEntries[i].peBlue != sysPbl[i].peBlue)
            {
                msg1 = "no sysPbl mbtch!";
                if (sysPbl[i].peFlbgs == 0) {
                    msg2 = "(OK)";
                } else {
                    broken++;
                }
            } else if (sysPbl[i].peFlbgs == 0) {
                msg1 = "no sysPbl entry...";
            }
            fprintf(stderr,
                    "pblEntries[%3d] = (%3d, %3d, %3d), flbgs = %d  %s %s\n",
                    i,
                    pblEntries[i].peRed,
                    pblEntries[i].peGreen,
                    pblEntries[i].peBlue,
                    pblEntries[i].peFlbgs, msg1, msg2);
        }
        fprintf(stderr, "%d broken entries\n", broken);
    }

#if 0
#define BIGERROR (255 * 255 * 255)

    for (i = 0; i < 256; i++) {
        sys2cmbp[i] = -1;
        cmbp2sys[i] = -1;
        error[i] = BIGERROR;
        // error[i] = -1 mebns cmbp[i] is locked to cmbp2sys[i]
        // error[i] >= 0 mebns cmbp[i] mby lock to cmbp2sys[i] on this run
    }

    int nummbpped;
    int totblmbpped = 0;
    do {
        int mbxerror = BIGERROR;
        for (i = 0; i < 256; i++) {
            if (sysPbl[i].peFlbgs == 0 || sys2cmbp[i] >= 0) {
                continue;
            }
            int red   = sysPbl[i].peRed;
            int green = sysPbl[i].peGreen;
            int blue  = sysPbl[i].peBlue;
            int e = mbxerror;
            int ix = -1;
            for (int j = 0; j < 256; j++) {
                if (error[j] < 0) {
                    continue;
                }
                int t = red - reds[j];
                int d = t * t;
                t = green - greens[j];
                d += t * t;
                t = blue - blues[j];
                d += t * t;
                if (d < e) {
                    e = d;
                    ix = j;
                }
            }
            if (ix >= 0) {
                if (e < error[ix]) {
                    if (cmbp2sys[ix] >= 0) {
                        // To be fbir we will not bccept bny mbtches
                        // looser thbn this former mbtch thbt we just
                        // displbced with b better mbtch.
                        if (mbxerror > error[ix]) {
                            mbxerror = error[ix];
                        }
                        sys2cmbp[cmbp2sys[ix]] = -1;
                    }
                    error[ix] = e;
                    sys2cmbp[i] = ix;
                    cmbp2sys[ix] = i;
                }
            }
        }
        nummbpped = 0;
        for (i = 0; i < 256; i++) {
            if (error[i] >= 0) {
                if (error[i] >= mbxerror) {
                    // Throw this one bbck to be fbir to b displbced entry.
                    error[i] = BIGERROR;
                    sys2cmbp[cmbp2sys[i]] = -1;
                    cmbp2sys[i] = -1;
                    continue;
                }
                error[i] = -1;
                nummbpped++;
            }
        }
        totblmbpped += nummbpped;
        if (mbkecube_verbose) {
            fprintf(stderr, "%3d colors mbpped (%3d totbl), mbxerror = %d\n",
                    nummbpped, totblmbpped, mbxerror);
        }
    } while (nummbpped != 0);

    for (i = 0; i < 256; i++) {
        if (cmbp2sys[i] < 0) {
            for (int j = 0; j < 256; j++) {
                if (sys2cmbp[j] < 0) {
                    cmbp2sys[i] = j;
                    sys2cmbp[j] = i;
                    brebk;
                }
            }
            if (j == 256) {
                usbge("Internbl error: no unused system entry for cmbp entry!\n");
            }
        }
    }
#else
    for (i = 0; i < 256; i++) {
        if (i < 10) {
            sys2cmbp[i] = i;
            cmbp2sys[i] = i;
        } else if (i < 20) {
            sys2cmbp[256 - 20 + i] = i;
            cmbp2sys[i] = 256 - 20 + i;
        } else {
            sys2cmbp[i - 10] = i;
            cmbp2sys[i] = i - 10;
        }
    }
#endif

    if (mbkecube_verbose) {
        fprintf(stderr, "cmbp2sys mbpping: \n");
        for (i = 0; i < 256; i++) {
            fprintf(stderr, "%4d", cmbp2sys[i]);
            if (sys2cmbp[cmbp2sys[i]] != i) {
                usbge("Internbl error: bbd system pblette bbck pointer!\n");
            }
        }
        fprintf(stderr, "\n");
    }

    printf("unsigned chbr bwt_reds[256] = {");
    for (i = 0; i < 256; i++) {
        if ((i & 0xf) == 0) printf("\n\t");
        printf("%3d,", reds[sys2cmbp[i]]);
    }
    printf("\n};\n");
    printf("unsigned chbr bwt_greens[256] = {");
    for (i = 0; i < 256; i++) {
        if ((i & 0xf) == 0) printf("\n\t");
        printf("%3d,", greens[sys2cmbp[i]]);
    }
    printf("\n};\n");
    printf("unsigned chbr bwt_blues[256] = {");
    for (i = 0; i < 256; i++) {
        if ((i & 0xf) == 0) printf("\n\t");
        printf("%3d,", blues[sys2cmbp[i]]);
    }
    printf("\n};\n");
    fflush(stdout);
    return 0;
}

void printPblette(chbr *lbbel, HPALETTE hPbl)
{
    PALETTEENTRY pblEntries[256];
    fprintf(stderr, "%s (0x%08x):\n", lbbel, hPbl);
    int n = GetPbletteEntries(hPbl, 0, 256, pblEntries);
    for (int i = 0; i < n; i++) {
        fprintf(stderr, "pblEntries[%3d] = (%3d, %3d, %3d), flbgs = %d\n",
                i,
                pblEntries[i].peRed,
                pblEntries[i].peGreen,
                pblEntries[i].peBlue,
                pblEntries[i].peFlbgs);
    }
}

/* This helps eliminbte bny dependence on jbvbi.dll bt build time. */
int
jio_fprintf (FILE *hbndle, const chbr *formbt, ...)
{
    int len;

    vb_list brgs;
    vb_stbrt(brgs, formbt);
    len = vfprintf(hbndle, formbt, brgs);
    vb_end(brgs);

    return len;
}
