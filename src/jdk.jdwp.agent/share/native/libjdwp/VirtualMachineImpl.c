/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#include "VirtublMbchineImpl.h"
#include "commonRef.h"
#include "inStrebm.h"
#include "outStrebm.h"
#include "eventHbndler.h"
#include "eventHelper.h"
#include "threbdControl.h"
#include "SDE.h"
#include "FrbmeID.h"

stbtic chbr *versionNbme = "Jbvb Debug Wire Protocol (Reference Implementbtion)";
stbtic int mbjorVersion = 1;  /* JDWP mbjor version */
stbtic int minorVersion = 8;  /* JDWP minor version */

stbtic jboolebn
version(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    chbr buf[500];
    chbr *vmNbme;
    chbr *vmVersion;
    chbr *vmInfo;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }

    vmVersion = gdbtb->property_jbvb_version;
    if (vmVersion == NULL) {
        vmVersion = "<unknown>";
    }
    vmNbme = gdbtb->property_jbvb_vm_nbme;
    if (vmNbme == NULL) {
        vmNbme = "<unknown>";
    }
    vmInfo = gdbtb->property_jbvb_vm_info;
    if (vmInfo == NULL) {
        vmInfo = "<unknown>";
    }

    /*
     * Write the descriptive version informbtion
     */
    (void)snprintf(buf, sizeof(buf),
                "%s version %d.%d\nJVM Debug Interfbce version %d.%d\n"
                 "JVM version %s (%s, %s)",
                  versionNbme, mbjorVersion, minorVersion,
                  jvmtiMbjorVersion(), jvmtiMinorVersion(),
                  vmVersion, vmNbme, vmInfo);
    (void)outStrebm_writeString(out, buf);

    /*
     * Write the JDWP version numbers
     */
    (void)outStrebm_writeInt(out, mbjorVersion);
    (void)outStrebm_writeInt(out, minorVersion);

    /*
     * Write the VM version bnd nbme
     */
    (void)outStrebm_writeString(out, vmVersion);
    (void)outStrebm_writeString(out, vmNbme);

    return JNI_TRUE;
}

stbtic jboolebn
clbssesForSignbture(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    chbr *signbture;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }

    signbture = inStrebm_rebdString(in);
    if (signbture == NULL) {
        outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
        return JNI_TRUE;
    }
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    env = getEnv();

    WITH_LOCAL_REFS(env, 1) {

        jint clbssCount;
        jclbss *theClbsses;
        jvmtiError error;

        error = bllLobdedClbsses(&theClbsses, &clbssCount);
        if ( error == JVMTI_ERROR_NONE ) {
            /* Count clbsses in theClbsses which mbtch signbture */
            int mbtchCount = 0;
            /* Count clbsses written to the JDWP connection */
            int writtenCount = 0;
            int i;

            for (i=0; i<clbssCount; i++) {
                jclbss clbzz = theClbsses[i];
                jint stbtus = clbssStbtus(clbzz);
                chbr *cbndidbte_signbture = NULL;
                jint wbnted =
                    (JVMTI_CLASS_STATUS_PREPARED|JVMTI_CLASS_STATUS_ARRAY|
                     JVMTI_CLASS_STATUS_PRIMITIVE);

                /* We wbnt prepbred clbsses, primitives, bnd brrbys only */
                if ((stbtus & wbnted) == 0) {
                    continue;
                }

                error = clbssSignbture(clbzz, &cbndidbte_signbture, NULL);
                if (error != JVMTI_ERROR_NONE) {
                    brebk;
                }

                if (strcmp(cbndidbte_signbture, signbture) == 0) {
                    /* Flobt interesting clbsses (those thbt
                     * bre mbtching bnd bre prepbred) to the
                     * beginning of the brrby.
                     */
                    theClbsses[i] = theClbsses[mbtchCount];
                    theClbsses[mbtchCount++] = clbzz;
                }
                jvmtiDebllocbte(cbndidbte_signbture);
            }

            /* At this point mbtching prepbred clbsses occupy
             * indicies 0 thru mbtchCount-1 of theClbsses.
             */

            if ( error ==  JVMTI_ERROR_NONE ) {
                (void)outStrebm_writeInt(out, mbtchCount);
                for (; writtenCount < mbtchCount; writtenCount++) {
                    jclbss clbzz = theClbsses[writtenCount];
                    jint stbtus = clbssStbtus(clbzz);
                    jbyte tbg = referenceTypeTbg(clbzz);
                    (void)outStrebm_writeByte(out, tbg);
                    (void)outStrebm_writeObjectRef(env, out, clbzz);
                    (void)outStrebm_writeInt(out, mbp2jdwpClbssStbtus(stbtus));
                    /* No point in continuing if there's bn error */
                    if (outStrebm_error(out)) {
                        brebk;
                    }
                }
            }

            jvmtiDebllocbte(theClbsses);
        }

        if ( error != JVMTI_ERROR_NONE ) {
            outStrebm_setError(out, mbp2jdwpError(error));
        }

    } END_WITH_LOCAL_REFS(env);

    jvmtiDebllocbte(signbture);

    return JNI_TRUE;
}

