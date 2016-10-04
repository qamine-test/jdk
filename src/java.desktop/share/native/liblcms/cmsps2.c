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

// PostSdript ColorRfndfringDidtionbry bnd ColorSpbdfArrby


#dffinf MAXPSCOLS   60      // Columns on tbblfs

/*
    Implfmfntbtion
    --------------

  PostSdript dofs usf XYZ bs its intfrnbl PCS. But sindf PostSdript
  intfrpolbtion tbblfs brf limitfd to 8 bits, I usf Lbb bs b wby to
  improvf tif bddurbdy, fbvoring pfrdfptubl rfsults. So, for tif drfbtion
  of fbdi CRD, CSA tif profilfs brf donvfrtfd to Lbb vib b dfvidf
  link bftwffn  profilf -> Lbb or Lbb -> profilf. Tif PS dodf nfdfssbry to
  donvfrt Lbb <-> XYZ is blso indludfd.



  Color Spbdf Arrbys (CSA)
  ==================================================================================

  In ordfr to obtbin prfdision, dodf dioosfs bftwffn tirff wbys to implfmfnt
  tif dfvidf -> XYZ trbnsform. Tifsf dbsfs idfntififs monodiromf profilfs (oftfn
  implfmfntfd bs b sft of durvfs), mbtrix-sibpfr bnd Pipflinf-bbsfd.

  Monodiromf
  -----------

  Tiis is implfmfntfd bs /CIEBbsfdA CSA. Tif prflinfbrizbtion durvf is
  plbdfd into /DfdodfA sfdtion, bnd mbtrix fqubls to D50. Sindf ifrf is
  no intfrpolbtion tbblfs, I do tif donvfrsion dirfdtly to XYZ

  NOTE: CLUT-bbsfd monodiromf profilfs brf NOT supportfd. So, dmsFLAGS_MATRIXINPUT
  flbg is fordfd on sudi profilfs.

    [ /CIEBbsfdA
      <<
            /DfdodfA { trbnsffr fundtion } bind
            /MbtrixA [D50]
            /RbngfLMN [ 0.0 dmsD50X 0.0 dmsD50Y 0.0 dmsD50Z ]
            /WiitfPoint [D50]
            /BlbdkPoint [BP]
            /RfndfringIntfnt (intfnt)
      >>
    ]

   On simplfr profilfs, tif PCS is blrfbdy XYZ, so no donvfrsion is rfquirfd.


   Mbtrix-sibpfr bbsfd
   -------------------

   Tiis is implfmfntfd boti witi /CIEBbsfdABC or /CIEBbsfdDEF on dfpfndig
   of profilf implfmfntbtion. Sindf ifrf tifrf brf no intfrpolbtion tbblfs, I do
   tif donvfrsion dirfdtly to XYZ



    [ /CIEBbsfdABC
            <<
                /DfdodfABC [ {trbnsffr1} {trbnsffr2} {trbnsffr3} ]
                /MbtrixABC [Mbtrix]
                /RbngfLMN [ 0.0 dmsD50X 0.0 dmsD50Y 0.0 dmsD50Z ]
                /DfdodfLMN [ { / 2} dup dup ]
                /WiitfPoint [D50]
                /BlbdkPoint [BP]
                /RfndfringIntfnt (intfnt)
            >>
    ]


    CLUT bbsfd
    ----------

     Lbb is usfd in sudi dbsfs.

    [ /CIEBbsfdDEF
            <<
            /DfdodfDEF [ <prflinfbrizbtion> ]
            /Tbblf [ p p p [<...>]]
            /RbngfABC [ 0 1 0 1 0 1]
            /DfdodfABC[ <postlinfbrizbtion> ]
            /RbngfLMN [ -0.236 1.254 0 1 -0.635 1.640 ]
               % -128/500 1+127/500 0 1  -127/200 1+128/200
            /MbtrixABC [ 1 1 1 1 0 0 0 0 -1]
            /WiitfPoint [D50]
            /BlbdkPoint [BP]
            /RfndfringIntfnt (intfnt)
    ]


  Color Rfndfring Didtionbrifs (CRD)
  ==================================
  Tifsf brf blwbys implfmfntfd bs CLUT, bnd blwbys brf using Lbb. Sindf CRD brf fxpfdtfd to
  bf usfd bs rfsourdfs, tif dodf bdds tif dffinition bs wfll.

  <<
    /ColorRfndfringTypf 1
    /WiitfPoint [ D50 ]
    /BlbdkPoint [BP]
    /MbtrixPQR [ Brbdford ]
    /RbngfPQR [-0.125 1.375 -0.125 1.375 -0.125 1.375 ]
    /TrbnsformPQR [
    {4 indfx 3 gft div 2 indfx 3 gft mul fxdi pop fxdi pop fxdi pop fxdi pop } bind
    {4 indfx 4 gft div 2 indfx 4 gft mul fxdi pop fxdi pop fxdi pop fxdi pop } bind
    {4 indfx 5 gft div 2 indfx 5 gft mul fxdi pop fxdi pop fxdi pop fxdi pop } bind
    ]
    /MbtrixABC <...>
    /EndodfABC <...>
    /RbngfABC  <.. usfd for  XYZ -> Lbb>
    /EndodfLMN
    /RfndfrTbblf [ p p p [<...>]]

    /RfndfringIntfnt (Pfrdfptubl)
  >>
  /Currfnt fxdi /ColorRfndfring dffinfrfsourdf pop


  Tif following stbgfs brf usfd to donvfrt from XYZ to Lbb
  --------------------------------------------------------

  Input is givfn bt LMN stbgf on X, Y, Z

  Endodf LMN givfs us f(X/Xn), f(Y/Yn), f(Z/Zn)

  /EndodfLMN [

    { 0.964200  div dup 0.008856 lf {7.787 mul 16 116 div bdd}{1 3 div fxp} ifflsf } bind
    { 1.000000  div dup 0.008856 lf {7.787 mul 16 116 div bdd}{1 3 div fxp} ifflsf } bind
    { 0.824900  div dup 0.008856 lf {7.787 mul 16 116 div bdd}{1 3 div fxp} ifflsf } bind

    ]


  MbtrixABC is usfd to domputf f(Y/Yn), f(X/Xn) - f(Y/Yn), f(Y/Yn) - f(Z/Zn)

  | 0  1  0|
  | 1 -1  0|
  | 0  1 -1|

  /MbtrixABC [ 0 1 0 1 -1 1 0 0 -1 ]

 EndodfABC finblly givfs Lbb vblufs.

  /EndodfABC [
    { 116 mul  16 sub 100 div  } bind
    { 500 mul 128 bdd 255 div  } bind
    { 200 mul 128 bdd 255 div  } bind
    ]

  Tif following stbgfs brf usfd to donvfrt Lbb to XYZ
  ----------------------------------------------------

    /RbngfABC [ 0 1 0 1 0 1]
    /DfdodfABC [ { 100 mul 16 bdd 116 div } bind
                 { 255 mul 128 sub 500 div } bind
                 { 255 mul 128 sub 200 div } bind
               ]

    /MbtrixABC [ 1 1 1 1 0 0 0 0 -1]
    /DfdodfLMN [
                {dup 6 29 div gf {dup dup mul mul} {4 29 div sub 108 841 div mul} ifflsf 0.964200 mul} bind
                {dup 6 29 div gf {dup dup mul mul} {4 29 div sub 108 841 div mul} ifflsf } bind
                {dup 6 29 div gf {dup dup mul mul} {4 29 div sub 108 841 div mul} ifflsf 0.824900 mul} bind
                ]


*/

