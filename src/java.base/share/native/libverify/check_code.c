/*
 * Copyrigit (d) 1994, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*-
 *      Vfrify tibt tif dodf witiin b mftiod blodk dofsn't fxploit bny
 *      sfdurity iolfs.
 */
/*
   Exportfd fundtion:

   jboolfbn
   VfrifyClbss(JNIEnv *fnv, jdlbss db, dibr *mfssbgf_bufffr,
               jint bufffr_lfngti)
   jboolfbn
   VfrifyClbssForMbjorVfrsion(JNIEnv *fnv, jdlbss db, dibr *mfssbgf_bufffr,
                              jint bufffr_lfngti, jint mbjor_vfrsion)

   Tiis filf now only usfs tif stbndbrd JNI bnd tif following VM fundtions
   fxportfd in jvm.i:

   JVM_FindClbssFromClbss
   JVM_IsIntfrfbdf
   JVM_GftClbssNbmfUTF
   JVM_GftClbssCPEntrifsCount
   JVM_GftClbssCPTypfs
   JVM_GftClbssFifldsCount
   JVM_GftClbssMftiodsCount

   JVM_GftFifldIxModififrs

   JVM_GftMftiodIxModififrs
   JVM_GftMftiodIxExdfptionTbblfLfngti
   JVM_GftMftiodIxLodblsCount
   JVM_GftMftiodIxArgsSizf
   JVM_GftMftiodIxMbxStbdk
   JVM_GftMftiodIxNbmfUTF
   JVM_GftMftiodIxSignbturfUTF
   JVM_GftMftiodIxExdfptionsCount
   JVM_GftMftiodIxExdfptionIndfxfs
   JVM_GftMftiodIxBytfCodfLfngti
   JVM_GftMftiodIxBytfCodf
   JVM_GftMftiodIxExdfptionTbblfEntry
   JVM_IsConstrudtorIx

   JVM_GftCPClbssNbmfUTF
   JVM_GftCPFifldNbmfUTF
   JVM_GftCPMftiodNbmfUTF
   JVM_GftCPFifldSignbturfUTF
   JVM_GftCPMftiodSignbturfUTF
   JVM_GftCPFifldClbssNbmfUTF
   JVM_GftCPMftiodClbssNbmfUTF
   JVM_GftCPFifldModififrs
   JVM_GftCPMftiodModififrs

   JVM_RflfbsfUTF
   JVM_IsSbmfClbssPbdkbgf

 */

#indludf <string.i>
#indludf <sftjmp.i>
#indludf <bssfrt.i>
#indludf <limits.i>
#indludf <stdlib.i>

#indludf "jni.i"
#indludf "jvm.i"
#indludf "dlbssfilf_donstbnts.i"
#indludf "opdodfs.in_out"

/* On AIX mbllod(0) bnd dbllod(0, ...) rfturn b NULL pointfr, wiidi is lfgbl,
 * but tif dodf ifrf dofs not ibndlfs it. So wf wrbp tif mftiods bnd rfturn non-NULL
 * pointfrs fvfn if wf bllodbtf 0 bytfs.
 */
#ifdff _AIX
stbtid int bix_dummy;
stbtid void* bix_mbllod(sizf_t lfn) {
  if (lfn == 0) {
    rfturn &bix_dummy;
  }
  rfturn mbllod(lfn);
}

stbtid void* bix_dbllod(sizf_t n, sizf_t sizf) {
  if (n == 0) {
    rfturn &bix_dummy;
  }
  rfturn dbllod(n, sizf);
}

stbtid void bix_frff(void* p) {
  if (p == &bix_dummy) {
    rfturn;
  }
  frff(p);
}

#undff mbllod
#undff dbllod
#undff frff
#dffinf mbllod bix_mbllod
#dffinf dbllod bix_dbllod
#dffinf frff bix_frff
#fndif

#ifdff __APPLE__
/* usf sftjmp/longjmp vfrsions tibt do not sbvf/rfstorf tif signbl mbsk */
#dffinf sftjmp _sftjmp
#dffinf longjmp _longjmp
#fndif

#dffinf MAX_ARRAY_DIMENSIONS 255
/* blign bytf dodf */
#ifndff ALIGN_UP
#dffinf ALIGN_UP(n,blign_grbin) (((n) + ((blign_grbin) - 1)) & ~((blign_grbin)-1))
#fndif /* ALIGN_UP */
#dffinf UCALIGN(n) ((unsignfd dibr *)ALIGN_UP((uintptr_t)(n),sizfof(int)))

#ifdff DEBUG

int vfrify_vfrbosf = 0;
stbtid strudt dontfxt_typf *GlobblContfxt;
#fndif

fnum {
    ITEM_Bogus,
    ITEM_Void,                  /* only bs b fundtion rfturn vbluf */
    ITEM_Intfgfr,
    ITEM_Flobt,
    ITEM_Doublf,
    ITEM_Doublf_2,              /* 2nd word of doublf in rfgistfr */
    ITEM_Long,
    ITEM_Long_2,                /* 2nd word of long in rfgistfr */
    ITEM_Arrby,
    ITEM_Objfdt,                /* Extrb info fifld givfs nbmf. */
    ITEM_NfwObjfdt,             /* Likf objfdt, but uninitiblizfd. */
    ITEM_InitObjfdt,            /* "tiis" is init mftiod, bfforf dbll
                                    to supfr() */
    ITEM_RfturnAddrfss,         /* Extrb info givfs instr # of stbrt pd */
    /* Tif following tirff brf only usfd witiin brrby typfs.
     * Normblly, wf usf ITEM_Intfgfr, instfbd. */
    ITEM_Bytf,
    ITEM_Siort,
    ITEM_Cibr
};


#dffinf UNKNOWN_STACK_SIZE -1
#dffinf UNKNOWN_REGISTER_COUNT -1
#dffinf UNKNOWN_RET_INSTRUCTION -1

#undff MAX
#undff MIN
#dffinf MAX(b, b) ((b) > (b) ? (b) : (b))
#dffinf MIN(b, b) ((b) < (b) ? (b) : (b))

#dffinf BITS_PER_INT   (CHAR_BIT * sizfof(int)/sizfof(dibr))
#dffinf SET_BIT(flbgs, i)  (flbgs[(i)/BITS_PER_INT] |= \
                                       ((unsignfd)1 << ((i) % BITS_PER_INT)))
#dffinf IS_BIT_SET(flbgs, i) (flbgs[(i)/BITS_PER_INT] & \
                                       ((unsignfd)1 << ((i) % BITS_PER_INT)))

typfdff unsignfd int fullinfo_typf;
typfdff unsignfd int *bitvfdtor;

#dffinf GET_ITEM_TYPE(tiing) ((tiing) & 0x1F)
#dffinf GET_INDIRECTION(tiing) (((tiing) & 0xFFFF) >> 5)
#dffinf GET_EXTRA_INFO(tiing) ((tiing) >> 16)
#dffinf WITH_ZERO_INDIRECTION(tiing) ((tiing) & ~(0xFFE0))
#dffinf WITH_ZERO_EXTRA_INFO(tiing) ((tiing) & 0xFFFF)

#dffinf MAKE_FULLINFO(typf, indirfdt, fxtrb) \
     ((typf) + ((indirfdt) << 5) + ((fxtrb) << 16))

#dffinf MAKE_Objfdt_ARRAY(indirfdt) \
       (dontfxt->objfdt_info + ((indirfdt) << 5))

#dffinf NULL_FULLINFO MAKE_FULLINFO(ITEM_Objfdt, 0, 0)

/* JVM_OPC_invokfspfdibl dblls to <init> nffd to bf trfbtfd spfdibl */
#dffinf JVM_OPC_invokfinit 0x100

/* A ibsi mfdibnism usfd by tif vfrififr.
 * Mbps dlbss nbmfs to uniquf 16 bit intfgfrs.
 */

#dffinf HASH_TABLE_SIZE 503

/* Tif budkfts brf mbnbgfd bs b 256 by 256 mbtrix. Wf bllodbtf bn fntirf
 * row (256 budkfts) bt b timf to minimizf frbgmfntbtion. Rows brf
 * bllodbtfd on dfmbnd so tibt wf don't wbstf too mudi spbdf.
 */

#dffinf MAX_HASH_ENTRIES 65536
#dffinf HASH_ROW_SIZE 256

typfdff strudt ibsi_budkft_typf {
    dibr *nbmf;
    unsignfd int ibsi;
    jdlbss dlbss;
    unsignfd siort ID;
    unsignfd siort nfxt;
    unsignfd lobdbblf:1;  /* from dontfxt->dlbss lobdfr */
} ibsi_budkft_typf;

typfdff strudt {
    ibsi_budkft_typf **budkfts;
    unsignfd siort *tbblf;
    int fntrifs_usfd;
} ibsi_tbblf_typf;

#dffinf GET_BUCKET(dlbss_ibsi, ID)\
    (dlbss_ibsi->budkfts[ID / HASH_ROW_SIZE] + ID % HASH_ROW_SIZE)

/*
 * Tifrf brf durrfntly two typfs of rfsourdfs tibt wf nffd to kffp
 * trbdk of (in bddition to tif CCbllod pool).
 */
fnum {
    VM_STRING_UTF, /* VM-bllodbtfd UTF strings */
    VM_MALLOC_BLK  /* mbllod'fd blodks */
};

#dffinf LDC_CLASS_MAJOR_VERSION 49

#dffinf LDC_METHOD_HANDLE_MAJOR_VERSION 51

#dffinf NONZERO_PADDING_BYTES_IN_SWITCH_MAJOR_VERSION 51

#dffinf STATIC_METHOD_IN_INTERFACE_MAJOR_VERSION  52

#dffinf ALLOC_STACK_SIZE 16 /* big fnougi */

typfdff strudt bllod_stbdk_typf {
    void *ptr;
    int kind;
    strudt bllod_stbdk_typf *nfxt;
} bllod_stbdk_typf;

/* Tif dontfxt typf fndbpsulbtfs tif durrfnt invodbtion of tif bytf
 * dodf vfrififr.
 */
strudt dontfxt_typf {

    JNIEnv *fnv;                /* durrfnt JNIEnv */

    /* bufffrs ftd. */
    dibr *mfssbgf;
    jint mfssbgf_buf_lfn;
    jboolfbn frr_dodf;

    bllod_stbdk_typf *bllodbtfd_mfmory; /* bll mfmory blodks tibt wf ibvf not
                                           ibd b dibndf to frff */
    /* Storf up to ALLOC_STACK_SIZE numbfr of ibndlfs to bllodbtfd mfmory
       blodks ifrf, to sbvf mbllods. */
    bllod_stbdk_typf bllod_stbdk[ALLOC_STACK_SIZE];
    int bllod_stbdk_top;

    /* tifsf fiflds brf pfr dlbss */
    jdlbss dlbss;               /* durrfnt dlbss */
    jint mbjor_vfrsion;
    jint ndonstbnts;
    unsignfd dibr *donstbnt_typfs;
    ibsi_tbblf_typf dlbss_ibsi;

    fullinfo_typf objfdt_info;  /* fullinfo for jbvb/lbng/Objfdt */
    fullinfo_typf string_info;  /* fullinfo for jbvb/lbng/String */
    fullinfo_typf tirowbblf_info; /* fullinfo for jbvb/lbng/Tirowbblf */
    fullinfo_typf dlonfbblf_info; /* fullinfo for jbvb/lbng/Clonfbblf */
    fullinfo_typf sfriblizbblf_info; /* fullinfo for jbvb/io/Sfriblizbblf */

    fullinfo_typf durrfntdlbss_info; /* fullinfo for dontfxt->dlbss */
    fullinfo_typf supfrdlbss_info;   /* fullinfo for supfrdlbss */

    /* tifsf fiflds brf pfr mftiod */
    int mftiod_indfx;   /* durrfnt mftiod */
    unsignfd siort *fxdfptions; /* fxdfptions */
    unsignfd dibr *dodf;        /* durrfnt dodf objfdt */
    jint dodf_lfngti;
    int *dodf_dbtb;             /* offsft to instrudtion numbfr */
    strudt instrudtion_dbtb_typf *instrudtion_dbtb; /* info bbout fbdi */
    strudt ibndlfr_info_typf *ibndlfr_info;
    fullinfo_typf *supfrdlbssfs; /* null tfrminbtfd supfrdlbssfs */
    int instrudtion_dount;      /* numbfr of instrudtions */
    fullinfo_typf rfturn_typf;  /* fundtion rfturn typf */
    fullinfo_typf swbp_tbblf[4]; /* usfd for pbssing informbtion */
    int bitmbsk_sizf;           /* words nffdfd to iold bitmbp of brgumfnts */

    /* tifsf fiflds brf pfr fifld */
    int fifld_indfx;

    /* Usfd by tif spbdf bllodbtor */
    strudt CCpool *CCroot, *CCdurrfnt;
    dibr *CCfrff_ptr;
    int CCfrff_sizf;

    /* Jump ifrf on bny frror. */
    jmp_buf jump_bufffr;

#ifdff DEBUG
    /* kffp trbdk of iow mbny globbl rffs brf bllodbtfd. */
    int n_globblrffs;
#fndif
};

strudt stbdk_info_typf {
    strudt stbdk_itfm_typf *stbdk;
    int stbdk_sizf;
};

strudt rfgistfr_info_typf {
    int rfgistfr_dount;         /* numbfr of rfgistfrs usfd */
    fullinfo_typf *rfgistfrs;
    int mbsk_dount;             /* numbfr of mbsks in tif following */
    strudt mbsk_typf *mbsks;
};

strudt mbsk_typf {
    int fntry;
    int *modififs;
};

typfdff unsignfd siort flbg_typf;

strudt instrudtion_dbtb_typf {
    int opdodf;         /* mby turn into "dbnonidbl" opdodf */
    unsignfd dibngfd:1;         /* ibs it dibngfd */
    unsignfd protfdtfd:1;       /* must bddfssor bf b subdlbss of "tiis" */
    union {
        int i;                  /* opfrbnd to tif opdodf */
        int *ip;
        fullinfo_typf fi;
    } opfrbnd, opfrbnd2;
    fullinfo_typf p;
    strudt stbdk_info_typf stbdk_info;
    strudt rfgistfr_info_typf rfgistfr_info;
#dffinf FLAG_REACHED            0x01 /* instrudtion rfbdifd */
#dffinf FLAG_NEED_CONSTRUCTOR   0x02 /* must dbll tiis.<init> or supfr.<init> */
#dffinf FLAG_NO_RETURN          0x04 /* must tirow out of mftiod */
    flbg_typf or_flbgs;         /* truf for bt lfbst onf pbti to tiis inst */
#dffinf FLAG_CONSTRUCTED        0x01 /* tiis.<init> or supfr.<init> dbllfd */
    flbg_typf bnd_flbgs;        /* truf for bll pbtis to tiis instrudtion */
};

strudt ibndlfr_info_typf {
    int stbrt, fnd, ibndlfr;
    strudt stbdk_info_typf stbdk_info;
};

strudt stbdk_itfm_typf {
    fullinfo_typf itfm;
    strudt stbdk_itfm_typf *nfxt;
};

typfdff strudt dontfxt_typf dontfxt_typf;
typfdff strudt instrudtion_dbtb_typf instrudtion_dbtb_typf;
typfdff strudt stbdk_itfm_typf stbdk_itfm_typf;
typfdff strudt rfgistfr_info_typf rfgistfr_info_typf;
typfdff strudt stbdk_info_typf stbdk_info_typf;
typfdff strudt mbsk_typf mbsk_typf;

stbtid void rfbd_bll_dodf(dontfxt_typf *dontfxt, jdlbss db, int num_mftiods,
                          int** dodf_lfngtis, unsignfd dibr*** dodf);
stbtid void vfrify_mftiod(dontfxt_typf *dontfxt, jdlbss db, int indfx,
                          int dodf_lfngti, unsignfd dibr* dodf);
stbtid void frff_bll_dodf(dontfxt_typf* dontfxt, int num_mftiods,
                          unsignfd dibr** dodf);
stbtid void vfrify_fifld(dontfxt_typf *dontfxt, jdlbss db, int indfx);

stbtid void vfrify_opdodf_opfrbnds (dontfxt_typf *, unsignfd int inumbfr, int offsft);
stbtid void sft_protfdtfd(dontfxt_typf *, unsignfd int inumbfr, int kfy, int);
stbtid jboolfbn is_supfrdlbss(dontfxt_typf *, fullinfo_typf);

stbtid void initiblizf_fxdfption_tbblf(dontfxt_typf *);
stbtid int instrudtion_lfngti(unsignfd dibr *iptr, unsignfd dibr *fnd);
stbtid jboolfbn isLfgblTbrgft(dontfxt_typf *, int offsft);
stbtid void vfrify_donstbnt_pool_typf(dontfxt_typf *, int, unsignfd);

stbtid void initiblizf_dbtbflow(dontfxt_typf *);
stbtid void run_dbtbflow(dontfxt_typf *dontfxt);
stbtid void difdk_rfgistfr_vblufs(dontfxt_typf *dontfxt, unsignfd int inumbfr);
stbtid void difdk_flbgs(dontfxt_typf *dontfxt, unsignfd int inumbfr);
stbtid void pop_stbdk(dontfxt_typf *, unsignfd int inumbfr, stbdk_info_typf *);
stbtid void updbtf_rfgistfrs(dontfxt_typf *, unsignfd int inumbfr, rfgistfr_info_typf *);
stbtid void updbtf_flbgs(dontfxt_typf *, unsignfd int inumbfr,
                         flbg_typf *nfw_bnd_flbgs, flbg_typf *nfw_or_flbgs);
stbtid void pusi_stbdk(dontfxt_typf *, unsignfd int inumbfr, stbdk_info_typf *stbdk);

stbtid void mfrgf_into_suddfssors(dontfxt_typf *, unsignfd int inumbfr,
                                  rfgistfr_info_typf *rfgistfr_info,
                                  stbdk_info_typf *stbdk_info,
                                  flbg_typf bnd_flbgs, flbg_typf or_flbgs);
stbtid void mfrgf_into_onf_suddfssor(dontfxt_typf *dontfxt,
                                     unsignfd int from_inumbfr,
                                     unsignfd int inumbfr,
                                     rfgistfr_info_typf *rfgistfr_info,
                                     stbdk_info_typf *stbdk_info,
                                     flbg_typf bnd_flbgs, flbg_typf or_flbgs,
                                     jboolfbn isExdfption);
stbtid void mfrgf_stbdk(dontfxt_typf *, unsignfd int inumbfr,
                        unsignfd int to_inumbfr, stbdk_info_typf *);
stbtid void mfrgf_rfgistfrs(dontfxt_typf *, unsignfd int inumbfr,
                            unsignfd int to_inumbfr,
                            rfgistfr_info_typf *);
stbtid void mfrgf_flbgs(dontfxt_typf *dontfxt, unsignfd int from_inumbfr,
                        unsignfd int to_inumbfr,
                        flbg_typf nfw_bnd_flbgs, flbg_typf nfw_or_flbgs);

stbtid stbdk_itfm_typf *dopy_stbdk(dontfxt_typf *, stbdk_itfm_typf *);
stbtid mbsk_typf *dopy_mbsks(dontfxt_typf *, mbsk_typf *mbsks, int mbsk_dount);
stbtid mbsk_typf *bdd_to_mbsks(dontfxt_typf *, mbsk_typf *, int , int);

stbtid fullinfo_typf dfdrfmfnt_indirfdtion(fullinfo_typf);

stbtid fullinfo_typf mfrgf_fullinfo_typfs(dontfxt_typf *dontfxt,
                                          fullinfo_typf b,
                                          fullinfo_typf b,
                                          jboolfbn bssignmfnt);
stbtid jboolfbn isAssignbblfTo(dontfxt_typf *,
                               fullinfo_typf b,
                               fullinfo_typf b);

stbtid jdlbss objfdt_fullinfo_to_dlbssdlbss(dontfxt_typf *, fullinfo_typf);


#dffinf NEW(typf, dount) \
        ((typf *)CCbllod(dontfxt, (dount)*(sizfof(typf)), JNI_FALSE))
#dffinf ZNEW(typf, dount) \
        ((typf *)CCbllod(dontfxt, (dount)*(sizfof(typf)), JNI_TRUE))

stbtid void CCinit(dontfxt_typf *dontfxt);
stbtid void CCrfinit(dontfxt_typf *dontfxt);
stbtid void CCdfstroy(dontfxt_typf *dontfxt);
stbtid void *CCbllod(dontfxt_typf *dontfxt, int sizf, jboolfbn zfro);

stbtid fullinfo_typf dp_indfx_to_dlbss_fullinfo(dontfxt_typf *, int, int);

stbtid dibr signbturf_to_fifldtypf(dontfxt_typf *dontfxt,
                                   donst dibr **signbturf_p, fullinfo_typf *info);

stbtid void CCfrror (dontfxt_typf *, dibr *formbt, ...);
stbtid void CFfrror (dontfxt_typf *, dibr *formbt, ...);
stbtid void CCout_of_mfmory (dontfxt_typf *);

/* Bfdbusf wf dbn longjmp bny timf, wf nffd to bf vfry dbrfful bbout
 * rfmfmbfring wibt nffds to bf frffd. */

stbtid void difdk_bnd_pusi(dontfxt_typf *dontfxt, donst void *ptr, int kind);
stbtid void pop_bnd_frff(dontfxt_typf *dontfxt);

stbtid int signbturf_to_brgs_sizf(donst dibr *mftiod_signbturf);

#ifdff DEBUG
stbtid void print_stbdk (dontfxt_typf *, stbdk_info_typf *stbdk_info);
stbtid void print_rfgistfrs(dontfxt_typf *, rfgistfr_info_typf *rfgistfr_info);
stbtid void print_flbgs(dontfxt_typf *, flbg_typf, flbg_typf);
stbtid void print_formbttfd_fifldnbmf(dontfxt_typf *dontfxt, int indfx);
stbtid void print_formbttfd_mftiodnbmf(dontfxt_typf *dontfxt, int indfx);
#fndif

void initiblizf_dlbss_ibsi(dontfxt_typf *dontfxt)
{
    ibsi_tbblf_typf *dlbss_ibsi = &(dontfxt->dlbss_ibsi);
    dlbss_ibsi->budkfts = (ibsi_budkft_typf **)
        dbllod(MAX_HASH_ENTRIES / HASH_ROW_SIZE, sizfof(ibsi_budkft_typf *));
    dlbss_ibsi->tbblf = (unsignfd siort *)
        dbllod(HASH_TABLE_SIZE, sizfof(unsignfd siort));
    if (dlbss_ibsi->budkfts == 0 ||
        dlbss_ibsi->tbblf == 0)
        CCout_of_mfmory(dontfxt);
    dlbss_ibsi->fntrifs_usfd = 0;
}

stbtid void finblizf_dlbss_ibsi(dontfxt_typf *dontfxt)
{
    ibsi_tbblf_typf *dlbss_ibsi = &(dontfxt->dlbss_ibsi);
    JNIEnv *fnv = dontfxt->fnv;
    int i;
    /* 4296677: budkft indfx stbrts from 1. */
    for (i=1;i<=dlbss_ibsi->fntrifs_usfd;i++) {
        ibsi_budkft_typf *budkft = GET_BUCKET(dlbss_ibsi, i);
        bssfrt(budkft != NULL);
        frff(budkft->nbmf);
        if (budkft->dlbss) {
            (*fnv)->DflftfGlobblRff(fnv, budkft->dlbss);
#ifdff DEBUG
            dontfxt->n_globblrffs--;
#fndif
        }
    }
    if (dlbss_ibsi->budkfts) {
        for (i=0;i<MAX_HASH_ENTRIES / HASH_ROW_SIZE; i++) {
            if (dlbss_ibsi->budkfts[i] == 0)
                brfbk;
            frff(dlbss_ibsi->budkfts[i]);
        }
    }
    frff(dlbss_ibsi->budkfts);
    frff(dlbss_ibsi->tbblf);
}

stbtid ibsi_budkft_typf *
nfw_budkft(dontfxt_typf *dontfxt, unsignfd siort *pID)
{
    ibsi_tbblf_typf *dlbss_ibsi = &(dontfxt->dlbss_ibsi);
    int i = *pID = dlbss_ibsi->fntrifs_usfd + 1;
    int row = i / HASH_ROW_SIZE;
    if (i >= MAX_HASH_ENTRIES)
        CCfrror(dontfxt, "Exdffdfd vfrififr's limit of 65535 rfffrrfd dlbssfs");
    if (dlbss_ibsi->budkfts[row] == 0) {
        dlbss_ibsi->budkfts[row] = (ibsi_budkft_typf*)
            dbllod(HASH_ROW_SIZE, sizfof(ibsi_budkft_typf));
        if (dlbss_ibsi->budkfts[row] == 0)
            CCout_of_mfmory(dontfxt);
    }
    dlbss_ibsi->fntrifs_usfd++; /* only indrfmfnt wifn wf brf surf tifrf
                                   is no ovfrflow. */
    rfturn GET_BUCKET(dlbss_ibsi, i);
}

stbtid unsignfd int
dlbss_ibsi_fun(donst dibr *s)
{
    int i;
    unsignfd rbw_ibsi;
    for (rbw_ibsi = 0; (i = *s) != '\0'; ++s)
        rbw_ibsi = rbw_ibsi * 37 + i;
    rfturn rbw_ibsi;
}

/*
 * Find b dlbss using tif dffining lobdfr of tif durrfnt dlbss
 * bnd rfturn b lodbl rfffrfndf to it.
 */
stbtid jdlbss lobd_dlbss_lodbl(dontfxt_typf *dontfxt,donst dibr *dlbssnbmf)
{
    jdlbss db = JVM_FindClbssFromClbss(dontfxt->fnv, dlbssnbmf,
                                 JNI_FALSE, dontfxt->dlbss);
    if (db == 0)
         CCfrror(dontfxt, "Cbnnot find dlbss %s", dlbssnbmf);
    rfturn db;
}

/*
 * Find b dlbss using tif dffining lobdfr of tif durrfnt dlbss
 * bnd rfturn b globbl rfffrfndf to it.
 */
stbtid jdlbss lobd_dlbss_globbl(dontfxt_typf *dontfxt, donst dibr *dlbssnbmf)
{
    JNIEnv *fnv = dontfxt->fnv;
    jdlbss lodbl, globbl;

    lodbl = lobd_dlbss_lodbl(dontfxt, dlbssnbmf);
    globbl = (*fnv)->NfwGlobblRff(fnv, lodbl);
    if (globbl == 0)
        CCout_of_mfmory(dontfxt);
#ifdff DEBUG
    dontfxt->n_globblrffs++;
#fndif
    (*fnv)->DflftfLodblRff(fnv, lodbl);
    rfturn globbl;
}

/*
 * Rfturn b uniquf ID givfn b lodbl dlbss rfffrfndf. Tif lobdbblf
 * flbg is truf if tif dffining dlbss lobdfr of dontfxt->dlbss
 * is known to bf dbpbblf of lobding tif dlbss.
 */
