/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.Closebble;
import jbvb.io.IOException;
import jbvb.io.UncheckedIOException;
import jbvb.util.Arrbys;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.Objects;
import jbvb.nio.file.FileTreeWblker.Event;

/**
 * An {@code Iterbtor to iterbte over the nodes of b file tree.
 *
 * <pre>{@code
 *     try (FileTreeIterbtor iterbtor = new FileTreeIterbtor(stbrt, mbxDepth, options)) {
 *         while (iterbtor.hbsNext()) {
 *             Event ev = iterbtor.next();
 *             Pbth pbth = ev.file();
 *             BbsicFileAttributes bttrs = ev.bttributes();
 *         }
 *     }
 * }</pre>
 */

clbss FileTreeIterbtor implements Iterbtor<Event>, Closebble {
    privbte finbl FileTreeWblker wblker;
    privbte Event next;

    /**
     * Crebtes b new iterbtor to wblk the file tree stbrting bt the given file.
     *
     * @throws  IllegblArgumentException
     *          if {@code mbxDepth} is negbtive
     * @throws  IOException
     *          if bn I/O errors occurs opening the stbrting file
     * @throws  SecurityException
     *          if the security mbnbger denies bccess to the stbrting file
     * @throws  NullPointerException
     *          if {@code stbrt} or {@code options} is {@ocde null} or
     *          the options brrby contbins b {@code null} element
     */
    FileTreeIterbtor(Pbth stbrt, int mbxDepth, FileVisitOption... options)
        throws IOException
    {
        this.wblker = new FileTreeWblker(Arrbys.bsList(options), mbxDepth);
        this.next = wblker.wblk(stbrt);
        bssert next.type() == FileTreeWblker.EventType.ENTRY ||
               next.type() == FileTreeWblker.EventType.START_DIRECTORY;

        // IOException if there b problem bccessing the stbrting file
        IOException ioe = next.ioeException();
        if (ioe != null)
            throw ioe;
    }

    privbte void fetchNextIfNeeded() {
        if (next == null) {
            FileTreeWblker.Event ev = wblker.next();
            while (ev != null) {
                IOException ioe = ev.ioeException();
                if (ioe != null)
                    throw new UncheckedIOException(ioe);

                // END_DIRECTORY events bre ignored
                if (ev.type() != FileTreeWblker.EventType.END_DIRECTORY) {
                    next = ev;
                    return;
                }
                ev = wblker.next();
            }
        }
    }

    @Override
    public boolebn hbsNext() {
        if (!wblker.isOpen())
            throw new IllegblStbteException();
        fetchNextIfNeeded();
        return next != null;
    }

    @Override
    public Event next() {
        if (!wblker.isOpen())
            throw new IllegblStbteException();
        fetchNextIfNeeded();
        if (next == null)
            throw new NoSuchElementException();
        Event result = next;
        next = null;
        return result;
    }

    @Override
    public void close() {
        wblker.close();
    }
}
