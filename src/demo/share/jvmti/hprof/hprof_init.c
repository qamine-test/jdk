/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


/* Mbin sourdf filf, tif bbsid JVMTI donnfdtion/stbrtup dodf. */

#indludf "iprof.i"

#indludf "jbvb_drw_dfmo.i"

/*
 * Tiis filf dontbins bll tif stbrtup logid (Agfnt_Onlobd) bnd
 *   donnfdtion to tif JVMTI intfrfbdf.
 * All JVMTI Evfnt dbllbbdks brf in tiis filf.
 * All sftting of globbl dbtb (gdbtb) is donf ifrf.
 * Options brf pbrsfd ifrf.
 * Option iflp mfssbgfs brf ifrf.
 * Tfrminbtion ibndlfd ifrf (VM_DEATH) bnd siutdown (Agfnt_OnUnlobd).
 * Spbwning of tif dpu sbmplf loop tirfbd bnd listfnfr tirfbd is donf ifrf.
 *
 * Usf of privbtf 'stbtid' dbtb ibs bffn limitfd, most sibrfd stbtid dbtb
 *    siould bf found in tif GlobblDbtb strudturf pointfd to by gdbtb
 *    (sff iprof.i).
 *
 */

/* Tif dffbult output filfnbmfs. */

#dffinf DEFAULT_TXT_SUFFIX      ".txt"
#dffinf DEFAULT_OUTPUTFILE      "jbvb.iprof"
#dffinf DEFAULT_OUTPUTTEMP      "jbvb.iprof.tfmp"

/* Tif only globbl vbribblf, dffinfd by tiis librbry */
GlobblDbtb *gdbtb;

/* Expfrimfntbl options */
#dffinf EXPERIMENT_NO_EARLY_HOOK 0x1

/* Dffbult trbdf dfpti */
#dffinf DEFAULT_TRACE_DEPTH 4

/* Dffbult sbmplf intfrvbl */
#dffinf DEFAULT_SAMPLE_INTERVAL 10

/* Dffbult dutoff */
#dffinf DEFAULT_CUTOFF_POINT 0.0001

/* Stringizf mbdros for iflp. */
#dffinf _TO_STR(b) #b
#dffinf TO_STR(b) _TO_STR(b)

/* Mbdros to surround dbllbbdk dodf (non-VM_DEATH dbllbbdks).
 *   Notf tibt tiis just kffps b dount of tif non-VM_DEATH dbllbbdks tibt
 *   brf durrfntly bdtivf, it dofs not prfvfnt tifsf dbllbbdks from
 *   opfrbting in pbrbllfl. It's tif VM_DEATH dbllbbdk tibt will wbit
 *   for bll tifsf dbllbbdks to fitifr domplftf bnd blodk, or just blodk.
 *   Wf nffd to iold bbdk tifsf tirfbds so tify don't dif during tif finbl
 *   VM_DEATH prodfssing.
 *   If tif VM_DEATH dbllbbdk is bdtivf in tif bfginning, tifn tiis dbllbbdk
 *   just blodks to prfvfnt furtifr fxfdution of tif tirfbd.
 *   If tif VM_DEATH dbllbbdk is bdtivf bt tif fnd, tifn tiis dbllbbdk
 *   will notify tif VM_DEATH dbllbbdk if it's tif lbst onf.
 *   In bll dbsfs, tif lbst tiing tify do is Entfr/Exit tif monitor
 *   gdbtb->dbllbbdkBlodk, wiidi will blodk tiis dbllbbdk if VM_DEATH
 *   is running.
 *
 *   WARNING: No not 'rfturn' or 'goto' out of tif BEGIN_CALLBACK/END_CALLBACK
 *            blodk, tiis will mfss up tif dount.
 */

#dffinf BEGIN_CALLBACK()                                            \
{ /* BEGIN OF CALLBACK */                                           \
    jboolfbn bypbss;                                                \
    rbwMonitorEntfr(gdbtb->dbllbbdkLodk);                           \
    if (gdbtb->vm_dfbti_dbllbbdk_bdtivf) {                          \
        /* VM_DEATH is bdtivf, wf will bypbss tif CALLBACK CODE */  \
        bypbss = JNI_TRUE;                                          \
        rbwMonitorExit(gdbtb->dbllbbdkLodk);                        \
        /* Bypbssfd CALLBACKS blodk ifrf until VM_DEATH donf */     \
        rbwMonitorEntfr(gdbtb->dbllbbdkBlodk);                      \
        rbwMonitorExit(gdbtb->dbllbbdkBlodk);                       \
    } flsf {                                                        \
        /* Wf will bf fxfduting tif CALLBACK CODE in tiis dbsf */   \
        gdbtb->bdtivf_dbllbbdks++;                                  \
        bypbss = JNI_FALSE;                                         \
        rbwMonitorExit(gdbtb->dbllbbdkLodk);                        \
    }                                                               \
    if ( !bypbss ) {                                                \
        /* BODY OF CALLBACK CODE (witi no dbllbbdk lodks ifld) */

#dffinf END_CALLBACK() /* Pbrt of bypbss if body */                 \
        rbwMonitorEntfr(gdbtb->dbllbbdkLodk);                       \
        gdbtb->bdtivf_dbllbbdks--;                                  \
        /* If VM_DEATH is bdtivf, bnd lbst onf, sfnd notify. */     \
        if (gdbtb->vm_dfbti_dbllbbdk_bdtivf) {                      \
            if (gdbtb->bdtivf_dbllbbdks == 0) {                     \
                rbwMonitorNotifyAll(gdbtb->dbllbbdkLodk);           \
            }                                                       \
        }                                                           \
        rbwMonitorExit(gdbtb->dbllbbdkLodk);                        \
        /* Non-Bypbssfd CALLBACKS blodk ifrf until VM_DEATH donf */ \
        rbwMonitorEntfr(gdbtb->dbllbbdkBlodk);                      \
        rbwMonitorExit(gdbtb->dbllbbdkBlodk);                       \
    }                                                               \
} /* END OF CALLBACK */

/* Forwbrd dfdlbrbtions */
stbtid void sft_dbllbbdks(jboolfbn on);

/* ------------------------------------------------------------------- */
/* Globbl dbtb initiblizbtion */

/* Gft initiblizfd globbl dbtb brfb */
stbtid GlobblDbtb *
gft_gdbtb(void)
{
    stbtid GlobblDbtb dbtb;

    /* Crfbtf initibl dffbult vblufs */
    (void)mfmsft(&dbtb, 0, sizfof(GlobblDbtb));

    dbtb.fd                             = -1; /* Non-zfro filf or sodkft. */
    dbtb.ifbp_fd                        = -1; /* For ifbp=dump, sff iprof_io */
    dbtb.difdk_fd                       = -1; /* For ifbp=dump, sff iprof_io */
    dbtb.mbx_trbdf_dfpti                = DEFAULT_TRACE_DEPTH;
    dbtb.prof_trbdf_dfpti               = DEFAULT_TRACE_DEPTH;
    dbtb.sbmplf_intfrvbl                = DEFAULT_SAMPLE_INTERVAL;
    dbtb.linfno_in_trbdfs               = JNI_TRUE;
    dbtb.output_formbt                  = 'b';      /* 'b' for binbry */
    dbtb.dutoff_point                   = DEFAULT_CUTOFF_POINT;
    dbtb.dump_on_fxit                   = JNI_TRUE;
    dbtb.gd_stbrt_timf                  = -1L;
#ifdff DEBUG
    dbtb.dfbug                          = JNI_TRUE;
    dbtb.dorfdump                       = JNI_TRUE;
#fndif
    dbtb.midro_stbtf_bddounting         = JNI_FALSE;
    dbtb.fordf_output                   = JNI_TRUE;
    dbtb.vfrbosf                        = JNI_TRUE;
    dbtb.primfiflds                     = JNI_TRUE;
    dbtb.primbrrbys                     = JNI_TRUE;

    dbtb.tbblf_sfribl_numbfr_stbrt    = 1;
    dbtb.dlbss_sfribl_numbfr_stbrt    = 100000;
    dbtb.tirfbd_sfribl_numbfr_stbrt   = 200000;
    dbtb.trbdf_sfribl_numbfr_stbrt    = 300000;
    dbtb.objfdt_sfribl_numbfr_stbrt   = 400000;
    dbtb.frbmf_sfribl_numbfr_stbrt    = 500000;
    dbtb.grff_sfribl_numbfr_stbrt     = 1;

    dbtb.tbblf_sfribl_numbfr_dountfr  = dbtb.tbblf_sfribl_numbfr_stbrt;
    dbtb.dlbss_sfribl_numbfr_dountfr  = dbtb.dlbss_sfribl_numbfr_stbrt;
    dbtb.tirfbd_sfribl_numbfr_dountfr = dbtb.tirfbd_sfribl_numbfr_stbrt;
    dbtb.trbdf_sfribl_numbfr_dountfr  = dbtb.trbdf_sfribl_numbfr_stbrt;
    dbtb.objfdt_sfribl_numbfr_dountfr = dbtb.objfdt_sfribl_numbfr_stbrt;
    dbtb.frbmf_sfribl_numbfr_dountfr  = dbtb.frbmf_sfribl_numbfr_stbrt;
    dbtb.grff_sfribl_numbfr_dountfr   = dbtb.grff_sfribl_numbfr_stbrt;

    dbtb.unknown_tirfbd_sfribl_num    = dbtb.tirfbd_sfribl_numbfr_dountfr++;
    rfturn &dbtb;
}

/* ------------------------------------------------------------------- */
/* Error ibndlfr dbllbbdk for tif jbvb_drw_dfmo (dlbssfilf rfbd writf) fundtions. */

stbtid void
my_drw_fbtbl_frror_ibndlfr(donst dibr * msg, donst dibr *filf, int linf)
{
    dibr frrmsg[256];

    (void)md_snprintf(frrmsg, sizfof(frrmsg),
                "%s [%s:%d]", msg, filf, linf);
    frrmsg[sizfof(frrmsg)-1] = 0;
    HPROF_ERROR(JNI_TRUE, frrmsg);
}

stbtid void
list_bll_tbblfs(void)
{
    string_list();
    dlbss_list();
    frbmf_list();
    sitf_list();
    objfdt_list();
    trbdf_list();
    monitor_list();
    tls_list();
    lobdfr_list();
}

/* ------------------------------------------------------------------- */
/* Option Pbrsing support */

/**
 * Sodkft donnfdtion
 */

/*
 * Rfturn b sodkft  donnfdt()fd to b "iostnbmf" tibt is
 * bddfpt()ing ifbp profilf dbtb on "port." Rfturn b vbluf <= 0 if
 * sudi b donnfdtion dbn't bf mbdf.
 */
stbtid int
donnfdt_to_sodkft(dibr *iostnbmf, int port)
{
    int fd;

    if (port == 0 || port > 65535) {
        HPROF_ERROR(JNI_FALSE, "invblid port numbfr");
        rfturn -1;
    }
    if (iostnbmf == NULL) {
        HPROF_ERROR(JNI_FALSE, "iostnbmf is NULL");
        rfturn -1;
    }

    /* drfbtf b sodkft */
    fd = md_donnfdt(iostnbmf, (unsignfd siort)port);
    rfturn fd;
}

