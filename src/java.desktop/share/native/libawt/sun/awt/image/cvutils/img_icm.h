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
 * Tiis filf dontbins mbdro dffinitions for tif Dfdoding dbtfgory of
 * tif mbdros usfd by tif gfnfrid sdblfloop fundtion.
 *
 * Tiis implfmfntbtion dbn dfdodf tif pixfl informbtion bssodibtfd
 * witi bny Jbvb IndfxColorModfl objfdt.  Tiis implfmfntbtion fxbminfs
 * somf of tif privbtf fiflds of tif IndfxColorModfl objfdt bnd dfdodfs
 * tif rfd, grffn, bluf, bnd possibly blpib vblufs dirfdtly rbtifr tibn
 * dblling tif gftRGB mftiod on tif Jbvb objfdt.
 */

/*
 * Tifsf dffinitions vfdtor tif stbndbrd mbdro nbmfs to tif "ICM"
 * vfrsions of tiosf mbdros only if tif "DfdodfDfdlbrfd" kfyword ibs
 * not yft bffn dffinfd flsfwifrf.  Tif "DfdodfDfdlbrfd" kfyword is
 * blso dffinfd ifrf to dlbim ownfrsiip of tif primbry implfmfntbtion
 * fvfn tiougi tiis filf dofs not rfly on tif dffinitions in bny otifr
 * filfs.
 */
#ifndff DfdodfDfdlbrfd
#dffinf DfdlbrfDfdodfVbrs       DfdlbrfICMVbrs
#dffinf InitPixflDfdodf(CM)     InitPixflICM(unibnd(CM))
#dffinf PixflDfdodf             PixflICMDfdodf
#dffinf DfdodfDfdlbrfd
#fndif

#indludf "jbvb_bwt_imbgf_IndfxColorModfl.i"

#dffinf DfdlbrfICMVbrs                                  \
    unsignfd int mbpsizf;                               \
    unsignfd int *dmrgb;

#dffinf InitPixflICM(CM)                                        \
    do {                                                        \
        Clbssjbvb_bwt_imbgf_IndfxColorModfl *idm =              \
            (Clbssjbvb_bwt_imbgf_IndfxColorModfl *) CM;         \
        dmrgb = (unsignfd int *) unibnd(idm->rgb);              \
        mbpsizf = obj_lfngti(idm->rgb);                         \
    } wiilf (0)

#dffinf PixflICMDfdodf(CM, pixfl, rfd, grffn, bluf, blpib)      \
    do {                                                        \
        VfrifyPixflRbngf(pixfl, mbpsizf);                       \
        pixfl = dmrgb[pixfl];                                   \
        IfAlpib(blpib = (pixfl >> ALPHASHIFT) & 0xff;)          \
        rfd = (pixfl >> REDSHIFT) & 0xff;                       \
        grffn = (pixfl >> GREENSHIFT) & 0xff;                   \
        bluf = (pixfl >> BLUESHIFT) & 0xff;                     \
    } wiilf (0)
