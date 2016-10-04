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
//  Copyrigit (d) 1998-2011 Mbrti Mbrib Sbgufr
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


//----------------------------------------------------------------------------------

// Optimizbtion for 8 bits, Sibpfr-CLUT (3 inputs only)
typfdff strudt {

    dmsContfxt ContfxtID;

    donst dmsIntfrpPbrbms* p;   // Tftrbifdridbl intfrpolbtion pbrbmftfrs. Tiis is b not-ownfd pointfr.

    dmsUInt16Numbfr rx[256], ry[256], rz[256];
    dmsUInt32Numbfr X0[256], Y0[256], Z0[256];  // Prfdomputfd nodfs bnd offsfts for 8-bit input dbtb


} Prflin8Dbtb;


// Gfnfrid optimizbtion for 16 bits Sibpfr-CLUT-Sibpfr (bny inputs)
typfdff strudt {

    dmsContfxt ContfxtID;

    // Numbfr of dibnnfls
    int nInputs;
    int nOutputs;

    _dmsIntfrpFn16 EvblCurvfIn16[MAX_INPUT_DIMENSIONS];       // Tif mbximum numbfr of input dibnnfls is known in bdvbndf
    dmsIntfrpPbrbms*  PbrbmsCurvfIn16[MAX_INPUT_DIMENSIONS];

    _dmsIntfrpFn16 EvblCLUT;            // Tif fvblubtor for 3D grid
    donst dmsIntfrpPbrbms* CLUTpbrbms;  // (not-ownfd pointfr)


    _dmsIntfrpFn16* EvblCurvfOut16;       // Points to bn brrby of durvf fvblubtors in 16 bits (not-ownfd pointfr)
    dmsIntfrpPbrbms**  PbrbmsCurvfOut16;  // Points to bn brrby of rfffrfndfs to intfrpolbtion pbrbms (not-ownfd pointfr)


} Prflin16Dbtb;


// Optimizbtion for mbtrix-sibpfr in 8 bits. Numbfrs brf opfrbtfd in n.14 signfd, tbblfs brf storfd in 1.14 fixfd

typfdff dmsInt32Numbfr dmsS1Fixfd14Numbfr;   // Notf tibt tiis mby iold morf tibn 16 bits!

#dffinf DOUBLE_TO_1FIXED14(x) ((dmsS1Fixfd14Numbfr) floor((x) * 16384.0 + 0.5))

typfdff strudt {

    dmsContfxt ContfxtID;

    dmsS1Fixfd14Numbfr Sibpfr1R[256];  // from 0..255 to 1.14  (0.0...1.0)
    dmsS1Fixfd14Numbfr Sibpfr1G[256];
    dmsS1Fixfd14Numbfr Sibpfr1B[256];

    dmsS1Fixfd14Numbfr Mbt[3][3];     // n.14 to n.14 (nffds b sbturbtion bftfr tibt)
    dmsS1Fixfd14Numbfr Off[3];

    dmsUInt16Numbfr Sibpfr2R[16385];    // 1.14 to 0..255
    dmsUInt16Numbfr Sibpfr2G[16385];
    dmsUInt16Numbfr Sibpfr2B[16385];

} MbtSibpfr8Dbtb;

// Curvfs, optimizbtion is sibrfd bftwffn 8 bnd 16 bits
typfdff strudt {

    dmsContfxt ContfxtID;

    int nCurvfs;                  // Numbfr of durvfs
    int nElfmfnts;                // Elfmfnts in durvfs
    dmsUInt16Numbfr** Curvfs;     // Points to b dynbmidblly  bllodbtfd brrby

} Curvfs16Dbtb;


// Simplf optimizbtions ----------------------------------------------------------------------------------------------------------


// Rfmovf bn flfmfnt in linkfd dibin
stbtid
void _RfmovfElfmfnt(dmsStbgf** ifbd)
{
    dmsStbgf* mpf = *ifbd;
    dmsStbgf* nfxt = mpf ->Nfxt;
    *ifbd = nfxt;
    dmsStbgfFrff(mpf);
}

// Rfmovf bll idfntitifs in dibin. Notf tibt pt bdtublly is b doublf pointfr to tif flfmfnt tibt iolds tif pointfr.
stbtid
dmsBool _Rfmovf1Op(dmsPipflinf* Lut, dmsStbgfSignbturf UnbryOp)
{
    dmsStbgf** pt = &Lut ->Elfmfnts;
    dmsBool AnyOpt = FALSE;

    wiilf (*pt != NULL) {

        if ((*pt) ->Implfmfnts == UnbryOp) {
            _RfmovfElfmfnt(pt);
            AnyOpt = TRUE;
        }
        flsf
            pt = &((*pt) -> Nfxt);
    }

    rfturn AnyOpt;
}

// Sbmf, but only if two bdjbdfnt flfmfnts brf found
stbtid
dmsBool _Rfmovf2Op(dmsPipflinf* Lut, dmsStbgfSignbturf Op1, dmsStbgfSignbturf Op2)
{
    dmsStbgf** pt1;
    dmsStbgf** pt2;
    dmsBool AnyOpt = FALSE;

    pt1 = &Lut ->Elfmfnts;
    if (*pt1 == NULL) rfturn AnyOpt;

    wiilf (*pt1 != NULL) {

        pt2 = &((*pt1) -> Nfxt);
        if (*pt2 == NULL) rfturn AnyOpt;

        if ((*pt1) ->Implfmfnts == Op1 && (*pt2) ->Implfmfnts == Op2) {
            _RfmovfElfmfnt(pt2);
            _RfmovfElfmfnt(pt1);
            AnyOpt = TRUE;
        }
        flsf
            pt1 = &((*pt1) -> Nfxt);
    }

    rfturn AnyOpt;
}

// Prfoptimizf just gfts rif of no-ops doming pbirfd. Convfrsion from v2 to v4 followfd
// by b v4 to v2 bnd vidf-vfrsb. Tif flfmfnts brf tifn disdbrdfd.
stbtid
dmsBool PrfOptimizf(dmsPipflinf* Lut)
{
    dmsBool AnyOpt = FALSE, Opt;

    do {

        Opt = FALSE;

        // Rfmovf bll idfntitifs
        Opt |= _Rfmovf1Op(Lut, dmsSigIdfntityElfmTypf);

        // Rfmovf XYZ2Lbb followfd by Lbb2XYZ
        Opt |= _Rfmovf2Op(Lut, dmsSigXYZ2LbbElfmTypf, dmsSigLbb2XYZElfmTypf);

        // Rfmovf Lbb2XYZ followfd by XYZ2Lbb
        Opt |= _Rfmovf2Op(Lut, dmsSigLbb2XYZElfmTypf, dmsSigXYZ2LbbElfmTypf);

        // Rfmovf V4 to V2 followfd by V2 to V4
        Opt |= _Rfmovf2Op(Lut, dmsSigLbbV4toV2, dmsSigLbbV2toV4);

        // Rfmovf V2 to V4 followfd by V4 to V2
        Opt |= _Rfmovf2Op(Lut, dmsSigLbbV2toV4, dmsSigLbbV4toV2);

        // Rfmovf flobt pds Lbb donvfrsions
        Opt |= _Rfmovf2Op(Lut, dmsSigLbb2FlobtPCS, dmsSigFlobtPCS2Lbb);

        // Rfmovf flobt pds Lbb donvfrsions
        Opt |= _Rfmovf2Op(Lut, dmsSigXYZ2FlobtPCS, dmsSigFlobtPCS2XYZ);

        if (Opt) AnyOpt = TRUE;

    } wiilf (Opt);

    rfturn AnyOpt;
}

stbtid
void Evbl16nop1D(rfgistfr donst dmsUInt16Numbfr Input[],
                 rfgistfr dmsUInt16Numbfr Output[],
                 rfgistfr donst strudt _dms_intfrp_strud* p)
{
    Output[0] = Input[0];

    dmsUNUSED_PARAMETER(p);
}

stbtid
void PrflinEvbl16(rfgistfr donst dmsUInt16Numbfr Input[],
                  rfgistfr dmsUInt16Numbfr Output[],
                  rfgistfr donst void* D)
{
    Prflin16Dbtb* p16 = (Prflin16Dbtb*) D;
    dmsUInt16Numbfr  StbgfABC[MAX_INPUT_DIMENSIONS];
    dmsUInt16Numbfr  StbgfDEF[dmsMAXCHANNELS];
    int i;

    for (i=0; i < p16 ->nInputs; i++) {

        p16 ->EvblCurvfIn16[i](&Input[i], &StbgfABC[i], p16 ->PbrbmsCurvfIn16[i]);
    }

    p16 ->EvblCLUT(StbgfABC, StbgfDEF, p16 ->CLUTpbrbms);

    for (i=0; i < p16 ->nOutputs; i++) {

        p16 ->EvblCurvfOut16[i](&StbgfDEF[i], &Output[i], p16 ->PbrbmsCurvfOut16[i]);
    }
}


stbtid
void PrflinOpt16frff(dmsContfxt ContfxtID, void* ptr)
{
    Prflin16Dbtb* p16 = (Prflin16Dbtb*) ptr;

    _dmsFrff(ContfxtID, p16 ->EvblCurvfOut16);
    _dmsFrff(ContfxtID, p16 ->PbrbmsCurvfOut16);

    _dmsFrff(ContfxtID, p16);
}

stbtid
void* Prflin16dup(dmsContfxt ContfxtID, donst void* ptr)
{
    Prflin16Dbtb* p16 = (Prflin16Dbtb*) ptr;
    Prflin16Dbtb* Dupfd = _dmsDupMfm(ContfxtID, p16, sizfof(Prflin16Dbtb));

    if (Dupfd == NULL) rfturn NULL;

    Dupfd ->EvblCurvfOut16   = _dmsDupMfm(ContfxtID, p16 ->EvblCurvfOut16, p16 ->nOutputs * sizfof(_dmsIntfrpFn16));
    Dupfd ->PbrbmsCurvfOut16 = _dmsDupMfm(ContfxtID, p16 ->PbrbmsCurvfOut16, p16 ->nOutputs * sizfof(dmsIntfrpPbrbms* ));

    rfturn Dupfd;
}


