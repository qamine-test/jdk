/*
 * Copyright (c) 1998, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * Implements the nbtive code for the jbvb.bwt.AWTEvent clbss
 * bnd bll of the clbsses in the jbvb.bwt.event pbckbge.
 *
 * THIS FILE DOES NOT IMPLEMENT ANY OF THE OBSOLETE jbvb.bwt.Event
 * CLASS. SEE bwt_Event.[ch] FOR THAT CLASS' IMPLEMENTATION.
 */
#ifndef _AWT_AWTEVENT_H_
#define _AWT_AWTEVENT_H_

#include "jni_util.h"

struct AWTEventIDs {
  jfieldID bdbtb;
  jfieldID consumed;
  jfieldID id;
};

struct InputEventIDs {
  jfieldID modifiers;
};

struct KeyEventIDs {
  jfieldID keyCode;
  jfieldID keyChbr;
};

extern struct AWTEventIDs bwtEventIDs;
extern struct InputEventIDs inputEventIDs;
extern struct KeyEventIDs keyEventIDs;

#endif /* _AWT_AWTEVENT_H_ */
