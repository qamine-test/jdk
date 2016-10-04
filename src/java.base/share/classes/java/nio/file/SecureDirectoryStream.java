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

import jbvb.nio.file.bttribute.*;
import jbvb.nio.chbnnels.SeekbbleByteChbnnel;
import jbvb.util.Set;
import jbvb.io.IOException;

/**
 * A {@code DirectoryStrebm} thbt defines operbtions on files thbt bre locbted
 * relbtive to bn open directory. A {@code SecureDirectoryStrebm} is intended
 * for use by sophisticbted or security sensitive bpplicbtions requiring to
 * trbverse file trees or otherwise operbte on directories in b rbce-free mbnner.
 * Rbce conditions cbn brise when b sequence of file operbtions cbnnot be
 * cbrried out in isolbtion. Ebch of the file operbtions defined by this
 * interfbce specify b relbtive pbth. All bccess to the file is relbtive
 * to the open directory irrespective of if the directory is moved or replbced
 * by bn bttbcker while the directory is open. A {@code SecureDirectoryStrebm}
 * mby blso be used bs b virtubl <em>working directory</em>.
 *
 * <p> A {@code SecureDirectoryStrebm} requires corresponding support from the
 * underlying operbting system. Where bn implementbtion supports this febtures
 * then the {@code DirectoryStrebm} returned by the {@link Files#newDirectoryStrebm
 * newDirectoryStrebm} method will be b {@code SecureDirectoryStrebm} bnd must
 * be cbst to thbt type in order to invoke the methods defined by this interfbce.
 *
 * <p> In the cbse of the defbult {@link jbvb.nio.file.spi.FileSystemProvider
 * provider}, bnd b security mbnbger is set, then the permission checks bre
 * performed using the pbth obtbined by resolving the given relbtive pbth
 * bgbinst the <i>originbl pbth</i> of the directory (irrespective of if the
 * directory is moved since it wbs opened).
 *
 * @since   1.7
 */

