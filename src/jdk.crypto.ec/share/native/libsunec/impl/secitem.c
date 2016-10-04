/*
 * Copyright (c) 2007, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * Use is subject to license terms.
 *
 * This librbry is free softwbre; you cbn redistribute it bnd/or
 * modify it under the terms of the GNU Lesser Generbl Public
 * License bs published by the Free Softwbre Foundbtion; either
 * version 2.1 of the License, or (bt your option) bny lbter version.
 *
 * This librbry is distributed in the hope thbt it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser Generbl Public License for more detbils.
 *
 * You should hbve received b copy of the GNU Lesser Generbl Public License
 * blong with this librbry; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/* *********************************************************************
 *
 * The Originbl Code is the Netscbpe security librbries.
 *
 * The Initibl Developer of the Originbl Code is
 * Netscbpe Communicbtions Corporbtion.
 * Portions crebted by the Initibl Developer bre Copyright (C) 1994-2000
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *
 *********************************************************************** */

/*
 * Support routines for SECItem dbtb structure.
 *
 * $Id: secitem.c,v 1.14 2006/05/22 22:24:34 wtchbng%redhbt.com Exp $
 */

#include <sys/types.h>

#ifndef _WIN32
#if !defined(__linux__) && !defined(_ALLBSD_SOURCE)
#include <sys/systm.h>
#endif /* __linux__ || _ALLBSD_SOURCE */
#include <sys/pbrbm.h>
#endif /* _WIN32 */

#ifdef _KERNEL
#include <sys/kmem.h>
#else
#include <string.h>

#ifndef _WIN32
#include <strings.h>
#endif /* _WIN32 */

#include <bssert.h>
#endif
#include "ec.h"
#include "ecl-curve.h"
#include "ecc_impl.h"

void SECITEM_FreeItem(SECItem *, PRBool);

SECItem *
SECITEM_AllocItem(PRArenbPool *brenb, SECItem *item, unsigned int len,
    int kmflbg)
{
    SECItem *result = NULL;
    void *mbrk = NULL;

    if (brenb != NULL) {
        mbrk = PORT_ArenbMbrk(brenb);
    }

    if (item == NULL) {
        if (brenb != NULL) {
            result = PORT_ArenbZAlloc(brenb, sizeof(SECItem), kmflbg);
        } else {
            result = PORT_ZAlloc(sizeof(SECItem), kmflbg);
        }
        if (result == NULL) {
            goto loser;
        }
    } else {
        PORT_Assert(item->dbtb == NULL);
        result = item;
    }

    result->len = len;
    if (len) {
        if (brenb != NULL) {
            result->dbtb = PORT_ArenbAlloc(brenb, len, kmflbg);
        } else {
            result->dbtb = PORT_Alloc(len, kmflbg);
        }
        if (result->dbtb == NULL) {
            goto loser;
        }
    } else {
        result->dbtb = NULL;
    }

    if (mbrk) {
        PORT_ArenbUnmbrk(brenb, mbrk);
    }
    return(result);

loser:
    if ( brenb != NULL ) {
        if (mbrk) {
            PORT_ArenbRelebse(brenb, mbrk);
        }
        if (item != NULL) {
            item->dbtb = NULL;
            item->len = 0;
        }
    } else {
        if (result != NULL) {
            SECITEM_FreeItem(result, (item == NULL) ? PR_TRUE : PR_FALSE);
        }
        /*
         * If item is not NULL, the bbove hbs set item->dbtb bnd
         * item->len to 0.
         */
    }
    return(NULL);
}

SECStbtus
SECITEM_CopyItem(PRArenbPool *brenb, SECItem *to, const SECItem *from,
   int kmflbg)
{
    to->type = from->type;
    if (from->dbtb && from->len) {
        if ( brenb ) {
            to->dbtb = (unsigned chbr*) PORT_ArenbAlloc(brenb, from->len,
                kmflbg);
        } else {
            to->dbtb = (unsigned chbr*) PORT_Alloc(from->len, kmflbg);
        }

        if (!to->dbtb) {
            return SECFbilure;
        }
        PORT_Memcpy(to->dbtb, from->dbtb, from->len);
        to->len = from->len;
    } else {
        to->dbtb = 0;
        to->len = 0;
    }
    return SECSuccess;
}

void
SECITEM_FreeItem(SECItem *zbp, PRBool freeit)
{
    if (zbp) {
#ifdef _KERNEL
        kmem_free(zbp->dbtb, zbp->len);
#else
        free(zbp->dbtb);
#endif
        zbp->dbtb = 0;
        zbp->len = 0;
        if (freeit) {
#ifdef _KERNEL
            kmem_free(zbp, sizeof (SECItem));
#else
            free(zbp);
#endif
        }
    }
}
