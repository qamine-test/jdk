/*
 * Copyright (c) 1998, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jni.h>
#include "SoundDefs.h"
#include "Configure.h"          // put flbgs for debug msgs etc. here

// return 1 if this plbtform is big endibn, or 0 for little endibn
int UTIL_IsBigEndibnPlbtform();


// ERROR PRINTS
#ifdef USE_ERROR
#define ERROR0(string)                        { fprintf(stdout, (string)); fflush(stdout); }
#define ERROR1(string, p1)                    { fprintf(stdout, (string), (p1)); fflush(stdout); }
#define ERROR2(string, p1, p2)                { fprintf(stdout, (string), (p1), (p2)); fflush(stdout); }
#define ERROR3(string, p1, p2, p3)            { fprintf(stdout, (string), (p1), (p2), (p3)); fflush(stdout); }
#define ERROR4(string, p1, p2, p3, p4)        { fprintf(stdout, (string), (p1), (p2), (p3), (p4)); fflush(stdout); }
#else
#define ERROR0(string)
#define ERROR1(string, p1)
#define ERROR2(string, p1, p2)
#define ERROR3(string, p1, p2, p3)
#define ERROR4(string, p1, p2, p3, p4)
#endif


// TRACE PRINTS
#ifdef USE_TRACE
#define TRACE0(string)                        { fprintf(stdout, (string)); fflush(stdout); }
#define TRACE1(string, p1)                    { fprintf(stdout, (string), (p1)); fflush(stdout); }
#define TRACE2(string, p1, p2)                { fprintf(stdout, (string), (p1), (p2)); fflush(stdout); }
#define TRACE3(string, p1, p2, p3)            { fprintf(stdout, (string), (p1), (p2), (p3)); fflush(stdout); }
#define TRACE4(string, p1, p2, p3, p4)        { fprintf(stdout, (string), (p1), (p2), (p3), (p4)); fflush(stdout); }
#define TRACE5(string, p1, p2, p3, p4, p5)    { fprintf(stdout, (string), (p1), (p2), (p3), (p4), (p5)); fflush(stdout); }
#else
#define TRACE0(string)
#define TRACE1(string, p1)
#define TRACE2(string, p1, p2)
#define TRACE3(string, p1, p2, p3)
#define TRACE4(string, p1, p2, p3, p4)
#define TRACE5(string, p1, p2, p3, p4, p5)
#endif


// VERBOSE TRACE PRINTS
#ifdef USE_VERBOSE_TRACE
#define VTRACE0(string)                 fprintf(stdout, (string));
#define VTRACE1(string, p1)             fprintf(stdout, (string), (p1));
#define VTRACE2(string, p1, p2)         fprintf(stdout, (string), (p1), (p2));
#define VTRACE3(string, p1, p2, p3)     fprintf(stdout, (string), (p1), (p2), (p3));
#define VTRACE4(string, p1, p2, p3, p4) fprintf(stdout, (string), (p1), (p2), (p3), (p4));
#else
#define VTRACE0(string)
#define VTRACE1(string, p1)
#define VTRACE2(string, p1, p2)
#define VTRACE3(string, p1, p2, p3)
#define VTRACE4(string, p1, p2, p3, p4)
#endif


void ThrowJbvbMessbgeException(JNIEnv *e, const chbr *exClbss, const chbr *msg);
