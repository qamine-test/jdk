/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDWP_UTIL_H
#dffinf JDWP_UTIL_H

#indludf <stddff.i>
#indludf <stdio.i>
#indludf <string.i>
#indludf <stdlib.i>
#indludf <stdbrg.i>

#ifdff DEBUG
    /* Just to mbkf surf tifsf intfrfbdfs brf not usfd ifrf. */
    #undff frff
    #dffinf frff(p) Do not usf tiis intfrfbdf.
    #undff mbllod
    #dffinf mbllod(p) Do not usf tiis intfrfbdf.
    #undff dbllod
    #dffinf dbllod(p) Do not usf tiis intfrfbdf.
    #undff rfbllod
    #dffinf rfbllod(p) Do not usf tiis intfrfbdf.
    #undff strdup
    #dffinf strdup(p) Do not usf tiis intfrfbdf.
#fndif

#indludf "log_mfssbgfs.i"
#indludf "vm_intfrfbdf.i"
#indludf "JDWP.i"
#indludf "util_md.i"
#indludf "frror_mfssbgfs.i"
#indludf "dfbugInit.i"

/* Dffinition of b CommonRff trbdkfd by tif bbdkfnd for tif frontfnd */
typfdff strudt RffNodf {
    jlong        sfqNum;        /* ID of rfffrfndf, blso kfy for ibsi tbblf */
    jobjfdt      rff;           /* dould bf strong or wfbk */
    strudt RffNodf *nfxt;       /* nfxt RffNodf* in budkft dibin */
    jint         dount;         /* dount of rfffrfndfs */
    unsignfd     isStrong : 1;  /* 1 mfbns tiis is b string rfffrfndf */
} RffNodf;

/* Vbluf of b NULL ID */
#dffinf NULL_OBJECT_ID  ((jlong)0)

/*
 * Globbls usfd tirougiout tif bbdk fnd
 */

typfdff jint FrbmfNumbfr;

typfdff strudt {
    jvmtiEnv *jvmti;
    JbvbVM   *jvm;
    volbtilf jboolfbn vmDfbd; /* Ondf VM is dfbd it stbys tibt wby - don't put in init */
    jboolfbn bssfrtOn;
    jboolfbn bssfrtFbtbl;
    jboolfbn dofrrorfxit;
    jboolfbn modififdUtf8;
    jboolfbn quift;

    /* Dfbug flbgs (bit mbsk) */
    int      dfbugflbgs;

    /* Possiblf dfbug flbgs */
    #dffinf USE_ITERATE_THROUGH_HEAP 0X001

    dibr * options;

    jdlbss              dlbssClbss;
    jdlbss              tirfbdClbss;
    jdlbss              tirfbdGroupClbss;
    jdlbss              dlbssLobdfrClbss;
    jdlbss              stringClbss;
    jdlbss              systfmClbss;
    jmftiodID           tirfbdConstrudtor;
    jmftiodID           tirfbdSftDbfmon;
    jmftiodID           tirfbdRfsumf;
    jmftiodID           systfmGftPropfrty;
    jmftiodID           sftPropfrty;
    jtirfbdGroup        systfmTirfbdGroup;
    jobjfdt             bgfnt_propfrtifs;

    jint                dbdifdJvmtiVfrsion;
    jvmtiCbpbbilitifs   dbdifdJvmtiCbpbbilitifs;
    jboolfbn            ibvfCbdifdJvmtiCbpbbilitifs;
    jvmtiEvfntCbllbbdks dbllbbdks;

    /* Vbrious propfrty vblufs wf siould grbb on initiblizbtion */
    dibr* propfrty_jbvb_vfrsion;          /* UTF8 jbvb.vfrsion */
    dibr* propfrty_jbvb_vm_nbmf;          /* UTF8 jbvb.vm.nbmf */
    dibr* propfrty_jbvb_vm_info;          /* UTF8 jbvb.vm.info */
    dibr* propfrty_jbvb_dlbss_pbti;       /* UTF8 jbvb.dlbss.pbti */
    dibr* propfrty_sun_boot_dlbss_pbti;   /* UTF8 sun.boot.dlbss.pbti */
    dibr* propfrty_sun_boot_librbry_pbti; /* UTF8 sun.boot.librbry.pbti */
    dibr* propfrty_pbti_sfpbrbtor;        /* UTF8 pbti.sfpbrbtor */
    dibr* propfrty_usfr_dir;              /* UTF8 usfr.dir */

    unsignfd log_flbgs;

    /* Common Rfffrfndfs stbtid dbtb */
    jrbwMonitorID rffLodk;
    jlong         nfxtSfqNum;
    RffNodf     **objfdtsByID;
    int           objfdtsByIDsizf;
    int           objfdtsByIDdount;

     /* Indidbtion tibt tif bgfnt ibs bffn lobdfd */
     jboolfbn isLobdfd;

} BbdkfndGlobblDbtb;

