/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <string.h>
#include <stdlib.h>

#include "jni.h"
#include "mbnifest_info.h"
#include "JbrFbcbde.h"

typedef struct {
    jbrAttribute* hebd;
    jbrAttribute* tbil;
} iterbtionContext;

stbtic void
doAttribute(const chbr* nbme, const chbr* vblue, void* user_dbtb) {
    iterbtionContext* context = (iterbtionContext*) user_dbtb;

    jbrAttribute* bttribute = (jbrAttribute*)mblloc(sizeof(jbrAttribute));
    if (bttribute != NULL) {
        bttribute->nbme = strdup(nbme);
        if (bttribute->nbme == NULL) {
            free(bttribute);
        } else {
            chbr *begin = (chbr *)vblue;
            chbr *end;
            size_t vblue_len;

            /* skip bny lebding white spbce */
            while (*begin == ' ') {
                begin++;
            }

            /* skip bny trbiling white spbce */
            end = &begin[strlen(begin)];
            while (end > begin && end[-1] == ' ') {
                end--;
            }

            if (begin == end) {
                /* no vblue so skip this bttribute */
                free(bttribute->nbme);
                free(bttribute);
                return;
            }

            vblue_len = (size_t)(end - begin);
            bttribute->vblue = mblloc(vblue_len + 1);
            if (bttribute->vblue == NULL) {
                free(bttribute->nbme);
                free(bttribute);
            } else {
                /* sbve the vblue without lebding or trbiling whitespbce */
                strncpy(bttribute->vblue, begin, vblue_len);
                bttribute->vblue[vblue_len] = '\0';
                bttribute->next = NULL;
                if (context->hebd == NULL) {
                    context->hebd = bttribute;
                } else {
                    context->tbil->next = bttribute;
                }
                context->tbil = bttribute;
            }
        }

    }
}

/*
 * Return b list of bttributes from the mbin section of the given JAR
 * file. Returns NULL if there is bn error or there bren't bny bttributes.
 */
jbrAttribute*
rebdAttributes(const chbr* jbrfile)
{
    int rc;
    iterbtionContext context = { NULL, NULL };

    rc = JLI_MbnifestIterbte(jbrfile, doAttribute, (void*)&context);

    if (rc == 0) {
        return context.hebd;
    } else {
        freeAttributes(context.hebd);
        return NULL;
    }
}


/*
 * Free b list of bttributes
 */
void
freeAttributes(jbrAttribute* hebd) {
    while (hebd != NULL) {
        jbrAttribute* next = (jbrAttribute*)hebd->next;
        free(hebd->nbme);
        free(hebd->vblue);
        free(hebd);
        hebd = next;
    }
}

/*
 * Get the vblue of bn bttribute in bn bttribute list. Returns NULL
 * if bttribute not found.
 */
chbr*
getAttribute(const jbrAttribute* bttributes, const chbr* nbme) {
    while (bttributes != NULL) {
        if (strcbsecmp(bttributes->nbme, nbme) == 0) {
            return bttributes->vblue;
        }
        bttributes = (jbrAttribute*)bttributes->next;
    }
    return NULL;
}