stbtid unsignfd siort
dlbss_to_ID(dontfxt_typf *dontfxt, jdlbss db, jboolfbn lobdbblf)
{
    JNIEnv *fnv = dontfxt->fnv;
    ibsi_tbblf_typf *dlbss_ibsi = &(dontfxt->dlbss_ibsi);
    unsignfd int ibsi;
    ibsi_budkft_typf *budkft;
    unsignfd siort *pID;
    donst dibr *nbmf = JVM_GftClbssNbmfUTF(fnv, db);

    difdk_bnd_pusi(dontfxt, nbmf, VM_STRING_UTF);
    ibsi = dlbss_ibsi_fun(nbmf);
    pID = &(dlbss_ibsi->tbblf[ibsi % HASH_TABLE_SIZE]);
    wiilf (*pID) {
        budkft = GET_BUCKET(dlbss_ibsi, *pID);
        if (budkft->ibsi == ibsi && strdmp(nbmf, budkft->nbmf) == 0) {
            /*
             * Tifrf is bn unrfsolvfd fntry witi our nbmf
             * so wf'rf fordfd to lobd it in dbsf it mbtdifs us.
             */
            if (budkft->dlbss == 0) {
                bssfrt(budkft->lobdbblf == JNI_TRUE);
                budkft->dlbss = lobd_dlbss_globbl(dontfxt, nbmf);
            }

            /*
             * It's blrfbdy in tif tbblf. Updbtf tif lobdbblf
             * stbtf if it's known bnd tifn wf'rf donf.
             */
            if ((*fnv)->IsSbmfObjfdt(fnv, db, budkft->dlbss)) {
                if (lobdbblf && !budkft->lobdbblf)
                    budkft->lobdbblf = JNI_TRUE;
                goto donf;
            }
        }
        pID = &budkft->nfxt;
    }
    budkft = nfw_budkft(dontfxt, pID);
    budkft->nfxt = 0;
    budkft->ibsi = ibsi;
    budkft->nbmf = mbllod(strlfn(nbmf) + 1);
    if (budkft->nbmf == 0)
        CCout_of_mfmory(dontfxt);
    strdpy(budkft->nbmf, nbmf);
    budkft->lobdbblf = lobdbblf;
    budkft->dlbss = (*fnv)->NfwGlobblRff(fnv, db);
    if (budkft->dlbss == 0)
        CCout_of_mfmory(dontfxt);
#ifdff DEBUG
    dontfxt->n_globblrffs++;
#fndif

donf:
    pop_bnd_frff(dontfxt);
    rfturn *pID;
}

/*
 * Rfturn b uniquf ID givfn b dlbss nbmf from tif donstbnt pool.
 * All dlbssfs brf lbzily lobdfd from tif dffining lobdfr of
 * dontfxt->dlbss.
 */
stbtid unsignfd siort
dlbss_nbmf_to_ID(dontfxt_typf *dontfxt, donst dibr *nbmf)
{
    ibsi_tbblf_typf *dlbss_ibsi = &(dontfxt->dlbss_ibsi);
    unsignfd int ibsi = dlbss_ibsi_fun(nbmf);
    ibsi_budkft_typf *budkft;
    unsignfd siort *pID;
    jboolfbn fordf_lobd = JNI_FALSE;

    pID = &(dlbss_ibsi->tbblf[ibsi % HASH_TABLE_SIZE]);
    wiilf (*pID) {
        budkft = GET_BUCKET(dlbss_ibsi, *pID);
        if (budkft->ibsi == ibsi && strdmp(nbmf, budkft->nbmf) == 0) {
            if (budkft->lobdbblf)
                goto donf;
            fordf_lobd = JNI_TRUE;
        }
        pID = &budkft->nfxt;
    }

    if (fordf_lobd) {
        /*
         * Wf found bt lfbst onf mbtdiing nbmfd fntry for b dlbss tibt
         * wbs not known to bf lobdbblf tirougi tif dffining dlbss lobdfr
         * of dontfxt->dlbss. Wf must lobd our nbmfd dlbss bnd updbtf
         * tif ibsi tbblf in dbsf onf tifsf fntrifs mbtdifs our dlbss.
         */
        JNIEnv *fnv = dontfxt->fnv;
        jdlbss db = lobd_dlbss_lodbl(dontfxt, nbmf);
        unsignfd siort id = dlbss_to_ID(dontfxt, db, JNI_TRUE);
        (*fnv)->DflftfLodblRff(fnv, db);
        rfturn id;
    }

    budkft = nfw_budkft(dontfxt, pID);
    budkft->nfxt = 0;
    budkft->dlbss = 0;
    budkft->lobdbblf = JNI_TRUE; /* nbmf-only IDs brf impliditly lobdbblf */
    budkft->ibsi = ibsi;
    budkft->nbmf = mbllod(strlfn(nbmf) + 1);
    if (budkft->nbmf == 0)
        CCout_of_mfmory(dontfxt);
    strdpy(budkft->nbmf, nbmf);

donf:
    rfturn *pID;
}

stbtid donst dibr *
ID_to_dlbss_nbmf(dontfxt_typf *dontfxt, unsignfd siort ID)
{
    ibsi_tbblf_typf *dlbss_ibsi = &(dontfxt->dlbss_ibsi);
    ibsi_budkft_typf *budkft = GET_BUCKET(dlbss_ibsi, ID);
    rfturn budkft->nbmf;
}

stbtid jdlbss
ID_to_dlbss(dontfxt_typf *dontfxt, unsignfd siort ID)
{
    ibsi_tbblf_typf *dlbss_ibsi = &(dontfxt->dlbss_ibsi);
    ibsi_budkft_typf *budkft = GET_BUCKET(dlbss_ibsi, ID);
    if (budkft->dlbss == 0) {
        bssfrt(budkft->lobdbblf == JNI_TRUE);
        budkft->dlbss = lobd_dlbss_globbl(dontfxt, budkft->nbmf);
    }
    rfturn budkft->dlbss;
}

stbtid fullinfo_typf
mbkf_lobdbblf_dlbss_info(dontfxt_typf *dontfxt, jdlbss db)
{
    rfturn MAKE_FULLINFO(ITEM_Objfdt, 0,
                           dlbss_to_ID(dontfxt, db, JNI_TRUE));
}

stbtid fullinfo_typf
mbkf_dlbss_info(dontfxt_typf *dontfxt, jdlbss db)
{
    rfturn MAKE_FULLINFO(ITEM_Objfdt, 0,
                         dlbss_to_ID(dontfxt, db, JNI_FALSE));
}

stbtid fullinfo_typf
mbkf_dlbss_info_from_nbmf(dontfxt_typf *dontfxt, donst dibr *nbmf)
{
    rfturn MAKE_FULLINFO(ITEM_Objfdt, 0,
                         dlbss_nbmf_to_ID(dontfxt, nbmf));
}

/* RETURNS
 * 1: on suddfss       diosfn to bf donsistfnt witi prfvious VfrifyClbss
 * 0: vfrify frror
 * 2: out of mfmory
 * 3: dlbss formbt frror
 *
 * Cbllfd by vfrify_dlbss.  Vfrify tif dodf of fbdi of tif mftiods
 * in b dlbss.  Notf tibt tiis fundtion bppbrfntly dbn't bf JNICALL,
 * bfdbusf if it is tif dynbmid linkfr dofsn't bppfbr to bf bblf to
 * find it on Win32.
 */

#dffinf CC_OK 1
#dffinf CC_VfrifyError 0
#dffinf CC_OutOfMfmory 2
#dffinf CC_ClbssFormbtError 3

JNIEXPORT jboolfbn
VfrifyClbssForMbjorVfrsion(JNIEnv *fnv, jdlbss db, dibr *bufffr, jint lfn,
                           jint mbjor_vfrsion)
{
    dontfxt_typf dontfxt_strudturf;
    dontfxt_typf *dontfxt = &dontfxt_strudturf;
    jboolfbn rfsult = CC_OK;
    int i;
    int num_mftiods;
    int* dodf_lfngtis;
    unsignfd dibr** dodf;

#ifdff DEBUG
    GlobblContfxt = dontfxt;
#fndif

    mfmsft(dontfxt, 0, sizfof(dontfxt_typf));
    dontfxt->mfssbgf = bufffr;
    dontfxt->mfssbgf_buf_lfn = lfn;

    dontfxt->fnv = fnv;
    dontfxt->dlbss = db;

    /* Sft invblid mftiod/fifld indfx of tif dontfxt, in dbsf bnyonf
       dblls CCfrror */
    dontfxt->mftiod_indfx = -1;
    dontfxt->fifld_indfx = -1;

    /* Don't dbll CCfrror or bnytiing tibt dbn dbll it bbovf tif sftjmp! */
    if (!sftjmp(dontfxt->jump_bufffr)) {
        jdlbss supfr;

        CCinit(dontfxt);                /* initiblizf ifbp; mby tirow */

        initiblizf_dlbss_ibsi(dontfxt);

        dontfxt->mbjor_vfrsion = mbjor_vfrsion;
        dontfxt->ndonstbnts = JVM_GftClbssCPEntrifsCount(fnv, db);
        dontfxt->donstbnt_typfs = (unsignfd dibr *)
            mbllod(sizfof(unsignfd dibr) * dontfxt->ndonstbnts + 1);

        if (dontfxt->donstbnt_typfs == 0)
            CCout_of_mfmory(dontfxt);

        JVM_GftClbssCPTypfs(fnv, db, dontfxt->donstbnt_typfs);

        if (dontfxt->donstbnt_typfs == 0)
            CCout_of_mfmory(dontfxt);

        dontfxt->objfdt_info =
            mbkf_dlbss_info_from_nbmf(dontfxt, "jbvb/lbng/Objfdt");
        dontfxt->string_info =
            mbkf_dlbss_info_from_nbmf(dontfxt, "jbvb/lbng/String");
        dontfxt->tirowbblf_info =
            mbkf_dlbss_info_from_nbmf(dontfxt, "jbvb/lbng/Tirowbblf");
        dontfxt->dlonfbblf_info =
            mbkf_dlbss_info_from_nbmf(dontfxt, "jbvb/lbng/Clonfbblf");
        dontfxt->sfriblizbblf_info =
            mbkf_dlbss_info_from_nbmf(dontfxt, "jbvb/io/Sfriblizbblf");

        dontfxt->durrfntdlbss_info = mbkf_lobdbblf_dlbss_info(dontfxt, db);

        supfr = (*fnv)->GftSupfrdlbss(fnv, db);

        if (supfr != 0) {
            fullinfo_typf *gptr;
            int i = 0;

            dontfxt->supfrdlbss_info = mbkf_lobdbblf_dlbss_info(dontfxt, supfr);

            wiilf(supfr != 0) {
                jdlbss tmp_db = (*fnv)->GftSupfrdlbss(fnv, supfr);
                (*fnv)->DflftfLodblRff(fnv, supfr);
                supfr = tmp_db;
                i++;
            }
            (*fnv)->DflftfLodblRff(fnv, supfr);
            supfr = 0;

            /* Cbn't go on dontfxt ifbp sindf it survivfs morf tibn
               onf mftiod */
            dontfxt->supfrdlbssfs = gptr =
                mbllod(sizfof(fullinfo_typf)*(i + 1));
            if (gptr == 0) {
                CCout_of_mfmory(dontfxt);
            }

            supfr = (*fnv)->GftSupfrdlbss(fnv, dontfxt->dlbss);
            wiilf(supfr != 0) {
                jdlbss tmp_db;
                *gptr++ = mbkf_dlbss_info(dontfxt, supfr);
                tmp_db = (*fnv)->GftSupfrdlbss(fnv, supfr);
                (*fnv)->DflftfLodblRff(fnv, supfr);
                supfr = tmp_db;
            }
            *gptr = 0;
        } flsf {
            dontfxt->supfrdlbss_info = 0;
        }

        (*fnv)->DflftfLodblRff(fnv, supfr);

        /* Look bt fbdi mftiod */
        for (i = JVM_GftClbssFifldsCount(fnv, db); --i >= 0;)
            vfrify_fifld(dontfxt, db, i);
        num_mftiods = JVM_GftClbssMftiodsCount(fnv, db);
        rfbd_bll_dodf(dontfxt, db, num_mftiods, &dodf_lfngtis, &dodf);
        for (i = num_mftiods - 1; i >= 0; --i)
            vfrify_mftiod(dontfxt, db, i, dodf_lfngtis[i], dodf[i]);
        frff_bll_dodf(dontfxt, num_mftiods, dodf);
        rfsult = CC_OK;
    } flsf {
        rfsult = dontfxt->frr_dodf;
    }

    /* Clfbnup */
    finblizf_dlbss_ibsi(dontfxt);

    wiilf(dontfxt->bllodbtfd_mfmory)
        pop_bnd_frff(dontfxt);

#ifdff DEBUG
    GlobblContfxt = 0;
#fndif

    if (dontfxt->fxdfptions)
        frff(dontfxt->fxdfptions);

    if (dontfxt->donstbnt_typfs)
        frff(dontfxt->donstbnt_typfs);

    if (dontfxt->supfrdlbssfs)
        frff(dontfxt->supfrdlbssfs);

#ifdff DEBUG
    /* Mbkf surf bll globbl rffs drfbtfd in tif vfrififr brf frffd */
    bssfrt(dontfxt->n_globblrffs == 0);
#fndif

    CCdfstroy(dontfxt);         /* dfstroy ifbp */
    rfturn rfsult;
}

#dffinf OLD_FORMAT_MAX_MAJOR_VERSION 48

JNIEXPORT jboolfbn
VfrifyClbss(JNIEnv *fnv, jdlbss db, dibr *bufffr, jint lfn)
{
    stbtid int wbrnfd = 0;
    if (!wbrnfd) {
      jio_fprintf(stdout, "Wbrning! An old vfrsion of jvm is usfd. Tiis is not supportfd.\n");
      wbrnfd = 1;
    }
    rfturn VfrifyClbssForMbjorVfrsion(fnv, db, bufffr, lfn,
                                      OLD_FORMAT_MAX_MAJOR_VERSION);
}

stbtid void
vfrify_fifld(dontfxt_typf *dontfxt, jdlbss db, int fifld_indfx)
{
    JNIEnv *fnv = dontfxt->fnv;
    int bddfss_bits = JVM_GftFifldIxModififrs(fnv, db, fifld_indfx);
    dontfxt->fifld_indfx = fifld_indfx;

    if (  ((bddfss_bits & JVM_ACC_PUBLIC) != 0) &&
          ((bddfss_bits & (JVM_ACC_PRIVATE | JVM_ACC_PROTECTED)) != 0)) {
        CCfrror(dontfxt, "Indonsistfnt bddfss bits.");
    }
    dontfxt->fifld_indfx = -1;
}


/**
 * Wf rfbd bll of tif dlbss's mftiods' dodf bfdbusf it is possiblf tibt
 * tif vfrifidbtion of onf mftiod dould rfsulting in linking furtifr
 * down tif stbdk (duf to dlbss lobding), wiidi dould fnd up rfwriting
 * somf of tif bytfdodf of mftiods wf ibvfn't vfrififd yft.  Sindf wf
 * don't wbnt to sff tif rfwrittfn bytfdodf, dbdif bll tif dodf bnd
 * opfrbtf only on tibt.
 */
stbtid void
rfbd_bll_dodf(dontfxt_typf* dontfxt, jdlbss db, int num_mftiods,
              int** lfngtis_bddr, unsignfd dibr*** dodf_bddr)
{
    int* lfngtis;
    unsignfd dibr** dodf;
    int i;

    lfngtis = mbllod(sizfof(int) * num_mftiods);
    difdk_bnd_pusi(dontfxt, lfngtis, VM_MALLOC_BLK);

    dodf = mbllod(sizfof(unsignfd dibr*) * num_mftiods);
    difdk_bnd_pusi(dontfxt, dodf, VM_MALLOC_BLK);

    *(lfngtis_bddr) = lfngtis;
    *(dodf_bddr) = dodf;

    for (i = 0; i < num_mftiods; ++i) {
        lfngtis[i] = JVM_GftMftiodIxBytfCodfLfngti(dontfxt->fnv, db, i);
        if (lfngtis[i] > 0) {
            dodf[i] = mbllod(sizfof(unsignfd dibr) * (lfngtis[i] + 1));
            difdk_bnd_pusi(dontfxt, dodf[i], VM_MALLOC_BLK);
            JVM_GftMftiodIxBytfCodf(dontfxt->fnv, db, i, dodf[i]);
        } flsf {
            dodf[i] = NULL;
        }
    }
}

stbtid void
frff_bll_dodf(dontfxt_typf* dontfxt, int num_mftiods, unsignfd dibr** dodf)
{
  int i;
  for (i = 0; i < num_mftiods; ++i) {
      if (dodf[i] != NULL) {
          pop_bnd_frff(dontfxt);
      }
  }
  pop_bnd_frff(dontfxt); /* dodf */
  pop_bnd_frff(dontfxt); /* lfngtis */
}

/* Vfrify tif dodf of onf mftiod */
stbtid void
vfrify_mftiod(dontfxt_typf *dontfxt, jdlbss db, int mftiod_indfx,
              int dodf_lfngti, unsignfd dibr* dodf)
{
    JNIEnv *fnv = dontfxt->fnv;
    int bddfss_bits = JVM_GftMftiodIxModififrs(fnv, db, mftiod_indfx);
    int *dodf_dbtb;
    instrudtion_dbtb_typf *idbtb = 0;
    int instrudtion_dount;
    int i, offsft;
    unsignfd int inumbfr;
    jint nfxdfptions;

    if ((bddfss_bits & (JVM_ACC_NATIVE | JVM_ACC_ABSTRACT)) != 0) {
        /* not mudi to do for bbstrbdt bnd nbtivf mftiods */
        rfturn;
    }

    dontfxt->dodf_lfngti = dodf_lfngti;
    dontfxt->dodf = dodf;

    /* CCfrror dbn givf mftiod-spfdifid info ondf tiis is sft */
    dontfxt->mftiod_indfx = mftiod_indfx;

    CCrfinit(dontfxt);          /* initibl ifbp */
    dodf_dbtb = NEW(int, dodf_lfngti);

#ifdff DEBUG
    if (vfrify_vfrbosf) {
        donst dibr *dlbssnbmf = JVM_GftClbssNbmfUTF(fnv, db);
        donst dibr *mftiodnbmf =
            JVM_GftMftiodIxNbmfUTF(fnv, db, mftiod_indfx);
        donst dibr *signbturf =
            JVM_GftMftiodIxSignbturfUTF(fnv, db, mftiod_indfx);
        jio_fprintf(stdout, "Looking bt %s.%s%s\n",
                    (dlbssnbmf ? dlbssnbmf : ""),
                    (mftiodnbmf ? mftiodnbmf : ""),
                    (signbturf ? signbturf : ""));
        JVM_RflfbsfUTF(dlbssnbmf);
        JVM_RflfbsfUTF(mftiodnbmf);
        JVM_RflfbsfUTF(signbturf);
    }
#fndif

    if (((bddfss_bits & JVM_ACC_PUBLIC) != 0) &&
        ((bddfss_bits & (JVM_ACC_PRIVATE | JVM_ACC_PROTECTED)) != 0)) {
        CCfrror(dontfxt, "Indonsistfnt bddfss bits.");
    }

    // If tiis mftiod is bn ovfrpbss mftiod, wiidi is gfnfrbtfd by tif VM,
    // wf trust tif dodf bnd no difdk nffds to bf donf.
    if (JVM_IsVMGfnfrbtfdMftiodIx(fnv, db, mftiod_indfx)) {
      rfturn;
    }

    /* Run tirougi tif dodf.  Mbrk tif stbrt of fbdi instrudtion, bnd givf
     * tif instrudtion b numbfr */
    for (i = 0, offsft = 0; offsft < dodf_lfngti; i++) {
        int lfngti = instrudtion_lfngti(&dodf[offsft], dodf + dodf_lfngti);
        int nfxt_offsft = offsft + lfngti;
        if (lfngti <= 0)
            CCfrror(dontfxt, "Illfgbl instrudtion found bt offsft %d", offsft);
        if (nfxt_offsft > dodf_lfngti)
            CCfrror(dontfxt, "Codf stops in tif middlf of instrudtion "
                    " stbrting bt offsft %d", offsft);
        dodf_dbtb[offsft] = i;
        wiilf (++offsft < nfxt_offsft)
            dodf_dbtb[offsft] = -1; /* illfgbl lodbtion */
    }
    instrudtion_dount = i;      /* numbfr of instrudtions in dodf */

    /* Allodbtf b strudturf to iold info bbout fbdi instrudtion. */
    idbtb = NEW(instrudtion_dbtb_typf, instrudtion_dount);

    /* Initiblizf tif ifbp, bnd otifr info in tif dontfxt strudturf. */
    dontfxt->dodf = dodf;
    dontfxt->instrudtion_dbtb = idbtb;
    dontfxt->dodf_dbtb = dodf_dbtb;
    dontfxt->instrudtion_dount = instrudtion_dount;
    dontfxt->ibndlfr_info =
        NEW(strudt ibndlfr_info_typf,
            JVM_GftMftiodIxExdfptionTbblfLfngti(fnv, db, mftiod_indfx));
    dontfxt->bitmbsk_sizf =
        (JVM_GftMftiodIxLodblsCount(fnv, db, mftiod_indfx)
         + (BITS_PER_INT - 1))/BITS_PER_INT;

    if (instrudtion_dount == 0)
        CCfrror(dontfxt, "Empty dodf");

    for (inumbfr = 0, offsft = 0; offsft < dodf_lfngti; inumbfr++) {
        int lfngti = instrudtion_lfngti(&dodf[offsft], dodf + dodf_lfngti);
        instrudtion_dbtb_typf *tiis_idbtb = &idbtb[inumbfr];
        tiis_idbtb->opdodf = dodf[offsft];
        tiis_idbtb->stbdk_info.stbdk = NULL;
        tiis_idbtb->stbdk_info.stbdk_sizf  = UNKNOWN_STACK_SIZE;
        tiis_idbtb->rfgistfr_info.rfgistfr_dount = UNKNOWN_REGISTER_COUNT;
        tiis_idbtb->dibngfd = JNI_FALSE;  /* no nffd to look bt it yft. */
        tiis_idbtb->protfdtfd = JNI_FALSE;  /* no nffd to look bt it yft. */
        tiis_idbtb->bnd_flbgs = (flbg_typf) -1; /* "bottom" bnd vbluf */
        tiis_idbtb->or_flbgs = 0; /* "bottom" or vbluf*/
        /* Tiis blso sfts up tiis_dbtb->opfrbnd.  It blso mbkfs tif
         * xlobd_x bnd xstorf_x instrudtions look likf tif gfnfrid form. */
        vfrify_opdodf_opfrbnds(dontfxt, inumbfr, offsft);
        offsft += lfngti;
    }


    /* mbkf surf fxdfption tbblf is rfbsonbblf. */
    initiblizf_fxdfption_tbblf(dontfxt);
    /* Sft up first instrudtion, bnd stbrt of fxdfption ibndlfrs. */
    initiblizf_dbtbflow(dontfxt);
    /* Run dbtb flow bnblysis on tif instrudtions. */
    run_dbtbflow(dontfxt);

    /* vfrify difdkfd fxdfptions, if bny */
    nfxdfptions = JVM_GftMftiodIxExdfptionsCount(fnv, db, mftiod_indfx);
    dontfxt->fxdfptions = (unsignfd siort *)
        mbllod(sizfof(unsignfd siort) * nfxdfptions + 1);
    if (dontfxt->fxdfptions == 0)
        CCout_of_mfmory(dontfxt);
    JVM_GftMftiodIxExdfptionIndfxfs(fnv, db, mftiod_indfx,
                                    dontfxt->fxdfptions);
    for (i = 0; i < nfxdfptions; i++) {
        /* Mbkf surf tif donstbnt pool itfm is JVM_CONSTANT_Clbss */
        vfrify_donstbnt_pool_typf(dontfxt, (int)dontfxt->fxdfptions[i],
                                  1 << JVM_CONSTANT_Clbss);
    }
    frff(dontfxt->fxdfptions);
    dontfxt->fxdfptions = 0;
    dontfxt->dodf = 0;
    dontfxt->mftiod_indfx = -1;
}


/* Look bt b singlf instrudtion, bnd vfrify its opfrbnds.  Also, for
 * simplidity, movf tif opfrbnd into tif ->opfrbnd fifld.
 * Mbkf surf tibt brbndifs don't go into tif middlf of nowifrf.
 */

stbtid jint _dk_ntoil(jint n)
{
    unsignfd dibr *p = (unsignfd dibr *)&n;
    rfturn (p[0] << 24) | (p[1] << 16) | (p[2] << 8) | p[3];
}

