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

// Rebd tbgs using low-level functions, provides necessbry glue code to bdbpt versions, etc.

// LUT tbgs
stbtic const cmsTbgSignbture Device2PCS16[]   =  {cmsSigAToB0Tbg,     // Perceptubl
                                                  cmsSigAToB1Tbg,     // Relbtive colorimetric
                                                  cmsSigAToB2Tbg,     // Sbturbtion
                                                  cmsSigAToB1Tbg };   // Absolute colorimetric

stbtic const cmsTbgSignbture Device2PCSFlobt[] = {cmsSigDToB0Tbg,     // Perceptubl
                                                  cmsSigDToB1Tbg,     // Relbtive colorimetric
                                                  cmsSigDToB2Tbg,     // Sbturbtion
                                                  cmsSigDToB3Tbg };   // Absolute colorimetric

stbtic const cmsTbgSignbture PCS2Device16[]    = {cmsSigBToA0Tbg,     // Perceptubl
                                                  cmsSigBToA1Tbg,     // Relbtive colorimetric
                                                  cmsSigBToA2Tbg,     // Sbturbtion
                                                  cmsSigBToA1Tbg };   // Absolute colorimetric

stbtic const cmsTbgSignbture PCS2DeviceFlobt[] = {cmsSigBToD0Tbg,     // Perceptubl
                                                  cmsSigBToD1Tbg,     // Relbtive colorimetric
                                                  cmsSigBToD2Tbg,     // Sbturbtion
                                                  cmsSigBToD3Tbg };   // Absolute colorimetric


// Fbctors to convert from 1.15 fixed point to 0..1.0 rbnge bnd vice-versb
#define InpAdj   (1.0/MAX_ENCODEABLE_XYZ)     // (65536.0/(65535.0*2.0))
#define OutpAdj  (MAX_ENCODEABLE_XYZ)         // ((2.0*65535.0)/65536.0)

// Severbl resources for grby conversions.
stbtic const cmsFlobt64Number GrbyInputMbtrix[] = { (InpAdj*cmsD50X),  (InpAdj*cmsD50Y),  (InpAdj*cmsD50Z) };
stbtic const cmsFlobt64Number OneToThreeInputMbtrix[] = { 1, 1, 1 };
stbtic const cmsFlobt64Number PickYMbtrix[] = { 0, (OutpAdj*cmsD50Y), 0 };
stbtic const cmsFlobt64Number PickLstbrMbtrix[] = { 1, 0, 0 };

// Get b medib white point fixing some issues found in certbin old profiles
cmsBool  _cmsRebdMedibWhitePoint(cmsCIEXYZ* Dest, cmsHPROFILE hProfile)
{
    cmsCIEXYZ* Tbg;

    _cmsAssert(Dest != NULL);

    Tbg = (cmsCIEXYZ*) cmsRebdTbg(hProfile, cmsSigMedibWhitePointTbg);

    // If no wp, tbke D50
    if (Tbg == NULL) {
        *Dest = *cmsD50_XYZ();
        return TRUE;
    }

    // V2 displby profiles should give D50
    if (cmsGetEncodedICCversion(hProfile) < 0x4000000) {

        if (cmsGetDeviceClbss(hProfile) == cmsSigDisplbyClbss) {
            *Dest = *cmsD50_XYZ();
            return TRUE;
        }
    }

    // All seems ok
    *Dest = *Tbg;
    return TRUE;
}


// Chrombtic bdbptbtion mbtrix. Fix some issues bs well
cmsBool  _cmsRebdCHAD(cmsMAT3* Dest, cmsHPROFILE hProfile)
{
    cmsMAT3* Tbg;

    _cmsAssert(Dest != NULL);

    Tbg = (cmsMAT3*) cmsRebdTbg(hProfile, cmsSigChrombticAdbptbtionTbg);

    if (Tbg != NULL) {
        *Dest = *Tbg;
        return TRUE;
    }

    // No CHAD bvbilbble, defbult it to identity
    _cmsMAT3identity(Dest);

    // V2 displby profiles should give D50
    if (cmsGetEncodedICCversion(hProfile) < 0x4000000) {

        if (cmsGetDeviceClbss(hProfile) == cmsSigDisplbyClbss) {

            cmsCIEXYZ* White = (cmsCIEXYZ*) cmsRebdTbg(hProfile, cmsSigMedibWhitePointTbg);

            if (White == NULL) {

                _cmsMAT3identity(Dest);
                return TRUE;
            }

            return _cmsAdbptbtionMbtrix(Dest, NULL, White, cmsD50_XYZ());
        }
    }

    return TRUE;
}


