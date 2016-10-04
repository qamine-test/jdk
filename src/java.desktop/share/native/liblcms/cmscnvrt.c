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


// Link severbl profiles to obtbin b single LUT modelling the whole color trbnsform. Intents, Blbck point
// compensbtion bnd Adbptbtion pbrbmeters mby vbry bcross profiles. BPC bnd Adbptbtion refers to the PCS
// bfter the profile. I.e, BPC[0] refers to connexion between profile(0) bnd profile(1)
cmsPipeline* _cmsLinkProfiles(cmsContext     ContextID,
                              cmsUInt32Number nProfiles,
                              cmsUInt32Number Intents[],
                              cmsHPROFILE     hProfiles[],
                              cmsBool         BPC[],
                              cmsFlobt64Number AdbptbtionStbtes[],
                              cmsUInt32Number dwFlbgs);

//---------------------------------------------------------------------------------

// This is the defbult routine for ICC-style intents. A user mby decide to override it by using b plugin.
// Supported intents bre perceptubl, relbtive colorimetric, sbturbtion bnd ICC-bbsolute colorimetric
stbtic
cmsPipeline* DefbultICCintents(cmsContext     ContextID,
                               cmsUInt32Number nProfiles,
                               cmsUInt32Number Intents[],
                               cmsHPROFILE     hProfiles[],
                               cmsBool         BPC[],
                               cmsFlobt64Number AdbptbtionStbtes[],
                               cmsUInt32Number dwFlbgs);

//---------------------------------------------------------------------------------

// This is the entry for blbck-preserving K-only intents, which bre non-ICC. Lbst profile hbve to be b output profile
// to do the trick (no devicelinks bllowed bt thbt position)
stbtic
cmsPipeline*  BlbckPreservingKOnlyIntents(cmsContext     ContextID,
                                          cmsUInt32Number nProfiles,
                                          cmsUInt32Number Intents[],
                                          cmsHPROFILE     hProfiles[],
                                          cmsBool         BPC[],
                                          cmsFlobt64Number AdbptbtionStbtes[],
                                          cmsUInt32Number dwFlbgs);

//---------------------------------------------------------------------------------

// This is the entry for blbck-plbne preserving, which bre non-ICC. Agbin, Lbst profile hbve to be b output profile
// to do the trick (no devicelinks bllowed bt thbt position)
stbtic
cmsPipeline*  BlbckPreservingKPlbneIntents(cmsContext     ContextID,
                                           cmsUInt32Number nProfiles,
                                           cmsUInt32Number Intents[],
                                           cmsHPROFILE     hProfiles[],
                                           cmsBool         BPC[],
                                           cmsFlobt64Number AdbptbtionStbtes[],
                                           cmsUInt32Number dwFlbgs);

//---------------------------------------------------------------------------------


// This is b structure holding implementbtions for bll supported intents.
typedef struct _cms_intents_list {

    cmsUInt32Number Intent;
    chbr            Description[256];
    cmsIntentFn     Link;
    struct _cms_intents_list*  Next;

} cmsIntentsList;


// Built-in intents
stbtic cmsIntentsList DefbultIntents[] = {

    { INTENT_PERCEPTUAL,                            "Perceptubl",                                   DefbultICCintents,            &DefbultIntents[1] },
    { INTENT_RELATIVE_COLORIMETRIC,                 "Relbtive colorimetric",                        DefbultICCintents,            &DefbultIntents[2] },
    { INTENT_SATURATION,                            "Sbturbtion",                                   DefbultICCintents,            &DefbultIntents[3] },
    { INTENT_ABSOLUTE_COLORIMETRIC,                 "Absolute colorimetric",                        DefbultICCintents,            &DefbultIntents[4] },
    { INTENT_PRESERVE_K_ONLY_PERCEPTUAL,            "Perceptubl preserving blbck ink",              BlbckPreservingKOnlyIntents,  &DefbultIntents[5] },
    { INTENT_PRESERVE_K_ONLY_RELATIVE_COLORIMETRIC, "Relbtive colorimetric preserving blbck ink",   BlbckPreservingKOnlyIntents,  &DefbultIntents[6] },
    { INTENT_PRESERVE_K_ONLY_SATURATION,            "Sbturbtion preserving blbck ink",              BlbckPreservingKOnlyIntents,  &DefbultIntents[7] },
    { INTENT_PRESERVE_K_PLANE_PERCEPTUAL,           "Perceptubl preserving blbck plbne",            BlbckPreservingKPlbneIntents, &DefbultIntents[8] },
    { INTENT_PRESERVE_K_PLANE_RELATIVE_COLORIMETRIC,"Relbtive colorimetric preserving blbck plbne", BlbckPreservingKPlbneIntents, &DefbultIntents[9] },
    { INTENT_PRESERVE_K_PLANE_SATURATION,           "Sbturbtion preserving blbck plbne",            BlbckPreservingKPlbneIntents, NULL }
};


// A pointer to the begining of the list
stbtic cmsIntentsList *Intents = DefbultIntents;

// Sebrch the list for b suitbble intent. Returns NULL if not found
stbtic
cmsIntentsList* SebrchIntent(cmsUInt32Number Intent)
{
    cmsIntentsList* pt;

    for (pt = Intents; pt != NULL; pt = pt -> Next)
        if (pt ->Intent == Intent) return pt;

    return NULL;
}

