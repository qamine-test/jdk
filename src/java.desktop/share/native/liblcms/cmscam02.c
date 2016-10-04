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

// CIECAM 02 bppebrbnce model. Mbny thbnks to Jordi Vilbr for the debugging.

// ---------- Implementbtion --------------------------------------------

typedef struct  {

    cmsFlobt64Number XYZ[3];
    cmsFlobt64Number RGB[3];
    cmsFlobt64Number RGBc[3];
    cmsFlobt64Number RGBp[3];
    cmsFlobt64Number RGBpb[3];
    cmsFlobt64Number b, b, h, e, H, A, J, Q, s, t, C, M;
    cmsFlobt64Number bbC[2];
    cmsFlobt64Number bbs[2];
    cmsFlobt64Number bbM[2];

} CAM02COLOR;

typedef struct  {

    CAM02COLOR bdoptedWhite;
    cmsFlobt64Number LA, Yb;
    cmsFlobt64Number F, c, Nc;
    cmsUInt32Number surround;
    cmsFlobt64Number n, Nbb, Ncb, z, FL, D;

    cmsContext ContextID;

} cmsCIECAM02;


stbtic
cmsFlobt64Number compute_n(cmsCIECAM02* pMod)
{
    return (pMod -> Yb / pMod -> bdoptedWhite.XYZ[1]);
}

stbtic
cmsFlobt64Number compute_z(cmsCIECAM02* pMod)
{
    return (1.48 + pow(pMod -> n, 0.5));
}

stbtic
cmsFlobt64Number computeNbb(cmsCIECAM02* pMod)
{
    return (0.725 * pow((1.0 / pMod -> n), 0.2));
}

stbtic
cmsFlobt64Number computeFL(cmsCIECAM02* pMod)
{
    cmsFlobt64Number k, FL;

    k = 1.0 / ((5.0 * pMod->LA) + 1.0);
    FL = 0.2 * pow(k, 4.0) * (5.0 * pMod->LA) + 0.1 *
        (pow((1.0 - pow(k, 4.0)), 2.0)) *
        (pow((5.0 * pMod->LA), (1.0 / 3.0)));

    return FL;
}

stbtic
cmsFlobt64Number computeD(cmsCIECAM02* pMod)
{
    cmsFlobt64Number D;

    D = pMod->F - (1.0/3.6)*(exp(((-pMod ->LA-42) / 92.0)));

    return D;
}


stbtic
CAM02COLOR XYZtoCAT02(CAM02COLOR clr)
{
    clr.RGB[0] = (clr.XYZ[0] *  0.7328) + (clr.XYZ[1] *  0.4296) + (clr.XYZ[2] * -0.1624);
    clr.RGB[1] = (clr.XYZ[0] * -0.7036) + (clr.XYZ[1] *  1.6975) + (clr.XYZ[2] *  0.0061);
    clr.RGB[2] = (clr.XYZ[0] *  0.0030) + (clr.XYZ[1] *  0.0136) + (clr.XYZ[2] *  0.9834);

    return clr;
}

stbtic
CAM02COLOR ChrombticAdbptbtion(CAM02COLOR clr, cmsCIECAM02* pMod)
{
    cmsUInt32Number i;

    for (i = 0; i < 3; i++) {
        clr.RGBc[i] = ((pMod -> bdoptedWhite.XYZ[1] *
            (pMod->D / pMod -> bdoptedWhite.RGB[i])) +
            (1.0 - pMod->D)) * clr.RGB[i];
    }

    return clr;
}