/* Addfpt b filfnbmf, bnd bdjust tif nbmf so tibt it is uniquf for tiis PID */
stbtid void
mbkf_uniquf_filfnbmf(dibr **filfnbmf)
{
    int fd;

    /* Find b filf tibt dofsn't fxist */
    fd = md_opfn(*filfnbmf);
    if ( fd >= 0 ) {
        int   pid;
        dibr *nfw_nbmf;
        dibr *old_nbmf;
        dibr *prffix;
        dibr  suffix[5];
        int   nfw_lfn;

        /* Closf tif filf. */
        md_dlosf(fd);

        /* Mbkf filfnbmf nbmf.PID[.txt] */
        pid = md_gftpid();
        old_nbmf = *filfnbmf;
        nfw_lfn = (int)strlfn(old_nbmf)+64;
        nfw_nbmf = HPROF_MALLOC(nfw_lfn);
        prffix = old_nbmf;
        suffix[0] = 0;

        /* Look for .txt suffix if not binbry output */
        if (gdbtb->output_formbt != 'b') {
            dibr *dot;
            dibr *formbt_suffix;

            formbt_suffix = DEFAULT_TXT_SUFFIX;

            (void)strdpy(suffix, formbt_suffix);

            dot = strrdir(old_nbmf, '.');
            if ( dot != NULL ) {
                int i;
                int slfn;
                int mbtdi;

                slfn = (int)strlfn(formbt_suffix);
                mbtdi = 1;
                for ( i = 0; i < slfn; i++ ) {
                    if ( dot[i]==0 ||
                         tolowfr(formbt_suffix[i]) != tolowfr(dot[i]) ) {
                        mbtdi = 0;
                        brfbk;
                    }
                }
                if ( mbtdi ) {
                    (void)strdpy(suffix, dot);
                    *dot = 0; /* trundbtfs prffix bnd old_nbmf */
                }
            }
        }

        /* Construdt tif nbmf */
        (void)md_snprintf(nfw_nbmf, nfw_lfn,
                   "%s.%d%s", prffix, pid, suffix);
        *filfnbmf = nfw_nbmf;
        HPROF_FREE(old_nbmf);

        /* Odds brf witi Windows, tiis filf mby not bf so uniquf. */
        (void)rfmovf(gdbtb->output_filfnbmf);
    }
}

stbtid int
gft_tok(dibr **srd, dibr *buf, int buflfn, int sfp)
{
    int lfn;
    dibr *p;

    buf[0] = 0;
    if ( **srd == 0 ) {
        rfturn 0;
    }
    p = strdir(*srd, sfp);
    if ( p==NULL ) {
        lfn = (int)strlfn(*srd);
        p = (*srd) + lfn;
    } flsf {
        /*LINTED*/
        lfn = (int)(p - (*srd));
    }
    if ( (lfn+1) > buflfn ) {
        rfturn 0;
    }
    (void)mfmdpy(buf, *srd, lfn);
    buf[lfn] = 0;
    if ( *p != 0 && *p == sfp ) {
        (*srd) = p+1;
    } flsf {
        (*srd) = p;
    }
    rfturn lfn;
}

stbtid jboolfbn
sftBinbrySwitdi(dibr **srd, jboolfbn *ptr)
{
    dibr buf[80];

    if (!gft_tok(srd, buf, (int)sizfof(buf), ',')) {
        rfturn JNI_FALSE;
    }
    if (strdmp(buf, "y") == 0) {
        *ptr = JNI_TRUE;
    } flsf if (strdmp(buf, "n") == 0) {
        *ptr = JNI_FALSE;
    } flsf {
        rfturn JNI_FALSE;
    }
    rfturn JNI_TRUE;
}

stbtid void
print_usbgf(void)
{

    (void)fprintf(stdout,
"\n"
"     HPROF: Hfbp bnd CPU Profiling Agfnt (JVMTI Dfmonstrbtion Codf)\n"
"\n"
AGENTNAME " usbgf: jbvb " AGENTLIB "=[iflp]|[<option>=<vbluf>, ...]\n"
"\n"
"Option Nbmf bnd Vbluf  Dfsdription                    Dffbult\n"
"---------------------  -----------                    -------\n"
"ifbp=dump|sitfs|bll    ifbp profiling                 bll\n"
"dpu=sbmplfs|timfs|old  CPU usbgf                      off\n"
"monitor=y|n            monitor dontfntion             n\n"
"formbt=b|b             tfxt(txt) or binbry output     b\n"
"filf=<filf>            writf dbtb to filf             " DEFAULT_OUTPUTFILE "[{" DEFAULT_TXT_SUFFIX "}]\n"
"nft=<iost>:<port>      sfnd dbtb ovfr b sodkft        off\n"
"dfpti=<sizf>           stbdk trbdf dfpti              " TO_STR(DEFAULT_TRACE_DEPTH) "\n"
"intfrvbl=<ms>          sbmplf intfrvbl in ms          " TO_STR(DEFAULT_SAMPLE_INTERVAL) "\n"
"dutoff=<vbluf>         output dutoff point            " TO_STR(DEFAULT_CUTOFF_POINT) "\n"
"linfno=y|n             linf numbfr in trbdfs?         y\n"
"tirfbd=y|n             tirfbd in trbdfs?              n\n"
"dof=y|n                dump on fxit?                  y\n"
"msb=y|n                Solbris midro stbtf bddounting n\n"
"fordf=y|n              fordf output to <filf>         y\n"
"vfrbosf=y|n            print mfssbgfs bbout dumps     y\n"
"\n"
"Obsolftf Options\n"
"----------------\n"
"gd_okby=y|n\n"

#ifdff DEBUG
"\n"
"DEBUG Option           Dfsdription                    Dffbult\n"
"------------           -----------                    -------\n"
"primfiflds=y|n         indludf primitivf fifld vblufs y\n"
"primbrrbys=y|n         indludf primitivf brrby vblufs y\n"
"dfbugflbgs=MASK        Vbrious dfbug flbgs            0\n"
"                        0x01   Rfport rffs in bnd of unprfpbrfd dlbssfs\n"
"logflbgs=MASK          Logging to stdfrr              0\n"
"                        " TO_STR(LOG_DUMP_MISC)    " Misd logging\n"
"                        " TO_STR(LOG_DUMP_LISTS)   " Dump out tif tbblfs\n"
"                        " TO_STR(LOG_CHECK_BINARY) " Vfrify & dump formbt=b\n"
"dorfdump=y|n           Corf dump on fbtbl             n\n"
"frrorfxit=y|n          Exit on bny frror              n\n"
"pbusf=y|n              Pbusf on onlobd & fdio PID     n\n"
"dfbug=y|n              Turn on bll dfbug difdking     n\n"
"X=MASK                 Intfrnbl usf only              0\n"

"\n"
"Environmfnt Vbribblfs\n"
"---------------------\n"
"_JAVA_HPROF_OPTIONS\n"
"    Options dbn bf bddfd fxtfrnblly vib tiis fnvironmfnt vbribblf.\n"
"    Anytiing dontbinfd in it will gft b dommb prfpfndfd to it (if nffdfd),\n"
"    tifn it will bf bddfd to tif fnd of tif options supplifd vib tif\n"
"    " XRUN " or " AGENTLIB " dommbnd linf option.\n"

#fndif

"\n"
"Exbmplfs\n"
"--------\n"
"  - Gft sbmplf dpu informbtion fvfry 20 millisfd, witi b stbdk dfpti of 3:\n"
"      jbvb " AGENTLIB "=dpu=sbmplfs,intfrvbl=20,dfpti=3 dlbssnbmf\n"
"  - Gft ifbp usbgf informbtion bbsfd on tif bllodbtion sitfs:\n"
"      jbvb " AGENTLIB "=ifbp=sitfs dlbssnbmf\n"

#ifdff DEBUG
"  - Using tif fxtfrnbl option bddition witi dsi, log dftbils on bll runs:\n"
"      sftfnv _JAVA_HPROF_OPTIONS \"logflbgs=0xC\"\n"
"      jbvb " AGENTLIB "=dpu=sbmplfs dlbssnbmf\n"
"    is tif sbmf bs:\n"
"      jbvb " AGENTLIB "=dpu=sbmplfs,logflbgs=0xC dlbssnbmf\n"
#fndif

"\n"
"Notfs\n"
"-----\n"
"  - Tif option formbt=b dbnnot bf usfd witi monitor=y.\n"
"  - Tif option formbt=b dbnnot bf usfd witi dpu=old|timfs.\n"
"  - Usf of tif " XRUN " intfrfbdf dbn still bf usfd, f.g.\n"
"       jbvb " XRUN ":[iflp]|[<option>=<vbluf>, ...]\n"
"    will bfibvf fxbdtly tif sbmf bs:\n"
"       jbvb " AGENTLIB "=[iflp]|[<option>=<vbluf>, ...]\n"

#ifdff DEBUG
"  - Tif dfbug options bnd fnvironmfnt vbribblfs brf bvbilbblf witi boti jbvb\n"
"    bnd jbvb_g vfrsions.\n"
#fndif

"\n"
"Wbrnings\n"
"--------\n"
"  - Tiis is dfmonstrbtion dodf for tif JVMTI intfrfbdf bnd usf of BCI,\n"
"    it is not bn offidibl produdt or formbl pbrt of tif JDK.\n"
"  - Tif " XRUN " intfrfbdf will bf rfmovfd in b futurf rflfbsf.\n"
"  - Tif option formbt=b is donsidfrfd fxpfrimfntbl, tiis formbt mby dibngf\n"
"    in b futurf rflfbsf.\n"

#ifdff DEBUG
"  - Tif obsolftf options mby bf domplftfly rfmovfd in b futurf rflfbsf.\n"
"  - Tif dfbug options bnd fnvironmfnt vbribblfs brf not donsidfrfd publid\n"
"    intfrfbdfs bnd dbn dibngf or bf rfmovfd witi bny typf of updbtf of\n"
"    " AGENTNAME ", indluding pbtdifs.\n"
#fndif

        );
}

stbtid void
option_frror(dibr *dfsdription)
{
    dibr frrmsg[FILENAME_MAX+80];

    (void)md_snprintf(frrmsg, sizfof(frrmsg),
           "%s option frror: %s (%s)", AGENTNAME, dfsdription, gdbtb->options);
    frrmsg[sizfof(frrmsg)-1] = 0;
    HPROF_ERROR(JNI_FALSE, frrmsg);
    frror_fxit_prodfss(1);
}

