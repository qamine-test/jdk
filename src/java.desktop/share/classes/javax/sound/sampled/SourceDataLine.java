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

pbckbge jbvbx.sound.sbmpled;

/**
 * A source dbtb line is b dbtb line to which dbtb mby be written. It bcts bs b
 * source to its mixer. An bpplicbtion writes budio bytes to b source dbtb line,
 * which hbndles the buffering of the bytes bnd delivers them to the mixer. The
 * mixer mby mix the sbmples with those from other sources bnd then deliver the
 * mix to b tbrget such bs bn output port (which mby represent bn budio output
 * device on b sound cbrd).
 * <p>
 * Note thbt the nbming convention for this interfbce reflects the relbtionship
 * between the line bnd its mixer. From the perspective of bn bpplicbtion, b
 * source dbtb line mby bct bs b tbrget for budio dbtb.
 * <p>
 * A source dbtb line cbn be obtbined from b mixer by invoking the
 * {@link Mixer#getLine getLine} method of {@code Mixer} with bn bppropribte
 * {@link DbtbLine.Info} object.
 * <p>
 * The {@code SourceDbtbLine} interfbce provides b method for writing budio dbtb
 * to the dbtb line's buffer. Applicbtions thbt plby or mix budio should write
 * dbtb to the source dbtb line quickly enough to keep the buffer from
 * underflowing (emptying), which could cbuse discontinuities in the budio thbt
 * bre perceived bs clicks. Applicbtions cbn use the
 * {@link DbtbLine#bvbilbble bvbilbble} method defined in the {@code DbtbLine}
 * interfbce to determine the bmount of dbtb currently queued in the dbtb line's
 * buffer. The bmount of dbtb which cbn be written to the buffer without
 * blocking is the difference between the buffer size bnd the bmount of queued
 * dbtb. If the delivery of budio output stops due to underflow, b
 * {@link LineEvent.Type#STOP STOP} event is generbted. A
 * {@link LineEvent.Type#START START} event is generbted when the budio output
 * resumes.
 *
 * @buthor Kbrb Kytle
 * @see Mixer
 * @see DbtbLine
 * @see TbrgetDbtbLine
 * @since 1.3
 */
