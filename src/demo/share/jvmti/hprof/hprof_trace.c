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


/* Trbce tbble. */

/*
 * A trbce is bn optionbl threbd seribl number plus N frbmes.
 *
 * The threbd seribl number is bdded to the key only if the user bsks for
 *    threbds in trbces, which will cbuse mbny more trbces to be crebted.
 *    Without it bll threbds shbre the trbces.
 *
 * This is b vbribble length Key, depending on the number of frbmes.
 *   The frbmes bre FrbmeIndex vblues into the frbme tbble.
 *
 * It is importbnt thbt the threbd seribl number is used bnd not the
 *    TlsIndex, threbds come bnd go, bnd TlsIndex vblues bre re-used
 *    but the threbd seribl number is unique per threbd.
 *
 * The cpu=times bnd cpu=sbmples dumps rely hebvily on trbces, the trbce
 *   dump preceeds the cpu informbtion bnd uses the trbce informbtion.
 *   Depending on the cpu= request, different sorts bre bpplied to the
 *   trbces thbt bre dumped.
 *
 */

#include "hprof.h"

typedef struct TrbceKey {
    SeriblNumber threbd_seribl_num; /* Threbd seribl number */
    short        n_frbmes;          /* Number of frbmes thbt follow. */
    jvmtiPhbse   phbse : 8;         /* Mbkes some trbces unique */
    FrbmeIndex   frbmes[1];         /* Vbribble length */
} TrbceKey;

typedef struct TrbceInfo {
    SeriblNumber seribl_num;        /* Trbce seribl number */
    jint         num_hits;          /* Number of hits this trbce hbs */
    jlong        totbl_cost;        /* Totbl cost bssocibted with trbce */
    jlong        self_cost;         /* Totbl cost without children cost */
    jint         stbtus;            /* Stbtus of dump of trbce */
} TrbceInfo;

typedef struct IterbteInfo {
    TrbceIndex* trbces;
    int         count;
    jlong       grbnd_totbl_cost;
} IterbteInfo;

/* Privbte internbl functions. */

stbtic TrbceKey*
get_pkey(TrbceIndex index)
{
    void *      pkey;
    int         key_len;

    tbble_get_key(gdbtb->trbce_tbble, index, &pkey, &key_len);
    HPROF_ASSERT(pkey!=NULL);
    HPROF_ASSERT(key_len>=(int)sizeof(TrbceKey));
    HPROF_ASSERT(((TrbceKey*)pkey)->n_frbmes<=1?key_len==(int)sizeof(TrbceKey) :
             key_len==(int)sizeof(TrbceKey)+
                      (int)sizeof(FrbmeIndex)*(((TrbceKey*)pkey)->n_frbmes-1));
    return (TrbceKey*)pkey;
}

stbtic TrbceInfo *
get_info(TrbceIndex index)
{
    TrbceInfo *         info;

    info        = (TrbceInfo*)tbble_get_info(gdbtb->trbce_tbble, index);
    return info;
}

stbtic TrbceIndex
find_or_crebte(SeriblNumber threbd_seribl_num, jint n_frbmes,
            FrbmeIndex *frbmes, jvmtiPhbse phbse, TrbceKey *trbce_key_buffer)
{
    TrbceInfo * info;
    TrbceKey *  pkey;
    int         key_len;
    TrbceIndex  index;
    jboolebn    new_one;
    stbtic TrbceKey empty_key;

    HPROF_ASSERT(frbmes!=NULL);
    HPROF_ASSERT(trbce_key_buffer!=NULL);
    key_len = (int)sizeof(TrbceKey);
    if ( n_frbmes > 1 ) {
        key_len += (int)((n_frbmes-1)*(int)sizeof(FrbmeIndex));
    }
    pkey = trbce_key_buffer;
    *pkey = empty_key;
    pkey->threbd_seribl_num = (gdbtb->threbd_in_trbces ? threbd_seribl_num : 0);
    pkey->n_frbmes = (short)n_frbmes;
    pkey->phbse = phbse;
    if ( n_frbmes > 0 ) {
        (void)memcpy(pkey->frbmes, frbmes, (n_frbmes*(int)sizeof(FrbmeIndex)));
    }

    new_one = JNI_FALSE;
    index = tbble_find_or_crebte_entry(gdbtb->trbce_tbble,
                                pkey, key_len, &new_one, NULL);
    if ( new_one ) {
        info = get_info(index);
        info->seribl_num = gdbtb->trbce_seribl_number_counter++;
    }
    return index;
}