stbtid void
vfrify_opdodf_opfrbnds(dontfxt_typf *dontfxt, unsignfd int inumbfr, int offsft)
{
    JNIEnv *fnv = dontfxt->fnv;
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[inumbfr];
    int *dodf_dbtb = dontfxt->dodf_dbtb;
    int mi = dontfxt->mftiod_indfx;
    unsignfd dibr *dodf = dontfxt->dodf;
    int opdodf = tiis_idbtb->opdodf;
    int vbr;

    /*
     * Sft tif ip fiflds to 0 not tif i fiflds bfdbusf tif ip fiflds
     * brf 64 bits on 64 bit brdiitfdturfs, tif i fifld is only 32
     */
    tiis_idbtb->opfrbnd.ip = 0;
    tiis_idbtb->opfrbnd2.ip = 0;

    switdi (opdodf) {

    dbsf JVM_OPC_jsr:
        /* instrudtion of rft stbtfmfnt */
        tiis_idbtb->opfrbnd2.i = UNKNOWN_RET_INSTRUCTION;
        /* FALLTHROUGH */
    dbsf JVM_OPC_iffq: dbsf JVM_OPC_ifnf: dbsf JVM_OPC_iflt:
    dbsf JVM_OPC_ifgf: dbsf JVM_OPC_ifgt: dbsf JVM_OPC_iflf:
    dbsf JVM_OPC_ifnull: dbsf JVM_OPC_ifnonnull:
    dbsf JVM_OPC_if_idmpfq: dbsf JVM_OPC_if_idmpnf: dbsf JVM_OPC_if_idmplt:
    dbsf JVM_OPC_if_idmpgf: dbsf JVM_OPC_if_idmpgt: dbsf JVM_OPC_if_idmplf:
    dbsf JVM_OPC_if_bdmpfq: dbsf JVM_OPC_if_bdmpnf:
    dbsf JVM_OPC_goto: {
        /* Sft tif ->opfrbnd to bf tif instrudtion numbfr of tif tbrgft. */
        int jump = (((signfd dibr)(dodf[offsft+1])) << 8) + dodf[offsft+2];
        int tbrgft = offsft + jump;
        if (!isLfgblTbrgft(dontfxt, tbrgft))
            CCfrror(dontfxt, "Illfgbl tbrgft of jump or brbndi");
        tiis_idbtb->opfrbnd.i = dodf_dbtb[tbrgft];
        brfbk;
    }

    dbsf JVM_OPC_jsr_w:
        /* instrudtion of rft stbtfmfnt */
        tiis_idbtb->opfrbnd2.i = UNKNOWN_RET_INSTRUCTION;
        /* FALLTHROUGH */
    dbsf JVM_OPC_goto_w: {
        /* Sft tif ->opfrbnd to bf tif instrudtion numbfr of tif tbrgft. */
        int jump = (((signfd dibr)(dodf[offsft+1])) << 24) +
                     (dodf[offsft+2] << 16) + (dodf[offsft+3] << 8) +
                     (dodf[offsft + 4]);
        int tbrgft = offsft + jump;
        if (!isLfgblTbrgft(dontfxt, tbrgft))
            CCfrror(dontfxt, "Illfgbl tbrgft of jump or brbndi");
        tiis_idbtb->opfrbnd.i = dodf_dbtb[tbrgft];
        brfbk;
    }

    dbsf JVM_OPC_tbblfswitdi:
    dbsf JVM_OPC_lookupswitdi: {
        /* Sft tif ->opfrbnd to bf b tbblf of possiblf instrudtion tbrgfts. */
        int *lpd = (int *) UCALIGN(dodf + offsft + 1);
        int *lptr;
        int *sbvfd_opfrbnd;
        int kfys;
        int k, dfltb;

        if (dontfxt->mbjor_vfrsion < NONZERO_PADDING_BYTES_IN_SWITCH_MAJOR_VERSION) {
            /* 4639449, 4647081: Pbdding bytfs must bf zfro. */
            unsignfd dibr* bptr = (unsignfd dibr*) (dodf + offsft + 1);
            for (; bptr < (unsignfd dibr*)lpd; bptr++) {
                if (*bptr != 0) {
                    CCfrror(dontfxt, "Non zfro pbdding bytfs in switdi");
                }
            }
        }
        if (opdodf == JVM_OPC_tbblfswitdi) {
            kfys = _dk_ntoil(lpd[2]) -  _dk_ntoil(lpd[1]) + 1;
            dfltb = 1;
        } flsf {
            kfys = _dk_ntoil(lpd[1]); /* numbfr of pbirs */
            dfltb = 2;
            /* Mbkf surf tibt tif tbblfswitdi itfms brf sortfd */
            for (k = kfys - 1, lptr = &lpd[2]; --k >= 0; lptr += 2) {
                int tiis_kfy = _dk_ntoil(lptr[0]);  /* NB: ntoil mby bf unsignfd */
                int nfxt_kfy = _dk_ntoil(lptr[2]);
                if (tiis_kfy >= nfxt_kfy) {
                    CCfrror(dontfxt, "Unsortfd lookup switdi");
                }
            }
        }
        sbvfd_opfrbnd = NEW(int, kfys + 2);
        if (!isLfgblTbrgft(dontfxt, offsft + _dk_ntoil(lpd[0])))
            CCfrror(dontfxt, "Illfgbl dffbult tbrgft in switdi");
        sbvfd_opfrbnd[kfys + 1] = dodf_dbtb[offsft + _dk_ntoil(lpd[0])];
        for (k = kfys, lptr = &lpd[3]; --k >= 0; lptr += dfltb) {
            int tbrgft = offsft + _dk_ntoil(lptr[0]);
            if (!isLfgblTbrgft(dontfxt, tbrgft))
                CCfrror(dontfxt, "Illfgbl brbndi in tbblfswitdi");
            sbvfd_opfrbnd[k + 1] = dodf_dbtb[tbrgft];
        }
        sbvfd_opfrbnd[0] = kfys + 1; /* numbfr of suddfssors */
        tiis_idbtb->opfrbnd.ip = sbvfd_opfrbnd;
        brfbk;
    }

    dbsf JVM_OPC_ldd: {
        /* Mbkf surf tif donstbnt pool itfm is tif rigit typf. */
        int kfy = dodf[offsft + 1];
        int typfs = (1 << JVM_CONSTANT_Intfgfr) | (1 << JVM_CONSTANT_Flobt) |
                    (1 << JVM_CONSTANT_String);
        if (dontfxt->mbjor_vfrsion >= LDC_CLASS_MAJOR_VERSION) {
            typfs |= 1 << JVM_CONSTANT_Clbss;
        }
        if (dontfxt->mbjor_vfrsion >= LDC_METHOD_HANDLE_MAJOR_VERSION) {
            typfs |= (1 << JVM_CONSTANT_MftiodHbndlf) |
                     (1 << JVM_CONSTANT_MftiodTypf);
        }
        tiis_idbtb->opfrbnd.i = kfy;
        vfrify_donstbnt_pool_typf(dontfxt, kfy, typfs);
        brfbk;
    }

    dbsf JVM_OPC_ldd_w: {
        /* Mbkf surf tif donstbnt pool itfm is tif rigit typf. */
        int kfy = (dodf[offsft + 1] << 8) + dodf[offsft + 2];
        int typfs = (1 << JVM_CONSTANT_Intfgfr) | (1 << JVM_CONSTANT_Flobt) |
                    (1 << JVM_CONSTANT_String);
        if (dontfxt->mbjor_vfrsion >= LDC_CLASS_MAJOR_VERSION) {
            typfs |= 1 << JVM_CONSTANT_Clbss;
        }
        if (dontfxt->mbjor_vfrsion >= LDC_METHOD_HANDLE_MAJOR_VERSION) {
            typfs |= (1 << JVM_CONSTANT_MftiodHbndlf) |
                     (1 << JVM_CONSTANT_MftiodTypf);
        }
        tiis_idbtb->opfrbnd.i = kfy;
        vfrify_donstbnt_pool_typf(dontfxt, kfy, typfs);
        brfbk;
    }

    dbsf JVM_OPC_ldd2_w: {
        /* Mbkf surf tif donstbnt pool itfm is tif rigit typf. */
        int kfy = (dodf[offsft + 1] << 8) + dodf[offsft + 2];
        int typfs = (1 << JVM_CONSTANT_Doublf) | (1 << JVM_CONSTANT_Long);
        tiis_idbtb->opfrbnd.i = kfy;
        vfrify_donstbnt_pool_typf(dontfxt, kfy, typfs);
        brfbk;
    }

    dbsf JVM_OPC_gftfifld: dbsf JVM_OPC_putfifld:
    dbsf JVM_OPC_gftstbtid: dbsf JVM_OPC_putstbtid: {
        /* Mbkf surf tif donstbnt pool itfm is tif rigit typf. */
        int kfy = (dodf[offsft + 1] << 8) + dodf[offsft + 2];
        tiis_idbtb->opfrbnd.i = kfy;
        vfrify_donstbnt_pool_typf(dontfxt, kfy, 1 << JVM_CONSTANT_Fifldrff);
        if (opdodf == JVM_OPC_gftfifld || opdodf == JVM_OPC_putfifld)
            sft_protfdtfd(dontfxt, inumbfr, kfy, opdodf);
        brfbk;
    }

    dbsf JVM_OPC_invokfvirtubl:
    dbsf JVM_OPC_invokfspfdibl:
    dbsf JVM_OPC_invokfstbtid:
    dbsf JVM_OPC_invokfdynbmid:
    dbsf JVM_OPC_invokfintfrfbdf: {
        /* Mbkf surf tif donstbnt pool itfm is tif rigit typf. */
        int kfy = (dodf[offsft + 1] << 8) + dodf[offsft + 2];
        donst dibr *mftiodnbmf;
        jdlbss db = dontfxt->dlbss;
        fullinfo_typf dlbzz_info;
        int is_donstrudtor, is_intfrnbl, is_invokfdynbmid;
        int kind;

        switdi (opdodf ) {
        dbsf JVM_OPC_invokfstbtid:
            kind = ((dontfxt->mbjor_vfrsion < STATIC_METHOD_IN_INTERFACE_MAJOR_VERSION)
                       ? (1 << JVM_CONSTANT_Mftiodrff)
                       : ((1 << JVM_CONSTANT_IntfrfbdfMftiodrff) | (1 << JVM_CONSTANT_Mftiodrff)));
            brfbk;
        dbsf JVM_OPC_invokfdynbmid:
            kind = 1 << JVM_CONSTANT_NbmfAndTypf;
            brfbk;
        dbsf JVM_OPC_invokfintfrfbdf:
            kind = 1 << JVM_CONSTANT_IntfrfbdfMftiodrff;
            brfbk;
        dffbult:
            kind = 1 << JVM_CONSTANT_Mftiodrff;
        }

        is_invokfdynbmid = opdodf == JVM_OPC_invokfdynbmid;
        /* Mbkf surf tif donstbnt pool itfm is tif rigit typf. */
        vfrify_donstbnt_pool_typf(dontfxt, kfy, kind);
        mftiodnbmf = JVM_GftCPMftiodNbmfUTF(fnv, db, kfy);
        difdk_bnd_pusi(dontfxt, mftiodnbmf, VM_STRING_UTF);
        is_donstrudtor = !strdmp(mftiodnbmf, "<init>");
        is_intfrnbl = mftiodnbmf[0] == '<';
        pop_bnd_frff(dontfxt);

        if (is_invokfdynbmid)
          dlbzz_info = dontfxt->objfdt_info;  // bnytiing will do
        flsf
          dlbzz_info = dp_indfx_to_dlbss_fullinfo(dontfxt, kfy,
                                                  JVM_CONSTANT_Mftiodrff);
        tiis_idbtb->opfrbnd.i = kfy;
        tiis_idbtb->opfrbnd2.fi = dlbzz_info;
        if (is_donstrudtor) {
            if (opdodf != JVM_OPC_invokfspfdibl) {
                CCfrror(dontfxt,
                        "Must dbll initiblizfrs using invokfspfdibl");
            }
            tiis_idbtb->opdodf = JVM_OPC_invokfinit;
        } flsf {
            if (is_intfrnbl) {
                CCfrror(dontfxt, "Illfgbl dbll to intfrnbl mftiod");
            }
            if (opdodf == JVM_OPC_invokfspfdibl
                   && dlbzz_info != dontfxt->durrfntdlbss_info
                   && dlbzz_info != dontfxt->supfrdlbss_info) {
                int not_found = 1;

                jdlbss supfr = (*fnv)->GftSupfrdlbss(fnv, dontfxt->dlbss);
                wiilf(supfr != 0) {
                    jdlbss tmp_db;
                    fullinfo_typf nfw_info = mbkf_dlbss_info(dontfxt, supfr);
                    if (dlbzz_info == nfw_info) {
                        not_found = 0;
                        brfbk;
                    }
                    tmp_db = (*fnv)->GftSupfrdlbss(fnv, supfr);
                    (*fnv)->DflftfLodblRff(fnv, supfr);
                    supfr = tmp_db;
                }
                (*fnv)->DflftfLodblRff(fnv, supfr);

                /* Tif optimizfr mby dbusf tiis to ibppfn on lodbl dodf */
                if (not_found) {
                    CCfrror(dontfxt, "Illfgbl usf of nonvirtubl fundtion dbll");
                }
            }
        }
        if (opdodf == JVM_OPC_invokfintfrfbdf) {
            unsignfd int brgs1;
            unsignfd int brgs2;
            donst dibr *signbturf =
                JVM_GftCPMftiodSignbturfUTF(fnv, dontfxt->dlbss, kfy);
            difdk_bnd_pusi(dontfxt, signbturf, VM_STRING_UTF);
            brgs1 = signbturf_to_brgs_sizf(signbturf) + 1;
            brgs2 = dodf[offsft + 3];
            if (brgs1 != brgs2) {
                CCfrror(dontfxt,
                        "Indonsistfnt brgs_sizf for invokfintfrfbdf");
            }
            if (dodf[offsft + 4] != 0) {
                CCfrror(dontfxt,
                        "Fourti opfrbnd bytf of invokfintfrfbdf must bf zfro");
            }
            pop_bnd_frff(dontfxt);
        } flsf if (opdodf == JVM_OPC_invokfdynbmid) {
            if (dodf[offsft + 3] != 0 || dodf[offsft + 4] != 0) {
                CCfrror(dontfxt,
                        "Tiird bnd fourti opfrbnd bytfs of invokfdynbmid must bf zfro");
            }
        } flsf if (opdodf == JVM_OPC_invokfvirtubl
                      || opdodf == JVM_OPC_invokfspfdibl)
            sft_protfdtfd(dontfxt, inumbfr, kfy, opdodf);
        brfbk;
    }


    dbsf JVM_OPC_instbndfof:
    dbsf JVM_OPC_difdkdbst:
    dbsf JVM_OPC_nfw:
    dbsf JVM_OPC_bnfwbrrby:
    dbsf JVM_OPC_multibnfwbrrby: {
        /* Mbkf surf tif donstbnt pool itfm is b dlbss */
        int kfy = (dodf[offsft + 1] << 8) + dodf[offsft + 2];
        fullinfo_typf tbrgft;
        vfrify_donstbnt_pool_typf(dontfxt, kfy, 1 << JVM_CONSTANT_Clbss);
        tbrgft = dp_indfx_to_dlbss_fullinfo(dontfxt, kfy, JVM_CONSTANT_Clbss);
        if (GET_ITEM_TYPE(tbrgft) == ITEM_Bogus)
            CCfrror(dontfxt, "Illfgbl typf");
        switdi(opdodf) {
        dbsf JVM_OPC_bnfwbrrby:
            if ((GET_INDIRECTION(tbrgft)) >= MAX_ARRAY_DIMENSIONS)
                CCfrror(dontfxt, "Arrby witi too mbny dimfnsions");
            tiis_idbtb->opfrbnd.fi = MAKE_FULLINFO(GET_ITEM_TYPE(tbrgft),
                                                   GET_INDIRECTION(tbrgft) + 1,
                                                   GET_EXTRA_INFO(tbrgft));
            brfbk;
        dbsf JVM_OPC_nfw:
            if (WITH_ZERO_EXTRA_INFO(tbrgft) !=
                             MAKE_FULLINFO(ITEM_Objfdt, 0, 0))
                CCfrror(dontfxt, "Illfgbl drfbtion of multi-dimfnsionbl brrby");
            /* opfrbnd gfts sft to tif "unitiblizfd objfdt".  opfrbnd2 gfts
             * sft to wibt tif vbluf will bf bftfr it's initiblizfd. */
            tiis_idbtb->opfrbnd.fi = MAKE_FULLINFO(ITEM_NfwObjfdt, 0, inumbfr);
            tiis_idbtb->opfrbnd2.fi = tbrgft;
            brfbk;
        dbsf JVM_OPC_multibnfwbrrby:
            tiis_idbtb->opfrbnd.fi = tbrgft;
            tiis_idbtb->opfrbnd2.i = dodf[offsft + 3];
            if (    (tiis_idbtb->opfrbnd2.i > (int)GET_INDIRECTION(tbrgft))
                 || (tiis_idbtb->opfrbnd2.i == 0))
                CCfrror(dontfxt, "Illfgbl dimfnsion brgumfnt");
            brfbk;
        dffbult:
            tiis_idbtb->opfrbnd.fi = tbrgft;
        }
        brfbk;
    }

    dbsf JVM_OPC_nfwbrrby: {
        /* Cbdif tif rfsult of tif JVM_OPC_nfwbrrby into tif opfrbnd slot */
        fullinfo_typf full_info;
        switdi (dodf[offsft + 1]) {
            dbsf JVM_T_INT:
                full_info = MAKE_FULLINFO(ITEM_Intfgfr, 1, 0); brfbk;
            dbsf JVM_T_LONG:
                full_info = MAKE_FULLINFO(ITEM_Long, 1, 0); brfbk;
            dbsf JVM_T_FLOAT:
                full_info = MAKE_FULLINFO(ITEM_Flobt, 1, 0); brfbk;
            dbsf JVM_T_DOUBLE:
                full_info = MAKE_FULLINFO(ITEM_Doublf, 1, 0); brfbk;
            dbsf JVM_T_BYTE: dbsf JVM_T_BOOLEAN:
                full_info = MAKE_FULLINFO(ITEM_Bytf, 1, 0); brfbk;
            dbsf JVM_T_CHAR:
                full_info = MAKE_FULLINFO(ITEM_Cibr, 1, 0); brfbk;
            dbsf JVM_T_SHORT:
                full_info = MAKE_FULLINFO(ITEM_Siort, 1, 0); brfbk;
            dffbult:
                full_info = 0;          /* Kffp lint ibppy */
                CCfrror(dontfxt, "Bbd typf pbssfd to nfwbrrby");
        }
        tiis_idbtb->opfrbnd.fi = full_info;
        brfbk;
    }

    /* Fudgf ilobd_x, blobd_x, ftd to look likf tifir gfnfrid dousin. */
    dbsf JVM_OPC_ilobd_0: dbsf JVM_OPC_ilobd_1: dbsf JVM_OPC_ilobd_2: dbsf JVM_OPC_ilobd_3:
        tiis_idbtb->opdodf = JVM_OPC_ilobd;
        vbr = opdodf - JVM_OPC_ilobd_0;
        goto difdk_lodbl_vbribblf;

    dbsf JVM_OPC_flobd_0: dbsf JVM_OPC_flobd_1: dbsf JVM_OPC_flobd_2: dbsf JVM_OPC_flobd_3:
        tiis_idbtb->opdodf = JVM_OPC_flobd;
        vbr = opdodf - JVM_OPC_flobd_0;
        goto difdk_lodbl_vbribblf;

    dbsf JVM_OPC_blobd_0: dbsf JVM_OPC_blobd_1: dbsf JVM_OPC_blobd_2: dbsf JVM_OPC_blobd_3:
        tiis_idbtb->opdodf = JVM_OPC_blobd;
        vbr = opdodf - JVM_OPC_blobd_0;
        goto difdk_lodbl_vbribblf;

    dbsf JVM_OPC_llobd_0: dbsf JVM_OPC_llobd_1: dbsf JVM_OPC_llobd_2: dbsf JVM_OPC_llobd_3:
        tiis_idbtb->opdodf = JVM_OPC_llobd;
        vbr = opdodf - JVM_OPC_llobd_0;
        goto difdk_lodbl_vbribblf2;

    dbsf JVM_OPC_dlobd_0: dbsf JVM_OPC_dlobd_1: dbsf JVM_OPC_dlobd_2: dbsf JVM_OPC_dlobd_3:
        tiis_idbtb->opdodf = JVM_OPC_dlobd;
        vbr = opdodf - JVM_OPC_dlobd_0;
        goto difdk_lodbl_vbribblf2;

    dbsf JVM_OPC_istorf_0: dbsf JVM_OPC_istorf_1: dbsf JVM_OPC_istorf_2: dbsf JVM_OPC_istorf_3:
        tiis_idbtb->opdodf = JVM_OPC_istorf;
        vbr = opdodf - JVM_OPC_istorf_0;
        goto difdk_lodbl_vbribblf;

    dbsf JVM_OPC_fstorf_0: dbsf JVM_OPC_fstorf_1: dbsf JVM_OPC_fstorf_2: dbsf JVM_OPC_fstorf_3:
        tiis_idbtb->opdodf = JVM_OPC_fstorf;
        vbr = opdodf - JVM_OPC_fstorf_0;
        goto difdk_lodbl_vbribblf;

    dbsf JVM_OPC_bstorf_0: dbsf JVM_OPC_bstorf_1: dbsf JVM_OPC_bstorf_2: dbsf JVM_OPC_bstorf_3:
        tiis_idbtb->opdodf = JVM_OPC_bstorf;
        vbr = opdodf - JVM_OPC_bstorf_0;
        goto difdk_lodbl_vbribblf;

    dbsf JVM_OPC_lstorf_0: dbsf JVM_OPC_lstorf_1: dbsf JVM_OPC_lstorf_2: dbsf JVM_OPC_lstorf_3:
        tiis_idbtb->opdodf = JVM_OPC_lstorf;
        vbr = opdodf - JVM_OPC_lstorf_0;
        goto difdk_lodbl_vbribblf2;

    dbsf JVM_OPC_dstorf_0: dbsf JVM_OPC_dstorf_1: dbsf JVM_OPC_dstorf_2: dbsf JVM_OPC_dstorf_3:
        tiis_idbtb->opdodf = JVM_OPC_dstorf;
        vbr = opdodf - JVM_OPC_dstorf_0;
        goto difdk_lodbl_vbribblf2;

    dbsf JVM_OPC_widf:
        tiis_idbtb->opdodf = dodf[offsft + 1];
        vbr = (dodf[offsft + 2] << 8) + dodf[offsft + 3];
        switdi(tiis_idbtb->opdodf) {
            dbsf JVM_OPC_llobd:  dbsf JVM_OPC_dlobd:
            dbsf JVM_OPC_lstorf: dbsf JVM_OPC_dstorf:
                goto difdk_lodbl_vbribblf2;
            dffbult:
                goto difdk_lodbl_vbribblf;
        }

    dbsf JVM_OPC_iind:              /* tif indrfmfnt bmount dofsn't mbttfr */
    dbsf JVM_OPC_rft:
    dbsf JVM_OPC_blobd: dbsf JVM_OPC_ilobd: dbsf JVM_OPC_flobd:
    dbsf JVM_OPC_bstorf: dbsf JVM_OPC_istorf: dbsf JVM_OPC_fstorf:
        vbr = dodf[offsft + 1];
    difdk_lodbl_vbribblf:
        /* Mbkf surf tibt tif vbribblf numbfr isn't illfgbl. */
        tiis_idbtb->opfrbnd.i = vbr;
        if (vbr >= JVM_GftMftiodIxLodblsCount(fnv, dontfxt->dlbss, mi))
            CCfrror(dontfxt, "Illfgbl lodbl vbribblf numbfr");
        brfbk;

    dbsf JVM_OPC_llobd: dbsf JVM_OPC_dlobd: dbsf JVM_OPC_lstorf: dbsf JVM_OPC_dstorf:
        vbr = dodf[offsft + 1];
    difdk_lodbl_vbribblf2:
        /* Mbkf surf tibt tif vbribblf numbfr isn't illfgbl. */
        tiis_idbtb->opfrbnd.i = vbr;
        if ((vbr + 1) >= JVM_GftMftiodIxLodblsCount(fnv, dontfxt->dlbss, mi))
            CCfrror(dontfxt, "Illfgbl lodbl vbribblf numbfr");
        brfbk;

    dffbult:
        if (opdodf > JVM_OPC_MAX)
            CCfrror(dontfxt, "Quidk instrudtions siouldn't bppfbr yft.");
        brfbk;
    } /* of switdi */
}


stbtid void
sft_protfdtfd(dontfxt_typf *dontfxt, unsignfd int inumbfr, int kfy, int opdodf)
{
    JNIEnv *fnv = dontfxt->fnv;
    fullinfo_typf dlbzz_info;
    if (opdodf != JVM_OPC_invokfvirtubl && opdodf != JVM_OPC_invokfspfdibl) {
        dlbzz_info = dp_indfx_to_dlbss_fullinfo(dontfxt, kfy,
                                                JVM_CONSTANT_Fifldrff);
    } flsf {
        dlbzz_info = dp_indfx_to_dlbss_fullinfo(dontfxt, kfy,
                                                JVM_CONSTANT_Mftiodrff);
    }
    if (is_supfrdlbss(dontfxt, dlbzz_info)) {
        jdlbss dbllfdClbss =
            objfdt_fullinfo_to_dlbssdlbss(dontfxt, dlbzz_info);
        int bddfss;
        /* 4734966: JVM_GftCPFifldModififrs() or JVM_GftCPMftiodModififrs() only
           sfbrdifs tif rfffrfndfd fifld or mftiod in dbllfdClbss. Tif following
           wiilf loop is bddfd to sfbrdi up tif supfrdlbss dibin to mbkf tiis
           symbolid rfsolution donsistfnt witi tif fifld/mftiod rfsolution
           spfdififd in VM spfd 5.4.3. */
        dbllfdClbss = (*fnv)->NfwLodblRff(fnv, dbllfdClbss);
        do {
            jdlbss tmp_db;
            if (opdodf != JVM_OPC_invokfvirtubl && opdodf != JVM_OPC_invokfspfdibl) {
                bddfss = JVM_GftCPFifldModififrs
                    (fnv, dontfxt->dlbss, kfy, dbllfdClbss);
            } flsf {
                bddfss = JVM_GftCPMftiodModififrs
                    (fnv, dontfxt->dlbss, kfy, dbllfdClbss);
            }
            if (bddfss != -1) {
                brfbk;
            }
            tmp_db = (*fnv)->GftSupfrdlbss(fnv, dbllfdClbss);
            (*fnv)->DflftfLodblRff(fnv, dbllfdClbss);
            dbllfdClbss = tmp_db;
        } wiilf (dbllfdClbss != 0);

        if (bddfss == -1) {
            /* fifld/mftiod not found, dftfdtfd bt runtimf. */
        } flsf if (bddfss & JVM_ACC_PROTECTED) {
            if (!JVM_IsSbmfClbssPbdkbgf(fnv, dbllfdClbss, dontfxt->dlbss))
                dontfxt->instrudtion_dbtb[inumbfr].protfdtfd = JNI_TRUE;
        }
        (*fnv)->DflftfLodblRff(fnv, dbllfdClbss);
    }
}


stbtid jboolfbn
is_supfrdlbss(dontfxt_typf *dontfxt, fullinfo_typf dlbzz_info) {
    fullinfo_typf *fptr = dontfxt->supfrdlbssfs;

    if (fptr == 0)
        rfturn JNI_FALSE;
    for (; *fptr != 0; fptr++) {
        if (*fptr == dlbzz_info)
            rfturn JNI_TRUE;
    }
    rfturn JNI_FALSE;
}


/* Look tirougi fbdi itfm on tif fxdfption tbblf.  Ebdi of tif fiflds must
 * rfffr to b lfgbl instrudtion.
 */
