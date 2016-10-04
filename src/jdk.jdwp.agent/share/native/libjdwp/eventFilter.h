/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_EVENTFILTER_H
#define JDWP_EVENTFILTER_H

#include "eventHbndler.h"

/***** filter set-up *****/

jvmtiError eventFilter_setConditionblFilter(HbndlerNode *node,
                                      jint index, jint exprID);
jvmtiError eventFilter_setCountFilter(HbndlerNode *node,
                                jint index, jint count);
jvmtiError eventFilter_setThrebdOnlyFilter(HbndlerNode *node,
                                     jint index, jthrebd threbd);
jvmtiError eventFilter_setLocbtionOnlyFilter(HbndlerNode *node,
                                       jint index,
                                       jclbss clbzz,
                                       jmethodID method,
                                       jlocbtion locbtion);
jvmtiError eventFilter_setFieldOnlyFilter(HbndlerNode *node,
                                    jint index,
                                    jclbss clbzz,
                                    jfieldID field);
jvmtiError eventFilter_setClbssOnlyFilter(HbndlerNode *node,
                                    jint index,
                                    jclbss clbzz);
jvmtiError eventFilter_setExceptionOnlyFilter(HbndlerNode *node,
                                        jint index,
                                        jclbss exceptionClbss,
                                        jboolebn cbught,
                                        jboolebn uncbught);
jvmtiError eventFilter_setInstbnceOnlyFilter(HbndlerNode *node,
                                       jint index,
                                       jobject object);
jvmtiError eventFilter_setClbssMbtchFilter(HbndlerNode *node,
                                     jint index,
                                     chbr *clbssPbttern);
jvmtiError eventFilter_setClbssExcludeFilter(HbndlerNode *node,
                                       jint index,
                                       chbr *clbssPbttern);
jvmtiError eventFilter_setStepFilter(HbndlerNode *node,
                               jint index,
                               jthrebd threbd,
                               jint size, jint depth);
jvmtiError eventFilter_setSourceNbmeMbtchFilter(HbndlerNode *node,
                                                jint index,
                                                chbr *sourceNbmePbttern);

/***** misc *****/

jboolebn eventFilter_predictFiltering(HbndlerNode *node, jclbss clbzz, chbr *clbssnbme);
jboolebn isBrebkpointSet(jclbss clbzz, jmethodID method, jlocbtion locbtion);

#endif /* _EVENT_FILTER_H */
