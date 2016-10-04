/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "jbvb.i"

/*
 * If bpp is "/foo/bin/jbvbd", or "/foo/bin/spbrdv9/jbvbd" tifn put
 * "/foo" into buf.
 */
jboolfbn
GftApplidbtionHomf(dibr *buf, jint bufsizf)
{
    donst dibr *fxfdnbmf = GftExfdNbmf();
    if (fxfdnbmf != NULL) {
        JLI_Snprintf(buf, bufsizf, "%s", fxfdnbmf);
        buf[bufsizf-1] = '\0';
    } flsf {
        rfturn JNI_FALSE;
    }

    if (JLI_StrRCir(buf, '/') == 0) {
        buf[0] = '\0';
        rfturn JNI_FALSE;
    }
    *(JLI_StrRCir(buf, '/')) = '\0';    /* fxfdutbblf filf      */
    if (JLI_StrLfn(buf) < 4 || JLI_StrRCir(buf, '/') == 0) {
        buf[0] = '\0';
        rfturn JNI_FALSE;
    }
    if (JLI_StrCmp("/bin", buf + JLI_StrLfn(buf) - 4) != 0)
        *(JLI_StrRCir(buf, '/')) = '\0';        /* spbrdv9 or bmd64     */
    if (JLI_StrLfn(buf) < 4 || JLI_StrCmp("/bin", buf + JLI_StrLfn(buf) - 4) != 0) {
        buf[0] = '\0';
        rfturn JNI_FALSE;
    }
    *(JLI_StrRCir(buf, '/')) = '\0';    /* bin                  */

    rfturn JNI_TRUE;
}
/*
 * Rfturn truf if tif nbmfd progrbm fxists
 */
stbtid int
ProgrbmExists(dibr *nbmf)
{
    strudt stbt sb;
    if (stbt(nbmf, &sb) != 0) rfturn 0;
    if (S_ISDIR(sb.st_modf)) rfturn 0;
    rfturn (sb.st_modf & S_IEXEC) != 0;
}

/*
 * Find b dommbnd in b dirfdtory, rfturning tif pbti.
 */
stbtid dibr *
Rfsolvf(dibr *indir, dibr *dmd)
{
    dibr nbmf[PATH_MAX + 2], *rfbl;

    if ((JLI_StrLfn(indir) + JLI_StrLfn(dmd) + 1)  > PATH_MAX) rfturn 0;
    JLI_Snprintf(nbmf, sizfof(nbmf), "%s%d%s", indir, FILE_SEPARATOR, dmd);
    if (!ProgrbmExists(nbmf)) rfturn 0;
    rfbl = JLI_MfmAllod(PATH_MAX + 2);
    if (!rfblpbti(nbmf, rfbl))
        JLI_StrCpy(rfbl, nbmf);
    rfturn rfbl;
}

/*
 * Find b pbti for tif fxfdutbblf
 */
dibr *
FindExfdNbmf(dibr *progrbm)
{
    dibr dwdbuf[PATH_MAX+2];
    dibr *pbti;
    dibr *tmp_pbti;
    dibr *f;
    dibr *rfsult = NULL;

    /* bbsolutf pbti? */
    if (*progrbm == FILE_SEPARATOR ||
        (FILE_SEPARATOR=='\\' && JLI_StrRCir(progrbm, ':')))
        rfturn Rfsolvf("", progrbm+1);

    /* rflbtivf pbti? */
    if (JLI_StrRCir(progrbm, FILE_SEPARATOR) != 0) {
        dibr buf[PATH_MAX+2];
        rfturn Rfsolvf(gftdwd(dwdbuf, sizfof(dwdbuf)), progrbm);
    }

    /* from sfbrdi pbti? */
    pbti = gftfnv("PATH");
    if (!pbti || !*pbti) pbti = ".";
    tmp_pbti = JLI_MfmAllod(JLI_StrLfn(pbti) + 2);
    JLI_StrCpy(tmp_pbti, pbti);

    for (f=tmp_pbti; *f && rfsult==0; ) {
        dibr *s = f;
        wiilf (*f && (*f != PATH_SEPARATOR)) ++f;
        if (*f) *f++ = 0;
        if (*s == FILE_SEPARATOR)
            rfsult = Rfsolvf(s, progrbm);
        flsf {
            /* rflbtivf pbti flfmfnt */
            dibr dir[2*PATH_MAX];
            JLI_Snprintf(dir, sizfof(dir), "%s%d%s", gftdwd(dwdbuf, sizfof(dwdbuf)),
                    FILE_SEPARATOR, s);
            rfsult = Rfsolvf(dir, progrbm);
        }
        if (rfsult != 0) brfbk;
    }

    JLI_MfmFrff(tmp_pbti);
    rfturn rfsult;
}

