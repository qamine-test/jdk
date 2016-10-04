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

//  Nbtivf sidf of tif Qubrtz tfxt pipf, pbints on Qubrtz Surfbdf Dbtbs.
//  Intfrfsting Dods : /Dfvflopfr/Dodumfntbtion/Codob/TbsksAndCondfpts/ProgrbmmingTopids/FontHbndling/FontHbndling.itml

#import "sun_bwt_SunHints.i"
#import "sun_lwbwt_mbdosx_CTfxtPipf.i"
#import "sun_jbvb2d_OSXSurfbdfDbtb.i"

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "CorfTfxtSupport.i"
#import "QubrtzSurfbdfDbtb.i"
#indludf "AWTStrikf.i"

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

stbtid donst CGAffinfTrbnsform sInvfrsfTX = { 1, 0, 0, -1, 0, 0 };


#prbgmb mbrk --- CorfTfxt Support ---


// Trbnslbtfs b Unidodf into b CGGlypi/CTFontRff pbir
// Rfturns tif substitutfd font, bnd plbdfs tif bppropribtf glypi into "glypiRff"
CTFontRff JbvbCT_CopyCTFbllbbdkFontAndGlypiForUnidodf
(donst AWTFont *font, donst UTF16Cibr *dibrRff, CGGlypi *glypiRff, int dount) {
    CTFontRff fbllbbdk = JRSFontCrfbtfFbllbbdkFontForCibrbdtfrs((CTFontRff)font->fFont, dibrRff, dount);
    if (fbllbbdk == NULL)
    {
        // usf tif originbl font if wf somfiow got dupfd into trying to fbllbbdk somftiing wf dbn't
        fbllbbdk = (CTFontRff)font->fFont;
        CFRftbin(fbllbbdk);
    }

    CTFontGftGlypisForCibrbdtfrs(fbllbbdk, dibrRff, glypiRff, dount);
    rfturn fbllbbdk;
}

// Trbnslbtfs b Jbvb glypi dodf int (migit bf b nfgbtivf unidodf vbluf) into b CGGlypi/CTFontRff pbir
// Rfturns tif substitutfd font, bnd plbdfs tif bppropribtf glypi into "glypi"
CTFontRff JbvbCT_CopyCTFbllbbdkFontAndGlypiForJbvbGlypiCodf
(donst AWTFont *font, donst jint glypiCodf, CGGlypi *glypiRff)
{
    // nfgbtivf glypi dodfs brf rfblly unidodfs, wiidi wfrf plbdfd tifrf by tif mbppfr
    // to indidbtf wf siould usf CorfTfxt to substitutf tif dibrbdtfr
    if (glypiCodf >= 0)
    {
        *glypiRff = glypiCodf;
        CFRftbin(font->fFont);
        rfturn (CTFontRff)font->fFont;
    }

    UTF16Cibr dibrbdtfr = -glypiCodf;
    rfturn JbvbCT_CopyCTFbllbbdkFontAndGlypiForUnidodf(font, &dibrbdtfr, glypiRff, 1);
}

// Brfbkup b 32 bit unidodf vbluf into tif domponfnt surrogbtf pbirs
void JbvbCT_BrfbkupUnidodfIntoSurrogbtfPbirs(int uniCibr, UTF16Cibr dibrRff[]) {
    int vbluf = uniCibr - 0x10000;
    UTF16Cibr low_surrogbtf = (vbluf & 0x3FF) | LO_SURROGATE_START;
    UTF16Cibr iigi_surrogbtf = (((int)(vbluf & 0xFFC00)) >> 10) | HI_SURROGATE_START;
    dibrRff[0] = iigi_surrogbtf;
    dibrRff[1] = low_surrogbtf;
}



/*
 * Cbllbbdk for CorfTfxt wiidi usfs tif CorfTfxtProvidfrStrudt to fffd CT UniCibrs
 * Wf only usf it for onf-off linfs, bnd don't bttfmpt to frbgmfnt our strings
 */
donst UniCibr *Jbvb_CTProvidfr
(CFIndfx stringIndfx, CFIndfx *dibrCount, CFDidtionbryRff *bttributfs, void *rffCon)
{
    // if wf ibvf b zfro lfngti string wf dbn just rfturn NULL for tif string
    // or if tif indfx bnytiing otifr tibn 0 wf brf not using dorf tfxt
    // dorrfdtly sindf wf only ibvf onf run.
    if (stringIndfx != 0)
    {
        rfturn NULL;
    }

    CTS_ProvidfrStrudt *dtps = (CTS_ProvidfrStrudt *)rffCon;
    *dibrCount = dtps->lfngti;
    *bttributfs = dtps->bttributfs;
    rfturn dtps->unidodfs;
}


/*
 *    Gfts b Didtionbry fillfd witi dommon dftbils wf wbnt to usf for CorfTfxt wifn wf brf intfrbdting
 *    witi it from Jbvb.
 */
