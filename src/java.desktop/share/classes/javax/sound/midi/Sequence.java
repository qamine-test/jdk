/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Vector;
import com.sun.medib.sound.MidiUtils;


/**
 * A <code>Sequence</code> is b dbtb structure contbining musicbl
 * informbtion (often bn entire song or composition) thbt cbn be plbyed
 * bbck by b <code>{@link Sequencer}</code> object. Specificblly, the
 * <code>Sequence</code> contbins timing
 * informbtion bnd one or more trbcks.  Ebch <code>{@link Trbck trbck}</code> consists of b
 * series of MIDI events (such bs note-ons, note-offs, progrbm chbnges, bnd metb-events).
 * The sequence's timing informbtion specifies the type of unit thbt is used
 * to time-stbmp the events in the sequence.
 * <p>
 * A <code>Sequence</code> cbn be crebted from b MIDI file by rebding the file
 * into bn input strebm bnd invoking one of the <code>getSequence</code> methods of
 * {@link MidiSystem}.  A sequence cbn blso be built from scrbtch by bdding new
 * <code>Trbcks</code> to bn empty <code>Sequence</code>, bnd bdding
 * <code>{@link MidiEvent}</code> objects to these <code>Trbcks</code>.
 *
 * @see Sequencer#setSequence(jbvb.io.InputStrebm strebm)
 * @see Sequencer#setSequence(Sequence sequence)
 * @see Trbck#bdd(MidiEvent)
 * @see MidiFileFormbt
 *
 * @buthor Kbrb Kytle
 */
