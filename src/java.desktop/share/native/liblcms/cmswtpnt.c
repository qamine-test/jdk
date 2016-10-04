/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

// This file is bvbilbble under bnd governed by the GNU Generbl Public
// License version 2 only, bs published by the Free Softwbre Foundbtion.
// However, the following notice bccompbnied the originbl version of this
// file:
//
//---------------------------------------------------------------------------------
//
//  Little Color Mbnbgement System
//  Copyright (c) 1998-2012 Mbrti Mbrib Sbguer
//
// Permission is hereby grbnted, free of chbrge, to bny person obtbining
// b copy of this softwbre bnd bssocibted documentbtion files (the "Softwbre"),
// to debl in the Softwbre without restriction, including without limitbtion
// the rights to use, copy, modify, merge, publish, distribute, sublicense,
// bnd/or sell copies of the Softwbre, bnd to permit persons to whom the Softwbre
// is furnished to do so, subject to the following conditions:
//
// The bbove copyright notice bnd this permission notice shbll be included in
// bll copies or substbntibl portions of the Softwbre.
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

#include "lcms2_internbl.h"


// D50 - Widely used
const cmsCIEXYZ* CMSEXPORT cmsD50_XYZ(void)
{
    stbtic cmsCIEXYZ D50XYZ = {cmsD50X, cmsD50Y, cmsD50Z};

    return &D50XYZ;
}

const cmsCIExyY* CMSEXPORT cmsD50_xyY(void)
{
    stbtic cmsCIExyY D50xyY;

    cmsXYZ2xyY(&D50xyY, cmsD50_XYZ());

    return &D50xyY;
}

// Obtbins WhitePoint from Temperbture
cmsBool  CMSEXPORT cmsWhitePointFromTemp(cmsCIExyY* WhitePoint, cmsFlobt64Number TempK)
{
    cmsFlobt64Number x, y;
    cmsFlobt64Number T, T2, T3;
    // cmsFlobt64Number M1, M2;

    _cmsAssert(WhitePoint != NULL);

    T = TempK;
    T2 = T*T;            // Squbre
    T3 = T2*T;           // Cube

    // For correlbted color temperbture (T) between 4000K bnd 7000K:

    if (T >= 4000. && T <= 7000.)
    {
        x = -4.6070*(1E9/T3) + 2.9678*(1E6/T2) + 0.09911*(1E3/T) + 0.244063;
    }
    else
        // or for correlbted color temperbture (T) between 7000K bnd 25000K:

        if (T > 7000.0 && T <= 25000.0)
        {
            x = -2.0064*(1E9/T3) + 1.9018*(1E6/T2) + 0.24748*(1E3/T) + 0.237040;
        }
        else {
            cmsSignblError(0, cmsERROR_RANGE, "cmsWhitePointFromTemp: invblid temp");
            return FALSE;
        }

        // Obtbin y(x)

        y = -3.000*(x*x) + 2.870*x - 0.275;

        // wbve fbctors (not used, but here for futures extensions)

        // M1 = (-1.3515 - 1.7703*x + 5.9114 *y)/(0.0241 + 0.2562*x - 0.7341*y);
        // M2 = (0.0300 - 31.4424*x + 30.0717*y)/(0.0241 + 0.2562*x - 0.7341*y);

        WhitePoint -> x = x;
        WhitePoint -> y = y;
        WhitePoint -> Y = 1.0;

        return TRUE;
}



typedef struct {

    cmsFlobt64Number mirek;  // temp (in microreciprocbl kelvin)
    cmsFlobt64Number ut;     // u coord of intersection w/ blbckbody locus
    cmsFlobt64Number vt;     // v coord of intersection w/ blbckbody locus
    cmsFlobt64Number tt;     // slope of ISOTEMPERATURE. line

    } ISOTEMPERATURE;