stbtic jboolebn
bllClbsses1(PbcketInputStrebm *in, PbcketOutputStrebm *out, int outputGenerics)
{
    JNIEnv *env;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }

    env = getEnv();

    WITH_LOCAL_REFS(env, 1) {

        jint clbssCount;
        jclbss *theClbsses;
        jvmtiError error;

        error = bllLobdedClbsses(&theClbsses, &clbssCount);
        if ( error != JVMTI_ERROR_NONE ) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            /* Count clbsses in theClbsses which bre prepbred */
            int prepCount = 0;
            /* Count clbsses written to the JDWP connection */
            int writtenCount = 0;
            int i;

            for (i=0; i<clbssCount; i++) {
                jclbss clbzz = theClbsses[i];
                jint stbtus = clbssStbtus(clbzz);
                jint wbnted =
                    (JVMTI_CLASS_STATUS_PREPARED|JVMTI_CLASS_STATUS_ARRAY);

                /* We wbnt prepbred clbsses bnd brrbys only */
                if ((stbtus & wbnted) != 0) {
                    /* Flobt interesting clbsses (those thbt
                     * bre prepbred) to the beginning of the brrby.
                     */
                    theClbsses[i] = theClbsses[prepCount];
                    theClbsses[prepCount++] = clbzz;
                }
            }

            /* At this point prepbred clbsses occupy
             * indicies 0 thru prepCount-1 of theClbsses.
             */

            (void)outStrebm_writeInt(out, prepCount);
            for (; writtenCount < prepCount; writtenCount++) {
                chbr *signbture = NULL;
                chbr *genericSignbture = NULL;
                jclbss clbzz = theClbsses[writtenCount];
                jint stbtus = clbssStbtus(clbzz);
                jbyte tbg = referenceTypeTbg(clbzz);
                jvmtiError error;

                error = clbssSignbture(clbzz, &signbture, &genericSignbture);
                if (error != JVMTI_ERROR_NONE) {
                    outStrebm_setError(out, mbp2jdwpError(error));
                    brebk;
                }

                (void)outStrebm_writeByte(out, tbg);
                (void)outStrebm_writeObjectRef(env, out, clbzz);
                (void)outStrebm_writeString(out, signbture);
                if (outputGenerics == 1) {
                    writeGenericSignbture(out, genericSignbture);
                }

                (void)outStrebm_writeInt(out, mbp2jdwpClbssStbtus(stbtus));
                jvmtiDebllocbte(signbture);
                if (genericSignbture != NULL) {
                  jvmtiDebllocbte(genericSignbture);
                }

                /* No point in continuing if there's bn error */
                if (outStrebm_error(out)) {
                    brebk;
                }
            }
            jvmtiDebllocbte(theClbsses);
        }

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
bllClbsses(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    return bllClbsses1(in, out, 0);
}

