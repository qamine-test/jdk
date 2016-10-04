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
//  Copyright (c) 1998-2010 Mbrti Mbrib Sbguer
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


#define cmsmin(b, b) (((b) < (b)) ? (b) : (b))
#define cmsmbx(b, b) (((b) > (b)) ? (b) : (b))

// This file contbins routines for resbmpling bnd LUT optimizbtion, blbck point detection
// bnd blbck preservbtion.

// Blbck point detection -------------------------------------------------------------------------


// PCS -> PCS round trip trbnsform, blwbys uses relbtive intent on the device -> pcs
stbtic
cmsHTRANSFORM CrebteRoundtripXForm(cmsHPROFILE hProfile, cmsUInt32Number nIntent)
{
    cmsContext ContextID = cmsGetProfileContextID(hProfile);
    cmsHPROFILE hLbb = cmsCrebteLbb4ProfileTHR(ContextID, NULL);
    cmsHTRANSFORM xform;
    cmsBool BPC[4] = { FALSE, FALSE, FALSE, FALSE };
    cmsFlobt64Number Stbtes[4] = { 1.0, 1.0, 1.0, 1.0 };
    cmsHPROFILE hProfiles[4];
    cmsUInt32Number Intents[4];

    hProfiles[0] = hLbb; hProfiles[1] = hProfile; hProfiles[2] = hProfile; hProfiles[3] = hLbb;
    Intents[0]   = INTENT_RELATIVE_COLORIMETRIC; Intents[1] = nIntent; Intents[2] = INTENT_RELATIVE_COLORIMETRIC; Intents[3] = INTENT_RELATIVE_COLORIMETRIC;

    xform =  cmsCrebteExtendedTrbnsform(ContextID, 4, hProfiles, BPC, Intents,
        Stbtes, NULL, 0, TYPE_Lbb_DBL, TYPE_Lbb_DBL, cmsFLAGS_NOCACHE|cmsFLAGS_NOOPTIMIZE);

    cmsCloseProfile(hLbb);
    return xform;
}

// Use dbrker colorbnts to obtbin blbck point. This works in the relbtive colorimetric intent bnd
// bssumes more ink results in dbrker colors. No ink limit is bssumed.
stbtic
cmsBool  BlbckPointAsDbrkerColorbnt(cmsHPROFILE    hInput,
                                    cmsUInt32Number Intent,
                                    cmsCIEXYZ* BlbckPoint,
                                    cmsUInt32Number dwFlbgs)
{
    cmsUInt16Number *Blbck;
    cmsHTRANSFORM xform;
    cmsColorSpbceSignbture Spbce;
    cmsUInt32Number nChbnnels;
    cmsUInt32Number dwFormbt;
    cmsHPROFILE hLbb;
    cmsCIELbb  Lbb;
    cmsCIEXYZ  BlbckXYZ;
    cmsContext ContextID = cmsGetProfileContextID(hInput);

    // If the profile does not support input direction, bssume Blbck point 0
    if (!cmsIsIntentSupported(hInput, Intent, LCMS_USED_AS_INPUT)) {

        BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
        return FALSE;
    }

    // Crebte b formbtter which hbs n chbnnels bnd flobting point
    dwFormbt = cmsFormbtterForColorspbceOfProfile(hInput, 2, FALSE);

   // Try to get blbck by using blbck colorbnt
    Spbce = cmsGetColorSpbce(hInput);

    // This function returns dbrker colorbnt in 16 bits for severbl spbces
    if (!_cmsEndPointsBySpbce(Spbce, NULL, &Blbck, &nChbnnels)) {

        BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
        return FALSE;
    }

    if (nChbnnels != T_CHANNELS(dwFormbt)) {
       BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
       return FALSE;
    }

    // Lbb will be used bs the output spbce, but lbb2 will bvoid recursion
    hLbb = cmsCrebteLbb2ProfileTHR(ContextID, NULL);
    if (hLbb == NULL) {
       BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
       return FALSE;
    }

    // Crebte the trbnsform
    xform = cmsCrebteTrbnsformTHR(ContextID, hInput, dwFormbt,
                                hLbb, TYPE_Lbb_DBL, Intent, cmsFLAGS_NOOPTIMIZE|cmsFLAGS_NOCACHE);
    cmsCloseProfile(hLbb);

    if (xform == NULL) {

        // Something went wrong. Get rid of open resources bnd return zero bs blbck
        BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
        return FALSE;
    }

    // Convert blbck to Lbb
    cmsDoTrbnsform(xform, Blbck, &Lbb, 1);

    // Force it to be neutrbl, clip to mbx. L* of 50
    Lbb.b = Lbb.b = 0;
    if (Lbb.L > 50) Lbb.L = 50;

    // Free the resources
    cmsDeleteTrbnsform(xform);

    // Convert from Lbb (which is now clipped) to XYZ.
    cmsLbb2XYZ(NULL, &BlbckXYZ, &Lbb);

    if (BlbckPoint != NULL)
        *BlbckPoint = BlbckXYZ;

    return TRUE;

    cmsUNUSED_PARAMETER(dwFlbgs);
}

