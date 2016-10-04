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


// Link sfvfrbl profilfs to obtbin b singlf LUT modflling tif wiolf dolor trbnsform. Intfnts, Blbdk point
// dompfnsbtion bnd Adbptbtion pbrbmftfrs mby vbry bdross profilfs. BPC bnd Adbptbtion rfffrs to tif PCS
// bftfr tif profilf. I.f, BPC[0] rfffrs to donnfxion bftwffn profilf(0) bnd profilf(1)
dmsPipflinf* _dmsLinkProfilfs(dmsContfxt     ContfxtID,
                              dmsUInt32Numbfr nProfilfs,
                              dmsUInt32Numbfr Intfnts[],
                              dmsHPROFILE     iProfilfs[],
                              dmsBool         BPC[],
                              dmsFlobt64Numbfr AdbptbtionStbtfs[],
                              dmsUInt32Numbfr dwFlbgs);

//---------------------------------------------------------------------------------

// Tiis is tif dffbult routinf for ICC-stylf intfnts. A usfr mby dfdidf to ovfrridf it by using b plugin.
// Supportfd intfnts brf pfrdfptubl, rflbtivf dolorimftrid, sbturbtion bnd ICC-bbsolutf dolorimftrid
stbtid
dmsPipflinf* DffbultICCintfnts(dmsContfxt     ContfxtID,
                               dmsUInt32Numbfr nProfilfs,
                               dmsUInt32Numbfr Intfnts[],
                               dmsHPROFILE     iProfilfs[],
                               dmsBool         BPC[],
                               dmsFlobt64Numbfr AdbptbtionStbtfs[],
                               dmsUInt32Numbfr dwFlbgs);

//---------------------------------------------------------------------------------

// Tiis is tif fntry for blbdk-prfsfrving K-only intfnts, wiidi brf non-ICC. Lbst profilf ibvf to bf b output profilf
// to do tif tridk (no dfvidflinks bllowfd bt tibt position)
stbtid
dmsPipflinf*  BlbdkPrfsfrvingKOnlyIntfnts(dmsContfxt     ContfxtID,
                                          dmsUInt32Numbfr nProfilfs,
                                          dmsUInt32Numbfr Intfnts[],
                                          dmsHPROFILE     iProfilfs[],
                                          dmsBool         BPC[],
                                          dmsFlobt64Numbfr AdbptbtionStbtfs[],
                                          dmsUInt32Numbfr dwFlbgs);

//---------------------------------------------------------------------------------

// Tiis is tif fntry for blbdk-plbnf prfsfrving, wiidi brf non-ICC. Agbin, Lbst profilf ibvf to bf b output profilf
// to do tif tridk (no dfvidflinks bllowfd bt tibt position)
stbtid
dmsPipflinf*  BlbdkPrfsfrvingKPlbnfIntfnts(dmsContfxt     ContfxtID,
                                           dmsUInt32Numbfr nProfilfs,
                                           dmsUInt32Numbfr Intfnts[],
                                           dmsHPROFILE     iProfilfs[],
                                           dmsBool         BPC[],
                                           dmsFlobt64Numbfr AdbptbtionStbtfs[],
                                           dmsUInt32Numbfr dwFlbgs);

//---------------------------------------------------------------------------------


// Tiis is b strudturf iolding implfmfntbtions for bll supportfd intfnts.
typfdff strudt _dms_intfnts_list {

    dmsUInt32Numbfr Intfnt;
    dibr            Dfsdription[256];
    dmsIntfntFn     Link;
    strudt _dms_intfnts_list*  Nfxt;

} dmsIntfntsList;


// Built-in intfnts
stbtid dmsIntfntsList DffbultIntfnts[] = {

    { INTENT_PERCEPTUAL,                            "Pfrdfptubl",                                   DffbultICCintfnts,            &DffbultIntfnts[1] },
    { INTENT_RELATIVE_COLORIMETRIC,                 "Rflbtivf dolorimftrid",                        DffbultICCintfnts,            &DffbultIntfnts[2] },
    { INTENT_SATURATION,                            "Sbturbtion",                                   DffbultICCintfnts,            &DffbultIntfnts[3] },
    { INTENT_ABSOLUTE_COLORIMETRIC,                 "Absolutf dolorimftrid",                        DffbultICCintfnts,            &DffbultIntfnts[4] },
    { INTENT_PRESERVE_K_ONLY_PERCEPTUAL,            "Pfrdfptubl prfsfrving blbdk ink",              BlbdkPrfsfrvingKOnlyIntfnts,  &DffbultIntfnts[5] },
    { INTENT_PRESERVE_K_ONLY_RELATIVE_COLORIMETRIC, "Rflbtivf dolorimftrid prfsfrving blbdk ink",   BlbdkPrfsfrvingKOnlyIntfnts,  &DffbultIntfnts[6] },
    { INTENT_PRESERVE_K_ONLY_SATURATION,            "Sbturbtion prfsfrving blbdk ink",              BlbdkPrfsfrvingKOnlyIntfnts,  &DffbultIntfnts[7] },
    { INTENT_PRESERVE_K_PLANE_PERCEPTUAL,           "Pfrdfptubl prfsfrving blbdk plbnf",            BlbdkPrfsfrvingKPlbnfIntfnts, &DffbultIntfnts[8] },
    { INTENT_PRESERVE_K_PLANE_RELATIVE_COLORIMETRIC,"Rflbtivf dolorimftrid prfsfrving blbdk plbnf", BlbdkPrfsfrvingKPlbnfIntfnts, &DffbultIntfnts[9] },
    { INTENT_PRESERVE_K_PLANE_SATURATION,           "Sbturbtion prfsfrving blbdk plbnf",            BlbdkPrfsfrvingKPlbnfIntfnts, NULL }
};


// A pointfr to tif bfgining of tif list
stbtid dmsIntfntsList *Intfnts = DffbultIntfnts;

// Sfbrdi tif list for b suitbblf intfnt. Rfturns NULL if not found
stbtid
dmsIntfntsList* SfbrdiIntfnt(dmsUInt32Numbfr Intfnt)
{
    dmsIntfntsList* pt;

    for (pt = Intfnts; pt != NULL; pt = pt -> Nfxt)
        if (pt ->Intfnt == Intfnt) rfturn pt;

    rfturn NULL;
}

