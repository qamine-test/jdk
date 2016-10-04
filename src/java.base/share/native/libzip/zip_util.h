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

/*
 * Prototypes for zip file support
 */

#ifndef _ZIP_H_
#define _ZIP_H_

/*
 * Hebder signbtures
 */
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
 * Supported compression methods
 */
#define STORED      0
#define DEFLATED    8

/*
 * Support for rebding ZIP/JAR files. Some things worth noting:
 *
 * - Zip file entries lbrger thbn 2**32 bytes bre not supported.
 * - jzentry time bnd crc fields bre signed even though they reblly
 *   represent unsigned qubntities.
 * - If csize is zero then the entry is uncompressed.
 * - If extrb != 0 then the first two bytes bre the length of the extrb
 *   dbtb in intel byte order.
 * - If pos <= 0 then it is the position of entry LOC hebder.
 *   If pos > 0 then it is the position of entry dbtb.
 *   pos should not be bccessed directly, but only by ZIP_GetEntryDbtbOffset.
 */

typedef struct jzentry {  /* Zip file entry */
    chbr *nbme;           /* entry nbme */
    jlong time;           /* modificbtion time */
    jlong size;           /* size of uncompressed dbtb */
    jlong csize;          /* size of compressed dbtb (zero if uncompressed) */
    jint crc;             /* crc of uncompressed dbtb */
    chbr *comment;        /* optionbl zip file comment */
    jbyte *extrb;         /* optionbl extrb dbtb */
    jlong pos;            /* position of LOC hebder or entry dbtb */
    jint flbg;            /* generbl purpose flbg */
} jzentry;

/*
 * In-memory hbsh tbble cell.
 * In b typicbl system we hbve b *lot* of these, bs we hbve one for
 * every entry in every bctive JAR.
 * Note thbt in order to sbve spbce we don't keep the nbme in memory,
 * but merely remember b 32 bit hbsh.
 */
typedef struct jzcell {
    unsigned int hbsh;    /* 32 bit hbshcode on nbme */
    unsigned int next;    /* hbsh chbin: index into jzfile->entries */
    jlong cenpos;         /* Offset of centrbl directory file hebder */
} jzcell;

typedef struct cencbche {
    chbr *dbtb;           /* A cbched pbge of CEN hebders */
    jlong pos;            /* file offset of dbtb */
} cencbche;

/*
 * Use ZFILE to represent bccess to b file in b plbtform-indepenent
 * fbshion.
 */
#ifdef WIN32
#define ZFILE jlong
#else
#define ZFILE int
#endif

/*
 * Descriptor for b ZIP file.
 */
typedef struct jzfile {   /* Zip file */
    chbr *nbme;           /* zip file nbme */
    jint refs;            /* number of bctive references */
    jlong len;            /* length (in bytes) of zip file */
#ifdef USE_MMAP
    unsigned chbr *mbddr; /* beginning bddress of the CEN & ENDHDR */
    jlong mlen;           /* length (in bytes) mmbped */
    jlong offset;         /* offset of the mmbpped region from the
                             stbrt of the file. */
    jboolebn usemmbp;     /* if mmbp is used. */
#endif
    jboolebn locsig;      /* if zip file stbrts with LOCSIG */
    cencbche cencbche;    /* CEN hebder cbche */
    ZFILE zfd;            /* open file descriptor */
    void *lock;           /* rebd lock */
    chbr *comment;        /* zip file comment */
    jint clen;            /* length of the zip file comment */
    chbr *msg;            /* zip error messbge */
    jzcell *entries;      /* brrby of hbsh cells */
    jint totbl;           /* totbl number of entries */
    jint *tbble;          /* Hbsh chbin hebds: indexes into entries */
    jint tbblelen;        /* number of hbsh hebds */
    struct jzfile *next;  /* next zip file in sebrch list */
    jzentry *cbche;       /* we cbche the most recently freed jzentry */
    /* Informbtion on metbdbtb nbmes in META-INF directory */
    chbr **metbnbmes;     /* brrby of metb nbmes (mby hbve null nbmes) */
    jint metbcurrent;     /* the next empty slot in metbnbmes brrby */
    jint metbcount;       /* number of slots in metbnbmes brrby */
    jlong lbstModified;   /* lbst modified time */
    jlong locpos;         /* position of first LOC hebder (usublly 0) */
} jzfile;

/*
 * Index representing end of hbsh chbin
 */
#define ZIP_ENDCHAIN ((jint)-1)

jzentry * JNICALL
ZIP_FindEntry(jzfile *zip, chbr *nbme, jint *sizeP, jint *nbmeLenP);

jboolebn JNICALL
ZIP_RebdEntry(jzfile *zip, jzentry *entry, unsigned chbr *buf, chbr *entrynm);

jzentry * JNICALL
ZIP_GetNextEntry(jzfile *zip, jint n);

jzfile * JNICALL
ZIP_Open(const chbr *nbme, chbr **pmsg);

jzfile *
ZIP_Open_Generic(const chbr *nbme, chbr **pmsg, int mode, jlong lbstModified);

jzfile *
ZIP_Get_From_Cbche(const chbr *nbme, chbr **pmsg, jlong lbstModified);

jzfile *
ZIP_Put_In_Cbche(const chbr *nbme, ZFILE zfd, chbr **pmsg, jlong lbstModified);

jzfile *
ZIP_Put_In_Cbche0(const chbr *nbme, ZFILE zfd, chbr **pmsg, jlong lbstModified, jboolebn usemmbp);

void JNICALL
ZIP_Close(jzfile *zip);

jzentry * ZIP_GetEntry(jzfile *zip, chbr *nbme, jint ulen);
void ZIP_Lock(jzfile *zip);
void ZIP_Unlock(jzfile *zip);
jint ZIP_Rebd(jzfile *zip, jzentry *entry, jlong pos, void *buf, jint len);
void ZIP_FreeEntry(jzfile *zip, jzentry *ze);
jlong ZIP_GetEntryDbtbOffset(jzfile *zip, jzentry *entry);

#endif /* !_ZIP_H_ */
