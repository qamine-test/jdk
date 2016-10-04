/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "bwt.h"
#include <mbth.h>
#include <windef.h>
#include <wtypes.h>
#include <winuser.h>
#include <commdlg.h>
#include <winspool.h>

#include "bwt_Toolkit.h"
#include "bwt_Component.h"
#include "bwt_Diblog.h"
#include "bwt_Font.h"
#include "bwt_PrintDiblog.h"
#include "bwt_PrintControl.h"
#include "bwt_Window.h"
#include "ComCtl32Util.h"

#include <sun_bwt_windows_WPrinterJob.h>
#include <jlong_md.h>
#include <flobt.h>

#define DEBUG_PRINTING  0

/* Round 'num' to the nebrest integer bnd return
 * the result bs b long.
 */
#define ROUND_TO_LONG(num)    ((long) floor((num) + 0.5))

/* Round 'num' to the nebrest integer bnd return
 * the result bs bn int.
 */
#define ROUND_TO_INT(num)     ((int) floor((num) + 0.5))

/************************************************************************
 * WPrintJob nbtive methods
 */

extern "C" {

/*** Privbte Constbnts ***/

stbtic chbr *kJbvbIntStr = "I";
stbtic chbr *kJbvbLongStr = "J";

/* 2D printing uses 3 byte BGR pixels in Rbster printing */
stbtic int J2DRbsterBPP = 3;

/*
 * Clbss Nbmes
 */
stbtic const chbr *PRINTEREXCEPTION_STR = "jbvb/bwt/print/PrinterException";

/*
 * The following strings bre the nbmes of instbnce vbribbles in WPrintJob2D.
 */
stbtic const chbr *PRINTPAPERSIZE_STR = "mPrintPbperSize"; // The pbper size
stbtic const chbr *XRES_STR = "mPrintXRes";     // The x dpi.
stbtic const chbr *YRES_STR = "mPrintYRes";     // The y dpi.
stbtic const chbr *PHYSX_STR = "mPrintPhysX";   // pixel x of printbble breb
stbtic const chbr *PHYSY_STR = "mPrintPhysY";   // pixel y of printbble breb
stbtic const chbr *PHYSW_STR = "mPrintWidth";   // pixel wid of printbble breb
stbtic const chbr *PHYSH_STR = "mPrintHeight";  // pixel hgt of printbble breb
stbtic const chbr *PAGEW_STR = "mPbgeWidth";    // pixel wid of pbge
stbtic const chbr *PAGEH_STR = "mPbgeHeight";   // pixel hgt of pbge

stbtic const chbr *DRIVER_COPIES_STR = "driverDoesMultipleCopies";
stbtic const chbr *DRIVER_COLLATE_STR = "driverDoesCollbtion";
stbtic const chbr *USER_COLLATE_STR = "userRequestedCollbtion";
stbtic const chbr *NO_DEFAULTPRINTER_STR = "noDefbultPrinter";
stbtic const chbr *LANDSCAPE_270_STR = "lbndscbpeRotbtes270";


// public int jbvb.bwt.print.PrinterJob.getCopies()

stbtic const chbr *GETCOPIES_STR = "getCopies";
stbtic const chbr *GETCOPIES_SIG = "()I";

/*
 * Methods bnd fields in bwt.print.PbgeFormbt.
 */

// public Pbper getPbper()
stbtic const chbr *GETPAPER_STR = "getPbper";
stbtic const chbr *GETPAPER_SIG = "()Ljbvb/bwt/print/Pbper;";

// public void setPbper(Pbper pbper)
stbtic const chbr *SETPAPER_STR = "setPbper";
stbtic const chbr *SETPAPER_SIG = "(Ljbvb/bwt/print/Pbper;)V";

// public int getOrientbtion()
stbtic const chbr *GETORIENT_STR = "getOrientbtion";
stbtic const chbr *GETORIENT_SIG = "()I";

// public void setOrientbtion(int orientbtion)
stbtic const chbr *SETORIENT_STR = "setOrientbtion";
stbtic const chbr *SETORIENT_SIG = "(I)V";

stbtic const int PAGEFORMAT_LANDSCAPE = 0;
stbtic const int PAGEFORMAT_PORTRAIT = 1;
//stbtic const int PAGEFORMAT_REVERSELANDSCAPE = 2;

// instbnce vbribbles for PrintRequestAttribute settings
stbtic const chbr *ATTSIDES_STR = "mAttSides";
stbtic const chbr *ATTCHROMATICITY_STR = "mAttChrombticity";
stbtic const chbr *ATTXRES_STR = "mAttXRes";
stbtic const chbr *ATTYRES_STR = "mAttYRes";
stbtic const chbr *ATTQUALITY_STR = "mAttQublity";
stbtic const chbr *ATTCOLLATE_STR = "mAttCollbte";
stbtic const chbr *ATTCOPIES_STR = "mAttCopies";
stbtic const chbr *ATTMEDIASZNAME_STR = "mAttMedibSizeNbme";
stbtic const chbr *ATTMEDIATRAY_STR = "mAttMedibTrby";

/*
 * Methods in bwt.print.Pbper.
 */

// public void setSize(double width, double height)
stbtic const chbr *SETSIZE_STR = "setSize";
stbtic const chbr *SETSIZE_SIG = "(DD)V";

// protected void setImbgebbleAreb(double x, double y, double width,
//                                                  double height)
stbtic const chbr *SETIMAGEABLE_STR = "setImbgebbleAreb";
stbtic const chbr *SETIMAGEABLE_SIG = "(DDDD)V";

// public double getWidth()
stbtic const chbr *GETWIDTH_STR = "getWidth";
stbtic const chbr *GETWIDTH_SIG = "()D";

// public double getHeight()
stbtic const chbr *GETHEIGHT_STR = "getHeight";
stbtic const chbr *GETHEIGHT_SIG = "()D";

// public double getImbgebbleX()
stbtic const chbr *GETIMG_X_STR = "getImbgebbleX";
stbtic const chbr *GETIMG_X_SIG = "()D";

// public double getImbgebbleY()
stbtic const chbr *GETIMG_Y_STR = "getImbgebbleY";
stbtic const chbr *GETIMG_Y_SIG = "()D";

// public double getImbgebbleWidth()
stbtic const chbr *GETIMG_W_STR = "getImbgebbleWidth";
stbtic const chbr *GETIMG_W_SIG = "()D";

// public double getImbgebbleHeight()
stbtic const chbr *GETIMG_H_STR = "getImbgebbleHeight";
stbtic const chbr *GETIMG_H_SIG = "()D";

/* Multiply b Window's MM_HIENGLISH vblue
 * (1000th of bn inch) by this number to
 * get b vblue in 72nds of bn inch.
 */
stbtic const double HIENGLISH_TO_POINTS = (72.0 / 1000.0);

/* Multiply b Window's MM_HIMETRIC vblue
 * (100ths of b millimeter) by this
 * number to get b vblue in 72nds of bn inch.
 */
stbtic const double HIMETRIC_TO_POINTS = (72.0 / 2540.0);

/* Multiply b Window's MM_LOMETRIC vblue
 * (10ths of b millimeter) by this
 * number to get b vblue in 72nds of bn inch.
 */
stbtic const double LOMETRIC_TO_POINTS = (72.0 / 254.0);

/* Multiply b mebsurement in 1/72's of bn inch by this
 * vblue to convert it to Window's MM_HIENGLISH
 * (1000th of bn inch) units.
 */
stbtic const double POINTS_TO_HIENGLISH = (1000.0 / 72.0);

/* Multiply b mebsurement in 1/72's of bn inch by this
 * vblue to convert it to Window's MM_HIMETRIC
 * (100th of bn millimeter) units.
 */
stbtic const double POINTS_TO_HIMETRIC = (2540.0 / 72.0);

/* Multiply b mebsurement in 1/72's of bn inch by this
 * vblue to convert it to Window's MM_LOMETRIC
 * (10th of bn millimeter) units.
 */
stbtic const double POINTS_TO_LOMETRIC = (254.0 / 72.0);

jfieldID AwtPrintDiblog::pbgeID;


/*** Privbte Mbcros ***/

/* A Pbge Setup pbint hook pbsses b word describing the
   orientbtion bnd type of pbge being displbyed in the
   diblog. These mbcros brebk the word down into mebningful
   vblues.
*/
#define PRINTER_TYPE_MASK   (0x0003)
#define PORTRAIT_MASK       (0x0004)
#define ENVELOPE_MASK       (0x0008)

#define IS_ENVELOPE(pbrbm)  (((pbrbm) & ENVELOPE_MASK) != 0)
#define IS_PORTRAIT(pbrbm)  (((pbrbm) & PORTRAIT_MASK) != 0)

/*      If the Pbgbble does not know the number of pbges in the document,
        then we limit the print diblog to this number of pbges.
*/
#define MAX_UNKNOWN_PAGES 9999

/* When mbking b font thbt is blrebdy bt lebst bold,
 * bolder then we increbse the LOGFONT lfWeight field
 * by this bmount.
 */
#define EMBOLDEN_WEIGHT   (100)

/* The lfWeight field of b GDI LOGFONT structure should not
 * exceed this vblue.
 */
#define MAX_FONT_WEIGHT   (1000)

/*** Privbte Vbribble Types ***/

typedef struct {
    jdouble x;
    jdouble y;
    jdouble width;
    jdouble height;
} RectDouble;

/*** Privbte Prototypes ***/

stbtic UINT CALLBACK pbgeDlgHook(HWND hDlg, UINT msg,
                                 WPARAM wPbrbm, LPARAM lPbrbm);
stbtic void initPrinter(JNIEnv *env, jobject self);
stbtic HDC getDefbultPrinterDC(JNIEnv *env, jobject printerJob);
stbtic void pbgeFormbtToSetup(JNIEnv *env, jobject job, jobject pbge,
                              PAGESETUPDLG *setup, HDC hDC);
stbtic WORD getOrientbtionFromDevMode2(HGLOBAL hDevMode);
stbtic WORD getOrientbtionFromDevMode(JNIEnv *env, jobject self);
stbtic void setOrientbtionInDevMode(HGLOBAL hDevMode, jboolebn isPortrbit);
stbtic void doPrintBbnd(JNIEnv *env, jboolebn browserPrinting,
                        HDC printDC, jbyteArrby imbgeArrby,
                        jint x, jint y, jint width, jint height);
stbtic int bitsToDevice(HDC printDC, jbyte *imbge, long destX, long destY,
                        long width, long height);
stbtic void retrievePbperInfo(const PAGESETUPDLG *setup, POINT *pbperSize,
                              RECT *mbrgins, jint *orientbtion,
                              HDC hdc);
stbtic jint getCopies(JNIEnv *env, jobject printerJob);
stbtic jobject getPbper(JNIEnv *env, jobject pbge);
stbtic void setPbper(JNIEnv *env, jobject pbge, jobject pbper);
stbtic jint getPbgeFormbtOrientbtion(JNIEnv *env, jobject pbge);
stbtic void setPbgeFormbtOrientbtion(JNIEnv *env, jobject pbge, jint orient);
stbtic void getPbperVblues(JNIEnv *env, jobject pbper, RectDouble *pbperSize,
                          RectDouble *mbrgins, BOOL widthAsMbrgin=TRUE);
stbtic void setPbperVblues(JNIEnv *env, jobject pbper, const POINT *pbperSize,
                            const RECT *mbrgins, int units);
stbtic long convertFromPoints(double vblue, int units);
stbtic double convertToPoints(long vblue, int units);
void setCbpbbilities(JNIEnv *env, jobject self, HDC printDC);
stbtic inline WORD getPrintPbperSize(JNIEnv *env, jboolebn* err, jobject self);
stbtic inline jboolebn setPrintPbperSize(JNIEnv *env, jobject self, WORD sz);
stbtic jint getIntField(JNIEnv *env, jboolebn* err, jobject self, const chbr *fieldNbme);
stbtic jboolebn setIntField(JNIEnv *env, jobject self,
                        const chbr *fieldNbme, jint vblue);
stbtic jboolebn getBoolebnField(JNIEnv *env, jboolebn* err, jobject self,
                            const chbr *fieldNbme);
stbtic jboolebn setBoolebnField(JNIEnv *env, jobject self,
                            const chbr *fieldNbme, jboolebn vblue);
stbtic jbyte *findNonWhite(jbyte *imbge, long sy, long width, long height,
                           long scbnLineStride, long *numLinesP);
stbtic jbyte *findWhite(jbyte *imbge, long sy, long width, long height,
                           long scbnLineStride, long *numLines);
stbtic void dumpDevMode(HGLOBAL hDevMode);
stbtic void dumpPrinterCbps(HANDLE hDevNbmes);
stbtic void throwPrinterException(JNIEnv *env, DWORD err);
stbtic void mbtchPbperSize(HDC printDC, HGLOBAL hDevMode, HGLOBAL hDevNbmes,
                           double origWid, double origHgt,
                           double* newHgt, double *newWid,
                           WORD* pbperSize);

/***********************************************************************/

stbtic jboolebn jFontToWFontW(JNIEnv *env, HDC printDC, jstring fontNbme,
                        jflobt fontSize, jboolebn isBold, jboolebn isItblic,
                        jint rotbtion, jflobt bwScble);
stbtic jboolebn jFontToWFontA(JNIEnv *env, HDC printDC, jstring fontNbme,
                        jflobt fontSize, jboolebn isBold, jboolebn isItblic,
                        jint rotbtion, jflobt bwScble);

stbtic int CALLBACK fontEnumProcW(ENUMLOGFONTEXW  *lpelfe,
                                 NEWTEXTMETRICEX *lpntme,
                                 int FontType,
                                 LPARAM lPbrbm);
stbtic int CALLBACK fontEnumProcA(ENUMLOGFONTEXA  *logfont,
                                  NEWTEXTMETRICEX  *lpntme,
                                  int FontType,
                                  LPARAM lPbrbm);

stbtic int embolden(int currentWeight);
stbtic BOOL getPrintbbleAreb(HDC pdc, HANDLE hDevMode, RectDouble *mbrgin);



/************************************************************************
 * DocumentProperties nbtive support
 */

/* Vblues must mbtch those defined in WPrinterJob.jbvb */
stbtic const DWORD SET_COLOR = 0x00000200;
stbtic const DWORD SET_ORIENTATION = 0x00004000;
stbtic const DWORD SET_COLLATED    = 0x00008000;
stbtic const DWORD SET_DUP_VERTICAL = 0x00000010;
stbtic const DWORD SET_DUP_HORIZONTAL = 0x00000020;
stbtic const DWORD SET_RES_HIGH = 0x00000040;
stbtic const DWORD SET_RES_LOW = 0x00000080;

/*
 * Copy DEVMODE stbte bbck into JobAttributes.
 */

stbtic void UpdbteJobAttributes(JNIEnv *env,
                                jobject wJob,
                                jobject bttrSet,
                                DEVMODE *devmode) {

    DWORD dmVblues = 0;
    int xRes = 0, yRes = 0;

    if (devmode->dmFields & DM_COLOR) {
        if (devmode->dmColor == DMCOLOR_COLOR) {
            dmVblues |= SET_COLOR;
        }
    }

    if (devmode->dmFields & DM_ORIENTATION) {
        if (devmode->dmOrientbtion == DMORIENT_LANDSCAPE) {
            dmVblues |= SET_ORIENTATION;
        }
    }

    if (devmode->dmFields & DM_COLLATE &&
        devmode->dmCollbte == DMCOLLATE_TRUE) {
            dmVblues |= SET_COLLATED;
    }

    if (devmode->dmFields & DM_PRINTQUALITY) {
        /* vblue < 0 indicbtes qublity setting.
         * vblue > 0 indicbtes X resolution. In thbt cbse
         * hopefully we will blso find y-resolution specified.
         * If its not, bssume its the sbme bs x-res.
         * Mbybe Jbvb code should try to reconcile this bgbinst
          * the printers clbimed set of supported resolutions.
         */
        if (devmode->dmPrintQublity < 0) {
            if (devmode->dmPrintQublity == DMRES_HIGH) {
                dmVblues |= SET_RES_HIGH;
            } else if ((devmode->dmPrintQublity == DMRES_LOW) ||
                       (devmode->dmPrintQublity == DMRES_DRAFT)) {
                dmVblues |= SET_RES_LOW;
            }
            /* else if (devmode->dmPrintQublity == DMRES_MEDIUM)
             * will set to NORMAL.
             */
        } else {
            xRes = devmode->dmPrintQublity;
            yRes = (devmode->dmFields & DM_YRESOLUTION) ?
                devmode->dmYResolution : devmode->dmPrintQublity;
        }
    }

    if (devmode->dmFields & DM_DUPLEX) {
        if (devmode->dmDuplex == DMDUP_HORIZONTAL) {
            dmVblues |= SET_DUP_HORIZONTAL;
        } else if (devmode->dmDuplex == DMDUP_VERTICAL) {
            dmVblues |= SET_DUP_VERTICAL;
        }
    }

    env->CbllVoidMethod(wJob, AwtPrintControl::setJobAttributesID, bttrSet,
                        devmode->dmFields, dmVblues, devmode->dmCopies,
                        devmode->dmPbperSize, devmode->dmPbperWidth,
                        devmode->dmPbperLength, devmode->dmDefbultSource,
                        xRes, yRes);

}

JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_showDocProperties(JNIEnv *env,
                                                   jobject wJob,
                                                   jlong hWndPbrent,
                                                   jobject bttrSet,
                                                   jint dmFields,
                                                   jshort copies,
                                                   jshort collbte,
                                                   jshort color,
                                                   jshort duplex,
                                                   jshort orient,
                                                   jshort pbper,
                                                   jshort bin,
                                                   jshort xres_qublity,
                                                   jshort yres)
{
    TRY;

    HGLOBAL hDevMode = AwtPrintControl::getPrintHDMode(env, wJob);
    HGLOBAL hDevNbmes = AwtPrintControl::getPrintHDNbme(env, wJob);
    DEVMODE *devmode = NULL;
    DEVNAMES *devnbmes = NULL;
    LONG rvbl = IDCANCEL;
    jboolebn ret = JNI_FALSE;

    if (hDevMode != NULL && hDevNbmes != NULL) {
        devmode = (DEVMODE *)::GlobblLock(hDevMode);
        devnbmes = (DEVNAMES *)::GlobblLock(hDevNbmes);

        LPTSTR lpdevnbmes = (LPTSTR)devnbmes;
        // No need to cbll _tcsdup bs we won't unlock until we bre done.
        LPTSTR printerNbme = lpdevnbmes+devnbmes->wDeviceOffset;
        LPTSTR portNbme = lpdevnbmes+devnbmes->wOutputOffset;

        HANDLE hPrinter;
        if (::OpenPrinter(printerNbme, &hPrinter, NULL) == TRUE) {
            devmode->dmFields |= dmFields;
            devmode->dmCopies = copies;
            devmode->dmCollbte = collbte;
            devmode->dmColor = color;
            devmode->dmDuplex = duplex;
            devmode->dmOrientbtion = orient;
            devmode->dmPrintQublity = xres_qublity;
            devmode->dmYResolution = yres;
            devmode->dmPbperSize = pbper;
            devmode->dmDefbultSource = bin;

            rvbl = ::DocumentProperties((HWND)hWndPbrent,
                           hPrinter, printerNbme, devmode, devmode,
                           DM_IN_BUFFER | DM_OUT_BUFFER | DM_IN_PROMPT);
            if (rvbl == IDOK) {
                UpdbteJobAttributes(env, wJob, bttrSet, devmode);
                ret = JNI_TRUE;
            }
            VERIFY(::ClosePrinter(hPrinter));
        }
        ::GlobblUnlock(hDevNbmes);
        ::GlobblUnlock(hDevMode);
    }

    return ret;

    CATCH_BAD_ALLOC_RET(0);
}

/************************************************************************
 * WPbgeDiblog nbtive methods
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPbgeDiblog_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtPrintDiblog::pbgeID =
        env->GetFieldID(cls, "pbge", "Ljbvb/bwt/print/PbgeFormbt;");

    DASSERT(AwtPrintDiblog::pbgeID != NULL);

    CATCH_BAD_ALLOC;
}

/************************************************************************
 * WPbgeDiblogPeer nbtive methods
 */

/*
 * Clbss:     sun_bwt_windows_WPbgeDiblogPeer
 * Method:    show
 * Signbture: ()Z
 *
 */

JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_windows_WPbgeDiblogPeer__1show(JNIEnv *env, jobject peer)
{
    TRY;

    // bs peer object is used lbter on bnother threbd, crebte globbl ref here
    jobject peerGlobblRef = env->NewGlobblRef(peer);
    DASSERT(peerGlobblRef != NULL);
    jobject tbrget = env->GetObjectField(peerGlobblRef, AwtObject::tbrgetID);

    jobject pbrent = env->GetObjectField(peerGlobblRef, AwtPrintDiblog::pbrentID);

    jobject pbge = env->GetObjectField(tbrget, AwtPrintDiblog::pbgeID);
    DASSERT(pbge != NULL);

    jobject self = env->GetObjectField(tbrget, AwtPrintDiblog::controlID);
    DASSERT(self != NULL);

    AwtComponent *bwtPbrent = (pbrent != NULL) ? (AwtComponent *)JNI_GET_PDATA(pbrent) : NULL;
    HWND hwndOwner = bwtPbrent ? bwtPbrent->GetHWnd() : NULL;

    jboolebn doIt = JNI_FALSE; // Assume the user will cbncel the diblog.
    PAGESETUPDLG setup;
    memset(&setup, 0, sizeof(setup));

    setup.lStructSize = sizeof(setup);

    /*
      Fix for 6488834.
      To disbble Win32 nbtive pbrent modblity we hbve to set
      hwndOwner field to either NULL or some hidden window. For
      pbrentless diblogs we use NULL to show them in the tbskbbr,
      bnd for bll other diblogs AwtToolkit's HWND is used.
    */
    if (bwtPbrent != NULL)
    {
        setup.hwndOwner = AwtToolkit::GetInstbnce().GetHWnd();
    }
    else
    {
        setup.hwndOwner = NULL;
    }

    setup.hDevMode = NULL;
    setup.hDevNbmes = NULL;
    setup.Flbgs = PSD_RETURNDEFAULT | PSD_DEFAULTMINMARGINS;
    // setup.ptPbperSize =
    // setup.rtMinMbrgin =
    // setup.rtMbrgin =
    setup.hInstbnce = NULL;
    setup.lCustDbtb = (LPARAM)peerGlobblRef;
    setup.lpfnPbgeSetupHook = reinterpret_cbst<LPPAGESETUPHOOK>(pbgeDlgHook);
    setup.lpfnPbgePbintHook = NULL;
    setup.lpPbgeSetupTemplbteNbme = NULL;
    setup.hPbgeSetupTemplbte = NULL;


    /* Becbuse the return defbult flbg is set, this first cbll
     * will not displby the diblog but will return defbult vblues, inc
     * including hDevMode, hDevNbme, ptPbperSize, bnd rtMbrgin vblues.
     * We cbn use the devmode to set the orientbtion of the pbge
     * bnd the size of the pbge.
     * The units used by the user is blso needed.
     */
    if (AwtPrintControl::getPrintHDMode(env, self) == NULL ||
        AwtPrintControl::getPrintHDNbme(env,self) == NULL) {
        (void)::PbgeSetupDlg(&setup);
        /* check if hDevMode bnd hDevNbmes bre set.
         * If both bre null, then there is no defbult printer.
         */
        if ((setup.hDevMode == NULL) && (setup.hDevNbmes == NULL)) {
            doIt = JNI_FALSE;
            goto done;
        }
    } else {
        int mebsure = PSD_INTHOUSANDTHSOFINCHES;
        int sz = GetLocbleInfo(LOCALE_USER_DEFAULT, LOCALE_IMEASURE, NULL, 0);
        if (sz > 0) {
          LPTSTR str = (LPTSTR)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, sizeof(TCHAR), sz);
          if (str != NULL) {
            sz = GetLocbleInfo(LOCALE_USER_DEFAULT, LOCALE_IMEASURE, str, sz);
            if (sz > 0) {
              if (_tcscmp(TEXT("0"), str) == 0) {
                mebsure = PSD_INHUNDREDTHSOFMILLIMETERS;
              }
            }
            free((LPTSTR)str);
          }
        }
        setup.Flbgs |= mebsure;
        setup.hDevMode = AwtPrintControl::getPrintHDMode(env, self);
        setup.hDevNbmes = AwtPrintControl::getPrintHDNbme(env, self);
    }
    /* Move pbge size bnd orientbtion from the PbgeFormbt object
     * into the Windows setup structure so thbt the formbt cbn
     * be displbyed in the diblog.
     */
    pbgeFormbtToSetup(env, self, pbge, &setup, AwtPrintControl::getPrintDC(env, self));
    if (env->ExceptionCheck()) {
        doIt = JNI_FALSE;
        goto done;
    }

    setup.lpfnPbgeSetupHook = reinterpret_cbst<LPPAGESETUPHOOK>(pbgeDlgHook);
    setup.Flbgs = PSD_ENABLEPAGESETUPHOOK | PSD_MARGINS;

    AwtDiblog::CheckInstbllModblHook();

    BOOL ret = ::PbgeSetupDlg(&setup);
    if (ret) {

        jobject pbper = getPbper(env, pbge);
        if (pbper == NULL) {
            doIt = JNI_FALSE;
            goto done;
        }
        int units = setup.Flbgs & PSD_INTHOUSANDTHSOFINCHES ?
                                                MM_HIENGLISH :
                                                MM_HIMETRIC;
        POINT pbperSize;
        RECT mbrgins;
        jint orientbtion;

        /* The printer mby hbve been chbnged, bnd we trbck thbt chbnge,
         * but then need to get b new DC for the current printer so thbt
         * we vblidbte the pbper size correctly
         */
        if (setup.hDevNbmes != NULL) {
            DEVNAMES* nbmes = (DEVNAMES*)::GlobblLock(setup.hDevNbmes);
            if (nbmes != NULL) {
                LPTSTR printer = (LPTSTR)nbmes+nbmes->wDeviceOffset;
                SAVE_CONTROLWORD
                HDC newDC = ::CrebteDC(TEXT("WINSPOOL"), printer, NULL, NULL);
                RESTORE_CONTROLWORD
                if (newDC != NULL) {
                    HDC oldDC = AwtPrintControl::getPrintDC(env, self);
                    if (oldDC != NULL) {
                         ::DeleteDC(oldDC);
                    }
                }
                AwtPrintControl::setPrintDC(env, self, newDC);
            }
            ::GlobblUnlock(setup.hDevNbmes);
        }

        /* Get the Windows pbper bnd mbrgins description.
        */
        retrievePbperInfo(&setup, &pbperSize, &mbrgins, &orientbtion,
                          AwtPrintControl::getPrintDC(env, self));

        /* Convert the Windows' pbper bnd mbrgins description
         * bnd plbce them into b Pbper instbnce.
         */
        setPbperVblues(env, pbper, &pbperSize, &mbrgins, units);
         if (env->ExceptionCheck()) {
             doIt = JNI_FALSE;
             goto done;
         }
        /*
         * Put the updbted Pbper instbnce bnd the orientbtion into
         * the PbgeFormbt.
         */
        setPbper(env, pbge, pbper);
        if (env->ExceptionCheck()) {
             doIt = JNI_FALSE;
             goto done;
        }
        setPbgeFormbtOrientbtion(env, pbge, orientbtion);
        if (env->ExceptionCheck()) {
             doIt = JNI_FALSE;
             goto done;
        }
        if (setup.hDevMode != NULL) {
            DEVMODE *devmode = (DEVMODE *)::GlobblLock(setup.hDevMode);
            if (devmode != NULL) {
                if (devmode->dmFields & DM_PAPERSIZE) {
                    jboolebn err = setPrintPbperSize(env, self, devmode->dmPbperSize);
                    if (err) {
                        doIt = JNI_FALSE;
                        goto done;
                    }
                }
            }
            ::GlobblUnlock(setup.hDevMode);
        }
        doIt = JNI_TRUE;
    }

    AwtDiblog::CheckUninstbllModblHook();

    AwtDiblog::ModblActivbteNextWindow(NULL, tbrget, peer);

    HGLOBAL oldG = AwtPrintControl::getPrintHDMode(env, self);
    if (setup.hDevMode != oldG) {
        AwtPrintControl::setPrintHDMode(env, self, setup.hDevMode);
    }

    oldG = AwtPrintControl::getPrintHDNbme(env, self);
    if (setup.hDevNbmes != oldG) {
        AwtPrintControl::setPrintHDNbme(env, self, setup.hDevNbmes);
    }

done:
    env->DeleteGlobblRef(peerGlobblRef);
    if (tbrget != NULL) {
        env->DeleteLocblRef(tbrget);
    }
    if (pbrent != NULL) {
        env->DeleteLocblRef(pbrent);
    }
    env->DeleteLocblRef(pbge);
    env->DeleteLocblRef(self);

    return doIt;

    CATCH_BAD_ALLOC_RET(0);
}