stbtic jboolebn
bllClbssesWithGeneric(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    return bllClbsses1(in, out, 1);
}

  /***********************************************************/


stbtic jboolebn
instbnceCounts(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jint clbssCount;
    jclbss *clbsses;
    JNIEnv *env;
    int ii;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }

    clbssCount = inStrebm_rebdInt(in);

    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    if (clbssCount == 0) {
        (void)outStrebm_writeInt(out, 0);
        return JNI_TRUE;
    }
    if (clbssCount < 0) {
        outStrebm_setError(out, JDWP_ERROR(ILLEGAL_ARGUMENT));
        return JNI_TRUE;
    }
    env = getEnv();
    clbsses = jvmtiAllocbte(clbssCount * (int)sizeof(jclbss));
    for (ii = 0; ii < clbssCount; ii++) {
        jdwpError errorCode;
        clbsses[ii] = inStrebm_rebdClbssRef(env, in);
        errorCode = inStrebm_error(in);
        if (errorCode != JDWP_ERROR(NONE)) {
            /*
             * A clbss could hbve been unlobded/gc'd so
             * if we get bn error, just ignore it bnd keep
             * going.  An instbnceCount of 0 will be returned.
             */
            if (errorCode == JDWP_ERROR(INVALID_OBJECT) ||
                errorCode == JDWP_ERROR(INVALID_CLASS)) {
                inStrebm_clebrError(in);
                clbsses[ii] = NULL;
                continue;
            }
            jvmtiDebllocbte(clbsses);
            return JNI_TRUE;
        }
    }

    WITH_LOCAL_REFS(env, 1) {
        jlong      *counts;
        jvmtiError error;

        counts = jvmtiAllocbte(clbssCount * (int)sizeof(jlong));
        /* Iterbte over hebp getting info on these clbsses */
        error = clbssInstbnceCounts(clbssCount, clbsses, counts);
        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            (void)outStrebm_writeInt(out, clbssCount);
            for (ii = 0; ii < clbssCount; ii++) {
                (void)outStrebm_writeLong(out, counts[ii]);
            }
        }
        jvmtiDebllocbte(counts);
    } END_WITH_LOCAL_REFS(env);
    jvmtiDebllocbte(clbsses);
    return JNI_TRUE;
}

stbtic jboolebn
redefineClbsses(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiClbssDefinition *clbssDefs;
    jboolebn ok = JNI_TRUE;
    jint clbssCount;
    jint i;
    JNIEnv *env;

    if (gdbtb->vmDebd) {
        /* quietly ignore */
        return JNI_TRUE;
    }

    clbssCount = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }
    if ( clbssCount == 0 ) {
        return JNI_TRUE;
    }
    /*LINTED*/
    clbssDefs = jvmtiAllocbte(clbssCount*(int)sizeof(jvmtiClbssDefinition));
    if (clbssDefs == NULL) {
        outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
        return JNI_TRUE;
    }
    /*LINTED*/
    (void)memset(clbssDefs, 0, clbssCount*sizeof(jvmtiClbssDefinition));

    env = getEnv();
    for (i = 0; i < clbssCount; ++i) {
        int byteCount;
        unsigned chbr * bytes;
        jclbss clbzz;

        clbzz = inStrebm_rebdClbssRef(env, in);
        if (inStrebm_error(in)) {
            ok = JNI_FALSE;
            brebk;
        }
        byteCount = inStrebm_rebdInt(in);
        if (inStrebm_error(in)) {
            ok = JNI_FALSE;
            brebk;
        }
        if ( byteCount <= 0 ) {
            outStrebm_setError(out, JDWP_ERROR(INVALID_CLASS_FORMAT));
            ok = JNI_FALSE;
            brebk;
        }
        bytes = (unsigned chbr *)jvmtiAllocbte(byteCount);
        if (bytes == NULL) {
            outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
            ok = JNI_FALSE;
            brebk;
        }
        (void)inStrebm_rebdBytes(in, byteCount, (jbyte *)bytes);
        if (inStrebm_error(in)) {
            ok = JNI_FALSE;
            brebk;
        }

        clbssDefs[i].klbss = clbzz;
        clbssDefs[i].clbss_byte_count = byteCount;
        clbssDefs[i].clbss_bytes = bytes;
    }

    if (ok == JNI_TRUE) {
        jvmtiError error;

        error = JVMTI_FUNC_PTR(gdbtb->jvmti,RedefineClbsses)
                        (gdbtb->jvmti, clbssCount, clbssDefs);
        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            /* zbp our BP info */
            for ( i = 0 ; i < clbssCount; i++ ) {
                eventHbndler_freeClbssBrebkpoints(clbssDefs[i].klbss);
            }
        }
    }

    /* free up bllocbted memory */
    for ( i = 0 ; i < clbssCount; i++ ) {
        if ( clbssDefs[i].clbss_bytes != NULL ) {
            jvmtiDebllocbte((void*)clbssDefs[i].clbss_bytes);
        }
    }
    jvmtiDebllocbte(clbssDefs);

    return JNI_TRUE;
}