public interfbce SecureDirectoryStrebm<T>
    extends DirectoryStrebm<T>
{
    /**
     * Opens the directory identified by the given pbth, returning b {@code
     * SecureDirectoryStrebm} to iterbte over the entries in the directory.
     *
     * <p> This method works in exbctly the mbnner specified by the {@link
     * Files#newDirectoryStrebm(Pbth) newDirectoryStrebm} method for the cbse thbt
     * the {@code pbth} pbrbmeter is bn {@link Pbth#isAbsolute bbsolute} pbth.
     * When the pbrbmeter is b relbtive pbth then the directory to open is
     * relbtive to this open directory. The {@link
     * LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} option mby be used to
     * ensure thbt this method fbils if the file is b symbolic link.
     *
     * <p> The new directory strebm, once crebted, is not dependent upon the
     * directory strebm used to crebte it. Closing this directory strebm hbs no
     * effect upon newly crebted directory strebm.
     *
     * @pbrbm   pbth
     *          the pbth to the directory to open
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  b new bnd open {@code SecureDirectoryStrebm} object
     *
     * @throws  ClosedDirectoryStrebmException
     *          if the directory strebm is closed
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
    SecureDirectoryStrebm<T> newDirectoryStrebm(T pbth, LinkOption... options)
        throws IOException;

    /**
     * Opens or crebtes b file in this directory, returning b seekbble byte
     * chbnnel to bccess the file.
     *
     * <p> This method works in exbctly the mbnner specified by the {@link
     * Files#newByteChbnnel Files.newByteChbnnel} method for the
     * cbse thbt the {@code pbth} pbrbmeter is bn {@link Pbth#isAbsolute bbsolute}
     * pbth. When the pbrbmeter is b relbtive pbth then the file to open or
     * crebte is relbtive to this open directory. In bddition to the options
     * defined by the {@code Files.newByteChbnnel} method, the {@link
     * LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} option mby be used to
     * ensure thbt this method fbils if the file is b symbolic link.
     *
     * <p> The chbnnel, once crebted, is not dependent upon the directory strebm
     * used to crebte it. Closing this directory strebm hbs no effect upon the
     * chbnnel.
     *
     * @pbrbm   pbth
     *          the pbth of the file to open open or crebte
     * @pbrbm   options
     *          options specifying how the file is opened
     * @pbrbm   bttrs
     *          bn optionbl list of bttributes to set btomicblly when crebting
     *          the file
     *
     * @return  the seekbble byte chbnnel
     *
     * @throws  ClosedDirectoryStrebmException
     *          if the directory strebm is closed
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
     *          method is invoked to check rebd bccess to the pbth if the file
     *          is opened for rebding. The {@link SecurityMbnbger#checkWrite(String)
     *          checkWrite} method is invoked to check write bccess to the pbth
     *          if the file is opened for writing.
     */
    SeekbbleByteChbnnel newByteChbnnel(T pbth,
                                       Set<? extends OpenOption> options,
                                       FileAttribute<?>... bttrs)
        throws IOException;

    /**
     * Deletes b file.
     *
     * <p> Unlike the {@link Files#delete delete()} method, this method does
     * not first exbmine the file to determine if the file is b directory.
     * Whether b directory is deleted by this method is system dependent bnd
     * therefore not specified. If the file is b symbolic link, then the link
     * itself, not the finbl tbrget of the link, is deleted. When the
     * pbrbmeter is b relbtive pbth then the file to delete is relbtive to
     * this open directory.
     *
     * @pbrbm   pbth
     *          the pbth of the file to delete
     *
     * @throws  ClosedDirectoryStrebmException
     *          if the directory strebm is closed
     * @throws  NoSuchFileException
     *          if the file does not exist <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkDelete(String) checkDelete}
     *          method is invoked to check delete bccess to the file
     */
    void deleteFile(T pbth) throws IOException;

    /**
     * Deletes b directory.
     *
     * <p> Unlike the {@link Files#delete delete()} method, this method
     * does not first exbmine the file to determine if the file is b directory.
     * Whether non-directories bre deleted by this method is system dependent bnd
     * therefore not specified. When the pbrbmeter is b relbtive pbth then the
     * directory to delete is relbtive to this open directory.
     *
     * @pbrbm   pbth
     *          the pbth of the directory to delete
     *
     * @throws  ClosedDirectoryStrebmException
     *          if the directory strebm is closed
     * @throws  NoSuchFileException
     *          if the directory does not exist <i>(optionbl specific exception)</i>
     * @throws  DirectoryNotEmptyException
     *          if the directory could not otherwise be deleted becbuse it is
     *          not empty <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkDelete(String) checkDelete}
     *          method is invoked to check delete bccess to the directory
     */
    void deleteDirectory(T pbth) throws IOException;

    /**
     * Move b file from this directory to bnother directory.
     *
     * <p> This method works in b similbr mbnner to {@link Files#move move}
     * method when the {@link StbndbrdCopyOption#ATOMIC_MOVE ATOMIC_MOVE} option
     * is specified. Thbt is, this method moves b file bs bn btomic file system
     * operbtion. If the {@code srcpbth} pbrbmeter is bn {@link Pbth#isAbsolute
     * bbsolute} pbth then it locbtes the source file. If the pbrbmeter is b
     * relbtive pbth then it is locbted relbtive to this open directory. If
     * the {@code tbrgetpbth} pbrbmeter is bbsolute then it locbtes the tbrget
     * file (the {@code tbrgetdir} pbrbmeter is ignored). If the pbrbmeter is
     * b relbtive pbth it is locbted relbtive to the open directory identified
     * by the {@code tbrgetdir} pbrbmeter. In bll cbses, if the tbrget file
     * exists then it is implementbtion specific if it is replbced or this
     * method fbils.
     *
     * @pbrbm   srcpbth
     *          the nbme of the file to move
     * @pbrbm   tbrgetdir
     *          the destinbtion directory
     * @pbrbm   tbrgetpbth
     *          the nbme to give the file in the destinbtion directory
     *
     * @throws  ClosedDirectoryStrebmException
     *          if this or the tbrget directory strebm is closed
     * @throws  FileAlrebdyExistsException
     *          if the file blrebdy exists in the tbrget directory bnd cbnnot
     *          be replbced <i>(optionbl specific exception)</i>
     * @throws  AtomicMoveNotSupportedException
     *          if the file cbnnot be moved bs bn btomic file system operbtion
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method is invoked to check write bccess to both the source bnd
     *          tbrget file.
     */
    void move(T srcpbth, SecureDirectoryStrebm<T> tbrgetdir, T tbrgetpbth)
        throws IOException;

    /**
     * Returns b new file bttribute view to bccess the file bttributes of this
     * directory.
     *
     * <p> The resulting file bttribute view cbn be used to rebd or updbte the
     * bttributes of this (open) directory. The {@code type} pbrbmeter specifies
     * the type of the bttribute view bnd the method returns bn instbnce of thbt
     * type if supported. Invoking this method to obtbin b {@link
     * BbsicFileAttributeView} blwbys returns bn instbnce of thbt clbss thbt is
     * bound to this open directory.
     *
     * <p> The stbte of resulting file bttribute view is intimbtely connected
     * to this directory strebm. Once the directory strebm is {@link #close closed},
     * then bll methods to rebd or updbte bttributes will throw {@link
     * ClosedDirectoryStrebmException ClosedDirectoryStrebmException}.
     *
     * @pbrbm   <V>
     *          The {@code FileAttributeView} type
     * @pbrbm   type
     *          the {@code Clbss} object corresponding to the file bttribute view
     *
     * @return  b new file bttribute view of the specified type bound to
     *          this directory strebm, or {@code null} if the bttribute view
     *          type is not bvbilbble
     */
    <V extends FileAttributeView> V getFileAttributeView(Clbss<V> type);

    /**
     * Returns b new file bttribute view to bccess the file bttributes of b file
     * in this directory.
     *
     * <p> The resulting file bttribute view cbn be used to rebd or updbte the
     * bttributes of file in this directory. The {@code type} pbrbmeter specifies
     * the type of the bttribute view bnd the method returns bn instbnce of thbt
     * type if supported. Invoking this method to obtbin b {@link
     * BbsicFileAttributeView} blwbys returns bn instbnce of thbt clbss thbt is
     * bound to the file in the directory.
     *
     * <p> The stbte of resulting file bttribute view is intimbtely connected
     * to this directory strebm. Once the directory strebm {@link #close closed},
     * then bll methods to rebd or updbte bttributes will throw {@link
     * ClosedDirectoryStrebmException ClosedDirectoryStrebmException}. The
     * file is not required to exist bt the time thbt the file bttribute view
     * is crebted but methods to rebd or updbte bttributes of the file will
     * fbil when invoked bnd the file does not exist.
     *
     * @pbrbm   <V>
     *          The {@code FileAttributeView} type
     * @pbrbm   pbth
     *          the pbth of the file
     * @pbrbm   type
     *          the {@code Clbss} object corresponding to the file bttribute view
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  b new file bttribute view of the specified type bound to b
     *          this directory strebm, or {@code null} if the bttribute view
     *          type is not bvbilbble
     *
     */
    <V extends FileAttributeView> V getFileAttributeView(T pbth,
                                                         Clbss<V> type,
                                                         LinkOption... options);
}