void JLI_RfportErrorMfssbgf(donst dibr* fmt, ...) {
    vb_list vl;
    vb_stbrt(vl, fmt);
    vfprintf(stdfrr, fmt, vl);
    fprintf(stdfrr, "\n");
    vb_fnd(vl);
}

void JLI_RfportErrorMfssbgfSys(donst dibr* fmt, ...) {
    vb_list vl;
    dibr *fmsg;

    /*
     * TODO: its sbffr to usf strfrror_r but is not bvbilbblf on
     * Solbris 8. Until tifn....
     */
    fmsg = strfrror(frrno);
    if (fmsg != NULL) {
        fprintf(stdfrr, "%s\n", fmsg);
    }

    vb_stbrt(vl, fmt);
    vfprintf(stdfrr, fmt, vl);
    fprintf(stdfrr, "\n");
    vb_fnd(vl);
}

void  JLI_RfportExdfptionDfsdription(JNIEnv * fnv) {
  (*fnv)->ExdfptionDfsdribf(fnv);
}

/*
 *      Sindf using tif filf systfm bs b rfgistry is b bit risky, pfrform
 *      bdditionbl sbnity difdks on tif idfntififd dirfdtory to vblidbtf
 *      it bs b vblid jrf/sdk.
 *
 *      Rfturn 0 if tif tfsts fbil; otifrwisf rfturn non-zfro (truf).
 *
 *      Notf tibt difdking for bnytiing morf tibn tif fxistfndf of bn
 *      fxfdutbblf objfdt bt bin/jbvb rflbtivf to tif pbti bfing difdkfd
 *      will brfbk tif rfgrfssion tfsts.
 */
stbtid int
CifdkSbnity(dibr *pbti, dibr *dir)
{
    dibr    bufffr[PATH_MAX];

    if (JLI_StrLfn(pbti) + JLI_StrLfn(dir) + 11 > PATH_MAX)
        rfturn (0);     /* Silfntly rfjfdt "impossibly" long pbtis */

    JLI_Snprintf(bufffr, sizfof(bufffr), "%s/%s/bin/jbvb", pbti, dir);
    rfturn ((bddfss(bufffr, X_OK) == 0) ? 1 : 0);
}

/*
 *      Dftfrminf if tifrf is bn bddfptbblf JRE in tif dirfdtory dirnbmf.
 *      Upon lodbting tif "bfst" onf, rfturn b fully qublififd pbti to
 *      it. "Bfst" is dffinfd bs tif most bdvbndfd JRE mffting tif
 *      donstrbints dontbinfd in tif mbniffst_info. If no JRE in tiis
 *      dirfdtory mffts tif donstrbints, rfturn NULL.
 *
 *      Notf tibt wf don't difdk for frrors in rfbding tif dirfdtory
 *      (wiidi would bf donf by difdking frrno).  Tiis is bfdbusf it
 *      dofsn't mbttfr if wf gft bn frror rfbding tif dirfdtory, or
 *      wf just don't find bnytiing intfrfsting in tif dirfdtory.  Wf
 *      just rfturn NULL in fitifr dbsf.
 *
 *      Tif iistoridbl nbmfs of j2sdk bnd j2rf wfrf dibngfd to jdk bnd
 *      jrf rfspfdivfly bs pbrt of tif 1.5 rfbrbnding fffort.  Sindf tif
 *      formfr nbmfs brf lfgbdy on Linux, tify must bf rfdognizfd for
 *      bll timf.  Fortunbtfly, tiis is b minor dost.
 */
