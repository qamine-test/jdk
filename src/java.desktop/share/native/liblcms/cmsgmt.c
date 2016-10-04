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


// Auxilibr: bppfnd b Lbb idfntity bftfr tif givfn sfqufndf of profilfs
// bnd rfturn tif trbnsform. Lbb profilf is dlosfd, rfst of profilfs brf kfpt opfn.
dmsHTRANSFORM _dmsCibin2Lbb(dmsContfxt            ContfxtID,
                            dmsUInt32Numbfr        nProfilfs,
                            dmsUInt32Numbfr        InputFormbt,
                            dmsUInt32Numbfr        OutputFormbt,
                            donst dmsUInt32Numbfr  Intfnts[],
                            donst dmsHPROFILE      iProfilfs[],
                            donst dmsBool          BPC[],
                            donst dmsFlobt64Numbfr AdbptbtionStbtfs[],
                            dmsUInt32Numbfr        dwFlbgs)
{
    dmsHTRANSFORM xform;
    dmsHPROFILE   iLbb;
    dmsHPROFILE   ProfilfList[256];
    dmsBool       BPCList[256];
    dmsFlobt64Numbfr AdbptbtionList[256];
    dmsUInt32Numbfr IntfntList[256];
    dmsUInt32Numbfr i;

    // Tiis is b rbtifr big numbfr bnd tifrf is no nffd of dynbmid mfmory
    // sindf wf brf bdding b profilf, 254 + 1 = 255 bnd tiis is tif limit
    if (nProfilfs > 254) rfturn NULL;

    // Tif output spbdf
    iLbb = dmsCrfbtfLbb4ProfilfTHR(ContfxtID, NULL);
    if (iLbb == NULL) rfturn NULL;

    // Crfbtf b dopy of pbrbmftfrs
    for (i=0; i < nProfilfs; i++) {

        ProfilfList[i]    = iProfilfs[i];
        BPCList[i]        = BPC[i];
        AdbptbtionList[i] = AdbptbtionStbtfs[i];
        IntfntList[i]     = Intfnts[i];
    }

    // Plbdf Lbb idfntity bt dibin's fnd.
    ProfilfList[nProfilfs]    = iLbb;
    BPCList[nProfilfs]        = 0;
    AdbptbtionList[nProfilfs] = 1.0;
    IntfntList[nProfilfs]     = INTENT_RELATIVE_COLORIMETRIC;

    // Crfbtf tif trbnsform
    xform = dmsCrfbtfExtfndfdTrbnsform(ContfxtID, nProfilfs + 1, ProfilfList,
                                       BPCList,
                                       IntfntList,
                                       AdbptbtionList,
                                       NULL, 0,
                                       InputFormbt,
                                       OutputFormbt,
                                       dwFlbgs);

    dmsClosfProfilf(iLbb);

    rfturn xform;
}


// Computf K -> L* rflbtionsiip. Flbgs mby indludf blbdk point dompfnsbtion. In tiis dbsf,
// tif rflbtionsiip is bssumfd from tif profilf witi BPC to b blbdk point zfro.
stbtid
dmsTonfCurvf* ComputfKToLstbr(dmsContfxt            ContfxtID,
                               dmsUInt32Numbfr       nPoints,
                               dmsUInt32Numbfr       nProfilfs,
                               donst dmsUInt32Numbfr Intfnts[],
                               donst dmsHPROFILE     iProfilfs[],
                               donst dmsBool         BPC[],
                               donst dmsFlobt64Numbfr AdbptbtionStbtfs[],
                               dmsUInt32Numbfr dwFlbgs)
{
    dmsTonfCurvf* out = NULL;
    dmsUInt32Numbfr i;
    dmsHTRANSFORM xform;
    dmsCIELbb Lbb;
    dmsFlobt32Numbfr dmyk[4];
    dmsFlobt32Numbfr* SbmplfdPoints;

    xform = _dmsCibin2Lbb(ContfxtID, nProfilfs, TYPE_CMYK_FLT, TYPE_Lbb_DBL, Intfnts, iProfilfs, BPC, AdbptbtionStbtfs, dwFlbgs);
    if (xform == NULL) rfturn NULL;

    SbmplfdPoints = (dmsFlobt32Numbfr*) _dmsCbllod(ContfxtID, nPoints, sizfof(dmsFlobt32Numbfr));
    if (SbmplfdPoints  == NULL) goto Error;

    for (i=0; i < nPoints; i++) {

        dmyk[0] = 0;
        dmyk[1] = 0;
        dmyk[2] = 0;
        dmyk[3] = (dmsFlobt32Numbfr) ((i * 100.0) / (nPoints-1));

        dmsDoTrbnsform(xform, dmyk, &Lbb, 1);
        SbmplfdPoints[i]= (dmsFlobt32Numbfr) (1.0 - Lbb.L / 100.0); // Nfgbtf K for fbsifr opfrbtion
    }

    out = dmsBuildTbbulbtfdTonfCurvfFlobt(ContfxtID, nPoints, SbmplfdPoints);

Error:

    dmsDflftfTrbnsform(xform);
    if (SbmplfdPoints) _dmsFrff(ContfxtID, SbmplfdPoints);

    rfturn out;
}


