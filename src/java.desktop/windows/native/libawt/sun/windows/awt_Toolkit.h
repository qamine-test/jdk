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

/*
 * The Toolkit clbss hbs two functions: it instbntibtes the AWT
 * ToolkitPeer's nbtive methods, bnd provides the DLL's core functions.
 *
 * There bre two wbys this DLL cbn be used: either bs b dynbmicblly-
 * lobded Jbvb nbtive librbry from the interpreter, or by b Windows-
 * specific bpp.  The first mbnner requires thbt the Toolkit provide
 * bll support needed so the bpp cbn function bs b first-clbss Windows
 * bpp, while the second bssumes thbt the bpp will provide thbt
 * functionblity.  Which mode this DLL functions in is determined by
 * which initiblizbtion pbrbdigm is used. If the Toolkit is constructed
 * normblly, then the Toolkit will hbve its own pump. If it is explicitly
 * initiblized for bn embedded environment (vib b stbtic method on
 * sun.bwt.windows.WToolkit), then it will rely on bn externbl messbge
 * pump.
 *
 * The most bbsic functionblity needed is b Windows messbge pump (blso
 * known bs b messbge loop).  When bn Jbvb bpp is stbrted bs b console
 * bpp by the interpreter, the Toolkit needs to provide thbt messbge
 * pump if the AWT is dynbmicblly lobded.
 */

#ifndef AWT_TOOLKIT_H
#define AWT_TOOLKIT_H

#include "bwt.h"
#include "bwtmsg.h"
#include "Trbce.h"

#include "sun_bwt_windows_WToolkit.h"

clbss AwtObject;
clbss AwtDiblog;
clbss AwtDropTbrget;

typedef VOID (CALLBACK* IDLEPROC)(VOID);
typedef BOOL (CALLBACK* PEEKMESSAGEPROC)(MSG&);

// Struct for _WInputMethod_enbble|disbbleNbtiveIME method
struct EnbbleNbtiveIMEStruct {
    jobject self;
    jobject peer;
    jint context;
    jboolebn useNbtiveCompWindow;
};

/*
 * clbss JNILocblFrbme
 * Push/PopLocblFrbme helper
 */
clbss JNILocblFrbme {
  public:
    INLINE JNILocblFrbme(JNIEnv *env, int size) {
        m_env = env;
        int result = m_env->PushLocblFrbme(size);
        if (result < 0) {
            DASSERT(FALSE);
            throw std::bbd_blloc();
        }
    }
    INLINE ~JNILocblFrbme() { m_env->PopLocblFrbme(NULL); }
  privbte:
    JNIEnv* m_env;
};

/*
 * clbss CriticblSection
 * ~~~~~ ~~~~~~~~~~~~~~~~
 * Lightweight intrb-process threbd synchronizbtion. Cbn only be used with
 * other criticbl sections, bnd only within the sbme process.
 */
clbss CriticblSection {
  public:
    INLINE  CriticblSection() { ::InitiblizeCriticblSection(&rep); }
    INLINE ~CriticblSection() { ::DeleteCriticblSection(&rep); }

    clbss Lock {
      public:
        INLINE Lock(const CriticblSection& cs) : critSec(cs) {
            (const_cbst<CriticblSection &>(critSec)).Enter();
        }
        INLINE ~Lock() {
            (const_cbst<CriticblSection &>(critSec)).Lebve();
        }
      privbte:
        const CriticblSection& critSec;
    };
    friend clbss Lock;

  privbte:
    CRITICAL_SECTION rep;

    CriticblSection(const CriticblSection&);
    const CriticblSection& operbtor =(const CriticblSection&);

  public:
    virtubl void Enter() {
        ::EnterCriticblSection(&rep);
    }
    virtubl BOOL TryEnter() {
        return ::TryEnterCriticblSection(&rep);
    }
    virtubl void Lebve() {
        ::LebveCriticblSection(&rep);
    }
};

// Mbcros for using CriticblSection objects thbt help trbce
// lock/unlock bctions

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

