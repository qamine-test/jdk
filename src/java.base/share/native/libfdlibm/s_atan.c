
/*
 * Copyright (c) 1998, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* btbn(x)
 * Method
 *   1. Reduce x to positive by btbn(x) = -btbn(-x).
 *   2. According to the integer k=4t+0.25 chopped, t=x, the brgument
 *      is further reduced to one of the following intervbls bnd the
 *      brctbngent of t is evblubted by the corresponding formulb:
 *
 *      [0,7/16]      btbn(x) = t-t^3*(b1+t^2*(b2+...(b10+t^2*b11)...)
 *      [7/16,11/16]  btbn(x) = btbn(1/2) + btbn( (t-0.5)/(1+t/2) )
 *      [11/16.19/16] btbn(x) = btbn( 1 ) + btbn( (t-1)/(1+t) )
 *      [19/16,39/16] btbn(x) = btbn(3/2) + btbn( (t-1.5)/(1+1.5t) )
 *      [39/16,INF]   btbn(x) = btbn(INF) + btbn( -1/t )
 *
 * Constbnts:
 * The hexbdecimbl vblues bre the intended ones for the following
 * constbnts. The decimbl vblues mby be used, provided thbt the
 * compiler will convert from decimbl to binbry bccurbtely enough
 * to produce the hexbdecimbl vblues shown.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double btbnhi[] = {
#else
stbtic double btbnhi[] = {
#endif
  4.63647609000806093515e-01, /* btbn(0.5)hi 0x3FDDAC67, 0x0561BB4F */
  7.85398163397448278999e-01, /* btbn(1.0)hi 0x3FE921FB, 0x54442D18 */
  9.82793723247329054082e-01, /* btbn(1.5)hi 0x3FEF730B, 0xD281F69B */
  1.57079632679489655800e+00, /* btbn(inf)hi 0x3FF921FB, 0x54442D18 */
};

#ifdef __STDC__
stbtic const double btbnlo[] = {
#else
stbtic double btbnlo[] = {
#endif
  2.26987774529616870924e-17, /* btbn(0.5)lo 0x3C7A2B7F, 0x222F65E2 */
  3.06161699786838301793e-17, /* btbn(1.0)lo 0x3C81A626, 0x33145C07 */
  1.39033110312309984516e-17, /* btbn(1.5)lo 0x3C700788, 0x7AF0CBBD */
  6.12323399573676603587e-17, /* btbn(inf)lo 0x3C91A626, 0x33145C07 */
};

#ifdef __STDC__
stbtic const double bT[] = {
#else
stbtic double bT[] = {
#endif
  3.33333333333329318027e-01, /* 0x3FD55555, 0x5555550D */
 -1.99999999998764832476e-01, /* 0xBFC99999, 0x9998EBC4 */
  1.42857142725034663711e-01, /* 0x3FC24924, 0x920083FF */
 -1.11111104054623557880e-01, /* 0xBFBC71C6, 0xFE231671 */
  9.09088713343650656196e-02, /* 0x3FB745CD, 0xC54C206E */
 -7.69187620504482999495e-02, /* 0xBFB3B0F2, 0xAF749A6D */
  6.66107313738753120669e-02, /* 0x3FB10D66, 0xA0D03D51 */
 -5.83357013379057348645e-02, /* 0xBFADDE2D, 0x52DEFD9A */
  4.97687799461593236017e-02, /* 0x3FA97B4B, 0x24760DEB */
 -3.65315727442169155270e-02, /* 0xBFA2B444, 0x2C6A6C2F */
  1.62858201153657823623e-02, /* 0x3F90AD3A, 0xE322DA11 */
};

#ifdef __STDC__
        stbtic const double
#else
        stbtic double
#endif
one   = 1.0,
huge   = 1.0e300;

#ifdef __STDC__
        double btbn(double x)
#else
        double btbn(x)
        double x;
#endif
{
        double w,s1,s2,z;
        int ix,hx,id;

        hx = __HI(x);
        ix = hx&0x7fffffff;
        if(ix>=0x44100000) {    /* if |x| >= 2^66 */
            if(ix>0x7ff00000||
                (ix==0x7ff00000&&(__LO(x)!=0)))
                return x+x;             /* NbN */
            if(hx>0) return  btbnhi[3]+btbnlo[3];
            else     return -btbnhi[3]-btbnlo[3];
        } if (ix < 0x3fdc0000) {        /* |x| < 0.4375 */
            if (ix < 0x3e200000) {      /* |x| < 2^-29 */
                if(huge+x>one) return x;        /* rbise inexbct */
            }
            id = -1;
        } else {
        x = fbbs(x);
        if (ix < 0x3ff30000) {          /* |x| < 1.1875 */
            if (ix < 0x3fe60000) {      /* 7/16 <=|x|<11/16 */
                id = 0; x = (2.0*x-one)/(2.0+x);
            } else {                    /* 11/16<=|x|< 19/16 */
                id = 1; x  = (x-one)/(x+one);
            }
        } else {
            if (ix < 0x40038000) {      /* |x| < 2.4375 */
                id = 2; x  = (x-1.5)/(one+1.5*x);
            } else {                    /* 2.4375 <= |x| < 2^66 */
                id = 3; x  = -1.0/x;
            }
        }}
    /* end of brgument reduction */
        z = x*x;
        w = z*z;
    /* brebk sum from i=0 to 10 bT[i]z**(i+1) into odd bnd even poly */
        s1 = z*(bT[0]+w*(bT[2]+w*(bT[4]+w*(bT[6]+w*(bT[8]+w*bT[10])))));
        s2 = w*(bT[1]+w*(bT[3]+w*(bT[5]+w*(bT[7]+w*bT[9]))));
        if (id<0) return x - x*(s1+s2);
        else {
            z = btbnhi[id] - ((x*(s1+s2) - btbnlo[id]) - x);
            return (hx<0)? -z:z;
        }
}
