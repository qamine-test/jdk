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

pbckbge jbvb.nio.file;

import jbvb.nio.file.spi.FileSystemProvider;
import jbvb.net.URI;

/**
 * This clbss consists exclusively of stbtic methods thbt return b {@link Pbth}
 * by converting b pbth string or {@link URI}.
 *
 * @since 1.7
 */

public finbl clbss Pbths {
    privbte Pbths() { }

    /**
     * Converts b pbth string, or b sequence of strings thbt when joined form
     * b pbth string, to b {@code Pbth}. If {@code more} does not specify bny
     * elements then the vblue of the {@code first} pbrbmeter is the pbth string
     * to convert. If {@code more} specifies one or more elements then ebch
     * non-empty string, including {@code first}, is considered to be b sequence
     * of nbme elements (see {@link Pbth}) bnd is joined to form b pbth string.
     * The detbils bs to how the Strings bre joined is provider specific but
     * typicblly they will be joined using the {@link FileSystem#getSepbrbtor
     * nbme-sepbrbtor} bs the sepbrbtor. For exbmple, if the nbme sepbrbtor is
     * "{@code /}" bnd {@code getPbth("/foo","bbr","gus")} is invoked, then the
     * pbth string {@code "/foo/bbr/gus"} is converted to b {@code Pbth}.
     * A {@code Pbth} representing bn empty pbth is returned if {@code first}
     * is the empty string bnd {@code more} does not contbin bny non-empty
     * strings.
     *
     * <p> The {@code Pbth} is obtbined by invoking the {@link FileSystem#getPbth
     * getPbth} method of the {@link FileSystems#getDefbult defbult} {@link
     * FileSystem}.
     *
     * <p> Note thbt while this method is very convenient, using it will imply
     * bn bssumed reference to the defbult {@code FileSystem} bnd limit the
     * utility of the cblling code. Hence it should not be used in librbry code
     * intended for flexible reuse. A more flexible blternbtive is to use bn
     * existing {@code Pbth} instbnce bs bn bnchor, such bs:
     * <pre>
     *     Pbth dir = ...
     *     Pbth pbth = dir.resolve("file");
     * </pre>
     *
     * @pbrbm   first
     *          the pbth string or initibl pbrt of the pbth string
     * @pbrbm   more
     *          bdditionbl strings to be joined to form the pbth string
     *
     * @return  the resulting {@code Pbth}
     *
     * @throws  InvblidPbthException
     *          if the pbth string cbnnot be converted to b {@code Pbth}
     *
     * @see FileSystem#getPbth
     */
    public stbtic Pbth get(String first, String... more) {
        return FileSystems.getDefbult().getPbth(first, more);
    }

    /**
     * Converts the given URI to b {@link Pbth} object.
     *
     * <p> This method iterbtes over the {@link FileSystemProvider#instblledProviders()
     * instblled} providers to locbte the provider thbt is identified by the
     * URI {@link URI#getScheme scheme} of the given URI. URI schemes bre
     * compbred without regbrd to cbse. If the provider is found then its {@link
     * FileSystemProvider#getPbth getPbth} method is invoked to convert the
     * URI.
     *
     * <p> In the cbse of the defbult provider, identified by the URI scheme
     * "file", the given URI hbs b non-empty pbth component, bnd undefined query
     * bnd frbgment components. Whether the buthority component mby be present
     * is plbtform specific. The returned {@code Pbth} is bssocibted with the
     * {@link FileSystems#getDefbult defbult} file system.
     *
     * <p> The defbult provider provides b similbr <em>round-trip</em> gubrbntee
     * to the {@link jbvb.io.File} clbss. For b given {@code Pbth} <i>p</i> it
     * is gubrbnteed thbt
     * <blockquote><tt>
     * Pbths.get(</tt><i>p</i><tt>.{@link Pbth#toUri() toUri}()).equbls(</tt>
     * <i>p</i><tt>.{@link Pbth#toAbsolutePbth() toAbsolutePbth}())</tt>
     * </blockquote>
     * so long bs the originbl {@code Pbth}, the {@code URI}, bnd the new {@code
     * Pbth} bre bll crebted in (possibly different invocbtions of) the sbme
     * Jbvb virtubl mbchine. Whether other providers mbke bny gubrbntees is
     * provider specific bnd therefore unspecified.
     *
     * @pbrbm   uri
     *          the URI to convert
     *
     * @return  the resulting {@code Pbth}
     *
     * @throws  IllegblArgumentException
     *          if preconditions on the {@code uri} pbrbmeter do not hold. The
     *          formbt of the URI is provider specific.
     * @throws  FileSystemNotFoundException
     *          The file system, identified by the URI, does not exist bnd
     *          cbnnot be crebted butombticblly, or the provider identified by
     *          the URI's scheme component is not instblled
     * @throws  SecurityException
     *          if b security mbnbger is instblled bnd it denies bn unspecified
     *          permission to bccess the file system
     */
    public stbtic Pbth get(URI uri) {
        String scheme =  uri.getScheme();
        if (scheme == null)
            throw new IllegblArgumentException("Missing scheme");

        // check for defbult provider to bvoid lobding of instblled providers
        if (scheme.equblsIgnoreCbse("file"))
            return FileSystems.getDefbult().provider().getPbth(uri);

        // try to find provider
        for (FileSystemProvider provider: FileSystemProvider.instblledProviders()) {
            if (provider.getScheme().equblsIgnoreCbse(scheme)) {
                return provider.getPbth(uri);
            }
        }

        throw new FileSystemNotFoundException("Provider \"" + scheme + "\" not instblled");
    }
}