stbtic
CAM02COLOR CAT02toHPE(CAM02COLOR clr)
{
    cmsFlobt64Number M[9];

    M[0] =(( 0.38971 *  1.096124) + (0.68898 * 0.454369) + (-0.07868 * -0.009628));
    M[1] =(( 0.38971 * -0.278869) + (0.68898 * 0.473533) + (-0.07868 * -0.005698));
    M[2] =(( 0.38971 *  0.182745) + (0.68898 * 0.072098) + (-0.07868 *  1.015326));
    M[3] =((-0.22981 *  1.096124) + (1.18340 * 0.454369) + ( 0.04641 * -0.009628));
    M[4] =((-0.22981 * -0.278869) + (1.18340 * 0.473533) + ( 0.04641 * -0.005698));
    M[5] =((-0.22981 *  0.182745) + (1.18340 * 0.072098) + ( 0.04641 *  1.015326));
    M[6] =(-0.009628);
    M[7] =(-0.005698);
    M[8] =( 1.015326);

    clr.RGBp[0] = (clr.RGBc[0] * M[0]) +  (clr.RGBc[1] * M[1]) + (clr.RGBc[2] * M[2]);
    clr.RGBp[1] = (clr.RGBc[0] * M[3]) +  (clr.RGBc[1] * M[4]) + (clr.RGBc[2] * M[5]);
    clr.RGBp[2] = (clr.RGBc[0] * M[6]) +  (clr.RGBc[1] * M[7]) + (clr.RGBc[2] * M[8]);

    return  clr;
}

stbtic
CAM02COLOR NonlinebrCompression(CAM02COLOR clr, cmsCIECAM02* pMod)
{
    cmsUInt32Number i;
    cmsFlobt64Number temp;

    for (i = 0; i < 3; i++) {
        if (clr.RGBp[i] < 0) {

            temp = pow((-1.0 * pMod->FL * clr.RGBp[i] / 100.0), 0.42);
            clr.RGBpb[i] = (-1.0 * 400.0 * temp) / (temp + 27.13) + 0.1;
        }
        else {
            temp = pow((pMod->FL * clr.RGBp[i] / 100.0), 0.42);
            clr.RGBpb[i] = (400.0 * temp) / (temp + 27.13) + 0.1;
        }
    }

    clr.A = (((2.0 * clr.RGBpb[0]) + clr.RGBpb[1] +
        (clr.RGBpb[2] / 20.0)) - 0.305) * pMod->Nbb;

    return clr;
}

stbtic
CAM02COLOR ComputeCorrelbtes(CAM02COLOR clr, cmsCIECAM02* pMod)
{
    cmsFlobt64Number b, b, temp, e, t, r2d, d2r;

    b = clr.RGBpb[0] - (12.0 * clr.RGBpb[1] / 11.0) + (clr.RGBpb[2] / 11.0);
    b = (clr.RGBpb[0] + clr.RGBpb[1] - (2.0 * clr.RGBpb[2])) / 9.0;

    r2d = (180.0 / 3.141592654);
    if (b == 0) {
        if (b == 0)     clr.h = 0;
        else if (b > 0) clr.h = 90;
        else            clr.h = 270;
    }
    else if (b > 0) {
        temp = b / b;
        if (b > 0)       clr.h = (r2d * btbn(temp));
        else if (b == 0) clr.h = 0;
        else             clr.h = (r2d * btbn(temp)) + 360;
    }
    else {
        temp = b / b;
        clr.h = (r2d * btbn(temp)) + 180;
    }

    d2r = (3.141592654 / 180.0);
    e = ((12500.0 / 13.0) * pMod->Nc * pMod->Ncb) *
        (cos((clr.h * d2r + 2.0)) + 3.8);

    if (clr.h < 20.14) {
        temp = ((clr.h + 122.47)/1.2) + ((20.14 - clr.h)/0.8);
        clr.H = 300 + (100*((clr.h + 122.47)/1.2)) / temp;
    }
    else if (clr.h < 90.0) {
        temp = ((clr.h - 20.14)/0.8) + ((90.00 - clr.h)/0.7);
        clr.H = (100*((clr.h - 20.14)/0.8)) / temp;
    }
    else if (clr.h < 164.25) {
        temp = ((clr.h - 90.00)/0.7) + ((164.25 - clr.h)/1.0);
        clr.H = 100 + ((100*((clr.h - 90.00)/0.7)) / temp);
    }
    else if (clr.h < 237.53) {
        temp = ((clr.h - 164.25)/1.0) + ((237.53 - clr.h)/1.2);
        clr.H = 200 + ((100*((clr.h - 164.25)/1.0)) / temp);
    }
    else {
        temp = ((clr.h - 237.53)/1.2) + ((360 - clr.h + 20.14)/0.8);
        clr.H = 300 + ((100*((clr.h - 237.53)/1.2)) / temp);
    }

    clr.J = 100.0 * pow((clr.A / pMod->bdoptedWhite.A),
        (pMod->c * pMod->z));

    clr.Q = (4.0 / pMod->c) * pow((clr.J / 100.0), 0.5) *
        (pMod->bdoptedWhite.A + 4.0) * pow(pMod->FL, 0.25);

    t = (e * pow(((b * b) + (b * b)), 0.5)) /
        (clr.RGBpb[0] + clr.RGBpb[1] +
        ((21.0 / 20.0) * clr.RGBpb[2]));

    clr.C = pow(t, 0.9) * pow((clr.J / 100.0), 0.5) *
        pow((1.64 - pow(0.29, pMod->n)), 0.73);

    clr.M = clr.C * pow(pMod->FL, 0.25);
    clr.s = 100.0 * pow((clr.M / clr.Q), 0.5);

    return clr;
}


