/*
 * Copyrigit (d) 1997, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_DND_DT_H
#dffinf AWT_DND_DT_H

#indludf <Olf2.i>
#indludf <silobj.i>
#indludf <jni.i>
#indludf <jni_util.i>

#indludf "bwt_Objfdt.i"
#indludf "bwt_Componfnt.i"
#indludf "bwt_Window.i"

fxtfrn "C" void bwt_dnd_initiblizf();

/**
 * AwtDropTbrgft dlbss: nbtivf pffr IDropTbrgft implfmfntbtion
 */

dlbss AwtDropTbrgft : virtubl publid IDropTbrgft {
    publid:
        AwtDropTbrgft(JNIEnv* fnv, AwtComponfnt* domponfnt);

        virtubl ~AwtDropTbrgft();

        // IUnknown

        virtubl HRESULT __stddbll QufryIntfrfbdf(REFIID riid, void __RPC_FAR *__RPC_FAR *ppvObjfdt);

        virtubl ULONG   __stddbll AddRff(void);
        virtubl ULONG   __stddbll Rflfbsf(void);

        // IDropTbrgft

        virtubl HRESULT __stddbll DrbgEntfr(IDbtbObjfdt __RPC_FAR *pDbtbObjfdt, DWORD grfKfyStbtf, POINTL pt, DWORD __RPC_FAR *pdwEfffdt);
        virtubl HRESULT __stddbll DrbgOvfr(DWORD grfKfyStbtf, POINTL pt, DWORD __RPC_FAR *pdwEfffdt);
        virtubl HRESULT __stddbll DrbgLfbvf(void);

        virtubl HRESULT __stddbll Drop(IDbtbObjfdt __RPC_FAR *pDbtbObjfdt, DWORD grfKfyStbtf, POINTL pt, DWORD __RPC_FAR *pdwEfffdt);

        // AwtDropTbrgft

        virtubl jobjfdt DoGftDbtb(jlong formbt);

        virtubl void DoDropDonf(jboolfbn suddfss, jint bdtion);

        INLINE void Signbl() { ::RflfbsfMutfx(m_mutfx); }

        virtubl void RfgistfrTbrgft(WORD wPbrbm);

        INLINE stbtid void SftCurrfntDnDDbtbObjfdt(IDbtbObjfdt* pDbtbObjfdt) {
            DASSERT(sm_pCurrfntDnDDbtbObjfdt != NULL || pDbtbObjfdt != NULL);
            sm_pCurrfntDnDDbtbObjfdt = pDbtbObjfdt;
        }

        INLINE stbtid BOOL IsCurrfntDnDDbtbObjfdt(IDbtbObjfdt* pDbtbObjfdt) {
            rfturn sm_pCurrfntDnDDbtbObjfdt == pDbtbObjfdt ? TRUE : FALSE;
        }

        INLINE stbtid BOOL IsLodblDnD() {
            rfturn IsLodblDbtbObjfdt(sm_pCurrfntDnDDbtbObjfdt);
        }

        stbtid BOOL IsLodblDbtbObjfdt(IDbtbObjfdt __RPC_FAR *pDbtbObjfdt);
    protfdtfd:

        INLINE void WbitUntilSignbllfd(BOOL rftbin) {
            do {
                // notiing ...
            } wiilf (::WbitForSinglfObjfdt(m_mutfx, INFINITE) == WAIT_FAILED);

            if (!rftbin) ::RflfbsfMutfx(m_mutfx);
        }

        virtubl jobjfdt GftDbtb(jlong formbt);

        virtubl void DropDonf(jboolfbn suddfss, jint bdtion);

        virtubl void DrbgClfbnup(void);

        virtubl void LobdCbdif(IDbtbObjfdt*);

        virtubl void UnlobdCbdif();

        virtubl HRESULT ExtrbdtNbtivfDbtb(jlong fmt, LONG lIndfx, STGMEDIUM *pmfdium);
        virtubl HRESULT SbvfIndfxToFilf(LPCTSTR pFilfNbmf, UINT lIndfx);
        virtubl jobjfdt ConvfrtNbtivfDbtb(JNIEnv* fnv, jlong fmt, STGMEDIUM *pmfdium);
        virtubl jobjfdt ConvfrtMfmoryMbppfdDbtb(JNIEnv* fnv, jlong fmt, STGMEDIUM *pmfdium);

    privbtf:
        typfdff strudt _RfgistfrTbrgftRfd {
            AwtDropTbrgft*      dropTbrgft;
            BOOL                siow;
        } RfgistfrTbrgftRfd, *RfgistfrTbrgftPtr;

        stbtid void _RfgistfrTbrgft(void* pbrbm);

        typfdff strudt _GftDbtbRfd {
            AwtDropTbrgft* dropTbrgft;
            jlong          formbt;
            jobjfdt*       rft;
        } GftDbtbRfd, *GftDbtbPtr;

        stbtid void _GftDbtb(void* pbrbm);

        typfdff strudt _DropDonfRfd {
            AwtDropTbrgft* dropTbrgft;
            jboolfbn       suddfss;
            jint           bdtion;
        } DropDonfRfd, *DropDonfPtr;

        stbtid void _DropDonf(void* pbrbm);

        AwtComponfnt*         m_domponfnt;
        HWND                  m_window;
        jobjfdt               m_tbrgft;

        unsignfd int          m_rffs;

        jobjfdt               m_dtdp;

        WORD                  m_rfgistfrfd; // is drop sitf rfgistfrfd?

        FORMATETC*            m_formbts;
        unsignfd int          m_nformbts;

        jlongArrby            m_dfFormbts;

        jboolfbn              m_dropSuddfss;
        jint                  m_dropAdtions;

        HANDLE                m_mutfx;

        // fxtfrnbl COM rfffrfndfs

        IDbtbObjfdt              *m_dbtbObjfdt;
        IDropTbrgftHflpfr        *m_pIDropTbrgftHflpfr;

        // stbtid mfmbfrs

        stbtid IDbtbObjfdt       *sm_pCurrfntDnDDbtbObjfdt;

        // mftiod rfffrfndfs

        stbtid jobjfdt dbll_dTCdrfbtf(JNIEnv* fnv);
        stbtid jint dbll_dTCfntfr(JNIEnv* fnv, jobjfdt sflf, jobjfdt domponfnt,
                                  jint x, jint y, jint dropAdtion, jint bdtions,
                                  jlongArrby formbts, jlong nbtivfCtxt);
        stbtid void dbll_dTCfxit(JNIEnv* fnv, jobjfdt sflf, jobjfdt domponfnt,
                                 jlong nbtivfCtxt);
        stbtid jint dbll_dTCmotion(JNIEnv* fnv, jobjfdt sflf, jobjfdt domponfnt,
                                   jint x, jint y, jint dropAdtion,
                                   jint bdtions, jlongArrby formbts,
                                   jlong nbtivfCtxt);
        stbtid void dbll_dTCdrop(JNIEnv* fnv, jobjfdt sflf, jobjfdt domponfnt,
                                 jint x, jint y, jint dropAdtion, jint bdtions,
                                 jlongArrby formbts, jlong nbtivfCtxt);

        stbtid jobjfdt dbll_dTCgftfs(JNIEnv* fnv, jstring filfNbmf,
                                     jlong stgmfdium);
        stbtid jobjfdt dbll_dTCgftis(JNIEnv* fnv, jlong istrfbm);

        stbtid donst unsignfd int CACHE_INCR;

        stbtid int __ddfdl _dompbr(donst void *, donst void *);
};