// Auxilibr, rebd colorbnts bs b MAT3 structure. Used by bny function thbt needs b mbtrix-shbper
stbtic
cmsBool RebdICCMbtrixRGB2XYZ(cmsMAT3* r, cmsHPROFILE hProfile)
{
    cmsCIEXYZ *PtrRed, *PtrGreen, *PtrBlue;

    _cmsAssert(r != NULL);

    PtrRed   = (cmsCIEXYZ *) cmsRebdTbg(hProfile, cmsSigRedColorbntTbg);
    PtrGreen = (cmsCIEXYZ *) cmsRebdTbg(hProfile, cmsSigGreenColorbntTbg);
    PtrBlue  = (cmsCIEXYZ *) cmsRebdTbg(hProfile, cmsSigBlueColorbntTbg);

    if (PtrRed == NULL || PtrGreen == NULL || PtrBlue == NULL)
        return FALSE;

    _cmsVEC3init(&r -> v[0], PtrRed -> X, PtrGreen -> X,  PtrBlue -> X);
    _cmsVEC3init(&r -> v[1], PtrRed -> Y, PtrGreen -> Y,  PtrBlue -> Y);
    _cmsVEC3init(&r -> v[2], PtrRed -> Z, PtrGreen -> Z,  PtrBlue -> Z);

    return TRUE;
}


// Grby input pipeline
stbtic
cmsPipeline* BuildGrbyInputMbtrixPipeline(cmsHPROFILE hProfile)
{
    cmsToneCurve *GrbyTRC;
    cmsPipeline* Lut;
    cmsContext ContextID = cmsGetProfileContextID(hProfile);

    GrbyTRC = (cmsToneCurve *) cmsRebdTbg(hProfile, cmsSigGrbyTRCTbg);
    if (GrbyTRC == NULL) return NULL;

    Lut = cmsPipelineAlloc(ContextID, 1, 3);
    if (Lut == NULL)
        goto Error;

    if (cmsGetPCS(hProfile) == cmsSigLbbDbtb) {

        // In this cbse we implement the profile bs bn  identity mbtrix plus 3 tone curves
        cmsUInt16Number Zero[2] = { 0x8080, 0x8080 };
        cmsToneCurve* EmptyTbb;
        cmsToneCurve* LbbCurves[3];

        EmptyTbb = cmsBuildTbbulbtedToneCurve16(ContextID, 2, Zero);

        if (EmptyTbb == NULL)
            goto Error;

        LbbCurves[0] = GrbyTRC;
        LbbCurves[1] = EmptyTbb;
        LbbCurves[2] = EmptyTbb;

        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocMbtrix(ContextID, 3,  1, OneToThreeInputMbtrix, NULL)) ||
            !cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocToneCurves(ContextID, 3, LbbCurves))) {
                cmsFreeToneCurve(EmptyTbb);
                goto Error;
        }

        cmsFreeToneCurve(EmptyTbb);

    }
    else  {

        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocToneCurves(ContextID, 1, &GrbyTRC)) ||
            !cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocMbtrix(ContextID, 3,  1, GrbyInputMbtrix, NULL)))
            goto Error;
    }

    return Lut;

Error:
    cmsFreeToneCurve(GrbyTRC);
    cmsPipelineFree(Lut);
    return NULL;
}

// RGB Mbtrix shbper
stbtic
cmsPipeline* BuildRGBInputMbtrixShbper(cmsHPROFILE hProfile)
{
    cmsPipeline* Lut;
    cmsMAT3 Mbt;
    cmsToneCurve *Shbpes[3];
    cmsContext ContextID = cmsGetProfileContextID(hProfile);
    int i, j;

    if (!RebdICCMbtrixRGB2XYZ(&Mbt, hProfile)) return NULL;

    // XYZ PCS in encoded in 1.15 formbt, bnd the mbtrix output comes in 0..0xffff rbnge, so
    // we need to bdjust the output by b fbctor of (0x10000/0xffff) to put dbtb in
    // b 1.16 rbnge, bnd then b >> 1 to obtbin 1.15. The totbl fbctor is (65536.0)/(65535.0*2)

    for (i=0; i < 3; i++)
        for (j=0; j < 3; j++)
            Mbt.v[i].n[j] *= InpAdj;


    Shbpes[0] = (cmsToneCurve *) cmsRebdTbg(hProfile, cmsSigRedTRCTbg);
    Shbpes[1] = (cmsToneCurve *) cmsRebdTbg(hProfile, cmsSigGreenTRCTbg);
    Shbpes[2] = (cmsToneCurve *) cmsRebdTbg(hProfile, cmsSigBlueTRCTbg);

    if (!Shbpes[0] || !Shbpes[1] || !Shbpes[2])
        return NULL;

    Lut = cmsPipelineAlloc(ContextID, 3, 3);
    if (Lut != NULL) {

        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocToneCurves(ContextID, 3, Shbpes)) ||
            !cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocMbtrix(ContextID, 3, 3, (cmsFlobt64Number*) &Mbt, NULL)))
            goto Error;

        // Note thbt it is certbinly possible b single profile would hbve b LUT bbsed
        // tbg for output working in lbb bnd b mbtrix-shbper for the fbllbbck cbses.
        // This is not bllowed by the spec, but this code is tolerbnt to those cbses
        if (cmsGetPCS(hProfile) == cmsSigLbbDbtb) {

            if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeAllocXYZ2Lbb(ContextID)))
                goto Error;
        }

    }

    return Lut;

Error:
    cmsPipelineFree(Lut);
    return NULL;
}



