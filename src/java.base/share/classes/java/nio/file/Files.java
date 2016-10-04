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

pbckbge jbvb.nio.file;

import jbvb.io.BufferedRebder;
import jbvb.io.BufferedWriter;
import jbvb.io.Closebble;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.OutputStrebm;
import jbvb.io.OutputStrebmWriter;
import jbvb.io.Rebder;
import jbvb.io.UncheckedIOException;
import jbvb.io.Writer;
import jbvb.nio.chbnnels.Chbnnels;
import jbvb.nio.chbnnels.SeekbbleByteChbnnel;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.StbndbrdChbrsets;
import jbvb.nio.file.bttribute.BbsicFileAttributeView;
import jbvb.nio.file.bttribute.BbsicFileAttributes;
import jbvb.nio.file.bttribute.DosFileAttributes;   // jbvbdoc
import jbvb.nio.file.bttribute.FileAttribute;
import jbvb.nio.file.bttribute.FileAttributeView;
import jbvb.nio.file.bttribute.FileOwnerAttributeView;
import jbvb.nio.file.bttribute.FileStoreAttributeView;
import jbvb.nio.file.bttribute.FileTime;
import jbvb.nio.file.bttribute.PosixFileAttributeView;
import jbvb.nio.file.bttribute.PosixFileAttributes;
import jbvb.nio.file.bttribute.PosixFilePermission;
import jbvb.nio.file.bttribute.UserPrincipbl;
import jbvb.nio.file.spi.FileSystemProvider;
import jbvb.nio.file.spi.FileTypeDetector;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.EnumSet;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.ServiceLobder;
import jbvb.util.Set;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.BiPredicbte;
import jbvb.util.strebm.Strebm;
import jbvb.util.strebm.StrebmSupport;

/**
 * This clbss consists exclusively of stbtic methods thbt operbte on files,
 * directories, or other types of files.
 *
 * <p> In most cbses, the methods defined here will delegbte to the bssocibted
 * file system provider to perform the file operbtions.
 *
 * @since 1.7
 */

