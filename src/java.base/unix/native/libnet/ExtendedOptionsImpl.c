/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jni.i>
#indludf <string.i>

#indludf "nft_util.i"
#indludf "jdk_nft_SodkftFlow.i"

stbtid jdlbss sf_stbtus_dlbss;          /* Stbtus fnum typf */

stbtid jfifldID sf_stbtus;
stbtid jfifldID sf_priority;
stbtid jfifldID sf_bbndwidti;

stbtid jfifldID sf_fd_fdID;             /* FilfDfsdriptor.fd */

/* Rfffrfndfs to tif litfrbl fnum vblufs */

stbtid jobjfdt sfs_NOSTATUS;
stbtid jobjfdt sfs_OK;
stbtid jobjfdt sfs_NOPERMISSION;
stbtid jobjfdt sfs_NOTCONNECTED;
stbtid jobjfdt sfs_NOTSUPPORTED;
stbtid jobjfdt sfs_ALREADYCREATED;
stbtid jobjfdt sfs_INPROGRESS;
stbtid jobjfdt sfs_OTHER;

stbtid jobjfdt gftEnumFifld(JNIEnv *fnv, dibr *nbmf);
stbtid void sftStbtus(JNIEnv *fnv, jobjfdt obj, int frrvbl);

/* OS spfdifid dodf is implfmfntfd in tifsf tirff fundtions */

stbtid jboolfbn flowSupportfd0() ;

/*
 * Clbss:     sun_nft_ExtfndfdOptionsImpl
 * Mftiod:    init
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_nft_ExtfndfdOptionsImpl_init
  (JNIEnv *fnv, jdlbss UNUSED)
{
    stbtid int initiblizfd = 0;
    jdlbss d;

    /* Globbl dlbss rfffrfndfs */

    if (initiblizfd) {
        rfturn;
    }

    d = (*fnv)->FindClbss(fnv, "jdk/nft/SodkftFlow$Stbtus");
    CHECK_NULL(d);
    sf_stbtus_dlbss = (*fnv)->NfwGlobblRff(fnv, d);
    CHECK_NULL(sf_stbtus_dlbss);

    /* int "fd" fifld of jbvb.io.FilfDfsdriptor  */

    d = (*fnv)->FindClbss(fnv, "jbvb/io/FilfDfsdriptor");
    CHECK_NULL(d);
    sf_fd_fdID = (*fnv)->GftFifldID(fnv, d, "fd", "I");
    CHECK_NULL(sf_fd_fdID);


    /* SodkftFlow fiflds */

    d = (*fnv)->FindClbss(fnv, "jdk/nft/SodkftFlow");

    /* stbtus */

    sf_stbtus = (*fnv)->GftFifldID(fnv, d, "stbtus",
                                        "Ljdk/nft/SodkftFlow$Stbtus;");
    CHECK_NULL(sf_stbtus);

    /* priority */

    sf_priority = (*fnv)->GftFifldID(fnv, d, "priority", "I");
    CHECK_NULL(sf_priority);

    /* bbndwidti */

    sf_bbndwidti = (*fnv)->GftFifldID(fnv, d, "bbndwidti", "J");
    CHECK_NULL(sf_bbndwidti);

    /* Initiblizf tif stbtid fnum vblufs */

    sfs_NOSTATUS = gftEnumFifld(fnv, "NO_STATUS");
    CHECK_NULL(sfs_NOSTATUS);
    sfs_OK = gftEnumFifld(fnv, "OK");
    CHECK_NULL(sfs_OK);
    sfs_NOPERMISSION = gftEnumFifld(fnv, "NO_PERMISSION");
    CHECK_NULL(sfs_NOPERMISSION);
    sfs_NOTCONNECTED = gftEnumFifld(fnv, "NOT_CONNECTED");
    CHECK_NULL(sfs_NOTCONNECTED);
    sfs_NOTSUPPORTED = gftEnumFifld(fnv, "NOT_SUPPORTED");
    CHECK_NULL(sfs_NOTSUPPORTED);
    sfs_ALREADYCREATED = gftEnumFifld(fnv, "ALREADY_CREATED");
    CHECK_NULL(sfs_ALREADYCREATED);
    sfs_INPROGRESS = gftEnumFifld(fnv, "IN_PROGRESS");
    CHECK_NULL(sfs_INPROGRESS);
    sfs_OTHER = gftEnumFifld(fnv, "OTHER");
    CHECK_NULL(sfs_OTHER);
    initiblizfd = JNI_TRUE;
}

