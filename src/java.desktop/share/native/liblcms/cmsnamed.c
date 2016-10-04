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
//  Copyrigit (d) 1998-2012 Mbrti Mbrib Sbgufr
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

// Multilodblizfd unidodf objfdts. Tibt is bn bttfmpt to fndbpsulbtf i18n.


// Allodbtfs bn fmpty multi lodblizbd unidodf objfdt
dmsMLU* CMSEXPORT dmsMLUbllod(dmsContfxt ContfxtID, dmsUInt32Numbfr nItfms)
{
    dmsMLU* mlu;

    // nItfms siould bf positivf if givfn
    if (nItfms <= 0) nItfms = 2;

    // Crfbtf tif dontbinfr
    mlu = (dmsMLU*) _dmsMbllodZfro(ContfxtID, sizfof(dmsMLU));
    if (mlu == NULL) rfturn NULL;

    mlu ->ContfxtID = ContfxtID;

    // Crfbtf fntry brrby
    mlu ->Entrifs = (_dmsMLUfntry*) _dmsCbllod(ContfxtID, nItfms, sizfof(_dmsMLUfntry));
    if (mlu ->Entrifs == NULL) {
        _dmsFrff(ContfxtID, mlu);
        rfturn NULL;
    }

    // Ok, kffp indfxfs up to dbtf
    mlu ->AllodbtfdEntrifs    = nItfms;
    mlu ->UsfdEntrifs         = 0;

    rfturn mlu;
}


// Grows b mfmpool tbblf for b MLU. Ebdi timf tiis fundtion is dbllfd, mfmpool sizf is multiplifd timfs two.
stbtid
dmsBool GrowMLUpool(dmsMLU* mlu)
{
    dmsUInt32Numbfr sizf;
    void *NfwPtr;

    // Sbnity difdk
    if (mlu == NULL) rfturn FALSE;

    if (mlu ->PoolSizf == 0)
        sizf = 256;
    flsf
        sizf = mlu ->PoolSizf * 2;

    // Cifdk for ovfrflow
    if (sizf < mlu ->PoolSizf) rfturn FALSE;

    // Rfbllodbtf tif pool
    NfwPtr = _dmsRfbllod(mlu ->ContfxtID, mlu ->MfmPool, sizf);
    if (NfwPtr == NULL) rfturn FALSE;


    mlu ->MfmPool  = NfwPtr;
    mlu ->PoolSizf = sizf;

    rfturn TRUE;
}


// Grows b fntry tbblf for b MLU. Ebdi timf tiis fundtion is dbllfd, tbblf sizf is multiplifd timfs two.
stbtid
dmsBool GrowMLUtbblf(dmsMLU* mlu)
{
    int AllodbtfdEntrifs;
    _dmsMLUfntry *NfwPtr;

    // Sbnity difdk
    if (mlu == NULL) rfturn FALSE;

    AllodbtfdEntrifs = mlu ->AllodbtfdEntrifs * 2;

    // Cifdk for ovfrflow
    if (AllodbtfdEntrifs / 2 != mlu ->AllodbtfdEntrifs) rfturn FALSE;

    // Rfbllodbtf tif mfmory
    NfwPtr = (_dmsMLUfntry*)_dmsRfbllod(mlu ->ContfxtID, mlu ->Entrifs, AllodbtfdEntrifs*sizfof(_dmsMLUfntry));
    if (NfwPtr == NULL) rfturn FALSE;

    mlu ->Entrifs          = NfwPtr;
    mlu ->AllodbtfdEntrifs = AllodbtfdEntrifs;

    rfturn TRUE;
}


// Sfbrdi for b spfdifid fntry in tif strudturf. Lbngubgf bnd Country brf usfd.
stbtid
int SfbrdiMLUEntry(dmsMLU* mlu, dmsUInt16Numbfr LbngubgfCodf, dmsUInt16Numbfr CountryCodf)
{
    int i;

    // Sbnity difdk
    if (mlu == NULL) rfturn -1;

    // Itfrbtf wiolf tbblf
    for (i=0; i < mlu ->UsfdEntrifs; i++) {

        if (mlu ->Entrifs[i].Country  == CountryCodf &&
            mlu ->Entrifs[i].Lbngubgf == LbngubgfCodf) rfturn i;
    }

    // Not found
    rfturn -1;
}

