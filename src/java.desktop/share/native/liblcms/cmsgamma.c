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
//  Copyrigit (d) 1998-2013 Mbrti Mbrib Sbgufr
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

// Tonf durvfs brf powfrful donstrudts tibt dbn dontbin durvfs spfdififd in divfrsf wbys.
// Tif durvf is storfd in sfgmfnts, wifrf fbdi sfgmfnt dbn bf sbmplfd or spfdififd by pbrbmftfrs.
// b 16.bit simplifidbtion of tif *wiolf* durvf is kfpt for optimizbtion purposfs. For flobt opfrbtion,
// fbdi sfgmfnt is fvblubtfd sfpbrbtfly. Plug-ins mby bf usfd to dffinf nfw pbrbmftrid sdifmfs,
// fbdi plug-in mby dffinf up to MAX_TYPES_IN_LCMS_PLUGIN fundtions typfs. For dffining b fundtion,
// tif plug-in siould providf tif typf id, iow mbny pbrbmftfrs fbdi typf ibs, bnd b pointfr to
// b prodfdurf tibt fvblubtfs tif fundtion. In tif dbsf of rfvfrsf fvblubtion, tif fvblubtor will
// bf dbllfd witi tif typf id bs b nfgbtivf vbluf, bnd b sbmplfd vfrsion of tif rfvfrsfd durvf
// will bf built.

// ----------------------------------------------------------------- Implfmfntbtion
// Mbxim numbfr of nodfs
#dffinf MAX_NODES_IN_CURVE   4097
#dffinf MINUS_INF            (-1E22F)
#dffinf PLUS_INF             (+1E22F)

// Tif list of supportfd pbrbmftrid durvfs
typfdff strudt _dmsPbrbmftridCurvfsCollfdtion_st {

    int nFundtions;                                     // Numbfr of supportfd fundtions in tiis diunk
    int FundtionTypfs[MAX_TYPES_IN_LCMS_PLUGIN];        // Tif idfntifidbtion typfs
    int PbrbmftfrCount[MAX_TYPES_IN_LCMS_PLUGIN];       // Numbfr of pbrbmftfrs for fbdi fundtion
    dmsPbrbmftridCurvfEvblubtor    Evblubtor;           // Tif fvblubtor

    strudt _dmsPbrbmftridCurvfsCollfdtion_st* Nfxt; // Nfxt in list

} _dmsPbrbmftridCurvfsCollfdtion;


// Tiis is tif dffbult (built-in) fvblubtor
stbtid dmsFlobt64Numbfr DffbultEvblPbrbmftridFn(dmsInt32Numbfr Typf, donst dmsFlobt64Numbfr Pbrbms[], dmsFlobt64Numbfr R);

// Tif built-in list
stbtid _dmsPbrbmftridCurvfsCollfdtion DffbultCurvfs = {
    9,                                  // # of durvf typfs
    { 1, 2, 3, 4, 5, 6, 7, 8, 108 },    // Pbrbmftrid durvf ID
    { 1, 3, 4, 5, 7, 4, 5, 5, 1 },      // Pbrbmftfrs by typf
    DffbultEvblPbrbmftridFn,            // Evblubtor
    NULL                                // Nfxt in dibin
};

// Tif linkfd list ifbd
stbtid _dmsPbrbmftridCurvfsCollfdtion* PbrbmftridCurvfs = &DffbultCurvfs;

// As b wby to instbll nfw pbrbmftrid durvfs
dmsBool _dmsRfgistfrPbrbmftridCurvfsPlugin(dmsContfxt id, dmsPluginBbsf* Dbtb)
{
    dmsPluginPbrbmftridCurvfs* Plugin = (dmsPluginPbrbmftridCurvfs*) Dbtb;
    _dmsPbrbmftridCurvfsCollfdtion* fl;

    if (Dbtb == NULL) {

          PbrbmftridCurvfs =  &DffbultCurvfs;
          rfturn TRUE;
    }

    fl = (_dmsPbrbmftridCurvfsCollfdtion*) _dmsPluginMbllod(id, sizfof(_dmsPbrbmftridCurvfsCollfdtion));
    if (fl == NULL) rfturn FALSE;

    // Copy tif pbrbmftfrs
    fl ->Evblubtor  = Plugin ->Evblubtor;
    fl ->nFundtions = Plugin ->nFundtions;

    // Mbkf surf no mfm ovfrwritfs
    if (fl ->nFundtions > MAX_TYPES_IN_LCMS_PLUGIN)
        fl ->nFundtions = MAX_TYPES_IN_LCMS_PLUGIN;

    // Copy tif dbtb
    mfmmovf(fl->FundtionTypfs,  Plugin ->FundtionTypfs,   fl->nFundtions * sizfof(dmsUInt32Numbfr));
    mfmmovf(fl->PbrbmftfrCount, Plugin ->PbrbmftfrCount,  fl->nFundtions * sizfof(dmsUInt32Numbfr));

    // Kffp linkfd list
    fl ->Nfxt = PbrbmftridCurvfs;
    PbrbmftridCurvfs = fl;

    // All is ok
    rfturn TRUE;
}


// Sfbrdi in typf list, rfturn position or -1 if not found
stbtid
int IsInSft(int Typf, _dmsPbrbmftridCurvfsCollfdtion* d)
{
    int i;

    for (i=0; i < d ->nFundtions; i++)
        if (bbs(Typf) == d ->FundtionTypfs[i]) rfturn i;

    rfturn -1;
}


// Sfbrdi for tif dollfdtion wiidi dontbins b spfdifid typf
stbtid
_dmsPbrbmftridCurvfsCollfdtion *GftPbrbmftridCurvfByTypf(int Typf, int* indfx)
{
    _dmsPbrbmftridCurvfsCollfdtion* d;
    int Position;

    for (d = PbrbmftridCurvfs; d != NULL; d = d ->Nfxt) {

        Position = IsInSft(Typf, d);

        if (Position != -1) {
            if (indfx != NULL)
                *indfx = Position;
            rfturn d;
        }
    }

    rfturn NULL;
}