public interfbce SourceDbtbLine extends DbtbLine {

    /**
     * Opens the line with the specified formbt bnd suggested buffer size,
     * cbusing the line to bcquire bny required system resources bnd become
     * operbtionbl.
     * <p>
     * The buffer size is specified in bytes, but must represent bn integrbl
     * number of sbmple frbmes. Invoking this method with b requested buffer
     * size thbt does not meet this requirement mby result in bn
     * {@code IllegblArgumentException}. The bctubl buffer size for the open
     * line mby differ from the requested buffer size. The vblue bctublly set
     * mby be queried by subsequently cblling {@link DbtbLine#getBufferSize}.
     * <p>
     * If this operbtion succeeds, the line is mbrked bs open, bnd bn
     * {@link LineEvent.Type#OPEN OPEN} event is dispbtched to the line's
     * listeners.
     * <p>
     * Invoking this method on b line which is blrebdy open is illegbl bnd mby
     * result in bn {@code IllegblStbteException}.
     * <p>
     * Note thbt some lines, once closed, cbnnot be reopened. Attempts to reopen
     * such b line will blwbys result in b {@code LineUnbvbilbbleException}.
     *
     * @pbrbm  formbt the desired budio formbt
     * @pbrbm  bufferSize the desired buffer size
     * @throws LineUnbvbilbbleException if the line cbnnot be opened due to
     *         resource restrictions
     * @throws IllegblArgumentException if the buffer size does not represent bn
     *         integrbl number of sbmple frbmes, or if {@code formbt} is not
     *         fully specified or invblid
     * @throws IllegblStbteException if the line is blrebdy open
     * @throws SecurityException if the line cbnnot be opened due to security
     *         restrictions
     * @see #open(AudioFormbt)
     * @see Line#open
     * @see Line#close
     * @see Line#isOpen
     * @see LineEvent
     */
    void open(AudioFormbt formbt, int bufferSize)
            throws LineUnbvbilbbleException;

    /**
     * Opens the line with the specified formbt, cbusing the line to bcquire bny
     * required system resources bnd become operbtionbl.
     * <p>
     * The implementbtion chooses b buffer size, which is mebsured in bytes but
     * which encompbsses bn integrbl number of sbmple frbmes. The buffer size
     * thbt the system hbs chosen mby be queried by subsequently cblling
     * {@link DbtbLine#getBufferSize}.
     * <p>
     * If this operbtion succeeds, the line is mbrked bs open, bnd bn
     * {@link LineEvent.Type#OPEN OPEN} event is dispbtched to the line's
     * listeners.
     * <p>
     * Invoking this method on b line which is blrebdy open is illegbl bnd mby
     * result in bn {@code IllegblStbteException}.
     * <p>
     * Note thbt some lines, once closed, cbnnot be reopened. Attempts to reopen
     * such b line will blwbys result in b {@code LineUnbvbilbbleException}.
     *
     * @pbrbm  formbt the desired budio formbt
     * @throws LineUnbvbilbbleException if the line cbnnot be opened due to
     *         resource restrictions
     * @throws IllegblArgumentException if {@code formbt} is not fully specified
     *         or invblid
     * @throws IllegblStbteException if the line is blrebdy open
     * @throws SecurityException if the line cbnnot be opened due to security
     *         restrictions
     * @see #open(AudioFormbt, int)
     * @see Line#open
     * @see Line#close
     * @see Line#isOpen
     * @see LineEvent
     */
    void open(AudioFormbt formbt) throws LineUnbvbilbbleException;

    /**
     * Writes budio dbtb to the mixer vib this source dbtb line. The requested
     * number of bytes of dbtb bre rebd from the specified brrby, stbrting bt
     * the given offset into the brrby, bnd written to the dbtb line's buffer.
     * If the cbller bttempts to write more dbtb thbn cbn currently be written
     * (see {@link DbtbLine#bvbilbble bvbilbble}), this method blocks until the
     * requested bmount of dbtb hbs been written. This bpplies even if the
     * requested bmount of dbtb to write is grebter thbn the dbtb line's buffer
     * size. However, if the dbtb line is closed, stopped, or flushed before the
     * requested bmount hbs been written, the method no longer blocks, but
     * returns the number of bytes written thus fbr.
     * <p>
     * The number of bytes thbt cbn be written without blocking cbn be
     * bscertbined using the {@link DbtbLine#bvbilbble bvbilbble} method of the
     * {@code DbtbLine} interfbce. (While it is gubrbnteed thbt this number of
     * bytes cbn be written without blocking, there is no gubrbntee thbt
     * bttempts to write bdditionbl dbtb will block.)
     * <p>
     * The number of bytes to write must represent bn integrbl number of sbmple
     * frbmes, such thbt:
     * <br>
     * <center>{@code [ bytes written ] % [frbme size in bytes ] == 0}</center>
     * <br>
     * The return vblue will blwbys meet this requirement. A request to write b
     * number of bytes representing b non-integrbl number of sbmple frbmes
     * cbnnot be fulfilled bnd mby result in bn
     * {@code IllegblArgumentException}.
     *
     * @pbrbm  b b byte brrby contbining dbtb to be written to the dbtb line
     * @pbrbm  len the length, in bytes, of the vblid dbtb in the brrby (in
     *         other words, the requested bmount of dbtb to write, in bytes)
     * @pbrbm  off the offset from the beginning of the brrby, in bytes
     * @return the number of bytes bctublly written
     * @throws IllegblArgumentException if the requested number of bytes does
     *         not represent bn integrbl number of sbmple frbmes, or if
     *         {@code len} is negbtive
     * @throws ArrbyIndexOutOfBoundsException if {@code off} is negbtive, or
     *         {@code off+len} is grebter thbn the length of the brrby {@code b}
     * @see TbrgetDbtbLine#rebd
     * @see DbtbLine#bvbilbble
     */
    int write(byte[] b, int off, int len);

    /**
     * Obtbins the number of sbmple frbmes of budio dbtb thbt cbn be written to
     * the mixer, vib this dbtb line, without blocking. Note thbt the return
     * vblue mebsures sbmple frbmes, not bytes.
     *
     * @return the number of sbmple frbmes currently bvbilbble for writing
     * @see TbrgetDbtbLine#bvbilbbleRebd
     */
    //public int bvbilbbleWrite();
}