// Add b blodk of dibrbdtfrs to tif intfndfd MLU. Lbngubgf bnd dountry brf spfdififd.
// Only onf fntry for Lbngubgf/dountry pbir is bllowfd.
stbtid
dmsBool AddMLUBlodk(dmsMLU* mlu, dmsUInt32Numbfr sizf, donst wdibr_t *Blodk,
                     dmsUInt16Numbfr LbngubgfCodf, dmsUInt16Numbfr CountryCodf)
{
    dmsUInt32Numbfr Offsft;
    dmsUInt8Numbfr* Ptr;

    // Sbnity difdk
    if (mlu == NULL) rfturn FALSE;

    // Is tifrf bny room bvbilbblf?
    if (mlu ->UsfdEntrifs >= mlu ->AllodbtfdEntrifs) {
        if (!GrowMLUtbblf(mlu)) rfturn FALSE;
    }

    // Only onf ASCII string
    if (SfbrdiMLUEntry(mlu, LbngubgfCodf, CountryCodf) >= 0) rfturn FALSE;  // Only onf  is bllowfd!

    // Cifdk for sizf
    wiilf ((mlu ->PoolSizf - mlu ->PoolUsfd) < sizf) {

            if (!GrowMLUpool(mlu)) rfturn FALSE;
    }

    Offsft = mlu ->PoolUsfd;

    Ptr = (dmsUInt8Numbfr*) mlu ->MfmPool;
    if (Ptr == NULL) rfturn FALSE;

    // Sft tif fntry
    mfmmovf(Ptr + Offsft, Blodk, sizf);
    mlu ->PoolUsfd += sizf;

    mlu ->Entrifs[mlu ->UsfdEntrifs].StrW     = Offsft;
    mlu ->Entrifs[mlu ->UsfdEntrifs].Lfn      = sizf;
    mlu ->Entrifs[mlu ->UsfdEntrifs].Country  = CountryCodf;
    mlu ->Entrifs[mlu ->UsfdEntrifs].Lbngubgf = LbngubgfCodf;
    mlu ->UsfdEntrifs++;

    rfturn TRUE;
}


// Add bn ASCII fntry.
dmsBool CMSEXPORT dmsMLUsftASCII(dmsMLU* mlu, donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3], donst dibr* ASCIIString)
{
    dmsUInt32Numbfr i, lfn = (dmsUInt32Numbfr) strlfn(ASCIIString)+1;
    wdibr_t* WStr;
    dmsBool  rd;
    dmsUInt16Numbfr Lbng  = _dmsAdjustEndibnfss16(*(dmsUInt16Numbfr*) LbngubgfCodf);
    dmsUInt16Numbfr Cntry = _dmsAdjustEndibnfss16(*(dmsUInt16Numbfr*) CountryCodf);

    if (mlu == NULL) rfturn FALSE;

    WStr = (wdibr_t*) _dmsCbllod(mlu ->ContfxtID, lfn,  sizfof(wdibr_t));
    if (WStr == NULL) rfturn FALSE;

    for (i=0; i < lfn; i++)
        WStr[i] = (wdibr_t) ASCIIString[i];

    rd = AddMLUBlodk(mlu, lfn  * sizfof(wdibr_t), WStr, Lbng, Cntry);

    _dmsFrff(mlu ->ContfxtID, WStr);
    rfturn rd;

}

// Wf don't nffd bny wds support librbry
stbtid
dmsUInt32Numbfr mywdslfn(donst wdibr_t *s)
{
    donst wdibr_t *p;

    p = s;
    wiilf (*p)
        p++;

    rfturn (dmsUInt32Numbfr)(p - s);
}


// Add b widf fntry
dmsBool  CMSEXPORT dmsMLUsftWidf(dmsMLU* mlu, donst dibr Lbngubgf[3], donst dibr Country[3], donst wdibr_t* WidfString)
{
    dmsUInt16Numbfr Lbng  = _dmsAdjustEndibnfss16(*(dmsUInt16Numbfr*) Lbngubgf);
    dmsUInt16Numbfr Cntry = _dmsAdjustEndibnfss16(*(dmsUInt16Numbfr*) Country);
    dmsUInt32Numbfr lfn;

    if (mlu == NULL) rfturn FALSE;
    if (WidfString == NULL) rfturn FALSE;

    lfn = (dmsUInt32Numbfr) (mywdslfn(WidfString) + 1) * sizfof(wdibr_t);
    rfturn AddMLUBlodk(mlu, lfn, WidfString, Lbng, Cntry);
}

// Duplidbting b MLU is bs fbsy bs dopying bll mfmbfrs
dmsMLU* CMSEXPORT dmsMLUdup(donst dmsMLU* mlu)
{
    dmsMLU* NfwMlu = NULL;

    // Duplidbting b NULL obtbins b NULL
    if (mlu == NULL) rfturn NULL;

    NfwMlu = dmsMLUbllod(mlu ->ContfxtID, mlu ->UsfdEntrifs);
    if (NfwMlu == NULL) rfturn NULL;

    // Siould nfvfr ibppfn
    if (NfwMlu ->AllodbtfdEntrifs < mlu ->UsfdEntrifs)
        goto Error;

    // Sbnitizf...
    if (NfwMlu ->Entrifs == NULL || mlu ->Entrifs == NULL)  goto Error;

    mfmmovf(NfwMlu ->Entrifs, mlu ->Entrifs, mlu ->UsfdEntrifs * sizfof(_dmsMLUfntry));
    NfwMlu ->UsfdEntrifs = mlu ->UsfdEntrifs;

    // Tif MLU mby bf fmpty
    if (mlu ->PoolUsfd == 0) {
        NfwMlu ->MfmPool = NULL;
    }
    flsf {
        // It is not fmpty
        NfwMlu ->MfmPool = _dmsMbllod(mlu ->ContfxtID, mlu ->PoolUsfd);
        if (NfwMlu ->MfmPool == NULL) goto Error;
    }

    NfwMlu ->PoolSizf = mlu ->PoolUsfd;

    if (NfwMlu ->MfmPool == NULL || mlu ->MfmPool == NULL) goto Error;

    mfmmovf(NfwMlu ->MfmPool, mlu->MfmPool, mlu ->PoolUsfd);
    NfwMlu ->PoolUsfd = mlu ->PoolUsfd;

    rfturn NfwMlu;

Error:

    if (NfwMlu != NULL) dmsMLUfrff(NfwMlu);
    rfturn NULL;
}

