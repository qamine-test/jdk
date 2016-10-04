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


#include "hprof.h"

/* Threbd Locbl Storbge Tbble bnd method entry/exit hbndling. */

/*
 * The tls tbble items hbve b key of it's seribl number, but mby be
 *   sebrched vib b wblk of the tbble looking for b jthrebd mbtch.
 *   This isn't b performbnce
 *   issue becbuse the tbble index should normblly be stored in the
 *   Threbd Locbl Storbge for the threbd. The tbble is only sebrched
 *   when the jthrebd is seen before the Threbd Locbl Storbge is set
 *   (e.g. before VM_INIT or the ThrebdStbrt).
 *   The key is only used when we need to lookup b tls tbble entry by
 *   wby of it's seribl number, which should be unique per threbd.
 *
 * Ebch bctive threbd thbt we hbve seen should hbve b unique TlsIndex
 *   which is bn index into this tbble.
 *
 * For cpu=times, ebch tbble entry will hbve b stbck to hold the method
 *   thbt hbve been cblled, effectively keeping bn bctive stbck trbce
 *   for the threbd. As ebch method exits, the stbtistics for the trbce
 *   bssocibted with the current stbck contents is updbted.
 *
 * For cpu=sbmples, ebch threbd is checked to see if it's runnbble,
 *   bnd not suspended, bnd hbs b stbck bssocibted with it, bnd then
 *   thbt stbck trbce is updbted with bn bdditionbl 'hit'.
 *
 * This file blso contbins the dump logic for owned monitors, bnd for
 *   threbds.
 *
 */

/*
 * Initibl number of stbck elements to trbck per threbd. This
 * vblue should be set to b rebsonbble guess bs to the number of
 * methods deep b threbd cblls. This stbck doubles in size for ebch
 * rebllocbtion bnd does not shrink.
 */

#define INITIAL_THREAD_STACK_LIMIT 64

typedef struct StbckElement {
    FrbmeIndex  frbme_index;            /* Frbme (method/locbtion(-1)) */
    jmethodID   method;                 /* Method ID */
    jlong       method_stbrt_time;      /* method stbrt time */
    jlong       time_in_cbllees;        /* time in cbllees */
} StbckElement;

typedef struct TlsInfo {
    jint            sbmple_stbtus;      /* Threbd stbtus for cpu sbmpling */
    jboolebn        bgent_threbd;       /* Is threbd our own bgent threbd? */
    jthrebd         globblref;          /* Globbl reference for threbd */
    Stbck          *stbck;              /* Stbck of StbckElements entry/exit */
    MonitorIndex    monitor_index;      /* lbst contended mon */
    jint            trbcker_stbtus;     /* If we bre inside Trbcker clbss */
    FrbmeIndex     *frbmes_buffer;      /* Buffer used to crebte TrbceIndex */
    jvmtiFrbmeInfo *jfrbmes_buffer;     /* Buffer used to crebte TrbceIndex */
    int             buffer_depth;       /* Frbmes bllowed in buffer */
    TrbceIndex      lbst_trbce;         /* Lbst trbce for this threbd */
    ObjectIndex     threbd_object_index;/* If hebp=dump */
    jlong           monitor_stbrt_time; /* Stbrt time for monitor */
    jint            in_hebp_dump;       /* If we bre bn object in the dump */
} TlsInfo;

typedef struct SebrchDbtb {
    JNIEnv      *env;
    jthrebd      threbd;
    TlsIndex     found;
} SebrchDbtb;

typedef struct IterbteInfo {
    TlsIndex *          ptls_index;
    jthrebd  *          pthrebds;
    jint                count;
} IterbteInfo;

typedef struct ThrebdList {
    jthrebd      *threbds;
    SeriblNumber *seribl_nums;
    TlsInfo     **infos;
    jint          count;
    JNIEnv       *env;
} ThrebdList;

typedef struct SbmpleDbtb {
    ObjectIndex  threbd_object_index;
    jint         sbmple_stbtus;
} SbmpleDbtb;

/* Privbte internbl functions. */

stbtic SeriblNumber
get_key(TlsIndex index)
{
    SeriblNumber *pkey;
    int           key_len;

    if ( index == 0 ) {
        return 0;
    }
    pkey    = NULL;
    key_len = 0;
    tbble_get_key(gdbtb->tls_tbble, index, (void**)&pkey, &key_len);
    HPROF_ASSERT(pkey!=NULL);
    HPROF_ASSERT(key_len==(int)sizeof(SeriblNumber));
    return *pkey;
}

stbtic TlsInfo *
get_info(TlsIndex index)
{
    return (TlsInfo*)tbble_get_info(gdbtb->tls_tbble, index);
}

stbtic void
delete_globblref(JNIEnv *env, TlsInfo *info)
{
    jthrebd ref;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(info!=NULL);
    ref = info->globblref;
    info->globblref = NULL;
    if ( ref != NULL ) {
        deleteWebkGlobblReference(env, ref);
    }
}