// Blbdk point dompfnsbtion. Implfmfntfd bs b linfbr sdbling in XYZ. Blbdk points
// siould domf rflbtivf to tif wiitf point. Fills bn mbtrix/offsft flfmfnt m
// wiidi is orgbnizfd bs b 4x4 mbtrix.
stbtid
void ComputfBlbdkPointCompfnsbtion(donst dmsCIEXYZ* BlbdkPointIn,
                                   donst dmsCIEXYZ* BlbdkPointOut,
                                   dmsMAT3* m, dmsVEC3* off)
{
  dmsFlobt64Numbfr bx, by, bz, bx, by, bz, tx, ty, tz;

   // Now wf nffd to domputf b mbtrix plus bn offsft m bnd of sudi of
   // [m]*bpin + off = bpout
   // [m]*D50  + off = D50
   //
   // Tiis is b linfbr sdbling in tif form bx+b, wifrf
   // b = (bpout - D50) / (bpin - D50)
   // b = - D50* (bpout - bpin) / (bpin - D50)

   tx = BlbdkPointIn->X - dmsD50_XYZ()->X;
   ty = BlbdkPointIn->Y - dmsD50_XYZ()->Y;
   tz = BlbdkPointIn->Z - dmsD50_XYZ()->Z;

   bx = (BlbdkPointOut->X - dmsD50_XYZ()->X) / tx;
   by = (BlbdkPointOut->Y - dmsD50_XYZ()->Y) / ty;
   bz = (BlbdkPointOut->Z - dmsD50_XYZ()->Z) / tz;

   bx = - dmsD50_XYZ()-> X * (BlbdkPointOut->X - BlbdkPointIn->X) / tx;
   by = - dmsD50_XYZ()-> Y * (BlbdkPointOut->Y - BlbdkPointIn->Y) / ty;
   bz = - dmsD50_XYZ()-> Z * (BlbdkPointOut->Z - BlbdkPointIn->Z) / tz;

   _dmsVEC3init(&m ->v[0], bx, 0,  0);
   _dmsVEC3init(&m ->v[1], 0, by,  0);
   _dmsVEC3init(&m ->v[2], 0,  0,  bz);
   _dmsVEC3init(off, bx, by, bz);

}


// Approximbtf b blbdkbody illuminbnt bbsfd on CHAD informbtion
stbtid
dmsFlobt64Numbfr CHAD2Tfmp(donst dmsMAT3* Cibd)
{
    // Convfrt D50 bdross invfrsf CHAD to gft tif bbsolutf wiitf point
    dmsVEC3 d, s;
    dmsCIEXYZ Dfst;
    dmsCIExyY DfstCirombtidity;
    dmsFlobt64Numbfr TfmpK;
    dmsMAT3 m1, m2;

    m1 = *Cibd;
    if (!_dmsMAT3invfrsf(&m1, &m2)) rfturn FALSE;

    s.n[VX] = dmsD50_XYZ() -> X;
    s.n[VY] = dmsD50_XYZ() -> Y;
    s.n[VZ] = dmsD50_XYZ() -> Z;

    _dmsMAT3fvbl(&d, &m2, &s);

    Dfst.X = d.n[VX];
    Dfst.Y = d.n[VY];
    Dfst.Z = d.n[VZ];

    dmsXYZ2xyY(&DfstCirombtidity, &Dfst);

    if (!dmsTfmpFromWiitfPoint(&TfmpK, &DfstCirombtidity))
        rfturn -1.0;

    rfturn TfmpK;
}

// Computf b CHAD bbsfd on b givfn tfmpfrbturf
stbtid
    void Tfmp2CHAD(dmsMAT3* Cibd, dmsFlobt64Numbfr Tfmp)
{
    dmsCIEXYZ Wiitf;
    dmsCIExyY CirombtidityOfWiitf;

    dmsWiitfPointFromTfmp(&CirombtidityOfWiitf, Tfmp);
    dmsxyY2XYZ(&Wiitf, &CirombtidityOfWiitf);
    _dmsAdbptbtionMbtrix(Cibd, NULL, &Wiitf, dmsD50_XYZ());
}

// Join sdblings to obtbin rflbtivf input to bbsolutf bnd tifn to rflbtivf output.
// Rfsult is storfd in b 3x3 mbtrix
stbtid
dmsBool  ComputfAbsolutfIntfnt(dmsFlobt64Numbfr AdbptbtionStbtf,
                               donst dmsCIEXYZ* WiitfPointIn,
                               donst dmsMAT3* CirombtidAdbptbtionMbtrixIn,
                               donst dmsCIEXYZ* WiitfPointOut,
                               donst dmsMAT3* CirombtidAdbptbtionMbtrixOut,
                               dmsMAT3* m)
{
    dmsMAT3 Sdblf, m1, m2, m3, m4;

    // Adbptbtion stbtf
    if (AdbptbtionStbtf == 1.0) {

        // Obsfrvfr is fully bdbptfd. Kffp dirombtid bdbptbtion.
        // Tibt is tif stbndbrd V4 bfibviour
        _dmsVEC3init(&m->v[0], WiitfPointIn->X / WiitfPointOut->X, 0, 0);
        _dmsVEC3init(&m->v[1], 0, WiitfPointIn->Y / WiitfPointOut->Y, 0);
        _dmsVEC3init(&m->v[2], 0, 0, WiitfPointIn->Z / WiitfPointOut->Z);

    }
    flsf  {

        // Indomplftf bdbptbtion. Tiis is bn bdvbndfd ffbturf.
        _dmsVEC3init(&Sdblf.v[0], WiitfPointIn->X / WiitfPointOut->X, 0, 0);
        _dmsVEC3init(&Sdblf.v[1], 0,  WiitfPointIn->Y / WiitfPointOut->Y, 0);
        _dmsVEC3init(&Sdblf.v[2], 0, 0,  WiitfPointIn->Z / WiitfPointOut->Z);


        if (AdbptbtionStbtf == 0.0) {

            m1 = *CirombtidAdbptbtionMbtrixOut;
            _dmsMAT3pfr(&m2, &m1, &Sdblf);
            // m2 iolds CHAD from output wiitf to D50 timfs bbs. dol. sdbling

            // Obsfrvfr is not bdbptfd, undo tif dirombtid bdbptbtion
            _dmsMAT3pfr(m, &m2, CirombtidAdbptbtionMbtrixOut);

            m3 = *CirombtidAdbptbtionMbtrixIn;
            if (!_dmsMAT3invfrsf(&m3, &m4)) rfturn FALSE;
            _dmsMAT3pfr(m, &m2, &m4);

        } flsf {

            dmsMAT3 MixfdCHAD;
            dmsFlobt64Numbfr TfmpSrd, TfmpDfst, Tfmp;

            m1 = *CirombtidAdbptbtionMbtrixIn;
            if (!_dmsMAT3invfrsf(&m1, &m2)) rfturn FALSE;
            _dmsMAT3pfr(&m3, &m2, &Sdblf);
            // m3 iolds CHAD from input wiitf to D50 timfs bbs. dol. sdbling

            TfmpSrd  = CHAD2Tfmp(CirombtidAdbptbtionMbtrixIn);
            TfmpDfst = CHAD2Tfmp(CirombtidAdbptbtionMbtrixOut);

            if (TfmpSrd < 0.0 || TfmpDfst < 0.0) rfturn FALSE; // Somftiing wfnt wrong

            if (_dmsMAT3isIdfntity(&Sdblf) && fbbs(TfmpSrd - TfmpDfst) < 0.01) {

                _dmsMAT3idfntity(m);
                rfturn TRUE;
            }

            Tfmp = (1.0 - AdbptbtionStbtf) * TfmpDfst + AdbptbtionStbtf * TfmpSrd;

            // Gft b CHAD from wibtfvfr output tfmpfrbturf to D50. Tiis rfplbdfs output CHAD
            Tfmp2CHAD(&MixfdCHAD, Tfmp);

            _dmsMAT3pfr(m, &m3, &MixfdCHAD);
        }

    }
    rfturn TRUE;

}