fxtfrn BbdkfndGlobblDbtb * gdbtb;

/*
 * Evfnt Indfx for ibndlfrs
 */

typfdff fnum {
        EI_min                  =  1,

        EI_SINGLE_STEP          =  1,
        EI_BREAKPOINT           =  2,
        EI_FRAME_POP            =  3,
        EI_EXCEPTION            =  4,
        EI_THREAD_START         =  5,
        EI_THREAD_END           =  6,
        EI_CLASS_PREPARE        =  7,
        EI_GC_FINISH            =  8,
        EI_CLASS_LOAD           =  9,
        EI_FIELD_ACCESS         = 10,
        EI_FIELD_MODIFICATION   = 11,
        EI_EXCEPTION_CATCH      = 12,
        EI_METHOD_ENTRY         = 13,
        EI_METHOD_EXIT          = 14,
        EI_MONITOR_CONTENDED_ENTER = 15,
        EI_MONITOR_CONTENDED_ENTERED = 16,
        EI_MONITOR_WAIT         = 17,
        EI_MONITOR_WAITED       = 18,
        EI_VM_INIT              = 19,
        EI_VM_DEATH             = 20,
        EI_mbx                  = 20
} EvfntIndfx;

/* Agfnt frrors tibt migit bf in b jvmtiError for JDWP or intfrnbl.
 *    (Donf tiis wby so tibt dompilfr bllows it's usf bs b jvmtiError)
 */
#dffinf _AGENT_ERROR(x)                 ((jvmtiError)(JVMTI_ERROR_MAX+64+x))
#dffinf AGENT_ERROR_INTERNAL                    _AGENT_ERROR(1)
#dffinf AGENT_ERROR_VM_DEAD                     _AGENT_ERROR(2)
#dffinf AGENT_ERROR_NO_JNI_ENV                  _AGENT_ERROR(3)
#dffinf AGENT_ERROR_JNI_EXCEPTION               _AGENT_ERROR(4)
#dffinf AGENT_ERROR_JVMTI_INTERNAL              _AGENT_ERROR(5)
#dffinf AGENT_ERROR_JDWP_INTERNAL               _AGENT_ERROR(6)
#dffinf AGENT_ERROR_NOT_CURRENT_FRAME           _AGENT_ERROR(7)
#dffinf AGENT_ERROR_OUT_OF_MEMORY               _AGENT_ERROR(8)
#dffinf AGENT_ERROR_INVALID_TAG                 _AGENT_ERROR(9)
#dffinf AGENT_ERROR_ALREADY_INVOKING            _AGENT_ERROR(10)
#dffinf AGENT_ERROR_INVALID_INDEX               _AGENT_ERROR(11)
#dffinf AGENT_ERROR_INVALID_LENGTH              _AGENT_ERROR(12)
#dffinf AGENT_ERROR_INVALID_STRING              _AGENT_ERROR(13)
#dffinf AGENT_ERROR_INVALID_CLASS_LOADER        _AGENT_ERROR(14)
#dffinf AGENT_ERROR_INVALID_ARRAY               _AGENT_ERROR(15)
#dffinf AGENT_ERROR_TRANSPORT_LOAD              _AGENT_ERROR(16)
#dffinf AGENT_ERROR_TRANSPORT_INIT              _AGENT_ERROR(17)
#dffinf AGENT_ERROR_NATIVE_METHOD               _AGENT_ERROR(18)
#dffinf AGENT_ERROR_INVALID_COUNT               _AGENT_ERROR(19)
#dffinf AGENT_ERROR_INVALID_FRAMEID             _AGENT_ERROR(20)
#dffinf AGENT_ERROR_NULL_POINTER                _AGENT_ERROR(21)
#dffinf AGENT_ERROR_ILLEGAL_ARGUMENT            _AGENT_ERROR(22)
#dffinf AGENT_ERROR_INVALID_THREAD              _AGENT_ERROR(23)
#dffinf AGENT_ERROR_INVALID_EVENT_TYPE          _AGENT_ERROR(24)
#dffinf AGENT_ERROR_INVALID_OBJECT              _AGENT_ERROR(25)
#dffinf AGENT_ERROR_NO_MORE_FRAMES              _AGENT_ERROR(26)

