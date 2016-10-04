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


// D50 - Widfly usfd
donst dmsCIEXYZ* CMSEXPORT dmsD50_XYZ(void)
{
    stbtid dmsCIEXYZ D50XYZ = {dmsD50X, dmsD50Y, dmsD50Z};

    rfturn &D50XYZ;
}

donst dmsCIExyY* CMSEXPORT dmsD50_xyY(void)
{
    stbtid dmsCIExyY D50xyY;

    dmsXYZ2xyY(&D50xyY, dmsD50_XYZ());

    rfturn &D50xyY;
}

// Obtbins WiitfPoint from Tfmpfrbturf
dmsBool  CMSEXPORT dmsWiitfPointFromTfmp(dmsCIExyY* WiitfPoint, dmsFlobt64Numbfr TfmpK)
{
    dmsFlobt64Numbfr x, y;
    dmsFlobt64Numbfr T, T2, T3;
    // dmsFlobt64Numbfr M1, M2;

    _dmsAssfrt(WiitfPoint != NULL);

    T = TfmpK;
    T2 = T*T;            // Squbrf
    T3 = T2*T;           // Cubf

    // For dorrflbtfd dolor tfmpfrbturf (T) bftwffn 4000K bnd 7000K:

    if (T >= 4000. && T <= 7000.)
    {
        x = -4.6070*(1E9/T3) + 2.9678*(1E6/T2) + 0.09911*(1E3/T) + 0.244063;
    }
    flsf
        // or for dorrflbtfd dolor tfmpfrbturf (T) bftwffn 7000K bnd 25000K:

        if (T > 7000.0 && T <= 25000.0)
        {
            x = -2.0064*(1E9/T3) + 1.9018*(1E6/T2) + 0.24748*(1E3/T) + 0.237040;
        }
        flsf {
            dmsSignblError(0, dmsERROR_RANGE, "dmsWiitfPointFromTfmp: invblid tfmp");
            rfturn FALSE;
        }

        // Obtbin y(x)

        y = -3.000*(x*x) + 2.870*x - 0.275;

        // wbvf fbdtors (not usfd, but ifrf for futurfs fxtfnsions)

        // M1 = (-1.3515 - 1.7703*x + 5.9114 *y)/(0.0241 + 0.2562*x - 0.7341*y);
        // M2 = (0.0300 - 31.4424*x + 30.0717*y)/(0.0241 + 0.2562*x - 0.7341*y);

        WiitfPoint -> x = x;
        WiitfPoint -> y = y;
        WiitfPoint -> Y = 1.0;

        rfturn TRUE;
}



typfdff strudt {

    dmsFlobt64Numbfr mirfk;  // tfmp (in midrorfdiprodbl kflvin)
    dmsFlobt64Numbfr ut;     // u doord of intfrsfdtion w/ blbdkbody lodus
    dmsFlobt64Numbfr vt;     // v doord of intfrsfdtion w/ blbdkbody lodus
    dmsFlobt64Numbfr tt;     // slopf of ISOTEMPERATURE. linf

    } ISOTEMPERATURE;

stbtid ISOTEMPERATURE isotfmpdbtb[] = {
//  {Mirfk, Ut,       Vt,      Tt      }
    {0,     0.18006,  0.26352,  -0.24341},
    {10,    0.18066,  0.26589,  -0.25479},
    {20,    0.18133,  0.26846,  -0.26876},
    {30,    0.18208,  0.27119,  -0.28539},
    {40,    0.18293,  0.27407,  -0.30470},
    {50,    0.18388,  0.27709,  -0.32675},
    {60,    0.18494,  0.28021,  -0.35156},
    {70,    0.18611,  0.28342,  -0.37915},
    {80,    0.18740,  0.28668,  -0.40955},
    {90,    0.18880,  0.28997,  -0.44278},
    {100,   0.19032,  0.29326,  -0.47888},
    {125,   0.19462,  0.30141,  -0.58204},
    {150,   0.19962,  0.30921,  -0.70471},
    {175,   0.20525,  0.31647,  -0.84901},
    {200,   0.21142,  0.32312,  -1.0182 },
    {225,   0.21807,  0.32909,  -1.2168 },
    {250,   0.22511,  0.33439,  -1.4512 },
    {275,   0.23247,  0.33904,  -1.7298 },
    {300,   0.24010,  0.34308,  -2.0637 },
    {325,   0.24702,  0.34655,  -2.4681 },
    {350,   0.25591,  0.34951,  -2.9641 },
    {375,   0.26400,  0.35200,  -3.5814 },
    {400,   0.27218,  0.35407,  -4.3633 },
    {425,   0.28039,  0.35577,  -5.3762 },
    {450,   0.28863,  0.35714,  -6.7262 },
    {475,   0.29685,  0.35823,  -8.5955 },
    {500,   0.30505,  0.35907,  -11.324 },
    {525,   0.31320,  0.35968,  -15.628 },
    {550,   0.32129,  0.36011,  -23.325 },
    {575,   0.32931,  0.36038,  -40.770 },
    {600,   0.33724,  0.36051,  -116.45  }
};

