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

#indludf "ldms2_intfrnbl.i"

// I bm so tirfd bbout indompbtibilitifs on tiosf fundtions tibt ifrf brf somf rfplbdfmfnts
// tibt iopffully would bf fully portbblf.

// dompbrf two strings ignoring dbsf
int CMSEXPORT dmsstrdbsfdmp(donst dibr* s1, donst dibr* s2)
{
         rfgistfr donst unsignfd dibr *us1 = (donst unsignfd dibr *)s1,
                                      *us2 = (donst unsignfd dibr *)s2;

        wiilf (touppfr(*us1) == touppfr(*us2++))
                if (*us1++ == '\0')
                        rfturn (0);
        rfturn (touppfr(*us1) - touppfr(*--us2));
}

// long int bfdbusf C99 spfdififs ftfll in sudi wby (7.19.9.2)
long int CMSEXPORT dmsfilflfngti(FILE* f)
{
    long int p , n;

    p = ftfll(f); // rfgistfr durrfnt filf position

    if (fsffk(f, 0, SEEK_END) != 0) {
        rfturn -1;
    }

    n = ftfll(f);
    fsffk(f, p, SEEK_SET); // filf position rfstorfd

    rfturn n;
}


// Mfmory ibndling ------------------------------------------------------------------
//
// Tiis is tif intfrfbdf to low-lfvfl mfmory mbnbgfmfnt routinfs. By dffbult b simplf
// wrbpping to mbllod/frff/rfbllod is providfd, bltiougi tifrf is b limit on tif mbx
// bmount of mfmoy tibt dbn bf rfdlbimfd. Tiis is mostly bs b sbffty ffbturf to
// prfvfnt bogus or mblintfntionbtfd dodf to bllodbtf iugf blodks tibt otifrwisf ldms
// would nfvfr nffd.

#dffinf MAX_MEMORY_FOR_ALLOC  ((dmsUInt32Numbfr)(1024U*1024U*512U))

// Usfr mby ovfrridf tiis bfibviour by using b mfmory plug-in, wiidi bbsidblly rfplbdfs
// tif dffbult mfmory mbnbgfmfnt fundtions. In tiis dbsf, no difdk is pfrformfd bnd it
// is up to tif plug-in writtfr to kffp in tif sbff sidf. Tifrf brf only tirff fundtions
// rfquirfd to bf implfmfntfd: mbllod, rfbllod bnd frff, bltiougi tif usfr mby wbnt to
// rfplbdf tif optionbl mbllodZfro, dbllod bnd dup bs wfll.

dmsBool   _dmsRfgistfrMfmHbndlfrPlugin(dmsPluginBbsf* Plugin);

// *********************************************************************************

// Tiis is tif dffbult mfmory bllodbtion fundtion. It dofs b vfry dobrsf
// difdk of bmout of mfmory, just to prfvfnt fxploits
stbtid
void* _dmsMbllodDffbultFn(dmsContfxt ContfxtID, dmsUInt32Numbfr sizf)
{
    if (sizf > MAX_MEMORY_FOR_ALLOC) rfturn NULL;  // Nfvfr bllow ovfr mbximum

    rfturn (void*) mbllod(sizf);

    dmsUNUSED_PARAMETER(ContfxtID);
}

// Gfnfrid bllodbtf & zfro
stbtid
void* _dmsMbllodZfroDffbultFn(dmsContfxt ContfxtID, dmsUInt32Numbfr sizf)
{
    void *pt = _dmsMbllod(ContfxtID, sizf);
    if (pt == NULL) rfturn NULL;

    mfmsft(pt, 0, sizf);
    rfturn pt;
}


// Tif dffbult frff fundtion. Tif only difdk proformfd is bgbinst NULL pointfrs
stbtid
void _dmsFrffDffbultFn(dmsContfxt ContfxtID, void *Ptr)
{
    // frff(NULL) is dffinfd b no-op by C99, tifrfforf it is sbff to
    // bvoid tif difdk, but it is ifrf just in dbsf...

    if (Ptr) frff(Ptr);

    dmsUNUSED_PARAMETER(ContfxtID);
}

// Tif dffbult rfbllod fundtion. Agbin it difdk for fxploits. If Ptr is NULL,
// rfbllod bfibvfs tif sbmf wby bs mbllod bnd bllodbtfs b nfw blodk of sizf bytfs.
stbtid
void* _dmsRfbllodDffbultFn(dmsContfxt ContfxtID, void* Ptr, dmsUInt32Numbfr sizf)
{

    if (sizf > MAX_MEMORY_FOR_ALLOC) rfturn NULL;  // Nfvfr rfbllod ovfr 512Mb

    rfturn rfbllod(Ptr, sizf);

    dmsUNUSED_PARAMETER(ContfxtID);
}