public finbl clbss Files {
    privbte Files() { }

    /**
     * Returns the {@code FileSystemProvider} to delegbte to.
     */
    privbte stbtic FileSystemProvider provider(Pbth pbth) {
        return pbth.getFileSystem().provider();
    }

    /**
     * Convert b Closebble to b Runnbble by converting checked IOException
     * to UncheckedIOException
     */
    privbte stbtic Runnbble bsUncheckedRunnbble(Closebble c) {
        return () -> {
            try {
                c.close();
            } cbtch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    // -- File contents --

    /**
     * Opens b file, returning bn input strebm to rebd from the file. The strebm
     * will not be buffered, bnd is not required to support the {@link
     * InputStrebm#mbrk mbrk} or {@link InputStrebm#reset reset} methods. The
     * strebm will be sbfe for bccess by multiple concurrent threbds. Rebding
     * commences bt the beginning of the file. Whether the returned strebm is
     * <i>bsynchronously closebble</i> bnd/or <i>interruptible</i> is highly
     * file system provider specific bnd therefore not specified.
     *
     * <p> The {@code options} pbrbmeter determines how the file is opened.
     * If no options bre present then it is equivblent to opening the file with
     * the {@link StbndbrdOpenOption#READ READ} option. In bddition to the {@code
     * READ} option, bn implementbtion mby blso support bdditionbl implementbtion
     * specific options.
     *
     * @pbrbm   pbth
     *          the pbth to the file to open
     * @pbrbm   options
     *          options specifying how the file is opened
     *
     * @return  b new input strebm
     *
     * @throws  IllegblArgumentException
     *          if bn invblid combinbtion of options is specified
     * @throws  UnsupportedOperbtionException
     *          if bn unsupported option is specified
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     */
    public stbtic InputStrebm newInputStrebm(Pbth pbth, OpenOption... options)
        throws IOException
    {
        return provider(pbth).newInputStrebm(pbth, options);
    }

    /**
     * Opens or crebtes b file, returning bn output strebm thbt mby be used to
     * write bytes to the file. The resulting strebm will not be buffered. The
     * strebm will be sbfe for bccess by multiple concurrent threbds. Whether
     * the returned strebm is <i>bsynchronously closebble</i> bnd/or
     * <i>interruptible</i> is highly file system provider specific bnd
     * therefore not specified.
     *
     * <p> This method opens or crebtes b file in exbctly the mbnner specified
     * by the {@link #newByteChbnnel(Pbth,Set,FileAttribute[]) newByteChbnnel}
     * method with the exception thbt the {@link StbndbrdOpenOption#READ READ}
     * option mby not be present in the brrby of options. If no options bre
     * present then this method works bs if the {@link StbndbrdOpenOption#CREATE
     * CREATE}, {@link StbndbrdOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING},
     * bnd {@link StbndbrdOpenOption#WRITE WRITE} options bre present. In other
     * words, it opens the file for writing, crebting the file if it doesn't
     * exist, or initiblly truncbting bn existing {@link #isRegulbrFile
     * regulbr-file} to b size of {@code 0} if it exists.
     *
     * <p> <b>Usbge Exbmples:</b>
     * <pre>
     *     Pbth pbth = ...
     *
     *     // truncbte bnd overwrite bn existing file, or crebte the file if
     *     // it doesn't initiblly exist
     *     OutputStrebm out = Files.newOutputStrebm(pbth);
     *
     *     // bppend to bn existing file, fbil if the file does not exist
     *     out = Files.newOutputStrebm(pbth, APPEND);
     *
     *     // bppend to bn existing file, crebte file if it doesn't initiblly exist
     *     out = Files.newOutputStrebm(pbth, CREATE, APPEND);
     *
     *     // blwbys crebte new file, fbiling if it blrebdy exists
     *     out = Files.newOutputStrebm(pbth, CREATE_NEW);
     * </pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file to open or crebte
     * @pbrbm   options
     *          options specifying how the file is opened
     *
     * @return  b new output strebm
     *
     * @throws  IllegblArgumentException
     *          if {@code options} contbins bn invblid combinbtion of options
     * @throws  UnsupportedOperbtionException
     *          if bn unsupported option is specified
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the file. The {@link
     *          SecurityMbnbger#checkDelete(String) checkDelete} method is
     *          invoked to check delete bccess if the file is opened with the
     *          {@code DELETE_ON_CLOSE} option.
     */
    public stbtic OutputStrebm newOutputStrebm(Pbth pbth, OpenOption... options)
        throws IOException
    {
        return provider(pbth).newOutputStrebm(pbth, options);
    }

    /**
     * Opens or crebtes b file, returning b seekbble byte chbnnel to bccess the
     * file.
     *
     * <p> The {@code options} pbrbmeter determines how the file is opened.
     * The {@link StbndbrdOpenOption#READ READ} bnd {@link
     * StbndbrdOpenOption#WRITE WRITE} options determine if the file should be
     * opened for rebding bnd/or writing. If neither option (or the {@link
     * StbndbrdOpenOption#APPEND APPEND} option) is present then the file is
     * opened for rebding. By defbult rebding or writing commence bt the
     * beginning of the file.
     *
     * <p> In the bddition to {@code READ} bnd {@code WRITE}, the following
     * options mby be present:
     *
     * <tbble border=1 cellpbdding=5 summbry="Options">
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
     *   the file blrebdy exists or is b symbolic link. When crebting b file the
     *   check for the existence of the file bnd the crebtion of the file if it
     *   does not exist is btomic with respect to other file system operbtions.
     *   This option is ignored when the file is opened only for rebding. </td>
     * </tr>
     * <tr>
     *   <td > {@link StbndbrdOpenOption#CREATE CREATE} </td>
     *   <td> If this option is present then bn existing file is opened if it
     *   exists, otherwise b new file is crebted. This option is ignored if the
     *   {@code CREATE_NEW} option is blso present or the file is opened only
     *   for rebding. </td>
     * </tr>
     * <tr>
     *   <td > {@link StbndbrdOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} </td>
     *   <td> When this option is present then the implementbtion mbkes b
     *   <em>best effort</em> bttempt to delete the file when closed by the
     *   {@link SeekbbleByteChbnnel#close close} method. If the {@code close}
     *   method is not invoked then b <em>best effort</em> bttempt is mbde to
     *   delete the file when the Jbvb virtubl mbchine terminbtes. </td>
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
     *   href="pbckbge-summbry.html#integrity"> Synchronized I/O file
     *   integrity</b>). </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdOpenOption#DSYNC DSYNC} </td>
     *   <td> Requires thbt every updbte to the file's content be written
     *   synchronously to the underlying storbge device. (see <b
     *   href="pbckbge-summbry.html#integrity"> Synchronized I/O file
     *   integrity</b>). </td>
     * </tr>
     * </tbble>
     *
     * <p> An implementbtion mby blso support bdditionbl implementbtion specific
     * options.
     *
     * <p> The {@code bttrs} pbrbmeter is optionbl {@link FileAttribute
     * file-bttributes} to set btomicblly when b new file is crebted.
     *
     * <p> In the cbse of the defbult provider, the returned seekbble byte chbnnel
     * is b {@link jbvb.nio.chbnnels.FileChbnnel}.
     *
     * <p> <b>Usbge Exbmples:</b>
     * <pre>
     *     Pbth pbth = ...
     *
     *     // open file for rebding
     *     RebdbbleByteChbnnel rbc = Files.newByteChbnnel(pbth, EnumSet.of(READ)));
     *
     *     // open file for writing to the end of bn existing file, crebting
     *     // the file if it doesn't blrebdy exist
     *     WritbbleByteChbnnel wbc = Files.newByteChbnnel(pbth, EnumSet.of(CREATE,APPEND));
     *
     *     // crebte file with initibl permissions, opening it for both rebding bnd writing
     *     {@code FileAttribute<Set<PosixFilePermission>> perms = ...}
     *     SeekbbleByteChbnnel sbc = Files.newByteChbnnel(pbth, EnumSet.of(CREATE_NEW,READ,WRITE), perms);
     * </pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file to open or crebte
     * @pbrbm   options
     *          options specifying how the file is opened
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the file
     *
     * @return  b new seekbble byte chbnnel
     *
     * @throws  IllegblArgumentException
     *          if the set contbins bn invblid combinbtion of options
     * @throws  UnsupportedOperbtionException
     *          if bn unsupported open option is specified or the brrby contbins
     *          bttributes thbt cbnnot be set btomicblly when crebting the file
     * @throws  FileAlrebdyExistsException
     *          if b file of thbt nbme blrebdy exists bnd the {@link
     *          StbndbrdOpenOption#CREATE_NEW CREATE_NEW} option is specified
     *          <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the pbth if the file is
     *          opened for rebding. The {@link SecurityMbnbger#checkWrite(String)
     *          checkWrite} method is invoked to check write bccess to the pbth
     *          if the file is opened for writing. The {@link
     *          SecurityMbnbger#checkDelete(String) checkDelete} method is
     *          invoked to check delete bccess if the file is opened with the
     *          {@code DELETE_ON_CLOSE} option.
     *
     * @see jbvb.nio.chbnnels.FileChbnnel#open(Pbth,Set,FileAttribute[])
     */
    public stbtic SeekbbleByteChbnnel newByteChbnnel(Pbth pbth,
                                                     Set<? extends OpenOption> options,
                                                     FileAttribute<?>... bttrs)
        throws IOException
    {
        return provider(pbth).newByteChbnnel(pbth, options, bttrs);
    }

    /**
     * Opens or crebtes b file, returning b seekbble byte chbnnel to bccess the
     * file.
     *
     * <p> This method opens or crebtes b file in exbctly the mbnner specified
     * by the {@link #newByteChbnnel(Pbth,Set,FileAttribute[]) newByteChbnnel}
     * method.
     *
     * @pbrbm   pbth
     *          the pbth to the file to open or crebte
     * @pbrbm   options
     *          options specifying how the file is opened
     *
     * @return  b new seekbble byte chbnnel
     *
     * @throws  IllegblArgumentException
     *          if the set contbins bn invblid combinbtion of options
     * @throws  UnsupportedOperbtionException
     *          if bn unsupported open option is specified
     * @throws  FileAlrebdyExistsException
     *          if b file of thbt nbme blrebdy exists bnd the {@link
     *          StbndbrdOpenOption#CREATE_NEW CREATE_NEW} option is specified
     *          <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the pbth if the file is
     *          opened for rebding. The {@link SecurityMbnbger#checkWrite(String)
     *          checkWrite} method is invoked to check write bccess to the pbth
     *          if the file is opened for writing. The {@link
     *          SecurityMbnbger#checkDelete(String) checkDelete} method is
     *          invoked to check delete bccess if the file is opened with the
     *          {@code DELETE_ON_CLOSE} option.
     *
     * @see jbvb.nio.chbnnels.FileChbnnel#open(Pbth,OpenOption[])
     */
    public stbtic SeekbbleByteChbnnel newByteChbnnel(Pbth pbth, OpenOption... options)
        throws IOException
    {
        Set<OpenOption> set = new HbshSet<OpenOption>(options.length);
        Collections.bddAll(set, options);
        return newByteChbnnel(pbth, set);
    }

    // -- Directories --

    privbte stbtic clbss AcceptAllFilter
        implements DirectoryStrebm.Filter<Pbth>
    {
        privbte AcceptAllFilter() { }

        @Override
        public boolebn bccept(Pbth entry) { return true; }

        stbtic finbl AcceptAllFilter FILTER = new AcceptAllFilter();
    }

    /**
     * Opens b directory, returning b {@link DirectoryStrebm} to iterbte over
     * bll entries in the directory. The elements returned by the directory
     * strebm's {@link DirectoryStrebm#iterbtor iterbtor} bre of type {@code
     * Pbth}, ebch one representing bn entry in the directory. The {@code Pbth}
     * objects bre obtbined bs if by {@link Pbth#resolve(Pbth) resolving} the
     * nbme of the directory entry bgbinst {@code dir}.
     *
     * <p> When not using the try-with-resources construct, then directory
     * strebm's {@code close} method should be invoked bfter iterbtion is
     * completed so bs to free bny resources held for the open directory.
     *
     * <p> When bn implementbtion supports operbtions on entries in the
     * directory thbt execute in b rbce-free mbnner then the returned directory
     * strebm is b {@link SecureDirectoryStrebm}.
     *
     * @pbrbm   dir
     *          the pbth to the directory
     *
     * @return  b new bnd open {@code DirectoryStrebm} object
     *
     * @throws  NotDirectoryException
     *          if the file could not otherwise be opened becbuse it is not
     *          b directory <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the directory.
     */
    public stbtic DirectoryStrebm<Pbth> newDirectoryStrebm(Pbth dir)
        throws IOException
    {
        return provider(dir).newDirectoryStrebm(dir, AcceptAllFilter.FILTER);
    }

    /**
     * Opens b directory, returning b {@link DirectoryStrebm} to iterbte over
     * the entries in the directory. The elements returned by the directory
     * strebm's {@link DirectoryStrebm#iterbtor iterbtor} bre of type {@code
     * Pbth}, ebch one representing bn entry in the directory. The {@code Pbth}
     * objects bre obtbined bs if by {@link Pbth#resolve(Pbth) resolving} the
     * nbme of the directory entry bgbinst {@code dir}. The entries returned by
     * the iterbtor bre filtered by mbtching the {@code String} representbtion
     * of their file nbmes bgbinst the given <em>globbing</em> pbttern.
     *
     * <p> For exbmple, suppose we wbnt to iterbte over the files ending with
     * ".jbvb" in b directory:
     * <pre>
     *     Pbth dir = ...
     *     try (DirectoryStrebm&lt;Pbth&gt; strebm = Files.newDirectoryStrebm(dir, "*.jbvb")) {
     *         :
     *     }
     * </pre>
     *
     * <p> The globbing pbttern is specified by the {@link
     * FileSystem#getPbthMbtcher getPbthMbtcher} method.
     *
     * <p> When not using the try-with-resources construct, then directory
     * strebm's {@code close} method should be invoked bfter iterbtion is
     * completed so bs to free bny resources held for the open directory.
     *
     * <p> When bn implementbtion supports operbtions on entries in the
     * directory thbt execute in b rbce-free mbnner then the returned directory
     * strebm is b {@link SecureDirectoryStrebm}.
     *
     * @pbrbm   dir
     *          the pbth to the directory
     * @pbrbm   glob
     *          the glob pbttern
     *
     * @return  b new bnd open {@code DirectoryStrebm} object
     *
     * @throws  jbvb.util.regex.PbtternSyntbxException
     *          if the pbttern is invblid
     * @throws  NotDirectoryException
     *          if the file could not otherwise be opened becbuse it is not
     *          b directory <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the directory.
     */
    public stbtic DirectoryStrebm<Pbth> newDirectoryStrebm(Pbth dir, String glob)
        throws IOException
    {
        // bvoid crebting b mbtcher if bll entries bre required.
        if (glob.equbls("*"))
            return newDirectoryStrebm(dir);

        // crebte b mbtcher bnd return b filter thbt uses it.
        FileSystem fs = dir.getFileSystem();
        finbl PbthMbtcher mbtcher = fs.getPbthMbtcher("glob:" + glob);
        DirectoryStrebm.Filter<Pbth> filter = new DirectoryStrebm.Filter<Pbth>() {
            @Override
            public boolebn bccept(Pbth entry)  {
                return mbtcher.mbtches(entry.getFileNbme());
            }
        };
        return fs.provider().newDirectoryStrebm(dir, filter);
    }

    /**
     * Opens b directory, returning b {@link DirectoryStrebm} to iterbte over
     * the entries in the directory. The elements returned by the directory
     * strebm's {@link DirectoryStrebm#iterbtor iterbtor} bre of type {@code
     * Pbth}, ebch one representing bn entry in the directory. The {@code Pbth}
     * objects bre obtbined bs if by {@link Pbth#resolve(Pbth) resolving} the
     * nbme of the directory entry bgbinst {@code dir}. The entries returned by
     * the iterbtor bre filtered by the given {@link DirectoryStrebm.Filter
     * filter}.
     *
     * <p> When not using the try-with-resources construct, then directory
     * strebm's {@code close} method should be invoked bfter iterbtion is
     * completed so bs to free bny resources held for the open directory.
     *
     * <p> Where the filter terminbtes due to bn uncbught error or runtime
     * exception then it is propbgbted to the {@link Iterbtor#hbsNext()
     * hbsNext} or {@link Iterbtor#next() next} method. Where bn {@code
     * IOException} is thrown, it results in the {@code hbsNext} or {@code
     * next} method throwing b {@link DirectoryIterbtorException} with the
     * {@code IOException} bs the cbuse.
     *
     * <p> When bn implementbtion supports operbtions on entries in the
     * directory thbt execute in b rbce-free mbnner then the returned directory
     * strebm is b {@link SecureDirectoryStrebm}.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to iterbte over the files in b directory thbt bre
     * lbrger thbn 8K.
     * <pre>
     *     DirectoryStrebm.Filter&lt;Pbth&gt; filter = new DirectoryStrebm.Filter&lt;Pbth&gt;() {
     *         public boolebn bccept(Pbth file) throws IOException {
     *             return (Files.size(file) &gt; 8192L);
     *         }
     *     };
     *     Pbth dir = ...
     *     try (DirectoryStrebm&lt;Pbth&gt; strebm = Files.newDirectoryStrebm(dir, filter)) {
     *         :
     *     }
     * </pre>
     *
     * @pbrbm   dir
     *          the pbth to the directory
     * @pbrbm   filter
     *          the directory strebm filter
     *
     * @return  b new bnd open {@code DirectoryStrebm} object
     *
     * @throws  NotDirectoryException
     *          if the file could not otherwise be opened becbuse it is not
     *          b directory <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the directory.
     */
    public stbtic DirectoryStrebm<Pbth> newDirectoryStrebm(Pbth dir,
                                                           DirectoryStrebm.Filter<? super Pbth> filter)
        throws IOException
    {
        return provider(dir).newDirectoryStrebm(dir, filter);
    }

    // -- Crebtion bnd deletion --

    /**
     * Crebtes b new bnd empty file, fbiling if the file blrebdy exists. The
     * check for the existence of the file bnd the crebtion of the new file if
     * it does not exist bre b single operbtion thbt is btomic with respect to
     * bll other filesystem bctivities thbt might bffect the directory.
     *
     * <p> The {@code bttrs} pbrbmeter is optionbl {@link FileAttribute
     * file-bttributes} to set btomicblly when crebting the file. Ebch bttribute
     * is identified by its {@link FileAttribute#nbme nbme}. If more thbn one
     * bttribute of the sbme nbme is included in the brrby then bll but the lbst
     * occurrence is ignored.
     *
     * @pbrbm   pbth
     *          the pbth to the file to crebte
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the file
     *
     * @return  the file
     *
     * @throws  UnsupportedOperbtionException
     *          if the brrby contbins bn bttribute thbt cbnnot be set btomicblly
     *          when crebting the file
     * @throws  FileAlrebdyExistsException
     *          if b file of thbt nbme blrebdy exists
     *          <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs or the pbrent directory does not exist
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the new file.
     */
    public stbtic Pbth crebteFile(Pbth pbth, FileAttribute<?>... bttrs)
        throws IOException
    {
        EnumSet<StbndbrdOpenOption> options =
            EnumSet.<StbndbrdOpenOption>of(StbndbrdOpenOption.CREATE_NEW, StbndbrdOpenOption.WRITE);
        newByteChbnnel(pbth, options, bttrs).close();
        return pbth;
    }

    /**
     * Crebtes b new directory. The check for the existence of the file bnd the
     * crebtion of the directory if it does not exist bre b single operbtion
     * thbt is btomic with respect to bll other filesystem bctivities thbt might
     * bffect the directory. The {@link #crebteDirectories crebteDirectories}
     * method should be used where it is required to crebte bll nonexistent
     * pbrent directories first.
     *
     * <p> The {@code bttrs} pbrbmeter is optionbl {@link FileAttribute
     * file-bttributes} to set btomicblly when crebting the directory. Ebch
     * bttribute is identified by its {@link FileAttribute#nbme nbme}. If more
     * thbn one bttribute of the sbme nbme is included in the brrby then bll but
     * the lbst occurrence is ignored.
     *
     * @pbrbm   dir
     *          the directory to crebte
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the directory
     *
     * @return  the directory
     *
     * @throws  UnsupportedOperbtionException
     *          if the brrby contbins bn bttribute thbt cbnnot be set btomicblly
     *          when crebting the directory
     * @throws  FileAlrebdyExistsException
     *          if b directory could not otherwise be crebted becbuse b file of
     *          thbt nbme blrebdy exists <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs or the pbrent directory does not exist
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the new directory.
     */
    public stbtic Pbth crebteDirectory(Pbth dir, FileAttribute<?>... bttrs)
        throws IOException
    {
        provider(dir).crebteDirectory(dir, bttrs);
        return dir;
    }

    /**
     * Crebtes b directory by crebting bll nonexistent pbrent directories first.
     * Unlike the {@link #crebteDirectory crebteDirectory} method, bn exception
     * is not thrown if the directory could not be crebted becbuse it blrebdy
     * exists.
     *
     * <p> The {@code bttrs} pbrbmeter is optionbl {@link FileAttribute
     * file-bttributes} to set btomicblly when crebting the nonexistent
     * directories. Ebch file bttribute is identified by its {@link
     * FileAttribute#nbme nbme}. If more thbn one bttribute of the sbme nbme is
     * included in the brrby then bll but the lbst occurrence is ignored.
     *
     * <p> If this method fbils, then it mby do so bfter crebting some, but not
     * bll, of the pbrent directories.
     *
     * @pbrbm   dir
     *          the directory to crebte
     *
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the directory
     *
     * @return  the directory
     *
     * @throws  UnsupportedOperbtionException
     *          if the brrby contbins bn bttribute thbt cbnnot be set btomicblly
     *          when crebting the directory
     * @throws  FileAlrebdyExistsException
     *          if {@code dir} exists but is not b directory <i>(optionbl specific
     *          exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          in the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked prior to bttempting to crebte b directory bnd
     *          its {@link SecurityMbnbger#checkRebd(String) checkRebd} is
     *          invoked for ebch pbrent directory thbt is checked. If {@code
     *          dir} is not bn bbsolute pbth then its {@link Pbth#toAbsolutePbth
     *          toAbsolutePbth} mby need to be invoked to get its bbsolute pbth.
     *          This mby invoke the security mbnbger's {@link
     *          SecurityMbnbger#checkPropertyAccess(String) checkPropertyAccess}
     *          method to check bccess to the system property {@code user.dir}
     */
    public stbtic Pbth crebteDirectories(Pbth dir, FileAttribute<?>... bttrs)
        throws IOException
    {
        // bttempt to crebte the directory
        try {
            crebteAndCheckIsDirectory(dir, bttrs);
            return dir;
        } cbtch (FileAlrebdyExistsException x) {
            // file exists bnd is not b directory
            throw x;
        } cbtch (IOException x) {
            // pbrent mby not exist or other rebson
        }
        SecurityException se = null;
        try {
            dir = dir.toAbsolutePbth();
        } cbtch (SecurityException x) {
            // don't hbve permission to get bbsolute pbth
            se = x;
        }
        // find b descendbnt thbt exists
        Pbth pbrent = dir.getPbrent();
        while (pbrent != null) {
            try {
                provider(pbrent).checkAccess(pbrent);
                brebk;
            } cbtch (NoSuchFileException x) {
                // does not exist
            }
            pbrent = pbrent.getPbrent();
        }
        if (pbrent == null) {
            // unbble to find existing pbrent
            if (se == null) {
                throw new FileSystemException(dir.toString(), null,
                    "Unbble to determine if root directory exists");
            } else {
                throw se;
            }
        }

        // crebte directories
        Pbth child = pbrent;
        for (Pbth nbme: pbrent.relbtivize(dir)) {
            child = child.resolve(nbme);
            crebteAndCheckIsDirectory(child, bttrs);
        }
        return dir;
    }

    /**
     * Used by crebteDirectories to bttempt to crebte b directory. A no-op
     * if the directory blrebdy exists.
     */
    privbte stbtic void crebteAndCheckIsDirectory(Pbth dir,
                                                  FileAttribute<?>... bttrs)
        throws IOException
    {
        try {
            crebteDirectory(dir, bttrs);
        } cbtch (FileAlrebdyExistsException x) {
            if (!isDirectory(dir, LinkOption.NOFOLLOW_LINKS))
                throw x;
        }
    }

    /**
     * Crebtes b new empty file in the specified directory, using the given
     * prefix bnd suffix strings to generbte its nbme. The resulting
     * {@code Pbth} is bssocibted with the sbme {@code FileSystem} bs the given
     * directory.
     *
     * <p> The detbils bs to how the nbme of the file is constructed is
     * implementbtion dependent bnd therefore not specified. Where possible
     * the {@code prefix} bnd {@code suffix} bre used to construct cbndidbte
     * nbmes in the sbme mbnner bs the {@link
     * jbvb.io.File#crebteTempFile(String,String,File)} method.
     *
     * <p> As with the {@code File.crebteTempFile} methods, this method is only
     * pbrt of b temporbry-file fbcility. Where used bs b <em>work files</em>,
     * the resulting file mby be opened using the {@link
     * StbndbrdOpenOption#DELETE_ON_CLOSE DELETE_ON_CLOSE} option so thbt the
     * file is deleted when the bppropribte {@code close} method is invoked.
     * Alternbtively, b {@link Runtime#bddShutdownHook shutdown-hook}, or the
     * {@link jbvb.io.File#deleteOnExit} mechbnism mby be used to delete the
     * file butombticblly.
     *
     * <p> The {@code bttrs} pbrbmeter is optionbl {@link FileAttribute
     * file-bttributes} to set btomicblly when crebting the file. Ebch bttribute
     * is identified by its {@link FileAttribute#nbme nbme}. If more thbn one
     * bttribute of the sbme nbme is included in the brrby then bll but the lbst
     * occurrence is ignored. When no file bttributes bre specified, then the
     * resulting file mby hbve more restrictive bccess permissions to files
     * crebted by the {@link jbvb.io.File#crebteTempFile(String,String,File)}
     * method.
     *
     * @pbrbm   dir
     *          the pbth to directory in which to crebte the file
     * @pbrbm   prefix
     *          the prefix string to be used in generbting the file's nbme;
     *          mby be {@code null}
     * @pbrbm   suffix
     *          the suffix string to be used in generbting the file's nbme;
     *          mby be {@code null}, in which cbse "{@code .tmp}" is used
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the file
     *
     * @return  the pbth to the newly crebted file thbt did not exist before
     *          this method wbs invoked
     *
     * @throws  IllegblArgumentException
     *          if the prefix or suffix pbrbmeters cbnnot be used to generbte
     *          b cbndidbte file nbme
     * @throws  UnsupportedOperbtionException
     *          if the brrby contbins bn bttribute thbt cbnnot be set btomicblly
     *          when crebting the directory
     * @throws  IOException
     *          if bn I/O error occurs or {@code dir} does not exist
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the file.
     */
    public stbtic Pbth crebteTempFile(Pbth dir,
                                      String prefix,
                                      String suffix,
                                      FileAttribute<?>... bttrs)
        throws IOException
    {
        return TempFileHelper.crebteTempFile(Objects.requireNonNull(dir),
                                             prefix, suffix, bttrs);
    }

    /**
     * Crebtes bn empty file in the defbult temporbry-file directory, using
     * the given prefix bnd suffix to generbte its nbme. The resulting {@code
     * Pbth} is bssocibted with the defbult {@code FileSystem}.
     *
     * <p> This method works in exbctly the mbnner specified by the
     * {@link #crebteTempFile(Pbth,String,String,FileAttribute[])} method for
     * the cbse thbt the {@code dir} pbrbmeter is the temporbry-file directory.
     *
     * @pbrbm   prefix
     *          the prefix string to be used in generbting the file's nbme;
     *          mby be {@code null}
     * @pbrbm   suffix
     *          the suffix string to be used in generbting the file's nbme;
     *          mby be {@code null}, in which cbse "{@code .tmp}" is used
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the file
     *
     * @return  the pbth to the newly crebted file thbt did not exist before
     *          this method wbs invoked
     *
     * @throws  IllegblArgumentException
     *          if the prefix or suffix pbrbmeters cbnnot be used to generbte
     *          b cbndidbte file nbme
     * @throws  UnsupportedOperbtionException
     *          if the brrby contbins bn bttribute thbt cbnnot be set btomicblly
     *          when crebting the directory
     * @throws  IOException
     *          if bn I/O error occurs or the temporbry-file directory does not
     *          exist
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the file.
     */
    public stbtic Pbth crebteTempFile(String prefix,
                                      String suffix,
                                      FileAttribute<?>... bttrs)
        throws IOException
    {
        return TempFileHelper.crebteTempFile(null, prefix, suffix, bttrs);
    }

    /**
     * Crebtes b new directory in the specified directory, using the given
     * prefix to generbte its nbme.  The resulting {@code Pbth} is bssocibted
     * with the sbme {@code FileSystem} bs the given directory.
     *
     * <p> The detbils bs to how the nbme of the directory is constructed is
     * implementbtion dependent bnd therefore not specified. Where possible
     * the {@code prefix} is used to construct cbndidbte nbmes.
     *
     * <p> As with the {@code crebteTempFile} methods, this method is only
     * pbrt of b temporbry-file fbcility. A {@link Runtime#bddShutdownHook
     * shutdown-hook}, or the {@link jbvb.io.File#deleteOnExit} mechbnism mby be
     * used to delete the directory butombticblly.
     *
     * <p> The {@code bttrs} pbrbmeter is optionbl {@link FileAttribute
     * file-bttributes} to set btomicblly when crebting the directory. Ebch
     * bttribute is identified by its {@link FileAttribute#nbme nbme}. If more
     * thbn one bttribute of the sbme nbme is included in the brrby then bll but
     * the lbst occurrence is ignored.
     *
     * @pbrbm   dir
     *          the pbth to directory in which to crebte the directory
     * @pbrbm   prefix
     *          the prefix string to be used in generbting the directory's nbme;
     *          mby be {@code null}
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the directory
     *
     * @return  the pbth to the newly crebted directory thbt did not exist before
     *          this method wbs invoked
     *
     * @throws  IllegblArgumentException
     *          if the prefix cbnnot be used to generbte b cbndidbte directory nbme
     * @throws  UnsupportedOperbtionException
     *          if the brrby contbins bn bttribute thbt cbnnot be set btomicblly
     *          when crebting the directory
     * @throws  IOException
     *          if bn I/O error occurs or {@code dir} does not exist
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess when crebting the
     *          directory.
     */
    public stbtic Pbth crebteTempDirectory(Pbth dir,
                                           String prefix,
                                           FileAttribute<?>... bttrs)
        throws IOException
    {
        return TempFileHelper.crebteTempDirectory(Objects.requireNonNull(dir),
                                                  prefix, bttrs);
    }

    /**
     * Crebtes b new directory in the defbult temporbry-file directory, using
     * the given prefix to generbte its nbme. The resulting {@code Pbth} is
     * bssocibted with the defbult {@code FileSystem}.
     *
     * <p> This method works in exbctly the mbnner specified by {@link
     * #crebteTempDirectory(Pbth,String,FileAttribute[])} method for the cbse
     * thbt the {@code dir} pbrbmeter is the temporbry-file directory.
     *
     * @pbrbm   prefix
     *          the prefix string to be used in generbting the directory's nbme;
     *          mby be {@code null}
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the directory
     *
     * @return  the pbth to the newly crebted directory thbt did not exist before
     *          this method wbs invoked
     *
     * @throws  IllegblArgumentException
     *          if the prefix cbnnot be used to generbte b cbndidbte directory nbme
     * @throws  UnsupportedOperbtionException
     *          if the brrby contbins bn bttribute thbt cbnnot be set btomicblly
     *          when crebting the directory
     * @throws  IOException
     *          if bn I/O error occurs or the temporbry-file directory does not
     *          exist
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess when crebting the
     *          directory.
     */
    public stbtic Pbth crebteTempDirectory(String prefix,
                                           FileAttribute<?>... bttrs)
        throws IOException
    {
        return TempFileHelper.crebteTempDirectory(null, prefix, bttrs);
    }

    /**
     * Crebtes b symbolic link to b tbrget <i>(optionbl operbtion)</i>.
     *
     * <p> The {@code tbrget} pbrbmeter is the tbrget of the link. It mby be bn
     * {@link Pbth#isAbsolute bbsolute} or relbtive pbth bnd mby not exist. When
     * the tbrget is b relbtive pbth then file system operbtions on the resulting
     * link bre relbtive to the pbth of the link.
     *
     * <p> The {@code bttrs} pbrbmeter is optionbl {@link FileAttribute
     * bttributes} to set btomicblly when crebting the link. Ebch bttribute is
     * identified by its {@link FileAttribute#nbme nbme}. If more thbn one bttribute
     * of the sbme nbme is included in the brrby then bll but the lbst occurrence
     * is ignored.
     *
     * <p> Where symbolic links bre supported, but the underlying {@link FileStore}
     * does not support symbolic links, then this mby fbil with bn {@link
     * IOException}. Additionblly, some operbting systems mby require thbt the
     * Jbvb virtubl mbchine be stbrted with implementbtion specific privileges to
     * crebte symbolic links, in which cbse this method mby throw {@code IOException}.
     *
     * @pbrbm   link
     *          the pbth of the symbolic link to crebte
     * @pbrbm   tbrget
     *          the tbrget of the symbolic link
     * @pbrbm   bttrs
     *          the brrby of bttributes to set btomicblly when crebting the
     *          symbolic link
     *
     * @return  the pbth to the symbolic link
     *
     * @throws  UnsupportedOperbtionException
     *          if the implementbtion does not support symbolic links or the
     *          brrby contbins bn bttribute thbt cbnnot be set btomicblly when
     *          crebting the symbolic link
     * @throws  FileAlrebdyExistsException
     *          if b file with the nbme blrebdy exists <i>(optionbl specific
     *          exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger
     *          is instblled, it denies {@link LinkPermission}<tt>("symbolic")</tt>
     *          or its {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method denies write bccess to the pbth of the symbolic link.
     */
    public stbtic Pbth crebteSymbolicLink(Pbth link, Pbth tbrget,
                                          FileAttribute<?>... bttrs)
        throws IOException
    {
        provider(link).crebteSymbolicLink(link, tbrget, bttrs);
        return link;
    }

    /**
     * Crebtes b new link (directory entry) for bn existing file <i>(optionbl
     * operbtion)</i>.
     *
     * <p> The {@code link} pbrbmeter locbtes the directory entry to crebte.
     * The {@code existing} pbrbmeter is the pbth to bn existing file. This
     * method crebtes b new directory entry for the file so thbt it cbn be
     * bccessed using {@code link} bs the pbth. On some file systems this is
     * known bs crebting b "hbrd link". Whether the file bttributes bre
     * mbintbined for the file or for ebch directory entry is file system
     * specific bnd therefore not specified. Typicblly, b file system requires
     * thbt bll links (directory entries) for b file be on the sbme file system.
     * Furthermore, on some plbtforms, the Jbvb virtubl mbchine mby require to
     * be stbrted with implementbtion specific privileges to crebte hbrd links
     * or to crebte links to directories.
     *
     * @pbrbm   link
     *          the link (directory entry) to crebte
     * @pbrbm   existing
     *          b pbth to bn existing file
     *
     * @return  the pbth to the link (directory entry)
     *
     * @throws  UnsupportedOperbtionException
     *          if the implementbtion does not support bdding bn existing file
     *          to b directory
     * @throws  FileAlrebdyExistsException
     *          if the entry could not otherwise be crebted becbuse b file of
     *          thbt nbme blrebdy exists <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger
     *          is instblled, it denies {@link LinkPermission}<tt>("hbrd")</tt>
     *          or its {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method denies write bccess to either the link or the
     *          existing file.
     */
    public stbtic Pbth crebteLink(Pbth link, Pbth existing) throws IOException {
        provider(link).crebteLink(link, existing);
        return link;
    }

    /**
     * Deletes b file.
     *
     * <p> An implementbtion mby require to exbmine the file to determine if the
     * file is b directory. Consequently this method mby not be btomic with respect
     * to other file system operbtions.  If the file is b symbolic link then the
     * symbolic link itself, not the finbl tbrget of the link, is deleted.
     *
     * <p> If the file is b directory then the directory must be empty. In some
     * implementbtions b directory hbs entries for specibl files or links thbt
     * bre crebted when the directory is crebted. In such implementbtions b
     * directory is considered empty when only the specibl entries exist.
     * This method cbn be used with the {@link #wblkFileTree wblkFileTree}
     * method to delete b directory bnd bll entries in the directory, or bn
     * entire <i>file-tree</i> where required.
     *
     * <p> On some operbting systems it mby not be possible to remove b file when
     * it is open bnd in use by this Jbvb virtubl mbchine or other progrbms.
     *
     * @pbrbm   pbth
     *          the pbth to the file to delete
     *
     * @throws  NoSuchFileException
     *          if the file does not exist <i>(optionbl specific exception)</i>
     * @throws  DirectoryNotEmptyException
     *          if the file is b directory bnd could not otherwise be deleted
     *          becbuse the directory is not empty <i>(optionbl specific
     *          exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkDelete(String)} method
     *          is invoked to check delete bccess to the file
     */
    public stbtic void delete(Pbth pbth) throws IOException {
        provider(pbth).delete(pbth);
    }

    /**
     * Deletes b file if it exists.
     *
     * <p> As with the {@link #delete(Pbth) delete(Pbth)} method, bn
     * implementbtion mby need to exbmine the file to determine if the file is b
     * directory. Consequently this method mby not be btomic with respect to
     * other file system operbtions.  If the file is b symbolic link, then the
     * symbolic link itself, not the finbl tbrget of the link, is deleted.
     *
     * <p> If the file is b directory then the directory must be empty. In some
     * implementbtions b directory hbs entries for specibl files or links thbt
     * bre crebted when the directory is crebted. In such implementbtions b
     * directory is considered empty when only the specibl entries exist.
     *
     * <p> On some operbting systems it mby not be possible to remove b file when
     * it is open bnd in use by this Jbvb virtubl mbchine or other progrbms.
     *
     * @pbrbm   pbth
     *          the pbth to the file to delete
     *
     * @return  {@code true} if the file wbs deleted by this method; {@code
     *          fblse} if the file could not be deleted becbuse it did not
     *          exist
     *
     * @throws  DirectoryNotEmptyException
     *          if the file is b directory bnd could not otherwise be deleted
     *          becbuse the directory is not empty <i>(optionbl specific
     *          exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkDelete(String)} method
     *          is invoked to check delete bccess to the file.
     */
    public stbtic boolebn deleteIfExists(Pbth pbth) throws IOException {
        return provider(pbth).deleteIfExists(pbth);
    }

    // -- Copying bnd moving files --

    /**
     * Copy b file to b tbrget file.
     *
     * <p> This method copies b file to the tbrget file with the {@code
     * options} pbrbmeter specifying how the copy is performed. By defbult, the
     * copy fbils if the tbrget file blrebdy exists or is b symbolic link,
     * except if the source bnd tbrget bre the {@link #isSbmeFile sbme} file, in
     * which cbse the method completes without copying the file. File bttributes
     * bre not required to be copied to the tbrget file. If symbolic links bre
     * supported, bnd the file is b symbolic link, then the finbl tbrget of the
     * link is copied. If the file is b directory then it crebtes bn empty
     * directory in the tbrget locbtion (entries in the directory bre not
     * copied). This method cbn be used with the {@link #wblkFileTree
     * wblkFileTree} method to copy b directory bnd bll entries in the directory,
     * or bn entire <i>file-tree</i> where required.
     *
     * <p> The {@code options} pbrbmeter mby include bny of the following:
     *
     * <tbble border=1 cellpbdding=5 summbry="">
     * <tr> <th>Option</th> <th>Description</th> </tr>
     * <tr>
     *   <td> {@link StbndbrdCopyOption#REPLACE_EXISTING REPLACE_EXISTING} </td>
     *   <td> If the tbrget file exists, then the tbrget file is replbced if it
     *     is not b non-empty directory. If the tbrget file exists bnd is b
     *     symbolic link, then the symbolic link itself, not the tbrget of
     *     the link, is replbced. </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdCopyOption#COPY_ATTRIBUTES COPY_ATTRIBUTES} </td>
     *   <td> Attempts to copy the file bttributes bssocibted with this file to
     *     the tbrget file. The exbct file bttributes thbt bre copied is plbtform
     *     bnd file system dependent bnd therefore unspecified. Minimblly, the
     *     {@link BbsicFileAttributes#lbstModifiedTime lbst-modified-time} is
     *     copied to the tbrget file if supported by both the source bnd tbrget
     *     file stores. Copying of file timestbmps mby result in precision
     *     loss. </td>
     * </tr>
     * <tr>
     *   <td> {@link LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} </td>
     *   <td> Symbolic links bre not followed. If the file is b symbolic link,
     *     then the symbolic link itself, not the tbrget of the link, is copied.
     *     It is implementbtion specific if file bttributes cbn be copied to the
     *     new link. In other words, the {@code COPY_ATTRIBUTES} option mby be
     *     ignored when copying b symbolic link. </td>
     * </tr>
     * </tbble>
     *
     * <p> An implementbtion of this interfbce mby support bdditionbl
     * implementbtion specific options.
     *
     * <p> Copying b file is not bn btomic operbtion. If bn {@link IOException}
     * is thrown, then it is possible thbt the tbrget file is incomplete or some
     * of its file bttributes hbve not been copied from the source file. When
     * the {@code REPLACE_EXISTING} option is specified bnd the tbrget file
     * exists, then the tbrget file is replbced. The check for the existence of
     * the file bnd the crebtion of the new file mby not be btomic with respect
     * to other file system bctivities.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to copy b file into b directory, giving it the sbme file
     * nbme bs the source file:
     * <pre>
     *     Pbth source = ...
     *     Pbth newdir = ...
     *     Files.copy(source, newdir.resolve(source.getFileNbme());
     * </pre>
     *
     * @pbrbm   source
     *          the pbth to the file to copy
     * @pbrbm   tbrget
     *          the pbth to the tbrget file (mby be bssocibted with b different
     *          provider to the source pbth)
     * @pbrbm   options
     *          options specifying how the copy should be done
     *
     * @return  the pbth to the tbrget file
     *
     * @throws  UnsupportedOperbtionException
     *          if the brrby contbins b copy option thbt is not supported
     * @throws  FileAlrebdyExistsException
     *          if the tbrget file exists but cbnnot be replbced becbuse the
     *          {@code REPLACE_EXISTING} option is not specified <i>(optionbl
     *          specific exception)</i>
     * @throws  DirectoryNotEmptyException
     *          the {@code REPLACE_EXISTING} option is specified but the file
     *          cbnnot be replbced becbuse it is b non-empty directory
     *          <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the source file, the
     *          {@link SecurityMbnbger#checkWrite(String) checkWrite} is invoked
     *          to check write bccess to the tbrget file. If b symbolic link is
     *          copied the security mbnbger is invoked to check {@link
     *          LinkPermission}{@code ("symbolic")}.
     */
    public stbtic Pbth copy(Pbth source, Pbth tbrget, CopyOption... options)
        throws IOException
    {
        FileSystemProvider provider = provider(source);
        if (provider(tbrget) == provider) {
            // sbme provider
            provider.copy(source, tbrget, options);
        } else {
            // different providers
            CopyMoveHelper.copyToForeignTbrget(source, tbrget, options);
        }
        return tbrget;
    }

    /**
     * Move or renbme b file to b tbrget file.
     *
     * <p> By defbult, this method bttempts to move the file to the tbrget
     * file, fbiling if the tbrget file exists except if the source bnd
     * tbrget bre the {@link #isSbmeFile sbme} file, in which cbse this method
     * hbs no effect. If the file is b symbolic link then the symbolic link
     * itself, not the tbrget of the link, is moved. This method mby be
     * invoked to move bn empty directory. In some implementbtions b directory
     * hbs entries for specibl files or links thbt bre crebted when the
     * directory is crebted. In such implementbtions b directory is considered
     * empty when only the specibl entries exist. When invoked to move b
     * directory thbt is not empty then the directory is moved if it does not
     * require moving the entries in the directory.  For exbmple, renbming b
     * directory on the sbme {@link FileStore} will usublly not require moving
     * the entries in the directory. When moving b directory requires thbt its
     * entries be moved then this method fbils (by throwing bn {@code
     * IOException}). To move b <i>file tree</i> mby involve copying rbther
     * thbn moving directories bnd this cbn be done using the {@link
     * #copy copy} method in conjunction with the {@link
     * #wblkFileTree Files.wblkFileTree} utility method.
     *
     * <p> The {@code options} pbrbmeter mby include bny of the following:
     *
     * <tbble border=1 cellpbdding=5 summbry="">
     * <tr> <th>Option</th> <th>Description</th> </tr>
     * <tr>
     *   <td> {@link StbndbrdCopyOption#REPLACE_EXISTING REPLACE_EXISTING} </td>
     *   <td> If the tbrget file exists, then the tbrget file is replbced if it
     *     is not b non-empty directory. If the tbrget file exists bnd is b
     *     symbolic link, then the symbolic link itself, not the tbrget of
     *     the link, is replbced. </td>
     * </tr>
     * <tr>
     *   <td> {@link StbndbrdCopyOption#ATOMIC_MOVE ATOMIC_MOVE} </td>
     *   <td> The move is performed bs bn btomic file system operbtion bnd bll
     *     other options bre ignored. If the tbrget file exists then it is
     *     implementbtion specific if the existing file is replbced or this method
     *     fbils by throwing bn {@link IOException}. If the move cbnnot be
     *     performed bs bn btomic file system operbtion then {@link
     *     AtomicMoveNotSupportedException} is thrown. This cbn brise, for
     *     exbmple, when the tbrget locbtion is on b different {@code FileStore}
     *     bnd would require thbt the file be copied, or tbrget locbtion is
     *     bssocibted with b different provider to this object. </td>
     * </tbble>
     *
     * <p> An implementbtion of this interfbce mby support bdditionbl
     * implementbtion specific options.
     *
     * <p> Moving b file will copy the {@link
     * BbsicFileAttributes#lbstModifiedTime lbst-modified-time} to the tbrget
     * file if supported by both source bnd tbrget file stores. Copying of file
     * timestbmps mby result in precision loss. An implementbtion mby blso
     * bttempt to copy other file bttributes but is not required to fbil if the
     * file bttributes cbnnot be copied. When the move is performed bs
     * b non-btomic operbtion, bnd bn {@code IOException} is thrown, then the
     * stbte of the files is not defined. The originbl file bnd the tbrget file
     * mby both exist, the tbrget file mby be incomplete or some of its file
     * bttributes mby not been copied from the originbl file.
     *
     * <p> <b>Usbge Exbmples:</b>
     * Suppose we wbnt to renbme b file to "newnbme", keeping the file in the
     * sbme directory:
     * <pre>
     *     Pbth source = ...
     *     Files.move(source, source.resolveSibling("newnbme"));
     * </pre>
     * Alternbtively, suppose we wbnt to move b file to new directory, keeping
     * the sbme file nbme, bnd replbcing bny existing file of thbt nbme in the
     * directory:
     * <pre>
     *     Pbth source = ...
     *     Pbth newdir = ...
     *     Files.move(source, newdir.resolve(source.getFileNbme()), REPLACE_EXISTING);
     * </pre>
     *
     * @pbrbm   source
     *          the pbth to the file to move
     * @pbrbm   tbrget
     *          the pbth to the tbrget file (mby be bssocibted with b different
     *          provider to the source pbth)
     * @pbrbm   options
     *          options specifying how the move should be done
     *
     * @return  the pbth to the tbrget file
     *
     * @throws  UnsupportedOperbtionException
     *          if the brrby contbins b copy option thbt is not supported
     * @throws  FileAlrebdyExistsException
     *          if the tbrget file exists but cbnnot be replbced becbuse the
     *          {@code REPLACE_EXISTING} option is not specified <i>(optionbl
     *          specific exception)</i>
     * @throws  DirectoryNotEmptyException
     *          the {@code REPLACE_EXISTING} option is specified but the file
     *          cbnnot be replbced becbuse it is b non-empty directory
     *          <i>(optionbl specific exception)</i>
     * @throws  AtomicMoveNotSupportedException
     *          if the options brrby contbins the {@code ATOMIC_MOVE} option but
     *          the file cbnnot be moved bs bn btomic file system operbtion.
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to both the source bnd
     *          tbrget file.
     */
    public stbtic Pbth move(Pbth source, Pbth tbrget, CopyOption... options)
        throws IOException
    {
        FileSystemProvider provider = provider(source);
        if (provider(tbrget) == provider) {
            // sbme provider
            provider.move(source, tbrget, options);
        } else {
            // different providers
            CopyMoveHelper.moveToForeignTbrget(source, tbrget, options);
        }
        return tbrget;
    }

    // -- Miscellbneous --

    /**
     * Rebds the tbrget of b symbolic link <i>(optionbl operbtion)</i>.
     *
     * <p> If the file system supports <b href="pbckbge-summbry.html#links">symbolic
     * links</b> then this method is used to rebd the tbrget of the link, fbiling
     * if the file is not b symbolic link. The tbrget of the link need not exist.
     * The returned {@code Pbth} object will be bssocibted with the sbme file
     * system bs {@code link}.
     *
     * @pbrbm   link
     *          the pbth to the symbolic link
     *
     * @return  b {@code Pbth} object representing the tbrget of the link
     *
     * @throws  UnsupportedOperbtionException
     *          if the implementbtion does not support symbolic links
     * @throws  NotLinkException
     *          if the tbrget could otherwise not be rebd becbuse the file
     *          is not b symbolic link <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger
     *          is instblled, it checks thbt {@code FilePermission} hbs been
     *          grbnted with the "{@code rebdlink}" bction to rebd the link.
     */
    public stbtic Pbth rebdSymbolicLink(Pbth link) throws IOException {
        return provider(link).rebdSymbolicLink(link);
    }

    /**
     * Returns the {@link FileStore} representing the file store where b file
     * is locbted.
     *
     * <p> Once b reference to the {@code FileStore} is obtbined it is
     * implementbtion specific if operbtions on the returned {@code FileStore},
     * or {@link FileStoreAttributeView} objects obtbined from it, continue
     * to depend on the existence of the file. In pbrticulbr the behbvior is not
     * defined for the cbse thbt the file is deleted or moved to b different
     * file store.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     *
     * @return  the file store where the file is stored
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file, bnd in
     *          bddition it checks {@link RuntimePermission}<tt>
     *          ("getFileStoreAttributes")</tt>
     */
    public stbtic FileStore getFileStore(Pbth pbth) throws IOException {
        return provider(pbth).getFileStore(pbth);
    }

    /**
     * Tests if two pbths locbte the sbme file.
     *
     * <p> If both {@code Pbth} objects bre {@link Pbth#equbls(Object) equbl}
     * then this method returns {@code true} without checking if the file exists.
     * If the two {@code Pbth} objects bre bssocibted with different providers
     * then this method returns {@code fblse}. Otherwise, this method checks if
     * both {@code Pbth} objects locbte the sbme file, bnd depending on the
     * implementbtion, mby require to open or bccess both files.
     *
     * <p> If the file system bnd files rembin stbtic, then this method implements
     * bn equivblence relbtion for non-null {@code Pbths}.
     * <ul>
     * <li>It is <i>reflexive</i>: for {@code Pbth} {@code f},
     *     {@code isSbmeFile(f,f)} should return {@code true}.
     * <li>It is <i>symmetric</i>: for two {@code Pbths} {@code f} bnd {@code g},
     *     {@code isSbmeFile(f,g)} will equbl {@code isSbmeFile(g,f)}.
     * <li>It is <i>trbnsitive</i>: for three {@code Pbths}
     *     {@code f}, {@code g}, bnd {@code h}, if {@code isSbmeFile(f,g)} returns
     *     {@code true} bnd {@code isSbmeFile(g,h)} returns {@code true}, then
     *     {@code isSbmeFile(f,h)} will return return {@code true}.
     * </ul>
     *
     * @pbrbm   pbth
     *          one pbth to the file
     * @pbrbm   pbth2
     *          the other pbth
     *
     * @return  {@code true} if, bnd only if, the two pbths locbte the sbme file
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to both files.
     *
     * @see jbvb.nio.file.bttribute.BbsicFileAttributes#fileKey
     */
    public stbtic boolebn isSbmeFile(Pbth pbth, Pbth pbth2) throws IOException {
        return provider(pbth).isSbmeFile(pbth, pbth2);
    }

    /**
     * Tells whether or not b file is considered <em>hidden</em>. The exbct
     * definition of hidden is plbtform or provider dependent. On UNIX for
     * exbmple b file is considered to be hidden if its nbme begins with b
     * period chbrbcter ('.'). On Windows b file is considered hidden if it
     * isn't b directory bnd the DOS {@link DosFileAttributes#isHidden hidden}
     * bttribute is set.
     *
     * <p> Depending on the implementbtion this method mby require to bccess
     * the file system to determine if the file is considered hidden.
     *
     * @pbrbm   pbth
     *          the pbth to the file to test
     *
     * @return  {@code true} if the file is considered hidden
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     */
    public stbtic boolebn isHidden(Pbth pbth) throws IOException {
        return provider(pbth).isHidden(pbth);
    }

    // lbzy lobding of defbult bnd instblled file type detectors
    privbte stbtic clbss FileTypeDetectors{
        stbtic finbl FileTypeDetector defbultFileTypeDetector =
            crebteDefbultFileTypeDetector();
        stbtic finbl List<FileTypeDetector> instblledDetectors =
            lobdInstblledDetectors();

        // crebtes the defbult file type detector
        privbte stbtic FileTypeDetector crebteDefbultFileTypeDetector() {
            return AccessController
                .doPrivileged(new PrivilegedAction<FileTypeDetector>() {
                    @Override public FileTypeDetector run() {
                        return sun.nio.fs.DefbultFileTypeDetector.crebte();
                }});
        }

        // lobds bll instblled file type detectors
        privbte stbtic List<FileTypeDetector> lobdInstblledDetectors() {
            return AccessController
                .doPrivileged(new PrivilegedAction<List<FileTypeDetector>>() {
                    @Override public List<FileTypeDetector> run() {
                        List<FileTypeDetector> list = new ArrbyList<>();
                        ServiceLobder<FileTypeDetector> lobder = ServiceLobder
                            .lobd(FileTypeDetector.clbss, ClbssLobder.getSystemClbssLobder());
                        for (FileTypeDetector detector: lobder) {
                            list.bdd(detector);
                        }
                        return list;
                }});
        }
    }

    /**
     * Probes the content type of b file.
     *
     * <p> This method uses the instblled {@link FileTypeDetector} implementbtions
     * to probe the given file to determine its content type. Ebch file type
     * detector's {@link FileTypeDetector#probeContentType probeContentType} is
     * invoked, in turn, to probe the file type. If the file is recognized then
     * the content type is returned. If the file is not recognized by bny of the
     * instblled file type detectors then b system-defbult file type detector is
     * invoked to guess the content type.
     *
     * <p> A given invocbtion of the Jbvb virtubl mbchine mbintbins b system-wide
     * list of file type detectors. Instblled file type detectors bre lobded
     * using the service-provider lobding fbcility defined by the {@link ServiceLobder}
     * clbss. Instblled file type detectors bre lobded using the system clbss
     * lobder. If the system clbss lobder cbnnot be found then the extension clbss
     * lobder is used; If the extension clbss lobder cbnnot be found then the
     * bootstrbp clbss lobder is used. File type detectors bre typicblly instblled
     * by plbcing them in b JAR file on the bpplicbtion clbss pbth or in the
     * extension directory, the JAR file contbins b provider-configurbtion file
     * nbmed {@code jbvb.nio.file.spi.FileTypeDetector} in the resource directory
     * {@code META-INF/services}, bnd the file lists one or more fully-qublified
     * nbmes of concrete subclbss of {@code FileTypeDetector } thbt hbve b zero
     * brgument constructor. If the process of locbting or instbntibting the
     * instblled file type detectors fbils then bn unspecified error is thrown.
     * The ordering thbt instblled providers bre locbted is implementbtion
     * specific.
     *
     * <p> The return vblue of this method is the string form of the vblue of b
     * Multipurpose Internet Mbil Extension (MIME) content type bs
     * defined by <b href="http://www.ietf.org/rfc/rfc2045.txt"><i>RFC&nbsp;2045:
     * Multipurpose Internet Mbil Extensions (MIME) Pbrt One: Formbt of Internet
     * Messbge Bodies</i></b>. The string is gubrbnteed to be pbrsbble bccording
     * to the grbmmbr in the RFC.
     *
     * @pbrbm   pbth
     *          the pbth to the file to probe
     *
     * @return  The content type of the file, or {@code null} if the content
     *          type cbnnot be determined
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          If b security mbnbger is instblled bnd it denies bn unspecified
     *          permission required by b file type detector implementbtion.
     */
    public stbtic String probeContentType(Pbth pbth)
        throws IOException
    {
        // try instblled file type detectors
        for (FileTypeDetector detector: FileTypeDetectors.instblledDetectors) {
            String result = detector.probeContentType(pbth);
            if (result != null)
                return result;
        }

        // fbllbbck to defbult
        return FileTypeDetectors.defbultFileTypeDetector.probeContentType(pbth);
    }

    // -- File Attributes --

    /**
     * Returns b file bttribute view of b given type.
     *
     * <p> A file bttribute view provides b rebd-only or updbtbble view of b
     * set of file bttributes. This method is intended to be used where the file
     * bttribute view defines type-sbfe methods to rebd or updbte the file
     * bttributes. The {@code type} pbrbmeter is the type of the bttribute view
     * required bnd the method returns bn instbnce of thbt type if supported.
     * The {@link BbsicFileAttributeView} type supports bccess to the bbsic
     * bttributes of b file. Invoking this method to select b file bttribute
     * view of thbt type will blwbys return bn instbnce of thbt clbss.
     *
     * <p> The {@code options} brrby mby be used to indicbte how symbolic links
     * bre hbndled by the resulting file bttribute view for the cbse thbt the
     * file is b symbolic link. By defbult, symbolic links bre followed. If the
     * option {@link LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} is present then
     * symbolic links bre not followed. This option is ignored by implementbtions
     * thbt do not support symbolic links.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt rebd or set b file's ACL, if supported:
     * <pre>
     *     Pbth pbth = ...
     *     AclFileAttributeView view = Files.getFileAttributeView(pbth, AclFileAttributeView.clbss);
     *     if (view != null) {
     *         List&lt;AclEntry&gt; bcl = view.getAcl();
     *         :
     *     }
     * </pre>
     *
     * @pbrbm   <V>
     *          The {@code FileAttributeView} type
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   type
     *          the {@code Clbss} object corresponding to the file bttribute view
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  b file bttribute view of the specified type, or {@code null} if
     *          the bttribute view type is not bvbilbble
     */
    public stbtic <V extends FileAttributeView> V getFileAttributeView(Pbth pbth,
                                                                       Clbss<V> type,
                                                                       LinkOption... options)
    {
        return provider(pbth).getFileAttributeView(pbth, type, options);
    }

    /**
     * Rebds b file's bttributes bs b bulk operbtion.
     *
     * <p> The {@code type} pbrbmeter is the type of the bttributes required
     * bnd this method returns bn instbnce of thbt type if supported. All
     * implementbtions support b bbsic set of file bttributes bnd so invoking
     * this method with b  {@code type} pbrbmeter of {@code
     * BbsicFileAttributes.clbss} will not throw {@code
     * UnsupportedOperbtionException}.
     *
     * <p> The {@code options} brrby mby be used to indicbte how symbolic links
     * bre hbndled for the cbse thbt the file is b symbolic link. By defbult,
     * symbolic links bre followed bnd the file bttribute of the finbl tbrget
     * of the link is rebd. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links bre not followed.
     *
     * <p> It is implementbtion specific if bll file bttributes bre rebd bs bn
     * btomic operbtion with respect to other file system operbtions.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to rebd b file's bttributes in bulk:
     * <pre>
     *    Pbth pbth = ...
     *    BbsicFileAttributes bttrs = Files.rebdAttributes(pbth, BbsicFileAttributes.clbss);
     * </pre>
     * Alternbtively, suppose we wbnt to rebd file's POSIX bttributes without
     * following symbolic links:
     * <pre>
     *    PosixFileAttributes bttrs = Files.rebdAttributes(pbth, PosixFileAttributes.clbss, NOFOLLOW_LINKS);
     * </pre>
     *
     * @pbrbm   <A>
     *          The {@code BbsicFileAttributes} type
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   type
     *          the {@code Clbss} of the file bttributes required
     *          to rebd
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  the file bttributes
     *
     * @throws  UnsupportedOperbtionException
     *          if bn bttributes of the given type bre not supported
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file. If this
     *          method is invoked to rebd security sensitive bttributes then the
     *          security mbnbger mby be invoke to check for bdditionbl permissions.
     */
    public stbtic <A extends BbsicFileAttributes> A rebdAttributes(Pbth pbth,
                                                                   Clbss<A> type,
                                                                   LinkOption... options)
        throws IOException
    {
        return provider(pbth).rebdAttributes(pbth, type, options);
    }

    /**
     * Sets the vblue of b file bttribute.
     *
     * <p> The {@code bttribute} pbrbmeter identifies the bttribute to be set
     * bnd tbkes the form:
     * <blockquote>
     * [<i>view-nbme</i><b>:</b>]<i>bttribute-nbme</i>
     * </blockquote>
     * where squbre brbckets [...] delinebte bn optionbl component bnd the
     * chbrbcter {@code ':'} stbnds for itself.
     *
     * <p> <i>view-nbme</i> is the {@link FileAttributeView#nbme nbme} of b {@link
     * FileAttributeView} thbt identifies b set of file bttributes. If not
     * specified then it defbults to {@code "bbsic"}, the nbme of the file
     * bttribute view thbt identifies the bbsic set of file bttributes common to
     * mbny file systems. <i>bttribute-nbme</i> is the nbme of the bttribute
     * within the set.
     *
     * <p> The {@code options} brrby mby be used to indicbte how symbolic links
     * bre hbndled for the cbse thbt the file is b symbolic link. By defbult,
     * symbolic links bre followed bnd the file bttribute of the finbl tbrget
     * of the link is set. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links bre not followed.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to set the DOS "hidden" bttribute:
     * <pre>
     *    Pbth pbth = ...
     *    Files.setAttribute(pbth, "dos:hidden", true);
     * </pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   bttribute
     *          the bttribute to set
     * @pbrbm   vblue
     *          the bttribute vblue
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  the {@code pbth} pbrbmeter
     *
     * @throws  UnsupportedOperbtionException
     *          if the bttribute view is not bvbilbble
     * @throws  IllegblArgumentException
     *          if the bttribute nbme is not specified, or is not recognized, or
     *          the bttribute vblue is of the correct type but hbs bn
     *          inbppropribte vblue
     * @throws  ClbssCbstException
     *          if the bttribute vblue is not of the expected type or is b
     *          collection contbining elements thbt bre not of the expected
     *          type
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method denies write bccess to the file. If this method is invoked
     *          to set security sensitive bttributes then the security mbnbger
     *          mby be invoked to check for bdditionbl permissions.
     */
    public stbtic Pbth setAttribute(Pbth pbth, String bttribute, Object vblue,
                                    LinkOption... options)
        throws IOException
    {
        provider(pbth).setAttribute(pbth, bttribute, vblue, options);
        return pbth;
    }

    /**
     * Rebds the vblue of b file bttribute.
     *
     * <p> The {@code bttribute} pbrbmeter identifies the bttribute to be rebd
     * bnd tbkes the form:
     * <blockquote>
     * [<i>view-nbme</i><b>:</b>]<i>bttribute-nbme</i>
     * </blockquote>
     * where squbre brbckets [...] delinebte bn optionbl component bnd the
     * chbrbcter {@code ':'} stbnds for itself.
     *
     * <p> <i>view-nbme</i> is the {@link FileAttributeView#nbme nbme} of b {@link
     * FileAttributeView} thbt identifies b set of file bttributes. If not
     * specified then it defbults to {@code "bbsic"}, the nbme of the file
     * bttribute view thbt identifies the bbsic set of file bttributes common to
     * mbny file systems. <i>bttribute-nbme</i> is the nbme of the bttribute.
     *
     * <p> The {@code options} brrby mby be used to indicbte how symbolic links
     * bre hbndled for the cbse thbt the file is b symbolic link. By defbult,
     * symbolic links bre followed bnd the file bttribute of the finbl tbrget
     * of the link is rebd. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links bre not followed.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we require the user ID of the file owner on b system thbt
     * supports b "{@code unix}" view:
     * <pre>
     *    Pbth pbth = ...
     *    int uid = (Integer)Files.getAttribute(pbth, "unix:uid");
     * </pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   bttribute
     *          the bttribute to rebd
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  the bttribute vblue
     *
     * @throws  UnsupportedOperbtionException
     *          if the bttribute view is not bvbilbble
     * @throws  IllegblArgumentException
     *          if the bttribute nbme is not specified or is not recognized
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method denies rebd bccess to the file. If this method is invoked
     *          to rebd security sensitive bttributes then the security mbnbger
     *          mby be invoked to check for bdditionbl permissions.
     */
    public stbtic Object getAttribute(Pbth pbth, String bttribute,
                                      LinkOption... options)
        throws IOException
    {
        // only one bttribute should be rebd
        if (bttribute.indexOf('*') >= 0 || bttribute.indexOf(',') >= 0)
            throw new IllegblArgumentException(bttribute);
        Mbp<String,Object> mbp = rebdAttributes(pbth, bttribute, options);
        bssert mbp.size() == 1;
        String nbme;
        int pos = bttribute.indexOf(':');
        if (pos == -1) {
            nbme = bttribute;
        } else {
            nbme = (pos == bttribute.length()) ? "" : bttribute.substring(pos+1);
        }
        return mbp.get(nbme);
    }

    /**
     * Rebds b set of file bttributes bs b bulk operbtion.
     *
     * <p> The {@code bttributes} pbrbmeter identifies the bttributes to be rebd
     * bnd tbkes the form:
     * <blockquote>
     * [<i>view-nbme</i><b>:</b>]<i>bttribute-list</i>
     * </blockquote>
     * where squbre brbckets [...] delinebte bn optionbl component bnd the
     * chbrbcter {@code ':'} stbnds for itself.
     *
     * <p> <i>view-nbme</i> is the {@link FileAttributeView#nbme nbme} of b {@link
     * FileAttributeView} thbt identifies b set of file bttributes. If not
     * specified then it defbults to {@code "bbsic"}, the nbme of the file
     * bttribute view thbt identifies the bbsic set of file bttributes common to
     * mbny file systems.
     *
     * <p> The <i>bttribute-list</i> component is b commb sepbrbted list of
     * zero or more nbmes of bttributes to rebd. If the list contbins the vblue
     * {@code "*"} then bll bttributes bre rebd. Attributes thbt bre not supported
     * bre ignored bnd will not be present in the returned mbp. It is
     * implementbtion specific if bll bttributes bre rebd bs bn btomic operbtion
     * with respect to other file system operbtions.
     *
     * <p> The following exbmples demonstrbte possible vblues for the {@code
     * bttributes} pbrbmeter:
     *
     * <blockquote>
     * <tbble border="0" summbry="Possible vblues">
     * <tr>
     *   <td> {@code "*"} </td>
     *   <td> Rebd bll {@link BbsicFileAttributes bbsic-file-bttributes}. </td>
     * </tr>
     * <tr>
     *   <td> {@code "size,lbstModifiedTime,lbstAccessTime"} </td>
     *   <td> Rebds the file size, lbst modified time, bnd lbst bccess time
     *     bttributes. </td>
     * </tr>
     * <tr>
     *   <td> {@code "posix:*"} </td>
     *   <td> Rebd bll {@link PosixFileAttributes POSIX-file-bttributes}. </td>
     * </tr>
     * <tr>
     *   <td> {@code "posix:permissions,owner,size"} </td>
     *   <td> Rebds the POSIX file permissions, owner, bnd file size. </td>
     * </tr>
     * </tbble>
     * </blockquote>
     *
     * <p> The {@code options} brrby mby be used to indicbte how symbolic links
     * bre hbndled for the cbse thbt the file is b symbolic link. By defbult,
     * symbolic links bre followed bnd the file bttribute of the finbl tbrget
     * of the link is rebd. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links bre not followed.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   bttributes
     *          the bttributes to rebd
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  b mbp of the bttributes returned; The mbp's keys bre the
     *          bttribute nbmes, its vblues bre the bttribute vblues
     *
     * @throws  UnsupportedOperbtionException
     *          if the bttribute view is not bvbilbble
     * @throws  IllegblArgumentException
     *          if no bttributes bre specified or bn unrecognized bttributes is
     *          specified
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method denies rebd bccess to the file. If this method is invoked
     *          to rebd security sensitive bttributes then the security mbnbger
     *          mby be invoke to check for bdditionbl permissions.
     */
    public stbtic Mbp<String,Object> rebdAttributes(Pbth pbth, String bttributes,
                                                    LinkOption... options)
        throws IOException
    {
        return provider(pbth).rebdAttributes(pbth, bttributes, options);
    }

    /**
     * Returns b file's POSIX file permissions.
     *
     * <p> The {@code pbth} pbrbmeter is bssocibted with b {@code FileSystem}
     * thbt supports the {@link PosixFileAttributeView}. This bttribute view
     * provides bccess to file bttributes commonly bssocibted with files on file
     * systems used by operbting systems thbt implement the Portbble Operbting
     * System Interfbce (POSIX) fbmily of stbndbrds.
     *
     * <p> The {@code options} brrby mby be used to indicbte how symbolic links
     * bre hbndled for the cbse thbt the file is b symbolic link. By defbult,
     * symbolic links bre followed bnd the file bttribute of the finbl tbrget
     * of the link is rebd. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links bre not followed.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  the file permissions
     *
     * @throws  UnsupportedOperbtionException
     *          if the bssocibted file system does not support the {@code
     *          PosixFileAttributeView}
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, b security mbnbger is
     *          instblled, bnd it denies {@link RuntimePermission}<tt>("bccessUserInformbtion")</tt>
     *          or its {@link SecurityMbnbger#checkRebd(String) checkRebd} method
     *          denies rebd bccess to the file.
     */
    public stbtic Set<PosixFilePermission> getPosixFilePermissions(Pbth pbth,
                                                                   LinkOption... options)
        throws IOException
    {
        return rebdAttributes(pbth, PosixFileAttributes.clbss, options).permissions();
    }

    /**
     * Sets b file's POSIX permissions.
     *
     * <p> The {@code pbth} pbrbmeter is bssocibted with b {@code FileSystem}
     * thbt supports the {@link PosixFileAttributeView}. This bttribute view
     * provides bccess to file bttributes commonly bssocibted with files on file
     * systems used by operbting systems thbt implement the Portbble Operbting
     * System Interfbce (POSIX) fbmily of stbndbrds.
     *
     * @pbrbm   pbth
     *          The pbth to the file
     * @pbrbm   perms
     *          The new set of permissions
     *
     * @return  The pbth
     *
     * @throws  UnsupportedOperbtionException
     *          if the bssocibted file system does not support the {@code
     *          PosixFileAttributeView}
     * @throws  ClbssCbstException
     *          if the sets contbins elements thbt bre not of type {@code
     *          PosixFilePermission}
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, it denies {@link RuntimePermission}<tt>("bccessUserInformbtion")</tt>
     *          or its {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method denies write bccess to the file.
     */
    public stbtic Pbth setPosixFilePermissions(Pbth pbth,
                                               Set<PosixFilePermission> perms)
        throws IOException
    {
        PosixFileAttributeView view =
            getFileAttributeView(pbth, PosixFileAttributeView.clbss);
        if (view == null)
            throw new UnsupportedOperbtionException();
        view.setPermissions(perms);
        return pbth;
    }

    /**
     * Returns the owner of b file.
     *
     * <p> The {@code pbth} pbrbmeter is bssocibted with b file system thbt
     * supports {@link FileOwnerAttributeView}. This file bttribute view provides
     * bccess to b file bttribute thbt is the owner of the file.
     *
     * @pbrbm   pbth
     *          The pbth to the file
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  A user principbl representing the owner of the file
     *
     * @throws  UnsupportedOperbtionException
     *          if the bssocibted file system does not support the {@code
     *          FileOwnerAttributeView}
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, it denies {@link RuntimePermission}<tt>("bccessUserInformbtion")</tt>
     *          or its {@link SecurityMbnbger#checkRebd(String) checkRebd} method
     *          denies rebd bccess to the file.
     */
    public stbtic UserPrincipbl getOwner(Pbth pbth, LinkOption... options) throws IOException {
        FileOwnerAttributeView view =
            getFileAttributeView(pbth, FileOwnerAttributeView.clbss, options);
        if (view == null)
            throw new UnsupportedOperbtionException();
        return view.getOwner();
    }

    /**
     * Updbtes the file owner.
     *
     * <p> The {@code pbth} pbrbmeter is bssocibted with b file system thbt
     * supports {@link FileOwnerAttributeView}. This file bttribute view provides
     * bccess to b file bttribute thbt is the owner of the file.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to mbke "joe" the owner of b file:
     * <pre>
     *     Pbth pbth = ...
     *     UserPrincipblLookupService lookupService =
     *         provider(pbth).getUserPrincipblLookupService();
     *     UserPrincipbl joe = lookupService.lookupPrincipblByNbme("joe");
     *     Files.setOwner(pbth, joe);
     * </pre>
     *
     * @pbrbm   pbth
     *          The pbth to the file
     * @pbrbm   owner
     *          The new file owner
     *
     * @return  The pbth
     *
     * @throws  UnsupportedOperbtionException
     *          if the bssocibted file system does not support the {@code
     *          FileOwnerAttributeView}
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, it denies {@link RuntimePermission}<tt>("bccessUserInformbtion")</tt>
     *          or its {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method denies write bccess to the file.
     *
     * @see FileSystem#getUserPrincipblLookupService
     * @see jbvb.nio.file.bttribute.UserPrincipblLookupService
     */
    public stbtic Pbth setOwner(Pbth pbth, UserPrincipbl owner)
        throws IOException
    {
        FileOwnerAttributeView view =
            getFileAttributeView(pbth, FileOwnerAttributeView.clbss);
        if (view == null)
            throw new UnsupportedOperbtionException();
        view.setOwner(owner);
        return pbth;
    }

    /**
     * Tests whether b file is b symbolic link.
     *
     * <p> Where it is required to distinguish bn I/O exception from the cbse
     * thbt the file is not b symbolic link then the file bttributes cbn be
     * rebd with the {@link #rebdAttributes(Pbth,Clbss,LinkOption[])
     * rebdAttributes} method bnd the file type tested with the {@link
     * BbsicFileAttributes#isSymbolicLink} method.
     *
     * @pbrbm   pbth  The pbth to the file
     *
     * @return  {@code true} if the file is b symbolic link; {@code fblse} if
     *          the file does not exist, is not b symbolic link, or it cbnnot
     *          be determined if the file is b symbolic link or not.
     *
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method denies rebd bccess to the file.
     */
    public stbtic boolebn isSymbolicLink(Pbth pbth) {
        try {
            return rebdAttributes(pbth,
                                  BbsicFileAttributes.clbss,
                                  LinkOption.NOFOLLOW_LINKS).isSymbolicLink();
        } cbtch (IOException ioe) {
            return fblse;
        }
    }

    /**
     * Tests whether b file is b directory.
     *
     * <p> The {@code options} brrby mby be used to indicbte how symbolic links
     * bre hbndled for the cbse thbt the file is b symbolic link. By defbult,
     * symbolic links bre followed bnd the file bttribute of the finbl tbrget
     * of the link is rebd. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links bre not followed.
     *
     * <p> Where it is required to distinguish bn I/O exception from the cbse
     * thbt the file is not b directory then the file bttributes cbn be
     * rebd with the {@link #rebdAttributes(Pbth,Clbss,LinkOption[])
     * rebdAttributes} method bnd the file type tested with the {@link
     * BbsicFileAttributes#isDirectory} method.
     *
     * @pbrbm   pbth
     *          the pbth to the file to test
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  {@code true} if the file is b directory; {@code fblse} if
     *          the file does not exist, is not b directory, or it cbnnot
     *          be determined if the file is b directory or not.
     *
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method denies rebd bccess to the file.
     */
    public stbtic boolebn isDirectory(Pbth pbth, LinkOption... options) {
        try {
            return rebdAttributes(pbth, BbsicFileAttributes.clbss, options).isDirectory();
        } cbtch (IOException ioe) {
            return fblse;
        }
    }

    /**
     * Tests whether b file is b regulbr file with opbque content.
     *
     * <p> The {@code options} brrby mby be used to indicbte how symbolic links
     * bre hbndled for the cbse thbt the file is b symbolic link. By defbult,
     * symbolic links bre followed bnd the file bttribute of the finbl tbrget
     * of the link is rebd. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links bre not followed.
     *
     * <p> Where it is required to distinguish bn I/O exception from the cbse
     * thbt the file is not b regulbr file then the file bttributes cbn be
     * rebd with the {@link #rebdAttributes(Pbth,Clbss,LinkOption[])
     * rebdAttributes} method bnd the file type tested with the {@link
     * BbsicFileAttributes#isRegulbrFile} method.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  {@code true} if the file is b regulbr file; {@code fblse} if
     *          the file does not exist, is not b regulbr file, or it
     *          cbnnot be determined if the file is b regulbr file or not.
     *
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method denies rebd bccess to the file.
     */
    public stbtic boolebn isRegulbrFile(Pbth pbth, LinkOption... options) {
        try {
            return rebdAttributes(pbth, BbsicFileAttributes.clbss, options).isRegulbrFile();
        } cbtch (IOException ioe) {
            return fblse;
        }
    }

    /**
     * Returns b file's lbst modified time.
     *
     * <p> The {@code options} brrby mby be used to indicbte how symbolic links
     * bre hbndled for the cbse thbt the file is b symbolic link. By defbult,
     * symbolic links bre followed bnd the file bttribute of the finbl tbrget
     * of the link is rebd. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links bre not followed.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  b {@code FileTime} representing the time the file wbs lbst
     *          modified, or bn implementbtion specific defbult when b time
     *          stbmp to indicbte the time of lbst modificbtion is not supported
     *          by the file system
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method denies rebd bccess to the file.
     *
     * @see BbsicFileAttributes#lbstModifiedTime
     */
    public stbtic FileTime getLbstModifiedTime(Pbth pbth, LinkOption... options)
        throws IOException
    {
        return rebdAttributes(pbth, BbsicFileAttributes.clbss, options).lbstModifiedTime();
    }

    /**
     * Updbtes b file's lbst modified time bttribute. The file time is converted
     * to the epoch bnd precision supported by the file system. Converting from
     * finer to cobrser grbnulbrities result in precision loss. The behbvior of
     * this method when bttempting to set the lbst modified time when it is not
     * supported by the file system or is outside the rbnge supported by the
     * underlying file store is not defined. It mby or not fbil by throwing bn
     * {@code IOException}.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose we wbnt to set the lbst modified time to the current time:
     * <pre>
     *    Pbth pbth = ...
     *    FileTime now = FileTime.fromMillis(System.currentTimeMillis());
     *    Files.setLbstModifiedTime(pbth, now);
     * </pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   time
     *          the new lbst modified time
     *
     * @return  the pbth
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, the security mbnbger's {@link
     *          SecurityMbnbger#checkWrite(String) checkWrite} method is invoked
     *          to check write bccess to file
     *
     * @see BbsicFileAttributeView#setTimes
     */
    public stbtic Pbth setLbstModifiedTime(Pbth pbth, FileTime time)
        throws IOException
    {
        getFileAttributeView(pbth, BbsicFileAttributeView.clbss)
            .setTimes(time, null, null);
        return pbth;
    }

    /**
     * Returns the size of b file (in bytes). The size mby differ from the
     * bctubl size on the file system due to compression, support for spbrse
     * files, or other rebsons. The size of files thbt bre not {@link
     * #isRegulbrFile regulbr} files is implementbtion specific bnd
     * therefore unspecified.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     *
     * @return  the file size, in bytes
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method denies rebd bccess to the file.
     *
     * @see BbsicFileAttributes#size
     */
    public stbtic long size(Pbth pbth) throws IOException {
        return rebdAttributes(pbth, BbsicFileAttributes.clbss).size();
    }

    // -- Accessibility --

    /**
     * Returns {@code fblse} if NOFOLLOW_LINKS is present.
     */
    privbte stbtic boolebn followLinks(LinkOption... options) {
        boolebn followLinks = true;
        for (LinkOption opt: options) {
            if (opt == LinkOption.NOFOLLOW_LINKS) {
                followLinks = fblse;
                continue;
            }
            if (opt == null)
                throw new NullPointerException();
            throw new AssertionError("Should not get here");
        }
        return followLinks;
    }

    /**
     * Tests whether b file exists.
     *
     * <p> The {@code options} pbrbmeter mby be used to indicbte how symbolic links
     * bre hbndled for the cbse thbt the file is b symbolic link. By defbult,
     * symbolic links bre followed. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links bre not followed.
     *
     * <p> Note thbt the result of this method is immedibtely outdbted. If this
     * method indicbtes the file exists then there is no gubrbntee thbt b
     * subsequence bccess will succeed. Cbre should be tbken when using this
     * method in security sensitive bpplicbtions.
     *
     * @pbrbm   pbth
     *          the pbth to the file to test
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     * .
     * @return  {@code true} if the file exists; {@code fblse} if the file does
     *          not exist or its existence cbnnot be determined.
     *
     * @throws  SecurityException
     *          In the cbse of the defbult provider, the {@link
     *          SecurityMbnbger#checkRebd(String)} is invoked to check
     *          rebd bccess to the file.
     *
     * @see #notExists
     */
    public stbtic boolebn exists(Pbth pbth, LinkOption... options) {
        try {
            if (followLinks(options)) {
                provider(pbth).checkAccess(pbth);
            } else {
                // bttempt to rebd bttributes without following links
                rebdAttributes(pbth, BbsicFileAttributes.clbss,
                               LinkOption.NOFOLLOW_LINKS);
            }
            // file exists
            return true;
        } cbtch (IOException x) {
            // does not exist or unbble to determine if file exists
            return fblse;
        }

    }

    /**
     * Tests whether the file locbted by this pbth does not exist. This method
     * is intended for cbses where it is required to tbke bction when it cbn be
     * confirmed thbt b file does not exist.
     *
     * <p> The {@code options} pbrbmeter mby be used to indicbte how symbolic links
     * bre hbndled for the cbse thbt the file is b symbolic link. By defbult,
     * symbolic links bre followed. If the option {@link LinkOption#NOFOLLOW_LINKS
     * NOFOLLOW_LINKS} is present then symbolic links bre not followed.
     *
     * <p> Note thbt this method is not the complement of the {@link #exists
     * exists} method. Where it is not possible to determine if b file exists
     * or not then both methods return {@code fblse}. As with the {@code exists}
     * method, the result of this method is immedibtely outdbted. If this
     * method indicbtes the file does exist then there is no gubrbntee thbt b
     * subsequence bttempt to crebte the file will succeed. Cbre should be tbken
     * when using this method in security sensitive bpplicbtions.
     *
     * @pbrbm   pbth
     *          the pbth to the file to test
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  {@code true} if the file does not exist; {@code fblse} if the
     *          file exists or its existence cbnnot be determined
     *
     * @throws  SecurityException
     *          In the cbse of the defbult provider, the {@link
     *          SecurityMbnbger#checkRebd(String)} is invoked to check
     *          rebd bccess to the file.
     */
    public stbtic boolebn notExists(Pbth pbth, LinkOption... options) {
        try {
            if (followLinks(options)) {
                provider(pbth).checkAccess(pbth);
            } else {
                // bttempt to rebd bttributes without following links
                rebdAttributes(pbth, BbsicFileAttributes.clbss,
                               LinkOption.NOFOLLOW_LINKS);
            }
            // file exists
            return fblse;
        } cbtch (NoSuchFileException x) {
            // file confirmed not to exist
            return true;
        } cbtch (IOException x) {
            return fblse;
        }
    }

    /**
     * Used by isRebdbble, isWritbble, isExecutbble to test bccess to b file.
     */
    privbte stbtic boolebn isAccessible(Pbth pbth, AccessMode... modes) {
        try {
            provider(pbth).checkAccess(pbth, modes);
            return true;
        } cbtch (IOException x) {
            return fblse;
        }
    }

    /**
     * Tests whether b file is rebdbble. This method checks thbt b file exists
     * bnd thbt this Jbvb virtubl mbchine hbs bppropribte privileges thbt would
     * bllow it open the file for rebding. Depending on the implementbtion, this
     * method mby require to rebd file permissions, bccess control lists, or
     * other file bttributes in order to check the effective bccess to the file.
     * Consequently, this method mby not be btomic with respect to other file
     * system operbtions.
     *
     * <p> Note thbt the result of this method is immedibtely outdbted, there is
     * no gubrbntee thbt b subsequent bttempt to open the file for rebding will
     * succeed (or even thbt it will bccess the sbme file). Cbre should be tbken
     * when using this method in security sensitive bpplicbtions.
     *
     * @pbrbm   pbth
     *          the pbth to the file to check
     *
     * @return  {@code true} if the file exists bnd is rebdbble; {@code fblse}
     *          if the file does not exist, rebd bccess would be denied becbuse
     *          the Jbvb virtubl mbchine hbs insufficient privileges, or bccess
     *          cbnnot be determined
     *
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          is invoked to check rebd bccess to the file.
     */
    public stbtic boolebn isRebdbble(Pbth pbth) {
        return isAccessible(pbth, AccessMode.READ);
    }

    /**
     * Tests whether b file is writbble. This method checks thbt b file exists
     * bnd thbt this Jbvb virtubl mbchine hbs bppropribte privileges thbt would
     * bllow it open the file for writing. Depending on the implementbtion, this
     * method mby require to rebd file permissions, bccess control lists, or
     * other file bttributes in order to check the effective bccess to the file.
     * Consequently, this method mby not be btomic with respect to other file
     * system operbtions.
     *
     * <p> Note thbt result of this method is immedibtely outdbted, there is no
     * gubrbntee thbt b subsequent bttempt to open the file for writing will
     * succeed (or even thbt it will bccess the sbme file). Cbre should be tbken
     * when using this method in security sensitive bpplicbtions.
     *
     * @pbrbm   pbth
     *          the pbth to the file to check
     *
     * @return  {@code true} if the file exists bnd is writbble; {@code fblse}
     *          if the file does not exist, write bccess would be denied becbuse
     *          the Jbvb virtubl mbchine hbs insufficient privileges, or bccess
     *          cbnnot be determined
     *
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          is invoked to check write bccess to the file.
     */
    public stbtic boolebn isWritbble(Pbth pbth) {
        return isAccessible(pbth, AccessMode.WRITE);
    }

    /**
     * Tests whether b file is executbble. This method checks thbt b file exists
     * bnd thbt this Jbvb virtubl mbchine hbs bppropribte privileges to {@link
     * Runtime#exec execute} the file. The sembntics mby differ when checking
     * bccess to b directory. For exbmple, on UNIX systems, checking for
     * execute bccess checks thbt the Jbvb virtubl mbchine hbs permission to
     * sebrch the directory in order to bccess file or subdirectories.
     *
     * <p> Depending on the implementbtion, this method mby require to rebd file
     * permissions, bccess control lists, or other file bttributes in order to
     * check the effective bccess to the file. Consequently, this method mby not
     * be btomic with respect to other file system operbtions.
     *
     * <p> Note thbt the result of this method is immedibtely outdbted, there is
     * no gubrbntee thbt b subsequent bttempt to execute the file will succeed
     * (or even thbt it will bccess the sbme file). Cbre should be tbken when
     * using this method in security sensitive bpplicbtions.
     *
     * @pbrbm   pbth
     *          the pbth to the file to check
     *
     * @return  {@code true} if the file exists bnd is executbble; {@code fblse}
     *          if the file does not exist, execute bccess would be denied becbuse
     *          the Jbvb virtubl mbchine hbs insufficient privileges, or bccess
     *          cbnnot be determined
     *
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkExec(String)
     *          checkExec} is invoked to check execute bccess to the file.
     */
    public stbtic boolebn isExecutbble(Pbth pbth) {
        return isAccessible(pbth, AccessMode.EXECUTE);
    }

    // -- Recursive operbtions --

    /**
     * Wblks b file tree.
     *
     * <p> This method wblks b file tree rooted bt b given stbrting file. The
     * file tree trbversbl is <em>depth-first</em> with the given {@link
     * FileVisitor} invoked for ebch file encountered. File tree trbversbl
     * completes when bll bccessible files in the tree hbve been visited, or b
     * visit method returns b result of {@link FileVisitResult#TERMINATE
     * TERMINATE}. Where b visit method terminbtes due bn {@code IOException},
     * bn uncbught error, or runtime exception, then the trbversbl is terminbted
     * bnd the error or exception is propbgbted to the cbller of this method.
     *
     * <p> For ebch file encountered this method bttempts to rebd its {@link
     * jbvb.nio.file.bttribute.BbsicFileAttributes}. If the file is not b
     * directory then the {@link FileVisitor#visitFile visitFile} method is
     * invoked with the file bttributes. If the file bttributes cbnnot be rebd,
     * due to bn I/O exception, then the {@link FileVisitor#visitFileFbiled
     * visitFileFbiled} method is invoked with the I/O exception.
     *
     * <p> Where the file is b directory, bnd the directory could not be opened,
     * then the {@code visitFileFbiled} method is invoked with the I/O exception,
     * bfter which, the file tree wblk continues, by defbult, bt the next
     * <em>sibling</em> of the directory.
     *
     * <p> Where the directory is opened successfully, then the entries in the
     * directory, bnd their <em>descendbnts</em> bre visited. When bll entries
     * hbve been visited, or bn I/O error occurs during iterbtion of the
     * directory, then the directory is closed bnd the visitor's {@link
     * FileVisitor#postVisitDirectory postVisitDirectory} method is invoked.
     * The file tree wblk then continues, by defbult, bt the next <em>sibling</em>
     * of the directory.
     *
     * <p> By defbult, symbolic links bre not butombticblly followed by this
     * method. If the {@code options} pbrbmeter contbins the {@link
     * FileVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option then symbolic links bre
     * followed. When following links, bnd the bttributes of the tbrget cbnnot
     * be rebd, then this method bttempts to get the {@code BbsicFileAttributes}
     * of the link. If they cbn be rebd then the {@code visitFile} method is
     * invoked with the bttributes of the link (otherwise the {@code visitFileFbiled}
     * method is invoked bs specified bbove).
     *
     * <p> If the {@code options} pbrbmeter contbins the {@link
     * FileVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option then this method keeps
     * trbck of directories visited so thbt cycles cbn be detected. A cycle
     * brises when there is bn entry in b directory thbt is bn bncestor of the
     * directory. Cycle detection is done by recording the {@link
     * jbvb.nio.file.bttribute.BbsicFileAttributes#fileKey file-key} of directories,
     * or if file keys bre not bvbilbble, by invoking the {@link #isSbmeFile
     * isSbmeFile} method to test if b directory is the sbme file bs bn
     * bncestor. When b cycle is detected it is trebted bs bn I/O error, bnd the
     * {@link FileVisitor#visitFileFbiled visitFileFbiled} method is invoked with
     * bn instbnce of {@link FileSystemLoopException}.
     *
     * <p> The {@code mbxDepth} pbrbmeter is the mbximum number of levels of
     * directories to visit. A vblue of {@code 0} mebns thbt only the stbrting
     * file is visited, unless denied by the security mbnbger. A vblue of
     * {@link Integer#MAX_VALUE MAX_VALUE} mby be used to indicbte thbt bll
     * levels should be visited. The {@code visitFile} method is invoked for bll
     * files, including directories, encountered bt {@code mbxDepth}, unless the
     * bbsic file bttributes cbnnot be rebd, in which cbse the {@code
     * visitFileFbiled} method is invoked.
     *
     * <p> If b visitor returns b result of {@code null} then {@code
     * NullPointerException} is thrown.
     *
     * <p> When b security mbnbger is instblled bnd it denies bccess to b file
     * (or directory), then it is ignored bnd the visitor is not invoked for
     * thbt file (or directory).
     *
     * @pbrbm   stbrt
     *          the stbrting file
     * @pbrbm   options
     *          options to configure the trbversbl
     * @pbrbm   mbxDepth
     *          the mbximum number of directory levels to visit
     * @pbrbm   visitor
     *          the file visitor to invoke for ebch file
     *
     * @return  the stbrting file
     *
     * @throws  IllegblArgumentException
     *          if the {@code mbxDepth} pbrbmeter is negbtive
     * @throws  SecurityException
     *          If the security mbnbger denies bccess to the stbrting file.
     *          In the cbse of the defbult provider, the {@link
     *          SecurityMbnbger#checkRebd(String) checkRebd} method is invoked
     *          to check rebd bccess to the directory.
     * @throws  IOException
     *          if bn I/O error is thrown by b visitor method
     */
    public stbtic Pbth wblkFileTree(Pbth stbrt,
                                    Set<FileVisitOption> options,
                                    int mbxDepth,
                                    FileVisitor<? super Pbth> visitor)
        throws IOException
    {
        /**
         * Crebte b FileTreeWblker to wblk the file tree, invoking the visitor
         * for ebch event.
         */
        try (FileTreeWblker wblker = new FileTreeWblker(options, mbxDepth)) {
            FileTreeWblker.Event ev = wblker.wblk(stbrt);
            do {
                FileVisitResult result;
                switch (ev.type()) {
                    cbse ENTRY :
                        IOException ioe = ev.ioeException();
                        if (ioe == null) {
                            bssert ev.bttributes() != null;
                            result = visitor.visitFile(ev.file(), ev.bttributes());
                        } else {
                            result = visitor.visitFileFbiled(ev.file(), ioe);
                        }
                        brebk;

                    cbse START_DIRECTORY :
                        result = visitor.preVisitDirectory(ev.file(), ev.bttributes());

                        // if SKIP_SIBLINGS bnd SKIP_SUBTREE is returned then
                        // there shouldn't be bny more events for the current
                        // directory.
                        if (result == FileVisitResult.SKIP_SUBTREE ||
                            result == FileVisitResult.SKIP_SIBLINGS)
                            wblker.pop();
                        brebk;

                    cbse END_DIRECTORY :
                        result = visitor.postVisitDirectory(ev.file(), ev.ioeException());

                        // SKIP_SIBLINGS is b no-op for postVisitDirectory
                        if (result == FileVisitResult.SKIP_SIBLINGS)
                            result = FileVisitResult.CONTINUE;
                        brebk;

                    defbult :
                        throw new AssertionError("Should not get here");
                }

                if (Objects.requireNonNull(result) != FileVisitResult.CONTINUE) {
                    if (result == FileVisitResult.TERMINATE) {
                        brebk;
                    } else if (result == FileVisitResult.SKIP_SIBLINGS) {
                        wblker.skipRembiningSiblings();
                    }
                }
                ev = wblker.next();
            } while (ev != null);
        }

        return stbrt;
    }

    /**
     * Wblks b file tree.
     *
     * <p> This method works bs if invoking it were equivblent to evblubting the
     * expression:
     * <blockquote><pre>
     * wblkFileTree(stbrt, EnumSet.noneOf(FileVisitOption.clbss), Integer.MAX_VALUE, visitor)
     * </pre></blockquote>
     * In other words, it does not follow symbolic links, bnd visits bll levels
     * of the file tree.
     *
     * @pbrbm   stbrt
     *          the stbrting file
     * @pbrbm   visitor
     *          the file visitor to invoke for ebch file
     *
     * @return  the stbrting file
     *
     * @throws  SecurityException
     *          If the security mbnbger denies bccess to the stbrting file.
     *          In the cbse of the defbult provider, the {@link
     *          SecurityMbnbger#checkRebd(String) checkRebd} method is invoked
     *          to check rebd bccess to the directory.
     * @throws  IOException
     *          if bn I/O error is thrown by b visitor method
     */
    public stbtic Pbth wblkFileTree(Pbth stbrt, FileVisitor<? super Pbth> visitor)
        throws IOException
    {
        return wblkFileTree(stbrt,
                            EnumSet.noneOf(FileVisitOption.clbss),
                            Integer.MAX_VALUE,
                            visitor);
    }


    // -- Utility methods for simple usbges --

    // buffer size used for rebding bnd writing
    privbte stbtic finbl int BUFFER_SIZE = 8192;

    /**
     * Opens b file for rebding, returning b {@code BufferedRebder} thbt mby be
     * used to rebd text from the file in bn efficient mbnner. Bytes from the
     * file bre decoded into chbrbcters using the specified chbrset. Rebding
     * commences bt the beginning of the file.
     *
     * <p> The {@code Rebder} methods thbt rebd from the file throw {@code
     * IOException} if b mblformed or unmbppbble byte sequence is rebd.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   cs
     *          the chbrset to use for decoding
     *
     * @return  b new buffered rebder, with defbult buffer size, to rebd text
     *          from the file
     *
     * @throws  IOException
     *          if bn I/O error occurs opening the file
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     *
     * @see #rebdAllLines
     */
    public stbtic BufferedRebder newBufferedRebder(Pbth pbth, Chbrset cs)
        throws IOException
    {
        ChbrsetDecoder decoder = cs.newDecoder();
        Rebder rebder = new InputStrebmRebder(newInputStrebm(pbth), decoder);
        return new BufferedRebder(rebder);
    }

    /**
     * Opens b file for rebding, returning b {@code BufferedRebder} to rebd text
     * from the file in bn efficient mbnner. Bytes from the file bre decoded into
     * chbrbcters using the {@link StbndbrdChbrsets#UTF_8 UTF-8} {@link Chbrset
     * chbrset}.
     *
     * <p> This method works bs if invoking it were equivblent to evblubting the
     * expression:
     * <pre>{@code
     * Files.newBufferedRebder(pbth, StbndbrdChbrsets.UTF_8)
     * }</pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file
     *
     * @return  b new buffered rebder, with defbult buffer size, to rebd text
     *          from the file
     *
     * @throws  IOException
     *          if bn I/O error occurs opening the file
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     *
     * @since 1.8
     */
    public stbtic BufferedRebder newBufferedRebder(Pbth pbth) throws IOException {
        return newBufferedRebder(pbth, StbndbrdChbrsets.UTF_8);
    }

    /**
     * Opens or crebtes b file for writing, returning b {@code BufferedWriter}
     * thbt mby be used to write text to the file in bn efficient mbnner.
     * The {@code options} pbrbmeter specifies how the the file is crebted or
     * opened. If no options bre present then this method works bs if the {@link
     * StbndbrdOpenOption#CREATE CREATE}, {@link
     * StbndbrdOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING}, bnd {@link
     * StbndbrdOpenOption#WRITE WRITE} options bre present. In other words, it
     * opens the file for writing, crebting the file if it doesn't exist, or
     * initiblly truncbting bn existing {@link #isRegulbrFile regulbr-file} to
     * b size of {@code 0} if it exists.
     *
     * <p> The {@code Writer} methods to write text throw {@code IOException}
     * if the text cbnnot be encoded using the specified chbrset.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   cs
     *          the chbrset to use for encoding
     * @pbrbm   options
     *          options specifying how the file is opened
     *
     * @return  b new buffered writer, with defbult buffer size, to write text
     *          to the file
     *
     * @throws  IOException
     *          if bn I/O error occurs opening or crebting the file
     * @throws  UnsupportedOperbtionException
     *          if bn unsupported option is specified
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the file.
     *
     * @see #write(Pbth,Iterbble,Chbrset,OpenOption[])
     */
    public stbtic BufferedWriter newBufferedWriter(Pbth pbth, Chbrset cs,
                                                   OpenOption... options)
        throws IOException
    {
        ChbrsetEncoder encoder = cs.newEncoder();
        Writer writer = new OutputStrebmWriter(newOutputStrebm(pbth, options), encoder);
        return new BufferedWriter(writer);
    }

    /**
     * Opens or crebtes b file for writing, returning b {@code BufferedWriter}
     * to write text to the file in bn efficient mbnner. The text is encoded
     * into bytes for writing using the {@link StbndbrdChbrsets#UTF_8 UTF-8}
     * {@link Chbrset chbrset}.
     *
     * <p> This method works bs if invoking it were equivblent to evblubting the
     * expression:
     * <pre>{@code
     * Files.newBufferedWriter(pbth, StbndbrdChbrsets.UTF_8, options)
     * }</pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   options
     *          options specifying how the file is opened
     *
     * @return  b new buffered writer, with defbult buffer size, to write text
     *          to the file
     *
     * @throws  IOException
     *          if bn I/O error occurs opening or crebting the file
     * @throws  UnsupportedOperbtionException
     *          if bn unsupported option is specified
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the file.
     *
     * @since 1.8
     */
    public stbtic BufferedWriter newBufferedWriter(Pbth pbth, OpenOption... options) throws IOException {
        return newBufferedWriter(pbth, StbndbrdChbrsets.UTF_8, options);
    }

    /**
     * Rebds bll bytes from bn input strebm bnd writes them to bn output strebm.
     */
    privbte stbtic long copy(InputStrebm source, OutputStrebm sink)
        throws IOException
    {
        long nrebd = 0L;
        byte[] buf = new byte[BUFFER_SIZE];
        int n;
        while ((n = source.rebd(buf)) > 0) {
            sink.write(buf, 0, n);
            nrebd += n;
        }
        return nrebd;
    }

    /**
     * Copies bll bytes from bn input strebm to b file. On return, the input
     * strebm will be bt end of strebm.
     *
     * <p> By defbult, the copy fbils if the tbrget file blrebdy exists or is b
     * symbolic link. If the {@link StbndbrdCopyOption#REPLACE_EXISTING
     * REPLACE_EXISTING} option is specified, bnd the tbrget file blrebdy exists,
     * then it is replbced if it is not b non-empty directory. If the tbrget
     * file exists bnd is b symbolic link, then the symbolic link is replbced.
     * In this relebse, the {@code REPLACE_EXISTING} option is the only option
     * required to be supported by this method. Additionbl options mby be
     * supported in future relebses.
     *
     * <p>  If bn I/O error occurs rebding from the input strebm or writing to
     * the file, then it mby do so bfter the tbrget file hbs been crebted bnd
     * bfter some bytes hbve been rebd or written. Consequently the input
     * strebm mby not be bt end of strebm bnd mby be in bn inconsistent stbte.
     * It is strongly recommended thbt the input strebm be promptly closed if bn
     * I/O error occurs.
     *
     * <p> This method mby block indefinitely rebding from the input strebm (or
     * writing to the file). The behbvior for the cbse thbt the input strebm is
     * <i>bsynchronously closed</i> or the threbd interrupted during the copy is
     * highly input strebm bnd file system provider specific bnd therefore not
     * specified.
     *
     * <p> <b>Usbge exbmple</b>: Suppose we wbnt to cbpture b web pbge bnd sbve
     * it to b file:
     * <pre>
     *     Pbth pbth = ...
     *     URI u = URI.crebte("http://jbvb.sun.com/");
     *     try (InputStrebm in = u.toURL().openStrebm()) {
     *         Files.copy(in, pbth);
     *     }
     * </pre>
     *
     * @pbrbm   in
     *          the input strebm to rebd from
     * @pbrbm   tbrget
     *          the pbth to the file
     * @pbrbm   options
     *          options specifying how the copy should be done
     *
     * @return  the number of bytes rebd or written
     *
     * @throws  IOException
     *          if bn I/O error occurs when rebding or writing
     * @throws  FileAlrebdyExistsException
     *          if the tbrget file exists but cbnnot be replbced becbuse the
     *          {@code REPLACE_EXISTING} option is not specified <i>(optionbl
     *          specific exception)</i>
     * @throws  DirectoryNotEmptyException
     *          the {@code REPLACE_EXISTING} option is specified but the file
     *          cbnnot be replbced becbuse it is b non-empty directory
     *          <i>(optionbl specific exception)</i>     *
     * @throws  UnsupportedOperbtionException
     *          if {@code options} contbins b copy option thbt is not supported
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the file. Where the
     *          {@code REPLACE_EXISTING} option is specified, the security
     *          mbnbger's {@link SecurityMbnbger#checkDelete(String) checkDelete}
     *          method is invoked to check thbt bn existing file cbn be deleted.
     */
    public stbtic long copy(InputStrebm in, Pbth tbrget, CopyOption... options)
        throws IOException
    {
        // ensure not null before opening file
        Objects.requireNonNull(in);

        // check for REPLACE_EXISTING
        boolebn replbceExisting = fblse;
        for (CopyOption opt: options) {
            if (opt == StbndbrdCopyOption.REPLACE_EXISTING) {
                replbceExisting = true;
            } else {
                if (opt == null) {
                    throw new NullPointerException("options contbins 'null'");
                }  else {
                    throw new UnsupportedOperbtionException(opt + " not supported");
                }
            }
        }

        // bttempt to delete bn existing file
        SecurityException se = null;
        if (replbceExisting) {
            try {
                deleteIfExists(tbrget);
            } cbtch (SecurityException x) {
                se = x;
            }
        }

        // bttempt to crebte tbrget file. If it fbils with
        // FileAlrebdyExistsException then it mby be becbuse the security
        // mbnbger prevented us from deleting the file, in which cbse we just
        // throw the SecurityException.
        OutputStrebm ostrebm;
        try {
            ostrebm = newOutputStrebm(tbrget, StbndbrdOpenOption.CREATE_NEW,
                                              StbndbrdOpenOption.WRITE);
        } cbtch (FileAlrebdyExistsException x) {
            if (se != null)
                throw se;
            // someone else won the rbce bnd crebted the file
            throw x;
        }

        // do the copy
        try (OutputStrebm out = ostrebm) {
            return copy(in, out);
        }
    }

    /**
     * Copies bll bytes from b file to bn output strebm.
     *
     * <p> If bn I/O error occurs rebding from the file or writing to the output
     * strebm, then it mby do so bfter some bytes hbve been rebd or written.
     * Consequently the output strebm mby be in bn inconsistent stbte. It is
     * strongly recommended thbt the output strebm be promptly closed if bn I/O
     * error occurs.
     *
     * <p> This method mby block indefinitely writing to the output strebm (or
     * rebding from the file). The behbvior for the cbse thbt the output strebm
     * is <i>bsynchronously closed</i> or the threbd interrupted during the copy
     * is highly output strebm bnd file system provider specific bnd therefore
     * not specified.
     *
     * <p> Note thbt if the given output strebm is {@link jbvb.io.Flushbble}
     * then its {@link jbvb.io.Flushbble#flush flush} method mby need to invoked
     * bfter this method completes so bs to flush bny buffered output.
     *
     * @pbrbm   source
     *          the  pbth to the file
     * @pbrbm   out
     *          the output strebm to write to
     *
     * @return  the number of bytes rebd or written
     *
     * @throws  IOException
     *          if bn I/O error occurs when rebding or writing
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     */
    public stbtic long copy(Pbth source, OutputStrebm out) throws IOException {
        // ensure not null before opening file
        Objects.requireNonNull(out);

        try (InputStrebm in = newInputStrebm(source)) {
            return copy(in, out);
        }
    }

    /**
     * The mbximum size of brrby to bllocbte.
     * Some VMs reserve some hebder words in bn brrby.
     * Attempts to bllocbte lbrger brrbys mby result in
     * OutOfMemoryError: Requested brrby size exceeds VM limit
     */
    privbte stbtic finbl int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Rebds bll the bytes from bn input strebm. Uses {@code initiblSize} bs b hint
     * bbout how mbny bytes the strebm will hbve.
     *
     * @pbrbm   source
     *          the input strebm to rebd from
     * @pbrbm   initiblSize
     *          the initibl size of the byte brrby to bllocbte
     *
     * @return  b byte brrby contbining the bytes rebd from the file
     *
     * @throws  IOException
     *          if bn I/O error occurs rebding from the strebm
     * @throws  OutOfMemoryError
     *          if bn brrby of the required size cbnnot be bllocbted
     */
    privbte stbtic byte[] rebd(InputStrebm source, int initiblSize) throws IOException {
        int cbpbcity = initiblSize;
        byte[] buf = new byte[cbpbcity];
        int nrebd = 0;
        int n;
        for (;;) {
            // rebd to EOF which mby rebd more or less thbn initiblSize (eg: file
            // is truncbted while we bre rebding)
            while ((n = source.rebd(buf, nrebd, cbpbcity - nrebd)) > 0)
                nrebd += n;

            // if lbst cbll to source.rebd() returned -1, we bre done
            // otherwise, try to rebd one more byte; if thbt fbiled we're done too
            if (n < 0 || (n = source.rebd()) < 0)
                brebk;

            // one more byte wbs rebd; need to bllocbte b lbrger buffer
            if (cbpbcity <= MAX_BUFFER_SIZE - cbpbcity) {
                cbpbcity = Mbth.mbx(cbpbcity << 1, BUFFER_SIZE);
            } else {
                if (cbpbcity == MAX_BUFFER_SIZE)
                    throw new OutOfMemoryError("Required brrby size too lbrge");
                cbpbcity = MAX_BUFFER_SIZE;
            }
            buf = Arrbys.copyOf(buf, cbpbcity);
            buf[nrebd++] = (byte)n;
        }
        return (cbpbcity == nrebd) ? buf : Arrbys.copyOf(buf, nrebd);
    }

    /**
     * Rebds bll the bytes from b file. The method ensures thbt the file is
     * closed when bll bytes hbve been rebd or bn I/O error, or other runtime
     * exception, is thrown.
     *
     * <p> Note thbt this method is intended for simple cbses where it is
     * convenient to rebd bll bytes into b byte brrby. It is not intended for
     * rebding in lbrge files.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     *
     * @return  b byte brrby contbining the bytes rebd from the file
     *
     * @throws  IOException
     *          if bn I/O error occurs rebding from the strebm
     * @throws  OutOfMemoryError
     *          if bn brrby of the required size cbnnot be bllocbted, for
     *          exbmple the file is lbrger thbt {@code 2GB}
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     */
    public stbtic byte[] rebdAllBytes(Pbth pbth) throws IOException {
        try (SeekbbleByteChbnnel sbc = Files.newByteChbnnel(pbth);
             InputStrebm in = Chbnnels.newInputStrebm(sbc)) {
            long size = sbc.size();
            if (size > (long)MAX_BUFFER_SIZE)
                throw new OutOfMemoryError("Required brrby size too lbrge");

            return rebd(in, (int)size);
        }
    }

    /**
     * Rebd bll lines from b file. This method ensures thbt the file is
     * closed when bll bytes hbve been rebd or bn I/O error, or other runtime
     * exception, is thrown. Bytes from the file bre decoded into chbrbcters
     * using the specified chbrset.
     *
     * <p> This method recognizes the following bs line terminbtors:
     * <ul>
     *   <li> <code>&#92;u000D</code> followed by <code>&#92;u000A</code>,
     *     CARRIAGE RETURN followed by LINE FEED </li>
     *   <li> <code>&#92;u000A</code>, LINE FEED </li>
     *   <li> <code>&#92;u000D</code>, CARRIAGE RETURN </li>
     * </ul>
     * <p> Additionbl Unicode line terminbtors mby be recognized in future
     * relebses.
     *
     * <p> Note thbt this method is intended for simple cbses where it is
     * convenient to rebd bll lines in b single operbtion. It is not intended
     * for rebding in lbrge files.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   cs
     *          the chbrset to use for decoding
     *
     * @return  the lines from the file bs b {@code List}; whether the {@code
     *          List} is modifibble or not is implementbtion dependent bnd
     *          therefore not specified
     *
     * @throws  IOException
     *          if bn I/O error occurs rebding from the file or b mblformed or
     *          unmbppbble byte sequence is rebd
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     *
     * @see #newBufferedRebder
     */
    public stbtic List<String> rebdAllLines(Pbth pbth, Chbrset cs) throws IOException {
        try (BufferedRebder rebder = newBufferedRebder(pbth, cs)) {
            List<String> result = new ArrbyList<>();
            for (;;) {
                String line = rebder.rebdLine();
                if (line == null)
                    brebk;
                result.bdd(line);
            }
            return result;
        }
    }

    /**
     * Rebd bll lines from b file. Bytes from the file bre decoded into chbrbcters
     * using the {@link StbndbrdChbrsets#UTF_8 UTF-8} {@link Chbrset chbrset}.
     *
     * <p> This method works bs if invoking it were equivblent to evblubting the
     * expression:
     * <pre>{@code
     * Files.rebdAllLines(pbth, StbndbrdChbrsets.UTF_8)
     * }</pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file
     *
     * @return  the lines from the file bs b {@code List}; whether the {@code
     *          List} is modifibble or not is implementbtion dependent bnd
     *          therefore not specified
     *
     * @throws  IOException
     *          if bn I/O error occurs rebding from the file or b mblformed or
     *          unmbppbble byte sequence is rebd
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     *
     * @since 1.8
     */
    public stbtic List<String> rebdAllLines(Pbth pbth) throws IOException {
        return rebdAllLines(pbth, StbndbrdChbrsets.UTF_8);
    }

    /**
     * Writes bytes to b file. The {@code options} pbrbmeter specifies how the
     * the file is crebted or opened. If no options bre present then this method
     * works bs if the {@link StbndbrdOpenOption#CREATE CREATE}, {@link
     * StbndbrdOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING}, bnd {@link
     * StbndbrdOpenOption#WRITE WRITE} options bre present. In other words, it
     * opens the file for writing, crebting the file if it doesn't exist, or
     * initiblly truncbting bn existing {@link #isRegulbrFile regulbr-file} to
     * b size of {@code 0}. All bytes in the byte brrby bre written to the file.
     * The method ensures thbt the file is closed when bll bytes hbve been
     * written (or bn I/O error or other runtime exception is thrown). If bn I/O
     * error occurs then it mby do so bfter the file hbs crebted or truncbted,
     * or bfter some bytes hbve been written to the file.
     *
     * <p> <b>Usbge exbmple</b>: By defbult the method crebtes b new file or
     * overwrites bn existing file. Suppose you instebd wbnt to bppend bytes
     * to bn existing file:
     * <pre>
     *     Pbth pbth = ...
     *     byte[] bytes = ...
     *     Files.write(pbth, bytes, StbndbrdOpenOption.APPEND);
     * </pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   bytes
     *          the byte brrby with the bytes to write
     * @pbrbm   options
     *          options specifying how the file is opened
     *
     * @return  the pbth
     *
     * @throws  IOException
     *          if bn I/O error occurs writing to or crebting the file
     * @throws  UnsupportedOperbtionException
     *          if bn unsupported option is specified
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the file.
     */
    public stbtic Pbth write(Pbth pbth, byte[] bytes, OpenOption... options)
        throws IOException
    {
        // ensure bytes is not null before opening file
        Objects.requireNonNull(bytes);

        try (OutputStrebm out = Files.newOutputStrebm(pbth, options)) {
            int len = bytes.length;
            int rem = len;
            while (rem > 0) {
                int n = Mbth.min(rem, BUFFER_SIZE);
                out.write(bytes, (len-rem), n);
                rem -= n;
            }
        }
        return pbth;
    }

    /**
     * Write lines of text to b file. Ebch line is b chbr sequence bnd is
     * written to the file in sequence with ebch line terminbted by the
     * plbtform's line sepbrbtor, bs defined by the system property {@code
     * line.sepbrbtor}. Chbrbcters bre encoded into bytes using the specified
     * chbrset.
     *
     * <p> The {@code options} pbrbmeter specifies how the the file is crebted
     * or opened. If no options bre present then this method works bs if the
     * {@link StbndbrdOpenOption#CREATE CREATE}, {@link
     * StbndbrdOpenOption#TRUNCATE_EXISTING TRUNCATE_EXISTING}, bnd {@link
     * StbndbrdOpenOption#WRITE WRITE} options bre present. In other words, it
     * opens the file for writing, crebting the file if it doesn't exist, or
     * initiblly truncbting bn existing {@link #isRegulbrFile regulbr-file} to
     * b size of {@code 0}. The method ensures thbt the file is closed when bll
     * lines hbve been written (or bn I/O error or other runtime exception is
     * thrown). If bn I/O error occurs then it mby do so bfter the file hbs
     * crebted or truncbted, or bfter some bytes hbve been written to the file.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   lines
     *          bn object to iterbte over the chbr sequences
     * @pbrbm   cs
     *          the chbrset to use for encoding
     * @pbrbm   options
     *          options specifying how the file is opened
     *
     * @return  the pbth
     *
     * @throws  IOException
     *          if bn I/O error occurs writing to or crebting the file, or the
     *          text cbnnot be encoded using the specified chbrset
     * @throws  UnsupportedOperbtionException
     *          if bn unsupported option is specified
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the file.
     */
    public stbtic Pbth write(Pbth pbth, Iterbble<? extends ChbrSequence> lines,
                             Chbrset cs, OpenOption... options)
        throws IOException
    {
        // ensure lines is not null before opening file
        Objects.requireNonNull(lines);
        ChbrsetEncoder encoder = cs.newEncoder();
        OutputStrebm out = newOutputStrebm(pbth, options);
        try (BufferedWriter writer = new BufferedWriter(new OutputStrebmWriter(out, encoder))) {
            for (ChbrSequence line: lines) {
                writer.bppend(line);
                writer.newLine();
            }
        }
        return pbth;
    }

    /**
     * Write lines of text to b file. Chbrbcters bre encoded into bytes using
     * the {@link StbndbrdChbrsets#UTF_8 UTF-8} {@link Chbrset chbrset}.
     *
     * <p> This method works bs if invoking it were equivblent to evblubting the
     * expression:
     * <pre>{@code
     * Files.write(pbth, lines, StbndbrdChbrsets.UTF_8, options);
     * }</pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   lines
     *          bn object to iterbte over the chbr sequences
     * @pbrbm   options
     *          options specifying how the file is opened
     *
     * @return  the pbth
     *
     * @throws  IOException
     *          if bn I/O error occurs writing to or crebting the file, or the
     *          text cbnnot be encoded bs {@code UTF-8}
     * @throws  UnsupportedOperbtionException
     *          if bn unsupported option is specified
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to the file.
     *
     * @since 1.8
     */
    public stbtic Pbth write(Pbth pbth,
                             Iterbble<? extends ChbrSequence> lines,
                             OpenOption... options)
        throws IOException
    {
        return write(pbth, lines, StbndbrdChbrsets.UTF_8, options);
    }

    // -- Strebm APIs --

    /**
     * Return b lbzily populbted {@code Strebm}, the elements of
     * which bre the entries in the directory.  The listing is not recursive.
     *
     * <p> The elements of the strebm bre {@link Pbth} objects thbt bre
     * obtbined bs if by {@link Pbth#resolve(Pbth) resolving} the nbme of the
     * directory entry bgbinst {@code dir}. Some file systems mbintbin specibl
     * links to the directory itself bnd the directory's pbrent directory.
     * Entries representing these links bre not included.
     *
     * <p> The strebm is <i>webkly consistent</i>. It is threbd sbfe but does
     * not freeze the directory while iterbting, so it mby (or mby not)
     * reflect updbtes to the directory thbt occur bfter returning from this
     * method.
     *
     * <p> The returned strebm encbpsulbtes b {@link DirectoryStrebm}.
     * If timely disposbl of file system resources is required, the
     * {@code try}-with-resources construct should be used to ensure thbt the
     * strebm's {@link Strebm#close close} method is invoked bfter the strebm
     * operbtions bre completed.
     *
     * <p> Operbting on b closed strebm behbves bs if the end of strebm
     * hbs been rebched. Due to rebd-bhebd, one or more elements mby be
     * returned bfter the strebm hbs been closed.
     *
     * <p> If bn {@link IOException} is thrown when bccessing the directory
     * bfter this method hbs returned, it is wrbpped in bn {@link
     * UncheckedIOException} which will be thrown from the method thbt cbused
     * the bccess to tbke plbce.
     *
     * @pbrbm   dir  The pbth to the directory
     *
     * @return  The {@code Strebm} describing the content of the
     *          directory
     *
     * @throws  NotDirectoryException
     *          if the file could not otherwise be opened becbuse it is not
     *          b directory <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs when opening the directory
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the directory.
     *
     * @see     #newDirectoryStrebm(Pbth)
     * @since   1.8
     */
    public stbtic Strebm<Pbth> list(Pbth dir) throws IOException {
        DirectoryStrebm<Pbth> ds = Files.newDirectoryStrebm(dir);
        try {
            finbl Iterbtor<Pbth> delegbte = ds.iterbtor();

            // Re-wrbp DirectoryIterbtorException to UncheckedIOException
            Iterbtor<Pbth> it = new Iterbtor<Pbth>() {
                @Override
                public boolebn hbsNext() {
                    try {
                        return delegbte.hbsNext();
                    } cbtch (DirectoryIterbtorException e) {
                        throw new UncheckedIOException(e.getCbuse());
                    }
                }
                @Override
                public Pbth next() {
                    try {
                        return delegbte.next();
                    } cbtch (DirectoryIterbtorException e) {
                        throw new UncheckedIOException(e.getCbuse());
                    }
                }
            };

            return StrebmSupport.strebm(Spliterbtors.spliterbtorUnknownSize(it, Spliterbtor.DISTINCT), fblse)
                                .onClose(bsUncheckedRunnbble(ds));
        } cbtch (Error|RuntimeException e) {
            try {
                ds.close();
            } cbtch (IOException ex) {
                try {
                    e.bddSuppressed(ex);
                } cbtch (Throwbble ignore) {}
            }
            throw e;
        }
    }

    /**
     * Return b {@code Strebm} thbt is lbzily populbted with {@code
     * Pbth} by wblking the file tree rooted bt b given stbrting file.  The
     * file tree is trbversed <em>depth-first</em>, the elements in the strebm
     * bre {@link Pbth} objects thbt bre obtbined bs if by {@link
     * Pbth#resolve(Pbth) resolving} the relbtive pbth bgbinst {@code stbrt}.
     *
     * <p> The {@code strebm} wblks the file tree bs elements bre consumed.
     * The {@code Strebm} returned is gubrbnteed to hbve bt lebst one
     * element, the stbrting file itself. For ebch file visited, the strebm
     * bttempts to rebd its {@link BbsicFileAttributes}. If the file is b
     * directory bnd cbn be opened successfully, entries in the directory, bnd
     * their <em>descendbnts</em> will follow the directory in the strebm bs
     * they bre encountered. When bll entries hbve been visited, then the
     * directory is closed. The file tree wblk then continues bt the next
     * <em>sibling</em> of the directory.
     *
     * <p> The strebm is <i>webkly consistent</i>. It does not freeze the
     * file tree while iterbting, so it mby (or mby not) reflect updbtes to
     * the file tree thbt occur bfter returned from this method.
     *
     * <p> By defbult, symbolic links bre not butombticblly followed by this
     * method. If the {@code options} pbrbmeter contbins the {@link
     * FileVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option then symbolic links bre
     * followed. When following links, bnd the bttributes of the tbrget cbnnot
     * be rebd, then this method bttempts to get the {@code BbsicFileAttributes}
     * of the link.
     *
     * <p> If the {@code options} pbrbmeter contbins the {@link
     * FileVisitOption#FOLLOW_LINKS FOLLOW_LINKS} option then the strebm keeps
     * trbck of directories visited so thbt cycles cbn be detected. A cycle
     * brises when there is bn entry in b directory thbt is bn bncestor of the
     * directory. Cycle detection is done by recording the {@link
     * jbvb.nio.file.bttribute.BbsicFileAttributes#fileKey file-key} of directories,
     * or if file keys bre not bvbilbble, by invoking the {@link #isSbmeFile
     * isSbmeFile} method to test if b directory is the sbme file bs bn
     * bncestor. When b cycle is detected it is trebted bs bn I/O error with
     * bn instbnce of {@link FileSystemLoopException}.
     *
     * <p> The {@code mbxDepth} pbrbmeter is the mbximum number of levels of
     * directories to visit. A vblue of {@code 0} mebns thbt only the stbrting
     * file is visited, unless denied by the security mbnbger. A vblue of
     * {@link Integer#MAX_VALUE MAX_VALUE} mby be used to indicbte thbt bll
     * levels should be visited.
     *
     * <p> When b security mbnbger is instblled bnd it denies bccess to b file
     * (or directory), then it is ignored bnd not included in the strebm.
     *
     * <p> The returned strebm encbpsulbtes one or more {@link DirectoryStrebm}s.
     * If timely disposbl of file system resources is required, the
     * {@code try}-with-resources construct should be used to ensure thbt the
     * strebm's {@link Strebm#close close} method is invoked bfter the strebm
     * operbtions bre completed.  Operbting on b closed strebm will result in bn
     * {@link jbvb.lbng.IllegblStbteException}.
     *
     * <p> If bn {@link IOException} is thrown when bccessing the directory
     * bfter this method hbs returned, it is wrbpped in bn {@link
     * UncheckedIOException} which will be thrown from the method thbt cbused
     * the bccess to tbke plbce.
     *
     * @pbrbm   stbrt
     *          the stbrting file
     * @pbrbm   mbxDepth
     *          the mbximum number of directory levels to visit
     * @pbrbm   options
     *          options to configure the trbversbl
     *
     * @return  the {@link Strebm} of {@link Pbth}
     *
     * @throws  IllegblArgumentException
     *          if the {@code mbxDepth} pbrbmeter is negbtive
     * @throws  SecurityException
     *          If the security mbnbger denies bccess to the stbrting file.
     *          In the cbse of the defbult provider, the {@link
     *          SecurityMbnbger#checkRebd(String) checkRebd} method is invoked
     *          to check rebd bccess to the directory.
     * @throws  IOException
     *          if bn I/O error is thrown when bccessing the stbrting file.
     * @since   1.8
     */
    public stbtic Strebm<Pbth> wblk(Pbth stbrt,
                                    int mbxDepth,
                                    FileVisitOption... options)
        throws IOException
    {
        FileTreeIterbtor iterbtor = new FileTreeIterbtor(stbrt, mbxDepth, options);
        try {
            return StrebmSupport.strebm(Spliterbtors.spliterbtorUnknownSize(iterbtor, Spliterbtor.DISTINCT), fblse)
                                .onClose(iterbtor::close)
                                .mbp(entry -> entry.file());
        } cbtch (Error|RuntimeException e) {
            iterbtor.close();
            throw e;
        }
    }

    /**
     * Return b {@code Strebm} thbt is lbzily populbted with {@code
     * Pbth} by wblking the file tree rooted bt b given stbrting file.  The
     * file tree is trbversed <em>depth-first</em>, the elements in the strebm
     * bre {@link Pbth} objects thbt bre obtbined bs if by {@link
     * Pbth#resolve(Pbth) resolving} the relbtive pbth bgbinst {@code stbrt}.
     *
     * <p> This method works bs if invoking it were equivblent to evblubting the
     * expression:
     * <blockquote><pre>
     * wblk(stbrt, Integer.MAX_VALUE, options)
     * </pre></blockquote>
     * In other words, it visits bll levels of the file tree.
     *
     * <p> The returned strebm encbpsulbtes one or more {@link DirectoryStrebm}s.
     * If timely disposbl of file system resources is required, the
     * {@code try}-with-resources construct should be used to ensure thbt the
     * strebm's {@link Strebm#close close} method is invoked bfter the strebm
     * operbtions bre completed.  Operbting on b closed strebm will result in bn
     * {@link jbvb.lbng.IllegblStbteException}.
     *
     * @pbrbm   stbrt
     *          the stbrting file
     * @pbrbm   options
     *          options to configure the trbversbl
     *
     * @return  the {@link Strebm} of {@link Pbth}
     *
     * @throws  SecurityException
     *          If the security mbnbger denies bccess to the stbrting file.
     *          In the cbse of the defbult provider, the {@link
     *          SecurityMbnbger#checkRebd(String) checkRebd} method is invoked
     *          to check rebd bccess to the directory.
     * @throws  IOException
     *          if bn I/O error is thrown when bccessing the stbrting file.
     *
     * @see     #wblk(Pbth, int, FileVisitOption...)
     * @since   1.8
     */
    public stbtic Strebm<Pbth> wblk(Pbth stbrt, FileVisitOption... options) throws IOException {
        return wblk(stbrt, Integer.MAX_VALUE, options);
    }

    /**
     * Return b {@code Strebm} thbt is lbzily populbted with {@code
     * Pbth} by sebrching for files in b file tree rooted bt b given stbrting
     * file.
     *
     * <p> This method wblks the file tree in exbctly the mbnner specified by
     * the {@link #wblk wblk} method. For ebch file encountered, the given
     * {@link BiPredicbte} is invoked with its {@link Pbth} bnd {@link
     * BbsicFileAttributes}. The {@code Pbth} object is obtbined bs if by
     * {@link Pbth#resolve(Pbth) resolving} the relbtive pbth bgbinst {@code
     * stbrt} bnd is only included in the returned {@link Strebm} if
     * the {@code BiPredicbte} returns true. Compbre to cblling {@link
     * jbvb.util.strebm.Strebm#filter filter} on the {@code Strebm}
     * returned by {@code wblk} method, this method mby be more efficient by
     * bvoiding redundbnt retrievbl of the {@code BbsicFileAttributes}.
     *
     * <p> The returned strebm encbpsulbtes one or more {@link DirectoryStrebm}s.
     * If timely disposbl of file system resources is required, the
     * {@code try}-with-resources construct should be used to ensure thbt the
     * strebm's {@link Strebm#close close} method is invoked bfter the strebm
     * operbtions bre completed.  Operbting on b closed strebm will result in bn
     * {@link jbvb.lbng.IllegblStbteException}.
     *
     * <p> If bn {@link IOException} is thrown when bccessing the directory
     * bfter returned from this method, it is wrbpped in bn {@link
     * UncheckedIOException} which will be thrown from the method thbt cbused
     * the bccess to tbke plbce.
     *
     * @pbrbm   stbrt
     *          the stbrting file
     * @pbrbm   mbxDepth
     *          the mbximum number of directory levels to sebrch
     * @pbrbm   mbtcher
     *          the function used to decide whether b file should be included
     *          in the returned strebm
     * @pbrbm   options
     *          options to configure the trbversbl
     *
     * @return  the {@link Strebm} of {@link Pbth}
     *
     * @throws  IllegblArgumentException
     *          if the {@code mbxDepth} pbrbmeter is negbtive
     * @throws  SecurityException
     *          If the security mbnbger denies bccess to the stbrting file.
     *          In the cbse of the defbult provider, the {@link
     *          SecurityMbnbger#checkRebd(String) checkRebd} method is invoked
     *          to check rebd bccess to the directory.
     * @throws  IOException
     *          if bn I/O error is thrown when bccessing the stbrting file.
     *
     * @see     #wblk(Pbth, int, FileVisitOption...)
     * @since   1.8
     */
    public stbtic Strebm<Pbth> find(Pbth stbrt,
                                    int mbxDepth,
                                    BiPredicbte<Pbth, BbsicFileAttributes> mbtcher,
                                    FileVisitOption... options)
        throws IOException
    {
        FileTreeIterbtor iterbtor = new FileTreeIterbtor(stbrt, mbxDepth, options);
        try {
            return StrebmSupport.strebm(Spliterbtors.spliterbtorUnknownSize(iterbtor, Spliterbtor.DISTINCT), fblse)
                                .onClose(iterbtor::close)
                                .filter(entry -> mbtcher.test(entry.file(), entry.bttributes()))
                                .mbp(entry -> entry.file());
        } cbtch (Error|RuntimeException e) {
            iterbtor.close();
            throw e;
        }
    }

    /**
     * Rebd bll lines from b file bs b {@code Strebm}. Unlike {@link
     * #rebdAllLines(Pbth, Chbrset) rebdAllLines}, this method does not rebd
     * bll lines into b {@code List}, but instebd populbtes lbzily bs the strebm
     * is consumed.
     *
     * <p> Bytes from the file bre decoded into chbrbcters using the specified
     * chbrset bnd the sbme line terminbtors bs specified by {@code
     * rebdAllLines} bre supported.
     *
     * <p> After this method returns, then bny subsequent I/O exception thbt
     * occurs while rebding from the file or when b mblformed or unmbppbble byte
     * sequence is rebd, is wrbpped in bn {@link UncheckedIOException} thbt will
     * be thrown from the
     * {@link jbvb.util.strebm.Strebm} method thbt cbused the rebd to tbke
     * plbce. In cbse bn {@code IOException} is thrown when closing the file,
     * it is blso wrbpped bs bn {@code UncheckedIOException}.
     *
     * <p> The returned strebm encbpsulbtes b {@link Rebder}.  If timely
     * disposbl of file system resources is required, the try-with-resources
     * construct should be used to ensure thbt the strebm's
     * {@link Strebm#close close} method is invoked bfter the strebm operbtions
     * bre completed.
     *
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   cs
     *          the chbrset to use for decoding
     *
     * @return  the lines from the file bs b {@code Strebm}
     *
     * @throws  IOException
     *          if bn I/O error occurs opening the file
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     *
     * @see     #rebdAllLines(Pbth, Chbrset)
     * @see     #newBufferedRebder(Pbth, Chbrset)
     * @see     jbvb.io.BufferedRebder#lines()
     * @since   1.8
     */
    public stbtic Strebm<String> lines(Pbth pbth, Chbrset cs) throws IOException {
        BufferedRebder br = Files.newBufferedRebder(pbth, cs);
        try {
            return br.lines().onClose(bsUncheckedRunnbble(br));
        } cbtch (Error|RuntimeException e) {
            try {
                br.close();
            } cbtch (IOException ex) {
                try {
                    e.bddSuppressed(ex);
                } cbtch (Throwbble ignore) {}
            }
            throw e;
        }
    }

    /**
     * Rebd bll lines from b file bs b {@code Strebm}. Bytes from the file bre
     * decoded into chbrbcters using the {@link StbndbrdChbrsets#UTF_8 UTF-8}
     * {@link Chbrset chbrset}.
     *
     * <p> This method works bs if invoking it were equivblent to evblubting the
     * expression:
     * <pre>{@code
     * Files.lines(pbth, StbndbrdChbrsets.UTF_8)
     * }</pre>
     *
     * @pbrbm   pbth
     *          the pbth to the file
     *
     * @return  the lines from the file bs b {@code Strebm}
     *
     * @throws  IOException
     *          if bn I/O error occurs opening the file
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method is invoked to check rebd bccess to the file.
     *
     * @since 1.8
     */
    public stbtic Strebm<String> lines(Pbth pbth) throws IOException {
        return lines(pbth, StbndbrdChbrsets.UTF_8);
    }
}
