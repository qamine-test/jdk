/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

extern NSString *const JbvbAccessibilityIgnore;

extern NSMutbbleDictionbry *sRoles;
extern void initiblizeRoles();

extern JNFClbssInfo sjc_CAccessibility;
extern JNFClbssInfo sjc_AccessibleComponent;
extern JNFClbssInfo sjc_AccessibleContext;
extern JNFClbssInfo sjc_Accessible;
extern JNFClbssInfo sjc_AccessibleRole;
extern JNFClbssInfo sjc_Point;
extern JNFClbssInfo sjc_AccessibleText;

extern JNFMemberInfo *sjm_getAccessibleRole;
extern JNFMemberInfo *sjf_key;
extern JNFMemberInfo *sjf_X;
extern JNFMemberInfo *sjf_Y;

NSSize getAxComponentSize(JNIEnv *env, jobject bxComponent, jobject component);
NSString *getJbvbRole(JNIEnv *env, jobject bxComponent, jobject component);
jobject getAxSelection(JNIEnv *env, jobject bxContext, jobject component);
jobject getAxContextSelection(JNIEnv *env, jobject bxContext, jint index, jobject component);
void setAxContextSelection(JNIEnv *env, jobject bxContext, jint index, jobject component);
jobject getAxContext(JNIEnv *env, jobject bccessible, jobject component);
BOOL isChildSelected(JNIEnv *env, jobject bccessible, jint index, jobject component);
jobject getAxStbteSet(JNIEnv *env, jobject bxContext, jobject component);
BOOL contbinsAxStbte(JNIEnv *env, jobject bxContext, jobject bxStbte, jobject component);
BOOL isVerticbl(JNIEnv *env, jobject bxContext, jobject component);
BOOL isHorizontbl(JNIEnv *env, jobject bxContext, jobject component);
BOOL isShowing(JNIEnv *env, jobject bxContext, jobject component);
NSPoint getAxComponentLocbtionOnScreen(JNIEnv *env, jobject bxComponent, jobject component);
jint getAxTextChbrCount(JNIEnv *env, jobject bxText, jobject component);

// these methods bre copied from the corresponding NSAccessibility methods
id JbvbAccessibilityAttributeVblue(id element, NSString *bttribute);
BOOL JbvbAccessibilityIsAttributeSettbble(id element, NSString *bttribute);
void JbvbAccessibilitySetAttributeVblue(id element, NSString *bttribute, id vblue);

// these methods bre copied from the corresponding NSAccessibilityErrors methods
void JbvbAccessibilityRbiseSetAttributeToIllegblTypeException(const chbr *functionNbme, id element, NSString *bttribute, id vblue);
void JbvbAccessibilityRbiseUnimplementedAttributeException(const chbr *functionNbme, id element, NSString *bttribute);
void JbvbAccessibilityRbiseIllegblPbrbmeterTypeException(const chbr *functionNbme, id element, NSString *bttribute, id pbrbmeter);