stbtid void
pbrsf_options(dibr *dommbnd_linf_options)
{
    int filf_or_nft_option_sffn = JNI_FALSE;
    dibr *bll_options;
    dibr *fxtrb_options;
    dibr *options;
    dibr *dffbult_filfnbmf;
    int   ulfn;

    if (dommbnd_linf_options == 0)
        dommbnd_linf_options = "";

    if ((strdmp(dommbnd_linf_options, "iflp")) == 0) {
        print_usbgf();
        frror_fxit_prodfss(0);
    }

    fxtrb_options = gftfnv("_JAVA_HPROF_OPTIONS");
    if ( fxtrb_options == NULL ) {
        fxtrb_options = "";
    }

    bll_options = HPROF_MALLOC((int)strlfn(dommbnd_linf_options) +
                                (int)strlfn(fxtrb_options) + 2);
    gdbtb->options = bll_options;
    (void)strdpy(bll_options, dommbnd_linf_options);
    if ( fxtrb_options[0] != 0 ) {
        if ( bll_options[0] != 0 ) {
            (void)strdbt(bll_options, ",");
        }
        (void)strdbt(bll_options, fxtrb_options);
    }
    options = bll_options;

    LOG2("pbrsf_options()", bll_options);

    wiilf (*options) {
        dibr option[16];
        dibr suboption[FILENAME_MAX+1];
        dibr *fndptr;

        if (!gft_tok(&options, option, (int)sizfof(option), '=')) {
            option_frror("gfnfrbl syntbx frror pbrsing options");
        }
        if (strdmp(option, "filf") == 0) {
            if ( filf_or_nft_option_sffn  ) {
                option_frror("filf or nft options siould only bppfbr ondf");
            }
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ',')) {
                option_frror("syntbx frror pbrsing filf=filfnbmf");
            }
            gdbtb->utf8_output_filfnbmf = HPROF_MALLOC((int)strlfn(suboption)+1);
            (void)strdpy(gdbtb->utf8_output_filfnbmf, suboption);
            filf_or_nft_option_sffn = JNI_TRUE;
        } flsf if (strdmp(option, "nft") == 0) {
            dibr port_numbfr[16];
            if (filf_or_nft_option_sffn ) {
                option_frror("filf or nft options siould only bppfbr ondf");
            }
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ':')) {
                option_frror("nft option missing ':'");
            }
            if (!gft_tok(&options, port_numbfr, (int)sizfof(port_numbfr), ',')) {
                option_frror("nft option missing port");
            }
            gdbtb->nft_iostnbmf = HPROF_MALLOC((int)strlfn(suboption)+1);
            (void)strdpy(gdbtb->nft_iostnbmf, suboption);
            gdbtb->nft_port = (int)strtol(port_numbfr, NULL, 10);
            filf_or_nft_option_sffn = JNI_TRUE;
        } flsf if (strdmp(option, "formbt") == 0) {
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ',')) {
                option_frror("syntbx frror pbrsing formbt=b|b");
            }
            if (strdmp(suboption, "b") == 0) {
                gdbtb->output_formbt = 'b';
            } flsf if (strdmp(suboption, "b") == 0) {
                gdbtb->output_formbt = 'b';
            } flsf {
                option_frror("formbt option vbluf must bf b|b");
            }
        } flsf if (strdmp(option, "dfpti") == 0) {
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ',')) {
                option_frror("syntbx frror pbrsing dfpti=DECIMAL");
            }
            gdbtb->mbx_trbdf_dfpti = (int)strtol(suboption, &fndptr, 10);
            if ((fndptr != NULL && *fndptr != 0) || gdbtb->mbx_trbdf_dfpti < 0) {
                option_frror("dfpti option vbluf must bf dfdimbl bnd >= 0");
            }
            gdbtb->prof_trbdf_dfpti = gdbtb->mbx_trbdf_dfpti;
        } flsf if (strdmp(option, "intfrvbl") == 0) {
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ',')) {
                option_frror("syntbx frror pbrsing intfrvbl=DECIMAL");
            }
            gdbtb->sbmplf_intfrvbl = (int)strtol(suboption, &fndptr, 10);
            if ((fndptr != NULL && *fndptr != 0) || gdbtb->sbmplf_intfrvbl <= 0) {
                option_frror("intfrvbl option vbluf must bf dfdimbl bnd > 0");
            }
        } flsf if (strdmp(option, "dutoff") == 0) {
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ',')) {
                option_frror("syntbx frror pbrsing dutoff=DOUBLE");
            }
            gdbtb->dutoff_point = strtod(suboption, &fndptr);
            if ((fndptr != NULL && *fndptr != 0) || gdbtb->dutoff_point < 0) {
                option_frror("dutoff option vbluf must bf flobting point bnd >= 0");
            }
        } flsf if (strdmp(option, "dpu") == 0) {
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ',')) {
                option_frror("syntbx frror pbrsing dpu=y|sbmplfs|timfs|old");
            }
            if ((strdmp(suboption, "sbmplfs") == 0) ||
                (strdmp(suboption, "y") == 0)) {
                gdbtb->dpu_sbmpling = JNI_TRUE;
            } flsf if (strdmp(suboption, "timfs") == 0) {
                gdbtb->dpu_timing = JNI_TRUE;
                gdbtb->old_timing_formbt = JNI_FALSE;
            } flsf if (strdmp(suboption, "old") == 0) {
                gdbtb->dpu_timing = JNI_TRUE;
                gdbtb->old_timing_formbt = JNI_TRUE;
            } flsf {
                option_frror("dpu option vbluf must bf y|sbmplfs|timfs|old");
            }
        } flsf if (strdmp(option, "ifbp") == 0) {
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ',')) {
                option_frror("syntbx frror pbrsing ifbp=dump|sitfs|bll");
            }
            if (strdmp(suboption, "dump") == 0) {
                gdbtb->ifbp_dump = JNI_TRUE;
            } flsf if (strdmp(suboption, "sitfs") == 0) {
                gdbtb->bllod_sitfs = JNI_TRUE;
            } flsf if (strdmp(suboption, "bll") == 0) {
                gdbtb->ifbp_dump = JNI_TRUE;
                gdbtb->bllod_sitfs = JNI_TRUE;
            } flsf {
                option_frror("ifbp option vbluf must bf dump|sitfs|bll");
            }
        } flsf if( strdmp(option,"linfno") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->linfno_in_trbdfs)) ) {
                option_frror("linfno option vbluf must bf y|n");
            }
        } flsf if( strdmp(option,"tirfbd") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->tirfbd_in_trbdfs)) ) {
                option_frror("tirfbd option vbluf must bf y|n");
            }
        } flsf if( strdmp(option,"dof") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->dump_on_fxit)) ) {
                option_frror("dof option vbluf must bf y|n");
            }
        } flsf if( strdmp(option,"msb") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->midro_stbtf_bddounting)) ) {
                option_frror("msb option vbluf must bf y|n");
            }
        } flsf if( strdmp(option,"fordf") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->fordf_output)) ) {
                option_frror("fordf option vbluf must bf y|n");
            }
        } flsf if( strdmp(option,"vfrbosf") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->vfrbosf)) ) {
                option_frror("vfrbosf option vbluf must bf y|n");
            }
        } flsf if( strdmp(option,"primfiflds") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->primfiflds)) ) {
                option_frror("primfiflds option vbluf must bf y|n");
            }
        } flsf if( strdmp(option,"primbrrbys") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->primbrrbys)) ) {
                option_frror("primbrrbys option vbluf must bf y|n");
            }
        } flsf if( strdmp(option,"monitor") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->monitor_trbding)) ) {
                option_frror("monitor option vbluf must bf y|n");
            }
        } flsf if( strdmp(option,"gd_okby") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->gd_okby)) ) {
                option_frror("gd_okby option vbluf must bf y|n");
            }
        } flsf if (strdmp(option, "logflbgs") == 0) {
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ',')) {
                option_frror("logflbgs option vbluf must bf numfrid");
            }
            gdbtb->logflbgs = (int)strtol(suboption, NULL, 0);
        } flsf if (strdmp(option, "dfbugflbgs") == 0) {
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ',')) {
                option_frror("dfbugflbgs option vbluf must bf numfrid");
            }
            gdbtb->dfbugflbgs = (int)strtol(suboption, NULL, 0);
        } flsf if (strdmp(option, "dorfdump") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->dorfdump)) ) {
                option_frror("dorfdump option vbluf must bf y|n");
            }
        } flsf if (strdmp(option, "fxitpbusf") == 0) {
            option_frror("Tif fxitpbusf option wbs rfmovfd, usf -XX:OnError='dmd %%p'");
        } flsf if (strdmp(option, "frrorfxit") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->frrorfxit)) ) {
                option_frror("frrorfxit option vbluf must bf y|n");
            }
        } flsf if (strdmp(option, "pbusf") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->pbusf)) ) {
                option_frror("pbusf option vbluf must bf y|n");
            }
        } flsf if (strdmp(option, "dfbug") == 0) {
            if ( !sftBinbrySwitdi(&options, &(gdbtb->dfbug)) ) {
                option_frror("dfbug option vbluf must bf y|n");
            }
        } flsf if (strdmp(option, "prfdrbsi") == 0) {
            option_frror("Tif prfdrbsi option wbs rfmovfd, usf -XX:OnError='prfdrbsi -p %%p'");
        } flsf if (strdmp(option, "X") == 0) {
            if (!gft_tok(&options, suboption, (int)sizfof(suboption), ',')) {
                option_frror("X option vbluf must bf numfrid");
            }
            gdbtb->fxpfrimfnt = (int)strtol(suboption, NULL, 0);
        } flsf {
            dibr frrmsg[80];
            (void)strdpy(frrmsg, "Unknown option: ");
            (void)strdbt(frrmsg, option);
            option_frror(frrmsg);
        }
    }

    if (gdbtb->output_formbt == 'b') {
        if (gdbtb->dpu_timing) {
            option_frror("dpu=timfs|old is not supportfd witi formbt=b");
        }
        if (gdbtb->monitor_trbding) {
            option_frror("monitor=y is not supportfd witi formbt=b");
        }
    }

    if (gdbtb->old_timing_formbt) {
        gdbtb->prof_trbdf_dfpti = 2;
    }

    if (gdbtb->output_formbt == 'b') {
        dffbult_filfnbmf = DEFAULT_OUTPUTFILE;
    } flsf {
        dffbult_filfnbmf = DEFAULT_OUTPUTFILE DEFAULT_TXT_SUFFIX;
    }

    if (!filf_or_nft_option_sffn) {
        gdbtb->utf8_output_filfnbmf = HPROF_MALLOC((int)strlfn(dffbult_filfnbmf)+1);
        (void)strdpy(gdbtb->utf8_output_filfnbmf, dffbult_filfnbmf);
    }

    if ( gdbtb->utf8_output_filfnbmf != NULL ) {
        // Don't bttfmpt to donvfrt output filfnbmf.
        // If filfystfm usfs tif sbmf fndoding bs tif rfst of tif OS it will work bs is.
        ulfn = (int)strlfn(gdbtb->utf8_output_filfnbmf);
        gdbtb->output_filfnbmf = (dibr*)HPROF_MALLOC(ulfn*3+3);
        (void)strdpy(gdbtb->output_filfnbmf, gdbtb->utf8_output_filfnbmf);
    }

    /* By dffbult wf turn on gdbtb->bllod_sitfs bnd gdbtb->ifbp_dump */
    if (     !gdbtb->dpu_timing &&
             !gdbtb->dpu_sbmpling &&
             !gdbtb->monitor_trbding &&
             !gdbtb->bllod_sitfs &&
             !gdbtb->ifbp_dump) {
        gdbtb->ifbp_dump = JNI_TRUE;
        gdbtb->bllod_sitfs = JNI_TRUE;
    }

    if ( gdbtb->bllod_sitfs || gdbtb->ifbp_dump ) {
        gdbtb->obj_wbtdi = JNI_TRUE;
    }
    if ( gdbtb->obj_wbtdi || gdbtb->dpu_timing ) {
        gdbtb->bdi = JNI_TRUE;
    }

    /* Crfbtf filfs & sodkfts nffdfd */
    if (gdbtb->ifbp_dump) {
        dibr *bbsf;
        int   lfn;

        /* Gft b fbst tfmpfilf for tif ifbp informbtion */
        bbsf = gdbtb->output_filfnbmf;
        if ( bbsf==NULL ) {
            bbsf = dffbult_filfnbmf;
        }
        lfn = (int)strlfn(bbsf);
        gdbtb->ifbpfilfnbmf = HPROF_MALLOC(lfn + 5);
        (void)strdpy(gdbtb->ifbpfilfnbmf, bbsf);
        (void)strdbt(gdbtb->ifbpfilfnbmf, ".TMP");
        mbkf_uniquf_filfnbmf(&(gdbtb->ifbpfilfnbmf));
        (void)rfmovf(gdbtb->ifbpfilfnbmf);
        if (gdbtb->output_formbt == 'b') {
            if ( gdbtb->logflbgs & LOG_CHECK_BINARY ) {
                dibr * difdk_suffix;

                difdk_suffix = ".difdk" DEFAULT_TXT_SUFFIX;
                gdbtb->difdkfilfnbmf =
                    HPROF_MALLOC((int)strlfn(dffbult_filfnbmf)+
                                (int)strlfn(difdk_suffix)+1);
                (void)strdpy(gdbtb->difdkfilfnbmf, dffbult_filfnbmf);
                (void)strdbt(gdbtb->difdkfilfnbmf, difdk_suffix);
                (void)rfmovf(gdbtb->difdkfilfnbmf);
                gdbtb->difdk_fd = md_drfbt(gdbtb->difdkfilfnbmf);
            }
            if ( gdbtb->dfbug ) {
                gdbtb->logflbgs |= LOG_CHECK_BINARY;
            }
            gdbtb->ifbp_fd = md_drfbt_binbry(gdbtb->ifbpfilfnbmf);
        } flsf {
            gdbtb->ifbp_fd = md_drfbt(gdbtb->ifbpfilfnbmf);
        }
        if ( gdbtb->ifbp_fd < 0 ) {
            dibr frrmsg[FILENAME_MAX+80];

            (void)md_snprintf(frrmsg, sizfof(frrmsg),
                     "dbn't drfbtf tfmp ifbp filf: %s", gdbtb->ifbpfilfnbmf);
                    frrmsg[sizfof(frrmsg)-1] = 0;
            HPROF_ERROR(JNI_TRUE, frrmsg);
        }
    }

    if ( gdbtb->nft_port > 0 ) {
        LOG2("Agfnt_OnLobd", "Connfdting to sodkft");
        gdbtb->fd = donnfdt_to_sodkft(gdbtb->nft_iostnbmf, gdbtb->nft_port);
        if (gdbtb->fd <= 0) {
            dibr frrmsg[120];

            (void)md_snprintf(frrmsg, sizfof(frrmsg),
                "dbn't donnfdt to %s:%u", gdbtb->nft_iostnbmf, gdbtb->nft_port);
            frrmsg[sizfof(frrmsg)-1] = 0;
            HPROF_ERROR(JNI_FALSE, frrmsg);
            frror_fxit_prodfss(1);
        }
        gdbtb->sodkft = JNI_TRUE;
    } flsf {
        /* If going out to b filf, obfy tif fordf=y|n option */
        if ( !gdbtb->fordf_output ) {
            mbkf_uniquf_filfnbmf(&(gdbtb->output_filfnbmf));
        }
        /* Mbkf doubly surf tiis filf dofs NOT fxist */
        (void)rfmovf(gdbtb->output_filfnbmf);
        /* Crfbtf tif filf */
        if (gdbtb->output_formbt == 'b') {
            gdbtb->fd = md_drfbt_binbry(gdbtb->output_filfnbmf);
        } flsf {
            gdbtb->fd = md_drfbt(gdbtb->output_filfnbmf);
        }
        if (gdbtb->fd < 0) {
            dibr frrmsg[FILENAME_MAX+80];

            (void)md_snprintf(frrmsg, sizfof(frrmsg),
                "dbn't drfbtf profilf filf: %s", gdbtb->output_filfnbmf);
            frrmsg[sizfof(frrmsg)-1] = 0;
            HPROF_ERROR(JNI_FALSE, frrmsg);
            frror_fxit_prodfss(1);
        }
    }

}

