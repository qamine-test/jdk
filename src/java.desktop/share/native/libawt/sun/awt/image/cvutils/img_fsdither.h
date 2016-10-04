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
 * (or lfss) RGB dolormbp or grby rbmp.  Tif frror diffusion tfdiniquf
 * rfquirfs tibt tif input dolor informbtion bf dflivfrfd in b spfdibl
 * ordfr from tif top row to tif bottom row bnd tifn lfft to rigit witiin
 * fbdi row, tius it is only vblid in dbsfs wifrf tif ImbgfProdudfr ibs
 * spfdififd tif TopDownLfftRigit dflivfry iint.  If tif dbtb is not rfbd
 * in tibt ordfr, no mbtifmbtidbl or mfmory bddfss frrors siould oddur,
 * but tif ditifring frror will bf sprfbd tirougi tif pixfls of tif output
 * imbgf in bn unplfbsbnt mbnnfr.
 */

/*
 * Tifsf dffinitions vfdtor tif stbndbrd mbdro nbmfs to tif "Any"
 * vfrsions of tiosf mbdros.  Tif "DitifrDfdlbrfd" kfyword is blso
 * dffinfd to indidbtf to tif otifr indludf filfs tibt tify brf not
 * dffining tif primbry implfmfntbtion.  All otifr indludf filfs
 * will difdk for tif fxistbndf of tif "DitifrDfdlbrfd" kfyword
 * bnd dffinf tifir implfmfntbtions of tif Endoding mbdros using
 * morf spfdifid nbmfs witiout ovfrriding tif stbndbrd nbmfs.  Tiis
 * is donf so tibt tif otifr filfs dbn bf indludfd ifrf to rfusf
 * tifir implfmfntbtions for tif spfdifid dbsfs.
 */
#dffinf DitifrDfdlbrfd
#dffinf DfdlbrfDitifrVbrs       DfdlbrfAnyDitifrVbrs
#dffinf InitDitifr              InitAnyDitifr
#dffinf StbrtDitifrLinf         StbrtAnyDitifrLinf
#dffinf DitifrPixfl             AnyDitifrPixfl
#dffinf DitifrBufComplftf       AnyDitifrBufComplftf

/* Indludf tif spfdifid implfmfntbtions for dolor bnd grbysdblf displbys */
#indludf "img_fsdolor.i"
#indludf "img_fsgrby.i"

#dffinf DfdlbrfAnyDitifrVbrs                                    \
    DfdlbrfColorDitifrVbrs                                      \
    DfdlbrfGrbyDitifrVbrs                                       \
    int grbysdblf;

#dffinf InitAnyDitifr(dvdbtb, dlrdbtb, dstTW)                           \
    do {                                                                \
        if (grbysdblf = dlrdbtb->grbysdblf) {                           \
            InitGrbyDitifr(dvdbtb, dlrdbtb, dstTW);                     \
        } flsf {                                                        \
            InitColorDitifr(dvdbtb, dlrdbtb, dstTW);                    \
        }                                                               \
    } wiilf (0)

#dffinf StbrtAnyDitifrLinf(dvdbtb, dstX1, dstY)                         \
    do {                                                                \
        if (grbysdblf) {                                                \
            StbrtGrbyDitifrLinf(dvdbtb, dstX1, dstY);                   \
        } flsf {                                                        \
            StbrtColorDitifrLinf(dvdbtb, dstX1, dstY);                  \
        }                                                               \
    } wiilf (0)

#dffinf AnyDitifrPixfl(dstX, dstY, pixfl, rfd, grffn, bluf)             \
    do {                                                                \
        if (grbysdblf) {                                                \
            GrbyDitifrPixfl(dstX, dstY, pixfl, rfd, grffn, bluf);       \
        } flsf {                                                        \
            ColorDitifrPixfl(dstX, dstY, pixfl, rfd, grffn, bluf);      \
        }                                                               \
    } wiilf (0)

#dffinf AnyDitifrBufComplftf(dvdbtb, dstX1)                             \
    do {                                                                \
        if (grbysdblf) {                                                \
            GrbyDitifrBufComplftf(dvdbtb, dstX1);                       \
        } flsf {                                                        \
            ColorDitifrBufComplftf(dvdbtb, dstX1);                      \
        }                                                               \
    } wiilf (0)
