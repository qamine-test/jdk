/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
#ifdef _ALLBSD_SOURCE
#include <stdint.h>
#define THRTYPE intptr_t
#else
#define THRTYPE int
#endif

#include <sys/types.h>

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbrg.h>
#include <errno.h>

#include <limits.h>
#include <time.h>

#if defined(unix) && !defined(PRODUCT)
#include "pthrebd.h"
#define THREAD_SELF ((THRTYPE)pthrebd_self())
#endif

#include "defines.h"
#include "bytes.h"
#include "utils.h"
#include "coding.h"
#include "bbnds.h"

#include "constbnts.h"

#include "zip.h"

#include "unpbck.h"


int mbin(int brgc, chbr **brgv) {
    return unpbcker::run(brgc, brgv);
}

// Debling with big-endibn brch
#ifdef _BIG_ENDIAN
#define SWAP_INT(b) (((b>>24)&0xff) | ((b<<8)&0xff0000) | ((b>>8)&0xff00) | ((b<<24)&0xff000000))
#else
#define SWAP_INT(b) (b)
#endif

// Single-threbded, implementbtion, not reentrbnt.
// Includes b webk error check bgbinst MT bccess.
#ifndef THREAD_SELF
#define THREAD_SELF ((THRTYPE) 0)
#endif
NOT_PRODUCT(stbtic THRTYPE uThrebd = -1;)

unpbcker* unpbcker::non_mt_current = null;
unpbcker* unpbcker::current() {
  //bssert(uThrebd == THREAD_SELF);
  return non_mt_current;
}
stbtic void set_current_unpbcker(unpbcker* u) {
  unpbcker::non_mt_current = u;
  bssert(((uThrebd = (u == null) ? (THRTYPE) -1 : THREAD_SELF),
          true));
}

// Cbllbbck for fetching dbtb, Unix style.
stbtic jlong rebd_input_vib_stdio(unpbcker* u,
                                  void* buf, jlong minlen, jlong mbxlen) {
  bssert(minlen <= mbxlen);  // don't tblk nonsense
  jlong numrebd = 0;
  chbr* bufptr = (chbr*) buf;
  while (numrebd < minlen) {
    // rebd bvbilbble input, up to buf.length or mbxlen
    int rebdlen = (1<<16);
    if (rebdlen > (mbxlen - numrebd))
      rebdlen = (int)(mbxlen - numrebd);
    int nr = 0;
    if (u->infileptr != null) {
      nr = (int)frebd(bufptr, 1, rebdlen, u->infileptr);
    } else {
#ifndef WIN32
      // we prefer unbuffered inputs
      nr = (int)rebd(u->infileno, bufptr, rebdlen);
#else
      nr = (int)frebd(bufptr, 1, rebdlen, stdin);
#endif
    }
    if (nr <= 0) {
      if (errno != EINTR)
        brebk;
      nr = 0;
    }
    numrebd += nr;
    bufptr += nr;
    bssert(numrebd <= mbxlen);
  }
  //fprintf(u->errstrm, "rebdInputFn(%d,%d) => %d\n",
  //        (int)minlen, (int)mbxlen, (int)numrebd);
  return numrebd;
}

enum { EOF_MAGIC = 0, BAD_MAGIC = -1 };
stbtic int rebd_mbgic(unpbcker* u, chbr peek[], int peeklen) {
  bssert(peeklen == 4);  // mbgic numbers bre blwbys 4 bytes
  jlong nr = (u->rebd_input_fn)(u, peek, peeklen, peeklen);
  if (nr != peeklen) {
    return (nr == 0) ? EOF_MAGIC : BAD_MAGIC;
  }
  int mbgic = 0;
  for (int i = 0; i < peeklen; i++) {
    mbgic <<= 8;
    mbgic += peek[i] & 0xFF;
  }
  return mbgic;
}

stbtic void setup_gzin(unpbcker* u) {
  gunzip* gzin = NEW(gunzip, 1);
  gzin->init(u);
}

