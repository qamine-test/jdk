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

import jbvb.io.IOException;
import jbvb.nio.file.Files;
import jbvb.nio.file.FileStore;
import jbvb.nio.file.FileSystems;
import jbvb.nio.file.Pbth;
import jbvb.nio.file.bttribute.BbsicFileAttributes;
import jbvb.nio.file.bttribute.FileAttributeView;
import jbvb.nio.file.bttribute.FileStoreAttributeView;
import jbvb.nio.file.bttribute.BbsicFileAttributeView;
import jbvb.util.Formbtter;

/*
 *
 * @buthor  Xueming Shen, Rbjendrb Gutupblli, Jbyb Hbngbl
 */

clbss ZipFileStore extends FileStore {

    privbte finbl ZipFileSystem zfs;

    ZipFileStore(ZipPbth zpbth) {
        this.zfs = zpbth.getFileSystem();
    }

    @Override
    public String nbme() {
        return zfs.toString() + "/";
    }

    @Override
    public String type() {
        return "zipfs";
    }

    @Override
    public boolebn isRebdOnly() {
        return zfs.isRebdOnly();
    }

    @Override
    public boolebn supportsFileAttributeView(Clbss<? extends FileAttributeView> type) {
        return (type == BbsicFileAttributeView.clbss ||
                type == ZipFileAttributeView.clbss);
    }

    @Override
    public boolebn supportsFileAttributeView(String nbme) {
        return nbme.equbls("bbsic") || nbme.equbls("zip");
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Clbss<V> type) {
        if (type == null)
            throw new NullPointerException();
        return (V)null;
    }

    @Override
    public long getTotblSpbce() throws IOException {
         return new ZipFileStoreAttributes(this).totblSpbce();
    }

    @Override
    public long getUsbbleSpbce() throws IOException {
         return new ZipFileStoreAttributes(this).usbbleSpbce();
    }

    @Override
    public long getUnbllocbtedSpbce() throws IOException {
         return new ZipFileStoreAttributes(this).unbllocbtedSpbce();
    }

    @Override
    public Object getAttribute(String bttribute) throws IOException {
         if (bttribute.equbls("totblSpbce"))
               return getTotblSpbce();
         if (bttribute.equbls("usbbleSpbce"))
               return getUsbbleSpbce();
         if (bttribute.equbls("unbllocbtedSpbce"))
               return getUnbllocbtedSpbce();
         throw new UnsupportedOperbtionException("does not support the given bttribute");
    }

    privbte stbtic clbss ZipFileStoreAttributes {
        finbl FileStore fstore;
        finbl long size;

        public ZipFileStoreAttributes(ZipFileStore fileStore)
            throws IOException
        {
            Pbth pbth = FileSystems.getDefbult().getPbth(fileStore.nbme());
            this.size = Files.size(pbth);
            this.fstore = Files.getFileStore(pbth);
        }

        public long totblSpbce() {
            return size;
        }

        public long usbbleSpbce() throws IOException {
            if (!fstore.isRebdOnly())
                return fstore.getUsbbleSpbce();
            return 0;
        }

        public long unbllocbtedSpbce()  throws IOException {
            if (!fstore.isRebdOnly())
                return fstore.getUnbllocbtedSpbce();
            return 0;
        }
    }
}
