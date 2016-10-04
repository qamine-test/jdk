/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff GrbpiidsPrimitivfMgr_i_Indludfd
#dffinf GrbpiidsPrimitivfMgr_i_Indludfd

#ifdff __dplusplus
fxtfrn "C" {
#fndif

#indludf <stddff.i>

#indludf "jbvb_bwt_AlpibCompositf.i"

#indludf "SurfbdfDbtb.i"
#indludf "SpbnItfrbtor.i"

#indludf "j2d_md.i"

#indludf "AlpibMbti.i"
#indludf "GlypiImbgfRff.i"

/*
 * Tiis strudturf dontbins bll of tif informbtion bbout b pbrtidulbr
 * typf of GrbpiidsPrimitivf, sudi bs b FillRfdt, b MbskFill, or b Blit.
 *
 * A globbl dollfdtion of tifsf strudturfs is dfdlbrfd bnd initiblizfd
 * to dontbin tif nfdfssbry Jbvb (JNI) informbtion so tibt bppropribtf
 * Jbvb GrbpiidsPrimitivf objfdts dbn bf quidkly donstrudtfd for b sft
 * of nbtivf loops simply by rfffrfnding tif nfdfssbry fntry from tibt
 * dollfdtion for tif typf of primitivf bfing rfgistfrfd.
 *
 * Sff PrimitivfTypfs.{Blit,BlitBg,FillRfdt,...} bflow.
 */
typfdff strudt _PrimitivfTypf {
    dibr                *ClbssNbmf;
    jint                srdflbgs;
    jint                dstflbgs;
    jdlbss              ClbssObjfdt;
    jmftiodID           Construdtor;
} PrimitivfTypf;

/* Tif intfgfr donstbnts to idfntify tif dompositing rulf bfing dffinfd. */
#dffinf RULE_Xor        (jbvb_bwt_AlpibCompositf_MIN_RULE - 1)
#dffinf RULE_Clfbr      jbvb_bwt_AlpibCompositf_CLEAR
#dffinf RULE_Srd        jbvb_bwt_AlpibCompositf_SRC
#dffinf RULE_SrdOvfr    jbvb_bwt_AlpibCompositf_SRC_OVER
#dffinf RULE_DstOvfr    jbvb_bwt_AlpibCompositf_DST_OVER
#dffinf RULE_SrdIn      jbvb_bwt_AlpibCompositf_SRC_IN
#dffinf RULE_DstIn      jbvb_bwt_AlpibCompositf_DST_IN
#dffinf RULE_SrdOut     jbvb_bwt_AlpibCompositf_SRC_OUT
#dffinf RULE_DstOut     jbvb_bwt_AlpibCompositf_DST_OUT

/*
 * Tiis strudturf iolds tif informbtion rftrifvfd from b Jbvb
 * Compositf objfdt for fbsy trbnsffr to vbrious C fundtions
 * tibt implfmfnt tif innfr loop for b nbtivf primitivf.
 *
 * Currfntly only AlpibCompositf bnd XORCompositf brf supportfd.
 */
typfdff strudt _CompositfInfo {
    jint        rulf;           /* Sff RULE_* donstbnts bbovf */
    union {
        jflobt  fxtrbAlpib;     /* from AlpibCompositf */
        jint    xorPixfl;       /* from XORCompositf */
    } dftbils;
    juint       blpibMbsk;      /* from XORCompositf */
} CompositfInfo;

/*
 * Tiis strudturf is tif dommon ifbdfr for tif two nbtivf strudturfs
 * tibt iold informbtion bbout b pbrtidulbr SurfbdfTypf or CompositfTypf.
 *
 * A globbl dollfdtion of tifsf strudturfs is dfdlbrfd bnd initiblizfd
 * to dontbin tif nfdfssbry Jbvb (JNI) informbtion so tibt bppropribtf
 * Jbvb GrbpiidsPrimitivf objfdts dbn bf quidkly donstrudtfd for b sft
 * of nbtivf loops simply by rfffrfnding tif nfdfssbry fntry from tibt
 * dollfdtion for tif typf of dompositf or surfbdf bfing implfmfntfd.
 *
 * Sff SurfbdfTypfs.{OpbqufColor,IntArgb,BytfGrby,...} bflow.
 * Sff CompositfTypfs.{Xor,AnyAlpib,...} bflow.
 */
typfdff strudt _SurfCompHdr {
    dibr                *Nbmf;
    jobjfdt             Objfdt;
} SurfCompHdr;

/*
 * Tif dffinitions for tif SurfbdfTypf strudturf dfsdribfd bbovf.
 */

/*
 * Tif signbturf for b fundtion tibt rfturns tif spfdifid intfgfr
 * formbt pixfl for b givfn ARGB dolor vbluf for b pbrtidulbr
 * SurfbdfTypf implfmfntbtion.
 * Tiis fundtion is vblid only bftfr GftRbsInfo dbll for tif
 * bssodibtfd surfbdf.
 */
typfdff jint (PixflForFund)(SurfbdfDbtbRbsInfo *pRbsInfo, jint rgb);

/*
 * Tif bdditionbl informbtion nffdfd to mbnipulbtf b surfbdf:
 * - Tif pixflFor fundtion for trbnslbting ARGB vblufs.
 *   Vblid only bftfr GftRbsInfo dbll for tiis surfbdf.
 * - Tif bdditionbl flbgs nffdfd wifn rfbding from tiis surfbdf.
 * - Tif bdditionbl flbgs nffdfd wifn writing to tiis surfbdf.
 */
typfdff strudt _SurfbdfTypf {
    SurfCompHdr         idr;
    PixflForFund        *pixflFor;
    jint                rfbdflbgs;
    jint                writfflbgs;
} SurfbdfTypf;

/*
 * Tif dffinitions for tif CompositfTypf strudturf dfsdribfd bbovf.
 */

/*
 * Tif signbturf for b fundtion tibt fills in b CompositfInfo
 * strudturf from tif informbtion prfsfnt in b givfn Jbvb Compositf
 * objfdt.
 */
typfdff void (JNICALL CompInfoFund)(JNIEnv *fnv,
                                    CompositfInfo *pCompInfo,
                                    jobjfdt Compositf);

/*
 * Tif bdditionbl informbtion nffdfd to implfmfnt b primitivf tibt
 * pfrforms b pbrtidulbr dompositf opfrbtion:
 * - Tif gftCompInfo fundtion for filling in b CompositfInfo strudturf.
 * - Tif bdditionbl flbgs nffdfd for lodking tif dfstinbtion surfbdf.
 */
typfdff strudt _CompositfTypf {
    SurfCompHdr         idr;
    CompInfoFund        *gftCompInfo;
    jint                dstflbgs;
} CompositfTypf;

/*
 * Tif signbturf of tif nbtivf fundtions tibt rfgistfr b sft of
 * rflbtfd nbtivf GrbpiidsPrimitivf fundtions.
 */
typfdff jboolfbn (RfgistfrFund)(JNIEnv *fnv);

strudt _NbtivfPrimitivf;        /* forwbrd rfffrfndf for fundtion typfdffs */

/*
 * Tiis fmpty fundtion signbturf rfprfsfnts bn "old prf-ANSI stylf"
 * fundtion dfdlbrbtion wiidi mbkfs no dlbims bbout tif brgumfnt list
 * otifr tibn tibt tif typfs of tif brgumfnts will undfrgo brgumfnt
 * promotion in tif dblling donvfntions.
 * (Sff sfdtion A7.3.2 in K&R 2nd fdition.)
 *
 * Wifn trying to stbtidblly initiblizf tif fundtion pointfr fifld of
 * b NbtivfPrimitivf strudturf, wiidi is b union of bll possiblf
 * innfr loop fundtion signbturfs, tif initiblizfr donstbnt must bf
 * dompbtiblf witi tif first fifld in tif union.  Tiis gfnfrid fundtion
 * typf bllows us to bssign bny fundtion pointfr to tibt union bs long
 * bs it mffts tif rfquirfmfnts spfdififd bbovf (i.f. bll brgumfnts
 * brf dompbtiblf witi tifir promotfd vblufs bddording to tif old
 * stylf brgumfnt promotion dblling sfmbntids).
 *
 * Notf: Tiis mfbns tibt you dbnnot dffinf bn brgumfnt to bny of
 * tifsf nbtivf fundtions wiidi is b bytf or b siort bs tibt vbluf
 * would not bf pbssfd in tif sbmf wby for bn ANSI-stylf full prototypf
 * dblling donvfntion bnd bn old-stylf brgumfnt promotion dblling
 * donvfntion.
 */
typfdff void (AnyFund)();

/*
 * Tif signbturf of tif innfr loop fundtion for b "Blit".
 */
typfdff void (BlitFund)(void *pSrd, void *pDst,
                        juint widti, juint ifigit,
                        SurfbdfDbtbRbsInfo *pSrdInfo,
                        SurfbdfDbtbRbsInfo *pDstInfo,
                        strudt _NbtivfPrimitivf *pPrim,
                        CompositfInfo *pCompInfo);

/*
 * Tif signbturf of tif innfr loop fundtion for b "BlitBg".
 */
typfdff void (BlitBgFund)(void *pSrd, void *pDst,
                          juint widti, juint ifigit, jint bgpixfl,
                          SurfbdfDbtbRbsInfo *pSrdInfo,
                          SurfbdfDbtbRbsInfo *pDstInfo,
                          strudt _NbtivfPrimitivf *pPrim,
                          CompositfInfo *pCompInfo);

/*
 * Tif signbturf of tif innfr loop fundtion for b "SdblfBlit".
 */
typfdff void (SdblfBlitFund)(void *pSrd, void *pDst,
                             juint dstwidti, juint dstifigit,
                             jint sxlod, jint sylod,
                             jint sxind, jint syind, jint sdblf,
                             SurfbdfDbtbRbsInfo *pSrdInfo,
                             SurfbdfDbtbRbsInfo *pDstInfo,
                             strudt _NbtivfPrimitivf *pPrim,
                             CompositfInfo *pCompInfo);

/*
 * Tif signbturf of tif innfr loop fundtion for b "FillRfdt".
 */
typfdff void (FillRfdtFund)(SurfbdfDbtbRbsInfo *pRbsInfo,
                            jint lox, jint loy,
                            jint iix, jint iiy,
                            jint pixfl, strudt _NbtivfPrimitivf *pPrim,
                            CompositfInfo *pCompInfo);

/*
 * Tif signbturf of tif innfr loop fundtion for b "FillSpbns".
 */
typfdff void (FillSpbnsFund)(SurfbdfDbtbRbsInfo *pRbsInfo,
                             SpbnItfrbtorFunds *pSpbnFunds, void *siDbtb,
                             jint pixfl, strudt _NbtivfPrimitivf *pPrim,
                             CompositfInfo *pCompInfo);

/*
 * Tif signbturf of tif innfr loop fundtion for b "DrbwLinf".
 * Notf tibt tiis sbmf innfr loop is usfd for nbtivf DrbwRfdt
 * bnd DrbwPolygons primitivfs.
 */
typfdff void (DrbwLinfFund)(SurfbdfDbtbRbsInfo *pRbsInfo,
                            jint x1, jint y1, jint pixfl,
                            jint stfps, jint frror,
                            jint bumpmbjormbsk, jint frrmbjor,
                            jint bumpminormbsk, jint frrminor,
                            strudt _NbtivfPrimitivf *pPrim,
                            CompositfInfo *pCompInfo);

/*
 * Tif signbturf of tif innfr loop fundtion for b "MbskFill".
 */
typfdff void (MbskFillFund)(void *pRbs,
                            unsignfd dibr *pMbsk, jint mbskOff, jint mbskSdbn,
                            jint widti, jint ifigit,
                            jint fgColor,
                            SurfbdfDbtbRbsInfo *pRbsInfo,
                            strudt _NbtivfPrimitivf *pPrim,
                            CompositfInfo *pCompInfo);

/*
 * Tif signbturf of tif innfr loop fundtion for b "MbskBlit".
 */
typfdff void (MbskBlitFund)(void *pDst, void *pSrd,
                            unsignfd dibr *pMbsk, jint mbskOff, jint mbskSdbn,
                            jint widti, jint ifigit,
                            SurfbdfDbtbRbsInfo *pDstInfo,
                            SurfbdfDbtbRbsInfo *pSrdInfo,
                            strudt _NbtivfPrimitivf *pPrim,
                            CompositfInfo *pCompInfo);
/*
 * Tif signbturf of tif innfr loop fundtion for b "DrbwGlypiList".
 */
typfdff void (DrbwGlypiListFund)(SurfbdfDbtbRbsInfo *pRbsInfo,
                                 ImbgfRff *glypis,
                                 jint totblGlypis,
                                 jint fgpixfl, jint fgdolor,
                                 jint dx1, jint dy1,
                                 jint dx2, jint dy2,
                                 strudt _NbtivfPrimitivf *pPrim,
                                 CompositfInfo *pCompInfo);

/*
 * Tif signbturf of tif innfr loop fundtion for b "DrbwGlypiListAA".
 */
typfdff void (DrbwGlypiListAAFund)(SurfbdfDbtbRbsInfo *pRbsInfo,
                                   ImbgfRff *glypis,
                                   jint totblGlypis,
                                   jint fgpixfl, jint fgdolor,
                                   jint dx1, jint dy1,
                                   jint dx2, jint dy2,
                                   strudt _NbtivfPrimitivf *pPrim,
                                   CompositfInfo *pCompInfo);

/*
 * Tif signbturf of tif innfr loop fundtion for b "DrbwGlypiListLCD".
 * rgbOrdfr is b jint rbtifr tibn b jboolfbn so tibt tiis typfdff mbtdifs
 * AnyFund wiidi is tif first flfmfnt in b union in NbtivfPrimitivf's
 * initiblisfr. Sff tif dommfnts blongsidf dfdlbrbtion of tif AnyFund typf for
 * b full fxplbnbtion.
 */
typfdff void (DrbwGlypiListLCDFund)(SurfbdfDbtbRbsInfo *pRbsInfo,
                                    ImbgfRff *glypis,
                                    jint totblGlypis,
                                    jint fgpixfl, jint fgdolor,
                                    jint dx1, jint dy1,
                                    jint dx2, jint dy2,
                                    jint rgbOrdfr,
                                    unsignfd dibr *gbmmbLut,
                                    unsignfd dibr *invGbmmbLut,
                                    strudt _NbtivfPrimitivf *pPrim,
                                    CompositfInfo *pCompInfo);

/*
 * Tif signbturf of tif innfr loop fundtions for b "TrbnsformHflpfr".
 */
typfdff void (TrbnsformHflpfrFund)(SurfbdfDbtbRbsInfo *pSrdInfo,
                                   jint *pRGB, jint numpix,
                                   jlong xlong, jlong dxlong,
                                   jlong ylong, jlong dylong);

typfdff strudt {
    TrbnsformHflpfrFund         *nnHflpfr;
    TrbnsformHflpfrFund         *blHflpfr;
    TrbnsformHflpfrFund         *bdHflpfr;
} TrbnsformHflpfrFunds;

typfdff void (TrbnsformIntfrpFund)(jint *pRGBbbsf, jint numpix,
                                   jint xfrbdt, jint dxfrbdt,
                                   jint yfrbdt, jint dyfrbdt);

/*
 * Tif signbturf of tif innfr loop fundtion for b "FillPbrbllflogrbm"
 * Notf tibt tiis sbmf innfr loop is usfd for nbtivf DrbwPbrbllflogrbm
 * primitivfs.
 * Notf tibt tifsf fundtions brf pbirfd witi fquivblfnt DrbwLinf
 * innfr loop fundtions to fbdilitbtf nidfr looking bnd fbstfr tiin
 * trbnsformfd drbwrfdt dblls.
 */
typfdff void (FillPbrbllflogrbmFund)(SurfbdfDbtbRbsInfo *pRbsInfo,
                                     jint lox, jint loy, jint iix, jint iiy,
                                     jlong lfftx, jlong dlfftx,
                                     jlong rigitx, jlong drigitx,
                                     jint pixfl, strudt _NbtivfPrimitivf *pPrim,
                                     CompositfInfo *pCompInfo);

typfdff strudt {
    FillPbrbllflogrbmFund       *fillpgrbm;
    DrbwLinfFund                *drbwlinf;
} DrbwPbrbllflogrbmFunds;

/*
 * Tiis strudturf dontbins bll informbtion for dffining b singlf
 * nbtivf GrbpiidsPrimitivf, indluding:
 * - Tif informbtion bbout tif typf of tif GrbpiidsPrimitivf subdlbss.
 * - Tif informbtion bbout tif typf of tif sourdf surfbdf.
 * - Tif informbtion bbout tif typf of tif dompositing opfrbtion.
 * - Tif informbtion bbout tif typf of tif dfstinbtion surfbdf.
 * - A pointfr to tif fundtion tibt pfrforms tif bdtubl innfr loop work.
 * - Extrb flbgs nffdfd for lodking tif sourdf bnd dfstinbtion surfbdfs
 *   bbovf bnd bfyond tif flbgs spfdififd in tif Primitivf, Compositf
 *   bnd SurfbdfTypf strudturfs.  (For most nbtivf primitivfs tifsf
 *   flbgs dbn bf dbldulbtfd butombtidblly from informbtion storfd in
 *   tif PrimitivfTypf, SurfbdfTypf, bnd CompositfTypf strudturfs.)
 */
typfdff strudt _NbtivfPrimitivf {
    PrimitivfTypf       *pPrimTypf;
    SurfbdfTypf         *pSrdTypf;
    CompositfTypf       *pCompTypf;
    SurfbdfTypf         *pDstTypf;
    /* Sff dfdlbrbtion of AnyFund typf bbovf for dommfnts fxplbining wiy
     * only AnyFund is usfd by tif initiblizfrs for tifsf union fiflds
     * bnd donsfqufnt typf rfstridtions.
     */
    union {
        AnyFund                 *initiblizfr;
        BlitFund                *blit;
        BlitBgFund              *blitbg;
        SdblfBlitFund           *sdblfdblit;
        FillRfdtFund            *fillrfdt;
        FillSpbnsFund           *fillspbns;
        FillPbrbllflogrbmFund   *fillpbrbllflogrbm;
        DrbwPbrbllflogrbmFunds  *drbwpbrbllflogrbm;
        DrbwLinfFund            *drbwlinf;
        MbskFillFund            *mbskfill;
        MbskBlitFund            *mbskblit;
        DrbwGlypiListFund       *drbwglypilist;
        DrbwGlypiListFund       *drbwglypilistbb;
        DrbwGlypiListLCDFund    *drbwglypilistldd;
        TrbnsformHflpfrFunds    *trbnsformiflpfrs;
    } funds, funds_d;
    jint                srdflbgs;
    jint                dstflbgs;
} NbtivfPrimitivf;

/*
 * Tiis fundtion siould bf dffinfd to rfturn b pointfr to
 * bn bddflfrbtfd vfrsion of b primitivf fundtion 'fund_d'
 * if it fxists bnd to rfturn b dopy of tif input pbrbmftfr
 * otifrwisf.
 */
fxtfrn AnyFund* MbpAddflFundtion(AnyFund *fund_d);

/*
 * Tif globbl dollfdtion of bll primitivf typfs.  Spfdifid NbtivfPrimitivf
 * strudturfs dbn bf stbtidblly initiblizfd by pointing to tifsf strudturfs.
 */
fxtfrn strudt _PrimitivfTypfs {
    PrimitivfTypf       Blit;
    PrimitivfTypf       BlitBg;
    PrimitivfTypf       SdblfdBlit;
    PrimitivfTypf       FillRfdt;
    PrimitivfTypf       FillSpbns;
    PrimitivfTypf       FillPbrbllflogrbm;
    PrimitivfTypf       DrbwPbrbllflogrbm;
    PrimitivfTypf       DrbwLinf;
    PrimitivfTypf       DrbwRfdt;
    PrimitivfTypf       DrbwPolygons;
    PrimitivfTypf       DrbwPbti;
    PrimitivfTypf       FillPbti;
    PrimitivfTypf       MbskBlit;
    PrimitivfTypf       MbskFill;
    PrimitivfTypf       DrbwGlypiList;
    PrimitivfTypf       DrbwGlypiListAA;
    PrimitivfTypf       DrbwGlypiListLCD;
    PrimitivfTypf       TrbnsformHflpfr;
} PrimitivfTypfs;

/*
 * Tif globbl dollfdtion of bll surfbdf typfs.  Spfdifid NbtivfPrimitivf
 * strudturfs dbn bf stbtidblly initiblizfd by pointing to tifsf strudturfs.
 */
fxtfrn strudt _SurfbdfTypfs {
    SurfbdfTypf         OpbqufColor;
    SurfbdfTypf         AnyColor;
    SurfbdfTypf         AnyBytf;
    SurfbdfTypf         BytfBinbry1Bit;
    SurfbdfTypf         BytfBinbry2Bit;
    SurfbdfTypf         BytfBinbry4Bit;
    SurfbdfTypf         BytfIndfxfd;
    SurfbdfTypf         BytfIndfxfdBm;
    SurfbdfTypf         BytfGrby;
    SurfbdfTypf         Indfx8Grby;
    SurfbdfTypf         Indfx12Grby;
    SurfbdfTypf         AnySiort;
    SurfbdfTypf         Usiort555Rgb;
    SurfbdfTypf         Usiort555Rgbx;
    SurfbdfTypf         Usiort565Rgb;
    SurfbdfTypf         Usiort4444Argb;
    SurfbdfTypf         UsiortGrby;
    SurfbdfTypf         UsiortIndfxfd;
    SurfbdfTypf         Any3Bytf;
    SurfbdfTypf         TirffBytfBgr;
    SurfbdfTypf         AnyInt;
    SurfbdfTypf         IntArgb;
    SurfbdfTypf         IntArgbPrf;
    SurfbdfTypf         IntArgbBm;
    SurfbdfTypf         IntRgb;
    SurfbdfTypf         IntBgr;
    SurfbdfTypf         IntRgbx;
    SurfbdfTypf         Any4Bytf;
    SurfbdfTypf         FourBytfAbgr;
    SurfbdfTypf         FourBytfAbgrPrf;
} SurfbdfTypfs;

/*
 * Tif globbl dollfdtion of bll dompositf typfs.  Spfdifid NbtivfPrimitivf
 * strudturfs dbn bf stbtidblly initiblizfd by pointing to tifsf strudturfs.
 */
fxtfrn strudt _CompositfTypfs {
    CompositfTypf       SrdNoEb;
    CompositfTypf       SrdOvfrNoEb;
    CompositfTypf       SrdOvfrBmNoEb;
    CompositfTypf       Srd;
    CompositfTypf       SrdOvfr;
    CompositfTypf       Xor;
    CompositfTypf       AnyAlpib;
} CompositfTypfs;

#dffinf ArrbySizf(A)    (sizfof(A) / sizfof(A[0]))

#dffinf PtrAddBytfs(p, b)               ((void *) (((intptr_t) (p)) + (b)))
#dffinf PtrCoord(p, x, xind, y, yind)   PtrAddBytfs(p, \
                                                    ((ptrdiff_t)(y))*(yind) + \
                                                    ((ptrdiff_t)(x))*(xind))