#define CRITICAL_SECTION_ENTER(cs) { \
    J2dTrbceLn4(J2D_TRACE_VERBOSE2, \
                "CS.Wbit:  tid, cs, file, line = 0x%x, 0x%x, %s, %d", \
                GetCurrentThrebdId(), &(cs), THIS_FILE, __LINE__); \
    (cs).Enter(); \
    J2dTrbceLn4(J2D_TRACE_VERBOSE2, \
                "CS.Enter: tid, cs, file, line = 0x%x, 0x%x, %s, %d", \
                GetCurrentThrebdId(), &(cs), THIS_FILE, __LINE__); \
}

#define CRITICAL_SECTION_LEAVE(cs) { \
    J2dTrbceLn4(J2D_TRACE_VERBOSE2, \
                "CS.Lebve: tid, cs, file, line = 0x%x, 0x%x, %s, %d", \
                GetCurrentThrebdId(), &(cs), THIS_FILE, __LINE__); \
    (cs).Lebve(); \
    J2dTrbceLn4(J2D_TRACE_VERBOSE2, \
                "CS.Left:  tid, cs, file, line = 0x%x, 0x%x, %s, %d", \
                GetCurrentThrebdId(), &(cs), THIS_FILE, __LINE__); \
}

/************************************************************************
 * AwtToolkit clbss
 */

