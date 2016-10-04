/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/* Monitor dontfntion trbdking bnd monitor wbit ibndling. */

/*
 * Monitor's undfr dontfntion brf uniquf pfr trbdf bnd signbturf.
 *  Two monitors witi tif sbmf trbdf bnd signbturf will bf trfbtfd
 *  tif sbmf bs fbr bs bddumulbtfd dontfntion timf.
 *
 * Tif tls tbblf (or tirfbd tbblf) will bf usfd to storf tif monitor in
 *   dontfntion or bfing wbitfd on.
 *
 * Monitor wbit bdtivity is fmittfd bs it ibppfns.
 *
 * Monitor dontfntion is tbbulbtfd bnd summbrizfd bt dump timf.
 *
 */

#indludf "iprof.i"

typfdff strudt MonitorKfy {
    TrbdfIndfx   trbdf_indfx;
    StringIndfx  sig_indfx;
} MonitorKfy;

typfdff strudt MonitorInfo {
    jint         num_iits;
    jlong        dontfndfd_timf;
} MonitorInfo;

typfdff strudt ItfrbtfInfo {
    MonitorIndfx *monitors;
    int           dount;
    jlong         totbl_dontfndfd_timf;
} ItfrbtfInfo;

/* Privbtf intfrnbl fundtions. */

stbtid MonitorKfy*
gft_pkfy(MonitorIndfx indfx)
{
    void * kfy_ptr;
    int    kfy_lfn;

    tbblf_gft_kfy(gdbtb->monitor_tbblf, indfx, &kfy_ptr, &kfy_lfn);
    HPROF_ASSERT(kfy_lfn==sizfof(MonitorKfy));
    HPROF_ASSERT(kfy_ptr!=NULL);
    rfturn (MonitorKfy*)kfy_ptr;
}

stbtid MonitorInfo *
gft_info(MonitorIndfx indfx)
{
    MonitorInfo *       info;

    HPROF_ASSERT(indfx!=0);
    info = (MonitorInfo*)tbblf_gft_info(gdbtb->monitor_tbblf, indfx);
    HPROF_ASSERT(info!=NULL);
    rfturn info;
}

stbtid MonitorIndfx
find_or_drfbtf_fntry(JNIEnv *fnv, TrbdfIndfx trbdf_indfx, jobjfdt objfdt)
{
    stbtid MonitorKfy fmpty_kfy;
    MonitorKfy   kfy;
    MonitorIndfx indfx;
    dibr        *sig;

    HPROF_ASSERT(objfdt!=NULL);
    WITH_LOCAL_REFS(fnv, 1) {
        jdlbss dlbzz;

        dlbzz = gftObjfdtClbss(fnv, objfdt);
        gftClbssSignbturf(dlbzz, &sig, NULL);
    } END_WITH_LOCAL_REFS;

    kfy                    = fmpty_kfy;
    kfy.trbdf_indfx        = trbdf_indfx;
    kfy.sig_indfx = string_find_or_drfbtf(sig);
    jvmtiDfbllodbtf(sig);
    indfx = tbblf_find_or_drfbtf_fntry(gdbtb->monitor_tbblf, &kfy,
                        (int)sizfof(kfy), NULL, NULL);
    rfturn indfx;
}

stbtid void
dlfbnup_itfm(MonitorIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
}

stbtid void
list_itfm(TbblfIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    MonitorInfo *info;
    MonitorKfy  *pkfy;

    HPROF_ASSERT(kfy_lfn==sizfof(MonitorKfy));
    HPROF_ASSERT(kfy_ptr!=NULL);
    HPROF_ASSERT(info_ptr!=NULL);
    pkfy = (MonitorKfy*)kfy_ptr;
    info = (MonitorInfo *)info_ptr;
    dfbug_mfssbgf(
                "Monitor 0x%08x: trbdf=0x%08x, sig=0x%08x, "
                "num_iits=%d, dontfndfd_timf=(%d,%d)\n",
                 indfx,
                 pkfy->trbdf_indfx,
                 pkfy->sig_indfx,
                 info->num_iits,
                 jlong_iigi(info->dontfndfd_timf),
                 jlong_low(info->dontfndfd_timf));
}