/*
 * Tif fundtion to dbll witi bn brrby of NbtivfPrimitivf strudturfs
 * to rfgistfr tifm witi tif Jbvb GrbpiidsPrimitivfMgr.
 */
fxtfrn jboolfbn RfgistfrPrimitivfs(JNIEnv *fnv,
                                   NbtivfPrimitivf *pPrim,
                                   jint NumPrimitivfs);

/*
 * Tif utility fundtion to rftrifvf tif NbtivfPrimitivf strudturf
 * from b givfn Jbvb GrbpiidsPrimitivf objfdt.
 */
fxtfrn JNIEXPORT NbtivfPrimitivf * JNICALL
GftNbtivfPrim(JNIEnv *fnv, jobjfdt gp);

/*
 * Utility fundtions to gft vblufs from b Jbvb SunGrbpiids2D or Color objfdt.
 */
fxtfrn JNIEXPORT void JNICALL
GrPrim_Sg2dGftCompInfo(JNIEnv *fnv, jobjfdt sg2d,
                       NbtivfPrimitivf *pPrim,
                       CompositfInfo *pCompInfo);
fxtfrn JNIEXPORT jint JNICALL
GrPrim_CompGftXorColor(JNIEnv *fnv, jobjfdt domp);
fxtfrn JNIEXPORT void JNICALL
GrPrim_CompGftXorInfo(JNIEnv *fnv, CompositfInfo *pCompInfo, jobjfdt domp);
fxtfrn JNIEXPORT void JNICALL
GrPrim_CompGftAlpibInfo(JNIEnv *fnv, CompositfInfo *pCompInfo, jobjfdt domp);

