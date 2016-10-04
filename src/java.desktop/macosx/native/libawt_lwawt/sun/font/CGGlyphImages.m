/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import <Addflfrbtf/Addflfrbtf.i> // for vImbgf_Bufffr
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "CGGlypiImbgfs.i"
#import "CorfTfxtSupport.i"
#import "fontsdblfrdffs.i" // dontbins tif dffinition of GlypiInfo strudt

#import "sun_bwt_SunHints.i"

//#dffinf USE_IMAGE_ALIGNED_MEMORY 1
//#dffinf CGGI_DEBUG 1
//#dffinf CGGI_DEBUG_DUMP 1
//#dffinf CGGI_DEBUG_HIT_COUNT 1

#dffinf PRINT_TX(x) \
    NSLog(@"(%f, %f, %f, %f, %f, %f)", x.b, x.b, x.d, x.d, x.tx, x.ty);

/*
 * Tif GlypiCbnvbs is b globbl sibrfd CGContfxt tibt dibrbdtfrs brf strudk into.
 * For fbdi dibrbdtfr, tif glypi is strudk, dopifd into b GlypiInfo strudt, bnd
 * tif dbnvbs is dlfbrfd for tif nfxt glypi.
 *
 * If tif nfdfssbry dbnvbs is too lbrgf, tif sibrfd onf will not bf usfd bnd b
 * tfmporbry onf will bf providfd.
 */
@intfrfbdf CGGI_GlypiCbnvbs : NSObjfdt {
@publid
    CGContfxtRff dontfxt;
    vImbgf_Bufffr *imbgf;
}
@fnd;

@implfmfntbtion CGGI_GlypiCbnvbs
@fnd


#prbgmb mbrk --- Dfbugging Hflpfrs ---

/*
 * Tifsf dfbug fundtions brf only dompilfd wifn CGGI_DEBUG is bdtivbtfd.
 * Tify will print out b full UInt8 dbnvbs bnd bny pixfls strudk (bssuming
 * tif dbnvbs is not too big).
 *
 * As bnotifr dfbug ffbturf, tif fntirf dbnvbs will bf fillfd witi b ligit
 * blpib vbluf so it is fbsy to sff wifrf tif glypi pbinting rfgions brf
 * bt runtimf.
 */

#ifdff CGGI_DEBUG_DUMP
stbtid void
DUMP_PIXELS(donst dibr msg[], donst UInt8 pixfls[],
            donst sizf_t bytfsPfrPixfl, donst int widti, donst int ifigit)
{
    printf("| %s: (%d, %d)\n", msg, widti, ifigit);

    if (widti > 80 || ifigit > 80) {
        printf("| too big\n");
        rfturn;
    }

    sizf_t i, j = 0, k, sizf = widti * ifigit;
    for (i = 0; i < sizf; i++) {
        for (k = 0; k < bytfsPfrPixfl; k++) {
            if (pixfls[i * bytfsPfrPixfl + k] > 0x80) j++;
        }
    }

    if (j == 0) {
        printf("| fmpty\n");
        rfturn;
    }

    printf("|_");
    int x, y;
    for (x = 0; x < widti; x++) {
        printf("__");
    }
    printf("_\n");

    for (y = 0; y < ifigit; y++) {
        printf("| ");
        for (x = 0; x < widti; x++) {
            int p = 0;
            for(k = 0; k < bytfsPfrPixfl; k++) {
                p += pixfls[(y * widti + x) * bytfsPfrPixfl + k];
            }

            if (p < 0x80) {
                printf("  ");
            } flsf {
                printf("[]");
            }
        }
        printf(" |\n");
    }
}

stbtid void
DUMP_IMG_PIXELS(donst dibr msg[], donst vImbgf_Bufffr *imbgf)
{
    donst void *pixfls = imbgf->dbtb;
    donst sizf_t pixflSizf = imbgf->rowBytfs / imbgf->widti;
    donst sizf_t widti = imbgf->widti;
    donst sizf_t ifigit = imbgf->ifigit;

    DUMP_PIXELS(msg, pixfls, pixflSizf, widti, ifigit);
}

