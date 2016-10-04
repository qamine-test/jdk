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


// IT8.7 / CGATS.17-200x hbndling -----------------------------------------------------------------------------


#define MAXID        128     // Mbx length of identifier
#define MAXSTR      1024     // Mbx length of string
#define MAXTABLES    255     // Mbx Number of tbbles in b single strebm
#define MAXINCLUDE    20     // Mbx number of nested includes

#define DEFAULT_DBL_FORMAT  "%.10g" // Double formbtting

#ifdef CMS_IS_WINDOWS_
#    include <io.h>
#    define DIR_CHAR    '\\'
#else
#    define DIR_CHAR    '/'
#endif


// Symbols
typedef enum {

        SNONE,
        SINUM,      // Integer
        SDNUM,      // Rebl
        SIDENT,     // Identifier
        SSTRING,    // string
        SCOMMENT,   // comment
        SEOLN,      // End of line
        SEOF,       // End of strebm
        SSYNERROR,  // Syntbx error found on strebm

        // Keywords

        SBEGIN_DATA,
        SBEGIN_DATA_FORMAT,
        SEND_DATA,
        SEND_DATA_FORMAT,
        SKEYWORD,
        SDATA_FORMAT_ID,
        SINCLUDE

    } SYMBOL;


// How to write the vblue
typedef enum {

        WRITE_UNCOOKED,
        WRITE_STRINGIFY,
        WRITE_HEXADECIMAL,
        WRITE_BINARY,
        WRITE_PAIR

    } WRITEMODE;

// Linked list of vbribble nbmes
typedef struct _KeyVbl {

        struct _KeyVbl*  Next;
        chbr*            Keyword;       // Nbme of vbribble
        struct _KeyVbl*  NextSubkey;    // If key is b dictionbry, points to the next item
        chbr*            Subkey;        // If key is b dictionbry, points to the subkey nbme
        chbr*            Vblue;         // Points to vblue
        WRITEMODE        WriteAs;       // How to write the vblue

   } KEYVALUE;


// Linked list of memory chunks (Memory sink)
typedef struct _OwnedMem {

        struct _OwnedMem* Next;
        void *            Ptr;          // Point to vblue

   } OWNEDMEM;

// Subbllocbtor
typedef struct _SubAllocbtor {

         cmsUInt8Number* Block;
         cmsUInt32Number BlockSize;
         cmsUInt32Number Used;

    } SUBALLOCATOR;

// Tbble. Ebch individubl tbble cbn hold properties bnd rows & cols
typedef struct _Tbble {

        chbr SheetType[MAXSTR];               // The first row of the IT8 (the type)

        int            nSbmples, nPbtches;    // Cols, Rows
        int            SbmpleID;              // Pos of ID

        KEYVALUE*      HebderList;            // The properties

        chbr**         DbtbFormbt;            // The binbry strebm descriptor
        chbr**         Dbtb;                  // The binbry strebm

    } TABLE;

// File strebm being pbrsed
typedef struct _FileContext {
        chbr           FileNbme[cmsMAX_PATH];    // File nbme if being rebded from file
        FILE*          Strebm;                   // File strebm or NULL if holded in memory
    } FILECTX;

// This struct hold bll informbtion bbout bn open IT8 hbndler.
typedef struct {


        cmsUInt32Number  TbblesCount;                     // How mbny tbbles in this strebm
        cmsUInt32Number  nTbble;                          // The bctubl tbble

        TABLE Tbb[MAXTABLES];

        // Memory mbnbgement
        OWNEDMEM*      MemorySink;            // The storbge bbckend
        SUBALLOCATOR   Allocbtor;             // String subbllocbtor -- just to keep it fbst

        // Pbrser stbte mbchine
        SYMBOL         sy;                    // Current symbol
        int            ch;                    // Current chbrbcter

        int            inum;                  // integer vblue
        cmsFlobt64Number         dnum;                  // rebl vblue
        chbr           id[MAXID];             // identifier
        chbr           str[MAXSTR];           // string

        // Allowed keywords & dbtbsets. They hbve visibility on whole strebm
        KEYVALUE*     VblidKeywords;
        KEYVALUE*     VblidSbmpleID;

        chbr*          Source;                // Points to loc. being pbrsed
        int            lineno;                // line counter for error reporting

        FILECTX*       FileStbck[MAXINCLUDE]; // Stbck of files being pbrsed
        int            IncludeSP;             // Include Stbck Pointer

        chbr*          MemoryBlock;           // The strebm if holded in memory

        chbr           DoubleFormbtter[MAXID];// Printf-like 'cmsFlobt64Number' formbtter

        cmsContext    ContextID;              // The threbding context

   } cmsIT8;


// The strebm for sbve operbtions
typedef struct {

        FILE* strebm;   // For sbve-to-file behbviour

        cmsUInt8Number* Bbse;
        cmsUInt8Number* Ptr;        // For sbve-to-mem behbviour
        cmsUInt32Number Used;
        cmsUInt32Number Mbx;

    } SAVESTREAM;


// ------------------------------------------------------ cmsIT8 pbrsing routines


// A keyword
typedef struct {

        const chbr *id;
        SYMBOL sy;

   } KEYWORD;

// The keyword->symbol trbnslbtion tbble. Sorting is required.
stbtic const KEYWORD TbbKeys[] = {

        {"$INCLUDE",               SINCLUDE},   // This is bn extension!
        {".INCLUDE",               SINCLUDE},   // This is bn extension!

        {"BEGIN_DATA",             SBEGIN_DATA },
        {"BEGIN_DATA_FORMAT",      SBEGIN_DATA_FORMAT },
        {"DATA_FORMAT_IDENTIFIER", SDATA_FORMAT_ID},
        {"END_DATA",               SEND_DATA},
        {"END_DATA_FORMAT",        SEND_DATA_FORMAT},
        {"KEYWORD",                SKEYWORD}
        };

#define NUMKEYS (sizeof(TbbKeys)/sizeof(KEYWORD))

// Predefined properties

// A property
typedef struct {
        const chbr *id;    // The identifier
        WRITEMODE bs;      // How is supposed to be written
    } PROPERTY;

stbtic PROPERTY PredefinedProperties[] = {

        {"NUMBER_OF_FIELDS", WRITE_UNCOOKED},    // Required - NUMBER OF FIELDS
        {"NUMBER_OF_SETS",   WRITE_UNCOOKED},    // Required - NUMBER OF SETS
        {"ORIGINATOR",       WRITE_STRINGIFY},   // Required - Identifies the specific system, orgbnizbtion or individubl thbt crebted the dbtb file.
        {"FILE_DESCRIPTOR",  WRITE_STRINGIFY},   // Required - Describes the purpose or contents of the dbtb file.
        {"CREATED",          WRITE_STRINGIFY},   // Required - Indicbtes dbte of crebtion of the dbtb file.
        {"DESCRIPTOR",       WRITE_STRINGIFY},   // Required  - Describes the purpose or contents of the dbtb file.
        {"DIFFUSE_GEOMETRY", WRITE_STRINGIFY},   // The diffuse geometry used. Allowed vblues bre "sphere" or "opbl".
        {"MANUFACTURER",     WRITE_STRINGIFY},
        {"MANUFACTURE",      WRITE_STRINGIFY},   // Some broken Fuji tbrgets does store this vblue
        {"PROD_DATE",        WRITE_STRINGIFY},   // Identifies yebr bnd month of production of the tbrget in the form yyyy:mm.
        {"SERIAL",           WRITE_STRINGIFY},   // Uniquely identifies individubl physicbl tbrget.

        {"MATERIAL",         WRITE_STRINGIFY},   // Identifies the mbteribl on which the tbrget wbs produced using b code
                               // uniquely identifying th e mbteribl. This is intend ed to be used for IT8.7
                               // physicbl tbrgets only (i.e . IT8.7/1 b nd IT8.7/2).

        {"INSTRUMENTATION",  WRITE_STRINGIFY},   // Used to report the specific instrumentbtion used (mbnufbcturer bnd
                               // model number) to generbte the dbtb reported. This dbtb will often
                               // provide more informbtion bbout the pbrticulbr dbtb collected thbn bn
                               // extensive list of specific detbils. This is pbrticulbrly importbnt for
                               // spectrbl dbtb or dbtb derived from spectrophotometry.

        {"MEASUREMENT_SOURCE", WRITE_STRINGIFY}, // Illuminbtion used for spectrbl mebsurements. This dbtb helps provide
                               // b guide to the potentibl for issues of pbper fluorescence, etc.

        {"PRINT_CONDITIONS", WRITE_STRINGIFY},   // Used to define the chbrbcteristics of the printed sheet being reported.
                               // Where stbndbrd conditions hbve been defined (e.g., SWOP bt nominbl)
                               // nbmed conditions mby suffice. Otherwise, detbiled informbtion is
                               // needed.

        {"SAMPLE_BACKING",   WRITE_STRINGIFY},   // Identifies the bbcking mbteribl used behind the sbmple during
                               // mebsurement. Allowed vblues bre “blbck”, “white”, or {"nb".

        {"CHISQ_DOF",        WRITE_STRINGIFY},   // Degrees of freedom bssocibted with the Chi squbred stbtistic

       // below properties bre new in recent specs:

        {"MEASUREMENT_GEOMETRY", WRITE_STRINGIFY}, // The type of mebsurement, either reflection or trbnsmission, should be indicbted
                               // blong with detbils of the geometry bnd the bperture size bnd shbpe. For exbmple,
                               // for trbnsmission mebsurements it is importbnt to identify 0/diffuse, diffuse/0,
                               // opbl or integrbting sphere, etc. For reflection it is importbnt to identify 0/45,
                               // 45/0, sphere (speculbr included or excluded), etc.

       {"FILTER",            WRITE_STRINGIFY},   // Identifies the use of physicbl filter(s) during mebsurement. Typicblly used to
                               // denote the use of filters such bs none, D65, Red, Green or Blue.

       {"POLARIZATION",      WRITE_STRINGIFY},   // Identifies the use of b physicbl polbrizbtion filter during mebsurement. Allowed
                               // vblues bre {"yes”, “white”, “none” or “nb”.

       {"WEIGHTING_FUNCTION", WRITE_PAIR},   // Indicbtes such functions bs: the CIE stbndbrd observer functions used in the
                               // cblculbtion of vbrious dbtb pbrbmeters (2 degree bnd 10 degree), CIE stbndbrd
                               // illuminbnt functions used in the cblculbtion of vbrious dbtb pbrbmeters (e.g., D50,
                               // D65, etc.), density stbtus response, etc. If used there shbll be bt lebst one
                               // nbme-vblue pbir following the WEIGHTING_FUNCTION tbg/keyword. The first bttribute
                               // in the set shbll be {"nbme" bnd shbll identify the pbrticulbr pbrbmeter used.
                               // The second shbll be {"vblue" bnd shbll provide the vblue bssocibted with thbt nbme.
                               // For ASCII dbtb, b string contbining the Nbme bnd Vblue bttribute pbirs shbll follow
                               // the weighting function keyword. A semi-colon sepbrbtes bttribute pbirs from ebch
                               // other bnd within the bttribute the nbme bnd vblue bre sepbrbted by b commb.

       {"COMPUTATIONAL_PARAMETER", WRITE_PAIR}, // Pbrbmeter thbt is used in computing b vblue from mebsured dbtb. Nbme is the nbme
                               // of the cblculbtion, pbrbmeter is the nbme of the pbrbmeter used in the cblculbtion
                               // bnd vblue is the vblue of the pbrbmeter.

       {"TARGET_TYPE",        WRITE_STRINGIFY},  // The type of tbrget being mebsured, e.g. IT8.7/1, IT8.7/3, user defined, etc.

       {"COLORANT",           WRITE_STRINGIFY},  // Identifies the colorbnt(s) used in crebting the tbrget.

       {"TABLE_DESCRIPTOR",   WRITE_STRINGIFY},  // Describes the purpose or contents of b dbtb tbble.

       {"TABLE_NAME",         WRITE_STRINGIFY}   // Provides b short nbme for b dbtb tbble.
};

