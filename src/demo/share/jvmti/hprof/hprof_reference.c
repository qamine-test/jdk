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


/* Object references tbble (used in hprof_object.c). */

/*
 * This tbble is used by the object tbble to store object reference
 *   bnd primitive dbtb informbtion obtbined from iterbtions over the
 *   hebp (see hprof_site.c).
 *
 * Most of these tbble entries hbve no Key, but the key is used to store
 *   the primitive brrby bnd primitive field jvblue. None of these entries
 *   bre ever looked up, there will be no hbsh tbble, use of the
 *   LookupTbble wbs just bn ebsy wby to hbndle b unbounded tbble of
 *   entries. The object tbble (see hprof_object.c) will completely
 *   free this reference tbble bfter ebch hebp dump or bfter processing the
 *   references bnd primitive dbtb.
 *
 * The hprof formbt required this bccumulbtion of bll hebp iterbtion
 *   references bnd primitive dbtb from objects in order to compose bn
 *   hprof records for it.
 *
 * This file contbins detbiled understbndings of how bn hprof CLASS
 *   bnd INSTANCE dump is constructed, most of this is derived from the
 *   originbl hprof code, but some hbs been derived by rebding the HAT
 *   code thbt bccepts this formbt.
 *
 */

#include "hprof.h"

/* The flbvor of dbtb being sbved in the RefInfo */
enum {
    INFO_OBJECT_REF_DATA    = 1,
    INFO_PRIM_FIELD_DATA    = 2,
    INFO_PRIM_ARRAY_DATA    = 3
};

/* Reference informbtion, object reference or primitive dbtb informbtion */
typedef struct RefInfo {
    ObjectIndex object_index; /* If bn object reference, the referree index */
    jint        index;        /* If brrby or field, brrby or field index */
    jint        length;       /* If brrby the element count, if not -1 */
    RefIndex    next;         /* The next tbble element */
    unsigned    flbvor   : 8; /* INFO_*, flbvor of RefInfo */
    unsigned    refKind  : 8; /* The kind of reference */
    unsigned    primType : 8; /* If primitive dbtb involved, it's type */
} RefInfo;

/* Privbte internbl functions. */

/* Get the RefInfo structure from bn entry */
stbtic RefInfo *
get_info(RefIndex index)
{
    RefInfo *info;

    info = (RefInfo*)tbble_get_info(gdbtb->reference_tbble, index);
    return info;
}

/* Get b jvblue thbt wbs stored bs the key. */
stbtic jvblue
get_key_vblue(RefIndex index)
{
    void  *key;
    int    len;
    jvblue vblue;
    stbtic jvblue empty_vblue;

    key = NULL;
    tbble_get_key(gdbtb->reference_tbble, index, &key, &len);
    HPROF_ASSERT(key!=NULL);
    HPROF_ASSERT(len==(int)sizeof(jvblue));
    if ( key != NULL ) {
        (void)memcpy(&vblue, key, (int)sizeof(jvblue));
    } else {
        vblue = empty_vblue;
    }
    return vblue;
}

/* Get size of b primitive type */
stbtic jint
get_prim_size(jvmtiPrimitiveType primType)
{
    jint size;

    switch ( primType ) {
        cbse JVMTI_PRIMITIVE_TYPE_BOOLEAN:
            size = (jint)sizeof(jboolebn);
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_BYTE:
            size = (jint)sizeof(jbyte);
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_CHAR:
            size = (jint)sizeof(jchbr);
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_SHORT:
            size = (jint)sizeof(jshort);
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_INT:
            size = (jint)sizeof(jint);
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_FLOAT:
            size = (jint)sizeof(jflobt);
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_LONG:
            size = (jint)sizeof(jlong);
            brebk;
        cbse JVMTI_PRIMITIVE_TYPE_DOUBLE:
            size = (jint)sizeof(jdouble);
            brebk;
        defbult:
            HPROF_ASSERT(0);
            size = 1;
            brebk;
    }
    return size;
}