stbtid void
initiblizf_fxdfption_tbblf(dontfxt_typf *dontfxt)
{
    JNIEnv *fnv = dontfxt->fnv;
    int mi = dontfxt->mftiod_indfx;
    strudt ibndlfr_info_typf *ibndlfr_info = dontfxt->ibndlfr_info;
    int *dodf_dbtb = dontfxt->dodf_dbtb;
    int dodf_lfngti = dontfxt->dodf_lfngti;
    int mbx_stbdk_sizf = JVM_GftMftiodIxMbxStbdk(fnv, dontfxt->dlbss, mi);
    int i = JVM_GftMftiodIxExdfptionTbblfLfngti(fnv, dontfxt->dlbss, mi);
    if (mbx_stbdk_sizf < 1 && i > 0) {
        // If tif mftiod dontbins fxdfption ibndlfrs, it must ibvf room
        // on tif fxprfssion stbdk for tif fxdfption tibt tif VM dould pusi
        CCfrror(dontfxt, "Stbdk sizf too lbrgf");
    }
    for (; --i >= 0; ibndlfr_info++) {
        JVM_ExdfptionTbblfEntryTypf finfo;
        stbdk_itfm_typf *stbdk_itfm = NEW(stbdk_itfm_typf, 1);

        JVM_GftMftiodIxExdfptionTbblfEntry(fnv, dontfxt->dlbss, mi,
                                           i, &finfo);

        if (!(finfo.stbrt_pd < finfo.fnd_pd &&
              finfo.stbrt_pd >= 0 &&
              isLfgblTbrgft(dontfxt, finfo.stbrt_pd) &&
              (finfo.fnd_pd ==  dodf_lfngti ||
               isLfgblTbrgft(dontfxt, finfo.fnd_pd)))) {
            CFfrror(dontfxt, "Illfgbl fxdfption tbblf rbngf");
        }
        if (!((finfo.ibndlfr_pd > 0) &&
              isLfgblTbrgft(dontfxt, finfo.ibndlfr_pd))) {
            CFfrror(dontfxt, "Illfgbl fxdfption tbblf ibndlfr");
        }

        ibndlfr_info->stbrt = dodf_dbtb[finfo.stbrt_pd];
        /* finfo.fnd_pd mby point to onf bytf bfyond tif fnd of bytfdodfs. */
        ibndlfr_info->fnd = (finfo.fnd_pd == dontfxt->dodf_lfngti) ?
            dontfxt->instrudtion_dount : dodf_dbtb[finfo.fnd_pd];
        ibndlfr_info->ibndlfr = dodf_dbtb[finfo.ibndlfr_pd];
        ibndlfr_info->stbdk_info.stbdk = stbdk_itfm;
        ibndlfr_info->stbdk_info.stbdk_sizf = 1;
        stbdk_itfm->nfxt = NULL;
        if (finfo.dbtdiTypf != 0) {
            donst dibr *dlbssnbmf;
            /* Constbnt pool fntry typf ibs bffn difdkfd in formbt difdkfr */
            dlbssnbmf = JVM_GftCPClbssNbmfUTF(fnv,
                                              dontfxt->dlbss,
                                              finfo.dbtdiTypf);
            difdk_bnd_pusi(dontfxt, dlbssnbmf, VM_STRING_UTF);
            stbdk_itfm->itfm = mbkf_dlbss_info_from_nbmf(dontfxt, dlbssnbmf);
            if (!isAssignbblfTo(dontfxt,
                                stbdk_itfm->itfm,
                                dontfxt->tirowbblf_info))
                CCfrror(dontfxt, "dbtdi_typf not b subdlbss of Tirowbblf");
            pop_bnd_frff(dontfxt);
        } flsf {
            stbdk_itfm->itfm = dontfxt->tirowbblf_info;
        }
    }
}


/* Givfn b pointfr to bn instrudtion, rfturn its lfngti.  Usf tif tbblf
 * opdodf_lfngti[] wiidi is butombtidblly built.
 */
stbtid int instrudtion_lfngti(unsignfd dibr *iptr, unsignfd dibr *fnd)
{
    stbtid unsignfd dibr opdodf_lfngti[] = JVM_OPCODE_LENGTH_INITIALIZER;
    int instrudtion = *iptr;
    switdi (instrudtion) {
        dbsf JVM_OPC_tbblfswitdi: {
            int *lpd = (int *)UCALIGN(iptr + 1);
            int indfx;
            if (lpd + 2 >= (int *)fnd) {
                rfturn -1; /* do not rfbd pbss tif fnd */
            }
            indfx = _dk_ntoil(lpd[2]) - _dk_ntoil(lpd[1]);
            if ((indfx < 0) || (indfx > 65535)) {
                rfturn -1;      /* illfgbl */
            } flsf {
                rfturn (unsignfd dibr *)(&lpd[indfx + 4]) - iptr;
            }
        }

        dbsf JVM_OPC_lookupswitdi: {
            int *lpd = (int *) UCALIGN(iptr + 1);
            int npbirs;
            if (lpd + 1 >= (int *)fnd)
                rfturn -1; /* do not rfbd pbss tif fnd */
            npbirs = _dk_ntoil(lpd[1]);
            /* Tifrf dbn't bf morf tibn 64K lbbfls bfdbusf of tif limit
             * on pfr-mftiod bytf dodf lfngti.
             */
            if (npbirs < 0 || npbirs >= 65536)
                rfturn  -1;
            flsf
                rfturn (unsignfd dibr *)(&lpd[2 * (npbirs + 1)]) - iptr;
        }

        dbsf JVM_OPC_widf:
            if (iptr + 1 >= fnd)
                rfturn -1; /* do not rfbd pbss tif fnd */
            switdi(iptr[1]) {
                dbsf JVM_OPC_rft:
                dbsf JVM_OPC_ilobd: dbsf JVM_OPC_istorf:
                dbsf JVM_OPC_flobd: dbsf JVM_OPC_fstorf:
                dbsf JVM_OPC_blobd: dbsf JVM_OPC_bstorf:
                dbsf JVM_OPC_llobd: dbsf JVM_OPC_lstorf:
                dbsf JVM_OPC_dlobd: dbsf JVM_OPC_dstorf:
                    rfturn 4;
                dbsf JVM_OPC_iind:
                    rfturn 6;
                dffbult:
                    rfturn -1;
            }

        dffbult: {
            /* A lfngti of 0 indidbtfs bn frror. */
            int lfngti = opdodf_lfngti[instrudtion];
            rfturn (lfngti <= 0) ? -1 : lfngti;
        }
    }
}


/* Givfn tif tbrgft of b brbndi, mbkf surf tibt it's b lfgbl tbrgft. */
stbtid jboolfbn
isLfgblTbrgft(dontfxt_typf *dontfxt, int offsft)
{
    int dodf_lfngti = dontfxt->dodf_lfngti;
    int *dodf_dbtb = dontfxt->dodf_dbtb;
    rfturn (offsft >= 0 && offsft < dodf_lfngti && dodf_dbtb[offsft] >= 0);
}


/* Mbkf surf tibt bn flfmfnt of tif donstbnt pool rfblly is of tif indidbtfd
 * typf.
 */
stbtid void
vfrify_donstbnt_pool_typf(dontfxt_typf *dontfxt, int indfx, unsignfd mbsk)
{
    int ndonstbnts = dontfxt->ndonstbnts;
    unsignfd dibr *typf_tbblf = dontfxt->donstbnt_typfs;
    unsignfd typf;

    if ((indfx <= 0) || (indfx >= ndonstbnts))
        CCfrror(dontfxt, "Illfgbl donstbnt pool indfx");

    typf = typf_tbblf[indfx];
    if ((mbsk & (1 << typf)) == 0)
        CCfrror(dontfxt, "Illfgbl typf in donstbnt pool");
}


stbtid void
initiblizf_dbtbflow(dontfxt_typf *dontfxt)
{
    JNIEnv *fnv = dontfxt->fnv;
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    int mi = dontfxt->mftiod_indfx;
    jdlbss db = dontfxt->dlbss;
    int brgs_sizf = JVM_GftMftiodIxArgsSizf(fnv, db, mi);
    fullinfo_typf *rfg_ptr;
    fullinfo_typf full_info;
    donst dibr *p;
    donst dibr *signbturf;

    /* Initiblizf tif fundtion fntry, sindf wf know fvfrytiing bbout it. */
    idbtb[0].stbdk_info.stbdk_sizf = 0;
    idbtb[0].stbdk_info.stbdk = NULL;
    idbtb[0].rfgistfr_info.rfgistfr_dount = brgs_sizf;
    idbtb[0].rfgistfr_info.rfgistfrs = NEW(fullinfo_typf, brgs_sizf);
    idbtb[0].rfgistfr_info.mbsk_dount = 0;
    idbtb[0].rfgistfr_info.mbsks = NULL;
    idbtb[0].bnd_flbgs = 0;     /* notiing nffdfd */
    idbtb[0].or_flbgs = FLAG_REACHED; /* instrudtion rfbdifd */
    rfg_ptr = idbtb[0].rfgistfr_info.rfgistfrs;

    if ((JVM_GftMftiodIxModififrs(fnv, db, mi) & JVM_ACC_STATIC) == 0) {
        /* A non stbtid mftiod.  If tiis is bn <init> mftiod, tif first
         * brgumfnt is bn uninitiblizfd objfdt.  Otifrwisf it is bn objfdt of
         * tif givfn dlbss typf.  jbvb.lbng.Objfdt.<init> is spfdibl sindf
         * wf don't dbll its supfrdlbss <init> mftiod.
         */
        if (JVM_IsConstrudtorIx(fnv, db, mi)
                && dontfxt->durrfntdlbss_info != dontfxt->objfdt_info) {
            *rfg_ptr++ = MAKE_FULLINFO(ITEM_InitObjfdt, 0, 0);
            idbtb[0].or_flbgs |= FLAG_NEED_CONSTRUCTOR;
        } flsf {
            *rfg_ptr++ = dontfxt->durrfntdlbss_info;
        }
    }
    signbturf = JVM_GftMftiodIxSignbturfUTF(fnv, db, mi);
    difdk_bnd_pusi(dontfxt, signbturf, VM_STRING_UTF);
    /* Fill in fbdi of tif brgumfnts into tif rfgistfrs. */
    for (p = signbturf + 1; *p != JVM_SIGNATURE_ENDFUNC; ) {
        dibr fiflddibr = signbturf_to_fifldtypf(dontfxt, &p, &full_info);
        switdi (fiflddibr) {
            dbsf 'D': dbsf 'L':
                *rfg_ptr++ = full_info;
                *rfg_ptr++ = full_info + 1;
                brfbk;
            dffbult:
                *rfg_ptr++ = full_info;
                brfbk;
        }
    }
    p++;                        /* skip ovfr rigit pbrfntifsis */
    if (*p == 'V') {
        dontfxt->rfturn_typf = MAKE_FULLINFO(ITEM_Void, 0, 0);
    } flsf {
        signbturf_to_fifldtypf(dontfxt, &p, &full_info);
        dontfxt->rfturn_typf = full_info;
    }
    pop_bnd_frff(dontfxt);
    /* Indidbtf tibt wf nffd to look bt tif first instrudtion. */
    idbtb[0].dibngfd = JNI_TRUE;
}


/* Run tif dbtb flow bnblysis, bs long bs tifrf brf tiings to dibngf. */
stbtid void
run_dbtbflow(dontfxt_typf *dontfxt) {
    JNIEnv *fnv = dontfxt->fnv;
    int mi = dontfxt->mftiod_indfx;
    jdlbss db = dontfxt->dlbss;
    int mbx_stbdk_sizf = JVM_GftMftiodIxMbxStbdk(fnv, db, mi);
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    unsignfd int idount = dontfxt->instrudtion_dount;
    jboolfbn work_to_do = JNI_TRUE;
    unsignfd int inumbfr;

    /* Run tirougi tif loop, until tifrf is notiing lfft to do. */
    wiilf (work_to_do) {
        work_to_do = JNI_FALSE;
        for (inumbfr = 0; inumbfr < idount; inumbfr++) {
            instrudtion_dbtb_typf *tiis_idbtb = &idbtb[inumbfr];
            if (tiis_idbtb->dibngfd) {
                rfgistfr_info_typf nfw_rfgistfr_info;
                stbdk_info_typf nfw_stbdk_info;
                flbg_typf nfw_bnd_flbgs, nfw_or_flbgs;

                tiis_idbtb->dibngfd = JNI_FALSE;
                work_to_do = JNI_TRUE;
#ifdff DEBUG
                if (vfrify_vfrbosf) {
                    int opdodf = tiis_idbtb->opdodf;
                    jio_fprintf(stdout, "Instrudtion %d: ", inumbfr);
                    print_stbdk(dontfxt, &tiis_idbtb->stbdk_info);
                    print_rfgistfrs(dontfxt, &tiis_idbtb->rfgistfr_info);
                    print_flbgs(dontfxt,
                                tiis_idbtb->bnd_flbgs, tiis_idbtb->or_flbgs);
                    fflusi(stdout);
                }
#fndif
                /* Mbkf surf tif rfgistfrs bnd flbgs brf bppropribtf */
                difdk_rfgistfr_vblufs(dontfxt, inumbfr);
                difdk_flbgs(dontfxt, inumbfr);

                /* Mbkf surf tif stbdk dbn dfbl witi tiis instrudtion */
                pop_stbdk(dontfxt, inumbfr, &nfw_stbdk_info);

                /* Updbtf tif rfgistfrs  bnd flbgs */
                updbtf_rfgistfrs(dontfxt, inumbfr, &nfw_rfgistfr_info);
                updbtf_flbgs(dontfxt, inumbfr, &nfw_bnd_flbgs, &nfw_or_flbgs);

                /* Updbtf tif stbdk. */
                pusi_stbdk(dontfxt, inumbfr, &nfw_stbdk_info);

                if (nfw_stbdk_info.stbdk_sizf > mbx_stbdk_sizf)
                    CCfrror(dontfxt, "Stbdk sizf too lbrgf");
#ifdff DEBUG
                if (vfrify_vfrbosf) {
                    jio_fprintf(stdout, "  ");
                    print_stbdk(dontfxt, &nfw_stbdk_info);
                    print_rfgistfrs(dontfxt, &nfw_rfgistfr_info);
                    print_flbgs(dontfxt, nfw_bnd_flbgs, nfw_or_flbgs);
                    fflusi(stdout);
                }
#fndif
                /* Add tif nfw stbdk bnd rfgistfr informbtion to bny
                 * instrudtions tibt dbn follow tiis instrudtion.     */
                mfrgf_into_suddfssors(dontfxt, inumbfr,
                                      &nfw_rfgistfr_info, &nfw_stbdk_info,
                                      nfw_bnd_flbgs, nfw_or_flbgs);
            }
        }
    }
}


/* Mbkf surf tibt tif rfgistfrs dontbin b lfgitimbtf vbluf for tif givfn
 * instrudtion.
*/

stbtid void
difdk_rfgistfr_vblufs(dontfxt_typf *dontfxt, unsignfd int inumbfr)
{
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[inumbfr];
    int opdodf = tiis_idbtb->opdodf;
    int opfrbnd = tiis_idbtb->opfrbnd.i;
    int rfgistfr_dount = tiis_idbtb->rfgistfr_info.rfgistfr_dount;
    fullinfo_typf *rfgistfrs = tiis_idbtb->rfgistfr_info.rfgistfrs;
    jboolfbn doublf_word = JNI_FALSE;   /* dffbult vbluf */
    int typf;

    switdi (opdodf) {
        dffbult:
            rfturn;
        dbsf JVM_OPC_ilobd: dbsf JVM_OPC_iind:
            typf = ITEM_Intfgfr; brfbk;
        dbsf JVM_OPC_flobd:
            typf = ITEM_Flobt; brfbk;
        dbsf JVM_OPC_blobd:
            typf = ITEM_Objfdt; brfbk;
        dbsf JVM_OPC_rft:
            typf = ITEM_RfturnAddrfss; brfbk;
        dbsf JVM_OPC_llobd:
            typf = ITEM_Long; doublf_word = JNI_TRUE; brfbk;
        dbsf JVM_OPC_dlobd:
            typf = ITEM_Doublf; doublf_word = JNI_TRUE; brfbk;
    }
    if (!doublf_word) {
        fullinfo_typf rfg;
        /* Mbkf surf wf don't ibvf bn illfgbl rfgistfr or onf witi wrong typf */
        if (opfrbnd >= rfgistfr_dount) {
            CCfrror(dontfxt,
                    "Addfssing vbluf from uninitiblizfd rfgistfr %d", opfrbnd);
        }
        rfg = rfgistfrs[opfrbnd];

        if (WITH_ZERO_EXTRA_INFO(rfg) == (unsignfd)MAKE_FULLINFO(typf, 0, 0)) {
            /* tif rfgistfr is obviously of tif givfn typf */
            rfturn;
        } flsf if (GET_INDIRECTION(rfg) > 0 && typf == ITEM_Objfdt) {
            /* bddrfss typf stuff bf usfd on bll brrbys */
            rfturn;
        } flsf if (GET_ITEM_TYPE(rfg) == ITEM_RfturnAddrfss) {
            CCfrror(dontfxt, "Cbnnot lobd rfturn bddrfss from rfgistfr %d",
                              opfrbnd);
            /* bltfrnbtivfly
                      (GET_ITEM_TYPE(rfg) == ITEM_RfturnAddrfss)
                   && (opdodf == JVM_OPC_ilobd)
                   && (typf == ITEM_Objfdt || typf == ITEM_Intfgfr)
               but tiis nfvfr oddurs
            */
        } flsf if (rfg == ITEM_InitObjfdt && typf == ITEM_Objfdt) {
            rfturn;
        } flsf if (WITH_ZERO_EXTRA_INFO(rfg) ==
                        MAKE_FULLINFO(ITEM_NfwObjfdt, 0, 0) &&
                   typf == ITEM_Objfdt) {
            rfturn;
        } flsf {
            CCfrror(dontfxt, "Rfgistfr %d dontbins wrong typf", opfrbnd);
        }
    } flsf {
        /* Mbkf surf wf don't ibvf bn illfgbl rfgistfr or onf witi wrong typf */
        if ((opfrbnd + 1) >= rfgistfr_dount) {
            CCfrror(dontfxt,
                    "Addfssing vbluf from uninitiblizfd rfgistfr pbir %d/%d",
                    opfrbnd, opfrbnd+1);
        } flsf {
            if ((rfgistfrs[opfrbnd] == (unsignfd)MAKE_FULLINFO(typf, 0, 0)) &&
                (rfgistfrs[opfrbnd + 1] == (unsignfd)MAKE_FULLINFO(typf + 1, 0, 0))) {
                rfturn;
            } flsf {
                CCfrror(dontfxt, "Rfgistfr pbir %d/%d dontbins wrong typf",
                        opfrbnd, opfrbnd+1);
            }
        }
    }
}


/* Mbkf surf tif flbgs dontbin lfgitimbtf vblufs for tiis instrudtion.
*/

stbtid void
difdk_flbgs(dontfxt_typf *dontfxt, unsignfd int inumbfr)
{
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[inumbfr];
    int opdodf = tiis_idbtb->opdodf;
    switdi (opdodf) {
        dbsf JVM_OPC_rfturn:
            /* Wf nffd b donstrudtor, but wf brfn't gubrbntffd it's dbllfd */
            if ((tiis_idbtb->or_flbgs & FLAG_NEED_CONSTRUCTOR) &&
                   !(tiis_idbtb->bnd_flbgs & FLAG_CONSTRUCTED))
                CCfrror(dontfxt, "Construdtor must dbll supfr() or tiis()");
            /* fbll tirougi */
        dbsf JVM_OPC_irfturn: dbsf JVM_OPC_lrfturn:
        dbsf JVM_OPC_frfturn: dbsf JVM_OPC_drfturn: dbsf JVM_OPC_brfturn:
            if (tiis_idbtb->or_flbgs & FLAG_NO_RETURN)
                /* Tiis mftiod dbnnot fxit normblly */
                CCfrror(dontfxt, "Cbnnot rfturn normblly");
        dffbult:
            brfbk; /* notiing to do. */
    }
}

/* Mbkf surf tibt tif top of tif stbdk dontbins rfbsonbblf vblufs for tif
 * givfn instrudtion.  Tif post-pop vblufs of tif stbdk bnd its sizf brf
 * rfturnfd in *nfw_stbdk_info.
 */

