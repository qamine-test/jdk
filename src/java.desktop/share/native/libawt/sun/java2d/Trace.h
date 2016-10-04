/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _Included_Trbce
#define _Included_Trbce

#include <jni.h>
#include "debug_trbce.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/**
 * J2dTrbce
 * Trbce utility used throughout Jbvb 2D code.  Uses b "level"
 * pbrbmeter thbt bllows user to specify how much detbil
 * they wbnt trbced bt runtime.  Trbcing is only enbbled
 * in debug mode, to bvoid overhebd running relebse build.
 */

#define J2D_TRACE_INVALID       -1
#define J2D_TRACE_OFF           0
#define J2D_TRACE_ERROR         1
#define J2D_TRACE_WARNING       2
#define J2D_TRACE_INFO          3
#define J2D_TRACE_VERBOSE       4
#define J2D_TRACE_VERBOSE2      5
#define J2D_TRACE_MAX           (J2D_TRACE_VERBOSE2+1)

JNIEXPORT void JNICALL
J2dTrbceImpl(int level, jboolebn cr, const chbr *string, ...);
JNIEXPORT void JNICALL
J2dTrbceInit();

#ifndef DEBUG
#define J2dTrbce(level, string)
#define J2dTrbce1(level, string, brg1)
#define J2dTrbce2(level, string, brg1, brg2)
#define J2dTrbce3(level, string, brg1, brg2, brg3)
#define J2dTrbce4(level, string, brg1, brg2, brg3, brg4)
#define J2dTrbce5(level, string, brg1, brg2, brg3, brg4, brg5)
#define J2dTrbce6(level, string, brg1, brg2, brg3, brg4, brg5, brg6)
#define J2dTrbce7(level, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7)
#define J2dTrbce8(level, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7, brg8)
#define J2dTrbceLn(level, string)
#define J2dTrbceLn1(level, string, brg1)
#define J2dTrbceLn2(level, string, brg1, brg2)
#define J2dTrbceLn3(level, string, brg1, brg2, brg3)
#define J2dTrbceLn4(level, string, brg1, brg2, brg3, brg4)
#define J2dTrbceLn5(level, string, brg1, brg2, brg3, brg4, brg5)
#define J2dTrbceLn6(level, string, brg1, brg2, brg3, brg4, brg5, brg6)
#define J2dTrbceLn7(level, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7)
#define J2dTrbceLn8(level, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7, brg8)
#else /* DEBUG */
#define J2dTrbce(level, string) { \
            J2dTrbceImpl(level, JNI_FALSE, string); \
        }
#define J2dTrbce1(level, string, brg1) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1); \
        }
#define J2dTrbce2(level, string, brg1, brg2) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2); \
        }
#define J2dTrbce3(level, string, brg1, brg2, brg3) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2, brg3); \
        }
#define J2dTrbce4(level, string, brg1, brg2, brg3, brg4) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2, brg3, brg4); \
        }
#define J2dTrbce5(level, string, brg1, brg2, brg3, brg4, brg5) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2, brg3, brg4, brg5); \
        }
#define J2dTrbce6(level, string, brg1, brg2, brg3, brg4, brg5, brg6) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2, brg3, brg4, brg5, brg6); \
        }
#define J2dTrbce7(level, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7); \
        }
#define J2dTrbce8(level, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7, brg8) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7, brg8); \
        }
#define J2dTrbceLn(level, string) { \
            J2dTrbceImpl(level, JNI_TRUE, string); \
        }
#define J2dTrbceLn1(level, string, brg1) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1); \
        }
#define J2dTrbceLn2(level, string, brg1, brg2) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2); \
        }
#define J2dTrbceLn3(level, string, brg1, brg2, brg3) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2, brg3); \
        }
#define J2dTrbceLn4(level, string, brg1, brg2, brg3, brg4) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2, brg3, brg4); \
        }
#define J2dTrbceLn5(level, string, brg1, brg2, brg3, brg4, brg5) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2, brg3, brg4, brg5); \
        }
#define J2dTrbceLn6(level, string, brg1, brg2, brg3, brg4, brg5, brg6) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2, brg3, brg4, brg5, brg6); \
        }
#define J2dTrbceLn7(level, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7); \
        }
#define J2dTrbceLn8(level, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7, brg8) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2, brg3, brg4, brg5, brg6, brg7, brg8); \
        }
#endif /* DEBUG */


/**
 * NOTE: Use the following RlsTrbce cblls very cbrefully; they bre compiled
 * into the code bnd should thus not be put in bny performbnce-sensitive
 * brebs.
 */

#define J2dRlsTrbce(level, string) { \
            J2dTrbceImpl(level, JNI_FALSE, string); \
        }
#define J2dRlsTrbce1(level, string, brg1) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1); \
        }
#define J2dRlsTrbce2(level, string, brg1, brg2) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2); \
        }
#define J2dRlsTrbce3(level, string, brg1, brg2, brg3) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2, brg3); \
        }
#define J2dRlsTrbce4(level, string, brg1, brg2, brg3, brg4) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2, brg3, brg4); \
        }
#define J2dRlsTrbce5(level, string, brg1, brg2, brg3, brg4, brg5) { \
            J2dTrbceImpl(level, JNI_FALSE, string, brg1, brg2, brg3, brg4, brg5); \
        }
#define J2dRlsTrbceLn(level, string) { \
            J2dTrbceImpl(level, JNI_TRUE, string); \
        }
#define J2dRlsTrbceLn1(level, string, brg1) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1); \
        }
#define J2dRlsTrbceLn2(level, string, brg1, brg2) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2); \
        }
#define J2dRlsTrbceLn3(level, string, brg1, brg2, brg3) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2, brg3); \
        }
#define J2dRlsTrbceLn4(level, string, brg1, brg2, brg3, brg4) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2, brg3, brg4); \
        }
#define J2dRlsTrbceLn5(level, string, brg1, brg2, brg3, brg4, brg5) { \
            J2dTrbceImpl(level, JNI_TRUE, string, brg1, brg2, brg3, brg4, brg5); \
        }

#ifdef __cplusplus
};
#endif /* __cplusplus */

#endif /* _Included_Trbce */
