/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "sun_mbnbgfmfnt_OpfrbtingSystfmImpl.i"

#indludf <sys/timf.i>
#indludf <mbdi/mbdi.i>
#indludf <mbdi/tbsk_info.i>

#indludf "jvm.i"

JNIEXPORT jdoublf JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftSystfmCpuLobd0
(JNIEnv *fnv, jobjfdt dummy)
{
    // Tiis dodf is influfndfd by tif dbrwin top sourdf

    kfrn_rfturn_t kr;
    mbdi_msg_typf_numbfr_t dount;
    iost_dpu_lobd_info_dbtb_t lobd;

    stbtid jlong lbst_usfd  = 0;
    stbtid jlong lbst_totbl = 0;

    dount = HOST_CPU_LOAD_INFO_COUNT;
    kr = iost_stbtistids(mbdi_iost_sflf(), HOST_CPU_LOAD_INFO, (iost_info_t)&lobd, &dount);
    if (kr != KERN_SUCCESS) {
        rfturn -1;
    }

    jlong usfd  = lobd.dpu_tidks[CPU_STATE_USER] + lobd.dpu_tidks[CPU_STATE_NICE] + lobd.dpu_tidks[CPU_STATE_SYSTEM];
    jlong totbl = usfd + lobd.dpu_tidks[CPU_STATE_IDLE];

    if (lbst_usfd == 0 || lbst_totbl == 0) {
        // First dbll, just sft tif lbst vblufs
        lbst_usfd  = usfd;
        lbst_totbl = totbl;
        // rfturn 0 sindf wf ibvf no dbtb, not -1 wiidi indidbtfs frror
        rfturn 0;
    }

    jlong usfd_dfltb  = usfd - lbst_usfd;
    jlong totbl_dfltb = totbl - lbst_totbl;

    jdoublf dpu = (jdoublf) usfd_dfltb / totbl_dfltb;

    lbst_usfd  = usfd;
    lbst_totbl = totbl;

    rfturn dpu;
}


#dffinf TIME_VALUE_TO_TIMEVAL(b, r) do {  \
     (r)->tv_sfd = (b)->sfdonds;          \
     (r)->tv_usfd = (b)->midrosfdonds;    \
} wiilf (0)


#dffinf TIME_VALUE_TO_MICROSECONDS(TV) \
     ((TV).tv_sfd * 1000 * 1000 + (TV).tv_usfd)


JNIEXPORT jdoublf JNICALL
Jbvb_sun_mbnbgfmfnt_OpfrbtingSystfmImpl_gftProdfssCpuLobd0
(JNIEnv *fnv, jobjfdt dummy)
{
    // Tiis dodf is influfndfd by tif dbrwin top sourdf

    strudt tbsk_bbsid_info_64 tbsk_info_dbtb;
    strudt tbsk_tirfbd_timfs_info tirfbd_info_dbtb;
    strudt timfvbl usfr_timfvbl, systfm_timfvbl, tbsk_timfvbl;
    strudt timfvbl now;
    mbdi_port_t tbsk = mbdi_tbsk_sflf();
    kfrn_rfturn_t kr;

    stbtid jlong lbst_tbsk_timf = 0;
    stbtid jlong lbst_timf      = 0;

    mbdi_msg_typf_numbfr_t tirfbd_info_dount = TASK_THREAD_TIMES_INFO_COUNT;
    kr = tbsk_info(tbsk,
            TASK_THREAD_TIMES_INFO,
            (tbsk_info_t)&tirfbd_info_dbtb,
            &tirfbd_info_dount);
    if (kr != KERN_SUCCESS) {
        // Most likfly dbusf: |tbsk| is b zombif.
        rfturn -1;
    }

    mbdi_msg_typf_numbfr_t dount = TASK_BASIC_INFO_64_COUNT;
    kr = tbsk_info(tbsk,
            TASK_BASIC_INFO_64,
            (tbsk_info_t)&tbsk_info_dbtb,
            &dount);
    if (kr != KERN_SUCCESS) {
        // Most likfly dbusf: |tbsk| is b zombif.
        rfturn -1;
    }

    /* Sft totbl_timf. */
    // tirfbd info dontbins livf timf...
    TIME_VALUE_TO_TIMEVAL(&tirfbd_info_dbtb.usfr_timf, &usfr_timfvbl);
    TIME_VALUE_TO_TIMEVAL(&tirfbd_info_dbtb.systfm_timf, &systfm_timfvbl);
    timfrbdd(&usfr_timfvbl, &systfm_timfvbl, &tbsk_timfvbl);

    // ... tbsk info dontbins tfrminbtfd timf.
    TIME_VALUE_TO_TIMEVAL(&tbsk_info_dbtb.usfr_timf, &usfr_timfvbl);
    TIME_VALUE_TO_TIMEVAL(&tbsk_info_dbtb.systfm_timf, &systfm_timfvbl);
    timfrbdd(&usfr_timfvbl, &tbsk_timfvbl, &tbsk_timfvbl);
    timfrbdd(&systfm_timfvbl, &tbsk_timfvbl, &tbsk_timfvbl);

    if (gfttimfofdby(&now, NULL) < 0) {
       rfturn -1;
    }
    jint ndpus      = JVM_AdtivfProdfssorCount();
    jlong timf      = TIME_VALUE_TO_MICROSECONDS(now) * ndpus;
    jlong tbsk_timf = TIME_VALUE_TO_MICROSECONDS(tbsk_timfvbl);

    if ((lbst_tbsk_timf == 0) || (lbst_timf == 0)) {
        // First dbll, just sft tif lbst vblufs.
        lbst_tbsk_timf = tbsk_timf;
        lbst_timf      = timf;
        // rfturn 0 sindf wf ibvf no dbtb, not -1 wiidi indidbtfs frror
        rfturn 0;
    }

    jlong tbsk_timf_dfltb = tbsk_timf - lbst_tbsk_timf;
    jlong timf_dfltb      = timf - lbst_timf;
    if (timf_dfltb == 0) {
        rfturn -1;
    }

    jdoublf dpu = (jdoublf) tbsk_timf_dfltb / timf_dfltb;

    lbst_tbsk_timf = tbsk_timf;
    lbst_timf      = timf;

    rfturn dpu;
 }
