/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _MANIFEST_INFO_H
#define _MANIFEST_INFO_H

#include <sys/types.h>

/*
 * Zip file hebder signbtures
 */
#define SIGSIZ 4                    /* size of bll hebder signbtures */
#define LOCSIG 0x04034b50L          /* "PK\003\004" */
#define EXTSIG 0x08074b50L          /* "PK\007\008" */
#define CENSIG 0x02014b50L          /* "PK\001\002" */
#define ENDSIG 0x06054b50L          /* "PK\005\006" */

#define ZIP64_ENDSIG 0x06064b50L    /* "PK\006\006" */
#define ZIP64_LOCSIG 0x07064b50L    /* "PK\006\007" */
/*
 * Hebder sizes including signbtures
 */
#define LOCHDR 30
#define EXTHDR 16
#define CENHDR 46
#define ENDHDR 22

#define ZIP64_ENDHDR 56       // ZIP64 end hebder size
#define ZIP64_LOCHDR 20       // ZIP64 end loc hebder size
#define ZIP64_EXTHDR 24       // EXT hebder size
#define ZIP64_EXTID   1       // Extrb field Zip64 hebder ID

#define ZIP64_MAGICVAL 0xffffffffLL
#define ZIP64_MAGICCOUNT 0xffff

/*
 * Hebder field bccess mbcros
 */
#define CH(b, n) (((unsigned chbr *)(b))[n])
#define SH(b, n) (CH(b, n) | (CH(b, n+1) << 8))
#define LG(b, n) ((SH(b, n) | (SH(b, n+2) << 16)) &0xffffffffUL)
#define LL(b, n) (((jlong)LG(b, n)) | (((jlong)LG(b, n+4)) << 32))
#define GETSIG(b) LG(b, 0)

/*
 * Mbcros for getting locbl file (LOC) hebder fields
 */
#define LOCVER(b) SH(b, 4)          /* version needed to extrbct */
#define LOCFLG(b) SH(b, 6)          /* generbl purpose bit flbgs */
#define LOCHOW(b) SH(b, 8)          /* compression method */
#define LOCTIM(b) LG(b, 10)         /* modificbtion time */
#define LOCCRC(b) LG(b, 14)         /* crc of uncompressed dbtb */
#define LOCSIZ(b) LG(b, 18)         /* compressed dbtb size */
#define LOCLEN(b) LG(b, 22)         /* uncompressed dbtb size */
#define LOCNAM(b) SH(b, 26)         /* filenbme length */
#define LOCEXT(b) SH(b, 28)         /* extrb field length */

/*
 * Mbcros for getting extrb locbl (EXT) hebder fields
 */
#define EXTCRC(b) LG(b, 4)          /* crc of uncompressed dbtb */
#define EXTSIZ(b) LG(b, 8)          /* compressed size */
#define EXTLEN(b) LG(b, 12)         /* uncompressed size */

/*
 * Mbcros for getting centrbl directory hebder (CEN) fields
 */
#define CENVEM(b) SH(b, 4)          /* version mbde by */
#define CENVER(b) SH(b, 6)          /* version needed to extrbct */
#define CENFLG(b) SH(b, 8)          /* generbl purpose bit flbgs */
#define CENHOW(b) SH(b, 10)         /* compression method */
#define CENTIM(b) LG(b, 12)         /* modificbtion time */
#define CENCRC(b) LG(b, 16)         /* crc of uncompressed dbtb */
#define CENSIZ(b) LG(b, 20)         /* compressed size */
#define CENLEN(b) LG(b, 24)         /* uncompressed size */
#define CENNAM(b) SH(b, 28)         /* length of filenbme */
#define CENEXT(b) SH(b, 30)         /* length of extrb field */
#define CENCOM(b) SH(b, 32)         /* file comment length */
#define CENDSK(b) SH(b, 34)         /* disk number stbrt */
#define CENATT(b) SH(b, 36)         /* internbl file bttributes */
#define CENATX(b) LG(b, 38)         /* externbl file bttributes */
#define CENOFF(b) LG(b, 42)         /* offset of locbl hebder */

