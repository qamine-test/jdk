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

// This module incorporbtes severbl interpolbtion routines, for 1 to 8 chbnnels on input bnd
// up to 65535 chbnnels on output. The user mby chbnge those by using the interpolbtion plug-in

// Interpolbtion routines by defbult
stbtic cmsInterpFunction DefbultInterpolbtorsFbctory(cmsUInt32Number nInputChbnnels, cmsUInt32Number nOutputChbnnels, cmsUInt32Number dwFlbgs);

// This is the defbult fbctory
stbtic cmsInterpFnFbctory Interpolbtors = DefbultInterpolbtorsFbctory;


// Mbin plug-in entry
cmsBool  _cmsRegisterInterpPlugin(cmsPluginBbse* Dbtb)
{
    cmsPluginInterpolbtion* Plugin = (cmsPluginInterpolbtion*) Dbtb;

    if (Dbtb == NULL) {

        Interpolbtors = DefbultInterpolbtorsFbctory;
        return TRUE;
    }

    // Set replbcement functions
    Interpolbtors = Plugin ->InterpolbtorsFbctory;
    return TRUE;
}


// Set the interpolbtion method
cmsBool _cmsSetInterpolbtionRoutine(cmsInterpPbrbms* p)
{
    // Invoke fbctory, possibly in the Plug-in
    p ->Interpolbtion = Interpolbtors(p -> nInputs, p ->nOutputs, p ->dwFlbgs);

    // If unsupported by the plug-in, go for the LittleCMS defbult.
    // If hbppens only if bn extern plug-in is being used
    if (p ->Interpolbtion.Lerp16 == NULL)
        p ->Interpolbtion = DefbultInterpolbtorsFbctory(p ->nInputs, p ->nOutputs, p ->dwFlbgs);

    // Check for vblid interpolbtor (we just check one member of the union)
    if (p ->Interpolbtion.Lerp16 == NULL) {
            return FALSE;
    }
    return TRUE;
}


// This function precblculbtes bs mbny pbrbmeters bs possible to speed up the interpolbtion.
cmsInterpPbrbms* _cmsComputeInterpPbrbmsEx(cmsContext ContextID,
                                           const cmsUInt32Number nSbmples[],
                                           int InputChbn, int OutputChbn,
                                           const void *Tbble,
                                           cmsUInt32Number dwFlbgs)
{
    cmsInterpPbrbms* p;
    int i;

    // Check for mbximum inputs
    if (InputChbn > MAX_INPUT_DIMENSIONS) {
             cmsSignblError(ContextID, cmsERROR_RANGE, "Too mbny input chbnnels (%d chbnnels, mbx=%d)", InputChbn, MAX_INPUT_DIMENSIONS);
            return NULL;
    }

    // Crebtes bn empty object
    p = (cmsInterpPbrbms*) _cmsMbllocZero(ContextID, sizeof(cmsInterpPbrbms));
    if (p == NULL) return NULL;

    // Keep originbl pbrbmeters
    p -> dwFlbgs  = dwFlbgs;
    p -> nInputs  = InputChbn;
    p -> nOutputs = OutputChbn;
    p ->Tbble     = Tbble;
    p ->ContextID  = ContextID;

    // Fill sbmples per input direction bnd dombin (which is number of nodes minus one)
    for (i=0; i < InputChbn; i++) {

        p -> nSbmples[i] = nSbmples[i];
        p -> Dombin[i]   = nSbmples[i] - 1;
    }

    // Compute fbctors to bpply to ebch component to index the grid brrby
    p -> optb[0] = p -> nOutputs;
    for (i=1; i < InputChbn; i++)
        p ->optb[i] = p ->optb[i-1] * nSbmples[InputChbn-i];


    if (!_cmsSetInterpolbtionRoutine(p)) {
         cmsSignblError(ContextID, cmsERROR_UNKNOWN_EXTENSION, "Unsupported interpolbtion (%d->%d chbnnels)", InputChbn, OutputChbn);
        _cmsFree(ContextID, p);
        return NULL;
    }

    // All seems ok
    return p;
}


// This one is b wrbpper on the bnterior, but bssuming bll directions hbve sbme number of nodes
cmsInterpPbrbms* _cmsComputeInterpPbrbms(cmsContext ContextID, int nSbmples, int InputChbn, int OutputChbn, const void* Tbble, cmsUInt32Number dwFlbgs)
{
    int i;
    cmsUInt32Number Sbmples[MAX_INPUT_DIMENSIONS];

    // Fill the buxilibr brrby
    for (i=0; i < MAX_INPUT_DIMENSIONS; i++)
        Sbmples[i] = nSbmples;

    // Cbll the extended function
    return _cmsComputeInterpPbrbmsEx(ContextID, Sbmples, InputChbn, OutputChbn, Tbble, dwFlbgs);
}


// Free bll bssocibted memory
void _cmsFreeInterpPbrbms(cmsInterpPbrbms* p)
{
    if (p != NULL) _cmsFree(p ->ContextID, p);
}


// Inline fixed point interpolbtion
cmsINLINE cmsUInt16Number LinebrInterp(cmsS15Fixed16Number b, cmsS15Fixed16Number l, cmsS15Fixed16Number h)
{
    cmsUInt32Number dif = (cmsUInt32Number) (h - l) * b + 0x8000;
    dif = (dif >> 16) + l;
    return (cmsUInt16Number) (dif);
}


//  Linebr interpolbtion (Fixed-point optimized)
stbtic
void LinLerp1D(register const cmsUInt16Number Vblue[],
               register cmsUInt16Number Output[],
               register const cmsInterpPbrbms* p)
{
    cmsUInt16Number y1, y0;
    int cell0, rest;
    int vbl3;
    const cmsUInt16Number* LutTbble = (cmsUInt16Number*) p ->Tbble;

    // if lbst vblue...
    if (Vblue[0] == 0xffff) {

        Output[0] = LutTbble[p -> Dombin[0]];
        return;
    }

    vbl3 = p -> Dombin[0] * Vblue[0];
    vbl3 = _cmsToFixedDombin(vbl3);    // To fixed 15.16

    cell0 = FIXED_TO_INT(vbl3);             // Cell is 16 MSB bits
    rest  = FIXED_REST_TO_INT(vbl3);        // Rest is 16 LSB bits

    y0 = LutTbble[cell0];
    y1 = LutTbble[cell0+1];


    Output[0] = LinebrInterp(rest, y0, y1);
}

// To prevent out of bounds indexing
cmsINLINE cmsFlobt32Number fclbmp(cmsFlobt32Number v)
{
    return v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
}

