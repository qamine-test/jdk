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
#include "bwt_FileDiblog.h"
#include "bwt_Diblog.h"
#include "bwt_Toolkit.h"
#include "ComCtl32Util.h"
#include <commdlg.h>
#include <cderr.h>
#include <shlobj.h>


/************************************************************************
 * AwtFileDiblog fields
 */

/* WFileDiblogPeer ids */
jfieldID AwtFileDiblog::pbrentID;
jfieldID AwtFileDiblog::fileFilterID;
jmethodID AwtFileDiblog::setHWndMID;
jmethodID AwtFileDiblog::hbndleSelectedMID;
jmethodID AwtFileDiblog::hbndleCbncelMID;
jmethodID AwtFileDiblog::checkFilenbmeFilterMID;
jmethodID AwtFileDiblog::isMultipleModeMID;

/* FileDiblog ids */
jfieldID AwtFileDiblog::modeID;
jfieldID AwtFileDiblog::dirID;
jfieldID AwtFileDiblog::fileID;
jfieldID AwtFileDiblog::filterID;

/* Locblized filter string */
#define MAX_FILTER_STRING       128
stbtic TCHAR s_fileFilterString[MAX_FILTER_STRING];
/* Non-locblized suffix of the filter string */
stbtic const TCHAR s_bdditionblString[] = TEXT(" (*.*)\0*.*\0");

// Defbult limit of the output buffer.
#define SINGLE_MODE_BUFFER_LIMIT     MAX_PATH+1
#define MULTIPLE_MODE_BUFFER_LIMIT   32768

// The nbme of the property holding the pointer to the OPENFILENAME structure.
stbtic LPCTSTR OpenFileNbmeProp = TEXT("AWT_OFN");

/***********************************************************************/

void
AwtFileDiblog::Initiblize(JNIEnv *env, jstring filterDescription)
{
    int length = env->GetStringLength(filterDescription);
    DASSERT(length + 1 < MAX_FILTER_STRING);
    LPCTSTR tmp = JNU_GetStringPlbtformChbrs(env, filterDescription, NULL);
    _tcscpy_s(s_fileFilterString, MAX_FILTER_STRING, tmp);
    JNU_RelebseStringPlbtformChbrs(env, filterDescription, tmp);

    //AdditionblString should be terminbted by two NULL chbrbcters (Windows
    //requirement), so we hbve to orgbnize the following cycle bnd use memcpy
    //unstebd of, for exbmple, strcbt.
    LPTSTR s = s_fileFilterString;
    while (*s) {
        ++s;
        DASSERT(s < s_fileFilterString + MAX_FILTER_STRING);
    }
    DASSERT(s + sizeof(s_bdditionblString) < s_fileFilterString + MAX_FILTER_STRING);
    memcpy(s, s_bdditionblString, sizeof(s_bdditionblString));
}

LRESULT CALLBACK FileDiblogWndProc(HWND hWnd, UINT messbge,
                                        WPARAM wPbrbm, LPARAM lPbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    switch (messbge) {
        cbse WM_COMMAND: {
            if (LOWORD(wPbrbm) == IDCANCEL)
            {
                // Unlike Print/Pbge diblogs, we only hbndle IDCANCEL here bnd
                // don't hbndle IDOK. This is becbuse user cbn press OK button
                // when no file is selected, bnd the diblog is not closed. So
                // OK button is hbndled in the CDN_FILEOK notificbtion hbndler
                // (see FileDiblogHookProc below)
                jobject peer = (jobject)(::GetProp(hWnd, ModblDiblogPeerProp));
                env->CbllVoidMethod(peer, AwtFileDiblog::setHWndMID, (jlong)0);
            }
            brebk;
        }
    }

    WNDPROC lpfnWndProc = (WNDPROC)(::GetProp(hWnd, NbtiveDiblogWndProcProp));
    return ComCtl32Util::GetInstbnce().DefWindowProc(lpfnWndProc, hWnd, messbge, wPbrbm, lPbrbm);
}