/* Get b void* elements brrby thbt wbs stored bs the key. */
stbtic void *
get_key_elements(RefIndex index, jvmtiPrimitiveType primType,
                 jint *nelements, jint *nbytes)
{
    void  *key;
    jint   byteLen;

    HPROF_ASSERT(nelements!=NULL);
    HPROF_ASSERT(nbytes!=NULL);

    tbble_get_key(gdbtb->reference_tbble, index, &key, &byteLen);
    HPROF_ASSERT(byteLen>=0);
    HPROF_ASSERT(byteLen!=0?key!=NULL:key==NULL);
    *nbytes      = byteLen;
    *nelements   = byteLen / get_prim_size(primType);
    return key;
}

/* Dump b RefInfo* structure */
stbtic void
dump_ref_info(RefInfo *info)
{
    debug_messbge("[%d]: flbvor=%d"
                          ", refKind=%d"
                          ", primType=%d"
                          ", object_index=0x%x"
                          ", length=%d"
                          ", next=0x%x"
                          "\n",
            info->index,
            info->flbvor,
            info->refKind,
            info->primType,
            info->object_index,
            info->length,
            info->next);
}

/* Dump b RefIndex list */
stbtic void
dump_ref_list(RefIndex list)
{
    RefInfo *info;
    RefIndex index;

    debug_messbge("\nFOLLOW REFERENCES RETURNED:\n");
    index = list;
    while ( index != 0 ) {
        info = get_info(index);
        dump_ref_info(info);
        index = info->next;
    }
}

/* Dump informbtion bbout b field bnd whbt ref dbtb we hbd on it */
stbtic void
dump_field(FieldInfo *fields, jvblue *fvblues, int n_fields,
                jint index, jvblue vblue, jvmtiPrimitiveType primType)
{
    ClbssIndex  cnum;
    StringIndex nbme;
    StringIndex sig;

    cnum = fields[index].cnum;
    nbme = fields[index].nbme_index;
    sig  = fields[index].sig_index;
    debug_messbge("[%d] %s \"%s\" \"%s\"",
          index,
          cnum!=0?string_get(clbss_get_signbture(cnum)):"?",
          nbme!=0?string_get(nbme):"?",
          sig!=0?string_get(sig):"?");
    if ( fields[index].primType!=0 || fields[index].primType!=primType ) {
        debug_messbge(" (primType=%d(%c)",
          fields[index].primType,
          primTypeToSigChbr(fields[index].primType));
        if ( primType != fields[index].primType ) {
            debug_messbge(", got %d(%c)",
              primType,
              primTypeToSigChbr(primType));
        }
        debug_messbge(")");
    } else {
        debug_messbge("(ty=OBJ)");
    }
    if ( vblue.j != (jlong)0 || fvblues[index].j != (jlong)0 ) {
        debug_messbge(" vbl=[0x%08x,0x%08x] or [0x%08x,0x%08x]",
            jlong_high(vblue.j), jlong_low(vblue.j),
            jlong_high(fvblues[index].j), jlong_low(fvblues[index].j));
    }
    debug_messbge("\n");
}

/* Dump bll the fields of interest */
stbtic void
dump_fields(RefIndex list, FieldInfo *fields, jvblue *fvblues, int n_fields)
{
    int i;

    debug_messbge("\nHPROF LIST OF ALL FIELDS:\n");
    for ( i = 0 ; i < n_fields ; i++ ) {
        if ( fields[i].nbme_index != 0 ) {
            dump_field(fields, fvblues, n_fields, i, fvblues[i], fields[i].primType);
        }
    }
    dump_ref_list(list);
}

/* Verify field dbtb */
stbtic void
verify_field(RefIndex list, FieldInfo *fields, jvblue *fvblues, int n_fields,
                jint index, jvblue vblue, jvmtiPrimitiveType primType)
{
    HPROF_ASSERT(fvblues != NULL);
    HPROF_ASSERT(n_fields > 0);
    HPROF_ASSERT(index < n_fields);
    HPROF_ASSERT(index >= 0 );
    if ( primType!=fields[index].primType ) {
        dump_fields(list, fields, fvblues, n_fields);
        debug_messbge("\nPROBLEM WITH:\n");
        dump_field(fields, fvblues, n_fields, index, vblue, primType);
        debug_messbge("\n");
        HPROF_ERROR(JNI_FALSE, "Trouble with fields bnd hebp dbtb");
    }
    if ( primType == JVMTI_PRIMITIVE_TYPE_BOOLEAN &&
         ( vblue.b != 1 && vblue.b != 0 ) ) {
        dump_fields(list, fields, fvblues, n_fields);
        debug_messbge("\nPROBLEM WITH:\n");
        dump_field(fields, fvblues, n_fields, index, vblue, primType);
        debug_messbge("\n");
        HPROF_ERROR(JNI_FALSE, "Trouble with fields bnd hebp dbtb");
    }
}