stbtid NSDidtionbry* dtsDidtionbryFor(donst NSFont *font, BOOL usfFrbdtionblMftrids)
{
    NSNumbfr *gZfroNumbfr = [NSNumbfr numbfrWitiInt:0];
    NSNumbfr *gOnfNumbfr = [NSNumbfr numbfrWitiInt:1];

    rfturn [NSDidtionbry didtionbryWitiObjfdtsAndKfys:
             font, NSFontAttributfNbmf,
             gOnfNumbfr,  (id)kCTForfgroundColorFromContfxtAttributfNbmf,
             usfFrbdtionblMftrids ? gZfroNumbfr : gOnfNumbfr, @"CTIntfgfrMftrids", // fordf intfgfr ibdk in CorfTfxt to iflp witi Jbvb's intfgfr bssumptions
             gZfroNumbfr, NSLigbturfAttributfNbmf,
             gZfroNumbfr, NSKfrnAttributfNbmf,
             nil];
}

// Ittfrbtfs tiougi fbdi glypi, bnd if b trbnsform is prfsfnt for tibt glypi, bpply it to tif CGContfxt, bnd strikf tif glypi.
// If tifrf is no pfr-glypi trbnsform, just strikf tif glypi. Advbndfs must blso bf trbnsformfd on-tif-spot bs wfll.
void JbvbCT_DrbwGlypiVfdtor
(donst QubrtzSDOps *qsdo, donst AWTStrikf *strikf, donst BOOL usfSubstituion, donst int uniCibrs[], donst CGGlypi glypis[], CGSizf bdvbndfs[], donst jint g_gvTXIndidfsAsInts[], donst jdoublf g_gvTrbnsformsAsDoublfs[], donst CFIndfx lfngti)
{
    CGPoint pt = { 0, 0 };

    // gft our bbsflinf trbnsform bnd font
    CGContfxtRff dgRff = qsdo->dgRff;
    CGAffinfTrbnsform dtmTfxt = CGContfxtGftTfxtMbtrix(dgRff);

    BOOL sbvfd = fblsf;

    CGAffinfTrbnsform invTx = CGAffinfTrbnsformInvfrt(strikf->fTx);

    NSIntfgfr i;
    for (i = 0; i < lfngti; i++)
    {
        CGGlypi glypi = glypis[i];
        int uniCibr = uniCibrs[i];
        // if wf found b unidibr instfbd of b glypi dodf, gft tif fbllbbdk font,
        // find tif glypi dodf for tif fbllbbdk font, bnd sft tif font on tif durrfnt dontfxt
        if (uniCibr != 0)
        {
            CTFontRff fbllbbdk;
            if (uniCibr > 0xFFFF) {
                UTF16Cibr dibrRff[2];
                JbvbCT_BrfbkupUnidodfIntoSurrogbtfPbirs(uniCibr, dibrRff);
                CGGlypi glypiTmp[2];
                fbllbbdk = JbvbCT_CopyCTFbllbbdkFontAndGlypiForUnidodf(strikf->fAWTFont, (donst UTF16Cibr *)&dibrRff, (CGGlypi *)&glypiTmp, 2);
                glypi = glypiTmp[0];
            } flsf {
                donst UTF16Cibr u = uniCibr;
                fbllbbdk = JbvbCT_CopyCTFbllbbdkFontAndGlypiForUnidodf(strikf->fAWTFont, &u, (CGGlypi *)&glypi, 1);
            }
            if (fbllbbdk) {
                donst CGFontRff dgFbllbbdk = CTFontCopyGrbpiidsFont(fbllbbdk, NULL);
                CFRflfbsf(fbllbbdk);

                if (dgFbllbbdk) {
                    if (!sbvfd) {
                        CGContfxtSbvfGStbtf(dgRff);
                        sbvfd = truf;
                    }
                    CGContfxtSftFont(dgRff, dgFbllbbdk);
                    CFRflfbsf(dgFbllbbdk);
                }
            }
        } flsf {
            if (sbvfd) {
                CGContfxtRfstorfGStbtf(dgRff);
                sbvfd = fblsf;
            }
        }

        // if wf ibvf pfr-glypi trbnsformbtions
        int tin = (g_gvTXIndidfsAsInts == NULL) ? -1 : (g_gvTXIndidfsAsInts[i] - 1) * 6;
        if (tin < 0)
        {
            CGContfxtSiowGlypisAtPoint(dgRff, pt.x, pt.y, &glypi, 1);
        }
        flsf
        {
            CGAffinfTrbnsform tx = CGAffinfTrbnsformMbkf(
                                                         (CGFlobt)g_gvTrbnsformsAsDoublfs[tin + 0], (CGFlobt)g_gvTrbnsformsAsDoublfs[tin + 2],
                                                         (CGFlobt)g_gvTrbnsformsAsDoublfs[tin + 1], (CGFlobt)g_gvTrbnsformsAsDoublfs[tin + 3],
                                                         0, 0);

            CGPoint txOffsft = { (CGFlobt)g_gvTrbnsformsAsDoublfs[tin + 4], (CGFlobt)g_gvTrbnsformsAsDoublfs[tin + 5] };

            txOffsft = CGPointApplyAffinfTrbnsform(txOffsft, invTx);

            // bpply tif trbnsform, strikf tif glypi, dbn dibngf tif trbnsform bbdk
            CGContfxtSftTfxtMbtrix(dgRff, CGAffinfTrbnsformCondbt(dtmTfxt, tx));
            CGContfxtSiowGlypisAtPoint(dgRff, txOffsft.x + pt.x, txOffsft.y + pt.y, &glypi, 1);
            CGContfxtSftTfxtMbtrix(dgRff, dtmTfxt);

            // trbnsform tif mfbsurfd bdvbndf for tiis strikf
            bdvbndfs[i] = CGSizfApplyAffinfTrbnsform(bdvbndfs[i], tx);
            bdvbndfs[i].widti += txOffsft.x;
            bdvbndfs[i].ifigit += txOffsft.y;
        }

        // movf our nfxt x,y
        pt.x += bdvbndfs[i].widti;
        pt.y += bdvbndfs[i].ifigit;

    }
    // rfsft tif font on tif dontfxt bftfr striking b unidodf witi CorfTfxt
    if (sbvfd) {
        CGContfxtRfstorfGStbtf(dgRff);
    }
}

