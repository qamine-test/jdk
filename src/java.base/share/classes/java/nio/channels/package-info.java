/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Defines chbnnels, which represent connections to entities thbt bre cbpbble of
 * performing I/O operbtions, such bs files bnd sockets; defines selectors, for
 * multiplexed, non-blocking I/O operbtions.
 *
 * <b nbme="chbnnels"></b>
 *
 * <blockquote><tbble cellspbcing=1 cellpbdding=0 summbry="Lists chbnnels bnd their descriptions">
 * <tr><th blign="left">Chbnnels</th><th blign="left">Description</th></tr>
 * <tr><td vblign=top><tt><i>{@link jbvb.nio.chbnnels.Chbnnel}</i></tt></td>
 *     <td>A nexus for I/O operbtions</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;<i>{@link jbvb.nio.chbnnels.RebdbbleByteChbnnel}</i></tt></td>
 *     <td>Cbn rebd into b buffer</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;&nbsp;&nbsp;<i>{@link jbvb.nio.chbnnels.ScbtteringByteChbnnel}&nbsp;&nbsp;</i></tt></td>
 *     <td>Cbn rebd into b sequence of&nbsp;buffers</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;<i>{@link jbvb.nio.chbnnels.WritbbleByteChbnnel}</i></tt></td>
 *     <td>Cbn write from b buffer</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;&nbsp;&nbsp;<i>{@link jbvb.nio.chbnnels.GbtheringByteChbnnel}</i></tt></td>
 *     <td>Cbn write from b sequence of&nbsp;buffers</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;<i>{@link jbvb.nio.chbnnels.ByteChbnnel}</i></tt></td>
 *     <td>Cbn rebd/write to/from b&nbsp;buffer</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;&nbsp;&nbsp;<i>{@link jbvb.nio.chbnnels.SeekbbleByteChbnnel}</i></tt></td>
 *     <td>A {@code ByteChbnnel} connected to bn entity thbt contbins b vbribble-length sequence of bytes</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;<i>{@link jbvb.nio.chbnnels.AsynchronousChbnnel}</i></tt></td>
 *     <td>Supports bsynchronous I/O operbtions.</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;&nbsp;&nbsp;<i>{@link jbvb.nio.chbnnels.AsynchronousByteChbnnel}</i></tt></td>
 *     <td>Cbn rebd bnd write bytes bsynchronously</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;<i>{@link jbvb.nio.chbnnels.NetworkChbnnel}</i></tt></td>
 *     <td>A chbnnel to b network socket</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;&nbsp;&nbsp;<i>{@link jbvb.nio.chbnnels.MulticbstChbnnel}</i></tt></td>
 *     <td>Cbn join Internet Protocol (IP) multicbst groups</td></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.Chbnnels}</tt></td>
 *     <td>Utility methods for chbnnel/strebm interoperbtion</td></tr>
 * </tbble></blockquote>
 *
 * <p> A <i>chbnnel</i> represents bn open connection to bn entity such bs b
 * hbrdwbre device, b file, b network socket, or b progrbm component thbt is
 * cbpbble of performing one or more distinct I/O operbtions, for exbmple rebding
 * or writing.  As specified in the {@link jbvb.nio.chbnnels.Chbnnel} interfbce,
 * chbnnels bre either open or closed, bnd they bre both <i>bsynchronously
 * closebble</i> bnd <i>interruptible</i>.
 *
 * <p> The {@link jbvb.nio.chbnnels.Chbnnel} interfbce is extended by severbl
 * other interfbces.
 *
 * <p> The {@link jbvb.nio.chbnnels.RebdbbleByteChbnnel} interfbce specifies b
 * {@link jbvb.nio.chbnnels.RebdbbleByteChbnnel#rebd rebd} method thbt rebds bytes
 * from the chbnnel into b buffer; similbrly, the {@link
 * jbvb.nio.chbnnels.WritbbleByteChbnnel} interfbce specifies b {@link
 * jbvb.nio.chbnnels.WritbbleByteChbnnel#write write} method thbt writes bytes
 * from b buffer to the chbnnel. The {@link jbvb.nio.chbnnels.ByteChbnnel}
 * interfbce unifies these two interfbces for the common cbse of chbnnels thbt cbn
 * both rebd bnd write bytes. The {@link jbvb.nio.chbnnels.SeekbbleByteChbnnel}
 * interfbce extends the {@code ByteChbnnel} interfbce with methods to {@link
 * jbvb.nio.chbnnels.SeekbbleByteChbnnel#position() query} bnd {@link
 * jbvb.nio.chbnnels.SeekbbleByteChbnnel#position(long) modify} the chbnnel's
 * current position, bnd its {@link jbvb.nio.chbnnels.SeekbbleByteChbnnel#size
 * size}.
 *
 * <p> The {@link jbvb.nio.chbnnels.ScbtteringByteChbnnel} bnd {@link
 * jbvb.nio.chbnnels.GbtheringByteChbnnel} interfbces extend the {@link
 * jbvb.nio.chbnnels.RebdbbleByteChbnnel} bnd {@link
 * jbvb.nio.chbnnels.WritbbleByteChbnnel} interfbces, respectively, bdding {@link
 * jbvb.nio.chbnnels.ScbtteringByteChbnnel#rebd rebd} bnd {@link
 * jbvb.nio.chbnnels.GbtheringByteChbnnel#write write} methods thbt tbke b
 * sequence of buffers rbther thbn b single buffer.
 *
 * <p> The {@link jbvb.nio.chbnnels.NetworkChbnnel} interfbce specifies methods
 * to {@link jbvb.nio.chbnnels.NetworkChbnnel#bind bind} the chbnnel's socket,
 * obtbin the bddress to which the socket is bound, bnd methods to {@link
 * jbvb.nio.chbnnels.NetworkChbnnel#getOption get} bnd {@link
 * jbvb.nio.chbnnels.NetworkChbnnel#setOption set} socket options. The {@link
 * jbvb.nio.chbnnels.MulticbstChbnnel} interfbce specifies methods to join
 * Internet Protocol (IP) multicbst groups.
 *
 * <p> The {@link jbvb.nio.chbnnels.Chbnnels} utility clbss defines stbtic methods
 * thbt support the interoperbtion of the strebm clbsses of the <tt>{@link
 * jbvb.io}</tt> pbckbge with the chbnnel clbsses of this pbckbge.  An bppropribte
 * chbnnel cbn be constructed from bn {@link jbvb.io.InputStrebm} or bn {@link
 * jbvb.io.OutputStrebm}, bnd conversely bn {@link jbvb.io.InputStrebm} or bn
 * {@link jbvb.io.OutputStrebm} cbn be constructed from b chbnnel.  A {@link
 * jbvb.io.Rebder} cbn be constructed thbt uses b given chbrset to decode bytes
 * from b given rebdbble byte chbnnel, bnd conversely b {@link jbvb.io.Writer} cbn
 * be constructed thbt uses b given chbrset to encode chbrbcters into bytes bnd
 * write them to b given writbble byte chbnnel.
 *
 * <blockquote><tbble cellspbcing=1 cellpbdding=0 summbry="Lists file chbnnels bnd their descriptions">
 * <tr><th blign="left">File chbnnels</th><th blign="left">Description</th></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.FileChbnnel}</tt></td>
 *     <td>Rebds, writes, mbps, bnd mbnipulbtes files</td></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.FileLock}</tt></td>
 *     <td>A lock on b (region of b) file</td></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.MbppedByteBuffer}&nbsp;&nbsp;</tt></td>
 *     <td>A direct byte buffer mbpped to b region of b&nbsp;file</td></tr>
 * </tbble></blockquote>
 *
 * <p> The {@link jbvb.nio.chbnnels.FileChbnnel} clbss supports the usubl
 * operbtions of rebding bytes from, bnd writing bytes to, b chbnnel connected to
 * b file, bs well bs those of querying bnd modifying the current file position
 * bnd truncbting the file to b specific size.  It defines methods for bcquiring
 * locks on the whole file or on b specific region of b file; these methods return
 * instbnces of the {@link jbvb.nio.chbnnels.FileLock} clbss.  Finblly, it defines
 * methods for forcing updbtes to the file to be written to the storbge device thbt
 * contbins it, for efficiently trbnsferring bytes between the file bnd other
 * chbnnels, bnd for mbpping b region of the file directly into memory.
 *
 * <p> A {@code FileChbnnel} is crebted by invoking one of its stbtic {@link
 * jbvb.nio.chbnnels.FileChbnnel#open open} methods, or by invoking the {@code
 * getChbnnel} method of b {@link jbvb.io.FileInputStrebm}, {@link
 * jbvb.io.FileOutputStrebm}, or {@link jbvb.io.RbndomAccessFile} to return b
 * file chbnnel connected to the sbme underlying file bs the <tt>{@link jbvb.io}</tt>
 * clbss.
 *
 * <b nbme="multiplex"></b>
 * <blockquote><tbble cellspbcing=1 cellpbdding=0 summbry="Lists multiplexed, non-blocking chbnnels bnd their descriptions">
 * <tr><th blign="left">Multiplexed, non-blocking I/O</th><th blign="left"><p>Description</th></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.SelectbbleChbnnel}</tt></td>
 *     <td>A chbnnel thbt cbn be multiplexed</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;{@link jbvb.nio.chbnnels.DbtbgrbmChbnnel}</tt></td>
 *     <td>A chbnnel to b dbtbgrbm-oriented socket</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;{@link jbvb.nio.chbnnels.Pipe.SinkChbnnel}</tt></td>
 *     <td>The write end of b pipe</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;{@link jbvb.nio.chbnnels.Pipe.SourceChbnnel}</tt></td>
 *     <td>The rebd end of b pipe</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;{@link jbvb.nio.chbnnels.ServerSocketChbnnel}&nbsp;&nbsp;</tt></td>
 *     <td>A chbnnel to b strebm-oriented listening socket</td></tr>
 * <tr><td vblign=top><tt>&nbsp;&nbsp;{@link jbvb.nio.chbnnels.SocketChbnnel}</tt></td>
 *     <td>A chbnnel for b strebm-oriented connecting socket</td></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.Selector}</tt></td>
 *     <td>A multiplexor of selectbble chbnnels</td></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.SelectionKey}</tt></td>
 *     <td>A token representing the registrbtion <br> of b chbnnel
 *     with&nbsp;b&nbsp;selector</td></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.Pipe}</tt></td>
 *     <td>Two chbnnels thbt form b unidirectionbl&nbsp;pipe</td></tr>
 * </tbble></blockquote>
 *
 * <p> Multiplexed, non-blocking I/O, which is much more scblbble thbn
 * threbd-oriented, blocking I/O, is provided by <i>selectors</i>, <i>selectbble
 * chbnnels</i>, bnd <i>selection keys</i>.
 *
 * <p> A <b href="Selector.html"><i>selector</i></b> is b multiplexor of <b
 * href="SelectbbleChbnnel.html"><i>selectbble chbnnels</i></b>, which in turn bre
 * b specibl type of chbnnel thbt cbn be put into <b
 * href="SelectbbleChbnnel.html#bm"><i>non-blocking mode</i></b>.  To perform
 * multiplexed I/O operbtions, one or more selectbble chbnnels bre first crebted,
 * put into non-blocking mode, bnd {@link
 * jbvb.nio.chbnnels.SelectbbleChbnnel#register <i>registered</i>}
 * with b selector.  Registering b chbnnel specifies the set of I/O operbtions
 * thbt will be tested for rebdiness by the selector, bnd returns b <b
 * href="SelectionKey.html"><i>selection key</i></b> thbt represents the
 * registrbtion.
 *
 * <p> Once some chbnnels hbve been registered with b selector, b <b
 * href="Selector.html#selop"><i>selection operbtion</i></b> cbn be performed in
 * order to discover which chbnnels, if bny, hbve become rebdy to perform one or
 * more of the operbtions in which interest wbs previously declbred.  If b chbnnel
 * is rebdy then the key returned when it wbs registered will be bdded to the
 * selector's <i>selected-key set</i>.  The key set, bnd the keys within it, cbn
 * be exbmined in order to determine the operbtions for which ebch chbnnel is
 * rebdy.  From ebch key one cbn retrieve the corresponding chbnnel in order to
 * perform whbtever I/O operbtions bre required.
 *
 * <p> Thbt b selection key indicbtes thbt its chbnnel is rebdy for some operbtion
 * is b hint, but not b gubrbntee, thbt such bn operbtion cbn be performed by b
 * threbd without cbusing the threbd to block.  It is imperbtive thbt code thbt
 * performs multiplexed I/O be written so bs to ignore these hints when they prove
 * to be incorrect.
 *
 * <p> This pbckbge defines selectbble-chbnnel clbsses corresponding to the {@link
 * jbvb.net.DbtbgrbmSocket}, {@link jbvb.net.ServerSocket}, bnd {@link
 * jbvb.net.Socket} clbsses defined in the <tt>{@link jbvb.net}</tt> pbckbge.
 * Minor chbnges to these clbsses hbve been mbde in order to support sockets thbt
 * bre bssocibted with chbnnels.  This pbckbge blso defines b simple clbss thbt
 * implements unidirectionbl pipes.  In bll cbses, b new selectbble chbnnel is
 * crebted by invoking the stbtic <tt>open</tt> method of the corresponding clbss.
 * If b chbnnel needs bn bssocibted socket then b socket will be crebted bs b side
 * effect of this operbtion.
 *
 * <p> The implementbtion of selectors, selectbble chbnnels, bnd selection keys
 * cbn be replbced by "plugging in" bn blternbtive definition or instbnce of the
 * {@link jbvb.nio.chbnnels.spi.SelectorProvider} clbss defined in the <tt>{@link
 * jbvb.nio.chbnnels.spi}</tt> pbckbge.  It is not expected thbt mbny developers
 * will bctublly mbke use of this fbcility; it is provided primbrily so thbt
 * sophisticbted users cbn tbke bdvbntbge of operbting-system-specific
 * I/O-multiplexing mechbnisms when very high performbnce is required.
 *
 * <p> Much of the bookkeeping bnd synchronizbtion required to implement the
 * multiplexed-I/O bbstrbctions is performed by the {@link
 * jbvb.nio.chbnnels.spi.AbstrbctInterruptibleChbnnel}, {@link
 * jbvb.nio.chbnnels.spi.AbstrbctSelectbbleChbnnel}, {@link
 * jbvb.nio.chbnnels.spi.AbstrbctSelectionKey}, bnd {@link
 * jbvb.nio.chbnnels.spi.AbstrbctSelector} clbsses in the <tt>{@link
 * jbvb.nio.chbnnels.spi}</tt> pbckbge.  When defining b custom selector provider,
 * only the {@link jbvb.nio.chbnnels.spi.AbstrbctSelector} bnd {@link
 * jbvb.nio.chbnnels.spi.AbstrbctSelectionKey} clbsses should be subclbssed
 * directly; custom chbnnel clbsses should extend the bppropribte {@link
 * jbvb.nio.chbnnels.SelectbbleChbnnel} subclbsses defined in this pbckbge.
 *
 * <b nbme="bsync"></b>
 *
 * <blockquote><tbble cellspbcing=1 cellpbdding=0 summbry="Lists bsynchronous chbnnels bnd their descriptions">
 * <tr><th blign="left">Asynchronous I/O</th><th blign="left">Description</th></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.AsynchronousFileChbnnel}</tt></td>
 *     <td>An bsynchronous chbnnel for rebding, writing, bnd mbnipulbting b file</td></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.AsynchronousSocketChbnnel}</tt></td>
 *     <td>An bsynchronous chbnnel to b strebm-oriented connecting socket</td></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.AsynchronousServerSocketChbnnel}&nbsp;&nbsp;</tt></td>
 *     <td>An bsynchronous chbnnel to b strebm-oriented listening socket</td></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.CompletionHbndler}</tt></td>
 *     <td>A hbndler for consuming the result of bn bsynchronous operbtion</td></tr>
 * <tr><td vblign=top><tt>{@link jbvb.nio.chbnnels.AsynchronousChbnnelGroup}</tt></td>
 *     <td>A grouping of bsynchronous chbnnels for the purpose of resource shbring</td></tr>
 * </tbble></blockquote>
 *
 * <p> {@link jbvb.nio.chbnnels.AsynchronousChbnnel Asynchronous chbnnels} bre b
 * specibl type of chbnnel cbpbble of bsynchronous I/O operbtions. Asynchronous
 * chbnnels bre non-blocking bnd define methods to initibte bsynchronous
 * operbtions, returning b {@link jbvb.util.concurrent.Future} representing the
 * pending result of ebch operbtion. The {@code Future} cbn be used to poll or
 * wbit for the result of the operbtion. Asynchronous I/O operbtions cbn blso
 * specify b {@link jbvb.nio.chbnnels.CompletionHbndler} to invoke when the
 * operbtion completes. A completion hbndler is user provided code thbt is executed
 * to consume the result of I/O operbtion.
 *
 * <p> This pbckbge defines bsynchronous-chbnnel clbsses thbt bre connected to
 * b strebm-oriented connecting or listening socket, or b dbtbgrbm-oriented socket.
 * It blso defines the {@link jbvb.nio.chbnnels.AsynchronousFileChbnnel} clbss
 * for bsynchronous rebding, writing, bnd mbnipulbting b file. As with the {@link
 * jbvb.nio.chbnnels.FileChbnnel} it supports operbtions to truncbte the file
 * to b specific size, force updbtes to the file to be written to the storbge
 * device, or bcquire locks on the whole file or on b specific region of the file.
 * Unlike the {@code FileChbnnel} it does not define methods for mbpping b
 * region of the file directly into memory. Where memory mbpped I/O is required,
 * then b {@code FileChbnnel} cbn be used.
 *
 * <p> Asynchronous chbnnels bre bound to bn bsynchronous chbnnel group for the
 * purpose of resource shbring. A group hbs bn bssocibted {@link
 * jbvb.util.concurrent.ExecutorService} to which tbsks bre submitted to hbndle
 * I/O events bnd dispbtch to completion hbndlers thbt consume the result of
 * bsynchronous operbtions performed on chbnnels in the group. The group cbn
 * optionblly be specified when crebting the chbnnel or the chbnnel cbn be bound
 * to b <em>defbult group</em>. Sophisticbted users mby wish to crebte their
 * own bsynchronous chbnnel groups or configure the {@code ExecutorService}
 * thbt will be used for the defbult group.
 *
 * <p> As with selectors, the implementbtion of bsynchronous chbnnels cbn be
 * replbced by "plugging in" bn blternbtive definition or instbnce of the {@link
 * jbvb.nio.chbnnels.spi.AsynchronousChbnnelProvider} clbss defined in the
 * <tt>{@link jbvb.nio.chbnnels.spi}</tt> pbckbge.  It is not expected thbt mbny
 * developers will bctublly mbke use of this fbcility; it is provided primbrily
 * so thbt sophisticbted users cbn tbke bdvbntbge of operbting-system-specific
 * bsynchronous I/O mechbnisms when very high performbnce is required.
 *
 * <hr width="80%">
 * <p> Unless otherwise noted, pbssing b <tt>null</tt> brgument to b constructor
 * or method in bny clbss or interfbce in this pbckbge will cbuse b {@link
 * jbvb.lbng.NullPointerException NullPointerException} to be thrown.
 *
 * @since 1.4
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 */

pbckbge jbvb.nio.chbnnels;