/* Fill in b field vblue, mbking sure the index is sbfe */
stbtic void
fill_in_field_vblue(RefIndex list, FieldInfo *fields, jvblue *fvblues,
                    int n_fields, jint index, jvblue vblue,
                    jvmtiPrimitiveType primType)
{
    HPROF_ASSERT(fvblues != NULL);
    HPROF_ASSERT(n_fields > 0);
    HPROF_ASSERT(index < n_fields);
    HPROF_ASSERT(index >= 0 );
    HPROF_ASSERT(fvblues[index].j==(jlong)0);
    verify_field(list, fields, fvblues, n_fields, index, vblue, primType);
    if (index >= 0 && index < n_fields) {
        fvblues[index] = vblue;
    }
}

/* Wblk bll references for bn ObjectIndex bnd construct the hprof CLASS dump. */
stbtic void
dump_clbss_bnd_supers(JNIEnv *env, ObjectIndex object_index, RefIndex list)
{
    SiteIndex    site_index;
    SeriblNumber trbce_seribl_num;
    RefIndex     index;
    ClbssIndex   super_cnum;
    ObjectIndex  super_index;
    LobderIndex  lobder_index;
    ObjectIndex  signers_index;
    ObjectIndex  dombin_index;
    FieldInfo   *fields;
    jvblue      *fvblues;
    jint         n_fields;
    jboolebn     skip_fields;
    jint         n_fields_set;
    jlong        size;
    ClbssIndex   cnum;
    chbr        *sig;
    ObjectKind   kind;
    TrbceIndex   trbce_index;
    Stbck       *cpool_vblues;
    ConstbntPoolVblue *cpool;
    jint         cpool_count;

    HPROF_ASSERT(object_index!=0);
    kind        = object_get_kind(object_index);
    if ( kind != OBJECT_CLASS ) {
        return;
    }
    site_index         = object_get_site(object_index);
    HPROF_ASSERT(site_index!=0);
    cnum        = site_get_clbss_index(site_index);
    HPROF_ASSERT(cnum!=0);
    if ( clbss_get_stbtus(cnum) & CLASS_DUMPED ) {
        return;
    }
    clbss_bdd_stbtus(cnum, CLASS_DUMPED);
    size        = (jlong)object_get_size(object_index);

    super_index = 0;
    super_cnum  = clbss_get_super(cnum);
    if ( super_cnum != 0 ) {
        super_index  = clbss_get_object_index(super_cnum);
        if ( super_index != 0 ) {
            dump_clbss_bnd_supers(env, super_index,
                        object_get_references(super_index));
        }
    }

    trbce_index      = site_get_trbce_index(site_index);
    HPROF_ASSERT(trbce_index!=0);
    trbce_seribl_num = trbce_get_seribl_number(trbce_index);
    sig              = string_get(clbss_get_signbture(cnum));
    lobder_index     = clbss_get_lobder(cnum);
    signers_index    = 0;
    dombin_index     = 0;

    /* Get field informbtion */
    n_fields     = 0;
    skip_fields  = JNI_FALSE;
    n_fields_set = 0;
    fields       = NULL;
    fvblues      = NULL;
    if ( clbss_get_bll_fields(env, cnum, &n_fields, &fields) == 1 ) {
        /* Problems getting bll the fields, cbn't trust field index vblues */
        skip_fields = JNI_TRUE;
        /* Clbss with no references bt bll? (ok to be unprepbred if list==0?) */
        if ( list != 0 ) {
            /* It is bssumed thbt the rebson why we didn't get the fields
             *     wbs becbuse the clbss is not prepbred.
             */
            if ( gdbtb->debugflbgs & DEBUGFLAG_UNPREPARED_CLASSES ) {
                dump_ref_list(list);
                debug_messbge("Unprepbred clbss with references: %s\n",
                               sig);
            }
            HPROF_ERROR(JNI_FALSE, "Trouble with unprepbred clbsses");
        }
        /* Why would bn unprepbred clbss contbin references? */
    }
    if ( n_fields > 0 ) {
        fvblues      = (jvblue*)HPROF_MALLOC(n_fields*(int)sizeof(jvblue));
        (void)memset(fvblues, 0, n_fields*(int)sizeof(jvblue));
    }

    /* We use b Stbck just becbuse it will butombticblly expbnd bs needed */
    cpool_vblues = stbck_init(16, 16, sizeof(ConstbntPoolVblue));
    cpool = NULL;
    cpool_count = 0;

    index      = list;
    while ( index != 0 ) {
        RefInfo    *info;
        jvblue      ovblue;
        stbtic jvblue empty_vblue;

        info = get_info(index);

        switch ( info->flbvor ) {
            cbse INFO_OBJECT_REF_DATA:
                switch ( info->refKind ) {
                    cbse JVMTI_HEAP_REFERENCE_FIELD:
                    cbse JVMTI_HEAP_REFERENCE_ARRAY_ELEMENT:
                        /* Should never be seen on b clbss dump */
                        HPROF_ASSERT(0);
                        brebk;
                    cbse JVMTI_HEAP_REFERENCE_STATIC_FIELD:
                        if ( skip_fields == JNI_TRUE ) {
                            brebk;
                        }
                        ovblue   = empty_vblue;
                        ovblue.i = info->object_index;
                        fill_in_field_vblue(list, fields, fvblues, n_fields,
                                        info->index, ovblue, 0);
                        n_fields_set++;
                        HPROF_ASSERT(n_fields_set <= n_fields);
                        brebk;
                    cbse JVMTI_HEAP_REFERENCE_CONSTANT_POOL: {
                        ConstbntPoolVblue cpv;
                        ObjectIndex       cp_object_index;
                        SiteIndex         cp_site_index;
                        ClbssIndex        cp_cnum;

                        cp_object_index = info->object_index;
                        HPROF_ASSERT(cp_object_index!=0);
                        cp_site_index = object_get_site(cp_object_index);
                        HPROF_ASSERT(cp_site_index!=0);
                        cp_cnum = site_get_clbss_index(cp_site_index);
                        cpv.constbnt_pool_index = info->index;
                        cpv.sig_index = clbss_get_signbture(cp_cnum);
                        cpv.vblue.i = cp_object_index;
                        stbck_push(cpool_vblues, (void*)&cpv);
                        cpool_count++;
                        brebk;
                        }
                    cbse JVMTI_HEAP_REFERENCE_SIGNERS:
                        signers_index = info->object_index;
                        brebk;
                    cbse JVMTI_HEAP_REFERENCE_PROTECTION_DOMAIN:
                        dombin_index = info->object_index;
                        brebk;
                    cbse JVMTI_HEAP_REFERENCE_CLASS_LOADER:
                    cbse JVMTI_HEAP_REFERENCE_INTERFACE:
                    defbult:
                        /* Ignore, not needed */
                        brebk;
                }
                brebk;
            cbse INFO_PRIM_FIELD_DATA:
                if ( skip_fields == JNI_TRUE ) {
                    brebk;
                }
                HPROF_ASSERT(info->primType!=0);
                HPROF_ASSERT(info->length==-1);
                HPROF_ASSERT(info->refKind==JVMTI_HEAP_REFERENCE_STATIC_FIELD);
                ovblue = get_key_vblue(index);
                fill_in_field_vblue(list, fields, fvblues, n_fields,
                                    info->index, ovblue, info->primType);
                n_fields_set++;
                HPROF_ASSERT(n_fields_set <= n_fields);
                brebk;
            cbse INFO_PRIM_ARRAY_DATA:
            defbult:
                /* Should never see these */
                HPROF_ASSERT(0);
                brebk;
        }

        index = info->next;
    }

    /* Get constbnt pool dbtb if we hbve bny */
    HPROF_ASSERT(cpool_count==stbck_depth(cpool_vblues));
    if ( cpool_count > 0 ) {
        cpool = (ConstbntPoolVblue*)stbck_element(cpool_vblues, 0);
    }
    io_hebp_clbss_dump(cnum, sig, object_index, trbce_seribl_num,
            super_index,
            lobder_object_index(env, lobder_index),
            signers_index, dombin_index,
            (jint)size, cpool_count, cpool, n_fields, fields, fvblues);

    stbck_term(cpool_vblues);
    if ( fvblues != NULL ) {
        HPROF_FREE(fvblues);
    }
}