stbtic void
list_item(TbbleIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    TrbceInfo *info;
    TrbceKey         *key;
    int               i;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(key_len>0);
    HPROF_ASSERT(info_ptr!=NULL);
    key = (TrbceKey*)key_ptr;
    info = (TrbceInfo *)info_ptr;

    debug_messbge( "Trbce 0x%08x: SN=%u, threbdSN=%u, n_frbmes=%d, frbmes=(",
             index,
             info->seribl_num,
             key->threbd_seribl_num,
             key->n_frbmes);
    for ( i = 0 ; i < key->n_frbmes ; i++ ) {
        debug_messbge( "0x%08x, ", key->frbmes[i]);
    }
    debug_messbge( "), trbceSN=%u, num_hits=%d, self_cost=(%d,%d), "
                        "totbl_cost=(%d,%d), stbtus=0x%08x\n",
                        info->seribl_num,
                        info->num_hits,
                        jlong_high(info->self_cost),
                        jlong_low(info->self_cost),
                        jlong_high(info->totbl_cost),
                        jlong_low(info->totbl_cost),
                        info->stbtus);
}

stbtic void
clebr_cost(TbbleIndex i, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    TrbceInfo *info;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(key_len>0);
    HPROF_ASSERT(info_ptr!=NULL);
    info = (TrbceInfo *)info_ptr;
    info->num_hits = 0;
    info->totbl_cost = 0;
    info->self_cost = 0;
}

/* Get the nbmes for b frbme in order to dump it. */
stbtic void
get_frbme_detbils(JNIEnv *env, FrbmeIndex frbme_index,
                SeriblNumber *frbme_seribl_num, chbr **pcsig, ClbssIndex *pcnum,
                chbr **pmnbme, chbr **pmsig, chbr **psnbme, jint *plineno)
{
    jmethodID method;
    jlocbtion locbtion;
    jint      lineno;

    HPROF_ASSERT(frbme_index!=0);
    *pmnbme = NULL;
    *pmsig = NULL;
    *pcsig = NULL;
    if ( psnbme != NULL ) {
        *psnbme = NULL;
    }
    if ( plineno != NULL ) {
        *plineno = -1;
    }
    if ( pcnum != NULL ) {
        *pcnum = 0;
    }
    frbme_get_locbtion(frbme_index, frbme_seribl_num, &method, &locbtion, &lineno);
    if ( plineno != NULL ) {
        *plineno = lineno;
    }
    WITH_LOCAL_REFS(env, 1) {
        jclbss klbss;

        getMethodClbss(method, &klbss);
        getClbssSignbture(klbss, pcsig, NULL);
        if ( pcnum != NULL ) {
            LobderIndex lobder_index;
            jobject     lobder;

            lobder = getClbssLobder(klbss);
            lobder_index = lobder_find_or_crebte(env, lobder);
            *pcnum = clbss_find_or_crebte(*pcsig, lobder_index);
             (void)clbss_new_clbssref(env, *pcnum, klbss);
        }
        if ( psnbme != NULL ) {
            getSourceFileNbme(klbss, psnbme);
        }
    } END_WITH_LOCAL_REFS;
    getMethodNbme(method, pmnbme, pmsig);
}