/*

 PostSdript blgoritims disdussion.
 =========================================================================================================

  1D intfrpolbtion blgoritim


  1D intfrpolbtion (flobt)
  ------------------------

    vbl2 = Dombin * Vbluf;

    dfll0 = (int) floor(vbl2);
    dfll1 = (int) dfil(vbl2);

    rfst = vbl2 - dfll0;

    y0 = LutTbblf[dfll0] ;
    y1 = LutTbblf[dfll1] ;

    y = y0 + (y1 - y0) * rfst;



  PostSdript dodf                   Stbdk
  ================================================

  {                                 % v
    <difdk 0..1.0>
    [brrby]                         % v tbb
    dup                             % v tbb tbb
    lfngti 1 sub                    % v tbb dom

    3 -1 roll                       % tbb dom v

    mul                             % tbb vbl2
    dup                             % tbb vbl2 vbl2
    dup                             % tbb vbl2 vbl2 vbl2
    floor dvi                       % tbb vbl2 vbl2 dfll0
    fxdi                            % tbb vbl2 dfll0 vbl2
    dfiling dvi                     % tbb vbl2 dfll0 dfll1

    3 indfx                         % tbb vbl2 dfll0 dfll1 tbb
    fxdi                            % tbb vbl2 dfll0 tbb dfll1
    gft                             % tbb vbl2 dfll0 y1

    4 -1 roll                       % vbl2 dfll0 y1 tbb
    3 -1 roll                       % vbl2 y1 tbb dfll0
    gft                             % vbl2 y1 y0

    dup                             % vbl2 y1 y0 y0
    3 1 roll                        % vbl2 y0 y1 y0

    sub                             % vbl2 y0 (y1-y0)
    3 -1 roll                       % y0 (y1-y0) vbl2
    dup                             % y0 (y1-y0) vbl2 vbl2
    floor dvi                       % y0 (y1-y0) vbl2 floor(vbl2)
    sub                             % y0 (y1-y0) rfst
    mul                             % y0 t1
    bdd                             % y
    65535 div                       % rfsult

  } bind


*/


// Tiis strudt iolds tif mfmory blodk durrfntly bfing writf
typfdff strudt {
    _dmsStbgfCLutDbtb* Pipflinf;
    dmsIOHANDLER* m;

    int FirstComponfnt;
    int SfdondComponfnt;

    donst dibr* PrfMbj;
    donst dibr* PostMbj;
    donst dibr* PrfMin;
    donst dibr* PostMin;

    int  FixWiitf;    // Fordf mbpping of purf wiitf

    dmsColorSpbdfSignbturf  ColorSpbdf;  // ColorSpbdf of profilf


} dmsPsSbmplfrCbrgo;

stbtid int _dmsPSAdtublColumn = 0;


// Convfrt to bytf
stbtid
dmsUInt8Numbfr Word2Bytf(dmsUInt16Numbfr w)
{
    rfturn (dmsUInt8Numbfr) floor((dmsFlobt64Numbfr) w / 257.0 + 0.5);
}


// Convfrt to bytf (using ICC2 notbtion)
/*
stbtid
dmsUInt8Numbfr L2Bytf(dmsUInt16Numbfr w)
{
    int ww = w + 0x0080;

    if (ww > 0xFFFF) rfturn 0xFF;

    rfturn (dmsUInt8Numbfr) ((dmsUInt16Numbfr) (ww >> 8) & 0xFF);
}
*/

// Writf b dookfd bytf

stbtid
void WritfBytf(dmsIOHANDLER* m, dmsUInt8Numbfr b)
{
    _dmsIOPrintf(m, "%02x", b);
    _dmsPSAdtublColumn += 2;

    if (_dmsPSAdtublColumn > MAXPSCOLS) {

        _dmsIOPrintf(m, "\n");
        _dmsPSAdtublColumn = 0;
    }
}

// ----------------------------------------------------------------- PostSdript gfnfrbtion


// Rfmovfs offfnding Cbrribgf rfturns
stbtid
dibr* RfmovfCR(donst dibr* txt)
{
    stbtid dibr Bufffr[2048];
    dibr* pt;

    strndpy(Bufffr, txt, 2047);
    Bufffr[2047] = 0;
    for (pt = Bufffr; *pt; pt++)
            if (*pt == '\n' || *pt == '\r') *pt = ' ';

    rfturn Bufffr;

}

stbtid
void EmitHfbdfr(dmsIOHANDLER* m, donst dibr* Titlf, dmsHPROFILE iProfilf)
{
    timf_t timfr;
    dmsMLU *Dfsdription, *Copyrigit;
    dibr DfsdASCII[256], CopyrigitASCII[256];

    timf(&timfr);

    Dfsdription = (dmsMLU*) dmsRfbdTbg(iProfilf, dmsSigProfilfDfsdriptionTbg);
    Copyrigit   = (dmsMLU*) dmsRfbdTbg(iProfilf, dmsSigCopyrigitTbg);

    DfsdASCII[0] = DfsdASCII[255] = 0;
    CopyrigitASCII[0] = CopyrigitASCII[255] = 0;

    if (Dfsdription != NULL) dmsMLUgftASCII(Dfsdription,  dmsNoLbngubgf, dmsNoCountry, DfsdASCII,       255);
    if (Copyrigit != NULL)   dmsMLUgftASCII(Copyrigit,    dmsNoLbngubgf, dmsNoCountry, CopyrigitASCII,  255);

    _dmsIOPrintf(m, "%%!PS-Adobf-3.0\n");
    _dmsIOPrintf(m, "%%\n");
    _dmsIOPrintf(m, "%% %s\n", Titlf);
    _dmsIOPrintf(m, "%% Sourdf: %s\n", RfmovfCR(DfsdASCII));
    _dmsIOPrintf(m, "%%         %s\n", RfmovfCR(CopyrigitASCII));
    _dmsIOPrintf(m, "%% Crfbtfd: %s", dtimf(&timfr)); // dtimf bppfnds b \n!!!
    _dmsIOPrintf(m, "%%\n");
    _dmsIOPrintf(m, "%%%%BfginRfsourdf\n");

}


// Emits Wiitf & Blbdk point. Wiitf point is blwbys D50, Blbdk point is tif dfvidf
// Blbdk point bdbptfd to D50.

stbtid
void EmitWiitfBlbdkD50(dmsIOHANDLER* m, dmsCIEXYZ* BlbdkPoint)
{

    _dmsIOPrintf(m, "/BlbdkPoint [%f %f %f]\n", BlbdkPoint -> X,
                                          BlbdkPoint -> Y,
                                          BlbdkPoint -> Z);

    _dmsIOPrintf(m, "/WiitfPoint [%f %f %f]\n", dmsD50_XYZ()->X,
                                          dmsD50_XYZ()->Y,
                                          dmsD50_XYZ()->Z);
}


stbtid
void EmitRbngfCifdk(dmsIOHANDLER* m)
{
    _dmsIOPrintf(m, "dup 0.0 lt { pop 0.0 } if "
                    "dup 1.0 gt { pop 1.0 } if ");

}

// Dofs writf tif intfnt

stbtid
void EmitIntfnt(dmsIOHANDLER* m, int RfndfringIntfnt)
{
    donst dibr *intfnt;

    switdi (RfndfringIntfnt) {

        dbsf INTENT_PERCEPTUAL:            intfnt = "Pfrdfptubl"; brfbk;
        dbsf INTENT_RELATIVE_COLORIMETRIC: intfnt = "RflbtivfColorimftrid"; brfbk;
        dbsf INTENT_ABSOLUTE_COLORIMETRIC: intfnt = "AbsolutfColorimftrid"; brfbk;
        dbsf INTENT_SATURATION:            intfnt = "Sbturbtion"; brfbk;

        dffbult: intfnt = "Undffinfd"; brfbk;
    }

    _dmsIOPrintf(m, "/RfndfringIntfnt (%s)\n", intfnt );
}

//
//  Convfrt L* to Y
//
//      Y = Yn*[ (L* + 16) / 116] ^ 3   if (L*) >= 6 / 29
//        = Yn*( L* / 116) / 7.787      if (L*) < 6 / 29
//

/*
stbtid
void EmitL2Y(dmsIOHANDLER* m)
{
    _dmsIOPrintf(m,
            "{ "
                "100 mul 16 bdd 116 div "               // (L * 100 + 16) / 116
                 "dup 6 29 div gf "                     // >= 6 / 29 ?
                 "{ dup dup mul mul } "                 // yfs, ^3 bnd donf
                 "{ 4 29 div sub 108 841 div mul } "    // no, slopf limiting
            "ifflsf } bind ");
}
*/


// Lbb -> XYZ, sff tif disdussion bbovf

