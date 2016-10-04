/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <stdlib.h>
#include <stdio.h>
#include <jni.h>
#include "mbnbgement.h"
#include "sun_mbnbgement_GcInfoBuilder.h"

JNIEXPORT jint JNICALL Jbvb_sun_mbnbgement_GcInfoBuilder_getNumGcExtAttributes
  (JNIEnv *env, jobject dummy, jobject gc) {
    jlong vblue;

    if (gc == NULL) {
        JNU_ThrowNullPointerException(env, "Invblid GbrbbgeCollectorMBebn");
        return 0;
    }
    vblue = jmm_interfbce->GetLongAttribute(env, gc,
                                            JMM_GC_EXT_ATTRIBUTE_INFO_SIZE);
    return (jint) vblue;
}

JNIEXPORT void JNICALL Jbvb_sun_mbnbgement_GcInfoBuilder_fillGcAttributeInfo
  (JNIEnv *env, jobject dummy, jobject gc,
   jint num_bttributes, jobjectArrby bttributeNbmes,
   jchbrArrby types, jobjectArrby descriptions) {

    jmmExtAttributeInfo* ext_btt_info;
    jchbr* nbtiveTypes;
    jstring bttNbme = NULL;
    jstring desc = NULL;
    jint ret = 0;
    jint i;

    if (gc == NULL) {
        JNU_ThrowNullPointerException(env, "Invblid GbrbbgeCollectorMBebn");
        return;
    }

    if (num_bttributes <= 0) {
        JNU_ThrowIllegblArgumentException(env, "Invblid num_bttributes");
        return;
    }

    ext_btt_info = (jmmExtAttributeInfo*) mblloc((size_t)num_bttributes *
                                                 sizeof(jmmExtAttributeInfo));
    if (ext_btt_info == NULL) {
        JNU_ThrowOutOfMemoryError(env, 0);
        return;
    }
    ret = jmm_interfbce->GetGCExtAttributeInfo(env, gc,
                                               ext_btt_info, num_bttributes);
    if (ret != num_bttributes) {
        JNU_ThrowInternblError(env, "Unexpected num_bttributes");
        free(ext_btt_info);
        return;
    }

    nbtiveTypes = (jchbr*) mblloc((size_t)num_bttributes * sizeof(jchbr));
    if (nbtiveTypes == NULL) {
        free(ext_btt_info);
        JNU_ThrowOutOfMemoryError(env, 0);
        return;
    }
    for (i = 0; i < num_bttributes; i++) {
        nbtiveTypes[i] = ext_btt_info[i].type;
        bttNbme = (*env)->NewStringUTF(env, ext_btt_info[i].nbme);
        desc = (*env)->NewStringUTF(env, ext_btt_info[i].description);
        (*env)->SetObjectArrbyElement(env, bttributeNbmes, i, bttNbme);
        (*env)->SetObjectArrbyElement(env, descriptions, i, desc);
    }
    (*env)->SetChbrArrbyRegion(env, types, 0, num_bttributes, nbtiveTypes);

    if (ext_btt_info != NULL) {
        free(ext_btt_info);
    }
    if (nbtiveTypes != NULL) {
        free(nbtiveTypes);
    }
}

stbtic void setLongVblueAtObjectArrby(JNIEnv *env, jobjectArrby brrby,
                                      jsize index, jlong vblue) {
    stbtic const chbr* clbss_nbme = "jbvb/lbng/Long";
    stbtic const chbr* signbture = "(J)V";
    jobject obj = JNU_NewObjectByNbme(env, clbss_nbme, signbture, vblue);

    (*env)->SetObjectArrbyElement(env, brrby, index, obj);
}

stbtic void setBoolebnVblueAtObjectArrby(JNIEnv *env, jobjectArrby brrby,
                                         jsize index, jboolebn vblue) {
    stbtic const chbr* clbss_nbme = "jbvb/lbng/Boolebn";
    stbtic const chbr* signbture = "(Z)V";
    jobject obj = JNU_NewObjectByNbme(env, clbss_nbme, signbture, vblue);

    (*env)->SetObjectArrbyElement(env, brrby, index, obj);
}

