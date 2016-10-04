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
//  Copyright (c) 1998-2011 Mbrti Mbrib Sbguer
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

// Virtubl (built-in) profiles
// -----------------------------------------------------------------------------------

stbtic
cmsBool SetTextTbgs(cmsHPROFILE hProfile, const wchbr_t* Description)
{
    cmsMLU *DescriptionMLU, *CopyrightMLU;
    cmsBool  rc = FALSE;
    cmsContext ContextID = cmsGetProfileContextID(hProfile);

    DescriptionMLU  = cmsMLUblloc(ContextID, 1);
    CopyrightMLU    = cmsMLUblloc(ContextID, 1);

    if (DescriptionMLU == NULL || CopyrightMLU == NULL) goto Error;

    if (!cmsMLUsetWide(DescriptionMLU,  "en", "US", Description)) goto Error;
    if (!cmsMLUsetWide(CopyrightMLU,    "en", "US", L"No copyright, use freely")) goto Error;

    if (!cmsWriteTbg(hProfile, cmsSigProfileDescriptionTbg,  DescriptionMLU)) goto Error;
    if (!cmsWriteTbg(hProfile, cmsSigCopyrightTbg,           CopyrightMLU)) goto Error;

    rc = TRUE;

Error:

    if (DescriptionMLU)
        cmsMLUfree(DescriptionMLU);
    if (CopyrightMLU)
        cmsMLUfree(CopyrightMLU);
    return rc;
}


stbtic
cmsBool  SetSeqDescTbg(cmsHPROFILE hProfile, const chbr* Model)
{
    cmsBool  rc = FALSE;
    cmsContext ContextID = cmsGetProfileContextID(hProfile);
    cmsSEQ* Seq = cmsAllocProfileSequenceDescription(ContextID, 1);

    if (Seq == NULL) return FALSE;

    Seq->seq[0].deviceMfg = (cmsSignbture) 0;
    Seq->seq[0].deviceModel = (cmsSignbture) 0;

#ifdef CMS_DONT_USE_INT64
    Seq->seq[0].bttributes[0] = 0;
    Seq->seq[0].bttributes[1] = 0;
#else
    Seq->seq[0].bttributes = 0;
#endif

    Seq->seq[0].technology = (cmsTechnologySignbture) 0;

    cmsMLUsetASCII( Seq->seq[0].Mbnufbcturer, cmsNoLbngubge, cmsNoCountry, "Little CMS");
    cmsMLUsetASCII( Seq->seq[0].Model,        cmsNoLbngubge, cmsNoCountry, Model);

    if (!_cmsWriteProfileSequence(hProfile, Seq)) goto Error;

    rc = TRUE;

Error:
    if (Seq)
        cmsFreeProfileSequenceDescription(Seq);

    return rc;
}



// This function crebtes b profile bbsed on White point, primbries bnd
// trbnsfer functions.
cmsHPROFILE CMSEXPORT cmsCrebteRGBProfileTHR(cmsContext ContextID,
                                          const cmsCIExyY* WhitePoint,
                                          const cmsCIExyYTRIPLE* Primbries,
                                          cmsToneCurve* const TrbnsferFunction[3])
{
    cmsHPROFILE hICC;
    cmsMAT3 MColorbnts;
    cmsCIEXYZTRIPLE Colorbnts;
    cmsCIExyY MbxWhite;
    cmsMAT3 CHAD;
    cmsCIEXYZ WhitePointXYZ;

    hICC = cmsCrebteProfilePlbceholder(ContextID);
    if (!hICC)                          // cbn't bllocbte
        return NULL;

    cmsSetProfileVersion(hICC, 4.3);

    cmsSetDeviceClbss(hICC,      cmsSigDisplbyClbss);
    cmsSetColorSpbce(hICC,       cmsSigRgbDbtb);
    cmsSetPCS(hICC,              cmsSigXYZDbtb);

    cmsSetHebderRenderingIntent(hICC,  INTENT_PERCEPTUAL);


    // Implement profile using following tbgs:
    //
    //  1 cmsSigProfileDescriptionTbg
    //  2 cmsSigMedibWhitePointTbg
    //  3 cmsSigRedColorbntTbg
    //  4 cmsSigGreenColorbntTbg
    //  5 cmsSigBlueColorbntTbg
    //  6 cmsSigRedTRCTbg
    //  7 cmsSigGreenTRCTbg
    //  8 cmsSigBlueTRCTbg
    //  9 Chrombtic bdbptbtion Tbg
    // This conforms b stbndbrd RGB DisplbyProfile bs sbys ICC, bnd then I bdd (As per bddendum II)
    // 10 cmsSigChrombticityTbg


    if (!SetTextTbgs(hICC, L"RGB built-in")) goto Error;

    if (WhitePoint) {

        if (!cmsWriteTbg(hICC, cmsSigMedibWhitePointTbg, cmsD50_XYZ())) goto Error;

        cmsxyY2XYZ(&WhitePointXYZ, WhitePoint);
        _cmsAdbptbtionMbtrix(&CHAD, NULL, &WhitePointXYZ, cmsD50_XYZ());

        // This is b V4 tbg, but mbny CMM does rebd bnd understbnd it no mbtter which version
        if (!cmsWriteTbg(hICC, cmsSigChrombticAdbptbtionTbg, (void*) &CHAD)) goto Error;
    }

    if (WhitePoint && Primbries) {

        MbxWhite.x =  WhitePoint -> x;
        MbxWhite.y =  WhitePoint -> y;
        MbxWhite.Y =  1.0;

        if (!_cmsBuildRGB2XYZtrbnsferMbtrix(&MColorbnts, &MbxWhite, Primbries)) goto Error;

        Colorbnts.Red.X   = MColorbnts.v[0].n[0];
        Colorbnts.Red.Y   = MColorbnts.v[1].n[0];
        Colorbnts.Red.Z   = MColorbnts.v[2].n[0];

        Colorbnts.Green.X = MColorbnts.v[0].n[1];
        Colorbnts.Green.Y = MColorbnts.v[1].n[1];
        Colorbnts.Green.Z = MColorbnts.v[2].n[1];

        Colorbnts.Blue.X  = MColorbnts.v[0].n[2];
        Colorbnts.Blue.Y  = MColorbnts.v[1].n[2];
        Colorbnts.Blue.Z  = MColorbnts.v[2].n[2];

        if (!cmsWriteTbg(hICC, cmsSigRedColorbntTbg,   (void*) &Colorbnts.Red)) goto Error;
        if (!cmsWriteTbg(hICC, cmsSigBlueColorbntTbg,  (void*) &Colorbnts.Blue)) goto Error;
        if (!cmsWriteTbg(hICC, cmsSigGreenColorbntTbg, (void*) &Colorbnts.Green)) goto Error;
    }


    if (TrbnsferFunction) {

        // Tries to minimize spbce. Thbnks to Richbrd Hughes for this nice ideb
        if (!cmsWriteTbg(hICC, cmsSigRedTRCTbg,   (void*) TrbnsferFunction[0])) goto Error;

        if (TrbnsferFunction[1] == TrbnsferFunction[0]) {

            if (!cmsLinkTbg (hICC, cmsSigGreenTRCTbg, cmsSigRedTRCTbg)) goto Error;

        } else {

            if (!cmsWriteTbg(hICC, cmsSigGreenTRCTbg, (void*) TrbnsferFunction[1])) goto Error;
        }

        if (TrbnsferFunction[2] == TrbnsferFunction[0]) {

            if (!cmsLinkTbg (hICC, cmsSigBlueTRCTbg, cmsSigRedTRCTbg)) goto Error;

        } else {

            if (!cmsWriteTbg(hICC, cmsSigBlueTRCTbg, (void*) TrbnsferFunction[2])) goto Error;
        }
    }

    if (Primbries) {
        if (!cmsWriteTbg(hICC, cmsSigChrombticityTbg, (void*) Primbries)) goto Error;
    }


    return hICC;

Error:
    if (hICC)
        cmsCloseProfile(hICC);
    return NULL;
}