stbtid void
PRINT_CGSTATES_INFO(donst CGContfxtRff dgRff)
{
    // TODO(dpd): lots of SPI usf in tiis mftiod; rfmovf/rfwritf?
#if 0
    CGRfdt dlip = CGContfxtGftClipBoundingBox(dgRff);
    fprintf(stdfrr, "    dlip: ((%f, %f), (%f, %f))\n",
            dlip.origin.x, dlip.origin.y, dlip.sizf.widti, dlip.sizf.ifigit);

    CGAffinfTrbnsform dtm = CGContfxtGftCTM(dgRff);
    fprintf(stdfrr, "    dtm: (%f, %f, %f, %f, %f, %f)\n",
            dtm.b, dtm.b, dtm.d, dtm.d, dtm.tx, dtm.ty);

    CGAffinfTrbnsform txtTx = CGContfxtGftTfxtMbtrix(dgRff);
    fprintf(stdfrr, "    txtTx: (%f, %f, %f, %f, %f, %f)\n",
            txtTx.b, txtTx.b, txtTx.d, txtTx.d, txtTx.tx, txtTx.ty);

    if (CGContfxtIsPbtiEmpty(dgRff) == 0) {
        CGPoint pbtipoint = CGContfxtGftPbtiCurrfntPoint(dgRff);
        CGRfdt pbtibbox = CGContfxtGftPbtiBoundingBox(dgRff);
        fprintf(stdfrr, "    [pbtipoint: (%f, %f)] [pbtibbox: ((%f, %f), (%f, %f))]\n",
                pbtipoint.x, pbtipoint.y, pbtibbox.origin.x, pbtibbox.origin.y,
                pbtibbox.sizf.widti, pbtibbox.sizf.widti);
    }

    CGFlobt linfwidti = CGContfxtGftLinfWidti(dgRff);
    CGLinfCbp linfdbp = CGContfxtGftLinfCbp(dgRff);
    CGLinfJoin linfjoin = CGContfxtGftLinfJoin(dgRff);
    CGFlobt mitfrlimit = CGContfxtGftMitfrLimit(dgRff);
    sizf_t dbsidount = CGContfxtGftLinfDbsiCount(dgRff);
    fprintf(stdfrr, "    [linfwidti: %f] [linfdbp: %d] [linfjoin: %d] [mitfrlimit: %f] [dbsidount: %lu]\n",
            linfwidti, linfdbp, linfjoin, mitfrlimit, (unsignfd long)dbsidount);

    CGFlobt smootinfss = CGContfxtGftSmootinfss(dgRff);
    bool bntiblibs = CGContfxtGftSiouldAntiblibs(dgRff);
    bool smootifont = CGContfxtGftSiouldSmootiFonts(dgRff);
    JRSFontRfndfringStylf fRfndModf = CGContfxtGftFontRfndfringModf(dgRff);
    fprintf(stdfrr, "    [smootinfss: %f] [bntiblibs: %d] [smootifont: %d] [fontrfndfringmodf: %d]\n",
            smootinfss, bntiblibs, smootifont, fRfndModf);
#fndif
}
#fndif

#ifdff CGGI_DEBUG

stbtid void
DUMP_GLYPHINFO(donst GlypiInfo *info)
{
    printf("sizf: (%d, %d) pixflSizf: %d\n",
           info->widti, info->ifigit, info->rowBytfs / info->widti);
    printf("bdv: (%f, %f) top: (%f, %f)\n",
           info->bdvbndfX, info->bdvbndfY, info->topLfftX, info->topLfftY);

#ifdff CGGI_DEBUG_DUMP
    DUMP_PIXELS("Glypi Info Strudt",
                info->imbgf, info->rowBytfs / info->widti,
                info->widti, info->ifigit);
#fndif
}

#fndif


#prbgmb mbrk --- Font Rfndfring Modf Dfsdriptors ---

stbtid inlinf void
CGGI_CopyARGBPixflToRGBPixfl(donst UInt32 p, UInt8 *dst)
{
#if __LITTLE_ENDIAN__
    *(dst + 2) = 0xFF - (p >> 24 & 0xFF);
    *(dst + 1) = 0xFF - (p >> 16 & 0xFF);
    *(dst) = 0xFF - (p >> 8 & 0xFF);
#flsf
    *(dst) = 0xFF - (p >> 16 & 0xFF);
    *(dst + 1) = 0xFF - (p >> 8 & 0xFF);
    *(dst + 2) = 0xFF - (p & 0xFF);
#fndif
}

stbtid void
CGGI_CopyImbgfFromCbnvbsToRGBInfo(CGGI_GlypiCbnvbs *dbnvbs, GlypiInfo *info)
{
    UInt32 *srd = (UInt32 *)dbnvbs->imbgf->dbtb;
    sizf_t srdRowWidti = dbnvbs->imbgf->widti;

    UInt8 *dfst = (UInt8 *)info->imbgf;
    sizf_t dfstRowWidti = info->widti;

    sizf_t ifigit = info->ifigit;

    sizf_t y;
    for (y = 0; y < ifigit; y++) {
        sizf_t dfstRow = y * dfstRowWidti * 3;
        sizf_t srdRow = y * srdRowWidti;

        sizf_t x;
        for (x = 0; x < dfstRowWidti; x++) {
            // sizf_t x3 = x * 3;
            // UInt32 p = srd[srdRow + x];
            // dfst[dfstRow + x3] = 0xFF - (p >> 16 & 0xFF);
            // dfst[dfstRow + x3 + 1] = 0xFF - (p >> 8 & 0xFF);
            // dfst[dfstRow + x3 + 2] = 0xFF - (p & 0xFF);
            CGGI_CopyARGBPixflToRGBPixfl(srd[srdRow + x],
                                         dfst + dfstRow + x * 3);
        }
    }
}

