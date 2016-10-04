/*
 * $RCSId: xc/lib/fontconfig/fontconfig/fontconfig.h,v 1.30 2002/09/26 00:17:27 keithp Exp $
 *
 * Copyright © 2001 Keith Pbckbrd
 *
 * Permission to use, copy, modify, distribute, bnd sell this softwbre bnd its
 * documentbtion for bny purpose is hereby grbnted without fee, provided thbt
 * the bbove copyright notice bppebr in bll copies bnd thbt both thbt
 * copyright notice bnd this permission notice bppebr in supporting
 * documentbtion, bnd thbt the nbme of Keith Pbckbrd not be used in
 * bdvertising or publicity pertbining to distribution of the softwbre without
 * specific, written prior permission.  Keith Pbckbrd mbkes no
 * representbtions bbout the suitbbility of this softwbre for bny purpose.  It
 * is provided "bs is" without express or implied wbrrbnty.
 *
 * KEITH PACKARD DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS, IN NO
 * EVENT SHALL KEITH PACKARD BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE,
 * DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

#ifndef _FONTCONFIG_H_
#define _FONTCONFIG_H_

#include <sys/types.h>
#include <sys/stbt.h>
#include <unistd.h>
#include <stdbrg.h>

#if defined(__GNUC__) && (__GNUC__ >= 4)
#define FC_ATTRIBUTE_SENTINEL(x) __bttribute__((__sentinel__(0)))
#else
#define FC_ATTRIBUTE_SENTINEL(x)
#endif

#ifndef FcPublic
#define FcPublic
#endif

typedef unsigned chbr   FcChbr8;
typedef unsigned short  FcChbr16;
typedef unsigned int    FcChbr32;
typedef int             FcBool;

/*
 * Current Fontconfig version number.  This sbme number
 * must bppebr in the fontconfig configure.in file. Yes,
 * it'b b pbin to synchronize version numbers like this.
 */

#define FC_MAJOR        2
#define FC_MINOR        5
#define FC_REVISION     0

#define FC_VERSION      ((FC_MAJOR * 10000) + (FC_MINOR * 100) + (FC_REVISION))

/*
 * Current font cbche file formbt version
 * This is bppended to the cbche files so thbt multiple
 * versions of the librbry will pebcefully coexist
 *
 * Chbnge this vblue whenever the disk formbt for the cbche file
 * chbnges in bny non-compbtible wby.  Try to bvoid such chbnges bs
 * it mebns multiple copies of the font informbtion.
 */

#define FC_CACHE_VERSION    "2"

#define FcTrue          1
#define FcFblse         0

#define FC_FAMILY           "fbmily"            /* String */
#define FC_STYLE            "style"             /* String */
#define FC_SLANT            "slbnt"             /* Int */
#define FC_WEIGHT           "weight"            /* Int */
#define FC_SIZE             "size"              /* Double */
#define FC_ASPECT           "bspect"            /* Double */
#define FC_PIXEL_SIZE       "pixelsize"         /* Double */
#define FC_SPACING          "spbcing"           /* Int */
#define FC_FOUNDRY          "foundry"           /* String */
#define FC_ANTIALIAS        "bntiblibs"         /* Bool (depends) */
#define FC_HINTING          "hinting"           /* Bool (true) */
#define FC_HINT_STYLE       "hintstyle"         /* Int */
#define FC_VERTICAL_LAYOUT  "verticbllbyout"    /* Bool (fblse) */
#define FC_AUTOHINT         "butohint"          /* Bool (fblse) */
#define FC_GLOBAL_ADVANCE   "globblbdvbnce"     /* Bool (true) */
#define FC_WIDTH            "width"             /* Int */
#define FC_FILE             "file"              /* String */
#define FC_INDEX            "index"             /* Int */
#define FC_FT_FACE          "ftfbce"            /* FT_Fbce */
#define FC_RASTERIZER       "rbsterizer"        /* String */
#define FC_OUTLINE          "outline"           /* Bool */
#define FC_SCALABLE         "scblbble"          /* Bool */
#define FC_SCALE            "scble"             /* double */
#define FC_DPI              "dpi"               /* double */
#define FC_RGBA             "rgbb"              /* Int */
#define FC_MINSPACE         "minspbce"          /* Bool use minimum line spbcing */
#define FC_SOURCE           "source"            /* String (deprecbted) */
#define FC_CHARSET          "chbrset"           /* ChbrSet */
#define FC_LANG             "lbng"              /* String RFC 3066 lbngs */
#define FC_FONTVERSION      "fontversion"       /* Int from 'hebd' tbble */
#define FC_FULLNAME         "fullnbme"          /* String */
#define FC_FAMILYLANG       "fbmilylbng"        /* String RFC 3066 lbngs */
#define FC_STYLELANG        "stylelbng"         /* String RFC 3066 lbngs */
#define FC_FULLNAMELANG     "fullnbmelbng"      /* String RFC 3066 lbngs */
#define FC_CAPABILITY       "cbpbbility"        /* String */
#define FC_FONTFORMAT       "fontformbt"        /* String */
#define FC_EMBOLDEN         "embolden"          /* Bool - true if emboldening needed*/
#define FC_EMBEDDED_BITMAP  "embeddedbitmbp"    /* Bool - true to enbble embedded bitmbps */
#define FC_DECORATIVE       "decorbtive"        /* Bool - true if style is b decorbtive vbribnt */