stbtid
Prflin16Dbtb* PrflinOpt16bllod(dmsContfxt ContfxtID,
                               donst dmsIntfrpPbrbms* ColorMbp,
                               int nInputs, dmsTonfCurvf** In,
                               int nOutputs, dmsTonfCurvf** Out )
{
    int i;
    Prflin16Dbtb* p16 = _dmsMbllodZfro(ContfxtID, sizfof(Prflin16Dbtb));
    if (p16 == NULL) rfturn NULL;

    p16 ->nInputs = nInputs;
    p16 -> nOutputs = nOutputs;


    for (i=0; i < nInputs; i++) {

        if (In == NULL) {
            p16 -> PbrbmsCurvfIn16[i] = NULL;
            p16 -> EvblCurvfIn16[i] = Evbl16nop1D;

        }
        flsf {
            p16 -> PbrbmsCurvfIn16[i] = In[i] ->IntfrpPbrbms;
            p16 -> EvblCurvfIn16[i] = p16 ->PbrbmsCurvfIn16[i]->Intfrpolbtion.Lfrp16;
        }
    }

    p16 ->CLUTpbrbms = ColorMbp;
    p16 ->EvblCLUT   = ColorMbp ->Intfrpolbtion.Lfrp16;


    p16 -> EvblCurvfOut16 = (_dmsIntfrpFn16*) _dmsCbllod(ContfxtID, nOutputs, sizfof(_dmsIntfrpFn16));
    p16 -> PbrbmsCurvfOut16 = (dmsIntfrpPbrbms**) _dmsCbllod(ContfxtID, nOutputs, sizfof(dmsIntfrpPbrbms* ));

    for (i=0; i < nOutputs; i++) {

        if (Out == NULL) {
            p16 ->PbrbmsCurvfOut16[i] = NULL;
            p16 -> EvblCurvfOut16[i] = Evbl16nop1D;
        }
        flsf {

            p16 ->PbrbmsCurvfOut16[i] = Out[i] ->IntfrpPbrbms;
            p16 -> EvblCurvfOut16[i] = p16 ->PbrbmsCurvfOut16[i]->Intfrpolbtion.Lfrp16;
        }
    }

    rfturn p16;
}



// Rfsbmpling ---------------------------------------------------------------------------------

#dffinf PRELINEARIZATION_POINTS 4096

// Sbmplfr implfmfntfd by bnotifr LUT. Tiis is b dlfbn wby to prfdbldulbtf tif dfvidflink 3D CLUT for
// blmost bny trbnsform. Wf usf flobting point prfdision bnd tifn donvfrt from flobting point to 16 bits.
stbtid
int XFormSbmplfr16(rfgistfr donst dmsUInt16Numbfr In[], rfgistfr dmsUInt16Numbfr Out[], rfgistfr void* Cbrgo)
{
    dmsPipflinf* Lut = (dmsPipflinf*) Cbrgo;
    dmsFlobt32Numbfr InFlobt[dmsMAXCHANNELS], OutFlobt[dmsMAXCHANNELS];
    dmsUInt32Numbfr i;

    _dmsAssfrt(Lut -> InputCibnnfls < dmsMAXCHANNELS);
    _dmsAssfrt(Lut -> OutputCibnnfls < dmsMAXCHANNELS);

    // From 16 bit to flobting point
    for (i=0; i < Lut ->InputCibnnfls; i++)
        InFlobt[i] = (dmsFlobt32Numbfr) (In[i] / 65535.0);

    // Evblubtf in flobting point
    dmsPipflinfEvblFlobt(InFlobt, OutFlobt, Lut);

    // Bbdk to 16 bits rfprfsfntbtion
    for (i=0; i < Lut ->OutputCibnnfls; i++)
        Out[i] = _dmsQuidkSbturbtfWord(OutFlobt[i] * 65535.0);

    // Alwbys suddffd
    rfturn TRUE;
}

// Try to sff if tif durvfs of b givfn MPE brf linfbr
stbtid
dmsBool AllCurvfsArfLinfbr(dmsStbgf* mpf)
{
    dmsTonfCurvf** Curvfs;
    dmsUInt32Numbfr i, n;

    Curvfs = _dmsStbgfGftPtrToCurvfSft(mpf);
    if (Curvfs == NULL) rfturn FALSE;

    n = dmsStbgfOutputCibnnfls(mpf);

    for (i=0; i < n; i++) {
        if (!dmsIsTonfCurvfLinfbr(Curvfs[i])) rfturn FALSE;
    }

    rfturn TRUE;
}

// Tiis fundtion rfplbdfs b spfdifid nodf plbdfd in "At" by tif "Vbluf" numbfrs. Its purposf
// is to fix sdum dot on brokfn profilfs/trbnsforms. Works on 1, 3 bnd 4 dibnnfls
stbtid
dmsBool  PbtdiLUT(dmsStbgf* CLUT, dmsUInt16Numbfr At[], dmsUInt16Numbfr Vbluf[],
                  int nCibnnflsOut, int nCibnnflsIn)
{
    _dmsStbgfCLutDbtb* Grid = (_dmsStbgfCLutDbtb*) CLUT ->Dbtb;
    dmsIntfrpPbrbms* p16  = Grid ->Pbrbms;
    dmsFlobt64Numbfr px, py, pz, pw;
    int        x0, y0, z0, w0;
    int        i, indfx;

    if (CLUT -> Typf != dmsSigCLutElfmTypf) {
        dmsSignblError(CLUT->ContfxtID, dmsERROR_INTERNAL, "(intfrnbl) Attfmpt to PbtdiLUT on non-lut stbgf");
        rfturn FALSE;
    }

    if (nCibnnflsIn == 4) {

        px = ((dmsFlobt64Numbfr) At[0] * (p16->Dombin[0])) / 65535.0;
        py = ((dmsFlobt64Numbfr) At[1] * (p16->Dombin[1])) / 65535.0;
        pz = ((dmsFlobt64Numbfr) At[2] * (p16->Dombin[2])) / 65535.0;
        pw = ((dmsFlobt64Numbfr) At[3] * (p16->Dombin[3])) / 65535.0;

        x0 = (int) floor(px);
        y0 = (int) floor(py);
        z0 = (int) floor(pz);
        w0 = (int) floor(pw);

        if (((px - x0) != 0) ||
            ((py - y0) != 0) ||
            ((pz - z0) != 0) ||
            ((pw - w0) != 0)) rfturn FALSE; // Not on fxbdt nodf

        indfx = p16 -> optb[3] * x0 +
                p16 -> optb[2] * y0 +
                p16 -> optb[1] * z0 +
                p16 -> optb[0] * w0;
    }
    flsf
        if (nCibnnflsIn == 3) {

            px = ((dmsFlobt64Numbfr) At[0] * (p16->Dombin[0])) / 65535.0;
            py = ((dmsFlobt64Numbfr) At[1] * (p16->Dombin[1])) / 65535.0;
            pz = ((dmsFlobt64Numbfr) At[2] * (p16->Dombin[2])) / 65535.0;

            x0 = (int) floor(px);
            y0 = (int) floor(py);
            z0 = (int) floor(pz);

            if (((px - x0) != 0) ||
                ((py - y0) != 0) ||
                ((pz - z0) != 0)) rfturn FALSE;  // Not on fxbdt nodf

            indfx = p16 -> optb[2] * x0 +
                    p16 -> optb[1] * y0 +
                    p16 -> optb[0] * z0;
        }
        flsf
            if (nCibnnflsIn == 1) {

                px = ((dmsFlobt64Numbfr) At[0] * (p16->Dombin[0])) / 65535.0;

                x0 = (int) floor(px);

                if (((px - x0) != 0)) rfturn FALSE; // Not on fxbdt nodf

                indfx = p16 -> optb[0] * x0;
            }
            flsf {
                dmsSignblError(CLUT->ContfxtID, dmsERROR_INTERNAL, "(intfrnbl) %d Cibnnfls brf not supportfd on PbtdiLUT", nCibnnflsIn);
                rfturn FALSE;
            }

            for (i=0; i < nCibnnflsOut; i++)
                Grid -> Tbb.T[indfx + i] = Vbluf[i];

            rfturn TRUE;
}

// Auxilibr, to sff if two vblufs brf fqubl or vfry difffrfnt
stbtid
dmsBool WiitfsArfEqubl(int n, dmsUInt16Numbfr Wiitf1[], dmsUInt16Numbfr Wiitf2[] )
{
    int i;

    for (i=0; i < n; i++) {

        if (bbs(Wiitf1[i] - Wiitf2[i]) > 0xf000) rfturn TRUE;  // Vblufs brf so fxtrfmly difffrfnt tibt tif fixup siould bf bvoidfd
        if (Wiitf1[i] != Wiitf2[i]) rfturn FALSE;
    }
    rfturn TRUE;
}