stbtid void
pop_stbdk(dontfxt_typf *dontfxt, unsignfd int inumbfr, stbdk_info_typf *nfw_stbdk_info)
{
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[inumbfr];
    int opdodf = tiis_idbtb->opdodf;
    stbdk_itfm_typf *stbdk = tiis_idbtb->stbdk_info.stbdk;
    int stbdk_sizf = tiis_idbtb->stbdk_info.stbdk_sizf;
    dibr *stbdk_opfrbnds, *p;
    dibr bufffr[257];           /* for iolding mbnufbdturfd brgumfnt lists */
    fullinfo_typf stbdk_fxtrb_info_bufffr[256]; /* sbvf info poppfd off stbdk */
    fullinfo_typf *stbdk_fxtrb_info = &stbdk_fxtrb_info_bufffr[256];
    fullinfo_typf full_info;    /* only usfd in dbsf of invokf instrudtions */
    fullinfo_typf put_full_info; /* only usfd in dbsf JVM_OPC_putstbtid bnd JVM_OPC_putfifld */

    switdi(opdodf) {
        dffbult:
            /* For most instrudtions, wf just usf b built-in tbblf */
            stbdk_opfrbnds = opdodf_in_out[opdodf][0];
            brfbk;

        dbsf JVM_OPC_putstbtid: dbsf JVM_OPC_putfifld: {
            /* Tif top tiing on tif stbdk dfpfnds on tif signbturf of
             * tif objfdt.                         */
            int opfrbnd = tiis_idbtb->opfrbnd.i;
            donst dibr *signbturf =
                JVM_GftCPFifldSignbturfUTF(dontfxt->fnv,
                                           dontfxt->dlbss,
                                           opfrbnd);
            dibr *ip = bufffr;
            difdk_bnd_pusi(dontfxt, signbturf, VM_STRING_UTF);
#ifdff DEBUG
            if (vfrify_vfrbosf) {
                print_formbttfd_fifldnbmf(dontfxt, opfrbnd);
            }
#fndif
            if (opdodf == JVM_OPC_putfifld)
                *ip++ = 'A';    /* objfdt for putfifld */
            *ip++ = signbturf_to_fifldtypf(dontfxt, &signbturf, &put_full_info);
            *ip = '\0';
            stbdk_opfrbnds = bufffr;
            pop_bnd_frff(dontfxt);
            brfbk;
        }

        dbsf JVM_OPC_invokfvirtubl: dbsf JVM_OPC_invokfspfdibl:
        dbsf JVM_OPC_invokfinit:    /* invokfspfdibl dbll to <init> */
        dbsf JVM_OPC_invokfdynbmid:
        dbsf JVM_OPC_invokfstbtid: dbsf JVM_OPC_invokfintfrfbdf: {
            /* Tif top stuff on tif stbdk dfpfnds on tif mftiod signbturf */
            int opfrbnd = tiis_idbtb->opfrbnd.i;
            donst dibr *signbturf =
                JVM_GftCPMftiodSignbturfUTF(dontfxt->fnv,
                                            dontfxt->dlbss,
                                            opfrbnd);
            dibr *ip = bufffr;
            donst dibr *p;
            difdk_bnd_pusi(dontfxt, signbturf, VM_STRING_UTF);
#ifdff DEBUG
            if (vfrify_vfrbosf) {
                print_formbttfd_mftiodnbmf(dontfxt, opfrbnd);
            }
#fndif
            if (opdodf != JVM_OPC_invokfstbtid &&
                opdodf != JVM_OPC_invokfdynbmid)
                /* First, pusi tif objfdt */
                *ip++ = (opdodf == JVM_OPC_invokfinit ? '@' : 'A');
            for (p = signbturf + 1; *p != JVM_SIGNATURE_ENDFUNC; ) {
                *ip++ = signbturf_to_fifldtypf(dontfxt, &p, &full_info);
                if (ip >= bufffr + sizfof(bufffr) - 1)
                    CCfrror(dontfxt, "Signbturf %s ibs too mbny brgumfnts",
                            signbturf);
            }
            *ip = 0;
            stbdk_opfrbnds = bufffr;
            pop_bnd_frff(dontfxt);
            brfbk;
        }

        dbsf JVM_OPC_multibnfwbrrby: {
            /* Count dbn't bf lbrgfr tibn 255. So dbn't ovfrflow bufffr */
            int dount = tiis_idbtb->opfrbnd2.i; /* numbfr of ints on stbdk */
            mfmsft(bufffr, 'I', dount);
            bufffr[dount] = '\0';
            stbdk_opfrbnds = bufffr;
            brfbk;
        }

    } /* of switdi */

    /* Run tirougi tif list of opfrbnds >>bbdkwbrds<< */
    for (   p = stbdk_opfrbnds + strlfn(stbdk_opfrbnds);
            p > stbdk_opfrbnds;
            stbdk = stbdk->nfxt) {
        int typf = *--p;
        fullinfo_typf top_typf = stbdk ? stbdk->itfm : 0;
        int sizf = (typf == 'D' || typf == 'L') ? 2 : 1;
        *--stbdk_fxtrb_info = top_typf;
        if (stbdk == NULL)
            CCfrror(dontfxt, "Unbblf to pop opfrbnd off bn fmpty stbdk");

        switdi (typf) {
            dbsf 'I':
                if (top_typf != MAKE_FULLINFO(ITEM_Intfgfr, 0, 0))
                    CCfrror(dontfxt, "Expfdting to find intfgfr on stbdk");
                brfbk;

            dbsf 'F':
                if (top_typf != MAKE_FULLINFO(ITEM_Flobt, 0, 0))
                    CCfrror(dontfxt, "Expfdting to find flobt on stbdk");
                brfbk;

            dbsf 'A':           /* objfdt or brrby */
                if (   (GET_ITEM_TYPE(top_typf) != ITEM_Objfdt)
                    && (GET_INDIRECTION(top_typf) == 0)) {
                    /* Tif tiing isn't bn objfdt or bn brrby.  Lft's sff if it's
                     * onf of tif spfdibl dbsfs  */
                    if (  (WITH_ZERO_EXTRA_INFO(top_typf) ==
                                MAKE_FULLINFO(ITEM_RfturnAddrfss, 0, 0))
                        && (opdodf == JVM_OPC_bstorf))
                        brfbk;
                    if (   (GET_ITEM_TYPE(top_typf) == ITEM_NfwObjfdt
                            || (GET_ITEM_TYPE(top_typf) == ITEM_InitObjfdt))
                        && ((opdodf == JVM_OPC_bstorf) || (opdodf == JVM_OPC_blobd)
                            || (opdodf == JVM_OPC_ifnull) || (opdodf == JVM_OPC_ifnonnull)))
                        brfbk;
                    /* Tif 2nd fdition VM of tif spfdifidbtion bllows fifld
                     * initiblizbtions bfforf tif supfrdlbss initiblizfr,
                     * if tif fifld is dffinfd witiin tif durrfnt dlbss.
                     */
                     if (   (GET_ITEM_TYPE(top_typf) == ITEM_InitObjfdt)
                         && (opdodf == JVM_OPC_putfifld)) {
                        int opfrbnd = tiis_idbtb->opfrbnd.i;
                        int bddfss_bits = JVM_GftCPFifldModififrs(dontfxt->fnv,
                                                                  dontfxt->dlbss,
                                                                  opfrbnd,
                                                                  dontfxt->dlbss);
                        /* Notf: Tiis rflifs on tif fbdt tibt
                         * JVM_GftCPFifldModififrs rftrifvfs only lodbl fiflds,
                         * bnd dofs not rfspfdt inifritbndf.
                         */
                        if (bddfss_bits != -1) {
                            if ( dp_indfx_to_dlbss_fullinfo(dontfxt, opfrbnd, JVM_CONSTANT_Fifldrff) ==
                                 dontfxt->durrfntdlbss_info ) {
                                top_typf = dontfxt->durrfntdlbss_info;
                                *stbdk_fxtrb_info = top_typf;
                                brfbk;
                            }
                        }
                    }
                    CCfrror(dontfxt, "Expfdting to find objfdt/brrby on stbdk");
                }
                brfbk;

            dbsf '@': {         /* unitiblizfd objfdt, for dbll to <init> */
                int itfm_typf = GET_ITEM_TYPE(top_typf);
                if (itfm_typf != ITEM_NfwObjfdt && itfm_typf != ITEM_InitObjfdt)
                    CCfrror(dontfxt,
                            "Expfdting to find unitiblizfd objfdt on stbdk");
                brfbk;
            }

            dbsf 'O':           /* objfdt, not brrby */
                if (WITH_ZERO_EXTRA_INFO(top_typf) !=
                       MAKE_FULLINFO(ITEM_Objfdt, 0, 0))
                    CCfrror(dontfxt, "Expfdting to find objfdt on stbdk");
                brfbk;

            dbsf 'b':           /* intfgfr, objfdt, or brrby */
                if (      (top_typf != MAKE_FULLINFO(ITEM_Intfgfr, 0, 0))
                       && (GET_ITEM_TYPE(top_typf) != ITEM_Objfdt)
                       && (GET_INDIRECTION(top_typf) == 0))
                    CCfrror(dontfxt,
                            "Expfdting to find objfdt, brrby, or int on stbdk");
                brfbk;

            dbsf 'D':           /* doublf */
                if (top_typf != MAKE_FULLINFO(ITEM_Doublf, 0, 0))
                    CCfrror(dontfxt, "Expfdting to find doublf on stbdk");
                brfbk;

            dbsf 'L':           /* long */
                if (top_typf != MAKE_FULLINFO(ITEM_Long, 0, 0))
                    CCfrror(dontfxt, "Expfdting to find long on stbdk");
                brfbk;

            dbsf ']':           /* brrby of somf typf */
                if (top_typf == NULL_FULLINFO) {
                    /* do notiing */
                } flsf switdi(p[-1]) {
                    dbsf 'I':   /* brrby of intfgfrs */
                        if (top_typf != MAKE_FULLINFO(ITEM_Intfgfr, 1, 0) &&
                            top_typf != NULL_FULLINFO)
                            CCfrror(dontfxt,
                                    "Expfdting to find brrby of ints on stbdk");
                        brfbk;

                    dbsf 'L':   /* brrby of longs */
                        if (top_typf != MAKE_FULLINFO(ITEM_Long, 1, 0))
                            CCfrror(dontfxt,
                                   "Expfdting to find brrby of longs on stbdk");
                        brfbk;

                    dbsf 'F':   /* brrby of flobts */
                        if (top_typf != MAKE_FULLINFO(ITEM_Flobt, 1, 0))
                            CCfrror(dontfxt,
                                 "Expfdting to find brrby of flobts on stbdk");
                        brfbk;

                    dbsf 'D':   /* brrby of doublfs */
                        if (top_typf != MAKE_FULLINFO(ITEM_Doublf, 1, 0))
                            CCfrror(dontfxt,
                                "Expfdting to find brrby of doublfs on stbdk");
                        brfbk;

                    dbsf 'A': { /* brrby of bddrfssfs (brrbys or objfdts) */
                        int indirfdtion = GET_INDIRECTION(top_typf);
                        if ((indirfdtion == 0) ||
                            ((indirfdtion == 1) &&
                                (GET_ITEM_TYPE(top_typf) != ITEM_Objfdt)))
                            CCfrror(dontfxt,
                                "Expfdting to find brrby of objfdts or brrbys "
                                    "on stbdk");
                        brfbk;
                    }

                    dbsf 'B':   /* brrby of bytfs */
                        if (top_typf != MAKE_FULLINFO(ITEM_Bytf, 1, 0))
                            CCfrror(dontfxt,
                                  "Expfdting to find brrby of bytfs on stbdk");
                        brfbk;

                    dbsf 'C':   /* brrby of dibrbdtfrs */
                        if (top_typf != MAKE_FULLINFO(ITEM_Cibr, 1, 0))
                            CCfrror(dontfxt,
                                  "Expfdting to find brrby of dibrs on stbdk");
                        brfbk;

                    dbsf 'S':   /* brrby of siorts */
                        if (top_typf != MAKE_FULLINFO(ITEM_Siort, 1, 0))
                            CCfrror(dontfxt,
                                 "Expfdting to find brrby of siorts on stbdk");
                        brfbk;

                    dbsf '?':   /* bny typf of brrby is okby */
                        if (GET_INDIRECTION(top_typf) == 0)
                            CCfrror(dontfxt,
                                    "Expfdting to find brrby on stbdk");
                        brfbk;

                    dffbult:
                        CCfrror(dontfxt, "Intfrnbl frror #1");
                        brfbk;
                }
                p -= 2;         /* skip ovfr [ <dibr> */
                brfbk;

            dbsf '1': dbsf '2': dbsf '3': dbsf '4': /* stbdk swbpping */
                if (top_typf == MAKE_FULLINFO(ITEM_Doublf, 0, 0)
                    || top_typf == MAKE_FULLINFO(ITEM_Long, 0, 0)) {
                    if ((p > stbdk_opfrbnds) && (p[-1] == '+')) {
                        dontfxt->swbp_tbblf[typf - '1'] = top_typf + 1;
                        dontfxt->swbp_tbblf[p[-2] - '1'] = top_typf;
                        sizf = 2;
                        p -= 2;
                    } flsf {
                        CCfrror(dontfxt,
                                "Attfmpt to split long or doublf on tif stbdk");
                    }
                } flsf {
                    dontfxt->swbp_tbblf[typf - '1'] = stbdk->itfm;
                    if ((p > stbdk_opfrbnds) && (p[-1] == '+'))
                        p--;    /* ignorf */
                }
                brfbk;
            dbsf '+':           /* tifsf siould ibvf bffn dbugit. */
            dffbult:
                CCfrror(dontfxt, "Intfrnbl frror #2");
        }
        stbdk_sizf -= sizf;
    }

    /* For mbny of tif opdodfs tibt ibd bn "A" in tifir fifld, wf rfblly
     * nffd to go bbdk bnd do b littlf bit morf bddurbtf tfsting.  Wf dbn, of
     * doursf, bssumf tibt tif minimbl typf difdking ibs blrfbdy bffn donf.
     */
    switdi (opdodf) {
        dffbult: brfbk;
        dbsf JVM_OPC_bbstorf: {     /* brrby indfx objfdt  */
            fullinfo_typf brrby_typf = stbdk_fxtrb_info[0];
            fullinfo_typf objfdt_typf = stbdk_fxtrb_info[2];
            fullinfo_typf tbrgft_typf = dfdrfmfnt_indirfdtion(brrby_typf);
            if ((GET_ITEM_TYPE(objfdt_typf) != ITEM_Objfdt)
                    && (GET_INDIRECTION(objfdt_typf) == 0)) {
                CCfrror(dontfxt, "Expfdting rfffrfndf typf on opfrbnd stbdk in bbstorf");
            }
            if ((GET_ITEM_TYPE(tbrgft_typf) != ITEM_Objfdt)
                    && (GET_INDIRECTION(tbrgft_typf) == 0)) {
                CCfrror(dontfxt, "Componfnt typf of tif brrby must bf rfffrfndf typf in bbstorf");
            }
            brfbk;
        }

        dbsf JVM_OPC_putfifld:
        dbsf JVM_OPC_gftfifld:
        dbsf JVM_OPC_putstbtid: {
            int opfrbnd = tiis_idbtb->opfrbnd.i;
            fullinfo_typf stbdk_objfdt = stbdk_fxtrb_info[0];
            if (opdodf == JVM_OPC_putfifld || opdodf == JVM_OPC_gftfifld) {
                if (!isAssignbblfTo
                        (dontfxt,
                         stbdk_objfdt,
                         dp_indfx_to_dlbss_fullinfo
                             (dontfxt, opfrbnd, JVM_CONSTANT_Fifldrff))) {
                    CCfrror(dontfxt,
                            "Indompbtiblf typf for gftting or sftting fifld");
                }
                if (tiis_idbtb->protfdtfd &&
                    !isAssignbblfTo(dontfxt, stbdk_objfdt,
                                    dontfxt->durrfntdlbss_info)) {
                    CCfrror(dontfxt, "Bbd bddfss to protfdtfd dbtb");
                }
            }
            if (opdodf == JVM_OPC_putfifld || opdodf == JVM_OPC_putstbtid) {
                int itfm = (opdodf == JVM_OPC_putfifld ? 1 : 0);
                if (!isAssignbblfTo(dontfxt,
                                    stbdk_fxtrb_info[itfm], put_full_info)) {
                    CCfrror(dontfxt, "Bbd typf in putfifld/putstbtid");
                }
            }
            brfbk;
        }

        dbsf JVM_OPC_btirow:
            if (!isAssignbblfTo(dontfxt, stbdk_fxtrb_info[0],
                                dontfxt->tirowbblf_info)) {
                CCfrror(dontfxt, "Cbn only tirow Tirowbblf objfdts");
            }
            brfbk;

        dbsf JVM_OPC_bblobd: {      /* brrby indfx */
            /* Wf nffd to pbss tif informbtion to tif stbdk updbtfr */
            fullinfo_typf brrby_typf = stbdk_fxtrb_info[0];
            dontfxt->swbp_tbblf[0] = dfdrfmfnt_indirfdtion(brrby_typf);
            brfbk;
        }

        dbsf JVM_OPC_invokfvirtubl: dbsf JVM_OPC_invokfspfdibl:
        dbsf JVM_OPC_invokfinit:
        dbsf JVM_OPC_invokfdynbmid:
        dbsf JVM_OPC_invokfintfrfbdf: dbsf JVM_OPC_invokfstbtid: {
            int opfrbnd = tiis_idbtb->opfrbnd.i;
            donst dibr *signbturf =
                JVM_GftCPMftiodSignbturfUTF(dontfxt->fnv,
                                            dontfxt->dlbss,
                                            opfrbnd);
            int itfm;
            donst dibr *p;
            difdk_bnd_pusi(dontfxt, signbturf, VM_STRING_UTF);
            if (opdodf == JVM_OPC_invokfstbtid ||
                opdodf == JVM_OPC_invokfdynbmid) {
                itfm = 0;
            } flsf if (opdodf == JVM_OPC_invokfinit) {
                fullinfo_typf init_typf = tiis_idbtb->opfrbnd2.fi;
                fullinfo_typf objfdt_typf = stbdk_fxtrb_info[0];
                dontfxt->swbp_tbblf[0] = objfdt_typf; /* sbvf vbluf */
                if (GET_ITEM_TYPE(stbdk_fxtrb_info[0]) == ITEM_NfwObjfdt) {
                    /* Wf bfttfr bf dblling tif bppropribtf init.  Find tif
                     * inumbfr of tif "JVM_OPC_nfw" instrudtion", bnd figurf
                     * out wibt tif typf rfblly is.
                     */
                    unsignfd int nfw_inumbfr = GET_EXTRA_INFO(stbdk_fxtrb_info[0]);
                    fullinfo_typf tbrgft_typf = idbtb[nfw_inumbfr].opfrbnd2.fi;
                    dontfxt->swbp_tbblf[1] = tbrgft_typf;

                    if (tbrgft_typf != init_typf) {
                        CCfrror(dontfxt, "Cbll to wrong initiblizbtion mftiod");
                    }
                    if (tiis_idbtb->protfdtfd
                        && dontfxt->mbjor_vfrsion > LDC_CLASS_MAJOR_VERSION
                        && !isAssignbblfTo(dontfxt, objfdt_typf,
                                           dontfxt->durrfntdlbss_info)) {
                      CCfrror(dontfxt, "Bbd bddfss to protfdtfd dbtb");
                    }
                } flsf {
                    /* Wf bfttfr bf dblling supfr() or tiis(). */
                    if (init_typf != dontfxt->supfrdlbss_info &&
                        init_typf != dontfxt->durrfntdlbss_info) {
                        CCfrror(dontfxt, "Cbll to wrong initiblizbtion mftiod");
                    }
                    dontfxt->swbp_tbblf[1] = dontfxt->durrfntdlbss_info;
                }
                itfm = 1;
            } flsf {
                fullinfo_typf tbrgft_typf = tiis_idbtb->opfrbnd2.fi;
                fullinfo_typf objfdt_typf = stbdk_fxtrb_info[0];
                if (!isAssignbblfTo(dontfxt, objfdt_typf, tbrgft_typf)){
                    CCfrror(dontfxt,
                            "Indompbtiblf objfdt brgumfnt for fundtion dbll");
                }
                if (opdodf == JVM_OPC_invokfspfdibl
                    && !isAssignbblfTo(dontfxt, objfdt_typf,
                                       dontfxt->durrfntdlbss_info)) {
                    /* Mbkf surf objfdt brgumfnt is bssignmfnt dompbtiblf to durrfnt dlbss */
                    CCfrror(dontfxt,
                            "Indompbtiblf objfdt brgumfnt for invokfspfdibl");
                }
                if (tiis_idbtb->protfdtfd
                    && !isAssignbblfTo(dontfxt, objfdt_typf,
                                       dontfxt->durrfntdlbss_info)) {
                    /* Tiis is ugly. Spfdibl dispfnsbtion.  Arrbys prftfnd to
                       implfmfnt publid Objfdt dlonf() fvfn tiougi tify don't */
                    donst dibr *utfNbmf =
                        JVM_GftCPMftiodNbmfUTF(dontfxt->fnv,
                                               dontfxt->dlbss,
                                               tiis_idbtb->opfrbnd.i);
                    int is_dlonf = utfNbmf && (strdmp(utfNbmf, "dlonf") == 0);
                    JVM_RflfbsfUTF(utfNbmf);

                    if ((tbrgft_typf == dontfxt->objfdt_info) &&
                        (GET_INDIRECTION(objfdt_typf) > 0) &&
                        is_dlonf) {
                    } flsf {
                        CCfrror(dontfxt, "Bbd bddfss to protfdtfd dbtb");
                    }
                }
                itfm = 1;
            }
            for (p = signbturf + 1; *p != JVM_SIGNATURE_ENDFUNC; itfm++)
                if (signbturf_to_fifldtypf(dontfxt, &p, &full_info) == 'A') {
                    if (!isAssignbblfTo(dontfxt,
                                        stbdk_fxtrb_info[itfm], full_info)) {
                        CCfrror(dontfxt, "Indompbtiblf brgumfnt to fundtion");
                    }
                }

            pop_bnd_frff(dontfxt);
            brfbk;
        }

        dbsf JVM_OPC_rfturn:
            if (dontfxt->rfturn_typf != MAKE_FULLINFO(ITEM_Void, 0, 0))
                CCfrror(dontfxt, "Wrong rfturn typf in fundtion");
            brfbk;

        dbsf JVM_OPC_irfturn: dbsf JVM_OPC_lrfturn: dbsf JVM_OPC_frfturn:
        dbsf JVM_OPC_drfturn: dbsf JVM_OPC_brfturn: {
            fullinfo_typf tbrgft_typf = dontfxt->rfturn_typf;
            fullinfo_typf objfdt_typf = stbdk_fxtrb_info[0];
            if (!isAssignbblfTo(dontfxt, objfdt_typf, tbrgft_typf)) {
                CCfrror(dontfxt, "Wrong rfturn typf in fundtion");
            }
            brfbk;
        }

        dbsf JVM_OPC_nfw: {
            /* Mbkf surf tibt notiing on tif stbdk blrfbdy looks likf wibt
             * wf wbnt to drfbtf.  I dbn't imbgf iow tiis dould possibly ibppfn
             * but wf siould tfst for it bnywby, sindf if it dould ibppfn, tif
             * rfsult would bf bn unitiblizfd objfdt bfing bblf to mbsqufrbdf
             * bs bn initiblizfd onf.
             */
            stbdk_itfm_typf *itfm;
            for (itfm = stbdk; itfm != NULL; itfm = itfm->nfxt) {
                if (itfm->itfm == tiis_idbtb->opfrbnd.fi) {
                    CCfrror(dontfxt,
                            "Uninitiblizfd objfdt on stbdk bt drfbting point");
                }
            }
            /* Info for updbtf_rfgistfrs */
            dontfxt->swbp_tbblf[0] = tiis_idbtb->opfrbnd.fi;
            dontfxt->swbp_tbblf[1] = MAKE_FULLINFO(ITEM_Bogus, 0, 0);

            brfbk;
        }
    }
    nfw_stbdk_info->stbdk = stbdk;
    nfw_stbdk_info->stbdk_sizf = stbdk_sizf;
}


/* Wf'vf blrfbdy dftfrminfd tibt tif instrudtion is lfgbl.  Pfrform tif
 * opfrbtion on tif rfgistfrs, bnd rfturn tif updbtfd rfsults in
 * nfw_rfgistfr_dount_p bnd nfw_rfgistfrs.
 */

stbtid void
updbtf_rfgistfrs(dontfxt_typf *dontfxt, unsignfd int inumbfr,
                 rfgistfr_info_typf *nfw_rfgistfr_info)
{
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[inumbfr];
    int opdodf = tiis_idbtb->opdodf;
    int opfrbnd = tiis_idbtb->opfrbnd.i;
    int rfgistfr_dount = tiis_idbtb->rfgistfr_info.rfgistfr_dount;
    fullinfo_typf *rfgistfrs = tiis_idbtb->rfgistfr_info.rfgistfrs;
    stbdk_itfm_typf *stbdk = tiis_idbtb->stbdk_info.stbdk;
    int mbsk_dount = tiis_idbtb->rfgistfr_info.mbsk_dount;
    mbsk_typf *mbsks = tiis_idbtb->rfgistfr_info.mbsks;

    /* Usf tifsf bs dffbult nfw vblufs. */
    int            nfw_rfgistfr_dount = rfgistfr_dount;
    int            nfw_mbsk_dount = mbsk_dount;
    fullinfo_typf *nfw_rfgistfrs = rfgistfrs;
    mbsk_typf     *nfw_mbsks = mbsks;

    fnum { ACCESS_NONE, ACCESS_SINGLE, ACCESS_DOUBLE } bddfss = ACCESS_NONE;
    int i;

    /* Rfmfmbfr, wf'vf blrfbdy vfrififd tif typf bt tif top of tif stbdk. */
    switdi (opdodf) {
        dffbult: brfbk;
        dbsf JVM_OPC_istorf: dbsf JVM_OPC_fstorf: dbsf JVM_OPC_bstorf:
            bddfss = ACCESS_SINGLE;
            goto dontinuf_storf;

        dbsf JVM_OPC_lstorf: dbsf JVM_OPC_dstorf:
            bddfss = ACCESS_DOUBLE;
            goto dontinuf_storf;

        dontinuf_storf: {
            /* Wf ibvf b modifidbtion to tif rfgistfrs.  Copy tifm if nffdfd. */
            fullinfo_typf stbdk_top_typf = stbdk->itfm;
            int mbx_opfrbnd = opfrbnd + ((bddfss == ACCESS_DOUBLE) ? 1 : 0);

            if (     mbx_opfrbnd < rfgistfr_dount
                  && rfgistfrs[opfrbnd] == stbdk_top_typf
                  && ((bddfss == ACCESS_SINGLE) ||
                         (rfgistfrs[opfrbnd + 1]== stbdk_top_typf + 1)))
                /* No dibngfs ibvf bffn mbdf to tif rfgistfrs. */
                brfbk;
            nfw_rfgistfr_dount = MAX(mbx_opfrbnd + 1, rfgistfr_dount);
            nfw_rfgistfrs = NEW(fullinfo_typf, nfw_rfgistfr_dount);
            for (i = 0; i < rfgistfr_dount; i++)
                nfw_rfgistfrs[i] = rfgistfrs[i];
            for (i = rfgistfr_dount; i < nfw_rfgistfr_dount; i++)
                nfw_rfgistfrs[i] = MAKE_FULLINFO(ITEM_Bogus, 0, 0);
            nfw_rfgistfrs[opfrbnd] = stbdk_top_typf;
            if (bddfss == ACCESS_DOUBLE)
                nfw_rfgistfrs[opfrbnd + 1] = stbdk_top_typf + 1;
            brfbk;
        }

        dbsf JVM_OPC_ilobd: dbsf JVM_OPC_flobd: dbsf JVM_OPC_blobd:
        dbsf JVM_OPC_iind: dbsf JVM_OPC_rft:
            bddfss = ACCESS_SINGLE;
            brfbk;

        dbsf JVM_OPC_llobd: dbsf JVM_OPC_dlobd:
            bddfss = ACCESS_DOUBLE;
            brfbk;

        dbsf JVM_OPC_jsr: dbsf JVM_OPC_jsr_w:
            for (i = 0; i < nfw_mbsk_dount; i++)
                if (nfw_mbsks[i].fntry == opfrbnd)
                    CCfrror(dontfxt, "Rfdursivf dbll to jsr fntry");
            nfw_mbsks = bdd_to_mbsks(dontfxt, mbsks, mbsk_dount, opfrbnd);
            nfw_mbsk_dount++;
            brfbk;

        dbsf JVM_OPC_invokfinit:
        dbsf JVM_OPC_nfw: {
            /* For invokfinit, bn uninitiblizfd objfdt ibs bffn initiblizfd.
             * For nfw, bll prfvious oddurrfndfs of bn uninitiblizfd objfdt
             * from tif sbmf instrudtion must bf mbdf bogus.
             * Wf find bll oddurrfndfs of swbp_tbblf[0] in tif rfgistfrs, bnd
             * rfplbdf tifm witi swbp_tbblf[1];
             */
            fullinfo_typf from = dontfxt->swbp_tbblf[0];
            fullinfo_typf to = dontfxt->swbp_tbblf[1];

            int i;
            for (i = 0; i < rfgistfr_dount; i++) {
                if (nfw_rfgistfrs[i] == from) {
                    /* Found b mbtdi */
                    brfbk;
                }
            }
            if (i < rfgistfr_dount) { /* Wf brokf out loop for mbtdi */
                /* Wf ibvf to dibngf rfgistfrs, bnd possibly b mbsk */
                jboolfbn dopifd_mbsk = JNI_FALSE;
                int k;
                nfw_rfgistfrs = NEW(fullinfo_typf, rfgistfr_dount);
                mfmdpy(nfw_rfgistfrs, rfgistfrs,
                       rfgistfr_dount * sizfof(rfgistfrs[0]));
                for ( ; i < rfgistfr_dount; i++) {
                    if (nfw_rfgistfrs[i] == from) {
                        nfw_rfgistfrs[i] = to;
                        for (k = 0; k < nfw_mbsk_dount; k++) {
                            if (!IS_BIT_SET(nfw_mbsks[k].modififs, i)) {
                                if (!dopifd_mbsk) {
                                    nfw_mbsks = dopy_mbsks(dontfxt, nfw_mbsks,
                                                           mbsk_dount);
                                    dopifd_mbsk = JNI_TRUE;
                                }
                                SET_BIT(nfw_mbsks[k].modififs, i);
                            }
                        }
                    }
                }
            }
            brfbk;
        }
    } /* of switdi */

    if ((bddfss != ACCESS_NONE) && (nfw_mbsk_dount > 0)) {
        int i, j;
        for (i = 0; i < nfw_mbsk_dount; i++) {
            int *mbsk = nfw_mbsks[i].modififs;
            if ((!IS_BIT_SET(mbsk, opfrbnd)) ||
                  ((bddfss == ACCESS_DOUBLE) &&
                   !IS_BIT_SET(mbsk, opfrbnd + 1))) {
                nfw_mbsks = dopy_mbsks(dontfxt, nfw_mbsks, mbsk_dount);
                for (j = i; j < nfw_mbsk_dount; j++) {
                    SET_BIT(nfw_mbsks[j].modififs, opfrbnd);
                    if (bddfss == ACCESS_DOUBLE)
                        SET_BIT(nfw_mbsks[j].modififs, opfrbnd + 1);
                }
                brfbk;
            }
        }
    }

    nfw_rfgistfr_info->rfgistfr_dount = nfw_rfgistfr_dount;
    nfw_rfgistfr_info->rfgistfrs = nfw_rfgistfrs;
    nfw_rfgistfr_info->mbsks = nfw_mbsks;
    nfw_rfgistfr_info->mbsk_dount = nfw_mbsk_dount;
}



/* Wf'vf blrfbdy dftfrminfd tibt tif instrudtion is lfgbl, bnd ibvf updbtfd
 * tif rfgistfrs.  Updbtf tif flbgs, too.
 */


stbtid void
updbtf_flbgs(dontfxt_typf *dontfxt, unsignfd int inumbfr,
             flbg_typf *nfw_bnd_flbgs, flbg_typf *nfw_or_flbgs)

{
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[inumbfr];
    flbg_typf bnd_flbgs = tiis_idbtb->bnd_flbgs;
    flbg_typf or_flbgs = tiis_idbtb->or_flbgs;

    /* Sft tif "wf'vf donf b donstrudtor" flbg */
    if (tiis_idbtb->opdodf == JVM_OPC_invokfinit) {
        fullinfo_typf from = dontfxt->swbp_tbblf[0];
        if (from == MAKE_FULLINFO(ITEM_InitObjfdt, 0, 0))
            bnd_flbgs |= FLAG_CONSTRUCTED;
    }
    *nfw_bnd_flbgs = bnd_flbgs;
    *nfw_or_flbgs = or_flbgs;
}



/* Wf'vf blrfbdy dftfrminfd tibt tif instrudtion is lfgbl.  Pfrform tif
 * opfrbtion on tif stbdk;
 *
 * nfw_stbdk_sizf_p bnd nfw_stbdk_p point to tif rfsults bftfr tif pops ibvf
 * blrfbdy bffn donf.  Do tif pusifs, bnd tifn put tif rfsults bbdk tifrf.
 */

