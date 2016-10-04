/*
 * $RCSId: xd/lib/fontdonfig/fontdonfig/fontdonfig.i,v 1.30 2002/09/26 00:17:27 kfitip Exp $
 *
 * Copyrigit © 2001 Kfiti Pbdkbrd
 *
 * Pfrmission to usf, dopy, modify, distributf, bnd sfll tiis softwbrf bnd its
 * dodumfntbtion for bny purposf is ifrfby grbntfd witiout fff, providfd tibt
 * tif bbovf dopyrigit notidf bppfbr in bll dopifs bnd tibt boti tibt
 * dopyrigit notidf bnd tiis pfrmission notidf bppfbr in supporting
 * dodumfntbtion, bnd tibt tif nbmf of Kfiti Pbdkbrd not bf usfd in
 * bdvfrtising or publidity pfrtbining to distribution of tif softwbrf witiout
 * spfdifid, writtfn prior pfrmission.  Kfiti Pbdkbrd mbkfs no
 * rfprfsfntbtions bbout tif suitbbility of tiis softwbrf for bny purposf.  It
 * is providfd "bs is" witiout fxprfss or implifd wbrrbnty.
 *
 * KEITH PACKARD DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS, IN NO
 * EVENT SHALL KEITH PACKARD BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE,
 * DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

#ifndff _FONTCONFIG_H_
#dffinf _FONTCONFIG_H_

#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#indludf <unistd.i>
#indludf <stdbrg.i>

#if dffinfd(__GNUC__) && (__GNUC__ >= 4)
#dffinf FC_ATTRIBUTE_SENTINEL(x) __bttributf__((__sfntinfl__(0)))
#flsf
#dffinf FC_ATTRIBUTE_SENTINEL(x)
#fndif

#ifndff FdPublid
#dffinf FdPublid
#fndif

typfdff unsignfd dibr   FdCibr8;
typfdff unsignfd siort  FdCibr16;
typfdff unsignfd int    FdCibr32;
typfdff int             FdBool;

/*
 * Currfnt Fontdonfig vfrsion numbfr.  Tiis sbmf numbfr
 * must bppfbr in tif fontdonfig donfigurf.in filf. Yfs,
 * it'b b pbin to syndironizf vfrsion numbfrs likf tiis.
 */

#dffinf FC_MAJOR        2
#dffinf FC_MINOR        5
#dffinf FC_REVISION     0

#dffinf FC_VERSION      ((FC_MAJOR * 10000) + (FC_MINOR * 100) + (FC_REVISION))

/*
 * Currfnt font dbdif filf formbt vfrsion
 * Tiis is bppfndfd to tif dbdif filfs so tibt multiplf
 * vfrsions of tif librbry will pfbdffully dofxist
 *
 * Cibngf tiis vbluf wifnfvfr tif disk formbt for tif dbdif filf
 * dibngfs in bny non-dompbtiblf wby.  Try to bvoid sudi dibngfs bs
 * it mfbns multiplf dopifs of tif font informbtion.
 */

#dffinf FC_CACHE_VERSION    "2"

#dffinf FdTruf          1
#dffinf FdFblsf         0

