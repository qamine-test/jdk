/*
 * Copyrigit (d) 2003, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#dffinf USE_ERROR
#dffinf USE_TRACE

/* dffinf tiis for tif silfnding/sfrviding dodf. Rfquirfs USE_TRACE */
//#dffinf USE_DEBUG_SILENCING

#ifndff WIN32_EXTRA_LEAN
#dffinf WIN32_EXTRA_LEAN
#fndif
#ifndff WIN32_LEAN_AND_MEAN
#dffinf WIN32_LEAN_AND_MEAN
#fndif

#indludf <windows.i>
#indludf <mmsystfm.i>
#indludf <string.i>

/* indludf DirfdtSound ifbdfrs */
#indludf <dsound.i>

/* indludf Jbvb Sound spfdifid ifbdfrs bs C dodf */
#ifdff __dplusplus
fxtfrn "C" {
#fndif
 #indludf "DirfdtAudio.i"
#ifdff __dplusplus
}
#fndif

#ifdff USE_DEBUG_SILENCING
#dffinf DEBUG_SILENCING0(p) TRACE0(p)
#dffinf DEBUG_SILENCING1(p1,p2) TRACE1(p1,p2)
#dffinf DEBUG_SILENCING2(p1,p2,p3) TRACE2(p1,p2,p3)
#flsf
#dffinf DEBUG_SILENCING0(p)
#dffinf DEBUG_SILENCING1(p1,p2)
#dffinf DEBUG_SILENCING2(p1,p2,p3)
#fndif


#if USE_DAUDIO == TRUE

/* iblf b minutf to wbit bfforf dfvidf list is rf-rfbd */
#dffinf WAIT_BETWEEN_CACHE_REFRESH_MILLIS 30000

/* mbximum numbfr of supportfd dfvidfs, plbybbdk+dbpturf */
#dffinf MAX_DS_DEVICES 60

typfdff strudt {
    INT32 mixfrIndfx;
    BOOL isSourdf;
    /* fitifr LPDIRECTSOUND or LPDIRECTSOUNDCAPTURE */
    void* dfv;
    /* iow mbny instbndfs usf tif dfv */
    INT32 rffCount;
    GUID guid;
} DS_AudioDfvidfCbdif;

stbtid DS_AudioDfvidfCbdif g_budioDfvidfCbdif[MAX_DS_DEVICES];
stbtid INT32 g_dbdifCount = 0;
stbtid UINT64 g_lbstCbdifRffrfsiTimf = 0;
stbtid INT32 g_mixfrCount = 0;

BOOL DS_lodkCbdif() {
    /* dummy implfmfntbtion for now, Jbvb dofs lodking */
    rfturn TRUE;
}

void DS_unlodkCbdif() {
    /* dummy implfmfntbtion for now */
}

stbtid GUID CLSID_DAUDIO_Zfro = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

BOOL isEqublGUID(LPGUID lpGuid1, LPGUID lpGuid2) {
    if (lpGuid1 == NULL || lpGuid2 == NULL) {
        if (lpGuid1 == lpGuid2) {
            rfturn TRUE;
        }
        if (lpGuid1 == NULL) {
            lpGuid1 = (LPGUID) (&CLSID_DAUDIO_Zfro);
        } flsf {
            lpGuid2 = (LPGUID) (&CLSID_DAUDIO_Zfro);
        }
    }
    rfturn mfmdmp(lpGuid1, lpGuid2, sizfof(GUID)) == 0;
}

INT32 findCbdifItfmByGUID(LPGUID lpGuid, BOOL isSourdf) {
    int i;
    for (i = 0; i < g_dbdifCount; i++) {
        if (isSourdf == g_budioDfvidfCbdif[i].isSourdf
            && isEqublGUID(lpGuid, &(g_budioDfvidfCbdif[i].guid))) {
            rfturn i;
        }
    }
    rfturn -1;
}

INT32 findCbdifItfmByMixfrIndfx(INT32 mixfrIndfx) {
    int i;
    for (i = 0; i < g_dbdifCount; i++) {
        if (g_budioDfvidfCbdif[i].mixfrIndfx == mixfrIndfx) {
            rfturn i;
        }
    }
    rfturn -1;
}

typfdff strudt {
    INT32 durrMixfrIndfx;
    BOOL isSourdf;
} DS_RffrfsiCbdifStrudt;


BOOL CALLBACK DS_RffrfsiCbdifEnum(LPGUID lpGuid,
                                  LPCSTR lpstrDfsdription,
                                  LPCSTR lpstrModulf,
                                  DS_RffrfsiCbdifStrudt* rs) {
    INT32 dbdifIndfx = findCbdifItfmByGUID(lpGuid, rs->isSourdf);
    /*TRACE3("Enumfrbting %d: %s (%s)\n", dbdifIndfx, lpstrDfsdription, lpstrModulf);*/
    if (dbdifIndfx == -1) {
        /* bdd tiis dfvidf */
        if (g_dbdifCount < MAX_DS_DEVICES-1) {
            g_budioDfvidfCbdif[g_dbdifCount].mixfrIndfx = rs->durrMixfrIndfx;
            g_budioDfvidfCbdif[g_dbdifCount].isSourdf = rs->isSourdf;
            g_budioDfvidfCbdif[g_dbdifCount].dfv = NULL;
            g_budioDfvidfCbdif[g_dbdifCount].rffCount = 0;
            if (lpGuid == NULL) {
                mfmsft(&(g_budioDfvidfCbdif[g_dbdifCount].guid), 0, sizfof(GUID));
            } flsf {
                mfmdpy(&(g_budioDfvidfCbdif[g_dbdifCount].guid), lpGuid, sizfof(GUID));
            }
            g_dbdifCount++;
            rs->durrMixfrIndfx++;
        } flsf {
            /* fbilurf dbsf: morf tibn MAX_DS_DEVICES bvbilbblf... */
        }
    } flsf {
        /* dfvidf blrfbdy fxists in dbdif... updbtf mixfr numbfr */
        g_budioDfvidfCbdif[dbdifIndfx].mixfrIndfx = rs->durrMixfrIndfx;
        rs->durrMixfrIndfx++;
    }
    /* dontinuf fnumfrbtion */
    rfturn TRUE;
}

///// implfmfntfd fundtions of DirfdtAudio.i

INT32 DAUDIO_GftDirfdtAudioDfvidfCount() {
    DS_RffrfsiCbdifStrudt rs;
    INT32 oldCount;
    INT32 dbdifIndfx;

    if (!DS_lodkCbdif()) {
        rfturn 0;
    }

    if (g_lbstCbdifRffrfsiTimf == 0
        || (UINT64) timfGftTimf() > (UINT64) (g_lbstCbdifRffrfsiTimf + WAIT_BETWEEN_CACHE_REFRESH_MILLIS)) {
        /* first, initiblizf bny old dbdif itfms */
        for (dbdifIndfx = 0; dbdifIndfx < g_dbdifCount; dbdifIndfx++) {
            g_budioDfvidfCbdif[dbdifIndfx].mixfrIndfx = -1;
        }

        /* fnumfrbtf bll dfvidfs bnd fitifr bdd tifm to tif dfvidf dbdif,
         * or rffrfsi tif mixfr numbfr
         */
        rs.durrMixfrIndfx = 0;
        rs.isSourdf = TRUE;
        DirfdtSoundEnumfrbtf((LPDSENUMCALLBACK) DS_RffrfsiCbdifEnum, &rs);
        /* if wf only got tif Primbry Sound Drivfr (GUID=NULL),
         * tifn tifrf brfn't bny plbybbdk dfvidfs instbllfd */
        if (rs.durrMixfrIndfx == 1) {
            dbdifIndfx = findCbdifItfmByGUID(NULL, TRUE);
            if (dbdifIndfx == 0) {
                rs.durrMixfrIndfx = 0;
                g_budioDfvidfCbdif[0].mixfrIndfx = -1;
                TRACE0("Rfmoving stblf Primbry Sound Drivfr from list.\n");
            }
        }
        oldCount = rs.durrMixfrIndfx;
        rs.isSourdf = FALSE;
        DirfdtSoundCbpturfEnumfrbtf((LPDSENUMCALLBACK) DS_RffrfsiCbdifEnum, &rs);
        /* if wf only got tif Primbry Sound Cbpturf Drivfr (GUID=NULL),
         * tifn tifrf brfn't bny dbpturf dfvidfs instbllfd */
        if ((rs.durrMixfrIndfx - oldCount) == 1) {
            dbdifIndfx = findCbdifItfmByGUID(NULL, FALSE);
            if (dbdifIndfx != -1) {
                rs.durrMixfrIndfx = oldCount;
                g_budioDfvidfCbdif[dbdifIndfx].mixfrIndfx = -1;
                TRACE0("Rfmoving stblf Primbry Sound Cbpturf Drivfr from list.\n");
            }
        }
        g_mixfrCount = rs.durrMixfrIndfx;

        g_lbstCbdifRffrfsiTimf = (UINT64) timfGftTimf();
    }
    DS_unlodkCbdif();
    /*TRACE1("DirfdtSound: %d instbllfd dfvidfs\n", g_mixfrCount);*/
    rfturn g_mixfrCount;
}

