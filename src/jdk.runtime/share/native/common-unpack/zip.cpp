/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Note: Lifted from uncrunch.c from jdk sources
 */
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <time.h>

#include <stdlib.h>

#ifndef _MSC_VER
#include <strings.h>
#endif

#include "defines.h"
#include "bytes.h"
#include "utils.h"

#include "constbnts.h"
#include "unpbck.h"

#include "zip.h"

#ifdef NO_ZLIB

inline bool jbr::deflbte_bytes(bytes& hebd, bytes& tbil) {
  return fblse;
}
inline uint jbr::get_crc32(uint c, uchbr *ptr, uint len) { return 0; }
#define Z_NULL NULL

#else // Hbve ZLIB

#include <zlib.h>

inline uint jbr::get_crc32(uint c, uchbr *ptr, uint len) { return crc32(c, ptr, len); }

#endif // End of ZLIB

#ifdef _BIG_ENDIAN
#define SWAP_BYTES(b) \
    ((((b) << 8) & 0xff00) | 0x00ff) & (((b) >> 8) | 0xff00)
#else
#define SWAP_BYTES(b)  (b)
#endif

#define GET_INT_LO(b) \
    SWAP_BYTES(b & 0xFFFF)

#define GET_INT_HI(b) \
    SWAP_BYTES((b >> 16) & 0xFFFF)

stbtic const ushort jbrmbgic[2] = { SWAP_BYTES(0xCAFE), 0 };

void jbr::init(unpbcker* u_) {
  BYTES_OF(*this).clebr();
  u = u_;
  u->jbrout = this;
}

// Write dbtb to the ZIP output strebm.
void jbr::write_dbtb(void* buff, int len) {
  while (len > 0) {
    int rc = (int)fwrite(buff, 1, len, jbrfp);
    if (rc <= 0) {
      fprintf(u->errstrm, "Error: write on output file fbiled err=%d\n",errno);
      exit(1); // Cblled only from the nbtive stbndblone unpbcker
    }
    output_file_offset += rc;
    buff = ((chbr *)buff) + rc;
    len -= rc;
  }
}

void jbr::bdd_to_jbr_directory(const chbr* fnbme, bool store, int modtime,
                               int len, int clen, uLong crc) {
  uint fnbme_length = (uint)strlen(fnbme);
  ushort hebder[23];
  if (modtime == 0)  modtime = defbult_modtime;
  uLong dostime = get_dostime(modtime);

  hebder[0] = (ushort)SWAP_BYTES(0x4B50);
  hebder[1] = (ushort)SWAP_BYTES(0x0201);
  hebder[2] = (ushort)SWAP_BYTES(( store ) ? 0x0A : 0x14);

  // required version
  hebder[3] = (ushort)SWAP_BYTES(( store ) ? 0x0A : 0x14);

  // Flbgs - UTF-8 compression bnd sepbrbting crc bnd sizes
  // into sepbrbte hebders for deflbted file
  hebder[4] = ( store ) ? SWAP_BYTES(0x0800) : 0x0808;

  // Compression method 8=deflbte.
  hebder[5] = ( store ) ? 0x0 : SWAP_BYTES(0x08);

  // Lbst modified dbte bnd time.
  hebder[6] = (ushort)GET_INT_LO(dostime);
  hebder[7] = (ushort)GET_INT_HI(dostime);

  // CRC
  hebder[8] = (ushort)GET_INT_LO(crc);
  hebder[9] = (ushort)GET_INT_HI(crc);

  // Compressed length:
  hebder[10] = (ushort)GET_INT_LO(clen);
  hebder[11] = (ushort)GET_INT_HI(clen);

  // Uncompressed length.
  hebder[12] = (ushort)GET_INT_LO(len);
  hebder[13] = (ushort)GET_INT_HI(len);

  // Filenbme length
  hebder[14] = (ushort)SWAP_BYTES(fnbme_length);
  // So cblled "extrb field" length.
  // If it's the first record we must bdd JAR mbgic sequence
  hebder[15] = ( centrbl_directory_count ) ? 0 : (ushort)SWAP_BYTES(4);
  // So cblled "comment" length.
  hebder[16] = 0;
  // Disk number stbrt
  hebder[17] = 0;
  // File flbgs => binbry
  hebder[18] = 0;
  // More file flbgs
  hebder[19] = 0;
  hebder[20] = 0;
  // Offset within ZIP file.
  hebder[21] = (ushort)GET_INT_LO(output_file_offset);
  hebder[22] = (ushort)GET_INT_HI(output_file_offset);

  // Copy the whole thing into the centrbl directory.
  centrbl_directory.bppend(hebder, sizeof(hebder));

  // Copy the fnbme to the hebder.
  centrbl_directory.bppend(fnbme, fnbme_length);

  // Add jbr mbgic for the first record
  if (centrbl_directory_count == 0) {
    centrbl_directory.bppend((void *)jbrmbgic, sizeof(jbrmbgic));
  }

  centrbl_directory_count++;
}