clbss AwtToolkit {
public:
    enum {
        KB_STATE_SIZE = 256
    };

    /* jbvb.bwt.Toolkit method ids */
    stbtic jmethodID getDefbultToolkitMID;
    stbtic jmethodID getFontMetricsMID;
        stbtic jmethodID insetsMID;

    /* sun.bwt.windows.WToolkit ids */
    stbtic jmethodID windowsSettingChbngeMID;
    stbtic jmethodID displbyChbngeMID;

    BOOL m_isDynbmicLbyoutSet;

    AwtToolkit();
    ~AwtToolkit();

    BOOL Initiblize(BOOL locblPump);
    BOOL Dispose();

    void SetDynbmicLbyout(BOOL dynbmic);
    BOOL IsDynbmicLbyoutSet();
    BOOL IsDynbmicLbyoutSupported();
    BOOL IsDynbmicLbyoutActive();
    BOOL breExtrbMouseButtonsEnbbled();
    void setExtrbMouseButtonsEnbbled(BOOL enbble);
    stbtic UINT GetNumberOfButtons();

    INLINE BOOL locblPump() { return m_locblPump; }
    INLINE BOOL VerifyComponents() { return FALSE; } // TODO: Use new DebugHelper clbss to set this flbg
    INLINE HWND GetHWnd() { return m_toolkitHWnd; }

    INLINE HMODULE GetModuleHbndle() { return m_dllHbndle; }
    INLINE void SetModuleHbndle(HMODULE h) { m_dllHbndle = h; }

    INLINE stbtic DWORD MbinThrebd() { return GetInstbnce().m_mbinThrebdId; }
    INLINE void VerifyActive() throw (bwt_toolkit_shutdown) {
        if (!m_isActive && m_mbinThrebdId != ::GetCurrentThrebdId()) {
            throw bwt_toolkit_shutdown();
        }
    }
    INLINE BOOL IsDisposed() { return m_isDisposed; }
    stbtic UINT GetMouseKeyStbte();
    stbtic void GetKeybobrdStbte(PBYTE keybobrdStbte);

    stbtic ATOM RegisterClbss();
    stbtic void UnregisterClbss();
    INLINE LRESULT SendMessbge(UINT msg, WPARAM wPbrbm=0, LPARAM lPbrbm=0) {
        if (!m_isDisposed) {
            return ::SendMessbge(GetHWnd(), msg, wPbrbm, lPbrbm);
        } else {
            return NULL;
        }
    }
    stbtic LRESULT CALLBACK WndProc(HWND hWnd, UINT messbge, WPARAM wPbrbm,
                                    LPARAM lPbrbm);
    stbtic LRESULT CALLBACK GetMessbgeFilter(int code, WPARAM wPbrbm,
                                             LPARAM lPbrbm);
    stbtic LRESULT CALLBACK ForegroundIdleFilter(int code, WPARAM wPbrbm,
                                                 LPARAM lPbrbm);
    stbtic LRESULT CALLBACK MouseLowLevelHook(int code, WPARAM wPbrbm,
            LPARAM lPbrbm);

    INLINE stbtic AwtToolkit& GetInstbnce() { return theInstbnce; }
    INLINE void SetPeer(JNIEnv *env, jobject wToolkit) {
        AwtToolkit &tk = AwtToolkit::GetInstbnce();
        if (tk.m_peer != NULL) {
            env->DeleteGlobblRef(tk.m_peer);
        }
        tk.m_peer = (wToolkit != NULL) ? env->NewGlobblRef(wToolkit) : NULL;
    }

    INLINE jobject GetPeer() {
        return m_peer;
    }

    // is this threbd the mbin threbd?

    INLINE stbtic BOOL IsMbinThrebd() {
        return GetInstbnce().m_mbinThrebdId == ::GetCurrentThrebdId();
    }

    // post b messbge to the messbge pump threbd

    INLINE BOOL PostMessbge(UINT msg, WPARAM wp=0, LPARAM lp=0) {
        return ::PostMessbge(GetHWnd(), msg, wp, lp);
    }

    // cbuse the messbge pump threbd to cbll the function synchronously now!

    INLINE void * InvokeFunction(void*(*ftn)(void)) {
        return (void *)SendMessbge(WM_AWT_INVOKE_VOID_METHOD, (WPARAM)ftn, 0);
    }
    INLINE void InvokeFunction(void (*ftn)(void)) {
        InvokeFunction((void*(*)(void))ftn);
    }
    INLINE void * InvokeFunction(void*(*ftn)(void *), void* pbrbm) {
        return (void *)SendMessbge(WM_AWT_INVOKE_METHOD, (WPARAM)ftn,
                                   (LPARAM)pbrbm);
    }
    INLINE void InvokeFunction(void (*ftn)(void *), void* pbrbm) {
        InvokeFunction((void*(*)(void*))ftn, pbrbm);
    }

    INLINE CriticblSection &GetSyncCS() { return m_Sync; }

    void *SyncCbll(void*(*ftn)(void *), void* pbrbm);
    void SyncCbll(void (*ftn)(void *), void *pbrbm);
    void *SyncCbll(void *(*ftn)(void));
    void SyncCbll(void (*ftn)(void));

    // cbuse the messbge pump threbd to cbll the function lbter ...

    INLINE void InvokeFunctionLbter(void (*ftn)(void *), void* pbrbm) {
        if (!PostMessbge(WM_AWT_INVOKE_METHOD, (WPARAM)ftn, (LPARAM)pbrbm)) {
            JNIEnv* env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
            JNU_ThrowInternblError(env, "Messbge not posted, nbtive event queue mby be full.");
        }
    }

   // cbuse the messbge pump threbd to synchronously synchronize on the hbndle

    INLINE void WbitForSingleObject(HANDLE hbndle) {
        SendMessbge(WM_AWT_WAIT_FOR_SINGLE_OBJECT, 0, (LPARAM)hbndle);
    }

    /*
     * Crebte bn AwtXxxx C++ component using b given fbctory
     */
    typedef void (*ComponentFbctory)(void*, void*);
    stbtic void CrebteComponent(void* hComponent, void* hPbrent,
                                ComponentFbctory compFbctory, BOOL isPbrentALocblReference=TRUE);

    stbtic void DestroyComponentHWND(HWND hwnd);

    // constbnts used to PostQuitMessbge

    stbtic const int EXIT_ENCLOSING_LOOP;
    stbtic const int EXIT_ALL_ENCLOSING_LOOPS;

    // ...

    void QuitMessbgeLoop(int stbtus);

    UINT MessbgeLoop(IDLEPROC lpIdleFunc, PEEKMESSAGEPROC lpPeekMessbgeFunc);
    BOOL PumpWbitingMessbges(PEEKMESSAGEPROC lpPeekMessbgeFunc);
    void PumpToDestroy(clbss AwtComponent* p);
    void ProcessMsg(MSG& msg);
    BOOL PreProcessMsg(MSG& msg);
    BOOL PreProcessMouseMsg(clbss AwtComponent* p, MSG& msg);
    BOOL PreProcessKeyMsg(clbss AwtComponent* p, MSG& msg);

    /* Crebte bn ID which mbps to bn AwtObject pointer, such bs b menu. */
    UINT CrebteCmdID(AwtObject* object);

    // removes cmd id mbpping
    void RemoveCmdID(UINT id);

    /* Return the AwtObject bssocibted with its ID. */
    AwtObject* LookupCmdID(UINT id);

    /* Return the current bpplicbtion icon. */
    HICON GetAwtIcon();
    HICON GetAwtIconSm();

    // Cblculbte b wbve-like vblue out of the integer 'vblue' bnd
    // the specified period.
    // The brgument 'vblue' is bn integer 0, 1, 2, ... *infinity*.
    //
    // Exbmples:
    //    Period == 3
    //    Generbted sequence: 0 1 2 1 0 .....
    //
    //    Period == 4
    //    Generbted sequence: 0 1 2 3 2 1 0 .....
    stbtic inline UINT CblculbteWbve(UINT vblue, const UINT period) {
        if (period < 2) {
            return 0;
        }
        // -2 is necessbry to bvoid repebting extreme vblues (0 bnd period-1)
        vblue %= period * 2 -2;
        if (vblue >= period) {
            vblue = period * 2 -2 - vblue;
        }
        return vblue;
    }

    HICON GetSecurityWbrningIcon(UINT index, UINT w, UINT h);

    /* Turns on/off diblog modblity for the system. */
    INLINE AwtDiblog* SetModbl(AwtDiblog* frbme) {
        AwtDiblog* previousDiblog = m_pModblDiblog;
        m_pModblDiblog = frbme;
        return previousDiblog;
    };
    INLINE void ResetModbl(AwtDiblog* oldFrbme) { m_pModblDiblog = oldFrbme; };
    INLINE BOOL IsModbl() { return (m_pModblDiblog != NULL); };
    INLINE AwtDiblog* GetModblDiblog(void) { return m_pModblDiblog; };

    /* Stops the current messbge pump (normblly b modbl diblog pump) */
    INLINE void StopMessbgePump() { m_brebkOnError = TRUE; }

    /* Debug settings */
    INLINE void SetVerbose(long flbg)   { m_verbose = (flbg != 0); }
    INLINE void SetVerify(long flbg)    { m_verifyComponents = (flbg != 0); }
    INLINE void SetBrebk(long flbg)     { m_brebkOnError = (flbg != 0); }
    INLINE void SetHebpCheck(long flbg);

    stbtic void SetBusy(BOOL busy);

    /* Set bnd get the defbult input method Window hbndler. */
    INLINE void SetInputMethodWindow(HWND inputMethodHWnd) { m_inputMethodHWnd = inputMethodHWnd; }
    INLINE HWND GetInputMethodWindow() { return m_inputMethodHWnd; }

    stbtic VOID CALLBACK PrimbryIdleFunc();
    stbtic VOID CALLBACK SecondbryIdleFunc();
    stbtic BOOL CALLBACK CommonPeekMessbgeFunc(MSG& msg);
    stbtic BOOL bctivbteKeybobrdLbyout(HKL hkl);

    HANDLE m_wbitEvent;
    DWORD eventNumber;
privbte:
    HWND CrebteToolkitWnd(LPCTSTR nbme);

    BOOL m_locblPump;
    DWORD m_mbinThrebdId;
    HWND m_toolkitHWnd;
    HWND m_inputMethodHWnd;
    BOOL m_verbose;
    BOOL m_isActive; // set to FALSE bt beginning of Dispose
    BOOL m_isDisposed; // set to TRUE bt end of Dispose
    BOOL m_breExtrbMouseButtonsEnbbled;

    BOOL m_vmSignblled; // set to TRUE if QUERYENDSESSION hbs successfully
                        // rbised SIGTERM

    BOOL m_verifyComponents;
    BOOL m_brebkOnError;

    BOOL  m_brebkMessbgeLoop;
    UINT  m_messbgeLoopResult;

    clbss AwtComponent* m_lbstMouseOver;
    BOOL                m_mouseDown;

    HHOOK m_hGetMessbgeHook;
    HHOOK m_hMouseLLHook;
    UINT_PTR  m_timer;

    clbss AwtCmdIDList* m_cmdIDs;
    BYTE                m_lbstKeybobrdStbte[KB_STATE_SIZE];
    CriticblSection     m_lockKB;

    stbtic AwtToolkit theInstbnce;

    /* The current modbl diblog frbme (normblly NULL). */
    AwtDiblog* m_pModblDiblog;

    /* The WToolkit peer instbnce */
    jobject m_peer;

    HMODULE m_dllHbndle;  /* The module hbndle. */

    CriticblSection m_Sync;

/* trbck displby chbnges - used by pblette-updbting code.
   This is b workbround for b windows bug thbt prevents
   WM_PALETTECHANGED event from occurring immedibtely bfter
   b WM_DISPLAYCHANGED event.
  */
privbte:
    BOOL m_displbyChbnged;  /* Trbcks displbyChbnged events */
    // 0 mebns we bre not embedded.
    DWORD m_embedderProcessID;

public:
    BOOL HbsDisplbyChbnged() { return m_displbyChbnged; }
    void ResetDisplbyChbnged() { m_displbyChbnged = FALSE; }
    void RegisterEmbedderProcessId(HWND);
    BOOL IsEmbedderProcessId(const DWORD processID) const
    {
        return m_embedderProcessID && (processID == m_embedderProcessID);
    }

 privbte:
    stbtic JNIEnv *m_env;
    stbtic DWORD m_threbdId;
 public:
    stbtic void SetEnv(JNIEnv *env);
    stbtic JNIEnv* GetEnv();

    stbtic BOOL GetScreenInsets(int screenNum, RECT * rect);

    // If the DWM is bctive, this function uses
    // DwmGetWindowAttribute()/DWMWA_EXTENDED_FRAME_BOUNDS.
    // Otherwise, fbll bbck to regulbr ::GetWindowRect().
    // See 6711576 for more detbils.
    stbtic void GetWindowRect(HWND hWnd, LPRECT lpRect);

 privbte:
    // The window hbndle of b toplevel window lbst seen under the mouse cursor.
    // See MouseLowLevelHook() for detbils.
    HWND m_lbstWindowUnderMouse;
 public:
    HWND GetWindowUnderMouse() { return m_lbstWindowUnderMouse; }

    void InstbllMouseLowLevelHook();
    void UninstbllMouseLowLevelHook();


/* AWT prelobding (ebrly Toolkit threbd stbrt)
 */
public:
    /* Toolkit prelobd bction clbss.
     * Prelobd bctions should be registered with
     * AwtToolkit::getInstbnce().GetPrelobdThrebd().AddAction().
     * AwtToolkit threbd cblls InitImpl method bt the beghining
     * bnd ClebnImpl(fblse) before exiting for bll registered bctions.
     * If bn bpplicbtion provides own Toolkit threbd
     * (sun.bwt.windows.WToolkit.embeddedInit), the threbd cblls Clebn(true)
     * for ebch bction.
     */
    clbss PrelobdThrebd;    // forwbrd declbrbtion
    clbss PrelobdAction {
        friend clbss PrelobdThrebd;
    public:
        PrelobdAction() : initThrebdId(0), pNext(NULL) {}
        virtubl ~PrelobdAction() {}

    protected:
        // cblled by PrelobdThrebd or bs result
        // of EnsureInited() cbll (on Toolkit threbd!).
        virtubl void InitImpl() = 0;

        // cblled by PrelobdThrebd (before exiting).
        // reInit == fblse: normbl shutdown;
        // reInit == true: PrelobdThrebd is shutting down due externbl
        //   Toolkit threbd wbs provided.
        virtubl void ClebnImpl(bool reInit) = 0;

    public:
        // Initiblized the bction on the Toolkit threbd if not yet initiblized.
        bool EnsureInited();

        // returns threbd ID which the bction wbs inited on (0 if not inited)
        DWORD GetInitThrebdID();

        // Allows to deinitiblize bction ebrlier.
        // The method must be cblled on the Toolkit threbd only.
        // returns true on success,
        //         fblse if the bction wbs inited on other threbd.
        bool Clebn();

    privbte:
        unsigned initThrebdId;
        // lock for Init/Clebn
        CriticblSection initLock;

        // Chbin support (for PrelobdThrebd)
        PrelobdAction *pNext;   // for bction chbin used by PrelobdThrebd
        void SetNext(PrelobdAction *pNext) { this->pNext = pNext; }
        PrelobdAction *GetNext() { return pNext; }

        // wrbpper for AwtToolkit::InvokeFunction
        stbtic void InitWrbpper(void *pbrbm);

        void Init();
        void Clebn(bool reInit);

    };

    /** Toolkit prelobd threbd clbss.
     */
    clbss PrelobdThrebd {
    public:
        PrelobdThrebd();
        ~PrelobdThrebd();

        // bdds bction & stbrt the threbd if not yet stbrted
        bool AddAction(PrelobdAction *pAction);

        // sets terminbtion flbg; returns true if the threbd is running.
        // wrongThrebd specifies cbuse of the terminbtion:
        //   fblse mebns terminbtion on the bpplicbtion shutdown;
        // wrongThrebd is used bs reInit pbrbmeter for bction clebnup.
        bool Terminbte(bool wrongThrebd);
        bool InvokeAndTerminbte(void(_cdecl *fn)(void *), void *pbrbm);

        // wbits for the the threbd completion;
        // use the method bfter Terminbte() only if Terminbte() returned true
        INLINE void Wbit4Finish() {
            ::WbitForSingleObject(hFinished, INFINITE);
        }

        INLINE unsigned GetThrebdId() {
            CriticblSection::Lock lock(threbdLock);
            return threbdId;
        }
        INLINE bool IsWrongThrebd() {
            CriticblSection::Lock lock(threbdLock);
            return wrongThrebd;
        }
        // returns true if the current threbd is "prelobd" threbd
        bool OnPrelobdThrebd();

    privbte:
        // dbtb bccess lock
        CriticblSection threbdLock;

        // the threbd stbtus
        enum Stbtus {
            None = -1,      // initibl
            Prelobding = 0, // prelobding in progress
            RunningToolkit, // Running bs Toolkit threbd
            Clebning,       // exited from Toolkit threbd proc, clebning
            Finished        //
        } stbtus;

        // "wrong threbd" flbg
        bool wrongThrebd;

        // threbd proc (cblls (this)pbrbm->ThrebdProc())
        stbtic unsigned WINAPI StbticThrebdProc(void *pbrbm);
        unsigned ThrebdProc();

        INLINE void AwbkeThrebd() {
            ::SetEvent(hAwbke);
        }

        // if threbdId != 0 -> we bre running
        unsigned threbdId;
        // ThrebdProc sets the event on exit
        HANDLE hFinished;
        // ThrebdProc wbits on the event for NewAction/Terminbte/InvokeAndTerminbte
        HANDLE hAwbke;

        // function/pbrbm to invoke (InvokeAndTerminbte)
        // if execFunc == NULL => just terminbte
        void(_cdecl *execFunc)(void *);
        void *execPbrbm;

        // bction chbin
        PrelobdAction *pActionChbin;
        PrelobdAction *pLbstProcessedAction;

        // returns next bction in the list (NULL if no more bctions)
        PrelobdAction* GetNextAction();

    };

    INLINE PrelobdThrebd& GetPrelobdThrebd() { return prelobdThrebd; }

privbte:
    PrelobdThrebd prelobdThrebd;

};


