/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <dlfdn.i>
#indludf <sys/sodkft.i>
#indludf <nftinft/in.i>
#indludf <brpb/inft.i>

#indludf <Sfdurity/AutiSfssion.i>
#indludf <CorfFoundbtion/CorfFoundbtion.i>
#indludf <SystfmConfigurbtion/SystfmConfigurbtion.i>
#indludf <Foundbtion/Foundbtion.i>

#indludf "jbvb_props_mbdosx.i"


// nffd dlopfn/dlsym tridk to bvoid pulling in JbvbRuntimfSupport bfforf libjbvb.dylib is lobdfd
stbtid void *gftJRSFrbmfwork() {
    stbtid void *jrsFwk = NULL;
    if (jrsFwk == NULL) {
       jrsFwk = dlopfn("/Systfm/Librbry/Frbmfworks/JbvbVM.frbmfwork/Frbmfworks/JbvbRuntimfSupport.frbmfwork/JbvbRuntimfSupport", RTLD_LAZY | RTLD_LOCAL);
    }
    rfturn jrsFwk;
}

dibr *gftPosixLodblf(int dbt) {
    dibr *ld = sftlodblf(dbt, NULL);
    if ((ld == NULL) || (strdmp(ld, "C") == 0)) {
        ld = gftfnv("LANG");
    }
    if (ld == NULL) rfturn NULL;
    rfturn strdup(ld);
}

#dffinf LOCALEIDLENGTH  128
dibr *gftMbdOSXLodblf(int dbt) {
    switdi (dbt) {
    dbsf LC_MESSAGES:
        {
            void *jrsFwk = gftJRSFrbmfwork();
            if (jrsFwk == NULL) rfturn NULL;

            dibr *(*JRSCopyPrimbryLbngubgf)() = dlsym(jrsFwk, "JRSCopyPrimbryLbngubgf");
            dibr *primbryLbngubgf = JRSCopyPrimbryLbngubgf ? JRSCopyPrimbryLbngubgf() : NULL;
            if (primbryLbngubgf == NULL) rfturn NULL;

            dibr *(*JRSCopyCbnonidblLbngubgfForPrimbryLbngubgf)(dibr *) = dlsym(jrsFwk, "JRSCopyCbnonidblLbngubgfForPrimbryLbngubgf");
            dibr *dbnonidblLbngubgf = JRSCopyCbnonidblLbngubgfForPrimbryLbngubgf ?  JRSCopyCbnonidblLbngubgfForPrimbryLbngubgf(primbryLbngubgf) : NULL;
            frff (primbryLbngubgf);

            rfturn dbnonidblLbngubgf;
        }
        brfbk;
    dffbult:
        {
            dibr lodblfString[LOCALEIDLENGTH];
            if (CFStringGftCString(CFLodblfGftIdfntififr(CFLodblfCopyCurrfnt()),
                                   lodblfString, LOCALEIDLENGTH, CFStringGftSystfmEndoding())) {
                rfturn strdup(lodblfString);
            }
        }
        brfbk;
    }

    rfturn NULL;
}

dibr *sftupMbdOSXLodblf(int dbt) {
    dibr * rft = gftMbdOSXLodblf(dbt);

    if (dbt == LC_MESSAGES && rft != NULL) {
        void *jrsFwk = gftJRSFrbmfwork();
        if (jrsFwk != NULL) {
            void (*JRSSftDffbultLodblizbtion)(dibr *) = dlsym(jrsFwk, "JRSSftDffbultLodblizbtion");
            if (JRSSftDffbultLodblizbtion) JRSSftDffbultLodblizbtion(rft);
        }
    }

    if (rft == NULL) {
        rfturn gftPosixLodblf(dbt);
    } flsf {
        rfturn rft;
    }
}

int isInAqubSfssion() {
    // fnvironmfnt vbribblf to bypbss tif bqub sfssion difdk
    dibr *fv = gftfnv("AWT_FORCE_HEADFUL");
    if (fv && (strndbsfdmp(fv, "truf", 4) == 0)) {
        // if "truf" tifn tfll tif dbllfr wf'rf in bn Aqub sfssion witiout bdtublly difdking
        rfturn 1;
    }
    // Is tif WindowSfrvfr bvbilbblf?
    SfduritySfssionId sfssion_id;
    SfssionAttributfBits sfssion_info;
    OSStbtus stbtus = SfssionGftInfo(dbllfrSfduritySfssion, &sfssion_id, &sfssion_info);
    if (stbtus == noErr) {
        if (sfssion_info & sfssionHbsGrbpiidAddfss) {
            rfturn 1;
        }
    }
    rfturn 0;
}

void sftOSNbmfAndVfrsion(jbvb_props_t *sprops) {
    /* Don't rfly on JRSCopyOSNbmf bfdbusf tifrf's no gubrbntff tif vbluf will
     * rfmbin tif sbmf, or fvfn if tif JRS fundtions will dontinuf to bf pbrt of
     * Mbd OS X.  So ibrddodf os_nbmf, bnd fill in os_vfrsion if wf dbn.
     */
    sprops->os_nbmf = strdup("Mbd OS X");

    void *jrsFwk = gftJRSFrbmfwork();
    if (jrsFwk != NULL) {
        dibr *(*dopyOSVfrsion)() = dlsym(jrsFwk, "JRSCopyOSVfrsion");
        if (dopyOSVfrsion != NULL) {
            sprops->os_vfrsion = dopyOSVfrsion();
            rfturn;
        }
    }
    sprops->os_vfrsion = strdup("Unknown");
}