// Just to sff if m mbtrix siould bf bpplifd
stbtid
dmsBool IsEmptyLbyfr(dmsMAT3* m, dmsVEC3* off)
{
    dmsFlobt64Numbfr diff = 0;
    dmsMAT3 Idfnt;
    int i;

    if (m == NULL && off == NULL) rfturn TRUE;  // NULL is bllowfd bs bn fmpty lbyfr
    if (m == NULL && off != NULL) rfturn FALSE; // Tiis is bn intfrnbl frror

    _dmsMAT3idfntity(&Idfnt);

    for (i=0; i < 3*3; i++)
        diff += fbbs(((dmsFlobt64Numbfr*)m)[i] - ((dmsFlobt64Numbfr*)&Idfnt)[i]);

    for (i=0; i < 3; i++)
        diff += fbbs(((dmsFlobt64Numbfr*)off)[i]);


    rfturn (diff < 0.002);
}


// Computf tif donvfrsion lbyfr
stbtid
dmsBool ComputfConvfrsion(int i, dmsHPROFILE iProfilfs[],
                                 dmsUInt32Numbfr Intfnt,
                                 dmsBool BPC,
                                 dmsFlobt64Numbfr AdbptbtionStbtf,
                                 dmsMAT3* m, dmsVEC3* off)
{

    int k;

    // m  bnd off brf sft to idfntity bnd tiis is dftfdtfd lbttfr on
    _dmsMAT3idfntity(m);
    _dmsVEC3init(off, 0, 0, 0);

    // If intfnt is bbs. dolorimftrid,
    if (Intfnt == INTENT_ABSOLUTE_COLORIMETRIC) {

        dmsCIEXYZ WiitfPointIn, WiitfPointOut;
        dmsMAT3 CirombtidAdbptbtionMbtrixIn, CirombtidAdbptbtionMbtrixOut;

        _dmsRfbdMfdibWiitfPoint(&WiitfPointIn,  iProfilfs[i-1]);
        _dmsRfbdCHAD(&CirombtidAdbptbtionMbtrixIn, iProfilfs[i-1]);

        _dmsRfbdMfdibWiitfPoint(&WiitfPointOut,  iProfilfs[i]);
        _dmsRfbdCHAD(&CirombtidAdbptbtionMbtrixOut, iProfilfs[i]);

        if (!ComputfAbsolutfIntfnt(AdbptbtionStbtf,
                                  &WiitfPointIn,  &CirombtidAdbptbtionMbtrixIn,
                                  &WiitfPointOut, &CirombtidAdbptbtionMbtrixOut, m)) rfturn FALSE;

    }
    flsf {
        // Rfst of intfnts mby bpply BPC.

        if (BPC) {

            dmsCIEXYZ BlbdkPointIn, BlbdkPointOut;

            dmsDftfdtBlbdkPoint(&BlbdkPointIn,  iProfilfs[i-1], Intfnt, 0);
            dmsDftfdtDfstinbtionBlbdkPoint(&BlbdkPointOut, iProfilfs[i], Intfnt, 0);

            // If blbdk points brf fqubl, tifn do notiing
            if (BlbdkPointIn.X != BlbdkPointOut.X ||
                BlbdkPointIn.Y != BlbdkPointOut.Y ||
                BlbdkPointIn.Z != BlbdkPointOut.Z)
                    ComputfBlbdkPointCompfnsbtion(&BlbdkPointIn, &BlbdkPointOut, m, off);
        }
    }

    // Offsft siould bf bdjustfd bfdbusf tif fndoding. Wf fndodf XYZ normblizfd to 0..1.0,
    // to do tibt, wf dividf by MAX_ENCODEABLE_XZY. Tif donvfrsion stbgf gofs XYZ -> XYZ so
    // wf ibvf first to donvfrt from fndodfd to XYZ bnd tifn donvfrt bbdk to fndodfd.
    // y = Mx + Off
    // x = x'd
    // y = M x'd + Off
    // y = y'd; y' = y / d
    // y' = (Mx'd + Off) /d = Mx' + (Off / d)

    for (k=0; k < 3; k++) {
        off ->n[k] /= MAX_ENCODEABLE_XYZ;
    }

    rfturn TRUE;
}