/* Combinfd fvfnt informbtion */

typfdff strudt {

    EvfntIndfx  fi;
    jtirfbd     tirfbd;
    jdlbss      dlbzz;
    jmftiodID   mftiod;
    jlodbtion   lodbtion;
    jobjfdt     objfdt; /* possibly bn fxdfption or usfr objfdt */

    union {

        /* fi = EI_FIELD_ACCESS */
        strudt {
            jdlbss      fifld_dlbzz;
            jfifldID    fifld;
        } fifld_bddfss;

        /* fi = EI_FIELD_MODIFICATION */
        strudt {
            jdlbss      fifld_dlbzz;
            jfifldID    fifld;
            dibr        signbturf_typf;
            jvbluf      nfw_vbluf;
        } fifld_modifidbtion;

        /* fi = EI_EXCEPTION */
        strudt {
            jdlbss      dbtdi_dlbzz;
            jmftiodID   dbtdi_mftiod;
            jlodbtion   dbtdi_lodbtion;
        } fxdfption;

        /* fi = EI_METHOD_EXIT */
        strudt {
            jvbluf      rfturn_vbluf;
        } mftiod_fxit;

        /* For monitor wbit fvfnts */
        union {
            /* fi = EI_MONITOR_WAIT */
            jlong timfout;
            /* fi = EI_MONITOR_WAITED */
            jboolfbn timfd_out;
        } monitor;
    } u;

} EvfntInfo;

/* Strudturf to iold dynbmid brrby of objfdts */
typfdff strudt ObjfdtBbtdi {
    jobjfdt *objfdts;
    jint     dount;
} ObjfdtBbtdi;

/*
 * JNI signbturf donstbnts, bfyond tiosf dffinfd in JDWP_TAG(*)
 */
#dffinf SIGNATURE_BEGIN_ARGS    '('
#dffinf SIGNATURE_END_ARGS      ')'
#dffinf SIGNATURE_END_CLASS     ';'

/*
 * Modififr flbgs for dlbssfs, fiflds, mftiods
 */
#dffinf MOD_PUBLIC       0x0001     /* visiblf to fvfryonf */
#dffinf MOD_PRIVATE      0x0002     /* visiblf only to tif dffining dlbss */
#dffinf MOD_PROTECTED    0x0004     /* visiblf to subdlbssfs */
#dffinf MOD_STATIC       0x0008     /* instbndf vbribblf is stbtid */
#dffinf MOD_FINAL        0x0010     /* no furtifr subdlbssing, ovfrriding */
#dffinf MOD_SYNCHRONIZED 0x0020     /* wrbp mftiod dbll in monitor lodk */
#dffinf MOD_VOLATILE     0x0040     /* dbn dbdif in rfgistfrs */
#dffinf MOD_TRANSIENT    0x0080     /* not pfrsistbnt */
#dffinf MOD_NATIVE       0x0100     /* implfmfntfd in C */
#dffinf MOD_INTERFACE    0x0200     /* dlbss is bn intfrfbdf */
#dffinf MOD_ABSTRACT     0x0400     /* no dffinition providfd */
/*
 * Additionbl modififrs not dffinfd bs sudi in tif JVM spfd
 */