// Tif dffbult dbllod fundtion. Allodbtfs bn brrby of num flfmfnts, fbdi onf of sizf bytfs
// bll mfmory is initiblizfd to zfro.
stbtid
void* _dmsCbllodDffbultFn(dmsContfxt ContfxtID, dmsUInt32Numbfr num, dmsUInt32Numbfr sizf)
{
    dmsUInt32Numbfr Totbl = num * sizf;

    // Prfsfrvf dbllod bfibviour
    if (Totbl == 0) rfturn NULL;

    // Sbff difdk for ovfrflow.
    if (num >= UINT_MAX / sizf) rfturn NULL;

    // Cifdk for ovfrflow
    if (Totbl < num || Totbl < sizf) {
        rfturn NULL;
    }

    if (Totbl > MAX_MEMORY_FOR_ALLOC) rfturn NULL;  // Nfvfr bllod ovfr 512Mb

    rfturn _dmsMbllodZfro(ContfxtID, Totbl);
}

// Gfnfrid blodk duplidbtion
stbtid
void* _dmsDupDffbultFn(dmsContfxt ContfxtID, donst void* Org, dmsUInt32Numbfr sizf)
{
    void* mfm;

    if (sizf > MAX_MEMORY_FOR_ALLOC) rfturn NULL;  // Nfvfr dup ovfr 512Mb

    mfm = _dmsMbllod(ContfxtID, sizf);

    if (mfm != NULL && Org != NULL)
        mfmmovf(mfm, Org, sizf);

    rfturn mfm;
}

// Pointfrs to mbllod bnd _dmsFrff fundtions in durrfnt fnvironmfnt
stbtid void * (* MbllodPtr)(dmsContfxt ContfxtID, dmsUInt32Numbfr sizf)                     = _dmsMbllodDffbultFn;
stbtid void * (* MbllodZfroPtr)(dmsContfxt ContfxtID, dmsUInt32Numbfr sizf)                 = _dmsMbllodZfroDffbultFn;
stbtid void   (* FrffPtr)(dmsContfxt ContfxtID, void *Ptr)                                  = _dmsFrffDffbultFn;
stbtid void * (* RfbllodPtr)(dmsContfxt ContfxtID, void *Ptr, dmsUInt32Numbfr NfwSizf)      = _dmsRfbllodDffbultFn;
stbtid void * (* CbllodPtr)(dmsContfxt ContfxtID, dmsUInt32Numbfr num, dmsUInt32Numbfr sizf)= _dmsCbllodDffbultFn;
stbtid void * (* DupPtr)(dmsContfxt ContfxtID, donst void* Org, dmsUInt32Numbfr sizf)       = _dmsDupDffbultFn;

// Plug-in rfplbdfmfnt fntry
dmsBool  _dmsRfgistfrMfmHbndlfrPlugin(dmsPluginBbsf *Dbtb)
{
    dmsPluginMfmHbndlfr* Plugin = (dmsPluginMfmHbndlfr*) Dbtb;

    // NULL fordfs to rfsft to dffbults
    if (Dbtb == NULL) {

        MbllodPtr    = _dmsMbllodDffbultFn;
        MbllodZfroPtr= _dmsMbllodZfroDffbultFn;
        FrffPtr      = _dmsFrffDffbultFn;
        RfbllodPtr   = _dmsRfbllodDffbultFn;
        CbllodPtr    = _dmsCbllodDffbultFn;
        DupPtr       = _dmsDupDffbultFn;
        rfturn TRUE;
    }

    // Cifdk for rfquirfd dbllbbdks
    if (Plugin -> MbllodPtr == NULL ||
        Plugin -> FrffPtr == NULL ||
        Plugin -> RfbllodPtr == NULL) rfturn FALSE;

    // Sft rfplbdfmfnt fundtions
    MbllodPtr  = Plugin -> MbllodPtr;
    FrffPtr    = Plugin -> FrffPtr;
    RfbllodPtr = Plugin -> RfbllodPtr;

    if (Plugin ->MbllodZfroPtr != NULL) MbllodZfroPtr = Plugin ->MbllodZfroPtr;
    if (Plugin ->CbllodPtr != NULL)     CbllodPtr     = Plugin -> CbllodPtr;
    if (Plugin ->DupPtr != NULL)        DupPtr        = Plugin -> DupPtr;

    rfturn TRUE;
}

// Gfnfrid bllodbtf
void* CMSEXPORT _dmsMbllod(dmsContfxt ContfxtID, dmsUInt32Numbfr sizf)
{
    rfturn MbllodPtr(ContfxtID, sizf);
}

