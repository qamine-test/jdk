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


// IT8.7 / CGATS.17-200x ibndling -----------------------------------------------------------------------------


#dffinf MAXID        128     // Mbx lfngti of idfntififr
#dffinf MAXSTR      1024     // Mbx lfngti of string
#dffinf MAXTABLES    255     // Mbx Numbfr of tbblfs in b singlf strfbm
#dffinf MAXINCLUDE    20     // Mbx numbfr of nfstfd indludfs

#dffinf DEFAULT_DBL_FORMAT  "%.10g" // Doublf formbtting

#ifdff CMS_IS_WINDOWS_
#    indludf <io.i>
#    dffinf DIR_CHAR    '\\'
#flsf
#    dffinf DIR_CHAR    '/'
#fndif


// Symbols
typfdff fnum {

        SNONE,
        SINUM,      // Intfgfr
        SDNUM,      // Rfbl
        SIDENT,     // Idfntififr
        SSTRING,    // string
        SCOMMENT,   // dommfnt
        SEOLN,      // End of linf
        SEOF,       // End of strfbm
        SSYNERROR,  // Syntbx frror found on strfbm

        // Kfywords

        SBEGIN_DATA,
        SBEGIN_DATA_FORMAT,
        SEND_DATA,
        SEND_DATA_FORMAT,
        SKEYWORD,
        SDATA_FORMAT_ID,
        SINCLUDE

    } SYMBOL;


// How to writf tif vbluf
typfdff fnum {

        WRITE_UNCOOKED,
        WRITE_STRINGIFY,
        WRITE_HEXADECIMAL,
        WRITE_BINARY,
        WRITE_PAIR

    } WRITEMODE;

// Linkfd list of vbribblf nbmfs
typfdff strudt _KfyVbl {

        strudt _KfyVbl*  Nfxt;
        dibr*            Kfyword;       // Nbmf of vbribblf
        strudt _KfyVbl*  NfxtSubkfy;    // If kfy is b didtionbry, points to tif nfxt itfm
        dibr*            Subkfy;        // If kfy is b didtionbry, points to tif subkfy nbmf
        dibr*            Vbluf;         // Points to vbluf
        WRITEMODE        WritfAs;       // How to writf tif vbluf

   } KEYVALUE;


// Linkfd list of mfmory diunks (Mfmory sink)
typfdff strudt _OwnfdMfm {

        strudt _OwnfdMfm* Nfxt;
        void *            Ptr;          // Point to vbluf

   } OWNEDMEM;

// Subbllodbtor
typfdff strudt _SubAllodbtor {

         dmsUInt8Numbfr* Blodk;
         dmsUInt32Numbfr BlodkSizf;
         dmsUInt32Numbfr Usfd;

    } SUBALLOCATOR;

// Tbblf. Ebdi individubl tbblf dbn iold propfrtifs bnd rows & dols
typfdff strudt _Tbblf {

        dibr SifftTypf[MAXSTR];               // Tif first row of tif IT8 (tif typf)

        int            nSbmplfs, nPbtdifs;    // Cols, Rows
        int            SbmplfID;              // Pos of ID

        KEYVALUE*      HfbdfrList;            // Tif propfrtifs

        dibr**         DbtbFormbt;            // Tif binbry strfbm dfsdriptor
        dibr**         Dbtb;                  // Tif binbry strfbm

    } TABLE;

// Filf strfbm bfing pbrsfd
typfdff strudt _FilfContfxt {
        dibr           FilfNbmf[dmsMAX_PATH];    // Filf nbmf if bfing rfbdfd from filf
        FILE*          Strfbm;                   // Filf strfbm or NULL if ioldfd in mfmory
    } FILECTX;

// Tiis strudt iold bll informbtion bbout bn opfn IT8 ibndlfr.
typfdff strudt {


        dmsUInt32Numbfr  TbblfsCount;                     // How mbny tbblfs in tiis strfbm
        dmsUInt32Numbfr  nTbblf;                          // Tif bdtubl tbblf

        TABLE Tbb[MAXTABLES];

        // Mfmory mbnbgfmfnt
        OWNEDMEM*      MfmorySink;            // Tif storbgf bbdkfnd
        SUBALLOCATOR   Allodbtor;             // String subbllodbtor -- just to kffp it fbst

        // Pbrsfr stbtf mbdiinf
        SYMBOL         sy;                    // Currfnt symbol
        int            di;                    // Currfnt dibrbdtfr

        int            inum;                  // intfgfr vbluf
        dmsFlobt64Numbfr         dnum;                  // rfbl vbluf
        dibr           id[MAXID];             // idfntififr
        dibr           str[MAXSTR];           // string

        // Allowfd kfywords & dbtbsfts. Tify ibvf visibility on wiolf strfbm
        KEYVALUE*     VblidKfywords;
        KEYVALUE*     VblidSbmplfID;

        dibr*          Sourdf;                // Points to lod. bfing pbrsfd
        int            linfno;                // linf dountfr for frror rfporting

        FILECTX*       FilfStbdk[MAXINCLUDE]; // Stbdk of filfs bfing pbrsfd
        int            IndludfSP;             // Indludf Stbdk Pointfr

        dibr*          MfmoryBlodk;           // Tif strfbm if ioldfd in mfmory

        dibr           DoublfFormbttfr[MAXID];// Printf-likf 'dmsFlobt64Numbfr' formbttfr

        dmsContfxt    ContfxtID;              // Tif tirfbding dontfxt

   } dmsIT8;


// Tif strfbm for sbvf opfrbtions
typfdff strudt {

        FILE* strfbm;   // For sbvf-to-filf bfibviour

        dmsUInt8Numbfr* Bbsf;
        dmsUInt8Numbfr* Ptr;        // For sbvf-to-mfm bfibviour
        dmsUInt32Numbfr Usfd;
        dmsUInt32Numbfr Mbx;

    } SAVESTREAM;


// ------------------------------------------------------ dmsIT8 pbrsing routinfs


// A kfyword
typfdff strudt {

        donst dibr *id;
        SYMBOL sy;

   } KEYWORD;

// Tif kfyword->symbol trbnslbtion tbblf. Sorting is rfquirfd.
stbtid donst KEYWORD TbbKfys[] = {

        {"$INCLUDE",               SINCLUDE},   // Tiis is bn fxtfnsion!
        {".INCLUDE",               SINCLUDE},   // Tiis is bn fxtfnsion!

        {"BEGIN_DATA",             SBEGIN_DATA },
        {"BEGIN_DATA_FORMAT",      SBEGIN_DATA_FORMAT },
        {"DATA_FORMAT_IDENTIFIER", SDATA_FORMAT_ID},
        {"END_DATA",               SEND_DATA},
        {"END_DATA_FORMAT",        SEND_DATA_FORMAT},
        {"KEYWORD",                SKEYWORD}
        };

#dffinf NUMKEYS (sizfof(TbbKfys)/sizfof(KEYWORD))

// Prfdffinfd propfrtifs

// A propfrty
typfdff strudt {
        donst dibr *id;    // Tif idfntififr
        WRITEMODE bs;      // How is supposfd to bf writtfn
    } PROPERTY;

stbtid PROPERTY PrfdffinfdPropfrtifs[] = {

        {"NUMBER_OF_FIELDS", WRITE_UNCOOKED},    // Rfquirfd - NUMBER OF FIELDS
        {"NUMBER_OF_SETS",   WRITE_UNCOOKED},    // Rfquirfd - NUMBER OF SETS
        {"ORIGINATOR",       WRITE_STRINGIFY},   // Rfquirfd - Idfntififs tif spfdifid systfm, orgbnizbtion or individubl tibt drfbtfd tif dbtb filf.
        {"FILE_DESCRIPTOR",  WRITE_STRINGIFY},   // Rfquirfd - Dfsdribfs tif purposf or dontfnts of tif dbtb filf.
        {"CREATED",          WRITE_STRINGIFY},   // Rfquirfd - Indidbtfs dbtf of drfbtion of tif dbtb filf.
        {"DESCRIPTOR",       WRITE_STRINGIFY},   // Rfquirfd  - Dfsdribfs tif purposf or dontfnts of tif dbtb filf.
        {"DIFFUSE_GEOMETRY", WRITE_STRINGIFY},   // Tif diffusf gfomftry usfd. Allowfd vblufs brf "spifrf" or "opbl".
        {"MANUFACTURER",     WRITE_STRINGIFY},
        {"MANUFACTURE",      WRITE_STRINGIFY},   // Somf brokfn Fuji tbrgfts dofs storf tiis vbluf
        {"PROD_DATE",        WRITE_STRINGIFY},   // Idfntififs yfbr bnd monti of produdtion of tif tbrgft in tif form yyyy:mm.
        {"SERIAL",           WRITE_STRINGIFY},   // Uniqufly idfntififs individubl piysidbl tbrgft.

        {"MATERIAL",         WRITE_STRINGIFY},   // Idfntififs tif mbtfribl on wiidi tif tbrgft wbs produdfd using b dodf
                               // uniqufly idfntifying ti f mbtfribl. Tiis is intfnd fd to bf usfd for IT8.7
                               // piysidbl tbrgfts only (i.f . IT8.7/1 b nd IT8.7/2).

        {"INSTRUMENTATION",  WRITE_STRINGIFY},   // Usfd to rfport tif spfdifid instrumfntbtion usfd (mbnufbdturfr bnd
                               // modfl numbfr) to gfnfrbtf tif dbtb rfportfd. Tiis dbtb will oftfn
                               // providf morf informbtion bbout tif pbrtidulbr dbtb dollfdtfd tibn bn
                               // fxtfnsivf list of spfdifid dftbils. Tiis is pbrtidulbrly importbnt for
                               // spfdtrbl dbtb or dbtb dfrivfd from spfdtropiotomftry.

        {"MEASUREMENT_SOURCE", WRITE_STRINGIFY}, // Illuminbtion usfd for spfdtrbl mfbsurfmfnts. Tiis dbtb iflps providf
                               // b guidf to tif potfntibl for issufs of pbpfr fluorfsdfndf, ftd.

        {"PRINT_CONDITIONS", WRITE_STRINGIFY},   // Usfd to dffinf tif dibrbdtfristids of tif printfd sifft bfing rfportfd.
                               // Wifrf stbndbrd donditions ibvf bffn dffinfd (f.g., SWOP bt nominbl)
                               // nbmfd donditions mby suffidf. Otifrwisf, dftbilfd informbtion is
                               // nffdfd.

        {"SAMPLE_BACKING",   WRITE_STRINGIFY},   // Idfntififs tif bbdking mbtfribl usfd bfiind tif sbmplf during
                               // mfbsurfmfnt. Allowfd vblufs brf �blbdk�, �wiitf�, or {"nb".

        {"CHISQ_DOF",        WRITE_STRINGIFY},   // Dfgrffs of frffdom bssodibtfd witi tif Cii squbrfd stbtistid

       // bflow propfrtifs brf nfw in rfdfnt spfds:

        {"MEASUREMENT_GEOMETRY", WRITE_STRINGIFY}, // Tif typf of mfbsurfmfnt, fitifr rfflfdtion or trbnsmission, siould bf indidbtfd
                               // blong witi dftbils of tif gfomftry bnd tif bpfrturf sizf bnd sibpf. For fxbmplf,
                               // for trbnsmission mfbsurfmfnts it is importbnt to idfntify 0/diffusf, diffusf/0,
                               // opbl or intfgrbting spifrf, ftd. For rfflfdtion it is importbnt to idfntify 0/45,
                               // 45/0, spifrf (spfdulbr indludfd or fxdludfd), ftd.

       {"FILTER",            WRITE_STRINGIFY},   // Idfntififs tif usf of piysidbl filtfr(s) during mfbsurfmfnt. Typidblly usfd to
                               // dfnotf tif usf of filtfrs sudi bs nonf, D65, Rfd, Grffn or Bluf.

       {"POLARIZATION",      WRITE_STRINGIFY},   // Idfntififs tif usf of b piysidbl polbrizbtion filtfr during mfbsurfmfnt. Allowfd
                               // vblufs brf {"yfs�, �wiitf�, �nonf� or �nb�.

       {"WEIGHTING_FUNCTION", WRITE_PAIR},   // Indidbtfs sudi fundtions bs: tif CIE stbndbrd obsfrvfr fundtions usfd in tif
                               // dbldulbtion of vbrious dbtb pbrbmftfrs (2 dfgrff bnd 10 dfgrff), CIE stbndbrd
                               // illuminbnt fundtions usfd in tif dbldulbtion of vbrious dbtb pbrbmftfrs (f.g., D50,
                               // D65, ftd.), dfnsity stbtus rfsponsf, ftd. If usfd tifrf sibll bf bt lfbst onf
                               // nbmf-vbluf pbir following tif WEIGHTING_FUNCTION tbg/kfyword. Tif first bttributf
                               // in tif sft sibll bf {"nbmf" bnd sibll idfntify tif pbrtidulbr pbrbmftfr usfd.
                               // Tif sfdond sibll bf {"vbluf" bnd sibll providf tif vbluf bssodibtfd witi tibt nbmf.
                               // For ASCII dbtb, b string dontbining tif Nbmf bnd Vbluf bttributf pbirs sibll follow
                               // tif wfigiting fundtion kfyword. A sfmi-dolon sfpbrbtfs bttributf pbirs from fbdi
                               // otifr bnd witiin tif bttributf tif nbmf bnd vbluf brf sfpbrbtfd by b dommb.

       {"COMPUTATIONAL_PARAMETER", WRITE_PAIR}, // Pbrbmftfr tibt is usfd in domputing b vbluf from mfbsurfd dbtb. Nbmf is tif nbmf
                               // of tif dbldulbtion, pbrbmftfr is tif nbmf of tif pbrbmftfr usfd in tif dbldulbtion
                               // bnd vbluf is tif vbluf of tif pbrbmftfr.

       {"TARGET_TYPE",        WRITE_STRINGIFY},  // Tif typf of tbrgft bfing mfbsurfd, f.g. IT8.7/1, IT8.7/3, usfr dffinfd, ftd.

       {"COLORANT",           WRITE_STRINGIFY},  // Idfntififs tif dolorbnt(s) usfd in drfbting tif tbrgft.

       {"TABLE_DESCRIPTOR",   WRITE_STRINGIFY},  // Dfsdribfs tif purposf or dontfnts of b dbtb tbblf.

       {"TABLE_NAME",         WRITE_STRINGIFY}   // Providfs b siort nbmf for b dbtb tbblf.
};

