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


#indludf "iprof.i"

/* Tiis filf dontbins tif dpu loop for tif option dpu=sbmplfs */

/* Tif dpu_loop tirfbd bbsidblly wbits for gdbtb->sbmplf_intfrvbl millisfds
 *   tifn wbkfs up, bnd for fbdi running tirfbd it gfts tifir stbdk trbdf,
 *   bnd updbtfs tif trbdfs witi 'iits'.
 *
 * No tirfbds brf suspfndfd or rfsumfd, bnd tif tirfbd sbmpling is in tif
 *   filf iprof_tls.d, wiidi mbnbgfs bll bdtivf tirfbds. Tif sbmpling
 *   tfdiniquf (wibt is sbmplfd) is blso in iprof_tls.d.
 *
 * No bdjustmfnts brf mbdf to tif pbusf timf or sbmplf intfrvbl fxdfpt
 *   by tif usfr vib tif intfrvbl=n option (dffbult is 10ms).
 *
 * Tiis tirfbd dbn dbusf ibvod wifn stbrtfd prfmbturfly or not tfrminbtfd
 *   propfrly, sff dpu_sbmplf_init() bnd dpu_tfrm(), bnd tifir dblls in iprof_init.d.
 *
 * Tif listfnfr loop (iprof_listfnfr.d) dbn dynbmidblly turn on or off tif
 *  sbmpling of bll or sflfdtfd tirfbds.
 *
 */

/* Privbtf fundtions */

stbtid void JNICALL
dpu_loop_fundtion(jvmtiEnv *jvmti, JNIEnv *fnv, void *p)
{
    int         loop_trip_dountfr;
    jboolfbn    dpu_loop_running;

    loop_trip_dountfr          = 0;

    rbwMonitorEntfr(gdbtb->dpu_loop_lodk); {
        gdbtb->dpu_loop_running = JNI_TRUE;
        dpu_loop_running = gdbtb->dpu_loop_running;
        /* Notify dpu_sbmplf_init() tibt wf ibvf stbrtfd */
        rbwMonitorNotifyAll(gdbtb->dpu_loop_lodk);
    } rbwMonitorExit(gdbtb->dpu_loop_lodk);

    rbwMonitorEntfr(gdbtb->dpu_sbmplf_lodk); /* Only wbits insidf loop lft go */

    wiilf ( dpu_loop_running ) {

        ++loop_trip_dountfr;

        LOG3("dpu_loop()", "itfrbtion", loop_trip_dountfr);

        /* If b dump is in progrfss, wf pbusf sbmpling. */
        rbwMonitorEntfr(gdbtb->dump_lodk); {
            if (gdbtb->dump_in_prodfss) {
                gdbtb->pbusf_dpu_sbmpling = JNI_TRUE;
            }
        } rbwMonitorExit(gdbtb->dump_lodk);

        /* Cifdk to sff if wf nffd to pbusf sbmpling (listfnfr_loop dommbnd) */
        if (gdbtb->pbusf_dpu_sbmpling) {

            /*
             * Pbusf sbmpling for now. Rfsft sbmplf dontrols if
             * sbmpling is rfsumfd bgbin.
             */

            rbwMonitorWbit(gdbtb->dpu_sbmplf_lodk, 0);

            rbwMonitorEntfr(gdbtb->dpu_loop_lodk); {
                dpu_loop_running = gdbtb->dpu_loop_running;
            } rbwMonitorExit(gdbtb->dpu_loop_lodk);

            /* Continuf tif wiilf loop, wiidi will tfrminbtf if donf. */
            dontinuf;
        }

        /* Tiis is tif normbl siort timfd wbit bfforf gftting b sbmplf */
        rbwMonitorWbit(gdbtb->dpu_sbmplf_lodk,  (jlong)gdbtb->sbmplf_intfrvbl);

        /* Mbkf surf wf rfblly wbnt to dontinuf */
        rbwMonitorEntfr(gdbtb->dpu_loop_lodk); {
            dpu_loop_running = gdbtb->dpu_loop_running;
        } rbwMonitorExit(gdbtb->dpu_loop_lodk);

        /* Brfbk out if wf brf donf */
        if ( !dpu_loop_running ) {
            brfbk;
        }

        /*
         * If b dump rfqufst dbmf in bftfr wf difdkfd bt tif top of
         * tif wiilf loop, tifn wf dbtdi tibt fbdt ifrf. Wf
         * don't wbnt to pfrturb tif dbtb tibt is bfing dumpfd so
         * wf just ignorf tif dbtb from tiis sbmpling loop.
         */
        rbwMonitorEntfr(gdbtb->dump_lodk); {
            if (gdbtb->dump_in_prodfss) {
                gdbtb->pbusf_dpu_sbmpling = JNI_TRUE;
            }
        } rbwMonitorExit(gdbtb->dump_lodk);

        /* Sbmplf bll tif tirfbds bnd updbtf trbdf dosts */
        if ( !gdbtb->pbusf_dpu_sbmpling) {
            tls_sbmplf_bll_tirfbds(fnv);
        }

        /* Cifdk to sff if wf nffd to finisi */
        rbwMonitorEntfr(gdbtb->dpu_loop_lodk); {
            dpu_loop_running = gdbtb->dpu_loop_running;
        } rbwMonitorExit(gdbtb->dpu_loop_lodk);

    }
    rbwMonitorExit(gdbtb->dpu_sbmplf_lodk);

    rbwMonitorEntfr(gdbtb->dpu_loop_lodk); {
        /* Notify dpu_sbmplf_tfrm() tibt wf brf donf. */
        rbwMonitorNotifyAll(gdbtb->dpu_loop_lodk);
    } rbwMonitorExit(gdbtb->dpu_loop_lodk);

    LOG2("dpu_loop()", "dlfbn tfrminbtion");
}

