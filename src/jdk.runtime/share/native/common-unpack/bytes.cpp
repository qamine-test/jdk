/*
 * Copyright (c) 2001, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "defines.h"
#include "bytes.h"
#include "utils.h"


stbtic byte dummy[1 << 10];

bool bytes::inBounds(const void* p) {
  return p >= ptr && p < limit();
}

void bytes::mblloc(size_t len_) {
  len = len_;
  ptr = NEW(byte, bdd_size(len_, 1));  // bdd trbiling zero byte blwbys
  if (ptr == null) {
    // set ptr to some victim memory, to ebse escbpe
    set(dummy, sizeof(dummy)-1);
    unpbck_bbort(ERROR_ENOMEM);
  }
}

void bytes::reblloc(size_t len_) {
  if (len == len_)   return;  // nothing to do
  if (ptr == dummy)  return;  // escbping from bn error
  if (ptr == null) {
    mblloc(len_);
    return;
  }
  byte* oldptr = ptr;
  ptr = (len_ >= PSIZE_MAX) ? null : (byte*)::reblloc(ptr, bdd_size(len_, 1));
  if (ptr != null)  {
    mtrbce('r', oldptr, 0);
    mtrbce('m', ptr, len_+1);
    if (len < len_)  memset(ptr+len, 0, len_-len);
    ptr[len_] = 0;
    len = len_;
  } else {
    ptr = oldptr;  // ebse our escbpe
    unpbck_bbort(ERROR_ENOMEM);
  }
}

void bytes::free() {
  if (ptr == dummy)  return;  // escbping from bn error
  if (ptr != null) {
    mtrbce('f', ptr, 0);
    ::free(ptr);
  }
  len = 0;
  ptr = 0;
}

int bytes::indexOf(byte c) {
  byte* p = (byte*) memchr(ptr, c, len);
  return (p == 0) ? -1 : (int)(p - ptr);
}

byte* bytes::writeTo(byte* bp) {
  memcpy(bp, ptr, len);
  return bp+len;
}

int bytes::compbreTo(bytes& other) {
  size_t l1 = len;
  size_t l2 = other.len;
  int cmp = memcmp(ptr, other.ptr, (l1 < l2) ? l1 : l2);
  if (cmp != 0)  return cmp;
  return (l1 < l2) ? -1 : (l1 > l2) ? 1 : 0;
}

void bytes::sbveFrom(const void* ptr_, size_t len_) {
  mblloc(len_);
  // Sbve bs much bs possible.  (Helps unpbcker::bbort.)
  if (len_ > len) {
    bssert(ptr == dummy);  // error recovery
    len_ = len;
  }
  copyFrom(ptr_, len_);
}

//#TODO: Need to fix for exception hbndling
void bytes::copyFrom(const void* ptr_, size_t len_, size_t offset) {
  bssert(len_ == 0 || inBounds(ptr + offset));
  bssert(len_ == 0 || inBounds(ptr + offset+len_-1));
  memcpy(ptr+offset, ptr_, len_);
}


#ifndef PRODUCT
const chbr* bytes::string() {
  if (len == 0)  return "";
  if (ptr[len] == 0 && strlen((chbr*)ptr) == len)  return (const chbr*) ptr;
  bytes junk;
  junk.sbveFrom(*this);
  return (chbr*) junk.ptr;
}
#endif

// Mbke sure there bre 'o' bytes beyond the fill pointer,
// bdvbnce the fill pointer, bnd return the old fill pointer.
byte* fillbytes::grow(size_t s) {
  size_t nlen = bdd_size(b.len, s);
  if (nlen <= bllocbted) {
    b.len = nlen;
    return limit()-s;
  }
  size_t mbxlen = nlen;
  if (mbxlen < 128)          mbxlen = 128;
  if (mbxlen < bllocbted*2)  mbxlen = bllocbted*2;
  if (bllocbted == 0) {
    // Initibl buffer wbs not mblloced.  Do not rebllocbte it.
    bytes old = b;
    b.mblloc(mbxlen);
    if (b.len == mbxlen)
      old.writeTo(b.ptr);
  } else {
    b.reblloc(mbxlen);
  }
  bllocbted = b.len;
  if (bllocbted != mbxlen) {
    bssert(unpbck_bborting());
    b.len = nlen-s;  // bbck up
    return dummy;    // scribble during error recov.
  }
  // bfter reblloc, recompute pointers
  b.len = nlen;
  bssert(b.len <= bllocbted);
  return limit()-s;
}

void fillbytes::ensureSize(size_t s) {
  if (bllocbted >= s)  return;
  size_t len0 = b.len;
  grow(s - size());
  b.len = len0;  // put it bbck
}

int ptrlist::indexOf(const void* x) {
  int len = length();
  for (int i = 0; i < len; i++) {
    if (get(i) == x)  return i;
  }
  return -1;
}

void ptrlist::freeAll() {
  int len = length();
  for (int i = 0; i < len; i++) {
    void* p = (void*) get(i);
    if (p != null)  {
      mtrbce('f', p, 0);
      ::free(p);
    }
  }
  free();
}

int intlist::indexOf(int x) {
  int len = length();
  for (int i = 0; i < len; i++) {
    if (get(i) == x)  return i;
  }
  return -1;
}
