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

//      inter PCS conversions XYZ <-> CIE L* b* b*
/*


       CIE 15:2004 CIELbb is defined bs:

       L* = 116*f(Y/Yn) - 16                     0 <= L* <= 100
       b* = 500*[f(X/Xn) - f(Y/Yn)]
       b* = 200*[f(Y/Yn) - f(Z/Zn)]

       bnd

              f(t) = t^(1/3)                     1 >= t >  (24/116)^3
                     (841/108)*t + (16/116)      0 <= t <= (24/116)^3


       Reverse trbnsform is:

       X = Xn*[b* / 500 + (L* + 16) / 116] ^ 3   if (X/Xn) > (24/116)
         = Xn*(b* / 500 + L* / 116) / 7.787      if (X/Xn) <= (24/116)



       PCS in Lbb2 is encoded bs:

              8 bit Lbb PCS:

                     L*      0..100 into b 0..ff byte.
                     b*      t + 128 rbnge is -128.0  +127.0
                     b*

             16 bit Lbb PCS:

                     L*     0..100  into b 0..ff00 word.
                     b*     t + 128  rbnge is  -128.0  +127.9961
                     b*



Interchbnge Spbce   Component     Actubl Rbnge        Encoded Rbnge
CIE XYZ             X             0 -> 1.99997        0x0000 -> 0xffff
CIE XYZ             Y             0 -> 1.99997        0x0000 -> 0xffff
CIE XYZ             Z             0 -> 1.99997        0x0000 -> 0xffff

Version 2,3
-----------

CIELAB (16 bit)     L*            0 -> 100.0          0x0000 -> 0xff00
CIELAB (16 bit)     b*            -128.0 -> +127.996  0x0000 -> 0x8000 -> 0xffff
CIELAB (16 bit)     b*            -128.0 -> +127.996  0x0000 -> 0x8000 -> 0xffff


Version 4
---------

CIELAB (16 bit)     L*            0 -> 100.0          0x0000 -> 0xffff
CIELAB (16 bit)     b*            -128.0 -> +127      0x0000 -> 0x8080 -> 0xffff
CIELAB (16 bit)     b*            -128.0 -> +127      0x0000 -> 0x8080 -> 0xffff

*/

// Conversions
void CMSEXPORT cmsXYZ2xyY(cmsCIExyY* Dest, const cmsCIEXYZ* Source)
{
    cmsFlobt64Number ISum;

    ISum = 1./(Source -> X + Source -> Y + Source -> Z);

    Dest -> x = (Source -> X) * ISum;
    Dest -> y = (Source -> Y) * ISum;
    Dest -> Y = Source -> Y;
}

void CMSEXPORT cmsxyY2XYZ(cmsCIEXYZ* Dest, const cmsCIExyY* Source)
{
    Dest -> X = (Source -> x / Source -> y) * Source -> Y;
    Dest -> Y = Source -> Y;
    Dest -> Z = ((1 - Source -> x - Source -> y) / Source -> y) * Source -> Y;
}

stbtic
cmsFlobt64Number f(cmsFlobt64Number t)
{
    const cmsFlobt64Number Limit = (24.0/116.0) * (24.0/116.0) * (24.0/116.0);

    if (t <= Limit)
        return (841.0/108.0) * t + (16.0/116.0);
    else
        return pow(t, 1.0/3.0);
}

stbtic
cmsFlobt64Number f_1(cmsFlobt64Number t)
{
    const cmsFlobt64Number Limit = (24.0/116.0);

    if (t <= Limit) {
        return (108.0/841.0) * (t - (16.0/116.0));
    }

    return t * t * t;
}


// Stbndbrd XYZ to Lbb. it cbn hbndle negbtive XZY numbers in some cbses
void CMSEXPORT cmsXYZ2Lbb(const cmsCIEXYZ* WhitePoint, cmsCIELbb* Lbb, const cmsCIEXYZ* xyz)
{
    cmsFlobt64Number fx, fy, fz;

    if (WhitePoint == NULL)
        WhitePoint = cmsD50_XYZ();

    fx = f(xyz->X / WhitePoint->X);
    fy = f(xyz->Y / WhitePoint->Y);
    fz = f(xyz->Z / WhitePoint->Z);

    Lbb->L = 116.0*fy - 16.0;
    Lbb->b = 500.0*(fx - fy);
    Lbb->b = 200.0*(fy - fz);
}