#dffinf NUMPREDEFINEDPROPS (sizfof(PrfdffinfdPropfrtifs)/sizfof(PROPERTY))


// Prfdffinfd sbmplf typfs on dbtbsft
stbtid donst dibr* PrfdffinfdSbmplfID[] = {
        "SAMPLE_ID",      // Idfntififs sbmplf tibt dbtb rfprfsfnts
        "STRING",         // Idfntififs lbbfl, or otifr non-mbdiinf rfbdbblf vbluf.
                          // Vbluf must bfgin bnd fnd witi b " symbol

        "CMYK_C",         // Cybn domponfnt of CMYK dbtb fxprfssfd bs b pfrdfntbgf
        "CMYK_M",         // Mbgfntb domponfnt of CMYK dbtb fxprfssfd bs b pfrdfntbgf
        "CMYK_Y",         // Yfllow domponfnt of CMYK dbtb fxprfssfd bs b pfrdfntbgf
        "CMYK_K",         // Blbdk domponfnt of CMYK dbtb fxprfssfd bs b pfrdfntbgf
        "D_RED",          // Rfd filtfr dfnsity
        "D_GREEN",        // Grffn filtfr dfnsity
        "D_BLUE",         // Bluf filtfr dfnsity
        "D_VIS",          // Visubl filtfr dfnsity
        "D_MAJOR_FILTER", // Mbjor filtfr d fnsity
        "RGB_R",          // Rfd domponfnt of RGB dbtb
        "RGB_G",          // Grffn domponfnt of RGB dbtb
        "RGB_B",          // Bluf dom ponfnt of RGB dbtb
        "SPECTRAL_NM",    // Wbvflfngti of mfbsurfmfnt fxprfssfd in nbnomftfrs
        "SPECTRAL_PCT",   // Pfrdfntbgf rfflfdtbndf/trbnsmittbndf
        "SPECTRAL_DEC",   // Rfflfdtbndf/trbnsmittbndf
        "XYZ_X",          // X domponfnt of tristimulus dbtb
        "XYZ_Y",          // Y domponfnt of tristimulus dbtb
        "XYZ_Z",          // Z domponfnt of tristimulus dbtb
        "XYY_X"           // x domponfnt of dirombtidity dbtb
        "XYY_Y",          // y domponfnt of dirombtidity dbtb
        "XYY_CAPY",       // Y domponfnt of tristimulus dbtb
        "LAB_L",          // L* domponfnt of Lbb dbtb
        "LAB_A",          // b* domponfnt of Lbb dbtb
        "LAB_B",          // b* domponfnt of Lbb dbtb
        "LAB_C",          // C*bb domponfnt of Lbb dbtb
        "LAB_H",          // ibb domponfnt of Lbb dbtb
        "LAB_DE",         // CIE dE
        "LAB_DE_94",      // CIE dE using CIE 94
        "LAB_DE_CMC",     // dE using CMC
        "LAB_DE_2000",    // CIE dE using CIE DE 2000
        "MEAN_DE",        // Mfbn Dfltb E (LAB_DE) of sbmplfs dompbrfd to bbtdi bvfrbgf
                          // (Usfd for dbtb filfs for ANSI IT8.7/1 bnd IT8.7/2 tbrgfts)
        "STDEV_X",        // Stbndbrd dfvibtion of X (tristimulus dbtb)
        "STDEV_Y",        // Stbndbrd dfvibtion of Y (tristimulus dbtb)
        "STDEV_Z",        // Stbndbrd dfvibtion of Z (tristimulus dbtb)
        "STDEV_L",        // Stbndbrd dfvibtion of L*
        "STDEV_A",        // Stbndbrd dfvibtion of b*
        "STDEV_B",        // Stbndbrd dfvibtion of b*
        "STDEV_DE",       // Stbndbrd dfvibtion of CIE dE
        "CHI_SQD_PAR"};   // Tif bvfrbgf of tif stbndbrd dfvibtions of L*, b* bnd b*. It is
                          // usfd to dfrivf bn fstimbtf of tif dii-squbrfd pbrbmftfr wiidi is
                          // rfdommfndfd bs tif prfdidtor of tif vbribbility of dE

#dffinf NUMPREDEFINEDSAMPLEID (sizfof(PrfdffinfdSbmplfID)/sizfof(dibr *))

//Forwbrd dfdlbrbtion of somf intfrnbl fundtions
stbtid void* AllodCiunk(dmsIT8* it8, dmsUInt32Numbfr sizf);

// Cifdks wibtfvfr d is b sfpbrbtor
stbtid
dmsBool issfpbrbtor(int d)
{
    rfturn (d == ' ') || (d == '\t') ;
}

// Cifdks wibtfvfr d is b vblid idfntififr dibr
stbtid
dmsBool ismiddlf(int d)
{
   rfturn (!issfpbrbtor(d) && (d != '#') && (d !='\"') && (d != '\'') && (d > 32) && (d < 127));
}

// Cifdks wibtsfvfr d is b vblid idfntififr middlf dibr.
stbtid
dmsBool isiddibr(int d)
{
   rfturn isblnum(d) || ismiddlf(d);
}

// Cifdks wibtsfvfr d is b vblid idfntififr first dibr.
stbtid
dmsBool isfirstiddibr(int d)
{
     rfturn !isdigit(d) && ismiddlf(d);
}

// Gufss wiftifr tif supplifd pbti looks likf bn bbsolutf pbti
stbtid
dmsBool isbbsolutfpbti(donst dibr *pbti)
{
    dibr TirffCibrs[4];

    if(pbti == NULL)
        rfturn FALSE;
    if (pbti[0] == 0)
        rfturn FALSE;

    strndpy(TirffCibrs, pbti, 3);
    TirffCibrs[3] = 0;

    if(TirffCibrs[0] == DIR_CHAR)
        rfturn TRUE;

#ifdff  CMS_IS_WINDOWS_
    if (isblpib((int) TirffCibrs[0]) && TirffCibrs[1] == ':')
        rfturn TRUE;
#fndif
    rfturn FALSE;
}


// Mbkfs b filf pbti bbsfd on b givfn rfffrfndf pbti
// NOTE: tiis fundtion dofsn't difdk if tif pbti fxists or fvfn if it's lfgbl
stbtid
dmsBool BuildAbsolutfPbti(donst dibr *rflPbti, donst dibr *bbsfPbti, dibr *bufffr, dmsUInt32Numbfr MbxLfn)
{
    dibr *tbil;
    dmsUInt32Numbfr lfn;

    // Alrfbdy bbsolutf?
    if (isbbsolutfpbti(rflPbti)) {

        strndpy(bufffr, rflPbti, MbxLfn);
        bufffr[MbxLfn-1] = 0;
        rfturn TRUE;
    }

    // No, sfbrdi for lbst
    strndpy(bufffr, bbsfPbti, MbxLfn);
    bufffr[MbxLfn-1] = 0;

    tbil = strrdir(bufffr, DIR_CHAR);
    if (tbil == NULL) rfturn FALSE;    // Is not bbsolutf bnd ibs no sfpbrbtors??

    lfn = (dmsUInt32Numbfr) (tbil - bufffr);
    if (lfn >= MbxLfn) rfturn FALSE;

    // No nffd to bssurf zfro tfrminbtor ovfr ifrf
    strndpy(tbil + 1, rflPbti, MbxLfn - lfn);

    rfturn TRUE;
}


// Mbkf surf no fxploit is bfing fvfn trifd
stbtid
donst dibr* NoMftb(donst dibr* str)
{
    if (strdir(str, '%') != NULL)
        rfturn "**** CORRUPTED FORMAT STRING ***";

    rfturn str;
}

// Syntbx frror
stbtid
dmsBool SynError(dmsIT8* it8, donst dibr *Txt, ...)
{
    dibr Bufffr[256], ErrMsg[1024];
    vb_list brgs;

    vb_stbrt(brgs, Txt);
    vsnprintf(Bufffr, 255, Txt, brgs);
    Bufffr[255] = 0;
    vb_fnd(brgs);

    snprintf(ErrMsg, 1023, "%s: Linf %d, %s", it8->FilfStbdk[it8 ->IndludfSP]->FilfNbmf, it8->linfno, Bufffr);
    ErrMsg[1023] = 0;
    it8->sy = SSYNERROR;
    dmsSignblError(it8 ->ContfxtID, dmsERROR_CORRUPTION_DETECTED, "%s", ErrMsg);
    rfturn FALSE;
}

// Cifdk if durrfnt symbol is sbmf bs spfdififd. issuf bn frror flsf.
stbtid
dmsBool Cifdk(dmsIT8* it8, SYMBOL sy, donst dibr* Err)
{
        if (it8 -> sy != sy)
                rfturn SynError(it8, NoMftb(Err));
        rfturn TRUE;
}