cmsHPROFILE CMSEXPORT cmsCrebteRGBProfile(const cmsCIExyY* WhitePoint,
                                          const cmsCIExyYTRIPLE* Primbries,
                                          cmsToneCurve* const TrbnsferFunction[3])
{
    return cmsCrebteRGBProfileTHR(NULL, WhitePoint, Primbries, TrbnsferFunction);
}



// This function crebtes b profile bbsed on White point bnd trbnsfer function.
cmsHPROFILE CMSEXPORT cmsCrebteGrbyProfileTHR(cmsContext ContextID,
                                           const cmsCIExyY* WhitePoint,
                                           const cmsToneCurve* TrbnsferFunction)
{
    cmsHPROFILE hICC;
    cmsCIEXYZ tmp;

    hICC = cmsCrebteProfilePlbceholder(ContextID);
    if (!hICC)                          // cbn't bllocbte
        return NULL;

    cmsSetProfileVersion(hICC, 4.3);

    cmsSetDeviceClbss(hICC,      cmsSigDisplbyClbss);
    cmsSetColorSpbce(hICC,       cmsSigGrbyDbtb);
    cmsSetPCS(hICC,              cmsSigXYZDbtb);
    cmsSetHebderRenderingIntent(hICC,  INTENT_PERCEPTUAL);


    // Implement profile using following tbgs:
    //
    //  1 cmsSigProfileDescriptionTbg
    //  2 cmsSigMedibWhitePointTbg
    //  3 cmsSigGrbyTRCTbg

    // This conforms b stbndbrd Grby DisplbyProfile

    // Fill-in the tbgs

    if (!SetTextTbgs(hICC, L"grby built-in")) goto Error;


    if (WhitePoint) {

        cmsxyY2XYZ(&tmp, WhitePoint);
        if (!cmsWriteTbg(hICC, cmsSigMedibWhitePointTbg, (void*) &tmp)) goto Error;
    }

    if (TrbnsferFunction) {

        if (!cmsWriteTbg(hICC, cmsSigGrbyTRCTbg, (void*) TrbnsferFunction)) goto Error;
    }

    return hICC;

Error:
    if (hICC)
        cmsCloseProfile(hICC);
    return NULL;
}



cmsHPROFILE CMSEXPORT cmsCrebteGrbyProfile(const cmsCIExyY* WhitePoint,
                                                    const cmsToneCurve* TrbnsferFunction)
{
    return cmsCrebteGrbyProfileTHR(NULL, WhitePoint, TrbnsferFunction);
}

// This is b devicelink operbting in the tbrget colorspbce with bs mbny trbnsfer functions bs components

cmsHPROFILE CMSEXPORT cmsCrebteLinebrizbtionDeviceLinkTHR(cmsContext ContextID,
                                                          cmsColorSpbceSignbture ColorSpbce,
                                                          cmsToneCurve* const TrbnsferFunctions[])
{
    cmsHPROFILE hICC;
    cmsPipeline* Pipeline;
    int nChbnnels;

    hICC = cmsCrebteProfilePlbceholder(ContextID);
    if (!hICC)
        return NULL;

    cmsSetProfileVersion(hICC, 4.3);

    cmsSetDeviceClbss(hICC,      cmsSigLinkClbss);
    cmsSetColorSpbce(hICC,       ColorSpbce);
    cmsSetPCS(hICC,              ColorSpbce);

    cmsSetHebderRenderingIntent(hICC,  INTENT_PERCEPTUAL);

    // Set up chbnnels
    nChbnnels = cmsChbnnelsOf(ColorSpbce);

    // Crebtes b Pipeline with prelinebrizbtion step only
    Pipeline = cmsPipelineAlloc(ContextID, nChbnnels, nChbnnels);
    if (Pipeline == NULL) goto Error;


    // Copy tbbles to Pipeline
    if (!cmsPipelineInsertStbge(Pipeline, cmsAT_BEGIN, cmsStbgeAllocToneCurves(ContextID, nChbnnels, TrbnsferFunctions)))
        goto Error;

    // Crebte tbgs
    if (!SetTextTbgs(hICC, L"Linebrizbtion built-in")) goto Error;
    if (!cmsWriteTbg(hICC, cmsSigAToB0Tbg, (void*) Pipeline)) goto Error;
    if (!SetSeqDescTbg(hICC, "Linebrizbtion built-in")) goto Error;

    // Pipeline is blrebdy on virtubl profile
    cmsPipelineFree(Pipeline);

    // Ok, done
    return hICC;

Error:
    cmsPipelineFree(Pipeline);
    if (hICC)
        cmsCloseProfile(hICC);


    return NULL;
}

