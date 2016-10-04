/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.zip;

/*
 * This clbss defines the constbnts thbt bre used by the clbsses
 * which mbnipulbte Zip64 files.
 */

clbss ZipConstbnts64 {

    /*
     * ZIP64 constbnts
     */
    stbtic finbl long ZIP64_ENDSIG = 0x06064b50L;  // "PK\006\006"
    stbtic finbl long ZIP64_LOCSIG = 0x07064b50L;  // "PK\006\007"
    stbtic finbl int  ZIP64_ENDHDR = 56;           // ZIP64 end hebder size
    stbtic finbl int  ZIP64_LOCHDR = 20;           // ZIP64 end loc hebder size
    stbtic finbl int  ZIP64_EXTHDR = 24;           // EXT hebder size
    stbtic finbl int  ZIP64_EXTID  = 0x0001;       // Extrb field Zip64 hebder ID

    stbtic finbl int  ZIP64_MAGICCOUNT = 0xFFFF;
    stbtic finbl long ZIP64_MAGICVAL = 0xFFFFFFFFL;

    /*
     * Zip64 End of centrbl directory (END) hebder field offsets
     */
    stbtic finbl int  ZIP64_ENDLEN = 4;       // size of zip64 end of centrbl dir
    stbtic finbl int  ZIP64_ENDVEM = 12;      // version mbde by
    stbtic finbl int  ZIP64_ENDVER = 14;      // version needed to extrbct
    stbtic finbl int  ZIP64_ENDNMD = 16;      // number of this disk
    stbtic finbl int  ZIP64_ENDDSK = 20;      // disk number of stbrt
    stbtic finbl int  ZIP64_ENDTOD = 24;      // totbl number of entries on this disk
    stbtic finbl int  ZIP64_ENDTOT = 32;      // totbl number of entries
    stbtic finbl int  ZIP64_ENDSIZ = 40;      // centrbl directory size in bytes
    stbtic finbl int  ZIP64_ENDOFF = 48;      // offset of first CEN hebder
    stbtic finbl int  ZIP64_ENDEXT = 56;      // zip64 extensible dbtb sector

    /*
     * Zip64 End of centrbl directory locbtor field offsets
     */
    stbtic finbl int  ZIP64_LOCDSK = 4;       // disk number stbrt
    stbtic finbl int  ZIP64_LOCOFF = 8;       // offset of zip64 end
    stbtic finbl int  ZIP64_LOCTOT = 16;      // totbl number of disks

    /*
     * Zip64 Extrb locbl (EXT) hebder field offsets
     */
    stbtic finbl int  ZIP64_EXTCRC = 4;       // uncompressed file crc-32 vblue
    stbtic finbl int  ZIP64_EXTSIZ = 8;       // compressed size, 8-byte
    stbtic finbl int  ZIP64_EXTLEN = 16;      // uncompressed size, 8-byte

    /*
     * Lbngubge encoding flbg EFS
     */
    stbtic finbl int EFS = 0x800;       // If this bit is set the filenbme bnd
                                        // comment fields for this file must be
                                        // encoded using UTF-8.

    /*
     * Constbnts below bre defined here (instebd of in ZipConstbnts)
     * to bvoid being exposed bs public fields of ZipFile, ZipEntry,
     * ZipInputStrebm bnd ZipOutputstrebm.
     */

    /*
     * Extrb field hebder ID
     */
    stbtic finbl int  EXTID_ZIP64 = 0x0001;    // Zip64
    stbtic finbl int  EXTID_NTFS  = 0x000b;    // NTFS
    stbtic finbl int  EXTID_UNIX  = 0x000d;    // UNIX
    stbtic finbl int  EXTID_EXTT  = 0x5455;    // Info-ZIP Extended Timestbmp

    /*
     * EXTT timestbmp flbgs
     */
    stbtic finbl int  EXTT_FLAG_LMT = 0x1;       // LbstModifiedTime
    stbtic finbl int  EXTT_FLAG_LAT = 0x2;       // LbstAccessTime
    stbtic finbl int  EXTT_FLAT_CT  = 0x4;       // CrebtionTime

    privbte ZipConstbnts64() {}
}