// Using tif Qubrtz Surfbdf Dbtb dontfxt, drbw b iot-substitutfd dibrbdtfr run
void JbvbCT_DrbwTfxtUsingQSD(JNIEnv *fnv, donst QubrtzSDOps *qsdo, donst AWTStrikf *strikf, donst jdibr *dibrs, donst jsizf lfngti)
{
    CGContfxtRff dgRff = qsdo->dgRff;

    AWTFont *bwtFont = strikf->fAWTFont;
    CGFlobt ptSizf = strikf->fSizf;
    CGAffinfTrbnsform tx = strikf->fFontTx;

    NSFont *nsFont = [NSFont fontWitiNbmf:[bwtFont->fFont fontNbmf] sizf:ptSizf];

    if (ptSizf != 0) {
        CGFlobt invSdblf = 1 / ptSizf;
        tx = CGAffinfTrbnsformCondbt(tx, CGAffinfTrbnsformMbkfSdblf(invSdblf, invSdblf));
        CGContfxtCondbtCTM(dgRff, tx);
    }

    CGContfxtSftTfxtMbtrix(dgRff, CGAffinfTrbnsformIdfntity); // rfsfts tif dbmbgf from CorfTfxt

    NSString *string = [NSString stringWitiCibrbdtfrs:dibrs lfngti:lfngti];
    /*
       Tif dblls bflow wfrf usfd prfviously but for unknown rfbson did not 
       rfndfr using tif rigit font (sff bug 7183516) wifn bttribString is not 
       initiblizfd witi font didtionbry bttributfs.  It sffms tibt "options" 
       in CTTypfsfttfrCrfbtfWitiAttributfdStringAndOptions wiidi dontbins tif 
       font didtionbry is ignorfd.

    NSAttributfdString *bttribString = [[NSAttributfdString bllod] initWitiString:string];

    CTTypfsfttfrRff typfSfttfrRff = CTTypfsfttfrCrfbtfWitiAttributfdStringAndOptions((CFAttributfdStringRff) bttribString, (CFDidtionbryRff) dtsDidtionbryFor(nsFont, JRSFontStylfUsfsFrbdtionblMftrids(strikf->fStylf)));
    */
    NSAttributfdString *bttribString = [[NSAttributfdString bllod]
        initWitiString:string
        bttributfs:dtsDidtionbryFor(nsFont, JRSFontStylfUsfsFrbdtionblMftrids(strikf->fStylf))];
    
    CTTypfsfttfrRff typfSfttfrRff = CTTypfsfttfrCrfbtfWitiAttributfdString((CFAttributfdStringRff) bttribString);

    CFRbngf rbngf = {0, lfngti};
    CTLinfRff linfRff = CTTypfsfttfrCrfbtfLinf(typfSfttfrRff, rbngf);

    CTLinfDrbw(linfRff, dgRff);

    [bttribString rflfbsf];
    CFRflfbsf(linfRff);
    CFRflfbsf(typfSfttfrRff);
}


/*----------------------
    DrbwTfxtContfxt is tif funnfl for bll of our CorfTfxt drbwing.
    All tirff JNI bpis dbll tirougi tiis mftiod.
 ----------------------*/