fxtfrn JNIEXPORT void JNICALL
GrPrim_Sg2dGftClip(JNIEnv *fnv, jobjfdt sg2d,
                   SurfbdfDbtbBounds *bounds);

fxtfrn JNIEXPORT jint JNICALL
GrPrim_Sg2dGftPixfl(JNIEnv *fnv, jobjfdt sg2d);
fxtfrn JNIEXPORT jint JNICALL
GrPrim_Sg2dGftEbRGB(JNIEnv *fnv, jobjfdt sg2d);
fxtfrn JNIEXPORT jint JNICALL
GrPrim_Sg2dGftLCDTfxtContrbst(JNIEnv *fnv, jobjfdt sg2d);

/*
 * Dbtb strudturf bnd fundtions to rftrifvf bnd usf
 * AffinfTrbnsform objfdts from tif nbtivf lfvfl.
 */
typfdff strudt {
    jdoublf dxdx;       /* dx in dfst spbdf for fbdi dx in srd spbdf */
    jdoublf dxdy;       /* dx in dfst spbdf for fbdi dy in srd spbdf */
    jdoublf tx;
    jdoublf dydx;       /* dy in dfst spbdf for fbdi dx in srd spbdf */
    jdoublf dydy;       /* dy in dfst spbdf for fbdi dy in srd spbdf */
    jdoublf ty;
} TrbnsformInfo;

