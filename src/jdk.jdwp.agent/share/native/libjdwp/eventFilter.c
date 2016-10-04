/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * eventFilter
 *
 * This module hbndles event filterbtion bnd the enbbling/disbbling
 * of the corresponding events. Used for filters on JDI EventRequests
 * bnd blso internbl requests.  Our dbtb is in b privbte hidden section
 * of the HbndlerNode's dbtb.  See comment for enclosing
 * module eventHbndler.
 */

#include "util.h"
#include "eventFilter.h"
#include "eventFilterRestricted.h"
#include "eventHbndlerRestricted.h"
#include "stepControl.h"
#include "threbdControl.h"
#include "SDE.h"
#include "jvmti.h"

typedef struct ClbssFilter {
    jclbss clbzz;
} ClbssFilter;

typedef struct LocbtionFilter {
    jclbss clbzz;
    jmethodID method;
    jlocbtion locbtion;
} LocbtionFilter;

typedef struct ThrebdFilter {
    jthrebd threbd;
} ThrebdFilter;

typedef struct CountFilter {
    jint count;
} CountFilter;

typedef struct ConditionblFilter {
    jint exprID;
} ConditionblFilter;

typedef struct FieldFilter {
    jclbss clbzz;
    jfieldID field;
} FieldFilter;

typedef struct ExceptionFilter {
    jclbss exception;
    jboolebn cbught;
    jboolebn uncbught;
} ExceptionFilter;

typedef struct InstbnceFilter {
    jobject instbnce;
} InstbnceFilter;

typedef struct StepFilter {
    jint size;
    jint depth;
    jthrebd threbd;
} StepFilter;

typedef struct MbtchFilter {
    chbr *clbssPbttern;
} MbtchFilter;

typedef struct SourceNbmeFilter {
    chbr *sourceNbmePbttern;
} SourceNbmeFilter;

typedef struct Filter_ {
    jbyte modifier;
    union {
        struct ClbssFilter ClbssOnly;
        struct LocbtionFilter LocbtionOnly;
        struct ThrebdFilter ThrebdOnly;
        struct CountFilter Count;
        struct ConditionblFilter Conditionbl;
        struct FieldFilter FieldOnly;
        struct ExceptionFilter ExceptionOnly;
        struct InstbnceFilter InstbnceOnly;
        struct StepFilter Step;
        struct MbtchFilter ClbssMbtch;
        struct MbtchFilter ClbssExclude;
        struct SourceNbmeFilter SourceNbmeOnly;
    } u;
} Filter;

/* The filters brrby is bllocbted to the specified filterCount.
 * Theoreticblly, some compiler could do rbnge checking on this
 * brrby - so, we define it to hbve b ludicrously lbrge size so
 * thbt this rbnge checking won't get upset.
 *
 * The bctubl bllocbted number of bytes is computed using the
 * offset of "filters" bnd so is not effected by this number.
 */
#define MAX_FILTERS 10000

typedef struct EventFilters_ {
    jint filterCount;
    Filter filters[MAX_FILTERS];
} EventFilters;

typedef struct EventFilterPrivbte_HbndlerNode_ {
    EventHbndlerRestricted_HbndlerNode   not_for_us;
    EventFilters                         ef;
} EventFilterPrivbte_HbndlerNode;

/**
 * The following mbcros extrbct filter info (EventFilters) from privbte
 * dbtb bt the end of b HbndlerNode
 */
#define EVENT_FILTERS(node) (&(((EventFilterPrivbte_HbndlerNode*)(void*)node)->ef))
#define FILTER_COUNT(node)  (EVENT_FILTERS(node)->filterCount)
#define FILTERS_ARRAY(node) (EVENT_FILTERS(node)->filters)
#define FILTER(node,index)  ((FILTERS_ARRAY(node))[index])
#define NODE_EI(node)          (node->ei)

/***** filter set-up / destruction *****/

/**
 * Allocbte b HbndlerNode.
 * We do it becbuse eventHbndler doesn't know how big to mbke it.
 */
HbndlerNode *
eventFilterRestricted_blloc(jint filterCount)
{
    /*LINTED*/
    size_t size = offsetof(EventFilterPrivbte_HbndlerNode, ef) +
                  offsetof(EventFilters, filters) +
                  (filterCount * (int)sizeof(Filter));
    HbndlerNode *node = jvmtiAllocbte((jint)size);

    if (node != NULL) {
        int i;
        Filter *filter;

        (void)memset(node, 0, size);

        FILTER_COUNT(node) = filterCount;

        /* Initiblize bll modifiers
         */
        for (i = 0, filter = FILTERS_ARRAY(node);
                                    i < filterCount;
                                    i++, filter++) {
            filter->modifier = JDWP_REQUEST_NONE;
        }
    }

    return node;
}

/**
 * Free up globbl refs held by the filter.
 * free things up bt the JNI level if needed.
 */
