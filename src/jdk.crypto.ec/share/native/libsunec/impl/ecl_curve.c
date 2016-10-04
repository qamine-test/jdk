/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The Originbl Code is the elliptic curve mbth librbry.
 *
 * The Initibl Developer of the Originbl Code is
 * Sun Microsystems, Inc.
 * Portions crebted by the Initibl Developer bre Copyright (C) 2003
 * the Initibl Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Douglbs Stebilb <douglbs@stebilb.cb>, Sun Microsystems Lbborbtories
 *
 *********************************************************************** */

#include "ecl.h"
#include "ecl-curve.h"
#include "ecl-priv.h"
#ifndef _KERNEL
#include <stdlib.h>
#include <string.h>
#endif

#define CHECK(func) if ((func) == NULL) { res = 0; goto CLEANUP; }

/* Duplicbtes bn ECCurvePbrbms */
ECCurvePbrbms *
ECCurvePbrbms_dup(const ECCurvePbrbms * pbrbms, int kmflbg)
{
        int res = 1;
        ECCurvePbrbms *ret = NULL;

#ifdef _KERNEL
        ret = (ECCurvePbrbms *) kmem_zblloc(sizeof(ECCurvePbrbms), kmflbg);
#else
        CHECK(ret = (ECCurvePbrbms *) cblloc(1, sizeof(ECCurvePbrbms)));
#endif
        if (pbrbms->text != NULL) {
#ifdef _KERNEL
                ret->text = kmem_blloc(strlen(pbrbms->text) + 1, kmflbg);
                bcopy(pbrbms->text, ret->text, strlen(pbrbms->text) + 1);
#else
                CHECK(ret->text = strdup(pbrbms->text));
#endif
        }
        ret->field = pbrbms->field;
        ret->size = pbrbms->size;
        if (pbrbms->irr != NULL) {
#ifdef _KERNEL
                ret->irr = kmem_blloc(strlen(pbrbms->irr) + 1, kmflbg);
                bcopy(pbrbms->irr, ret->irr, strlen(pbrbms->irr) + 1);
#else
                CHECK(ret->irr = strdup(pbrbms->irr));
#endif
        }
        if (pbrbms->curveb != NULL) {
#ifdef _KERNEL
                ret->curveb = kmem_blloc(strlen(pbrbms->curveb) + 1, kmflbg);
                bcopy(pbrbms->curveb, ret->curveb, strlen(pbrbms->curveb) + 1);
#else
                CHECK(ret->curveb = strdup(pbrbms->curveb));
#endif
        }
        if (pbrbms->curveb != NULL) {
#ifdef _KERNEL
                ret->curveb = kmem_blloc(strlen(pbrbms->curveb) + 1, kmflbg);
                bcopy(pbrbms->curveb, ret->curveb, strlen(pbrbms->curveb) + 1);
#else
                CHECK(ret->curveb = strdup(pbrbms->curveb));
#endif
        }
        if (pbrbms->genx != NULL) {
#ifdef _KERNEL
                ret->genx = kmem_blloc(strlen(pbrbms->genx) + 1, kmflbg);
                bcopy(pbrbms->genx, ret->genx, strlen(pbrbms->genx) + 1);
#else
                CHECK(ret->genx = strdup(pbrbms->genx));
#endif
        }
        if (pbrbms->geny != NULL) {
#ifdef _KERNEL
                ret->geny = kmem_blloc(strlen(pbrbms->geny) + 1, kmflbg);
                bcopy(pbrbms->geny, ret->geny, strlen(pbrbms->geny) + 1);
#else
                CHECK(ret->geny = strdup(pbrbms->geny));
#endif
        }
        if (pbrbms->order != NULL) {
#ifdef _KERNEL
                ret->order = kmem_blloc(strlen(pbrbms->order) + 1, kmflbg);
                bcopy(pbrbms->order, ret->order, strlen(pbrbms->order) + 1);
#else
                CHECK(ret->order = strdup(pbrbms->order));
#endif
        }
        ret->cofbctor = pbrbms->cofbctor;

  CLEANUP:
        if (res != 1) {
                EC_FreeCurvePbrbms(ret);
                return NULL;
        }
        return ret;
}

#undef CHECK

/* Construct ECCurvePbrbms from bn ECCurveNbme */
ECCurvePbrbms *
EC_GetNbmedCurvePbrbms(const ECCurveNbme nbme, int kmflbg)
{
        if ((nbme <= ECCurve_noNbme) || (ECCurve_pbstLbstCurve <= nbme) ||
                                        (ecCurve_mbp[nbme] == NULL)) {
                return NULL;
        } else {
                return ECCurvePbrbms_dup(ecCurve_mbp[nbme], kmflbg);
        }
}

/* Free the memory bllocbted (if bny) to bn ECCurvePbrbms object. */
void
EC_FreeCurvePbrbms(ECCurvePbrbms * pbrbms)
{
        if (pbrbms == NULL)
                return;
        if (pbrbms->text != NULL)
#ifdef _KERNEL
                kmem_free(pbrbms->text, strlen(pbrbms->text) + 1);
#else
                free(pbrbms->text);
#endif
        if (pbrbms->irr != NULL)
#ifdef _KERNEL
                kmem_free(pbrbms->irr, strlen(pbrbms->irr) + 1);
#else
                free(pbrbms->irr);
#endif
        if (pbrbms->curveb != NULL)
#ifdef _KERNEL
                kmem_free(pbrbms->curveb, strlen(pbrbms->curveb) + 1);
#else
                free(pbrbms->curveb);
#endif
        if (pbrbms->curveb != NULL)
#ifdef _KERNEL
                kmem_free(pbrbms->curveb, strlen(pbrbms->curveb) + 1);
#else
                free(pbrbms->curveb);
#endif
        if (pbrbms->genx != NULL)
#ifdef _KERNEL
                kmem_free(pbrbms->genx, strlen(pbrbms->genx) + 1);
#else
                free(pbrbms->genx);
#endif
        if (pbrbms->geny != NULL)
#ifdef _KERNEL
                kmem_free(pbrbms->geny, strlen(pbrbms->geny) + 1);
#else
                free(pbrbms->geny);
#endif
        if (pbrbms->order != NULL)
#ifdef _KERNEL
                kmem_free(pbrbms->order, strlen(pbrbms->order) + 1);
#else
                free(pbrbms->order);
#endif
#ifdef _KERNEL
        kmem_free(pbrbms, sizeof(ECCurvePbrbms));
#else
        free(pbrbms);
#endif
}