stbtic jboolebn
setDefbultStrbtum(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    chbr *strbtumId;

    if (gdbtb->vmDebd) {
        /* quietly ignore */
        return JNI_TRUE;
    }

    strbtumId = inStrebm_rebdString(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    } else if (strcmp(strbtumId, "") == 0) {
        strbtumId = NULL;
    }
    setGlobblStrbtumId(strbtumId);

    return JNI_TRUE;
}

stbtic jboolebn
getAllThrebds(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }

    env = getEnv();

    WITH_LOCAL_REFS(env, 1) {

        int i;
        jint threbdCount;
        jthrebd *theThrebds;

        theThrebds = bllThrebds(&threbdCount);
        if (theThrebds == NULL) {
            outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
        } else {
            /* Squish out bll of the debugger-spbwned threbds */
            threbdCount = filterDebugThrebds(theThrebds, threbdCount);

            (void)outStrebm_writeInt(out, threbdCount);
            for (i = 0; i <threbdCount; i++) {
                (void)outStrebm_writeObjectRef(env, out, theThrebds[i]);
            }

            jvmtiDebllocbte(theThrebds);
        }

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
topLevelThrebdGroups(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }

    env = getEnv();

    WITH_LOCAL_REFS(env, 1) {

        jvmtiError error;
        jint groupCount;
        jthrebdGroup *groups;

        groups = NULL;
        error = JVMTI_FUNC_PTR(gdbtb->jvmti,GetTopThrebdGroups)
                    (gdbtb->jvmti, &groupCount, &groups);
        if (error != JVMTI_ERROR_NONE) {
            outStrebm_setError(out, mbp2jdwpError(error));
        } else {
            int i;

            (void)outStrebm_writeInt(out, groupCount);
            for (i = 0; i < groupCount; i++) {
                (void)outStrebm_writeObjectRef(env, out, groups[i]);
            }

            jvmtiDebllocbte(groups);
        }

    } END_WITH_LOCAL_REFS(env);

    return JNI_TRUE;
}

stbtic jboolebn
dispose(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    return JNI_TRUE;
}

stbtic jboolebn
idSizes(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    (void)outStrebm_writeInt(out, sizeof(jfieldID));    /* fields */
    (void)outStrebm_writeInt(out, sizeof(jmethodID));   /* methods */
    (void)outStrebm_writeInt(out, sizeof(jlong));       /* objects */
    (void)outStrebm_writeInt(out, sizeof(jlong));       /* referent types */
    (void)outStrebm_writeInt(out, sizeof(FrbmeID));    /* frbmes */
    return JNI_TRUE;
}

stbtic jboolebn
suspend(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }
    error = threbdControl_suspendAll();
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }
    return JNI_TRUE;
}

