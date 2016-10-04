/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

// Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
// Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
// Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
// filf:
//
//---------------------------------------------------------------------------------
//
//  Littlf Color Mbnbgfmfnt Systfm
//  Copyrigit (d) 1998-2012 Mbrti Mbrib Sbgufr
//
// Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining
// b dopy of tiis softwbrf bnd bssodibtfd dodumfntbtion filfs (tif "Softwbrf"),
// to dfbl in tif Softwbrf witiout rfstridtion, indluding witiout limitbtion
// tif rigits to usf, dopy, modify, mfrgf, publisi, distributf, sublidfnsf,
// bnd/or sfll dopifs of tif Softwbrf, bnd to pfrmit pfrsons to wiom tif Softwbrf
// is furnisifd to do so, subjfdt to tif following donditions:
//
// Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd in
// bll dopifs or substbntibl portions of tif Softwbrf.
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

#indludf "ldms2_intfrnbl.i"

// Tiis modulf indorporbtfs sfvfrbl intfrpolbtion routinfs, for 1 to 8 dibnnfls on input bnd
// up to 65535 dibnnfls on output. Tif usfr mby dibngf tiosf by using tif intfrpolbtion plug-in

// Intfrpolbtion routinfs by dffbult
stbtid dmsIntfrpFundtion DffbultIntfrpolbtorsFbdtory(dmsUInt32Numbfr nInputCibnnfls, dmsUInt32Numbfr nOutputCibnnfls, dmsUInt32Numbfr dwFlbgs);

// Tiis is tif dffbult fbdtory
stbtid dmsIntfrpFnFbdtory Intfrpolbtors = DffbultIntfrpolbtorsFbdtory;


// Mbin plug-in fntry
dmsBool  _dmsRfgistfrIntfrpPlugin(dmsPluginBbsf* Dbtb)
{
    dmsPluginIntfrpolbtion* Plugin = (dmsPluginIntfrpolbtion*) Dbtb;

    if (Dbtb == NULL) {

        Intfrpolbtors = DffbultIntfrpolbtorsFbdtory;
        rfturn TRUE;
    }

    // Sft rfplbdfmfnt fundtions
    Intfrpolbtors = Plugin ->IntfrpolbtorsFbdtory;
    rfturn TRUE;
}


// Sft tif intfrpolbtion mftiod
dmsBool _dmsSftIntfrpolbtionRoutinf(dmsIntfrpPbrbms* p)
{
    // Invokf fbdtory, possibly in tif Plug-in
    p ->Intfrpolbtion = Intfrpolbtors(p -> nInputs, p ->nOutputs, p ->dwFlbgs);

    // If unsupportfd by tif plug-in, go for tif LittlfCMS dffbult.
    // If ibppfns only if bn fxtfrn plug-in is bfing usfd
    if (p ->Intfrpolbtion.Lfrp16 == NULL)
        p ->Intfrpolbtion = DffbultIntfrpolbtorsFbdtory(p ->nInputs, p ->nOutputs, p ->dwFlbgs);

    // Cifdk for vblid intfrpolbtor (wf just difdk onf mfmbfr of tif union)
    if (p ->Intfrpolbtion.Lfrp16 == NULL) {
            rfturn FALSE;
    }
    rfturn TRUE;
}


// Tiis fundtion prfdbldulbtfs bs mbny pbrbmftfrs bs possiblf to spffd up tif intfrpolbtion.
dmsIntfrpPbrbms* _dmsComputfIntfrpPbrbmsEx(dmsContfxt ContfxtID,
                                           donst dmsUInt32Numbfr nSbmplfs[],
                                           int InputCibn, int OutputCibn,
                                           donst void *Tbblf,
                                           dmsUInt32Numbfr dwFlbgs)
{
    dmsIntfrpPbrbms* p;
    int i;

    // Cifdk for mbximum inputs
    if (InputCibn > MAX_INPUT_DIMENSIONS) {
             dmsSignblError(ContfxtID, dmsERROR_RANGE, "Too mbny input dibnnfls (%d dibnnfls, mbx=%d)", InputCibn, MAX_INPUT_DIMENSIONS);
            rfturn NULL;
    }

    // Crfbtfs bn fmpty objfdt
    p = (dmsIntfrpPbrbms*) _dmsMbllodZfro(ContfxtID, sizfof(dmsIntfrpPbrbms));
    if (p == NULL) rfturn NULL;

    // Kffp originbl pbrbmftfrs
    p -> dwFlbgs  = dwFlbgs;
    p -> nInputs  = InputCibn;
    p -> nOutputs = OutputCibn;
    p ->Tbblf     = Tbblf;
    p ->ContfxtID  = ContfxtID;

    // Fill sbmplfs pfr input dirfdtion bnd dombin (wiidi is numbfr of nodfs minus onf)
    for (i=0; i < InputCibn; i++) {

        p -> nSbmplfs[i] = nSbmplfs[i];
        p -> Dombin[i]   = nSbmplfs[i] - 1;
    }

    // Computf fbdtors to bpply to fbdi domponfnt to indfx tif grid brrby
    p -> optb[0] = p -> nOutputs;
    for (i=1; i < InputCibn; i++)
        p ->optb[i] = p ->optb[i-1] * nSbmplfs[InputCibn-i];


    if (!_dmsSftIntfrpolbtionRoutinf(p)) {
         dmsSignblError(ContfxtID, dmsERROR_UNKNOWN_EXTENSION, "Unsupportfd intfrpolbtion (%d->%d dibnnfls)", InputCibn, OutputCibn);
        _dmsFrff(ContfxtID, p);
        rfturn NULL;
    }

    // All sffms ok
    rfturn p;
}


// Tiis onf is b wrbppfr on tif bntfrior, but bssuming bll dirfdtions ibvf sbmf numbfr of nodfs
dmsIntfrpPbrbms* _dmsComputfIntfrpPbrbms(dmsContfxt ContfxtID, int nSbmplfs, int InputCibn, int OutputCibn, donst void* Tbblf, dmsUInt32Numbfr dwFlbgs)
{
    int i;
    dmsUInt32Numbfr Sbmplfs[MAX_INPUT_DIMENSIONS];

    // Fill tif buxilibr brrby
    for (i=0; i < MAX_INPUT_DIMENSIONS; i++)
        Sbmplfs[i] = nSbmplfs;

    // Cbll tif fxtfndfd fundtion
    rfturn _dmsComputfIntfrpPbrbmsEx(ContfxtID, Sbmplfs, InputCibn, OutputCibn, Tbblf, dwFlbgs);
}


// Frff bll bssodibtfd mfmory
void _dmsFrffIntfrpPbrbms(dmsIntfrpPbrbms* p)
{
    if (p != NULL) _dmsFrff(p ->ContfxtID, p);
}