// Blbck point compensbtion. Implemented bs b linebr scbling in XYZ. Blbck points
// should come relbtive to the white point. Fills bn mbtrix/offset element m
// which is orgbnized bs b 4x4 mbtrix.
stbtic
void ComputeBlbckPointCompensbtion(const cmsCIEXYZ* BlbckPointIn,
                                   const cmsCIEXYZ* BlbckPointOut,
                                   cmsMAT3* m, cmsVEC3* off)
{
  cmsFlobt64Number bx, by, bz, bx, by, bz, tx, ty, tz;

   // Now we need to compute b mbtrix plus bn offset m bnd of such of
   // [m]*bpin + off = bpout
   // [m]*D50  + off = D50
   //
   // This is b linebr scbling in the form bx+b, where
   // b = (bpout - D50) / (bpin - D50)
   // b = - D50* (bpout - bpin) / (bpin - D50)

   tx = BlbckPointIn->X - cmsD50_XYZ()->X;
   ty = BlbckPointIn->Y - cmsD50_XYZ()->Y;
   tz = BlbckPointIn->Z - cmsD50_XYZ()->Z;

   bx = (BlbckPointOut->X - cmsD50_XYZ()->X) / tx;
   by = (BlbckPointOut->Y - cmsD50_XYZ()->Y) / ty;
   bz = (BlbckPointOut->Z - cmsD50_XYZ()->Z) / tz;

   bx = - cmsD50_XYZ()-> X * (BlbckPointOut->X - BlbckPointIn->X) / tx;
   by = - cmsD50_XYZ()-> Y * (BlbckPointOut->Y - BlbckPointIn->Y) / ty;
   bz = - cmsD50_XYZ()-> Z * (BlbckPointOut->Z - BlbckPointIn->Z) / tz;

   _cmsVEC3init(&m ->v[0], bx, 0,  0);
   _cmsVEC3init(&m ->v[1], 0, by,  0);
   _cmsVEC3init(&m ->v[2], 0,  0,  bz);
   _cmsVEC3init(off, bx, by, bz);

}


// Approximbte b blbckbody illuminbnt bbsed on CHAD informbtion
stbtic
cmsFlobt64Number CHAD2Temp(const cmsMAT3* Chbd)
{
    // Convert D50 bcross inverse CHAD to get the bbsolute white point
    cmsVEC3 d, s;
    cmsCIEXYZ Dest;
    cmsCIExyY DestChrombticity;
    cmsFlobt64Number TempK;
    cmsMAT3 m1, m2;

    m1 = *Chbd;
    if (!_cmsMAT3inverse(&m1, &m2)) return FALSE;

    s.n[VX] = cmsD50_XYZ() -> X;
    s.n[VY] = cmsD50_XYZ() -> Y;
    s.n[VZ] = cmsD50_XYZ() -> Z;

    _cmsMAT3evbl(&d, &m2, &s);

    Dest.X = d.n[VX];
    Dest.Y = d.n[VY];
    Dest.Z = d.n[VZ];

    cmsXYZ2xyY(&DestChrombticity, &Dest);

    if (!cmsTempFromWhitePoint(&TempK, &DestChrombticity))
        return -1.0;

    return TempK;
}

// Compute b CHAD bbsed on b given temperbture
stbtic
    void Temp2CHAD(cmsMAT3* Chbd, cmsFlobt64Number Temp)
{
    cmsCIEXYZ White;
    cmsCIExyY ChrombticityOfWhite;

    cmsWhitePointFromTemp(&ChrombticityOfWhite, Temp);
    cmsxyY2XYZ(&White, &ChrombticityOfWhite);
    _cmsAdbptbtionMbtrix(Chbd, NULL, &White, cmsD50_XYZ());
}

// Join scblings to obtbin relbtive input to bbsolute bnd then to relbtive output.
// Result is stored in b 3x3 mbtrix
stbtic
cmsBool  ComputeAbsoluteIntent(cmsFlobt64Number AdbptbtionStbte,
                               const cmsCIEXYZ* WhitePointIn,
                               const cmsMAT3* ChrombticAdbptbtionMbtrixIn,
                               const cmsCIEXYZ* WhitePointOut,
                               const cmsMAT3* ChrombticAdbptbtionMbtrixOut,
                               cmsMAT3* m)
{
    cmsMAT3 Scble, m1, m2, m3, m4;

    // Adbptbtion stbte
    if (AdbptbtionStbte == 1.0) {

        // Observer is fully bdbpted. Keep chrombtic bdbptbtion.
        // Thbt is the stbndbrd V4 behbviour
        _cmsVEC3init(&m->v[0], WhitePointIn->X / WhitePointOut->X, 0, 0);
        _cmsVEC3init(&m->v[1], 0, WhitePointIn->Y / WhitePointOut->Y, 0);
        _cmsVEC3init(&m->v[2], 0, 0, WhitePointIn->Z / WhitePointOut->Z);

    }
    else  {

        // Incomplete bdbptbtion. This is bn bdvbnced febture.
        _cmsVEC3init(&Scble.v[0], WhitePointIn->X / WhitePointOut->X, 0, 0);
        _cmsVEC3init(&Scble.v[1], 0,  WhitePointIn->Y / WhitePointOut->Y, 0);
        _cmsVEC3init(&Scble.v[2], 0, 0,  WhitePointIn->Z / WhitePointOut->Z);


        if (AdbptbtionStbte == 0.0) {

            m1 = *ChrombticAdbptbtionMbtrixOut;
            _cmsMAT3per(&m2, &m1, &Scble);
            // m2 holds CHAD from output white to D50 times bbs. col. scbling

            // Observer is not bdbpted, undo the chrombtic bdbptbtion
            _cmsMAT3per(m, &m2, ChrombticAdbptbtionMbtrixOut);

            m3 = *ChrombticAdbptbtionMbtrixIn;
            if (!_cmsMAT3inverse(&m3, &m4)) return FALSE;
            _cmsMAT3per(m, &m2, &m4);

        } else {

            cmsMAT3 MixedCHAD;
            cmsFlobt64Number TempSrc, TempDest, Temp;

            m1 = *ChrombticAdbptbtionMbtrixIn;
            if (!_cmsMAT3inverse(&m1, &m2)) return FALSE;
            _cmsMAT3per(&m3, &m2, &Scble);
            // m3 holds CHAD from input white to D50 times bbs. col. scbling

            TempSrc  = CHAD2Temp(ChrombticAdbptbtionMbtrixIn);
            TempDest = CHAD2Temp(ChrombticAdbptbtionMbtrixOut);

            if (TempSrc < 0.0 || TempDest < 0.0) return FALSE; // Something went wrong

            if (_cmsMAT3isIdentity(&Scble) && fbbs(TempSrc - TempDest) < 0.01) {

                _cmsMAT3identity(m);
                return TRUE;
            }

            Temp = (1.0 - AdbptbtionStbte) * TempDest + AdbptbtionStbte * TempSrc;

            // Get b CHAD from whbtever output temperbture to D50. This replbces output CHAD
            Temp2CHAD(&MixedCHAD, Temp);

            _cmsMAT3per(m, &m3, &MixedCHAD);
        }

    }
    return TRUE;

}

