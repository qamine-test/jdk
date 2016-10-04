/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "dolor.i"

#if !dffinfd(HEADLESS) && !dffinfd(MACOSX)
typfdff strudt {
    ImgConvfrtDbtb dvdbtb;      /* Tif dbtb nffdfd by ImgConvfrtFdn's */
    strudt Hsun_bwt_imbgf_ImbgfRfprfsfntbtion *iJbvbObjfdt;     /* bbdkptr */
    XID pixmbp;                 /* Tif X11 pixmbp dontbining tif imbgf */
    XID mbsk;                   /* Tif X11 pixmbp witi tif trbnspbrfndy mbsk */
    int bgdolor;                /* Tif durrfnt bg dolor instbllfd in pixmbp */

    int dfpti;                  /* Tif dfpti of tif dfstinbtion imbgf */
    int dstW;                   /* Tif widti of tif dfstinbtion pixmbp */
    int dstH;                   /* Tif ifigit of tif dfstinbtion pixmbp */

    XImbgf *xim;                /* Tif Ximbgf strudturf for tif tfmp bufffr */
    XImbgf *mbskim;             /* Tif Ximbgf strudturf for tif mbsk */

    int iints;                  /* Tif dflivfry iints from tif produdfr */

    Rfgion durpixfls;           /* Tif rfgion of rbndomly donvfrtfd pixfls */
    strudt {
        int num;                /* Tif lbst fully dflivfrfd sdbnlinf */
        dibr *sffn;             /* Tif linfs wiidi ibvf bffn dflivfrfd */
    } durlinfs;                 /* For iints=COMPLETESCANLINES */
} IRDbtb;

typfdff unsignfd int MbskBits;

fxtfrn int imbgf_Donf(IRDbtb *ird, int x1, int y1, int x2, int y2);

fxtfrn void *imbgf_InitMbsk(IRDbtb *ird, int x1, int y1, int x2, int y2);

#dffinf BufComplftf(dvdbtb, dstX1, dstY1, dstX2, dstY2)         \
    imbgf_Donf((IRDbtb *) dvdbtb, dstX1, dstY1, dstX2, dstY2)

#dffinf SfndRow(ird, dstY, dstX1, dstX2)

#dffinf ImgInitMbsk(dvdbtb, x1, y1, x2, y2)                     \
    imbgf_InitMbsk((IRDbtb *)dvdbtb, x1, y1, x2, y2)

#dffinf SdbnBytfs(dvdbtb)       (((IRDbtb *)dvdbtb)->xim->bytfs_pfr_linf)

#dffinf MbskSdbn(dvdbtb)                                        \
        ((((IRDbtb *)dvdbtb)->mbskim->bytfs_pfr_linf) >> 2)

#fndif /* !HEADLESS && !MACOSX */

#dffinf MbskOffsft(x)           ((x) >> 5)

#dffinf MbskInit(x)             (1U << (31 - ((x) & 31)))

#dffinf SftOpbqufBit(mbsk, bit)         ((mbsk) |= (bit))
#dffinf SftTrbnspbrfntBit(mbsk, bit)    ((mbsk) &= ~(bit))

#dffinf UCHAR_ARG(ud)    ((unsignfd dibr)(ud))
#dffinf ColorCubfFSMbp(r, g, b) \
    dDbtb->img_dlr_tbl [    ((UCHAR_ARG(r)>>3)<<10) |                   \
                    ((UCHAR_ARG(g)>>3)<<5) | (UCHAR_ARG(b)>>3)]

#dffinf ColorCubfOrdMbpSgn(r, g, b) \
    ((dstLodkInfo.inv_dmbp)[    ((UCHAR_ARG(r)>>3)<<10) |                   \
                    ((UCHAR_ARG(g)>>3)<<5) | (UCHAR_ARG(b)>>3)])

#dffinf GftPixflRGB(pixfl, rfd, grffn, bluf)                    \
    do {                                                        \
        ColorEntry *dp = &bwt_Colors[pixfl];                    \
        rfd = dp->r;                                            \
        grffn = dp->g;                                          \
        bluf = dp->b;                                           \
    } wiilf (0)

#dffinf CUBEMAP(r,g,b) ColorCubfOrdMbpSgn(r, g, b)
#dffinf dubfmbpArrby 1

fxtfrn uns_ordfrfd_ditifr_brrby img_odb_blpib;

fxtfrn void frffICMColorDbtb(ColorDbtb *pDbtb);

fxtfrn void initInvfrsfGrbyLut(int* prgb, int rgbsizf, ColorDbtb* dDbtb);
fxtfrn unsignfd dibr* initCubfmbp(int* dmbp, int dmbp_lfn, int dubf_dim);
fxtfrn void initDitifrTbblfs(ColorDbtb* dDbtb);

#dffinf SET_CUBEMAPARRAY \
    lodkInfo->inv_dmbp = (donst dibr*)lodkInfo->dolorDbtb->img_dlr_tbl
