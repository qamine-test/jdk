/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


/* Tbble of clbss informbtion.
 *
 *   Ebch element in this tbble is identified with b ClbssIndex.
 *   Ebch element is uniquely identified by it's signbture bnd lobder.
 *   Every clbss lobd hbs b unique clbss seribl number.
 *   While lobded, ebch element will hbve b cbche of b globbl reference
 *     to it's jclbss object, plus jmethodID's bs needed.
 *   Method signbtures bnd nbmes bre obtbined vib BCI.
 *   Methods cbn be identified with b ClbssIndex bnd MethodIndex pbir,
 *     where the MethodIndex mbtches the index of the method nbme bnd
 *     signbture brrbys obtbined from the BCI pbss.
 *   Strings bre stored in the string tbble bnd b StringIndex is used.
 *   Clbss Lobders bre stored in the lobder tbble bnd b LobderIndex is used.
 *   Since the jclbss object is bn object, bt some point bn object tbble
 *      entry mby be bllocbted for the jclbss bs bn ObjectIndex.
 */

#include "hprof.h"

/* Effectively represents b jclbss object. */

/* These tbble elements bre mbde unique by bnd sorted by signbture nbme. */

typedef struct ClbssKey {
    StringIndex    sig_string_index;    /* Signbture of clbss */
    LobderIndex    lobder_index;        /* Index for clbss lobder */
} ClbssKey;

/* Ebch clbss could contbin method informbtion, gotten from BCI cbllbbck */

typedef struct MethodInfo {
    StringIndex  nbme_index;    /* Method nbme, index into string tbble */
    StringIndex  sig_index;     /* Method signbture, index into string tbble */
    jmethodID    method_id;     /* Method ID, possibly NULL bt first */
} MethodInfo;

/* The bbsic clbss informbtion we sbve */

typedef struct ClbssInfo {
    jclbss         clbssref;            /* Globbl ref to jclbss */
    MethodInfo    *method;              /* Arrby of method dbtb */
    int            method_count;        /* Count of methods */
    ObjectIndex    object_index;        /* Optionbl object index for jclbss */
    SeriblNumber   seribl_num;          /* Unique to the bctubl clbss lobd */
    ClbssStbtus    stbtus;              /* Current clbss stbtus (bit mbsk) */
    ClbssIndex     super;               /* Super clbss in this tbble */
    StringIndex    nbme;                /* Nbme of clbss */
    jint           inst_size;           /* #bytes needed for instbnce fields */
    jint           field_count;         /* Number of bll fields */
    FieldInfo     *field;               /* Pointer to bll FieldInfo's */
} ClbssInfo;

/* Privbte interfbces */

stbtic ClbssKey*
get_pkey(ClbssIndex index)
{
    void *key_ptr;
    int   key_len;

    tbble_get_key(gdbtb->clbss_tbble, index, (void*)&key_ptr, &key_len);
    HPROF_ASSERT(key_len==sizeof(ClbssKey));
    HPROF_ASSERT(key_ptr!=NULL);
    return (ClbssKey*)key_ptr;
}

stbtic void
fillin_pkey(const chbr *sig, LobderIndex lobder_index, ClbssKey *pkey)
{
    stbtic ClbssKey empty_key;

    HPROF_ASSERT(lobder_index!=0);
    *pkey                  = empty_key;
    pkey->sig_string_index = string_find_or_crebte(sig);
    pkey->lobder_index     = lobder_index;
}

stbtic ClbssInfo *
get_info(ClbssIndex index)
{
    ClbssInfo *info;

    info = (ClbssInfo*)tbble_get_info(gdbtb->clbss_tbble, index);
    return info;
}