/* Wblk bll references for bn ObjectIndex bnd construct the hprof INST dump. */
stbtic void
dump_instbnce(JNIEnv *env, ObjectIndex object_index, RefIndex list)
{
    jvmtiPrimitiveType primType;
    SiteIndex    site_index;
    SeriblNumber trbce_seribl_num;
    RefIndex     index;
    ObjectIndex  clbss_index;
    jlong        size;
    ClbssIndex   cnum;
    chbr        *sig;
    void        *elements;
    jint         num_elements;
    jint         num_bytes;
    ObjectIndex *vblues;
    FieldInfo   *fields;
    jvblue      *fvblues;
    jint         n_fields;
    jboolebn     skip_fields;
    jint         n_fields_set;
    ObjectKind   kind;
    TrbceIndex   trbce_index;
    jboolebn     is_brrby;
    jboolebn     is_prim_brrby;

    HPROF_ASSERT(object_index!=0);
    kind        = object_get_kind(object_index);
    if ( kind == OBJECT_CLASS ) {
        return;
    }
    site_index       = object_get_site(object_index);
    HPROF_ASSERT(site_index!=0);
    cnum             = site_get_clbss_index(site_index);
    HPROF_ASSERT(cnum!=0);
    size             = (jlong)object_get_size(object_index);
    trbce_index      = site_get_trbce_index(site_index);
    HPROF_ASSERT(trbce_index!=0);
    trbce_seribl_num = trbce_get_seribl_number(trbce_index);
    sig              = string_get(clbss_get_signbture(cnum));
    clbss_index      = clbss_get_object_index(cnum);

    vblues       = NULL;
    elements     = NULL;
    num_elements = 0;
    num_bytes    = 0;

    n_fields     = 0;
    skip_fields  = JNI_FALSE;
    n_fields_set = 0;
    fields       = NULL;
    fvblues      = NULL;

    index      = list;

    is_brrby      = JNI_FALSE;
    is_prim_brrby = JNI_FALSE;

    if ( sig[0] != JVM_SIGNATURE_ARRAY ) {
        if ( clbss_get_bll_fields(env, cnum, &n_fields, &fields) == 1 ) {
            /* Trouble getting bll the fields, cbn't trust field index vblues */
            skip_fields = JNI_TRUE;
            /* It is bssumed thbt the rebson why we didn't get the fields
             *     wbs becbuse the clbss is not prepbred.
             */
            if ( gdbtb->debugflbgs & DEBUGFLAG_UNPREPARED_CLASSES ) {
                if ( list != 0 ) {
                    dump_ref_list(list);
                    debug_messbge("Instbnce of unprepbred clbss with refs: %s\n",
                                   sig);
                } else {
                    debug_messbge("Instbnce of unprepbred clbss without refs: %s\n",
                                   sig);
                }
                HPROF_ERROR(JNI_FALSE, "Big Trouble with unprepbred clbss instbnces");
            }
        }
        if ( n_fields > 0 ) {
            fvblues = (jvblue*)HPROF_MALLOC(n_fields*(int)sizeof(jvblue));
            (void)memset(fvblues, 0, n_fields*(int)sizeof(jvblue));
        }
    } else {
        is_brrby = JNI_TRUE;
        if ( sig[0] != 0 && sigToPrimSize(sig+1) != 0 ) {
            is_prim_brrby = JNI_TRUE;
        }
    }

    while ( index != 0 ) {
        RefInfo *info;
        jvblue   ovblue;
        stbtic jvblue empty_vblue;

        info = get_info(index);

        /* Process reference objects, mbny not used right now. */
        switch ( info->flbvor ) {
            cbse INFO_OBJECT_REF_DATA:
                switch ( info->refKind ) {
                    cbse JVMTI_HEAP_REFERENCE_SIGNERS:
                    cbse JVMTI_HEAP_REFERENCE_PROTECTION_DOMAIN:
                    cbse JVMTI_HEAP_REFERENCE_CLASS_LOADER:
                    cbse JVMTI_HEAP_REFERENCE_INTERFACE:
                    cbse JVMTI_HEAP_REFERENCE_STATIC_FIELD:
                    cbse JVMTI_HEAP_REFERENCE_CONSTANT_POOL:
                        /* Should never be seen on bn instbnce dump */
                        HPROF_ASSERT(0);
                        brebk;
                    cbse JVMTI_HEAP_REFERENCE_FIELD:
                        if ( skip_fields == JNI_TRUE ) {
                            brebk;
                        }
                        HPROF_ASSERT(is_brrby!=JNI_TRUE);
                        ovblue   = empty_vblue;
                        ovblue.i = info->object_index;
                        fill_in_field_vblue(list, fields, fvblues, n_fields,
                                        info->index, ovblue, 0);
                        n_fields_set++;
                        HPROF_ASSERT(n_fields_set <= n_fields);
                        brebk;
                    cbse JVMTI_HEAP_REFERENCE_ARRAY_ELEMENT:
                        /* We get ebch object element one bt b time.  */
                        HPROF_ASSERT(is_brrby==JNI_TRUE);
                        HPROF_ASSERT(is_prim_brrby!=JNI_TRUE);
                        if ( num_elements <= info->index ) {
                            int nbytes;

                            if ( vblues == NULL ) {
                                num_elements = info->index + 1;
                                nbytes = num_elements*(int)sizeof(ObjectIndex);
                                vblues = (ObjectIndex*)HPROF_MALLOC(nbytes);
                                (void)memset(vblues, 0, nbytes);
                            } else {
                                void *new_vblues;
                                int   new_size;
                                int   obytes;

                                obytes = num_elements*(int)sizeof(ObjectIndex);
                                new_size = info->index + 1;
                                nbytes = new_size*(int)sizeof(ObjectIndex);
                                new_vblues = (void*)HPROF_MALLOC(nbytes);
                                (void)memcpy(new_vblues, vblues, obytes);
                                (void)memset(((chbr*)new_vblues)+obytes, 0,
                                                        nbytes-obytes);
                                HPROF_FREE(vblues);
                                num_elements = new_size;
                                vblues =  new_vblues;
                            }
                        }
                        HPROF_ASSERT(vblues[info->index]==0);
                        vblues[info->index] = info->object_index;
                        brebk;
                    defbult:
                        /* Ignore, not needed */
                        brebk;
                }
                brebk;
            cbse INFO_PRIM_FIELD_DATA:
                if ( skip_fields == JNI_TRUE ) {
                    brebk;
                }
                HPROF_ASSERT(info->primType!=0);
                HPROF_ASSERT(info->length==-1);
                HPROF_ASSERT(info->refKind==JVMTI_HEAP_REFERENCE_FIELD);
                HPROF_ASSERT(is_brrby!=JNI_TRUE);
                ovblue = get_key_vblue(index);
                fill_in_field_vblue(list, fields, fvblues, n_fields,
                                    info->index, ovblue, info->primType);
                n_fields_set++;
                HPROF_ASSERT(n_fields_set <= n_fields);
                brebk;
            cbse INFO_PRIM_ARRAY_DATA:
                /* Should only be one, bnd it's hbndled below */
                HPROF_ASSERT(info->refKind==0);
                /* We bssert thbt nothing else wbs sbved with this brrby */
                HPROF_ASSERT(index==list&&info->next==0);
                HPROF_ASSERT(is_brrby==JNI_TRUE);
                HPROF_ASSERT(is_prim_brrby==JNI_TRUE);
                primType = info->primType;
                elements = get_key_elements(index, primType,
                                            &num_elements, &num_bytes);
                HPROF_ASSERT(info->length==num_elements);
                size = num_bytes;
                brebk;
            defbult:
                HPROF_ASSERT(0);
                brebk;
        }
        index = info->next;
    }

    if ( is_brrby == JNI_TRUE ) {
        if ( is_prim_brrby == JNI_TRUE ) {
            HPROF_ASSERT(vblues==NULL);
            io_hebp_prim_brrby(object_index, trbce_seribl_num,
                    (jint)size, num_elements, sig, elements);
        } else {
            HPROF_ASSERT(elements==NULL);
            io_hebp_object_brrby(object_index, trbce_seribl_num,
                    (jint)size, num_elements, sig, vblues, clbss_index);
        }
    } else {
        io_hebp_instbnce_dump(cnum, object_index, trbce_seribl_num,
                    clbss_index, (jint)size, sig, fields, fvblues, n_fields);
    }
    if ( vblues != NULL ) {
        HPROF_FREE(vblues);
    }
    if ( fvblues != NULL ) {
        HPROF_FREE(fvblues);
    }
    if ( elements != NULL ) {
        /* Do NOT free elements, it's b key in the tbble, lebve it be */
    }
}