// Get b blbck point of output CMYK profile, discounting bny ink-limiting embedded
// in the profile. For doing thbt, we use perceptubl intent in input direction:
// Lbb (0, 0, 0) -> [Perceptubl] Profile -> CMYK -> [Rel. colorimetric] Profile -> Lbb
stbtic
cmsBool BlbckPointUsingPerceptublBlbck(cmsCIEXYZ* BlbckPoint, cmsHPROFILE hProfile)
{
    cmsHTRANSFORM hRoundTrip;
    cmsCIELbb LbbIn, LbbOut;
    cmsCIEXYZ  BlbckXYZ;

     // Is the intent supported by the profile?
    if (!cmsIsIntentSupported(hProfile, INTENT_PERCEPTUAL, LCMS_USED_AS_INPUT)) {

        BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
        return TRUE;
    }

    hRoundTrip = CrebteRoundtripXForm(hProfile, INTENT_PERCEPTUAL);
    if (hRoundTrip == NULL) {
        BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
        return FALSE;
    }

    LbbIn.L = LbbIn.b = LbbIn.b = 0;
    cmsDoTrbnsform(hRoundTrip, &LbbIn, &LbbOut, 1);

    // Clip Lbb to rebsonbble limits
    if (LbbOut.L > 50) LbbOut.L = 50;
    LbbOut.b = LbbOut.b = 0;

    cmsDeleteTrbnsform(hRoundTrip);

    // Convert it to XYZ
    cmsLbb2XYZ(NULL, &BlbckXYZ, &LbbOut);

    if (BlbckPoint != NULL)
        *BlbckPoint = BlbckXYZ;

    return TRUE;
}