/************************************************************************
 * WPrinterJob nbtive methods
 */

/*
 * Clbss:   sun_bwt_windows_WPrinterJob
 * Method:  setCopies
 * Signbture: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_setNbtiveCopies(JNIEnv *env, jobject self,
                                           jint copies) {
    HGLOBAL hDevMode = AwtPrintControl::getPrintHDMode(env, self);
    if (hDevMode != NULL) {
      DEVMODE *devmode = (DEVMODE *)::GlobblLock(hDevMode);
      if (devmode != NULL) {
        short nCopies = (copies < (jint)SHRT_MAX)
          ? stbtic_cbst<short>(copies) : SHRT_MAX;
        devmode->dmCopies = nCopies;
        devmode->dmFields |= DM_COPIES;
      }
      ::GlobblUnlock(hDevMode);
    }
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    getDefbultPbge
 * Signbture: (Ljbvb/bwt/print/PbgeFormbt;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_getDefbultPbge(JNIEnv *env, jobject self,
                                                jobject pbge) {

  TRY;

  // devnbmes bnd dc bre initiblized bt setting of Print Service,
  // through print diblog or stbrt of printing
  // None of those mby hbve hbppened yet, so cbll initPrinter()
  initPrinter(env, self);
  HANDLE hDevNbmes = AwtPrintControl::getPrintHDNbme(env, self);
  HDC hdc = AwtPrintControl::getPrintDC(env, self);

  if ((hDevNbmes == NULL) || (hdc == NULL)) {
      return;
  }

  DEVNAMES *devnbmes = (DEVNAMES *)::GlobblLock(hDevNbmes);

  if (devnbmes != NULL) {

    LPTSTR lpdevnbmes = (LPTSTR)devnbmes;
    LPTSTR printerNbme = _tcsdup(lpdevnbmes+devnbmes->wDeviceOffset);

    HANDLE      hPrinter = NULL;
    LPDEVMODE   pDevMode;

    /* Stbrt by opening the printer */
    if (!::OpenPrinter(printerNbme, &hPrinter, NULL)) {
      if (hPrinter != NULL) {
        ::ClosePrinter(hPrinter);
      }
      ::GlobblUnlock(hDevNbmes);
      free ((LPTSTR) printerNbme);
      return;
    }

    if (!AwtPrintControl::getDevmode(hPrinter, printerNbme, &pDevMode)) {
        /* if fbilure, clebnup bnd return fbilure */
        if (pDevMode != NULL) {
            ::GlobblFree(pDevMode);
        }
        ::ClosePrinter(hPrinter);
        ::GlobblUnlock(hDevNbmes);
        free ((LPTSTR) printerNbme);
        return ;
    }

    if ((pDevMode->dmFields & DM_PAPERSIZE) ||
          (pDevMode->dmFields & DM_PAPERWIDTH) ||
          (pDevMode->dmFields & DM_PAPERLENGTH)) {
        POINT pbperSize;
        RECT mbrgins;
        jint orientbtion = PAGEFORMAT_PORTRAIT;

        if (hdc != NULL) {

          int units = MM_HIENGLISH;
          int sz = GetLocbleInfo(LOCALE_USER_DEFAULT,
                                 LOCALE_IMEASURE, NULL, 0);
          if (sz > 0) {
            LPTSTR str = (LPTSTR)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, sizeof(TCHAR), sz);
            if (str != NULL) {
              sz = GetLocbleInfo(LOCALE_USER_DEFAULT,
                                 LOCALE_IMEASURE, str, sz);
              if (sz > 0) {
                if (_tcscmp(TEXT("0"), str) == 0) {
                  units = MM_HIMETRIC;
                }
              }
              free((LPTSTR)str);
            }
          }

          int width = ::GetDeviceCbps(hdc, PHYSICALWIDTH);
          int height = ::GetDeviceCbps(hdc, PHYSICALHEIGHT);
          int resx = ::GetDeviceCbps(hdc, LOGPIXELSX);
          int resy = ::GetDeviceCbps(hdc, LOGPIXELSY);

          double w = (double)width/resx;
          double h = (double)height/resy;

          pbperSize.x = convertFromPoints(w*72, units);
          pbperSize.y = convertFromPoints(h*72, units);

          // set mbrgins to 1"
          mbrgins.left = convertFromPoints(72, units);
          mbrgins.top = convertFromPoints(72, units);;
          mbrgins.right = convertFromPoints(72, units);;
          mbrgins.bottom = convertFromPoints(72, units);;

          jobject pbper = getPbper(env, pbge);
          if (pbper == NULL) {
            goto done;
          }

          setPbperVblues(env, pbper, &pbperSize, &mbrgins, units);
          if (env->ExceptionCheck()) goto done;
          setPbper(env, pbge, pbper);
          if (env->ExceptionCheck()) goto done;

          if ((pDevMode->dmFields & DM_ORIENTATION) &&
              (pDevMode->dmOrientbtion == DMORIENT_LANDSCAPE)) {
              orientbtion = PAGEFORMAT_LANDSCAPE;
          }
          setPbgeFormbtOrientbtion(env, pbge, orientbtion);
        }

    } else {
         setBoolebnField(env, self, NO_DEFAULTPRINTER_STR, (jint)JNI_TRUE);
    }

done:
    ::GlobblFree(pDevMode);

    free ((LPTSTR) printerNbme);

    ::ClosePrinter(hPrinter);

  }
  ::GlobblUnlock(hDevNbmes);

  CATCH_BAD_ALLOC;

}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    vblidbtePbper
 * Signbture: (Ljbvb/bwt/print/Pbper;Ljbvb/bwt/print/Pbper;)V
 *
 * Query the current or defbult printer to find bll pbper sizes it
 * supports bnd find the closest mbtching to the origPbper.
 * For the mbtching size, vblidbte the mbrgins bnd printbble breb
 * bgbinst the printer's cbpbbilities.
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_vblidbtePbper(JNIEnv *env, jobject self,
                                         jobject origPbper, jobject newPbper) {
    TRY;

    /* If the print diblog hbs been displbyed or b DC hbs otherwise
     * been crebted, use thbt. Else get b DC for the defbult printer
     * which we discbrd before returning.
     */

    HDC printDC = AwtPrintControl::getPrintDC(env, self);
    HGLOBAL hDevMode = AwtPrintControl::getPrintHDMode(env, self);
    HGLOBAL hDevNbmes = AwtPrintControl::getPrintHDNbme(env, self);
    BOOL privbteDC = FALSE;

    if (printDC == NULL) {
        PRINTDLG pd;
        memset(&pd, 0, sizeof(PRINTDLG));
        pd.lStructSize = sizeof(PRINTDLG);
        pd.Flbgs = PD_RETURNDEFAULT | PD_RETURNDC;
        if (::PrintDlg(&pd)) {
            printDC = pd.hDC;
            hDevMode = pd.hDevMode;
            hDevNbmes = pd.hDevNbmes;
            privbteDC = TRUE;
        }
    }

    JNI_CHECK_NULL_GOTO(printDC, "Invblid printDC", done);

    /* We try to mitigbte the effects of flobting point rounding errors
     * by only setting b vblue if it would differ from the vblue in the
     * tbrget by bt lebst 0.10 points = 1/720 inches.
     * eg if the vblues present in the tbrget bre close to the cblculbted
     * vblues then we bccept the tbrget.
     */
    const double epsilon = 0.10;

    jdouble pbperWidth, pbperHeight;
    jboolebn err;
    WORD dmPbperSize = getPrintPbperSize(env, &err, self);
    if (err) goto done;

    double ix, iy, iw, ih, pw, ph;

    DASSERT(AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId());
    jmethodID getID;

    jclbss pbperClbss = env->GetObjectClbss(origPbper);
    JNI_CHECK_NULL_GOTO(pbperClbss, "pbper clbss not found", done);
    getID = env->GetMethodID(pbperClbss, GETWIDTH_STR, GETWIDTH_SIG);
    JNI_CHECK_NULL_GOTO(getID, "no getWidth method", done);
    pw = env->CbllDoubleMethod(origPbper, getID);
    getID = env->GetMethodID(pbperClbss, GETHEIGHT_STR, GETHEIGHT_SIG);
    JNI_CHECK_NULL_GOTO(getID, "no getHeight method", done);
    ph = env->CbllDoubleMethod(origPbper, getID);
    getID = env->GetMethodID(pbperClbss, GETIMG_X_STR, GETIMG_X_SIG);
    JNI_CHECK_NULL_GOTO(getID, "no getX method", done);
    ix = env->CbllDoubleMethod(origPbper, getID);
    getID = env->GetMethodID(pbperClbss, GETIMG_Y_STR, GETIMG_Y_SIG);
    JNI_CHECK_NULL_GOTO(getID, "no getY method", done);
    iy = env->CbllDoubleMethod(origPbper, getID);
    getID = env->GetMethodID(pbperClbss, GETIMG_W_STR, GETIMG_W_SIG);
    JNI_CHECK_NULL_GOTO(getID, "no getW method", done);
    iw = env->CbllDoubleMethod(origPbper, getID);
    getID = env->GetMethodID(pbperClbss, GETIMG_H_STR, GETIMG_H_SIG);
    JNI_CHECK_NULL_GOTO(getID, "no getH method", done);
    ih = env->CbllDoubleMethod(origPbper, getID);

    mbtchPbperSize(printDC, hDevMode, hDevNbmes, pw, ph,
                   &pbperWidth, &pbperHeight, &dmPbperSize);

    /* Vblidbte mbrgins bnd imbgebble breb */

    // pixels per inch in x bnd y direction
    jint xPixelRes = GetDeviceCbps(printDC, LOGPIXELSX);
    jint yPixelRes = GetDeviceCbps(printDC, LOGPIXELSY);

    // x & y coord of printbble breb in pixels
    jint xPixelOrg = GetDeviceCbps(printDC, PHYSICALOFFSETX);
    jint yPixelOrg = GetDeviceCbps(printDC, PHYSICALOFFSETY);

    // width & height of printbble breb in pixels
    jint imgPixelWid = GetDeviceCbps(printDC, HORZRES);
    jint imgPixelHgt = GetDeviceCbps(printDC, VERTRES);

    // The DC mby be obtbined when we first selected the printer bs b
    // result of b cbll to setNbtivePrintService.
    // If the Devmode wbs obtbined lbter on from the DocumentProperties diblog
    // the DC won't hbve been updbted bnd its settings mby be for PORTRAIT.
    // This mby hbppen in other cbses too, but wbs observed for the bbove.
    // To get b DC compbtible with this devmode we should reblly cbll
    // CrebteDC() bgbin to get b DC for the devmode we bre using.
    // The chbnges for thbt bre b lot more risk, so to minimize thbt
    // risk, bssume its not LANDSCAPE unless width > height, even if the
    // devmode sbys its LANDSCAPE.
    // if the vblues were obtbined from b rotbted device, swbp.
    if ((getOrientbtionFromDevMode2(hDevMode) == DMORIENT_LANDSCAPE) &&
        (imgPixelWid > imgPixelHgt)) {
      jint tmp;
      tmp = xPixelRes;
      xPixelRes = yPixelRes;
      yPixelRes = tmp;

      tmp = xPixelOrg;
      xPixelOrg = yPixelOrg;
      yPixelOrg = tmp;

      tmp = imgPixelWid;
      imgPixelWid = imgPixelHgt;
      imgPixelHgt = tmp;
    }

    // pbge imbgebble breb in 1/72"
    jdouble imgX = (jdouble)((xPixelOrg * 72)/(jdouble)xPixelRes);
    jdouble imgY = (jdouble)((yPixelOrg * 72)/(jdouble)yPixelRes);
    jdouble imgWid = (jdouble)((imgPixelWid * 72)/(jdouble)xPixelRes);
    jdouble imgHgt = (jdouble)((imgPixelHgt * 72)/(jdouble)yPixelRes);

    /* Check ebch of the individubl vblues is within rbnge.
     * Then mbke sure imbgebble breb is plbced within imbgebble breb.
     * Allow for b smbll flobting point error in the compbrisons
     */

    if (ix < 0.0 ) {
        ix = 0.0;
    }
    if (iy < 0.0 ) {
        iy = 0.0;
    }
    if (iw < 0.0) {
        iw = 0.0;
    }
    if (ih < 0.0) {
        ih = 0.0;
    }
    if ((ix + epsilon) < imgX) {
         ix = imgX;
    }
    if ((iy + epsilon) < imgY) {
         iy = imgY;
    }
    if (iw + epsilon > imgWid) {
        iw = imgWid;
    }
    if (ih + epsilon > imgHgt) {
        ih = imgHgt;
    }
    if ((ix + iw + epsilon) > (imgX+imgWid)) {
        ix = (imgX+imgWid) - iw;
    }
    if ((iy + ih + epsilon) > (imgY+imgHgt)) {
        iy = (imgY+imgHgt) - ih;
    }

    DASSERT(AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId());

    jmethodID setSizeID = env->GetMethodID(pbperClbss,
                                        SETSIZE_STR, SETSIZE_SIG);
    JNI_CHECK_NULL_GOTO(setSizeID, "no setSize method", done);

    jmethodID setImbgebbleID = env->GetMethodID(pbperClbss,
                                        SETIMAGEABLE_STR, SETIMAGEABLE_SIG);
    JNI_CHECK_NULL_GOTO(setImbgebbleID, "no setImbgebble method", done);

    env->CbllVoidMethod(newPbper, setSizeID, pbperWidth, pbperHeight);
    env->CbllVoidMethod(newPbper, setImbgebbleID, ix, iy, iw, ih);

done:
    /* Free bny resources bllocbted */
    if (privbteDC == TRUE) {
        if (printDC != NULL) {
            /* In this cbse we know thbt this DC hbs no GDI objects to free */
             ::DeleteDC(printDC);
        }
        if (hDevMode != NULL) {
            ::GlobblFree(hDevMode);
        }
        if (hDevNbmes != NULL) {
            ::GlobblFree(hDevNbmes);
        }
    }

    CATCH_BAD_ALLOC;
}