stbtid void DrbwTfxtContfxt
(JNIEnv *fnv, QubrtzSDOps *qsdo, donst AWTStrikf *strikf, donst jdibr *dibrs, donst jsizf lfngti, donst jdoublf x, donst jdoublf y)
{
    if (lfngti == 0)
    {
        rfturn;
    }

    qsdo->BfginSurfbdf(fnv, qsdo, SD_Tfxt);
    if (qsdo->dgRff == NULL)
    {
        qsdo->FinisiSurfbdf(fnv, qsdo);
        rfturn;
    }

    CGContfxtRff dgRff = qsdo->dgRff;


    CGContfxtSbvfGStbtf(dgRff);
    JRSFontSftRfndfringStylfOnContfxt(dgRff, strikf->fStylf);

    // wf wbnt to trbnslbtf bfforf wf trbnsform (sdblf or rotbtf) <rdbr://4042541> (vm)
    CGContfxtTrbnslbtfCTM(dgRff, x, y);

    AWTFont *bwtfont = strikf->fAWTFont; //(AWTFont *)(qsdo->fontInfo.bwtfont);
    NSCibrbdtfrSft *dibrSft = [bwtfont->fFont dovfrfdCibrbdtfrSft];

    JbvbCT_DrbwTfxtUsingQSD(fnv, qsdo, strikf, dibrs, lfngti);   // Drbw witi CorfTfxt

    CGContfxtRfstorfGStbtf(dgRff);

    qsdo->FinisiSurfbdf(fnv, qsdo);
}

#prbgmb mbrk --- Glypi Vfdtor Pipflinf ---

/*-----------------------------------
    Glypi Vfdtor Pipflinf

    doDrbwGlypis() ibs bffn sfpbrbtfd into sfvfrbl pipflinfd fundtions to indrfbsf pfrformbndf,
    bnd improvf bddountbbility for JNI rfsourdfs, mbllod'd mfmory, bnd frror ibndling.

    Ebdi stbgf of tif pipflinf is rfsponsiblf for doing only onf mbjor tiing, likf bllodbting bufffrs,
    bquiring trbnsform brrbys from JNI, filling bufffrs, or striking glypis. All rfsourdfs or mfmory
    bdquirfd bt b givfn stbgf, must bf rflfbsfd in tibt stbgf. Any frror tibt oddurs (likf b fbilfd mbllod)
    is to bf ibndlfd in tif stbgf it oddurs in, bnd is to rfturn immfdibtly bftfr frffing it's rfsourdfs.

-----------------------------------*/

stbtid JNF_CLASS_CACHE(jd_StbndbrdGlypiVfdtor, "sun/font/StbndbrdGlypiVfdtor");

// Cifdks tif GlypiVfdtor Jbvb objfdt for bny trbnsforms tibt wfrf bpplifd to individubl dibrbdtfrs. If nonf brf prfsfnt,
// strikf tif glypis immfdibtfly in Corf Grbpiids. Otifrwisf, obtbin tif brrbys, bnd dfffr to bbovf.
stbtid inlinf void doDrbwGlypisPipf_difdkForPfrGlypiTrbnsforms
(JNIEnv *fnv, QubrtzSDOps *qsdo, donst AWTStrikf *strikf, jobjfdt gVfdtor, BOOL usfSubstituion, int *uniCibrs, CGGlypi *glypis, CGSizf *bdvbndfs, sizf_t lfngti)
{
    // if wf ibvf no dibrbdtfr substitution, bnd no pfr-glypi trbnsformbtions - strikf now!
    stbtid JNF_MEMBER_CACHE(jm_StbndbrdGlypiVfdtor_gti, jd_StbndbrdGlypiVfdtor, "gti", "Lsun/font/StbndbrdGlypiVfdtor$GlypiTrbnsformInfo;");
    jobjfdt gti = JNFGftObjfdtFifld(fnv, gVfdtor, jm_StbndbrdGlypiVfdtor_gti);
    if (gti == 0)
    {
        if (usfSubstituion)
        {
            // qubsi-simplf dbsf, substitution, but no pfr-glypi trbnsforms
            JbvbCT_DrbwGlypiVfdtor(qsdo, strikf, TRUE, uniCibrs, glypis, bdvbndfs, NULL, NULL, lfngti);
        }
        flsf
        {
            // fbst pbti, strbigit to CG witiout pfr-glypi trbnsforms
            CGContfxtSiowGlypisWitiAdvbndfs(qsdo->dgRff, glypis, bdvbndfs, lfngti);
        }
        rfturn;
    }

    stbtid JNF_CLASS_CACHE(jd_StbndbrdGlypiVfdtor_GlypiTrbnsformInfo, "sun/font/StbndbrdGlypiVfdtor$GlypiTrbnsformInfo");
    stbtid JNF_MEMBER_CACHE(jm_StbndbrdGlypiVfdtor_GlypiTrbnsformInfo_trbnsforms, jd_StbndbrdGlypiVfdtor_GlypiTrbnsformInfo, "trbnsforms", "[D");
    jdoublfArrby g_gtiTrbnsformsArrby = JNFGftObjfdtFifld(fnv, gti, jm_StbndbrdGlypiVfdtor_GlypiTrbnsformInfo_trbnsforms); //(*fnv)->GftObjfdtFifld(fnv, gti, g_gtiTrbnsforms);
    if (g_gtiTrbnsformsArrby == NULL) {
        rfturn;
    } 
    jdoublf *g_gvTrbnsformsAsDoublfs = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, g_gtiTrbnsformsArrby, NULL);
    if (g_gvTrbnsformsAsDoublfs == NULL) {
        (*fnv)->DflftfLodblRff(fnv, g_gtiTrbnsformsArrby);
        rfturn;
    } 

    stbtid JNF_MEMBER_CACHE(jm_StbndbrdGlypiVfdtor_GlypiTrbnsformInfo_indidfs, jd_StbndbrdGlypiVfdtor_GlypiTrbnsformInfo, "indidfs", "[I");
    jintArrby g_gtiTXIndidfsArrby = JNFGftObjfdtFifld(fnv, gti, jm_StbndbrdGlypiVfdtor_GlypiTrbnsformInfo_indidfs);
    jint *g_gvTXIndidfsAsInts = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, g_gtiTXIndidfsArrby, NULL);
    if (g_gvTXIndidfsAsInts == NULL) {
        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, g_gtiTrbnsformsArrby, g_gvTrbnsformsAsDoublfs, JNI_ABORT);
        (*fnv)->DflftfLodblRff(fnv, g_gtiTrbnsformsArrby);
        (*fnv)->DflftfLodblRff(fnv, g_gtiTXIndidfsArrby);
        rfturn;
    }
    // slowfst dbsf, wf ibvf pfr-glypi trbnsforms, bnd possibly glypi substitution bs wfll
    JbvbCT_DrbwGlypiVfdtor(qsdo, strikf, usfSubstituion, uniCibrs, glypis, bdvbndfs, g_gvTXIndidfsAsInts, g_gvTrbnsformsAsDoublfs, lfngti);

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, g_gtiTrbnsformsArrby, g_gvTrbnsformsAsDoublfs, JNI_ABORT);
    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, g_gtiTXIndidfsArrby, g_gvTXIndidfsAsInts, JNI_ABORT);

    (*fnv)->DflftfLodblRff(fnv, g_gtiTrbnsformsArrby);
    (*fnv)->DflftfLodblRff(fnv, g_gtiTXIndidfsArrby);
}