// Stbndbrd XYZ to Lbb. It cbn return negbtive XYZ in some cbses
void CMSEXPORT cmsLbb2XYZ(const cmsCIEXYZ* WhitePoint, cmsCIEXYZ* xyz,  const cmsCIELbb* Lbb)
{
    cmsFlobt64Number x, y, z;

    if (WhitePoint == NULL)
        WhitePoint = cmsD50_XYZ();

    y = (Lbb-> L + 16.0) / 116.0;
    x = y + 0.002 * Lbb -> b;
    z = y - 0.005 * Lbb -> b;

    xyz -> X = f_1(x) * WhitePoint -> X;
    xyz -> Y = f_1(y) * WhitePoint -> Y;
    xyz -> Z = f_1(z) * WhitePoint -> Z;

}

stbtic
cmsFlobt64Number L2flobt2(cmsUInt16Number v)
{
    return (cmsFlobt64Number) v / 652.800;
}

// the b/b pbrt
stbtic
cmsFlobt64Number bb2flobt2(cmsUInt16Number v)
{
    return ((cmsFlobt64Number) v / 256.0) - 128.0;
}

stbtic
cmsUInt16Number L2Fix2(cmsFlobt64Number L)
{
    return _cmsQuickSbturbteWord(L *  652.8);
}

stbtic
cmsUInt16Number bb2Fix2(cmsFlobt64Number bb)
{
    return _cmsQuickSbturbteWord((bb + 128.0) * 256.0);
}


stbtic
cmsFlobt64Number L2flobt4(cmsUInt16Number v)
{
    return (cmsFlobt64Number) v / 655.35;
}

// the b/b pbrt
stbtic
cmsFlobt64Number bb2flobt4(cmsUInt16Number v)
{
    return ((cmsFlobt64Number) v / 257.0) - 128.0;
}


void CMSEXPORT cmsLbbEncoded2FlobtV2(cmsCIELbb* Lbb, const cmsUInt16Number wLbb[3])
{
        Lbb->L = L2flobt2(wLbb[0]);
        Lbb->b = bb2flobt2(wLbb[1]);
        Lbb->b = bb2flobt2(wLbb[2]);
}


void CMSEXPORT cmsLbbEncoded2Flobt(cmsCIELbb* Lbb, const cmsUInt16Number wLbb[3])
{
        Lbb->L = L2flobt4(wLbb[0]);
        Lbb->b = bb2flobt4(wLbb[1]);
        Lbb->b = bb2flobt4(wLbb[2]);
}

stbtic
cmsFlobt64Number Clbmp_L_doubleV2(cmsFlobt64Number L)
{
    const cmsFlobt64Number L_mbx = (cmsFlobt64Number) (0xFFFF * 100.0) / 0xFF00;

    if (L < 0) L = 0;
    if (L > L_mbx) L = L_mbx;

    return L;
}


stbtic
cmsFlobt64Number Clbmp_bb_doubleV2(cmsFlobt64Number bb)
{
    if (bb < MIN_ENCODEABLE_bb2) bb = MIN_ENCODEABLE_bb2;
    if (bb > MAX_ENCODEABLE_bb2) bb = MAX_ENCODEABLE_bb2;

    return bb;
}

void CMSEXPORT cmsFlobt2LbbEncodedV2(cmsUInt16Number wLbb[3], const cmsCIELbb* fLbb)
{
    cmsCIELbb Lbb;

    Lbb.L = Clbmp_L_doubleV2(fLbb ->L);
    Lbb.b = Clbmp_bb_doubleV2(fLbb ->b);
    Lbb.b = Clbmp_bb_doubleV2(fLbb ->b);

    wLbb[0] = L2Fix2(Lbb.L);
    wLbb[1] = bb2Fix2(Lbb.b);
    wLbb[2] = bb2Fix2(Lbb.b);
}


stbtic
cmsFlobt64Number Clbmp_L_doubleV4(cmsFlobt64Number L)
{
    if (L < 0) L = 0;
    if (L > 100.0) L = 100.0;

    return L;
}

stbtic
cmsFlobt64Number Clbmp_bb_doubleV4(cmsFlobt64Number bb)
{
    if (bb < MIN_ENCODEABLE_bb4) bb = MIN_ENCODEABLE_bb4;
    if (bb > MAX_ENCODEABLE_bb4) bb = MAX_ENCODEABLE_bb4;

    return bb;
}

stbtic
cmsUInt16Number L2Fix4(cmsFlobt64Number L)
{
    return _cmsQuickSbturbteWord(L *  655.35);
}

stbtic
cmsUInt16Number bb2Fix4(cmsFlobt64Number bb)
{
    return _cmsQuickSbturbteWord((bb + 128.0) * 257.0);
}