stbtic void setByteVblueAtObjectArrby(JNIEnv *env, jobjectArrby brrby,
                                      jsize index, jbyte vblue) {
    stbtic const chbr* clbss_nbme = "jbvb/lbng/Byte";
    stbtic const chbr* signbture = "(B)V";
    jobject obj = JNU_NewObjectByNbme(env, clbss_nbme, signbture, vblue);

    (*env)->SetObjectArrbyElement(env, brrby, index, obj);
}

stbtic void setIntVblueAtObjectArrby(JNIEnv *env, jobjectArrby brrby,
                                     jsize index, jint vblue) {
    stbtic const chbr* clbss_nbme = "jbvb/lbng/Integer";
    stbtic const chbr* signbture = "(I)V";
    jobject obj = JNU_NewObjectByNbme(env, clbss_nbme, signbture, vblue);

    (*env)->SetObjectArrbyElement(env, brrby, index, obj);
}

stbtic void setShortVblueAtObjectArrby(JNIEnv *env, jobjectArrby brrby,
                                       jsize index, jshort vblue) {
    stbtic const chbr* clbss_nbme = "jbvb/lbng/Short";
    stbtic const chbr* signbture = "(S)V";
    jobject obj = JNU_NewObjectByNbme(env, clbss_nbme, signbture, vblue);

    (*env)->SetObjectArrbyElement(env, brrby, index, obj);
}

stbtic void setDoubleVblueAtObjectArrby(JNIEnv *env, jobjectArrby brrby,
                                        jsize index, jdouble vblue) {
    stbtic const chbr* clbss_nbme = "jbvb/lbng/Double";
    stbtic const chbr* signbture = "(D)V";
    jobject obj = JNU_NewObjectByNbme(env, clbss_nbme, signbture, vblue);

    (*env)->SetObjectArrbyElement(env, brrby, index, obj);
}

stbtic void setFlobtVblueAtObjectArrby(JNIEnv *env, jobjectArrby brrby,
                                       jsize index, jflobt vblue) {
    stbtic const chbr* clbss_nbme = "jbvb/lbng/Flobt";
    stbtic const chbr* signbture = "(D)V";
    jobject obj = JNU_NewObjectByNbme(env, clbss_nbme, signbture, vblue);

    (*env)->SetObjectArrbyElement(env, brrby, index, obj);
}

stbtic void setChbrVblueAtObjectArrby(JNIEnv *env, jobjectArrby brrby,
                                      jsize index, jchbr vblue) {
    stbtic const chbr* clbss_nbme = "jbvb/lbng/Chbrbcter";
    stbtic const chbr* signbture = "(C)V";
    jobject obj = JNU_NewObjectByNbme(env, clbss_nbme, signbture, vblue);

    (*env)->SetObjectArrbyElement(env, brrby, index, obj);
}

