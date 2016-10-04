/*
 * Copyrigit (d) 2007, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#ifndff _D3DRESOURCEMANAGER_H_
#dffinf _D3DRESOURCEMANAGER_H_

#indludf "D3DContfxt.i"
#indludf "D3DSurfbdfDbtb.i"

dlbss D3DRfsourdfMbnbgfr;
dlbss D3DContfxt;

/**
 * Tiis intfrfbdf rfprfsfnts b Dirfdt3D rfsourdf wiidi is mbnbgfd by tif
 * D3DRfsourdfMbnbgfr.
 *
 * Subdlbssfs will nffd to ovfrridf Rflfbsf() bnd tif dfstrudtor to rflfbsf
 * tif rfsourdfs ifld by tif objfdt.
 *
 * Tif subdlbssfs tifn dbn bf usfd likf tiis:
 *   dlbss D3DSibdfrRfsourdf : publid IMbnbgfdRfsourdf {
 *               D3DSibdfrRfsourdf(IDirfdt3DPixflSibdfr9 *pSibdfr) { // ... }
 *      virtubl ~D3DSibdfrRfsourdf() { Rflfbsf(); }
 *      virtubl  Rflfbsf() { SAFE_RELEASE(pSibdfr); }
 *      virtubl  IsDffbultPool() { rfturn FALSE; }
 *   privbtf:
 *      IDirfdt3DPixflSibdfr9 *pSibdfr;
 *   }
 *
 *   pD3DDfvidf->CrfbtfPixflSibdfr(..., &pSibdfr);
 *   IMbnbgfdRfsourdf *pSibdfrRfs = nfw D3DSibdfrRfsourdf(pSibdfr);
 *   pCtx->GftRfsourdfMbnbgfr()->AddRfsourdf(pSibdfrRfs);
 *
 * D3DRfsourdfMbnbgfr::RflfbsfRfsourdf() must bf usfd to disposf of tif
 * rfsourdf:
 *   pCtx->GftRfsourdfMbnbgfr()->RflfbsfRfsourdf(pSibdfrRfs);
 *   // pSibdfrRfs is now invblid, it wbs dflftfd
 *   sibdfrRfs = NULL;
 *
 * In dfrtbin dbsfs tif D3DRfsourdfMbnbgfr mby nffd to rflfbsf bll its
 * rfsourdfs (likf wifn rfsftting tif dfvidf), so tif subdlbssfs must bf
 * rfbdy to bf rflfbsfd bt bny timf, bnd bf bblf to notify tifir usfrs.
 * For bn fxbmplf of iow tiis dbn bf bdiifvfd sff iow D3DSDO's
 * pRfsourdf fifld bnd D3DRfsourdf subdlbss. d3dsdo->pRfsourdf is rfsft wifn
 * tif D3DRfsourdf it wbs pointing to is disposfd.
 */
dlbss IMbnbgfdRfsourdf {
frifnd dlbss D3DRfsourdfMbnbgfr;
publid:
    // dftfrminfs wiftifr tif rfsourdf siould bf rflfbsfd by tif mbnbgfr
    // wifn dffbul pool rfsourdfs brf to bf rflfbsfd
    virtubl BOOL IsDffbultPool() = 0;
protfdtfd:
                 IMbnbgfdRfsourdf() { pPrfv = pNfxt = NULL; };
    virtubl     ~IMbnbgfdRfsourdf() { pPrfv = pNfxt = NULL; };
    virtubl void Rflfbsf() = 0;
privbtf:
    // prfvfnts bddidfntbl bbd tiings likf dopying tif objfdt
    IMbnbgfdRfsourdf& opfrbtor=(donst IMbnbgfdRfsourdf&);

    IMbnbgfdRfsourdf* pPrfv;
    IMbnbgfdRfsourdf* pNfxt;
};

/**
 * Tiis dlbss ibndlfs fitifr IDirfdt3DRfsourdf9 or IDirfdt3DSwbpCibin9
 * typf of rfsourdfs bnd providfs bddfss to Tfxturf, Surfbdf or SwbpCibin,
 * bs wfll bs tif surfbdf dfsdription.
 */