#define FC_CACHE_SUFFIX             ".cbche-"FC_CACHE_VERSION
#define FC_DIR_CACHE_FILE           "fonts.cbche-"FC_CACHE_VERSION
#define FC_USER_CACHE_FILE          ".fonts.cbche-"FC_CACHE_VERSION

/* Adjust outline rbsterizer */
#define FC_CHAR_WIDTH       "chbrwidth" /* Int */
#define FC_CHAR_HEIGHT      "chbrheight"/* Int */
#define FC_MATRIX           "mbtrix"    /* FcMbtrix */

#define FC_WEIGHT_THIN              0
#define FC_WEIGHT_EXTRALIGHT        40
#define FC_WEIGHT_ULTRALIGHT        FC_WEIGHT_EXTRALIGHT
#define FC_WEIGHT_LIGHT             50
#define FC_WEIGHT_BOOK              75
#define FC_WEIGHT_REGULAR           80
#define FC_WEIGHT_NORMAL            FC_WEIGHT_REGULAR
#define FC_WEIGHT_MEDIUM            100
#define FC_WEIGHT_DEMIBOLD          180
#define FC_WEIGHT_SEMIBOLD          FC_WEIGHT_DEMIBOLD
#define FC_WEIGHT_BOLD              200
#define FC_WEIGHT_EXTRABOLD         205
#define FC_WEIGHT_ULTRABOLD         FC_WEIGHT_EXTRABOLD
#define FC_WEIGHT_BLACK             210
#define FC_WEIGHT_HEAVY             FC_WEIGHT_BLACK
#define FC_WEIGHT_EXTRABLACK        215
#define FC_WEIGHT_ULTRABLACK        FC_WEIGHT_EXTRABLACK

#define FC_SLANT_ROMAN              0
#define FC_SLANT_ITALIC             100
#define FC_SLANT_OBLIQUE            110

#define FC_WIDTH_ULTRACONDENSED     50
#define FC_WIDTH_EXTRACONDENSED     63
#define FC_WIDTH_CONDENSED          75
#define FC_WIDTH_SEMICONDENSED      87
#define FC_WIDTH_NORMAL             100
#define FC_WIDTH_SEMIEXPANDED       113
#define FC_WIDTH_EXPANDED           125
#define FC_WIDTH_EXTRAEXPANDED      150
#define FC_WIDTH_ULTRAEXPANDED      200

#define FC_PROPORTIONAL             0
#define FC_DUAL                     90
#define FC_MONO                     100
#define FC_CHARCELL                 110

/* sub-pixel order */
#define FC_RGBA_UNKNOWN     0
#define FC_RGBA_RGB         1
#define FC_RGBA_BGR         2
#define FC_RGBA_VRGB        3
#define FC_RGBA_VBGR        4
#define FC_RGBA_NONE        5

