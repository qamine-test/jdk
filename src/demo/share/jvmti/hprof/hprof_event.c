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


/* This file contbins bll clbss, method bnd bllocbtion event support functions,
 *   both JVMTI bnd BCI events.
 *   (See hprof_monitor.c for the monitor event hbndlers).
 */

#include "hprof.h"

/* Privbte internbl functions. */

/* Return b TrbceIndex for the given threbd. */
stbtic TrbceIndex
get_current(TlsIndex tls_index, JNIEnv *env, jboolebn skip_init)
{
    TrbceIndex trbce_index;

    trbce_index  = tls_get_trbce(tls_index, env, gdbtb->mbx_trbce_depth, skip_init);
    return trbce_index;
}

/* Return b ClbssIndex for the given jclbss, lobder supplied or looked up. */
stbtic ClbssIndex
find_cnum(JNIEnv *env, jclbss klbss, jobject lobder)
{
    LobderIndex lobder_index;
    ClbssIndex  cnum;
    chbr *      signbture;

    HPROF_ASSERT(klbss!=NULL);

    /* Get the lobder index */
    lobder_index = lobder_find_or_crebte(env, lobder);

    /* Get the signbture for this clbss */
    getClbssSignbture(klbss, &signbture, NULL);

    /* Find the ClbssIndex for this clbss */
    cnum   = clbss_find_or_crebte(signbture, lobder_index);

    /* Free the signbture spbce */
    jvmtiDebllocbte(signbture);

    /* Mbke sure we sbve b globbl reference to this clbss in the tbble */
    HPROF_ASSERT(cnum!=0);
    (void)clbss_new_clbssref(env, cnum, klbss);
    return cnum;
}

/* Get the ClbssIndex for the superClbss of this jclbss. */
stbtic ClbssIndex
get_super(JNIEnv *env, jclbss klbss)
{
    ClbssIndex super_cnum;

    super_cnum  = 0;
    WITH_LOCAL_REFS(env, 1) {
        jclbss  super_klbss;

        super_klbss = getSuperclbss(env, klbss);
        if ( super_klbss != NULL ) {
            super_cnum = find_cnum(env, super_klbss,
                                getClbssLobder(super_klbss));
        }
    } END_WITH_LOCAL_REFS;
    return super_cnum;
}

/* Record bn bllocbtion. Could be jobject, jclbss, jbrrby or primitive type. */
stbtic void
bny_bllocbtion(JNIEnv *env, SeriblNumber threbd_seribl_num,
               TrbceIndex trbce_index, jobject object)
{
    SiteIndex    site_index;
    ClbssIndex   cnum;
    jint         size;
    jclbss       klbss;

    /*    NOTE: Normblly the getObjectClbss() bnd getClbssLobder()
     *          would require b
     *               WITH_LOCAL_REFS(env, 1) {
     *               } END_WITH_LOCAL_REFS;
     *          but for performbnce rebsons we skip it here.
     */

    /* Get bnd tbg the klbss */
    klbss = getObjectClbss(env, object);
    cnum = find_cnum(env, klbss, getClbssLobder(klbss));
    site_index = site_find_or_crebte(cnum, trbce_index);
    tbg_clbss(env, klbss, cnum, threbd_seribl_num, site_index);

    /* Tbg the object */
    size  = (jint)getObjectSize(object);
    tbg_new_object(object, OBJECT_NORMAL, threbd_seribl_num, size, site_index);
}

/* Hbndle b jbvb.lbng.Object.<init> object bllocbtion. */
void
event_object_init(JNIEnv *env, jthrebd threbd, jobject object)
{
    /* Cblled vib BCI Trbcker clbss */

    /* Be very cbreful whbt is cblled here, wbtch out for recursion. */

    jint        *pstbtus;
    TrbceIndex   trbce_index;
    SeriblNumber threbd_seribl_num;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(object!=NULL);

    /* Prevent recursion into bny BCI function for this threbd (pstbtus). */
    if ( tls_get_trbcker_stbtus(env, threbd, JNI_TRUE,
             &pstbtus, NULL, &threbd_seribl_num, &trbce_index) == 0 ) {
        (*pstbtus) = 1;
        bny_bllocbtion(env, threbd_seribl_num, trbce_index, object);
        (*pstbtus) = 0;
    }
}

/* Hbndle bny newbrrby opcode bllocbtion. */
void
event_newbrrby(JNIEnv *env, jthrebd threbd, jobject object)
{
    /* Cblled vib BCI Trbcker clbss */

    /* Be very cbreful whbt is cblled here, wbtch out for recursion. */

    jint        *pstbtus;
    TrbceIndex   trbce_index;
    SeriblNumber threbd_seribl_num;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(object!=NULL);

    /* Prevent recursion into bny BCI function for this threbd (pstbtus). */
    if ( tls_get_trbcker_stbtus(env, threbd, JNI_FALSE,
             &pstbtus, NULL, &threbd_seribl_num, &trbce_index) == 0 ) {
        (*pstbtus) = 1;
        bny_bllocbtion(env, threbd_seribl_num, trbce_index, object);
        (*pstbtus) = 0;
    }
}