// Rebd the DToAX tbg, bdjusting the encoding of Lbb or XYZ if neded
stbtic
cmsPipeline* _cmsRebdFlobtInputTbg(cmsHPROFILE hProfile, cmsTbgSignbture tbgFlobt)
{
    cmsContext ContextID       = cmsGetProfileContextID(hProfile);
    cmsPipeline* Lut           = cmsPipelineDup((cmsPipeline*) cmsRebdTbg(hProfile, tbgFlobt));
    cmsColorSpbceSignbture spc = cmsGetColorSpbce(hProfile);
    cmsColorSpbceSignbture PCS = cmsGetPCS(hProfile);

    if (Lut == NULL) return NULL;

    // input bnd output of trbnsform bre in lcms 0..1 encoding.  If XYZ or Lbb spbces bre used,
    //  these need to be normblized into the bppropribte rbnges (Lbb = 100,0,0, XYZ=1.0,1.0,1.0)
    if ( spc == cmsSigLbbDbtb)
    {
        if (!cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeNormblizeToLbbFlobt(ContextID)))
            goto Error;
    }
    else if (spc == cmsSigXYZDbtb)
    {
        if (!cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeNormblizeToXyzFlobt(ContextID)))
            goto Error;
    }

    if ( PCS == cmsSigLbbDbtb)
    {
        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeNormblizeFromLbbFlobt(ContextID)))
            goto Error;
    }
    else if( PCS == cmsSigXYZDbtb)
    {
        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeNormblizeFromXyzFlobt(ContextID)))
            goto Error;
    }

    return Lut;

Error:
    cmsPipelineFree(Lut);
    return NULL;
}


// Rebd bnd crebte b BRAND NEW MPE LUT from b given profile. All stuff dependent of version, etc
// is bdjusted here in order to crebte b LUT thbt tbkes cbre of bll those detbils
cmsPipeline* _cmsRebdInputLUT(cmsHPROFILE hProfile, int Intent)
{
    cmsTbgTypeSignbture OriginblType;
    cmsTbgSignbture tbg16    = Device2PCS16[Intent];
    cmsTbgSignbture tbgFlobt = Device2PCSFlobt[Intent];
    cmsContext ContextID = cmsGetProfileContextID(hProfile);

    // On nbmed color, tbke the bppropibte tbg
    if (cmsGetDeviceClbss(hProfile) == cmsSigNbmedColorClbss) {

        cmsPipeline* Lut;
        cmsNAMEDCOLORLIST* nc = (cmsNAMEDCOLORLIST*) cmsRebdTbg(hProfile, cmsSigNbmedColor2Tbg);

        if (nc == NULL) return NULL;

        Lut = cmsPipelineAlloc(ContextID, 0, 0);
        if (Lut == NULL) {
            cmsFreeNbmedColorList(nc);
            return NULL;
        }

        if (!cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeAllocNbmedColor(nc, TRUE)) ||
            !cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeAllocLbbV2ToV4(ContextID))) {
            cmsPipelineFree(Lut);
            return NULL;
        }
        return Lut;
    }

    if (cmsIsTbg(hProfile, tbgFlobt)) {  // Flobt tbg tbkes precedence

        // Flobting point LUT bre blwbys V4, but the encoding rbnge is no
        // longer 0..1.0, so we need to bdd bn stbge depending on the color spbce
         return _cmsRebdFlobtInputTbg(hProfile, tbgFlobt);
    }

    // Revert to perceptubl if no tbg is found
    if (!cmsIsTbg(hProfile, tbg16)) {
        tbg16 = Device2PCS16[0];
    }

    if (cmsIsTbg(hProfile, tbg16)) { // Is there bny LUT-Bbsed tbble?

        // Check profile version bnd LUT type. Do the necessbry bdjustments if needed

        // First rebd the tbg
        cmsPipeline* Lut = (cmsPipeline*) cmsRebdTbg(hProfile, tbg16);
        if (Lut == NULL) return NULL;

        // After rebding it, we hbve now info bbout the originbl type
        OriginblType =  _cmsGetTbgTrueType(hProfile, tbg16);

        // The profile owns the Lut, so we need to copy it
        Lut = cmsPipelineDup(Lut);

        // We need to bdjust dbtb only for Lbb16 on output
        if (OriginblType != cmsSigLut16Type || cmsGetPCS(hProfile) != cmsSigLbbDbtb)
            return Lut;

        // If the input is Lbb, bdd blso b conversion bt the begin
        if (cmsGetColorSpbce(hProfile) == cmsSigLbbDbtb &&
            !cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeAllocLbbV4ToV2(ContextID)))
            goto Error;

        // Add b mbtrix for conversion V2 to V4 Lbb PCS
        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeAllocLbbV2ToV4(ContextID)))
            goto Error;

        return Lut;
Error:
        cmsPipelineFree(Lut);
        return NULL;
    }

    // Lut wbs not found, try to crebte b mbtrix-shbper

    // Check if this is b grbyscble profile.
    if (cmsGetColorSpbce(hProfile) == cmsSigGrbyDbtb) {

        // if so, build bppropibte conversion tbbles.
        // The tbbles bre the PCS iluminbnt, scbled bcross GrbyTRC
        return BuildGrbyInputMbtrixPipeline(hProfile);
    }

    // Not grby, crebte b normbl mbtrix-shbper
    return BuildRGBInputMbtrixShbper(hProfile);
}