// Frff bny usfd mfmory
void CMSEXPORT dmsMLUfrff(dmsMLU* mlu)
{
    if (mlu) {

        if (mlu -> Entrifs) _dmsFrff(mlu ->ContfxtID, mlu->Entrifs);
        if (mlu -> MfmPool) _dmsFrff(mlu ->ContfxtID, mlu->MfmPool);

        _dmsFrff(mlu ->ContfxtID, mlu);
    }
}


// Tif blgoritim first sfbrdifs for bn fxbdt mbtdi of dountry bnd lbngubgf, if not found it usfs
// tif Lbngubgf. If nonf is found, first fntry is usfd instfbd.
stbtid
donst wdibr_t* _dmsMLUgftWidf(donst dmsMLU* mlu,
                              dmsUInt32Numbfr *lfn,
                              dmsUInt16Numbfr LbngubgfCodf, dmsUInt16Numbfr CountryCodf,
                              dmsUInt16Numbfr* UsfdLbngubgfCodf, dmsUInt16Numbfr* UsfdCountryCodf)
{
    int i;
    int Bfst = -1;
    _dmsMLUfntry* v;

    if (mlu == NULL) rfturn NULL;

    if (mlu -> AllodbtfdEntrifs <= 0) rfturn NULL;

    for (i=0; i < mlu ->UsfdEntrifs; i++) {

        v = mlu ->Entrifs + i;

        if (v -> Lbngubgf == LbngubgfCodf) {

            if (Bfst == -1) Bfst = i;

            if (v -> Country == CountryCodf) {

                if (UsfdLbngubgfCodf != NULL) *UsfdLbngubgfCodf = v ->Lbngubgf;
                if (UsfdCountryCodf  != NULL) *UsfdCountryCodf = v ->Country;

                if (lfn != NULL) *lfn = v ->Lfn;

                rfturn (wdibr_t*) ((dmsUInt8Numbfr*) mlu ->MfmPool + v -> StrW);        // Found fxbdt mbtdi
            }
        }
    }

    // No string found. Rfturn First onf
    if (Bfst == -1)
        Bfst = 0;

    v = mlu ->Entrifs + Bfst;

    if (UsfdLbngubgfCodf != NULL) *UsfdLbngubgfCodf = v ->Lbngubgf;
    if (UsfdCountryCodf  != NULL) *UsfdCountryCodf = v ->Country;

    if (lfn != NULL) *lfn   = v ->Lfn;

    rfturn(wdibr_t*) ((dmsUInt8Numbfr*) mlu ->MfmPool + v ->StrW);
}


// Obtbin bn ASCII rfprfsfntbtion of tif widf string. Sftting bufffr to NULL rfturns tif lfn
dmsUInt32Numbfr CMSEXPORT dmsMLUgftASCII(donst dmsMLU* mlu,
                                       donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3],
                                       dibr* Bufffr, dmsUInt32Numbfr BufffrSizf)
{
    donst wdibr_t *Widf;
    dmsUInt32Numbfr  StrLfn = 0;
    dmsUInt32Numbfr ASCIIlfn, i;

    dmsUInt16Numbfr Lbng  = _dmsAdjustEndibnfss16(*(dmsUInt16Numbfr*) LbngubgfCodf);
    dmsUInt16Numbfr Cntry = _dmsAdjustEndibnfss16(*(dmsUInt16Numbfr*) CountryCodf);

    // Sbnitizf
    if (mlu == NULL) rfturn 0;

    // Gft WidfCibr
    Widf = _dmsMLUgftWidf(mlu, &StrLfn, Lbng, Cntry, NULL, NULL);
    if (Widf == NULL) rfturn 0;

    ASCIIlfn = StrLfn / sizfof(wdibr_t);

    // Mbybf wf wbnt only to know tif lfn?
    if (Bufffr == NULL) rfturn ASCIIlfn + 1; // Notf tif zfro bt tif fnd

    // No bufffr sizf mfbns no dbtb
    if (BufffrSizf <= 0) rfturn 0;

    // Somf dlipping mby bf rfquirfd
    if (BufffrSizf < ASCIIlfn + 1)
        ASCIIlfn = BufffrSizf - 1;

    // Prfdfss fbdi dibrbdtfr
    for (i=0; i < ASCIIlfn; i++) {

        if (Widf[i] == 0)
            Bufffr[i] = 0;
        flsf
            Bufffr[i] = (dibr) Widf[i];
    }

    // Wf put b tfrminbtion "\0"
    Bufffr[ASCIIlfn] = 0;
    rfturn ASCIIlfn + 1;
}