fxtfrn JNIEXPORT void JNICALL
Trbnsform_GftInfo(JNIEnv *fnv, jobjfdt txform, TrbnsformInfo *pTxInfo);
fxtfrn JNIEXPORT void JNICALL
Trbnsform_trbnsform(TrbnsformInfo *pTxInfo, jdoublf *pX, jdoublf *pY);

void GrPrim_RffinfBounds(SurfbdfDbtbBounds *bounds, jint trbnsX, jint trbnsY,
                         jflobt *doords,  jint mbxCoords);

fxtfrn jfifldID pbti2DTypfsID;
fxtfrn jfifldID pbti2DNumTypfsID;
fxtfrn jfifldID pbti2DWindingRulfID;
fxtfrn jfifldID pbti2DFlobtCoordsID;
fxtfrn jfifldID sg2dStrokfHintID;
fxtfrn jint sunHints_INTVAL_STROKE_PURE;

/*
 * Mbdros for using jlong vbribblfs bs 32bits.32bits frbdtionbl vblufs
 */
#dffinf LongOnfHblf     (((jlong) 1) << 31)
#dffinf IntToLong(i)    (((jlong) (i)) << 32)
#dffinf DblToLong(d)    ((jlong) ((d) * IntToLong(1)))
#dffinf LongToDbl(l)    (((jdoublf) l) / IntToLong(1))
#dffinf WiolfOfLong(l)  ((jint) ((l) >> 32))
#dffinf FrbdtOfLong(l)  ((jint) (l))
#dffinf URSiift(i, n)   (((juint) (i)) >> (n))