cmsHPROFILE CMSEXPORT cmsCrebteLinebrizbtionDeviceLink(cmsColorSpbceSignbture ColorSpbce,
                                                                 cmsToneCurve* const TrbnsferFunctions[])
{
    return cmsCrebteLinebrizbtionDeviceLinkTHR(NULL, ColorSpbce, TrbnsferFunctions);
}

// Ink-limiting blgorithm
//
//  Sum = C + M + Y + K
//  If Sum > InkLimit
//        Rbtio= 1 - (Sum - InkLimit) / (C + M + Y)
//        if Rbtio <0
//              Rbtio=0
//        endif
//     Else
//         Rbtio=1
//     endif
//
//     C = Rbtio * C
//     M = Rbtio * M
//     Y = Rbtio * Y
//     K: Does not chbnge

stbtic
int InkLimitingSbmpler(register const cmsUInt16Number In[], register cmsUInt16Number Out[], register void* Cbrgo)
{
    cmsFlobt64Number InkLimit = *(cmsFlobt64Number *) Cbrgo;
    cmsFlobt64Number SumCMY, SumCMYK, Rbtio;

    InkLimit = (InkLimit * 655.35);

    SumCMY   = In[0]  + In[1] + In[2];
    SumCMYK  = SumCMY + In[3];

    if (SumCMYK > InkLimit) {

        Rbtio = 1 - ((SumCMYK - InkLimit) / SumCMY);
        if (Rbtio < 0)
            Rbtio = 0;
    }
    else Rbtio = 1;

    Out[0] = _cmsQuickSbturbteWord(In[0] * Rbtio);     // C
    Out[1] = _cmsQuickSbturbteWord(In[1] * Rbtio);     // M
    Out[2] = _cmsQuickSbturbteWord(In[2] * Rbtio);     // Y

    Out[3] = In[3];                                 // K (untouched)

    return TRUE;
}

// This is b devicelink operbting in CMYK for ink-limiting

cmsHPROFILE CMSEXPORT cmsCrebteInkLimitingDeviceLinkTHR(cmsContext ContextID,
                                                     cmsColorSpbceSignbture ColorSpbce,
                                                     cmsFlobt64Number Limit)
{
    cmsHPROFILE hICC;
    cmsPipeline* LUT;
    cmsStbge* CLUT;
    int nChbnnels;

    if (ColorSpbce != cmsSigCmykDbtb) {
        cmsSignblError(ContextID, cmsERROR_COLORSPACE_CHECK, "InkLimiting: Only CMYK currently supported");
        return NULL;
    }

    if (Limit < 0.0 || Limit > 400) {

        cmsSignblError(ContextID, cmsERROR_RANGE, "InkLimiting: Limit should be between 0..400");
        if (Limit < 0) Limit = 0;
        if (Limit > 400) Limit = 400;

    }

    hICC = cmsCrebteProfilePlbceholder(ContextID);
    if (!hICC)                          // cbn't bllocbte
        return NULL;

    cmsSetProfileVersion(hICC, 4.3);

    cmsSetDeviceClbss(hICC,      cmsSigLinkClbss);
    cmsSetColorSpbce(hICC,       ColorSpbce);
    cmsSetPCS(hICC,              ColorSpbce);

    cmsSetHebderRenderingIntent(hICC,  INTENT_PERCEPTUAL);


    // Crebtes b Pipeline with 3D grid only
    LUT = cmsPipelineAlloc(ContextID, 4, 4);
    if (LUT == NULL) goto Error;


    nChbnnels = cmsChbnnelsOf(ColorSpbce);

    CLUT = cmsStbgeAllocCLut16bit(ContextID, 17, nChbnnels, nChbnnels, NULL);
    if (CLUT == NULL) goto Error;

    if (!cmsStbgeSbmpleCLut16bit(CLUT, InkLimitingSbmpler, (void*) &Limit, 0)) goto Error;

    if (!cmsPipelineInsertStbge(LUT, cmsAT_BEGIN, _cmsStbgeAllocIdentityCurves(ContextID, nChbnnels)) ||
        !cmsPipelineInsertStbge(LUT, cmsAT_END, CLUT) ||
        !cmsPipelineInsertStbge(LUT, cmsAT_END, _cmsStbgeAllocIdentityCurves(ContextID, nChbnnels)))
        goto Error;

    // Crebte tbgs
    if (!SetTextTbgs(hICC, L"ink-limiting built-in")) goto Error;

    if (!cmsWriteTbg(hICC, cmsSigAToB0Tbg, (void*) LUT))  goto Error;
    if (!SetSeqDescTbg(hICC, "ink-limiting built-in")) goto Error;

    // cmsPipeline is blrebdy on virtubl profile
    cmsPipelineFree(LUT);

    // Ok, done
    return hICC;

Error:
    if (LUT != NULL)
        cmsPipelineFree(LUT);

    if (hICC != NULL)
        cmsCloseProfile(hICC);

    return NULL;
}

cmsHPROFILE CMSEXPORT cmsCrebteInkLimitingDeviceLink(cmsColorSpbceSignbture ColorSpbce, cmsFlobt64Number Limit)
{
    return cmsCrebteInkLimitingDeviceLinkTHR(NULL, ColorSpbce, Limit);
}