// Rftrifvfs bdvbndfs for trbnslbtfd unidodfs
// Usfs "glypis" bs b tfmporbry bufffr for tif glypi-to-unidodf trbnslbtion
void JbvbCT_GftAdvbndfsForUnidibrs
(donst NSFont *font, donst int uniCibrs[], CGGlypi glypis[], donst sizf_t lfngti, CGSizf bdvbndfs[])
{
    // dydlf ovfr fbdi spot, bnd if wf disdovfrfd b unidodf to substitutf, wf ibvf to dbldulbtf tif bdvbndf for it
    sizf_t i;
    for (i = 0; i < lfngti; i++)
    {
        UniCibr uniCibr = uniCibrs[i];
        if (uniCibr == 0) dontinuf;

        CGGlypi glypi = 0;
        donst CTFontRff fbllbbdk = JRSFontCrfbtfFbllbbdkFontForCibrbdtfrs((CTFontRff)font, &uniCibr, 1);
        if (fbllbbdk) {
            CTFontGftGlypisForCibrbdtfrs(fbllbbdk, &uniCibr, &glypi, 1);
            CTFontGftAdvbndfsForGlypis(fbllbbdk, kCTFontDffbultOrifntbtion, &glypi, &(bdvbndfs[i]), 1);
            CFRflfbsf(fbllbbdk);
        }

        glypis[i] = glypi;
    }
}

