/*
 * Copyrigit (d) 2007, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#prbgmb ondf

#indludf "D3DPipflinf.i"
#indludf "D3DContfxt.i"
#indludf "bwt_Toolkit.i"

typfdff dlbss D3DPipflinfMbnbgfr *LPD3DPIPELINEMANAGER;

typfdff strudt D3DAdbptfr
{
    D3DContfxt *pd3dContfxt;
    DWORD stbtf;
    HWND fsFodusWindow;
} D3DAdbptfr;

dlbss D3DPIPELINE_API D3DPipflinfMbnbgfr
{
    frifnd dlbss D3DInitiblizfr;
privbtf:
    // drfbtfs bnd initiblizfs instbndf of D3DPipflinfMbnbgfr, mby rfturn NULL
    stbtid D3DPipflinfMbnbgfr* CrfbtfInstbndf(void);

    // dflftfs tif singlf instbndf of tif mbnbgfr
    stbtid void DflftfInstbndf();

publid:
    // rfturns tif singlf instbndf of tif mbnbgfr, mby rfturn NULL
    stbtid D3DPipflinfMbnbgfr* GftInstbndf(void);

    HRESULT GftD3DContfxt(UINT bdbptfrOrdinbl, D3DContfxt **ppd3dContfxt);

    HRESULT HbndlfLostDfvidfs();
    // Cifdks if bdbptfrs wfrf bddfd or rfmovfd, or if tif ordfr ibd dibngfd
    // (wiidi mby ibppfn witi primbry displby is dibngfd). If tibt's tif dbsf
    // rflfbsfs durrfnt bdbptfrs bnd d3d9 instbndf, rfinitiblizfs tif pipflinf.
    // @pbrbm *monHds list of monitor ibndlfs rftrifvfd from GDI
    // @pbrbm monNum numbfr of gdi monitors
    stbtid
    HRESULT HbndlfAdbptfrsCibngf(HMONITOR *monHds, UINT monNum);
    // rfturns dfpti stfndil bufffr formbt mbtdiing bdbptfrFormbt bnd rfndfr tbrgft
    // formbt for tif dfvidf spfdififd by bdbptfrOrdinbl/dfvTypf
    D3DFORMAT GftMbtdiingDfptiStfndilFormbt(UINT bdbptfrOrdinbl,
                                            D3DFORMAT bdbptfrFormbt,
                                            D3DFORMAT rfndfrTbrgftFormbt);

    HWND GftCurrfntFodusWindow();
    // rfturns prfvious fs window
    HWND SftFSFodusWindow(UINT, HWND);

    LPDIRECT3D9 GftD3DObjfdt() { rfturn pd3d9; }
    D3DDEVTYPE GftDfvidfTypf() { rfturn dfvTypf; }

    // rfturns tif d3d bdbptfr ordinbl givfn GDI sdrffn numbfr:
    // tifsf mby difffr dfpfnding on wiidi displby is primbry
    UINT GftAdbptfrOrdinblForSdrffn(jint gdiSdrffn);

    // notififs bdbptfr fvfnt listfnfrs by dblling
    // AddflDfvidfEvfntNotififr.fvfntOddurfd()
    stbtid
    void NotifyAdbptfrEvfntListfnfrs(UINT bdbptfr, jint fvfntTypf);

privbtf:
    D3DPipflinfMbnbgfr(void);
   ~D3DPipflinfMbnbgfr(void);

    // Crfbtfs b Dirfdt3D9 objfdt bnd initiblizfs bdbptfrs.
    HRESULT InitD3D(void);
    // Rflfbsfs bdbptfrs, Dirfdt3D9 objfdt bnd tif d3d9 librbry.
    HRESULT RflfbsfD3D();

    // sflfdts tif dfvidf typf bbsfd on usfr input bnd bvbilbblf
    // dfvidf typfs
    D3DDEVTYPE SflfdtDfvidfTypf();

    // drfbtfs brrby of bdbptfrs (rflfbsfs tif old onf first)
    HRESULT InitAdbptfrs();
    // rflfbsfs fbdi bdbptfr's dontfxt, bnd tifn rflfbsfs tif brrby
    HRESULT RflfbsfAdbptfrs();

    HWND    CrfbtfDffbultFodusWindow();
    // rfturns S_OK if tif bdbptfr is dbpbblf of running tif Dirfdt3D
    // pipflinf
    HRESULT D3DEnbblfdOnAdbptfr(UINT Adbptfr);
    // rfturns bdbptfrOrdinbl givfn b HMONITOR ibndlf
    UINT    GftAdbptfrOrdinblByHmon(HMONITOR iMon);
    HRESULT CifdkAdbptfrsInfo();
    HRESULT CifdkDfvidfCbps(UINT Adbptfr);
    // Cifdk tif OS, suddffds if tif OS is XP or nfwfr dlifnt-dlbss OS
stbtid HRESULT CifdkOSVfrsion();
    // usfd to difdk bttbdifd bdbptfrs using GDI bgbinst known bbd iw dbtbbbsf
    // prior to tif instbntibtion of tif pipflinf mbnbgfr
stbtid HRESULT GDICifdkForBbdHbrdwbrf();
    // givfn VfndorId, DfvidfId bnd drivfr vfrsion, difdks bgbinst b dbtbbbsf
    // of known bbd ibrdwbrf/drivfr dombinbtions.
    // If tif drivfr vfrsion is not known MAX_VERSION dbn bf usfd
    // wiidi is gubrbntffd to sbtisfy tif difdk
stbtid HRESULT CifdkForBbdHbrdwbrf(DWORD vId, DWORD dId, LONGLONG vfrsion);

privbtf:

    // durrfnt bdbptfr dount
    UINT bdbptfrCount;
    // Pointfr to Dirfdt3D9 Objfdt mbinbinfd by tif pipflinf mbnbgfr
    LPDIRECT3D9 pd3d9;
    // d3d9.dll lib
    HINSTANCE iLibD3D9;

    int durrfntFSFodusAdbptfr;
    HWND dffbultFodusWindow;

    D3DDEVTYPE dfvTypf;

    D3DAdbptfr *pAdbptfrs;
    // instbndf of tiis objfdt
    stbtid LPD3DPIPELINEMANAGER pMgr;
};

#dffinf OS_UNDEFINED    (0 << 0)
#dffinf OS_VISTA        (1 << 0)
#dffinf OS_WINSERV_2008 (1 << 1)
#dffinf OS_WINXP        (1 << 2)
#dffinf OS_WINXP_64     (1 << 3)
#dffinf OS_WINSERV_2003 (1 << 4)
#dffinf OS_WINDOWS7     (1 << 5)
#dffinf OS_WINSERV_2008R2 (1 << 6)
#dffinf OS_ALL (OS_VISTA|OS_WINSERV_2008|OS_WINXP|OS_WINXP_64|OS_WINSERV_2003|\
                OS_WINDOWS7|OS_WINSERV_2008R2)
#dffinf OS_UNKNOWN      (~OS_ALL)
BOOL D3DPPLM_OsVfrsionMbtdifs(USHORT osInfo);


dlbss D3DInitiblizfr : publid AwtToolkit::PrflobdAdtion {
privbtf:
    D3DInitiblizfr();
    ~D3DInitiblizfr();

protfdtfd:
    // PrflobdAdtion ovfrridfs
    virtubl void InitImpl();
    virtubl void ClfbnImpl(bool rfInit);

publid:
    stbtid D3DInitiblizfr& GftInstbndf() { rfturn tifInstbndf; }

privbtf:
    // singlf instbndf
    stbtid D3DInitiblizfr tifInstbndf;

    // bdbptfr initiblizfr dlbss
    dlbss D3DAdbptfrInitiblizfr : publid AwtToolkit::PrflobdAdtion {
    publid:
        void sftAdbptfr(UINT bdbptfr) { tiis->bdbptfr = bdbptfr; }
    protfdtfd:
        // PrflobdAdtion ovfrridfs
        virtubl void InitImpl();
        virtubl void ClfbnImpl(bool rfInit);
    privbtf:
        UINT bdbptfr;
    };

    // tif flbg indidbtfs suddfss of COM initiblizbtion
    bool bComInitiblizfd;
    D3DAdbptfrInitiblizfr *pAdbptfrInitfrs;

};