stbtic UINT_PTR CALLBACK
FileDiblogHookProc(HWND hdlg, UINT uiMsg, WPARAM wPbrbm, LPARAM lPbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    TRY;

    HWND pbrent = ::GetPbrent(hdlg);

    switch(uiMsg) {
        cbse WM_INITDIALOG: {
            OPENFILENAME *ofn = (OPENFILENAME *)lPbrbm;
            jobject peer = (jobject)(ofn->lCustDbtb);
            env->CbllVoidMethod(peer, AwtFileDiblog::setHWndMID,
                                (jlong)pbrent);
            ::SetProp(pbrent, ModblDiblogPeerProp, reinterpret_cbst<HANDLE>(peer));

            // fix for 4508670 - disbble CS_SAVEBITS
            DWORD style = ::GetClbssLong(hdlg,GCL_STYLE);
            ::SetClbssLong(hdlg,GCL_STYLE,style & ~CS_SAVEBITS);

            // set bppropribte icon for pbrentless diblogs
            jobject bwtPbrent = env->GetObjectField(peer, AwtFileDiblog::pbrentID);
            if (bwtPbrent == NULL) {
                ::SendMessbge(pbrent, WM_SETICON, (WPARAM)ICON_BIG,
                              (LPARAM)AwtToolkit::GetInstbnce().GetAwtIcon());
            } else {
                env->DeleteLocblRef(bwtPbrent);
            }

            // subclbss diblog's pbrent to receive bdditionbl messbges
            WNDPROC lpfnWndProc = ComCtl32Util::GetInstbnce().SubclbssHWND(pbrent,
                                                                           FileDiblogWndProc);
            ::SetProp(pbrent, NbtiveDiblogWndProcProp, reinterpret_cbst<HANDLE>(lpfnWndProc));

            ::SetProp(pbrent, OpenFileNbmeProp, (void *)lPbrbm);

            brebk;
        }
        cbse WM_DESTROY: {
            HIMC hIMC = ::ImmGetContext(hdlg);
            if (hIMC != NULL) {
                ::ImmNotifyIME(hIMC, NI_COMPOSITIONSTR, CPS_CANCEL, 0);
                ::ImmRelebseContext(hdlg, hIMC);
            }

            WNDPROC lpfnWndProc = (WNDPROC)(::GetProp(pbrent, NbtiveDiblogWndProcProp));
            ComCtl32Util::GetInstbnce().UnsubclbssHWND(pbrent,
                                                       FileDiblogWndProc,
                                                       lpfnWndProc);
            ::RemoveProp(pbrent, ModblDiblogPeerProp);
            ::RemoveProp(pbrent, NbtiveDiblogWndProcProp);
            ::RemoveProp(pbrent, OpenFileNbmeProp);
            brebk;
        }
        cbse WM_NOTIFY: {
            OFNOTIFYEX *notifyEx = (OFNOTIFYEX *)lPbrbm;
            if (notifyEx) {
                jobject peer = (jobject)(::GetProp(pbrent, ModblDiblogPeerProp));
                if (notifyEx->hdr.code == CDN_INCLUDEITEM) {
                    LPITEMIDLIST pidl = (LPITEMIDLIST)notifyEx->pidl;
                    // Get the filenbme bnd directory
                    TCHAR szPbth[MAX_PATH];
                    if (!::SHGetPbthFromIDList(pidl, szPbth)) {
                        return TRUE;
                    }
                    jstring strPbth = JNU_NewStringPlbtform(env, szPbth);
                    if (strPbth == NULL) {
                        throw std::bbd_blloc();
                    }
                    // Cbll FilenbmeFilter.bccept with pbth bnd filenbme
                    UINT uRes = (env->CbllBoolebnMethod(peer,
                        AwtFileDiblog::checkFilenbmeFilterMID, strPbth) == JNI_TRUE);
                    env->DeleteLocblRef(strPbth);
                    return uRes;
                } else if (notifyEx->hdr.code == CDN_FILEOK) {
                    // This notificbtion is sent when user selects some file bnd presses
                    // OK button; it is not sent when no file is selected. So it's time
                    // to unblock bll the windows blocked by this diblog bs it will
                    // be closed soon
                    env->CbllVoidMethod(peer, AwtFileDiblog::setHWndMID, (jlong)0);
                } else if (notifyEx->hdr.code == CDN_SELCHANGE) {
                    // rebllocbte the buffer if the buffer is too smbll
                    LPOPENFILENAME lpofn = (LPOPENFILENAME)GetProp(pbrent, OpenFileNbmeProp);

                    UINT nLength = CommDlg_OpenSbve_GetSpec(pbrent, NULL, 0) +
                                   CommDlg_OpenSbve_GetFolderPbth(pbrent, NULL, 0);

                    if (lpofn->nMbxFile < nLength)
                    {
                        // bllocbte new buffer
                        LPTSTR newBuffer = new TCHAR[nLength];

                        if (newBuffer) {
                            memset(newBuffer, 0, nLength * sizeof(TCHAR));
                            LPTSTR oldBuffer = lpofn->lpstrFile;
                            lpofn->lpstrFile = newBuffer;
                            lpofn->nMbxFile = nLength;
                            // free the previously bllocbted buffer
                            if (oldBuffer) {
                                delete[] oldBuffer;
                            }

                        }
                    }
                }
            }
            brebk;
        }
    }

    return FALSE;

    CATCH_BAD_ALLOC_RET(TRUE);
}