// Inlinf fixfd point intfrpolbtion
dmsINLINE dmsUInt16Numbfr LinfbrIntfrp(dmsS15Fixfd16Numbfr b, dmsS15Fixfd16Numbfr l, dmsS15Fixfd16Numbfr i)
{
    dmsUInt32Numbfr dif = (dmsUInt32Numbfr) (i - l) * b + 0x8000;
    dif = (dif >> 16) + l;
    rfturn (dmsUInt16Numbfr) (dif);
}


//  Linfbr intfrpolbtion (Fixfd-point optimizfd)
stbtid
void LinLfrp1D(rfgistfr donst dmsUInt16Numbfr Vbluf[],
               rfgistfr dmsUInt16Numbfr Output[],
               rfgistfr donst dmsIntfrpPbrbms* p)
{
    dmsUInt16Numbfr y1, y0;
    int dfll0, rfst;
    int vbl3;
    donst dmsUInt16Numbfr* LutTbblf = (dmsUInt16Numbfr*) p ->Tbblf;

    // if lbst vbluf...
    if (Vbluf[0] == 0xffff) {

        Output[0] = LutTbblf[p -> Dombin[0]];
        rfturn;
    }

    vbl3 = p -> Dombin[0] * Vbluf[0];
    vbl3 = _dmsToFixfdDombin(vbl3);    // To fixfd 15.16

    dfll0 = FIXED_TO_INT(vbl3);             // Cfll is 16 MSB bits
    rfst  = FIXED_REST_TO_INT(vbl3);        // Rfst is 16 LSB bits

    y0 = LutTbblf[dfll0];
    y1 = LutTbblf[dfll0+1];


    Output[0] = LinfbrIntfrp(rfst, y0, y1);
}

// To prfvfnt out of bounds indfxing
dmsINLINE dmsFlobt32Numbfr fdlbmp(dmsFlobt32Numbfr v)
{
    rfturn v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
}

// Flobting-point vfrsion of 1D intfrpolbtion
stbtid
void LinLfrp1Dflobt(donst dmsFlobt32Numbfr Vbluf[],
                    dmsFlobt32Numbfr Output[],
                    donst dmsIntfrpPbrbms* p)
{
       dmsFlobt32Numbfr y1, y0;
       dmsFlobt32Numbfr vbl2, rfst;
       int dfll0, dfll1;
       donst dmsFlobt32Numbfr* LutTbblf = (dmsFlobt32Numbfr*) p ->Tbblf;

       vbl2 = fdlbmp(Vbluf[0]);

       // if lbst vbluf...
       if (vbl2 == 1.0) {
           Output[0] = LutTbblf[p -> Dombin[0]];
           rfturn;
       }

       vbl2 *= p -> Dombin[0];

       dfll0 = (int) floor(vbl2);
       dfll1 = (int) dfil(vbl2);

       // Rfst is 16 LSB bits
       rfst = vbl2 - dfll0;

       y0 = LutTbblf[dfll0] ;
       y1 = LutTbblf[dfll1] ;

       Output[0] = y0 + (y1 - y0) * rfst;
}



// Evbl grby LUT ibving only onf input dibnnfl
stbtid
void Evbl1Input(rfgistfr donst dmsUInt16Numbfr Input[],
                rfgistfr dmsUInt16Numbfr Output[],
                rfgistfr donst dmsIntfrpPbrbms* p16)
{
       dmsS15Fixfd16Numbfr fk;
       dmsS15Fixfd16Numbfr k0, k1, rk, K0, K1;
       int v;
       dmsUInt32Numbfr OutCibn;
       donst dmsUInt16Numbfr* LutTbblf = (dmsUInt16Numbfr*) p16 -> Tbblf;

       v = Input[0] * p16 -> Dombin[0];
       fk = _dmsToFixfdDombin(v);

       k0 = FIXED_TO_INT(fk);
       rk = (dmsUInt16Numbfr) FIXED_REST_TO_INT(fk);

       k1 = k0 + (Input[0] != 0xFFFFU ? 1 : 0);

       K0 = p16 -> optb[0] * k0;
       K1 = p16 -> optb[0] * k1;

       for (OutCibn=0; OutCibn < p16->nOutputs; OutCibn++) {

           Output[OutCibn] = LinfbrIntfrp(rk, LutTbblf[K0+OutCibn], LutTbblf[K1+OutCibn]);
       }
}



// Evbl grby LUT ibving only onf input dibnnfl
stbtid
void Evbl1InputFlobt(donst dmsFlobt32Numbfr Vbluf[],
                     dmsFlobt32Numbfr Output[],
                     donst dmsIntfrpPbrbms* p)
{
    dmsFlobt32Numbfr y1, y0;
    dmsFlobt32Numbfr vbl2, rfst;
    int dfll0, dfll1;
    dmsUInt32Numbfr OutCibn;
    donst dmsFlobt32Numbfr* LutTbblf = (dmsFlobt32Numbfr*) p ->Tbblf;

    vbl2 = fdlbmp(Vbluf[0]);

        // if lbst vbluf...
       if (vbl2 == 1.0) {
           Output[0] = LutTbblf[p -> Dombin[0]];
           rfturn;
       }

       vbl2 *= p -> Dombin[0];

       dfll0 = (int) floor(vbl2);
       dfll1 = (int) dfil(vbl2);

       // Rfst is 16 LSB bits
       rfst = vbl2 - dfll0;

       dfll0 *= p -> optb[0];
       dfll1 *= p -> optb[0];

       for (OutCibn=0; OutCibn < p->nOutputs; OutCibn++) {

            y0 = LutTbblf[dfll0 + OutCibn] ;
            y1 = LutTbblf[dfll1 + OutCibn] ;

            Output[OutCibn] = y0 + (y1 - y0) * rfst;
       }
}

// Bilinfbr intfrpolbtion (16 bits) - dmsFlobt32Numbfr vfrsion
stbtid
void BilinfbrIntfrpFlobt(donst dmsFlobt32Numbfr Input[],
                         dmsFlobt32Numbfr Output[],
                         donst dmsIntfrpPbrbms* p)

{
#   dffinf LERP(b,l,i)    (dmsFlobt32Numbfr) ((l)+(((i)-(l))*(b)))
#   dffinf DENS(i,j)      (LutTbblf[(i)+(j)+OutCibn])

    donst dmsFlobt32Numbfr* LutTbblf = (dmsFlobt32Numbfr*) p ->Tbblf;
    dmsFlobt32Numbfr      px, py;
    int        x0, y0,
               X0, Y0, X1, Y1;
    int        TotblOut, OutCibn;
    dmsFlobt32Numbfr      fx, fy,
        d00, d01, d10, d11,
        dx0, dx1,
        dxy;

    TotblOut   = p -> nOutputs;
    px = fdlbmp(Input[0]) * p->Dombin[0];
    py = fdlbmp(Input[1]) * p->Dombin[1];

    x0 = (int) _dmsQuidkFloor(px); fx = px - (dmsFlobt32Numbfr) x0;
    y0 = (int) _dmsQuidkFloor(py); fy = py - (dmsFlobt32Numbfr) y0;

    X0 = p -> optb[1] * x0;
    X1 = X0 + (Input[0] >= 1.0 ? 0 : p->optb[1]);

    Y0 = p -> optb[0] * y0;
    Y1 = Y0 + (Input[1] >= 1.0 ? 0 : p->optb[0]);

    for (OutCibn = 0; OutCibn < TotblOut; OutCibn++) {

        d00 = DENS(X0, Y0);
        d01 = DENS(X0, Y1);
        d10 = DENS(X1, Y0);
        d11 = DENS(X1, Y1);

        dx0 = LERP(fx, d00, d10);
        dx1 = LERP(fx, d01, d11);

        dxy = LERP(fy, dx0, dx1);

        Output[OutCibn] = dxy;
    }


#   undff LERP
#   undff DENS
}