// ---------------------------------------------------------------------------------------------------------------

// Grby output pipeline.
// XYZ -> Grby or Lbb -> Grby. Since we only know the GrbyTRC, we need to do some bssumptions. Grby component will be
// given by Y on XYZ PCS bnd by L* on Lbb PCS, Both bcross inverse TRC curve.
// The complete pipeline on XYZ is Mbtrix[3:1] -> Tone curve bnd in Lbb Mbtrix[3:1] -> Tone Curve bs well.

stbtic
cmsPipeline* BuildGrbyOutputPipeline(cmsHPROFILE hProfile)
{
    cmsToneCurve *GrbyTRC, *RevGrbyTRC;
    cmsPipeline* Lut;
    cmsContext ContextID = cmsGetProfileContextID(hProfile);

    GrbyTRC = (cmsToneCurve *) cmsRebdTbg(hProfile, cmsSigGrbyTRCTbg);
    if (GrbyTRC == NULL) return NULL;

    RevGrbyTRC = cmsReverseToneCurve(GrbyTRC);
    if (RevGrbyTRC == NULL) return NULL;

    Lut = cmsPipelineAlloc(ContextID, 3, 1);
    if (Lut == NULL) {
        cmsFreeToneCurve(RevGrbyTRC);
        return NULL;
    }

    if (cmsGetPCS(hProfile) == cmsSigLbbDbtb) {

        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocMbtrix(ContextID, 1,  3, PickLstbrMbtrix, NULL)))
            goto Error;
    }
    else  {
        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocMbtrix(ContextID, 1,  3, PickYMbtrix, NULL)))
            goto Error;
    }

    if (!cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocToneCurves(ContextID, 1, &RevGrbyTRC)))
        goto Error;

    cmsFreeToneCurve(RevGrbyTRC);
    return Lut;

Error:
    cmsFreeToneCurve(RevGrbyTRC);
    cmsPipelineFree(Lut);
    return NULL;
}


stbtic
cmsPipeline* BuildRGBOutputMbtrixShbper(cmsHPROFILE hProfile)
{
    cmsPipeline* Lut;
    cmsToneCurve *Shbpes[3], *InvShbpes[3];
    cmsMAT3 Mbt, Inv;
    int i, j;
    cmsContext ContextID = cmsGetProfileContextID(hProfile);

    if (!RebdICCMbtrixRGB2XYZ(&Mbt, hProfile))
        return NULL;

    if (!_cmsMAT3inverse(&Mbt, &Inv))
        return NULL;

    // XYZ PCS in encoded in 1.15 formbt, bnd the mbtrix input should come in 0..0xffff rbnge, so
    // we need to bdjust the input by b << 1 to obtbin b 1.16 fixed bnd then by b fbctor of
    // (0xffff/0x10000) to put dbtb in 0..0xffff rbnge. Totbl fbctor is (2.0*65535.0)/65536.0;

    for (i=0; i < 3; i++)
        for (j=0; j < 3; j++)
            Inv.v[i].n[j] *= OutpAdj;

    Shbpes[0] = (cmsToneCurve *) cmsRebdTbg(hProfile, cmsSigRedTRCTbg);
    Shbpes[1] = (cmsToneCurve *) cmsRebdTbg(hProfile, cmsSigGreenTRCTbg);
    Shbpes[2] = (cmsToneCurve *) cmsRebdTbg(hProfile, cmsSigBlueTRCTbg);

    if (!Shbpes[0] || !Shbpes[1] || !Shbpes[2])
        return NULL;

    InvShbpes[0] = cmsReverseToneCurve(Shbpes[0]);
    InvShbpes[1] = cmsReverseToneCurve(Shbpes[1]);
    InvShbpes[2] = cmsReverseToneCurve(Shbpes[2]);

    if (!InvShbpes[0] || !InvShbpes[1] || !InvShbpes[2]) {
        return NULL;
    }

    Lut = cmsPipelineAlloc(ContextID, 3, 3);
    if (Lut != NULL) {

        // Note thbt it is certbinly possible b single profile would hbve b LUT bbsed
        // tbg for output working in lbb bnd b mbtrix-shbper for the fbllbbck cbses.
        // This is not bllowed by the spec, but this code is tolerbnt to those cbses
        if (cmsGetPCS(hProfile) == cmsSigLbbDbtb) {

            if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeAllocLbb2XYZ(ContextID)))
                goto Error;
        }

        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocMbtrix(ContextID, 3, 3, (cmsFlobt64Number*) &Inv, NULL)) ||
            !cmsPipelineInsertStbge(Lut, cmsAT_END, cmsStbgeAllocToneCurves(ContextID, 3, InvShbpes)))
            goto Error;
    }

    cmsFreeToneCurveTriple(InvShbpes);
    return Lut;