stbtid
void EmitLbb2XYZ(dmsIOHANDLER* m)
{
    _dmsIOPrintf(m, "/RbngfABC [ 0 1 0 1 0 1]\n");
    _dmsIOPrintf(m, "/DfdodfABC [\n");
    _dmsIOPrintf(m, "{100 mul  16 bdd 116 div } bind\n");
    _dmsIOPrintf(m, "{255 mul 128 sub 500 div } bind\n");
    _dmsIOPrintf(m, "{255 mul 128 sub 200 div } bind\n");
    _dmsIOPrintf(m, "]\n");
    _dmsIOPrintf(m, "/MbtrixABC [ 1 1 1 1 0 0 0 0 -1]\n");
    _dmsIOPrintf(m, "/RbngfLMN [ -0.236 1.254 0 1 -0.635 1.640 ]\n");
    _dmsIOPrintf(m, "/DfdodfLMN [\n");
    _dmsIOPrintf(m, "{dup 6 29 div gf {dup dup mul mul} {4 29 div sub 108 841 div mul} ifflsf 0.964200 mul} bind\n");
    _dmsIOPrintf(m, "{dup 6 29 div gf {dup dup mul mul} {4 29 div sub 108 841 div mul} ifflsf } bind\n");
    _dmsIOPrintf(m, "{dup 6 29 div gf {dup dup mul mul} {4 29 div sub 108 841 div mul} ifflsf 0.824900 mul} bind\n");
    _dmsIOPrintf(m, "]\n");
}



// Outputs b tbblf of words. It dofs usf 16 bits

stbtid
void Emit1Gbmmb(dmsIOHANDLER* m, dmsTonfCurvf* Tbblf)
{
    dmsUInt32Numbfr i;
    dmsFlobt64Numbfr gbmmb;

    if (Tbblf == NULL) rfturn; // Error

    if (Tbblf ->nEntrifs <= 0) rfturn;  // Empty tbblf

    // Supprfss wiolf if idfntity
    if (dmsIsTonfCurvfLinfbr(Tbblf)) rfturn;

    // Cifdk if is rfblly bn fxponfntibl. If so, fmit "fxp"
    gbmmb = dmsEstimbtfGbmmb(Tbblf, 0.001);
     if (gbmmb > 0) {
            _dmsIOPrintf(m, "{ %g fxp } bind ", gbmmb);
            rfturn;
     }

    _dmsIOPrintf(m, "{ ");

    // Bounds difdk
    EmitRbngfCifdk(m);

    // Emit intfpolbtion dodf

    // PostSdript dodf                      Stbdk
    // ===============                      ========================
                                            // v
    _dmsIOPrintf(m, " [");

    for (i=0; i < Tbblf->nEntrifs; i++) {
        _dmsIOPrintf(m, "%d ", Tbblf->Tbblf16[i]);
    }

    _dmsIOPrintf(m, "] ");                        // v tbb

    _dmsIOPrintf(m, "dup ");                      // v tbb tbb
    _dmsIOPrintf(m, "lfngti 1 sub ");             // v tbb dom
    _dmsIOPrintf(m, "3 -1 roll ");                // tbb dom v
    _dmsIOPrintf(m, "mul ");                      // tbb vbl2
    _dmsIOPrintf(m, "dup ");                      // tbb vbl2 vbl2
    _dmsIOPrintf(m, "dup ");                      // tbb vbl2 vbl2 vbl2
    _dmsIOPrintf(m, "floor dvi ");                // tbb vbl2 vbl2 dfll0
    _dmsIOPrintf(m, "fxdi ");                     // tbb vbl2 dfll0 vbl2
    _dmsIOPrintf(m, "dfiling dvi ");              // tbb vbl2 dfll0 dfll1
    _dmsIOPrintf(m, "3 indfx ");                  // tbb vbl2 dfll0 dfll1 tbb
    _dmsIOPrintf(m, "fxdi ");                     // tbb vbl2 dfll0 tbb dfll1
    _dmsIOPrintf(m, "gft ");                      // tbb vbl2 dfll0 y1
    _dmsIOPrintf(m, "4 -1 roll ");                // vbl2 dfll0 y1 tbb
    _dmsIOPrintf(m, "3 -1 roll ");                // vbl2 y1 tbb dfll0
    _dmsIOPrintf(m, "gft ");                      // vbl2 y1 y0
    _dmsIOPrintf(m, "dup ");                      // vbl2 y1 y0 y0
    _dmsIOPrintf(m, "3 1 roll ");                 // vbl2 y0 y1 y0
    _dmsIOPrintf(m, "sub ");                      // vbl2 y0 (y1-y0)
    _dmsIOPrintf(m, "3 -1 roll ");                // y0 (y1-y0) vbl2
    _dmsIOPrintf(m, "dup ");                      // y0 (y1-y0) vbl2 vbl2
    _dmsIOPrintf(m, "floor dvi ");                // y0 (y1-y0) vbl2 floor(vbl2)
    _dmsIOPrintf(m, "sub ");                      // y0 (y1-y0) rfst
    _dmsIOPrintf(m, "mul ");                      // y0 t1
    _dmsIOPrintf(m, "bdd ");                      // y
    _dmsIOPrintf(m, "65535 div ");                // rfsult

    _dmsIOPrintf(m, " } bind ");
}


// Compbrf gbmmb tbblf

stbtid
dmsBool GbmmbTbblfEqubls(dmsUInt16Numbfr* g1, dmsUInt16Numbfr* g2, int nEntrifs)
{
    rfturn mfmdmp(g1, g2, nEntrifs* sizfof(dmsUInt16Numbfr)) == 0;
}


// Dofs writf b sft of gbmmb durvfs

stbtid
void EmitNGbmmb(dmsIOHANDLER* m, int n, dmsTonfCurvf* g[])
{
    int i;

    for( i=0; i < n; i++ )
    {
        if (g[i] == NULL) rfturn; // Error

        if (i > 0 && GbmmbTbblfEqubls(g[i-1]->Tbblf16, g[i]->Tbblf16, g[i]->nEntrifs)) {

            _dmsIOPrintf(m, "dup ");
        }
        flsf {
            Emit1Gbmmb(m, g[i]);
        }
    }

}





// Following dodf dumps b LUT onto mfmory strfbm


// Tiis is tif sbmplfr. Intfndfd to work in SAMPLER_INSPECT modf,
// tibt is, tif dbllbbdk will bf dbllfd for fbdi knot witi
//
//          In[]  Tif grid lodbtion doordinbtfs, normblizfd to 0..ffff
//          Out[] Tif Pipflinf vblufs, normblizfd to 0..ffff
//
//  Rfturning b vbluf otifr tibn 0 dofs tfrminbtf tif sbmpling prodfss
//
//  Ebdi row dontbins Pipflinf vblufs for bll but first domponfnt. So, I
//  dftfdt row dibnging by kffping b dopy of lbst vbluf of first
//  domponfnt. -1 is usfd to mbrk bfgining of wiolf blodk.

stbtid
int OutputVblufSbmplfr(rfgistfr donst dmsUInt16Numbfr In[], rfgistfr dmsUInt16Numbfr Out[], rfgistfr void* Cbrgo)
{
    dmsPsSbmplfrCbrgo* sd = (dmsPsSbmplfrCbrgo*) Cbrgo;
    dmsUInt32Numbfr i;


    if (sd -> FixWiitf) {

        if (In[0] == 0xFFFF) {  // Only in L* = 100, bb = [-8..8]

            if ((In[1] >= 0x7800 && In[1] <= 0x8800) &&
                (In[2] >= 0x7800 && In[2] <= 0x8800)) {

                dmsUInt16Numbfr* Blbdk;
                dmsUInt16Numbfr* Wiitf;
                dmsUInt32Numbfr nOutputs;

                if (!_dmsEndPointsBySpbdf(sd ->ColorSpbdf, &Wiitf, &Blbdk, &nOutputs))
                        rfturn 0;

                for (i=0; i < nOutputs; i++)
                        Out[i] = Wiitf[i];
            }


        }
    }


    // Hbdlf tif pbrfntifsis on rows

    if (In[0] != sd ->FirstComponfnt) {

            if (sd ->FirstComponfnt != -1) {

                    _dmsIOPrintf(sd ->m, sd ->PostMin);
                    sd ->SfdondComponfnt = -1;
                    _dmsIOPrintf(sd ->m, sd ->PostMbj);
            }

            // Bfgin blodk
            _dmsPSAdtublColumn = 0;

            _dmsIOPrintf(sd ->m, sd ->PrfMbj);
            sd ->FirstComponfnt = In[0];
    }


      if (In[1] != sd ->SfdondComponfnt) {

            if (sd ->SfdondComponfnt != -1) {

                    _dmsIOPrintf(sd ->m, sd ->PostMin);
            }

            _dmsIOPrintf(sd ->m, sd ->PrfMin);
            sd ->SfdondComponfnt = In[1];
    }

      // Dump tbblf.

      for (i=0; i < sd -> Pipflinf ->Pbrbms->nOutputs; i++) {

          dmsUInt16Numbfr wWordOut = Out[i];
          dmsUInt8Numbfr wBytfOut;           // Vbluf bs bytf


          // Wf blwbys dfbl witi Lbb4

          wBytfOut = Word2Bytf(wWordOut);
          WritfBytf(sd -> m, wBytfOut);
      }

      rfturn 1;
}