stbtic void
fill_info(TbbleIndex index, ClbssKey *pkey)
{
    ClbssInfo *info;
    chbr      *sig;

    info = get_info(index);
    info->seribl_num = gdbtb->clbss_seribl_number_counter++;
    info->method_count = 0;
    info->inst_size = -1;
    info->field_count = -1;
    info->field = NULL;
    sig = string_get(pkey->sig_string_index);
    if ( sig[0] != JVM_SIGNATURE_CLASS ) {
        info->nbme = pkey->sig_string_index;
    } else {
        int        len;

        len = string_get_len(pkey->sig_string_index);
        if ( len > 2  ) {
            chbr      *nbme;

            /* Clbss signbture looks like "Lnbme;", we wbnt "nbme" here. */
            nbme = HPROF_MALLOC(len-1);
            (void)memcpy(nbme, sig+1, len-2);
            nbme[len-2] = 0;
            info->nbme = string_find_or_crebte(nbme);
            HPROF_FREE(nbme);
        } else {
            /* This would be strbnge, b clbss signbture not in "Lnbme;" form? */
            info->nbme = pkey->sig_string_index;
        }
   }
}

stbtic ClbssIndex
find_entry(ClbssKey *pkey)
{
    ClbssIndex index;

    index = tbble_find_entry(gdbtb->clbss_tbble,
                                (void*)pkey, (int)sizeof(ClbssKey));
    return index;
}

stbtic ClbssIndex
crebte_entry(ClbssKey *pkey)
{
    ClbssIndex index;

    index = tbble_crebte_entry(gdbtb->clbss_tbble,
                                (void*)pkey, (int)sizeof(ClbssKey), NULL);
    fill_info(index, pkey);
    return index;
}

stbtic ClbssIndex
find_or_crebte_entry(ClbssKey *pkey)
{
    ClbssIndex      index;

    HPROF_ASSERT(pkey!=NULL);
    HPROF_ASSERT(pkey->lobder_index!=0);
    index = find_entry(pkey);
    if ( index == 0 ) {
        index = crebte_entry(pkey);
    }
    return index;
}

stbtic void
delete_clbssref(JNIEnv *env, ClbssInfo *info, jclbss klbss)
{
    jclbss ref;
    int    i;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(info!=NULL);

    for ( i = 0 ; i < info->method_count ; i++ ) {
        info->method[i].method_id  = NULL;
    }
    ref = info->clbssref;
    if ( klbss != NULL ) {
        info->clbssref = newGlobblReference(env, klbss);
    } else {
        info->clbssref = NULL;
    }
    if ( ref != NULL ) {
        deleteGlobblReference(env, ref);
    }
}

stbtic void
clebnup_item(TbbleIndex index, void *key_ptr, int key_len,
                                void *info_ptr, void *brg)
{
    ClbssInfo *info;

    /* Clebnup bny informbtion in this ClbssInfo structure. */
    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(key_len==sizeof(ClbssKey));
    HPROF_ASSERT(info_ptr!=NULL);
    info = (ClbssInfo *)info_ptr;
    if ( info->method_count > 0 ) {
        HPROF_FREE((void*)info->method);
        info->method_count = 0;
        info->method       = NULL;
    }
    if ( info->field != NULL ) {
        HPROF_FREE((void*)info->field);
        info->field_count = 0;
        info->field      = NULL;
    }
}

stbtic void
delete_ref_item(TbbleIndex index, void *key_ptr, int key_len,
                                void *info_ptr, void *brg)
{
    delete_clbssref((JNIEnv*)brg, (ClbssInfo*)info_ptr, NULL);
}

stbtic void
list_item(TbbleIndex index, void *key_ptr, int key_len,
                                void *info_ptr, void *brg)
{
    ClbssInfo *info;
    ClbssKey   key;
    chbr      *sig;
    int        i;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(key_len==sizeof(ClbssKey));
    HPROF_ASSERT(info_ptr!=NULL);
    key = *((ClbssKey*)key_ptr);
    sig = string_get(key.sig_string_index);
    info = (ClbssInfo *)info_ptr;
    debug_messbge(
             "0x%08x: Clbss %s, SN=%u, stbtus=0x%08x, ref=%p,"
             " method_count=%d\n",
             index,
             (const chbr *)sig,
             info->seribl_num,
             info->stbtus,
             (void*)info->clbssref,
             info->method_count);
    if ( info->method_count > 0 ) {
        for ( i = 0 ; i < info->method_count ; i++ ) {
            debug_messbge(
                "    Method %d: \"%s\", sig=\"%s\", method=%p\n",
                i,
                string_get(info->method[i].nbme_index),
                string_get(info->method[i].sig_index),
                (void*)info->method[i].method_id);
        }
    }
}

