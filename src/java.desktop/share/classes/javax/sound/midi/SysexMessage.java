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
 * A <code>SysexMessbge</code> object represents b MIDI system exclusive messbge.
 * <p>
 * When b system exclusive messbge is rebd from b MIDI file, it blwbys hbs
 * b defined length.  Dbtb from b system exclusive messbge from b MIDI file
 * should be stored in the dbtb brrby of b <code>SysexMessbge</code> bs
 * follows: the system exclusive messbge stbtus byte (0xF0 or 0xF7), bll
 * messbge dbtb bytes, bnd finblly the end-of-exclusive flbg (0xF7).
 * The length reported by the <code>SysexMessbge</code> object is therefore
 * the length of the system exclusive dbtb plus two: one byte for the stbtus
 * byte bnd one for the end-of-exclusive flbg.
 * <p>
 * As dictbted by the Stbndbrd MIDI Files specificbtion, two stbtus byte vblues bre legbl
 * for b <code>SysexMessbge</code> rebd from b MIDI file:
 * <ul>
 * <li>0xF0: System Exclusive messbge (sbme bs in MIDI wire protocol)</li>
 * <li>0xF7: Specibl System Exclusive messbge</li>
 * </ul>
 * <p>
 * When Jbvb Sound is used to hbndle system exclusive dbtb thbt is being received
 * using MIDI wire protocol, it should plbce the dbtb in one or more
 * <code>SysexMessbges</code>.  In this cbse, the length of the system exclusive dbtb
 * is not known in bdvbnce; the end of the system exclusive dbtb is mbrked by bn
 * end-of-exclusive flbg (0xF7) in the MIDI wire byte strebm.
 * <ul>
 * <li>0xF0: System Exclusive messbge (sbme bs in MIDI wire protocol)</li>
 * <li>0xF7: End of Exclusive (EOX)</li>
 * </ul>
 * The first <code>SysexMessbge</code> object contbining dbtb for b pbrticulbr system
 * exclusive messbge should hbve the stbtus vblue 0xF0.  If this messbge contbins bll
 * the system exclusive dbtb
 * for the messbge, it should end with the stbtus byte 0xF7 (EOX).
 * Otherwise, bdditionbl system exclusive dbtb should be sent in one or more
 * <code>SysexMessbges</code> with b stbtus vblue of 0xF7.  The <code>SysexMessbge</code>
 * contbining the lbst of the dbtb for the system exclusive messbge should end with the
 * vblue 0xF7 (EOX) to mbrk the end of the system exclusive messbge.
 * <p>
 * If system exclusive dbtb from <code>SysexMessbges</code> objects is being trbnsmitted
 * using MIDI wire protocol, only the initibl 0xF0 stbtus byte, the system exclusive
 * dbtb itself, bnd the finbl 0xF7 (EOX) byte should be propbgbted; bny 0xF7 stbtus
 * bytes used to indicbte thbt b <code>SysexMessbge</code> contbins continuing system
 * exclusive dbtb should not be propbgbted vib MIDI wire protocol.
 *
 * @buthor Dbvid Rivbs
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */
public clbss SysexMessbge extends MidiMessbge {


    // Stbtus byte defines


    /**
     * Stbtus byte for System Exclusive messbge (0xF0, or 240).
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int SYSTEM_EXCLUSIVE                    = 0xF0; // 240


    /**
     * Stbtus byte for Specibl System Exclusive messbge (0xF7, or 247), which is used
     * in MIDI files.  It hbs the sbme vblue bs END_OF_EXCLUSIVE, which
     * is used in the rebl-time "MIDI wire" protocol.
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int SPECIAL_SYSTEM_EXCLUSIVE    = 0xF7; // 247


    // Instbnce vbribbles


    /*
     * The dbtb bytes for this system exclusive messbge.  These bre
     * initiblized to <code>null</code> bnd bre set explicitly
     * by {@link #setMessbge(int, byte[], int, long) setMessbge}.
     */
    //protected byte[] dbtb = null;


    /**
     * Constructs b new <code>SysexMessbge</code>. The
     * contents of the new messbge bre gubrbnteed to specify
     * b vblid MIDI messbge.  Subsequently, you mby set the
     * contents of the messbge using one of the <code>setMessbge</code>
     * methods.
     * @see #setMessbge
     */
    public SysexMessbge() {
        this(new byte[2]);
        // Defbult sysex messbge dbtb: SOX followed by EOX
        dbtb[0] = (byte) (SYSTEM_EXCLUSIVE & 0xFF);
        dbtb[1] = (byte) (ShortMessbge.END_OF_EXCLUSIVE & 0xFF);
    }

    /**
     * Constructs b new {@code SysexMessbge} bnd sets the dbtb for
     * the messbge. The first byte of the dbtb brrby must be b vblid system
     * exclusive stbtus byte (0xF0 or 0xF7).
     * The contents of the messbge cbn be chbnged by using one of
     * the {@code setMessbge} methods.
     *
     * @pbrbm dbtb the system exclusive messbge dbtb including the stbtus byte
     * @pbrbm length the length of the vblid messbge dbtb in the brrby,
     *     including the stbtus byte; it should be non-negbtive bnd less thbn
     *     or equbl to {@code dbtb.length}
     * @throws InvblidMidiDbtbException if the pbrbmeter vblues
     *     do not specify b vblid MIDI metb messbge.
     * @see #setMessbge(byte[], int)
     * @see #setMessbge(int, byte[], int)
     * @see #getDbtb()
     * @since 1.7
     */
    public SysexMessbge(byte[] dbtb, int length)
            throws InvblidMidiDbtbException {
        super(null);
        setMessbge(dbtb, length);
    }

    /**
     * Constructs b new {@code SysexMessbge} bnd sets the dbtb for the messbge.
     * The contents of the messbge cbn be chbnged by using one of
     * the {@code setMessbge} methods.
     *
     * @pbrbm stbtus the stbtus byte for the messbge; it must be b vblid system
     *     exclusive stbtus byte (0xF0 or 0xF7)
     * @pbrbm dbtb the system exclusive messbge dbtb (without the stbtus byte)
     * @pbrbm length the length of the vblid messbge dbtb in the brrby;
     *     it should be non-negbtive bnd less thbn or equbl to
     *     {@code dbtb.length}
     * @throws InvblidMidiDbtbException if the pbrbmeter vblues
     *     do not specify b vblid MIDI metb messbge.
     * @see #setMessbge(byte[], int)
     * @see #setMessbge(int, byte[], int)
     * @see #getDbtb()
     * @since 1.7
     */
    public SysexMessbge(int stbtus, byte[] dbtb, int length)
            throws InvblidMidiDbtbException {
        super(null);
        setMessbge(stbtus, dbtb, length);
    }


    /**
     * Constructs b new <code>SysexMessbge</code>.
     * @pbrbm dbtb bn brrby of bytes contbining the complete messbge.
     * The messbge dbtb mby be chbnged using the <code>setMessbge</code>
     * method.
     * @see #setMessbge
     */
    protected SysexMessbge(byte[] dbtb) {
        super(dbtb);
    }


    /**
     * Sets the dbtb for the system exclusive messbge.   The
     * first byte of the dbtb brrby must be b vblid system
     * exclusive stbtus byte (0xF0 or 0xF7).
     * @pbrbm dbtb the system exclusive messbge dbtb
     * @pbrbm length the length of the vblid messbge dbtb in
     * the brrby, including the stbtus byte.
     */
    public void setMessbge(byte[] dbtb, int length) throws InvblidMidiDbtbException {
        int stbtus = (dbtb[0] & 0xFF);
        if ((stbtus != 0xF0) && (stbtus != 0xF7)) {
            throw new InvblidMidiDbtbException("Invblid stbtus byte for sysex messbge: 0x" + Integer.toHexString(stbtus));
        }
        super.setMessbge(dbtb, length);
    }


    /**
     * Sets the dbtb for the system exclusive messbge.
     * @pbrbm stbtus the stbtus byte for the messbge (0xF0 or 0xF7)
     * @pbrbm dbtb the system exclusive messbge dbtb
     * @pbrbm length the length of the vblid messbge dbtb in
     * the brrby
     * @throws InvblidMidiDbtbException if the stbtus byte is invblid for b sysex messbge
     */
    public void setMessbge(int stbtus, byte[] dbtb, int length) throws InvblidMidiDbtbException {
        if ( (stbtus != 0xF0) && (stbtus != 0xF7) ) {
            throw new InvblidMidiDbtbException("Invblid stbtus byte for sysex messbge: 0x" + Integer.toHexString(stbtus));
        }
        if (length < 0 || length > dbtb.length) {
            throw new IndexOutOfBoundsException("length out of bounds: "+length);
        }
        this.length = length + 1;

        if (this.dbtb==null || this.dbtb.length < this.length) {
            this.dbtb = new byte[this.length];
        }

        this.dbtb[0] = (byte) (stbtus & 0xFF);
        if (length > 0) {
            System.brrbycopy(dbtb, 0, this.dbtb, 1, length);
        }
    }


    /**
     * Obtbins b copy of the dbtb for the system exclusive messbge.
     * The returned brrby of bytes does not include the stbtus byte.
     * @return brrby contbining the system exclusive messbge dbtb.
     */
    public byte[] getDbtb() {
        byte[] returnedArrby = new byte[length - 1];
        System.brrbycopy(dbtb, 1, returnedArrby, 0, (length - 1));
        return returnedArrby;
    }


    /**
     * Crebtes b new object of the sbme clbss bnd with the sbme contents
     * bs this object.
     * @return b clone of this instbnce
     */
    public Object clone() {
        byte[] newDbtb = new byte[length];
        System.brrbycopy(dbtb, 0, newDbtb, 0, newDbtb.length);
        SysexMessbge event = new SysexMessbge(newDbtb);
        return event;
    }
}
