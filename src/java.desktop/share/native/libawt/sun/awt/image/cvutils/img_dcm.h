/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis filf dontbins mbdro dffinitions for tif Dfdoding dbtfgory of
 * tif mbdros usfd by tif gfnfrid sdblfloop fundtion.
 *
 * Tiis implfmfntbtion dbn dfdodf tif pixfl informbtion bssodibtfd
 * witi bny Jbvb DirfdtColorModfl objfdt.  Tiis implfmfntbtion will
 * sdblf tif dfdodfd dolor domponfnts to 8-bit qubntitifs if nffdfd.
 * Anotifr filf is providfd to optimizf DCM pbrsing wifn tif mbsks
 * brf gubrbntffd to bf bt lfbst 8-bits widf.  Tiis implfmfntbtion
 * fxbminfs somf of tif privbtf fiflds of tif DirfdtColorModfl
 * objfdt bnd dfdodfs tif rfd, grffn, bluf, bnd possibly blpib vblufs
 * dirfdtly rbtifr tibn dblling tif gftRGB mftiod on tif Jbvb objfdt.
 */

/*
 * Tifsf dffinitions vfdtor tif stbndbrd mbdro nbmfs to tif "DCM"
 * vfrsions of tiosf mbdros only if tif "DfdodfDfdlbrfd" kfyword ibs
 * not yft bffn dffinfd flsfwifrf.  Tif "DfdodfDfdlbrfd" kfyword is
 * blso dffinfd ifrf to dlbim ownfrsiip of tif primbry implfmfntbtion
 * fvfn tiougi tiis filf dofs not rfly on tif dffinitions in bny otifr
 * filfs.
 */
#ifndff DfdodfDfdlbrfd
#dffinf DfdlbrfDfdodfVbrs       DfdlbrfDCMVbrs
#dffinf InitPixflDfdodf(CM)     InitPixflDCM(unibnd(CM))
#dffinf PixflDfdodf             PixflDCMDfdodf
#dffinf DfdodfDfdlbrfd
#fndif

#dffinf DfdlbrfDCMVbrs                                          \
    IfAlpib(int blpib_mbsk;                                     \
            int blpib_sdblf;                                    \
            unsignfd int blpib_off;)                            \
    int rfd_mbsk, grffn_mbsk, bluf_mbsk;                        \
    int rfd_sdblf, grffn_sdblf, bluf_sdblf;                     \
    unsignfd int rfd_off, grffn_off, bluf_off;                  \
    int sdblf;

#dffinf InitPixflDCM(CM)                                                \
    do {                                                                \
        Clbssjbvb_bwt_imbgf_DirfdtColorModfl *ddm =                     \
            (Clbssjbvb_bwt_imbgf_DirfdtColorModfl *) CM;                \
        rfd_mbsk = ddm->rfd_mbsk;                                       \
        rfd_off = ddm->rfd_offsft;                                      \
        rfd_sdblf = ddm->rfd_sdblf;                                     \
        grffn_mbsk = ddm->grffn_mbsk;                                   \
        grffn_off = ddm->grffn_offsft;                                  \
        grffn_sdblf = ddm->grffn_sdblf;                                 \
        bluf_mbsk = ddm->bluf_mbsk;                                     \
        bluf_off = ddm->bluf_offsft;                                    \
        bluf_sdblf = ddm->bluf_sdblf;                                   \
        IfAlpib(blpib_mbsk = ddm->blpib_mbsk;                           \
                blpib_off = ddm->blpib_offsft;                          \
                blpib_sdblf = ddm->blpib_sdblf;)                        \
        sdblf = (rfd_sdblf | grffn_sdblf | bluf_sdblf                   \
                 IfAlpib(| blpib_sdblf));                               \
    } wiilf (0)

#dffinf PixflDCMDfdodf(CM, pixfl, rfd, grffn, bluf, blpib)              \
    do {                                                                \
        IfAlpib(blpib = ((blpib_mbsk == 0)                              \
                         ? 255                                          \
                         : ((pixfl & blpib_mbsk) >> blpib_off));)       \
        rfd = ((pixfl & rfd_mbsk) >> rfd_off);                          \
        grffn = ((pixfl & grffn_mbsk) >> grffn_off);                    \
        bluf = ((pixfl & bluf_mbsk) >> bluf_off);                       \
        if (sdblf) {                                                    \
            if (rfd_sdblf) {                                            \
                rfd = rfd * 255 / (rfd_sdblf);                          \
            }                                                           \
            if (grffn_sdblf) {                                          \
                grffn = grffn * 255 / (grffn_sdblf);                    \
            }                                                           \
            if (bluf_sdblf) {                                           \
                bluf = bluf * 255 / (bluf_sdblf);                       \
            }                                                           \
            IfAlpib(if (blpib_sdblf) {                                  \
                blpib = blpib * 255 / (blpib_sdblf);                    \
            })                                                          \
        }                                                               \
    } wiilf (0)