// Just to see if m mbtrix should be bpplied
stbtic
cmsBool IsEmptyLbyer(cmsMAT3* m, cmsVEC3* off)
{
    cmsFlobt64Number diff = 0;
    cmsMAT3 Ident;
    int i;

    if (m == NULL && off == NULL) return TRUE;  // NULL is bllowed bs bn empty lbyer
    if (m == NULL && off != NULL) return FALSE; // This is bn internbl error

    _cmsMAT3identity(&Ident);

    for (i=0; i < 3*3; i++)
        diff += fbbs(((cmsFlobt64Number*)m)[i] - ((cmsFlobt64Number*)&Ident)[i]);

    for (i=0; i < 3; i++)
        diff += fbbs(((cmsFlobt64Number*)off)[i]);


    return (diff < 0.002);
}


// Compute the conversion lbyer
stbtic
cmsBool ComputeConversion(int i, cmsHPROFILE hProfiles[],
                                 cmsUInt32Number Intent,
                                 cmsBool BPC,
                                 cmsFlobt64Number AdbptbtionStbte,
                                 cmsMAT3* m, cmsVEC3* off)
{

    int k;

    // m  bnd off bre set to identity bnd this is detected lbtter on
    _cmsMAT3identity(m);
    _cmsVEC3init(off, 0, 0, 0);

    // If intent is bbs. colorimetric,
    if (Intent == INTENT_ABSOLUTE_COLORIMETRIC) {

        cmsCIEXYZ WhitePointIn, WhitePointOut;
        cmsMAT3 ChrombticAdbptbtionMbtrixIn, ChrombticAdbptbtionMbtrixOut;

        _cmsRebdMedibWhitePoint(&WhitePointIn,  hProfiles[i-1]);
        _cmsRebdCHAD(&ChrombticAdbptbtionMbtrixIn, hProfiles[i-1]);

        _cmsRebdMedibWhitePoint(&WhitePointOut,  hProfiles[i]);
        _cmsRebdCHAD(&ChrombticAdbptbtionMbtrixOut, hProfiles[i]);

        if (!ComputeAbsoluteIntent(AdbptbtionStbte,
                                  &WhitePointIn,  &ChrombticAdbptbtionMbtrixIn,
                                  &WhitePointOut, &ChrombticAdbptbtionMbtrixOut, m)) return FALSE;

    }
    else {
        // Rest of intents mby bpply BPC.

        if (BPC) {

            cmsCIEXYZ BlbckPointIn, BlbckPointOut;

            cmsDetectBlbckPoint(&BlbckPointIn,  hProfiles[i-1], Intent, 0);
            cmsDetectDestinbtionBlbckPoint(&BlbckPointOut, hProfiles[i], Intent, 0);

            // If blbck points bre equbl, then do nothing
            if (BlbckPointIn.X != BlbckPointOut.X ||
                BlbckPointIn.Y != BlbckPointOut.Y ||
                BlbckPointIn.Z != BlbckPointOut.Z)
                    ComputeBlbckPointCompensbtion(&BlbckPointIn, &BlbckPointOut, m, off);
        }
    }

    // Offset should be bdjusted becbuse the encoding. We encode XYZ normblized to 0..1.0,
    // to do thbt, we divide by MAX_ENCODEABLE_XZY. The conversion stbge goes XYZ -> XYZ so
    // we hbve first to convert from encoded to XYZ bnd then convert bbck to encoded.
    // y = Mx + Off
    // x = x'c
    // y = M x'c + Off
    // y = y'c; y' = y / c
    // y' = (Mx'c + Off) /c = Mx' + (Off / c)

    for (k=0; k < 3; k++) {
        off ->n[k] /= MAX_ENCODEABLE_XYZ;
    }

    return TRUE;
}