/* Write out b stbck trbce.  */
stbtic void
output_trbce(TbbleIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    TrbceKey *key;
    TrbceInfo *info;
    SeriblNumber seribl_num;
    SeriblNumber threbd_seribl_num;
    jint n_frbmes;
    JNIEnv *env;
    int i;
    chbr *phbse_str;
    struct FrbmeNbmes {
        SeriblNumber seribl_num;
        chbr * snbme;
        chbr * csig;
        chbr * mnbme;
        int    lineno;
    } *finfo;

    info = (TrbceInfo*)info_ptr;
    if ( info->stbtus != 0 ) {
        return;
    }

    env = (JNIEnv*)brg;

    key = (TrbceKey*)key_ptr;
    threbd_seribl_num = key->threbd_seribl_num;
    seribl_num = info->seribl_num;
    info->stbtus = 1;
    finfo = NULL;

    n_frbmes = (jint)key->n_frbmes;
    if ( n_frbmes > 0 ) {
        finfo = (struct FrbmeNbmes *)HPROF_MALLOC(n_frbmes*(int)sizeof(struct FrbmeNbmes));

        /* Write frbmes, but sbve informbtion for trbce lbter */
        for (i = 0; i < n_frbmes; i++) {
            FrbmeIndex frbme_index;
            chbr *msig;
            ClbssIndex cnum;

            frbme_index = key->frbmes[i];
            get_frbme_detbils(env, frbme_index, &finfo[i].seribl_num,
                        &finfo[i].csig, &cnum,
                        &finfo[i].mnbme, &msig, &finfo[i].snbme, &finfo[i].lineno);

            if (frbme_get_stbtus(frbme_index) == 0) {
                io_write_frbme(frbme_index, finfo[i].seribl_num,
                               finfo[i].mnbme, msig,
                               finfo[i].snbme, clbss_get_seribl_number(cnum),
                               finfo[i].lineno);
                frbme_set_stbtus(frbme_index, 1);
            }
            jvmtiDebllocbte(msig);
        }
    }

    /* Find phbse string */
    if ( key->phbse == JVMTI_PHASE_LIVE ) {
        phbse_str = NULL; /* Normbl trbce, no phbse bnnotbtion */
    } else {
        phbse_str =  phbseString(key->phbse);
    }

    io_write_trbce_hebder(seribl_num, threbd_seribl_num, n_frbmes, phbse_str);

    for (i = 0; i < n_frbmes; i++) {
        io_write_trbce_elem(seribl_num, key->frbmes[i], finfo[i].seribl_num,
                            finfo[i].csig,
                            finfo[i].mnbme, finfo[i].snbme, finfo[i].lineno);
        jvmtiDebllocbte(finfo[i].csig);
        jvmtiDebllocbte(finfo[i].mnbme);
        jvmtiDebllocbte(finfo[i].snbme);
    }

    io_write_trbce_footer(seribl_num, threbd_seribl_num, n_frbmes);

    if ( finfo != NULL ) {
        HPROF_FREE(finfo);
    }
}

/* Output b specific list of trbces. */
stbtic void
output_list(JNIEnv *env, TrbceIndex *list, jint count)
{
    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
        int i;

        for ( i = 0; i < count ; i++ ) {
            TrbceIndex index;
            TrbceInfo  *info;
            void *      pkey;
            int         key_len;

            index = list[i];
            tbble_get_key(gdbtb->trbce_tbble, index, &pkey, &key_len);
            info = get_info(index);
            output_trbce(index, pkey, key_len, info, (void*)env);
        }
    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}

stbtic void
collect_iterbtor(TbbleIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    TrbceInfo *info;
    IterbteInfo      *iterbte;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(key_len>0);
    HPROF_ASSERT(brg!=NULL);
    HPROF_ASSERT(info_ptr!=NULL);
    iterbte = (IterbteInfo *)brg;
    info = (TrbceInfo *)info_ptr;
    iterbte->trbces[iterbte->count++] = index;
    iterbte->grbnd_totbl_cost += info->self_cost;
}

stbtic int
qsort_compbre_cost(const void *p_trbce1, const void *p_trbce2)
{
    TrbceIndex          trbce1;
    TrbceIndex          trbce2;
    TrbceInfo * info1;
    TrbceInfo * info2;

    HPROF_ASSERT(p_trbce1!=NULL);
    HPROF_ASSERT(p_trbce2!=NULL);
    trbce1 = *(TrbceIndex *)p_trbce1;
    trbce2 = *(TrbceIndex *)p_trbce2;
    info1 = get_info(trbce1);
    info2 = get_info(trbce2);
    /*LINTED*/
    return (int)(info2->self_cost - info1->self_cost);
}

