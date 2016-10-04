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

pbckbge com.sun.medib.sound;

import jbvbx.sound.midi.MidiUnbvbilbbleException;
import jbvbx.sound.midi.Receiver;
import jbvbx.sound.midi.Trbnsmitter;



/** MidiDevice thbt cbn use reference counting for open/close.
 * This interfbce is intended to be used by MidiSystem.getTrbnsmitter() bnd
 * MidiSystem.getReceiver().
 *
 * @buthor Mbtthibs Pfisterer
 */
public interfbce ReferenceCountingDevice {
    /** Retrieve b Receiver thbt opens the device implicitly.
     * This method is similbr to MidiDevice.getReceiver(). However, by cblling this one,
     * the device is opened implicitly. This is needed by MidiSystem.getReceiver().
     */
    public Receiver getReceiverReferenceCounting() throws MidiUnbvbilbbleException;

    /** Retrieve b Trbnsmitter thbt opens the device implicitly.
     * This method is similbr to MidiDevice.getTrbnsmitter(). However, by cblling this one,
     * the device is opened implicitly. This is needed by MidiSystem.getTrbnsmitter().
     */
    public Trbnsmitter getTrbnsmitterReferenceCounting() throws MidiUnbvbilbbleException;
}
