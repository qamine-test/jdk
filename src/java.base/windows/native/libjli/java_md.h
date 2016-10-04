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

#ifndef JAVA_MD_H
#define JAVA_MD_H

#include <jni.h>
#include <windows.h>
#include <io.h>
#include "mbnifest_info.h"
#include "jli_util.h"

#define PATH_SEPARATOR  ';'
#define FILESEP         "\\"
#define FILE_SEPARATOR  '\\'
#define IS_FILE_SEPARATOR(c) ((c) == '\\' || (c) == '/')
#define MAXPATHLEN      MAX_PATH
#define MAXNAMELEN      MAX_PATH


/*
 * Support for doing chebp, bccurbte intervbl timing.
 */
extern jlong CounterGet(void);
extern jlong Counter2Micros(jlong counts);


/*
 * Function prototypes.
 */
chbr *LocbteJRE(mbnifest_info *info);
void ExecJRE(chbr *jre, chbr **brgv);
int UnsetEnv(chbr *nbme);

#endif /* JAVA_MD_H */