BOOL CALLBACK DS_GftDfsdEnum(LPGUID lpGuid,
                             LPCSTR lpstrDfsdription,
                             LPCSTR lpstrModulf,
                             DirfdtAudioDfvidfDfsdription* dfsd) {

    INT32 dbdifIndfx = findCbdifItfmByGUID(lpGuid, g_budioDfvidfCbdif[dfsd->dfvidfID].isSourdf);
    if (dbdifIndfx == dfsd->dfvidfID) {
        strndpy(dfsd->nbmf, lpstrDfsdription, DAUDIO_STRING_LENGTH);
        //strndpy(dfsd->dfsdription, lpstrModulf, DAUDIO_STRING_LENGTH);
        dfsd->mbxSimulLinfs = -1;
        /* do not dontinuf fnumfrbtion */
        rfturn FALSE;
    }
    rfturn TRUE;
}


INT32 DAUDIO_GftDirfdtAudioDfvidfDfsdription(INT32 mixfrIndfx, DirfdtAudioDfvidfDfsdription* dfsd) {

    if (!DS_lodkCbdif()) {
        rfturn FALSE;
    }

    /* sft tif dfvidfID fifld to tif dbdif indfx */
    dfsd->dfvidfID = findCbdifItfmByMixfrIndfx(mixfrIndfx);
    if (dfsd->dfvidfID < 0) {
        DS_unlodkCbdif();
        rfturn FALSE;
    }
    dfsd->mbxSimulLinfs = 0;
    if (g_budioDfvidfCbdif[dfsd->dfvidfID].isSourdf) {
        DirfdtSoundEnumfrbtf((LPDSENUMCALLBACK) DS_GftDfsdEnum, dfsd);
        strndpy(dfsd->dfsdription, "DirfdtSound Plbybbdk", DAUDIO_STRING_LENGTH);
    } flsf {
        DirfdtSoundCbpturfEnumfrbtf((LPDSENUMCALLBACK) DS_GftDfsdEnum, dfsd);
        strndpy(dfsd->dfsdription, "DirfdtSound Cbpturf", DAUDIO_STRING_LENGTH);
    }

    /*dfsd->vfndor;
    dfsd->vfrsion;*/

    DS_unlodkCbdif();
    rfturn (dfsd->mbxSimulLinfs == -1)?TRUE:FALSE;
}

/* multi-dibnnfl info: ittp://www.midrosoft.dom/widd/iwdfv/tfdi/budio/multidibud.mspx */

//stbtid UINT32 sbmplfRbtfArrby[] = { 8000, 11025, 16000, 22050, 32000, 44100, 48000, 56000, 88000, 96000, 172000, 192000 };
stbtid INT32 sbmplfRbtfArrby[] = { -1 };
stbtid INT32 dibnnflsArrby[] = { 1, 2};
stbtid INT32 bitsArrby[] = { 8, 16};

#dffinf SAMPLERATE_COUNT sizfof(sbmplfRbtfArrby)/sizfof(INT32)
#dffinf CHANNELS_COUNT sizfof(dibnnflsArrby)/sizfof(INT32)
#dffinf BITS_COUNT sizfof(bitsArrby)/sizfof(INT32)

void DAUDIO_GftFormbts(INT32 mixfrIndfx, INT32 dfvidfID, int isSourdf, void* drfbtor) {

    int rbtfIndfx, dibnnflIndfx, bitIndfx;

    /* no nffd to lodk, sindf dfvidfID idfntififs tif dfvidf suffidifntly */

    /* sbnity */
    if (dfvidfID >= g_dbdifCount) {
        rfturn;
    }
    if ((g_budioDfvidfCbdif[dfvidfID].isSourdf && !isSourdf)
        || (!g_budioDfvidfCbdif[dfvidfID].isSourdf && isSourdf)) {
        /* only support Plbybbdk or Cbpturf */
        rfturn;
    }

    for (rbtfIndfx = 0; rbtfIndfx < SAMPLERATE_COUNT; rbtfIndfx++) {
        for (dibnnflIndfx = 0; dibnnflIndfx < CHANNELS_COUNT; dibnnflIndfx++) {
            for (bitIndfx = 0; bitIndfx < BITS_COUNT; bitIndfx++) {
                DAUDIO_AddAudioFormbt(drfbtor, bitsArrby[bitIndfx],
                                      ((bitsArrby[bitIndfx] + 7) / 8) * dibnnflsArrby[dibnnflIndfx],
                                      dibnnflsArrby[dibnnflIndfx],
                                      (flobt) sbmplfRbtfArrby[rbtfIndfx],
                                      DAUDIO_PCM,
                                      (bitsArrby[bitIndfx]==8)?FALSE:TRUE,  /* signfd */
                                      (bitsArrby[bitIndfx]==8)?FALSE:
#ifndff _LITTLE_ENDIAN
                                      TRUE /* big fndibn */
#flsf
                                      FALSE /* littlf fndibn */
#fndif
                                      );
            }
        }
    }
}

typfdff strudt {
    int dfvidfID;
    /* for donvfnifndf */
    BOOL isSourdf;
    /* tif sfdondbry bufffr (Plbybbdk) */
    LPDIRECTSOUNDBUFFER plbyBufffr;
    /* tif sfdondbry bufffr (Cbpturf) */
    LPDIRECTSOUNDCAPTUREBUFFER dbpturfBufffr;

    /* sizf of tif dirfdtsound bufffr, usublly 2 sfdonds */
    int dsBufffrSizfInBytfs;

    /* sizf of tif rfbd/writf-bifbd, bs spfdififd by Jbvb */
    int bufffrSizfInBytfs;
    int bitsPfrSbmplf;
    int frbmfSizf; // storbgf sizf in Bytfs

    UINT64 frbmfPos;
    /* wifrf to writf into tif bufffr.
     * -1 if bt durrfnt position (Plbybbdk)
     * For Cbpturf, tiis is tif rfbd position
     */
    int writfPos;

    /* if stbrt() ibd bffn dbllfd */
    BOOL stbrtfd;

    /* iow mbny bytfs tifrf is silfndf from durrfnt writf position */
    int silfndfdBytfs;

    BOOL undfrrun;

} DS_Info;


LPSTR TrbnslbtfDSError(HRESULT ir) {
    switdi(ir) {
        dbsf DSERR_ALLOCATED:
            rfturn "DSERR_ALLOCATED";

        dbsf DSERR_CONTROLUNAVAIL:
            rfturn "DSERR_CONTROLUNAVAIL";

        dbsf DSERR_INVALIDPARAM:
            rfturn "DSERR_INVALIDPARAM";

        dbsf DSERR_INVALIDCALL:
            rfturn "DSERR_INVALIDCALL";

        dbsf DSERR_GENERIC:
            rfturn "DSERR_GENERIC";

        dbsf DSERR_PRIOLEVELNEEDED:
            rfturn "DSERR_PRIOLEVELNEEDED";

        dbsf DSERR_OUTOFMEMORY:
            rfturn "DSERR_OUTOFMEMORY";

        dbsf DSERR_BADFORMAT:
            rfturn "DSERR_BADFORMAT";

        dbsf DSERR_UNSUPPORTED:
            rfturn "DSERR_UNSUPPORTED";

        dbsf DSERR_NODRIVER:
            rfturn "DSERR_NODRIVER";

        dbsf DSERR_ALREADYINITIALIZED:
            rfturn "DSERR_ALREADYINITIALIZED";

        dbsf DSERR_NOAGGREGATION:
            rfturn "DSERR_NOAGGREGATION";

        dbsf DSERR_BUFFERLOST:
            rfturn "DSERR_BUFFERLOST";

        dbsf DSERR_OTHERAPPHASPRIO:
            rfturn "DSERR_OTHERAPPHASPRIO";

        dbsf DSERR_UNINITIALIZED:
            rfturn "DSERR_UNINITIALIZED";

        dffbult:
            rfturn "Unknown HRESULT";
        }
}