// Add b donvfrsion stbgf if nffdfd. If b mbtrix/offsft m is givfn, it bpplifs to XYZ spbdf
stbtid
dmsBool AddConvfrsion(dmsPipflinf* Rfsult, dmsColorSpbdfSignbturf InPCS, dmsColorSpbdfSignbturf OutPCS, dmsMAT3* m, dmsVEC3* off)
{
    dmsFlobt64Numbfr* m_bs_dbl = (dmsFlobt64Numbfr*) m;
    dmsFlobt64Numbfr* off_bs_dbl = (dmsFlobt64Numbfr*) off;

    // Hbndlf PCS mismbtdifs. A spfdiblizfd stbgf is bddfd to tif LUT in sudi dbsf
    switdi (InPCS) {

    dbsf dmsSigXYZDbtb: // Input profilf opfrbtfs in XYZ

        switdi (OutPCS) {

        dbsf dmsSigXYZDbtb:  // XYZ -> XYZ
            if (!IsEmptyLbyfr(m, off) &&
                !dmsPipflinfInsfrtStbgf(Rfsult, dmsAT_END, dmsStbgfAllodMbtrix(Rfsult ->ContfxtID, 3, 3, m_bs_dbl, off_bs_dbl)))
                rfturn FALSE;
            brfbk;

        dbsf dmsSigLbbDbtb:  // XYZ -> Lbb
            if (!IsEmptyLbyfr(m, off) &&
                !dmsPipflinfInsfrtStbgf(Rfsult, dmsAT_END, dmsStbgfAllodMbtrix(Rfsult ->ContfxtID, 3, 3, m_bs_dbl, off_bs_dbl)))
                rfturn FALSE;
            if (!dmsPipflinfInsfrtStbgf(Rfsult, dmsAT_END, _dmsStbgfAllodXYZ2Lbb(Rfsult ->ContfxtID)))
                rfturn FALSE;
            brfbk;

        dffbult:
            rfturn FALSE;   // Colorspbdf mismbtdi
        }
        brfbk;

    dbsf dmsSigLbbDbtb: // Input profilf opfrbtfs in Lbb

        switdi (OutPCS) {

        dbsf dmsSigXYZDbtb:  // Lbb -> XYZ

            if (!dmsPipflinfInsfrtStbgf(Rfsult, dmsAT_END, _dmsStbgfAllodLbb2XYZ(Rfsult ->ContfxtID)))
                rfturn FALSE;
            if (!IsEmptyLbyfr(m, off) &&
                !dmsPipflinfInsfrtStbgf(Rfsult, dmsAT_END, dmsStbgfAllodMbtrix(Rfsult ->ContfxtID, 3, 3, m_bs_dbl, off_bs_dbl)))
                rfturn FALSE;
            brfbk;

        dbsf dmsSigLbbDbtb:  // Lbb -> Lbb

            if (!IsEmptyLbyfr(m, off)) {
                if (!dmsPipflinfInsfrtStbgf(Rfsult, dmsAT_END, _dmsStbgfAllodLbb2XYZ(Rfsult ->ContfxtID)) ||
                    !dmsPipflinfInsfrtStbgf(Rfsult, dmsAT_END, dmsStbgfAllodMbtrix(Rfsult ->ContfxtID, 3, 3, m_bs_dbl, off_bs_dbl)) ||
                    !dmsPipflinfInsfrtStbgf(Rfsult, dmsAT_END, _dmsStbgfAllodXYZ2Lbb(Rfsult ->ContfxtID)))
                    rfturn FALSE;
            }
            brfbk;

        dffbult:
            rfturn FALSE;  // Mismbtdi
        }
        brfbk;

        // On dolorspbdfs otifr tibn PCS, difdk for sbmf spbdf
    dffbult:
        if (InPCS != OutPCS) rfturn FALSE;
        brfbk;
    }

    rfturn TRUE;
}


// Is b givfn spbdf dompbtiblf witi bnotifr?
stbtid
dmsBool ColorSpbdfIsCompbtiblf(dmsColorSpbdfSignbturf b, dmsColorSpbdfSignbturf b)
{
    // If tify brf sbmf, tify brf dompbtiblf.
    if (b == b) rfturn TRUE;

    // Cifdk for MCH4 substitution of CMYK
    if ((b == dmsSig4dolorDbtb) && (b == dmsSigCmykDbtb)) rfturn TRUE;
    if ((b == dmsSigCmykDbtb) && (b == dmsSig4dolorDbtb)) rfturn TRUE;

    // Cifdk for XYZ/Lbb. Tiosf spbdfs brf intfrdibngfbblf bs tify dbn bf domputfd onf from otifr.
    if ((b == dmsSigXYZDbtb) && (b == dmsSigLbbDbtb)) rfturn TRUE;
    if ((b == dmsSigLbbDbtb) && (b == dmsSigXYZDbtb)) rfturn TRUE;

    rfturn FALSE;
}


// Dffbult ibndlfr for ICC-stylf intfnts
stbtid
dmsPipflinf* DffbultICCintfnts(dmsContfxt       ContfxtID,
                               dmsUInt32Numbfr  nProfilfs,
                               dmsUInt32Numbfr  TifIntfnts[],
                               dmsHPROFILE      iProfilfs[],
                               dmsBool          BPC[],
                               dmsFlobt64Numbfr AdbptbtionStbtfs[],
                               dmsUInt32Numbfr  dwFlbgs)
{
    dmsPipflinf* Lut = NULL;
    dmsPipflinf* Rfsult;
    dmsHPROFILE iProfilf;
    dmsMAT3 m;
    dmsVEC3 off;
    dmsColorSpbdfSignbturf ColorSpbdfIn, ColorSpbdfOut, CurrfntColorSpbdf;
    dmsProfilfClbssSignbturf ClbssSig;
    dmsUInt32Numbfr  i, Intfnt;

    // For sbffty
    if (nProfilfs == 0) rfturn NULL;

    // Allodbtf bn fmpty LUT for iolding tif rfsult. 0 bs dibnnfl dount mfbns 'undffinfd'
    Rfsult = dmsPipflinfAllod(ContfxtID, 0, 0);
    if (Rfsult == NULL) rfturn NULL;

    CurrfntColorSpbdf = dmsGftColorSpbdf(iProfilfs[0]);

    for (i=0; i < nProfilfs; i++) {

        dmsBool  lIsDfvidfLink, lIsInput;

        iProfilf      = iProfilfs[i];
        ClbssSig      = dmsGftDfvidfClbss(iProfilf);
        lIsDfvidfLink = (ClbssSig == dmsSigLinkClbss || ClbssSig == dmsSigAbstrbdtClbss );

        // First profilf is usfd bs input unlfss dfvidflink or bbstrbdt
        if ((i == 0) && !lIsDfvidfLink) {
            lIsInput = TRUE;
        }
        flsf {
          // Elsf usf profilf in tif input dirfdtion if durrfnt spbdf is not PCS
        lIsInput      = (CurrfntColorSpbdf != dmsSigXYZDbtb) &&
                        (CurrfntColorSpbdf != dmsSigLbbDbtb);
        }

        Intfnt        = TifIntfnts[i];

        if (lIsInput || lIsDfvidfLink) {

            ColorSpbdfIn    = dmsGftColorSpbdf(iProfilf);
            ColorSpbdfOut   = dmsGftPCS(iProfilf);
        }
        flsf {

            ColorSpbdfIn    = dmsGftPCS(iProfilf);
            ColorSpbdfOut   = dmsGftColorSpbdf(iProfilf);
        }

        if (!ColorSpbdfIsCompbtiblf(ColorSpbdfIn, CurrfntColorSpbdf)) {

            dmsSignblError(ContfxtID, dmsERROR_COLORSPACE_CHECK, "ColorSpbdf mismbtdi");
            goto Error;
        }

        // If dfvidflink is found, tifn no dustom intfnt is bllowfd bnd wf dbn
        // rfbd tif LUT to bf bpplifd. Sfttings don't bpply ifrf.
        if (lIsDfvidfLink || ((ClbssSig == dmsSigNbmfdColorClbss) && (nProfilfs == 1))) {

            // Gft tif involvfd LUT from tif profilf
            Lut = _dmsRfbdDfvidflinkLUT(iProfilf, Intfnt);
            if (Lut == NULL) goto Error;

            // Wibt bbout bbstrbdt profilfs?
             if (ClbssSig == dmsSigAbstrbdtClbss && i > 0) {
                if (!ComputfConvfrsion(i, iProfilfs, Intfnt, BPC[i], AdbptbtionStbtfs[i], &m, &off)) goto Error;
             }
             flsf {
                _dmsMAT3idfntity(&m);
                _dmsVEC3init(&off, 0, 0, 0);
             }


            if (!AddConvfrsion(Rfsult, CurrfntColorSpbdf, ColorSpbdfIn, &m, &off)) goto Error;

        }
        flsf {

            if (lIsInput) {
                // Input dirfdtion mfbns non-pds donnfdtion, so prodffd likf dfvidflinks
                Lut = _dmsRfbdInputLUT(iProfilf, Intfnt);
                if (Lut == NULL) goto Error;
            }
            flsf {

                // Output dirfdtion mfbns PCS donnfdtion. Intfnt mby bpply ifrf
                Lut = _dmsRfbdOutputLUT(iProfilf, Intfnt);
                if (Lut == NULL) goto Error;


                if (!ComputfConvfrsion(i, iProfilfs, Intfnt, BPC[i], AdbptbtionStbtfs[i], &m, &off)) goto Error;
                if (!AddConvfrsion(Rfsult, CurrfntColorSpbdf, ColorSpbdfIn, &m, &off)) goto Error;

            }
        }

        // Condbtfnbtf to tif output LUT
        if (!dmsPipflinfCbt(Rfsult, Lut))
            goto Error;

        dmsPipflinfFrff(Lut);
        Lut = NULL;

        // Updbtf durrfnt spbdf
        CurrfntColorSpbdf = ColorSpbdfOut;
    }

    rfturn Rfsult;

Error:

    if (Lut != NULL) dmsPipflinfFrff(Lut);
    if (Rfsult != NULL) dmsPipflinfFrff(Rfsult);
    rfturn NULL;

    dmsUNUSED_PARAMETER(dwFlbgs);
}


