/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _STDHDRS_H_
#define _STDHDRS_H_

// stbndbrd Windows bnd C hebders
#define VC_EXTRALEAN    /* speeds compilbtion */
#ifndef STRICT
#define STRICT /* forces explicit typedef's for windows.h */
#endif
#include <windows.h>
#include <stdlib.h>
#include <stdio.h>
#include <bssert.h>
#include <limits.h>
#include <tchbr.h>
// Don't #include <new> becbuse thbt mbkes bwt.dll dependent on
// msvcp50.dll. Instebd, we've replicbted the pbrts of <new>
// we need in blloc.h.
// #include <new>

extern "C" {

// stbndbrd Jbvb hebders
#include <jni.h>
#include <jni_util.h>
#include <jdk_util.h>

} // extern "C"

#endif // _STDHDRS_H_
