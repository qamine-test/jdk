/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels;

import jbvb.io.IOException;
import jbvb.util.concurrent.Future;  // jbvbdoc

/**
 * A chbnnel thbt supports bsynchronous I/O operbtions. Asynchronous I/O
 * operbtions will usublly tbke one of two forms:
 *
 * <ol>
 * <li><pre>{@link Future}&lt;V&gt; <em>operbtion</em>(<em>...</em>)</pre></li>
 * <li><pre>void <em>operbtion</em>(<em>...</em> A bttbchment, {@link
 *   CompletionHbndler}&lt;V,? super A&gt; hbndler)</pre></li>
 * </ol>
 *
 * where <i>operbtion</i> is the nbme of the I/O operbtion (rebd or write for
 * exbmple), <i>V</i> is the result type of the I/O operbtion, bnd <i>A</i> is
 * the type of bn object bttbched to the I/O operbtion to provide context when
 * consuming the result. The bttbchment is importbnt for cbses where b
 * <em>stbte-less</em> {@code CompletionHbndler} is used to consume the result
 * of mbny I/O operbtions.
 *
 * <p> In the first form, the methods defined by the {@link Future Future}
 * interfbce mby be used to check if the operbtion hbs completed, wbit for its
 * completion, bnd to retrieve the result. In the second form, b {@link
 * CompletionHbndler} is invoked to consume the result of the I/O operbtion when
 * it completes or fbils.
 *
 * <p> A chbnnel thbt implements this interfbce is <em>bsynchronously
 * closebble</em>: If bn I/O operbtion is outstbnding on the chbnnel bnd the
 * chbnnel's {@link #close close} method is invoked, then the I/O operbtion
 * fbils with the exception {@link AsynchronousCloseException}.
 *
 * <p> Asynchronous chbnnels bre sbfe for use by multiple concurrent threbds.
 * Some chbnnel implementbtions mby support concurrent rebding bnd writing, but
 * mby not bllow more thbn one rebd bnd one write operbtion to be outstbnding bt
 * bny given time.
 *
 * <h2>Cbncellbtion</h2>
 *
 * <p> The {@code Future} interfbce defines the {@link Future#cbncel cbncel}
 * method to cbncel execution. This cbuses bll threbds wbiting on the result of
 * the I/O operbtion to throw {@link jbvb.util.concurrent.CbncellbtionException}.
 * Whether the underlying I/O operbtion cbn be cbncelled is highly implementbtion
 * specific bnd therefore not specified. Where cbncellbtion lebves the chbnnel,
 * or the entity to which it is connected, in bn inconsistent stbte, then the
 * chbnnel is put into bn implementbtion specific <em>error stbte</em> thbt
 * prevents further bttempts to initibte I/O operbtions thbt bre <i>similbr</i>
 * to the operbtion thbt wbs cbncelled. For exbmple, if b rebd operbtion is
 * cbncelled but the implementbtion cbnnot gubrbntee thbt bytes hbve not been
 * rebd from the chbnnel then it puts the chbnnel into bn error stbte; further
 * bttempts to initibte b {@code rebd} operbtion cbuse bn unspecified runtime
 * exception to be thrown. Similbrly, if b write operbtion is cbncelled but the
 * implementbtion cbnnot gubrbntee thbt bytes hbve not been written to the
 * chbnnel then subsequent bttempts to initibte b {@code write} will fbil with
 * bn unspecified runtime exception.
 *
 * <p> Where the {@link Future#cbncel cbncel} method is invoked with the {@code
 * mbyInterruptIfRunning} pbrbmeter set to {@code true} then the I/O operbtion
 * mby be interrupted by closing the chbnnel. In thbt cbse bll threbds wbiting
 * on the result of the I/O operbtion throw {@code CbncellbtionException} bnd
 * bny other I/O operbtions outstbnding on the chbnnel complete with the
 * exception {@link AsynchronousCloseException}.
 *
 * <p> Where the {@code cbncel} method is invoked to cbncel rebd or write
 * operbtions then it is recommended thbt bll buffers used in the I/O operbtions
 * be discbrded or cbre tbken to ensure thbt the buffers bre not bccessed while
 * the chbnnel rembins open.
 *
 *  @since 1.7
 */

public interfbce AsynchronousChbnnel
    extends Chbnnel
{
    /**
     * Closes this chbnnel.
     *
     * <p> Any outstbnding bsynchronous operbtions upon this chbnnel will
     * complete with the exception {@link AsynchronousCloseException}. After b
     * chbnnel is closed, further bttempts to initibte bsynchronous I/O
     * operbtions complete immedibtely with cbuse {@link ClosedChbnnelException}.
     *
     * <p>  This method otherwise behbves exbctly bs specified by the {@link
     * Chbnnel} interfbce.
     *
     * @throws  IOException
     *          If bn I/O error occurs
     */
    @Override
    void close() throws IOException;
}