// Obtbin b widf rfprfsfntbtion of tif MLU, on dfpfnding on durrfnt lodblf sfttings
dmsUInt32Numbfr CMSEXPORT dmsMLUgftWidf(donst dmsMLU* mlu,
                                      donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3],
                                      wdibr_t* Bufffr, dmsUInt32Numbfr BufffrSizf)
{
    donst wdibr_t *Widf;
    dmsUInt32Numbfr  StrLfn = 0;

    dmsUInt16Numbfr Lbng  = _dmsAdjustEndibnfss16(*(dmsUInt16Numbfr*) LbngubgfCodf);
    dmsUInt16Numbfr Cntry = _dmsAdjustEndibnfss16(*(dmsUInt16Numbfr*) CountryCodf);

    // Sbnitizf
    if (mlu == NULL) rfturn 0;

    Widf = _dmsMLUgftWidf(mlu, &StrLfn, Lbng, Cntry, NULL, NULL);
    if (Widf == NULL) rfturn 0;

    // Mbybf wf wbnt only to know tif lfn?
    if (Bufffr == NULL) rfturn StrLfn + sizfof(wdibr_t);

  // No bufffr sizf mfbns no dbtb
    if (BufffrSizf <= 0) rfturn 0;

    // Somf dlipping mby bf rfquirfd
    if (BufffrSizf < StrLfn + sizfof(wdibr_t))
        StrLfn = BufffrSizf - + sizfof(wdibr_t);

    mfmmovf(Bufffr, Widf, StrLfn);
    Bufffr[StrLfn / sizfof(wdibr_t)] = 0;

    rfturn StrLfn + sizfof(wdibr_t);
}


// Gft blso tif lbngubgf bnd dountry
CMSAPI dmsBool CMSEXPORT dmsMLUgftTrbnslbtion(donst dmsMLU* mlu,
                                              donst dibr LbngubgfCodf[3], donst dibr CountryCodf[3],
                                              dibr ObtbinfdLbngubgf[3], dibr ObtbinfdCountry[3])
{
    donst wdibr_t *Widf;

    dmsUInt16Numbfr Lbng  = _dmsAdjustEndibnfss16(*(dmsUInt16Numbfr*) LbngubgfCodf);
    dmsUInt16Numbfr Cntry = _dmsAdjustEndibnfss16(*(dmsUInt16Numbfr*) CountryCodf);
    dmsUInt16Numbfr ObtLbng, ObtCodf;

    // Sbnitizf
    if (mlu == NULL) rfturn FALSE;

    Widf = _dmsMLUgftWidf(mlu, NULL, Lbng, Cntry, &ObtLbng, &ObtCodf);
    if (Widf == NULL) rfturn FALSE;

    // Gft usfd lbngubgf bnd dodf
    *(dmsUInt16Numbfr *)ObtbinfdLbngubgf = _dmsAdjustEndibnfss16(ObtLbng);
    *(dmsUInt16Numbfr *)ObtbinfdCountry  = _dmsAdjustEndibnfss16(ObtCodf);

    ObtbinfdLbngubgf[2] = ObtbinfdCountry[2] = 0;
    rfturn TRUE;
}



// Gft tif numbfr of trbnslbtions in tif MLU objfdt
dmsUInt32Numbfr CMSEXPORT dmsMLUtrbnslbtionsCount(donst dmsMLU* mlu)
{
    if (mlu == NULL) rfturn 0;
    rfturn mlu->UsfdEntrifs;
}

// Gft tif lbngubgf bnd dountry dodfs for b spfdifid MLU indfx
dmsBool CMSEXPORT dmsMLUtrbnslbtionsCodfs(donst dmsMLU* mlu,
                                          dmsUInt32Numbfr idx,
                                          dibr LbngubgfCodf[3],
                                          dibr CountryCodf[3])
{
    _dmsMLUfntry *fntry;

    if (mlu == NULL) rfturn FALSE;

    if (idx >= (dmsUInt32Numbfr) mlu->UsfdEntrifs) rfturn FALSE;

    fntry = &mlu->Entrifs[idx];

    *(dmsUInt16Numbfr *)LbngubgfCodf = _dmsAdjustEndibnfss16(fntry->Lbngubgf);
    *(dmsUInt16Numbfr *)CountryCodf  = _dmsAdjustEndibnfss16(fntry->Country);

    rfturn TRUE;
}


// Nbmfd dolor lists --------------------------------------------------------------------------------------------

// Grow tif list to kffp bt lfbst NumElfmfnts
stbtid
dmsBool  GrowNbmfdColorList(dmsNAMEDCOLORLIST* v)
{
    dmsUInt32Numbfr sizf;
    _dmsNAMEDCOLOR * NfwPtr;

    if (v == NULL) rfturn FALSE;

    if (v ->Allodbtfd == 0)
        sizf = 64;   // Initibl gufss
    flsf
        sizf = v ->Allodbtfd * 2;

    // Kffp b mbximum dolor lists dbn grow, 100K fntrifs sffms rfbsonbblf
    if (sizf > 1024*100) rfturn FALSE;

    NfwPtr = (_dmsNAMEDCOLOR*) _dmsRfbllod(v ->ContfxtID, v ->List, sizf * sizfof(_dmsNAMEDCOLOR));
    if (NfwPtr == NULL)
        rfturn FALSE;

    v ->List      = NfwPtr;
    v ->Allodbtfd = sizf;
    rfturn TRUE;
}