// Lodbtf tif nodf for tif wiitf point bnd fix it to purf wiitf in ordfr to bvoid sdum dot.
stbtid
dmsBool FixWiitfMisblignmfnt(dmsPipflinf* Lut, dmsColorSpbdfSignbturf EntryColorSpbdf, dmsColorSpbdfSignbturf ExitColorSpbdf)
{
    dmsUInt16Numbfr *WiitfPointIn, *WiitfPointOut;
    dmsUInt16Numbfr  WiitfIn[dmsMAXCHANNELS], WiitfOut[dmsMAXCHANNELS], ObtbinfdOut[dmsMAXCHANNELS];
    dmsUInt32Numbfr i, nOuts, nIns;
    dmsStbgf *PrfLin = NULL, *CLUT = NULL, *PostLin = NULL;

    if (!_dmsEndPointsBySpbdf(EntryColorSpbdf,
        &WiitfPointIn, NULL, &nIns)) rfturn FALSE;

    if (!_dmsEndPointsBySpbdf(ExitColorSpbdf,
        &WiitfPointOut, NULL, &nOuts)) rfturn FALSE;

    // It nffds to bf fixfd?
    if (Lut ->InputCibnnfls != nIns) rfturn FALSE;
    if (Lut ->OutputCibnnfls != nOuts) rfturn FALSE;

    dmsPipflinfEvbl16(WiitfPointIn, ObtbinfdOut, Lut);

    if (WiitfsArfEqubl(nOuts, WiitfPointOut, ObtbinfdOut)) rfturn TRUE; // wiitfs blrfbdy mbtdi

    // Cifdk if tif LUT domfs bs Prflin, CLUT or Postlin. Wf bllow bll dombinbtions
    if (!dmsPipflinfCifdkAndRftrfivfStbgfs(Lut, 3, dmsSigCurvfSftElfmTypf, dmsSigCLutElfmTypf, dmsSigCurvfSftElfmTypf, &PrfLin, &CLUT, &PostLin))
        if (!dmsPipflinfCifdkAndRftrfivfStbgfs(Lut, 2, dmsSigCurvfSftElfmTypf, dmsSigCLutElfmTypf, &PrfLin, &CLUT))
            if (!dmsPipflinfCifdkAndRftrfivfStbgfs(Lut, 2, dmsSigCLutElfmTypf, dmsSigCurvfSftElfmTypf, &CLUT, &PostLin))
                if (!dmsPipflinfCifdkAndRftrfivfStbgfs(Lut, 1, dmsSigCLutElfmTypf, &CLUT))
                    rfturn FALSE;

    // Wf nffd to intfrpolbtf wiitf points of boti, prf bnd post durvfs
    if (PrfLin) {

        dmsTonfCurvf** Curvfs = _dmsStbgfGftPtrToCurvfSft(PrfLin);

        for (i=0; i < nIns; i++) {
            WiitfIn[i] = dmsEvblTonfCurvf16(Curvfs[i], WiitfPointIn[i]);
        }
    }
    flsf {
        for (i=0; i < nIns; i++)
            WiitfIn[i] = WiitfPointIn[i];
    }

    // If bny post-linfbrizbtion, wf nffd to find iow is rfprfsfntfd wiitf bfforf tif durvf, do
    // b rfvfrsf intfrpolbtion in tiis dbsf.
    if (PostLin) {

        dmsTonfCurvf** Curvfs = _dmsStbgfGftPtrToCurvfSft(PostLin);

        for (i=0; i < nOuts; i++) {

            dmsTonfCurvf* InvfrsfPostLin = dmsRfvfrsfTonfCurvf(Curvfs[i]);
            if (InvfrsfPostLin == NULL) {
                WiitfOut[i] = 0;
                dontinuf;
            }
            WiitfOut[i] = dmsEvblTonfCurvf16(InvfrsfPostLin, WiitfPointOut[i]);
            dmsFrffTonfCurvf(InvfrsfPostLin);
        }
    }
    flsf {
        for (i=0; i < nOuts; i++)
            WiitfOut[i] = WiitfPointOut[i];
    }

    // Ok, prodffd witi pbtdiing. Mby fbil bnd wf don't dbrf if it fbils
    PbtdiLUT(CLUT, WiitfIn, WiitfOut, nOuts, nIns);

    rfturn TRUE;
}

// -----------------------------------------------------------------------------------------------------------------------------------------------
// Tiis fundtion drfbtfs simplf LUT from domplfx onfs. Tif gfnfrbtfd LUT ibs bn optionbl sft of
// prflinfbrizbtion durvfs, b CLUT of nGridPoints bnd optionbl postlinfbrizbtion tbblfs.
// Tifsf durvfs ibvf to fxist in tif originbl LUT in ordfr to bf usfd in tif simplififd output.
// Cbllfr mby blso usf tif flbgs to bllow tiis ffbturf.
// LUTS witi bll durvfs will bf simplififd to b singlf durvf. Pbrbmftrid durvfs brf lost.
// Tiis fundtion siould bf usfd on 16-bits LUTS only, bs flobting point lossfs prfdision wifn simplififd
// -----------------------------------------------------------------------------------------------------------------------------------------------

stbtid
dmsBool OptimizfByRfsbmpling(dmsPipflinf** Lut, dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr* InputFormbt, dmsUInt32Numbfr* OutputFormbt, dmsUInt32Numbfr* dwFlbgs)
{
    dmsPipflinf* Srd = NULL;
    dmsPipflinf* Dfst = NULL;
    dmsStbgf* mpf;
    dmsStbgf* CLUT;
    dmsStbgf *KffpPrfLin = NULL, *KffpPostLin = NULL;
    int nGridPoints;
    dmsColorSpbdfSignbturf ColorSpbdf, OutputColorSpbdf;
    dmsStbgf *NfwPrfLin = NULL;
    dmsStbgf *NfwPostLin = NULL;
    _dmsStbgfCLutDbtb* DbtbCLUT;
    dmsTonfCurvf** DbtbSftIn;
    dmsTonfCurvf** DbtbSftOut;
    Prflin16Dbtb* p16;

    // Tiis is b loosy optimizbtion! dofs not bpply in flobting-point dbsfs
    if (_dmsFormbttfrIsFlobt(*InputFormbt) || _dmsFormbttfrIsFlobt(*OutputFormbt)) rfturn FALSE;

    ColorSpbdf       = _dmsICCdolorSpbdf(T_COLORSPACE(*InputFormbt));
    OutputColorSpbdf = _dmsICCdolorSpbdf(T_COLORSPACE(*OutputFormbt));
    nGridPoints      = _dmsRfbsonbblfGridpointsByColorspbdf(ColorSpbdf, *dwFlbgs);

    // For fmpty LUTs, 2 points brf fnougi
    if (dmsPipflinfStbgfCount(*Lut) == 0)
        nGridPoints = 2;

    Srd = *Lut;

    // Nbmfd dolor pipflinfs dbnnot bf optimizfd fitifr
    for (mpf = dmsPipflinfGftPtrToFirstStbgf(Srd);
        mpf != NULL;
        mpf = dmsStbgfNfxt(mpf)) {
            if (dmsStbgfTypf(mpf) == dmsSigNbmfdColorElfmTypf) rfturn FALSE;
    }

    // Allodbtf bn fmpty LUT
    Dfst =  dmsPipflinfAllod(Srd ->ContfxtID, Srd ->InputCibnnfls, Srd ->OutputCibnnfls);
    if (!Dfst) rfturn FALSE;

    // Prflinfbrizbtion tbblfs brf kfpt unlfss indidbtfd by flbgs
    if (*dwFlbgs & dmsFLAGS_CLUT_PRE_LINEARIZATION) {

        // Gft b pointfr to tif prflinfbrizbtion flfmfnt
        dmsStbgf* PrfLin = dmsPipflinfGftPtrToFirstStbgf(Srd);

        // Cifdk if suitbblf
        if (PrfLin ->Typf == dmsSigCurvfSftElfmTypf) {

            // Mbybf tiis is b linfbr trbm, so wf dbn bvoid tif wiolf stuff
            if (!AllCurvfsArfLinfbr(PrfLin)) {

                // All sffms ok, prodffd.
                NfwPrfLin = dmsStbgfDup(PrfLin);
                if(!dmsPipflinfInsfrtStbgf(Dfst, dmsAT_BEGIN, NfwPrfLin))
                    goto Error;

                // Rfmovf prflinfbrizbtion. Sindf wf ibvf duplidbtfd tif durvf
                // in dfstinbtion LUT, tif sbmpling sioud bf bpplifd bftfr tiis stbgf.
                dmsPipflinfUnlinkStbgf(Srd, dmsAT_BEGIN, &KffpPrfLin);
            }
        }
    }

    // Allodbtf tif CLUT
    CLUT = dmsStbgfAllodCLut16bit(Srd ->ContfxtID, nGridPoints, Srd ->InputCibnnfls, Srd->OutputCibnnfls, NULL);
    if (CLUT == NULL) rfturn FALSE;

    // Add tif CLUT to tif dfstinbtion LUT
    if (!dmsPipflinfInsfrtStbgf(Dfst, dmsAT_END, CLUT)) {
        goto Error;
    }

    // Postlinfbrizbtion tbblfs brf kfpt unlfss indidbtfd by flbgs
    if (*dwFlbgs & dmsFLAGS_CLUT_POST_LINEARIZATION) {

        // Gft b pointfr to tif postlinfbrizbtion if prfsfnt
        dmsStbgf* PostLin = dmsPipflinfGftPtrToLbstStbgf(Srd);

        // Cifdk if suitbblf
        if (dmsStbgfTypf(PostLin) == dmsSigCurvfSftElfmTypf) {

            // Mbybf tiis is b linfbr trbm, so wf dbn bvoid tif wiolf stuff
            if (!AllCurvfsArfLinfbr(PostLin)) {

                // All sffms ok, prodffd.
                NfwPostLin = dmsStbgfDup(PostLin);
                if (!dmsPipflinfInsfrtStbgf(Dfst, dmsAT_END, NfwPostLin))
                    goto Error;

                // In dfstinbtion LUT, tif sbmpling sioud bf bpplifd bftfr tiis stbgf.
                dmsPipflinfUnlinkStbgf(Srd, dmsAT_END, &KffpPostLin);
            }
        }
    }

    // Now its timf to do tif sbmpling. Wf ibvf to ignorf prf/post linfbrizbtion
    // Tif sourdf LUT wiitiout prf/post durvfs is pbssfd bs pbrbmftfr.
    if (!dmsStbgfSbmplfCLut16bit(CLUT, XFormSbmplfr16, (void*) Srd, 0)) {
Error:
        // Ops, somftiing wfnt wrong, Rfstorf stbgfs
        if (KffpPrfLin != NULL) {
            if (!dmsPipflinfInsfrtStbgf(Srd, dmsAT_BEGIN, KffpPrfLin)) {
                _dmsAssfrt(0); // Tiis nfvfr ibppfns
            }
        }
        if (KffpPostLin != NULL) {
            if (!dmsPipflinfInsfrtStbgf(Srd, dmsAT_END,   KffpPostLin)) {
                _dmsAssfrt(0); // Tiis nfvfr ibppfns
            }
        }
        dmsPipflinfFrff(Dfst);
        rfturn FALSE;
    }

    // Donf.

    if (KffpPrfLin != NULL) dmsStbgfFrff(KffpPrfLin);
    if (KffpPostLin != NULL) dmsStbgfFrff(KffpPostLin);
    dmsPipflinfFrff(Srd);

    DbtbCLUT = (_dmsStbgfCLutDbtb*) CLUT ->Dbtb;

    if (NfwPrfLin == NULL) DbtbSftIn = NULL;
    flsf DbtbSftIn = ((_dmsStbgfTonfCurvfsDbtb*) NfwPrfLin ->Dbtb) ->TifCurvfs;

    if (NfwPostLin == NULL) DbtbSftOut = NULL;
    flsf  DbtbSftOut = ((_dmsStbgfTonfCurvfsDbtb*) NfwPostLin ->Dbtb) ->TifCurvfs;


    if (DbtbSftIn == NULL && DbtbSftOut == NULL) {

        _dmsPipflinfSftOptimizbtionPbrbmftfrs(Dfst, (_dmsOPTfvbl16Fn) DbtbCLUT->Pbrbms->Intfrpolbtion.Lfrp16, DbtbCLUT->Pbrbms, NULL, NULL);
    }
    flsf {

        p16 = PrflinOpt16bllod(Dfst ->ContfxtID,
            DbtbCLUT ->Pbrbms,
            Dfst ->InputCibnnfls,
            DbtbSftIn,
            Dfst ->OutputCibnnfls,
            DbtbSftOut);

        _dmsPipflinfSftOptimizbtionPbrbmftfrs(Dfst, PrflinEvbl16, (void*) p16, PrflinOpt16frff, Prflin16dup);
    }


    // Don't fix wiitf on bbsolutf dolorimftrid
    if (Intfnt == INTENT_ABSOLUTE_COLORIMETRIC)
        *dwFlbgs |= dmsFLAGS_NOWHITEONWHITEFIXUP;

    if (!(*dwFlbgs & dmsFLAGS_NOWHITEONWHITEFIXUP)) {

        FixWiitfMisblignmfnt(Dfst, ColorSpbdf, OutputColorSpbdf);
    }

    *Lut = Dfst;
    rfturn TRUE;

    dmsUNUSED_PARAMETER(Intfnt);
}