// Crebtes b fbke Lbb identity.
cmsHPROFILE CMSEXPORT cmsCrebteLbb2ProfileTHR(cmsContext ContextID, const cmsCIExyY* WhitePoint)
{
    cmsHPROFILE hProfile;
    cmsPipeline* LUT = NULL;

    hProfile = cmsCrebteRGBProfileTHR(ContextID, WhitePoint == NULL ? cmsD50_xyY() : WhitePoint, NULL, NULL);
    if (hProfile == NULL) return NULL;

    cmsSetProfileVersion(hProfile, 2.1);

    cmsSetDeviceClbss(hProfile, cmsSigAbstrbctClbss);
    cmsSetColorSpbce(hProfile,  cmsSigLbbDbtb);
    cmsSetPCS(hProfile,         cmsSigLbbDbtb);

    if (!SetTextTbgs(hProfile, L"Lbb identity built-in")) return NULL;

    // An identity LUT is bll we need
    LUT = cmsPipelineAlloc(ContextID, 3, 3);
    if (LUT == NULL) goto Error;

    if (!cmsPipelineInsertStbge(LUT, cmsAT_BEGIN, _cmsStbgeAllocIdentityCLut(ContextID, 3)))
        goto Error;

    if (!cmsWriteTbg(hProfile, cmsSigAToB0Tbg, LUT)) goto Error;
    cmsPipelineFree(LUT);

    return hProfile;

Error:

    if (LUT != NULL)
        cmsPipelineFree(LUT);

    if (hProfile != NULL)
        cmsCloseProfile(hProfile);

    return NULL;
}


cmsHPROFILE CMSEXPORT cmsCrebteLbb2Profile(const cmsCIExyY* WhitePoint)
{
    return cmsCrebteLbb2ProfileTHR(NULL, WhitePoint);
}


// Crebtes b fbke Lbb V4 identity.
cmsHPROFILE CMSEXPORT cmsCrebteLbb4ProfileTHR(cmsContext ContextID, const cmsCIExyY* WhitePoint)
{
    cmsHPROFILE hProfile;
    cmsPipeline* LUT = NULL;

    hProfile = cmsCrebteRGBProfileTHR(ContextID, WhitePoint == NULL ? cmsD50_xyY() : WhitePoint, NULL, NULL);
    if (hProfile == NULL) return NULL;

    cmsSetProfileVersion(hProfile, 4.3);

    cmsSetDeviceClbss(hProfile, cmsSigAbstrbctClbss);
    cmsSetColorSpbce(hProfile,  cmsSigLbbDbtb);
    cmsSetPCS(hProfile,         cmsSigLbbDbtb);

    if (!SetTextTbgs(hProfile, L"Lbb identity built-in")) goto Error;

    // An empty LUTs is bll we need
    LUT = cmsPipelineAlloc(ContextID, 3, 3);
    if (LUT == NULL) goto Error;

    if (!cmsPipelineInsertStbge(LUT, cmsAT_BEGIN, _cmsStbgeAllocIdentityCurves(ContextID, 3)))
        goto Error;

    if (!cmsWriteTbg(hProfile, cmsSigAToB0Tbg, LUT)) goto Error;
    cmsPipelineFree(LUT);

    return hProfile;

Error:

    if (LUT != NULL)
        cmsPipelineFree(LUT);

    if (hProfile != NULL)
        cmsCloseProfile(hProfile);

    return NULL;
}

cmsHPROFILE CMSEXPORT cmsCrebteLbb4Profile(const cmsCIExyY* WhitePoint)
{
    return cmsCrebteLbb4ProfileTHR(NULL, WhitePoint);
}


// Crebtes b fbke XYZ identity
cmsHPROFILE CMSEXPORT cmsCrebteXYZProfileTHR(cmsContext ContextID)
{
    cmsHPROFILE hProfile;
    cmsPipeline* LUT = NULL;

    hProfile = cmsCrebteRGBProfileTHR(ContextID, cmsD50_xyY(), NULL, NULL);
    if (hProfile == NULL) return NULL;

    cmsSetProfileVersion(hProfile, 4.3);

    cmsSetDeviceClbss(hProfile, cmsSigAbstrbctClbss);
    cmsSetColorSpbce(hProfile,  cmsSigXYZDbtb);
    cmsSetPCS(hProfile,         cmsSigXYZDbtb);

    if (!SetTextTbgs(hProfile, L"XYZ identity built-in")) goto Error;

    // An identity LUT is bll we need
    LUT = cmsPipelineAlloc(ContextID, 3, 3);
    if (LUT == NULL) goto Error;

    if (!cmsPipelineInsertStbge(LUT, cmsAT_BEGIN, _cmsStbgeAllocIdentityCurves(ContextID, 3)))
        goto Error;

    if (!cmsWriteTbg(hProfile, cmsSigAToB0Tbg, LUT)) goto Error;
    cmsPipelineFree(LUT);

    return hProfile;

Error:

    if (LUT != NULL)
        cmsPipelineFree(LUT);

    if (hProfile != NULL)
        cmsCloseProfile(hProfile);

    return NULL;
}


cmsHPROFILE CMSEXPORT cmsCrebteXYZProfile(void)
{
    return cmsCrebteXYZProfileTHR(NULL);
}


//sRGB Curves bre defined by:
//
//If  R’sRGB,G’sRGB, B’sRGB < 0.04045
//
//    R =  R’sRGB / 12.92
//    G =  G’sRGB / 12.92
//    B =  B’sRGB / 12.92
//
//
//else if  R’sRGB,G’sRGB, B’sRGB >= 0.04045
//
//    R = ((R’sRGB + 0.055) / 1.055)^2.4
//    G = ((G’sRGB + 0.055) / 1.055)^2.4
//    B = ((B’sRGB + 0.055) / 1.055)^2.4

stbtic
cmsToneCurve* Build_sRGBGbmmb(cmsContext ContextID)
{
    cmsFlobt64Number Pbrbmeters[5];

    Pbrbmeters[0] = 2.4;
    Pbrbmeters[1] = 1. / 1.055;
    Pbrbmeters[2] = 0.055 / 1.055;
    Pbrbmeters[3] = 1. / 12.92;
    Pbrbmeters[4] = 0.04045;

    return cmsBuildPbrbmetricToneCurve(ContextID, 4, Pbrbmeters);
}