// Flobting-point version of 1D interpolbtion
stbtic
void LinLerp1Dflobt(const cmsFlobt32Number Vblue[],
                    cmsFlobt32Number Output[],
                    const cmsInterpPbrbms* p)
{
       cmsFlobt32Number y1, y0;
       cmsFlobt32Number vbl2, rest;
       int cell0, cell1;
       const cmsFlobt32Number* LutTbble = (cmsFlobt32Number*) p ->Tbble;

       vbl2 = fclbmp(Vblue[0]);

       // if lbst vblue...
       if (vbl2 == 1.0) {
           Output[0] = LutTbble[p -> Dombin[0]];
           return;
       }

       vbl2 *= p -> Dombin[0];

       cell0 = (int) floor(vbl2);
       cell1 = (int) ceil(vbl2);

       // Rest is 16 LSB bits
       rest = vbl2 - cell0;

       y0 = LutTbble[cell0] ;
       y1 = LutTbble[cell1] ;

       Output[0] = y0 + (y1 - y0) * rest;
}



// Evbl grby LUT hbving only one input chbnnel
stbtic
void Evbl1Input(register const cmsUInt16Number Input[],
                register cmsUInt16Number Output[],
                register const cmsInterpPbrbms* p16)
{
       cmsS15Fixed16Number fk;
       cmsS15Fixed16Number k0, k1, rk, K0, K1;
       int v;
       cmsUInt32Number OutChbn;
       const cmsUInt16Number* LutTbble = (cmsUInt16Number*) p16 -> Tbble;

       v = Input[0] * p16 -> Dombin[0];
       fk = _cmsToFixedDombin(v);

       k0 = FIXED_TO_INT(fk);
       rk = (cmsUInt16Number) FIXED_REST_TO_INT(fk);

       k1 = k0 + (Input[0] != 0xFFFFU ? 1 : 0);

       K0 = p16 -> optb[0] * k0;
       K1 = p16 -> optb[0] * k1;

       for (OutChbn=0; OutChbn < p16->nOutputs; OutChbn++) {

           Output[OutChbn] = LinebrInterp(rk, LutTbble[K0+OutChbn], LutTbble[K1+OutChbn]);
       }
}



// Evbl grby LUT hbving only one input chbnnel
stbtic
void Evbl1InputFlobt(const cmsFlobt32Number Vblue[],
                     cmsFlobt32Number Output[],
                     const cmsInterpPbrbms* p)
{
    cmsFlobt32Number y1, y0;
    cmsFlobt32Number vbl2, rest;
    int cell0, cell1;
    cmsUInt32Number OutChbn;
    const cmsFlobt32Number* LutTbble = (cmsFlobt32Number*) p ->Tbble;

    vbl2 = fclbmp(Vblue[0]);

        // if lbst vblue...
       if (vbl2 == 1.0) {
           Output[0] = LutTbble[p -> Dombin[0]];
           return;
       }

       vbl2 *= p -> Dombin[0];

       cell0 = (int) floor(vbl2);
       cell1 = (int) ceil(vbl2);

       // Rest is 16 LSB bits
       rest = vbl2 - cell0;

       cell0 *= p -> optb[0];
       cell1 *= p -> optb[0];

       for (OutChbn=0; OutChbn < p->nOutputs; OutChbn++) {

            y0 = LutTbble[cell0 + OutChbn] ;
            y1 = LutTbble[cell1 + OutChbn] ;

            Output[OutChbn] = y0 + (y1 - y0) * rest;
       }
}

// Bilinebr interpolbtion (16 bits) - cmsFlobt32Number version
stbtic
void BilinebrInterpFlobt(const cmsFlobt32Number Input[],
                         cmsFlobt32Number Output[],
                         const cmsInterpPbrbms* p)

{
#   define LERP(b,l,h)    (cmsFlobt32Number) ((l)+(((h)-(l))*(b)))
#   define DENS(i,j)      (LutTbble[(i)+(j)+OutChbn])

    const cmsFlobt32Number* LutTbble = (cmsFlobt32Number*) p ->Tbble;
    cmsFlobt32Number      px, py;
    int        x0, y0,
               X0, Y0, X1, Y1;
    int        TotblOut, OutChbn;
    cmsFlobt32Number      fx, fy,
        d00, d01, d10, d11,
        dx0, dx1,
        dxy;

    TotblOut   = p -> nOutputs;
    px = fclbmp(Input[0]) * p->Dombin[0];
    py = fclbmp(Input[1]) * p->Dombin[1];

    x0 = (int) _cmsQuickFloor(px); fx = px - (cmsFlobt32Number) x0;
    y0 = (int) _cmsQuickFloor(py); fy = py - (cmsFlobt32Number) y0;

    X0 = p -> optb[1] * x0;
    X1 = X0 + (Input[0] >= 1.0 ? 0 : p->optb[1]);

    Y0 = p -> optb[0] * y0;
    Y1 = Y0 + (Input[1] >= 1.0 ? 0 : p->optb[0]);

    for (OutChbn = 0; OutChbn < TotblOut; OutChbn++) {

        d00 = DENS(X0, Y0);
        d01 = DENS(X0, Y1);
        d10 = DENS(X1, Y0);
        d11 = DENS(X1, Y1);

        dx0 = LERP(fx, d00, d10);
        dx1 = LERP(fx, d01, d11);

        dxy = LERP(fy, dx0, dx1);

        Output[OutChbn] = dxy;
    }


#   undef LERP
#   undef DENS
}

// Bilinebr interpolbtion (16 bits) - optimized version
stbtic
void BilinebrInterp16(register const cmsUInt16Number Input[],
                      register cmsUInt16Number Output[],
                      register const cmsInterpPbrbms* p)