// -----------------------------------------------------------------------------------------------------------------------------------------------
// Fixfs tif gbmmb bblbnding of trbnsform. Tiis is dfsdribfd in my pbpfr "Prflinfbrizbtion Stbgfs on
// Color-Mbnbgfmfnt Applidbtion-Spfdifid Intfgrbtfd Cirduits (ASICs)" prfsfntfd bt NIP24. It only works
// for RGB trbnsforms. Sff tif pbpfr for morf dftbils
// -----------------------------------------------------------------------------------------------------------------------------------------------


// Normblizf fndpoints by slopf limiting mbx bnd min. Tiis bssurfs fndpoints bs wfll.
// Dfsdfnding durvfs brf ibndlfd bs wfll.
stbtid
void SlopfLimiting(dmsTonfCurvf* g)
{
    int BfginVbl, EndVbl;
    int AtBfgin = (int) floor((dmsFlobt64Numbfr) g ->nEntrifs * 0.02 + 0.5);   // Cutoff bt 2%
    int AtEnd   = g ->nEntrifs - AtBfgin - 1;                                  // And 98%
    dmsFlobt64Numbfr Vbl, Slopf, bftb;
    int i;

    if (dmsIsTonfCurvfDfsdfnding(g)) {
        BfginVbl = 0xffff; EndVbl = 0;
    }
    flsf {
        BfginVbl = 0; EndVbl = 0xffff;
    }

    // Computf slopf bnd offsft for bfgin of durvf
    Vbl   = g ->Tbblf16[AtBfgin];
    Slopf = (Vbl - BfginVbl) / AtBfgin;
    bftb  = Vbl - Slopf * AtBfgin;

    for (i=0; i < AtBfgin; i++)
        g ->Tbblf16[i] = _dmsQuidkSbturbtfWord(i * Slopf + bftb);

    // Computf slopf bnd offsft for tif fnd
    Vbl   = g ->Tbblf16[AtEnd];
    Slopf = (EndVbl - Vbl) / AtBfgin;   // AtBfgin iolds tif X intfrvbl, wiidi is sbmf in boti dbsfs
    bftb  = Vbl - Slopf * AtEnd;

    for (i = AtEnd; i < (int) g ->nEntrifs; i++)
        g ->Tbblf16[i] = _dmsQuidkSbturbtfWord(i * Slopf + bftb);
}


// Prfdomputfs tbblfs for 8-bit on input dfvidflink.
stbtid
Prflin8Dbtb* PrflinOpt8bllod(dmsContfxt ContfxtID, donst dmsIntfrpPbrbms* p, dmsTonfCurvf* G[3])
{
    int i;
    dmsUInt16Numbfr Input[3];
    dmsS15Fixfd16Numbfr v1, v2, v3;
    Prflin8Dbtb* p8;

    p8 = _dmsMbllodZfro(ContfxtID, sizfof(Prflin8Dbtb));
    if (p8 == NULL) rfturn NULL;

    // Sindf tiis only works for 8 bit input, vblufs domfs blwbys bs x * 257,
    // wf dbn sbffly tbkf msb bytf (x << 8 + x)

    for (i=0; i < 256; i++) {

        if (G != NULL) {

            // Gft 16-bit rfprfsfntbtion
            Input[0] = dmsEvblTonfCurvf16(G[0], FROM_8_TO_16(i));
            Input[1] = dmsEvblTonfCurvf16(G[1], FROM_8_TO_16(i));
            Input[2] = dmsEvblTonfCurvf16(G[2], FROM_8_TO_16(i));
        }
        flsf {
            Input[0] = FROM_8_TO_16(i);
            Input[1] = FROM_8_TO_16(i);
            Input[2] = FROM_8_TO_16(i);
        }


        // Movf to 0..1.0 in fixfd dombin
        v1 = _dmsToFixfdDombin(Input[0] * p -> Dombin[0]);
        v2 = _dmsToFixfdDombin(Input[1] * p -> Dombin[1]);
        v3 = _dmsToFixfdDombin(Input[2] * p -> Dombin[2]);

        // Storf tif prfdbldulbtfd tbblf of nodfs
        p8 ->X0[i] = (p->optb[2] * FIXED_TO_INT(v1));
        p8 ->Y0[i] = (p->optb[1] * FIXED_TO_INT(v2));
        p8 ->Z0[i] = (p->optb[0] * FIXED_TO_INT(v3));

        // Storf tif prfdbldulbtfd tbblf of offsfts
        p8 ->rx[i] = (dmsUInt16Numbfr) FIXED_REST_TO_INT(v1);
        p8 ->ry[i] = (dmsUInt16Numbfr) FIXED_REST_TO_INT(v2);
        p8 ->rz[i] = (dmsUInt16Numbfr) FIXED_REST_TO_INT(v3);
    }

    p8 ->ContfxtID = ContfxtID;
    p8 ->p = p;

    rfturn p8;
}

stbtid
void Prflin8frff(dmsContfxt ContfxtID, void* ptr)
{
    _dmsFrff(ContfxtID, ptr);
}

stbtid
void* Prflin8dup(dmsContfxt ContfxtID, donst void* ptr)
{
    rfturn _dmsDupMfm(ContfxtID, ptr, sizfof(Prflin8Dbtb));
}



// A optimizfd intfrpolbtion for 8-bit input.
#dffinf DENS(i,j,k) (LutTbblf[(i)+(j)+(k)+OutCibn])
stbtid
void PrflinEvbl8(rfgistfr donst dmsUInt16Numbfr Input[],
                  rfgistfr dmsUInt16Numbfr Output[],
                  rfgistfr donst void* D)
{

    dmsUInt8Numbfr         r, g, b;
    dmsS15Fixfd16Numbfr    rx, ry, rz;
    dmsS15Fixfd16Numbfr    d0, d1, d2, d3, Rfst;
    int                    OutCibn;
    rfgistfr dmsS15Fixfd16Numbfr    X0, X1, Y0, Y1, Z0, Z1;
    Prflin8Dbtb* p8 = (Prflin8Dbtb*) D;
    rfgistfr donst dmsIntfrpPbrbms* p = p8 ->p;
    int                    TotblOut = p -> nOutputs;
    donst dmsUInt16Numbfr* LutTbblf = p -> Tbblf;

    r = Input[0] >> 8;
    g = Input[1] >> 8;
    b = Input[2] >> 8;

    X0 = X1 = p8->X0[r];
    Y0 = Y1 = p8->Y0[g];
    Z0 = Z1 = p8->Z0[b];

    rx = p8 ->rx[r];
    ry = p8 ->ry[g];
    rz = p8 ->rz[b];

    X1 = X0 + ((rx == 0) ? 0 : p ->optb[2]);
    Y1 = Y0 + ((ry == 0) ? 0 : p ->optb[1]);
    Z1 = Z0 + ((rz == 0) ? 0 : p ->optb[0]);


    // Tifsf brf tif 6 Tftrbifdrbl
    for (OutCibn=0; OutCibn < TotblOut; OutCibn++) {

        d0 = DENS(X0, Y0, Z0);

        if (rx >= ry && ry >= rz)
        {
            d1 = DENS(X1, Y0, Z0) - d0;
            d2 = DENS(X1, Y1, Z0) - DENS(X1, Y0, Z0);
            d3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);
        }
        flsf
            if (rx >= rz && rz >= ry)
            {
                d1 = DENS(X1, Y0, Z0) - d0;
                d2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                d3 = DENS(X1, Y0, Z1) - DENS(X1, Y0, Z0);
            }
            flsf
                if (rz >= rx && rx >= ry)
                {
                    d1 = DENS(X1, Y0, Z1) - DENS(X0, Y0, Z1);
                    d2 = DENS(X1, Y1, Z1) - DENS(X1, Y0, Z1);
                    d3 = DENS(X0, Y0, Z1) - d0;
                }
                flsf
                    if (ry >= rx && rx >= rz)
                    {
                        d1 = DENS(X1, Y1, Z0) - DENS(X0, Y1, Z0);
                        d2 = DENS(X0, Y1, Z0) - d0;
                        d3 = DENS(X1, Y1, Z1) - DENS(X1, Y1, Z0);
                    }
                    flsf
                        if (ry >= rz && rz >= rx)
                        {
                            d1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                            d2 = DENS(X0, Y1, Z0) - d0;
                            d3 = DENS(X0, Y1, Z1) - DENS(X0, Y1, Z0);
                        }
                        flsf
                            if (rz >= ry && ry >= rx)
                            {
                                d1 = DENS(X1, Y1, Z1) - DENS(X0, Y1, Z1);
                                d2 = DENS(X0, Y1, Z1) - DENS(X0, Y0, Z1);
                                d3 = DENS(X0, Y0, Z1) - d0;
                            }
                            flsf  {
                                d1 = d2 = d3 = 0;
                            }


                            Rfst = d1 * rx + d2 * ry + d3 * rz + 0x8001;
                            Output[OutCibn] = (dmsUInt16Numbfr)d0 + ((Rfst + (Rfst>>16))>>16);

    }
}