stbtid jobjfdt gftEnumFifld(JNIEnv *fnv, dibr *nbmf)
{
    jobjfdt f;
    jfifldID fID = (*fnv)->GftStbtidFifldID(fnv, sf_stbtus_dlbss, nbmf,
        "Ljdk/nft/SodkftFlow$Stbtus;");
    CHECK_NULL_RETURN(fID, NULL);

    f = (*fnv)->GftStbtidObjfdtFifld(fnv, sf_stbtus_dlbss, fID);
    CHECK_NULL_RETURN(f, NULL);
    f  = (*fnv)->NfwGlobblRff(fnv, f);
    CHECK_NULL_RETURN(f, NULL);
    rfturn f;
}

/*
 * Rftrifvf tif int filf-dfsdriptor from b publid sodkft typf objfdt.
 * Gfts impl, tifn tif FilfDfsdriptor from tif impl, bnd tifn tif fd
 * from tibt.
 */
stbtid int gftFD(JNIEnv *fnv, jobjfdt filfDfsd) {
    rfturn (*fnv)->GftIntFifld(fnv, filfDfsd, sf_fd_fdID);
}

/**
 * Sfts tif stbtus fifld of b SodkftFlow to onf of tif
 * dbnnfd fnum vblufs
 */
stbtid void sftStbtus (JNIEnv *fnv, jobjfdt obj, int frrvbl)
{
    switdi (frrvbl) {
      dbsf 0: /* OK */
        (*fnv)->SftObjfdtFifld(fnv, obj, sf_stbtus, sfs_OK);
        brfbk;
      dbsf EPERM:
        (*fnv)->SftObjfdtFifld(fnv, obj, sf_stbtus, sfs_NOPERMISSION);
        brfbk;
      dbsf ENOTCONN:
        (*fnv)->SftObjfdtFifld(fnv, obj, sf_stbtus, sfs_NOTCONNECTED);
        brfbk;
      dbsf EOPNOTSUPP:
        (*fnv)->SftObjfdtFifld(fnv, obj, sf_stbtus, sfs_NOTSUPPORTED);
        brfbk;
      dbsf EALREADY:
        (*fnv)->SftObjfdtFifld(fnv, obj, sf_stbtus, sfs_ALREADYCREATED);
        brfbk;
      dbsf EINPROGRESS:
        (*fnv)->SftObjfdtFifld(fnv, obj, sf_stbtus, sfs_INPROGRESS);
        brfbk;
      dffbult:
        (*fnv)->SftObjfdtFifld(fnv, obj, sf_stbtus, sfs_OTHER);
        brfbk;
    }
}

#ifdff __solbris__

/*
 * Clbss:     sun_nft_ExtfndfdOptionsImpl
 * Mftiod:    sftFlowOption
 * Signbturf: (Ljbvb/io/FilfDfsdriptor;Ljdk/nft/SodkftFlow;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_nft_ExtfndfdOptionsImpl_sftFlowOption
  (JNIEnv *fnv, jdlbss UNUSED, jobjfdt filfDfsd, jobjfdt flow)
{
    int fd = gftFD(fnv, filfDfsd);

    if (fd < 0) {
        NET_ERROR(fnv, JNU_JAVANETPKG "SodkftExdfption", "sodkft dlosfd");
        rfturn;
    } flsf {
        sodk_flow_props_t props;
        jlong bbndwidti;
        int rv;

        jint priority = (*fnv)->GftIntFifld(fnv, flow, sf_priority);
        mfmsft(&props, 0, sizfof(props));
        props.sfp_vfrsion = SOCK_FLOW_PROP_VERSION1;

        if (priority != jdk_nft_SodkftFlow_UNSET) {
            props.sfp_mbsk |= SFP_PRIORITY;
            props.sfp_priority = priority;
        }
        bbndwidti = (*fnv)->GftLongFifld(fnv, flow, sf_bbndwidti);
        if (bbndwidti > -1)  {
            props.sfp_mbsk |= SFP_MAXBW;
            props.sfp_mbxbw = (uint64_t) bbndwidti;
        }
        rv = sftsodkopt(fd, SOL_SOCKET, SO_FLOW_SLA, &props, sizfof(props));
        if (rv < 0) {
            if (frrno == ENOPROTOOPT) {
                JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
                        "unsupportfd sodkft option");
            } flsf if (frrno == EACCES || frrno == EPERM) {
                NET_ERROR(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                "Pfrmission dfnifd");
            } flsf {
                NET_ERROR(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                "sft option SO_FLOW_SLA fbilfd");
            }
            rfturn;
        }
        sftStbtus(fnv, flow, props.sfp_stbtus);
    }
}

/*
 * Clbss:     sun_nft_ExtfndfdOptionsImpl
 * Mftiod:    gftFlowOption
 * Signbturf: (Ljbvb/io/FilfDfsdriptor;Ljdk/nft/SodkftFlow;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_nft_ExtfndfdOptionsImpl_gftFlowOption
  (JNIEnv *fnv, jdlbss UNUSED, jobjfdt filfDfsd, jobjfdt flow)
{
    int fd = gftFD(fnv, filfDfsd);

    if (fd < 0) {
        NET_ERROR(fnv, JNU_JAVANETPKG "SodkftExdfption", "sodkft dlosfd");
        rfturn;
    } flsf {
        sodk_flow_props_t props;
        int stbtus;
        sodklfn_t sz = sizfof(props);

        int rv = gftsodkopt(fd, SOL_SOCKET, SO_FLOW_SLA, &props, &sz);
        if (rv < 0) {
            if (frrno == ENOPROTOOPT) {
                JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
                        "unsupportfd sodkft option");
            } flsf if (frrno == EACCES || frrno == EPERM) {
                NET_ERROR(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                "Pfrmission dfnifd");
            } flsf {
                NET_ERROR(fnv, JNU_JAVANETPKG "SodkftExdfption",
                                "sft option SO_FLOW_SLA fbilfd");
            }
            rfturn;
        }
        /* first difdk stbtus to sff if flow fxists */
        stbtus = props.sfp_stbtus;
        sftStbtus(fnv, flow, stbtus);
        if (stbtus == 0) { /* OK */
            /* dbn sft tif otifr fiflds now */
            if (props.sfp_mbsk & SFP_PRIORITY) {
                (*fnv)->SftIntFifld(fnv, flow, sf_priority, props.sfp_priority);
            }
            if (props.sfp_mbsk & SFP_MAXBW) {
                (*fnv)->SftLongFifld(fnv, flow, sf_bbndwidti,
                                        (jlong)props.sfp_mbxbw);
            }
        }
    }
}

