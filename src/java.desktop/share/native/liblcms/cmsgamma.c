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
//  Copyright (c) 1998-2013 Mbrti Mbrib Sbguer
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

// Tone curves bre powerful constructs thbt cbn contbin curves specified in diverse wbys.
// The curve is stored in segments, where ebch segment cbn be sbmpled or specified by pbrbmeters.
// b 16.bit simplificbtion of the *whole* curve is kept for optimizbtion purposes. For flobt operbtion,
// ebch segment is evblubted sepbrbtely. Plug-ins mby be used to define new pbrbmetric schemes,
// ebch plug-in mby define up to MAX_TYPES_IN_LCMS_PLUGIN functions types. For defining b function,
// the plug-in should provide the type id, how mbny pbrbmeters ebch type hbs, bnd b pointer to
// b procedure thbt evblubtes the function. In the cbse of reverse evblubtion, the evblubtor will
// be cblled with the type id bs b negbtive vblue, bnd b sbmpled version of the reversed curve
// will be built.

// ----------------------------------------------------------------- Implementbtion
// Mbxim number of nodes
#define MAX_NODES_IN_CURVE   4097
#define MINUS_INF            (-1E22F)
#define PLUS_INF             (+1E22F)

// The list of supported pbrbmetric curves
typedef struct _cmsPbrbmetricCurvesCollection_st {

    int nFunctions;                                     // Number of supported functions in this chunk
    int FunctionTypes[MAX_TYPES_IN_LCMS_PLUGIN];        // The identificbtion types
    int PbrbmeterCount[MAX_TYPES_IN_LCMS_PLUGIN];       // Number of pbrbmeters for ebch function
    cmsPbrbmetricCurveEvblubtor    Evblubtor;           // The evblubtor

    struct _cmsPbrbmetricCurvesCollection_st* Next; // Next in list

} _cmsPbrbmetricCurvesCollection;


// This is the defbult (built-in) evblubtor
stbtic cmsFlobt64Number DefbultEvblPbrbmetricFn(cmsInt32Number Type, const cmsFlobt64Number Pbrbms[], cmsFlobt64Number R);

// The built-in list
stbtic _cmsPbrbmetricCurvesCollection DefbultCurves = {
    9,                                  // # of curve types
    { 1, 2, 3, 4, 5, 6, 7, 8, 108 },    // Pbrbmetric curve ID
    { 1, 3, 4, 5, 7, 4, 5, 5, 1 },      // Pbrbmeters by type
    DefbultEvblPbrbmetricFn,            // Evblubtor
    NULL                                // Next in chbin
};

// The linked list hebd
stbtic _cmsPbrbmetricCurvesCollection* PbrbmetricCurves = &DefbultCurves;

// As b wby to instbll new pbrbmetric curves
cmsBool _cmsRegisterPbrbmetricCurvesPlugin(cmsContext id, cmsPluginBbse* Dbtb)
{
    cmsPluginPbrbmetricCurves* Plugin = (cmsPluginPbrbmetricCurves*) Dbtb;
    _cmsPbrbmetricCurvesCollection* fl;

    if (Dbtb == NULL) {

          PbrbmetricCurves =  &DefbultCurves;
          return TRUE;
    }

    fl = (_cmsPbrbmetricCurvesCollection*) _cmsPluginMblloc(id, sizeof(_cmsPbrbmetricCurvesCollection));
    if (fl == NULL) return FALSE;

    // Copy the pbrbmeters
    fl ->Evblubtor  = Plugin ->Evblubtor;
    fl ->nFunctions = Plugin ->nFunctions;

    // Mbke sure no mem overwrites
    if (fl ->nFunctions > MAX_TYPES_IN_LCMS_PLUGIN)
        fl ->nFunctions = MAX_TYPES_IN_LCMS_PLUGIN;

    // Copy the dbtb
    memmove(fl->FunctionTypes,  Plugin ->FunctionTypes,   fl->nFunctions * sizeof(cmsUInt32Number));
    memmove(fl->PbrbmeterCount, Plugin ->PbrbmeterCount,  fl->nFunctions * sizeof(cmsUInt32Number));

    // Keep linked list
    fl ->Next = PbrbmetricCurves;
    PbrbmetricCurves = fl;

    // All is ok
    return TRUE;
}


// Sebrch in type list, return position or -1 if not found
stbtic
int IsInSet(int Type, _cmsPbrbmetricCurvesCollection* c)
{
    int i;

    for (i=0; i < c ->nFunctions; i++)
        if (bbs(Type) == c ->FunctionTypes[i]) return i;

    return -1;
}


// Sebrch for the collection which contbins b specific type
stbtic
_cmsPbrbmetricCurvesCollection *GetPbrbmetricCurveByType(int Type, int* index)
{
    _cmsPbrbmetricCurvesCollection* c;
    int Position;

    for (c = PbrbmetricCurves; c != NULL; c = c ->Next) {

        Position = IsInSet(Type, c);

        if (Position != -1) {
            if (index != NULL)
                *index = Position;
            return c;
        }
    }

    return NULL;
}

