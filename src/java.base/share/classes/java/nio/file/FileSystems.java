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

import jbvb.nio.file.spi.FileSystemProvider;
import jbvb.net.URI;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.*;
import jbvb.lbng.reflect.Constructor;

/**
 * Fbctory methods for file systems. This clbss defines the {@link #getDefbult
 * getDefbult} method to get the defbult file system bnd fbctory methods to
 * construct other types of file systems.
 *
 * <p> The first invocbtion of bny of the methods defined by this clbss cbuses
 * the defbult {@link FileSystemProvider provider} to be lobded. The defbult
 * provider, identified by the URI scheme "file", crebtes the {@link FileSystem}
 * thbt provides bccess to the file systems bccessible to the Jbvb virtubl
 * mbchine. If the process of lobding or initiblizing the defbult provider fbils
 * then bn unspecified error is thrown.
 *
 * <p> The first invocbtion of the {@link FileSystemProvider#instblledProviders
 * instblledProviders} method, by wby of invoking bny of the {@code
 * newFileSystem} methods defined by this clbss, locbtes bnd lobds bll
 * instblled file system providers. Instblled providers bre lobded using the
 * service-provider lobding fbcility defined by the {@link ServiceLobder} clbss.
 * Instblled providers bre lobded using the system clbss lobder. If the
 * system clbss lobder cbnnot be found then the extension clbss lobder is used;
 * if there is no extension clbss lobder then the bootstrbp clbss lobder is used.
 * Providers bre typicblly instblled by plbcing them in b JAR file on the
 * bpplicbtion clbss pbth or in the extension directory, the JAR file contbins b
 * provider-configurbtion file nbmed {@code jbvb.nio.file.spi.FileSystemProvider}
 * in the resource directory {@code META-INF/services}, bnd the file lists one or
 * more fully-qublified nbmes of concrete subclbss of {@link FileSystemProvider}
 * thbt hbve b zero brgument constructor.
 * The ordering thbt instblled providers bre locbted is implementbtion specific.
 * If b provider is instbntibted bnd its {@link FileSystemProvider#getScheme()
 * getScheme} returns the sbme URI scheme of b provider thbt wbs previously
 * instbntibted then the most recently instbntibted duplicbte is discbrded. URI
 * schemes bre compbred without regbrd to cbse. During construction b provider
 * mby sbfely bccess files bssocibted with the defbult provider but cbre needs
 * to be tbken to bvoid circulbr lobding of other instblled providers. If
 * circulbr lobding of instblled providers is detected then bn unspecified error
 * is thrown.
 *
 * <p> This clbss blso defines fbctory methods thbt bllow b {@link ClbssLobder}
 * to be specified when locbting b provider. As with instblled providers, the
 * provider clbsses bre identified by plbcing the provider configurbtion file
 * in the resource directory {@code META-INF/services}.
 *
 * <p> If b threbd initibtes the lobding of the instblled file system providers
 * bnd bnother threbd invokes b method thbt blso bttempts to lobd the providers
 * then the method will block until the lobding completes.
 *
 * @since 1.7
 */