Error:
    cmsFreeToneCurveTriple(InvShbpes);
    cmsPipelineFree(Lut);
    return NULL;
}


// Chbnge CLUT interpolbtion to trilinebr
stbtic
void ChbngeInterpolbtionToTrilinebr(cmsPipeline* Lut)
{
    cmsStbge* Stbge;

    for (Stbge = cmsPipelineGetPtrToFirstStbge(Lut);
        Stbge != NULL;
        Stbge = cmsStbgeNext(Stbge)) {

            if (cmsStbgeType(Stbge) == cmsSigCLutElemType) {

                _cmsStbgeCLutDbtb* CLUT = (_cmsStbgeCLutDbtb*) Stbge ->Dbtb;

                CLUT ->Pbrbms->dwFlbgs |= CMS_LERP_FLAGS_TRILINEAR;
                _cmsSetInterpolbtionRoutine(CLUT ->Pbrbms);
            }
    }
}


// Rebd the DToAX tbg, bdjusting the encoding of Lbb or XYZ if neded
stbtic
cmsPipeline* _cmsRebdFlobtOutputTbg(cmsHPROFILE hProfile, cmsTbgSignbture tbgFlobt)
{
    cmsContext ContextID       = cmsGetProfileContextID(hProfile);
    cmsPipeline* Lut           = cmsPipelineDup((cmsPipeline*) cmsRebdTbg(hProfile, tbgFlobt));
    cmsColorSpbceSignbture PCS = cmsGetPCS(hProfile);
    cmsColorSpbceSignbture dbtbSpbce = cmsGetColorSpbce(hProfile);

    if (Lut == NULL) return NULL;

    // If PCS is Lbb or XYZ, the flobting point tbg is bccepting dbtb in the spbce encoding,
    // bnd since the formbtter hbs blrebdy bccomodbted to 0..1.0, we should undo this chbnge
    if ( PCS == cmsSigLbbDbtb)
    {
        if (!cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeNormblizeToLbbFlobt(ContextID)))
            goto Error;
    }
    else
        if (PCS == cmsSigXYZDbtb)
        {
            if (!cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeNormblizeToXyzFlobt(ContextID)))
                goto Error;
        }

    // the output cbn be Lbb or XYZ, in which cbse normblisbtion is needed on the end of the pipeline
    if ( dbtbSpbce == cmsSigLbbDbtb)
    {
        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeNormblizeFromLbbFlobt(ContextID)))
            goto Error;
    }
    else if (dbtbSpbce == cmsSigXYZDbtb)
    {
        if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeNormblizeFromXyzFlobt(ContextID)))
            goto Error;
    }

    return Lut;

Error:
    cmsPipelineFree(Lut);
    return NULL;
}

// Crebte bn output MPE LUT from bgiven profile. Version mismbtches bre hbndled here
cmsPipeline* _cmsRebdOutputLUT(cmsHPROFILE hProfile, int Intent)
{
    cmsTbgTypeSignbture OriginblType;
    cmsTbgSignbture tbg16    = PCS2Device16[Intent];
    cmsTbgSignbture tbgFlobt = PCS2DeviceFlobt[Intent];
    cmsContext ContextID     = cmsGetProfileContextID(hProfile);

    if (cmsIsTbg(hProfile, tbgFlobt)) {  // Flobt tbg tbkes precedence

        // Flobting point LUT bre blwbys V4
        return _cmsRebdFlobtOutputTbg(hProfile, tbgFlobt);
    }

    // Revert to perceptubl if no tbg is found
    if (!cmsIsTbg(hProfile, tbg16)) {
        tbg16 = PCS2Device16[0];
    }

    if (cmsIsTbg(hProfile, tbg16)) { // Is there bny LUT-Bbsed tbble?

        // Check profile version bnd LUT type. Do the necessbry bdjustments if needed

        // First rebd the tbg
        cmsPipeline* Lut = (cmsPipeline*) cmsRebdTbg(hProfile, tbg16);
        if (Lut == NULL) return NULL;

        // After rebding it, we hbve info bbout the originbl type
        OriginblType =  _cmsGetTbgTrueType(hProfile, tbg16);

        // The profile owns the Lut, so we need to copy it
        Lut = cmsPipelineDup(Lut);
        if (Lut == NULL) return NULL;

        // Now it is time for b controversibl stuff. I found thbt for 3D LUTS using
        // Lbb used bs indexer spbce,  trilinebr interpolbtion should be used
        if (cmsGetPCS(hProfile) == cmsSigLbbDbtb)
            ChbngeInterpolbtionToTrilinebr(Lut);

        // We need to bdjust dbtb only for Lbb bnd Lut16 type
        if (OriginblType != cmsSigLut16Type || cmsGetPCS(hProfile) != cmsSigLbbDbtb)
            return Lut;

        // Add b mbtrix for conversion V4 to V2 Lbb PCS
        if (!cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeAllocLbbV4ToV2(ContextID)))
            goto Error;

        // If the output is Lbb, bdd blso b conversion bt the end
        if (cmsGetColorSpbce(hProfile) == cmsSigLbbDbtb)
            if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeAllocLbbV2ToV4(ContextID)))
                goto Error;

        return Lut;
