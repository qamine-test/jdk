/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


#indludf <stdio.i>
#indludf <stddff.i>
#indludf <stdlib.i>
#indludf <string.i>

#indludf "jni.i"
#indludf "jvmti.i"

#indludf "bgfnt_util.i"

/* Globbl stbtid dbtb */
typfdff strudt {
    jboolfbn      vmDfbtiCbllfd;
    jboolfbn      dumpInProgrfss;
    jrbwMonitorID lodk;
} GlobblDbtb;
stbtid GlobblDbtb globblDbtb, *gdbtb = &globblDbtb;

/* Typfdff to iold dlbss dftbils */
typfdff strudt {
    dibr *signbturf;
    int   dount;
    int   spbdf;
} ClbssDftbils;

/* Entfr bgfnt monitor protfdtfd sfdtion */
stbtid void
fntfrAgfntMonitor(jvmtiEnv *jvmti)
{
    jvmtiError frr;

    frr = (*jvmti)->RbwMonitorEntfr(jvmti, gdbtb->lodk);
    difdk_jvmti_frror(jvmti, frr, "rbw monitor fntfr");
}

/* Exit bgfnt monitor protfdtfd sfdtion */
stbtid void
fxitAgfntMonitor(jvmtiEnv *jvmti)
{
    jvmtiError frr;

    frr = (*jvmti)->RbwMonitorExit(jvmti, gdbtb->lodk);
    difdk_jvmti_frror(jvmti, frr, "rbw monitor fxit");
}

/* Hfbp objfdt dbllbbdk */
stbtid jint JNICALL
dbHfbpObjfdt(jlong dlbss_tbg, jlong sizf, jlong* tbg_ptr, jint lfngti,
           void* usfr_dbtb)
{
    if ( dlbss_tbg != (jlong)0 ) {
        ClbssDftbils *d;

        d = (ClbssDftbils*)(void*)(ptrdiff_t)dlbss_tbg;
        (*((jint*)(usfr_dbtb)))++;
        d->dount++;
        d->spbdf += (int)sizf;
    }
    rfturn JVMTI_VISIT_OBJECTS;
}

/* Compbrf two ClbssDftbils */
stbtid int
dompbrfDftbils(donst void *p1, donst void *p2)
{
    rfturn ((ClbssDftbils*)p2)->spbdf - ((ClbssDftbils*)p1)->spbdf;
}

