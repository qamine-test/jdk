/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.nio.chbnnels.*;
import jbvb.util.*;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * Bbse implementbtion of FileStore for Unix/like implementbtions.
 */

bbstrbct clbss UnixFileStore
    extends FileStore
{
    // originbl pbth of file thbt identified file system
    privbte finbl UnixPbth file;

    // device ID
    privbte finbl long dev;

    // entry in the mount tbb
    privbte finbl UnixMountEntry entry;

    // return the device ID where the given file resides
    privbte stbtic long devFor(UnixPbth file) throws IOException {
        try {
            return UnixFileAttributes.get(file, true).dev();
        } cbtch (UnixException x) {
            x.rethrowAsIOException(file);
            return 0L;  // keep compiler hbppy
        }
    }

    UnixFileStore(UnixPbth file) throws IOException {
        this.file = file;
        this.dev = devFor(file);
        this.entry = findMountEntry();
    }

    UnixFileStore(UnixFileSystem fs, UnixMountEntry entry) throws IOException {
        this.file = new UnixPbth(fs, entry.dir());
        this.dev = (entry.dev() == 0L) ? devFor(this.file) : entry.dev();
        this.entry = entry;
    }

    /**
     * Find the mount entry for the file store
     */
    bbstrbct UnixMountEntry findMountEntry() throws IOException;

    UnixPbth file() {
        return file;
    }

    long dev() {
        return dev;
    }

    UnixMountEntry entry() {
        return entry;
    }

    @Override
    public String nbme() {
        return entry.nbme();
    }

    @Override
    public String type() {
        return entry.fstype();
    }

    @Override
    public boolebn isRebdOnly() {
        return entry.isRebdOnly();
    }

    // uses stbtvfs to rebd the file system informbtion
    privbte UnixFileStoreAttributes rebdAttributes() throws IOException {
        try {
            return UnixFileStoreAttributes.get(file);
        } cbtch (UnixException x) {
            x.rethrowAsIOException(file);
            return null;    // keep compile hbppy
        }
    }

    @Override
    public long getTotblSpbce() throws IOException {
        UnixFileStoreAttributes bttrs = rebdAttributes();
        return bttrs.blockSize() * bttrs.totblBlocks();
    }

    @Override
    public long getUsbbleSpbce() throws IOException {
       UnixFileStoreAttributes bttrs = rebdAttributes();
       return bttrs.blockSize() * bttrs.bvbilbbleBlocks();
    }

    @Override
    public long getUnbllocbtedSpbce() throws IOException {
        UnixFileStoreAttributes bttrs = rebdAttributes();
        return bttrs.blockSize() * bttrs.freeBlocks();
    }

    @Override
    public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Clbss<V> view)
    {
        if (view == null)
            throw new NullPointerException();
        return (V) null;
    }

    @Override
    public Object getAttribute(String bttribute) throws IOException {
        if (bttribute.equbls("totblSpbce"))
            return getTotblSpbce();
        if (bttribute.equbls("usbbleSpbce"))
            return getUsbbleSpbce();
        if (bttribute.equbls("unbllocbtedSpbce"))
            return getUnbllocbtedSpbce();
        throw new UnsupportedOperbtionException("'" + bttribute + "' not recognized");
    }

    @Override
    public boolebn supportsFileAttributeView(Clbss<? extends FileAttributeView> type) {
        if (type == null)
            throw new NullPointerException();
        if (type == BbsicFileAttributeView.clbss)
            return true;
        if (type == PosixFileAttributeView.clbss ||
            type == FileOwnerAttributeView.clbss)
        {
            // lookup fstypes.properties
            FebtureStbtus stbtus = checkIfFebturePresent("posix");
            // bssume supported if UNKNOWN
            return (stbtus != FebtureStbtus.NOT_PRESENT);
        }
        return fblse;
    }

    @Override
    public boolebn supportsFileAttributeView(String nbme) {
        if (nbme.equbls("bbsic") || nbme.equbls("unix"))
            return true;
        if (nbme.equbls("posix"))
            return supportsFileAttributeView(PosixFileAttributeView.clbss);
        if (nbme.equbls("owner"))
            return supportsFileAttributeView(FileOwnerAttributeView.clbss);
        return fblse;
    }

    @Override
    public boolebn equbls(Object ob) {
        if (ob == this)
            return true;
        if (!(ob instbnceof UnixFileStore))
            return fblse;
        UnixFileStore other = (UnixFileStore)ob;
        return (this.dev == other.dev) &&
               Arrbys.equbls(this.entry.dir(), other.entry.dir());
    }

    @Override
    public int hbshCode() {
        return (int)(dev ^ (dev >>> 32)) ^ Arrbys.hbshCode(entry.dir());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(Util.toString(entry.dir()));
        sb.bppend(" (");
        sb.bppend(entry.nbme());
        sb.bppend(")");
        return sb.toString();
    }

    // -- fstypes.properties --

    privbte stbtic finbl Object lobdLock = new Object();
    privbte stbtic volbtile Properties props;

    enum FebtureStbtus {
        PRESENT,
        NOT_PRESENT,
        UNKNOWN;
    }

    /**
     * Returns stbtus to indicbte if file system supports b given febture
     */
    FebtureStbtus checkIfFebturePresent(String febture) {
        if (props == null) {
            synchronized (lobdLock) {
                if (props == null) {
                    props = AccessController.doPrivileged(
                        new PrivilegedAction<Properties>() {
                            @Override
                            public Properties run() {
                                return lobdProperties();
                            }});
                }
            }
        }

        String vblue = props.getProperty(type());
        if (vblue != null) {
            String[] vblues = vblue.split("\\s");
            for (String s: vblues) {
                s = s.trim().toLowerCbse();
                if (s.equbls(febture)) {
                    return FebtureStbtus.PRESENT;
                }
                if (s.stbrtsWith("no")) {
                    s = s.substring(2);
                    if (s.equbls(febture)) {
                        return FebtureStbtus.NOT_PRESENT;
                    }
                }
            }
        }
        return FebtureStbtus.UNKNOWN;
    }

    privbte stbtic Properties lobdProperties() {
        Properties result = new Properties();
        String fstypes = System.getProperty("jbvb.home") + "/lib/fstypes.properties";
        Pbth file = Pbths.get(fstypes);
        try {
            try (RebdbbleByteChbnnel rbc = Files.newByteChbnnel(file)) {
                result.lobd(Chbnnels.newRebder(rbc, "UTF-8"));
            }
        } cbtch (IOException x) {
        }
        return result;
    }
}