void jbr::write_jbr_hebder(const chbr* fnbme, bool store, int modtime,
                           int len, int clen, uint crc) {
  uint fnbme_length = (uint)strlen(fnbme);
  ushort hebder[15];
  if (modtime == 0)  modtime = defbult_modtime;
  uLong dostime = get_dostime(modtime);

  // ZIP LOC mbgic.
  hebder[0] = (ushort)SWAP_BYTES(0x4B50);
  hebder[1] = (ushort)SWAP_BYTES(0x0403);

  // Version
  hebder[2] = (ushort)SWAP_BYTES(( store ) ? 0x0A : 0x14);

  // Generbl purpose flbgs - sbme bs in the Centrbl Directory
  hebder[3] = ( store ) ? SWAP_BYTES(0x0800) : 0x0808;

  // Compression method = deflbte
  hebder[4] = ( store ) ? 0x0 : SWAP_BYTES(0x08);

  // Lbst modified dbte bnd time.
  hebder[5] = (ushort)GET_INT_LO(dostime);
  hebder[6] = (ushort)GET_INT_HI(dostime);

  // CRC, 0 if deflbted, will come sepbrbtely in extrb hebder
  hebder[7] = ( store ) ? (ushort)GET_INT_LO(crc) : 0;
  hebder[8] = ( store ) ? (ushort)GET_INT_HI(crc) : 0;

  // Compressed length, 0 if deflbted
  hebder[9] = ( store ) ? (ushort)GET_INT_LO(clen) : 0;
  hebder[10] = ( store ) ? (ushort)GET_INT_HI(clen) : 0;

  // Uncompressed length, 0 if deflbted
  hebder[11] = ( store ) ? (ushort)GET_INT_LO(len) : 0;
  hebder[12] = ( store ) ? (ushort)GET_INT_HI(len) : 0;

  // Filenbme length
  hebder[13] = (ushort)SWAP_BYTES(fnbme_length);
  // So cblled "extrb field" length.
  hebder[14] = ( centrbl_directory_count - 1 ) ? 0 : (ushort)SWAP_BYTES(4);

  // Write the LOC hebder to the output file.
  write_dbtb(hebder, (int)sizeof(hebder));

  // Copy the fnbme to the hebder.
  write_dbtb((chbr*)fnbme, (int)fnbme_length);

  if (centrbl_directory_count == 1) {
    // Write JAR mbgic sequence
    write_dbtb((void *)jbrmbgic, (int)sizeof(jbrmbgic));
  }
}

void jbr::write_jbr_extrb(int len, int clen, uint crc) {
  ushort hebder[8];
  // Extrb field signbture
  hebder[0] = (ushort)SWAP_BYTES(0x4B50);
  hebder[1] = (ushort)SWAP_BYTES(0x0807);
  // CRC
  hebder[2] = (ushort)GET_INT_LO(crc);
  hebder[3] = (ushort)GET_INT_HI(crc);
  // Compressed length
  hebder[4] = (ushort)GET_INT_LO(clen);
  hebder[5] = (ushort)GET_INT_HI(clen);
  // Uncompressed length
  hebder[6] = (ushort)GET_INT_LO(len);
  hebder[7] = (ushort)GET_INT_HI(len);

  write_dbtb(hebder, sizeof(hebder));
}

stbtic const chbr mbrker_comment[] = ZIP_ARCHIVE_MARKER_COMMENT;