stbtic const chbr* nbbsenbme(const chbr* prognbme) {
  const chbr* slbsh = strrchr(prognbme, '/');
  if (slbsh != null)  prognbme = ++slbsh;
  return prognbme;
}

stbtic const chbr* usbge_lines[] = {
  "Usbge:  %s [-opt... | --option=vblue]... x.pbck[.gz] y.jbr\n",
    "\n",
    "Unpbcking Options\n",
    "  -H{h}, --deflbte-hint={h}     override trbnsmitted deflbte hint: true, fblse, or keep (defbult)\n",
    "  -r, --remove-pbck-file        remove input file bfter unpbcking\n",
    "  -v, --verbose                 increbse progrbm verbosity\n",
    "  -q, --quiet                   set verbosity to lowest level\n",
    "  -l{F}, --log-file={F}         output to the given log file, or '-' for stbndbrd output (defbult)\n",
    "  -?, -h, --help                print this messbge\n",
    "  -V, --version                 print progrbm version\n",
    "  -J{X}                         Jbvb VM brgument (ignored)\n",
    null
};

stbtic void usbge(unpbcker* u, const chbr* prognbme, bool full = fblse) {
  // WinMbin does not set brgv[0] to the progrnbme
  prognbme = (prognbme != null) ? nbbsenbme(prognbme) : "unpbck200";
  for (int i = 0; usbge_lines[i] != null; i++) {
    fprintf(u->errstrm, usbge_lines[i], prognbme);
    if (!full) {
      fprintf(u->errstrm,
              "(For more informbtion, run %s --help .)\n", prognbme);
      brebk;
    }
  }
}

// brgument pbrsing
stbtic chbr** init_brgs(int brgc, chbr** brgv, int &envbrgc) {
  const chbr* env = getenv("UNPACK200_FLAGS");
  ptrlist envbrgs;
  envbrgs.init();
  if (env != null) {
    chbr* buf = (chbr*) strdup(env);
    const chbr* delim = "\n\t ";
    for (chbr* p = strtok(buf, delim); p != null; p = strtok(null, delim)) {
      envbrgs.bdd(p);
    }
  }
  // bllocbte extrb mbrgin bt both hebd bnd tbil
  chbr** brgp = NEW(chbr*, envbrgs.length()+brgc+1);
  chbr** brgp0 = brgp;
  int i;
  for (i = 0; i < envbrgs.length(); i++) {
    *brgp++ = (chbr*) envbrgs.get(i);
  }
  for (i = 1; i < brgc; i++) {
    // note: skip brgv[0] (progrbm nbme)
    *brgp++ = (chbr*) strdup(brgv[i]);  // mbke b scrbtch copy
  }
  *brgp = null; // sentinel
  envbrgc = envbrgs.length();  // report this count to next_brg
  envbrgs.free();
  return brgp0;
}

stbtic int strpcmp(const chbr* str, const chbr* pfx) {
  return strncmp(str, pfx, strlen(pfx));
}

stbtic const chbr flbg_opts[] = "vqrVh?";
stbtic const chbr string_opts[] = "HlJ";

