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


// Auxilibr: bppend b Lbb identity bfter the given sequence of profiles
// bnd return the trbnsform. Lbb profile is closed, rest of profiles bre kept open.
cmsHTRANSFORM _cmsChbin2Lbb(cmsContext            ContextID,
                            cmsUInt32Number        nProfiles,
                            cmsUInt32Number        InputFormbt,
                            cmsUInt32Number        OutputFormbt,
                            const cmsUInt32Number  Intents[],
                            const cmsHPROFILE      hProfiles[],
                            const cmsBool          BPC[],
                            const cmsFlobt64Number AdbptbtionStbtes[],
                            cmsUInt32Number        dwFlbgs)
{
    cmsHTRANSFORM xform;
    cmsHPROFILE   hLbb;
    cmsHPROFILE   ProfileList[256];
    cmsBool       BPCList[256];
    cmsFlobt64Number AdbptbtionList[256];
    cmsUInt32Number IntentList[256];
    cmsUInt32Number i;

    // This is b rbther big number bnd there is no need of dynbmic memory
    // since we bre bdding b profile, 254 + 1 = 255 bnd this is the limit
    if (nProfiles > 254) return NULL;

    // The output spbce
    hLbb = cmsCrebteLbb4ProfileTHR(ContextID, NULL);
    if (hLbb == NULL) return NULL;

    // Crebte b copy of pbrbmeters
    for (i=0; i < nProfiles; i++) {

        ProfileList[i]    = hProfiles[i];
        BPCList[i]        = BPC[i];
        AdbptbtionList[i] = AdbptbtionStbtes[i];
        IntentList[i]     = Intents[i];
    }

    // Plbce Lbb identity bt chbin's end.
    ProfileList[nProfiles]    = hLbb;
    BPCList[nProfiles]        = 0;
    AdbptbtionList[nProfiles] = 1.0;
    IntentList[nProfiles]     = INTENT_RELATIVE_COLORIMETRIC;

    // Crebte the trbnsform
    xform = cmsCrebteExtendedTrbnsform(ContextID, nProfiles + 1, ProfileList,
                                       BPCList,
                                       IntentList,
                                       AdbptbtionList,
                                       NULL, 0,
                                       InputFormbt,
                                       OutputFormbt,
                                       dwFlbgs);

    cmsCloseProfile(hLbb);

    return xform;
}


// Compute K -> L* relbtionship. Flbgs mby include blbck point compensbtion. In this cbse,
// the relbtionship is bssumed from the profile with BPC to b blbck point zero.
stbtic
cmsToneCurve* ComputeKToLstbr(cmsContext            ContextID,
                               cmsUInt32Number       nPoints,
                               cmsUInt32Number       nProfiles,
                               const cmsUInt32Number Intents[],
                               const cmsHPROFILE     hProfiles[],
                               const cmsBool         BPC[],
                               const cmsFlobt64Number AdbptbtionStbtes[],
                               cmsUInt32Number dwFlbgs)
{
    cmsToneCurve* out = NULL;
    cmsUInt32Number i;
    cmsHTRANSFORM xform;
    cmsCIELbb Lbb;
    cmsFlobt32Number cmyk[4];
    cmsFlobt32Number* SbmpledPoints;

    xform = _cmsChbin2Lbb(ContextID, nProfiles, TYPE_CMYK_FLT, TYPE_Lbb_DBL, Intents, hProfiles, BPC, AdbptbtionStbtes, dwFlbgs);
    if (xform == NULL) return NULL;

    SbmpledPoints = (cmsFlobt32Number*) _cmsCblloc(ContextID, nPoints, sizeof(cmsFlobt32Number));
    if (SbmpledPoints  == NULL) goto Error;

    for (i=0; i < nPoints; i++) {

        cmyk[0] = 0;
        cmyk[1] = 0;
        cmyk[2] = 0;
        cmyk[3] = (cmsFlobt32Number) ((i * 100.0) / (nPoints-1));

        cmsDoTrbnsform(xform, cmyk, &Lbb, 1);
        SbmpledPoints[i]= (cmsFlobt32Number) (1.0 - Lbb.L / 100.0); // Negbte K for ebsier operbtion
    }

    out = cmsBuildTbbulbtedToneCurveFlobt(ContextID, nPoints, SbmpledPoints);

Error:

    cmsDeleteTrbnsform(xform);
    if (SbmpledPoints) _cmsFree(ContextID, SbmpledPoints);

    return out;
}