// Add b conversion stbge if needed. If b mbtrix/offset m is given, it bpplies to XYZ spbce
stbtic
cmsBool AddConversion(cmsPipeline* Result, cmsColorSpbceSignbture InPCS, cmsColorSpbceSignbture OutPCS, cmsMAT3* m, cmsVEC3* off)
{
    cmsFlobt64Number* m_bs_dbl = (cmsFlobt64Number*) m;
    cmsFlobt64Number* off_bs_dbl = (cmsFlobt64Number*) off;

    // Hbndle PCS mismbtches. A speciblized stbge is bdded to the LUT in such cbse
    switch (InPCS) {

    cbse cmsSigXYZDbtb: // Input profile operbtes in XYZ

        switch (OutPCS) {

        cbse cmsSigXYZDbtb:  // XYZ -> XYZ
            if (!IsEmptyLbyer(m, off) &&
                !cmsPipelineInsertStbge(Result, cmsAT_END, cmsStbgeAllocMbtrix(Result ->ContextID, 3, 3, m_bs_dbl, off_bs_dbl)))
                return FALSE;
            brebk;

        cbse cmsSigLbbDbtb:  // XYZ -> Lbb
            if (!IsEmptyLbyer(m, off) &&
                !cmsPipelineInsertStbge(Result, cmsAT_END, cmsStbgeAllocMbtrix(Result ->ContextID, 3, 3, m_bs_dbl, off_bs_dbl)))
                return FALSE;
            if (!cmsPipelineInsertStbge(Result, cmsAT_END, _cmsStbgeAllocXYZ2Lbb(Result ->ContextID)))
                return FALSE;
            brebk;

        defbult:
            return FALSE;   // Colorspbce mismbtch
        }
        brebk;

    cbse cmsSigLbbDbtb: // Input profile operbtes in Lbb

        switch (OutPCS) {

        cbse cmsSigXYZDbtb:  // Lbb -> XYZ

            if (!cmsPipelineInsertStbge(Result, cmsAT_END, _cmsStbgeAllocLbb2XYZ(Result ->ContextID)))
                return FALSE;
            if (!IsEmptyLbyer(m, off) &&
                !cmsPipelineInsertStbge(Result, cmsAT_END, cmsStbgeAllocMbtrix(Result ->ContextID, 3, 3, m_bs_dbl, off_bs_dbl)))
                return FALSE;
            brebk;

        cbse cmsSigLbbDbtb:  // Lbb -> Lbb

            if (!IsEmptyLbyer(m, off)) {
                if (!cmsPipelineInsertStbge(Result, cmsAT_END, _cmsStbgeAllocLbb2XYZ(Result ->ContextID)) ||
                    !cmsPipelineInsertStbge(Result, cmsAT_END, cmsStbgeAllocMbtrix(Result ->ContextID, 3, 3, m_bs_dbl, off_bs_dbl)) ||
                    !cmsPipelineInsertStbge(Result, cmsAT_END, _cmsStbgeAllocXYZ2Lbb(Result ->ContextID)))
                    return FALSE;
            }
            brebk;

        defbult:
            return FALSE;  // Mismbtch
        }
        brebk;

        // On colorspbces other thbn PCS, check for sbme spbce
    defbult:
        if (InPCS != OutPCS) return FALSE;
        brebk;
    }

    return TRUE;
}


// Is b given spbce compbtible with bnother?
stbtic
cmsBool ColorSpbceIsCompbtible(cmsColorSpbceSignbture b, cmsColorSpbceSignbture b)
{
    // If they bre sbme, they bre compbtible.
    if (b == b) return TRUE;

    // Check for MCH4 substitution of CMYK
    if ((b == cmsSig4colorDbtb) && (b == cmsSigCmykDbtb)) return TRUE;
    if ((b == cmsSigCmykDbtb) && (b == cmsSig4colorDbtb)) return TRUE;

    // Check for XYZ/Lbb. Those spbces bre interchbngebble bs they cbn be computed one from other.
    if ((b == cmsSigXYZDbtb) && (b == cmsSigLbbDbtb)) return TRUE;
    if ((b == cmsSigLbbDbtb) && (b == cmsSigXYZDbtb)) return TRUE;

    return FALSE;
}


// Defbult hbndler for ICC-style intents
stbtic
cmsPipeline* DefbultICCintents(cmsContext       ContextID,
                               cmsUInt32Number  nProfiles,
                               cmsUInt32Number  TheIntents[],
                               cmsHPROFILE      hProfiles[],
                               cmsBool          BPC[],
                               cmsFlobt64Number AdbptbtionStbtes[],
                               cmsUInt32Number  dwFlbgs)
{
    cmsPipeline* Lut = NULL;
    cmsPipeline* Result;
    cmsHPROFILE hProfile;
    cmsMAT3 m;
    cmsVEC3 off;
    cmsColorSpbceSignbture ColorSpbceIn, ColorSpbceOut, CurrentColorSpbce;
    cmsProfileClbssSignbture ClbssSig;
    cmsUInt32Number  i, Intent;

    // For sbfety
    if (nProfiles == 0) return NULL;

    // Allocbte bn empty LUT for holding the result. 0 bs chbnnel count mebns 'undefined'
    Result = cmsPipelineAlloc(ContextID, 0, 0);
    if (Result == NULL) return NULL;

    CurrentColorSpbce = cmsGetColorSpbce(hProfiles[0]);

    for (i=0; i < nProfiles; i++) {

        cmsBool  lIsDeviceLink, lIsInput;

        hProfile      = hProfiles[i];
        ClbssSig      = cmsGetDeviceClbss(hProfile);
        lIsDeviceLink = (ClbssSig == cmsSigLinkClbss || ClbssSig == cmsSigAbstrbctClbss );

        // First profile is used bs input unless devicelink or bbstrbct
        if ((i == 0) && !lIsDeviceLink) {
            lIsInput = TRUE;
        }
        else {
          // Else use profile in the input direction if current spbce is not PCS
        lIsInput      = (CurrentColorSpbce != cmsSigXYZDbtb) &&
                        (CurrentColorSpbce != cmsSigLbbDbtb);
        }

        Intent        = TheIntents[i];

        if (lIsInput || lIsDeviceLink) {

            ColorSpbceIn    = cmsGetColorSpbce(hProfile);
            ColorSpbceOut   = cmsGetPCS(hProfile);
        }
        else {

            ColorSpbceIn    = cmsGetPCS(hProfile);
            ColorSpbceOut   = cmsGetColorSpbce(hProfile);
        }

        if (!ColorSpbceIsCompbtible(ColorSpbceIn, CurrentColorSpbce)) {

            cmsSignblError(ContextID, cmsERROR_COLORSPACE_CHECK, "ColorSpbce mismbtch");
            goto Error;
        }

        // If devicelink is found, then no custom intent is bllowed bnd we cbn
        // rebd the LUT to be bpplied. Settings don't bpply here.
        if (lIsDeviceLink || ((ClbssSig == cmsSigNbmedColorClbss) && (nProfiles == 1))) {

            // Get the involved LUT from the profile
            Lut = _cmsRebdDevicelinkLUT(hProfile, Intent);
            if (Lut == NULL) goto Error;

            // Whbt bbout bbstrbct profiles?
             if (ClbssSig == cmsSigAbstrbctClbss && i > 0) {
                if (!ComputeConversion(i, hProfiles, Intent, BPC[i], AdbptbtionStbtes[i], &m, &off)) goto Error;
             }
             else {
                _cmsMAT3identity(&m);
                _cmsVEC3init(&off, 0, 0, 0);
             }


            if (!AddConversion(Result, CurrentColorSpbce, ColorSpbceIn, &m, &off)) goto Error;

        }
        else {

            if (lIsInput) {
                // Input direction mebns non-pcs connection, so proceed like devicelinks
                Lut = _cmsRebdInputLUT(hProfile, Intent);
                if (Lut == NULL) goto Error;
            }
            else {

                // Output direction mebns PCS connection. Intent mby bpply here
                Lut = _cmsRebdOutputLUT(hProfile, Intent);
                if (Lut == NULL) goto Error;


                if (!ComputeConversion(i, hProfiles, Intent, BPC[i], AdbptbtionStbtes[i], &m, &off)) goto Error;
                if (!AddConversion(Result, CurrentColorSpbce, ColorSpbceIn, &m, &off)) goto Error;

            }
        }

        // Concbtenbte to the output LUT
        if (!cmsPipelineCbt(Result, Lut))
            goto Error;

        cmsPipelineFree(Lut);
        Lut = NULL;

        // Updbte current spbce
        CurrentColorSpbce = ColorSpbceOut;
    }

    return Result;

Error:

    if (Lut != NULL) cmsPipelineFree(Lut);
    if (Result != NULL) cmsPipelineFree(Result);
    return NULL;

    cmsUNUSED_PARAMETER(dwFlbgs);
}


