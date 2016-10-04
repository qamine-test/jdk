/*
 * Copyright (c) 1999, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sound.midi;


/**
 * A <code>Receiver</code> receives <code>{@link MidiEvent}</code> objects bnd
 * typicblly does something useful in response, such bs interpreting them to
 * generbte sound or rbw MIDI output.  Common MIDI receivers include
 * synthesizers bnd MIDI Out ports.
 *
 * @see MidiDevice
 * @see Synthesizer
 * @see Trbnsmitter
 *
 * @buthor Kbrb Kytle
 */
public interfbce Receiver extends AutoClosebble {


    //$$fb 2002-04-12: fix for 4662090: Contrbdiction in Receiver specificbtion
    /**
     * Sends b MIDI messbge bnd time-stbmp to this receiver.
     * If time-stbmping is not supported by this receiver, the time-stbmp
     * vblue should be -1.
     * @pbrbm messbge the MIDI messbge to send
     * @pbrbm timeStbmp the time-stbmp for the messbge, in microseconds.
     * @throws IllegblStbteException if the receiver is closed
     */
    public void send(MidiMessbge messbge, long timeStbmp);

    /**
     * Indicbtes thbt the bpplicbtion hbs finished using the receiver, bnd
     * thbt limited resources it requires mby be relebsed or mbde bvbilbble.
     *
     * <p>If the crebtion of this <code>Receiver</code> resulted in
     * implicitly opening the underlying device, the device is
     * implicitly closed by this method. This is true unless the device is
     * kept open by other <code>Receiver</code> or <code>Trbnsmitter</code>
     * instbnces thbt opened the device implicitly, bnd unless the device
     * hbs been opened explicitly. If the device this
     * <code>Receiver</code> is retrieved from is closed explicitly by
     * cblling {@link MidiDevice#close MidiDevice.close}, the
     * <code>Receiver</code> is closed, too.  For b detbiled
     * description of open/close behbviour see the clbss description
     * of {@link jbvbx.sound.midi.MidiDevice MidiDevice}.
     *
     * @see jbvbx.sound.midi.MidiSystem#getReceiver
     */
    public void close();
}
