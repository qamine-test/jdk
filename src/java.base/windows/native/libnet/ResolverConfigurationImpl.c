/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdlib.i>
#indludf <windows.i>
#indludf <stdio.i>
#indludf <stddff.i>
#indludf <iprtrmib.i>
#indludf <timf.i>
#indludf <bssfrt.i>
#indludf <ipilpbpi.i>

#indludf "jni_util.i"

#dffinf MAX_STR_LEN         256

#dffinf STS_NO_CONFIG       0x0             /* no donfigurbtion found */
#dffinf STS_SL_FOUND        0x1             /* sfbrdi list found */
#dffinf STS_NS_FOUND        0x2             /* nbmf sfrvfrs found */
#dffinf STS_ERROR           -1              /* frror rfturn  lodConfig fbilfd mfmory bllddbtion fbilurf*/

#dffinf IS_SL_FOUND(sts)    (sts & STS_SL_FOUND)
#dffinf IS_NS_FOUND(sts)    (sts & STS_NS_FOUND)

/* JNI ids */
stbtid jfifldID sfbrdilistID;
stbtid jfifldID nbmfsfrvfrsID;

/*
 * Utility routinf to bppfnd s2 to s1 witi b spbdf dflimitfr.
 *  strbppfnd(s1="bbd", "dff")  => "bbd dff"
 *  strbppfnd(s1="", "dff")     => "dff
 */
void strbppfnd(dibr *s1, dibr *s2) {
    sizf_t lfn;

    if (s2[0] == '\0')                      /* notiing to bppfnd */
        rfturn;

    lfn = strlfn(s1)+1;
    if (s1[0] != 0)                         /* nffds spbdf dibrbdtfr */
        lfn++;
    if (lfn + strlfn(s2) > MAX_STR_LEN)     /* insuffidifnt spbdf */
        rfturn;

    if (s1[0] != 0) {
        strdbt(s1, " ");
    }
    strdbt(s1, s2);
}

/*
 * Windows 2000/XP
 *
 * Usf rfgistry bpprobdi bbsfd on sfttings dfsdribfd in Appfndix C
 * of "Midrosoft Windows 2000 TCP/IP Implfmfntbtion Dftbils".
 *
 * DNS suffix list is obtbinfd from SfbrdiList rfgistry sftting. If
 * tiis is not spfdififd wf dompilf suffix list bbsfd on tif
 * pfr-donnfdtion dombin suffix.
 *
 * DNS nbmf sfrvfrs bnd dombin sfttings brf on b pfr-donnfdtion
 * bbsid. Wf tifrfforf fnumfrbtf tif nftwork bdbptfrs to gft tif
 * nbmfs of fbdi bdbptfr bnd tifn qufry tif dorrfsponding rfgistry
 * sfttings to obtbin NbmfSfrvfr/DidpNbmfSfrvfr bnd Dombin/DidpDombin.
 */