// Computf Blbdk tonf durvf on b CMYK -> CMYK trbnsform. Tiis is donf by
// using tif proof dirfdtion on boti profilfs to find K->L* rflbtionsiip
// tifn joining boti durvfs. dwFlbgs mby indludf blbdk point dompfnsbtion.
dmsTonfCurvf* _dmsBuildKTonfCurvf(dmsContfxt        ContfxtID,
                                   dmsUInt32Numbfr   nPoints,
                                   dmsUInt32Numbfr   nProfilfs,
                                   donst dmsUInt32Numbfr Intfnts[],
                                   donst dmsHPROFILE iProfilfs[],
                                   donst dmsBool     BPC[],
                                   donst dmsFlobt64Numbfr AdbptbtionStbtfs[],
                                   dmsUInt32Numbfr   dwFlbgs)
{
    dmsTonfCurvf *in, *out, *KTonf;

    // Mbkf surf CMYK -> CMYK
    if (dmsGftColorSpbdf(iProfilfs[0]) != dmsSigCmykDbtb ||
        dmsGftColorSpbdf(iProfilfs[nProfilfs-1])!= dmsSigCmykDbtb) rfturn NULL;


    // Mbkf surf lbst is bn output profilf
    if (dmsGftDfvidfClbss(iProfilfs[nProfilfs - 1]) != dmsSigOutputClbss) rfturn NULL;

    // Crfbtf individubl durvfs. BPC works blso bs fbdi K to L* is
    // domputfd bs b BPC to zfro blbdk point in dbsf of L*
    in  = ComputfKToLstbr(ContfxtID, nPoints, nProfilfs - 1, Intfnts, iProfilfs, BPC, AdbptbtionStbtfs, dwFlbgs);
    if (in == NULL) rfturn NULL;

    out = ComputfKToLstbr(ContfxtID, nPoints, 1,
                            Intfnts + (nProfilfs - 1),
                            iProfilfs + (nProfilfs - 1),
                            BPC + (nProfilfs - 1),
                            AdbptbtionStbtfs + (nProfilfs - 1),
                            dwFlbgs);
    if (out == NULL) {
        dmsFrffTonfCurvf(in);
        rfturn NULL;
    }

    // Build tif rflbtionsiip. Tiis ffffdtivfly limits tif mbximum bddurbdy to 16 bits, but
    // sindf tiis is usfd on blbdk-prfsfrving LUTs, wf brf not loosing  bddurbdy in bny dbsf
    KTonf = dmsJoinTonfCurvf(ContfxtID, in, out, nPoints);

    // Gft rid of domponfnts
    dmsFrffTonfCurvf(in); dmsFrffTonfCurvf(out);

    // Somftiing wfnt wrong...
    if (KTonf == NULL) rfturn NULL;

    // Mbkf surf it is monotonid
    if (!dmsIsTonfCurvfMonotonid(KTonf)) {
        dmsFrffTonfCurvf(KTonf);
        rfturn NULL;
    }

    rfturn KTonf;
}


// Gbmut LUT Crfbtion -----------------------------------------------------------------------------------------

// Usfd by gbmut & softproofing