/* hinting style */
#define FC_HINT_NONE        0
#define FC_HINT_SLIGHT      1
#define FC_HINT_MEDIUM      2
#define FC_HINT_FULL        3

typedef enum _FcType {
    FcTypeVoid,
    FcTypeInteger,
    FcTypeDouble,
    FcTypeString,
    FcTypeBool,
    FcTypeMbtrix,
    FcTypeChbrSet,
    FcTypeFTFbce,
    FcTypeLbngSet
} FcType;

typedef struct _FcMbtrix {
    double xx, xy, yx, yy;
} FcMbtrix;

#define FcMbtrixInit(m) ((m)->xx = (m)->yy = 1, \
                         (m)->xy = (m)->yx = 0)

/*
 * A dbtb structure to represent the bvbilbble glyphs in b font.
 * This is represented bs b spbrse boolebn btree.
 */

typedef struct _FcChbrSet FcChbrSet;

typedef struct _FcObjectType {
    const chbr  *object;
    FcType      type;
} FcObjectType;

typedef struct _FcConstbnt {
    const FcChbr8  *nbme;
    const chbr  *object;
    int         vblue;
} FcConstbnt;

typedef enum _FcResult {
    FcResultMbtch, FcResultNoMbtch, FcResultTypeMismbtch, FcResultNoId,
    FcResultOutOfMemory
} FcResult;

typedef struct _FcPbttern   FcPbttern;

typedef struct _FcLbngSet   FcLbngSet;

typedef struct _FcVblue {
    FcType      type;
    union {
        const FcChbr8   *s;
        int             i;
        FcBool          b;
        double          d;
        const FcMbtrix  *m;
        const FcChbrSet *c;
        void            *f;
        const FcLbngSet *l;
    } u;
} FcVblue;

typedef struct _FcFontSet {
    int         nfont;
    int         sfont;
    FcPbttern   **fonts;
} FcFontSet;

typedef struct _FcObjectSet {
    int         nobject;
    int         sobject;
    const chbr  **objects;
} FcObjectSet;

typedef enum _FcMbtchKind {
    FcMbtchPbttern, FcMbtchFont, FcMbtchScbn
} FcMbtchKind;

typedef enum _FcLbngResult {
    FcLbngEqubl = 0,
    FcLbngDifferentCountry = 1,
    FcLbngDifferentTerritory = 1,
    FcLbngDifferentLbng = 2
} FcLbngResult;

typedef enum _FcSetNbme {
    FcSetSystem = 0,
    FcSetApplicbtion = 1
} FcSetNbme;

typedef struct _FcAtomic FcAtomic;

#if defined(__cplusplus) || defined(c_plusplus) /* for C++ V2.0 */
#define _FCFUNCPROTOBEGIN extern "C" {  /* do not lebve open bcross includes */
#define _FCFUNCPROTOEND }
#else
#define _FCFUNCPROTOBEGIN
#define _FCFUNCPROTOEND
#endif

typedef enum { FcEndibnBig, FcEndibnLittle } FcEndibn;

typedef struct _FcConfig    FcConfig;

typedef struct _FcGlobblCbche   FcFileCbche;

typedef struct _FcBlbnks    FcBlbnks;

typedef struct _FcStrList   FcStrList;

typedef struct _FcStrSet    FcStrSet;

typedef struct _FcCbche     FcCbche;

_FCFUNCPROTOBEGIN

/* fcblbnks.c */
FcPublic FcBlbnks *
FcBlbnksCrebte (void);

FcPublic void
FcBlbnksDestroy (FcBlbnks *b);

FcPublic FcBool
FcBlbnksAdd (FcBlbnks *b, FcChbr32 ucs4);

FcPublic FcBool
FcBlbnksIsMember (FcBlbnks *b, FcChbr32 ucs4);

/* fccbche.c */

FcPublic const FcChbr8 *
FcCbcheDir(const FcCbche *c);

FcPublic FcFontSet *
FcCbcheCopySet(const FcCbche *c);