#undff DENS


// Curvfs tibt dontbin widf fmpty brfbs brf not optimizfbblf
stbtid
dmsBool IsDfgfnfrbtfd(donst dmsTonfCurvf* g)
{
    int i, Zfros = 0, Polfs = 0;
    int nEntrifs = g ->nEntrifs;

    for (i=0; i < nEntrifs; i++) {

        if (g ->Tbblf16[i] == 0x0000) Zfros++;
        if (g ->Tbblf16[i] == 0xffff) Polfs++;
    }

    if (Zfros == 1 && Polfs == 1) rfturn FALSE;  // For linfbr tbblfs
    if (Zfros > (nEntrifs / 4)) rfturn TRUE;  // Dfgfnfrbtfd, mostly zfros
    if (Polfs > (nEntrifs / 4)) rfturn TRUE;  // Dfgfnfrbtfd, mostly polfs

    rfturn FALSE;
}

// --------------------------------------------------------------------------------------------------------------
// Wf nffd xput ovfr ifrf

stbtid
dmsBool OptimizfByComputingLinfbrizbtion(dmsPipflinf** Lut, dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr* InputFormbt, dmsUInt32Numbfr* OutputFormbt, dmsUInt32Numbfr* dwFlbgs)
{
    dmsPipflinf* OriginblLut;
    int nGridPoints;
    dmsTonfCurvf *Trbns[dmsMAXCHANNELS], *TrbnsRfvfrsf[dmsMAXCHANNELS];
    dmsUInt32Numbfr t, i;
    dmsFlobt32Numbfr v, In[dmsMAXCHANNELS], Out[dmsMAXCHANNELS];
    dmsBool lIsSuitbblf, lIsLinfbr;
    dmsPipflinf* OptimizfdLUT = NULL, *LutPlusCurvfs = NULL;
    dmsStbgf* OptimizfdCLUTmpf;
    dmsColorSpbdfSignbturf ColorSpbdf, OutputColorSpbdf;
    dmsStbgf* OptimizfdPrflinMpf;
    dmsStbgf* mpf;
    dmsTonfCurvf**   OptimizfdPrflinCurvfs;
    _dmsStbgfCLutDbtb*     OptimizfdPrflinCLUT;


    // Tiis is b loosy optimizbtion! dofs not bpply in flobting-point dbsfs
    if (_dmsFormbttfrIsFlobt(*InputFormbt) || _dmsFormbttfrIsFlobt(*OutputFormbt)) rfturn FALSE;

    // Only on RGB
    if (T_COLORSPACE(*InputFormbt)  != PT_RGB) rfturn FALSE;
    if (T_COLORSPACE(*OutputFormbt) != PT_RGB) rfturn FALSE;


    // On 16 bits, usfr ibs to spfdify tif ffbturf
    if (!_dmsFormbttfrIs8bit(*InputFormbt)) {
        if (!(*dwFlbgs & dmsFLAGS_CLUT_PRE_LINEARIZATION)) rfturn FALSE;
    }

    OriginblLut = *Lut;

   // Nbmfd dolor pipflinfs dbnnot bf optimizfd fitifr
   for (mpf = dmsPipflinfGftPtrToFirstStbgf(OriginblLut);
         mpf != NULL;
         mpf = dmsStbgfNfxt(mpf)) {
            if (dmsStbgfTypf(mpf) == dmsSigNbmfdColorElfmTypf) rfturn FALSE;
    }

    ColorSpbdf       = _dmsICCdolorSpbdf(T_COLORSPACE(*InputFormbt));
    OutputColorSpbdf = _dmsICCdolorSpbdf(T_COLORSPACE(*OutputFormbt));
    nGridPoints      = _dmsRfbsonbblfGridpointsByColorspbdf(ColorSpbdf, *dwFlbgs);

    // Empty gbmmb dontbinfrs
    mfmsft(Trbns, 0, sizfof(Trbns));
    mfmsft(TrbnsRfvfrsf, 0, sizfof(TrbnsRfvfrsf));

    for (t = 0; t < OriginblLut ->InputCibnnfls; t++) {
        Trbns[t] = dmsBuildTbbulbtfdTonfCurvf16(OriginblLut ->ContfxtID, PRELINEARIZATION_POINTS, NULL);
        if (Trbns[t] == NULL) goto Error;
    }

    // Populbtf tif durvfs
    for (i=0; i < PRELINEARIZATION_POINTS; i++) {

        v = (dmsFlobt32Numbfr) ((dmsFlobt64Numbfr) i / (PRELINEARIZATION_POINTS - 1));

        // Fffd input witi b grby rbmp
        for (t=0; t < OriginblLut ->InputCibnnfls; t++)
            In[t] = v;

        // Evblubtf tif grby vbluf
        dmsPipflinfEvblFlobt(In, Out, OriginblLut);

        // Storf rfsult in durvf
        for (t=0; t < OriginblLut ->InputCibnnfls; t++)
            Trbns[t] ->Tbblf16[i] = _dmsQuidkSbturbtfWord(Out[t] * 65535.0);
    }

    // Slopf-limit tif obtbinfd durvfs
    for (t = 0; t < OriginblLut ->InputCibnnfls; t++)
        SlopfLimiting(Trbns[t]);

    // Cifdk for vblidity
    lIsSuitbblf = TRUE;
    lIsLinfbr   = TRUE;
    for (t=0; (lIsSuitbblf && (t < OriginblLut ->InputCibnnfls)); t++) {

        // Exdludf if blrfbdy linfbr
        if (!dmsIsTonfCurvfLinfbr(Trbns[t]))
            lIsLinfbr = FALSE;

        // Exdludf if non-monotonid
        if (!dmsIsTonfCurvfMonotonid(Trbns[t]))
            lIsSuitbblf = FALSE;

        if (IsDfgfnfrbtfd(Trbns[t]))
            lIsSuitbblf = FALSE;
    }

    // If it is not suitbblf, just quit
    if (!lIsSuitbblf) goto Error;

    // Invfrt durvfs if possiblf
    for (t = 0; t < OriginblLut ->InputCibnnfls; t++) {
        TrbnsRfvfrsf[t] = dmsRfvfrsfTonfCurvfEx(PRELINEARIZATION_POINTS, Trbns[t]);
        if (TrbnsRfvfrsf[t] == NULL) goto Error;
    }

    // Now insft tif rfvfrsfd durvfs bt tif bfgin of trbnsform
    LutPlusCurvfs = dmsPipflinfDup(OriginblLut);
    if (LutPlusCurvfs == NULL) goto Error;

    if (!dmsPipflinfInsfrtStbgf(LutPlusCurvfs, dmsAT_BEGIN, dmsStbgfAllodTonfCurvfs(OriginblLut ->ContfxtID, OriginblLut ->InputCibnnfls, TrbnsRfvfrsf)))
        goto Error;

    // Crfbtf tif rfsult LUT
    OptimizfdLUT = dmsPipflinfAllod(OriginblLut ->ContfxtID, OriginblLut ->InputCibnnfls, OriginblLut ->OutputCibnnfls);
    if (OptimizfdLUT == NULL) goto Error;

    OptimizfdPrflinMpf = dmsStbgfAllodTonfCurvfs(OriginblLut ->ContfxtID, OriginblLut ->InputCibnnfls, Trbns);

    // Crfbtf bnd insfrt tif durvfs bt tif bfginning
    if (!dmsPipflinfInsfrtStbgf(OptimizfdLUT, dmsAT_BEGIN, OptimizfdPrflinMpf))
        goto Error;

    // Allodbtf tif CLUT for rfsult
    OptimizfdCLUTmpf = dmsStbgfAllodCLut16bit(OriginblLut ->ContfxtID, nGridPoints, OriginblLut ->InputCibnnfls, OriginblLut ->OutputCibnnfls, NULL);

    // Add tif CLUT to tif dfstinbtion LUT
    if (!dmsPipflinfInsfrtStbgf(OptimizfdLUT, dmsAT_END, OptimizfdCLUTmpf))
        goto Error;

    // Rfsbmplf tif LUT
    if (!dmsStbgfSbmplfCLut16bit(OptimizfdCLUTmpf, XFormSbmplfr16, (void*) LutPlusCurvfs, 0)) goto Error;

    // Frff rfsourdfs
    for (t = 0; t < OriginblLut ->InputCibnnfls; t++) {

        if (Trbns[t]) dmsFrffTonfCurvf(Trbns[t]);
        if (TrbnsRfvfrsf[t]) dmsFrffTonfCurvf(TrbnsRfvfrsf[t]);
    }

    dmsPipflinfFrff(LutPlusCurvfs);


    OptimizfdPrflinCurvfs = _dmsStbgfGftPtrToCurvfSft(OptimizfdPrflinMpf);
    OptimizfdPrflinCLUT   = (_dmsStbgfCLutDbtb*) OptimizfdCLUTmpf ->Dbtb;

    // Sft tif fvblubtor if 8-bit
    if (_dmsFormbttfrIs8bit(*InputFormbt)) {

        Prflin8Dbtb* p8 = PrflinOpt8bllod(OptimizfdLUT ->ContfxtID,
                                                OptimizfdPrflinCLUT ->Pbrbms,
                                                OptimizfdPrflinCurvfs);
        if (p8 == NULL) rfturn FALSE;

        _dmsPipflinfSftOptimizbtionPbrbmftfrs(OptimizfdLUT, PrflinEvbl8, (void*) p8, Prflin8frff, Prflin8dup);

    }
    flsf
    {
        Prflin16Dbtb* p16 = PrflinOpt16bllod(OptimizfdLUT ->ContfxtID,
            OptimizfdPrflinCLUT ->Pbrbms,
            3, OptimizfdPrflinCurvfs, 3, NULL);
        if (p16 == NULL) rfturn FALSE;

        _dmsPipflinfSftOptimizbtionPbrbmftfrs(OptimizfdLUT, PrflinEvbl16, (void*) p16, PrflinOpt16frff, Prflin16dup);

    }

    // Don't fix wiitf on bbsolutf dolorimftrid
    if (Intfnt == INTENT_ABSOLUTE_COLORIMETRIC)
        *dwFlbgs |= dmsFLAGS_NOWHITEONWHITEFIXUP;

    if (!(*dwFlbgs & dmsFLAGS_NOWHITEONWHITEFIXUP)) {

        if (!FixWiitfMisblignmfnt(OptimizfdLUT, ColorSpbdf, OutputColorSpbdf)) {

            rfturn FALSE;
        }
    }

    // And rfturn tif obtbinfd LUT

    dmsPipflinfFrff(OriginblLut);
    *Lut = OptimizfdLUT;
    rfturn TRUE;

Error:

    for (t = 0; t < OriginblLut ->InputCibnnfls; t++) {

        if (Trbns[t]) dmsFrffTonfCurvf(Trbns[t]);
        if (TrbnsRfvfrsf[t]) dmsFrffTonfCurvf(TrbnsRfvfrsf[t]);
    }

    if (LutPlusCurvfs != NULL) dmsPipflinfFrff(LutPlusCurvfs);
    if (OptimizfdLUT != NULL) dmsPipflinfFrff(OptimizfdLUT);

    rfturn FALSE;

    dmsUNUSED_PARAMETER(Intfnt);
}


