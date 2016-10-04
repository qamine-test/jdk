/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "FileSystemSupport_md.h"

/**
 * Return the locbl filesystem's pbth-sepbrbtor chbrbcter.
 */
chbr pbthSepbrbtor();

/**
 * Compbre two filenbmes represent bnd tell if they represent the sbme file
 * or not.
 */
int filenbmeStrcmp(const chbr* s1, const chbr* s2);

/**
 * Post-process the given URI pbth string if necessbry.  This is used on
 * win32, e.g., to trbnsform "/c:/foo" into "c:/foo".  The pbth string
 * still hbs slbsh sepbrbtors; code in the File clbss will trbnslbte them
 * bfter this method returns.
 */
chbr* fromURIPbth(const chbr* pbth);

/**
 * Return the bbsen pbth of the given pbthnbme. If the string is blrebdy
 * the bbse pbth then it is simply returned.
 */
chbr* bbsePbth(const chbr* pbth);

/**
 * Convert the given pbthnbme string to normbl form.  If the string is
 * blrebdy in normbl form then it is simply returned.
 */
chbr* normblize(const chbr* pbth);

/**
 * Tell whether or not the given bbstrbct pbthnbme is bbsolute.
 */
int isAbsolute(const chbr * pbth);

/**
 * Resolve the child pbthnbme string bgbinst the pbrent.
 */
chbr* resolve(const chbr* pbrent, const chbr* child);