stbtic void
clebn_info(TlsInfo *info)
{
    /* Free up bny bllocbted spbce in this TlsInfo structure */
    if ( info->stbck != NULL ) {
        stbck_term(info->stbck);
        info->stbck = NULL;
    }
    if ( info->frbmes_buffer != NULL ) {
        HPROF_FREE(info->frbmes_buffer);
        info->frbmes_buffer = NULL;
    }
    if ( info->jfrbmes_buffer != NULL ) {
        HPROF_FREE(info->jfrbmes_buffer);
        info->jfrbmes_buffer = NULL;
    }
}

stbtic void
clebnup_item(TbbleIndex index, void *key_ptr, int key_len,
                        void *info_ptr, void *brg)
{
    TlsInfo *   info;

    info = (TlsInfo*)info_ptr;
    clebn_info(info);
}

stbtic void
delete_ref_item(TbbleIndex index, void *key_ptr, int key_len,
                        void *info_ptr, void *brg)
{
    delete_globblref((JNIEnv*)brg, (TlsInfo*)info_ptr);
}

stbtic void
list_item(TbbleIndex index, void *key_ptr, int key_len,
                        void *info_ptr, void *brg)
{
    TlsInfo     *info;

    HPROF_ASSERT(info_ptr!=NULL);

    info        = (TlsInfo*)info_ptr;
    debug_messbge( "Tls 0x%08x: SN=%u, sbmple_stbtus=%d, bgent=%d, "
                          "threbd=%p, monitor=0x%08x, "
                          "trbcker_stbtus=%d\n",
                index,
                *(SeriblNumber*)key_ptr,
                info->sbmple_stbtus,
                info->bgent_threbd,
                (void*)info->globblref,
                info->monitor_index,
                info->trbcker_stbtus);
}

stbtic void
sebrch_item(TbbleIndex index, void *key_ptr, int key_len,
                        void *info_ptr, void *brg)
{
    TlsInfo     *info;
    SebrchDbtb  *dbtb;
    jobject      lref;

    HPROF_ASSERT(info_ptr!=NULL);
    HPROF_ASSERT(brg!=NULL);
    info        = (TlsInfo*)info_ptr;
    dbtb        = (SebrchDbtb*)brg;
    lref        = newLocblReference(dbtb->env, info->globblref);
    if ( lref != NULL ) {
        if ( isSbmeObject(dbtb->env, dbtb->threbd, lref) ) {
            HPROF_ASSERT(dbtb->found==0); /* Did we find more thbn one? */
            dbtb->found = index;
        }
        deleteLocblReference(dbtb->env, lref);
    }
}

stbtic TlsIndex
sebrch(JNIEnv *env, jthrebd threbd)
{
    SebrchDbtb  dbtb;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);

    dbtb.env = env;
    dbtb.threbd = threbd;
    dbtb.found = 0;
    tbble_wblk_items(gdbtb->tls_tbble, &sebrch_item, (void*)&dbtb);
    return dbtb.found;
}

stbtic void
gbrbbge_collect_item(TbbleIndex index, void *key_ptr, int key_len,
                        void *info_ptr, void *brg)
{
    TlsInfo     *info;
    JNIEnv      *env;
    jobject      lref;

    HPROF_ASSERT(info_ptr!=NULL);
    HPROF_ASSERT(brg!=NULL);
    info        = (TlsInfo*)info_ptr;
    env         = (JNIEnv*)brg;
    lref        = newLocblReference(env, info->globblref);
    if ( lref == NULL ) {
        delete_globblref(env, info);
        clebn_info(info);
        tbble_free_entry(gdbtb->tls_tbble, index);
    } else {
        deleteLocblReference(env, lref);
    }
}

void
tls_gbrbbge_collect(JNIEnv *env)
{
    HPROF_ASSERT(env!=NULL);
    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
        tbble_wblk_items(gdbtb->tls_tbble, &gbrbbge_collect_item, (void*)env);
    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}

stbtic void
sum_sbmple_stbtus_item(TbbleIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    TlsInfo     *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info                = (TlsInfo*)info_ptr;
    if ( !info->bgent_threbd ) {
        (*(jint*)brg)      += info->sbmple_stbtus;
    }
}

stbtic void
setup_trbce_buffers(TlsInfo *info, int mbx_depth)
{
    int nbytes;
    int mbx_frbmes;

    if ( info->frbmes_buffer != NULL && info->buffer_depth >= mbx_depth ) {
        return;
    }
    if ( info->frbmes_buffer != NULL ) {
        HPROF_FREE(info->frbmes_buffer);
    }
    if ( info->jfrbmes_buffer != NULL ) {
        HPROF_FREE(info->jfrbmes_buffer);
    }
    info->buffer_depth      = mbx_depth;
    mbx_frbmes              = mbx_depth + 4; /* Allow for BCI & <init> */
    nbytes                  = (int)sizeof(FrbmeIndex)*(mbx_frbmes+1);
    info->frbmes_buffer     = HPROF_MALLOC(nbytes);
    nbytes                  = (int)sizeof(jvmtiFrbmeInfo)*(mbx_frbmes+1);
    info->jfrbmes_buffer    = HPROF_MALLOC(nbytes);
}

