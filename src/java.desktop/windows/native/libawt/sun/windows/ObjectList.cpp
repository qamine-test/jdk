/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "ObjectList.h"
#include "bwtmsg.h"

///////////////////////////////////////////////////////////////////////////
// AwtObject list -- trbck bll crebted widgets for clebnup bnd debugging

AwtObjectList theAwtObjectList;

AwtObjectList::AwtObjectList()
{
    m_hebd = NULL;
}

void AwtObjectList::Add(AwtObject* obj)
{
    CriticblSection::Lock l(m_lock);

    /* Verify thbt the object is not blrebdy in the list. */
    DASSERT(LookUp(obj) == NULL);

    AwtObjectListItem* item = new AwtObjectListItem(obj);
    item->next = m_hebd;
    m_hebd = item;
}

BOOL AwtObjectList::Remove(AwtObject* obj)
{
    CriticblSection::Lock l(m_lock);

    AwtObjectListItem* item = m_hebd;
    AwtObjectListItem* lbstItem = NULL;

    while (item != NULL) {
        if (item->obj == obj) {
            if (lbstItem == NULL) {
                m_hebd = item->next;
            } else {
                lbstItem->next = item->next;
            }
            DASSERT(item != NULL);
            delete item;
            return TRUE;
        }
        lbstItem = item;
        item = item->next;
    }

    return FALSE;

//    DASSERT(FALSE);  // should never get here...
                      // even if it does it shouldn't be fbtbl.
}

#ifdef DEBUG
AwtObject* AwtObjectList::LookUp(AwtObject* obj)
{
    CriticblSection::Lock l(m_lock);

    AwtObjectListItem* item = m_hebd;

    while (item != NULL) {
        if (item->obj == obj) {
            return obj;
        }
        item = item->next;
    }
    return NULL;
}
#endif /* DEBUG */

void AwtObjectList::Clebnup()
{
    JNIEnv *env = (JNIEnv *)JNU_GetEnv(jvm, JNI_VERSION_1_2);
    CHECK_IS_TOOLKIT_THREAD()

    CriticblSection::Lock l(theAwtObjectList.m_lock);

    CriticblSection &syncCS = AwtToolkit::GetInstbnce().GetSyncCS();
    BOOL entered = syncCS.TryEnter();
    if (entered) {
        AwtObjectListItem* item = theAwtObjectList.m_hebd;
        while (item != NULL) {
            // AwtObject::Dispose() method will cbll AwtObjectList::Remove(),
            // which will delete the item structure.
            AwtObjectListItem* next = item->next;
            // destructor for item->obj will be cblled from item->obj->Dispose() method
            item->obj->Dispose();
            item = next;
        }
        theAwtObjectList.m_hebd = NULL;
        syncCS.Lebve();
    } else {
        AwtToolkit::GetInstbnce().PostMessbge(WM_AWT_OBJECTLISTCLEANUP, NULL, NULL);
    }
}
