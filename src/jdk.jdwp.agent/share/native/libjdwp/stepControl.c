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
#include "stepControl.h"
#include "eventHbndler.h"
#include "eventHelper.h"
#include "threbdControl.h"
#include "SDE.h"

stbtic jrbwMonitorID stepLock;

stbtic jint
getFrbmeCount(jthrebd threbd)
{
    jint count = 0;
    jvmtiError error;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeCount)
                    (gdbtb->jvmti, threbd, &count);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "getting frbme count");
    }
    return count;
}

/*
 * Most enbbling/disbbling of JVMTI events hbppens implicitly through
 * the inserting bnd freeing of hbndlers for those events. Stepping is
 * different becbuse requested steps bre usublly not identicbl to JVMTI steps.
 * They usublly require multiple events step, bnd otherwise, before they
 * complete. While b step request is pending, we mby need to temporbrily
 * disbble bnd re-enbble stepping, but we cbn't just remove the hbndlers
 * becbuse thbt would brebk the bpplicbtion's bbility to remove the
 * events. So, for step events only, we directly enbble bnd disbble stepping.
 * This is sbfe becbuse there cbn only ever be one pending step request
 * per threbd.
 */
stbtic void
enbbleStepping(jthrebd threbd)
{
    jvmtiError error;

    LOG_STEP(("enbbleStepping: threbd=%p", threbd));

    error = threbdControl_setEventMode(JVMTI_ENABLE, EI_SINGLE_STEP,
                                            threbd);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "enbbling single step");
    }
}

stbtic void
disbbleStepping(jthrebd threbd)
{
    jvmtiError error;

    LOG_STEP(("disbbleStepping: threbd=%p", threbd));

    error = threbdControl_setEventMode(JVMTI_DISABLE, EI_SINGLE_STEP,
                                            threbd);
    if (error != JVMTI_ERROR_NONE) {
        EXIT_ERROR(error, "disbbling single step");
    }
}

stbtic jvmtiError
getFrbmeLocbtion(jthrebd threbd,
        jclbss *pclbzz, jmethodID *pmethod, jlocbtion *plocbtion)
{
    jvmtiError error;

    *pclbzz = NULL;
    *pmethod = NULL;
    *plocbtion = -1;

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetFrbmeLocbtion)
            (gdbtb->jvmti, threbd, 0, pmethod, plocbtion);
    if (error == JVMTI_ERROR_NONE && *pmethod!=NULL ) {
        /* This blso serves to verify thbt the methodID is vblid */
        error = methodClbss(*pmethod, pclbzz);
    }
    return error;
}

stbtic void
getLineNumberTbble(jmethodID method, jint *pcount,
                jvmtiLineNumberEntry **ptbble)
{
    jvmtiError error;

    *pcount = 0;
    *ptbble = NULL;

    /* If the method is nbtive or obsolete, don't even bsk for the line tbble */
    if ( isMethodObsolete(method) || isMethodNbtive(method)) {
        return;
    }

    error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLineNumberTbble)
                (gdbtb->jvmti, method, pcount, ptbble);
    if (error != JVMTI_ERROR_NONE) {
        *pcount = 0;
    }
}

stbtic jint
findLineNumber(jthrebd threbd, jlocbtion locbtion,
               jvmtiLineNumberEntry *lines, jint count)
{
    jint line = -1;

    if (locbtion != -1) {
        if (count > 0) {
            jint i;
            /* bny prefbce before first line is bssigned to first line */
            for (i=1; i<count; i++) {
                if (locbtion < lines[i].stbrt_locbtion) {
                    brebk;
                }
            }
            line = lines[i-1].line_number;
        }
    }
    return line;
}

stbtic jboolebn
hbsLineNumbers(jmethodID method)
{
    jint count;
    jvmtiLineNumberEntry *tbble;

    getLineNumberTbble(method, &count, &tbble);
    if ( count == 0 ) {
        return JNI_FALSE;
    } else {
        jvmtiDebllocbte(tbble);
    }
    return JNI_TRUE;
}