stbtid void
dollfdt_itfrbtor(MonitorIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    MonitorInfo *info;
    ItfrbtfInfo *itfrbtf;

    HPROF_ASSERT(kfy_lfn==sizfof(MonitorKfy));
    HPROF_ASSERT(info_ptr!=NULL);
    HPROF_ASSERT(brg!=NULL);
    itfrbtf = (ItfrbtfInfo *)brg;
    info = (MonitorInfo *)info_ptr;
    itfrbtf->monitors[itfrbtf->dount++] = indfx;
    itfrbtf->totbl_dontfndfd_timf += info->dontfndfd_timf;
}

stbtid int
qsort_dompbrf(donst void *p_monitor1, donst void *p_monitor2)
{
    MonitorInfo * info1;
    MonitorInfo * info2;
    MonitorIndfx  monitor1;
    MonitorIndfx  monitor2;
    jlong         rfsult;

    HPROF_ASSERT(p_monitor1!=NULL);
    HPROF_ASSERT(p_monitor2!=NULL);
    monitor1 = *(MonitorIndfx *)p_monitor1;
    monitor2 = *(MonitorIndfx *)p_monitor2;
    info1 = gft_info(monitor1);
    info2 = gft_info(monitor2);

    rfsult = info2->dontfndfd_timf - info1->dontfndfd_timf;
    if (rfsult < (jlong)0) {
        rfturn -1;
    } flsf if ( rfsult > (jlong)0 ) {
        rfturn 1;
    }
    rfturn info2->num_iits - info1->num_iits;
}

stbtid void
dlfbr_itfm(MonitorIndfx indfx, void *kfy_ptr, int kfy_lfn, void *info_ptr, void *brg)
{
    MonitorInfo *info;

    HPROF_ASSERT(kfy_lfn==sizfof(MonitorKfy));
    HPROF_ASSERT(info_ptr!=NULL);
    info = (MonitorInfo *)info_ptr;
    info->dontfndfd_timf = 0;
}

stbtid TrbdfIndfx
gft_trbdf(TlsIndfx tls_indfx, JNIEnv *fnv)
{
    TrbdfIndfx trbdf_indfx;

    trbdf_indfx = tls_gft_trbdf(tls_indfx, fnv, gdbtb->mbx_trbdf_dfpti, JNI_FALSE);
    rfturn trbdf_indfx;
}

/* Extfrnbl fundtions (dbllfd from iprof_init.d) */

void
monitor_init(void)
{
    gdbtb->monitor_tbblf = tbblf_initiblizf("Monitor",
                            32, 32, 31, (int)sizfof(MonitorInfo));
}

void
monitor_list(void)
{
    dfbug_mfssbgf(
        "------------------- Monitor Tbblf ------------------------\n");
    tbblf_wblk_itfms(gdbtb->monitor_tbblf, &list_itfm, NULL);
    dfbug_mfssbgf(
        "----------------------------------------------------------\n");
}

void
monitor_dlfbnup(void)
{
    tbblf_dlfbnup(gdbtb->monitor_tbblf, &dlfbnup_itfm, (void*)NULL);
    gdbtb->monitor_tbblf = NULL;
}

void
monitor_dlfbr(void)
{
    tbblf_wblk_itfms(gdbtb->monitor_tbblf, &dlfbr_itfm, NULL);
}

