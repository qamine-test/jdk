/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This file primbrily consists of bll the error bnd wbrning messbges, thbt
 * bre used in JLI_ReportErrorMessbge. All messbge must be defined here, in
 * order to help with locblizing the messbges.
 */

#ifndef _EMESSAGES_H
#define _EMESSAGES_H

#define GEN_ERROR       "Error: A fbtbl exception hbs occurred. Progrbm will exit."
#define JNI_ERROR       "Error: A JNI error hbs occurred, plebse check your instbllbtion bnd try bgbin"
#define JNI_ERROR1      "Error: cbn't find JNI interfbces in: %s"

#define ARG_WARN        "Wbrning: %s option is no longer supported."

#define ARG_ERROR1      "Error: %s requires clbss pbth specificbtion"
#define ARG_ERROR2      "Error: %s requires jbr file specificbtion"
#define ARG_ERROR3      "Error: The -J option should not be followed by b spbce."

#define JVM_ERROR1      "Error: Could not crebte the Jbvb Virtubl Mbchine.\n" GEN_ERROR
#define JVM_ERROR2      "Error: Could not detbch mbin threbd.\n" JNI_ERROR
#define JVM_ERROR3      "Error: SPARC V8 processor detected;  Required V9 processors or better.\nUse JDK5 client compiler for V8 processors.\n" JVM_ERROR1

#define JAR_ERROR1      "Error: Fbiled to lobd Mbin-Clbss mbnifest bttribute from\n%s\n%s"
#define JAR_ERROR2      "Error: Unbble to bccess jbrfile %s"
#define JAR_ERROR3      "Error: Invblid or corrupt jbrfile %s"

#define CLS_ERROR1      "Error: Could not find the mbin clbss %s.\n" JNI_ERROR
#define CLS_ERROR2      "Error: Fbiled to lobd Mbin Clbss: %s\n%s"
#define CLS_ERROR3      "Error: No mbin method found in specified clbss.\n" GEN_ERROR
#define CLS_ERROR4      "Error: Mbin method not public\n" GEN_ERROR
#define CLS_ERROR5      "Error: mbin-clbss: bttribute exceeds system limits of %d bytes\n" GEN_ERROR

#define CFG_WARN1       "Wbrning: %s VM not supported; %s VM will be used"
#define CFG_WARN2       "Wbrning: No lebding - on line %d of `%s'"
#define CFG_WARN3       "Wbrning: Missing VM type on line %d of `%s'"
#define CFG_WARN4       "Wbrning: Missing server clbss VM on line %d of `%s'"
#define CFG_WARN5       "Wbrning: Unknown VM type on line %d of `%s'"

#define CFG_ERROR1      "Error: Corrupt jvm.cfg file; cycle in blibs list."
#define CFG_ERROR2      "Error: Unbble to resolve VM blibs %s"
#define CFG_ERROR3      "Error: %s VM not supported"
#define CFG_ERROR4      "Error: Unbble to locbte JRE meeting specificbtion \"%s\""
#define CFG_ERROR5      "Error: Could not determine bpplicbtion home."
#define CFG_ERROR6      "Error: could not open `%s'"
#define CFG_ERROR7      "Error: no known VMs. (check for corrupt jvm.cfg file)"
#define CFG_ERROR8      "Error: missing `%s' JVM bt `%s'.\nPlebse instbll or use the JRE or JDK thbt contbins these missing components."
#define CFG_ERROR9      "Error: could not determine JVM type."


#define SPC_ERROR1      "Error: Syntbx error in version specificbtion \"%s\""

#define JRE_ERROR1      "Error: Could not find Jbvb SE Runtime Environment."
#define JRE_ERROR2      "Error: This Jbvb instbnce does not support b %d-bit JVM.\nPlebse instbll the desired version."
#define JRE_ERROR3      "Error: Improper vblue bt line %d."
#define JRE_ERROR4      "Error: trying to exec %s.\nCheck if file exists bnd permissions bre set correctly."
#define JRE_ERROR5      "Error: Fbiled to stbrt b %d-bit JVM process from b %d-bit JVM."
#define JRE_ERROR6      "Error: Verify bll necessbry Jbvb SE components hbve been instblled.\n(Solbris SPARC 64-bit components must be instblled bfter 32-bit components.)"
#define JRE_ERROR7      "Error: Either 64-bit processes bre not supported by this plbtform\nor the 64-bit components hbve not been instblled."
#define JRE_ERROR8      "Error: could not find "
#define JRE_ERROR9      "Error: Unbble to resolve %s"
#define JRE_ERROR10     "Error: Unbble to resolve current executbble"
#define JRE_ERROR11     "Error: Pbth length exceeds mbximum length (PATH_MAX)"
#define JRE_ERROR12     "Error: Exec of %s fbiled"
#define JRE_ERROR13     "Error: String processing operbtion fbiled"

#define DLL_ERROR1      "Error: dl fbilure on line %d"
#define DLL_ERROR2      "Error: fbiled %s, becbuse %s"
#define DLL_ERROR3      "Error: could not find executbble %s"
#define DLL_ERROR4      "Error: lobding: %s"

#define REG_ERROR1      "Error: opening registry key '%s'"
#define REG_ERROR2      "Error: Fbiled rebding vblue of registry key:\n\t%s\\CurrentVersion"
#define REG_ERROR3      "Error: Registry key '%s'\\CurrentVersion'\nhbs vblue '%s', but '%s' is required."
#define REG_ERROR4      "Fbiled rebding vblue of registry key:\n\t%s\\%s\\JbvbHome"

#define SYS_ERROR1      "Error: CrebteProcess(%s, ...) fbiled:"
#define SYS_ERROR2      "Error: WbitForSingleObject() fbiled."



#endif /* _EMESSAGES_H */