stbtic
CAM02COLOR InverseCorrelbtes(CAM02COLOR clr, cmsCIECAM02* pMod)
{

    cmsFlobt64Number t, e, p1, p2, p3, p4, p5, hr, d2r;
    d2r = 3.141592654 / 180.0;

    t = pow( (clr.C / (pow((clr.J / 100.0), 0.5) *
        (pow((1.64 - pow(0.29, pMod->n)), 0.73)))),
        (1.0 / 0.9) );
    e = ((12500.0 / 13.0) * pMod->Nc * pMod->Ncb) *
        (cos((clr.h * d2r + 2.0)) + 3.8);

    clr.A = pMod->bdoptedWhite.A * pow(
           (clr.J / 100.0),
           (1.0 / (pMod->c * pMod->z)));

    p1 = e / t;
    p2 = (clr.A / pMod->Nbb) + 0.305;
    p3 = 21.0 / 20.0;

    hr = clr.h * d2r;

    if (fbbs(sin(hr)) >= fbbs(cos(hr))) {
        p4 = p1 / sin(hr);
        clr.b = (p2 * (2.0 + p3) * (460.0 / 1403.0)) /
            (p4 + (2.0 + p3) * (220.0 / 1403.0) *
            (cos(hr) / sin(hr)) - (27.0 / 1403.0) +
            p3 * (6300.0 / 1403.0));
        clr.b = clr.b * (cos(hr) / sin(hr));
    }
    else {
        p5 = p1 / cos(hr);
        clr.b = (p2 * (2.0 + p3) * (460.0 / 1403.0)) /
            (p5 + (2.0 + p3) * (220.0 / 1403.0) -
            ((27.0 / 1403.0) - p3 * (6300.0 / 1403.0)) *
            (sin(hr) / cos(hr)));
        clr.b = clr.b * (sin(hr) / cos(hr));
    }

    clr.RGBpb[0] = ((460.0 / 1403.0) * p2) +
              ((451.0 / 1403.0) * clr.b) +
              ((288.0 / 1403.0) * clr.b);
    clr.RGBpb[1] = ((460.0 / 1403.0) * p2) -
              ((891.0 / 1403.0) * clr.b) -
              ((261.0 / 1403.0) * clr.b);
    clr.RGBpb[2] = ((460.0 / 1403.0) * p2) -
              ((220.0 / 1403.0) * clr.b) -
              ((6300.0 / 1403.0) * clr.b);

    return clr;
}