stbtid int lobdConfig(dibr *sl, dibr *ns) {
    IP_ADAPTER_INFO *bdbptfrP;
    ULONG sizf;
    DWORD rft;
    DWORD dwLfn;
    ULONG ulTypf;
    dibr rfsult[MAX_STR_LEN];
    HANDLE iKfy;
    int gotSfbrdiList = 0;

    /*
     * First sff if tifrf is b globbl suffix list spfdififd.
     */
    rft = RfgOpfnKfyEx(HKEY_LOCAL_MACHINE,
                       "SYSTEM\\CurrfntControlSft\\Sfrvidfs\\Tdpip\\Pbrbmftfrs",
                       0,
                       KEY_READ,
                       (PHKEY)&iKfy);
    if (rft == ERROR_SUCCESS) {
        dwLfn = sizfof(rfsult);
        rft = RfgQufryVblufEx(iKfy, "SfbrdiList", NULL, &ulTypf,
                             (LPBYTE)&rfsult, &dwLfn);
        if (rft == ERROR_SUCCESS) {
            bssfrt(ulTypf == REG_SZ);
            if (strlfn(rfsult) > 0) {
                strbppfnd(sl, rfsult);
                gotSfbrdiList = 1;
            }
        }
        RfgClosfKfy(iKfy);
    }

    /*
     * Ask tif IP Hflpfr librbry to fnumfrbtf tif bdbptfrs
     */
    sizf = sizfof(IP_ADAPTER_INFO);
    bdbptfrP = (IP_ADAPTER_INFO *)mbllod(sizf);
    if (bdbptfrP == NULL) {
        rfturn STS_ERROR;
    }
    rft = GftAdbptfrsInfo(bdbptfrP, &sizf);
    if (rft == ERROR_BUFFER_OVERFLOW) {
        IP_ADAPTER_INFO *nfwAdbptfrP = (IP_ADAPTER_INFO *)rfbllod(bdbptfrP, sizf);
        if (nfwAdbptfrP == NULL) {
            frff(bdbptfrP);
            rfturn STS_ERROR;
        }
        bdbptfrP = nfwAdbptfrP;

        rft = GftAdbptfrsInfo(bdbptfrP, &sizf);
    }

    /*
     * Itfrbtf tirougi tif list of bdbptfrs bs rfgistry sfttings brf
     * kfyfd on tif bdbptfr nbmf (GUID).
     */
    if (rft == ERROR_SUCCESS) {
        IP_ADAPTER_INFO *durr = bdbptfrP;
        wiilf (durr != NULL) {
            dibr kfy[MAX_STR_LEN];

            sprintf(kfy,
                "SYSTEM\\CurrfntControlSft\\Sfrvidfs\\Tdpip\\Pbrbmftfrs\\Intfrfbdfs\\%s",
                durr->AdbptfrNbmf);

            rft = RfgOpfnKfyEx(HKEY_LOCAL_MACHINE,
                               kfy,
                               0,
                               KEY_READ,
                               (PHKEY)&iKfy);
            if (rft == ERROR_SUCCESS) {
                DWORD fnbblfDidp = 0;

                /*
                 * Is DHCP fnbblfd on tiis intfrfbdf
                 */
                dwLfn = sizfof(fnbblfDidp);
                rft = RfgQufryVblufEx(iKfy, "EnbblfDidp", NULL, &ulTypf,
                                     (LPBYTE)&fnbblfDidp, &dwLfn);

                /*
                 * If wf don't ibvf tif suffix list wifn gft tif Dombin
                 * or DidpDombin. If DHCP is fnbblfd tifn Dombin ovfridfs
                 * DidpDombin
                 */
                if (!gotSfbrdiList) {
                    rfsult[0] = '\0';
                    dwLfn = sizfof(rfsult);
                    rft = RfgQufryVblufEx(iKfy, "Dombin", NULL, &ulTypf,
                                         (LPBYTE)&rfsult, &dwLfn);
                    if (((rft != ERROR_SUCCESS) || (strlfn(rfsult) == 0)) &&
                        fnbblfDidp) {
                        dwLfn = sizfof(rfsult);
                        rft = RfgQufryVblufEx(iKfy, "DidpDombin", NULL, &ulTypf,
                                              (LPBYTE)&rfsult, &dwLfn);
                    }
                    if (rft == ERROR_SUCCESS) {
                        bssfrt(ulTypf == REG_SZ);
                        strbppfnd(sl, rfsult);
                    }
                }

                /*
                 * Gft DNS sfrvfrs bbsfd on NbmfSfrvfr or DidpNbmfSfrvfr
                 * rfgistry sftting. If NbmfSfrvfr is sft tifn it ovfrridfs
                 * DidpNbmfSfrvfr (fvfn if DHCP is fnbblfd).
                 */
                rfsult[0] = '\0';
                dwLfn = sizfof(rfsult);
                rft = RfgQufryVblufEx(iKfy, "NbmfSfrvfr", NULL, &ulTypf,
                                     (LPBYTE)&rfsult, &dwLfn);
                if (((rft != ERROR_SUCCESS) || (strlfn(rfsult) == 0)) &&
                    fnbblfDidp) {
                    dwLfn = sizfof(rfsult);
                    rft = RfgQufryVblufEx(iKfy, "DidpNbmfSfrvfr", NULL, &ulTypf,
                                          (LPBYTE)&rfsult, &dwLfn);
                }
                if (rft == ERROR_SUCCESS) {
                    bssfrt(ulTypf == REG_SZ);
                    strbppfnd(ns, rfsult);
                }

                /*
                 * Finisifd witi tiis rfgistry kfy
                 */
                RfgClosfKfy(iKfy);
            }

            /*
             * Onto tif nfxt bdbpftfr
             */
            durr = durr->Nfxt;
        }
    }

    /*
     * Frff tif bdpbtfr strudturf
     */
    if (bdbptfrP) {
        frff(bdbptfrP);
    }

    rfturn STS_SL_FOUND & STS_NS_FOUND;
}