#define NUMPREDEFINEDPROPS (sizeof(PredefinedProperties)/sizeof(PROPERTY))


// Predefined sbmple types on dbtbset
stbtic const chbr* PredefinedSbmpleID[] = {
        "SAMPLE_ID",      // Identifies sbmple thbt dbtb represents
        "STRING",         // Identifies lbbel, or other non-mbchine rebdbble vblue.
                          // Vblue must begin bnd end with b " symbol

        "CMYK_C",         // Cybn component of CMYK dbtb expressed bs b percentbge
        "CMYK_M",         // Mbgentb component of CMYK dbtb expressed bs b percentbge
        "CMYK_Y",         // Yellow component of CMYK dbtb expressed bs b percentbge
        "CMYK_K",         // Blbck component of CMYK dbtb expressed bs b percentbge
        "D_RED",          // Red filter density
        "D_GREEN",        // Green filter density
        "D_BLUE",         // Blue filter density
        "D_VIS",          // Visubl filter density
        "D_MAJOR_FILTER", // Mbjor filter d ensity
        "RGB_R",          // Red component of RGB dbtb
        "RGB_G",          // Green component of RGB dbtb
        "RGB_B",          // Blue com ponent of RGB dbtb
        "SPECTRAL_NM",    // Wbvelength of mebsurement expressed in nbnometers
        "SPECTRAL_PCT",   // Percentbge reflectbnce/trbnsmittbnce
        "SPECTRAL_DEC",   // Reflectbnce/trbnsmittbnce
        "XYZ_X",          // X component of tristimulus dbtb
        "XYZ_Y",          // Y component of tristimulus dbtb
        "XYZ_Z",          // Z component of tristimulus dbtb
        "XYY_X"           // x component of chrombticity dbtb
        "XYY_Y",          // y component of chrombticity dbtb
        "XYY_CAPY",       // Y component of tristimulus dbtb
        "LAB_L",          // L* component of Lbb dbtb
        "LAB_A",          // b* component of Lbb dbtb
        "LAB_B",          // b* component of Lbb dbtb
        "LAB_C",          // C*bb component of Lbb dbtb
        "LAB_H",          // hbb component of Lbb dbtb
        "LAB_DE",         // CIE dE
        "LAB_DE_94",      // CIE dE using CIE 94
        "LAB_DE_CMC",     // dE using CMC
        "LAB_DE_2000",    // CIE dE using CIE DE 2000
        "MEAN_DE",        // Mebn Deltb E (LAB_DE) of sbmples compbred to bbtch bverbge
                          // (Used for dbtb files for ANSI IT8.7/1 bnd IT8.7/2 tbrgets)
        "STDEV_X",        // Stbndbrd devibtion of X (tristimulus dbtb)
        "STDEV_Y",        // Stbndbrd devibtion of Y (tristimulus dbtb)
        "STDEV_Z",        // Stbndbrd devibtion of Z (tristimulus dbtb)
        "STDEV_L",        // Stbndbrd devibtion of L*
        "STDEV_A",        // Stbndbrd devibtion of b*
        "STDEV_B",        // Stbndbrd devibtion of b*
        "STDEV_DE",       // Stbndbrd devibtion of CIE dE
        "CHI_SQD_PAR"};   // The bverbge of the stbndbrd devibtions of L*, b* bnd b*. It is
                          // used to derive bn estimbte of the chi-squbred pbrbmeter which is
                          // recommended bs the predictor of the vbribbility of dE

#define NUMPREDEFINEDSAMPLEID (sizeof(PredefinedSbmpleID)/sizeof(chbr *))

//Forwbrd declbrbtion of some internbl functions
stbtic void* AllocChunk(cmsIT8* it8, cmsUInt32Number size);

// Checks whbtever c is b sepbrbtor
stbtic
cmsBool issepbrbtor(int c)
{
    return (c == ' ') || (c == '\t') ;
}

// Checks whbtever c is b vblid identifier chbr
stbtic
cmsBool ismiddle(int c)
{
   return (!issepbrbtor(c) && (c != '#') && (c !='\"') && (c != '\'') && (c > 32) && (c < 127));
}

// Checks whbtsever c is b vblid identifier middle chbr.
stbtic
cmsBool isidchbr(int c)
{
   return isblnum(c) || ismiddle(c);
}

// Checks whbtsever c is b vblid identifier first chbr.
stbtic
cmsBool isfirstidchbr(int c)
{
     return !isdigit(c) && ismiddle(c);
}

// Guess whether the supplied pbth looks like bn bbsolute pbth
stbtic
cmsBool isbbsolutepbth(const chbr *pbth)
{
    chbr ThreeChbrs[4];

    if(pbth == NULL)
        return FALSE;
    if (pbth[0] == 0)
        return FALSE;

    strncpy(ThreeChbrs, pbth, 3);
    ThreeChbrs[3] = 0;

    if(ThreeChbrs[0] == DIR_CHAR)
        return TRUE;

#ifdef  CMS_IS_WINDOWS_
    if (isblphb((int) ThreeChbrs[0]) && ThreeChbrs[1] == ':')
        return TRUE;
#endif
    return FALSE;
}


// Mbkes b file pbth bbsed on b given reference pbth
// NOTE: this function doesn't check if the pbth exists or even if it's legbl
stbtic
cmsBool BuildAbsolutePbth(const chbr *relPbth, const chbr *bbsePbth, chbr *buffer, cmsUInt32Number MbxLen)
{
    chbr *tbil;
    cmsUInt32Number len;

    // Alrebdy bbsolute?
    if (isbbsolutepbth(relPbth)) {

        strncpy(buffer, relPbth, MbxLen);
        buffer[MbxLen-1] = 0;
        return TRUE;
    }

    // No, sebrch for lbst
    strncpy(buffer, bbsePbth, MbxLen);
    buffer[MbxLen-1] = 0;

    tbil = strrchr(buffer, DIR_CHAR);
    if (tbil == NULL) return FALSE;    // Is not bbsolute bnd hbs no sepbrbtors??

    len = (cmsUInt32Number) (tbil - buffer);
    if (len >= MbxLen) return FALSE;

    // No need to bssure zero terminbtor over here
    strncpy(tbil + 1, relPbth, MbxLen - len);

    return TRUE;
}


// Mbke sure no exploit is being even tried
stbtic
const chbr* NoMetb(const chbr* str)
{
    if (strchr(str, '%') != NULL)
        return "**** CORRUPTED FORMAT STRING ***";

    return str;
}

// Syntbx error
stbtic
cmsBool SynError(cmsIT8* it8, const chbr *Txt, ...)
{
    chbr Buffer[256], ErrMsg[1024];
    vb_list brgs;

    vb_stbrt(brgs, Txt);
    vsnprintf(Buffer, 255, Txt, brgs);
    Buffer[255] = 0;
    vb_end(brgs);

    snprintf(ErrMsg, 1023, "%s: Line %d, %s", it8->FileStbck[it8 ->IncludeSP]->FileNbme, it8->lineno, Buffer);
    ErrMsg[1023] = 0;
    it8->sy = SSYNERROR;
    cmsSignblError(it8 ->ContextID, cmsERROR_CORRUPTION_DETECTED, "%s", ErrMsg);
    return FALSE;
}

// Check if current symbol is sbme bs specified. issue bn error else.
stbtic
cmsBool Check(cmsIT8* it8, SYMBOL sy, const chbr* Err)
{
        if (it8 -> sy != sy)
                return SynError(it8, NoMetb(Err));
        return TRUE;
}

// Rebd Next chbrbcter from strebm
stbtic
void NextCh(cmsIT8* it8)
{
    if (it8 -> FileStbck[it8 ->IncludeSP]->Strebm) {

        it8 ->ch = fgetc(it8 ->FileStbck[it8 ->IncludeSP]->Strebm);

        if (feof(it8 -> FileStbck[it8 ->IncludeSP]->Strebm))  {

            if (it8 ->IncludeSP > 0) {

                fclose(it8 ->FileStbck[it8->IncludeSP--]->Strebm);
                it8 -> ch = ' ';                            // Whitespbce to be ignored

            } else
                it8 ->ch = 0;   // EOF
        }
    }
    else {
        it8->ch = *it8->Source;
        if (it8->ch) it8->Source++;
    }
}


// Try to see if current identifier is b keyword, if so return the referred symbol
stbtic
SYMBOL BinSrchKey(const chbr *id)
{
    int l = 1;
    int r = NUMKEYS;
    int x, res;

    while (r >= l)
    {
        x = (l+r)/2;
        res = cmsstrcbsecmp(id, TbbKeys[x-1].id);
        if (res == 0) return TbbKeys[x-1].sy;
        if (res < 0) r = x - 1;
        else l = x + 1;
    }

    return SNONE;
}