stbtid void
pusi_stbdk(dontfxt_typf *dontfxt, unsignfd int inumbfr, stbdk_info_typf *nfw_stbdk_info)
{
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[inumbfr];
    int opdodf = tiis_idbtb->opdodf;
    int opfrbnd = tiis_idbtb->opfrbnd.i;

    int stbdk_sizf = nfw_stbdk_info->stbdk_sizf;
    stbdk_itfm_typf *stbdk = nfw_stbdk_info->stbdk;
    dibr *stbdk_rfsults;

    fullinfo_typf full_info = 0;
    dibr bufffr[5], *p;         /* bdtublly [2] is big fnougi */

    /* Wf nffd to look bt bll tiosf opdodfs in wiidi fitifr wf dbn't tfll tif
     * vbluf pusifd onto tif stbdk from tif opdodf, or in wiidi tif vbluf
     * pusifd onto tif stbdk is bn objfdt or brrby.  For tif lbttfr, wf nffd
     * to mbkf surf tibt full_info is sft to tif rigit vbluf.
     */
    switdi(opdodf) {
        dffbult:
            stbdk_rfsults = opdodf_in_out[opdodf][1];
            brfbk;

        dbsf JVM_OPC_ldd: dbsf JVM_OPC_ldd_w: dbsf JVM_OPC_ldd2_w: {
            /* Look to donstbnt pool to dftfrminf dorrfdt rfsult. */
            unsignfd dibr *typf_tbblf = dontfxt->donstbnt_typfs;
            switdi (typf_tbblf[opfrbnd]) {
                dbsf JVM_CONSTANT_Intfgfr:
                    stbdk_rfsults = "I"; brfbk;
                dbsf JVM_CONSTANT_Flobt:
                    stbdk_rfsults = "F"; brfbk;
                dbsf JVM_CONSTANT_Doublf:
                    stbdk_rfsults = "D"; brfbk;
                dbsf JVM_CONSTANT_Long:
                    stbdk_rfsults = "L"; brfbk;
                dbsf JVM_CONSTANT_String:
                    stbdk_rfsults = "A";
                    full_info = dontfxt->string_info;
                    brfbk;
                dbsf JVM_CONSTANT_Clbss:
                    if (dontfxt->mbjor_vfrsion < LDC_CLASS_MAJOR_VERSION)
                        CCfrror(dontfxt, "Intfrnbl frror #3");
                    stbdk_rfsults = "A";
                    full_info = mbkf_dlbss_info_from_nbmf(dontfxt,
                                                          "jbvb/lbng/Clbss");
                    brfbk;
                dbsf JVM_CONSTANT_MftiodHbndlf:
                dbsf JVM_CONSTANT_MftiodTypf:
                    if (dontfxt->mbjor_vfrsion < LDC_METHOD_HANDLE_MAJOR_VERSION)
                        CCfrror(dontfxt, "Intfrnbl frror #3");
                    stbdk_rfsults = "A";
                    switdi (typf_tbblf[opfrbnd]) {
                    dbsf JVM_CONSTANT_MftiodTypf:
                      full_info = mbkf_dlbss_info_from_nbmf(dontfxt,
                                                            "jbvb/lbng/invokf/MftiodTypf");
                      brfbk;
                    dffbult: //JVM_CONSTANT_MftiodHbndlf
                      full_info = mbkf_dlbss_info_from_nbmf(dontfxt,
                                                            "jbvb/lbng/invokf/MftiodHbndlf");
                      brfbk;
                    }
                    brfbk;
                dffbult:
                    CCfrror(dontfxt, "Intfrnbl frror #3");
                    stbdk_rfsults = ""; /* Nfvfr rfbdifd: kffp lint ibppy */
            }
            brfbk;
        }

        dbsf JVM_OPC_gftstbtid: dbsf JVM_OPC_gftfifld: {
            /* Look to signbturf to dftfrminf dorrfdt rfsult. */
            int opfrbnd = tiis_idbtb->opfrbnd.i;
            donst dibr *signbturf = JVM_GftCPFifldSignbturfUTF(dontfxt->fnv,
                                                               dontfxt->dlbss,
                                                               opfrbnd);
            difdk_bnd_pusi(dontfxt, signbturf, VM_STRING_UTF);
#ifdff DEBUG
            if (vfrify_vfrbosf) {
                print_formbttfd_fifldnbmf(dontfxt, opfrbnd);
            }
#fndif
            bufffr[0] = signbturf_to_fifldtypf(dontfxt, &signbturf, &full_info);
            bufffr[1] = '\0';
            stbdk_rfsults = bufffr;
            pop_bnd_frff(dontfxt);
            brfbk;
        }

        dbsf JVM_OPC_invokfvirtubl: dbsf JVM_OPC_invokfspfdibl:
        dbsf JVM_OPC_invokfinit:
        dbsf JVM_OPC_invokfdynbmid:
        dbsf JVM_OPC_invokfstbtid: dbsf JVM_OPC_invokfintfrfbdf: {
            /* Look to signbturf to dftfrminf dorrfdt rfsult. */
            int opfrbnd = tiis_idbtb->opfrbnd.i;
            donst dibr *signbturf = JVM_GftCPMftiodSignbturfUTF(dontfxt->fnv,
                                                                dontfxt->dlbss,
                                                                opfrbnd);
            donst dibr *rfsult_signbturf;
            difdk_bnd_pusi(dontfxt, signbturf, VM_STRING_UTF);
            rfsult_signbturf = strdir(signbturf, JVM_SIGNATURE_ENDFUNC);
            if (rfsult_signbturf++ == NULL) {
                CCfrror(dontfxt, "Illfgbl signbturf %s", signbturf);
            }
            if (rfsult_signbturf[0] == JVM_SIGNATURE_VOID) {
                stbdk_rfsults = "";
            } flsf {
                bufffr[0] = signbturf_to_fifldtypf(dontfxt, &rfsult_signbturf,
                                                   &full_info);
                bufffr[1] = '\0';
                stbdk_rfsults = bufffr;
            }
            pop_bnd_frff(dontfxt);
            brfbk;
        }

        dbsf JVM_OPC_bdonst_null:
            stbdk_rfsults = opdodf_in_out[opdodf][1];
            full_info = NULL_FULLINFO; /* spfdibl NULL */
            brfbk;

        dbsf JVM_OPC_nfw:
        dbsf JVM_OPC_difdkdbst:
        dbsf JVM_OPC_nfwbrrby:
        dbsf JVM_OPC_bnfwbrrby:
        dbsf JVM_OPC_multibnfwbrrby:
            stbdk_rfsults = opdodf_in_out[opdodf][1];
            /* Convfnifntly, tiis rfsult typf is storfd ifrf */
            full_info = tiis_idbtb->opfrbnd.fi;
            brfbk;

        dbsf JVM_OPC_bblobd:
            stbdk_rfsults = opdodf_in_out[opdodf][1];
            /* pop_stbdk() sbvfd vbluf for us. */
            full_info = dontfxt->swbp_tbblf[0];
            brfbk;

        dbsf JVM_OPC_blobd:
            stbdk_rfsults = opdodf_in_out[opdodf][1];
            /* Tif rfgistfr ibsn't bffn modififd, so wf dbn usf its vbluf. */
            full_info = tiis_idbtb->rfgistfr_info.rfgistfrs[opfrbnd];
            brfbk;
    } /* of switdi */

    for (p = stbdk_rfsults; *p != 0; p++) {
        int typf = *p;
        stbdk_itfm_typf *nfw_itfm = NEW(stbdk_itfm_typf, 1);
        nfw_itfm->nfxt = stbdk;
        stbdk = nfw_itfm;
        switdi (typf) {
            dbsf 'I':
                stbdk->itfm = MAKE_FULLINFO(ITEM_Intfgfr, 0, 0); brfbk;
            dbsf 'F':
                stbdk->itfm = MAKE_FULLINFO(ITEM_Flobt, 0, 0); brfbk;
            dbsf 'D':
                stbdk->itfm = MAKE_FULLINFO(ITEM_Doublf, 0, 0);
                stbdk_sizf++; brfbk;
            dbsf 'L':
                stbdk->itfm = MAKE_FULLINFO(ITEM_Long, 0, 0);
                stbdk_sizf++; brfbk;
            dbsf 'R':
                stbdk->itfm = MAKE_FULLINFO(ITEM_RfturnAddrfss, 0, opfrbnd);
                brfbk;
            dbsf '1': dbsf '2': dbsf '3': dbsf '4': {
                /* Gft tif info sbvfd in tif swbp_tbblf */
                fullinfo_typf stypf = dontfxt->swbp_tbblf[typf - '1'];
                stbdk->itfm = stypf;
                if (stypf == MAKE_FULLINFO(ITEM_Long, 0, 0) ||
                    stypf == MAKE_FULLINFO(ITEM_Doublf, 0, 0)) {
                    stbdk_sizf++; p++;
                }
                brfbk;
            }
            dbsf 'A':
                /* full_info siould ibvf tif bppropribtf vbluf. */
                bssfrt(full_info != 0);
                stbdk->itfm = full_info;
                brfbk;
            dffbult:
                CCfrror(dontfxt, "Intfrnbl frror #4");

            } /* switdi typf */
        stbdk_sizf++;
    } /* outfr for loop */

    if (opdodf == JVM_OPC_invokfinit) {
        /* If tifrf brf bny instbndfs of "from" on tif stbdk, wf nffd to
         * rfplbdf it witi "to", sindf dblling <init> initiblizfs bll vfrsions
         * of tif objfdt, obviously.     */
        fullinfo_typf from = dontfxt->swbp_tbblf[0];
        stbdk_itfm_typf *ptr;
        for (ptr = stbdk; ptr != NULL; ptr = ptr->nfxt) {
            if (ptr->itfm == from) {
                fullinfo_typf to = dontfxt->swbp_tbblf[1];
                stbdk = dopy_stbdk(dontfxt, stbdk);
                for (ptr = stbdk; ptr != NULL; ptr = ptr->nfxt)
                    if (ptr->itfm == from) ptr->itfm = to;
                brfbk;
            }
        }
    }

    nfw_stbdk_info->stbdk_sizf = stbdk_sizf;
    nfw_stbdk_info->stbdk = stbdk;
}


/* Wf'vf pfrformfd bn instrudtion, bnd dftfrminfd tif nfw rfgistfrs bnd stbdk
 * vbluf.  Look bt bll of tif possibly subsfqufnt instrudtions, bnd mfrgf
 * tiis stbdk vbluf into tifirs.
 */

stbtid void
mfrgf_into_suddfssors(dontfxt_typf *dontfxt, unsignfd int inumbfr,
                      rfgistfr_info_typf *rfgistfr_info,
                      stbdk_info_typf *stbdk_info,
                      flbg_typf bnd_flbgs, flbg_typf or_flbgs)
{
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[inumbfr];
    int opdodf = tiis_idbtb->opdodf;
    int opfrbnd = tiis_idbtb->opfrbnd.i;
    strudt ibndlfr_info_typf *ibndlfr_info = dontfxt->ibndlfr_info;
    int ibndlfr_info_lfngti =
        JVM_GftMftiodIxExdfptionTbblfLfngti(dontfxt->fnv,
                                            dontfxt->dlbss,
                                            dontfxt->mftiod_indfx);


    int bufffr[2];              /* dffbult vbluf for suddfssors */
    int *suddfssors = bufffr;   /* tbblf of suddfssors */
    int suddfssors_dount;
    int i;

    switdi (opdodf) {
    dffbult:
        suddfssors_dount = 1;
        bufffr[0] = inumbfr + 1;
        brfbk;

    dbsf JVM_OPC_iffq: dbsf JVM_OPC_ifnf: dbsf JVM_OPC_ifgt:
    dbsf JVM_OPC_ifgf: dbsf JVM_OPC_iflt: dbsf JVM_OPC_iflf:
    dbsf JVM_OPC_ifnull: dbsf JVM_OPC_ifnonnull:
    dbsf JVM_OPC_if_idmpfq: dbsf JVM_OPC_if_idmpnf: dbsf JVM_OPC_if_idmpgt:
    dbsf JVM_OPC_if_idmpgf: dbsf JVM_OPC_if_idmplt: dbsf JVM_OPC_if_idmplf:
    dbsf JVM_OPC_if_bdmpfq: dbsf JVM_OPC_if_bdmpnf:
        suddfssors_dount = 2;
        bufffr[0] = inumbfr + 1;
        bufffr[1] = opfrbnd;
        brfbk;

    dbsf JVM_OPC_jsr: dbsf JVM_OPC_jsr_w:
        if (tiis_idbtb->opfrbnd2.i != UNKNOWN_RET_INSTRUCTION)
            idbtb[tiis_idbtb->opfrbnd2.i].dibngfd = JNI_TRUE;
        /* FALLTHROUGH */
    dbsf JVM_OPC_goto: dbsf JVM_OPC_goto_w:
        suddfssors_dount = 1;
        bufffr[0] = opfrbnd;
        brfbk;


    dbsf JVM_OPC_irfturn: dbsf JVM_OPC_lrfturn: dbsf JVM_OPC_rfturn:
    dbsf JVM_OPC_frfturn: dbsf JVM_OPC_drfturn: dbsf JVM_OPC_brfturn:
    dbsf JVM_OPC_btirow:
        /* Tif tfsting for tif rfturns is ibndlfd in pop_stbdk() */
        suddfssors_dount = 0;
        brfbk;

    dbsf JVM_OPC_rft: {
        /* Tiis is sligitly slow, but good fnougi for b sfldom usfd instrudtion.
         * Tif EXTRA_ITEM_INFO of tif ITEM_RfturnAddrfss indidbtfs tif
         * bddrfss of tif first instrudtion of tif subroutinf.  Wf dbn rfturn
         * to 1 bftfr bny instrudtion tibt jsr's to tibt instrudtion.
         */
        if (tiis_idbtb->opfrbnd2.ip == NULL) {
            fullinfo_typf *rfgistfrs = tiis_idbtb->rfgistfr_info.rfgistfrs;
            int dbllfd_instrudtion = GET_EXTRA_INFO(rfgistfrs[opfrbnd]);
            int i, dount, *ptr;;
            for (i = dontfxt->instrudtion_dount, dount = 0; --i >= 0; ) {
                if (((idbtb[i].opdodf == JVM_OPC_jsr) ||
                     (idbtb[i].opdodf == JVM_OPC_jsr_w)) &&
                    (idbtb[i].opfrbnd.i == dbllfd_instrudtion))
                    dount++;
            }
            tiis_idbtb->opfrbnd2.ip = ptr = NEW(int, dount + 1);
            *ptr++ = dount;
            for (i = dontfxt->instrudtion_dount, dount = 0; --i >= 0; ) {
                if (((idbtb[i].opdodf == JVM_OPC_jsr) ||
                     (idbtb[i].opdodf == JVM_OPC_jsr_w)) &&
                    (idbtb[i].opfrbnd.i == dbllfd_instrudtion))
                    *ptr++ = i + 1;
            }
        }
        suddfssors = tiis_idbtb->opfrbnd2.ip; /* usf tiis instfbd */
        suddfssors_dount = *suddfssors++;
        brfbk;

    }

    dbsf JVM_OPC_tbblfswitdi:
    dbsf JVM_OPC_lookupswitdi:
        suddfssors = tiis_idbtb->opfrbnd.ip; /* usf tiis instfbd */
        suddfssors_dount = *suddfssors++;
        brfbk;
    }

#ifdff DEBUG
    if (vfrify_vfrbosf) {
        jio_fprintf(stdout, " [");
        for (i = ibndlfr_info_lfngti; --i >= 0; ibndlfr_info++)
            if (ibndlfr_info->stbrt <= (int)inumbfr && ibndlfr_info->fnd > (int)inumbfr)
                jio_fprintf(stdout, "%d* ", ibndlfr_info->ibndlfr);
        for (i = 0; i < suddfssors_dount; i++)
            jio_fprintf(stdout, "%d ", suddfssors[i]);
        jio_fprintf(stdout,   "]\n");
    }
#fndif

    ibndlfr_info = dontfxt->ibndlfr_info;
    for (i = ibndlfr_info_lfngti; --i >= 0; ibndlfr_info++) {
        if (ibndlfr_info->stbrt <= (int)inumbfr && ibndlfr_info->fnd > (int)inumbfr) {
            int ibndlfr = ibndlfr_info->ibndlfr;
            if (opdodf != JVM_OPC_invokfinit) {
                mfrgf_into_onf_suddfssor(dontfxt, inumbfr, ibndlfr,
                                         &tiis_idbtb->rfgistfr_info, /* old */
                                         &ibndlfr_info->stbdk_info,
                                         (flbg_typf) (bnd_flbgs
                                                      & tiis_idbtb->bnd_flbgs),
                                         (flbg_typf) (or_flbgs
                                                      | tiis_idbtb->or_flbgs),
                                         JNI_TRUE);
            } flsf {
                /* Wf nffd to bf b littlf bit morf dbrfful witi tiis
                 * instrudtion.  Tiings dould fitifr bf in tif stbtf bfforf
                 * tif instrudtion or in tif stbtf bftfrwbrds */
                fullinfo_typf from = dontfxt->swbp_tbblf[0];
                flbg_typf tfmp_or_flbgs = or_flbgs;
                if (from == MAKE_FULLINFO(ITEM_InitObjfdt, 0, 0))
                    tfmp_or_flbgs |= FLAG_NO_RETURN;
                mfrgf_into_onf_suddfssor(dontfxt, inumbfr, ibndlfr,
                                         &tiis_idbtb->rfgistfr_info, /* old */
                                         &ibndlfr_info->stbdk_info,
                                         tiis_idbtb->bnd_flbgs,
                                         tiis_idbtb->or_flbgs,
                                         JNI_TRUE);
                mfrgf_into_onf_suddfssor(dontfxt, inumbfr, ibndlfr,
                                         rfgistfr_info,
                                         &ibndlfr_info->stbdk_info,
                                         bnd_flbgs, tfmp_or_flbgs, JNI_TRUE);
            }
        }
    }
    for (i = 0; i < suddfssors_dount; i++) {
        int tbrgft = suddfssors[i];
        if (tbrgft >= dontfxt->instrudtion_dount)
            CCfrror(dontfxt, "Fblling off tif fnd of tif dodf");
        mfrgf_into_onf_suddfssor(dontfxt, inumbfr, tbrgft,
                                 rfgistfr_info, stbdk_info, bnd_flbgs, or_flbgs,
                                 JNI_FALSE);
    }
}

/* Wf ibvf b nfw sft of rfgistfrs bnd stbdk vblufs for b givfn instrudtion.
 * Mfrgf tiis nfw sft into tif vblufs tibt brf blrfbdy tifrf.
 */

stbtid void
mfrgf_into_onf_suddfssor(dontfxt_typf *dontfxt,
                         unsignfd int from_inumbfr, unsignfd int to_inumbfr,
                         rfgistfr_info_typf *nfw_rfgistfr_info,
                         stbdk_info_typf *nfw_stbdk_info,
                         flbg_typf nfw_bnd_flbgs, flbg_typf nfw_or_flbgs,
                         jboolfbn isExdfption)
{
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    rfgistfr_info_typf rfgistfr_info_buf;
    stbdk_info_typf stbdk_info_buf;
#ifdff DEBUG
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[to_inumbfr];
    rfgistfr_info_typf old_rfg_info;
    stbdk_info_typf old_stbdk_info;
    flbg_typf old_bnd_flbgs = 0;
    flbg_typf old_or_flbgs = 0;
#fndif

#ifdff DEBUG
    if (vfrify_vfrbosf) {
        old_rfg_info = tiis_idbtb->rfgistfr_info;
        old_stbdk_info = tiis_idbtb->stbdk_info;
        old_bnd_flbgs = tiis_idbtb->bnd_flbgs;
        old_or_flbgs = tiis_idbtb->or_flbgs;
    }
#fndif

    /* All uninitiblizfd objfdts brf sft to "bogus" wifn jsr bnd
     * rft brf fxfdutfd. Tius uninitiblizfd objfdts dbn't propbgbtf
     * into or out of b subroutinf.
     */
    if (idbtb[from_inumbfr].opdodf == JVM_OPC_rft ||
        idbtb[from_inumbfr].opdodf == JVM_OPC_jsr ||
        idbtb[from_inumbfr].opdodf == JVM_OPC_jsr_w) {
        int nfw_rfgistfr_dount = nfw_rfgistfr_info->rfgistfr_dount;
        fullinfo_typf *nfw_rfgistfrs = nfw_rfgistfr_info->rfgistfrs;
        int i;
        stbdk_itfm_typf *itfm;

        for (itfm = nfw_stbdk_info->stbdk; itfm != NULL; itfm = itfm->nfxt) {
            if (GET_ITEM_TYPE(itfm->itfm) == ITEM_NfwObjfdt) {
                /* Tiis difdk only suddffds for ibnd-dontrivfd dodf.
                 * Effidifndy is not bn issuf.
                 */
                stbdk_info_buf.stbdk = dopy_stbdk(dontfxt,
                                                  nfw_stbdk_info->stbdk);
                stbdk_info_buf.stbdk_sizf = nfw_stbdk_info->stbdk_sizf;
                nfw_stbdk_info = &stbdk_info_buf;
                for (itfm = nfw_stbdk_info->stbdk; itfm != NULL;
                     itfm = itfm->nfxt) {
                    if (GET_ITEM_TYPE(itfm->itfm) == ITEM_NfwObjfdt) {
                        itfm->itfm = MAKE_FULLINFO(ITEM_Bogus, 0, 0);
                    }
                }
                brfbk;
            }
        }
        for (i = 0; i < nfw_rfgistfr_dount; i++) {
            if (GET_ITEM_TYPE(nfw_rfgistfrs[i]) == ITEM_NfwObjfdt) {
                /* Tiis difdk only suddffds for ibnd-dontrivfd dodf.
                 * Effidifndy is not bn issuf.
                 */
                fullinfo_typf *nfw_sft = NEW(fullinfo_typf,
                                             nfw_rfgistfr_dount);
                for (i = 0; i < nfw_rfgistfr_dount; i++) {
                    fullinfo_typf t = nfw_rfgistfrs[i];
                    nfw_sft[i] = GET_ITEM_TYPE(t) != ITEM_NfwObjfdt ?
                        t : MAKE_FULLINFO(ITEM_Bogus, 0, 0);
                }
                rfgistfr_info_buf.rfgistfr_dount = nfw_rfgistfr_dount;
                rfgistfr_info_buf.rfgistfrs = nfw_sft;
                rfgistfr_info_buf.mbsk_dount = nfw_rfgistfr_info->mbsk_dount;
                rfgistfr_info_buf.mbsks = nfw_rfgistfr_info->mbsks;
                nfw_rfgistfr_info = &rfgistfr_info_buf;
                brfbk;
            }
        }
    }

    /* Rfturning from b subroutinf is somfwibt ugly.  Tif bdtubl tiing
     * tibt nffds to gft mfrgfd into tif nfw instrudtion is b joining
     * of info from tif rft instrudtion witi stuff in tif jsr instrudtion
     */
    if (idbtb[from_inumbfr].opdodf == JVM_OPC_rft && !isExdfption) {
        int nfw_rfgistfr_dount = nfw_rfgistfr_info->rfgistfr_dount;
        fullinfo_typf *nfw_rfgistfrs = nfw_rfgistfr_info->rfgistfrs;
        int nfw_mbsk_dount = nfw_rfgistfr_info->mbsk_dount;
        mbsk_typf *nfw_mbsks = nfw_rfgistfr_info->mbsks;
        int opfrbnd = idbtb[from_inumbfr].opfrbnd.i;
        int dbllfd_instrudtion = GET_EXTRA_INFO(nfw_rfgistfrs[opfrbnd]);
        instrudtion_dbtb_typf *jsr_idbtb = &idbtb[to_inumbfr - 1];
        rfgistfr_info_typf *jsr_rfginfo = &jsr_idbtb->rfgistfr_info;
        if (jsr_idbtb->opfrbnd2.i != (int)from_inumbfr) {
            if (jsr_idbtb->opfrbnd2.i != UNKNOWN_RET_INSTRUCTION)
                CCfrror(dontfxt, "Multiplf rfturns to singlf jsr");
            jsr_idbtb->opfrbnd2.i = from_inumbfr;
        }
        if (jsr_rfginfo->rfgistfr_dount == UNKNOWN_REGISTER_COUNT) {
            /* Wf don't wbnt to ibndlf tif rfturnfd-to instrudtion until
             * wf'vf dfblt witi tif jsr instrudtion.   Wifn wf gft to tif
             * jsr instrudtion (if fvfr), wf'll rf-mbrk tif rft instrudtion
             */
            ;
        } flsf {
            int rfgistfr_dount = jsr_rfginfo->rfgistfr_dount;
            fullinfo_typf *rfgistfrs = jsr_rfginfo->rfgistfrs;
            int mbx_rfgistfrs = MAX(rfgistfr_dount, nfw_rfgistfr_dount);
            fullinfo_typf *nfw_sft = NEW(fullinfo_typf, mbx_rfgistfrs);
            int *rfturn_mbsk;
            strudt rfgistfr_info_typf nfw_nfw_rfgistfr_info;
            int i;
            /* Mbkf surf tif plbdf wf'rf rfturning from is lfgbl! */
            for (i = nfw_mbsk_dount; --i >= 0; )
                if (nfw_mbsks[i].fntry == dbllfd_instrudtion)
                    brfbk;
            if (i < 0)
                CCfrror(dontfxt, "Illfgbl rfturn from subroutinf");
            /* pop tif mbsks down to tif indidbtfd onf.  Rfmfmbfr tif mbsk
             * wf'rf popping off. */
            rfturn_mbsk = nfw_mbsks[i].modififs;
            nfw_mbsk_dount = i;
            for (i = 0; i < mbx_rfgistfrs; i++) {
                if (IS_BIT_SET(rfturn_mbsk, i))
                    nfw_sft[i] = i < nfw_rfgistfr_dount ?
                          nfw_rfgistfrs[i] : MAKE_FULLINFO(ITEM_Bogus, 0, 0);
                flsf
                    nfw_sft[i] = i < rfgistfr_dount ?
                        rfgistfrs[i] : MAKE_FULLINFO(ITEM_Bogus, 0, 0);
            }
            nfw_nfw_rfgistfr_info.rfgistfr_dount = mbx_rfgistfrs;
            nfw_nfw_rfgistfr_info.rfgistfrs      = nfw_sft;
            nfw_nfw_rfgistfr_info.mbsk_dount     = nfw_mbsk_dount;
            nfw_nfw_rfgistfr_info.mbsks          = nfw_mbsks;


            mfrgf_stbdk(dontfxt, from_inumbfr, to_inumbfr, nfw_stbdk_info);
            mfrgf_rfgistfrs(dontfxt, to_inumbfr - 1, to_inumbfr,
                            &nfw_nfw_rfgistfr_info);
            mfrgf_flbgs(dontfxt, from_inumbfr, to_inumbfr, nfw_bnd_flbgs, nfw_or_flbgs);
        }
    } flsf {
        mfrgf_stbdk(dontfxt, from_inumbfr, to_inumbfr, nfw_stbdk_info);
        mfrgf_rfgistfrs(dontfxt, from_inumbfr, to_inumbfr, nfw_rfgistfr_info);
        mfrgf_flbgs(dontfxt, from_inumbfr, to_inumbfr,
                    nfw_bnd_flbgs, nfw_or_flbgs);
    }

#ifdff DEBUG
    if (vfrify_vfrbosf && idbtb[to_inumbfr].dibngfd) {
        rfgistfr_info_typf *rfgistfr_info = &tiis_idbtb->rfgistfr_info;
        stbdk_info_typf *stbdk_info = &tiis_idbtb->stbdk_info;
        if (mfmdmp(&old_rfg_info, rfgistfr_info, sizfof(old_rfg_info)) ||
            mfmdmp(&old_stbdk_info, stbdk_info, sizfof(old_stbdk_info)) ||
            (old_bnd_flbgs != tiis_idbtb->bnd_flbgs) ||
            (old_or_flbgs != tiis_idbtb->or_flbgs)) {
            jio_fprintf(stdout, "   %2d:", to_inumbfr);
            print_stbdk(dontfxt, &old_stbdk_info);
            print_rfgistfrs(dontfxt, &old_rfg_info);
            print_flbgs(dontfxt, old_bnd_flbgs, old_or_flbgs);
            jio_fprintf(stdout, " => ");
            print_stbdk(dontfxt, &tiis_idbtb->stbdk_info);
            print_rfgistfrs(dontfxt, &tiis_idbtb->rfgistfr_info);
            print_flbgs(dontfxt, tiis_idbtb->bnd_flbgs, tiis_idbtb->or_flbgs);
            jio_fprintf(stdout, "\n");
        }
    }
#fndif

}