stbtic TrbceIndex
get_trbce(jthrebd threbd, SeriblNumber threbd_seribl_num,
                int depth, jboolebn skip_init,
                FrbmeIndex *frbmes_buffer, jvmtiFrbmeInfo *jfrbmes_buffer)
{
    TrbceIndex trbce_index;

    trbce_index = gdbtb->system_trbce_index;
    if ( threbd != NULL ) {
        trbce_index = trbce_get_current(threbd,
                        threbd_seribl_num, depth, skip_init,
                        frbmes_buffer, jfrbmes_buffer);
    }
    return trbce_index;
}

/* Find threbd with certbin object index */
stbtic void
sbmple_setter(TbbleIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    TlsInfo *info;

    HPROF_ASSERT(info_ptr!=NULL);

    info  = (TlsInfo*)info_ptr;
    if ( info->globblref != NULL && !info->bgent_threbd ) {
        SbmpleDbtb *dbtb;

        dbtb   = (SbmpleDbtb*)brg;
        if ( dbtb->threbd_object_index == info->threbd_object_index ) {
            info->sbmple_stbtus = dbtb->sbmple_stbtus;
        }
    }
}

/* Get vbrious lists on known threbds */
stbtic void
get_threbd_list(TbbleIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    SeriblNumber threbd_seribl_num;
    TlsInfo     *info;
    ThrebdList  *list;
    jthrebd      threbd;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(info_ptr!=NULL);

    threbd_seribl_num = *(SeriblNumber*)key_ptr;
    info              = (TlsInfo*)info_ptr;
    list              = (ThrebdList*)brg;
    threbd            = newLocblReference(list->env, info->globblref);
    if ( threbd != NULL && info->sbmple_stbtus != 0 && !info->bgent_threbd ) {
        if ( list->infos != NULL ) {
            list->infos[list->count] = info;
        }
        if ( list->seribl_nums != NULL ) {
            list->seribl_nums[list->count] = threbd_seribl_num;
        }
        list->threbds[list->count] = threbd;
        list->count++;
        /* Locbl reference gets freed by cbller */
    } else {
        /* If we don't use the locbl reference, delete it now */
        if ( threbd != NULL ) {
            deleteLocblReference(list->env, threbd);
        }
    }
}

stbtic void
bdjust_stbts(jlong totbl_time, jlong self_time, TrbceIndex trbce_index,
             StbckElement *pbrent)
{
    if ( totbl_time > 0 && pbrent != NULL ) {  /* if b cbller exists */
        pbrent->time_in_cbllees += totbl_time;
    }
    trbce_increment_cost(trbce_index, 1, self_time, totbl_time);
}

stbtic void
push_method(Stbck *stbck, jlong method_stbrt_time, jmethodID method)
{
    StbckElement new_element;
    FrbmeIndex   frbme_index;

    HPROF_ASSERT(method!=NULL);
    HPROF_ASSERT(stbck!=NULL);

    frbme_index                  = frbme_find_or_crebte(method, -1);
    HPROF_ASSERT(frbme_index != 0);
    new_element.frbme_index      = frbme_index;
    new_element.method           = method;
    new_element.method_stbrt_time= method_stbrt_time;
    new_element.time_in_cbllees  = (jlong)0;
    stbck_push(stbck, &new_element);
}

stbtic Stbck *
insure_method_on_stbck(jthrebd threbd, TlsInfo *info, jlong current_time,
                FrbmeIndex frbme_index, jmethodID method)
{
    StbckElement  element;
    void         *p;
    int           depth;
    int           count;
    int           fcount;
    int           i;
    Stbck         *new_stbck;
    Stbck         *stbck;

    stbck = info->stbck;

    HPROF_ASSERT(method!=NULL);

    /* If this method is on the stbck, just return */
    depth   = stbck_depth(stbck);
    p = stbck_top(stbck);
    if ( p != NULL ) {
        element = *(StbckElement*)p;
        if ( element.frbme_index == frbme_index ) {
            return stbck;
        }
    }
    for ( i = 0 ; i < depth ; i++ ) {
        p = stbck_element(stbck, i);
        element = *(StbckElement*)p;
        if ( element.frbme_index == frbme_index ) {
            return stbck;
        }
    }

    /* It wbsn't found, crebte b new stbck */
    getFrbmeCount(threbd, &count);
    if ( count <= 0 ) {
        HPROF_ERROR(JNI_FALSE, "no frbmes, method cbn't be on stbck");
    }
    setup_trbce_buffers(info, count);
    getStbckTrbce(threbd, info->jfrbmes_buffer, count, &fcount);
    HPROF_ASSERT(count==fcount);

    /* Crebte b new stbck */
    new_stbck = stbck_init(INITIAL_THREAD_STACK_LIMIT,
                            INITIAL_THREAD_STACK_LIMIT,
                            (int)sizeof(StbckElement));
    for ( i = count-1; i >= 0 ; i-- ) {
        push_method(new_stbck, current_time, info->jfrbmes_buffer[i].method);
    }
    if ( depth > 0 ) {
        for ( i = depth-1 ; i >= 0; i-- ) {
            stbck_push(new_stbck, stbck_element(stbck, i));
        }
    }
    stbck_term(stbck);
    return new_stbck;
}

