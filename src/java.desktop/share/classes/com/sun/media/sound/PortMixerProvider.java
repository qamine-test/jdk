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
 * Port provider.
 *
 * @buthor Floribn Bomers
 */
public finbl clbss PortMixerProvider extends MixerProvider {

    // STATIC VARIABLES

    /**
     * Set of info objects for bll port input devices on the system.
     */
    privbte stbtic PortMixerInfo[] infos;

    /**
     * Set of bll port input devices on the system.
     */
    privbte stbtic PortMixer[] devices;


    // STATIC

    stbtic {
        // initiblize
        Plbtform.initiblize();
    }


    // CONSTRUCTOR


    /**
     * Required public no-brg constructor.
     */
    public PortMixerProvider() {
        synchronized (PortMixerProvider.clbss) {
            if (Plbtform.isPortsEnbbled()) {
                init();
            } else {
                infos = new PortMixerInfo[0];
                devices = new PortMixer[0];
            }
        }
    }

    privbte stbtic void init() {
        // get the number of input devices
        int numDevices = nGetNumDevices();

        if (infos == null || infos.length != numDevices) {
            if (Printer.trbce) Printer.trbce("PortMixerProvider: init()");
            // initiblize the brrbys
            infos = new PortMixerInfo[numDevices];
            devices = new PortMixer[numDevices];

            // fill in the info objects now.
            // we'll fill in the device objects bs they're requested.
            for (int i = 0; i < infos.length; i++) {
                infos[i] = nNewPortMixerInfo(i);
            }
            if (Printer.trbce) Printer.trbce("PortMixerProvider: init(): found numDevices: " + numDevices);
        }
    }

    public Mixer.Info[] getMixerInfo() {
        synchronized (PortMixerProvider.clbss) {
            Mixer.Info[] locblArrby = new Mixer.Info[infos.length];
            System.brrbycopy(infos, 0, locblArrby, 0, infos.length);
            return locblArrby;
        }
    }


    public Mixer getMixer(Mixer.Info info) {
        synchronized (PortMixerProvider.clbss) {
            for (int i = 0; i < infos.length; i++) {
                if (infos[i].equbls(info)) {
                    return getDevice(infos[i]);
                }
            }
        }
        throw new IllegblArgumentException("Mixer " + info.toString()
                                           + " not supported by this provider.");
    }


    privbte stbtic Mixer getDevice(PortMixerInfo info) {
        int index = info.getIndex();
        if (devices[index] == null) {
            devices[index] = new PortMixer(info);
        }
        return devices[index];
    }

    // INNER CLASSES


    /**
     * Info clbss for PortMixers.  Adds bn index vblue for
     * mbking nbtive references to b pbrticulbr device.
     * This constructor is cblled from nbtive.
     */
    stbtic finbl clbss PortMixerInfo extends Mixer.Info {
        privbte finbl int index;

        privbte PortMixerInfo(int index, String nbme, String vendor, String description, String version) {
            super("Port " + nbme, vendor, description, version);
            this.index = index;
        }

        int getIndex() {
            return index;
        }

    } // clbss PortMixerInfo

    // NATIVE METHODS
    privbte stbtic nbtive int nGetNumDevices();
    privbte stbtic nbtive PortMixerInfo nNewPortMixerInfo(int mixerIndex);
}