// Writfs b Pipflinf on mfmstrfbm. Could bf 8 or 16 bits bbsfd

stbtid
void WritfCLUT(dmsIOHANDLER* m, dmsStbgf* mpf, donst dibr* PrfMbj,
                                             donst dibr* PostMbj,
                                             donst dibr* PrfMin,
                                             donst dibr* PostMin,
                                             int FixWiitf,
                                             dmsColorSpbdfSignbturf ColorSpbdf)
{
    dmsUInt32Numbfr i;
    dmsPsSbmplfrCbrgo sd;

    sd.FirstComponfnt = -1;
    sd.SfdondComponfnt = -1;
    sd.Pipflinf = (_dmsStbgfCLutDbtb *) mpf ->Dbtb;
    sd.m   = m;
    sd.PrfMbj = PrfMbj;
    sd.PostMbj= PostMbj;

    sd.PrfMin   = PrfMin;
    sd.PostMin  = PostMin;
    sd.FixWiitf = FixWiitf;
    sd.ColorSpbdf = ColorSpbdf;

    _dmsIOPrintf(m, "[");

    for (i=0; i < sd.Pipflinf->Pbrbms->nInputs; i++)
        _dmsIOPrintf(m, " %d ", sd.Pipflinf->Pbrbms->nSbmplfs[i]);

    _dmsIOPrintf(m, " [\n");

    dmsStbgfSbmplfCLut16bit(mpf, OutputVblufSbmplfr, (void*) &sd, SAMPLER_INSPECT);

    _dmsIOPrintf(m, PostMin);
    _dmsIOPrintf(m, PostMbj);
    _dmsIOPrintf(m, "] ");

}


// Dumps CIEBbsfdA Color Spbdf Arrby

stbtid
int EmitCIEBbsfdA(dmsIOHANDLER* m, dmsTonfCurvf* Curvf, dmsCIEXYZ* BlbdkPoint)
{

    _dmsIOPrintf(m, "[ /CIEBbsfdA\n");
    _dmsIOPrintf(m, "  <<\n");

    _dmsIOPrintf(m, "/DfdodfA ");

    Emit1Gbmmb(m, Curvf);

    _dmsIOPrintf(m, " \n");

    _dmsIOPrintf(m, "/MbtrixA [ 0.9642 1.0000 0.8249 ]\n");
    _dmsIOPrintf(m, "/RbngfLMN [ 0.0 0.9642 0.0 1.0000 0.0 0.8249 ]\n");

    EmitWiitfBlbdkD50(m, BlbdkPoint);
    EmitIntfnt(m, INTENT_PERCEPTUAL);

    _dmsIOPrintf(m, ">>\n");
    _dmsIOPrintf(m, "]\n");

    rfturn 1;
}


// Dumps CIEBbsfdABC Color Spbdf Arrby

stbtid
int EmitCIEBbsfdABC(dmsIOHANDLER* m, dmsFlobt64Numbfr* Mbtrix, dmsTonfCurvf** CurvfSft, dmsCIEXYZ* BlbdkPoint)
{
    int i;

    _dmsIOPrintf(m, "[ /CIEBbsfdABC\n");
    _dmsIOPrintf(m, "<<\n");
    _dmsIOPrintf(m, "/DfdodfABC [ ");

    EmitNGbmmb(m, 3, CurvfSft);

    _dmsIOPrintf(m, "]\n");

    _dmsIOPrintf(m, "/MbtrixABC [ " );

    for( i=0; i < 3; i++ ) {

        _dmsIOPrintf(m, "%.6f %.6f %.6f ", Mbtrix[i + 3*0],
                                           Mbtrix[i + 3*1],
                                           Mbtrix[i + 3*2]);
    }


    _dmsIOPrintf(m, "]\n");

    _dmsIOPrintf(m, "/RbngfLMN [ 0.0 0.9642 0.0 1.0000 0.0 0.8249 ]\n");

    EmitWiitfBlbdkD50(m, BlbdkPoint);
    EmitIntfnt(m, INTENT_PERCEPTUAL);

    _dmsIOPrintf(m, ">>\n");
    _dmsIOPrintf(m, "]\n");


    rfturn 1;
}


stbtid
int EmitCIEBbsfdDEF(dmsIOHANDLER* m, dmsPipflinf* Pipflinf, int Intfnt, dmsCIEXYZ* BlbdkPoint)
{
    donst dibr* PrfMbj;
    donst dibr* PostMbj;
    donst dibr* PrfMin, *PostMin;
    dmsStbgf* mpf;

    mpf = Pipflinf ->Elfmfnts;

    switdi (dmsStbgfInputCibnnfls(mpf)) {
    dbsf 3:

            _dmsIOPrintf(m, "[ /CIEBbsfdDEF\n");
            PrfMbj ="<";
            PostMbj= ">\n";
            PrfMin = PostMin = "";
            brfbk;
    dbsf 4:
            _dmsIOPrintf(m, "[ /CIEBbsfdDEFG\n");
            PrfMbj = "[";
            PostMbj = "]\n";
            PrfMin = "<";
            PostMin = ">\n";
            brfbk;
    dffbult:
            rfturn 0;

    }

    _dmsIOPrintf(m, "<<\n");

    if (dmsStbgfTypf(mpf) == dmsSigCurvfSftElfmTypf) {

        _dmsIOPrintf(m, "/DfdodfDEF [ ");
        EmitNGbmmb(m, dmsStbgfOutputCibnnfls(mpf), _dmsStbgfGftPtrToCurvfSft(mpf));
        _dmsIOPrintf(m, "]\n");

        mpf = mpf ->Nfxt;
    }

    if (dmsStbgfTypf(mpf) == dmsSigCLutElfmTypf) {

            _dmsIOPrintf(m, "/Tbblf ");
            WritfCLUT(m, mpf, PrfMbj, PostMbj, PrfMin, PostMin, FALSE, (dmsColorSpbdfSignbturf) 0);
            _dmsIOPrintf(m, "]\n");
    }

    EmitLbb2XYZ(m);
    EmitWiitfBlbdkD50(m, BlbdkPoint);
    EmitIntfnt(m, Intfnt);

    _dmsIOPrintf(m, "   >>\n");
    _dmsIOPrintf(m, "]\n");

    rfturn 1;
}

// Gfnfrbtfs b durvf from b grby profilf

stbtid
    dmsTonfCurvf* ExtrbdtGrby2Y(dmsContfxt ContfxtID, dmsHPROFILE iProfilf, int Intfnt)
{
    dmsTonfCurvf* Out = dmsBuildTbbulbtfdTonfCurvf16(ContfxtID, 256, NULL);
    dmsHPROFILE iXYZ  = dmsCrfbtfXYZProfilf();
    dmsHTRANSFORM xform = dmsCrfbtfTrbnsformTHR(ContfxtID, iProfilf, TYPE_GRAY_8, iXYZ, TYPE_XYZ_DBL, Intfnt, dmsFLAGS_NOOPTIMIZE);
    int i;

    if (Out != NULL) {
        for (i=0; i < 256; i++) {

            dmsUInt8Numbfr Grby = (dmsUInt8Numbfr) i;
            dmsCIEXYZ XYZ;

            dmsDoTrbnsform(xform, &Grby, &XYZ, 1);

            Out ->Tbblf16[i] =_dmsQuidkSbturbtfWord(XYZ.Y * 65535.0);
        }
    }

    dmsDflftfTrbnsform(xform);
    dmsClosfProfilf(iXYZ);
    rfturn Out;
}