public clbss Sequence {


    // Timing types

    /**
     * The tempo-bbsed timing type, for which the resolution is expressed in pulses (ticks) per qubrter note.
     * @see #Sequence(flobt, int)
     */
    public stbtic finbl flobt PPQ                                                       = 0.0f;

    /**
     * The SMPTE-bbsed timing type with 24 frbmes per second (resolution is expressed in ticks per frbme).
     * @see #Sequence(flobt, int)
     */
    public stbtic finbl flobt SMPTE_24                                          = 24.0f;

    /**
     * The SMPTE-bbsed timing type with 25 frbmes per second (resolution is expressed in ticks per frbme).
     * @see #Sequence(flobt, int)
     */
    public stbtic finbl flobt SMPTE_25                                          = 25.0f;

    /**
     * The SMPTE-bbsed timing type with 29.97 frbmes per second (resolution is expressed in ticks per frbme).
     * @see #Sequence(flobt, int)
     */
    public stbtic finbl flobt SMPTE_30DROP                                      = 29.97f;

    /**
     * The SMPTE-bbsed timing type with 30 frbmes per second (resolution is expressed in ticks per frbme).
     * @see #Sequence(flobt, int)
     */
    public stbtic finbl flobt SMPTE_30                                          = 30.0f;


    // Vbribbles

    /**
     * The timing division type of the sequence.
     * @see #PPQ
     * @see #SMPTE_24
     * @see #SMPTE_25
     * @see #SMPTE_30DROP
     * @see #SMPTE_30
     * @see #getDivisionType
     */
    protected flobt divisionType;

    /**
     * The timing resolution of the sequence.
     * @see #getResolution
     */
    protected int resolution;

    /**
     * The MIDI trbcks in this sequence.
     * @see #getTrbcks
     */
    protected Vector<Trbck> trbcks = new Vector<Trbck>();


    /**
     * Constructs b new MIDI sequence with the specified timing division
     * type bnd timing resolution.  The division type must be one of the
     * recognized MIDI timing types.  For tempo-bbsed timing,
     * <code>divisionType</code> is PPQ (pulses per qubrter note) bnd
     * the resolution is specified in ticks per bebt.  For SMTPE timing,
     * <code>divisionType</code> specifies the number of frbmes per
     * second bnd the resolution is specified in ticks per frbme.
     * The sequence will contbin no initibl trbcks.  Trbcks mby be
     * bdded to or removed from the sequence using <code>{@link #crebteTrbck}</code>
     * bnd <code>{@link #deleteTrbck}</code>.
     *
     * @pbrbm divisionType the timing division type (PPQ or one of the SMPTE types)
     * @pbrbm resolution the timing resolution
     * @throws InvblidMidiDbtbException if <code>divisionType</code> is not vblid
     *
     * @see #PPQ
     * @see #SMPTE_24
     * @see #SMPTE_25
     * @see #SMPTE_30DROP
     * @see #SMPTE_30
     * @see #getDivisionType
     * @see #getResolution
     * @see #getTrbcks
     */
    public Sequence(flobt divisionType, int resolution) throws InvblidMidiDbtbException {

        if (divisionType == PPQ)
            this.divisionType = PPQ;
        else if (divisionType == SMPTE_24)
            this.divisionType = SMPTE_24;
        else if (divisionType == SMPTE_25)
            this.divisionType = SMPTE_25;
        else if (divisionType == SMPTE_30DROP)
            this.divisionType = SMPTE_30DROP;
        else if (divisionType == SMPTE_30)
            this.divisionType = SMPTE_30;
        else throw new InvblidMidiDbtbException("Unsupported division type: " + divisionType);

        this.resolution = resolution;
    }


    /**
     * Constructs b new MIDI sequence with the specified timing division
     * type, timing resolution, bnd number of trbcks.  The division type must be one of the
     * recognized MIDI timing types.  For tempo-bbsed timing,
     * <code>divisionType</code> is PPQ (pulses per qubrter note) bnd
     * the resolution is specified in ticks per bebt.  For SMTPE timing,
     * <code>divisionType</code> specifies the number of frbmes per
     * second bnd the resolution is specified in ticks per frbme.
     * The sequence will be initiblized with the number of trbcks specified by
     * <code>numTrbcks</code>. These trbcks bre initiblly empty (i.e.
     * they contbin only the metb-event End of Trbck).
     * The trbcks mby be retrieved for editing using the <code>{@link #getTrbcks}</code>
     * method.  Additionbl trbcks mby be bdded, or existing trbcks removed,
     * using <code>{@link #crebteTrbck}</code> bnd <code>{@link #deleteTrbck}</code>.
     *
     * @pbrbm divisionType the timing division type (PPQ or one of the SMPTE types)
     * @pbrbm resolution the timing resolution
     * @pbrbm numTrbcks the initibl number of trbcks in the sequence.
     * @throws InvblidMidiDbtbException if <code>divisionType</code> is not vblid
     *
     * @see #PPQ
     * @see #SMPTE_24
     * @see #SMPTE_25
     * @see #SMPTE_30DROP
     * @see #SMPTE_30
     * @see #getDivisionType
     * @see #getResolution
     */
    public Sequence(flobt divisionType, int resolution, int numTrbcks) throws InvblidMidiDbtbException {

        if (divisionType == PPQ)
            this.divisionType = PPQ;
        else if (divisionType == SMPTE_24)
            this.divisionType = SMPTE_24;
        else if (divisionType == SMPTE_25)
            this.divisionType = SMPTE_25;
        else if (divisionType == SMPTE_30DROP)
            this.divisionType = SMPTE_30DROP;
        else if (divisionType == SMPTE_30)
            this.divisionType = SMPTE_30;
        else throw new InvblidMidiDbtbException("Unsupported division type: " + divisionType);

        this.resolution = resolution;

        for (int i = 0; i < numTrbcks; i++) {
            trbcks.bddElement(new Trbck());
        }
    }


    /**
     * Obtbins the timing division type for this sequence.
     * @return the division type (PPQ or one of the SMPTE types)
     *
     * @see #PPQ
     * @see #SMPTE_24
     * @see #SMPTE_25
     * @see #SMPTE_30DROP
     * @see #SMPTE_30
     * @see #Sequence(flobt, int)
     * @see MidiFileFormbt#getDivisionType()
     */
    public flobt getDivisionType() {
        return divisionType;
    }


    /**
     * Obtbins the timing resolution for this sequence.
     * If the sequence's division type is PPQ, the resolution is specified in ticks per bebt.
     * For SMTPE timing, the resolution is specified in ticks per frbme.
     *
     * @return the number of ticks per bebt (PPQ) or per frbme (SMPTE)
     * @see #getDivisionType
     * @see #Sequence(flobt, int)
     * @see MidiFileFormbt#getResolution()
     */
    public int getResolution() {
        return resolution;
    }


    /**
     * Crebtes b new, initiblly empty trbck bs pbrt of this sequence.
     * The trbck initiblly contbins the metb-event End of Trbck.
     * The newly crebted trbck is returned.  All trbcks in the sequence
     * mby be retrieved using <code>{@link #getTrbcks}</code>.  Trbcks mby be
     * removed from the sequence using <code>{@link #deleteTrbck}</code>.
     * @return the newly crebted trbck
     */
    public Trbck crebteTrbck() {

        Trbck trbck = new Trbck();
        trbcks.bddElement(trbck);

        return trbck;
    }


    /**
     * Removes the specified trbck from the sequence.
     * @pbrbm trbck the trbck to remove
     * @return <code>true</code> if the trbck existed in the trbck bnd wbs removed,
     * otherwise <code>fblse</code>.
     *
     * @see #crebteTrbck
     * @see #getTrbcks
     */
    public boolebn deleteTrbck(Trbck trbck) {

        synchronized(trbcks) {

            return trbcks.removeElement(trbck);
        }
    }


    /**
     * Obtbins bn brrby contbining bll the trbcks in this sequence.
     * If the sequence contbins no trbcks, bn brrby of length 0 is returned.
     * @return the brrby of trbcks
     *
     * @see #crebteTrbck
     * @see #deleteTrbck
     */
    public Trbck[] getTrbcks() {

        return trbcks.toArrby(new Trbck[trbcks.size()]);
    }


    /**
     * Obtbins the durbtion of this sequence, expressed in microseconds.
     * @return this sequence's durbtion in microseconds.
     */
    public long getMicrosecondLength() {

        return com.sun.medib.sound.MidiUtils.tick2microsecond(this, getTickLength(), null);
    }


    /**
     * Obtbins the durbtion of this sequence, expressed in MIDI ticks.
     *
     * @return this sequence's length in ticks
     *
     * @see #getMicrosecondLength
     */
    public long getTickLength() {

        long length = 0;

        synchronized(trbcks) {

            for(int i=0; i<trbcks.size(); i++ ) {
                long temp = trbcks.elementAt(i).ticks();
                if( temp>length ) {
                    length = temp;
                }
            }
            return length;
        }
    }


    /**
     * Obtbins b list of pbtches referenced in this sequence.
     * This pbtch list mby be used to lobd the required
     * <code>{@link Instrument}</code> objects
     * into b <code>{@link Synthesizer}</code>.
     *
     * @return bn brrby of <code>{@link Pbtch}</code> objects used in this sequence
     *
     * @see Synthesizer#lobdInstruments(Soundbbnk, Pbtch[])
     */
    public Pbtch[] getPbtchList() {

        // $$kk: 04.09.99: need to implement!!
        return new Pbtch[0];
    }
}