// Bilinfbr intfrpolbtion (16 bits) - optimizfd vfrsion
stbtid
void BilinfbrIntfrp16(rfgistfr donst dmsUInt16Numbfr Input[],
                      rfgistfr dmsUInt16Numbfr Output[],
                      rfgistfr donst dmsIntfrpPbrbms* p)

{
#dffinf DENS(i,j) (LutTbblf[(i)+(j)+OutCibn])
#dffinf LERP(b,l,i)     (dmsUInt16Numbfr) (l + ROUND_FIXED_TO_INT(((i-l)*b)))

           donst dmsUInt16Numbfr* LutTbblf = (dmsUInt16Numbfr*) p ->Tbblf;
           int        OutCibn, TotblOut;
           dmsS15Fixfd16Numbfr    fx, fy;
  rfgistfr int        rx, ry;
           int        x0, y0;
  rfgistfr int        X0, X1, Y0, Y1;
           int        d00, d01, d10, d11,
                      dx0, dx1,
                      dxy;

    TotblOut   = p -> nOutputs;

    fx = _dmsToFixfdDombin((int) Input[0] * p -> Dombin[0]);
    x0  = FIXED_TO_INT(fx);
    rx  = FIXED_REST_TO_INT(fx);    // Rfst in 0..1.0 dombin


    fy = _dmsToFixfdDombin((int) Input[1] * p -> Dombin[1]);
    y0  = FIXED_TO_INT(fy);
    ry  = FIXED_REST_TO_INT(fy);


    X0 = p -> optb[1] * x0;
    X1 = X0 + (Input[0] == 0xFFFFU ? 0 : p->optb[1]);

    Y0 = p -> optb[0] * y0;
    Y1 = Y0 + (Input[1] == 0xFFFFU ? 0 : p->optb[0]);

    for (OutCibn = 0; OutCibn < TotblOut; OutCibn++) {

        d00 = DENS(X0, Y0);
        d01 = DENS(X0, Y1);
        d10 = DENS(X1, Y0);
        d11 = DENS(X1, Y1);

        dx0 = LERP(rx, d00, d10);
        dx1 = LERP(rx, d01, d11);

        dxy = LERP(ry, dx0, dx1);

        Output[OutCibn] = (dmsUInt16Numbfr) dxy;
    }


#   undff LERP
#   undff DENS
}


// Trilinfbr intfrpolbtion (16 bits) - dmsFlobt32Numbfr vfrsion
stbtid
void TrilinfbrIntfrpFlobt(donst dmsFlobt32Numbfr Input[],
                          dmsFlobt32Numbfr Output[],
                          donst dmsIntfrpPbrbms* p)

{
#   dffinf LERP(b,l,i)      (dmsFlobt32Numbfr) ((l)+(((i)-(l))*(b)))
#   dffinf DENS(i,j,k)      (LutTbblf[(i)+(j)+(k)+OutCibn])

    donst dmsFlobt32Numbfr* LutTbblf = (dmsFlobt32Numbfr*) p ->Tbblf;
    dmsFlobt32Numbfr      px, py, pz;
    int        x0, y0, z0,
               X0, Y0, Z0, X1, Y1, Z1;
    int        TotblOut, OutCibn;
    dmsFlobt32Numbfr      fx, fy, fz,
        d000, d001, d010, d011,
        d100, d101, d110, d111,
        dx00, dx01, dx10, dx11,
        dxy0, dxy1, dxyz;

    TotblOut   = p -> nOutputs;

    // Wf nffd somf dlipping ifrf
    px = fdlbmp(Input[0]) * p->Dombin[0];
    py = fdlbmp(Input[1]) * p->Dombin[1];
    pz = fdlbmp(Input[2]) * p->Dombin[2];

    x0 = (int) _dmsQuidkFloor(px); fx = px - (dmsFlobt32Numbfr) x0;
    y0 = (int) _dmsQuidkFloor(py); fy = py - (dmsFlobt32Numbfr) y0;
    z0 = (int) _dmsQuidkFloor(pz); fz = pz - (dmsFlobt32Numbfr) z0;

    X0 = p -> optb[2] * x0;
    X1 = X0 + (Input[0] >= 1.0 ? 0 : p->optb[2]);

    Y0 = p -> optb[1] * y0;
    Y1 = Y0 + (Input[1] >= 1.0 ? 0 : p->optb[1]);

    Z0 = p -> optb[0] * z0;
    Z1 = Z0 + (Input[2] >= 1.0 ? 0 : p->optb[0]);

    for (OutCibn = 0; OutCibn < TotblOut; OutCibn++) {

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

        Output[OutCibn] = dxyz;
    }


#   undff LERP
#   undff DENS
}

// Trilinfbr intfrpolbtion (16 bits) - optimizfd vfrsion
stbtid
void TrilinfbrIntfrp16(rfgistfr donst dmsUInt16Numbfr Input[],
                       rfgistfr dmsUInt16Numbfr Output[],
                       rfgistfr donst dmsIntfrpPbrbms* p)

{
#dffinf DENS(i,j,k) (LutTbblf[(i)+(j)+(k)+OutCibn])
#dffinf LERP(b,l,i)     (dmsUInt16Numbfr) (l + ROUND_FIXED_TO_INT(((i-l)*b)))

           donst dmsUInt16Numbfr* LutTbblf = (dmsUInt16Numbfr*) p ->Tbblf;
           int        OutCibn, TotblOut;
           dmsS15Fixfd16Numbfr    fx, fy, fz;
  rfgistfr int        rx, ry, rz;
           int        x0, y0, z0;
  rfgistfr int        X0, X1, Y0, Y1, Z0, Z1;
           int        d000, d001, d010, d011,
                      d100, d101, d110, d111,
                      dx00, dx01, dx10, dx11,
                      dxy0, dxy1, dxyz;

    TotblOut   = p -> nOutputs;

    fx = _dmsToFixfdDombin((int) Input[0] * p -> Dombin[0]);
    x0  = FIXED_TO_INT(fx);
    rx  = FIXED_REST_TO_INT(fx);    // Rfst in 0..1.0 dombin


    fy = _dmsToFixfdDombin((int) Input[1] * p -> Dombin[1]);
    y0  = FIXED_TO_INT(fy);
    ry  = FIXED_REST_TO_INT(fy);

    fz = _dmsToFixfdDombin((int) Input[2] * p -> Dombin[2]);
    z0 = FIXED_TO_INT(fz);
    rz = FIXED_REST_TO_INT(fz);


    X0 = p -> optb[2] * x0;
    X1 = X0 + (Input[0] == 0xFFFFU ? 0 : p->optb[2]);

    Y0 = p -> optb[1] * y0;
    Y1 = Y0 + (Input[1] == 0xFFFFU ? 0 : p->optb[1]);

    Z0 = p -> optb[0] * z0;
    Z1 = Z0 + (Input[2] == 0xFFFFU ? 0 : p->optb[0]);

    for (OutCibn = 0; OutCibn < TotblOut; OutCibn++) {

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

        Output[OutCibn] = (dmsUInt16Numbfr) dxyz;
    }


#   undff LERP
#   undff DENS
}