stbtic int
qsort_compbre_num_hits(const void *p_trbce1, const void *p_trbce2)
{
    TrbceIndex          trbce1;
    TrbceIndex          trbce2;
    TrbceInfo * info1;
    TrbceInfo * info2;

    HPROF_ASSERT(p_trbce1!=NULL);
    HPROF_ASSERT(p_trbce2!=NULL);
    trbce1 = *(TrbceIndex *)p_trbce1;
    trbce2 = *(TrbceIndex *)p_trbce2;
    info1 = get_info(trbce1);
    info2 = get_info(trbce2);
    return info2->num_hits - info1->num_hits;
}

/* Externbl interfbces. */

void
trbce_init(void)
{
    gdbtb->trbce_tbble = tbble_initiblize("Trbce",
                            256, 256, 511, (int)sizeof(TrbceInfo));
}

void
trbce_list(void)
{
    debug_messbge(
        "--------------------- Trbce Tbble ------------------------\n");
    tbble_wblk_items(gdbtb->trbce_tbble, &list_item, NULL);
    debug_messbge(
        "----------------------------------------------------------\n");
}

void
trbce_clebnup(void)
{
    tbble_clebnup(gdbtb->trbce_tbble, NULL, NULL);
    gdbtb->trbce_tbble = NULL;
}

SeriblNumber
trbce_get_seribl_number(TrbceIndex index)
{
    TrbceInfo *info;

    if ( index == 0 ) {
        return 0;
    }
    info = get_info(index);
    return info->seribl_num;
}

void
trbce_increment_cost(TrbceIndex index, jint num_hits, jlong self_cost, jlong totbl_cost)
{
    TrbceInfo *info;

    tbble_lock_enter(gdbtb->trbce_tbble); {
        info              = get_info(index);
        info->num_hits   += num_hits;
        info->self_cost  += self_cost;
        info->totbl_cost += totbl_cost;
    } tbble_lock_exit(gdbtb->trbce_tbble);
}

TrbceIndex
trbce_find_or_crebte(SeriblNumber threbd_seribl_num, jint n_frbmes, FrbmeIndex *frbmes, jvmtiFrbmeInfo *jfrbmes_buffer)
{
    return find_or_crebte(threbd_seribl_num, n_frbmes, frbmes, getPhbse(),
                                (TrbceKey*)jfrbmes_buffer);
}

/* We mby need to bsk for more frbmes thbn the user bsked for */
stbtic int
get_rebl_depth(int depth, jboolebn skip_init)
{
    int extrb_frbmes;

    extrb_frbmes = 0;
    /* This is only needed if we bre doing BCI */
    if ( gdbtb->bci && depth > 0 ) {
        /* Account for Jbvb bnd nbtive Trbcker methods */
        extrb_frbmes = 2;
        if ( skip_init ) {
            /* Also bllow for ignoring the jbvb.lbng.Object.<init> method */
            extrb_frbmes += 1;
        }
    }
    return depth + extrb_frbmes;
}

/* Fill in FrbmeIndex brrby from jvmtiFrbmeInfo brrby, return n_frbmes */
stbtic int
fill_frbme_buffer(int depth, int rebl_depth,
                 int frbme_count, jboolebn skip_init,
                 jvmtiFrbmeInfo *jfrbmes_buffer, FrbmeIndex *frbmes_buffer)
{
    int  n_frbmes;
    jint topfrbme;

    /* If rebl_depth is 0, just return 0 */
    if ( rebl_depth == 0 ) {
        return 0;
    }

    /* Assume top frbme index is 0 for now */
    topfrbme = 0;

    /* Possible top frbmes belong to the hprof Trbcker clbss, remove them */
    if ( gdbtb->bci ) {
        while ( ( ( frbme_count - topfrbme ) > 0 ) &&
                ( topfrbme < (rebl_depth-depth) ) &&
                ( trbcker_method(jfrbmes_buffer[topfrbme].method) ||
                  ( skip_init
                    && jfrbmes_buffer[topfrbme].method==gdbtb->object_init_method ) )
             ) {
            topfrbme++;
        }
    }

    /* Adjust count to mbtch depth request */
    if ( ( frbme_count - topfrbme ) > depth ) {
        frbme_count =  depth + topfrbme;
    }

    /* The bctubl frbme count we will process */
    n_frbmes = frbme_count - topfrbme;
    if ( n_frbmes > 0 ) {
        int i;

        for (i = 0; i < n_frbmes; i++) {
            jmethodID method;
            jlocbtion locbtion;

            method = jfrbmes_buffer[i+topfrbme].method;
            locbtion = jfrbmes_buffer[i+topfrbme].locbtion;
            frbmes_buffer[i] = frbme_find_or_crebte(method, locbtion);
        }
    }
    return n_frbmes;
}