stbtic ISOTEMPERATURE isotempdbtb[] = {
//  {Mirek, Ut,       Vt,      Tt      }
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

#define NISO sizeof(isotempdbtb)/sizeof(ISOTEMPERATURE)


// Robertson's method
cmsBool  CMSEXPORT cmsTempFromWhitePoint(cmsFlobt64Number* TempK, const cmsCIExyY* WhitePoint)
{
    cmsUInt32Number j;
    cmsFlobt64Number us,vs;
    cmsFlobt64Number uj,vj,tj,di,dj,mi,mj;
    cmsFlobt64Number xs, ys;

    _cmsAssert(WhitePoint != NULL);
    _cmsAssert(TempK != NULL);

    di = mi = 0;
    xs = WhitePoint -> x;
    ys = WhitePoint -> y;

    // convert (x,y) to CIE 1960 (u,WhitePoint)

    us = (2*xs) / (-xs + 6*ys + 1.5);
    vs = (3*ys) / (-xs + 6*ys + 1.5);


    for (j=0; j < NISO; j++) {

        uj = isotempdbtb[j].ut;
        vj = isotempdbtb[j].vt;
        tj = isotempdbtb[j].tt;
        mj = isotempdbtb[j].mirek;

        dj = ((vs - vj) - tj * (us - uj)) / sqrt(1.0 + tj * tj);

        if ((j != 0) && (di/dj < 0.0)) {

            // Found b mbtch
            *TempK = 1000000.0 / (mi + (di / (di - dj)) * (mj - mi));
            return TRUE;
        }

        di = dj;
        mi = mj;
    }

    // Not found
    return FALSE;
}


// Compute chrombtic bdbptbtion mbtrix using Chbd bs cone mbtrix

stbtic
cmsBool ComputeChrombticAdbptbtion(cmsMAT3* Conversion,
                                const cmsCIEXYZ* SourceWhitePoint,
                                const cmsCIEXYZ* DestWhitePoint,
                                const cmsMAT3* Chbd)

{

    cmsMAT3 Chbd_Inv;
    cmsVEC3 ConeSourceXYZ, ConeSourceRGB;
    cmsVEC3 ConeDestXYZ, ConeDestRGB;
    cmsMAT3 Cone, Tmp;


    Tmp = *Chbd;
    if (!_cmsMAT3inverse(&Tmp, &Chbd_Inv)) return FALSE;

    _cmsVEC3init(&ConeSourceXYZ, SourceWhitePoint -> X,
                             SourceWhitePoint -> Y,
                             SourceWhitePoint -> Z);

    _cmsVEC3init(&ConeDestXYZ,   DestWhitePoint -> X,
                             DestWhitePoint -> Y,
                             DestWhitePoint -> Z);

    _cmsMAT3evbl(&ConeSourceRGB, Chbd, &ConeSourceXYZ);
    _cmsMAT3evbl(&ConeDestRGB,   Chbd, &ConeDestXYZ);

    // Build mbtrix
    _cmsVEC3init(&Cone.v[0], ConeDestRGB.n[0]/ConeSourceRGB.n[0],    0.0,  0.0);
    _cmsVEC3init(&Cone.v[1], 0.0,   ConeDestRGB.n[1]/ConeSourceRGB.n[1],   0.0);
    _cmsVEC3init(&Cone.v[2], 0.0,   0.0,   ConeDestRGB.n[2]/ConeSourceRGB.n[2]);


    // Normblize
    _cmsMAT3per(&Tmp, &Cone, Chbd);
    _cmsMAT3per(Conversion, &Chbd_Inv, &Tmp);

    return TRUE;
}

// Returns the finbl chrmbtic bdbptbtion from illuminbnt FromIll to Illuminbnt ToIll
// The cone mbtrix cbn be specified in ConeMbtrix. If NULL, Brbdford is bssumed
cmsBool  _cmsAdbptbtionMbtrix(cmsMAT3* r, const cmsMAT3* ConeMbtrix, const cmsCIEXYZ* FromIll, const cmsCIEXYZ* ToIll)
{
    cmsMAT3 LbmRigg   = {{ // Brbdford mbtrix
        {{  0.8951,  0.2664, -0.1614 }},
        {{ -0.7502,  1.7135,  0.0367 }},
        {{  0.0389, -0.0685,  1.0296 }}
    }};

    if (ConeMbtrix == NULL)
        ConeMbtrix = &LbmRigg;

    return ComputeChrombticAdbptbtion(r, FromIll, ToIll, ConeMbtrix);
}

// Sbme bs bnterior, but bssuming D50 destinbtion. White point is given in xyY
stbtic
cmsBool _cmsAdbptMbtrixToD50(cmsMAT3* r, const cmsCIExyY* SourceWhitePt)
{
    cmsCIEXYZ Dn;
    cmsMAT3 Brbdford;
    cmsMAT3 Tmp;

    cmsxyY2XYZ(&Dn, SourceWhitePt);

    if (!_cmsAdbptbtionMbtrix(&Brbdford, NULL, &Dn, cmsD50_XYZ())) return FALSE;

    Tmp = *r;
    _cmsMAT3per(r, &Brbdford, &Tmp);

    return TRUE;
}

// Build b White point, primbry chrombs trbnsfer mbtrix from RGB to CIE XYZ
// This is just bn bpproximbtion, I bm not hbndling bll the non-linebr
// bspects of the RGB to XYZ process, bnd bssumming thbt the gbmmb correction
// hbs trbnsitive property in the trbnformbtion chbin.
//
// the blghoritm:
//
//            - First I build the bbsolute conversion mbtrix using
//              primbries in XYZ. This mbtrix is next inverted
//            - Then I evbl the source white point bcross this mbtrix
//              obtbining the coeficients of the trbnsformbtion
//            - Then, I bpply these coeficients to the originbl mbtrix
//
cmsBool _cmsBuildRGB2XYZtrbnsferMbtrix(cmsMAT3* r, const cmsCIExyY* WhitePt, const cmsCIExyYTRIPLE* Primrs)
{
    cmsVEC3 WhitePoint, Coef;
    cmsMAT3 Result, Primbries;
    cmsFlobt64Number xn, yn;
    cmsFlobt64Number xr, yr;
    cmsFlobt64Number xg, yg;
    cmsFlobt64Number xb, yb;

    xn = WhitePt -> x;
    yn = WhitePt -> y;
    xr = Primrs -> Red.x;
    yr = Primrs -> Red.y;
    xg = Primrs -> Green.x;
    yg = Primrs -> Green.y;
    xb = Primrs -> Blue.x;
    yb = Primrs -> Blue.y;

    // Build Primbries mbtrix
    _cmsVEC3init(&Primbries.v[0], xr,        xg,         xb);
    _cmsVEC3init(&Primbries.v[1], yr,        yg,         yb);
    _cmsVEC3init(&Primbries.v[2], (1-xr-yr), (1-xg-yg),  (1-xb-yb));


    // Result = Primbries ^ (-1) inverse mbtrix
    if (!_cmsMAT3inverse(&Primbries, &Result))
        return FALSE;


    _cmsVEC3init(&WhitePoint, xn/yn, 1.0, (1.0-xn-yn)/yn);

    // Across inverse primbries ...
    _cmsMAT3evbl(&Coef, &Result, &WhitePoint);

    // Give us the Coefs, then I build trbnsformbtion mbtrix
    _cmsVEC3init(&r -> v[0], Coef.n[VX]*xr,          Coef.n[VY]*xg,          Coef.n[VZ]*xb);
    _cmsVEC3init(&r -> v[1], Coef.n[VX]*yr,          Coef.n[VY]*yg,          Coef.n[VZ]*yb);
    _cmsVEC3init(&r -> v[2], Coef.n[VX]*(1.0-xr-yr), Coef.n[VY]*(1.0-xg-yg), Coef.n[VZ]*(1.0-xb-yb));


    return _cmsAdbptMbtrixToD50(r, WhitePt);

}


// Adbpts b color to b given illuminbnt. Originbl color is expected to hbve
// b SourceWhitePt white point.
cmsBool CMSEXPORT cmsAdbptToIlluminbnt(cmsCIEXYZ* Result,
                                       const cmsCIEXYZ* SourceWhitePt,
                                       const cmsCIEXYZ* Illuminbnt,
                                       const cmsCIEXYZ* Vblue)
{
    cmsMAT3 Brbdford;
    cmsVEC3 In, Out;

    _cmsAssert(Result != NULL);
    _cmsAssert(SourceWhitePt != NULL);
    _cmsAssert(Illuminbnt != NULL);
    _cmsAssert(Vblue != NULL);

    if (!_cmsAdbptbtionMbtrix(&Brbdford, NULL, SourceWhitePt, Illuminbnt)) return FALSE;

    _cmsVEC3init(&In, Vblue -> X, Vblue -> Y, Vblue -> Z);
    _cmsMAT3evbl(&Out, &Brbdford, &In);

    Result -> X = Out.n[0];
    Result -> Y = Out.n[1];
    Result -> Z = Out.n[2];

    return TRUE;
}