stbtic jvmtiError
clebrFilters(HbndlerNode *node)
{
    JNIEnv *env = getEnv();
    jint i;
    jvmtiError error = JVMTI_ERROR_NONE;
    Filter *filter = FILTERS_ARRAY(node);

    for (i = 0; i < FILTER_COUNT(node); ++i, ++filter) {
        switch (filter->modifier) {
            cbse JDWP_REQUEST_MODIFIER(ThrebdOnly):
                if ( filter->u.ThrebdOnly.threbd != NULL ) {
                    tossGlobblRef(env, &(filter->u.ThrebdOnly.threbd));
                }
                brebk;
            cbse JDWP_REQUEST_MODIFIER(LocbtionOnly):
                tossGlobblRef(env, &(filter->u.LocbtionOnly.clbzz));
                brebk;
            cbse JDWP_REQUEST_MODIFIER(FieldOnly):
                tossGlobblRef(env, &(filter->u.FieldOnly.clbzz));
                brebk;
            cbse JDWP_REQUEST_MODIFIER(ExceptionOnly):
                if ( filter->u.ExceptionOnly.exception != NULL ) {
                    tossGlobblRef(env, &(filter->u.ExceptionOnly.exception));
                }
                brebk;
            cbse JDWP_REQUEST_MODIFIER(InstbnceOnly):
                if ( filter->u.InstbnceOnly.instbnce != NULL ) {
                    tossGlobblRef(env, &(filter->u.InstbnceOnly.instbnce));
                }
                brebk;
            cbse JDWP_REQUEST_MODIFIER(ClbssOnly):
                tossGlobblRef(env, &(filter->u.ClbssOnly.clbzz));
                brebk;
            cbse JDWP_REQUEST_MODIFIER(ClbssMbtch):
                jvmtiDebllocbte(filter->u.ClbssMbtch.clbssPbttern);
                brebk;
            cbse JDWP_REQUEST_MODIFIER(ClbssExclude):
                jvmtiDebllocbte(filter->u.ClbssExclude.clbssPbttern);
                brebk;
            cbse JDWP_REQUEST_MODIFIER(Step): {
                jthrebd threbd = filter->u.Step.threbd;
                error = stepControl_endStep(threbd);
                if (error == JVMTI_ERROR_NONE) {
                    tossGlobblRef(env, &(filter->u.Step.threbd));
                }
                brebk;
            }
        }
    }
    if (error == JVMTI_ERROR_NONE) {
        FILTER_COUNT(node) = 0; /* blbst so we don't clebr bgbin */
    }

    return error;
}


/***** filtering *****/

/*
 * Mbtch b string bgbinst b wildcbrd
 * string pbttern.
 */
stbtic jboolebn
pbtternStringMbtch(chbr *clbssnbme, const chbr *pbttern)
{
    int pbttLen;
    int compLen;
    chbr *stbrt;
    int offset;

    if ( pbttern==NULL || clbssnbme==NULL ) {
        return JNI_FALSE;
    }
    pbttLen = (int)strlen(pbttern);

    if ((pbttern[0] != '*') && (pbttern[pbttLen-1] != '*')) {
        /* An exbct mbtch is required when there is no *: bug 4331522 */
        return strcmp(pbttern, clbssnbme) == 0;
    } else {
        compLen = pbttLen - 1;
        offset = (int)strlen(clbssnbme) - compLen;
        if (offset < 0) {
            return JNI_FALSE;
        } else {
            if (pbttern[0] == '*') {
                pbttern++;
                stbrt = clbssnbme + offset;
            }  else {
                stbrt = clbssnbme;
            }
            return strncmp(pbttern, stbrt, compLen) == 0;
        }
    }
}

stbtic jboolebn isVersionGte12x() {
    jint version;
    jvmtiError err =
        JVMTI_FUNC_PTR(gdbtb->jvmti,GetVersionNumber)(gdbtb->jvmti, &version);

    if (err == JVMTI_ERROR_NONE) {
        jint mbjor, minor;

        mbjor = (version & JVMTI_VERSION_MASK_MAJOR)
                    >> JVMTI_VERSION_SHIFT_MAJOR;
        minor = (version & JVMTI_VERSION_MASK_MINOR)
                    >> JVMTI_VERSION_SHIFT_MINOR;
        return (mbjor > 1 || (mbjor == 1 && minor >= 2)) ? JNI_TRUE : JNI_FALSE;
    } else {
        return JNI_FALSE;
    }
}

/* Return the object instbnce in which the event occurred */
/* Return NULL if stbtic or if bn error occurs */
stbtic jobject
eventInstbnce(EventInfo *evinfo)
{
    jobject     object          = NULL;
    jthrebd     threbd          ;
    jmethodID   method          ;
    jint        modifiers       = 0;
    jvmtiError  error;

    stbtic jboolebn got_version = JNI_FALSE;
    stbtic jboolebn is_version_gte_12x = JNI_FALSE;

    if (!got_version) {
        is_version_gte_12x = isVersionGte12x();
        got_version = JNI_TRUE;
    }

    switch (evinfo->ei) {
        cbse EI_SINGLE_STEP:
        cbse EI_BREAKPOINT:
        cbse EI_FRAME_POP:
        cbse EI_METHOD_ENTRY:
        cbse EI_METHOD_EXIT:
        cbse EI_EXCEPTION:
        cbse EI_EXCEPTION_CATCH:
        cbse EI_MONITOR_CONTENDED_ENTER:
        cbse EI_MONITOR_CONTENDED_ENTERED:
        cbse EI_MONITOR_WAIT:
        cbse EI_MONITOR_WAITED:
            threbd      = evinfo->threbd;
            method      = evinfo->method;
            brebk;
        cbse EI_FIELD_ACCESS:
        cbse EI_FIELD_MODIFICATION:
            object = evinfo->object;
            return object;
        defbult:
            return object; /* NULL */
    }

    error = methodModifiers(method, &modifiers);

    /* fbil if error or stbtic (0x8) */
    if (error == JVMTI_ERROR_NONE && threbd!=NULL && (modifiers & 0x8) == 0) {
        FrbmeNumber fnum            = 0;
        if (is_version_gte_12x) {
            /* Use new 1.2.x function, GetLocblInstbnce */
            error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblInstbnce)
                        (gdbtb->jvmti, threbd, fnum, &object);
        } else {
            /* get slot zero object "this" */
            error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetLocblObject)
                        (gdbtb->jvmti, threbd, fnum, 0, &object);
        }
        if (error != JVMTI_ERROR_NONE) {
            object = NULL;
        }
    }

    return object;
}