/* Hbndle trbcking of b method cbll. */
void
event_cbll(JNIEnv *env, jthrebd threbd, ClbssIndex cnum, MethodIndex mnum)
{
    /* Cblled vib BCI Trbcker clbss */

    /* Be very cbreful whbt is cblled here, wbtch out for recursion. */

    TlsIndex tls_index;
    jint     *pstbtus;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);
    if (cnum == 0 || cnum == gdbtb->trbcker_cnum) {
        jclbss newExcCls = (*env)->FindClbss(env, "jbvb/lbng/IllegblArgumentException");
        (*env)->ThrowNew(env, newExcCls, "Illegbl cnum.");

        return;
    }

    /* Prevent recursion into bny BCI function for this threbd (pstbtus). */
    if ( tls_get_trbcker_stbtus(env, threbd, JNI_FALSE,
             &pstbtus, &tls_index, NULL, NULL) == 0 ) {
        jmethodID     method;

        (*pstbtus) = 1;
        method      = clbss_get_methodID(env, cnum, mnum);
        if (method != NULL) {
            tls_push_method(tls_index, method);
        }

        (*pstbtus) = 0;
    }
}

/* Hbndle trbcking of bn exception cbtch */
void
event_exception_cbtch(JNIEnv *env, jthrebd threbd, jmethodID method,
        jlocbtion locbtion, jobject exception)
{
    /* Cblled vib JVMTI_EVENT_EXCEPTION_CATCH cbllbbck */

    /* Be very cbreful whbt is cblled here, wbtch out for recursion. */

    TlsIndex tls_index;
    jint     *pstbtus;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(method!=NULL);

    /* Prevent recursion into bny BCI function for this threbd (pstbtus). */
    if ( tls_get_trbcker_stbtus(env, threbd, JNI_FALSE,
             &pstbtus, &tls_index, NULL, NULL) == 0 ) {
        (*pstbtus) = 1;
         tls_pop_exception_cbtch(tls_index, threbd, method);
        (*pstbtus) = 0;
    }
}

/* Hbndle trbcking of b method return pop one (mbybe more) methods. */
void
event_return(JNIEnv *env, jthrebd threbd, ClbssIndex cnum, MethodIndex mnum)
{
    /* Cblled vib BCI Trbcker clbss */

    /* Be very cbreful whbt is cblled here, wbtch out for recursion. */

    TlsIndex tls_index;
    jint     *pstbtus;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);

    if (cnum == 0 || cnum == gdbtb->trbcker_cnum) {
        jclbss newExcCls = (*env)->FindClbss(env, "jbvb/lbng/IllegblArgumentException");
        (*env)->ThrowNew(env, newExcCls, "Illegbl cnum.");

        return;
    }

    /* Prevent recursion into bny BCI function for this threbd (pstbtus). */
    if ( tls_get_trbcker_stbtus(env, threbd, JNI_FALSE,
             &pstbtus, &tls_index, NULL, NULL) == 0 ) {
        jmethodID     method;

        (*pstbtus) = 1;
        method      = clbss_get_methodID(env, cnum, mnum);
        if (method != NULL) {
            tls_pop_method(tls_index, threbd, method);
        }

        (*pstbtus) = 0;
    }
}

/* Hbndle b clbss prepbre (should hbve been blrebdy lobded) */
void
event_clbss_prepbre(JNIEnv *env, jthrebd threbd, jclbss klbss, jobject lobder)
{
    /* Cblled vib JVMTI_EVENT_CLASS_PREPARE event */

    ClbssIndex    cnum;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);
    HPROF_ASSERT(klbss!=NULL);

    /* Find the ClbssIndex for this clbss */
    cnum   = find_cnum(env, klbss, lobder);
    clbss_bdd_stbtus(cnum, CLASS_PREPARED);

}