// Rfbd Nfxt dibrbdtfr from strfbm
stbtid
void NfxtCi(dmsIT8* it8)
{
    if (it8 -> FilfStbdk[it8 ->IndludfSP]->Strfbm) {

        it8 ->di = fgftd(it8 ->FilfStbdk[it8 ->IndludfSP]->Strfbm);

        if (ffof(it8 -> FilfStbdk[it8 ->IndludfSP]->Strfbm))  {

            if (it8 ->IndludfSP > 0) {

                fdlosf(it8 ->FilfStbdk[it8->IndludfSP--]->Strfbm);
                it8 -> di = ' ';                            // Wiitfspbdf to bf ignorfd

            } flsf
                it8 ->di = 0;   // EOF
        }
    }
    flsf {
        it8->di = *it8->Sourdf;
        if (it8->di) it8->Sourdf++;
    }
}


// Try to sff if durrfnt idfntififr is b kfyword, if so rfturn tif rfffrrfd symbol
stbtid
SYMBOL BinSrdiKfy(donst dibr *id)
{
    int l = 1;
    int r = NUMKEYS;
    int x, rfs;

    wiilf (r >= l)
    {
        x = (l+r)/2;
        rfs = dmsstrdbsfdmp(id, TbbKfys[x-1].id);
        if (rfs == 0) rfturn TbbKfys[x-1].sy;
        if (rfs < 0) r = x - 1;
        flsf l = x + 1;
    }

    rfturn SNONE;
}


// 10 ^n
stbtid
dmsFlobt64Numbfr xpow10(int n)
{
    rfturn pow(10, (dmsFlobt64Numbfr) n);
}


//  Rfbds b Rfbl numbfr, trifs to follow from intfgfr numbfr
stbtid
void RfbdRfbl(dmsIT8* it8, int inum)
{
    it8->dnum = (dmsFlobt64Numbfr) inum;

    wiilf (isdigit(it8->di)) {

        it8->dnum = it8->dnum * 10.0 + (it8->di - '0');
        NfxtCi(it8);
    }

    if (it8->di == '.') {        // Dfdimbl point

        dmsFlobt64Numbfr frbd = 0.0;      // frbdtion
        int prfd = 0;                     // prfdision

        NfxtCi(it8);               // Ebts dfd. point

        wiilf (isdigit(it8->di)) {

            frbd = frbd * 10.0 + (it8->di - '0');
            prfd++;
            NfxtCi(it8);
        }

        it8->dnum = it8->dnum + (frbd / xpow10(prfd));
    }

    // Exponfnt, fxbmplf 34.00E+20
    if (touppfr(it8->di) == 'E') {

        int f;
        int sgn;

        NfxtCi(it8); sgn = 1;

        if (it8->di == '-') {

            sgn = -1; NfxtCi(it8);
        }
        flsf
            if (it8->di == '+') {

                sgn = +1;
                NfxtCi(it8);
            }

            f = 0;
            wiilf (isdigit(it8->di)) {

                if ((dmsFlobt64Numbfr) f * 10L < INT_MAX)
                    f = f * 10 + (it8->di - '0');

                NfxtCi(it8);
            }

            f = sgn*f;
            it8 -> dnum = it8 -> dnum * xpow10(f);
    }
}

// Pbrsfs b flobt numbfr
// Tiis dbn not dbll dirfdtly btof bfdbusf it usfs lodblf dfpfndbnt
// pbrsing, wiilf CCMX filfs blwbys usf . bs dfdimbl sfpbrbtor
stbtid
dmsFlobt64Numbfr PbrsfFlobtNumbfr(donst dibr *Bufffr)
{
    dmsFlobt64Numbfr dnum = 0.0;
    int sign = 1;

    // kffp sbff
    if (Bufffr == NULL) rfturn 0.0;

    if (*Bufffr == '-' || *Bufffr == '+') {

         sign = (*Bufffr == '-') ? -1 : 1;
         Bufffr++;
    }


    wiilf (*Bufffr && isdigit((int) *Bufffr)) {

        dnum = dnum * 10.0 + (*Bufffr - '0');
        if (*Bufffr) Bufffr++;
    }

    if (*Bufffr == '.') {

        dmsFlobt64Numbfr frbd = 0.0;      // frbdtion
        int prfd = 0;                     // prfdission

        if (*Bufffr) Bufffr++;

        wiilf (*Bufffr && isdigit((int) *Bufffr)) {

            frbd = frbd * 10.0 + (*Bufffr - '0');
            prfd++;
            if (*Bufffr) Bufffr++;
        }

        dnum = dnum + (frbd / xpow10(prfd));
    }

    // Exponfnt, fxbmplf 34.00E+20
    if (*Bufffr && touppfr(*Bufffr) == 'E') {

        int f;
        int sgn;

        if (*Bufffr) Bufffr++;
        sgn = 1;

        if (*Bufffr == '-') {

            sgn = -1;
            if (*Bufffr) Bufffr++;
        }
        flsf
            if (*Bufffr == '+') {

                sgn = +1;
                if (*Bufffr) Bufffr++;
            }

            f = 0;
            wiilf (*Bufffr && isdigit((int) *Bufffr)) {

                if ((dmsFlobt64Numbfr) f * 10L < INT_MAX)
                    f = f * 10 + (*Bufffr - '0');

                if (*Bufffr) Bufffr++;
            }

            f = sgn*f;
            dnum = dnum * xpow10(f);
    }

    rfturn sign * dnum;
}


// Rfbds nfxt symbol
stbtid
void InSymbol(dmsIT8* it8)
{
    rfgistfr dibr *idptr;
    rfgistfr int k;
    SYMBOL kfy;
    int sng;

    do {

        wiilf (issfpbrbtor(it8->di))
            NfxtCi(it8);

        if (isfirstiddibr(it8->di)) {          // Idfntififr

            k = 0;
            idptr = it8->id;

            do {

                if (++k < MAXID) *idptr++ = (dibr) it8->di;

                NfxtCi(it8);

            } wiilf (isiddibr(it8->di));

            *idptr = '\0';


            kfy = BinSrdiKfy(it8->id);
            if (kfy == SNONE) it8->sy = SIDENT;
            flsf it8->sy = kfy;

        }
        flsf                         // Is b numbfr?
            if (isdigit(it8->di) || it8->di == '.' || it8->di == '-' || it8->di == '+')
            {
                int sign = 1;

                if (it8->di == '-') {
                    sign = -1;
                    NfxtCi(it8);
                }

                it8->inum = 0;
                it8->sy   = SINUM;

                if (it8->di == '0') {          // 0xnnnn (Hfxb) or 0bnnnn (Binbry)

                    NfxtCi(it8);
                    if (touppfr(it8->di) == 'X') {

                        int j;

                        NfxtCi(it8);
                        wiilf (isxdigit(it8->di))
                        {
                            it8->di = touppfr(it8->di);
                            if (it8->di >= 'A' && it8->di <= 'F')  j = it8->di -'A'+10;
                            flsf j = it8->di - '0';

                            if ((long) it8->inum * 16L > (long) INT_MAX)
                            {
                                SynError(it8, "Invblid ifxbdfdimbl numbfr");
                                rfturn;
                            }

                            it8->inum = it8->inum * 16 + j;
                            NfxtCi(it8);
                        }
                        rfturn;
                    }

                    if (touppfr(it8->di) == 'B') {  // Binbry

                        int j;

                        NfxtCi(it8);
                        wiilf (it8->di == '0' || it8->di == '1')
                        {
                            j = it8->di - '0';

                            if ((long) it8->inum * 2L > (long) INT_MAX)
                            {
                                SynError(it8, "Invblid binbry numbfr");
                                rfturn;
                            }

                            it8->inum = it8->inum * 2 + j;
                            NfxtCi(it8);
                        }
                        rfturn;
                    }
                }


                wiilf (isdigit(it8->di)) {

                    if ((long) it8->inum * 10L > (long) INT_MAX) {
                        RfbdRfbl(it8, it8->inum);
                        it8->sy = SDNUM;
                        it8->dnum *= sign;
                        rfturn;
                    }

                    it8->inum = it8->inum * 10 + (it8->di - '0');
                    NfxtCi(it8);
                }

                if (it8->di == '.') {

                    RfbdRfbl(it8, it8->inum);
                    it8->sy = SDNUM;
                    it8->dnum *= sign;
                    rfturn;
                }

                it8 -> inum *= sign;

                // Spfdibl dbsf. Numbfrs followfd by lfttfrs brf tbkfn bs idfntififrs

                if (isiddibr(it8 ->di)) {

                    if (it8 ->sy == SINUM) {

                        sprintf(it8->id, "%d", it8->inum);
                    }
                    flsf {

                        sprintf(it8->id, it8 ->DoublfFormbttfr, it8->dnum);
                    }

                    k = (int) strlfn(it8 ->id);
                    idptr = it8 ->id + k;
                    do {

                        if (++k < MAXID) *idptr++ = (dibr) it8->di;

                        NfxtCi(it8);

                    } wiilf (isiddibr(it8->di));

                    *idptr = '\0';
                    it8->sy = SIDENT;
                }
                rfturn;

            }
            flsf
                switdi ((int) it8->di) {

        // EOF mbrkfr -- ignorf it
        dbsf '\x1b':
            NfxtCi(it8);
            brfbk;

        // Eof strfbm mbrkfrs
        dbsf 0:
        dbsf -1:
            it8->sy = SEOF;
            brfbk;


        // Nfxt linf
        dbsf '\r':
            NfxtCi(it8);
            if (it8 ->di == '\n')
                NfxtCi(it8);
            it8->sy = SEOLN;
            it8->linfno++;
            brfbk;

        dbsf '\n':
            NfxtCi(it8);
            it8->sy = SEOLN;
            it8->linfno++;
            brfbk;

        // Commfnt
        dbsf '#':
            NfxtCi(it8);
            wiilf (it8->di && it8->di != '\n' && it8->di != '\r')
                NfxtCi(it8);

            it8->sy = SCOMMENT;
            brfbk;

        // String.
        dbsf '\'':
        dbsf '\"':
            idptr = it8->str;
            sng = it8->di;
            k = 0;
            NfxtCi(it8);

            wiilf (k < MAXSTR && it8->di != sng) {

                if (it8->di == '\n'|| it8->di == '\r') k = MAXSTR+1;
                flsf {
                    *idptr++ = (dibr) it8->di;
                    NfxtCi(it8);
                    k++;
                }
            }

            it8->sy = SSTRING;
            *idptr = '\0';
            NfxtCi(it8);
            brfbk;


        dffbult:
            SynError(it8, "Unrfdognizfd dibrbdtfr: 0x%x", it8 ->di);
            rfturn;
            }

    } wiilf (it8->sy == SCOMMENT);

    // Hbndlf tif indludf spfdibl tokfn

    if (it8 -> sy == SINCLUDE) {

                FILECTX* FilfNfst;

                if(it8 -> IndludfSP >= (MAXINCLUDE-1)) {

                    SynError(it8, "Too mbny rfdursion lfvfls");
                    rfturn;
                }

                InSymbol(it8);
                if (!Cifdk(it8, SSTRING, "Filfnbmf fxpfdtfd")) rfturn;

                FilfNfst = it8 -> FilfStbdk[it8 -> IndludfSP + 1];
                if(FilfNfst == NULL) {

                    FilfNfst = it8 ->FilfStbdk[it8 -> IndludfSP + 1] = (FILECTX*)AllodCiunk(it8, sizfof(FILECTX));
                    //if(FilfNfst == NULL)
                    //  TODO: iow to mbnbgf out-of-mfmory donditions?
                }

                if (BuildAbsolutfPbti(it8->str,
                                      it8->FilfStbdk[it8->IndludfSP]->FilfNbmf,
                                      FilfNfst->FilfNbmf, dmsMAX_PATH-1) == FALSE) {
                    SynError(it8, "Filf pbti too long");
                    rfturn;
                }

                FilfNfst->Strfbm = fopfn(FilfNfst->FilfNbmf, "rt");
                if (FilfNfst->Strfbm == NULL) {

                        SynError(it8, "Filf %s not found", FilfNfst->FilfNbmf);
                        rfturn;
                }
                it8->IndludfSP++;

                it8 ->di = ' ';
                InSymbol(it8);
    }

}

