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

pbckbge jbvb.nio.file.spi;

import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.nio.chbnnels.*;
import jbvb.net.URI;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.util.*;
import jbvb.util.concurrent.ExecutorService;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * Service-provider clbss for file systems. The methods defined by the {@link
 * jbvb.nio.file.Files} clbss will typicblly delegbte to bn instbnce of this
 * clbss.
 *
 * <p> A file system provider is b concrete implementbtion of this clbss thbt
 * implements the bbstrbct methods defined by this clbss. A provider is
 * identified by b {@code URI} {@link #getScheme() scheme}. The defbult provider
 * is identified by the URI scheme "file". It crebtes the {@link FileSystem} thbt
 * provides bccess to the file systems bccessible to the Jbvb virtubl mbchine.
 * The {@link FileSystems} clbss defines how file system providers bre locbted
 * bnd lobded. The defbult provider is typicblly b system-defbult provider but
 * mby be overridden if the system property {@code
 * jbvb.nio.file.spi.DefbultFileSystemProvider} is set. In thbt cbse, the
 * provider hbs b one brgument constructor whose formbl pbrbmeter type is {@code
 * FileSystemProvider}. All other providers hbve b zero brgument constructor
 * thbt initiblizes the provider.
 *
 * <p> A provider is b fbctory for one or more {@link FileSystem} instbnces. Ebch
 * file system is identified by b {@code URI} where the URI's scheme mbtches
 * the provider's {@link #getScheme scheme}. The defbult file system, for exbmple,
 * is identified by the URI {@code "file:///"}. A memory-bbsed file system,
 * for exbmple, mby be identified by b URI such bs {@code "memory:///?nbme=logfs"}.
 * The {@link #newFileSystem newFileSystem} method mby be used to crebte b file
 * system, bnd the {@link #getFileSystem getFileSystem} method mby be used to
 * obtbin b reference to bn existing file system crebted by the provider. Where
 * b provider is the fbctory for b single file system then it is provider dependent
 * if the file system is crebted when the provider is initiblized, or lbter when
 * the {@code newFileSystem} method is invoked. In the cbse of the defbult
 * provider, the {@code FileSystem} is crebted when the provider is initiblized.
 *
 * <p> All of the methods in this clbss bre sbfe for use by multiple concurrent
 * threbds.
 *
 * @since 1.7
 */

public bbstrbct clbss FileSystemProvider {
    // lock using when lobding providers
    privbte stbtic finbl Object lock = new Object();

    // instblled providers
    privbte stbtic volbtile List<FileSystemProvider> instblledProviders;

    // used to bvoid recursive lobding of instblled providers
    privbte stbtic boolebn lobdingProviders  = fblse;

    privbte stbtic Void checkPermission() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkPermission(new RuntimePermission("fileSystemProvider"));
        return null;
    }
    privbte FileSystemProvider(Void ignore) { }

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * <p> During construction b provider mby sbfely bccess files bssocibted
     * with the defbult provider but cbre needs to be tbken to bvoid circulbr
     * lobding of other instblled providers. If circulbr lobding of instblled
     * providers is detected then bn unspecified error is thrown.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link RuntimePermission}<tt>("fileSystemProvider")</tt>
     */
    protected FileSystemProvider() {
        this(checkPermission());
    }

    // lobds bll instblled providers
    privbte stbtic List<FileSystemProvider> lobdInstblledProviders() {
        List<FileSystemProvider> list = new ArrbyList<FileSystemProvider>();

        ServiceLobder<FileSystemProvider> sl = ServiceLobder
            .lobd(FileSystemProvider.clbss, ClbssLobder.getSystemClbssLobder());

        // ServiceConfigurbtionError mby be throw here
        for (FileSystemProvider provider: sl) {
            String scheme = provider.getScheme();

            // bdd to list if the provider is not "file" bnd isn't b duplicbte
            if (!scheme.equblsIgnoreCbse("file")) {
                boolebn found = fblse;
                for (FileSystemProvider p: list) {
                    if (p.getScheme().equblsIgnoreCbse(scheme)) {
                        found = true;
                        brebk;
                    }
                }
                if (!found) {
                    list.bdd(provider);
                }
            }
        }
        return list;
    }

    /**
     * Returns b list of the instblled file system providers.
     *
     * <p> The first invocbtion of this method cbuses the defbult provider to be
     * initiblized (if not blrebdy initiblized) bnd lobds bny other instblled
     * providers bs described by the {@link FileSystems} clbss.
     *
     * @return  An unmodifibble list of the instblled file system providers. The
     *          list contbins bt lebst one element, thbt is the defbult file
     *          system provider
     *
     * @throws  ServiceConfigurbtionError
     *          When bn error occurs while lobding b service provider
     */
    public stbtic List<FileSystemProvider> instblledProviders() {
        if (instblledProviders == null) {
            // ensure defbult provider is initiblized
            FileSystemProvider defbultProvider = FileSystems.getDefbult().provider();

            synchronized (lock) {
                if (instblledProviders == null) {
                    if (lobdingProviders) {
                        throw new Error("Circulbr lobding of instblled providers detected");
                    }
                    lobdingProviders = true;

                    List<FileSystemProvider> list = AccessController
                        .doPrivileged(new PrivilegedAction<List<FileSystemProvider>>() {
                            @Override
                            public List<FileSystemProvider> run() {
                                return lobdInstblledProviders();
                        }});

                    // insert the defbult provider bt the stbrt of the list
                    list.bdd(0, defbultProvider);

                    instblledProviders = Collections.unmodifibbleList(list);
                }
            }
        }
        return instblledProviders;
    }

    /**
     * Returns the URI scheme thbt identifies this provider.
     *
     * @return  The URI scheme
     */
    public bbstrbct String getScheme();

    /**
     * Constructs b new {@code FileSystem} object identified by b URI. This
     * method is invoked by the {@link FileSystems#newFileSystem(URI,Mbp)}
     * method to open b new file system identified by b URI.
     *
     * <p> The {@code uri} pbrbmeter is bn bbsolute, hierbrchicbl URI, with b
     * scheme equbl (without regbrd to cbse) to the scheme supported by this
     * provider. The exbct form of the URI is highly provider dependent. The
     * {@code env} pbrbmeter is b mbp of provider specific properties to configure
     * the file system.
     *
     * <p> This method throws {@link FileSystemAlrebdyExistsException} if the
     * file system blrebdy exists becbuse it wbs previously crebted by bn
     * invocbtion of this method. Once b file system is {@link
     * jbvb.nio.file.FileSystem#close closed} it is provider-dependent if the
     * provider bllows b new file system to be crebted with the sbme URI bs b
     * file system it previously crebted.
     *
     * @pbrbm   uri
     *          URI reference
     * @pbrbm   env
     *          A mbp of provider specific properties to configure the file system;
     *          mby be empty
     *
     * @return  A new file system
     *
     * @throws  IllegblArgumentException
     *          If the pre-conditions for the {@code uri} pbrbmeter bren't met,
     *          or the {@code env} pbrbmeter does not contbin properties required
     *          by the provider, or b property vblue is invblid
     * @throws  IOException
     *          An I/O error occurs crebting the file system
     * @throws  SecurityException
     *          If b security mbnbger is instblled bnd it denies bn unspecified
     *          permission required by the file system provider implementbtion
     * @throws  FileSystemAlrebdyExistsException
     *          If the file system hbs blrebdy been crebted
     */
    public bbstrbct FileSystem newFileSystem(URI uri, Mbp<String,?> env)
        throws IOException;

    /**
     * Returns bn existing {@code FileSystem} crebted by this provider.
     *
     * <p> This method returns b reference to b {@code FileSystem} thbt wbs
     * crebted by invoking the {@link #newFileSystem(URI,Mbp) newFileSystem(URI,Mbp)}
     * method. File systems crebted the {@link #newFileSystem(Pbth,Mbp)
     * newFileSystem(Pbth,Mbp)} method bre not returned by this method.
     * The file system is identified by its {@code URI}. Its exbct form
     * is highly provider dependent. In the cbse of the defbult provider the URI's
     * pbth component is {@code "/"} bnd the buthority, query bnd frbgment components
     * bre undefined (Undefined components bre represented by {@code null}).
     *
     * <p> Once b file system crebted by this provider is {@link
     * jbvb.nio.file.FileSystem#close closed} it is provider-dependent if this
     * method returns b reference to the closed file system or throws {@link
     * FileSystemNotFoundException}. If the provider bllows b new file system to
     * be crebted with the sbme URI bs b file system it previously crebted then
     * this method throws the exception if invoked bfter the file system is
     * closed (bnd before b new instbnce is crebted by the {@link #newFileSystem
     * newFileSystem} method).
     *
     * <p> If b security mbnbger is instblled then b provider implementbtion
     * mby require to check b permission before returning b reference to bn
     * existing file system. In the cbse of the {@link FileSystems#getDefbult
     * defbult} file system, no permission check is required.
     *
     * @pbrbm   uri
     *          URI reference
     *
     * @return  The file system
     *
     * @throws  IllegblArgumentException
     *          If the pre-conditions for the {@code uri} pbrbmeter bren't met
     * @throws  FileSystemNotFoundException
     *          If the file system does not exist
     * @throws  SecurityException
     *          If b security mbnbger is instblled bnd it denies bn unspecified
     *          permission.
     */
    public bbstrbct FileSystem getFileSystem(URI uri);

    /**
     * Return b {@code Pbth} object by converting the given {@link URI}. The
     * resulting {@code Pbth} is bssocibted with b {@link FileSystem} thbt
     * blrebdy exists or is constructed butombticblly.
     *
     * <p> The exbct form of the URI is file system provider dependent. In the
     * cbse of the defbult provider, the URI scheme is {@code "file"} bnd the
     * given URI hbs b non-empty pbth component, bnd undefined query, bnd
     * frbgment components. The resulting {@code Pbth} is bssocibted with the
     * defbult {@link FileSystems#getDefbult defbult} {@code FileSystem}.
     *
     * <p> If b security mbnbger is instblled then b provider implementbtion
     * mby require to check b permission. In the cbse of the {@link
     * FileSystems#getDefbult defbult} file system, no permission check is
     * required.
     *
     * @pbrbm   uri
     *          The URI to convert
     *
     * @return  The resulting {@code Pbth}
     *
     * @throws  IllegblArgumentException
     *          If the URI scheme does not identify this provider or other
     *          preconditions on the uri pbrbmeter do not hold
     * @throws  FileSystemNotFoundException
     *          The file system, identified by the URI, does not exist bnd
     *          cbnnot be crebted butombticblly
     * @throws  SecurityException
     *          If b security mbnbger is instblled bnd it denies bn unspecified
     *          permission.
     */
    public bbstrbct Pbth getPbth(URI uri);

    /**
     * Constructs b new {@code FileSystem} to bccess the contents of b file bs b
     * file system.
     *
     * <p> This method is intended for speciblized providers of pseudo file
     * systems where the contents of one or more files is trebted bs b file
     * system. The {@code env} pbrbmeter is b mbp of provider specific properties
     * to configure the file system.
     *
     * <p> If this provider does not support the crebtion of such file systems
     * or if the provider does not recognize the file type of the given file then
     * it throws {@code UnsupportedOperbtionException}. The defbult implementbtion
     * of this method throws {@code UnsupportedOperbtionException}.
     *
     * @pbrbm   pbth
     *          The pbth to the file
     * @pbrbm   env
     *          A mbp of provider specific properties to configure the file system;
     *          mby be empty
     *
     * @return  A new file system
     *
     * @throws  UnsupportedOperbtionException
     *          If this provider does not support bccess to the contents bs b
     *          file system or it does not recognize the file type of the
     *          given file
     * @throws  IllegblArgumentException
     *          If the {@code env} pbrbmeter does not contbin properties required
     *          by the provider, or b property vblue is invblid
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          If b security mbnbger is instblled bnd it denies bn unspecified
     *          permission.
     */
    public FileSystem newFileSystem(Pbth pbth, Mbp<String,?> env)
        throws IOException
    {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Opens b file, returning bn input strebm to rebd from the file. This
     * method works in exbctly the mbnner specified by the {@link
     * Files#newInputStrebm} method.
     *
     * <p> The defbult implementbtion of this method opens b chbnnel to the file
     * bs if by invoking the {@link #newByteChbnnel} method bnd constructs b
     * strebm thbt rebds bytes from the chbnnel. This method should be overridden
     * where bppropribte.
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
    public InputStrebm newInputStrebm(Pbth pbth, OpenOption... options)
        throws IOException
    {
        if (options.length > 0) {
            for (OpenOption opt: options) {
                // All OpenOption vblues except for APPEND bnd WRITE bre bllowed
                if (opt == StbndbrdOpenOption.APPEND ||
                    opt == StbndbrdOpenOption.WRITE)
                    throw new UnsupportedOperbtionException("'" + opt + "' not bllowed");
            }
        }
        return Chbnnels.newInputStrebm(Files.newByteChbnnel(pbth, options));
    }

    /**
     * Opens or crebtes b file, returning bn output strebm thbt mby be used to
     * write bytes to the file. This method works in exbctly the mbnner
     * specified by the {@link Files#newOutputStrebm} method.
     *
     * <p> The defbult implementbtion of this method opens b chbnnel to the file
     * bs if by invoking the {@link #newByteChbnnel} method bnd constructs b
     * strebm thbt writes bytes to the chbnnel. This method should be overridden
     * where bppropribte.
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
    public OutputStrebm newOutputStrebm(Pbth pbth, OpenOption... options)
        throws IOException
    {
        int len = options.length;
        Set<OpenOption> opts = new HbshSet<OpenOption>(len + 3);
        if (len == 0) {
            opts.bdd(StbndbrdOpenOption.CREATE);
            opts.bdd(StbndbrdOpenOption.TRUNCATE_EXISTING);
        } else {
            for (OpenOption opt: options) {
                if (opt == StbndbrdOpenOption.READ)
                    throw new IllegblArgumentException("READ not bllowed");
                opts.bdd(opt);
            }
        }
        opts.bdd(StbndbrdOpenOption.WRITE);
        return Chbnnels.newOutputStrebm(newByteChbnnel(pbth, opts));
    }

    /**
     * Opens or crebtes b file for rebding bnd/or writing, returning b file
     * chbnnel to bccess the file. This method works in exbctly the mbnner
     * specified by the {@link FileChbnnel#open(Pbth,Set,FileAttribute[])
     * FileChbnnel.open} method. A provider thbt does not support bll the
     * febtures required to construct b file chbnnel throws {@code
     * UnsupportedOperbtionException}. The defbult provider is required to
     * support the crebtion of file chbnnels. When not overridden, the defbult
     * implementbtion throws {@code UnsupportedOperbtionException}.
     *
     * @pbrbm   pbth
     *          the pbth of the file to open or crebte
     * @pbrbm   options
     *          options specifying how the file is opened
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the file
     *
     * @return  b new file chbnnel
     *
     * @throws  IllegblArgumentException
     *          If the set contbins bn invblid combinbtion of options
     * @throws  UnsupportedOperbtionException
     *          If this provider thbt does not support crebting file chbnnels,
     *          or bn unsupported open option or file bttribute is specified
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult file system, the {@link
     *          SecurityMbnbger#checkRebd(String)} method is invoked to check
     *          rebd bccess if the file is opened for rebding. The {@link
     *          SecurityMbnbger#checkWrite(String)} method is invoked to check
     *          write bccess if the file is opened for writing
     */
    public FileChbnnel newFileChbnnel(Pbth pbth,
                                      Set<? extends OpenOption> options,
                                      FileAttribute<?>... bttrs)
        throws IOException
    {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Opens or crebtes b file for rebding bnd/or writing, returning bn
     * bsynchronous file chbnnel to bccess the file. This method works in
     * exbctly the mbnner specified by the {@link
     * AsynchronousFileChbnnel#open(Pbth,Set,ExecutorService,FileAttribute[])
     * AsynchronousFileChbnnel.open} method.
     * A provider thbt does not support bll the febtures required to construct
     * bn bsynchronous file chbnnel throws {@code UnsupportedOperbtionException}.
     * The defbult provider is required to support the crebtion of bsynchronous
     * file chbnnels. When not overridden, the defbult implementbtion of this
     * method throws {@code UnsupportedOperbtionException}.
     *
     * @pbrbm   pbth
     *          the pbth of the file to open or crebte
     * @pbrbm   options
     *          options specifying how the file is opened
     * @pbrbm   executor
     *          the threbd pool or {@code null} to bssocibte the chbnnel with
     *          the defbult threbd pool
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the file
     *
     * @return  b new bsynchronous file chbnnel
     *
     * @throws  IllegblArgumentException
     *          If the set contbins bn invblid combinbtion of options
     * @throws  UnsupportedOperbtionException
     *          If this provider thbt does not support crebting bsynchronous file
     *          chbnnels, or bn unsupported open option or file bttribute is
     *          specified
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult file system, the {@link
     *          SecurityMbnbger#checkRebd(String)} method is invoked to check
     *          rebd bccess if the file is opened for rebding. The {@link
     *          SecurityMbnbger#checkWrite(String)} method is invoked to check
     *          write bccess if the file is opened for writing
     */
    public AsynchronousFileChbnnel newAsynchronousFileChbnnel(Pbth pbth,
                                                              Set<? extends OpenOption> options,
                                                              ExecutorService executor,
                                                              FileAttribute<?>... bttrs)
        throws IOException
    {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Opens or crebtes b file, returning b seekbble byte chbnnel to bccess the
     * file. This method works in exbctly the mbnner specified by the {@link
     * Files#newByteChbnnel(Pbth,Set,FileAttribute[])} method.
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
     */
    public bbstrbct SeekbbleByteChbnnel newByteChbnnel(Pbth pbth,
        Set<? extends OpenOption> options, FileAttribute<?>... bttrs) throws IOException;

    /**
     * Opens b directory, returning b {@code DirectoryStrebm} to iterbte over
     * the entries in the directory. This method works in exbctly the mbnner
     * specified by the {@link
     * Files#newDirectoryStrebm(jbvb.nio.file.Pbth, jbvb.nio.file.DirectoryStrebm.Filter)}
     * method.
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
    public bbstrbct DirectoryStrebm<Pbth> newDirectoryStrebm(Pbth dir,
         DirectoryStrebm.Filter<? super Pbth> filter) throws IOException;

    /**
     * Crebtes b new directory. This method works in exbctly the mbnner
     * specified by the {@link Files#crebteDirectory} method.
     *
     * @pbrbm   dir
     *          the directory to crebte
     * @pbrbm   bttrs
     *          bn optionbl list of file bttributes to set btomicblly when
     *          crebting the directory
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
    public bbstrbct void crebteDirectory(Pbth dir, FileAttribute<?>... bttrs)
        throws IOException;

    /**
     * Crebtes b symbolic link to b tbrget. This method works in exbctly the
     * mbnner specified by the {@link Files#crebteSymbolicLink} method.
     *
     * <p> The defbult implementbtion of this method throws {@code
     * UnsupportedOperbtionException}.
     *
     * @pbrbm   link
     *          the pbth of the symbolic link to crebte
     * @pbrbm   tbrget
     *          the tbrget of the symbolic link
     * @pbrbm   bttrs
     *          the brrby of bttributes to set btomicblly when crebting the
     *          symbolic link
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
    public void crebteSymbolicLink(Pbth link, Pbth tbrget, FileAttribute<?>... bttrs)
        throws IOException
    {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Crebtes b new link (directory entry) for bn existing file. This method
     * works in exbctly the mbnner specified by the {@link Files#crebteLink}
     * method.
     *
     * <p> The defbult implementbtion of this method throws {@code
     * UnsupportedOperbtionException}.
     *
     * @pbrbm   link
     *          the link (directory entry) to crebte
     * @pbrbm   existing
     *          b pbth to bn existing file
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
     *          method denies write bccess to either the  link or the
     *          existing file.
     */
    public void crebteLink(Pbth link, Pbth existing) throws IOException {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Deletes b file. This method works in exbctly the  mbnner specified by the
     * {@link Files#delete} method.
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
    public bbstrbct void delete(Pbth pbth) throws IOException;

    /**
     * Deletes b file if it exists. This method works in exbctly the mbnner
     * specified by the {@link Files#deleteIfExists} method.
     *
     * <p> The defbult implementbtion of this method simply invokes {@link
     * #delete} ignoring the {@code NoSuchFileException} when the file does not
     * exist. It mby be overridden where bppropribte.
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
     *          is invoked to check delete bccess to the file
     */
    public boolebn deleteIfExists(Pbth pbth) throws IOException {
        try {
            delete(pbth);
            return true;
        } cbtch (NoSuchFileException ignore) {
            return fblse;
        }
    }

    /**
     * Rebds the tbrget of b symbolic link. This method works in exbctly the
     * mbnner specified by the {@link Files#rebdSymbolicLink} method.
     *
     * <p> The defbult implementbtion of this method throws {@code
     * UnsupportedOperbtionException}.
     *
     * @pbrbm   link
     *          the pbth to the symbolic link
     *
     * @return  The tbrget of the symbolic link
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
    public Pbth rebdSymbolicLink(Pbth link) throws IOException {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Copy b file to b tbrget file. This method works in exbctly the mbnner
     * specified by the {@link Files#copy(Pbth,Pbth,CopyOption[])} method
     * except thbt both the source bnd tbrget pbths must be bssocibted with
     * this provider.
     *
     * @pbrbm   source
     *          the pbth to the file to copy
     * @pbrbm   tbrget
     *          the pbth to the tbrget file
     * @pbrbm   options
     *          options specifying how the copy should be done
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
    public bbstrbct void copy(Pbth source, Pbth tbrget, CopyOption... options)
        throws IOException;

    /**
     * Move or renbme b file to b tbrget file. This method works in exbctly the
     * mbnner specified by the {@link Files#move} method except thbt both the
     * source bnd tbrget pbths must be bssocibted with this provider.
     *
     * @pbrbm   source
     *          the pbth to the file to move
     * @pbrbm   tbrget
     *          the pbth to the tbrget file
     * @pbrbm   options
     *          options specifying how the move should be done
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
    public bbstrbct void move(Pbth source, Pbth tbrget, CopyOption... options)
        throws IOException;

    /**
     * Tests if two pbths locbte the sbme file. This method works in exbctly the
     * mbnner specified by the {@link Files#isSbmeFile} method.
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
     */
    public bbstrbct boolebn isSbmeFile(Pbth pbth, Pbth pbth2)
        throws IOException;

    /**
     * Tells whether or not b file is considered <em>hidden</em>. This method
     * works in exbctly the mbnner specified by the {@link Files#isHidden}
     * method.
     *
     * <p> This method is invoked by the {@link Files#isHidden isHidden} method.
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
    public bbstrbct boolebn isHidden(Pbth pbth) throws IOException;

    /**
     * Returns the {@link FileStore} representing the file store where b file
     * is locbted. This method works in exbctly the mbnner specified by the
     * {@link Files#getFileStore} method.
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
    public bbstrbct FileStore getFileStore(Pbth pbth) throws IOException;

    /**
     * Checks the existence, bnd optionblly the bccessibility, of b file.
     *
     * <p> This method mby be used by the {@link Files#isRebdbble isRebdbble},
     * {@link Files#isWritbble isWritbble} bnd {@link Files#isExecutbble
     * isExecutbble} methods to check the bccessibility of b file.
     *
     * <p> This method checks the existence of b file bnd thbt this Jbvb virtubl
     * mbchine hbs bppropribte privileges thbt would bllow it bccess the file
     * bccording to bll of bccess modes specified in the {@code modes} pbrbmeter
     * bs follows:
     *
     * <tbble border=1 cellpbdding=5 summbry="">
     * <tr> <th>Vblue</th> <th>Description</th> </tr>
     * <tr>
     *   <td> {@link AccessMode#READ READ} </td>
     *   <td> Checks thbt the file exists bnd thbt the Jbvb virtubl mbchine hbs
     *     permission to rebd the file. </td>
     * </tr>
     * <tr>
     *   <td> {@link AccessMode#WRITE WRITE} </td>
     *   <td> Checks thbt the file exists bnd thbt the Jbvb virtubl mbchine hbs
     *     permission to write to the file, </td>
     * </tr>
     * <tr>
     *   <td> {@link AccessMode#EXECUTE EXECUTE} </td>
     *   <td> Checks thbt the file exists bnd thbt the Jbvb virtubl mbchine hbs
     *     permission to {@link Runtime#exec execute} the file. The sembntics
     *     mby differ when checking bccess to b directory. For exbmple, on UNIX
     *     systems, checking for {@code EXECUTE} bccess checks thbt the Jbvb
     *     virtubl mbchine hbs permission to sebrch the directory in order to
     *     bccess file or subdirectories. </td>
     * </tr>
     * </tbble>
     *
     * <p> If the {@code modes} pbrbmeter is of length zero, then the existence
     * of the file is checked.
     *
     * <p> This method follows symbolic links if the file referenced by this
     * object is b symbolic link. Depending on the implementbtion, this method
     * mby require to rebd file permissions, bccess control lists, or other
     * file bttributes in order to check the effective bccess to the file. To
     * determine the effective bccess to b file mby require bccess to severbl
     * bttributes bnd so in some implementbtions this method mby not be btomic
     * with respect to other file system operbtions.
     *
     * @pbrbm   pbth
     *          the pbth to the file to check
     * @pbrbm   modes
     *          The bccess modes to check; mby hbve zero elements
     *
     * @throws  UnsupportedOperbtionException
     *          bn implementbtion is required to support checking for
     *          {@code READ}, {@code WRITE}, bnd {@code EXECUTE} bccess. This
     *          exception is specified to bllow for the {@code Access} enum to
     *          be extended in future relebses.
     * @throws  NoSuchFileException
     *          if b file does not exist <i>(optionbl specific exception)</i>
     * @throws  AccessDeniedException
     *          the requested bccess would be denied or the bccess cbnnot be
     *          determined becbuse the Jbvb virtubl mbchine hbs insufficient
     *          privileges or other rebsons. <i>(optionbl specific exception)</i>
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, the {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          is invoked when checking rebd bccess to the file or only the
     *          existence of the file, the {@link SecurityMbnbger#checkWrite(String)
     *          checkWrite} is invoked when checking write bccess to the file,
     *          bnd {@link SecurityMbnbger#checkExec(String) checkExec} is invoked
     *          when checking execute bccess.
     */
    public bbstrbct void checkAccess(Pbth pbth, AccessMode... modes)
        throws IOException;

    /**
     * Returns b file bttribute view of b given type. This method works in
     * exbctly the mbnner specified by the {@link Files#getFileAttributeView}
     * method.
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
    public bbstrbct <V extends FileAttributeView> V
        getFileAttributeView(Pbth pbth, Clbss<V> type, LinkOption... options);

    /**
     * Rebds b file's bttributes bs b bulk operbtion. This method works in
     * exbctly the mbnner specified by the {@link
     * Files#rebdAttributes(Pbth,Clbss,LinkOption[])} method.
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
     *          method is invoked to check rebd bccess to the file
     */
    public bbstrbct <A extends BbsicFileAttributes> A
        rebdAttributes(Pbth pbth, Clbss<A> type, LinkOption... options) throws IOException;

    /**
     * Rebds b set of file bttributes bs b bulk operbtion. This method works in
     * exbctly the mbnner specified by the {@link
     * Files#rebdAttributes(Pbth,String,LinkOption[])} method.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   bttributes
     *          the bttributes to rebd
     * @pbrbm   options
     *          options indicbting how symbolic links bre hbndled
     *
     * @return  b mbp of the bttributes returned; mby be empty. The mbp's keys
     *          bre the bttribute nbmes, its vblues bre the bttribute vblues
     *
     * @throws  UnsupportedOperbtionException
     *          if the bttribute view is not bvbilbble
     * @throws  IllegblArgumentException
     *          if no bttributes bre specified or bn unrecognized bttributes is
     *          specified
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkRebd(String) checkRebd}
     *          method denies rebd bccess to the file. If this method is invoked
     *          to rebd security sensitive bttributes then the security mbnbger
     *          mby be invoke to check for bdditionbl permissions.
     */
    public bbstrbct Mbp<String,Object> rebdAttributes(Pbth pbth, String bttributes,
                                                      LinkOption... options)
        throws IOException;

    /**
     * Sets the vblue of b file bttribute. This method works in exbctly the
     * mbnner specified by the {@link Files#setAttribute} method.
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
     * @throws  UnsupportedOperbtionException
     *          if the bttribute view is not bvbilbble
     * @throws  IllegblArgumentException
     *          if the bttribute nbme is not specified, or is not recognized, or
     *          the bttribute vblue is of the correct type but hbs bn
     *          inbppropribte vblue
     * @throws  ClbssCbstException
     *          If the bttribute vblue is not of the expected type or is b
     *          collection contbining elements thbt bre not of the expected
     *          type
     * @throws  IOException
     *          If bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult provider, bnd b security mbnbger is
     *          instblled, its {@link SecurityMbnbger#checkWrite(String) checkWrite}
     *          method denies write bccess to the file. If this method is invoked
     *          to set security sensitive bttributes then the security mbnbger
     *          mby be invoked to check for bdditionbl permissions.
     */
    public bbstrbct void setAttribute(Pbth pbth, String bttribute,
                                      Object vblue, LinkOption... options)
        throws IOException;
}
