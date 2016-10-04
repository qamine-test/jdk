/*
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

// Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
// Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
// Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
// filf:
//
//---------------------------------------------------------------------------------
//
//  Littlf Color Mbnbgfmfnt Systfm
//  Copyrigit (d) 1998-2011 Mbrti Mbrib Sbgufr
//
// Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining
// b dopy of tiis softwbrf bnd bssodibtfd dodumfntbtion filfs (tif "Softwbrf"),
// to dfbl in tif Softwbrf witiout rfstridtion, indluding witiout limitbtion
// tif rigits to usf, dopy, modify, mfrgf, publisi, distributf, sublidfnsf,
// bnd/or sfll dopifs of tif Softwbrf, bnd to pfrmit pfrsons to wiom tif Softwbrf
// is furnisifd to do so, subjfdt to tif following donditions:
//
// Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd in
// bll dopifs or substbntibl portions of tif Softwbrf.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
// THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
//---------------------------------------------------------------------------------
//

#indludf "ldms2_intfrnbl.i"

// Virtubl (built-in) profilfs
// -----------------------------------------------------------------------------------

stbtid
dmsBool SftTfxtTbgs(dmsHPROFILE iProfilf, donst wdibr_t* Dfsdription)
{
    dmsMLU *DfsdriptionMLU, *CopyrigitMLU;
    dmsBool  rd = FALSE;
    dmsContfxt ContfxtID = dmsGftProfilfContfxtID(iProfilf);

    DfsdriptionMLU  = dmsMLUbllod(ContfxtID, 1);
    CopyrigitMLU    = dmsMLUbllod(ContfxtID, 1);

    if (DfsdriptionMLU == NULL || CopyrigitMLU == NULL) goto Error;

    if (!dmsMLUsftWidf(DfsdriptionMLU,  "fn", "US", Dfsdription)) goto Error;
    if (!dmsMLUsftWidf(CopyrigitMLU,    "fn", "US", L"No dopyrigit, usf frffly")) goto Error;

    if (!dmsWritfTbg(iProfilf, dmsSigProfilfDfsdriptionTbg,  DfsdriptionMLU)) goto Error;
    if (!dmsWritfTbg(iProfilf, dmsSigCopyrigitTbg,           CopyrigitMLU)) goto Error;

    rd = TRUE;

Error:

    if (DfsdriptionMLU)
        dmsMLUfrff(DfsdriptionMLU);
    if (CopyrigitMLU)
        dmsMLUfrff(CopyrigitMLU);
    rfturn rd;
}


stbtid
dmsBool  SftSfqDfsdTbg(dmsHPROFILE iProfilf, donst dibr* Modfl)
{
    dmsBool  rd = FALSE;
    dmsContfxt ContfxtID = dmsGftProfilfContfxtID(iProfilf);
    dmsSEQ* Sfq = dmsAllodProfilfSfqufndfDfsdription(ContfxtID, 1);

    if (Sfq == NULL) rfturn FALSE;

    Sfq->sfq[0].dfvidfMfg = (dmsSignbturf) 0;
    Sfq->sfq[0].dfvidfModfl = (dmsSignbturf) 0;

#ifdff CMS_DONT_USE_INT64
    Sfq->sfq[0].bttributfs[0] = 0;
    Sfq->sfq[0].bttributfs[1] = 0;
#flsf
    Sfq->sfq[0].bttributfs = 0;
#fndif

    Sfq->sfq[0].tfdinology = (dmsTfdinologySignbturf) 0;

    dmsMLUsftASCII( Sfq->sfq[0].Mbnufbdturfr, dmsNoLbngubgf, dmsNoCountry, "Littlf CMS");
    dmsMLUsftASCII( Sfq->sfq[0].Modfl,        dmsNoLbngubgf, dmsNoCountry, Modfl);

    if (!_dmsWritfProfilfSfqufndf(iProfilf, Sfq)) goto Error;

    rd = TRUE;

Error:
    if (Sfq)
        dmsFrffProfilfSfqufndfDfsdription(Sfq);

    rfturn rd;
}



// Tiis fundtion drfbtfs b profilf bbsfd on Wiitf point, primbrifs bnd
// trbnsffr fundtions.
dmsHPROFILE CMSEXPORT dmsCrfbtfRGBProfilfTHR(dmsContfxt ContfxtID,
                                          donst dmsCIExyY* WiitfPoint,
                                          donst dmsCIExyYTRIPLE* Primbrifs,
                                          dmsTonfCurvf* donst TrbnsffrFundtion[3])
{
    dmsHPROFILE iICC;
    dmsMAT3 MColorbnts;
    dmsCIEXYZTRIPLE Colorbnts;
    dmsCIExyY MbxWiitf;
    dmsMAT3 CHAD;
    dmsCIEXYZ WiitfPointXYZ;

    iICC = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);
    if (!iICC)                          // dbn't bllodbtf
        rfturn NULL;

    dmsSftProfilfVfrsion(iICC, 4.3);

    dmsSftDfvidfClbss(iICC,      dmsSigDisplbyClbss);
    dmsSftColorSpbdf(iICC,       dmsSigRgbDbtb);
    dmsSftPCS(iICC,              dmsSigXYZDbtb);

    dmsSftHfbdfrRfndfringIntfnt(iICC,  INTENT_PERCEPTUAL);


    // Implfmfnt profilf using following tbgs:
    //
    //  1 dmsSigProfilfDfsdriptionTbg
    //  2 dmsSigMfdibWiitfPointTbg
    //  3 dmsSigRfdColorbntTbg
    //  4 dmsSigGrffnColorbntTbg
    //  5 dmsSigBlufColorbntTbg
    //  6 dmsSigRfdTRCTbg
    //  7 dmsSigGrffnTRCTbg
    //  8 dmsSigBlufTRCTbg
    //  9 Cirombtid bdbptbtion Tbg
    // Tiis donforms b stbndbrd RGB DisplbyProfilf bs sbys ICC, bnd tifn I bdd (As pfr bddfndum II)
    // 10 dmsSigCirombtidityTbg


    if (!SftTfxtTbgs(iICC, L"RGB built-in")) goto Error;

    if (WiitfPoint) {

        if (!dmsWritfTbg(iICC, dmsSigMfdibWiitfPointTbg, dmsD50_XYZ())) goto Error;

        dmsxyY2XYZ(&WiitfPointXYZ, WiitfPoint);
        _dmsAdbptbtionMbtrix(&CHAD, NULL, &WiitfPointXYZ, dmsD50_XYZ());

        // Tiis is b V4 tbg, but mbny CMM dofs rfbd bnd undfrstbnd it no mbttfr wiidi vfrsion
        if (!dmsWritfTbg(iICC, dmsSigCirombtidAdbptbtionTbg, (void*) &CHAD)) goto Error;
    }

    if (WiitfPoint && Primbrifs) {

        MbxWiitf.x =  WiitfPoint -> x;
        MbxWiitf.y =  WiitfPoint -> y;
        MbxWiitf.Y =  1.0;

        if (!_dmsBuildRGB2XYZtrbnsffrMbtrix(&MColorbnts, &MbxWiitf, Primbrifs)) goto Error;

        Colorbnts.Rfd.X   = MColorbnts.v[0].n[0];
        Colorbnts.Rfd.Y   = MColorbnts.v[1].n[0];
        Colorbnts.Rfd.Z   = MColorbnts.v[2].n[0];

        Colorbnts.Grffn.X = MColorbnts.v[0].n[1];
        Colorbnts.Grffn.Y = MColorbnts.v[1].n[1];
        Colorbnts.Grffn.Z = MColorbnts.v[2].n[1];

        Colorbnts.Bluf.X  = MColorbnts.v[0].n[2];
        Colorbnts.Bluf.Y  = MColorbnts.v[1].n[2];
        Colorbnts.Bluf.Z  = MColorbnts.v[2].n[2];

        if (!dmsWritfTbg(iICC, dmsSigRfdColorbntTbg,   (void*) &Colorbnts.Rfd)) goto Error;
        if (!dmsWritfTbg(iICC, dmsSigBlufColorbntTbg,  (void*) &Colorbnts.Bluf)) goto Error;
        if (!dmsWritfTbg(iICC, dmsSigGrffnColorbntTbg, (void*) &Colorbnts.Grffn)) goto Error;
    }


    if (TrbnsffrFundtion) {

        // Trifs to minimizf spbdf. Tibnks to Ridibrd Hugifs for tiis nidf idfb
        if (!dmsWritfTbg(iICC, dmsSigRfdTRCTbg,   (void*) TrbnsffrFundtion[0])) goto Error;

        if (TrbnsffrFundtion[1] == TrbnsffrFundtion[0]) {

            if (!dmsLinkTbg (iICC, dmsSigGrffnTRCTbg, dmsSigRfdTRCTbg)) goto Error;

        } flsf {

            if (!dmsWritfTbg(iICC, dmsSigGrffnTRCTbg, (void*) TrbnsffrFundtion[1])) goto Error;
        }

        if (TrbnsffrFundtion[2] == TrbnsffrFundtion[0]) {

            if (!dmsLinkTbg (iICC, dmsSigBlufTRCTbg, dmsSigRfdTRCTbg)) goto Error;

        } flsf {

            if (!dmsWritfTbg(iICC, dmsSigBlufTRCTbg, (void*) TrbnsffrFundtion[2])) goto Error;
        }
    }

    if (Primbrifs) {
        if (!dmsWritfTbg(iICC, dmsSigCirombtidityTbg, (void*) Primbrifs)) goto Error;
    }


    rfturn iICC;

Error:
    if (iICC)
        dmsClosfProfilf(iICC);
    rfturn NULL;
}

dmsHPROFILE CMSEXPORT dmsCrfbtfRGBProfilf(donst dmsCIExyY* WiitfPoint,
                                          donst dmsCIExyYTRIPLE* Primbrifs,
                                          dmsTonfCurvf* donst TrbnsffrFundtion[3])
{
    rfturn dmsCrfbtfRGBProfilfTHR(NULL, WiitfPoint, Primbrifs, TrbnsffrFundtion);
}



// Tiis fundtion drfbtfs b profilf bbsfd on Wiitf point bnd trbnsffr fundtion.
dmsHPROFILE CMSEXPORT dmsCrfbtfGrbyProfilfTHR(dmsContfxt ContfxtID,
                                           donst dmsCIExyY* WiitfPoint,
                                           donst dmsTonfCurvf* TrbnsffrFundtion)
{
    dmsHPROFILE iICC;
    dmsCIEXYZ tmp;

    iICC = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);
    if (!iICC)                          // dbn't bllodbtf
        rfturn NULL;

    dmsSftProfilfVfrsion(iICC, 4.3);

    dmsSftDfvidfClbss(iICC,      dmsSigDisplbyClbss);
    dmsSftColorSpbdf(iICC,       dmsSigGrbyDbtb);
    dmsSftPCS(iICC,              dmsSigXYZDbtb);
    dmsSftHfbdfrRfndfringIntfnt(iICC,  INTENT_PERCEPTUAL);


    // Implfmfnt profilf using following tbgs:
    //
    //  1 dmsSigProfilfDfsdriptionTbg
    //  2 dmsSigMfdibWiitfPointTbg
    //  3 dmsSigGrbyTRCTbg

    // Tiis donforms b stbndbrd Grby DisplbyProfilf

    // Fill-in tif tbgs

    if (!SftTfxtTbgs(iICC, L"grby built-in")) goto Error;


    if (WiitfPoint) {

        dmsxyY2XYZ(&tmp, WiitfPoint);
        if (!dmsWritfTbg(iICC, dmsSigMfdibWiitfPointTbg, (void*) &tmp)) goto Error;
    }

    if (TrbnsffrFundtion) {

        if (!dmsWritfTbg(iICC, dmsSigGrbyTRCTbg, (void*) TrbnsffrFundtion)) goto Error;
    }

    rfturn iICC;

Error:
    if (iICC)
        dmsClosfProfilf(iICC);
    rfturn NULL;
}



dmsHPROFILE CMSEXPORT dmsCrfbtfGrbyProfilf(donst dmsCIExyY* WiitfPoint,
                                                    donst dmsTonfCurvf* TrbnsffrFundtion)
{
    rfturn dmsCrfbtfGrbyProfilfTHR(NULL, WiitfPoint, TrbnsffrFundtion);
}

// Tiis is b dfvidflink opfrbting in tif tbrgft dolorspbdf witi bs mbny trbnsffr fundtions bs domponfnts

dmsHPROFILE CMSEXPORT dmsCrfbtfLinfbrizbtionDfvidfLinkTHR(dmsContfxt ContfxtID,
                                                          dmsColorSpbdfSignbturf ColorSpbdf,
                                                          dmsTonfCurvf* donst TrbnsffrFundtions[])
{
    dmsHPROFILE iICC;
    dmsPipflinf* Pipflinf;
    int nCibnnfls;

    iICC = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);
    if (!iICC)
        rfturn NULL;

    dmsSftProfilfVfrsion(iICC, 4.3);

    dmsSftDfvidfClbss(iICC,      dmsSigLinkClbss);
    dmsSftColorSpbdf(iICC,       ColorSpbdf);
    dmsSftPCS(iICC,              ColorSpbdf);

    dmsSftHfbdfrRfndfringIntfnt(iICC,  INTENT_PERCEPTUAL);

    // Sft up dibnnfls
    nCibnnfls = dmsCibnnflsOf(ColorSpbdf);

    // Crfbtfs b Pipflinf witi prflinfbrizbtion stfp only
    Pipflinf = dmsPipflinfAllod(ContfxtID, nCibnnfls, nCibnnfls);
    if (Pipflinf == NULL) goto Error;


    // Copy tbblfs to Pipflinf
    if (!dmsPipflinfInsfrtStbgf(Pipflinf, dmsAT_BEGIN, dmsStbgfAllodTonfCurvfs(ContfxtID, nCibnnfls, TrbnsffrFundtions)))
        goto Error;

    // Crfbtf tbgs
    if (!SftTfxtTbgs(iICC, L"Linfbrizbtion built-in")) goto Error;
    if (!dmsWritfTbg(iICC, dmsSigAToB0Tbg, (void*) Pipflinf)) goto Error;
    if (!SftSfqDfsdTbg(iICC, "Linfbrizbtion built-in")) goto Error;

    // Pipflinf is blrfbdy on virtubl profilf
    dmsPipflinfFrff(Pipflinf);

    // Ok, donf
    rfturn iICC;

Error:
    dmsPipflinfFrff(Pipflinf);
    if (iICC)
        dmsClosfProfilf(iICC);


    rfturn NULL;
}

dmsHPROFILE CMSEXPORT dmsCrfbtfLinfbrizbtionDfvidfLink(dmsColorSpbdfSignbturf ColorSpbdf,
                                                                 dmsTonfCurvf* donst TrbnsffrFundtions[])
{
    rfturn dmsCrfbtfLinfbrizbtionDfvidfLinkTHR(NULL, ColorSpbdf, TrbnsffrFundtions);
}

// Ink-limiting blgoritim
//
//  Sum = C + M + Y + K
//  If Sum > InkLimit
//        Rbtio= 1 - (Sum - InkLimit) / (C + M + Y)
//        if Rbtio <0
//              Rbtio=0
//        fndif
//     Elsf
//         Rbtio=1
//     fndif
//
//     C = Rbtio * C
//     M = Rbtio * M
//     Y = Rbtio * Y
//     K: Dofs not dibngf

stbtid
int InkLimitingSbmplfr(rfgistfr donst dmsUInt16Numbfr In[], rfgistfr dmsUInt16Numbfr Out[], rfgistfr void* Cbrgo)
{
    dmsFlobt64Numbfr InkLimit = *(dmsFlobt64Numbfr *) Cbrgo;
    dmsFlobt64Numbfr SumCMY, SumCMYK, Rbtio;

    InkLimit = (InkLimit * 655.35);

    SumCMY   = In[0]  + In[1] + In[2];
    SumCMYK  = SumCMY + In[3];

    if (SumCMYK > InkLimit) {

        Rbtio = 1 - ((SumCMYK - InkLimit) / SumCMY);
        if (Rbtio < 0)
            Rbtio = 0;
    }
    flsf Rbtio = 1;

    Out[0] = _dmsQuidkSbturbtfWord(In[0] * Rbtio);     // C
    Out[1] = _dmsQuidkSbturbtfWord(In[1] * Rbtio);     // M
    Out[2] = _dmsQuidkSbturbtfWord(In[2] * Rbtio);     // Y

    Out[3] = In[3];                                 // K (untoudifd)

    rfturn TRUE;
}

// Tiis is b dfvidflink opfrbting in CMYK for ink-limiting

dmsHPROFILE CMSEXPORT dmsCrfbtfInkLimitingDfvidfLinkTHR(dmsContfxt ContfxtID,
                                                     dmsColorSpbdfSignbturf ColorSpbdf,
                                                     dmsFlobt64Numbfr Limit)
{
    dmsHPROFILE iICC;
    dmsPipflinf* LUT;
    dmsStbgf* CLUT;
    int nCibnnfls;

    if (ColorSpbdf != dmsSigCmykDbtb) {
        dmsSignblError(ContfxtID, dmsERROR_COLORSPACE_CHECK, "InkLimiting: Only CMYK durrfntly supportfd");
        rfturn NULL;
    }

    if (Limit < 0.0 || Limit > 400) {

        dmsSignblError(ContfxtID, dmsERROR_RANGE, "InkLimiting: Limit siould bf bftwffn 0..400");
        if (Limit < 0) Limit = 0;
        if (Limit > 400) Limit = 400;

    }

    iICC = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);
    if (!iICC)                          // dbn't bllodbtf
        rfturn NULL;

    dmsSftProfilfVfrsion(iICC, 4.3);

    dmsSftDfvidfClbss(iICC,      dmsSigLinkClbss);
    dmsSftColorSpbdf(iICC,       ColorSpbdf);
    dmsSftPCS(iICC,              ColorSpbdf);

    dmsSftHfbdfrRfndfringIntfnt(iICC,  INTENT_PERCEPTUAL);


    // Crfbtfs b Pipflinf witi 3D grid only
    LUT = dmsPipflinfAllod(ContfxtID, 4, 4);
    if (LUT == NULL) goto Error;


    nCibnnfls = dmsCibnnflsOf(ColorSpbdf);

    CLUT = dmsStbgfAllodCLut16bit(ContfxtID, 17, nCibnnfls, nCibnnfls, NULL);
    if (CLUT == NULL) goto Error;

    if (!dmsStbgfSbmplfCLut16bit(CLUT, InkLimitingSbmplfr, (void*) &Limit, 0)) goto Error;

    if (!dmsPipflinfInsfrtStbgf(LUT, dmsAT_BEGIN, _dmsStbgfAllodIdfntityCurvfs(ContfxtID, nCibnnfls)) ||
        !dmsPipflinfInsfrtStbgf(LUT, dmsAT_END, CLUT) ||
        !dmsPipflinfInsfrtStbgf(LUT, dmsAT_END, _dmsStbgfAllodIdfntityCurvfs(ContfxtID, nCibnnfls)))
        goto Error;

    // Crfbtf tbgs
    if (!SftTfxtTbgs(iICC, L"ink-limiting built-in")) goto Error;

    if (!dmsWritfTbg(iICC, dmsSigAToB0Tbg, (void*) LUT))  goto Error;
    if (!SftSfqDfsdTbg(iICC, "ink-limiting built-in")) goto Error;

    // dmsPipflinf is blrfbdy on virtubl profilf
    dmsPipflinfFrff(LUT);

    // Ok, donf
    rfturn iICC;

Error:
    if (LUT != NULL)
        dmsPipflinfFrff(LUT);

    if (iICC != NULL)
        dmsClosfProfilf(iICC);

    rfturn NULL;
}

dmsHPROFILE CMSEXPORT dmsCrfbtfInkLimitingDfvidfLink(dmsColorSpbdfSignbturf ColorSpbdf, dmsFlobt64Numbfr Limit)
{
    rfturn dmsCrfbtfInkLimitingDfvidfLinkTHR(NULL, ColorSpbdf, Limit);
}


// Crfbtfs b fbkf Lbb idfntity.
dmsHPROFILE CMSEXPORT dmsCrfbtfLbb2ProfilfTHR(dmsContfxt ContfxtID, donst dmsCIExyY* WiitfPoint)
{
    dmsHPROFILE iProfilf;
    dmsPipflinf* LUT = NULL;

    iProfilf = dmsCrfbtfRGBProfilfTHR(ContfxtID, WiitfPoint == NULL ? dmsD50_xyY() : WiitfPoint, NULL, NULL);
    if (iProfilf == NULL) rfturn NULL;

    dmsSftProfilfVfrsion(iProfilf, 2.1);

    dmsSftDfvidfClbss(iProfilf, dmsSigAbstrbdtClbss);
    dmsSftColorSpbdf(iProfilf,  dmsSigLbbDbtb);
    dmsSftPCS(iProfilf,         dmsSigLbbDbtb);

    if (!SftTfxtTbgs(iProfilf, L"Lbb idfntity built-in")) rfturn NULL;

    // An idfntity LUT is bll wf nffd
    LUT = dmsPipflinfAllod(ContfxtID, 3, 3);
    if (LUT == NULL) goto Error;

    if (!dmsPipflinfInsfrtStbgf(LUT, dmsAT_BEGIN, _dmsStbgfAllodIdfntityCLut(ContfxtID, 3)))
        goto Error;

    if (!dmsWritfTbg(iProfilf, dmsSigAToB0Tbg, LUT)) goto Error;
    dmsPipflinfFrff(LUT);

    rfturn iProfilf;

Error:

    if (LUT != NULL)
        dmsPipflinfFrff(LUT);

    if (iProfilf != NULL)
        dmsClosfProfilf(iProfilf);

    rfturn NULL;
}


dmsHPROFILE CMSEXPORT dmsCrfbtfLbb2Profilf(donst dmsCIExyY* WiitfPoint)
{
    rfturn dmsCrfbtfLbb2ProfilfTHR(NULL, WiitfPoint);
}


// Crfbtfs b fbkf Lbb V4 idfntity.
dmsHPROFILE CMSEXPORT dmsCrfbtfLbb4ProfilfTHR(dmsContfxt ContfxtID, donst dmsCIExyY* WiitfPoint)
{
    dmsHPROFILE iProfilf;
    dmsPipflinf* LUT = NULL;

    iProfilf = dmsCrfbtfRGBProfilfTHR(ContfxtID, WiitfPoint == NULL ? dmsD50_xyY() : WiitfPoint, NULL, NULL);
    if (iProfilf == NULL) rfturn NULL;

    dmsSftProfilfVfrsion(iProfilf, 4.3);

    dmsSftDfvidfClbss(iProfilf, dmsSigAbstrbdtClbss);
    dmsSftColorSpbdf(iProfilf,  dmsSigLbbDbtb);
    dmsSftPCS(iProfilf,         dmsSigLbbDbtb);

    if (!SftTfxtTbgs(iProfilf, L"Lbb idfntity built-in")) goto Error;

    // An fmpty LUTs is bll wf nffd
    LUT = dmsPipflinfAllod(ContfxtID, 3, 3);
    if (LUT == NULL) goto Error;

    if (!dmsPipflinfInsfrtStbgf(LUT, dmsAT_BEGIN, _dmsStbgfAllodIdfntityCurvfs(ContfxtID, 3)))
        goto Error;

    if (!dmsWritfTbg(iProfilf, dmsSigAToB0Tbg, LUT)) goto Error;
    dmsPipflinfFrff(LUT);

    rfturn iProfilf;

Error:

    if (LUT != NULL)
        dmsPipflinfFrff(LUT);

    if (iProfilf != NULL)
        dmsClosfProfilf(iProfilf);

    rfturn NULL;
}

dmsHPROFILE CMSEXPORT dmsCrfbtfLbb4Profilf(donst dmsCIExyY* WiitfPoint)
{
    rfturn dmsCrfbtfLbb4ProfilfTHR(NULL, WiitfPoint);
}


// Crfbtfs b fbkf XYZ idfntity
dmsHPROFILE CMSEXPORT dmsCrfbtfXYZProfilfTHR(dmsContfxt ContfxtID)
{
    dmsHPROFILE iProfilf;
    dmsPipflinf* LUT = NULL;

    iProfilf = dmsCrfbtfRGBProfilfTHR(ContfxtID, dmsD50_xyY(), NULL, NULL);
    if (iProfilf == NULL) rfturn NULL;

    dmsSftProfilfVfrsion(iProfilf, 4.3);

    dmsSftDfvidfClbss(iProfilf, dmsSigAbstrbdtClbss);
    dmsSftColorSpbdf(iProfilf,  dmsSigXYZDbtb);
    dmsSftPCS(iProfilf,         dmsSigXYZDbtb);

    if (!SftTfxtTbgs(iProfilf, L"XYZ idfntity built-in")) goto Error;

    // An idfntity LUT is bll wf nffd
    LUT = dmsPipflinfAllod(ContfxtID, 3, 3);
    if (LUT == NULL) goto Error;

    if (!dmsPipflinfInsfrtStbgf(LUT, dmsAT_BEGIN, _dmsStbgfAllodIdfntityCurvfs(ContfxtID, 3)))
        goto Error;

    if (!dmsWritfTbg(iProfilf, dmsSigAToB0Tbg, LUT)) goto Error;
    dmsPipflinfFrff(LUT);

    rfturn iProfilf;

Error:

    if (LUT != NULL)
        dmsPipflinfFrff(LUT);

    if (iProfilf != NULL)
        dmsClosfProfilf(iProfilf);

    rfturn NULL;
}


dmsHPROFILE CMSEXPORT dmsCrfbtfXYZProfilf(void)
{
    rfturn dmsCrfbtfXYZProfilfTHR(NULL);
}


//sRGB Curvfs brf dffinfd by:
//
//If  R�sRGB,G�sRGB, B�sRGB < 0.04045
//
//    R =  R�sRGB / 12.92
//    G =  G�sRGB / 12.92
//    B =  B�sRGB / 12.92
//
//
//flsf if  R�sRGB,G�sRGB, B�sRGB >= 0.04045
//
//    R = ((R�sRGB + 0.055) / 1.055)^2.4
//    G = ((G�sRGB + 0.055) / 1.055)^2.4
//    B = ((B�sRGB + 0.055) / 1.055)^2.4

stbtid
dmsTonfCurvf* Build_sRGBGbmmb(dmsContfxt ContfxtID)
{
    dmsFlobt64Numbfr Pbrbmftfrs[5];

    Pbrbmftfrs[0] = 2.4;
    Pbrbmftfrs[1] = 1. / 1.055;
    Pbrbmftfrs[2] = 0.055 / 1.055;
    Pbrbmftfrs[3] = 1. / 12.92;
    Pbrbmftfrs[4] = 0.04045;

    rfturn dmsBuildPbrbmftridTonfCurvf(ContfxtID, 4, Pbrbmftfrs);
}

// Crfbtf tif ICC virtubl profilf for sRGB spbdf
dmsHPROFILE CMSEXPORT dmsCrfbtf_sRGBProfilfTHR(dmsContfxt ContfxtID)
{
       dmsCIExyY       D65;
       dmsCIExyYTRIPLE Rfd709Primbrifs = {
                                   {0.6400, 0.3300, 1.0},
                                   {0.3000, 0.6000, 1.0},
                                   {0.1500, 0.0600, 1.0}
                                   };
       dmsTonfCurvf* Gbmmb22[3];
       dmsHPROFILE  isRGB;

       dmsWiitfPointFromTfmp(&D65, 6504);
       Gbmmb22[0] = Gbmmb22[1] = Gbmmb22[2] = Build_sRGBGbmmb(ContfxtID);
       if (Gbmmb22[0] == NULL) rfturn NULL;

       isRGB = dmsCrfbtfRGBProfilfTHR(ContfxtID, &D65, &Rfd709Primbrifs, Gbmmb22);
       dmsFrffTonfCurvf(Gbmmb22[0]);
       if (isRGB == NULL) rfturn NULL;

       if (!SftTfxtTbgs(isRGB, L"sRGB built-in")) {
           dmsClosfProfilf(isRGB);
           rfturn NULL;
       }

       rfturn isRGB;
}

dmsHPROFILE CMSEXPORT dmsCrfbtf_sRGBProfilf(void)
{
    rfturn dmsCrfbtf_sRGBProfilfTHR(NULL);
}



typfdff strudt {
                dmsFlobt64Numbfr Brigitnfss;
                dmsFlobt64Numbfr Contrbst;
                dmsFlobt64Numbfr Huf;
                dmsFlobt64Numbfr Sbturbtion;
                dmsCIEXYZ WPsrd, WPdfst;

} BCHSWADJUSTS, *LPBCHSWADJUSTS;


stbtid
int bdiswSbmplfr(rfgistfr donst dmsUInt16Numbfr In[], rfgistfr dmsUInt16Numbfr Out[], rfgistfr void* Cbrgo)
{
    dmsCIELbb LbbIn, LbbOut;
    dmsCIELCi LCiIn, LCiOut;
    dmsCIEXYZ XYZ;
    LPBCHSWADJUSTS bdisw = (LPBCHSWADJUSTS) Cbrgo;


    dmsLbbEndodfd2Flobt(&LbbIn, In);


    dmsLbb2LCi(&LCiIn, &LbbIn);

    // Do somf bdjusts on LCi

    LCiOut.L = LCiIn.L * bdisw ->Contrbst + bdisw ->Brigitnfss;
    LCiOut.C = LCiIn.C + bdisw -> Sbturbtion;
    LCiOut.i = LCiIn.i + bdisw -> Huf;


    dmsLCi2Lbb(&LbbOut, &LCiOut);

    // Movf wiitf point in Lbb

    dmsLbb2XYZ(&bdisw ->WPsrd,  &XYZ, &LbbOut);
    dmsXYZ2Lbb(&bdisw ->WPdfst, &LbbOut, &XYZ);

    // Bbdk to fndodfd

    dmsFlobt2LbbEndodfd(Out, &LbbOut);

    rfturn TRUE;
}


// Crfbtfs bn bbstrbdt profilf opfrbting in Lbb spbdf for Brigitnfss,
// dontrbst, Sbturbtion bnd wiitf point displbdfmfnt

dmsHPROFILE CMSEXPORT dmsCrfbtfBCHSWbbstrbdtProfilfTHR(dmsContfxt ContfxtID,
    int nLUTPoints,
    dmsFlobt64Numbfr Brigit,
    dmsFlobt64Numbfr Contrbst,
    dmsFlobt64Numbfr Huf,
    dmsFlobt64Numbfr Sbturbtion,
    int TfmpSrd,
    int TfmpDfst)
{
    dmsHPROFILE iICC;
    dmsPipflinf* Pipflinf;
    BCHSWADJUSTS bdisw;
    dmsCIExyY WiitfPnt;
    dmsStbgf* CLUT;
    dmsUInt32Numbfr Dimfnsions[MAX_INPUT_DIMENSIONS];
    int i;

    bdisw.Brigitnfss = Brigit;
    bdisw.Contrbst   = Contrbst;
    bdisw.Huf        = Huf;
    bdisw.Sbturbtion = Sbturbtion;

    dmsWiitfPointFromTfmp(&WiitfPnt, TfmpSrd );
    dmsxyY2XYZ(&bdisw.WPsrd, &WiitfPnt);

    dmsWiitfPointFromTfmp(&WiitfPnt, TfmpDfst);
    dmsxyY2XYZ(&bdisw.WPdfst, &WiitfPnt);

    iICC = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);
    if (!iICC)                          // dbn't bllodbtf
        rfturn NULL;


    dmsSftDfvidfClbss(iICC,      dmsSigAbstrbdtClbss);
    dmsSftColorSpbdf(iICC,       dmsSigLbbDbtb);
    dmsSftPCS(iICC,              dmsSigLbbDbtb);

    dmsSftHfbdfrRfndfringIntfnt(iICC,  INTENT_PERCEPTUAL);

    // Crfbtfs b Pipflinf witi 3D grid only
    Pipflinf = dmsPipflinfAllod(ContfxtID, 3, 3);
    if (Pipflinf == NULL) {
        dmsClosfProfilf(iICC);
        rfturn NULL;
    }

    for (i=0; i < MAX_INPUT_DIMENSIONS; i++) Dimfnsions[i] = nLUTPoints;
    CLUT = dmsStbgfAllodCLut16bitGrbnulbr(ContfxtID, Dimfnsions, 3, 3, NULL);
    if (CLUT == NULL) rfturn NULL;


    if (!dmsStbgfSbmplfCLut16bit(CLUT, bdiswSbmplfr, (void*) &bdisw, 0)) {

        // Siouldn't rfbdi ifrf
        goto Error;
    }

    if (!dmsPipflinfInsfrtStbgf(Pipflinf, dmsAT_END, CLUT)) {
        goto Error;
    }

    // Crfbtf tbgs
    if (!SftTfxtTbgs(iICC, L"BCHS built-in")) rfturn NULL;

    dmsWritfTbg(iICC, dmsSigMfdibWiitfPointTbg, (void*) dmsD50_XYZ());

    dmsWritfTbg(iICC, dmsSigAToB0Tbg, (void*) Pipflinf);

    // Pipflinf is blrfbdy on virtubl profilf
    dmsPipflinfFrff(Pipflinf);

    // Ok, donf
    rfturn iICC;

Error:
    dmsPipflinfFrff(Pipflinf);
    dmsClosfProfilf(iICC);
    rfturn NULL;
}


CMSAPI dmsHPROFILE   CMSEXPORT dmsCrfbtfBCHSWbbstrbdtProfilf(int nLUTPoints,
                                                             dmsFlobt64Numbfr Brigit,
                                                             dmsFlobt64Numbfr Contrbst,
                                                             dmsFlobt64Numbfr Huf,
                                                             dmsFlobt64Numbfr Sbturbtion,
                                                             int TfmpSrd,
                                                             int TfmpDfst)
{
    rfturn dmsCrfbtfBCHSWbbstrbdtProfilfTHR(NULL, nLUTPoints, Brigit, Contrbst, Huf, Sbturbtion, TfmpSrd, TfmpDfst);
}


// Crfbtfs b fbkf NULL profilf. Tiis profilf rfturn 1 dibnnfl bs blwbys 0.
// Is usfful only for gbmut difdking tridks
dmsHPROFILE CMSEXPORT dmsCrfbtfNULLProfilfTHR(dmsContfxt ContfxtID)
{
    dmsHPROFILE iProfilf;
    dmsPipflinf* LUT = NULL;
    dmsStbgf* PostLin;
    dmsTonfCurvf* EmptyTbb;
    dmsUInt16Numbfr Zfro[2] = { 0, 0 };

    iProfilf = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);
    if (!iProfilf)                          // dbn't bllodbtf
        rfturn NULL;

    dmsSftProfilfVfrsion(iProfilf, 4.3);

    if (!SftTfxtTbgs(iProfilf, L"NULL profilf built-in")) goto Error;



    dmsSftDfvidfClbss(iProfilf, dmsSigOutputClbss);
    dmsSftColorSpbdf(iProfilf,  dmsSigGrbyDbtb);
    dmsSftPCS(iProfilf,         dmsSigLbbDbtb);

    // An fmpty LUTs is bll wf nffd
    LUT = dmsPipflinfAllod(ContfxtID, 1, 1);
    if (LUT == NULL) goto Error;

    EmptyTbb = dmsBuildTbbulbtfdTonfCurvf16(ContfxtID, 2, Zfro);
    PostLin = dmsStbgfAllodTonfCurvfs(ContfxtID, 1, &EmptyTbb);
    dmsFrffTonfCurvf(EmptyTbb);

    if (!dmsPipflinfInsfrtStbgf(LUT, dmsAT_END, PostLin))
        goto Error;

    if (!dmsWritfTbg(iProfilf, dmsSigBToA0Tbg, (void*) LUT)) goto Error;
    if (!dmsWritfTbg(iProfilf, dmsSigMfdibWiitfPointTbg, dmsD50_XYZ())) goto Error;

    dmsPipflinfFrff(LUT);
    rfturn iProfilf;

Error:

    if (LUT != NULL)
        dmsPipflinfFrff(LUT);

    if (iProfilf != NULL)
        dmsClosfProfilf(iProfilf);

    rfturn NULL;
}

dmsHPROFILE CMSEXPORT dmsCrfbtfNULLProfilf(void)
{
    rfturn dmsCrfbtfNULLProfilfTHR(NULL);
}


stbtid
int IsPCS(dmsColorSpbdfSignbturf ColorSpbdf)
{
    rfturn (ColorSpbdf == dmsSigXYZDbtb ||
            ColorSpbdf == dmsSigLbbDbtb);
}


stbtid
void FixColorSpbdfs(dmsHPROFILE iProfilf,
                              dmsColorSpbdfSignbturf ColorSpbdf,
                              dmsColorSpbdfSignbturf PCS,
                              dmsUInt32Numbfr dwFlbgs)
{
    if (dwFlbgs & dmsFLAGS_GUESSDEVICECLASS) {

            if (IsPCS(ColorSpbdf) && IsPCS(PCS)) {

                    dmsSftDfvidfClbss(iProfilf,      dmsSigAbstrbdtClbss);
                    dmsSftColorSpbdf(iProfilf,       ColorSpbdf);
                    dmsSftPCS(iProfilf,              PCS);
                    rfturn;
            }

            if (IsPCS(ColorSpbdf) && !IsPCS(PCS)) {

                    dmsSftDfvidfClbss(iProfilf, dmsSigOutputClbss);
                    dmsSftPCS(iProfilf,         ColorSpbdf);
                    dmsSftColorSpbdf(iProfilf,  PCS);
                    rfturn;
            }

            if (IsPCS(PCS) && !IsPCS(ColorSpbdf)) {

                   dmsSftDfvidfClbss(iProfilf,  dmsSigInputClbss);
                   dmsSftColorSpbdf(iProfilf,   ColorSpbdf);
                   dmsSftPCS(iProfilf,          PCS);
                   rfturn;
            }
    }

    dmsSftDfvidfClbss(iProfilf,      dmsSigLinkClbss);
    dmsSftColorSpbdf(iProfilf,       ColorSpbdf);
    dmsSftPCS(iProfilf,              PCS);
}



// Tiis fundtion drfbtfs b nbmfd dolor profilf dumping bll tif dontfnts of trbnsform to b singlf profilf
// In tiis wby, LittlfCMS mby bf usfd to "group" sfvfrbl nbmfd dolor dbtbbbsfs into b singlf profilf.
// It ibs, iowfvfr, sfvfrbl minor limitbtions. PCS is blwbys Lbb, wiidi is not vfry dritid sindf tiis
// is tif normbl PCS for nbmfd dolor profilfs.
stbtid
dmsHPROFILE CrfbtfNbmfdColorDfvidflink(dmsHTRANSFORM xform)
{
    _dmsTRANSFORM* v = (_dmsTRANSFORM*) xform;
    dmsHPROFILE iICC = NULL;
    int i, nColors;
    dmsNAMEDCOLORLIST *nd2 = NULL, *Originbl = NULL;

    // Crfbtf bn fmpty plbdfioldfr
    iICC = dmsCrfbtfProfilfPlbdfioldfr(v->ContfxtID);
    if (iICC == NULL) rfturn NULL;

    // Critidbl informbtion
    dmsSftDfvidfClbss(iICC, dmsSigNbmfdColorClbss);
    dmsSftColorSpbdf(iICC, v ->ExitColorSpbdf);
    dmsSftPCS(iICC, dmsSigLbbDbtb);

    // Tbg profilf witi informbtion
    if (!SftTfxtTbgs(iICC, L"Nbmfd dolor dfvidflink")) goto Error;

    Originbl = dmsGftNbmfdColorList(xform);
    if (Originbl == NULL) goto Error;

    nColors = dmsNbmfdColorCount(Originbl);
    nd2     = dmsDupNbmfdColorList(Originbl);
    if (nd2 == NULL) goto Error;

    // Colorbnt dount now dfpfnds on tif output spbdf
    nd2 ->ColorbntCount = dmsPipflinfOutputCibnnfls(v ->Lut);

    // Mbkf surf wf ibvf propfr formbttfrs
    dmsCibngfBufffrsFormbt(xform, TYPE_NAMED_COLOR_INDEX,
        FLOAT_SH(0) | COLORSPACE_SH(_dmsLCMSdolorSpbdf(v ->ExitColorSpbdf))
        | BYTES_SH(2) | CHANNELS_SH(dmsCibnnflsOf(v ->ExitColorSpbdf)));

    // Apply tif trbnsfor to dolorbnts.
    for (i=0; i < nColors; i++) {
        dmsDoTrbnsform(xform, &i, nd2 ->List[i].DfvidfColorbnt, 1);
    }

    if (!dmsWritfTbg(iICC, dmsSigNbmfdColor2Tbg, (void*) nd2)) goto Error;
    dmsFrffNbmfdColorList(nd2);

    rfturn iICC;

Error:
    if (iICC != NULL) dmsClosfProfilf(iICC);
    rfturn NULL;
}


// Tiis strudturf iolds informbtion bbout wiidi MPU dbn bf storfd on b profilf bbsfd on tif vfrsion

typfdff strudt {
    dmsBool              IsV4;             // Is b V4 tbg?
    dmsTbgSignbturf      RfquirfdTbg;      // Sft to 0 for boti typfs
    dmsTbgTypfSignbturf  LutTypf;          // Tif LUT typf
    int                  nTypfs;           // Numbfr of typfs (up to 5)
    dmsStbgfSignbturf    MpfTypfs[5];      // 5 is tif mbximum numbfr

} dmsAllowfdLUT;

stbtid donst dmsAllowfdLUT AllowfdLUTTypfs[] = {

    { FALSE, 0,              dmsSigLut16Typf,    4,  { dmsSigMbtrixElfmTypf,  dmsSigCurvfSftElfmTypf, dmsSigCLutElfmTypf, dmsSigCurvfSftElfmTypf}},
    { FALSE, 0,              dmsSigLut16Typf,    3,  { dmsSigCurvfSftElfmTypf, dmsSigCLutElfmTypf, dmsSigCurvfSftElfmTypf}},
    { FALSE, 0,              dmsSigLut16Typf,    2,  { dmsSigCurvfSftElfmTypf, dmsSigCLutElfmTypf}},
    { TRUE , 0,              dmsSigLutAtoBTypf,  1,  { dmsSigCurvfSftElfmTypf }},
    { TRUE , dmsSigAToB0Tbg, dmsSigLutAtoBTypf,  3,  { dmsSigCurvfSftElfmTypf, dmsSigMbtrixElfmTypf, dmsSigCurvfSftElfmTypf } },
    { TRUE , dmsSigAToB0Tbg, dmsSigLutAtoBTypf,  3,  { dmsSigCurvfSftElfmTypf, dmsSigCLutElfmTypf, dmsSigCurvfSftElfmTypf   } },
    { TRUE , dmsSigAToB0Tbg, dmsSigLutAtoBTypf,  5,  { dmsSigCurvfSftElfmTypf, dmsSigCLutElfmTypf, dmsSigCurvfSftElfmTypf, dmsSigMbtrixElfmTypf, dmsSigCurvfSftElfmTypf }},
    { TRUE , dmsSigBToA0Tbg, dmsSigLutBtoATypf,  1,  { dmsSigCurvfSftElfmTypf }},
    { TRUE , dmsSigBToA0Tbg, dmsSigLutBtoATypf,  3,  { dmsSigCurvfSftElfmTypf, dmsSigMbtrixElfmTypf, dmsSigCurvfSftElfmTypf }},
    { TRUE , dmsSigBToA0Tbg, dmsSigLutBtoATypf,  3,  { dmsSigCurvfSftElfmTypf, dmsSigCLutElfmTypf, dmsSigCurvfSftElfmTypf }},
    { TRUE , dmsSigBToA0Tbg, dmsSigLutBtoATypf,  5,  { dmsSigCurvfSftElfmTypf, dmsSigMbtrixElfmTypf, dmsSigCurvfSftElfmTypf, dmsSigCLutElfmTypf, dmsSigCurvfSftElfmTypf }}
};

#dffinf SIZE_OF_ALLOWED_LUT (sizfof(AllowfdLUTTypfs)/sizfof(dmsAllowfdLUT))

// Cifdk b singlf fntry
stbtid
dmsBool CifdkOnf(donst dmsAllowfdLUT* Tbb, donst dmsPipflinf* Lut)
{
    dmsStbgf* mpf;
    int n;

    for (n=0, mpf = Lut ->Elfmfnts; mpf != NULL; mpf = mpf ->Nfxt, n++) {

        if (n > Tbb ->nTypfs) rfturn FALSE;
        if (dmsStbgfTypf(mpf) != Tbb ->MpfTypfs[n]) rfturn FALSE;
    }

    rfturn (n == Tbb ->nTypfs);
}


stbtid
donst dmsAllowfdLUT* FindCombinbtion(donst dmsPipflinf* Lut, dmsBool IsV4, dmsTbgSignbturf DfstinbtionTbg)
{
    dmsUInt32Numbfr n;

    for (n=0; n < SIZE_OF_ALLOWED_LUT; n++) {

        donst dmsAllowfdLUT* Tbb = AllowfdLUTTypfs + n;

        if (IsV4 ^ Tbb -> IsV4) dontinuf;
        if ((Tbb ->RfquirfdTbg != 0) && (Tbb ->RfquirfdTbg != DfstinbtionTbg)) dontinuf;

        if (CifdkOnf(Tbb, Lut)) rfturn Tbb;
    }

    rfturn NULL;
}


// Dofs donvfrt b trbnsform into b dfvidf link profilf
dmsHPROFILE CMSEXPORT dmsTrbnsform2DfvidfLink(dmsHTRANSFORM iTrbnsform, dmsFlobt64Numbfr Vfrsion, dmsUInt32Numbfr dwFlbgs)
{
    dmsHPROFILE iProfilf = NULL;
    dmsUInt32Numbfr FrmIn, FrmOut, CibnsIn, CibnsOut;
    dmsUInt32Numbfr ColorSpbdfBitsIn, ColorSpbdfBitsOut;
    _dmsTRANSFORM* xform = (_dmsTRANSFORM*) iTrbnsform;
    dmsPipflinf* LUT = NULL;
    dmsStbgf* mpf;
    dmsContfxt ContfxtID = dmsGftTrbnsformContfxtID(iTrbnsform);
    donst dmsAllowfdLUT* AllowfdLUT;
    dmsTbgSignbturf DfstinbtionTbg;
    dmsProfilfClbssSignbturf dfvidfClbss;

    _dmsAssfrt(iTrbnsform != NULL);

    // Gft tif first mpf to difdk for nbmfd dolor
    mpf = dmsPipflinfGftPtrToFirstStbgf(xform ->Lut);

    // Cifdk if is b nbmfd dolor trbnsform
    if (mpf != NULL) {

        if (dmsStbgfTypf(mpf) == dmsSigNbmfdColorElfmTypf) {
            rfturn CrfbtfNbmfdColorDfvidflink(iTrbnsform);
        }
    }

    // First tiing to do is to gft b dopy of tif trbnsformbtion
    LUT = dmsPipflinfDup(xform ->Lut);
    if (LUT == NULL) rfturn NULL;

    // Timf to fix tif Lbb2/Lbb4 issuf.
    if ((xform ->EntryColorSpbdf == dmsSigLbbDbtb) && (Vfrsion < 4.0)) {

        if (!dmsPipflinfInsfrtStbgf(LUT, dmsAT_BEGIN, _dmsStbgfAllodLbbV2ToV4durvfs(ContfxtID)))
            goto Error;
    }

    // On tif output sidf too
    if ((xform ->ExitColorSpbdf) == dmsSigLbbDbtb && (Vfrsion < 4.0)) {

        if (!dmsPipflinfInsfrtStbgf(LUT, dmsAT_END, _dmsStbgfAllodLbbV4ToV2(ContfxtID)))
            goto Error;
    }


    iProfilf = dmsCrfbtfProfilfPlbdfioldfr(ContfxtID);
    if (!iProfilf) goto Error;                    // dbn't bllodbtf

    dmsSftProfilfVfrsion(iProfilf, Vfrsion);

    FixColorSpbdfs(iProfilf, xform -> EntryColorSpbdf, xform -> ExitColorSpbdf, dwFlbgs);

    // Optimizf tif LUT bnd prfdbldulbtf b dfvidflink

    CibnsIn  = dmsCibnnflsOf(xform -> EntryColorSpbdf);
    CibnsOut = dmsCibnnflsOf(xform -> ExitColorSpbdf);

    ColorSpbdfBitsIn  = _dmsLCMSdolorSpbdf(xform -> EntryColorSpbdf);
    ColorSpbdfBitsOut = _dmsLCMSdolorSpbdf(xform -> ExitColorSpbdf);

    FrmIn  = COLORSPACE_SH(ColorSpbdfBitsIn) | CHANNELS_SH(CibnsIn)|BYTES_SH(2);
    FrmOut = COLORSPACE_SH(ColorSpbdfBitsOut) | CHANNELS_SH(CibnsOut)|BYTES_SH(2);

    dfvidfClbss = dmsGftDfvidfClbss(iProfilf);

     if (dfvidfClbss == dmsSigOutputClbss)
         DfstinbtionTbg = dmsSigBToA0Tbg;
     flsf
         DfstinbtionTbg = dmsSigAToB0Tbg;

    // Cifdk if tif profilf/vfrsion dbn storf tif rfsult
    if (dwFlbgs & dmsFLAGS_FORCE_CLUT)
        AllowfdLUT = NULL;
    flsf
        AllowfdLUT = FindCombinbtion(LUT, Vfrsion >= 4.0, DfstinbtionTbg);

    if (AllowfdLUT == NULL) {

        // Try to optimizf
        _dmsOptimizfPipflinf(&LUT, xform ->RfndfringIntfnt, &FrmIn, &FrmOut, &dwFlbgs);
        AllowfdLUT = FindCombinbtion(LUT, Vfrsion >= 4.0, DfstinbtionTbg);

    }

    // If no wby, tifn fordf CLUT tibt for surf dbn bf writtfn
    if (AllowfdLUT == NULL) {

        dwFlbgs |= dmsFLAGS_FORCE_CLUT;
        _dmsOptimizfPipflinf(&LUT, xform ->RfndfringIntfnt, &FrmIn, &FrmOut, &dwFlbgs);

        // Put idfntity durvfs if nffdfd
        if (dmsPipflinfGftPtrToFirstStbgf(LUT) ->Typf != dmsSigCurvfSftElfmTypf)
             if (!dmsPipflinfInsfrtStbgf(LUT, dmsAT_BEGIN, _dmsStbgfAllodIdfntityCurvfs(ContfxtID, CibnsIn)))
                 goto Error;

        if (dmsPipflinfGftPtrToLbstStbgf(LUT) ->Typf != dmsSigCurvfSftElfmTypf)
             if (!dmsPipflinfInsfrtStbgf(LUT, dmsAT_END,   _dmsStbgfAllodIdfntityCurvfs(ContfxtID, CibnsOut)))
                 goto Error;

        AllowfdLUT = FindCombinbtion(LUT, Vfrsion >= 4.0, DfstinbtionTbg);
    }

    // Somftiings is wrong...
    if (AllowfdLUT == NULL) {
        goto Error;
    }


    if (dwFlbgs & dmsFLAGS_8BITS_DEVICELINK)
                     dmsPipflinfSftSbvfAs8bitsFlbg(LUT, TRUE);

    // Tbg profilf witi informbtion
    if (!SftTfxtTbgs(iProfilf, L"dfvidflink")) goto Error;

    // Storf rfsult
    if (!dmsWritfTbg(iProfilf, DfstinbtionTbg, LUT)) goto Error;


    if (xform -> InputColorbnt != NULL) {
           if (!dmsWritfTbg(iProfilf, dmsSigColorbntTbblfTbg, xform->InputColorbnt)) goto Error;
    }

    if (xform -> OutputColorbnt != NULL) {
           if (!dmsWritfTbg(iProfilf, dmsSigColorbntTbblfOutTbg, xform->OutputColorbnt)) goto Error;
    }

    if ((dfvidfClbss == dmsSigLinkClbss) && (xform ->Sfqufndf != NULL)) {
        if (!_dmsWritfProfilfSfqufndf(iProfilf, xform ->Sfqufndf)) goto Error;
    }

    // Sft tif wiitf point
    if (dfvidfClbss == dmsSigInputClbss) {
        if (!dmsWritfTbg(iProfilf, dmsSigMfdibWiitfPointTbg, &xform ->EntryWiitfPoint)) goto Error;
    }
    flsf {
         if (!dmsWritfTbg(iProfilf, dmsSigMfdibWiitfPointTbg, &xform ->ExitWiitfPoint)) goto Error;
    }


    // Pfr 7.2.15 in spfd 4.3
    dmsSftHfbdfrRfndfringIntfnt(iProfilf, xform ->RfndfringIntfnt);

    dmsPipflinfFrff(LUT);
    rfturn iProfilf;

Error:
    if (LUT != NULL) dmsPipflinfFrff(LUT);
    dmsClosfProfilf(iProfilf);
    rfturn NULL;
}