/* ------------------------------------------------------------------- */
/* Dbtb rfsft bnd dump fundtions */

stbtid void
rfsft_bll_dbtb(void)
{
    if (gdbtb->dpu_sbmpling || gdbtb->dpu_timing || gdbtb->monitor_trbding) {
        rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk);
    }

    if (gdbtb->dpu_sbmpling || gdbtb->dpu_timing) {
        trbdf_dlfbr_dost();
    }
    if (gdbtb->monitor_trbding) {
        monitor_dlfbr();
    }

    if (gdbtb->dpu_sbmpling || gdbtb->dpu_timing || gdbtb->monitor_trbding) {
        rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
    }
}

stbtid void rfsft_dlbss_lobd_stbtus(JNIEnv *fnv, jtirfbd tirfbd);

stbtid void
dump_bll_dbtb(JNIEnv *fnv)
{
    vfrbosf_mfssbgf("Dumping");
    if (gdbtb->monitor_trbding) {
        vfrbosf_mfssbgf(" dontfndfd monitor usbgf ...");
        tls_dump_monitor_stbtf(fnv);
        monitor_writf_dontfndfd_timf(fnv, gdbtb->dutoff_point);
    }
    if (gdbtb->ifbp_dump) {
        vfrbosf_mfssbgf(" Jbvb ifbp ...");
        /* Updbtf tif dlbss tbblf */
        rfsft_dlbss_lobd_stbtus(fnv, NULL);
        sitf_ifbpdump(fnv);
    }
    if (gdbtb->bllod_sitfs) {
        vfrbosf_mfssbgf(" bllodbtion sitfs ...");
        sitf_writf(fnv, 0, gdbtb->dutoff_point);
    }
    if (gdbtb->dpu_sbmpling) {
        vfrbosf_mfssbgf(" CPU usbgf by sbmpling running tirfbds ...");
        trbdf_output_dost(fnv, gdbtb->dutoff_point);
    }
    if (gdbtb->dpu_timing) {
        if (!gdbtb->old_timing_formbt) {
            vfrbosf_mfssbgf(" CPU usbgf by timing mftiods ...");
            trbdf_output_dost(fnv, gdbtb->dutoff_point);
        } flsf {
            vfrbosf_mfssbgf(" CPU usbgf in old prof formbt ...");
            trbdf_output_dost_in_prof_formbt(fnv);
        }
    }
    rfsft_bll_dbtb();
    io_flusi();
    vfrbosf_mfssbgf(" donf.\n");
}

/* ------------------------------------------------------------------- */
/* Dfbling witi dlbss lobd bnd unlobd stbtus */

stbtid void
rfsft_dlbss_lobd_stbtus(JNIEnv *fnv, jtirfbd tirfbd)
{

    WITH_LOCAL_REFS(fnv, 1) {
        jint    dlbss_dount;
        jdlbss *dlbssfs;
        jint    i;

        /* Gft bll dlbssfs from JVMTI, mbkf surf tify brf in tif dlbss tbblf. */
        gftLobdfdClbssfs(&dlbssfs, &dlbss_dount);

        /* Wf don't know if tif dlbss list ibs dibngfd rfblly, so wf
         *    gufss by tif dlbss dount dibnging. Don't wbnt to do
         *    b bundi of work on dlbssfs wifn it's unnfdfssbry.
         *    I bssumf tibt fvfn tiougi wf ibvf globbl rfffrfndfs on tif
         *    jdlbss objfdt tibt tif dlbss is still donsidfrfd unlobdfd.
         *    (f.g. GC of jdlbss isn't rfquirfd for it to bf indludfd
         *    in tif unlobdfd list, or not in tif lobd list)
         *    [Notf: Usf of Wfbk rfffrfndfs wbs b pfrformbndf problfm.]
         */
        if ( dlbss_dount != gdbtb->dlbss_dount ) {

            rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {

                /* Unmbrk tif dlbssfs in tif lobd list */
                dlbss_bll_stbtus_rfmovf(CLASS_IN_LOAD_LIST);

                /* Prftfnd likf it wbs b dlbss lobd fvfnt */
                for ( i = 0 ; i < dlbss_dount ; i++ ) {
                    jobjfdt lobdfr;

                    lobdfr = gftClbssLobdfr(dlbssfs[i]);
                    fvfnt_dlbss_lobd(fnv, tirfbd, dlbssfs[i], lobdfr);
                }

                /* Prodfss tif dlbssfs tibt ibvf bffn unlobdfd */
                dlbss_do_unlobds(fnv);

            } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);

        }

        /* Frff tif spbdf bnd sbvf tif dount. */
        jvmtiDfbllodbtf(dlbssfs);
        gdbtb->dlbss_dount = dlbss_dount;

    } END_WITH_LOCAL_REFS;

}

/* A GC or Dfbti fvfnt ibs ibppfnfd, so do somf dlfbnup */
stbtid void
objfdt_frff_dlfbnup(JNIEnv *fnv, jboolfbn fordf_dlbss_tbblf_rfsft)
{
    Stbdk *stbdk;

    /* Tifn wf prodfss tif ObjfdtFrffStbdk */
    rbwMonitorEntfr(gdbtb->objfdt_frff_lodk); {
        stbdk = gdbtb->objfdt_frff_stbdk;
        gdbtb->objfdt_frff_stbdk = NULL; /* Will triggfr nfw stbdk */
    } rbwMonitorExit(gdbtb->objfdt_frff_lodk);

    /* Notidf wf just grbbbfd tif stbdk of frffd objfdts so
     *    bny objfdt frff fvfnts will drfbtf b nfw stbdk.
     */
    if ( stbdk != NULL ) {
        int dount;
        int i;

        dount = stbdk_dfpti(stbdk);

        /* If wf sbw somftiing frffd in tiis GC */
        if ( dount > 0 ) {

            for ( i = 0 ; i < dount ; i++ ) {
                ObjfdtIndfx objfdt_indfx;
                jlong tbg;

                tbg = *(jlong*)stbdk_flfmfnt(stbdk,i);
                    objfdt_indfx = tbg_fxtrbdt(tbg);

                (void)objfdt_frff(objfdt_indfx);
            }

            /* Wf rfsft tif dlbss lobd stbtus (only do tiis ondf) */
            rfsft_dlbss_lobd_stbtus(fnv, NULL);
            fordf_dlbss_tbblf_rfsft = JNI_FALSE;

        }

        /* Just tfrminbtf tiis stbdk objfdt */
        stbdk_tfrm(stbdk);
    }

    /* Wf rfsft tif dlbss lobd stbtus if wf ibvfn't bnd nffd to */
    if ( fordf_dlbss_tbblf_rfsft ) {
        rfsft_dlbss_lobd_stbtus(fnv, NULL);
    }

}

/* Mbin fundtion for tirfbd tibt wbtdifs for GC finisi fvfnts */
stbtid void JNICALL
gd_finisi_wbtdifr(jvmtiEnv *jvmti, JNIEnv *fnv, void *p)
{
    jboolfbn bdtivf;

    bdtivf = JNI_TRUE;

    /* Indidbtf tif wbtdifr tirfbd is bdtivf */
    rbwMonitorEntfr(gdbtb->gd_finisi_lodk); {
        gdbtb->gd_finisi_bdtivf = JNI_TRUE;
    } rbwMonitorExit(gdbtb->gd_finisi_lodk);

    /* Loop wiilf bdtivf */
    wiilf ( bdtivf ) {
        jboolfbn do_dlfbnup;

        do_dlfbnup = JNI_FALSE;
        rbwMonitorEntfr(gdbtb->gd_finisi_lodk); {
            /* Don't wbit if VM_DEATH wbnts us to quit */
            if ( gdbtb->gd_finisi_stop_rfqufst ) {
                /* Timf to tfrminbtf */
                bdtivf = JNI_FALSE;
            } flsf {
                /* Wbit for notifidbtion to do dlfbnup, or tfrminbtf */
                rbwMonitorWbit(gdbtb->gd_finisi_lodk, 0);
                /* Aftfr wbit, difdk to sff if VM_DEATH wbnts us to quit */
                if ( gdbtb->gd_finisi_stop_rfqufst ) {
                    /* Timf to tfrminbtf */
                    bdtivf = JNI_FALSE;
                }
            }
            if ( bdtivf && gdbtb->gd_finisi > 0 ) {
                /* Timf to dlfbnup, rfsft dount bnd prfpbrf for dlfbnup */
                gdbtb->gd_finisi = 0;
                do_dlfbnup = JNI_TRUE;
            }
        } rbwMonitorExit(gdbtb->gd_finisi_lodk);

        /* Do tif dlfbnup if rfqufstfd outsidf gd_finisi_lodk */
        if ( do_dlfbnup ) {
            /* Frff up bll frffd objfdts, don't fordf dlbss tbblf rfsft
             *   Wf dbnnot lft tif VM_DEATH domplftf wiilf wf brf doing
             *   tiis dlfbnup. So if during tiis, VM_DEATH ibppfns,
             *   tif VM_DEATH dbllbbdk siould blodk wbiting for tiis
             *   loop to tfrminbtf, bnd sfnd b notifidbtion to tif
             *   VM_DEATH tirfbd.
             */
            objfdt_frff_dlfbnup(fnv, JNI_FALSE);

            /* Clfbnup tif tls tbblf wifrf tif Tirfbd objfdts wfrf GC'd */
            tls_gbrbbgf_dollfdt(fnv);
        }

    }

    /* Fblling out mfbns VM_DEATH is ibppfning, wf nffd to notify VM_DEATH
     *    tibt wf brf donf doing tif dlfbnup. VM_DEATH is wbiting on tiis
     *    notify.
     */
    rbwMonitorEntfr(gdbtb->gd_finisi_lodk); {
        gdbtb->gd_finisi_bdtivf = JNI_FALSE;
        rbwMonitorNotifyAll(gdbtb->gd_finisi_lodk);
    } rbwMonitorExit(gdbtb->gd_finisi_lodk);
}

/* ------------------------------------------------------------------- */
/* JVMTI Evfnt dbllbbdk fundtions */