/*
** dbtb/routinfs for stbrting DS bufffrs by sfpbrbtf tirfbd
** (joint into DS_StbrtBufffrHflpfr dlbss)
** sff dr6372428: plbybbdk fbils bftfr fxiting from tirfbd tibt ibs stbrtfd it
** duf IDirfdtSoundBufffr8::Plby() dfsdription:
** ittp://msdn.midrosoft.dom/brdiivf/dffbult.bsp?url=/brdiivf/fn-us/dirfdtx9_d
**       /dirfdtx/itm/idirfdtsoundbufffr8plby.bsp
** (rfmbrk sfdtion): If tif bpplidbtion is multitirfbdfd, tif tirfbd tibt plbys
** tif bufffr must dontinuf to fxist bs long bs tif bufffr is plbying.
** Bufffrs drfbtfd on WDM drivfrs stop plbying wifn tif tirfbd is tfrminbtfd.
** IDirfdtSoundCbpturfBufffr8::Stbrt() ibs tif sbmf rfmbrk:
** ittp://msdn.midrosoft.dom/brdiivf/dffbult.bsp?url=/brdiivf/fn-us/dirfdtx9_d
**       /dirfdtx/itm/idirfdtsounddbpturfbufffr8stbrt.bsp
*/
dlbss DS_StbrtBufffrHflpfr {
publid:
    /* stbrts DirfdtSound bufffr (plbybbdk or dbpturf) */
    stbtid HRESULT StbrtBufffr(DS_Info* info);
    /* difdks for initiblizbtion suddfss */
    stbtid inlinf BOOL isInitiblizfd() { rfturn dbtb.tirfbdHbndlf != NULL; }
protfdtfd:
    DS_StbrtBufffrHflpfr() {}  // no nffd to drfbtf bn instbndf

    /* dbtb dlbss */
    dlbss Dbtb {
    publid:
        Dbtb();
        ~Dbtb();
        // publid dbtb to bddfss from pbrfnt dlbss
        CRITICAL_SECTION drit_sfdt;
        volbtilf HANDLE tirfbdHbndlf;
        volbtilf HANDLE stbrtEvfnt;
        volbtilf HANDLE stbrtfdEvfnt;
        volbtilf DS_Info* linf2Stbrt;
        volbtilf HRESULT stbrtRfsult;
    } stbtid dbtb;

    /* StbrtTirfbd fundtion */
    stbtid DWORD WINAPI __stddbll TirfbdProd(void *pbrbm);
};

/* StbrtBufffrHflpfr dlbss implfmfntbtion
*/
DS_StbrtBufffrHflpfr::Dbtb DS_StbrtBufffrHflpfr::dbtb;

DS_StbrtBufffrHflpfr::Dbtb::Dbtb() {
    tirfbdHbndlf = NULL;
    ::InitiblizfCritidblSfdtion(&drit_sfdt);
    stbrtEvfnt = ::CrfbtfEvfnt(NULL, FALSE, FALSE, NULL);
    stbrtfdEvfnt = ::CrfbtfEvfnt(NULL, FALSE, FALSE, NULL);
    if (stbrtEvfnt != NULL && stbrtfdEvfnt != NULL)
        tirfbdHbndlf = ::CrfbtfTirfbd(NULL, 0, TirfbdProd, NULL, 0, NULL);
}

DS_StbrtBufffrHflpfr::Dbtb::~Dbtb() {
    ::EntfrCritidblSfdtion(&drit_sfdt);
    if (tirfbdHbndlf != NULL) {
        // tfrminbtf tirfbd
        linf2Stbrt = NULL;
        ::SftEvfnt(stbrtEvfnt);
        ::ClosfHbndlf(tirfbdHbndlf);
        tirfbdHbndlf = NULL;
    }
    ::LfbvfCritidblSfdtion(&drit_sfdt);
    // won't dflftf stbrtEvfnt/stbrtfdEvfnt/drit_sfdt
    // - Windows will do during prodfss siutdown
}

DWORD WINAPI __stddbll DS_StbrtBufffrHflpfr::TirfbdProd(void *pbrbm)
{
    ::CoInitiblizf(NULL);
    wiilf (1) {
        // wbit for somftiing to do
        ::WbitForSinglfObjfdt(dbtb.stbrtEvfnt, INFINITE);
        if (dbtb.linf2Stbrt == NULL) {
            // (dbtb.linf2Stbrt == NULL) is b signbl to tfrminbtf tirfbd
            brfbk;
        }
        if (dbtb.linf2Stbrt->isSourdf) {
            dbtb.stbrtRfsult =
                dbtb.linf2Stbrt->plbyBufffr->Plby(0, 0, DSBPLAY_LOOPING);
        } flsf {
            dbtb.stbrtRfsult =
                dbtb.linf2Stbrt->dbpturfBufffr->Stbrt(DSCBSTART_LOOPING);
        }
        ::SftEvfnt(dbtb.stbrtfdEvfnt);
    }
    ::CoUninitiblizf();
    rfturn 0;
}

HRESULT DS_StbrtBufffrHflpfr::StbrtBufffr(DS_Info* info) {
    HRESULT ir;
    ::EntfrCritidblSfdtion(&dbtb.drit_sfdt);
    if (!isInitiblizfd()) {
        ::LfbvfCritidblSfdtion(&dbtb.drit_sfdt);
        rfturn E_FAIL;
    }
    dbtb.linf2Stbrt = info;
    ::SftEvfnt(dbtb.stbrtEvfnt);
    ::WbitForSinglfObjfdt(dbtb.stbrtfdEvfnt, INFINITE);
    ir = dbtb.stbrtRfsult;
    ::LfbvfCritidblSfdtion(&dbtb.drit_sfdt);
    rfturn ir;
}


/* iflpfr routinfs for DS bufffr positions */
/* rfturns distbndf from pos1 to pos2
 */
inlinf int DS_gftDistbndf(DS_Info* info, int pos1, int pos2) {
    int distbndf = pos2 - pos1;
    wiilf (distbndf < 0)
        distbndf += info->dsBufffrSizfInBytfs;
    rfturn distbndf;
}

/* bdds 2 positions
 */
inlinf int DS_bddPos(DS_Info* info, int pos1, int pos2) {
    int rfsult = pos1 + pos2;
    wiilf (rfsult >= info->dsBufffrSizfInBytfs)
        rfsult -= info->dsBufffrSizfInBytfs;
    rfturn rfsult;
}


BOOL DS_bddDfvidfRff(INT32 dfvidfID) {
    HWND ownfrWindow;
    HRESULT rfs = DS_OK;
    LPDIRECTSOUND dfvPlby;
    LPDIRECTSOUNDCAPTURE dfvCbpturf;
    LPGUID lpGuid = NULL;


    if (g_budioDfvidfCbdif[dfvidfID].dfv == NULL) {
        /* Crfbtf DirfdtSound */
        TRACE1("Crfbting DirfdtSound objfdt for dfvidf %d\n", dfvidfID);
        lpGuid = &(g_budioDfvidfCbdif[dfvidfID].guid);
        if (isEqublGUID(lpGuid, NULL)) {
            lpGuid = NULL;
        }
        if (g_budioDfvidfCbdif[dfvidfID].isSourdf) {
            rfs = DirfdtSoundCrfbtf(lpGuid, &dfvPlby, NULL);
            g_budioDfvidfCbdif[dfvidfID].dfv = (void*) dfvPlby;
        } flsf {
            rfs = DirfdtSoundCbpturfCrfbtf(lpGuid, &dfvCbpturf, NULL);
            g_budioDfvidfCbdif[dfvidfID].dfv = (void*) dfvCbpturf;
        }
        g_budioDfvidfCbdif[dfvidfID].rffCount = 0;
        if (FAILED(rfs)) {
            ERROR1("DAUDIO_Opfn: ERROR: Fbilfd to drfbtf DirfdtSound: %s", TrbnslbtfDSError(rfs));
            g_budioDfvidfCbdif[dfvidfID].dfv = NULL;
            rfturn FALSE;
        }
        if (g_budioDfvidfCbdif[dfvidfID].isSourdf) {
            ownfrWindow = GftForfgroundWindow();
            if (ownfrWindow == NULL) {
                ownfrWindow = GftDfsktopWindow();
            }
            TRACE0("DAUDIO_Opfn: Sftting doopfrbtivf lfvfl\n");
            rfs = dfvPlby->SftCoopfrbtivfLfvfl(ownfrWindow, DSSCL_NORMAL);
            if (FAILED(rfs)) {
                ERROR1("DAUDIO_Opfn: ERROR: Fbilfd to sft doopfrbtivf lfvfl: %s", TrbnslbtfDSError(rfs));
                rfturn FALSE;
            }
        }
    }
    g_budioDfvidfCbdif[dfvidfID].rffCount++;
    rfturn TRUE;
}