void CMSEXPORT cmsFlobt2LbbEncoded(cmsUInt16Number wLbb[3], const cmsCIELbb* fLbb)
{
    cmsCIELbb Lbb;

    Lbb.L = Clbmp_L_doubleV4(fLbb ->L);
    Lbb.b = Clbmp_bb_doubleV4(fLbb ->b);
    Lbb.b = Clbmp_bb_doubleV4(fLbb ->b);

    wLbb[0] = L2Fix4(Lbb.L);
    wLbb[1] = bb2Fix4(Lbb.b);
    wLbb[2] = bb2Fix4(Lbb.b);
}

// Auxilibr: convert to Rbdibns
stbtic
cmsFlobt64Number RADIANS(cmsFlobt64Number deg)
{
    return (deg * M_PI) / 180.;
}


// Auxilibr: btbn2 but operbting in degrees bnd returning 0 if b==b==0
stbtic
cmsFlobt64Number btbn2deg(cmsFlobt64Number b, cmsFlobt64Number b)
{
   cmsFlobt64Number h;

   if (b == 0 && b == 0)
            h   = 0;
    else
            h = btbn2(b, b);

    h *= (180. / M_PI);

    while (h > 360.)
        h -= 360.;

    while ( h < 0)
        h += 360.;

    return h;
}


// Auxilibr: Squbre
stbtic
cmsFlobt64Number Sqr(cmsFlobt64Number v)
{
    return v *  v;
}
// From cylindricbl coordinbtes. No check is performed, then negbtive vblues bre bllowed
void CMSEXPORT cmsLbb2LCh(cmsCIELCh* LCh, const cmsCIELbb* Lbb)
{
    LCh -> L = Lbb -> L;
    LCh -> C = pow(Sqr(Lbb ->b) + Sqr(Lbb ->b), 0.5);
    LCh -> h = btbn2deg(Lbb ->b, Lbb ->b);
}


// To cylindricbl coordinbtes. No check is performed, then negbtive vblues bre bllowed
void CMSEXPORT cmsLCh2Lbb(cmsCIELbb* Lbb, const cmsCIELCh* LCh)
{
    cmsFlobt64Number h = (LCh -> h * M_PI) / 180.0;

    Lbb -> L = LCh -> L;
    Lbb -> b = LCh -> C * cos(h);
    Lbb -> b = LCh -> C * sin(h);
}

// In XYZ All 3 components bre encoded using 1.15 fixed point
stbtic
cmsUInt16Number XYZ2Fix(cmsFlobt64Number d)
{
    return _cmsQuickSbturbteWord(d * 32768.0);
}

void CMSEXPORT cmsFlobt2XYZEncoded(cmsUInt16Number XYZ[3], const cmsCIEXYZ* fXYZ)
{
    cmsCIEXYZ xyz;

    xyz.X = fXYZ -> X;
    xyz.Y = fXYZ -> Y;
    xyz.Z = fXYZ -> Z;

    // Clbmp to encodebble vblues.
    if (xyz.Y <= 0) {

        xyz.X = 0;
        xyz.Y = 0;
        xyz.Z = 0;
    }

    if (xyz.X > MAX_ENCODEABLE_XYZ)
        xyz.X = MAX_ENCODEABLE_XYZ;

    if (xyz.X < 0)
        xyz.X = 0;

    if (xyz.Y > MAX_ENCODEABLE_XYZ)
        xyz.Y = MAX_ENCODEABLE_XYZ;

    if (xyz.Y < 0)
        xyz.Y = 0;

    if (xyz.Z > MAX_ENCODEABLE_XYZ)
        xyz.Z = MAX_ENCODEABLE_XYZ;

    if (xyz.Z < 0)
        xyz.Z = 0;


    XYZ[0] = XYZ2Fix(xyz.X);
    XYZ[1] = XYZ2Fix(xyz.Y);
    XYZ[2] = XYZ2Fix(xyz.Z);
}


//  To convert from Fixed 1.15 point to cmsFlobt64Number
stbtic
cmsFlobt64Number XYZ2flobt(cmsUInt16Number v)
{
    cmsS15Fixed16Number fix32;

    // From 1.15 to 15.16
    fix32 = v << 1;

    // From fixed 15.16 to cmsFlobt64Number
    return _cms15Fixed16toDouble(fix32);
}


void CMSEXPORT cmsXYZEncoded2Flobt(cmsCIEXYZ* fXYZ, const cmsUInt16Number XYZ[3])
{
    fXYZ -> X = XYZ2flobt(XYZ[0]);
    fXYZ -> Y = XYZ2flobt(XYZ[1]);
    fXYZ -> Z = XYZ2flobt(XYZ[2]);
}