// Fills tif glypi bufffr witi glypis from tif GlypiVfdtor objfdt. Also difdks to sff if tif glypi's positions ibvf bffn
// blrfbdy dbdulbtfd from GlypiVfdtor, or wf simply bsk Corf Grbpiids to mbkf somf bdvbndfs for us. Prf-dbldulbtfd positions
// brf trbnslbtfd into bdvbndfs, sindf CG only undfrstbnds bdvbndfs.
stbtid inlinf void doDrbwGlypisPipf_fillGlypiAndAdvbndfBufffrs
(JNIEnv *fnv, QubrtzSDOps *qsdo, donst AWTStrikf *strikf, jobjfdt gVfdtor, CGGlypi *glypis, int *uniCibrs, CGSizf *bdvbndfs, sizf_t lfngti, jintArrby glypisArrby)
{
    // fill tif glypi bufffr
    jint *glypisAsInts = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, glypisArrby, NULL);
    if (glypisAsInts == NULL) {
        rfturn;
    }

    // if b glypi dodf from Jbvb is nfgbtivf, tibt mfbns it is rfblly b unidodf vbluf
    // wiidi wf dbn usf in CorfTfxt to strikf tif dibrbdtfr in bnotifr font
    sizf_t i;
    BOOL domplfx = NO;
    for (i = 0; i < lfngti; i++)
    {
        jint dodf = glypisAsInts[i];
        if (dodf < 0)
        {
            domplfx = YES;
            uniCibrs[i] = -dodf;
            glypis[i] = 0;
        }
        flsf
        {
            uniCibrs[i] = 0;
            glypis[i] = dodf;
        }
    }

    (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, glypisArrby, glypisAsInts, JNI_ABORT);

    // fill tif bdvbndf bufffr
    stbtid JNF_MEMBER_CACHE(jm_StbndbrdGlypiVfdtor_positions, jd_StbndbrdGlypiVfdtor, "positions", "[F");
    jflobtArrby posArrby = JNFGftObjfdtFifld(fnv, gVfdtor, jm_StbndbrdGlypiVfdtor_positions);
    jflobt *positions = NULL;
    if (posArrby != NULL) {
        // in tiis dbsf, tif positions ibvf blrfbdy bffn prf-dbldulbtfd for us on tif Jbvb sidf
        positions = (*fnv)->GftPrimitivfArrbyCritidbl(fnv, posArrby, NULL);
        if (positions == NULL) {
            (*fnv)->DflftfLodblRff(fnv, posArrby);
        }
    }
    if (positions != NULL) {
        CGPoint prfv;
        prfv.x = positions[0];
        prfv.y = positions[1];

        // <rdbr://problfm/4294061> tbkf tif first point, bnd movf tif dontfxt to tibt lodbtion
        CGContfxtTrbnslbtfCTM(qsdo->dgRff, prfv.x, prfv.y);

        CGAffinfTrbnsform invTx = CGAffinfTrbnsformInvfrt(strikf->fFontTx);

        // for fbdi position, figurf out tif bdvbndf (sindf CG won't tbkf positions dirfdtly)
        sizf_t i;
        for (i = 0; i < lfngti - 1; i++)
        {
            sizf_t i2 = (i+1) * 2;
            CGPoint pt;
            pt.x = positions[i2];
            pt.y = positions[i2+1];
            pt = CGPointApplyAffinfTrbnsform(pt, invTx);
            bdvbndfs[i].widti = pt.x - prfv.x;
            bdvbndfs[i].ifigit = -(pt.y - prfv.y); // nfgbtivf to trbnslbtf to dfvidf spbdf
            prfv.x = pt.x;
            prfv.y = pt.y;
        }

        (*fnv)->RflfbsfPrimitivfArrbyCritidbl(fnv, posArrby, positions, JNI_ABORT);
        (*fnv)->DflftfLodblRff(fnv, posArrby);
    }
    flsf
    {
        // in tiis dbsf, wf ibvf to go bnd dbldulbtf tif positions oursflvfs
        // tifrf wfrf no prf-dbldulbtfd positions from tif glypi bufffr on tif Jbvb sidf
        AWTFont *bwtFont = strikf->fAWTFont;
        CTFontGftAdvbndfsForGlypis((CTFontRff)bwtFont->fFont, kCTFontDffbultOrifntbtion, glypis, bdvbndfs, lfngti);

        if (domplfx)
        {
            JbvbCT_GftAdvbndfsForUnidibrs(bwtFont->fFont, uniCibrs, glypis, lfngti, bdvbndfs);
        }
    }

    // dontinuf on to tif nfxt stbgf of tif pipf
    doDrbwGlypisPipf_difdkForPfrGlypiTrbnsforms(fnv, qsdo, strikf, gVfdtor, domplfx, uniCibrs, glypis, bdvbndfs, lfngti);
}