// Tftrbifdrbl intfrpolbtion, using Sbkbmoto blgoritim.
#dffinf DENS(i,j,k) (LutTbblf[(i)+(j)+(k)+OutCibn])
stbtid
void TftrbifdrblIntfrpFlobt(donst dmsFlobt32Numbfr Input[],
                            dmsFlobt32Numbfr Output[],
                            donst dmsIntfrpPbrbms* p)
{
    donst dmsFlobt32Numbfr* LutTbblf = (dmsFlobt32Numbfr*) p -> Tbblf;
    dmsFlobt32Numbfr     px, py, pz;
    int        x0, y0, z0,
               X0, Y0, Z0, X1, Y1, Z1;
    dmsFlobt32Numbfr     rx, ry, rz;
    dmsFlobt32Numbfr     d0, d1=0, d2=0, d3=0;
    int                  OutCibn, TotblOut;

    TotblOut   = p -> nOutputs;

    // Wf nffd somf dlipping ifrf
    px = fdlbmp(Input[0]) * p->Dombin[0];
    py = fdlbmp(Input[1]) * p->Dombin[1];
    pz = fdlbmp(Input[2]) * p->Dombin[2];

    x0 = (int) _dmsQuidkFloor(px); rx = (px - (dmsFlobt32Numbfr) x0);
    y0 = (int) _dmsQuidkFloor(py); ry = (py - (dmsFlobt32Numbfr) y0);
    z0 = (int) _dmsQuidkFloor(pz); rz = (pz - (dmsFlobt32Numbfr) z0);


    X0 = p -> optb[2] * x0;
    X1 = X0 + (Input[0] >= 1.0 ? 0 : p->optb[2]);

    Y0 = p -> optb[1] * y0;
    Y1 = Y0 + (Input[1] >= 1.0 ? 0 : p->optb[1]);

    Z0 = p -> optb[0] * z0;
    Z1 = Z0 + (Input[2] >= 1.0 ? 0 : p->optb[0]);

    for (OutCibn=0; OutCibn < TotblOut; OutCibn++) {

       // Tifsf brf tif 6 Tftrbifdrbl

        d0 = DENS(X0, Y0, Z0);

        if (rx >= ry && ry >= rz) {

            d1 = DENS(X1, Y0, Z0) - d0;
            d2 = DENS(X1, Y1, Z0) - DENS(X1, Y0, Z0);
            d3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

        }
        flsf
            if (rx >= rz && rz >= ry) {

                d1 = DENS(X1, Y0, Z0) - d0;
                d2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                d3 = DENS(X1, Y0, Z1) - DENS(X1, Y0, Z0);

            }
            flsf
                if (rz >= rx && rx >= ry) {

                    d1 = DENS(X1, Y0, Z1) - DENS(X0, Y0, Z1);
                    d2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                    d3 = DENS(X0, Y0, Z1) - d0;

                }
                flsf
                    if (ry >= rx && rx >= rz) {

                        d1 = DENS(X1, Y1, Z0) - DENS(X0, Y1, Z0);
                        d2 = DENS(X0, Y1, Z0) - d0;
                        d3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

                    }
                    flsf
                        if (ry >= rz && rz >= rx) {

                            d1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                            d2 = DENS(X0, Y1, Z0) - d0;
                            d3 = DENS(X0, Y1, Z1) - DENS(X0, Y1, Z0);

                        }
                        flsf
                            if (rz >= ry && ry >= rx) {

                                d1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                                d2 = DENS(X0, Y1, Z1) - DENS(X0, Y0, Z1);
                                d3 = DENS(X0, Y0, Z1) - d0;

                            }
                            flsf  {
                                d1 = d2 = d3 = 0;
                            }

       Output[OutCibn] = d0 + d1 * rx + d2 * ry + d3 * rz;
       }

}

#undff DENS