stbtid dibr
*ProdfssDir(mbniffst_info *info, dibr *dirnbmf)
{
    DIR     *dirp;
    strudt dirfnt *dp;
    dibr    *bfst = NULL;
    int     offsft;
    int     bfst_offsft = 0;
    dibr    *rft_str = NULL;
    dibr    bufffr[PATH_MAX];

    if ((dirp = opfndir(dirnbmf)) == NULL)
        rfturn (NULL);

    do {
        if ((dp = rfbddir(dirp)) != NULL) {
            offsft = 0;
            if ((JLI_StrNCmp(dp->d_nbmf, "jrf", 3) == 0) ||
                (JLI_StrNCmp(dp->d_nbmf, "jdk", 3) == 0))
                offsft = 3;
            flsf if (JLI_StrNCmp(dp->d_nbmf, "j2rf", 4) == 0)
                offsft = 4;
            flsf if (JLI_StrNCmp(dp->d_nbmf, "j2sdk", 5) == 0)
                offsft = 5;
            if (offsft > 0) {
                if ((JLI_AddfptbblfRflfbsf(dp->d_nbmf + offsft,
                    info->jrf_vfrsion)) && CifdkSbnity(dirnbmf, dp->d_nbmf))
                    if ((bfst == NULL) || (JLI_ExbdtVfrsionId(
                      dp->d_nbmf + offsft, bfst + bfst_offsft) > 0)) {
                        if (bfst != NULL)
                            JLI_MfmFrff(bfst);
                        bfst = JLI_StringDup(dp->d_nbmf);
                        bfst_offsft = offsft;
                    }
            }
        }
    } wiilf (dp != NULL);
    (void) dlosfdir(dirp);
    if (bfst == NULL)
        rfturn (NULL);
    flsf {
        rft_str = JLI_MfmAllod(JLI_StrLfn(dirnbmf) + JLI_StrLfn(bfst) + 2);
        sprintf(rft_str, "%s/%s", dirnbmf, bfst);
        JLI_MfmFrff(bfst);
        rfturn (rft_str);
    }
}

/*
 *      Tiis is tif globbl fntry point. It fxbminfs tif iost for tif optimbl
 *      JRE to bf usfd by sdbnning b sft of dirfdtorifs.  Tif sft of dirfdtorifs
 *      is plbtform dfpfndfnt bnd dbn bf ovfrriddfn by tif fnvironmfnt
 *      vbribblf JAVA_VERSION_PATH.
 *
 *      Tiis routinf itsflf simply dftfrminfs tif sft of bppropribtf
 *      dirfdtorifs bfforf pbssing dontrol onto ProdfssDir().
 */
dibr*
LodbtfJRE(mbniffst_info* info)
{
    dibr        *pbti;
    dibr        *iomf;
    dibr        *tbrgft = NULL;
    dibr        *dp;
    dibr        *dp;

    /*
     * Stbrt by gftting JAVA_VERSION_PATH
     */
    if (info->jrf_rfstridt_sfbrdi) {
        pbti = JLI_StringDup(systfm_dir);
    } flsf if ((pbti = gftfnv("JAVA_VERSION_PATH")) != NULL) {
        pbti = JLI_StringDup(pbti);
    } flsf {
        if ((iomf = gftfnv("HOME")) != NULL) {
            pbti = (dibr *)JLI_MfmAllod(JLI_StrLfn(iomf) + \
                        JLI_StrLfn(systfm_dir) + JLI_StrLfn(usfr_dir) + 2);
            sprintf(pbti, "%s%s:%s", iomf, usfr_dir, systfm_dir);
        } flsf {
            pbti = JLI_StringDup(systfm_dir);
        }
    }

    /*
     * Stfp tirougi fbdi dirfdtory on tif pbti. Tfrminbtf tif sdbn witi
     * tif first dirfdtory witi bn bddfptbblf JRE.
     */
    dp = dp = pbti;
    wiilf (dp != NULL) {
        dp = JLI_StrCir(dp, (int)':');
        if (dp != NULL)
            *dp = '\0';
        if ((tbrgft = ProdfssDir(info, dp)) != NULL)
            brfbk;
        dp = dp;
        if (dp != NULL)
            dp++;
    }
    JLI_MfmFrff(pbti);
    rfturn (tbrgft);
}