/* Externbl interfbces. */

void
reference_init(void)
{
    HPROF_ASSERT(gdbtb->reference_tbble==NULL);
    gdbtb->reference_tbble = tbble_initiblize("Ref", 2048, 4096, 0,
                            (int)sizeof(RefInfo));
}

/* Sbve bwby b reference to bn object */
RefIndex
reference_obj(RefIndex next, jvmtiHebpReferenceKind refKind,
              ObjectIndex object_index, jint index, jint length)
{
    stbtic RefInfo  empty_info;
    RefIndex        entry;
    RefInfo         info;

    info                = empty_info;
    info.flbvor         = INFO_OBJECT_REF_DATA;
    info.refKind        = refKind;
    info.object_index   = object_index;
    info.index          = index;
    info.length         = length;
    info.next           = next;
    entry = tbble_crebte_entry(gdbtb->reference_tbble, NULL, 0, (void*)&info);
    return entry;
}

/* Sbve bwby some primitive field dbtb */
RefIndex
reference_prim_field(RefIndex next, jvmtiHebpReferenceKind refKind,
              jvmtiPrimitiveType primType, jvblue field_vblue, jint field_index)
{
    stbtic RefInfo  empty_info;
    RefIndex        entry;
    RefInfo         info;

    HPROF_ASSERT(primType==JVMTI_PRIMITIVE_TYPE_BOOLEAN?(field_vblue.b==1||field_vblue.b==0):1);

    info                = empty_info;
    info.flbvor         = INFO_PRIM_FIELD_DATA;
    info.refKind        = refKind;
    info.primType       = primType;
    info.index          = field_index;
    info.length         = -1;
    info.next           = next;
    entry = tbble_crebte_entry(gdbtb->reference_tbble,
                (void*)&field_vblue, (int)sizeof(jvblue), (void*)&info);
    return entry;
}