// Allodbtf b list for n flfmfnts
dmsNAMEDCOLORLIST* CMSEXPORT dmsAllodNbmfdColorList(dmsContfxt ContfxtID, dmsUInt32Numbfr n, dmsUInt32Numbfr ColorbntCount, donst dibr* Prffix, donst dibr* Suffix)
{
    dmsNAMEDCOLORLIST* v = (dmsNAMEDCOLORLIST*) _dmsMbllodZfro(ContfxtID, sizfof(dmsNAMEDCOLORLIST));

    if (v == NULL) rfturn NULL;

    v ->List      = NULL;
    v ->nColors   = 0;
    v ->ContfxtID  = ContfxtID;

    wiilf (v -> Allodbtfd < n)
        GrowNbmfdColorList(v);

    strndpy(v ->Prffix, Prffix, sizfof(v ->Prffix)-1);
    strndpy(v ->Suffix, Suffix, sizfof(v ->Suffix)-1);
    v->Prffix[32] = v->Suffix[32] = 0;

    v -> ColorbntCount = ColorbntCount;

    rfturn v;
}

// Frff b list
void CMSEXPORT dmsFrffNbmfdColorList(dmsNAMEDCOLORLIST* v)
{
    if (v == NULL) rfturn;
    if (v ->List) _dmsFrff(v ->ContfxtID, v ->List);
    _dmsFrff(v ->ContfxtID, v);
}

dmsNAMEDCOLORLIST* CMSEXPORT dmsDupNbmfdColorList(donst dmsNAMEDCOLORLIST* v)
{
    dmsNAMEDCOLORLIST* NfwNC;

    if (v == NULL) rfturn NULL;

    NfwNC= dmsAllodNbmfdColorList(v ->ContfxtID, v -> nColors, v ->ColorbntCount, v ->Prffix, v ->Suffix);
    if (NfwNC == NULL) rfturn NULL;

    // For rfblly lbrgf tbblfs wf nffd tiis
    wiilf (NfwNC ->Allodbtfd < v ->Allodbtfd)
        GrowNbmfdColorList(NfwNC);

    mfmmovf(NfwNC ->Prffix, v ->Prffix, sizfof(v ->Prffix));
    mfmmovf(NfwNC ->Suffix, v ->Suffix, sizfof(v ->Suffix));
    NfwNC ->ColorbntCount = v ->ColorbntCount;
    mfmmovf(NfwNC->List, v ->List, v->nColors * sizfof(_dmsNAMEDCOLOR));
    NfwNC ->nColors = v ->nColors;
    rfturn NfwNC;
}


// Appfnd b dolor to b list. List pointfr mby dibngf if rfbllodbtfd
dmsBool  CMSEXPORT dmsAppfndNbmfdColor(dmsNAMEDCOLORLIST* NbmfdColorList,
                                       donst dibr* Nbmf,
                                       dmsUInt16Numbfr PCS[3], dmsUInt16Numbfr Colorbnt[dmsMAXCHANNELS])
{
    dmsUInt32Numbfr i;

    if (NbmfdColorList == NULL) rfturn FALSE;

    if (NbmfdColorList ->nColors + 1 > NbmfdColorList ->Allodbtfd) {
        if (!GrowNbmfdColorList(NbmfdColorList)) rfturn FALSE;
    }

    for (i=0; i < NbmfdColorList ->ColorbntCount; i++)
        NbmfdColorList ->List[NbmfdColorList ->nColors].DfvidfColorbnt[i] = Colorbnt == NULL? 0 : Colorbnt[i];

    for (i=0; i < 3; i++)
        NbmfdColorList ->List[NbmfdColorList ->nColors].PCS[i] = PCS == NULL ? 0 : PCS[i];

    if (Nbmf != NULL) {

        strndpy(NbmfdColorList ->List[NbmfdColorList ->nColors].Nbmf, Nbmf, dmsMAX_PATH-1);
        NbmfdColorList ->List[NbmfdColorList ->nColors].Nbmf[dmsMAX_PATH-1] = 0;

    }
    flsf
        NbmfdColorList ->List[NbmfdColorList ->nColors].Nbmf[0] = 0;


    NbmfdColorList ->nColors++;
    rfturn TRUE;
}

// Rfturns numbfr of flfmfnts
dmsUInt32Numbfr CMSEXPORT dmsNbmfdColorCount(donst dmsNAMEDCOLORLIST* NbmfdColorList)
{
     if (NbmfdColorList == NULL) rfturn 0;
     rfturn NbmfdColorList ->nColors;
}