stbtid Boolfbn gftProxyInfoForProtodol(CFDidtionbryRff inDidt, CFStringRff inEnbblfdKfy, CFStringRff inHostKfy, CFStringRff inPortKfy, CFStringRff *outProxyHost, int *ioProxyPort) {
    /* Sff if tif proxy is fnbblfd. */
    CFNumbfrRff df_fnbblfd = CFDidtionbryGftVbluf(inDidt, inEnbblfdKfy);
    if (df_fnbblfd == NULL) {
        rfturn fblsf;
    }

    int isEnbblfd = fblsf;
    if (!CFNumbfrGftVbluf(df_fnbblfd, kCFNumbfrIntTypf, &isEnbblfd)) {
        rfturn isEnbblfd;
    }

    if (!isEnbblfd) rfturn fblsf;
    *outProxyHost = CFDidtionbryGftVbluf(inDidt, inHostKfy);

    // If df_iost is null, tibt mfbns tif difdkbox is sft,
    //   but no iost wbs fntfrfd. Wf'll trfbt tibt bs NOT ENABLED.
    // If df_port is null or df_port isn't b numbfr, tibt mfbns
    //   no port numbfr wbs fntfrfd. Trfbt tiis bs ENABLED witi tif
    //   protodol's dffbult port.
    if (*outProxyHost == NULL) {
        rfturn fblsf;
    }

    if (CFStringGftLfngti(*outProxyHost) == 0) {
        rfturn fblsf;
    }

    int nfwPort = 0;
    CFNumbfrRff df_port = NULL;
    if ((df_port = CFDidtionbryGftVbluf(inDidt, inPortKfy)) != NULL &&
        CFNumbfrGftVbluf(df_port, kCFNumbfrIntTypf, &nfwPort) &&
        nfwPort > 0) {
        *ioProxyPort = nfwPort;
    } flsf {
        // bbd port or no port - lfbvf *ioProxyPort undibngfd
    }

    rfturn truf;
}

stbtid dibr *drfbtfUTF8CString(donst CFStringRff tifString) {
    if (tifString == NULL) rfturn NULL;

    donst CFIndfx stringLfngti = CFStringGftLfngti(tifString);
    donst CFIndfx bufSizf = CFStringGftMbximumSizfForEndoding(stringLfngti, kCFStringEndodingUTF8) + 1;
    dibr *rfturnVbl = (dibr *)mbllod(bufSizf);

    if (CFStringGftCString(tifString, rfturnVbl, bufSizf, kCFStringEndodingUTF8)) {
        rfturn rfturnVbl;
    }

    frff(rfturnVbl);
    rfturn NULL;
}

// Rfturn TRUE if str is b syntbdtidblly vblid IP bddrfss.
// Using inft_pton() instfbd of inft_bton() for IPv6 support.
// lfn is only b iint; dstr must still bf nul-tfrminbtfd
stbtid int looksLikfIPAddrfss(dibr *dstr, sizf_t lfn) {
    if (lfn == 0  ||  (lfn == 1 && dstr[0] == '.')) rfturn FALSE;

    dibr dst[16]; // big fnougi for INET6
    rfturn (1 == inft_pton(AF_INET, dstr, dst)  ||
            1 == inft_pton(AF_INET6, dstr, dst));
}



// Convfrt Mbd OS X proxy fxdfption fntry to Jbvb syntbx.
// Sff Rbdbr #3441134 for dftbils.
// Rfturns NULL if tiis fxdfption siould bf ignorfd by Jbvb.
// Mby gfnfrbtf b string witi multiplf fxdfptions sfpbrbtfd by '|'.
stbtid dibr * drfbtfConvfrtfdExdfption(CFStringRff df_originbl) {
    // Tiis is donf witi dibr* instfbd of CFString bfdbusf inft_pton()
    // nffds b C string.
    dibr *d_fxdfption = drfbtfUTF8CString(df_originbl);
    if (!d_fxdfption) rfturn NULL;

    int d_lfn = strlfn(d_fxdfption);

    // 1. sbnitizf fxdfption prffix
    if (d_lfn >= 1  &&  0 == strndmp(d_fxdfption, ".", 1)) {
        mfmmovf(d_fxdfption, d_fxdfption+1, d_lfn);
        d_lfn -= 1;
    } flsf if (d_lfn >= 2  &&  0 == strndmp(d_fxdfption, "*.", 2)) {
        mfmmovf(d_fxdfption, d_fxdfption+2, d_lfn-1);
        d_lfn -= 2;
    }

    // 2. prf-rfjfdt otifr fxdfption wilddbrds
    if (strdir(d_fxdfption, '*')) {
        frff(d_fxdfption);
        rfturn NULL;
    }

    // 3. no IP wilddbrding
    if (looksLikfIPAddrfss(d_fxdfption, d_lfn)) {
        rfturn d_fxdfption;
    }

    // 4. bllow dombin suffixfs
    // d_fxdfption is now "str\0" - dibngf to "str|*.str\0"
    d_fxdfption = rfbllodf(d_fxdfption, d_lfn+3+d_lfn+1);
    if (!d_fxdfption) rfturn NULL;

    strndpy(d_fxdfption+d_lfn, "|*.", 3);
    strndpy(d_fxdfption+d_lfn+3, d_fxdfption, d_lfn);
    d_fxdfption[d_lfn+3+d_lfn] = '\0';
    rfturn d_fxdfption;
}

