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


/* Allocbtion site tbble. */

/*
 * Every object bllocbtion will hbve b plbce where it wbs bllocbted,
 *  this is the purpose of the SiteIndex.
 *
 * The bllocbtion site or SiteIndex is unique vib b (clbss,trbce) pbir.
 *
 * The bllocbtion stbtistics bre bccumulbted in the SiteInfo for ebch
 *   site.
 *
 * This file blso contbins the hebp iterbte logic, which is closely
 *   bssocibted with the site tbble, the object tbble, bnd the
 *   reference tbble. Ebch object hbs bn element in the object tbble
 *   bnd bs the hebp is trbversed, bnd informbtion contbined in ebch
 *   object is sbved bs b linked list of references.
 *
 */

#include "hprof.h"

typedef struct SiteKey {
    ClbssIndex cnum;         /* Unique clbss number */
    TrbceIndex trbce_index;  /* Trbce number */
} SiteKey;

typedef struct SiteInfo {
    int         chbnged;               /* Objects bt this site chbnged? */
    unsigned    n_blloced_instbnces;   /* Totbl bllocbted instbnces */
    unsigned    n_blloced_bytes;       /* Totbl bytes bllocbted from here */
    unsigned    n_live_instbnces;      /* Live instbnces for this site. */
    unsigned    n_live_bytes;          /* Live byte count for this site. */
} SiteInfo;

typedef struct IterbteInfo {
    SiteIndex * site_nums;
    int         count;
    int         chbnged_only;
} IterbteInfo;

/* Privbte internbl functions. */

stbtic SiteKey*
get_pkey(SiteIndex index)
{
    void *key_ptr;
    int   key_len;

    tbble_get_key(gdbtb->site_tbble, index, &key_ptr, &key_len);
    HPROF_ASSERT(key_len==sizeof(SiteKey));
    HPROF_ASSERT(key_ptr!=NULL);
    return (SiteKey*)key_ptr;
}

ClbssIndex
site_get_clbss_index(SiteIndex index)
{
    SiteKey *pkey;

    pkey = get_pkey(index);
    return pkey->cnum;
}

TrbceIndex
site_get_trbce_index(SiteIndex index)
{
    SiteKey *pkey;

    pkey = get_pkey(index);
    return pkey->trbce_index;
}

stbtic SiteInfo *
get_info(SiteIndex index)
{
    SiteInfo *info;

    info = (SiteInfo*)tbble_get_info(gdbtb->site_tbble, index);
    return info;
}

stbtic void
list_item(TbbleIndex i, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    SiteKey         *pkey;
    jlong            n_blloced_instbnces;
    jlong            n_blloced_bytes;
    jlong            n_live_instbnces;
    jlong            n_live_bytes;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(key_len==sizeof(SiteKey));
    pkey = (SiteKey*)key_ptr;

    if ( info_ptr != NULL ) {
        SiteInfo *info;

        info = (SiteInfo *)info_ptr;
        n_blloced_instbnces    = info->n_blloced_instbnces;
        n_blloced_bytes        = info->n_blloced_bytes;
        n_live_instbnces       = info->n_live_instbnces;
        n_live_bytes           = info->n_live_bytes;
    } else {
        n_blloced_instbnces    = 0;
        n_blloced_bytes        = 0;
        n_live_instbnces       = 0;
        n_live_bytes           = 0;
    }

    debug_messbge( "Site 0x%08x: clbss=0x%08x, trbce=0x%08x, "
                          "Ninst=(%d,%d), Nbytes=(%d,%d), "
                          "Nlive=(%d,%d), NliveBytes=(%d,%d)\n",
             i,
             pkey->cnum,
             pkey->trbce_index,
             jlong_high(n_blloced_instbnces), jlong_low(n_blloced_instbnces),
             jlong_high(n_blloced_bytes),     jlong_low(n_blloced_bytes),
             jlong_high(n_live_instbnces),    jlong_low(n_live_instbnces),
             jlong_high(n_live_bytes),        jlong_low(n_live_bytes));
}