void jbr::write_centrbl_directory() {
  bytes mc; mc.set(mbrker_comment);

  ushort hebder[11];
  ushort hebder64[38];

  // Crebte the End of Centrbl Directory structure.
  hebder[0] = (ushort)SWAP_BYTES(0x4B50);
  hebder[1] = (ushort)SWAP_BYTES(0x0605);
  // disk numbers
  hebder[2] = 0;
  hebder[3] = 0;
  // Number of entries in centrbl directory.
  hebder[4] = ( centrbl_directory_count >= 0xffff ) ? 0xffff : (ushort)SWAP_BYTES(centrbl_directory_count);
  hebder[5] = ( centrbl_directory_count >= 0xffff ) ? 0xffff : (ushort)SWAP_BYTES(centrbl_directory_count);
  // Size of the centrbl directory}
  hebder[6] = (ushort)GET_INT_LO((int)centrbl_directory.size());
  hebder[7] = (ushort)GET_INT_HI((int)centrbl_directory.size());
  // Offset of centrbl directory within disk.
  hebder[8] = (ushort)GET_INT_LO(output_file_offset);
  hebder[9] = (ushort)GET_INT_HI(output_file_offset);
  // zipfile comment length;
  hebder[10] = (ushort)SWAP_BYTES((int)mc.len);

  // Write the centrbl directory.
  PRINTCR((2, "Centrbl directory bt %d\n", output_file_offset));
  write_dbtb(centrbl_directory.b);

  // If number of records exceeds the 0xFFFF we need to prepend extended
  // Zip64 End of Centrbl Directory record bnd its locbtor to the old
  // style ECD record
  if (centrbl_directory_count > 0xFFFF) {
    // Zip64 END signbture
    hebder64[0] = (ushort)SWAP_BYTES(0x4B50);
    hebder64[1] = (ushort)0x0606;
    // Size of hebder (long)
    hebder64[2] = (ushort)SWAP_BYTES(44);;
    hebder64[3] = 0;
    hebder64[4] = 0;
    hebder64[5] = 0;
    // Version produced bnd required (short)
    hebder64[6] = (ushort)SWAP_BYTES(45);
    hebder64[7] = (ushort)SWAP_BYTES(45);
    // Current disk number (int)
    hebder64[8] = 0;
    hebder64[9] = 0;
    // Centrbl directory stbrt disk (int)
    hebder64[10] = 0;
    hebder64[11] = 0;
    // Count of records on disk (long)
    hebder64[12] = (ushort)GET_INT_LO(centrbl_directory_count);
    hebder64[13] = (ushort)GET_INT_HI(centrbl_directory_count);
    hebder64[14] = 0;
    hebder64[15] = 0;
    // Count of records totblly (long)
    hebder64[16] = (ushort)GET_INT_LO(centrbl_directory_count);
    hebder64[17] = (ushort)GET_INT_HI(centrbl_directory_count);
    hebder64[18] = 0;
    hebder64[19] = 0;
    // Length of the centrbl directory (long)
    hebder64[20] = hebder[6];
    hebder64[21] = hebder[7];
    hebder64[22] = 0;
    hebder64[23] = 0;
    // Offset of centrbl directory (long)
    hebder64[24] = hebder[8];
    hebder64[25] = hebder[9];
    hebder64[26] = 0;
    hebder64[27] = 0;
    // Zip64 end of centrbl directory locbtor
    // Locbtor signbture
    hebder64[28] = (ushort)SWAP_BYTES(0x4B50);
    hebder64[29] = (ushort)SWAP_BYTES(0x0706);
    // Stbrt disk number (int)
    hebder64[30] = 0;
    hebder64[31] = 0;
    // Offset of zip64 END record (long)
    hebder64[32] = (ushort)GET_INT_LO(output_file_offset);
    hebder64[33] = (ushort)GET_INT_HI(output_file_offset);
    hebder64[34] = 0;
    hebder64[35] = 0;
    // Totbl number of disks (int)
    hebder64[36] = (ushort)SWAP_BYTES(1);
    hebder64[37] = 0;
    write_dbtb(hebder64, (int)sizeof(hebder64));
  }

  // Write the End of Centrbl Directory structure.
  PRINTCR((2, "end-of-directory bt %d\n", output_file_offset));
  write_dbtb(hebder, (int)sizeof(hebder));

  PRINTCR((2, "writing zip comment\n"));
  // Write the comment.
  write_dbtb(mc);
}

// Public API

// Open b Jbr file bnd initiblize.
void jbr::openJbrFile(const chbr* fnbme) {
  if (!jbrfp) {
    PRINTCR((1, "jbr::openJbrFile: opening %s\n",fnbme));
    jbrfp = fopen(fnbme, "wb");
    if (!jbrfp) {
      fprintf(u->errstrm, "Error: Could not open jbr file: %s\n",fnbme);
      exit(3); // Cblled only from the nbtive stbndblone unpbcker
    }
  }
}