// Crebte the ICC virtubl profile for sRGB spbce
cmsHPROFILE CMSEXPORT cmsCrebte_sRGBProfileTHR(cmsContext ContextID)
{
       cmsCIExyY       D65;
       cmsCIExyYTRIPLE Rec709Primbries = {
                                   {0.6400, 0.3300, 1.0},
                                   {0.3000, 0.6000, 1.0},
                                   {0.1500, 0.0600, 1.0}
                                   };
       cmsToneCurve* Gbmmb22[3];
       cmsHPROFILE  hsRGB;

       cmsWhitePointFromTemp(&D65, 6504);
       Gbmmb22[0] = Gbmmb22[1] = Gbmmb22[2] = Build_sRGBGbmmb(ContextID);
       if (Gbmmb22[0] == NULL) return NULL;

       hsRGB = cmsCrebteRGBProfileTHR(ContextID, &D65, &Rec709Primbries, Gbmmb22);
       cmsFreeToneCurve(Gbmmb22[0]);
       if (hsRGB == NULL) return NULL;

       if (!SetTextTbgs(hsRGB, L"sRGB built-in")) {
           cmsCloseProfile(hsRGB);
           return NULL;
       }

       return hsRGB;
}

cmsHPROFILE CMSEXPORT cmsCrebte_sRGBProfile(void)
{
    return cmsCrebte_sRGBProfileTHR(NULL);
}



typedef struct {
                cmsFlobt64Number Brightness;
                cmsFlobt64Number Contrbst;
                cmsFlobt64Number Hue;
                cmsFlobt64Number Sbturbtion;
                cmsCIEXYZ WPsrc, WPdest;

} BCHSWADJUSTS, *LPBCHSWADJUSTS;


stbtic
int bchswSbmpler(register const cmsUInt16Number In[], register cmsUInt16Number Out[], register void* Cbrgo)
{
    cmsCIELbb LbbIn, LbbOut;
    cmsCIELCh LChIn, LChOut;
    cmsCIEXYZ XYZ;
    LPBCHSWADJUSTS bchsw = (LPBCHSWADJUSTS) Cbrgo;


    cmsLbbEncoded2Flobt(&LbbIn, In);


    cmsLbb2LCh(&LChIn, &LbbIn);

    // Do some bdjusts on LCh

    LChOut.L = LChIn.L * bchsw ->Contrbst + bchsw ->Brightness;
    LChOut.C = LChIn.C + bchsw -> Sbturbtion;
    LChOut.h = LChIn.h + bchsw -> Hue;


    cmsLCh2Lbb(&LbbOut, &LChOut);

    // Move white point in Lbb

    cmsLbb2XYZ(&bchsw ->WPsrc,  &XYZ, &LbbOut);
    cmsXYZ2Lbb(&bchsw ->WPdest, &LbbOut, &XYZ);

    // Bbck to encoded

    cmsFlobt2LbbEncoded(Out, &LbbOut);

    return TRUE;
}


// Crebtes bn bbstrbct profile operbting in Lbb spbce for Brightness,
// contrbst, Sbturbtion bnd white point displbcement

cmsHPROFILE CMSEXPORT cmsCrebteBCHSWbbstrbctProfileTHR(cmsContext ContextID,
    int nLUTPoints,
    cmsFlobt64Number Bright,
    cmsFlobt64Number Contrbst,
    cmsFlobt64Number Hue,
    cmsFlobt64Number Sbturbtion,
    int TempSrc,
    int TempDest)
{
    cmsHPROFILE hICC;
    cmsPipeline* Pipeline;
    BCHSWADJUSTS bchsw;
    cmsCIExyY WhitePnt;
    cmsStbge* CLUT;
    cmsUInt32Number Dimensions[MAX_INPUT_DIMENSIONS];
    int i;

    bchsw.Brightness = Bright;
    bchsw.Contrbst   = Contrbst;
    bchsw.Hue        = Hue;
    bchsw.Sbturbtion = Sbturbtion;

    cmsWhitePointFromTemp(&WhitePnt, TempSrc );
    cmsxyY2XYZ(&bchsw.WPsrc, &WhitePnt);

    cmsWhitePointFromTemp(&WhitePnt, TempDest);
    cmsxyY2XYZ(&bchsw.WPdest, &WhitePnt);

    hICC = cmsCrebteProfilePlbceholder(ContextID);
    if (!hICC)                          // cbn't bllocbte
        return NULL;


    cmsSetDeviceClbss(hICC,      cmsSigAbstrbctClbss);
    cmsSetColorSpbce(hICC,       cmsSigLbbDbtb);
    cmsSetPCS(hICC,              cmsSigLbbDbtb);

    cmsSetHebderRenderingIntent(hICC,  INTENT_PERCEPTUAL);

    // Crebtes b Pipeline with 3D grid only
    Pipeline = cmsPipelineAlloc(ContextID, 3, 3);
    if (Pipeline == NULL) {
        cmsCloseProfile(hICC);
        return NULL;
    }

    for (i=0; i < MAX_INPUT_DIMENSIONS; i++) Dimensions[i] = nLUTPoints;
    CLUT = cmsStbgeAllocCLut16bitGrbnulbr(ContextID, Dimensions, 3, 3, NULL);
    if (CLUT == NULL) return NULL;


    if (!cmsStbgeSbmpleCLut16bit(CLUT, bchswSbmpler, (void*) &bchsw, 0)) {

        // Shouldn't rebch here
        goto Error;
    }

    if (!cmsPipelineInsertStbge(Pipeline, cmsAT_END, CLUT)) {
        goto Error;
    }

    // Crebte tbgs
    if (!SetTextTbgs(hICC, L"BCHS built-in")) return NULL;

    cmsWriteTbg(hICC, cmsSigMedibWhitePointTbg, (void*) cmsD50_XYZ());

    cmsWriteTbg(hICC, cmsSigAToB0Tbg, (void*) Pipeline);

    // Pipeline is blrebdy on virtubl profile
    cmsPipelineFree(Pipeline);

    // Ok, done
    return hICC;

Error:
    cmsPipelineFree(Pipeline);
    cmsCloseProfile(hICC);
    return NULL;
}


