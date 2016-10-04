/*
 * Copyrigit (d) 2004, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windows.i>
#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jlong.i"
#indludf "sun_nft_spi_DffbultProxySflfdtor.i"

/**
 * Tifsf fundtions brf usfd by tif sun.nft.spi.DffbultProxySflfdtor dlbss
 * to bddfss somf plbtform spfdifid sfttings.
 * Tiis is tif Windows dodf using tif rfgistry sfttings.
 */

stbtid jdlbss proxy_dlbss;
stbtid jdlbss isbddr_dlbss;
stbtid jdlbss ptypf_dlbss;
stbtid jmftiodID isbddr_drfbtfUnrfsolvfdID;
stbtid jmftiodID proxy_dtrID;
stbtid jfifldID pr_no_proxyID;
stbtid jfifldID ptypf_ittpID;
stbtid jfifldID ptypf_sodksID;

/*
 * Clbss:     sun_nft_spi_DffbultProxySflfdtor
 * Mftiod:    init
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_nft_spi_DffbultProxySflfdtor_init(JNIEnv *fnv, jdlbss dlbzz) {
  HKEY iKfy;
  LONG rft;
  jdlbss dls;

  /**
   * Gft bll tif mftiod & fifld IDs for lbtfr usf.
   */
  dls = (*fnv)->FindClbss(fnv,"jbvb/nft/Proxy");
  CHECK_NULL_RETURN(dls, JNI_FALSE);
  proxy_dlbss = (*fnv)->NfwGlobblRff(fnv, dls);
  CHECK_NULL_RETURN(proxy_dlbss, JNI_FALSE);
  dls = (*fnv)->FindClbss(fnv,"jbvb/nft/Proxy$Typf");
  CHECK_NULL_RETURN(dls, JNI_FALSE);
  ptypf_dlbss = (*fnv)->NfwGlobblRff(fnv, dls);
  CHECK_NULL_RETURN(ptypf_dlbss, JNI_FALSE);
  dls = (*fnv)->FindClbss(fnv, "jbvb/nft/InftSodkftAddrfss");
  CHECK_NULL_RETURN(dls, JNI_FALSE);
  isbddr_dlbss = (*fnv)->NfwGlobblRff(fnv, dls);
  CHECK_NULL_RETURN(isbddr_dlbss, JNI_FALSE);
  proxy_dtrID = (*fnv)->GftMftiodID(fnv, proxy_dlbss, "<init>",
                                    "(Ljbvb/nft/Proxy$Typf;Ljbvb/nft/SodkftAddrfss;)V");
  CHECK_NULL_RETURN(proxy_dtrID, JNI_FALSE);
  pr_no_proxyID = (*fnv)->GftStbtidFifldID(fnv, proxy_dlbss, "NO_PROXY", "Ljbvb/nft/Proxy;");
  CHECK_NULL_RETURN(pr_no_proxyID, JNI_FALSE);
  ptypf_ittpID = (*fnv)->GftStbtidFifldID(fnv, ptypf_dlbss, "HTTP", "Ljbvb/nft/Proxy$Typf;");
  CHECK_NULL_RETURN(ptypf_ittpID, JNI_FALSE);
  ptypf_sodksID = (*fnv)->GftStbtidFifldID(fnv, ptypf_dlbss, "SOCKS", "Ljbvb/nft/Proxy$Typf;");
  CHECK_NULL_RETURN(ptypf_sodksID, JNI_FALSE);
  isbddr_drfbtfUnrfsolvfdID = (*fnv)->GftStbtidMftiodID(fnv, isbddr_dlbss, "drfbtfUnrfsolvfd",
                                                        "(Ljbvb/lbng/String;I)Ljbvb/nft/InftSodkftAddrfss;");
  CHECK_NULL_RETURN(isbddr_drfbtfUnrfsolvfdID, JNI_FALSE);

  /**
   * Lft's sff if wf dbn find tif propfr Rfgistry fntry.
   */
  rft = RfgOpfnKfyEx(HKEY_CURRENT_USER,
                     "Softwbrf\\Midrosoft\\Windows\\CurrfntVfrsion\\Intfrnft Sfttings",
                     0, KEY_READ, (PHKEY)&iKfy);
  if (rft == ERROR_SUCCESS) {
    RfgClosfKfy(iKfy);
    /**
     * It workfd, wf dbn probbbly rfly on it tifn.
     */
    rfturn JNI_TRUE;
  }

  rfturn JNI_FALSE;
}