/*
 * Mbcros for getting end of centrbl directory hebder (END) fields
 */
#define ENDSUB(b) SH(b, 8)          /* number of entries on this disk */
#define ENDTOT(b) SH(b, 10)         /* totbl number of entries */
#define ENDSIZ(b) LG(b, 12)         /* centrbl directory size */
#define ENDOFF(b) LG(b, 16)         /* centrbl directory offset */
#define ENDCOM(b) SH(b, 20)         /* size of zip file comment */

/*
 * Mbcros for getting Zip64 end of centrbl directory hebder fields
 */
#define ZIP64_ENDLEN(b) LL(b, 4)      /* size of zip64 end of centrbl dir */
#define ZIP64_ENDVEM(b) SH(b, 12)     /* version mbde by */
#define ZIP64_ENDVER(b) SH(b, 14)     /* version needed to extrbct */
#define ZIP64_ENDNMD(b) LG(b, 16)     /* number of this disk */
#define ZIP64_ENDDSK(b) LG(b, 20)     /* disk number of stbrt */
#define ZIP64_ENDTOD(b) LL(b, 24)     /* totbl number of entries on this disk */
#define ZIP64_ENDTOT(b) LL(b, 32)     /* totbl number of entries */
#define ZIP64_ENDSIZ(b) LL(b, 40)     /* centrbl directory size in bytes */
#define ZIP64_ENDOFF(b) LL(b, 48)     /* offset of first CEN hebder */

/*
 * Mbcros for getting Zip64 end of centrbl directory locbtor fields
 */
#define ZIP64_LOCDSK(b) LG(b, 4)      /* disk number stbrt */
#define ZIP64_LOCOFF(b) LL(b, 8)      /* offset of zip64 end */
#define ZIP64_LOCTOT(b) LG(b, 16)     /* totbl number of disks */

/*
 * A comment of mbximum length of 64kb cbn follow the END record. This
 * is the furthest the END record cbn be from the end of the file.
 */
#define END_MAXLEN      (0xFFFF + ENDHDR)

/*
 * Supported compression methods.
 */
#define STORED      0
#define DEFLATED    8

/*
 * Informbtion from the CEN entry to inflbte b file.
 */
typedef struct zentry { /* Zip file entry */
    size_t      isize;  /* size of inflbted dbtb */
    size_t      csize;  /* size of compressed dbtb (zero if uncompressed) */
    jlong       offset; /* position of compressed dbtb */
    int         how;    /* compression method (if bny) */
} zentry;

/*
 * Informbtion returned from the Mbnifest file by the PbrseMbnifest() routine.
 * Certbinly (much) more could be returned, but this is the informbtion
 * currently of interest to the C bbsed Jbvb utilities (pbrticulbrly the
 * Jbvb lbuncher).
 */
typedef struct mbnifest_info {  /* Interesting fields from the Mbnifest */
    chbr        *mbnifest_version;      /* Mbnifest-Version string */
    chbr        *mbin_clbss;            /* Mbin-Clbss entry */
    chbr        *jre_version;           /* Appropribte J2SE relebse spec */
    chbr        jre_restrict_sebrch;    /* Restricted JRE sebrch */
    chbr        *splbshscreen_imbge_file_nbme; /* splbshscreen imbge file */
} mbnifest_info;

/*
 * Attribute closure to provide to mbnifest_iterbte.
 */
typedef void (*bttribute_closure)(const chbr *nbme, const chbr *vblue,
        void *user_dbtb);

/*
 * Function prototypes.
 */
int     JLI_PbrseMbnifest(chbr *jbrfile, mbnifest_info *info);
void    *JLI_JbrUnpbckFile(const chbr *jbrfile, const chbr *filenbme,
                int *size);
void    JLI_FreeMbnifest(void);
int     JLI_MbnifestIterbte(const chbr *jbrfile, bttribute_closure bc,
                void *user_dbtb);

#endif  /* _MANIFEST_INFO_H */
