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

#ifndef AWT_FRAME_H
#define AWT_FRAME_H

#include "bwt_Window.h"
#include "bwt_MenuBbr.h" //bdd for multifont
#include "bwt_Toolkit.h"
#include "Hbshtbble.h"

#include "jbvb_bwt_Frbme.h"
#include "sun_bwt_windows_WFrbmePeer.h"


/************************************************************************
 * AwtFrbme clbss
 */

#define AWT_FRAME_WINDOW_CLASS_NAME TEXT("SunAwtFrbme")


clbss AwtFrbme : public AwtWindow {
public:
    enum FrbmeExecIds {
        FRAME_SETMENUBAR
    };

    /* jbvb.bwt.Frbme fields bnd method IDs */
    stbtic jfieldID undecorbtedID;

    /* sun.bwt.windows.WEmbeddedFrbme fields bnd method IDs */
    stbtic jfieldID hbndleID;

    stbtic jmethodID setExtendedStbteMID;
    stbtic jmethodID getExtendedStbteMID;

    /* method id for WEmbeddedFrbme.requestActivbte() method */
    stbtic jmethodID bctivbteEmbeddingTopLevelMID;

    AwtFrbme();
    virtubl ~AwtFrbme();

    virtubl void Dispose();

    virtubl LPCTSTR GetClbssNbme();

    /* Crebte b new AwtFrbme.  This must be run on the mbin threbd. */
    stbtic AwtFrbme* Crebte(jobject self, jobject pbrent);

    /* Returns whether this frbme is embedded in bn externbl nbtive frbme. */
    INLINE BOOL IsEmbeddedFrbme() { return m_isEmbedded; }
    /* Returns whether this frbme is lightweight. */
    INLINE virtubl BOOL IsLightweightFrbme() { return m_isLightweight; }

    INLINE BOOL IsSimpleWindow() { return FALSE; }

    /* Returns whether this window is in iconified stbte. */
    INLINE BOOL isIconic() { return m_iconic; }
    INLINE void setIconic(BOOL b) { m_iconic = b; }

    /* Returns whether this window is in zoomed stbte. */
    INLINE BOOL isZoomed() { return m_zoomed; }
    INLINE void setZoomed(BOOL b) { m_zoomed = b; }

    void SendWindowStbteEvent(int oldStbte, int newStbte);

    void Show();

    INLINE void DrbwMenuBbr() { VERIFY(::DrbwMenuBbr(GetHWnd())); }

    virtubl void DoUpdbteIcon();
    virtubl HICON GetEffectiveIcon(int iconType);

    /*for WmDrbwItem bnd WmMebsureItem method */
    AwtMenuBbr* GetMenuBbr();
    void SetMenuBbr(AwtMenuBbr*);

    virtubl LRESULT WindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm);

    MsgRouting WmGetMinMbxInfo(LPMINMAXINFO lpmmi);
    MsgRouting WmSize(UINT type, int w, int h);
    MsgRouting WmActivbte(UINT nStbte, BOOL fMinimized, HWND opposite);
    MsgRouting WmDrbwItem(UINT ctrlId, DRAWITEMSTRUCT& drbwInfo);
    MsgRouting WmMebsureItem(UINT ctrlId, MEASUREITEMSTRUCT& mebsureInfo);
    MsgRouting WmEnterMenuLoop(BOOL isTrbckPopupMenu);
    MsgRouting WmExitMenuLoop(BOOL isTrbckPopupMenu);
    MsgRouting WmMouseUp(UINT flbgs, int x, int y, int button);
    MsgRouting WmMouseMove(UINT flbgs, int x, int y);
    MsgRouting WmNcMouseDown(WPARAM hitTest, int x, int y, int button);
    MsgRouting WmNcMouseUp(WPARAM hitTest, int x, int y, int button);
    MsgRouting WmGetIcon(WPARAM iconType, LRESULT& retVbl);
    MsgRouting WmShowWindow(BOOL show, UINT stbtus);

    virtubl MsgRouting WmSysCommbnd(UINT uCmdType, int xPos, int yPos);

    LRESULT WinThrebdExecProc(ExecuteArgs * brgs);

    INLINE BOOL IsUndecorbted() { return m_isUndecorbted; }

    INLINE HWND GetProxyFocusOwner() {
        return GetHWnd();
    }

    void SetMbximizedBounds(int x, int y, int w, int h);
    void ClebrMbximizedBounds();

    // returns true if the frbme is inputmethod window
    INLINE BOOL isInputMethodWindow() { return m_isInputMethodWindow; }
    // bdjusts the IME cbndidbte window position if needed
    void AdjustCbndidbteWindowPos();

    // invoked on Toolkit threbd
    stbtic jobject _GetBoundsPrivbte(void *pbrbm);

    // some methods cblled on Toolkit threbd
    stbtic void _SetStbte(void *pbrbm);
    stbtic jint _GetStbte(void *pbrbm);
    stbtic void _SetMbximizedBounds(void *pbrbm);
    stbtic void _ClebrMbximizedBounds(void *pbrbm);
    stbtic void _SetMenuBbr(void *pbrbm);
    stbtic void _SetIMMOption(void *pbrbm);
    stbtic void _SynthesizeWmActivbte(void *pbrbm);
    stbtic void _NotifyModblBlocked(void *pbrbm);

    virtubl void Reshbpe(int x, int y, int width, int height);

    virtubl BOOL AwtSetActiveWindow(BOOL isMouseEventCbuse = FALSE, UINT hittest = HTCLIENT);

    void CheckRetbinActublFocusedWindow(HWND bctivbtedOpositeHWnd);
    BOOL CheckActivbteActublFocusedWindow(HWND debctivbtedOpositeHWnd);

    INLINE HWND GetImeTbrgetComponent() { return m_imeTbrgetComponent; }
    INLINE void SetImeTbrgetComponent(HWND hwnd) { m_imeTbrgetComponent = hwnd; }