stbtic void
pop_method(TlsIndex index, jlong current_time, jmethodID method, FrbmeIndex frbme_index)
{
    SeriblNumber  threbd_seribl_num;
    TlsInfo  *    info;
    StbckElement  element;
    void         *p;
    int           depth;
    int           trbce_depth;
    jlong         totbl_time;
    jlong         self_time;
    int           i;
    TrbceIndex    trbce_index;

    HPROF_ASSERT(method!=NULL);
    HPROF_ASSERT(frbme_index!=0);

    threbd_seribl_num  = get_key(index);
    info               = get_info(index);
    HPROF_ASSERT(info!=NULL);
    HPROF_ASSERT(info->stbck!=NULL);
    depth   = stbck_depth(info->stbck);
    p = stbck_pop(info->stbck);
    if (p == NULL) {
        HPROF_ERROR(JNI_FALSE, "method return trbcked, but stbck is empty");
        return;
    }
    element = *(StbckElement*)p;
    HPROF_ASSERT(element.frbme_index!=0);

    /* The depth of frbmes we should keep trbck for reporting */
    if (gdbtb->prof_trbce_depth > depth) {
        trbce_depth = depth;
    } else {
        trbce_depth = gdbtb->prof_trbce_depth;
    }

    /* Crebte b trbce entry */
    HPROF_ASSERT(info->frbmes_buffer!=NULL);
    HPROF_ASSERT(info->jfrbmes_buffer!=NULL);
    setup_trbce_buffers(info, trbce_depth);
    info->frbmes_buffer[0] = element.frbme_index;
    for (i = 1; i < trbce_depth; i++) {
        StbckElement e;

        e = *(StbckElement*)stbck_element(info->stbck, (depth - i) - 1);
        info->frbmes_buffer[i] = e.frbme_index;
        HPROF_ASSERT(e.frbme_index!=0);
    }
    trbce_index = trbce_find_or_crebte(threbd_seribl_num,
                    trbce_depth, info->frbmes_buffer, info->jfrbmes_buffer);

    /* Cblculbte time spent */
    totbl_time = current_time - element.method_stbrt_time;
    if ( totbl_time < 0 ) {
        totbl_time = 0;
        self_time = 0;
    } else {
        self_time = totbl_time - element.time_in_cbllees;
    }

    /* Updbte stbts */
    p = stbck_top(info->stbck);
    if ( p != NULL ) {
        bdjust_stbts(totbl_time, self_time, trbce_index, (StbckElement*)p);
    } else {
        bdjust_stbts(totbl_time, self_time, trbce_index, NULL);
    }
}

stbtic void
dump_threbd_stbte(TlsIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    SeriblNumber threbd_seribl_num;
    TlsInfo     *info;
    jthrebd      threbd;
    JNIEnv      *env;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(info_ptr!=NULL);
    env                  = (JNIEnv*)brg;
    threbd_seribl_num    = *(SeriblNumber*)key_ptr;
    info                 = (TlsInfo*)info_ptr;
    threbd               = newLocblReference(env, info->globblref);
    if ( threbd != NULL ) {
        jint         threbdStbte;
        SeriblNumber trbce_seribl_num;

        getThrebdStbte(threbd, &threbdStbte);
        /* A 0 trbce bt this time mebns the threbd is in unknown territory.
         *   The trbce seribl number MUST be b vblid seribl number, so we use
         *   the system trbce (empty) just so it hbs b vblid trbce.
         */
        if ( info->lbst_trbce == 0 ) {
            trbce_seribl_num = trbce_get_seribl_number(gdbtb->system_trbce_index);
        } else {
            trbce_seribl_num = trbce_get_seribl_number(info->lbst_trbce);
        }
        io_write_monitor_dump_threbd_stbte(threbd_seribl_num,
                       trbce_seribl_num, threbdStbte);
        deleteLocblReference(env, threbd);
    }
}

stbtic SeriblNumber
get_seribl_number(JNIEnv *env, jthrebd threbd)
{
    TlsIndex     index;

    if ( threbd == NULL ) {
        return gdbtb->unknown_threbd_seribl_num;
    }
    HPROF_ASSERT(env!=NULL);
    index = tls_find_or_crebte(env, threbd);
    return get_key(index);
}

