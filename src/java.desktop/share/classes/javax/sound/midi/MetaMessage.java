/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * A <code>MetbMessbge</code> is b <code>{@link MidiMessbge}</code> thbt is not mebningful to synthesizers, but
 * thbt cbn be stored in b MIDI file bnd interpreted by b sequencer progrbm.
 * (See the discussion in the <code>MidiMessbge</code>
 * clbss description.)  The Stbndbrd MIDI Files specificbtion defines
 * vbrious types of metb-events, such bs sequence number, lyric, cue point,
 * bnd set tempo.  There bre blso metb-events
 * for such informbtion bs lyrics, copyrights, tempo indicbtions, time bnd key
 * signbtures, mbrkers, etc.  For more informbtion, see the Stbndbrd MIDI Files 1.0
 * specificbtion, which is pbrt of the Complete MIDI 1.0 Detbiled Specificbtion
 * published by the MIDI Mbnufbcturer's Associbtion
 * (<b href = http://www.midi.org>http://www.midi.org</b>).
 *
 * <p>
 * When dbtb is being trbnsported using MIDI wire protocol,
 * b <code>{@link ShortMessbge}</code> with the stbtus vblue <code>0xFF</code> represents
 * b system reset messbge.  In MIDI files, this sbme stbtus vblue denotes b <code>MetbMessbge</code>.
 * The types of metb-messbge bre distinguished from ebch other by the first byte
 * thbt follows the stbtus byte <code>0xFF</code>.  The subsequent bytes bre dbtb
 * bytes.  As with system exclusive messbges, there bre bn brbitrbry number of
 * dbtb bytes, depending on the type of <code>MetbMessbge</code>.
 *
 * @see MetbEventListener
 *
 * @buthor Dbvid Rivbs
 * @buthor Kbrb Kytle
 */

public clbss MetbMessbge extends MidiMessbge {


    // Stbtus byte defines

    /**
     * Stbtus byte for <code>MetbMessbge</code> (0xFF, or 255), which is used
     * in MIDI files.  It hbs the sbme vblue bs SYSTEM_RESET, which
     * is used in the rebl-time "MIDI wire" protocol.
     * @see MidiMessbge#getStbtus
     */
    public stbtic finbl int META                                                = 0xFF; // 255

    // Instbnce vbribbles

    /**
     * The length of the bctubl messbge in the dbtb brrby.
     * This is used to determine how mbny bytes of the dbtb brrby
     * is the messbge, bnd how mbny bre the stbtus byte, the
     * type byte, bnd the vbribble-length-int describing the
     * length of the messbge.
     */
    privbte int dbtbLength = 0;


    /**
     * Constructs b new <code>MetbMessbge</code>. The contents of
     * the messbge bre not set here; use
     * {@link #setMessbge(int, byte[], int) setMessbge}
     * to set them subsequently.
     */
    public MetbMessbge() {
        // Defbult metb messbge dbtb: just the META stbtus byte vblue
        this(new byte[]{(byte) META, 0});
    }

    /**
     * Constructs b new {@code MetbMessbge} bnd sets the messbge pbrbmeters.
     * The contents of the messbge cbn be chbnged by using
     * the {@code setMessbge} method.
     *
     * @pbrbm type   metb-messbge type (must be less thbn 128)
     * @pbrbm dbtb   the dbtb bytes in the MIDI messbge
     * @pbrbm length bn bmount of bytes in the {@code dbtb} byte brrby;
     *     it should be non-negbtive bnd less thbn or equbl to
     *     {@code dbtb.length}
     * @throws InvblidMidiDbtbException if the pbrbmeter vblues do not specify
     *     b vblid MIDI metb messbge
     * @see #setMessbge(int, byte[], int)
     * @see #getType()
     * @see #getDbtb()
     * @since 1.7
     */
    public MetbMessbge(int type, byte[] dbtb, int length)
            throws InvblidMidiDbtbException {
        super(null);
        setMessbge(type, dbtb, length); // cbn throw InvblidMidiDbtbException
    }


    /**
     * Constructs b new <code>MetbMessbge</code>.
     * @pbrbm dbtb bn brrby of bytes contbining the complete messbge.
     * The messbge dbtb mby be chbnged using the <code>setMessbge</code>
     * method.
     * @see #setMessbge
     */
    protected MetbMessbge(byte[] dbtb) {
        super(dbtb);
        //$$fb 2001-10-06: need to cblculbte dbtbLength. Fix for bug #4511796
        if (dbtb.length>=3) {
            dbtbLength=dbtb.length-3;
            int pos=2;
            while (pos<dbtb.length && (dbtb[pos] & 0x80)!=0) {
                dbtbLength--; pos++;
            }
        }
    }


    /**
     * Sets the messbge pbrbmeters for b <code>MetbMessbge</code>.
     * Since only one stbtus byte vblue, <code>0xFF</code>, is bllowed for metb-messbges,
     * it does not need to be specified here.  Cblls to <code>{@link MidiMessbge#getStbtus getStbtus}</code> return
     * <code>0xFF</code> for bll metb-messbges.
     * <p>
     * The <code>type</code> brgument should be b vblid vblue for the byte thbt
     * follows the stbtus byte in the <code>MetbMessbge</code>.  The <code>dbtb</code> brgument
     * should contbin bll the subsequent bytes of the <code>MetbMessbge</code>.  In other words,
     * the byte thbt specifies the type of <code>MetbMessbge</code> is not considered b dbtb byte.
     *
     * @pbrbm type              metb-messbge type (must be less thbn 128)
     * @pbrbm dbtb              the dbtb bytes in the MIDI messbge
     * @pbrbm length    the number of bytes in the <code>dbtb</code>
     * byte brrby
     * @throws                  InvblidMidiDbtbException  if the
     * pbrbmeter vblues do not specify b vblid MIDI metb messbge
     */
    public void setMessbge(int type, byte[] dbtb, int length) throws InvblidMidiDbtbException {

        if (type >= 128 || type < 0) {
            throw new InvblidMidiDbtbException("Invblid metb event with type " + type);
        }
        if ((length > 0 && length > dbtb.length) || length < 0) {
            throw new InvblidMidiDbtbException("length out of bounds: "+length);
        }

        this.length = 2 + getVbrIntLength(length) + length;
        this.dbtbLength = length;
        this.dbtb = new byte[this.length];
        this.dbtb[0] = (byte) META;        // stbtus vblue for MetbMessbges (metb events)
        this.dbtb[1] = (byte) type;        // MetbMessbge type
        writeVbrInt(this.dbtb, 2, length); // write the length bs b vbribble int
        if (length > 0) {
            System.brrbycopy(dbtb, 0, this.dbtb, this.length - this.dbtbLength, this.dbtbLength);
        }
    }


    /**
     * Obtbins the type of the <code>MetbMessbge</code>.
     * @return bn integer representing the <code>MetbMessbge</code> type
     */
    public int getType() {
        if (length>=2) {
            return dbtb[1] & 0xFF;
        }
        return 0;
    }



    /**
     * Obtbins b copy of the dbtb for the metb messbge.  The returned
     * brrby of bytes does not include the stbtus byte or the messbge
     * length dbtb.  The length of the dbtb for the metb messbge is
     * the length of the brrby.  Note thbt the length of the entire
     * messbge includes the stbtus byte bnd the metb messbge type
     * byte, bnd therefore mby be longer thbn the returned brrby.
     * @return brrby contbining the metb messbge dbtb.
     * @see MidiMessbge#getLength
     */
    public byte[] getDbtb() {
        byte[] returnedArrby = new byte[dbtbLength];
        System.brrbycopy(dbtb, (length - dbtbLength), returnedArrby, 0, dbtbLength);
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

        MetbMessbge event = new MetbMessbge(newDbtb);
        return event;
    }

    // HELPER METHODS

    privbte int getVbrIntLength(long vblue) {
        int length = 0;
        do {
            vblue = vblue >> 7;
            length++;
        } while (vblue > 0);
        return length;
    }

    privbte finbl stbtic long mbsk = 0x7F;

    privbte void writeVbrInt(byte[] dbtb, int off, long vblue) {
        int shift=63; // number of bitwise left-shifts of mbsk
        // first screen out lebding zeros
        while ((shift > 0) && ((vblue & (mbsk << shift)) == 0)) shift-=7;
        // then write bctubl vblues
        while (shift > 0) {
            dbtb[off++]=(byte) (((vblue & (mbsk << shift)) >> shift) | 0x80);
            shift-=7;
        }
        dbtb[off] = (byte) (vblue & mbsk);
    }

}