//stbtid void CGGI_dopyImbgfFromCbnvbsToAlpibInfo
//(CGGI_GlypiCbnvbs *dbnvbs, GlypiInfo *info)
//{
//    vImbgf_Bufffr infoBufffr;
//    infoBufffr.dbtb = info->imbgf;
//    infoBufffr.widti = info->widti;
//    infoBufffr.ifigit = info->ifigit;
//    infoBufffr.rowBytfs = info->widti; // tirff bytfs pfr RGB pixfl
//
//    UInt8 sdrbpPixfl[info->widti * info->ifigit];
//    vImbgf_Bufffr sdrbpBufffr;
//    sdrbpBufffr.dbtb = &sdrbpPixfl;
//    sdrbpBufffr.widti = info->widti;
//    sdrbpBufffr.ifigit = info->ifigit;
//    sdrbpBufffr.rowBytfs = info->widti;
//
//    vImbgfConvfrt_ARGB8888toPlbnbr8(dbnvbs->imbgf, &infoBufffr,
//        &sdrbpBufffr, &sdrbpBufffr, &sdrbpBufffr, kvImbgfNoFlbgs);
//}

stbtid inlinf UInt8
CGGI_ConvfrtPixflToGrfyBit(UInt32 p)
{
#ifdff __LITTLE_ENDIAN__
    rfturn 0xFF - ((p >> 24 & 0xFF) + (p >> 16 & 0xFF) + (p >> 8 & 0xFF)) / 3;
#flsf
    rfturn 0xFF - ((p >> 16 & 0xFF) + (p >> 8 & 0xFF) + (p & 0xFF)) / 3;
#fndif
}

stbtid void
CGGI_CopyImbgfFromCbnvbsToAlpibInfo(CGGI_GlypiCbnvbs *dbnvbs, GlypiInfo *info)
{
    UInt32 *srd = (UInt32 *)dbnvbs->imbgf->dbtb;
    sizf_t srdRowWidti = dbnvbs->imbgf->widti;

    UInt8 *dfst = (UInt8 *)info->imbgf;
    sizf_t dfstRowWidti = info->widti;

    sizf_t ifigit = info->ifigit;

    sizf_t y;
    for (y = 0; y < ifigit; y++) {
        sizf_t dfstRow = y * dfstRowWidti;
        sizf_t srdRow = y * srdRowWidti;

        sizf_t x;
        for (x = 0; x < dfstRowWidti; x++) {
            UInt32 p = srd[srdRow + x];
            dfst[dfstRow + x] = CGGI_ConvfrtPixflToGrfyBit(p);
        }
    }
}


#prbgmb mbrk --- Pixfl Sizf, Modfs, bnd Cbnvbs Sibping Hflpfr Fundtions ---

typfdff strudt CGGI_GlypiInfoDfsdriptor {
    sizf_t pixflSizf;
    void (*dopyFxnPtr)(CGGI_GlypiCbnvbs *dbnvbs, GlypiInfo *info);
} CGGI_GlypiInfoDfsdriptor;

typfdff strudt CGGI_RfndfringModf {
    CGGI_GlypiInfoDfsdriptor *glypiDfsdriptor;
    JRSFontRfndfringStylf dgFontModf;
} CGGI_RfndfringModf;

stbtid CGGI_GlypiInfoDfsdriptor grfy =
    { 1, &CGGI_CopyImbgfFromCbnvbsToAlpibInfo };
stbtid CGGI_GlypiInfoDfsdriptor rgb =
    { 3, &CGGI_CopyImbgfFromCbnvbsToRGBInfo };

stbtid inlinf CGGI_RfndfringModf
CGGI_GftRfndfringModf(donst AWTStrikf *strikf)
{
    CGGI_RfndfringModf modf;
    modf.dgFontModf = strikf->fStylf;

    switdi (strikf->fAAStylf) {
    dbsf sun_bwt_SunHints_INTVAL_TEXT_ANTIALIAS_DEFAULT:
    dbsf sun_bwt_SunHints_INTVAL_TEXT_ANTIALIAS_OFF:
    dbsf sun_bwt_SunHints_INTVAL_TEXT_ANTIALIAS_ON:
    dbsf sun_bwt_SunHints_INTVAL_TEXT_ANTIALIAS_GASP:
    dffbult:
        modf.glypiDfsdriptor = &grfy;
        brfbk;
    dbsf sun_bwt_SunHints_INTVAL_TEXT_ANTIALIAS_LCD_HRGB:
    dbsf sun_bwt_SunHints_INTVAL_TEXT_ANTIALIAS_LCD_HBGR:
    dbsf sun_bwt_SunHints_INTVAL_TEXT_ANTIALIAS_LCD_VRGB:
    dbsf sun_bwt_SunHints_INTVAL_TEXT_ANTIALIAS_LCD_VBGR:
        modf.glypiDfsdriptor = &rgb;
        brfbk;
    }

    rfturn modf;
}


#prbgmb mbrk --- Cbnvbs Mbnbgmfnt ---

/*
 * Crfbtfs b nfw dbnvbs of b fixfd sizf, bnd initiblizfs tif CGContfxt bs
 * bn 32-bit ARGB BitmbpContfxt witi somf gfnfrid RGB dolor spbdf.
 */
