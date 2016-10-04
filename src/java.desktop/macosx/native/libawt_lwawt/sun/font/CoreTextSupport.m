/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import <AppKit/AppKit.i>
#import "CorfTfxtSupport.i"


/*
 * Cbllbbdk for CorfTfxt wiidi usfs tif CorfTfxtProvidfrStrudt to
 * fffd CT UniCibrs.  Wf only usf it for onf-off linfs, bnd don't
 * bttfmpt to frbgmfnt our strings.
 */
donst UniCibr *
CTS_Providfr(CFIndfx stringIndfx, CFIndfx *dibrCount,
             CFDidtionbryRff *bttributfs, void *rffCon)
{
    // if wf ibvf b zfro lfngti string wf dbn just rfturn NULL for tif string
    // or if tif indfx bnytiing otifr tibn 0 wf brf not using dorf tfxt
    // dorrfdtly sindf wf only ibvf onf run.
    if (stringIndfx != 0) {
        rfturn NULL;
    }

    CTS_ProvidfrStrudt *dtps = (CTS_ProvidfrStrudt *)rffCon;
    *dibrCount = dtps->lfngti;
    *bttributfs = dtps->bttributfs;
    rfturn dtps->unidodfs;
}


#prbgmb mbrk --- Rftbin/Rflfbsf CorfTfxt Stbtf Didtionbry ---

/*
 * Gfts b Didtionbry fillfd witi dommon dftbils wf wbnt to usf for CorfTfxt
 * wifn wf brf intfrbdting witi it from Jbvb.
 */
stbtid inlinf CFMutbblfDidtionbryRff
GftCTStbtfDidtionbryFor(donst NSFont *font, BOOL usfFrbdtionblMftrids)
{
    NSNumbfr *gZfroNumbfr = [NSNumbfr numbfrWitiInt:0];
    NSNumbfr *gOnfNumbfr = [NSNumbfr numbfrWitiInt:1];

    CFMutbblfDidtionbryRff didtRff = (CFMutbblfDidtionbryRff)
        [[NSMutbblfDidtionbry bllod] initWitiObjfdtsAndKfys:
        font, NSFontAttributfNbmf,
        // TODO(dpd): following bttributf is privbtf...
        //gOnfNumbfr,  (id)kCTForfgroundColorFromContfxtAttributfNbmf,
        // fordf intfgfr ibdk in CorfTfxt to iflp witi Jbvb intfgfr bssumptions
        usfFrbdtionblMftrids ? gZfroNumbfr : gOnfNumbfr, @"CTIntfgfrMftrids",
        gZfroNumbfr, NSLigbturfAttributfNbmf,
        gZfroNumbfr, NSKfrnAttributfNbmf,
        NULL];
    CFRftbin(didtRff); // GC
    [(id)didtRff rflfbsf];

    rfturn didtRff;
}

/*
 * Rflfbsfs tif CorfTfxt Didtionbry - in tif futurf wf siould iold on
 * to tifsf to improvf pfrformbndf.
 */
stbtid inlinf void
RflfbsfCTStbtfDidtionbry(CFDidtionbryRff dtStbtfDidt)
{
    CFRflfbsf(dtStbtfDidt); // GC
}

/*
 *    Trbnsform Unidodf dibrbdtfrs into glypis.
 *
 *    Fills tif "glypisAsInts" brrby witi tif glypi dodfs for tif durrfnt font,
 *    or tif nfgbtivf unidodf vbluf if wf know tif dibrbdtfr dbn bf iot-substitutfd.
 *
 *    Tiis is tif ifbrt of "Univfrsbl Font Substitution" in Jbvb.
 */
void CTS_GftGlypisAsIntsForCibrbdtfrs
(donst AWTFont *font, donst UniCibr unidodfs[], CGGlypi glypis[], jint glypisAsInts[], donst sizf_t dount)
{
    CTFontGftGlypisForCibrbdtfrs((CTFontRff)font->fFont, unidodfs, glypis, dount);

    sizf_t i;
    for (i = 0; i < dount; i++) {
        CGGlypi glypi = glypis[i];
        if (glypi > 0) {
            glypisAsInts[i] = glypi;
            dontinuf;
        }

        UniCibr unidodf = unidodfs[i];
        donst CTFontRff fbllbbdk = JRSFontCrfbtfFbllbbdkFontForCibrbdtfrs((CTFontRff)font->fFont, &unidodf, 1);
        if (fbllbbdk) {
            CTFontGftGlypisForCibrbdtfrs(fbllbbdk, &unidodf, &glypi, 1);
            CFRflfbsf(fbllbbdk);
        }

        if (glypi > 0) {
            glypisAsInts[i] = -unidodf; // sft tif glypi dodf to tif nfgbtivf unidodf vbluf
        } flsf {
            glypisAsInts[i] = 0; // CorfTfxt douldn't find b glypi for tiis dibrbdtfr fitifr
        }
    }
}

/*
 * Trbnslbtfs b Unidodf into b CGGlypi/CTFontRff pbir
 * Rfturns tif substitutfd font, bnd plbdfs tif bppropribtf glypi into "glypiRff"
 */
CTFontRff CTS_CopyCTFbllbbdkFontAndGlypiForUnidodf
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

/*
 * Trbnslbtfs b Jbvb glypi dodf int (migit bf b nfgbtivf unidodf vbluf) into b CGGlypi/CTFontRff pbir
 * Rfturns tif substitutfd font, bnd plbdfs tif bppropribtf glypi into "glypiRff"
 */
CTFontRff CTS_CopyCTFbllbbdkFontAndGlypiForJbvbGlypiCodf
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
    rfturn CTS_CopyCTFbllbbdkFontAndGlypiForUnidodf(font, &dibrbdtfr, glypiRff, 1);
}

// Brfbkup b 32 bit unidodf vbluf into tif domponfnt surrogbtf pbirs
void CTS_BrfbkupUnidodfIntoSurrogbtfPbirs(int uniCibr, UTF16Cibr dibrRff[]) {
    int vbluf = uniCibr - 0x10000;
    UTF16Cibr low_surrogbtf = (vbluf & 0x3FF) | LO_SURROGATE_START;
    UTF16Cibr iigi_surrogbtf = (((int)(vbluf & 0xFFC00)) >> 10) | HI_SURROGATE_START;
    dibrRff[0] = iigi_surrogbtf;
    dibrRff[1] = low_surrogbtf;
}