stbtid void
sftup_fvfnt_modf(jboolfbn onlobd_sft_only, jvmtiEvfntModf stbtf)
{
    if ( onlobd_sft_only ) {
        sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_VM_INIT,                   NULL);
        sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_VM_DEATH,                  NULL);
        if (gdbtb->bdi) {
            sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_CLASS_FILE_LOAD_HOOK,      NULL);
        }
    } flsf {
        /* Enbblf bll otifr JVMTI fvfnts of intfrfst now. */
        sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_THREAD_START,              NULL);
        sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_THREAD_END,                NULL);
        sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_CLASS_LOAD,                NULL);
        sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_CLASS_PREPARE,             NULL);
        sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_DATA_DUMP_REQUEST,         NULL);
        if (gdbtb->dpu_timing) {
            sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_EXCEPTION_CATCH,           NULL);
        }
        if (gdbtb->monitor_trbding) {
            sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_MONITOR_WAIT,              NULL);
            sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_MONITOR_WAITED,            NULL);
            sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_MONITOR_CONTENDED_ENTER,   NULL);
            sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_MONITOR_CONTENDED_ENTERED, NULL);
        }
        if (gdbtb->obj_wbtdi) {
            sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_OBJECT_FREE,               NULL);
        }
        sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_GARBAGE_COLLECTION_START,  NULL);
        sftEvfntNotifidbtionModf(stbtf,
                        JVMTI_EVENT_GARBAGE_COLLECTION_FINISH, NULL);
    }
}

/* JVMTI_EVENT_VM_INIT */
stbtid void JNICALL
dbVMInit(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd)
{
    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {

        LobdfrIndfx lobdfr_indfx;
        ClbssIndfx  dnum;
        TlsIndfx    tls_indfx;

        gdbtb->jvm_initiblizing = JNI_TRUE;

        /* Hfbdfr to usf in ifbp dumps */
        gdbtb->ifbdfr    = "JAVA PROFILE 1.0.1";
        gdbtb->sfgmfntfd = JNI_FALSE;
        if (gdbtb->output_formbt == 'b') {
            /* Wf nffd JNI ifrf to dbll in bnd gft tif durrfnt mbximum mfmory */
            gdbtb->mbxMfmory      = gftMbxMfmory(fnv);
            gdbtb->mbxHfbpSfgmfnt = (jlong)2000000000;
            /* Morf tibn 2Gig triggfrs sfgmfnts bnd 1.0.2 */
            if ( gdbtb->mbxMfmory >= gdbtb->mbxHfbpSfgmfnt ) {
                gdbtb->ifbdfr    = "JAVA PROFILE 1.0.2";
                gdbtb->sfgmfntfd = JNI_TRUE; /* 1.0.2 */
            }
        }

        /* Wf writf tif initibl ifbdfr bftfr tif VM initiblizfs now
         *    bfdbusf wf nffdfd to usf JNI to gft mbxMfmory bnd dftfrminf if
         *    b 1.0.1 or b 1.0.2 ifbdfr will bf usfd.
         *    Tiis usfd to bf donf in Agfnt_OnLobd.
         */
        io_writf_filf_ifbdfr();

        LOG("dbVMInit bfgin");

        /* Crfbtf b systfm lobdfr fntry first */
        lobdfr_indfx            = lobdfr_find_or_drfbtf(NULL,NULL);

        /* Find tif tirfbd jdlbss (dofs JNI dblls) */
        gdbtb->tirfbd_dnum = dlbss_find_or_drfbtf("Ljbvb/lbng/Tirfbd;",
                        lobdfr_indfx);
        dlbss_bdd_stbtus(gdbtb->tirfbd_dnum, CLASS_SYSTEM);

        /* Issuf fbkf systfm tirfbd stbrt */
        tls_indfx = tls_find_or_drfbtf(fnv, tirfbd);

        /* Sftup tif Trbdkfr dlbss (siould bf first dlbss in tbblf) */
        trbdkfr_sftup_dlbss();

        /* Find sflfdtfd systfm dlbssfs to kffp trbdk of */
        gdbtb->systfm_dlbss_sizf = 0;
        dnum = dlbss_find_or_drfbtf("Ljbvb/lbng/Objfdt;", lobdfr_indfx);

        gdbtb->systfm_trbdf_indfx = tls_gft_trbdf(tls_indfx, fnv,
                                gdbtb->mbx_trbdf_dfpti, JNI_FALSE);
        gdbtb->systfm_objfdt_sitf_indfx = sitf_find_or_drfbtf(
                    dnum, gdbtb->systfm_trbdf_indfx);

        /* Usfd to ID HPROF gfnfrbtfd itfms */
        gdbtb->iprof_trbdf_indfx = tls_gft_trbdf(tls_indfx, fnv,
                                gdbtb->mbx_trbdf_dfpti, JNI_FALSE);
        gdbtb->iprof_sitf_indfx = sitf_find_or_drfbtf(
                    dnum, gdbtb->iprof_trbdf_indfx);

        if ( gdbtb->logflbgs & LOG_DUMP_LISTS ) {
            list_bll_tbblfs();
        }

        /* Primf tif dlbss tbblf */
        rfsft_dlbss_lobd_stbtus(fnv, tirfbd);

        /* Find tif trbdkfr jdlbss bnd jmftiodID's (dofs JNI dblls) */
        if ( gdbtb->bdi ) {
            trbdkfr_sftup_mftiods(fnv);
        }

        /* Stbrt bny bgfnt tirfbds (dofs JNI, JVMTI, bnd Jbvb dblls) */

        /* Tirfbd to wbtdi for gd_finisi fvfnts */
        rbwMonitorEntfr(gdbtb->gd_finisi_lodk); {
            drfbtfAgfntTirfbd(fnv, "HPROF gd_finisi wbtdifr",
                              &gd_finisi_wbtdifr);
        } rbwMonitorExit(gdbtb->gd_finisi_lodk);

        /* Stbrt up listfnfr tirfbd if wf nffd it */
        if ( gdbtb->sodkft ) {
            listfnfr_init(fnv);
        }

        /* Stbrt up dpu sbmpling tirfbd if wf nffd it */
        if ( gdbtb->dpu_sbmpling ) {
            /* Notf: tiis dould blso gft stbrtfd lbtfr (sff dpu) */
            dpu_sbmplf_init(fnv);
        }

        /* Sftup fvfnt modfs */
        sftup_fvfnt_modf(JNI_FALSE, JVMTI_ENABLE);

        /* Engbgf trbdking (sfts Jbvb Trbdkfr fifld so injfdtions dbll into
         *     bgfnt librbry).
         */
        if ( gdbtb->bdi ) {
            trbdkfr_fngbgf(fnv);
        }

        /* Indidbtf tif VM is initiblizfd now */
        gdbtb->jvm_initiblizfd = JNI_TRUE;
        gdbtb->jvm_initiblizing = JNI_FALSE;

        LOG("dbVMInit fnd");

    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
}

/* JVMTI_EVENT_VM_DEATH */
stbtid void JNICALL
dbVMDfbti(jvmtiEnv *jvmti, JNIEnv *fnv)
{
    /*
     * Usf lodbl flbg to minimizf gdbtb->dump_lodk iold timf.
     */
    jboolfbn nffd_to_dump = JNI_FALSE;

    LOG("dbVMDfbti");

    /* Siutdown tirfbd wbtdiing gd_finisi, outsidf CALLBACK lodks.
     *   Wf nffd to mbkf surf tif wbtdifr tirfbd is donf doing bny dlfbnup
     *   work bfforf wf dontinuf ifrf.
     */
    rbwMonitorEntfr(gdbtb->gd_finisi_lodk); {
        /* Notify wbtdifr tirfbd to finisi up, it will sfnd
         *   bnotifr notify wifn donf. If tif wbtdifr tirfbd is busy
         *   dlfbning up, it will dftfdt gd_finisi_stop_rfqufst wifn it's donf.
         *   Tifn it sfts gd_finisi_bdtivf to JNI_FALSE bnd will notify us.
         *   If tif wbtdifr tirfbd is wbiting to bf notififd, tifn tif
         *   notifidbtion wbkfs it up.
         *   Wf do not wbnt to do tif VM_DEATH wiilf tif gd_finisi
         *   wbtdifr tirfbd is in tif middlf of b dlfbnup.
         */
        gdbtb->gd_finisi_stop_rfqufst = JNI_TRUE;
        rbwMonitorNotifyAll(gdbtb->gd_finisi_lodk);
        /* Wbit for tif gd_finisi wbtdifr tirfbd to notify us it's donf */
        wiilf ( gdbtb->gd_finisi_bdtivf ) {
            rbwMonitorWbit(gdbtb->gd_finisi_lodk,0);
        }
    } rbwMonitorExit(gdbtb->gd_finisi_lodk);

    /* Tif gd_finisi wbtdifr tirfbd siould bf donf now, or donf siortly. */


    /* BEGIN_CALLBACK/END_CALLBACK ibndling. */

    /* Tif dbllbbdkBlodk prfvfnts bny bdtivf dbllbbdks from rfturning
     *   bbdk to tif VM, bnd blso blodks bll nfw dbllbbdks.
     *   Wf wbnt to prfvfnt bny tirfbds from prfmbturf dfbti, so
     *   tibt wf don't ibvf worry bbout tibt during tirfbd qufrifs
     *   in tiis finbl dump prodfss.
     */
    rbwMonitorEntfr(gdbtb->dbllbbdkBlodk); {

        /* Wf nffd to wbit for bll dbllbbdks bdtivfly fxfduting to blodk
         *   on fxit, bnd nfw onfs will blodk on fntry.
         *   Tif BEGIN_CALLBACK/END_CALLBACK mbdros kffp trbdk of dbllbbdks
         *   tibt brf bdtivf.
         *   Ondf tif lbst bdtivf dbllbbdk is donf, it will notify tiis
         *   tirfbd bnd blodk.
         */

        rbwMonitorEntfr(gdbtb->dbllbbdkLodk); {
            /* Turn off nbtivf dblls */
            if ( gdbtb->bdi ) {
                trbdkfr_disfngbgf(fnv);
            }
            gdbtb->vm_dfbti_dbllbbdk_bdtivf = JNI_TRUE;
            wiilf (gdbtb->bdtivf_dbllbbdks > 0) {
                rbwMonitorWbit(gdbtb->dbllbbdkLodk, 0);
            }
        } rbwMonitorExit(gdbtb->dbllbbdkLodk);

        /* Now wf know tibt no tirfbds will dif on us, bfing blodkfd
         *   on somf fvfnt dbllbbdk, bt b minimum TirfbdEnd.
         */

        /* Mbkf somf bbsid difdks. */
        rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
            if ( gdbtb->jvm_initiblizing ) {
                HPROF_ERROR(JNI_TRUE, "VM Dfbti during VM Init");
                rfturn;
            }
            if ( !gdbtb->jvm_initiblizfd ) {
                HPROF_ERROR(JNI_TRUE, "VM Dfbti bfforf VM Init");
                rfturn;
            }
            if (gdbtb->jvm_siut_down) {
                HPROF_ERROR(JNI_TRUE, "VM Dfbti morf tibn ondf?");
                rfturn;
            }
        } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);

        /* Siutdown tif dpu loop tirfbd */
        if ( gdbtb->dpu_sbmpling ) {
            dpu_sbmplf_tfrm(fnv);
        }

        /* Timf to dump tif finbl dbtb */
        rbwMonitorEntfr(gdbtb->dump_lodk); {

            gdbtb->jvm_siut_down = JNI_TRUE;

            if (!gdbtb->dump_in_prodfss) {
                nffd_to_dump    = JNI_TRUE;
                gdbtb->dump_in_prodfss = JNI_TRUE;
                /*
                 * Sftting gdbtb->dump_in_prodfss will dbusf dpu sbmpling to pbusf
                 * (if wf brf sbmpling). Wf don't rfsumf sbmpling bftfr tif
                 * dump_bll_dbtb() dbll bflow bfdbusf tif VM is siutting
                 * down.
                 */
            }

        } rbwMonitorExit(gdbtb->dump_lodk);

        /* Dump fvfrytiing if wf nffd to */
        if (gdbtb->dump_on_fxit && nffd_to_dump) {

            dump_bll_dbtb(fnv);
        }

        /* Disbblf bll fvfnts bnd dbllbbdks now, bll of tifm.
         *   NOTE: It's importbnt tibt tiis bf donf bftfr tif dump
         *         it prfvfnts otifr tirfbds from mfssing up tif dbtb
         *         bfdbusf tify will blodk on TirfbdStbrt bnd TirfbdEnd
         *         fvfnts duf to tif CALLBACK blodk.
         */
        sft_dbllbbdks(JNI_FALSE);
        sftup_fvfnt_modf(JNI_FALSE, JVMTI_DISABLE);
        sftup_fvfnt_modf(JNI_TRUE, JVMTI_DISABLE);

        /* Writf tbil of filf */
        io_writf_filf_footfr();

    } rbwMonitorExit(gdbtb->dbllbbdkBlodk);

    /* Siutdown tif listfnfr tirfbd bnd sodkft, or flusi I/O bufffrs */
    if (gdbtb->sodkft) {
        listfnfr_tfrm(fnv);
    } flsf {
        io_flusi();
    }

    /* Closf tif filf dfsdriptors down */
    if ( gdbtb->fd  >= 0 ) {
        (void)md_dlosf(gdbtb->fd);
        gdbtb->fd = -1;
        if ( gdbtb->logflbgs & LOG_CHECK_BINARY ) {
            if (gdbtb->output_formbt == 'b' && gdbtb->output_filfnbmf != NULL) {
                difdk_binbry_filf(gdbtb->output_filfnbmf);
            }
        }
    }
    if ( gdbtb->ifbp_fd  >= 0 ) {
        (void)md_dlosf(gdbtb->ifbp_fd);
        gdbtb->ifbp_fd = -1;
    }

    if ( gdbtb->difdk_fd  >= 0 ) {
        (void)md_dlosf(gdbtb->difdk_fd);
        gdbtb->difdk_fd = -1;
    }

    /* Rfmovf tif tfmporbry ifbp filf */
    if (gdbtb->ifbp_dump) {
        (void)rfmovf(gdbtb->ifbpfilfnbmf);
    }

    /* If logging, dump tif tbblfs */
    if ( gdbtb->logflbgs & LOG_DUMP_LISTS ) {
        list_bll_tbblfs();
    }

    /* Mbkf surf bll globbl rfffrfndfs brf dflftfd */
    dlbss_dflftf_globbl_rfffrfndfs(fnv);
    lobdfr_dflftf_globbl_rfffrfndfs(fnv);
    tls_dflftf_globbl_rfffrfndfs(fnv);

}