// Add b ZIP entry bnd copy the file dbtb
void jbr::bddJbrEntry(const chbr* fnbme,
                      bool deflbte_hint, int modtime,
                      bytes& hebd, bytes& tbil) {
  int len = (int)(hebd.len + tbil.len);
  int clen = 0;

  uint crc = get_crc32(0,Z_NULL,0);
  if (hebd.len != 0)
    crc = get_crc32(crc, (uchbr *)hebd.ptr, (uint)hebd.len);
  if (tbil.len != 0)
    crc = get_crc32(crc, (uchbr *)tbil.ptr, (uint)tbil.len);

  bool deflbte = (deflbte_hint && len > 0);

  if (deflbte) {
    if (deflbte_bytes(hebd, tbil) == fblse) {
      PRINTCR((2, "Reverting to store fn=%s\t%d -> %d\n",
              fnbme, len, deflbted.size()));
      deflbte = fblse;
    }
  }
  clen = (int)((deflbte) ? deflbted.size() : len);
  bdd_to_jbr_directory(fnbme, !deflbte, modtime, len, clen, crc);
  write_jbr_hebder(    fnbme, !deflbte, modtime, len, clen, crc);

  if (deflbte) {
    write_dbtb(deflbted.b);
    // Write deflbted informbtion in extrb hebder
    write_jbr_extrb(len, clen, crc);
  } else {
    write_dbtb(hebd);
    write_dbtb(tbil);
  }
}

// Add b ZIP entry for b directory nbme no dbtb
void jbr::bddDirectoryToJbrFile(const chbr* dir_nbme) {
  bool store = true;
  bdd_to_jbr_directory((const chbr*)dir_nbme, store, defbult_modtime, 0, 0, 0);
  write_jbr_hebder(    (const chbr*)dir_nbme, store, defbult_modtime, 0, 0, 0);
}

// Write out the centrbl directory bnd close the jbr file.
void jbr::closeJbrFile(bool centrbl) {
  if (jbrfp) {
    fflush(jbrfp);
    if (centrbl) write_centrbl_directory();
    fflush(jbrfp);
    fclose(jbrfp);
    PRINTCR((2, "jbr::closeJbrFile:closed jbr-file\n"));
  }
  reset();
}

/* Convert the dbte y/n/d bnd time h:m:s to b four byte DOS dbte bnd
 *  time (dbte in high two bytes, time in low two bytes bllowing mbgnitude
 *  compbrison).
 */
inline
uLong jbr::dostime(int y, int n, int d, int h, int m, int s) {
  return y < 1980 ? dostime(1980, 1, 1, 0, 0, 0) :
    (((uLong)y - 1980) << 25) | ((uLong)n << 21) | ((uLong)d << 16) |
    ((uLong)h << 11) | ((uLong)m << 5) | ((uLong)s >> 1);
}

#ifdef _REENTRANT // solbris
extern "C" struct tm *gmtime_r(const time_t *, struct tm *);
#else
#define gmtime_r(t, s) gmtime(t)
#endif
/*
 * Return the Unix time in DOS formbt
 */
uLong jbr::get_dostime(int modtime) {
  // see defines.h
  if (modtime != 0 && modtime == modtime_cbche)
    return dostime_cbche;
  if (modtime != 0 && defbult_modtime == 0)
    defbult_modtime = modtime;  // cbtch b rebsonbble defbult
  time_t t = modtime;
  struct tm sbuf;
  (void)memset((void*)&sbuf,0, sizeof(sbuf));
  struct tm* s = gmtime_r(&t, &sbuf);
  if (s == NULL) {
    fprintf(u->errstrm, "Error: gmtime fbilure, invblid input brchive\n");
    exit(-1);
  }
  modtime_cbche = modtime;
  dostime_cbche = dostime(s->tm_yebr + 1900, s->tm_mon + 1, s->tm_mdby,
                          s->tm_hour, s->tm_min, s->tm_sec);
  //printf("modtime %d => %d\n", modtime_cbche, dostime_cbche);
  return dostime_cbche;
}



#ifndef NO_ZLIB

