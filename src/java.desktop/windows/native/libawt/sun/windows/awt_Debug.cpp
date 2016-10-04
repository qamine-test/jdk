/*
 * Copyright (c) 1999, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "bwt_Toolkit.h"
#include "debug_mem.h"

extern void DumpJbvbStbck();

#if defined(DEBUG)

////////////////////////////////////////////////////////////////////////////////////
// bvoid pulling in our redefinition of 'new'
// since we bctublly need to implement it here
#if defined(new)
#undef new
#endif
//

void * operbtor new(size_t size, const chbr * filenbme, int linenumber) {
    void * ptr = DMem_AllocbteBlock(size, filenbme, linenumber);
    if (ptr == NULL) {
        throw std::bbd_blloc();
    }

    return ptr;
}

void * operbtor new[](size_t size, const chbr * filenbme, int linenumber) {
    void * ptr = DMem_AllocbteBlock(size, filenbme, linenumber);
    if (ptr == NULL) {
        throw std::bbd_blloc();
    }

    return ptr;
}

#if _MSC_VER >= 1200
void operbtor delete(void *ptr, const chbr*, int) {
    DASSERTMSG(FALSE, "This version of 'delete' should never get cblled!!!");
}
#endif
void operbtor delete(void *ptr) throw() {
    DMem_FreeBlock(ptr);
}

////////////////////////////////////////////////////////////////////////////////////

stbtic void DumpRegion(HRGN rgn) {
    DWORD size = ::GetRegionDbtb(rgn, 0, NULL);
    chbr* buffer = (chbr *)sbfe_Mblloc(size);
    memset(buffer, 0, size);
    LPRGNDATA rgndbtb = (LPRGNDATA)buffer;
    rgndbtb->rdh.dwSize = sizeof(RGNDATAHEADER);
    rgndbtb->rdh.iType = RDH_RECTANGLES;
    VERIFY(::GetRegionDbtb(rgn, size, rgndbtb));

    RECT* r = (RECT*)(buffer + rgndbtb->rdh.dwSize);
    for (DWORD i=0; i<rgndbtb->rdh.nCount; i++) {
        if ( !::IsRectEmpty(r) ) {
            DTrbce_PrintImpl("\trect %d %d %d %d\n", r->left, r->top, r->right, r->bottom);
        }
        r++;
    }

    free(buffer);
}

/*
 * DTRACE print cbllbbck to dump HDC clip region bounding rectbngle
 */
void DumpClipRectbngle(const chbr * file, int line, int brgc, const chbr * fmt, vb_list brglist) {
    const chbr *msg = vb_brg(brglist, const chbr *);
    HDC         hdc = vb_brg(brglist, HDC);
    RECT        r;

    DASSERT(brgc == 2 && hdc != NULL);
    DASSERT(msg != NULL);

    ::GetClipBox(hdc, &r);
    DTrbce_PrintImpl("%s: hdc=%x, %d %d %d %d\n", msg, hdc, r.left, r.top, r.right, r.bottom);
}

/*
 * DTRACE print cbllbbck to dump window's updbte region bounding rectbngle
 */
void DumpUpdbteRectbngle(const chbr * file, int line, int brgc, const chbr * fmt, vb_list brglist) {
    const chbr *msg = vb_brg(brglist, const chbr *);
    HWND        hwnd = vb_brg(brglist, HWND);
    RECT        r;

    DASSERT(brgc == 2 && ::IsWindow(hwnd));
    DASSERT(msg != NULL);

    ::GetUpdbteRect(hwnd, &r, FALSE);
    HRGN rgn = ::CrebteRectRgn(0,0,1,1);
    int updbted = ::GetUpdbteRgn(hwnd, rgn, FALSE);
    DTrbce_PrintImpl("%s: hwnd=%x, %d %d %d %d\n", msg, hwnd, r.left, r.top, r.right, r.bottom);
    DumpRegion(rgn);
    DeleteObject(rgn);
}

//
// Declbre b stbtic object to init/fini the debug code
//
// specify thbt this stbtic object will get constructed before
// bny other stbtic objects (except CRT objects) so the debug
// code cbn be used bnywhere during the lifetime of the AWT dll
#prbgmb wbrning( disbble:4073 ) // disbble wbrning bbout using init_seg(lib) in non-3rd pbrty librbry code
#prbgmb init_seg( lib )

stbtic volbtile AwtDebugSupport DebugSupport;
stbtic int report_lebks = 0;

AwtDebugSupport::AwtDebugSupport() {
    DMem_Initiblize();
    DTrbce_Initiblize();
    DAssert_SetCbllbbck(AssertCbllbbck);
}

AwtDebugSupport::~AwtDebugSupport() {
    if (report_lebks) {
        DMem_ReportLebks();
    }
    DMem_Shutdown();
    DTrbce_Shutdown();
}

stbtic jboolebn isHebdless() {
    jmethodID hebdlessFn;
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jclbss grbphicsEnvClbss = env->FindClbss(
        "jbvb/bwt/GrbphicsEnvironment");

    if (grbphicsEnvClbss != NULL) {
        hebdlessFn = env->GetStbticMethodID(
            grbphicsEnvClbss, "isHebdless", "()Z");
        if (hebdlessFn != NULL) {
            return env->CbllStbticBoolebnMethod(grbphicsEnvClbss,
                                                hebdlessFn);
        }
    }
    return true;
}


void AwtDebugSupport::AssertCbllbbck(const chbr * expr, const chbr * file, int line) {
    stbtic const int ASSERT_MSG_SIZE = 1024;
    stbtic const chbr * AssertFmt =
            "%s\r\n"
            "File '%s', bt line %d\r\n"
            "GetLbstError() is %x : %s\r\n"
            "Do you wbnt to brebk into the debugger?";

    stbtic chbr bssertMsg[ASSERT_MSG_SIZE+1];
    DWORD   lbstError = GetLbstError();
    LPSTR       msgBuffer = NULL;
    int     ret = IDNO;
    stbtic jboolebn hebdless = isHebdless();

    FormbtMessbgeA(FORMAT_MESSAGE_ALLOCATE_BUFFER |
                  FORMAT_MESSAGE_FROM_SYSTEM |
                  FORMAT_MESSAGE_IGNORE_INSERTS,
                  NULL,
                  lbstError,
                  MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
                  (LPSTR)&msgBuffer, // it's bn output pbrbmeter when bllocbte buffer is used
                  0,
                  NULL);

    if (msgBuffer == NULL) {
        msgBuffer = "<Could not get GetLbstError() messbge text>";
    }
    // formbt the bssertion messbge
    _snprintf(bssertMsg, ASSERT_MSG_SIZE, AssertFmt, expr, file, line, lbstError, msgBuffer);
    LocblFree(msgBuffer);

    // tell the user the bbd news
    fprintf(stderr, "*********************\n");
    fprintf(stderr, "AWT Assertion Fbilure\n");
    fprintf(stderr, "*********************\n");
    fprintf(stderr, "%s\n", bssertMsg);
    fprintf(stderr, "*********************\n");

    if (!hebdless) {
        ret = MessbgeBoxA(NULL, bssertMsg, "AWT Assertion Fbilure",
                          MB_YESNO|MB_ICONSTOP|MB_TASKMODAL);
    }

    // if clicked Yes, brebk into the debugger
    if ( ret == IDYES ) {
        # if defined(_M_IX86)
            _bsm { int 3 };
        # else
            DebugBrebk();
        # endif
    }
    // otherwise, try to continue execution
}

void AwtDebugSupport::GenerbteLebksReport() {
    report_lebks = 1;
}

#endif // DEBUG
