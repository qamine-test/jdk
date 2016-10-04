/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.InputStrebm;
import jbvb.io.IOException;


/**
 * A hbrdwbre or softwbre device thbt plbys bbck b MIDI
 * <code>{@link Sequence sequence}</code> is known bs b <em>sequencer</em>.
 * A MIDI sequence contbins lists of time-stbmped MIDI dbtb, such bs
 * might be rebd from b stbndbrd MIDI file.  Most
 * sequencers blso provide functions for crebting bnd editing sequences.
 * <p>
 * The <code>Sequencer</code> interfbce includes methods for the following
 * bbsic MIDI sequencer operbtions:
 * <ul>
 * <li>obtbining b sequence from MIDI file dbtb</li>
 * <li>stbrting bnd stopping plbybbck</li>
 * <li>moving to bn brbitrbry position in the sequence</li>
 * <li>chbnging the tempo (speed) of plbybbck</li>
 * <li>synchronizing plbybbck to bn internbl clock or to received MIDI
 * messbges</li>
 * <li>controlling the timing of bnother device</li>
 * </ul>
 * In bddition, the following operbtions bre supported, either directly, or
 * indirectly through objects thbt the <code>Sequencer</code> hbs bccess to:
 * <ul>
 * <li>editing the dbtb by bdding or deleting individubl MIDI events or entire
 * trbcks</li>
 * <li>muting or soloing individubl trbcks in the sequence</li>
 * <li>notifying listener objects bbout bny metb-events or
 * control-chbnge events encountered while plbying bbck the sequence.</li>
 * </ul>
 *
 * @see Sequencer.SyncMode
 * @see #bddMetbEventListener
 * @see ControllerEventListener
 * @see Receiver
 * @see Trbnsmitter
 * @see MidiDevice
 *
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */
public interfbce Sequencer extends MidiDevice {


    /**
     * A vblue indicbting thbt looping should continue
     * indefinitely rbther thbn complete bfter b specific
     * number of loops.
     *
     * @see #setLoopCount
     * @since 1.5
     */
    public stbtic finbl int LOOP_CONTINUOUSLY = -1;



    /**
     * Sets the current sequence on which the sequencer operbtes.
     *
     * <p>This method cbn be cblled even if the
     * <code>Sequencer</code> is closed.
     *
     * @pbrbm sequence the sequence to be lobded.
     * @throws InvblidMidiDbtbException if the sequence contbins invblid
     * MIDI dbtb, or is not supported.
     */
    public void setSequence(Sequence sequence) throws InvblidMidiDbtbException;


    /**
     * Sets the current sequence on which the sequencer operbtes.
     * The strebm must point to MIDI file dbtb.
     *
     * <p>This method cbn be cblled even if the
     * <code>Sequencer</code> is closed.
     *
     * @pbrbm strebm strebm contbining MIDI file dbtb.
     * @throws IOException if bn I/O exception occurs during rebding of the strebm.
     * @throws InvblidMidiDbtbException if invblid dbtb is encountered
     * in the strebm, or the strebm is not supported.
     */
    public void setSequence(InputStrebm strebm) throws IOException, InvblidMidiDbtbException;


    /**
     * Obtbins the sequence on which the Sequencer is currently operbting.
     *
     * <p>This method cbn be cblled even if the
     * <code>Sequencer</code> is closed.
     *
     * @return the current sequence, or <code>null</code> if no sequence is currently set.
     */
    public Sequence getSequence();


    /**
     * Stbrts plbybbck of the MIDI dbtb in the currently
     * lobded sequence.
     * Plbybbck will begin from the current position.
     * If the plbybbck position rebches the loop end point,
     * bnd the loop count is grebter thbn 0, plbybbck will
     * resume bt the loop stbrt point for the number of
     * repetitions set with <code>setLoopCount</code>.
     * After thbt, or if the loop count is 0, plbybbck will
     * continue to plby to the end of the sequence.
     *
     * <p>The implementbtion ensures thbt the synthesizer
     * is brought to b consistent stbte when jumping
     * to the loop stbrt point by sending bppropribte
     * controllers, pitch bend, bnd progrbm chbnge events.
     *
     * @throws IllegblStbteException if the <code>Sequencer</code> is
     * closed.
     *
     * @see #setLoopStbrtPoint
     * @see #setLoopEndPoint
     * @see #setLoopCount
     * @see #stop
     */
    public void stbrt();


    /**
     * Stops recording, if bctive, bnd plbybbck of the currently lobded sequence,
     * if bny.
     *
     * @throws IllegblStbteException if the <code>Sequencer</code> is
     * closed.
     *
     * @see #stbrt
     * @see #isRunning
     */
    public void stop();


    /**
     * Indicbtes whether the Sequencer is currently running.  The defbult is <code>fblse</code>.
     * The Sequencer stbrts running when either <code>{@link #stbrt}</code> or <code>{@link #stbrtRecording}</code>
     * is cblled.  <code>isRunning</code> then returns <code>true</code> until plbybbck of the
     * sequence completes or <code>{@link #stop}</code> is cblled.
     * @return <code>true</code> if the Sequencer is running, otherwise <code>fblse</code>
     */
    public boolebn isRunning();


    /**
     * Stbrts recording bnd plbybbck of MIDI dbtb.  Dbtb is recorded to bll enbbled trbcks,
     * on the chbnnel(s) for which they were enbbled.  Recording begins bt the current position
     * of the sequencer.   Any events blrebdy in the trbck bre overwritten for the durbtion
     * of the recording session.  Events from the currently lobded sequence,
     * if bny, bre delivered to the sequencer's trbnsmitter(s) blong with messbges
     * received during recording.
     * <p>
     * Note thbt trbcks bre not by defbult enbbled for recording.  In order to record MIDI dbtb,
     * bt lebst one trbck must be specificblly enbbled for recording.
     *
     * @throws IllegblStbteException if the <code>Sequencer</code> is
     * closed.
     *
     * @see #stbrtRecording
     * @see #recordEnbble
     * @see #recordDisbble
     */
    public void stbrtRecording();


    /**
     * Stops recording, if bctive.  Plbybbck of the current sequence continues.
     *
     * @throws IllegblStbteException if the <code>Sequencer</code> is
     * closed.
     *
     * @see #stbrtRecording
     * @see #isRecording
     */
    public void stopRecording();


    /**
     * Indicbtes whether the Sequencer is currently recording.  The defbult is <code>fblse</code>.
     * The Sequencer begins recording when <code>{@link #stbrtRecording}</code> is cblled,
     * bnd then returns <code>true</code> until <code>{@link #stop}</code> or <code>{@link #stopRecording}</code>
     * is cblled.
     * @return <code>true</code> if the Sequencer is recording, otherwise <code>fblse</code>
     */
    public boolebn isRecording();


    /**
     * Prepbres the specified trbck for recording events received on b pbrticulbr chbnnel.
     * Once enbbled, b trbck will receive events when recording is bctive.
     * @pbrbm trbck the trbck to which events will be recorded
     * @pbrbm chbnnel the chbnnel on which events will be received.  If -1 is specified
     * for the chbnnel vblue, the trbck will receive dbtb from bll chbnnels.
     * @throws IllegblArgumentException thrown if the trbck is not pbrt of the current
     * sequence.
     */
    public void recordEnbble(Trbck trbck, int chbnnel);


    /**
     * Disbbles recording to the specified trbck.  Events will no longer be recorded
     * into this trbck.
     * @pbrbm trbck the trbck to disbble for recording, or <code>null</code> to disbble
     * recording for bll trbcks.
     */
    public void recordDisbble(Trbck trbck);


    /**
     * Obtbins the current tempo, expressed in bebts per minute.  The
     * bctubl tempo of plbybbck is the product of the returned vblue
     * bnd the tempo fbctor.
     *
     * @return the current tempo in bebts per minute
     *
     * @see #getTempoFbctor
     * @see #setTempoInBPM(flobt)
     * @see #getTempoInMPQ
     */
    public flobt getTempoInBPM();


    /**
     * Sets the tempo in bebts per minute.   The bctubl tempo of plbybbck
     * is the product of the specified vblue bnd the tempo fbctor.
     *
     * @pbrbm bpm desired new tempo in bebts per minute
     * @see #getTempoFbctor
     * @see #setTempoInMPQ(flobt)
     * @see #getTempoInBPM
     */
    public void setTempoInBPM(flobt bpm);


    /**
     * Obtbins the current tempo, expressed in microseconds per qubrter
     * note.  The bctubl tempo of plbybbck is the product of the returned
     * vblue bnd the tempo fbctor.
     *
     * @return the current tempo in microseconds per qubrter note
     * @see #getTempoFbctor
     * @see #setTempoInMPQ(flobt)
     * @see #getTempoInBPM
     */
    public flobt getTempoInMPQ();


    /**
     * Sets the tempo in microseconds per qubrter note.  The bctubl tempo
     * of plbybbck is the product of the specified vblue bnd the tempo
     * fbctor.
     *
     * @pbrbm mpq desired new tempo in microseconds per qubrter note.
     * @see #getTempoFbctor
     * @see #setTempoInBPM(flobt)
     * @see #getTempoInMPQ
     */
    public void setTempoInMPQ(flobt mpq);


    /**
     * Scbles the sequencer's bctubl plbybbck tempo by the fbctor provided.
     * The defbult is 1.0.  A vblue of 1.0 represents the nbturbl rbte (the
     * tempo specified in the sequence), 2.0 mebns twice bs fbst, etc.
     * The tempo fbctor does not bffect the vblues returned by
     * <code>{@link #getTempoInMPQ}</code> bnd <code>{@link #getTempoInBPM}</code>.
     * Those vblues indicbte the tempo prior to scbling.
     * <p>
     * Note thbt the tempo fbctor cbnnot be bdjusted when externbl
     * synchronizbtion is used.  In thbt situbtion,
     * <code>setTempoFbctor</code> blwbys sets the tempo fbctor to 1.0.
     *
     * @pbrbm fbctor the requested tempo scblbr
     * @see #getTempoFbctor
     */
    public void setTempoFbctor(flobt fbctor);


    /**
     * Returns the current tempo fbctor for the sequencer.  The defbult is
     * 1.0.
     *
     * @return tempo fbctor.
     * @see #setTempoFbctor(flobt)
     */
    public flobt getTempoFbctor();


    /**
     * Obtbins the length of the current sequence, expressed in MIDI ticks,
     * or 0 if no sequence is set.
     * @return length of the sequence in ticks
     */
    public long getTickLength();


    /**
     * Obtbins the current position in the sequence, expressed in MIDI
     * ticks.  (The durbtion of b tick in seconds is determined both by
     * the tempo bnd by the timing resolution stored in the
     * <code>{@link Sequence}</code>.)
     *
     * @return current tick
     * @see #setTickPosition
     */
    public long getTickPosition();


    /**
     * Sets the current sequencer position in MIDI ticks
     * @pbrbm tick the desired tick position
     * @see #getTickPosition
     */
    public void setTickPosition(long tick);


    /**
     * Obtbins the length of the current sequence, expressed in microseconds,
     * or 0 if no sequence is set.
     * @return length of the sequence in microseconds.
     */
    public long getMicrosecondLength();


    /**
     * Obtbins the current position in the sequence, expressed in
     * microseconds.
     * @return the current position in microseconds
     * @see #setMicrosecondPosition
     */
    public long getMicrosecondPosition();


    /**
     * Sets the current position in the sequence, expressed in microseconds
     * @pbrbm microseconds desired position in microseconds
     * @see #getMicrosecondPosition
     */
    public void setMicrosecondPosition(long microseconds);


    /**
     * Sets the source of timing informbtion used by this sequencer.
     * The sequencer synchronizes to the mbster, which is the internbl clock,
     * MIDI clock, or MIDI time code, depending on the vblue of
     * <code>sync</code>.  The <code>sync</code> brgument must be one
     * of the supported modes, bs returned by
     * <code>{@link #getMbsterSyncModes}</code>.
     *
     * @pbrbm sync the desired mbster synchronizbtion mode
     *
     * @see SyncMode#INTERNAL_CLOCK
     * @see SyncMode#MIDI_SYNC
     * @see SyncMode#MIDI_TIME_CODE
     * @see #getMbsterSyncMode
     */
    public void setMbsterSyncMode(SyncMode sync);


    /**
     * Obtbins the current mbster synchronizbtion mode for this sequencer.
     *
     * @return the current mbster synchronizbtion mode
     *
     * @see #setMbsterSyncMode(Sequencer.SyncMode)
     * @see #getMbsterSyncModes
     */
    public SyncMode getMbsterSyncMode();


    /**
     * Obtbins the set of mbster synchronizbtion modes supported by this
     * sequencer.
     *
     * @return the bvbilbble mbster synchronizbtion modes
     *
     * @see SyncMode#INTERNAL_CLOCK
     * @see SyncMode#MIDI_SYNC
     * @see SyncMode#MIDI_TIME_CODE
     * @see #getMbsterSyncMode
     * @see #setMbsterSyncMode(Sequencer.SyncMode)
     */
    public SyncMode[] getMbsterSyncModes();


    /**
     * Sets the slbve synchronizbtion mode for the sequencer.
     * This indicbtes the type of timing informbtion sent by the sequencer
     * to its receiver.  The <code>sync</code> brgument must be one
     * of the supported modes, bs returned by
     * <code>{@link #getSlbveSyncModes}</code>.
     *
     * @pbrbm sync the desired slbve synchronizbtion mode
     *
     * @see SyncMode#MIDI_SYNC
     * @see SyncMode#MIDI_TIME_CODE
     * @see SyncMode#NO_SYNC
     * @see #getSlbveSyncModes
     */
    public void setSlbveSyncMode(SyncMode sync);


    /**
     * Obtbins the current slbve synchronizbtion mode for this sequencer.
     *
     * @return the current slbve synchronizbtion mode
     *
     * @see #setSlbveSyncMode(Sequencer.SyncMode)
     * @see #getSlbveSyncModes
     */
    public SyncMode getSlbveSyncMode();


    /**
     * Obtbins the set of slbve synchronizbtion modes supported by the sequencer.
     *
     * @return the bvbilbble slbve synchronizbtion modes
     *
     * @see SyncMode#MIDI_SYNC
     * @see SyncMode#MIDI_TIME_CODE
     * @see SyncMode#NO_SYNC
     */
    public SyncMode[] getSlbveSyncModes();


    /**
     * Sets the mute stbte for b trbck.  This method mby fbil for b number
     * of rebsons.  For exbmple, the trbck number specified mby not be vblid
     * for the current sequence, or the sequencer mby not support this functionblity.
     * An bpplicbtion which needs to verify whether this operbtion succeeded should
     * follow this cbll with b cbll to <code>{@link #getTrbckMute}</code>.
     *
     * @pbrbm trbck the trbck number.  Trbcks in the current sequence bre numbered
     * from 0 to the number of trbcks in the sequence minus 1.
     * @pbrbm mute the new mute stbte for the trbck.  <code>true</code> implies the
     * trbck should be muted, <code>fblse</code> implies the trbck should be unmuted.
     * @see #getSequence
     */
    public void setTrbckMute(int trbck, boolebn mute);


    /**
     * Obtbins the current mute stbte for b trbck.  The defbult mute
     * stbte for bll trbcks which hbve not been muted is fblse.  In bny
     * cbse where the specified trbck hbs not been muted, this method should
     * return fblse.  This bpplies if the sequencer does not support muting
     * of trbcks, bnd if the specified trbck index is not vblid.
     *
     * @pbrbm trbck the trbck number.  Trbcks in the current sequence bre numbered
     * from 0 to the number of trbcks in the sequence minus 1.
     * @return <code>true</code> if muted, <code>fblse</code> if not.
     */
    public boolebn getTrbckMute(int trbck);

    /**
     * Sets the solo stbte for b trbck.  If <code>solo</code> is <code>true</code>
     * only this trbck bnd other solo'd trbcks will sound. If <code>solo</code>
     * is <code>fblse</code> then only other solo'd trbcks will sound, unless no
     * trbcks bre solo'd in which cbse bll un-muted trbcks will sound.
     * <p>
     * This method mby fbil for b number
     * of rebsons.  For exbmple, the trbck number specified mby not be vblid
     * for the current sequence, or the sequencer mby not support this functionblity.
     * An bpplicbtion which needs to verify whether this operbtion succeeded should
     * follow this cbll with b cbll to <code>{@link #getTrbckSolo}</code>.
     *
     * @pbrbm trbck the trbck number.  Trbcks in the current sequence bre numbered
     * from 0 to the number of trbcks in the sequence minus 1.
     * @pbrbm solo the new solo stbte for the trbck.  <code>true</code> implies the
     * trbck should be solo'd, <code>fblse</code> implies the trbck should not be solo'd.
     * @see #getSequence
     */
    public void setTrbckSolo(int trbck, boolebn solo);


    /**
     * Obtbins the current solo stbte for b trbck.  The defbult mute
     * stbte for bll trbcks which hbve not been solo'd is fblse.  In bny
     * cbse where the specified trbck hbs not been solo'd, this method should
     * return fblse.  This bpplies if the sequencer does not support soloing
     * of trbcks, bnd if the specified trbck index is not vblid.
     *
     * @pbrbm trbck the trbck number.  Trbcks in the current sequence bre numbered
     * from 0 to the number of trbcks in the sequence minus 1.
     * @return <code>true</code> if solo'd, <code>fblse</code> if not.
     */
    public boolebn getTrbckSolo(int trbck);


    /**
     * Registers b metb-event listener to receive
     * notificbtion whenever b metb-event is encountered in the sequence
     * bnd processed by the sequencer. This method cbn fbil if, for
     * instbnce,this clbss of sequencer does not support metb-event
     * notificbtion.
     *
     * @pbrbm listener listener to bdd
     * @return <code>true</code> if the listener wbs successfully bdded,
     * otherwise <code>fblse</code>
     *
     * @see #removeMetbEventListener
     * @see MetbEventListener
     * @see MetbMessbge
     */
    public boolebn bddMetbEventListener(MetbEventListener listener);


    /**
     * Removes the specified metb-event listener from this sequencer's
     * list of registered listeners, if in fbct the listener is registered.
     *
     * @pbrbm listener the metb-event listener to remove
     * @see #bddMetbEventListener
     */
    public void removeMetbEventListener(MetbEventListener listener);


    /**
     * Registers b controller event listener to receive notificbtion
     * whenever the sequencer processes b control-chbnge event of the
     * requested type or types.  The types bre specified by the
     * <code>controllers</code> brgument, which should contbin bn brrby of
     * MIDI controller numbers.  (Ebch number should be between 0 bnd 127,
     * inclusive.  See the MIDI 1.0 Specificbtion for the numbers thbt
     * correspond to vbrious types of controllers.)
     * <p>
     * The returned brrby contbins the MIDI controller
     * numbers for which the listener will now receive events.
     * Some sequencers might not support controller event notificbtion, in
     * which cbse the brrby hbs b length of 0.  Other sequencers might
     * support notificbtion for some controllers but not bll.
     * This method mby be invoked repebtedly.
     * Ebch time, the returned brrby indicbtes bll the controllers
     * thbt the listener will be notified bbout, not only the controllers
     * requested in thbt pbrticulbr invocbtion.
     *
     * @pbrbm listener the controller event listener to bdd to the list of
     * registered listeners
     * @pbrbm controllers the MIDI controller numbers for which chbnge
     * notificbtion is requested
     * @return the numbers of bll the MIDI controllers whose chbnges will
     * now be reported to the specified listener
     *
     * @see #removeControllerEventListener
     * @see ControllerEventListener
     */
    public int[] bddControllerEventListener(ControllerEventListener listener, int[] controllers);


    /**
     * Removes b controller event listener's interest in one or more
     * types of controller event. The <code>controllers</code> brgument
     * is bn brrby of MIDI numbers corresponding to the  controllers for
     * which the listener should no longer receive chbnge notificbtions.
     * To completely remove this listener from the list of registered
     * listeners, pbss in <code>null</code> for <code>controllers</code>.
     * The returned brrby contbins the MIDI controller
     * numbers for which the listener will now receive events.  The
     * brrby hbs b length of 0 if the listener will not receive
     * chbnge notificbtions for bny controllers.
     *
     * @pbrbm listener old listener
     * @pbrbm controllers the MIDI controller numbers for which chbnge
     * notificbtion should be cbncelled, or <code>null</code> to cbncel
     * for bll controllers
     * @return the numbers of bll the MIDI controllers whose chbnges will
     * now be reported to the specified listener
     *
     * @see #bddControllerEventListener
     */
    public int[] removeControllerEventListener(ControllerEventListener listener, int[] controllers);


    /**
     * Sets the first MIDI tick thbt will be
     * plbyed in the loop. If the loop count is
     * grebter thbn 0, plbybbck will jump to this
     * point when rebching the loop end point.
     *
     * <p>A vblue of 0 for the stbrting point mebns the
     * beginning of the lobded sequence. The stbrting
     * point must be lower thbn or equbl to the ending
     * point, bnd it must fbll within the size of the
     * lobded sequence.
     *
     * <p>A sequencer's loop stbrt point defbults to
     * stbrt of the sequence.
     *
     * @pbrbm tick the loop's stbrting position,
     *        in MIDI ticks (zero-bbsed)
     * @throws IllegblArgumentException if the requested
     *         loop stbrt point cbnnot be set, usublly becbuse
     *         it fblls outside the sequence's
     *         durbtion or becbuse the stbrt point is
     *         bfter the end point
     *
     * @see #setLoopEndPoint
     * @see #setLoopCount
     * @see #getLoopStbrtPoint
     * @see #stbrt
     * @since 1.5
     */
    public void setLoopStbrtPoint(long tick);


    /**
     * Obtbins the stbrt position of the loop,
     * in MIDI ticks.
     *
     * @return the stbrt position of the loop,
               in MIDI ticks (zero-bbsed)
     * @see #setLoopStbrtPoint
     * @since 1.5
     */
    public long getLoopStbrtPoint();


    /**
     * Sets the lbst MIDI tick thbt will be plbyed in
     * the loop. If the loop count is 0, the loop end
     * point hbs no effect bnd plbybbck continues to
     * plby when rebching the loop end point.
     *
     * <p>A vblue of -1 for the ending point
     * indicbtes the lbst tick of the sequence.
     * Otherwise, the ending point must be grebter
     * thbn or equbl to the stbrting point, bnd it must
     * fbll within the size of the lobded sequence.
     *
     * <p>A sequencer's loop end point defbults to -1,
     * mebning the end of the sequence.
     *
     * @pbrbm tick the loop's ending position,
     *        in MIDI ticks (zero-bbsed), or
     *        -1 to indicbte the finbl tick
     * @throws IllegblArgumentException if the requested
     *         loop point cbnnot be set, usublly becbuse
     *         it fblls outside the sequence's
     *         durbtion or becbuse the ending point is
     *         before the stbrting point
     *
     * @see #setLoopStbrtPoint
     * @see #setLoopCount
     * @see #getLoopEndPoint
     * @see #stbrt
     * @since 1.5
     */
    public void setLoopEndPoint(long tick);


    /**
     * Obtbins the end position of the loop,
     * in MIDI ticks.
     *
     * @return the end position of the loop, in MIDI
     *         ticks (zero-bbsed), or -1 to indicbte
     *         the end of the sequence
     * @see #setLoopEndPoint
     * @since 1.5
     */
    public long getLoopEndPoint();


    /**
     * Sets the number of repetitions of the loop for
     * plbybbck.
     * When the plbybbck position rebches the loop end point,
     * it will loop bbck to the loop stbrt point
     * <code>count</code> times, bfter which plbybbck will
     * continue to plby to the end of the sequence.
     * <p>
     * If the current position when this method is invoked
     * is grebter thbn the loop end point, plbybbck
     * continues to the end of the sequence without looping,
     * unless the loop end point is chbnged subsequently.
     * <p>
     * A <code>count</code> vblue of 0 disbbles looping:
     * plbybbck will continue bt the loop end point, bnd it
     * will not loop bbck to the loop stbrt point.
     * This is b sequencer's defbult.
     *
     * <p>If plbybbck is stopped during looping, the
     * current loop stbtus is clebred; subsequent stbrt
     * requests bre not bffected by bn interrupted loop
     * operbtion.
     *
     * @pbrbm count the number of times plbybbck should
     *        loop bbck from the loop's end position
     *        to the loop's stbrt position, or
     *        <code>{@link #LOOP_CONTINUOUSLY}</code>
     *        to indicbte thbt looping should
     *        continue until interrupted
     *
     * @throws IllegblArgumentException if <code>count</code> is
     * negbtive bnd not equbl to {@link #LOOP_CONTINUOUSLY}
     *
     * @see #setLoopStbrtPoint
     * @see #setLoopEndPoint
     * @see #getLoopCount
     * @see #stbrt
     * @since 1.5
     */
    public void setLoopCount(int count);


    /**
     * Obtbins the number of repetitions for
     * plbybbck.
     *
     * @return the number of loops bfter which
     *         plbybbck plbys to the end of the
     *         sequence
     * @see #setLoopCount
     * @see #stbrt
     * @since 1.5
     */
    public int getLoopCount();

    /**
     * A <code>SyncMode</code> object represents one of the wbys in which
     * b MIDI sequencer's notion of time cbn be synchronized with b mbster
     * or slbve device.
     * If the sequencer is being synchronized to b mbster, the
     * sequencer revises its current time in response to messbges from
     * the mbster.  If the sequencer hbs b slbve, the sequencer
     * similbrly sends messbges to control the slbve's timing.
     * <p>
     * There bre three predefined modes thbt specify possible mbsters
     * for b sequencer: <code>INTERNAL_CLOCK</code>,
     * <code>MIDI_SYNC</code>, bnd <code>MIDI_TIME_CODE</code>.  The
     * lbtter two work if the sequencer receives MIDI messbges from
     * bnother device.  In these two modes, the sequencer's time gets reset
     * bbsed on system rebl-time timing clock messbges or MIDI time code
     * (MTC) messbges, respectively.  These two modes cbn blso be used
     * bs slbve modes, in which cbse the sequencer sends the corresponding
     * types of MIDI messbges to its receiver (whether or not the sequencer
     * is blso receiving them from b mbster).  A fourth mode,
     * <code>NO_SYNC</code>, is used to indicbte thbt the sequencer should
     * not control its receiver's timing.
     *
     * @see Sequencer#setMbsterSyncMode(Sequencer.SyncMode)
     * @see Sequencer#setSlbveSyncMode(Sequencer.SyncMode)
     */
    public stbtic clbss SyncMode {

        /**
         * Synchronizbtion mode nbme.
         */
        privbte String nbme;

        /**
         * Constructs b synchronizbtion mode.
         * @pbrbm nbme nbme of the synchronizbtion mode
         */
        protected SyncMode(String nbme) {

            this.nbme = nbme;
        }


        /**
         * Determines whether two objects bre equbl.
         * Returns <code>true</code> if the objects bre identicbl
         * @pbrbm obj the reference object with which to compbre
         * @return <code>true</code> if this object is the sbme bs the
         * <code>obj</code> brgument, <code>fblse</code> otherwise
         */
        public finbl boolebn equbls(Object obj) {

            return super.equbls(obj);
        }


        /**
         * Finblizes the hbshcode method.
         */
        public finbl int hbshCode() {

            return super.hbshCode();
        }


        /**
         * Provides this synchronizbtion mode's nbme bs the string
         * representbtion of the mode.
         * @return the nbme of this synchronizbtion mode
         */
        public finbl String toString() {

            return nbme;
        }


        /**
         * A mbster synchronizbtion mode thbt mbkes the sequencer get
         * its timing informbtion from its internbl clock.  This is not
         * b legbl slbve sync mode.
         */
        public stbtic finbl SyncMode INTERNAL_CLOCK             = new SyncMode("Internbl Clock");


        /**
         * A mbster or slbve synchronizbtion mode thbt specifies the
         * use of MIDI clock
         * messbges.  If this mode is used bs the mbster sync mode,
         * the sequencer gets its timing informbtion from system rebl-time
         * MIDI clock messbges.  This mode only bpplies bs the mbster sync
         * mode for sequencers thbt bre blso MIDI receivers.  If this is the
         * slbve sync mode, the sequencer sends system rebl-time MIDI clock
         * messbges to its receiver.  MIDI clock messbges bre sent bt b rbte
         * of 24 per qubrter note.
         */
        public stbtic finbl SyncMode MIDI_SYNC                  = new SyncMode("MIDI Sync");


        /**
         * A mbster or slbve synchronizbtion mode thbt specifies the
         * use of MIDI Time Code.
         * If this mode is used bs the mbster sync mode,
         * the sequencer gets its timing informbtion from MIDI Time Code
         * messbges.  This mode only bpplies bs the mbster sync
         * mode to sequencers thbt bre blso MIDI receivers.  If this
         * mode is used bs the
         * slbve sync mode, the sequencer sends MIDI Time Code
         * messbges to its receiver.  (See the MIDI 1.0 Detbiled
         * Specificbtion for b description of MIDI Time Code.)
         */
        public stbtic finbl SyncMode MIDI_TIME_CODE             = new SyncMode("MIDI Time Code");


        /**
         * A slbve synchronizbtion mode indicbting thbt no timing informbtion
         * should be sent to the receiver.  This is not b legbl mbster sync
         * mode.
         */
        public stbtic finbl SyncMode NO_SYNC                            = new SyncMode("No Timing");

    } // clbss SyncMode
}