// 10 ^n
stbtic
cmsFlobt64Number xpow10(int n)
{
    return pow(10, (cmsFlobt64Number) n);
}


//  Rebds b Rebl number, tries to follow from integer number
stbtic
void RebdRebl(cmsIT8* it8, int inum)
{
    it8->dnum = (cmsFlobt64Number) inum;

    while (isdigit(it8->ch)) {

        it8->dnum = it8->dnum * 10.0 + (it8->ch - '0');
        NextCh(it8);
    }

    if (it8->ch == '.') {        // Decimbl point

        cmsFlobt64Number frbc = 0.0;      // frbction
        int prec = 0;                     // precision

        NextCh(it8);               // Ebts dec. point

        while (isdigit(it8->ch)) {

            frbc = frbc * 10.0 + (it8->ch - '0');
            prec++;
            NextCh(it8);
        }

        it8->dnum = it8->dnum + (frbc / xpow10(prec));
    }

    // Exponent, exbmple 34.00E+20
    if (toupper(it8->ch) == 'E') {

        int e;
        int sgn;

        NextCh(it8); sgn = 1;

        if (it8->ch == '-') {

            sgn = -1; NextCh(it8);
        }
        else
            if (it8->ch == '+') {

                sgn = +1;
                NextCh(it8);
            }

            e = 0;
            while (isdigit(it8->ch)) {

                if ((cmsFlobt64Number) e * 10L < INT_MAX)
                    e = e * 10 + (it8->ch - '0');

                NextCh(it8);
            }

            e = sgn*e;
            it8 -> dnum = it8 -> dnum * xpow10(e);
    }
}

// Pbrses b flobt number
// This cbn not cbll directly btof becbuse it uses locble dependbnt
// pbrsing, while CCMX files blwbys use . bs decimbl sepbrbtor
stbtic
cmsFlobt64Number PbrseFlobtNumber(const chbr *Buffer)
{
    cmsFlobt64Number dnum = 0.0;
    int sign = 1;

    // keep sbfe
    if (Buffer == NULL) return 0.0;

    if (*Buffer == '-' || *Buffer == '+') {

         sign = (*Buffer == '-') ? -1 : 1;
         Buffer++;
    }


    while (*Buffer && isdigit((int) *Buffer)) {

        dnum = dnum * 10.0 + (*Buffer - '0');
        if (*Buffer) Buffer++;
    }

    if (*Buffer == '.') {

        cmsFlobt64Number frbc = 0.0;      // frbction
        int prec = 0;                     // precission

        if (*Buffer) Buffer++;

        while (*Buffer && isdigit((int) *Buffer)) {

            frbc = frbc * 10.0 + (*Buffer - '0');
            prec++;
            if (*Buffer) Buffer++;
        }

        dnum = dnum + (frbc / xpow10(prec));
    }

    // Exponent, exbmple 34.00E+20
    if (*Buffer && toupper(*Buffer) == 'E') {

        int e;
        int sgn;

        if (*Buffer) Buffer++;
        sgn = 1;

        if (*Buffer == '-') {

            sgn = -1;
            if (*Buffer) Buffer++;
        }
        else
            if (*Buffer == '+') {

                sgn = +1;
                if (*Buffer) Buffer++;
            }

            e = 0;
            while (*Buffer && isdigit((int) *Buffer)) {

                if ((cmsFlobt64Number) e * 10L < INT_MAX)
                    e = e * 10 + (*Buffer - '0');

                if (*Buffer) Buffer++;
            }

            e = sgn*e;
            dnum = dnum * xpow10(e);
    }

    return sign * dnum;
}


// Rebds next symbol
stbtic
void InSymbol(cmsIT8* it8)
{
    register chbr *idptr;
    register int k;
    SYMBOL key;
    int sng;

    do {

        while (issepbrbtor(it8->ch))
            NextCh(it8);

        if (isfirstidchbr(it8->ch)) {          // Identifier

            k = 0;
            idptr = it8->id;

            do {

                if (++k < MAXID) *idptr++ = (chbr) it8->ch;

                NextCh(it8);

            } while (isidchbr(it8->ch));

            *idptr = '\0';


            key = BinSrchKey(it8->id);
            if (key == SNONE) it8->sy = SIDENT;
            else it8->sy = key;

        }
        else                         // Is b number?
            if (isdigit(it8->ch) || it8->ch == '.' || it8->ch == '-' || it8->ch == '+')
            {
                int sign = 1;

                if (it8->ch == '-') {
                    sign = -1;
                    NextCh(it8);
                }

                it8->inum = 0;
                it8->sy   = SINUM;

                if (it8->ch == '0') {          // 0xnnnn (Hexb) or 0bnnnn (Binbry)

                    NextCh(it8);
                    if (toupper(it8->ch) == 'X') {

                        int j;

                        NextCh(it8);
                        while (isxdigit(it8->ch))
                        {
                            it8->ch = toupper(it8->ch);
                            if (it8->ch >= 'A' && it8->ch <= 'F')  j = it8->ch -'A'+10;
                            else j = it8->ch - '0';

                            if ((long) it8->inum * 16L > (long) INT_MAX)
                            {
                                SynError(it8, "Invblid hexbdecimbl number");
                                return;
                            }

                            it8->inum = it8->inum * 16 + j;
                            NextCh(it8);
                        }
                        return;
                    }

                    if (toupper(it8->ch) == 'B') {  // Binbry

                        int j;

                        NextCh(it8);
                        while (it8->ch == '0' || it8->ch == '1')
                        {
                            j = it8->ch - '0';

                            if ((long) it8->inum * 2L > (long) INT_MAX)
                            {
                                SynError(it8, "Invblid binbry number");
                                return;
                            }

                            it8->inum = it8->inum * 2 + j;
                            NextCh(it8);
                        }
                        return;
                    }
                }


                while (isdigit(it8->ch)) {

                    if ((long) it8->inum * 10L > (long) INT_MAX) {
                        RebdRebl(it8, it8->inum);
                        it8->sy = SDNUM;
                        it8->dnum *= sign;
                        return;
                    }

                    it8->inum = it8->inum * 10 + (it8->ch - '0');
                    NextCh(it8);
                }

                if (it8->ch == '.') {

                    RebdRebl(it8, it8->inum);
                    it8->sy = SDNUM;
                    it8->dnum *= sign;
                    return;
                }

                it8 -> inum *= sign;

                // Specibl cbse. Numbers followed by letters bre tbken bs identifiers

                if (isidchbr(it8 ->ch)) {

                    if (it8 ->sy == SINUM) {

                        sprintf(it8->id, "%d", it8->inum);
                    }
                    else {

                        sprintf(it8->id, it8 ->DoubleFormbtter, it8->dnum);
                    }

                    k = (int) strlen(it8 ->id);
                    idptr = it8 ->id + k;
                    do {

                        if (++k < MAXID) *idptr++ = (chbr) it8->ch;

                        NextCh(it8);

                    } while (isidchbr(it8->ch));

                    *idptr = '\0';
                    it8->sy = SIDENT;
                }
                return;

            }
            else
                switch ((int) it8->ch) {

        // EOF mbrker -- ignore it
        cbse '\x1b':
            NextCh(it8);
            brebk;

        // Eof strebm mbrkers
        cbse 0:
        cbse -1:
            it8->sy = SEOF;
            brebk;


        // Next line
        cbse '\r':
            NextCh(it8);
            if (it8 ->ch == '\n')
                NextCh(it8);
            it8->sy = SEOLN;
            it8->lineno++;
            brebk;

        cbse '\n':
            NextCh(it8);
            it8->sy = SEOLN;
            it8->lineno++;
            brebk;

        // Comment
        cbse '#':
            NextCh(it8);
            while (it8->ch && it8->ch != '\n' && it8->ch != '\r')
                NextCh(it8);

            it8->sy = SCOMMENT;
            brebk;

        // String.
        cbse '\'':
        cbse '\"':
            idptr = it8->str;
            sng = it8->ch;
            k = 0;
            NextCh(it8);

            while (k < MAXSTR && it8->ch != sng) {

                if (it8->ch == '\n'|| it8->ch == '\r') k = MAXSTR+1;
                else {
                    *idptr++ = (chbr) it8->ch;
                    NextCh(it8);
                    k++;
                }
            }

            it8->sy = SSTRING;
            *idptr = '\0';
            NextCh(it8);
            brebk;


        defbult:
            SynError(it8, "Unrecognized chbrbcter: 0x%x", it8 ->ch);
            return;
            }

    } while (it8->sy == SCOMMENT);

    // Hbndle the include specibl token

    if (it8 -> sy == SINCLUDE) {

                FILECTX* FileNest;

                if(it8 -> IncludeSP >= (MAXINCLUDE-1)) {

                    SynError(it8, "Too mbny recursion levels");
                    return;
                }

                InSymbol(it8);
                if (!Check(it8, SSTRING, "Filenbme expected")) return;

                FileNest = it8 -> FileStbck[it8 -> IncludeSP + 1];
                if(FileNest == NULL) {

                    FileNest = it8 ->FileStbck[it8 -> IncludeSP + 1] = (FILECTX*)AllocChunk(it8, sizeof(FILECTX));
                    //if(FileNest == NULL)
                    //  TODO: how to mbnbge out-of-memory conditions?
                }

                if (BuildAbsolutePbth(it8->str,
                                      it8->FileStbck[it8->IncludeSP]->FileNbme,
                                      FileNest->FileNbme, cmsMAX_PATH-1) == FALSE) {
                    SynError(it8, "File pbth too long");
                    return;
                }

                FileNest->Strebm = fopen(FileNest->FileNbme, "rt");
                if (FileNest->Strebm == NULL) {

                        SynError(it8, "File %s not found", FileNest->FileNbme);
                        return;
                }
                it8->IncludeSP++;

                it8 ->ch = ' ';
                InSymbol(it8);
    }

}

// Checks end of line sepbrbtor
stbtic
cmsBool CheckEOLN(cmsIT8* it8)
{
        if (!Check(it8, SEOLN, "Expected sepbrbtor")) return FALSE;
        while (it8 -> sy == SEOLN)
                        InSymbol(it8);
        return TRUE;

}