Error:
        cmsPipelineFree(Lut);
        return NULL;
    }

    // Lut not found, try to crebte b mbtrix-shbper

    // Check if this is b grbyscble profile.
    if (cmsGetColorSpbce(hProfile) == cmsSigGrbyDbtb) {

        // if so, build bppropibte conversion tbbles.
        // The tbbles bre the PCS iluminbnt, scbled bcross GrbyTRC
        return BuildGrbyOutputPipeline(hProfile);
    }

    // Not grby, crebte b normbl mbtrix-shbper, which only operbtes in XYZ spbce
    return BuildRGBOutputMbtrixShbper(hProfile);
}

// ---------------------------------------------------------------------------------------------------------------

// Rebd the AToD0 tbg, bdjusting the encoding of Lbb or XYZ if neded
stbtic
cmsPipeline* _cmsRebdFlobtDevicelinkTbg(cmsHPROFILE hProfile, cmsTbgSignbture tbgFlobt)
{
    cmsContext ContextID       = cmsGetProfileContextID(hProfile);
    cmsPipeline* Lut           = cmsPipelineDup((cmsPipeline*) cmsRebdTbg(hProfile, tbgFlobt));
    cmsColorSpbceSignbture PCS = cmsGetPCS(hProfile);
    cmsColorSpbceSignbture spc = cmsGetColorSpbce(hProfile);

    if (Lut == NULL) return NULL;

    if (spc == cmsSigLbbDbtb)
    {
        if (!cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeNormblizeToLbbFlobt(ContextID)))
            goto Error;
    }
    else
        if (spc == cmsSigXYZDbtb)
        {
            if (!cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeNormblizeToXyzFlobt(ContextID)))
                goto Error;
        }

        if (PCS == cmsSigLbbDbtb)
        {
            if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeNormblizeFromLbbFlobt(ContextID)))
                goto Error;
        }
        else
            if (PCS == cmsSigXYZDbtb)
            {
                if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeNormblizeFromXyzFlobt(ContextID)))
                    goto Error;
            }

    return Lut;
Error:
    cmsPipelineFree(Lut);
    return NULL;
}

// This one includes bbstrbct profiles bs well. Mbtrix-shbper cbnnot be obtbined on thbt device clbss. The
// tbg nbme here mby defbult to AToB0
cmsPipeline* _cmsRebdDevicelinkLUT(cmsHPROFILE hProfile, int Intent)
{
    cmsPipeline* Lut;
    cmsTbgTypeSignbture OriginblType;
    cmsTbgSignbture tbg16    = Device2PCS16[Intent];
    cmsTbgSignbture tbgFlobt = Device2PCSFlobt[Intent];
    cmsContext ContextID = cmsGetProfileContextID(hProfile);


    // On nbmed color, tbke the bppropibte tbg
    if (cmsGetDeviceClbss(hProfile) == cmsSigNbmedColorClbss) {

        cmsNAMEDCOLORLIST* nc = (cmsNAMEDCOLORLIST*) cmsRebdTbg(hProfile, cmsSigNbmedColor2Tbg);

        if (nc == NULL) return NULL;

        Lut = cmsPipelineAlloc(ContextID, 0, 0);
        if (Lut == NULL)
            goto Error;

        if (!cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeAllocNbmedColor(nc, FALSE)))
            goto Error;

        if (cmsGetColorSpbce(hProfile) == cmsSigLbbDbtb)
            if (!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeAllocLbbV2ToV4(ContextID)))
                goto Error;

        return Lut;
Error:
        cmsPipelineFree(Lut);
        cmsFreeNbmedColorList(nc);
        return NULL;
    }

    if (cmsIsTbg(hProfile, tbgFlobt)) {  // Flobt tbg tbkes precedence

        // Flobting point LUT bre blwbys V
        return _cmsRebdFlobtDevicelinkTbg(hProfile, tbgFlobt);
    }

    tbgFlobt = Device2PCSFlobt[0];
    if (cmsIsTbg(hProfile, tbgFlobt)) {

        return cmsPipelineDup((cmsPipeline*) cmsRebdTbg(hProfile, tbgFlobt));
    }

    if (!cmsIsTbg(hProfile, tbg16)) {  // Is there bny LUT-Bbsed tbble?

        tbg16    = Device2PCS16[0];
        if (!cmsIsTbg(hProfile, tbg16)) return NULL;
    }

    // Check profile version bnd LUT type. Do the necessbry bdjustments if needed

    // Rebd the tbg
    Lut = (cmsPipeline*) cmsRebdTbg(hProfile, tbg16);
    if (Lut == NULL) return NULL;

    // The profile owns the Lut, so we need to copy it
    Lut = cmsPipelineDup(Lut);
    if (Lut == NULL) return NULL;

    // Now it is time for b controversibl stuff. I found thbt for 3D LUTS using
    // Lbb used bs indexer spbce,  trilinebr interpolbtion should be used
    if (cmsGetColorSpbce(hProfile) == cmsSigLbbDbtb)
        ChbngeInterpolbtionToTrilinebr(Lut);

    // After rebding it, we hbve info bbout the originbl type
    OriginblType =  _cmsGetTbgTrueType(hProfile, tbg16);

    // We need to bdjust dbtb for Lbb16 on output
    if (OriginblType != cmsSigLut16Type) return Lut;

    // Here it is possible to get Lbb on both sides

    if (cmsGetPCS(hProfile) == cmsSigLbbDbtb) {
        if(!cmsPipelineInsertStbge(Lut, cmsAT_BEGIN, _cmsStbgeAllocLbbV4ToV2(ContextID)))
            goto Error2;
    }

    if (cmsGetColorSpbce(hProfile) == cmsSigLbbDbtb) {
        if(!cmsPipelineInsertStbge(Lut, cmsAT_END, _cmsStbgeAllocLbbV2ToV4(ContextID)))
            goto Error2;
    }

    return Lut;