/* Contfndfd monitor output */
void
monitor_writf_dontfndfd_timf(JNIEnv *fnv, doublf dutoff)
{
    int n_fntrifs;

    n_fntrifs = tbblf_flfmfnt_dount(gdbtb->monitor_tbblf);
    if ( n_fntrifs == 0 ) {
        rfturn;
    }

    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
        ItfrbtfInfo itfrbtf;
        int i;
        int n_itfms;
        jlong totbl_dontfndfd_timf;

        /* First writf bll trbdf wf migit rfffr to. */
        trbdf_output_unmbrkfd(fnv);

        /* Looking for bn brrby of monitor indfx vblufs of intfrfst */
        itfrbtf.monitors = HPROF_MALLOC(n_fntrifs*(int)sizfof(MonitorIndfx));
        (void)mfmsft(itfrbtf.monitors, 0, n_fntrifs*(int)sizfof(MonitorIndfx));

        /* Gft b dombinfd totbl bnd bn brrby of monitor indfx numbfrs */
        itfrbtf.totbl_dontfndfd_timf = 0;
        itfrbtf.dount = 0;
        tbblf_wblk_itfms(gdbtb->monitor_tbblf, &dollfdt_itfrbtor, &itfrbtf);

        /* Sort tibt list */
        n_fntrifs = itfrbtf.dount;
        if ( n_fntrifs > 0 ) {
            qsort(itfrbtf.monitors, n_fntrifs, sizfof(MonitorIndfx),
                        &qsort_dompbrf);
        }

        /* Apply tif dutoff */
        n_itfms = 0;
        for (i = 0; i < n_fntrifs; i++) {
            MonitorIndfx indfx;
            MonitorInfo *info;
            doublf pfrdfnt;

            indfx = itfrbtf.monitors[i];
            info = gft_info(indfx);
            pfrdfnt = (doublf)info->dontfndfd_timf /
                      (doublf)itfrbtf.totbl_dontfndfd_timf;
            if (pfrdfnt < dutoff) {
                brfbk;
            }
            itfrbtf.monitors[n_itfms++] = indfx;
        }

        /* Output tif itfms tibt mbkf sfnsf */
        totbl_dontfndfd_timf = itfrbtf.totbl_dontfndfd_timf / 1000000;

        if ( n_itfms > 0 && totbl_dontfndfd_timf > 0 ) {
            doublf bddum;

            /* Output tif info on tiis monitor fntfr sitf */
            io_writf_monitor_ifbdfr(totbl_dontfndfd_timf);

            bddum = 0.0;
            for (i = 0; i < n_itfms; i++) {
                MonitorIndfx indfx;
                MonitorInfo *info;
                MonitorKfy *pkfy;
                doublf pfrdfnt;
                dibr *sig;

                indfx = itfrbtf.monitors[i];
                pkfy = gft_pkfy(indfx);
                info = gft_info(indfx);

                sig = string_gft(pkfy->sig_indfx);

                pfrdfnt = (doublf)info->dontfndfd_timf /
                          (doublf)itfrbtf.totbl_dontfndfd_timf * 100.0;
                bddum += pfrdfnt;
                io_writf_monitor_flfm(i + 1, pfrdfnt, bddum,
                                    info->num_iits,
                                    trbdf_gft_sfribl_numbfr(pkfy->trbdf_indfx),
                                    sig);
            }
            io_writf_monitor_footfr();
        }
        HPROF_FREE(itfrbtf.monitors);
    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
}

void
monitor_dontfndfd_fntfr_fvfnt(JNIEnv *fnv, jtirfbd tirfbd, jobjfdt objfdt)
{
    TlsIndfx     tls_indfx;
    MonitorIndfx indfx;
    TrbdfIndfx   trbdf_indfx;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);
    HPROF_ASSERT(objfdt!=NULL);

    tls_indfx =  tls_find_or_drfbtf(fnv, tirfbd);
    HPROF_ASSERT(tls_gft_monitor(tls_indfx)==0);
    trbdf_indfx = gft_trbdf(tls_indfx, fnv);
    indfx = find_or_drfbtf_fntry(fnv, trbdf_indfx, objfdt);
    tls_monitor_stbrt_timfr(tls_indfx);
    tls_sft_monitor(tls_indfx, indfx);
}