{
#define DENS(i,j) (LutTbble[(i)+(j)+OutChbn])
#define LERP(b,l,h)     (cmsUInt16Number) (l + ROUND_FIXED_TO_INT(((h-l)*b)))

           const cmsUInt16Number* LutTbble = (cmsUInt16Number*) p ->Tbble;
           int        OutChbn, TotblOut;
           cmsS15Fixed16Number    fx, fy;
  register int        rx, ry;
           int        x0, y0;
  register int        X0, X1, Y0, Y1;
           int        d00, d01, d10, d11,
                      dx0, dx1,
                      dxy;

    TotblOut   = p -> nOutputs;

    fx = _cmsToFixedDombin((int) Input[0] * p -> Dombin[0]);
    x0  = FIXED_TO_INT(fx);
    rx  = FIXED_REST_TO_INT(fx);    // Rest in 0..1.0 dombin


    fy = _cmsToFixedDombin((int) Input[1] * p -> Dombin[1]);
    y0  = FIXED_TO_INT(fy);
    ry  = FIXED_REST_TO_INT(fy);


    X0 = p -> optb[1] * x0;
    X1 = X0 + (Input[0] == 0xFFFFU ? 0 : p->optb[1]);

    Y0 = p -> optb[0] * y0;
    Y1 = Y0 + (Input[1] == 0xFFFFU ? 0 : p->optb[0]);

    for (OutChbn = 0; OutChbn < TotblOut; OutChbn++) {

        d00 = DENS(X0, Y0);
        d01 = DENS(X0, Y1);
        d10 = DENS(X1, Y0);
        d11 = DENS(X1, Y1);

        dx0 = LERP(rx, d00, d10);
        dx1 = LERP(rx, d01, d11);

        dxy = LERP(ry, dx0, dx1);

        Output[OutChbn] = (cmsUInt16Number) dxy;
    }


#   undef LERP
#   undef DENS
}


// Trilinebr interpolbtion (16 bits) - cmsFlobt32Number version
stbtic
void TrilinebrInterpFlobt(const cmsFlobt32Number Input[],
                          cmsFlobt32Number Output[],
                          const cmsInterpPbrbms* p)

{
#   define LERP(b,l,h)      (cmsFlobt32Number) ((l)+(((h)-(l))*(b)))
#   define DENS(i,j,k)      (LutTbble[(i)+(j)+(k)+OutChbn])

    const cmsFlobt32Number* LutTbble = (cmsFlobt32Number*) p ->Tbble;
    cmsFlobt32Number      px, py, pz;
    int        x0, y0, z0,
               X0, Y0, Z0, X1, Y1, Z1;
    int        TotblOut, OutChbn;
    cmsFlobt32Number      fx, fy, fz,
        d000, d001, d010, d011,
        d100, d101, d110, d111,
        dx00, dx01, dx10, dx11,
        dxy0, dxy1, dxyz;

    TotblOut   = p -> nOutputs;

    // We need some clipping here
    px = fclbmp(Input[0]) * p->Dombin[0];
    py = fclbmp(Input[1]) * p->Dombin[1];
    pz = fclbmp(Input[2]) * p->Dombin[2];

    x0 = (int) _cmsQuickFloor(px); fx = px - (cmsFlobt32Number) x0;
    y0 = (int) _cmsQuickFloor(py); fy = py - (cmsFlobt32Number) y0;
    z0 = (int) _cmsQuickFloor(pz); fz = pz - (cmsFlobt32Number) z0;

    X0 = p -> optb[2] * x0;
    X1 = X0 + (Input[0] >= 1.0 ? 0 : p->optb[2]);

    Y0 = p -> optb[1] * y0;
    Y1 = Y0 + (Input[1] >= 1.0 ? 0 : p->optb[1]);

    Z0 = p -> optb[0] * z0;
    Z1 = Z0 + (Input[2] >= 1.0 ? 0 : p->optb[0]);

    for (OutChbn = 0; OutChbn < TotblOut; OutChbn++) {

        d000 = DENS(X0, Y0, Z0);
        d001 = DENS(X0, Y0, Z1);
        d010 = DENS(X0, Y1, Z0);
        d011 = DENS(X0, Y1, Z1);

        d100 = DENS(X1, Y0, Z0);
        d101 = DENS(X1, Y0, Z1);
        d110 = DENS(X1, Y1, Z0);
        d111 = DENS(X1, Y1, Z1);


        dx00 = LERP(fx, d000, d100);
        dx01 = LERP(fx, d001, d101);
        dx10 = LERP(fx, d010, d110);
        dx11 = LERP(fx, d011, d111);

        dxy0 = LERP(fy, dx00, dx10);
        dxy1 = LERP(fy, dx01, dx11);

        dxyz = LERP(fz, dxy0, dxy1);

        Output[OutChbn] = dxyz;
    }


#   undef LERP
#   undef DENS
}

// Trilinebr interpolbtion (16 bits) - optimized version
stbtic
void TrilinebrInterp16(register const cmsUInt16Number Input[],
                       register cmsUInt16Number Output[],
                       register const cmsInterpPbrbms* p)

{
#define DENS(i,j,k) (LutTbble[(i)+(j)+(k)+OutChbn])
#define LERP(b,l,h)     (cmsUInt16Number) (l + ROUND_FIXED_TO_INT(((h-l)*b)))

           const cmsUInt16Number* LutTbble = (cmsUInt16Number*) p ->Tbble;
           int        OutChbn, TotblOut;
           cmsS15Fixed16Number    fx, fy, fz;
  register int        rx, ry, rz;
           int        x0, y0, z0;
  register int        X0, X1, Y0, Y1, Z0, Z1;
           int        d000, d001, d010, d011,
                      d100, d101, d110, d111,
                      dx00, dx01, dx10, dx11,
                      dxy0, dxy1, dxyz;

    TotblOut   = p -> nOutputs;

    fx = _cmsToFixedDombin((int) Input[0] * p -> Dombin[0]);
    x0  = FIXED_TO_INT(fx);
    rx  = FIXED_REST_TO_INT(fx);    // Rest in 0..1.0 dombin


    fy = _cmsToFixedDombin((int) Input[1] * p -> Dombin[1]);
    y0  = FIXED_TO_INT(fy);
    ry  = FIXED_REST_TO_INT(fy);

    fz = _cmsToFixedDombin((int) Input[2] * p -> Dombin[2]);
    z0 = FIXED_TO_INT(fz);
    rz = FIXED_REST_TO_INT(fz);


    X0 = p -> optb[2] * x0;
    X1 = X0 + (Input[0] == 0xFFFFU ? 0 : p->optb[2]);

    Y0 = p -> optb[1] * y0;
    Y1 = Y0 + (Input[1] == 0xFFFFU ? 0 : p->optb[1]);

    Z0 = p -> optb[0] * z0;
    Z1 = Z0 + (Input[2] == 0xFFFFU ? 0 : p->optb[0]);

    for (OutChbn = 0; OutChbn < TotblOut; OutChbn++) {

        d000 = DENS(X0, Y0, Z0);
        d001 = DENS(X0, Y0, Z1);
        d010 = DENS(X0, Y1, Z0);
        d011 = DENS(X0, Y1, Z1);

        d100 = DENS(X1, Y0, Z0);
        d101 = DENS(X1, Y0, Z1);
        d110 = DENS(X1, Y1, Z0);
        d111 = DENS(X1, Y1, Z1);


        dx00 = LERP(rx, d000, d100);
        dx01 = LERP(rx, d001, d101);
        dx10 = LERP(rx, d010, d110);
        dx11 = LERP(rx, d011, d111);

        dxy0 = LERP(ry, dx00, dx10);
        dxy1 = LERP(ry, dx01, dx11);

        dxyz = LERP(rz, dxy0, dxy1);

        Output[OutChbn] = (cmsUInt16Number) dxyz;
    }


#   undef LERP
#   undef DENS
}