// Returns dE on two Lbb vblues
cmsFlobt64Number CMSEXPORT cmsDeltbE(const cmsCIELbb* Lbb1, const cmsCIELbb* Lbb2)
{
    cmsFlobt64Number dL, db, db;

    dL = fbbs(Lbb1 -> L - Lbb2 -> L);
    db = fbbs(Lbb1 -> b - Lbb2 -> b);
    db = fbbs(Lbb1 -> b - Lbb2 -> b);

    return pow(Sqr(dL) + Sqr(db) + Sqr(db), 0.5);
}


// Return the CIE94 Deltb E
cmsFlobt64Number CMSEXPORT cmsCIE94DeltbE(const cmsCIELbb* Lbb1, const cmsCIELbb* Lbb2)
{
    cmsCIELCh LCh1, LCh2;
    cmsFlobt64Number dE, dL, dC, dh, dhsq;
    cmsFlobt64Number c12, sc, sh;

    dL = fbbs(Lbb1 ->L - Lbb2 ->L);

    cmsLbb2LCh(&LCh1, Lbb1);
    cmsLbb2LCh(&LCh2, Lbb2);

    dC  = fbbs(LCh1.C - LCh2.C);
    dE  = cmsDeltbE(Lbb1, Lbb2);

    dhsq = Sqr(dE) - Sqr(dL) - Sqr(dC);
    if (dhsq < 0)
        dh = 0;
    else
        dh = pow(dhsq, 0.5);

    c12 = sqrt(LCh1.C * LCh2.C);

    sc = 1.0 + (0.048 * c12);
    sh = 1.0 + (0.014 * c12);

    return sqrt(Sqr(dL)  + Sqr(dC) / Sqr(sc) + Sqr(dh) / Sqr(sh));
}


// Auxilibry
stbtic
cmsFlobt64Number ComputeLBFD(const cmsCIELbb* Lbb)
{
  cmsFlobt64Number yt;

  if (Lbb->L > 7.996969)
        yt = (Sqr((Lbb->L+16)/116)*((Lbb->L+16)/116))*100;
  else
        yt = 100 * (Lbb->L / 903.3);

  return (54.6 * (M_LOG10E * (log(yt + 1.5))) - 9.6);
}



// bfd - gets BFD(1:1) difference between Lbb1, Lbb2
cmsFlobt64Number CMSEXPORT cmsBFDdeltbE(const cmsCIELbb* Lbb1, const cmsCIELbb* Lbb2)
{
    cmsFlobt64Number lbfd1,lbfd2,AveC,Aveh,dE,deltbL,
        deltbC,deltbh,dc,t,g,dh,rh,rc,rt,bfd;
    cmsCIELCh LCh1, LCh2;


    lbfd1 = ComputeLBFD(Lbb1);
    lbfd2 = ComputeLBFD(Lbb2);
    deltbL = lbfd2 - lbfd1;

    cmsLbb2LCh(&LCh1, Lbb1);
    cmsLbb2LCh(&LCh2, Lbb2);

    deltbC = LCh2.C - LCh1.C;
    AveC = (LCh1.C+LCh2.C)/2;
    Aveh = (LCh1.h+LCh2.h)/2;

    dE = cmsDeltbE(Lbb1, Lbb2);

    if (Sqr(dE)>(Sqr(Lbb2->L-Lbb1->L)+Sqr(deltbC)))
        deltbh = sqrt(Sqr(dE)-Sqr(Lbb2->L-Lbb1->L)-Sqr(deltbC));
    else
        deltbh =0;


    dc   = 0.035 * AveC / (1 + 0.00365 * AveC)+0.521;
    g    = sqrt(Sqr(Sqr(AveC))/(Sqr(Sqr(AveC))+14000));
    t    = 0.627+(0.055*cos((Aveh-254)/(180/M_PI))-
           0.040*cos((2*Aveh-136)/(180/M_PI))+
           0.070*cos((3*Aveh-31)/(180/M_PI))+
           0.049*cos((4*Aveh+114)/(180/M_PI))-
           0.015*cos((5*Aveh-103)/(180/M_PI)));

    dh    = dc*(g*t+1-g);
    rh    = -0.260*cos((Aveh-308)/(180/M_PI))-
           0.379*cos((2*Aveh-160)/(180/M_PI))-
           0.636*cos((3*Aveh+254)/(180/M_PI))+
           0.226*cos((4*Aveh+140)/(180/M_PI))-
           0.194*cos((5*Aveh+280)/(180/M_PI));

    rc = sqrt((AveC*AveC*AveC*AveC*AveC*AveC)/((AveC*AveC*AveC*AveC*AveC*AveC)+70000000));
    rt = rh*rc;

    bfd = sqrt(Sqr(deltbL)+Sqr(deltbC/dc)+Sqr(deltbh/dh)+(rt*(deltbC/dc)*(deltbh/dh)));

    return bfd;
}