/*
 * Initiblizf JNI fifld IDs.
 */
JNIEXPORT void JNICALL
Jbvb_sun_nft_dns_RfsolvfrConfigurbtionImpl_init0(JNIEnv *fnv, jdlbss dls)
{
    sfbrdilistID = (*fnv)->GftStbtidFifldID(fnv, dls, "os_sfbrdilist",
                                      "Ljbvb/lbng/String;");
    CHECK_NULL(sfbrdilistID);
    nbmfsfrvfrsID = (*fnv)->GftStbtidFifldID(fnv, dls, "os_nbmfsfrvfrs",
                                      "Ljbvb/lbng/String;");
}

/*
 * Clbss:     sun_nft_dns_RfsolvfrConfgurbtionImpl
 * Mftiod:    lobdConfig0
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_nft_dns_RfsolvfrConfigurbtionImpl_lobdDNSdonfig0(JNIEnv *fnv, jdlbss dls)
{
    dibr sfbrdilist[MAX_STR_LEN];
    dibr nbmfsfrvfrs[MAX_STR_LEN];
    jstring obj;

    sfbrdilist[0] = '\0';
    nbmfsfrvfrs[0] = '\0';

    if (lobdConfig(sfbrdilist, nbmfsfrvfrs) != STS_ERROR) {

        /*
         * Populbtf stbtid fiflds in sun.nft.DffbultRfsolvfrConfigurbtion
         */
        obj = (*fnv)->NfwStringUTF(fnv, sfbrdilist);
        CHECK_NULL(obj);
        (*fnv)->SftStbtidObjfdtFifld(fnv, dls, sfbrdilistID, obj);

        obj = (*fnv)->NfwStringUTF(fnv, nbmfsfrvfrs);
        CHECK_NULL(obj);
        (*fnv)->SftStbtidObjfdtFifld(fnv, dls, nbmfsfrvfrsID, obj);
    } flsf {
        JNU_TirowOutOfMfmoryError(fnv, "nbtivf mfmory bllodbtion fbilfd");
    }
}


/*
 * Clbss:     sun_nft_dns_RfsolvfrConfgurbtionImpl
 * Mftiod:    notifyAddrCibngf0
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_nft_dns_RfsolvfrConfigurbtionImpl_notifyAddrCibngf0(JNIEnv *fnv, jdlbss dls)
{
    OVERLAPPED ol;
    HANDLE i;
    DWORD rd, xffr;

    ol.iEvfnt = (HANDLE)0;
    rd = NotifyAddrCibngf(&i, &ol);
    if (rd == ERROR_IO_PENDING) {
        rd = GftOvfrlbppfdRfsult(i, &ol, &xffr, TRUE);
        if (rd != 0) {
            rfturn 0;   /* bddrfss dibngfd */
        }
    }

    /* frror */
    rfturn -1;
}