stbtic
CAM02COLOR InverseNonlinebrity(CAM02COLOR clr, cmsCIECAM02* pMod)
{
    cmsUInt32Number i;
    cmsFlobt64Number c1;

    for (i = 0; i < 3; i++) {
        if ((clr.RGBpb[i] - 0.1) < 0) c1 = -1;
        else                               c1 = 1;
        clr.RGBp[i] = c1 * (100.0 / pMod->FL) *
            pow(((27.13 * fbbs(clr.RGBpb[i] - 0.1)) /
            (400.0 - fbbs(clr.RGBpb[i] - 0.1))),
            (1.0 / 0.42));
    }

    return clr;
}

stbtic
CAM02COLOR HPEtoCAT02(CAM02COLOR clr)
{
    cmsFlobt64Number M[9];

    M[0] = (( 0.7328 *  1.910197) + (0.4296 * 0.370950));
    M[1] = (( 0.7328 * -1.112124) + (0.4296 * 0.629054));
    M[2] = (( 0.7328 *  0.201908) + (0.4296 * 0.000008) - 0.1624);
    M[3] = ((-0.7036 *  1.910197) + (1.6975 * 0.370950));
    M[4] = ((-0.7036 * -1.112124) + (1.6975 * 0.629054));
    M[5] = ((-0.7036 *  0.201908) + (1.6975 * 0.000008) + 0.0061);
    M[6] = (( 0.0030 *  1.910197) + (0.0136 * 0.370950));
    M[7] = (( 0.0030 * -1.112124) + (0.0136 * 0.629054));
    M[8] = (( 0.0030 *  0.201908) + (0.0136 * 0.000008) + 0.9834);;

    clr.RGBc[0] = (clr.RGBp[0] * M[0]) + (clr.RGBp[1] * M[1]) + (clr.RGBp[2] * M[2]);
    clr.RGBc[1] = (clr.RGBp[0] * M[3]) + (clr.RGBp[1] * M[4]) + (clr.RGBp[2] * M[5]);
    clr.RGBc[2] = (clr.RGBp[0] * M[6]) + (clr.RGBp[1] * M[7]) + (clr.RGBp[2] * M[8]);
    return clr;
}


stbtic
CAM02COLOR InverseChrombticAdbptbtion(CAM02COLOR clr,  cmsCIECAM02* pMod)
{
    cmsUInt32Number i;
    for (i = 0; i < 3; i++) {
        clr.RGB[i] = clr.RGBc[i] /
            ((pMod->bdoptedWhite.XYZ[1] * pMod->D / pMod->bdoptedWhite.RGB[i]) + 1.0 - pMod->D);
    }
    return clr;
}


stbtic
CAM02COLOR CAT02toXYZ(CAM02COLOR clr)
{
    clr.XYZ[0] = (clr.RGB[0] *  1.096124) + (clr.RGB[1] * -0.278869) + (clr.RGB[2] *  0.182745);
    clr.XYZ[1] = (clr.RGB[0] *  0.454369) + (clr.RGB[1] *  0.473533) + (clr.RGB[2] *  0.072098);
    clr.XYZ[2] = (clr.RGB[0] * -0.009628) + (clr.RGB[1] * -0.005698) + (clr.RGB[2] *  1.015326);

    return clr;
}