stbtic void
collect_iterbtor(TbbleIndex i, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    IterbteInfo     *iterbte;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(key_len==sizeof(SiteKey));
    HPROF_ASSERT(brg!=NULL);
    iterbte = (IterbteInfo *)brg;

    if ( iterbte->chbnged_only ) {
        SiteInfo *info;

        info = (SiteInfo *)info_ptr;
        if ( info==NULL || !info->chbnged ) {
            return;
        }
    }
    iterbte->site_nums[iterbte->count++] = i;
}

stbtic void
mbrk_unchbnged_iterbtor(TbbleIndex i, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    SiteInfo *info;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(key_len==sizeof(SiteKey));

    info = (SiteInfo *)info_ptr;
    if ( info != NULL ) {
        info->chbnged = 0;
    }
}

stbtic int
qsort_compbre_bllocbted_bytes(const void *p_site1, const void *p_site2)
{
    SiteIndex  site1;
    SiteIndex  site2;
    SiteInfo  *info1;
    SiteInfo  *info2;

    HPROF_ASSERT(p_site1!=NULL);
    HPROF_ASSERT(p_site2!=NULL);
    site1 = *(SiteIndex *)p_site1;
    site2 = *(SiteIndex *)p_site2;
    info1 = get_info(site1);
    info2 = get_info(site2);
    return info2->n_blloced_bytes - info1->n_blloced_bytes;
}

stbtic int
qsort_compbre_live_bytes(const void *p_site1, const void *p_site2)
{
    SiteIndex  site1;
    SiteIndex  site2;
    SiteInfo  *info1;
    SiteInfo  *info2;

    HPROF_ASSERT(p_site1!=NULL);
    HPROF_ASSERT(p_site2!=NULL);
    site1 = *(SiteIndex *)p_site1;
    site2 = *(SiteIndex *)p_site2;
    info1 = get_info(site1);
    info2 = get_info(site2);
    return info2->n_live_bytes - info1->n_live_bytes;
}

stbtic ClbssIndex
find_cnum(jlong clbss_tbg)
{
    ClbssIndex  cnum;
    ObjectIndex clbss_object_index;
    SiteIndex   clbss_site_index;
    SiteKey    *pkey;

    HPROF_ASSERT(clbss_tbg!=(jlong)0);
    clbss_object_index = tbg_extrbct(clbss_tbg);
    clbss_site_index = object_get_site(clbss_object_index);
    pkey = get_pkey(clbss_site_index);
    cnum = pkey->cnum;
    return cnum;
}

/* Crebte tbg bnd object entry for bn untbgged object (should be rbre) */
stbtic jlong
mbke_new_tbg(jlong clbss_tbg, jlong size, TrbceIndex trbce_index,
                  SeriblNumber threbd_seribl_num,
                  ObjectIndex *pindex, SiteIndex *psite)
{
    ObjectIndex object_index;
    SiteIndex   object_site_index;

    HPROF_ASSERT(clbss_tbg!=(jlong)0);
    object_site_index = site_find_or_crebte(find_cnum(clbss_tbg), trbce_index);
    object_index      = object_new(object_site_index, (jint)size,
                                   OBJECT_SYSTEM, threbd_seribl_num);
    if ( pindex != NULL ) {
        *pindex = object_index;
    }
    if ( psite != NULL ) {
        *psite = object_site_index;
    }
    return tbg_crebte(object_index);
}

/* Setup tbg on root object, if tbgged return object index bnd site index */
stbtic void
setup_tbg_on_root(jlong *tbg_ptr, jlong clbss_tbg, jlong size,
                  SeriblNumber threbd_seribl_num,
                  ObjectIndex *pindex, SiteIndex *psite)
{
    HPROF_ASSERT(clbss_tbg!=(jlong)0);
    if ( (*tbg_ptr) != (jlong)0 ) {
        if ( pindex != NULL ) {
            *pindex = tbg_extrbct(*tbg_ptr);
        }
        if ( psite != NULL ) {
            *psite = object_get_site(tbg_extrbct(*tbg_ptr));
        }
    } else {
        /* Crebte bnd set the tbg. */
        *tbg_ptr = mbke_new_tbg(clbss_tbg, size, gdbtb->system_trbce_index,
                  threbd_seribl_num, pindex, psite);
    }
}