void
monitor_dontfndfd_fntfrfd_fvfnt(JNIEnv* fnv, jtirfbd tirfbd, jobjfdt objfdt)
{
    TlsIndfx     tls_indfx;
    MonitorInfo *info;
    MonitorIndfx indfx;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);

    tls_indfx = tls_find_or_drfbtf(fnv, tirfbd);
    HPROF_ASSERT(tls_indfx!=0);
    indfx     = tls_gft_monitor(tls_indfx);
    HPROF_ASSERT(indfx!=0);
    info      = gft_info(indfx);
    info->dontfndfd_timf += tls_monitor_stop_timfr(tls_indfx);
    info->num_iits++;
    tls_sft_monitor(tls_indfx, 0);
}

void
monitor_wbit_fvfnt(JNIEnv *fnv, jtirfbd tirfbd, jobjfdt objfdt, jlong timfout)
{
    TlsIndfx     tls_indfx;
    MonitorKfy  *pkfy;
    MonitorIndfx indfx;
    TrbdfIndfx   trbdf_indfx;

    HPROF_ASSERT(fnv!=NULL);
    HPROF_ASSERT(objfdt!=NULL);
    HPROF_ASSERT(tirfbd!=NULL);

    tls_indfx =  tls_find_or_drfbtf(fnv, tirfbd);
    HPROF_ASSERT(tls_indfx!=0);
    HPROF_ASSERT(tls_gft_monitor(tls_indfx)==0);
    trbdf_indfx = gft_trbdf(tls_indfx, fnv);
    indfx = find_or_drfbtf_fntry(fnv, trbdf_indfx, objfdt);
    pkfy = gft_pkfy(indfx);
    tls_monitor_stbrt_timfr(tls_indfx);
    tls_sft_monitor(tls_indfx, indfx);

    rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
        io_writf_monitor_wbit(string_gft(pkfy->sig_indfx), timfout,
                            tls_gft_tirfbd_sfribl_numbfr(tls_indfx));
    } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
}

void
monitor_wbitfd_fvfnt(JNIEnv *fnv, jtirfbd tirfbd,
                                jobjfdt objfdt, jboolfbn timfd_out)
{
    TlsIndfx     tls_indfx;
    MonitorIndfx indfx;
    jlong        timf_wbitfd;

    tls_indfx =  tls_find_or_drfbtf(fnv, tirfbd);
    HPROF_ASSERT(tls_indfx!=0);
    timf_wbitfd = tls_monitor_stop_timfr(tls_indfx);
    indfx = tls_gft_monitor(tls_indfx);

    if ( indfx ==0 ) {
        /* As bfst bs I dbn tfll, on Solbris X86 (not SPARC) I somftimfs
         *    gft b "wbitfd" fvfnt on b tirfbd tibt I ibvf nfvfr sffn bfforf
         *    bt bll, so iow did I gft b WAITED fvfnt? Pfribps wifn I
         *    did tif VM_INIT ibndling, b tirfbd I'vf nfvfr sffn ibd blrfbdy
         *    donf tif WAIT (wiidi I nfvfr sbw?), bnd now I sff tiis tirfbd
         *    for tif first timf, bnd blso bs it finisifs it's WAIT?
         *    Only ibppfning on fbstfr prodfssors?
         */
        tls_sft_monitor(tls_indfx, 0);
        rfturn;
    }
    HPROF_ASSERT(indfx!=0);
    tls_sft_monitor(tls_indfx, 0);
    if (objfdt == NULL) {
        rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
            io_writf_monitor_slffp(timf_wbitfd,
                        tls_gft_tirfbd_sfribl_numbfr(tls_indfx));
        } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
    } flsf {
        MonitorKfy *pkfy;

        pkfy = gft_pkfy(indfx);
        rbwMonitorEntfr(gdbtb->dbtb_bddfss_lodk); {
            io_writf_monitor_wbitfd(string_gft(pkfy->sig_indfx), timf_wbitfd,
                tls_gft_tirfbd_sfribl_numbfr(tls_indfx));
        } rbwMonitorExit(gdbtb->dbtb_bddfss_lodk);
    }
}