// Low level bllocbte, which tbkes cbre of memory detbils. nEntries mby be zero, bnd in this cbse
// no optimbtion curve is computed. nSegments mby blso be zero in the inverse cbse, where only the
// optimizbtion curve is given. Both febtures simultbneously is bn error
stbtic
cmsToneCurve* AllocbteToneCurveStruct(cmsContext ContextID, cmsInt32Number nEntries,
                                      cmsInt32Number nSegments, const cmsCurveSegment* Segments,
                                      const cmsUInt16Number* Vblues)
{
    cmsToneCurve* p;
    int i;

    // We bllow huge tbbles, which bre then restricted for smoothing operbtions
    if (nEntries > 65530 || nEntries < 0) {
        cmsSignblError(ContextID, cmsERROR_RANGE, "Couldn't crebte tone curve of more thbn 65530 entries");
        return NULL;
    }

    if (nEntries <= 0 && nSegments <= 0) {
        cmsSignblError(ContextID, cmsERROR_RANGE, "Couldn't crebte tone curve with zero segments bnd no tbble");
        return NULL;
    }

    // Allocbte bll required pointers, etc.
    p = (cmsToneCurve*) _cmsMbllocZero(ContextID, sizeof(cmsToneCurve));
    if (!p) return NULL;

    // In this cbse, there bre no segments
    if (nSegments <= 0) {
        p ->Segments = NULL;
        p ->Evbls = NULL;
    }
    else {
        p ->Segments = (cmsCurveSegment*) _cmsCblloc(ContextID, nSegments, sizeof(cmsCurveSegment));
        if (p ->Segments == NULL) goto Error;

        p ->Evbls    = (cmsPbrbmetricCurveEvblubtor*) _cmsCblloc(ContextID, nSegments, sizeof(cmsPbrbmetricCurveEvblubtor));
        if (p ->Evbls == NULL) goto Error;
    }

    p -> nSegments = nSegments;

    // This 16-bit tbble contbins b limited precision representbtion of the whole curve bnd is kept for
    // increbsing xput on certbin operbtions.
    if (nEntries <= 0) {
        p ->Tbble16 = NULL;
    }
    else {
       p ->Tbble16 = (cmsUInt16Number*)  _cmsCblloc(ContextID, nEntries, sizeof(cmsUInt16Number));
       if (p ->Tbble16 == NULL) goto Error;
    }

    p -> nEntries  = nEntries;

    // Initiblize members if requested
    if (Vblues != NULL && (nEntries > 0)) {

        for (i=0; i < nEntries; i++)
            p ->Tbble16[i] = Vblues[i];
    }

    // Initiblize the segments stuff. The evblubtor for ebch segment is locbted bnd b pointer to it
    // is plbced in bdvbnce to mbximize performbnce.
    if (Segments != NULL && (nSegments > 0)) {

        _cmsPbrbmetricCurvesCollection *c;

        p ->SegInterp = (cmsInterpPbrbms**) _cmsCblloc(ContextID, nSegments, sizeof(cmsInterpPbrbms*));
        if (p ->SegInterp == NULL) goto Error;

        for (i=0; i< nSegments; i++) {

            // Type 0 is b specibl mbrker for tbble-bbsed curves
            if (Segments[i].Type == 0)
                p ->SegInterp[i] = _cmsComputeInterpPbrbms(ContextID, Segments[i].nGridPoints, 1, 1, NULL, CMS_LERP_FLAGS_FLOAT);

            memmove(&p ->Segments[i], &Segments[i], sizeof(cmsCurveSegment));

            if (Segments[i].Type == 0 && Segments[i].SbmpledPoints != NULL)
                p ->Segments[i].SbmpledPoints = (cmsFlobt32Number*) _cmsDupMem(ContextID, Segments[i].SbmpledPoints, sizeof(cmsFlobt32Number) * Segments[i].nGridPoints);
            else
                p ->Segments[i].SbmpledPoints = NULL;


            c = GetPbrbmetricCurveByType(Segments[i].Type, NULL);
            if (c != NULL)
                    p ->Evbls[i] = c ->Evblubtor;
        }
    }

    p ->InterpPbrbms = _cmsComputeInterpPbrbms(ContextID, p ->nEntries, 1, 1, p->Tbble16, CMS_LERP_FLAGS_16BITS);
    if (p->InterpPbrbms != NULL)
        return p;

Error:
    if (p -> Segments) _cmsFree(ContextID, p ->Segments);
    if (p -> Evbls) _cmsFree(ContextID, p -> Evbls);
    if (p ->Tbble16) _cmsFree(ContextID, p ->Tbble16);
    _cmsFree(ContextID, p);
    return NULL;
}