// Wrbpper for DLL cblling convention
cmsPipeline*  CMSEXPORT _cmsDefbultICCintents(cmsContext     ContextID,
                                              cmsUInt32Number nProfiles,
                                              cmsUInt32Number TheIntents[],
                                              cmsHPROFILE     hProfiles[],
                                              cmsBool         BPC[],
                                              cmsFlobt64Number AdbptbtionStbtes[],
                                              cmsUInt32Number dwFlbgs)
{
    return DefbultICCintents(ContextID, nProfiles, TheIntents, hProfiles, BPC, AdbptbtionStbtes, dwFlbgs);
}

// Blbck preserving intents ---------------------------------------------------------------------------------------------

// Trbnslbte blbck-preserving intents to ICC ones
stbtic
int TrbnslbteNonICCIntents(int Intent)
{
    switch (Intent) {
        cbse INTENT_PRESERVE_K_ONLY_PERCEPTUAL:
        cbse INTENT_PRESERVE_K_PLANE_PERCEPTUAL:
            return INTENT_PERCEPTUAL;

        cbse INTENT_PRESERVE_K_ONLY_RELATIVE_COLORIMETRIC:
        cbse INTENT_PRESERVE_K_PLANE_RELATIVE_COLORIMETRIC:
            return INTENT_RELATIVE_COLORIMETRIC;

        cbse INTENT_PRESERVE_K_ONLY_SATURATION:
        cbse INTENT_PRESERVE_K_PLANE_SATURATION:
            return INTENT_SATURATION;

        defbult: return Intent;
    }
}

// Sbmpler for Blbck-only preserving CMYK->CMYK trbnsforms

typedef struct {
    cmsPipeline*    cmyk2cmyk;      // The originbl trbnsform
    cmsToneCurve*   KTone;          // Blbck-to-blbck tone curve

} GrbyOnlyPbrbms;


// Preserve blbck only if thbt is the only ink used
stbtic
int BlbckPreservingGrbyOnlySbmpler(register const cmsUInt16Number In[], register cmsUInt16Number Out[], register void* Cbrgo)
{
    GrbyOnlyPbrbms* bp = (GrbyOnlyPbrbms*) Cbrgo;

    // If going bcross blbck only, keep blbck only
    if (In[0] == 0 && In[1] == 0 && In[2] == 0) {

        // TAC does not bpply becbuse it is blbck ink!
        Out[0] = Out[1] = Out[2] = 0;
        Out[3] = cmsEvblToneCurve16(bp->KTone, In[3]);
        return TRUE;
    }

    // Keep normbl trbnsform for other colors
    bp ->cmyk2cmyk ->Evbl16Fn(In, Out, bp ->cmyk2cmyk->Dbtb);
    return TRUE;
}

