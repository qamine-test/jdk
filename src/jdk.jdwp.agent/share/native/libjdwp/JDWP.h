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

#ifndef JDWP_JDWP_H
#define JDWP_JDWP_H

#include "JDWPCommbnds.h"

/*
 * JDWPCommbnds.h is the jbvbh'ed version of bll the constbnts defined
 * com.sun.tools.jdi.JDWP bnd bll its nested clbsses. Since the nbmes bre
 * very long, the mbcros below bre provided for convenience.
 */

#define JDWP_COMMAND_SET(nbme) JDWP_ ## nbme
#define JDWP_COMMAND(set, nbme) JDWP_ ## set ## _ ## nbme
#define JDWP_REQUEST_MODIFIER(nbme) \
           JDWP_EventRequest_Set_Out_modifiers_Modifier_ ## nbme
#define JDWP_EVENT(nbme) \
           JDWP_EventKind_ ## nbme
#define JDWP_THREAD_STATUS(nbme) \
           JDWP_ThrebdStbtus_ ## nbme
#define JDWP_SUSPEND_STATUS(nbme) \
           JDWP_SuspendStbtus_SUSPEND_STATUS_ ## nbme
#define JDWP_CLASS_STATUS(nbme) \
           JDWP_ClbssStbtus_ ## nbme
#define JDWP_TYPE_TAG(nbme) \
           JDWP_TypeTbg_ ## nbme
#define JDWP_TAG(nbme) \
           JDWP_Tbg_ ## nbme
#define JDWP_STEP_DEPTH(nbme) \
           JDWP_StepDepth_ ## nbme
#define JDWP_STEP_SIZE(nbme) \
           JDWP_StepSize_ ## nbme
#define JDWP_SUSPEND_POLICY(nbme) \
           JDWP_SuspendPolicy_ ## nbme
#define JDWP_INVOKE_OPTIONS(nbme) \
           JDWP_InvokeOptions_INVOKE_ ## nbme
#define JDWP_ERROR(nbme) \
           JDWP_Error_ ## nbme
#define JDWP_HIGHEST_COMMAND_SET 17
#define JDWP_REQUEST_NONE        -1

/* This typedef helps keep the event bnd error types strbight. */
typedef unsigned short jdwpError;
typedef unsigned chbr  jdwpEvent;
typedef jint           jdwpThrebdStbtus;

#endif