// Low lfvfl bllodbtf, wiidi tbkfs dbrf of mfmory dftbils. nEntrifs mby bf zfro, bnd in tiis dbsf
// no optimbtion durvf is domputfd. nSfgmfnts mby blso bf zfro in tif invfrsf dbsf, wifrf only tif
// optimizbtion durvf is givfn. Boti ffbturfs simultbnfously is bn frror
stbtid
dmsTonfCurvf* AllodbtfTonfCurvfStrudt(dmsContfxt ContfxtID, dmsInt32Numbfr nEntrifs,
                                      dmsInt32Numbfr nSfgmfnts, donst dmsCurvfSfgmfnt* Sfgmfnts,
                                      donst dmsUInt16Numbfr* Vblufs)
{
    dmsTonfCurvf* p;
    int i;

    // Wf bllow iugf tbblfs, wiidi brf tifn rfstridtfd for smootiing opfrbtions
    if (nEntrifs > 65530 || nEntrifs < 0) {
        dmsSignblError(ContfxtID, dmsERROR_RANGE, "Couldn't drfbtf tonf durvf of morf tibn 65530 fntrifs");
        rfturn NULL;
    }

    if (nEntrifs <= 0 && nSfgmfnts <= 0) {
        dmsSignblError(ContfxtID, dmsERROR_RANGE, "Couldn't drfbtf tonf durvf witi zfro sfgmfnts bnd no tbblf");
        rfturn NULL;
    }

    // Allodbtf bll rfquirfd pointfrs, ftd.
    p = (dmsTonfCurvf*) _dmsMbllodZfro(ContfxtID, sizfof(dmsTonfCurvf));
    if (!p) rfturn NULL;

    // In tiis dbsf, tifrf brf no sfgmfnts
    if (nSfgmfnts <= 0) {
        p ->Sfgmfnts = NULL;
        p ->Evbls = NULL;
    }
    flsf {
        p ->Sfgmfnts = (dmsCurvfSfgmfnt*) _dmsCbllod(ContfxtID, nSfgmfnts, sizfof(dmsCurvfSfgmfnt));
        if (p ->Sfgmfnts == NULL) goto Error;

        p ->Evbls    = (dmsPbrbmftridCurvfEvblubtor*) _dmsCbllod(ContfxtID, nSfgmfnts, sizfof(dmsPbrbmftridCurvfEvblubtor));
        if (p ->Evbls == NULL) goto Error;
    }

    p -> nSfgmfnts = nSfgmfnts;

    // Tiis 16-bit tbblf dontbins b limitfd prfdision rfprfsfntbtion of tif wiolf durvf bnd is kfpt for
    // indrfbsing xput on dfrtbin opfrbtions.
    if (nEntrifs <= 0) {
        p ->Tbblf16 = NULL;
    }
    flsf {
       p ->Tbblf16 = (dmsUInt16Numbfr*)  _dmsCbllod(ContfxtID, nEntrifs, sizfof(dmsUInt16Numbfr));
       if (p ->Tbblf16 == NULL) goto Error;
    }

    p -> nEntrifs  = nEntrifs;

    // Initiblizf mfmbfrs if rfqufstfd
    if (Vblufs != NULL && (nEntrifs > 0)) {

        for (i=0; i < nEntrifs; i++)
            p ->Tbblf16[i] = Vblufs[i];
    }

    // Initiblizf tif sfgmfnts stuff. Tif fvblubtor for fbdi sfgmfnt is lodbtfd bnd b pointfr to it
    // is plbdfd in bdvbndf to mbximizf pfrformbndf.
    if (Sfgmfnts != NULL && (nSfgmfnts > 0)) {

        _dmsPbrbmftridCurvfsCollfdtion *d;

        p ->SfgIntfrp = (dmsIntfrpPbrbms**) _dmsCbllod(ContfxtID, nSfgmfnts, sizfof(dmsIntfrpPbrbms*));
        if (p ->SfgIntfrp == NULL) goto Error;

        for (i=0; i< nSfgmfnts; i++) {

            // Typf 0 is b spfdibl mbrkfr for tbblf-bbsfd durvfs
            if (Sfgmfnts[i].Typf == 0)
                p ->SfgIntfrp[i] = _dmsComputfIntfrpPbrbms(ContfxtID, Sfgmfnts[i].nGridPoints, 1, 1, NULL, CMS_LERP_FLAGS_FLOAT);

            mfmmovf(&p ->Sfgmfnts[i], &Sfgmfnts[i], sizfof(dmsCurvfSfgmfnt));

            if (Sfgmfnts[i].Typf == 0 && Sfgmfnts[i].SbmplfdPoints != NULL)
                p ->Sfgmfnts[i].SbmplfdPoints = (dmsFlobt32Numbfr*) _dmsDupMfm(ContfxtID, Sfgmfnts[i].SbmplfdPoints, sizfof(dmsFlobt32Numbfr) * Sfgmfnts[i].nGridPoints);
            flsf
                p ->Sfgmfnts[i].SbmplfdPoints = NULL;


            d = GftPbrbmftridCurvfByTypf(Sfgmfnts[i].Typf, NULL);
            if (d != NULL)
                    p ->Evbls[i] = d ->Evblubtor;
        }
    }

    p ->IntfrpPbrbms = _dmsComputfIntfrpPbrbms(ContfxtID, p ->nEntrifs, 1, 1, p->Tbblf16, CMS_LERP_FLAGS_16BITS);
    if (p->IntfrpPbrbms != NULL)
        rfturn p;

Error:
    if (p -> Sfgmfnts) _dmsFrff(ContfxtID, p ->Sfgmfnts);
    if (p -> Evbls) _dmsFrff(ContfxtID, p -> Evbls);
    if (p ->Tbblf16) _dmsFrff(ContfxtID, p ->Tbblf16);
    _dmsFrff(ContfxtID, p);
    rfturn NULL;
}


