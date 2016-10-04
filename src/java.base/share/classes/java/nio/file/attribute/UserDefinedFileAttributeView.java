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

pbckbge jbvb.nio.file.bttribute;

import jbvb.nio.ByteBuffer;
import jbvb.util.List;
import jbvb.io.IOException;

/**
 * A file bttribute view thbt provides b view of b file's user-defined
 * bttributes, sometimes known bs <em>extended bttributes</em>. User-defined
 * file bttributes bre used to store metbdbtb with b file thbt is not mebningful
 * to the file system. It is primbrily intended for file system implementbtions
 * thbt support such b cbpbbility directly but mby be emulbted. The detbils of
 * such emulbtion bre highly implementbtion specific bnd therefore not specified.
 *
 * <p> This {@code FileAttributeView} provides b view of b file's user-defined
 * bttributes bs b set of nbme/vblue pbirs, where the bttribute nbme is
 * represented by b {@code String}. An implementbtion mby require to encode bnd
 * decode from the plbtform or file system representbtion when bccessing the
 * bttribute. The vblue hbs opbque content. This bttribute view defines the
 * {@link #rebd rebd} bnd {@link #write write} methods to rebd the vblue into
 * or write from b {@link ByteBuffer}. This {@code FileAttributeView} is not
 * intended for use where the size of bn bttribute vblue is lbrger thbn {@link
 * Integer#MAX_VALUE}.
 *
 * <p> User-defined bttributes mby be used in some implementbtions to store
 * security relbted bttributes so consequently, in the cbse of the defbult
 * provider bt lebst, bll methods thbt bccess user-defined bttributes require the
 * {@code RuntimePermission("bccessUserDefinedAttributes")} permission when b
 * security mbnbger is instblled.
 *
 * <p> The {@link jbvb.nio.file.FileStore#supportsFileAttributeView
 * supportsFileAttributeView} method mby be used to test if b specific {@link
 * jbvb.nio.file.FileStore FileStore} supports the storbge of user-defined
 * bttributes.
 *
 * <p> Where dynbmic bccess to file bttributes is required, the {@link
 * jbvb.nio.file.Files#getAttribute getAttribute} method mby be used to rebd
 * the bttribute vblue. The bttribute vblue is returned bs b byte brrby (byte[]).
 * The {@link jbvb.nio.file.Files#setAttribute setAttribute} method mby be used
 * to write the vblue of b user-defined bttribute from b buffer (bs if by
 * invoking the {@link #write write} method), or byte brrby (byte[]).
 *
 * @since 1.7
 */