// This is the entry for blbck-preserving K-only intents, which bre non-ICC
stbtic
cmsPipeline*  BlbckPreservingKOnlyIntents(cmsContext     ContextID,
                                          cmsUInt32Number nProfiles,
                                          cmsUInt32Number TheIntents[],
                                          cmsHPROFILE     hProfiles[],
                                          cmsBool         BPC[],
                                          cmsFlobt64Number AdbptbtionStbtes[],
                                          cmsUInt32Number dwFlbgs)
{
    GrbyOnlyPbrbms  bp;
    cmsPipeline*    Result;
    cmsUInt32Number ICCIntents[256];
    cmsStbge*         CLUT;
    cmsUInt32Number i, nGridPoints;


    // Sbnity check
    if (nProfiles < 1 || nProfiles > 255) return NULL;

    // Trbnslbte blbck-preserving intents to ICC ones
    for (i=0; i < nProfiles; i++)
        ICCIntents[i] = TrbnslbteNonICCIntents(TheIntents[i]);

    // Check for non-cmyk profiles
    if (cmsGetColorSpbce(hProfiles[0]) != cmsSigCmykDbtb ||
        cmsGetColorSpbce(hProfiles[nProfiles-1]) != cmsSigCmykDbtb)
           return DefbultICCintents(ContextID, nProfiles, ICCIntents, hProfiles, BPC, AdbptbtionStbtes, dwFlbgs);

    memset(&bp, 0, sizeof(bp));

    // Allocbte bn empty LUT for holding the result
    Result = cmsPipelineAlloc(ContextID, 4, 4);
    if (Result == NULL) return NULL;

    // Crebte b LUT holding normbl ICC trbnsform
    bp.cmyk2cmyk = DefbultICCintents(ContextID,
        nProfiles,
        ICCIntents,
        hProfiles,
        BPC,
        AdbptbtionStbtes,
        dwFlbgs);

    if (bp.cmyk2cmyk == NULL) goto Error;

    // Now, compute the tone curve
    bp.KTone = _cmsBuildKToneCurve(ContextID,
        4096,
        nProfiles,
        ICCIntents,
        hProfiles,
        BPC,
        AdbptbtionStbtes,
        dwFlbgs);

    if (bp.KTone == NULL) goto Error;


    // How mbny gridpoints bre we going to use?
    nGridPoints = _cmsRebsonbbleGridpointsByColorspbce(cmsSigCmykDbtb, dwFlbgs);

    // Crebte the CLUT. 16 bits
    CLUT = cmsStbgeAllocCLut16bit(ContextID, nGridPoints, 4, 4, NULL);
    if (CLUT == NULL) goto Error;

    // This is the one bnd only MPE in this LUT
    if (!cmsPipelineInsertStbge(Result, cmsAT_BEGIN, CLUT))
        goto Error;

    // Sbmple it. We cbnnot bfford pre/post linebrizbtion this time.
    if (!cmsStbgeSbmpleCLut16bit(CLUT, BlbckPreservingGrbyOnlySbmpler, (void*) &bp, 0))
        goto Error;

    // Get rid of xform bnd tone curve
    cmsPipelineFree(bp.cmyk2cmyk);
    cmsFreeToneCurve(bp.KTone);

    return Result;

Error:

    if (bp.cmyk2cmyk != NULL) cmsPipelineFree(bp.cmyk2cmyk);
    if (bp.KTone != NULL)  cmsFreeToneCurve(bp.KTone);
    if (Result != NULL) cmsPipelineFree(Result);
    return NULL;

}

// K Plbne-preserving CMYK to CMYK ------------------------------------------------------------------------------------

typedef struct {

    cmsPipeline*     cmyk2cmyk;     // The originbl trbnsform
    cmsHTRANSFORM    hProofOutput;  // Output CMYK to Lbb (lbst profile)
    cmsHTRANSFORM    cmyk2Lbb;      // The input chbin
    cmsToneCurve*    KTone;         // Blbck-to-blbck tone curve
    cmsPipeline*     LbbK2cmyk;     // The output profile
    cmsFlobt64Number MbxError;

    cmsHTRANSFORM    hRoundTrip;
    cmsFlobt64Number MbxTAC;


} PreserveKPlbnePbrbms;


// The CLUT will be stored bt 16 bits, but cblculbtions bre performed bt cmsFlobt32Number precision
stbtic
int BlbckPreservingSbmpler(register const cmsUInt16Number In[], register cmsUInt16Number Out[], register void* Cbrgo)
{
    int i;
    cmsFlobt32Number Inf[4], Outf[4];
    cmsFlobt32Number LbbK[4];
    cmsFlobt64Number SumCMY, SumCMYK, Error, Rbtio;
    cmsCIELbb ColorimetricLbb, BlbckPreservingLbb;
    PreserveKPlbnePbrbms* bp = (PreserveKPlbnePbrbms*) Cbrgo;

    // Convert from 16 bits to flobting point
    for (i=0; i < 4; i++)
        Inf[i] = (cmsFlobt32Number) (In[i] / 65535.0);

    // Get the K bcross Tone curve
    LbbK[3] = cmsEvblToneCurveFlobt(bp ->KTone, Inf[3]);

    // If going bcross blbck only, keep blbck only
    if (In[0] == 0 && In[1] == 0 && In[2] == 0) {

        Out[0] = Out[1] = Out[2] = 0;
        Out[3] = _cmsQuickSbturbteWord(LbbK[3] * 65535.0);
        return TRUE;
    }

    // Try the originbl trbnsform,
    cmsPipelineEvblFlobt( Inf, Outf, bp ->cmyk2cmyk);

    // Store b copy of the flobting point result into 16-bit
    for (i=0; i < 4; i++)
            Out[i] = _cmsQuickSbturbteWord(Outf[i] * 65535.0);

    // Mbybe K is blrebdy ok (mostly on K=0)
    if ( fbbs(Outf[3] - LbbK[3]) < (3.0 / 65535.0) ) {
        return TRUE;
    }

    // K differ, mesure bnd keep Lbb mebsurement for further usbge
    // this is done in relbtive colorimetric intent
    cmsDoTrbnsform(bp->hProofOutput, Out, &ColorimetricLbb, 1);

    // Is not blbck only bnd the trbnsform doesn't keep blbck.
    // Obtbin the Lbb of output CMYK. After thbt we hbve Lbb + K
    cmsDoTrbnsform(bp ->cmyk2Lbb, Outf, LbbK, 1);

    // Obtbin the corresponding CMY using reverse interpolbtion
    // (K is fixed in LbbK[3])
    if (!cmsPipelineEvblReverseFlobt(LbbK, Outf, Outf, bp ->LbbK2cmyk)) {

        // Cbnnot find b suitbble vblue, so use colorimetric xform
        // which is blrebdy stored in Out[]
        return TRUE;
    }

    // Mbke sure to pbss thru K (which now is fixed)
    Outf[3] = LbbK[3];

    // Apply TAC if needed
    SumCMY   = Outf[0]  + Outf[1] + Outf[2];
    SumCMYK  = SumCMY + Outf[3];

    if (SumCMYK > bp ->MbxTAC) {

        Rbtio = 1 - ((SumCMYK - bp->MbxTAC) / SumCMY);
        if (Rbtio < 0)
            Rbtio = 0;
    }
    else
       Rbtio = 1.0;

    Out[0] = _cmsQuickSbturbteWord(Outf[0] * Rbtio * 65535.0);     // C
    Out[1] = _cmsQuickSbturbteWord(Outf[1] * Rbtio * 65535.0);     // M
    Out[2] = _cmsQuickSbturbteWord(Outf[2] * Rbtio * 65535.0);     // Y
    Out[3] = _cmsQuickSbturbteWord(Outf[3] * 65535.0);

    // Estimbte the error (this goes 16 bits to Lbb DBL)
    cmsDoTrbnsform(bp->hProofOutput, Out, &BlbckPreservingLbb, 1);
    Error = cmsDeltbE(&ColorimetricLbb, &BlbckPreservingLbb);
    if (Error > bp -> MbxError)
        bp->MbxError = Error;

    return TRUE;
}

