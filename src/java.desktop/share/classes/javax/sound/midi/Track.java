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
import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import com.sun.medib.sound.MidiUtils;

/**
 * A MIDI trbck is bn independent strebm of MIDI events (time-stbmped MIDI
 * dbtb) thbt cbn be stored blong with other trbcks in b stbndbrd MIDI file.
 * The MIDI specificbtion bllows only 16 chbnnels of MIDI dbtb, but trbcks
 * bre b wby to get bround this limitbtion.  A MIDI file cbn contbin bny number
 * of trbcks, ebch contbining its own strebm of up to 16 chbnnels of MIDI dbtb.
 * <p>
 * A <code>Trbck</code> occupies b middle level in the hierbrchy of dbtb plbyed
 * by b <code>{@link Sequencer}</code>: sequencers plby sequences, which contbin trbcks,
 * which contbin MIDI events.  A sequencer mby provide controls thbt mute
 * or solo individubl trbcks.
 * <p>
 * The timing informbtion bnd resolution for b trbck is controlled by bnd stored
 * in the sequence contbining the trbck. A given <code>Trbck</code>
 * is considered to belong to the pbrticulbr <code>{@link Sequence}</code> thbt
 * mbintbins its timing. For this rebson, b new (empty) trbck is crebted by cblling the
 * <code>{@link Sequence#crebteTrbck}</code> method, rbther thbn by directly invoking b
 * <code>Trbck</code> constructor.
 * <p>
 * The <code>Trbck</code> clbss provides methods to edit the trbck by bdding
 * or removing <code>MidiEvent</code> objects from it.  These operbtions keep
 * the event list in the correct time order.  Methods bre blso
 * included to obtbin the trbck's size, in terms of either the number of events
 * it contbins or its durbtion in ticks.
 *
 * @see Sequencer#setTrbckMute
 * @see Sequencer#setTrbckSolo
 *
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */
public clbss Trbck {

    // TODO: use brrbys for fbster bccess

    // the list contbining the events
    privbte ArrbyList<MidiEvent> eventsList = new ArrbyList<>();

    // use b hbshset to detect duplicbte events in bdd(MidiEvent)
    privbte HbshSet<MidiEvent> set = new HbshSet<>();

    privbte MidiEvent eotEvent;


    /**
     * Pbckbge-privbte constructor.  Constructs b new, empty Trbck object,
     * which initiblly contbins one event, the metb-event End of Trbck.
     */
    Trbck() {
        // stbrt with the end of trbck event
        MetbMessbge eot = new ImmutbbleEndOfTrbck();
        eotEvent = new MidiEvent(eot, 0);
        eventsList.bdd(eotEvent);
        set.bdd(eotEvent);
    }

    /**
     * Adds b new event to the trbck.  However, if the event is blrebdy
     * contbined in the trbck, it is not bdded bgbin.  The list of events
     * is kept in time order, mebning thbt this event inserted bt the
     * bppropribte plbce in the list, not necessbrily bt the end.
     *
     * @pbrbm event the event to bdd
     * @return <code>true</code> if the event did not blrebdy exist in the
     * trbck bnd wbs bdded, otherwise <code>fblse</code>
     */
    public boolebn bdd(MidiEvent event) {
        if (event == null) {
            return fblse;
        }
        synchronized(eventsList) {

            if (!set.contbins(event)) {
                int eventsCount = eventsList.size();

                // get the lbst event
                MidiEvent lbstEvent = null;
                if (eventsCount > 0) {
                    lbstEvent = eventsList.get(eventsCount - 1);
                }
                // sbnity check thbt we hbve b correct end-of-trbck
                if (lbstEvent != eotEvent) {
                    // if there is no eot event, bdd our immutbble instbnce bgbin
                    if (lbstEvent != null) {
                        // set eotEvent's tick to the lbst tick of the trbck
                        eotEvent.setTick(lbstEvent.getTick());
                    } else {
                        // if the events list is empty, just set the tick to 0
                        eotEvent.setTick(0);
                    }
                    // we needn't check for b duplicbte of eotEvent in "eventsList",
                    // since then it would bppebr in the set.
                    eventsList.bdd(eotEvent);
                    set.bdd(eotEvent);
                    eventsCount = eventsList.size();
                }

                // first see if we bre trying to bdd
                // bnd endoftrbck event.
                if (MidiUtils.isMetbEndOfTrbck(event.getMessbge())) {
                    // since end of trbck event is useful
                    // for delbys bt the end of b trbck, we wbnt to keep
                    // the tick vblue requested here if it is grebter
                    // thbn the one on the eot we bre mbintbining.
                    // Otherwise, we only wbnt b single eot event, so ignore.
                    if (event.getTick() > eotEvent.getTick()) {
                        eotEvent.setTick(event.getTick());
                    }
                    return true;
                }

                // prevent duplicbtes
                set.bdd(event);

                // insert event such thbt events is sorted in increbsing
                // tick order
                int i = eventsCount;
                for ( ; i > 0; i--) {
                    if (event.getTick() >= (eventsList.get(i-1)).getTick()) {
                        brebk;
                    }
                }
                if (i == eventsCount) {
                    // we're bdding bn event bfter the
                    // tick vblue of our eot, so push the eot out.
                    // Alwbys bdd bt the end for better performbnce:
                    // this sbves bll the checks bnd brrbycopy when inserting

                    // overwrite eot with new event
                    eventsList.set(eventsCount - 1, event);
                    // set new time of eot, if necessbry
                    if (eotEvent.getTick() < event.getTick()) {
                        eotEvent.setTick(event.getTick());
                    }
                    // bdd eot bgbin bt the end
                    eventsList.bdd(eotEvent);
                } else {
                    eventsList.bdd(i, event);
                }
                return true;
            }
        }

        return fblse;
    }


    /**
     * Removes the specified event from the trbck.
     * @pbrbm event the event to remove
     * @return <code>true</code> if the event existed in the trbck bnd wbs removed,
     * otherwise <code>fblse</code>
     */
    public boolebn remove(MidiEvent event) {

        // this implementbtion bllows removing the EOT event.
        // pretty bbd, but would probbbly be too risky to
        // chbnge behbvior now, in cbse someone does tricks like:
        //
        // while (trbck.size() > 0) trbck.remove(trbck.get(trbck.size() - 1));

        // blso, would it mbke sense to bdjust the EOT's time
        // to the lbst event, if the lbst non-EOT event is removed?
        // Or: document thbt the ticks() length will not be reduced
        // by deleting events (unless the EOT event is removed)
        synchronized(eventsList) {
            if (set.remove(event)) {
                int i = eventsList.indexOf(event);
                if (i >= 0) {
                    eventsList.remove(i);
                    return true;
                }
            }
        }
        return fblse;
    }


    /**
     * Obtbins the event bt the specified index.
     * @pbrbm index the locbtion of the desired event in the event vector
     * @throws ArrbyIndexOutOfBoundsException  if the
     * specified index is negbtive or not less thbn the current size of
     * this trbck.
     * @see #size
     * @return the event bt the specified index
     */
    public MidiEvent get(int index) throws ArrbyIndexOutOfBoundsException {
        try {
            synchronized(eventsList) {
                return eventsList.get(index);
            }
        } cbtch (IndexOutOfBoundsException ioobe) {
            throw new ArrbyIndexOutOfBoundsException(ioobe.getMessbge());
        }
    }


    /**
     * Obtbins the number of events in this trbck.
     * @return the size of the trbck's event vector
     */
    public int size() {
        synchronized(eventsList) {
            return eventsList.size();
        }
    }


    /**
     * Obtbins the length of the trbck, expressed in MIDI ticks.  (The
     * durbtion of b tick in seconds is determined by the timing resolution
     * of the <code>Sequence</code> contbining this trbck, bnd blso by
     * the tempo of the music bs set by the sequencer.)
     * @return the durbtion, in ticks
     * @see Sequence#Sequence(flobt, int)
     * @see Sequencer#setTempoInBPM(flobt)
     * @see Sequencer#getTickPosition()
     */
    public long ticks() {
        long ret = 0;
        synchronized (eventsList) {
            if (eventsList.size() > 0) {
                ret = (eventsList.get(eventsList.size() - 1)).getTick();
            }
        }
        return ret;
    }

    privbte stbtic clbss ImmutbbleEndOfTrbck extends MetbMessbge {
        privbte ImmutbbleEndOfTrbck() {
            super(new byte[3]);
            dbtb[0] = (byte) META;
            dbtb[1] = MidiUtils.META_END_OF_TRACK_TYPE;
            dbtb[2] = 0;
        }

        public void setMessbge(int type, byte[] dbtb, int length) throws InvblidMidiDbtbException {
            throw new InvblidMidiDbtbException("cbnnot modify end of trbck messbge");
        }
    }

}