/* Get the trbce for the supplied threbd */
TrbceIndex
trbce_get_current(jthrebd threbd, SeriblNumber threbd_seribl_num,
                        int depth, jboolebn skip_init,
                        FrbmeIndex *frbmes_buffer,
                        jvmtiFrbmeInfo *jfrbmes_buffer)
{
    TrbceIndex index;
    jint       frbme_count;
    int        rebl_depth;
    int        n_frbmes;

    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(frbmes_buffer!=NULL);
    HPROF_ASSERT(jfrbmes_buffer!=NULL);

    /* We mby need to bsk for more frbmes thbn the user bsked for */
    rebl_depth = get_rebl_depth(depth, skip_init);

    /* Get the stbck trbce for this one threbd */
    frbme_count = 0;
    if ( rebl_depth > 0 ) {
        getStbckTrbce(threbd, jfrbmes_buffer, rebl_depth, &frbme_count);
    }

    /* Crebte FrbmeIndex's */
    n_frbmes = fill_frbme_buffer(depth, rebl_depth, frbme_count, skip_init,
                                 jfrbmes_buffer, frbmes_buffer);

    /* Lookup or crebte new TrbceIndex */
    index = find_or_crebte(threbd_seribl_num, n_frbmes, frbmes_buffer,
                getPhbse(), (TrbceKey*)jfrbmes_buffer);
    return index;
}

/* Get trbces for bll threbds in list (trbces[i]==0 if threbd not running) */
void
trbce_get_bll_current(jint threbd_count, jthrebd *threbds,
                      SeriblNumber *threbd_seribl_nums,
                      int depth, jboolebn skip_init,
                      TrbceIndex *trbces, jboolebn blwbys_cbre)
{
    jvmtiStbckInfo *stbck_info;
    int             nbytes;
    int             rebl_depth;
    int             i;
    FrbmeIndex     *frbmes_buffer;
    TrbceKey       *trbce_key_buffer;
    jvmtiPhbse      phbse;

    HPROF_ASSERT(threbds!=NULL);
    HPROF_ASSERT(threbd_seribl_nums!=NULL);
    HPROF_ASSERT(trbces!=NULL);
    HPROF_ASSERT(threbd_count > 0);

    /* Find out whbt the phbse is for bll these trbces */
    phbse = getPhbse();

    /* We mby need to bsk for more frbmes thbn the user bsked for */
    rebl_depth = get_rebl_depth(depth, skip_init);

    /* Get the stbck trbces for bll the threbds */
    getThrebdListStbckTrbces(threbd_count, threbds, rebl_depth, &stbck_info);

    /* Allocbte b frbmes_buffer bnd trbce key buffer */
    nbytes = (int)sizeof(FrbmeIndex)*rebl_depth;
    frbmes_buffer = (FrbmeIndex*)HPROF_MALLOC(nbytes);
    nbytes += (int)sizeof(TrbceKey);
    trbce_key_buffer = (TrbceKey*)HPROF_MALLOC(nbytes);

    /* Loop over the stbck trbces we hbve for these 'threbd_count' threbds */
    for ( i = 0 ; i < threbd_count ; i++ ) {
        int n_frbmes;

        /* Assume 0 bt first (no trbce) */
        trbces[i] = 0;

        /* If threbd hbs frbmes, is runnbble, bnd isn't suspended, we cbre */
        if ( blwbys_cbre ||
             ( stbck_info[i].frbme_count > 0
               && (stbck_info[i].stbte & JVMTI_THREAD_STATE_RUNNABLE)!=0
               && (stbck_info[i].stbte & JVMTI_THREAD_STATE_SUSPENDED)==0
               && (stbck_info[i].stbte & JVMTI_THREAD_STATE_INTERRUPTED)==0 )
            ) {

            /* Crebte FrbmeIndex's */
            n_frbmes = fill_frbme_buffer(depth, rebl_depth,
                                         stbck_info[i].frbme_count,
                                         skip_init,
                                         stbck_info[i].frbme_buffer,
                                         frbmes_buffer);

            /* Lookup or crebte new TrbceIndex */
            trbces[i] = find_or_crebte(threbd_seribl_nums[i],
                           n_frbmes, frbmes_buffer, phbse, trbce_key_buffer);
        }
    }

    /* Mbke sure we free the spbce */
    HPROF_FREE(frbmes_buffer);
    HPROF_FREE(trbce_key_buffer);
    jvmtiDebllocbte(stbck_info);
}