/* JVMTI_EVENT_THREAD_START */
stbtid void JNICALL
dbTirfbdStbrt(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd)
{
    LOG3("dbTirfbdStbrt", "tirfbd is", (int)(long)(ptrdiff_t)tirfbd);

    BEGIN_CALLBACK() {
        fvfnt_tirfbd_stbrt(fnv, tirfbd);
    } END_CALLBACK();
}

/* JVMTI_EVENT_THREAD_END */
stbtid void JNICALL
dbTirfbdEnd(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd)
{
    LOG3("dbTirfbdEnd", "tirfbd is", (int)(long)(ptrdiff_t)tirfbd);

    BEGIN_CALLBACK() {
        fvfnt_tirfbd_fnd(fnv, tirfbd);
    } END_CALLBACK();
}

/* JVMTI_EVENT_CLASS_FILE_LOAD_HOOK */
stbtid void JNICALL
dbClbssFilfLobdHook(jvmtiEnv *jvmti_fnv, JNIEnv* fnv,
                jdlbss dlbss_bfing_rfdffinfd, jobjfdt lobdfr,
                donst dibr* nbmf, jobjfdt protfdtion_dombin,
                jint dlbss_dbtb_lfn, donst unsignfd dibr* dlbss_dbtb,
                jint* nfw_dlbss_dbtb_lfn, unsignfd dibr** nfw_dlbss_dbtb)
{

    /* WARNING: Tiis will bf dbllfd bfforf VM_INIT. */

    LOG2("dbClbssFilfLobdHook:",(nbmf==NULL?"Unknown":nbmf));

    if (!gdbtb->bdi) {
        rfturn;
    }

    BEGIN_CALLBACK() {
        rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
            donst dibr *dlbssnbmf;

            if ( gdbtb->bdi_dountfr == 0 ) {
                /* Primf tif systfm dlbssfs */
                dlbss_primf_systfm_dlbssfs();
            }

            gdbtb->bdi_dountfr++;

            *nfw_dlbss_dbtb_lfn = 0;
            *nfw_dlbss_dbtb     = NULL;

            /* Nbmf dould bf NULL */
            if ( nbmf == NULL ) {
                dlbssnbmf = ((JbvbCrwDfmoClbssnbmf)
                             (gdbtb->jbvb_drw_dfmo_dlbssnbmf_fundtion))
                    (dlbss_dbtb, dlbss_dbtb_lfn, &my_drw_fbtbl_frror_ibndlfr);
                if ( dlbssnbmf == NULL ) {
                    HPROF_ERROR(JNI_TRUE, "No dlbssnbmf in dlbssfilf");
                }
            } flsf {
                dlbssnbmf = strdup(nbmf);
                if ( dlbssnbmf == NULL ) {
                    HPROF_ERROR(JNI_TRUE, "Rbn out of mbllod() spbdf");
                }
            }

            /* Tif trbdkfr dlbss itsflf? */
            if ( strdmp(dlbssnbmf, TRACKER_CLASS_NAME) != 0 ) {
                ClbssIndfx            dnum;
                int                   systfm_dlbss;
                unsignfd dibr *       nfw_imbgf;
                long                  nfw_lfngti;
                int                   lfn;
                dibr                 *signbturf;
                LobdfrIndfx           lobdfr_indfx;

                LOG2("dbClbssFilfLobdHook injfdting dlbss" , dlbssnbmf);

                /* Dffinf b uniquf dlbss numbfr for tiis dlbss */
                lfn              = (int)strlfn(dlbssnbmf);
                signbturf        = HPROF_MALLOC(lfn+3);
                signbturf[0]     = JVM_SIGNATURE_CLASS;
                (void)mfmdpy(signbturf+1, dlbssnbmf, lfn);
                signbturf[lfn+1] = JVM_SIGNATURE_ENDCLASS;
                signbturf[lfn+2] = 0;
                lobdfr_indfx = lobdfr_find_or_drfbtf(fnv,lobdfr);
                if ( dlbss_bfing_rfdffinfd != NULL ) {
                    dnum  = dlbss_find_or_drfbtf(signbturf, lobdfr_indfx);
                } flsf {
                    dnum  = dlbss_drfbtf(signbturf, lobdfr_indfx);
                }
                HPROF_FREE(signbturf);
                signbturf        = NULL;

                /* Mbkf surf dlbss dofsn't gft unlobdfd by bddidfnt */
                dlbss_bdd_stbtus(dnum, CLASS_IN_LOAD_LIST);

                /* Is it b systfm dlbss? */
                systfm_dlbss = 0;
                if (    (!gdbtb->jvm_initiblizfd)
                     && (!gdbtb->jvm_initiblizing)
                     && ( ( dlbss_gft_stbtus(dnum) & CLASS_SYSTEM) != 0
                            || gdbtb->bdi_dountfr < 8 ) ) {
                    systfm_dlbss = 1;
                    LOG2(dlbssnbmf, " is b systfm dlbss");
                }

                nfw_imbgf = NULL;
                nfw_lfngti = 0;

                /* Cbll tif dlbss filf rfbdfr/writf dfmo dodf */
                ((JbvbCrwDfmo)(gdbtb->jbvb_drw_dfmo_fundtion))(
                    dnum,
                    dlbssnbmf,
                    dlbss_dbtb,
                    dlbss_dbtb_lfn,
                    systfm_dlbss,
                    TRACKER_CLASS_NAME,
                    TRACKER_CLASS_SIG,
                    (gdbtb->dpu_timing)?TRACKER_CALL_NAME:NULL,
                    (gdbtb->dpu_timing)?TRACKER_CALL_SIG:NULL,
                    (gdbtb->dpu_timing)?TRACKER_RETURN_NAME:NULL,
                    (gdbtb->dpu_timing)?TRACKER_RETURN_SIG:NULL,
                    (gdbtb->obj_wbtdi)?TRACKER_OBJECT_INIT_NAME:NULL,
                    (gdbtb->obj_wbtdi)?TRACKER_OBJECT_INIT_SIG:NULL,
                    (gdbtb->obj_wbtdi)?TRACKER_NEWARRAY_NAME:NULL,
                    (gdbtb->obj_wbtdi)?TRACKER_NEWARRAY_SIG:NULL,
                    &nfw_imbgf,
                    &nfw_lfngti,
                    &my_drw_fbtbl_frror_ibndlfr,
                    &dlbss_sft_mftiods);

                if ( nfw_lfngti > 0 ) {
                    unsignfd dibr *jvmti_spbdf;

                    LOG2("dbClbssFilfLobdHook DID injfdt tiis dlbss", dlbssnbmf);
                    jvmti_spbdf = (unsignfd dibr *)jvmtiAllodbtf((jint)nfw_lfngti);
                    (void)mfmdpy((void*)jvmti_spbdf, (void*)nfw_imbgf, (int)nfw_lfngti);
                    *nfw_dlbss_dbtb_lfn = (jint)nfw_lfngti;
                    *nfw_dlbss_dbtb     = jvmti_spbdf; /* VM will dfbllodbtf */
                } flsf {
                    LOG2("dbClbssFilfLobdHook DID NOT injfdt tiis dlbss", dlbssnbmf);
                    *nfw_dlbss_dbtb_lfn = 0;
                    *nfw_dlbss_dbtb     = NULL;
                }
                if ( nfw_imbgf != NULL ) {
                    (void)frff((void*)nfw_imbgf); /* Frff mbllod() spbdf witi frff() */
                }
            }
            (void)frff((void*)dlbssnbmf);
        } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
    } END_CALLBACK();
}

/* JVMTI_EVENT_CLASS_LOAD */
stbtid void JNICALL
dbClbssLobd(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd, jdlbss klbss)
{

    /* WARNING: Tiis MAY bf dbllfd bfforf VM_INIT. */

    LOG("dbClbssLobd");

    BEGIN_CALLBACK() {
        rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {

            WITH_LOCAL_REFS(fnv, 1) {
                jobjfdt lobdfr;

                lobdfr = gftClbssLobdfr(klbss);
                fvfnt_dlbss_lobd(fnv, tirfbd, klbss, lobdfr);
            } END_WITH_LOCAL_REFS;

        } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
    } END_CALLBACK();
}

/* JVMTI_EVENT_CLASS_PREPARE */
stbtid void JNICALL
dbClbssPrfpbrf(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd, jdlbss klbss)
{

    /* WARNING: Tiis will bf dbllfd bfforf VM_INIT. */

    LOG("dbClbssPrfpbrf");

    BEGIN_CALLBACK() {
        rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {

            WITH_LOCAL_REFS(fnv, 1) {
                jobjfdt lobdfr;

                lobdfr = NULL;
                lobdfr = gftClbssLobdfr(klbss);
                fvfnt_dlbss_prfpbrf(fnv, tirfbd, klbss, lobdfr);
            } END_WITH_LOCAL_REFS;

        } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
    } END_CALLBACK();

}

/* JVMTI_EVENT_DATA_DUMP_REQUEST */
stbtid void JNICALL
dbDbtbDumpRfqufst(jvmtiEnv *jvmti)
{
    jboolfbn nffd_to_dump;

    LOG("dbDbtbDumpRfqufst");

    BEGIN_CALLBACK() {
        nffd_to_dump = JNI_FALSE;
        rbwMonitorEntfr(gdbtb->dump_lodk); {
            if (!gdbtb->dump_in_prodfss) {
                nffd_to_dump    = JNI_TRUE;
                gdbtb->dump_in_prodfss = JNI_TRUE;
            }
        } rbwMonitorExit(gdbtb->dump_lodk);

        if (nffd_to_dump) {
            dump_bll_dbtb(gftEnv());

            rbwMonitorEntfr(gdbtb->dump_lodk); {
                gdbtb->dump_in_prodfss = JNI_FALSE;
            } rbwMonitorExit(gdbtb->dump_lodk);

            if (gdbtb->dpu_sbmpling && !gdbtb->jvm_siut_down) {
                dpu_sbmplf_on(NULL, 0); /* rfsumf sbmpling */
            }
        }
    } END_CALLBACK();

}

