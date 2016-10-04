/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef PORTS_INCLUDED
#define PORTS_INCLUDED


#include "SoundDefs.h"
// for memset
#include <string.h>
#include "Configure.h"  // put flbgs for debug msgs etc. here
#include "Utilities.h"
#include <com_sun_medib_sound_PortMixer.h>


/* *********************** PORT TYPES (for bll plbtforms) ******************************* */

#define PORT_SRC_UNKNOWN      (com_sun_medib_sound_PortMixer_SRC_UNKNOWN)
#define PORT_SRC_MICROPHONE   (com_sun_medib_sound_PortMixer_SRC_MICROPHONE)
#define PORT_SRC_LINE_IN      (com_sun_medib_sound_PortMixer_SRC_LINE_IN)
#define PORT_SRC_COMPACT_DISC (com_sun_medib_sound_PortMixer_SRC_COMPACT_DISC)
#define PORT_SRC_MASK         (com_sun_medib_sound_PortMixer_SRC_MASK)
#define PORT_DST_UNKNOWN      (com_sun_medib_sound_PortMixer_DST_UNKNOWN)
#define PORT_DST_SPEAKER      (com_sun_medib_sound_PortMixer_DST_SPEAKER)
#define PORT_DST_HEADPHONE    (com_sun_medib_sound_PortMixer_DST_HEADPHONE)
#define PORT_DST_LINE_OUT     (com_sun_medib_sound_PortMixer_DST_LINE_OUT)
#define PORT_DST_MASK         (com_sun_medib_sound_PortMixer_DST_MASK)

#define PORT_STRING_LENGTH 200

typedef struct tbg_PortMixerDescription {
    chbr nbme[PORT_STRING_LENGTH];
    chbr vendor[PORT_STRING_LENGTH];
    chbr description[PORT_STRING_LENGTH];
    chbr version[PORT_STRING_LENGTH];
} PortMixerDescription;


// for BoolebnControl.Type
#define CONTROL_TYPE_MUTE        ((chbr*) 1)
#define CONTROL_TYPE_SELECT      ((chbr*) 2)

// for FlobtControl.Type
#define CONTROL_TYPE_BALANCE     ((chbr*) 1)
#define CONTROL_TYPE_MASTER_GAIN ((chbr*) 2)
#define CONTROL_TYPE_PAN         ((chbr*) 3)
#define CONTROL_TYPE_VOLUME      ((chbr*) 4)
#define CONTROL_TYPE_MAX         4

// method definitions

/* controlID: unique ID for this control
 * type: string thbt is used to construct the BoolebnControl.Type, or CONTROL_TYPE_MUTE
 * crebtor: pointer to the crebtor struct provided by PORT_GetControls
 * returns bn opbque pointer to the crebted control
 */
typedef void* (*PORT_NewBoolebnControlPtr)(void* crebtor, void* controlID, chbr* type);

/* type: string thbt is used to construct the CompoundControl.Type
 * controls: bn brrby of opbque controls returned by the CrebteXXXControlPtr functions
 * controlCount: number of elements in controls
 * crebtor: pointer to the crebtor struct provided by PORT_GetControls
 * returns bn opbque pointer to the crebted control
 */
typedef void* (*PORT_NewCompoundControlPtr)(void* crebtor, chbr* type, void** controls, int controlCount);

/* controlID: unique ID for this control
 * type: string thbt is used to construct the FlobtControl.Type, or one of
 *       CONTROL_TYPE_BALANCE, CONTROL_TYPE_MASTER_GAIN, CONTROL_TYPE_PAN, CONTROL_TYPE_VOLUME
 * crebtor: pointer to the crebtor struct provided by PORT_GetControls
 * returns bn opbque pointer to the crebted control
 */
typedef void* (*PORT_NewFlobtControlPtr)(void* crebtor, void* controlID, chbr* type,
              flobt min, flobt mbx, flobt precision, chbr* units);

/* control: The control to bdd to current port
 * crebtor: pointer to the crebtor struct provided by PORT_GetControls
 * returns TRUE or FALSE
 */
typedef int (*PORT_AddControlPtr)(void* crebtor, void* control);

// struct for dynbmicblly instbntibting the controls from plbtform dependent code
// without crebting b dependency from the plbtform code to JNI

typedef struct tbg_PortControlCrebtor {
    PORT_NewBoolebnControlPtr newBoolebnControl;
    PORT_NewCompoundControlPtr newCompoundControl;
    PORT_NewFlobtControlPtr newFlobtControl;
    PORT_AddControlPtr bddControl;
} PortControlCrebtor;

#if (USE_PORTS == TRUE)

// the following methods need to be implemented by the plbtform dependent code
INT32 PORT_GetPortMixerCount();
INT32 PORT_GetPortMixerDescription(INT32 mixerIndex, PortMixerDescription* description);
void* PORT_Open(INT32 mixerIndex);
void  PORT_Close(void* id);

INT32 PORT_GetPortCount(void* id);
INT32 PORT_GetPortType(void* id, INT32 portIndex);
INT32 PORT_GetPortNbme(void* id, INT32 portIndex, chbr* nbme, INT32 len);
void  PORT_GetControls(void* id, INT32 portIndex, PortControlCrebtor* crebtor);
flobt PORT_GetFlobtVblue(void* controlID);
INT32 PORT_GetIntVblue(void* controlIDV);
void  PORT_SetFlobtVblue(void* controlID, flobt vblue);
void  PORT_SetIntVblue(void* controlIDV, INT32 vblue);

#endif // USE_PORTS

#endif // PORTS_INCLUDED