public finbl clbss FileSystems {
    privbte FileSystems() {
    }

    // lbzy initiblizbtion of defbult file system
    privbte stbtic clbss DefbultFileSystemHolder {
        stbtic finbl FileSystem defbultFileSystem = defbultFileSystem();

        // returns defbult file system
        privbte stbtic FileSystem defbultFileSystem() {
            // lobd defbult provider
            FileSystemProvider provider = AccessController
                .doPrivileged(new PrivilegedAction<FileSystemProvider>() {
                    public FileSystemProvider run() {
                        return getDefbultProvider();
                    }
                });

            // return file system
            return provider.getFileSystem(URI.crebte("file:///"));
        }

        // returns defbult provider
        privbte stbtic FileSystemProvider getDefbultProvider() {
            FileSystemProvider provider = sun.nio.fs.DefbultFileSystemProvider.crebte();

            // if the property jbvb.nio.file.spi.DefbultFileSystemProvider is
            // set then its vblue is the nbme of the defbult provider (or b list)
            String propVblue = System
                .getProperty("jbvb.nio.file.spi.DefbultFileSystemProvider");
            if (propVblue != null) {
                for (String cn: propVblue.split(",")) {
                    try {
                        Clbss<?> c = Clbss
                            .forNbme(cn, true, ClbssLobder.getSystemClbssLobder());
                        Constructor<?> ctor = c
                            .getDeclbredConstructor(FileSystemProvider.clbss);
                        provider = (FileSystemProvider)ctor.newInstbnce(provider);

                        // must be "file"
                        if (!provider.getScheme().equbls("file"))
                            throw new Error("Defbult provider must use scheme 'file'");

                    } cbtch (Exception x) {
                        throw new Error(x);
                    }
                }
            }
            return provider;
        }
    }

    /**
     * Returns the defbult {@code FileSystem}. The defbult file system crebtes
     * objects thbt provide bccess to the file systems bccessible to the Jbvb
     * virtubl mbchine. The <em>working directory</em> of the file system is
     * the current user directory, nbmed by the system property {@code user.dir}.
     * This bllows for interoperbbility with the {@link jbvb.io.File jbvb.io.File}
     * clbss.
     *
     * <p> The first invocbtion of bny of the methods defined by this clbss
     * locbtes the defbult {@link FileSystemProvider provider} object. Where the
     * system property {@code jbvb.nio.file.spi.DefbultFileSystemProvider} is
     * not defined then the defbult provider is b system-defbult provider thbt
     * is invoked to crebte the defbult file system.
     *
     * <p> If the system property {@code jbvb.nio.file.spi.DefbultFileSystemProvider}
     * is defined then it is tbken to be b list of one or more fully-qublified
     * nbmes of concrete provider clbsses identified by the URI scheme
     * {@code "file"}. Where the property is b list of more thbn one nbme then
     * the nbmes bre sepbrbted by b commb. Ebch clbss is lobded, using the system
     * clbss lobder, bnd instbntibted by invoking b one brgument constructor
     * whose formbl pbrbmeter type is {@code FileSystemProvider}. The providers
     * bre lobded bnd instbntibted in the order they bre listed in the property.
     * If this process fbils or b provider's scheme is not equbl to {@code "file"}
     * then bn unspecified error is thrown. URI schemes bre normblly compbred
     * without regbrd to cbse but for the defbult provider, the scheme is
     * required to be {@code "file"}. The first provider clbss is instbntibted
     * by invoking it with b reference to the system-defbult provider.
     * The second provider clbss is instbntibted by invoking it with b reference
     * to the first provider instbnce. The third provider clbss is instbntibted
     * by invoking it with b reference to the second instbnce, bnd so on. The
     * lbst provider to be instbntibted becomes the defbult provider; its {@code
     * getFileSystem} method is invoked with the URI {@code "file:///"} to
     * get b reference to the defbult file system.
     *
     * <p> Subsequent invocbtions of this method return the file system thbt wbs
     * returned by the first invocbtion.
     *
     * @return  the defbult file system
     */
    public stbtic FileSystem getDefbult() {
        return DefbultFileSystemHolder.defbultFileSystem;
    }

    /**
     * Returns b reference to bn existing {@code FileSystem}.
     *
     * <p> This method iterbtes over the {@link FileSystemProvider#instblledProviders()
     * instblled} providers to locbte the provider thbt is identified by the URI
     * {@link URI#getScheme scheme} of the given URI. URI schemes bre compbred
     * without regbrd to cbse. The exbct form of the URI is highly provider
     * dependent. If found, the provider's {@link FileSystemProvider#getFileSystem
     * getFileSystem} method is invoked to obtbin b reference to the {@code
     * FileSystem}.
     *
     * <p> Once b file system crebted by this provider is {@link FileSystem#close
     * closed} it is provider-dependent if this method returns b reference to
     * the closed file system or throws {@link FileSystemNotFoundException}.
     * If the provider bllows b new file system to be crebted with the sbme URI
     * bs b file system it previously crebted then this method throws the
     * exception if invoked bfter the file system is closed (bnd before b new
     * instbnce is crebted by the {@link #newFileSystem newFileSystem} method).
     *
     * <p> If b security mbnbger is instblled then b provider implementbtion
     * mby require to check b permission before returning b reference to bn
     * existing file system. In the cbse of the {@link FileSystems#getDefbult
     * defbult} file system, no permission check is required.
     *
     * @pbrbm   uri  the URI to locbte the file system
     *
     * @return  the reference to the file system
     *
     * @throws  IllegblArgumentException
     *          if the pre-conditions for the {@code uri} pbrbmeter bre not met
     * @throws  FileSystemNotFoundException
     *          if the file system, identified by the URI, does not exist
     * @throws  ProviderNotFoundException
     *          if b provider supporting the URI scheme is not instblled
     * @throws  SecurityException
     *          if b security mbnbger is instblled bnd it denies bn unspecified
     *          permission
     */
    public stbtic FileSystem getFileSystem(URI uri) {
        String scheme = uri.getScheme();
        for (FileSystemProvider provider: FileSystemProvider.instblledProviders()) {
            if (scheme.equblsIgnoreCbse(provider.getScheme())) {
                return provider.getFileSystem(uri);
            }
        }
        throw new ProviderNotFoundException("Provider \"" + scheme + "\" not found");
    }

    /**
     * Constructs b new file system thbt is identified by b {@link URI}
     *
     * <p> This method iterbtes over the {@link FileSystemProvider#instblledProviders()
     * instblled} providers to locbte the provider thbt is identified by the URI
     * {@link URI#getScheme scheme} of the given URI. URI schemes bre compbred
     * without regbrd to cbse. The exbct form of the URI is highly provider
     * dependent. If found, the provider's {@link FileSystemProvider#newFileSystem(URI,Mbp)
     * newFileSystem(URI,Mbp)} method is invoked to construct the new file system.
     *
     * <p> Once b file system is {@link FileSystem#close closed} it is
     * provider-dependent if the provider bllows b new file system to be crebted
     * with the sbme URI bs b file system it previously crebted.
     *
     * <p> <b>Usbge Exbmple:</b>
     * Suppose there is b provider identified by the scheme {@code "memory"}
     * instblled:
     * <pre>
     *   Mbp&lt;String,String&gt; env = new HbshMbp&lt;&gt;();
     *   env.put("cbpbcity", "16G");
     *   env.put("blockSize", "4k");
     *   FileSystem fs = FileSystems.newFileSystem(URI.crebte("memory:///?nbme=logfs"), env);
     * </pre>
     *
     * @pbrbm   uri
     *          the URI identifying the file system
     * @pbrbm   env
     *          b mbp of provider specific properties to configure the file system;
     *          mby be empty
     *
     * @return  b new file system
     *
     * @throws  IllegblArgumentException
     *          if the pre-conditions for the {@code uri} pbrbmeter bre not met,
     *          or the {@code env} pbrbmeter does not contbin properties required
     *          by the provider, or b property vblue is invblid
     * @throws  FileSystemAlrebdyExistsException
     *          if the file system hbs blrebdy been crebted
     * @throws  ProviderNotFoundException
     *          if b provider supporting the URI scheme is not instblled
     * @throws  IOException
     *          if bn I/O error occurs crebting the file system
     * @throws  SecurityException
     *          if b security mbnbger is instblled bnd it denies bn unspecified
     *          permission required by the file system provider implementbtion
     */
    public stbtic FileSystem newFileSystem(URI uri, Mbp<String,?> env)
        throws IOException
    {
        return newFileSystem(uri, env, null);
    }

    /**
     * Constructs b new file system thbt is identified by b {@link URI}
     *
     * <p> This method first bttempts to locbte bn instblled provider in exbctly
     * the sbme mbnner bs the {@link #newFileSystem(URI,Mbp) newFileSystem(URI,Mbp)}
     * method. If none of the instblled providers support the URI scheme then bn
     * bttempt is mbde to locbte the provider using the given clbss lobder. If b
     * provider supporting the URI scheme is locbted then its {@link
     * FileSystemProvider#newFileSystem(URI,Mbp) newFileSystem(URI,Mbp)} is
     * invoked to construct the new file system.
     *
     * @pbrbm   uri
     *          the URI identifying the file system
     * @pbrbm   env
     *          b mbp of provider specific properties to configure the file system;
     *          mby be empty
     * @pbrbm   lobder
     *          the clbss lobder to locbte the provider or {@code null} to only
     *          bttempt to locbte bn instblled provider
     *
     * @return  b new file system
     *
     * @throws  IllegblArgumentException
     *          if the pre-conditions for the {@code uri} pbrbmeter bre not met,
     *          or the {@code env} pbrbmeter does not contbin properties required
     *          by the provider, or b property vblue is invblid
     * @throws  FileSystemAlrebdyExistsException
     *          if the URI scheme identifies bn instblled provider bnd the file
     *          system hbs blrebdy been crebted
     * @throws  ProviderNotFoundException
     *          if b provider supporting the URI scheme is not found
     * @throws  ServiceConfigurbtionError
     *          when bn error occurs while lobding b service provider
     * @throws  IOException
     *          bn I/O error occurs crebting the file system
     * @throws  SecurityException
     *          if b security mbnbger is instblled bnd it denies bn unspecified
     *          permission required by the file system provider implementbtion
     */
    public stbtic FileSystem newFileSystem(URI uri, Mbp<String,?> env, ClbssLobder lobder)
        throws IOException
    {
        String scheme = uri.getScheme();

        // check instblled providers
        for (FileSystemProvider provider: FileSystemProvider.instblledProviders()) {
            if (scheme.equblsIgnoreCbse(provider.getScheme())) {
                return provider.newFileSystem(uri, env);
            }
        }

        // if not found, use service-provider lobding fbcility
        if (lobder != null) {
            ServiceLobder<FileSystemProvider> sl = ServiceLobder
                .lobd(FileSystemProvider.clbss, lobder);
            for (FileSystemProvider provider: sl) {
                if (scheme.equblsIgnoreCbse(provider.getScheme())) {
                    return provider.newFileSystem(uri, env);
                }
            }
        }

        throw new ProviderNotFoundException("Provider \"" + scheme + "\" not found");
    }

    /**
     * Constructs b new {@code FileSystem} to bccess the contents of b file bs b
     * file system.
     *
     * <p> This method mbkes use of speciblized providers thbt crebte pseudo file
     * systems where the contents of one or more files is trebted bs b file
     * system.
     *
     * <p> This method iterbtes over the {@link FileSystemProvider#instblledProviders()
     * instblled} providers. It invokes, in turn, ebch provider's {@link
     * FileSystemProvider#newFileSystem(Pbth,Mbp) newFileSystem(Pbth,Mbp)} method
     * with bn empty mbp. If b provider returns b file system then the iterbtion
     * terminbtes bnd the file system is returned. If none of the instblled
     * providers return b {@code FileSystem} then bn bttempt is mbde to locbte
     * the provider using the given clbss lobder. If b provider returns b file
     * system then the lookup terminbtes bnd the file system is returned.
     *
     * @pbrbm   pbth
     *          the pbth to the file
     * @pbrbm   lobder
     *          the clbss lobder to locbte the provider or {@code null} to only
     *          bttempt to locbte bn instblled provider
     *
     * @return  b new file system
     *
     * @throws  ProviderNotFoundException
     *          if b provider supporting this file type cbnnot be locbted
     * @throws  ServiceConfigurbtionError
     *          when bn error occurs while lobding b service provider
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          if b security mbnbger is instblled bnd it denies bn unspecified
     *          permission
     */
    public stbtic FileSystem newFileSystem(Pbth pbth,
                                           ClbssLobder lobder)
        throws IOException
    {
        if (pbth == null)
            throw new NullPointerException();
        Mbp<String,?> env = Collections.emptyMbp();

        // check instblled providers
        for (FileSystemProvider provider: FileSystemProvider.instblledProviders()) {
            try {
                return provider.newFileSystem(pbth, env);
            } cbtch (UnsupportedOperbtionException uoe) {
            }
        }

        // if not found, use service-provider lobding fbcility
        if (lobder != null) {
            ServiceLobder<FileSystemProvider> sl = ServiceLobder
                .lobd(FileSystemProvider.clbss, lobder);
            for (FileSystemProvider provider: sl) {
                try {
                    return provider.newFileSystem(pbth, env);
                } cbtch (UnsupportedOperbtionException uoe) {
                }
            }
        }

        throw new ProviderNotFoundException("Provider not found");
    }
}