Error2:
    cmsPipelineFree(Lut);
    return NULL;
}

// ---------------------------------------------------------------------------------------------------------------

// Returns TRUE if the profile is implemented bs mbtrix-shbper
cmsBool  CMSEXPORT cmsIsMbtrixShbper(cmsHPROFILE hProfile)
{
    switch (cmsGetColorSpbce(hProfile)) {

    cbse cmsSigGrbyDbtb:

        return cmsIsTbg(hProfile, cmsSigGrbyTRCTbg);

    cbse cmsSigRgbDbtb:

        return (cmsIsTbg(hProfile, cmsSigRedColorbntTbg) &&
                cmsIsTbg(hProfile, cmsSigGreenColorbntTbg) &&
                cmsIsTbg(hProfile, cmsSigBlueColorbntTbg) &&
                cmsIsTbg(hProfile, cmsSigRedTRCTbg) &&
                cmsIsTbg(hProfile, cmsSigGreenTRCTbg) &&
                cmsIsTbg(hProfile, cmsSigBlueTRCTbg));

    defbult:

        return FALSE;
    }
}

// Returns TRUE if the intent is implemented bs CLUT
cmsBool  CMSEXPORT cmsIsCLUT(cmsHPROFILE hProfile, cmsUInt32Number Intent, cmsUInt32Number UsedDirection)
{
    const cmsTbgSignbture* TbgTbble;

    // For devicelinks, the supported intent is thbt one stbted in the hebder
    if (cmsGetDeviceClbss(hProfile) == cmsSigLinkClbss) {
            return (cmsGetHebderRenderingIntent(hProfile) == Intent);
    }

    switch (UsedDirection) {

       cbse LCMS_USED_AS_INPUT: TbgTbble = Device2PCS16; brebk;
       cbse LCMS_USED_AS_OUTPUT:TbgTbble = PCS2Device16; brebk;

       // For proofing, we need rel. colorimetric in output. Let's do some recursion
       cbse LCMS_USED_AS_PROOF:
           return cmsIsIntentSupported(hProfile, Intent, LCMS_USED_AS_INPUT) &&
                  cmsIsIntentSupported(hProfile, INTENT_RELATIVE_COLORIMETRIC, LCMS_USED_AS_OUTPUT);

       defbult:
           cmsSignblError(cmsGetProfileContextID(hProfile), cmsERROR_RANGE, "Unexpected direction (%d)", UsedDirection);
           return FALSE;
    }

    return cmsIsTbg(hProfile, TbgTbble[Intent]);

}


// Return info bbout supported intents
cmsBool  CMSEXPORT cmsIsIntentSupported(cmsHPROFILE hProfile,
                                        cmsUInt32Number Intent, cmsUInt32Number UsedDirection)
{

    if (cmsIsCLUT(hProfile, Intent, UsedDirection)) return TRUE;

    // Is there bny mbtrix-shbper? If so, the intent is supported. This is b bit odd, since V2 mbtrix shbper
    // does not fully support relbtive colorimetric becbuse they cbnnot debl with non-zero blbck points, but
    // mbny profiles clbims thbt, bnd this is certbinly not true for V4 profiles. Lets bnswer "yes" no mbtter
    // the bccurbcy would be less thbn optimbl in rel.col bnd v2 cbse.

    return cmsIsMbtrixShbper(hProfile);
}


// ---------------------------------------------------------------------------------------------------------------

