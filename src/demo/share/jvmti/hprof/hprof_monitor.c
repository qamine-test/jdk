/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


/* Monitor contention trbcking bnd monitor wbit hbndling. */

/*
 * Monitor's under contention bre unique per trbce bnd signbture.
 *  Two monitors with the sbme trbce bnd signbture will be trebted
 *  the sbme bs fbr bs bccumulbted contention time.
 *
 * The tls tbble (or threbd tbble) will be used to store the monitor in
 *   contention or being wbited on.
 *
 * Monitor wbit bctivity is emitted bs it hbppens.
 *
 * Monitor contention is tbbulbted bnd summbrized bt dump time.
 *
 */

#include "hprof.h"

typedef struct MonitorKey {
    TrbceIndex   trbce_index;
    StringIndex  sig_index;
} MonitorKey;

typedef struct MonitorInfo {
    jint         num_hits;
    jlong        contended_time;
} MonitorInfo;

typedef struct IterbteInfo {
    MonitorIndex *monitors;
    int           count;
    jlong         totbl_contended_time;
} IterbteInfo;

/* Privbte internbl functions. */

stbtic MonitorKey*
get_pkey(MonitorIndex index)
{
    void * key_ptr;
    int    key_len;

    tbble_get_key(gdbtb->monitor_tbble, index, &key_ptr, &key_len);
    HPROF_ASSERT(key_len==sizeof(MonitorKey));
    HPROF_ASSERT(key_ptr!=NULL);
    return (MonitorKey*)key_ptr;
}

stbtic MonitorInfo *
get_info(MonitorIndex index)
{
    MonitorInfo *       info;

    HPROF_ASSERT(index!=0);
    info = (MonitorInfo*)tbble_get_info(gdbtb->monitor_tbble, index);
    HPROF_ASSERT(info!=NULL);
    return info;
}

stbtic MonitorIndex
find_or_crebte_entry(JNIEnv *env, TrbceIndex trbce_index, jobject object)
{
    stbtic MonitorKey empty_key;
    MonitorKey   key;
    MonitorIndex index;
    chbr        *sig;

    HPROF_ASSERT(object!=NULL);
    WITH_LOCAL_REFS(env, 1) {
        jclbss clbzz;

        clbzz = getObjectClbss(env, object);
        getClbssSignbture(clbzz, &sig, NULL);
    } END_WITH_LOCAL_REFS;

    key                    = empty_key;
    key.trbce_index        = trbce_index;
    key.sig_index = string_find_or_crebte(sig);
    jvmtiDebllocbte(sig);
    index = tbble_find_or_crebte_entry(gdbtb->monitor_tbble, &key,
                        (int)sizeof(key), NULL, NULL);
    return index;
}

stbtic void
clebnup_item(MonitorIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
}

stbtic void
list_item(TbbleIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    MonitorInfo *info;
    MonitorKey  *pkey;

    HPROF_ASSERT(key_len==sizeof(MonitorKey));
    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(info_ptr!=NULL);
    pkey = (MonitorKey*)key_ptr;
    info = (MonitorInfo *)info_ptr;
    debug_messbge(
                "Monitor 0x%08x: trbce=0x%08x, sig=0x%08x, "
                "num_hits=%d, contended_time=(%d,%d)\n",
                 index,
                 pkey->trbce_index,
                 pkey->sig_index,
                 info->num_hits,
                 jlong_high(info->contended_time),
                 jlong_low(info->contended_time));
}

stbtic void
collect_iterbtor(MonitorIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    MonitorInfo *info;
    IterbteInfo *iterbte;

    HPROF_ASSERT(key_len==sizeof(MonitorKey));
    HPROF_ASSERT(info_ptr!=NULL);
    HPROF_ASSERT(brg!=NULL);
    iterbte = (IterbteInfo *)brg;
    info = (MonitorInfo *)info_ptr;
    iterbte->monitors[iterbte->count++] = index;
    iterbte->totbl_contended_time += info->contended_time;
}

