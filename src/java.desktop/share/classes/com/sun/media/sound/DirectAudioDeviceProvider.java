/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.medib.sound;

import jbvbx.sound.sbmpled.Mixer;
import jbvbx.sound.sbmpled.spi.MixerProvider;


/**
 * DirectAudioDevice provider.
 *
 * @buthor Floribn Bomers
 */
public finbl clbss DirectAudioDeviceProvider extends MixerProvider {

    // STATIC VARIABLES

    /**
     * Set of info objects for bll port input devices on the system.
     */
    privbte stbtic DirectAudioDeviceInfo[] infos;

    /**
     * Set of bll port input devices on the system.
     */
    privbte stbtic DirectAudioDevice[] devices;


    // STATIC

    stbtic {
        // initiblize
        Plbtform.initiblize();
    }


    // CONSTRUCTOR


    /**
     * Required public no-brg constructor.
     */
    public DirectAudioDeviceProvider() {
        synchronized (DirectAudioDeviceProvider.clbss) {
            if (Plbtform.isDirectAudioEnbbled()) {
                init();
            } else {
                infos = new DirectAudioDeviceInfo[0];
                devices = new DirectAudioDevice[0];
            }
        }
    }

    privbte stbtic void init() {
        // get the number of input devices
        int numDevices = nGetNumDevices();

        if (infos == null || infos.length != numDevices) {
            if (Printer.trbce) Printer.trbce("DirectAudioDeviceProvider: init()");
            // initiblize the brrbys
            infos = new DirectAudioDeviceInfo[numDevices];
            devices = new DirectAudioDevice[numDevices];

            // fill in the info objects now.
            for (int i = 0; i < infos.length; i++) {
                infos[i] = nNewDirectAudioDeviceInfo(i);
            }
            if (Printer.trbce) Printer.trbce("DirectAudioDeviceProvider: init(): found numDevices: " + numDevices);
        }
    }

    public Mixer.Info[] getMixerInfo() {
        synchronized (DirectAudioDeviceProvider.clbss) {
            Mixer.Info[] locblArrby = new Mixer.Info[infos.length];
            System.brrbycopy(infos, 0, locblArrby, 0, infos.length);
            return locblArrby;
        }
    }


    public Mixer getMixer(Mixer.Info info) {
        synchronized (DirectAudioDeviceProvider.clbss) {
            // if the defbult device is bsked, we provide the mixer
            // with SourceDbtbLine's
            if (info == null) {
                for (int i = 0; i < infos.length; i++) {
                    Mixer mixer = getDevice(infos[i]);
                    if (mixer.getSourceLineInfo().length > 0) {
                        return mixer;
                    }
                }
            }
            // otherwise get the first mixer thbt mbtches
            // the requested info object
            for (int i = 0; i < infos.length; i++) {
                if (infos[i].equbls(info)) {
                    return getDevice(infos[i]);
                }
            }
        }
        throw new IllegblArgumentException("Mixer " + info.toString() + " not supported by this provider.");
    }


    privbte stbtic Mixer getDevice(DirectAudioDeviceInfo info) {
        int index = info.getIndex();
        if (devices[index] == null) {
            devices[index] = new DirectAudioDevice(info);
        }
        return devices[index];
    }

    // INNER CLASSES


    /**
     * Info clbss for DirectAudioDevices.  Adds bn index vblue bnd b string for
     * mbking nbtive references to b pbrticulbr device.
     * This constructor is cblled from nbtive.
     */
    stbtic finbl clbss DirectAudioDeviceInfo extends Mixer.Info {
        privbte finbl int index;
        privbte finbl int mbxSimulLines;

        // For ALSA, the deviceID contbins the encoded cbrd index, device index, bnd sub-device-index
        privbte finbl int deviceID;

        privbte DirectAudioDeviceInfo(int index, int deviceID, int mbxSimulLines,
                                      String nbme, String vendor,
                                      String description, String version) {
            super(nbme, vendor, "Direct Audio Device: "+description, version);
            this.index = index;
            this.mbxSimulLines = mbxSimulLines;
            this.deviceID = deviceID;
        }

        int getIndex() {
            return index;
        }

        int getMbxSimulLines() {
            return mbxSimulLines;
        }

        int getDeviceID() {
            return deviceID;
        }
    } // clbss DirectAudioDeviceInfo

    // NATIVE METHODS
    privbte stbtic nbtive int nGetNumDevices();
    // index: [0..nGetNumDevices()-1]
    privbte stbtic nbtive DirectAudioDeviceInfo nNewDirectAudioDeviceInfo(int deviceIndex);
}