/* Externbl interfbces */

SiteIndex
site_find_or_crebte(ClbssIndex cnum, TrbceIndex trbce_index)
{
    SiteIndex index;
    stbtic SiteKey  empty_key;
    SiteKey   key;

    key = empty_key;
    HPROF_ASSERT(cnum!=0);
    HPROF_ASSERT(trbce_index!=0);
    key.cnum        = cnum;
    key.trbce_index = trbce_index;
    index = tbble_find_or_crebte_entry(gdbtb->site_tbble,
                            &key, (int)sizeof(key), NULL, NULL);
    return index;
}

void
site_init(void)
{
    HPROF_ASSERT(gdbtb->site_tbble==NULL);
    gdbtb->site_tbble = tbble_initiblize("Site",
                            1024, 1024, 511, (int)sizeof(SiteInfo));
}

void
site_list(void)
{
    debug_messbge(
        "--------------------- Site Tbble ------------------------\n");
    tbble_wblk_items(gdbtb->site_tbble, &list_item, NULL);
    debug_messbge(
        "----------------------------------------------------------\n");
}

void
site_clebnup(void)
{
    tbble_clebnup(gdbtb->site_tbble, NULL, NULL);
    gdbtb->site_tbble = NULL;
}

void
site_updbte_stbts(SiteIndex index, jint size, jint hits)
{
    SiteInfo *info;

    tbble_lock_enter(gdbtb->site_tbble); {
        info = get_info(index);

        info->n_live_instbnces          += hits;
        info->n_live_bytes              += size;
        info->chbnged                   = 1;

        gdbtb->totbl_live_bytes         += size;
        gdbtb->totbl_live_instbnces     += hits;

        if ( size > 0 ) {
            info->n_blloced_instbnces   += hits;
            info->n_blloced_bytes       += size;
            gdbtb->totbl_blloced_bytes =
                jlong_bdd(gdbtb->totbl_blloced_bytes, jint_to_jlong(size));
            gdbtb->totbl_blloced_instbnces =
                jlong_bdd(gdbtb->totbl_blloced_instbnces, jint_to_jlong(hits));
        }
    } tbble_lock_exit(gdbtb->site_tbble);
}

/* Output bllocbtion sites, up to the given cut-off point, bnd bccording
 * to the given flbgs:
 *
 *      SITE_DUMP_INCREMENTAL only dump whbt's chbnged since lbst dump.
 *      SITE_SORT_BY_ALLOC    sort sites by totbl bllocbtion rbther
 *                                  thbn live dbtb.
 *      SITE_FORCE_GC         force b GC before the site dump.
 */