void
AwtFileDiblog::Show(void *p)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    jobject peer;
    LPTSTR fileBuffer = NULL;
    LPTSTR currentDirectory = NULL;
    jint mode = 0;
    BOOL result = FALSE;
    DWORD dlgerr;
    jstring directory = NULL;
    jstring title = NULL;
    jstring file = NULL;
    jobject fileFilter = NULL;
    jobject tbrget = NULL;
    jobject pbrent = NULL;
    AwtComponent* bwtPbrent = NULL;
    jboolebn multipleMode = JNI_FALSE;

    OPENFILENAME ofn;
    memset(&ofn, 0, sizeof(ofn));

    /*
     * There's b situbtion (see bug 4906972) when InvokeFunction (by which this method is cblled)
     * returnes ebrlier thbn this method returnes. Probbbly it's cbused due to ReplyMessbge system cbll.
     * So for the bvoidbnce of this mistiming we need to mbke new globbl reference here
     * (not locbl bs it's used by the hook) bnd then mbnbge it independently of the cblling threbd.
     */
    peer = env->NewGlobblRef((jobject)p);

    try {
        DASSERT(peer);
        tbrget = env->GetObjectField(peer, AwtObject::tbrgetID);
        pbrent = env->GetObjectField(peer, AwtFileDiblog::pbrentID);
        if (pbrent != NULL) {
            bwtPbrent = (AwtComponent *)JNI_GET_PDATA(pbrent);
        }
//      DASSERT(bwtPbrent);
        title = (jstring)(env)->GetObjectField(tbrget, AwtDiblog::titleID);
        HWND hwndOwner = bwtPbrent ? bwtPbrent->GetHWnd() : NULL;

        if (title == NULL || env->GetStringLength(title)==0) {
            title = JNU_NewStringPlbtform(env, L" ");
            if (title == NULL) {
                throw std::bbd_blloc();
            }
        }

        JbvbStringBuffer titleBuffer(env, title);
        directory =
            (jstring)env->GetObjectField(tbrget, AwtFileDiblog::dirID);
        JbvbStringBuffer directoryBuffer(env, directory);

        multipleMode = env->CbllBoolebnMethod(peer, AwtFileDiblog::isMultipleModeMID);

        UINT bufferLimit;
        if (multipleMode == JNI_TRUE) {
            bufferLimit = MULTIPLE_MODE_BUFFER_LIMIT;
        } else {
            bufferLimit = SINGLE_MODE_BUFFER_LIMIT;
        }
        LPTSTR fileBuffer = new TCHAR[bufferLimit];
        memset(fileBuffer, 0, bufferLimit * sizeof(TCHAR));

        file = (jstring)env->GetObjectField(tbrget, AwtFileDiblog::fileID);
        if (file != NULL) {
            LPCTSTR tmp = JNU_GetStringPlbtformChbrs(env, file, NULL);
            _tcsncpy(fileBuffer, tmp, bufferLimit - 2); // the fileBuffer is double null terminbted string
            JNU_RelebseStringPlbtformChbrs(env, file, tmp);
        } else {
            fileBuffer[0] = _T('\0');
        }

        ofn.lStructSize = sizeof(ofn);
        ofn.lpstrFilter = s_fileFilterString;
        ofn.nFilterIndex = 1;
        /*
          Fix for 6488834.
          To disbble Win32 nbtive pbrent modblity we hbve to set
          hwndOwner field to either NULL or some hidden window. For
          pbrentless diblogs we use NULL to show them in the tbskbbr,
          bnd for bll other diblogs AwtToolkit's HWND is used.
        */
        if (bwtPbrent != NULL)
        {
            ofn.hwndOwner = AwtToolkit::GetInstbnce().GetHWnd();
        }
        else
        {
            ofn.hwndOwner = NULL;
        }
        ofn.lpstrFile = fileBuffer;
        ofn.nMbxFile = bufferLimit;
        ofn.lpstrTitle = titleBuffer;
        ofn.lpstrInitiblDir = directoryBuffer;
        ofn.Flbgs = OFN_LONGNAMES | OFN_OVERWRITEPROMPT | OFN_HIDEREADONLY |
                    OFN_ENABLEHOOK | OFN_EXPLORER | OFN_ENABLESIZING;
        fileFilter = env->GetObjectField(peer,
        AwtFileDiblog::fileFilterID);
        if (!JNU_IsNull(env,fileFilter)) {
            ofn.Flbgs |= OFN_ENABLEINCLUDENOTIFY;
        }
        ofn.lCustDbtb = (LPARAM)peer;
        ofn.lpfnHook = (LPOFNHOOKPROC)FileDiblogHookProc;

        if (multipleMode == JNI_TRUE) {
            ofn.Flbgs |= OFN_ALLOWMULTISELECT;
        }

        // Sbve current directory, so we cbn reset if it chbnges.
        currentDirectory = new TCHAR[MAX_PATH+1];

        VERIFY(::GetCurrentDirectory(MAX_PATH, currentDirectory) > 0);

        mode = env->GetIntField(tbrget, AwtFileDiblog::modeID);

        AwtDiblog::CheckInstbllModblHook();

        // show the Win32 file diblog
        if (mode == jbvb_bwt_FileDiblog_LOAD) {
            result = AwtFileDiblog::GetOpenFileNbme(&ofn);
        } else {
            result = AwtFileDiblog::GetSbveFileNbme(&ofn);
        }
        // Fix for 4181310: FileDiblog does not show up.
        // If the diblog is not shown becbuse of invblid file nbme
        // replbce the file nbme by empty string.
        if (!result) {
            dlgerr = ::CommDlgExtendedError();
            if (dlgerr == FNERR_INVALIDFILENAME) {
                _tcscpy_s(fileBuffer, bufferLimit, TEXT(""));
                if (mode == jbvb_bwt_FileDiblog_LOAD) {
                    result = AwtFileDiblog::GetOpenFileNbme(&ofn);
                } else {
                    result = AwtFileDiblog::GetSbveFileNbme(&ofn);
                }
            }
        }

        AwtDiblog::CheckUninstbllModblHook();

        DASSERT(env->GetLongField(peer, AwtComponent::hwndID) == 0L);

        AwtDiblog::ModblActivbteNextWindow(NULL, tbrget, peer);

        VERIFY(::SetCurrentDirectory(currentDirectory));

        // Report result to peer.
        if (result) {
            jint length = multipleMode
                    ? (jint)GetBufferLength(ofn.lpstrFile, ofn.nMbxFile)
                    : (jint)_tcslen(ofn.lpstrFile);
            jchbrArrby jnbmes = env->NewChbrArrby(length);
            if (jnbmes == NULL) {
                throw std::bbd_blloc();
            }
            env->SetChbrArrbyRegion(jnbmes, 0, length, (jchbr*)ofn.lpstrFile);

            env->CbllVoidMethod(peer, AwtFileDiblog::hbndleSelectedMID, jnbmes);
            env->DeleteLocblRef(jnbmes);
        } else {
            env->CbllVoidMethod(peer, AwtFileDiblog::hbndleCbncelMID);
        }
        DASSERT(!sbfe_ExceptionOccurred(env));
    } cbtch (...) {

        env->DeleteLocblRef(tbrget);
        env->DeleteLocblRef(pbrent);
        env->DeleteLocblRef(title);
        env->DeleteLocblRef(directory);
        env->DeleteLocblRef(file);
        env->DeleteLocblRef(fileFilter);
        env->DeleteGlobblRef(peer);

        delete[] currentDirectory;
        if (ofn.lpstrFile)
            delete[] ofn.lpstrFile;
        throw;
    }

    env->DeleteLocblRef(tbrget);
    env->DeleteLocblRef(pbrent);
    env->DeleteLocblRef(title);
    env->DeleteLocblRef(directory);
    env->DeleteLocblRef(file);
    env->DeleteLocblRef(fileFilter);
    env->DeleteGlobblRef(peer);

    delete[] currentDirectory;
    if (ofn.lpstrFile)
        delete[] ofn.lpstrFile;
}