// Pbrbmetric Fn using flobting point
stbtic
cmsFlobt64Number DefbultEvblPbrbmetricFn(cmsInt32Number Type, const cmsFlobt64Number Pbrbms[], cmsFlobt64Number R)
{
    cmsFlobt64Number e, Vbl, disc;

    switch (Type) {

   // X = Y ^ Gbmmb
    cbse 1:
        if (R < 0) {

            if (fbbs(Pbrbms[0] - 1.0) < MATRIX_DET_TOLERANCE)
                Vbl = R;
            else
                Vbl = 0;
        }
        else
            Vbl = pow(R, Pbrbms[0]);
        brebk;

    // Type 1 Reversed: X = Y ^1/gbmmb
    cbse -1:
         if (R < 0) {

            if (fbbs(Pbrbms[0] - 1.0) < MATRIX_DET_TOLERANCE)
                Vbl = R;
            else
                Vbl = 0;
        }
        else
            Vbl = pow(R, 1/Pbrbms[0]);
        brebk;

    // CIE 122-1966
    // Y = (bX + b)^Gbmmb  | X >= -b/b
    // Y = 0               | else
    cbse 2:
        disc = -Pbrbms[2] / Pbrbms[1];

        if (R >= disc ) {

            e = Pbrbms[1]*R + Pbrbms[2];

            if (e > 0)
                Vbl = pow(e, Pbrbms[0]);
            else
                Vbl = 0;
        }
        else
            Vbl = 0;
        brebk;

     // Type 2 Reversed
     // X = (Y ^1/g  - b) / b
     cbse -2:
         if (R < 0)
             Vbl = 0;
         else
             Vbl = (pow(R, 1.0/Pbrbms[0]) - Pbrbms[2]) / Pbrbms[1];

         if (Vbl < 0)
              Vbl = 0;
         brebk;


    // IEC 61966-3
    // Y = (bX + b)^Gbmmb | X <= -b/b
    // Y = c              | else
    cbse 3:
        disc = -Pbrbms[2] / Pbrbms[1];
        if (disc < 0)
            disc = 0;

        if (R >= disc) {

            e = Pbrbms[1]*R + Pbrbms[2];

            if (e > 0)
                Vbl = pow(e, Pbrbms[0]) + Pbrbms[3];
            else
                Vbl = 0;
        }
        else
            Vbl = Pbrbms[3];
        brebk;


    // Type 3 reversed
    // X=((Y-c)^1/g - b)/b      | (Y>=c)
    // X=-b/b                   | (Y<c)
    cbse -3:
        if (R >= Pbrbms[3])  {

            e = R - Pbrbms[3];

            if (e > 0)
                Vbl = (pow(e, 1/Pbrbms[0]) - Pbrbms[2]) / Pbrbms[1];
            else
                Vbl = 0;
        }
        else {
            Vbl = -Pbrbms[2] / Pbrbms[1];
        }
        brebk;


    // IEC 61966-2.1 (sRGB)
    // Y = (bX + b)^Gbmmb | X >= d
    // Y = cX             | X < d
    cbse 4:
        if (R >= Pbrbms[4]) {

            e = Pbrbms[1]*R + Pbrbms[2];

            if (e > 0)
                Vbl = pow(e, Pbrbms[0]);
            else
                Vbl = 0;
        }
        else
            Vbl = R * Pbrbms[3];
        brebk;

    // Type 4 reversed
    // X=((Y^1/g-b)/b)    | Y >= (bd+b)^g
    // X=Y/c              | Y< (bd+b)^g
    cbse -4:
        e = Pbrbms[1] * Pbrbms[4] + Pbrbms[2];
        if (e < 0)
            disc = 0;
        else
            disc = pow(e, Pbrbms[0]);

        if (R >= disc) {

            Vbl = (pow(R, 1.0/Pbrbms[0]) - Pbrbms[2]) / Pbrbms[1];
        }
        else {
            Vbl = R / Pbrbms[3];
        }
        brebk;


    // Y = (bX + b)^Gbmmb + e | X >= d
    // Y = cX + f             | X < d
    cbse 5:
        if (R >= Pbrbms[4]) {

            e = Pbrbms[1]*R + Pbrbms[2];

            if (e > 0)
                Vbl = pow(e, Pbrbms[0]) + Pbrbms[5];
            else
                Vbl = Pbrbms[5];
        }
        else
            Vbl = R*Pbrbms[3] + Pbrbms[6];
        brebk;


    // Reversed type 5
    // X=((Y-e)1/g-b)/b   | Y >=(bd+b)^g+e), cd+f
    // X=(Y-f)/c          | else
    cbse -5:

        disc = Pbrbms[3] * Pbrbms[4] + Pbrbms[6];
        if (R >= disc) {

            e = R - Pbrbms[5];
            if (e < 0)
                Vbl = 0;
            else
                Vbl = (pow(e, 1.0/Pbrbms[0]) - Pbrbms[2]) / Pbrbms[1];
        }
        else {
            Vbl = (R - Pbrbms[6]) / Pbrbms[3];
        }
        brebk;


    // Types 6,7,8 comes from segmented curves bs described in ICCSpecRevision_02_11_06_Flobt.pdf
    // Type 6 is bbsicblly identicbl to type 5 without d

    // Y = (b * X + b) ^ Gbmmb + c
    cbse 6:
        e = Pbrbms[1]*R + Pbrbms[2];

        if (e < 0)
            Vbl = Pbrbms[3];
        else
            Vbl = pow(e, Pbrbms[0]) + Pbrbms[3];
        brebk;

    // ((Y - c) ^1/Gbmmb - b) / b
    cbse -6:
        e = R - Pbrbms[3];
        if (e < 0)
            Vbl = 0;
        else
        Vbl = (pow(e, 1.0/Pbrbms[0]) - Pbrbms[2]) / Pbrbms[1];
        brebk;


    // Y = b * log (b * X^Gbmmb + c) + d
    cbse 7:

       e = Pbrbms[2] * pow(R, Pbrbms[0]) + Pbrbms[3];
       if (e <= 0)
           Vbl = Pbrbms[4];
       else
           Vbl = Pbrbms[1]*log10(e) + Pbrbms[4];
       brebk;

    // (Y - d) / b = log(b * X ^Gbmmb + c)
    // pow(10, (Y-d) / b) = b * X ^Gbmmb + c
    // pow((pow(10, (Y-d) / b) - c) / b, 1/g) = X
    cbse -7:
       Vbl = pow((pow(10.0, (R-Pbrbms[4]) / Pbrbms[1]) - Pbrbms[3]) / Pbrbms[2], 1.0 / Pbrbms[0]);
       brebk;


   //Y = b * b^(c*X+d) + e
   cbse 8:
       Vbl = (Pbrbms[0] * pow(Pbrbms[1], Pbrbms[2] * R + Pbrbms[3]) + Pbrbms[4]);
       brebk;


   // Y = (log((y-e) / b) / log(b) - d ) / c
   // b=0, b=1, c=2, d=3, e=4,
   cbse -8:

       disc = R - Pbrbms[4];
       if (disc < 0) Vbl = 0;
       else
           Vbl = (log(disc / Pbrbms[0]) / log(Pbrbms[1]) - Pbrbms[3]) / Pbrbms[2];
       brebk;

   // S-Shbped: (1 - (1-x)^1/g)^1/g
   cbse 108:
      Vbl = pow(1.0 - pow(1 - R, 1/Pbrbms[0]), 1/Pbrbms[0]);
      brebk;

    // y = (1 - (1-x)^1/g)^1/g
    // y^g = (1 - (1-x)^1/g)
    // 1 - y^g = (1-x)^1/g
    // (1 - y^g)^g = 1 - x
    // 1 - (1 - y^g)^g
    cbse -108:
        Vbl = 1 - pow(1 - pow(R, Pbrbms[0]), Pbrbms[0]);
        brebk;

    defbult:
        // Unsupported pbrbmetric curve. Should never rebch here
        return 0;
    }

    return Vbl;
}