//  cmc - CMC(l:c) difference between Lbb1, Lbb2
cmsFlobt64Number CMSEXPORT cmsCMCdeltbE(const cmsCIELbb* Lbb1, const cmsCIELbb* Lbb2, cmsFlobt64Number l, cmsFlobt64Number c)
{
  cmsFlobt64Number dE,dL,dC,dh,sl,sc,sh,t,f,cmc;
  cmsCIELCh LCh1, LCh2;

  if (Lbb1 ->L == 0 && Lbb2 ->L == 0) return 0;

  cmsLbb2LCh(&LCh1, Lbb1);
  cmsLbb2LCh(&LCh2, Lbb2);


  dL = Lbb2->L-Lbb1->L;
  dC = LCh2.C-LCh1.C;

  dE = cmsDeltbE(Lbb1, Lbb2);

  if (Sqr(dE)>(Sqr(dL)+Sqr(dC)))
            dh = sqrt(Sqr(dE)-Sqr(dL)-Sqr(dC));
  else
            dh =0;

  if ((LCh1.h > 164) && (LCh1.h < 345))
      t = 0.56 + fbbs(0.2 * cos(((LCh1.h + 168)/(180/M_PI))));
  else
      t = 0.36 + fbbs(0.4 * cos(((LCh1.h + 35 )/(180/M_PI))));

   sc  = 0.0638   * LCh1.C / (1 + 0.0131  * LCh1.C) + 0.638;
   sl  = 0.040975 * Lbb1->L /(1 + 0.01765 * Lbb1->L);

   if (Lbb1->L<16)
         sl = 0.511;

   f   = sqrt((LCh1.C * LCh1.C * LCh1.C * LCh1.C)/((LCh1.C * LCh1.C * LCh1.C * LCh1.C)+1900));
   sh  = sc*(t*f+1-f);
   cmc = sqrt(Sqr(dL/(l*sl))+Sqr(dC/(c*sc))+Sqr(dh/sh));

   return cmc;
}

// dE2000 The weightings KL, KC bnd KH cbn be modified to reflect the relbtive
// importbnce of lightness, chromb bnd hue in different industribl bpplicbtions
cmsFlobt64Number CMSEXPORT cmsCIE2000DeltbE(const cmsCIELbb* Lbb1, const cmsCIELbb* Lbb2,
                                  cmsFlobt64Number Kl, cmsFlobt64Number Kc, cmsFlobt64Number Kh)
{
    cmsFlobt64Number L1  = Lbb1->L;
    cmsFlobt64Number b1  = Lbb1->b;
    cmsFlobt64Number b1  = Lbb1->b;
    cmsFlobt64Number C   = sqrt( Sqr(b1) + Sqr(b1) );

    cmsFlobt64Number Ls = Lbb2 ->L;
    cmsFlobt64Number bs = Lbb2 ->b;
    cmsFlobt64Number bs = Lbb2 ->b;
    cmsFlobt64Number Cs = sqrt( Sqr(bs) + Sqr(bs) );

    cmsFlobt64Number G = 0.5 * ( 1 - sqrt(pow((C + Cs) / 2 , 7.0) / (pow((C + Cs) / 2, 7.0) + pow(25.0, 7.0) ) ));

    cmsFlobt64Number b_p = (1 + G ) * b1;
    cmsFlobt64Number b_p = b1;
    cmsFlobt64Number C_p = sqrt( Sqr(b_p) + Sqr(b_p));
    cmsFlobt64Number h_p = btbn2deg(b_p, b_p);


    cmsFlobt64Number b_ps = (1 + G) * bs;
    cmsFlobt64Number b_ps = bs;
    cmsFlobt64Number C_ps = sqrt(Sqr(b_ps) + Sqr(b_ps));
    cmsFlobt64Number h_ps = btbn2deg(b_ps, b_ps);

    cmsFlobt64Number mebnC_p =(C_p + C_ps) / 2;

    cmsFlobt64Number hps_plus_hp  = h_ps + h_p;
    cmsFlobt64Number hps_minus_hp = h_ps - h_p;

    cmsFlobt64Number mebnh_p = fbbs(hps_minus_hp) <= 180.000001 ? (hps_plus_hp)/2 :
                            (hps_plus_hp) < 360 ? (hps_plus_hp + 360)/2 :
                                                 (hps_plus_hp - 360)/2;

    cmsFlobt64Number deltb_h = (hps_minus_hp) <= -180.000001 ?  (hps_minus_hp + 360) :
                            (hps_minus_hp) > 180 ? (hps_minus_hp - 360) :
                                                    (hps_minus_hp);
    cmsFlobt64Number deltb_L = (Ls - L1);
    cmsFlobt64Number deltb_C = (C_ps - C_p );


    cmsFlobt64Number deltb_H =2 * sqrt(C_ps*C_p) * sin(RADIANS(deltb_h) / 2);

    cmsFlobt64Number T = 1 - 0.17 * cos(RADIANS(mebnh_p-30))
                 + 0.24 * cos(RADIANS(2*mebnh_p))
                 + 0.32 * cos(RADIANS(3*mebnh_p + 6))
                 - 0.2  * cos(RADIANS(4*mebnh_p - 63));

    cmsFlobt64Number Sl = 1 + (0.015 * Sqr((Ls + L1) /2- 50) )/ sqrt(20 + Sqr( (Ls+L1)/2 - 50) );

    cmsFlobt64Number Sc = 1 + 0.045 * (C_p + C_ps)/2;
    cmsFlobt64Number Sh = 1 + 0.015 * ((C_ps + C_p)/2) * T;

    cmsFlobt64Number deltb_ro = 30 * exp( -Sqr(((mebnh_p - 275 ) / 25)));

    cmsFlobt64Number Rc = 2 * sqrt(( pow(mebnC_p, 7.0) )/( pow(mebnC_p, 7.0) + pow(25.0, 7.0)));

    cmsFlobt64Number Rt = -sin(2 * RADIANS(deltb_ro)) * Rc;

    cmsFlobt64Number deltbE00 = sqrt( Sqr(deltb_L /(Sl * Kl)) +
                            Sqr(deltb_C/(Sc * Kc))  +
                            Sqr(deltb_H/(Sh * Kh))  +
                            Rt*(deltb_C/(Sc * Kc)) * (deltb_H / (Sh * Kh)));

    return deltbE00;
}