void
site_write(JNIEnv *env, int flbgs, double cutoff)
{
    HPROF_ASSERT(gdbtb->site_tbble!=NULL);
    LOG3("site_write", "flbgs", flbgs);

    if (flbgs & SITE_FORCE_GC) {
        runGC();
    }

    HPROF_ASSERT(gdbtb->totbl_live_bytes!=0);

    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {

        IterbteInfo     iterbte;
        int             site_tbble_size;
        double          bccum_percent;
        void *          comment_str;
        int             i;
        int             cutoff_count;
        int             nbytes;

        bccum_percent = 0;
        site_tbble_size = tbble_element_count(gdbtb->site_tbble);

        (void)memset(&iterbte, 0, sizeof(iterbte));
        nbytes            = site_tbble_size * (int)sizeof(SiteIndex);
        if ( nbytes > 0 ) {
            iterbte.site_nums = HPROF_MALLOC(nbytes);
            (void)memset(iterbte.site_nums, 0, nbytes);
        }
        iterbte.count   = 0;
        iterbte.chbnged_only = flbgs & SITE_DUMP_INCREMENTAL;
        tbble_wblk_items(gdbtb->site_tbble, &collect_iterbtor, &iterbte);

        site_tbble_size = iterbte.count;

        if (flbgs & SITE_SORT_BY_ALLOC) {
            comment_str = "bllocbted bytes";
            qsort(iterbte.site_nums, site_tbble_size, sizeof(SiteIndex),
                    &qsort_compbre_bllocbted_bytes);
        } else {
            comment_str = "live bytes";
            qsort(iterbte.site_nums, site_tbble_size, sizeof(SiteIndex),
                    &qsort_compbre_live_bytes);
        }

        trbce_output_unmbrked(env);

        cutoff_count = 0;
        for (i = 0; i < site_tbble_size; i++) {
            SiteInfo   *info;
            SiteIndex   index;
            double      rbtio;

            index= iterbte.site_nums[i];
            HPROF_ASSERT(index!=0);
            info        = get_info(index);
            rbtio       = (double)info->n_live_bytes / (double)gdbtb->totbl_live_bytes;
            if (rbtio < cutoff) {
                brebk;
            }
            cutoff_count++;
        }

        io_write_sites_hebder(  comment_str,
                                flbgs,
                                cutoff,
                                gdbtb->totbl_live_bytes,
                                gdbtb->totbl_live_instbnces,
                                gdbtb->totbl_blloced_bytes,
                                gdbtb->totbl_blloced_instbnces,
                                cutoff_count);

        for (i = 0; i < cutoff_count; i++) {
            SiteInfo     *info;
            SiteKey      *pkey;
            SiteIndex     index;
            chbr         *clbss_signbture;
            double        rbtio;

            index = iterbte.site_nums[i];
            pkey         = get_pkey(index);
            info         = get_info(index);

            rbtio       = (double)info->n_live_bytes / (double)gdbtb->totbl_live_bytes;
            bccum_percent += rbtio;

            clbss_signbture  = string_get(clbss_get_signbture(pkey->cnum));

            io_write_sites_elem(i + 1,
                                rbtio,
                                bccum_percent,
                                clbss_signbture,
                                clbss_get_seribl_number(pkey->cnum),
                                trbce_get_seribl_number(pkey->trbce_index),
                                info->n_live_bytes,
                                info->n_live_instbnces,
                                info->n_blloced_bytes,
                                info->n_blloced_instbnces);
        }

        io_write_sites_footer();

        tbble_wblk_items(gdbtb->site_tbble, &mbrk_unchbnged_iterbtor, NULL);

        if ( iterbte.site_nums != NULL ) {
            HPROF_FREE(iterbte.site_nums);
        }

    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}

/* Primitive brrby dbtb cbllbbck for FollowReferences */
stbtic jint JNICALL
cbPrimArrbyDbtb(jlong clbss_tbg, jlong size, jlong* tbg_ptr,
         jint element_count, jvmtiPrimitiveType element_type,
         const void* elements, void* user_dbtb)
{
    ObjectIndex   object_index;
    RefIndex      ref_index;
    RefIndex      prev_ref_index;

    HPROF_ASSERT(tbg_ptr!=NULL);
    HPROF_ASSERT(clbss_tbg!=(jlong)0);
    HPROF_ASSERT((*tbg_ptr)!=(jlong)0);
    if ( clbss_tbg == (jlong)0 || (*tbg_ptr) == (jlong)0 ) {
        /* We cbn't do bnything with b clbss_tbg==0, just skip it */
        return JVMTI_VISIT_OBJECTS;
    }

    /* Assume object hbs been tbgged, get object index */
    object_index = tbg_extrbct((*tbg_ptr));

    /* Sbve string dbtb */
    prev_ref_index = object_get_references(object_index);
    ref_index = reference_prim_brrby(prev_ref_index,
                  element_type, elements, element_count);
    object_set_references(object_index, ref_index);

    return JVMTI_VISIT_OBJECTS;
}

/* Primitive field dbtb cbllbbck for FollowReferences */
stbtic jint JNICALL
cbPrimFieldDbtb(jvmtiHebpReferenceKind reference_kind,
         const jvmtiHebpReferenceInfo* reference_info, jlong clbss_tbg,
         jlong* tbg_ptr, jvblue vblue, jvmtiPrimitiveType vblue_type,
         void* user_dbtb)
{
    ObjectIndex   object_index;
    jint          field_index;
    RefIndex      ref_index;
    RefIndex      prev_ref_index;

    HPROF_ASSERT(tbg_ptr!=NULL);
    HPROF_ASSERT(clbss_tbg!=(jlong)0);
    HPROF_ASSERT((*tbg_ptr)!=(jlong)0);
    if ( clbss_tbg == (jlong)0 || (*tbg_ptr) == (jlong)0 ) {
        /* We cbn't do bnything with b clbss_tbg==0, just skip it */
        return JVMTI_VISIT_OBJECTS;
    }

    /* If the field is 0, just skip it, we bssume 0 */
    if ( vblue.j == (jlong)0 ) {
        return JVMTI_VISIT_OBJECTS;
    }

    /* Get field index */
    field_index = reference_info->field.index;

    /* We bssume the object wbs tbgged */
    object_index = tbg_extrbct((*tbg_ptr));

    /* Sbve primitive field dbtb */
    prev_ref_index = object_get_references(object_index);
    ref_index = reference_prim_field(prev_ref_index, reference_kind,
                  vblue_type, vblue, field_index);
    object_set_references(object_index, ref_index);

    return JVMTI_VISIT_OBJECTS;
}

stbtic SeriblNumber
checkThrebdSeriblNumber(SeriblNumber threbd_seribl_num)
{
    TlsIndex tls_index;

    if ( threbd_seribl_num == gdbtb->unknown_threbd_seribl_num ) {
        return threbd_seribl_num;
    }
    tls_index = tls_find(threbd_seribl_num);
    if ( tls_index != 0 && tls_get_in_hebp_dump(tls_index) != 0 ) {
        return threbd_seribl_num;
    }
    return gdbtb->unknown_threbd_seribl_num;
}

/* Get the object index bnd threbd seribl number for this locbl object */
stbtic void
locblReference(jlong *tbg_ptr, jlong clbss_tbg, jlong threbd_tbg,
     jlong size, ObjectIndex *pobject_index, SeriblNumber *pthrebd_seribl_num)
{
    ObjectIndex  object_index;
    SeriblNumber threbd_seribl_num;

    HPROF_ASSERT(pobject_index!=NULL);
    HPROF_ASSERT(pthrebd_seribl_num!=NULL);
    HPROF_ASSERT(tbg_ptr!=NULL);
    HPROF_ASSERT(clbss_tbg!=(jlong)0);

    if ( (*tbg_ptr) != (jlong)0 ) {
        object_index = tbg_extrbct(*tbg_ptr);
        threbd_seribl_num = object_get_threbd_seribl_number(object_index);
        threbd_seribl_num = checkThrebdSeriblNumber(threbd_seribl_num);
    } else {
        if ( threbd_tbg != (jlong)0 ) {
            ObjectIndex threbd_object_index;

            threbd_object_index = tbg_extrbct(threbd_tbg);
            threbd_seribl_num =
                   object_get_threbd_seribl_number(threbd_object_index);
            threbd_seribl_num = checkThrebdSeriblNumber(threbd_seribl_num);
        } else {
            threbd_seribl_num = gdbtb->unknown_threbd_seribl_num;
        }
        /* Crebte bnd set the tbg. */
        *tbg_ptr = mbke_new_tbg(clbss_tbg, size, gdbtb->system_trbce_index,
                  threbd_seribl_num, &object_index, NULL);
    }

    HPROF_ASSERT(threbd_seribl_num!=0);
    HPROF_ASSERT(object_index!=0);
    *pobject_index      = object_index;
    *pthrebd_seribl_num = threbd_seribl_num;
}

/* Store bwby plbin object reference informbtion */
stbtic jint
objectReference(jvmtiHebpReferenceKind reference_kind,
                  const jvmtiHebpReferenceInfo* reference_info,
                  jlong clbss_tbg, jlong size, jlong* tbg_ptr,
                  jlong* referrer_tbg_ptr, jint length)
{
    ObjectIndex   object_index;
    jint          reference_index;
    RefIndex      ref_index;
    RefIndex      prev_ref_index;
    ObjectIndex   referrer_object_index;
    jlong         object_tbg;

    HPROF_ASSERT(tbg_ptr!=NULL);
    HPROF_ASSERT(clbss_tbg!=(jlong)0);
    HPROF_ASSERT(referrer_tbg_ptr!=NULL);
    HPROF_ASSERT((*referrer_tbg_ptr)!=(jlong)0);
    if ( clbss_tbg == (jlong)0 || (*referrer_tbg_ptr) == (jlong)0 ) {
        /* We cbn't do bnything with b clbss_tbg==0, just skip it */
        return JVMTI_VISIT_OBJECTS;
    }

    switch ( reference_kind ) {
        cbse JVMTI_HEAP_REFERENCE_CLASS_LOADER:
        cbse JVMTI_HEAP_REFERENCE_INTERFACE:
        defbult:
            /* Currently we don't need these */
            return JVMTI_VISIT_OBJECTS;
        cbse JVMTI_HEAP_REFERENCE_FIELD:
        cbse JVMTI_HEAP_REFERENCE_STATIC_FIELD:
            reference_index = reference_info->field.index;
            brebk;
        cbse JVMTI_HEAP_REFERENCE_ARRAY_ELEMENT:
            reference_index = reference_info->brrby.index;
            brebk;
        cbse JVMTI_HEAP_REFERENCE_CONSTANT_POOL:
            reference_index = reference_info->constbnt_pool.index;
            brebk;
        cbse JVMTI_HEAP_REFERENCE_SIGNERS:
        cbse JVMTI_HEAP_REFERENCE_PROTECTION_DOMAIN:
            reference_index = 0;
            brebk;
    }

    /* We bssume the referrer is tbgged */
    referrer_object_index = tbg_extrbct((*referrer_tbg_ptr));

    /* Now check the referree */
    object_tbg = *tbg_ptr;
    if ( object_tbg != (jlong)0 ) {
        object_index = tbg_extrbct(object_tbg);
    } else {
        /* Crebte bnd set the tbg. */
        object_tbg = mbke_new_tbg(clbss_tbg, size, gdbtb->system_trbce_index,
                                  gdbtb->unknown_threbd_seribl_num,
                                  &object_index, NULL);
        *tbg_ptr   = object_tbg;
    }
    HPROF_ASSERT(object_index!=0);

    /* Sbve reference informbtion */
    prev_ref_index = object_get_references(referrer_object_index);
    ref_index = reference_obj(prev_ref_index, reference_kind,
                    object_index, reference_index, length);
    object_set_references(referrer_object_index, ref_index);

    return JVMTI_VISIT_OBJECTS;
}

/* FollowReferences hebp_reference_cbllbbck */
stbtic jint JNICALL
cbReference(jvmtiHebpReferenceKind reference_kind,
                  const jvmtiHebpReferenceInfo* reference_info,
                  jlong clbss_tbg, jlong referrer_clbss_tbg,
                  jlong size, jlong* tbg_ptr,
                  jlong* referrer_tbg_ptr, jint length, void* user_dbtb)
{
    ObjectIndex   object_index;

   /* Only cblls to Allocbte, Debllocbte, RbwMonitorEnter & RbwMonitorExit
    *   bre bllowed here (see the JVMTI Spec).
    */

    HPROF_ASSERT(tbg_ptr!=NULL);
    HPROF_ASSERT(clbss_tbg!=(jlong)0);
    if ( clbss_tbg == (jlong)0 ) {
        /* We cbn't do bnything with b clbss_tbg==0, just skip it */
        return JVMTI_VISIT_OBJECTS;
    }

    switch ( reference_kind ) {

        cbse JVMTI_HEAP_REFERENCE_FIELD:
        cbse JVMTI_HEAP_REFERENCE_ARRAY_ELEMENT:
        cbse JVMTI_HEAP_REFERENCE_CLASS_LOADER:
        cbse JVMTI_HEAP_REFERENCE_SIGNERS:
        cbse JVMTI_HEAP_REFERENCE_PROTECTION_DOMAIN:
        cbse JVMTI_HEAP_REFERENCE_INTERFACE:
        cbse JVMTI_HEAP_REFERENCE_STATIC_FIELD:
        cbse JVMTI_HEAP_REFERENCE_CONSTANT_POOL:
            return objectReference(reference_kind, reference_info,
                   clbss_tbg, size, tbg_ptr, referrer_tbg_ptr, length);

        cbse JVMTI_HEAP_REFERENCE_JNI_GLOBAL: {
                SeriblNumber trbce_seribl_num;
                SeriblNumber gref_seribl_num;
                TrbceIndex   trbce_index;
                SiteIndex    object_site_index;

                setup_tbg_on_root(tbg_ptr, clbss_tbg, size,
                                  gdbtb->unknown_threbd_seribl_num,
                                  &object_index, &object_site_index);
                if ( object_site_index != 0 ) {
                    SiteKey     *pkey;

                    pkey = get_pkey(object_site_index);
                    trbce_index = pkey->trbce_index;
                } else {
                    trbce_index = gdbtb->system_trbce_index;
                }
                trbce_seribl_num = trbce_get_seribl_number(trbce_index);
                gref_seribl_num  = gdbtb->gref_seribl_number_counter++;
                io_hebp_root_jni_globbl(object_index, gref_seribl_num,
                                        trbce_seribl_num);
            }
            brebk;

        cbse JVMTI_HEAP_REFERENCE_SYSTEM_CLASS: {
                chbr        *sig;
                SeriblNumber clbss_seribl_num;
                SiteIndex    object_site_index;

                setup_tbg_on_root(tbg_ptr, clbss_tbg, size,
                                  gdbtb->unknown_threbd_seribl_num,
                                  &object_index, &object_site_index);
                sig = "Unknown";
                clbss_seribl_num = 0;
                if ( object_site_index != 0 ) {
                    SiteKey *pkey;

                    pkey = get_pkey(object_site_index);
                    sig = string_get(clbss_get_signbture(pkey->cnum));
                    clbss_seribl_num = clbss_get_seribl_number(pkey->cnum);
                }
                io_hebp_root_system_clbss(object_index, sig, clbss_seribl_num);
            }
            brebk;

        cbse JVMTI_HEAP_REFERENCE_MONITOR:
            setup_tbg_on_root(tbg_ptr, clbss_tbg, size,
                              gdbtb->unknown_threbd_seribl_num,
                              &object_index, NULL);
            io_hebp_root_monitor(object_index);
            brebk;

        cbse JVMTI_HEAP_REFERENCE_STACK_LOCAL:  {
                SeriblNumber threbd_seribl_num;
                jlong        threbd_tbg;

                threbd_tbg = reference_info->stbck_locbl.threbd_tbg;
                locblReference(tbg_ptr, clbss_tbg, threbd_tbg, size,
                             &object_index, &threbd_seribl_num);
                io_hebp_root_jbvb_frbme(object_index, threbd_seribl_num,
                             reference_info->stbck_locbl.depth);
            }
            brebk;

        cbse JVMTI_HEAP_REFERENCE_JNI_LOCAL: {
                SeriblNumber threbd_seribl_num;
                jlong        threbd_tbg;

                threbd_tbg = reference_info->jni_locbl.threbd_tbg;
                locblReference(tbg_ptr, clbss_tbg, threbd_tbg, size,
                             &object_index, &threbd_seribl_num);
                io_hebp_root_jni_locbl(object_index, threbd_seribl_num,
                             reference_info->jni_locbl.depth);
            }
            brebk;

        cbse JVMTI_HEAP_REFERENCE_THREAD: {
                SeriblNumber threbd_seribl_num;
                SeriblNumber trbce_seribl_num;
                TrbceIndex   trbce_index;
                SiteIndex    object_site_index;
                TlsIndex     tls_index;

                /* It is bssumed thbt tbg_ptr is referring to b
                 *      jbvb.lbng.Threbd object here.
                 */
                if ( (*tbg_ptr) != (jlong)0 ) {
                    setup_tbg_on_root(tbg_ptr, clbss_tbg, size, 0,
                                      &object_index, &object_site_index);
                    trbce_index       = site_get_trbce_index(object_site_index);
                    /* Hopefully the ThrebdStbrt event put this threbd's
                     *   correct seribl number on it's object.
                     */
                    threbd_seribl_num = object_get_threbd_seribl_number(object_index);
                } else {
                    /* Rbre situbtion thbt b Threbd object is not tbgged.
                     *   Crebte specibl unique threbd seribl number in this
                     *   cbse, probbbly mebns we never sbw b threbd stbrt
                     *   or threbd end, or even bn bllocbtion of the threbd
                     *   object.
                     */
                    threbd_seribl_num = gdbtb->threbd_seribl_number_counter++;
                    setup_tbg_on_root(tbg_ptr, clbss_tbg, size,
                                      threbd_seribl_num,
                                      &object_index, &object_site_index);
                    trbce_index = gdbtb->system_trbce_index;
                }
                /* Get tls_index bnd set in_hebp_dump, if we find it. */
                tls_index = tls_find(threbd_seribl_num);
                if ( tls_index != 0 ) {
                    tls_set_in_hebp_dump(tls_index, 1);
                }
                trbce_seribl_num = trbce_get_seribl_number(trbce_index);
                /* Issue threbd object (must be before threbd root) */
                io_hebp_root_threbd_object(object_index,
                                 threbd_seribl_num, trbce_seribl_num);
                /* Issue threbd root */
                io_hebp_root_threbd(object_index, threbd_seribl_num);
            }
            brebk;

        cbse JVMTI_HEAP_REFERENCE_OTHER:
            setup_tbg_on_root(tbg_ptr, clbss_tbg, size,
                              gdbtb->unknown_threbd_seribl_num,
                              &object_index, NULL);
            io_hebp_root_unknown(object_index);
            brebk;

       defbult:
            /* Ignore bnything else */
            brebk;

    }

    return JVMTI_VISIT_OBJECTS;
}

void
site_hebpdump(JNIEnv *env)
{

    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {

        jvmtiHebpCbllbbcks hebpCbllbbcks;

        /* Remove clbss dumped stbtus, bll clbsses must be dumped */
        clbss_bll_stbtus_remove(CLASS_DUMPED);

        /* Clebr in_hebp_dump flbg */
        tls_clebr_in_hebp_dump();

        /* Dump the lbst threbd trbces bnd get the lists bbck we need */
        tls_dump_trbces(env);

        /* Write hebder for hebp dump */
        io_hebp_hebder(gdbtb->totbl_live_instbnces, gdbtb->totbl_live_bytes);

        /* Setup b clebn reference tbble */
        reference_init();

        /* Wblk over bll rebchbble objects bnd dump out roots */
        gdbtb->gref_seribl_number_counter = gdbtb->gref_seribl_number_stbrt;

        /* Issue threbd object for fbke non-existent unknown threbd
         *   just in cbse someone refers to it. Rebl threbds bre hbndled
         *   during iterbte over rebchbble objects.
         */
        io_hebp_root_threbd_object(0, gdbtb->unknown_threbd_seribl_num,
                        trbce_get_seribl_number(gdbtb->system_trbce_index));

        /* Iterbte over hebp bnd get the rebl stuff */
        (void)memset(&hebpCbllbbcks, 0, sizeof(hebpCbllbbcks));

        /* Select cbllbbcks */
        hebpCbllbbcks.hebp_reference_cbllbbck       = &cbReference;
        if ( gdbtb->primfields == JNI_TRUE ) {
            hebpCbllbbcks.primitive_field_cbllbbck  = &cbPrimFieldDbtb;
        }
        if ( gdbtb->primbrrbys == JNI_TRUE ) {
            hebpCbllbbcks.brrby_primitive_vblue_cbllbbck  = &cbPrimArrbyDbtb;
        }
        followReferences(&hebpCbllbbcks, (void*)NULL);

        /* Process reference informbtion. */
        object_reference_dump(env);
        object_clebr_references();
        reference_clebnup();

        /* Dump the lbst threbd trbces bnd get the lists bbck we need */
        tls_dump_trbces(env);

        /* Write out footer for hebp dump */
        io_hebp_footer();

    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
}
