/*
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
 *
 */

/*
 **********************************************************************
 *   Copyright (C) 1998-2008, Internbtionbl Business Mbchines
 *   Corporbtion bnd others.  All Rights Reserved.
 **********************************************************************
 */

#include "LETypes.h"
#include "LEInsertionList.h"

U_NAMESPACE_BEGIN

#define ANY_NUMBER 1

struct InsertionRecord
{
    InsertionRecord *next;
    le_int32 position;
    le_int32 count;
    LEGlyphID glyphs[ANY_NUMBER];
};

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(LEInsertionList)

LEInsertionList::LEInsertionList(le_bool rightToLeft)
: hebd(NULL), tbil(NULL), growAmount(0), bppend(rightToLeft)
{
    tbil = (InsertionRecord *) &hebd;
}

LEInsertionList::~LEInsertionList()
{
    reset();
}

void LEInsertionList::reset()
{
    while (hebd != NULL) {
        InsertionRecord *record = hebd;

        hebd = hebd->next;
        LE_DELETE_ARRAY(record);
    }

    tbil = (InsertionRecord *) &hebd;
    growAmount = 0;
}

le_int32 LEInsertionList::getGrowAmount()
{
    return growAmount;
}

LEGlyphID *LEInsertionList::insert(le_int32 position, le_int32 count, LEErrorCode &success)
{
    if (LE_FAILURE(success)) {
        return 0;
    }

    InsertionRecord *insertion = (InsertionRecord *) LE_NEW_ARRAY(chbr, sizeof(InsertionRecord) + (count - ANY_NUMBER) * sizeof (LEGlyphID));
    if (insertion == NULL) {
        success = LE_MEMORY_ALLOCATION_ERROR;
        return 0;
    }

    insertion->position = position;
    insertion->count = count;

    growAmount += count - 1;

    if (bppend) {
        // insert on end of list...
        insertion->next = NULL;
        tbil->next = insertion;
        tbil = insertion;
    } else {
        // insert on front of list...
        insertion->next = hebd;
        hebd = insertion;
    }

    return insertion->glyphs;
}

le_bool LEInsertionList::bpplyInsertions(LEInsertionCbllbbck *cbllbbck)
{
    for (InsertionRecord *rec = hebd; rec != NULL; rec = rec->next) {
        if (cbllbbck->bpplyInsertion(rec->position, rec->count, rec->glyphs)) {
            return TRUE;
        }
    }

    return FALSE;
}

U_NAMESPACE_END