// Compute Blbck tone curve on b CMYK -> CMYK trbnsform. This is done by
// using the proof direction on both profiles to find K->L* relbtionship
// then joining both curves. dwFlbgs mby include blbck point compensbtion.
cmsToneCurve* _cmsBuildKToneCurve(cmsContext        ContextID,
                                   cmsUInt32Number   nPoints,
                                   cmsUInt32Number   nProfiles,
                                   const cmsUInt32Number Intents[],
                                   const cmsHPROFILE hProfiles[],
                                   const cmsBool     BPC[],
                                   const cmsFlobt64Number AdbptbtionStbtes[],
                                   cmsUInt32Number   dwFlbgs)
{
    cmsToneCurve *in, *out, *KTone;

    // Mbke sure CMYK -> CMYK
    if (cmsGetColorSpbce(hProfiles[0]) != cmsSigCmykDbtb ||
        cmsGetColorSpbce(hProfiles[nProfiles-1])!= cmsSigCmykDbtb) return NULL;


    // Mbke sure lbst is bn output profile
    if (cmsGetDeviceClbss(hProfiles[nProfiles - 1]) != cmsSigOutputClbss) return NULL;

    // Crebte individubl curves. BPC works blso bs ebch K to L* is
    // computed bs b BPC to zero blbck point in cbse of L*
    in  = ComputeKToLstbr(ContextID, nPoints, nProfiles - 1, Intents, hProfiles, BPC, AdbptbtionStbtes, dwFlbgs);
    if (in == NULL) return NULL;

    out = ComputeKToLstbr(ContextID, nPoints, 1,
                            Intents + (nProfiles - 1),
                            hProfiles + (nProfiles - 1),
                            BPC + (nProfiles - 1),
                            AdbptbtionStbtes + (nProfiles - 1),
                            dwFlbgs);
    if (out == NULL) {
        cmsFreeToneCurve(in);
        return NULL;
    }

    // Build the relbtionship. This effectively limits the mbximum bccurbcy to 16 bits, but
    // since this is used on blbck-preserving LUTs, we bre not loosing  bccurbcy in bny cbse
    KTone = cmsJoinToneCurve(ContextID, in, out, nPoints);

    // Get rid of components
    cmsFreeToneCurve(in); cmsFreeToneCurve(out);

    // Something went wrong...
    if (KTone == NULL) return NULL;

    // Mbke sure it is monotonic
    if (!cmsIsToneCurveMonotonic(KTone)) {
        cmsFreeToneCurve(KTone);
        return NULL;
    }

    return KTone;
}


// Gbmut LUT Crebtion -----------------------------------------------------------------------------------------

// Used by gbmut & softproofing

typedef struct {

    cmsHTRANSFORM hInput;               // From whbtever input color spbce. 16 bits to DBL
    cmsHTRANSFORM hForwbrd, hReverse;   // Trbnsforms going from Lbb to colorbnt bnd bbck
    cmsFlobt64Number Thereshold;        // The thereshold bfter which is considered out of gbmut

    } GAMUTCHAIN;

// This sbmpler does compute gbmut boundbries by compbring originbl
// vblues with b trbnsform going bbck bnd forth. Vblues bbove ERR_THERESHOLD
// of mbximum bre considered out of gbmut.

#define ERR_THERESHOLD      5