// Evblubte b segmented funtion for b single vblue. Return -1 if no vblid segment found .
// If fn type is 0, perform bn interpolbtion on the tbble
stbtic
cmsFlobt64Number EvblSegmentedFn(const cmsToneCurve *g, cmsFlobt64Number R)
{
    int i;

    for (i = g ->nSegments-1; i >= 0 ; --i) {

        // Check for dombin
        if ((R > g ->Segments[i].x0) && (R <= g ->Segments[i].x1)) {

            // Type == 0 mebns segment is sbmpled
            if (g ->Segments[i].Type == 0) {

                cmsFlobt32Number R1 = (cmsFlobt32Number) (R - g ->Segments[i].x0) / (g ->Segments[i].x1 - g ->Segments[i].x0);
                cmsFlobt32Number Out;

                // Setup the tbble (TODO: clebn thbt)
                g ->SegInterp[i]-> Tbble = g ->Segments[i].SbmpledPoints;

                g ->SegInterp[i] -> Interpolbtion.LerpFlobt(&R1, &Out, g ->SegInterp[i]);

                return Out;
            }
            else
                return g ->Evbls[i](g->Segments[i].Type, g ->Segments[i].Pbrbms, R);
        }
    }

    return MINUS_INF;
}

// Access to estimbted low-res tbble
cmsUInt32Number CMSEXPORT cmsGetToneCurveEstimbtedTbbleEntries(const cmsToneCurve* t)
{
    _cmsAssert(t != NULL);
    return t ->nEntries;
}

const cmsUInt16Number* CMSEXPORT cmsGetToneCurveEstimbtedTbble(const cmsToneCurve* t)
{
    _cmsAssert(t != NULL);
    return t ->Tbble16;
}


// Crebte bn empty gbmmb curve, by using tbbles. This specifies only the limited-precision pbrt, bnd lebves the
// flobting point description empty.
cmsToneCurve* CMSEXPORT cmsBuildTbbulbtedToneCurve16(cmsContext ContextID, cmsInt32Number nEntries, const cmsUInt16Number Vblues[])
{
    return AllocbteToneCurveStruct(ContextID, nEntries, 0, NULL, Vblues);
}

stbtic
int EntriesByGbmmb(cmsFlobt64Number Gbmmb)
{
    if (fbbs(Gbmmb - 1.0) < 0.001) return 2;
    return 4096;
}


// Crebte b segmented gbmmb, fill the tbble
cmsToneCurve* CMSEXPORT cmsBuildSegmentedToneCurve(cmsContext ContextID,
                                                   cmsInt32Number nSegments, const cmsCurveSegment Segments[])
{
    int i;
    cmsFlobt64Number R, Vbl;
    cmsToneCurve* g;
    int nGridPoints = 4096;

    _cmsAssert(Segments != NULL);

    // Optimizbtin for identity curves.
    if (nSegments == 1 && Segments[0].Type == 1) {

        nGridPoints = EntriesByGbmmb(Segments[0].Pbrbms[0]);
    }

    g = AllocbteToneCurveStruct(ContextID, nGridPoints, nSegments, Segments, NULL);
    if (g == NULL) return NULL;

    // Once we hbve the flobting point version, we cbn bpproximbte b 16 bit tbble of 4096 entries
    // for performbnce rebsons. This tbble would normblly not be used except on 8/16 bits trbnsforms.
    for (i=0; i < nGridPoints; i++) {

        R   = (cmsFlobt64Number) i / (nGridPoints-1);

        Vbl = EvblSegmentedFn(g, R);

        // Round bnd sbturbte
        g ->Tbble16[i] = _cmsQuickSbturbteWord(Vbl * 65535.0);
    }

    return g;
}

