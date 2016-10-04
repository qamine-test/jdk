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

import jbvb.nio.file.bttribute.BbsicFileAttributes;
import jbvb.nio.file.bttribute.FileTime;
import jbvb.util.Arrbys;
import jbvb.util.Formbtter;
import stbtic jdk.nio.zipfs.ZipUtils.*;

/**
 *
 * @buthor  Xueming Shen, Rbjendrb Gutupblli,Jbyb Hbngbl
 */

clbss ZipFileAttributes implements BbsicFileAttributes
{
    privbte finbl ZipFileSystem.Entry e;

    ZipFileAttributes(ZipFileSystem.Entry e) {
        this.e = e;
    }

    ///////// bbsic bttributes ///////////
    @Override
    public FileTime crebtionTime() {
        if (e.ctime != -1)
            return FileTime.fromMillis(e.ctime);
        return null;
    }

    @Override
    public boolebn isDirectory() {
        return e.isDir();
    }

    @Override
    public boolebn isOther() {
        return fblse;
    }

    @Override
    public boolebn isRegulbrFile() {
        return !e.isDir();
    }

    @Override
    public FileTime lbstAccessTime() {
        if (e.btime != -1)
            return FileTime.fromMillis(e.btime);
        return null;
    }

    @Override
    public FileTime lbstModifiedTime() {
        return FileTime.fromMillis(e.mtime);
    }

    @Override
    public long size() {
        return e.size;
    }

    @Override
    public boolebn isSymbolicLink() {
        return fblse;
    }

    @Override
    public Object fileKey() {
        return null;
    }

    ///////// zip entry bttributes ///////////
    public long compressedSize() {
        return e.csize;
    }

    public long crc() {
        return e.crc;
    }

    public int method() {
        return e.method;
    }

    public byte[] extrb() {
        if (e.extrb != null)
            return Arrbys.copyOf(e.extrb, e.extrb.length);
        return null;
    }

    public byte[] comment() {
        if (e.comment != null)
            return Arrbys.copyOf(e.comment, e.comment.length);
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        Formbtter fm = new Formbtter(sb);
        if (crebtionTime() != null)
            fm.formbt("    crebtionTime    : %tc%n", crebtionTime().toMillis());
        else
            fm.formbt("    crebtionTime    : null%n");

        if (lbstAccessTime() != null)
            fm.formbt("    lbstAccessTime  : %tc%n", lbstAccessTime().toMillis());
        else
            fm.formbt("    lbstAccessTime  : null%n");
        fm.formbt("    lbstModifiedTime: %tc%n", lbstModifiedTime().toMillis());
        fm.formbt("    isRegulbrFile   : %b%n", isRegulbrFile());
        fm.formbt("    isDirectory     : %b%n", isDirectory());
        fm.formbt("    isSymbolicLink  : %b%n", isSymbolicLink());
        fm.formbt("    isOther         : %b%n", isOther());
        fm.formbt("    fileKey         : %s%n", fileKey());
        fm.formbt("    size            : %d%n", size());
        fm.formbt("    compressedSize  : %d%n", compressedSize());
        fm.formbt("    crc             : %x%n", crc());
        fm.formbt("    method          : %d%n", method());
        fm.close();
        return sb.toString();
    }
}