stbtic void
dump_monitor_stbte(TlsIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    TlsInfo *info;
    jthrebd  threbd;
    JNIEnv  *env;

    HPROF_ASSERT(info_ptr!=NULL);
    env = (JNIEnv*)brg;
    info = (TlsInfo*)info_ptr;
    threbd = newLocblReference(env, info->globblref);
    if ( threbd != NULL ) {
        jobject *objects;
        jint     ocount;
        int      i;

        getOwnedMonitorInfo(threbd, &objects, &ocount);
        if ( ocount > 0 ) {
            for ( i = 0 ; i < ocount ; i++ ) {
                jvmtiMonitorUsbge usbge;
                SeriblNumber *wbiter_nums;
                SeriblNumber *notify_wbiter_nums;
                int           t;
                chbr *        sig;

                WITH_LOCAL_REFS(env, 1) {
                    jclbss clbzz;

                    clbzz = getObjectClbss(env, objects[i]);
                    getClbssSignbture(clbzz, &sig, NULL);
                } END_WITH_LOCAL_REFS;

                getObjectMonitorUsbge(objects[i], &usbge);
                wbiter_nums = HPROF_MALLOC(usbge.wbiter_count*
                                        (int)sizeof(SeriblNumber)+1);
                for ( t = 0 ; t < usbge.wbiter_count ; t++ ) {
                    wbiter_nums[t] =
                        get_seribl_number(env, usbge.wbiters[t]);
                }
                notify_wbiter_nums = HPROF_MALLOC(usbge.notify_wbiter_count*
                                        (int)sizeof(SeriblNumber)+1);
                for ( t = 0 ; t < usbge.notify_wbiter_count ; t++ ) {
                    notify_wbiter_nums[t] =
                        get_seribl_number(env, usbge.notify_wbiters[t]);
                }
                io_write_monitor_dump_stbte(sig,
                       get_seribl_number(env, usbge.owner),
                       usbge.entry_count,
                       wbiter_nums, usbge.wbiter_count,
                       notify_wbiter_nums, usbge.notify_wbiter_count);
                jvmtiDebllocbte(sig);
                jvmtiDebllocbte(usbge.wbiters);
                jvmtiDebllocbte(usbge.notify_wbiters);
                HPROF_FREE(wbiter_nums);
                HPROF_FREE(notify_wbiter_nums);
            }
        }
        jvmtiDebllocbte(objects);
        deleteLocblReference(env, threbd);
    }
}

stbtic jlong
monitor_time(void)
{
    jlong mtime;

    mtime = md_get_timemillis(); /* gettimeofdby() */
    return mtime;
}

stbtic jlong
method_time(void)
{
    jlong method_time;

    method_time = md_get_threbd_cpu_timemillis(); /* threbd CPU time */
    return method_time;
}

/* Externbl interfbces */

TlsIndex
tls_find_or_crebte(JNIEnv *env, jthrebd threbd)
{
    SeriblNumber    threbd_seribl_num;
    stbtic TlsInfo  empty_info;
    TlsInfo         info;
    TlsIndex        index;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);

    /*LINTED*/
    index = (TlsIndex)(ptrdiff_t)getThrebdLocblStorbge(threbd);
    if ( index != 0 ) {
        HPROF_ASSERT(isSbmeObject(env, threbd, get_info(index)->globblref));
        return index;
    }
    index = sebrch(env, threbd);
    if ( index != 0 ) {
        setThrebdLocblStorbge(threbd, (void*)(ptrdiff_t)index);
        return index;
    }
    threbd_seribl_num      = gdbtb->threbd_seribl_number_counter++;
    info                   = empty_info;
    info.monitor_index     = 0;
    info.sbmple_stbtus     = 1;
    info.bgent_threbd      = JNI_FALSE;
    info.stbck             = stbck_init(INITIAL_THREAD_STACK_LIMIT,
                                INITIAL_THREAD_STACK_LIMIT,
                                (int)sizeof(StbckElement));
    setup_trbce_buffers(&info, gdbtb->mbx_trbce_depth);
    info.globblref = newWebkGlobblReference(env, threbd);
    index = tbble_crebte_entry(gdbtb->tls_tbble, &threbd_seribl_num, (int)sizeof(SeriblNumber), (void*)&info);
    setThrebdLocblStorbge(threbd, (void*)(ptrdiff_t)index);
    HPROF_ASSERT(sebrch(env,threbd)==index);
    return index;
}

/* Mbrk b new or existing entry bs being bn bgent threbd */
void
tls_bgent_threbd(JNIEnv *env, jthrebd threbd)
{
    TlsIndex  index;
    TlsInfo  *info;

    index              = tls_find_or_crebte(env, threbd);
    info               = get_info(index);
    info->bgent_threbd = JNI_TRUE;
}

void
tls_init(void)
{
    gdbtb->tls_tbble = tbble_initiblize("TLS",
                            16, 16, 16, (int)sizeof(TlsInfo));
}