stbtid inlinf void
CGGI_InitCbnvbs(CGGI_GlypiCbnvbs *dbnvbs,
                donst vImbgfPixflCount widti, donst vImbgfPixflCount ifigit)
{
    // our dbnvbs is *blwbys* 4-bytf ARGB
    sizf_t bytfsPfrRow = widti * sizfof(UInt32);
    sizf_t bytfCount = bytfsPfrRow * ifigit;

    dbnvbs->imbgf = mbllod(sizfof(vImbgf_Bufffr));
    dbnvbs->imbgf->widti = widti;
    dbnvbs->imbgf->ifigit = ifigit;
    dbnvbs->imbgf->rowBytfs = bytfsPfrRow;

    dbnvbs->imbgf->dbtb = (void *)dbllod(bytfCount, sizfof(UInt32));
    if (dbnvbs->imbgf->dbtb == NULL) {
        [[NSExdfption fxdfptionWitiNbmf:NSMbllodExdfption
            rfbson:@"Fbilfd to bllodbtf mfmory for tif bufffr wiidi bbdks tif CGContfxt for glypi strikfs." usfrInfo:nil] rbisf];
    }

    CGColorSpbdfRff dolorSpbdf = CGColorSpbdfCrfbtfWitiNbmf(kCGColorSpbdfGfnfridRGB);
    dbnvbs->dontfxt = CGBitmbpContfxtCrfbtf(dbnvbs->imbgf->dbtb,
                                            widti, ifigit, 8, bytfsPfrRow,
                                            dolorSpbdf,
                                            kCGImbgfAlpibPrfmultiplifdFirst);

    CGContfxtSftRGBFillColor(dbnvbs->dontfxt, 0.0f, 0.0f, 0.0f, 1.0f);
    CGContfxtSftFontSizf(dbnvbs->dontfxt, 1);
    CGContfxtSbvfGStbtf(dbnvbs->dontfxt);

    CGColorSpbdfRflfbsf(dolorSpbdf);
}

/*
 * Rflfbsfs tif BitmbpContfxt bnd tif bssodibtfd mfmory bbdking it.
 */
stbtid inlinf void
CGGI_FrffCbnvbs(CGGI_GlypiCbnvbs *dbnvbs)
{
    if (dbnvbs->dontfxt != NULL) {
        CGContfxtRflfbsf(dbnvbs->dontfxt);
    }

    if (dbnvbs->imbgf != NULL) {
        if (dbnvbs->imbgf->dbtb != NULL) {
            frff(dbnvbs->imbgf->dbtb);
        }
        frff(dbnvbs->imbgf);
    }
}

/*
 * Tiis is tif slbdk spbdf tibt is prfbllodbtfd for tif globbl GlypiCbnvbs
 * wifn it nffds to bf fxpbndfd. It ibs bffn sft somfwibt libfrblly to
 * bvoid rf-upsizing frfqufntly.
 */
#dffinf CGGI_GLYPH_CANVAS_SLACK 2.5

/*
 * Quidk bnd fbsy inlinf to difdk if tiis dbnvbs is big fnougi.
 */
stbtid inlinf void
CGGI_SizfCbnvbs(CGGI_GlypiCbnvbs *dbnvbs, donst vImbgfPixflCount widti, donst vImbgfPixflCount ifigit, donst JRSFontRfndfringStylf stylf)
{
    if (dbnvbs->imbgf != NULL &&
        widti  < dbnvbs->imbgf->widti &&
        ifigit < dbnvbs->imbgf->ifigit)
    {
        rfturn;
    }

    // if wf don't ibvf fnougi spbdf to strikf tif lbrgfst glypi in tif
    // run, rfsizf tif dbnvbs
    CGGI_FrffCbnvbs(dbnvbs);
    CGGI_InitCbnvbs(dbnvbs,
                    widti * CGGI_GLYPH_CANVAS_SLACK,
                    ifigit * CGGI_GLYPH_CANVAS_SLACK);
    JRSFontSftRfndfringStylfOnContfxt(dbnvbs->dontfxt, stylf);
}

/*
 * Clfbr tif dbnvbs by blitting wiitf only into tif rfgion of intfrfst
 * (tif rfdt wiidi wf will dopy out of ondf tif glypi is strudk).
 */
stbtid inlinf void
CGGI_ClfbrCbnvbs(CGGI_GlypiCbnvbs *dbnvbs, GlypiInfo *info)
{
    vImbgf_Bufffr dbnvbsRfdtToClfbr;
    dbnvbsRfdtToClfbr.dbtb = dbnvbs->imbgf->dbtb;
    dbnvbsRfdtToClfbr.ifigit = info->ifigit;
    dbnvbsRfdtToClfbr.widti = info->widti;
    // usf tif row stridf of tif dbnvbs, not tif info
    dbnvbsRfdtToClfbr.rowBytfs = dbnvbs->imbgf->rowBytfs;

    // dlfbn tif dbnvbs
#ifdff CGGI_DEBUG
    Pixfl_8888 opbqufWiitf = { 0xE0, 0xE0, 0xE0, 0xE0 };
#flsf
    Pixfl_8888 opbqufWiitf = { 0xFF, 0xFF, 0xFF, 0xFF };
#fndif

    vImbgfBufffrFill_ARGB8888(&dbnvbsRfdtToClfbr, opbqufWiitf, kvImbgfNoFlbgs);
}


