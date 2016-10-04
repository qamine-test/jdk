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

import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.FileAttribute;
import jbvb.nio.file.spi.*;
import jbvb.nio.ByteBuffer;
import jbvb.io.IOException;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.ExecutorService;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.util.Collections;

/**
 * An bsynchronous chbnnel for rebding, writing, bnd mbnipulbting b file.
 *
 * <p> An bsynchronous file chbnnel is crebted when b file is opened by invoking
 * one of the {@link #open open} methods defined by this clbss. The file contbins
 * b vbribble-length sequence of bytes thbt cbn be rebd bnd written bnd whose
 * current size cbn be {@link #size() queried}. The size of the file increbses
 * when bytes bre written beyond its  current size; the size of the file decrebses
 * when it is {@link #truncbte truncbted}.
 *
 * <p> An bsynchronous file chbnnel does not hbve b <i>current position</i>
 * within the file. Instebd, the file position is specified to ebch rebd bnd
 * write method thbt initibtes bsynchronous operbtions. A {@link CompletionHbndler}
 * is specified bs b pbrbmeter bnd is invoked to consume the result of the I/O
 * operbtion. This clbss blso defines rebd bnd write methods thbt initibte
 * bsynchronous operbtions, returning b {@link Future} to represent the pending
 * result of the operbtion. The {@code Future} mby be used to check if the
 * operbtion hbs completed, wbit for its completion, bnd retrieve the result.
 *
 * <p> In bddition to rebd bnd write operbtions, this clbss defines the
 * following operbtions: </p>
 *
 * <ul>
 *
 *   <li><p> Updbtes mbde to b file mby be {@link #force <i>forced
 *   out</i>} to the underlying storbge device, ensuring thbt dbtb bre not
 *   lost in the event of b system crbsh.  </p></li>
 *
 *   <li><p> A region of b file mby be {@link #lock <i>locked</i>} bgbinst
 *   bccess by other progrbms.  </p></li>
 *
 * </ul>
 *
 * <p> An {@code AsynchronousFileChbnnel} is bssocibted with b threbd pool to
 * which tbsks bre submitted to hbndle I/O events bnd dispbtch to completion
 * hbndlers thbt consume the results of I/O operbtions on the chbnnel. The
 * completion hbndler for bn I/O operbtion initibted on b chbnnel is gubrbnteed
 * to be invoked by one of the threbds in the threbd pool (This ensures thbt the
 * completion hbndler is run by b threbd with the expected <em>identity</em>).
 * Where bn I/O operbtion completes immedibtely, bnd the initibting threbd is
 * itself b threbd in the threbd pool, then the completion hbndler mby be invoked
 * directly by the initibting threbd. When bn {@code AsynchronousFileChbnnel} is
 * crebted without specifying b threbd pool then the chbnnel is bssocibted with
 * b system-dependent defbult threbd pool thbt mby be shbred with other
 * chbnnels. The defbult threbd pool is configured by the system properties
 * defined by the {@link AsynchronousChbnnelGroup} clbss.
 *
 * <p> Chbnnels of this type bre sbfe for use by multiple concurrent threbds. The
 * {@link Chbnnel#close close} method mby be invoked bt bny time, bs specified
 * by the {@link Chbnnel} interfbce. This cbuses bll outstbnding bsynchronous
 * operbtions on the chbnnel to complete with the exception {@link
 * AsynchronousCloseException}. Multiple rebd bnd write operbtions mby be
 * outstbnding bt the sbme time. When multiple rebd bnd write operbtions bre
 * outstbnding then the ordering of the I/O operbtions, bnd the order thbt the
 * completion hbndlers bre invoked, is not specified; they bre not, in pbrticulbr,
 * gubrbnteed to execute in the order thbt the operbtions were initibted. The
 * {@link jbvb.nio.ByteBuffer ByteBuffers} used when rebding or writing bre not
 * sbfe for use by multiple concurrent I/O operbtions. Furthermore, bfter bn I/O
 * operbtion is initibted then cbre should be tbken to ensure thbt the buffer is
 * not bccessed until bfter the operbtion hbs completed.
 *
 * <p> As with {@link FileChbnnel}, the view of b file provided by bn instbnce of
 * this clbss is gubrbnteed to be consistent with other views of the sbme file
 * provided by other instbnces in the sbme progrbm.  The view provided by bn
 * instbnce of this clbss mby or mby not, however, be consistent with the views
 * seen by other concurrently-running progrbms due to cbching performed by the
 * underlying operbting system bnd delbys induced by network-filesystem protocols.
 * This is true regbrdless of the lbngubge in which these other progrbms bre
 * written, bnd whether they bre running on the sbme mbchine or on some other
 * mbchine.  The exbct nbture of bny such inconsistencies bre system-dependent
 * bnd bre therefore unspecified.
 *
 * @since 1.7
 */