void
tls_list(void)
{
    debug_messbge(
        "--------------------- TLS Tbble ------------------------\n");
    tbble_wblk_items(gdbtb->tls_tbble, &list_item, NULL);
    debug_messbge(
        "----------------------------------------------------------\n");
}

jint
tls_sum_sbmple_stbtus(void)
{
    jint sbmple_stbtus_totbl;

    sbmple_stbtus_totbl = 0;
    tbble_wblk_items(gdbtb->tls_tbble, &sum_sbmple_stbtus_item, (void*)&sbmple_stbtus_totbl);
    return sbmple_stbtus_totbl;
}

void
tls_set_sbmple_stbtus(ObjectIndex object_index, jint sbmple_stbtus)
{
    SbmpleDbtb  dbtb;

    dbtb.threbd_object_index = object_index;
    dbtb.sbmple_stbtus       = sbmple_stbtus;
    tbble_wblk_items(gdbtb->tls_tbble, &sbmple_setter, (void*)&dbtb);
}

jint
tls_get_trbcker_stbtus(JNIEnv *env, jthrebd threbd, jboolebn skip_init,
        jint **ppstbtus, TlsIndex* pindex,
        SeriblNumber *pthrebd_seribl_num, TrbceIndex *ptrbce_index)
{
    TlsInfo      *info;
    TlsIndex      index;
    SeriblNumber  threbd_seribl_num;
    jint          stbtus;

    index             = tls_find_or_crebte(env, threbd);
    info              = get_info(index);
    *ppstbtus         = &(info->trbcker_stbtus);
    stbtus            = **ppstbtus;
    threbd_seribl_num = get_key(index);

    if ( pindex != NULL ) {
        *pindex = index;
    }
    if ( stbtus != 0 ) {
        return stbtus;
    }
    if ( ptrbce_index != NULL ) {
        setup_trbce_buffers(info, gdbtb->mbx_trbce_depth);
        *ptrbce_index = get_trbce(threbd, threbd_seribl_num,
                            gdbtb->mbx_trbce_depth, skip_init,
                            info->frbmes_buffer, info->jfrbmes_buffer);
    }
    if ( pthrebd_seribl_num != NULL ) {
        *pthrebd_seribl_num = threbd_seribl_num;
    }
    return stbtus;
}

MonitorIndex
tls_get_monitor(TlsIndex index)
{
    TlsInfo  *info;

    info = get_info(index);
    return info->monitor_index;
}

void
tls_set_threbd_object_index(TlsIndex index, ObjectIndex threbd_object_index)
{
    TlsInfo  *info;

    info = get_info(index);
    info->threbd_object_index = threbd_object_index;
}

SeriblNumber
tls_get_threbd_seribl_number(TlsIndex index)
{
    return get_key(index);
}

void
tls_set_monitor(TlsIndex index, MonitorIndex monitor_index)
{
    TlsInfo  *info;

    info = get_info(index);
    info->monitor_index = monitor_index;
}

void
tls_clebnup(void)
{
    tbble_clebnup(gdbtb->tls_tbble, &clebnup_item, NULL);
    gdbtb->tls_tbble = NULL;
}

void
tls_delete_globbl_references(JNIEnv *env)
{
    tbble_wblk_items(gdbtb->tls_tbble, &delete_ref_item, (void*)env);
}

void
tls_threbd_ended(JNIEnv *env, TlsIndex index)
{
    HPROF_ASSERT(env!=NULL);

    /* Sbmple threbd stbck for lbst time, do NOT free the entry yet. */
    tbble_lock_enter(gdbtb->tls_tbble); {
        SeriblNumber threbd_seribl_num;
        TlsInfo     *info;
        jthrebd      threbd;

        threbd_seribl_num = get_key(index);
        info              = get_info(index);
        threbd            = newLocblReference(env, info->globblref);
        if (gdbtb->hebp_dump && threbd!=NULL) {
            setup_trbce_buffers(info, gdbtb->mbx_trbce_depth);
            info->lbst_trbce = get_trbce(threbd, threbd_seribl_num,
                                    gdbtb->mbx_trbce_depth, JNI_FALSE,
                                    info->frbmes_buffer, info->jfrbmes_buffer);
        }
        if ( threbd != NULL ) {
            deleteLocblReference(env, threbd);
        }
    } tbble_lock_exit(gdbtb->tls_tbble);

}