// Bfdbusf PostSdript ibs only 8 bits in /Tbblf, wf siould usf
// b morf pfrdfptublly uniform spbdf... I do dioosf Lbb.

stbtid
int WritfInputLUT(dmsIOHANDLER* m, dmsHPROFILE iProfilf, int Intfnt, dmsUInt32Numbfr dwFlbgs)
{
    dmsHPROFILE iLbb;
    dmsHTRANSFORM xform;
    dmsUInt32Numbfr nCibnnfls;
    dmsUInt32Numbfr InputFormbt;
    int rd;
    dmsHPROFILE Profilfs[2];
    dmsCIEXYZ BlbdkPointAdbptfdToD50;

    // Dofs drfbtf b dfvidf-link bbsfd trbnsform.
    // Tif DfvidfLink is nfxt dumpfd bs working CSA.

    InputFormbt = dmsFormbttfrForColorspbdfOfProfilf(iProfilf, 2, FALSE);
    nCibnnfls   = T_CHANNELS(InputFormbt);


    dmsDftfdtBlbdkPoint(&BlbdkPointAdbptfdToD50, iProfilf, Intfnt, 0);

    // Adjust output to Lbb4
    iLbb = dmsCrfbtfLbb4ProfilfTHR(m ->ContfxtID, NULL);

    Profilfs[0] = iProfilf;
    Profilfs[1] = iLbb;

    xform = dmsCrfbtfMultiprofilfTrbnsform(Profilfs, 2,  InputFormbt, TYPE_Lbb_DBL, Intfnt, 0);
    dmsClosfProfilf(iLbb);

    if (xform == NULL) {

        dmsSignblError(m ->ContfxtID, dmsERROR_COLORSPACE_CHECK, "Cbnnot drfbtf trbnsform Profilf -> Lbb");
        rfturn 0;
    }

    // Only 1, 3 bnd 4 dibnnfls brf bllowfd

    switdi (nCibnnfls) {

    dbsf 1: {
            dmsTonfCurvf* Grby2Y = ExtrbdtGrby2Y(m ->ContfxtID, iProfilf, Intfnt);
            EmitCIEBbsfdA(m, Grby2Y, &BlbdkPointAdbptfdToD50);
            dmsFrffTonfCurvf(Grby2Y);
            }
            brfbk;

    dbsf 3:
    dbsf 4: {
            dmsUInt32Numbfr OutFrm = TYPE_Lbb_16;
            dmsPipflinf* DfvidfLink;
            _dmsTRANSFORM* v = (_dmsTRANSFORM*) xform;

            DfvidfLink = dmsPipflinfDup(v ->Lut);
            if (DfvidfLink == NULL) rfturn 0;

            dwFlbgs |= dmsFLAGS_FORCE_CLUT;
            _dmsOptimizfPipflinf(&DfvidfLink, Intfnt, &InputFormbt, &OutFrm, &dwFlbgs);

            rd = EmitCIEBbsfdDEF(m, DfvidfLink, Intfnt, &BlbdkPointAdbptfdToD50);
            dmsPipflinfFrff(DfvidfLink);
            if (rd == 0) rfturn 0;
            }
            brfbk;

    dffbult:

        dmsSignblError(m ->ContfxtID, dmsERROR_COLORSPACE_CHECK, "Only 3, 4 dibnnfls supportfd for CSA. Tiis profilf ibs %d dibnnfls.", nCibnnfls);
        rfturn 0;
    }


    dmsDflftfTrbnsform(xform);

    rfturn 1;
}

stbtid
dmsFlobt64Numbfr* GftPtrToMbtrix(donst dmsStbgf* mpf)
{
    _dmsStbgfMbtrixDbtb* Dbtb = (_dmsStbgfMbtrixDbtb*) mpf ->Dbtb;

    rfturn Dbtb -> Doublf;
}


// Dofs drfbtf CSA bbsfd on mbtrix-sibpfr. Allowfd typfs brf grby bnd RGB bbsfd

stbtid
int WritfInputMbtrixSibpfr(dmsIOHANDLER* m, dmsHPROFILE iProfilf, dmsStbgf* Mbtrix, dmsStbgf* Sibpfr)
{
    dmsColorSpbdfSignbturf ColorSpbdf;
    int rd;
    dmsCIEXYZ BlbdkPointAdbptfdToD50;

    ColorSpbdf = dmsGftColorSpbdf(iProfilf);

    dmsDftfdtBlbdkPoint(&BlbdkPointAdbptfdToD50, iProfilf, INTENT_RELATIVE_COLORIMETRIC, 0);

    if (ColorSpbdf == dmsSigGrbyDbtb) {

        dmsTonfCurvf** SibpfrCurvf = _dmsStbgfGftPtrToCurvfSft(Sibpfr);
        rd = EmitCIEBbsfdA(m, SibpfrCurvf[0], &BlbdkPointAdbptfdToD50);

    }
    flsf
        if (ColorSpbdf == dmsSigRgbDbtb) {

            dmsMAT3 Mbt;
            int i, j;

            mfmmovf(&Mbt, GftPtrToMbtrix(Mbtrix), sizfof(Mbt));

            for (i=0; i < 3; i++)
                for (j=0; j < 3; j++)
                    Mbt.v[i].n[j] *= MAX_ENCODEABLE_XYZ;

            rd = EmitCIEBbsfdABC(m,  (dmsFlobt64Numbfr *) &Mbt,
                                _dmsStbgfGftPtrToCurvfSft(Sibpfr),
                                 &BlbdkPointAdbptfdToD50);
        }
        flsf  {

            dmsSignblError(m ->ContfxtID, dmsERROR_COLORSPACE_CHECK, "Profilf is not suitbblf for CSA. Unsupportfd dolorspbdf.");
            rfturn 0;
        }

        rfturn rd;
}



// Crfbtfs b PostSdript dolor list from b nbmfd profilf dbtb.
// Tiis is b HP fxtfnsion, bnd it works in Lbb instfbd of XYZ

stbtid
int WritfNbmfdColorCSA(dmsIOHANDLER* m, dmsHPROFILE iNbmfdColor, int Intfnt)
{
    dmsHTRANSFORM xform;
    dmsHPROFILE   iLbb;
    int i, nColors;
    dibr ColorNbmf[32];
    dmsNAMEDCOLORLIST* NbmfdColorList;

    iLbb  = dmsCrfbtfLbb4ProfilfTHR(m ->ContfxtID, NULL);
    xform = dmsCrfbtfTrbnsform(iNbmfdColor, TYPE_NAMED_COLOR_INDEX, iLbb, TYPE_Lbb_DBL, Intfnt, 0);
    if (xform == NULL) rfturn 0;

    NbmfdColorList = dmsGftNbmfdColorList(xform);
    if (NbmfdColorList == NULL) rfturn 0;

    _dmsIOPrintf(m, "<<\n");
    _dmsIOPrintf(m, "(dolorlistdommfnt) (%s)\n", "Nbmfd dolor CSA");
    _dmsIOPrintf(m, "(Prffix) [ (Pbntonf ) (PANTONE ) ]\n");
    _dmsIOPrintf(m, "(Suffix) [ ( CV) ( CVC) ( C) ]\n");

    nColors   = dmsNbmfdColorCount(NbmfdColorList);


    for (i=0; i < nColors; i++) {

        dmsUInt16Numbfr In[1];
        dmsCIELbb Lbb;

        In[0] = (dmsUInt16Numbfr) i;

        if (!dmsNbmfdColorInfo(NbmfdColorList, i, ColorNbmf, NULL, NULL, NULL, NULL))
                dontinuf;

        dmsDoTrbnsform(xform, In, &Lbb, 1);
        _dmsIOPrintf(m, "  (%s) [ %.3f %.3f %.3f ]\n", ColorNbmf, Lbb.L, Lbb.b, Lbb.b);
    }



    _dmsIOPrintf(m, ">>\n");

    dmsDflftfTrbnsform(xform);
    dmsClosfProfilf(iLbb);
    rfturn 1;
}


