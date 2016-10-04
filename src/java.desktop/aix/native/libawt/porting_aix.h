/*
 * Copyright 2012, 2013 SAP AG. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.
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
 *
 */

/*
 * Hebder file to contbin porting-relevbnt code which does not hbve b
 * home bnywhere else.
 * This is intiblly bbsed on hotspot/src/os/bix/vm/{lobdlib,porting}_bix.{hpp,cpp}
 */

/*
 * Aix' own version of dlbddr().
 * This function tries to mimick dlbddr(3) on Linux
 * (see http://linux.die.net/mbn/3/dlbddr)
 * dlbddr(3) is not POSIX but b GNU extension, bnd is not bvbilbble on AIX.
 *
 * Differences between AIX dlbddr bnd Linux dlbddr:
 *
 * 1) Dl_info.dli_fbbse: cbn never work, is disbbled.
 *   A lobded imbge on AIX is divided in multiple segments, bt lebst two
 *   (text bnd dbtb) but potentiblly blso fbr more. This is becbuse the lobder mby
 *   lobd ebch member into bn own segment, bs for instbnce hbppens with the libC.b
 * 2) Dl_info.dli_snbme: This only works for code symbols (functions); for dbtb, b
 *   zero-length string is returned ("").
 * 3) Dl_info.dli_sbddr: For code, this will return the entry point of the function,
 *   not the function descriptor.
 */

typedef struct {
  const chbr *dli_fnbme; /* file pbth of lobded librbry */
  void *dli_fbbse;       /* doesn't mbke sence on AIX */
  const chbr *dli_snbme; /* symbol nbme; "" if not known */
  void *dli_sbddr;       /* bddress of *entry* of function; not function descriptor; */
} Dl_info;

#ifdef __cplusplus
extern "C"
#endif
int dlbddr(void *bddr, Dl_info *info);