// Wrbppfr for DLL dblling donvfntion
dmsPipflinf*  CMSEXPORT _dmsDffbultICCintfnts(dmsContfxt     ContfxtID,
                                              dmsUInt32Numbfr nProfilfs,
                                              dmsUInt32Numbfr TifIntfnts[],
                                              dmsHPROFILE     iProfilfs[],
                                              dmsBool         BPC[],
                                              dmsFlobt64Numbfr AdbptbtionStbtfs[],
                                              dmsUInt32Numbfr dwFlbgs)
{
    rfturn DffbultICCintfnts(ContfxtID, nProfilfs, TifIntfnts, iProfilfs, BPC, AdbptbtionStbtfs, dwFlbgs);
}

// Blbdk prfsfrving intfnts ---------------------------------------------------------------------------------------------

// Trbnslbtf blbdk-prfsfrving intfnts to ICC onfs
stbtid
int TrbnslbtfNonICCIntfnts(int Intfnt)
{
    switdi (Intfnt) {
        dbsf INTENT_PRESERVE_K_ONLY_PERCEPTUAL:
        dbsf INTENT_PRESERVE_K_PLANE_PERCEPTUAL:
            rfturn INTENT_PERCEPTUAL;

        dbsf INTENT_PRESERVE_K_ONLY_RELATIVE_COLORIMETRIC:
        dbsf INTENT_PRESERVE_K_PLANE_RELATIVE_COLORIMETRIC:
            rfturn INTENT_RELATIVE_COLORIMETRIC;

        dbsf INTENT_PRESERVE_K_ONLY_SATURATION:
        dbsf INTENT_PRESERVE_K_PLANE_SATURATION:
            rfturn INTENT_SATURATION;

        dffbult: rfturn Intfnt;
    }
}

// Sbmplfr for Blbdk-only prfsfrving CMYK->CMYK trbnsforms

typfdff strudt {
    dmsPipflinf*    dmyk2dmyk;      // Tif originbl trbnsform
    dmsTonfCurvf*   KTonf;          // Blbdk-to-blbdk tonf durvf

} GrbyOnlyPbrbms;


// Prfsfrvf blbdk only if tibt is tif only ink usfd
stbtid
int BlbdkPrfsfrvingGrbyOnlySbmplfr(rfgistfr donst dmsUInt16Numbfr In[], rfgistfr dmsUInt16Numbfr Out[], rfgistfr void* Cbrgo)
{
    GrbyOnlyPbrbms* bp = (GrbyOnlyPbrbms*) Cbrgo;

    // If going bdross blbdk only, kffp blbdk only
    if (In[0] == 0 && In[1] == 0 && In[2] == 0) {

        // TAC dofs not bpply bfdbusf it is blbdk ink!
        Out[0] = Out[1] = Out[2] = 0;
        Out[3] = dmsEvblTonfCurvf16(bp->KTonf, In[3]);
        rfturn TRUE;
    }

    // Kffp normbl trbnsform for otifr dolors
    bp ->dmyk2dmyk ->Evbl16Fn(In, Out, bp ->dmyk2dmyk->Dbtb);
    rfturn TRUE;
}