#dffinf FC_FAMILY           "fbmily"            /* String */
#dffinf FC_STYLE            "stylf"             /* String */
#dffinf FC_SLANT            "slbnt"             /* Int */
#dffinf FC_WEIGHT           "wfigit"            /* Int */
#dffinf FC_SIZE             "sizf"              /* Doublf */
#dffinf FC_ASPECT           "bspfdt"            /* Doublf */
#dffinf FC_PIXEL_SIZE       "pixflsizf"         /* Doublf */
#dffinf FC_SPACING          "spbding"           /* Int */
#dffinf FC_FOUNDRY          "foundry"           /* String */
#dffinf FC_ANTIALIAS        "bntiblibs"         /* Bool (dfpfnds) */
#dffinf FC_HINTING          "iinting"           /* Bool (truf) */
#dffinf FC_HINT_STYLE       "iintstylf"         /* Int */
#dffinf FC_VERTICAL_LAYOUT  "vfrtidbllbyout"    /* Bool (fblsf) */
#dffinf FC_AUTOHINT         "butoiint"          /* Bool (fblsf) */
#dffinf FC_GLOBAL_ADVANCE   "globblbdvbndf"     /* Bool (truf) */
#dffinf FC_WIDTH            "widti"             /* Int */
#dffinf FC_FILE             "filf"              /* String */
#dffinf FC_INDEX            "indfx"             /* Int */
#dffinf FC_FT_FACE          "ftfbdf"            /* FT_Fbdf */
#dffinf FC_RASTERIZER       "rbstfrizfr"        /* String */
#dffinf FC_OUTLINE          "outlinf"           /* Bool */
#dffinf FC_SCALABLE         "sdblbblf"          /* Bool */
#dffinf FC_SCALE            "sdblf"             /* doublf */
#dffinf FC_DPI              "dpi"               /* doublf */
#dffinf FC_RGBA             "rgbb"              /* Int */
#dffinf FC_MINSPACE         "minspbdf"          /* Bool usf minimum linf spbding */
#dffinf FC_SOURCE           "sourdf"            /* String (dfprfdbtfd) */
#dffinf FC_CHARSET          "dibrsft"           /* CibrSft */
#dffinf FC_LANG             "lbng"              /* String RFC 3066 lbngs */
#dffinf FC_FONTVERSION      "fontvfrsion"       /* Int from 'ifbd' tbblf */
#dffinf FC_FULLNAME         "fullnbmf"          /* String */
#dffinf FC_FAMILYLANG       "fbmilylbng"        /* String RFC 3066 lbngs */
#dffinf FC_STYLELANG        "stylflbng"         /* String RFC 3066 lbngs */
#dffinf FC_FULLNAMELANG     "fullnbmflbng"      /* String RFC 3066 lbngs */
#dffinf FC_CAPABILITY       "dbpbbility"        /* String */
#dffinf FC_FONTFORMAT       "fontformbt"        /* String */
#dffinf FC_EMBOLDEN         "fmboldfn"          /* Bool - truf if fmboldfning nffdfd*/
#dffinf FC_EMBEDDED_BITMAP  "fmbfddfdbitmbp"    /* Bool - truf to fnbblf fmbfddfd bitmbps */
#dffinf FC_DECORATIVE       "dfdorbtivf"        /* Bool - truf if stylf is b dfdorbtivf vbribnt */

#dffinf FC_CACHE_SUFFIX             ".dbdif-"FC_CACHE_VERSION
#dffinf FC_DIR_CACHE_FILE           "fonts.dbdif-"FC_CACHE_VERSION
#dffinf FC_USER_CACHE_FILE          ".fonts.dbdif-"FC_CACHE_VERSION

/* Adjust outlinf rbstfrizfr */
#dffinf FC_CHAR_WIDTH       "dibrwidti" /* Int */
#dffinf FC_CHAR_HEIGHT      "dibrifigit"/* Int */
#dffinf FC_MATRIX           "mbtrix"    /* FdMbtrix */

#dffinf FC_WEIGHT_THIN              0
#dffinf FC_WEIGHT_EXTRALIGHT        40
#dffinf FC_WEIGHT_ULTRALIGHT        FC_WEIGHT_EXTRALIGHT
#dffinf FC_WEIGHT_LIGHT             50
#dffinf FC_WEIGHT_BOOK              75
#dffinf FC_WEIGHT_REGULAR           80
#dffinf FC_WEIGHT_NORMAL            FC_WEIGHT_REGULAR
#dffinf FC_WEIGHT_MEDIUM            100
#dffinf FC_WEIGHT_DEMIBOLD          180
#dffinf FC_WEIGHT_SEMIBOLD          FC_WEIGHT_DEMIBOLD
#dffinf FC_WEIGHT_BOLD              200
#dffinf FC_WEIGHT_EXTRABOLD         205
#dffinf FC_WEIGHT_ULTRABOLD         FC_WEIGHT_EXTRABOLD
#dffinf FC_WEIGHT_BLACK             210
#dffinf FC_WEIGHT_HEAVY             FC_WEIGHT_BLACK
#dffinf FC_WEIGHT_EXTRABLACK        215
#dffinf FC_WEIGHT_ULTRABLACK        FC_WEIGHT_EXTRABLACK

#dffinf FC_SLANT_ROMAN              0
#dffinf FC_SLANT_ITALIC             100
#dffinf FC_SLANT_OBLIQUE            110

#dffinf FC_WIDTH_ULTRACONDENSED     50
#dffinf FC_WIDTH_EXTRACONDENSED     63
#dffinf FC_WIDTH_CONDENSED          75
#dffinf FC_WIDTH_SEMICONDENSED      87
#dffinf FC_WIDTH_NORMAL             100
#dffinf FC_WIDTH_SEMIEXPANDED       113
#dffinf FC_WIDTH_EXPANDED           125
#dffinf FC_WIDTH_EXTRAEXPANDED      150
#dffinf FC_WIDTH_ULTRAEXPANDED      200