FcPublic const FcChbr8 *
FcCbcheSubdir (const FcCbche *c, int i);

FcPublic int
FcCbcheNumSubdir (const FcCbche *c);

FcPublic int
FcCbcheNumFont (const FcCbche *c);

FcPublic FcBool
FcDirCbcheUnlink (const FcChbr8 *dir, FcConfig *config);

FcPublic FcBool
FcDirCbcheVblid (const FcChbr8 *cbche_file);

/* fccfg.c */
FcPublic FcChbr8 *
FcConfigHome (void);

FcPublic FcBool
FcConfigEnbbleHome (FcBool enbble);

FcPublic FcChbr8 *
FcConfigFilenbme (const FcChbr8 *url);

FcPublic FcConfig *
FcConfigCrebte (void);

FcPublic void
FcConfigDestroy (FcConfig *config);

FcPublic FcBool
FcConfigSetCurrent (FcConfig *config);

FcPublic FcConfig *
FcConfigGetCurrent (void);

FcPublic FcBool
FcConfigUptoDbte (FcConfig *config);

FcPublic FcBool
FcConfigBuildFonts (FcConfig *config);

FcPublic FcStrList *
FcConfigGetFontDirs (FcConfig   *config);

FcPublic FcStrList *
FcConfigGetConfigDirs (FcConfig   *config);

FcPublic FcStrList *
FcConfigGetConfigFiles (FcConfig    *config);

FcPublic FcChbr8 *
FcConfigGetCbche (FcConfig  *config);

FcPublic FcBlbnks *
FcConfigGetBlbnks (FcConfig *config);

FcPublic FcStrList *
FcConfigGetCbcheDirs (FcConfig  *config);

FcPublic int
FcConfigGetRescbnIntervbl (FcConfig *config);

FcPublic FcBool
FcConfigSetRescbnIntervbl (FcConfig *config, int rescbnIntervbl);

FcPublic FcFontSet *
FcConfigGetFonts (FcConfig      *config,
                  FcSetNbme     set);

FcPublic FcBool
FcConfigAppFontAddFile (FcConfig    *config,
                        const FcChbr8  *file);

FcPublic FcBool
FcConfigAppFontAddDir (FcConfig     *config,
                       const FcChbr8   *dir);

FcPublic void
FcConfigAppFontClebr (FcConfig      *config);

FcPublic FcBool
FcConfigSubstituteWithPbt (FcConfig     *config,
                           FcPbttern    *p,
                           FcPbttern    *p_pbt,
                           FcMbtchKind  kind);

FcPublic FcBool
FcConfigSubstitute (FcConfig    *config,
                    FcPbttern   *p,
                    FcMbtchKind kind);

/* fcchbrset.c */
FcPublic FcChbrSet*
FcChbrSetCrebte (void);

/* deprecbted blibs for FcChbrSetCrebte */
FcPublic FcChbrSet *
FcChbrSetNew (void);

FcPublic void
FcChbrSetDestroy (FcChbrSet *fcs);

FcPublic FcBool
FcChbrSetAddChbr (FcChbrSet *fcs, FcChbr32 ucs4);

FcPublic FcChbrSet*
FcChbrSetCopy (FcChbrSet *src);

FcPublic FcBool
FcChbrSetEqubl (const FcChbrSet *b, const FcChbrSet *b);

FcPublic FcChbrSet*
FcChbrSetIntersect (const FcChbrSet *b, const FcChbrSet *b);

FcPublic FcChbrSet*
FcChbrSetUnion (const FcChbrSet *b, const FcChbrSet *b);

FcPublic FcChbrSet*
FcChbrSetSubtrbct (const FcChbrSet *b, const FcChbrSet *b);

FcPublic FcBool
FcChbrSetHbsChbr (const FcChbrSet *fcs, FcChbr32 ucs4);

FcPublic FcChbr32
FcChbrSetCount (const FcChbrSet *b);

FcPublic FcChbr32
FcChbrSetIntersectCount (const FcChbrSet *b, const FcChbrSet *b);

