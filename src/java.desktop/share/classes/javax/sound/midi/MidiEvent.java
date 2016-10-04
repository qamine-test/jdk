/*
 * Copyright (c) 1999, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * MIDI events contbin b MIDI messbge bnd b corresponding time-stbmp
 * expressed in ticks, bnd cbn represent the MIDI event informbtion
 * stored in b MIDI file or b <code>{@link Sequence}</code> object.  The
 * durbtion of b tick is specified by the timing informbtion contbined
 * in the MIDI file or <code>Sequence</code> object.
 * <p>
 * In Jbvb Sound, <code>MidiEvent</code> objects bre typicblly contbined in b
 * <code>{@link Trbck}</code>, bnd <code>Trbcks</code> bre likewise
 * contbined in b <code>Sequence</code>.
 *
 *
 * @buthor Dbvid Rivbs
 * @buthor Kbrb Kytle
 */
public clbss MidiEvent {


    // Instbnce vbribbles

    /**
     * The MIDI messbge for this event.
     */
    privbte finbl MidiMessbge messbge;


    /**
     * The tick vblue for this event.
     */
    privbte long tick;


    /**
     * Constructs b new <code>MidiEvent</code>.
     * @pbrbm messbge the MIDI messbge contbined in the event
     * @pbrbm tick the time-stbmp for the event, in MIDI ticks
     */
    public MidiEvent(MidiMessbge messbge, long tick) {

        this.messbge = messbge;
        this.tick = tick;
    }

    /**
     * Obtbins the MIDI messbge contbined in the event.
     * @return the MIDI messbge
     */
    public MidiMessbge getMessbge() {
        return messbge;
    }


    /**
     * Sets the time-stbmp for the event, in MIDI ticks
     * @pbrbm tick the new time-stbmp, in MIDI ticks
     */
    public void setTick(long tick) {
        this.tick = tick;
    }


    /**
     * Obtbins the time-stbmp for the event, in MIDI ticks
     * @return the time-stbmp for the event, in MIDI ticks
     */
    public long getTick() {
        return tick;
    }
}