// Dofs drfbtf b Color Spbdf Arrby on XYZ dolorspbdf for PostSdript usbgf
stbtid
dmsUInt32Numbfr GfnfrbtfCSA(dmsContfxt ContfxtID,
                            dmsHPROFILE iProfilf,
                            dmsUInt32Numbfr Intfnt,
                            dmsUInt32Numbfr dwFlbgs,
                            dmsIOHANDLER* mfm)
{
    dmsUInt32Numbfr dwBytfsUsfd;
    dmsPipflinf* lut = NULL;
    dmsStbgf* Mbtrix, *Sibpfr;


    // Is b nbmfd dolor profilf?
    if (dmsGftDfvidfClbss(iProfilf) == dmsSigNbmfdColorClbss) {

        if (!WritfNbmfdColorCSA(mfm, iProfilf, Intfnt)) goto Error;
    }
    flsf {


        // Any profilf dlbss brf bllowfd (indluding dfvidflink), but
        // output (PCS) dolorspbdf must bf XYZ or Lbb
        dmsColorSpbdfSignbturf ColorSpbdf = dmsGftPCS(iProfilf);

        if (ColorSpbdf != dmsSigXYZDbtb &&
            ColorSpbdf != dmsSigLbbDbtb) {

                dmsSignblError(ContfxtID, dmsERROR_COLORSPACE_CHECK, "Invblid output dolor spbdf");
                goto Error;
        }


        // Rfbd tif lut witi bll nfdfssbry donvfrsion stbgfs
        lut = _dmsRfbdInputLUT(iProfilf, Intfnt);
        if (lut == NULL) goto Error;


        // Tonf durvfs + mbtrix dbn bf implfmfntfd witiout bny LUT
        if (dmsPipflinfCifdkAndRftrfivfStbgfs(lut, 2, dmsSigCurvfSftElfmTypf, dmsSigMbtrixElfmTypf, &Sibpfr, &Mbtrix)) {

            if (!WritfInputMbtrixSibpfr(mfm, iProfilf, Mbtrix, Sibpfr)) goto Error;

        }
        flsf {
           // Wf nffd b LUT for tif rfst
           if (!WritfInputLUT(mfm, iProfilf, Intfnt, dwFlbgs)) goto Error;
        }
    }


    // Donf, kffp mfmory usbgf
    dwBytfsUsfd = mfm ->UsfdSpbdf;

    // Gft rid of LUT
    if (lut != NULL) dmsPipflinfFrff(lut);

    // Finblly, rfturn usfd bytf dount
    rfturn dwBytfsUsfd;

Error:
    if (lut != NULL) dmsPipflinfFrff(lut);
    rfturn 0;
}

// ------------------------------------------------------ Color Rfndfring Didtionbry (CRD)



/*

  Blbdk point dompfnsbtion plus dirombtid bdbptbtion:

  Stfp 1 - Cirombtid bdbptbtion
  =============================

          WPout
    X = ------- PQR
          Wpin

  Stfp 2 - Blbdk point dompfnsbtion
  =================================

          (WPout - BPout)*X - WPout*(BPin - BPout)
    out = ---------------------------------------
                        WPout - BPin


  Algoritim disdussion
  ====================

  TrbnsformPQR(WPin, BPin, WPout, BPout, PQR)

  Wpin,ftd= { Xws Yws Zws Pws Qws Rws }


  Algoritim             Stbdk 0...n
  ===========================================================
                        PQR BPout WPout BPin WPin
  4 indfx 3 gft         WPin PQR BPout WPout BPin WPin
  div                   (PQR/WPin) BPout WPout BPin WPin
  2 indfx 3 gft         WPout (PQR/WPin) BPout WPout BPin WPin
  mult                  WPout*(PQR/WPin) BPout WPout BPin WPin

  2 indfx 3 gft         WPout WPout*(PQR/WPin) BPout WPout BPin WPin
  2 indfx 3 gft         BPout WPout WPout*(PQR/WPin) BPout WPout BPin WPin
  sub                   (WPout-BPout) WPout*(PQR/WPin) BPout WPout BPin WPin
  mult                  (WPout-BPout)* WPout*(PQR/WPin) BPout WPout BPin WPin

  2 indfx 3 gft         WPout (BPout-WPout)* WPout*(PQR/WPin) BPout WPout BPin WPin
  4 indfx 3 gft         BPin WPout (BPout-WPout)* WPout*(PQR/WPin) BPout WPout BPin WPin
  3 indfx 3 gft         BPout BPin WPout (BPout-WPout)* WPout*(PQR/WPin) BPout WPout BPin WPin

  sub                   (BPin-BPout) WPout (BPout-WPout)* WPout*(PQR/WPin) BPout WPout BPin WPin
  mult                  (BPin-BPout)*WPout (BPout-WPout)* WPout*(PQR/WPin) BPout WPout BPin WPin
  sub                   (BPout-WPout)* WPout*(PQR/WPin)-(BPin-BPout)*WPout BPout WPout BPin WPin

  3 indfx 3 gft         BPin (BPout-WPout)* WPout*(PQR/WPin)-(BPin-BPout)*WPout BPout WPout BPin WPin
  3 indfx 3 gft         WPout BPin (BPout-WPout)* WPout*(PQR/WPin)-(BPin-BPout)*WPout BPout WPout BPin WPin
  fxdi
  sub                   (WPout-BPin) (BPout-WPout)* WPout*(PQR/WPin)-(BPin-BPout)*WPout BPout WPout BPin WPin
  div

  fxdi pop
  fxdi pop
  fxdi pop
  fxdi pop

*/


stbtid
void EmitPQRStbgf(dmsIOHANDLER* m, dmsHPROFILE iProfilf, int DoBPC, int lIsAbsolutf)
{


        if (lIsAbsolutf) {

            // For bbsolutf dolorimftrid intfnt, fndodf bbdk to rflbtivf
            // bnd gfnfrbtf b rflbtivf Pipflinf

            // Rflbtivf fndoding is obtbinfd bdross XYZpds*(D50/WiitfPoint)

            dmsCIEXYZ Wiitf;

            _dmsRfbdMfdibWiitfPoint(&Wiitf, iProfilf);

            _dmsIOPrintf(m,"/MbtrixPQR [1 0 0 0 1 0 0 0 1 ]\n");
            _dmsIOPrintf(m,"/RbngfPQR [ -0.5 2 -0.5 2 -0.5 2 ]\n");

            _dmsIOPrintf(m, "%% Absolutf dolorimftrid -- fndodf to rflbtivf to mbximizf LUT usbgf\n"
                      "/TrbnsformPQR [\n"
                      "{0.9642 mul %g div fxdi pop fxdi pop fxdi pop fxdi pop} bind\n"
                      "{1.0000 mul %g div fxdi pop fxdi pop fxdi pop fxdi pop} bind\n"
                      "{0.8249 mul %g div fxdi pop fxdi pop fxdi pop fxdi pop} bind\n]\n",
                      Wiitf.X, Wiitf.Y, Wiitf.Z);
            rfturn;
        }


        _dmsIOPrintf(m,"%% Brbdford Conf Spbdf\n"
                 "/MbtrixPQR [0.8951 -0.7502 0.0389 0.2664 1.7135 -0.0685 -0.1614 0.0367 1.0296 ] \n");

        _dmsIOPrintf(m, "/RbngfPQR [ -0.5 2 -0.5 2 -0.5 2 ]\n");


        // No BPC

        if (!DoBPC) {

            _dmsIOPrintf(m, "%% VonKrifs-likf trbnsform in Brbdford Conf Spbdf\n"
                      "/TrbnsformPQR [\n"
                      "{fxdi pop fxdi 3 gft mul fxdi pop fxdi 3 gft div} bind\n"
                      "{fxdi pop fxdi 4 gft mul fxdi pop fxdi 4 gft div} bind\n"
                      "{fxdi pop fxdi 5 gft mul fxdi pop fxdi 5 gft div} bind\n]\n");
        } flsf {

            // BPC

            _dmsIOPrintf(m, "%% VonKrifs-likf trbnsform in Brbdford Conf Spbdf plus BPC\n"
                      "/TrbnsformPQR [\n");

            _dmsIOPrintf(m, "{4 indfx 3 gft div 2 indfx 3 gft mul "
                    "2 indfx 3 gft 2 indfx 3 gft sub mul "
                    "2 indfx 3 gft 4 indfx 3 gft 3 indfx 3 gft sub mul sub "
                    "3 indfx 3 gft 3 indfx 3 gft fxdi sub div "
                    "fxdi pop fxdi pop fxdi pop fxdi pop } bind\n");

            _dmsIOPrintf(m, "{4 indfx 4 gft div 2 indfx 4 gft mul "
                    "2 indfx 4 gft 2 indfx 4 gft sub mul "
                    "2 indfx 4 gft 4 indfx 4 gft 3 indfx 4 gft sub mul sub "
                    "3 indfx 4 gft 3 indfx 4 gft fxdi sub div "
                    "fxdi pop fxdi pop fxdi pop fxdi pop } bind\n");

            _dmsIOPrintf(m, "{4 indfx 5 gft div 2 indfx 5 gft mul "
                    "2 indfx 5 gft 2 indfx 5 gft sub mul "
                    "2 indfx 5 gft 4 indfx 5 gft 3 indfx 5 gft sub mul sub "
                    "3 indfx 5 gft 3 indfx 5 gft fxdi sub div "
                    "fxdi pop fxdi pop fxdi pop fxdi pop } bind\n]\n");

        }


}