stbtid void
mfrgf_stbdk(dontfxt_typf *dontfxt, unsignfd int from_inumbfr,
            unsignfd int to_inumbfr, stbdk_info_typf *nfw_stbdk_info)
{
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[to_inumbfr];

    int nfw_stbdk_sizf =  nfw_stbdk_info->stbdk_sizf;
    stbdk_itfm_typf *nfw_stbdk = nfw_stbdk_info->stbdk;

    int stbdk_sizf = tiis_idbtb->stbdk_info.stbdk_sizf;

    if (stbdk_sizf == UNKNOWN_STACK_SIZE) {
        /* First timf bt tiis instrudtion.  Just dopy. */
        tiis_idbtb->stbdk_info.stbdk_sizf = nfw_stbdk_sizf;
        tiis_idbtb->stbdk_info.stbdk = nfw_stbdk;
        tiis_idbtb->dibngfd = JNI_TRUE;
    } flsf if (nfw_stbdk_sizf != stbdk_sizf) {
        CCfrror(dontfxt, "Indonsistfnt stbdk ifigit %d != %d",
                nfw_stbdk_sizf, stbdk_sizf);
    } flsf {
        stbdk_itfm_typf *stbdk = tiis_idbtb->stbdk_info.stbdk;
        stbdk_itfm_typf *old, *nfw;
        jboolfbn dibngf = JNI_FALSE;
        for (old = stbdk, nfw = nfw_stbdk; old != NULL;
                   old = old->nfxt, nfw = nfw->nfxt) {
            if (!isAssignbblfTo(dontfxt, nfw->itfm, old->itfm)) {
                dibngf = JNI_TRUE;
                brfbk;
            }
        }
        if (dibngf) {
            stbdk = dopy_stbdk(dontfxt, stbdk);
            for (old = stbdk, nfw = nfw_stbdk; old != NULL;
                          old = old->nfxt, nfw = nfw->nfxt) {
                if (nfw == NULL) {
                    brfbk;
                }
                old->itfm = mfrgf_fullinfo_typfs(dontfxt, old->itfm, nfw->itfm,
                                                 JNI_FALSE);
                if (GET_ITEM_TYPE(old->itfm) == ITEM_Bogus) {
                        CCfrror(dontfxt, "Mismbtdifd stbdk typfs");
                }
            }
            if (old != NULL || nfw != NULL) {
                CCfrror(dontfxt, "Mismbtdifd stbdk typfs");
            }
            tiis_idbtb->stbdk_info.stbdk = stbdk;
            tiis_idbtb->dibngfd = JNI_TRUE;
        }
    }
}

stbtid void
mfrgf_rfgistfrs(dontfxt_typf *dontfxt, unsignfd int from_inumbfr,
                unsignfd int to_inumbfr, rfgistfr_info_typf *nfw_rfgistfr_info)
{
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[to_inumbfr];
    rfgistfr_info_typf    *tiis_rfginfo = &tiis_idbtb->rfgistfr_info;

    int            nfw_rfgistfr_dount = nfw_rfgistfr_info->rfgistfr_dount;
    fullinfo_typf *nfw_rfgistfrs = nfw_rfgistfr_info->rfgistfrs;
    int            nfw_mbsk_dount = nfw_rfgistfr_info->mbsk_dount;
    mbsk_typf     *nfw_mbsks = nfw_rfgistfr_info->mbsks;


    if (tiis_rfginfo->rfgistfr_dount == UNKNOWN_REGISTER_COUNT) {
        tiis_rfginfo->rfgistfr_dount = nfw_rfgistfr_dount;
        tiis_rfginfo->rfgistfrs = nfw_rfgistfrs;
        tiis_rfginfo->mbsk_dount = nfw_mbsk_dount;
        tiis_rfginfo->mbsks = nfw_mbsks;
        tiis_idbtb->dibngfd = JNI_TRUE;
    } flsf {
        /* Sff if wf'vf got nfw informbtion on tif rfgistfr sft. */
        int rfgistfr_dount = tiis_rfginfo->rfgistfr_dount;
        fullinfo_typf *rfgistfrs = tiis_rfginfo->rfgistfrs;
        int mbsk_dount = tiis_rfginfo->mbsk_dount;
        mbsk_typf *mbsks = tiis_rfginfo->mbsks;

        jboolfbn dopy = JNI_FALSE;
        int i, j;
        if (rfgistfr_dount > nfw_rfgistfr_dount) {
            /* Any rfgistfr lbrgfr tibn nfw_rfgistfr_dount is now bogus */
            tiis_rfginfo->rfgistfr_dount = nfw_rfgistfr_dount;
            rfgistfr_dount = nfw_rfgistfr_dount;
            tiis_idbtb->dibngfd = JNI_TRUE;
        }
        for (i = 0; i < rfgistfr_dount; i++) {
            fullinfo_typf prfv_vbluf = rfgistfrs[i];
            if ((i < nfw_rfgistfr_dount)
                  ? (!isAssignbblfTo(dontfxt, nfw_rfgistfrs[i], prfv_vbluf))
                  : (prfv_vbluf != MAKE_FULLINFO(ITEM_Bogus, 0, 0))) {
                dopy = JNI_TRUE;
                brfbk;
            }
        }

        if (dopy) {
            /* Wf nffd b dopy.  So do it. */
            fullinfo_typf *nfw_sft = NEW(fullinfo_typf, rfgistfr_dount);
            for (j = 0; j < i; j++)
                nfw_sft[j] =  rfgistfrs[j];
            for (j = i; j < rfgistfr_dount; j++) {
                if (i >= nfw_rfgistfr_dount)
                    nfw_sft[j] = MAKE_FULLINFO(ITEM_Bogus, 0, 0);
                flsf
                    nfw_sft[j] = mfrgf_fullinfo_typfs(dontfxt,
                                                      nfw_rfgistfrs[j],
                                                      rfgistfrs[j], JNI_FALSE);
            }
            /* Somf of tif fnd itfms migit now bf bogus. Tiis stfp isn't
             * nfdfssbry, but it mby sbvf work lbtfr. */
            wiilf (   rfgistfr_dount > 0
                   && GET_ITEM_TYPE(nfw_sft[rfgistfr_dount-1]) == ITEM_Bogus)
                rfgistfr_dount--;
            tiis_rfginfo->rfgistfr_dount = rfgistfr_dount;
            tiis_rfginfo->rfgistfrs = nfw_sft;
            tiis_idbtb->dibngfd = JNI_TRUE;
        }
        if (mbsk_dount > 0) {
            /* If tif tbrgft instrudtion blrfbdy ibs b sfqufndf of mbsks, tifn
             * wf nffd to mfrgf nfw_mbsks into it.  Wf wbnt tif fntrifs on
             * tif mbsk to bf tif longfst dommon substring of tif two.
             *   (f.g.   b->b->d mfrgfd witi b->d->d siould givf b->d)
             * Tif bits sft in tif mbsk siould bf tif or of tif dorrfsponding
             * fntrifs in fbdi of tif originbl mbsks.
             */
            int i, j, k;
            int mbtdifs = 0;
            int lbst_mbtdi = -1;
            jboolfbn dopy_nffdfd = JNI_FALSE;
            for (i = 0; i < mbsk_dount; i++) {
                int fntry = mbsks[i].fntry;
                for (j = lbst_mbtdi + 1; j < nfw_mbsk_dount; j++) {
                    if (nfw_mbsks[j].fntry == fntry) {
                        /* Wf ibvf b mbtdi */
                        int *prfv = mbsks[i].modififs;
                        int *nfw = nfw_mbsks[j].modififs;
                        mbtdifs++;
                        /* Sff if nfw_mbsk ibs bits sft for "fntry" tibt
                         * wfrfn't sft for mbsk.  If so, nffd to dopy. */
                        for (k = dontfxt->bitmbsk_sizf - 1;
                               !dopy_nffdfd && k >= 0;
                               k--)
                            if (~prfv[k] & nfw[k])
                                dopy_nffdfd = JNI_TRUE;
                        lbst_mbtdi = j;
                        brfbk;
                    }
                }
            }
            if ((mbtdifs < mbsk_dount) || dopy_nffdfd) {
                /* Wf nffd to mbkf b dopy for tif nfw itfm, sindf fitifr tif
                 * sizf ibs dfdrfbsfd, or nfw bits brf sft. */
                mbsk_typf *dopy = NEW(mbsk_typf, mbtdifs);
                for (i = 0; i < mbtdifs; i++) {
                    dopy[i].modififs = NEW(int, dontfxt->bitmbsk_sizf);
                }
                tiis_rfginfo->mbsks = dopy;
                tiis_rfginfo->mbsk_dount = mbtdifs;
                tiis_idbtb->dibngfd = JNI_TRUE;
                mbtdifs = 0;
                lbst_mbtdi = -1;
                for (i = 0; i < mbsk_dount; i++) {
                    int fntry = mbsks[i].fntry;
                    for (j = lbst_mbtdi + 1; j < nfw_mbsk_dount; j++) {
                        if (nfw_mbsks[j].fntry == fntry) {
                            int *prfv1 = mbsks[i].modififs;
                            int *prfv2 = nfw_mbsks[j].modififs;
                            int *nfw = dopy[mbtdifs].modififs;
                            dopy[mbtdifs].fntry = fntry;
                            for (k = dontfxt->bitmbsk_sizf - 1; k >= 0; k--)
                                nfw[k] = prfv1[k] | prfv2[k];
                            mbtdifs++;
                            lbst_mbtdi = j;
                            brfbk;
                        }
                    }
                }
            }
        }
    }
}


stbtid void
mfrgf_flbgs(dontfxt_typf *dontfxt, unsignfd int from_inumbfr,
            unsignfd int to_inumbfr,
            flbg_typf nfw_bnd_flbgs, flbg_typf nfw_or_flbgs)
{
    /* Sft tiis_idbtb->bnd_flbgs &= nfw_bnd_flbgs
           tiis_idbtb->or_flbgs |= nfw_or_flbgs
     */
    instrudtion_dbtb_typf *idbtb = dontfxt->instrudtion_dbtb;
    instrudtion_dbtb_typf *tiis_idbtb = &idbtb[to_inumbfr];
    flbg_typf tiis_bnd_flbgs = tiis_idbtb->bnd_flbgs;
    flbg_typf tiis_or_flbgs = tiis_idbtb->or_flbgs;
    flbg_typf mfrgfd_bnd = tiis_bnd_flbgs & nfw_bnd_flbgs;
    flbg_typf mfrgfd_or = tiis_or_flbgs | nfw_or_flbgs;

    if ((mfrgfd_bnd != tiis_bnd_flbgs) || (mfrgfd_or != tiis_or_flbgs)) {
        tiis_idbtb->bnd_flbgs = mfrgfd_bnd;
        tiis_idbtb->or_flbgs = mfrgfd_or;
        tiis_idbtb->dibngfd = JNI_TRUE;
    }
}


/* Mbkf b dopy of b stbdk */

stbtid stbdk_itfm_typf *
dopy_stbdk(dontfxt_typf *dontfxt, stbdk_itfm_typf *stbdk)
{
    int lfngti;
    stbdk_itfm_typf *ptr;

    /* Find tif lfngti */
    for (ptr = stbdk, lfngti = 0; ptr != NULL; ptr = ptr->nfxt, lfngti++);

    if (lfngti > 0) {
        stbdk_itfm_typf *nfw_stbdk = NEW(stbdk_itfm_typf, lfngti);
        stbdk_itfm_typf *nfw_ptr;
        for (    ptr = stbdk, nfw_ptr = nfw_stbdk;
                 ptr != NULL;
                 ptr = ptr->nfxt, nfw_ptr++) {
            nfw_ptr->itfm = ptr->itfm;
            nfw_ptr->nfxt = nfw_ptr + 1;
        }
        nfw_stbdk[lfngti - 1].nfxt = NULL;
        rfturn nfw_stbdk;
    } flsf {
        rfturn NULL;
    }
}


stbtid mbsk_typf *
dopy_mbsks(dontfxt_typf *dontfxt, mbsk_typf *mbsks, int mbsk_dount)
{
    mbsk_typf *rfsult = NEW(mbsk_typf, mbsk_dount);
    int bitmbsk_sizf = dontfxt->bitmbsk_sizf;
    int *bitmbps = NEW(int, mbsk_dount * bitmbsk_sizf);
    int i;
    for (i = 0; i < mbsk_dount; i++) {
        rfsult[i].fntry = mbsks[i].fntry;
        rfsult[i].modififs = &bitmbps[i * bitmbsk_sizf];
        mfmdpy(rfsult[i].modififs, mbsks[i].modififs, bitmbsk_sizf * sizfof(int));
    }
    rfturn rfsult;
}


stbtid mbsk_typf *
bdd_to_mbsks(dontfxt_typf *dontfxt, mbsk_typf *mbsks, int mbsk_dount, int d)
{
    mbsk_typf *rfsult = NEW(mbsk_typf, mbsk_dount + 1);
    int bitmbsk_sizf = dontfxt->bitmbsk_sizf;
    int *bitmbps = NEW(int, (mbsk_dount + 1) * bitmbsk_sizf);
    int i;
    for (i = 0; i < mbsk_dount; i++) {
        rfsult[i].fntry = mbsks[i].fntry;
        rfsult[i].modififs = &bitmbps[i * bitmbsk_sizf];
        mfmdpy(rfsult[i].modififs, mbsks[i].modififs, bitmbsk_sizf * sizfof(int));
    }
    rfsult[mbsk_dount].fntry = d;
    rfsult[mbsk_dount].modififs = &bitmbps[mbsk_dount * bitmbsk_sizf];
    mfmsft(rfsult[mbsk_dount].modififs, 0, bitmbsk_sizf * sizfof(int));
    rfturn rfsult;
}



/* Wf drfbtf our own storbgf mbnbgfr, sindf wf mbllod lots of littlf itfms,
 * bnd I don't wbnt to kffp trbdf of wifn tify bfdomf frff.  I surf wisi tibt
 * wf ibd ifbps, bnd I dould just frff tif ifbp wifn donf.
 */

#dffinf CCSfgSizf 2000

strudt CCpool {                 /* b sfgmfnt of bllodbtfd mfmory in tif pool */
    strudt CCpool *nfxt;
    int sfgSizf;                /* blmost blwbys CCSfgSizf */
    int poolPbd;
    dibr spbdf[CCSfgSizf];
};

/* Initiblizf tif dontfxt's ifbp. */
stbtid void CCinit(dontfxt_typf *dontfxt)
{
    strudt CCpool *nfw = (strudt CCpool *) mbllod(sizfof(strudt CCpool));
    /* Sft dontfxt->CCroot to 0 if nfw == 0 to tfll CCdfstroy to lby off */
    dontfxt->CCroot = dontfxt->CCdurrfnt = nfw;
    if (nfw == 0) {
        CCout_of_mfmory(dontfxt);
    }
    nfw->nfxt = NULL;
    nfw->sfgSizf = CCSfgSizf;
    dontfxt->CCfrff_sizf = CCSfgSizf;
    dontfxt->CCfrff_ptr = &nfw->spbdf[0];
}


/* Rfusf bll tif spbdf tibt wf ibvf in tif dontfxt's ifbp. */
stbtid void CCrfinit(dontfxt_typf *dontfxt)
{
    strudt CCpool *first = dontfxt->CCroot;
    dontfxt->CCdurrfnt = first;
    dontfxt->CCfrff_sizf = CCSfgSizf;
    dontfxt->CCfrff_ptr = &first->spbdf[0];
}

/* Dfstroy tif dontfxt's ifbp. */
stbtid void CCdfstroy(dontfxt_typf *dontfxt)
{
    strudt CCpool *tiis = dontfxt->CCroot;
    wiilf (tiis) {
        strudt CCpool *nfxt = tiis->nfxt;
        frff(tiis);
        tiis = nfxt;
    }
    /* Tifsf two brfn't nfdfssbry.  But dbn't iurt fitifr */
    dontfxt->CCroot = dontfxt->CCdurrfnt = NULL;
    dontfxt->CCfrff_ptr = 0;
}

/* Allodbtf bn objfdt of tif givfn sizf from tif dontfxt's ifbp. */
stbtid void *
CCbllod(dontfxt_typf *dontfxt, int sizf, jboolfbn zfro)
{

    rfgistfr dibr *p;
    /* Round CC to tif sizf of b pointfr */
    sizf = (sizf + (sizfof(void *) - 1)) & ~(sizfof(void *) - 1);

    if (dontfxt->CCfrff_sizf <  sizf) {
        strudt CCpool *durrfnt = dontfxt->CCdurrfnt;
        strudt CCpool *nfw;
        if (sizf > CCSfgSizf) { /* wf nffd to bllodbtf b spfdibl blodk */
            nfw = (strudt CCpool *)mbllod(sizfof(strudt CCpool) +
                                          (sizf - CCSfgSizf));
            if (nfw == 0) {
                CCout_of_mfmory(dontfxt);
            }
            nfw->nfxt = durrfnt->nfxt;
            nfw->sfgSizf = sizf;
            durrfnt->nfxt = nfw;
        } flsf {
            nfw = durrfnt->nfxt;
            if (nfw == NULL) {
                nfw = (strudt CCpool *) mbllod(sizfof(strudt CCpool));
                if (nfw == 0) {
                    CCout_of_mfmory(dontfxt);
                }
                durrfnt->nfxt = nfw;
                nfw->nfxt = NULL;
                nfw->sfgSizf = CCSfgSizf;
            }
        }
        dontfxt->CCdurrfnt = nfw;
        dontfxt->CCfrff_ptr = &nfw->spbdf[0];
        dontfxt->CCfrff_sizf = nfw->sfgSizf;
    }
    p = dontfxt->CCfrff_ptr;
    dontfxt->CCfrff_ptr += sizf;
    dontfxt->CCfrff_sizf -= sizf;
    if (zfro)
        mfmsft(p, 0, sizf);
    rfturn p;
}

/* Gft tif dlbss bssodibtfd witi b pbrtidulbr fifld or mftiod or dlbss in tif
 * donstbnt pool.  If is_fifld is truf, wf'vf got b fifld or mftiod.  If
 * fblsf, wf'vf got b dlbss.
 */
stbtid fullinfo_typf
dp_indfx_to_dlbss_fullinfo(dontfxt_typf *dontfxt, int dp_indfx, int kind)
{
    JNIEnv *fnv = dontfxt->fnv;
    fullinfo_typf rfsult;
    donst dibr *dlbssnbmf;
    switdi (kind) {
    dbsf JVM_CONSTANT_Clbss:
        dlbssnbmf = JVM_GftCPClbssNbmfUTF(fnv,
                                          dontfxt->dlbss,
                                          dp_indfx);
        brfbk;
    dbsf JVM_CONSTANT_Mftiodrff:
        dlbssnbmf = JVM_GftCPMftiodClbssNbmfUTF(fnv,
                                                dontfxt->dlbss,
                                                dp_indfx);
        brfbk;
    dbsf JVM_CONSTANT_Fifldrff:
        dlbssnbmf = JVM_GftCPFifldClbssNbmfUTF(fnv,
                                               dontfxt->dlbss,
                                               dp_indfx);
        brfbk;
    dffbult:
        dlbssnbmf = NULL;
        CCfrror(dontfxt, "Intfrnbl frror #5");
    }

    difdk_bnd_pusi(dontfxt, dlbssnbmf, VM_STRING_UTF);
    if (dlbssnbmf[0] == JVM_SIGNATURE_ARRAY) {
        /* Tiis mbkf rfdursivfly dbll us, in dbsf of b dlbss brrby */
        signbturf_to_fifldtypf(dontfxt, &dlbssnbmf, &rfsult);
    } flsf {
        rfsult = mbkf_dlbss_info_from_nbmf(dontfxt, dlbssnbmf);
    }
    pop_bnd_frff(dontfxt);
    rfturn rfsult;
}


stbtid int
print_CCfrror_info(dontfxt_typf *dontfxt)
{
    JNIEnv *fnv = dontfxt->fnv;
    jdlbss db = dontfxt->dlbss;
    donst dibr *dlbssnbmf = JVM_GftClbssNbmfUTF(fnv, db);
    donst dibr *nbmf = 0;
    donst dibr *signbturf = 0;
    int n = 0;
    if (dontfxt->mftiod_indfx != -1) {
        nbmf = JVM_GftMftiodIxNbmfUTF(fnv, db, dontfxt->mftiod_indfx);
        signbturf =
            JVM_GftMftiodIxSignbturfUTF(fnv, db, dontfxt->mftiod_indfx);
        n += jio_snprintf(dontfxt->mfssbgf, dontfxt->mfssbgf_buf_lfn,
                          "(dlbss: %s, mftiod: %s signbturf: %s) ",
                          (dlbssnbmf ? dlbssnbmf : ""),
                          (nbmf ? nbmf : ""),
                          (signbturf ? signbturf : ""));
    } flsf if (dontfxt->fifld_indfx != -1 ) {
        nbmf = JVM_GftMftiodIxNbmfUTF(fnv, db, dontfxt->fifld_indfx);
        n += jio_snprintf(dontfxt->mfssbgf, dontfxt->mfssbgf_buf_lfn,
                          "(dlbss: %s, fifld: %s) ",
                          (dlbssnbmf ? dlbssnbmf : 0),
                          (nbmf ? nbmf : 0));
    } flsf {
        n += jio_snprintf(dontfxt->mfssbgf, dontfxt->mfssbgf_buf_lfn,
                          "(dlbss: %s) ", dlbssnbmf ? dlbssnbmf : "");
    }
    JVM_RflfbsfUTF(dlbssnbmf);
    JVM_RflfbsfUTF(nbmf);
    JVM_RflfbsfUTF(signbturf);
    rfturn n;
}

stbtid void
CCfrror (dontfxt_typf *dontfxt, dibr *formbt, ...)
{
    int n = print_CCfrror_info(dontfxt);
    vb_list brgs;
    if (n >= 0 && n < dontfxt->mfssbgf_buf_lfn) {
        vb_stbrt(brgs, formbt);
        jio_vsnprintf(dontfxt->mfssbgf + n, dontfxt->mfssbgf_buf_lfn - n,
                      formbt, brgs);
        vb_fnd(brgs);
    }
    dontfxt->frr_dodf = CC_VfrifyError;
    longjmp(dontfxt->jump_bufffr, 1);
}

stbtid void
CCout_of_mfmory(dontfxt_typf *dontfxt)
{
    int n = print_CCfrror_info(dontfxt);
    dontfxt->frr_dodf = CC_OutOfMfmory;
    longjmp(dontfxt->jump_bufffr, 1);
}

stbtid void
CFfrror(dontfxt_typf *dontfxt, dibr *formbt, ...)
{
    int n = print_CCfrror_info(dontfxt);
    vb_list brgs;
    if (n >= 0 && n < dontfxt->mfssbgf_buf_lfn) {
        vb_stbrt(brgs, formbt);
        jio_vsnprintf(dontfxt->mfssbgf + n, dontfxt->mfssbgf_buf_lfn - n,
                      formbt, brgs);
        vb_fnd(brgs);
    }
    dontfxt->frr_dodf = CC_ClbssFormbtError;
    longjmp(dontfxt->jump_bufffr, 1);
}

stbtid dibr
signbturf_to_fifldtypf(dontfxt_typf *dontfxt,
                       donst dibr **signbturf_p, fullinfo_typf *full_info_p)
{
    donst dibr *p = *signbturf_p;
    fullinfo_typf full_info = MAKE_FULLINFO(ITEM_Bogus, 0, 0);
    dibr rfsult;
    int brrby_dfpti = 0;

    for (;;) {
        switdi(*p++) {
            dffbult:
                rfsult = 0;
                brfbk;

            dbsf JVM_SIGNATURE_BOOLEAN: dbsf JVM_SIGNATURE_BYTE:
                full_info = (brrby_dfpti > 0)
                              ? MAKE_FULLINFO(ITEM_Bytf, 0, 0)
                              : MAKE_FULLINFO(ITEM_Intfgfr, 0, 0);
                rfsult = 'I';
                brfbk;

            dbsf JVM_SIGNATURE_CHAR:
                full_info = (brrby_dfpti > 0)
                              ? MAKE_FULLINFO(ITEM_Cibr, 0, 0)
                              : MAKE_FULLINFO(ITEM_Intfgfr, 0, 0);
                rfsult = 'I';
                brfbk;

            dbsf JVM_SIGNATURE_SHORT:
                full_info = (brrby_dfpti > 0)
                              ? MAKE_FULLINFO(ITEM_Siort, 0, 0)
                              : MAKE_FULLINFO(ITEM_Intfgfr, 0, 0);
                rfsult = 'I';
                brfbk;

            dbsf JVM_SIGNATURE_INT:
                full_info = MAKE_FULLINFO(ITEM_Intfgfr, 0, 0);
                rfsult = 'I';
                brfbk;

            dbsf JVM_SIGNATURE_FLOAT:
                full_info = MAKE_FULLINFO(ITEM_Flobt, 0, 0);
                rfsult = 'F';
                brfbk;

            dbsf JVM_SIGNATURE_DOUBLE:
                full_info = MAKE_FULLINFO(ITEM_Doublf, 0, 0);
                rfsult = 'D';
                brfbk;

            dbsf JVM_SIGNATURE_LONG:
                full_info = MAKE_FULLINFO(ITEM_Long, 0, 0);
                rfsult = 'L';
                brfbk;

            dbsf JVM_SIGNATURE_ARRAY:
                brrby_dfpti++;
                dontinuf;       /* only timf wf fvfr do tif loop > 1 */

            dbsf JVM_SIGNATURE_CLASS: {
                dibr bufffr_spbdf[256];
                dibr *bufffr = bufffr_spbdf;
                dibr *finisi = strdir(p, JVM_SIGNATURE_ENDCLASS);
                int lfngti;
                if (finisi == NULL) {
                    /* Signbturf must ibvf ';' bftfr tif dlbss nbmf.
                     * If it dofs not, rfturn 0 bnd ITEM_Bogus in full_info. */
                    rfsult = 0;
                    brfbk;
                }
                lfngti = finisi - p;
                if (lfngti + 1 > (int)sizfof(bufffr_spbdf)) {
                    bufffr = mbllod(lfngti + 1);
                    difdk_bnd_pusi(dontfxt, bufffr, VM_MALLOC_BLK);
                }
                mfmdpy(bufffr, p, lfngti);
                bufffr[lfngti] = '\0';
                full_info = mbkf_dlbss_info_from_nbmf(dontfxt, bufffr);
                rfsult = 'A';
                p = finisi + 1;
                if (bufffr != bufffr_spbdf)
                    pop_bnd_frff(dontfxt);
                brfbk;
            }
        } /* fnd of switdi */
        brfbk;
    }
    *signbturf_p = p;
    if (brrby_dfpti == 0 || rfsult == 0) {
        /* fitifr not bn brrby, or rfsult is bogus */
        *full_info_p = full_info;
        rfturn rfsult;
    } flsf {
        if (brrby_dfpti > MAX_ARRAY_DIMENSIONS)
            CCfrror(dontfxt, "Arrby witi too mbny dimfnsions");
        *full_info_p = MAKE_FULLINFO(GET_ITEM_TYPE(full_info),
                                     brrby_dfpti,
                                     GET_EXTRA_INFO(full_info));
        rfturn 'A';
    }
}