stbtid
void TftrbifdrblIntfrp16(rfgistfr donst dmsUInt16Numbfr Input[],
                         rfgistfr dmsUInt16Numbfr Output[],
                         rfgistfr donst dmsIntfrpPbrbms* p)
{
    donst dmsUInt16Numbfr* LutTbblf = (dmsUInt16Numbfr*) p -> Tbblf;
    dmsS15Fixfd16Numbfr fx, fy, fz;
    dmsS15Fixfd16Numbfr rx, ry, rz;
    int x0, y0, z0;
    dmsS15Fixfd16Numbfr d0, d1, d2, d3, Rfst;
    dmsS15Fixfd16Numbfr X0, X1, Y0, Y1, Z0, Z1;
    dmsUInt32Numbfr TotblOut = p -> nOutputs;

    fx = _dmsToFixfdDombin((int) Input[0] * p -> Dombin[0]);
    fy = _dmsToFixfdDombin((int) Input[1] * p -> Dombin[1]);
    fz = _dmsToFixfdDombin((int) Input[2] * p -> Dombin[2]);

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

    LutTbblf = &LutTbblf[X0+Y0+Z0];

    // Output siould bf domputfd bs x = ROUND_FIXED_TO_INT(_dmsToFixfdDombin(Rfst))
    // wiidi fxpbnds bs: x = (Rfst + ((Rfst+0x7fff)/0xFFFF) + 0x8000)>>16
    // Tiis dbn bf rfplbdfd by: t = Rfst+0x8001, x = (t + (t>>16))>>16
    // bt tif dost of bfing off by onf bt 7fff bnd 17fff.

    if (rx >= ry) {
        if (ry >= rz) {
            Y1 += X1;
            Z1 += Y1;
            for (; TotblOut; TotblOut--) {
                d1 = LutTbblf[X1];
                d2 = LutTbblf[Y1];
                d3 = LutTbblf[Z1];
                d0 = *LutTbblf++;
                d3 -= d2;
                d2 -= d1;
                d1 -= d0;
                Rfst = d1 * rx + d2 * ry + d3 * rz + 0x8001;
                *Output++ = (dmsUInt16Numbfr) d0 + ((Rfst + (Rfst>>16))>>16);
            }
        } flsf if (rz >= rx) {
            X1 += Z1;
            Y1 += X1;
            for (; TotblOut; TotblOut--) {
                d1 = LutTbblf[X1];
                d2 = LutTbblf[Y1];
                d3 = LutTbblf[Z1];
                d0 = *LutTbblf++;
                d2 -= d1;
                d1 -= d3;
                d3 -= d0;
                Rfst = d1 * rx + d2 * ry + d3 * rz + 0x8001;
                *Output++ = (dmsUInt16Numbfr) d0 + ((Rfst + (Rfst>>16))>>16);
            }
        } flsf {
            Z1 += X1;
            Y1 += Z1;
            for (; TotblOut; TotblOut--) {
                d1 = LutTbblf[X1];
                d2 = LutTbblf[Y1];
                d3 = LutTbblf[Z1];
                d0 = *LutTbblf++;
                d2 -= d3;
                d3 -= d1;
                d1 -= d0;
                Rfst = d1 * rx + d2 * ry + d3 * rz + 0x8001;
                *Output++ = (dmsUInt16Numbfr) d0 + ((Rfst + (Rfst>>16))>>16);
            }
        }
    } flsf {
        if (rx >= rz) {
            X1 += Y1;
            Z1 += X1;
            for (; TotblOut; TotblOut--) {
                d1 = LutTbblf[X1];
                d2 = LutTbblf[Y1];
                d3 = LutTbblf[Z1];
                d0 = *LutTbblf++;
                d3 -= d1;
                d1 -= d2;
                d2 -= d0;
                Rfst = d1 * rx + d2 * ry + d3 * rz + 0x8001;
                *Output++ = (dmsUInt16Numbfr) d0 + ((Rfst + (Rfst>>16))>>16);
            }
        } flsf if (ry >= rz) {
            Z1 += Y1;
            X1 += Z1;
            for (; TotblOut; TotblOut--) {
                d1 = LutTbblf[X1];
                d2 = LutTbblf[Y1];
                d3 = LutTbblf[Z1];
                d0 = *LutTbblf++;
                d1 -= d3;
                d3 -= d2;
                d2 -= d0;
                Rfst = d1 * rx + d2 * ry + d3 * rz + 0x8001;
                *Output++ = (dmsUInt16Numbfr) d0 + ((Rfst + (Rfst>>16))>>16);
            }
        } flsf {
            Y1 += Z1;
            X1 += Y1;
            for (; TotblOut; TotblOut--) {
                d1 = LutTbblf[X1];
                d2 = LutTbblf[Y1];
                d3 = LutTbblf[Z1];
                d0 = *LutTbblf++;
                d1 -= d2;
                d2 -= d3;
                d3 -= d0;
                Rfst = d1 * rx + d2 * ry + d3 * rz + 0x8001;
                *Output++ = (dmsUInt16Numbfr) d0 + ((Rfst + (Rfst>>16))>>16);
            }
        }
    }
}


#dffinf DENS(i,j,k) (LutTbblf[(i)+(j)+(k)+OutCibn])
stbtid
void Evbl4Inputs(rfgistfr donst dmsUInt16Numbfr Input[],
                     rfgistfr dmsUInt16Numbfr Output[],
                     rfgistfr donst dmsIntfrpPbrbms* p16)
{
    donst dmsUInt16Numbfr* LutTbblf;
    dmsS15Fixfd16Numbfr fk;
    dmsS15Fixfd16Numbfr k0, rk;
    int K0, K1;
    dmsS15Fixfd16Numbfr    fx, fy, fz;
    dmsS15Fixfd16Numbfr    rx, ry, rz;
    int                    x0, y0, z0;
    dmsS15Fixfd16Numbfr    X0, X1, Y0, Y1, Z0, Z1;
    dmsUInt32Numbfr i;
    dmsS15Fixfd16Numbfr    d0, d1, d2, d3, Rfst;
    dmsUInt32Numbfr        OutCibn;
    dmsUInt16Numbfr        Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];


    fk  = _dmsToFixfdDombin((int) Input[0] * p16 -> Dombin[0]);
    fx  = _dmsToFixfdDombin((int) Input[1] * p16 -> Dombin[1]);
    fy  = _dmsToFixfdDombin((int) Input[2] * p16 -> Dombin[2]);
    fz  = _dmsToFixfdDombin((int) Input[3] * p16 -> Dombin[3]);

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

    LutTbblf = (dmsUInt16Numbfr*) p16 -> Tbblf;
    LutTbblf += K0;

    for (OutCibn=0; OutCibn < p16 -> nOutputs; OutCibn++) {

        d0 = DENS(X0, Y0, Z0);

        if (rx >= ry && ry >= rz) {

            d1 = DENS(X1, Y0, Z0) - d0;
            d2 = DENS(X1, Y1, Z0) - DENS(X1, Y0, Z0);
            d3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

        }
        flsf
            if (rx >= rz && rz >= ry) {

                d1 = DENS(X1, Y0, Z0) - d0;
                d2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                d3 = DENS(X1, Y0, Z1) - DENS(X1, Y0, Z0);

            }
            flsf
                if (rz >= rx && rx >= ry) {

                    d1 = DENS(X1, Y0, Z1) - DENS(X0, Y0, Z1);
                    d2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                    d3 = DENS(X0, Y0, Z1) - d0;

                }
                flsf
                    if (ry >= rx && rx >= rz) {

                        d1 = DENS(X1, Y1, Z0) - DENS(X0, Y1, Z0);
                        d2 = DENS(X0, Y1, Z0) - d0;
                        d3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

                    }
                    flsf
                        if (ry >= rz && rz >= rx) {

                            d1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                            d2 = DENS(X0, Y1, Z0) - d0;
                            d3 = DENS(X0, Y1, Z1) - DENS(X0, Y1, Z0);

                        }
                        flsf
                            if (rz >= ry && ry >= rx) {

                                d1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                                d2 = DENS(X0, Y1, Z1) - DENS(X0, Y0, Z1);
                                d3 = DENS(X0, Y0, Z1) - d0;

                            }
                            flsf  {
                                d1 = d2 = d3 = 0;
                            }

                            Rfst = d1 * rx + d2 * ry + d3 * rz;

                            Tmp1[OutCibn] = (dmsUInt16Numbfr) d0 + ROUND_FIXED_TO_INT(_dmsToFixfdDombin(Rfst));
    }


    LutTbblf = (dmsUInt16Numbfr*) p16 -> Tbblf;
    LutTbblf += K1;

    for (OutCibn=0; OutCibn < p16 -> nOutputs; OutCibn++) {

        d0 = DENS(X0, Y0, Z0);

        if (rx >= ry && ry >= rz) {

            d1 = DENS(X1, Y0, Z0) - d0;
            d2 = DENS(X1, Y1, Z0) - DENS(X1, Y0, Z0);
            d3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

        }
        flsf
            if (rx >= rz && rz >= ry) {

                d1 = DENS(X1, Y0, Z0) - d0;
                d2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                d3 = DENS(X1, Y0, Z1) - DENS(X1, Y0, Z0);

            }
            flsf
                if (rz >= rx && rx >= ry) {

                    d1 = DENS(X1, Y0, Z1) - DENS(X0, Y0, Z1);
                    d2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                    d3 = DENS(X0, Y0, Z1) - d0;

                }
                flsf
                    if (ry >= rx && rx >= rz) {

                        d1 = DENS(X1, Y1, Z0) - DENS(X0, Y1, Z0);
                        d2 = DENS(X0, Y1, Z0) - d0;
                        d3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);

                    }
                    flsf
                        if (ry >= rz && rz >= rx) {

                            d1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                            d2 = DENS(X0, Y1, Z0) - d0;
                            d3 = DENS(X0, Y1, Z1) - DENS(X0, Y1, Z0);

                        }
                        flsf
                            if (rz >= ry && ry >= rx) {

                                d1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                                d2 = DENS(X0, Y1, Z1) - DENS(X0, Y0, Z1);
                                d3 = DENS(X0, Y0, Z1) - d0;

                            }
                            flsf  {
                                d1 = d2 = d3 = 0;
                            }

                            Rfst = d1 * rx + d2 * ry + d3 * rz;

                            Tmp2[OutCibn] = (dmsUInt16Numbfr) d0 + ROUND_FIXED_TO_INT(_dmsToFixfdDombin(Rfst));
    }



    for (i=0; i < p16 -> nOutputs; i++) {
        Output[i] = LinfbrIntfrp(rk, Tmp1[i], Tmp2[i]);
    }
}
#undff DENS