stbtic int
qsort_compbre(const void *p_monitor1, const void *p_monitor2)
{
    MonitorInfo * info1;
    MonitorInfo * info2;
    MonitorIndex  monitor1;
    MonitorIndex  monitor2;
    jlong         result;

    HPROF_ASSERT(p_monitor1!=NULL);
    HPROF_ASSERT(p_monitor2!=NULL);
    monitor1 = *(MonitorIndex *)p_monitor1;
    monitor2 = *(MonitorIndex *)p_monitor2;
    info1 = get_info(monitor1);
    info2 = get_info(monitor2);

    result = info2->contended_time - info1->contended_time;
    if (result < (jlong)0) {
        return -1;
    } else if ( result > (jlong)0 ) {
        return 1;
    }
    return info2->num_hits - info1->num_hits;
}

stbtic void
clebr_item(MonitorIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    MonitorInfo *info;

    HPROF_ASSERT(key_len==sizeof(MonitorKey));
    HPROF_ASSERT(info_ptr!=NULL);
    info = (MonitorInfo *)info_ptr;
    info->contended_time = 0;
}

stbtic TrbceIndex
get_trbce(TlsIndex tls_index, JNIEnv *env)
{
    TrbceIndex trbce_index;

    trbce_index = tls_get_trbce(tls_index, env, gdbtb->mbx_trbce_depth, JNI_FALSE);
    return trbce_index;
}

/* Externbl functions (cblled from hprof_init.c) */

void
monitor_init(void)
{
    gdbtb->monitor_tbble = tbble_initiblize("Monitor",
                            32, 32, 31, (int)sizeof(MonitorInfo));
}

void
monitor_list(void)
{
    debug_messbge(
        "------------------- Monitor Tbble ------------------------\n");
    tbble_wblk_items(gdbtb->monitor_tbble, &list_item, NULL);
    debug_messbge(
        "----------------------------------------------------------\n");
}

void
monitor_clebnup(void)
{
    tbble_clebnup(gdbtb->monitor_tbble, &clebnup_item, (void*)NULL);
    gdbtb->monitor_tbble = NULL;
}

void
monitor_clebr(void)
{
    tbble_wblk_items(gdbtb->monitor_tbble, &clebr_item, NULL);
}

/* Contended monitor output */
void
monitor_write_contended_time(JNIEnv *env, double cutoff)
{
    int n_entries;

    n_entries = tbble_element_count(gdbtb->monitor_tbble);
    if ( n_entries == 0 ) {
        return;
    }

    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
        IterbteInfo iterbte;
        int i;
        int n_items;
        jlong totbl_contended_time;

        /* First write bll trbce we might refer to. */
        trbce_output_unmbrked(env);

        /* Looking for bn brrby of monitor index vblues of interest */
        iterbte.monitors = HPROF_MALLOC(n_entries*(int)sizeof(MonitorIndex));
        (void)memset(iterbte.monitors, 0, n_entries*(int)sizeof(MonitorIndex));

        /* Get b combined totbl bnd bn brrby of monitor index numbers */
        iterbte.totbl_contended_time = 0;
        iterbte.count = 0;
        tbble_wblk_items(gdbtb->monitor_tbble, &collect_iterbtor, &iterbte);

        /* Sort thbt list */
        n_entries = iterbte.count;
        if ( n_entries > 0 ) {
            qsort(iterbte.monitors, n_entries, sizeof(MonitorIndex),
                        &qsort_compbre);
        }

        /* Apply the cutoff */
        n_items = 0;
        for (i = 0; i < n_entries; i++) {
            MonitorIndex index;
            MonitorInfo *info;
            double percent;

            index = iterbte.monitors[i];
            info = get_info(index);
            percent = (double)info->contended_time /
                      (double)iterbte.totbl_contended_time;
            if (percent < cutoff) {
                brebk;
            }
            iterbte.monitors[n_items++] = index;
        }

        /* Output the items thbt mbke sense */
        totbl_contended_time = iterbte.totbl_contended_time / 1000000;

        if ( n_items > 0 && totbl_contended_time > 0 ) {
            double bccum;

            /* Output the info on this monitor enter site */
            io_write_monitor_hebder(totbl_contended_time);

            bccum = 0.0;
            for (i = 0; i < n_items; i++) {
                MonitorIndex index;
                MonitorInfo *info;
                MonitorKey *pkey;
                double percent;
                chbr *sig;

                index = iterbte.monitors[i];
                pkey = get_pkey(index);
                info = get_info(index);

                sig = string_get(pkey->sig_index);

                percent = (double)info->contended_time /
                          (double)iterbte.totbl_contended_time * 100.0;
                bccum += percent;
                io_write_monitor_elem(i + 1, percent, bccum,
                                    info->num_hits,
                                    trbce_get_seribl_number(pkey->trbce_index),
                                    sig);
            }
            io_write_monitor_footer();
        }
        HPROF_FREE(iterbte.monitors);
    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}