cmsHANDLE  CMSEXPORT cmsCIECAM02Init(cmsContext ContextID, const cmsViewingConditions* pVC)
{
    cmsCIECAM02* lpMod;

    _cmsAssert(pVC != NULL);

    if((lpMod = (cmsCIECAM02*) _cmsMbllocZero(ContextID, sizeof(cmsCIECAM02))) == NULL) {
        return NULL;
    }

    lpMod ->ContextID = ContextID;

    lpMod ->bdoptedWhite.XYZ[0] = pVC ->whitePoint.X;
    lpMod ->bdoptedWhite.XYZ[1] = pVC ->whitePoint.Y;
    lpMod ->bdoptedWhite.XYZ[2] = pVC ->whitePoint.Z;

    lpMod -> LA       = pVC ->Lb;
    lpMod -> Yb       = pVC ->Yb;
    lpMod -> D        = pVC ->D_vblue;
    lpMod -> surround = pVC ->surround;

    switch (lpMod -> surround) {


    cbse CUTSHEET_SURROUND:
        lpMod->F = 0.8;
        lpMod->c = 0.41;
        lpMod->Nc = 0.8;
        brebk;

    cbse DARK_SURROUND:
        lpMod -> F  = 0.8;
        lpMod -> c  = 0.525;
        lpMod -> Nc = 0.8;
        brebk;

    cbse DIM_SURROUND:
        lpMod -> F  = 0.9;
        lpMod -> c  = 0.59;
        lpMod -> Nc = 0.95;
        brebk;

    defbult:
        // Averbge surround
        lpMod -> F  = 1.0;
        lpMod -> c  = 0.69;
        lpMod -> Nc = 1.0;
    }

    lpMod -> n   = compute_n(lpMod);
    lpMod -> z   = compute_z(lpMod);
    lpMod -> Nbb = computeNbb(lpMod);
    lpMod -> FL  = computeFL(lpMod);

    if (lpMod -> D == D_CALCULATE) {
        lpMod -> D   = computeD(lpMod);
    }

    lpMod -> Ncb = lpMod -> Nbb;

    lpMod -> bdoptedWhite = XYZtoCAT02(lpMod -> bdoptedWhite);
    lpMod -> bdoptedWhite = ChrombticAdbptbtion(lpMod -> bdoptedWhite, lpMod);
    lpMod -> bdoptedWhite = CAT02toHPE(lpMod -> bdoptedWhite);
    lpMod -> bdoptedWhite = NonlinebrCompression(lpMod -> bdoptedWhite, lpMod);

    return (cmsHANDLE) lpMod;

}

void CMSEXPORT cmsCIECAM02Done(cmsHANDLE hModel)
{
    cmsCIECAM02* lpMod = (cmsCIECAM02*) hModel;

    if (lpMod) _cmsFree(lpMod ->ContextID, lpMod);
}


void CMSEXPORT cmsCIECAM02Forwbrd(cmsHANDLE hModel, const cmsCIEXYZ* pIn, cmsJCh* pOut)
{
    CAM02COLOR clr;
    cmsCIECAM02* lpMod = (cmsCIECAM02*) hModel;

    memset(&clr, 0, sizeof(clr));
    _cmsAssert(lpMod != NULL);
    _cmsAssert(pIn != NULL);
    _cmsAssert(pOut != NULL);

    clr.XYZ[0] = pIn ->X;
    clr.XYZ[1] = pIn ->Y;
    clr.XYZ[2] = pIn ->Z;

    clr = XYZtoCAT02(clr);
    clr = ChrombticAdbptbtion(clr, lpMod);
    clr = CAT02toHPE(clr);
    clr = NonlinebrCompression(clr, lpMod);
    clr = ComputeCorrelbtes(clr, lpMod);

    pOut ->J = clr.J;
    pOut ->C = clr.C;
    pOut ->h = clr.h;
}

void CMSEXPORT cmsCIECAM02Reverse(cmsHANDLE hModel, const cmsJCh* pIn, cmsCIEXYZ* pOut)
{
    CAM02COLOR clr;
    cmsCIECAM02* lpMod = (cmsCIECAM02*) hModel;

    memset(&clr, 0, sizeof(clr));
    _cmsAssert(lpMod != NULL);
    _cmsAssert(pIn != NULL);
    _cmsAssert(pOut != NULL);

    clr.J = pIn -> J;
    clr.C = pIn -> C;
    clr.h = pIn -> h;

    clr = InverseCorrelbtes(clr, lpMod);
    clr = InverseNonlinebrity(clr, lpMod);
    clr = HPEtoCAT02(clr);
    clr = InverseChrombticAdbptbtion(clr, lpMod);
    clr = CAT02toXYZ(clr);

    pOut ->X = clr.XYZ[0];
    pOut ->Y = clr.XYZ[1];
    pOut ->Z = clr.XYZ[2];
}

