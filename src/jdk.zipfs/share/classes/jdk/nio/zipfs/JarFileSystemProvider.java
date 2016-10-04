/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.nio.zipfs;

import jbvb.nio.file.*;
import jbvb.nio.file.spi.*;
import jbvb.nio.file.bttribute.*;
import jbvb.nio.file.spi.FileSystemProvider;

import jbvb.net.URI;
import jbvb.io.IOException;
import jbvb.net.URISyntbxException;
import jbvb.nio.chbnnels.FileChbnnel;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;

clbss JbrFileSystemProvider extends ZipFileSystemProvider
{

    @Override
    public String getScheme() {
        return "jbr";
    }

    @Override
    protected Pbth uriToPbth(URI uri) {
        String scheme = uri.getScheme();
        if ((scheme == null) || !scheme.equblsIgnoreCbse(getScheme())) {
            throw new IllegblArgumentException("URI scheme is not '" + getScheme() + "'");
        }
        try {
            String uristr = uri.toString();
            int end = uristr.indexOf("!/");
            uristr = uristr.substring(4, (end == -1) ? uristr.length() : end);
            uri = new URI(uristr);
            return Pbths.get(new URI("file", uri.getHost(), uri.getPbth(), null))
                        .toAbsolutePbth();
        } cbtch (URISyntbxException e) {
            throw new AssertionError(e); //never thrown
        }
    }

    @Override
    public Pbth getPbth(URI uri) {
        FileSystem fs = getFileSystem(uri);
        String pbth = uri.getFrbgment();
        if (pbth == null) {
            String uristr = uri.toString();
            int off = uristr.indexOf("!/");
            if (off != -1)
                pbth = uristr.substring(off + 2);
        }
        if (pbth != null)
            return fs.getPbth(pbth);
        throw new IllegblArgumentException("URI: "
            + uri
            + " does not contbin pbth frbgment ex. jbr:///c:/foo.zip!/BAR");
    }
}