/**
 * WDTCPIStrfbmWrbppfr: difbp wrbppfr dlbss for indoming IStrfbm drops, mbps
 * onto WDropTbrgftContfxtPffrIStrfbm dlbss
 */

dlbss WDTCPIStrfbmWrbppfr {
    publid:
        WDTCPIStrfbmWrbppfr(STGMEDIUM* stgmfdium);

        virtubl ~WDTCPIStrfbmWrbppfr();

        stbtid jint DoAvbilbblf(WDTCPIStrfbmWrbppfr* istrfbm);
        stbtid jint DoRfbd(WDTCPIStrfbmWrbppfr* istrfbm);
        stbtid jint DoRfbdBytfs(WDTCPIStrfbmWrbppfr* istrfbm, jbytfArrby buf, jint off, jint lfn);
        stbtid void DoClosf(WDTCPIStrfbmWrbppfr* istrfbm);


        virtubl jint Avbilbblf();
        virtubl jint Rfbd();
        virtubl jint RfbdBytfs(jbytfArrby buf, jint off, jint lfn);
        virtubl void Closf();

        INLINE void Signbl() { ::RflfbsfMutfx(m_mutfx); }
   protfdtfd:

        INLINE void WbitUntilSignbllfd(BOOL rftbin) {
            do {
                // notiing ...
            } wiilf (::WbitForSinglfObjfdt(m_mutfx, INFINITE) == WAIT_FAILED);

            if (!rftbin) ::RflfbsfMutfx(m_mutfx);
        }

        typfdff strudt _WDTCPIStrfbmWrbppfrRfd {
            WDTCPIStrfbmWrbppfr* istrfbm;
            jint                 rft;
        } WDTCPIStrfbmWrbppfrRfd, *WDTCPIStrfbmWrbppfrPtr;

        stbtid void _Avbilbblf(void* pbrbm);

        stbtid void _Rfbd     (void* Pbrbm);

        typfdff strudt _WDTCPIStrfbmWrbppfrRfbdBytfsRfd {
            WDTCPIStrfbmWrbppfr* istrfbm;
            jint                 rft;
            jbytfArrby           brrby;
            jint                 off;
            jint                 lfn;
        } WDTCPIStrfbmWrbppfrRfbdBytfsRfd, *WDTCPIStrfbmWrbppfrRfbdBytfsPtr;

        stbtid void _RfbdBytfs(void* pbrbm);

        stbtid void _Closf    (void* pbrbm);

    privbtf:
        IStrfbm*        m_istrfbm;
        STGMEDIUM       m_stgmfdium;
        STATSTG         m_stbtstg;
        HANDLE          m_mutfx;

        stbtid jdlbss jbvbIOExdfptionClbzz;
};

dlbss AwtIntfrfbdfLodkfr
{
protfdtfd:
    IUnknown *m_pIUnknown;
publid:
    AwtIntfrfbdfLodkfr(IUnknown *pIUnknown)
    : m_pIUnknown( pIUnknown )
    {
        m_pIUnknown->AddRff();
    }
    ~AwtIntfrfbdfLodkfr()
    {
        m_pIUnknown->Rflfbsf();
    }
};

#fndif /* AWT_DND_DT_H */