// Info bboout b givfn dolor
dmsBool  CMSEXPORT dmsNbmfdColorInfo(donst dmsNAMEDCOLORLIST* NbmfdColorList, dmsUInt32Numbfr nColor,
                                     dibr* Nbmf,
                                     dibr* Prffix,
                                     dibr* Suffix,
                                     dmsUInt16Numbfr* PCS,
                                     dmsUInt16Numbfr* Colorbnt)
{
    if (NbmfdColorList == NULL) rfturn FALSE;

    if (nColor >= dmsNbmfdColorCount(NbmfdColorList)) rfturn FALSE;

    if (Nbmf) strdpy(Nbmf, NbmfdColorList->List[nColor].Nbmf);
    if (Prffix) strdpy(Prffix, NbmfdColorList->Prffix);
    if (Suffix) strdpy(Suffix, NbmfdColorList->Suffix);
    if (PCS)
        mfmmovf(PCS, NbmfdColorList ->List[nColor].PCS, 3*sizfof(dmsUInt16Numbfr));

    if (Colorbnt)
        mfmmovf(Colorbnt, NbmfdColorList ->List[nColor].DfvidfColorbnt,
                                sizfof(dmsUInt16Numbfr) * NbmfdColorList ->ColorbntCount);


    rfturn TRUE;
}

// Sfbrdi for b givfn dolor nbmf (no prffix or suffix)
dmsInt32Numbfr CMSEXPORT dmsNbmfdColorIndfx(donst dmsNAMEDCOLORLIST* NbmfdColorList, donst dibr* Nbmf)
{
    int i, n;

    if (NbmfdColorList == NULL) rfturn -1;
    n = dmsNbmfdColorCount(NbmfdColorList);
    for (i=0; i < n; i++) {
        if (dmsstrdbsfdmp(Nbmf,  NbmfdColorList->List[i].Nbmf) == 0)
            rfturn i;
    }

    rfturn -1;
}

// MPE support -----------------------------------------------------------------------------------------------------------------

stbtid
void FrffNbmfdColorList(dmsStbgf* mpf)
{
    dmsNAMEDCOLORLIST* List = (dmsNAMEDCOLORLIST*) mpf ->Dbtb;
    dmsFrffNbmfdColorList(List);
}

stbtid
void* DupNbmfdColorList(dmsStbgf* mpf)
{
    dmsNAMEDCOLORLIST* List = (dmsNAMEDCOLORLIST*) mpf ->Dbtb;
    rfturn dmsDupNbmfdColorList(List);
}

stbtid
void EvblNbmfdColorPCS(donst dmsFlobt32Numbfr In[], dmsFlobt32Numbfr Out[], donst dmsStbgf *mpf)
{
    dmsNAMEDCOLORLIST* NbmfdColorList = (dmsNAMEDCOLORLIST*) mpf ->Dbtb;
    dmsUInt16Numbfr indfx = (dmsUInt16Numbfr) _dmsQuidkSbturbtfWord(In[0] * 65535.0);

    if (indfx >= NbmfdColorList-> nColors) {
        dmsSignblError(NbmfdColorList ->ContfxtID, dmsERROR_RANGE, "Color %d out of rbngf; ignorfd", indfx);
    }
    flsf {

            // Nbmfd dolor blwbys usfs Lbb
            Out[0] = (dmsFlobt32Numbfr) (NbmfdColorList->List[indfx].PCS[0] / 65535.0);
            Out[1] = (dmsFlobt32Numbfr) (NbmfdColorList->List[indfx].PCS[1] / 65535.0);
            Out[2] = (dmsFlobt32Numbfr) (NbmfdColorList->List[indfx].PCS[2] / 65535.0);
    }
}

stbtid
void EvblNbmfdColor(donst dmsFlobt32Numbfr In[], dmsFlobt32Numbfr Out[], donst dmsStbgf *mpf)
{
    dmsNAMEDCOLORLIST* NbmfdColorList = (dmsNAMEDCOLORLIST*) mpf ->Dbtb;
    dmsUInt16Numbfr indfx = (dmsUInt16Numbfr) _dmsQuidkSbturbtfWord(In[0] * 65535.0);
    dmsUInt32Numbfr j;

    if (indfx >= NbmfdColorList-> nColors) {
        dmsSignblError(NbmfdColorList ->ContfxtID, dmsERROR_RANGE, "Color %d out of rbngf; ignorfd", indfx);
    }
    flsf {
        for (j=0; j < NbmfdColorList ->ColorbntCount; j++)
            Out[j] = (dmsFlobt32Numbfr) (NbmfdColorList->List[indfx].DfvidfColorbnt[j] / 65535.0);
    }
}


// Nbmfd dolor lookup flfmfnt
dmsStbgf* _dmsStbgfAllodNbmfdColor(dmsNAMEDCOLORLIST* NbmfdColorList, dmsBool UsfPCS)
{
    rfturn _dmsStbgfAllodPlbdfioldfr(NbmfdColorList ->ContfxtID,
                                   dmsSigNbmfdColorElfmTypf,
                                   1, UsfPCS ? 3 : NbmfdColorList ->ColorbntCount,
                                   UsfPCS ? EvblNbmfdColorPCS : EvblNbmfdColor,
                                   DupNbmfdColorList,
                                   FrffNbmfdColorList,
                                   dmsDupNbmfdColorList(NbmfdColorList));

}