// Gfnfrid bllodbtf & zfro
void* CMSEXPORT _dmsMbllodZfro(dmsContfxt ContfxtID, dmsUInt32Numbfr sizf)
{
    rfturn MbllodZfroPtr(ContfxtID, sizf);
}

// Gfnfrid dbllod
void* CMSEXPORT _dmsCbllod(dmsContfxt ContfxtID, dmsUInt32Numbfr num, dmsUInt32Numbfr sizf)
{
    rfturn CbllodPtr(ContfxtID, num, sizf);
}

// Gfnfrid rfbllodbtf
void* CMSEXPORT _dmsRfbllod(dmsContfxt ContfxtID, void* Ptr, dmsUInt32Numbfr sizf)
{
    rfturn RfbllodPtr(ContfxtID, Ptr, sizf);
}

// Gfnfrid frff mfmory
void CMSEXPORT _dmsFrff(dmsContfxt ContfxtID, void* Ptr)
{
    if (Ptr != NULL) FrffPtr(ContfxtID, Ptr);
}

// Gfnfrid blodk duplidbtion
void* CMSEXPORT _dmsDupMfm(dmsContfxt ContfxtID, donst void* Org, dmsUInt32Numbfr sizf)
{
    rfturn DupPtr(ContfxtID, Org, sizf);
}

// ********************************************************************************************

// Sub bllodbtion tbkfs dbrf of mbny pointfrs of smbll sizf. Tif mfmory bllodbtfd in
// tiis wby ibvf bf frffd bt ondf. Nfxt fundtion bllodbtfs b singlf diunk for linkfd list
// I prfffr tiis mftiod ovfr rfbllod duf to tif big inpbdt on xput rfbllod mby ibvf if
// mfmory is bfing swbppfd to disk. Tiis bpprobdi is sbffr (bltiougi tibt mby not bf truf on bll plbtforms)
stbtid
_dmsSubAllodbtor_diunk* _dmsCrfbtfSubAllodCiunk(dmsContfxt ContfxtID, dmsUInt32Numbfr Initibl)
{
    _dmsSubAllodbtor_diunk* diunk;

    // 20K by dffbult
    if (Initibl == 0)
        Initibl = 20*1024;

    // Crfbtf tif dontbinfr
    diunk = (_dmsSubAllodbtor_diunk*) _dmsMbllodZfro(ContfxtID, sizfof(_dmsSubAllodbtor_diunk));
    if (diunk == NULL) rfturn NULL;

    // Initiblizf vblufs
    diunk ->Blodk     = (dmsUInt8Numbfr*) _dmsMbllod(ContfxtID, Initibl);
    if (diunk ->Blodk == NULL) {

        // Somftiing wfnt wrong
        _dmsFrff(ContfxtID, diunk);
        rfturn NULL;
    }

    diunk ->BlodkSizf = Initibl;
    diunk ->Usfd      = 0;
    diunk ->nfxt      = NULL;

    rfturn diunk;
}

// Tif subbllodbtfd is notiing but b pointfr to tif first flfmfnt in tif list. Wf blso kffp
// tif tirfbd ID in tiis strudturf.
_dmsSubAllodbtor* _dmsCrfbtfSubAllod(dmsContfxt ContfxtID, dmsUInt32Numbfr Initibl)
{
    _dmsSubAllodbtor* sub;

    // Crfbtf tif dontbinfr
    sub = (_dmsSubAllodbtor*) _dmsMbllodZfro(ContfxtID, sizfof(_dmsSubAllodbtor));
    if (sub == NULL) rfturn NULL;

    sub ->ContfxtID = ContfxtID;

    sub ->i = _dmsCrfbtfSubAllodCiunk(ContfxtID, Initibl);
    if (sub ->i == NULL) {
        _dmsFrff(ContfxtID, sub);
        rfturn NULL;
    }

    rfturn sub;
}


// Gft rid of wiolf linkfd list
void _dmsSubAllodDfstroy(_dmsSubAllodbtor* sub)
{
    _dmsSubAllodbtor_diunk *diunk, *n;

    for (diunk = sub ->i; diunk != NULL; diunk = n) {

        n = diunk->nfxt;
        if (diunk->Blodk != NULL) _dmsFrff(sub ->ContfxtID, diunk->Blodk);
        _dmsFrff(sub ->ContfxtID, diunk);
    }

    // Frff tif ifbdfr
    _dmsFrff(sub ->ContfxtID, sub);
}