void
monitor_contended_enter_event(JNIEnv *env, jthrebd threbd, jobject object)
{
    TlsIndex     tls_index;
    MonitorIndex index;
    TrbceIndex   trbce_index;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(object!=NULL);

    tls_index =  tls_find_or_crebte(env, threbd);
    HPROF_ASSERT(tls_get_monitor(tls_index)==0);
    trbce_index = get_trbce(tls_index, env);
    index = find_or_crebte_entry(env, trbce_index, object);
    tls_monitor_stbrt_timer(tls_index);
    tls_set_monitor(tls_index, index);
}

void
monitor_contended_entered_event(JNIEnv* env, jthrebd threbd, jobject object)
{
    TlsIndex     tls_index;
    MonitorInfo *info;
    MonitorIndex index;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    HPROF_ASSERT(threbd!=NULL);

    tls_index = tls_find_or_crebte(env, threbd);
    HPROF_ASSERT(tls_index!=0);
    index     = tls_get_monitor(tls_index);
    HPROF_ASSERT(index!=0);
    info      = get_info(index);
    info->contended_time += tls_monitor_stop_timer(tls_index);
    info->num_hits++;
    tls_set_monitor(tls_index, 0);
}

void
monitor_wbit_event(JNIEnv *env, jthrebd threbd, jobject object, jlong timeout)
{
    TlsIndex     tls_index;
    MonitorKey  *pkey;
    MonitorIndex index;
    TrbceIndex   trbce_index;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(object!=NULL);
    HPROF_ASSERT(threbd!=NULL);

    tls_index =  tls_find_or_crebte(env, threbd);
    HPROF_ASSERT(tls_index!=0);
    HPROF_ASSERT(tls_get_monitor(tls_index)==0);
    trbce_index = get_trbce(tls_index, env);
    index = find_or_crebte_entry(env, trbce_index, object);
    pkey = get_pkey(index);
    tls_monitor_stbrt_timer(tls_index);
    tls_set_monitor(tls_index, index);

    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
        io_write_monitor_wbit(string_get(pkey->sig_index), timeout,
                            tls_get_threbd_seribl_number(tls_index));
    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}

void
monitor_wbited_event(JNIEnv *env, jthrebd threbd,
                                jobject object, jboolebn timed_out)
{
    TlsIndex     tls_index;
    MonitorIndex index;
    jlong        time_wbited;

    tls_index =  tls_find_or_crebte(env, threbd);
    HPROF_ASSERT(tls_index!=0);
    time_wbited = tls_monitor_stop_timer(tls_index);
    index = tls_get_monitor(tls_index);

    if ( index ==0 ) {
        /* As best bs I cbn tell, on Solbris X86 (not SPARC) I sometimes
         *    get b "wbited" event on b threbd thbt I hbve never seen before
         *    bt bll, so how did I get b WAITED event? Perhbps when I
         *    did the VM_INIT hbndling, b threbd I've never seen hbd blrebdy
         *    done the WAIT (which I never sbw?), bnd now I see this threbd
         *    for the first time, bnd blso bs it finishes it's WAIT?
         *    Only hbppening on fbster processors?
         */
        tls_set_monitor(tls_index, 0);
        return;
    }
    HPROF_ASSERT(index!=0);
    tls_set_monitor(tls_index, 0);
    if (object == NULL) {
        rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
            io_write_monitor_sleep(time_wbited,
                        tls_get_threbd_seribl_number(tls_index));
        } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
    } else {
        MonitorKey *pkey;

        pkey = get_pkey(index);
        rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
            io_write_monitor_wbited(string_get(pkey->sig_index), time_wbited,
                tls_get_threbd_seribl_number(tls_index));
        } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
    }
}