public bbstrbct clbss AsynchronousFileChbnnel
    implements AsynchronousChbnnel
{
    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected AsynchronousFileChbnnel() {
    }

    /**
     * Opens or crebtes b file for rebding bnd/or writing, returning bn
     * bsynchronous file chbnnel to bccess the file.
     *
     * <p> The {@code options} pbrbmeter determines how the file is opened.
     * The {@link StbndbrdOpenOption#READ READ} bnd {@link StbndbrdOpenOption#WRITE
     * WRITE} options determines if the file should be opened for rebding bnd/or
     * writing. If neither option is contbined in the brrby then bn existing file
     * is opened for  rebding.
     *
     * <p> In bddition to {@code READ} bnd {@code WRITE}, the following options
     * mby be present:
     *
     * <tbble border=1 cellpbdding=5 summbry="">
     * <tr> <th>Option</th> <th>Description</th> </tr>
     * <tr>
     *   <td> {@link StbndbrdOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING} </td>
     *   <td> When opening bn existing file, the file is first truncbted to b
     *   size of 0 bytes. This option is ignored when the file is opened only
     *   for rebding.</td>
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
     * <p> The {@code executor} pbrbmeter is the {@link ExecutorService} to
     * which tbsks bre submitted to hbndle I/O events bnd dispbtch completion
     * results for operbtions initibted on resulting chbnnel.
     * The nbture of these tbsks is highly implementbtion specific bnd so cbre
     * should be tbken when configuring the {@code Executor}. Minimblly it
     * should support bn unbounded work queue bnd should not run tbsks on the
     * cbller threbd of the {@link ExecutorService#execute execute} method.
     * Shutting down the executor service while the chbnnel is open results in
     * unspecified behbvior.
     *
     * <p> The {@code bttrs} pbrbmeter is bn optionbl brrby of file {@link
     * FileAttribute file-bttributes} to set btomicblly when crebting the file.
     *
     * <p> The new chbnnel is crebted by invoking the {@link
     * FileSystemProvider#newFileChbnnel newFileChbnnel} method on the
     * provider thbt crebted the {@code Pbth}.
     *
     * @pbrbm   file
     *          The pbth of the file to open or crebte
     * @pbrbm   options
     *          Options specifying how the file is opened
     * @pbrbm   executor
     *          The threbd pool or {@code null} to bssocibte the chbnnel with
     *          the defbult threbd pool
     * @pbrbm   bttrs
     *          An optionbl list of file bttributes to set btomicblly when
     *          crebting the file
     *
     * @return  A new bsynchronous file chbnnel
     *
     * @throws  IllegblArgumentException
     *          If the set contbins bn invblid combinbtion of options
     * @throws  UnsupportedOperbtionException
     *          If the {@code file} is bssocibted with b provider thbt does not
     *          support crebting bsynchronous file chbnnels, or bn unsupported
     *          open option is specified, or the brrby contbins bn bttribute thbt
     *          cbnnot be set btomicblly when crebting the file
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
     */
    public stbtic AsynchronousFileChbnnel open(Pbth file,
                                               Set<? extends OpenOption> options,
                                               ExecutorService executor,
                                               FileAttribute<?>... bttrs)
        throws IOException
    {
        FileSystemProvider provider = file.getFileSystem().provider();
        return provider.newAsynchronousFileChbnnel(file, options, executor, bttrs);
    }

    @SuppressWbrnings({"unchecked", "rbwtypes"}) // generic brrby construction
    privbte stbtic finbl FileAttribute<?>[] NO_ATTRIBUTES = new FileAttribute[0];

    /**
     * Opens or crebtes b file for rebding bnd/or writing, returning bn
     * bsynchronous file chbnnel to bccess the file.
     *
     * <p> An invocbtion of this method behbves in exbctly the sbme wby bs the
     * invocbtion
     * <pre>
     *     ch.{@link #open(Pbth,Set,ExecutorService,FileAttribute[])
     *       open}(file, opts, null, new FileAttribute&lt;?&gt;[0]);
     * </pre>
     * where {@code opts} is b {@code Set} contbining the options specified to
     * this method.
     *
     * <p> The resulting chbnnel is bssocibted with defbult threbd pool to which
     * tbsks bre submitted to hbndle I/O events bnd dispbtch to completion
     * hbndlers thbt consume the result of bsynchronous operbtions performed on
     * the resulting chbnnel.
     *
     * @pbrbm   file
     *          The pbth of the file to open or crebte
     * @pbrbm   options
     *          Options specifying how the file is opened
     *
     * @return  A new bsynchronous file chbnnel
     *
     * @throws  IllegblArgumentException
     *          If the set contbins bn invblid combinbtion of options
     * @throws  UnsupportedOperbtionException
     *          If the {@code file} is bssocibted with b provider thbt does not
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
     */
    public stbtic AsynchronousFileChbnnel open(Pbth file, OpenOption... options)
        throws IOException
    {
        Set<OpenOption> set = new HbshSet<OpenOption>(options.length);
        Collections.bddAll(set, options);
        return open(file, set, null, NO_ATTRIBUTES);
    }

    /**
     * Returns the current size of this chbnnel's file.
     *
     * @return  The current size of this chbnnel's file, mebsured in bytes
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
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
     * the file is not modified. </p>
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
    public bbstrbct AsynchronousFileChbnnel truncbte(long size) throws IOException;

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
     * <p> The {@code metbDbtb} pbrbmeter cbn be used to limit the number of
     * I/O operbtions thbt this method is required to perform.  Pbssing
     * {@code fblse} for this pbrbmeter indicbtes thbt only updbtes to the
     * file's content need be written to storbge; pbssing {@code true}
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
     * this chbnnel's file vib the methods defined in this clbss.
     *
     * @pbrbm   metbDbtb
     *          If {@code true} then this method is required to force chbnges
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
     * Acquires b lock on the given region of this chbnnel's file.
     *
     * <p> This method initibtes bn operbtion to bcquire b lock on the given
     * region of this chbnnel's file. The {@code hbndler} pbrbmeter is b
     * completion hbndler thbt is invoked when the lock is bcquired (or the
     * operbtion fbils). The result pbssed to the completion hbndler is the
     * resulting {@code FileLock}.
     *
     * <p> The region specified by the {@code position} bnd {@code size}
     * pbrbmeters need not be contbined within, or even overlbp, the bctubl
     * underlying file.  Lock regions bre fixed in size; if b locked region
     * initiblly contbins the end of the file bnd the file grows beyond the
     * region then the new portion of the file will not be covered by the lock.
     * If b file is expected to grow in size bnd b lock on the entire file is
     * required then b region stbrting bt zero, bnd no smbller thbn the
     * expected mbximum size of the file, should be locked.  The two-brgument
     * {@link #lock(Object,CompletionHbndler)} method simply locks b region
     * of size {@link Long#MAX_VALUE}. If b lock thbt overlbps the requested
     * region is blrebdy held by this Jbvb virtubl mbchine, or this method hbs
     * been invoked to lock bn overlbpping region bnd thbt operbtion hbs not
     * completed, then this method throws {@link OverlbppingFileLockException}.
     *
     * <p> Some operbting systems do not support b mechbnism to bcquire b file
     * lock in bn bsynchronous mbnner. Consequently bn implementbtion mby
     * bcquire the file lock in b bbckground threbd or from b tbsk executed by
     * b threbd in the bssocibted threbd pool. If there bre mbny lock operbtions
     * outstbnding then it mby consume threbds in the Jbvb virtubl mbchine for
     * indefinite periods.
     *
     * <p> Some operbting systems do not support shbred locks, in which cbse b
     * request for b shbred lock is butombticblly converted into b request for
     * bn exclusive lock.  Whether the newly-bcquired lock is shbred or
     * exclusive mby be tested by invoking the resulting lock object's {@link
     * FileLock#isShbred() isShbred} method.
     *
     * <p> File locks bre held on behblf of the entire Jbvb virtubl mbchine.
     * They bre not suitbble for controlling bccess to b file by multiple
     * threbds within the sbme virtubl mbchine.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   position
     *          The position bt which the locked region is to stbrt; must be
     *          non-negbtive
     * @pbrbm   size
     *          The size of the locked region; must be non-negbtive, bnd the sum
     *          {@code position}&nbsp;+&nbsp;{@code size} must be non-negbtive
     * @pbrbm   shbred
     *          {@code true} to request b shbred lock, in which cbse this
     *          chbnnel must be open for rebding (bnd possibly writing);
     *          {@code fblse} to request bn exclusive lock, in which cbse this
     *          chbnnel must be open for writing (bnd possibly rebding)
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The hbndler for consuming the result
     *
     * @throws  OverlbppingFileLockException
     *          If b lock thbt overlbps the requested region is blrebdy held by
     *          this Jbvb virtubl mbchine, or there is blrebdy b pending bttempt
     *          to lock bn overlbpping region
     * @throws  IllegblArgumentException
     *          If the preconditions on the pbrbmeters do not hold
     * @throws  NonRebdbbleChbnnelException
     *          If {@code shbred} is true but this chbnnel wbs not opened for rebding
     * @throws  NonWritbbleChbnnelException
     *          If {@code shbred} is fblse but this chbnnel wbs not opened for writing
     */
    public bbstrbct <A> void lock(long position,
                                  long size,
                                  boolebn shbred,
                                  A bttbchment,
                                  CompletionHbndler<FileLock,? super A> hbndler);

    /**
     * Acquires bn exclusive lock on this chbnnel's file.
     *
     * <p> This method initibtes bn operbtion to bcquire b lock on the given
     * region of this chbnnel's file. The {@code hbndler} pbrbmeter is b
     * completion hbndler thbt is invoked when the lock is bcquired (or the
     * operbtion fbils). The result pbssed to the completion hbndler is the
     * resulting {@code FileLock}.
     *
     * <p> An invocbtion of this method of the form {@code ch.lock(btt,hbndler)}
     * behbves in exbctly the sbme wby bs the invocbtion
     * <pre>
     *     ch.{@link #lock(long,long,boolebn,Object,CompletionHbndler) lock}(0L, Long.MAX_VALUE, fblse, btt, hbndler)
     * </pre>
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The hbndler for consuming the result
     *
     * @throws  OverlbppingFileLockException
     *          If b lock is blrebdy held by this Jbvb virtubl mbchine, or there
     *          is blrebdy b pending bttempt to lock b region
     * @throws  NonWritbbleChbnnelException
     *          If this chbnnel wbs not opened for writing
     */
    public finbl <A> void lock(A bttbchment,
                               CompletionHbndler<FileLock,? super A> hbndler)
    {
        lock(0L, Long.MAX_VALUE, fblse, bttbchment, hbndler);
    }

    /**
     * Acquires b lock on the given region of this chbnnel's file.
     *
     * <p> This method initibtes bn operbtion to bcquire b lock on the given
     * region of this chbnnel's file.  The method behbves in exbctly the sbme
     * mbnner bs the {@link #lock(long, long, boolebn, Object, CompletionHbndler)}
     * method except thbt instebd of specifying b completion hbndler, this
     * method returns b {@code Future} representing the pending result. The
     * {@code Future}'s {@link Future#get() get} method returns the {@link
     * FileLock} on successful completion.
     *
     * @pbrbm   position
     *          The position bt which the locked region is to stbrt; must be
     *          non-negbtive
     * @pbrbm   size
     *          The size of the locked region; must be non-negbtive, bnd the sum
     *          {@code position}&nbsp;+&nbsp;{@code size} must be non-negbtive
     * @pbrbm   shbred
     *          {@code true} to request b shbred lock, in which cbse this
     *          chbnnel must be open for rebding (bnd possibly writing);
     *          {@code fblse} to request bn exclusive lock, in which cbse this
     *          chbnnel must be open for writing (bnd possibly rebding)
     *
     * @return  b {@code Future} object representing the pending result
     *
     * @throws  OverlbppingFileLockException
     *          If b lock is blrebdy held by this Jbvb virtubl mbchine, or there
     *          is blrebdy b pending bttempt to lock b region
     * @throws  IllegblArgumentException
     *          If the preconditions on the pbrbmeters do not hold
     * @throws  NonRebdbbleChbnnelException
     *          If {@code shbred} is true but this chbnnel wbs not opened for rebding
     * @throws  NonWritbbleChbnnelException
     *          If {@code shbred} is fblse but this chbnnel wbs not opened for writing
     */
    public bbstrbct Future<FileLock> lock(long position, long size, boolebn shbred);

    /**
     * Acquires bn exclusive lock on this chbnnel's file.
     *
     * <p> This method initibtes bn operbtion to bcquire bn exclusive lock on this
     * chbnnel's file. The method returns b {@code Future} representing the
     * pending result of the operbtion. The {@code Future}'s {@link Future#get()
     * get} method returns the {@link FileLock} on successful completion.
     *
     * <p> An invocbtion of this method behbves in exbctly the sbme wby bs the
     * invocbtion
     * <pre>
     *     ch.{@link #lock(long,long,boolebn) lock}(0L, Long.MAX_VALUE, fblse)
     * </pre>
     *
     * @return  b {@code Future} object representing the pending result
     *
     * @throws  OverlbppingFileLockException
     *          If b lock is blrebdy held by this Jbvb virtubl mbchine, or there
     *          is blrebdy b pending bttempt to lock b region
     * @throws  NonWritbbleChbnnelException
     *          If this chbnnel wbs not opened for writing
     */
    public finbl Future<FileLock> lock() {
        return lock(0L, Long.MAX_VALUE, fblse);
    }

    /**
     * Attempts to bcquire b lock on the given region of this chbnnel's file.
     *
     * <p> This method does not block. An invocbtion blwbys returns immedibtely,
     * either hbving bcquired b lock on the requested region or hbving fbiled to
     * do so.  If it fbils to bcquire b lock becbuse bn overlbpping lock is held
     * by bnother progrbm then it returns {@code null}.  If it fbils to bcquire
     * b lock for bny other rebson then bn bppropribte exception is thrown.
     *
     * @pbrbm  position
     *         The position bt which the locked region is to stbrt; must be
     *         non-negbtive
     *
     * @pbrbm  size
     *         The size of the locked region; must be non-negbtive, bnd the sum
     *         {@code position}&nbsp;+&nbsp;{@code size} must be non-negbtive
     *
     * @pbrbm  shbred
     *         {@code true} to request b shbred lock,
     *         {@code fblse} to request bn exclusive lock
     *
     * @return  A lock object representing the newly-bcquired lock,
     *          or {@code null} if the lock could not be bcquired
     *          becbuse bnother progrbm holds bn overlbpping lock
     *
     * @throws  IllegblArgumentException
     *          If the preconditions on the pbrbmeters do not hold
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  OverlbppingFileLockException
     *          If b lock thbt overlbps the requested region is blrebdy held by
     *          this Jbvb virtubl mbchine, or if bnother threbd is blrebdy
     *          blocked in this method bnd is bttempting to lock bn overlbpping
     *          region of the sbme file
     * @throws  NonRebdbbleChbnnelException
     *          If {@code shbred} is true but this chbnnel wbs not opened for rebding
     * @throws  NonWritbbleChbnnelException
     *          If {@code shbred} is fblse but this chbnnel wbs not opened for writing
     *
     * @throws  IOException
     *          If some other I/O error occurs
     *
     * @see     #lock(Object,CompletionHbndler)
     * @see     #lock(long,long,boolebn,Object,CompletionHbndler)
     * @see     #tryLock()
     */
    public bbstrbct FileLock tryLock(long position, long size, boolebn shbred)
        throws IOException;

    /**
     * Attempts to bcquire bn exclusive lock on this chbnnel's file.
     *
     * <p> An invocbtion of this method of the form {@code ch.tryLock()}
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     ch.{@link #tryLock(long,long,boolebn) tryLock}(0L, Long.MAX_VALUE, fblse) </pre>
     *
     * @return  A lock object representing the newly-bcquired lock,
     *          or {@code null} if the lock could not be bcquired
     *          becbuse bnother progrbm holds bn overlbpping lock
     *
     * @throws  ClosedChbnnelException
     *          If this chbnnel is closed
     * @throws  OverlbppingFileLockException
     *          If b lock thbt overlbps the requested region is blrebdy held by
     *          this Jbvb virtubl mbchine, or if bnother threbd is blrebdy
     *          blocked in this method bnd is bttempting to lock bn overlbpping
     *          region
     * @throws  NonWritbbleChbnnelException
     *          If {@code shbred} is fblse but this chbnnel wbs not opened for writing
     *
     * @throws  IOException
     *          If some other I/O error occurs
     *
     * @see     #lock(Object,CompletionHbndler)
     * @see     #lock(long,long,boolebn,Object,CompletionHbndler)
     * @see     #tryLock(long,long,boolebn)
     */
    public finbl FileLock tryLock() throws IOException {
        return tryLock(0L, Long.MAX_VALUE, fblse);
    }

    /**
     * Rebds b sequence of bytes from this chbnnel into the given buffer,
     * stbrting bt the given file position.
     *
     * <p> This method initibtes the rebding of b sequence of bytes from this
     * chbnnel into the given buffer, stbrting bt the given file position. The
     * result of the rebd is the number of bytes rebd or {@code -1} if the given
     * position is grebter thbn or equbl to the file's size bt the time thbt the
     * rebd is bttempted.
     *
     * <p> This method works in the sbme mbnner bs the {@link
     * AsynchronousByteChbnnel#rebd(ByteBuffer,Object,CompletionHbndler)}
     * method, except thbt bytes bre rebd stbrting bt the given file position.
     * If the given file position is grebter thbn the file's size bt the time
     * thbt the rebd is bttempted then no bytes bre rebd.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   dst
     *          The buffer into which bytes bre to be trbnsferred
     * @pbrbm   position
     *          The file position bt which the trbnsfer is to begin;
     *          must be non-negbtive
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The hbndler for consuming the result
     *
     * @throws  IllegblArgumentException
     *          If the position is negbtive or the buffer is rebd-only
     * @throws  NonRebdbbleChbnnelException
     *          If this chbnnel wbs not opened for rebding
     */
    public bbstrbct <A> void rebd(ByteBuffer dst,
                                  long position,
                                  A bttbchment,
                                  CompletionHbndler<Integer,? super A> hbndler);

    /**
     * Rebds b sequence of bytes from this chbnnel into the given buffer,
     * stbrting bt the given file position.
     *
     * <p> This method initibtes the rebding of b sequence of bytes from this
     * chbnnel into the given buffer, stbrting bt the given file position. This
     * method returns b {@code Future} representing the pending result of the
     * operbtion. The {@code Future}'s {@link Future#get() get} method returns
     * the number of bytes rebd or {@code -1} if the given position is grebter
     * thbn or equbl to the file's size bt the time thbt the rebd is bttempted.
     *
     * <p> This method works in the sbme mbnner bs the {@link
     * AsynchronousByteChbnnel#rebd(ByteBuffer)} method, except thbt bytes bre
     * rebd stbrting bt the given file position. If the given file position is
     * grebter thbn the file's size bt the time thbt the rebd is bttempted then
     * no bytes bre rebd.
     *
     * @pbrbm   dst
     *          The buffer into which bytes bre to be trbnsferred
     * @pbrbm   position
     *          The file position bt which the trbnsfer is to begin;
     *          must be non-negbtive
     *
     * @return  A {@code Future} object representing the pending result
     *
     * @throws  IllegblArgumentException
     *          If the position is negbtive or the buffer is rebd-only
     * @throws  NonRebdbbleChbnnelException
     *          If this chbnnel wbs not opened for rebding
     */
    public bbstrbct Future<Integer> rebd(ByteBuffer dst, long position);

    /**
     * Writes b sequence of bytes to this chbnnel from the given buffer, stbrting
     * bt the given file position.
     *
     * <p> This method works in the sbme mbnner bs the {@link
     * AsynchronousByteChbnnel#write(ByteBuffer,Object,CompletionHbndler)}
     * method, except thbt bytes bre written stbrting bt the given file position.
     * If the given position is grebter thbn the file's size, bt the time thbt
     * the write is bttempted, then the file will be grown to bccommodbte the new
     * bytes; the vblues of bny bytes between the previous end-of-file bnd the
     * newly-written bytes bre unspecified.
     *
     * @pbrbm   <A>
     *          The type of the bttbchment
     * @pbrbm   src
     *          The buffer from which bytes bre to be trbnsferred
     * @pbrbm   position
     *          The file position bt which the trbnsfer is to begin;
     *          must be non-negbtive
     * @pbrbm   bttbchment
     *          The object to bttbch to the I/O operbtion; cbn be {@code null}
     * @pbrbm   hbndler
     *          The hbndler for consuming the result
     *
     * @throws  IllegblArgumentException
     *          If the position is negbtive
     * @throws  NonWritbbleChbnnelException
     *          If this chbnnel wbs not opened for writing
     */
    public bbstrbct <A> void write(ByteBuffer src,
                                   long position,
                                   A bttbchment,
                                   CompletionHbndler<Integer,? super A> hbndler);

    /**
     * Writes b sequence of bytes to this chbnnel from the given buffer, stbrting
     * bt the given file position.
     *
     * <p> This method initibtes the writing of b sequence of bytes to this
     * chbnnel from the given buffer, stbrting bt the given file position. The
     * method returns b {@code Future} representing the pending result of the
     * write operbtion. The {@code Future}'s {@link Future#get() get} method
     * returns the number of bytes written.
     *
     * <p> This method works in the sbme mbnner bs the {@link
     * AsynchronousByteChbnnel#write(ByteBuffer)} method, except thbt bytes bre
     * written stbrting bt the given file position. If the given position is
     * grebter thbn the file's size, bt the time thbt the write is bttempted,
     * then the file will be grown to bccommodbte the new bytes; the vblues of
     * bny bytes between the previous end-of-file bnd the newly-written bytes
     * bre unspecified.
     *
     * @pbrbm   src
     *          The buffer from which bytes bre to be trbnsferred
     * @pbrbm   position
     *          The file position bt which the trbnsfer is to begin;
     *          must be non-negbtive
     *
     * @return  A {@code Future} object representing the pending result
     *
     * @throws  IllegblArgumentException
     *          If the position is negbtive
     * @throws  NonWritbbleChbnnelException
     *          If this chbnnel wbs not opened for writing
     */
    public bbstrbct Future<Integer> write(ByteBuffer src, long position);
}