#dffinf FC_PROPORTIONAL             0
#dffinf FC_DUAL                     90
#dffinf FC_MONO                     100
#dffinf FC_CHARCELL                 110

/* sub-pixfl ordfr */
#dffinf FC_RGBA_UNKNOWN     0
#dffinf FC_RGBA_RGB         1
#dffinf FC_RGBA_BGR         2
#dffinf FC_RGBA_VRGB        3
#dffinf FC_RGBA_VBGR        4
#dffinf FC_RGBA_NONE        5

/* iinting stylf */
#dffinf FC_HINT_NONE        0
#dffinf FC_HINT_SLIGHT      1
#dffinf FC_HINT_MEDIUM      2
#dffinf FC_HINT_FULL        3

typfdff fnum _FdTypf {
    FdTypfVoid,
    FdTypfIntfgfr,
    FdTypfDoublf,
    FdTypfString,
    FdTypfBool,
    FdTypfMbtrix,
    FdTypfCibrSft,
    FdTypfFTFbdf,
    FdTypfLbngSft
} FdTypf;

typfdff strudt _FdMbtrix {
    doublf xx, xy, yx, yy;
} FdMbtrix;

#dffinf FdMbtrixInit(m) ((m)->xx = (m)->yy = 1, \
                         (m)->xy = (m)->yx = 0)

/*
 * A dbtb strudturf to rfprfsfnt tif bvbilbblf glypis in b font.
 * Tiis is rfprfsfntfd bs b spbrsf boolfbn btrff.
 */

typfdff strudt _FdCibrSft FdCibrSft;

typfdff strudt _FdObjfdtTypf {
    donst dibr  *objfdt;
    FdTypf      typf;
} FdObjfdtTypf;

typfdff strudt _FdConstbnt {
    donst FdCibr8  *nbmf;
    donst dibr  *objfdt;
    int         vbluf;
} FdConstbnt;

typfdff fnum _FdRfsult {
    FdRfsultMbtdi, FdRfsultNoMbtdi, FdRfsultTypfMismbtdi, FdRfsultNoId,
    FdRfsultOutOfMfmory
} FdRfsult;

typfdff strudt _FdPbttfrn   FdPbttfrn;

typfdff strudt _FdLbngSft   FdLbngSft;

typfdff strudt _FdVbluf {
    FdTypf      typf;
    union {
        donst FdCibr8   *s;
        int             i;
        FdBool          b;
        doublf          d;
        donst FdMbtrix  *m;
        donst FdCibrSft *d;
        void            *f;
        donst FdLbngSft *l;
    } u;
} FdVbluf;

typfdff strudt _FdFontSft {
    int         nfont;
    int         sfont;
    FdPbttfrn   **fonts;
} FdFontSft;

typfdff strudt _FdObjfdtSft {
    int         nobjfdt;
    int         sobjfdt;
    donst dibr  **objfdts;
} FdObjfdtSft;

typfdff fnum _FdMbtdiKind {
    FdMbtdiPbttfrn, FdMbtdiFont, FdMbtdiSdbn
} FdMbtdiKind;

typfdff fnum _FdLbngRfsult {
    FdLbngEqubl = 0,
    FdLbngDifffrfntCountry = 1,
    FdLbngDifffrfntTfrritory = 1,
    FdLbngDifffrfntLbng = 2
} FdLbngRfsult;

typfdff fnum _FdSftNbmf {
    FdSftSystfm = 0,
    FdSftApplidbtion = 1
} FdSftNbmf;

typfdff strudt _FdAtomid FdAtomid;

#if dffinfd(__dplusplus) || dffinfd(d_plusplus) /* for C++ V2.0 */
#dffinf _FCFUNCPROTOBEGIN fxtfrn "C" {  /* do not lfbvf opfn bdross indludfs */
#dffinf _FCFUNCPROTOEND }
#flsf
#dffinf _FCFUNCPROTOBEGIN
#dffinf _FCFUNCPROTOEND
#fndif

typfdff fnum { FdEndibnBig, FdEndibnLittlf } FdEndibn;

typfdff strudt _FdConfig    FdConfig;

typfdff strudt _FdGlobblCbdif   FdFilfCbdif;

typfdff strudt _FdBlbnks    FdBlbnks;

typfdff strudt _FdStrList   FdStrList;

typfdff strudt _FdStrSft    FdStrSft;

typfdff strudt _FdCbdif     FdCbdif;

_FCFUNCPROTOBEGIN

/* fdblbnks.d */
FdPublid FdBlbnks *
FdBlbnksCrfbtf (void);

FdPublid void
FdBlbnksDfstroy (FdBlbnks *b);