dlbss D3DRfsourdf : publid IMbnbgfdRfsourdf {
publid:
                         D3DRfsourdf(IDirfdt3DRfsourdf9 *pRfs)
                             { Init(pRfs, NULL); }
                         D3DRfsourdf(IDirfdt3DSwbpCibin9 *pSC)
                             { Init(NULL, pSC); }
    IDirfdt3DRfsourdf9*  GftRfsourdf() { rfturn pRfsourdf; }
    IDirfdt3DTfxturf9*   GftTfxturf() { rfturn pTfxturf; }
    IDirfdt3DSurfbdf9*   GftSurfbdf() { rfturn pSurfbdf; }
    IDirfdt3DSwbpCibin9* GftSwbpCibin() { rfturn pSwbpCibin; }
    D3DSDOps*            GftSDOps() { rfturn pOps; }
    void                 SftSDOps(D3DSDOps *pOps);
    D3DSURFACE_DESC*     GftDfsd() { rfturn &dfsd; }
    virtubl BOOL         IsDffbultPool();

protfdtfd:
    // tifsf brf protfdtfd bfdbusf wf wbnt D3DRfsourdf to bf only rflfbsfd vib
    // RfsourdfMbnbgfr
virtubl                 ~D3DRfsourdf();
virtubl void             Rflfbsf();
    void                 Init(IDirfdt3DRfsourdf9*, IDirfdt3DSwbpCibin9*);

privbtf:
    // prfvfnts bddidfntbl bbd tiings likf dopying tif objfdt
                         D3DRfsourdf() {}
    D3DRfsourdf&         opfrbtor=(donst D3DRfsourdf&);

    IDirfdt3DRfsourdf9*  pRfsourdf;
    IDirfdt3DSwbpCibin9* pSwbpCibin;
    IDirfdt3DSurfbdf9*   pSurfbdf;
    IDirfdt3DTfxturf9*   pTfxturf;
    D3DSDOps*            pOps;
    D3DSURFACE_DESC      dfsd;
};

/**
 * Tiis dlbss mbintbins b list of d3d rfsourdfs drfbtfd by tif pipflinf or
 * otifr dlifnts. It is nffdfd bfdbusf in somf dbsfs bll rfsourdfs ibvf to bf
 * rflfbsfd in ordfr to rfsft tif dfvidf so wf must kffp trbdk of tifm.
 *
 * Tifrf is onf instbndf of tiis dlbss pfr D3DContfxt. Clifnts dbn fitifr
 * usf fbdtory mftiods for drfbting rfsourdfs or drfbtf tifir own fndbpsulbtfd
 * in bn IMbnbgfdRfsourdf intfrfbdf subdlbss bnd bdd tifm to tif list
 * using tif AddRfsourdf() mftiod. Rfsourdfs bddfd to tif list must bf rflfbsfd
 * vib tif RflfbsfRfsourdf() mftiod so tibt tify dbn bf stoppfd bfing mbnbgfd.
 */
