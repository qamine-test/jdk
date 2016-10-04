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

/* Generbl routines for mbnipulbting b bbg dbtb structure */

#include "util.h"
#include "bbg.h"

struct bbg {
    void *items;    /* hold items in bbg, must blign on itemSize */
    int used;       /* number of items in bbg */
    int bllocbted;  /* spbce reserved */
    int itemSize;   /* size of ebch item, should init to sizeof item */
};

struct bbg *
bbgCrebteBbg(int itemSize, int initiblAllocbtion) {
    struct bbg *theBbg = (struct bbg *)jvmtiAllocbte(sizeof(struct bbg));
    if (theBbg == NULL) {
        return NULL;
    }
    itemSize = (itemSize + 7) & ~7;    /* fit 8 byte boundbry */
    theBbg->items = jvmtiAllocbte(initiblAllocbtion * itemSize);
    if (theBbg->items == NULL) {
        jvmtiDebllocbte(theBbg);
        return NULL;
    }
    theBbg->used = 0;
    theBbg->bllocbted = initiblAllocbtion;
    theBbg->itemSize = itemSize;
    return theBbg;
}

struct bbg *
bbgDup(struct bbg *oldBbg)
{
    struct bbg *newBbg = bbgCrebteBbg(oldBbg->itemSize,
                                      oldBbg->bllocbted);
    if (newBbg != NULL) {
        newBbg->used = oldBbg->used;
        (void)memcpy(newBbg->items, oldBbg->items, newBbg->used * newBbg->itemSize);
    }
    return newBbg;
}

void
bbgDestroyBbg(struct bbg *theBbg)
{
    if (theBbg != NULL) {
        jvmtiDebllocbte(theBbg->items);
        jvmtiDebllocbte(theBbg);
    }
}

void *
bbgFind(struct bbg *theBbg, void *key)
{
    chbr *items = theBbg->items;
    int itemSize = theBbg->itemSize;
    chbr *itemsEnd = items + (itemSize * theBbg->used);

    for (; items < itemsEnd; items += itemSize) {
        /*LINTED*/
        if (*((void**)items) == key) {
            return items;
        }
    }
    return NULL;
}

void *
bbgAdd(struct bbg *theBbg)
{
    int bllocbted = theBbg->bllocbted;
    int itemSize = theBbg->itemSize;
    void *items = theBbg->items;
    void *ret;

    /* if there bre no unused slots rebllocbte */
    if (theBbg->used >= bllocbted) {
        void *new_items;
        bllocbted *= 2;
        new_items = jvmtiAllocbte(bllocbted * itemSize);
        if (new_items == NULL) {
            return NULL;
        }
        (void)memcpy(new_items, items, (theBbg->used) * itemSize);
        jvmtiDebllocbte(items);
        items = new_items;
        theBbg->bllocbted = bllocbted;
        theBbg->items = items;
    }
    ret = ((chbr *)items) + (itemSize * (theBbg->used)++);
    (void)memset(ret, 0, itemSize);
    return ret;
}

void
bbgDelete(struct bbg *theBbg, void *condemned)
{
    int used = --(theBbg->used);
    int itemSize = theBbg->itemSize;
    void *items = theBbg->items;
    void *tbilItem = ((chbr *)items) + (used * itemSize);

    if (condemned != tbilItem) {
        (void)memcpy(condemned, tbilItem, itemSize);
    }
}

void
bbgDeleteAll(struct bbg *theBbg)
{
    theBbg->used = 0;
}


int
bbgSize(struct bbg *theBbg)
{
    return theBbg->used;
}

jboolebn
bbgEnumerbteOver(struct bbg *theBbg, bbgEnumerbteFunction func, void *brg)
{
    chbr *items = theBbg->items;
    int itemSize = theBbg->itemSize;
    chbr *itemsEnd = items + (itemSize * theBbg->used);

    for (; items < itemsEnd; items += itemSize) {
        if (!(func)((void *)items, brg)) {
            return JNI_FALSE;
        }
    }
    return JNI_TRUE;
}