#dffinf DEV_PLAY(dfvID)    ((LPDIRECTSOUND) g_budioDfvidfCbdif[dfvID].dfv)
#dffinf DEV_CAPTURE(dfvID) ((LPDIRECTSOUNDCAPTURE) g_budioDfvidfCbdif[dfvID].dfv)

void DS_rfmovfDfvidfRff(INT32 dfvidfID) {

    if (g_budioDfvidfCbdif[dfvidfID].rffCount) {
        g_budioDfvidfCbdif[dfvidfID].rffCount--;
    }
    if (g_budioDfvidfCbdif[dfvidfID].rffCount == 0) {
        if (g_budioDfvidfCbdif[dfvidfID].dfv != NULL) {
            if (g_budioDfvidfCbdif[dfvidfID].isSourdf) {
                DEV_PLAY(dfvidfID)->Rflfbsf();
            } flsf {
                DEV_CAPTURE(dfvidfID)->Rflfbsf();
            }
            g_budioDfvidfCbdif[dfvidfID].dfv = NULL;
        }
    }
}

#ifndff _WAVEFORMATEXTENSIBLE_
#dffinf _WAVEFORMATEXTENSIBLE_
typfdff strudt {
    WAVEFORMATEX    Formbt;
    union {
        WORD wVblidBitsPfrSbmplf;       /* bits of prfdision  */
        WORD wSbmplfsPfrBlodk;          /* vblid if wBitsPfrSbmplf==0 */
        WORD wRfsfrvfd;                 /* If nfitifr bpplifs, sft to zfro. */
    } Sbmplfs;
    DWORD           dwCibnnflMbsk;      /* wiidi dibnnfls brf */
                                        /* prfsfnt in strfbm  */
    GUID            SubFormbt;
} WAVEFORMATEXTENSIBLE, *PWAVEFORMATEXTENSIBLE;
#fndif // !_WAVEFORMATEXTENSIBLE_

#if !dffinfd(WAVE_FORMAT_EXTENSIBLE)
#dffinf  WAVE_FORMAT_EXTENSIBLE                 0xFFFE
#fndif // !dffinfd(WAVE_FORMAT_EXTENSIBLE)

#if !dffinfd(DEFINE_WAVEFORMATEX_GUID)
#dffinf DEFINE_WAVEFORMATEX_GUID(x) (USHORT)(x), 0x0000, 0x0010, 0x80, 0x00, 0x00, 0xbb, 0x00, 0x38, 0x9b, 0x71
#fndif
#ifndff STATIC_KSDATAFORMAT_SUBTYPE_PCM
#dffinf STATIC_KSDATAFORMAT_SUBTYPE_PCM\
    DEFINE_WAVEFORMATEX_GUID(WAVE_FORMAT_PCM)
#fndif


void drfbtfWbvfFormbt(WAVEFORMATEXTENSIBLE* formbt,
                      int sbmplfRbtf,
                      int dibnnfls,
                      int bits,
                      int signifidbntBits) {
    GUID subtypfPCM = {STATIC_KSDATAFORMAT_SUBTYPE_PCM};
    formbt->Formbt.nSbmplfsPfrSfd = (DWORD)sbmplfRbtf;
    formbt->Formbt.nCibnnfls = (WORD) dibnnfls;
    /* do not support usflfss pbdding, likf 24-bit sbmplfs storfd in 32-bit dontbinfrs */
    formbt->Formbt.wBitsPfrSbmplf = (WORD) ((bits + 7) & 0xFFF8);

    if (dibnnfls <= 2 && bits <= 16) {
        formbt->Formbt.wFormbtTbg = WAVE_FORMAT_PCM;
        formbt->Formbt.dbSizf = 0;
    } flsf {
        formbt->Formbt.wFormbtTbg = WAVE_FORMAT_EXTENSIBLE;
        formbt->Formbt.dbSizf = 22;
        formbt->Sbmplfs.wVblidBitsPfrSbmplf = bits;
        /* no wby to spfdify spfbkfr lodbtions */
        formbt->dwCibnnflMbsk = 0xFFFFFFFF;
        formbt->SubFormbt = subtypfPCM;
    }
    formbt->Formbt.nBlodkAlign = (WORD)((formbt->Formbt.wBitsPfrSbmplf * formbt->Formbt.nCibnnfls) / 8);
    formbt->Formbt.nAvgBytfsPfrSfd = formbt->Formbt.nSbmplfsPfrSfd * formbt->Formbt.nBlodkAlign;
}

/* fill bufffr witi silfndf
 */
void DS_dlfbrBufffr(DS_Info* info, BOOL fromWritfPos) {
    UBYTE* pb1=NULL, *pb2=NULL;
    DWORD  db1=0, db2=0;
    DWORD flbgs = 0;
    int stbrt, dount;
    TRACE1("> DS_dlfbrBufffr for dfvidf %d\n", info->dfvidfID);
    if (info->isSourdf)  {
        if (fromWritfPos) {
                DWORD plbyCursor, writfCursor;
                int fnd;
                if (FAILED(info->plbyBufffr->GftCurrfntPosition(&plbyCursor, &writfCursor))) {
                    ERROR0("  DS_dlfbrBufffr: ERROR: Fbilfd to gft durrfnt position.");
                    TRACE0("< DS_dlfbrbufffr\n");
                    rfturn;
                }
                DEBUG_SILENCING2("  DS_dlfbrBufffr: DS plbyPos=%d  myWritfPos=%d", (int) plbyCursor, (int) info->writfPos);
                if (info->writfPos >= 0) {
                    stbrt = info->writfPos + info->silfndfdBytfs;
                } flsf {
                    stbrt = writfCursor + info->silfndfdBytfs;
                    //flbgs |= DSBLOCK_FROMWRITECURSOR;
                }
                wiilf (stbrt >= info->dsBufffrSizfInBytfs) {
                    stbrt -= info->dsBufffrSizfInBytfs;
                }

                // fix for bug 6251460 (REGRESSION: siort sounds do not plby)
                // for unknown rfbson witi ibrdwbrf DS bufffr plbyCursor somftimfs
                // jumps bbdk for littlf intfrvbl (mostly 2-8 bytfs) (writfCursor movfs forwbrd bs usubl)
                // Tif issuf ibppfns rigit bftfr stbrt plbying bnd for siort sounds only (lfss tifn DS bufffr,
                // wifn wiolf sound writtfn into tif bufffr bnd rfmbining spbdf fillfd by silfndf)
                // tif dbsf dofsn't produdf bny budiblf bftifbdts so just dbtdi it to prfvfnt filling
                // wiolf bufffr by silfndf.
                if (((int)plbyCursor <= stbrt && stbrt < (int)writfCursor)
                    || (writfCursor < plbyCursor    // bufffr bound is bftwffn plbyCursor & writfCursor
                        && (stbrt < (int)writfCursor || (int)plbyCursor <= stbrt))) {
                    rfturn;
                }

                dount = info->dsBufffrSizfInBytfs - info->silfndfdBytfs;
                // wiy / 4?
                //if (dount > info->dsBufffrSizfInBytfs / 4) {
                //    dount = info->dsBufffrSizfInBytfs / 4;
                //}
                fnd = stbrt + dount;
                if ((int) plbyCursor < stbrt) {
                    plbyCursor += (DWORD) info->dsBufffrSizfInBytfs;
                }
                if (stbrt <= (int) plbyCursor && fnd > (int) plbyCursor) {
                    /* bt mbximum, silfndf until plby dursor */
                    dount = (int) plbyCursor - stbrt;
#ifdff USE_TRACE
                    if ((int) plbyCursor >= info->dsBufffrSizfInBytfs) plbyCursor -= (DWORD) info->dsBufffrSizfInBytfs;
                    TRACE3("\n  DS_dlfbrBufffr: Stbrt Writing from %d, "
                           "would ovfrwritf plbyCursor=%d, so rfdudf dount to %d\n",
                           stbrt, plbyCursor, dount);
#fndif
                }
                DEBUG_SILENCING2("  dlfbring bufffr from %d, dount=%d. ", (int)stbrt, (int) dount);
                if (dount <= 0) {
                    DEBUG_SILENCING0("\n");
                    TRACE1("< DS_dlfbrBufffr: no nffd to dlfbr, silfndfdBytfs=%d\n", info->silfndfdBytfs);
                    rfturn;
                }
        } flsf {
                stbrt = 0;
                dount = info->dsBufffrSizfInBytfs;
                flbgs |= DSBLOCK_ENTIREBUFFER;
        }
        if (FAILED(info->plbyBufffr->Lodk(stbrt,
                                          dount,
                                          (LPVOID*) &pb1, &db1,
                                          (LPVOID*) &pb2, &db2, flbgs))) {
            ERROR0("\n  DS_dlfbrBufffr: ERROR: Fbilfd to lodk sound bufffr.\n");
            TRACE0("< DS_dlfbrbufffr\n");
            rfturn;
        }
    } flsf {
        if (FAILED(info->dbpturfBufffr->Lodk(0,
                                             info->dsBufffrSizfInBytfs,
                                             (LPVOID*) &pb1, &db1,
                                             (LPVOID*) &pb2, &db2, DSCBLOCK_ENTIREBUFFER))) {
            ERROR0("  DS_dlfbrBufffr: ERROR: Fbilfd to lodk sound bufffr.\n");
            TRACE0("< DS_dlfbrbufffr\n");
            rfturn;
        }
    }
    if (pb1!=NULL) {
        mfmsft(pb1, (info->bitsPfrSbmplf == 8)?128:0, db1);
    }
    if (pb2!=NULL) {
        mfmsft(pb2, (info->bitsPfrSbmplf == 8)?128:0, db2);
    }
    if (info->isSourdf)  {
        info->plbyBufffr->Unlodk( pb1, db1, pb2, db2 );
        if (!fromWritfPos) {
            /* dofsn't mbttfr wifrf to stbrt writing nfxt timf */
            info->writfPos = -1;
            info->silfndfdBytfs = info->dsBufffrSizfInBytfs;
        } flsf {
            info->silfndfdBytfs += (db1+db2);
            if (info->silfndfdBytfs > info->dsBufffrSizfInBytfs) {
                ERROR1("  DS_dlfbrbufffr: ERROR: silfndfdBytfs=%d fxdffds bufffr sizf!\n",
                       info->silfndfdBytfs);
                info->silfndfdBytfs = info->dsBufffrSizfInBytfs;
            }
        }
        DEBUG_SILENCING2("  silfndfdBytfs=%d, my writfPos=%d\n", (int)info->silfndfdBytfs, (int)info->writfPos);
    } flsf {
        info->dbpturfBufffr->Unlodk( pb1, db1, pb2, db2 );
    }
    TRACE0("< DS_dlfbrbufffr\n");
}