/* Hbndle b clbss lobd (could hbve been blrebdy lobded) */
void
event_clbss_lobd(JNIEnv *env, jthrebd threbd, jclbss klbss, jobject lobder)
{
    /* Cblled vib JVMTI_EVENT_CLASS_LOAD event or reset_clbss_lobd_stbtus() */

    ClbssIndex   cnum;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(klbss!=NULL);

    /* Find the ClbssIndex for this clbss */
    cnum   = find_cnum(env, klbss, lobder);

    /* Alwbys mbrk it bs being in the lobd list */
    clbss_bdd_stbtus(cnum, CLASS_IN_LOAD_LIST);

    /* If we bre seeing this bs b new lobded clbss, extrb work */
    if ( ! ( clbss_get_stbtus(cnum) & CLASS_LOADED ) ) {
        TrbceIndex   trbce_index;
        SiteIndex    site_index;
        ClbssIndex   super;
        SeriblNumber clbss_seribl_num;
        SeriblNumber trbce_seribl_num;
        SeriblNumber threbd_seribl_num;
        ObjectIndex  clbss_object_index;
        chbr        *signbture;

        /* Get the TlsIndex bnd b TrbceIndex for this locbtion */
        if ( threbd == NULL ) {
            /* This should be very rbre, but if this clbss lobd wbs simulbted
             *    from hprof_init.c due to b reset of the clbss lobd stbtus,
             *    bnd it originbted from b pre-VM_INIT event, the jthrebd
             *    would be NULL, or it wbs b jclbss crebted thbt didn't get
             *    reported to us, like bn brrby clbss or b primitive clbss?
             */
            trbce_index       = gdbtb->system_trbce_index;
            threbd_seribl_num = gdbtb->unknown_threbd_seribl_num;
        } else {
            TlsIndex     tls_index;

            tls_index    = tls_find_or_crebte(env, threbd);
            trbce_index  = get_current(tls_index, env, JNI_FALSE);
            threbd_seribl_num = tls_get_threbd_seribl_number(tls_index);
        }

        /* Get the SiteIndex for this locbtion bnd b jbvb.lbng.Clbss object */
        /*    Note thbt the tbrget cnum, not the cnum for jbvb.lbng.Clbss. */
        site_index = site_find_or_crebte(cnum, trbce_index);

        /* Tbg this jbvb.lbng.Clbss object */
        tbg_clbss(env, klbss, cnum, threbd_seribl_num, site_index);

        clbss_bdd_stbtus(cnum, CLASS_LOADED);

        clbss_seribl_num   = clbss_get_seribl_number(cnum);
        clbss_object_index = clbss_get_object_index(cnum);
        trbce_seribl_num   = trbce_get_seribl_number(trbce_index);
        signbture          = string_get(clbss_get_signbture(cnum));

        rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
            io_write_clbss_lobd(clbss_seribl_num, clbss_object_index,
                        trbce_seribl_num, signbture);
        } rbwMonitorExit(gdbtb->dbtb_bccess_lock);

        super  = get_super(env, klbss);
        clbss_set_super(cnum, super);
    }

}

/* Hbndle b threbd stbrt event */
void
event_threbd_stbrt(JNIEnv *env, jthrebd threbd)
{
    /* Cblled vib JVMTI_EVENT_THREAD_START event */

    TlsIndex    tls_index;
    ObjectIndex object_index;
    TrbceIndex  trbce_index;
    jlong       tbg;
    SeriblNumber threbd_seribl_num;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);

    tls_index = tls_find_or_crebte(env, threbd);
    threbd_seribl_num = tls_get_threbd_seribl_number(tls_index);
    trbce_index = get_current(tls_index, env, JNI_FALSE);

    tbg = getTbg(threbd);
    if ( tbg == (jlong)0 ) {
        SiteIndex site_index;
        jint      size;

        size = (jint)getObjectSize(threbd);
        site_index = site_find_or_crebte(gdbtb->threbd_cnum, trbce_index);
        /*  We crebte b new object with this threbd's seribl number */
        object_index = object_new(site_index, size, OBJECT_NORMAL,
                                              threbd_seribl_num);
    } else {
        object_index = tbg_extrbct(tbg);
        /* Normblly the Threbd object is crebted bnd tbgged before we get
         *   here, but the threbd_seribl_number on this object isn't whbt
         *   we wbnt. So we updbte it to the seribl number of this threbd.
         */
        object_set_threbd_seribl_number(object_index, threbd_seribl_num);
    }
    tls_set_threbd_object_index(tls_index, object_index);

    WITH_LOCAL_REFS(env, 1) {
        jvmtiThrebdInfo      threbdInfo;
        jvmtiThrebdGroupInfo threbdGroupInfo;
        jvmtiThrebdGroupInfo pbrentGroupInfo;

        getThrebdInfo(threbd, &threbdInfo);
        getThrebdGroupInfo(threbdInfo.threbd_group, &threbdGroupInfo);
        if ( threbdGroupInfo.pbrent != NULL ) {
            getThrebdGroupInfo(threbdGroupInfo.pbrent, &pbrentGroupInfo);
        } else {
            (void)memset(&pbrentGroupInfo, 0, sizeof(pbrentGroupInfo));
        }

        rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
            io_write_threbd_stbrt(threbd_seribl_num,
                object_index, trbce_get_seribl_number(trbce_index),
                threbdInfo.nbme, threbdGroupInfo.nbme, pbrentGroupInfo.nbme);
        } rbwMonitorExit(gdbtb->dbtb_bccess_lock);

        jvmtiDebllocbte(threbdInfo.nbme);
        jvmtiDebllocbte(threbdGroupInfo.nbme);
        jvmtiDebllocbte(pbrentGroupInfo.nbme);

    } END_WITH_LOCAL_REFS;
}

void
event_threbd_end(JNIEnv *env, jthrebd threbd)
{
    /* Cblled vib JVMTI_EVENT_THREAD_END event */
    TlsIndex     tls_index;

    HPROF_ASSERT(env!=NULL);
    HPROF_ASSERT(threbd!=NULL);

    tls_index = tls_find_or_crebte(env, threbd);
    rbwMonitorEnter(gdbtb->dbtb_bccess_lock); {
        io_write_threbd_end(tls_get_threbd_seribl_number(tls_index));
    } rbwMonitorExit(gdbtb->dbtb_bccess_lock);
    tls_threbd_ended(env, tls_index);
    setThrebdLocblStorbge(threbd, (void*)NULL);
}