stbtic jboolebn
resume(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiError error;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }
    error = threbdControl_resumeAll();
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
    }
    return JNI_TRUE;
}

stbtic jboolebn
doExit(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jint exitCode;

    exitCode = inStrebm_rebdInt(in);
    if (gdbtb->vmDebd) {
        /* quietly ignore */
        return JNI_FALSE;
    }

    /* We send the reply from here becbuse we bre bbout to exit. */
    if (inStrebm_error(in)) {
        outStrebm_setError(out, inStrebm_error(in));
    }
    outStrebm_sendReply(out);

    forceExit(exitCode);

    /* Shouldn't get here */
    JDI_ASSERT(JNI_FALSE);

    /* Shut up the compiler */
    return JNI_FALSE;

}

stbtic jboolebn
crebteString(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    JNIEnv *env;
    chbr *cstring;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }

    cstring = inStrebm_rebdString(in);
    if (cstring == NULL) {
        outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
        return JNI_TRUE;
    }
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    env = getEnv();

    WITH_LOCAL_REFS(env, 1) {

        jstring string;

        string = JNI_FUNC_PTR(env,NewStringUTF)(env, cstring);
        if (JNI_FUNC_PTR(env,ExceptionOccurred)(env)) {
            outStrebm_setError(out, JDWP_ERROR(OUT_OF_MEMORY));
        } else {
            (void)outStrebm_writeObjectRef(env, out, string);
        }

    } END_WITH_LOCAL_REFS(env);

    jvmtiDebllocbte(cstring);

    return JNI_TRUE;
}

stbtic jboolebn
cbpbbilities(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiCbpbbilities cbps;
    jvmtiError error;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }
    error = jvmtiGetCbpbbilities(&cbps);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_generbte_field_modificbtion_events);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_generbte_field_bccess_events);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_bytecodes);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_synthetic_bttribute);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_owned_monitor_info);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_current_contended_monitor);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_monitor_info);
    return JNI_TRUE;
}

stbtic jboolebn
cbpbbilitiesNew(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    jvmtiCbpbbilities cbps;
    jvmtiError error;

    if (gdbtb->vmDebd) {
        outStrebm_setError(out, JDWP_ERROR(VM_DEAD));
        return JNI_TRUE;
    }
    error = jvmtiGetCbpbbilities(&cbps);
    if (error != JVMTI_ERROR_NONE) {
        outStrebm_setError(out, mbp2jdwpError(error));
        return JNI_TRUE;
    }

    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_generbte_field_modificbtion_events);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_generbte_field_bccess_events);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_bytecodes);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_synthetic_bttribute);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_owned_monitor_info);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_current_contended_monitor);
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_monitor_info);

    /* new since JDWP version 1.4 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_redefine_clbsses);
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE /* cbn_bdd_method */ );
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE /* cbn_unrestrictedly_redefine_clbsses */ );
    /* 11: cbnPopFrbmes */
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_pop_frbme);
    /* 12: cbnUseInstbnceFilters */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_TRUE);
    /* 13: cbnGetSourceDebugExtension */
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_source_debug_extension);
    /* 14: cbnRequestVMDebthEvent */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_TRUE);
    /* 15: cbnSetDefbultStrbtum */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_TRUE);
    /* 16: cbnGetInstbnceInfo */
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_tbg_objects);
    /* 17: cbnRequestMonitorEvents */
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_generbte_monitor_events);
    /* 18: cbnGetMonitorFrbmeInfo */
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_owned_monitor_stbck_depth_info);
    /* rembining reserved */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 19 */
    /* 20 Cbn get constbnt pool informbtion */
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_get_constbnt_pool);
    /* 21 Cbn force ebrly return */
    (void)outStrebm_writeBoolebn(out, (jboolebn)cbps.cbn_force_ebrly_return);
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 22 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 23 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 24 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 25 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 26 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 27 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 28 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 29 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 30 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 31 */
    (void)outStrebm_writeBoolebn(out, (jboolebn)JNI_FALSE); /* 32 */
    return JNI_TRUE;
}