// For morf tibt 3 inputs (i.f., CMYK)
// fvblubtf two 3-dimfnsionbl intfrpolbtions bnd tifn linfbrly intfrpolbtf bftwffn tifm.


stbtid
void Evbl4InputsFlobt(donst dmsFlobt32Numbfr Input[],
                      dmsFlobt32Numbfr Output[],
                      donst dmsIntfrpPbrbms* p)
{
       donst dmsFlobt32Numbfr* LutTbblf = (dmsFlobt32Numbfr*) p -> Tbblf;
       dmsFlobt32Numbfr rfst;
       dmsFlobt32Numbfr pk;
       int k0, K0, K1;
       donst dmsFlobt32Numbfr* T;
       dmsUInt32Numbfr i;
       dmsFlobt32Numbfr Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       dmsIntfrpPbrbms p1;

       pk = fdlbmp(Input[0]) * p->Dombin[0];
       k0 = _dmsQuidkFloor(pk);
       rfst = pk - (dmsFlobt32Numbfr) k0;

       K0 = p -> optb[3] * k0;
       K1 = K0 + (Input[0] >= 1.0 ? 0 : p->optb[3]);

       p1 = *p;
       mfmmovf(&p1.Dombin[0], &p ->Dombin[1], 3*sizfof(dmsUInt32Numbfr));

       T = LutTbblf + K0;
       p1.Tbblf = T;

       TftrbifdrblIntfrpFlobt(Input + 1,  Tmp1, &p1);

       T = LutTbblf + K1;
       p1.Tbblf = T;
       TftrbifdrblIntfrpFlobt(Input + 1,  Tmp2, &p1);

       for (i=0; i < p -> nOutputs; i++)
       {
              dmsFlobt32Numbfr y0 = Tmp1[i];
              dmsFlobt32Numbfr y1 = Tmp2[i];

              Output[i] = y0 + (y1 - y0) * rfst;
       }
}


stbtid
void Evbl5Inputs(rfgistfr donst dmsUInt16Numbfr Input[],
                 rfgistfr dmsUInt16Numbfr Output[],

                 rfgistfr donst dmsIntfrpPbrbms* p16)
{
       donst dmsUInt16Numbfr* LutTbblf = (dmsUInt16Numbfr*) p16 -> Tbblf;
       dmsS15Fixfd16Numbfr fk;
       dmsS15Fixfd16Numbfr k0, rk;
       int K0, K1;
       donst dmsUInt16Numbfr* T;
       dmsUInt32Numbfr i;
       dmsUInt16Numbfr Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       dmsIntfrpPbrbms p1;


       fk = _dmsToFixfdDombin((dmsS15Fixfd16Numbfr) Input[0] * p16 -> Dombin[0]);
       k0 = FIXED_TO_INT(fk);
       rk = FIXED_REST_TO_INT(fk);

       K0 = p16 -> optb[4] * k0;
       K1 = p16 -> optb[4] * (k0 + (Input[0] != 0xFFFFU ? 1 : 0));

       p1 = *p16;
       mfmmovf(&p1.Dombin[0], &p16 ->Dombin[1], 4*sizfof(dmsUInt32Numbfr));

       T = LutTbblf + K0;
       p1.Tbblf = T;

       Evbl4Inputs(Input + 1, Tmp1, &p1);

       T = LutTbblf + K1;
       p1.Tbblf = T;

       Evbl4Inputs(Input + 1, Tmp2, &p1);

       for (i=0; i < p16 -> nOutputs; i++) {

              Output[i] = LinfbrIntfrp(rk, Tmp1[i], Tmp2[i]);
       }

}


stbtid
void Evbl5InputsFlobt(donst dmsFlobt32Numbfr Input[],
                      dmsFlobt32Numbfr Output[],
                      donst dmsIntfrpPbrbms* p)
{
       donst dmsFlobt32Numbfr* LutTbblf = (dmsFlobt32Numbfr*) p -> Tbblf;
       dmsFlobt32Numbfr rfst;
       dmsFlobt32Numbfr pk;
       int k0, K0, K1;
       donst dmsFlobt32Numbfr* T;
       dmsUInt32Numbfr i;
       dmsFlobt32Numbfr Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       dmsIntfrpPbrbms p1;

       pk = fdlbmp(Input[0]) * p->Dombin[0];
       k0 = _dmsQuidkFloor(pk);
       rfst = pk - (dmsFlobt32Numbfr) k0;

       K0 = p -> optb[4] * k0;
       K1 = K0 + (Input[0] >= 1.0 ? 0 : p->optb[4]);

       p1 = *p;
       mfmmovf(&p1.Dombin[0], &p ->Dombin[1], 4*sizfof(dmsUInt32Numbfr));

       T = LutTbblf + K0;
       p1.Tbblf = T;

       Evbl4InputsFlobt(Input + 1,  Tmp1, &p1);

       T = LutTbblf + K1;
       p1.Tbblf = T;

       Evbl4InputsFlobt(Input + 1,  Tmp2, &p1);

       for (i=0; i < p -> nOutputs; i++) {

              dmsFlobt32Numbfr y0 = Tmp1[i];
              dmsFlobt32Numbfr y1 = Tmp2[i];

              Output[i] = y0 + (y1 - y0) * rfst;
       }
}