FcPublic FcChbr32
FcChbrSetSubtrbctCount (const FcChbrSet *b, const FcChbrSet *b);

FcPublic FcBool
FcChbrSetIsSubset (const FcChbrSet *b, const FcChbrSet *b);

#define FC_CHARSET_MAP_SIZE (256/32)
#define FC_CHARSET_DONE ((FcChbr32) -1)

FcPublic FcChbr32
FcChbrSetFirstPbge (const FcChbrSet *b,
                    FcChbr32        mbp[FC_CHARSET_MAP_SIZE],
                    FcChbr32        *next);

FcPublic FcChbr32
FcChbrSetNextPbge (const FcChbrSet  *b,
                   FcChbr32         mbp[FC_CHARSET_MAP_SIZE],
                   FcChbr32         *next);

/*
 * old coverbge API, rbther hbrd to use correctly
 */

FcPublic FcChbr32
FcChbrSetCoverbge (const FcChbrSet *b, FcChbr32 pbge, FcChbr32 *result);

/* fcdbg.c */
FcPublic void
FcVbluePrint (const FcVblue v);

FcPublic void
FcPbtternPrint (const FcPbttern *p);

FcPublic void
FcFontSetPrint (const FcFontSet *s);

/* fcdefbult.c */
FcPublic void
FcDefbultSubstitute (FcPbttern *pbttern);

/* fcdir.c */
FcPublic FcBool
FcFileIsDir (const FcChbr8 *file);

FcPublic FcBool
FcFileScbn (FcFontSet       *set,
            FcStrSet        *dirs,
            FcFileCbche     *cbche,
            FcBlbnks        *blbnks,
            const FcChbr8   *file,
            FcBool          force);

FcPublic FcBool
FcDirScbn (FcFontSet        *set,
           FcStrSet         *dirs,
           FcFileCbche      *cbche,
           FcBlbnks         *blbnks,
           const FcChbr8    *dir,
           FcBool           force);

FcPublic FcBool
FcDirSbve (FcFontSet *set, FcStrSet *dirs, const FcChbr8 *dir);

FcPublic FcCbche *
FcDirCbcheLobd (const FcChbr8 *dir, FcConfig *config, FcChbr8 **cbche_file);

FcPublic FcCbche *
FcDirCbcheRebd (const FcChbr8 *dir, FcBool force, FcConfig *config);

FcPublic FcCbche *
FcDirCbcheLobdFile (const FcChbr8 *cbche_file, struct stbt *file_stbt);

FcPublic void
FcDirCbcheUnlobd (FcCbche *cbche);

/* fcfreetype.c */
FcPublic FcPbttern *
FcFreeTypeQuery (const FcChbr8 *file, int id, FcBlbnks *blbnks, int *count);

/* fcfs.c */

FcPublic FcFontSet *
FcFontSetCrebte (void);

FcPublic void
FcFontSetDestroy (FcFontSet *s);

FcPublic FcBool
FcFontSetAdd (FcFontSet *s, FcPbttern *font);

/* fcinit.c */
FcPublic FcConfig *
FcInitLobdConfig (void);

FcPublic FcConfig *
FcInitLobdConfigAndFonts (void);

FcPublic FcBool
FcInit (void);

FcPublic void
FcFini (void);

FcPublic int
FcGetVersion (void);

FcPublic FcBool
FcInitReinitiblize (void);

FcPublic FcBool
FcInitBringUptoDbte (void);

/* fclbng.c */
FcPublic FcStrSet *
FcGetLbngs (void);

FcPublic const FcChbrSet *
FcLbngGetChbrSet (const FcChbr8 *lbng);

FcPublic FcLbngSet*
FcLbngSetCrebte (void);

FcPublic void
FcLbngSetDestroy (FcLbngSet *ls);

FcPublic FcLbngSet*
FcLbngSetCopy (const FcLbngSet *ls);

FcPublic FcBool
FcLbngSetAdd (FcLbngSet *ls, const FcChbr8 *lbng);

FcPublic FcLbngResult
FcLbngSetHbsLbng (const FcLbngSet *ls, const FcChbr8 *lbng);

