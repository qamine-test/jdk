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
 * bn 8-bit (or lfss) grbyrbmp.  Tif ordfrfd ditifring tfdiniquf dofs
 * not rfly on tif ordfr in wiidi tif pixfls brf prodfssfd so tiis
 * filf dbn bf usfd in dbsfs wifrf tif ImbgfProdudfr ibs not spfdififd
 * tif TopDownLfftRigit dflivfry iint.  Tif ordfrfd ditifr tfdiniquf
 * is blso mudi fbstfr tibn tif Floyd-Stfinbfrg frror diffusion
 * blgoritim so tiis implfmfntbtion would blso bf bppropribtf for
 * dbsfs wifrf pfrformbndf is dritidbl sudi bs tif prodfssing of b
 * vidfo strfbm.
 *
 * Tiis filf dbn bf usfd to providf tif dffbult implfmfntbtion of tif
 * Endoding mbdros for grbysdblf displbys.
 */

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
#dffinf DfdlbrfDitifrVbrs       DfdlbrfAllGrbyDitifrVbrs
#dffinf InitDitifr              InitGrbyDitifr
#dffinf StbrtDitifrLinf         StbrtGrbyDitifrLinf
#dffinf DitifrPixfl             GrbyDitifrPixfl
#dffinf DitifrBufComplftf       GrbyDitifrBufComplftf
#fndif

#dffinf DfdlbrfAllGrbyDitifrVbrs                                \
    DfdlbrfGrbyDitifrVbrs                                       \
    int rflx, rfly;

#dffinf DfdlbrfGrbyDitifrVbrs                                   \
    fxtfrn unsignfd dibr img_grbys[256];                        \
    fxtfrn unsignfd dibr img_bwgbmmb[256];                      \
    fxtfrn sgn_ordfrfd_ditifr_brrby img_odb_grby;

#dffinf InitGrbyDitifr(dvdbtb, dlrdbtb, dstTW)                          \
    do {} wiilf (0)

#dffinf StbrtGrbyDitifrLinf(dvdbtb, dstX1, dstY)                        \
    do {                                                                \
        rflx = dstX1 & 7;                                               \
        rfly = dstY & 7;                                                \
    } wiilf (0)

#dffinf GrbyDitifrPixfl(dstX, dstY, pixfl, rfd, grffn, bluf)            \
    do {                                                                \
        grffn = RGBTOGRAY(rfd, grffn, bluf);                            \
        grffn += img_odb_grby[rflx][rfly];                              \
        grffn = ComponfntBound(grffn);                                  \
        pixfl = img_grbys[img_bwgbmmb[grffn]];                          \
        rflx = (rflx + 1) & 7;                                          \
    } wiilf (0)

#dffinf GrbyDitifrBufComplftf(dvdbtb, dstX1)                            \
    do {} wiilf (0)