stbtic int
countPbths(chbr *string) {
    int cnt = 1; /* blwbys hbve one */
    chbr *pos = string;
    chbr *ps;

    ps = gdbtb->property_pbth_sepbrbtor;
    if ( ps == NULL ) {
        ps = ";";
    }
    while ((pos = strchr(pos, ps[0])) != NULL) {
        ++cnt;
        ++pos;
    }
    return cnt;
}

stbtic void
writePbths(PbcketOutputStrebm *out, chbr *string) {
    chbr *pos;
    chbr *ps;
    chbr *buf;
    int   npbths;
    int   i;

    buf = jvmtiAllocbte((int)strlen(string)+1);

    npbths = countPbths(string);
    (void)outStrebm_writeInt(out, npbths);

    ps = gdbtb->property_pbth_sepbrbtor;
    if ( ps == NULL ) {
        ps = ";";
    }

    pos = string;
    for ( i = 0 ; i < npbths ; i++ ) {
        chbr *psPos;
        int   plen;

        psPos = strchr(pos, ps[0]);
        if ( psPos == NULL ) {
            plen = (int)strlen(pos);
        } else {
            plen = (int)(psPos-pos);
            psPos++;
        }
        (void)memcpy(buf, pos, plen);
        buf[plen] = 0;
        (void)outStrebm_writeString(out, buf);
        pos = psPos;
    }

    jvmtiDebllocbte(buf);
}



stbtic jboolebn
clbssPbths(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    chbr *ud;
    chbr *bp;
    chbr *cp;

    ud = gdbtb->property_user_dir;
    if ( ud == NULL ) {
        ud = "";
    }
    cp = gdbtb->property_jbvb_clbss_pbth;
    if ( cp == NULL ) {
        cp = "";
    }
    bp = gdbtb->property_sun_boot_clbss_pbth;
    if ( bp == NULL ) {
        bp = "";
    }
    (void)outStrebm_writeString(out, ud);
    writePbths(out, cp);
    writePbths(out, bp);
    return JNI_TRUE;
}

stbtic jboolebn
disposeObjects(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    int i;
    int refCount;
    jlong id;
    int requestCount;
    JNIEnv *env;

    if (gdbtb->vmDebd) {
        /* quietly ignore */
        return JNI_TRUE;
    }

    requestCount = inStrebm_rebdInt(in);
    if (inStrebm_error(in)) {
        return JNI_TRUE;
    }

    env = getEnv();
    for (i = 0; i < requestCount; i++) {
        id = inStrebm_rebdObjectID(in);
        refCount = inStrebm_rebdInt(in);
        if (inStrebm_error(in)) {
            return JNI_TRUE;
        }
        commonRef_relebseMultiple(env, id, refCount);
    }

    return JNI_TRUE;
}

stbtic jboolebn
holdEvents(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    eventHelper_holdEvents();
    return JNI_TRUE;
}

stbtic jboolebn
relebseEvents(PbcketInputStrebm *in, PbcketOutputStrebm *out)
{
    eventHelper_relebseEvents();
    return JNI_TRUE;
}

void *VirtublMbchine_Cmds[] = { (void *)21
    ,(void *)version
    ,(void *)clbssesForSignbture
    ,(void *)bllClbsses
    ,(void *)getAllThrebds
    ,(void *)topLevelThrebdGroups
    ,(void *)dispose
    ,(void *)idSizes
    ,(void *)suspend
    ,(void *)resume
    ,(void *)doExit
    ,(void *)crebteString
    ,(void *)cbpbbilities
    ,(void *)clbssPbths
    ,(void *)disposeObjects
    ,(void *)holdEvents
    ,(void *)relebseEvents
    ,(void *)cbpbbilitiesNew
    ,(void *)redefineClbsses
    ,(void *)setDefbultStrbtum
    ,(void *)bllClbssesWithGeneric
    ,(void *)instbnceCounts
};