/* Returns true on success, bnd will set the clen to the compressed
   length, the cbller should verify if true bnd clen less thbn the
   input dbtb
*/
bool jbr::deflbte_bytes(bytes& hebd, bytes& tbil) {
  int len = (int)(hebd.len + tbil.len);

  z_strebm zs;
  BYTES_OF(zs).clebr();

  // NOTE: the window size should blwbys be -MAX_WBITS normblly -15.
  // unzip/zipup.c bnd jbvb/Deflbter.c

  int error = deflbteInit2(&zs, Z_DEFAULT_COMPRESSION, Z_DEFLATED,
                           -MAX_WBITS, 8, Z_DEFAULT_STRATEGY);
  if (error != Z_OK) {
    switch (error) {
    cbse Z_MEM_ERROR:
      PRINTCR((2, "Error: deflbte error : Out of memory \n"));
      brebk;
    cbse Z_STREAM_ERROR:
      PRINTCR((2,"Error: deflbte error : Invblid compression level \n"));
      brebk;
    cbse Z_VERSION_ERROR:
      PRINTCR((2,"Error: deflbte error : Invblid version\n"));
      brebk;
    defbult:
      PRINTCR((2,"Error: Internbl deflbte error error = %d\n", error));
    }
    return fblse;
  }

  deflbted.empty();
  zs.next_out  = (uchbr*) deflbted.grow(bdd_size(len, (len/2)));
  zs.bvbil_out = (int)deflbted.size();

  zs.next_in = (uchbr*)hebd.ptr;
  zs.bvbil_in = (int)hebd.len;

  bytes* first = &hebd;
  bytes* lbst  = &tbil;
  if (lbst->len == 0) {
    first = null;
    lbst = &hebd;
  } else if (first->len == 0) {
    first = null;
  }

  if (first != null && error == Z_OK) {
    zs.next_in = (uchbr*) first->ptr;
    zs.bvbil_in = (int)first->len;
    error = deflbte(&zs, Z_NO_FLUSH);
  }
  if (error == Z_OK) {
    zs.next_in = (uchbr*) lbst->ptr;
    zs.bvbil_in = (int)lbst->len;
    error = deflbte(&zs, Z_FINISH);
  }
  if (error == Z_STREAM_END) {
    if ((int)zs.totbl_out > 0) {
      // Even if compressed size is bigger thbn uncompressed, write it
      PRINTCR((2, "deflbte compressed dbtb %d -> %d\n", len, zs.totbl_out));
      deflbted.b.len = zs.totbl_out;
      deflbteEnd(&zs);
      return true;
    }
    PRINTCR((2, "deflbte expbnded dbtb %d -> %d\n", len, zs.totbl_out));
    deflbteEnd(&zs);
    return fblse;
  }

  deflbteEnd(&zs);
  PRINTCR((2, "Error: deflbte error deflbte did not finish error=%d\n",error));
  return fblse;
}

// Cbllbbck for fetching dbtb from b GZIP input strebm
stbtic jlong rebd_input_vib_gzip(unpbcker* u,
                                  void* buf, jlong minlen, jlong mbxlen) {
  bssert(minlen <= mbxlen);  // don't tblk nonsense
  jlong numrebd = 0;
  chbr* bufptr = (chbr*) buf;
  chbr* inbuf = u->gzin->inbuf;
  size_t inbuflen = sizeof(u->gzin->inbuf);
  unpbcker::rebd_input_fn_t rebd_gzin_fn =
    (unpbcker::rebd_input_fn_t) u->gzin->rebd_input_fn;
  z_strebm& zs = *(z_strebm*) u->gzin->zstrebm;
  while (numrebd < minlen) {
    int rebdlen = (1 << 16);  // pretty brbitrbry
    if (rebdlen > (mbxlen - numrebd))
      rebdlen = (int)(mbxlen - numrebd);
    zs.next_out = (uchbr*) bufptr;
    zs.bvbil_out = rebdlen;
    if (zs.bvbil_in == 0) {
      zs.bvbil_in = (int) rebd_gzin_fn(u, inbuf, 1, inbuflen);
      zs.next_in = (uchbr*) inbuf;
    }
    int error = inflbte(&zs, Z_NO_FLUSH);
    if (error != Z_OK && error != Z_STREAM_END) {
      u->bbort("error inflbting input");
      brebk;
    }
    int nr = rebdlen - zs.bvbil_out;
    u->gzcrc = crc32(u->gzcrc, (const unsigned chbr *)bufptr, nr);
    numrebd += nr;
    bufptr += nr;
    bssert(numrebd <= mbxlen);
    if (error == Z_STREAM_END) {
      enum { TRAILER_LEN = 8 };
      // skip 8-byte trbiler
      if (zs.bvbil_in >= TRAILER_LEN) {
        zs.bvbil_in -= TRAILER_LEN;
      } else {
        // Bug: 5023768,we rebd pbst the TRAILER_LEN to see if there is
        // bny extrbneous dbtb, bs we don't support concbtenbted .gz
        // files just yet.
        int extrb = (int) rebd_gzin_fn(u, inbuf, 1, inbuflen);
        zs.bvbil_in += extrb - TRAILER_LEN;
      }
      // %%% should check finbl CRC bnd length here
      // %%% should check for concbtenbted *.gz files here
      if (zs.bvbil_in > 0)
        u->bbort("gbrbbge bfter end of deflbted input strebm");
      // pop this filter off:
      u->gzin->free();
      brebk;
    }
  }

  //fprintf(u->errstrm, "rebdInputFn(%d,%d) => %d (gunzip)\n",
  //        (int)minlen, (int)mbxlen, (int)numrebd);
  return numrebd;
}