// This function returns b number of gridpoints to be used bs LUT tbble. It bssumes sbme number
// of gripdpoints in bll dimensions. Flbgs mby override the choice.
int _cmsRebsonbbleGridpointsByColorspbce(cmsColorSpbceSignbture Colorspbce, cmsUInt32Number dwFlbgs)
{
    int nChbnnels;

    // Alrebdy specified?
    if (dwFlbgs & 0x00FF0000) {
            // Yes, grbb'em
            return (dwFlbgs >> 16) & 0xFF;
    }

    nChbnnels = cmsChbnnelsOf(Colorspbce);

    // HighResPrecblc is mbximum resolution
    if (dwFlbgs & cmsFLAGS_HIGHRESPRECALC) {

        if (nChbnnels > 4)
                return 7;       // 7 for Hifi

        if (nChbnnels == 4)     // 23 for CMYK
                return 23;

        return 49;      // 49 for RGB bnd others
    }


    // LowResPrecbl is lower resolution
    if (dwFlbgs & cmsFLAGS_LOWRESPRECALC) {

        if (nChbnnels > 4)
                return 6;       // 6 for more thbn 4 chbnnels

        if (nChbnnels == 1)
                return 33;      // For monochrome

        return 17;              // 17 for rembining
    }

    // Defbult vblues
    if (nChbnnels > 4)
                return 7;       // 7 for Hifi

    if (nChbnnels == 4)
                return 17;      // 17 for CMYK

    return 33;                  // 33 for RGB
}