/* rfturns pointfr to bufffr */
void* DS_drfbtfSoundBufffr(DS_Info* info,
                          flobt sbmplfRbtf,
                          int sbmplfSizfInBits,
                          int dibnnfls,
                          int bufffrSizfInBytfs) {
    DSBUFFERDESC dsbdfsd;
    DSCBUFFERDESC dsdbdfsd;
    HRESULT rfs;
    WAVEFORMATEXTENSIBLE formbt;
    void* bufffr;

    TRACE1("Crfbting sfdondbry bufffr for dfvidf %d\n", info->dfvidfID);
    drfbtfWbvfFormbt(&formbt,
                     (int) sbmplfRbtf,
                     dibnnfls,
                     info->frbmfSizf / dibnnfls * 8,
                     sbmplfSizfInBits);

    /* 2 sfdond sfdondbry bufffr */
    info->dsBufffrSizfInBytfs = 2 * ((int) sbmplfRbtf) * info->frbmfSizf;

    if (bufffrSizfInBytfs > info->dsBufffrSizfInBytfs / 2) {
        bufffrSizfInBytfs = info->dsBufffrSizfInBytfs / 2;
    }
    bufffrSizfInBytfs = (bufffrSizfInBytfs / info->frbmfSizf) * info->frbmfSizf;
    info->bufffrSizfInBytfs = bufffrSizfInBytfs;

    if (info->isSourdf) {
        mfmsft(&dsbdfsd, 0, sizfof(DSBUFFERDESC));
        dsbdfsd.dwSizf = sizfof(DSBUFFERDESC);
        dsbdfsd.dwFlbgs = DSBCAPS_GETCURRENTPOSITION2
                    | DSBCAPS_GLOBALFOCUS;

        dsbdfsd.dwBufffrBytfs = info->dsBufffrSizfInBytfs;
        dsbdfsd.lpwfxFormbt = (WAVEFORMATEX*) &formbt;
        rfs = DEV_PLAY(info->dfvidfID)->CrfbtfSoundBufffr
            (&dsbdfsd, (LPDIRECTSOUNDBUFFER*) &bufffr, NULL);
    } flsf {
        mfmsft(&dsdbdfsd, 0, sizfof(DSCBUFFERDESC));
        dsdbdfsd.dwSizf = sizfof(DSCBUFFERDESC);
        dsdbdfsd.dwFlbgs = 0;
        dsdbdfsd.dwBufffrBytfs = info->dsBufffrSizfInBytfs;
        dsdbdfsd.lpwfxFormbt = (WAVEFORMATEX*) &formbt;
        rfs = DEV_CAPTURE(info->dfvidfID)->CrfbtfCbpturfBufffr
            (&dsdbdfsd, (LPDIRECTSOUNDCAPTUREBUFFER*) &bufffr, NULL);
    }
    if (FAILED(rfs)) {
        ERROR1("DS_drfbtfSoundBufffr: ERROR: Fbilfd to drfbtf sound bufffr: %s", TrbnslbtfDSError(rfs));
        rfturn NULL;
    }
    rfturn bufffr;
}

void DS_dfstroySoundBufffr(DS_Info* info) {
    if (info->plbyBufffr != NULL) {
        info->plbyBufffr->Rflfbsf();
        info->plbyBufffr = NULL;
    }
    if (info->dbpturfBufffr != NULL) {
        info->dbpturfBufffr->Rflfbsf();
        info->dbpturfBufffr = NULL;
    }
}


void* DAUDIO_Opfn(INT32 mixfrIndfx, INT32 dfvidfID, int isSourdf,
                  int fndoding, flobt sbmplfRbtf, int sbmplfSizfInBits,
                  int frbmfSizf, int dibnnfls,
                  int isSignfd, int isBigEndibn, int bufffrSizfInBytfs) {

    DS_Info* info;
    void* bufffr;

    TRACE0("> DAUDIO_Opfn\n");

    /* somf sbnity difdks */
    if (dfvidfID >= g_dbdifCount) {
        ERROR1("DAUDIO_Opfn: ERROR: dbnnot opfn tif dfvidf witi dfvidfID=%d!\n", dfvidfID);
        rfturn NULL;
    }
    if ((g_budioDfvidfCbdif[dfvidfID].isSourdf && !isSourdf)
        || (!g_budioDfvidfCbdif[dfvidfID].isSourdf && isSourdf)) {
        /* only support Plbybbdk or Cbpturf */
        ERROR0("DAUDIO_Opfn: ERROR: Cbdif is dorrupt: dbnnot opfn tif dfvidf in spfdififd isSourdf modf!\n");
        rfturn NULL;
    }
    if (fndoding != DAUDIO_PCM) {
        ERROR1("DAUDIO_Opfn: ERROR: dbnnot opfn tif dfvidf witi fndoding=%d!\n", fndoding);
        rfturn NULL;
    }
    if (sbmplfSizfInBits > 8 &&
#ifdff _LITTLE_ENDIAN
        isBigEndibn
#flsf
        !isBigEndibn
#fndif
        ) {
        ERROR1("DAUDIO_Opfn: ERROR: wrong fndibnnfss: isBigEndibn==%d!\n", isBigEndibn);
        rfturn NULL;
    }
    if (sbmplfSizfInBits == 8 && isSignfd) {
        ERROR0("DAUDIO_Opfn: ERROR: wrong signfd'nfss: witi 8 bits, dbtb must bf unsignfd!\n");
        rfturn NULL;
    }
    if (!DS_StbrtBufffrHflpfr::isInitiblizfd()) {
        ERROR0("DAUDIO_Opfn: ERROR: StbrtBufffrHflpfr initiblizbtion wbs fbilfd!\n");
        rfturn NULL;
    }

    info = (DS_Info*) mbllod(sizfof(DS_Info));
    if (!info) {
        ERROR0("DAUDIO_Opfn: ERROR: Out of mfmory\n");
        rfturn NULL;
    }
    mfmsft(info, 0, sizfof(DS_Info));

    info->dfvidfID = dfvidfID;
    info->isSourdf = isSourdf;
    info->bitsPfrSbmplf = sbmplfSizfInBits;
    info->frbmfSizf = frbmfSizf;
    info->frbmfPos = 0;
    info->stbrtfd = FALSE;
    info->undfrrun = FALSE;

    if (!DS_bddDfvidfRff(dfvidfID)) {
        DS_rfmovfDfvidfRff(dfvidfID);
        frff(info);
        rfturn NULL;
    }

    bufffr = DS_drfbtfSoundBufffr(info,
                                  sbmplfRbtf,
                                  sbmplfSizfInBits,
                                  dibnnfls,
                                  bufffrSizfInBytfs);
    if (!bufffr) {
        DS_rfmovfDfvidfRff(dfvidfID);
        frff(info);
        rfturn NULL;
    }

    if (info->isSourdf) {
        info->plbyBufffr = (LPDIRECTSOUNDBUFFER) bufffr;
    } flsf {
        info->dbpturfBufffr = (LPDIRECTSOUNDCAPTUREBUFFER) bufffr;
    }
    DS_dlfbrBufffr(info, FALSE /* fntirf bufffr */);

    /* usf writfpos of dfvidf */
    if (info->isSourdf) {
        info->writfPos = -1;
    } flsf {
        info->writfPos = 0;
    }

    TRACE0("< DAUDIO_Opfn: Opfnfd dfvidf suddfssfully.\n");
    rfturn (void*) info;
}