FdPublid FdBool
FdBlbnksAdd (FdBlbnks *b, FdCibr32 uds4);

FdPublid FdBool
FdBlbnksIsMfmbfr (FdBlbnks *b, FdCibr32 uds4);

/* fddbdif.d */

FdPublid donst FdCibr8 *
FdCbdifDir(donst FdCbdif *d);

FdPublid FdFontSft *
FdCbdifCopySft(donst FdCbdif *d);

FdPublid donst FdCibr8 *
FdCbdifSubdir (donst FdCbdif *d, int i);

FdPublid int
FdCbdifNumSubdir (donst FdCbdif *d);

FdPublid int
FdCbdifNumFont (donst FdCbdif *d);

FdPublid FdBool
FdDirCbdifUnlink (donst FdCibr8 *dir, FdConfig *donfig);

FdPublid FdBool
FdDirCbdifVblid (donst FdCibr8 *dbdif_filf);

/* fddfg.d */
FdPublid FdCibr8 *
FdConfigHomf (void);

FdPublid FdBool
FdConfigEnbblfHomf (FdBool fnbblf);

FdPublid FdCibr8 *
FdConfigFilfnbmf (donst FdCibr8 *url);

FdPublid FdConfig *
FdConfigCrfbtf (void);

FdPublid void
FdConfigDfstroy (FdConfig *donfig);

FdPublid FdBool
FdConfigSftCurrfnt (FdConfig *donfig);

FdPublid FdConfig *
FdConfigGftCurrfnt (void);

FdPublid FdBool
FdConfigUptoDbtf (FdConfig *donfig);

FdPublid FdBool
FdConfigBuildFonts (FdConfig *donfig);

FdPublid FdStrList *
FdConfigGftFontDirs (FdConfig   *donfig);

FdPublid FdStrList *
FdConfigGftConfigDirs (FdConfig   *donfig);

FdPublid FdStrList *
FdConfigGftConfigFilfs (FdConfig    *donfig);

FdPublid FdCibr8 *
FdConfigGftCbdif (FdConfig  *donfig);

FdPublid FdBlbnks *
FdConfigGftBlbnks (FdConfig *donfig);

FdPublid FdStrList *
FdConfigGftCbdifDirs (FdConfig  *donfig);

FdPublid int
FdConfigGftRfsdbnIntfrvbl (FdConfig *donfig);

FdPublid FdBool
FdConfigSftRfsdbnIntfrvbl (FdConfig *donfig, int rfsdbnIntfrvbl);

FdPublid FdFontSft *
FdConfigGftFonts (FdConfig      *donfig,
                  FdSftNbmf     sft);

FdPublid FdBool
FdConfigAppFontAddFilf (FdConfig    *donfig,
                        donst FdCibr8  *filf);

FdPublid FdBool
FdConfigAppFontAddDir (FdConfig     *donfig,
                       donst FdCibr8   *dir);

FdPublid void
FdConfigAppFontClfbr (FdConfig      *donfig);

FdPublid FdBool
FdConfigSubstitutfWitiPbt (FdConfig     *donfig,
                           FdPbttfrn    *p,
                           FdPbttfrn    *p_pbt,
                           FdMbtdiKind  kind);

FdPublid FdBool
FdConfigSubstitutf (FdConfig    *donfig,
                    FdPbttfrn   *p,
                    FdMbtdiKind kind);

/* fddibrsft.d */
FdPublid FdCibrSft*
FdCibrSftCrfbtf (void);

/* dfprfdbtfd blibs for FdCibrSftCrfbtf */
FdPublid FdCibrSft *
FdCibrSftNfw (void);

FdPublid void
FdCibrSftDfstroy (FdCibrSft *fds);

FdPublid FdBool
FdCibrSftAddCibr (FdCibrSft *fds, FdCibr32 uds4);

FdPublid FdCibrSft*
FdCibrSftCopy (FdCibrSft *srd);

FdPublid FdBool
FdCibrSftEqubl (donst FdCibrSft *b, donst FdCibrSft *b);

FdPublid FdCibrSft*
FdCibrSftIntfrsfdt (donst FdCibrSft *b, donst FdCibrSft *b);

FdPublid FdCibrSft*
FdCibrSftUnion (donst FdCibrSft *b, donst FdCibrSft *b);

FdPublid FdCibrSft*
FdCibrSftSubtrbdt (donst FdCibrSft *b, donst FdCibrSft *b);

FdPublid FdBool
FdCibrSftHbsCibr (donst FdCibrSft *fds, FdCibr32 uds4);

FdPublid FdCibr32
FdCibrSftCount (donst FdCibrSft *b);