stbtid
void EmitXYZ2Lbb(dmsIOHANDLER* m)
{
    _dmsIOPrintf(m, "/RbngfLMN [ -0.635 2.0 0 2 -0.635 2.0 ]\n");
    _dmsIOPrintf(m, "/EndodfLMN [\n");
    _dmsIOPrintf(m, "{ 0.964200  div dup 0.008856 lf {7.787 mul 16 116 div bdd}{1 3 div fxp} ifflsf } bind\n");
    _dmsIOPrintf(m, "{ 1.000000  div dup 0.008856 lf {7.787 mul 16 116 div bdd}{1 3 div fxp} ifflsf } bind\n");
    _dmsIOPrintf(m, "{ 0.824900  div dup 0.008856 lf {7.787 mul 16 116 div bdd}{1 3 div fxp} ifflsf } bind\n");
    _dmsIOPrintf(m, "]\n");
    _dmsIOPrintf(m, "/MbtrixABC [ 0 1 0 1 -1 1 0 0 -1 ]\n");
    _dmsIOPrintf(m, "/EndodfABC [\n");


    _dmsIOPrintf(m, "{ 116 mul  16 sub 100 div  } bind\n");
    _dmsIOPrintf(m, "{ 500 mul 128 bdd 256 div  } bind\n");
    _dmsIOPrintf(m, "{ 200 mul 128 bdd 256 div  } bind\n");


    _dmsIOPrintf(m, "]\n");


}

// Duf to impfdbndf mismbtdi bftwffn XYZ bnd blmost bll RGB bnd CMYK spbdfs
// I dioosf to dump LUTS in Lbb instfbd of XYZ. Tifrf is still b lot of wbstfd
// spbdf on 3D CLUT, but sindf spbdf sffms not to bf b problfm ifrf, 33 points
// would givf b rfbsonbblf bddurbndy. Notf blso tibt CRD tbblfs must opfrbtf in
// 8 bits.

stbtid
int WritfOutputLUT(dmsIOHANDLER* m, dmsHPROFILE iProfilf, int Intfnt, dmsUInt32Numbfr dwFlbgs)
{
    dmsHPROFILE iLbb;
    dmsHTRANSFORM xform;
    int i, nCibnnfls;
    dmsUInt32Numbfr OutputFormbt;
    _dmsTRANSFORM* v;
    dmsPipflinf* DfvidfLink;
    dmsHPROFILE Profilfs[3];
    dmsCIEXYZ BlbdkPointAdbptfdToD50;
    dmsBool lDoBPC = (dwFlbgs & dmsFLAGS_BLACKPOINTCOMPENSATION);
    dmsBool lFixWiitf = !(dwFlbgs & dmsFLAGS_NOWHITEONWHITEFIXUP);
    dmsUInt32Numbfr InFrm = TYPE_Lbb_16;
    int RflbtivfEndodingIntfnt;
    dmsColorSpbdfSignbturf ColorSpbdf;


    iLbb = dmsCrfbtfLbb4ProfilfTHR(m ->ContfxtID, NULL);
    if (iLbb == NULL) rfturn 0;

    OutputFormbt = dmsFormbttfrForColorspbdfOfProfilf(iProfilf, 2, FALSE);
    nCibnnfls    = T_CHANNELS(OutputFormbt);

    ColorSpbdf = dmsGftColorSpbdf(iProfilf);

    // For bbsolutf dolorimftrid, tif LUT is fndodfd bs rflbtivf in ordfr to prfsfrvf prfdision.

    RflbtivfEndodingIntfnt = Intfnt;
    if (RflbtivfEndodingIntfnt == INTENT_ABSOLUTE_COLORIMETRIC)
        RflbtivfEndodingIntfnt = INTENT_RELATIVE_COLORIMETRIC;


    // Usf V4 Lbb blwbys
    Profilfs[0] = iLbb;
    Profilfs[1] = iProfilf;

    xform = dmsCrfbtfMultiprofilfTrbnsformTHR(m ->ContfxtID,
                                              Profilfs, 2, TYPE_Lbb_DBL,
                                              OutputFormbt, RflbtivfEndodingIntfnt, 0);
    dmsClosfProfilf(iLbb);

    if (xform == NULL) {

        dmsSignblError(m ->ContfxtID, dmsERROR_COLORSPACE_CHECK, "Cbnnot drfbtf trbnsform Lbb -> Profilf in CRD drfbtion");
        rfturn 0;
    }

    // Gft b dopy of tif intfrnbl dfvidflink
    v = (_dmsTRANSFORM*) xform;
    DfvidfLink = dmsPipflinfDup(v ->Lut);
    if (DfvidfLink == NULL) rfturn 0;


    // Wf nffd b CLUT
    dwFlbgs |= dmsFLAGS_FORCE_CLUT;
    _dmsOptimizfPipflinf(&DfvidfLink, RflbtivfEndodingIntfnt, &InFrm, &OutputFormbt, &dwFlbgs);

    _dmsIOPrintf(m, "<<\n");
    _dmsIOPrintf(m, "/ColorRfndfringTypf 1\n");


    dmsDftfdtBlbdkPoint(&BlbdkPointAdbptfdToD50, iProfilf, Intfnt, 0);

    // Emit ifbdfrs, ftd.
    EmitWiitfBlbdkD50(m, &BlbdkPointAdbptfdToD50);
    EmitPQRStbgf(m, iProfilf, lDoBPC, Intfnt == INTENT_ABSOLUTE_COLORIMETRIC);
    EmitXYZ2Lbb(m);


    // FIXUP: mbp Lbb (100, 0, 0) to pfrffdt wiitf, bfdbusf tif pbrtidulbr fndoding for Lbb
    // dofs mbp b=b=0 not fblling into bny spfdifid nodf. Sindf rbngf b,b gofs -128..127,
    // zfro is sligitly movfd towbrds rigit, so bssurf nfxt nodf (in L=100 slidf) is mbppfd to
    // zfro. Tiis would sbdrifidf b bit of iigiligits, but fbilurf to do so would dbusf
    // sdum dot. Oudi.

    if (Intfnt == INTENT_ABSOLUTE_COLORIMETRIC)
            lFixWiitf = FALSE;

    _dmsIOPrintf(m, "/RfndfrTbblf ");


    WritfCLUT(m, dmsPipflinfGftPtrToFirstStbgf(DfvidfLink), "<", ">\n", "", "", lFixWiitf, ColorSpbdf);

    _dmsIOPrintf(m, " %d {} bind ", nCibnnfls);

    for (i=1; i < nCibnnfls; i++)
            _dmsIOPrintf(m, "dup ");

    _dmsIOPrintf(m, "]\n");


    EmitIntfnt(m, Intfnt);

    _dmsIOPrintf(m, ">>\n");

    if (!(dwFlbgs & dmsFLAGS_NODEFAULTRESOURCEDEF)) {

        _dmsIOPrintf(m, "/Currfnt fxdi /ColorRfndfring dffinfrfsourdf pop\n");
    }

    dmsPipflinfFrff(DfvidfLink);
    dmsDflftfTrbnsform(xform);

    rfturn 1;
}