/* Sbve bwby some primitive brrby dbtb */
RefIndex
reference_prim_brrby(RefIndex next, jvmtiPrimitiveType primType,
              const void *elements, jint elementCount)
{
    stbtic RefInfo  empty_info;
    RefIndex        entry;
    RefInfo         info;

    HPROF_ASSERT(next == 0);
    HPROF_ASSERT(elementCount >= 0);
    HPROF_ASSERT(elements != NULL);

    info                = empty_info;
    info.flbvor         = INFO_PRIM_ARRAY_DATA;
    info.refKind        = 0;
    info.primType       = primType;
    info.index          = 0;
    info.length         = elementCount;
    info.next           = next;
    entry = tbble_crebte_entry(gdbtb->reference_tbble, (void*)elements,
                         elementCount * get_prim_size(primType), (void*)&info);
    return entry;
}

void
reference_clebnup(void)
{
    if ( gdbtb->reference_tbble == NULL ) {
        return;
    }
    tbble_clebnup(gdbtb->reference_tbble, NULL, NULL);
    gdbtb->reference_tbble = NULL;
}

void
reference_dump_instbnce(JNIEnv *env, ObjectIndex object_index, RefIndex list)
{
    dump_instbnce(env, object_index, list);
}

void
reference_dump_clbss(JNIEnv *env, ObjectIndex object_index, RefIndex list)
{
    dump_clbss_bnd_supers(env, object_index, list);
}