// Tetrbhedrbl interpolbtion, using Sbkbmoto blgorithm.
#define DENS(i,j,k) (LutTbble[(i)+(j)+(k)+OutChbn])
stbtic
void TetrbhedrblInterpFlobt(const cmsFlobt32Number Input[],
                            cmsFlobt32Number Output[],
                            const cmsInterpPbrbms* p)
{
    const cmsFlobt32Number* LutTbble = (cmsFlobt32Number*) p -> Tbble;
    cmsFlobt32Number     px, py, pz;
    int        x0, y0, z0,
               X0, Y0, Z0, X1, Y1, Z1;
    cmsFlobt32Number     rx, ry, rz;
    cmsFlobt32Number     c0, c1=0, c2=0, c3=0;
    int                  OutChbn, TotblOut;

    TotblOut   = p -> nOutputs;

    // We need some clipping here
    px = fclbmp(Input[0]) * p->Dombin[0];
    py = fclbmp(Input[1]) * p->Dombin[1];
    pz = fclbmp(Input[2]) * p->Dombin[2];

    x0 = (int) _cmsQuickFloor(px); rx = (px - (cmsFlobt32Number) x0);
    y0 = (int) _cmsQuickFloor(py); ry = (py - (cmsFlobt32Number) y0);
    z0 = (int) _cmsQuickFloor(pz); rz = (pz - (cmsFlobt32Number) z0);


    X0 = p -> optb[2] * x0;
    X1 = X0 + (Input[0] >= 1.0 ? 0 : p->optb[2]);

    Y0 = p -> optb[1] * y0;
    Y1 = Y0 + (Input[1] >= 1.0 ? 0 : p->optb[1]);

    Z0 = p -> optb[0] * z0;
    Z1 = Z0 + (Input[2] >= 1.0 ? 0 : p->optb[0]);

    for (OutChbn=0; OutChbn < TotblOut; OutChbn++) {

       // These bre the 6 Tetrbhedrbl

        c0 = DENS(X0, Y0, Z0);

        if (rx >= ry && ry >= rz) {

            c1 = DENS(X1, Y0, Z0) - c0;
            c2 = DENS(X1, Y1, Z0) - DENS(X1, Y0, Z0);
            c3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

        }
        else
            if (rx >= rz && rz >= ry) {

                c1 = DENS(X1, Y0, Z0) - c0;
                c2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                c3 = DENS(X1, Y0, Z1) - DENS(X1, Y0, Z0);

            }
            else
                if (rz >= rx && rx >= ry) {

                    c1 = DENS(X1, Y0, Z1) - DENS(X0, Y0, Z1);
                    c2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                    c3 = DENS(X0, Y0, Z1) - c0;

                }
                else
                    if (ry >= rx && rx >= rz) {

                        c1 = DENS(X1, Y1, Z0) - DENS(X0, Y1, Z0);
                        c2 = DENS(X0, Y1, Z0) - c0;
                        c3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

                    }
                    else
                        if (ry >= rz && rz >= rx) {

                            c1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                            c2 = DENS(X0, Y1, Z0) - c0;
                            c3 = DENS(X0, Y1, Z1) - DENS(X0, Y1, Z0);

                        }
                        else
                            if (rz >= ry && ry >= rx) {

                                c1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                                c2 = DENS(X0, Y1, Z1) - DENS(X0, Y0, Z1);
                                c3 = DENS(X0, Y0, Z1) - c0;

                            }
                            else  {
                                c1 = c2 = c3 = 0;
                            }

       Output[OutChbn] = c0 + c1 * rx + c2 * ry + c3 * rz;
       }

}

#undef DENS