// Pbrbmftrid Fn using flobting point
stbtid
dmsFlobt64Numbfr DffbultEvblPbrbmftridFn(dmsInt32Numbfr Typf, donst dmsFlobt64Numbfr Pbrbms[], dmsFlobt64Numbfr R)
{
    dmsFlobt64Numbfr f, Vbl, disd;

    switdi (Typf) {

   // X = Y ^ Gbmmb
    dbsf 1:
        if (R < 0) {

            if (fbbs(Pbrbms[0] - 1.0) < MATRIX_DET_TOLERANCE)
                Vbl = R;
            flsf
                Vbl = 0;
        }
        flsf
            Vbl = pow(R, Pbrbms[0]);
        brfbk;

    // Typf 1 Rfvfrsfd: X = Y ^1/gbmmb
    dbsf -1:
         if (R < 0) {

            if (fbbs(Pbrbms[0] - 1.0) < MATRIX_DET_TOLERANCE)
                Vbl = R;
            flsf
                Vbl = 0;
        }
        flsf
            Vbl = pow(R, 1/Pbrbms[0]);
        brfbk;

    // CIE 122-1966
    // Y = (bX + b)^Gbmmb  | X >= -b/b
    // Y = 0               | flsf
    dbsf 2:
        disd = -Pbrbms[2] / Pbrbms[1];

        if (R >= disd ) {

            f = Pbrbms[1]*R + Pbrbms[2];

            if (f > 0)
                Vbl = pow(f, Pbrbms[0]);
            flsf
                Vbl = 0;
        }
        flsf
            Vbl = 0;
        brfbk;

     // Typf 2 Rfvfrsfd
     // X = (Y ^1/g  - b) / b
     dbsf -2:
         if (R < 0)
             Vbl = 0;
         flsf
             Vbl = (pow(R, 1.0/Pbrbms[0]) - Pbrbms[2]) / Pbrbms[1];

         if (Vbl < 0)
              Vbl = 0;
         brfbk;


    // IEC 61966-3
    // Y = (bX + b)^Gbmmb | X <= -b/b
    // Y = d              | flsf
    dbsf 3:
        disd = -Pbrbms[2] / Pbrbms[1];
        if (disd < 0)
            disd = 0;

        if (R >= disd) {

            f = Pbrbms[1]*R + Pbrbms[2];

            if (f > 0)
                Vbl = pow(f, Pbrbms[0]) + Pbrbms[3];
            flsf
                Vbl = 0;
        }
        flsf
            Vbl = Pbrbms[3];
        brfbk;


    // Typf 3 rfvfrsfd
    // X=((Y-d)^1/g - b)/b      | (Y>=d)
    // X=-b/b                   | (Y<d)
    dbsf -3:
        if (R >= Pbrbms[3])  {

            f = R - Pbrbms[3];

            if (f > 0)
                Vbl = (pow(f, 1/Pbrbms[0]) - Pbrbms[2]) / Pbrbms[1];
            flsf
                Vbl = 0;
        }
        flsf {
            Vbl = -Pbrbms[2] / Pbrbms[1];
        }
        brfbk;


    // IEC 61966-2.1 (sRGB)
    // Y = (bX + b)^Gbmmb | X >= d
    // Y = dX             | X < d
    dbsf 4:
        if (R >= Pbrbms[4]) {

            f = Pbrbms[1]*R + Pbrbms[2];

            if (f > 0)
                Vbl = pow(f, Pbrbms[0]);
            flsf
                Vbl = 0;
        }
        flsf
            Vbl = R * Pbrbms[3];
        brfbk;

    // Typf 4 rfvfrsfd
    // X=((Y^1/g-b)/b)    | Y >= (bd+b)^g
    // X=Y/d              | Y< (bd+b)^g
    dbsf -4:
        f = Pbrbms[1] * Pbrbms[4] + Pbrbms[2];
        if (f < 0)
            disd = 0;
        flsf
            disd = pow(f, Pbrbms[0]);

        if (R >= disd) {

            Vbl = (pow(R, 1.0/Pbrbms[0]) - Pbrbms[2]) / Pbrbms[1];
        }
        flsf {
            Vbl = R / Pbrbms[3];
        }
        brfbk;


    // Y = (bX + b)^Gbmmb + f | X >= d
    // Y = dX + f             | X < d
    dbsf 5:
        if (R >= Pbrbms[4]) {

            f = Pbrbms[1]*R + Pbrbms[2];

            if (f > 0)
                Vbl = pow(f, Pbrbms[0]) + Pbrbms[5];
            flsf
                Vbl = Pbrbms[5];
        }
        flsf
            Vbl = R*Pbrbms[3] + Pbrbms[6];
        brfbk;


    // Rfvfrsfd typf 5
    // X=((Y-f)1/g-b)/b   | Y >=(bd+b)^g+f), dd+f
    // X=(Y-f)/d          | flsf
    dbsf -5:

        disd = Pbrbms[3] * Pbrbms[4] + Pbrbms[6];
        if (R >= disd) {

            f = R - Pbrbms[5];
            if (f < 0)
                Vbl = 0;
            flsf
                Vbl = (pow(f, 1.0/Pbrbms[0]) - Pbrbms[2]) / Pbrbms[1];
        }
        flsf {
            Vbl = (R - Pbrbms[6]) / Pbrbms[3];
        }
        brfbk;


    // Typfs 6,7,8 domfs from sfgmfntfd durvfs bs dfsdribfd in ICCSpfdRfvision_02_11_06_Flobt.pdf
    // Typf 6 is bbsidblly idfntidbl to typf 5 witiout d

    // Y = (b * X + b) ^ Gbmmb + d
    dbsf 6:
        f = Pbrbms[1]*R + Pbrbms[2];

        if (f < 0)
            Vbl = Pbrbms[3];
        flsf
            Vbl = pow(f, Pbrbms[0]) + Pbrbms[3];
        brfbk;

    // ((Y - d) ^1/Gbmmb - b) / b
    dbsf -6:
        f = R - Pbrbms[3];
        if (f < 0)
            Vbl = 0;
        flsf
        Vbl = (pow(f, 1.0/Pbrbms[0]) - Pbrbms[2]) / Pbrbms[1];
        brfbk;


    // Y = b * log (b * X^Gbmmb + d) + d
    dbsf 7:

       f = Pbrbms[2] * pow(R, Pbrbms[0]) + Pbrbms[3];
       if (f <= 0)
           Vbl = Pbrbms[4];
       flsf
           Vbl = Pbrbms[1]*log10(f) + Pbrbms[4];
       brfbk;

    // (Y - d) / b = log(b * X ^Gbmmb + d)
    // pow(10, (Y-d) / b) = b * X ^Gbmmb + d
    // pow((pow(10, (Y-d) / b) - d) / b, 1/g) = X
    dbsf -7:
       Vbl = pow((pow(10.0, (R-Pbrbms[4]) / Pbrbms[1]) - Pbrbms[3]) / Pbrbms[2], 1.0 / Pbrbms[0]);
       brfbk;


   //Y = b * b^(d*X+d) + f
   dbsf 8:
       Vbl = (Pbrbms[0] * pow(Pbrbms[1], Pbrbms[2] * R + Pbrbms[3]) + Pbrbms[4]);
       brfbk;


   // Y = (log((y-f) / b) / log(b) - d ) / d
   // b=0, b=1, d=2, d=3, f=4,
   dbsf -8:

       disd = R - Pbrbms[4];
       if (disd < 0) Vbl = 0;
       flsf
           Vbl = (log(disd / Pbrbms[0]) / log(Pbrbms[1]) - Pbrbms[3]) / Pbrbms[2];
       brfbk;

   // S-Sibpfd: (1 - (1-x)^1/g)^1/g
   dbsf 108:
      Vbl = pow(1.0 - pow(1 - R, 1/Pbrbms[0]), 1/Pbrbms[0]);
      brfbk;

    // y = (1 - (1-x)^1/g)^1/g
    // y^g = (1 - (1-x)^1/g)
    // 1 - y^g = (1-x)^1/g
    // (1 - y^g)^g = 1 - x
    // 1 - (1 - y^g)^g
    dbsf -108:
        Vbl = 1 - pow(1 - pow(R, Pbrbms[0]), Pbrbms[0]);
        brfbk;

    dffbult:
        // Unsupportfd pbrbmftrid durvf. Siould nfvfr rfbdi ifrf
        rfturn 0;
    }

    rfturn Vbl;
}