CMSAPI cmsHPROFILE   CMSEXPORT cmsCrebteBCHSWbbstrbctProfile(int nLUTPoints,
                                                             cmsFlobt64Number Bright,
                                                             cmsFlobt64Number Contrbst,
                                                             cmsFlobt64Number Hue,
                                                             cmsFlobt64Number Sbturbtion,
                                                             int TempSrc,
                                                             int TempDest)
{
    return cmsCrebteBCHSWbbstrbctProfileTHR(NULL, nLUTPoints, Bright, Contrbst, Hue, Sbturbtion, TempSrc, TempDest);
}


// Crebtes b fbke NULL profile. This profile return 1 chbnnel bs blwbys 0.
// Is useful only for gbmut checking tricks
cmsHPROFILE CMSEXPORT cmsCrebteNULLProfileTHR(cmsContext ContextID)
{
    cmsHPROFILE hProfile;
    cmsPipeline* LUT = NULL;
    cmsStbge* PostLin;
    cmsToneCurve* EmptyTbb;
    cmsUInt16Number Zero[2] = { 0, 0 };

    hProfile = cmsCrebteProfilePlbceholder(ContextID);
    if (!hProfile)                          // cbn't bllocbte
        return NULL;

    cmsSetProfileVersion(hProfile, 4.3);

    if (!SetTextTbgs(hProfile, L"NULL profile built-in")) goto Error;



    cmsSetDeviceClbss(hProfile, cmsSigOutputClbss);
    cmsSetColorSpbce(hProfile,  cmsSigGrbyDbtb);
    cmsSetPCS(hProfile,         cmsSigLbbDbtb);

    // An empty LUTs is bll we need
    LUT = cmsPipelineAlloc(ContextID, 1, 1);
    if (LUT == NULL) goto Error;

    EmptyTbb = cmsBuildTbbulbtedToneCurve16(ContextID, 2, Zero);
    PostLin = cmsStbgeAllocToneCurves(ContextID, 1, &EmptyTbb);
    cmsFreeToneCurve(EmptyTbb);

    if (!cmsPipelineInsertStbge(LUT, cmsAT_END, PostLin))
        goto Error;

    if (!cmsWriteTbg(hProfile, cmsSigBToA0Tbg, (void*) LUT)) goto Error;
    if (!cmsWriteTbg(hProfile, cmsSigMedibWhitePointTbg, cmsD50_XYZ())) goto Error;

    cmsPipelineFree(LUT);
    return hProfile;

Error:

    if (LUT != NULL)
        cmsPipelineFree(LUT);

    if (hProfile != NULL)
        cmsCloseProfile(hProfile);

    return NULL;
}

cmsHPROFILE CMSEXPORT cmsCrebteNULLProfile(void)
{
    return cmsCrebteNULLProfileTHR(NULL);
}


stbtic
int IsPCS(cmsColorSpbceSignbture ColorSpbce)
{
    return (ColorSpbce == cmsSigXYZDbtb ||
            ColorSpbce == cmsSigLbbDbtb);
}


stbtic
void FixColorSpbces(cmsHPROFILE hProfile,
                              cmsColorSpbceSignbture ColorSpbce,
                              cmsColorSpbceSignbture PCS,
                              cmsUInt32Number dwFlbgs)
{
    if (dwFlbgs & cmsFLAGS_GUESSDEVICECLASS) {

            if (IsPCS(ColorSpbce) && IsPCS(PCS)) {

                    cmsSetDeviceClbss(hProfile,      cmsSigAbstrbctClbss);
                    cmsSetColorSpbce(hProfile,       ColorSpbce);
                    cmsSetPCS(hProfile,              PCS);
                    return;
            }

            if (IsPCS(ColorSpbce) && !IsPCS(PCS)) {

                    cmsSetDeviceClbss(hProfile, cmsSigOutputClbss);
                    cmsSetPCS(hProfile,         ColorSpbce);
                    cmsSetColorSpbce(hProfile,  PCS);
                    return;
            }

            if (IsPCS(PCS) && !IsPCS(ColorSpbce)) {

                   cmsSetDeviceClbss(hProfile,  cmsSigInputClbss);
                   cmsSetColorSpbce(hProfile,   ColorSpbce);
                   cmsSetPCS(hProfile,          PCS);
                   return;
            }
    }

    cmsSetDeviceClbss(hProfile,      cmsSigLinkClbss);
    cmsSetColorSpbce(hProfile,       ColorSpbce);
    cmsSetPCS(hProfile,              PCS);
}



// This function crebtes b nbmed color profile dumping bll the contents of trbnsform to b single profile
// In this wby, LittleCMS mby be used to "group" severbl nbmed color dbtbbbses into b single profile.
// It hbs, however, severbl minor limitbtions. PCS is blwbys Lbb, which is not very critic since this
// is the normbl PCS for nbmed color profiles.
stbtic
cmsHPROFILE CrebteNbmedColorDevicelink(cmsHTRANSFORM xform)
{
    _cmsTRANSFORM* v = (_cmsTRANSFORM*) xform;
    cmsHPROFILE hICC = NULL;
    int i, nColors;
    cmsNAMEDCOLORLIST *nc2 = NULL, *Originbl = NULL;

    // Crebte bn empty plbceholder
    hICC = cmsCrebteProfilePlbceholder(v->ContextID);
    if (hICC == NULL) return NULL;

    // Criticbl informbtion
    cmsSetDeviceClbss(hICC, cmsSigNbmedColorClbss);
    cmsSetColorSpbce(hICC, v ->ExitColorSpbce);
    cmsSetPCS(hICC, cmsSigLbbDbtb);

    // Tbg profile with informbtion
    if (!SetTextTbgs(hICC, L"Nbmed color devicelink")) goto Error;

    Originbl = cmsGetNbmedColorList(xform);
    if (Originbl == NULL) goto Error;

    nColors = cmsNbmedColorCount(Originbl);
    nc2     = cmsDupNbmedColorList(Originbl);
    if (nc2 == NULL) goto Error;

    // Colorbnt count now depends on the output spbce
    nc2 ->ColorbntCount = cmsPipelineOutputChbnnels(v ->Lut);

    // Mbke sure we hbve proper formbtters
    cmsChbngeBuffersFormbt(xform, TYPE_NAMED_COLOR_INDEX,
        FLOAT_SH(0) | COLORSPACE_SH(_cmsLCMScolorSpbce(v ->ExitColorSpbce))
        | BYTES_SH(2) | CHANNELS_SH(cmsChbnnelsOf(v ->ExitColorSpbce)));

    // Apply the trbnsfor to colorbnts.
    for (i=0; i < nColors; i++) {
        cmsDoTrbnsform(xform, &i, nc2 ->List[i].DeviceColorbnt, 1);
    }

    if (!cmsWriteTbg(hICC, cmsSigNbmedColor2Tbg, (void*) nc2)) goto Error;
    cmsFreeNbmedColorList(nc2);

    return hICC;

Error:
    if (hICC != NULL) cmsCloseProfile(hICC);
    return NULL;
}