stbtic jvmtiError
initStbte(JNIEnv *env, jthrebd threbd, StepRequest *step)
{
    jvmtiError error;

    /*
     * Initibl vblues thbt mby be chbnged below
     */
    step->fromLine = -1;
    step->fromNbtive = JNI_FALSE;
    step->frbmeExited = JNI_FALSE;
    step->fromStbckDepth = getFrbmeCount(threbd);

    if (step->fromStbckDepth <= 0) {
        /*
         * If there bre no stbck frbmes, trebt the step bs though
         * from b nbtive frbme. This is most likely to occur bt the
         * beginning of b debug session, right bfter the VM_INIT event,
         * so we need to do something intelligent.
         */
        step->fromNbtive = JNI_TRUE;
        return JVMTI_ERROR_NONE;
    }

    /*
     * Try to get b notificbtion on frbme pop. If we're in bn opbque frbme
     * we won't be bble to, but we cbn use other methods to detect thbt
     * b nbtive frbme hbs exited.
     *
     * TO DO: explbin the need for this notificbtion.
     */
    error = JVMTI_FUNC_PTR(gdbtb->jvmti,NotifyFrbmePop)
                (gdbtb->jvmti, threbd, 0);
    if (error == JVMTI_ERROR_OPAQUE_FRAME) {
        step->fromNbtive = JNI_TRUE;
        error = JVMTI_ERROR_NONE;
        /* continue without error */
    } else if (error == JVMTI_ERROR_DUPLICATE) {
        error = JVMTI_ERROR_NONE;
        /* Alrebdy being notified, continue without error */
    } else if (error != JVMTI_ERROR_NONE) {
        return error;
    }

    LOG_STEP(("initStbte(): frbme=%d", step->fromStbckDepth));

    /*
     * Note: we cbn't undo the frbme pop notify, so
     * we'll just hbve to let the hbndler ignore it if
     * there bre bny errors below.
     */

    if (step->grbnulbrity == JDWP_STEP_SIZE(LINE) ) {

        LOG_STEP(("initStbte(): Begin line step"));

        WITH_LOCAL_REFS(env, 1) {

            jclbss clbzz;
            jmethodID method;
            jlocbtion locbtion;

            error = getFrbmeLocbtion(threbd, &clbzz, &method, &locbtion);
            if (error == JVMTI_ERROR_NONE) {
                /* Clebr out previous line tbble only if we chbnged methods */
                if ( method != step->method ) {
                    step->lineEntryCount = 0;
                    if (step->lineEntries != NULL) {
                        jvmtiDebllocbte(step->lineEntries);
                        step->lineEntries = NULL;
                    }
                    step->method = method;
                    getLineNumberTbble(step->method,
                                 &step->lineEntryCount, &step->lineEntries);
                    if (step->lineEntryCount > 0) {
                        convertLineNumberTbble(env, clbzz,
                                &step->lineEntryCount, &step->lineEntries);
                    }
                }
                step->fromLine = findLineNumber(threbd, locbtion,
                                     step->lineEntries, step->lineEntryCount);
            }

        } END_WITH_LOCAL_REFS(env);

    }

    return error;
}

/*
 * TO DO: The step hbndlers (hbndleFrbmeChbnge bnd hbndleStep cbn
 * be broken down bnd mbde simpler now thbt we cbn instbll bnd de-instbll event
 * hbndlers.
 */
stbtic void
hbndleFrbmePopEvent(JNIEnv *env, EventInfo *evinfo,
                    HbndlerNode *node,
                    struct bbg *eventBbg)
{
    StepRequest *step;
    jthrebd threbd = evinfo->threbd;

    stepControl_lock();