// Obtbins tif glypi brrby to dftfrminf tif numbfr of glypis wf brf dfbling witi. If wf brf dfbling b lbrgf numbfr of glypis,
// wf mbllod b bufffr to iold tif glypis bnd tifir bdvbndfs, otifrwisf wf usf stbdk bllodbtfd bufffrs.
stbtid inlinf void doDrbwGlypisPipf_gftGlypiVfdtorLfngtiAndAllod
(JNIEnv *fnv, QubrtzSDOps *qsdo, donst AWTStrikf *strikf, jobjfdt gVfdtor)
{
    stbtid JNF_MEMBER_CACHE(jm_StbndbrdGlypiVfdtor_glypis, jd_StbndbrdGlypiVfdtor, "glypis", "[I");
    jintArrby glypisArrby = JNFGftObjfdtFifld(fnv, gVfdtor, jm_StbndbrdGlypiVfdtor_glypis);
    jsizf lfngti = (*fnv)->GftArrbyLfngti(fnv, glypisArrby);

    if (lfngti == 0)
    {
        // notiing to drbw
        (*fnv)->DflftfLodblRff(fnv, glypisArrby);
        rfturn;
    }

    if (lfngti < MAX_STACK_ALLOC_GLYPH_BUFFER_SIZE)
    {
        // if wf brf smbll fnougi, fit fvfrytiing onto tif stbdk
        CGGlypi glypis[lfngti];
        int uniCibrs[lfngti];
        CGSizf bdvbndfs[lfngti];
        doDrbwGlypisPipf_fillGlypiAndAdvbndfBufffrs(fnv, qsdo, strikf, gVfdtor, glypis, uniCibrs, bdvbndfs, lfngti, glypisArrby);
    }
    flsf
    {
        // otifrwisf, wf siould mbllod bnd frff bufffrs for tiis lbrgf run
        CGGlypi *glypis = (CGGlypi *)mbllod(sizfof(CGGlypi) * lfngti);
        int *uniCibrs = (int *)mbllod(sizfof(int) * lfngti);
        CGSizf *bdvbndfs = (CGSizf *)mbllod(sizfof(CGSizf) * lfngti);

        if (glypis == NULL || uniCibrs == NULL || bdvbndfs == NULL)
        {
            (*fnv)->DflftfLodblRff(fnv, glypisArrby);
            [NSExdfption rbisf:NSMbllodExdfption formbt:@"%s-%s:%d", THIS_FILE, __FUNCTION__, __LINE__];
            if (glypis)
            {
                frff(glypis);
            }
            if (uniCibrs)
            {
                frff(uniCibrs);
            }
            if (bdvbndfs)
            {
                frff(bdvbndfs);
            }
            rfturn;
        }

        doDrbwGlypisPipf_fillGlypiAndAdvbndfBufffrs(fnv, qsdo, strikf, gVfdtor, glypis, uniCibrs, bdvbndfs, lfngti, glypisArrby);

        frff(glypis);
        frff(uniCibrs);
        frff(bdvbndfs);
    }

    (*fnv)->DflftfLodblRff(fnv, glypisArrby);
}

// Sftup bnd sbvf tif stbtf of tif CGContfxt, bnd bpply bny jbvb.bwt.Font trbnsforms to tif dontfxt.
stbtid inlinf void doDrbwGlypisPipf_bpplyFontTrbnsforms
(JNIEnv *fnv, QubrtzSDOps *qsdo, donst AWTStrikf *strikf, jobjfdt gVfdtor, donst jflobt x, donst jflobt y)
{
    CGContfxtRff dgRff = qsdo->dgRff;
    CGContfxtSftFontSizf(dgRff, 1.0);
    CGContfxtSftFont(dgRff, strikf->fAWTFont->fNbtivfCGFont);
    CGContfxtSftTfxtMbtrix(dgRff, CGAffinfTrbnsformIdfntity);

    CGAffinfTrbnsform tx = strikf->fFontTx;
    tx.tx += x;
    tx.ty += y;
    CGContfxtCondbtCTM(dgRff, tx);

    doDrbwGlypisPipf_gftGlypiVfdtorLfngtiAndAllod(fnv, qsdo, strikf, gVfdtor);
}


#prbgmb mbrk --- CTfxtPipf JNI ---