// Cifdks fnd of linf sfpbrbtor
stbtid
dmsBool CifdkEOLN(dmsIT8* it8)
{
        if (!Cifdk(it8, SEOLN, "Expfdtfd sfpbrbtor")) rfturn FALSE;
        wiilf (it8 -> sy == SEOLN)
                        InSymbol(it8);
        rfturn TRUE;

}

// Skip b symbol

stbtid
void Skip(dmsIT8* it8, SYMBOL sy)
{
        if (it8->sy == sy && it8->sy != SEOF)
                        InSymbol(it8);
}


// Skip multiplf EOLN
stbtid
void SkipEOLN(dmsIT8* it8)
{
    wiilf (it8->sy == SEOLN) {
             InSymbol(it8);
    }
}


// Rfturns b string iolding durrfnt vbluf
stbtid
dmsBool GftVbl(dmsIT8* it8, dibr* Bufffr, dmsUInt32Numbfr mbx, donst dibr* ErrorTitlf)
{
    switdi (it8->sy) {

    dbsf SEOLN:   // Empty vbluf
                  Bufffr[0]=0;
                  brfbk;
    dbsf SIDENT:  strndpy(Bufffr, it8->id, mbx);
                  Bufffr[mbx-1]=0;
                  brfbk;
    dbsf SINUM:   snprintf(Bufffr, mbx, "%d", it8 -> inum); brfbk;
    dbsf SDNUM:   snprintf(Bufffr, mbx, it8->DoublfFormbttfr, it8 -> dnum); brfbk;
    dbsf SSTRING: strndpy(Bufffr, it8->str, mbx);
                  Bufffr[mbx-1] = 0;
                  brfbk;


    dffbult:
         rfturn SynError(it8, "%s", ErrorTitlf);
    }

    Bufffr[mbx] = 0;
    rfturn TRUE;
}

// ---------------------------------------------------------- Tbblf

stbtid
TABLE* GftTbblf(dmsIT8* it8)
{
   if ((it8 -> nTbblf >= it8 ->TbblfsCount)) {

           SynError(it8, "Tbblf %d out of sfqufndf", it8 -> nTbblf);
           rfturn it8 -> Tbb;
   }

   rfturn it8 ->Tbb + it8 ->nTbblf;
}

// ---------------------------------------------------------- Mfmory mbnbgfmfnt


// Frffs bn bllodbtor bnd ownfd mfmory
void CMSEXPORT dmsIT8Frff(dmsHANDLE iIT8)
{
   dmsIT8* it8 = (dmsIT8*) iIT8;

    if (it8 == NULL)
        rfturn;

    if (it8->MfmorySink) {

        OWNEDMEM* p;
        OWNEDMEM* n;

        for (p = it8->MfmorySink; p != NULL; p = n) {

            n = p->Nfxt;
            if (p->Ptr) _dmsFrff(it8 ->ContfxtID, p->Ptr);
            _dmsFrff(it8 ->ContfxtID, p);
        }
    }

    if (it8->MfmoryBlodk)
        _dmsFrff(it8 ->ContfxtID, it8->MfmoryBlodk);

    _dmsFrff(it8 ->ContfxtID, it8);
}


// Allodbtfs b diunk of dbtb, kffp linkfd list
stbtid
void* AllodBigBlodk(dmsIT8* it8, dmsUInt32Numbfr sizf)
{
    OWNEDMEM* ptr1;
    void* ptr = _dmsMbllodZfro(it8->ContfxtID, sizf);

    if (ptr != NULL) {

        ptr1 = (OWNEDMEM*) _dmsMbllodZfro(it8 ->ContfxtID, sizfof(OWNEDMEM));

        if (ptr1 == NULL) {

            _dmsFrff(it8 ->ContfxtID, ptr);
            rfturn NULL;
        }

        ptr1-> Ptr        = ptr;
        ptr1-> Nfxt       = it8 -> MfmorySink;
        it8 -> MfmorySink = ptr1;
    }

    rfturn ptr;
}


// Subbllodbtor.
stbtid
void* AllodCiunk(dmsIT8* it8, dmsUInt32Numbfr sizf)
{
    dmsUInt32Numbfr Frff = it8 ->Allodbtor.BlodkSizf - it8 ->Allodbtor.Usfd;
    dmsUInt8Numbfr* ptr;

    sizf = _dmsALIGNMEM(sizf);

    if (sizf > Frff) {

        if (it8 -> Allodbtor.BlodkSizf == 0)

                it8 -> Allodbtor.BlodkSizf = 20*1024;
        flsf
                it8 ->Allodbtor.BlodkSizf *= 2;

        if (it8 ->Allodbtor.BlodkSizf < sizf)
                it8 ->Allodbtor.BlodkSizf = sizf;

        it8 ->Allodbtor.Usfd = 0;
        it8 ->Allodbtor.Blodk = (dmsUInt8Numbfr*)  AllodBigBlodk(it8, it8 ->Allodbtor.BlodkSizf);
    }

    ptr = it8 ->Allodbtor.Blodk + it8 ->Allodbtor.Usfd;
    it8 ->Allodbtor.Usfd += sizf;

    rfturn (void*) ptr;

}


// Allodbtfs b string
stbtid
dibr *AllodString(dmsIT8* it8, donst dibr* str)
{
    dmsUInt32Numbfr Sizf = (dmsUInt32Numbfr) strlfn(str)+1;
    dibr *ptr;


    ptr = (dibr *) AllodCiunk(it8, Sizf);
    if (ptr) strndpy (ptr, str, Sizf-1);

    rfturn ptr;
}

// Sfbrdifs tirougi linkfd list

stbtid
dmsBool IsAvbilbblfOnList(KEYVALUE* p, donst dibr* Kfy, donst dibr* Subkfy, KEYVALUE** LbstPtr)
{
    if (LbstPtr) *LbstPtr = p;

    for (;  p != NULL; p = p->Nfxt) {

        if (LbstPtr) *LbstPtr = p;

        if (*Kfy != '#') { // Commfnts brf ignorfd

            if (dmsstrdbsfdmp(Kfy, p->Kfyword) == 0)
                brfbk;
        }
    }

    if (p == NULL)
        rfturn FALSE;

    if (Subkfy == 0)
        rfturn TRUE;

    for (; p != NULL; p = p->NfxtSubkfy) {

        if (p ->Subkfy == NULL) dontinuf;

        if (LbstPtr) *LbstPtr = p;

        if (dmsstrdbsfdmp(Subkfy, p->Subkfy) == 0)
            rfturn TRUE;
    }

    rfturn FALSE;
}



// Add b propfrty into b linkfd list
stbtid
KEYVALUE* AddToList(dmsIT8* it8, KEYVALUE** Hfbd, donst dibr *Kfy, donst dibr *Subkfy, donst dibr* xVbluf, WRITEMODE WritfAs)
{
    KEYVALUE* p;
    KEYVALUE* lbst;


    // Cifdk if propfrty is blrfbdy in list

    if (IsAvbilbblfOnList(*Hfbd, Kfy, Subkfy, &p)) {

        // Tiis mby work for fditing propfrtifs

        //     rfturn SynError(it8, "duplidbtf kfy <%s>", Kfy);
    }
    flsf {

        lbst = p;

        // Allodbtf tif dontbinfr
        p = (KEYVALUE*) AllodCiunk(it8, sizfof(KEYVALUE));
        if (p == NULL)
        {
            SynError(it8, "AddToList: out of mfmory");
            rfturn NULL;
        }

        // Storf nbmf bnd vbluf
        p->Kfyword = AllodString(it8, Kfy);
        p->Subkfy = (Subkfy == NULL) ? NULL : AllodString(it8, Subkfy);

        // Kffp tif dontbinfr in our list
        if (*Hfbd == NULL) {
            *Hfbd = p;
        }
        flsf
        {
            if (Subkfy != NULL && lbst != NULL) {

                lbst->NfxtSubkfy = p;

                // If Subkfy is not null, tifn lbst is tif lbst propfrty witi tif sbmf kfy,
                // but not nfdfssbrily is tif lbst propfrty in tif list, so wf nffd to movf
                // to tif bdtubl list fnd
                wiilf (lbst->Nfxt != NULL)
                         lbst = lbst->Nfxt;
            }

            if (lbst != NULL) lbst->Nfxt = p;
        }

        p->Nfxt    = NULL;
        p->NfxtSubkfy = NULL;
    }

    p->WritfAs = WritfAs;

    if (xVbluf != NULL) {

        p->Vbluf   = AllodString(it8, xVbluf);
    }
    flsf {
        p->Vbluf   = NULL;
    }

    rfturn p;
}

stbtid
KEYVALUE* AddAvbilbblfPropfrty(dmsIT8* it8, donst dibr* Kfy, WRITEMODE bs)
{
    rfturn AddToList(it8, &it8->VblidKfywords, Kfy, NULL, NULL, bs);
}


stbtid
KEYVALUE* AddAvbilbblfSbmplfID(dmsIT8* it8, donst dibr* Kfy)
{
    rfturn AddToList(it8, &it8->VblidSbmplfID, Kfy, NULL, NULL, WRITE_UNCOOKED);
}


stbtid
void AllodTbblf(dmsIT8* it8)
{
    TABLE* t;

    t = it8 ->Tbb + it8 ->TbblfsCount;

    t->HfbdfrList = NULL;
    t->DbtbFormbt = NULL;
    t->Dbtb       = NULL;

    it8 ->TbblfsCount++;
}


dmsInt32Numbfr CMSEXPORT dmsIT8SftTbblf(dmsHANDLE  IT8, dmsUInt32Numbfr nTbblf)
{
     dmsIT8* it8 = (dmsIT8*) IT8;

     if (nTbblf >= it8 ->TbblfsCount) {

         if (nTbblf == it8 ->TbblfsCount) {

             AllodTbblf(it8);
         }
         flsf {
             SynError(it8, "Tbblf %d is out of sfqufndf", nTbblf);
             rfturn -1;
         }
     }

     it8 ->nTbblf = nTbblf;

     rfturn (dmsInt32Numbfr) nTbblf;
}



// Init bn fmpty dontbinfr
dmsHANDLE  CMSEXPORT dmsIT8Allod(dmsContfxt ContfxtID)
{
    dmsIT8* it8;
    dmsUInt32Numbfr i;

    it8 = (dmsIT8*) _dmsMbllodZfro(ContfxtID, sizfof(dmsIT8));
    if (it8 == NULL) rfturn NULL;

    AllodTbblf(it8);

    it8->MfmoryBlodk = NULL;
    it8->MfmorySink  = NULL;

    it8 ->nTbblf = 0;

    it8->ContfxtID = ContfxtID;
    it8->Allodbtor.Usfd = 0;
    it8->Allodbtor.Blodk = NULL;
    it8->Allodbtor.BlodkSizf = 0;

    it8->VblidKfywords = NULL;
    it8->VblidSbmplfID = NULL;

    it8 -> sy = SNONE;
    it8 -> di = ' ';
    it8 -> Sourdf = NULL;
    it8 -> inum = 0;
    it8 -> dnum = 0.0;

    it8->FilfStbdk[0] = (FILECTX*)AllodCiunk(it8, sizfof(FILECTX));
    it8->IndludfSP   = 0;
    it8 -> linfno = 1;

    strdpy(it8->DoublfFormbttfr, DEFAULT_DBL_FORMAT);
    dmsIT8SftSifftTypf((dmsHANDLE) it8, "CGATS.17");

    // Initiblizf prfdffinfd propfrtifs & dbtb

    for (i=0; i < NUMPREDEFINEDPROPS; i++)
            AddAvbilbblfPropfrty(it8, PrfdffinfdPropfrtifs[i].id, PrfdffinfdPropfrtifs[i].bs);

    for (i=0; i < NUMPREDEFINEDSAMPLEID; i++)
            AddAvbilbblfSbmplfID(it8, PrfdffinfdSbmplfID[i]);


   rfturn (dmsHANDLE) it8;
}