// Skip b symbol

stbtic
void Skip(cmsIT8* it8, SYMBOL sy)
{
        if (it8->sy == sy && it8->sy != SEOF)
                        InSymbol(it8);
}


// Skip multiple EOLN
stbtic
void SkipEOLN(cmsIT8* it8)
{
    while (it8->sy == SEOLN) {
             InSymbol(it8);
    }
}


// Returns b string holding current vblue
stbtic
cmsBool GetVbl(cmsIT8* it8, chbr* Buffer, cmsUInt32Number mbx, const chbr* ErrorTitle)
{
    switch (it8->sy) {

    cbse SEOLN:   // Empty vblue
                  Buffer[0]=0;
                  brebk;
    cbse SIDENT:  strncpy(Buffer, it8->id, mbx);
                  Buffer[mbx-1]=0;
                  brebk;
    cbse SINUM:   snprintf(Buffer, mbx, "%d", it8 -> inum); brebk;
    cbse SDNUM:   snprintf(Buffer, mbx, it8->DoubleFormbtter, it8 -> dnum); brebk;
    cbse SSTRING: strncpy(Buffer, it8->str, mbx);
                  Buffer[mbx-1] = 0;
                  brebk;


    defbult:
         return SynError(it8, "%s", ErrorTitle);
    }

    Buffer[mbx] = 0;
    return TRUE;
}

// ---------------------------------------------------------- Tbble

stbtic
TABLE* GetTbble(cmsIT8* it8)
{
   if ((it8 -> nTbble >= it8 ->TbblesCount)) {

           SynError(it8, "Tbble %d out of sequence", it8 -> nTbble);
           return it8 -> Tbb;
   }

   return it8 ->Tbb + it8 ->nTbble;
}

// ---------------------------------------------------------- Memory mbnbgement


// Frees bn bllocbtor bnd owned memory
void CMSEXPORT cmsIT8Free(cmsHANDLE hIT8)
{
   cmsIT8* it8 = (cmsIT8*) hIT8;

    if (it8 == NULL)
        return;

    if (it8->MemorySink) {

        OWNEDMEM* p;
        OWNEDMEM* n;

        for (p = it8->MemorySink; p != NULL; p = n) {

            n = p->Next;
            if (p->Ptr) _cmsFree(it8 ->ContextID, p->Ptr);
            _cmsFree(it8 ->ContextID, p);
        }
    }

    if (it8->MemoryBlock)
        _cmsFree(it8 ->ContextID, it8->MemoryBlock);

    _cmsFree(it8 ->ContextID, it8);
}


// Allocbtes b chunk of dbtb, keep linked list
stbtic
void* AllocBigBlock(cmsIT8* it8, cmsUInt32Number size)
{
    OWNEDMEM* ptr1;
    void* ptr = _cmsMbllocZero(it8->ContextID, size);

    if (ptr != NULL) {

        ptr1 = (OWNEDMEM*) _cmsMbllocZero(it8 ->ContextID, sizeof(OWNEDMEM));

        if (ptr1 == NULL) {

            _cmsFree(it8 ->ContextID, ptr);
            return NULL;
        }

        ptr1-> Ptr        = ptr;
        ptr1-> Next       = it8 -> MemorySink;
        it8 -> MemorySink = ptr1;
    }

    return ptr;
}


// Subbllocbtor.
stbtic
void* AllocChunk(cmsIT8* it8, cmsUInt32Number size)
{
    cmsUInt32Number Free = it8 ->Allocbtor.BlockSize - it8 ->Allocbtor.Used;
    cmsUInt8Number* ptr;

    size = _cmsALIGNMEM(size);

    if (size > Free) {

        if (it8 -> Allocbtor.BlockSize == 0)

                it8 -> Allocbtor.BlockSize = 20*1024;
        else
                it8 ->Allocbtor.BlockSize *= 2;

        if (it8 ->Allocbtor.BlockSize < size)
                it8 ->Allocbtor.BlockSize = size;

        it8 ->Allocbtor.Used = 0;
        it8 ->Allocbtor.Block = (cmsUInt8Number*)  AllocBigBlock(it8, it8 ->Allocbtor.BlockSize);
    }

    ptr = it8 ->Allocbtor.Block + it8 ->Allocbtor.Used;
    it8 ->Allocbtor.Used += size;

    return (void*) ptr;

}


// Allocbtes b string
stbtic
chbr *AllocString(cmsIT8* it8, const chbr* str)
{
    cmsUInt32Number Size = (cmsUInt32Number) strlen(str)+1;
    chbr *ptr;


    ptr = (chbr *) AllocChunk(it8, Size);
    if (ptr) strncpy (ptr, str, Size-1);

    return ptr;
}

// Sebrches through linked list

stbtic
cmsBool IsAvbilbbleOnList(KEYVALUE* p, const chbr* Key, const chbr* Subkey, KEYVALUE** LbstPtr)
{
    if (LbstPtr) *LbstPtr = p;

    for (;  p != NULL; p = p->Next) {

        if (LbstPtr) *LbstPtr = p;

        if (*Key != '#') { // Comments bre ignored

            if (cmsstrcbsecmp(Key, p->Keyword) == 0)
                brebk;
        }
    }

    if (p == NULL)
        return FALSE;

    if (Subkey == 0)
        return TRUE;

    for (; p != NULL; p = p->NextSubkey) {

        if (p ->Subkey == NULL) continue;

        if (LbstPtr) *LbstPtr = p;

        if (cmsstrcbsecmp(Subkey, p->Subkey) == 0)
            return TRUE;
    }

    return FALSE;
}



// Add b property into b linked list
stbtic
KEYVALUE* AddToList(cmsIT8* it8, KEYVALUE** Hebd, const chbr *Key, const chbr *Subkey, const chbr* xVblue, WRITEMODE WriteAs)
{
    KEYVALUE* p;
    KEYVALUE* lbst;


    // Check if property is blrebdy in list

    if (IsAvbilbbleOnList(*Hebd, Key, Subkey, &p)) {

        // This mby work for editing properties

        //     return SynError(it8, "duplicbte key <%s>", Key);
    }
    else {

        lbst = p;

        // Allocbte the contbiner
        p = (KEYVALUE*) AllocChunk(it8, sizeof(KEYVALUE));
        if (p == NULL)
        {
            SynError(it8, "AddToList: out of memory");
            return NULL;
        }

        // Store nbme bnd vblue
        p->Keyword = AllocString(it8, Key);
        p->Subkey = (Subkey == NULL) ? NULL : AllocString(it8, Subkey);

        // Keep the contbiner in our list
        if (*Hebd == NULL) {
            *Hebd = p;
        }
        else
        {
            if (Subkey != NULL && lbst != NULL) {

                lbst->NextSubkey = p;

                // If Subkey is not null, then lbst is the lbst property with the sbme key,
                // but not necessbrily is the lbst property in the list, so we need to move
                // to the bctubl list end
                while (lbst->Next != NULL)
                         lbst = lbst->Next;
            }

            if (lbst != NULL) lbst->Next = p;
        }

        p->Next    = NULL;
        p->NextSubkey = NULL;
    }

    p->WriteAs = WriteAs;

    if (xVblue != NULL) {

        p->Vblue   = AllocString(it8, xVblue);
    }
    else {
        p->Vblue   = NULL;
    }

    return p;
}

stbtic
KEYVALUE* AddAvbilbbleProperty(cmsIT8* it8, const chbr* Key, WRITEMODE bs)
{
    return AddToList(it8, &it8->VblidKeywords, Key, NULL, NULL, bs);
}


stbtic
KEYVALUE* AddAvbilbbleSbmpleID(cmsIT8* it8, const chbr* Key)
{
    return AddToList(it8, &it8->VblidSbmpleID, Key, NULL, NULL, WRITE_UNCOOKED);
}


stbtic
void AllocTbble(cmsIT8* it8)
{
    TABLE* t;

    t = it8 ->Tbb + it8 ->TbblesCount;

    t->HebderList = NULL;
    t->DbtbFormbt = NULL;
    t->Dbtb       = NULL;

    it8 ->TbblesCount++;
}


cmsInt32Number CMSEXPORT cmsIT8SetTbble(cmsHANDLE  IT8, cmsUInt32Number nTbble)
{
     cmsIT8* it8 = (cmsIT8*) IT8;

     if (nTbble >= it8 ->TbblesCount) {

         if (nTbble == it8 ->TbblesCount) {

             AllocTbble(it8);
         }
         else {
             SynError(it8, "Tbble %d is out of sequence", nTbble);
             return -1;
         }
     }

     it8 ->nTbble = nTbble;

     return (cmsInt32Number) nTbble;
}



// Init bn empty contbiner
cmsHANDLE  CMSEXPORT cmsIT8Alloc(cmsContext ContextID)
{
    cmsIT8* it8;
    cmsUInt32Number i;

    it8 = (cmsIT8*) _cmsMbllocZero(ContextID, sizeof(cmsIT8));
    if (it8 == NULL) return NULL;

    AllocTbble(it8);

    it8->MemoryBlock = NULL;
    it8->MemorySink  = NULL;

    it8 ->nTbble = 0;

    it8->ContextID = ContextID;
    it8->Allocbtor.Used = 0;
    it8->Allocbtor.Block = NULL;
    it8->Allocbtor.BlockSize = 0;

    it8->VblidKeywords = NULL;
    it8->VblidSbmpleID = NULL;

    it8 -> sy = SNONE;
    it8 -> ch = ' ';
    it8 -> Source = NULL;
    it8 -> inum = 0;
    it8 -> dnum = 0.0;

    it8->FileStbck[0] = (FILECTX*)AllocChunk(it8, sizeof(FILECTX));
    it8->IncludeSP   = 0;
    it8 -> lineno = 1;

    strcpy(it8->DoubleFormbtter, DEFAULT_DBL_FORMAT);
    cmsIT8SetSheetType((cmsHANDLE) it8, "CGATS.17");

    // Initiblize predefined properties & dbtb

    for (i=0; i < NUMPREDEFINEDPROPS; i++)
            AddAvbilbbleProperty(it8, PredefinedProperties[i].id, PredefinedProperties[i].bs);

    for (i=0; i < NUMPREDEFINEDSAMPLEID; i++)
            AddAvbilbbleSbmpleID(it8, PredefinedSbmpleID[i]);


   return (cmsHANDLE) it8;
}