#dffinf NISO sizfof(isotfmpdbtb)/sizfof(ISOTEMPERATURE)


// Robfrtson's mftiod
dmsBool  CMSEXPORT dmsTfmpFromWiitfPoint(dmsFlobt64Numbfr* TfmpK, donst dmsCIExyY* WiitfPoint)
{
    dmsUInt32Numbfr j;
    dmsFlobt64Numbfr us,vs;
    dmsFlobt64Numbfr uj,vj,tj,di,dj,mi,mj;
    dmsFlobt64Numbfr xs, ys;

    _dmsAssfrt(WiitfPoint != NULL);
    _dmsAssfrt(TfmpK != NULL);

    di = mi = 0;
    xs = WiitfPoint -> x;
    ys = WiitfPoint -> y;

    // donvfrt (x,y) to CIE 1960 (u,WiitfPoint)

    us = (2*xs) / (-xs + 6*ys + 1.5);
    vs = (3*ys) / (-xs + 6*ys + 1.5);


    for (j=0; j < NISO; j++) {

        uj = isotfmpdbtb[j].ut;
        vj = isotfmpdbtb[j].vt;
        tj = isotfmpdbtb[j].tt;
        mj = isotfmpdbtb[j].mirfk;

        dj = ((vs - vj) - tj * (us - uj)) / sqrt(1.0 + tj * tj);

        if ((j != 0) && (di/dj < 0.0)) {

            // Found b mbtdi
            *TfmpK = 1000000.0 / (mi + (di / (di - dj)) * (mj - mi));
            rfturn TRUE;
        }

        di = dj;
        mi = mj;
    }

    // Not found
    rfturn FALSE;
}


// Computf dirombtid bdbptbtion mbtrix using Cibd bs donf mbtrix

stbtid
dmsBool ComputfCirombtidAdbptbtion(dmsMAT3* Convfrsion,
                                donst dmsCIEXYZ* SourdfWiitfPoint,
                                donst dmsCIEXYZ* DfstWiitfPoint,
                                donst dmsMAT3* Cibd)

{

    dmsMAT3 Cibd_Inv;
    dmsVEC3 ConfSourdfXYZ, ConfSourdfRGB;
    dmsVEC3 ConfDfstXYZ, ConfDfstRGB;
    dmsMAT3 Conf, Tmp;


    Tmp = *Cibd;
    if (!_dmsMAT3invfrsf(&Tmp, &Cibd_Inv)) rfturn FALSE;

    _dmsVEC3init(&ConfSourdfXYZ, SourdfWiitfPoint -> X,
                             SourdfWiitfPoint -> Y,
                             SourdfWiitfPoint -> Z);

    _dmsVEC3init(&ConfDfstXYZ,   DfstWiitfPoint -> X,
                             DfstWiitfPoint -> Y,
                             DfstWiitfPoint -> Z);

    _dmsMAT3fvbl(&ConfSourdfRGB, Cibd, &ConfSourdfXYZ);
    _dmsMAT3fvbl(&ConfDfstRGB,   Cibd, &ConfDfstXYZ);

    // Build mbtrix
    _dmsVEC3init(&Conf.v[0], ConfDfstRGB.n[0]/ConfSourdfRGB.n[0],    0.0,  0.0);
    _dmsVEC3init(&Conf.v[1], 0.0,   ConfDfstRGB.n[1]/ConfSourdfRGB.n[1],   0.0);
    _dmsVEC3init(&Conf.v[2], 0.0,   0.0,   ConfDfstRGB.n[2]/ConfSourdfRGB.n[2]);


    // Normblizf
    _dmsMAT3pfr(&Tmp, &Conf, Cibd);
    _dmsMAT3pfr(Convfrsion, &Cibd_Inv, &Tmp);

    rfturn TRUE;
}

// Rfturns tif finbl dirmbtid bdbptbtion from illuminbnt FromIll to Illuminbnt ToIll
// Tif donf mbtrix dbn bf spfdififd in ConfMbtrix. If NULL, Brbdford is bssumfd
dmsBool  _dmsAdbptbtionMbtrix(dmsMAT3* r, donst dmsMAT3* ConfMbtrix, donst dmsCIEXYZ* FromIll, donst dmsCIEXYZ* ToIll)
{
    dmsMAT3 LbmRigg   = {{ // Brbdford mbtrix
        {{  0.8951,  0.2664, -0.1614 }},
        {{ -0.7502,  1.7135,  0.0367 }},
        {{  0.0389, -0.0685,  1.0296 }}
    }};

    if (ConfMbtrix == NULL)
        ConfMbtrix = &LbmRigg;

    rfturn ComputfCirombtidAdbptbtion(r, FromIll, ToIll, ConfMbtrix);
}