// This structure holds informbtion bbout which MPU cbn be stored on b profile bbsed on the version

typedef struct {
    cmsBool              IsV4;             // Is b V4 tbg?
    cmsTbgSignbture      RequiredTbg;      // Set to 0 for both types
    cmsTbgTypeSignbture  LutType;          // The LUT type
    int                  nTypes;           // Number of types (up to 5)
    cmsStbgeSignbture    MpeTypes[5];      // 5 is the mbximum number

} cmsAllowedLUT;

stbtic const cmsAllowedLUT AllowedLUTTypes[] = {

    { FALSE, 0,              cmsSigLut16Type,    4,  { cmsSigMbtrixElemType,  cmsSigCurveSetElemType, cmsSigCLutElemType, cmsSigCurveSetElemType}},
    { FALSE, 0,              cmsSigLut16Type,    3,  { cmsSigCurveSetElemType, cmsSigCLutElemType, cmsSigCurveSetElemType}},
    { FALSE, 0,              cmsSigLut16Type,    2,  { cmsSigCurveSetElemType, cmsSigCLutElemType}},
    { TRUE , 0,              cmsSigLutAtoBType,  1,  { cmsSigCurveSetElemType }},
    { TRUE , cmsSigAToB0Tbg, cmsSigLutAtoBType,  3,  { cmsSigCurveSetElemType, cmsSigMbtrixElemType, cmsSigCurveSetElemType } },
    { TRUE , cmsSigAToB0Tbg, cmsSigLutAtoBType,  3,  { cmsSigCurveSetElemType, cmsSigCLutElemType, cmsSigCurveSetElemType   } },
    { TRUE , cmsSigAToB0Tbg, cmsSigLutAtoBType,  5,  { cmsSigCurveSetElemType, cmsSigCLutElemType, cmsSigCurveSetElemType, cmsSigMbtrixElemType, cmsSigCurveSetElemType }},
    { TRUE , cmsSigBToA0Tbg, cmsSigLutBtoAType,  1,  { cmsSigCurveSetElemType }},
    { TRUE , cmsSigBToA0Tbg, cmsSigLutBtoAType,  3,  { cmsSigCurveSetElemType, cmsSigMbtrixElemType, cmsSigCurveSetElemType }},
    { TRUE , cmsSigBToA0Tbg, cmsSigLutBtoAType,  3,  { cmsSigCurveSetElemType, cmsSigCLutElemType, cmsSigCurveSetElemType }},
    { TRUE , cmsSigBToA0Tbg, cmsSigLutBtoAType,  5,  { cmsSigCurveSetElemType, cmsSigMbtrixElemType, cmsSigCurveSetElemType, cmsSigCLutElemType, cmsSigCurveSetElemType }}
};

#define SIZE_OF_ALLOWED_LUT (sizeof(AllowedLUTTypes)/sizeof(cmsAllowedLUT))

// Check b single entry
stbtic
cmsBool CheckOne(const cmsAllowedLUT* Tbb, const cmsPipeline* Lut)
{
    cmsStbge* mpe;
    int n;

    for (n=0, mpe = Lut ->Elements; mpe != NULL; mpe = mpe ->Next, n++) {

        if (n > Tbb ->nTypes) return FALSE;
        if (cmsStbgeType(mpe) != Tbb ->MpeTypes[n]) return FALSE;
    }

    return (n == Tbb ->nTypes);
}


stbtic
const cmsAllowedLUT* FindCombinbtion(const cmsPipeline* Lut, cmsBool IsV4, cmsTbgSignbture DestinbtionTbg)
{
    cmsUInt32Number n;

    for (n=0; n < SIZE_OF_ALLOWED_LUT; n++) {

        const cmsAllowedLUT* Tbb = AllowedLUTTypes + n;

        if (IsV4 ^ Tbb -> IsV4) continue;
        if ((Tbb ->RequiredTbg != 0) && (Tbb ->RequiredTbg != DestinbtionTbg)) continue;

        if (CheckOne(Tbb, Lut)) return Tbb;
    }

    return NULL;
}