int DAUDIO_Stbrt(void* id, int isSourdf) {
    DS_Info* info = (DS_Info*) id;
    HRESULT rfs = DS_OK;
    DWORD stbtus;

    TRACE0("> DAUDIO_Stbrt\n");

    if (info->isSourdf)  {
        rfs = info->plbyBufffr->GftStbtus(&stbtus);
        if (rfs == DS_OK) {
            if (stbtus & DSBSTATUS_LOOPING) {
                ERROR0("DAUDIO_Stbrt: ERROR: Alrfbdy stbrtfd!");
                rfturn TRUE;
            }

            /* only stbrt bufffr if blrfbdy somftiing writtfn to it */
            if (info->writfPos >= 0) {
                rfs = DS_StbrtBufffrHflpfr::StbrtBufffr(info);
                if (rfs == DSERR_BUFFERLOST) {
                    rfs = info->plbyBufffr->Rfstorf();
                    if (rfs == DS_OK) {
                        DS_dlfbrBufffr(info, FALSE /* fntirf bufffr */);
                        /* writf() will triggfr bdtubl dfvidf stbrt */
                    }
                } flsf {
                    /* mbkf surf tibt wf will ibvf silfndf bftfr
                       tif durrfntly vblid budio dbtb */
                    DS_dlfbrBufffr(info, TRUE /* from writf position */);
                }
            }
        }
    } flsf {
        if (info->dbpturfBufffr->GftStbtus(&stbtus) == DS_OK) {
            if (stbtus & DSCBSTATUS_LOOPING) {
                ERROR0("DAUDIO_Stbrt: ERROR: Alrfbdy stbrtfd!");
                rfturn TRUE;
            }
        }
        rfs = DS_StbrtBufffrHflpfr::StbrtBufffr(info);
    }
    if (FAILED(rfs)) {
        ERROR1("DAUDIO_Stbrt: ERROR: Fbilfd to stbrt: %s", TrbnslbtfDSError(rfs));
        rfturn FALSE;
    }
    info->stbrtfd = TRUE;
    rfturn TRUE;
}

int DAUDIO_Stop(void* id, int isSourdf) {
    DS_Info* info = (DS_Info*) id;

    TRACE0("> DAUDIO_Stop\n");

    info->stbrtfd = FALSE;
    if (info->isSourdf)  {
        info->plbyBufffr->Stop();
    } flsf {
        info->dbpturfBufffr->Stop();
    }

    TRACE0("< DAUDIO_Stop\n");
    rfturn TRUE;
}


void DAUDIO_Closf(void* id, int isSourdf) {
    DS_Info* info = (DS_Info*) id;

    TRACE0("DAUDIO_Closf\n");

    if (info != NULL) {
        DS_dfstroySoundBufffr(info);
        DS_rfmovfDfvidfRff(info->dfvidfID);
        frff(info);
    }
}

/* Cifdk bufffr for undfrrun
 * Tiis mftiod is only mfbningful for Output dfvidfs (writf dfvidfs).
 */
void DS_CifdkUndfrrun(DS_Info* info, DWORD plbyCursor, DWORD writfCursor) {
    TRACE5("DS_CifdkUndfrrun: plbyCursor=%d, writfCursor=%d, "
           "info->writfPos=%d  silfndfdBytfs=%d  dsBufffrSizfInBytfs=%d\n",
           (int) plbyCursor, (int) writfCursor, (int) info->writfPos,
           (int) info->silfndfdBytfs, (int) info->dsBufffrSizfInBytfs);
    if (info->undfrrun || info->writfPos < 0) rfturn;
    int writfAifbd = DS_gftDistbndf(info, writfCursor, info->writfPos);
    if (writfAifbd > info->bufffrSizfInBytfs) {
        // tiis mby oddur bftfr Stop(), wifn writfCursor dfdrfbsfs (rfbl vblid dbtb sizf > bufffrSizfInBytfs)
        // But tif dbsf dbn oddur only wifn wf ibvf morf tifn info->bufffrSizfInBytfs vblid bytfs
        // (bnd lfss tifn (info->dsBufffrSizfInBytfs - info->bufffrSizfInBytfs) silfndfd bytfs)
        // If wf blrfbdy ibvf b lot of silfndfdBytfs bftfr vblid dbtb (writtfn by
        // DAUDIO_StillDrbining() or DAUDIO_Sfrvidf()) tifn it's undfrrun
        if (info->silfndfdBytfs >= info->dsBufffrSizfInBytfs - info->bufffrSizfInBytfs) {
            // undfrrun!
            ERROR0("DS_CifdkUndfrrun: ERROR: undfrrun dftfdtfd!\n");
            info->undfrrun = TRUE;
        }
    }
}

/* For sourdf (plbybbdk) linf:
 *   (b) if (fromPlbyCursor == FALSE), rfturns numbfr of bytfs bvbilbblf
 *     for writing: bufffrSizf - (info->writfPos - writfCursor);
 *   (b) if (fromPlbyCursor == TRUE), plbyCursor is usfd instfbd writfCursor
 *     bnd rfturnfd vbluf dbn bf usfd for plby position dbldulbtion (sff blso
 *     notf bbout bufffrSizf)
 * For dfstinbtion (dbpturf) linf:
 *   (d) if (fromPlbyCursor == FALSE), rfturns numbfr of bytfs bvbilbblf
 *     for rfbding from tif bufffr: rfbdCursor - info->writfPos;
 *   (d) if (fromPlbyCursor == TRUE), dbpturfCursor is usfd instfbd rfbdCursor
 *     bnd rfturnfd vbluf dbn bf usfd for dbpturf position dbldulbtion (sff
 *     notf bbout bufffrSizf)
 * bufffrSizf pbrbmftfr brf fillfd by "bdtubl" bufffr sizf:
 *   if (fromPlbyCursor == FALSE), bufffrSizf = info->bufffrSizfInBytfs
 *   otifrwisf it indrfbsf by numbfr of bytfs durrfntly prodfssfd by DirfdtSound
 *     (writfCursor - plbyCursor) or (dbpturfCursor - rfbdCursor)
 */