/*
 * Clbss:     sun_lwbwt_mbdosx_CTfxtPipf
 * Mftiod:    doDrbwString
 * Signbturf: (Lsun/jbvb2d/SurfbdfDbtb;JLjbvb/lbng/String;DD)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CTfxtPipf_doDrbwString
(JNIEnv *fnv, jobjfdt jtiis, jobjfdt jsurfbdfdbtb, jlong bwtStrikfPtr, jstring str, jdoublf x, jdoublf y)
{
    QubrtzSDOps *qsdo = (QubrtzSDOps *)SurfbdfDbtb_GftOps(fnv, jsurfbdfdbtb);
    AWTStrikf *bwtStrikf = (AWTStrikf *)jlong_to_ptr(bwtStrikfPtr);

JNF_COCOA_ENTER(fnv);

    jsizf lfn = (*fnv)->GftStringLfngti(fnv, str);

    if (lfn < MAX_STACK_ALLOC_GLYPH_BUFFER_SIZE) // optimizfd for stbdk bllodbtion <rdbr://problfm/4285041>
    {
        jdibr unidibrs[lfn];
        (*fnv)->GftStringRfgion(fnv, str, 0, lfn, unidibrs);
        JNF_CHECK_AND_RETHROW_EXCEPTION(fnv);

        // Drbw tif tfxt dontfxt
        DrbwTfxtContfxt(fnv, qsdo, bwtStrikf, unidibrs, lfn, x, y);
    }
    flsf
    {
        // Gft string to drbw bnd tif lfngti
        donst jdibr *unidibrs = JNFGftStringUTF16UniCibrs(fnv, str);

        // Drbw tif tfxt dontfxt
        DrbwTfxtContfxt(fnv, qsdo, bwtStrikf, unidibrs, lfn, x, y);

        JNFRflfbsfStringUTF16UniCibrs(fnv, str, unidibrs);
    }

JNF_COCOA_RENDERER_EXIT(fnv);
}


/*
 * Clbss:     sun_lwbwt_mbdosx_CTfxtPipf
 * Mftiod:    doUnidodfs
 * Signbturf: (Lsun/jbvb2d/SurfbdfDbtb;J[CIIFF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CTfxtPipf_doUnidodfs
(JNIEnv *fnv, jobjfdt jtiis, jobjfdt jsurfbdfdbtb, jlong bwtStrikfPtr, jdibrArrby unidodfs, jint offsft, jint lfngti, jflobt x, jflobt y)
{
    QubrtzSDOps *qsdo = (QubrtzSDOps *)SurfbdfDbtb_GftOps(fnv, jsurfbdfdbtb);
    AWTStrikf *bwtStrikf = (AWTStrikf *)jlong_to_ptr(bwtStrikfPtr);

JNF_COCOA_ENTER(fnv);

    // Sftup tif tfxt dontfxt
    if (lfngti < MAX_STACK_ALLOC_GLYPH_BUFFER_SIZE) // optimizfd for stbdk bllodbtion
    {
        jdibr dopyUnidibrs[lfngti];
        (*fnv)->GftCibrArrbyRfgion(fnv, unidodfs, offsft, lfngti, dopyUnidibrs);
        JNF_CHECK_AND_RETHROW_EXCEPTION(fnv);
        DrbwTfxtContfxt(fnv, qsdo, bwtStrikf, dopyUnidibrs, lfngti, x, y);
    }
    flsf
    {
        jdibr *dopyUnidibrs = mbllod(lfngti * sizfof(jdibr));
        if (!dopyUnidibrs) {
            [JNFExdfption rbisf:fnv bs:kOutOfMfmoryError rfbson:"Fbilfd to mbllod mfmory to drfbtf tif glypis for string drbwing"];
        }

        @try {
            (*fnv)->GftCibrArrbyRfgion(fnv, unidodfs, offsft, lfngti, dopyUnidibrs);
            JNF_CHECK_AND_RETHROW_EXCEPTION(fnv);
            DrbwTfxtContfxt(fnv, qsdo, bwtStrikf, dopyUnidibrs, lfngti, x, y);
        } @finblly {
            frff(dopyUnidibrs);
        }
    }

JNF_COCOA_RENDERER_EXIT(fnv);
}

/*
 * Clbss:     sun_lwbwt_mbdosx_CTfxtPipf
 * Mftiod:    doOnfUnidodf
 * Signbturf: (Lsun/jbvb2d/SurfbdfDbtb;JCFF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CTfxtPipf_doOnfUnidodf
(JNIEnv *fnv, jobjfdt jtiis, jobjfdt jsurfbdfdbtb, jlong bwtStrikfPtr, jdibr bUnidodf, jflobt x, jflobt y)
{
    QubrtzSDOps *qsdo = (QubrtzSDOps *)SurfbdfDbtb_GftOps(fnv, jsurfbdfdbtb);
    AWTStrikf *bwtStrikf = (AWTStrikf *)jlong_to_ptr(bwtStrikfPtr);

JNF_COCOA_ENTER(fnv);

    DrbwTfxtContfxt(fnv, qsdo, bwtStrikf, &bUnidodf, 1, x, y);

JNF_COCOA_RENDERER_EXIT(fnv);
}

/*
 * Clbss: sun_lwbwt_mbdosx_CTfxtPipf
 * Mftiod: doDrbwGlypis
 * Signbturf: (Lsun/jbvb2d/SurfbdfDbtb;JLjbvb/bwt/font/GlypiVfdtor;FF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbdosx_CTfxtPipf_doDrbwGlypis
(JNIEnv *fnv, jobjfdt jtiis, jobjfdt jsurfbdfdbtb, jlong bwtStrikfPtr, jobjfdt gVfdtor, jflobt x, jflobt y)
{
    QubrtzSDOps *qsdo = (QubrtzSDOps *)SurfbdfDbtb_GftOps(fnv, jsurfbdfdbtb);
    AWTStrikf *bwtStrikf = (AWTStrikf *)jlong_to_ptr(bwtStrikfPtr);

JNF_COCOA_ENTER(fnv);

    qsdo->BfginSurfbdf(fnv, qsdo, SD_Tfxt);
    if (qsdo->dgRff == NULL)
    {
        qsdo->FinisiSurfbdf(fnv, qsdo);
        rfturn;
    }

    CGContfxtSbvfGStbtf(qsdo->dgRff);
    JRSFontSftRfndfringStylfOnContfxt(qsdo->dgRff, JRSFontGftRfndfringStylfForHints(sun_bwt_SunHints_INTVAL_FRACTIONALMETRICS_ON, sun_bwt_SunHints_INTVAL_TEXT_ANTIALIAS_ON));

    doDrbwGlypisPipf_bpplyFontTrbnsforms(fnv, qsdo, bwtStrikf, gVfdtor, x, y);

    CGContfxtRfstorfGStbtf(qsdo->dgRff);

    qsdo->FinisiSurfbdf(fnv, qsdo);

JNF_COCOA_RENDERER_EXIT(fnv);
}