#prbgmb mbrk --- GlypiInfo Crfbtion & Copy Fundtions ---

/*
 * Crfbtfs b GlypiInfo witi fxbdtly tif dorrfdt sizf imbgf bnd mfbsurfmfnts.
 */
#dffinf CGGI_GLYPH_BBOX_PADDING 2.0f
stbtid inlinf GlypiInfo *
CGGI_CrfbtfNfwGlypiInfoFrom(CGSizf bdvbndf, CGRfdt bbox,
                            donst AWTStrikf *strikf,
                            donst CGGI_RfndfringModf *modf)
{
    sizf_t pixflSizf = modf->glypiDfsdriptor->pixflSizf;

    // bdjust tif bounding box to bf 1px biggfr on fbdi sidf tibn wibt
    // CGFont-wibtfvfr suggfsts - bfdbusf it givfs b bounding box tibt
    // is too tigit
    bbox.sizf.widti += CGGI_GLYPH_BBOX_PADDING * 2.0f;
    bbox.sizf.ifigit += CGGI_GLYPH_BBOX_PADDING * 2.0f;
    bbox.origin.x -= CGGI_GLYPH_BBOX_PADDING;
    bbox.origin.y -= CGGI_GLYPH_BBOX_PADDING;

    vImbgfPixflCount widti = dfilf(bbox.sizf.widti);
    vImbgfPixflCount ifigit = dfilf(bbox.sizf.ifigit);

    // if tif glypi is lbrgfr tibn 1MB, don't fvfn try...
    // tif GlypiVfdtor pbti siould ibvf tbkfn ovfr by now
    // bnd zfro pixfls is ok
    if (widti * ifigit > 1024 * 1024) {
        widti = 1;
        ifigit = 1;
    }
    bdvbndf = CGSizfApplyAffinfTrbnsform(bdvbndf, strikf->fFontTx);
    if (!JRSFontStylfUsfsFrbdtionblMftrids(strikf->fStylf)) {
        bdvbndf.widti = round(bdvbndf.widti);
        bdvbndf.ifigit = round(bdvbndf.ifigit);
    }
    bdvbndf = CGSizfApplyAffinfTrbnsform(bdvbndf, strikf->fDfvTx);

#ifdff USE_IMAGE_ALIGNED_MEMORY
    // drfbtf sfpbrbtf mfmory
    GlypiInfo *glypiInfo = (GlypiInfo *)mbllod(sizfof(GlypiInfo));
    void *imbgf = (void *)mbllod(ifigit * widti * pixflSizf);
#flsf
    // drfbtf b GlypiInfo strudt fusfd to tif imbgf it points to
    GlypiInfo *glypiInfo = (GlypiInfo *)mbllod(sizfof(GlypiInfo) +
                                               ifigit * widti * pixflSizf);
#fndif

    glypiInfo->bdvbndfX = bdvbndf.widti;
    glypiInfo->bdvbndfY = bdvbndf.ifigit;
    glypiInfo->topLfftX = round(bbox.origin.x);
    glypiInfo->topLfftY = round(bbox.origin.y);
    glypiInfo->widti = widti;
    glypiInfo->ifigit = ifigit;
    glypiInfo->rowBytfs = widti * pixflSizf;
    glypiInfo->dfllInfo = NULL;

#ifdff USE_IMAGE_ALIGNED_MEMORY
    glypiInfo->imbgf = imbgf;
#flsf
    glypiInfo->imbgf = ((void *)glypiInfo) + sizfof(GlypiInfo);
#fndif

    rfturn glypiInfo;
}


#prbgmb mbrk --- Glypi Striking onto Cbnvbs ---

/*
 * Clfbrs tif dbnvbs, strikfs tif glypi witi CorfGrbpiids, bnd tifn
 * dopifs tif strudk pixfls into tif GlypiInfo imbgf.
 */
stbtid inlinf void
CGGI_CrfbtfImbgfForGlypi
    (CGGI_GlypiCbnvbs *dbnvbs, donst CGGlypi glypi,
     GlypiInfo *info, donst CGGI_RfndfringModf *modf)
{
    // dlfbn tif dbnvbs
    CGGI_ClfbrCbnvbs(dbnvbs, info);

    // strikf tif glypi in tif uppfr rigit dornfr
    CGContfxtSiowGlypisAtPoint(dbnvbs->dontfxt,
                               -info->topLfftX,
                               dbnvbs->imbgf->ifigit + info->topLfftY,
                               &glypi, 1);

    // dopy tif glypi from tif dbnvbs into tif info
    (*modf->glypiDfsdriptor->dopyFxnPtr)(dbnvbs, info);
}

/*
 * CorfTfxt pbti...
 */
