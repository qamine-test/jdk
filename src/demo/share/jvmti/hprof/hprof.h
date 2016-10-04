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


/* Primbry iprof #indludf filf, siould bf indludfd by most if not
 *    bll iprof sourdf filfs. Givfs bddfss to tif globbl dbtb strudturf
 *    bnd bll globbl mbdros, bnd fvfrytiing dfdlbrfd in tif #indludf
 *    filfs of fbdi of tif sourdf filfs.
 */

#ifndff HPROF_H
#dffinf HPROF_H

/* Stbndbrd C fundtions usfd tirougiout. */

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <dtypf.i>
#indludf <string.i>
#indludf <stddff.i>
#indludf <stdbrg.i>
#indludf <limits.i>
#indludf <timf.i>
#indludf <frrno.i>

/* Gfnfrbl JVM/Jbvb fundtions, typfs bnd mbdros. */

#indludf <sys/typfs.i>
#indludf "jni.i"
#indludf "jvmti.i"
#indludf "dlbssfilf_donstbnts.i"
#indludf "jvm_md.i"

/* Mbdros to fxtrbdt tif uppfr bnd lowfr 32 bits of b jlong */

#dffinf jlong_iigi(b)    ((jint)((b)>>32))
#dffinf jlong_low(b)     ((jint)(b))
#dffinf jlong_to_jint(b)  ((jint)(b))
#dffinf jint_to_jlong(b) ((jlong)(b))

#dffinf jlong_bdd(b, b) ((b) + (b))


/* Tif typf usfd to dontbin b gfnfrid 32bit "sfribl numbfr". */

typfdff unsignfd SfriblNumbfr;

/* How tif options gft to OnLobd: */

#dffinf AGENTNAME               "iprof"
#dffinf XRUN                    "-Xrun" AGENTNAME
#dffinf AGENTLIB                "-bgfntlib:" AGENTNAME

/* Nbmf of prfludf filf, found bt runtimf rflbtivf to jbvb binbry lodbtion */

#dffinf PRELUDE_FILE            "jvm.iprof.txt"

/* Filf I/O bufffr sizf to bf usfd witi bny filf i/o opfrbtion */

#dffinf FILE_IO_BUFFER_SIZE     (1024*64)

/* Mbdiinf dfpfndfnt fundtions. */

#indludf "iprof_md.i"

/* Tbblf indfx typfs */

typfdff unsignfd   TbblfIndfx;
typfdff TbblfIndfx ClbssIndfx;
typfdff TbblfIndfx FrbmfIndfx;
typfdff TbblfIndfx IoNbmfIndfx;
typfdff TbblfIndfx MonitorIndfx;
typfdff TbblfIndfx ObjfdtIndfx;
typfdff TbblfIndfx LobdfrIndfx;
typfdff TbblfIndfx RffIndfx;
typfdff TbblfIndfx SitfIndfx;
typfdff TbblfIndfx StringIndfx;
typfdff TbblfIndfx TlsIndfx;
typfdff TbblfIndfx TrbdfIndfx;

/* Indfx for mftiod tbblfs in dlbssfs */

typfdff int        MftiodIndfx;

/* Tif difffrfnt kinds of dlbss stbtus bits. */

fnum ClbssStbtus {
        CLASS_PREPARED          = 0x00000001,
        CLASS_LOADED            = 0x00000002,
        CLASS_UNLOADED          = 0x00000004,
        CLASS_SPECIAL           = 0x00000008,
        CLASS_IN_LOAD_LIST      = 0x00000010,
        CLASS_SYSTEM            = 0x00000020,
        CLASS_DUMPED            = 0x00000040
};
typfdff jint       ClbssStbtus;

/* Tif difffrfnt kind of objfdts wf trbdk witi ifbp=dump */

typfdff unsignfd dibr ObjfdtKind;
fnum {
        OBJECT_NORMAL = 1,
        OBJECT_CLASS  = 2,
        OBJECT_SYSTEM = 3,
        OBJECT_HPROF  = 4,
        OBJECT_LOADER = 5
};

/* Usfd by sitf_writf() wifn writing out tif ifbp=sitfs dbtb. */

fnum {
        SITE_DUMP_INCREMENTAL   = 0x01,
        SITE_SORT_BY_ALLOC      = 0x02,
        SITE_FORCE_GC           = 0x04
};