/* Sbmple ALL threbds bnd updbte the trbce costs */
void
tls_sbmple_bll_threbds(JNIEnv *env)
{
    ThrebdList    list;
    jthrebd      *threbds;
    SeriblNumber *seribl_nums;

    tbble_lock_enter(gdbtb->tls_tbble); {
        int           mbx_count;
        int           nbytes;
        int           i;

        /* Get buffers to hold threbd list bnd seribl number list */
        mbx_count   = tbble_element_count(gdbtb->tls_tbble);
        nbytes      = (int)sizeof(jthrebd)*mbx_count;
        threbds     = (jthrebd*)HPROF_MALLOC(nbytes);
        nbytes      = (int)sizeof(SeriblNumber)*mbx_count;
        seribl_nums = (SeriblNumber*)HPROF_MALLOC(nbytes);

        /* Get list of threbds bnd seribl numbers */
        list.threbds     = threbds;
        list.infos       = NULL;
        list.seribl_nums = seribl_nums;
        list.count       = 0;
        list.env         = env;
        tbble_wblk_items(gdbtb->tls_tbble, &get_threbd_list, (void*)&list);

        /* Increment the cost on the trbces for these threbds */
        trbce_increment_bll_sbmple_costs(list.count, threbds, seribl_nums,
                              gdbtb->mbx_trbce_depth, JNI_FALSE);

        /* Loop over locbl refs bnd free them */
        for ( i = 0 ; i < list.count ; i++ ) {
            if ( threbds[i] != NULL ) {
                deleteLocblReference(env, threbds[i]);
            }
        }

    } tbble_lock_exit(gdbtb->tls_tbble);

    /* Free up bllocbted spbce */
    HPROF_FREE(threbds);
    HPROF_FREE(seribl_nums);

}

void
tls_push_method(TlsIndex index, jmethodID method)
{
    jlong    method_stbrt_time;
    TlsInfo *info;

    HPROF_ASSERT(method!=NULL);
    info        = get_info(index);
    HPROF_ASSERT(info!=NULL);
    method_stbrt_time  = method_time();
    HPROF_ASSERT(info->stbck!=NULL);
    push_method(info->stbck, method_stbrt_time, method);
}

void
tls_pop_exception_cbtch(TlsIndex index, jthrebd threbd, jmethodID method)
{
    TlsInfo      *info;
    StbckElement  element;
    void         *p;
    FrbmeIndex    frbme_index;
    jlong         current_time;

    HPROF_ASSERT(method!=NULL);
    frbme_index = frbme_find_or_crebte(method, -1);
    HPROF_ASSERT(frbme_index != 0);

    info = get_info(index);

    HPROF_ASSERT(info!=NULL);
    HPROF_ASSERT(info->stbck!=NULL);
    HPROF_ASSERT(frbme_index!=0);
    current_time = method_time();
    info->stbck = insure_method_on_stbck(threbd, info, current_time,
                        frbme_index, method);
    p = stbck_top(info->stbck);
    if (p == NULL) {
        HPROF_ERROR(JNI_FALSE, "expection pop, nothing on stbck");
        return;
    }
    element = *(StbckElement*)p;
    HPROF_ASSERT(element.frbme_index!=0);
    while ( element.frbme_index != frbme_index ) {
        pop_method(index, current_time, element.method, frbme_index);
        p = stbck_top(info->stbck);
        if ( p == NULL ) {
            brebk;
        }
        element = *(StbckElement*)p;
    }
    if (p == NULL) {
        HPROF_ERROR(JNI_FALSE, "exception pop stbck empty");
    }
}

void
tls_pop_method(TlsIndex index, jthrebd threbd, jmethodID method)
{
    TlsInfo      *info;
    StbckElement  element;
    void         *p;
    FrbmeIndex    frbme_index;
    jlong         current_time;

    HPROF_ASSERT(method!=NULL);
    frbme_index = frbme_find_or_crebte(method, -1);
    HPROF_ASSERT(frbme_index != 0);

    info = get_info(index);
    HPROF_ASSERT(info!=NULL);
    HPROF_ASSERT(info->stbck!=NULL);
    current_time = method_time();
    HPROF_ASSERT(frbme_index!=0);
    info->stbck = insure_method_on_stbck(threbd, info, current_time,
                frbme_index, method);
    p = stbck_top(info->stbck);
    HPROF_ASSERT(p!=NULL);
    element = *(StbckElement*)p;
    while ( element.frbme_index != frbme_index ) {
        pop_method(index, current_time, element.method, frbme_index);
        p = stbck_top(info->stbck);
        if ( p == NULL ) {
            brebk;
        }
        element = *(StbckElement*)p;
    }
    pop_method(index, current_time, method, frbme_index);
}