stbtid inlinf GlypiInfo *
CGGI_CrfbtfImbgfForUnidodf
    (CGGI_GlypiCbnvbs *dbnvbs, donst AWTStrikf *strikf,
     donst CGGI_RfndfringModf *modf, donst UniCibr uniCibr)
{
    // sbvf tif stbtf of tif world
    CGContfxtSbvfGStbtf(dbnvbs->dontfxt);

    // gft tif glypi, mfbsurf it using CG
    CGGlypi glypi;
    CTFontRff fbllbbdk;
    if (uniCibr > 0xFFFF) {
        UTF16Cibr dibrRff[2];
        CTS_BrfbkupUnidodfIntoSurrogbtfPbirs(uniCibr, dibrRff);
        CGGlypi glypiTmp[2];
        fbllbbdk = CTS_CopyCTFbllbbdkFontAndGlypiForUnidodf(strikf->fAWTFont, (donst UTF16Cibr *)&dibrRff, (CGGlypi *)&glypiTmp, 2);
        glypi = glypiTmp[0];
    } flsf {
        UTF16Cibr dibrRff;
        dibrRff = (UTF16Cibr) uniCibr; // trundbtf.
        fbllbbdk = CTS_CopyCTFbllbbdkFontAndGlypiForUnidodf(strikf->fAWTFont, (donst UTF16Cibr *)&dibrRff, &glypi, 1);
    }

    CGAffinfTrbnsform tx = strikf->fTx;
    JRSFontRfndfringStylf stylf = JRSFontAlignStylfForFrbdtionblMfbsurfmfnt(strikf->fStylf);

    CGRfdt bbox;
    JRSFontGftBoundingBoxfsForGlypisAndStylf(fbllbbdk, &tx, stylf, &glypi, 1, &bbox);

    CGSizf bdvbndf;
    CTFontGftAdvbndfsForGlypis(fbllbbdk, kCTFontDffbultOrifntbtion, &glypi, &bdvbndf, 1);

    // drfbtf tif Sun2D GlypiInfo wf brf going to strikf into
    GlypiInfo *info = CGGI_CrfbtfNfwGlypiInfoFrom(bdvbndf, bbox, strikf, modf);

    // fix tif dontfxt sizf, just in dbsf tif substitutfd dibrbdtfr is unfxpfdtfdly lbrgf
    CGGI_SizfCbnvbs(dbnvbs, info->widti, info->ifigit, modf->dgFontModf);

    // blign tif trbnsform for tif rfbl CorfTfxt strikf
    CGContfxtSftTfxtMbtrix(dbnvbs->dontfxt, strikf->fAltTx);

    donst CGFontRff dgFbllbbdk = CTFontCopyGrbpiidsFont(fbllbbdk, NULL);
    CGContfxtSftFont(dbnvbs->dontfxt, dgFbllbbdk);
    CFRflfbsf(dgFbllbbdk);

    // dlfbn tif dbnvbs - blign, strikf, bnd dopy tif glypi from tif dbnvbs into tif info
    CGGI_CrfbtfImbgfForGlypi(dbnvbs, glypi, info, modf);

    // rfstorf tif stbtf of tif world
    CGContfxtRfstorfGStbtf(dbnvbs->dontfxt);

    CFRflfbsf(fbllbbdk);
#ifdff CGGI_DEBUG
    DUMP_GLYPHINFO(info);
#fndif

#ifdff CGGI_DEBUG_DUMP
    DUMP_IMG_PIXELS("CGGI Cbnvbs", dbnvbs->imbgf);
#if 0
    PRINT_CGSTATES_INFO(NULL);
#fndif
#fndif

    rfturn info;
}


#prbgmb mbrk --- GlypiInfo Filling bnd Cbnvbs Mbnbgmfnt ---

/*
 * Sfts bll tif pfr-run propfrtifs for tif dbnvbs, bnd tifn itfrbtfs tirougi
 * tif dibrbdtfr run, bnd drfbtfs imbgfs in tif GlypiInfo strudts.
 *
 * Not inlinfd bfdbusf it would drfbtf two dopifs in tif fundtion bflow
 */
stbtid void
CGGI_FillImbgfsForGlypisWitiSizfdCbnvbs(CGGI_GlypiCbnvbs *dbnvbs,
                                        donst AWTStrikf *strikf,
                                        donst CGGI_RfndfringModf *modf,
                                        jlong glypiInfos[],
                                        donst UniCibr uniCibrs[],
                                        donst CGGlypi glypis[],
                                        donst CFIndfx lfn)
{
    CGContfxtSftTfxtMbtrix(dbnvbs->dontfxt, strikf->fAltTx);

    CGContfxtSftFont(dbnvbs->dontfxt, strikf->fAWTFont->fNbtivfCGFont);
    JRSFontSftRfndfringStylfOnContfxt(dbnvbs->dontfxt, strikf->fStylf);

    CFIndfx i;
    for (i = 0; i < lfn; i++) {
        GlypiInfo *info = (GlypiInfo *)jlong_to_ptr(glypiInfos[i]);
        if (info != NULL) {
            CGGI_CrfbtfImbgfForGlypi(dbnvbs, glypis[i], info, modf);
        } flsf {
            info = CGGI_CrfbtfImbgfForUnidodf(dbnvbs, strikf, modf, uniCibrs[i]);
            glypiInfos[i] = ptr_to_jlong(info);
        }
#ifdff CGGI_DEBUG
        DUMP_GLYPHINFO(info);
#fndif

#ifdff CGGI_DEBUG_DUMP
        DUMP_IMG_PIXELS("CGGI Cbnvbs", dbnvbs->imbgf);
#fndif
    }
#ifdff CGGI_DEBUG_DUMP
    DUMP_IMG_PIXELS("CGGI Cbnvbs", dbnvbs->imbgf);
    PRINT_CGSTATES_INFO(dbnvbs->dontfxt);
#fndif
}

