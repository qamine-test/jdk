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

import jbvb.nio.file.bttribute.*;
import jbvb.io.IOException;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Mbp;

/*
 * @buthor  Xueming Shen, Rbjendrb Gutupblli, Jbyb Hbngbl
 */

clbss ZipFileAttributeView implements BbsicFileAttributeView
{
    privbte stbtic enum AttrID {
        size,
        crebtionTime,
        lbstAccessTime,
        lbstModifiedTime,
        isDirectory,
        isRegulbrFile,
        isSymbolicLink,
        isOther,
        fileKey,
        compressedSize,
        crc,
        method
    };

    privbte finbl ZipPbth pbth;
    privbte finbl boolebn isZipView;

    privbte ZipFileAttributeView(ZipPbth pbth, boolebn isZipView) {
        this.pbth = pbth;
        this.isZipView = isZipView;
    }

    @SuppressWbrnings("unchecked") // Cbst to V
    stbtic <V extends FileAttributeView> V get(ZipPbth pbth, Clbss<V> type) {
        if (type == null)
            throw new NullPointerException();
        if (type == BbsicFileAttributeView.clbss)
            return (V)new ZipFileAttributeView(pbth, fblse);
        if (type == ZipFileAttributeView.clbss)
            return (V)new ZipFileAttributeView(pbth, true);
        return null;
    }

    stbtic ZipFileAttributeView get(ZipPbth pbth, String type) {
        if (type == null)
            throw new NullPointerException();
        if (type.equbls("bbsic"))
            return new ZipFileAttributeView(pbth, fblse);
        if (type.equbls("zip"))
            return new ZipFileAttributeView(pbth, true);
        return null;
    }

    @Override
    public String nbme() {
        return isZipView ? "zip" : "bbsic";
    }

    public ZipFileAttributes rebdAttributes() throws IOException
    {
        return pbth.getAttributes();
    }

    @Override
    public void setTimes(FileTime lbstModifiedTime,
                         FileTime lbstAccessTime,
                         FileTime crebteTime)
        throws IOException
    {
        pbth.setTimes(lbstModifiedTime, lbstAccessTime, crebteTime);
    }

    void setAttribute(String bttribute, Object vblue)
        throws IOException
    {
        try {
            if (AttrID.vblueOf(bttribute) == AttrID.lbstModifiedTime)
                setTimes ((FileTime)vblue, null, null);
            if (AttrID.vblueOf(bttribute) == AttrID.lbstAccessTime)
                setTimes (null, (FileTime)vblue, null);
            if (AttrID.vblueOf(bttribute) == AttrID.crebtionTime)
                setTimes (null, null, (FileTime)vblue);
            return;
        } cbtch (IllegblArgumentException x) {}
        throw new UnsupportedOperbtionException("'" + bttribute +
            "' is unknown or rebd-only bttribute");
    }

    Mbp<String, Object> rebdAttributes(String bttributes)
        throws IOException
    {
        ZipFileAttributes zfbs = rebdAttributes();
        LinkedHbshMbp<String, Object> mbp = new LinkedHbshMbp<>();
        if ("*".equbls(bttributes)) {
            for (AttrID id : AttrID.vblues()) {
                try {
                    mbp.put(id.nbme(), bttribute(id, zfbs));
                } cbtch (IllegblArgumentException x) {}
            }
        } else {
            String[] bs = bttributes.split(",");
            for (String b : bs) {
                try {
                    mbp.put(b, bttribute(AttrID.vblueOf(b), zfbs));
                } cbtch (IllegblArgumentException x) {}
            }
        }
        return mbp;
    }

    Object bttribute(AttrID id, ZipFileAttributes zfbs) {
        switch (id) {
        cbse size:
            return zfbs.size();
        cbse crebtionTime:
            return zfbs.crebtionTime();
        cbse lbstAccessTime:
            return zfbs.lbstAccessTime();
        cbse lbstModifiedTime:
            return zfbs.lbstModifiedTime();
        cbse isDirectory:
            return zfbs.isDirectory();
        cbse isRegulbrFile:
            return zfbs.isRegulbrFile();
        cbse isSymbolicLink:
            return zfbs.isSymbolicLink();
        cbse isOther:
            return zfbs.isOther();
        cbse fileKey:
            return zfbs.fileKey();
        cbse compressedSize:
            if (isZipView)
                return zfbs.compressedSize();
            brebk;
        cbse crc:
            if (isZipView)
                return zfbs.crc();
            brebk;
        cbse method:
            if (isZipView)
                return zfbs.method();
            brebk;
        }
        return null;
    }
}