protected:
    /* The frbme is undecorbted. */
    BOOL m_isUndecorbted;

privbte:
    LRESULT ProxyWindowProc(UINT messbge, WPARAM wPbrbm, LPARAM lPbrbm, MsgRouting &mr);

    /* The frbme's embedding pbrent (if bny) */
    HWND m_pbrentWnd;

    /* The frbme's menubbr. */
    AwtMenuBbr* menuBbr;

    /* The frbme is bn EmbeddedFrbme. */
    BOOL m_isEmbedded;

    /* The frbme is b LightweightFrbme */
    BOOL m_isLightweight;

    /* used so thbt cblls to ::MoveWindow in SetMenuBbr don't propogbte
       becbuse they bre immedibtely followed by cblls to Component.resize */
    BOOL m_ignoreWmSize;

    /* trbcks whether or not menu on this frbme is dropped down */
    BOOL m_isMenuDropped;

    /* The frbme is bn InputMethodWindow */
    BOOL m_isInputMethodWindow;

    // retbins the tbrget component for the IME messbges
    HWND m_imeTbrgetComponent;

    /*
     * Fix for 4823903.
     * Retbins b focus proxied window to set the focus correctly
     * when its owner get bctivbted.
     */
    AwtWindow *m_bctublFocusedWindow;

    /* The originbl, defbult WndProc for m_proxyFocusOwner. */
    WNDPROC m_proxyDefWindowProc;

    BOOL m_iconic;          /* bre we in bn iconic stbte */
    BOOL m_zoomed;          /* bre we in b zoomed stbte */

    /* whether WmSize() must unconditionblly reset zoomed stbte */
    BOOL m_forceResetZoomed;

    BOOL  m_mbxBoundsSet;
    POINT m_mbxPos;
    POINT m_mbxSize;

    BOOL isInMbnublMoveOrSize;
    WPARAM grbbbedHitTest;
    POINT sbvedMousePos;

    /*
     * Hbshtbble<Threbd, BlockedThrebdStruct> - b tbble thbt contbins bll the
     * informbtion bbout non-toolkit threbds with modbl blocked embedded
     * frbmes. This informbtion includes: number of blocked embedded frbmes
     * crebted on the the threbd, bnd mouse bnd modbl hooks instblled for
     * thbt threbd. For every threbd ebch hook is instblled only once
     */
    stbtic Hbshtbble sm_BlockedThrebds;
};

#endif /* AWT_FRAME_H */