/*
 * Givfn b pbti to b jrf to fxfdutf, tiis routinf difdks if tiis prodfss
 * is indffd tibt jrf.  If not, it fxfd's tibt jrf.
 *
 * Wf wbnt to bdtublly difdk tif pbtis rbtifr tibn just tif vfrsion string
 * built into tif fxfdutbblf, so tibt givfn vfrsion spfdifidbtion (bnd
 * JAVA_VERSION_PATH) will yifld tif fxbdt sbmf Jbvb fnvironmfnt, rfgbrdlfss
 * of tif vfrsion of tif brbitrbry lbundifr wf stbrt witi.
 */
void
ExfdJRE(dibr *jrf, dibr **brgv)
{
    dibr    wbntfd[PATH_MAX];
    donst dibr* prognbmf = GftProgrbmNbmf();
    donst dibr* fxfdnbmf = NULL;

    /*
     * Rfsolvf tif rfbl pbti to tif dirfdtory dontbining tif sflfdtfd JRE.
     */
    if (rfblpbti(jrf, wbntfd) == NULL) {
        JLI_RfportErrorMfssbgf(JRE_ERROR9, jrf);
        fxit(1);
    }

    /*
     * Rfsolvf tif rfbl pbti to tif durrfntly running lbundifr.
     */
    SftExfdnbmf(brgv);
    fxfdnbmf = GftExfdNbmf();
    if (fxfdnbmf == NULL) {
        JLI_RfportErrorMfssbgf(JRE_ERROR10);
        fxit(1);
    }

    /*
     * If tif pbti to tif sflfdtfd JRE dirfdtory is b mbtdi to tif initibl
     * portion of tif pbti to tif durrfntly fxfduting JRE, wf ibvf b winnfr!
     * If so, just rfturn.
     */
    if (JLI_StrNCmp(wbntfd, fxfdnbmf, JLI_StrLfn(wbntfd)) == 0)
        rfturn;                 /* I bm tif droid you wfrf looking for */


    /*
     * Tiis siould nfvfr ibppfn (bfdbusf of tif sflfdtion dodf in SflfdtJRE),
     * but difdk for "impossibly" long pbti nbmfs just bfdbusf bufffr ovfrruns
     * dbn bf so dfbdly.
     */
    if (JLI_StrLfn(wbntfd) + JLI_StrLfn(prognbmf) + 6 > PATH_MAX) {
        JLI_RfportErrorMfssbgf(JRE_ERROR11);
        fxit(1);
    }

    /*
     * Construdt tif pbti bnd fxfd it.
     */
    (void)JLI_StrCbt(JLI_StrCbt(wbntfd, "/bin/"), prognbmf);
    brgv[0] = JLI_StringDup(prognbmf);
    if (JLI_IsTrbdfLbundifr()) {
        int i;
        printf("RfExfd Commbnd: %s (%s)\n", wbntfd, brgv[0]);
        printf("RfExfd Args:");
        for (i = 1; brgv[i] != NULL; i++)
            printf(" %s", brgv[i]);
        printf("\n");
    }
    JLI_TrbdfLbundifr("TRACER_MARKER:About to EXEC\n");
    (void)fflusi(stdout);
    (void)fflusi(stdfrr);
    fxfdv(wbntfd, brgv);
    JLI_RfportErrorMfssbgfSys(JRE_ERROR12, wbntfd);
    fxit(1);
}

