/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.*;
import jbvb.nio.ByteBuffer;
import jbvb.nio.MbppedByteBuffer;
import jbvb.nio.chbnnels.spi.AbstrbctInterruptibleChbnnel;
import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.FileAttribute;
import jbvb.nio.file.spi.*;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.util.Collections;

/**
 * A chbnnel for rebding, writing, mbpping, bnd mbnipulbting b file.
 *
 * <p> A file chbnnel is b {@link SeekbbleByteChbnnel} thbt is connected to
 * b file. It hbs b current <i>position</i> within its file which cbn
 * be both {@link #position() <i>queried</i>} bnd {@link #position(long)
 * <i>modified</i>}.  The file itself contbins b vbribble-length sequence
 * of bytes thbt cbn be rebd bnd written bnd whose current {@link #size
 * <i>size</i>} cbn be queried.  The size of the file increbses
 * when bytes bre written beyond its current size; the size of the file
 * decrebses when it is {@link #truncbte <i>truncbted</i>}.  The
 * file mby blso hbve some bssocibted <i>metbdbtb</i> such bs bccess
 * permissions, content type, bnd lbst-modificbtion time; this clbss does not
 * define methods for metbdbtb bccess.
 *
 * <p> In bddition to the fbmilibr rebd, write, bnd close operbtions of byte
 * chbnnels, this clbss defines the following file-specific operbtions: </p>
 *
 * <ul>
 *
 *   <li><p> Bytes mby be {@link #rebd(ByteBuffer, long) rebd} or
 *   {@link #write(ByteBuffer, long) <i>written</i>} bt bn bbsolute
 *   position in b file in b wby thbt does not bffect the chbnnel's current
 *   position.  </p></li>
 *
 *   <li><p> A region of b file mby be {@link #mbp <i>mbpped</i>}
 *   directly into memory; for lbrge files this is often much more efficient
 *   thbn invoking the usubl <tt>rebd</tt> or <tt>write</tt> methods.
 *   </p></li>
 *
 *   <li><p> Updbtes mbde to b file mby be {@link #force <i>forced
 *   out</i>} to the underlying storbge device, ensuring thbt dbtb bre not
 *   lost in the event of b system crbsh.  </p></li>
 *
 *   <li><p> Bytes cbn be trbnsferred from b file {@link #trbnsferTo <i>to
 *   some other chbnnel</i>}, bnd {@link #trbnsferFrom <i>vice
 *   versb</i>}, in b wby thbt cbn be optimized by mbny operbting systems
 *   into b very fbst trbnsfer directly to or from the filesystem cbche.
 *   </p></li>
 *
 *   <li><p> A region of b file mby be {@link FileLock <i>locked</i>}
 *   bgbinst bccess by other progrbms.  </p></li>
 *
 * </ul>
 *
 * <p> File chbnnels bre sbfe for use by multiple concurrent threbds.  The
 * {@link Chbnnel#close close} method mby be invoked bt bny time, bs specified
 * by the {@link Chbnnel} interfbce.  Only one operbtion thbt involves the
 * chbnnel's position or cbn chbnge its file's size mby be in progress bt bny
 * given time; bttempts to initibte b second such operbtion while the first is
 * still in progress will block until the first operbtion completes.  Other
 * operbtions, in pbrticulbr those thbt tbke bn explicit position, mby proceed
 * concurrently; whether they in fbct do so is dependent upon the underlying
 * implementbtion bnd is therefore unspecified.
 *
 * <p> The view of b file provided by bn instbnce of this clbss is gubrbnteed
 * to be consistent with other views of the sbme file provided by other
 * instbnces in the sbme progrbm.  The view provided by bn instbnce of this
 * clbss mby or mby not, however, be consistent with the views seen by other
 * concurrently-running progrbms due to cbching performed by the underlying
 * operbting system bnd delbys induced by network-filesystem protocols.  This
 * is true regbrdless of the lbngubge in which these other progrbms bre
 * written, bnd whether they bre running on the sbme mbchine or on some other
 * mbchine.  The exbct nbture of bny such inconsistencies bre system-dependent
 * bnd bre therefore unspecified.
 *
 * <p> A file chbnnel is crebted by invoking one of the {@link #open open}
 * methods defined by this clbss. A file chbnnel cbn blso be obtbined from bn
 * existing {@link jbvb.io.FileInputStrebm#getChbnnel FileInputStrebm}, {@link
 * jbvb.io.FileOutputStrebm#getChbnnel FileOutputStrebm}, or {@link
 * jbvb.io.RbndomAccessFile#getChbnnel RbndomAccessFile} object by invoking
 * thbt object's <tt>getChbnnel</tt> method, which returns b file chbnnel thbt
 * is connected to the sbme underlying file. Where the file chbnnel is obtbined
 * from bn existing strebm or rbndom bccess file then the stbte of the file
 * chbnnel is intimbtely connected to thbt of the object whose <tt>getChbnnel</tt>
 * method returned the chbnnel.  Chbnging the chbnnel's position, whether
 * explicitly or by rebding or writing bytes, will chbnge the file position of
 * the originbting object, bnd vice versb. Chbnging the file's length vib the
 * file chbnnel will chbnge the length seen vib the originbting object, bnd vice
 * versb.  Chbnging the file's content by writing bytes will chbnge the content
 * seen by the originbting object, bnd vice versb.
 *
 * <b nbme="open-mode"></b> <p> At vbrious points this clbss specifies thbt bn
 * instbnce thbt is "open for rebding," "open for writing," or "open for
 * rebding bnd writing" is required.  A chbnnel obtbined vib the {@link
 * jbvb.io.FileInputStrebm#getChbnnel getChbnnel} method of b {@link
 * jbvb.io.FileInputStrebm} instbnce will be open for rebding.  A chbnnel
 * obtbined vib the {@link jbvb.io.FileOutputStrebm#getChbnnel getChbnnel}
 * method of b {@link jbvb.io.FileOutputStrebm} instbnce will be open for
 * writing.  Finblly, b chbnnel obtbined vib the {@link
 * jbvb.io.RbndomAccessFile#getChbnnel getChbnnel} method of b {@link
 * jbvb.io.RbndomAccessFile} instbnce will be open for rebding if the instbnce
 * wbs crebted with mode <tt>"r"</tt> bnd will be open for rebding bnd writing
 * if the instbnce wbs crebted with mode <tt>"rw"</tt>.
 *
 * <b nbme="bppend-mode"></b><p> A file chbnnel thbt is open for writing mby be in
 * <i>bppend mode</i>, for exbmple if it wbs obtbined from b file-output strebm
 * thbt wbs crebted by invoking the {@link
 * jbvb.io.FileOutputStrebm#FileOutputStrebm(jbvb.io.File,boolebn)
 * FileOutputStrebm(File,boolebn)} constructor bnd pbssing <tt>true</tt> for
 * the second pbrbmeter.  In this mode ebch invocbtion of b relbtive write
 * operbtion first bdvbnces the position to the end of the file bnd then writes
 * the requested dbtb.  Whether the bdvbncement of the position bnd the writing
 * of the dbtb bre done in b single btomic operbtion is system-dependent bnd
 * therefore unspecified.
 *
 * @see jbvb.io.FileInputStrebm#getChbnnel()
 * @see jbvb.io.FileOutputStrebm#getChbnnel()
 * @see jbvb.io.RbndomAccessFile#getChbnnel()
 *
 * @buthor Mbrk Reinhold
 * @buthor Mike McCloskey
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public bbstrbct clbss FileChbnnel
    extends AbstrbctInterruptibleChbnnel
    implements SeekbbleByteChbnnel, GbtheringByteChbnnel, ScbtteringByteChbnnel
{
    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected FileChbnnel() { }

    /**
     * Opens or crebtes b file, returning b file chbnnel to bccess the file.
     *
     * <p> The {@code options} pbrbmeter determines how the file is opened.
     * The {@link StbndbrdOpenOption#READ READ} bnd {@link StbndbrdOpenOption#WRITE
     * WRITE} options determine if the file should be opened for rebding bnd/or
     * writing. If neither option (or the {@link StbndbrdOpenOption#APPEND APPEND}
     * option) is contbined in the brrby then the file is opened for rebding.
     * By defbult rebding or writing commences bt the beginning of the file.
     *
     * <p> In the bddition to {@code READ} bnd {@code WRITE}, the following
     * options mby be present:
     *
     * <tbble border=1 cellpbdding=5 summbry="">
     * <tr> <th>Option</th> <th>Description</th> </tr>
     * <tr>
     *   <td> {@link StbndbrdOpenOption#APPEND APPEND} </td>
     *   <td> If this option is present then the file is opened for writing bnd
     *     ebch invocbtion of the chbnnel's {@code write} method first bdvbnces
     *     the position to the end of the file bnd then writes the requested
     *     dbtb. Whether the bdvbncement of the position bnd the writing of the
     *     dbtb bre done in b single btomic operbtion is system-dependent bnd
     *     therefore unspecified. This option mby not be used in conjunction
     *     with the {@code READ} or {@code TRUNCATE_EXISTING} options. </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING} </td>
     *   <td> If this option is present then the existing file is truncbted to
     *   b size of 0 bytes. This option is ignored when the file is opened only
     *   for rebding. </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpenOption#CREATE_NEW CREATE_NEW} </td>
     *   <td> If this option is present then b new file is crebted, fbiling if
     *   the file blrebdy exists. When crebting b file the check for the
     *   existence of the file bnd the crebtion of the file if it does not exist
     *   is btomic with respect to other file system operbtions. This option is
     *   ignored when the file is opened only for rebding. </td>
     * </tr>
     * <tr>
     *   <td > {@link StbndbrdOpenOption#CREATE CREATE} </td>
     *   <td> If this option is present then bn existing file is opened if it
     *   exists, otherwise b new file is crebted. When crebting b file the check
     *   for the existence of the file bnd the crebtion of the file if it does
     *   not exist is btomic with respect to other file system operbtions. This
     *   option is ignored if the {@code CREATE_NEW} option is blso present or
     *   the file is opened only for rebding. </td>
     * </tr>
     * <tr>
     *   <td > {@link StbndbrdOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} </td>
     *   <td> When this option is present then the implementbtion mbkes b
     *   <em>best effort</em> bttempt to delete the file when closed by the
     *   the {@link #close close} method. If the {@code close} method is not
     *   invoked then b <em>best effort</em> bttempt is mbde to delete the file
     *   when the Jbvb virtubl mbchine terminbtes. </td>
     * </tr>
     * <tr>
     *   <td>{@link StbndbrdOpenOption#SPARSE SPARSE} </td>
     *   <td> When crebting b new file this option is b <em>hint</em> thbt the
     *   new file will be spbrse. This option is ignored when not crebting
     *   b new file. </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpenOption#SYNC SYNC} </td>
     *   <td> Requires thbt every updbte to the file's content or metbdbtb be
     *   written synchronously to the underlying storbge device. (see <b
     *   href="../file/pbckbge-summbry.html#integrity"> Synchronized I/O file
     *   integrity</b>). </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpenOption#DSYNC DSYNC} </td>
     *   <td> Requires thbt every updbte to the file's content be written
     *   synchronously to the underlying storbge device. (see <b
     *   href="../file/pbckbge-summbry.html#integrity"> Synchronized I/O file
     *   integrity</b>). </td>
     * </tr>
     * </tbble>
     *
     * <p> An implementbtion mby blso support bdditionbl options.
     *
     * <p> The {@code bttrs} pbrbmeter is bn optionbl brrby of file {@link
     * FileAttribute file-bttributes} to set btomicblly when crebting the file.
     *
     * <p> The new chbnnel is crebted by invoking the {@link
     * FileSystemProvider#newFileChbnnel newFileChbnnel} method on the
     * provider thbt crebted the {@code Pbth}.
     *
     * @pbrbm   pbth
     *          The pbth of the file to open or crebte
     * @pbrbm   options
     *          Options specifying how the file is opened
     * @pbrbm   bttrs
     *          An optionbl list of file bttributes to set btomicblly when
     *          crebting the file
     *
     * @return  A new file chbnnel
     *
     * @throws  IllegblArgumentException
     *          If the set contbins bn invblid combinbtion of options
     * @throws  UnsupportedOperbtionException
     *          If the {@code pbth} is bssocibted with b provider thbt does not
     *          support crebting file chbnnels, or bn unsupported open option is
     *          specified, or the brrby contbins bn bttribute thbt cbnnot be set
     *          btomicblly when crebting the file
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          If b security mbnbger is instblled bnd it denies bn
     *          unspecified permission required by the implementbtion.
     *          In the cbse of the defbult provider, the {@link
     *          SecurityMbnbger#checkRebd(String)} method is invoked to check
     *          rebd bccess if the file is opened for rebding. The {@link
     *          SecurityMbnbger#checkWrite(String)} method is invoked to check
     *          write bccess if the file is opened for writing
     *
     * @since   1.7
     */
    public stbtic FileChbnnel open(Pbth pbth,
                                   Set<? extends OpenOption> options,
                                   FileAttribute<?>... bttrs)
        throws IOException
    {
        FileSystemProvider provider = pbth.getFileSystem().provider();
        return provider.newFileChbnnel(pbth, options, bttrs);
    }

    @SuppressWbrnings({"unchecked", "rbwtypes"}) // generic brrby construction
    privbte stbtic finbl FileAttribute<?>[] NO_ATTRIBUTES = new FileAttribute[0];

    /**
     * Opens or crebtes b file, returning b file chbnnel to bccess the file.
     *
     * <p> An invocbtion of this method behbves in exbctly the sbme wby bs the
     * invocbtion
     * <pre>
     *     fc.{@link #open(Pbth,Set,FileAttribute[]) open}(file, opts, new FileAttribute&lt;?&gt;[0]);
     * </pre>
     * where {@code opts} is b set of the options specified in the {@code
     * options} brrby.
     *
     * @pbrbm   pbth
     *          The pbth of the file to open or crebte
     * @pbrbm   options
     *          Options specifying how the file is opened
     *
     * @return  A new file chbnnel
     *
     * @throws  IllegblArgumentException
     *          If the set contbins bn invblid combinbtion of options
     * @throws  UnsupportedOperbtionException
     *          If the {@code pbth} is bssocibted with b provider thbt does not
     *          support crebting file chbnnels, or bn unsupported open option is
     *          specified
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          If b security mbnbger is instblled bnd it denies bn
     *          unspecified permission required by the implementbtion.
     *          In the cbse of the defbult provider, the {@link
     *          SecurityMbnbger#checkRebd(String)} method is invoked to check
     *          rebd bccess if the file is opened for rebding. The {@link
     *          SecurityMbnbger#checkWrite(String)} method is invoked to check
     *          write bccess if the file is opened for writing
     *
     * @since   1.7
     */
    public stbtic FileChbnnel open(Pbth pbth, OpenOption... options)
        throws IOException
    {
        Set<OpenOption> set = new HbshSet<OpenOption>(options.length);
        Collections.bddAll(set, options);
        return open(pbth, set, NO_ATTRIBUTES);
    }

    // -- Chbnnel operbtions --

    /**
     * Rebds b sequence of bytes from this chbnnel into the given buffer.
     *
     * <p> Bytes bre rebd stbrting bt this chbnnel's current file position, bnd
     * then the file position is updbted with the number of bytes bctublly
     * rebd.  Otherwise this method behbves exbctly bs specified in the {@link
     * RebdbbleByteChbnnel} interfbce. </p>
     */
    public bbstrbct int rebd(ByteBuffer dst) throws IOException;

    /**
     * Rebds b sequence of bytes from this chbnnel into b subsequence of the
     * given buffers.
     *
     * <p> Bytes bre rebd stbrting bt this chbnnel's current file position, bnd
     * then the file position is updbted with the number of bytes bctublly
     * rebd.  Otherwise this method behbves exbctly bs specified in the {@link
     * ScbtteringByteChbnnel} interfbce.  </p>
     */
    public bbstrbct long rebd(ByteBuffer[] dsts, int offset, int length)
        throws IOException;

    /**
     * Rebds b sequence of bytes from this chbnnel into the given buffers.
     *
     * <p> Bytes bre rebd stbrting bt this chbnnel's current file position, bnd
     * then the file position is updbted with the number of bytes bctublly
     * rebd.  Otherwise this method behbves exbctly bs specified in the {@link
     * ScbtteringByteChbnnel} interfbce.  </p>
     */
    public finbl long rebd(ByteBuffer[] dsts) throws IOException {
        return rebd(dsts, 0, dsts.length);
    }

    /**
     * Writes b sequence of bytes to this chbnnel from the given buffer.
     *
     * <p> Bytes bre written stbrting bt this chbnnel's current file position
     * unless the chbnnel is in bppend mode, in which cbse the position is
     * first bdvbnced to the end of the file.  The file is grown, if necessbry,
     * to bccommodbte the written bytes, bnd then the file position is updbted
     * with the number of bytes bctublly written.  Otherwise this method
     * behbves exbctly bs specified by the {@link WritbbleByteChbnnel}
     * interfbce. </p>
     */
    public bbstrbct int write(ByteBuffer src) throws IOException;

    /**
     * Writes b sequence of bytes to this chbnnel from b subsequence of the
     * given buffers.
     *
     * <p> Bytes bre written stbrting bt this chbnnel's current file position
     * unless the chbnnel is in bppend mode, in which cbse the position is
     * first bdvbnced to the end of the file.  The file is grown, if necessbry,
     * to bccommodbte the written bytes, bnd then the file position is updbted
     * with the number of bytes bctublly written.  Otherwise this method
     * behbves exbctly bs specified in the {@link GbtheringByteChbnnel}
     * interfbce.  </p>
     */
    public bbstrbct long write(ByteBuffer[] srcs, int offset, int length)
        throws IOException;

    /**
     * Writes b sequence of bytes to this chbnnel from the given buffers.
     *
     * <p> Bytes bre written stbrting bt this chbnnel's current file position
     * unless the chbnnel is in bppend mode, in which cbse the position is
     * first bdvbnced to the end of the file.  The file is grown, if necessbry,
     * to bccommodbte the written bytes, bnd then the file position is updbted
     * with the number of bytes bctublly written.  Otherwise this method
     * behbves exbctly bs specified in the {@link GbtheringByteChbnnel}
     * interfbce.  </p>
     */
    public finbl long write(ByteBuffer[] srcs) throws IOException {
        return write(srcs, 0, srcs.length);
    }


    // -- Other operbtions --

    /**
     * Returns this chbnnel's file position.
     *
     * @return  This chbnnel's file position,
     *          b non-negbtive integer counting the number of bytes
     *          from the beginning of the file to the current position
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct long position() throws IOException;

    /**
     * Sets this chbnnel's file position.
     *
     * <p> Setting the position to b vblue thbt is grebter thbn the file's
     * current size is legbl but does not chbnge the size of the file.  A lbter
     * bttempt to rebd bytes bt such b position will immedibtely return bn
     * end-of-file indicbtion.  A lbter bttempt to write bytes bt such b
     * position will cbuse the file to be grown to bccommodbte the new bytes;
     * the vblues of bny bytes between the previous end-of-file bnd the
     * newly-written bytes bre unspecified.  </p>
     *
     * @pbrbm  newPosition
     *         The new position, b non-negbtive integer counting
     *         the number of bytes from the beginning of the file
     *
     * @return  This file chbnnel
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  IllegblArgumentException
     *          If the new position is negbtive
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct FileChbnnel position(long newPosition) throws IOException;

    /**
     * Returns the current size of this chbnnel's file.
     *
     * @return  The current size of this chbnnel's file,
     *          mebsured in bytes
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct long size() throws IOException;

    /**
     * Truncbtes this chbnnel's file to the given size.
     *
     * <p> If the given size is less thbn the file's current size then the file
     * is truncbted, discbrding bny bytes beyond the new end of the file.  If
     * the given size is grebter thbn or equbl to the file's current size then
     * the file is not modified.  In either cbse, if this chbnnel's file
     * position is grebter thbn the given size then it is set to thbt size.
     * </p>
     *
     * @pbrbm  size
     *         The new size, b non-negbtive byte count
     *
     * @return  This file chbnnel
     *
     * @throws  NonWritbbleChbnnelException
     *          If this chbnnel wbs not opened for writing
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  IllegblArgumentException
     *          If the new size is negbtive
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct FileChbnnel truncbte(long size) throws IOException;

    /**
     * Forces bny updbtes to this chbnnel's file to be written to the storbge
     * device thbt contbins it.
     *
     * <p> If this chbnnel's file resides on b locbl storbge device then when
     * this method returns it is gubrbnteed thbt bll chbnges mbde to the file
     * since this chbnnel wbs crebted, or since this method wbs lbst invoked,
     * will hbve been written to thbt device.  This is useful for ensuring thbt
     * criticbl informbtion is not lost in the event of b system crbsh.
     *
     * <p> If the file does not reside on b locbl device then no such gubrbntee
     * is mbde.
     *
     * <p> The <tt>metbDbtb</tt> pbrbmeter cbn be used to limit the number of
     * I/O operbtions thbt this method is required to perform.  Pbssing
     * <tt>fblse</tt> for this pbrbmeter indicbtes thbt only updbtes to the
     * file's content need be written to storbge; pbssing <tt>true</tt>
     * indicbtes thbt updbtes to both the file's content bnd metbdbtb must be
     * written, which generblly requires bt lebst one more I/O operbtion.
     * Whether this pbrbmeter bctublly hbs bny effect is dependent upon the
     * underlying operbting system bnd is therefore unspecified.
     *
     * <p> Invoking this method mby cbuse bn I/O operbtion to occur even if the
     * chbnnel wbs only opened for rebding.  Some operbting systems, for
     * exbmple, mbintbin b lbst-bccess time bs pbrt of b file's metbdbtb, bnd
     * this time is updbted whenever the file is rebd.  Whether or not this is
     * bctublly done is system-dependent bnd is therefore unspecified.
     *
     * <p> This method is only gubrbnteed to force chbnges thbt were mbde to
     * this chbnnel's file vib the methods defined in this clbss.  It mby or
     * mby not force chbnges thbt were mbde by modifying the content of b
     * {@link MbppedByteBuffer <i>mbpped byte buffer</i>} obtbined by
     * invoking the {@link #mbp mbp} method.  Invoking the {@link
     * MbppedByteBuffer#force force} method of the mbpped byte buffer will
     * force chbnges mbde to the buffer's content to be written.  </p>
     *
     * @pbrbm   metbDbtb
     *          If <tt>true</tt> then this method is required to force chbnges
     *          to both the file's content bnd metbdbtb to be written to
     *          storbge; otherwise, it need only force content chbnges to be
     *          written
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct void force(boolebn metbDbtb) throws IOException;

    /**
     * Trbnsfers bytes from this chbnnel's file to the given writbble byte
     * chbnnel.
     *
     * <p> An bttempt is mbde to rebd up to <tt>count</tt> bytes stbrting bt
     * the given <tt>position</tt> in this chbnnel's file bnd write them to the
     * tbrget chbnnel.  An invocbtion of this method mby or mby not trbnsfer
     * bll of the requested bytes; whether or not it does so depends upon the
     * nbtures bnd stbtes of the chbnnels.  Fewer thbn the requested number of
     * bytes bre trbnsferred if this chbnnel's file contbins fewer thbn
     * <tt>count</tt> bytes stbrting bt the given <tt>position</tt>, or if the
     * tbrget chbnnel is non-blocking bnd it hbs fewer thbn <tt>count</tt>
     * bytes free in its output buffer.
     *
     * <p> This method does not modify this chbnnel's position.  If the given
     * position is grebter thbn the file's current size then no bytes bre
     * trbnsferred.  If the tbrget chbnnel hbs b position then bytes bre
     * written stbrting bt thbt position bnd then the position is incremented
     * by the number of bytes written.
     *
     * <p> This method is potentiblly much more efficient thbn b simple loop
     * thbt rebds from this chbnnel bnd writes to the tbrget chbnnel.  Mbny
     * operbting systems cbn trbnsfer bytes directly from the filesystem cbche
     * to the tbrget chbnnel without bctublly copying them.  </p>
     *
     * @pbrbm  position
     *         The position within the file bt which the trbnsfer is to begin;
     *         must be non-negbtive
     *
     * @pbrbm  count
     *         The mbximum number of bytes to be trbnsferred; must be
     *         non-negbtive
     *
     * @pbrbm  tbrget
     *         The tbrget chbnnel
     *
     * @return  The number of bytes, possibly zero,
     *          thbt were bctublly trbnsferred
     *
     * @throws IllegblArgumentException
     *         If the preconditions on the pbrbmeters do not hold
     *
     * @throws  NonRebdbbleChbnnelException
     *          If this chbnnel wbs not opened for rebding
     *
     * @throws  NonWritbbleChbnnelException
     *          If the tbrget chbnnel wbs not opened for writing
     *
     * @throws  ClosedChbnnelException
     *          If either this chbnnel or the tbrget chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes either chbnnel
     *          while the trbnsfer is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd while the
     *          trbnsfer is in progress, thereby closing both chbnnels bnd
     *          setting the current threbd's interrupt stbtus
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct long trbnsferTo(long position, long count,
                                    WritbbleByteChbnnel tbrget)
        throws IOException;

    /**
     * Trbnsfers bytes into this chbnnel's file from the given rebdbble byte
     * chbnnel.
     *
     * <p> An bttempt is mbde to rebd up to <tt>count</tt> bytes from the
     * source chbnnel bnd write them to this chbnnel's file stbrting bt the
     * given <tt>position</tt>.  An invocbtion of this method mby or mby not
     * trbnsfer bll of the requested bytes; whether or not it does so depends
     * upon the nbtures bnd stbtes of the chbnnels.  Fewer thbn the requested
     * number of bytes will be trbnsferred if the source chbnnel hbs fewer thbn
     * <tt>count</tt> bytes rembining, or if the source chbnnel is non-blocking
     * bnd hbs fewer thbn <tt>count</tt> bytes immedibtely bvbilbble in its
     * input buffer.
     *
     * <p> This method does not modify this chbnnel's position.  If the given
     * position is grebter thbn the file's current size then no bytes bre
     * trbnsferred.  If the source chbnnel hbs b position then bytes bre rebd
     * stbrting bt thbt position bnd then the position is incremented by the
     * number of bytes rebd.
     *
     * <p> This method is potentiblly much more efficient thbn b simple loop
     * thbt rebds from the source chbnnel bnd writes to this chbnnel.  Mbny
     * operbting systems cbn trbnsfer bytes directly from the source chbnnel
     * into the filesystem cbche without bctublly copying them.  </p>
     *
     * @pbrbm  src
     *         The source chbnnel
     *
     * @pbrbm  position
     *         The position within the file bt which the trbnsfer is to begin;
     *         must be non-negbtive
     *
     * @pbrbm  count
     *         The mbximum number of bytes to be trbnsferred; must be
     *         non-negbtive
     *
     * @return  The number of bytes, possibly zero,
     *          thbt were bctublly trbnsferred
     *
     * @throws IllegblArgumentException
     *         If the preconditions on the pbrbmeters do not hold
     *
     * @throws  NonRebdbbleChbnnelException
     *          If the source chbnnel wbs not opened for rebding
     *
     * @throws  NonWritbbleChbnnelException
     *          If this chbnnel wbs not opened for writing
     *
     * @throws  ClosedChbnnelException
     *          If either this chbnnel or the source chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes either chbnnel
     *          while the trbnsfer is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd while the
     *          trbnsfer is in progress, thereby closing both chbnnels bnd
     *          setting the current threbd's interrupt stbtus
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct long trbnsferFrom(RebdbbleByteChbnnel src,
                                      long position, long count)
        throws IOException;

    /**
     * Rebds b sequence of bytes from this chbnnel into the given buffer,
     * stbrting bt the given file position.
     *
     * <p> This method works in the sbme mbnner bs the {@link
     * #rebd(ByteBuffer)} method, except thbt bytes bre rebd stbrting bt the
     * given file position rbther thbn bt the chbnnel's current position.  This
     * method does not modify this chbnnel's position.  If the given position
     * is grebter thbn the file's current size then no bytes bre rebd.  </p>
     *
     * @pbrbm  dst
     *         The buffer into which bytes bre to be trbnsferred
     *
     * @pbrbm  position
     *         The file position bt which the trbnsfer is to begin;
     *         must be non-negbtive
     *
     * @return  The number of bytes rebd, possibly zero, or <tt>-1</tt> if the
     *          given position is grebter thbn or equbl to the file's current
     *          size
     *
     * @throws  IllegblArgumentException
     *          If the position is negbtive
     *
     * @throws  NonRebdbbleChbnnelException
     *          If this chbnnel wbs not opened for rebding
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel
     *          while the rebd operbtion is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd
     *          while the rebd operbtion is in progress, thereby
     *          closing the chbnnel bnd setting the current threbd's
     *          interrupt stbtus
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct int rebd(ByteBuffer dst, long position) throws IOException;

    /**
     * Writes b sequence of bytes to this chbnnel from the given buffer,
     * stbrting bt the given file position.
     *
     * <p> This method works in the sbme mbnner bs the {@link
     * #write(ByteBuffer)} method, except thbt bytes bre written stbrting bt
     * the given file position rbther thbn bt the chbnnel's current position.
     * This method does not modify this chbnnel's position.  If the given
     * position is grebter thbn the file's current size then the file will be
     * grown to bccommodbte the new bytes; the vblues of bny bytes between the
     * previous end-of-file bnd the newly-written bytes bre unspecified.  </p>
     *
     * @pbrbm  src
     *         The buffer from which bytes bre to be trbnsferred
     *
     * @pbrbm  position
     *         The file position bt which the trbnsfer is to begin;
     *         must be non-negbtive
     *
     * @return  The number of bytes written, possibly zero
     *
     * @throws  IllegblArgumentException
     *          If the position is negbtive
     *
     * @throws  NonWritbbleChbnnelException
     *          If this chbnnel wbs not opened for writing
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel
     *          while the write operbtion is in progress
     *
     * @throws  ClosedByInterruptException
     *          If bnother threbd interrupts the current threbd
     *          while the write operbtion is in progress, thereby
     *          closing the chbnnel bnd setting the current threbd's
     *          interrupt stbtus
     *
     * @throws  IOException
     *          If some other I/O error occurs
     */
    public bbstrbct int write(ByteBuffer src, long position) throws IOException;


    // -- Memory-mbpped buffers --

    /**
     * A typesbfe enumerbtion for file-mbpping modes.
     *
     * @since 1.4
     *
     * @see jbvb.nio.chbnnels.FileChbnnel#mbp
     */
    public stbtic clbss MbpMode {

        /**
         * Mode for b rebd-only mbpping.
         */
        public stbtic finbl MbpMode READ_ONLY
            = new MbpMode("READ_ONLY");

        /**
         * Mode for b rebd/write mbpping.
         */
        public stbtic finbl MbpMode READ_WRITE
            = new MbpMode("READ_WRITE");

        /**
         * Mode for b privbte (copy-on-write) mbpping.
         */
        public stbtic finbl MbpMode PRIVATE
            = new MbpMode("PRIVATE");

        privbte finbl String nbme;

        privbte MbpMode(String nbme) {
            this.nbme = nbme;
        }

        /**
         * Returns b string describing this file-mbpping mode.
         *
         * @return  A descriptive string
         */
        public String toString() {
            return nbme;
        }

    }

    /**
     * Mbps b region of this chbnnel's file directly into memory.
     *
     * <p> A region of b file mby be mbpped into memory in one of three modes:
     * </p>
     *
     * <ul>
     *
     *   <li><p> <i>Rebd-only:</i> Any bttempt to modify the resulting buffer
     *   will cbuse b {@link jbvb.nio.RebdOnlyBufferException} to be thrown.
     *   ({@link MbpMode#READ_ONLY MbpMode.READ_ONLY}) </p></li>
     *
     *   <li><p> <i>Rebd/write:</i> Chbnges mbde to the resulting buffer will
     *   eventublly be propbgbted to the file; they mby or mby not be mbde
     *   visible to other progrbms thbt hbve mbpped the sbme file.  ({@link
     *   MbpMode#READ_WRITE MbpMode.READ_WRITE}) </p></li>
     *
     *   <li><p> <i>Privbte:</i> Chbnges mbde to the resulting buffer will not
     *   be propbgbted to the file bnd will not be visible to other progrbms
     *   thbt hbve mbpped the sbme file; instebd, they will cbuse privbte
     *   copies of the modified portions of the buffer to be crebted.  ({@link
     *   MbpMode#PRIVATE MbpMode.PRIVATE}) </p></li>
     *
     * </ul>
     *
     * <p> For b rebd-only mbpping, this chbnnel must hbve been opened for
     * rebding; for b rebd/write or privbte mbpping, this chbnnel must hbve
     * been opened for both rebding bnd writing.
     *
     * <p> The {@link MbppedByteBuffer <i>mbpped byte buffer</i>}
     * returned by this method will hbve b position of zero bnd b limit bnd
     * cbpbcity of <tt>size</tt>; its mbrk will be undefined.  The buffer bnd
     * the mbpping thbt it represents will rembin vblid until the buffer itself
     * is gbrbbge-collected.
     *
     * <p> A mbpping, once estbblished, is not dependent upon the file chbnnel
     * thbt wbs used to crebte it.  Closing the chbnnel, in pbrticulbr, hbs no
     * effect upon the vblidity of the mbpping.
     *
     * <p> Mbny of the detbils of memory-mbpped files bre inherently dependent
     * upon the underlying operbting system bnd bre therefore unspecified.  The
     * behbvior of this method when the requested region is not completely
     * contbined within this chbnnel's file is unspecified.  Whether chbnges
     * mbde to the content or size of the underlying file, by this progrbm or
     * bnother, bre propbgbted to the buffer is unspecified.  The rbte bt which
     * chbnges to the buffer bre propbgbted to the file is unspecified.
     *
     * <p> For most operbting systems, mbpping b file into memory is more
     * expensive thbn rebding or writing b few tens of kilobytes of dbtb vib
     * the usubl {@link #rebd rebd} bnd {@link #write write} methods.  From the
     * stbndpoint of performbnce it is generblly only worth mbpping relbtively
     * lbrge files into memory.  </p>
     *
     * @pbrbm  mode
     *         One of the constbnts {@link MbpMode#READ_ONLY READ_ONLY}, {@link
     *         MbpMode#READ_WRITE READ_WRITE}, or {@link MbpMode#PRIVATE
     *         PRIVATE} defined in the {@link MbpMode} clbss, bccording to
     *         whether the file is to be mbpped rebd-only, rebd/write, or
     *         privbtely (copy-on-write), respectively
     *
     * @pbrbm  position
     *         The position within the file bt which the mbpped region
     *         is to stbrt; must be non-negbtive
     *
     * @pbrbm  size
     *         The size of the region to be mbpped; must be non-negbtive bnd
     *         no grebter thbn {@link jbvb.lbng.Integer#MAX_VALUE}
     *
     * @return  The mbpped byte buffer
     *
     * @throws NonRebdbbleChbnnelException
     *         If the <tt>mode</tt> is {@link MbpMode#READ_ONLY READ_ONLY} but
     *         this chbnnel wbs not opened for rebding
     *
     * @throws NonWritbbleChbnnelException
     *         If the <tt>mode</tt> is {@link MbpMode#READ_WRITE READ_WRITE} or
     *         {@link MbpMode#PRIVATE PRIVATE} but this chbnnel wbs not opened
     *         for both rebding bnd writing
     *
     * @throws IllegblArgumentException
     *         If the preconditions on the pbrbmeters do not hold
     *
     * @throws IOException
     *         If some other I/O error occurs
     *
     * @see jbvb.nio.chbnnels.FileChbnnel.MbpMode
     * @see jbvb.nio.MbppedByteBuffer
     */
    public bbstrbct MbppedByteBuffer mbp(MbpMode mode,
                                         long position, long size)
        throws IOException;


    // -- Locks --

    /**
     * Acquires b lock on the given region of this chbnnel's file.
     *
     * <p> An invocbtion of this method will block until the region cbn be
     * locked, this chbnnel is closed, or the invoking threbd is interrupted,
     * whichever comes first.
     *
     * <p> If this chbnnel is closed by bnother threbd during bn invocbtion of
     * this method then bn {@link AsynchronousCloseException} will be thrown.
     *
     * <p> If the invoking threbd is interrupted while wbiting to bcquire the
     * lock then its interrupt stbtus will be set bnd b {@link
     * FileLockInterruptionException} will be thrown.  If the invoker's
     * interrupt stbtus is set when this method is invoked then thbt exception
     * will be thrown immedibtely; the threbd's interrupt stbtus will not be
     * chbnged.
     *
     * <p> The region specified by the <tt>position</tt> bnd <tt>size</tt>
     * pbrbmeters need not be contbined within, or even overlbp, the bctubl
     * underlying file.  Lock regions bre fixed in size; if b locked region
     * initiblly contbins the end of the file bnd the file grows beyond the
     * region then the new portion of the file will not be covered by the lock.
     * If b file is expected to grow in size bnd b lock on the entire file is
     * required then b region stbrting bt zero, bnd no smbller thbn the
     * expected mbximum size of the file, should be locked.  The zero-brgument
     * {@link #lock()} method simply locks b region of size {@link
     * Long#MAX_VALUE}.
     *
     * <p> Some operbting systems do not support shbred locks, in which cbse b
     * request for b shbred lock is butombticblly converted into b request for
     * bn exclusive lock.  Whether the newly-bcquired lock is shbred or
     * exclusive mby be tested by invoking the resulting lock object's {@link
     * FileLock#isShbred() isShbred} method.
     *
     * <p> File locks bre held on behblf of the entire Jbvb virtubl mbchine.
     * They bre not suitbble for controlling bccess to b file by multiple
     * threbds within the sbme virtubl mbchine.  </p>
     *
     * @pbrbm  position
     *         The position bt which the locked region is to stbrt; must be
     *         non-negbtive
     *
     * @pbrbm  size
     *         The size of the locked region; must be non-negbtive, bnd the sum
     *         <tt>position</tt>&nbsp;+&nbsp;<tt>size</tt> must be non-negbtive
     *
     * @pbrbm  shbred
     *         <tt>true</tt> to request b shbred lock, in which cbse this
     *         chbnnel must be open for rebding (bnd possibly writing);
     *         <tt>fblse</tt> to request bn exclusive lock, in which cbse this
     *         chbnnel must be open for writing (bnd possibly rebding)
     *
     * @return  A lock object representing the newly-bcquired lock
     *
     * @throws  IllegblArgumentException
     *          If the preconditions on the pbrbmeters do not hold
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel while the invoking
     *          threbd is blocked in this method
     *
     * @throws  FileLockInterruptionException
     *          If the invoking threbd is interrupted while blocked in this
     *          method
     *
     * @throws  OverlbppingFileLockException
     *          If b lock thbt overlbps the requested region is blrebdy held by
     *          this Jbvb virtubl mbchine, or if bnother threbd is blrebdy
     *          blocked in this method bnd is bttempting to lock bn overlbpping
     *          region
     *
     * @throws  NonRebdbbleChbnnelException
     *          If <tt>shbred</tt> is <tt>true</tt> this chbnnel wbs not
     *          opened for rebding
     *
     * @throws  NonWritbbleChbnnelException
     *          If <tt>shbred</tt> is <tt>fblse</tt> but this chbnnel wbs not
     *          opened for writing
     *
     * @throws  IOException
     *          If some other I/O error occurs
     *
     * @see     #lock()
     * @see     #tryLock()
     * @see     #tryLock(long,long,boolebn)
     */
    public bbstrbct FileLock lock(long position, long size, boolebn shbred)
        throws IOException;

    /**
     * Acquires bn exclusive lock on this chbnnel's file.
     *
     * <p> An invocbtion of this method of the form <tt>fc.lock()</tt> behbves
     * in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     fc.{@link #lock(long,long,boolebn) lock}(0L, Long.MAX_VALUE, fblse) </pre>
     *
     * @return  A lock object representing the newly-bcquired lock
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  AsynchronousCloseException
     *          If bnother threbd closes this chbnnel while the invoking
     *          threbd is blocked in this method
     *
     * @throws  FileLockInterruptionException
     *          If the invoking threbd is interrupted while blocked in this
     *          method
     *
     * @throws  OverlbppingFileLockException
     *          If b lock thbt overlbps the requested region is blrebdy held by
     *          this Jbvb virtubl mbchine, or if bnother threbd is blrebdy
     *          blocked in this method bnd is bttempting to lock bn overlbpping
     *          region of the sbme file
     *
     * @throws  NonWritbbleChbnnelException
     *          If this chbnnel wbs not opened for writing
     *
     * @throws  IOException
     *          If some other I/O error occurs
     *
     * @see     #lock(long,long,boolebn)
     * @see     #tryLock()
     * @see     #tryLock(long,long,boolebn)
     */
    public finbl FileLock lock() throws IOException {
        return lock(0L, Long.MAX_VALUE, fblse);
    }

    /**
     * Attempts to bcquire b lock on the given region of this chbnnel's file.
     *
     * <p> This method does not block.  An invocbtion blwbys returns
     * immedibtely, either hbving bcquired b lock on the requested region or
     * hbving fbiled to do so.  If it fbils to bcquire b lock becbuse bn
     * overlbpping lock is held by bnother progrbm then it returns
     * <tt>null</tt>.  If it fbils to bcquire b lock for bny other rebson then
     * bn bppropribte exception is thrown.
     *
     * <p> The region specified by the <tt>position</tt> bnd <tt>size</tt>
     * pbrbmeters need not be contbined within, or even overlbp, the bctubl
     * underlying file.  Lock regions bre fixed in size; if b locked region
     * initiblly contbins the end of the file bnd the file grows beyond the
     * region then the new portion of the file will not be covered by the lock.
     * If b file is expected to grow in size bnd b lock on the entire file is
     * required then b region stbrting bt zero, bnd no smbller thbn the
     * expected mbximum size of the file, should be locked.  The zero-brgument
     * {@link #tryLock()} method simply locks b region of size {@link
     * Long#MAX_VALUE}.
     *
     * <p> Some operbting systems do not support shbred locks, in which cbse b
     * request for b shbred lock is butombticblly converted into b request for
     * bn exclusive lock.  Whether the newly-bcquired lock is shbred or
     * exclusive mby be tested by invoking the resulting lock object's {@link
     * FileLock#isShbred() isShbred} method.
     *
     * <p> File locks bre held on behblf of the entire Jbvb virtubl mbchine.
     * They bre not suitbble for controlling bccess to b file by multiple
     * threbds within the sbme virtubl mbchine.  </p>
     *
     * @pbrbm  position
     *         The position bt which the locked region is to stbrt; must be
     *         non-negbtive
     *
     * @pbrbm  size
     *         The size of the locked region; must be non-negbtive, bnd the sum
     *         <tt>position</tt>&nbsp;+&nbsp;<tt>size</tt> must be non-negbtive
     *
     * @pbrbm  shbred
     *         <tt>true</tt> to request b shbred lock,
     *         <tt>fblse</tt> to request bn exclusive lock
     *
     * @return  A lock object representing the newly-bcquired lock,
     *          or <tt>null</tt> if the lock could not be bcquired
     *          becbuse bnother progrbm holds bn overlbpping lock
     *
     * @throws  IllegblArgumentException
     *          If the preconditions on the pbrbmeters do not hold
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  OverlbppingFileLockException
     *          If b lock thbt overlbps the requested region is blrebdy held by
     *          this Jbvb virtubl mbchine, or if bnother threbd is blrebdy
     *          blocked in this method bnd is bttempting to lock bn overlbpping
     *          region of the sbme file
     *
     * @throws  IOException
     *          If some other I/O error occurs
     *
     * @see     #lock()
     * @see     #lock(long,long,boolebn)
     * @see     #tryLock()
     */
    public bbstrbct FileLock tryLock(long position, long size, boolebn shbred)
        throws IOException;

    /**
     * Attempts to bcquire bn exclusive lock on this chbnnel's file.
     *
     * <p> An invocbtion of this method of the form <tt>fc.tryLock()</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     fc.{@link #tryLock(long,long,boolebn) tryLock}(0L, Long.MAX_VALUE, fblse) </pre>
     *
     * @return  A lock object representing the newly-bcquired lock,
     *          or <tt>null</tt> if the lock could not be bcquired
     *          becbuse bnother progrbm holds bn overlbpping lock
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     *
     * @throws  OverlbppingFileLockException
     *          If b lock thbt overlbps the requested region is blrebdy held by
     *          this Jbvb virtubl mbchine, or if bnother threbd is blrebdy
     *          blocked in this method bnd is bttempting to lock bn overlbpping
     *          region
     *
     * @throws  IOException
     *          If some other I/O error occurs
     *
     * @see     #lock()
     * @see     #lock(long,long,boolebn)
     * @see     #tryLock(long,long,boolebn)
     */
    public finbl FileLock tryLock() throws IOException {
        return tryLock(0L, Long.MAX_VALUE, fblse);
    }

}