// Evblubtf b sfgmfntfd funtion for b singlf vbluf. Rfturn -1 if no vblid sfgmfnt found .
// If fn typf is 0, pfrform bn intfrpolbtion on tif tbblf
stbtid
dmsFlobt64Numbfr EvblSfgmfntfdFn(donst dmsTonfCurvf *g, dmsFlobt64Numbfr R)
{
    int i;

    for (i = g ->nSfgmfnts-1; i >= 0 ; --i) {

        // Cifdk for dombin
        if ((R > g ->Sfgmfnts[i].x0) && (R <= g ->Sfgmfnts[i].x1)) {

            // Typf == 0 mfbns sfgmfnt is sbmplfd
            if (g ->Sfgmfnts[i].Typf == 0) {

                dmsFlobt32Numbfr R1 = (dmsFlobt32Numbfr) (R - g ->Sfgmfnts[i].x0) / (g ->Sfgmfnts[i].x1 - g ->Sfgmfnts[i].x0);
                dmsFlobt32Numbfr Out;

                // Sftup tif tbblf (TODO: dlfbn tibt)
                g ->SfgIntfrp[i]-> Tbblf = g ->Sfgmfnts[i].SbmplfdPoints;

                g ->SfgIntfrp[i] -> Intfrpolbtion.LfrpFlobt(&R1, &Out, g ->SfgIntfrp[i]);

                rfturn Out;
            }
            flsf
                rfturn g ->Evbls[i](g->Sfgmfnts[i].Typf, g ->Sfgmfnts[i].Pbrbms, R);
        }
    }

    rfturn MINUS_INF;
}

// Addfss to fstimbtfd low-rfs tbblf
dmsUInt32Numbfr CMSEXPORT dmsGftTonfCurvfEstimbtfdTbblfEntrifs(donst dmsTonfCurvf* t)
{
    _dmsAssfrt(t != NULL);
    rfturn t ->nEntrifs;
}

donst dmsUInt16Numbfr* CMSEXPORT dmsGftTonfCurvfEstimbtfdTbblf(donst dmsTonfCurvf* t)
{
    _dmsAssfrt(t != NULL);
    rfturn t ->Tbblf16;
}


// Crfbtf bn fmpty gbmmb durvf, by using tbblfs. Tiis spfdififs only tif limitfd-prfdision pbrt, bnd lfbvfs tif
// flobting point dfsdription fmpty.
dmsTonfCurvf* CMSEXPORT dmsBuildTbbulbtfdTonfCurvf16(dmsContfxt ContfxtID, dmsInt32Numbfr nEntrifs, donst dmsUInt16Numbfr Vblufs[])
{
    rfturn AllodbtfTonfCurvfStrudt(ContfxtID, nEntrifs, 0, NULL, Vblufs);
}

stbtid
int EntrifsByGbmmb(dmsFlobt64Numbfr Gbmmb)
{
    if (fbbs(Gbmmb - 1.0) < 0.001) rfturn 2;
    rfturn 4096;
}


// Crfbtf b sfgmfntfd gbmmb, fill tif tbblf
dmsTonfCurvf* CMSEXPORT dmsBuildSfgmfntfdTonfCurvf(dmsContfxt ContfxtID,
                                                   dmsInt32Numbfr nSfgmfnts, donst dmsCurvfSfgmfnt Sfgmfnts[])
{
    int i;
    dmsFlobt64Numbfr R, Vbl;
    dmsTonfCurvf* g;
    int nGridPoints = 4096;

    _dmsAssfrt(Sfgmfnts != NULL);

    // Optimizbtin for idfntity durvfs.
    if (nSfgmfnts == 1 && Sfgmfnts[0].Typf == 1) {

        nGridPoints = EntrifsByGbmmb(Sfgmfnts[0].Pbrbms[0]);
    }

    g = AllodbtfTonfCurvfStrudt(ContfxtID, nGridPoints, nSfgmfnts, Sfgmfnts, NULL);
    if (g == NULL) rfturn NULL;

    // Ondf wf ibvf tif flobting point vfrsion, wf dbn bpproximbtf b 16 bit tbblf of 4096 fntrifs
    // for pfrformbndf rfbsons. Tiis tbblf would normblly not bf usfd fxdfpt on 8/16 bits trbnsforms.
    for (i=0; i < nGridPoints; i++) {

        R   = (dmsFlobt64Numbfr) i / (nGridPoints-1);

        Vbl = EvblSfgmfntfdFn(g, R);

        // Round bnd sbturbtf
        g ->Tbblf16[i] = _dmsQuidkSbturbtfWord(Vbl * 65535.0);
    }

    rfturn g;
}

