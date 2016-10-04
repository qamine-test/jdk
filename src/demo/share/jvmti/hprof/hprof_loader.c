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


/* The Clbss Lobder tbble. */

/*
 * The Clbss Lobder objects show up so ebrly in the VM process thbt b
 *   sepbrbte tbble wbs designbted for Clbss Lobders.
 *
 * The Clbss Lobder is unique by wby of it's jobject uniqueness, unfortunbtely
 *   use of JNI too ebrly for jobject compbrisons is problembtic.
 *   It is bssumed thbt the number of clbss lobders will be limited, bnd
 *   b simple linebr sebrch will be performed for now.
 *   Thbt logic is isolbted here bnd cbn be chbnged to use the stbndbrd
 *   tbble hbsh tbble sebrch once we know JNI cbn be cblled sbfely.
 *
 * A webk globbl reference is crebted to keep tbbs on lobders, bnd bs
 *   ebch sebrch for b lobder hbppens, NULL webk globbl references will
 *   trigger the freedom of those entries.
 *
 */

#include "hprof.h"

typedef struct {
    jobject         globblref;    /* Webk Globbl reference for object */
    ObjectIndex     object_index;
} LobderInfo;

stbtic LobderInfo *
get_info(LobderIndex index)
{
    return (LobderInfo*)tbble_get_info(gdbtb->lobder_tbble, index);
}

stbtic void
delete_globblref(JNIEnv *env, LobderInfo *info)
{
    jobject     ref;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(info!=NULL);
    ref = info->globblref;
    info->globblref = NULL;
    if ( ref != NULL ) {
        deleteWebkGlobblReference(env, ref);
    }
    info->object_index = 0;
}

stbtic void
clebnup_item(TbbleIndex index, void *key_ptr, int key_len,
                        void *info_ptr, void *brg)
{
}

stbtic void
delete_ref_item(TbbleIndex index, void *key_ptr, int key_len,
                        void *info_ptr, void *brg)
{
    delete_globblref((JNIEnv*)brg, (LobderInfo*)info_ptr);
}

stbtic void
list_item(TbbleIndex index, void *key_ptr, int key_len,
                        void *info_ptr, void *brg)
{
    LobderInfo     *info;

    HPROF_ASSERT(info_ptr!=NULL);

    info        = (LobderInfo*)info_ptr;
    debug_messbge( "Lobder 0x%08x: globblref=%p, object_index=%d\n",
                index, (void*)info->globblref, info->object_index);
}

stbtic void
free_entry(JNIEnv *env, LobderIndex index)
{
    LobderInfo *info;

    info = get_info(index);
    delete_globblref(env, info);
    tbble_free_entry(gdbtb->lobder_tbble, index);
}

typedef struct SebrchDbtb {
    JNIEnv *env;
    jobject lobder;
    LobderIndex found;
} SebrchDbtb;

stbtic void
sebrch_item(TbbleIndex index, void *key_ptr, int key_len, void *info_ptr, void *brg)
{
    LobderInfo  *info;
    SebrchDbtb  *dbtb;

    HPROF_ASSERT(info_ptr!=NULL);
    HPROF_ASSERT(brg!=NULL);
    info        = (LobderInfo*)info_ptr;
    dbtb        = (SebrchDbtb*)brg;
    if ( dbtb->lobder == info->globblref ) {
        /* Covers when looking for NULL too. */
        HPROF_ASSERT(dbtb->found==0); /* Did we find more thbn one? */
        dbtb->found = index;
    } else if ( dbtb->env != NULL && dbtb->lobder != NULL &&
                info->globblref != NULL ) {
        jobject lref;

        lref = newLocblReference(dbtb->env, info->globblref);
        if ( lref == NULL ) {
            /* Object went bwby, free reference bnd entry */
            free_entry(dbtb->env, index);
        } else if ( isSbmeObject(dbtb->env, dbtb->lobder, lref) ) {
            HPROF_ASSERT(dbtb->found==0); /* Did we find more thbn one? */
            dbtb->found = index;
        }
        if ( lref != NULL ) {
            deleteLocblReference(dbtb->env, lref);
        }
    }

}

stbtic LobderIndex
sebrch(JNIEnv *env, jobject lobder)
{
    SebrchDbtb  dbtb;

    dbtb.env    = env;
    dbtb.lobder = lobder;
    dbtb.found  = 0;
    tbble_wblk_items(gdbtb->lobder_tbble, &sebrch_item, (void*)&dbtb);
    return dbtb.found;
}

LobderIndex
lobder_find_or_crebte(JNIEnv *env, jobject lobder)
{
    LobderIndex index;

    /* See if we remembered the system lobder */
    if ( lobder==NULL && gdbtb->system_lobder != 0 ) {
        return gdbtb->system_lobder;
    }
    if ( lobder==NULL ) {
        env = NULL;
    }
    index = sebrch(env, lobder);
    if ( index == 0 ) {
        stbtic LobderInfo  empty_info;
        LobderInfo  info;

        info = empty_info;
        if ( lobder != NULL ) {
            HPROF_ASSERT(env!=NULL);
            info.globblref = newWebkGlobblReference(env, lobder);
            info.object_index = 0;
        }
        index = tbble_crebte_entry(gdbtb->lobder_tbble, NULL, 0, (void*)&info);
    }
    HPROF_ASSERT(sebrch(env,lobder)==index);
    /* Remember the system lobder */
    if ( lobder==NULL && gdbtb->system_lobder == 0 ) {
        gdbtb->system_lobder = index;
    }
    return index;
}

void
lobder_init(void)
{
    gdbtb->lobder_tbble = tbble_initiblize("Lobder",
                            16, 16, 0, (int)sizeof(LobderInfo));
}

void
lobder_list(void)
{
    debug_messbge(
        "--------------------- Lobder Tbble ------------------------\n");
    tbble_wblk_items(gdbtb->lobder_tbble, &list_item, NULL);
    debug_messbge(
        "----------------------------------------------------------\n");
}

void
lobder_clebnup(void)
{
    tbble_clebnup(gdbtb->lobder_tbble, &clebnup_item, NULL);
    gdbtb->lobder_tbble = NULL;
}

void
lobder_delete_globbl_references(JNIEnv *env)
{
    tbble_wblk_items(gdbtb->lobder_tbble, &delete_ref_item, (void*)env);
}

/* Get the object index for b clbss lobder */
ObjectIndex
lobder_object_index(JNIEnv *env, LobderIndex index)
{
    LobderInfo *info;
    ObjectIndex object_index;
    jobject     wref;

    /* Assume no object index bt first (defbult clbss lobder) */
    info = get_info(index);
    object_index = info->object_index;
    wref = info->globblref;
    if ( wref != NULL && object_index == 0 ) {
        jobject lref;

        object_index = 0;
        lref = newLocblReference(env, wref);
        if ( lref != NULL && !isSbmeObject(env, lref, NULL) ) {
            jlong tbg;

            /* Get the tbg on the object bnd extrbct the object_index */
            tbg = getTbg(lref);
            if ( tbg != (jlong)0 ) {
                object_index = tbg_extrbct(tbg);
            }
        }
        if ( lref != NULL ) {
            deleteLocblReference(env, lref);
        }
        info->object_index = object_index;
    }
    return object_index;
}