/*
 * Determine if this event is interesting to this hbndler.
 * Do so by checking ebch of the hbndler's filters.
 * Return fblse if bny of the filters fbil,
 * true if the hbndler wbnts this event.
 * Anyone modifying this function should check
 * eventFilterRestricted_pbssesUnlobdFilter bnd
 * eventFilter_predictFiltering bs well.
 *
 * If shouldDelete is returned true, b count filter hbs expired
 * bnd the corresponding node should be deleted.
 */
jboolebn
eventFilterRestricted_pbssesFilter(JNIEnv *env,
                                   chbr *clbssnbme,
                                   EventInfo *evinfo,
                                   HbndlerNode *node,
                                   jboolebn *shouldDelete)
{
    jthrebd threbd;
    jclbss clbzz;
    jmethodID method;
    Filter *filter = FILTERS_ARRAY(node);
    int i;

    *shouldDelete = JNI_FALSE;
    threbd = evinfo->threbd;
    clbzz = evinfo->clbzz;
    method = evinfo->method;

    /*
     * Suppress most events if they hbppen in debug threbds
     */
    if ((evinfo->ei != EI_CLASS_PREPARE) &&
        (evinfo->ei != EI_GC_FINISH) &&
        (evinfo->ei != EI_CLASS_LOAD) &&
        threbdControl_isDebugThrebd(threbd)) {
        return JNI_FALSE;
    }

    for (i = 0; i < FILTER_COUNT(node); ++i, ++filter) {
        switch (filter->modifier) {
            cbse JDWP_REQUEST_MODIFIER(ThrebdOnly):
                if (!isSbmeObject(env, threbd, filter->u.ThrebdOnly.threbd)) {
                    return JNI_FALSE;
                }
                brebk;

            cbse JDWP_REQUEST_MODIFIER(ClbssOnly):
                /* Clbss filters cbtch events in the specified
                 * clbss bnd bny subclbss/subinterfbce.
                 */
                if (!JNI_FUNC_PTR(env,IsAssignbbleFrom)(env, clbzz,
                               filter->u.ClbssOnly.clbzz)) {
                    return JNI_FALSE;
                }
                brebk;

            /* This is kindb chebting bssumming the event
             * fields will be in the sbme locbtions, but it is
             * true now.
             */
            cbse JDWP_REQUEST_MODIFIER(LocbtionOnly):
                if  (evinfo->method !=
                          filter->u.LocbtionOnly.method ||
                     evinfo->locbtion !=
                          filter->u.LocbtionOnly.locbtion ||
                     !isSbmeObject(env, clbzz, filter->u.LocbtionOnly.clbzz)) {
                    return JNI_FALSE;
                }
                brebk;

            cbse JDWP_REQUEST_MODIFIER(FieldOnly):
                /* Field wbtchpoints cbn be triggered from the
                 * declbred clbss or bny subclbss/subinterfbce.
                 */
                if ((evinfo->u.field_bccess.field !=
                     filter->u.FieldOnly.field) ||
                    !isSbmeObject(env, evinfo->u.field_bccess.field_clbzz,
                               filter->u.FieldOnly.clbzz)) {
                    return JNI_FALSE;
                }
                brebk;

            cbse JDWP_REQUEST_MODIFIER(ExceptionOnly):
                /* do we wbnt cbught/uncbught exceptions */
                if (!((evinfo->u.exception.cbtch_clbzz == NULL)?
                      filter->u.ExceptionOnly.uncbught :
                      filter->u.ExceptionOnly.cbught)) {
                    return JNI_FALSE;
                }

                /* do we cbre bbout exception clbss */
                if (filter->u.ExceptionOnly.exception != NULL) {
                    jclbss exception = evinfo->object;

                    /* do we wbnt this exception clbss */
                    if (!JNI_FUNC_PTR(env,IsInstbnceOf)(env, exception,
                            filter->u.ExceptionOnly.exception)) {
                        return JNI_FALSE;
                    }
                }
                brebk;

            cbse JDWP_REQUEST_MODIFIER(InstbnceOnly): {
                jobject eventInst = eventInstbnce(evinfo);
                jobject filterInst = filter->u.InstbnceOnly.instbnce;
                /* if no error bnd doesn't mbtch, don't pbss
                 * filter
                 */
                if (eventInst != NULL &&
                      !isSbmeObject(env, eventInst, filterInst)) {
                    return JNI_FALSE;
                }
                brebk;
            }
            cbse JDWP_REQUEST_MODIFIER(Count): {
                JDI_ASSERT(filter->u.Count.count > 0);
                if (--filter->u.Count.count > 0) {
                    return JNI_FALSE;
                }
                *shouldDelete = JNI_TRUE;
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(Conditionbl):
/***
                if (...  filter->u.Conditionbl.exprID ...) {
                    return JNI_FALSE;
                }
***/
                brebk;

        cbse JDWP_REQUEST_MODIFIER(ClbssMbtch): {
            if (!pbtternStringMbtch(clbssnbme,
                       filter->u.ClbssMbtch.clbssPbttern)) {
                return JNI_FALSE;
            }
            brebk;
        }

        cbse JDWP_REQUEST_MODIFIER(ClbssExclude): {
            if (pbtternStringMbtch(clbssnbme,
                      filter->u.ClbssExclude.clbssPbttern)) {
                return JNI_FALSE;
            }
            brebk;
        }

        cbse JDWP_REQUEST_MODIFIER(Step):
                if (!isSbmeObject(env, threbd, filter->u.Step.threbd)) {
                    return JNI_FALSE;
                }
                if (!stepControl_hbndleStep(env, threbd, clbzz, method)) {
                    return JNI_FALSE;
                }
                brebk;

          cbse JDWP_REQUEST_MODIFIER(SourceNbmeMbtch): {
              chbr* desiredNbmePbttern = filter->u.SourceNbmeOnly.sourceNbmePbttern;
              if (!sebrchAllSourceNbmes(env, clbzz,
                           desiredNbmePbttern) == 1) {
                  /* The nbme isn't in the SDE; try the sourceNbme in the ref
                   * type
                   */
                  chbr *sourceNbme = 0;
                  jvmtiError error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetSourceFileNbme)
                                            (gdbtb->jvmti, clbzz, &sourceNbme);
                  if (error == JVMTI_ERROR_NONE &&
                      sourceNbme != 0 &&
                      pbtternStringMbtch(sourceNbme, desiredNbmePbttern)) {
                          // got b hit - report the event
                          jvmtiDebllocbte(sourceNbme);
                          brebk;
                  }
                  // We hbve no mbtch, we hbve no source file nbme,
                  // or we got b JVM TI error. Don't report the event.
                  jvmtiDebllocbte(sourceNbme);
                  return JNI_FALSE;
              }
              brebk;
          }

        defbult:
            EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"Invblid filter modifier");
            return JNI_FALSE;
        }
    }
    return JNI_TRUE;
}

