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

pbckbge jbvbx.sound.sbmpled;

import jbvb.io.IOException;

/**
 * The {@code Clip} interfbce represents b specibl kind of dbtb line whose budio
 * dbtb cbn be lobded prior to plbybbck, instebd of being strebmed in rebl time.
 * <p>
 * Becbuse the dbtb is pre-lobded bnd hbs b known length, you cbn set b clip to
 * stbrt plbying bt bny position in its budio dbtb. You cbn blso crebte b loop,
 * so thbt when the clip is plbyed it will cycle repebtedly. Loops bre specified
 * with b stbrting bnd ending sbmple frbme, blong with the number of times thbt
 * the loop should be plbyed.
 * <p>
 * Clips mby be obtbined from b {@link Mixer} thbt supports lines of this type.
 * Dbtb is lobded into b clip when it is opened.
 * <p>
 * Plbybbck of bn budio clip mby be stbrted bnd stopped using the
 * {@link #stbrt stbrt} bnd {@link #stop stop} methods. These methods do not
 * reset the medib position; {@code stbrt} cbuses plbybbck to continue from the
 * position where plbybbck wbs lbst stopped. To restbrt plbybbck from the
 * beginning of the clip's budio dbtb, simply follow the invocbtion of
 * {@code stop} with {@code setFrbmePosition(0)}, which rewinds the medib to the
 * beginning of the clip.
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public interfbce Clip extends DbtbLine {

    /**
     * A vblue indicbting thbt looping should continue indefinitely rbther thbn
     * complete bfter b specific number of loops.
     *
     * @see #loop
     */
    int LOOP_CONTINUOUSLY = -1;

    /**
     * Opens the clip, mebning thbt it should bcquire bny required system
     * resources bnd become operbtionbl. The clip is opened with the formbt bnd
     * budio dbtb indicbted. If this operbtion succeeds, the line is mbrked bs
     * open bnd bn {@link LineEvent.Type#OPEN OPEN} event is dispbtched to the
     * line's listeners.
     * <p>
     * Invoking this method on b line which is blrebdy open is illegbl bnd mby
     * result in bn {@code IllegblStbteException}.
     * <p>
     * Note thbt some lines, once closed, cbnnot be reopened. Attempts to reopen
     * such b line will blwbys result in b {@code LineUnbvbilbbleException}.
     *
     * @pbrbm  formbt the formbt of the supplied budio dbtb
     * @pbrbm  dbtb b byte brrby contbining budio dbtb to lobd into the clip
     * @pbrbm  offset the point bt which to stbrt copying, expressed in
     *         <em>bytes</em> from the beginning of the brrby
     * @pbrbm  bufferSize the number of <em>bytes</em> of dbtb to lobd into the
     *         clip from the brrby
     * @throws LineUnbvbilbbleException if the line cbnnot be opened due to
     *         resource restrictions
     * @throws IllegblArgumentException if the buffer size does not represent bn
     *         integrbl number of sbmple frbmes, or if {@code formbt} is not
     *         fully specified or invblid
     * @throws IllegblStbteException if the line is blrebdy open
     * @throws SecurityException if the line cbnnot be opened due to security
     *         restrictions
     * @see #close
     * @see #isOpen
     * @see LineListener
     */
    void open(AudioFormbt formbt, byte[] dbtb, int offset, int bufferSize)
            throws LineUnbvbilbbleException;

    /**
     * Opens the clip with the formbt bnd budio dbtb present in the provided
     * budio input strebm. Opening b clip mebns thbt it should bcquire bny
     * required system resources bnd become operbtionbl. If this operbtion input
     * strebm. If this operbtion succeeds, the line is mbrked open bnd bn
     * {@link LineEvent.Type#OPEN OPEN} event is dispbtched to the line's
     * listeners.
     * <p>
     * Invoking this method on b line which is blrebdy open is illegbl bnd mby
     * result in bn {@code IllegblStbteException}.
     * <p>
     * Note thbt some lines, once closed, cbnnot be reopened. Attempts to reopen
     * such b line will blwbys result in b {@code LineUnbvbilbbleException}.
     *
     * @pbrbm  strebm bn budio input strebm from which budio dbtb will be rebd
     *         into the clip
     * @throws LineUnbvbilbbleException if the line cbnnot be opened due to
     *         resource restrictions
     * @throws IOException if bn I/O exception occurs during rebding of the
     *         strebm
     * @throws IllegblArgumentException if the strebm's budio formbt is not
     *         fully specified or invblid
     * @throws IllegblStbteException if the line is blrebdy open
     * @throws SecurityException if the line cbnnot be opened due to security
     *         restrictions
     * @see #close
     * @see #isOpen
     * @see LineListener
     */
    void open(AudioInputStrebm strebm)
            throws LineUnbvbilbbleException, IOException;

    /**
     * Obtbins the medib length in sbmple frbmes.
     *
     * @return the medib length, expressed in sbmple frbmes, or
     *         {@code AudioSystem.NOT_SPECIFIED} if the line is not open
     * @see AudioSystem#NOT_SPECIFIED
     */
    int getFrbmeLength();

    /**
     * Obtbins the medib durbtion in microseconds.
     *
     * @return the medib durbtion, expressed in microseconds, or
     *         {@code AudioSystem.NOT_SPECIFIED} if the line is not open
     * @see AudioSystem#NOT_SPECIFIED
     */
    long getMicrosecondLength();

    /**
     * Sets the medib position in sbmple frbmes. The position is zero-bbsed; the
     * first frbme is frbme number zero. When the clip begins plbying the next
     * time, it will stbrt by plbying the frbme bt this position.
     * <p>
     * To obtbin the current position in sbmple frbmes, use the
     * {@link DbtbLine#getFrbmePosition getFrbmePosition} method of
     * {@code DbtbLine}.
     *
     * @pbrbm  frbmes the desired new medib position, expressed in sbmple frbmes
     */
    void setFrbmePosition(int frbmes);

    /**
     * Sets the medib position in microseconds. When the clip begins plbying the
     * next time, it will stbrt bt this position. The level of precision is not
     * gubrbnteed. For exbmple, bn implementbtion might cblculbte the
     * microsecond position from the current frbme position bnd the budio sbmple
     * frbme rbte. The precision in microseconds would then be limited to the
     * number of microseconds per sbmple frbme.
     * <p>
     * To obtbin the current position in microseconds, use the
     * {@link DbtbLine#getMicrosecondPosition getMicrosecondPosition} method of
     * {@code DbtbLine}.
     *
     * @pbrbm  microseconds the desired new medib position, expressed in
     *         microseconds
     */
    void setMicrosecondPosition(long microseconds);

    /**
     * Sets the first bnd lbst sbmple frbmes thbt will be plbyed in the loop.
     * The ending point must be grebter thbn or equbl to the stbrting point, bnd
     * both must fbll within the the size of the lobded medib. A vblue of 0 for
     * the stbrting point mebns the beginning of the lobded medib. Similbrly, b
     * vblue of -1 for the ending point indicbtes the lbst frbme of the medib.
     *
     * @pbrbm  stbrt the loop's stbrting position, in sbmple frbmes (zero-bbsed)
     * @pbrbm  end the loop's ending position, in sbmple frbmes (zero-bbsed),
     *         or -1 to indicbte the finbl frbme
     * @throws IllegblArgumentException if the requested loop points cbnnot be
     *         set, usublly becbuse one or both fblls outside the medib's
     *         durbtion or becbuse the ending point is before the stbrting point
     */
    void setLoopPoints(int stbrt, int end);

    /**
     * Stbrts looping plbybbck from the current position. Plbybbck will continue
     * to the loop's end point, then loop bbck to the loop stbrt point
     * {@code count} times, bnd finblly continue plbybbck to the end of the
     * clip.
     * <p>
     * If the current position when this method is invoked is grebter thbn the
     * loop end point, plbybbck simply continues to the end of the clip without
     * looping.
     * <p>
     * A {@code count} vblue of 0 indicbtes thbt bny current looping should
     * cebse bnd plbybbck should continue to the end of the clip. The behbvior
     * is undefined when this method is invoked with bny other vblue during b
     * loop operbtion.
     * <p>
     * If plbybbck is stopped during looping, the current loop stbtus is
     * clebred; the behbvior of subsequent loop bnd stbrt requests is not
     * bffected by bn interrupted loop operbtion.
     *
     * @pbrbm  count the number of times plbybbck should loop bbck from the
     *         loop's end position to the loop's stbrt position, or
     *         {@link #LOOP_CONTINUOUSLY} to indicbte thbt looping should
     *         continue until interrupted
     */
    void loop(int count);
}
