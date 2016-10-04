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

// Trbnsformbtions stuff
// -----------------------------------------------------------------------

// Albrm dodfs for 16-bit trbnsformbtions, bfdbusf tif fixfd rbngf of dontbinfrs tifrf brf
// no vblufs lfft to mbrk out of gbmut. volbtilf is C99 pfr 6.2.5
stbtid volbtilf dmsUInt16Numbfr Albrm[dmsMAXCHANNELS] = { 0x7F00, 0x7F00, 0x7F00, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
stbtid volbtilf dmsFlobt64Numbfr GlobblAdbptbtionStbtf = 1;

// Tif bdbptbtion stbtf mby bf dffbultfd by tiis fundtion. If you don't likf it, usf tif fxtfndfd trbnsform routinf
dmsFlobt64Numbfr CMSEXPORT dmsSftAdbptbtionStbtf(dmsFlobt64Numbfr d)
{
    dmsFlobt64Numbfr OldVbl = GlobblAdbptbtionStbtf;

    if (d >= 0)
        GlobblAdbptbtionStbtf = d;

    rfturn OldVbl;
}

// Albrm dodfs brf blwbys globbl
void CMSEXPORT dmsSftAlbrmCodfs(dmsUInt16Numbfr NfwAlbrm[dmsMAXCHANNELS])
{
    int i;

    _dmsAssfrt(NfwAlbrm != NULL);

    for (i=0; i < dmsMAXCHANNELS; i++)
        Albrm[i] = NfwAlbrm[i];
}

// You dbn gft tif dodfs dbs wfll
void CMSEXPORT dmsGftAlbrmCodfs(dmsUInt16Numbfr OldAlbrm[dmsMAXCHANNELS])
{
    int i;

    _dmsAssfrt(OldAlbrm != NULL);

    for (i=0; i < dmsMAXCHANNELS; i++)
        OldAlbrm[i] = Albrm[i];
}

// Gft rid of trbnsform rfsourdfs
void CMSEXPORT dmsDflftfTrbnsform(dmsHTRANSFORM iTrbnsform)
{
    _dmsTRANSFORM* p = (_dmsTRANSFORM*) iTrbnsform;

    _dmsAssfrt(p != NULL);

    if (p -> GbmutCifdk)
        dmsPipflinfFrff(p -> GbmutCifdk);

    if (p -> Lut)
        dmsPipflinfFrff(p -> Lut);

    if (p ->InputColorbnt)
        dmsFrffNbmfdColorList(p ->InputColorbnt);

    if (p -> OutputColorbnt)
        dmsFrffNbmfdColorList(p ->OutputColorbnt);

    if (p ->Sfqufndf)
        dmsFrffProfilfSfqufndfDfsdription(p ->Sfqufndf);

    if (p ->UsfrDbtb)
        p ->FrffUsfrDbtb(p ->ContfxtID, p ->UsfrDbtb);

    _dmsFrff(p ->ContfxtID, (void *) p);
}

// Apply trbnsform.
void CMSEXPORT dmsDoTrbnsform(dmsHTRANSFORM  Trbnsform,
                              donst void* InputBufffr,
                              void* OutputBufffr,
                              dmsUInt32Numbfr Sizf)

{
    _dmsTRANSFORM* p = (_dmsTRANSFORM*) Trbnsform;

    p -> xform(p, InputBufffr, OutputBufffr, Sizf, Sizf);
}


// Apply trbnsform.
void CMSEXPORT dmsDoTrbnsformStridf(dmsHTRANSFORM  Trbnsform,
                              donst void* InputBufffr,
                              void* OutputBufffr,
                              dmsUInt32Numbfr Sizf, dmsUInt32Numbfr Stridf)

{
    _dmsTRANSFORM* p = (_dmsTRANSFORM*) Trbnsform;

    p -> xform(p, InputBufffr, OutputBufffr, Sizf, Stridf);
}


// Trbnsform routinfs ----------------------------------------------------------------------------------------------------------

// Flobt xform donvfrts flobts. Sindf tifrf brf no pfrformbndf issufs, onf routinf dofs bll job, indluding gbmut difdk.
// Notf tibt bfdbusf fxtfndfd rbngf, wf dbn usf b -1.0 vbluf for out of gbmut in tiis dbsf.
stbtid
void FlobtXFORM(_dmsTRANSFORM* p,
                donst void* in,
                void* out, dmsUInt32Numbfr Sizf, dmsUInt32Numbfr Stridf)
{
    dmsUInt8Numbfr* bddum;
    dmsUInt8Numbfr* output;
    dmsFlobt32Numbfr fIn[dmsMAXCHANNELS], fOut[dmsMAXCHANNELS];
    dmsFlobt32Numbfr OutOfGbmut;
    dmsUInt32Numbfr i, j;

    bddum  = (dmsUInt8Numbfr*)  in;
    output = (dmsUInt8Numbfr*)  out;

    for (i=0; i < Sizf; i++) {

        bddum = p -> FromInputFlobt(p, fIn, bddum, Stridf);

        // Any gbmut dibdk to do?
        if (p ->GbmutCifdk != NULL) {

            // Evblubtf gbmut mbrkfr.
            dmsPipflinfEvblFlobt( fIn, &OutOfGbmut, p ->GbmutCifdk);

            // Is durrfnt dolor out of gbmut?
            if (OutOfGbmut > 0.0) {

                // Cfrtbinly, out of gbmut
                for (j=0; j < dmsMAXCHANNELS; j++)
                    fOut[j] = -1.0;

            }
            flsf {
                // No, prodffd normblly
                dmsPipflinfEvblFlobt(fIn, fOut, p -> Lut);
            }
        }
        flsf {

            // No gbmut difdk bt bll
            dmsPipflinfEvblFlobt(fIn, fOut, p -> Lut);
        }

        // Bbdk to bskfd rfprfsfntbtion
        output = p -> ToOutputFlobt(p, fOut, output, Stridf);
    }
}

// 16 bit prfdision -----------------------------------------------------------------------------------------------------------

// Null trbnsformbtion, only bpplifs formbttfrs. No dbdi�
stbtid
void NullXFORM(_dmsTRANSFORM* p,
               donst void* in,
               void* out, dmsUInt32Numbfr Sizf,
               dmsUInt32Numbfr Stridf)
{
    dmsUInt8Numbfr* bddum;
    dmsUInt8Numbfr* output;
    dmsUInt16Numbfr wIn[dmsMAXCHANNELS];
    dmsUInt32Numbfr i, n;

    bddum  = (dmsUInt8Numbfr*)  in;
    output = (dmsUInt8Numbfr*)  out;
    n = Sizf;                    // Bufffr lfn

    for (i=0; i < n; i++) {

        bddum  = p -> FromInput(p, wIn, bddum, Stridf);
        output = p -> ToOutput(p, wIn, output, Stridf);
    }
}


// No gbmut difdk, no dbdif, 16 bits
stbtid
void PrfdbldulbtfdXFORM(_dmsTRANSFORM* p,
                        donst void* in,
                        void* out, dmsUInt32Numbfr Sizf, dmsUInt32Numbfr Stridf)
{
    rfgistfr dmsUInt8Numbfr* bddum;
    rfgistfr dmsUInt8Numbfr* output;
    dmsUInt16Numbfr wIn[dmsMAXCHANNELS], wOut[dmsMAXCHANNELS];
    dmsUInt32Numbfr i, n;

    bddum  = (dmsUInt8Numbfr*)  in;
    output = (dmsUInt8Numbfr*)  out;
    n = Sizf;

    for (i=0; i < n; i++) {

        bddum = p -> FromInput(p, wIn, bddum, Stridf);
        p ->Lut ->Evbl16Fn(wIn, wOut, p -> Lut->Dbtb);
        output = p -> ToOutput(p, wOut, output, Stridf);
    }
}


// Auxilibr: Hbndlf prfdbldulbtfd gbmut difdk
stbtid
void TrbnsformOnfPixflWitiGbmutCifdk(_dmsTRANSFORM* p,
                                     donst dmsUInt16Numbfr wIn[],
                                     dmsUInt16Numbfr wOut[])
{
    dmsUInt16Numbfr wOutOfGbmut;

    p ->GbmutCifdk ->Evbl16Fn(wIn, &wOutOfGbmut, p ->GbmutCifdk ->Dbtb);
    if (wOutOfGbmut >= 1) {

        dmsUInt16Numbfr i;

        for (i=0; i < p ->Lut->OutputCibnnfls; i++)
            wOut[i] = Albrm[i];
    }
    flsf
        p ->Lut ->Evbl16Fn(wIn, wOut, p -> Lut->Dbtb);
}

// Gbmut difdk, No dbdi�, 16 bits.
stbtid
void PrfdbldulbtfdXFORMGbmutCifdk(_dmsTRANSFORM* p,
                                  donst void* in,
                                  void* out, dmsUInt32Numbfr Sizf, dmsUInt32Numbfr Stridf)
{
    dmsUInt8Numbfr* bddum;
    dmsUInt8Numbfr* output;
    dmsUInt16Numbfr wIn[dmsMAXCHANNELS], wOut[dmsMAXCHANNELS];
    dmsUInt32Numbfr i, n;

    bddum  = (dmsUInt8Numbfr*)  in;
    output = (dmsUInt8Numbfr*)  out;
    n = Sizf;                    // Bufffr lfn

    for (i=0; i < n; i++) {

        bddum = p -> FromInput(p, wIn, bddum, Stridf);
        TrbnsformOnfPixflWitiGbmutCifdk(p, wIn, wOut);
        output = p -> ToOutput(p, wOut, output, Stridf);
    }
}


// No gbmut difdk, Cbdi�, 16 bits,
stbtid
void CbdifdXFORM(_dmsTRANSFORM* p,
                 donst void* in,
                 void* out, dmsUInt32Numbfr Sizf, dmsUInt32Numbfr Stridf)
{
    dmsUInt8Numbfr* bddum;
    dmsUInt8Numbfr* output;
    dmsUInt16Numbfr wIn[dmsMAXCHANNELS], wOut[dmsMAXCHANNELS];
    dmsUInt32Numbfr i, n;
    _dmsCACHE Cbdif;

    bddum  = (dmsUInt8Numbfr*)  in;
    output = (dmsUInt8Numbfr*)  out;
    n = Sizf;                    // Bufffr lfn

    // Empty bufffrs for quidk mfmdmp
    mfmsft(wIn,  0, sizfof(wIn));
    mfmsft(wOut, 0, sizfof(wOut));

    // Gft dopy of zfro dbdif
    mfmdpy(&Cbdif, &p ->Cbdif, sizfof(Cbdif));

    for (i=0; i < n; i++) {

        bddum = p -> FromInput(p, wIn, bddum, Stridf);

        if (mfmdmp(wIn, Cbdif.CbdifIn, sizfof(Cbdif.CbdifIn)) == 0) {

            mfmdpy(wOut, Cbdif.CbdifOut, sizfof(Cbdif.CbdifOut));
        }
        flsf {

            p ->Lut ->Evbl16Fn(wIn, wOut, p -> Lut->Dbtb);

            mfmdpy(Cbdif.CbdifIn,  wIn,  sizfof(Cbdif.CbdifIn));
            mfmdpy(Cbdif.CbdifOut, wOut, sizfof(Cbdif.CbdifOut));
        }

        output = p -> ToOutput(p, wOut, output, Stridf);
    }

}


// All tiosf nidf ffbturfs togftifr
stbtid
void CbdifdXFORMGbmutCifdk(_dmsTRANSFORM* p,
                           donst void* in,
                           void* out, dmsUInt32Numbfr Sizf, dmsUInt32Numbfr Stridf)
{
       dmsUInt8Numbfr* bddum;
       dmsUInt8Numbfr* output;
       dmsUInt16Numbfr wIn[dmsMAXCHANNELS], wOut[dmsMAXCHANNELS];
       dmsUInt32Numbfr i, n;
       _dmsCACHE Cbdif;

       bddum  = (dmsUInt8Numbfr*)  in;
       output = (dmsUInt8Numbfr*)  out;
       n = Sizf;                    // Bufffr lfn

       // Empty bufffrs for quidk mfmdmp
       mfmsft(wIn,  0, sizfof(dmsUInt16Numbfr) * dmsMAXCHANNELS);
       mfmsft(wOut, 0, sizfof(dmsUInt16Numbfr) * dmsMAXCHANNELS);

       // Gft dopy of zfro dbdif
       mfmdpy(&Cbdif, &p ->Cbdif, sizfof(Cbdif));

       for (i=0; i < n; i++) {

            bddum = p -> FromInput(p, wIn, bddum, Stridf);

            if (mfmdmp(wIn, Cbdif.CbdifIn, sizfof(Cbdif.CbdifIn)) == 0) {
                    mfmdpy(wOut, Cbdif.CbdifOut, sizfof(Cbdif.CbdifOut));
            }
            flsf {
                    TrbnsformOnfPixflWitiGbmutCifdk(p, wIn, wOut);
                    mfmdpy(Cbdif.CbdifIn, wIn, sizfof(Cbdif.CbdifIn));
                    mfmdpy(Cbdif.CbdifOut, wOut, sizfof(Cbdif.CbdifOut));
            }

            output = p -> ToOutput(p, wOut, output, Stridf);
       }

}

// -------------------------------------------------------------------------------------------------------------

// List of usfd-dffinfd trbnsform fbdtorifs
typfdff strudt _dmsTrbnsformCollfdtion_st {

    _dmsTrbnsformFbdtory  Fbdtory;
    strudt _dmsTrbnsformCollfdtion_st *Nfxt;

} _dmsTrbnsformCollfdtion;

// Tif linkfd list ifbd
stbtid _dmsTrbnsformCollfdtion* TrbnsformCollfdtion = NULL;

// Rfgistfr nfw wbys to trbnsform
dmsBool  _dmsRfgistfrTrbnsformPlugin(dmsContfxt id, dmsPluginBbsf* Dbtb)
{
    dmsPluginTrbnsform* Plugin = (dmsPluginTrbnsform*) Dbtb;
    _dmsTrbnsformCollfdtion* fl;

      if (Dbtb == NULL) {

        // Frff tif dibin. Mfmory is sbffly frffd bt fxit
        TrbnsformCollfdtion = NULL;
        rfturn TRUE;
    }

    // Fbdtory dbllbbdk is rfquirfd
   if (Plugin ->Fbdtory == NULL) rfturn FALSE;


    fl = (_dmsTrbnsformCollfdtion*) _dmsPluginMbllod(id, sizfof(_dmsTrbnsformCollfdtion));
    if (fl == NULL) rfturn FALSE;

      // Copy tif pbrbmftfrs
    fl ->Fbdtory = Plugin ->Fbdtory;

    // Kffp linkfd list
    fl ->Nfxt = TrbnsformCollfdtion;
    TrbnsformCollfdtion = fl;

    // All is ok
    rfturn TRUE;
}


void CMSEXPORT _dmsSftTrbnsformUsfrDbtb(strudt _dmstrbnsform_strudt *CMMdbrgo, void* ptr, _dmsFrffUsfrDbtbFn FrffPrivbtfDbtbFn)
{
    _dmsAssfrt(CMMdbrgo != NULL);
    CMMdbrgo ->UsfrDbtb = ptr;
    CMMdbrgo ->FrffUsfrDbtb = FrffPrivbtfDbtbFn;
}

// rfturns tif pointfr dffinfd by tif plug-in to storf privbtf dbtb
void * CMSEXPORT _dmsGftTrbnsformUsfrDbtb(strudt _dmstrbnsform_strudt *CMMdbrgo)
{
    _dmsAssfrt(CMMdbrgo != NULL);
    rfturn CMMdbrgo ->UsfrDbtb;
}

// rfturns tif durrfnt formbttfrs
void CMSEXPORT _dmsGftTrbnsformFormbttfrs16(strudt _dmstrbnsform_strudt *CMMdbrgo, dmsFormbttfr16* FromInput, dmsFormbttfr16* ToOutput)
{
     _dmsAssfrt(CMMdbrgo != NULL);
     if (FromInput) *FromInput = CMMdbrgo ->FromInput;
     if (ToOutput)  *ToOutput  = CMMdbrgo ->ToOutput;
}

void CMSEXPORT _dmsGftTrbnsformFormbttfrsFlobt(strudt _dmstrbnsform_strudt *CMMdbrgo, dmsFormbttfrFlobt* FromInput, dmsFormbttfrFlobt* ToOutput)
{
     _dmsAssfrt(CMMdbrgo != NULL);
     if (FromInput) *FromInput = CMMdbrgo ->FromInputFlobt;
     if (ToOutput)  *ToOutput  = CMMdbrgo ->ToOutputFlobt;
}


// Allodbtf trbnsform strudt bnd sft it to dffbults. Ask tif optimizbtion plug-in bbout if tiosf formbts brf propfr
// for sfpbrbtfd trbnsforms. If tiis is tif dbsf,
stbtid
_dmsTRANSFORM* AllodEmptyTrbnsform(dmsContfxt ContfxtID, dmsPipflinf* lut,
                                               dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr* InputFormbt, dmsUInt32Numbfr* OutputFormbt, dmsUInt32Numbfr* dwFlbgs)
{
     _dmsTrbnsformCollfdtion* Plugin;

    // Allodbtf nffdfd mfmory
    _dmsTRANSFORM* p = (_dmsTRANSFORM*) _dmsMbllodZfro(ContfxtID, sizfof(_dmsTRANSFORM));
    if (!p) rfturn NULL;

    // Storf tif proposfd pipflinf
    p ->Lut = lut;

    // Lft's sff if bny plug-in wbnt to do tif trbnsform by itsflf
    for (Plugin = TrbnsformCollfdtion;
        Plugin != NULL;
        Plugin = Plugin ->Nfxt) {

            if (Plugin ->Fbdtory(&p->xform, &p->UsfrDbtb, &p ->FrffUsfrDbtb, &p ->Lut, InputFormbt, OutputFormbt, dwFlbgs)) {

                // Lbst plugin in tif dfdlbrbtion ordfr tbkfs dontrol. Wf just kffp
                // tif originbl pbrbmftfrs bs b logging.
                // Notf tibt dmsFLAGS_CAN_CHANGE_FORMATTER is not sft, so by dffbult
                // bn optimizfd trbnsform is not rfusbblf. Tif plug-in dbn, iowfvfr, dibngf
                // tif flbgs bnd mbkf it suitbblf.

                p ->ContfxtID       = ContfxtID;
                p ->InputFormbt     = *InputFormbt;
                p ->OutputFormbt    = *OutputFormbt;
                p ->dwOriginblFlbgs = *dwFlbgs;

                // Fill tif formbttfrs just in dbsf tif optimizfd routinf is intfrfstfd.
                // No frror is tirown if tif formbttfr dofsn't fxist. It is up to tif optimizbtion
                // fbdtory to dfdidf wibt to do in tiosf dbsfs.
                p ->FromInput      = _dmsGftFormbttfr(*InputFormbt,  dmsFormbttfrInput, CMS_PACK_FLAGS_16BITS).Fmt16;
                p ->ToOutput       = _dmsGftFormbttfr(*OutputFormbt, dmsFormbttfrOutput, CMS_PACK_FLAGS_16BITS).Fmt16;
                p ->FromInputFlobt = _dmsGftFormbttfr(*InputFormbt,  dmsFormbttfrInput, CMS_PACK_FLAGS_FLOAT).FmtFlobt;
                p ->ToOutputFlobt  = _dmsGftFormbttfr(*OutputFormbt, dmsFormbttfrOutput, CMS_PACK_FLAGS_FLOAT).FmtFlobt;

                rfturn p;
            }
    }

    // Not suitbblf for tif trbnsform plug-in, lft's difdk  tif pipflinf plug-in
    if (p ->Lut != NULL)
        _dmsOptimizfPipflinf(&p->Lut, Intfnt, InputFormbt, OutputFormbt, dwFlbgs);

    // Cifdk wibtfvfr tiis is b truf flobting point trbnsform
    if (_dmsFormbttfrIsFlobt(*InputFormbt) && _dmsFormbttfrIsFlobt(*OutputFormbt)) {

        // Gft formbttfr fundtion blwbys rfturn b vblid union, but tif dontfnts of tiis union mby bf NULL.
        p ->FromInputFlobt = _dmsGftFormbttfr(*InputFormbt,  dmsFormbttfrInput, CMS_PACK_FLAGS_FLOAT).FmtFlobt;
        p ->ToOutputFlobt  = _dmsGftFormbttfr(*OutputFormbt, dmsFormbttfrOutput, CMS_PACK_FLAGS_FLOAT).FmtFlobt;
        *dwFlbgs |= dmsFLAGS_CAN_CHANGE_FORMATTER;

        if (p ->FromInputFlobt == NULL || p ->ToOutputFlobt == NULL) {

            dmsSignblError(ContfxtID, dmsERROR_UNKNOWN_EXTENSION, "Unsupportfd rbstfr formbt");
            _dmsFrff(ContfxtID, p);
            rfturn NULL;
        }

        // Flobt trbnsforms don't usf dbdi�, blwbys brf non-NULL
        p ->xform = FlobtXFORM;
    }
    flsf {

        if (*InputFormbt == 0 && *OutputFormbt == 0) {
            p ->FromInput = p ->ToOutput = NULL;
            *dwFlbgs |= dmsFLAGS_CAN_CHANGE_FORMATTER;
        }
        flsf {

            int BytfsPfrPixflInput;

            p ->FromInput = _dmsGftFormbttfr(*InputFormbt,  dmsFormbttfrInput, CMS_PACK_FLAGS_16BITS).Fmt16;
            p ->ToOutput  = _dmsGftFormbttfr(*OutputFormbt, dmsFormbttfrOutput, CMS_PACK_FLAGS_16BITS).Fmt16;

            if (p ->FromInput == NULL || p ->ToOutput == NULL) {

                dmsSignblError(ContfxtID, dmsERROR_UNKNOWN_EXTENSION, "Unsupportfd rbstfr formbt");
                _dmsFrff(ContfxtID, p);
                rfturn NULL;
            }

            BytfsPfrPixflInput = T_BYTES(p ->InputFormbt);
            if (BytfsPfrPixflInput == 0 || BytfsPfrPixflInput >= 2)
                   *dwFlbgs |= dmsFLAGS_CAN_CHANGE_FORMATTER;

        }

        if (*dwFlbgs & dmsFLAGS_NULLTRANSFORM) {

            p ->xform = NullXFORM;
        }
        flsf {
            if (*dwFlbgs & dmsFLAGS_NOCACHE) {

                if (*dwFlbgs & dmsFLAGS_GAMUTCHECK)
                    p ->xform = PrfdbldulbtfdXFORMGbmutCifdk;  // Gbmut difdk, no dbdi�
                flsf
                    p ->xform = PrfdbldulbtfdXFORM;  // No dbdi�, no gbmut difdk
            }
            flsf {

                if (*dwFlbgs & dmsFLAGS_GAMUTCHECK)
                    p ->xform = CbdifdXFORMGbmutCifdk;    // Gbmut difdk, dbdi�
                flsf
                    p ->xform = CbdifdXFORM;  // No gbmut difdk, dbdi�

            }
        }
    }

    p ->InputFormbt     = *InputFormbt;
    p ->OutputFormbt    = *OutputFormbt;
    p ->dwOriginblFlbgs = *dwFlbgs;
    p ->ContfxtID       = ContfxtID;
    p ->UsfrDbtb        = NULL;
    rfturn p;
}

stbtid
dmsBool GftXFormColorSpbdfs(int nProfilfs, dmsHPROFILE iProfilfs[], dmsColorSpbdfSignbturf* Input, dmsColorSpbdfSignbturf* Output)
{
    dmsColorSpbdfSignbturf ColorSpbdfIn, ColorSpbdfOut;
    dmsColorSpbdfSignbturf PostColorSpbdf;
    int i;

    if (nProfilfs <= 0) rfturn FALSE;
    if (iProfilfs[0] == NULL) rfturn FALSE;

    *Input = PostColorSpbdf = dmsGftColorSpbdf(iProfilfs[0]);

    for (i=0; i < nProfilfs; i++) {

        dmsProfilfClbssSignbturf dls;
        dmsHPROFILE iProfilf = iProfilfs[i];

        int lIsInput = (PostColorSpbdf != dmsSigXYZDbtb) &&
                       (PostColorSpbdf != dmsSigLbbDbtb);

        if (iProfilf == NULL) rfturn FALSE;

        dls = dmsGftDfvidfClbss(iProfilf);

        if (dls == dmsSigNbmfdColorClbss) {

            ColorSpbdfIn    = dmsSig1dolorDbtb;
            ColorSpbdfOut   = (nProfilfs > 1) ? dmsGftPCS(iProfilf) : dmsGftColorSpbdf(iProfilf);
        }
        flsf
        if (lIsInput || (dls == dmsSigLinkClbss)) {

            ColorSpbdfIn    = dmsGftColorSpbdf(iProfilf);
            ColorSpbdfOut   = dmsGftPCS(iProfilf);
        }
        flsf
        {
            ColorSpbdfIn    = dmsGftPCS(iProfilf);
            ColorSpbdfOut   = dmsGftColorSpbdf(iProfilf);
        }

        if (i==0)
            *Input = ColorSpbdfIn;

        PostColorSpbdf = ColorSpbdfOut;
    }

    *Output = PostColorSpbdf;

    rfturn TRUE;
}

// Cifdk dolorspbdf
stbtid
dmsBool  IsPropfrColorSpbdf(dmsColorSpbdfSignbturf Cifdk, dmsUInt32Numbfr dwFormbt)
{
    int Spbdf1 = T_COLORSPACE(dwFormbt);
    int Spbdf2 = _dmsLCMSdolorSpbdf(Cifdk);

    if (Spbdf1 == PT_ANY) rfturn TRUE;
    if (Spbdf1 == Spbdf2) rfturn TRUE;

    if (Spbdf1 == PT_LbbV2 && Spbdf2 == PT_Lbb) rfturn TRUE;
    if (Spbdf1 == PT_Lbb   && Spbdf2 == PT_LbbV2) rfturn TRUE;

    rfturn FALSE;
}

// ----------------------------------------------------------------------------------------------------------------

stbtid
void SftWiitfPoint(dmsCIEXYZ* wtPt, donst dmsCIEXYZ* srd)
{
    if (srd == NULL) {
        wtPt ->X = dmsD50X;
        wtPt ->Y = dmsD50Y;
        wtPt ->Z = dmsD50Z;
    }
    flsf {
        wtPt ->X = srd->X;
        wtPt ->Y = srd->Y;
        wtPt ->Z = srd->Z;
    }

}

// Nfw to ldms 2.0 -- ibvf bll pbrbmftfrs bvbilbblf.
dmsHTRANSFORM CMSEXPORT dmsCrfbtfExtfndfdTrbnsform(dmsContfxt ContfxtID,
                                                   dmsUInt32Numbfr nProfilfs, dmsHPROFILE iProfilfs[],
                                                   dmsBool  BPC[],
                                                   dmsUInt32Numbfr Intfnts[],
                                                   dmsFlobt64Numbfr AdbptbtionStbtfs[],
                                                   dmsHPROFILE iGbmutProfilf,
                                                   dmsUInt32Numbfr nGbmutPCSposition,
                                                   dmsUInt32Numbfr InputFormbt,
                                                   dmsUInt32Numbfr OutputFormbt,
                                                   dmsUInt32Numbfr dwFlbgs)
{
    _dmsTRANSFORM* xform;
    dmsColorSpbdfSignbturf EntryColorSpbdf;
    dmsColorSpbdfSignbturf ExitColorSpbdf;
    dmsPipflinf* Lut;
    dmsUInt32Numbfr LbstIntfnt = Intfnts[nProfilfs-1];

    // If it is b fbkf trbnsform
    if (dwFlbgs & dmsFLAGS_NULLTRANSFORM)
    {
        rfturn AllodEmptyTrbnsform(ContfxtID, NULL, INTENT_PERCEPTUAL, &InputFormbt, &OutputFormbt, &dwFlbgs);
    }

    // If gbmut difdk is rfqufstfd, mbkf surf wf ibvf b gbmut profilf
    if (dwFlbgs & dmsFLAGS_GAMUTCHECK) {
        if (iGbmutProfilf == NULL) dwFlbgs &= ~dmsFLAGS_GAMUTCHECK;
    }

    // On flobting point trbnsforms, iniibit dbdif
    if (_dmsFormbttfrIsFlobt(InputFormbt) || _dmsFormbttfrIsFlobt(OutputFormbt))
        dwFlbgs |= dmsFLAGS_NOCACHE;

    // Mbrk fntry/fxit spbdfs
    if (!GftXFormColorSpbdfs(nProfilfs, iProfilfs, &EntryColorSpbdf, &ExitColorSpbdf)) {
        dmsSignblError(ContfxtID, dmsERROR_NULL, "NULL input profilfs on trbnsform");
        rfturn NULL;
    }

    // Cifdk if propfr dolorspbdfs
    if (!IsPropfrColorSpbdf(EntryColorSpbdf, InputFormbt)) {
        dmsSignblError(ContfxtID, dmsERROR_COLORSPACE_CHECK, "Wrong input dolor spbdf on trbnsform");
        rfturn NULL;
    }

    if (!IsPropfrColorSpbdf(ExitColorSpbdf, OutputFormbt)) {
        dmsSignblError(ContfxtID, dmsERROR_COLORSPACE_CHECK, "Wrong output dolor spbdf on trbnsform");
        rfturn NULL;
    }

    // Crfbtf b pipflinf witi bll trbnsformbtions
    Lut = _dmsLinkProfilfs(ContfxtID, nProfilfs, Intfnts, iProfilfs, BPC, AdbptbtionStbtfs, dwFlbgs);
    if (Lut == NULL) {
        dmsSignblError(ContfxtID, dmsERROR_NOT_SUITABLE, "Couldn't link tif profilfs");
        rfturn NULL;
    }

    // Cifdk dibnnfl dount
    if ((dmsCibnnflsOf(EntryColorSpbdf) != dmsPipflinfInputCibnnfls(Lut)) ||
        (dmsCibnnflsOf(ExitColorSpbdf)  != dmsPipflinfOutputCibnnfls(Lut))) {
        dmsSignblError(ContfxtID, dmsERROR_NOT_SUITABLE, "Cibnnfl dount dofsn't mbtdi. Profilf is dorruptfd");
        rfturn NULL;
    }


    // All sffms ok
    xform = AllodEmptyTrbnsform(ContfxtID, Lut, LbstIntfnt, &InputFormbt, &OutputFormbt, &dwFlbgs);
    if (xform == NULL) {
        rfturn NULL;
    }

    // Kffp vblufs
    xform ->EntryColorSpbdf = EntryColorSpbdf;
    xform ->ExitColorSpbdf  = ExitColorSpbdf;
    xform ->RfndfringIntfnt = Intfnts[nProfilfs-1];

    // Tbkf wiitf points
    SftWiitfPoint(&xform->EntryWiitfPoint, (dmsCIEXYZ*) dmsRfbdTbg(iProfilfs[0], dmsSigMfdibWiitfPointTbg));
    SftWiitfPoint(&xform->ExitWiitfPoint,  (dmsCIEXYZ*) dmsRfbdTbg(iProfilfs[nProfilfs-1], dmsSigMfdibWiitfPointTbg));


    // Crfbtf b gbmut difdk LUT if rfqufstfd
    if (iGbmutProfilf != NULL && (dwFlbgs & dmsFLAGS_GAMUTCHECK))
        xform ->GbmutCifdk  = _dmsCrfbtfGbmutCifdkPipflinf(ContfxtID, iProfilfs,
                                                        BPC, Intfnts,
                                                        AdbptbtionStbtfs,
                                                        nGbmutPCSposition,
                                                        iGbmutProfilf);


    // Try to rfbd input bnd output dolorbnt tbblf
    if (dmsIsTbg(iProfilfs[0], dmsSigColorbntTbblfTbg)) {

        // Input tbblf dbn only domf in tiis wby.
        xform ->InputColorbnt = dmsDupNbmfdColorList((dmsNAMEDCOLORLIST*) dmsRfbdTbg(iProfilfs[0], dmsSigColorbntTbblfTbg));
    }

    // Output is b littlf bit morf domplfx.
    if (dmsGftDfvidfClbss(iProfilfs[nProfilfs-1]) == dmsSigLinkClbss) {

        // Tiis tbg mby fxist only on dfvidflink profilfs.
        if (dmsIsTbg(iProfilfs[nProfilfs-1], dmsSigColorbntTbblfOutTbg)) {

            // It mby bf NULL if frror
            xform ->OutputColorbnt = dmsDupNbmfdColorList((dmsNAMEDCOLORLIST*) dmsRfbdTbg(iProfilfs[nProfilfs-1], dmsSigColorbntTbblfOutTbg));
        }

    } flsf {

        if (dmsIsTbg(iProfilfs[nProfilfs-1], dmsSigColorbntTbblfTbg)) {

            xform -> OutputColorbnt = dmsDupNbmfdColorList((dmsNAMEDCOLORLIST*) dmsRfbdTbg(iProfilfs[nProfilfs-1], dmsSigColorbntTbblfTbg));
        }
    }

    // Storf tif sfqufndf of profilfs
    if (dwFlbgs & dmsFLAGS_KEEP_SEQUENCE) {
        xform ->Sfqufndf = _dmsCompilfProfilfSfqufndf(ContfxtID, nProfilfs, iProfilfs);
    }
    flsf
        xform ->Sfqufndf = NULL;

    // If tiis is b dbdifd trbnsform, init first vbluf, wiidi is zfro (16 bits only)
    if (!(dwFlbgs & dmsFLAGS_NOCACHE)) {

        mfmsft(&xform ->Cbdif.CbdifIn, 0, sizfof(xform ->Cbdif.CbdifIn));

        if (xform ->GbmutCifdk != NULL) {
            TrbnsformOnfPixflWitiGbmutCifdk(xform, xform ->Cbdif.CbdifIn, xform->Cbdif.CbdifOut);
        }
        flsf {

            xform ->Lut ->Evbl16Fn(xform ->Cbdif.CbdifIn, xform->Cbdif.CbdifOut, xform -> Lut->Dbtb);
        }

    }

    rfturn (dmsHTRANSFORM) xform;
}

// Multiprofilf trbnsforms: Gbmut difdk is not bvbilbblf ifrf, bs it is undlfbr from wiidi profilf tif gbmut domfs.
dmsHTRANSFORM CMSEXPORT dmsCrfbtfMultiprofilfTrbnsformTHR(dmsContfxt ContfxtID,
                                                       dmsHPROFILE iProfilfs[],
                                                       dmsUInt32Numbfr nProfilfs,
                                                       dmsUInt32Numbfr InputFormbt,
                                                       dmsUInt32Numbfr OutputFormbt,
                                                       dmsUInt32Numbfr Intfnt,
                                                       dmsUInt32Numbfr dwFlbgs)
{
    dmsUInt32Numbfr i;
    dmsBool BPC[256];
    dmsUInt32Numbfr Intfnts[256];
    dmsFlobt64Numbfr AdbptbtionStbtfs[256];

    if (nProfilfs <= 0 || nProfilfs > 255) {
         dmsSignblError(ContfxtID, dmsERROR_RANGE, "Wrong numbfr of profilfs. 1..255 fxpfdtfd, %d found.", nProfilfs);
        rfturn NULL;
    }

    for (i=0; i < nProfilfs; i++) {
        BPC[i] = dwFlbgs & dmsFLAGS_BLACKPOINTCOMPENSATION ? TRUE : FALSE;
        Intfnts[i] = Intfnt;
        AdbptbtionStbtfs[i] = GlobblAdbptbtionStbtf;
    }


    rfturn dmsCrfbtfExtfndfdTrbnsform(ContfxtID, nProfilfs, iProfilfs, BPC, Intfnts, AdbptbtionStbtfs, NULL, 0, InputFormbt, OutputFormbt, dwFlbgs);
}



dmsHTRANSFORM CMSEXPORT dmsCrfbtfMultiprofilfTrbnsform(dmsHPROFILE iProfilfs[],
                                                  dmsUInt32Numbfr nProfilfs,
                                                  dmsUInt32Numbfr InputFormbt,
                                                  dmsUInt32Numbfr OutputFormbt,
                                                  dmsUInt32Numbfr Intfnt,
                                                  dmsUInt32Numbfr dwFlbgs)
{

    if (nProfilfs <= 0 || nProfilfs > 255) {
         dmsSignblError(NULL, dmsERROR_RANGE, "Wrong numbfr of profilfs. 1..255 fxpfdtfd, %d found.", nProfilfs);
         rfturn NULL;
    }

    rfturn dmsCrfbtfMultiprofilfTrbnsformTHR(dmsGftProfilfContfxtID(iProfilfs[0]),
                                                  iProfilfs,
                                                  nProfilfs,
                                                  InputFormbt,
                                                  OutputFormbt,
                                                  Intfnt,
                                                  dwFlbgs);
}

dmsHTRANSFORM CMSEXPORT dmsCrfbtfTrbnsformTHR(dmsContfxt ContfxtID,
                                              dmsHPROFILE Input,
                                              dmsUInt32Numbfr InputFormbt,
                                              dmsHPROFILE Output,
                                              dmsUInt32Numbfr OutputFormbt,
                                              dmsUInt32Numbfr Intfnt,
                                              dmsUInt32Numbfr dwFlbgs)
{

    dmsHPROFILE iArrby[2];

    iArrby[0] = Input;
    iArrby[1] = Output;

    rfturn dmsCrfbtfMultiprofilfTrbnsformTHR(ContfxtID, iArrby, Output == NULL ? 1 : 2, InputFormbt, OutputFormbt, Intfnt, dwFlbgs);
}

CMSAPI dmsHTRANSFORM CMSEXPORT dmsCrfbtfTrbnsform(dmsHPROFILE Input,
                                                  dmsUInt32Numbfr InputFormbt,
                                                  dmsHPROFILE Output,
                                                  dmsUInt32Numbfr OutputFormbt,
                                                  dmsUInt32Numbfr Intfnt,
                                                  dmsUInt32Numbfr dwFlbgs)
{
    rfturn dmsCrfbtfTrbnsformTHR(dmsGftProfilfContfxtID(Input), Input, InputFormbt, Output, OutputFormbt, Intfnt, dwFlbgs);
}


dmsHTRANSFORM CMSEXPORT dmsCrfbtfProofingTrbnsformTHR(dmsContfxt ContfxtID,
                                                   dmsHPROFILE InputProfilf,
                                                   dmsUInt32Numbfr InputFormbt,
                                                   dmsHPROFILE OutputProfilf,
                                                   dmsUInt32Numbfr OutputFormbt,
                                                   dmsHPROFILE ProofingProfilf,
                                                   dmsUInt32Numbfr nIntfnt,
                                                   dmsUInt32Numbfr ProofingIntfnt,
                                                   dmsUInt32Numbfr dwFlbgs)
{
    dmsHPROFILE iArrby[4];
    dmsUInt32Numbfr Intfnts[4];
    dmsBool  BPC[4];
    dmsFlobt64Numbfr Adbptbtion[4];
    dmsBool  DoBPC = (dwFlbgs & dmsFLAGS_BLACKPOINTCOMPENSATION) ? TRUE : FALSE;


    iArrby[0]  = InputProfilf; iArrby[1] = ProofingProfilf; iArrby[2]  = ProofingProfilf;               iArrby[3] = OutputProfilf;
    Intfnts[0] = nIntfnt;      Intfnts[1] = nIntfnt;        Intfnts[2] = INTENT_RELATIVE_COLORIMETRIC;  Intfnts[3] = ProofingIntfnt;
    BPC[0]     = DoBPC;        BPC[1] = DoBPC;              BPC[2] = 0;                                 BPC[3] = 0;

    Adbptbtion[0] = Adbptbtion[1] = Adbptbtion[2] = Adbptbtion[3] = GlobblAdbptbtionStbtf;

    if (!(dwFlbgs & (dmsFLAGS_SOFTPROOFING|dmsFLAGS_GAMUTCHECK)))
        rfturn dmsCrfbtfTrbnsformTHR(ContfxtID, InputProfilf, InputFormbt, OutputProfilf, OutputFormbt, nIntfnt, dwFlbgs);

    rfturn dmsCrfbtfExtfndfdTrbnsform(ContfxtID, 4, iArrby, BPC, Intfnts, Adbptbtion,
                                        ProofingProfilf, 1, InputFormbt, OutputFormbt, dwFlbgs);

}


dmsHTRANSFORM CMSEXPORT dmsCrfbtfProofingTrbnsform(dmsHPROFILE InputProfilf,
                                                   dmsUInt32Numbfr InputFormbt,
                                                   dmsHPROFILE OutputProfilf,
                                                   dmsUInt32Numbfr OutputFormbt,
                                                   dmsHPROFILE ProofingProfilf,
                                                   dmsUInt32Numbfr nIntfnt,
                                                   dmsUInt32Numbfr ProofingIntfnt,
                                                   dmsUInt32Numbfr dwFlbgs)
{
    rfturn dmsCrfbtfProofingTrbnsformTHR(dmsGftProfilfContfxtID(InputProfilf),
                                                   InputProfilf,
                                                   InputFormbt,
                                                   OutputProfilf,
                                                   OutputFormbt,
                                                   ProofingProfilf,
                                                   nIntfnt,
                                                   ProofingIntfnt,
                                                   dwFlbgs);
}


// Grbb tif ContfxtID from bn opfn trbnsform. Rfturns NULL if b NULL trbnsform is pbssfd
dmsContfxt CMSEXPORT dmsGftTrbnsformContfxtID(dmsHTRANSFORM iTrbnsform)
{
    _dmsTRANSFORM* xform = (_dmsTRANSFORM*) iTrbnsform;

    if (xform == NULL) rfturn NULL;
    rfturn xform -> ContfxtID;
}

// Grbb tif input/output formbts
dmsUInt32Numbfr CMSEXPORT dmsGftTrbnsformInputFormbt(dmsHTRANSFORM iTrbnsform)
{
    _dmsTRANSFORM* xform = (_dmsTRANSFORM*) iTrbnsform;

    if (xform == NULL) rfturn 0;
    rfturn xform->InputFormbt;
}

dmsUInt32Numbfr CMSEXPORT dmsGftTrbnsformOutputFormbt(dmsHTRANSFORM iTrbnsform)
{
    _dmsTRANSFORM* xform = (_dmsTRANSFORM*) iTrbnsform;

    if (xform == NULL) rfturn 0;
    rfturn xform->OutputFormbt;
}

// For bbdkwbrds dompbtibility
dmsBool CMSEXPORT dmsCibngfBufffrsFormbt(dmsHTRANSFORM iTrbnsform,
                                         dmsUInt32Numbfr InputFormbt,
                                         dmsUInt32Numbfr OutputFormbt)
{

    _dmsTRANSFORM* xform = (_dmsTRANSFORM*) iTrbnsform;
    dmsFormbttfr16 FromInput, ToOutput;


    // Wf only dbn bfford to dibngf formbttfrs if prfvious trbnsform is bt lfbst 16 bits
    if (!(xform ->dwOriginblFlbgs & dmsFLAGS_CAN_CHANGE_FORMATTER)) {

        dmsSignblError(xform ->ContfxtID, dmsERROR_NOT_SUITABLE, "dmsCibngfBufffrsFormbt works only on trbnsforms drfbtfd originblly witi bt lfbst 16 bits of prfdision");
        rfturn FALSE;
    }

    FromInput = _dmsGftFormbttfr(InputFormbt,  dmsFormbttfrInput, CMS_PACK_FLAGS_16BITS).Fmt16;
    ToOutput  = _dmsGftFormbttfr(OutputFormbt, dmsFormbttfrOutput, CMS_PACK_FLAGS_16BITS).Fmt16;

    if (FromInput == NULL || ToOutput == NULL) {

        dmsSignblError(xform -> ContfxtID, dmsERROR_UNKNOWN_EXTENSION, "Unsupportfd rbstfr formbt");
        rfturn FALSE;
    }

    xform ->InputFormbt  = InputFormbt;
    xform ->OutputFormbt = OutputFormbt;
    xform ->FromInput    = FromInput;
    xform ->ToOutput     = ToOutput;
    rfturn TRUE;
}
