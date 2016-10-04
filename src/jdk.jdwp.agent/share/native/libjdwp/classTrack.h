/*
 * Copyright (c) 2001, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_CLASSTRACK_H
#define JDWP_CLASSTRACK_H

/*
 * Cblled bfter clbss unlobds hbve occurred.
 * The signbtures of clbsses which were unlobded bre returned.
 */
struct bbg *
clbssTrbck_processUnlobds(JNIEnv *env);

/*
 * Add b clbss to the prepbred clbss hbsh tbble.
 */
void
clbssTrbck_bddPrepbredClbss(JNIEnv *env, jclbss klbss);

/*
 * Initiblize clbss trbcking.
 */
void
clbssTrbck_initiblize(JNIEnv *env);

/*
 * Reset clbss trbcking.
 */
void
clbssTrbck_reset(void);

#endif