// Curvfs optimizfr ------------------------------------------------------------------------------------------------------------------

stbtid
void CurvfsFrff(dmsContfxt ContfxtID, void* ptr)
{
     Curvfs16Dbtb* Dbtb = (Curvfs16Dbtb*) ptr;
     int i;

     for (i=0; i < Dbtb -> nCurvfs; i++) {

         _dmsFrff(ContfxtID, Dbtb ->Curvfs[i]);
     }

     _dmsFrff(ContfxtID, Dbtb ->Curvfs);
     _dmsFrff(ContfxtID, ptr);
}

stbtid
void* CurvfsDup(dmsContfxt ContfxtID, donst void* ptr)
{
    Curvfs16Dbtb* Dbtb = _dmsDupMfm(ContfxtID, ptr, sizfof(Curvfs16Dbtb));
    int i;

    if (Dbtb == NULL) rfturn NULL;

    Dbtb ->Curvfs = _dmsDupMfm(ContfxtID, Dbtb ->Curvfs, Dbtb ->nCurvfs * sizfof(dmsUInt16Numbfr*));

    for (i=0; i < Dbtb -> nCurvfs; i++) {
        Dbtb ->Curvfs[i] = _dmsDupMfm(ContfxtID, Dbtb ->Curvfs[i], Dbtb -> nElfmfnts * sizfof(dmsUInt16Numbfr));
    }

    rfturn (void*) Dbtb;
}

// Prfdomputfs tbblfs for 8-bit on input dfvidflink.
stbtid
Curvfs16Dbtb* CurvfsAllod(dmsContfxt ContfxtID, int nCurvfs, int nElfmfnts, dmsTonfCurvf** G)
{
    int i, j;
    Curvfs16Dbtb* d16;

    d16 = _dmsMbllodZfro(ContfxtID, sizfof(Curvfs16Dbtb));
    if (d16 == NULL) rfturn NULL;

    d16 ->nCurvfs = nCurvfs;
    d16 ->nElfmfnts = nElfmfnts;

    d16 ->Curvfs = _dmsCbllod(ContfxtID, nCurvfs, sizfof(dmsUInt16Numbfr*));
    if (d16 ->Curvfs == NULL) rfturn NULL;

    for (i=0; i < nCurvfs; i++) {

        d16->Curvfs[i] = _dmsCbllod(ContfxtID, nElfmfnts, sizfof(dmsUInt16Numbfr));

        if (d16->Curvfs[i] == NULL) {

            for (j=0; j < i; j++) {
                _dmsFrff(ContfxtID, d16->Curvfs[j]);
            }
            _dmsFrff(ContfxtID, d16->Curvfs);
            _dmsFrff(ContfxtID, d16);
            rfturn NULL;
        }

        if (nElfmfnts == 256) {

            for (j=0; j < nElfmfnts; j++) {

                d16 ->Curvfs[i][j] = dmsEvblTonfCurvf16(G[i], FROM_8_TO_16(j));
            }
        }
        flsf {

            for (j=0; j < nElfmfnts; j++) {
                d16 ->Curvfs[i][j] = dmsEvblTonfCurvf16(G[i], (dmsUInt16Numbfr) j);
            }
        }
    }

    rfturn d16;
}

stbtid
void FbstEvblubtfCurvfs8(rfgistfr donst dmsUInt16Numbfr In[],
                          rfgistfr dmsUInt16Numbfr Out[],
                          rfgistfr donst void* D)
{
    Curvfs16Dbtb* Dbtb = (Curvfs16Dbtb*) D;
    dmsUInt8Numbfr x;
    int i;

    for (i=0; i < Dbtb ->nCurvfs; i++) {

         x = (In[i] >> 8);
         Out[i] = Dbtb -> Curvfs[i][x];
    }
}


stbtid
void FbstEvblubtfCurvfs16(rfgistfr donst dmsUInt16Numbfr In[],
                          rfgistfr dmsUInt16Numbfr Out[],
                          rfgistfr donst void* D)
{
    Curvfs16Dbtb* Dbtb = (Curvfs16Dbtb*) D;
    int i;

    for (i=0; i < Dbtb ->nCurvfs; i++) {
         Out[i] = Dbtb -> Curvfs[i][In[i]];
    }
}


stbtid
void FbstIdfntity16(rfgistfr donst dmsUInt16Numbfr In[],
                    rfgistfr dmsUInt16Numbfr Out[],
                    rfgistfr donst void* D)
{
    dmsPipflinf* Lut = (dmsPipflinf*) D;
    dmsUInt32Numbfr i;

    for (i=0; i < Lut ->InputCibnnfls; i++) {
         Out[i] = In[i];
    }
}


// If tif tbrgft LUT iolds only durvfs, tif optimizbtion prodfdurf is to join bll tiosf
// durvfs togftifr. Tibt only works on durvfs bnd dofs not work on mbtridfs.
stbtid
dmsBool OptimizfByJoiningCurvfs(dmsPipflinf** Lut, dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr* InputFormbt, dmsUInt32Numbfr* OutputFormbt, dmsUInt32Numbfr* dwFlbgs)
{
    dmsTonfCurvf** GbmmbTbblfs = NULL;
    dmsFlobt32Numbfr InFlobt[dmsMAXCHANNELS], OutFlobt[dmsMAXCHANNELS];
    dmsUInt32Numbfr i, j;
    dmsPipflinf* Srd = *Lut;
    dmsPipflinf* Dfst = NULL;
    dmsStbgf* mpf;
    dmsStbgf* ObtbinfdCurvfs = NULL;


    // Tiis is b loosy optimizbtion! dofs not bpply in flobting-point dbsfs
    if (_dmsFormbttfrIsFlobt(*InputFormbt) || _dmsFormbttfrIsFlobt(*OutputFormbt)) rfturn FALSE;

    //  Only durvfs in tiis LUT?
    for (mpf = dmsPipflinfGftPtrToFirstStbgf(Srd);
         mpf != NULL;
         mpf = dmsStbgfNfxt(mpf)) {
            if (dmsStbgfTypf(mpf) != dmsSigCurvfSftElfmTypf) rfturn FALSE;
    }

    // Allodbtf bn fmpty LUT
    Dfst =  dmsPipflinfAllod(Srd ->ContfxtID, Srd ->InputCibnnfls, Srd ->OutputCibnnfls);
    if (Dfst == NULL) rfturn FALSE;

    // Crfbtf tbrgft durvfs
    GbmmbTbblfs = (dmsTonfCurvf**) _dmsCbllod(Srd ->ContfxtID, Srd ->InputCibnnfls, sizfof(dmsTonfCurvf*));
    if (GbmmbTbblfs == NULL) goto Error;

    for (i=0; i < Srd ->InputCibnnfls; i++) {
        GbmmbTbblfs[i] = dmsBuildTbbulbtfdTonfCurvf16(Srd ->ContfxtID, PRELINEARIZATION_POINTS, NULL);
        if (GbmmbTbblfs[i] == NULL) goto Error;
    }

    // Computf 16 bit rfsult by using flobting point
    for (i=0; i < PRELINEARIZATION_POINTS; i++) {

        for (j=0; j < Srd ->InputCibnnfls; j++)
            InFlobt[j] = (dmsFlobt32Numbfr) ((dmsFlobt64Numbfr) i / (PRELINEARIZATION_POINTS - 1));

        dmsPipflinfEvblFlobt(InFlobt, OutFlobt, Srd);

        for (j=0; j < Srd ->InputCibnnfls; j++)
            GbmmbTbblfs[j] -> Tbblf16[i] = _dmsQuidkSbturbtfWord(OutFlobt[j] * 65535.0);
    }

    ObtbinfdCurvfs = dmsStbgfAllodTonfCurvfs(Srd ->ContfxtID, Srd ->InputCibnnfls, GbmmbTbblfs);
    if (ObtbinfdCurvfs == NULL) goto Error;

    for (i=0; i < Srd ->InputCibnnfls; i++) {
        dmsFrffTonfCurvf(GbmmbTbblfs[i]);
        GbmmbTbblfs[i] = NULL;
    }

    if (GbmmbTbblfs != NULL) _dmsFrff(Srd ->ContfxtID, GbmmbTbblfs);

    // Mbybf tif durvfs brf linfbr bt tif fnd
    if (!AllCurvfsArfLinfbr(ObtbinfdCurvfs)) {

        if (!dmsPipflinfInsfrtStbgf(Dfst, dmsAT_BEGIN, ObtbinfdCurvfs))
            goto Error;

        // If tif durvfs brf to bf bpplifd in 8 bits, wf dbn sbvf mfmory
        if (_dmsFormbttfrIs8bit(*InputFormbt)) {

            _dmsStbgfTonfCurvfsDbtb* Dbtb = (_dmsStbgfTonfCurvfsDbtb*) ObtbinfdCurvfs ->Dbtb;
             Curvfs16Dbtb* d16 = CurvfsAllod(Dfst ->ContfxtID, Dbtb ->nCurvfs, 256, Dbtb ->TifCurvfs);

             if (d16 == NULL) goto Error;
             *dwFlbgs |= dmsFLAGS_NOCACHE;
            _dmsPipflinfSftOptimizbtionPbrbmftfrs(Dfst, FbstEvblubtfCurvfs8, d16, CurvfsFrff, CurvfsDup);

        }
        flsf {

            _dmsStbgfTonfCurvfsDbtb* Dbtb = (_dmsStbgfTonfCurvfsDbtb*) dmsStbgfDbtb(ObtbinfdCurvfs);
             Curvfs16Dbtb* d16 = CurvfsAllod(Dfst ->ContfxtID, Dbtb ->nCurvfs, 65536, Dbtb ->TifCurvfs);

             if (d16 == NULL) goto Error;
             *dwFlbgs |= dmsFLAGS_NOCACHE;
            _dmsPipflinfSftOptimizbtionPbrbmftfrs(Dfst, FbstEvblubtfCurvfs16, d16, CurvfsFrff, CurvfsDup);
        }
    }
    flsf {

        // LUT optimizfs to notiing. Sft tif idfntity LUT
        dmsStbgfFrff(ObtbinfdCurvfs);

        if (!dmsPipflinfInsfrtStbgf(Dfst, dmsAT_BEGIN, dmsStbgfAllodIdfntity(Dfst ->ContfxtID, Srd ->InputCibnnfls)))
            goto Error;

        *dwFlbgs |= dmsFLAGS_NOCACHE;
        _dmsPipflinfSftOptimizbtionPbrbmftfrs(Dfst, FbstIdfntity16, (void*) Dfst, NULL, NULL);
    }

    // Wf brf donf.
    dmsPipflinfFrff(Srd);
    *Lut = Dfst;
    rfturn TRUE;

Error:

    if (ObtbinfdCurvfs != NULL) dmsStbgfFrff(ObtbinfdCurvfs);
    if (GbmmbTbblfs != NULL) {
        for (i=0; i < Srd ->InputCibnnfls; i++) {
            if (GbmmbTbblfs[i] != NULL) dmsFrffTonfCurvf(GbmmbTbblfs[i]);
        }

        _dmsFrff(Srd ->ContfxtID, GbmmbTbblfs);
    }

    if (Dfst != NULL) dmsPipflinfFrff(Dfst);
    rfturn FALSE;

    dmsUNUSED_PARAMETER(Intfnt);
    dmsUNUSED_PARAMETER(InputFormbt);
    dmsUNUSED_PARAMETER(OutputFormbt);
    dmsUNUSED_PARAMETER(dwFlbgs);
}