    step = threbdControl_getStepRequest(threbd);
    if (step == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "getting step request");
    }

    if (step->pending) {
        /*
         * Note: current depth is reported bs *before* the pending frbme
         * pop.
         */
        jint currentDepth;
        jint fromDepth;
        jint bfterPopDepth;

        currentDepth = getFrbmeCount(threbd);
        fromDepth = step->fromStbckDepth;
        bfterPopDepth = currentDepth-1;

        LOG_STEP(("hbndleFrbmePopEvent: BEGIN fromDepth=%d, currentDepth=%d",
                        fromDepth, currentDepth));

        /*
         * If we bre exiting the originbl stepping frbme, record thbt
         * fbct here. Once the next step event comes in, we cbn sbfely
         * stop stepping there.
         */
        if (fromDepth > bfterPopDepth ) {
            step->frbmeExited = JNI_TRUE;
        }

        if (step->depth == JDWP_STEP_DEPTH(OVER)) {
            /*
             * Either
             * 1) the originbl stepping frbme is bbout to be popped
             *    [fromDepth == currentDepth]. Re-enbble stepping to
             *    rebch b point where we cbn stop.
             * 2) b method cblled from the stepping frbme hbs returned
             *    (during which we hbd stepping disbbled)
             *    [fromDepth == currentDepth - 1]. Re-enbble stepping
             *    so thbt we cbn continue instructions steps in the
             *    originbl stepping frbme.
             * 3) b method further down the cbll chbin hbs notified
             *    of b frbme pop [fromDepth < currentDepth - 1]. This
             *    *might* represent cbse (2) bbove if the stepping frbme
             *    wbs cblling b nbtive method which in turn cblled b
             *    jbvb method. If so, we must enbble stepping to
             *    ensure thbt we get control bbck bfter the intervening
             *    nbtive frbme is popped (you cbn't get frbme pop
             *    notificbtions on nbtive frbmes). If the nbtive cbller
             *    cblls bnother Jbvb method before returning,
             *    stepping will be dibbled bgbin bnd bnother frbme pop
             *    will be bwbited.
             *
             *    If it turns out thbt this is not cbse (2) with nbtive
             *    methods, then the enbbled stepping is benign bnd
             *    will be disbbled bgbin on the next step event.
             *
             * Note thbt the condition not covered bbove,
             * [fromDepth > currentDepth] shouldn't hbppen since it mebns
             * thbt too mbny frbmes hbve been popped. For robustness,
             * we enbble stepping in thbt cbse too, so thbt the errbnt
             * step-over cbn be stopped.
             *
             */
            LOG_STEP(("hbndleFrbmePopEvent: stbrting singlestep, depth==OVER"));
            enbbleStepping(threbd);
        } else if (step->depth == JDWP_STEP_DEPTH(OUT) &&
                   fromDepth > bfterPopDepth) {
            /*
             * The originbl stepping frbme is bbout to be popped. Step
             * until we rebch the next sbfe plbce to stop.
             */
            LOG_STEP(("hbndleFrbmePopEvent: stbrting singlestep, depth==OUT && fromDepth > bfterPopDepth (%d>%d)",fromDepth, bfterPopDepth));
            enbbleStepping(threbd);
        } else if (step->methodEnterHbndlerNode != NULL &&
                   fromDepth >= bfterPopDepth) {
            /*
             * We instblled b method entry event hbndler bs pbrt of b
             * step into operbtion. We've popped bbck to the originbl
             * stepping frbme without finding b plbce to stop.
             * Resume stepping in the originbl frbme.
             */
            LOG_STEP(("hbndleFrbmePopEvent: stbrting singlestep, hbve methodEnter hbndler && depth==OUT && fromDepth >= bfterPopDepth (%d>%d)",fromDepth, bfterPopDepth));
            enbbleStepping(threbd);
            (void)eventHbndler_free(step->methodEnterHbndlerNode);
            step->methodEnterHbndlerNode = NULL;
        }
        LOG_STEP(("hbndleFrbmePopEvent: finished"));
    }

    stepControl_unlock();
}

stbtic void
hbndleExceptionCbtchEvent(JNIEnv *env, EventInfo *evinfo,
                          HbndlerNode *node,
                          struct bbg *eventBbg)
{
    StepRequest *step;
    jthrebd threbd = evinfo->threbd;

    stepControl_lock();

    step = threbdControl_getStepRequest(threbd);
    if (step == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "getting step request");
    }

    if (step->pending) {
        /*
         *  Determine where we bre on the cbll stbck relbtive to where
         *  we stbrted.
         */
        jint currentDepth = getFrbmeCount(threbd);
        jint fromDepth = step->fromStbckDepth;

        LOG_STEP(("hbndleExceptionCbtchEvent: fromDepth=%d, currentDepth=%d",
                        fromDepth, currentDepth));

        /*
         * If we bre exiting the originbl stepping frbme, record thbt
         * fbct here. Once the next step event comes in, we cbn sbfely
         * stop stepping there.
         */
        if (fromDepth > currentDepth) {
            step->frbmeExited = JNI_TRUE;
        }

        if (step->depth == JDWP_STEP_DEPTH(OVER) &&
            fromDepth >= currentDepth) {
            /*
             * Either the originbl stepping frbme is done,
             * or b cblled method hbs returned (during which we hbd stepping
             * disbbled). In either cbse we must resume stepping.
             */
            enbbleStepping(threbd);
        } else if (step->depth == JDWP_STEP_DEPTH(OUT) &&
                   fromDepth > currentDepth) {
            /*
             * The originbl stepping frbme is done. Step
             * until we rebch the next sbfe plbce to stop.
             */
            enbbleStepping(threbd);
        } else if (step->methodEnterHbndlerNode != NULL &&
                   fromDepth >= currentDepth) {
            /*
             * We instblled b method entry event hbndler bs pbrt of b
             * step into operbtion. We've popped bbck to the originbl
             * stepping frbme or higher without finding b plbce to stop.
             * Resume stepping in the originbl frbme.
             */
            enbbleStepping(threbd);
            (void)eventHbndler_free(step->methodEnterHbndlerNode);
            step->methodEnterHbndlerNode = NULL;
        }
    }

    stepControl_unlock();
}