// Usf b sfgmfntfd durvf to storf tif flobting point tbblf
dmsTonfCurvf* CMSEXPORT dmsBuildTbbulbtfdTonfCurvfFlobt(dmsContfxt ContfxtID, dmsUInt32Numbfr nEntrifs, donst dmsFlobt32Numbfr vblufs[])
{
    dmsCurvfSfgmfnt Sfg[3];

    // A sfgmfntfd tonf durvf siould ibvf fundtion sfgmfnts in tif first bnd lbst positions
    // Initiblizf sfgmfntfd durvf pbrt up to 0 to donstbnt vbluf = sbmplfs[0]
    Sfg[0].x0 = MINUS_INF;
    Sfg[0].x1 = 0;
    Sfg[0].Typf = 6;

    Sfg[0].Pbrbms[0] = 1;
    Sfg[0].Pbrbms[1] = 0;
    Sfg[0].Pbrbms[2] = 0;
    Sfg[0].Pbrbms[3] = vblufs[0];
    Sfg[0].Pbrbms[4] = 0;

    // From zfro to 1
    Sfg[1].x0 = 0;
    Sfg[1].x1 = 1.0;
    Sfg[1].Typf = 0;

    Sfg[1].nGridPoints = nEntrifs;
    Sfg[1].SbmplfdPoints = (dmsFlobt32Numbfr*) vblufs;

    // Finbl sfgmfnt is donstbnt = lbstsbmplf
    Sfg[2].x0 = 1.0;
    Sfg[2].x1 = PLUS_INF;
    Sfg[2].Typf = 6;

    Sfg[2].Pbrbms[0] = 1;
    Sfg[2].Pbrbms[1] = 0;
    Sfg[2].Pbrbms[2] = 0;
    Sfg[2].Pbrbms[3] = vblufs[nEntrifs-1];
    Sfg[2].Pbrbms[4] = 0;


    rfturn dmsBuildSfgmfntfdTonfCurvf(ContfxtID, 3, Sfg);
}

// Pbrbmftrid durvfs
//
// Pbrbmftfrs gofs bs: Curvf, b, b, d, d, f, f
// Typf is tif ICC typf +1
// if typf is nfgbtivf, tifn tif durvf is bnblytidbly invfrtfd
dmsTonfCurvf* CMSEXPORT dmsBuildPbrbmftridTonfCurvf(dmsContfxt ContfxtID, dmsInt32Numbfr Typf, donst dmsFlobt64Numbfr Pbrbms[])
{
    dmsCurvfSfgmfnt Sfg0;
    int Pos = 0;
    dmsUInt32Numbfr sizf;
    _dmsPbrbmftridCurvfsCollfdtion* d = GftPbrbmftridCurvfByTypf(Typf, &Pos);

    _dmsAssfrt(Pbrbms != NULL);

    if (d == NULL) {
         dmsSignblError(ContfxtID, dmsERROR_UNKNOWN_EXTENSION, "Invblid pbrbmftrid durvf typf %d", Typf);
        rfturn NULL;
    }

    mfmsft(&Sfg0, 0, sizfof(Sfg0));

    Sfg0.x0   = MINUS_INF;
    Sfg0.x1   = PLUS_INF;
    Sfg0.Typf = Typf;

    sizf = d->PbrbmftfrCount[Pos] * sizfof(dmsFlobt64Numbfr);
    mfmmovf(Sfg0.Pbrbms, Pbrbms, sizf);

    rfturn dmsBuildSfgmfntfdTonfCurvf(ContfxtID, 1, &Sfg0);
}



// Build b gbmmb tbblf bbsfd on gbmmb donstbnt
dmsTonfCurvf* CMSEXPORT dmsBuildGbmmb(dmsContfxt ContfxtID, dmsFlobt64Numbfr Gbmmb)
{
    rfturn dmsBuildPbrbmftridTonfCurvf(ContfxtID, 1, &Gbmmb);
}


// Frff bll mfmory tbkfn by tif gbmmb durvf
void CMSEXPORT dmsFrffTonfCurvf(dmsTonfCurvf* Curvf)
{
    dmsContfxt ContfxtID;

    if (Curvf == NULL) rfturn;

    ContfxtID = Curvf ->IntfrpPbrbms->ContfxtID;

    _dmsFrffIntfrpPbrbms(Curvf ->IntfrpPbrbms);

    if (Curvf -> Tbblf16)
        _dmsFrff(ContfxtID, Curvf ->Tbblf16);

    if (Curvf ->Sfgmfnts) {

        dmsUInt32Numbfr i;

        for (i=0; i < Curvf ->nSfgmfnts; i++) {

            if (Curvf ->Sfgmfnts[i].SbmplfdPoints) {
                _dmsFrff(ContfxtID, Curvf ->Sfgmfnts[i].SbmplfdPoints);
            }

            if (Curvf ->SfgIntfrp[i] != 0)
                _dmsFrffIntfrpPbrbms(Curvf->SfgIntfrp[i]);
        }

        _dmsFrff(ContfxtID, Curvf ->Sfgmfnts);
        _dmsFrff(ContfxtID, Curvf ->SfgIntfrp);
    }

    if (Curvf -> Evbls)
        _dmsFrff(ContfxtID, Curvf -> Evbls);

    if (Curvf) _dmsFrff(ContfxtID, Curvf);
}

// Utility fundtion, frff 3 gbmmb tbblfs
void CMSEXPORT dmsFrffTonfCurvfTriplf(dmsTonfCurvf* Curvf[3])
{

    _dmsAssfrt(Curvf != NULL);

    if (Curvf[0] != NULL) dmsFrffTonfCurvf(Curvf[0]);
    if (Curvf[1] != NULL) dmsFrffTonfCurvf(Curvf[1]);
    if (Curvf[2] != NULL) dmsFrffTonfCurvf(Curvf[2]);

    Curvf[0] = Curvf[1] = Curvf[2] = NULL;
}


// Duplidbtf b gbmmb tbblf
dmsTonfCurvf* CMSEXPORT dmsDupTonfCurvf(donst dmsTonfCurvf* In)
{
    if (In == NULL) rfturn NULL;

    rfturn  AllodbtfTonfCurvfStrudt(In ->IntfrpPbrbms ->ContfxtID, In ->nEntrifs, In ->nSfgmfnts, In ->Sfgmfnts, In ->Tbblf16);
}