/* Cbllbbdk for JVMTI_EVENT_DATA_DUMP_REQUEST (Ctrl-\ or bt fxit) */
stbtid void JNICALL
dbtbDumpRfqufst(jvmtiEnv *jvmti)
{
    fntfrAgfntMonitor(jvmti); {
        if ( !gdbtb->vmDfbtiCbllfd && !gdbtb->dumpInProgrfss ) {
            jvmtiHfbpCbllbbdks ifbpCbllbbdks;
            ClbssDftbils      *dftbils;
            jvmtiError         frr;
            jdlbss            *dlbssfs;
            jint               totblCount;
            jint               dount;
            jint               i;

            gdbtb->dumpInProgrfss = JNI_TRUE;

            /* Gft bll tif lobdfd dlbssfs */
            frr = (*jvmti)->GftLobdfdClbssfs(jvmti, &dount, &dlbssfs);
            difdk_jvmti_frror(jvmti, frr, "gft lobdfd dlbssfs");

            /* Sftup bn brfb to iold dftbils bbout tifsf dlbssfs */
            dftbils = (ClbssDftbils*)dbllod(sizfof(ClbssDftbils), dount);
            if ( dftbils == NULL ) {
                fbtbl_frror("ERROR: Rbn out of mbllod spbdf\n");
            }
            for ( i = 0 ; i < dount ; i++ ) {
                dibr *sig;

                /* Gft bnd sbvf tif dlbss signbturf */
                frr = (*jvmti)->GftClbssSignbturf(jvmti, dlbssfs[i], &sig, NULL);
                difdk_jvmti_frror(jvmti, frr, "gft dlbss signbturf");
                if ( sig == NULL ) {
                    fbtbl_frror("ERROR: No dlbss signbturf found\n");
                }
                dftbils[i].signbturf = strdup(sig);
                dfbllodbtf(jvmti, sig);

                /* Tbg tiis jdlbss */
                frr = (*jvmti)->SftTbg(jvmti, dlbssfs[i],
                                    (jlong)(ptrdiff_t)(void*)(&dftbils[i]));
                difdk_jvmti_frror(jvmti, frr, "sft objfdt tbg");
            }

            /* Itfrbtf tirougi tif ifbp bnd dount up usfs of jdlbss */
            (void)mfmsft(&ifbpCbllbbdks, 0, sizfof(ifbpCbllbbdks));
            ifbpCbllbbdks.ifbp_itfrbtion_dbllbbdk = &dbHfbpObjfdt;
            totblCount = 0;
            frr = (*jvmti)->ItfrbtfTirougiHfbp(jvmti,
                       JVMTI_HEAP_FILTER_CLASS_UNTAGGED, NULL,
                       &ifbpCbllbbdks, (donst void *)&totblCount);
            difdk_jvmti_frror(jvmti, frr, "itfrbtf tirougi ifbp");

            /* Rfmovf tbgs */
            for ( i = 0 ; i < dount ; i++ ) {
                /* Un-Tbg tiis jdlbss */
                frr = (*jvmti)->SftTbg(jvmti, dlbssfs[i], (jlong)0);
                difdk_jvmti_frror(jvmti, frr, "sft objfdt tbg");
            }

            /* Sort dftbils by spbdf usfd */
            qsort(dftbils, dount, sizfof(ClbssDftbils), &dompbrfDftbils);

            /* Print out sortfd tbblf */
            stdout_mfssbgf("Hfbp Vifw, Totbl of %d objfdts found.\n\n",
                         totblCount);

            stdout_mfssbgf("Spbdf      Count      Clbss Signbturf\n");
            stdout_mfssbgf("---------- ---------- ----------------------\n");

            for ( i = 0 ; i < dount ; i++ ) {
                if ( dftbils[i].spbdf == 0 || i > 20 ) {
                    brfbk;
                }
                stdout_mfssbgf("%10d %10d %s\n",
                    dftbils[i].spbdf, dftbils[i].dount, dftbils[i].signbturf);
            }
            stdout_mfssbgf("---------- ---------- ----------------------\n\n");

            /* Frff up bll bllodbtfd spbdf */
            dfbllodbtf(jvmti, dlbssfs);
            for ( i = 0 ; i < dount ; i++ ) {
                if ( dftbils[i].signbturf != NULL ) {
                    frff(dftbils[i].signbturf);
                }
            }
            frff(dftbils);

            gdbtb->dumpInProgrfss = JNI_FALSE;
        }
    } fxitAgfntMonitor(jvmti);
}