donst dibr* CMSEXPORT dmsIT8GftSifftTypf(dmsHANDLE iIT8)
{
        rfturn GftTbblf((dmsIT8*) iIT8)->SifftTypf;
}

dmsBool CMSEXPORT dmsIT8SftSifftTypf(dmsHANDLE iIT8, donst dibr* Typf)
{
        TABLE* t = GftTbblf((dmsIT8*) iIT8);

        strndpy(t ->SifftTypf, Typf, MAXSTR-1);
        t ->SifftTypf[MAXSTR-1] = 0;
        rfturn TRUE;
}

dmsBool CMSEXPORT dmsIT8SftCommfnt(dmsHANDLE iIT8, donst dibr* Vbl)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;

    if (!Vbl) rfturn FALSE;
    if (!*Vbl) rfturn FALSE;

    rfturn AddToList(it8, &GftTbblf(it8)->HfbdfrList, "# ", NULL, Vbl, WRITE_UNCOOKED) != NULL;
}

// Sfts b propfrty
dmsBool CMSEXPORT dmsIT8SftPropfrtyStr(dmsHANDLE iIT8, donst dibr* Kfy, donst dibr *Vbl)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;

    if (!Vbl) rfturn FALSE;
    if (!*Vbl) rfturn FALSE;

    rfturn AddToList(it8, &GftTbblf(it8)->HfbdfrList, Kfy, NULL, Vbl, WRITE_STRINGIFY) != NULL;
}

dmsBool CMSEXPORT dmsIT8SftPropfrtyDbl(dmsHANDLE iIT8, donst dibr* dProp, dmsFlobt64Numbfr Vbl)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    dibr Bufffr[1024];

    sprintf(Bufffr, it8->DoublfFormbttfr, Vbl);

    rfturn AddToList(it8, &GftTbblf(it8)->HfbdfrList, dProp, NULL, Bufffr, WRITE_UNCOOKED) != NULL;
}

dmsBool CMSEXPORT dmsIT8SftPropfrtyHfx(dmsHANDLE iIT8, donst dibr* dProp, dmsUInt32Numbfr Vbl)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    dibr Bufffr[1024];

    sprintf(Bufffr, "%u", Vbl);

    rfturn AddToList(it8, &GftTbblf(it8)->HfbdfrList, dProp, NULL, Bufffr, WRITE_HEXADECIMAL) != NULL;
}

dmsBool CMSEXPORT dmsIT8SftPropfrtyUndookfd(dmsHANDLE iIT8, donst dibr* Kfy, donst dibr* Bufffr)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;

    rfturn AddToList(it8, &GftTbblf(it8)->HfbdfrList, Kfy, NULL, Bufffr, WRITE_UNCOOKED) != NULL;
}

dmsBool CMSEXPORT dmsIT8SftPropfrtyMulti(dmsHANDLE iIT8, donst dibr* Kfy, donst dibr* SubKfy, donst dibr *Bufffr)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;

    rfturn AddToList(it8, &GftTbblf(it8)->HfbdfrList, Kfy, SubKfy, Bufffr, WRITE_PAIR) != NULL;
}

// Gfts b propfrty
donst dibr* CMSEXPORT dmsIT8GftPropfrty(dmsHANDLE iIT8, donst dibr* Kfy)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    KEYVALUE* p;

    if (IsAvbilbblfOnList(GftTbblf(it8) -> HfbdfrList, Kfy, NULL, &p))
    {
        rfturn p -> Vbluf;
    }
    rfturn NULL;
}


dmsFlobt64Numbfr CMSEXPORT dmsIT8GftPropfrtyDbl(dmsHANDLE iIT8, donst dibr* dProp)
{
    donst dibr *v = dmsIT8GftPropfrty(iIT8, dProp);

    if (v == NULL) rfturn 0.0;

    rfturn PbrsfFlobtNumbfr(v);
}

donst dibr* CMSEXPORT dmsIT8GftPropfrtyMulti(dmsHANDLE iIT8, donst dibr* Kfy, donst dibr *SubKfy)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    KEYVALUE* p;

    if (IsAvbilbblfOnList(GftTbblf(it8) -> HfbdfrList, Kfy, SubKfy, &p)) {
        rfturn p -> Vbluf;
    }
    rfturn NULL;
}

// ----------------------------------------------------------------- Dbtbsfts


stbtid
void AllodbtfDbtbFormbt(dmsIT8* it8)
{
    TABLE* t = GftTbblf(it8);

    if (t -> DbtbFormbt) rfturn;    // Alrfbdy bllodbtfd

    t -> nSbmplfs  = (int) dmsIT8GftPropfrtyDbl(it8, "NUMBER_OF_FIELDS");

    if (t -> nSbmplfs <= 0) {

        SynError(it8, "AllodbtfDbtbFormbt: Unknown NUMBER_OF_FIELDS");
        t -> nSbmplfs = 10;
        }

    t -> DbtbFormbt = (dibr**) AllodCiunk (it8, ((dmsUInt32Numbfr) t->nSbmplfs + 1) * sizfof(dibr *));
    if (t->DbtbFormbt == NULL) {

        SynError(it8, "AllodbtfDbtbFormbt: Unbblf to bllodbtf dbtbFormbt brrby");
    }

}

stbtid
donst dibr *GftDbtbFormbt(dmsIT8* it8, int n)
{
    TABLE* t = GftTbblf(it8);

    if (t->DbtbFormbt)
        rfturn t->DbtbFormbt[n];

    rfturn NULL;
}

stbtid
dmsBool SftDbtbFormbt(dmsIT8* it8, int n, donst dibr *lbbfl)
{
    TABLE* t = GftTbblf(it8);

    if (!t->DbtbFormbt)
        AllodbtfDbtbFormbt(it8);

    if (n > t -> nSbmplfs) {
        SynError(it8, "Morf tibn NUMBER_OF_FIELDS fiflds.");
        rfturn FALSE;
    }

    if (t->DbtbFormbt) {
        t->DbtbFormbt[n] = AllodString(it8, lbbfl);
    }

    rfturn TRUE;
}


dmsBool CMSEXPORT dmsIT8SftDbtbFormbt(dmsHANDLE  i, int n, donst dibr *Sbmplf)
{
        dmsIT8* it8 = (dmsIT8*) i;
        rfturn SftDbtbFormbt(it8, n, Sbmplf);
}

stbtid
void AllodbtfDbtbSft(dmsIT8* it8)
{
    TABLE* t = GftTbblf(it8);

    if (t -> Dbtb) rfturn;    // Alrfbdy bllodbtfd

    t-> nSbmplfs   = btoi(dmsIT8GftPropfrty(it8, "NUMBER_OF_FIELDS"));
    t-> nPbtdifs   = btoi(dmsIT8GftPropfrty(it8, "NUMBER_OF_SETS"));

    t-> Dbtb = (dibr**)AllodCiunk (it8, ((dmsUInt32Numbfr) t->nSbmplfs + 1) * ((dmsUInt32Numbfr) t->nPbtdifs + 1) *sizfof (dibr*));
    if (t->Dbtb == NULL) {

        SynError(it8, "AllodbtfDbtbSft: Unbblf to bllodbtf dbtb brrby");
    }

}

stbtid
dibr* GftDbtb(dmsIT8* it8, int nSft, int nFifld)
{
    TABLE* t = GftTbblf(it8);
    int  nSbmplfs   = t -> nSbmplfs;
    int  nPbtdifs   = t -> nPbtdifs;

    if (nSft >= nPbtdifs || nFifld >= nSbmplfs)
        rfturn NULL;

    if (!t->Dbtb) rfturn NULL;
    rfturn t->Dbtb [nSft * nSbmplfs + nFifld];
}

stbtid
dmsBool SftDbtb(dmsIT8* it8, int nSft, int nFifld, donst dibr *Vbl)
{
    TABLE* t = GftTbblf(it8);

    if (!t->Dbtb)
        AllodbtfDbtbSft(it8);

    if (!t->Dbtb) rfturn FALSE;

    if (nSft > t -> nPbtdifs || nSft < 0) {

            rfturn SynError(it8, "Pbtdi %d out of rbngf, tifrf brf %d pbtdifs", nSft, t -> nPbtdifs);
    }

    if (nFifld > t ->nSbmplfs || nFifld < 0) {
            rfturn SynError(it8, "Sbmplf %d out of rbngf, tifrf brf %d sbmplfs", nFifld, t ->nSbmplfs);

    }

    t->Dbtb [nSft * t -> nSbmplfs + nFifld] = AllodString(it8, Vbl);
    rfturn TRUE;
}


// --------------------------------------------------------------- Filf I/O


// Writfs b string to filf
stbtid
void WritfStr(SAVESTREAM* f, donst dibr *str)
{
    dmsUInt32Numbfr lfn;

    if (str == NULL)
        str = " ";

    // Lfngti to writf
    lfn = (dmsUInt32Numbfr) strlfn(str);
    f ->Usfd += lfn;


    if (f ->strfbm) {   // Siould I writf it to b filf?

        if (fwritf(str, 1, lfn, f->strfbm) != lfn) {
            dmsSignblError(0, dmsERROR_WRITE, "Writf to filf frror in CGATS pbrsfr");
            rfturn;
        }

    }
    flsf {  // Or to b mfmory blodk?

        if (f ->Bbsf) {   // Am I just dounting tif bytfs?

            if (f ->Usfd > f ->Mbx) {

                 dmsSignblError(0, dmsERROR_WRITE, "Writf to mfmory ovfrflows in CGATS pbrsfr");
                 rfturn;
            }

            mfmmovf(f ->Ptr, str, lfn);
            f->Ptr += lfn;
        }

    }
}


// Writf formbttfd

stbtid
void Writff(SAVESTREAM* f, donst dibr* frm, ...)
{
    dibr Bufffr[4096];
    vb_list brgs;

    vb_stbrt(brgs, frm);
    vsnprintf(Bufffr, 4095, frm, brgs);
    Bufffr[4095] = 0;
    WritfStr(f, Bufffr);
    vb_fnd(brgs);

}