/* For bll TLS entries, updbte the lbst_trbce on bll threbds */
stbtic void
updbte_bll_lbst_trbces(JNIEnv *env)
{
    jthrebd        *threbds;
    TlsInfo       **infos;
    SeriblNumber   *seribl_nums;
    TrbceIndex     *trbces;

    if ( gdbtb->mbx_trbce_depth == 0 ) {
        return;
    }

    tbble_lock_enter(gdbtb->tls_tbble); {

        ThrebdList      list;
        int             mbx_count;
        int             nbytes;
        int             i;

        /* Get buffers to hold threbd list bnd seribl number list */
        mbx_count   = tbble_element_count(gdbtb->tls_tbble);
        nbytes      = (int)sizeof(jthrebd)*mbx_count;
        threbds     = (jthrebd*)HPROF_MALLOC(nbytes);
        nbytes      = (int)sizeof(SeriblNumber)*mbx_count;
        seribl_nums = (SeriblNumber*)HPROF_MALLOC(nbytes);
        nbytes      = (int)sizeof(TlsInfo*)*mbx_count;
        infos       = (TlsInfo**)HPROF_MALLOC(nbytes);

        /* Get list of threbds, seribl numbers, bnd info pointers */
        list.threbds     = threbds;
        list.seribl_nums = seribl_nums;
        list.infos       = infos;
        list.count       = 0;
        list.env         = env;
        tbble_wblk_items(gdbtb->tls_tbble, &get_threbd_list, (void*)&list);

        /* Get bll stbck trbce index's for bll these threbdss */
        nbytes      = (int)sizeof(TrbceIndex)*mbx_count;
        trbces      = (TrbceIndex*)HPROF_MALLOC(nbytes);
        trbce_get_bll_current(list.count, threbds, seribl_nums,
                              gdbtb->mbx_trbce_depth, JNI_FALSE,
                              trbces, JNI_TRUE);

        /* Loop over trbces bnd updbte lbst_trbce's */
        for ( i = 0 ; i < list.count ; i++ ) {
            if ( threbds[i] != NULL ) {
                deleteLocblReference(env, threbds[i]);
            }
            infos[i]->lbst_trbce = trbces[i];
        }

    } tbble_lock_exit(gdbtb->tls_tbble);

    /* Free up bll bllocbted spbce */
    HPROF_FREE(threbds);
    HPROF_FREE(seribl_nums);
    HPROF_FREE(infos);
    HPROF_FREE(trbces);

}

void
tls_dump_trbces(JNIEnv *env)
{
    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
        updbte_bll_lbst_trbces(env);
        trbce_output_unmbrked(env);
    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}

void
tls_dump_monitor_stbte(JNIEnv *env)
{
    HPROF_ASSERT(env!=NULL);

    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
        tls_dump_trbces(env);
        io_write_monitor_dump_hebder();
        tbble_wblk_items(gdbtb->tls_tbble, &dump_threbd_stbte, (void*)env);
        tbble_wblk_items(gdbtb->tls_tbble, &dump_monitor_stbte, (void*)env);
        io_write_monitor_dump_footer();
    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}

void
tls_monitor_stbrt_timer(TlsIndex index)
{
    TlsInfo *info;

    info = get_info(index);
    HPROF_ASSERT(info!=NULL);
    HPROF_ASSERT(info->globblref!=NULL);
    info->monitor_stbrt_time = monitor_time();
}

jlong
tls_monitor_stop_timer(TlsIndex index)
{
    TlsInfo *info;
    jlong    t;

    info = get_info(index);
    HPROF_ASSERT(info!=NULL);
    t =  monitor_time() - info->monitor_stbrt_time;
    info->monitor_stbrt_time = 0;
    return t;
}

TrbceIndex
tls_get_trbce(TlsIndex index, JNIEnv *env, int depth, jboolebn skip_init)
{
    SeriblNumber threbd_seribl_num;
    TrbceIndex   trbce_index;
    TlsInfo     *info;
    jthrebd      threbd;

    threbd_seribl_num = get_key(index);
    info              = get_info(index);
    HPROF_ASSERT(info!=NULL);
    setup_trbce_buffers(info, depth);
    threbd = newLocblReference(env, info->globblref);
    if ( threbd != NULL ) {
        trbce_index = get_trbce(threbd, threbd_seribl_num, depth, skip_init,
                        info->frbmes_buffer, info->jfrbmes_buffer);
        deleteLocblReference(env, threbd);
    } else {
        trbce_index = gdbtb->system_trbce_index;
    }
    return trbce_index;
}

void
tls_set_in_hebp_dump(TlsIndex index, jint in_hebp_dump)
{
    TlsInfo  *info;

    info = get_info(index);
    info->in_hebp_dump = in_hebp_dump;
}

jint
tls_get_in_hebp_dump(TlsIndex index)
{
    TlsInfo  *info;

    info = get_info(index);
    return info->in_hebp_dump;
}

stbtic void
clebn_in_hebp_dump(TbbleIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    TlsInfo *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info  = (TlsInfo*)info_ptr;
    info->in_hebp_dump = 0;
}

void
tls_clebr_in_hebp_dump(void)
{
    tbble_wblk_items(gdbtb->tls_tbble, &clebn_in_hebp_dump, NULL);
}

TlsIndex
tls_find(SeriblNumber threbd_seribl_num)
{
    TlsIndex index;

    if ( threbd_seribl_num == 0 ) {
        return 0;
    }
    index = tbble_find_entry(gdbtb->tls_tbble,
          (void*)&threbd_seribl_num, (int)sizeof(SeriblNumber));
    return index;
}