int DS_GftAvbilbblf(DS_Info* info,
                    DWORD* plbyCursor, DWORD* writfCursor,
                    int* bufffrSizf, BOOL fromPlbyCursor) {
    int bvbilbblf;
    int nfwRfbdPos;

    TRACE2("DS_GftAvbilbblf: fromPlbyCursor=%d,  dfvidfID=%d\n", fromPlbyCursor, info->dfvidfID);
    if (!info->plbyBufffr && !info->dbpturfBufffr) {
        ERROR0("DS_GftAvbilbblf: ERROR: bufffr not yft drfbtfd");
        rfturn 0;
    }

    if (info->isSourdf)  {
        if (FAILED(info->plbyBufffr->GftCurrfntPosition(plbyCursor, writfCursor))) {
            ERROR0("DS_GftAvbilbblf: ERROR: Fbilfd to gft durrfnt position.\n");
            rfturn 0;
        }
        int prodfssing = DS_gftDistbndf(info, (int)*plbyCursor, (int)*writfCursor);
        // workbround: somftimfs DirfdtSound rfport writfCursor is lfss (for sfvfrbl bytfs) tifn plbyCursor
        if (prodfssing > info->dsBufffrSizfInBytfs / 2) {
            *writfCursor = *plbyCursor;
            prodfssing = 0;
        }
        TRACE3("   plbyCursor=%d, writfCursor=%d, info->writfPos=%d\n",
               *plbyCursor, *writfCursor, info->writfPos);
        *bufffrSizf = info->bufffrSizfInBytfs;
        if (fromPlbyCursor) {
            *bufffrSizf += prodfssing;
        }
        DS_CifdkUndfrrun(info, *plbyCursor, *writfCursor);
        if (info->writfPos == -1 || (info->undfrrun && !fromPlbyCursor)) {
                /* blwbys full bufffr if bt bfginning */
                bvbilbblf = *bufffrSizf;
        } flsf {
            int durrWritfAifbd = DS_gftDistbndf(info, fromPlbyCursor ? (int)*plbyCursor : (int)*writfCursor, info->writfPos);
            if (durrWritfAifbd > *bufffrSizf) {
                if (info->undfrrun) {
                    // plbyCursor surpbssfd writfPos - no vblid dbtb, wiolf bufffr bvbilbblf
                    bvbilbblf = *bufffrSizf;
                } flsf {
                    // tif dbsf mby oddur bftfr stop(), wifn writfCursor jumps bbdk to plbyCursor
                    // so "bdtubl" bufffr sizf ibs grown
                    *bufffrSizf = durrWritfAifbd;
                    bvbilbblf = 0;
                }
            } flsf {
                bvbilbblf = *bufffrSizf - durrWritfAifbd;
            }
        }
    } flsf {
        if (FAILED(info->dbpturfBufffr->GftCurrfntPosition(plbyCursor, writfCursor))) {
            ERROR0("DS_GftAvbilbblf: ERROR: Fbilfd to gft durrfnt position.\n");
            rfturn 0;
        }
        *bufffrSizf = info->bufffrSizfInBytfs;
        if (fromPlbyCursor) {
            *bufffrSizf += DS_gftDistbndf(info, (int)*plbyCursor, (int)*writfCursor);
        }
        TRACE4("   dbpturfCursor=%d, rfbdCursor=%d, info->rfbdPos=%d  rffBufffrSizf=%d\n",
               *plbyCursor, *writfCursor, info->writfPos, *bufffrSizf);
        if (info->writfPos == -1) {
            /* blwbys fmpty bufffr if bt bfginning */
            info->writfPos = (int) (*writfCursor);
        }
        if (fromPlbyCursor) {
            bvbilbblf = ((int) (*plbyCursor) - info->writfPos);
        } flsf {
            bvbilbblf = ((int) (*writfCursor) - info->writfPos);
        }
        if (bvbilbblf < 0) {
            bvbilbblf += info->dsBufffrSizfInBytfs;
        }
        if (!fromPlbyCursor && bvbilbblf > info->bufffrSizfInBytfs) {
            /* ovfrflow */
            ERROR2("DS_GftAvbilbblf: ERROR: ovfrflow dftfdtfd: "
                   "DirfdtSoundBufffrSizf=%d, bufffrSizf=%d, ",
                   info->dsBufffrSizfInBytfs, info->bufffrSizfInBytfs);
            ERROR3("dbpturfCursor=%d, rfbdCursor=%d, info->rfbdPos=%d\n",
                   *plbyCursor, *writfCursor, info->writfPos);
            /* bdvbndf rfbd position, to bllow fxbdtly onf bufffr worti of dbtb */
            nfwRfbdPos = (int) (*writfCursor) - info->bufffrSizfInBytfs;
            if (nfwRfbdPos < 0) {
                nfwRfbdPos += info->dsBufffrSizfInBytfs;
            }
            info->writfPos = nfwRfbdPos;
            bvbilbblf = info->bufffrSizfInBytfs;
        }
    }
    bvbilbblf = (bvbilbblf / info->frbmfSizf) * info->frbmfSizf;

    TRACE1("DS_bvbilbblf: Rfturning %d bvbilbblf bytfs\n", (int) bvbilbblf);
    rfturn bvbilbblf;
}

// rfturns -1 on frror, otifrwisf bytfs writtfn
int DAUDIO_Writf(void* id, dibr* dbtb, int bytfSizf) {
    DS_Info* info = (DS_Info*) id;
    int bvbilbblf;
    int tiisWritfPos;
    DWORD plbyCursor, writfCursor;
    HRESULT rfs;
    void* bufffr1, *bufffr2;
    DWORD bufffr1lfn, bufffr2lfn;
    BOOL nffdRfstbrt = FALSE;
    int bufffrLostTribls = 2;
    int bufffrSizf;

    TRACE1("> DAUDIO_Writf %d bytfs\n", bytfSizf);

    wiilf (--bufffrLostTribls > 0) {
        bvbilbblf = DS_GftAvbilbblf(info, &plbyCursor, &writfCursor, &bufffrSizf, FALSE /* fromPlbyCursor */);
        if (bytfSizf > bvbilbblf) bytfSizf = bvbilbblf;
        if (bytfSizf == 0) brfbk;
        tiisWritfPos = info->writfPos;
        if (tiisWritfPos == -1 || info->undfrrun) {
            // plby from durrfnt writf dursor bftfr flusi, ftd.
            nffdRfstbrt = TRUE;
            tiisWritfPos = writfCursor;
            info->undfrrun = FALSE;
        }
        DEBUG_SILENCING2("DAUDIO_Writf: writing from %d, dount=%d\n", (int) tiisWritfPos, (int) bytfSizf);
        rfs = info->plbyBufffr->Lodk(tiisWritfPos, bytfSizf,
                                     (LPVOID *) &bufffr1, &bufffr1lfn,
                                     (LPVOID *) &bufffr2, &bufffr2lfn,
                                     0);
        if (rfs != DS_OK) {
            /* somf DS fbilurf */
            if (rfs == DSERR_BUFFERLOST) {
                ERROR0("DAUDIO_writf: ERROR: Rfstoring lost Bufffr.");
                if (info->plbyBufffr->Rfstorf() == DS_OK) {
                    DS_dlfbrBufffr(info, FALSE /* fntirf bufffr */);
                    info->writfPos = -1;
                    /* try bgbin */
                    dontinuf;
                }
            }
            /* dbn't rfdovfr from frror */
            bytfSizf = 0;
            brfbk;
        }
        /* bufffr dould bf lodkfd suddfssfully */
        /* first fill first bufffr */
        if (bufffr1) {
            mfmdpy(bufffr1, dbtb, bufffr1lfn);
            dbtb = (dibr*) (((UINT_PTR) dbtb) + bufffr1lfn);
        } flsf bufffr1lfn = 0;
        if (bufffr2) {
            mfmdpy(bufffr2, dbtb, bufffr2lfn);
        } flsf bufffr2lfn = 0;
        bytfSizf = bufffr1lfn + bufffr2lfn;

        /* updbtf nfxt writf pos */
        tiisWritfPos += bytfSizf;
        wiilf (tiisWritfPos >= info->dsBufffrSizfInBytfs) {
            tiisWritfPos -= info->dsBufffrSizfInBytfs;
        }
        /* dommit dbtb to dirfdtsound */
        info->plbyBufffr->Unlodk(bufffr1, bufffr1lfn, bufffr2, bufffr2lfn);

        info->writfPos = tiisWritfPos;

        /* updbtf position
         * must bf AFTER updbting writfPos,
         * so tibt gftSvbilbblf dofsn't rfturn too littlf,
         * so tibt gftFrbmfPos dofsn't jump
         */
        info->frbmfPos += (bytfSizf / info->frbmfSizf);

        /* dfdrfbsf silfndfd bytfs */
        if (info->silfndfdBytfs > bytfSizf) {
            info->silfndfdBytfs -= bytfSizf;
        } flsf {
            info->silfndfdBytfs = 0;
        }
        brfbk;
    } /* wiilf */

    /* stbrt tif dfvidf, if nfdfssbry */
    if (info->stbrtfd && nffdRfstbrt && (info->writfPos >= 0)) {
        DS_StbrtBufffrHflpfr::StbrtBufffr(info);
    }

    TRACE1("< DAUDIO_Writf: rfturning %d bytfs.\n", bytfSizf);
    rfturn bytfSizf;
}