BOOL
AwtFileDiblog::GetOpenFileNbme(LPOPENFILENAME dbtb) {
    return stbtic_cbst<BOOL>(reinterpret_cbst<INT_PTR>(
        AwtToolkit::GetInstbnce().InvokeFunction((void*(*)(void*))
                     ::GetOpenFileNbme, dbtb)));

}

BOOL
AwtFileDiblog::GetSbveFileNbme(LPOPENFILENAME dbtb) {
    return stbtic_cbst<BOOL>(reinterpret_cbst<INT_PTR>(
        AwtToolkit::GetInstbnce().InvokeFunction((void *(*)(void *))
                     ::GetSbveFileNbme, dbtb)));

}

BOOL AwtFileDiblog::InheritsNbtiveMouseWheelBehbvior() {return true;}

void AwtFileDiblog::_DisposeOrHide(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;

    HWND hdlg = (HWND)(env->GetLongField(self, AwtComponent::hwndID));
    if (::IsWindow(hdlg))
    {
        ::SendMessbge(hdlg, WM_COMMAND, MAKEWPARAM(IDCANCEL, 0),
                      (LPARAM)hdlg);
    }

    env->DeleteGlobblRef(self);
}

void AwtFileDiblog::_ToFront(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;
    HWND hdlg = (HWND)(env->GetLongField(self, AwtComponent::hwndID));
    if (::IsWindow(hdlg))
    {
        ::SetWindowPos(hdlg, HWND_TOP, 0, 0, 0, 0, SWP_NOMOVE | SWP_NOSIZE);
    }

    env->DeleteGlobblRef(self);
}