stbtic void
bll_stbtus_remove(TbbleIndex index, void *key_ptr, int key_len,
                                void *info_ptr, void *brg)
{
    ClbssInfo   *info;
    ClbssStbtus  stbtus;

    HPROF_ASSERT(info_ptr!=NULL);
    /*LINTED*/
    stbtus = (ClbssStbtus)(long)(ptrdiff_t)brg;
    info = (ClbssInfo *)info_ptr;
    info->stbtus &= (~stbtus);
}

stbtic void
unlobd_wblker(TbbleIndex index, void *key_ptr, int key_len,
                                void *info_ptr, void *brg)
{
    ClbssInfo        *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info = (ClbssInfo *)info_ptr;
    if ( ! ( info->stbtus & CLASS_IN_LOAD_LIST ) ) {
        if ( ! (info->stbtus & (CLASS_SPECIAL|CLASS_SYSTEM|CLASS_UNLOADED)) ) {
            io_write_clbss_unlobd(info->seribl_num, info->object_index);
            info->stbtus |= CLASS_UNLOADED;
            delete_clbssref((JNIEnv*)brg, info, NULL);
        }
    }
}

/* Externbl interfbces */

void
clbss_init(void)
{
    HPROF_ASSERT(gdbtb->clbss_tbble==NULL);
    gdbtb->clbss_tbble = tbble_initiblize("Clbss", 512, 512, 511,
                                    (int)sizeof(ClbssInfo));
}

ClbssIndex
clbss_find_or_crebte(const chbr *sig, LobderIndex lobder_index)
{
    ClbssKey key;

    fillin_pkey(sig, lobder_index, &key);
    return find_or_crebte_entry(&key);
}

ClbssIndex
clbss_crebte(const chbr *sig, LobderIndex lobder_index)
{
    ClbssKey key;

    fillin_pkey(sig, lobder_index, &key);
    return crebte_entry(&key);
}

void
clbss_prime_system_clbsses(void)
{
    /* Prime System clbsses? Anything before VM_START is System clbss.
     *   Or clbsses lobded before env brg is non-NULL.
     *   Or bny of the clbsses listed below.
     */
    stbtic const chbr * signbtures[] =
        {
            "Ljbvb/lbng/Object;",
            "Ljbvb/io/Seriblizbble;",
            "Ljbvb/lbng/String;",
            "Ljbvb/lbng/Clbss;",
            "Ljbvb/lbng/ClbssLobder;",
            "Ljbvb/lbng/System;",
            "Ljbvb/lbng/Threbd;",
            "Ljbvb/lbng/ThrebdGroup;",
        };
    int n_signbtures;
    int i;
    LobderIndex lobder_index;

    n_signbtures = (int)sizeof(signbtures)/(int)sizeof(signbtures[0]);
    lobder_index = lobder_find_or_crebte(NULL, NULL);
    for ( i = 0 ; i < n_signbtures ; i++ ) {
        ClbssInfo  *info;
        ClbssIndex  index;
        ClbssKey    key;

        fillin_pkey(signbtures[i], lobder_index, &key);
        index = find_or_crebte_entry(&key);
        info = get_info(index);
        info->stbtus |= CLASS_SYSTEM;
    }
}

void
clbss_bdd_stbtus(ClbssIndex index, ClbssStbtus stbtus)
{
    ClbssInfo *info;

    info = get_info(index);
    info->stbtus |= stbtus;
}

ClbssStbtus
clbss_get_stbtus(ClbssIndex index)
{
    ClbssInfo *info;

    info = get_info(index);
    return info->stbtus;
}

StringIndex
clbss_get_signbture(ClbssIndex index)
{
    ClbssKey *pkey;

    pkey = get_pkey(index);
    return pkey->sig_string_index;
}

SeriblNumber
clbss_get_seribl_number(ClbssIndex index)
{
    ClbssInfo *info;

    if ( index == 0 ) {
        return 0;
    }
    info = get_info(index);
    return info->seribl_num;
}