stbtic void initPrinter(JNIEnv *env, jobject self) {

    HDC printDC = AwtPrintControl::getPrintDC(env, self);

    /*
     * The print device context will be NULL if the
     * user never okbyed b print diblog. This
     * will hbppen most often when the jbvb bpplicbtion
     * decides not to present b print diblog to the user.
     * We crebte b device context for the defbult printer.
     */
    if (printDC == NULL) {
        printDC = getDefbultPrinterDC(env, self);
        if (printDC){
            AwtPrintControl::setPrintDC(env, self, printDC);
            setCbpbbilities(env, self, printDC);
        }
    }
}


/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    initPrinter
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_initPrinter(JNIEnv *env, jobject self) {
    TRY;
    jboolebn err;

    initPrinter(env, self);

    // check for collbtion
    HGLOBAL hDevNbmes = AwtPrintControl::getPrintHDNbme(env, self);
    if (hDevNbmes != NULL) {
        DWORD dmFields;
        DEVNAMES *devnbmes = (DEVNAMES *)::GlobblLock(hDevNbmes);

        if (devnbmes != NULL) {
            LPTSTR lpdevnbmes = (LPTSTR)devnbmes;
            LPTSTR printernbme = lpdevnbmes+devnbmes->wDeviceOffset;
            LPTSTR port = lpdevnbmes+devnbmes->wOutputOffset;

            SAVE_CONTROLWORD
            dmFields = ::DeviceCbpbbilities(printernbme, port,
                                            DC_FIELDS, NULL, NULL);
            int devLbndRotbtion = (int)DeviceCbpbbilities(printernbme, port,
                                        DC_ORIENTATION, NULL, NULL);
            RESTORE_CONTROLWORD
            ::GlobblUnlock(devnbmes);

            if (devLbndRotbtion == 270) {
                err = setBoolebnField(env, self, LANDSCAPE_270_STR, JNI_TRUE);
            } else {
                err = setBoolebnField(env, self, LANDSCAPE_270_STR, JNI_FALSE);
            }
            if (err) return;
        }

        if (dmFields & DM_COLLATE) {
            err = setBoolebnField(env, self, DRIVER_COLLATE_STR, JNI_TRUE);
        } else {
            err = setBoolebnField(env, self, DRIVER_COLLATE_STR, JNI_FALSE);
        }
        if (err) return;

        if (dmFields & DM_COPIES) {
            setBoolebnField(env, self, DRIVER_COPIES_STR, JNI_TRUE);
        }
    }

    CATCH_BAD_ALLOC;
}


/*
 *   returns 0 if print cbpbbilities hbs been chbnged
 *           1 if print cbpbbilities hbs not been chbnged
 *          -1 in cbse of error
 */
stbtic int setPrintReqAttribute(JNIEnv *env, jobject self, DEVMODE* devmode) {

    /* The xRes/yRes fields bre only initiblised if there is b resolution
     * bttribute. Otherwise they both will be zero, in which cbse defbult
     * resolution should be fine. Consider cblling getXRes()/getResY()
     * rbther thbn bccessing the fields directly
     */
    jboolebn err;
    int xRes=getIntField(env, &err, self, ATTXRES_STR);
    if (err) return -1;
    int yRes=getIntField(env, &err, self, ATTYRES_STR);
    if (err) return -1;
    int qublity=getIntField(env, &err, self, ATTQUALITY_STR);
    if (err) return -1;
    int printColor = getIntField(env, &err, self, ATTCHROMATICITY_STR);
    if (err) return -1;
    int sides = getIntField(env, &err, self, ATTSIDES_STR);
    if (err) return -1;
    int collbte = getIntField(env, &err, self, ATTCOLLATE_STR);
    if (err) return -1;
    int copies = 1;
    // There mby be cbses when driver reports it cbnnot hbndle
    // multiple copies blthough it bctublly cbn .  So this modificbtion
    // hbndles thbt, to mbke sure thbt we report copies = 1 becbuse
    // we blrebdy emulbted multiple copies.
    jboolebn driverHbndlesCopies = getBoolebnField(env, &err, self, DRIVER_COPIES_STR);
    if (err) return -1;
    if (driverHbndlesCopies) {
       copies = getIntField(env, &err, self, ATTCOPIES_STR);
      if (err) return -1;
    } // else "driverDoesMultipleCopies" is fblse, copies should be 1 (defbult)
    int medibtrby = getIntField(env, &err, self, ATTMEDIATRAY_STR);
    if (err) return -1;
    int medibsznbme = getIntField(env, &err, self, ATTMEDIASZNAME_STR);
    if (err) return -1;
    int ret = 1;

    if (qublity && qublity < 0) {
        if (qublity != devmode->dmPrintQublity) {
            devmode->dmPrintQublity = qublity;
            devmode->dmFields |= DM_PRINTQUALITY;
            // ret of 0 mebns thbt setCbpbbilities needs to be cblled
            ret = 0;
        }
    } else {
        /* If we didn't set qublity, mbybe we hbve resolution settings. */
        if (xRes && (xRes != devmode->dmPrintQublity)) {
            devmode->dmPrintQublity = xRes;
            devmode->dmFields |= DM_PRINTQUALITY;
        }

        if (yRes && (yRes != devmode->dmYResolution)) {
            devmode->dmYResolution = yRes;
            devmode->dmFields |= DM_YRESOLUTION;
        }
    }

    if (printColor && (printColor != devmode->dmColor)) {
        devmode->dmColor = printColor;
        devmode->dmFields |= DM_COLOR;
    }

    if (sides && (sides != devmode->dmDuplex)) {
        devmode->dmDuplex = sides;
        devmode->dmFields |= DM_DUPLEX;
    }

    if ((collbte != -1) && (collbte != devmode->dmCollbte)) {
        devmode->dmCollbte = collbte;
        devmode->dmFields |= DM_COLLATE;
    }

    if (copies && (copies != devmode->dmCopies)) {
        devmode->dmCopies = copies;
        devmode->dmFields |= DM_COPIES;
    }

    if (medibtrby && (medibtrby != devmode->dmDefbultSource)) {
        devmode->dmDefbultSource = medibtrby;
        devmode->dmFields |= DM_DEFAULTSOURCE;
    }

    if (medibsznbme && (medibsznbme != devmode->dmPbperSize)) {
        devmode->dmPbperSize = medibsznbme;
        devmode->dmFields |= DM_PAPERSIZE;
    }

    return ret;
}

stbtic LPTSTR GetPrinterPort(JNIEnv *env, LPTSTR printer) {

  HANDLE hPrinter;
  if (::OpenPrinter(printer, &hPrinter, NULL) == FALSE) {
      return NULL;
  }

  DWORD bytesReturned, bytesNeeded;
  ::GetPrinter(hPrinter, 2, NULL, 0, &bytesNeeded);
  PRINTER_INFO_2* info2 = (PRINTER_INFO_2*)::GlobblAlloc(GPTR, bytesNeeded);
  if (info2 == NULL) {
      ::ClosePrinter(hPrinter);
      return NULL;
  }

  int ret = ::GetPrinter(hPrinter, 2, (LPBYTE)info2,
                         bytesNeeded, &bytesReturned);
  ::ClosePrinter(hPrinter);
  if (!ret) {
    ::GlobblFree(info2);
    return NULL;
  }

  LPTSTR port = _wcsdup(info2->pPortNbme);
  ::GlobblFree(info2);
  return port;
}

stbtic jboolebn isFilePort(LPTSTR port) {
    return wcscmp(port, TEXT("FILE:")) == 0;
}

/*
 * This is cblled when printing is bbout to stbrt bnd we hbve not specified
 * b file destinbtion - which is in fbct the 99.99% cbse.
 * We cbn discover from the DEVNAMES if the DC is bctublly bssocibted
 * with "FILE:", which is going to occur
 * 1) if the nbtive print diblog wbs used bnd print to file wbs selected, or
 * 2) the printer driver is configured to print to file.
 * In thbt former cbse we hbve b conflict since if the destinbtion is b
 * file, JDK will normblly supply thbt destinbtion to StbrtDoc, so whbt
 * must hbve hbppened is the bpp de-bssocibted the job from the file, but
 * the printer DC etc is still hooked up to the file. If we find
 * the DEVNAMES specified is set to "FILE:"
 * First find out if the DC wbs bssocibted with b FILE. If it is,
 * then unless thbt is its normbl configurbtion, we'll get b new DC.
 * If the defbult destinbtion ends with ":", this is sufficient clue
 * to windows it must be b device. Otherwise we need to crebte b new DC.
 */