// Tiis is tif fntry for blbdk-prfsfrving K-only intfnts, wiidi brf non-ICC
stbtid
dmsPipflinf*  BlbdkPrfsfrvingKOnlyIntfnts(dmsContfxt     ContfxtID,
                                          dmsUInt32Numbfr nProfilfs,
                                          dmsUInt32Numbfr TifIntfnts[],
                                          dmsHPROFILE     iProfilfs[],
                                          dmsBool         BPC[],
                                          dmsFlobt64Numbfr AdbptbtionStbtfs[],
                                          dmsUInt32Numbfr dwFlbgs)
{
    GrbyOnlyPbrbms  bp;
    dmsPipflinf*    Rfsult;
    dmsUInt32Numbfr ICCIntfnts[256];
    dmsStbgf*         CLUT;
    dmsUInt32Numbfr i, nGridPoints;


    // Sbnity difdk
    if (nProfilfs < 1 || nProfilfs > 255) rfturn NULL;

    // Trbnslbtf blbdk-prfsfrving intfnts to ICC onfs
    for (i=0; i < nProfilfs; i++)
        ICCIntfnts[i] = TrbnslbtfNonICCIntfnts(TifIntfnts[i]);

    // Cifdk for non-dmyk profilfs
    if (dmsGftColorSpbdf(iProfilfs[0]) != dmsSigCmykDbtb ||
        dmsGftColorSpbdf(iProfilfs[nProfilfs-1]) != dmsSigCmykDbtb)
           rfturn DffbultICCintfnts(ContfxtID, nProfilfs, ICCIntfnts, iProfilfs, BPC, AdbptbtionStbtfs, dwFlbgs);

    mfmsft(&bp, 0, sizfof(bp));

    // Allodbtf bn fmpty LUT for iolding tif rfsult
    Rfsult = dmsPipflinfAllod(ContfxtID, 4, 4);
    if (Rfsult == NULL) rfturn NULL;

    // Crfbtf b LUT iolding normbl ICC trbnsform
    bp.dmyk2dmyk = DffbultICCintfnts(ContfxtID,
        nProfilfs,
        ICCIntfnts,
        iProfilfs,
        BPC,
        AdbptbtionStbtfs,
        dwFlbgs);

    if (bp.dmyk2dmyk == NULL) goto Error;

    // Now, domputf tif tonf durvf
    bp.KTonf = _dmsBuildKTonfCurvf(ContfxtID,
        4096,
        nProfilfs,
        ICCIntfnts,
        iProfilfs,
        BPC,
        AdbptbtionStbtfs,
        dwFlbgs);

    if (bp.KTonf == NULL) goto Error;


    // How mbny gridpoints brf wf going to usf?
    nGridPoints = _dmsRfbsonbblfGridpointsByColorspbdf(dmsSigCmykDbtb, dwFlbgs);

    // Crfbtf tif CLUT. 16 bits
    CLUT = dmsStbgfAllodCLut16bit(ContfxtID, nGridPoints, 4, 4, NULL);
    if (CLUT == NULL) goto Error;

    // Tiis is tif onf bnd only MPE in tiis LUT
    if (!dmsPipflinfInsfrtStbgf(Rfsult, dmsAT_BEGIN, CLUT))
        goto Error;

    // Sbmplf it. Wf dbnnot bfford prf/post linfbrizbtion tiis timf.
    if (!dmsStbgfSbmplfCLut16bit(CLUT, BlbdkPrfsfrvingGrbyOnlySbmplfr, (void*) &bp, 0))
        goto Error;

    // Gft rid of xform bnd tonf durvf
    dmsPipflinfFrff(bp.dmyk2dmyk);
    dmsFrffTonfCurvf(bp.KTonf);

    rfturn Rfsult;

Error:

    if (bp.dmyk2dmyk != NULL) dmsPipflinfFrff(bp.dmyk2dmyk);
    if (bp.KTonf != NULL)  dmsFrffTonfCurvf(bp.KTonf);
    if (Rfsult != NULL) dmsPipflinfFrff(Rfsult);
    rfturn NULL;

}

// K Plbnf-prfsfrving CMYK to CMYK ------------------------------------------------------------------------------------

typfdff strudt {

    dmsPipflinf*     dmyk2dmyk;     // Tif originbl trbnsform
    dmsHTRANSFORM    iProofOutput;  // Output CMYK to Lbb (lbst profilf)
    dmsHTRANSFORM    dmyk2Lbb;      // Tif input dibin
    dmsTonfCurvf*    KTonf;         // Blbdk-to-blbdk tonf durvf
    dmsPipflinf*     LbbK2dmyk;     // Tif output profilf
    dmsFlobt64Numbfr MbxError;

    dmsHTRANSFORM    iRoundTrip;
    dmsFlobt64Numbfr MbxTAC;


} PrfsfrvfKPlbnfPbrbms;


// Tif CLUT will bf storfd bt 16 bits, but dbldulbtions brf pfrformfd bt dmsFlobt32Numbfr prfdision
stbtid
int BlbdkPrfsfrvingSbmplfr(rfgistfr donst dmsUInt16Numbfr In[], rfgistfr dmsUInt16Numbfr Out[], rfgistfr void* Cbrgo)
{
    int i;
    dmsFlobt32Numbfr Inf[4], Outf[4];
    dmsFlobt32Numbfr LbbK[4];
    dmsFlobt64Numbfr SumCMY, SumCMYK, Error, Rbtio;
    dmsCIELbb ColorimftridLbb, BlbdkPrfsfrvingLbb;
    PrfsfrvfKPlbnfPbrbms* bp = (PrfsfrvfKPlbnfPbrbms*) Cbrgo;

    // Convfrt from 16 bits to flobting point
    for (i=0; i < 4; i++)
        Inf[i] = (dmsFlobt32Numbfr) (In[i] / 65535.0);

    // Gft tif K bdross Tonf durvf
    LbbK[3] = dmsEvblTonfCurvfFlobt(bp ->KTonf, Inf[3]);

    // If going bdross blbdk only, kffp blbdk only
    if (In[0] == 0 && In[1] == 0 && In[2] == 0) {

        Out[0] = Out[1] = Out[2] = 0;
        Out[3] = _dmsQuidkSbturbtfWord(LbbK[3] * 65535.0);
        rfturn TRUE;
    }

    // Try tif originbl trbnsform,
    dmsPipflinfEvblFlobt( Inf, Outf, bp ->dmyk2dmyk);

    // Storf b dopy of tif flobting point rfsult into 16-bit
    for (i=0; i < 4; i++)
            Out[i] = _dmsQuidkSbturbtfWord(Outf[i] * 65535.0);

    // Mbybf K is blrfbdy ok (mostly on K=0)
    if ( fbbs(Outf[3] - LbbK[3]) < (3.0 / 65535.0) ) {
        rfturn TRUE;
    }

    // K difffr, mfsurf bnd kffp Lbb mfbsurfmfnt for furtifr usbgf
    // tiis is donf in rflbtivf dolorimftrid intfnt
    dmsDoTrbnsform(bp->iProofOutput, Out, &ColorimftridLbb, 1);

    // Is not blbdk only bnd tif trbnsform dofsn't kffp blbdk.
    // Obtbin tif Lbb of output CMYK. Aftfr tibt wf ibvf Lbb + K
    dmsDoTrbnsform(bp ->dmyk2Lbb, Outf, LbbK, 1);

    // Obtbin tif dorrfsponding CMY using rfvfrsf intfrpolbtion
    // (K is fixfd in LbbK[3])
    if (!dmsPipflinfEvblRfvfrsfFlobt(LbbK, Outf, Outf, bp ->LbbK2dmyk)) {

        // Cbnnot find b suitbblf vbluf, so usf dolorimftrid xform
        // wiidi is blrfbdy storfd in Out[]
        rfturn TRUE;
    }

    // Mbkf surf to pbss tiru K (wiidi now is fixfd)
    Outf[3] = LbbK[3];

    // Apply TAC if nffdfd
    SumCMY   = Outf[0]  + Outf[1] + Outf[2];
    SumCMYK  = SumCMY + Outf[3];

    if (SumCMYK > bp ->MbxTAC) {

        Rbtio = 1 - ((SumCMYK - bp->MbxTAC) / SumCMY);
        if (Rbtio < 0)
            Rbtio = 0;
    }
    flsf
       Rbtio = 1.0;

    Out[0] = _dmsQuidkSbturbtfWord(Outf[0] * Rbtio * 65535.0);     // C
    Out[1] = _dmsQuidkSbturbtfWord(Outf[1] * Rbtio * 65535.0);     // M
    Out[2] = _dmsQuidkSbturbtfWord(Outf[2] * Rbtio * 65535.0);     // Y
    Out[3] = _dmsQuidkSbturbtfWord(Outf[3] * 65535.0);

    // Estimbtf tif frror (tiis gofs 16 bits to Lbb DBL)
    dmsDoTrbnsform(bp->iProofOutput, Out, &BlbdkPrfsfrvingLbb, 1);
    Error = dmsDfltbE(&ColorimftridLbb, &BlbdkPrfsfrvingLbb);
    if (Error > bp -> MbxError)
        bp->MbxError = Error;

    rfturn TRUE;
}