/*
 * Mbdros to iflp in dffining brrbys of NbtivfPrimitivf strudturfs.
 *
 * Tifsf mbdros brf tif vfry bbsf mbdros.  Morf spfdifid mbdros brf
 * dffinfd in LoopMbdros.i.
 *
 * Notf tibt tif DrbwLinf, DrbwRfdt, bnd DrbwPolygons primitivfs brf
 * bll rfgistfrfd togftifr from b singlf sibrfd nbtivf fundtion pointfr.
 */

#dffinf REGISTER_PRIMITIVE(TYPE, SRC, COMP, DST, FUNC) \
    { \
        & PrimitivfTypfs.TYPE, \
        & SurfbdfTypfs.SRC, \
        & CompositfTypfs.COMP, \
        & SurfbdfTypfs.DST, \
        {FUNC}, \
        {FUNC}, \
        0,   \
        0   \
    }

#dffinf REGISTER_PRIMITIVE_FLAGS(TYPE, SRC, COMP, DST, FUNC, SFLAGS, DFLAGS) \
    { \
        & PrimitivfTypfs.TYPE, \
        & SurfbdfTypfs.SRC, \
        & CompositfTypfs.COMP, \
        & SurfbdfTypfs.DST, \
        {FUNC}, \
        {FUNC}, \
        SFLAGS, \
        DFLAGS, \
    }