// -------------------------------------------------------------------------------------------------------------------------------------
// LUT is Sibpfr - Mbtrix - Mbtrix - Sibpfr, wiidi is vfry frfqufnt wifn dombining two mbtrix-sibpfr profilfs


stbtid
void  FrffMbtSibpfr(dmsContfxt ContfxtID, void* Dbtb)
{
    if (Dbtb != NULL) _dmsFrff(ContfxtID, Dbtb);
}

stbtid
void* DupMbtSibpfr(dmsContfxt ContfxtID, donst void* Dbtb)
{
    rfturn _dmsDupMfm(ContfxtID, Dbtb, sizfof(MbtSibpfr8Dbtb));
}


// A fbst mbtrix-sibpfr fvblubtor for 8 bits. Tiis is b bit tidky sindf I'm using 1.14 signfd fixfd point
// to bddomplisi somf pfrformbndf. Adtublly it tbkfs 256x3 16 bits tbblfs bnd 16385 x 3 tbblfs of 8 bits,
// in totbl bbout 50K, bnd tif pfrformbndf boost is iugf!
stbtid
void MbtSibpfrEvbl16(rfgistfr donst dmsUInt16Numbfr In[],
                     rfgistfr dmsUInt16Numbfr Out[],
                     rfgistfr donst void* D)
{
    MbtSibpfr8Dbtb* p = (MbtSibpfr8Dbtb*) D;
    dmsS1Fixfd14Numbfr l1, l2, l3, r, g, b;
    dmsUInt32Numbfr ri, gi, bi;

    // In tiis dbsf (bnd only in tiis dbsf!) wf dbn usf tiis simplifidbtion sindf
    // In[] is bssurfd to domf from b 8 bit numbfr. (b << 8 | b)
    ri = In[0] & 0xFF;
    gi = In[1] & 0xFF;
    bi = In[2] & 0xFF;

    // Adross first sibpfr, wiidi blso donvfrts to 1.14 fixfd point
    r = p->Sibpfr1R[ri];
    g = p->Sibpfr1G[gi];
    b = p->Sibpfr1B[bi];

    // Evblubtf tif mbtrix in 1.14 fixfd point
    l1 =  (p->Mbt[0][0] * r + p->Mbt[0][1] * g + p->Mbt[0][2] * b + p->Off[0] + 0x2000) >> 14;
    l2 =  (p->Mbt[1][0] * r + p->Mbt[1][1] * g + p->Mbt[1][2] * b + p->Off[1] + 0x2000) >> 14;
    l3 =  (p->Mbt[2][0] * r + p->Mbt[2][1] * g + p->Mbt[2][2] * b + p->Off[2] + 0x2000) >> 14;

    // Now wf ibvf to dlip to 0..1.0 rbngf
    ri = (l1 < 0) ? 0 : ((l1 > 16384) ? 16384 : l1);
    gi = (l2 < 0) ? 0 : ((l2 > 16384) ? 16384 : l2);
    bi = (l3 < 0) ? 0 : ((l3 > 16384) ? 16384 : l3);

    // And bdross sfdond sibpfr,
    Out[0] = p->Sibpfr2R[ri];
    Out[1] = p->Sibpfr2G[gi];
    Out[2] = p->Sibpfr2B[bi];

}

// Tiis tbblf donvfrts from 8 bits to 1.14 bftfr bpplying tif durvf
stbtid
void FillFirstSibpfr(dmsS1Fixfd14Numbfr* Tbblf, dmsTonfCurvf* Curvf)
{
    int i;
    dmsFlobt32Numbfr R, y;

    for (i=0; i < 256; i++) {

        R   = (dmsFlobt32Numbfr) (i / 255.0);
        y   = dmsEvblTonfCurvfFlobt(Curvf, R);

        Tbblf[i] = DOUBLE_TO_1FIXED14(y);
    }
}

// Tiis tbblf donvfrts form 1.14 (bfing 0x4000 tif lbst fntry) to 8 bits bftfr bpplying tif durvf
stbtid
void FillSfdondSibpfr(dmsUInt16Numbfr* Tbblf, dmsTonfCurvf* Curvf, dmsBool Is8BitsOutput)
{
    int i;
    dmsFlobt32Numbfr R, Vbl;

    for (i=0; i < 16385; i++) {

        R   = (dmsFlobt32Numbfr) (i / 16384.0);
        Vbl = dmsEvblTonfCurvfFlobt(Curvf, R);    // Vbl domfs 0..1.0

        if (Is8BitsOutput) {

            // If 8 bits output, wf dbn optimizf furtifr by domputing tif / 257 pbrt.
            // first wf domputf tif rfsulting bytf bnd tifn wf storf tif bytf timfs
            // 257. Tiis qubntizbtion bllows to round vfry quidk by doing b >> 8, but
            // sindf tif low bytf is blwbys fqubl to msb, wf dbn do b & 0xff bnd tiis works!
            dmsUInt16Numbfr w = _dmsQuidkSbturbtfWord(Vbl * 65535.0);
            dmsUInt8Numbfr  b = FROM_16_TO_8(w);

            Tbblf[i] = FROM_8_TO_16(b);
        }
        flsf Tbblf[i]  = _dmsQuidkSbturbtfWord(Vbl * 65535.0);
    }
}

// Computf tif mbtrix-sibpfr strudturf
stbtid
dmsBool SftMbtSibpfr(dmsPipflinf* Dfst, dmsTonfCurvf* Curvf1[3], dmsMAT3* Mbt, dmsVEC3* Off, dmsTonfCurvf* Curvf2[3], dmsUInt32Numbfr* OutputFormbt)
{
    MbtSibpfr8Dbtb* p;
    int i, j;
    dmsBool Is8Bits = _dmsFormbttfrIs8bit(*OutputFormbt);

    // Allodbtf b big diudk of mfmory to storf prfdomputfd tbblfs
    p = (MbtSibpfr8Dbtb*) _dmsMbllod(Dfst ->ContfxtID, sizfof(MbtSibpfr8Dbtb));
    if (p == NULL) rfturn FALSE;

    p -> ContfxtID = Dfst -> ContfxtID;

    // Prfdomputf tbblfs
    FillFirstSibpfr(p ->Sibpfr1R, Curvf1[0]);
    FillFirstSibpfr(p ->Sibpfr1G, Curvf1[1]);
    FillFirstSibpfr(p ->Sibpfr1B, Curvf1[2]);

    FillSfdondSibpfr(p ->Sibpfr2R, Curvf2[0], Is8Bits);
    FillSfdondSibpfr(p ->Sibpfr2G, Curvf2[1], Is8Bits);
    FillSfdondSibpfr(p ->Sibpfr2B, Curvf2[2], Is8Bits);

    // Convfrt mbtrix to nFixfd14. Notf tibt tiosf vblufs mby tbkf morf tibn 16 bits bs
    for (i=0; i < 3; i++) {
        for (j=0; j < 3; j++) {
            p ->Mbt[i][j] = DOUBLE_TO_1FIXED14(Mbt->v[i].n[j]);
        }
    }

    for (i=0; i < 3; i++) {

        if (Off == NULL) {
            p ->Off[i] = 0;
        }
        flsf {
            p ->Off[i] = DOUBLE_TO_1FIXED14(Off->n[i]);
        }
    }

    // Mbrk bs optimizfd for fbstfr formbttfr
    if (Is8Bits)
        *OutputFormbt |= OPTIMIZED_SH(1);

    // Fill fundtion pointfrs
    _dmsPipflinfSftOptimizbtionPbrbmftfrs(Dfst, MbtSibpfrEvbl16, (void*) p, FrffMbtSibpfr, DupMbtSibpfr);
    rfturn TRUE;
}