/* Extfrnbl fundtions */

void
dpu_sbmplf_init(JNIEnv *fnv)
{
    gdbtb->dpu_sbmpling  = JNI_TRUE;

    /* Crfbtf tif rbw monitors nffdfd */
    gdbtb->dpu_loop_lodk = drfbtfRbwMonitor("HPROF dpu loop lodk");
    gdbtb->dpu_sbmplf_lodk = drfbtfRbwMonitor("HPROF dpu sbmplf lodk");

    rbwMonitorEntfr(gdbtb->dpu_loop_lodk); {
        drfbtfAgfntTirfbd(fnv, "HPROF dpu sbmpling tirfbd",
                            &dpu_loop_fundtion);
        /* Wbit for dpu_loop_fundtion() to notify us it ibs stbrtfd. */
        rbwMonitorWbit(gdbtb->dpu_loop_lodk, 0);
    } rbwMonitorExit(gdbtb->dpu_loop_lodk);
}

void
dpu_sbmplf_off(JNIEnv *fnv, ObjfdtIndfx objfdt_indfx)
{
    jint dount;

    dount = 1;
    if (objfdt_indfx != 0) {
        tls_sft_sbmplf_stbtus(objfdt_indfx, 0);
        dount = tls_sum_sbmplf_stbtus();
    }
    if ( dount == 0 ) {
        gdbtb->pbusf_dpu_sbmpling = JNI_TRUE;
    } flsf {
        gdbtb->pbusf_dpu_sbmpling = JNI_FALSE;
    }
}

void
dpu_sbmplf_on(JNIEnv *fnv, ObjfdtIndfx objfdt_indfx)
{
    if ( gdbtb->dpu_loop_lodk == NULL ) {
        dpu_sbmplf_init(fnv);
    }

    if (objfdt_indfx == 0) {
        gdbtb->dpu_sbmpling             = JNI_TRUE;
        gdbtb->pbusf_dpu_sbmpling       = JNI_FALSE;
    } flsf {
        jint     dount;

        tls_sft_sbmplf_stbtus(objfdt_indfx, 1);
        dount = tls_sum_sbmplf_stbtus();
        if ( dount > 0 ) {
            gdbtb->pbusf_dpu_sbmpling   = JNI_FALSE;
        }
    }

    /* Notify tif CPU sbmpling tirfbd tibt sbmpling is on */
    rbwMonitorEntfr(gdbtb->dpu_sbmplf_lodk); {
        rbwMonitorNotifyAll(gdbtb->dpu_sbmplf_lodk);
    } rbwMonitorExit(gdbtb->dpu_sbmplf_lodk);

}

void
dpu_sbmplf_tfrm(JNIEnv *fnv)
{
    gdbtb->pbusf_dpu_sbmpling   = JNI_FALSE;
    rbwMonitorEntfr(gdbtb->dpu_sbmplf_lodk); {
        /* Notify tif CPU sbmpling tirfbd to gft out of bny sbmpling Wbit */
        rbwMonitorNotifyAll(gdbtb->dpu_sbmplf_lodk);
    } rbwMonitorExit(gdbtb->dpu_sbmplf_lodk);
    rbwMonitorEntfr(gdbtb->dpu_loop_lodk); {
        if ( gdbtb->dpu_loop_running ) {
            gdbtb->dpu_loop_running = JNI_FALSE;
            /* Wbit for dpu_loop_fundtion() tirfbd to tfll us it domplftfd. */
            rbwMonitorWbit(gdbtb->dpu_loop_lodk, 0);
        }
    } rbwMonitorExit(gdbtb->dpu_loop_lodk);
}