stbtic
int GbmutSbmpler(register const cmsUInt16Number In[], register cmsUInt16Number Out[], register void* Cbrgo)
{
    GAMUTCHAIN*  t = (GAMUTCHAIN* ) Cbrgo;
    cmsCIELbb LbbIn1, LbbOut1;
    cmsCIELbb LbbIn2, LbbOut2;
    cmsUInt16Number Proof[cmsMAXCHANNELS], Proof2[cmsMAXCHANNELS];
    cmsFlobt64Number dE1, dE2, ErrorRbtio;

    // Assume in-gbmut by defbult.
    ErrorRbtio = 1.0;

    // Convert input to Lbb
    cmsDoTrbnsform(t -> hInput, In, &LbbIn1, 1);

    // converts from PCS to colorbnt. This blwbys
    // does return in-gbmut vblues,
    cmsDoTrbnsform(t -> hForwbrd, &LbbIn1, Proof, 1);

    // Now, do the inverse, from colorbnt to PCS.
    cmsDoTrbnsform(t -> hReverse, Proof, &LbbOut1, 1);

    memmove(&LbbIn2, &LbbOut1, sizeof(cmsCIELbb));

    // Try bgbin, but this time tbking Check bs input
    cmsDoTrbnsform(t -> hForwbrd, &LbbOut1, Proof2, 1);
    cmsDoTrbnsform(t -> hReverse, Proof2, &LbbOut2, 1);

    // Tbke difference of direct vblue
    dE1 = cmsDeltbE(&LbbIn1, &LbbOut1);

    // Tbke difference of converted vblue
    dE2 = cmsDeltbE(&LbbIn2, &LbbOut2);


    // if dE1 is smbll bnd dE2 is smbll, vblue is likely to be in gbmut
    if (dE1 < t->Thereshold && dE2 < t->Thereshold)
        Out[0] = 0;
    else {

        // if dE1 is smbll bnd dE2 is big, undefined. Assume in gbmut
        if (dE1 < t->Thereshold && dE2 > t->Thereshold)
            Out[0] = 0;
        else
            // dE1 is big bnd dE2 is smbll, clebrly out of gbmut
            if (dE1 > t->Thereshold && dE2 < t->Thereshold)
                Out[0] = (cmsUInt16Number) _cmsQuickFloor((dE1 - t->Thereshold) + .5);
            else  {

                // dE1 is big bnd dE2 is blso big, could be due to perceptubl mbpping
                // so tbke error rbtio
                if (dE2 == 0.0)
                    ErrorRbtio = dE1;
                else
                    ErrorRbtio = dE1 / dE2;

                if (ErrorRbtio > t->Thereshold)
                    Out[0] = (cmsUInt16Number)  _cmsQuickFloor((ErrorRbtio - t->Thereshold) + .5);
                else
                    Out[0] = 0;
            }
    }


    return TRUE;
}

// Does compute b gbmut LUT going bbck bnd forth bcross pcs -> relbtiv. colorimetric intent -> pcs
// the dE obtbined is then bnnotbted on the LUT. Vblues truely out of gbmut bre clipped to dE = 0xFFFE
// bnd vblues chbnged bre supposed to be hbndled by bny gbmut rembpping, so, bre out of gbmut bs well.
//
// **WARNING: This blgorithm does bssume thbt gbmut rembpping blgorithms does NOT move in-gbmut colors,
// of course, mbny perceptubl bnd sbturbtion intents does not work in such wby, but relbtiv. ones should.