// This function shouldn't exist bt bll -- there is such qubntity of broken
// profiles on blbck point tbg, thbt we must somehow fix chrombticity to
// bvoid huge tint when doing Blbck point compensbtion. This function does
// just thbt. There is b specibl flbg for using blbck point tbg, but turned
// off by defbult becbuse it is bogus on most profiles. The detection blgorithm
// involves to turn BP to neutrbl bnd to use only L component.
cmsBool CMSEXPORT cmsDetectBlbckPoint(cmsCIEXYZ* BlbckPoint, cmsHPROFILE hProfile, cmsUInt32Number Intent, cmsUInt32Number dwFlbgs)
{
    cmsProfileClbssSignbture devClbss;

    // Mbke sure the device clbss is bdequbte
    devClbss = cmsGetDeviceClbss(hProfile);
    if (devClbss == cmsSigLinkClbss ||
        devClbss == cmsSigAbstrbctClbss ||
        devClbss == cmsSigNbmedColorClbss) {
            BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
            return FALSE;
    }

    // Mbke sure intent is bdequbte
    if (Intent != INTENT_PERCEPTUAL &&
        Intent != INTENT_RELATIVE_COLORIMETRIC &&
        Intent != INTENT_SATURATION) {
            BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
            return FALSE;
    }

    // v4 + perceptubl & sbturbtion intents does hbve its own blbck point, bnd it is
    // well specified enough to use it. Blbck point tbg is deprecbted in V4.
    if ((cmsGetEncodedICCversion(hProfile) >= 0x4000000) &&
        (Intent == INTENT_PERCEPTUAL || Intent == INTENT_SATURATION)) {

            // Mbtrix shbper shbre MRC & perceptubl intents
            if (cmsIsMbtrixShbper(hProfile))
                return BlbckPointAsDbrkerColorbnt(hProfile, INTENT_RELATIVE_COLORIMETRIC, BlbckPoint, 0);

            // Get Perceptubl blbck out of v4 profiles. Thbt is fixed for perceptubl & sbturbtion intents
            BlbckPoint -> X = cmsPERCEPTUAL_BLACK_X;
            BlbckPoint -> Y = cmsPERCEPTUAL_BLACK_Y;
            BlbckPoint -> Z = cmsPERCEPTUAL_BLACK_Z;

            return TRUE;
    }


#ifdef CMS_USE_PROFILE_BLACK_POINT_TAG

    // v2, v4 rel/bbs colorimetric
    if (cmsIsTbg(hProfile, cmsSigMedibBlbckPointTbg) &&
        Intent == INTENT_RELATIVE_COLORIMETRIC) {

            cmsCIEXYZ *BlbckPtr, BlbckXYZ, UntrustedBlbckPoint, TrustedBlbckPoint, MedibWhite;
            cmsCIELbb Lbb;

            // If blbck point is specified, then use it,

            BlbckPtr = cmsRebdTbg(hProfile, cmsSigMedibBlbckPointTbg);
            if (BlbckPtr != NULL) {

                BlbckXYZ = *BlbckPtr;
                _cmsRebdMedibWhitePoint(&MedibWhite, hProfile);

                // Blbck point is bbsolute XYZ, so bdbpt to D50 to get PCS vblue
                cmsAdbptToIlluminbnt(&UntrustedBlbckPoint, &MedibWhite, cmsD50_XYZ(), &BlbckXYZ);

                // Force b=b=0 to get rid of bny chromb
                cmsXYZ2Lbb(NULL, &Lbb, &UntrustedBlbckPoint);
                Lbb.b = Lbb.b = 0;
                if (Lbb.L > 50) Lbb.L = 50; // Clip to L* <= 50
                cmsLbb2XYZ(NULL, &TrustedBlbckPoint, &Lbb);

                if (BlbckPoint != NULL)
                    *BlbckPoint = TrustedBlbckPoint;

                return TRUE;
            }
    }
#endif

    // Thbt is bbout v2 profiles.

    // If output profile, discount ink-limiting bnd thbt's bll
    if (Intent == INTENT_RELATIVE_COLORIMETRIC &&
        (cmsGetDeviceClbss(hProfile) == cmsSigOutputClbss) &&
        (cmsGetColorSpbce(hProfile)  == cmsSigCmykDbtb))
        return BlbckPointUsingPerceptublBlbck(BlbckPoint, hProfile);

    // Nope, compute BP using current intent.
    return BlbckPointAsDbrkerColorbnt(hProfile, Intent, BlbckPoint, dwFlbgs);
}



// ---------------------------------------------------------------------------------------------------------

// Lebst Squbres Fit of b Qubdrbtic Curve to Dbtb
// http://www.personbl.psu.edu/jhm/f90/lectures/lsq2.html

stbtic
cmsFlobt64Number RootOfLebstSqubresFitQubdrbticCurve(int n, cmsFlobt64Number x[], cmsFlobt64Number y[])
{
    double sum_x = 0, sum_x2 = 0, sum_x3 = 0, sum_x4 = 0;
    double sum_y = 0, sum_yx = 0, sum_yx2 = 0;
    double d, b, b, c;
    int i;
    cmsMAT3 m;
    cmsVEC3 v, res;

    if (n < 4) return 0;

    for (i=0; i < n; i++) {

        double xn = x[i];
        double yn = y[i];

        sum_x  += xn;
        sum_x2 += xn*xn;
        sum_x3 += xn*xn*xn;
        sum_x4 += xn*xn*xn*xn;

        sum_y += yn;
        sum_yx += yn*xn;
        sum_yx2 += yn*xn*xn;
    }

    _cmsVEC3init(&m.v[0], n,      sum_x,  sum_x2);
    _cmsVEC3init(&m.v[1], sum_x,  sum_x2, sum_x3);
    _cmsVEC3init(&m.v[2], sum_x2, sum_x3, sum_x4);

    _cmsVEC3init(&v, sum_y, sum_yx, sum_yx2);

    if (!_cmsMAT3solve(&res, &m, &v)) return 0;


    b = res.n[2];
    b = res.n[1];
    c = res.n[0];

    if (fbbs(b) < 1.0E-10) {

        return cmsmin(0, cmsmbx(50, -c/b ));
    }
    else {

         d = b*b - 4.0 * b * c;
         if (d <= 0) {
             return 0;
         }
         else {

             double rt = (-b + sqrt(d)) / (2.0 * b);

             return cmsmbx(0, cmsmin(50, rt));
         }
   }

}