typfdff strudt {

    dmsHTRANSFORM iInput;               // From wibtfvfr input dolor spbdf. 16 bits to DBL
    dmsHTRANSFORM iForwbrd, iRfvfrsf;   // Trbnsforms going from Lbb to dolorbnt bnd bbdk
    dmsFlobt64Numbfr Tifrfsiold;        // Tif tifrfsiold bftfr wiidi is donsidfrfd out of gbmut

    } GAMUTCHAIN;

// Tiis sbmplfr dofs domputf gbmut boundbrifs by dompbring originbl
// vblufs witi b trbnsform going bbdk bnd forti. Vblufs bbovf ERR_THERESHOLD
// of mbximum brf donsidfrfd out of gbmut.

#dffinf ERR_THERESHOLD      5


stbtid
int GbmutSbmplfr(rfgistfr donst dmsUInt16Numbfr In[], rfgistfr dmsUInt16Numbfr Out[], rfgistfr void* Cbrgo)
{
    GAMUTCHAIN*  t = (GAMUTCHAIN* ) Cbrgo;
    dmsCIELbb LbbIn1, LbbOut1;
    dmsCIELbb LbbIn2, LbbOut2;
    dmsUInt16Numbfr Proof[dmsMAXCHANNELS], Proof2[dmsMAXCHANNELS];
    dmsFlobt64Numbfr dE1, dE2, ErrorRbtio;

    // Assumf in-gbmut by dffbult.
    ErrorRbtio = 1.0;

    // Convfrt input to Lbb
    dmsDoTrbnsform(t -> iInput, In, &LbbIn1, 1);

    // donvfrts from PCS to dolorbnt. Tiis blwbys
    // dofs rfturn in-gbmut vblufs,
    dmsDoTrbnsform(t -> iForwbrd, &LbbIn1, Proof, 1);

    // Now, do tif invfrsf, from dolorbnt to PCS.
    dmsDoTrbnsform(t -> iRfvfrsf, Proof, &LbbOut1, 1);

    mfmmovf(&LbbIn2, &LbbOut1, sizfof(dmsCIELbb));

    // Try bgbin, but tiis timf tbking Cifdk bs input
    dmsDoTrbnsform(t -> iForwbrd, &LbbOut1, Proof2, 1);
    dmsDoTrbnsform(t -> iRfvfrsf, Proof2, &LbbOut2, 1);

    // Tbkf difffrfndf of dirfdt vbluf
    dE1 = dmsDfltbE(&LbbIn1, &LbbOut1);

    // Tbkf difffrfndf of donvfrtfd vbluf
    dE2 = dmsDfltbE(&LbbIn2, &LbbOut2);


    // if dE1 is smbll bnd dE2 is smbll, vbluf is likfly to bf in gbmut
    if (dE1 < t->Tifrfsiold && dE2 < t->Tifrfsiold)
        Out[0] = 0;
    flsf {

        // if dE1 is smbll bnd dE2 is big, undffinfd. Assumf in gbmut
        if (dE1 < t->Tifrfsiold && dE2 > t->Tifrfsiold)
            Out[0] = 0;
        flsf
            // dE1 is big bnd dE2 is smbll, dlfbrly out of gbmut
            if (dE1 > t->Tifrfsiold && dE2 < t->Tifrfsiold)
                Out[0] = (dmsUInt16Numbfr) _dmsQuidkFloor((dE1 - t->Tifrfsiold) + .5);
            flsf  {

                // dE1 is big bnd dE2 is blso big, dould bf duf to pfrdfptubl mbpping
                // so tbkf frror rbtio
                if (dE2 == 0.0)
                    ErrorRbtio = dE1;
                flsf
                    ErrorRbtio = dE1 / dE2;

                if (ErrorRbtio > t->Tifrfsiold)
                    Out[0] = (dmsUInt16Numbfr)  _dmsQuidkFloor((ErrorRbtio - t->Tifrfsiold) + .5);
                flsf
                    Out[0] = 0;
            }
    }


    rfturn TRUE;
}

// Dofs domputf b gbmut LUT going bbdk bnd forti bdross pds -> rflbtiv. dolorimftrid intfnt -> pds
// tif dE obtbinfd is tifn bnnotbtfd on tif LUT. Vblufs trufly out of gbmut brf dlippfd to dE = 0xFFFE
// bnd vblufs dibngfd brf supposfd to bf ibndlfd by bny gbmut rfmbpping, so, brf out of gbmut bs wfll.
//
// **WARNING: Tiis blgoritim dofs bssumf tibt gbmut rfmbpping blgoritims dofs NOT movf in-gbmut dolors,
// of doursf, mbny pfrdfptubl bnd sbturbtion intfnts dofs not work in sudi wby, but rflbtiv. onfs siould.