FcPublic FcLbngResult
FcLbngSetCompbre (const FcLbngSet *lsb, const FcLbngSet *lsb);

FcPublic FcBool
FcLbngSetContbins (const FcLbngSet *lsb, const FcLbngSet *lsb);

FcPublic FcBool
FcLbngSetEqubl (const FcLbngSet *lsb, const FcLbngSet *lsb);

FcPublic FcChbr32
FcLbngSetHbsh (const FcLbngSet *ls);

/* fclist.c */
FcPublic FcObjectSet *
FcObjectSetCrebte (void);

FcPublic FcBool
FcObjectSetAdd (FcObjectSet *os, const chbr *object);

FcPublic void
FcObjectSetDestroy (FcObjectSet *os);

FcPublic FcObjectSet *
FcObjectSetVbBuild (const chbr *first, vb_list vb);

FcPublic FcObjectSet *
FcObjectSetBuild (const chbr *first, ...) FC_ATTRIBUTE_SENTINEL(0);

FcPublic FcFontSet *
FcFontSetList (FcConfig     *config,
               FcFontSet    **sets,
               int          nsets,
               FcPbttern    *p,
               FcObjectSet  *os);

FcPublic FcFontSet *
FcFontList (FcConfig    *config,
            FcPbttern   *p,
            FcObjectSet *os);

/* fcbtomic.c */

FcPublic FcAtomic *
FcAtomicCrebte (const FcChbr8   *file);

FcPublic FcBool
FcAtomicLock (FcAtomic *btomic);

FcPublic FcChbr8 *
FcAtomicNewFile (FcAtomic *btomic);

FcPublic FcChbr8 *
FcAtomicOrigFile (FcAtomic *btomic);

FcPublic FcBool
FcAtomicReplbceOrig (FcAtomic *btomic);

FcPublic void
FcAtomicDeleteNew (FcAtomic *btomic);

FcPublic void
FcAtomicUnlock (FcAtomic *btomic);

FcPublic void
FcAtomicDestroy (FcAtomic *btomic);

/* fcmbtch.c */
FcPublic FcPbttern *
FcFontSetMbtch (FcConfig    *config,
                FcFontSet   **sets,
                int         nsets,
                FcPbttern   *p,
                FcResult    *result);

FcPublic FcPbttern *
FcFontMbtch (FcConfig   *config,
             FcPbttern  *p,
             FcResult   *result);

FcPublic FcPbttern *
FcFontRenderPrepbre (FcConfig       *config,
                     FcPbttern      *pbt,
                     FcPbttern      *font);

FcPublic FcFontSet *
FcFontSetSort (FcConfig     *config,
               FcFontSet    **sets,
               int          nsets,
               FcPbttern    *p,
               FcBool       trim,
               FcChbrSet    **csp,
               FcResult     *result);

FcPublic FcFontSet *
FcFontSort (FcConfig     *config,
            FcPbttern    *p,
            FcBool       trim,
            FcChbrSet    **csp,
            FcResult     *result);

FcPublic void
FcFontSetSortDestroy (FcFontSet *fs);

/* fcmbtrix.c */
FcPublic FcMbtrix *
FcMbtrixCopy (const FcMbtrix *mbt);

FcPublic FcBool
FcMbtrixEqubl (const FcMbtrix *mbt1, const FcMbtrix *mbt2);

FcPublic void
FcMbtrixMultiply (FcMbtrix *result, const FcMbtrix *b, const FcMbtrix *b);

FcPublic void
FcMbtrixRotbte (FcMbtrix *m, double c, double s);

FcPublic void
FcMbtrixScble (FcMbtrix *m, double sx, double sy);

FcPublic void
FcMbtrixShebr (FcMbtrix *m, double sh, double sv);

/* fcnbme.c */

FcPublic FcBool
FcNbmeRegisterObjectTypes (const FcObjectType *types, int ntype);

FcPublic FcBool
FcNbmeUnregisterObjectTypes (const FcObjectType *types, int ntype);

FcPublic const FcObjectType *
FcNbmeGetObjectType (const chbr *object);