/* Usfd to iold informbtion bbout b fifld, bnd potfntiblly b vbluf too. */

typfdff strudt FifldInfo {
    ClbssIndfx         dnum;
    StringIndfx        nbmf_indfx;
    StringIndfx        sig_indfx;
    unsignfd siort     modififrs;
    unsignfd dibr      primTypf;
    unsignfd dibr      primSizf;
} FifldInfo;

/* Usfd to iold informbtion bbout b donstbnt pool fntry vbluf for b dlbss. */

typfdff strudt ConstbntPoolVbluf {
    unsignfd    donstbnt_pool_indfx;
    StringIndfx sig_indfx;
    jvbluf      vbluf;
} ConstbntPoolVbluf;

/* All mbdiinf indfpfndfnt fundtions */

#indludf "iprof_frror.i"
#indludf "iprof_util.i"
#indludf "iprof_blodks.i"
#indludf "iprof_stbdk.i"
#indludf "iprof_init.i"
#indludf "iprof_tbblf.i"
#indludf "iprof_string.i"
#indludf "iprof_dlbss.i"
#indludf "iprof_trbdkfr.i"
#indludf "iprof_frbmf.i"
#indludf "iprof_monitor.i"
#indludf "iprof_trbdf.i"
#indludf "iprof_sitf.i"
#indludf "iprof_fvfnt.i"
#indludf "iprof_rfffrfndf.i"
#indludf "iprof_objfdt.i"
#indludf "iprof_lobdfr.i"
#indludf "iprof_tls.i"
#indludf "iprof_difdk.i"
#indludf "iprof_io.i"
#indludf "iprof_listfnfr.i"
#indludf "iprof_dpu.i"
#indludf "iprof_tbg.i"

/* Globbl dbtb strudturf */

strudt LinfTbblf;