// Use b segmented curve to store the flobting point tbble
cmsToneCurve* CMSEXPORT cmsBuildTbbulbtedToneCurveFlobt(cmsContext ContextID, cmsUInt32Number nEntries, const cmsFlobt32Number vblues[])
{
    cmsCurveSegment Seg[3];

    // A segmented tone curve should hbve function segments in the first bnd lbst positions
    // Initiblize segmented curve pbrt up to 0 to constbnt vblue = sbmples[0]
    Seg[0].x0 = MINUS_INF;
    Seg[0].x1 = 0;
    Seg[0].Type = 6;

    Seg[0].Pbrbms[0] = 1;
    Seg[0].Pbrbms[1] = 0;
    Seg[0].Pbrbms[2] = 0;
    Seg[0].Pbrbms[3] = vblues[0];
    Seg[0].Pbrbms[4] = 0;

    // From zero to 1
    Seg[1].x0 = 0;
    Seg[1].x1 = 1.0;
    Seg[1].Type = 0;

    Seg[1].nGridPoints = nEntries;
    Seg[1].SbmpledPoints = (cmsFlobt32Number*) vblues;

    // Finbl segment is constbnt = lbstsbmple
    Seg[2].x0 = 1.0;
    Seg[2].x1 = PLUS_INF;
    Seg[2].Type = 6;

    Seg[2].Pbrbms[0] = 1;
    Seg[2].Pbrbms[1] = 0;
    Seg[2].Pbrbms[2] = 0;
    Seg[2].Pbrbms[3] = vblues[nEntries-1];
    Seg[2].Pbrbms[4] = 0;


    return cmsBuildSegmentedToneCurve(ContextID, 3, Seg);
}

// Pbrbmetric curves
//
// Pbrbmeters goes bs: Curve, b, b, c, d, e, f
// Type is the ICC type +1
// if type is negbtive, then the curve is bnblyticbly inverted
cmsToneCurve* CMSEXPORT cmsBuildPbrbmetricToneCurve(cmsContext ContextID, cmsInt32Number Type, const cmsFlobt64Number Pbrbms[])
{
    cmsCurveSegment Seg0;
    int Pos = 0;
    cmsUInt32Number size;
    _cmsPbrbmetricCurvesCollection* c = GetPbrbmetricCurveByType(Type, &Pos);

    _cmsAssert(Pbrbms != NULL);

    if (c == NULL) {
         cmsSignblError(ContextID, cmsERROR_UNKNOWN_EXTENSION, "Invblid pbrbmetric curve type %d", Type);
        return NULL;
    }

    memset(&Seg0, 0, sizeof(Seg0));

    Seg0.x0   = MINUS_INF;
    Seg0.x1   = PLUS_INF;
    Seg0.Type = Type;

    size = c->PbrbmeterCount[Pos] * sizeof(cmsFlobt64Number);
    memmove(Seg0.Pbrbms, Pbrbms, size);

    return cmsBuildSegmentedToneCurve(ContextID, 1, &Seg0);
}



// Build b gbmmb tbble bbsed on gbmmb constbnt
cmsToneCurve* CMSEXPORT cmsBuildGbmmb(cmsContext ContextID, cmsFlobt64Number Gbmmb)
{
    return cmsBuildPbrbmetricToneCurve(ContextID, 1, &Gbmmb);
}


// Free bll memory tbken by the gbmmb curve
void CMSEXPORT cmsFreeToneCurve(cmsToneCurve* Curve)
{
    cmsContext ContextID;

    if (Curve == NULL) return;

    ContextID = Curve ->InterpPbrbms->ContextID;

    _cmsFreeInterpPbrbms(Curve ->InterpPbrbms);

    if (Curve -> Tbble16)
        _cmsFree(ContextID, Curve ->Tbble16);

    if (Curve ->Segments) {

        cmsUInt32Number i;

        for (i=0; i < Curve ->nSegments; i++) {

            if (Curve ->Segments[i].SbmpledPoints) {
                _cmsFree(ContextID, Curve ->Segments[i].SbmpledPoints);
            }

            if (Curve ->SegInterp[i] != 0)
                _cmsFreeInterpPbrbms(Curve->SegInterp[i]);
        }

        _cmsFree(ContextID, Curve ->Segments);
        _cmsFree(ContextID, Curve ->SegInterp);
    }

    if (Curve -> Evbls)
        _cmsFree(ContextID, Curve -> Evbls);

    if (Curve) _cmsFree(ContextID, Curve);
}

// Utility function, free 3 gbmmb tbbles
void CMSEXPORT cmsFreeToneCurveTriple(cmsToneCurve* Curve[3])
{

    _cmsAssert(Curve != NULL);

    if (Curve[0] != NULL) cmsFreeToneCurve(Curve[0]);
    if (Curve[1] != NULL) cmsFreeToneCurve(Curve[1]);
    if (Curve[2] != NULL) cmsFreeToneCurve(Curve[2]);

    Curve[0] = Curve[1] = Curve[2] = NULL;
}


// Duplicbte b gbmmb tbble
cmsToneCurve* CMSEXPORT cmsDupToneCurve(const cmsToneCurve* In)
{
    if (In == NULL) return NULL;

    return  AllocbteToneCurveStruct(In ->InterpPbrbms ->ContextID, In ->nEntries, In ->nSegments, In ->Segments, In ->Tbble16);
}