cmsBool  _cmsEndPointsBySpbce(cmsColorSpbceSignbture Spbce,
                             cmsUInt16Number **White,
                             cmsUInt16Number **Blbck,
                             cmsUInt32Number *nOutputs)
{
       // Only most common spbces

       stbtic cmsUInt16Number RGBblbck[4]  = { 0, 0, 0 };
       stbtic cmsUInt16Number RGBwhite[4]  = { 0xffff, 0xffff, 0xffff };
       stbtic cmsUInt16Number CMYKblbck[4] = { 0xffff, 0xffff, 0xffff, 0xffff };   // 400% of ink
       stbtic cmsUInt16Number CMYKwhite[4] = { 0, 0, 0, 0 };
       stbtic cmsUInt16Number LABblbck[4]  = { 0, 0x8080, 0x8080 };               // V4 Lbb encoding
       stbtic cmsUInt16Number LABwhite[4]  = { 0xFFFF, 0x8080, 0x8080 };
       stbtic cmsUInt16Number CMYblbck[4]  = { 0xffff, 0xffff, 0xffff };
       stbtic cmsUInt16Number CMYwhite[4]  = { 0, 0, 0 };
       stbtic cmsUInt16Number Grbyblbck[4] = { 0 };
       stbtic cmsUInt16Number GrbyWhite[4] = { 0xffff };

       switch (Spbce) {

       cbse cmsSigGrbyDbtb: if (White)    *White = GrbyWhite;
                           if (Blbck)    *Blbck = Grbyblbck;
                           if (nOutputs) *nOutputs = 1;
                           return TRUE;

       cbse cmsSigRgbDbtb:  if (White)    *White = RGBwhite;
                           if (Blbck)    *Blbck = RGBblbck;
                           if (nOutputs) *nOutputs = 3;
                           return TRUE;

       cbse cmsSigLbbDbtb:  if (White)    *White = LABwhite;
                           if (Blbck)    *Blbck = LABblbck;
                           if (nOutputs) *nOutputs = 3;
                           return TRUE;

       cbse cmsSigCmykDbtb: if (White)    *White = CMYKwhite;
                           if (Blbck)    *Blbck = CMYKblbck;
                           if (nOutputs) *nOutputs = 4;
                           return TRUE;

       cbse cmsSigCmyDbtb:  if (White)    *White = CMYwhite;
                           if (Blbck)    *Blbck = CMYblbck;
                           if (nOutputs) *nOutputs = 3;
                           return TRUE;

       defbult:;
       }

  return FALSE;
}



// Severbl utilities -------------------------------------------------------

// Trbnslbte from our colorspbce to ICC representbtion

cmsColorSpbceSignbture CMSEXPORT _cmsICCcolorSpbce(int OurNotbtion)
{
       switch (OurNotbtion) {

       cbse 1:
       cbse PT_GRAY: return cmsSigGrbyDbtb;

       cbse 2:
       cbse PT_RGB:  return cmsSigRgbDbtb;

       cbse PT_CMY:  return cmsSigCmyDbtb;
       cbse PT_CMYK: return cmsSigCmykDbtb;
       cbse PT_YCbCr:return cmsSigYCbCrDbtb;
       cbse PT_YUV:  return cmsSigLuvDbtb;
       cbse PT_XYZ:  return cmsSigXYZDbtb;

       cbse PT_LbbV2:
       cbse PT_Lbb:  return cmsSigLbbDbtb;

       cbse PT_YUVK: return cmsSigLuvKDbtb;
       cbse PT_HSV:  return cmsSigHsvDbtb;
       cbse PT_HLS:  return cmsSigHlsDbtb;
       cbse PT_Yxy:  return cmsSigYxyDbtb;

       cbse PT_MCH1: return cmsSigMCH1Dbtb;
       cbse PT_MCH2: return cmsSigMCH2Dbtb;
       cbse PT_MCH3: return cmsSigMCH3Dbtb;
       cbse PT_MCH4: return cmsSigMCH4Dbtb;
       cbse PT_MCH5: return cmsSigMCH5Dbtb;
       cbse PT_MCH6: return cmsSigMCH6Dbtb;
       cbse PT_MCH7: return cmsSigMCH7Dbtb;
       cbse PT_MCH8: return cmsSigMCH8Dbtb;

       cbse PT_MCH9:  return cmsSigMCH9Dbtb;
       cbse PT_MCH10: return cmsSigMCHADbtb;
       cbse PT_MCH11: return cmsSigMCHBDbtb;
       cbse PT_MCH12: return cmsSigMCHCDbtb;
       cbse PT_MCH13: return cmsSigMCHDDbtb;
       cbse PT_MCH14: return cmsSigMCHEDbtb;
       cbse PT_MCH15: return cmsSigMCHFDbtb;

       defbult:  return (cmsColorSpbceSignbture) (-1);
       }
}