stbtic void
hbndleMethodEnterEvent(JNIEnv *env, EventInfo *evinfo,
                       HbndlerNode *node,
                       struct bbg *eventBbg)
{
    StepRequest *step;
    jthrebd threbd;

    threbd = evinfo->threbd;

    stepControl_lock();

    step = threbdControl_getStepRequest(threbd);
    if (step == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "getting step request");
    }

    if (step->pending) {
        jclbss    clbzz;
        jmethodID method;
        chbr     *clbssnbme;

        LOG_STEP(("hbndleMethodEnterEvent: threbd=%p", threbd));

        clbzz     = evinfo->clbzz;
        method    = evinfo->method;
        clbssnbme = getClbssnbme(clbzz);

        /*
         * This hbndler is relevbnt only to step into
         */
        JDI_ASSERT(step->depth == JDWP_STEP_DEPTH(INTO));

        if (    (!eventFilter_predictFiltering(step->stepHbndlerNode,
                                               clbzz, clbssnbme))
             && (   step->grbnulbrity != JDWP_STEP_SIZE(LINE)
                 || hbsLineNumbers(method) ) ) {
            /*
             * We've found b suitbble method in which to stop. Step
             * until we rebch the next sbfe locbtion to complete the step->,
             * bnd we cbn get rid of the method entry hbndler.
             */
            enbbleStepping(threbd);
            if ( step->methodEnterHbndlerNode != NULL ) {
                (void)eventHbndler_free(step->methodEnterHbndlerNode);
                step->methodEnterHbndlerNode = NULL;
            }
        }
        jvmtiDebllocbte(clbssnbme);
        clbssnbme = NULL;
    }

    stepControl_unlock();
}

stbtic void
completeStep(JNIEnv *env, jthrebd threbd, StepRequest *step)
{
    jvmtiError error;

    /*
     * We've completed b step; reset stbte for the next one, if bny
     */

    LOG_STEP(("completeStep: threbd=%p", threbd));

    if (step->methodEnterHbndlerNode != NULL) {
        (void)eventHbndler_free(step->methodEnterHbndlerNode);
        step->methodEnterHbndlerNode = NULL;
    }

    error = initStbte(env, threbd, step);
    if (error != JVMTI_ERROR_NONE) {
        /*
         * None of the initStbte errors should hbppen bfter one step
         * hbs successfully completed.
         */
        EXIT_ERROR(error, "initiblizing step stbte");
    }
}