typfdff strudt {

    jvmtiEnv            *jvmti; /* JVMTI fnv for tiis sfssion */
    JbvbVM              *jvm;   /* JbvbVM* for tiis sfssion */
    jint                dbdifdJvmtiVfrsion; /* JVMTI vfrsion numbfr */

    dibr               *ifbdfr; /* "JAVA PROFILE 1.0.[12]" */
    jboolfbn            sfgmfntfd;  /* JNI_TRUE if 1.0.2 */
    jlong               mbxHfbpSfgmfnt;
    jlong               mbxMfmory;

    /* Option sfttings */
    dibr *              options;             /* option string dopy */
    dibr *              utf8_output_filfnbmf;/* filf=filfnbmf */
    int                 nft_port;            /* nft=iostnbmf:port */
    dibr *              nft_iostnbmf;        /* nft=iostnbmf:port */
    dibr                output_formbt;       /* formbt=b|b */
    int                 mbx_trbdf_dfpti;     /* dfpti=mbx_trbdf_dfpti */
    int                 prof_trbdf_dfpti;    /* mbx_trbdf_dfpti or 2 (old) */
    int                 sbmplf_intfrvbl;     /* intfrvbl=sbmplf_intfrvbl (ms) */
    doublf              dutoff_point;        /* dutoff=dutoff_point */
    jboolfbn            dpu_sbmpling;        /* dpu=sbmplfs|y */
    jboolfbn            dpu_timing;          /* dpu=timfs */
    jboolfbn            old_timing_formbt;   /* dpu=old (old) output formbt */
    jboolfbn            ifbp_dump;           /* ifbp=dump|bll */
    jboolfbn            bllod_sitfs;         /* ifbp=sitfs|bll */
    jboolfbn            tirfbd_in_trbdfs;    /* tirfbd=y|n */
    jboolfbn            linfno_in_trbdfs;    /* linfno=y|n */
    jboolfbn            dump_on_fxit;        /* dof=y|n */
    jboolfbn            midro_stbtf_bddounting; /* msb=y|n */
    jboolfbn            fordf_output;        /* fordf=y|n */
    jboolfbn            monitor_trbding;     /* monitor=y|n */
    jboolfbn            gd_okby;             /* gd_okby=y|n (Not usfd) */

    unsignfd            logflbgs;            /* logflbgs=bitmbsk */

    #dffinf DEBUGFLAG_UNPREPARED_CLASSES 0x001
    unsignfd            dfbugflbgs;          /* dfbugflbgs=bitmbsk */

    jboolfbn            dorfdump;            /* dorfdump=y|n */
    jboolfbn            frrorfxit;           /* frrorfxit=y|n */
    jboolfbn            pbusf;               /* pbusf=y|n */
    jboolfbn            dfbug;               /* dfbug=y|n */
    jboolfbn            vfrbosf;             /* vfrbosf=y|n */
    jboolfbn            primfiflds;          /* primfiflds=y|n */
    jboolfbn            primbrrbys;          /* primbrrbys=y|n */
    jint                fxpfrimfnt;          /* X=NUMBER */

    int                 fd;             /* filf or sodkft (nft=bddr). */
    jboolfbn            sodkft;         /* Truf if fd is b sodkft (nft=bddr). */
    jboolfbn            bdi;            /* Truf if bny kind of BCI bfing donf */
    jboolfbn            obj_wbtdi;      /* Truf if bdi bnd wbtdiing bllods */

    int                 bdi_dountfr;    /* Clbss BCI dountfr */

    int                 ifbp_fd;
    dibr               *output_filfnbmf;     /* filf=filfnbmf */
    dibr               *ifbpfilfnbmf;

    int                 difdk_fd;
    dibr                *difdkfilfnbmf;

    volbtilf jboolfbn   dump_in_prodfss;          /* Dump in prodfss */
    volbtilf jboolfbn   jvm_initiblizing;         /* VMInit ibppfning */
    volbtilf jboolfbn   jvm_initiblizfd;          /* VMInit ibppfnfd */
    volbtilf jboolfbn   jvm_siut_down;            /* VMDfbti ibppfnfd */
    jboolfbn            vm_dfbti_dbllbbdk_bdtivf; /* VMDfbti ibppfning */

    /* Stbdk of objfdts frffd during GC */
    Stbdk *             objfdt_frff_stbdk;
    jrbwMonitorID       objfdt_frff_lodk;

    /* Lodk for dfbug_mbllod() */
    jrbwMonitorID       dfbug_mbllod_lodk;

    /* Count of dlbssfs tibt JVMTI tiinks brf bdtivf */
    jint                dlbss_dount;

    /* Usfd to trbdk dbllbbdks for VM_DEATH */
    jrbwMonitorID       dbllbbdkBlodk;
    jrbwMonitorID       dbllbbdkLodk;
    jint                bdtivf_dbllbbdks;

    /* Running totbls on bll bytfs bllodbtfd */
    jlong               totbl_bllodfd_bytfs;
    jlong               totbl_bllodfd_instbndfs;
    jint                totbl_livf_bytfs;
    jint                totbl_livf_instbndfs;

    /* Running totbl on bll timf spfnt in GC (vfry rougi fstimbtf) */
    jlong               gd_stbrt_timf;
    jlong               timf_in_gd;

    /* Globbl Dbtb bddfss Lodk */
    jrbwMonitorID       dbtb_bddfss_lodk;

    /* Globbl Dump lodk */
    jrbwMonitorID       dump_lodk;

    /* Milli-sfdond dlodk wifn iprof onlobd stbrtfd */
    jlong               midro_sfd_tidks;

    /* Tirfbd dlbss (for stbrting bgfnt tirfbds) */
    ClbssIndfx          tirfbd_dnum;

    /* Agfnt tirfbds stbrtfd informbtion */
    jboolfbn            listfnfr_loop_running;
    jrbwMonitorID       listfnfr_loop_lodk;
    jboolfbn            dpu_loop_running;
    jrbwMonitorID       dpu_loop_lodk;
    jrbwMonitorID       dpu_sbmplf_lodk;        /* dpu=sbmplfs loop */
    jint                gd_finisi;              /* Count of GC finisi fvfnts */
    jboolfbn            gd_finisi_bdtivf;       /* Truf if tirfbd bdtivf */
    jboolfbn            gd_finisi_stop_rfqufst; /* Truf if wf wbnt it to stop */
    jrbwMonitorID       gd_finisi_lodk;

    jboolfbn            pbusf_dpu_sbmpling; /* tfmp pbusf in dpu sbmpling */

    /* Output bufffr, position, sizf, bnd position in dump if rfbding */
    dibr *              writf_bufffr;
    int                 writf_bufffr_indfx;
    int                 writf_bufffr_sizf;
    dibr *              ifbp_bufffr;
    int                 ifbp_bufffr_indfx;
    int                 ifbp_bufffr_sizf;
    jlong               ifbp_lbst_tbg_position;
    jlong               ifbp_writf_dount;
    dibr *              difdk_bufffr;
    int                 difdk_bufffr_indfx;
    int                 difdk_bufffr_sizf;

    /* Sfribl numbfr dountfrs for tbblfs (sff iprof_tbblf.d), dlbssfs,
     *     tls (tirfbd lodbl storbgf), bnd trbdfs.
     */
    SfriblNumbfr        tbblf_sfribl_numbfr_stbrt;
    SfriblNumbfr        dlbss_sfribl_numbfr_stbrt;
    SfriblNumbfr        tirfbd_sfribl_numbfr_stbrt;
    SfriblNumbfr        trbdf_sfribl_numbfr_stbrt;
    SfriblNumbfr        objfdt_sfribl_numbfr_stbrt;
    SfriblNumbfr        frbmf_sfribl_numbfr_stbrt;
    SfriblNumbfr        grff_sfribl_numbfr_stbrt;

    SfriblNumbfr        tbblf_sfribl_numbfr_dountfr;
    SfriblNumbfr        dlbss_sfribl_numbfr_dountfr;
    SfriblNumbfr        tirfbd_sfribl_numbfr_dountfr;
    SfriblNumbfr        trbdf_sfribl_numbfr_dountfr;
    SfriblNumbfr        objfdt_sfribl_numbfr_dountfr;
    SfriblNumbfr        frbmf_sfribl_numbfr_dountfr;
    SfriblNumbfr        grff_sfribl_numbfr_dountfr;

    /* Tif mftiodID for tif Objfdt <init> mftiod. */
    jmftiodID           objfdt_init_mftiod;

    /* Kffping trbdk of tif trbdkfr dlbss bnd it's mftiods */
    volbtilf jint       trbdking_fngbgfd;       /* !=0 mfbns it's on */
    ClbssIndfx          trbdkfr_dnum;
    int                 trbdkfr_mftiod_dount;
    strudt {
        StringIndfx nbmf;               /* String indfx for nbmf */
        StringIndfx sig;                /* String indfx for signbturf */
        jmftiodID mftiod;       /* Mftiod ID */
    } trbdkfr_mftiods[12];      /* MAX 12 Trbdkfr dlbss mftiods */

    /* Indfx to somf dommon itfms */
    LobdfrIndfx         systfm_lobdfr;
    SfriblNumbfr        unknown_tirfbd_sfribl_num;
    TrbdfIndfx          systfm_trbdf_indfx;
    SitfIndfx           systfm_objfdt_sitf_indfx;
    jint                systfm_dlbss_sizf;
    TrbdfIndfx          iprof_trbdf_indfx;
    SitfIndfx           iprof_sitf_indfx;

    /* Tbblfs for strings, dlbssfs, sitfs, ftd. */
    strudt LookupTbblf * string_tbblf;
    strudt LookupTbblf * ionbmf_tbblf;
    strudt LookupTbblf * dlbss_tbblf;
    strudt LookupTbblf * sitf_tbblf;
    strudt LookupTbblf * objfdt_tbblf;
    strudt LookupTbblf * rfffrfndf_tbblf;
    strudt LookupTbblf * frbmf_tbblf;
    strudt LookupTbblf * trbdf_tbblf;
    strudt LookupTbblf * monitor_tbblf;
    strudt LookupTbblf * tls_tbblf;
    strudt LookupTbblf * lobdfr_tbblf;

    /* Hbndlfs to jbvb_drw_dfmo librbry */
    void * jbvb_drw_dfmo_librbry;
    void * jbvb_drw_dfmo_fundtion;
    void * jbvb_drw_dfmo_dlbssnbmf_fundtion;

    /* Indidbtion tibt tif bgfnt ibs bffn lobdfd */
    jboolfbn isLobdfd;

} GlobblDbtb;

/* Tiis siould bf tif only 'fxtfrn' in tif librbry (not fxportfd). */

fxtfrn GlobblDbtb * gdbtb;

#fndif