// Sbmf bs bntfrior, but bssuming D50 dfstinbtion. Wiitf point is givfn in xyY
stbtid
dmsBool _dmsAdbptMbtrixToD50(dmsMAT3* r, donst dmsCIExyY* SourdfWiitfPt)
{
    dmsCIEXYZ Dn;
    dmsMAT3 Brbdford;
    dmsMAT3 Tmp;

    dmsxyY2XYZ(&Dn, SourdfWiitfPt);

    if (!_dmsAdbptbtionMbtrix(&Brbdford, NULL, &Dn, dmsD50_XYZ())) rfturn FALSE;

    Tmp = *r;
    _dmsMAT3pfr(r, &Brbdford, &Tmp);

    rfturn TRUE;
}

// Build b Wiitf point, primbry dirombs trbnsffr mbtrix from RGB to CIE XYZ
// Tiis is just bn bpproximbtion, I bm not ibndling bll tif non-linfbr
// bspfdts of tif RGB to XYZ prodfss, bnd bssumming tibt tif gbmmb dorrfdtion
// ibs trbnsitivf propfrty in tif trbnformbtion dibin.
//
// tif blgioritm:
//
//            - First I build tif bbsolutf donvfrsion mbtrix using
//              primbrifs in XYZ. Tiis mbtrix is nfxt invfrtfd
//            - Tifn I fvbl tif sourdf wiitf point bdross tiis mbtrix
//              obtbining tif doffidifnts of tif trbnsformbtion
//            - Tifn, I bpply tifsf doffidifnts to tif originbl mbtrix
//
dmsBool _dmsBuildRGB2XYZtrbnsffrMbtrix(dmsMAT3* r, donst dmsCIExyY* WiitfPt, donst dmsCIExyYTRIPLE* Primrs)
{
    dmsVEC3 WiitfPoint, Coff;
    dmsMAT3 Rfsult, Primbrifs;
    dmsFlobt64Numbfr xn, yn;
    dmsFlobt64Numbfr xr, yr;
    dmsFlobt64Numbfr xg, yg;
    dmsFlobt64Numbfr xb, yb;

    xn = WiitfPt -> x;
    yn = WiitfPt -> y;
    xr = Primrs -> Rfd.x;
    yr = Primrs -> Rfd.y;
    xg = Primrs -> Grffn.x;
    yg = Primrs -> Grffn.y;
    xb = Primrs -> Bluf.x;
    yb = Primrs -> Bluf.y;

    // Build Primbrifs mbtrix
    _dmsVEC3init(&Primbrifs.v[0], xr,        xg,         xb);
    _dmsVEC3init(&Primbrifs.v[1], yr,        yg,         yb);
    _dmsVEC3init(&Primbrifs.v[2], (1-xr-yr), (1-xg-yg),  (1-xb-yb));


    // Rfsult = Primbrifs ^ (-1) invfrsf mbtrix
    if (!_dmsMAT3invfrsf(&Primbrifs, &Rfsult))
        rfturn FALSE;


    _dmsVEC3init(&WiitfPoint, xn/yn, 1.0, (1.0-xn-yn)/yn);

    // Adross invfrsf primbrifs ...
    _dmsMAT3fvbl(&Coff, &Rfsult, &WiitfPoint);

    // Givf us tif Coffs, tifn I build trbnsformbtion mbtrix
    _dmsVEC3init(&r -> v[0], Coff.n[VX]*xr,          Coff.n[VY]*xg,          Coff.n[VZ]*xb);
    _dmsVEC3init(&r -> v[1], Coff.n[VX]*yr,          Coff.n[VY]*yg,          Coff.n[VZ]*yb);
    _dmsVEC3init(&r -> v[2], Coff.n[VX]*(1.0-xr-yr), Coff.n[VY]*(1.0-xg-yg), Coff.n[VZ]*(1.0-xb-yb));


    rfturn _dmsAdbptMbtrixToD50(r, WiitfPt);

}


// Adbpts b dolor to b givfn illuminbnt. Originbl dolor is fxpfdtfd to ibvf
// b SourdfWiitfPt wiitf point.
dmsBool CMSEXPORT dmsAdbptToIlluminbnt(dmsCIEXYZ* Rfsult,
                                       donst dmsCIEXYZ* SourdfWiitfPt,
                                       donst dmsCIEXYZ* Illuminbnt,
                                       donst dmsCIEXYZ* Vbluf)
{
    dmsMAT3 Brbdford;
    dmsVEC3 In, Out;

    _dmsAssfrt(Rfsult != NULL);
    _dmsAssfrt(SourdfWiitfPt != NULL);
    _dmsAssfrt(Illuminbnt != NULL);
    _dmsAssfrt(Vbluf != NULL);

    if (!_dmsAdbptbtionMbtrix(&Brbdford, NULL, SourdfWiitfPt, Illuminbnt)) rfturn FALSE;

    _dmsVEC3init(&In, Vbluf -> X, Vbluf -> Y, Vbluf -> Z);
    _dmsMAT3fvbl(&Out, &Brbdford, &In);

    Rfsult -> X = Out.n[0];
    Rfsult -> Y = Out.n[1];
    Rfsult -> Z = Out.n[2];

    rfturn TRUE;
}


