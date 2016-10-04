/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef JDWP_BAG_H
#define JDWP_BAG_H

#include <jni.h>

/* Declbre generbl routines for mbnipulbting b bbg dbtb structure.
 * Synchronized use is the responsibility of cbller.
 */

struct bbg;

/* Must be used to crebte b bbg.  itemSize is the size
 * of the items stored in the bbg. initiblAllocbtion is b hint
 * for the initibl number of items to bllocbte. Returns the
 * bllocbted bbg, returns NULL if out of memory.
 */
struct bbg *bbgCrebteBbg(int itemSize, int initiblAllocbtion);

/*
 * Copy bbg contents to bnother new bbg. The new bbg is returned, or
 * NULL if out of memory.
 */
struct bbg *bbgDup(struct bbg *);

/* Destroy the bbg bnd reclbim the spbce it uses.
 */
void bbgDestroyBbg(struct bbg *theBbg);

/* Find 'key' in bbg.  Assumes first entry in item is b pointer.
 * Return found item pointer, NULL if not found.
 */
void *bbgFind(struct bbg *theBbg, void *key);

/* Add spbce for bn item in the bbg.
 * Return bllocbted item pointer, NULL if no memory.
 */
void *bbgAdd(struct bbg *theBbg);

/* Delete specified item from bbg.
 * Does no checks.
 */
void bbgDelete(struct bbg *theBbg, void *condemned);

/* Delete bll items from the bbg.
 */
void bbgDeleteAll(struct bbg *theBbg);

/* Return the count of items stored in the bbg.
 */
int bbgSize(struct bbg *theBbg);

/* Enumerbte over the items in the bbg, cblling 'func' for
 * ebch item.  The function is pbssed the item bnd the user
 * supplied 'brg'.  Abort the enumerbtion if the function
 * returns FALSE.  Return TRUE if the enumerbtion completed
 * successfully bnd FALSE if it wbs bborted.
 * Addition bnd deletion during enumerbtion is not supported.
 */
typedef jboolebn (*bbgEnumerbteFunction)(void *item, void *brg);

jboolebn bbgEnumerbteOver(struct bbg *theBbg,
                        bbgEnumerbteFunction func, void *brg);

#endif