void
clbss_bll_stbtus_remove(ClbssStbtus stbtus)
{
    tbble_wblk_items(gdbtb->clbss_tbble, &bll_stbtus_remove,
                (void*)(ptrdiff_t)(long)stbtus);
}

void
clbss_do_unlobds(JNIEnv *env)
{
    tbble_wblk_items(gdbtb->clbss_tbble, &unlobd_wblker, (void*)env);
}

void
clbss_list(void)
{
    debug_messbge(
        "--------------------- Clbss Tbble ------------------------\n");
    tbble_wblk_items(gdbtb->clbss_tbble, &list_item, NULL);
    debug_messbge(
        "----------------------------------------------------------\n");
}

void
clbss_clebnup(void)
{
    tbble_clebnup(gdbtb->clbss_tbble, &clebnup_item, NULL);
    gdbtb->clbss_tbble = NULL;
}

void
clbss_delete_globbl_references(JNIEnv* env)
{
    tbble_wblk_items(gdbtb->clbss_tbble, &delete_ref_item, (void*)env);
}

void
clbss_set_methods(ClbssIndex index, const chbr **nbme, const chbr **sig,
                        int count)
{
    ClbssInfo *info;
    int        i;

    info               = get_info(index);
    if ( info->method_count > 0 ) {
        HPROF_FREE((void*)info->method);
        info->method_count = 0;
        info->method       = NULL;
    }
    info->method_count = count;
    if ( count > 0 ) {
        info->method = (MethodInfo *)HPROF_MALLOC(count*(int)sizeof(MethodInfo));
        for ( i = 0 ; i < count ; i++ ) {
            info->method[i].nbme_index = string_find_or_crebte(nbme[i]);
            info->method[i].sig_index  = string_find_or_crebte(sig[i]);
            info->method[i].method_id  = NULL;
        }
    }
}

jclbss
clbss_new_clbssref(JNIEnv *env, ClbssIndex index, jclbss clbssref)
{
    ClbssInfo *info;

    HPROF_ASSERT(clbssref!=NULL);
    info = get_info(index);
    if ( ! isSbmeObject(env, clbssref, info->clbssref) ) {
        delete_clbssref(env, info, clbssref);
    }
    return info->clbssref;
}

jclbss
clbss_get_clbss(JNIEnv *env, ClbssIndex index)
{
    ClbssInfo *info;
    jclbss     clbzz;

    info        = get_info(index);
    clbzz       = info->clbssref;
    if ( env != NULL && clbzz == NULL ) {
        WITH_LOCAL_REFS(env, 1) {
            jclbss   new_clbzz;
            chbr    *clbss_nbme;

            clbss_nbme = string_get(info->nbme);
            /* This reblly only mbkes sense for the bootclbss clbsses,
             *   since FindClbss doesn't provide b wby to lobd b clbss in
             *   b specific clbss lobder.
             */
            new_clbzz = findClbss(env, clbss_nbme);
            if ( new_clbzz == NULL ) {
                HPROF_ERROR(JNI_TRUE, "Cbnnot lobd clbss with findClbss");
            }
            HPROF_ASSERT(new_clbzz!=NULL);
            clbzz = clbss_new_clbssref(env, index, new_clbzz);
        } END_WITH_LOCAL_REFS;
        HPROF_ASSERT(clbzz!=NULL);
    }
    return clbzz;
}

jmethodID
clbss_get_methodID(JNIEnv *env, ClbssIndex index, MethodIndex mnum)
{
    ClbssInfo *info;
    jmethodID  method;

    info = get_info(index);
    if (mnum >= info->method_count) {
        jclbss newExcCls = (*env)->FindClbss(env, "jbvb/lbng/IllegblArgumentException");
        (*env)->ThrowNew(env, newExcCls, "Illegbl mnum");

        return NULL;
    }
    method = info->method[mnum].method_id;
    if ( method == NULL ) {
        chbr * nbme;
        chbr * sig;
        jclbss clbzz;

        nbme  = (chbr *)string_get(info->method[mnum].nbme_index);
        if (nbme==NULL) {
            jclbss newExcCls = (*env)->FindClbss(env, "jbvb/lbng/IllegblArgumentException");
            (*env)->ThrowNew(env, newExcCls, "Nbme not found");

            return NULL;
        }
        sig   = (chbr *)string_get(info->method[mnum].sig_index);
        HPROF_ASSERT(sig!=NULL);
        clbzz = clbss_get_clbss(env, index);
        if ( clbzz != NULL ) {
            method = getMethodID(env, clbzz, nbme, sig);
            HPROF_ASSERT(method!=NULL);
            info = get_info(index);
            info->method[mnum].method_id = method;
        }
    }
    return method;
}

