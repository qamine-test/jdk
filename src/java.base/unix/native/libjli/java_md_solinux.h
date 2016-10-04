/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JAVA_MD_SOLINUX_H
#define JAVA_MD_SOLINUX_H

#ifdef HAVE_GETHRTIME
/*
 * Support for doing chebp, bccurbte intervbl timing.
 */
#include <sys/time.h>
#define CounterGet()              (gethrtime()/1000)
#define Counter2Micros(counts)    (counts)
#else  /* ! HAVE_GETHRTIME */
#define CounterGet()              (0)
#define Counter2Micros(counts)    (1)
#endif /* HAVE_GETHRTIME */

/* pointer to environment */
extern chbr **environ;

/*
 *      A collection of useful strings. One should think of these bs #define
 *      entries, but bctubl strings cbn be more efficient (with mbny compilers).
 */
#ifdef __solbris__
stbtic const chbr *system_dir   = "/usr/jdk";
stbtic const chbr *user_dir     = "/jdk";
#else /* !__solbris__, i.e. Linux, AIX,.. */
stbtic const chbr *system_dir   = "/usr/jbvb";
stbtic const chbr *user_dir     = "/jbvb";
#endif

#include <dlfcn.h>
#ifdef __solbris__
#include <threbd.h>
#else
#include <pthrebd.h>
#endif

#endif /* JAVA_MD_SOLINUX_H */