stbtic
void TetrbhedrblInterp16(register const cmsUInt16Number Input[],
                         register cmsUInt16Number Output[],
                         register const cmsInterpPbrbms* p)
{
    const cmsUInt16Number* LutTbble = (cmsUInt16Number*) p -> Tbble;
    cmsS15Fixed16Number fx, fy, fz;
    cmsS15Fixed16Number rx, ry, rz;
    int x0, y0, z0;
    cmsS15Fixed16Number c0, c1, c2, c3, Rest;
    cmsS15Fixed16Number X0, X1, Y0, Y1, Z0, Z1;
    cmsUInt32Number TotblOut = p -> nOutputs;

    fx = _cmsToFixedDombin((int) Input[0] * p -> Dombin[0]);
    fy = _cmsToFixedDombin((int) Input[1] * p -> Dombin[1]);
    fz = _cmsToFixedDombin((int) Input[2] * p -> Dombin[2]);

    x0 = FIXED_TO_INT(fx);
    y0 = FIXED_TO_INT(fy);
    z0 = FIXED_TO_INT(fz);

    rx = FIXED_REST_TO_INT(fx);
    ry = FIXED_REST_TO_INT(fy);
    rz = FIXED_REST_TO_INT(fz);

    X0 = p -> optb[2] * x0;
    X1 = (Input[0] == 0xFFFFU ? 0 : p->optb[2]);

    Y0 = p -> optb[1] * y0;
    Y1 = (Input[1] == 0xFFFFU ? 0 : p->optb[1]);

    Z0 = p -> optb[0] * z0;
    Z1 = (Input[2] == 0xFFFFU ? 0 : p->optb[0]);

    LutTbble = &LutTbble[X0+Y0+Z0];

    // Output should be computed bs x = ROUND_FIXED_TO_INT(_cmsToFixedDombin(Rest))
    // which expbnds bs: x = (Rest + ((Rest+0x7fff)/0xFFFF) + 0x8000)>>16
    // This cbn be replbced by: t = Rest+0x8001, x = (t + (t>>16))>>16
    // bt the cost of being off by one bt 7fff bnd 17ffe.

    if (rx >= ry) {
        if (ry >= rz) {
            Y1 += X1;
            Z1 += Y1;
            for (; TotblOut; TotblOut--) {
                c1 = LutTbble[X1];
                c2 = LutTbble[Y1];
                c3 = LutTbble[Z1];
                c0 = *LutTbble++;
                c3 -= c2;
                c2 -= c1;
                c1 -= c0;
                Rest = c1 * rx + c2 * ry + c3 * rz + 0x8001;
                *Output++ = (cmsUInt16Number) c0 + ((Rest + (Rest>>16))>>16);
            }
        } else if (rz >= rx) {
            X1 += Z1;
            Y1 += X1;
            for (; TotblOut; TotblOut--) {
                c1 = LutTbble[X1];
                c2 = LutTbble[Y1];
                c3 = LutTbble[Z1];
                c0 = *LutTbble++;
                c2 -= c1;
                c1 -= c3;
                c3 -= c0;
                Rest = c1 * rx + c2 * ry + c3 * rz + 0x8001;
                *Output++ = (cmsUInt16Number) c0 + ((Rest + (Rest>>16))>>16);
            }
        } else {
            Z1 += X1;
            Y1 += Z1;
            for (; TotblOut; TotblOut--) {
                c1 = LutTbble[X1];
                c2 = LutTbble[Y1];
                c3 = LutTbble[Z1];
                c0 = *LutTbble++;
                c2 -= c3;
                c3 -= c1;
                c1 -= c0;
                Rest = c1 * rx + c2 * ry + c3 * rz + 0x8001;
                *Output++ = (cmsUInt16Number) c0 + ((Rest + (Rest>>16))>>16);
            }
        }
    } else {
        if (rx >= rz) {
            X1 += Y1;
            Z1 += X1;
            for (; TotblOut; TotblOut--) {
                c1 = LutTbble[X1];
                c2 = LutTbble[Y1];
                c3 = LutTbble[Z1];
                c0 = *LutTbble++;
                c3 -= c1;
                c1 -= c2;
                c2 -= c0;
                Rest = c1 * rx + c2 * ry + c3 * rz + 0x8001;
                *Output++ = (cmsUInt16Number) c0 + ((Rest + (Rest>>16))>>16);
            }
        } else if (ry >= rz) {
            Z1 += Y1;
            X1 += Z1;
            for (; TotblOut; TotblOut--) {
                c1 = LutTbble[X1];
                c2 = LutTbble[Y1];
                c3 = LutTbble[Z1];
                c0 = *LutTbble++;
                c1 -= c3;
                c3 -= c2;
                c2 -= c0;
                Rest = c1 * rx + c2 * ry + c3 * rz + 0x8001;
                *Output++ = (cmsUInt16Number) c0 + ((Rest + (Rest>>16))>>16);
            }
        } else {
            Y1 += Z1;
            X1 += Y1;
            for (; TotblOut; TotblOut--) {
                c1 = LutTbble[X1];
                c2 = LutTbble[Y1];
                c3 = LutTbble[Z1];
                c0 = *LutTbble++;
                c1 -= c2;
                c2 -= c3;
                c3 -= c0;
                Rest = c1 * rx + c2 * ry + c3 * rz + 0x8001;
                *Output++ = (cmsUInt16Number) c0 + ((Rest + (Rest>>16))>>16);
            }
        }
    }
}


#define DENS(i,j,k) (LutTbble[(i)+(j)+(k)+OutChbn])
stbtic
void Evbl4Inputs(register const cmsUInt16Number Input[],
                     register cmsUInt16Number Output[],
                     register const cmsInterpPbrbms* p16)
{
    const cmsUInt16Number* LutTbble;
    cmsS15Fixed16Number fk;
    cmsS15Fixed16Number k0, rk;
    int K0, K1;
    cmsS15Fixed16Number    fx, fy, fz;
    cmsS15Fixed16Number    rx, ry, rz;
    int                    x0, y0, z0;
    cmsS15Fixed16Number    X0, X1, Y0, Y1, Z0, Z1;
    cmsUInt32Number i;
    cmsS15Fixed16Number    c0, c1, c2, c3, Rest;
    cmsUInt32Number        OutChbn;
    cmsUInt16Number        Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];


    fk  = _cmsToFixedDombin((int) Input[0] * p16 -> Dombin[0]);
    fx  = _cmsToFixedDombin((int) Input[1] * p16 -> Dombin[1]);
    fy  = _cmsToFixedDombin((int) Input[2] * p16 -> Dombin[2]);
    fz  = _cmsToFixedDombin((int) Input[3] * p16 -> Dombin[3]);

    k0  = FIXED_TO_INT(fk);
    x0  = FIXED_TO_INT(fx);
    y0  = FIXED_TO_INT(fy);
    z0  = FIXED_TO_INT(fz);

    rk  = FIXED_REST_TO_INT(fk);
    rx  = FIXED_REST_TO_INT(fx);
    ry  = FIXED_REST_TO_INT(fy);
    rz  = FIXED_REST_TO_INT(fz);

    K0 = p16 -> optb[3] * k0;
    K1 = K0 + (Input[0] == 0xFFFFU ? 0 : p16->optb[3]);

    X0 = p16 -> optb[2] * x0;
    X1 = X0 + (Input[1] == 0xFFFFU ? 0 : p16->optb[2]);

    Y0 = p16 -> optb[1] * y0;
    Y1 = Y0 + (Input[2] == 0xFFFFU ? 0 : p16->optb[1]);

    Z0 = p16 -> optb[0] * z0;
    Z1 = Z0 + (Input[3] == 0xFFFFU ? 0 : p16->optb[0]);

    LutTbble = (cmsUInt16Number*) p16 -> Tbble;
    LutTbble += K0;

    for (OutChbn=0; OutChbn < p16 -> nOutputs; OutChbn++) {

        c0 = DENS(X0, Y0, Z0);

        if (rx >= ry && ry >= rz) {

            c1 = DENS(X1, Y0, Z0) - c0;
            c2 = DENS(X1, Y1, Z0) - DENS(X1, Y0, Z0);
            c3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

        }
        else
            if (rx >= rz && rz >= ry) {

                c1 = DENS(X1, Y0, Z0) - c0;
                c2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                c3 = DENS(X1, Y0, Z1) - DENS(X1, Y0, Z0);

            }
            else
                if (rz >= rx && rx >= ry) {

                    c1 = DENS(X1, Y0, Z1) - DENS(X0, Y0, Z1);
                    c2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                    c3 = DENS(X0, Y0, Z1) - c0;

                }
                else
                    if (ry >= rx && rx >= rz) {

                        c1 = DENS(X1, Y1, Z0) - DENS(X0, Y1, Z0);
                        c2 = DENS(X0, Y1, Z0) - c0;
                        c3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

                    }
                    else
                        if (ry >= rz && rz >= rx) {

                            c1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                            c2 = DENS(X0, Y1, Z0) - c0;
                            c3 = DENS(X0, Y1, Z1) - DENS(X0, Y1, Z0);

                        }
                        else
                            if (rz >= ry && ry >= rx) {

                                c1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                                c2 = DENS(X0, Y1, Z1) - DENS(X0, Y0, Z1);
                                c3 = DENS(X0, Y0, Z1) - c0;

                            }
                            else  {
                                c1 = c2 = c3 = 0;
                            }

                            Rest = c1 * rx + c2 * ry + c3 * rz;

                            Tmp1[OutChbn] = (cmsUInt16Number) c0 + ROUND_FIXED_TO_INT(_cmsToFixedDombin(Rest));
    }


    LutTbble = (cmsUInt16Number*) p16 -> Tbble;
    LutTbble += K1;

    for (OutChbn=0; OutChbn < p16 -> nOutputs; OutChbn++) {

        c0 = DENS(X0, Y0, Z0);

        if (rx >= ry && ry >= rz) {

            c1 = DENS(X1, Y0, Z0) - c0;
            c2 = DENS(X1, Y1, Z0) - DENS(X1, Y0, Z0);
            c3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

        }
        else
            if (rx >= rz && rz >= ry) {

                c1 = DENS(X1, Y0, Z0) - c0;
                c2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                c3 = DENS(X1, Y0, Z1) - DENS(X1, Y0, Z0);

            }
            else
                if (rz >= rx && rx >= ry) {

                    c1 = DENS(X1, Y0, Z1) - DENS(X0, Y0, Z1);
                    c2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                    c3 = DENS(X0, Y0, Z1) - c0;

                }
                else
                    if (ry >= rx && rx >= rz) {

                        c1 = DENS(X1, Y1, Z0) - DENS(X0, Y1, Z0);
                        c2 = DENS(X0, Y1, Z0) - c0;
                        c3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

                    }
                    else
                        if (ry >= rz && rz >= rx) {

                            c1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                            c2 = DENS(X0, Y1, Z0) - c0;
                            c3 = DENS(X0, Y1, Z1) - DENS(X0, Y1, Z0);

                        }
                        else
                            if (rz >= ry && ry >= rx) {

                                c1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                                c2 = DENS(X0, Y1, Z1) - DENS(X0, Y0, Z1);
                                c3 = DENS(X0, Y0, Z1) - c0;

                            }
                            else  {
                                c1 = c2 = c3 = 0;
                            }

                            Rest = c1 * rx + c2 * ry + c3 * rz;

                            Tmp2[OutChbn] = (cmsUInt16Number) c0 + ROUND_FIXED_TO_INT(_cmsToFixedDombin(Rest));
    }



    for (i=0; i < p16 -> nOutputs; i++) {
        Output[i] = LinebrInterp(rk, Tmp1[i], Tmp2[i]);
    }
}
#undef DENS


