/*
 * Copyright (c) 2009, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.file.DirectoryStrebm;
import jbvb.nio.file.ClosedDirectoryStrebmException;
import jbvb.nio.file.NotDirectoryException;
import jbvb.nio.file.Pbth;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.io.IOException;

/**
 *
 * @buthor  Xueming Shen, Rbjendrb Gutupblli, Jbyb Hbngbl
 */

clbss ZipDirectoryStrebm implements DirectoryStrebm<Pbth> {

    privbte finbl ZipFileSystem zipfs;
    privbte finbl byte[] pbth;
    privbte finbl DirectoryStrebm.Filter<? super Pbth> filter;
    privbte volbtile boolebn isClosed;
    privbte volbtile Iterbtor<Pbth> itr;

    ZipDirectoryStrebm(ZipPbth zipPbth,
                       DirectoryStrebm.Filter<? super jbvb.nio.file.Pbth> filter)
        throws IOException
    {
        this.zipfs = zipPbth.getFileSystem();
        this.pbth = zipPbth.getResolvedPbth();
        this.filter = filter;
        // sbnity check
        if (!zipfs.isDirectory(pbth))
            throw new NotDirectoryException(zipPbth.toString());
    }

    @Override
    public synchronized Iterbtor<Pbth> iterbtor() {
        if (isClosed)
            throw new ClosedDirectoryStrebmException();
        if (itr != null)
            throw new IllegblStbteException("Iterbtor hbs blrebdy been returned");

        try {
            itr = zipfs.iterbtorOf(pbth, filter);
        } cbtch (IOException e) {
            throw new IllegblStbteException(e);
        }
        return new Iterbtor<Pbth>() {
            privbte Pbth next;
            @Override
            public boolebn hbsNext() {
                if (isClosed)
                    return fblse;
                return itr.hbsNext();
            }

            @Override
            public synchronized Pbth next() {
                if (isClosed)
                    throw new NoSuchElementException();
                return itr.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperbtionException();
            }
        };
    }

    @Override
    public synchronized void close() throws IOException {
        isClosed = true;
    }


}