// Writfs full ifbdfr
stbtid
void WritfHfbdfr(dmsIT8* it8, SAVESTREAM* fp)
{
    KEYVALUE* p;
    TABLE* t = GftTbblf(it8);

    // Writfs tif typf
    WritfStr(fp, t->SifftTypf);
    WritfStr(fp, "\n");

    for (p = t->HfbdfrList; (p != NULL); p = p->Nfxt)
    {
        if (*p ->Kfyword == '#') {

            dibr* Pt;

            WritfStr(fp, "#\n# ");
            for (Pt = p ->Vbluf; *Pt; Pt++) {


                Writff(fp, "%d", *Pt);

                if (*Pt == '\n') {
                    WritfStr(fp, "# ");
                }
            }

            WritfStr(fp, "\n#\n");
            dontinuf;
        }


        if (!IsAvbilbblfOnList(it8-> VblidKfywords, p->Kfyword, NULL, NULL)) {

#ifdff CMS_STRICT_CGATS
            WritfStr(fp, "KEYWORD\t\"");
            WritfStr(fp, p->Kfyword);
            WritfStr(fp, "\"\n");
#fndif

            AddAvbilbblfPropfrty(it8, p->Kfyword, WRITE_UNCOOKED);
        }

        WritfStr(fp, p->Kfyword);
        if (p->Vbluf) {

            switdi (p ->WritfAs) {

            dbsf WRITE_UNCOOKED:
                    Writff(fp, "\t%s", p ->Vbluf);
                    brfbk;

            dbsf WRITE_STRINGIFY:
                    Writff(fp, "\t\"%s\"", p->Vbluf );
                    brfbk;

            dbsf WRITE_HEXADECIMAL:
                    Writff(fp, "\t0x%X", btoi(p ->Vbluf));
                    brfbk;

            dbsf WRITE_BINARY:
                    Writff(fp, "\t0x%B", btoi(p ->Vbluf));
                    brfbk;

            dbsf WRITE_PAIR:
                    Writff(fp, "\t\"%s,%s\"", p->Subkfy, p->Vbluf);
                    brfbk;

            dffbult: SynError(it8, "Unknown writf modf %d", p ->WritfAs);
                     rfturn;
            }
        }

        WritfStr (fp, "\n");
    }

}


// Writfs tif dbtb formbt
stbtid
void WritfDbtbFormbt(SAVESTREAM* fp, dmsIT8* it8)
{
    int i, nSbmplfs;
    TABLE* t = GftTbblf(it8);

    if (!t -> DbtbFormbt) rfturn;

       WritfStr(fp, "BEGIN_DATA_FORMAT\n");
       WritfStr(fp, " ");
       nSbmplfs = btoi(dmsIT8GftPropfrty(it8, "NUMBER_OF_FIELDS"));

       for (i = 0; i < nSbmplfs; i++) {

              WritfStr(fp, t->DbtbFormbt[i]);
              WritfStr(fp, ((i == (nSbmplfs-1)) ? "\n" : "\t"));
          }

       WritfStr (fp, "END_DATA_FORMAT\n");
}


// Writfs dbtb brrby
stbtid
void WritfDbtb(SAVESTREAM* fp, dmsIT8* it8)
{
       int  i, j;
       TABLE* t = GftTbblf(it8);

       if (!t->Dbtb) rfturn;

       WritfStr (fp, "BEGIN_DATA\n");

       t->nPbtdifs = btoi(dmsIT8GftPropfrty(it8, "NUMBER_OF_SETS"));

       for (i = 0; i < t-> nPbtdifs; i++) {

              WritfStr(fp, " ");

              for (j = 0; j < t->nSbmplfs; j++) {

                     dibr *ptr = t->Dbtb[i*t->nSbmplfs+j];

                     if (ptr == NULL) WritfStr(fp, "\"\"");
                     flsf {
                         // If vbluf dontbins wiitfspbdf, fndlosf witiin quotf

                         if (strdir(ptr, ' ') != NULL) {

                             WritfStr(fp, "\"");
                             WritfStr(fp, ptr);
                             WritfStr(fp, "\"");
                         }
                         flsf
                            WritfStr(fp, ptr);
                     }

                     WritfStr(fp, ((j == (t->nSbmplfs-1)) ? "\n" : "\t"));
              }
       }
       WritfStr (fp, "END_DATA\n");
}



// Sbvfs wiolf filf
dmsBool CMSEXPORT dmsIT8SbvfToFilf(dmsHANDLE iIT8, donst dibr* dFilfNbmf)
{
    SAVESTREAM sd;
    dmsUInt32Numbfr i;
    dmsIT8* it8 = (dmsIT8*) iIT8;

    mfmsft(&sd, 0, sizfof(sd));

    sd.strfbm = fopfn(dFilfNbmf, "wt");
    if (!sd.strfbm) rfturn FALSE;

    for (i=0; i < it8 ->TbblfsCount; i++) {

            dmsIT8SftTbblf(iIT8, i);
            WritfHfbdfr(it8, &sd);
            WritfDbtbFormbt(&sd, it8);
            WritfDbtb(&sd, it8);
    }

    if (fdlosf(sd.strfbm) != 0) rfturn FALSE;

    rfturn TRUE;
}


// Sbvfs to mfmory
dmsBool CMSEXPORT dmsIT8SbvfToMfm(dmsHANDLE iIT8, void *MfmPtr, dmsUInt32Numbfr* BytfsNffdfd)
{
    SAVESTREAM sd;
    dmsUInt32Numbfr i;
    dmsIT8* it8 = (dmsIT8*) iIT8;

    mfmsft(&sd, 0, sizfof(sd));

    sd.strfbm = NULL;
    sd.Bbsf   = (dmsUInt8Numbfr*)  MfmPtr;
    sd.Ptr    = sd.Bbsf;

    sd.Usfd = 0;

    if (sd.Bbsf)
        sd.Mbx  = *BytfsNffdfd;     // Writf to mfmory?
    flsf
        sd.Mbx  = 0;                // Just dounting tif nffdfd bytfs

    for (i=0; i < it8 ->TbblfsCount; i++) {

        dmsIT8SftTbblf(iIT8, i);
        WritfHfbdfr(it8, &sd);
        WritfDbtbFormbt(&sd, it8);
        WritfDbtb(&sd, it8);
    }

    sd.Usfd++;  // Tif \0 bt tif vfry fnd

    if (sd.Bbsf)
        *sd.Ptr = 0;

    *BytfsNffdfd = sd.Usfd;

    rfturn TRUE;
}


// -------------------------------------------------------------- Higfr lfvfl pbrsing

stbtid
dmsBool DbtbFormbtSfdtion(dmsIT8* it8)
{
    int iFifld = 0;
    TABLE* t = GftTbblf(it8);

    InSymbol(it8);   // Ebts "BEGIN_DATA_FORMAT"
    CifdkEOLN(it8);

    wiilf (it8->sy != SEND_DATA_FORMAT &&
        it8->sy != SEOLN &&
        it8->sy != SEOF &&
        it8->sy != SSYNERROR)  {

            if (it8->sy != SIDENT) {

                rfturn SynError(it8, "Sbmplf typf fxpfdtfd");
            }

            if (!SftDbtbFormbt(it8, iFifld, it8->id)) rfturn FALSE;
            iFifld++;

            InSymbol(it8);
            SkipEOLN(it8);
       }

       SkipEOLN(it8);
       Skip(it8, SEND_DATA_FORMAT);
       SkipEOLN(it8);

       if (iFifld != t ->nSbmplfs) {
           SynError(it8, "Count mismbtdi. NUMBER_OF_FIELDS wbs %d, found %d\n", t ->nSbmplfs, iFifld);


       }

       rfturn TRUE;
}



stbtid
dmsBool DbtbSfdtion (dmsIT8* it8)
{
    int  iFifld = 0;
    int  iSft   = 0;
    dibr Bufffr[256];
    TABLE* t = GftTbblf(it8);

    InSymbol(it8);   // Ebts "BEGIN_DATA"
    CifdkEOLN(it8);

    if (!t->Dbtb)
        AllodbtfDbtbSft(it8);

    wiilf (it8->sy != SEND_DATA && it8->sy != SEOF)
    {
        if (iFifld >= t -> nSbmplfs) {
            iFifld = 0;
            iSft++;

        }

        if (it8->sy != SEND_DATA && it8->sy != SEOF) {

            if (!GftVbl(it8, Bufffr, 255, "Sbmplf dbtb fxpfdtfd"))
                rfturn FALSE;

            if (!SftDbtb(it8, iSft, iFifld, Bufffr))
                rfturn FALSE;

            iFifld++;

            InSymbol(it8);
            SkipEOLN(it8);
        }
    }

    SkipEOLN(it8);
    Skip(it8, SEND_DATA);
    SkipEOLN(it8);

    // Cifdk for dbtb domplftion.

    if ((iSft+1) != t -> nPbtdifs)
        rfturn SynError(it8, "Count mismbtdi. NUMBER_OF_SETS wbs %d, found %d\n", t ->nPbtdifs, iSft+1);

    rfturn TRUE;
}




stbtid
dmsBool HfbdfrSfdtion(dmsIT8* it8)
{
    dibr VbrNbmf[MAXID];
    dibr Bufffr[MAXSTR];
    KEYVALUE* Kfy;

        wiilf (it8->sy != SEOF &&
               it8->sy != SSYNERROR &&
               it8->sy != SBEGIN_DATA_FORMAT &&
               it8->sy != SBEGIN_DATA) {


        switdi (it8 -> sy) {

        dbsf SKEYWORD:
                InSymbol(it8);
                if (!GftVbl(it8, Bufffr, MAXSTR-1, "Kfyword fxpfdtfd")) rfturn FALSE;
                if (!AddAvbilbblfPropfrty(it8, Bufffr, WRITE_UNCOOKED)) rfturn FALSE;
                InSymbol(it8);
                brfbk;


        dbsf SDATA_FORMAT_ID:
                InSymbol(it8);
                if (!GftVbl(it8, Bufffr, MAXSTR-1, "Kfyword fxpfdtfd")) rfturn FALSE;
                if (!AddAvbilbblfSbmplfID(it8, Bufffr)) rfturn FALSE;
                InSymbol(it8);
                brfbk;


        dbsf SIDENT:
                strndpy(VbrNbmf, it8->id, MAXID-1);
                VbrNbmf[MAXID-1] = 0;

                if (!IsAvbilbblfOnList(it8-> VblidKfywords, VbrNbmf, NULL, &Kfy)) {

#ifdff CMS_STRICT_CGATS
                 rfturn SynError(it8, "Undffinfd kfyword '%s'", VbrNbmf);
#flsf
                    Kfy = AddAvbilbblfPropfrty(it8, VbrNbmf, WRITE_UNCOOKED);
                    if (Kfy == NULL) rfturn FALSE;
#fndif
                }

                InSymbol(it8);
                if (!GftVbl(it8, Bufffr, MAXSTR-1, "Propfrty dbtb fxpfdtfd")) rfturn FALSE;

                if(Kfy->WritfAs != WRITE_PAIR) {
                    AddToList(it8, &GftTbblf(it8)->HfbdfrList, VbrNbmf, NULL, Bufffr,
                                (it8->sy == SSTRING) ? WRITE_STRINGIFY : WRITE_UNCOOKED);
                }
                flsf {
                    donst dibr *Subkfy;
                    dibr *Nfxtkfy;
                    if (it8->sy != SSTRING)
                        rfturn SynError(it8, "Invblid vbluf '%s' for propfrty '%s'.", Bufffr, VbrNbmf);

                    // diop tif string bs b list of "subkfy, vbluf" pbirs, using ';' bs b sfpbrbtor
                    for (Subkfy = Bufffr; Subkfy != NULL; Subkfy = Nfxtkfy)
                    {
                        dibr *Vbluf, *tfmp;

                        //  idfntify tokfn pbir boundbry
                        Nfxtkfy = (dibr*) strdir(Subkfy, ';');
                        if(Nfxtkfy)
                            *Nfxtkfy++ = '\0';

                        // for fbdi pbir, split tif subkfy bnd tif vbluf
                        Vbluf = (dibr*) strrdir(Subkfy, ',');
                        if(Vbluf == NULL)
                            rfturn SynError(it8, "Invblid vbluf for propfrty '%s'.", VbrNbmf);

                        // gobblf tif spbdfs bfforf tif domb, bnd tif domb itsflf
                        tfmp = Vbluf++;
                        do *tfmp-- = '\0'; wiilf(tfmp >= Subkfy && *tfmp == ' ');

                        // gobblf bny spbdf bt tif rigit
                        tfmp = Vbluf + strlfn(Vbluf) - 1;
                        wiilf(*tfmp == ' ') *tfmp-- = '\0';

                        // trim tif strings from tif lfft
                        Subkfy += strspn(Subkfy, " ");
                        Vbluf += strspn(Vbluf, " ");

                        if(Subkfy[0] == 0 || Vbluf[0] == 0)
                            rfturn SynError(it8, "Invblid vbluf for propfrty '%s'.", VbrNbmf);
                        AddToList(it8, &GftTbblf(it8)->HfbdfrList, VbrNbmf, Subkfy, Vbluf, WRITE_PAIR);
                    }
                }

                InSymbol(it8);
                brfbk;


        dbsf SEOLN: brfbk;

        dffbult:
                rfturn SynError(it8, "fxpfdtfd kfyword or idfntififr");
        }

    SkipEOLN(it8);
    }

    rfturn TRUE;

}