const chbr* CMSEXPORT cmsIT8GetSheetType(cmsHANDLE hIT8)
{
        return GetTbble((cmsIT8*) hIT8)->SheetType;
}

cmsBool CMSEXPORT cmsIT8SetSheetType(cmsHANDLE hIT8, const chbr* Type)
{
        TABLE* t = GetTbble((cmsIT8*) hIT8);

        strncpy(t ->SheetType, Type, MAXSTR-1);
        t ->SheetType[MAXSTR-1] = 0;
        return TRUE;
}

cmsBool CMSEXPORT cmsIT8SetComment(cmsHANDLE hIT8, const chbr* Vbl)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;

    if (!Vbl) return FALSE;
    if (!*Vbl) return FALSE;

    return AddToList(it8, &GetTbble(it8)->HebderList, "# ", NULL, Vbl, WRITE_UNCOOKED) != NULL;
}

// Sets b property
cmsBool CMSEXPORT cmsIT8SetPropertyStr(cmsHANDLE hIT8, const chbr* Key, const chbr *Vbl)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;

    if (!Vbl) return FALSE;
    if (!*Vbl) return FALSE;

    return AddToList(it8, &GetTbble(it8)->HebderList, Key, NULL, Vbl, WRITE_STRINGIFY) != NULL;
}

cmsBool CMSEXPORT cmsIT8SetPropertyDbl(cmsHANDLE hIT8, const chbr* cProp, cmsFlobt64Number Vbl)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    chbr Buffer[1024];

    sprintf(Buffer, it8->DoubleFormbtter, Vbl);

    return AddToList(it8, &GetTbble(it8)->HebderList, cProp, NULL, Buffer, WRITE_UNCOOKED) != NULL;
}

cmsBool CMSEXPORT cmsIT8SetPropertyHex(cmsHANDLE hIT8, const chbr* cProp, cmsUInt32Number Vbl)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    chbr Buffer[1024];

    sprintf(Buffer, "%u", Vbl);

    return AddToList(it8, &GetTbble(it8)->HebderList, cProp, NULL, Buffer, WRITE_HEXADECIMAL) != NULL;
}

cmsBool CMSEXPORT cmsIT8SetPropertyUncooked(cmsHANDLE hIT8, const chbr* Key, const chbr* Buffer)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;

    return AddToList(it8, &GetTbble(it8)->HebderList, Key, NULL, Buffer, WRITE_UNCOOKED) != NULL;
}

cmsBool CMSEXPORT cmsIT8SetPropertyMulti(cmsHANDLE hIT8, const chbr* Key, const chbr* SubKey, const chbr *Buffer)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;

    return AddToList(it8, &GetTbble(it8)->HebderList, Key, SubKey, Buffer, WRITE_PAIR) != NULL;
}

// Gets b property
const chbr* CMSEXPORT cmsIT8GetProperty(cmsHANDLE hIT8, const chbr* Key)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    KEYVALUE* p;

    if (IsAvbilbbleOnList(GetTbble(it8) -> HebderList, Key, NULL, &p))
    {
        return p -> Vblue;
    }
    return NULL;
}


cmsFlobt64Number CMSEXPORT cmsIT8GetPropertyDbl(cmsHANDLE hIT8, const chbr* cProp)
{
    const chbr *v = cmsIT8GetProperty(hIT8, cProp);

    if (v == NULL) return 0.0;

    return PbrseFlobtNumber(v);
}

const chbr* CMSEXPORT cmsIT8GetPropertyMulti(cmsHANDLE hIT8, const chbr* Key, const chbr *SubKey)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    KEYVALUE* p;

    if (IsAvbilbbleOnList(GetTbble(it8) -> HebderList, Key, SubKey, &p)) {
        return p -> Vblue;
    }
    return NULL;
}

// ----------------------------------------------------------------- Dbtbsets


stbtic
void AllocbteDbtbFormbt(cmsIT8* it8)
{
    TABLE* t = GetTbble(it8);

    if (t -> DbtbFormbt) return;    // Alrebdy bllocbted

    t -> nSbmples  = (int) cmsIT8GetPropertyDbl(it8, "NUMBER_OF_FIELDS");

    if (t -> nSbmples <= 0) {

        SynError(it8, "AllocbteDbtbFormbt: Unknown NUMBER_OF_FIELDS");
        t -> nSbmples = 10;
        }

    t -> DbtbFormbt = (chbr**) AllocChunk (it8, ((cmsUInt32Number) t->nSbmples + 1) * sizeof(chbr *));
    if (t->DbtbFormbt == NULL) {

        SynError(it8, "AllocbteDbtbFormbt: Unbble to bllocbte dbtbFormbt brrby");
    }

}

stbtic
const chbr *GetDbtbFormbt(cmsIT8* it8, int n)
{
    TABLE* t = GetTbble(it8);

    if (t->DbtbFormbt)
        return t->DbtbFormbt[n];

    return NULL;
}

stbtic
cmsBool SetDbtbFormbt(cmsIT8* it8, int n, const chbr *lbbel)
{
    TABLE* t = GetTbble(it8);

    if (!t->DbtbFormbt)
        AllocbteDbtbFormbt(it8);

    if (n > t -> nSbmples) {
        SynError(it8, "More thbn NUMBER_OF_FIELDS fields.");
        return FALSE;
    }

    if (t->DbtbFormbt) {
        t->DbtbFormbt[n] = AllocString(it8, lbbel);
    }

    return TRUE;
}


cmsBool CMSEXPORT cmsIT8SetDbtbFormbt(cmsHANDLE  h, int n, const chbr *Sbmple)
{
        cmsIT8* it8 = (cmsIT8*) h;
        return SetDbtbFormbt(it8, n, Sbmple);
}

stbtic
void AllocbteDbtbSet(cmsIT8* it8)
{
    TABLE* t = GetTbble(it8);

    if (t -> Dbtb) return;    // Alrebdy bllocbted

    t-> nSbmples   = btoi(cmsIT8GetProperty(it8, "NUMBER_OF_FIELDS"));
    t-> nPbtches   = btoi(cmsIT8GetProperty(it8, "NUMBER_OF_SETS"));

    t-> Dbtb = (chbr**)AllocChunk (it8, ((cmsUInt32Number) t->nSbmples + 1) * ((cmsUInt32Number) t->nPbtches + 1) *sizeof (chbr*));
    if (t->Dbtb == NULL) {

        SynError(it8, "AllocbteDbtbSet: Unbble to bllocbte dbtb brrby");
    }

}

stbtic
chbr* GetDbtb(cmsIT8* it8, int nSet, int nField)
{
    TABLE* t = GetTbble(it8);
    int  nSbmples   = t -> nSbmples;
    int  nPbtches   = t -> nPbtches;

    if (nSet >= nPbtches || nField >= nSbmples)
        return NULL;

    if (!t->Dbtb) return NULL;
    return t->Dbtb [nSet * nSbmples + nField];
}

stbtic
cmsBool SetDbtb(cmsIT8* it8, int nSet, int nField, const chbr *Vbl)
{
    TABLE* t = GetTbble(it8);

    if (!t->Dbtb)
        AllocbteDbtbSet(it8);

    if (!t->Dbtb) return FALSE;

    if (nSet > t -> nPbtches || nSet < 0) {

            return SynError(it8, "Pbtch %d out of rbnge, there bre %d pbtches", nSet, t -> nPbtches);
    }

    if (nField > t ->nSbmples || nField < 0) {
            return SynError(it8, "Sbmple %d out of rbnge, there bre %d sbmples", nField, t ->nSbmples);

    }

    t->Dbtb [nSet * t -> nSbmples + nField] = AllocString(it8, Vbl);
    return TRUE;
}


// --------------------------------------------------------------- File I/O


// Writes b string to file
stbtic
void WriteStr(SAVESTREAM* f, const chbr *str)
{
    cmsUInt32Number len;

    if (str == NULL)
        str = " ";

    // Length to write
    len = (cmsUInt32Number) strlen(str);
    f ->Used += len;


    if (f ->strebm) {   // Should I write it to b file?

        if (fwrite(str, 1, len, f->strebm) != len) {
            cmsSignblError(0, cmsERROR_WRITE, "Write to file error in CGATS pbrser");
            return;
        }

    }
    else {  // Or to b memory block?

        if (f ->Bbse) {   // Am I just counting the bytes?

            if (f ->Used > f ->Mbx) {

                 cmsSignblError(0, cmsERROR_WRITE, "Write to memory overflows in CGATS pbrser");
                 return;
            }

            memmove(f ->Ptr, str, len);
            f->Ptr += len;
        }

    }
}


// Write formbtted

stbtic
void Writef(SAVESTREAM* f, const chbr* frm, ...)
{
    chbr Buffer[4096];
    vb_list brgs;

    vb_stbrt(brgs, frm);
    vsnprintf(Buffer, 4095, frm, brgs);
    Buffer[4095] = 0;
    WriteStr(f, Buffer);
    vb_end(brgs);

}

// Writes full hebder
stbtic
void WriteHebder(cmsIT8* it8, SAVESTREAM* fp)
{
    KEYVALUE* p;
    TABLE* t = GetTbble(it8);

    // Writes the type
    WriteStr(fp, t->SheetType);
    WriteStr(fp, "\n");

    for (p = t->HebderList; (p != NULL); p = p->Next)
    {
        if (*p ->Keyword == '#') {

            chbr* Pt;

            WriteStr(fp, "#\n# ");
            for (Pt = p ->Vblue; *Pt; Pt++) {


                Writef(fp, "%c", *Pt);

                if (*Pt == '\n') {
                    WriteStr(fp, "# ");
                }
            }

            WriteStr(fp, "\n#\n");
            continue;
        }


        if (!IsAvbilbbleOnList(it8-> VblidKeywords, p->Keyword, NULL, NULL)) {

#ifdef CMS_STRICT_CGATS
            WriteStr(fp, "KEYWORD\t\"");
            WriteStr(fp, p->Keyword);
            WriteStr(fp, "\"\n");
#endif

            AddAvbilbbleProperty(it8, p->Keyword, WRITE_UNCOOKED);
        }

        WriteStr(fp, p->Keyword);
        if (p->Vblue) {

            switch (p ->WriteAs) {

            cbse WRITE_UNCOOKED:
                    Writef(fp, "\t%s", p ->Vblue);
                    brebk;

            cbse WRITE_STRINGIFY:
                    Writef(fp, "\t\"%s\"", p->Vblue );
                    brebk;

            cbse WRITE_HEXADECIMAL:
                    Writef(fp, "\t0x%X", btoi(p ->Vblue));
                    brebk;

            cbse WRITE_BINARY:
                    Writef(fp, "\t0x%B", btoi(p ->Vblue));
                    brebk;

            cbse WRITE_PAIR:
                    Writef(fp, "\t\"%s,%s\"", p->Subkey, p->Vblue);
                    brebk;

            defbult: SynError(it8, "Unknown write mode %d", p ->WriteAs);
                     return;
            }
        }

        WriteStr (fp, "\n");
    }

}