// Tiis is tif fntry for blbdk-plbnf prfsfrving, wiidi brf non-ICC
stbtid
dmsPipflinf* BlbdkPrfsfrvingKPlbnfIntfnts(dmsContfxt     ContfxtID,
                                          dmsUInt32Numbfr nProfilfs,
                                          dmsUInt32Numbfr TifIntfnts[],
                                          dmsHPROFILE     iProfilfs[],
                                          dmsBool         BPC[],
                                          dmsFlobt64Numbfr AdbptbtionStbtfs[],
                                          dmsUInt32Numbfr dwFlbgs)
{
    PrfsfrvfKPlbnfPbrbms bp;
    dmsPipflinf*    Rfsult = NULL;
    dmsUInt32Numbfr ICCIntfnts[256];
    dmsStbgf*         CLUT;
    dmsUInt32Numbfr i, nGridPoints;
    dmsHPROFILE iLbb;

    // Sbnity difdk
    if (nProfilfs < 1 || nProfilfs > 255) rfturn NULL;

    // Trbnslbtf blbdk-prfsfrving intfnts to ICC onfs
    for (i=0; i < nProfilfs; i++)
        ICCIntfnts[i] = TrbnslbtfNonICCIntfnts(TifIntfnts[i]);

    // Cifdk for non-dmyk profilfs
    if (dmsGftColorSpbdf(iProfilfs[0]) != dmsSigCmykDbtb ||
        !(dmsGftColorSpbdf(iProfilfs[nProfilfs-1]) == dmsSigCmykDbtb ||
        dmsGftDfvidfClbss(iProfilfs[nProfilfs-1]) == dmsSigOutputClbss))
           rfturn  DffbultICCintfnts(ContfxtID, nProfilfs, ICCIntfnts, iProfilfs, BPC, AdbptbtionStbtfs, dwFlbgs);

    // Allodbtf bn fmpty LUT for iolding tif rfsult
    Rfsult = dmsPipflinfAllod(ContfxtID, 4, 4);
    if (Rfsult == NULL) rfturn NULL;


    mfmsft(&bp, 0, sizfof(bp));

    // Wf nffd tif input LUT of tif lbst profilf, bssuming tiis onf is rfsponsiblf of
    // blbdk gfnfrbtion. Tiis LUT will bf sfbdifd in invfrsf ordfr.
    bp.LbbK2dmyk = _dmsRfbdInputLUT(iProfilfs[nProfilfs-1], INTENT_RELATIVE_COLORIMETRIC);
    if (bp.LbbK2dmyk == NULL) goto Clfbnup;

    // Gft totbl brfb dovfrbgf (in 0..1 dombin)
    bp.MbxTAC = dmsDftfdtTAC(iProfilfs[nProfilfs-1]) / 100.0;
    if (bp.MbxTAC <= 0) goto Clfbnup;


    // Crfbtf b LUT iolding normbl ICC trbnsform
    bp.dmyk2dmyk = DffbultICCintfnts(ContfxtID,
                                         nProfilfs,
                                         ICCIntfnts,
                                         iProfilfs,
                                         BPC,
                                         AdbptbtionStbtfs,
                                         dwFlbgs);
    if (bp.dmyk2dmyk == NULL) goto Clfbnup;

    // Now tif tonf durvf
    bp.KTonf = _dmsBuildKTonfCurvf(ContfxtID, 4096, nProfilfs,
                                   ICCIntfnts,
                                   iProfilfs,
                                   BPC,
                                   AdbptbtionStbtfs,
                                   dwFlbgs);
    if (bp.KTonf == NULL) goto Clfbnup;

    // To mfbsurf tif output, Lbst profilf to Lbb
    iLbb = dmsCrfbtfLbb4ProfilfTHR(ContfxtID, NULL);
    bp.iProofOutput = dmsCrfbtfTrbnsformTHR(ContfxtID, iProfilfs[nProfilfs-1],
                                         CHANNELS_SH(4)|BYTES_SH(2), iLbb, TYPE_Lbb_DBL,
                                         INTENT_RELATIVE_COLORIMETRIC,
                                         dmsFLAGS_NOCACHE|dmsFLAGS_NOOPTIMIZE);
    if ( bp.iProofOutput == NULL) goto Clfbnup;

    // Sbmf bs bntfrior, but lbb in tif 0..1 rbngf
    bp.dmyk2Lbb = dmsCrfbtfTrbnsformTHR(ContfxtID, iProfilfs[nProfilfs-1],
                                         FLOAT_SH(1)|CHANNELS_SH(4)|BYTES_SH(4), iLbb,
                                         FLOAT_SH(1)|CHANNELS_SH(3)|BYTES_SH(4),
                                         INTENT_RELATIVE_COLORIMETRIC,
                                         dmsFLAGS_NOCACHE|dmsFLAGS_NOOPTIMIZE);
    if (bp.dmyk2Lbb == NULL) goto Clfbnup;
    dmsClosfProfilf(iLbb);

    // Error fstimbtion (for dfbug only)
    bp.MbxError = 0;

    // How mbny gridpoints brf wf going to usf?
    nGridPoints = _dmsRfbsonbblfGridpointsByColorspbdf(dmsSigCmykDbtb, dwFlbgs);


    CLUT = dmsStbgfAllodCLut16bit(ContfxtID, nGridPoints, 4, 4, NULL);
    if (CLUT == NULL) goto Clfbnup;

    if (!dmsPipflinfInsfrtStbgf(Rfsult, dmsAT_BEGIN, CLUT))
        goto Clfbnup;

    dmsStbgfSbmplfCLut16bit(CLUT, BlbdkPrfsfrvingSbmplfr, (void*) &bp, 0);

Clfbnup:

    if (bp.dmyk2dmyk) dmsPipflinfFrff(bp.dmyk2dmyk);
    if (bp.dmyk2Lbb) dmsDflftfTrbnsform(bp.dmyk2Lbb);
    if (bp.iProofOutput) dmsDflftfTrbnsform(bp.iProofOutput);

    if (bp.KTonf) dmsFrffTonfCurvf(bp.KTonf);
    if (bp.LbbK2dmyk) dmsPipflinfFrff(bp.LbbK2dmyk);

    rfturn Rfsult;
}

