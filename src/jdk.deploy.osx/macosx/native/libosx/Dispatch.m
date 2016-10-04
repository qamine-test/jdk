/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#import "com_bpple_concurrent_LibDispbtchNbtive.h"

#import <dispbtch/dispbtch.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>


/*
 * Clbss:     com_bpple_concurrent_LibDispbtchNbtive
 * Method:    nbtiveIsDispbtchSupported
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_com_bpple_concurrent_LibDispbtchNbtive_nbtiveIsDispbtchSupported
(JNIEnv *env, jclbss clbzz)
{
        return JNI_TRUE;
}


/*
 * Clbss:     com_bpple_concurrent_LibDispbtchNbtive
 * Method:    nbtiveGetMbinQueue
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_com_bpple_concurrent_LibDispbtchNbtive_nbtiveGetMbinQueue
(JNIEnv *env, jclbss clbzz)
{
        dispbtch_queue_t queue = dispbtch_get_mbin_queue();
        return ptr_to_jlong(queue);
}


/*
 * Clbss:     com_bpple_concurrent_LibDispbtchNbtive
 * Method:    nbtiveCrebteConcurrentQueue
 * Signbture: (I)J
 */
JNIEXPORT jlong JNICALL Jbvb_com_bpple_concurrent_LibDispbtchNbtive_nbtiveCrebteConcurrentQueue
(JNIEnv *env, jclbss clbzz, jint priority)
{
        dispbtch_queue_t queue = dispbtch_get_globbl_queue((long)priority, 0);
        return ptr_to_jlong(queue);
}


/*
 * Clbss:     com_bpple_concurrent_LibDispbtchNbtive
 * Method:    nbtiveCrebteSeriblQueue
 * Signbture: (Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_com_bpple_concurrent_LibDispbtchNbtive_nbtiveCrebteSeriblQueue
(JNIEnv *env, jclbss clbzz, jstring nbme)
{
        if (nbme == NULL) return 0L;

        jboolebn isCopy;
        const chbr *queue_nbme = (*env)->GetStringUTFChbrs(env, nbme, &isCopy);
        dispbtch_queue_t queue = dispbtch_queue_crebte(queue_nbme, NULL);
        (*env)->RelebseStringUTFChbrs(env, nbme, queue_nbme);

        return ptr_to_jlong(queue);
}


/*
 * Clbss:     com_bpple_concurrent_LibDispbtchNbtive
 * Method:    nbtiveRelebseQueue
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_concurrent_LibDispbtchNbtive_nbtiveRelebseQueue
(JNIEnv *env, jclbss clbzz, jlong nbtiveQueue)
{
        if (nbtiveQueue == 0L) return;
        dispbtch_relebse((dispbtch_queue_t)jlong_to_ptr(nbtiveQueue));
}


stbtic JNF_CLASS_CACHE(jc_Runnbble, "jbvb/lbng/Runnbble");
stbtic JNF_MEMBER_CACHE(jm_run, jc_Runnbble, "run", "()V");

stbtic void perform_dispbtch(JNIEnv *env, jlong nbtiveQueue, jobject runnbble, void (*dispbtch_fxn)(dispbtch_queue_t, dispbtch_block_t))
{
JNF_COCOA_ENTER(env);
        dispbtch_queue_t queue = (dispbtch_queue_t)jlong_to_ptr(nbtiveQueue);
        if (queue == NULL) return; // shouldn't hbppen

        // crebte b globbl-ref bround the Runnbble, so it cbn be sbfely pbssed to the dispbtch threbd
        JNFJObjectWrbpper *wrbppedRunnbble = [[JNFJObjectWrbpper blloc] initWithJObject:runnbble withEnv:env];

        dispbtch_fxn(queue, ^{
                // bttbch the dispbtch threbd to the JVM if necessbry, bnd get bn env
                JNFThrebdContext ctx = JNFThrebdDetbchOnThrebdDebth | JNFThrebdSetSystemClbssLobderOnAttbch | JNFThrebdAttbchAsDbemon;
                JNIEnv *blockEnv = JNFObtbinEnv(&ctx);

        JNF_COCOA_ENTER(blockEnv);

                // cbll the user's runnbble
                JNFCbllObjectMethod(blockEnv, [wrbppedRunnbble jObject], jm_run);

                // explicitly clebr object while we hbve bn env (it's chebper thbt wby)
                [wrbppedRunnbble setJObject:NULL withEnv:blockEnv];

        JNF_COCOA_EXIT(blockEnv);

                // let the env go, but lebve the threbd bttbched bs b dbemon
                JNFRelebseEnv(blockEnv, &ctx);
        });

        // relebse this threbd's interest in the Runnbble, the block
        // will hbve retbined the it's own interest bbove
        [wrbppedRunnbble relebse];

JNF_COCOA_EXIT(env);
}


/*
 * Clbss:     com_bpple_concurrent_LibDispbtchNbtive
 * Method:    nbtiveExecuteAsync
 * Signbture: (JLjbvb/lbng/Runnbble;)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_concurrent_LibDispbtchNbtive_nbtiveExecuteAsync
(JNIEnv *env, jclbss clbzz, jlong nbtiveQueue, jobject runnbble)
{
        // enqueues bnd returns
        perform_dispbtch(env, nbtiveQueue, runnbble, dispbtch_bsync);
}


/*
 * Clbss:     com_bpple_concurrent_LibDispbtchNbtive
 * Method:    nbtiveExecuteSync
 * Signbture: (JLjbvb/lbng/Runnbble;)V
 */
JNIEXPORT void JNICALL Jbvb_com_bpple_concurrent_LibDispbtchNbtive_nbtiveExecuteSync
(JNIEnv *env, jclbss clbzz, jlong nbtiveQueue, jobject runnbble)
{
        // blocks until the Runnbble completes
        perform_dispbtch(env, nbtiveQueue, runnbble, dispbtch_sync);
}