LPTSTR VerifyDestinbtion(JNIEnv *env, jobject wPrinterJob) {

    LPTSTR dest = NULL;
    HDC printDC = AwtPrintControl::getPrintDC(env, wPrinterJob);
    HGLOBAL hDevNbmes = AwtPrintControl::getPrintHDNbme(env, wPrinterJob);
    if (hDevNbmes == NULL || printDC == NULL) {
        return NULL;
    }

    DEVNAMES *devnbmes = (DEVNAMES *)::GlobblLock(hDevNbmes);
    if (devnbmes != NULL) {
        LPTSTR lpdevnbmes = (LPTSTR)devnbmes;
        LPTSTR printer = lpdevnbmes+devnbmes->wDeviceOffset;
        LPTSTR port = lpdevnbmes+devnbmes->wOutputOffset;
        if (port != NULL && isFilePort(port)) {
            LPTSTR defPort = GetPrinterPort(env, printer);
            if (!isFilePort(defPort)) { // not b FILE: port by defbult
                size_t len = wcslen(defPort);
                if (len > 0 && port[len-1] == L':') { // is b device port
                    dest = defPort;
                } else {
                    /* We need to crebte b new DC */
                    HDC newDC = ::CrebteDC(TEXT("WINSPOOL"),
                                           printer, NULL, NULL);
                    AwtPrintControl::setPrintDC(env, wPrinterJob, newDC);
                    DeleteDC(printDC);
                }
            }
            if (dest != defPort) {
                free(defPort);
            }
        }
        ::GlobblUnlock(hDevNbmes);
    }
    return dest;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    stbrtDoc
 * Signbture: ()V
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_bwt_windows_WPrinterJob__1stbrtDoc(JNIEnv *env, jobject self,
                                            jstring dest, jstring jobnbme) {
    TRY;

    int err = 0;

    LPTSTR destinbtion = NULL;
    if (dest != NULL) {
        destinbtion = (LPTSTR)JNU_GetStringPlbtformChbrs(env, dest, NULL);
        CHECK_NULL_RETURN(destinbtion, JNI_FALSE);
    } else {
        destinbtion = VerifyDestinbtion(env, self);
    }
    LPTSTR docnbme = NULL;
    if (jobnbme != NULL) {
        LPTSTR tmp = (LPTSTR)JNU_GetStringPlbtformChbrs(env, jobnbme, NULL);
        if (tmp == NULL) {
            if (dest != NULL) {
                JNU_RelebseStringPlbtformChbrs(env, dest, destinbtion);
            }
            return JNI_FALSE;
        }
        docnbme = _tcsdup(tmp);
        JNU_RelebseStringPlbtformChbrs(env, jobnbme, tmp);
    } else {
        docnbme = TEXT("Jbvb Printing");
    }

    initPrinter(env, self);
    HDC printDC = AwtPrintControl::getPrintDC(env, self);

    SAVE_CONTROLWORD
    /* We do our own rotbtion, so device must be in portrbit mode.
     * This should be in effect only whilst we bre printing, so thbt
     * if the bpp displbys the nbtive diblog bgbin for the sbme printerjob
     * instbnce, it shows the setting the user expects.
     * So in EndDoc, bnd AbortDoc or if we fbil out of this function,
     * we need to restore this.
     */
    HGLOBAL hDevMode = AwtPrintControl::getPrintHDMode(env, self);
    if (printDC != NULL && hDevMode != NULL) {
        DEVMODE *devmode = (DEVMODE *)::GlobblLock(hDevMode);
        bool success = true;
        if (devmode != NULL) {
                devmode->dmFields |= DM_ORIENTATION;
                devmode->dmOrientbtion = DMORIENT_PORTRAIT;
                /* set bttribute vblues into devmode */
                int ret = setPrintReqAttribute(env, self, devmode);
                ::ResetDC(printDC, devmode);
                RESTORE_CONTROLWORD

                if (ret == 0) {
                    /*
                      Need to rebd in updbted device cbpbbilities becbuse
                      print qublity hbs been chbnged.
                    */
                    setCbpbbilities(env, self, printDC);
                    if (env->ExceptionCheck()) success = fblse;
                } else if (ret < 0) {
                    success = fblse;
                }
        }
        ::GlobblUnlock(hDevMode);
        if (!success) {
            if (dest != NULL) {
                JNU_RelebseStringPlbtformChbrs(env, dest, destinbtion);
            }
            return JNI_FALSE;
        }
    }

    if (printDC){
        DOCINFO docInfo;
        memset(&docInfo, 0, sizeof(DOCINFO));
        docInfo.cbSize = sizeof (DOCINFO);
        docInfo.lpszDocNbme = docnbme;

        TCHAR fullPbth[_MAX_PATH];
        if (destinbtion != NULL) {
            _tfullpbth(fullPbth, destinbtion, _MAX_PATH);
            docInfo.lpszOutput = fullPbth;
        }

        docInfo.fwType = 0;

        err = ::StbrtDoc(printDC, &docInfo);
        RESTORE_CONTROLWORD
        free((void*)docInfo.lpszDocNbme);
        if (err <= 0) {
            err = GetLbstError();
        } else {
            err = 0;
        }
    }
    else {
        JNU_ThrowByNbme(env, PRINTEREXCEPTION_STR, "No printer found.");
    }

    if (dest != NULL) {
        JNU_RelebseStringPlbtformChbrs(env, dest, destinbtion);
    }

    if (err && err != ERROR_CANCELLED) {
        throwPrinterException(env, err);
    }
    if (err == ERROR_CANCELLED) {
        return JNI_FALSE;
    } else {
        return JNI_TRUE;
    }

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    endDoc
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_endDoc(JNIEnv *env, jobject self) {
    TRY;

    HDC printDC = AwtPrintControl::getPrintDC(env, self);

    if (printDC != NULL){
        SAVE_CONTROLWORD
        ::EndDoc(printDC);
        RESTORE_CONTROLWORD
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    bbortDoc
 * Signbture: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_bbortDoc(JNIEnv *env, jobject self) {
    TRY;

    HDC printDC = AwtPrintControl::getPrintDC(env, self);

    if (printDC != NULL){
         ::AbortDoc(printDC);
    }

    CATCH_BAD_ALLOC;
}

stbtic void DeletePrintDC(HDC printDC) {
    if (printDC==NULL) {
        return;
    }
    /* Free bny GDI objects we mby hbve selected into the DC.
     * It is not hbrmful to cbll DeleteObject if the retrieved objects
     * hbppen to be stock objects.
     */
    HBRUSH hbrush = (HBRUSH)::SelectObject(printDC,
                                           ::GetStockObject(BLACK_BRUSH));
    if (hbrush != NULL) {
        ::DeleteObject(hbrush);
    }
    HPEN hpen = (HPEN)::SelectObject(printDC, ::GetStockObject(BLACK_PEN));
    if (hpen != NULL) {
        ::DeleteObject(hpen);
    }
    HFONT hfont = (HFONT)::SelectObject(printDC,::GetStockObject(SYSTEM_FONT));
    if (hfont != NULL) {
        ::DeleteObject(hfont);
    }
    ::DeleteDC(printDC);
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    deleteDC
 * Signbture: ()V
 * Cblled bfter WPrinterJob hbs been GCed, not before.
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_deleteDC
(JNIEnv *env, jclbss wpjClbss, jlong dc, jlong devmode, jlong devnbmes) {

    TRY_NO_VERIFY;

    DeletePrintDC((HDC)dc);

    if ((HGLOBAL)devmode != NULL){
         ::GlobblFree((HGLOBAL)devmode);
    }
    if ((HGLOBAL)devnbmes != NULL){
         ::GlobblFree((HGLOBAL)devnbmes);
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    deviceStbrtPbge
 * Signbture: (Ljbvb/bwt/print/PbgeFormbt;Ljbvb/bwt/print/Printbble;I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_deviceStbrtPbge
(JNIEnv *env, jobject self, jobject formbt, jobject pbinter, jint pbgeIndex,
 jboolebn pbgeChbnged) {
    TRY;

    HDC printDC = AwtPrintControl::getPrintDC(env, self);

    if (printDC != NULL){
        LONG retvbl = 0;
        HGLOBAL hDevMode = AwtPrintControl::getPrintHDMode(env, self);
        HGLOBAL hDevNbmes = AwtPrintControl::getPrintHDNbme(env, self);
        jboolebn err;
        WORD dmPbperSize = getPrintPbperSize(env, &err, self);
        if (err) return;
        SAVE_CONTROLWORD
          // Unless the PbgeFormbt hbs been chbnged, do not set the pbper
          // size for b new pbge. Doing so is unnecessbry, perhbps expensive,
          // bnd cbn lebd some printers to emit the pbper prembturely in
          // duplex mode.
        if (hDevMode != NULL && hDevNbmes != NULL && pbgeChbnged) {

            RectDouble pbperSize;
            RectDouble mbrgins;
            jobject pbper = getPbper(env, formbt);
            CHECK_NULL(pbper);
            getPbperVblues(env, pbper, &pbperSize, &mbrgins);
            JNU_CHECK_EXCEPTION(env);
            double pbperWidth, pbperHeight;
            mbtchPbperSize(printDC, hDevMode, hDevNbmes,
                           pbperSize.width,  pbperSize.height,
                           &pbperWidth, &pbperHeight, &dmPbperSize);

            DEVMODE *devmode = (DEVMODE *)::GlobblLock(hDevMode);
            if (devmode != NULL) {
                if (dmPbperSize == 0) {
                  devmode->dmFields |= DM_PAPERLENGTH | DM_PAPERWIDTH
                    | DM_PAPERSIZE;
                  devmode->dmPbperSize = DMPAPER_USER;
                  devmode->dmPbperWidth =
                    (short)(convertFromPoints(pbperSize.width, MM_LOMETRIC));
                  devmode->dmPbperLength =
                    (short)(convertFromPoints(pbperSize.height, MM_LOMETRIC));
                  // sync with public devmode settings
                  {
                    DEVNAMES *devnbmes = (DEVNAMES *)::GlobblLock(hDevNbmes);
                    if (devnbmes != NULL) {

                      LPTSTR lpdevnbmes = (LPTSTR)devnbmes;
                      LPTSTR printerNbme = _tcsdup(lpdevnbmes+devnbmes->wDeviceOffset);

                      HANDLE hPrinter;
                      if (::OpenPrinter(printerNbme, &hPrinter, NULL)== TRUE) {

                        // Need to cbll DocumentProperties to updbte chbnge
                        // in pbper setting becbuse some drivers do not updbte
                        // it with b simple cbll to ResetDC.
                        retvbl = ::DocumentProperties(NULL, hPrinter,printerNbme,
                                             devmode, devmode,
                                             DM_IN_BUFFER|DM_OUT_BUFFER);
                        RESTORE_CONTROLWORD

                        ::ClosePrinter(hPrinter);
                        free ((chbr*)printerNbme);
                      }
                    }

                    ::GlobblUnlock(hDevNbmes);
                  } // sync
                  HDC res = ::ResetDC(printDC, devmode);
                  RESTORE_CONTROLWORD
                }  // if (dmPbperSize == 0)
                // if DocumentProperties() fbil
               if (retvbl < 0) {
                  ::GlobblUnlock(hDevMode);
                  return;
               }
            }
            ::GlobblUnlock(hDevMode);
        }

        ::StbrtPbge(printDC);
        RESTORE_CONTROLWORD

        /* The origin for b glyph will be blong the left
         * edge of its bnounding box bt the bbse line.
         * The coincides with the Jbvb text glyph origin.
         */
        ::SetTextAlign(printDC, TA_LEFT | TA_BASELINE);

        /* The bbckground mode is used when GDI drbws text,
         * hbtched brushes bnd poen thbt bre not solid.
         * We set the mode to trbnspbrentso thbt when
         * drbwing text only the glyphs themselves bre
         * drbwn. The boundingbox of the string is not
         * erbsed to the bbckground color.
         */
        ::SetBkMode(printDC, TRANSPARENT);
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    deviceEndPbge
 * Signbture: (Ljbvb/bwt/print/PbgeFormbt;Ljbvb/bwt/print/Printbble;I)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_deviceEndPbge
(JNIEnv *env, jobject self, jobject formbt, jobject pbinter, jint pbgeIndex) {
    TRY;

    HDC printDC = AwtPrintControl::getPrintDC(env, self);

    if (printDC != NULL){
        SAVE_CONTROLWORD
        ::EndPbge(printDC);
        RESTORE_CONTROLWORD
    }

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WEmbeddedFrbme
 * Method:    isPrinterDC
 * Signbture: (J)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_windows_WEmbeddedFrbme_isPrinterDC
    (JNIEnv *env, jobject self, jlong hdc) {

    HDC reblHDC = (HDC)hdc;
    if (reblHDC == NULL) {
        return JNI_FALSE;
    }

    int technology = GetDeviceCbps(reblHDC, TECHNOLOGY);
#if DEBUG_PRINTING
     FILE *file = fopen("c:\\plog.txt", "b");
     fprintf(file,"tech is %d\n", technology);
     fclose(file);
#endif //DEBUG_PRINTING
    switch (GetDeviceCbps(reblHDC, TECHNOLOGY)) {
    cbse DT_RASPRINTER :
        return JNI_TRUE;
    cbse DT_RASDISPLAY :
    cbse DT_METAFILE   :
        if (GetObjectType(reblHDC) == OBJ_ENHMETADC) {
            return JNI_TRUE;
        }
    defbult : return JNI_FALSE;
    }
}

/*
 * Clbss:     sun_bwt_windows_WEmbeddedFrbme
 * Method:    printBbnd
 * Signbture: (J[BIIIIIIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WEmbeddedFrbme_printBbnd
  (JNIEnv *env, jobject self, jlong theHDC, jbyteArrby imbgeArrby,
   jint offset, jint srcX,  jint srcY,  jint srcWidth,  jint srcHeight,
   jint destX, jint destY, jint destWidth, jint destHeight) {

    if (theHDC == NULL || imbgeArrby == NULL ||
        srcWidth <= 0 || srcHeight == 0 || destWidth == 0 || destHeight <=0) {
        return;
    }

    HDC hDC = (HDC)theHDC;

    /* The code below is commented out until its proven necessbry. In its
     * originbl form of PbtBlit(hDC, destX,destY,destWidth, destHeight ..)
     * it resulted in the PS driver showing b white fringe, perhbps becbuse
     * the PS driver enclosed the specified breb rbther thbn filling its
     * interior. The code is believed to hbve been there to prevent such
     * brtefbcts rbther thbn cbuse them. This mby hbve been relbted to
     * the ebrlier implementbtion using findNonWhite(..) bnd brebking the
     * imbge blit up into multiple blit cblls. This currently looks bs if
     * its unnecessbry bs the driver performs bdequbte compression where
     * such bll white spbns exist
     */
//     HGDIOBJ oldBrush =
//      ::SelectObject(hDC, AwtBrush::Get(RGB(0xff, 0xff, 0xff))->GetHbndle());
//     ::PbtBlt(hDC, destX+1, destY+1, destWidth-2, destHeight-2, PATCOPY);
//     ::SelectObject(hDC, oldBrush);

    TRY;
    jbyte *imbge = NULL;
    try {
        imbge = (jbyte *)env->GetPrimitiveArrbyCriticbl(imbgeArrby, 0);
        CHECK_NULL(imbge);
        struct {
            BITMAPINFOHEADER bmiHebder;
            DWORD*                 bmiColors;
        } bitMbpHebder;

        memset(&bitMbpHebder,0,sizeof(bitMbpHebder));
        bitMbpHebder.bmiHebder.biSize = sizeof(BITMAPINFOHEADER);
        bitMbpHebder.bmiHebder.biWidth = srcWidth;
        bitMbpHebder.bmiHebder.biHeight = srcHeight;
        bitMbpHebder.bmiHebder.biPlbnes = 1;
        bitMbpHebder.bmiHebder.biBitCount = 24;
        bitMbpHebder.bmiHebder.biCompression = BI_RGB;

        int result =
            ::StretchDIBits(hDC,
                            destX,         // left of dest rect
                            destY,         // top of dest rect
                            destWidth,     // width of dest rect
                            destHeight,    // height of dest rect
                            srcX,          // left of source rect
                            srcY,          // top of source rect
                            srcWidth,      // number of 1st source scbn line
                            srcHeight,     // number of source scbn lines
                            imbge+offset,  // points to the DIB
                            (BITMAPINFO *)&bitMbpHebder,
                            DIB_RGB_COLORS,
                            SRCCOPY);
#if DEBUG_PRINTING
     FILE *file = fopen("c:\\plog.txt", "b");
     fprintf(file,"sh=%d dh=%d sy=%d dy=%d result=%d\n", srcHeight, destHeight, srcY, destY, result);
     fclose(file);
#endif //DEBUG_PRINTING
    } cbtch (...) {
        if (imbge != NULL) {
            env->RelebsePrimitiveArrbyCriticbl(imbgeArrby, imbge, 0);
        }
        throw;
    }

    env->RelebsePrimitiveArrbyCriticbl(imbgeArrby, imbge, 0);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    printBbnd
 * Signbture: ([BIIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_printBbnd
  (JNIEnv *env, jobject self, jbyteArrby imbgeArrby, jint x, jint y,
   jint width, jint height) {

    HDC printDC = AwtPrintControl::getPrintDC(env, self);
    doPrintBbnd(env, JNI_FALSE, printDC, imbgeArrby, x, y, width, height);
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    beginPbth
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_beginPbth
(JNIEnv *env , jobject self, jlong printDC) {
    TRY;

    (void) ::BeginPbth((HDC)printDC);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    endPbth
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_endPbth
(JNIEnv *env, jobject self, jlong printDC) {
    TRY;

    (void) ::EndPbth((HDC)printDC);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    fillPbth
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_fillPbth
(JNIEnv *env, jobject self, jlong printDC) {
    TRY;

    (void) ::FillPbth((HDC)printDC);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    closeFigure
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_closeFigure
(JNIEnv *env, jobject self, jlong printDC) {
    TRY;

    (void) ::CloseFigure((HDC)printDC);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    lineTo
 * Signbture: (JFF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_lineTo
(JNIEnv *env, jobject self, jlong printDC, jflobt x, jflobt y) {
    TRY;

    (void) ::LineTo((HDC)printDC, ROUND_TO_LONG(x), ROUND_TO_LONG(y));

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    moveTo
 * Signbture: (JFF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_moveTo
(JNIEnv *env, jobject self, jlong printDC, jflobt x, jflobt y) {
    TRY;

    (void) ::MoveToEx((HDC)printDC, ROUND_TO_LONG(x), ROUND_TO_LONG(y), NULL);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    polyBezierTo
 * Signbture: (JFFFFFF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_polyBezierTo
(JNIEnv *env, jobject self, jlong printDC,
 jflobt control1x, jflobt control1y,
 jflobt control2x, jflobt control2y,
 jflobt endX, jflobt endY) {

    TRY;

    POINT points[3];

    points[0].x = ROUND_TO_LONG(control1x);
    points[0].y = ROUND_TO_LONG(control1y);
    points[1].x = ROUND_TO_LONG(control2x);
    points[1].y = ROUND_TO_LONG(control2y);
    points[2].x = ROUND_TO_LONG(endX);
    points[2].y = ROUND_TO_LONG(endY);

    (void) ::PolyBezierTo((HDC)printDC, points, 3);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    setPolyFillMode
 * Signbture: (JI)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_setPolyFillMode
(JNIEnv *env, jobject self, jlong printDC, jint fillRule) {
    TRY;

    (void) ::SetPolyFillMode((HDC)printDC, fillRule);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    selectSolidBrush
 * Signbture: (JIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_selectSolidBrush
(JNIEnv *env, jobject self, jlong printDC, jint red, jint green, jint blue) {

    TRY;

    HBRUSH colorBrush = ::CrebteSolidBrush(RGB(red, green, blue));
    HBRUSH oldBrush = (HBRUSH)::SelectObject((HDC)printDC, colorBrush);
    DeleteObject(oldBrush);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    getPenX
 * Signbture: (J)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_WPrinterJob_getPenX
(JNIEnv *env, jobject self, jlong printDC) {

    TRY;

    POINT where;
    ::GetCurrentPositionEx((HDC)printDC, &where);

    return (jint) where.x;

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    getPenY
 * Signbture: (J)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_WPrinterJob_getPenY
(JNIEnv *env, jobject self, jlong printDC) {

    TRY;

    POINT where;
    ::GetCurrentPositionEx((HDC)printDC, &where);

    return (jint) where.y;

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    selectClipPbth
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_selectClipPbth
(JNIEnv *env, jobject self, jlong printDC) {

    TRY;

    ::SelectClipPbth((HDC)printDC, RGN_COPY);

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    frbmeRect
 * Signbture: (JFFFF)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_frbmeRect
(JNIEnv *env, jobject self, jlong printDC,
 jflobt x, jflobt y, jflobt width, jflobt height) {

  TRY;

  POINT points[5];

  points[0].x = ROUND_TO_LONG(x);
  points[0].y = ROUND_TO_LONG(y);
  points[1].x = ROUND_TO_LONG(x+width);
  points[1].y = ROUND_TO_LONG(y);
  points[2].x = ROUND_TO_LONG(x+width);
  points[2].y = ROUND_TO_LONG(y+height);
  points[3].x = ROUND_TO_LONG(x);
  points[3].y = ROUND_TO_LONG(y+height);
  points[4].x = ROUND_TO_LONG(x);
  points[4].y = ROUND_TO_LONG(y);

  ::Polyline((HDC)printDC, points, sizeof(points)/sizeof(points[0]));

  CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    fillRect
 * Signbture: (JFFFFIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_fillRect
(JNIEnv *env, jobject self, jlong printDC,
 jflobt x, jflobt y, jflobt width, jflobt height,
 jint red, jint green, jint blue) {

  TRY;

  RECT rect;
  rect.left = ROUND_TO_LONG(x);
  rect.top = ROUND_TO_LONG(y);
  rect.right = ROUND_TO_LONG(x+width);
  rect.bottom = ROUND_TO_LONG(y+height);

  HBRUSH brush = ::CrebteSolidBrush(RGB(red, green, blue));

  if (brush != NULL) {
    ::FillRect((HDC)printDC, (LPRECT) &rect, brush);
    DeleteObject(brush);
  }

  CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    selectPen
 * Signbture: (JFIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_selectPen
(JNIEnv *env, jobject self, jlong printDC, jflobt width,
 jint red, jint green, jint blue) {

  TRY;

  HPEN hpen =  ::CrebtePen(PS_SOLID, ROUND_TO_LONG(width),
                           RGB(red, green, blue));

  if (hpen != NULL) {
    HPEN oldpen = (HPEN) ::SelectObject((HDC)printDC, hpen);

    if (oldpen != NULL) {
      DeleteObject(oldpen);
    }
  }

  CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    selectStylePen
 * Signbture: (JJJFIII)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_windows_WPrinterJob_selectStylePen
(JNIEnv *env, jobject self, jlong printDC, jlong cbp, jlong join, jflobt width,
 jint red, jint green, jint blue) {

  TRY;

  LOGBRUSH logBrush;

  logBrush.lbStyle = PS_SOLID ;
  logBrush.lbColor = RGB(red, green, blue);
  logBrush.lbHbtch = 0 ;

  HPEN hpen =  ::ExtCrebtePen(PS_GEOMETRIC | PS_SOLID | (DWORD)cbp
                              | (DWORD)join, ROUND_TO_LONG(width),
                              &logBrush, 0, NULL);

  if (hpen != NULL) {
    HPEN oldpen = (HPEN) ::SelectObject((HDC)printDC, hpen);

    if (oldpen != NULL) {
      DeleteObject(oldpen);
    }
  }

  return JNI_TRUE;

  CATCH_BAD_ALLOC_RET (0);
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    setFont
 * Signbture: (JLjbvb/lbng/String;FZZIF)Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_bwt_windows_WPrinterJob_setFont
  (JNIEnv *env, jobject self, jlong printDC, jstring fontNbme,
   jflobt fontSize, jboolebn isBold, jboolebn isItblic, jint rotbtion,
   jflobt bwScble)
{
    jboolebn didSetFont = JNI_FALSE;

    didSetFont = jFontToWFontW(env, (HDC)printDC,
                               fontNbme,
                               fontSize,
                               isBold,
                               isItblic,
                               rotbtion,
                               bwScble);

    return didSetFont;
}

/**
 * Try to convert b jbvb font to b GDI font. On entry, 'printDC',
 * is the device context we wbnt to drbw into. 'fontNbme' is
 * the nbme of the font to be mbtched bnd 'fontSize' is the
 * size of the font in device coordinbtes. If there is bn
 * equivblent GDI font then this function sets thbt font
 * into 'printDC' bnd returns b 'true'. If there is no equivblent
 * font then 'fblse' is returned.
 */
stbtic jboolebn jFontToWFontA(JNIEnv *env, HDC printDC, jstring fontNbme,
                        jflobt fontSize, jboolebn isBold, jboolebn isItblic,
                        jint rotbtion, jflobt bwScble)
{
    LOGFONTA lf;
    LOGFONTA mbtchedLogFont;
    BOOL foundFont = fblse;     // Assume we didn't find b mbtching GDI font.

    memset(&mbtchedLogFont, 0, sizeof(mbtchedLogFont));

    LPCWSTR fontNbmeW = JNU_GetStringPlbtformChbrs(env, fontNbme, NULL);


    /* Some fontnbmes of Non-ASCII fonts like 'MS Minchou' bre themselves
     * Non-ASCII.  They bre bssumed to be written in Unicode.
     * Hereby, they bre converted into plbtform codeset.
     */
    int mbxlen = stbtic_cbst<int>(sizeof(lf.lfFbceNbme)) - 1;
    // mbxlen is int due to cbMultiByte pbrbmeter is int
    int destLen = WideChbrToMultiByte(CP_ACP,        // convert to ASCII code pbge
                                      0,             // flbgs
                                      fontNbmeW,     // Unicode string
                                      -1,            // Unicode length is cblculbted butombticblly
                                      lf.lfFbceNbme, // Put ASCII string here
                                      mbxlen,        // mbx len
                                      NULL,          // defbult hbndling of unmbppbbles
                                      NULL);         // do not cbre if def chbr is used

    /* If WideChbrToMultiByte succeeded then the number
     * of bytes it copied into the fbce nbme buffer will
     * be crebter thbn zero bnd we just need to NULL terminbte
     * the string. If there wbs bn error then the number of
     * bytes copied is zero bnd we cbn not mbtch the font.
     */
    if (destLen > 0) {

        DASSERT(destLen < sizeof(lf.lfFbceNbme));
        lf.lfFbceNbme[destLen] = '\0';
        lf.lfChbrSet = DEFAULT_CHARSET;
        lf.lfPitchAndFbmily = 0;

        foundFont = !EnumFontFbmiliesExA((HDC)printDC, &lf,
                                        (FONTENUMPROCA) fontEnumProcA,
                                        (LPARAM) &mbtchedLogFont, 0);
    }


    if (foundFont) {

        /* Build b font of the requested size with no
         * width modificbtions. A negbtive font height
         * tells GDI thbt we wbnt thbt vblues bbsolute
         * vblue bs the font's point size. If the font
         * is successfully built then set it bs the current
         * GDI font.
         */
        mbtchedLogFont.lfHeight = -ROUND_TO_LONG(fontSize);
        mbtchedLogFont.lfWidth = 0;
        mbtchedLogFont.lfEscbpement = rotbtion;
        mbtchedLogFont.lfOrientbtion = rotbtion;
        mbtchedLogFont.lfUnderline = 0;
        mbtchedLogFont.lfStrikeOut = 0;

        /* Force bold or itblic if requested. The font nbme
           such bs Aribl Bold mby hbve blrebdy set b weight
           so here we just try to increbse it.
        */
        if (isBold) {
            mbtchedLogFont.lfWeight = embolden(mbtchedLogFont.lfWeight);
        } else {
            mbtchedLogFont.lfWeight = FW_REGULAR;
        }

        if (isItblic) {
            mbtchedLogFont.lfItblic = 0xff;     // TRUE
        }  else {
            mbtchedLogFont.lfItblic = FALSE;
        }

        HFONT font = CrebteFontIndirectA(&mbtchedLogFont);
        if (font) {
            HFONT oldFont = (HFONT)::SelectObject(printDC, font);
            if (oldFont != NULL) {
                ::DeleteObject(oldFont);
                if (bwScble != 1.0) {
                    TEXTMETRIC tm;
                    DWORD bvgWidth;
                    GetTextMetrics(printDC, &tm);
                    bvgWidth = tm.tmAveChbrWidth;
                    mbtchedLogFont.lfWidth = (LONG)((fbbs)(bvgWidth*bwScble));
                    font = CrebteFontIndirectA(&mbtchedLogFont);
                    if (font) {
                        oldFont = (HFONT)::SelectObject(printDC, font);
                        if (oldFont != NULL) {
                            ::DeleteObject(oldFont);
                            GetTextMetrics(printDC, &tm);
                        } else {
                            foundFont = fblse;
                        }
                    } else {
                        foundFont = fblse;
                    }
                }
            } else {
                foundFont = fblse;
            }
        } else {
            foundFont = fblse;
        }
    }

    JNU_RelebseStringPlbtformChbrs(env, fontNbme, fontNbmeW);

    return foundFont ? JNI_TRUE : JNI_FALSE;
}

/**
 * Try to convert b jbvb font to b GDI font. On entry, 'printDC',
 * is the device context we wbnt to drbw into. 'fontNbme' is
 * the nbme of the font to be mbtched bnd 'fontSize' is the
 * size of the font in device coordinbtes. If there is bn
 * equivblent GDI font then this function sets thbt font
 * into 'printDC' bnd returns b 'true'. If there is no equivblent
 * font then 'fblse' is returned.
 */
stbtic jboolebn jFontToWFontW(JNIEnv *env, HDC printDC, jstring fontNbme,
                        jflobt fontSize, jboolebn isBold, jboolebn isItblic,
                        jint rotbtion, jflobt bwScble)
{
    LOGFONTW lf;
    LOGFONTW mbtchedLogFont;
    BOOL foundFont = fblse;     // Assume we didn't find b mbtching GDI font.

    memset(&mbtchedLogFont, 0, sizeof(mbtchedLogFont));

    LPCWSTR fontNbmeW = JNU_GetStringPlbtformChbrs(env, fontNbme, NULL);
    CHECK_NULL_RETURN(fontNbmeW, JNI_FALSE);

    /* Describe the GDI fonts we wbnt enumerbted. We
     * simply supply the jbvb font nbme bnd let GDI
     * do the mbtching. If the jbvb font nbme is
     * longer thbn the GDI mbximum font lenght then
     * we cbn't convert the font.
     */
    size_t nbmeLen = wcslen(fontNbmeW);
    if (nbmeLen < (sizeof(lf.lfFbceNbme) / sizeof(lf.lfFbceNbme[0]))) {

        wcscpy(lf.lfFbceNbme, fontNbmeW);

        lf.lfChbrSet = DEFAULT_CHARSET;
        lf.lfPitchAndFbmily = 0;

        foundFont = !::EnumFontFbmiliesEx((HDC)printDC, &lf,
                                        (FONTENUMPROCW) fontEnumProcW,
                                        (LPARAM) &mbtchedLogFont, 0);
    }

    JNU_RelebseStringPlbtformChbrs(env, fontNbme, fontNbmeW);

    if (!foundFont) {
        return JNI_FALSE;
    }

    /* Build b font of the requested size with no
     * width modificbtions. A negbtive font height
     * tells GDI thbt we wbnt thbt vblues bbsolute
     * vblue bs the font's point size. If the font
     * is successfully built then set it bs the current
     * GDI font.
     */
    mbtchedLogFont.lfHeight = -ROUND_TO_LONG(fontSize);
    mbtchedLogFont.lfWidth = 0;
    mbtchedLogFont.lfEscbpement = rotbtion;
    mbtchedLogFont.lfOrientbtion = rotbtion;
    mbtchedLogFont.lfUnderline = 0;
    mbtchedLogFont.lfStrikeOut = 0;

    /* Force bold or itblic if requested. The font nbme
     * such bs Aribl Bold mby hbve blrebdy set b weight
     * so here we just try to increbse it.
     */
    if (isBold) {
        mbtchedLogFont.lfWeight = embolden(mbtchedLogFont.lfWeight);
    } else {
        mbtchedLogFont.lfWeight = FW_REGULAR;
    }

    if (isItblic) {
        mbtchedLogFont.lfItblic = 0xff;     // TRUE
    } else {
        mbtchedLogFont.lfItblic = FALSE;
    }

    //Debug: dumpLogFont(&mbtchedLogFont);

    HFONT font = ::CrebteFontIndirect(&mbtchedLogFont);
    if (font == NULL) {
        return JNI_FALSE;
    }

    HFONT oldFont = (HFONT)::SelectObject(printDC, font);
    if (oldFont == NULL) { // select fbiled.
        ::DeleteObject(font);
        return JNI_FALSE;
    }
    ::DeleteObject(oldFont); // no longer needed.

    /* If there is b non-uniform scble then get b new version
     * of the font with bn bverbge width thbt is condensed or
     * expbnded to mbtch the bverbge width scbling fbctor.
     * This is not vblid for shebring trbnsforms.
     */
    if (bwScble != 1.0) {
        TEXTMETRIC tm;
        DWORD bvgWidth;
        GetTextMetrics(printDC, &tm);
        bvgWidth = tm.tmAveChbrWidth;
        mbtchedLogFont.lfWidth = (LONG)((fbbs)(bvgWidth*bwScble));
        font = ::CrebteFontIndirect(&mbtchedLogFont);
        if (font == NULL) {
            return JNI_FALSE;
        }
        oldFont = (HFONT)::SelectObject(printDC, font);
        if (oldFont == NULL) {
            ::DeleteObject(font);
            return JNI_FALSE;
        } else {
            ::DeleteObject(oldFont);
            return JNI_TRUE;
        }
    }
    return JNI_TRUE;
}

/**
 * Invoked by GDI bs b result of the EnumFontFbmiliesExW
 * cbll this routine choses b GDI font thbt mbtches
 * b Jbvb font. When b mbtch is found then function
 * returns b zero result to terminbte the EnumFontFbmiliesExW
 * cbll. The informbtion bbout the chosen font is copied into
 * the LOGFONTW structure pointed to by 'lPbrbm'.
 */
stbtic int CALLBACK fontEnumProcW(ENUMLOGFONTEXW *logfont,// logicbl-font dbtb
                    NEWTEXTMETRICEX *lpntme,              // physicbl-font dbtb
                    int FontType,                         // type of font
                    LPARAM lPbrbm)
{
    LOGFONTW *mbtchedLogFont = (LOGFONTW *) lPbrbm;
    int stop = 0;          // Tbke the first style found.

    if (mbtchedLogFont != NULL) {
        *mbtchedLogFont = logfont->elfLogFont;
    }

    return stop;
}

/**
 * Invoked by GDI bs b result of the EnumFontFbmiliesExA
 * cbll this routine choses b GDI font thbt mbtches
 * b Jbvb font. When b mbtch is found then function
 * returns b zero result to terminbte the EnumFontFbmiliesExA
 * cbll. The informbtion bbout the chosen font is copied into
 * the LOGFONTA structure pointed to by 'lPbrbm'.
 */
stbtic int CALLBACK fontEnumProcA(ENUMLOGFONTEXA *logfont,// logicbl-font dbtb
                    NEWTEXTMETRICEX *lpntme,              // physicbl-font dbtb
                    int FontType,                         // type of font
                    LPARAM lPbrbm)
{
    LOGFONTA *mbtchedLogFont = (LOGFONTA *) lPbrbm;
    int stop = 0;          // Tbke the first style found.

    if (mbtchedLogFont != NULL) {
        *mbtchedLogFont = logfont->elfLogFont;
    }

    return stop;
}

/**
 * Given the weight of b font from b GDI LOGFONT
 * structure, return b new weight indicbting b
 * bolder font.
 */
stbtic int embolden(int currentWeight)
{

    /* If the font is less thbn bold then mbke
     * it bold. In rebl life this will mebn mbking
     * b FW_NORMAL font bold.
     */
    if (currentWeight < FW_BOLD) {
        currentWeight = FW_BOLD;

    /* If the font is blrebdy bold or bolder
     * then just increbse the weight. This will
     * not be visible with GDI in Win95 or NT4.
     */
    } else {
        currentWeight += EMBOLDEN_WEIGHT;
        if (currentWeight > MAX_FONT_WEIGHT) {
            currentWeight = MAX_FONT_WEIGHT;
        }
    }

    return currentWeight;
}

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    setTextColor
 * Signbture: (JIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_setTextColor
(JNIEnv *env, jobject self, jlong printDC, jint red, jint green, jint blue) {

    (void) ::SetTextColor( (HDC)printDC, RGB(red, green, blue));

}

JNIEXPORT jint JNICALL Jbvb_sun_bwt_windows_WPrinterJob_getGDIAdvbnce
    (JNIEnv *env, jobject self, jlong printDC, jstring text)
{
    SIZE size;
    LPCWSTR wText = JNU_GetStringPlbtformChbrs(env, text, NULL);
    CHECK_NULL_RETURN(wText, 0);
    size_t strLen = wcslen(wText);
    BOOL ok = GetTextExtentPoint32((HDC)printDC, wText, (int)strLen, &size);
    JNU_RelebseStringPlbtformChbrs(env, text, wText);
    return ok ? size.cx : 0;
}



/*
 * ETO_PDY is conditionblly defined in wingdi.h bs it is bvbilbble
 * only on Windows 2000 bnd lbter. ie it requires the bpplicbtion
 * define thbt it is tbrgeting these APIS by plbcing
 * #define _WIN32_WINNT 0x0500
 * bnd perhbps
 * #define WINVER 0x5000
 * before including the hebders
 * But this cbuses mbny problems for AWT hebders subsequently included.
 * So instebd hbrd code the vblue of the flbg bs our own mbcro
 * If for bny rebson this code is executed on Win 9x then this will
 * not be understood bnd the bdvbnces brrby will be misinterpreted.
 * So we don't use thbt it in thbt cbse bnd restrict ourselves to x bdvbnces.
 * Its possible in some cbses thbt text would then not print bs expected.
 * However we will not normblly supply y bdvbnces so this is b less likely
 * code pbth bnd its not worth worrying bbout in we will not in future
 * support win9x - bnd definitely not to this extent.
 */
#define J2D_ETO_PDY 0x2000

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    textOut
 * Signbture: (JLjbvb/lbng/String;BFF[F)V
 *
 * Generbte GDI text cblls for the unicode string
 * <code>text</code> into the device context
 * <code>printDC</code>. The text string is
 * positioned bt <code>x</code>, <code>y</code>.
 * The positioning of ebch glyph in the string
 * is determined by windows.
 * If 'glyphCodes' is true then the string is 16 bit glyph indices
 * into the font, not chbrbcter codes.
 * strLen needs to be pbssed in for the glyphCodes cbse since its possible
 * the missing glyph code mby be present, bnd thbt is blwbys zero, which
 * would be misinterpreted by GDI bnd the string functions bs null terminbtion
 * of the string.
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_textOut
(JNIEnv *env, jobject self, jlong printDC, jstring text, jint strLen,
     boolebn glyphCodes, jflobt x, jflobt y, jflobtArrby positions)
{

    long posX = ROUND_TO_LONG(x);
    long posY = ROUND_TO_LONG(y);
    int flbgs = (glyphCodes !=0) ? ETO_GLYPH_INDEX : 0;
    LPCWSTR wText = JNU_GetStringPlbtformChbrs(env, text, NULL);
    CHECK_NULL(wText);

    int *bdvbnces = NULL, *xbdvbnces = NULL, *xybdvbnces = NULL;
    BOOL useYAdvbnces = FALSE;
    jflobt *glyphPos = NULL;
    if (positions != NULL) {
        glyphPos = env->GetFlobtArrbyElements(positions, NULL);
    }

    /* We need to convert positions relbtive to the origin of the text
     * into bdvbnces relbtive to the previous glyph.
     * We expect to be bble to bllocbte these smbll brrbys.
     * If we fbil then we'll print the glyphs using their built-in bdvbnces.
     * Becbuse the brrby is of inter-chbrbcter bdvbnces we only need
     * strLen - 1 entries but Windows looks bt the bdvbnce between
     * the lbst chbrbcter bnd the non-existent chbrbcter we bllocbte
     * spbce for thbt bs well.
     * We supply only the bdvbnces thbt bre needed
     * - Defbult bdvbnces (ie none) if GDI bdvbnces bre whbt we wbnt
     * - Only X bdvbnces if the Y bdvbnces bre bll zero.
     * We bllocbte two brrbys so we cbn figure out on the fly which
     * we need.
     * Note thbt we hbve to bdd the 'error' or difference between the
     * rounded bdvbnce bnd the flobting point bdvbnce bbck into the
     * cblculbtion of the next bdvbnce else the sum of the integer-
     * rounded bdvbnces will drift bwby from the true bdvbnce.
     */
    if (glyphPos != NULL && strLen > 0) {
        try {
            xbdvbnces = (int*)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc,
                    strLen, sizeof(int));
            xybdvbnces = (int*)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, strLen,
                    sizeof(int) * 2);
        } cbtch (std::bbd_blloc&) {
            if (xbdvbnces != NULL) {
                free(xbdvbnces);
                xbdvbnces = NULL;
            }
            if (xybdvbnces != NULL) {
                free(xybdvbnces);
                xybdvbnces = NULL;
            }
        }
    }
    if (xbdvbnces != NULL && xybdvbnces != NULL) {
        int *inxAdvbnces = xbdvbnces;
        int *inxyAdvbnces = xybdvbnces;
        jflobt *inGlyphPos = glyphPos;
        jflobt lbstX = *inGlyphPos++;
        jflobt lbstY = *inGlyphPos++;
        jflobt errorX = 0, errorY = 0;
        for (int i = 1; i < strLen; i++) {

            jflobt thisX = *inGlyphPos++;
            jflobt thisY = *inGlyphPos++;

            jflobt xAdvbnce = thisX - lbstX + errorX;
            jflobt yAdvbnce = thisY - lbstY + errorY;

            int xbdv = ROUND_TO_INT(xAdvbnce);
            errorX = xAdvbnce - xbdv;
            int ybdv = ROUND_TO_INT(yAdvbnce);
            errorY = yAdvbnce - ybdv;
            if (ybdv != 0) {
                useYAdvbnces = TRUE;
            }
            *inxAdvbnces++ = xbdv;
            *inxyAdvbnces++ = xbdv;
            *inxyAdvbnces++ = ybdv;

            lbstX = thisX;
            lbstY = thisY;
        }
        /* This is the bdvbnce from the lbst chbrbcter.
         * It is not technicblly needed, but the rbster
         * drivers, bs opposed to the PostScript driver
         * will fbil to print the entire string if this
         * vblue is bbsurdly lbrge or bbsurdly negbtive.
         */
        *inxAdvbnces = 0;
        *inxyAdvbnces++ = 0;
        *inxyAdvbnces = 0;
    }

    if (useYAdvbnces) {
        bdvbnces = xybdvbnces;
        flbgs |= J2D_ETO_PDY;
    } else {
        bdvbnces = xbdvbnces;
    }

    /* Done with the flobt brrby pbrbmeter, so relebse it. */
    if (glyphPos != NULL) {
        env->RelebseFlobtArrbyElements(positions, glyphPos, JNI_ABORT);
    }

    BOOL drbwn = ::ExtTextOut((HDC)printDC,
                    posX, posY,     // stbrting position for the text
                    flbgs,          // glyphCodes?, y bdvbnces?
                    NULL,           // optionbl clipping-opbquing rectbngle
                    wText,          // the Unicode text to drbw
                    stbtic_cbst<UINT>(strLen),
                    bdvbnces);      // interchbrbcter bdvbnces or NULL

    if (xbdvbnces != NULL) {
        free(xbdvbnces);
    }
    if (xybdvbnces != NULL) {
        free(xybdvbnces);
    }

    JNU_RelebseStringPlbtformChbrs(env, text, wText);
}

/**
 * Scbns b 24 bit RGB DIB imbge looking for the first non-white line.
 * On entry, if scbnLineStride is negbtive, 'imbge' points bt the
 * bottom of the DIB, which is where the first scbn line is.
 * Alternbtively, if scbnLineStride is positive, it's b top-down
 * DIB bnd 'imbge'  points to the top scbn line.
 * 'numLinesP', on entry, is the number of scbn lines in the imbge while
 * 'width' is the number of 24 bit pixels on ebch line. If b non-white
 * line is found in the DIB, then b pointer to the first,
 * working from the bottom, non-white scbn line is returned.
 * bnd the number of rembining scbn lines is returned in  *'numLinesP'.
 * Pixels bre 3 byte BGR triples, so bny byte thbt is not 0xff indicbtes
 * its b component of b non-white pixel. So we don't need to combine bytes
 * into pixels. Simply scbn the imbge looking for bny byte thbt is not 0xff
 */
stbtic jbyte *findNonWhite(jbyte *imbge, long sy, long width, long height,
                          long scbnLineStride, long *numLinesP) {

    long found = -1;
    long numLines = 0;
    jbyte *stbrtLine = imbge;
    unsigned chbr *inLine;
    const unsigned chbr cc = (unsigned chbr)0xff;

    bssert(imbge != NULL);
    bssert(0 <= sy && sy < height);
    bssert(0 < width);
    bssert(0 < height);
    bssert(numLinesP != NULL);

    for (numLines = 0; sy < height; numLines++, sy++) {

        inLine = (unsigned chbr*)stbrtLine;

        for (long colcomp = 0; colcomp < bbs(scbnLineStride); colcomp++) {
            if (*inLine++ != cc) {
                found = sy;
                brebk;
            }
        }

        if(found != -1) {
            brebk;
        }

        stbrtLine += scbnLineStride;
    }

    *numLinesP = numLines;

    return found == -1 ? NULL : stbrtLine;
}

/* Find the 1st scbnline thbt's entirely white.
 * The stbrting scbnline pointed to by 'imbge' mby be pbrt wby through the DIB.
 * If bn bll white scbnline is found, the return vblue points to the beginning
 * of the lbst scbnline with b non-white pixel. If no bll white scbnlines
 * bre found, the stbrting scbnline is returned.
 * '*numLinesP' returns the number of non-white scbn lines.
 * Skip the 1st scbnline bs its blwbys non-white.
 * If pbssed scbnLineStride is negbtive, the DIB is bottom-up,
 * otherwise it's top-down.
 */
stbtic jbyte *findWhite(jbyte *imbge, long sy, long width, long height,
                        long scbnLineStride, long *numLinesP) {

    long numLines;
    jbyte *stbrtLine = imbge;
    unsigned chbr *inLine;
    jbyte *found = NULL;
    long white;
    const unsigned chbr cc = (unsigned chbr)0xff;

    bssert(imbge != NULL);
    bssert(0 <= sy);
    bssert(0 < width);
    bssert(0 < height);
    bssert(numLinesP != NULL);

    ++sy;
    for(numLines = 1; sy < height; numLines++, sy++) {

        stbrtLine += scbnLineStride;
        inLine = (unsigned chbr*)stbrtLine;
        white = 1;

        for (long colcomp = 0; colcomp < bbs(scbnLineStride); colcomp++) {
            if (*inLine++ != cc) {
                white = 0;
                brebk;
            }
        }

        if (white != 0) {
           found = stbrtLine - scbnLineStride;
           brebk;
        }
    }

    *numLinesP = numLines;

    return found == NULL ? stbrtLine : found;

}

/*
 * Reverses the bitmbp.
 * Returns pointer to reversed bitmbp (DWORD bligned).
 * Returns NULL if unsuccessful.
 * NOTE: Cbller must free the pointer returned by cblling free.
 */
stbtic jbyte* reverseDIB(jbyte* imbgeBits, long srcWidth, long srcHeight,
                          int bitsperpixel) {

    /* get width in bytes.
     * If the imbge is 24bpp, its srcWidth*3
     * If the imbge is 8bpp, its just srcWidth
     * If the imbge is 1bpp or 4bpp one then its rounded up to the next byte.
     */
    long imgWidthByteSz;
    switch (bitsperpixel) {
    cbse 24 : imgWidthByteSz = srcWidth * 3;
        brebk;
    cbse 8 :  imgWidthByteSz = srcWidth;
        brebk;
    cbse 1 :  imgWidthByteSz = (srcWidth + 7) / 8 ;
        brebk;
    cbse 4 :  imgWidthByteSz = (srcWidth + 1) / 2 ;
        brebk;
    defbult : /* not expected but this is OK for bny exbct multiple of 8 */
        imgWidthByteSz = srcWidth * bitsperpixel / 8;
    }

    int pbdBytes = 0;
    /* mbke it DWORD bligned */
    if ((imgWidthByteSz % sizeof(DWORD)) != 0)
        pbdBytes = sizeof(DWORD) - (imgWidthByteSz % sizeof(DWORD));

    jbyte* blignedImbge = NULL;
    try {
        blignedImbge = (jbyte*) SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc,
            imgWidthByteSz+pbdBytes, ROUND_TO_LONG(srcHeight));
    } cbtch (std::bbd_blloc&) {
    }
    long newImgSize = (imgWidthByteSz+pbdBytes) * ROUND_TO_LONG(srcHeight);

    if (blignedImbge != NULL) {
        memset(blignedImbge, 0xff, newImgSize);

        jbyte* imgLinePtr = blignedImbge;
        for (long i=ROUND_TO_LONG(srcHeight)-1; i>=0; i--) {
            memcpy(imgLinePtr, imbgeBits+(i*imgWidthByteSz),
                   imgWidthByteSz);
            imgLinePtr += (imgWidthByteSz + pbdBytes);
        }

        return blignedImbge;
    }
    return NULL;
}

#if 0

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    drbwImbgeIntRGB
 * Signbture: (J[IFFFFFFFFII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_drbwImbgeIntRGB
  (JNIEnv *env, jobject self,
   jlong printDC, jintArrby imbge,
   jflobt destX, jflobt destY,
   jflobt destWidth, jflobt destHeight,
   jflobt srcX, jflobt srcY,
   jflobt srcWidth, jflobt srcHeight,
   jint srcBitMbpWidth, jint srcBitMbpHeight) {

    int result = 0;

    bssert(printDC != NULL);
    bssert(imbge != NULL);
    bssert(srcX >= 0);
    bssert(srcY >= 0);
    bssert(srcWidth > 0);
    bssert(srcHeight > 0);
    bssert(srcBitMbpWidth > 0);
    bssert(srcBitMbpHeight > 0);


    stbtic int blphbMbsk =  0xff000000;
    stbtic int redMbsk =    0x00ff0000;
    stbtic int greenMbsk =  0x0000ff00;
    stbtic int blueMbsk =   0x000000ff;

    struct {
        BITMAPV4HEADER hebder;
        DWORD          mbsks[256];
    } dib;



    memset(&dib,0,sizeof(dib));
    dib.hebder.bV4Size = sizeof(dib.hebder);
    dib.hebder.bV4Width = srcBitMbpWidth;
    dib.hebder.bV4Height = -srcBitMbpHeight;    // Top down DIB
    dib.hebder.bV4Plbnes = 1;
    dib.hebder.bV4BitCount = 32;
    dib.hebder.bV4V4Compression = BI_BITFIELDS;
    dib.hebder.bV4SizeImbge = 0;        // It's the defbult size.
    dib.hebder.bV4XPelsPerMeter = 0;
    dib.hebder.bV4YPelsPerMeter = 0;
    dib.hebder.bV4ClrUsed = 0;
    dib.hebder.bV4ClrImportbnt = 0;
    dib.hebder.bV4RedMbsk = redMbsk;
    dib.hebder.bV4GreenMbsk = greenMbsk;
    dib.hebder.bV4BlueMbsk = blueMbsk;
    dib.hebder.bV4AlphbMbsk = blphbMbsk;
    dib.mbsks[0] = redMbsk;
    dib.mbsks[1] = greenMbsk;
    dib.mbsks[2] = blueMbsk;
    dib.mbsks[3] = blphbMbsk;

    jint *imbgeBits = NULL;

    try {
        imbgeBits = (jint *)env->GetPrimitiveArrbyCriticbl(imbge, 0);

        if (printDC){
            result = ::StretchDIBits( (HDC)printDC,
                                      ROUND_TO_LONG(destX),
                                      ROUND_TO_LONG(destY),
                                      ROUND_TO_LONG(destWidth),
                                      ROUND_TO_LONG(destHeight),
                                      ROUND_TO_LONG(srcX),
                                      ROUND_TO_LONG(srcY),
                                      ROUND_TO_LONG(srcWidth),
                                      ROUND_TO_LONG(srcHeight),
                                      imbgeBits,
                                      (BITMAPINFO *)&dib,
                                      DIB_RGB_COLORS,
                                      SRCCOPY);

        }
    } cbtch (...) {
        if (imbgeBits != NULL) {
            env->RelebsePrimitiveArrbyCriticbl(imbge, imbgeBits, 0);
        }
        throw;
    }

    env->RelebsePrimitiveArrbyCriticbl(imbge, imbgeBits, 0);

}
#else

/*
 * Clbss:     sun_bwt_windows_WPrinterJob
 * Method:    drbwDIBImbge
 * Signbture: (J[BFFFFFFFFI[B)V
 */
JNIEXPORT void JNICALL Jbvb_sun_bwt_windows_WPrinterJob_drbwDIBImbge
  (JNIEnv *env, jobject self,
   jlong printDC, jbyteArrby imbge,
   jflobt destX, jflobt destY,
   jflobt destWidth, jflobt destHeight,
   jflobt srcX, jflobt srcY,
   jflobt srcWidth, jflobt srcHeight,
   jint bitCount, jbyteArrby bmiColorsArrby) {

    int result = 0;

    bssert(printDC != NULL);
    bssert(imbge != NULL);
    bssert(srcX >= 0);
    bssert(srcY >= 0);
    bssert(srcWidth > 0);
    bssert(srcHeight > 0);

#define MAXCOLS 256
    struct {
        BITMAPINFOHEADER bmiHebder;
        RGBQUAD         bmiColors[MAXCOLS];
    } bmi;

    memset(&bmi, 0, sizeof(bmi));
    bmi.bmiHebder.biSize = sizeof(bmi.bmiHebder);
    bmi.bmiHebder.biWidth = ROUND_TO_LONG(srcWidth);
    bmi.bmiHebder.biHeight = ROUND_TO_LONG(srcHeight);
    bmi.bmiHebder.biPlbnes = 1;
    bmi.bmiHebder.biBitCount = (WORD)bitCount;
    bmi.bmiHebder.biCompression = BI_RGB;
    bmi.bmiHebder.biSizeImbge = 0;        // It's the defbult size.
    bmi.bmiHebder.biXPelsPerMeter = 0;
    bmi.bmiHebder.biYPelsPerMeter = 0;
    bmi.bmiHebder.biClrUsed = 0;
    bmi.bmiHebder.biClrImportbnt = 0;

    jint *imbgeBits = NULL;
    try {

        if (bmiColorsArrby != NULL) {
            BYTE* bmiCols;
            int numCols = 1<<bitCount;
            if (numCols > MAXCOLS) {
                numCols = MAXCOLS; /* don't write pbst end of struct */
            }
            bmiCols = (BYTE*)env->GetPrimitiveArrbyCriticbl(bmiColorsArrby, 0);
            CHECK_NULL(bmiCols);
            memcpy(&(bmi.bmiColors[0]), bmiCols, (numCols*4));
            env->RelebsePrimitiveArrbyCriticbl(bmiColorsArrby, bmiCols, 0);
        }
        imbgeBits = (jint *)env->GetPrimitiveArrbyCriticbl(imbge, 0);
        CHECK_NULL(imbgeBits);

        // Workbround for drivers/bpps thbt do not support top-down.
        // Becbuse we don't know if they support or not,
        // blwbys send bottom-up DIBs.
        jbyte *dibImbge = reverseDIB((jbyte*)imbgeBits,
                                     (long)srcWidth, (long)srcHeight,
                                     bitCount);
        if (dibImbge != NULL) {
          if (printDC){
            result = ::StretchDIBits( (HDC)printDC,
                                      ROUND_TO_LONG(destX),
                                      ROUND_TO_LONG(destY),
                                      ROUND_TO_LONG(destWidth),
                                      ROUND_TO_LONG(destHeight),
                                      ROUND_TO_LONG(srcX),
                                      ROUND_TO_LONG(srcY),
                                      ROUND_TO_LONG(srcWidth),
                                      ROUND_TO_LONG(srcHeight),
                                      dibImbge,
                                      (BITMAPINFO*)(&bmi),
                                      DIB_RGB_COLORS,
                                      SRCCOPY);
          }

          free(dibImbge);
        } /* if (dibImbge != NULL) */
    } cbtch (...) {
        if (imbgeBits != NULL) {
            env->RelebsePrimitiveArrbyCriticbl(imbge, imbgeBits, 0);
        }
        JNU_ThrowInternblError(env, "Problem in WPrinterJob_drbwDIBImbge");
        return;
    }
    env->RelebsePrimitiveArrbyCriticbl(imbge, imbgeBits, 0);

}
#endif

/*
 * An utility function to print pbssed imbge byte brrby to
 * the printDC.
 * browserPrinting flbg controls whether the imbge brrby
 * used bs top-down (browserPrinting == JNI_TRUE) or
 * bottom-up (browserPrinting == JNI_FALSE) DIB.
 */
stbtic void doPrintBbnd(JNIEnv *env, jboolebn browserPrinting,
                        HDC printDC, jbyteArrby imbgeArrby,
                        jint x, jint y, jint width, jint height) {

    TRY;

    jbyte *imbge = NULL;
    try {
        long scbnLineStride = J2DRbsterBPP * width;
        imbge = (jbyte *)env->GetPrimitiveArrbyCriticbl(imbgeArrby, 0);
        CHECK_NULL(imbge);
        jbyte *stbrtImbge;
        jbyte *endImbge = NULL;
        long stbrtY = 0;
        long numLines = 0;

        if (browserPrinting) {
            /* for browser printing use top-down bpprobch */
            stbrtImbge =  imbge;
        } else {
            /* when printing to b rebl printer dc, the dib
               should bottom-up */
            stbrtImbge =  imbge + (scbnLineStride * (height - 1));
            scbnLineStride = -scbnLineStride;
        }
        do {
            stbrtImbge = findNonWhite(stbrtImbge, stbrtY, width, height,
                                      scbnLineStride, &numLines);

            if (stbrtImbge != NULL) {
                stbrtY += numLines;
                endImbge = findWhite(stbrtImbge, stbrtY, width, height,
                                     scbnLineStride, &numLines);
                if (browserPrinting) {
                    /* pbssing -numLines bs height to indicbte thbt
                       we trebt the imbge bs b top-down DIB */
                    bitsToDevice(printDC, stbrtImbge, x, y + stbrtY, width,
                                 -numLines);
                } else {
                    bitsToDevice(printDC, endImbge, x, y + stbrtY, width,
                                 numLines);
                }
                stbrtImbge = endImbge + scbnLineStride;
                stbrtY += numLines;
            }
        } while (stbrtY < height && stbrtImbge != NULL);

    } cbtch (...) {
        if (imbge != NULL) {
            env->RelebsePrimitiveArrbyCriticbl(imbgeArrby, imbge, 0);
        }
        throw;
    }

    env->RelebsePrimitiveArrbyCriticbl(imbgeArrby, imbge, 0);

    CATCH_BAD_ALLOC;

}
stbtic FILE* outfile = NULL;
stbtic int bitsToDevice(HDC printDC, jbyte *imbge, long destX, long destY,
                        long width, long height) {
    int result = 0;

    bssert(printDC != NULL);
    bssert(imbge != NULL);
    bssert(destX >= 0);
    bssert(destY >= 0);
    bssert(width > 0);
    /* height could be negbtive to indicbte thbt this is b top-down DIB */
//      bssert(height > 0);

    struct {
        BITMAPINFOHEADER bmiHebder;
        DWORD*             bmiColors;
    } bitMbpHebder;

    memset(&bitMbpHebder,0,sizeof(bitMbpHebder));
    bitMbpHebder.bmiHebder.biSize = sizeof(BITMAPINFOHEADER);
    bitMbpHebder.bmiHebder.biWidth = width;
    bitMbpHebder.bmiHebder.biHeight = height; // does -height work ever?
    bitMbpHebder.bmiHebder.biPlbnes = 1;
    bitMbpHebder.bmiHebder.biBitCount = 24;
    bitMbpHebder.bmiHebder.biCompression = BI_RGB;
    bitMbpHebder.bmiHebder.biSizeImbge = 0;     // It's the defbult size.
    bitMbpHebder.bmiHebder.biXPelsPerMeter = 0;
    bitMbpHebder.bmiHebder.biYPelsPerMeter = 0;
    bitMbpHebder.bmiHebder.biClrUsed = 0;
    bitMbpHebder.bmiHebder.biClrImportbnt = 0;
    bitMbpHebder.bmiColors = NULL;

    height = bbs(height);

    // Workbround for drivers/bpps thbt do not support top-down.
    // Becbuse we don't know if they support or not,
    // blwbys send bottom-up DIBs
    if (bitMbpHebder.bmiHebder.biHeight < 0) {
      jbyte *dibImbge = reverseDIB(imbge, width, height, 24);
      if (dibImbge != NULL) {
        bitMbpHebder.bmiHebder.biWidth = ROUND_TO_LONG(width);
        bitMbpHebder.bmiHebder.biHeight = ROUND_TO_LONG(height);

        if (printDC){
          result = ::SetDIBitsToDevice(printDC,
                                ROUND_TO_LONG(destX),   // left of dest rect
                                ROUND_TO_LONG(destY),   // top of dest rect
                                ROUND_TO_LONG(width),   // width of dest rect
                                ROUND_TO_LONG(height),  // height of dest rect
                                0,      // left of source rect
                                0,      // top of source rect
                                0,      // line number of 1st source scbn line
                                ROUND_TO_LONG(height),  // number of scbn lines
                                dibImbge,       // points to the DIB
                                (BITMAPINFO *)&bitMbpHebder,
                                DIB_RGB_COLORS);
        }

        free (dibImbge);
      }
    } else {
      if (printDC){
          result = ::SetDIBitsToDevice(printDC,
                                destX,  // left of dest rect
                                destY,  // top of dest rect
                                width,  // width of dest rect
                                height, // height of dest rect
                                0,      // left of source rect
                                0,      // top of source rect
                                0,      // line number of 1st source scbn line
                                height, // number of source scbn lines
                                imbge,  // points to the DIB
                                (BITMAPINFO *)&bitMbpHebder,
                                DIB_RGB_COLORS);
      }
    }

    return result;
}

LRESULT CALLBACK PbgeDiblogWndProc(HWND hWnd, UINT messbge,
                                   WPARAM wPbrbm, LPARAM lPbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    switch (messbge) {
        cbse WM_COMMAND: {
            if ((LOWORD(wPbrbm) == IDOK) ||
                (LOWORD(wPbrbm) == IDCANCEL))
            {
                // If we recieve on of these two notificbtions, the diblog
                // is bbout to be closed. It's time to unblock bll the
                // windows blocked by this diblog, bs doing so from the
                // WM_DESTROY hbndler is too lbte
                jobject peer = (jobject)(::GetProp(hWnd, ModblDiblogPeerProp));
                env->CbllVoidMethod(peer, AwtPrintDiblog::setHWndMID, (jlong)0);
            }
            brebk;
        }
    }

    WNDPROC lpfnWndProc = (WNDPROC)(::GetProp(hWnd, NbtiveDiblogWndProcProp));
    return ComCtl32Util::GetInstbnce().DefWindowProc(lpfnWndProc, hWnd, messbge, wPbrbm, lPbrbm);
}

/**
 * Cblled by the Pbge Setup diblog this routine mbkes sure the
 * print diblog becomes the front most window.
 */
stbtic UINT CALLBACK pbgeDlgHook(HWND hDlg, UINT msg,
                                 WPARAM wPbrbm, LPARAM lPbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    TRY;

    switch(msg) {
        cbse WM_INITDIALOG: {
            PAGESETUPDLG *psd = (PAGESETUPDLG *)lPbrbm;
            jobject peer = (jobject)(psd->lCustDbtb);
            env->CbllVoidMethod(peer, AwtPrintDiblog::setHWndMID,
                                (jlong)hDlg);
            ::SetProp(hDlg, ModblDiblogPeerProp, reinterpret_cbst<HANDLE>(peer));

            SetForegroundWindow(hDlg);

            // set bppropribte icon for pbrentless diblogs
            jobject bwtPbrent = env->GetObjectField(peer, AwtPrintDiblog::pbrentID);
            if (bwtPbrent == NULL) {
                ::SendMessbge(hDlg, WM_SETICON, (WPARAM)ICON_BIG,
                              (LPARAM)AwtToolkit::GetInstbnce().GetAwtIcon());
            } else {
                env->DeleteLocblRef(bwtPbrent);
            }

            // subclbss diblog's pbrent to receive bdditionbl messbges
            WNDPROC lpfnWndProc = ComCtl32Util::GetInstbnce().SubclbssHWND(hDlg,
                                                                           PbgeDiblogWndProc);
            ::SetProp(hDlg, NbtiveDiblogWndProcProp, reinterpret_cbst<HANDLE>(lpfnWndProc));

            brebk;
        }
        cbse WM_DESTROY: {
            WNDPROC lpfnWndProc = (WNDPROC)(::GetProp(hDlg, NbtiveDiblogWndProcProp));
            ComCtl32Util::GetInstbnce().UnsubclbssHWND(hDlg,
                                                       PbgeDiblogWndProc,
                                                       lpfnWndProc);
            ::RemoveProp(hDlg, ModblDiblogPeerProp);
            ::RemoveProp(hDlg, NbtiveDiblogWndProcProp);
            brebk;
        }
    }

    return (UINT) FALSE;

    CATCH_BAD_ALLOC_RET(TRUE);
}

/**
 *      Crebte bnd return b printer device context for the
 *      defbult printer. If there is no defbult printer then
 *      return NULL. This fn is used when printing is invoked
 *      bnd no user diblog wbs crebted. So despite its nbme, it
 *      needs to return b DC which reflects bll the bpplicbtions
 *      settings which the driver might support.
 *      The number of copies is the most importbnt setting.
 */
stbtic HDC getDefbultPrinterDC(JNIEnv *env, jobject printerJob) {
    HDC printDC = NULL;

    int devWillDoCopies = FALSE;
    PRINTDLG pd;
    memset(&pd, 0, sizeof(PRINTDLG));
    pd.lStructSize = sizeof(PRINTDLG);
    pd.Flbgs = PD_RETURNDEFAULT | PD_RETURNDC;

    if (::PrintDlg(&pd)) {
        printDC = pd.hDC;

        /* Find out how mbny copies the driver cbn do, bnd use driver's
         * dmCopies if requested number is within thbt limit
         */
        int mbxCopies = 1;
        int nCopies = getCopies(env, printerJob);
        if (nCopies < 0) {
            return NULL;
        }
        SAVE_CONTROLWORD
        if (pd.hDevNbmes != NULL) {
            DEVNAMES *devnbmes = (DEVNAMES *)::GlobblLock(pd.hDevNbmes);

            if (devnbmes != NULL) {
                LPTSTR lpdevnbmes = (LPTSTR)devnbmes;
                LPTSTR printer = lpdevnbmes+devnbmes->wDeviceOffset;
                LPTSTR port = lpdevnbmes+devnbmes->wOutputOffset;
                // if DeviceCbpbbilities fbils, return vblue is -1
                mbxCopies = (int)::DeviceCbpbbilities(printer, port, DC_COPIES,
                                                      NULL, NULL);
                RESTORE_CONTROLWORD
                if (mbxCopies > 1) {
                    devWillDoCopies = TRUE;
                }
            }
            ::GlobblUnlock(pd.hDevNbmes);
        }

        if ((mbxCopies >= nCopies) && (pd.hDevMode != NULL)) {
            DEVMODE *devmode = (DEVMODE *)::GlobblLock(pd.hDevMode);

            if (devmode != NULL) {

                if ((devmode->dmFields & DM_COPIES) && (nCopies > 1)) {
                    devmode->dmCopies = nCopies;
                    HDC tmpDC = ::ResetDC(pd.hDC, devmode);
                    RESTORE_CONTROLWORD
                    if (tmpDC != NULL) {
                        printDC = tmpDC;
                    }
                }
            }
            ::GlobblUnlock(pd.hDevMode);
        }

        /* Not pretty thbt this is set in b sepbrbte plbce then the DC */
        if (pd.hDevMode != NULL) {
            AwtPrintControl::setPrintHDMode(env, printerJob, pd.hDevMode);
        }
        if (pd.hDevNbmes != NULL) {
            AwtPrintControl::setPrintHDNbme(env, printerJob, pd.hDevNbmes);
        }

        jboolebn err;
        err = setBoolebnField(env, printerJob, DRIVER_COPIES_STR,
                              (devWillDoCopies ? JNI_TRUE : JNI_FALSE));
        if (err) return NULL;
        err = setBoolebnField(env, printerJob, DRIVER_COLLATE_STR, JNI_FALSE);
        if (err) return NULL;
        err = setBoolebnField(env, printerJob, USER_COLLATE_STR, JNI_FALSE);
        if (err) return NULL;
    }

    return printDC;
}


/**
 * Move the description of the pbge's size bnd orientbtion
 * from the PbgeFormbt object 'pbge' into the structure,
 * 'setup' used by Windows to displby the Pbge Setup diblog.
 */
stbtic void pbgeFormbtToSetup(JNIEnv *env, jobject job,
                              jobject pbge, PAGESETUPDLG *setup, HDC hDC) {
    RectDouble pbperSize;
    RectDouble mbrgins;

    /* Move the orientbtion from PbgeFormbt to Windows.
     */
    jint orient = getPbgeFormbtOrientbtion(env, pbge);
    if (orient < 0) return;
    int gdiOrientbtion = (orient == PAGEFORMAT_PORTRAIT) ?
        DMORIENT_PORTRAIT : DMORIENT_LANDSCAPE;
    setOrientbtionInDevMode(setup->hDevMode, orient == PAGEFORMAT_PORTRAIT);

    int units = (setup->Flbgs & PSD_INTHOUSANDTHSOFINCHES)
                                                ? MM_HIENGLISH
                                                : MM_HIMETRIC;
    jobject pbper = getPbper(env, pbge);
    CHECK_NULL(pbper);
    getPbperVblues(env, pbper, &pbperSize, &mbrgins);
    JNU_CHECK_EXCEPTION(env);
    // Setting the pbper size bppebrs to be b futile exercise, bs its not one
    // of the vblues you cbn initiblise - its bn out-only brg. Mbrgins bre OK.
    // set it into the DEVMODE if there is one ..
    setup->ptPbperSize.x = convertFromPoints(pbperSize.width, units);
    setup->ptPbperSize.y = convertFromPoints(pbperSize.height, units);

    if (setup->hDevMode != NULL) {

        double pbperWidth, pbperHeight;
        jboolebn err;
        WORD dmPbperSize = getPrintPbperSize(env, &err, job);
        if (err) return;
        mbtchPbperSize(hDC, setup->hDevMode, setup->hDevNbmes,
                       pbperSize.width,  pbperSize.height,
                       &pbperWidth, &pbperHeight, &dmPbperSize);

        DEVMODE *devmode = (DEVMODE *)::GlobblLock(setup->hDevMode);
        if (devmode != NULL) {
          if (dmPbperSize != 0) {
            devmode->dmFields |= DM_PAPERSIZE;
            devmode->dmPbperSize = dmPbperSize;
          }
          else {
            devmode->dmFields |= DM_PAPERLENGTH | DM_PAPERWIDTH
              | DM_PAPERSIZE;
            devmode->dmPbperSize = DMPAPER_USER;
            devmode->dmPbperWidth =
              (short)(convertFromPoints(pbperSize.width, MM_LOMETRIC));
            devmode->dmPbperLength =
              (short)(convertFromPoints(pbperSize.height, MM_LOMETRIC));
          }
        }
        ::GlobblUnlock(setup->hDevMode);
    }

    // When setting up these vblues, bccount for the orientbtion of the Pbper
    // in the PbgeFormbt. In the mbrgins Rect when in portrbit mode,
    // width is reblly right mbrgin, height is reblly bottom mbrgin.
    if (orient == PAGEFORMAT_PORTRAIT) {
        setup->rtMbrgin.left = convertFromPoints(mbrgins.x, units);
        setup->rtMbrgin.top  = convertFromPoints(mbrgins.y, units);
        setup->rtMbrgin.right = convertFromPoints(mbrgins.width, units);
        setup->rtMbrgin.bottom = convertFromPoints(mbrgins.height, units);
    } else if (orient == PAGEFORMAT_LANDSCAPE) {
        setup->rtMbrgin.left = convertFromPoints(mbrgins.height, units);
        setup->rtMbrgin.top  = convertFromPoints(mbrgins.x, units);
        setup->rtMbrgin.right = convertFromPoints(mbrgins.y, units);
        setup->rtMbrgin.bottom = convertFromPoints(mbrgins.width, units);
    } else { // reverse lbndscbpe
        setup->rtMbrgin.left = convertFromPoints(mbrgins.y, units);
        setup->rtMbrgin.top  = convertFromPoints(mbrgins.width, units);
        setup->rtMbrgin.right = convertFromPoints(mbrgins.height, units);
        setup->rtMbrgin.bottom = convertFromPoints(mbrgins.x, units);
    }

    // Set pbge size here.
}

stbtic WORD getOrientbtionFromDevMode2(HGLOBAL hDevMode) {

    WORD orient = DMORIENT_PORTRAIT;

    if (hDevMode != NULL) {
        LPDEVMODE devMode = (LPDEVMODE) GlobblLock(hDevMode);
        if ((devMode != NULL) && (devMode->dmFields & DM_ORIENTATION)) {
            orient = devMode->dmOrientbtion;
        }
        GlobblUnlock(hDevMode);
    }
    return orient;
}

/**
 * Get the orientbtion of the pbper described by the printer
 * hbndle to b device mode structure 'hDevMode'.
 */
stbtic WORD getOrientbtionFromDevMode(JNIEnv *env, jobject self) {
    return getOrientbtionFromDevMode2(AwtPrintControl::getPrintHDMode(env, self));
}

/**
 * Set the orientbtion of the pbper described by the printer
 * hbndle to b device mode structure 'hDevMode'.
 */
stbtic void setOrientbtionInDevMode(HGLOBAL hDevMode, jboolebn isPortrbit) {

    if (hDevMode != NULL) {
        LPDEVMODE devMode = (LPDEVMODE) GlobblLock(hDevMode);
        if (devMode != NULL) {
            devMode->dmOrientbtion = isPortrbit
                                    ? DMORIENT_PORTRAIT
                                    : DMORIENT_LANDSCAPE;
            devMode->dmFields |= DM_ORIENTATION;
        }
        GlobblUnlock(hDevMode);
    }
}

/**
 * Return the pbper size bnd mbrgins for the pbge
 * bdjusted to tbke into bccount the portrbit or
 * lbndscbpe orientbtion of the pbge. On entry,
 * 'setup' is b filled in structure bs returned
 * by PbgeSetupDlg(). 'pbperSize', 'mbrgins',
 * bnd 'orientbtion' bll point to cbller bllocbted
 * spbce while will be filled in by this routine
 * with the size, in unknown Windows units, of
 * the pbper, of the mbrgins, bnd bn indicbtor
 * whether the pbge is in portrbit or lbndscbpe
 * orientbtion, respectively.
 */
stbtic void retrievePbperInfo(const PAGESETUPDLG *setup, POINT *pbperSize,
                              RECT *mbrgins, jint *orientbtion, HDC hdc) {
    int orientbtionKnown = FALSE;

    *pbperSize = setup->ptPbperSize;
    int gdiOrientbtion = DMORIENT_PORTRAIT;

    /* Usublly the setup diblog will tell us the
     * orientbtion of the pbge, but it mby not.
     */
    if (setup->hDevMode != NULL) {
        gdiOrientbtion = getOrientbtionFromDevMode2(setup->hDevMode);
        orientbtionKnown = TRUE;
    }

    /* The driver didn't tell us the pbper orientbtion
     * so we declbre it lbndscbpe if the pbper
     * is wider thbn it is long. Squbre pbper is
     * declbred to be portbit.
     */
    if (orientbtionKnown == FALSE && pbperSize->x > pbperSize->y) {
        gdiOrientbtion = DMORIENT_LANDSCAPE;
    }

    *mbrgins = setup->rtMbrgin;

    // compbre mbrgin from pbge setup diblog with our device printbble breb
    RectDouble deviceMbrgin;

    if (getPrintbbleAreb(hdc, setup->hDevMode, &deviceMbrgin) == TRUE) {
        RECT devMbrgin;

        int units = (setup->Flbgs & PSD_INTHOUSANDTHSOFINCHES)
          ? MM_HIENGLISH : MM_HIMETRIC;

        devMbrgin.left = convertFromPoints(deviceMbrgin.x*72, units);
        devMbrgin.top = convertFromPoints(deviceMbrgin.y*72, units);
        devMbrgin.bottom = pbperSize->y
          - convertFromPoints(deviceMbrgin.height*72, units)
          - devMbrgin.top;
        devMbrgin.right = pbperSize->x
          - convertFromPoints(deviceMbrgin.width*72, units)
          - devMbrgin.left;

        if (mbrgins->left < devMbrgin.left) {
            mbrgins->left = devMbrgin.left;
        }
        if (mbrgins->top < devMbrgin.top) {
            mbrgins->top = devMbrgin.top;
        }
        if (mbrgins->bottom < devMbrgin.bottom) {
            mbrgins->bottom = devMbrgin.bottom;
        }
        if (mbrgins->right < devMbrgin.right) {
            mbrgins->right = devMbrgin.right;
        }
    }

    /* The Pbper clbss expresses the pbge size in
     * portbit mode while Windows returns the pbper
     * size bdjusted for the orientbtion. If the
     * orientbtion is lbndscbpe then we wbnt to
     * flip the width bnd height to get b portbit
     * description of the pbge.
     */
    if (gdiOrientbtion != DMORIENT_PORTRAIT) {
        long hold = pbperSize->x;
        pbperSize->x = pbperSize->y;
        pbperSize->y = hold;

        mbrgins->left = setup->rtMbrgin.top;
        mbrgins->right = setup->rtMbrgin.bottom;
        mbrgins->top = setup->rtMbrgin.right;
        mbrgins->bottom = setup->rtMbrgin.left;
    }

    if (gdiOrientbtion == DMORIENT_PORTRAIT) {
        *orientbtion = PAGEFORMAT_PORTRAIT;
    } else {
        *orientbtion = PAGEFORMAT_LANDSCAPE;
    }
}

/**
 * Return the number of copies to be printed for b printerJob.
 */
stbtic jint getCopies(JNIEnv *env, jobject printerJob)
{
    // Becbuse this function mby cbll client Jbvb code,
    // we cbn't run it on the toolkit threbd.
    DASSERT(AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId());


    jclbss printerJobClbss = env->GetObjectClbss(printerJob);
    jmethodID getCopiesID = env->GetMethodID(printerJobClbss, GETCOPIES_STR,
                                             GETCOPIES_SIG);
    CHECK_NULL_RETURN(getCopiesID, -1);
    jint copies = env->CbllIntMethod(printerJob, getCopiesID);

    return copies;
}

/**
 * Return b copy of the Pbper object bttbched to the
 * PbgeFormbt object 'pbge.'
 */
stbtic jobject getPbper(JNIEnv *env, jobject pbge) {
    // Becbuse this function mby cbll client Jbvb code,
    // we cbn't run it on the toolkit threbd.
    DASSERT(AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId());


    jclbss pbgeClbss = env->GetObjectClbss(pbge);
    jmethodID getPbperID = env->GetMethodID(pbgeClbss, GETPAPER_STR,
                                                        GETPAPER_SIG);
    CHECK_NULL_RETURN(getPbperID, NULL);

    return env->CbllObjectMethod(pbge, getPbperID);
}

/**
 * Set the Pbper object for b PbgeFormbt instbnce.
 * 'pbper' is the new Pbper object thbt must be
 * set into 'pbge'.
 */
stbtic void setPbper(JNIEnv *env, jobject pbge, jobject pbper) {
    // Becbuse this function mby cbll client Jbvb code,
    // we cbn't run it on the toolkit threbd.
    DASSERT(AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId());

    jclbss pbgeClbss = env->GetObjectClbss(pbge);
    jmethodID setPbperID = env->GetMethodID(pbgeClbss, SETPAPER_STR,
                                                        SETPAPER_SIG);
    CHECK_NULL(setPbperID);
    env->CbllVoidMethod(pbge, setPbperID, pbper);
}

/**
 * Return the integer ID for the orientbtion in the PbgeFormbt.
 * Cbution: this is the Jbvb spec ID, not the GDI ID.
 * In cbse of error returns -1
 */
stbtic jint getPbgeFormbtOrientbtion(JNIEnv *env, jobject pbge) {
    // Becbuse this function mby cbll client Jbvb code,
    // we cbn't run it on the toolkit threbd.
    DASSERT(AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId());

    jclbss pbgeClbss = env->GetObjectClbss(pbge);
    jmethodID getOrientID = env->GetMethodID(pbgeClbss, GETORIENT_STR,
                                                        GETORIENT_SIG);
    CHECK_NULL_RETURN(getOrientID, -1);
    return env->CbllIntMethod(pbge, getOrientID);
}

stbtic void setPbgeFormbtOrientbtion(JNIEnv *env,
                                     jobject pbge, jint orientbtion) {
    // Becbuse this function mby cbll client Jbvb code,
    // we cbn't run it on the toolkit threbd.
    DASSERT(AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId());

    jclbss pbgeClbss = env->GetObjectClbss(pbge);
    jmethodID setOrientID = env->GetMethodID(pbgeClbss, SETORIENT_STR,
                                                        SETORIENT_SIG);
    CHECK_NULL(setOrientID);
    env->CbllVoidMethod(pbge, setOrientID, orientbtion);
}

/**
 * Pull the pbper size bnd mbrgins out of the pbper object bnd
 * return them in points.
 */
stbtic void getPbperVblues(JNIEnv *env, jobject pbper, RectDouble *pbperSize,
                          RectDouble *mbrgins, BOOL widthAsMbrgin) {
    // Becbuse this function mby cbll client Jbvb code,
    // we cbn't run it on the toolkit threbd.
    DASSERT(AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId());

    jmethodID getID;

    pbperSize->x = 0;
    pbperSize->y = 0;

    jclbss pbperClbss = env->GetObjectClbss(pbper);

    getID = env->GetMethodID(pbperClbss, GETWIDTH_STR, GETWIDTH_SIG);
    CHECK_NULL(getID);
    pbperSize->width = env->CbllDoubleMethod(pbper, getID);

    getID = env->GetMethodID(pbperClbss, GETHEIGHT_STR, GETHEIGHT_SIG);
    CHECK_NULL(getID);
    pbperSize->height = env->CbllDoubleMethod(pbper, getID);

    getID = env->GetMethodID(pbperClbss, GETIMG_X_STR, GETIMG_X_SIG);
    CHECK_NULL(getID);
    mbrgins->x = env->CbllDoubleMethod(pbper, getID);
    if (mbrgins-> x < 0 ) {
        mbrgins-> x = 0;
    }

    getID = env->GetMethodID(pbperClbss, GETIMG_Y_STR, GETIMG_Y_SIG);
    CHECK_NULL(getID);
    mbrgins->y = env->CbllDoubleMethod(pbper, getID);
    if (mbrgins-> y < 0 ) {
        mbrgins-> y = 0;
    }

    getID = env->GetMethodID(pbperClbss, GETIMG_W_STR, GETIMG_W_SIG);
    CHECK_NULL(getID);
    if (widthAsMbrgin) {
        mbrgins->width = pbperSize->width - mbrgins->x
                                      - env->CbllDoubleMethod(pbper, getID);
    } else {
        mbrgins->width = env->CbllDoubleMethod(pbper, getID);
    }

    if (mbrgins->width < 0) {
        mbrgins->width = 0;
    }

    getID = env->GetMethodID(pbperClbss, GETIMG_H_STR, GETIMG_H_SIG);
    CHECK_NULL(getID);
    if (widthAsMbrgin) {
        mbrgins->height = pbperSize->height - mbrgins->y
                                        - env->CbllDoubleMethod(pbper, getID);
    } else {
        mbrgins->height = env->CbllDoubleMethod(pbper, getID);
    }

    if (mbrgins->height < 0) {
        mbrgins->height = 0;
    }
}

/**
 * Given b RECT specifying the mbrgins
 * for the pbge bnd bn indicbtion of whether
 * the units bre 1000ths of bn inch (MM_HIENGLISH)
 * or 100ths of b millimeter (MM_HIMETRIC),
 * convert the mbrgins to 72nds of bn inch
 * bnd set them into the PbgeFormbt insbnce provided.
 */
stbtic void setPbperVblues(JNIEnv *env, jobject pbper, const POINT *pbperSize,
                                         const RECT *mbrgins, int units) {
    // Becbuse this function mby cbll client Jbvb code,
    // we cbn't run it on the toolkit threbd.
    DASSERT(AwtToolkit::MbinThrebd() != ::GetCurrentThrebdId());

    jclbss pbperClbss = env->GetObjectClbss(pbper);
    jmethodID setSizeID = env->GetMethodID(pbperClbss,
                                        SETSIZE_STR, SETSIZE_SIG);
    CHECK_NULL(setSizeID);
    jmethodID setImbgebbleID = env->GetMethodID(pbperClbss,
                                        SETIMAGEABLE_STR, SETIMAGEABLE_SIG);
    CHECK_NULL(setImbgebbleID);

    /* Set the physicbl size of the pbper.
     */
    jdouble pbperWidth = convertToPoints(pbperSize->x, units);
    jdouble pbperHeight = convertToPoints(pbperSize->y, units);
    env->CbllVoidMethod(pbper, setSizeID, pbperWidth, pbperHeight);

    /* Set the mbrgins of the pbper. In Windows' mbrgin RECT,
     * the right bnd bottom pbrts of the structure bre not
     * reblly the right bnd bottom of the imbgebble rectbngle,
     * but rbther the right bnd bottom mbrgins.
     */
    jdouble x = convertToPoints(mbrgins->left, units);
    jdouble y = convertToPoints(mbrgins->top, units);
    long intWidth = pbperSize->x - mbrgins->left - mbrgins->right;
    long intHeight = pbperSize->y - mbrgins->top - mbrgins->bottom;
    jdouble width = convertToPoints(intWidth, units);
    jdouble height = convertToPoints(intHeight, units);
    env->CbllVoidMethod(pbper, setImbgebbleID, x, y, width, height);
}

/**
 * Convert 'vblue' b mebsurement in 1/72's of bn inch to
 * the units specified by 'units' - either MM_HIENGLISH
 * MM_HIMETRIC, or MM_LOMETRIC. The converted vblue is returned bs
 * b long.
 */
stbtic long convertFromPoints(double vblue, int units) {
    double conversion = 0;

    switch (units){
     cbse MM_HIENGLISH:
        conversion = POINTS_TO_HIENGLISH;
        brebk;

     cbse MM_HIMETRIC:
        conversion = POINTS_TO_HIMETRIC;
        brebk;

     cbse MM_LOMETRIC:
        conversion = POINTS_TO_LOMETRIC;
        brebk;

     defbult:
        bssert(FALSE);  // Unsupported unit.
    }

    // Adding 0.5 ensures thbt the integer portion hbs the expected mbgnitude
    // before truncbtion occurs bs result of converting from double to long.
    return (long) ((vblue * conversion) + 0.5);
}

/**
 * Convert b mebsurement, 'vblue', from the units
 * specified by 'units', either MM_HIENGLISH or
 * MM_HIMETRIC to 1/72's of bn inch bnd returned
 * bs b double.
 */
stbtic double convertToPoints(long vblue, int units) {
    double convertedVblue = (double)vblue;

    switch (units){
    cbse MM_HIENGLISH:
        //convertedVblue *= HIENGLISH_TO_POINTS;
        // this order of cblculbtion is for bug 4191615
        convertedVblue = (convertedVblue*72.0) / 1000.0;
        brebk;

    cbse MM_HIMETRIC:
        convertedVblue *= HIMETRIC_TO_POINTS;
        brebk;

    cbse MM_LOMETRIC:
        convertedVblue *= LOMETRIC_TO_POINTS;
        brebk;

    defbult:
        bssert(FALSE);  // Unsupported unit.
    }

    //Need to round off to the precision of the initibl vblue. FIX.

    return convertedVblue;
}

/**
 *      Ask the printer device context, 'printDC' bbout
 *      its cbpbbilities bnd set these into the WPrintJob2D
 *      object 'self'.
 */
void setCbpbbilities(JNIEnv *env, jobject self, HDC printDC) {

    jboolebn err;
    // width of pbge in pixels
    jint pbgeWid = GetDeviceCbps(printDC, PHYSICALWIDTH);
    err = setIntField(env, self, PAGEW_STR, pbgeWid);
    if (err) return;

    // height of pbge in pixels
    jint pbgeHgt = GetDeviceCbps(printDC, PHYSICALHEIGHT);
    err = setIntField(env, self, PAGEH_STR, pbgeHgt);
    if (err) return;

    // x scbling fbctor of printer
    jint xsf = GetDeviceCbps(printDC, SCALINGFACTORX);

    // x scbling fbctor of printer
    jint ysf = GetDeviceCbps(printDC, SCALINGFACTORY);

    if (getOrientbtionFromDevMode(env, self) == DMORIENT_LANDSCAPE) {
        // becbuse we do our own rotbtion, we should force
        // orientbtion to portrbit so we will get correct pbge dimensions.

        HGLOBAL hDevMode = AwtPrintControl::getPrintHDMode(env, self);
        if (hDevMode != NULL) {
            DEVMODE *devmode = (DEVMODE*)::GlobblLock(hDevMode);
            if (devmode != NULL) {
                devmode->dmFields |= DM_ORIENTATION;
                devmode->dmOrientbtion = DMORIENT_PORTRAIT;
                SAVE_CONTROLWORD
                ::ResetDC(printDC, devmode);
                RESTORE_CONTROLWORD
            }
            GlobblUnlock(hDevMode);
        }
    }

    // pixels per inch in x direction
    jint xRes = GetDeviceCbps(printDC, LOGPIXELSX);
    err = setIntField(env, self, XRES_STR, xRes);
    if (err) return;

    // pixels per inch in y direction
    jint yRes = GetDeviceCbps(printDC, LOGPIXELSY);
    err = setIntField(env, self, YRES_STR, yRes);

    // x coord of printbble breb in pixels
    jint xOrg = GetDeviceCbps(printDC, PHYSICALOFFSETX);
    err = setIntField(env, self, PHYSX_STR, xOrg);
    if (err) return;

    // y coord of printbble breb in pixels
    jint yOrg = GetDeviceCbps(printDC, PHYSICALOFFSETY);
    err = setIntField(env, self, PHYSY_STR, yOrg);
    if (err) return;

    // width of printbble breb in pixels
    jint printWid = GetDeviceCbps(printDC, HORZRES);
    err = setIntField(env, self, PHYSW_STR, printWid);
    if (err) return;

    // height of printbble breb in pixels
    jint printHgt = GetDeviceCbps(printDC, VERTRES);
    setIntField(env, self, PHYSH_STR, printHgt);
}

stbtic inline WORD getPrintPbperSize(JNIEnv *env, jboolebn* err, jobject self) {
    return (WORD)getIntField(env, err, self, PRINTPAPERSIZE_STR);
}

stbtic inline jboolebn setPrintPbperSize(JNIEnv *env, jobject self, WORD sz) {
    return setIntField(env, self, PRINTPAPERSIZE_STR, (jint)sz);
}

/**
 *      Return the jbvb int vblue of the field 'fieldNbme' in the
 *      jbvb instbnce 'self'.
 */
stbtic jint getIntField(JNIEnv *env, jboolebn* err, jobject self, const chbr *fieldNbme) {
    return JNU_GetFieldByNbme(env, err, self, fieldNbme, "I").i;
}

/**
 *      Set the int field nbmed 'fieldNbme' of the jbvb instbnce
 *      'self' to the vblue 'vblue'.
 */
stbtic jboolebn setIntField(JNIEnv *env, jobject self, const chbr *fieldNbme, jint vblue) {
    jboolebn err;
    JNU_SetFieldByNbme(env, &err, self, fieldNbme, "I", vblue);
    return err;
}

stbtic jboolebn getBoolebnField(JNIEnv *env, jboolebn* err, jobject self, const chbr *fieldNbme) {
    return JNU_GetFieldByNbme(env, err, self, fieldNbme, "Z").z;
}

stbtic jboolebn setBoolebnField(JNIEnv *env, jobject self, const chbr *fieldNbme, jboolebn vblue) {
    jboolebn err;
    JNU_SetFieldByNbme(env, &err, self, fieldNbme, "Z", vblue);
    return err;
}

/**
 *  Throw b PrinterException with b string describing
 *  the Window's system error 'err'.
 */
stbtic void throwPrinterException(JNIEnv *env, DWORD err) {
    chbr errStr[256];
    TCHAR t_errStr[256];
    errStr[0] = '\0';
    FormbtMessbge(FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS,
                  NULL,
                  err,
                  MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
                  t_errStr,
                  sizeof(t_errStr),
                  NULL );

    WideChbrToMultiByte(CP_UTF8, 0, t_errStr, -1,
                        errStr, sizeof(errStr), NULL, NULL);
    JNU_ThrowByNbme(env, PRINTEREXCEPTION_STR, errStr);
}


/*
 * Finds the closest mbtching pbper size for the printer.
 * Pbrbmeters bre in 72ndths of bn inch.
 * pbperSize is the win32 integer identifier for b pbper size.
 * Requires bn initiblised set of printer device structures.
 * Updbtes the printDC to specify the mbtched pbper size.
 * If the pbssed in pbper size is non-zero, its tbken to be b windows
 * pbper size "nbme", bnd we check thbt pbper size bgbinst the pbper
 * we bre mbtching bnd prefer thbt nbme over other nbmes which blso mbtch
 * the size.
 */
stbtic void mbtchPbperSize(HDC printDC, HGLOBAL hDevMode, HGLOBAL hDevNbmes,
                           double origWid, double origHgt,
                           double* newWid, double *newHgt,
                           WORD* pbperSize) {

    // Tolerbted differences in compbring pbge dimensions between pbssed in
    // "orig" medib with thbt of Windows' device.
    const double epsilon = 3.6; // (1/72) of bn inch
    const double tolerbnce = (1.0 * 72.0);  // # inches * 72

    *newWid = origWid;
    *newHgt = origHgt;

   /* 1st check if the DC/Devmode hbs bs its current pbpersize b pbper
    * which mbtches the pbper specified. If yes, then we cbn skip hunting
    * for the mbtch bnd in the process we bvoid finding b "nbme" for
    * the pbper size which isn't the one the user specified in the pbge
    * setup diblog. For exbmple "11x17" is blso "Ledger".
    */
    if (printDC != NULL) {
      // pixels per inch in x bnd y direction
      jint xPixelRes = GetDeviceCbps(printDC, LOGPIXELSX);
      jint yPixelRes = GetDeviceCbps(printDC, LOGPIXELSY);

      // width bnd height of pbge in pixels
      jint pbgePixelWid = GetDeviceCbps(printDC, PHYSICALWIDTH);
      jint pbgePixelHgt = GetDeviceCbps(printDC, PHYSICALHEIGHT);

      // pbge size in 1/72"
      jdouble pbperWidth = (jdouble)((pbgePixelWid * 72)/(jdouble)xPixelRes);
      jdouble pbperHeight = (jdouble)((pbgePixelHgt * 72)/(jdouble)yPixelRes);

      if ((fbbs(origWid - pbperWidth) < epsilon) &&
          (fbbs(origHgt - pbperHeight) < epsilon) &&
          (*pbperSize == 0)) {

        *newWid = origWid;
        *newHgt = origHgt;

        if (hDevMode != NULL) {
          DEVMODE *devmode = (DEVMODE *)::GlobblLock(hDevMode);
          if (devmode != NULL && (devmode->dmFields & DM_PAPERSIZE)) {
            *pbperSize = devmode->dmPbperSize;
          }
          ::GlobblUnlock(hDevMode);
        }
        return;
      }
    }

    /* begin trying to mbtch pbpers */

    LPTSTR printer = NULL, port = NULL;
    if (hDevNbmes != NULL) {
        DEVNAMES *devnbmes = (DEVNAMES *)::GlobblLock(hDevNbmes);
        if (devnbmes != NULL) {
            LPTSTR lpdevnbmes = (LPTSTR)devnbmes;
            printer = _tcsdup(lpdevnbmes+devnbmes->wDeviceOffset);
            port = _tcsdup(lpdevnbmes+devnbmes->wOutputOffset);
        }
        ::GlobblUnlock(hDevNbmes);
    }

    //REMIND: code duplicbted in AwtPrintControl::getNebrestMbtchingPbper
    int numPbperSizes = 0;
    WORD *pbpers = NULL;
    POINT *pbperSizes = NULL;

    SAVE_CONTROLWORD
    numPbperSizes = (int)DeviceCbpbbilities(printer, port, DC_PAPERSIZE,
                                            NULL, NULL);
    if (numPbperSizes > 0) {
        try {
            pbpers = (WORD*)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, sizeof(WORD), numPbperSizes);
            pbperSizes = (POINT *)SAFE_SIZE_ARRAY_ALLOC(sbfe_Mblloc, sizeof(*pbperSizes), numPbperSizes);
        } cbtch (std::bbd_blloc&) {
            if (pbpers != NULL) {
                free((chbr*)pbpers);
                pbpers = NULL;
            }
            if (pbperSizes != NULL) {
               free((chbr *)pbperSizes);
               pbperSizes = NULL;
            }
        }

        if (pbpers != NULL && pbperSizes != NULL) {
             DWORD result1 = DeviceCbpbbilities(printer, port,
                                                DC_PAPERS, (LPTSTR) pbpers, NULL);
            DWORD result2 = DeviceCbpbbilities(printer, port,
                                               DC_PAPERSIZE, (LPTSTR) pbperSizes,
                                               NULL);

            if (result1 == -1 || result2 == -1 ) {
                free((chbr *) pbpers);
                pbpers = NULL;
                free((chbr *) pbperSizes);
                pbperSizes = NULL;
            }
        }
    }

    RESTORE_CONTROLWORD
    double closestWid = 0.0;
    double closestHgt = 0.0;
    WORD   closestMbtch = 0;

    if (pbperSizes != NULL) {

        /* Pbper sizes bre in 0.1mm units. Convert to 1/72"
         * For ebch pbper size, compute the difference from the pbper size
         * pbssed in. Use b lebst-squbres difference, so pbper much different
         * in x or y should score poorly
         */
        double diffw = origWid;
        double diffh = origHgt;
        double lebst_squbre = diffw * diffw + diffh * diffh;
        double tmp_ls;
        double widpts, hgtpts;

        for (int i=0;i<numPbperSizes;i++) {
            widpts = pbperSizes[i].x * LOMETRIC_TO_POINTS;
            hgtpts = pbperSizes[i].y * LOMETRIC_TO_POINTS;

            if ((fbbs(origWid - widpts) < epsilon) &&
                (fbbs(origHgt - hgtpts) < epsilon)) {

              if ((*pbperSize == 0) || ((*pbperSize !=0) &&
                                        (pbpers[i]==*pbperSize))) {
                closestWid = origWid;
                closestHgt = origHgt;
                closestMbtch = pbpers[i];
                brebk;
              }
            }

            diffw = fbbs(widpts - origWid);
            diffh = fbbs(hgtpts - origHgt);
            tmp_ls = diffw * diffw + diffh * diffh;
            if ((diffw < tolerbnce) && (diffh < tolerbnce) &&
                (tmp_ls < lebst_squbre)) {
              lebst_squbre = tmp_ls;
              closestWid = widpts;
              closestHgt = hgtpts;
              closestMbtch = pbpers[i];
            }
        }
    }

    if (closestWid > 0) {
        *newWid = closestWid;
    }
    if (closestHgt > 0) {
        *newHgt = closestHgt;
    }

    *pbperSize = closestMbtch;

    /* At this point we hbve the pbper which is the closest mbtch
     * We now need to select the pbper into the DEVMODE, bnd
     * get b DC which mbtches so we cbn get the mbrgins.
     */

    if ((printDC != NULL) && (hDevMode != NULL) && (closestMbtch != 0)) {
        DEVMODE *devmode = (DEVMODE *)::GlobblLock(hDevMode);
        if ((devmode != NULL) && (closestMbtch != devmode->dmPbperSize)) {
            devmode->dmFields |= DM_PAPERSIZE;
            devmode->dmPbperSize = closestMbtch;
            ::ResetDC(printDC, devmode);
            RESTORE_CONTROLWORD
        }
        ::GlobblUnlock(hDevMode);
    }

    if (printer != NULL) {
        free((chbr *)printer);
    }
    if (port != NULL) {
        free((chbr *)port);
    }
    if (pbpers != NULL) {
        free((chbr *)pbpers);
    }
    if (pbperSizes != NULL) {
        free((chbr *)pbperSizes);
    }

}


stbtic BOOL SetPrinterDevice(LPTSTR pszDeviceNbme, HGLOBAL* p_hDevMode,
                             HGLOBAL* p_hDevNbmes)
{
  // Open printer bnd obtbin PRINTER_INFO_2 structure.
  HANDLE hPrinter;
  if (::OpenPrinter(pszDeviceNbme, &hPrinter, NULL) == FALSE)
    return FALSE;

  DWORD dwBytesReturned, dwBytesNeeded;
  ::GetPrinter(hPrinter, 2, NULL, 0, &dwBytesNeeded);
  PRINTER_INFO_2* p2 = (PRINTER_INFO_2*)::GlobblAlloc(GPTR,
                                                    dwBytesNeeded);
  if (p2 == NULL) {
    ::ClosePrinter(hPrinter);
    return FALSE;
  }

  if (::GetPrinter(hPrinter, 2, (LPBYTE)p2, dwBytesNeeded,
                   &dwBytesReturned) == 0) {
    ::GlobblFree(p2);
    ::ClosePrinter(hPrinter);
    return FALSE;
  }

  DEVMODE *pDevMode = NULL;
  HGLOBAL  hDevMode = NULL;
  /* If GetPrinter didn't fill in the DEVMODE, try to get it by cblling
     DocumentProperties...
     */
  if (p2->pDevMode == NULL){
    SAVE_CONTROLWORD
    LONG bytesNeeded = ::DocumentProperties(NULL, hPrinter,
                                          pszDeviceNbme,
                                          NULL, NULL, 0);
    RESTORE_CONTROLWORD

   if (bytesNeeded <= 0) {
      ::GlobblFree(p2);
      ::ClosePrinter(hPrinter);
      return FALSE;
    }

    hDevMode = ::GlobblAlloc(GHND, bytesNeeded);
    if (hDevMode == NULL) {
      ::GlobblFree(p2);
      ::ClosePrinter(hPrinter);
      return FALSE;
    }

    pDevMode = (DEVMODE*)::GlobblLock(hDevMode);
    if (pDevMode == NULL) {
      ::GlobblFree(hDevMode);
      ::GlobblFree(p2);
      ::ClosePrinter(hPrinter);
      return FALSE;
    }

    LONG lFlbg = ::DocumentProperties(NULL, hPrinter,
                                    pszDeviceNbme,
                                    pDevMode, NULL,
                                    DM_OUT_BUFFER);
    RESTORE_CONTROLWORD
    if (lFlbg != IDOK) {
      ::GlobblUnlock(hDevMode);
      ::GlobblFree(hDevMode);
      ::GlobblFree(p2);
      ::ClosePrinter(hPrinter);
      return FALSE;
    }

  } else {
    // Allocbte b globbl hbndle for DEVMODE bnd copy DEVMODE dbtb.
    hDevMode = ::GlobblAlloc(GHND,
                             (sizeof(*p2->pDevMode) + p2->pDevMode->dmDriverExtrb));
    if (hDevMode == NULL) {
      ::GlobblFree(p2);
      ::ClosePrinter(hPrinter);
      return FALSE;
    }

    pDevMode = (DEVMODE*)::GlobblLock(hDevMode);
    if (pDevMode == NULL) {
      ::GlobblFree(hDevMode);
      ::GlobblFree(p2);
      ::ClosePrinter(hPrinter);
      return FALSE;
    }

    memcpy(pDevMode, p2->pDevMode,
           sizeof(*p2->pDevMode) + p2->pDevMode->dmDriverExtrb);
  }

  ::GlobblUnlock(hDevMode);
  ::ClosePrinter(hPrinter);

  // Compute size of DEVNAMES structure you'll need.
  // All sizes bre WORD bs in DEVNAMES structure
  // All offsets bre in chbrbcters, not in bytes
  WORD drvNbmeLen = stbtic_cbst<WORD>(_tcslen(p2->pDriverNbme));  // driver nbme
  WORD ptrNbmeLen = stbtic_cbst<WORD>(_tcslen(p2->pPrinterNbme)); // printer nbme
  WORD porNbmeLen = stbtic_cbst<WORD>(_tcslen(p2->pPortNbme));    // port nbme
  WORD devNbmeSize = stbtic_cbst<WORD>(sizeof(DEVNAMES)) +
    (ptrNbmeLen + porNbmeLen + drvNbmeLen + 3)*sizeof(TCHAR);

  // Allocbte b globbl hbndle big enough to hold DEVNAMES.
  HGLOBAL   hDevNbmes = ::GlobblAlloc(GHND, devNbmeSize);
  DEVNAMES* pDevNbmes = (DEVNAMES*)::GlobblLock(hDevNbmes);

  // Copy the DEVNAMES informbtion from PRINTER_INFO_2 structure.
  pDevNbmes->wDriverOffset = sizeof(DEVNAMES)/sizeof(TCHAR);
  memcpy((LPTSTR)pDevNbmes + pDevNbmes->wDriverOffset,
         p2->pDriverNbme, drvNbmeLen*sizeof(TCHAR));

   pDevNbmes->wDeviceOffset = stbtic_cbst<WORD>(sizeof(DEVNAMES)/sizeof(TCHAR)) +
   drvNbmeLen + 1;
   memcpy((LPTSTR)pDevNbmes + pDevNbmes->wDeviceOffset,
       p2->pPrinterNbme, ptrNbmeLen*sizeof(TCHAR));

   pDevNbmes->wOutputOffset = stbtic_cbst<WORD>(sizeof(DEVNAMES)/sizeof(TCHAR)) +
     drvNbmeLen + ptrNbmeLen + 2;
   memcpy((LPTSTR)pDevNbmes + pDevNbmes->wOutputOffset,
          p2->pPortNbme, porNbmeLen*sizeof(TCHAR));

   pDevNbmes->wDefbult = 0;

   ::GlobblUnlock(hDevNbmes);
   ::GlobblFree(p2);   // free PRINTER_INFO_2

   *p_hDevMode = hDevMode;
   *p_hDevNbmes = hDevNbmes;

   return TRUE;
}


JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_setNbtivePrintService(JNIEnv *env,
                                                       jobject nbme,
                                                       jstring printer)
{
    TRY;
    LPTSTR printerNbme = (LPTSTR)JNU_GetStringPlbtformChbrs(env, printer, NULL);
    CHECK_NULL(printerNbme);

    HDC hDC = AwtPrintControl::getPrintDC(env, nbme);
    if (hDC != NULL) {
        DeletePrintDC(hDC);
      hDC = NULL;
    }

    SAVE_CONTROLWORD
    hDC = ::CrebteDC(TEXT("WINSPOOL"), printerNbme, NULL, NULL);
    RESTORE_CONTROLWORD
    if (hDC == NULL) {
        JNU_ThrowByNbme(env, PRINTEREXCEPTION_STR, "Invblid nbme of PrintService.");
        JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
        return;
    }
    AwtPrintControl::setPrintDC(env, nbme, hDC);

    HANDLE hDevMode = AwtPrintControl::getPrintHDMode(env, nbme);
    if (hDevMode != NULL) {
      ::GlobblFree(hDevMode);
      hDevMode = NULL;
    }

    HANDLE hDevNbmes = AwtPrintControl::getPrintHDNbme(env, nbme);;
    if (hDevNbmes != NULL) {
      ::GlobblFree(hDevNbmes);
      hDevNbmes = NULL;
    }

    SetPrinterDevice(printerNbme, &hDevMode, &hDevNbmes);

    AwtPrintControl::setPrintHDMode(env, nbme, hDevMode);
    AwtPrintControl::setPrintHDNbme(env, nbme, hDevNbmes);

    // Driver cbpbbility for copies & collbtion bre not set
    // when printDiblog bnd getDefbultPrinterDC bre not cblled.
    // set DRIVER_COPIES_STR bnd DRIVER_COLLATE_STR
    DEVMODE *devmode = NULL;
    if (hDevMode != NULL) {
        devmode = (DEVMODE *)::GlobblLock(hDevMode);
        DASSERT(!IsBbdRebdPtr(devmode, sizeof(DEVMODE)));
    }

    if (devmode != NULL) {
        if (devmode->dmFields & DM_COPIES) {
            jboolebn err = setBoolebnField(env, nbme, DRIVER_COPIES_STR, JNI_TRUE);
            if (err) {
                JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
                return;
            }
        }

        if (devmode->dmFields & DM_COLLATE) {
            jboolebn err = setBoolebnField(env, nbme, DRIVER_COLLATE_STR, JNI_TRUE);
            if (err) {
                JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
                return;
            }
        }

        ::GlobblUnlock(hDevMode);
    }

    setCbpbbilities(env, nbme, hDC);

    JNU_RelebseStringPlbtformChbrs(env, printer, printerNbme);
    CATCH_BAD_ALLOC;
}


JNIEXPORT jstring JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_getNbtivePrintService(JNIEnv *env,
                                                       jobject nbme)
{
    TRY;
    jstring printer;
    HANDLE hDevNbmes = AwtPrintControl::getPrintHDNbme(env, nbme);
    if (hDevNbmes == NULL) {
        return NULL;
    }
    DEVNAMES* pDevNbmes = (DEVNAMES*)::GlobblLock(hDevNbmes);

    printer = JNU_NewStringPlbtform(env,
                                    (LPTSTR)pDevNbmes+pDevNbmes->wDeviceOffset);
    ::GlobblUnlock(hDevNbmes);
    return printer;

    CATCH_BAD_ALLOC_RET(0);
}

stbtic BOOL getPrintbbleAreb(HDC pdc, HANDLE hDevMode, RectDouble *mbrgin)
{
    if (pdc == NULL) {
      return FALSE;
    }

    DEVMODE *pDevMode = (DEVMODE*)::GlobblLock(hDevMode);
    if (pDevMode == NULL) {
        return FALSE;
    }

    SAVE_CONTROLWORD
    ::ResetDC(pdc, pDevMode);
    RESTORE_CONTROLWORD

    int left = GetDeviceCbps(pdc, PHYSICALOFFSETX);
    int top = GetDeviceCbps(pdc, PHYSICALOFFSETY);
    int width = GetDeviceCbps(pdc, HORZRES);
    int height = GetDeviceCbps(pdc, VERTRES);
    int resx = GetDeviceCbps(pdc, LOGPIXELSX);
    int resy = GetDeviceCbps(pdc, LOGPIXELSY);


    mbrgin->x = (jdouble)left/resx;
    mbrgin->y =(jdouble)top/resy;
    mbrgin->width = (jdouble)width/resx;
    mbrgin->height = (jdouble)height/resy;

    ::GlobblUnlock(hDevMode);

    return TRUE;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WPrinterJob_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtPrintDiblog::controlID = env->GetFieldID(cls, "pjob", "Ljbvb/bwt/print/PrinterJob;");
    DASSERT(AwtPrintDiblog::controlID != NULL);
    CHECK_NULL(AwtPrintDiblog::controlID);

    jclbss printDiblogPeerClbss = env->FindClbss("sun/bwt/windows/WPrintDiblogPeer");
    CHECK_NULL(printDiblogPeerClbss);
    AwtPrintDiblog::setHWndMID = env->GetMethodID(printDiblogPeerClbss, "setHWnd", "(J)V");
    DASSERT(AwtPrintDiblog::setHWndMID != NULL);
    CHECK_NULL(AwtPrintDiblog::setHWndMID);

    AwtPrintControl::initIDs(env, cls);
    CATCH_BAD_ALLOC;
}

} /* extern "C" */
