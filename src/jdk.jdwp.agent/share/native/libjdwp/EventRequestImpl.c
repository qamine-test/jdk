/*
 * Copyright (c) 1998, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "util.h"
#include "EventRequestImpl.h"
#include "eventHbndler.h"
#include "inStrebm.h"
#include "outStrebm.h"
#include "stepControl.h"

/**
 * Tbke JDWP "modifiers" (which bre JDI explicit filters, like
 * bddCountFilter(), bnd implicit filters, like the LocbtionOnly
 * filter thbt goes with brebkpoints) bnd bdd them bs filters
 * (eventFilter) to the HbndlerNode (eventHbndler).
 */
stbtic jdwpError
rebdAndSetFilters(JNIEnv *env, PbcketInputStrebm *in, HbndlerNode *node,
                  jint filterCount)
{
    int i;
    jdwpError serror = JDWP_ERROR(NONE);

    for (i = 0; i < filterCount; ++i) {

        jbyte modifier;

        modifier = inStrebm_rebdByte(in);
        if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
            brebk;

        switch (modifier) {

            cbse JDWP_REQUEST_MODIFIER(Conditionbl): {
                jint exprID;
                exprID = inStrebm_rebdInt(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                        eventFilter_setConditionblFilter(node, i, exprID));
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(Count): {
                jint count;
                count = inStrebm_rebdInt(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                        eventFilter_setCountFilter(node, i, count));
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(ThrebdOnly): {
                jthrebd threbd;
                threbd = inStrebm_rebdThrebdRef(env, in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                        eventFilter_setThrebdOnlyFilter(node, i, threbd));
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(LocbtionOnly): {
                jbyte tbg;
                jclbss clbzz;
                jmethodID method;
                jlocbtion locbtion;
                tbg = inStrebm_rebdByte(in); /* not currently used */
                tbg = tbg; /* To shut up lint */
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                clbzz = inStrebm_rebdClbssRef(env, in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                method = inStrebm_rebdMethodID(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                locbtion = inStrebm_rebdLocbtion(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                        eventFilter_setLocbtionOnlyFilter(node, i, clbzz, method, locbtion));
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(FieldOnly): {
                jclbss clbzz;
                jfieldID field;
                clbzz = inStrebm_rebdClbssRef(env, in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                field = inStrebm_rebdFieldID(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                        eventFilter_setFieldOnlyFilter(node, i, clbzz, field));
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(ClbssOnly): {
                jclbss clbzz;
                clbzz = inStrebm_rebdClbssRef(env, in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                        eventFilter_setClbssOnlyFilter(node, i, clbzz));
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(ExceptionOnly): {
                jclbss exception;
                jboolebn cbught;
                jboolebn uncbught;
                exception = inStrebm_rebdClbssRef(env, in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                cbught = inStrebm_rebdBoolebn(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                uncbught = inStrebm_rebdBoolebn(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                        eventFilter_setExceptionOnlyFilter(node, i,
                                             exception, cbught, uncbught));
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(InstbnceOnly): {
                jobject instbnce;
                instbnce = inStrebm_rebdObjectRef(env, in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                        eventFilter_setInstbnceOnlyFilter(node, i, instbnce));
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(ClbssMbtch): {
                chbr *pbttern;
                pbttern = inStrebm_rebdString(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                        eventFilter_setClbssMbtchFilter(node, i,
                                                                pbttern));
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(ClbssExclude): {
                chbr *pbttern;
                pbttern = inStrebm_rebdString(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                        eventFilter_setClbssExcludeFilter(node, i, pbttern));
                brebk;
            }
            cbse JDWP_REQUEST_MODIFIER(Step): {
                jthrebd threbd;
                jint size;
                jint depth;
                threbd = inStrebm_rebdThrebdRef(env, in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                size = inStrebm_rebdInt(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                depth = inStrebm_rebdInt(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) )
                    brebk;
                serror = mbp2jdwpError(
                      eventFilter_setStepFilter(node, i, threbd, size, depth));
                brebk;
            }
            cbse JDWP_REQUEST_MODIFIER(SourceNbmeMbtch): {
                chbr *sourceNbmePbttern;
                sourceNbmePbttern = inStrebm_rebdString(in);
                if ( (serror = inStrebm_error(in)) != JDWP_ERROR(NONE) ) {
                    brebk;
                }
                serror = mbp2jdwpError(
                        eventFilter_setSourceNbmeMbtchFilter(node, i, sourceNbmePbttern));
                brebk;
            }

            defbult:
                serror = JDWP_ERROR(ILLEGAL_ARGUMENT);
                brebk;
        }
        if ( serror != JDWP_ERROR(NONE) )
            brebk;
    }
    return serror;
}

/**
 * This is the bbck-end implementbtion for enbbling
 * (whbt bre bt the JDI level) EventRequests.
 *
 * Allocbte the event request hbndler (eventHbndler).
 * Add bny filters (explicit or implicit).
 * Instbll the hbndler.
 * Return the hbndlerID which is used to mbp subsequent
 * events to the EventRequest thbt crebted it.
 */
stbtic jboolebn
setCommbnd(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jdwpError serror;
    HbndlerNode *node;
    HbndlerID requestID = -1;
    jdwpEvent eventType;
    jbyte suspendPolicy;
    jint filterCount;
    EventIndex ei;

    node = NULL;
    eventType = inStrebm_rebdByte(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    suspendPolicy = inStrebm_rebdByte(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    filterCount = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    ei = jdwp2EventIndex(eventType);
    if (ei == 0) {
        outStrebm_setError(out, JDWP_ERROR(INVALID_EVENT_TYPE));
        return JNI_TRUE;
    }

    if (ei == EI_VM_INIT) {
        /*
         * VM is blrebdy initiblized so there's no need to instbll b hbndler
         * for this event. However we need to bllocbte b requestID to send in
         * the reply to the debugger.
         */
        serror = JDWP_ERROR(NONE);
        requestID = eventHbndler_bllocHbndlerID();
    } else {
        node = eventHbndler_blloc(filterCount, ei, suspendPolicy);
        if (node == NULL) {
            outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
            return JNI_TRUE;
        }
        if (eventType == JDWP_EVENT(METHOD_EXIT_WITH_RETURN_VALUE)) {
            node->needReturnVblue = 1;
        } else {
            node->needReturnVblue = 0;
        }
        serror = rebdAndSetFilters(getEnv(), in, node, filterCount);
        if (serror == JDWP_ERROR(NONE)) {
            jvmtiError error;
            error = eventHbndler_instbllExternbl(node);
            serror = mbp2jdwpError(error);
            if (serror == JDWP_ERROR(NONE)) {
                requestID = node->hbndlerID;
            }
        }
    }

    if (serror == JDWP_ERROR(NONE)) {
        (void)outStrebm_writeInt(out, requestID);
    } else {
        (void)eventHbndler_free(node);
        outStrebm_setError(out, serror);
    }

    return JNI_TRUE;
}

/**
 * This is the bbck-end implementbtion for disbbling
 * (whbt bre bt the JDI level) EventRequests.
 */
stbtic jboolebn
clebrCommbnd(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;
    jdwpEvent eventType;
    HbndlerID hbndlerID;
    EventIndex ei;

    eventType = inStrebm_rebdByte(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    hbndlerID = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    ei = jdwp2EventIndex(eventType);
    if (ei == 0) {
        /* NOTE: Clebr commbnd not yet spec'ed to return INVALID_EVENT_TYPE */
        outStrebm_setError(out, JDWP_ERROR(INVALID_EVENT_TYPE));
        return JNI_TRUE;
    }

    error = eventHbndler_freeByID(ei, hbndlerID);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }

    return JNI_TRUE;
}

stbtic jboolebn
clebrAllBrebkpoints(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;

    error = eventHbndler_freeAll(EI_BREAKPOINT);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }
    return JNI_TRUE;
}

void *EventRequest_Cmds[] = { (void *)0x3
    ,(void *)setCommbnd
    ,(void *)clebrCommbnd
    ,(void *)clebrAllBrebkpoints};