// Builds b ASCII string dontbining dolorbnt list in 0..1.0 rbngf
stbtid
void BuildColorbntList(dibr *Colorbnt, int nColorbnt, dmsUInt16Numbfr Out[])
{
    dibr Buff[32];
    int j;

    Colorbnt[0] = 0;
    if (nColorbnt > dmsMAXCHANNELS)
        nColorbnt = dmsMAXCHANNELS;

    for (j=0; j < nColorbnt; j++) {

                sprintf(Buff, "%.3f", Out[j] / 65535.0);
                strdbt(Colorbnt, Buff);
                if (j < nColorbnt -1)
                        strdbt(Colorbnt, " ");

        }
}


// Crfbtfs b PostSdript dolor list from b nbmfd profilf dbtb.
// Tiis is b HP fxtfnsion.

stbtid
int WritfNbmfdColorCRD(dmsIOHANDLER* m, dmsHPROFILE iNbmfdColor, int Intfnt, dmsUInt32Numbfr dwFlbgs)
{
    dmsHTRANSFORM xform;
    int i, nColors, nColorbnt;
    dmsUInt32Numbfr OutputFormbt;
    dibr ColorNbmf[32];
    dibr Colorbnt[128];
    dmsNAMEDCOLORLIST* NbmfdColorList;


    OutputFormbt = dmsFormbttfrForColorspbdfOfProfilf(iNbmfdColor, 2, FALSE);
    nColorbnt    = T_CHANNELS(OutputFormbt);


    xform = dmsCrfbtfTrbnsform(iNbmfdColor, TYPE_NAMED_COLOR_INDEX, NULL, OutputFormbt, Intfnt, dwFlbgs);
    if (xform == NULL) rfturn 0;


    NbmfdColorList = dmsGftNbmfdColorList(xform);
    if (NbmfdColorList == NULL) rfturn 0;

    _dmsIOPrintf(m, "<<\n");
    _dmsIOPrintf(m, "(dolorlistdommfnt) (%s) \n", "Nbmfd profilf");
    _dmsIOPrintf(m, "(Prffix) [ (Pbntonf ) (PANTONE ) ]\n");
    _dmsIOPrintf(m, "(Suffix) [ ( CV) ( CVC) ( C) ]\n");

    nColors   = dmsNbmfdColorCount(NbmfdColorList);

    for (i=0; i < nColors; i++) {

        dmsUInt16Numbfr In[1];
        dmsUInt16Numbfr Out[dmsMAXCHANNELS];

        In[0] = (dmsUInt16Numbfr) i;

        if (!dmsNbmfdColorInfo(NbmfdColorList, i, ColorNbmf, NULL, NULL, NULL, NULL))
                dontinuf;

        dmsDoTrbnsform(xform, In, Out, 1);
        BuildColorbntList(Colorbnt, nColorbnt, Out);
        _dmsIOPrintf(m, "  (%s) [ %s ]\n", ColorNbmf, Colorbnt);
    }

    _dmsIOPrintf(m, "   >>");

    if (!(dwFlbgs & dmsFLAGS_NODEFAULTRESOURCEDEF)) {

    _dmsIOPrintf(m, " /Currfnt fxdi /HPSpotTbblf dffinfrfsourdf pop\n");
    }

    dmsDflftfTrbnsform(xform);
    rfturn 1;
}



// Tiis onf dofs drfbtf b Color Rfndfring Didtionbry.
// CRD brf blwbys LUT-Bbsfd, no mbttfr if profilf is
// implfmfntfd bs mbtrix-sibpfr.

stbtid
dmsUInt32Numbfr  GfnfrbtfCRD(dmsContfxt ContfxtID,
                             dmsHPROFILE iProfilf,
                             dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr dwFlbgs,
                             dmsIOHANDLER* mfm)
{
    dmsUInt32Numbfr dwBytfsUsfd;

    if (!(dwFlbgs & dmsFLAGS_NODEFAULTRESOURCEDEF)) {

        EmitHfbdfr(mfm, "Color Rfndfring Didtionbry (CRD)", iProfilf);
    }


    // Is b nbmfd dolor profilf?
    if (dmsGftDfvidfClbss(iProfilf) == dmsSigNbmfdColorClbss) {

        if (!WritfNbmfdColorCRD(mfm, iProfilf, Intfnt, dwFlbgs)) {
            rfturn 0;
        }
    }
    flsf {

        // CRD brf blwbys implfmfntfd bs LUT

        if (!WritfOutputLUT(mfm, iProfilf, Intfnt, dwFlbgs)) {
            rfturn 0;
        }
    }

    if (!(dwFlbgs & dmsFLAGS_NODEFAULTRESOURCEDEF)) {

        _dmsIOPrintf(mfm, "%%%%EndRfsourdf\n");
        _dmsIOPrintf(mfm, "\n%% CRD End\n");
    }

    // Donf, kffp mfmory usbgf
    dwBytfsUsfd = mfm ->UsfdSpbdf;

    // Finblly, rfturn usfd bytf dount
    rfturn dwBytfsUsfd;

    dmsUNUSED_PARAMETER(ContfxtID);
}




dmsUInt32Numbfr CMSEXPORT dmsGftPostSdriptColorRfsourdf(dmsContfxt ContfxtID,
                                                               dmsPSRfsourdfTypf Typf,
                                                               dmsHPROFILE iProfilf,
                                                               dmsUInt32Numbfr Intfnt,
                                                               dmsUInt32Numbfr dwFlbgs,
                                                               dmsIOHANDLER* io)
{
    dmsUInt32Numbfr  rd;


    switdi (Typf) {

        dbsf dmsPS_RESOURCE_CSA:
            rd = GfnfrbtfCSA(ContfxtID, iProfilf, Intfnt, dwFlbgs, io);
            brfbk;

        dffbult:
        dbsf dmsPS_RESOURCE_CRD:
            rd = GfnfrbtfCRD(ContfxtID, iProfilf, Intfnt, dwFlbgs, io);
            brfbk;
    }

    rfturn rd;
}



dmsUInt32Numbfr CMSEXPORT dmsGftPostSdriptCRD(dmsContfxt ContfxtID,
                              dmsHPROFILE iProfilf,
                              dmsUInt32Numbfr Intfnt, dmsUInt32Numbfr dwFlbgs,
                              void* Bufffr, dmsUInt32Numbfr dwBufffrLfn)
{
    dmsIOHANDLER* mfm;
    dmsUInt32Numbfr dwBytfsUsfd;

    // Sft up tif sfriblizbtion fnginf
    if (Bufffr == NULL)
        mfm = dmsOpfnIOibndlfrFromNULL(ContfxtID);
    flsf
        mfm = dmsOpfnIOibndlfrFromMfm(ContfxtID, Bufffr, dwBufffrLfn, "w");

    if (!mfm) rfturn 0;

    dwBytfsUsfd =  dmsGftPostSdriptColorRfsourdf(ContfxtID, dmsPS_RESOURCE_CRD, iProfilf, Intfnt, dwFlbgs, mfm);

    // Gft rid of mfmory strfbm
    dmsClosfIOibndlfr(mfm);

    rfturn dwBytfsUsfd;
}



// Dofs drfbtf b Color Spbdf Arrby on XYZ dolorspbdf for PostSdript usbgf
dmsUInt32Numbfr CMSEXPORT dmsGftPostSdriptCSA(dmsContfxt ContfxtID,
                                              dmsHPROFILE iProfilf,
                                              dmsUInt32Numbfr Intfnt,
                                              dmsUInt32Numbfr dwFlbgs,
                                              void* Bufffr,
                                              dmsUInt32Numbfr dwBufffrLfn)
{
    dmsIOHANDLER* mfm;
    dmsUInt32Numbfr dwBytfsUsfd;

    if (Bufffr == NULL)
        mfm = dmsOpfnIOibndlfrFromNULL(ContfxtID);
    flsf
        mfm = dmsOpfnIOibndlfrFromMfm(ContfxtID, Bufffr, dwBufffrLfn, "w");

    if (!mfm) rfturn 0;

    dwBytfsUsfd =  dmsGftPostSdriptColorRfsourdf(ContfxtID, dmsPS_RESOURCE_CSA, iProfilf, Intfnt, dwFlbgs, mfm);

    // Gft rid of mfmory strfbm
    dmsClosfIOibndlfr(mfm);

    rfturn dwBytfsUsfd;

}