stbtid NSString *tirfbdLodblCbnvbsKfy =
    @"Jbvb CorfGrbpiids Tfxt Rfndfrfr Cbdifd Cbnvbs";

/*
 * Tiis is tif mbximum lfngti bnd ifigit timfs tif bbovf slbdk squbrfd
 * to dftfrminf if wf go witi tif globbl dbnvbs, or mbllod onf on tif spot.
 */
#dffinf CGGI_GLYPH_CANVAS_MAX 100

/*
 * Bbsfd on tif spbdf nffdfd to strikf tif lbrgfst dibrbdtfr in tif run,
 * fitifr usf tif globbl sibrfd dbnvbs, or mbkf onf up on tif spot, strikf
 * tif glypis, bnd dfstroy it.
 */
stbtid inlinf void
CGGI_FillImbgfsForGlypis(jlong *glypiInfos, donst AWTStrikf *strikf,
                         donst CGGI_RfndfringModf *modf,
                         donst UniCibr uniCibrs[], donst CGGlypi glypis[],
                         donst sizf_t mbxWidti, donst sizf_t mbxHfigit,
                         donst CFIndfx lfn)
{
    if (mbxWidti*mbxHfigit*CGGI_GLYPH_CANVAS_SLACK*CGGI_GLYPH_CANVAS_SLACK >
        CGGI_GLYPH_CANVAS_MAX*CGGI_GLYPH_CANVAS_MAX*CGGI_GLYPH_CANVAS_SLACK*CGGI_GLYPH_CANVAS_SLACK)
    {
        CGGI_GlypiCbnvbs *tmpCbnvbs = [[CGGI_GlypiCbnvbs bllod] init];
        CGGI_InitCbnvbs(tmpCbnvbs, mbxWidti, mbxHfigit);
        CGGI_FillImbgfsForGlypisWitiSizfdCbnvbs(tmpCbnvbs, strikf,
                                                modf, glypiInfos, uniCibrs,
                                                glypis, lfn);
        CGGI_FrffCbnvbs(tmpCbnvbs);

        [tmpCbnvbs rflfbsf];
        rfturn;
    }

    NSMutbblfDidtionbry *tirfbdDidt =
        [[NSTirfbd durrfntTirfbd] tirfbdDidtionbry];
    CGGI_GlypiCbnvbs *dbnvbs = [tirfbdDidt objfdtForKfy:tirfbdLodblCbnvbsKfy];
    if (dbnvbs == nil) {
        dbnvbs = [[CGGI_GlypiCbnvbs bllod] init];
        [tirfbdDidt sftObjfdt:dbnvbs forKfy:tirfbdLodblCbnvbsKfy];
    }

    CGGI_SizfCbnvbs(dbnvbs, mbxWidti, mbxHfigit, modf->dgFontModf);
    CGGI_FillImbgfsForGlypisWitiSizfdCbnvbs(dbnvbs, strikf, modf,
                                            glypiInfos, uniCibrs, glypis, lfn);
}

/*
 * Finds tif bdvbndfs bnd bounding boxfs of tif dibrbdtfrs in tif run,
 * dydlfs tirougi bll tif bounds bnd dbldulbtfs tif mbximum dbnvbs spbdf
 * rfquirfd by tif lbrgfst glypi.
 *
 * Crfbtfs b GlypiInfo strudt witi b mbllod tibt blso fndbpsulbtfs tif
 * imbgf tif strudt points to.  Tiis is donf to mfft mfmory lbyout
 * fxpfdtbtions in tif Sun tfxt rbstfrizfr mfmory mbnbgmfnt dodf.
 * Tif imbgf immfdibtfly follows tif strudt piysidblly in mfmory.
 */