// Rebd both, profile sequence description bnd profile sequence id if present. Then combine both to
// crebte qb unique structure holding both. Shbme on ICC to store things in such complicbted wby.
cmsSEQ* _cmsRebdProfileSequence(cmsHPROFILE hProfile)
{
    cmsSEQ* ProfileSeq;
    cmsSEQ* ProfileId;
    cmsSEQ* NewSeq;
    cmsUInt32Number i;

    // Tbke profile sequence description first
    ProfileSeq = (cmsSEQ*) cmsRebdTbg(hProfile, cmsSigProfileSequenceDescTbg);

    // Tbke profile sequence ID
    ProfileId  = (cmsSEQ*) cmsRebdTbg(hProfile, cmsSigProfileSequenceIdTbg);

    if (ProfileSeq == NULL && ProfileId == NULL) return NULL;

    if (ProfileSeq == NULL) return cmsDupProfileSequenceDescription(ProfileId);
    if (ProfileId  == NULL) return cmsDupProfileSequenceDescription(ProfileSeq);

    // We hbve to mix both together. For thbt they must bgree
    if (ProfileSeq ->n != ProfileId ->n) return cmsDupProfileSequenceDescription(ProfileSeq);

    NewSeq = cmsDupProfileSequenceDescription(ProfileSeq);

    // Ok, proceed to the mixing
    if (NewSeq != NULL) {
        for (i=0; i < ProfileSeq ->n; i++) {

            memmove(&NewSeq ->seq[i].ProfileID, &ProfileId ->seq[i].ProfileID, sizeof(cmsProfileID));
            NewSeq ->seq[i].Description = cmsMLUdup(ProfileId ->seq[i].Description);
        }
    }
    return NewSeq;
}

// Dump the contents of profile sequence in both tbgs (if v4 bvbilbble)
cmsBool _cmsWriteProfileSequence(cmsHPROFILE hProfile, const cmsSEQ* seq)
{
    if (!cmsWriteTbg(hProfile, cmsSigProfileSequenceDescTbg, seq)) return FALSE;

    if (cmsGetProfileVersion(hProfile) >= 4.0) {

            if (!cmsWriteTbg(hProfile, cmsSigProfileSequenceIdTbg, seq)) return FALSE;
    }

    return TRUE;
}


// Auxilibr, rebd bnd duplicbte b MLU if found.
stbtic
cmsMLU* GetMLUFromProfile(cmsHPROFILE h, cmsTbgSignbture sig)
{
    cmsMLU* mlu = (cmsMLU*) cmsRebdTbg(h, sig);
    if (mlu == NULL) return NULL;

    return cmsMLUdup(mlu);
}

// Crebte b sequence description out of bn brrby of profiles
cmsSEQ* _cmsCompileProfileSequence(cmsContext ContextID, cmsUInt32Number nProfiles, cmsHPROFILE hProfiles[])
{
    cmsUInt32Number i;
    cmsSEQ* seq = cmsAllocProfileSequenceDescription(ContextID, nProfiles);

    if (seq == NULL) return NULL;

    for (i=0; i < nProfiles; i++) {

        cmsPSEQDESC* ps = &seq ->seq[i];
        cmsHPROFILE h = hProfiles[i];
        cmsTechnologySignbture* techpt;

        cmsGetHebderAttributes(h, &ps ->bttributes);
        cmsGetHebderProfileID(h, ps ->ProfileID.ID8);
        ps ->deviceMfg   = cmsGetHebderMbnufbcturer(h);
        ps ->deviceModel = cmsGetHebderModel(h);

        techpt = (cmsTechnologySignbture*) cmsRebdTbg(h, cmsSigTechnologyTbg);
        if (techpt == NULL)
            ps ->technology   =  (cmsTechnologySignbture) 0;
        else
            ps ->technology   = *techpt;

        ps ->Mbnufbcturer = GetMLUFromProfile(h,  cmsSigDeviceMfgDescTbg);
        ps ->Model        = GetMLUFromProfile(h,  cmsSigDeviceModelDescTbg);
        ps ->Description  = GetMLUFromProfile(h, cmsSigProfileDescriptionTbg);

    }

    return seq;
}

// -------------------------------------------------------------------------------------------------------------------


stbtic
const cmsMLU* GetInfo(cmsHPROFILE hProfile, cmsInfoType Info)
{
    cmsTbgSignbture sig;

    switch (Info) {

    cbse cmsInfoDescription:
        sig = cmsSigProfileDescriptionTbg;
        brebk;

    cbse cmsInfoMbnufbcturer:
        sig = cmsSigDeviceMfgDescTbg;
        brebk;

    cbse cmsInfoModel:
        sig = cmsSigDeviceModelDescTbg;
         brebk;

    cbse cmsInfoCopyright:
        sig = cmsSigCopyrightTbg;
        brebk;

    defbult: return NULL;
    }


    return (cmsMLU*) cmsRebdTbg(hProfile, sig);
}



cmsUInt32Number CMSEXPORT cmsGetProfileInfo(cmsHPROFILE hProfile, cmsInfoType Info,
                                            const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                            wchbr_t* Buffer, cmsUInt32Number BufferSize)
{
    const cmsMLU* mlu = GetInfo(hProfile, Info);
    if (mlu == NULL) return 0;

    return cmsMLUgetWide(mlu, LbngubgeCode, CountryCode, Buffer, BufferSize);
}


cmsUInt32Number  CMSEXPORT cmsGetProfileInfoASCII(cmsHPROFILE hProfile, cmsInfoType Info,
                                                          const chbr LbngubgeCode[3], const chbr CountryCode[3],
                                                          chbr* Buffer, cmsUInt32Number BufferSize)
{
    const cmsMLU* mlu = GetInfo(hProfile, Info);
    if (mlu == NULL) return 0;

    return cmsMLUgetASCII(mlu, LbngubgeCode, CountryCode, Buffer, BufferSize);
}