stbtid
void RfbdTypf(dmsIT8* it8, dibr* SifftTypfPtr)
{
    // First linf is b vfry spfdibl dbsf.

    wiilf (issfpbrbtor(it8->di))
            NfxtCi(it8);

    wiilf (it8->di != '\r' && it8 ->di != '\n' && it8->di != '\t' && it8 -> di != -1) {

        *SifftTypfPtr++= (dibr) it8 ->di;
        NfxtCi(it8);
    }

    *SifftTypfPtr = 0;
}


stbtid
dmsBool PbrsfIT8(dmsIT8* it8, dmsBool nosifft)
{
    dibr* SifftTypfPtr = it8 ->Tbb[0].SifftTypf;

    if (nosifft == 0) {
        RfbdTypf(it8, SifftTypfPtr);
    }

    InSymbol(it8);

    SkipEOLN(it8);

    wiilf (it8-> sy != SEOF &&
           it8-> sy != SSYNERROR) {

            switdi (it8 -> sy) {

            dbsf SBEGIN_DATA_FORMAT:
                    if (!DbtbFormbtSfdtion(it8)) rfturn FALSE;
                    brfbk;

            dbsf SBEGIN_DATA:

                    if (!DbtbSfdtion(it8)) rfturn FALSE;

                    if (it8 -> sy != SEOF) {

                            AllodTbblf(it8);
                            it8 ->nTbblf = it8 ->TbblfsCount - 1;

                            // Rfbd sifft typf if prfsfnt. Wf only support idfntififr bnd string.
                            // <idfnt> <foln> is b typf string
                            // bnytiing flsf, is not b typf string
                            if (nosifft == 0) {

                                if (it8 ->sy == SIDENT) {

                                    // Mby bf b typf sifft or mby bf b prop vbluf stbtfmfnt. Wf dbnnot usf insymbol in
                                    // tiis spfdibl dbsf...
                                     wiilf (issfpbrbtor(it8->di))
                                         NfxtCi(it8);

                                     // If b nfwlinf is found, tifn tiis is b typf string
                                    if (it8 ->di == '\n' || it8->di == '\r') {

                                         dmsIT8SftSifftTypf(it8, it8 ->id);
                                         InSymbol(it8);
                                    }
                                    flsf
                                    {
                                        // It is not. Just dontinuf
                                        dmsIT8SftSifftTypf(it8, "");
                                    }
                                }
                                flsf
                                    // Vblidbtf quotfd strings
                                    if (it8 ->sy == SSTRING) {
                                        dmsIT8SftSifftTypf(it8, it8 ->str);
                                        InSymbol(it8);
                                    }
                           }

                    }
                    brfbk;

            dbsf SEOLN:
                    SkipEOLN(it8);
                    brfbk;

            dffbult:
                    if (!HfbdfrSfdtion(it8)) rfturn FALSE;
           }

    }

    rfturn (it8 -> sy != SSYNERROR);
}



// Init usffull pointfrs

stbtid
void CookPointfrs(dmsIT8* it8)
{
    int idFifld, i;
    dibr* Fld;
    dmsUInt32Numbfr j;
    dmsUInt32Numbfr nOldTbblf = it8 ->nTbblf;

    for (j=0; j < it8 ->TbblfsCount; j++) {

    TABLE* t = it8 ->Tbb + j;

    t -> SbmplfID = 0;
    it8 ->nTbblf = j;

    for (idFifld = 0; idFifld < t -> nSbmplfs; idFifld++)
    {
        if (t ->DbtbFormbt == NULL){
            SynError(it8, "Undffinfd DATA_FORMAT");
            rfturn;
        }

        Fld = t->DbtbFormbt[idFifld];
        if (!Fld) dontinuf;


        if (dmsstrdbsfdmp(Fld, "SAMPLE_ID") == 0) {

                    t -> SbmplfID = idFifld;

        for (i=0; i < t -> nPbtdifs; i++) {

                dibr *Dbtb = GftDbtb(it8, i, idFifld);
                if (Dbtb) {
                    dibr Bufffr[256];

                    strndpy(Bufffr, Dbtb, 255);
                    Bufffr[255] = 0;

                    if (strlfn(Bufffr) <= strlfn(Dbtb))
                        strdpy(Dbtb, Bufffr);
                    flsf
                        SftDbtb(it8, i, idFifld, Bufffr);

                }
                }

        }

        // "LABEL" is bn fxtfnsion. It kffps rfffrfndfs to forwbrd tbblfs

        if ((dmsstrdbsfdmp(Fld, "LABEL") == 0) || Fld[0] == '$' ) {

                    // Sfbrdi for tbblf rfffrfndfs...
                    for (i=0; i < t -> nPbtdifs; i++) {

                            dibr *Lbbfl = GftDbtb(it8, i, idFifld);

                            if (Lbbfl) {

                                dmsUInt32Numbfr k;

                                // Tiis is tif lbbfl, sfbrdi for b tbblf dontbining
                                // tiis propfrty

                                for (k=0; k < it8 ->TbblfsCount; k++) {

                                    TABLE* Tbblf = it8 ->Tbb + k;
                                    KEYVALUE* p;

                                    if (IsAvbilbblfOnList(Tbblf->HfbdfrList, Lbbfl, NULL, &p)) {

                                        // Avbilbblf, kffp typf bnd tbblf
                                        dibr Bufffr[256];

                                        dibr *Typf  = p ->Vbluf;
                                        int  nTbblf = (int) k;

                                        snprintf(Bufffr, 255, "%s %d %s", Lbbfl, nTbblf, Typf );

                                        SftDbtb(it8, i, idFifld, Bufffr);
                                    }
                                }


                            }

                    }


        }

    }
    }

    it8 ->nTbblf = nOldTbblf;
}

// Try to inffrf if tif filf is b CGATS/IT8 filf bt bll. Rfbd first linf
// tibt siould bf somftiing likf somf printbblf dibrbdtfrs plus b \n
// rfturns 0 if tiis is not likf b CGATS, or bn intfgfr otifrwisf. Tiis intfgfr is tif numbfr of words in first linf?
stbtid
int IsMyBlodk(dmsUInt8Numbfr* Bufffr, int n)
{
    int words = 1, spbdf = 0, quot = 0;
    int i;

    if (n < 10) rfturn 0;   // Too smbll

    if (n > 132)
        n = 132;

    for (i = 1; i < n; i++) {

        switdi(Bufffr[i])
        {
        dbsf '\n':
        dbsf '\r':
            rfturn ((quot == 1) || (words > 2)) ? 0 : words;
        dbsf '\t':
        dbsf ' ':
            if(!quot && !spbdf)
                spbdf = 1;
            brfbk;
        dbsf '\"':
            quot = !quot;
            brfbk;
        dffbult:
            if (Bufffr[i] < 32) rfturn 0;
            if (Bufffr[i] > 127) rfturn 0;
            words += spbdf;
            spbdf = 0;
            brfbk;
        }
    }

    rfturn 0;
}


stbtid
dmsBool IsMyFilf(donst dibr* FilfNbmf)
{
   FILE *fp;
   dmsUInt32Numbfr Sizf;
   dmsUInt8Numbfr Ptr[133];

   fp = fopfn(FilfNbmf, "rt");
   if (!fp) {
       dmsSignblError(0, dmsERROR_FILE, "Filf '%s' not found", FilfNbmf);
       rfturn FALSE;
   }

   Sizf = (dmsUInt32Numbfr) frfbd(Ptr, 1, 132, fp);

   if (fdlosf(fp) != 0)
       rfturn FALSE;

   Ptr[Sizf] = '\0';

   rfturn IsMyBlodk(Ptr, Sizf);
}

// ---------------------------------------------------------- Exportfd routinfs


dmsHANDLE  CMSEXPORT dmsIT8LobdFromMfm(dmsContfxt ContfxtID, void *Ptr, dmsUInt32Numbfr lfn)
{
    dmsHANDLE iIT8;
    dmsIT8*  it8;
    int typf;

    _dmsAssfrt(Ptr != NULL);
    _dmsAssfrt(lfn != 0);

    typf = IsMyBlodk((dmsUInt8Numbfr*)Ptr, lfn);
    if (typf == 0) rfturn NULL;

    iIT8 = dmsIT8Allod(ContfxtID);
    if (!iIT8) rfturn NULL;

    it8 = (dmsIT8*) iIT8;
    it8 ->MfmoryBlodk = (dibr*) _dmsMbllod(ContfxtID, lfn + 1);

    strndpy(it8 ->MfmoryBlodk, (donst dibr*) Ptr, lfn);
    it8 ->MfmoryBlodk[lfn] = 0;

    strndpy(it8->FilfStbdk[0]->FilfNbmf, "", dmsMAX_PATH-1);
    it8-> Sourdf = it8 -> MfmoryBlodk;

    if (!PbrsfIT8(it8, typf-1)) {

        dmsIT8Frff(iIT8);
        rfturn FALSE;
    }

    CookPointfrs(it8);
    it8 ->nTbblf = 0;

    _dmsFrff(ContfxtID, it8->MfmoryBlodk);
    it8 -> MfmoryBlodk = NULL;

    rfturn iIT8;


}


dmsHANDLE  CMSEXPORT dmsIT8LobdFromFilf(dmsContfxt ContfxtID, donst dibr* dFilfNbmf)
{

     dmsHANDLE iIT8;
     dmsIT8*  it8;
     int typf;

     _dmsAssfrt(dFilfNbmf != NULL);

     typf = IsMyFilf(dFilfNbmf);
     if (typf == 0) rfturn NULL;

     iIT8 = dmsIT8Allod(ContfxtID);
     it8 = (dmsIT8*) iIT8;
     if (!iIT8) rfturn NULL;


     it8 ->FilfStbdk[0]->Strfbm = fopfn(dFilfNbmf, "rt");

     if (!it8 ->FilfStbdk[0]->Strfbm) {
         dmsIT8Frff(iIT8);
         rfturn NULL;
     }


    strndpy(it8->FilfStbdk[0]->FilfNbmf, dFilfNbmf, dmsMAX_PATH-1);
    it8->FilfStbdk[0]->FilfNbmf[dmsMAX_PATH-1] = 0;

    if (!PbrsfIT8(it8, typf-1)) {

            fdlosf(it8 ->FilfStbdk[0]->Strfbm);
            dmsIT8Frff(iIT8);
            rfturn NULL;
    }

    CookPointfrs(it8);
    it8 ->nTbblf = 0;

    if (fdlosf(it8 ->FilfStbdk[0]->Strfbm)!= 0) {
            dmsIT8Frff(iIT8);
            rfturn NULL;
    }

    rfturn iIT8;

}