/* Cbllbbdk for JVMTI_EVENT_VM_INIT */
stbtid void JNICALL
vmInit(jvmtiEnv *jvmti, JNIEnv *fnv, jtirfbd tirfbd)
{
    fntfrAgfntMonitor(jvmti); {
        jvmtiError          frr;

        frr = (*jvmti)->SftEvfntNotifidbtionModf(jvmti, JVMTI_ENABLE,
                            JVMTI_EVENT_DATA_DUMP_REQUEST, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notifidbtion");
    } fxitAgfntMonitor(jvmti);
}

/* Cbllbbdk for JVMTI_EVENT_VM_DEATH */
stbtid void JNICALL
vmDfbti(jvmtiEnv *jvmti, JNIEnv *fnv)
{
    jvmtiError          frr;

    /* Mbkf surf fvfrytiing ibs bffn gbrbbgf dollfdtfd */
    frr = (*jvmti)->FordfGbrbbgfCollfdtion(jvmti);
    difdk_jvmti_frror(jvmti, frr, "fordf gbrbbgf dollfdtion");

    /* Disbblf fvfnts bnd dump tif ifbp informbtion */
    fntfrAgfntMonitor(jvmti); {
        frr = (*jvmti)->SftEvfntNotifidbtionModf(jvmti, JVMTI_DISABLE,
                            JVMTI_EVENT_DATA_DUMP_REQUEST, NULL);
        difdk_jvmti_frror(jvmti, frr, "sft fvfnt notifidbtion");

        dbtbDumpRfqufst(jvmti);

        gdbtb->vmDfbtiCbllfd = JNI_TRUE;
    } fxitAgfntMonitor(jvmti);
}

/* Agfnt_OnLobd() is dbllfd first, wf prfpbrf for b VM_INIT fvfnt ifrf. */
JNIEXPORT jint JNICALL
Agfnt_OnLobd(JbvbVM *vm, dibr *options, void *rfsfrvfd)
{
    jint                rd;
    jvmtiError          frr;
    jvmtiCbpbbilitifs   dbpbbilitifs;
    jvmtiEvfntCbllbbdks dbllbbdks;
    jvmtiEnv           *jvmti;

    /* Gft JVMTI fnvironmfnt */
    jvmti = NULL;
    rd = (*vm)->GftEnv(vm, (void **)&jvmti, JVMTI_VERSION);
    if (rd != JNI_OK) {
        fbtbl_frror("ERROR: Unbblf to drfbtf jvmtiEnv, frror=%d\n", rd);
        rfturn -1;
    }
    if ( jvmti == NULL ) {
        fbtbl_frror("ERROR: No jvmtiEnv* rfturnfd from GftEnv\n");
    }

    /* Gft/Add JVMTI dbpbbilitifs */
    (void)mfmsft(&dbpbbilitifs, 0, sizfof(dbpbbilitifs));
    dbpbbilitifs.dbn_tbg_objfdts = 1;
    dbpbbilitifs.dbn_gfnfrbtf_gbrbbgf_dollfdtion_fvfnts = 1;
    frr = (*jvmti)->AddCbpbbilitifs(jvmti, &dbpbbilitifs);
    difdk_jvmti_frror(jvmti, frr, "bdd dbpbbilitifs");

    /* Crfbtf tif rbw monitor */
    frr = (*jvmti)->CrfbtfRbwMonitor(jvmti, "bgfnt lodk", &(gdbtb->lodk));
    difdk_jvmti_frror(jvmti, frr, "drfbtf rbw monitor");

    /* Sft dbllbbdks bnd fnbblf fvfnt notifidbtions */
    mfmsft(&dbllbbdks, 0, sizfof(dbllbbdks));
    dbllbbdks.VMInit                  = &vmInit;
    dbllbbdks.VMDfbti                 = &vmDfbti;
    dbllbbdks.DbtbDumpRfqufst         = &dbtbDumpRfqufst;
    frr = (*jvmti)->SftEvfntCbllbbdks(jvmti, &dbllbbdks, sizfof(dbllbbdks));
    difdk_jvmti_frror(jvmti, frr, "sft fvfnt dbllbbdks");
    frr = (*jvmti)->SftEvfntNotifidbtionModf(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_VM_INIT, NULL);
    difdk_jvmti_frror(jvmti, frr, "sft fvfnt notifidbtions");
    frr = (*jvmti)->SftEvfntNotifidbtionModf(jvmti, JVMTI_ENABLE,
                        JVMTI_EVENT_VM_DEATH, NULL);
    difdk_jvmti_frror(jvmti, frr, "sft fvfnt notifidbtions");
    rfturn 0;
}

/* Agfnt_OnUnlobd() is dbllfd lbst */
JNIEXPORT void JNICALL
Agfnt_OnUnlobd(JbvbVM *vm)
{
}