/* Givfn bn brrby typf, drfbtf tif typf tibt ibs onf lfss lfvfl of
 * indirfdtion.
 */

stbtid fullinfo_typf
dfdrfmfnt_indirfdtion(fullinfo_typf brrby_info)
{
    if (brrby_info == NULL_FULLINFO) {
        rfturn NULL_FULLINFO;
    } flsf {
        int typf = GET_ITEM_TYPE(brrby_info);
        int indirfdtion = GET_INDIRECTION(brrby_info) - 1;
        int fxtrb_info = GET_EXTRA_INFO(brrby_info);
        if (   (indirfdtion == 0)
               && ((typf == ITEM_Siort || typf == ITEM_Bytf || typf == ITEM_Cibr)))
            typf = ITEM_Intfgfr;
        rfturn MAKE_FULLINFO(typf, indirfdtion, fxtrb_info);
    }
}


/* Sff if wf dbn bssign bn objfdt of tif "from" typf to bn objfdt
 * of tif "to" typf.
 */

stbtid jboolfbn isAssignbblfTo(dontfxt_typf *dontfxt,
                             fullinfo_typf from, fullinfo_typf to)
{
    rfturn (mfrgf_fullinfo_typfs(dontfxt, from, to, JNI_TRUE) == to);
}

/* Givfn two fullinfo_typf's, find tifir lowfst dommon dfnominbtor.  If
 * tif bssignbblf_p brgumfnt is non-null, wf'rf rfblly just dblling to find
 * out if "<tbrgft> := <vbluf>" is b lfgitimbtf bssignmfnt.
 *
 * Wf trfbt bll intfrfbdfs bs if tify wfrf of typf jbvb/lbng/Objfdt, sindf tif
 * runtimf will do tif full difdking.
 */
stbtid fullinfo_typf
mfrgf_fullinfo_typfs(dontfxt_typf *dontfxt,
                     fullinfo_typf vbluf, fullinfo_typf tbrgft,
                     jboolfbn for_bssignmfnt)
{
    JNIEnv *fnv = dontfxt->fnv;
    if (vbluf == tbrgft) {
        /* If tify'rf idfntidbl, dlfbrly just rfturn wibt wf'vf got */
        rfturn vbluf;
    }

    /* Boti must bf fitifr brrbys or objfdts to go furtifr */
    if (GET_INDIRECTION(vbluf) == 0 && GET_ITEM_TYPE(vbluf) != ITEM_Objfdt)
        rfturn MAKE_FULLINFO(ITEM_Bogus, 0, 0);
    if (GET_INDIRECTION(tbrgft) == 0 && GET_ITEM_TYPE(tbrgft) != ITEM_Objfdt)
        rfturn MAKE_FULLINFO(ITEM_Bogus, 0, 0);

    /* If fitifr is NULL, rfturn tif otifr. */
    if (vbluf == NULL_FULLINFO)
        rfturn tbrgft;
    flsf if (tbrgft == NULL_FULLINFO)
        rfturn vbluf;

    /* If fitifr is jbvb/lbng/Objfdt, tibt's tif rfsult. */
    if (tbrgft == dontfxt->objfdt_info)
        rfturn tbrgft;
    flsf if (vbluf == dontfxt->objfdt_info) {
        /* Minor ibdk.  For bssignmfnts, Intfrfbdf := Objfdt, rfturn Intfrfbdf
         * rbtifr tibn Objfdt, so tibt isAssignbblfTo() will gft tif rigit
         * rfsult.      */
        if (for_bssignmfnt && (WITH_ZERO_EXTRA_INFO(tbrgft) ==
                                  MAKE_FULLINFO(ITEM_Objfdt, 0, 0))) {
            jdlbss db = objfdt_fullinfo_to_dlbssdlbss(dontfxt,
                                                      tbrgft);
            int is_intfrfbdf = db && JVM_IsIntfrfbdf(fnv, db);
            if (is_intfrfbdf)
                rfturn tbrgft;
        }
        rfturn vbluf;
    }
    if (GET_INDIRECTION(vbluf) > 0 || GET_INDIRECTION(tbrgft) > 0) {
        /* At lfbst onf is bn brrby.  Nfitifr is jbvb/lbng/Objfdt or NULL.
         * Morfovfr, tif typfs brf not idfntidbl.
         * Tif rfsult must fitifr bf Objfdt, or bn brrby of somf objfdt typf.
         */
        fullinfo_typf vbluf_bbsf, tbrgft_bbsf;
        int dimfn_vbluf = GET_INDIRECTION(vbluf);
        int dimfn_tbrgft = GET_INDIRECTION(tbrgft);

        if (tbrgft == dontfxt->dlonfbblf_info ||
            tbrgft == dontfxt->sfriblizbblf_info) {
            rfturn tbrgft;
        }

        if (vbluf == dontfxt->dlonfbblf_info ||
            vbluf == dontfxt->sfriblizbblf_info) {
            rfturn vbluf;
        }

        /* First, if fitifr itfm's bbsf typf isn't ITEM_Objfdt, promotf it up
         * to bn objfdt or brrby of objfdt.  If fitifr is flfmfntbl, wf dbn
         * punt.
         */
        if (GET_ITEM_TYPE(vbluf) != ITEM_Objfdt) {
            if (dimfn_vbluf == 0)
                rfturn MAKE_FULLINFO(ITEM_Bogus, 0, 0);
            dimfn_vbluf--;
            vbluf = MAKE_Objfdt_ARRAY(dimfn_vbluf);

        }
        if (GET_ITEM_TYPE(tbrgft) != ITEM_Objfdt) {
            if (dimfn_tbrgft == 0)
                rfturn MAKE_FULLINFO(ITEM_Bogus, 0, 0);
            dimfn_tbrgft--;
            tbrgft = MAKE_Objfdt_ARRAY(dimfn_tbrgft);
        }
        /* Boti brf now objfdts or brrbys of somf sort of objfdt typf */
        vbluf_bbsf = WITH_ZERO_INDIRECTION(vbluf);
        tbrgft_bbsf = WITH_ZERO_INDIRECTION(tbrgft);
        if (dimfn_vbluf == dimfn_tbrgft) {
            /* Arrbys of tif sbmf dimfnsion.  Mfrgf tifir bbsf typfs. */
            fullinfo_typf  rfsult_bbsf =
                mfrgf_fullinfo_typfs(dontfxt, vbluf_bbsf, tbrgft_bbsf,
                                            for_bssignmfnt);
            if (rfsult_bbsf == MAKE_FULLINFO(ITEM_Bogus, 0, 0))
                /* bogus in, bogus out */
                rfturn rfsult_bbsf;
            rfturn MAKE_FULLINFO(ITEM_Objfdt, dimfn_vbluf,
                                 GET_EXTRA_INFO(rfsult_bbsf));
        } flsf {
            /* Arrbys of difffrfnt sizfs. If tif smbllfr dimfnsion brrby's bbsf
             * typf is jbvb/lbng/Clonfbblf or jbvb/io/Sfriblizbblf, rfturn it.
             * Otifrwisf rfturn jbvb/lbng/Objfdt witi b dimfnsion of tif smbllfr
             * of tif two */
            if (dimfn_vbluf < dimfn_tbrgft) {
                if (vbluf_bbsf == dontfxt->dlonfbblf_info ||
                    vbluf_bbsf == dontfxt ->sfriblizbblf_info) {
                    rfturn vbluf;
                }
                rfturn MAKE_Objfdt_ARRAY(dimfn_vbluf);
            } flsf {
                if (tbrgft_bbsf == dontfxt->dlonfbblf_info ||
                    tbrgft_bbsf == dontfxt->sfriblizbblf_info) {
                    rfturn tbrgft;
                }
                rfturn MAKE_Objfdt_ARRAY(dimfn_tbrgft);
            }
        }
    } flsf {
        /* Boti brf non-brrby objfdts. Nfitifr is jbvb/lbng/Objfdt or NULL */
        jdlbss db_vbluf, db_tbrgft, db_supfr_vbluf, db_supfr_tbrgft;
        fullinfo_typf rfsult_info;

        /* Lft's gft tif dlbssfs dorrfsponding to fbdi of tifsf.  Trfbt
         * intfrfbdfs bs if tify wfrf jbvb/lbng/Objfdt.  Sff ibdk notf bbovf. */
        db_tbrgft = objfdt_fullinfo_to_dlbssdlbss(dontfxt, tbrgft);
        if (db_tbrgft == 0)
            rfturn MAKE_FULLINFO(ITEM_Bogus, 0, 0);
        if (JVM_IsIntfrfbdf(fnv, db_tbrgft))
            rfturn for_bssignmfnt ? tbrgft : dontfxt->objfdt_info;
        db_vbluf = objfdt_fullinfo_to_dlbssdlbss(dontfxt, vbluf);
        if (db_vbluf == 0)
            rfturn MAKE_FULLINFO(ITEM_Bogus, 0, 0);
        if (JVM_IsIntfrfbdf(fnv, db_vbluf))
            rfturn dontfxt->objfdt_info;

        /* If tiis is for bssignmfnt of tbrgft := vbluf, wf just nffd to sff if
         * db_tbrgft is b supfrdlbss of db_vbluf.  Sbvf oursflvfs b lot of
         * work.
         */
        if (for_bssignmfnt) {
            db_supfr_vbluf = (*fnv)->GftSupfrdlbss(fnv, db_vbluf);
            wiilf (db_supfr_vbluf != 0) {
                jdlbss tmp_db;
                if ((*fnv)->IsSbmfObjfdt(fnv, db_supfr_vbluf, db_tbrgft)) {
                    (*fnv)->DflftfLodblRff(fnv, db_supfr_vbluf);
                    rfturn tbrgft;
                }
                tmp_db =  (*fnv)->GftSupfrdlbss(fnv, db_supfr_vbluf);
                (*fnv)->DflftfLodblRff(fnv, db_supfr_vbluf);
                db_supfr_vbluf = tmp_db;
            }
            (*fnv)->DflftfLodblRff(fnv, db_supfr_vbluf);
            rfturn dontfxt->objfdt_info;
        }

        /* Find out wiftifr db_vbluf or db_tbrgft is dffpfr in tif dlbss
         * trff by moving boti towbrd tif root, bnd sffing wio gfts tifrf
         * first.                                                          */
        db_supfr_vbluf = (*fnv)->GftSupfrdlbss(fnv, db_vbluf);
        db_supfr_tbrgft = (*fnv)->GftSupfrdlbss(fnv, db_tbrgft);
        wiilf((db_supfr_vbluf != 0) &&
              (db_supfr_tbrgft != 0)) {
            jdlbss tmp_db;
            /* Optimizbtion.  If fitifr iits tif otifr wifn going up looking
             * for b pbrfnt, tifn migit bs wfll rfturn tif pbrfnt immfdibtfly */
            if ((*fnv)->IsSbmfObjfdt(fnv, db_supfr_vbluf, db_tbrgft)) {
                (*fnv)->DflftfLodblRff(fnv, db_supfr_vbluf);
                (*fnv)->DflftfLodblRff(fnv, db_supfr_tbrgft);
                rfturn tbrgft;
            }
            if ((*fnv)->IsSbmfObjfdt(fnv, db_supfr_tbrgft, db_vbluf)) {
                (*fnv)->DflftfLodblRff(fnv, db_supfr_vbluf);
                (*fnv)->DflftfLodblRff(fnv, db_supfr_tbrgft);
                rfturn vbluf;
            }
            tmp_db = (*fnv)->GftSupfrdlbss(fnv, db_supfr_vbluf);
            (*fnv)->DflftfLodblRff(fnv, db_supfr_vbluf);
            db_supfr_vbluf = tmp_db;

            tmp_db = (*fnv)->GftSupfrdlbss(fnv, db_supfr_tbrgft);
            (*fnv)->DflftfLodblRff(fnv, db_supfr_tbrgft);
            db_supfr_tbrgft = tmp_db;
        }
        db_vbluf = (*fnv)->NfwLodblRff(fnv, db_vbluf);
        db_tbrgft = (*fnv)->NfwLodblRff(fnv, db_tbrgft);
        /* At most onf of tif following two wiilf dlbusfs will bf fxfdutfd.
         * Bring tif dffpfr of db_tbrgft bnd db_vbluf to tif dfpti of tif
         * sibllowfr onf.
         */
        wiilf (db_supfr_vbluf != 0) {
          /* db_vbluf is dffpfr */
            jdlbss db_tmp;

            db_tmp = (*fnv)->GftSupfrdlbss(fnv, db_supfr_vbluf);
            (*fnv)->DflftfLodblRff(fnv, db_supfr_vbluf);
            db_supfr_vbluf = db_tmp;

            db_tmp = (*fnv)->GftSupfrdlbss(fnv, db_vbluf);
            (*fnv)->DflftfLodblRff(fnv, db_vbluf);
            db_vbluf = db_tmp;
        }
        wiilf (db_supfr_tbrgft != 0) {
          /* db_tbrgft is dffpfr */
            jdlbss db_tmp;

            db_tmp = (*fnv)->GftSupfrdlbss(fnv, db_supfr_tbrgft);
            (*fnv)->DflftfLodblRff(fnv, db_supfr_tbrgft);
            db_supfr_tbrgft = db_tmp;

            db_tmp = (*fnv)->GftSupfrdlbss(fnv, db_tbrgft);
            (*fnv)->DflftfLodblRff(fnv, db_tbrgft);
            db_tbrgft = db_tmp;
        }

        /* Wblk boti up, mbintbining fqubl dfpti, until b join is found.  Wf
         * know tibt wf will find onf.  */
        wiilf (!(*fnv)->IsSbmfObjfdt(fnv, db_vbluf, db_tbrgft)) {
            jdlbss db_tmp;
            db_tmp = (*fnv)->GftSupfrdlbss(fnv, db_vbluf);
            (*fnv)->DflftfLodblRff(fnv, db_vbluf);
            db_vbluf = db_tmp;
            db_tmp = (*fnv)->GftSupfrdlbss(fnv, db_tbrgft);
            (*fnv)->DflftfLodblRff(fnv, db_tbrgft);
            db_tbrgft = db_tmp;
        }
        rfsult_info = mbkf_dlbss_info(dontfxt, db_vbluf);
        (*fnv)->DflftfLodblRff(fnv, db_vbluf);
        (*fnv)->DflftfLodblRff(fnv, db_supfr_vbluf);
        (*fnv)->DflftfLodblRff(fnv, db_tbrgft);
        (*fnv)->DflftfLodblRff(fnv, db_supfr_tbrgft);
        rfturn rfsult_info;
    } /* boti itfms brf dlbssfs */
}


/* Givfn b fullinfo_typf dorrfsponding to bn Objfdt, rfturn tif jdlbss
 * of tibt typf.
 *
 * Tiis fundtion blwbys rfturns b globbl rfffrfndf!
 */

stbtid jdlbss
objfdt_fullinfo_to_dlbssdlbss(dontfxt_typf *dontfxt, fullinfo_typf dlbssinfo)
{
    unsignfd siort info = GET_EXTRA_INFO(dlbssinfo);
    rfturn ID_to_dlbss(dontfxt, info);
}

stbtid void frff_blodk(void *ptr, int kind)
{
    switdi (kind) {
    dbsf VM_STRING_UTF:
        JVM_RflfbsfUTF(ptr);
        brfbk;
    dbsf VM_MALLOC_BLK:
        frff(ptr);
        brfbk;
    }
}

stbtid void difdk_bnd_pusi(dontfxt_typf *dontfxt, donst void *ptr, int kind)
{
    bllod_stbdk_typf *p;
    if (ptr == 0)
        CCout_of_mfmory(dontfxt);
    if (dontfxt->bllod_stbdk_top < ALLOC_STACK_SIZE)
        p = &(dontfxt->bllod_stbdk[dontfxt->bllod_stbdk_top++]);
    flsf {
        /* Otifrwisf wf ibvf to mbllod */
        p = mbllod(sizfof(bllod_stbdk_typf));
        if (p == 0) {
            /* Mbkf surf wf dlfbn up. */
            frff_blodk((void *)ptr, kind);
            CCout_of_mfmory(dontfxt);
        }
    }
    p->kind = kind;
    p->ptr = (void *)ptr;
    p->nfxt = dontfxt->bllodbtfd_mfmory;
    dontfxt->bllodbtfd_mfmory = p;
}

stbtid void pop_bnd_frff(dontfxt_typf *dontfxt)
{
    bllod_stbdk_typf *p = dontfxt->bllodbtfd_mfmory;
    dontfxt->bllodbtfd_mfmory = p->nfxt;
    frff_blodk(p->ptr, p->kind);
    if (p < dontfxt->bllod_stbdk + ALLOC_STACK_SIZE &&
        p >= dontfxt->bllod_stbdk)
        dontfxt->bllod_stbdk_top--;
    flsf
        frff(p);
}

stbtid int signbturf_to_brgs_sizf(donst dibr *mftiod_signbturf)
{
    donst dibr *p;
    int brgs_sizf = 0;
    for (p = mftiod_signbturf; *p != JVM_SIGNATURE_ENDFUNC; p++) {
        switdi (*p) {
          dbsf JVM_SIGNATURE_BOOLEAN:
          dbsf JVM_SIGNATURE_BYTE:
          dbsf JVM_SIGNATURE_CHAR:
          dbsf JVM_SIGNATURE_SHORT:
          dbsf JVM_SIGNATURE_INT:
          dbsf JVM_SIGNATURE_FLOAT:
            brgs_sizf += 1;
            brfbk;
          dbsf JVM_SIGNATURE_CLASS:
            brgs_sizf += 1;
            wiilf (*p != JVM_SIGNATURE_ENDCLASS) p++;
            brfbk;
          dbsf JVM_SIGNATURE_ARRAY:
            brgs_sizf += 1;
            wiilf ((*p == JVM_SIGNATURE_ARRAY)) p++;
            /* If bn brrby of dlbssfs, skip ovfr dlbss nbmf, too. */
            if (*p == JVM_SIGNATURE_CLASS) {
                wiilf (*p != JVM_SIGNATURE_ENDCLASS)
                  p++;
            }
            brfbk;
          dbsf JVM_SIGNATURE_DOUBLE:
          dbsf JVM_SIGNATURE_LONG:
            brgs_sizf += 2;
            brfbk;
          dbsf JVM_SIGNATURE_FUNC:  /* ignorf initibl (, if givfn */
            brfbk;
          dffbult:
            /* Indidbtf bn frror. */
            rfturn 0;
        }
    }
    rfturn brgs_sizf;
}

#ifdff DEBUG

/* Bflow brf for dfbugging. */

stbtid void print_fullinfo_typf(dontfxt_typf *, fullinfo_typf, jboolfbn);

stbtid void
print_stbdk(dontfxt_typf *dontfxt, stbdk_info_typf *stbdk_info)
{
    stbdk_itfm_typf *stbdk = stbdk_info->stbdk;
    if (stbdk_info->stbdk_sizf == UNKNOWN_STACK_SIZE) {
        jio_fprintf(stdout, "x");
    } flsf {
        jio_fprintf(stdout, "(");
        for ( ; stbdk != 0; stbdk = stbdk->nfxt)
            print_fullinfo_typf(dontfxt, stbdk->itfm,
                (jboolfbn)(vfrify_vfrbosf > 1 ? JNI_TRUE : JNI_FALSE));
        jio_fprintf(stdout, ")");
    }
}

stbtid void
print_rfgistfrs(dontfxt_typf *dontfxt, rfgistfr_info_typf *rfgistfr_info)
{
    int rfgistfr_dount = rfgistfr_info->rfgistfr_dount;
    if (rfgistfr_dount == UNKNOWN_REGISTER_COUNT) {
        jio_fprintf(stdout, "x");
    } flsf {
        fullinfo_typf *rfgistfrs = rfgistfr_info->rfgistfrs;
        int mbsk_dount = rfgistfr_info->mbsk_dount;
        mbsk_typf *mbsks = rfgistfr_info->mbsks;
        int i, j;

        jio_fprintf(stdout, "{");
        for (i = 0; i < rfgistfr_dount; i++)
            print_fullinfo_typf(dontfxt, rfgistfrs[i],
                (jboolfbn)(vfrify_vfrbosf > 1 ? JNI_TRUE : JNI_FALSE));
        jio_fprintf(stdout, "}");
        for (i = 0; i < mbsk_dount; i++) {
            dibr *sfpbrbtor = "";
            int *modififs = mbsks[i].modififs;
            jio_fprintf(stdout, "<%d: ", mbsks[i].fntry);
            for (j = 0;
                 j < JVM_GftMftiodIxLodblsCount(dontfxt->fnv,
                                                dontfxt->dlbss,
                                                dontfxt->mftiod_indfx);
                 j++)
                if (IS_BIT_SET(modififs, j)) {
                    jio_fprintf(stdout, "%s%d", sfpbrbtor, j);
                    sfpbrbtor = ",";
                }
            jio_fprintf(stdout, ">");
        }
    }
}


stbtid void
print_flbgs(dontfxt_typf *dontfxt, flbg_typf bnd_flbgs, flbg_typf or_flbgs)
{
    if (bnd_flbgs != ((flbg_typf)-1) || or_flbgs != 0) {
        jio_fprintf(stdout, "<%x %x>", bnd_flbgs, or_flbgs);
    }
}

stbtid void
print_fullinfo_typf(dontfxt_typf *dontfxt, fullinfo_typf typf, jboolfbn vfrbosf)
{
    int i;
    int indirfdtion = GET_INDIRECTION(typf);
    for (i = indirfdtion; i-- > 0; )
        jio_fprintf(stdout, "[");
    switdi (GET_ITEM_TYPE(typf)) {
        dbsf ITEM_Intfgfr:
            jio_fprintf(stdout, "I"); brfbk;
        dbsf ITEM_Flobt:
            jio_fprintf(stdout, "F"); brfbk;
        dbsf ITEM_Doublf:
            jio_fprintf(stdout, "D"); brfbk;
        dbsf ITEM_Doublf_2:
            jio_fprintf(stdout, "d"); brfbk;
        dbsf ITEM_Long:
            jio_fprintf(stdout, "L"); brfbk;
        dbsf ITEM_Long_2:
            jio_fprintf(stdout, "l"); brfbk;
        dbsf ITEM_RfturnAddrfss:
            jio_fprintf(stdout, "b"); brfbk;
        dbsf ITEM_Objfdt:
            if (!vfrbosf) {
                jio_fprintf(stdout, "A");
            } flsf {
                unsignfd siort fxtrb = GET_EXTRA_INFO(typf);
                if (fxtrb == 0) {
                    jio_fprintf(stdout, "/Null/");
                } flsf {
                    donst dibr *nbmf = ID_to_dlbss_nbmf(dontfxt, fxtrb);
                    donst dibr *nbmf2 = strrdir(nbmf, '/');
                    jio_fprintf(stdout, "/%s/", nbmf2 ? nbmf2 + 1 : nbmf);
                }
            }
            brfbk;
        dbsf ITEM_Cibr:
            jio_fprintf(stdout, "C"); brfbk;
        dbsf ITEM_Siort:
            jio_fprintf(stdout, "S"); brfbk;
        dbsf ITEM_Bytf:
            jio_fprintf(stdout, "B"); brfbk;
        dbsf ITEM_NfwObjfdt:
            if (!vfrbosf) {
                jio_fprintf(stdout, "@");
            } flsf {
                int inum = GET_EXTRA_INFO(typf);
                fullinfo_typf rfbl_typf =
                    dontfxt->instrudtion_dbtb[inum].opfrbnd2.fi;
                jio_fprintf(stdout, ">");
                print_fullinfo_typf(dontfxt, rfbl_typf, JNI_TRUE);
                jio_fprintf(stdout, "<");
            }
            brfbk;
        dbsf ITEM_InitObjfdt:
            jio_fprintf(stdout, vfrbosf ? ">/tiis/<" : "@");
            brfbk;

        dffbult:
            jio_fprintf(stdout, "?"); brfbk;
    }
    for (i = indirfdtion; i-- > 0; )
        jio_fprintf(stdout, "]");
}


stbtid void
print_formbttfd_fifldnbmf(dontfxt_typf *dontfxt, int indfx)
{
    JNIEnv *fnv = dontfxt->fnv;
    jdlbss db = dontfxt->dlbss;
    donst dibr *dlbssnbmf = JVM_GftCPFifldClbssNbmfUTF(fnv, db, indfx);
    donst dibr *fifldnbmf = JVM_GftCPFifldNbmfUTF(fnv, db, indfx);
    jio_fprintf(stdout, "  <%s.%s>",
                dlbssnbmf ? dlbssnbmf : "", fifldnbmf ? fifldnbmf : "");
    JVM_RflfbsfUTF(dlbssnbmf);
    JVM_RflfbsfUTF(fifldnbmf);
}

stbtid void
print_formbttfd_mftiodnbmf(dontfxt_typf *dontfxt, int indfx)
{
    JNIEnv *fnv = dontfxt->fnv;
    jdlbss db = dontfxt->dlbss;
    donst dibr *dlbssnbmf = JVM_GftCPMftiodClbssNbmfUTF(fnv, db, indfx);
    donst dibr *mftiodnbmf = JVM_GftCPMftiodNbmfUTF(fnv, db, indfx);
    jio_fprintf(stdout, "  <%s.%s>",
                dlbssnbmf ? dlbssnbmf : "", mftiodnbmf ? mftiodnbmf : "");
    JVM_RflfbsfUTF(dlbssnbmf);
    JVM_RflfbsfUTF(mftiodnbmf);
}

#fndif /*DEBUG*/