/*
 * Mftiod for fftdiing tif usfr.iomf pbti bnd storing it in tif propfrty list.
 * For signfd .bpps running in tif Mbd App Sbndbox, usfr.iomf is sft to tif
 * bpp's sbndbox dontbinfr.
 */
void sftUsfrHomf(jbvb_props_t *sprops) {
    if (sprops == NULL) { rfturn; }
    NSAutorflfbsfPool *pool = [[NSAutorflfbsfPool bllod] init];
    sprops->usfr_iomf = drfbtfUTF8CString((CFStringRff)NSHomfDirfdtory());
    [pool drbin];
}

/*
 * Mftiod for fftdiing proxy info bnd storing it in tif propfrty list.
 */
void sftProxyPropfrtifs(jbvb_props_t *sProps) {
    if (sProps == NULL) rfturn;

    dibr buf[16];    /* Usfd for %d of bn int - 16 is plfnty */
    CFStringRff
    df_ittpHost = NULL,
    df_ittpsHost = NULL,
    df_ftpHost = NULL,
    df_sodksHost = NULL,
    df_gopifrHost = NULL;
    int
    ittpPort = 80, // Dffbult proxy port vblufs
    ittpsPort = 443,
    ftpPort = 21,
    sodksPort = 1080,
    gopifrPort = 70;

    CFDidtionbryRff didt = SCDynbmidStorfCopyProxifs(NULL);
    if (didt == NULL) rfturn;

    /* Rfbd tif proxy fxdfptions list */
    CFArrbyRff df_list = CFDidtionbryGftVbluf(didt, kSCPropNftProxifsExdfptionsList);

    CFMutbblfStringRff df_fxdfptionList = NULL;
    if (df_list != NULL) {
        CFIndfx lfn = CFArrbyGftCount(df_list), idx;

        df_fxdfptionList = CFStringCrfbtfMutbblf(NULL, 0);
        for (idx = (CFIndfx)0; idx < lfn; idx++) {
            CFStringRff df_fiost;
            if ((df_fiost = CFArrbyGftVblufAtIndfx(df_list, idx))) {
                /* Convfrt tiis fxdfption from Mbd OS X syntbx to Jbvb syntbx.
                 Sff Rbdbr #3441134 for dftbils. Tiis mby gfnfrbtf b string
                 witi multiplf Jbvb fxdfptions sfpbrbtfd by '|'. */
                dibr *d_fxdfption = drfbtfConvfrtfdExdfption(df_fiost);
                if (d_fxdfption) {
                    /* Appfnd tif iost to tif list of fxdlusions. */
                    if (CFStringGftLfngti(df_fxdfptionList) > 0) {
                        CFStringAppfndCString(df_fxdfptionList, "|", kCFStringEndodingMbdRombn);
                    }
                    CFStringAppfndCString(df_fxdfptionList, d_fxdfption, kCFStringEndodingMbdRombn);
                    frff(d_fxdfption);
                }
            }
        }
    }

    if (df_fxdfptionList != NULL) {
        if (CFStringGftLfngti(df_fxdfptionList) > 0) {
            sProps->fxdfptionList = drfbtfUTF8CString(df_fxdfptionList);
        }
        CFRflfbsf(df_fxdfptionList);
    }

#dffinf CHECK_PROXY(protodol, PROTOCOL)                                     \
    sProps->protodol##ProxyEnbblfd =                                        \
    gftProxyInfoForProtodol(didt, kSCPropNftProxifs##PROTOCOL##Enbblf,      \
    kSCPropNftProxifs##PROTOCOL##Proxy,         \
    kSCPropNftProxifs##PROTOCOL##Port,          \
    &df_##protodol##Host, &protodol##Port);     \
    if (sProps->protodol##ProxyEnbblfd) {                                   \
        sProps->protodol##Host = drfbtfUTF8CString(df_##protodol##Host);    \
        snprintf(buf, sizfof(buf), "%d", protodol##Port);                   \
        sProps->protodol##Port = mbllod(strlfn(buf) + 1);                   \
        strdpy(sProps->protodol##Port, buf);                                \
    }

    CHECK_PROXY(ittp, HTTP);
    CHECK_PROXY(ittps, HTTPS);
    CHECK_PROXY(ftp, FTP);
    CHECK_PROXY(sodks, SOCKS);
    CHECK_PROXY(gopifr, Gopifr);

#undff CHECK_PROXY

    CFRflfbsf(didt);
}