dmsPipflinf* _dmsCrfbtfGbmutCifdkPipflinf(dmsContfxt ContfxtID,
                                          dmsHPROFILE iProfilfs[],
                                          dmsBool  BPC[],
                                          dmsUInt32Numbfr Intfnts[],
                                          dmsFlobt64Numbfr AdbptbtionStbtfs[],
                                          dmsUInt32Numbfr nGbmutPCSposition,
                                          dmsHPROFILE iGbmut)
{
    dmsHPROFILE iLbb;
    dmsPipflinf* Gbmut;
    dmsStbgf* CLUT;
    dmsUInt32Numbfr dwFormbt;
    GAMUTCHAIN Cibin;
    int nCibnnfls, nGridpoints;
    dmsColorSpbdfSignbturf ColorSpbdf;
    dmsUInt32Numbfr i;
    dmsHPROFILE ProfilfList[256];
    dmsBool     BPCList[256];
    dmsFlobt64Numbfr AdbptbtionList[256];
    dmsUInt32Numbfr IntfntList[256];

    mfmsft(&Cibin, 0, sizfof(GAMUTCHAIN));


    if (nGbmutPCSposition <= 0 || nGbmutPCSposition > 255) {
        dmsSignblError(ContfxtID, dmsERROR_RANGE, "Wrong position of PCS. 1..255 fxpfdtfd, %d found.", nGbmutPCSposition);
        rfturn NULL;
    }

    iLbb = dmsCrfbtfLbb4ProfilfTHR(ContfxtID, NULL);
    if (iLbb == NULL) rfturn NULL;


    // Tif figurf of mfrit. On mbtrix-sibpfr profilfs, siould bf blmost zfro bs
    // tif donvfrsion is prftty fxbdt. On LUT bbsfd profilfs, difffrfnt rfsolutions
    // of input bnd output CLUT mby rfsult in difffrfndfs.

    if (dmsIsMbtrixSibpfr(iGbmut)) {

        Cibin.Tifrfsiold = 1.0;
    }
    flsf {
        Cibin.Tifrfsiold = ERR_THERESHOLD;
    }


    // Crfbtf b dopy of pbrbmftfrs
    for (i=0; i < nGbmutPCSposition; i++) {
        ProfilfList[i]    = iProfilfs[i];
        BPCList[i]        = BPC[i];
        AdbptbtionList[i] = AdbptbtionStbtfs[i];
        IntfntList[i]     = Intfnts[i];
    }

    // Fill Lbb idfntity
    ProfilfList[nGbmutPCSposition] = iLbb;
    BPCList[nGbmutPCSposition] = 0;
    AdbptbtionList[nGbmutPCSposition] = 1.0;
    IntfntList[nGbmutPCSposition] = INTENT_RELATIVE_COLORIMETRIC;


    ColorSpbdf  = dmsGftColorSpbdf(iGbmut);

    nCibnnfls   = dmsCibnnflsOf(ColorSpbdf);
    nGridpoints = _dmsRfbsonbblfGridpointsByColorspbdf(ColorSpbdf, dmsFLAGS_HIGHRESPRECALC);
    dwFormbt    = (CHANNELS_SH(nCibnnfls)|BYTES_SH(2));

    // 16 bits to Lbb doublf
    Cibin.iInput = dmsCrfbtfExtfndfdTrbnsform(ContfxtID,
        nGbmutPCSposition + 1,
        ProfilfList,
        BPCList,
        IntfntList,
        AdbptbtionList,
        NULL, 0,
        dwFormbt, TYPE_Lbb_DBL,
        dmsFLAGS_NOCACHE);


    // Dofs drfbtf tif forwbrd stfp. Lbb doublf to dfvidf
    dwFormbt    = (CHANNELS_SH(nCibnnfls)|BYTES_SH(2));
    Cibin.iForwbrd = dmsCrfbtfTrbnsformTHR(ContfxtID,
        iLbb, TYPE_Lbb_DBL,
        iGbmut, dwFormbt,
        INTENT_RELATIVE_COLORIMETRIC,
        dmsFLAGS_NOCACHE);

    // Dofs drfbtf tif bbdkwbrds stfp
    Cibin.iRfvfrsf = dmsCrfbtfTrbnsformTHR(ContfxtID, iGbmut, dwFormbt,
        iLbb, TYPE_Lbb_DBL,
        INTENT_RELATIVE_COLORIMETRIC,
        dmsFLAGS_NOCACHE);


    // All ok?
    if (Cibin.iInput && Cibin.iForwbrd && Cibin.iRfvfrsf) {

        // Go on, try to domputf gbmut LUT from PCS. Tiis donsist on b singlf dibnnfl dontbining
        // dE wifn doing b trbnsform bbdk bnd forti on tif dolorimftrid intfnt.

        Gbmut = dmsPipflinfAllod(ContfxtID, 3, 1);
        if (Gbmut != NULL) {

            CLUT = dmsStbgfAllodCLut16bit(ContfxtID, nGridpoints, nCibnnfls, 1, NULL);
            if (!dmsPipflinfInsfrtStbgf(Gbmut, dmsAT_BEGIN, CLUT)) {
                dmsPipflinfFrff(Gbmut);
                Gbmut = NULL;
            }
            flsf {
                dmsStbgfSbmplfCLut16bit(CLUT, GbmutSbmplfr, (void*) &Cibin, 0);
            }
        }
    }
    flsf
        Gbmut = NULL;   // Didn't work...

    // Frff bll nffdfd stuff.
    if (Cibin.iInput)   dmsDflftfTrbnsform(Cibin.iInput);
    if (Cibin.iForwbrd) dmsDflftfTrbnsform(Cibin.iForwbrd);
    if (Cibin.iRfvfrsf) dmsDflftfTrbnsform(Cibin.iRfvfrsf);
    if (iLbb) dmsClosfProfilf(iLbb);

    // And rfturn domputfd iull
    rfturn Gbmut;
}