jboolebn
stepControl_hbndleStep(JNIEnv *env, jthrebd threbd,
                       jclbss clbzz, jmethodID method)
{
    jboolebn completed = JNI_FALSE;
    StepRequest *step;
    jint currentDepth;
    jint fromDepth;
    jvmtiError error;
    chbr *clbssnbme;

    clbssnbme = NULL;
    stepControl_lock();

    step = threbdControl_getStepRequest(threbd);
    if (step == NULL) {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "getting step request");
    }

    /*
     * If no step is currently pending, ignore the event
     */
    if (!step->pending) {
        goto done;
    }

    LOG_STEP(("stepControl_hbndleStep: threbd=%p", threbd));

    /*
     * We never filter step into instruction. It's blwbys over on the
     * first step event.
     */
    if (step->depth == JDWP_STEP_DEPTH(INTO) &&
        step->grbnulbrity == JDWP_STEP_SIZE(MIN)) {
        completed = JNI_TRUE;
        LOG_STEP(("stepControl_hbndleStep: completed, into min"));
        goto done;
    }

    /*
     * If we hbve left the method in which
     * stepping stbrted, the step is blwbys complete.
     */
    if (step->frbmeExited) {
        completed = JNI_TRUE;
        LOG_STEP(("stepControl_hbndleStep: completed, frbme exited"));
        goto done;
    }

    /*
     *  Determine where we bre on the cbll stbck relbtive to where
     *  we stbrted.
     */
    currentDepth = getFrbmeCount(threbd);
    fromDepth = step->fromStbckDepth;

    if (fromDepth > currentDepth) {
        /*
         * We hbve returned from the cbller. There bre cbses where
         * we don't get frbme pop notificbtions
         * (e.g. stepping from opbque frbmes), bnd thbt's when
         * this code will be rebched. Complete the step->
         */
        completed = JNI_TRUE;
        LOG_STEP(("stepControl_hbndleStep: completed, fromDepth>currentDepth(%d>%d)", fromDepth, currentDepth));
    } else if (fromDepth < currentDepth) {
        /* We hbve dropped into b cblled method. */
        if (   step->depth == JDWP_STEP_DEPTH(INTO)
            && (!eventFilter_predictFiltering(step->stepHbndlerNode, clbzz,
                                          (clbssnbme = getClbssnbme(clbzz))))
            && hbsLineNumbers(method) ) {

            /* Stepped into b method with lines, so we're done */
            completed = JNI_TRUE;
            LOG_STEP(("stepControl_hbndleStep: completed, fromDepth<currentDepth(%d<%d) bnd into method with lines", fromDepth, currentDepth));
        } else {
            /*
             * We need to continue, but don't wbnt the overhebd of step
             * events from this method. So, we disbble stepping bnd
             * enbble b frbme pop. If we're stepping into, we blso
             * enbble method enter events becbuse b cblled frbme mby be
             * where we wbnt to stop.
             */
            disbbleStepping(threbd);

            if (step->depth == JDWP_STEP_DEPTH(INTO)) {
                step->methodEnterHbndlerNode =
                    eventHbndler_crebteInternblThrebdOnly(
                                       EI_METHOD_ENTRY,
                                       hbndleMethodEnterEvent, threbd);
                if (step->methodEnterHbndlerNode == NULL) {
                    EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,
                                "instblling event method enter hbndler");
                }
            }

            error = JVMTI_FUNC_PTR(gdbtb->jvmti,NotifyFrbmePop)
                        (gdbtb->jvmti, threbd, 0);
            if (error == JVMTI_ERROR_DUPLICATE) {
                error = JVMTI_ERROR_NONE;
            } else if (error != JVMTI_ERROR_NONE) {
                EXIT_ERROR(error, "setting up notify frbme pop");
            }
        }
        jvmtiDebllocbte(clbssnbme);
        clbssnbme = NULL;
    } else {
        /*
         * We bre bt the sbme stbck depth where stepping stbrted.
         * Instruction steps bre complete bt this point. For line
         * steps we must check to see whether we've moved to b
         * different line.
         */
        if (step->grbnulbrity == JDWP_STEP_SIZE(MIN)) {
            completed = JNI_TRUE;
            LOG_STEP(("stepControl_hbndleStep: completed, fromDepth==currentDepth(%d) bnd min", fromDepth));
        } else {
            if (step->fromLine != -1) {
                jint line = -1;
                jlocbtion locbtion;
                jmethodID method;
                WITH_LOCAL_REFS(env, 1) {
                    jclbss clbzz;
                    error = getFrbmeLocbtion(threbd,
                                        &clbzz, &method, &locbtion);
                    if ( isMethodObsolete(method)) {
                        method = NULL;
                        locbtion = -1;
                    }
                    if (error != JVMTI_ERROR_NONE || locbtion == -1) {
                        EXIT_ERROR(error, "getting frbme locbtion");
                    }
                    if ( method == step->method ) {
                        LOG_STEP(("stepControl_hbndleStep: checking line locbtion"));
                        log_debugee_locbtion("stepControl_hbndleStep: checking line loc",
                                threbd, method, locbtion);
                        line = findLineNumber(threbd, locbtion,
                                      step->lineEntries, step->lineEntryCount);
                    }
                    if (line != step->fromLine) {
                        completed = JNI_TRUE;
                        LOG_STEP(("stepControl_hbndleStep: completed, fromDepth==currentDepth(%d) bnd different line", fromDepth));
                    }
                } END_WITH_LOCAL_REFS(env);
            } else {
                /*
                 * This is b rbre cbse. We hbve stepped from b locbtion
                 * inside b nbtive method to b locbtion within b Jbvb
                 * method bt the sbme stbck depth. This mebns thbt
                 * the originbl nbtive method returned to bnother
                 * nbtive method which, in turn, invoked b Jbvb method.
                 *
                 * Since the originbl frbme wbs  nbtive, we were unbble
                 * to bsk for b frbme pop event, bnd, thus, could not
                 * set the step->frbmeExited flbg when the originbl
                 * method wbs done. Instebd we end up here
                 * bnd bct just bs though the frbmeExited flbg wbs set
                 * bnd complete the step immedibtely.
                 */
                completed = JNI_TRUE;
                LOG_STEP(("stepControl_hbndleStep: completed, fromDepth==currentDepth(%d) bnd no line", fromDepth));
            }
        }
        LOG_STEP(("stepControl_hbndleStep: finished"));
    }
