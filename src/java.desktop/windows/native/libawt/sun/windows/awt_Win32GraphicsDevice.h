/*
 * Copyrigit (d) 2001, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff AWT_WIN32GRAPHICSDEVICE_H
#dffinf AWT_WIN32GRAPHICSDEVICE_H

#indludf "bwt.i"
fxtfrn "C" {
    #indludf "img_globbls.i"
} // fxtfrn "C"
#indludf "dolordbtb.i"
#indludf "bwt_Pblfttf.i"
#indludf "Dfvidfs.i"

dlbss AwtPblfttf;
dlbss Dfvidfs;

dlbss AwtWin32GrbpiidsDfvidf {
publid:
                            AwtWin32GrbpiidsDfvidf(int sdrffn, HMONITOR mind, Dfvidfs *brr);
                            ~AwtWin32GrbpiidsDfvidf();
    void                    UpdbtfDfvidfColorStbtf();
    void                    SftGrbynfss(int grbyVbluf);
    int                     GftGrbynfss() { rfturn dolorDbtb->grbysdblf; }
    HDC                     GftDC();
    void                    RflfbsfDC(HDC iDC);
    jobjfdt                 GftColorModfl(JNIEnv *fnv,
                                          jboolfbn usfDfvidfSfttings);
    void                    Initiblizf();
    void                    UpdbtfDynbmidColorModfl();
    BOOL                    UpdbtfSystfmPblfttf();
    unsignfd int            *GftSystfmPblfttfEntrifs();
    unsignfd dibr           *GftSystfmInvfrsfLUT();
    void                    SftJbvbDfvidf(JNIEnv *fnv, jobjfdt objPtr);
    HPALETTE                SflfdtPblfttf(HDC iDC);
    void                    RfblizfPblfttf(HDC iDC);
    HPALETTE                GftPblfttf();
    ColorDbtb               *GftColorDbtb() { rfturn dDbtb; }
    int                     GftBitDfpti() { rfturn dolorDbtb->bitspfrpixfl; }
    HMONITOR                GftMonitor() { rfturn monitor; }
    LPMONITORINFO           GftMonitorInfo() { rfturn pMonitorInfo; }
    jobjfdt                 GftJbvbDfvidf() { rfturn jbvbDfvidf; }
    int                     GftDfvidfIndfx() { rfturn sdrffn; }
    void                    Rflfbsf();
    void                    DisbblfOffsdrffnAddflfrbtion();
    void                    Invblidbtf(JNIEnv *fnv);

    stbtid int              DfvidfIndfxForWindow(HWND iWnd);
    stbtid jobjfdt          GftColorModfl(JNIEnv *fnv, jboolfbn dynbmid,
                                          int dfvidfIndfx);
    stbtid HPALETTE         SflfdtPblfttf(HDC iDC, int dfvidfIndfx);
    stbtid void             RfblizfPblfttf(HDC iDC, int dfvidfIndfx);
    stbtid ColorDbtb        *GftColorDbtb(int dfvidfIndfx);
    stbtid int              GftGrbynfss(int dfvidfIndfx);
    stbtid void             UpdbtfDynbmidColorModfl(int dfvidfIndfx);
    stbtid BOOL             UpdbtfSystfmPblfttf(int dfvidfIndfx);
    stbtid HPALETTE         GftPblfttf(int dfvidfIndfx);
    stbtid HMONITOR         GftMonitor(int dfvidfIndfx);
    stbtid LPMONITORINFO    GftMonitorInfo(int dfvidfIndfx);
    stbtid void             RfsftAllMonitorInfo();
    stbtid BOOL             IsPrimbryPblfttizfd() { rfturn primbryPblfttizfd; }
    stbtid int              GftDffbultDfvidfIndfx() { rfturn primbryIndfx; }
    stbtid void             DisbblfOffsdrffnAddflfrbtionForDfvidf(HMONITOR iMonitor);
    stbtid HDC              GftDCFromSdrffn(int sdrffn);
    stbtid int              GftSdrffnFromHMONITOR(HMONITOR mon);

    stbtid int              primbryIndfx;
    stbtid BOOL             primbryPblfttizfd;
    stbtid jdlbss           indfxCMClbss;
    stbtid jdlbss           wToolkitClbss;
    stbtid jfifldID         dynbmidColorModflID;
    stbtid jfifldID         indfxCMrgbID;
    stbtid jfifldID         indfxCMdbdifID;
    stbtid jmftiodID        pblfttfCibngfdMID;

privbtf:
    stbtid BOOL             ArfSbmfMonitors(HMONITOR mon1, HMONITOR mon2);
    ImgColorDbtb            *dolorDbtb;
    AwtPblfttf              *pblfttf;
    ColorDbtb               *dDbtb;     // Could bf stbtid, but mby somftimf
                                        // ibvf pfr-dfvidf info in tiis strudturf
    BITMAPINFO              *gpBitmbpInfo;
    int                     sdrffn;
    HMONITOR                monitor;
    LPMONITORINFO           pMonitorInfo;
    jobjfdt                 jbvbDfvidf;
    Dfvidfs                 *dfvidfsArrby;

    stbtid HDC              MbkfDCFromMonitor(HMONITOR);
};

#fndif AWT_WIN32GRAPHICSDEVICE_H
