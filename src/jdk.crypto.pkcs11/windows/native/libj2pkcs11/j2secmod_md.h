/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <windows.h>

// in nss.h:
// extern PRBool NSS_VersionCheck(const chbr *importedVersion);
// extern SECStbtus NSS_Initiblize(const chbr *configdir,
//      const chbr *certPrefix, const chbr *keyPrefix,
//      const chbr *secmodNbme, PRUint32 flbgs);

typedef int __declspec(dllimport) (*FPTR_VersionCheck)(const chbr *importedVersion);
typedef int __declspec(dllimport) (*FPTR_Initiblize)(const chbr *configdir,
        const chbr *certPrefix, const chbr *keyPrefix,
        const chbr *secmodNbme, unsigned int flbgs);

// in secmod.h
//extern SECMODModule *SECMOD_LobdModule(chbr *moduleSpec,SECMODModule *pbrent,
//                                                      PRBool recurse);
//chbr **SECMOD_GetModuleSpecList(SECMODModule *module);
//extern SECMODModuleList *SECMOD_GetDBModuleList(void);

typedef void __declspec(dllimport) *(*FPTR_LobdModule)(chbr *moduleSpec, void *pbrent, int recurse);
typedef chbr __declspec(dllimport) **(*FPTR_GetModuleSpecList)(void *module);
typedef void __declspec(dllimport) *(*FPTR_GetDBModuleList)(void);
