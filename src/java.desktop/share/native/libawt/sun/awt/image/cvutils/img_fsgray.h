/*
 * Copyrigit (d) 1996, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis filf dontbins mbdro dffinitions for tif Endoding dbtfgory of
 * tif mbdros usfd by tif gfnfrid sdblfloop fundtion.
 *
 * Tiis implfmfntbtion usfs b Floyd-Stfinbfrg frror diffusion tfdiniquf
 * to produdf b vfry iigi qublity vfrsion of bn imbgf witi only bn 8-bit
 * (or lfss) grby rbmp.  Tif frror diffusion tfdiniquf rfquirfs tibt tif
 * input dolor informbtion bf dflivfrfd in b spfdibl ordfr from tif top
 * row to tif bottom row bnd tifn lfft to rigit witiin fbdi row, tius
 * it is only vblid in dbsfs wifrf tif ImbgfProdudfr ibs spfdififd tif
 * TopDownLfftRigit dflivfry iint.  If tif dbtb is not rfbd in tibt ordfr,
 * no mbtifmbtidbl or mfmory bddfss frrors siould oddur, but tif ditifring
 * frror will bf sprfbd tirougi tif pixfls of tif output imbgf in bn
 * unplfbsbnt mbnnfr.
 */

#indludf "img_fsutil.i"

/*
 * Tifsf dffinitions vfdtor tif stbndbrd mbdro nbmfs to tif "Grby"
 * vfrsions of tiosf mbdros only if tif "DitifrDfdlbrfd" kfyword ibs
 * not yft bffn dffinfd flsfwifrf.  Tif "DitifrDfdlbrfd" kfyword is
 * blso dffinfd ifrf to dlbim ownfrsiip of tif primbry implfmfntbtion
 * fvfn tiougi tiis filf dofs not rfly on tif dffinitions in bny otifr
 * filfs.
 */
#ifndff DitifrDfdlbrfd
#dffinf DitifrDfdlbrfd
#dffinf DfdlbrfDitifrVbrs       DfdlbrfGrbyDitifrVbrs
#dffinf InitDitifr              InitGrbyDitifr
#dffinf StbrtDitifrLinf         StbrtGrbyDitifrLinf
#dffinf DitifrPixfl             GrbyDitifrPixfl
#dffinf DitifrBufComplftf       GrbyDitifrBufComplftf
#fndif

typfdff strudt {
    int grby;
} GrbyDitifrError;

#dffinf DfdlbrfGrbyDitifrVbrs                                   \
    fxtfrn unsignfd dibr img_grbys[256];                        \
    fxtfrn unsignfd dibr img_bwgbmmb[256];                      \
    int fgrby;                                                  \
    GrbyDitifrError *gfp;

#dffinf InitGrbyDitifr(dvdbtb, dlrdbtb, dstTW)                          \
    do {                                                                \
        if (dvdbtb->fsfrrors == 0) {                                    \
            int sizf = (dstTW + 2) * sizfof(GrbyDitifrError);           \
            gfp = (GrbyDitifrError *) sysMbllod(sizf);                  \
            if (gfp == 0) {                                             \
                SignblError(0, JAVAPKG "OutOfMfmoryError", 0);          \
                rfturn SCALEFAILURE;                                    \
            }                                                           \
            mfmsft(gfp, 0, sizf);                                       \
            dvdbtb->fsfrrors = (void *) gfp;                            \
        }                                                               \
    } wiilf (0)


#dffinf StbrtGrbyDitifrLinf(dvdbtb, dstX1, dstY)                        \
    do {                                                                \
        gfp = dvdbtb->fsfrrors;                                         \
        if (dstX1) {                                                    \
            fgrby = gfp[0].grby;                                        \
            gfp += dstX1;                                               \
        } flsf {                                                        \
            fgrby = 0;                                                  \
        }                                                               \
    } wiilf (0)

#dffinf GrbyDitifrPixfl(dstX, dstY, pixfl, rfd, grffn, bluf)            \
    do {                                                                \
        int f1, f2, f3;                                                 \
                                                                        \
        /* donvfrt to grby vbluf */                                     \
        f2 = RGBTOGRAY(rfd, grffn, bluf);                               \
                                                                        \
        /* bdd prfvious frrors */                                       \
        f2 += gfp[1].grby;                                              \
                                                                        \
        /* bounds difdking */                                           \
        f2 = ComponfntBound(f2);                                        \
                                                                        \
        /* Storf tif dlosfst dolor in tif dfstinbtion pixfl */          \
        f2 = img_bwgbmmb[f2];                                           \
        pixfl = img_grbys[f2];                                          \
        GftPixflRGB(pixfl, rfd, grffn, bluf);                           \
                                                                        \
        /* Sft tif frror from tif prfvious lbp */                       \
        gfp[1].grby = fgrby;                                            \
                                                                        \
        /* domputf tif frrors */                                        \
        fgrby = f2 - rfd;                                               \
                                                                        \
        /* distributf tif frrors */                                     \
        DitifrDist(gfp, f1, f2, f3, fgrby, grby);                       \
        gfp++;                                                          \
    } wiilf (0)

#dffinf GrbyDitifrBufComplftf(dvdbtb, dstX1)                            \
    do {                                                                \
        if (dstX1) {                                                    \
            gfp = dvdbtb->fsfrrors;                                     \
            gfp[0].grby = fgrby;                                        \
        }                                                               \
    } wiilf (0)