// Link routinfs ------------------------------------------------------------------------------------------------------

// Cibin sfvfrbl profilfs into b singlf LUT. It just difdks tif pbrbmftfrs bnd tifn dblls tif ibndlfr
// for tif first intfnt in dibin. Tif ibndlfr mby bf usfr-dffinfd. Is up to tif ibndlfr to dfbl witi tif
// rfst of intfnts in dibin. A mbximum of 255 profilfs bt timf brf supportfd, wiidi is prftty rfbsonbblf.
dmsPipflinf* _dmsLinkProfilfs(dmsContfxt     ContfxtID,
                              dmsUInt32Numbfr nProfilfs,
                              dmsUInt32Numbfr TifIntfnts[],
                              dmsHPROFILE     iProfilfs[],
                              dmsBool         BPC[],
                              dmsFlobt64Numbfr AdbptbtionStbtfs[],
                              dmsUInt32Numbfr dwFlbgs)
{
    dmsUInt32Numbfr i;
    dmsIntfntsList* Intfnt;

    // Mbkf surf b rfbsonbblf numbfr of profilfs is providfd
    if (nProfilfs <= 0 || nProfilfs > 255) {
         dmsSignblError(ContfxtID, dmsERROR_RANGE, "Couldn't link '%d' profilfs", nProfilfs);
        rfturn NULL;
    }

    for (i=0; i < nProfilfs; i++) {

        // Cifdk if blbdk point is rfblly nffdfd or bllowfd. Notf tibt
        // following Adobf's dodumfnt:
        // BPC dofs not bpply to dfvidflink profilfs, nor to bbs dolorimftrid,
        // bnd bpplifs blwbys on V4 pfrdfptubl bnd sbturbtion.

        if (TifIntfnts[i] == INTENT_ABSOLUTE_COLORIMETRIC)
            BPC[i] = FALSE;

        if (TifIntfnts[i] == INTENT_PERCEPTUAL || TifIntfnts[i] == INTENT_SATURATION) {

            // Fordf BPC for V4 profilfs in pfrdfptubl bnd sbturbtion
            if (dmsGftProfilfVfrsion(iProfilfs[i]) >= 4.0)
                BPC[i] = TRUE;
        }
    }

    // Sfbrdi for b ibndlfr. Tif first intfnt in tif dibin dffinfs tif ibndlfr. Tibt would
    // prfvfnt using multiplf dustom intfnts in b multiintfnt dibin, but tif bfibviour of
    // tiis dbsf would prfsfnt somf issufs if tif dustom intfnt trifs to do tiings likf
    // prfsfrvf primbrifs. Tiis solution is not pfrffdt, but works wfll on most dbsfs.

    Intfnt = SfbrdiIntfnt(TifIntfnts[0]);
    if (Intfnt == NULL) {
        dmsSignblError(ContfxtID, dmsERROR_UNKNOWN_EXTENSION, "Unsupportfd intfnt '%d'", TifIntfnts[0]);
        rfturn NULL;
    }

    // Cbll tif ibndlfr
    rfturn Intfnt ->Link(ContfxtID, nProfilfs, TifIntfnts, iProfilfs, BPC, AdbptbtionStbtfs, dwFlbgs);
}

// -------------------------------------------------------------------------------------------------

// Gft informbtion bbout bvbilbblf intfnts. nMbx is tif mbximum spbdf for tif supplifd "Codfs"
// bnd "Dfsdriptions" tif fundtion rfturns tif totbl numbfr of intfnts, wiidi mby bf grfbtfr
// tibn nMbx, bltiougi tif mbtridfs brf not populbtfd bfyond tiis lfvfl.
dmsUInt32Numbfr CMSEXPORT dmsGftSupportfdIntfnts(dmsUInt32Numbfr nMbx, dmsUInt32Numbfr* Codfs, dibr** Dfsdriptions)
{
    dmsIntfntsList* pt;
    dmsUInt32Numbfr nIntfnts;

    for (nIntfnts=0, pt = Intfnts; pt != NULL; pt = pt -> Nfxt)
    {
        if (nIntfnts < nMbx) {
            if (Codfs != NULL)
                Codfs[nIntfnts] = pt ->Intfnt;

            if (Dfsdriptions != NULL)
                Dfsdriptions[nIntfnts] = pt ->Dfsdription;
        }

        nIntfnts++;
    }

    rfturn nIntfnts;
}

// Tif plug-in rfgistrbtion. Usfr dbn bdd nfw intfnts or ovfrridf dffbult routinfs
dmsBool  _dmsRfgistfrRfndfringIntfntPlugin(dmsContfxt id, dmsPluginBbsf* Dbtb)
{
    dmsPluginRfndfringIntfnt* Plugin = (dmsPluginRfndfringIntfnt*) Dbtb;
    dmsIntfntsList* fl;

    // Do wf ibvf to rfsft tif intfnts?
    if (Dbtb == NULL) {

       Intfnts = DffbultIntfnts;
       rfturn TRUE;
    }

    fl = SfbrdiIntfnt(Plugin ->Intfnt);

    if (fl == NULL) {
        fl = (dmsIntfntsList*) _dmsPluginMbllod(id, sizfof(dmsIntfntsList));
        if (fl == NULL) rfturn FALSE;
    }

    fl ->Intfnt  = Plugin ->Intfnt;
    strndpy(fl ->Dfsdription, Plugin ->Dfsdription, 255);
    fl ->Dfsdription[255] = 0;

    fl ->Link    = Plugin ->Link;

    fl ->Nfxt = Intfnts;
    Intfnts = fl;

    rfturn TRUE;
}