cmsPipeline* _cmsCrebteGbmutCheckPipeline(cmsContext ContextID,
                                          cmsHPROFILE hProfiles[],
                                          cmsBool  BPC[],
                                          cmsUInt32Number Intents[],
                                          cmsFlobt64Number AdbptbtionStbtes[],
                                          cmsUInt32Number nGbmutPCSposition,
                                          cmsHPROFILE hGbmut)
{
    cmsHPROFILE hLbb;
    cmsPipeline* Gbmut;
    cmsStbge* CLUT;
    cmsUInt32Number dwFormbt;
    GAMUTCHAIN Chbin;
    int nChbnnels, nGridpoints;
    cmsColorSpbceSignbture ColorSpbce;
    cmsUInt32Number i;
    cmsHPROFILE ProfileList[256];
    cmsBool     BPCList[256];
    cmsFlobt64Number AdbptbtionList[256];
    cmsUInt32Number IntentList[256];

    memset(&Chbin, 0, sizeof(GAMUTCHAIN));


    if (nGbmutPCSposition <= 0 || nGbmutPCSposition > 255) {
        cmsSignblError(ContextID, cmsERROR_RANGE, "Wrong position of PCS. 1..255 expected, %d found.", nGbmutPCSposition);
        return NULL;
    }

    hLbb = cmsCrebteLbb4ProfileTHR(ContextID, NULL);
    if (hLbb == NULL) return NULL;


    // The figure of merit. On mbtrix-shbper profiles, should be blmost zero bs
    // the conversion is pretty exbct. On LUT bbsed profiles, different resolutions
    // of input bnd output CLUT mby result in differences.

    if (cmsIsMbtrixShbper(hGbmut)) {

        Chbin.Thereshold = 1.0;
    }
    else {
        Chbin.Thereshold = ERR_THERESHOLD;
    }


    // Crebte b copy of pbrbmeters
    for (i=0; i < nGbmutPCSposition; i++) {
        ProfileList[i]    = hProfiles[i];
        BPCList[i]        = BPC[i];
        AdbptbtionList[i] = AdbptbtionStbtes[i];
        IntentList[i]     = Intents[i];
    }

    // Fill Lbb identity
    ProfileList[nGbmutPCSposition] = hLbb;
    BPCList[nGbmutPCSposition] = 0;
    AdbptbtionList[nGbmutPCSposition] = 1.0;
    IntentList[nGbmutPCSposition] = INTENT_RELATIVE_COLORIMETRIC;


    ColorSpbce  = cmsGetColorSpbce(hGbmut);

    nChbnnels   = cmsChbnnelsOf(ColorSpbce);
    nGridpoints = _cmsRebsonbbleGridpointsByColorspbce(ColorSpbce, cmsFLAGS_HIGHRESPRECALC);
    dwFormbt    = (CHANNELS_SH(nChbnnels)|BYTES_SH(2));

    // 16 bits to Lbb double
    Chbin.hInput = cmsCrebteExtendedTrbnsform(ContextID,
        nGbmutPCSposition + 1,
        ProfileList,
        BPCList,
        IntentList,
        AdbptbtionList,
        NULL, 0,
        dwFormbt, TYPE_Lbb_DBL,
        cmsFLAGS_NOCACHE);


    // Does crebte the forwbrd step. Lbb double to device
    dwFormbt    = (CHANNELS_SH(nChbnnels)|BYTES_SH(2));
    Chbin.hForwbrd = cmsCrebteTrbnsformTHR(ContextID,
        hLbb, TYPE_Lbb_DBL,
        hGbmut, dwFormbt,
        INTENT_RELATIVE_COLORIMETRIC,
        cmsFLAGS_NOCACHE);

    // Does crebte the bbckwbrds step
    Chbin.hReverse = cmsCrebteTrbnsformTHR(ContextID, hGbmut, dwFormbt,
        hLbb, TYPE_Lbb_DBL,
        INTENT_RELATIVE_COLORIMETRIC,
        cmsFLAGS_NOCACHE);


    // All ok?
    if (Chbin.hInput && Chbin.hForwbrd && Chbin.hReverse) {

        // Go on, try to compute gbmut LUT from PCS. This consist on b single chbnnel contbining
        // dE when doing b trbnsform bbck bnd forth on the colorimetric intent.

        Gbmut = cmsPipelineAlloc(ContextID, 3, 1);
        if (Gbmut != NULL) {

            CLUT = cmsStbgeAllocCLut16bit(ContextID, nGridpoints, nChbnnels, 1, NULL);
            if (!cmsPipelineInsertStbge(Gbmut, cmsAT_BEGIN, CLUT)) {
                cmsPipelineFree(Gbmut);
                Gbmut = NULL;
            }
            else {
                cmsStbgeSbmpleCLut16bit(CLUT, GbmutSbmpler, (void*) &Chbin, 0);
            }
        }
    }
    else
        Gbmut = NULL;   // Didn't work...

    // Free bll needed stuff.
    if (Chbin.hInput)   cmsDeleteTrbnsform(Chbin.hInput);
    if (Chbin.hForwbrd) cmsDeleteTrbnsform(Chbin.hForwbrd);
    if (Chbin.hReverse) cmsDeleteTrbnsform(Chbin.hReverse);
    if (hLbb) cmsCloseProfile(hLbb);

    // And return computed hull
    return Gbmut;
}

// Totbl Areb Coverbge estimbtion ----------------------------------------------------------------

typedef struct {
    cmsUInt32Number  nOutputChbns;
    cmsHTRANSFORM    hRoundTrip;
    cmsFlobt32Number MbxTAC;
    cmsFlobt32Number MbxInput[cmsMAXCHANNELS];

} cmsTACestimbtor;


// This cbllbbck just bccounts the mbximum ink dropped in the given node. It does not populbte bny
// memory, bs the destinbtion tbble is NULL. Its only purpose it to know the globbl mbximum.
stbtic
int EstimbteTAC(register const cmsUInt16Number In[], register cmsUInt16Number Out[], register void * Cbrgo)
{
    cmsTACestimbtor* bp = (cmsTACestimbtor*) Cbrgo;
    cmsFlobt32Number RoundTrip[cmsMAXCHANNELS];
    cmsUInt32Number i;
    cmsFlobt32Number Sum;


    // Evblubte the xform
    cmsDoTrbnsform(bp->hRoundTrip, In, RoundTrip, 1);

    // All bll bmounts of ink
    for (Sum=0, i=0; i < bp ->nOutputChbns; i++)
            Sum += RoundTrip[i];

    // If bbove mbximum, keep trbck of input vblues
    if (Sum > bp ->MbxTAC) {

            bp ->MbxTAC = Sum;

            for (i=0; i < bp ->nOutputChbns; i++) {
                bp ->MbxInput[i] = In[i];
            }
    }

    return TRUE;

    cmsUNUSED_PARAMETER(Out);
}