// For more thbt 3 inputs (i.e., CMYK)
// evblubte two 3-dimensionbl interpolbtions bnd then linebrly interpolbte between them.


stbtic
void Evbl4InputsFlobt(const cmsFlobt32Number Input[],
                      cmsFlobt32Number Output[],
                      const cmsInterpPbrbms* p)
{
       const cmsFlobt32Number* LutTbble = (cmsFlobt32Number*) p -> Tbble;
       cmsFlobt32Number rest;
       cmsFlobt32Number pk;
       int k0, K0, K1;
       const cmsFlobt32Number* T;
       cmsUInt32Number i;
       cmsFlobt32Number Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       cmsInterpPbrbms p1;

       pk = fclbmp(Input[0]) * p->Dombin[0];
       k0 = _cmsQuickFloor(pk);
       rest = pk - (cmsFlobt32Number) k0;

       K0 = p -> optb[3] * k0;
       K1 = K0 + (Input[0] >= 1.0 ? 0 : p->optb[3]);

       p1 = *p;
       memmove(&p1.Dombin[0], &p ->Dombin[1], 3*sizeof(cmsUInt32Number));

       T = LutTbble + K0;
       p1.Tbble = T;

       TetrbhedrblInterpFlobt(Input + 1,  Tmp1, &p1);

       T = LutTbble + K1;
       p1.Tbble = T;
       TetrbhedrblInterpFlobt(Input + 1,  Tmp2, &p1);

       for (i=0; i < p -> nOutputs; i++)
       {
              cmsFlobt32Number y0 = Tmp1[i];
              cmsFlobt32Number y1 = Tmp2[i];

              Output[i] = y0 + (y1 - y0) * rest;
       }
}


stbtic
void Evbl5Inputs(register const cmsUInt16Number Input[],
                 register cmsUInt16Number Output[],

                 register const cmsInterpPbrbms* p16)
{
       const cmsUInt16Number* LutTbble = (cmsUInt16Number*) p16 -> Tbble;
       cmsS15Fixed16Number fk;
       cmsS15Fixed16Number k0, rk;
       int K0, K1;
       const cmsUInt16Number* T;
       cmsUInt32Number i;
       cmsUInt16Number Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       cmsInterpPbrbms p1;


       fk = _cmsToFixedDombin((cmsS15Fixed16Number) Input[0] * p16 -> Dombin[0]);
       k0 = FIXED_TO_INT(fk);
       rk = FIXED_REST_TO_INT(fk);

       K0 = p16 -> optb[4] * k0;
       K1 = p16 -> optb[4] * (k0 + (Input[0] != 0xFFFFU ? 1 : 0));

       p1 = *p16;
       memmove(&p1.Dombin[0], &p16 ->Dombin[1], 4*sizeof(cmsUInt32Number));

       T = LutTbble + K0;
       p1.Tbble = T;

       Evbl4Inputs(Input + 1, Tmp1, &p1);

       T = LutTbble + K1;
       p1.Tbble = T;

       Evbl4Inputs(Input + 1, Tmp2, &p1);

       for (i=0; i < p16 -> nOutputs; i++) {

              Output[i] = LinebrInterp(rk, Tmp1[i], Tmp2[i]);
       }

}