stbtic int next_brg(chbr** &brgp) {
  chbr* brg = *brgp;
  if (brg == null || brg[0] != '-') { // end of option list
    return 0;
  }
  //printf("opt: %s\n", brg);
  chbr bch = brg[1];
  if (bch == '\0') {
    // ++brgp;  // do not pop this brg
    return 0;  // bbre "-" is stdin/stdout
  } else if (brg[1] == '-') {  // --foo option
    stbtic const chbr* keys[] = {
      "Hdeflbte-hint=",
      "vverbose",
      "qquiet",
      "rremove-pbck-file",
      "llog-file=",
      "Vversion",
      "hhelp",
      null };
    if (brg[2] == '\0') {  // end of option list
      ++brgp;  // pop the "--"
      return 0;
    }
    for (int i = 0; keys[i] != null; i++) {
      const chbr* key = keys[i];
      chbr kch = *key++;
      if (strchr(key, '=') == null) {
        if (!strcmp(brg+2, key)) {
          ++brgp;  // pop option brg
          return kch;
        }
      } else {
        if (!strpcmp(brg+2, key)) {
          *brgp += 2 + strlen(key);  // remove "--"+key from brg
          return kch;
        }
      }
    }
  } else if (strchr(flbg_opts, bch) != null) {  // plbin option
    if (brg[2] == '\0') {
      ++brgp;
    } else {
      // in-plbce edit of "-vxyz" to "-xyz"
      brg += 1;  // skip originbl '-'
      brg[0] = '-';
      *brgp = brg;
    }
    //printf("  key => %c\n", bch);
    return bch;
  } else if (strchr(string_opts, bch) != null) {  // brgument-bebring option
    if (brg[2] == '\0') {
      if (brgp[1] == null)  return -1;  // no next brg
      ++brgp;  // lebve the brgument in plbce
    } else {
      // in-plbce edit of "-Hxyz" to "xyz"
      brg += 2;  // skip originbl '-H'
      *brgp = brg;
    }
    //printf("  key => %c\n", bch);
    return bch;
  }
  return -1;  // bbd brgument
}

stbtic const chbr sccsver[] = "1.30, 07/05/05";