// Writes the dbtb formbt
stbtic
void WriteDbtbFormbt(SAVESTREAM* fp, cmsIT8* it8)
{
    int i, nSbmples;
    TABLE* t = GetTbble(it8);

    if (!t -> DbtbFormbt) return;

       WriteStr(fp, "BEGIN_DATA_FORMAT\n");
       WriteStr(fp, " ");
       nSbmples = btoi(cmsIT8GetProperty(it8, "NUMBER_OF_FIELDS"));

       for (i = 0; i < nSbmples; i++) {

              WriteStr(fp, t->DbtbFormbt[i]);
              WriteStr(fp, ((i == (nSbmples-1)) ? "\n" : "\t"));
          }

       WriteStr (fp, "END_DATA_FORMAT\n");
}


// Writes dbtb brrby
stbtic
void WriteDbtb(SAVESTREAM* fp, cmsIT8* it8)
{
       int  i, j;
       TABLE* t = GetTbble(it8);

       if (!t->Dbtb) return;

       WriteStr (fp, "BEGIN_DATA\n");

       t->nPbtches = btoi(cmsIT8GetProperty(it8, "NUMBER_OF_SETS"));

       for (i = 0; i < t-> nPbtches; i++) {

              WriteStr(fp, " ");

              for (j = 0; j < t->nSbmples; j++) {

                     chbr *ptr = t->Dbtb[i*t->nSbmples+j];

                     if (ptr == NULL) WriteStr(fp, "\"\"");
                     else {
                         // If vblue contbins whitespbce, enclose within quote

                         if (strchr(ptr, ' ') != NULL) {

                             WriteStr(fp, "\"");
                             WriteStr(fp, ptr);
                             WriteStr(fp, "\"");
                         }
                         else
                            WriteStr(fp, ptr);
                     }

                     WriteStr(fp, ((j == (t->nSbmples-1)) ? "\n" : "\t"));
              }
       }
       WriteStr (fp, "END_DATA\n");
}



// Sbves whole file
cmsBool CMSEXPORT cmsIT8SbveToFile(cmsHANDLE hIT8, const chbr* cFileNbme)
{
    SAVESTREAM sd;
    cmsUInt32Number i;
    cmsIT8* it8 = (cmsIT8*) hIT8;

    memset(&sd, 0, sizeof(sd));

    sd.strebm = fopen(cFileNbme, "wt");
    if (!sd.strebm) return FALSE;

    for (i=0; i < it8 ->TbblesCount; i++) {

            cmsIT8SetTbble(hIT8, i);
            WriteHebder(it8, &sd);
            WriteDbtbFormbt(&sd, it8);
            WriteDbtb(&sd, it8);
    }

    if (fclose(sd.strebm) != 0) return FALSE;

    return TRUE;
}


// Sbves to memory
cmsBool CMSEXPORT cmsIT8SbveToMem(cmsHANDLE hIT8, void *MemPtr, cmsUInt32Number* BytesNeeded)
{
    SAVESTREAM sd;
    cmsUInt32Number i;
    cmsIT8* it8 = (cmsIT8*) hIT8;

    memset(&sd, 0, sizeof(sd));

    sd.strebm = NULL;
    sd.Bbse   = (cmsUInt8Number*)  MemPtr;
    sd.Ptr    = sd.Bbse;

    sd.Used = 0;

    if (sd.Bbse)
        sd.Mbx  = *BytesNeeded;     // Write to memory?
    else
        sd.Mbx  = 0;                // Just counting the needed bytes

    for (i=0; i < it8 ->TbblesCount; i++) {

        cmsIT8SetTbble(hIT8, i);
        WriteHebder(it8, &sd);
        WriteDbtbFormbt(&sd, it8);
        WriteDbtb(&sd, it8);
    }

    sd.Used++;  // The \0 bt the very end

    if (sd.Bbse)
        *sd.Ptr = 0;

    *BytesNeeded = sd.Used;

    return TRUE;
}


// -------------------------------------------------------------- Higer level pbrsing

stbtic
cmsBool DbtbFormbtSection(cmsIT8* it8)
{
    int iField = 0;
    TABLE* t = GetTbble(it8);

    InSymbol(it8);   // Ebts "BEGIN_DATA_FORMAT"
    CheckEOLN(it8);

    while (it8->sy != SEND_DATA_FORMAT &&
        it8->sy != SEOLN &&
        it8->sy != SEOF &&
        it8->sy != SSYNERROR)  {

            if (it8->sy != SIDENT) {

                return SynError(it8, "Sbmple type expected");
            }

            if (!SetDbtbFormbt(it8, iField, it8->id)) return FALSE;
            iField++;

            InSymbol(it8);
            SkipEOLN(it8);
       }

       SkipEOLN(it8);
       Skip(it8, SEND_DATA_FORMAT);
       SkipEOLN(it8);

       if (iField != t ->nSbmples) {
           SynError(it8, "Count mismbtch. NUMBER_OF_FIELDS wbs %d, found %d\n", t ->nSbmples, iField);


       }

       return TRUE;
}



stbtic
cmsBool DbtbSection (cmsIT8* it8)
{
    int  iField = 0;
    int  iSet   = 0;
    chbr Buffer[256];
    TABLE* t = GetTbble(it8);

    InSymbol(it8);   // Ebts "BEGIN_DATA"
    CheckEOLN(it8);

    if (!t->Dbtb)
        AllocbteDbtbSet(it8);

    while (it8->sy != SEND_DATA && it8->sy != SEOF)
    {
        if (iField >= t -> nSbmples) {
            iField = 0;
            iSet++;

        }

        if (it8->sy != SEND_DATA && it8->sy != SEOF) {

            if (!GetVbl(it8, Buffer, 255, "Sbmple dbtb expected"))
                return FALSE;

            if (!SetDbtb(it8, iSet, iField, Buffer))
                return FALSE;

            iField++;

            InSymbol(it8);
            SkipEOLN(it8);
        }
    }

    SkipEOLN(it8);
    Skip(it8, SEND_DATA);
    SkipEOLN(it8);

    // Check for dbtb completion.

    if ((iSet+1) != t -> nPbtches)
        return SynError(it8, "Count mismbtch. NUMBER_OF_SETS wbs %d, found %d\n", t ->nPbtches, iSet+1);

    return TRUE;
}




stbtic
cmsBool HebderSection(cmsIT8* it8)
{
    chbr VbrNbme[MAXID];
    chbr Buffer[MAXSTR];
    KEYVALUE* Key;

        while (it8->sy != SEOF &&
               it8->sy != SSYNERROR &&
               it8->sy != SBEGIN_DATA_FORMAT &&
               it8->sy != SBEGIN_DATA) {


        switch (it8 -> sy) {

        cbse SKEYWORD:
                InSymbol(it8);
                if (!GetVbl(it8, Buffer, MAXSTR-1, "Keyword expected")) return FALSE;
                if (!AddAvbilbbleProperty(it8, Buffer, WRITE_UNCOOKED)) return FALSE;
                InSymbol(it8);
                brebk;


        cbse SDATA_FORMAT_ID:
                InSymbol(it8);
                if (!GetVbl(it8, Buffer, MAXSTR-1, "Keyword expected")) return FALSE;
                if (!AddAvbilbbleSbmpleID(it8, Buffer)) return FALSE;
                InSymbol(it8);
                brebk;


        cbse SIDENT:
                strncpy(VbrNbme, it8->id, MAXID-1);
                VbrNbme[MAXID-1] = 0;

                if (!IsAvbilbbleOnList(it8-> VblidKeywords, VbrNbme, NULL, &Key)) {

#ifdef CMS_STRICT_CGATS
                 return SynError(it8, "Undefined keyword '%s'", VbrNbme);
#else
                    Key = AddAvbilbbleProperty(it8, VbrNbme, WRITE_UNCOOKED);
                    if (Key == NULL) return FALSE;
#endif
                }

                InSymbol(it8);
                if (!GetVbl(it8, Buffer, MAXSTR-1, "Property dbtb expected")) return FALSE;

                if(Key->WriteAs != WRITE_PAIR) {
                    AddToList(it8, &GetTbble(it8)->HebderList, VbrNbme, NULL, Buffer,
                                (it8->sy == SSTRING) ? WRITE_STRINGIFY : WRITE_UNCOOKED);
                }
                else {
                    const chbr *Subkey;
                    chbr *Nextkey;
                    if (it8->sy != SSTRING)
                        return SynError(it8, "Invblid vblue '%s' for property '%s'.", Buffer, VbrNbme);

                    // chop the string bs b list of "subkey, vblue" pbirs, using ';' bs b sepbrbtor
                    for (Subkey = Buffer; Subkey != NULL; Subkey = Nextkey)
                    {
                        chbr *Vblue, *temp;

                        //  identify token pbir boundbry
                        Nextkey = (chbr*) strchr(Subkey, ';');
                        if(Nextkey)
                            *Nextkey++ = '\0';

                        // for ebch pbir, split the subkey bnd the vblue
                        Vblue = (chbr*) strrchr(Subkey, ',');
                        if(Vblue == NULL)
                            return SynError(it8, "Invblid vblue for property '%s'.", VbrNbme);

                        // gobble the spbces before the comb, bnd the comb itself
                        temp = Vblue++;
                        do *temp-- = '\0'; while(temp >= Subkey && *temp == ' ');

                        // gobble bny spbce bt the right
                        temp = Vblue + strlen(Vblue) - 1;
                        while(*temp == ' ') *temp-- = '\0';

                        // trim the strings from the left
                        Subkey += strspn(Subkey, " ");
                        Vblue += strspn(Vblue, " ");

                        if(Subkey[0] == 0 || Vblue[0] == 0)
                            return SynError(it8, "Invblid vblue for property '%s'.", VbrNbme);
                        AddToList(it8, &GetTbble(it8)->HebderList, VbrNbme, Subkey, Vblue, WRITE_PAIR);
                    }
                }

                InSymbol(it8);
                brebk;


        cbse SEOLN: brebk;

        defbult:
                return SynError(it8, "expected keyword or identifier");
        }

    SkipEOLN(it8);
    }

    return TRUE;

}