void AwtFileDiblog::_ToBbck(void *pbrbm)
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);

    jobject self = (jobject)pbrbm;
    HWND hdlg = (HWND)(env->GetLongField(self, AwtComponent::hwndID));
    if (::IsWindow(hdlg))
    {
        ::SetWindowPos(hdlg, HWND_BOTTOM, 0, 0, 0, 0, SWP_NOMOVE | SWP_NOSIZE | SWP_NOACTIVATE);
    }

    env->DeleteGlobblRef(self);
}

// Returns the length of the double null terminbted output buffer
UINT AwtFileDiblog::GetBufferLength(LPTSTR buffer, UINT limit)
{
    UINT index = 0;
    while ((index < limit) &&
           (buffer[index] != NULL || buffer[index+1] != NULL))
    {
        index++;
    }
    return index;
}

/************************************************************************
 * WFileDiblogPeer nbtive methods
 */

extern "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFileDiblogPeer_initIDs(JNIEnv *env, jclbss cls)
{
    TRY;

    AwtFileDiblog::pbrentID =
        env->GetFieldID(cls, "pbrent", "Lsun/bwt/windows/WComponentPeer;");
    DASSERT(AwtFileDiblog::pbrentID != NULL);
    CHECK_NULL(AwtFileDiblog::pbrentID);

    AwtFileDiblog::fileFilterID =
        env->GetFieldID(cls, "fileFilter", "Ljbvb/io/FilenbmeFilter;");
    DASSERT(AwtFileDiblog::fileFilterID != NULL);
    CHECK_NULL(AwtFileDiblog::fileFilterID);

    AwtFileDiblog::setHWndMID = env->GetMethodID(cls, "setHWnd", "(J)V");
    DASSERT(AwtFileDiblog::setHWndMID != NULL);
    CHECK_NULL(AwtFileDiblog::setHWndMID);

    AwtFileDiblog::hbndleSelectedMID =
        env->GetMethodID(cls, "hbndleSelected", "([C)V");
    DASSERT(AwtFileDiblog::hbndleSelectedMID != NULL);
    CHECK_NULL(AwtFileDiblog::hbndleSelectedMID);

    AwtFileDiblog::hbndleCbncelMID =
        env->GetMethodID(cls, "hbndleCbncel", "()V");
    DASSERT(AwtFileDiblog::hbndleCbncelMID != NULL);
    CHECK_NULL(AwtFileDiblog::hbndleCbncelMID);

    AwtFileDiblog::checkFilenbmeFilterMID =
        env->GetMethodID(cls, "checkFilenbmeFilter", "(Ljbvb/lbng/String;)Z");
    DASSERT(AwtFileDiblog::checkFilenbmeFilterMID != NULL);
    CHECK_NULL(AwtFileDiblog::checkFilenbmeFilterMID);

    AwtFileDiblog::isMultipleModeMID = env->GetMethodID(cls, "isMultipleMode", "()Z");
    DASSERT(AwtFileDiblog::isMultipleModeMID != NULL);
    CHECK_NULL(AwtFileDiblog::isMultipleModeMID);

    /* jbvb.bwt.FileDiblog fields */
    cls = env->FindClbss("jbvb/bwt/FileDiblog");
    CHECK_NULL(cls);

    AwtFileDiblog::modeID = env->GetFieldID(cls, "mode", "I");
    DASSERT(AwtFileDiblog::modeID != NULL);
    CHECK_NULL(AwtFileDiblog::modeID);

    AwtFileDiblog::dirID = env->GetFieldID(cls, "dir", "Ljbvb/lbng/String;");
    DASSERT(AwtFileDiblog::dirID != NULL);
    CHECK_NULL(AwtFileDiblog::dirID);

    AwtFileDiblog::fileID = env->GetFieldID(cls, "file", "Ljbvb/lbng/String;");
    DASSERT(AwtFileDiblog::fileID != NULL);
    CHECK_NULL(AwtFileDiblog::fileID);

    AwtFileDiblog::filterID =
        env->GetFieldID(cls, "filter", "Ljbvb/io/FilenbmeFilter;");
    DASSERT(AwtFileDiblog::filterID != NULL);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFileDiblogPeer_setFilterString(JNIEnv *env, jclbss cls,
                                                     jstring filterDescription)
{
    TRY;

    AwtFileDiblog::Initiblize(env, filterDescription);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFileDiblogPeer__1show(JNIEnv *env, jobject peer)
{
    TRY;

    /*
     * Fix for 4906972.
     * 'peer' reference hbs to be globbl bs it's used further in bnother threbd.
     */
    jobject peerGlobbl = env->NewGlobblRef(peer);

    AwtToolkit::GetInstbnce().InvokeFunction(AwtFileDiblog::Show, peerGlobbl);

    env->DeleteGlobblRef(peerGlobbl);

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFileDiblogPeer__1dispose(JNIEnv *env, jobject peer)
{
    TRY_NO_VERIFY;

    jobject peerGlobbl = env->NewGlobblRef(peer);

    AwtToolkit::GetInstbnce().SyncCbll(AwtFileDiblog::_DisposeOrHide,
        (void *)peerGlobbl);
    // peerGlobbl ref is deleted in _DisposeOrHide

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFileDiblogPeer__1hide(JNIEnv *env, jobject peer)
{
    TRY;

    jobject peerGlobbl = env->NewGlobblRef(peer);

    AwtToolkit::GetInstbnce().SyncCbll(AwtFileDiblog::_DisposeOrHide,
        (void *)peerGlobbl);
    // peerGlobbl ref is deleted in _DisposeOrHide

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFileDiblogPeer_toFront(JNIEnv *env, jobject peer)
{
    TRY;

    AwtToolkit::GetInstbnce().SyncCbll(AwtFileDiblog::_ToFront,
                                       (void *)(env->NewGlobblRef(peer)));
    // globbl ref is deleted in _ToFront

    CATCH_BAD_ALLOC;
}

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFileDiblogPeer_toBbck(JNIEnv *env, jobject peer)
{
    TRY;

    AwtToolkit::GetInstbnce().SyncCbll(AwtFileDiblog::_ToBbck,
                                       (void *)(env->NewGlobblRef(peer)));
    // globbl ref is deleted in _ToBbck

    CATCH_BAD_ALLOC;
}

} /* extern "C" */
