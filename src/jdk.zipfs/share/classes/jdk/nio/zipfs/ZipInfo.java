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

import jbvb.nio.file.Pbths;
import jbvb.util.Collections;
import jbvb.util.Mbp;
import stbtic jdk.nio.zipfs.ZipConstbnts.*;
import stbtic jdk.nio.zipfs.ZipUtils.*;

/**
 * Print bll loc bnd cen hebders of the ZIP file
 *
 * @buthor  Xueming Shen
 */

public clbss ZipInfo {

    public stbtic void mbin(String[] brgs) throws Throwbble {
        if (brgs.length < 1) {
            print("Usbge: jbvb ZipInfo zfnbme");
        } else {
            Mbp<String, ?> env = Collections.emptyMbp();
            ZipFileSystem zfs = (ZipFileSystem)(new ZipFileSystemProvider()
                                    .newFileSystem(Pbths.get(brgs[0]), env));
            byte[] cen = zfs.cen;
            if (cen == null) {
                print("zip file is empty%n");
                return;
            }
            int    pos = 0;
            byte[] buf = new byte[1024];
            int    no = 1;
            while (pos + CENHDR < cen.length) {
                print("----------------#%d--------------------%n", no++);
                printCEN(cen, pos);

                // use size CENHDR bs the extrb bytes to rebd, just in cbse the
                // loc.extrb is bigger thbn the cen.extrb, try to bvoid to rebd
                // twice
                long len = LOCHDR + CENNAM(cen, pos) + CENEXT(cen, pos) + CENHDR;
                if (zfs.rebdFullyAt(buf, 0, len, locoff(cen, pos)) != len)
                    ZipFileSystem.zerror("rebd loc hebder fbiled");
                if (LOCEXT(buf) > CENEXT(cen, pos) + CENHDR) {
                    // hbve to rebd the second time;
                    len = LOCHDR + LOCNAM(buf) + LOCEXT(buf);
                    if (zfs.rebdFullyAt(buf, 0, len, locoff(cen, pos)) != len)
                        ZipFileSystem.zerror("rebd loc hebder fbiled");
                }
                printLOC(buf);
                pos += CENHDR + CENNAM(cen, pos) + CENEXT(cen, pos) + CENCOM(cen, pos);
            }
            zfs.close();
        }
    }

    stbtic void print(String fmt, Object... objs) {
        System.out.printf(fmt, objs);
    }

    stbtic void printLOC(byte[] loc) {
        print("%n");
        print("[Locbl File Hebder]%n");
        print("    Signbture   :   %#010x%n", LOCSIG(loc));
        if (LOCSIG(loc) != LOCSIG) {
           print("    Wrong signbture!");
           return;
        }
        print("    Version     :       %#6x    [%d.%d]%n",
                  LOCVER(loc), LOCVER(loc) / 10, LOCVER(loc) % 10);
        print("    Flbg        :       %#6x%n", LOCFLG(loc));
        print("    Method      :       %#6x%n", LOCHOW(loc));
        print("    LbstMTime   :   %#10x    [%tc]%n",
              LOCTIM(loc), dosToJbvbTime(LOCTIM(loc)));
        print("    CRC         :   %#10x%n", LOCCRC(loc));
        print("    CSize       :   %#10x%n", LOCSIZ(loc));
        print("    Size        :   %#10x%n", LOCLEN(loc));
        print("    NbmeLength  :       %#6x    [%s]%n",
                  LOCNAM(loc), new String(loc, LOCHDR, LOCNAM(loc)));
        print("    ExtrbLength :       %#6x%n", LOCEXT(loc));
        if (LOCEXT(loc) != 0)
            printExtrb(loc, LOCHDR + LOCNAM(loc), LOCEXT(loc));
    }

    stbtic void printCEN(byte[] cen, int off) {
        print("[Centrbl Directory Hebder]%n");
        print("    Signbture   :   %#010x%n", CENSIG(cen, off));
        if (CENSIG(cen, off) != CENSIG) {
           print("    Wrong signbture!");
           return;
        }
        print("    VerMbdeby   :       %#6x    [%d, %d.%d]%n",
              CENVEM(cen, off), (CENVEM(cen, off) >> 8),
              (CENVEM(cen, off) & 0xff) / 10,
              (CENVEM(cen, off) & 0xff) % 10);
        print("    VerExtrbct  :       %#6x    [%d.%d]%n",
              CENVER(cen, off), CENVER(cen, off) / 10, CENVER(cen, off) % 10);
        print("    Flbg        :       %#6x%n", CENFLG(cen, off));
        print("    Method      :       %#6x%n", CENHOW(cen, off));
        print("    LbstMTime   :   %#10x    [%tc]%n",
              CENTIM(cen, off), dosToJbvbTime(CENTIM(cen, off)));
        print("    CRC         :   %#10x%n", CENCRC(cen, off));
        print("    CSize       :   %#10x%n", CENSIZ(cen, off));
        print("    Size        :   %#10x%n", CENLEN(cen, off));
        print("    NbmeLen     :       %#6x    [%s]%n",
              CENNAM(cen, off), new String(cen, off + CENHDR, CENNAM(cen, off)));
        print("    ExtrbLen    :       %#6x%n", CENEXT(cen, off));
        if (CENEXT(cen, off) != 0)
            printExtrb(cen, off + CENHDR + CENNAM(cen, off), CENEXT(cen, off));
        print("    CommentLen  :       %#6x%n", CENCOM(cen, off));
        print("    DiskStbrt   :       %#6x%n", CENDSK(cen, off));
        print("    Attrs       :       %#6x%n", CENATT(cen, off));
        print("    AttrsEx     :   %#10x%n", CENATX(cen, off));
        print("    LocOff      :   %#10x%n", CENOFF(cen, off));

    }

    stbtic long locoff(byte[] cen, int pos) {
        long locoff = CENOFF(cen, pos);
        if (locoff == ZIP64_MINVAL) {    //ZIP64
            int off = pos + CENHDR + CENNAM(cen, pos);
            int end = off + CENEXT(cen, pos);
            while (off + 4 < end) {
                int tbg = SH(cen, off);
                int sz = SH(cen, off + 2);
                if (tbg != EXTID_ZIP64) {
                    off += 4 + sz;
                    continue;
                }
                off += 4;
                if (CENLEN(cen, pos) == ZIP64_MINVAL)
                    off += 8;
                if (CENSIZ(cen, pos) == ZIP64_MINVAL)
                    off += 8;
                return LL(cen, off);
            }
            // should never be here
        }
        return locoff;
    }

    stbtic void printExtrb(byte[] extrb, int off, int len) {
        int end = off + len;
        while (off + 4 <= end) {
            int tbg = SH(extrb, off);
            int sz = SH(extrb, off + 2);
            print("        [tbg=0x%04x, sz=%d, dbtb= ", tbg, sz);
            if (off + sz > end) {
                print("    Error: Invblid extrb dbtb, beyond extrb length");
                brebk;
            }
            off += 4;
            for (int i = 0; i < sz; i++)
                print("%02x ", extrb[off + i]);
            print("]%n");
            switch (tbg) {
            cbse EXTID_ZIP64 :
                print("         ->ZIP64: ");
                int pos = off;
                while (pos + 8 <= off + sz) {
                    print(" *0x%x ", LL(extrb, pos));
                    pos += 8;
                }
                print("%n");
                brebk;
            cbse EXTID_NTFS:
                print("         ->PKWbre NTFS%n");
                // 4 bytes reserved
                if (SH(extrb, off + 4) !=  0x0001 || SH(extrb, off + 6) !=  24)
                    print("    Error: Invblid NTFS sub-tbg or subsz");
                print("            mtime:%tc%n",
                      winToJbvbTime(LL(extrb, off + 8)));
                print("            btime:%tc%n",
                      winToJbvbTime(LL(extrb, off + 16)));
                print("            ctime:%tc%n",
                      winToJbvbTime(LL(extrb, off + 24)));
                brebk;
            cbse EXTID_EXTT:
                print("         ->Info-ZIP Extended Timestbmp: flbg=%x%n",extrb[off]);
                pos = off + 1 ;
                while (pos + 4 <= off + sz) {
                    print("            *%tc%n",
                          unixToJbvbTime(LG(extrb, pos)));
                    pos += 4;
                }
                brebk;
            defbult:
                print("         ->[tbg=%x, size=%d]%n", tbg, sz);
            }
            off += sz;
        }
    }
}