#dffinf MOD_SYNTHETIC    0xf0000000  /* not in sourdf dodf */

/*
 * jlong donvfrsion mbdros
 */
#dffinf jlong_zfro       ((jlong) 0)
#dffinf jlong_onf        ((jlong) 1)

#dffinf jlong_to_ptr(b)  ((void*)(intptr_t)(b))
#dffinf ptr_to_jlong(b)  ((jlong)(intptr_t)(b))
#dffinf jint_to_jlong(b) ((jlong)(b))
#dffinf jlong_to_jint(b) ((jint)(b))


/*
 * util funds
 */
void util_initiblizf(JNIEnv *fnv);
void util_rfsft(void);

strudt PbdkftInputStrfbm;
strudt PbdkftOutputStrfbm;

jint uniqufID(void);
jbytf rfffrfndfTypfTbg(jdlbss dlbzz);
jbytf spfdifidTypfKfy(JNIEnv *fnv, jobjfdt objfdt);
jboolfbn isObjfdtTbg(jbytf tbg);
jvmtiError spbwnNfwTirfbd(jvmtiStbrtFundtion fund, void *brg, dibr *nbmf);
void donvfrtSignbturfToClbssnbmf(dibr *donvfrt);
void writfCodfLodbtion(strudt PbdkftOutputStrfbm *out, jdlbss dlbzz,
                       jmftiodID mftiod, jlodbtion lodbtion);

jvmtiError dlbssInstbndfs(jdlbss klbss, ObjfdtBbtdi *instbndfs, int mbxInstbndfs);
jvmtiError dlbssInstbndfCounts(jint dlbssCount, jdlbss *dlbssfs, jlong *dounts);
jvmtiError objfdtRfffrrfrs(jobjfdt obj, ObjfdtBbtdi *rfffrrfrs, int mbxObjfdts);

/*
 * Commbnd ibndling iflpfrs sibrfd bmong multiplf dommbnd sfts
 */
int filtfrDfbugTirfbds(jtirfbd *tirfbds, int dount);


void sibrfdGftFifldVblufs(strudt PbdkftInputStrfbm *in,
                          strudt PbdkftOutputStrfbm *out,
                          jboolfbn isStbtid);
jboolfbn sibrfdInvokf(strudt PbdkftInputStrfbm *in,
                      strudt PbdkftOutputStrfbm *out);

jvmtiError fifldSignbturf(jdlbss, jfifldID, dibr **, dibr **, dibr **);
jvmtiError fifldModififrs(jdlbss, jfifldID, jint *);
jvmtiError mftiodSignbturf(jmftiodID, dibr **, dibr **, dibr **);
jvmtiError mftiodRfturnTypf(jmftiodID, dibr *);
jvmtiError mftiodModififrs(jmftiodID, jint *);
jvmtiError mftiodClbss(jmftiodID, jdlbss *);
jvmtiError mftiodLodbtion(jmftiodID, jlodbtion*, jlodbtion*);
jvmtiError dlbssLobdfr(jdlbss, jobjfdt *);

/*
 * Tiin wrbppfrs on top of JNI
 */
JNIEnv *gftEnv(void);
jboolfbn isClbss(jobjfdt objfdt);
jboolfbn isTirfbd(jobjfdt objfdt);
jboolfbn isTirfbdGroup(jobjfdt objfdt);
jboolfbn isString(jobjfdt objfdt);
jboolfbn isClbssLobdfr(jobjfdt objfdt);
jboolfbn isArrby(jobjfdt objfdt);

/*
 * Tiin wrbppfrs on top of JVMTI
 */
jvmtiError jvmtiGftCbpbbilitifs(jvmtiCbpbbilitifs *dbps);
jint jvmtiMbjorVfrsion(void);
jint jvmtiMinorVfrsion(void);
jint jvmtiMidroVfrsion(void);
jvmtiError gftSourdfDfbugExtfnsion(jdlbss dlbzz, dibr **fxtfnsionPtr);
jboolfbn dbnSuspfndRfsumfTirfbdLists(void);