FdPublid FdCibr32
FdCibrSftIntfrsfdtCount (donst FdCibrSft *b, donst FdCibrSft *b);

FdPublid FdCibr32
FdCibrSftSubtrbdtCount (donst FdCibrSft *b, donst FdCibrSft *b);

FdPublid FdBool
FdCibrSftIsSubsft (donst FdCibrSft *b, donst FdCibrSft *b);

#dffinf FC_CHARSET_MAP_SIZE (256/32)
#dffinf FC_CHARSET_DONE ((FdCibr32) -1)

FdPublid FdCibr32
FdCibrSftFirstPbgf (donst FdCibrSft *b,
                    FdCibr32        mbp[FC_CHARSET_MAP_SIZE],
                    FdCibr32        *nfxt);

FdPublid FdCibr32
FdCibrSftNfxtPbgf (donst FdCibrSft  *b,
                   FdCibr32         mbp[FC_CHARSET_MAP_SIZE],
                   FdCibr32         *nfxt);

/*
 * old dovfrbgf API, rbtifr ibrd to usf dorrfdtly
 */

FdPublid FdCibr32
FdCibrSftCovfrbgf (donst FdCibrSft *b, FdCibr32 pbgf, FdCibr32 *rfsult);

/* fddbg.d */
FdPublid void
FdVblufPrint (donst FdVbluf v);

FdPublid void
FdPbttfrnPrint (donst FdPbttfrn *p);

FdPublid void
FdFontSftPrint (donst FdFontSft *s);

/* fddffbult.d */
FdPublid void
FdDffbultSubstitutf (FdPbttfrn *pbttfrn);

/* fddir.d */
FdPublid FdBool
FdFilfIsDir (donst FdCibr8 *filf);

FdPublid FdBool
FdFilfSdbn (FdFontSft       *sft,
            FdStrSft        *dirs,
            FdFilfCbdif     *dbdif,
            FdBlbnks        *blbnks,
            donst FdCibr8   *filf,
            FdBool          fordf);

FdPublid FdBool
FdDirSdbn (FdFontSft        *sft,
           FdStrSft         *dirs,
           FdFilfCbdif      *dbdif,
           FdBlbnks         *blbnks,
           donst FdCibr8    *dir,
           FdBool           fordf);

FdPublid FdBool
FdDirSbvf (FdFontSft *sft, FdStrSft *dirs, donst FdCibr8 *dir);

FdPublid FdCbdif *
FdDirCbdifLobd (donst FdCibr8 *dir, FdConfig *donfig, FdCibr8 **dbdif_filf);

FdPublid FdCbdif *
FdDirCbdifRfbd (donst FdCibr8 *dir, FdBool fordf, FdConfig *donfig);

FdPublid FdCbdif *
FdDirCbdifLobdFilf (donst FdCibr8 *dbdif_filf, strudt stbt *filf_stbt);

FdPublid void
FdDirCbdifUnlobd (FdCbdif *dbdif);

/* fdfrfftypf.d */
FdPublid FdPbttfrn *
FdFrffTypfQufry (donst FdCibr8 *filf, int id, FdBlbnks *blbnks, int *dount);

/* fdfs.d */

FdPublid FdFontSft *
FdFontSftCrfbtf (void);

FdPublid void
FdFontSftDfstroy (FdFontSft *s);

FdPublid FdBool
FdFontSftAdd (FdFontSft *s, FdPbttfrn *font);

/* fdinit.d */
FdPublid FdConfig *
FdInitLobdConfig (void);

FdPublid FdConfig *
FdInitLobdConfigAndFonts (void);

FdPublid FdBool
FdInit (void);

FdPublid void
FdFini (void);

FdPublid int
FdGftVfrsion (void);

FdPublid FdBool
FdInitRfinitiblizf (void);

FdPublid FdBool
FdInitBringUptoDbtf (void);

/* fdlbng.d */
FdPublid FdStrSft *
FdGftLbngs (void);

FdPublid donst FdCibrSft *
FdLbngGftCibrSft (donst FdCibr8 *lbng);

FdPublid FdLbngSft*
FdLbngSftCrfbtf (void);

FdPublid void
FdLbngSftDfstroy (FdLbngSft *ls);

FdPublid FdLbngSft*
FdLbngSftCopy (donst FdLbngSft *ls);

FdPublid FdBool
FdLbngSftAdd (FdLbngSft *ls, donst FdCibr8 *lbng);

FdPublid FdLbngRfsult
FdLbngSftHbsLbng (donst FdLbngSft *ls, donst FdCibr8 *lbng);