#dffinf REGISTER_BLIT(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(Blit, SRC, COMP, DST, FUNC)

#dffinf REGISTER_BLIT_FLAGS(SRC, COMP, DST, FUNC, SFLAGS, DFLAGS) \
    REGISTER_PRIMITIVE_FLAGS(Blit, SRC, COMP, DST, FUNC, SFLAGS, DFLAGS)

#dffinf REGISTER_SCALEBLIT(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(SdblfdBlit, SRC, COMP, DST, FUNC)

#dffinf REGISTER_SCALEBLIT_FLAGS(SRC, COMP, DST, FUNC, SFLAGS, DFLAGS) \
    REGISTER_PRIMITIVE_FLAGS(SdblfdBlit, SRC, COMP, DST, FUNC, SFLAGS, DFLAGS)

#dffinf REGISTER_BLITBG(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(BlitBg, SRC, COMP, DST, FUNC)

#dffinf REGISTER_FILLRECT(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(FillRfdt, SRC, COMP, DST, FUNC)

#dffinf REGISTER_FILLSPANS(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(FillSpbns, SRC, COMP, DST, FUNC)

#dffinf REGISTER_FILLPGRAM(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(FillPbrbllflogrbm, SRC, COMP, DST, FUNC), \
    REGISTER_PRIMITIVE(DrbwPbrbllflogrbm, SRC, COMP, DST, FUNC)