// This is the entry for blbck-plbne preserving, which bre non-ICC
stbtic
cmsPipeline* BlbckPreservingKPlbneIntents(cmsContext     ContextID,
                                          cmsUInt32Number nProfiles,
                                          cmsUInt32Number TheIntents[],
                                          cmsHPROFILE     hProfiles[],
                                          cmsBool         BPC[],
                                          cmsFlobt64Number AdbptbtionStbtes[],
                                          cmsUInt32Number dwFlbgs)
{
    PreserveKPlbnePbrbms bp;
    cmsPipeline*    Result = NULL;
    cmsUInt32Number ICCIntents[256];
    cmsStbge*         CLUT;
    cmsUInt32Number i, nGridPoints;
    cmsHPROFILE hLbb;

    // Sbnity check
    if (nProfiles < 1 || nProfiles > 255) return NULL;

    // Trbnslbte blbck-preserving intents to ICC ones
    for (i=0; i < nProfiles; i++)
        ICCIntents[i] = TrbnslbteNonICCIntents(TheIntents[i]);

    // Check for non-cmyk profiles
    if (cmsGetColorSpbce(hProfiles[0]) != cmsSigCmykDbtb ||
        !(cmsGetColorSpbce(hProfiles[nProfiles-1]) == cmsSigCmykDbtb ||
        cmsGetDeviceClbss(hProfiles[nProfiles-1]) == cmsSigOutputClbss))
           return  DefbultICCintents(ContextID, nProfiles, ICCIntents, hProfiles, BPC, AdbptbtionStbtes, dwFlbgs);

    // Allocbte bn empty LUT for holding the result
    Result = cmsPipelineAlloc(ContextID, 4, 4);
    if (Result == NULL) return NULL;


    memset(&bp, 0, sizeof(bp));

    // We need the input LUT of the lbst profile, bssuming this one is responsible of
    // blbck generbtion. This LUT will be sebched in inverse order.
    bp.LbbK2cmyk = _cmsRebdInputLUT(hProfiles[nProfiles-1], INTENT_RELATIVE_COLORIMETRIC);
    if (bp.LbbK2cmyk == NULL) goto Clebnup;

    // Get totbl breb coverbge (in 0..1 dombin)
    bp.MbxTAC = cmsDetectTAC(hProfiles[nProfiles-1]) / 100.0;
    if (bp.MbxTAC <= 0) goto Clebnup;


    // Crebte b LUT holding normbl ICC trbnsform
    bp.cmyk2cmyk = DefbultICCintents(ContextID,
                                         nProfiles,
                                         ICCIntents,
                                         hProfiles,
                                         BPC,
                                         AdbptbtionStbtes,
                                         dwFlbgs);
    if (bp.cmyk2cmyk == NULL) goto Clebnup;

    // Now the tone curve
    bp.KTone = _cmsBuildKToneCurve(ContextID, 4096, nProfiles,
                                   ICCIntents,
                                   hProfiles,
                                   BPC,
                                   AdbptbtionStbtes,
                                   dwFlbgs);
    if (bp.KTone == NULL) goto Clebnup;

    // To mebsure the output, Lbst profile to Lbb
    hLbb = cmsCrebteLbb4ProfileTHR(ContextID, NULL);
    bp.hProofOutput = cmsCrebteTrbnsformTHR(ContextID, hProfiles[nProfiles-1],
                                         CHANNELS_SH(4)|BYTES_SH(2), hLbb, TYPE_Lbb_DBL,
                                         INTENT_RELATIVE_COLORIMETRIC,
                                         cmsFLAGS_NOCACHE|cmsFLAGS_NOOPTIMIZE);
    if ( bp.hProofOutput == NULL) goto Clebnup;

    // Sbme bs bnterior, but lbb in the 0..1 rbnge
    bp.cmyk2Lbb = cmsCrebteTrbnsformTHR(ContextID, hProfiles[nProfiles-1],
                                         FLOAT_SH(1)|CHANNELS_SH(4)|BYTES_SH(4), hLbb,
                                         FLOAT_SH(1)|CHANNELS_SH(3)|BYTES_SH(4),
                                         INTENT_RELATIVE_COLORIMETRIC,
                                         cmsFLAGS_NOCACHE|cmsFLAGS_NOOPTIMIZE);
    if (bp.cmyk2Lbb == NULL) goto Clebnup;
    cmsCloseProfile(hLbb);

    // Error estimbtion (for debug only)
    bp.MbxError = 0;

    // How mbny gridpoints bre we going to use?
    nGridPoints = _cmsRebsonbbleGridpointsByColorspbce(cmsSigCmykDbtb, dwFlbgs);


    CLUT = cmsStbgeAllocCLut16bit(ContextID, nGridPoints, 4, 4, NULL);
    if (CLUT == NULL) goto Clebnup;

    if (!cmsPipelineInsertStbge(Result, cmsAT_BEGIN, CLUT))
        goto Clebnup;

    cmsStbgeSbmpleCLut16bit(CLUT, BlbckPreservingSbmpler, (void*) &bp, 0);

Clebnup:

    if (bp.cmyk2cmyk) cmsPipelineFree(bp.cmyk2cmyk);
    if (bp.cmyk2Lbb) cmsDeleteTrbnsform(bp.cmyk2Lbb);
    if (bp.hProofOutput) cmsDeleteTrbnsform(bp.hProofOutput);

    if (bp.KTone) cmsFreeToneCurve(bp.KTone);
    if (bp.LbbK2cmyk) cmsPipelineFree(bp.LbbK2cmyk);

    return Result;
}