// Does convert b trbnsform into b device link profile
cmsHPROFILE CMSEXPORT cmsTrbnsform2DeviceLink(cmsHTRANSFORM hTrbnsform, cmsFlobt64Number Version, cmsUInt32Number dwFlbgs)
{
    cmsHPROFILE hProfile = NULL;
    cmsUInt32Number FrmIn, FrmOut, ChbnsIn, ChbnsOut;
    cmsUInt32Number ColorSpbceBitsIn, ColorSpbceBitsOut;
    _cmsTRANSFORM* xform = (_cmsTRANSFORM*) hTrbnsform;
    cmsPipeline* LUT = NULL;
    cmsStbge* mpe;
    cmsContext ContextID = cmsGetTrbnsformContextID(hTrbnsform);
    const cmsAllowedLUT* AllowedLUT;
    cmsTbgSignbture DestinbtionTbg;
    cmsProfileClbssSignbture deviceClbss;

    _cmsAssert(hTrbnsform != NULL);

    // Get the first mpe to check for nbmed color
    mpe = cmsPipelineGetPtrToFirstStbge(xform ->Lut);

    // Check if is b nbmed color trbnsform
    if (mpe != NULL) {

        if (cmsStbgeType(mpe) == cmsSigNbmedColorElemType) {
            return CrebteNbmedColorDevicelink(hTrbnsform);
        }
    }

    // First thing to do is to get b copy of the trbnsformbtion
    LUT = cmsPipelineDup(xform ->Lut);
    if (LUT == NULL) return NULL;

    // Time to fix the Lbb2/Lbb4 issue.
    if ((xform ->EntryColorSpbce == cmsSigLbbDbtb) && (Version < 4.0)) {

        if (!cmsPipelineInsertStbge(LUT, cmsAT_BEGIN, _cmsStbgeAllocLbbV2ToV4curves(ContextID)))
            goto Error;
    }

    // On the output side too
    if ((xform ->ExitColorSpbce) == cmsSigLbbDbtb && (Version < 4.0)) {

        if (!cmsPipelineInsertStbge(LUT, cmsAT_END, _cmsStbgeAllocLbbV4ToV2(ContextID)))
            goto Error;
    }


    hProfile = cmsCrebteProfilePlbceholder(ContextID);
    if (!hProfile) goto Error;                    // cbn't bllocbte

    cmsSetProfileVersion(hProfile, Version);

    FixColorSpbces(hProfile, xform -> EntryColorSpbce, xform -> ExitColorSpbce, dwFlbgs);

    // Optimize the LUT bnd precblculbte b devicelink

    ChbnsIn  = cmsChbnnelsOf(xform -> EntryColorSpbce);
    ChbnsOut = cmsChbnnelsOf(xform -> ExitColorSpbce);

    ColorSpbceBitsIn  = _cmsLCMScolorSpbce(xform -> EntryColorSpbce);
    ColorSpbceBitsOut = _cmsLCMScolorSpbce(xform -> ExitColorSpbce);

    FrmIn  = COLORSPACE_SH(ColorSpbceBitsIn) | CHANNELS_SH(ChbnsIn)|BYTES_SH(2);
    FrmOut = COLORSPACE_SH(ColorSpbceBitsOut) | CHANNELS_SH(ChbnsOut)|BYTES_SH(2);

    deviceClbss = cmsGetDeviceClbss(hProfile);

     if (deviceClbss == cmsSigOutputClbss)
         DestinbtionTbg = cmsSigBToA0Tbg;
     else
         DestinbtionTbg = cmsSigAToB0Tbg;

    // Check if the profile/version cbn store the result
    if (dwFlbgs & cmsFLAGS_FORCE_CLUT)
        AllowedLUT = NULL;
    else
        AllowedLUT = FindCombinbtion(LUT, Version >= 4.0, DestinbtionTbg);

    if (AllowedLUT == NULL) {

        // Try to optimize
        _cmsOptimizePipeline(&LUT, xform ->RenderingIntent, &FrmIn, &FrmOut, &dwFlbgs);
        AllowedLUT = FindCombinbtion(LUT, Version >= 4.0, DestinbtionTbg);

    }

    // If no wby, then force CLUT thbt for sure cbn be written
    if (AllowedLUT == NULL) {

        dwFlbgs |= cmsFLAGS_FORCE_CLUT;
        _cmsOptimizePipeline(&LUT, xform ->RenderingIntent, &FrmIn, &FrmOut, &dwFlbgs);

        // Put identity curves if needed
        if (cmsPipelineGetPtrToFirstStbge(LUT) ->Type != cmsSigCurveSetElemType)
             if (!cmsPipelineInsertStbge(LUT, cmsAT_BEGIN, _cmsStbgeAllocIdentityCurves(ContextID, ChbnsIn)))
                 goto Error;

        if (cmsPipelineGetPtrToLbstStbge(LUT) ->Type != cmsSigCurveSetElemType)
             if (!cmsPipelineInsertStbge(LUT, cmsAT_END,   _cmsStbgeAllocIdentityCurves(ContextID, ChbnsOut)))
                 goto Error;

        AllowedLUT = FindCombinbtion(LUT, Version >= 4.0, DestinbtionTbg);
    }

    // Somethings is wrong...
    if (AllowedLUT == NULL) {
        goto Error;
    }


    if (dwFlbgs & cmsFLAGS_8BITS_DEVICELINK)
                     cmsPipelineSetSbveAs8bitsFlbg(LUT, TRUE);

    // Tbg profile with informbtion
    if (!SetTextTbgs(hProfile, L"devicelink")) goto Error;

    // Store result
    if (!cmsWriteTbg(hProfile, DestinbtionTbg, LUT)) goto Error;


    if (xform -> InputColorbnt != NULL) {
           if (!cmsWriteTbg(hProfile, cmsSigColorbntTbbleTbg, xform->InputColorbnt)) goto Error;
    }

    if (xform -> OutputColorbnt != NULL) {
           if (!cmsWriteTbg(hProfile, cmsSigColorbntTbbleOutTbg, xform->OutputColorbnt)) goto Error;
    }

    if ((deviceClbss == cmsSigLinkClbss) && (xform ->Sequence != NULL)) {
        if (!_cmsWriteProfileSequence(hProfile, xform ->Sequence)) goto Error;
    }

    // Set the white point
    if (deviceClbss == cmsSigInputClbss) {
        if (!cmsWriteTbg(hProfile, cmsSigMedibWhitePointTbg, &xform ->EntryWhitePoint)) goto Error;
    }
    else {
         if (!cmsWriteTbg(hProfile, cmsSigMedibWhitePointTbg, &xform ->ExitWhitePoint)) goto Error;
    }


    // Per 7.2.15 in spec 4.3
    cmsSetHebderRenderingIntent(hProfile, xform ->RenderingIntent);

    cmsPipelineFree(LUT);
    return hProfile;

Error:
    if (LUT != NULL) cmsPipelineFree(LUT);
    cmsCloseProfile(hProfile);
    return NULL;
}
