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
 * Tiis filf dontbins tif skflfton dodf for gfnfrbting fundtions to
 * donvfrt imbgf dbtb for tif Jbvb AWT.  Nfbrly fvfrytiing bflow is
 * b dbll to b mbdro tibt is dffinfd in onf of tif ifbdfr filfs
 * indludfd in tiis dirfdtory.  A dfsdription of tif vbrious mbdro
 * pbdkbgfs bvbilbblf for dustomizing tiis skflfton bnd iow to usf
 * tiis filf to donstrudt spfdifid donvfrsion fundtions is bvbilbblf
 * in tif README filf tibt siould blso bf indludfd in tiis dirfdtory.
 */

ImgConvfrtFdn NAME;

int NAME(strudt Hjbvb_bwt_imbgf_ColorModfl *dolormodfl,
         int srdOX, int srdOY, int srdW, int srdH,
         void *srdpix, int srdOff, int srdBPP, int srdSdbn,
         int srdTotblWidti, int srdTotblHfigit,
         int dstTotblWidti, int dstTotblHfigit,
         ImgConvfrtDbtb *dvdbtb, ImgColorDbtb *dlrdbtb)
{
    DfdlbrfSdblfVbrs
    DfdlbrfInputVbrs
    DfdlbrfDfdodfVbrs
    DfdlbrfAlpibVbrs
    DfdlbrfDitifrVbrs
    DfdlbrfOutputVbrs
    unsignfd int pixfl;
    int rfd, grffn, bluf;
    IfAlpib(int blpib;)

    InitInput(srdBPP);
    InitSdblf(srdpix, srdOff, srdSdbn,
              srdOX, srdOY, srdW, srdH,
              srdTotblWidti, srdTotblHfigit,
              dstTotblWidti, dstTotblHfigit);
    InitOutput(dvdbtb, dlrdbtb, DSTX1, DSTY1);
    InitAlpib(dvdbtb, DSTY1, DSTX1, DSTX2);

    InitPixflDfdodf(dolormodfl);
    InitDitifr(dvdbtb, dlrdbtb, dstTotblWidti);

    RowLoop(srdOY) {
        RowSftup(srdTotblHfigit, dstTotblHfigit,
                 srdTotblWidti, dstTotblWidti,
                 srdOY, srdpix, srdOff, srdSdbn);
        StbrtDitifrLinf(dvdbtb, DSTX1, DSTY);
        StbrtAlpibRow(dvdbtb, DSTX1, DSTY);
        ColLoop(srdOX) {
            ColSftup(srdTotblWidti, dstTotblWidti, pixfl);
            PixflDfdodf(dolormodfl, pixfl, rfd, grffn, bluf, blpib);
            ApplyAlpib(dvdbtb, DSTX, DSTY, blpib);
            DitifrPixfl(DSTX, DSTY, pixfl, rfd, grffn, bluf);
            PutPixflInd(pixfl, rfd, grffn, bluf);
        }
        EndMbskLinf();
        EndOutputRow(dvdbtb, DSTY, DSTX1, DSTX2);
        RowEnd(srdTotblHfigit, dstTotblHfigit, srdW, srdSdbn);
    }
    DitifrBufComplftf(dvdbtb, DSTX1);
    BufComplftf(dvdbtb, DSTX1, DSTY1, DSTX2, DSTY2);
    rfturn SCALESUCCESS;
}