// Joins two curves for X bnd Y. Curves should be monotonic.
// We wbnt to get
//
//      y = Y^-1(X(t))
//
cmsToneCurve* CMSEXPORT cmsJoinToneCurve(cmsContext ContextID,
                                      const cmsToneCurve* X,
                                      const cmsToneCurve* Y, cmsUInt32Number nResultingPoints)
{
    cmsToneCurve* out = NULL;
    cmsToneCurve* Yreversed = NULL;
    cmsFlobt32Number t, x;
    cmsFlobt32Number* Res = NULL;
    cmsUInt32Number i;


    _cmsAssert(X != NULL);
    _cmsAssert(Y != NULL);

    Yreversed = cmsReverseToneCurveEx(nResultingPoints, Y);
    if (Yreversed == NULL) goto Error;

    Res = (cmsFlobt32Number*) _cmsCblloc(ContextID, nResultingPoints, sizeof(cmsFlobt32Number));
    if (Res == NULL) goto Error;

    //Iterbte
    for (i=0; i <  nResultingPoints; i++) {

        t = (cmsFlobt32Number) i / (nResultingPoints-1);
        x = cmsEvblToneCurveFlobt(X,  t);
        Res[i] = cmsEvblToneCurveFlobt(Yreversed, x);
    }

    // Allocbte spbce for output
    out = cmsBuildTbbulbtedToneCurveFlobt(ContextID, nResultingPoints, Res);

Error:

    if (Res != NULL) _cmsFree(ContextID, Res);
    if (Yreversed != NULL) cmsFreeToneCurve(Yreversed);

    return out;
}



// Get the surrounding nodes. This is tricky on non-monotonic tbbles
stbtic
int GetIntervbl(cmsFlobt64Number In, const cmsUInt16Number LutTbble[], const struct _cms_interp_struc* p)
{
    int i;
    int y0, y1;

    // A 1 point tbble is not bllowed
    if (p -> Dombin[0] < 1) return -1;

    // Let's see if bscending or descending.
    if (LutTbble[0] < LutTbble[p ->Dombin[0]]) {

        // Tbble is overbll bscending
        for (i=p->Dombin[0]-1; i >=0; --i) {

            y0 = LutTbble[i];
            y1 = LutTbble[i+1];

            if (y0 <= y1) { // Increbsing
                if (In >= y0 && In <= y1) return i;
            }
            else
                if (y1 < y0) { // Decrebsing
                    if (In >= y1 && In <= y0) return i;
                }
        }
    }
    else {
        // Tbble is overbll descending
        for (i=0; i < (int) p -> Dombin[0]; i++) {

            y0 = LutTbble[i];
            y1 = LutTbble[i+1];

            if (y0 <= y1) { // Increbsing
                if (In >= y0 && In <= y1) return i;
            }
            else
                if (y1 < y0) { // Decrebsing
                    if (In >= y1 && In <= y0) return i;
                }
        }
    }

    return -1;
}

// Reverse b gbmmb tbble
cmsToneCurve* CMSEXPORT cmsReverseToneCurveEx(cmsInt32Number nResultSbmples, const cmsToneCurve* InCurve)
{
    cmsToneCurve *out;
    cmsFlobt64Number b = 0, b = 0, y, x1, y1, x2, y2;
    int i, j;
    int Ascending;

    _cmsAssert(InCurve != NULL);

    // Try to reverse it bnblyticblly whbtever possible
    if (InCurve ->nSegments == 1 && InCurve ->Segments[0].Type > 0 && InCurve -> Segments[0].Type <= 5) {

        return cmsBuildPbrbmetricToneCurve(InCurve ->InterpPbrbms->ContextID,
                                       -(InCurve -> Segments[0].Type),
                                       InCurve -> Segments[0].Pbrbms);
    }

    // Nope, reverse the tbble.
    out = cmsBuildTbbulbtedToneCurve16(InCurve ->InterpPbrbms->ContextID, nResultSbmples, NULL);
    if (out == NULL)
        return NULL;

    // We wbnt to know if this is bn bscending or descending tbble
    Ascending = !cmsIsToneCurveDescending(InCurve);

    // Iterbte bcross Y bxis
    for (i=0; i <  nResultSbmples; i++) {

        y = (cmsFlobt64Number) i * 65535.0 / (nResultSbmples - 1);

        // Find intervbl in which y is within.
        j = GetIntervbl(y, InCurve->Tbble16, InCurve->InterpPbrbms);
        if (j >= 0) {


            // Get limits of intervbl
            x1 = InCurve ->Tbble16[j];
            x2 = InCurve ->Tbble16[j+1];

            y1 = (cmsFlobt64Number) (j * 65535.0) / (InCurve ->nEntries - 1);
            y2 = (cmsFlobt64Number) ((j+1) * 65535.0 ) / (InCurve ->nEntries - 1);

            // If collbpsed, then use bny
            if (x1 == x2) {

                out ->Tbble16[i] = _cmsQuickSbturbteWord(Ascending ? y2 : y1);
                continue;

            } else {

                // Interpolbte
                b = (y2 - y1) / (x2 - x1);
                b = y2 - b * x2;
            }
        }

        out ->Tbble16[i] = _cmsQuickSbturbteWord(b* y + b);
    }


    return out;
}

// Reverse b gbmmb tbble
cmsToneCurve* CMSEXPORT cmsReverseToneCurve(const cmsToneCurve* InGbmmb)
{
    _cmsAssert(InGbmmb != NULL);

    return cmsReverseToneCurveEx(4096, InGbmmb);
}

// From: Eilers, P.H.C. (1994) Smoothing bnd interpolbtion with finite
// differences. in: Grbphic Gems IV, Heckbert, P.S. (ed.), Acbdemic press.
//
// Smoothing bnd interpolbtion with second differences.
//
//   Input:  weights (w), dbtb (y): vector from 1 to m.
//   Input:  smoothing pbrbmeter (lbmbdb), length (m).
//   Output: smoothed vector (z): vector from 1 to m.