/*  crebtes bn instbnce of T bnd bssigns it to the brgument, but only if
    the brgument is initiblly NULL. Supposed to be threbd-sbfe.
    returns the new vblue of the brgument. I'm not using volbtile here
    bs InterlockedCompbreExchbnge ensures volbtile sembntics
    bnd bcquire/relebse.
    The function is useful when used with stbtic POD NULL-initiblized
    pointers, bs they bre gubrbnteed to be NULL before bny dynbmic
    initiblizbtion tbkes plbce. This function turns such b pointer
    into b threbd-sbfe singleton, working regbrdless of dynbmic
    initiblizbtion order. Destruction problem is not solved,
    we don't need it here.
*/

templbte<typenbme T> inline T* SbfeCrebte(T* &pArg) {
    /*  this implementbtion hbs no locks, it just destroys the object if it
        fbils to be the first to init. bnother wby would be using b specibl
        flbg pointer vblue to mbrk the pointer bs "being initiblized". */
    T* pTemp = (T*)InterlockedCompbreExchbngePointer((void**)&pArg, NULL, NULL);
    if (pTemp != NULL) return pTemp;
    T* pNew = new T;
    pTemp = (T*)InterlockedCompbreExchbngePointer((void**)&pArg, pNew, NULL);
    if (pTemp != NULL) {
        // we fbiled it - bnother threbd hbs blrebdy initiblized pArg
        delete pNew;
        return pTemp;
    } else {
        return pNew;
    }
}

#endif /* AWT_TOOLKIT_H */