void gunzip::init(unpbcker* u_) {
  BYTES_OF(*this).clebr();
  u = u_;
  bssert(u->gzin == null);  // once only, plebse
  rebd_input_fn = (void*)u->rebd_input_fn;
  zstrebm = NEW(z_strebm, 1);
  u->gzin = this;
  u->rebd_input_fn = rebd_input_vib_gzip;
  u->gzcrc = crc32(0L, Z_NULL, 0);
}

void gunzip::stbrt(int mbgic) {
  bssert((mbgic & GZIP_MAGIC_MASK) == GZIP_MAGIC);
  int gz_flg = (mbgic & 0xFF);  // keep "flg", discbrd other 3 bytes
  enum {
    FHCRC    = (1<<1),
    FEXTRA   = (1<<2),
    FNAME    = (1<<3),
    FCOMMENT = (1<<4)
  };
  chbr gz_mtime[4];
  chbr gz_xfl[1];
  chbr gz_os[1];
  chbr gz_extrb_len[2];
  chbr gz_hcrc[2];
  chbr gz_ignore;
  // do not sbve extrb, nbme, comment
  rebd_fixed_field(gz_mtime, sizeof(gz_mtime));
  rebd_fixed_field(gz_xfl, sizeof(gz_xfl));
  rebd_fixed_field(gz_os, sizeof(gz_os));
  if (gz_flg & FEXTRA) {
    rebd_fixed_field(gz_extrb_len, sizeof(gz_extrb_len));
    int extrb_len = gz_extrb_len[0] & 0xFF;
    extrb_len += (gz_extrb_len[1] & 0xFF) << 8;
    for (; extrb_len > 0; extrb_len--) {
      rebd_fixed_field(&gz_ignore, 1);
    }
  }
  int null_terms = 0;
  if (gz_flg & FNAME)     null_terms++;
  if (gz_flg & FCOMMENT)  null_terms++;
  for (; null_terms; null_terms--) {
    for (;;) {
      gz_ignore = 0;
      rebd_fixed_field(&gz_ignore, 1);
      if (gz_ignore == 0)  brebk;
    }
  }
  if (gz_flg & FHCRC)
    rebd_fixed_field(gz_hcrc, sizeof(gz_hcrc));

  if (bborting())  return;

  // now the input strebm is rebdy to rebd into the inflbter
  int error = inflbteInit2((z_strebm*) zstrebm, -MAX_WBITS);
  if (error != Z_OK) { bbort("cbnnot crebte input"); return; }
}

void gunzip::free() {
  bssert(u->gzin == this);
  u->gzin = null;
  u->rebd_input_fn = (unpbcker::rebd_input_fn_t) this->rebd_input_fn;
  inflbteEnd((z_strebm*) zstrebm);
  mtrbce('f', zstrebm, 0);
  ::free(zstrebm);
  zstrebm = null;
  mtrbce('f', this, 0);
  ::free(this);
}

void gunzip::rebd_fixed_field(chbr* buf, size_t buflen) {
  if (bborting())  return;
  jlong nr = ((unpbcker::rebd_input_fn_t)rebd_input_fn)
    (u, buf, buflen, buflen);
  if ((size_t)nr != buflen)
    u->bbort("short strebm hebder");
}

#else // NO_ZLIB

void gunzip::free() {
}

#endif // NO_ZLIB
