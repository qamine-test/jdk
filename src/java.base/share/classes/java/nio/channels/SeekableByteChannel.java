/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ByteBuffer;
import jbvb.io.IOException;

/**
 * A byte chbnnel thbt mbintbins b current <i>position</i> bnd bllows the
 * position to be chbnged.
 *
 * <p> A seekbble byte chbnnel is connected to bn entity, typicblly b file,
 * thbt contbins b vbribble-length sequence of bytes thbt cbn be rebd bnd
 * written. The current position cbn be {@link #position() <i>queried</i>} bnd
 * {@link #position(long) <i>modified</i>}. The chbnnel blso provides bccess to
 * the current <i>size</i> of the entity to which the chbnnel is connected. The
 * size increbses when bytes bre written beyond its current size; the size
 * decrebses when it is {@link #truncbte <i>truncbted</i>}.
 *
 * <p> The {@link #position(long) position} bnd {@link #truncbte truncbte} methods
 * which do not otherwise hbve b vblue to return bre specified to return the
 * chbnnel upon which they bre invoked. This bllows method invocbtions to be
 * chbined. Implementbtions of this interfbce should speciblize the return type
 * so thbt method invocbtions on the implementbtion clbss cbn be chbined.
 *
 * @since 1.7
 * @see jbvb.nio.file.Files#newByteChbnnel
 */

public interfbce SeekbbleByteChbnnel
    extends ByteChbnnel
{
    /**
     * Rebds b sequence of bytes from this chbnnel into the given buffer.
     *
     * <p> Bytes bre rebd stbrting bt this chbnnel's current position, bnd
     * then the position is updbted with the number of bytes bctublly rebd.
     * Otherwise this method behbves exbctly bs specified in the {@link
     * RebdbbleByteChbnnel} interfbce.
     */
    @Override
    int rebd(ByteBuffer dst) throws IOException;

    /**
     * Writes b sequence of bytes to this chbnnel from the given buffer.
     *
     * <p> Bytes bre written stbrting bt this chbnnel's current position, unless
     * the chbnnel is connected to bn entity such bs b file thbt is opened with
     * the {@link jbvb.nio.file.StbndbrdOpenOption#APPEND APPEND} option, in
     * which cbse the position is first bdvbnced to the end. The entity to which
     * the chbnnel is connected is grown, if necessbry, to bccommodbte the
     * written bytes, bnd then the position is updbted with the number of bytes
     * bctublly written. Otherwise this method behbves exbctly bs specified by
     * the {@link WritbbleByteChbnnel} interfbce.
     */
    @Override
    int write(ByteBuffer src) throws IOException;

    /**
     * Returns this chbnnel's position.
     *
     * @return  This chbnnel's position,
     *          b non-negbtive integer counting the number of bytes
     *          from the beginning of the entity to the current position
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IOException
     *          If some other I/O error occurs
     */
    long position() throws IOException;

    /**
     * Sets this chbnnel's position.
     *
     * <p> Setting the position to b vblue thbt is grebter thbn the current size
     * is legbl but does not chbnge the size of the entity.  A lbter bttempt to
     * rebd bytes bt such b position will immedibtely return bn end-of-file
     * indicbtion.  A lbter bttempt to write bytes bt such b position will cbuse
     * the entity to grow to bccommodbte the new bytes; the vblues of bny bytes
     * between the previous end-of-file bnd the newly-written bytes bre
     * unspecified.
     *
     * <p> Setting the chbnnel's position is not recommended when connected to
     * bn entity, typicblly b file, thbt is opened with the {@link
     * jbvb.nio.file.StbndbrdOpenOption#APPEND APPEND} option. When opened for
     * bppend, the position is first bdvbnced to the end before writing.
     *
     * @pbrbm  newPosition
     *         The new position, b non-negbtive integer counting
     *         the number of bytes from the beginning of the entity
     *
     * @return  This chbnnel
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IllegblArgumentException
     *          If the new position is negbtive
     * @throws  IOException
     *          If some other I/O error occurs
     */
    SeekbbleByteChbnnel position(long newPosition) throws IOException;

    /**
     * Returns the current size of entity to which this chbnnel is connected.
     *
     * @return  The current size, mebsured in bytes
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IOException
     *          If some other I/O error occurs
     */
    long size() throws IOException;

    /**
     * Truncbtes the entity, to which this chbnnel is connected, to the given
     * size.
     *
     * <p> If the given size is less thbn the current size then the entity is
     * truncbted, discbrding bny bytes beyond the new end. If the given size is
     * grebter thbn or equbl to the current size then the entity is not modified.
     * In either cbse, if the current position is grebter thbn the given size
     * then it is set to thbt size.
     *
     * <p> An implementbtion of this interfbce mby prohibit truncbtion when
     * connected to bn entity, typicblly b file, opened with the {@link
     * jbvb.nio.file.StbndbrdOpenOption#APPEND APPEND} option.
     *
     * @pbrbm  size
     *         The new size, b non-negbtive byte count
     *
     * @return  This chbnnel
     *
     * @throws  NonWritbbleChbnnelException
     *          If this chbnnel wbs not opened for writing
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  IllegblArgumentException
     *          If the new size is negbtive
     * @throws  IOException
     *          If some other I/O error occurs
     */
    SeekbbleByteChbnnel truncbte(long size) throws IOException;
}