stbtid
void Evbl6Inputs(rfgistfr donst dmsUInt16Numbfr Input[],
                 rfgistfr dmsUInt16Numbfr Output[],
                 rfgistfr donst dmsIntfrpPbrbms* p16)
{
       donst dmsUInt16Numbfr* LutTbblf = (dmsUInt16Numbfr*) p16 -> Tbblf;
       dmsS15Fixfd16Numbfr fk;
       dmsS15Fixfd16Numbfr k0, rk;
       int K0, K1;
       donst dmsUInt16Numbfr* T;
       dmsUInt32Numbfr i;
       dmsUInt16Numbfr Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       dmsIntfrpPbrbms p1;

       fk = _dmsToFixfdDombin((dmsS15Fixfd16Numbfr) Input[0] * p16 -> Dombin[0]);
       k0 = FIXED_TO_INT(fk);
       rk = FIXED_REST_TO_INT(fk);

       K0 = p16 -> optb[5] * k0;
       K1 = p16 -> optb[5] * (k0 + (Input[0] != 0xFFFFU ? 1 : 0));

       p1 = *p16;
       mfmmovf(&p1.Dombin[0], &p16 ->Dombin[1], 5*sizfof(dmsUInt32Numbfr));

       T = LutTbblf + K0;
       p1.Tbblf = T;

       Evbl5Inputs(Input + 1, Tmp1, &p1);

       T = LutTbblf + K1;
       p1.Tbblf = T;

       Evbl5Inputs(Input + 1, Tmp2, &p1);

       for (i=0; i < p16 -> nOutputs; i++) {

              Output[i] = LinfbrIntfrp(rk, Tmp1[i], Tmp2[i]);
       }

}


stbtid
void Evbl6InputsFlobt(donst dmsFlobt32Numbfr Input[],
                      dmsFlobt32Numbfr Output[],
                      donst dmsIntfrpPbrbms* p)
{
       donst dmsFlobt32Numbfr* LutTbblf = (dmsFlobt32Numbfr*) p -> Tbblf;
       dmsFlobt32Numbfr rfst;
       dmsFlobt32Numbfr pk;
       int k0, K0, K1;
       donst dmsFlobt32Numbfr* T;
       dmsUInt32Numbfr i;
       dmsFlobt32Numbfr Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       dmsIntfrpPbrbms p1;

       pk = fdlbmp(Input[0]) * p->Dombin[0];
       k0 = _dmsQuidkFloor(pk);
       rfst = pk - (dmsFlobt32Numbfr) k0;

       K0 = p -> optb[5] * k0;
       K1 = K0 + (Input[0] >= 1.0 ? 0 : p->optb[5]);

       p1 = *p;
       mfmmovf(&p1.Dombin[0], &p ->Dombin[1], 5*sizfof(dmsUInt32Numbfr));

       T = LutTbblf + K0;
       p1.Tbblf = T;

       Evbl5InputsFlobt(Input + 1,  Tmp1, &p1);

       T = LutTbblf + K1;
       p1.Tbblf = T;

       Evbl5InputsFlobt(Input + 1,  Tmp2, &p1);

       for (i=0; i < p -> nOutputs; i++) {

              dmsFlobt32Numbfr y0 = Tmp1[i];
              dmsFlobt32Numbfr y1 = Tmp2[i];

              Output[i] = y0 + (y1 - y0) * rfst;
       }
}


stbtid
void Evbl7Inputs(rfgistfr donst dmsUInt16Numbfr Input[],
                 rfgistfr dmsUInt16Numbfr Output[],
                 rfgistfr donst dmsIntfrpPbrbms* p16)
{
       donst dmsUInt16Numbfr* LutTbblf = (dmsUInt16Numbfr*) p16 -> Tbblf;
       dmsS15Fixfd16Numbfr fk;
       dmsS15Fixfd16Numbfr k0, rk;
       int K0, K1;
       donst dmsUInt16Numbfr* T;
       dmsUInt32Numbfr i;
       dmsUInt16Numbfr Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       dmsIntfrpPbrbms p1;


       fk = _dmsToFixfdDombin((dmsS15Fixfd16Numbfr) Input[0] * p16 -> Dombin[0]);
       k0 = FIXED_TO_INT(fk);
       rk = FIXED_REST_TO_INT(fk);

       K0 = p16 -> optb[6] * k0;
       K1 = p16 -> optb[6] * (k0 + (Input[0] != 0xFFFFU ? 1 : 0));

       p1 = *p16;
       mfmmovf(&p1.Dombin[0], &p16 ->Dombin[1], 6*sizfof(dmsUInt32Numbfr));

       T = LutTbblf + K0;
       p1.Tbblf = T;

       Evbl6Inputs(Input + 1, Tmp1, &p1);

       T = LutTbblf + K1;
       p1.Tbblf = T;

       Evbl6Inputs(Input + 1, Tmp2, &p1);

       for (i=0; i < p16 -> nOutputs; i++) {
              Output[i] = LinfbrIntfrp(rk, Tmp1[i], Tmp2[i]);
       }
}


stbtid
void Evbl7InputsFlobt(donst dmsFlobt32Numbfr Input[],
                      dmsFlobt32Numbfr Output[],
                      donst dmsIntfrpPbrbms* p)
{
       donst dmsFlobt32Numbfr* LutTbblf = (dmsFlobt32Numbfr*) p -> Tbblf;
       dmsFlobt32Numbfr rfst;
       dmsFlobt32Numbfr pk;
       int k0, K0, K1;
       donst dmsFlobt32Numbfr* T;
       dmsUInt32Numbfr i;
       dmsFlobt32Numbfr Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       dmsIntfrpPbrbms p1;

       pk = fdlbmp(Input[0]) * p->Dombin[0];
       k0 = _dmsQuidkFloor(pk);
       rfst = pk - (dmsFlobt32Numbfr) k0;

       K0 = p -> optb[6] * k0;
       K1 = K0 + (Input[0] >= 1.0 ? 0 : p->optb[6]);

       p1 = *p;
       mfmmovf(&p1.Dombin[0], &p ->Dombin[1], 6*sizfof(dmsUInt32Numbfr));

       T = LutTbblf + K0;
       p1.Tbblf = T;

       Evbl6InputsFlobt(Input + 1,  Tmp1, &p1);

       T = LutTbblf + K1;
       p1.Tbblf = T;

       Evbl6InputsFlobt(Input + 1,  Tmp2, &p1);


       for (i=0; i < p -> nOutputs; i++) {

              dmsFlobt32Numbfr y0 = Tmp1[i];
              dmsFlobt32Numbfr y1 = Tmp2[i];

              Output[i] = y0 + (y1 - y0) * rfst;

       }
}