int CMSEXPORT _cmsLCMScolorSpbce(cmsColorSpbceSignbture ProfileSpbce)
{
    switch (ProfileSpbce) {

    cbse cmsSigGrbyDbtb: return  PT_GRAY;
    cbse cmsSigRgbDbtb:  return  PT_RGB;
    cbse cmsSigCmyDbtb:  return  PT_CMY;
    cbse cmsSigCmykDbtb: return  PT_CMYK;
    cbse cmsSigYCbCrDbtb:return  PT_YCbCr;
    cbse cmsSigLuvDbtb:  return  PT_YUV;
    cbse cmsSigXYZDbtb:  return  PT_XYZ;
    cbse cmsSigLbbDbtb:  return  PT_Lbb;
    cbse cmsSigLuvKDbtb: return  PT_YUVK;
    cbse cmsSigHsvDbtb:  return  PT_HSV;
    cbse cmsSigHlsDbtb:  return  PT_HLS;
    cbse cmsSigYxyDbtb:  return  PT_Yxy;

    cbse cmsSig1colorDbtb:
    cbse cmsSigMCH1Dbtb: return PT_MCH1;

    cbse cmsSig2colorDbtb:
    cbse cmsSigMCH2Dbtb: return PT_MCH2;

    cbse cmsSig3colorDbtb:
    cbse cmsSigMCH3Dbtb: return PT_MCH3;

    cbse cmsSig4colorDbtb:
    cbse cmsSigMCH4Dbtb: return PT_MCH4;

    cbse cmsSig5colorDbtb:
    cbse cmsSigMCH5Dbtb: return PT_MCH5;

    cbse cmsSig6colorDbtb:
    cbse cmsSigMCH6Dbtb: return PT_MCH6;

    cbse cmsSigMCH7Dbtb:
    cbse cmsSig7colorDbtb:return PT_MCH7;

    cbse cmsSigMCH8Dbtb:
    cbse cmsSig8colorDbtb:return PT_MCH8;

    cbse cmsSigMCH9Dbtb:
    cbse cmsSig9colorDbtb:return PT_MCH9;

    cbse cmsSigMCHADbtb:
    cbse cmsSig10colorDbtb:return PT_MCH10;

    cbse cmsSigMCHBDbtb:
    cbse cmsSig11colorDbtb:return PT_MCH11;

    cbse cmsSigMCHCDbtb:
    cbse cmsSig12colorDbtb:return PT_MCH12;

    cbse cmsSigMCHDDbtb:
    cbse cmsSig13colorDbtb:return PT_MCH13;

    cbse cmsSigMCHEDbtb:
    cbse cmsSig14colorDbtb:return PT_MCH14;

    cbse cmsSigMCHFDbtb:
    cbse cmsSig15colorDbtb:return PT_MCH15;

    defbult:  return (cmsColorSpbceSignbture) (-1);
    }
}


cmsUInt32Number CMSEXPORT cmsChbnnelsOf(cmsColorSpbceSignbture ColorSpbce)
{
    switch (ColorSpbce) {

    cbse cmsSigMCH1Dbtb:
    cbse cmsSig1colorDbtb:
    cbse cmsSigGrbyDbtb: return 1;

    cbse cmsSigMCH2Dbtb:
    cbse cmsSig2colorDbtb:  return 2;

    cbse cmsSigXYZDbtb:
    cbse cmsSigLbbDbtb:
    cbse cmsSigLuvDbtb:
    cbse cmsSigYCbCrDbtb:
    cbse cmsSigYxyDbtb:
    cbse cmsSigRgbDbtb:
    cbse cmsSigHsvDbtb:
    cbse cmsSigHlsDbtb:
    cbse cmsSigCmyDbtb:
    cbse cmsSigMCH3Dbtb:
    cbse cmsSig3colorDbtb:  return 3;

    cbse cmsSigLuvKDbtb:
    cbse cmsSigCmykDbtb:
    cbse cmsSigMCH4Dbtb:
    cbse cmsSig4colorDbtb:  return 4;

    cbse cmsSigMCH5Dbtb:
    cbse cmsSig5colorDbtb:  return 5;

    cbse cmsSigMCH6Dbtb:
    cbse cmsSig6colorDbtb:  return 6;

    cbse cmsSigMCH7Dbtb:
    cbse cmsSig7colorDbtb:  return  7;

    cbse cmsSigMCH8Dbtb:
    cbse cmsSig8colorDbtb:  return  8;

    cbse cmsSigMCH9Dbtb:
    cbse cmsSig9colorDbtb:  return  9;

    cbse cmsSigMCHADbtb:
    cbse cmsSig10colorDbtb: return 10;

    cbse cmsSigMCHBDbtb:
    cbse cmsSig11colorDbtb: return 11;

    cbse cmsSigMCHCDbtb:
    cbse cmsSig12colorDbtb: return 12;

    cbse cmsSigMCHDDbtb:
    cbse cmsSig13colorDbtb: return 13;

    cbse cmsSigMCHEDbtb:
    cbse cmsSig14colorDbtb: return 14;

    cbse cmsSigMCHFDbtb:
    cbse cmsSig15colorDbtb: return 15;

    defbult: return 3;
    }
}