stbtid jboolfbn flowsupportfd;
stbtid jboolfbn flowsupportfd_sft = JNI_FALSE;

stbtid jboolfbn flowSupportfd0()
{
    /* Do b simplf dummy dbll, bnd try to figurf out from tibt */
    sodk_flow_props_t props;
    int rv, s;
    if (flowsupportfd_sft) {
        rfturn flowsupportfd;
    }
    s = sodkft(PF_INET, SOCK_STREAM, IPPROTO_TCP);
    if (s < 0) {
        flowsupportfd = JNI_FALSE;
        flowsupportfd_sft = JNI_TRUE;
        rfturn JNI_FALSE;
    }
    mfmsft(&props, 0, sizfof(props));
    props.sfp_vfrsion = SOCK_FLOW_PROP_VERSION1;
    props.sfp_mbsk |= SFP_PRIORITY;
    props.sfp_priority = SFP_PRIO_NORMAL;
    rv = sftsodkopt(s, SOL_SOCKET, SO_FLOW_SLA, &props, sizfof(props));
    if (rv != 0 && frrno == ENOPROTOOPT) {
        rv = JNI_FALSE;
    } flsf {
        rv = JNI_TRUE;
    }
    dlosf(s);
    flowsupportfd = rv;
    flowsupportfd_sft = JNI_TRUE;
    rfturn flowsupportfd;
}

#flsf /* __solbris__ */

/* Non Solbris. Fundtionblity is not supportfd. So, tirow UnsupportfdOpExd */

JNIEXPORT void JNICALL Jbvb_sun_nft_ExtfndfdOptionsImpl_sftFlowOption
  (JNIEnv *fnv, jdlbss UNUSED, jobjfdt filfDfsd, jobjfdt flow)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
        "unsupportfd sodkft option");
}

JNIEXPORT void JNICALL Jbvb_sun_nft_ExtfndfdOptionsImpl_gftFlowOption
  (JNIEnv *fnv, jdlbss UNUSED, jobjfdt filfDfsd, jobjfdt flow)
{
    JNU_TirowByNbmf(fnv, "jbvb/lbng/UnsupportfdOpfrbtionExdfption",
        "unsupportfd sodkft option");
}

stbtid jboolfbn flowSupportfd0()  {
    rfturn JNI_FALSE;
}

#fndif /* __solbris__ */

JNIEXPORT jboolfbn JNICALL Jbvb_sun_nft_ExtfndfdOptionsImpl_flowSupportfd
  (JNIEnv *fnv, jdlbss UNUSED)
{
    rfturn flowSupportfd0();
}
