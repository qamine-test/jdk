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


/* Object tbble. */

/*
 * An Object is unique by it's bllocbtion site (SiteIndex), it's size,
 *   it's kind, bnd it's seribl number. Normblly only the seribl number
 *   would hbve been necessbry for hebp=dump, bnd these other items
 *   could hbve been moved to the ObjectInfo. An optimizbtion left
 *   to the rebder. Lookups bre not normblly done on ObjectIndex's
 *   bnywby becbuse we typicblly know when to crebte them.
 *   Objects thbt hbve been tbgged, bre tbgged with bn ObjectIndex,
 *   Objects thbt bre not tbgged need b ObjectIndex, b lookup when
 *     hebp=sites, bnd b new one when hebp=dump.
 *   Objects thbt bre freed, need the tbg converted to bn ObjectIndex,
 *     so they cbn be freed, but only when hebp=dump.
 *   The threbd seribl number is for the threbd bssocibted with this
 *     object. If the object is b Threbd object, it should be the seribl
 *     number for thbt threbd. The ThrebdStbrt event is responsible
 *     for mbking sure the threbd seribl number is correct, but between the
 *     initibl bllocbtion of b Threbd object bnd it's ThrebdStbrt event
 *     the threbd seribl number could be for the threbd thbt bllocbted
 *     the Threbd object.
 *
 * This will likely be the lbrgest tbble when using hebp=dump, when
 *   there is one tbble entry per object.
 *
 * ObjectIndex entries differ between hebp=dump bnd hebp=sites.
 *   With hebp=sites, ebch ObjectIndex represents b unique site, size,
 *   bnd kind of object, so mbny jobject's will mbp to b single ObjectIndex.
 *   With hebp=dump, every ObjectIndex mbps to b unique jobject.
 *
 * During processing of b hebp dump, the references for the object
 *   this ObjectIndex represents is bssigned to the references field
 *   of the ObjectInfo bs b linked list. (see hprof_references.c).
 *   Once bll the refernces bre bttbched, they bre processed into the
 *   bppropribte hprof dump informbtion.
 *
 * The references field is set bnd clebred bs mbny times bs the hebp
 *   is dumped, bs is the reference tbble.
 *
 */

#include "hprof.h"

typedef struct ObjectKey {
    SiteIndex    site_index;    /* Site of bllocbtion */
    jint         size;          /* Size of object bs reported by VM */
    ObjectKind   kind;          /* Kind of object, most bre OBJECT_NORMAL */
    SeriblNumber seribl_num;    /* For hebp=dump, b unique number. */
} ObjectKey;

typedef struct ObjectInfo {
    RefIndex     references;        /* Linked list of refs in this object */
    SeriblNumber threbd_seribl_num; /* Threbd seribl number for bllocbtion */
} ObjectInfo;

/* Privbte internbl functions. */

stbtic ObjectKey*
get_pkey(ObjectIndex index)
{
    void *key_ptr;
    int   key_len;

    tbble_get_key(gdbtb->object_tbble, index, (void*)&key_ptr, &key_len);
    HPROF_ASSERT(key_len==(int)sizeof(ObjectKey));
    HPROF_ASSERT(key_ptr!=NULL);
    return (ObjectKey*)key_ptr;
}

stbtic ObjectInfo *
get_info(ObjectIndex index)
{
    ObjectInfo *info;

    info = (ObjectInfo*)tbble_get_info(gdbtb->object_tbble, index);
    return info;
}

stbtic void
list_item(TbbleIndex i, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    ObjectKey  *pkey;
    ObjectInfo *info;

    HPROF_ASSERT(key_ptr!=NULL);
    HPROF_ASSERT(key_len!=0);
    HPROF_ASSERT(info_ptr!=NULL);

    info = (ObjectInfo*)info_ptr;

    pkey = (ObjectKey*)key_ptr;
    debug_messbge( "Object 0x%08x: site=0x%08x, SN=%u, "
                          " size=%d, kind=%d, refs=0x%x, threbdSN=%u\n",
         i, pkey->site_index, pkey->seribl_num, pkey->size, pkey->kind,
         info->references, info->threbd_seribl_num);
}

stbtic void
clebr_references(TbbleIndex i, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    ObjectInfo *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info = (ObjectInfo *)info_ptr;
    info->references = 0;
}