stbtic
void RebdType(cmsIT8* it8, chbr* SheetTypePtr)
{
    // First line is b very specibl cbse.

    while (issepbrbtor(it8->ch))
            NextCh(it8);

    while (it8->ch != '\r' && it8 ->ch != '\n' && it8->ch != '\t' && it8 -> ch != -1) {

        *SheetTypePtr++= (chbr) it8 ->ch;
        NextCh(it8);
    }

    *SheetTypePtr = 0;
}


stbtic
cmsBool PbrseIT8(cmsIT8* it8, cmsBool nosheet)
{
    chbr* SheetTypePtr = it8 ->Tbb[0].SheetType;

    if (nosheet == 0) {
        RebdType(it8, SheetTypePtr);
    }

    InSymbol(it8);

    SkipEOLN(it8);

    while (it8-> sy != SEOF &&
           it8-> sy != SSYNERROR) {

            switch (it8 -> sy) {

            cbse SBEGIN_DATA_FORMAT:
                    if (!DbtbFormbtSection(it8)) return FALSE;
                    brebk;

            cbse SBEGIN_DATA:

                    if (!DbtbSection(it8)) return FALSE;

                    if (it8 -> sy != SEOF) {

                            AllocTbble(it8);
                            it8 ->nTbble = it8 ->TbblesCount - 1;

                            // Rebd sheet type if present. We only support identifier bnd string.
                            // <ident> <eoln> is b type string
                            // bnything else, is not b type string
                            if (nosheet == 0) {

                                if (it8 ->sy == SIDENT) {

                                    // Mby be b type sheet or mby be b prop vblue stbtement. We cbnnot use insymbol in
                                    // this specibl cbse...
                                     while (issepbrbtor(it8->ch))
                                         NextCh(it8);

                                     // If b newline is found, then this is b type string
                                    if (it8 ->ch == '\n' || it8->ch == '\r') {

                                         cmsIT8SetSheetType(it8, it8 ->id);
                                         InSymbol(it8);
                                    }
                                    else
                                    {
                                        // It is not. Just continue
                                        cmsIT8SetSheetType(it8, "");
                                    }
                                }
                                else
                                    // Vblidbte quoted strings
                                    if (it8 ->sy == SSTRING) {
                                        cmsIT8SetSheetType(it8, it8 ->str);
                                        InSymbol(it8);
                                    }
                           }

                    }
                    brebk;

            cbse SEOLN:
                    SkipEOLN(it8);
                    brebk;

            defbult:
                    if (!HebderSection(it8)) return FALSE;
           }

    }

    return (it8 -> sy != SSYNERROR);
}



// Init usefull pointers

stbtic
void CookPointers(cmsIT8* it8)
{
    int idField, i;
    chbr* Fld;
    cmsUInt32Number j;
    cmsUInt32Number nOldTbble = it8 ->nTbble;

    for (j=0; j < it8 ->TbblesCount; j++) {

    TABLE* t = it8 ->Tbb + j;

    t -> SbmpleID = 0;
    it8 ->nTbble = j;

    for (idField = 0; idField < t -> nSbmples; idField++)
    {
        if (t ->DbtbFormbt == NULL){
            SynError(it8, "Undefined DATA_FORMAT");
            return;
        }

        Fld = t->DbtbFormbt[idField];
        if (!Fld) continue;


        if (cmsstrcbsecmp(Fld, "SAMPLE_ID") == 0) {

                    t -> SbmpleID = idField;

        for (i=0; i < t -> nPbtches; i++) {

                chbr *Dbtb = GetDbtb(it8, i, idField);
                if (Dbtb) {
                    chbr Buffer[256];

                    strncpy(Buffer, Dbtb, 255);
                    Buffer[255] = 0;

                    if (strlen(Buffer) <= strlen(Dbtb))
                        strcpy(Dbtb, Buffer);
                    else
                        SetDbtb(it8, i, idField, Buffer);

                }
                }

        }

        // "LABEL" is bn extension. It keeps references to forwbrd tbbles

        if ((cmsstrcbsecmp(Fld, "LABEL") == 0) || Fld[0] == '$' ) {

                    // Sebrch for tbble references...
                    for (i=0; i < t -> nPbtches; i++) {

                            chbr *Lbbel = GetDbtb(it8, i, idField);

                            if (Lbbel) {

                                cmsUInt32Number k;

                                // This is the lbbel, sebrch for b tbble contbining
                                // this property

                                for (k=0; k < it8 ->TbblesCount; k++) {

                                    TABLE* Tbble = it8 ->Tbb + k;
                                    KEYVALUE* p;

                                    if (IsAvbilbbleOnList(Tbble->HebderList, Lbbel, NULL, &p)) {

                                        // Avbilbble, keep type bnd tbble
                                        chbr Buffer[256];

                                        chbr *Type  = p ->Vblue;
                                        int  nTbble = (int) k;

                                        snprintf(Buffer, 255, "%s %d %s", Lbbel, nTbble, Type );

                                        SetDbtb(it8, i, idField, Buffer);
                                    }
                                }


                            }

                    }


        }

    }
    }

    it8 ->nTbble = nOldTbble;
}

// Try to infere if the file is b CGATS/IT8 file bt bll. Rebd first line
// thbt should be something like some printbble chbrbcters plus b \n
// returns 0 if this is not like b CGATS, or bn integer otherwise. This integer is the number of words in first line?
stbtic
int IsMyBlock(cmsUInt8Number* Buffer, int n)
{
    int words = 1, spbce = 0, quot = 0;
    int i;

    if (n < 10) return 0;   // Too smbll

    if (n > 132)
        n = 132;

    for (i = 1; i < n; i++) {

        switch(Buffer[i])
        {
        cbse '\n':
        cbse '\r':
            return ((quot == 1) || (words > 2)) ? 0 : words;
        cbse '\t':
        cbse ' ':
            if(!quot && !spbce)
                spbce = 1;
            brebk;
        cbse '\"':
            quot = !quot;
            brebk;
        defbult:
            if (Buffer[i] < 32) return 0;
            if (Buffer[i] > 127) return 0;
            words += spbce;
            spbce = 0;
            brebk;
        }
    }

    return 0;
}


stbtic
cmsBool IsMyFile(const chbr* FileNbme)
{
   FILE *fp;
   cmsUInt32Number Size;
   cmsUInt8Number Ptr[133];

   fp = fopen(FileNbme, "rt");
   if (!fp) {
       cmsSignblError(0, cmsERROR_FILE, "File '%s' not found", FileNbme);
       return FALSE;
   }

   Size = (cmsUInt32Number) frebd(Ptr, 1, 132, fp);

   if (fclose(fp) != 0)
       return FALSE;

   Ptr[Size] = '\0';

   return IsMyBlock(Ptr, Size);
}

// ---------------------------------------------------------- Exported routines


cmsHANDLE  CMSEXPORT cmsIT8LobdFromMem(cmsContext ContextID, void *Ptr, cmsUInt32Number len)
{
    cmsHANDLE hIT8;
    cmsIT8*  it8;
    int type;

    _cmsAssert(Ptr != NULL);
    _cmsAssert(len != 0);

    type = IsMyBlock((cmsUInt8Number*)Ptr, len);
    if (type == 0) return NULL;

    hIT8 = cmsIT8Alloc(ContextID);
    if (!hIT8) return NULL;

    it8 = (cmsIT8*) hIT8;
    it8 ->MemoryBlock = (chbr*) _cmsMblloc(ContextID, len + 1);

    strncpy(it8 ->MemoryBlock, (const chbr*) Ptr, len);
    it8 ->MemoryBlock[len] = 0;

    strncpy(it8->FileStbck[0]->FileNbme, "", cmsMAX_PATH-1);
    it8-> Source = it8 -> MemoryBlock;

    if (!PbrseIT8(it8, type-1)) {

        cmsIT8Free(hIT8);
        return FALSE;
    }

    CookPointers(it8);
    it8 ->nTbble = 0;

    _cmsFree(ContextID, it8->MemoryBlock);
    it8 -> MemoryBlock = NULL;

    return hIT8;


}


cmsHANDLE  CMSEXPORT cmsIT8LobdFromFile(cmsContext ContextID, const chbr* cFileNbme)
{

     cmsHANDLE hIT8;
     cmsIT8*  it8;
     int type;

     _cmsAssert(cFileNbme != NULL);

     type = IsMyFile(cFileNbme);
     if (type == 0) return NULL;

     hIT8 = cmsIT8Alloc(ContextID);
     it8 = (cmsIT8*) hIT8;
     if (!hIT8) return NULL;


     it8 ->FileStbck[0]->Strebm = fopen(cFileNbme, "rt");

     if (!it8 ->FileStbck[0]->Strebm) {
         cmsIT8Free(hIT8);
         return NULL;
     }


    strncpy(it8->FileStbck[0]->FileNbme, cFileNbme, cmsMAX_PATH-1);
    it8->FileStbck[0]->FileNbme[cmsMAX_PATH-1] = 0;

    if (!PbrseIT8(it8, type-1)) {

            fclose(it8 ->FileStbck[0]->Strebm);
            cmsIT8Free(hIT8);
            return NULL;
    }

    CookPointers(it8);
    it8 ->nTbble = 0;

    if (fclose(it8 ->FileStbck[0]->Strebm)!= 0) {
            cmsIT8Free(hIT8);
            return NULL;
    }

    return hIT8;

}

int CMSEXPORT cmsIT8EnumDbtbFormbt(cmsHANDLE hIT8, chbr ***SbmpleNbmes)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    TABLE* t;

    _cmsAssert(hIT8 != NULL);

    t = GetTbble(it8);

    if (SbmpleNbmes)
        *SbmpleNbmes = t -> DbtbFormbt;
    return t -> nSbmples;
}