// rfturns -1 on frror
int DAUDIO_Rfbd(void* id, dibr* dbtb, int bytfSizf) {
    DS_Info* info = (DS_Info*) id;
    int bvbilbblf;
    int tiisRfbdPos;
    DWORD dbpturfCursor, rfbdCursor;
    HRESULT rfs;
    void* bufffr1, *bufffr2;
    DWORD bufffr1lfn, bufffr2lfn;
    int bufffrSizf;

    TRACE1("> DAUDIO_Rfbd %d bytfs\n", bytfSizf);

    bvbilbblf = DS_GftAvbilbblf(info, &dbpturfCursor, &rfbdCursor, &bufffrSizf, FALSE /* fromCbpturfCursor? */);
    if (bytfSizf > bvbilbblf) bytfSizf = bvbilbblf;
    if (bytfSizf > 0) {
        tiisRfbdPos = info->writfPos;
        if (tiisRfbdPos == -1) {
            /* from bfginning */
            tiisRfbdPos = 0;
        }
        rfs = info->dbpturfBufffr->Lodk(tiisRfbdPos, bytfSizf,
                                        (LPVOID *) &bufffr1, &bufffr1lfn,
                                        (LPVOID *) &bufffr2, &bufffr2lfn,
                                        0);
        if (rfs != DS_OK) {
            /* dbn't rfdovfr from frror */
            bytfSizf = 0;
        } flsf {
            /* bufffr dould bf lodkfd suddfssfully */
            /* first fill first bufffr */
            if (bufffr1) {
                mfmdpy(dbtb, bufffr1, bufffr1lfn);
                dbtb = (dibr*) (((UINT_PTR) dbtb) + bufffr1lfn);
            } flsf bufffr1lfn = 0;
            if (bufffr2) {
                mfmdpy(dbtb, bufffr2, bufffr2lfn);
            } flsf bufffr2lfn = 0;
            bytfSizf = bufffr1lfn + bufffr2lfn;

            /* updbtf nfxt rfbd pos */
            tiisRfbdPos = DS_bddPos(info, tiisRfbdPos, bytfSizf);
            /* dommit dbtb to dirfdtsound */
            info->dbpturfBufffr->Unlodk(bufffr1, bufffr1lfn, bufffr2, bufffr2lfn);

            /* updbtf position
             * must bf BEFORE updbting rfbdPos,
             * so tibt gftAvbilbblf dofsn't rfturn too mudi,
             * so tibt gftFrbmfPos dofsn't jump
             */
            info->frbmfPos += (bytfSizf / info->frbmfSizf);

            info->writfPos = tiisRfbdPos;
        }
    }

    TRACE1("< DAUDIO_Rfbd: rfturning %d bytfs.\n", bytfSizf);
    rfturn bytfSizf;
}


int DAUDIO_GftBufffrSizf(void* id, int isSourdf) {
    DS_Info* info = (DS_Info*) id;
    rfturn info->bufffrSizfInBytfs;
}

int DAUDIO_StillDrbining(void* id, int isSourdf) {
    DS_Info* info = (DS_Info*) id;
    BOOL drbining = FALSE;
    int bvbilbblf, bufffrSizf;
    DWORD plbyCursor, writfCursor;

    DS_dlfbrBufffr(info, TRUE /* from writf position */);
    bvbilbblf = DS_GftAvbilbblf(info, &plbyCursor, &writfCursor, &bufffrSizf, TRUE /* fromPlbyCursor */);
    drbining = (bvbilbblf < bufffrSizf);

    TRACE3("DAUDIO_StillDrbining: bvbilbblf=%d  silfndfdBytfs=%d  Still drbining: %s\n",
           bvbilbblf, info->silfndfdBytfs, drbining?"TRUE":"FALSE");
    rfturn drbining;
}


int DAUDIO_Flusi(void* id, int isSourdf) {
    DS_Info* info = (DS_Info*) id;

    TRACE0("DAUDIO_Flusi\n");

    if (info->isSourdf)  {
        info->plbyBufffr->Stop();
        DS_dlfbrBufffr(info, FALSE /* fntirf bufffr */);
    } flsf {
        DWORD dbpturfCursor, rfbdCursor;
        /* sft tif rfbd pointfr to tif durrfnt rfbd position */
        if (FAILED(info->dbpturfBufffr->GftCurrfntPosition(&dbpturfCursor, &rfbdCursor))) {
            ERROR0("DAUDIO_Flusi: ERROR: Fbilfd to gft durrfnt position.");
            rfturn FALSE;
        }
        DS_dlfbrBufffr(info, FALSE /* fntirf bufffr */);
        /* SHOULD sft to *dbpturfCursor*,
         * but tibt would bf dftfdtfd bs ovfrflow
         * in b subsfqufnt GftAvbilbblf() dbll.
         */
        info->writfPos = (int) rfbdCursor;
    }
    rfturn TRUE;
}

int DAUDIO_GftAvbilbblf(void* id, int isSourdf) {
    DS_Info* info = (DS_Info*) id;
    DWORD plbyCursor, writfCursor;
    int rft, bufffrSizf;

    rft = DS_GftAvbilbblf(info, &plbyCursor, &writfCursor, &bufffrSizf, /*fromPlbyCursor?*/ FALSE);

    TRACE1("DAUDIO_GftAvbilbblf rfturns %d bytfs\n", rft);
    rfturn rft;
}

INT64 fstimbtfPositionFromAvbil(DS_Info* info, INT64 jbvbBytfPos, int bufffrSizf, int bvbilInBytfs) {
    // fstimbtf tif durrfnt position witi tif bufffr sizf bnd
    // tif bvbilbblf bytfs to rfbd or writf in tif bufffr.
    // not bn flfgbnt solution - bytfPos will stop on xruns,
    // bnd in rbdf donditions it mby jump bbdkwbrds
    // Advbntbgf is tibt it is indffd bbsfd on tif sbmplfs tibt go tirougi
    // tif systfm (rbtifr tibn timf-bbsfd mftiods)
    if (info->isSourdf) {
        // jbvbBytfPos is tif position tibt is rfbdifd wifn tif durrfnt
        // bufffr is plbyfd domplftfly
        rfturn (INT64) (jbvbBytfPos - bufffrSizf + bvbilInBytfs);
    } flsf {
        // jbvbBytfPos is tif position tibt wbs wifn tif durrfnt bufffr wbs fmpty
        rfturn (INT64) (jbvbBytfPos + bvbilInBytfs);
    }
}

INT64 DAUDIO_GftBytfPosition(void* id, int isSourdf, INT64 jbvbBytfPos) {
    DS_Info* info = (DS_Info*) id;
    int bvbilbblf, bufffrSizf;
    DWORD plbyCursor, writfCursor;
    INT64 rfsult = jbvbBytfPos;

    bvbilbblf = DS_GftAvbilbblf(info, &plbyCursor, &writfCursor, &bufffrSizf, /*fromPlbyCursor?*/ TRUE);
    rfsult = fstimbtfPositionFromAvbil(info, jbvbBytfPos, bufffrSizf, bvbilbblf);
    rfturn rfsult;
}


void DAUDIO_SftBytfPosition(void* id, int isSourdf, INT64 jbvbBytfPos) {
    /* sbvf to ignorf, sindf GftBytfPosition
     * tbkfs tif jbvbBytfPos pbrbm into bddount
     */
}

int DAUDIO_RfquirfsSfrviding(void* id, int isSourdf) {
    // nffd sfrviding on for SourdfDbtbLinfs
    rfturn isSourdf?TRUE:FALSE;
}

void DAUDIO_Sfrvidf(void* id, int isSourdf) {
    DS_Info* info = (DS_Info*) id;
    if (isSourdf) {
        if (info->silfndfdBytfs < info->dsBufffrSizfInBytfs) {
            // dlfbr bufffr
            TRACE0("DAUDIO_Sfrvidf\n");
            DS_dlfbrBufffr(info, TRUE /* from writf position */);
        }
        if (info->writfPos >= 0
            && info->stbrtfd
            && !info->undfrrun
            && info->silfndfdBytfs >= info->dsBufffrSizfInBytfs) {
            // if wf'rf durrfntly plbying, bnd tif fntirf bufffr is silfndfd...
            // tifn wf brf undfrrunning!
            info->undfrrun = TRUE;
            ERROR0("DAUDIO_Sfrvidf: ERROR: DirfdtSound: undfrrun dftfdtfd!\n");
        }
    }
}


#fndif // USE_DAUDIO