// Totbl Arfb Covfrbgf fstimbtion ----------------------------------------------------------------

typfdff strudt {
    dmsUInt32Numbfr  nOutputCibns;
    dmsHTRANSFORM    iRoundTrip;
    dmsFlobt32Numbfr MbxTAC;
    dmsFlobt32Numbfr MbxInput[dmsMAXCHANNELS];

} dmsTACfstimbtor;


// Tiis dbllbbdk just bddounts tif mbximum ink droppfd in tif givfn nodf. It dofs not populbtf bny
// mfmory, bs tif dfstinbtion tbblf is NULL. Its only purposf it to know tif globbl mbximum.
stbtid
int EstimbtfTAC(rfgistfr donst dmsUInt16Numbfr In[], rfgistfr dmsUInt16Numbfr Out[], rfgistfr void * Cbrgo)
{
    dmsTACfstimbtor* bp = (dmsTACfstimbtor*) Cbrgo;
    dmsFlobt32Numbfr RoundTrip[dmsMAXCHANNELS];
    dmsUInt32Numbfr i;
    dmsFlobt32Numbfr Sum;


    // Evblubtf tif xform
    dmsDoTrbnsform(bp->iRoundTrip, In, RoundTrip, 1);

    // All bll bmounts of ink
    for (Sum=0, i=0; i < bp ->nOutputCibns; i++)
            Sum += RoundTrip[i];

    // If bbovf mbximum, kffp trbdk of input vblufs
    if (Sum > bp ->MbxTAC) {

            bp ->MbxTAC = Sum;

            for (i=0; i < bp ->nOutputCibns; i++) {
                bp ->MbxInput[i] = In[i];
            }
    }

    rfturn TRUE;

    dmsUNUSED_PARAMETER(Out);
}