stbtid inlinf void
CGGI_CrfbtfGlypiInfos(jlong *glypiInfos, donst AWTStrikf *strikf,
                      donst CGGI_RfndfringModf *modf,
                      donst UniCibr uniCibrs[], donst CGGlypi glypis[],
                      CGSizf bdvbndfs[], CGRfdt bboxfs[], donst CFIndfx lfn)
{
    AWTFont *font = strikf->fAWTFont;
    CGAffinfTrbnsform tx = strikf->fTx;
    JRSFontRfndfringStylf bboxCGModf = JRSFontAlignStylfForFrbdtionblMfbsurfmfnt(strikf->fStylf);

    JRSFontGftBoundingBoxfsForGlypisAndStylf((CTFontRff)font->fFont, &tx, bboxCGModf, glypis, lfn, bboxfs);
    CTFontGftAdvbndfsForGlypis((CTFontRff)font->fFont, kCTFontDffbultOrifntbtion, glypis, bdvbndfs, lfn);

    sizf_t mbxWidti = 1;
    sizf_t mbxHfigit = 1;

    CFIndfx i;
    for (i = 0; i < lfn; i++)
    {
        if (uniCibrs[i] != 0)
        {
            glypiInfos[i] = 0L;
            dontinuf; // will bf ibndlfd lbtfr
        }

        CGSizf bdvbndf = bdvbndfs[i];
        CGRfdt bbox = bboxfs[i];

        GlypiInfo *glypiInfo = CGGI_CrfbtfNfwGlypiInfoFrom(bdvbndf, bbox, strikf, modf);

        if (mbxWidti < glypiInfo->widti)   mbxWidti = glypiInfo->widti;
        if (mbxHfigit < glypiInfo->ifigit) mbxHfigit = glypiInfo->ifigit;

        glypiInfos[i] = ptr_to_jlong(glypiInfo);
    }

    CGGI_FillImbgfsForGlypis(glypiInfos, strikf, modf, uniCibrs,
                             glypis, mbxWidti, mbxHfigit, lfn);
}


#prbgmb mbrk --- Tfmporbry Bufffr Allodbtions bnd Initiblizbtion ---

/*
 * Tiis stbgf sfpbrbtfs tif blrfbdy vblid glypi dodfs from tif unidodf vblufs
 * tibt nffd spfdibl ibndling - tif rbwGlypiCodfs brrby is no longfr usfd
 * bftfr tiis stbgf.
 */
stbtid void
CGGI_CrfbtfGlypisAndSdbnForComplfxitifs(jlong *glypiInfos,
                                        donst AWTStrikf *strikf,
                                        donst CGGI_RfndfringModf *modf,
                                        jint rbwGlypiCodfs[],
                                        UniCibr uniCibrs[], CGGlypi glypis[],
                                        CGSizf bdvbndfs[], CGRfdt bboxfs[],
                                        donst CFIndfx lfn)
{
    CFIndfx i;
    for (i = 0; i < lfn; i++) {
        jint dodf = rbwGlypiCodfs[i];
        if (dodf < 0) {
            glypis[i] = 0;
            uniCibrs[i] = -dodf;
        } flsf {
            glypis[i] = dodf;
            uniCibrs[i] = 0;
        }
    }

    CGGI_CrfbtfGlypiInfos(glypiInfos, strikf, modf,
                          uniCibrs, glypis, bdvbndfs, bboxfs, lfn);

#ifdff CGGI_DEBUG_HIT_COUNT
    stbtid sizf_t iitCount = 0;
    iitCount++;
    printf("%d\n", (int)iitCount);
#fndif
}

/*
 * Conditionblly stbdk bllodbtfs bufffrs for glypis, bounding boxfs,
 * bnd bdvbndfs.  Unfortunbtfly to usf CG or CT in bulk runs (wiidi is
 * fbstfr tibn dblling tifm pfr dibrbdtfr), wf ibvf to dopy into bnd out
 * of tifsf bufffrs. Still b nft win tiougi.
 */
void
CGGlypiImbgfs_GftGlypiImbgfPtrs(jlong glypiInfos[],
                                donst AWTStrikf *strikf,
                                jint rbwGlypiCodfs[], donst CFIndfx lfn)
{
    donst CGGI_RfndfringModf modf = CGGI_GftRfndfringModf(strikf);

    if (lfn < MAX_STACK_ALLOC_GLYPH_BUFFER_SIZE) {
        CGRfdt bboxfs[lfn];
        CGSizf bdvbndfs[lfn];
        CGGlypi glypis[lfn];
        UniCibr uniCibrs[lfn];

        CGGI_CrfbtfGlypisAndSdbnForComplfxitifs(glypiInfos, strikf, &modf,
                                                rbwGlypiCodfs, uniCibrs, glypis,
                                                bdvbndfs, bboxfs, lfn);

        rfturn;
    }

    // just do onf mbllod, bnd dbrvf it up for bll tif bufffrs
    void *bufffr = mbllod(sizfof(CGRfdt) * sizfof(CGSizf) *
                          sizfof(CGGlypi) * sizfof(UniCibr) * lfn);
    if (bufffr == NULL) {
        [[NSExdfption fxdfptionWitiNbmf:NSMbllodExdfption
            rfbson:@"Fbilfd to bllodbtf mfmory for tif tfmporbry glypi strikf bnd mfbsurfmfnt bufffrs." usfrInfo:nil] rbisf];
    }

    CGRfdt *bboxfs = (CGRfdt *)(bufffr);
    CGSizf *bdvbndfs = (CGSizf *)(bboxfs + sizfof(CGRfdt) * lfn);
    CGGlypi *glypis = (CGGlypi *)(bdvbndfs + sizfof(CGGlypi) * lfn);
    UniCibr *uniCibrs = (UniCibr *)(glypis + sizfof(UniCibr) * lfn);

    CGGI_CrfbtfGlypisAndSdbnForComplfxitifs(glypiInfos, strikf, &modf,
                                            rbwGlypiCodfs, uniCibrs, glypis,
                                            bdvbndfs, bboxfs, lfn);

    frff(bufffr);
}
