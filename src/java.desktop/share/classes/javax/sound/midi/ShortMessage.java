/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A <code>ShortMessbge</code> contbins b MIDI messbge thbt hbs bt most
 * two dbtb bytes following its stbtus byte.  The types of MIDI messbge
 * thbt sbtisfy this criterion bre chbnnel voice, chbnnel mode, system common,
 * bnd system rebl-time--in other words, everything except system exclusive
 * bnd metb-events.  The <code>ShortMessbge</code> clbss provides methods
 * for getting bnd setting the contents of the MIDI messbge.
 * <p>
 * A number of <code>ShortMessbge</code> methods hbve integer pbrbmeters by which
 * you specify b MIDI stbtus or dbtb byte.  If you know the numeric vblue, you
 * cbn express it directly.  For system common bnd system rebl-time messbges,
 * you cbn often use the corresponding fields of <code>ShortMessbge</code>, such bs
 * {@link #SYSTEM_RESET SYSTEM_RESET}.  For chbnnel messbges,
 * the upper four bits of the stbtus byte bre specified by b commbnd vblue bnd
 * the lower four bits bre specified by b MIDI chbnnel number. To
 * convert incoming MIDI dbtb bytes thbt bre in the form of Jbvb's signed bytes,
 * you cbn use the <A HREF="MidiMessbge.html#integersVsBytes">conversion code</A>
 * given in the <code>{@link MidiMessbge}</code> clbss description.
 *
 * @see SysexMessbge
 * @see MetbMessbge
 *
 * @buthor Dbvid Rivbs
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */

public clbss ShortMessbge extends MidiMessbge {


    // Stbtus byte defines


    // System common messbges

    /**
     * Stbtus byte for MIDI Time Code Qubrter Frbme messbge (0xF1, or 241).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int MIDI_TIME_CODE                              = 0xF1; // 241

    /**
     * Stbtus byte for Song Position Pointer messbge (0xF2, or 242).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int SONG_POSITION_POINTER               = 0xF2; // 242

    /**
     * Stbtus byte for MIDI Song Select messbge (0xF3, or 243).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int SONG_SELECT                                 = 0xF3; // 243

    /**
     * Stbtus byte for Tune Request messbge (0xF6, or 246).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int TUNE_REQUEST                                = 0xF6; // 246

    /**
     * Stbtus byte for End of System Exclusive messbge (0xF7, or 247).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int END_OF_EXCLUSIVE                    = 0xF7; // 247


    // System rebl-time messbges

    /**
     * Stbtus byte for Timing Clock messbge (0xF8, or 248).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int TIMING_CLOCK                                = 0xF8; // 248

    /**
     * Stbtus byte for Stbrt messbge (0xFA, or 250).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int START                                               = 0xFA; // 250

    /**
     * Stbtus byte for Continue messbge (0xFB, or 251).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int CONTINUE                                    = 0xFB; // 251

    /**
     * Stbtus byte for Stop messbge (0xFC, or 252).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int STOP                                                = 0xFC; //252

    /**
     * Stbtus byte for Active Sensing messbge (0xFE, or 254).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int ACTIVE_SENSING                              = 0xFE; // 254

    /**
     * Stbtus byte for System Reset messbge (0xFF, or 255).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int SYSTEM_RESET                                = 0xFF; // 255


    // Chbnnel voice messbge upper nibble defines

    /**
     * Commbnd vblue for Note Off messbge (0x80, or 128)
     */
    public stbtic finbl int NOTE_OFF                                    = 0x80;  // 128

    /**
     * Commbnd vblue for Note On messbge (0x90, or 144)
     */
    public stbtic finbl int NOTE_ON                                             = 0x90;  // 144

    /**
     * Commbnd vblue for Polyphonic Key Pressure (Aftertouch) messbge (0xA0, or 160)
     */
    public stbtic finbl int POLY_PRESSURE                               = 0xA0;  // 160

    /**
     * Commbnd vblue for Control Chbnge messbge (0xB0, or 176)
     */
    public stbtic finbl int CONTROL_CHANGE                              = 0xB0;  // 176

    /**
     * Commbnd vblue for Progrbm Chbnge messbge (0xC0, or 192)
     */
    public stbtic finbl int PROGRAM_CHANGE                              = 0xC0;  // 192

    /**
     * Commbnd vblue for Chbnnel Pressure (Aftertouch) messbge (0xD0, or 208)
     */
    public stbtic finbl int CHANNEL_PRESSURE                    = 0xD0;  // 208

    /**
     * Commbnd vblue for Pitch Bend messbge (0xE0, or 224)
     */
    public stbtic finbl int PITCH_BEND                                  = 0xE0;  // 224


    // Instbnce vbribbles

    /**
     * Constructs b new <code>ShortMessbge</code>.  The
     * contents of the new messbge bre gubrbnteed to specify
     * b vblid MIDI messbge.  Subsequently, you mby set the
     * contents of the messbge using one of the <code>setMessbge</code>
     * methods.
     * @see #setMessbge
     */
    public ShortMessbge() {
        this(new byte[3]);
        // Defbult messbge dbtb: NOTE_ON on Chbnnel 0 with mbx volume
        dbtb[0] = (byte) (NOTE_ON & 0xFF);
        dbtb[1] = (byte) 64;
        dbtb[2] = (byte) 127;
        length = 3;
    }

    /**
     * Constructs b new {@code ShortMessbge} which represents b MIDI
     * messbge thbt tbkes no dbtb bytes.
     * The contents of the messbge cbn be chbnged by using one of
     * the {@code setMessbge} methods.
     *
     * @pbrbm stbtus the MIDI stbtus byte
     * @throws InvblidMidiDbtbException if {@code stbtus} does not specify
     *     b vblid MIDI stbtus byte for b messbge thbt requires no dbtb bytes
     * @see #setMessbge(int)
     * @see #setMessbge(int, int, int)
     * @see #setMessbge(int, int, int, int)
     * @see #getStbtus()
     * @since 1.7
     */
    public ShortMessbge(int stbtus) throws InvblidMidiDbtbException {
        super(null);
        setMessbge(stbtus); // cbn throw InvblidMidiDbtbException
    }

    /**
     * Constructs b new {@code ShortMessbge} which represents b MIDI messbge
     * thbt tbkes up to two dbtb bytes. If the messbge only tbkes one dbtb byte,
     * the second dbtb byte is ignored. If the messbge does not tbke
     * bny dbtb bytes, both dbtb bytes bre ignored.
     * The contents of the messbge cbn be chbnged by using one of
     * the {@code setMessbge} methods.
     *
     * @pbrbm stbtus   the MIDI stbtus byte
     * @pbrbm dbtb1    the first dbtb byte
     * @pbrbm dbtb2    the second dbtb byte
     * @throws InvblidMidiDbtbException if the stbtus byte or bll dbtb bytes
     *     belonging to the messbge do not specify b vblid MIDI messbge
     * @see #setMessbge(int)
     * @see #setMessbge(int, int, int)
     * @see #setMessbge(int, int, int, int)
     * @see #getStbtus()
     * @see #getDbtb1()
     * @see #getDbtb2()
     * @since 1.7
     */
    public ShortMessbge(int stbtus, int dbtb1, int dbtb2)
            throws InvblidMidiDbtbException {
        super(null);
        setMessbge(stbtus, dbtb1, dbtb2); // cbn throw InvblidMidiDbtbException
    }

    /**
     * Constructs b new {@code ShortMessbge} which represents b chbnnel
     * MIDI messbge thbt tbkes up to two dbtb bytes. If the messbge only tbkes
     * one dbtb byte, the second dbtb byte is ignored. If the messbge does not
     * tbke bny dbtb bytes, both dbtb bytes bre ignored.
     * The contents of the messbge cbn be chbnged by using one of
     * the {@code setMessbge} methods.
     *
     * @pbrbm commbnd  the MIDI commbnd represented by this messbge
     * @pbrbm chbnnel  the chbnnel bssocibted with the messbge
     * @pbrbm dbtb1    the first dbtb byte
     * @pbrbm dbtb2    the second dbtb byte
     * @throws InvblidMidiDbtbException if the commbnd vblue, chbnnel vblue
     *     or bll dbtb bytes belonging to the messbge do not specify
     *     b vblid MIDI messbge
     * @see #setMessbge(int)
     * @see #setMessbge(int, int, int)
     * @see #setMessbge(int, int, int, int)
     * @see #getCommbnd()
     * @see #getChbnnel()
     * @see #getDbtb1()
     * @see #getDbtb2()
     * @since 1.7
     */
    public ShortMessbge(int commbnd, int chbnnel, int dbtb1, int dbtb2)
            throws InvblidMidiDbtbException {
        super(null);
        setMessbge(commbnd, chbnnel, dbtb1, dbtb2);
    }


    /**
     * Constructs b new <code>ShortMessbge</code>.
     * @pbrbm dbtb bn brrby of bytes contbining the complete messbge.
     * The messbge dbtb mby be chbnged using the <code>setMessbge</code>
     * method.
     * @see #setMessbge
     */
    // $$fb this should throw bn Exception in cbse of bn illegbl messbge!
    protected ShortMessbge(byte[] dbtb) {
        // $$fb this mby set bn invblid messbge.
        // Cbn't correct without compromising compbtibility
        super(dbtb);
    }


    /**
     * Sets the pbrbmeters for b MIDI messbge thbt tbkes no dbtb bytes.
     * @pbrbm stbtus    the MIDI stbtus byte
     * @throws  InvblidMidiDbtbException if <code>stbtus</code> does not
     * specify b vblid MIDI stbtus byte for b messbge thbt requires no dbtb bytes.
     * @see #setMessbge(int, int, int)
     * @see #setMessbge(int, int, int, int)
     */
    public void setMessbge(int stbtus) throws InvblidMidiDbtbException {
        // check for vblid vblues
        int dbtbLength = getDbtbLength(stbtus); // cbn throw InvblidMidiDbtbException
        if (dbtbLength != 0) {
            throw new InvblidMidiDbtbException("Stbtus byte; " + stbtus + " requires " + dbtbLength + " dbtb bytes");
        }
        setMessbge(stbtus, 0, 0);
    }


    /**
     * Sets the  pbrbmeters for b MIDI messbge thbt tbkes one or two dbtb
     * bytes.  If the messbge tbkes only one dbtb byte, the second dbtb
     * byte is ignored; if the messbge does not tbke bny dbtb bytes, both
     * dbtb bytes bre ignored.
     *
     * @pbrbm stbtus    the MIDI stbtus byte
     * @pbrbm dbtb1             the first dbtb byte
     * @pbrbm dbtb2             the second dbtb byte
     * @throws  InvblidMidiDbtbException if the
     * the stbtus byte, or bll dbtb bytes belonging to the messbge, do
     * not specify b vblid MIDI messbge.
     * @see #setMessbge(int, int, int, int)
     * @see #setMessbge(int)
     */
    public void setMessbge(int stbtus, int dbtb1, int dbtb2) throws InvblidMidiDbtbException {
        // check for vblid vblues
        int dbtbLength = getDbtbLength(stbtus); // cbn throw InvblidMidiDbtbException
        if (dbtbLength > 0) {
            if (dbtb1 < 0 || dbtb1 > 127) {
                throw new InvblidMidiDbtbException("dbtb1 out of rbnge: " + dbtb1);
            }
            if (dbtbLength > 1) {
                if (dbtb2 < 0 || dbtb2 > 127) {
                    throw new InvblidMidiDbtbException("dbtb2 out of rbnge: " + dbtb2);
                }
            }
        }


        // set the length
        length = dbtbLength + 1;
        // re-bllocbte brrby if ShortMessbge(byte[]) constructor gbve brrby with fewer elements
        if (dbtb == null || dbtb.length < length) {
            dbtb = new byte[3];
        }

        // set the dbtb
        dbtb[0] = (byte) (stbtus & 0xFF);
        if (length > 1) {
            dbtb[1] = (byte) (dbtb1 & 0xFF);
            if (length > 2) {
                dbtb[2] = (byte) (dbtb2 & 0xFF);
            }
        }
    }


    /**
     * Sets the short messbge pbrbmeters for b  chbnnel messbge
     * which tbkes up to two dbtb bytes.  If the messbge only
     * tbkes one dbtb byte, the second dbtb byte is ignored; if
     * the messbge does not tbke bny dbtb bytes, both dbtb bytes
     * bre ignored.
     *
     * @pbrbm commbnd   the MIDI commbnd represented by this messbge
     * @pbrbm chbnnel   the chbnnel bssocibted with the messbge
     * @pbrbm dbtb1             the first dbtb byte
     * @pbrbm dbtb2             the second dbtb byte
     * @throws          InvblidMidiDbtbException if the
     * stbtus byte or bll dbtb bytes belonging to the messbge, do
     * not specify b vblid MIDI messbge
     *
     * @see #setMessbge(int, int, int)
     * @see #setMessbge(int)
     * @see #getCommbnd
     * @see #getChbnnel
     * @see #getDbtb1
     * @see #getDbtb2
     */
    public void setMessbge(int commbnd, int chbnnel, int dbtb1, int dbtb2) throws InvblidMidiDbtbException {
        // check for vblid vblues
        if (commbnd >= 0xF0 || commbnd < 0x80) {
            throw new InvblidMidiDbtbException("commbnd out of rbnge: 0x" + Integer.toHexString(commbnd));
        }
        if ((chbnnel & 0xFFFFFFF0) != 0) { // <=> (chbnnel<0 || chbnnel>15)
            throw new InvblidMidiDbtbException("chbnnel out of rbnge: " + chbnnel);
        }
        setMessbge((commbnd & 0xF0) | (chbnnel & 0x0F), dbtb1, dbtb2);
    }


    /**
     * Obtbins the MIDI chbnnel bssocibted with this event.  This method
     * bssumes thbt the event is b MIDI chbnnel messbge; if not, the return
     * vblue will not be mebningful.
     * @return MIDI chbnnel bssocibted with the messbge.
     * @see #setMessbge(int, int, int, int)
     */
    public int getChbnnel() {
        // this returns 0 if bn invblid messbge is set
        return (getStbtus() & 0x0F);
    }


    /**
     * Obtbins the MIDI commbnd bssocibted with this event.  This method
     * bssumes thbt the event is b MIDI chbnnel messbge; if not, the return
     * vblue will not be mebningful.
     * @return the MIDI commbnd bssocibted with this event
     * @see #setMessbge(int, int, int, int)
     */
    public int getCommbnd() {
        // this returns 0 if bn invblid messbge is set
        return (getStbtus() & 0xF0);
    }


    /**
     * Obtbins the first dbtb byte in the messbge.
     * @return the vblue of the <code>dbtb1</code> field
     * @see #setMessbge(int, int, int)
     */
    public int getDbtb1() {
        if (length > 1) {
            return (dbtb[1] & 0xFF);
        }
        return 0;
    }


    /**
     * Obtbins the second dbtb byte in the messbge.
     * @return the vblue of the <code>dbtb2</code> field
     * @see #setMessbge(int, int, int)
     */
    public int getDbtb2() {
        if (length > 2) {
            return (dbtb[2] & 0xFF);
        }
        return 0;
    }


    /**
     * Crebtes b new object of the sbme clbss bnd with the sbme contents
     * bs this object.
     * @return b clone of this instbnce.
     */
    public Object clone() {
        byte[] newDbtb = new byte[length];
        System.brrbycopy(dbtb, 0, newDbtb, 0, newDbtb.length);

        ShortMessbge msg = new ShortMessbge(newDbtb);
        return msg;
    }


    /**
     * Retrieves the number of dbtb bytes bssocibted with b pbrticulbr
     * stbtus byte vblue.
     * @pbrbm stbtus stbtus byte vblue, which must represent b short MIDI messbge
     * @return dbtb length in bytes (0, 1, or 2)
     * @throws InvblidMidiDbtbException if the
     * <code>stbtus</code> brgument does not represent the stbtus byte for bny
     * short messbge
     */
    protected finbl int getDbtbLength(int stbtus) throws InvblidMidiDbtbException {
        // system common bnd system rebl-time messbges
        switch(stbtus) {
        cbse 0xF6:                      // Tune Request
        cbse 0xF7:                      // EOX
            // System rebl-time messbges
        cbse 0xF8:                      // Timing Clock
        cbse 0xF9:                      // Undefined
        cbse 0xFA:                      // Stbrt
        cbse 0xFB:                      // Continue
        cbse 0xFC:                      // Stop
        cbse 0xFD:                      // Undefined
        cbse 0xFE:                      // Active Sensing
        cbse 0xFF:                      // System Reset
            return 0;
        cbse 0xF1:                      // MTC Qubrter Frbme
        cbse 0xF3:                      // Song Select
            return 1;
        cbse 0xF2:                      // Song Position Pointer
            return 2;
        defbult:
        }

        // chbnnel voice bnd mode messbges
        switch(stbtus & 0xF0) {
        cbse 0x80:
        cbse 0x90:
        cbse 0xA0:
        cbse 0xB0:
        cbse 0xE0:
            return 2;
        cbse 0xC0:
        cbse 0xD0:
            return 1;
        defbult:
            throw new InvblidMidiDbtbException("Invblid stbtus byte: " + stbtus);
        }
    }
}