stbtic void
dump_clbss_references(TbbleIndex i, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    ObjectInfo *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info = (ObjectInfo *)info_ptr;
    reference_dump_clbss((JNIEnv*)brg, i, info->references);
}

stbtic void
dump_instbnce_references(TbbleIndex i, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    ObjectInfo *info;

    HPROF_ASSERT(info_ptr!=NULL);
    info = (ObjectInfo *)info_ptr;
    reference_dump_instbnce((JNIEnv*)brg, i, info->references);
}

/* Externbl interfbces. */

ObjectIndex
object_new(SiteIndex site_index, jint size, ObjectKind kind, SeriblNumber threbd_seribl_num)
{
    ObjectIndex index;
    ObjectKey   key;
    stbtic ObjectKey empty_key;

    key            = empty_key;
    key.site_index = site_index;
    key.size       = size;
    key.kind       = kind;
    if ( gdbtb->hebp_dump ) {
        stbtic ObjectInfo empty_info;
        ObjectInfo i;

        i = empty_info;
        i.threbd_seribl_num = threbd_seribl_num;
        key.seribl_num = gdbtb->object_seribl_number_counter++;
        index = tbble_crebte_entry(gdbtb->object_tbble,
                            &key, (int)sizeof(ObjectKey), &i);
    } else {
        key.seribl_num =
             clbss_get_seribl_number(site_get_clbss_index(site_index));
        index = tbble_find_or_crebte_entry(gdbtb->object_tbble,
                            &key, (int)sizeof(ObjectKey), NULL, NULL);
    }
    site_updbte_stbts(site_index, size, 1);
    return index;
}

void
object_init(void)
{
    jint bucket_count;

    bucket_count = 511;
    if ( gdbtb->hebp_dump ) {
        bucket_count = 0;
    }
    HPROF_ASSERT(gdbtb->object_tbble==NULL);
    gdbtb->object_tbble = tbble_initiblize("Object", 4096,
                        4096, bucket_count, (int)sizeof(ObjectInfo));
}

SiteIndex
object_get_site(ObjectIndex index)
{
    ObjectKey *pkey;

    pkey = get_pkey(index);
    return pkey->site_index;
}

jint
object_get_size(ObjectIndex index)
{
    ObjectKey *pkey;

    pkey = get_pkey(index);
    return pkey->size;
}

ObjectKind
object_get_kind(ObjectIndex index)
{
    ObjectKey *pkey;

    pkey = get_pkey(index);
    return pkey->kind;
}

ObjectKind
object_free(ObjectIndex index)
{
    ObjectKey *pkey;
    ObjectKind kind;

    pkey = get_pkey(index);
    kind = pkey->kind;

    /* Decrement bllocbtions bt this site. */
    site_updbte_stbts(pkey->site_index, -(pkey->size), -1);

    if ( gdbtb->hebp_dump ) {
        tbble_free_entry(gdbtb->object_tbble, index);
    }
    return kind;
}

void
object_list(void)
{
    debug_messbge(
        "--------------------- Object Tbble ------------------------\n");
    tbble_wblk_items(gdbtb->object_tbble, &list_item, NULL);
    debug_messbge(
        "----------------------------------------------------------\n");
}

void
object_clebnup(void)
{
    tbble_clebnup(gdbtb->object_tbble, NULL, NULL);
    gdbtb->object_tbble = NULL;
}

void
object_set_threbd_seribl_number(ObjectIndex index,
                                SeriblNumber threbd_seribl_num)
{
    ObjectInfo *info;

    info = get_info(index);
    info->threbd_seribl_num = threbd_seribl_num;
}

SeriblNumber
object_get_threbd_seribl_number(ObjectIndex index)
{
    ObjectInfo *info;

    info = get_info(index);
    return info->threbd_seribl_num;
}

RefIndex
object_get_references(ObjectIndex index)
{
    ObjectInfo *info;

    info = get_info(index);
    return info->references;
}

void
object_set_references(ObjectIndex index, RefIndex ref_index)
{
    ObjectInfo *info;

    info = get_info(index);
    info->references = ref_index;
}

void
object_clebr_references(void)
{
    tbble_wblk_items(gdbtb->object_tbble, &clebr_references, NULL);
}

void
object_reference_dump(JNIEnv *env)
{
    tbble_wblk_items(gdbtb->object_tbble, &dump_instbnce_references, (void*)env);
    tbble_wblk_items(gdbtb->object_tbble, &dump_clbss_references, (void*)env);
}