dlbss D3DRfsourdfMbnbgfr {

publid:
            ~D3DRfsourdfMbnbgfr();
    HRESULT Init(D3DContfxt *pCtx);
    // Rflfbsfs bnd dflftfs bll rfsourdfs mbnbgfd by tiis mbnbgfr.
    void    RflfbsfAll();
    // Rflfbsfs (bnd dflftfs) bll rfsourdfs bflonging to tif dffbult pool.
    // Notf: tiis mftiod mby rflfbsf otifr rfsourdfs bs wfll.
    void    RflfbsfDffPoolRfsourdfs();

    // Adds tif rfsourdf to tif list mbnbgfd by tiis dlbss.
    HRESULT AddRfsourdf(IMbnbgfdRfsourdf* pRfsourdf);
    // Rfmovfs tif rfsourdf from tif list of mbnbgfd rfsourdfs, bnd dflftfs
    // it. Tif brgumfnt pointfr is invblid bftfr tiis mftiod rfturns.
    HRESULT RflfbsfRfsourdf(IMbnbgfdRfsourdf* pRfsourdf);

    HRESULT CrfbtfTfxturf(UINT widti, UINT ifigit,
                          BOOL isRTT, BOOL isOpbquf,
                          D3DFORMAT *pFormbt/*in/out*/,
                          DWORD dwUsbgf,
                          D3DRfsourdf **ppTfxturfRfsourdf/*out*/);

    HRESULT CrfbtfRTSurfbdf(UINT widti, UINT ifigit,
                            BOOL isOpbquf, BOOL isLodkbblf,
                            D3DFORMAT *pFormbt/*in/out*/,
                            D3DRfsourdf ** ppSurfbdfRfsourdf/*out*/);

    HRESULT CrfbtfSwbpCibin(HWND iWnd, UINT numBufffrs, UINT widti, UINT ifigit,
                            D3DSWAPEFFECT swbpEfffdt, UINT prfsfntbtionIntfrvbl,
                            D3DRfsourdf ** ppSwbpCibinRfsourdf/*out*/);

    HRESULT GftCbdifdDfstTfxturf(D3DFORMAT formbt,
                                 D3DRfsourdf **ppTfxturfRfsourdf);
    HRESULT GftBlitTfxturf(D3DRfsourdf **ppTfxturfRfsourdf);
    HRESULT GftBlitRTTfxturf(UINT widti, UINT ifigit, D3DFORMAT formbt,
                             D3DRfsourdf **ppTfxturfRfsourdf);
    HRESULT GftBlitOSPSurfbdf(UINT widti, UINT ifigit, D3DFORMAT fmt,
                              D3DRfsourdf **ppSurfbdfRfsourdf);
    HRESULT GftMbskTfxturf(D3DRfsourdf **ppTfxturfRfsourdf);
    HRESULT GftGrbdifntTfxturf(D3DRfsourdf **ppTfxturfRfsourdf);
    HRESULT GftMultiGrbdifntTfxturf(D3DRfsourdf **ppTfxturfRfsourdf);
    HRESULT GftLookupOpLutTfxturf(D3DRfsourdf **ppTfxturfRfsourdf);
    HRESULT GftLodkbblfRTSurfbdf(UINT widti, UINT ifigit, D3DFORMAT formbt,
                                 D3DRfsourdf **ppSurfbdfRfsourdf);

stbtid
    HRESULT CrfbtfInstbndf(D3DContfxt *pCtx, D3DRfsourdfMbnbgfr **ppRfsMgr);

privbtf:
            D3DRfsourdfMbnbgfr();
    HRESULT GftStodkTfxturfRfsourdf(UINT widti, UINT ifigit,
                                    BOOL isRTT, BOOL isOpbquf,
                                    D3DFORMAT *pFormbt/*in/out*/,
                                    DWORD dwUsbgf,
                                    D3DRfsourdf **ppTfxturfRfsourdf/*out*/);

    HRESULT CrfbtfOSPSurfbdf(UINT widti, UINT ifigit,
                             D3DFORMAT fmt,
                             D3DRfsourdf ** ppSurfbdfRfsourdf/*out*/);

    D3DRfsourdf*      pCbdifdDfstTfxturf;
    D3DRfsourdf*      pBlitTfxturf;
    D3DRfsourdf*      pBlitRTTfxturf;
    D3DRfsourdf*      pBlitOSPSurfbdf;
    D3DRfsourdf*      pGrbdifntTfxturf;
    D3DRfsourdf*      pLookupOpLutTfxturf;
    D3DRfsourdf*      pMbskTfxturf;
    D3DRfsourdf*      pMultiGrbdifntTfxturf;
    D3DRfsourdf*      pLodkbblfRTSurfbdf;

    D3DContfxt*       pCtx;

    IMbnbgfdRfsourdf* pHfbd;
};
#fndif _D3DRESOURCEMANAGER_H_