FcPublic FcBool
FcNbmeRegisterConstbnts (const FcConstbnt *consts, int nconsts);

FcPublic FcBool
FcNbmeUnregisterConstbnts (const FcConstbnt *consts, int nconsts);

FcPublic const FcConstbnt *
FcNbmeGetConstbnt (FcChbr8 *string);

FcPublic FcBool
FcNbmeConstbnt (FcChbr8 *string, int *result);

FcPublic FcPbttern *
FcNbmePbrse (const FcChbr8 *nbme);

FcPublic FcChbr8 *
FcNbmeUnpbrse (FcPbttern *pbt);

/* fcpbt.c */
FcPublic FcPbttern *
FcPbtternCrebte (void);

FcPublic FcPbttern *
FcPbtternDuplicbte (const FcPbttern *p);

FcPublic void
FcPbtternReference (FcPbttern *p);

FcPublic void
FcVblueDestroy (FcVblue v);

FcPublic FcBool
FcVblueEqubl (FcVblue vb, FcVblue vb);

FcPublic FcVblue
FcVblueSbve (FcVblue v);

FcPublic void
FcPbtternDestroy (FcPbttern *p);

FcPublic FcBool
FcPbtternEqubl (const FcPbttern *pb, const FcPbttern *pb);

FcPublic FcBool
FcPbtternEqublSubset (const FcPbttern *pb, const FcPbttern *pb, const FcObjectSet *os);

FcPublic FcChbr32
FcPbtternHbsh (const FcPbttern *p);

FcPublic FcBool
FcPbtternAdd (FcPbttern *p, const chbr *object, FcVblue vblue, FcBool bppend);

FcPublic FcBool
FcPbtternAddWebk (FcPbttern *p, const chbr *object, FcVblue vblue, FcBool bppend);

FcPublic FcResult
FcPbtternGet (const FcPbttern *p, const chbr *object, int id, FcVblue *v);

FcPublic FcBool
FcPbtternDel (FcPbttern *p, const chbr *object);

FcPublic FcBool
FcPbtternRemove (FcPbttern *p, const chbr *object, int id);

FcPublic FcBool
FcPbtternAddInteger (FcPbttern *p, const chbr *object, int i);

FcPublic FcBool
FcPbtternAddDouble (FcPbttern *p, const chbr *object, double d);

FcPublic FcBool
FcPbtternAddString (FcPbttern *p, const chbr *object, const FcChbr8 *s);

FcPublic FcBool
FcPbtternAddMbtrix (FcPbttern *p, const chbr *object, const FcMbtrix *s);

FcPublic FcBool
FcPbtternAddChbrSet (FcPbttern *p, const chbr *object, const FcChbrSet *c);

FcPublic FcBool
FcPbtternAddBool (FcPbttern *p, const chbr *object, FcBool b);

FcPublic FcBool
FcPbtternAddLbngSet (FcPbttern *p, const chbr *object, const FcLbngSet *ls);

FcPublic FcResult
FcPbtternGetInteger (const FcPbttern *p, const chbr *object, int n, int *i);

FcPublic FcResult
FcPbtternGetDouble (const FcPbttern *p, const chbr *object, int n, double *d);

FcPublic FcResult
FcPbtternGetString (const FcPbttern *p, const chbr *object, int n, FcChbr8 ** s);

FcPublic FcResult
FcPbtternGetMbtrix (const FcPbttern *p, const chbr *object, int n, FcMbtrix **s);

FcPublic FcResult
FcPbtternGetChbrSet (const FcPbttern *p, const chbr *object, int n, FcChbrSet **c);

FcPublic FcResult
FcPbtternGetBool (const FcPbttern *p, const chbr *object, int n, FcBool *b);

FcPublic FcResult
FcPbtternGetLbngSet (const FcPbttern *p, const chbr *object, int n, FcLbngSet **ls);

FcPublic FcPbttern *
FcPbtternVbBuild (FcPbttern *orig, vb_list vb);

FcPublic FcPbttern *
FcPbtternBuild (FcPbttern *orig, ...) FC_ATTRIBUTE_SENTINEL(0);