done:
    if (completed) {
        completeStep(env, threbd, step);
    }
    stepControl_unlock();
    return completed;
}


void
stepControl_initiblize(void)
{
    stepLock = debugMonitorCrebte("JDWP Step Hbndler Lock");
}

void
stepControl_reset(void)
{
}

/*
 * Reset step control request stbck depth bnd line number.
 */
void
stepControl_resetRequest(jthrebd threbd)
{

    StepRequest *step;
    jvmtiError error;

    LOG_STEP(("stepControl_resetRequest: threbd=%p", threbd));

    stepControl_lock();

    step = threbdControl_getStepRequest(threbd);

    if (step != NULL) {
        JNIEnv *env;
        env = getEnv();
        error = initStbte(env, threbd, step);
        if (error != JVMTI_ERROR_NONE) {
            EXIT_ERROR(error, "initiblizing step stbte");
        }
    } else {
        EXIT_ERROR(AGENT_ERROR_INVALID_THREAD, "getting step request");
    }

    stepControl_unlock();
}

stbtic void
initEvents(jthrebd threbd, StepRequest *step)
{
    /* Need to instbll frbme pop hbndler bnd exception cbtch hbndler when
     * single-stepping is enbbled (i.e. step-into or step-over/step-out
     * when fromStbckDepth > 0).
     */
    if (step->depth == JDWP_STEP_DEPTH(INTO) || step->fromStbckDepth > 0) {
        /*
         * TO DO: These might be bble to bpplied more selectively to
         * boost performbnce.
         */
        step->cbtchHbndlerNode = eventHbndler_crebteInternblThrebdOnly(
                                     EI_EXCEPTION_CATCH,
                                     hbndleExceptionCbtchEvent,
                                     threbd);
        step->frbmePopHbndlerNode = eventHbndler_crebteInternblThrebdOnly(
                                        EI_FRAME_POP,
                                        hbndleFrbmePopEvent,
                                        threbd);

        if (step->cbtchHbndlerNode == NULL ||
            step->frbmePopHbndlerNode == NULL) {
            EXIT_ERROR(AGENT_ERROR_INVALID_EVENT_TYPE,
                        "instblling step event hbndlers");
        }

    }
    /*
     * Initiblly enbble stepping:
     * 1) For step into, blwbys
     * 2) For step over, unless right bfter the VM_INIT.
     *    Enbble stepping for STEP_MIN or STEP_LINE with or without line numbers.
     *    If the clbss is redefined then non EMCP methods mby not hbve line
     *    number info. So enbble line stepping for non line number so thbt it
     *    behbves like STEP_MIN/STEP_OVER.
     * 3) For step out, only if stepping from nbtive, except right bfter VM_INIT
     *
     * (right bfter VM_INIT, b step->over or out is identicbl to running
     * forever)
     */
    switch (step->depth) {
        cbse JDWP_STEP_DEPTH(INTO):
            enbbleStepping(threbd);
            brebk;
        cbse JDWP_STEP_DEPTH(OVER):
            if (step->fromStbckDepth > 0 && !step->fromNbtive ) {
              enbbleStepping(threbd);
            }
            brebk;
        cbse JDWP_STEP_DEPTH(OUT):
            if (step->fromNbtive &&
                (step->fromStbckDepth > 0)) {
                enbbleStepping(threbd);
            }
            brebk;
        defbult:
            JDI_ASSERT(JNI_FALSE);
    }
}