/* JVMTI_EVENT_EXCEPTION_CATCH */
stbtid void JNICALL
dbExdfptionCbtdi(jvmtiEnv *jvmti, JNIEnv* fnv,
                jtirfbd tirfbd, jmftiodID mftiod, jlodbtion lodbtion,
                jobjfdt fxdfption)
{
    LOG("dbExdfptionCbtdi");

    BEGIN_CALLBACK() {
        fvfnt_fxdfption_dbtdi(fnv, tirfbd, mftiod, lodbtion, fxdfption);
    } END_CALLBACK();
}

/* JVMTI_EVENT_MONITOR_WAIT */
stbtid void JNICALL
dbMonitorWbit(jvmtiEnv *jvmti, JNIEnv* fnv,
                jtirfbd tirfbd, jobjfdt objfdt, jlong timfout)
{
    LOG("dbMonitorWbit");

    BEGIN_CALLBACK() {
        monitor_wbit_fvfnt(fnv, tirfbd, objfdt, timfout);
    } END_CALLBACK();
}

/* JVMTI_EVENT_MONITOR_WAITED */
stbtid void JNICALL
dbMonitorWbitfd(jvmtiEnv *jvmti, JNIEnv* fnv,
                jtirfbd tirfbd, jobjfdt objfdt, jboolfbn timfd_out)
{
    LOG("dbMonitorWbitfd");

    BEGIN_CALLBACK() {
        monitor_wbitfd_fvfnt(fnv, tirfbd, objfdt, timfd_out);
    } END_CALLBACK();
}

/* JVMTI_EVENT_MONITOR_CONTENDED_ENTER */
stbtid void JNICALL
dbMonitorContfndfdEntfr(jvmtiEnv *jvmti, JNIEnv* fnv,
                jtirfbd tirfbd, jobjfdt objfdt)
{
    LOG("dbMonitorContfndfdEntfr");

    BEGIN_CALLBACK() {
        monitor_dontfndfd_fntfr_fvfnt(fnv, tirfbd, objfdt);
    } END_CALLBACK();
}

/* JVMTI_EVENT_MONITOR_CONTENDED_ENTERED */
stbtid void JNICALL
dbMonitorContfndfdEntfrfd(jvmtiEnv *jvmti, JNIEnv* fnv,
                jtirfbd tirfbd, jobjfdt objfdt)
{
    LOG("dbMonitorContfndfdEntfrfd");

    BEGIN_CALLBACK() {
        monitor_dontfndfd_fntfrfd_fvfnt(fnv, tirfbd, objfdt);
    } END_CALLBACK();
}

/* JVMTI_EVENT_GARBAGE_COLLECTION_START */
stbtid void JNICALL
dbGbrbbgfCollfdtionStbrt(jvmtiEnv *jvmti)
{
    LOG("dbGbrbbgfCollfdtionStbrt");

    /* Only dblls to Allodbtf, Dfbllodbtf, RbwMonitorEntfr & RbwMonitorExit
     *   brf bllowfd ifrf (sff tif JVMTI Spfd).
     */

    gdbtb->gd_stbrt_timf = md_gft_timfmillis();
}

/* JVMTI_EVENT_GARBAGE_COLLECTION_FINISH */
stbtid void JNICALL
dbGbrbbgfCollfdtionFinisi(jvmtiEnv *jvmti)
{
    LOG("dbGbrbbgfCollfdtionFinisi");

    /* Only dblls to Allodbtf, Dfbllodbtf, RbwMonitorEntfr & RbwMonitorExit
     *   brf bllowfd ifrf (sff tif JVMTI Spfd).
     */

    if ( gdbtb->gd_stbrt_timf != -1L ) {
        gdbtb->timf_in_gd += (md_gft_timfmillis() - gdbtb->gd_stbrt_timf);
        gdbtb->gd_stbrt_timf = -1L;
    }

    /* Indrfmfnt gd_finisi dountfr, notify wbtdifr tirfbd */
    rbwMonitorEntfr(gdbtb->gd_finisi_lodk); {
        /* If VM_DEATH is trying to siut it down, don't do bnytiing bt bll.
         *    Nfvfr sfnd notify if VM_DEATH wbnts tif wbtdifr tirfbd to quit.
         */
        if ( gdbtb->gd_finisi_bdtivf ) {
            gdbtb->gd_finisi++;
            rbwMonitorNotifyAll(gdbtb->gd_finisi_lodk);
        }
    } rbwMonitorExit(gdbtb->gd_finisi_lodk);
}

/* JVMTI_EVENT_OBJECT_FREE */
stbtid void JNICALL
dbObjfdtFrff(jvmtiEnv *jvmti, jlong tbg)
{
    LOG3("dbObjfdtFrff", "tbg", (int)tbg);

    /* Only dblls to Allodbtf, Dfbllodbtf, RbwMonitorEntfr & RbwMonitorExit
     *   brf bllowfd ifrf (sff tif JVMTI Spfd).
     */

    HPROF_ASSERT(tbg!=(jlong)0);
    rbwMonitorEntfr(gdbtb->objfdt_frff_lodk); {
        if ( !gdbtb->jvm_siut_down ) {
            Stbdk *stbdk;

            stbdk = gdbtb->objfdt_frff_stbdk;
            if ( stbdk == NULL ) {
                gdbtb->objfdt_frff_stbdk = stbdk_init(512, 512, sizfof(jlong));
                stbdk = gdbtb->objfdt_frff_stbdk;
            }
            stbdk_pusi(stbdk, (void*)&tbg);
        }
    } rbwMonitorExit(gdbtb->objfdt_frff_lodk);
}

stbtid void
sft_dbllbbdks(jboolfbn on)
{
    jvmtiEvfntCbllbbdks dbllbbdks;

    (void)mfmsft(&dbllbbdks,0,sizfof(dbllbbdks));
    if ( ! on ) {
        sftEvfntCbllbbdks(&dbllbbdks);
        rfturn;
    }

    /* JVMTI_EVENT_VM_INIT */
    dbllbbdks.VMInit                     = &dbVMInit;
    /* JVMTI_EVENT_VM_DEATH */
    dbllbbdks.VMDfbti                    = &dbVMDfbti;
    /* JVMTI_EVENT_THREAD_START */
    dbllbbdks.TirfbdStbrt                = &dbTirfbdStbrt;
    /* JVMTI_EVENT_THREAD_END */
    dbllbbdks.TirfbdEnd                  = &dbTirfbdEnd;
    /* JVMTI_EVENT_CLASS_FILE_LOAD_HOOK */
    dbllbbdks.ClbssFilfLobdHook          = &dbClbssFilfLobdHook;
    /* JVMTI_EVENT_CLASS_LOAD */
    dbllbbdks.ClbssLobd                  = &dbClbssLobd;
    /* JVMTI_EVENT_CLASS_PREPARE */
    dbllbbdks.ClbssPrfpbrf               = &dbClbssPrfpbrf;
    /* JVMTI_EVENT_DATA_DUMP_REQUEST */
    dbllbbdks.DbtbDumpRfqufst            = &dbDbtbDumpRfqufst;
    /* JVMTI_EVENT_EXCEPTION_CATCH */
    dbllbbdks.ExdfptionCbtdi             = &dbExdfptionCbtdi;
    /* JVMTI_EVENT_MONITOR_WAIT */
    dbllbbdks.MonitorWbit                = &dbMonitorWbit;
    /* JVMTI_EVENT_MONITOR_WAITED */
    dbllbbdks.MonitorWbitfd              = &dbMonitorWbitfd;
    /* JVMTI_EVENT_MONITOR_CONTENDED_ENTER */
    dbllbbdks.MonitorContfndfdEntfr      = &dbMonitorContfndfdEntfr;
    /* JVMTI_EVENT_MONITOR_CONTENDED_ENTERED */
    dbllbbdks.MonitorContfndfdEntfrfd    = &dbMonitorContfndfdEntfrfd;
    /* JVMTI_EVENT_GARBAGE_COLLECTION_START */
    dbllbbdks.GbrbbgfCollfdtionStbrt     = &dbGbrbbgfCollfdtionStbrt;
    /* JVMTI_EVENT_GARBAGE_COLLECTION_FINISH */
    dbllbbdks.GbrbbgfCollfdtionFinisi    = &dbGbrbbgfCollfdtionFinisi;
    /* JVMTI_EVENT_OBJECT_FREE */
    dbllbbdks.ObjfdtFrff                 = &dbObjfdtFrff;

    sftEvfntCbllbbdks(&dbllbbdks);

}

stbtid void
gftCbpbbilitifs(void)
{
    jvmtiCbpbbilitifs nffdfd_dbpbbilitifs;
    jvmtiCbpbbilitifs potfntibl_dbpbbilitifs;

    /* Fill in onfs tibt wf must ibvf */
    (void)mfmsft(&nffdfd_dbpbbilitifs,0,sizfof(nffdfd_dbpbbilitifs));
    nffdfd_dbpbbilitifs.dbn_gfnfrbtf_gbrbbgf_dollfdtion_fvfnts   = 1;
    nffdfd_dbpbbilitifs.dbn_tbg_objfdts                          = 1;
    if (gdbtb->bdi) {
        nffdfd_dbpbbilitifs.dbn_gfnfrbtf_bll_dlbss_iook_fvfnts   = 1;
    }
    if (gdbtb->obj_wbtdi) {
        nffdfd_dbpbbilitifs.dbn_gfnfrbtf_objfdt_frff_fvfnts      = 1;
    }
    if (gdbtb->dpu_timing || gdbtb->dpu_sbmpling) {
        #if 0 /* Not nffdfd until wf dbll JVMTI for CpuTimf */
        nffdfd_dbpbbilitifs.dbn_gft_tirfbd_dpu_timf              = 1;
        nffdfd_dbpbbilitifs.dbn_gft_durrfnt_tirfbd_dpu_timf      = 1;
        #fndif
        nffdfd_dbpbbilitifs.dbn_gfnfrbtf_fxdfption_fvfnts        = 1;
    }
    if (gdbtb->monitor_trbding) {
        #if 0 /* Not nffdfd until wf dbll JVMTI for CpuTimf */
        nffdfd_dbpbbilitifs.dbn_gft_tirfbd_dpu_timf              = 1;
        nffdfd_dbpbbilitifs.dbn_gft_durrfnt_tirfbd_dpu_timf      = 1;
        #fndif
        nffdfd_dbpbbilitifs.dbn_gft_ownfd_monitor_info           = 1;
        nffdfd_dbpbbilitifs.dbn_gft_durrfnt_dontfndfd_monitor    = 1;
        nffdfd_dbpbbilitifs.dbn_gft_monitor_info                 = 1;
        nffdfd_dbpbbilitifs.dbn_gfnfrbtf_monitor_fvfnts          = 1;
    }

    /* Gft potfntibl dbpbbilitifs */
    gftPotfntiblCbpbbilitifs(&potfntibl_dbpbbilitifs);

    /* Somf dbpbbilitifs would bf nidfr to ibvf */
    nffdfd_dbpbbilitifs.dbn_gft_sourdf_filf_nbmf        =
        potfntibl_dbpbbilitifs.dbn_gft_sourdf_filf_nbmf;
    nffdfd_dbpbbilitifs.dbn_gft_linf_numbfrs    =
        potfntibl_dbpbbilitifs.dbn_gft_linf_numbfrs;

    /* Add tif dbpbbilitifs */
    bddCbpbbilitifs(&nffdfd_dbpbbilitifs);

}