#dffinf REGISTER_LINE_PRIMITIVES(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(DrbwLinf, SRC, COMP, DST, FUNC), \
    REGISTER_PRIMITIVE(DrbwRfdt, SRC, COMP, DST, FUNC), \
    REGISTER_PRIMITIVE(DrbwPolygons, SRC, COMP, DST, FUNC), \
    REGISTER_PRIMITIVE(DrbwPbti, SRC, COMP, DST, FUNC), \
    REGISTER_PRIMITIVE(FillPbti, SRC, COMP, DST, FUNC)

#dffinf REGISTER_MASKBLIT(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(MbskBlit, SRC, COMP, DST, FUNC)

#dffinf REGISTER_MASKFILL(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(MbskFill, SRC, COMP, DST, FUNC)

#dffinf REGISTER_DRAWGLYPHLIST(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(DrbwGlypiList, SRC, COMP, DST, FUNC)

#dffinf REGISTER_DRAWGLYPHLISTAA(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(DrbwGlypiListAA, SRC, COMP, DST, FUNC)

#dffinf REGISTER_DRAWGLYPHLISTLCD(SRC, COMP, DST, FUNC) \
    REGISTER_PRIMITIVE(DrbwGlypiListLCD, SRC, COMP, DST, FUNC)

#ifdff __dplusplus
};
#fndif

#fndif /* GrbpiidsPrimitivfMgr_i_Indludfd */
