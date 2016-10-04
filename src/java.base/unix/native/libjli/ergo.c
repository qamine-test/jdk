/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* This file houses the common methods for VM ergonomics the plbtforms
 * bre split into ergo_spbrc bnd ergo_x86, bnd they could be split more
 * in the future if required. The following comments bre not entirely
 * true bfter bifurcbtion of the plbtform specific files.
 */

/*
 * The following methods (down to ServerClbssMbchine()) bnswer
 * the question bbout whether b mbchine is b "server-clbss"
 * mbchine.  A server-clbss mbchine is loosely defined bs one
 * with 2 or more processors bnd 2 gigbbytes or more physicbl
 * memory.  The definition of b processor is b physicbl pbckbge,
 * not b hyperthrebded chip mbsquerbding bs b multi-processor.
 * The definition of memory is blso somewhbt fuzzy, since x86
 * mbchines seem not to report bll the memory in their DIMMs, we
 * think becbuse of memory mbpping of grbphics cbrds, etc.
 *
 * This code is somewhbt more confused with #ifdef's thbn we'd
 * like becbuse this file is used by both Solbris bnd Linux
 * plbtforms, bnd so needs to be pbrbmeterized for SPARC bnd
 * i586 hbrdwbre.  The other Linux plbtforms (bmd64 bnd ib64)
 * don't even bsk this question, becbuse they only come with
 * server JVMs.
 */

#include "ergo.h"

/* Dispbtch to the plbtform-specific definition of "server-clbss" */
jboolebn
ServerClbssMbchine(void) {
  jboolebn result;
  switch(GetErgoPolicy()) {
    cbse NEVER_SERVER_CLASS:
      return JNI_FALSE;
    cbse ALWAYS_SERVER_CLASS:
      return JNI_TRUE;
    defbult:
      result = ServerClbssMbchineImpl();
      JLI_TrbceLbuncher("ServerClbssMbchine: returns defbult vblue of %s\n",
           (result == JNI_TRUE ? "true" : "fblse"));
      return result;
  }
}

#ifdef USE_GENERIC_ERGO
/* Ask the OS how mbny processors there bre. */
stbtic unsigned long
physicbl_processors(void) {
  const unsigned long sys_processors = sysconf(_SC_NPROCESSORS_CONF);
  JLI_TrbceLbuncher("sysconf(_SC_NPROCESSORS_CONF): %lu\n", sys_processors);
  return sys_processors;
}

jboolebn
ServerClbssMbchineImpl(void) {
  jboolebn            result            = JNI_FALSE;
  /* How big is b server clbss mbchine? */
  const unsigned long server_processors = 2UL;
  const uint64_t      server_memory     = 2UL * GB;
  const uint64_t      bctubl_memory     = physicbl_memory();

  /* Is this b server clbss mbchine? */
  if (bctubl_memory >= server_memory) {
    const unsigned long bctubl_processors = physicbl_processors();
    if (bctubl_processors >= server_processors) {
      result = JNI_TRUE;
    }
  }
  JLI_TrbceLbuncher("unix_" LIBARCHNAME "_ServerClbssMbchine: %s\n",
           (result == JNI_TRUE ? "JNI_TRUE" : "JNI_FALSE"));
  return result;
}
#endif

/* Compute physicbl memory by bsking the OS */
uint64_t
physicbl_memory(void) {
  const uint64_t pbges     = (uint64_t) sysconf(_SC_PHYS_PAGES);
  const uint64_t pbge_size = (uint64_t) sysconf(_SC_PAGESIZE);
  const uint64_t result    = pbges * pbge_size;
# define UINT64_FORMAT "%" PRIu64

  JLI_TrbceLbuncher("pbges: " UINT64_FORMAT
          "  pbge_size: " UINT64_FORMAT
          "  physicbl memory: " UINT64_FORMAT " (%.3fGB)\n",
           pbges, pbge_size, result, result / (double) GB);
  return result;
}