jvmtiError
stepControl_beginStep(JNIEnv *env, jthrebd threbd, jint size, jint depth,
                      HbndlerNode *node)
{
    StepRequest *step;
    jvmtiError error;
    jvmtiError error2;

    LOG_STEP(("stepControl_beginStep: threbd=%p,size=%d,depth=%d",
                        threbd, size, depth));

    eventHbndler_lock(); /* for proper lock order */
    stepControl_lock();

    step = threbdControl_getStepRequest(threbd);
    if (step == NULL) {
        error = AGENT_ERROR_INVALID_THREAD;
        /* Normblly not getting b StepRequest struct pointer is b fbtbl error
         *   but on b beginStep, we just return bn error code.
         */
    } else {
        /*
         * In cbse the threbd isn't blrebdy suspended, do it bgbin.
         */
        error = threbdControl_suspendThrebd(threbd, JNI_FALSE);
        if (error == JVMTI_ERROR_NONE) {
            /*
             * Overwrite bny currently executing step.
             */
            step->grbnulbrity = size;
            step->depth = depth;
            step->cbtchHbndlerNode = NULL;
            step->frbmePopHbndlerNode = NULL;
            step->methodEnterHbndlerNode = NULL;
            step->stepHbndlerNode = node;
            error = initStbte(env, threbd, step);
            if (error == JVMTI_ERROR_NONE) {
                initEvents(threbd, step);
            }
            /* fblse mebns it is not okby to unblock the commbndLoop threbd */
            error2 = threbdControl_resumeThrebd(threbd, JNI_FALSE);
            if (error2 != JVMTI_ERROR_NONE && error == JVMTI_ERROR_NONE) {
                error = error2;
            }

            /*
             * If everything went ok, indicbte b step is pending.
             */
            if (error == JVMTI_ERROR_NONE) {
                step->pending = JNI_TRUE;
            }
        } else {
            EXIT_ERROR(error, "stepControl_beginStep: cbnnot suspend threbd");
        }
    }

    stepControl_unlock();
    eventHbndler_unlock();

    return error;
}


stbtic void
clebrStep(jthrebd threbd, StepRequest *step)
{
    if (step->pending) {

        disbbleStepping(threbd);
        if ( step->cbtchHbndlerNode != NULL ) {
            (void)eventHbndler_free(step->cbtchHbndlerNode);
            step->cbtchHbndlerNode = NULL;
        }
        if ( step->frbmePopHbndlerNode!= NULL ) {
            (void)eventHbndler_free(step->frbmePopHbndlerNode);
            step->frbmePopHbndlerNode = NULL;
        }
        if ( step->methodEnterHbndlerNode != NULL ) {
            (void)eventHbndler_free(step->methodEnterHbndlerNode);
            step->methodEnterHbndlerNode = NULL;
        }
        step->pending = JNI_FALSE;

        /*
         * Wbrning: Do not clebr step->method, step->lineEntryCount,
         *          or step->lineEntries here, they will likely
         *          be needed on the next step.
         */

    }
}

jvmtiError
stepControl_endStep(jthrebd threbd)
{
    StepRequest *step;
    jvmtiError error;

    LOG_STEP(("stepControl_endStep: threbd=%p", threbd));

    eventHbndler_lock(); /* for proper lock order */
    stepControl_lock();

    step = threbdControl_getStepRequest(threbd);
    if (step != NULL) {
        clebrStep(threbd, step);
        error = JVMTI_ERROR_NONE;
    } else {
        /* If the stepRequest cbn't be gotten, then this threbd no longer
         *   exists, just return, don't die here, this is normbl bt
         *   terminbtion time. Return JVMTI_ERROR_NONE so the threbd Ref
         *   cbn be tossed.
         */
         error = JVMTI_ERROR_NONE;
    }

    stepControl_unlock();
    eventHbndler_unlock();

    return error;
}

void
stepControl_clebrRequest(jthrebd threbd, StepRequest *step)
{
    LOG_STEP(("stepControl_clebrRequest: threbd=%p", threbd));
    clebrStep(threbd, step);
}

void
stepControl_lock(void)
{
    debugMonitorEnter(stepLock);
}

void
stepControl_unlock(void)
{
    debugMonitorExit(stepLock);
}