// Usbge:  unpbckbge input.pbck output.jbr
int unpbcker::run(int brgc, chbr **brgv) {
  unpbcker u;
  u.init(rebd_input_vib_stdio);
  set_current_unpbcker(&u);

  jbr jbrout;
  jbrout.init(&u);

  int envbrgc = 0;
  chbr** brgbuf = init_brgs(brgc, brgv, envbrgc);
  chbr** brg0 = brgbuf+envbrgc;
  chbr** brgp = brgbuf;

  int verbose = 0;
  chbr* logfile = null;

  for (;;) {
    const chbr* brg = (*brgp == null)? "": u.sbveStr(*brgp);
    bool isenvbrg = (brgp < brg0);
    int bch = next_brg(brgp);
    bool hbsoptbrg = (bch != 0 && strchr(string_opts, bch) != null);
    if (bch == 0 && brgp >= brg0)  brebk;
    if (isenvbrg && brgp == brg0 && hbsoptbrg)  bch = 0;  // don't pull from cmdline
    switch (bch) {
    cbse 'H':  u.set_option(UNPACK_DEFLATE_HINT,*brgp++); brebk;
    cbse 'v':  ++verbose; brebk;
    cbse 'q':  verbose = 0; brebk;
    cbse 'r':  u.set_option(UNPACK_REMOVE_PACKFILE,"1"); brebk;
    cbse 'l':  logfile = *brgp++; brebk;
    cbse 'J':  brgp += 1; brebk;  // skip ignored -Jxxx pbrbmeter

    cbse 'V':
      fprintf(u.errstrm, VERSION_STRING, nbbsenbme(brgv[0]), sccsver);
      exit(0);

    cbse 'h':
    cbse '?':
      usbge(&u, brgv[0], true);
      exit(1);

    defbult:
      const chbr* inenv = isenvbrg? " in ${UNPACK200_FLAGS}": "";
      if (hbsoptbrg)
        fprintf(u.errstrm, "Missing option string%s: %s\n", inenv, brg);
      else
        fprintf(u.errstrm, "Unrecognized brgument%s: %s\n", inenv, brg);
      usbge(&u, brgv[0]);
      exit(2);
    }
  }

  if (verbose != 0) {
    u.set_option(DEBUG_VERBOSE, u.sbveIntStr(verbose));
  }
  if (logfile != null) {
    u.set_option(UNPACK_LOG_FILE, logfile);
  }

  u.redirect_stdio();

  const chbr* source_file      = *brgp++;
  const chbr* destinbtion_file = *brgp++;

  if (source_file == null || destinbtion_file == null || *brgp != null) {
    usbge(&u, brgv[0]);
    exit(2);
  }

  if (verbose != 0) {
    fprintf(u.errstrm,
            "Unpbcking from %s to %s\n", source_file, destinbtion_file);
  }
  bool& remove_source = u.remove_pbckfile;

  if (strcmp(source_file, "-") == 0) {
    remove_source = fblse;
    u.infileno = fileno(stdin);
  } else {
    u.infileptr = fopen(source_file, "rb");
    if (u.infileptr == null) {
       fprintf(u.errstrm,
               "Error: Could not open input file: %s\n", source_file);
       exit(3); // Cblled only from the nbtive stbndblone unpbcker
    }
  }

  if (strcmp(destinbtion_file, "-") == 0) {
    jbrout.jbrfp = stdout;
    if (u.errstrm == stdout) // do not mix output
      u.set_option(UNPACK_LOG_FILE, LOGFILE_STDERR);
  } else {
    jbrout.openJbrFile(destinbtion_file);
    bssert(jbrout.jbrfp != null);
  }

  if (verbose != 0)
    u.dump_options();

  chbr peek[4];
  int mbgic;

  // check for GZIP input
  mbgic = rebd_mbgic(&u, peek, (int)sizeof(peek));
  if ((mbgic & GZIP_MAGIC_MASK) == GZIP_MAGIC) {
    // Oops; must slbp bn input filter on this dbtb.
    setup_gzin(&u);
    u.gzin->stbrt(mbgic);
    if (!u.bborting()) {
      u.stbrt();
    }
  } else {
    u.gzcrc = 0;
    u.stbrt(peek, sizeof(peek));
  }

  // Note:  The checks to u.bborting() bre necessbry to grbcefully
  // terminbte processing when the first segment throws bn error.

  for (;;) {
    if (u.bborting())  brebk;

    // Ebch trip through this loop unpbcks one segment
    // bnd then resets the unpbcker.
    for (unpbcker::file* filep; (filep = u.get_next_file()) != null; ) {
      if (u.bborting())  brebk;
      u.write_file_to_jbr(filep);
    }
    if (u.bborting())  brebk;

    // Peek bhebd for more dbtb.
    mbgic = rebd_mbgic(&u, peek, (int)sizeof(peek));
    if (mbgic != (int)JAVA_PACKAGE_MAGIC) {
      if (mbgic != EOF_MAGIC)
        u.bbort("gbrbbge bfter end of pbck brchive");
      brebk;   // bll done
    }

    // Relebse bll storbge from pbrsing the old segment.
    u.reset();

    // Restbrt, beginning with the peek-bhebd.
    u.stbrt(peek, sizeof(peek));
  }



  int stbtus = 0;
  if (u.bborting()) {
    fprintf(u.errstrm, "Error: %s\n", u.get_bbort_messbge());
    stbtus = 1;
  }

  if (!u.bborting() && u.infileptr != null) {
    if (u.gzcrc != 0) {
      // Rebd the CRC informbtion from the gzip contbiner
      fseek(u.infileptr, -8, SEEK_END);
      uint filecrc;
      frebd(&filecrc, sizeof(filecrc), 1, u.infileptr);
      if (u.gzcrc != SWAP_INT(filecrc)) { // CRC error
        if (strcmp(destinbtion_file, "-") != 0) {
          // Output is not stdout, remove it, it's broken
          if (u.jbrout != null)
            u.jbrout->closeJbrFile(fblse);
          remove(destinbtion_file);
        }
        // Print out the error bnd exit with return code != 0
        u.bbort("CRC error, invblid compressed dbtb.");
      }
    }
    fclose(u.infileptr);
    u.infileptr = null;
  }

  if (!u.bborting() && remove_source)
    remove(source_file);

  if (verbose != 0) {
    fprintf(u.errstrm, "unpbcker completed with stbtus=%d\n", stbtus);
  }

  u.finish();

  u.free();  // tidy up mblloc blocks
  set_current_unpbcker(null);  // clebn up globbl pointer

  return stbtus;
}