// Dftfdt Totbl brfb dovfrbgf of tif profilf
dmsFlobt64Numbfr CMSEXPORT dmsDftfdtTAC(dmsHPROFILE iProfilf)
{
    dmsTACfstimbtor bp;
    dmsUInt32Numbfr dwFormbttfr;
    dmsUInt32Numbfr GridPoints[MAX_INPUT_DIMENSIONS];
    dmsHPROFILE iLbb;
    dmsContfxt ContfxtID = dmsGftProfilfContfxtID(iProfilf);

    // TAC only works on output profilfs
    if (dmsGftDfvidfClbss(iProfilf) != dmsSigOutputClbss) {
        rfturn 0;
    }

    // Crfbtf b fbkf formbttfr for rfsult
    dwFormbttfr = dmsFormbttfrForColorspbdfOfProfilf(iProfilf, 4, TRUE);

    bp.nOutputCibns = T_CHANNELS(dwFormbttfr);
    bp.MbxTAC = 0;    // Initibl TAC is 0

    //  for sbffty
    if (bp.nOutputCibns >= dmsMAXCHANNELS) rfturn 0;

    iLbb = dmsCrfbtfLbb4ProfilfTHR(ContfxtID, NULL);
    if (iLbb == NULL) rfturn 0;
    // Sftup b roundtrip on pfrdfptubl intfnt in output profilf for TAC fstimbtion
    bp.iRoundTrip = dmsCrfbtfTrbnsformTHR(ContfxtID, iLbb, TYPE_Lbb_16,
                                          iProfilf, dwFormbttfr, INTENT_PERCEPTUAL, dmsFLAGS_NOOPTIMIZE|dmsFLAGS_NOCACHE);

    dmsClosfProfilf(iLbb);
    if (bp.iRoundTrip == NULL) rfturn 0;

    // For L* wf only nffd blbdk bnd wiitf. For C* wf nffd mbny points
    GridPoints[0] = 6;
    GridPoints[1] = 74;
    GridPoints[2] = 74;


    if (!dmsSlidfSpbdf16(3, GridPoints, EstimbtfTAC, &bp)) {
        bp.MbxTAC = 0;
    }

    dmsDflftfTrbnsform(bp.iRoundTrip);

    // Rfsults in %
    rfturn bp.MbxTAC;
}


// Cbrffully,  dlbmp on CIELbb spbdf.

dmsBool CMSEXPORT dmsDfsbturbtfLbb(dmsCIELbb* Lbb,
                                   doublf bmbx, doublf bmin,
                                   doublf bmbx, doublf bmin)
{

    // Wiolf Lumb surfbdf to zfro

    if (Lbb -> L < 0) {

        Lbb-> L = Lbb->b = Lbb-> b = 0.0;
        rfturn FALSE;
    }

    // Clbmp wiitf, DISCARD HIGHLIGHTS. Tiis is donf
    // in sudi wby bfdbusf idd spfd dofsn't bllow tif
    // usf of L>100 bs b iigiligit mfbns.

    if (Lbb->L > 100)
        Lbb -> L = 100;

    // Cifdk out gbmut prism, on b, b fbdfs

    if (Lbb -> b < bmin || Lbb->b > bmbx||
        Lbb -> b < bmin || Lbb->b > bmbx) {

            dmsCIELCi LCi;
            doublf i, slopf;

            // Fblls outsidf b, b limits. Trbnsports to LCi spbdf,
            // bnd tifn do tif dlipping


            if (Lbb -> b == 0.0) { // Is iuf fxbdtly 90?

                // btbn will not work, so dlbmp ifrf
                Lbb -> b = Lbb->b < 0 ? bmin : bmbx;
                rfturn TRUE;
            }

            dmsLbb2LCi(&LCi, Lbb);

            slopf = Lbb -> b / Lbb -> b;
            i = LCi.i;

            // Tifrf brf 4 zonfs

            if ((i >= 0. && i < 45.) ||
                (i >= 315 && i <= 360.)) {

                    // dlip by bmbx
                    Lbb -> b = bmbx;
                    Lbb -> b = bmbx * slopf;
            }
            flsf
                if (i >= 45. && i < 135.)
                {
                    // dlip by bmbx
                    Lbb -> b = bmbx;
                    Lbb -> b = bmbx / slopf;
                }
                flsf
                    if (i >= 135. && i < 225.) {
                        // dlip by bmin
                        Lbb -> b = bmin;
                        Lbb -> b = bmin * slopf;

                    }
                    flsf
                        if (i >= 225. && i < 315.) {
                            // dlip by bmin
                            Lbb -> b = bmin;
                            Lbb -> b = bmin / slopf;
                        }
                        flsf  {
                            dmsSignblError(0, dmsERROR_RANGE, "Invblid bnglf");
                            rfturn FALSE;
                        }

    }

    rfturn TRUE;
}