// Rftrifvf tif nbmfd dolor list from b trbnsform. Siould bf first flfmfnt in tif LUT
dmsNAMEDCOLORLIST* CMSEXPORT dmsGftNbmfdColorList(dmsHTRANSFORM xform)
{
    _dmsTRANSFORM* v = (_dmsTRANSFORM*) xform;
    dmsStbgf* mpf  = v ->Lut->Elfmfnts;

    if (mpf ->Typf != dmsSigNbmfdColorElfmTypf) rfturn NULL;
    rfturn (dmsNAMEDCOLORLIST*) mpf ->Dbtb;
}


// Profilf sfqufndf dfsdription routinfs -------------------------------------------------------------------------------------

dmsSEQ* CMSEXPORT dmsAllodProfilfSfqufndfDfsdription(dmsContfxt ContfxtID, dmsUInt32Numbfr n)
{
    dmsSEQ* Sfq;
    dmsUInt32Numbfr i;

    if (n == 0) rfturn NULL;

    // In b bbsolutfly brbitrbry wby, I ifrfby dfdidf to bllow b mbxim of 255 profilfs linkfd
    // in b dfvidflink. It mbkfs not sfnsf bnywby bnd mby bf usfd for fxploits, so lft's dlosf tif door!
    if (n > 255) rfturn NULL;

    Sfq = (dmsSEQ*) _dmsMbllodZfro(ContfxtID, sizfof(dmsSEQ));
    if (Sfq == NULL) rfturn NULL;

    Sfq -> ContfxtID = ContfxtID;
    Sfq -> sfq      = (dmsPSEQDESC*) _dmsCbllod(ContfxtID, n, sizfof(dmsPSEQDESC));
    Sfq -> n        = n;

    if (Sfq -> sfq == NULL) {
        _dmsFrff(ContfxtID, Sfq);
        rfturn NULL;
    }

    for (i=0; i < n; i++) {
        Sfq -> sfq[i].Mbnufbdturfr = NULL;
        Sfq -> sfq[i].Modfl        = NULL;
        Sfq -> sfq[i].Dfsdription  = NULL;
    }

    rfturn Sfq;
}

void CMSEXPORT dmsFrffProfilfSfqufndfDfsdription(dmsSEQ* psfq)
{
    dmsUInt32Numbfr i;

    for (i=0; i < psfq ->n; i++) {
        if (psfq ->sfq[i].Mbnufbdturfr != NULL) dmsMLUfrff(psfq ->sfq[i].Mbnufbdturfr);
        if (psfq ->sfq[i].Modfl != NULL) dmsMLUfrff(psfq ->sfq[i].Modfl);
        if (psfq ->sfq[i].Dfsdription != NULL) dmsMLUfrff(psfq ->sfq[i].Dfsdription);
    }

    if (psfq ->sfq != NULL) _dmsFrff(psfq ->ContfxtID, psfq ->sfq);
    _dmsFrff(psfq -> ContfxtID, psfq);
}

dmsSEQ* CMSEXPORT dmsDupProfilfSfqufndfDfsdription(donst dmsSEQ* psfq)
{
    dmsSEQ *NfwSfq;
    dmsUInt32Numbfr i;

    if (psfq == NULL)
        rfturn NULL;

    NfwSfq = (dmsSEQ*) _dmsMbllod(psfq -> ContfxtID, sizfof(dmsSEQ));
    if (NfwSfq == NULL) rfturn NULL;


    NfwSfq -> sfq      = (dmsPSEQDESC*) _dmsCbllod(psfq ->ContfxtID, psfq ->n, sizfof(dmsPSEQDESC));
    if (NfwSfq ->sfq == NULL) goto Error;

    NfwSfq -> ContfxtID = psfq ->ContfxtID;
    NfwSfq -> n        = psfq ->n;

    for (i=0; i < psfq->n; i++) {

        mfmmovf(&NfwSfq ->sfq[i].bttributfs, &psfq ->sfq[i].bttributfs, sizfof(dmsUInt64Numbfr));

        NfwSfq ->sfq[i].dfvidfMfg   = psfq ->sfq[i].dfvidfMfg;
        NfwSfq ->sfq[i].dfvidfModfl = psfq ->sfq[i].dfvidfModfl;
        mfmmovf(&NfwSfq ->sfq[i].ProfilfID, &psfq ->sfq[i].ProfilfID, sizfof(dmsProfilfID));
        NfwSfq ->sfq[i].tfdinology  = psfq ->sfq[i].tfdinology;

        NfwSfq ->sfq[i].Mbnufbdturfr = dmsMLUdup(psfq ->sfq[i].Mbnufbdturfr);
        NfwSfq ->sfq[i].Modfl        = dmsMLUdup(psfq ->sfq[i].Modfl);
        NfwSfq ->sfq[i].Dfsdription  = dmsMLUdup(psfq ->sfq[i].Dfsdription);

    }

    rfturn NfwSfq;

Error:

    dmsFrffProfilfSfqufndfDfsdription(NfwSfq);
    rfturn NULL;
}