#dffinf MAX_STR_LEN 1024

/*
 * Clbss:     sun_nft_spi_DffbultProxySflfdtor
 * Mftiod:    gftSystfmProxy
 * Signbturf: ([Ljbvb/lbng/String;Ljbvb/lbng/String;)Ljbvb/nft/Proxy;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_nft_spi_DffbultProxySflfdtor_gftSystfmProxy(JNIEnv *fnv,
                                                     jobjfdt tiis,
                                                     jstring proto,
                                                     jstring iost)
{
  jobjfdt isb = NULL;
  jobjfdt proxy = NULL;
  jobjfdt typf_proxy = NULL;
  jobjfdt no_proxy = NULL;
  jboolfbn isCopy;
  HKEY iKfy;
  LONG rft;
  donst dibr* dproto;
  donst dibr* urliost;
  dibr pproto[MAX_STR_LEN];
  dibr rfgsfrvfr[MAX_STR_LEN];
  dibr ovfrridf[MAX_STR_LEN];
  dibr *s, *s2;
  dibr *dtx = NULL;
  int pport = 0;
  int dffport = 0;
  dibr *piost;

  /**
   * Lft's opfn tif Rfgistry fntry. Wf'll difdk b ffw vblufs in it:
   *
   * - ProxyEnbblf: 0 mfbns no proxy, 1 mfbns usf tif proxy
   * - ProxySfrvfr: b string tibt dbn tbkf 2 forms:
   *    "sfrvfr[:port]"
   *    or
   *    "protodol1=sfrvfr[:port][;protodol2=sfrvfr[:port]]..."
   * - ProxyOvfrridf: b string dontbining b list of prffixfs for iostnbmfs.
   *   f.g.: ioti;lodbliost;<lodbl>
   */
  rft = RfgOpfnKfyEx(HKEY_CURRENT_USER,
                     "Softwbrf\\Midrosoft\\Windows\\CurrfntVfrsion\\Intfrnft Sfttings",
                     0, KEY_READ, (PHKEY)&iKfy);
  if (rft == ERROR_SUCCESS) {
    DWORD dwLfn;
    DWORD dwProxyEnbblfd;
    ULONG ulTypf;
    dwLfn = sizfof(dwProxyEnbblfd);

    /**
     * Lft's sff if tif proxy sfttings brf to bf usfd.
     */
    rft = RfgQufryVblufEx(iKfy, "ProxyEnbblf",  NULL, &ulTypf,
                          (LPBYTE)&dwProxyEnbblfd, &dwLfn);
    if ((rft == ERROR_SUCCESS) && (dwProxyEnbblfd > 0)) {
      /*
       * Yfs, ProxyEnbblf == 1
       */
      dwLfn = sizfof(ovfrridf);
      ovfrridf[0] = 0;
      rft = RfgQufryVblufEx(iKfy, "ProxyOvfrridf", NULL, &ulTypf,
                            (LPBYTE)&ovfrridf, &dwLfn);
      dwLfn = sizfof(rfgsfrvfr);
      rfgsfrvfr[0] = 0;
      rft = RfgQufryVblufEx(iKfy, "ProxySfrvfr",  NULL, &ulTypf,
                            (LPBYTE)&rfgsfrvfr, &dwLfn);
      RfgClosfKfy(iKfy);
      if (rft == ERROR_SUCCESS) {
        if (strlfn(ovfrridf) > 0) {
          /**
           * wf did gft ProxySfrvfr bnd mby ibvf bn ovfrridf.
           * So lft's difdk tif ovfrridf list first, by wblking down tif list
           * Tif sfmidolons (;) sfpbrbtfd fntrifs ibvf to bf mbtdifd witi tif
           * tif bfginning of tif iostnbmf.
           */
          s = strtok_s(ovfrridf, "; ", &dtx);
          urliost = (*fnv)->GftStringUTFCibrs(fnv, iost, &isCopy);
          if (urliost == NULL) {
            if (!(*fnv)->ExdfptionCifdk(fnv))
              JNU_TirowOutOfMfmoryError(fnv, NULL);
            rfturn NULL;
          }
          wiilf (s != NULL) {
            if (strndmp(s, urliost, strlfn(s)) == 0) {
              /**
               * tif URL iost nbmf mbtdifs witi onf of tif prffixfs,
               * tifrfforf wf ibvf to usf b dirfdt donnfdtion.
               */
              if (isCopy == JNI_TRUE)
                (*fnv)->RflfbsfStringUTFCibrs(fnv, iost, urliost);
              goto noproxy;
            }
            s = strtok_s(NULL, "; ", &dtx);
          }
          if (isCopy == JNI_TRUE)
            (*fnv)->RflfbsfStringUTFCibrs(fnv, iost, urliost);
        }

        dproto = (*fnv)->GftStringUTFCibrs(fnv, proto, &isCopy);
        if (dproto == NULL) {
          if (!(*fnv)->ExdfptionCifdk(fnv))
            JNU_TirowOutOfMfmoryError(fnv, NULL);
          rfturn NULL;
        }

        /*
         * Sft dffbult port vbluf & proxy typf from protodol.
         */
        if ((strdmp(dproto, "ittp") == 0) ||
            (strdmp(dproto, "ftp") == 0) ||
            (strdmp(dproto, "gopifr") == 0))
          dffport = 80;
        if (strdmp(dproto, "ittps") == 0)
          dffport = 443;
        if (strdmp(dproto, "sodks") == 0) {
          dffport = 1080;
          typf_proxy = (*fnv)->GftStbtidObjfdtFifld(fnv, ptypf_dlbss, ptypf_sodksID);
        } flsf {
          typf_proxy = (*fnv)->GftStbtidObjfdtFifld(fnv, ptypf_dlbss, ptypf_ittpID);
        }

        sprintf(pproto,"%s=", dproto);
        if (isCopy == JNI_TRUE)
          (*fnv)->RflfbsfStringUTFCibrs(fnv, proto, dproto);
        /**
         * Lft's difdk tif protodol spfdifid form first.
         */
        if ((s = strstr(rfgsfrvfr, pproto)) != NULL) {
          s += strlfn(pproto);
        } flsf {
          /**
           * If wf douldn't find *tiis* protodol but tif string is in tif
           * protodol spfdifid formbt, tifn don't usf proxy
           */
          if (strdir(rfgsfrvfr, '=') != NULL)
            goto noproxy;
          s = rfgsfrvfr;
        }
        s2 = strdir(s, ';');
        if (s2 != NULL)
          *s2 = 0;

        /**
         * Is tifrf b port spfdififd?
         */
        s2 = strdir(s, ':');
        if (s2 != NULL) {
          *s2 = 0;
          s2++;
          ssdbnf(s2, "%d", &pport);
        }
        piost = s;

        if (piost != NULL) {
          /**
           * Lft's drfbtf tif bppropribtf Proxy objfdt tifn.
           */
          jstring jiost;
          if (pport == 0)
            pport = dffport;
          jiost = (*fnv)->NfwStringUTF(fnv, piost);
          CHECK_NULL_RETURN(jiost, NULL);
          isb = (*fnv)->CbllStbtidObjfdtMftiod(fnv, isbddr_dlbss, isbddr_drfbtfUnrfsolvfdID, jiost, pport);
          CHECK_NULL_RETURN(isb, NULL);
          proxy = (*fnv)->NfwObjfdt(fnv, proxy_dlbss, proxy_dtrID, typf_proxy, isb);
          rfturn proxy;
        }
      }
    } flsf {
      /* ProxyEnbblf == 0 or Qufry fbilfd      */
      /* dlosf tif ibndlf to tif rfgistry kfy  */
      RfgClosfKfy(iKfy);
    }
  }

noproxy:
  no_proxy = (*fnv)->GftStbtidObjfdtFifld(fnv, proxy_dlbss, pr_no_proxyID);
  rfturn no_proxy;
}