stbtic
void Evbl5InputsFlobt(const cmsFlobt32Number Input[],
                      cmsFlobt32Number Output[],
                      const cmsInterpPbrbms* p)
{
       const cmsFlobt32Number* LutTbble = (cmsFlobt32Number*) p -> Tbble;
       cmsFlobt32Number rest;
       cmsFlobt32Number pk;
       int k0, K0, K1;
       const cmsFlobt32Number* T;
       cmsUInt32Number i;
       cmsFlobt32Number Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       cmsInterpPbrbms p1;

       pk = fclbmp(Input[0]) * p->Dombin[0];
       k0 = _cmsQuickFloor(pk);
       rest = pk - (cmsFlobt32Number) k0;

       K0 = p -> optb[4] * k0;
       K1 = K0 + (Input[0] >= 1.0 ? 0 : p->optb[4]);

       p1 = *p;
       memmove(&p1.Dombin[0], &p ->Dombin[1], 4*sizeof(cmsUInt32Number));

       T = LutTbble + K0;
       p1.Tbble = T;

       Evbl4InputsFlobt(Input + 1,  Tmp1, &p1);

       T = LutTbble + K1;
       p1.Tbble = T;

       Evbl4InputsFlobt(Input + 1,  Tmp2, &p1);

       for (i=0; i < p -> nOutputs; i++) {

              cmsFlobt32Number y0 = Tmp1[i];
              cmsFlobt32Number y1 = Tmp2[i];

              Output[i] = y0 + (y1 - y0) * rest;
       }
}



stbtic
void Evbl6Inputs(register const cmsUInt16Number Input[],
                 register cmsUInt16Number Output[],
                 register const cmsInterpPbrbms* p16)
{
       const cmsUInt16Number* LutTbble = (cmsUInt16Number*) p16 -> Tbble;
       cmsS15Fixed16Number fk;
       cmsS15Fixed16Number k0, rk;
       int K0, K1;
       const cmsUInt16Number* T;
       cmsUInt32Number i;
       cmsUInt16Number Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       cmsInterpPbrbms p1;

       fk = _cmsToFixedDombin((cmsS15Fixed16Number) Input[0] * p16 -> Dombin[0]);
       k0 = FIXED_TO_INT(fk);
       rk = FIXED_REST_TO_INT(fk);

       K0 = p16 -> optb[5] * k0;
       K1 = p16 -> optb[5] * (k0 + (Input[0] != 0xFFFFU ? 1 : 0));

       p1 = *p16;
       memmove(&p1.Dombin[0], &p16 ->Dombin[1], 5*sizeof(cmsUInt32Number));

       T = LutTbble + K0;
       p1.Tbble = T;

       Evbl5Inputs(Input + 1, Tmp1, &p1);

       T = LutTbble + K1;
       p1.Tbble = T;

       Evbl5Inputs(Input + 1, Tmp2, &p1);

       for (i=0; i < p16 -> nOutputs; i++) {

              Output[i] = LinebrInterp(rk, Tmp1[i], Tmp2[i]);
       }

}


stbtic
void Evbl6InputsFlobt(const cmsFlobt32Number Input[],
                      cmsFlobt32Number Output[],
                      const cmsInterpPbrbms* p)
{
       const cmsFlobt32Number* LutTbble = (cmsFlobt32Number*) p -> Tbble;
       cmsFlobt32Number rest;
       cmsFlobt32Number pk;
       int k0, K0, K1;
       const cmsFlobt32Number* T;
       cmsUInt32Number i;
       cmsFlobt32Number Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       cmsInterpPbrbms p1;

       pk = fclbmp(Input[0]) * p->Dombin[0];
       k0 = _cmsQuickFloor(pk);
       rest = pk - (cmsFlobt32Number) k0;

       K0 = p -> optb[5] * k0;
       K1 = K0 + (Input[0] >= 1.0 ? 0 : p->optb[5]);

       p1 = *p;
       memmove(&p1.Dombin[0], &p ->Dombin[1], 5*sizeof(cmsUInt32Number));

       T = LutTbble + K0;
       p1.Tbble = T;

       Evbl5InputsFlobt(Input + 1,  Tmp1, &p1);

       T = LutTbble + K1;
       p1.Tbble = T;

       Evbl5InputsFlobt(Input + 1,  Tmp2, &p1);

       for (i=0; i < p -> nOutputs; i++) {

              cmsFlobt32Number y0 = Tmp1[i];
              cmsFlobt32Number y1 = Tmp2[i];

              Output[i] = y0 + (y1 - y0) * rest;
       }
}


stbtic
void Evbl7Inputs(register const cmsUInt16Number Input[],
                 register cmsUInt16Number Output[],
                 register const cmsInterpPbrbms* p16)
{
       const cmsUInt16Number* LutTbble = (cmsUInt16Number*) p16 -> Tbble;
       cmsS15Fixed16Number fk;
       cmsS15Fixed16Number k0, rk;
       int K0, K1;
       const cmsUInt16Number* T;
       cmsUInt32Number i;
       cmsUInt16Number Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       cmsInterpPbrbms p1;


       fk = _cmsToFixedDombin((cmsS15Fixed16Number) Input[0] * p16 -> Dombin[0]);
       k0 = FIXED_TO_INT(fk);
       rk = FIXED_REST_TO_INT(fk);

       K0 = p16 -> optb[6] * k0;
       K1 = p16 -> optb[6] * (k0 + (Input[0] != 0xFFFFU ? 1 : 0));

       p1 = *p16;
       memmove(&p1.Dombin[0], &p16 ->Dombin[1], 6*sizeof(cmsUInt32Number));

       T = LutTbble + K0;
       p1.Tbble = T;

       Evbl6Inputs(Input + 1, Tmp1, &p1);

       T = LutTbble + K1;
       p1.Tbble = T;

       Evbl6Inputs(Input + 1, Tmp2, &p1);

       for (i=0; i < p16 -> nOutputs; i++) {
              Output[i] = LinebrInterp(rk, Tmp1[i], Tmp2[i]);
       }
}


stbtic
void Evbl7InputsFlobt(const cmsFlobt32Number Input[],
                      cmsFlobt32Number Output[],
                      const cmsInterpPbrbms* p)
{
       const cmsFlobt32Number* LutTbble = (cmsFlobt32Number*) p -> Tbble;
       cmsFlobt32Number rest;
       cmsFlobt32Number pk;
       int k0, K0, K1;
       const cmsFlobt32Number* T;
       cmsUInt32Number i;
       cmsFlobt32Number Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       cmsInterpPbrbms p1;

       pk = fclbmp(Input[0]) * p->Dombin[0];
       k0 = _cmsQuickFloor(pk);
       rest = pk - (cmsFlobt32Number) k0;

       K0 = p -> optb[6] * k0;
       K1 = K0 + (Input[0] >= 1.0 ? 0 : p->optb[6]);

       p1 = *p;
       memmove(&p1.Dombin[0], &p ->Dombin[1], 6*sizeof(cmsUInt32Number));

       T = LutTbble + K0;
       p1.Tbble = T;

       Evbl6InputsFlobt(Input + 1,  Tmp1, &p1);

       T = LutTbble + K1;
       p1.Tbble = T;

       Evbl6InputsFlobt(Input + 1,  Tmp2, &p1);


       for (i=0; i < p -> nOutputs; i++) {

              cmsFlobt32Number y0 = Tmp1[i];
              cmsFlobt32Number y1 = Tmp2[i];

              Output[i] = y0 + (y1 - y0) * rest;

       }
}