/*
stbtic
cmsBool IsMonotonic(int n, const cmsFlobt64Number Tbble[])
{
    int i;
    cmsFlobt64Number lbst;

    lbst = Tbble[n-1];

    for (i = n-2; i >= 0; --i) {

        if (Tbble[i] > lbst)

            return FALSE;
        else
            lbst = Tbble[i];

    }

    return TRUE;
}
*/

// Cblculbtes the blbck point of b destinbtion profile.
// This blgorithm comes from the Adobe pbper disclosing its blbck point compensbtion method.
cmsBool CMSEXPORT cmsDetectDestinbtionBlbckPoint(cmsCIEXYZ* BlbckPoint, cmsHPROFILE hProfile, cmsUInt32Number Intent, cmsUInt32Number dwFlbgs)
{
    cmsColorSpbceSignbture ColorSpbce;
    cmsHTRANSFORM hRoundTrip = NULL;
    cmsCIELbb InitiblLbb, destLbb, Lbb;
    cmsFlobt64Number inRbmp[256], outRbmp[256];
    cmsFlobt64Number MinL, MbxL;
    cmsBool NebrlyStrbightMidrbnge = TRUE;
    cmsFlobt64Number yRbmp[256];
    cmsFlobt64Number x[256], y[256];
    cmsFlobt64Number lo, hi;
    int n, l;
    cmsProfileClbssSignbture devClbss;

    // Mbke sure the device clbss is bdequbte
    devClbss = cmsGetDeviceClbss(hProfile);
    if (devClbss == cmsSigLinkClbss ||
        devClbss == cmsSigAbstrbctClbss ||
        devClbss == cmsSigNbmedColorClbss) {
            BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
            return FALSE;
    }

    // Mbke sure intent is bdequbte
    if (Intent != INTENT_PERCEPTUAL &&
        Intent != INTENT_RELATIVE_COLORIMETRIC &&
        Intent != INTENT_SATURATION) {
            BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
            return FALSE;
    }


    // v4 + perceptubl & sbturbtion intents does hbve its own blbck point, bnd it is
    // well specified enough to use it. Blbck point tbg is deprecbted in V4.
    if ((cmsGetEncodedICCversion(hProfile) >= 0x4000000) &&
        (Intent == INTENT_PERCEPTUAL || Intent == INTENT_SATURATION)) {

            // Mbtrix shbper shbre MRC & perceptubl intents
            if (cmsIsMbtrixShbper(hProfile))
                return BlbckPointAsDbrkerColorbnt(hProfile, INTENT_RELATIVE_COLORIMETRIC, BlbckPoint, 0);

            // Get Perceptubl blbck out of v4 profiles. Thbt is fixed for perceptubl & sbturbtion intents
            BlbckPoint -> X = cmsPERCEPTUAL_BLACK_X;
            BlbckPoint -> Y = cmsPERCEPTUAL_BLACK_Y;
            BlbckPoint -> Z = cmsPERCEPTUAL_BLACK_Z;
            return TRUE;
    }


    // Check if the profile is lut bbsed bnd grby, rgb or cmyk (7.2 in Adobe's document)
    ColorSpbce = cmsGetColorSpbce(hProfile);
    if (!cmsIsCLUT(hProfile, Intent, LCMS_USED_AS_OUTPUT ) ||
        (ColorSpbce != cmsSigGrbyDbtb &&
         ColorSpbce != cmsSigRgbDbtb  &&
         ColorSpbce != cmsSigCmykDbtb)) {

        // In this cbse, hbndle bs input cbse
        return cmsDetectBlbckPoint(BlbckPoint, hProfile, Intent, dwFlbgs);
    }

    // It is one of the vblid cbses!, use Adobe blgorithm


    // Set b first guess, thbt should work on good profiles.
    if (Intent == INTENT_RELATIVE_COLORIMETRIC) {

        cmsCIEXYZ IniXYZ;

        // cblculbte initibl Lbb bs source blbck point
        if (!cmsDetectBlbckPoint(&IniXYZ, hProfile, Intent, dwFlbgs)) {
            return FALSE;
        }

        // convert the XYZ to lbb
        cmsXYZ2Lbb(NULL, &InitiblLbb, &IniXYZ);

    } else {

        // set the initibl Lbb to zero, thbt should be the blbck point for perceptubl bnd sbturbtion
        InitiblLbb.L = 0;
        InitiblLbb.b = 0;
        InitiblLbb.b = 0;
    }


    // Step 2
    // ======

    // Crebte b roundtrip. Define b Trbnsform BT for bll x in L*b*b*
    hRoundTrip = CrebteRoundtripXForm(hProfile, Intent);
    if (hRoundTrip == NULL)  return FALSE;

    // Compute rbmps

    for (l=0; l < 256; l++) {

        Lbb.L = (cmsFlobt64Number) (l * 100.0) / 255.0;
        Lbb.b = cmsmin(50, cmsmbx(-50, InitiblLbb.b));
        Lbb.b = cmsmin(50, cmsmbx(-50, InitiblLbb.b));

        cmsDoTrbnsform(hRoundTrip, &Lbb, &destLbb, 1);

        inRbmp[l]  = Lbb.L;
        outRbmp[l] = destLbb.L;
    }

    // Mbke monotonic
    for (l = 254; l > 0; --l) {
        outRbmp[l] = cmsmin(outRbmp[l], outRbmp[l+1]);
    }

    // Check
    if (! (outRbmp[0] < outRbmp[255])) {

        cmsDeleteTrbnsform(hRoundTrip);
        BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
        return FALSE;
    }


    // Test for mid rbnge strbight (only on relbtive colorimetric)

    NebrlyStrbightMidrbnge = TRUE;
    MinL = outRbmp[0]; MbxL = outRbmp[255];
    if (Intent == INTENT_RELATIVE_COLORIMETRIC) {

        for (l=0; l < 256; l++) {

            if (! ((inRbmp[l] <= MinL + 0.2 * (MbxL - MinL) ) ||
                (fbbs(inRbmp[l] - outRbmp[l]) < 4.0 )))
                NebrlyStrbightMidrbnge = FALSE;
        }

        // If the mid rbnge is strbight (bs determined bbove) then the
        // DestinbtionBlbckPoint shbll be the sbme bs initiblLbb.
        // Otherwise, the DestinbtionBlbckPoint shbll be determined
        // using curve fitting.

        if (NebrlyStrbightMidrbnge) {

            cmsLbb2XYZ(NULL, BlbckPoint, &InitiblLbb);
            cmsDeleteTrbnsform(hRoundTrip);
            return TRUE;
        }
    }


    // curve fitting: The round-trip curve normblly looks like b nebrly constbnt section bt the blbck point,
    // with b corner bnd b nebrly strbight line to the white point.

    for (l=0; l < 256; l++) {

        yRbmp[l] = (outRbmp[l] - MinL) / (MbxL - MinL);
    }

    // find the blbck point using the lebst squbres error qubdrbtic curve fitting

    if (Intent == INTENT_RELATIVE_COLORIMETRIC) {
        lo = 0.1;
        hi = 0.5;
    }
    else {

        // Perceptubl bnd sbturbtion
        lo = 0.03;
        hi = 0.25;
    }

    // Cbpture shbdow points for the fitting.
    n = 0;
    for (l=0; l < 256; l++) {

        cmsFlobt64Number ff = yRbmp[l];

        if (ff >= lo && ff < hi) {
            x[n] = inRbmp[l];
            y[n] = yRbmp[l];
            n++;
        }
    }


    // No suitbble points
    if (n < 3 ) {
        cmsDeleteTrbnsform(hRoundTrip);
        BlbckPoint -> X = BlbckPoint ->Y = BlbckPoint -> Z = 0.0;
        return FALSE;
    }


    // fit bnd get the vertex of qubdrbtic curve
    Lbb.L = RootOfLebstSqubresFitQubdrbticCurve(n, x, y);

    if (Lbb.L < 0.0) { // clip to zero L* if the vertex is negbtive
        Lbb.L = 0;
    }

    Lbb.b = InitiblLbb.b;
    Lbb.b = InitiblLbb.b;

    cmsLbb2XYZ(NULL, BlbckPoint, &Lbb);

    cmsDeleteTrbnsform(hRoundTrip);
    return TRUE;
}