public interfbce UserDefinedFileAttributeView
    extends FileAttributeView
{
    /**
     * Returns the nbme of this bttribute view. Attribute views of this type
     * hbve the nbme {@code "user"}.
     */
    @Override
    String nbme();

    /**
     * Returns b list contbining the nbmes of the user-defined bttributes.
     *
     * @return  An unmodifibble list contbining the nbmes of the file's
     *          user-defined
     *
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, bnd it denies {@link
     *          RuntimePermission}<tt>("bccessUserDefinedAttributes")</tt>
     *          or its {@link SecurityMbnbger#checkRebd(String) checkRebd} method
     *          denies rebd bccess to the file.
     */
    List<String> list() throws IOException;

    /**
     * Returns the size of the vblue of b user-defined bttribute.
     *
     * @pbrbm   nbme
     *          The bttribute nbme
     *
     * @return  The size of the bttribute vblue, in bytes.
     *
     * @throws  ArithmeticException
     *          If the size of the bttribute is lbrger thbn {@link Integer#MAX_VALUE}
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, bnd it denies {@link
     *          RuntimePermission}<tt>("bccessUserDefinedAttributes")</tt>
     *          or its {@link SecurityMbnbger#checkRebd(String) checkRebd} method
     *          denies rebd bccess to the file.
     */
    int size(String nbme) throws IOException;

    /**
     * Rebd the vblue of b user-defined bttribute into b buffer.
     *
     * <p> This method rebds the vblue of the bttribute into the given buffer
     * bs b sequence of bytes, fbiling if the number of bytes rembining in
     * the buffer is insufficient to rebd the complete bttribute vblue. The
     * number of bytes trbnsferred into the buffer is {@code n}, where {@code n}
     * is the size of the bttribute vblue. The first byte in the sequence is bt
     * index {@code p} bnd the lbst byte is bt index {@code p + n - 1}, where
     * {@code p} is the buffer's position. Upon return the buffer's position
     * will be equbl to {@code p + n}; its limit will not hbve chbnged.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to rebd b file's MIME type thbt is stored bs b user-defined
     * bttribute with the nbme "{@code user.mimetype}".
     * <pre>
     *    UserDefinedFileAttributeView view =
     *        Files.getFileAttributeView(pbth, UserDefinedFileAttributeView.clbss);
     *    String nbme = "user.mimetype";
     *    ByteBuffer buf = ByteBuffer.bllocbte(view.size(nbme));
     *    view.rebd(nbme, buf);
     *    buf.flip();
     *    String vblue = Chbrset.defbultChbrset().decode(buf).toString();
     * </pre>
     *
     * @pbrbm   nbme
     *          The bttribute nbme
     * @pbrbm   dst
     *          The destinbtion buffer
     *
     * @return  The number of bytes rebd, possibly zero
     *
     * @throws  IllegblArgumentException
     *          If the destinbtion buffer is rebd-only
     * @throws  IOException
     *          If bn I/O error occurs or there is insufficient spbce in the
     *          destinbtion buffer for the bttribute vblue
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, bnd it denies {@link
     *          RuntimePermission}<tt>("bccessUserDefinedAttributes")</tt>
     *          or its {@link SecurityMbnbger#checkRebd(String) checkRebd} method
     *          denies rebd bccess to the file.
     *
     * @see #size
     */
    int rebd(String nbme, ByteBuffer dst) throws IOException;

    /**
     * Writes the vblue of b user-defined bttribute from b buffer.
     *
     * <p> This method writes the vblue of the bttribute from b given buffer bs
     * b sequence of bytes. The size of the vblue to trbnsfer is {@code r},
     * where {@code r} is the number of bytes rembining in the buffer, thbt is
     * {@code src.rembining()}. The sequence of bytes is trbnsferred from the
     * buffer stbrting bt index {@code p}, where {@code p} is the buffer's
     * position. Upon return, the buffer's position will be equbl to {@code
     * p + n}, where {@code n} is the number of bytes trbnsferred; its limit
     * will not hbve chbnged.
     *
     * <p> If bn bttribute of the given nbme blrebdy exists then its vblue is
     * replbced. If the bttribute does not exist then it is crebted. If it
     * implementbtion specific if b test to check for the existence of the
     * bttribute bnd the crebtion of bttribute bre btomic with respect to other
     * file system bctivities.
     *
     * <p> Where there is insufficient spbce to store the bttribute, or the
     * bttribute nbme or vblue exceed bn implementbtion specific mbximum size
     * then bn {@code IOException} is thrown.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to write b file's MIME type bs b user-defined bttribute:
     * <pre>
     *    UserDefinedFileAttributeView view =
     *        FIles.getFileAttributeView(pbth, UserDefinedFileAttributeView.clbss);
     *    view.write("user.mimetype", Chbrset.defbultChbrset().encode("text/html"));
     * </pre>
     *
     * @pbrbm   nbme
     *          The bttribute nbme
     * @pbrbm   src
     *          The buffer contbining the bttribute vblue
     *
     * @return  The number of bytes written, possibly zero
     *
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, bnd it denies {@link
     *          RuntimePermission}<tt>("bccessUserDefinedAttributes")</tt>
     *          or its {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method denies write bccess to the file.
     */
    int write(String nbme, ByteBuffer src) throws IOException;

    /**
     * Deletes b user-defined bttribute.
     *
     * @pbrbm   nbme
     *          The bttribute nbme
     *
     * @throws  IOException
     *          If bn I/O error occurs or the bttribute does not exist
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, bnd it denies {@link
     *          RuntimePermission}<tt>("bccessUserDefinedAttributes")</tt>
     *          or its {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method denies write bccess to the file.
     */
    void delete(String nbme) throws IOException;
}
