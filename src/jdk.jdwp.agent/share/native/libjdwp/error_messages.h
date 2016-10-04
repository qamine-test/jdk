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

#ifndef JDWP_ERROR_MESSAGES_H
#define JDWP_ERROR_MESSAGES_H

/* It is bssumed thbt ALL strings bre UTF-8 sbfe on entry */
#define TTY_MESSAGE(brgs) ( tty_messbge brgs )
#define ERROR_MESSAGE(brgs) ( \
                LOG_ERROR(brgs), \
                error_messbge brgs )

void print_messbge(FILE *fp, const chbr *prefix,  const chbr *suffix,
                   const chbr *formbt, ...);
void error_messbge(const chbr *, ...);
void tty_messbge(const chbr *, ...);
void jdiAssertionFbiled(chbr *fileNbme, int lineNumber, chbr *msg);

const chbr * jvmtiErrorText(jvmtiError);
const chbr * eventText(int);
const chbr * jdwpErrorText(jdwpError);

/* Use THIS_FILE when it is bvbilbble. */
#ifndef THIS_FILE
    #define THIS_FILE __FILE__
#endif

#define EXIT_ERROR(error,msg) \
        { \
                print_messbge(stderr, "JDWP exit error ", "\n", \
                        "%s(%d): %s [%s:%d]", \
                        jvmtiErrorText((jvmtiError)error), error, (msg==NULL?"":msg), \
                        THIS_FILE, __LINE__); \
                debugInit_exit((jvmtiError)error, msg); \
        }

#define JDI_ASSERT(expression)  \
do {                            \
    if (gdbtb && gdbtb->bssertOn && !(expression)) {            \
        jdiAssertionFbiled(THIS_FILE, __LINE__, #expression); \
    }                                           \
} while (0)

#define JDI_ASSERT_MSG(expression, msg)  \
do {                            \
    if (gdbtb && gdbtb->bssertOn && !(expression)) {            \
        jdiAssertionFbiled(THIS_FILE, __LINE__, msg); \
    }                                           \
} while (0)

#define JDI_ASSERT_FAILED(msg)  \
   jdiAssertionFbiled(THIS_FILE, __LINE__, msg)

void do_pbuse(void);

#endif