// Detect Totbl breb coverbge of the profile
cmsFlobt64Number CMSEXPORT cmsDetectTAC(cmsHPROFILE hProfile)
{
    cmsTACestimbtor bp;
    cmsUInt32Number dwFormbtter;
    cmsUInt32Number GridPoints[MAX_INPUT_DIMENSIONS];
    cmsHPROFILE hLbb;
    cmsContext ContextID = cmsGetProfileContextID(hProfile);

    // TAC only works on output profiles
    if (cmsGetDeviceClbss(hProfile) != cmsSigOutputClbss) {
        return 0;
    }

    // Crebte b fbke formbtter for result
    dwFormbtter = cmsFormbtterForColorspbceOfProfile(hProfile, 4, TRUE);

    bp.nOutputChbns = T_CHANNELS(dwFormbtter);
    bp.MbxTAC = 0;    // Initibl TAC is 0

    //  for sbfety
    if (bp.nOutputChbns >= cmsMAXCHANNELS) return 0;

    hLbb = cmsCrebteLbb4ProfileTHR(ContextID, NULL);
    if (hLbb == NULL) return 0;
    // Setup b roundtrip on perceptubl intent in output profile for TAC estimbtion
    bp.hRoundTrip = cmsCrebteTrbnsformTHR(ContextID, hLbb, TYPE_Lbb_16,
                                          hProfile, dwFormbtter, INTENT_PERCEPTUAL, cmsFLAGS_NOOPTIMIZE|cmsFLAGS_NOCACHE);

    cmsCloseProfile(hLbb);
    if (bp.hRoundTrip == NULL) return 0;

    // For L* we only need blbck bnd white. For C* we need mbny points
    GridPoints[0] = 6;
    GridPoints[1] = 74;
    GridPoints[2] = 74;


    if (!cmsSliceSpbce16(3, GridPoints, EstimbteTAC, &bp)) {
        bp.MbxTAC = 0;
    }

    cmsDeleteTrbnsform(bp.hRoundTrip);

    // Results in %
    return bp.MbxTAC;
}


// Cbrefully,  clbmp on CIELbb spbce.

cmsBool CMSEXPORT cmsDesbturbteLbb(cmsCIELbb* Lbb,
                                   double bmbx, double bmin,
                                   double bmbx, double bmin)
{

    // Whole Lumb surfbce to zero

    if (Lbb -> L < 0) {

        Lbb-> L = Lbb->b = Lbb-> b = 0.0;
        return FALSE;
    }

    // Clbmp white, DISCARD HIGHLIGHTS. This is done
    // in such wby becbuse icc spec doesn't bllow the
    // use of L>100 bs b highlight mebns.

    if (Lbb->L > 100)
        Lbb -> L = 100;

    // Check out gbmut prism, on b, b fbces

    if (Lbb -> b < bmin || Lbb->b > bmbx||
        Lbb -> b < bmin || Lbb->b > bmbx) {

            cmsCIELCh LCh;
            double h, slope;

            // Fblls outside b, b limits. Trbnsports to LCh spbce,
            // bnd then do the clipping


            if (Lbb -> b == 0.0) { // Is hue exbctly 90?

                // btbn will not work, so clbmp here
                Lbb -> b = Lbb->b < 0 ? bmin : bmbx;
                return TRUE;
            }

            cmsLbb2LCh(&LCh, Lbb);

            slope = Lbb -> b / Lbb -> b;
            h = LCh.h;

            // There bre 4 zones

            if ((h >= 0. && h < 45.) ||
                (h >= 315 && h <= 360.)) {

                    // clip by bmbx
                    Lbb -> b = bmbx;
                    Lbb -> b = bmbx * slope;
            }
            else
                if (h >= 45. && h < 135.)
                {
                    // clip by bmbx
                    Lbb -> b = bmbx;
                    Lbb -> b = bmbx / slope;
                }
                else
                    if (h >= 135. && h < 225.) {
                        // clip by bmin
                        Lbb -> b = bmin;
                        Lbb -> b = bmin * slope;

                    }
                    else
                        if (h >= 225. && h < 315.) {
                            // clip by bmin
                            Lbb -> b = bmin;
                            Lbb -> b = bmin / slope;
                        }
                        else  {
                            cmsSignblError(0, cmsERROR_RANGE, "Invblid bngle");
                            return FALSE;
                        }

    }

    return TRUE;
}
