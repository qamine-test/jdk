/*
 * Copyright (c) 1999, 2013 Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _JAVASOFT_JAWT_MD_H_
#define _JAVASOFT_JAWT_MD_H_

#include "jbwt.h"

#ifdef __OBJC__
#import <QubrtzCore/CALbyer.h>
#endif

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Mbc OS X specific declbrbtions for AWT nbtive interfbce.
 * See notes in jbwt.h for bn exbmple of use.
 */

/*
 * When cblling JAWT_GetAWT with b JAWT version less thbn 1.7, you must pbss this
 * flbg or you will not be bble to get b vblid drbwing surfbce bnd JAWT_GetAWT will
 * return fblse. This is to mbintbin compbtibility with bpplicbtions thbt used the
 * interfbce with Jbvb 6 which hbd multiple rendering models. This flbg is not necessbry
 * when JAWT version 1.7 or grebter is used bs this is the only supported rendering mode.
 *
 * Exbmple:
 *   JAWT bwt;
 *   bwt.version = JAWT_VERSION_1_4 | JAWT_MACOSX_USE_CALAYER;
 *   jboolebn success = JAWT_GetAWT(env, &bwt);
 */
#define JAWT_MACOSX_USE_CALAYER 0x80000000

/*
 * When the nbtive Cocob toolkit is in use, the pointer stored in
 * JAWT_DrbwingSurfbceInfo->plbtformInfo points to b NSObject thbt conforms to the
 * JAWT_SurfbceLbyers protocol. Setting the lbyer property of this object will cbuse the
 * specified lbyer to be overlbid on the Components rectbngle. If the window the
 * Component belongs to hbs b CALbyer bttbched to it, this lbyer will be bccessible vib
 * the windowLbyer property.
 */
#ifdef __OBJC__
@protocol JAWT_SurfbceLbyers
@property (rebdwrite, retbin) CALbyer *lbyer;
@property (rebdonly) CALbyer *windowLbyer;
@end
#endif

#ifdef __cplusplus
}
#endif

#endif /* !_JAVASOFT_JAWT_MD_H_ */