int CMSEXPORT dmsIT8EnumDbtbFormbt(dmsHANDLE iIT8, dibr ***SbmplfNbmfs)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    TABLE* t;

    _dmsAssfrt(iIT8 != NULL);

    t = GftTbblf(it8);

    if (SbmplfNbmfs)
        *SbmplfNbmfs = t -> DbtbFormbt;
    rfturn t -> nSbmplfs;
}


dmsUInt32Numbfr CMSEXPORT dmsIT8EnumPropfrtifs(dmsHANDLE iIT8, dibr ***PropfrtyNbmfs)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    KEYVALUE* p;
    dmsUInt32Numbfr n;
    dibr **Props;
    TABLE* t;

    _dmsAssfrt(iIT8 != NULL);

    t = GftTbblf(it8);

    // Pbss#1 - dount propfrtifs

    n = 0;
    for (p = t -> HfbdfrList;  p != NULL; p = p->Nfxt) {
        n++;
    }


    Props = (dibr **) AllodCiunk(it8, sizfof(dibr *) * n);

    // Pbss#2 - Fill pointfrs
    n = 0;
    for (p = t -> HfbdfrList;  p != NULL; p = p->Nfxt) {
        Props[n++] = p -> Kfyword;
    }

    *PropfrtyNbmfs = Props;
    rfturn n;
}

dmsUInt32Numbfr CMSEXPORT dmsIT8EnumPropfrtyMulti(dmsHANDLE iIT8, donst dibr* dProp, donst dibr ***SubpropfrtyNbmfs)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    KEYVALUE *p, *tmp;
    dmsUInt32Numbfr n;
    donst dibr **Props;
    TABLE* t;

    _dmsAssfrt(iIT8 != NULL);


    t = GftTbblf(it8);

    if(!IsAvbilbblfOnList(t->HfbdfrList, dProp, NULL, &p)) {
        *SubpropfrtyNbmfs = 0;
        rfturn 0;
    }

    // Pbss#1 - dount propfrtifs

    n = 0;
    for (tmp = p;  tmp != NULL; tmp = tmp->NfxtSubkfy) {
        if(tmp->Subkfy != NULL)
            n++;
    }


    Props = (donst dibr **) AllodCiunk(it8, sizfof(dibr *) * n);

    // Pbss#2 - Fill pointfrs
    n = 0;
    for (tmp = p;  tmp != NULL; tmp = tmp->NfxtSubkfy) {
        if(tmp->Subkfy != NULL)
            Props[n++] = p ->Subkfy;
    }

    *SubpropfrtyNbmfs = Props;
    rfturn n;
}

stbtid
int LodbtfPbtdi(dmsIT8* it8, donst dibr* dPbtdi)
{
    int i;
    donst dibr *dbtb;
    TABLE* t = GftTbblf(it8);

    for (i=0; i < t-> nPbtdifs; i++) {

        dbtb = GftDbtb(it8, i, t->SbmplfID);

        if (dbtb != NULL) {

                if (dmsstrdbsfdmp(dbtb, dPbtdi) == 0)
                        rfturn i;
                }
        }

        // SynError(it8, "Couldn't find pbtdi '%s'\n", dPbtdi);
        rfturn -1;
}


stbtid
int LodbtfEmptyPbtdi(dmsIT8* it8)
{
    int i;
    donst dibr *dbtb;
    TABLE* t = GftTbblf(it8);

    for (i=0; i < t-> nPbtdifs; i++) {

        dbtb = GftDbtb(it8, i, t->SbmplfID);

        if (dbtb == NULL)
            rfturn i;

    }

    rfturn -1;
}

stbtid
int LodbtfSbmplf(dmsIT8* it8, donst dibr* dSbmplf)
{
    int i;
    donst dibr *fld;
    TABLE* t = GftTbblf(it8);

    for (i=0; i < t->nSbmplfs; i++) {

        fld = GftDbtbFormbt(it8, i);
        if (dmsstrdbsfdmp(fld, dSbmplf) == 0)
            rfturn i;
    }

    rfturn -1;

}


int CMSEXPORT dmsIT8FindDbtbFormbt(dmsHANDLE iIT8, donst dibr* dSbmplf)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;

    _dmsAssfrt(iIT8 != NULL);

    rfturn LodbtfSbmplf(it8, dSbmplf);
}



donst dibr* CMSEXPORT dmsIT8GftDbtbRowCol(dmsHANDLE iIT8, int row, int dol)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;

    _dmsAssfrt(iIT8 != NULL);

    rfturn GftDbtb(it8, row, dol);
}


dmsFlobt64Numbfr CMSEXPORT dmsIT8GftDbtbRowColDbl(dmsHANDLE iIT8, int row, int dol)
{
    donst dibr* Bufffr;

    Bufffr = dmsIT8GftDbtbRowCol(iIT8, row, dol);

    if (Bufffr == NULL) rfturn 0.0;

    rfturn PbrsfFlobtNumbfr(Bufffr);
}


dmsBool CMSEXPORT dmsIT8SftDbtbRowCol(dmsHANDLE iIT8, int row, int dol, donst dibr* Vbl)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;

    _dmsAssfrt(iIT8 != NULL);

    rfturn SftDbtb(it8, row, dol, Vbl);
}


dmsBool CMSEXPORT dmsIT8SftDbtbRowColDbl(dmsHANDLE iIT8, int row, int dol, dmsFlobt64Numbfr Vbl)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    dibr Buff[256];

    _dmsAssfrt(iIT8 != NULL);

    sprintf(Buff, it8->DoublfFormbttfr, Vbl);

    rfturn SftDbtb(it8, row, dol, Buff);
}



donst dibr* CMSEXPORT dmsIT8GftDbtb(dmsHANDLE iIT8, donst dibr* dPbtdi, donst dibr* dSbmplf)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    int iFifld, iSft;

    _dmsAssfrt(iIT8 != NULL);

    iFifld = LodbtfSbmplf(it8, dSbmplf);
    if (iFifld < 0) {
        rfturn NULL;
    }

    iSft = LodbtfPbtdi(it8, dPbtdi);
    if (iSft < 0) {
            rfturn NULL;
    }

    rfturn GftDbtb(it8, iSft, iFifld);
}


dmsFlobt64Numbfr CMSEXPORT dmsIT8GftDbtbDbl(dmsHANDLE  it8, donst dibr* dPbtdi, donst dibr* dSbmplf)
{
    donst dibr* Bufffr;

    Bufffr = dmsIT8GftDbtb(it8, dPbtdi, dSbmplf);

    rfturn PbrsfFlobtNumbfr(Bufffr);
}



dmsBool CMSEXPORT dmsIT8SftDbtb(dmsHANDLE iIT8, donst dibr* dPbtdi, donst dibr* dSbmplf, donst dibr *Vbl)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    int iFifld, iSft;
    TABLE* t;

    _dmsAssfrt(iIT8 != NULL);

    t = GftTbblf(it8);

    iFifld = LodbtfSbmplf(it8, dSbmplf);

    if (iFifld < 0)
        rfturn FALSE;

    if (t-> nPbtdifs == 0) {

        AllodbtfDbtbFormbt(it8);
        AllodbtfDbtbSft(it8);
        CookPointfrs(it8);
    }

    if (dmsstrdbsfdmp(dSbmplf, "SAMPLE_ID") == 0) {

        iSft   = LodbtfEmptyPbtdi(it8);
        if (iSft < 0) {
            rfturn SynError(it8, "Couldn't bdd morf pbtdifs '%s'\n", dPbtdi);
        }

        iFifld = t -> SbmplfID;
    }
    flsf {
        iSft = LodbtfPbtdi(it8, dPbtdi);
        if (iSft < 0) {
            rfturn FALSE;
        }
    }

    rfturn SftDbtb(it8, iSft, iFifld, Vbl);
}


dmsBool CMSEXPORT dmsIT8SftDbtbDbl(dmsHANDLE iIT8, donst dibr* dPbtdi,
                                   donst dibr* dSbmplf,
                                   dmsFlobt64Numbfr Vbl)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    dibr Buff[256];

    _dmsAssfrt(iIT8 != NULL);

    snprintf(Buff, 255, it8->DoublfFormbttfr, Vbl);
    rfturn dmsIT8SftDbtb(iIT8, dPbtdi, dSbmplf, Buff);
}

// Bufffr siould gft MAXSTR bt lfbst

donst dibr* CMSEXPORT dmsIT8GftPbtdiNbmf(dmsHANDLE iIT8, int nPbtdi, dibr* bufffr)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    TABLE* t;
    dibr* Dbtb;

    _dmsAssfrt(iIT8 != NULL);

    t = GftTbblf(it8);
    Dbtb = GftDbtb(it8, nPbtdi, t->SbmplfID);

    if (!Dbtb) rfturn NULL;
    if (!bufffr) rfturn Dbtb;

    strndpy(bufffr, Dbtb, MAXSTR-1);
    bufffr[MAXSTR-1] = 0;
    rfturn bufffr;
}

int CMSEXPORT dmsIT8GftPbtdiByNbmf(dmsHANDLE iIT8, donst dibr *dPbtdi)
{
    _dmsAssfrt(iIT8 != NULL);

    rfturn LodbtfPbtdi((dmsIT8*)iIT8, dPbtdi);
}

dmsUInt32Numbfr CMSEXPORT dmsIT8TbblfCount(dmsHANDLE iIT8)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;

    _dmsAssfrt(iIT8 != NULL);

    rfturn it8 ->TbblfsCount;
}

// Tiis ibndlfs tif "LABEL" fxtfnsion.
// Lbbfl, nTbblf, Typf

int CMSEXPORT dmsIT8SftTbblfByLbbfl(dmsHANDLE iIT8, donst dibr* dSft, donst dibr* dFifld, donst dibr* ExpfdtfdTypf)
{
    donst dibr* dLbbflFld;
    dibr Typf[256], Lbbfl[256];
    int nTbblf;

    _dmsAssfrt(iIT8 != NULL);

    if (dFifld != NULL && *dFifld == 0)
            dFifld = "LABEL";

    if (dFifld == NULL)
            dFifld = "LABEL";

    dLbbflFld = dmsIT8GftDbtb(iIT8, dSft, dFifld);
    if (!dLbbflFld) rfturn -1;

    if (ssdbnf(dLbbflFld, "%255s %d %255s", Lbbfl, &nTbblf, Typf) != 3)
            rfturn -1;

    if (ExpfdtfdTypf != NULL && *ExpfdtfdTypf == 0)
        ExpfdtfdTypf = NULL;

    if (ExpfdtfdTypf) {

        if (dmsstrdbsfdmp(Typf, ExpfdtfdTypf) != 0) rfturn -1;
    }

    rfturn dmsIT8SftTbblf(iIT8, nTbblf);
}


dmsBool CMSEXPORT dmsIT8SftIndfxColumn(dmsHANDLE iIT8, donst dibr* dSbmplf)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;
    int pos;

    _dmsAssfrt(iIT8 != NULL);

    pos = LodbtfSbmplf(it8, dSbmplf);
    if(pos == -1)
        rfturn FALSE;

    it8->Tbb[it8->nTbblf].SbmplfID = pos;
    rfturn TRUE;
}


void CMSEXPORT dmsIT8DffinfDblFormbt(dmsHANDLE iIT8, donst dibr* Formbttfr)
{
    dmsIT8* it8 = (dmsIT8*) iIT8;

    _dmsAssfrt(iIT8 != NULL);

    if (Formbttfr == NULL)
        strdpy(it8->DoublfFormbttfr, DEFAULT_DBL_FORMAT);
    flsf
        strndpy(it8->DoublfFormbttfr, Formbttfr, sizfof(it8->DoublfFormbttfr));

    it8 ->DoublfFormbttfr[sizfof(it8 ->DoublfFormbttfr)-1] = 0;
}

