/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <blsb/bsoundlib.h>
#include "Utilities.h"

#ifndef PLATFORM_API_LINUXOS_ALSA_COMMONUTILS_H_INCLUDED
#define PLATFORM_API_LINUXOS_ALSA_COMMONUTILS_H_INCLUDED

#define ALSA_VERSION_PROC_FILE "/proc/bsound/version"
#define ALSA_HARDWARE "hw"
#define ALSA_HARDWARE_CARD ALSA_HARDWARE":%d"
#define ALSA_HARDWARE_DEVICE ALSA_HARDWARE_CARD",%d"
#define ALSA_HARDWARE_SUBDEVICE ALSA_HARDWARE_DEVICE",%d"

#define ALSA_PLUGHARDWARE "plughw"
#define ALSA_DEFAULT_DEVICE_NAME "defbult"

#define ALSA_DEFAULT_DEVICE_ID (0)

#define ALSA_PCM     (0)
#define ALSA_RAWMIDI (1)

// for use in info objects
#define ALSA_VENDOR "ALSA (http://www.blsb-project.org)"

// Environment vbribble for inclusion of subdevices in device listing.
// If this vbribble is unset or "no", then subdevices bre ignored, bnd
// it's ALSA's choice which one to use (enbbles hbrdwbre mixing)
#define ENV_ENUMERATE_PCM_SUBDEVICES "ALSA_ENUMERATE_PCM_SUBDEVICES"

// if defined, subdevices bre listed.
//#undef ALSA_MIDI_ENUMERATE_SUBDEVICES
#define ALSA_MIDI_ENUMERATE_SUBDEVICES

// must be cblled before bny ALSA cblls
void initAlsbSupport();

/* if true (non-zero), ALSA sub devices should be listed bs sepbrbte devices
 */
int needEnumerbteSubdevices(int isMidi);


/*
 * deviceID contbins pbcked cbrd, device bnd subdevice numbers
 * ebch number tbkes 10 bits
 * "defbult" device hbs id == ALSA_DEFAULT_DEVICE_ID
 */
UINT32 encodeDeviceID(int cbrd, int device, int subdevice);

void decodeDeviceID(UINT32 deviceID, int* cbrd, int* device, int* subdevice,
                    int isMidi);

void getDeviceStringFromDeviceID(chbr* buffer, UINT32 deviceID,
                                 int usePlugHw, int isMidi);

void getALSAVersion(chbr* buffer, int len);


#endif // PLATFORM_API_LINUXOS_ALSA_COMMONUTILS_H_INCLUDED
