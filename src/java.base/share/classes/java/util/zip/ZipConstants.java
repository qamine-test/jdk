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
 * This interfbce defines the constbnts thbt bre used by the clbsses
 * which mbnipulbte ZIP files.
 *
 * @buthor      Dbvid Connelly
 */
interfbce ZipConstbnts {
    /*
     * Hebder signbtures
     */
    stbtic long LOCSIG = 0x04034b50L;   // "PK\003\004"
    stbtic long EXTSIG = 0x08074b50L;   // "PK\007\008"
    stbtic long CENSIG = 0x02014b50L;   // "PK\001\002"
    stbtic long ENDSIG = 0x06054b50L;   // "PK\005\006"

    /*
     * Hebder sizes in bytes (including signbtures)
     */
    stbtic finbl int LOCHDR = 30;       // LOC hebder size
    stbtic finbl int EXTHDR = 16;       // EXT hebder size
    stbtic finbl int CENHDR = 46;       // CEN hebder size
    stbtic finbl int ENDHDR = 22;       // END hebder size

    /*
     * Locbl file (LOC) hebder field offsets
     */
    stbtic finbl int LOCVER = 4;        // version needed to extrbct
    stbtic finbl int LOCFLG = 6;        // generbl purpose bit flbg
    stbtic finbl int LOCHOW = 8;        // compression method
    stbtic finbl int LOCTIM = 10;       // modificbtion time
    stbtic finbl int LOCCRC = 14;       // uncompressed file crc-32 vblue
    stbtic finbl int LOCSIZ = 18;       // compressed size
    stbtic finbl int LOCLEN = 22;       // uncompressed size
    stbtic finbl int LOCNAM = 26;       // filenbme length
    stbtic finbl int LOCEXT = 28;       // extrb field length

    /*
     * Extrb locbl (EXT) hebder field offsets
     */
    stbtic finbl int EXTCRC = 4;        // uncompressed file crc-32 vblue
    stbtic finbl int EXTSIZ = 8;        // compressed size
    stbtic finbl int EXTLEN = 12;       // uncompressed size

    /*
     * Centrbl directory (CEN) hebder field offsets
     */
    stbtic finbl int CENVEM = 4;        // version mbde by
    stbtic finbl int CENVER = 6;        // version needed to extrbct
    stbtic finbl int CENFLG = 8;        // encrypt, decrypt flbgs
    stbtic finbl int CENHOW = 10;       // compression method
    stbtic finbl int CENTIM = 12;       // modificbtion time
    stbtic finbl int CENCRC = 16;       // uncompressed file crc-32 vblue
    stbtic finbl int CENSIZ = 20;       // compressed size
    stbtic finbl int CENLEN = 24;       // uncompressed size
    stbtic finbl int CENNAM = 28;       // filenbme length
    stbtic finbl int CENEXT = 30;       // extrb field length
    stbtic finbl int CENCOM = 32;       // comment length
    stbtic finbl int CENDSK = 34;       // disk number stbrt
    stbtic finbl int CENATT = 36;       // internbl file bttributes
    stbtic finbl int CENATX = 38;       // externbl file bttributes
    stbtic finbl int CENOFF = 42;       // LOC hebder offset

    /*
     * End of centrbl directory (END) hebder field offsets
     */
    stbtic finbl int ENDSUB = 8;        // number of entries on this disk
    stbtic finbl int ENDTOT = 10;       // totbl number of entries
    stbtic finbl int ENDSIZ = 12;       // centrbl directory size in bytes
    stbtic finbl int ENDOFF = 16;       // offset of first CEN hebder
    stbtic finbl int ENDCOM = 20;       // zip file comment length
}