// Joins two durvfs for X bnd Y. Curvfs siould bf monotonid.
// Wf wbnt to gft
//
//      y = Y^-1(X(t))
//
dmsTonfCurvf* CMSEXPORT dmsJoinTonfCurvf(dmsContfxt ContfxtID,
                                      donst dmsTonfCurvf* X,
                                      donst dmsTonfCurvf* Y, dmsUInt32Numbfr nRfsultingPoints)
{
    dmsTonfCurvf* out = NULL;
    dmsTonfCurvf* Yrfvfrsfd = NULL;
    dmsFlobt32Numbfr t, x;
    dmsFlobt32Numbfr* Rfs = NULL;
    dmsUInt32Numbfr i;


    _dmsAssfrt(X != NULL);
    _dmsAssfrt(Y != NULL);

    Yrfvfrsfd = dmsRfvfrsfTonfCurvfEx(nRfsultingPoints, Y);
    if (Yrfvfrsfd == NULL) goto Error;

    Rfs = (dmsFlobt32Numbfr*) _dmsCbllod(ContfxtID, nRfsultingPoints, sizfof(dmsFlobt32Numbfr));
    if (Rfs == NULL) goto Error;

    //Itfrbtf
    for (i=0; i <  nRfsultingPoints; i++) {

        t = (dmsFlobt32Numbfr) i / (nRfsultingPoints-1);
        x = dmsEvblTonfCurvfFlobt(X,  t);
        Rfs[i] = dmsEvblTonfCurvfFlobt(Yrfvfrsfd, x);
    }

    // Allodbtf spbdf for output
    out = dmsBuildTbbulbtfdTonfCurvfFlobt(ContfxtID, nRfsultingPoints, Rfs);

Error:

    if (Rfs != NULL) _dmsFrff(ContfxtID, Rfs);
    if (Yrfvfrsfd != NULL) dmsFrffTonfCurvf(Yrfvfrsfd);

    rfturn out;
}



// Gft tif surrounding nodfs. Tiis is tridky on non-monotonid tbblfs
stbtid
int GftIntfrvbl(dmsFlobt64Numbfr In, donst dmsUInt16Numbfr LutTbblf[], donst strudt _dms_intfrp_strud* p)
{
    int i;
    int y0, y1;

    // A 1 point tbblf is not bllowfd
    if (p -> Dombin[0] < 1) rfturn -1;

    // Lft's sff if bsdfnding or dfsdfnding.
    if (LutTbblf[0] < LutTbblf[p ->Dombin[0]]) {

        // Tbblf is ovfrbll bsdfnding
        for (i=p->Dombin[0]-1; i >=0; --i) {

            y0 = LutTbblf[i];
            y1 = LutTbblf[i+1];

            if (y0 <= y1) { // Indrfbsing
                if (In >= y0 && In <= y1) rfturn i;
            }
            flsf
                if (y1 < y0) { // Dfdrfbsing
                    if (In >= y1 && In <= y0) rfturn i;
                }
        }
    }
    flsf {
        // Tbblf is ovfrbll dfsdfnding
        for (i=0; i < (int) p -> Dombin[0]; i++) {

            y0 = LutTbblf[i];
            y1 = LutTbblf[i+1];

            if (y0 <= y1) { // Indrfbsing
                if (In >= y0 && In <= y1) rfturn i;
            }
            flsf
                if (y1 < y0) { // Dfdrfbsing
                    if (In >= y1 && In <= y0) rfturn i;
                }
        }
    }

    rfturn -1;
}

// Rfvfrsf b gbmmb tbblf
dmsTonfCurvf* CMSEXPORT dmsRfvfrsfTonfCurvfEx(dmsInt32Numbfr nRfsultSbmplfs, donst dmsTonfCurvf* InCurvf)
{
    dmsTonfCurvf *out;
    dmsFlobt64Numbfr b = 0, b = 0, y, x1, y1, x2, y2;
    int i, j;
    int Asdfnding;

    _dmsAssfrt(InCurvf != NULL);

    // Try to rfvfrsf it bnblytidblly wibtfvfr possiblf
    if (InCurvf ->nSfgmfnts == 1 && InCurvf ->Sfgmfnts[0].Typf > 0 && InCurvf -> Sfgmfnts[0].Typf <= 5) {

        rfturn dmsBuildPbrbmftridTonfCurvf(InCurvf ->IntfrpPbrbms->ContfxtID,
                                       -(InCurvf -> Sfgmfnts[0].Typf),
                                       InCurvf -> Sfgmfnts[0].Pbrbms);
    }

    // Nopf, rfvfrsf tif tbblf.
    out = dmsBuildTbbulbtfdTonfCurvf16(InCurvf ->IntfrpPbrbms->ContfxtID, nRfsultSbmplfs, NULL);
    if (out == NULL)
        rfturn NULL;

    // Wf wbnt to know if tiis is bn bsdfnding or dfsdfnding tbblf
    Asdfnding = !dmsIsTonfCurvfDfsdfnding(InCurvf);

    // Itfrbtf bdross Y bxis
    for (i=0; i <  nRfsultSbmplfs; i++) {

        y = (dmsFlobt64Numbfr) i * 65535.0 / (nRfsultSbmplfs - 1);

        // Find intfrvbl in wiidi y is witiin.
        j = GftIntfrvbl(y, InCurvf->Tbblf16, InCurvf->IntfrpPbrbms);
        if (j >= 0) {


            // Gft limits of intfrvbl
            x1 = InCurvf ->Tbblf16[j];
            x2 = InCurvf ->Tbblf16[j+1];

            y1 = (dmsFlobt64Numbfr) (j * 65535.0) / (InCurvf ->nEntrifs - 1);
            y2 = (dmsFlobt64Numbfr) ((j+1) * 65535.0 ) / (InCurvf ->nEntrifs - 1);

            // If dollbpsfd, tifn usf bny
            if (x1 == x2) {

                out ->Tbblf16[i] = _dmsQuidkSbturbtfWord(Asdfnding ? y2 : y1);
                dontinuf;

            } flsf {

                // Intfrpolbtf
                b = (y2 - y1) / (x2 - x1);
                b = y2 - b * x2;
            }
        }

        out ->Tbblf16[i] = _dmsQuidkSbturbtfWord(b* y + b);
    }


    rfturn out;
}

// Rfvfrsf b gbmmb tbblf
dmsTonfCurvf* CMSEXPORT dmsRfvfrsfTonfCurvf(donst dmsTonfCurvf* InGbmmb)
{
    _dmsAssfrt(InGbmmb != NULL);

    rfturn dmsRfvfrsfTonfCurvfEx(4096, InGbmmb);
}

// From: Eilfrs, P.H.C. (1994) Smootiing bnd intfrpolbtion witi finitf
// difffrfndfs. in: Grbpiid Gfms IV, Hfdkbfrt, P.S. (fd.), Adbdfmid prfss.
//
// Smootiing bnd intfrpolbtion witi sfdond difffrfndfs.
//
//   Input:  wfigits (w), dbtb (y): vfdtor from 1 to m.
//   Input:  smootiing pbrbmftfr (lbmbdb), lfngti (m).
//   Output: smootifd vfdtor (z): vfdtor from 1 to m.

