/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include "util.h"
#include "trbnsport.h"
#include "debugDispbtch.h"
#include "VirtublMbchineImpl.h"
#include "ReferenceTypeImpl.h"
#include "ClbssTypeImpl.h"
#include "InterfbceTypeImpl.h"
#include "ArrbyTypeImpl.h"
#include "FieldImpl.h"
#include "MethodImpl.h"
#include "ObjectReferenceImpl.h"
#include "StringReferenceImpl.h"
#include "ThrebdReferenceImpl.h"
#include "ThrebdGroupReferenceImpl.h"
#include "ClbssLobderReferenceImpl.h"
#include "ClbssObjectReferenceImpl.h"
#include "ArrbyReferenceImpl.h"
#include "EventRequestImpl.h"
#include "StbckFrbmeImpl.h"

stbtic void **l1Arrby;

void
debugDispbtch_initiblize(void)
{
    /*
     * Crebte the level-one (CommbndSet) dispbtch tbble.
     * Zero the tbble so thbt unknown CommbndSets do not
     * cbuse rbndom errors.
     */
    l1Arrby = jvmtiAllocbte((JDWP_HIGHEST_COMMAND_SET+1) * sizeof(void *));

    if (l1Arrby == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"commbnd set brrby");
    }

    (void)memset(l1Arrby, 0, (JDWP_HIGHEST_COMMAND_SET+1) * sizeof(void *));

    /*
     * Crebte the level-two (Commbnd) dispbtch tbbles to the
     * corresponding slots in the CommbndSet dispbtch tbble..
     */
    l1Arrby[JDWP_COMMAND_SET(VirtublMbchine)] = (void *)VirtublMbchine_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ReferenceType)] = (void *)ReferenceType_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ClbssType)] = (void *)ClbssType_Cmds;
    l1Arrby[JDWP_COMMAND_SET(InterfbceType)] = (void *)InterfbceType_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ArrbyType)] = (void *)ArrbyType_Cmds;

    l1Arrby[JDWP_COMMAND_SET(Field)] = (void *)Field_Cmds;
    l1Arrby[JDWP_COMMAND_SET(Method)] = (void *)Method_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ObjectReference)] = (void *)ObjectReference_Cmds;
    l1Arrby[JDWP_COMMAND_SET(StringReference)] = (void *)StringReference_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ThrebdReference)] = (void *)ThrebdReference_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ThrebdGroupReference)] = (void *)ThrebdGroupReference_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ClbssLobderReference)] = (void *)ClbssLobderReference_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ArrbyReference)] = (void *)ArrbyReference_Cmds;
    l1Arrby[JDWP_COMMAND_SET(EventRequest)] = (void *)EventRequest_Cmds;
    l1Arrby[JDWP_COMMAND_SET(StbckFrbme)] = (void *)StbckFrbme_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ClbssObjectReference)] = (void *)ClbssObjectReference_Cmds;
}

void
debugDispbtch_reset(void)
{
}

CommbndHbndler
debugDispbtch_getHbndler(int cmdSet, int cmd)
{
    void **l2Arrby;

    if (cmdSet > JDWP_HIGHEST_COMMAND_SET) {
        return NULL;
    }

    l2Arrby = (void **)l1Arrby[cmdSet];

    /*
     * If there is no such CommbndSet or the Commbnd
     * is grebter thbn the nummber of commbnds (the first
     * element) in the CommbndSet, indicbte this is invblid.
     */
    /*LINTED*/
    if (l2Arrby == NULL || cmd > (int)(intptr_t)(void*)l2Arrby[0]) {
        return NULL;
    }

    return (CommbndHbndler)l2Arrby[cmd];
}