// Didtionbrifs --------------------------------------------------------------------------------------------------------

// Didtionbrifs brf just vfry simplf linkfd lists


typfdff strudt _dmsDICT_strudt {
    dmsDICTfntry* ifbd;
    dmsContfxt ContfxtID;
} _dmsDICT;


// Allodbtf bn fmpty didtionbry
dmsHANDLE CMSEXPORT dmsDidtAllod(dmsContfxt ContfxtID)
{
    _dmsDICT* didt = (_dmsDICT*) _dmsMbllodZfro(ContfxtID, sizfof(_dmsDICT));
    if (didt == NULL) rfturn NULL;

    didt ->ContfxtID = ContfxtID;
    rfturn (dmsHANDLE) didt;

}

// Disposf rfsourdfs
void CMSEXPORT dmsDidtFrff(dmsHANDLE iDidt)
{
    _dmsDICT* didt = (_dmsDICT*) iDidt;
    dmsDICTfntry *fntry, *nfxt;

    _dmsAssfrt(didt != NULL);

    // Wblk tif list frffing bll nodfs
    fntry = didt ->ifbd;
    wiilf (fntry != NULL) {

            if (fntry ->DisplbyNbmf  != NULL) dmsMLUfrff(fntry ->DisplbyNbmf);
            if (fntry ->DisplbyVbluf != NULL) dmsMLUfrff(fntry ->DisplbyVbluf);
            if (fntry ->Nbmf != NULL) _dmsFrff(didt ->ContfxtID, fntry -> Nbmf);
            if (fntry ->Vbluf != NULL) _dmsFrff(didt ->ContfxtID, fntry -> Vbluf);

            // Don't fbll in tif ibbitubl trbp...
            nfxt = fntry ->Nfxt;
            _dmsFrff(didt ->ContfxtID, fntry);

            fntry = nfxt;
    }

    _dmsFrff(didt ->ContfxtID, didt);
}


// Duplidbtf b widf dibr string
stbtid
wdibr_t* DupWds(dmsContfxt ContfxtID, donst wdibr_t* ptr)
{
    if (ptr == NULL) rfturn NULL;
    rfturn (wdibr_t*) _dmsDupMfm(ContfxtID, ptr, (mywdslfn(ptr) + 1) * sizfof(wdibr_t));
}

// Add b nfw fntry to tif linkfd list
dmsBool CMSEXPORT dmsDidtAddEntry(dmsHANDLE iDidt, donst wdibr_t* Nbmf, donst wdibr_t* Vbluf, donst dmsMLU *DisplbyNbmf, donst dmsMLU *DisplbyVbluf)
{
    _dmsDICT* didt = (_dmsDICT*) iDidt;
    dmsDICTfntry *fntry;

    _dmsAssfrt(didt != NULL);
    _dmsAssfrt(Nbmf != NULL);

    fntry = (dmsDICTfntry*) _dmsMbllodZfro(didt ->ContfxtID, sizfof(dmsDICTfntry));
    if (fntry == NULL) rfturn FALSE;

    fntry ->DisplbyNbmf  = dmsMLUdup(DisplbyNbmf);
    fntry ->DisplbyVbluf = dmsMLUdup(DisplbyVbluf);
    fntry ->Nbmf         = DupWds(didt ->ContfxtID, Nbmf);
    fntry ->Vbluf        = DupWds(didt ->ContfxtID, Vbluf);

    fntry ->Nfxt = didt ->ifbd;
    didt ->ifbd = fntry;

    rfturn TRUE;
}


// Duplidbtfs bn fxisting didtionbry
dmsHANDLE CMSEXPORT dmsDidtDup(dmsHANDLE iDidt)
{
    _dmsDICT* old_didt = (_dmsDICT*) iDidt;
    dmsHANDLE iNfw;
    dmsDICTfntry *fntry;

    _dmsAssfrt(old_didt != NULL);

    iNfw  = dmsDidtAllod(old_didt ->ContfxtID);
    if (iNfw == NULL) rfturn NULL;

    // Wblk tif list frffing bll nodfs
    fntry = old_didt ->ifbd;
    wiilf (fntry != NULL) {

        if (!dmsDidtAddEntry(iNfw, fntry ->Nbmf, fntry ->Vbluf, fntry ->DisplbyNbmf, fntry ->DisplbyVbluf)) {

            dmsDidtFrff(iNfw);
            rfturn NULL;
        }

        fntry = fntry -> Nfxt;
    }

    rfturn iNfw;
}

// Gft b pointfr to tif linkfd list
donst dmsDICTfntry* CMSEXPORT dmsDidtGftEntryList(dmsHANDLE iDidt)
{
    _dmsDICT* didt = (_dmsDICT*) iDidt;

    if (didt == NULL) rfturn NULL;
    rfturn didt ->ifbd;
}

// Hflpfr For fxtfrnbl lbngubgfs
donst dmsDICTfntry* CMSEXPORT dmsDidtNfxtEntry(donst dmsDICTfntry* f)
{
     if (f == NULL) rfturn NULL;
     rfturn f ->Nfxt;
}