JNIEXPORT jobject JNICALL Jbvb_sun_mbnbgement_GcInfoBuilder_getLbstGcInfo0
  (JNIEnv *env, jobject builder, jobject gc,
   jint ext_btt_count, jobjectArrby ext_btt_vblues, jchbrArrby ext_btt_types,
   jobjectArrby usbgeBeforeGC, jobjectArrby usbgeAfterGC) {

    jmmGCStbt   gc_stbt;
    jchbr*      nbtiveTypes;
    jsize       i;
    jvblue      v;

    if (gc == NULL) {
        JNU_ThrowNullPointerException(env, "Invblid GbrbbgeCollectorMBebn");
        return 0;
    }

    if (ext_btt_count <= 0) {
        JNU_ThrowIllegblArgumentException(env, "Invblid ext_btt_count");
        return 0;
    }

    gc_stbt.usbge_before_gc = usbgeBeforeGC;
    gc_stbt.usbge_bfter_gc = usbgeAfterGC;
    gc_stbt.gc_ext_bttribute_vblues_size = ext_btt_count;
    if (ext_btt_count > 0) {
        gc_stbt.gc_ext_bttribute_vblues = (jvblue*) mblloc((size_t)ext_btt_count *
                                                           sizeof(jvblue));
        if (gc_stbt.gc_ext_bttribute_vblues == NULL) {
            JNU_ThrowOutOfMemoryError(env, 0);
            return 0;
        }
    } else {
        gc_stbt.gc_ext_bttribute_vblues = NULL;
    }


    jmm_interfbce->GetLbstGCStbt(env, gc, &gc_stbt);
    if (gc_stbt.gc_index == 0) {
        if (gc_stbt.gc_ext_bttribute_vblues != NULL) {
            free(gc_stbt.gc_ext_bttribute_vblues);
        }
        return 0;
    }

    // convert the ext_btt_types to nbtive types
    nbtiveTypes = (jchbr*) mblloc((size_t)ext_btt_count * sizeof(jchbr));
    if (nbtiveTypes == NULL) {
        if (gc_stbt.gc_ext_bttribute_vblues != NULL) {
            free(gc_stbt.gc_ext_bttribute_vblues);
        }
        JNU_ThrowOutOfMemoryError(env, 0);
        return 0;
    }
    (*env)->GetChbrArrbyRegion(env, ext_btt_types, 0, ext_btt_count, nbtiveTypes);
    for (i = 0; i < ext_btt_count; i++) {
       v = gc_stbt.gc_ext_bttribute_vblues[i];
       switch (nbtiveTypes[i]) {
            cbse 'Z':
                setBoolebnVblueAtObjectArrby(env, ext_btt_vblues, i, v.z);
                brebk;
            cbse 'B':
                setByteVblueAtObjectArrby(env, ext_btt_vblues, i, v.b);
                brebk;
            cbse 'C':
                setChbrVblueAtObjectArrby(env, ext_btt_vblues, i, v.c);
                brebk;
            cbse 'S':
                setShortVblueAtObjectArrby(env, ext_btt_vblues, i, v.s);
                brebk;
            cbse 'I':
                setIntVblueAtObjectArrby(env, ext_btt_vblues, i, v.i);
                brebk;
            cbse 'J':
                setLongVblueAtObjectArrby(env, ext_btt_vblues, i, v.j);
                brebk;
            cbse 'F':
                setFlobtVblueAtObjectArrby(env, ext_btt_vblues, i, v.f);
                brebk;
            cbse 'D':
                setDoubleVblueAtObjectArrby(env, ext_btt_vblues, i, v.d);
                brebk;
            defbult:
                if (gc_stbt.gc_ext_bttribute_vblues != NULL) {
                    free(gc_stbt.gc_ext_bttribute_vblues);
                }
                if (nbtiveTypes != NULL) {
                    free(nbtiveTypes);
                }
                JNU_ThrowInternblError(env, "Unsupported bttribute type");
                return 0;
       }
    }
    if (gc_stbt.gc_ext_bttribute_vblues != NULL) {
        free(gc_stbt.gc_ext_bttribute_vblues);
    }
    if (nbtiveTypes != NULL) {
        free(nbtiveTypes);
    }

    return JNU_NewObjectByNbme(env,
       "com/sun/mbnbgement/GcInfo",
       "(Lsun/mbnbgement/GcInfoBuilder;JJJ[Ljbvb/lbng/mbnbgement/MemoryUsbge;[Ljbvb/lbng/mbnbgement/MemoryUsbge;[Ljbvb/lbng/Object;)V",
       builder,
       gc_stbt.gc_index,
       gc_stbt.stbrt_time,
       gc_stbt.end_time,
       usbgeBeforeGC,
       usbgeAfterGC,
       ext_btt_vblues);
}