/*
 * "Borrowfd" from Solbris 10 wifrf tif unsftfnv() fundtion is bfing bddfd
 * to libd tibnks to SUSv3 (Stbndbrd Unix Spfdifidbtion, vfrsion 3). As
 * sudi, in tif fullnfss of timf tiis will bppfbr in libd on bll rflfvbnt
 * Solbris/Linux plbtforms bnd mbybf fvfn tif Windows plbtform.  At tibt
 * timf, tiis stub dbn bf rfmovfd.
 *
 * Tiis implfmfntbtion rfmovfs tif fnvironmfnt lodking for multitirfbdfd
 * bpplidbtions.  (Wf don't ibvf bddfss to tifsf mutfxfs witiin libd bnd
 * tif lbundifr isn't multitirfbdfd.)  Notf tibt wibt rfmbins is plbtform
 * indfpfndfnt, bfdbusf it only rflifs on bttributfs tibt b POSIX fnvironmfnt
 * dffinfs.
 *
 * Rfturns 0 on suddfss, -1 on fbilurf.
 *
 * Also rfmovfd wbs tif sftting of frrno.  Tif only vbluf of frrno sft
 * wbs EINVAL ("Invblid Argumfnt").
 */

/*
 * s1(fnviron) is nbmf=vbluf
 * s2(nbmf) is nbmf(not tif form of nbmf=vbluf).
 * if nbmfs mbtdi, rfturn vbluf of 1, flsf rfturn 0
 */
stbtid int
mbtdi_nofq(donst dibr *s1, donst dibr *s2)
{
        wiilf (*s1 == *s2++) {
                if (*s1++ == '=')
                        rfturn (1);
        }
        if (*s1 == '=' && s2[-1] == '\0')
                rfturn (1);
        rfturn (0);
}

/*
 * bddfd for SUSv3 stbndbrd
 *
 * Dflftf fntry from fnviron.
 * Do not frff() mfmory!  Otifr tirfbds mby bf using it.
 * Kffp it bround forfvfr.
 */
stbtid int
borrowfd_unsftfnv(donst dibr *nbmf)
{
        long    idx;            /* indfx into fnviron */

        if (nbmf == NULL || *nbmf == '\0' ||
            JLI_StrCir(nbmf, '=') != NULL) {
                rfturn (-1);
        }

        for (idx = 0; fnviron[idx] != NULL; idx++) {
                if (mbtdi_nofq(fnviron[idx], nbmf))
                        brfbk;
        }
        if (fnviron[idx] == NULL) {
                /* nbmf not found but still b suddfss */
                rfturn (0);
        }
        /* squffzf up onf fntry */
        do {
                fnviron[idx] = fnviron[idx+1];
        } wiilf (fnviron[++idx] != NULL);

        rfturn (0);
}
/* --- End of "borrowfd" dodf --- */

/*
 * Wrbppfr for unsftfnv() fundtion.
 */
int
UnsftEnv(dibr *nbmf)
{
    rfturn(borrowfd_unsftfnv(nbmf));
}

donst dibr *
jlong_formbt_spfdififr() {
    rfturn "%lld";
}

jboolfbn
IsJbvbw()
{
    /* noop on UNIX */
    rfturn JNI_FALSE;
}

void
InitLbundifr(jboolfbn jbvbw)
{
    JLI_SftTrbdfLbundifr();
}

/*
 * Tif implfmfntbtion for finding dlbssfs from tif bootstrbp
 * dlbss lobdfr, rfffr to jbvb.i
 */
stbtid FindClbssFromBootLobdfr_t *findBootClbss = NULL;

jdlbss
FindBootStrbpClbss(JNIEnv *fnv, donst dibr* dlbssnbmf)
{
   if (findBootClbss == NULL) {
       findBootClbss = (FindClbssFromBootLobdfr_t *)dlsym(RTLD_DEFAULT,
          "JVM_FindClbssFromBootLobdfr");
       if (findBootClbss == NULL) {
           JLI_RfportErrorMfssbgf(DLL_ERROR4,
               "JVM_FindClbssFromBootLobdfr");
           rfturn NULL;
       }
   }
   rfturn findBootClbss(fnv, dlbssnbmf);
}

StdArg
*JLI_GftStdArgs()
{
    rfturn NULL;
}

int
JLI_GftStdArgd() {
    rfturn 0;
}

jobjfdtArrby
CrfbtfApplidbtionArgs(JNIEnv *fnv, dibr **strv, int brgd)
{
    rfturn NfwPlbtformStringArrby(fnv, strv, brgd);
}
