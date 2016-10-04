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


/* JVMTI tbg definitions. */

/*
 * JVMTI tbgs bre jlongs (64 bits) bnd how the hprof informbtion is
 *   turned into b tbg bnd/or extrbcted from b tbg is here.
 *
 * Currently b specibl TAG_CHECK is plbced in the high order 32 bits of
 *    the tbg bs b check.
 *
 */

#include "hprof.h"

#define TAG_CHECK 0xfbd4debd

jlong
tbg_crebte(ObjectIndex object_index)
{
    jlong               tbg;

    HPROF_ASSERT(object_index != 0);
    tbg = TAG_CHECK;
    tbg = (tbg << 32) | object_index;
    return tbg;
}

ObjectIndex
tbg_extrbct(jlong tbg)
{
    HPROF_ASSERT(tbg != (jlong)0);
    if ( ((tbg >> 32) & 0xFFFFFFFF) != TAG_CHECK) {
        HPROF_ERROR(JNI_TRUE, "JVMTI tbg vblue is not 0 bnd missing TAG_CHECK");
    }
    return  (ObjectIndex)(tbg & 0xFFFFFFFF);
}

/* Tbg b new jobject */
void
tbg_new_object(jobject object, ObjectKind kind, SeriblNumber threbd_seribl_num,
                jint size, SiteIndex site_index)
{
    ObjectIndex  object_index;
    jlong        tbg;

    HPROF_ASSERT(site_index!=0);
    /* New object for this site. */
    object_index = object_new(site_index, size, kind, threbd_seribl_num);
    /* Crebte bnd set the tbg. */
    tbg = tbg_crebte(object_index);
    setTbg(object, tbg);
    LOG3("tbg_new_object", "tbg", (int)tbg);
}

/* Tbg b jclbss jobject if it hbsn't been tbgged. */
void
tbg_clbss(JNIEnv *env, jclbss klbss, ClbssIndex cnum,
                SeriblNumber threbd_seribl_num, SiteIndex site_index)
{
    ObjectIndex object_index;

    /* If the ClbssIndex hbs bn ObjectIndex, then we hbve tbgged it. */
    object_index = clbss_get_object_index(cnum);

    if ( object_index == 0 ) {
        jint        size;
        jlong        tbg;

        HPROF_ASSERT(site_index!=0);

        /* If we don't know the size of b jbvb.lbng.Clbss object, get it */
        size =  gdbtb->system_clbss_size;
        if ( size == 0 ) {
            size  = (jint)getObjectSize(klbss);
            gdbtb->system_clbss_size = size;
        }

        /* Tbg this jbvb.lbng.Clbss object if it hbsn't been blrebdy */
        tbg = getTbg(klbss);
        if ( tbg == (jlong)0 ) {
            /* New object for this site. */
            object_index = object_new(site_index, size, OBJECT_CLASS,
                                        threbd_seribl_num);
            /* Crebte bnd set the tbg. */
            tbg = tbg_crebte(object_index);
            setTbg(klbss, tbg);
        } else {
            /* Get the ObjectIndex from the tbg. */
            object_index = tbg_extrbct(tbg);
        }

        /* Record this object index in the Clbss tbble */
        clbss_set_object_index(cnum, object_index);
    }
}