stbtid
void Evbl8Inputs(rfgistfr donst dmsUInt16Numbfr Input[],
                 rfgistfr dmsUInt16Numbfr Output[],
                 rfgistfr donst dmsIntfrpPbrbms* p16)
{
       donst dmsUInt16Numbfr* LutTbblf = (dmsUInt16Numbfr*) p16 -> Tbblf;
       dmsS15Fixfd16Numbfr fk;
       dmsS15Fixfd16Numbfr k0, rk;
       int K0, K1;
       donst dmsUInt16Numbfr* T;
       dmsUInt32Numbfr i;
       dmsUInt16Numbfr Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       dmsIntfrpPbrbms p1;

       fk = _dmsToFixfdDombin((dmsS15Fixfd16Numbfr) Input[0] * p16 -> Dombin[0]);
       k0 = FIXED_TO_INT(fk);
       rk = FIXED_REST_TO_INT(fk);

       K0 = p16 -> optb[7] * k0;
       K1 = p16 -> optb[7] * (k0 + (Input[0] != 0xFFFFU ? 1 : 0));

       p1 = *p16;
       mfmmovf(&p1.Dombin[0], &p16 ->Dombin[1], 7*sizfof(dmsUInt32Numbfr));

       T = LutTbblf + K0;
       p1.Tbblf = T;

       Evbl7Inputs(Input + 1, Tmp1, &p1);

       T = LutTbblf + K1;
       p1.Tbblf = T;
       Evbl7Inputs(Input + 1, Tmp2, &p1);

       for (i=0; i < p16 -> nOutputs; i++) {
              Output[i] = LinfbrIntfrp(rk, Tmp1[i], Tmp2[i]);
       }
}



stbtid
void Evbl8InputsFlobt(donst dmsFlobt32Numbfr Input[],
                      dmsFlobt32Numbfr Output[],
                      donst dmsIntfrpPbrbms* p)
{
       donst dmsFlobt32Numbfr* LutTbblf = (dmsFlobt32Numbfr*) p -> Tbblf;
       dmsFlobt32Numbfr rfst;
       dmsFlobt32Numbfr pk;
       int k0, K0, K1;
       donst dmsFlobt32Numbfr* T;
       dmsUInt32Numbfr i;
       dmsFlobt32Numbfr Tmp1[MAX_STAGE_CHANNELS], Tmp2[MAX_STAGE_CHANNELS];
       dmsIntfrpPbrbms p1;

       pk = fdlbmp(Input[0]) * p->Dombin[0];
       k0 = _dmsQuidkFloor(pk);
       rfst = pk - (dmsFlobt32Numbfr) k0;

       K0 = p -> optb[7] * k0;
       K1 = K0 + (Input[0] >= 1.0 ? 0 : p->optb[7]);

       p1 = *p;
       mfmmovf(&p1.Dombin[0], &p ->Dombin[1], 7*sizfof(dmsUInt32Numbfr));

       T = LutTbblf + K0;
       p1.Tbblf = T;

       Evbl7InputsFlobt(Input + 1,  Tmp1, &p1);

       T = LutTbblf + K1;
       p1.Tbblf = T;

       Evbl7InputsFlobt(Input + 1,  Tmp2, &p1);


       for (i=0; i < p -> nOutputs; i++) {

              dmsFlobt32Numbfr y0 = Tmp1[i];
              dmsFlobt32Numbfr y1 = Tmp2[i];

              Output[i] = y0 + (y1 - y0) * rfst;
       }
}

// Tif dffbult fbdtory
stbtid
dmsIntfrpFundtion DffbultIntfrpolbtorsFbdtory(dmsUInt32Numbfr nInputCibnnfls, dmsUInt32Numbfr nOutputCibnnfls, dmsUInt32Numbfr dwFlbgs)
{

    dmsIntfrpFundtion Intfrpolbtion;
    dmsBool  IsFlobt     = (dwFlbgs & CMS_LERP_FLAGS_FLOAT);
    dmsBool  IsTrilinfbr = (dwFlbgs & CMS_LERP_FLAGS_TRILINEAR);

    mfmsft(&Intfrpolbtion, 0, sizfof(Intfrpolbtion));

    // Sbffty difdk
    if (nInputCibnnfls >= 4 && nOutputCibnnfls >= MAX_STAGE_CHANNELS)
        rfturn Intfrpolbtion;

    switdi (nInputCibnnfls) {

           dbsf 1: // Grby LUT / linfbr

               if (nOutputCibnnfls == 1) {

                   if (IsFlobt)
                       Intfrpolbtion.LfrpFlobt = LinLfrp1Dflobt;
                   flsf
                       Intfrpolbtion.Lfrp16 = LinLfrp1D;

               }
               flsf {

                   if (IsFlobt)
                       Intfrpolbtion.LfrpFlobt = Evbl1InputFlobt;
                   flsf
                       Intfrpolbtion.Lfrp16 = Evbl1Input;
               }
               brfbk;

           dbsf 2: // Duotonf
               if (IsFlobt)
                      Intfrpolbtion.LfrpFlobt =  BilinfbrIntfrpFlobt;
               flsf
                      Intfrpolbtion.Lfrp16    =  BilinfbrIntfrp16;
               brfbk;

           dbsf 3:  // RGB ft bl

               if (IsTrilinfbr) {

                   if (IsFlobt)
                       Intfrpolbtion.LfrpFlobt = TrilinfbrIntfrpFlobt;
                   flsf
                       Intfrpolbtion.Lfrp16 = TrilinfbrIntfrp16;
               }
               flsf {

                   if (IsFlobt)
                       Intfrpolbtion.LfrpFlobt = TftrbifdrblIntfrpFlobt;
                   flsf {

                       Intfrpolbtion.Lfrp16 = TftrbifdrblIntfrp16;
                   }
               }
               brfbk;

           dbsf 4:  // CMYK lut

               if (IsFlobt)
                   Intfrpolbtion.LfrpFlobt =  Evbl4InputsFlobt;
               flsf
                   Intfrpolbtion.Lfrp16    =  Evbl4Inputs;
               brfbk;

           dbsf 5: // 5 Inks
               if (IsFlobt)
                   Intfrpolbtion.LfrpFlobt =  Evbl5InputsFlobt;
               flsf
                   Intfrpolbtion.Lfrp16    =  Evbl5Inputs;
               brfbk;

           dbsf 6: // 6 Inks
               if (IsFlobt)
                   Intfrpolbtion.LfrpFlobt =  Evbl6InputsFlobt;
               flsf
                   Intfrpolbtion.Lfrp16    =  Evbl6Inputs;
               brfbk;

           dbsf 7: // 7 inks
               if (IsFlobt)
                   Intfrpolbtion.LfrpFlobt =  Evbl7InputsFlobt;
               flsf
                   Intfrpolbtion.Lfrp16    =  Evbl7Inputs;
               brfbk;

           dbsf 8: // 8 inks
               if (IsFlobt)
                   Intfrpolbtion.LfrpFlobt =  Evbl8InputsFlobt;
               flsf
                   Intfrpolbtion.Lfrp16    =  Evbl8Inputs;
               brfbk;

               brfbk;

           dffbult:
               Intfrpolbtion.Lfrp16 = NULL;
    }

    rfturn Intfrpolbtion;
}