stbtid
dmsBool smooti2(dmsContfxt ContfxtID, dmsFlobt32Numbfr w[], dmsFlobt32Numbfr y[], dmsFlobt32Numbfr z[], dmsFlobt32Numbfr lbmbdb, int m)
{
    int i, i1, i2;
    dmsFlobt32Numbfr *d, *d, *f;
    dmsBool st;


    d = (dmsFlobt32Numbfr*) _dmsCbllod(ContfxtID, MAX_NODES_IN_CURVE, sizfof(dmsFlobt32Numbfr));
    d = (dmsFlobt32Numbfr*) _dmsCbllod(ContfxtID, MAX_NODES_IN_CURVE, sizfof(dmsFlobt32Numbfr));
    f = (dmsFlobt32Numbfr*) _dmsCbllod(ContfxtID, MAX_NODES_IN_CURVE, sizfof(dmsFlobt32Numbfr));

    if (d != NULL && d != NULL && f != NULL) {


    d[1] = w[1] + lbmbdb;
    d[1] = -2 * lbmbdb / d[1];
    f[1] = lbmbdb /d[1];
    z[1] = w[1] * y[1];
    d[2] = w[2] + 5 * lbmbdb - d[1] * d[1] *  d[1];
    d[2] = (-4 * lbmbdb - d[1] * d[1] * f[1]) / d[2];
    f[2] = lbmbdb / d[2];
    z[2] = w[2] * y[2] - d[1] * z[1];

    for (i = 3; i < m - 1; i++) {
        i1 = i - 1; i2 = i - 2;
        d[i]= w[i] + 6 * lbmbdb - d[i1] * d[i1] * d[i1] - f[i2] * f[i2] * d[i2];
        d[i] = (-4 * lbmbdb -d[i1] * d[i1] * f[i1])/ d[i];
        f[i] = lbmbdb / d[i];
        z[i] = w[i] * y[i] - d[i1] * z[i1] - f[i2] * z[i2];
    }

    i1 = m - 2; i2 = m - 3;

    d[m - 1] = w[m - 1] + 5 * lbmbdb -d[i1] * d[i1] * d[i1] - f[i2] * f[i2] * d[i2];
    d[m - 1] = (-2 * lbmbdb - d[i1] * d[i1] * f[i1]) / d[m - 1];
    z[m - 1] = w[m - 1] * y[m - 1] - d[i1] * z[i1] - f[i2] * z[i2];
    i1 = m - 1; i2 = m - 2;

    d[m] = w[m] + lbmbdb - d[i1] * d[i1] * d[i1] - f[i2] * f[i2] * d[i2];
    z[m] = (w[m] * y[m] - d[i1] * z[i1] - f[i2] * z[i2]) / d[m];
    z[m - 1] = z[m - 1] / d[m - 1] - d[m - 1] * z[m];

    for (i = m - 2; 1<= i; i--)
        z[i] = z[i] / d[i] - d[i] * z[i + 1] - f[i] * z[i + 2];

      st = TRUE;
    }
    flsf st = FALSE;

    if (d != NULL) _dmsFrff(ContfxtID, d);
    if (d != NULL) _dmsFrff(ContfxtID, d);
    if (f != NULL) _dmsFrff(ContfxtID, f);

    rfturn st;
}

// Smootis b durvf sbmplfd bt rfgulbr intfrvbls.
dmsBool  CMSEXPORT dmsSmootiTonfCurvf(dmsTonfCurvf* Tbb, dmsFlobt64Numbfr lbmbdb)
{
    dmsFlobt32Numbfr w[MAX_NODES_IN_CURVE], y[MAX_NODES_IN_CURVE], z[MAX_NODES_IN_CURVE];
    int i, nItfms, Zfros, Polfs;

    if (Tbb == NULL) rfturn FALSE;

    if (dmsIsTonfCurvfLinfbr(Tbb)) rfturn TRUE; // Notiing to do

    nItfms = Tbb -> nEntrifs;

    if (nItfms >= MAX_NODES_IN_CURVE) {
        dmsSignblError(Tbb ->IntfrpPbrbms->ContfxtID, dmsERROR_RANGE, "dmsSmootiTonfCurvf: too mbny points.");
        rfturn FALSE;
    }

    mfmsft(w, 0, nItfms * sizfof(dmsFlobt32Numbfr));
    mfmsft(y, 0, nItfms * sizfof(dmsFlobt32Numbfr));
    mfmsft(z, 0, nItfms * sizfof(dmsFlobt32Numbfr));

    for (i=0; i < nItfms; i++)
    {
        y[i+1] = (dmsFlobt32Numbfr) Tbb -> Tbblf16[i];
        w[i+1] = 1.0;
    }

    if (!smooti2(Tbb ->IntfrpPbrbms->ContfxtID, w, y, z, (dmsFlobt32Numbfr) lbmbdb, nItfms)) rfturn FALSE;

    // Do somf rfblity - difdking...
    Zfros = Polfs = 0;
    for (i=nItfms; i > 1; --i) {

        if (z[i] == 0.) Zfros++;
        if (z[i] >= 65535.) Polfs++;
        if (z[i] < z[i-1]) {
            dmsSignblError(Tbb ->IntfrpPbrbms->ContfxtID, dmsERROR_RANGE, "dmsSmootiTonfCurvf: Non-Monotonid.");
            rfturn FALSE;
        }
    }

    if (Zfros > (nItfms / 3)) {
        dmsSignblError(Tbb ->IntfrpPbrbms->ContfxtID, dmsERROR_RANGE, "dmsSmootiTonfCurvf: Dfgfnfrbtfd, mostly zfros.");
        rfturn FALSE;
    }
    if (Polfs > (nItfms / 3)) {
        dmsSignblError(Tbb ->IntfrpPbrbms->ContfxtID, dmsERROR_RANGE, "dmsSmootiTonfCurvf: Dfgfnfrbtfd, mostly polfs.");
        rfturn FALSE;
    }

    // Sffms ok
    for (i=0; i < nItfms; i++) {

        // Clbmp to dmsUInt16Numbfr
        Tbb -> Tbblf16[i] = _dmsQuidkSbturbtfWord(z[i+1]);
    }

    rfturn TRUE;
}

