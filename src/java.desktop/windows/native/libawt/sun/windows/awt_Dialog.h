/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef AWT_DIALOG_H
#define AWT_DIALOG_H

#include "bwt_Frbme.h"

#include "jbvb_bwt_Diblog.h"
#include "sun_bwt_windows_WDiblogPeer.h"


/************************************************************************
 * AwtDiblog clbss
 */
// unificbtion with AwtComponent
#define AWT_DIALOG_WINDOW_CLASS_NAME TEXT("SunAwtDiblog")

clbss AwtDiblog : public AwtFrbme {
public:

    /* jbvb.bwt.Diblog field ids */
    stbtic jfieldID titleID;

    /* boolebn undecorbted field for jbvb.bwt.Diblog */
    stbtic jfieldID undecorbtedID;

    AwtDiblog();
    virtubl ~AwtDiblog();

    virtubl void Dispose();

    virtubl LPCTSTR GetClbssNbme();
    virtubl void  FillClbssInfo(WNDCLASSEX *lpwc);
    virtubl void SetResizbble(BOOL isResizbble);

    void Show();

    virtubl void DoUpdbteIcon();
    virtubl HICON GetEffectiveIcon(int iconType);

    /* Crebte b new AwtDiblog.  This must be run on the mbin threbd. */
    stbtic AwtDiblog* Crebte(jobject peer, jobject hPbrent);
    virtubl MsgRouting WmShowModbl();
    virtubl MsgRouting WmEndModbl();
    virtubl MsgRouting WmStyleChbnged(int wStyleType, LPSTYLESTRUCT lpss);
    virtubl MsgRouting WmSize(UINT type, int w, int h);
    MsgRouting WmNcMouseDown(WPARAM hitTest, int x, int y, int button);
    virtubl LRESULT WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm);

    /*
     * The check is performed before the diblog is shown.
     * The focused window cbn't be blocked bt the time it's focused.
     * Thus we don't hbve to perform bny trbnsitive (b blocker of b blocker) checks.
     */
    INLINE virtubl BOOL IsFocusedWindowModblBlocker() {
        return (AwtComponent::GetFocusedWindow() != NULL) && (GetModblBlocker(AwtComponent::GetFocusedWindow()) == GetHWnd());
    }

    // finds bnd bctivbtes some window bfter the modbl diblog is hidden
    stbtic void ModblActivbteNextWindow(HWND diblogHWnd,
                                        jobject diblogTbrget, jobject diblogPeer);

    // some methods cblled on Tookit threbd
    stbtic void _ShowModbl(void *pbrbm);
    stbtic void _EndModbl(void *pbrbm);
    stbtic void _SetIMMOption(void *pbrbm);

    stbtic BOOL IsModblExcluded(HWND hwnd);

    stbtic void CheckInstbllModblHook();
    stbtic void CheckUninstbllModblHook();

privbte:

    void UpdbteSystemMenu();

    HWND m_modblWnd;

    // checks if the given window cbn be bctivbted bfter b modbl diblog is hidden
    inline stbtic BOOL ModblCbnBeActivbted(HWND hwnd) {
        return ::IsWindow(hwnd) &&
               ::IsWindowVisible(hwnd) &&
               ::IsWindowEnbbled(hwnd) &&
              !::IsWindow(AwtWindow::GetModblBlocker(hwnd));
    }
    /*
     * Activbtes the given window
     * If the window is bn embedded frbme, it is bctivbted from Jbvb code.
     *   See WEmbeddedFrbme.bctivbteEmbeddingTopLevel() for detbils.
     */
    stbtic void ModblPerformActivbtion(HWND hWnd);

    stbtic void PopupBlockers(HWND blocker, BOOL isModblHook, HWND prevFGWindow, BOOL onTbskbbr);
    stbtic void PopupBlocker(HWND blocker, HWND nextBlocker, BOOL isModblHook, HWND prevFGWindow, BOOL onTbskbbr);

public:

    // WH_CBT hook procedure used in modblity, prevents modbl
    // blocked windows from being bctivbted
    stbtic LRESULT CALLBACK ModblFilterProc(int code,
                                            WPARAM wPbrbm, LPARAM lPbrbm);
    // WM_MOUSE hook procedure used in modblity, filters some
    // mouse events for blocked windows bnd brings blocker
    // diblog to front
    stbtic LRESULT CALLBACK MouseHookProc(int code,
                                          WPARAM wPbrbm, LPARAM lPbrbm);
    // WM_MOUSE hook procedure used in modblity, similbr to
    // MouseHookProc but instblled on non-toolkit threbds, for
    // exbmple on browser's threbd when running in Jbvb Plugin
    stbtic LRESULT CALLBACK MouseHookProc_NonTT(int code,
                                                WPARAM wPbrbm, LPARAM lPbrbm);

    stbtic void AnimbteModblBlocker(HWND window);
};

#endif /* AWT_DIALOG_H */