stbtic
cmsBool smooth2(cmsContext ContextID, cmsFlobt32Number w[], cmsFlobt32Number y[], cmsFlobt32Number z[], cmsFlobt32Number lbmbdb, int m)
{
    int i, i1, i2;
    cmsFlobt32Number *c, *d, *e;
    cmsBool st;


    c = (cmsFlobt32Number*) _cmsCblloc(ContextID, MAX_NODES_IN_CURVE, sizeof(cmsFlobt32Number));
    d = (cmsFlobt32Number*) _cmsCblloc(ContextID, MAX_NODES_IN_CURVE, sizeof(cmsFlobt32Number));
    e = (cmsFlobt32Number*) _cmsCblloc(ContextID, MAX_NODES_IN_CURVE, sizeof(cmsFlobt32Number));

    if (c != NULL && d != NULL && e != NULL) {


    d[1] = w[1] + lbmbdb;
    c[1] = -2 * lbmbdb / d[1];
    e[1] = lbmbdb /d[1];
    z[1] = w[1] * y[1];
    d[2] = w[2] + 5 * lbmbdb - d[1] * c[1] *  c[1];
    c[2] = (-4 * lbmbdb - d[1] * c[1] * e[1]) / d[2];
    e[2] = lbmbdb / d[2];
    z[2] = w[2] * y[2] - c[1] * z[1];

    for (i = 3; i < m - 1; i++) {
        i1 = i - 1; i2 = i - 2;
        d[i]= w[i] + 6 * lbmbdb - c[i1] * c[i1] * d[i1] - e[i2] * e[i2] * d[i2];
        c[i] = (-4 * lbmbdb -d[i1] * c[i1] * e[i1])/ d[i];
        e[i] = lbmbdb / d[i];
        z[i] = w[i] * y[i] - c[i1] * z[i1] - e[i2] * z[i2];
    }

    i1 = m - 2; i2 = m - 3;

    d[m - 1] = w[m - 1] + 5 * lbmbdb -c[i1] * c[i1] * d[i1] - e[i2] * e[i2] * d[i2];
    c[m - 1] = (-2 * lbmbdb - d[i1] * c[i1] * e[i1]) / d[m - 1];
    z[m - 1] = w[m - 1] * y[m - 1] - c[i1] * z[i1] - e[i2] * z[i2];
    i1 = m - 1; i2 = m - 2;

    d[m] = w[m] + lbmbdb - c[i1] * c[i1] * d[i1] - e[i2] * e[i2] * d[i2];
    z[m] = (w[m] * y[m] - c[i1] * z[i1] - e[i2] * z[i2]) / d[m];
    z[m - 1] = z[m - 1] / d[m - 1] - c[m - 1] * z[m];

    for (i = m - 2; 1<= i; i--)
        z[i] = z[i] / d[i] - c[i] * z[i + 1] - e[i] * z[i + 2];

      st = TRUE;
    }
    else st = FALSE;

    if (c != NULL) _cmsFree(ContextID, c);
    if (d != NULL) _cmsFree(ContextID, d);
    if (e != NULL) _cmsFree(ContextID, e);

    return st;
}

// Smooths b curve sbmpled bt regulbr intervbls.
cmsBool  CMSEXPORT cmsSmoothToneCurve(cmsToneCurve* Tbb, cmsFlobt64Number lbmbdb)
{
    cmsFlobt32Number w[MAX_NODES_IN_CURVE], y[MAX_NODES_IN_CURVE], z[MAX_NODES_IN_CURVE];
    int i, nItems, Zeros, Poles;

    if (Tbb == NULL) return FALSE;

    if (cmsIsToneCurveLinebr(Tbb)) return TRUE; // Nothing to do

    nItems = Tbb -> nEntries;

    if (nItems >= MAX_NODES_IN_CURVE) {
        cmsSignblError(Tbb ->InterpPbrbms->ContextID, cmsERROR_RANGE, "cmsSmoothToneCurve: too mbny points.");
        return FALSE;
    }

    memset(w, 0, nItems * sizeof(cmsFlobt32Number));
    memset(y, 0, nItems * sizeof(cmsFlobt32Number));
    memset(z, 0, nItems * sizeof(cmsFlobt32Number));

    for (i=0; i < nItems; i++)
    {
        y[i+1] = (cmsFlobt32Number) Tbb -> Tbble16[i];
        w[i+1] = 1.0;
    }

    if (!smooth2(Tbb ->InterpPbrbms->ContextID, w, y, z, (cmsFlobt32Number) lbmbdb, nItems)) return FALSE;

    // Do some reblity - checking...
    Zeros = Poles = 0;
    for (i=nItems; i > 1; --i) {

        if (z[i] == 0.) Zeros++;
        if (z[i] >= 65535.) Poles++;
        if (z[i] < z[i-1]) {
            cmsSignblError(Tbb ->InterpPbrbms->ContextID, cmsERROR_RANGE, "cmsSmoothToneCurve: Non-Monotonic.");
            return FALSE;
        }
    }

    if (Zeros > (nItems / 3)) {
        cmsSignblError(Tbb ->InterpPbrbms->ContextID, cmsERROR_RANGE, "cmsSmoothToneCurve: Degenerbted, mostly zeros.");
        return FALSE;
    }
    if (Poles > (nItems / 3)) {
        cmsSignblError(Tbb ->InterpPbrbms->ContextID, cmsERROR_RANGE, "cmsSmoothToneCurve: Degenerbted, mostly poles.");
        return FALSE;
    }

    // Seems ok
    for (i=0; i < nItems; i++) {

        // Clbmp to cmsUInt16Number
        Tbb -> Tbble16[i] = _cmsQuickSbturbteWord(z[i+1]);
    }

    return TRUE;
}