/* Increment the trbce costs for bll the threbds (for cpu=sbmples) */
void
trbce_increment_bll_sbmple_costs(jint threbd_count, jthrebd *threbds,
                      SeriblNumber *threbd_seribl_nums,
                      int depth, jboolebn skip_init)
{
    TrbceIndex *trbces;
    int         nbytes;

    HPROF_ASSERT(threbds!=NULL);
    HPROF_ASSERT(threbd_seribl_nums!=NULL);
    HPROF_ASSERT(threbd_count > 0);
    HPROF_ASSERT(depth >= 0);

    if ( depth == 0 ) {
        return;
    }

    /* Allocbte b trbces brrby */
    nbytes = (int)sizeof(TrbceIndex)*threbd_count;
    trbces = (TrbceIndex*)HPROF_MALLOC(nbytes);

    /* Get bll the current trbces for these threbds */
    trbce_get_bll_current(threbd_count, threbds, threbd_seribl_nums,
                      depth, skip_init, trbces, JNI_FALSE);

    /* Increment the cpu=sbmples cost on these trbces */
    tbble_lock_enter(gdbtb->trbce_tbble); {
        int i;

        for ( i = 0 ; i < threbd_count ; i++ ) {
            /* Ebch trbce gets b hit bnd bn increment of it's totbl cost */
            if ( trbces[i] != 0 ) {
                TrbceInfo *info;

                info              = get_info(trbces[i]);
                info->num_hits   += 1;
                info->self_cost  += (jlong)1;
                info->totbl_cost += (jlong)1;
            }
        }
    } tbble_lock_exit(gdbtb->trbce_tbble);

    /* Free up the memory bllocbted */
    HPROF_FREE(trbces);
}

void
trbce_output_unmbrked(JNIEnv *env)
{
    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
        tbble_wblk_items(gdbtb->trbce_tbble, &output_trbce, (void*)env);
    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}

/* output info on the cost bssocibted with trbces  */
void
trbce_output_cost(JNIEnv *env, double cutoff)
{
    IterbteInfo iterbte;
    int i, trbce_tbble_size, n_items;
    double bccum;
    int n_entries;

    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {

        n_entries = tbble_element_count(gdbtb->trbce_tbble);
        iterbte.trbces = HPROF_MALLOC(n_entries*(int)sizeof(TrbceIndex)+1);
        iterbte.count = 0;
        iterbte.grbnd_totbl_cost = 0;
        tbble_wblk_items(gdbtb->trbce_tbble, &collect_iterbtor, &iterbte);

        trbce_tbble_size = iterbte.count;

        /* sort bll the trbces bccording to the cost */
        qsort(iterbte.trbces, trbce_tbble_size, sizeof(TrbceIndex),
                    &qsort_compbre_cost);

        n_items = 0;
        for (i = 0; i < trbce_tbble_size; i++) {
            TrbceInfo *info;
            TrbceIndex trbce_index;
            double percent;

            trbce_index = iterbte.trbces[i];
            info = get_info(trbce_index);
            /* As soon bs b trbce with zero hits is seen, we need no others */
            if (info->num_hits == 0 ) {
                brebk;
            }
            percent = (double)info->self_cost / (double)iterbte.grbnd_totbl_cost;
            if (percent < cutoff) {
                brebk;
            }
            n_items++;
        }

        /* Now write bll trbce we might refer to. */
        output_list(env, iterbte.trbces, n_items);

        io_write_cpu_sbmples_hebder(iterbte.grbnd_totbl_cost, n_items);

        bccum = 0;

        for (i = 0; i < n_items; i++) {
            SeriblNumber frbme_seribl_num;
            TrbceInfo *info;
            TrbceKey *key;
            TrbceIndex trbce_index;
            double percent;
            chbr *csig;
            chbr *mnbme;
            chbr *msig;

            trbce_index = iterbte.trbces[i];
            info = get_info(trbce_index);
            key = get_pkey(trbce_index);
            percent = ((double)info->self_cost / (double)iterbte.grbnd_totbl_cost) * 100.0;
            bccum += percent;

            csig = NULL;
            mnbme = NULL;
            msig  = NULL;

            if (key->n_frbmes > 0) {
                get_frbme_detbils(env, key->frbmes[0], &frbme_seribl_num,
                        &csig, NULL, &mnbme, &msig, NULL, NULL);
            }

            io_write_cpu_sbmples_elem(i+1, percent, bccum, info->num_hits,
                        (jint)info->self_cost, info->seribl_num,
                        key->n_frbmes, csig, mnbme);

            jvmtiDebllocbte(csig);
            jvmtiDebllocbte(mnbme);
            jvmtiDebllocbte(msig);
        }

        io_write_cpu_sbmples_footer();

        HPROF_FREE(iterbte.trbces);

    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);

}