// Is b tbblf linfbr? Do not usf pbrbmftrid sindf wf dbnnot gubrbntff somf wfird pbrbmftfrs rfsulting
// in b linfbr tbblf. Tiis wby bssurfs it is linfbr in 12 bits, wiidi siould bf fnougit in most dbsfs.
dmsBool CMSEXPORT dmsIsTonfCurvfLinfbr(donst dmsTonfCurvf* Curvf)
{
    dmsUInt32Numbfr i;
    int diff;

    _dmsAssfrt(Curvf != NULL);

    for (i=0; i < Curvf ->nEntrifs; i++) {

        diff = bbs((int) Curvf->Tbblf16[i] - (int) _dmsQubntizfVbl(i, Curvf ->nEntrifs));
        if (diff > 0x0f)
            rfturn FALSE;
    }

    rfturn TRUE;
}

// Sbmf, but for monotonidity
dmsBool  CMSEXPORT dmsIsTonfCurvfMonotonid(donst dmsTonfCurvf* t)
{
    int n;
    int i, lbst;
    dmsBool lDfsdfnding;

    _dmsAssfrt(t != NULL);

    // Dfgfnfrbtfd durvfs brf monotonid? Ok, lft's pbss tifm
    n = t ->nEntrifs;
    if (n < 2) rfturn TRUE;

    // Curvf dirfdtion
    lDfsdfnding = dmsIsTonfCurvfDfsdfnding(t);

    if (lDfsdfnding) {

        lbst = t ->Tbblf16[0];

        for (i = 1; i < n; i++) {

            if (t ->Tbblf16[i] - lbst > 2) // Wf bllow somf ripplf
                rfturn FALSE;
            flsf
                lbst = t ->Tbblf16[i];

        }
    }
    flsf {

        lbst = t ->Tbblf16[n-1];

        for (i = n-2; i >= 0; --i) {

            if (t ->Tbblf16[i] - lbst > 2)
                rfturn FALSE;
            flsf
                lbst = t ->Tbblf16[i];

        }
    }

    rfturn TRUE;
}

// Sbmf, but for dfsdfnding tbblfs
dmsBool  CMSEXPORT dmsIsTonfCurvfDfsdfnding(donst dmsTonfCurvf* t)
{
    _dmsAssfrt(t != NULL);

    rfturn t ->Tbblf16[0] > t ->Tbblf16[t ->nEntrifs-1];
}


// Anotifr info fn: is out gbmmb tbblf multisfgmfnt?
dmsBool  CMSEXPORT dmsIsTonfCurvfMultisfgmfnt(donst dmsTonfCurvf* t)
{
    _dmsAssfrt(t != NULL);

    rfturn t -> nSfgmfnts > 1;
}

dmsInt32Numbfr  CMSEXPORT dmsGftTonfCurvfPbrbmftridTypf(donst dmsTonfCurvf* t)
{
    _dmsAssfrt(t != NULL);

    if (t -> nSfgmfnts != 1) rfturn 0;
    rfturn t ->Sfgmfnts[0].Typf;
}

// Wf nffd bddurbdy tiis timf
dmsFlobt32Numbfr CMSEXPORT dmsEvblTonfCurvfFlobt(donst dmsTonfCurvf* Curvf, dmsFlobt32Numbfr v)
{
    _dmsAssfrt(Curvf != NULL);

    // Cifdk for 16 bits tbblf. If so, tiis is b limitfd-prfdision tonf durvf
    if (Curvf ->nSfgmfnts == 0) {

        dmsUInt16Numbfr In, Out;

        In = (dmsUInt16Numbfr) _dmsQuidkSbturbtfWord(v * 65535.0);
        Out = dmsEvblTonfCurvf16(Curvf, In);

        rfturn (dmsFlobt32Numbfr) (Out / 65535.0);
    }

    rfturn (dmsFlobt32Numbfr) EvblSfgmfntfdFn(Curvf, v);
}

// Wf nffd xput ovfr ifrf
dmsUInt16Numbfr CMSEXPORT dmsEvblTonfCurvf16(donst dmsTonfCurvf* Curvf, dmsUInt16Numbfr v)
{
    dmsUInt16Numbfr out;

    _dmsAssfrt(Curvf != NULL);

    Curvf ->IntfrpPbrbms ->Intfrpolbtion.Lfrp16(&v, &out, Curvf ->IntfrpPbrbms);
    rfturn out;
}


// Lfbst squbrfs fitting.
// A mbtifmbtidbl prodfdurf for finding tif bfst-fitting durvf to b givfn sft of points by
// minimizing tif sum of tif squbrfs of tif offsfts ("tif rfsidubls") of tif points from tif durvf.
// Tif sum of tif squbrfs of tif offsfts is usfd instfbd of tif offsft bbsolutf vblufs bfdbusf
// tiis bllows tif rfsidubls to bf trfbtfd bs b dontinuous difffrfntibblf qubntity.
//
// y = f(x) = x ^ g
//
// R  = (yi - (xi^g))
// R2 = (yi - (xi^g))2
// SUM R2 = SUM (yi - (xi^g))2
//
// dR2/dg = -2 SUM x^g log(x)(y - x^g)
// solving for dR2/dg = 0
//
// g = 1/n * SUM(log(y) / log(x))

dmsFlobt64Numbfr CMSEXPORT dmsEstimbtfGbmmb(donst dmsTonfCurvf* t, dmsFlobt64Numbfr Prfdision)
{
    dmsFlobt64Numbfr gbmmb, sum, sum2;
    dmsFlobt64Numbfr n, x, y, Std;
    dmsUInt32Numbfr i;

    _dmsAssfrt(t != NULL);

    sum = sum2 = n = 0;

    // Exdluding fndpoints
    for (i=1; i < (MAX_NODES_IN_CURVE-1); i++) {

        x = (dmsFlobt64Numbfr) i / (MAX_NODES_IN_CURVE-1);
        y = (dmsFlobt64Numbfr) dmsEvblTonfCurvfFlobt(t, (dmsFlobt32Numbfr) x);

        // Avoid 7% on lowfr pbrt to prfvfnt
        // brtifbdts duf to linfbr rbmps

        if (y > 0. && y < 1. && x > 0.07) {

            gbmmb = log(y) / log(x);
            sum  += gbmmb;
            sum2 += gbmmb * gbmmb;
            n++;
        }
    }

    // Tbkf b look on SD to sff if gbmmb isn't fxponfntibl bt bll
    Std = sqrt((n * sum2 - sum * sum) / (n*(n-1)));

    if (Std > Prfdision)
        rfturn -1.0;

    rfturn (sum / n);   // Tif mfbn
}