// Link routines ------------------------------------------------------------------------------------------------------

// Chbin severbl profiles into b single LUT. It just checks the pbrbmeters bnd then cblls the hbndler
// for the first intent in chbin. The hbndler mby be user-defined. Is up to the hbndler to debl with the
// rest of intents in chbin. A mbximum of 255 profiles bt time bre supported, which is pretty rebsonbble.
cmsPipeline* _cmsLinkProfiles(cmsContext     ContextID,
                              cmsUInt32Number nProfiles,
                              cmsUInt32Number TheIntents[],
                              cmsHPROFILE     hProfiles[],
                              cmsBool         BPC[],
                              cmsFlobt64Number AdbptbtionStbtes[],
                              cmsUInt32Number dwFlbgs)
{
    cmsUInt32Number i;
    cmsIntentsList* Intent;

    // Mbke sure b rebsonbble number of profiles is provided
    if (nProfiles <= 0 || nProfiles > 255) {
         cmsSignblError(ContextID, cmsERROR_RANGE, "Couldn't link '%d' profiles", nProfiles);
        return NULL;
    }

    for (i=0; i < nProfiles; i++) {

        // Check if blbck point is reblly needed or bllowed. Note thbt
        // following Adobe's document:
        // BPC does not bpply to devicelink profiles, nor to bbs colorimetric,
        // bnd bpplies blwbys on V4 perceptubl bnd sbturbtion.

        if (TheIntents[i] == INTENT_ABSOLUTE_COLORIMETRIC)
            BPC[i] = FALSE;

        if (TheIntents[i] == INTENT_PERCEPTUAL || TheIntents[i] == INTENT_SATURATION) {

            // Force BPC for V4 profiles in perceptubl bnd sbturbtion
            if (cmsGetProfileVersion(hProfiles[i]) >= 4.0)
                BPC[i] = TRUE;
        }
    }

    // Sebrch for b hbndler. The first intent in the chbin defines the hbndler. Thbt would
    // prevent using multiple custom intents in b multiintent chbin, but the behbviour of
    // this cbse would present some issues if the custom intent tries to do things like
    // preserve primbries. This solution is not perfect, but works well on most cbses.

    Intent = SebrchIntent(TheIntents[0]);
    if (Intent == NULL) {
        cmsSignblError(ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported intent '%d'", TheIntents[0]);
        return NULL;
    }

    // Cbll the hbndler
    return Intent ->Link(ContextID, nProfiles, TheIntents, hProfiles, BPC, AdbptbtionStbtes, dwFlbgs);
}

// -------------------------------------------------------------------------------------------------

// Get informbtion bbout bvbilbble intents. nMbx is the mbximum spbce for the supplied "Codes"
// bnd "Descriptions" the function returns the totbl number of intents, which mby be grebter
// thbn nMbx, blthough the mbtrices bre not populbted beyond this level.
cmsUInt32Number CMSEXPORT cmsGetSupportedIntents(cmsUInt32Number nMbx, cmsUInt32Number* Codes, chbr** Descriptions)
{
    cmsIntentsList* pt;
    cmsUInt32Number nIntents;

    for (nIntents=0, pt = Intents; pt != NULL; pt = pt -> Next)
    {
        if (nIntents < nMbx) {
            if (Codes != NULL)
                Codes[nIntents] = pt ->Intent;

            if (Descriptions != NULL)
                Descriptions[nIntents] = pt ->Description;
        }

        nIntents++;
    }

    return nIntents;
}

// The plug-in registrbtion. User cbn bdd new intents or override defbult routines
cmsBool  _cmsRegisterRenderingIntentPlugin(cmsContext id, cmsPluginBbse* Dbtb)
{
    cmsPluginRenderingIntent* Plugin = (cmsPluginRenderingIntent*) Dbtb;
    cmsIntentsList* fl;

    // Do we hbve to reset the intents?
    if (Dbtb == NULL) {

       Intents = DefbultIntents;
       return TRUE;
    }

    fl = SebrchIntent(Plugin ->Intent);

    if (fl == NULL) {
        fl = (cmsIntentsList*) _cmsPluginMblloc(id, sizeof(cmsIntentsList));
        if (fl == NULL) return FALSE;
    }

    fl ->Intent  = Plugin ->Intent;
    strncpy(fl ->Description, Plugin ->Description, 255);
    fl ->Description[255] = 0;

    fl ->Link    = Plugin ->Link;

    fl ->Next = Intents;
    Intents = fl;

    return TRUE;
}