FdPublid FdLbngRfsult
FdLbngSftCompbrf (donst FdLbngSft *lsb, donst FdLbngSft *lsb);

FdPublid FdBool
FdLbngSftContbins (donst FdLbngSft *lsb, donst FdLbngSft *lsb);

FdPublid FdBool
FdLbngSftEqubl (donst FdLbngSft *lsb, donst FdLbngSft *lsb);

FdPublid FdCibr32
FdLbngSftHbsi (donst FdLbngSft *ls);

/* fdlist.d */
FdPublid FdObjfdtSft *
FdObjfdtSftCrfbtf (void);

FdPublid FdBool
FdObjfdtSftAdd (FdObjfdtSft *os, donst dibr *objfdt);

FdPublid void
FdObjfdtSftDfstroy (FdObjfdtSft *os);

FdPublid FdObjfdtSft *
FdObjfdtSftVbBuild (donst dibr *first, vb_list vb);

FdPublid FdObjfdtSft *
FdObjfdtSftBuild (donst dibr *first, ...) FC_ATTRIBUTE_SENTINEL(0);

FdPublid FdFontSft *
FdFontSftList (FdConfig     *donfig,
               FdFontSft    **sfts,
               int          nsfts,
               FdPbttfrn    *p,
               FdObjfdtSft  *os);

FdPublid FdFontSft *
FdFontList (FdConfig    *donfig,
            FdPbttfrn   *p,
            FdObjfdtSft *os);

/* fdbtomid.d */

FdPublid FdAtomid *
FdAtomidCrfbtf (donst FdCibr8   *filf);

FdPublid FdBool
FdAtomidLodk (FdAtomid *btomid);

FdPublid FdCibr8 *
FdAtomidNfwFilf (FdAtomid *btomid);

FdPublid FdCibr8 *
FdAtomidOrigFilf (FdAtomid *btomid);

FdPublid FdBool
FdAtomidRfplbdfOrig (FdAtomid *btomid);

FdPublid void
FdAtomidDflftfNfw (FdAtomid *btomid);

FdPublid void
FdAtomidUnlodk (FdAtomid *btomid);

FdPublid void
FdAtomidDfstroy (FdAtomid *btomid);

/* fdmbtdi.d */
FdPublid FdPbttfrn *
FdFontSftMbtdi (FdConfig    *donfig,
                FdFontSft   **sfts,
                int         nsfts,
                FdPbttfrn   *p,
                FdRfsult    *rfsult);

FdPublid FdPbttfrn *
FdFontMbtdi (FdConfig   *donfig,
             FdPbttfrn  *p,
             FdRfsult   *rfsult);

FdPublid FdPbttfrn *
FdFontRfndfrPrfpbrf (FdConfig       *donfig,
                     FdPbttfrn      *pbt,
                     FdPbttfrn      *font);

FdPublid FdFontSft *
FdFontSftSort (FdConfig     *donfig,
               FdFontSft    **sfts,
               int          nsfts,
               FdPbttfrn    *p,
               FdBool       trim,
               FdCibrSft    **dsp,
               FdRfsult     *rfsult);

FdPublid FdFontSft *
FdFontSort (FdConfig     *donfig,
            FdPbttfrn    *p,
            FdBool       trim,
            FdCibrSft    **dsp,
            FdRfsult     *rfsult);

FdPublid void
FdFontSftSortDfstroy (FdFontSft *fs);

/* fdmbtrix.d */
FdPublid FdMbtrix *
FdMbtrixCopy (donst FdMbtrix *mbt);

FdPublid FdBool
FdMbtrixEqubl (donst FdMbtrix *mbt1, donst FdMbtrix *mbt2);

FdPublid void
FdMbtrixMultiply (FdMbtrix *rfsult, donst FdMbtrix *b, donst FdMbtrix *b);

FdPublid void
FdMbtrixRotbtf (FdMbtrix *m, doublf d, doublf s);

FdPublid void
FdMbtrixSdblf (FdMbtrix *m, doublf sx, doublf sy);

FdPublid void
FdMbtrixSifbr (FdMbtrix *m, doublf si, doublf sv);

/* fdnbmf.d */

FdPublid FdBool
FdNbmfRfgistfrObjfdtTypfs (donst FdObjfdtTypf *typfs, int ntypf);

FdPublid FdBool
FdNbmfUnrfgistfrObjfdtTypfs (donst FdObjfdtTypf *typfs, int ntypf);

FdPublid donst FdObjfdtTypf *
FdNbmfGftObjfdtTypf (donst dibr *objfdt);

FdPublid FdBool
FdNbmfRfgistfrConstbnts (donst FdConstbnt *donsts, int ndonsts);