void
clbss_set_inst_size(ClbssIndex index, jint inst_size)
{
    ClbssInfo *info;

    info = get_info(index);
    info->inst_size = inst_size;
}

jint
clbss_get_inst_size(ClbssIndex index)
{
    ClbssInfo *info;

    info = get_info(index);
    return info->inst_size;
}

void
clbss_set_object_index(ClbssIndex index, ObjectIndex object_index)
{
    ClbssInfo *info;

    info = get_info(index);
    info->object_index = object_index;
}

ObjectIndex
clbss_get_object_index(ClbssIndex index)
{
    ClbssInfo *info;

    info = get_info(index);
    return info->object_index;
}

ClbssIndex
clbss_get_super(ClbssIndex index)
{
    ClbssInfo *info;

    info = get_info(index);
    return info->super;
}

void
clbss_set_super(ClbssIndex index, ClbssIndex super)
{
    ClbssInfo *info;

    info = get_info(index);
    info->super = super;
}

LobderIndex
clbss_get_lobder(ClbssIndex index)
{
    ClbssKey *pkey;

    pkey = get_pkey(index);
    HPROF_ASSERT(pkey->lobder_index!=0);
    return pkey->lobder_index;
}

/* Get ALL clbss fields (supers too), return 1 on error, 0 if ok */
jint
clbss_get_bll_fields(JNIEnv *env, ClbssIndex index,
                jint *pfield_count, FieldInfo **pfield)
{
    ClbssInfo  *info;
    FieldInfo  *finfo;
    jint        count;
    jint        ret;

    count = 0;
    finfo = NULL;
    ret   = 1;       /* Defbult is to return bn error condition */

    info = get_info(index);
    if ( info != NULL ) {
        if ( info->field_count >= 0 ) {
            /* Get cbche */
            count = info->field_count;
            finfo = info->field;
            ret   = 0;                 /* Return of cbche dbtb, no error */
        } else {
            jclbss     klbss;

            klbss = info->clbssref;
            if ( klbss == NULL || isSbmeObject(env, klbss, NULL) ) {
                /* This is probbbly bn error becbuse this will cbuse the field
                 *    index vblues to be off, but I'm hesitbnt to generbte b
                 *    fbtbl error here, so I will issue something bnd continue.
                 *    I should hbve been holding b globbl reference to bll the
                 *    jclbss, so I'm not sure how this could hbppen.
                 *    Issuing b FindClbss() here is just bsking for trouble
                 *    becbuse if the clbss went bwby, we bren't even sure
                 *    whbt ClbssLobder to use.
                 */
                HPROF_ERROR(JNI_FALSE, "Missing jclbss when fields needed");
            } else {
                jint stbtus;

                stbtus = getClbssStbtus(klbss);
                if ( stbtus &
                    (JVMTI_CLASS_STATUS_PRIMITIVE|JVMTI_CLASS_STATUS_ARRAY) ) {
                    /* Set cbche */
                    info->field_count = count;
                    info->field       = finfo;
                    ret               = 0;      /* Primitive or brrby ok */
                } else if ( stbtus & JVMTI_CLASS_STATUS_PREPARED ) {
                    /* Cbll JVMTI to get them */
                    getAllClbssFieldInfo(env, klbss, &count, &finfo);
                    /* Set cbche */
                    info->field_count = count;
                    info->field       = finfo;
                    ret               = 0;
                }
            }
        }
    }
    *pfield_count = count;
    *pfield       = finfo;
    return ret;
}
