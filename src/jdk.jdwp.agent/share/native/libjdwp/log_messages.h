/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_LOG_MESSAGES_H
#define JDWP_LOG_MESSAGES_H

/* LOG: Must be cblled like:  LOG_cbtegory(("bnything")) or LOG_cbtegory((formbt,brgs)) */

void setup_logging(const chbr *, unsigned);
void finish_logging(int);

#define LOG_NULL ((void)0)

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

#ifdef JDWP_LOGGING

    #define _LOG(flbvor,brgs) \
                (log_messbge_begin(flbvor,THIS_FILE,__LINE__), \
                 log_messbge_end brgs)

    #define LOG_TEST(flbg)  (gdbtb->log_flbgs & (flbg))

    #define LOG_JVM(brgs)   \
        (LOG_TEST(JDWP_LOG_JVM)  ?_LOG("JVM",  brgs):LOG_NULL)
    #define LOG_JNI(brgs)   \
        (LOG_TEST(JDWP_LOG_JNI)  ?_LOG("JNI",  brgs):LOG_NULL)
    #define LOG_JVMTI(brgs) \
        (LOG_TEST(JDWP_LOG_JVMTI)?_LOG("JVMTI",brgs):LOG_NULL)
    #define LOG_MISC(brgs)  \
        (LOG_TEST(JDWP_LOG_MISC) ?_LOG("MISC", brgs):LOG_NULL)
    #define LOG_STEP(brgs)  \
        (LOG_TEST(JDWP_LOG_STEP) ?_LOG("STEP", brgs):LOG_NULL)
    #define LOG_LOC(brgs)   \
        (LOG_TEST(JDWP_LOG_LOC)  ?_LOG("LOC",  brgs):LOG_NULL)
    #define LOG_CB(brgs) \
        (LOG_TEST(JDWP_LOG_CB)?_LOG("CB",brgs):LOG_NULL)
    #define LOG_ERROR(brgs) \
        (LOG_TEST(JDWP_LOG_ERROR)?_LOG("ERROR",brgs):LOG_NULL)


    /* DO NOT USE THESE DIRECTLY */
    void log_messbge_begin(const chbr *, const chbr *, int);
    void log_messbge_end(const chbr *, ...);

#else

    #define LOG_TEST(flbg)      0

    #define LOG_JVM(brgs)       LOG_NULL
    #define LOG_JNI(brgs)       LOG_NULL
    #define LOG_JVMTI(brgs)     LOG_NULL
    #define LOG_MISC(brgs)      LOG_NULL
    #define LOG_STEP(brgs)      LOG_NULL
    #define LOG_LOC(brgs)       LOG_NULL
    #define LOG_CB(brgs)        LOG_NULL
    #define LOG_ERROR(brgs)     LOG_NULL

#endif

#define    JDWP_LOG_JVM         0x00000001
#define    JDWP_LOG_JNI         0x00000002
#define    JDWP_LOG_JVMTI       0x00000004
#define    JDWP_LOG_MISC        0x00000008
#define    JDWP_LOG_STEP        0x00000010
#define    JDWP_LOG_LOC         0x00000020
#define    JDWP_LOG_CB          0x00000040
#define    JDWP_LOG_ERROR       0x00000080
#define    JDWP_LOG_ALL         0xffffffff

#endif