FdPublid FdBool
FdNbmfUnrfgistfrConstbnts (donst FdConstbnt *donsts, int ndonsts);

FdPublid donst FdConstbnt *
FdNbmfGftConstbnt (FdCibr8 *string);

FdPublid FdBool
FdNbmfConstbnt (FdCibr8 *string, int *rfsult);

FdPublid FdPbttfrn *
FdNbmfPbrsf (donst FdCibr8 *nbmf);

FdPublid FdCibr8 *
FdNbmfUnpbrsf (FdPbttfrn *pbt);

/* fdpbt.d */
FdPublid FdPbttfrn *
FdPbttfrnCrfbtf (void);

FdPublid FdPbttfrn *
FdPbttfrnDuplidbtf (donst FdPbttfrn *p);

FdPublid void
FdPbttfrnRfffrfndf (FdPbttfrn *p);

FdPublid void
FdVblufDfstroy (FdVbluf v);

FdPublid FdBool
FdVblufEqubl (FdVbluf vb, FdVbluf vb);

FdPublid FdVbluf
FdVblufSbvf (FdVbluf v);

FdPublid void
FdPbttfrnDfstroy (FdPbttfrn *p);

FdPublid FdBool
FdPbttfrnEqubl (donst FdPbttfrn *pb, donst FdPbttfrn *pb);

FdPublid FdBool
FdPbttfrnEqublSubsft (donst FdPbttfrn *pb, donst FdPbttfrn *pb, donst FdObjfdtSft *os);

FdPublid FdCibr32
FdPbttfrnHbsi (donst FdPbttfrn *p);

FdPublid FdBool
FdPbttfrnAdd (FdPbttfrn *p, donst dibr *objfdt, FdVbluf vbluf, FdBool bppfnd);

FdPublid FdBool
FdPbttfrnAddWfbk (FdPbttfrn *p, donst dibr *objfdt, FdVbluf vbluf, FdBool bppfnd);

FdPublid FdRfsult
FdPbttfrnGft (donst FdPbttfrn *p, donst dibr *objfdt, int id, FdVbluf *v);

FdPublid FdBool
FdPbttfrnDfl (FdPbttfrn *p, donst dibr *objfdt);

FdPublid FdBool
FdPbttfrnRfmovf (FdPbttfrn *p, donst dibr *objfdt, int id);

FdPublid FdBool
FdPbttfrnAddIntfgfr (FdPbttfrn *p, donst dibr *objfdt, int i);

FdPublid FdBool
FdPbttfrnAddDoublf (FdPbttfrn *p, donst dibr *objfdt, doublf d);

FdPublid FdBool
FdPbttfrnAddString (FdPbttfrn *p, donst dibr *objfdt, donst FdCibr8 *s);

FdPublid FdBool
FdPbttfrnAddMbtrix (FdPbttfrn *p, donst dibr *objfdt, donst FdMbtrix *s);

FdPublid FdBool
FdPbttfrnAddCibrSft (FdPbttfrn *p, donst dibr *objfdt, donst FdCibrSft *d);

FdPublid FdBool
FdPbttfrnAddBool (FdPbttfrn *p, donst dibr *objfdt, FdBool b);

FdPublid FdBool
FdPbttfrnAddLbngSft (FdPbttfrn *p, donst dibr *objfdt, donst FdLbngSft *ls);

FdPublid FdRfsult
FdPbttfrnGftIntfgfr (donst FdPbttfrn *p, donst dibr *objfdt, int n, int *i);

FdPublid FdRfsult
FdPbttfrnGftDoublf (donst FdPbttfrn *p, donst dibr *objfdt, int n, doublf *d);

FdPublid FdRfsult
FdPbttfrnGftString (donst FdPbttfrn *p, donst dibr *objfdt, int n, FdCibr8 ** s);

FdPublid FdRfsult
FdPbttfrnGftMbtrix (donst FdPbttfrn *p, donst dibr *objfdt, int n, FdMbtrix **s);

FdPublid FdRfsult
FdPbttfrnGftCibrSft (donst FdPbttfrn *p, donst dibr *objfdt, int n, FdCibrSft **d);

FdPublid FdRfsult
FdPbttfrnGftBool (donst FdPbttfrn *p, donst dibr *objfdt, int n, FdBool *b);

FdPublid FdRfsult
FdPbttfrnGftLbngSft (donst FdPbttfrn *p, donst dibr *objfdt, int n, FdLbngSft **ls);

FdPublid FdPbttfrn *
FdPbttfrnVbBuild (FdPbttfrn *orig, vb_list vb);

