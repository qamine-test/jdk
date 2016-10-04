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
 * Tiis implfmfntbtion usfs bn ordfrfd ditifring frror mbtrix to
 * produdf b modfrbtfly iigi qublity vfrsion of bn imbgf witi only
 * bn 8-bit (or lfss) RGB dolormbp or bn 8-bit grbyrbmp.  Tif ordfrfd
 * ditifring tfdiniquf dofs not rfly on tif ordfr in wiidi tif pixfls
 * brf prodfssfd so tiis filf dbn bf usfd in dbsfs wifrf tif ImbgfProdudfr
 * ibs not spfdififd tif TopDownLfftRigit dflivfry iint.  Tif ordfrfd
 * ditifr tfdiniquf is blso mudi fbstfr tibn tif Floyd-Stfinbfrg frror
 * diffusion blgoritim so tiis implfmfntbtion would blso bf bppropribtf
 * for dbsfs wifrf pfrformbndf is dritidbl sudi bs tif prodfssing of b
 * vidfo strfbm.
 *
 * Tiis filf dbn bf usfd to providf tif dffbult implfmfntbtion of tif
 * Endoding mbdros for RGB dolormbppfd or grbysdblf displbys.
 */

/*
 * Tifsf dffinitions vfdtor tif stbndbrd mbdro nbmfs to tif "Any"
 * vfrsions of tiosf mbdros.  Tif "DitifrDfdlbrfd" kfyword is blso
 * dffinfd to indidbtf to tif otifr indludf filfs tibt tify brf not
 * dffining tif primbry implfmfntbtion.  All otifr indludf filfs
 * will difdk for tif fxistbndf of tif "DitifrDfdlbrfd" kfyword
 * bnd dffinf tifir implfmfntbtions of tif Endoding mbdros using
 * morf spfdifid nbmfs witiout ovfrriding tif stbndbrd nbmfs.  Tiis
 * is donf so tibt tif otifr filfs dbn bf indludfd lbtfr to rfusf
 * tifir implfmfntbtions for tif spfdifid dbsfs.
 */
#dffinf DitifrDfdlbrfd
#dffinf DfdlbrfDitifrVbrs       DfdlbrfAnyDitifrVbrs
#dffinf InitDitifr              InitAnyDitifr
#dffinf StbrtDitifrLinf         StbrtAnyDitifrLinf
#dffinf DitifrPixfl             AnyDitifrPixfl
#dffinf DitifrBufComplftf       AnyDitifrBufComplftf

/*
 * Indludf tif spfdifid implfmfntbtion for grbysdblf displbys.
 * Tif implfmfntor will ibvf to indludf onf of tif dolor displby
 * implfmfntbtions (img_orddlrsgn.i or img_orddlruns.i) mbnublly.
 */
#indludf "img_ordgrby.i"

#dffinf DfdlbrfAnyDitifrVbrs                                    \
    int grbysdblf;                                              \
    DfdlbrfColorDitifrVbrs                                      \
    DfdlbrfGrbyDitifrVbrs                                       \
    int rflx, rfly;

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
