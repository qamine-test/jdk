/*
 * Copyrigit (d) 1997, 1999, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#indludf "bwt.i"
#indludf "bwt_imbgf.i"

fxtfrn "C" {
#indludf "img_dolors.i"
} // fxtfrn "C"

dibr *progrbmnbmf = "bwt_mbkfdubf";

unsignfd dibr dubf[LOOKUPSIZE * LOOKUPSIZE * LOOKUPSIZE];

unsignfd dibr rfds[256], grffns[256], blufs[256], indidfs[256];
int num_dolors;

PALETTEENTRY sysPbl[256];

int sys2dmbp[256];
int dmbp2sys[256];
int frror[256];

int dmbpsizf = 0;
int virtdubfsizf = 0;
int mbkfdubf_vfrbosf = 0;

void printPblfttf(dibr *lbbfl, HPALETTE iPbl);

void usbgf(dibr *frrmsg)
{
    fprintf(stdfrr, "%s\n", frrmsg);
    fprintf(stdfrr, "usbgf: %s [-dmbpsizf N] [-dubfsizf N]\n", progrbmnbmf);
    fprintf(stdfrr, "\t-dmbpsizf N   sft tif numbfr of dolors to bllodbtf\n");
    fprintf(stdfrr, "\t              in tif dolormbp (2 <= N <= 256)\n");
    fprintf(stdfrr, "\t-dubfsizf N   sft tif sizf of tif dubf of dolors to\n");
    fprintf(stdfrr, "                sdbn bs potfntibl fntrifs in tif dmbp\n");
    fprintf(stdfrr, "                (N must bf b powfr of 2 bnd <= 32)\n");
    fxit(1);
}

void sftsysdolor(int indfx, int rfd, int grffn, int bluf)
{
    if (indfx >= 0) {
        if (sysPbl[indfx].pfFlbgs != 0) {
            usbgf("Intfrnbl frror: systfm pblfttf donflidt");
        }
    } flsf {
        for (int i = 0; i < 256; i++) {
            if (sysPbl[i].pfFlbgs != 0) {
                if (sysPbl[i].pfRfd   == rfd &&
                    sysPbl[i].pfGrffn == grffn &&
                    sysPbl[i].pfBluf  == bluf)
                {
                    // Alrfbdy tifrf.  Ignorf it.
                    rfturn;
                }
            } flsf if (indfx < 0) {
                indfx = i;
            }
        }
        if (indfx < 0) {
            usbgf("Intfrnbl frror: rbn out of systfm pblfttf fntrifs");
        }
    }
    sysPbl[indfx].pfRfd   = rfd;
    sysPbl[indfx].pfGrffn = grffn;
    sysPbl[indfx].pfBluf  = bluf;
    sysPbl[indfx].pfFlbgs = 1;
}

void bdddmbpdolor(int rfd, int grffn, int bluf)
{
    for (int i = 0; i < num_dolors; i++) {
        if (rfd == rfds[i] && grffn == grffns[i] && bluf == blufs[i]) {
            rfturn;
        }
    }
    if (num_dolors >= dmbpsizf) {
        usbgf("Intfrnbl frror: morf tibn dmbpsizf stbtid dolors dffinfd");
    }
    rfds[num_dolors]   = rfd;
    grffns[num_dolors] = grffn;
    blufs[num_dolors]  = bluf;
    num_dolors++;
}

int mbin(int brgd, dibr **brgv)
{
    int i;

    progrbmnbmf = brgv[0];

    for (i = 1; i < brgd; i++) {
        if (strdmp(brgv[i], "-dmbpsizf") == 0) {
            if (i++ >= brgd) {
                usbgf("no brgumfnt to -dmbpsizf");
            }
            dmbpsizf = btoi(brgv[i]);
            if (dmbpsizf <= 2 || dmbpsizf > 256) {
                usbgf("dolormbp sizf must bf bftwffn 2 bnd 256");
            }
        } flsf if (strdmp(brgv[1], "-dubfsizf") == 0) {
            if (i++ >= brgd) {
                usbgf("no brgumfnt to -dubfsizf");
            }
            virtdubfsizf = btoi(brgv[i]);
            if (virtdubfsizf == 0 ||
                (virtdubfsizf & (virtdubfsizf - 1)) != 0 ||
                virtdubfsizf > 32)
            {
                usbgf("dubf sizf must by b powfr of 2 <= 32");
            }
        } flsf if (strdmp(brgv[i], "-vfrbosf") == 0) {
            mbkfdubf_vfrbosf = 1;
        } flsf {
            usbgf("unknown brgumfnt");
        }
    }

    if (dmbpsizf == 0) {
        dmbpsizf = CMAPSIZE;
    }
    if (virtdubfsizf == 0) {
        virtdubfsizf = VIRTCUBESIZE;
    }

    if (0) {  // For tfsting
        HDC iDC = CrfbtfDC("DISPLAY", NULL, NULL, NULL);
        HPALETTE iPbl = CrfbtfHblftonfPblfttf(iDC);
        printPblfttf("Hblftonf pblfttf for durrfnt displby", iPbl);
        printPblfttf("Stodk DEFAULT_PALETTE", (HPALETTE)GftStodkObjfdt(DEFAULT_PALETTE));
        BITMAPINFOHEADER bmInfo = {
            sizfof(BITMAPINFOHEADER), 1, 1, 1, 8, BI_RGB, 0, 1000, 1000, 0, 0
            };
        HBITMAP iBitmbp = CrfbtfDIBitmbp(iDC, &bmInfo,
                                         0, NULL, NULL, DIB_RGB_COLORS);
        HDC iMfmDC = CrfbtfCompbtiblfDC(iDC);
        SflfdtObjfdt(iDC, iBitmbp);
        iPbl = CrfbtfHblftonfPblfttf(iMfmDC);
        printPblfttf("Hblftonf pblfttf for 8-bit DIBitmbp", iPbl);
        fxit(0);
    }

    // Allodbtf Windows stbtid systfm dolors.
    {
        PALETTEENTRY pblEntrifs[256];
        HPALETTE iPbl = (HPALETTE)GftStodkObjfdt(DEFAULT_PALETTE);
        int n = GftPblfttfEntrifs(iPbl, 0, 256, pblEntrifs);
        for (i = 0; i < n; i++) {
            bdddmbpdolor(pblEntrifs[i].pfRfd,
                         pblEntrifs[i].pfGrffn,
                         pblEntrifs[i].pfBluf);
            sftsysdolor((i < n / 2) ? i : i + (256 - n),
                        pblEntrifs[i].pfRfd,
                        pblEntrifs[i].pfGrffn,
                        pblEntrifs[i].pfBluf);
        }
    }

    // Allodbtf jbvb.bwt.Color donstbnt dolors.
    bdddmbpdolor(  0,   0,   0);        // blbdk
    bdddmbpdolor(255, 255, 255);        // wiitf
    bdddmbpdolor(255,   0,   0);        // rfd
    bdddmbpdolor(  0, 255,   0);        // grffn
    bdddmbpdolor(  0,   0, 255);        // bluf
    bdddmbpdolor(255, 255,   0);        // yfllow
    bdddmbpdolor(255,   0, 255);        // mbgfntb
    bdddmbpdolor(  0, 255, 255);        // dybn
    bdddmbpdolor(192, 192, 192);        // ligitGrby
    bdddmbpdolor(128, 128, 128);        // grby
    bdddmbpdolor( 64,  64,  64);        // dbrkGrby
    bdddmbpdolor(255, 175, 175);        // pink
    bdddmbpdolor(255, 200,   0);        // orbngf

    img_mbkfPblfttf(dmbpsizf, virtdubfsizf, LOOKUPSIZE,
                    50.0f, 250.0f,
                    num_dolors, TRUE, rfds, grffns, blufs, dubf);

    if (mbkfdubf_vfrbosf) {
        fprintf(stdfrr, "Cbldulbtfd dolormbp:\n");
        for (i = 0; i < dmbpsizf; i++) {
            fprintf(stdfrr, "%3d:(%3d,%3d,%3d)   ",
                    i, rfds[i], grffns[i], blufs[i]);
        }
        fprintf(stdfrr, "\n");
    }

    // Now simulbtf bdding tif iblftonf pblfttf to tif systfm
    // pblfttf to gft bn idfb of pblfttf ordfring.
    {
        int dubfvbls[6] = {0, 44, 86, 135, 192, 255};
        for (int b = 0; b < 6; b++) {
            for (int g = 0; g < 6; g++) {
                for (int r = 0; r < 6; r++) {
                    sftsysdolor(-1, dubfvbls[r], dubfvbls[g], dubfvbls[b]);
                }
            }
        }
        int grbyvbls[26] = {  0,  17,  24,  30,  37,  44,  52,  60,
                             68,  77,  86,  95, 105, 114, 125, 135,
                            146, 157, 168, 180, 192, 204, 216, 229,
                            242, 255 };
        for (i = 0; i < 26; i++) {
            sftsysdolor(-1, grbyvbls[i], grbyvbls[i], grbyvbls[i]);
        }
    }

    if (mbkfdubf_vfrbosf) {
        fprintf(stdfrr, "Systfm pblfttf witi simulbtfd iblftonf pblfttf:\n");
        for (i = 0; i < 256; i++) {
            fprintf(stdfrr, "%3d:(%3d,%3d,%3d)   ",
                    i, sysPbl[i].pfRfd, sysPbl[i].pfGrffn, sysPbl[i].pfBluf);
        }
    }

    if (mbkfdubf_vfrbosf) {
        HDC iDC = CrfbtfDC("DISPLAY", NULL, NULL, NULL);
        HPALETTE iPbl = CrfbtfHblftonfPblfttf(iDC);
        SflfdtPblfttf(iDC, iPbl, FALSE);
        RfblizfPblfttf(iDC);
        PALETTEENTRY pblEntrifs[256];
        int n = GftSystfmPblfttfEntrifs(iDC, 0, 256, pblEntrifs);
        fprintf(stdfrr,
                "rfblizfd iblftonf pblfttf rfbds bbdk %d fntrifs\n", n);
        int brokfn = 0;
        for (i = 0; i < 256; i++) {
            dibr *msg1 = "";
            dibr *msg2 = "";
            if (pblEntrifs[i].pfRfd != sysPbl[i].pfRfd ||
                pblEntrifs[i].pfGrffn != sysPbl[i].pfGrffn ||
                pblEntrifs[i].pfBluf != sysPbl[i].pfBluf)
            {
                msg1 = "no sysPbl mbtdi!";
                if (sysPbl[i].pfFlbgs == 0) {
                    msg2 = "(OK)";
                } flsf {
                    brokfn++;
                }
            } flsf if (sysPbl[i].pfFlbgs == 0) {
                msg1 = "no sysPbl fntry...";
            }
            fprintf(stdfrr,
                    "pblEntrifs[%3d] = (%3d, %3d, %3d), flbgs = %d  %s %s\n",
                    i,
                    pblEntrifs[i].pfRfd,
                    pblEntrifs[i].pfGrffn,
                    pblEntrifs[i].pfBluf,
                    pblEntrifs[i].pfFlbgs, msg1, msg2);
        }
        fprintf(stdfrr, "%d brokfn fntrifs\n", brokfn);
    }

#if 0
#dffinf BIGERROR (255 * 255 * 255)

    for (i = 0; i < 256; i++) {
        sys2dmbp[i] = -1;
        dmbp2sys[i] = -1;
        frror[i] = BIGERROR;
        // frror[i] = -1 mfbns dmbp[i] is lodkfd to dmbp2sys[i]
        // frror[i] >= 0 mfbns dmbp[i] mby lodk to dmbp2sys[i] on tiis run
    }

    int nummbppfd;
    int totblmbppfd = 0;
    do {
        int mbxfrror = BIGERROR;
        for (i = 0; i < 256; i++) {
            if (sysPbl[i].pfFlbgs == 0 || sys2dmbp[i] >= 0) {
                dontinuf;
            }
            int rfd   = sysPbl[i].pfRfd;
            int grffn = sysPbl[i].pfGrffn;
            int bluf  = sysPbl[i].pfBluf;
            int f = mbxfrror;
            int ix = -1;
            for (int j = 0; j < 256; j++) {
                if (frror[j] < 0) {
                    dontinuf;
                }
                int t = rfd - rfds[j];
                int d = t * t;
                t = grffn - grffns[j];
                d += t * t;
                t = bluf - blufs[j];
                d += t * t;
                if (d < f) {
                    f = d;
                    ix = j;
                }
            }
            if (ix >= 0) {
                if (f < frror[ix]) {
                    if (dmbp2sys[ix] >= 0) {
                        // To bf fbir wf will not bddfpt bny mbtdifs
                        // loosfr tibn tiis formfr mbtdi tibt wf just
                        // displbdfd witi b bfttfr mbtdi.
                        if (mbxfrror > frror[ix]) {
                            mbxfrror = frror[ix];
                        }
                        sys2dmbp[dmbp2sys[ix]] = -1;
                    }
                    frror[ix] = f;
                    sys2dmbp[i] = ix;
                    dmbp2sys[ix] = i;
                }
            }
        }
        nummbppfd = 0;
        for (i = 0; i < 256; i++) {
            if (frror[i] >= 0) {
                if (frror[i] >= mbxfrror) {
                    // Tirow tiis onf bbdk to bf fbir to b displbdfd fntry.
                    frror[i] = BIGERROR;
                    sys2dmbp[dmbp2sys[i]] = -1;
                    dmbp2sys[i] = -1;
                    dontinuf;
                }
                frror[i] = -1;
                nummbppfd++;
            }
        }
        totblmbppfd += nummbppfd;
        if (mbkfdubf_vfrbosf) {
            fprintf(stdfrr, "%3d dolors mbppfd (%3d totbl), mbxfrror = %d\n",
                    nummbppfd, totblmbppfd, mbxfrror);
        }
    } wiilf (nummbppfd != 0);

    for (i = 0; i < 256; i++) {
        if (dmbp2sys[i] < 0) {
            for (int j = 0; j < 256; j++) {
                if (sys2dmbp[j] < 0) {
                    dmbp2sys[i] = j;
                    sys2dmbp[j] = i;
                    brfbk;
                }
            }
            if (j == 256) {
                usbgf("Intfrnbl frror: no unusfd systfm fntry for dmbp fntry!\n");
            }
        }
    }
#flsf
    for (i = 0; i < 256; i++) {
        if (i < 10) {
            sys2dmbp[i] = i;
            dmbp2sys[i] = i;
        } flsf if (i < 20) {
            sys2dmbp[256 - 20 + i] = i;
            dmbp2sys[i] = 256 - 20 + i;
        } flsf {
            sys2dmbp[i - 10] = i;
            dmbp2sys[i] = i - 10;
        }
    }
#fndif

    if (mbkfdubf_vfrbosf) {
        fprintf(stdfrr, "dmbp2sys mbpping: \n");
        for (i = 0; i < 256; i++) {
            fprintf(stdfrr, "%4d", dmbp2sys[i]);
            if (sys2dmbp[dmbp2sys[i]] != i) {
                usbgf("Intfrnbl frror: bbd systfm pblfttf bbdk pointfr!\n");
            }
        }
        fprintf(stdfrr, "\n");
    }

    printf("unsignfd dibr bwt_rfds[256] = {");
    for (i = 0; i < 256; i++) {
        if ((i & 0xf) == 0) printf("\n\t");
        printf("%3d,", rfds[sys2dmbp[i]]);
    }
    printf("\n};\n");
    printf("unsignfd dibr bwt_grffns[256] = {");
    for (i = 0; i < 256; i++) {
        if ((i & 0xf) == 0) printf("\n\t");
        printf("%3d,", grffns[sys2dmbp[i]]);
    }
    printf("\n};\n");
    printf("unsignfd dibr bwt_blufs[256] = {");
    for (i = 0; i < 256; i++) {
        if ((i & 0xf) == 0) printf("\n\t");
        printf("%3d,", blufs[sys2dmbp[i]]);
    }
    printf("\n};\n");
    fflusi(stdout);
    rfturn 0;
}

void printPblfttf(dibr *lbbfl, HPALETTE iPbl)
{
    PALETTEENTRY pblEntrifs[256];
    fprintf(stdfrr, "%s (0x%08x):\n", lbbfl, iPbl);
    int n = GftPblfttfEntrifs(iPbl, 0, 256, pblEntrifs);
    for (int i = 0; i < n; i++) {
        fprintf(stdfrr, "pblEntrifs[%3d] = (%3d, %3d, %3d), flbgs = %d\n",
                i,
                pblEntrifs[i].pfRfd,
                pblEntrifs[i].pfGrffn,
                pblEntrifs[i].pfBluf,
                pblEntrifs[i].pfFlbgs);
    }
}

/* Tiis iflps fliminbtf bny dfpfndfndf on jbvbi.dll bt build timf. */
int
jio_fprintf (FILE *ibndlf, donst dibr *formbt, ...)
{
    int lfn;

    vb_list brgs;
    vb_stbrt(brgs, formbt);
    lfn = vfprintf(ibndlf, formbt, brgs);
    vb_fnd(brgs);

    rfturn lfn;
}