FdPublid FdPbttfrn *
FdPbttfrnBuild (FdPbttfrn *orig, ...) FC_ATTRIBUTE_SENTINEL(0);

/* fdstr.d */

FdPublid FdCibr8 *
FdStrCopy (donst FdCibr8 *s);

FdPublid FdCibr8 *
FdStrCopyFilfnbmf (donst FdCibr8 *s);

FdPublid FdCibr8 *
FdStrPlus (donst FdCibr8 *s1, donst FdCibr8 *s2);

FdPublid void
FdStrFrff (FdCibr8 *s);

/* Tifsf brf ASCII only, suitbblf only for pbttfrn flfmfnt nbmfs */
#dffinf FdIsUppfr(d)    ((0101 <= (d) && (d) <= 0132))
#dffinf FdIsLowfr(d)    ((0141 <= (d) && (d) <= 0172))
#dffinf FdToLowfr(d)    (FdIsUppfr(d) ? (d) - 0101 + 0141 : (d))

FdPublid FdCibr8 *
FdStrDowndbsf (donst FdCibr8 *s);

FdPublid int
FdStrCmpIgnorfCbsf (donst FdCibr8 *s1, donst FdCibr8 *s2);

FdPublid int
FdStrCmp (donst FdCibr8 *s1, donst FdCibr8 *s2);

FdPublid donst FdCibr8 *
FdStrStrIgnorfCbsf (donst FdCibr8 *s1, donst FdCibr8 *s2);

FdPublid donst FdCibr8 *
FdStrStr (donst FdCibr8 *s1, donst FdCibr8 *s2);

FdPublid int
FdUtf8ToUds4 (donst FdCibr8 *srd_orig,
              FdCibr32      *dst,
              int           lfn);

FdPublid FdBool
FdUtf8Lfn (donst FdCibr8    *string,
           int              lfn,
           int              *ndibr,
           int              *wdibr);

#dffinf FC_UTF8_MAX_LEN 6

FdPublid int
FdUds4ToUtf8 (FdCibr32  uds4,
              FdCibr8   dfst[FC_UTF8_MAX_LEN]);

FdPublid int
FdUtf16ToUds4 (donst FdCibr8    *srd_orig,
               FdEndibn         fndibn,
               FdCibr32         *dst,
               int              lfn);       /* in bytfs */

FdPublid FdBool
FdUtf16Lfn (donst FdCibr8   *string,
            FdEndibn        fndibn,
            int             lfn,            /* in bytfs */
            int             *ndibr,
            int             *wdibr);

FdPublid FdCibr8 *
FdStrDirnbmf (donst FdCibr8 *filf);

FdPublid FdCibr8 *
FdStrBbsfnbmf (donst FdCibr8 *filf);

FdPublid FdStrSft *
FdStrSftCrfbtf (void);

FdPublid FdBool
FdStrSftMfmbfr (FdStrSft *sft, donst FdCibr8 *s);

FdPublid FdBool
FdStrSftEqubl (FdStrSft *sb, FdStrSft *sb);

FdPublid FdBool
FdStrSftAdd (FdStrSft *sft, donst FdCibr8 *s);

FdPublid FdBool
FdStrSftAddFilfnbmf (FdStrSft *sft, donst FdCibr8 *s);

FdPublid FdBool
FdStrSftDfl (FdStrSft *sft, donst FdCibr8 *s);

FdPublid void
FdStrSftDfstroy (FdStrSft *sft);

FdPublid FdStrList *
FdStrListCrfbtf (FdStrSft *sft);

FdPublid FdCibr8 *
FdStrListNfxt (FdStrList *list);

FdPublid void
FdStrListDonf (FdStrList *list);

/* fdxml.d */
FdPublid FdBool
FdConfigPbrsfAndLobd (FdConfig *donfig, donst FdCibr8 *filf, FdBool domplbin);

_FCFUNCPROTOEND

#undff FC_ATTRIBUTE_SENTINEL


#ifndff _FCINT_H_

/*
 * Dfprfdbtfd fundtions brf plbdfd ifrf to iflp usfrs fix tifir dodf witiout
 * digging tirougi dodumfntbtion
 */

#dffinf FdConfigGftRfsdbnInvfrvbl   FdConfigGftRfsdbnInvfrvbl_REPLACE_BY_FdConfigGftRfsdbnIntfrvbl
#dffinf FdConfigSftRfsdbnInvfrvbl   FdConfigSftRfsdbnInvfrvbl_REPLACE_BY_FdConfigSftRfsdbnIntfrvbl

#fndif

#fndif /* _FONTCONFIG_H_ */