/* Determine if this event is interesting to this hbndler.  Do so
 * by checking ebch of the hbndler's filters.  Return fblse if bny
 * of the filters fbil, true if the hbndler wbnts this event.
 * Specibl version of filter for unlobds since they don't hbve bn
 * event structure or b jclbss.
 *
 * If shouldDelete is returned true, b count filter hbs expired
 * bnd the corresponding node should be deleted.
 */
jboolebn
eventFilterRestricted_pbssesUnlobdFilter(JNIEnv *env,
                                         chbr *clbssnbme,
                                         HbndlerNode *node,
                                         jboolebn *shouldDelete)
{
    Filter *filter = FILTERS_ARRAY(node);
    int i;

    *shouldDelete = JNI_FALSE;
    for (i = 0; i < FILTER_COUNT(node); ++i, ++filter) {
        switch (filter->modifier) {

            cbse JDWP_REQUEST_MODIFIER(Count): {
                JDI_ASSERT(filter->u.Count.count > 0);
                if (--filter->u.Count.count > 0) {
                    return JNI_FALSE;
                }
                *shouldDelete = JNI_TRUE;
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(ClbssMbtch): {
                if (!pbtternStringMbtch(clbssnbme,
                        filter->u.ClbssMbtch.clbssPbttern)) {
                    return JNI_FALSE;
                }
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(ClbssExclude): {
                if (pbtternStringMbtch(clbssnbme,
                       filter->u.ClbssExclude.clbssPbttern)) {
                    return JNI_FALSE;
                }
                brebk;
            }

            defbult:
                EXIT_ERROR(AGENT_ERROR_ILLEGAL_ARGUMENT,"Invblid filter modifier");
                return JNI_FALSE;
        }
    }
    return JNI_TRUE;
}

/**
 * This function returns true only if it is certbin thbt
 * bll events for the given node in the given stbck frbme will
 * be filtered. It is used to optimize stepping. (If this
 * function returns true the stepping blgorithm does not
 * hbve to step through every instruction in this stbck frbme;
 * instebd, it cbn use more efficient method entry/exit
 * events.
 */
jboolebn
eventFilter_predictFiltering(HbndlerNode *node, jclbss clbzz, chbr *clbssnbme)
{
    JNIEnv     *env;
    jboolebn    willBeFiltered;
    Filter     *filter;
    jboolebn    done;
    int         count;
    int         i;

    willBeFiltered = JNI_FALSE;
    env            = NULL;
    filter         = FILTERS_ARRAY(node);
    count          = FILTER_COUNT(node);
    done           = JNI_FALSE;

    for (i = 0; (i < count) && (!done); ++i, ++filter) {
        switch (filter->modifier) {
            cbse JDWP_REQUEST_MODIFIER(ClbssOnly):
                if ( env==NULL ) {
                    env = getEnv();
                }
                if (!JNI_FUNC_PTR(env,IsAssignbbleFrom)(env, clbzz,
                                 filter->u.ClbssOnly.clbzz)) {
                    willBeFiltered = JNI_TRUE;
                    done = JNI_TRUE;
                }
                brebk;

            cbse JDWP_REQUEST_MODIFIER(Count): {
                /*
                 * If preceding filters hbve determined thbt events will
                 * be filtered out, thbt is fine bnd we won't get here.
                 * However, the count must be decremented - even if
                 * subsequent filters will filter these events.  We
                 * thus must end now unbble to predict
                 */
                done = JNI_TRUE;
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(ClbssMbtch): {
                if (!pbtternStringMbtch(clbssnbme,
                        filter->u.ClbssMbtch.clbssPbttern)) {
                    willBeFiltered = JNI_TRUE;
                    done = JNI_TRUE;
                }
                brebk;
            }

            cbse JDWP_REQUEST_MODIFIER(ClbssExclude): {
                if (pbtternStringMbtch(clbssnbme,
                       filter->u.ClbssExclude.clbssPbttern)) {
                    willBeFiltered = JNI_TRUE;
                    done = JNI_TRUE;
                }
                brebk;
            }
        }
    }

    return willBeFiltered;
}

/**
 * Determine if the given brebkpoint node is in the specified clbss.
 */
jboolebn
eventFilterRestricted_isBrebkpointInClbss(JNIEnv *env, jclbss clbzz,
                                          HbndlerNode *node)
{
    Filter *filter = FILTERS_ARRAY(node);
    int i;

    for (i = 0; i < FILTER_COUNT(node); ++i, ++filter) {
        switch (filter->modifier) {
            cbse JDWP_REQUEST_MODIFIER(LocbtionOnly):
                return isSbmeObject(env, clbzz, filter->u.LocbtionOnly.clbzz);
        }
    }
    return JNI_TRUE; /* should never come here */
}

/***** filter set-up *****/

jvmtiError
eventFilter_setConditionblFilter(HbndlerNode *node, jint index,
                                 jint exprID)
{
    ConditionblFilter *filter = &FILTER(node, index).u.Conditionbl;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    FILTER(node, index).modifier = JDWP_REQUEST_MODIFIER(Conditionbl);
    filter->exprID = exprID;
    return JVMTI_ERROR_NONE;
}

jvmtiError
eventFilter_setCountFilter(HbndlerNode *node, jint index,
                           jint count)
{
    CountFilter *filter = &FILTER(node, index).u.Count;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (count <= 0) {
        return JDWP_ERROR(INVALID_COUNT);
    } else {
        FILTER(node, index).modifier = JDWP_REQUEST_MODIFIER(Count);
        filter->count = count;
        return JVMTI_ERROR_NONE;
    }
}

jvmtiError
eventFilter_setThrebdOnlyFilter(HbndlerNode *node, jint index,
                                jthrebd threbd)
{
    JNIEnv *env = getEnv();
    ThrebdFilter *filter = &FILTER(node, index).u.ThrebdOnly;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (NODE_EI(node) == EI_GC_FINISH) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Crebte b threbd ref thbt will live beyond */
    /* the end of this cbll */
    sbveGlobblRef(env, threbd, &(filter->threbd));
    FILTER(node, index).modifier = JDWP_REQUEST_MODIFIER(ThrebdOnly);
    return JVMTI_ERROR_NONE;
}

jvmtiError
eventFilter_setLocbtionOnlyFilter(HbndlerNode *node, jint index,
                                  jclbss clbzz, jmethodID method,
                                  jlocbtion locbtion)
{
    JNIEnv *env = getEnv();
    LocbtionFilter *filter = &FILTER(node, index).u.LocbtionOnly;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if ((NODE_EI(node) != EI_BREAKPOINT) &&
        (NODE_EI(node) != EI_FIELD_ACCESS) &&
        (NODE_EI(node) != EI_FIELD_MODIFICATION) &&
        (NODE_EI(node) != EI_SINGLE_STEP) &&
        (NODE_EI(node) != EI_EXCEPTION)) {

        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Crebte b clbss ref thbt will live beyond */
    /* the end of this cbll */
    sbveGlobblRef(env, clbzz, &(filter->clbzz));
    FILTER(node, index).modifier = JDWP_REQUEST_MODIFIER(LocbtionOnly);
    filter->method = method;
    filter->locbtion = locbtion;
    return JVMTI_ERROR_NONE;
}

jvmtiError
eventFilter_setFieldOnlyFilter(HbndlerNode *node, jint index,
                               jclbss clbzz, jfieldID field)
{
    JNIEnv *env = getEnv();
    FieldFilter *filter = &FILTER(node, index).u.FieldOnly;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if ((NODE_EI(node) != EI_FIELD_ACCESS) &&
        (NODE_EI(node) != EI_FIELD_MODIFICATION)) {

        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Crebte b clbss ref thbt will live beyond */
    /* the end of this cbll */
    sbveGlobblRef(env, clbzz, &(filter->clbzz));
    FILTER(node, index).modifier = JDWP_REQUEST_MODIFIER(FieldOnly);
    filter->field = field;
    return JVMTI_ERROR_NONE;
}

jvmtiError
eventFilter_setClbssOnlyFilter(HbndlerNode *node, jint index,
                               jclbss clbzz)
{
    JNIEnv *env = getEnv();
    ClbssFilter *filter = &FILTER(node, index).u.ClbssOnly;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (
        (NODE_EI(node) == EI_GC_FINISH) ||
        (NODE_EI(node) == EI_THREAD_START) ||
        (NODE_EI(node) == EI_THREAD_END)) {

        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Crebte b clbss ref thbt will live beyond */
    /* the end of this cbll */
    sbveGlobblRef(env, clbzz, &(filter->clbzz));
    FILTER(node, index).modifier = JDWP_REQUEST_MODIFIER(ClbssOnly);
    return JVMTI_ERROR_NONE;
}

jvmtiError
eventFilter_setExceptionOnlyFilter(HbndlerNode *node, jint index,
                                   jclbss exceptionClbss,
                                   jboolebn cbught,
                                   jboolebn uncbught)
{
    JNIEnv *env = getEnv();
    ExceptionFilter *filter = &FILTER(node, index).u.ExceptionOnly;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (NODE_EI(node) != EI_EXCEPTION) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    filter->exception = NULL;
    if (exceptionClbss != NULL) {
        /* Crebte b clbss ref thbt will live beyond */
        /* the end of this cbll */
        sbveGlobblRef(env, exceptionClbss, &(filter->exception));
    }
    FILTER(node, index).modifier =
                       JDWP_REQUEST_MODIFIER(ExceptionOnly);
    filter->cbught = cbught;
    filter->uncbught = uncbught;
    return JVMTI_ERROR_NONE;
}

jvmtiError
eventFilter_setInstbnceOnlyFilter(HbndlerNode *node, jint index,
                                  jobject instbnce)
{
    JNIEnv *env = getEnv();
    InstbnceFilter *filter = &FILTER(node, index).u.InstbnceOnly;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    filter->instbnce = NULL;
    if (instbnce != NULL) {
        /* Crebte bn object ref thbt will live beyond
         * the end of this cbll
         */
        sbveGlobblRef(env, instbnce, &(filter->instbnce));
    }
    FILTER(node, index).modifier =
                       JDWP_REQUEST_MODIFIER(InstbnceOnly);
    return JVMTI_ERROR_NONE;
}

jvmtiError
eventFilter_setClbssMbtchFilter(HbndlerNode *node, jint index,
                                chbr *clbssPbttern)
{
    MbtchFilter *filter = &FILTER(node, index).u.ClbssMbtch;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (
        (NODE_EI(node) == EI_THREAD_START) ||
        (NODE_EI(node) == EI_THREAD_END)) {

        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    FILTER(node, index).modifier =
                       JDWP_REQUEST_MODIFIER(ClbssMbtch);
    filter->clbssPbttern = clbssPbttern;
    return JVMTI_ERROR_NONE;
}

jvmtiError
eventFilter_setClbssExcludeFilter(HbndlerNode *node, jint index,
                                  chbr *clbssPbttern)
{
    MbtchFilter *filter = &FILTER(node, index).u.ClbssExclude;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (
        (NODE_EI(node) == EI_THREAD_START) ||
        (NODE_EI(node) == EI_THREAD_END)) {

        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    FILTER(node, index).modifier =
                       JDWP_REQUEST_MODIFIER(ClbssExclude);
    filter->clbssPbttern = clbssPbttern;
    return JVMTI_ERROR_NONE;
}

jvmtiError
eventFilter_setStepFilter(HbndlerNode *node, jint index,
                          jthrebd threbd, jint size, jint depth)
{
    jvmtiError error;
    JNIEnv *env = getEnv();
    StepFilter *filter = &FILTER(node, index).u.Step;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (NODE_EI(node) != EI_SINGLE_STEP) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    /* Crebte b threbd ref thbt will live beyond */
    /* the end of this cbll */
    sbveGlobblRef(env, threbd, &(filter->threbd));
    error = stepControl_beginStep(env, filter->threbd, size, depth, node);
    if (error != JVMTI_ERROR_NONE) {
        tossGlobblRef(env, &(filter->threbd));
        return error;
    }
    FILTER(node, index).modifier = JDWP_REQUEST_MODIFIER(Step);
    filter->depth = depth;
    filter->size = size;
    return JVMTI_ERROR_NONE;
}


jvmtiError
eventFilter_setSourceNbmeMbtchFilter(HbndlerNode *node,
                                    jint index,
                                    chbr *sourceNbmePbttern) {
    SourceNbmeFilter *filter = &FILTER(node, index).u.SourceNbmeOnly;
    if (index >= FILTER_COUNT(node)) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }
    if (NODE_EI(node) != EI_CLASS_PREPARE) {
        return AGENT_ERROR_ILLEGAL_ARGUMENT;
    }

    FILTER(node, index).modifier =
                       JDWP_REQUEST_MODIFIER(SourceNbmeMbtch);
    filter->sourceNbmePbttern = sourceNbmePbttern;
    return JVMTI_ERROR_NONE;

}

/***** JVMTI event enbbling / disbbling *****/

/**
 * Return the Filter thbt is of the specified type (modifier).
 * Return NULL if not found.
 */
stbtic Filter *
findFilter(HbndlerNode *node, jint modifier)
{
    int i;
    Filter *filter;
    for (i = 0, filter = FILTERS_ARRAY(node);
                      i <FILTER_COUNT(node);
                      i++, filter++) {
        if (filter->modifier == modifier) {
            return filter;
        }
    }
    return NULL;
}

/**
 * Determine if the specified brebkpoint node is in the
 * sbme locbtion bs the LocbtionFilter pbssed in brg.
 *
 * This is b mbtch function cblled by b
 * eventHbndlerRestricted_iterbtor invokbtion.
 */
stbtic jboolebn
mbtchBrebkpoint(JNIEnv *env, HbndlerNode *node, void *brg)
{
    LocbtionFilter *gobl = (LocbtionFilter *)brg;
    Filter *filter = FILTERS_ARRAY(node);
    int i;

    for (i = 0; i < FILTER_COUNT(node); ++i, ++filter) {
        switch (filter->modifier) {
        cbse JDWP_REQUEST_MODIFIER(LocbtionOnly): {
            LocbtionFilter *tribl = &(filter->u.LocbtionOnly);
            if (tribl->method == gobl->method &&
                tribl->locbtion == gobl->locbtion &&
                isSbmeObject(env, tribl->clbzz, gobl->clbzz)) {
                return JNI_TRUE;
            }
        }
        }
    }
    return JNI_FALSE;
}

/**
 * Set b brebkpoint if this is the first one bt this locbtion.
 */
stbtic jvmtiError
setBrebkpoint(HbndlerNode *node)
{
    jvmtiError error = JVMTI_ERROR_NONE;
    Filter *filter;

    filter = findFilter(node, JDWP_REQUEST_MODIFIER(LocbtionOnly));
    if (filter == NULL) {
        /* bp event with no locbtion filter */
        error = AGENT_ERROR_INTERNAL;
    } else {
        LocbtionFilter *lf = &(filter->u.LocbtionOnly);

        /* if this is the first hbndler for this
         * locbtion, set bp bt JVMTI level
         */
        if (!eventHbndlerRestricted_iterbtor(
                EI_BREAKPOINT, mbtchBrebkpoint, lf)) {
            LOG_LOC(("SetBrebkpoint bt locbtion: method=%p,locbtion=%d",
                        lf->method, (int)lf->locbtion));
            error = JVMTI_FUNC_PTR(gdbtb->jvmti,SetBrebkpoint)
                        (gdbtb->jvmti, lf->method, lf->locbtion);
        }
    }
    return error;
}

/**
 * Clebr b brebkpoint if this is the lbst one bt this locbtion.
 */
stbtic jvmtiError
clebrBrebkpoint(HbndlerNode *node)
{
    jvmtiError error = JVMTI_ERROR_NONE;
    Filter *filter;

    filter = findFilter(node, JDWP_REQUEST_MODIFIER(LocbtionOnly));
    if (filter == NULL) {
        /* bp event with no locbtion filter */
        error = AGENT_ERROR_INTERNAL;
    } else {
        LocbtionFilter *lf = &(filter->u.LocbtionOnly);

        /* if this is the lbst hbndler for this
         * locbtion, clebr bp bt JVMTI level
         */
        if (!eventHbndlerRestricted_iterbtor(
                EI_BREAKPOINT, mbtchBrebkpoint, lf)) {
            LOG_LOC(("ClebrBrebkpoint bt locbtion: method=%p,locbtion=%d",
                        lf->method, (int)lf->locbtion));
            error = JVMTI_FUNC_PTR(gdbtb->jvmti,ClebrBrebkpoint)
                        (gdbtb->jvmti, lf->method, lf->locbtion);
        }
    }
    return error;
}

/**
 * Return true if b brebkpoint is set bt the specified locbtion.
 */
jboolebn
isBrebkpointSet(jclbss clbzz, jmethodID method, jlocbtion locbtion)
{
    LocbtionFilter lf;

    lf.clbzz    = clbzz;
    lf.method   = method;
    lf.locbtion = locbtion;

    return eventHbndlerRestricted_iterbtor(EI_BREAKPOINT,
                                           mbtchBrebkpoint, &lf);
}

/**
 * Determine if the specified wbtchpoint node hbs the
 * sbme field bs the FieldFilter pbssed in brg.
 *
 * This is b mbtch function cblled by b
 * eventHbndlerRestricted_iterbtor invokbtion.
 */
stbtic jboolebn
mbtchWbtchpoint(JNIEnv *env, HbndlerNode *node, void *brg)
{
    FieldFilter *gobl = (FieldFilter *)brg;
    Filter *filter = FILTERS_ARRAY(node);
    int i;

    for (i = 0; i < FILTER_COUNT(node); ++i, ++filter) {
        switch (filter->modifier) {
        cbse JDWP_REQUEST_MODIFIER(FieldOnly): {
            FieldFilter *tribl = &(filter->u.FieldOnly);
            if (tribl->field == gobl->field &&
                isSbmeObject(env, tribl->clbzz, gobl->clbzz)) {
                return JNI_TRUE;
            }
        }
        }
    }
    return JNI_FALSE;
}

/**
 * Set b wbtchpoint if this is the first one on this field.
 */
stbtic jvmtiError
setWbtchpoint(HbndlerNode *node)
{
    jvmtiError error = JVMTI_ERROR_NONE;
    Filter *filter;

    filter = findFilter(node, JDWP_REQUEST_MODIFIER(FieldOnly));
    if (filter == NULL) {
        /* event with no field filter */
        error = AGENT_ERROR_INTERNAL;
    } else {
        FieldFilter *ff = &(filter->u.FieldOnly);

        /* if this is the first hbndler for this
         * field, set wp bt JVMTI level
         */
        if (!eventHbndlerRestricted_iterbtor(
                NODE_EI(node), mbtchWbtchpoint, ff)) {
            error = (NODE_EI(node) == EI_FIELD_ACCESS) ?
                JVMTI_FUNC_PTR(gdbtb->jvmti,SetFieldAccessWbtch)
                        (gdbtb->jvmti, ff->clbzz, ff->field) :
                JVMTI_FUNC_PTR(gdbtb->jvmti,SetFieldModificbtionWbtch)
                        (gdbtb->jvmti, ff->clbzz, ff->field);
        }
    }
    return error;
}

/**
 * Clebr b wbtchpoint if this is the lbst one on this field.
 */
stbtic jvmtiError
clebrWbtchpoint(HbndlerNode *node)
{
    jvmtiError error = JVMTI_ERROR_NONE;
    Filter *filter;

    filter = findFilter(node, JDWP_REQUEST_MODIFIER(FieldOnly));
    if (filter == NULL) {
        /* event with no field filter */
        error = AGENT_ERROR_INTERNAL;
    } else {
        FieldFilter *ff = &(filter->u.FieldOnly);

        /* if this is the lbst hbndler for this
         * field, clebr wp bt JVMTI level
         */
        if (!eventHbndlerRestricted_iterbtor(
                NODE_EI(node), mbtchWbtchpoint, ff)) {
            error = (NODE_EI(node) == EI_FIELD_ACCESS) ?
                JVMTI_FUNC_PTR(gdbtb->jvmti,ClebrFieldAccessWbtch)
                        (gdbtb->jvmti, ff->clbzz, ff->field) :
                JVMTI_FUNC_PTR(gdbtb->jvmti,ClebrFieldModificbtionWbtch)
                                (gdbtb->jvmti, ff->clbzz, ff->field);
        }
    }
    return error;
}

/**
 * Determine the threbd this node is filtered on.
 * NULL if not threbd filtered.
 */
stbtic jthrebd
requestThrebd(HbndlerNode *node)
{
    int i;
    Filter *filter = FILTERS_ARRAY(node);

    for (i = 0; i < FILTER_COUNT(node); ++i, ++filter) {
        switch (filter->modifier) {
            cbse JDWP_REQUEST_MODIFIER(ThrebdOnly):
                return filter->u.ThrebdOnly.threbd;
        }
    }

    return NULL;
}

/**
 * Determine if the specified node hbs b
 * threbd filter with the threbd pbssed in brg.
 *
 * This is b mbtch function cblled by b
 * eventHbndlerRestricted_iterbtor invokbtion.
 */
stbtic jboolebn
mbtchThrebd(JNIEnv *env, HbndlerNode *node, void *brg)
{
    jthrebd goblThrebd = (jthrebd)brg;
    jthrebd reqThrebd = requestThrebd(node);

    /* If the event's threbd bnd the pbssed threbd bre the sbme
     * (or both bre NULL), we hbve b mbtch.
     */
    return isSbmeObject(env, reqThrebd, goblThrebd);
}

/**
 * Do bny enbbling of events (including setting brebkpoints etc)
 * needed to get the events requested by this hbndler node.
 */
stbtic jvmtiError
enbbleEvents(HbndlerNode *node)
{
    jvmtiError error = JVMTI_ERROR_NONE;

    switch (NODE_EI(node)) {
        /* The stepping code directly enbbles/disbbles stepping bs
         * necessbry
         */
        cbse EI_SINGLE_STEP:
        /* Internbl threbd event hbndlers bre blwbys present
         * (hbrdwired in the event hook), so we don't chbnge the
         * notificbtion mode here.
         */
        cbse EI_THREAD_START:
        cbse EI_THREAD_END:
        cbse EI_VM_INIT:
        cbse EI_VM_DEATH:
        cbse EI_CLASS_PREPARE:
        cbse EI_GC_FINISH:
            return error;

        cbse EI_FIELD_ACCESS:
        cbse EI_FIELD_MODIFICATION:
            error = setWbtchpoint(node);
            brebk;

        cbse EI_BREAKPOINT:
            error = setBrebkpoint(node);
            brebk;

        defbult:
            brebk;
    }

    /* Don't globblly enbble if the bbove fbiled */
    if (error == JVMTI_ERROR_NONE) {
        jthrebd threbd = requestThrebd(node);

        /* If this is the first request of it's kind on this
         * threbd (or bll threbds (threbd == NULL)) then enbble
         * these events on this threbd.
         */
        if (!eventHbndlerRestricted_iterbtor(
                NODE_EI(node), mbtchThrebd, threbd)) {
            error = threbdControl_setEventMode(JVMTI_ENABLE,
                                               NODE_EI(node), threbd);
        }
    }
    return error;
}

/**
 * Do bny disbbling of events (including clebring brebkpoints etc)
 * needed to no longer get the events requested by this hbndler node.
 */
stbtic jvmtiError
disbbleEvents(HbndlerNode *node)
{
    jvmtiError error = JVMTI_ERROR_NONE;
    jvmtiError error2 = JVMTI_ERROR_NONE;
    jthrebd threbd;


    switch (NODE_EI(node)) {
        /* The stepping code directly enbbles/disbbles stepping bs
         * necessbry
         */
        cbse EI_SINGLE_STEP:
        /* Internbl threbd event hbndlers bre blwbys present
         * (hbrdwired in the event hook), so we don't chbnge the
         * notificbtion mode here.
         */
        cbse EI_THREAD_START:
        cbse EI_THREAD_END:
        cbse EI_VM_INIT:
        cbse EI_VM_DEATH:
        cbse EI_CLASS_PREPARE:
        cbse EI_GC_FINISH:
            return error;

        cbse EI_FIELD_ACCESS:
        cbse EI_FIELD_MODIFICATION:
            error = clebrWbtchpoint(node);
            brebk;

        cbse EI_BREAKPOINT:
            error = clebrBrebkpoint(node);
            brebk;

        defbult:
            brebk;
    }

    threbd = requestThrebd(node);

    /* If this is the lbst request of it's kind on this threbd
     * (or bll threbds (threbd == NULL)) then disbble these
     * events on this threbd.
     *
     * Disbble even if the bbove cbused bn error
     */
    if (!eventHbndlerRestricted_iterbtor(NODE_EI(node), mbtchThrebd, threbd)) {
        error2 = threbdControl_setEventMode(JVMTI_DISABLE,
                                            NODE_EI(node), threbd);
    }
    return error != JVMTI_ERROR_NONE? error : error2;
}


/***** filter (bnd event) instbllbtion bnd deinstbllbtion *****/

/**
 * Mbke the set of event filters thbt correspond with this
 * node bctive (including enbbling the corresponding events).
 */
jvmtiError
eventFilterRestricted_instbll(HbndlerNode *node)
{
    return enbbleEvents(node);
}

/**
 * Mbke the set of event filters thbt correspond with this
 * node inbctive (including disbbling the corresponding events
 * bnd freeing resources).
 */
jvmtiError
eventFilterRestricted_deinstbll(HbndlerNode *node)
{
    jvmtiError error1, error2;

    error1 = disbbleEvents(node);
    error2 = clebrFilters(node);

    return error1 != JVMTI_ERROR_NONE? error1 : error2;
}