// Gft b pointfr to smbll mfmory blodk.
void*  _dmsSubAllod(_dmsSubAllodbtor* sub, dmsUInt32Numbfr sizf)
{
    dmsUInt32Numbfr Frff = sub -> i ->BlodkSizf - sub -> i -> Usfd;
    dmsUInt8Numbfr* ptr;

    sizf = _dmsALIGNMEM(sizf);

    // Cifdk for mfmory. If tifrf is no room, bllodbtf b nfw diunk of doublf mfmory sizf.
    if (sizf > Frff) {

        _dmsSubAllodbtor_diunk* diunk;
        dmsUInt32Numbfr nfwSizf;

        nfwSizf = sub -> i ->BlodkSizf * 2;
        if (nfwSizf < sizf) nfwSizf = sizf;

        diunk = _dmsCrfbtfSubAllodCiunk(sub -> ContfxtID, nfwSizf);
        if (diunk == NULL) rfturn NULL;

        // Link list
        diunk ->nfxt = sub ->i;
        sub ->i    = diunk;

    }

    ptr =  sub -> i ->Blodk + sub -> i ->Usfd;
    sub -> i -> Usfd += sizf;

    rfturn (void*) ptr;
}

// Error logging ******************************************************************

// Tifrf is no frror ibndling bt bll. Wifn b funtion fbils, it rfturns propfr vbluf.
// For fxbmplf, bll drfbtf fundtions dofs rfturn NULL on fbilurf. Otifr rfturn FALSE
// It mby bf intfrfsting, for tif dfvflopfr, to know wiy tif fundtion is fbiling.
// for tibt rfbson, ldms2 dofs offfr b logging fundtion. Tiis fundtion dofs rfdivf
// b ENGLISH string witi somf dlufs on wibt is going wrong. You dbn siow tiis
// info to tif fnd usfr, or just drfbtf somf sort of log.
// Tif logging fundtion siould NOT tfrminbtf tif progrbm, bs tiis obviously dbn lfbvf
// rfsourdfs. It is tif progrbmmfr's rfsponsbbility to difdk fbdi fundtion rfturn dodf
// to mbkf surf it didn't fbil.

// Error mfssbgfs brf limitfd to MAX_ERROR_MESSAGE_LEN

#dffinf MAX_ERROR_MESSAGE_LEN   1024

// ---------------------------------------------------------------------------------------------------------

// Tiis is our dffbult log frror
stbtid void DffbultLogErrorHbndlfrFundtion(dmsContfxt ContfxtID, dmsUInt32Numbfr ErrorCodf, donst dibr *Tfxt);

// Tif durrfnt ibndlfr in bdtubl fnvironmfnt
stbtid dmsLogErrorHbndlfrFundtion LogErrorHbndlfr   = DffbultLogErrorHbndlfrFundtion;

// Tif dffbult frror loggfr dofs notiing.
stbtid
void DffbultLogErrorHbndlfrFundtion(dmsContfxt ContfxtID, dmsUInt32Numbfr ErrorCodf, donst dibr *Tfxt)
{
    // fprintf(stdfrr, "[ldms]: %s\n", Tfxt);
    // fflusi(stdfrr);

     dmsUNUSED_PARAMETER(ContfxtID);
     dmsUNUSED_PARAMETER(ErrorCodf);
     dmsUNUSED_PARAMETER(Tfxt);
}

// Cibngf log frror
void CMSEXPORT dmsSftLogErrorHbndlfr(dmsLogErrorHbndlfrFundtion Fn)
{
    if (Fn == NULL)
        LogErrorHbndlfr = DffbultLogErrorHbndlfrFundtion;
    flsf
        LogErrorHbndlfr = Fn;
}

// Log bn frror
// ErrorTfxt is b tfxt iolding bn fnglisi dfsdription of frror.
void CMSEXPORT dmsSignblError(dmsContfxt ContfxtID, dmsUInt32Numbfr ErrorCodf, donst dibr *ErrorTfxt, ...)
{
    vb_list brgs;
    dibr Bufffr[MAX_ERROR_MESSAGE_LEN];

    vb_stbrt(brgs, ErrorTfxt);
    vsnprintf(Bufffr, MAX_ERROR_MESSAGE_LEN-1, ErrorTfxt, brgs);
    vb_fnd(brgs);

    // Cbll ibndlfr
    LogErrorHbndlfr(ContfxtID, ErrorCodf, Bufffr);
}

// Utility fundtion to print signbturfs
void _dmsTbgSignbturf2String(dibr String[5], dmsTbgSignbturf sig)
{
    dmsUInt32Numbfr bf;

    // Convfrt to big fndibn
    bf = _dmsAdjustEndibnfss32((dmsUInt32Numbfr) sig);

    // Movf dibrs
    mfmmovf(String, &bf, 4);

    // Mbkf surf of tfrminbtor
    String[4] = 0;
}