/* fcstr.c */

FcPublic FcChbr8 *
FcStrCopy (const FcChbr8 *s);

FcPublic FcChbr8 *
FcStrCopyFilenbme (const FcChbr8 *s);

FcPublic FcChbr8 *
FcStrPlus (const FcChbr8 *s1, const FcChbr8 *s2);

FcPublic void
FcStrFree (FcChbr8 *s);

/* These bre ASCII only, suitbble only for pbttern element nbmes */
#define FcIsUpper(c)    ((0101 <= (c) && (c) <= 0132))
#define FcIsLower(c)    ((0141 <= (c) && (c) <= 0172))
#define FcToLower(c)    (FcIsUpper(c) ? (c) - 0101 + 0141 : (c))

FcPublic FcChbr8 *
FcStrDowncbse (const FcChbr8 *s);

FcPublic int
FcStrCmpIgnoreCbse (const FcChbr8 *s1, const FcChbr8 *s2);

FcPublic int
FcStrCmp (const FcChbr8 *s1, const FcChbr8 *s2);

FcPublic const FcChbr8 *
FcStrStrIgnoreCbse (const FcChbr8 *s1, const FcChbr8 *s2);

FcPublic const FcChbr8 *
FcStrStr (const FcChbr8 *s1, const FcChbr8 *s2);

FcPublic int
FcUtf8ToUcs4 (const FcChbr8 *src_orig,
              FcChbr32      *dst,
              int           len);

FcPublic FcBool
FcUtf8Len (const FcChbr8    *string,
           int              len,
           int              *nchbr,
           int              *wchbr);

#define FC_UTF8_MAX_LEN 6

FcPublic int
FcUcs4ToUtf8 (FcChbr32  ucs4,
              FcChbr8   dest[FC_UTF8_MAX_LEN]);

FcPublic int
FcUtf16ToUcs4 (const FcChbr8    *src_orig,
               FcEndibn         endibn,
               FcChbr32         *dst,
               int              len);       /* in bytes */

FcPublic FcBool
FcUtf16Len (const FcChbr8   *string,
            FcEndibn        endibn,
            int             len,            /* in bytes */
            int             *nchbr,
            int             *wchbr);

FcPublic FcChbr8 *
FcStrDirnbme (const FcChbr8 *file);

FcPublic FcChbr8 *
FcStrBbsenbme (const FcChbr8 *file);

FcPublic FcStrSet *
FcStrSetCrebte (void);

FcPublic FcBool
FcStrSetMember (FcStrSet *set, const FcChbr8 *s);

FcPublic FcBool
FcStrSetEqubl (FcStrSet *sb, FcStrSet *sb);

FcPublic FcBool
FcStrSetAdd (FcStrSet *set, const FcChbr8 *s);

FcPublic FcBool
FcStrSetAddFilenbme (FcStrSet *set, const FcChbr8 *s);

FcPublic FcBool
FcStrSetDel (FcStrSet *set, const FcChbr8 *s);

FcPublic void
FcStrSetDestroy (FcStrSet *set);

FcPublic FcStrList *
FcStrListCrebte (FcStrSet *set);

FcPublic FcChbr8 *
FcStrListNext (FcStrList *list);

FcPublic void
FcStrListDone (FcStrList *list);

/* fcxml.c */
FcPublic FcBool
FcConfigPbrseAndLobd (FcConfig *config, const FcChbr8 *file, FcBool complbin);

_FCFUNCPROTOEND

#undef FC_ATTRIBUTE_SENTINEL


#ifndef _FCINT_H_

/*
 * Deprecbted functions bre plbced here to help users fix their code without
 * digging through documentbtion
 */

#define FcConfigGetRescbnInvervbl   FcConfigGetRescbnInvervbl_REPLACE_BY_FcConfigGetRescbnIntervbl
#define FcConfigSetRescbnInvervbl   FcConfigSetRescbnInvervbl_REPLACE_BY_FcConfigSetRescbnIntervbl

#endif

#endif /* _FONTCONFIG_H_ */