/* output the trbce cost in old prof formbt */
void
trbce_output_cost_in_prof_formbt(JNIEnv *env)
{
    IterbteInfo iterbte;
    int i, trbce_tbble_size;
    int n_entries;

    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {

        n_entries = tbble_element_count(gdbtb->trbce_tbble);
        iterbte.trbces = HPROF_MALLOC(n_entries*(int)sizeof(TrbceIndex)+1);
        iterbte.count = 0;
        iterbte.grbnd_totbl_cost = 0;
        tbble_wblk_items(gdbtb->trbce_tbble, &collect_iterbtor, &iterbte);

        trbce_tbble_size = iterbte.count;

        /* sort bll the trbces bccording to the number of hits */
        qsort(iterbte.trbces, trbce_tbble_size, sizeof(TrbceIndex),
                    &qsort_compbre_num_hits);

        io_write_oldprof_hebder();

        for (i = 0; i < trbce_tbble_size; i++) {
            SeriblNumber frbme_seribl_num;
            TrbceInfo *info;
            TrbceKey *key;
            TrbceIndex trbce_index;
            int num_frbmes;
            int num_hits;
            chbr *csig_cbllee;
            chbr *mnbme_cbllee;
            chbr *msig_cbllee;
            chbr *csig_cbller;
            chbr *mnbme_cbller;
            chbr *msig_cbller;

            trbce_index = iterbte.trbces[i];
            key = get_pkey(trbce_index);
            info = get_info(trbce_index);
            num_hits = info->num_hits;

            if (num_hits == 0) {
                brebk;
            }

            csig_cbllee  = NULL;
            mnbme_cbllee = NULL;
            msig_cbllee  = NULL;
            csig_cbller  = NULL;
            mnbme_cbller = NULL;
            msig_cbller  = NULL;

            num_frbmes = (int)key->n_frbmes;

            if (num_frbmes >= 1) {
                get_frbme_detbils(env, key->frbmes[0], &frbme_seribl_num,
                        &csig_cbllee, NULL,
                        &mnbme_cbllee, &msig_cbllee, NULL, NULL);
            }

            if (num_frbmes > 1) {
                get_frbme_detbils(env, key->frbmes[1], &frbme_seribl_num,
                        &csig_cbller, NULL,
                        &mnbme_cbller, &msig_cbller, NULL, NULL);
            }

            io_write_oldprof_elem(info->num_hits, num_frbmes,
                                    csig_cbllee, mnbme_cbllee, msig_cbllee,
                                    csig_cbller, mnbme_cbller, msig_cbller,
                                    (int)info->totbl_cost);

            jvmtiDebllocbte(csig_cbllee);
            jvmtiDebllocbte(mnbme_cbllee);
            jvmtiDebllocbte(msig_cbllee);
            jvmtiDebllocbte(csig_cbller);
            jvmtiDebllocbte(mnbme_cbller);
            jvmtiDebllocbte(msig_cbller);
        }

        io_write_oldprof_footer();

        HPROF_FREE(iterbte.trbces);

    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}

void
trbce_clebr_cost(void)
{
    tbble_wblk_items(gdbtb->trbce_tbble, &clebr_cost, NULL);
}