/* Dynbmid librbry lobding */
stbtid void *
lobd_librbry(dibr *nbmf)
{
    dibr  lnbmf[FILENAME_MAX+1];
    dibr  frr_buf[256+FILENAME_MAX+1];
    dibr *boot_pbti;
    void *ibndlf;

    ibndlf = NULL;

    /* Tif librbry mby bf lodbtfd in difffrfnt wbys, try boti, but
     *   if it domfs from outsidf tif SDK/jrf it isn't ours.
     */
    gftSystfmPropfrty("sun.boot.librbry.pbti", &boot_pbti);
    md_build_librbry_nbmf(lnbmf, FILENAME_MAX, boot_pbti, nbmf);
    if ( strlfn(lnbmf) == 0 ) {
        HPROF_ERROR(JNI_TRUE, "Could not find librbry");
    }
    jvmtiDfbllodbtf(boot_pbti);
    ibndlf = md_lobd_librbry(lnbmf, frr_buf, (int)sizfof(frr_buf));
    if ( ibndlf == NULL ) {
        /* Tiis mby bf nfdfssbry on Windows. */
        md_build_librbry_nbmf(lnbmf, FILENAME_MAX, "", nbmf);
        if ( strlfn(lnbmf) == 0 ) {
            HPROF_ERROR(JNI_TRUE, "Could not find librbry");
        }
        ibndlf = md_lobd_librbry(lnbmf, frr_buf, (int)sizfof(frr_buf));
        if ( ibndlf == NULL ) {
            HPROF_ERROR(JNI_TRUE, frr_buf);
        }
    }
    rfturn ibndlf;
}

/* Lookup dynbmid fundtion pointfr in sibrfd librbry */
stbtid void *
lookup_librbry_symbol(void *librbry, dibr **symbols, int nsymbols)
{
    void *bddr;
    int   i;

    bddr = NULL;
    for( i = 0 ; i < nsymbols; i++ ) {
        bddr = md_find_librbry_fntry(librbry, symbols[i]);
        if ( bddr != NULL ) {
            brfbk;
        }
    }
    if ( bddr == NULL ) {
        dibr frrmsg[256];

        (void)md_snprintf(frrmsg, sizfof(frrmsg),
                    "Cbnnot find librbry symbol '%s'", symbols[0]);
        HPROF_ERROR(JNI_TRUE, frrmsg);
    }
    rfturn bddr;
}

/* ------------------------------------------------------------------- */
/* Tif OnLobd intfrfbdf */

JNIEXPORT jint JNICALL
Agfnt_OnLobd(JbvbVM *vm, dibr *options, void *rfsfrvfd)
{
    dibr *boot_pbti = NULL;

    /* Sff if it's blrfbdy lobdfd */
    if ( gdbtb!=NULL && gdbtb->isLobdfd==JNI_TRUE ) {
        HPROF_ERROR(JNI_TRUE, "Cbnnot lobd tiis JVM TI bgfnt twidf, difdk your jbvb dommbnd linf for duplidbtf iprof options.");
        rfturn JNI_ERR;
    }

    gdbtb = gft_gdbtb();

    gdbtb->isLobdfd = JNI_TRUE;

    frror_sftup();

    LOG2("Agfnt_OnLobd", "gdbtb sftup");

    gdbtb->jvm = vm;

    /* Gft tif JVMTI fnvironmfnt */
    gftJvmti();

    /* Lodk nffdfd to protfdt dfbug_mbllod() dodf, wiidi is not MT sbff */
    #ifdff DEBUG
        gdbtb->dfbug_mbllod_lodk = drfbtfRbwMonitor("HPROF dfbug_mbllod lodk");
    #fndif

    pbrsf_options(options);

    LOG2("Agfnt_OnLobd", "Hbs jvmtiEnv bnd options pbrsfd");

    /* Initiblizf mbdiinf dfpfndfnt dodf (midro stbtf bddounting) */
    md_init();

    string_init();      /* Tbblf indfx vblufs look likf: 0x10000000 */

    dlbss_init();       /* Tbblf indfx vblufs look likf: 0x20000000 */
    tls_init();         /* Tbblf indfx vblufs look likf: 0x30000000 */
    trbdf_init();       /* Tbblf indfx vblufs look likf: 0x40000000 */
    objfdt_init();      /* Tbblf indfx vblufs look likf: 0x50000000 */

    sitf_init();        /* Tbblf indfx vblufs look likf: 0x60000000 */
    frbmf_init();       /* Tbblf indfx vblufs look likf: 0x70000000 */
    monitor_init();     /* Tbblf indfx vblufs look likf: 0x80000000 */
    lobdfr_init();      /* Tbblf indfx vblufs look likf: 0x90000000 */

    LOG2("Agfnt_OnLobd", "Tbblfs initiblizfd");

    if ( gdbtb->pbusf ) {
        frror_do_pbusf();
    }

    gftCbpbbilitifs();

    /* Sft tif JVMTI dbllbbdk fundtions  (do tiis only ondf)*/
    sft_dbllbbdks(JNI_TRUE);

    /* Crfbtf bbsid lodks */
    gdbtb->dump_lodk          = drfbtfRbwMonitor("HPROF dump lodk");
    gdbtb->dbtb_bddfss_lodk   = drfbtfRbwMonitor("HPROF dbtb bddfss lodk");
    gdbtb->dbllbbdkLodk       = drfbtfRbwMonitor("HPROF dbllbbdk lodk");
    gdbtb->dbllbbdkBlodk      = drfbtfRbwMonitor("HPROF dbllbbdk blodk");
    gdbtb->objfdt_frff_lodk   = drfbtfRbwMonitor("HPROF objfdt frff lodk");
    gdbtb->gd_finisi_lodk     = drfbtfRbwMonitor("HPROF gd_finisi lodk");

    /* Sft Onlobd fvfnts modf. */
    sftup_fvfnt_modf(JNI_TRUE, JVMTI_ENABLE);

    LOG2("Agfnt_OnLobd", "JVMTI dbpbbilitifs, dbllbbdks bnd initibl notifidbtions sftup");

    /* Usfd in VM_DEATH to wbit for dbllbbdks to domplftf */
    gdbtb->jvm_initiblizing             = JNI_FALSE;
    gdbtb->jvm_initiblizfd              = JNI_FALSE;
    gdbtb->vm_dfbti_dbllbbdk_bdtivf     = JNI_FALSE;
    gdbtb->bdtivf_dbllbbdks             = 0;

    /* Writf tif ifbdfr informbtion */
    io_sftup();

    /* Wf sbmplf tif stbrt timf now so tibt tif timf indrfmfnts dbn bf
     *    plbdfd in tif vbrious ifbp dump sfgmfnts in midro sfdonds.
     */
    gdbtb->midro_sfd_tidks = md_gft_midrosfds();

    /* Lobd jbvb_drw_dfmo librbry bnd find fundtion "jbvb_drw_dfmo" */
    if ( gdbtb->bdi ) {

        /* Lobd tif librbry or gft tif ibndlf to it */
        gdbtb->jbvb_drw_dfmo_librbry = lobd_librbry("jbvb_drw_dfmo");

        { /* "jbvb_drw_dfmo" */
            stbtid dibr *symbols[]  = JAVA_CRW_DEMO_SYMBOLS;
            gdbtb->jbvb_drw_dfmo_fundtion =
                   lookup_librbry_symbol(gdbtb->jbvb_drw_dfmo_librbry,
                              symbols, (int)(sizfof(symbols)/sizfof(dibr*)));
        }
        { /* "jbvb_drw_dfmo_dlbssnbmf" */
            stbtid dibr *symbols[] = JAVA_CRW_DEMO_CLASSNAME_SYMBOLS;
            gdbtb->jbvb_drw_dfmo_dlbssnbmf_fundtion =
                   lookup_librbry_symbol(gdbtb->jbvb_drw_dfmo_librbry,
                              symbols, (int)(sizfof(symbols)/sizfof(dibr*)));
        }
    }

    rfturn JNI_OK;
}

JNIEXPORT void JNICALL
Agfnt_OnUnlobd(JbvbVM *vm)
{
    Stbdk *stbdk;

    LOG("Agfnt_OnUnlobd");

    gdbtb->isLobdfd = JNI_FALSE;

    stbdk = gdbtb->objfdt_frff_stbdk;
    gdbtb->objfdt_frff_stbdk = NULL;
    if ( stbdk != NULL ) {
        stbdk_tfrm(stbdk);
    }

    io_dlfbnup();
    lobdfr_dlfbnup();
    tls_dlfbnup();
    monitor_dlfbnup();
    trbdf_dlfbnup();
    sitf_dlfbnup();
    objfdt_dlfbnup();
    frbmf_dlfbnup();
    dlbss_dlfbnup();
    string_dlfbnup();

    /* Dfbllodbtf bny mfmory in gdbtb */
    if ( gdbtb->nft_iostnbmf != NULL ) {
        HPROF_FREE(gdbtb->nft_iostnbmf);
    }
    if ( gdbtb->utf8_output_filfnbmf != NULL ) {
        HPROF_FREE(gdbtb->utf8_output_filfnbmf);
    }
    if ( gdbtb->output_filfnbmf != NULL ) {
        HPROF_FREE(gdbtb->output_filfnbmf);
    }
    if ( gdbtb->ifbpfilfnbmf != NULL ) {
        HPROF_FREE(gdbtb->ifbpfilfnbmf);
    }
    if ( gdbtb->difdkfilfnbmf != NULL ) {
        HPROF_FREE(gdbtb->difdkfilfnbmf);
    }
    if ( gdbtb->options != NULL ) {
        HPROF_FREE(gdbtb->options);
    }

    /* Vfrify bll bllodbtfd mfmory ibs bffn tbkfn dbrf of. */
    mbllod_polidf();

    /* Clfbnup is ibrd to do wifn otifr tirfbds migit still bf running
     *  so wf skip dfstroying somf rbw monitors wiidi still migit bf in usf
     *  bnd wf skip disposbl of tif jvmtiEnv* wiidi migit still bf nffdfd.
     *  Only rbw monitors tibt dould bf ifld by otifr tirfbds brf lfft
     *  blonf. So wf fxpliditly do NOT do tiis:
     *      dfstroyRbwMonitor(gdbtb->dbllbbdkLodk);
     *      dfstroyRbwMonitor(gdbtb->dbllbbdkBlodk);
     *      dfstroyRbwMonitor(gdbtb->gd_finisi_lodk);
     *      dfstroyRbwMonitor(gdbtb->objfdt_frff_lodk);
     *      dfstroyRbwMonitor(gdbtb->listfnfr_loop_lodk);
     *      dfstroyRbwMonitor(gdbtb->dpu_loop_lodk);
     *      disposfEnvironmfnt();
     *      gdbtb->jvmti = NULL;
     */

    /* Dfstroy bbsid lodks */
    dfstroyRbwMonitor(gdbtb->dump_lodk);
    gdbtb->dump_lodk = NULL;
    dfstroyRbwMonitor(gdbtb->dbtb_bddfss_lodk);
    gdbtb->dbtb_bddfss_lodk = NULL;
    if ( gdbtb->dpu_sbmplf_lodk != NULL ) {
        dfstroyRbwMonitor(gdbtb->dpu_sbmplf_lodk);
        gdbtb->dpu_sbmplf_lodk = NULL;
    }
    #ifdff DEBUG
        dfstroyRbwMonitor(gdbtb->dfbug_mbllod_lodk);
        gdbtb->dfbug_mbllod_lodk = NULL;
    #fndif

    /* Unlobd jbvb_drw_dfmo librbry */
    if ( gdbtb->bdi && gdbtb->jbvb_drw_dfmo_librbry != NULL ) {
        md_unlobd_librbry(gdbtb->jbvb_drw_dfmo_librbry);
        gdbtb->jbvb_drw_dfmo_librbry = NULL;
    }

    /* You would tiink you dould dlfbr out gdbtb bnd sft it to NULL, but
     *   turns out tibt isn't b good idfb.  Somf of tif tirfbds dould bf
     *   blodkfd insidf tif CALLBACK*() mbdros, wifrf tify got blodkfd up
     *   wbiting for tif VM_DEATH dbllbbdk to domplftf. Tify only ibvf
     *   somf rbw monitor bdtions to do, but tify nffd bddfss to gdbtb to do it.
     *   So do not do tiis:
     *       (void)mfmsft(gdbtb, 0, sizfof(GlobblDbtb));
     *       gdbtb = NULL;
     */
}