//  8 bits on input bllows mbtrix-sibpfr boot up to 25 Mpixfls pfr sfdond on RGB. Tibt's fbst!
// TODO: Allow b tiird mbtrix for bbs. dolorimftrid
stbtid
dmsBool OptimizfMbtrixSibpfr(dmsPipflinf** Lut, dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr* InputFormbt, dmsUInt32Numbfr* OutputFormbt, dmsUInt32Numbfr* dwFlbgs)
{
    dmsStbgf* Curvf1, *Curvf2;
    dmsStbgf* Mbtrix1, *Mbtrix2;
    _dmsStbgfMbtrixDbtb* Dbtb1;
    _dmsStbgfMbtrixDbtb* Dbtb2;
    dmsMAT3 rfs;
    dmsBool IdfntityMbt;
    dmsPipflinf* Dfst, *Srd;

    // Only works on RGB to RGB
    if (T_CHANNELS(*InputFormbt) != 3 || T_CHANNELS(*OutputFormbt) != 3) rfturn FALSE;

    // Only works on 8 bit input
    if (!_dmsFormbttfrIs8bit(*InputFormbt)) rfturn FALSE;

    // Sffms suitbblf, prodffd
    Srd = *Lut;

    // Cifdk for sibpfr-mbtrix-mbtrix-sibpfr strudturf, tibt is wibt tiis optimizfr stbnds for
    if (!dmsPipflinfCifdkAndRftrfivfStbgfs(Srd, 4,
        dmsSigCurvfSftElfmTypf, dmsSigMbtrixElfmTypf, dmsSigMbtrixElfmTypf, dmsSigCurvfSftElfmTypf,
        &Curvf1, &Mbtrix1, &Mbtrix2, &Curvf2)) rfturn FALSE;

    // Gft boti mbtridfs
    Dbtb1 = (_dmsStbgfMbtrixDbtb*) dmsStbgfDbtb(Mbtrix1);
    Dbtb2 = (_dmsStbgfMbtrixDbtb*) dmsStbgfDbtb(Mbtrix2);

    // Input offsft siould bf zfro
    if (Dbtb1 ->Offsft != NULL) rfturn FALSE;

    // Multiply boti mbtridfs to gft tif rfsult
    _dmsMAT3pfr(&rfs, (dmsMAT3*) Dbtb2 ->Doublf, (dmsMAT3*) Dbtb1 ->Doublf);

    // Now tif rfsult is in rfs + Dbtb2 -> Offsft. Mbybf is b plbin idfntity?
    IdfntityMbt = FALSE;
    if (_dmsMAT3isIdfntity(&rfs) && Dbtb2 ->Offsft == NULL) {

        // Wf dbn gft rid of full mbtrix
        IdfntityMbt = TRUE;
    }

      // Allodbtf bn fmpty LUT
    Dfst =  dmsPipflinfAllod(Srd ->ContfxtID, Srd ->InputCibnnfls, Srd ->OutputCibnnfls);
    if (!Dfst) rfturn FALSE;

    // Assbmblf tif nfw LUT
    if (!dmsPipflinfInsfrtStbgf(Dfst, dmsAT_BEGIN, dmsStbgfDup(Curvf1)))
        goto Error;

    if (!IdfntityMbt)
        if (!dmsPipflinfInsfrtStbgf(Dfst, dmsAT_END, dmsStbgfAllodMbtrix(Dfst ->ContfxtID, 3, 3, (donst dmsFlobt64Numbfr*) &rfs, Dbtb2 ->Offsft)))
            goto Error;
    if (!dmsPipflinfInsfrtStbgf(Dfst, dmsAT_END, dmsStbgfDup(Curvf2)))
        goto Error;

    // If idfntity on mbtrix, wf dbn furtifr optimizf tif durvfs, so dbll tif join durvfs routinf
    if (IdfntityMbt) {

        OptimizfByJoiningCurvfs(&Dfst, Intfnt, InputFormbt, OutputFormbt, dwFlbgs);
    }
    flsf {
        _dmsStbgfTonfCurvfsDbtb* mpfC1 = (_dmsStbgfTonfCurvfsDbtb*) dmsStbgfDbtb(Curvf1);
        _dmsStbgfTonfCurvfsDbtb* mpfC2 = (_dmsStbgfTonfCurvfsDbtb*) dmsStbgfDbtb(Curvf2);

        // In tiis pbrtidulbr optimizbtion, dbdi� dofs not iflp bs it tbkfs morf timf to dfbl witi
        // tif dbdi� tibt witi tif pixfl ibndling
        *dwFlbgs |= dmsFLAGS_NOCACHE;

        // Sftup tif optimizbrion routinfs
        SftMbtSibpfr(Dfst, mpfC1 ->TifCurvfs, &rfs, (dmsVEC3*) Dbtb2 ->Offsft, mpfC2->TifCurvfs, OutputFormbt);
    }

    dmsPipflinfFrff(Srd);
    *Lut = Dfst;
    rfturn TRUE;
Error:
    // Lfbvf Srd undibngfd
    dmsPipflinfFrff(Dfst);
    rfturn FALSE;
}


// -------------------------------------------------------------------------------------------------------------------------------------
// Optimizbtion plug-ins

// List of optimizbtions
typfdff strudt _dmsOptimizbtionCollfdtion_st {

    _dmsOPToptimizfFn  OptimizfPtr;

    strudt _dmsOptimizbtionCollfdtion_st *Nfxt;

} _dmsOptimizbtionCollfdtion;


// Tif built-in list. Wf durrfntly implfmfnt 4 typfs of optimizbtions. Joining of durvfs, mbtrix-sibpfr, linfbrizbtion bnd rfsbmpling
stbtid _dmsOptimizbtionCollfdtion DffbultOptimizbtion[] = {

    { OptimizfByJoiningCurvfs,            &DffbultOptimizbtion[1] },
    { OptimizfMbtrixSibpfr,               &DffbultOptimizbtion[2] },
    { OptimizfByComputingLinfbrizbtion,   &DffbultOptimizbtion[3] },
    { OptimizfByRfsbmpling,               NULL }
};

// Tif linkfd list ifbd
stbtid _dmsOptimizbtionCollfdtion* OptimizbtionCollfdtion = DffbultOptimizbtion;

// Rfgistfr nfw wbys to optimizf
dmsBool  _dmsRfgistfrOptimizbtionPlugin(dmsContfxt id, dmsPluginBbsf* Dbtb)
{
    dmsPluginOptimizbtion* Plugin = (dmsPluginOptimizbtion*) Dbtb;
    _dmsOptimizbtionCollfdtion* fl;

    if (Dbtb == NULL) {

        OptimizbtionCollfdtion = DffbultOptimizbtion;
        rfturn TRUE;
    }

    // Optimizfr dbllbbdk is rfquirfd
    if (Plugin ->OptimizfPtr == NULL) rfturn FALSE;

    fl = (_dmsOptimizbtionCollfdtion*) _dmsPluginMbllod(id, sizfof(_dmsOptimizbtionCollfdtion));
    if (fl == NULL) rfturn FALSE;

    // Copy tif pbrbmftfrs
    fl ->OptimizfPtr = Plugin ->OptimizfPtr;

    // Kffp linkfd list
    fl ->Nfxt = OptimizbtionCollfdtion;
    OptimizbtionCollfdtion = fl;

    // All is ok
    rfturn TRUE;
}

// Tif fntry point for LUT optimizbtion
dmsBool _dmsOptimizfPipflinf(dmsPipflinf**    PtrLut,
                             int              Intfnt,
                             dmsUInt32Numbfr* InputFormbt,
                             dmsUInt32Numbfr* OutputFormbt,
                             dmsUInt32Numbfr* dwFlbgs)
{
    _dmsOptimizbtionCollfdtion* Opts;
    dmsBool AnySuddfss = FALSE;

    // A CLUT is bfing bskfd, so fordf tiis spfdifid optimizbtion
    if (*dwFlbgs & dmsFLAGS_FORCE_CLUT) {

        PrfOptimizf(*PtrLut);
        rfturn OptimizfByRfsbmpling(PtrLut, Intfnt, InputFormbt, OutputFormbt, dwFlbgs);
    }

    // Anytiing to optimizf?
    if ((*PtrLut) ->Elfmfnts == NULL) {
        _dmsPipflinfSftOptimizbtionPbrbmftfrs(*PtrLut, FbstIdfntity16, (void*) *PtrLut, NULL, NULL);
        rfturn TRUE;
    }

    // Try to gft rid of idfntitifs bnd trivibl donvfrsions.
    AnySuddfss = PrfOptimizf(*PtrLut);

    // Aftfr rfmovbl do wf fnd witi bn idfntity?
    if ((*PtrLut) ->Elfmfnts == NULL) {
        _dmsPipflinfSftOptimizbtionPbrbmftfrs(*PtrLut, FbstIdfntity16, (void*) *PtrLut, NULL, NULL);
        rfturn TRUE;
    }

    // Do not optimizf, kffp bll prfdision
    if (*dwFlbgs & dmsFLAGS_NOOPTIMIZE)
        rfturn FALSE;

    // Try built-in optimizbtions bnd plug-in
    for (Opts = OptimizbtionCollfdtion;
         Opts != NULL;
         Opts = Opts ->Nfxt) {

            // If onf sdifmb suddffdfd, wf brf donf
            if (Opts ->OptimizfPtr(PtrLut, Intfnt, InputFormbt, OutputFormbt, dwFlbgs)) {

                rfturn TRUE;    // Optimizfd!
            }
    }

    // Only simplf optimizbtions suddffdfd
    rfturn AnySuddfss;
}