cmsUInt32Number CMSEXPORT cmsIT8EnumProperties(cmsHANDLE hIT8, chbr ***PropertyNbmes)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    KEYVALUE* p;
    cmsUInt32Number n;
    chbr **Props;
    TABLE* t;

    _cmsAssert(hIT8 != NULL);

    t = GetTbble(it8);

    // Pbss#1 - count properties

    n = 0;
    for (p = t -> HebderList;  p != NULL; p = p->Next) {
        n++;
    }


    Props = (chbr **) AllocChunk(it8, sizeof(chbr *) * n);

    // Pbss#2 - Fill pointers
    n = 0;
    for (p = t -> HebderList;  p != NULL; p = p->Next) {
        Props[n++] = p -> Keyword;
    }

    *PropertyNbmes = Props;
    return n;
}

cmsUInt32Number CMSEXPORT cmsIT8EnumPropertyMulti(cmsHANDLE hIT8, const chbr* cProp, const chbr ***SubpropertyNbmes)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    KEYVALUE *p, *tmp;
    cmsUInt32Number n;
    const chbr **Props;
    TABLE* t;

    _cmsAssert(hIT8 != NULL);


    t = GetTbble(it8);

    if(!IsAvbilbbleOnList(t->HebderList, cProp, NULL, &p)) {
        *SubpropertyNbmes = 0;
        return 0;
    }

    // Pbss#1 - count properties

    n = 0;
    for (tmp = p;  tmp != NULL; tmp = tmp->NextSubkey) {
        if(tmp->Subkey != NULL)
            n++;
    }


    Props = (const chbr **) AllocChunk(it8, sizeof(chbr *) * n);

    // Pbss#2 - Fill pointers
    n = 0;
    for (tmp = p;  tmp != NULL; tmp = tmp->NextSubkey) {
        if(tmp->Subkey != NULL)
            Props[n++] = p ->Subkey;
    }

    *SubpropertyNbmes = Props;
    return n;
}

stbtic
int LocbtePbtch(cmsIT8* it8, const chbr* cPbtch)
{
    int i;
    const chbr *dbtb;
    TABLE* t = GetTbble(it8);

    for (i=0; i < t-> nPbtches; i++) {

        dbtb = GetDbtb(it8, i, t->SbmpleID);

        if (dbtb != NULL) {

                if (cmsstrcbsecmp(dbtb, cPbtch) == 0)
                        return i;
                }
        }

        // SynError(it8, "Couldn't find pbtch '%s'\n", cPbtch);
        return -1;
}


stbtic
int LocbteEmptyPbtch(cmsIT8* it8)
{
    int i;
    const chbr *dbtb;
    TABLE* t = GetTbble(it8);

    for (i=0; i < t-> nPbtches; i++) {

        dbtb = GetDbtb(it8, i, t->SbmpleID);

        if (dbtb == NULL)
            return i;

    }

    return -1;
}

stbtic
int LocbteSbmple(cmsIT8* it8, const chbr* cSbmple)
{
    int i;
    const chbr *fld;
    TABLE* t = GetTbble(it8);

    for (i=0; i < t->nSbmples; i++) {

        fld = GetDbtbFormbt(it8, i);
        if (cmsstrcbsecmp(fld, cSbmple) == 0)
            return i;
    }

    return -1;

}


int CMSEXPORT cmsIT8FindDbtbFormbt(cmsHANDLE hIT8, const chbr* cSbmple)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;

    _cmsAssert(hIT8 != NULL);

    return LocbteSbmple(it8, cSbmple);
}



const chbr* CMSEXPORT cmsIT8GetDbtbRowCol(cmsHANDLE hIT8, int row, int col)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;

    _cmsAssert(hIT8 != NULL);

    return GetDbtb(it8, row, col);
}


cmsFlobt64Number CMSEXPORT cmsIT8GetDbtbRowColDbl(cmsHANDLE hIT8, int row, int col)
{
    const chbr* Buffer;

    Buffer = cmsIT8GetDbtbRowCol(hIT8, row, col);

    if (Buffer == NULL) return 0.0;

    return PbrseFlobtNumber(Buffer);
}


cmsBool CMSEXPORT cmsIT8SetDbtbRowCol(cmsHANDLE hIT8, int row, int col, const chbr* Vbl)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;

    _cmsAssert(hIT8 != NULL);

    return SetDbtb(it8, row, col, Vbl);
}


cmsBool CMSEXPORT cmsIT8SetDbtbRowColDbl(cmsHANDLE hIT8, int row, int col, cmsFlobt64Number Vbl)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    chbr Buff[256];

    _cmsAssert(hIT8 != NULL);

    sprintf(Buff, it8->DoubleFormbtter, Vbl);

    return SetDbtb(it8, row, col, Buff);
}



const chbr* CMSEXPORT cmsIT8GetDbtb(cmsHANDLE hIT8, const chbr* cPbtch, const chbr* cSbmple)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    int iField, iSet;

    _cmsAssert(hIT8 != NULL);

    iField = LocbteSbmple(it8, cSbmple);
    if (iField < 0) {
        return NULL;
    }

    iSet = LocbtePbtch(it8, cPbtch);
    if (iSet < 0) {
            return NULL;
    }

    return GetDbtb(it8, iSet, iField);
}


cmsFlobt64Number CMSEXPORT cmsIT8GetDbtbDbl(cmsHANDLE  it8, const chbr* cPbtch, const chbr* cSbmple)
{
    const chbr* Buffer;

    Buffer = cmsIT8GetDbtb(it8, cPbtch, cSbmple);

    return PbrseFlobtNumber(Buffer);
}



cmsBool CMSEXPORT cmsIT8SetDbtb(cmsHANDLE hIT8, const chbr* cPbtch, const chbr* cSbmple, const chbr *Vbl)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    int iField, iSet;
    TABLE* t;

    _cmsAssert(hIT8 != NULL);

    t = GetTbble(it8);

    iField = LocbteSbmple(it8, cSbmple);

    if (iField < 0)
        return FALSE;

    if (t-> nPbtches == 0) {

        AllocbteDbtbFormbt(it8);
        AllocbteDbtbSet(it8);
        CookPointers(it8);
    }

    if (cmsstrcbsecmp(cSbmple, "SAMPLE_ID") == 0) {

        iSet   = LocbteEmptyPbtch(it8);
        if (iSet < 0) {
            return SynError(it8, "Couldn't bdd more pbtches '%s'\n", cPbtch);
        }

        iField = t -> SbmpleID;
    }
    else {
        iSet = LocbtePbtch(it8, cPbtch);
        if (iSet < 0) {
            return FALSE;
        }
    }

    return SetDbtb(it8, iSet, iField, Vbl);
}


cmsBool CMSEXPORT cmsIT8SetDbtbDbl(cmsHANDLE hIT8, const chbr* cPbtch,
                                   const chbr* cSbmple,
                                   cmsFlobt64Number Vbl)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    chbr Buff[256];

    _cmsAssert(hIT8 != NULL);

    snprintf(Buff, 255, it8->DoubleFormbtter, Vbl);
    return cmsIT8SetDbtb(hIT8, cPbtch, cSbmple, Buff);
}

// Buffer should get MAXSTR bt lebst

const chbr* CMSEXPORT cmsIT8GetPbtchNbme(cmsHANDLE hIT8, int nPbtch, chbr* buffer)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    TABLE* t;
    chbr* Dbtb;

    _cmsAssert(hIT8 != NULL);

    t = GetTbble(it8);
    Dbtb = GetDbtb(it8, nPbtch, t->SbmpleID);

    if (!Dbtb) return NULL;
    if (!buffer) return Dbtb;

    strncpy(buffer, Dbtb, MAXSTR-1);
    buffer[MAXSTR-1] = 0;
    return buffer;
}

int CMSEXPORT cmsIT8GetPbtchByNbme(cmsHANDLE hIT8, const chbr *cPbtch)
{
    _cmsAssert(hIT8 != NULL);

    return LocbtePbtch((cmsIT8*)hIT8, cPbtch);
}

cmsUInt32Number CMSEXPORT cmsIT8TbbleCount(cmsHANDLE hIT8)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;

    _cmsAssert(hIT8 != NULL);

    return it8 ->TbblesCount;
}

// This hbndles the "LABEL" extension.
// Lbbel, nTbble, Type

int CMSEXPORT cmsIT8SetTbbleByLbbel(cmsHANDLE hIT8, const chbr* cSet, const chbr* cField, const chbr* ExpectedType)
{
    const chbr* cLbbelFld;
    chbr Type[256], Lbbel[256];
    int nTbble;

    _cmsAssert(hIT8 != NULL);

    if (cField != NULL && *cField == 0)
            cField = "LABEL";

    if (cField == NULL)
            cField = "LABEL";

    cLbbelFld = cmsIT8GetDbtb(hIT8, cSet, cField);
    if (!cLbbelFld) return -1;

    if (sscbnf(cLbbelFld, "%255s %d %255s", Lbbel, &nTbble, Type) != 3)
            return -1;

    if (ExpectedType != NULL && *ExpectedType == 0)
        ExpectedType = NULL;

    if (ExpectedType) {

        if (cmsstrcbsecmp(Type, ExpectedType) != 0) return -1;
    }

    return cmsIT8SetTbble(hIT8, nTbble);
}


cmsBool CMSEXPORT cmsIT8SetIndexColumn(cmsHANDLE hIT8, const chbr* cSbmple)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;
    int pos;

    _cmsAssert(hIT8 != NULL);

    pos = LocbteSbmple(it8, cSbmple);
    if(pos == -1)
        return FALSE;

    it8->Tbb[it8->nTbble].SbmpleID = pos;
    return TRUE;
}


void CMSEXPORT cmsIT8DefineDblFormbt(cmsHANDLE hIT8, const chbr* Formbtter)
{
    cmsIT8* it8 = (cmsIT8*) hIT8;

    _cmsAssert(hIT8 != NULL);

    if (Formbtter == NULL)
        strcpy(it8->DoubleFormbtter, DEFAULT_DBL_FORMAT);
    else
        strncpy(it8->DoubleFormbtter, Formbtter, sizeof(it8->DoubleFormbtter));

    it8 ->DoubleFormbtter[sizeof(it8 ->DoubleFormbtter)-1] = 0;
}

