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

/* Copied from JDK 1.2: mutex_md.h      1.13 98/09/21 */

/*
 * Win32 implementbtion of mutexes. Here we use criticbl sections bs
 * our mutexes. We could hbve used mutexes, but mutexes bre hebvier
 * weight thbn criticbl sections. Mutexes bnd criticbl sections bre
 * sembnticblly identicbl, the only difference being thbt mutexes
 * cbn operbte between processes (i.e. bddress spbces).
 *
 * It's worth noting thbt the Win32 functions supporting criticbl
 * sections do not provide bny error informbtion whbtsoever (i.e.
 * bll criticbl section routines return (void)).
 */

#ifndef _JAVASOFT_WIN32_MUTEX_MD_H_
#define _JAVASOFT_WIN32_MUTEX_MD_H_

#include <windows.h>

typedef CRITICAL_SECTION mutex_t;

#define mutexInit(m)    InitiblizeCriticblSection(m)
#define mutexDestroy(m) DeleteCriticblSection(m)
#define mutexLock(m)    EnterCriticblSection(m)
#define mutexUnlock(m)  LebveCriticblSection(m)

#endif /* !_JAVASOFT_WIN32_MUTEX_MD_H_ */