jrbwMonitorID dfbugMonitorCrfbtf(dibr *nbmf);
void dfbugMonitorEntfr(jrbwMonitorID tifLodk);
void dfbugMonitorExit(jrbwMonitorID tifLodk);
void dfbugMonitorWbit(jrbwMonitorID tifLodk);
void dfbugMonitorTimfdWbit(jrbwMonitorID tifLodk, jlong millis);
void dfbugMonitorNotify(jrbwMonitorID tifLodk);
void dfbugMonitorNotifyAll(jrbwMonitorID tifLodk);
void dfbugMonitorDfstroy(jrbwMonitorID tifLodk);

jtirfbd *bllTirfbds(jint *dount);

void tirfbdGroupInfo(jtirfbdGroup, jvmtiTirfbdGroupInfo *info);

dibr *gftClbssnbmf(jdlbss);
jvmtiError dlbssSignbturf(jdlbss, dibr**, dibr**);
jint dlbssStbtus(jdlbss);
void writfGfnfridSignbturf(strudt PbdkftOutputStrfbm *, dibr *);
jboolfbn isMftiodNbtivf(jmftiodID);
jboolfbn isMftiodObsolftf(jmftiodID);
jvmtiError isMftiodSyntiftid(jmftiodID, jboolfbn*);
jvmtiError isFifldSyntiftid(jdlbss, jfifldID, jboolfbn*);

jboolfbn isSbmfObjfdt(JNIEnv *fnv, jobjfdt o1, jobjfdt o2);

jint objfdtHbsiCodf(jobjfdt);

jvmtiError bllIntfrfbdfs(jdlbss dlbzz, jdlbss **ppintfrfbdfs, jint *dount);
jvmtiError bllLobdfdClbssfs(jdlbss **ppdlbssfs, jint *dount);
jvmtiError bllClbssLobdfrClbssfs(jobjfdt lobdfr, jdlbss **ppdlbssfs, jint *dount);
jvmtiError bllNfstfdClbssfs(jdlbss dlbzz, jdlbss **ppnfstfd, jint *pdount);

void sftAgfntPropfrtyVbluf(JNIEnv *fnv, dibr *propfrtyNbmf, dibr* propfrtyVbluf);

void *jvmtiAllodbtf(jint numBytfs);
void jvmtiDfbllodbtf(void *bufffr);

void             fvfntIndfxInit(void);
jdwpEvfnt        fvfntIndfx2jdwp(EvfntIndfx i);
jvmtiEvfnt       fvfntIndfx2jvmti(EvfntIndfx i);
EvfntIndfx       jdwp2EvfntIndfx(jdwpEvfnt fvfntTypf);
EvfntIndfx       jvmti2EvfntIndfx(jvmtiEvfnt kind);

jvmtiError       mbp2jvmtiError(jdwpError);
jdwpError        mbp2jdwpError(jvmtiError);
jdwpTirfbdStbtus mbp2jdwpTirfbdStbtus(jint stbtf);
jint             mbp2jdwpSuspfndStbtus(jint stbtf);
jint             mbp2jdwpClbssStbtus(jint);

void log_dfbugff_lodbtion(donst dibr *fund,
                jtirfbd tirfbd, jmftiodID mftiod, jlodbtion lodbtion);

/*
 * Lodbl Rfffrfndf mbnbgfmfnt. Tif two mbdros bflow brf usfd
 * tirougiout tif bbdk fnd wifnfvfr spbdf for JNI lodbl rfffrfndfs
 * is nffdfd in tif durrfnt frbmf.
 */

void drfbtfLodblRffSpbdf(JNIEnv *fnv, jint dbpbdity);

#dffinf WITH_LOCAL_REFS(fnv, numbfr) \
    drfbtfLodblRffSpbdf(fnv, numbfr); \
    { /* BEGINNING OF WITH SCOPE */

#dffinf END_WITH_LOCAL_REFS(fnv) \
        JNI_FUNC_PTR(fnv,PopLodblFrbmf)(fnv, NULL); \
    } /* END OF WITH SCOPE */

void sbvfGlobblRff(JNIEnv *fnv, jobjfdt obj, jobjfdt *pobj);
void tossGlobblRff(JNIEnv *fnv, jobjfdt *pobj);

#fndif