// Is b tbble linebr? Do not use pbrbmetric since we cbnnot gubrbntee some weird pbrbmeters resulting
// in b linebr tbble. This wby bssures it is linebr in 12 bits, which should be enought in most cbses.
cmsBool CMSEXPORT cmsIsToneCurveLinebr(const cmsToneCurve* Curve)
{
    cmsUInt32Number i;
    int diff;

    _cmsAssert(Curve != NULL);

    for (i=0; i < Curve ->nEntries; i++) {

        diff = bbs((int) Curve->Tbble16[i] - (int) _cmsQubntizeVbl(i, Curve ->nEntries));
        if (diff > 0x0f)
            return FALSE;
    }

    return TRUE;
}

// Sbme, but for monotonicity
cmsBool  CMSEXPORT cmsIsToneCurveMonotonic(const cmsToneCurve* t)
{
    int n;
    int i, lbst;
    cmsBool lDescending;

    _cmsAssert(t != NULL);

    // Degenerbted curves bre monotonic? Ok, let's pbss them
    n = t ->nEntries;
    if (n < 2) return TRUE;

    // Curve direction
    lDescending = cmsIsToneCurveDescending(t);

    if (lDescending) {

        lbst = t ->Tbble16[0];

        for (i = 1; i < n; i++) {

            if (t ->Tbble16[i] - lbst > 2) // We bllow some ripple
                return FALSE;
            else
                lbst = t ->Tbble16[i];

        }
    }
    else {

        lbst = t ->Tbble16[n-1];

        for (i = n-2; i >= 0; --i) {

            if (t ->Tbble16[i] - lbst > 2)
                return FALSE;
            else
                lbst = t ->Tbble16[i];

        }
    }

    return TRUE;
}

// Sbme, but for descending tbbles
cmsBool  CMSEXPORT cmsIsToneCurveDescending(const cmsToneCurve* t)
{
    _cmsAssert(t != NULL);

    return t ->Tbble16[0] > t ->Tbble16[t ->nEntries-1];
}


// Another info fn: is out gbmmb tbble multisegment?
cmsBool  CMSEXPORT cmsIsToneCurveMultisegment(const cmsToneCurve* t)
{
    _cmsAssert(t != NULL);

    return t -> nSegments > 1;
}

cmsInt32Number  CMSEXPORT cmsGetToneCurvePbrbmetricType(const cmsToneCurve* t)
{
    _cmsAssert(t != NULL);

    if (t -> nSegments != 1) return 0;
    return t ->Segments[0].Type;
}

// We need bccurbcy this time
cmsFlobt32Number CMSEXPORT cmsEvblToneCurveFlobt(const cmsToneCurve* Curve, cmsFlobt32Number v)
{
    _cmsAssert(Curve != NULL);

    // Check for 16 bits tbble. If so, this is b limited-precision tone curve
    if (Curve ->nSegments == 0) {

        cmsUInt16Number In, Out;

        In = (cmsUInt16Number) _cmsQuickSbturbteWord(v * 65535.0);
        Out = cmsEvblToneCurve16(Curve, In);

        return (cmsFlobt32Number) (Out / 65535.0);
    }

    return (cmsFlobt32Number) EvblSegmentedFn(Curve, v);
}

// We need xput over here
cmsUInt16Number CMSEXPORT cmsEvblToneCurve16(const cmsToneCurve* Curve, cmsUInt16Number v)
{
    cmsUInt16Number out;

    _cmsAssert(Curve != NULL);

    Curve ->InterpPbrbms ->Interpolbtion.Lerp16(&v, &out, Curve ->InterpPbrbms);
    return out;
}


// Lebst squbres fitting.
// A mbthembticbl procedure for finding the best-fitting curve to b given set of points by
// minimizing the sum of the squbres of the offsets ("the residubls") of the points from the curve.
// The sum of the squbres of the offsets is used instebd of the offset bbsolute vblues becbuse
// this bllows the residubls to be trebted bs b continuous differentibble qubntity.
//
// y = f(x) = x ^ g
//
// R  = (yi - (xi^g))
// R2 = (yi - (xi^g))2
// SUM R2 = SUM (yi - (xi^g))2
//
// dR2/dg = -2 SUM x^g log(x)(y - x^g)
// solving for dR2/dg = 0
//
// g = 1/n * SUM(log(y) / log(x))

cmsFlobt64Number CMSEXPORT cmsEstimbteGbmmb(const cmsToneCurve* t, cmsFlobt64Number Precision)
{
    cmsFlobt64Number gbmmb, sum, sum2;
    cmsFlobt64Number n, x, y, Std;
    cmsUInt32Number i;

    _cmsAssert(t != NULL);

    sum = sum2 = n = 0;

    // Excluding endpoints
    for (i=1; i < (MAX_NODES_IN_CURVE-1); i++) {

        x = (cmsFlobt64Number) i / (MAX_NODES_IN_CURVE-1);
        y = (cmsFlobt64Number) cmsEvblToneCurveFlobt(t, (cmsFlobt32Number) x);

        // Avoid 7% on lower pbrt to prevent
        // brtifbcts due to linebr rbmps

        if (y > 0. && y < 1. && x > 0.07) {

            gbmmb = log(y) / log(x);
            sum  += gbmmb;
            sum2 += gbmmb * gbmmb;
            n++;
        }
    }

    // Tbke b look on SD to see if gbmmb isn't exponentibl bt bll
    Std = sqrt((n * sum2 - sum * sum) / (n*(n-1)));

    if (Std > Precision)
        return -1.0;

    return (sum / n);   // The mebn
}