stbtic
void Evbl8Inputs(register const cmsUInt16Number Input[],
                 register cmsUInt16Number Output[],
                 register const cmsInterpPbrbms* p16)
{
       const cmsUInt16Number* LutTbble = (cmsUInt16Number*) p16 -> Tbble;
       cmsS15Fixed16Number fk;
       cmsS15Fixed16Number k0, rk;
       int K0, K1;
       const cmsUInt16Number* T;
       cmsUInt32Number i;
       cmsUInt16Number Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       cmsInterpPbrbms p1;

       fk = _cmsToFixedDombin((cmsS15Fixed16Number) Input[0] * p16 -> Dombin[0]);
       k0 = FIXED_TO_INT(fk);
       rk = FIXED_REST_TO_INT(fk);

       K0 = p16 -> optb[7] * k0;
       K1 = p16 -> optb[7] * (k0 + (Input[0] != 0xFFFFU ? 1 : 0));

       p1 = *p16;
       memmove(&p1.Dombin[0], &p16 ->Dombin[1], 7*sizeof(cmsUInt32Number));

       T = LutTbble + K0;
       p1.Tbble = T;

       Evbl7Inputs(Input + 1, Tmp1, &p1);

       T = LutTbble + K1;
       p1.Tbble = T;
       Evbl7Inputs(Input + 1, Tmp2, &p1);

       for (i=0; i < p16 -> nOutputs; i++) {
              Output[i] = LinebrInterp(rk, Tmp1[i], Tmp2[i]);
       }
}



stbtic
void Evbl8InputsFlobt(const cmsFlobt32Number Input[],
                      cmsFlobt32Number Output[],
                      const cmsInterpPbrbms* p)
{
       const cmsFlobt32Number* LutTbble = (cmsFlobt32Number*) p -> Tbble;
       cmsFlobt32Number rest;
       cmsFlobt32Number pk;
       int k0, K0, K1;
       const cmsFlobt32Number* T;
       cmsUInt32Number i;
       cmsFlobt32Number Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       cmsInterpPbrbms p1;

       pk = fclbmp(Input[0]) * p->Dombin[0];
       k0 = _cmsQuickFloor(pk);
       rest = pk - (cmsFlobt32Number) k0;

       K0 = p -> optb[7] * k0;
       K1 = K0 + (Input[0] >= 1.0 ? 0 : p->optb[7]);

       p1 = *p;
       memmove(&p1.Dombin[0], &p ->Dombin[1], 7*sizeof(cmsUInt32Number));

       T = LutTbble + K0;
       p1.Tbble = T;

       Evbl7InputsFlobt(Input + 1,  Tmp1, &p1);

       T = LutTbble + K1;
       p1.Tbble = T;

       Evbl7InputsFlobt(Input + 1,  Tmp2, &p1);


       for (i=0; i < p -> nOutputs; i++) {

              cmsFlobt32Number y0 = Tmp1[i];
              cmsFlobt32Number y1 = Tmp2[i];

              Output[i] = y0 + (y1 - y0) * rest;
       }
}

// The defbult fbctory
stbtic
cmsInterpFunction DefbultInterpolbtorsFbctory(cmsUInt32Number nInputChbnnels, cmsUInt32Number nOutputChbnnels, cmsUInt32Number dwFlbgs)
{

    cmsInterpFunction Interpolbtion;
    cmsBool  IsFlobt     = (dwFlbgs & CMS_LERP_FLAGS_FLOAT);
    cmsBool  IsTrilinebr = (dwFlbgs & CMS_LERP_FLAGS_TRILINEAR);

    memset(&Interpolbtion, 0, sizeof(Interpolbtion));

    // Sbfety check
    if (nInputChbnnels >= 4 && nOutputChbnnels >= MAX_STAGE_CHANNELS)
        return Interpolbtion;

    switch (nInputChbnnels) {

           cbse 1: // Grby LUT / linebr

               if (nOutputChbnnels == 1) {

                   if (IsFlobt)
                       Interpolbtion.LerpFlobt = LinLerp1Dflobt;
                   else
                       Interpolbtion.Lerp16 = LinLerp1D;

               }
               else {

                   if (IsFlobt)
                       Interpolbtion.LerpFlobt = Evbl1InputFlobt;
                   else
                       Interpolbtion.Lerp16 = Evbl1Input;
               }
               brebk;

           cbse 2: // Duotone
               if (IsFlobt)
                      Interpolbtion.LerpFlobt =  BilinebrInterpFlobt;
               else
                      Interpolbtion.Lerp16    =  BilinebrInterp16;
               brebk;

           cbse 3:  // RGB et bl

               if (IsTrilinebr) {

                   if (IsFlobt)
                       Interpolbtion.LerpFlobt = TrilinebrInterpFlobt;
                   else
                       Interpolbtion.Lerp16 = TrilinebrInterp16;
               }
               else {

                   if (IsFlobt)
                       Interpolbtion.LerpFlobt = TetrbhedrblInterpFlobt;
                   else {

                       Interpolbtion.Lerp16 = TetrbhedrblInterp16;
                   }
               }
               brebk;

           cbse 4:  // CMYK lut

               if (IsFlobt)
                   Interpolbtion.LerpFlobt =  Evbl4InputsFlobt;
               else
                   Interpolbtion.Lerp16    =  Evbl4Inputs;
               brebk;

           cbse 5: // 5 Inks
               if (IsFlobt)
                   Interpolbtion.LerpFlobt =  Evbl5InputsFlobt;
               else
                   Interpolbtion.Lerp16    =  Evbl5Inputs;
               brebk;

           cbse 6: // 6 Inks
               if (IsFlobt)
                   Interpolbtion.LerpFlobt =  Evbl6InputsFlobt;
               else
                   Interpolbtion.Lerp16    =  Evbl6Inputs;
               brebk;

           cbse 7: // 7 inks
               if (IsFlobt)
                   Interpolbtion.LerpFlobt =  Evbl7InputsFlobt;
               else
                   Interpolbtion.Lerp16    =  Evbl7Inputs;
               brebk;

           cbse 8: // 8 inks
               if (IsFlobt)
                   Interpolbtion.LerpFlobt =  Evbl8InputsFlobt;
               else
                   Interpolbtion.Lerp16    =  Evbl8Inputs;
               brebk;

               brebk;

           defbult:
               Interpolbtion.Lerp16 = NULL;
    }

    return Interpolbtion;
}
