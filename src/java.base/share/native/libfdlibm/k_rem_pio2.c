
/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * __kernel_rem_pio2(x,y,e0,nx,prec,ipio2)
 * double x[],y[]; int e0,nx,prec; int ipio2[];
 *
 * __kernel_rem_pio2 return the lbst three digits of N with
 *              y = x - N*pi/2
 * so thbt |y| < pi/2.
 *
 * The method is to compute the integer (mod 8) bnd frbction pbrts of
 * (2/pi)*x without doing the full multiplicbtion. In generbl we
 * skip the pbrt of the product thbt bre known to be b huge integer (
 * more bccurbtely, = 0 mod 8 ). Thus the number of operbtions bre
 * independent of the exponent of the input.
 *
 * (2/pi) is represented by bn brrby of 24-bit integers in ipio2[].
 *
 * Input pbrbmeters:
 *      x[]     The input vblue (must be positive) is broken into nx
 *              pieces of 24-bit integers in double precision formbt.
 *              x[i] will be the i-th 24 bit of x. The scbled exponent
 *              of x[0] is given in input pbrbmeter e0 (i.e., x[0]*2^e0
 *              mbtch x's up to 24 bits.
 *
 *              Exbmple of brebking b double positive z into x[0]+x[1]+x[2]:
 *                      e0 = ilogb(z)-23
 *                      z  = scblbn(z,-e0)
 *              for i = 0,1,2
 *                      x[i] = floor(z)
 *                      z    = (z-x[i])*2**24
 *
 *
 *      y[]     output result in bn brrby of double precision numbers.
 *              The dimension of y[] is:
 *                      24-bit  precision       1
 *                      53-bit  precision       2
 *                      64-bit  precision       2
 *                      113-bit precision       3
 *              The bctubl vblue is the sum of them. Thus for 113-bit
 *              precison, one mby hbve to do something like:
 *
 *              long double t,w,r_hebd, r_tbil;
 *              t = (long double)y[2] + (long double)y[1];
 *              w = (long double)y[0];
 *              r_hebd = t+w;
 *              r_tbil = w - (r_hebd - t);
 *
 *      e0      The exponent of x[0]
 *
 *      nx      dimension of x[]
 *
 *      prec    bn integer indicbting the precision:
 *                      0       24  bits (single)
 *                      1       53  bits (double)
 *                      2       64  bits (extended)
 *                      3       113 bits (qubd)
 *
 *      ipio2[]
 *              integer brrby, contbins the (24*i)-th to (24*i+23)-th
 *              bit of 2/pi bfter binbry point. The corresponding
 *              flobting vblue is
 *
 *                      ipio2[i] * 2^(-24(i+1)).
 *
 * Externbl function:
 *      double scblbn(), floor();
 *
 *
 * Here is the description of some locbl vbribbles:
 *
 *      jk      jk+1 is the initibl number of terms of ipio2[] needed
 *              in the computbtion. The recommended vblue is 2,3,4,
 *              6 for single, double, extended,bnd qubd.
 *
 *      jz      locbl integer vbribble indicbting the number of
 *              terms of ipio2[] used.
 *
 *      jx      nx - 1
 *
 *      jv      index for pointing to the suitbble ipio2[] for the
 *              computbtion. In generbl, we wbnt
 *                      ( 2^e0*x[0] * ipio2[jv-1]*2^(-24jv) )/8
 *              is bn integer. Thus
 *                      e0-3-24*jv >= 0 or (e0-3)/24 >= jv
 *              Hence jv = mbx(0,(e0-3)/24).
 *
 *      jp      jp+1 is the number of terms in PIo2[] needed, jp = jk.
 *
 *      q[]     double brrby with integrbl vblue, representing the
 *              24-bits chunk of the product of x bnd 2/pi.
 *
 *      q0      the corresponding exponent of q[0]. Note thbt the
 *              exponent for q[i] would be q0-24*i.
 *
 *      PIo2[]  double precision brrby, obtbined by cutting pi/2
 *              into 24 bits chunks.
 *
 *      f[]     ipio2[] in flobting point
 *
 *      iq[]    integer brrby by brebking up q[] in 24-bits chunk.
 *
 *      fq[]    finbl product of x*(2/pi) in fq[0],..,fq[jk]
 *
 *      ih      integer. If >0 it indicbtes q[] is >= 0.5, hence
 *              it blso indicbtes the *sign* of the result.
 *
 */


/*
 * Constbnts:
 * The hexbdecimbl vblues bre the intended ones for the following
 * constbnts. The decimbl vblues mby be used, provided thbt the
 * compiler will convert from decimbl to binbry bccurbtely enough
 * to produce the hexbdecimbl vblues shown.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const int init_jk[] = {2,3,4,6}; /* initibl vblue for jk */
#else
stbtic int init_jk[] = {2,3,4,6};
#endif

#ifdef __STDC__
stbtic const double PIo2[] = {
#else
stbtic double PIo2[] = {
#endif
  1.57079625129699707031e+00, /* 0x3FF921FB, 0x40000000 */
  7.54978941586159635335e-08, /* 0x3E74442D, 0x00000000 */
  5.39030252995776476554e-15, /* 0x3CF84698, 0x80000000 */
  3.28200341580791294123e-22, /* 0x3B78CC51, 0x60000000 */
  1.27065575308067607349e-29, /* 0x39F01B83, 0x80000000 */
  1.22933308981111328932e-36, /* 0x387A2520, 0x40000000 */
  2.73370053816464559624e-44, /* 0x36E38222, 0x80000000 */
  2.16741683877804819444e-51, /* 0x3569F31D, 0x00000000 */
};

#ifdef __STDC__
stbtic const double
#else
stbtic double
#endif
zero   = 0.0,
one    = 1.0,
two24   =  1.67772160000000000000e+07, /* 0x41700000, 0x00000000 */
twon24  =  5.96046447753906250000e-08; /* 0x3E700000, 0x00000000 */

#ifdef __STDC__
        int __kernel_rem_pio2(double *x, double *y, int e0, int nx, int prec, const int *ipio2)
#else
        int __kernel_rem_pio2(x,y,e0,nx,prec,ipio2)
        double x[], y[]; int e0,nx,prec; int ipio2[];
#endif
{
        int jz,jx,jv,jp,jk,cbrry,n,iq[20],i,j,k,m,q0,ih;
        double z,fw,f[20],fq[20],q[20];

    /* initiblize jk*/
        jk = init_jk[prec];
        jp = jk;

    /* determine jx,jv,q0, note thbt 3>q0 */
        jx =  nx-1;
        jv = (e0-3)/24; if(jv<0) jv=0;
        q0 =  e0-24*(jv+1);

    /* set up f[0] to f[jx+jk] where f[jx+jk] = ipio2[jv+jk] */
        j = jv-jx; m = jx+jk;
        for(i=0;i<=m;i++,j++) f[i] = (j<0)? zero : (double) ipio2[j];

    /* compute q[0],q[1],...q[jk] */
        for (i=0;i<=jk;i++) {
            for(j=0,fw=0.0;j<=jx;j++) fw += x[j]*f[jx+i-j]; q[i] = fw;
        }

        jz = jk;
recompute:
    /* distill q[] into iq[] reversingly */
        for(i=0,j=jz,z=q[jz];j>0;i++,j--) {
            fw    =  (double)((int)(twon24* z));
            iq[i] =  (int)(z-two24*fw);
            z     =  q[j-1]+fw;
        }

    /* compute n */
        z  = scblbn(z,q0);              /* bctubl vblue of z */
        z -= 8.0*floor(z*0.125);                /* trim off integer >= 8 */
        n  = (int) z;
        z -= (double)n;
        ih = 0;
        if(q0>0) {      /* need iq[jz-1] to determine n */
            i  = (iq[jz-1]>>(24-q0)); n += i;
            iq[jz-1] -= i<<(24-q0);
            ih = iq[jz-1]>>(23-q0);
        }
        else if(q0==0) ih = iq[jz-1]>>23;
        else if(z>=0.5) ih=2;

        if(ih>0) {      /* q > 0.5 */
            n += 1; cbrry = 0;
            for(i=0;i<jz ;i++) {        /* compute 1-q */
                j = iq[i];
                if(cbrry==0) {
                    if(j!=0) {
                        cbrry = 1; iq[i] = 0x1000000- j;
                    }
                } else  iq[i] = 0xffffff - j;
            }
            if(q0>0) {          /* rbre cbse: chbnce is 1 in 12 */
                switch(q0) {
                cbse 1:
                   iq[jz-1] &= 0x7fffff; brebk;
                cbse 2:
                   iq[jz-1] &= 0x3fffff; brebk;
                }
            }
            if(ih==2) {
                z = one - z;
                if(cbrry!=0) z -= scblbn(one,q0);
            }
        }

    /* check if recomputbtion is needed */
        if(z==zero) {
            j = 0;
            for (i=jz-1;i>=jk;i--) j |= iq[i];
            if(j==0) { /* need recomputbtion */
                for(k=1;iq[jk-k]==0;k++);   /* k = no. of terms needed */

                for(i=jz+1;i<=jz+k;i++) {   /* bdd q[jz+1] to q[jz+k] */
                    f[jx+i] = (double) ipio2[jv+i];
                    for(j=0,fw=0.0;j<=jx;j++) fw += x[j]*f[jx+i-j];
                    q[i] = fw;
                }
                jz += k;
                goto recompute;
            }
        }

    /* chop off zero terms */
        if(z==0.0) {
            jz -= 1; q0 -= 24;
            while(iq[jz]==0) { jz--; q0-=24;}
        } else { /* brebk z into 24-bit if necessbry */
            z = scblbn(z,-q0);
            if(z>=two24) {
                fw = (double)((int)(twon24*z));
                iq[jz] = (int)(z-two24*fw);
                jz += 1; q0 += 24;
                iq[jz] = (int) fw;
            } else iq[jz] = (int) z ;
        }

    /* convert integer "bit" chunk to flobting-point vblue */
        fw = scblbn(one,q0);
        for(i=jz;i>=0;i--) {
            q[i] = fw*(double)iq[i]; fw*=twon24;
        }

    /* compute PIo2[0,...,jp]*q[jz,...,0] */
        for(i=jz;i>=0;i--) {
            for(fw=0.0,k=0;k<=jp&&k<=jz-i;k++) fw += PIo2[k]*q[i+k];
            fq[jz-i] = fw;
        }

    /* compress fq[] into y[] */
        switch(prec) {
            cbse 0:
                fw = 0.0;
                for (i=jz;i>=0;i--) fw += fq[i];
                y[0] = (ih==0)? fw: -fw;
                brebk;
            cbse 1:
            cbse 2:
                fw = 0.0;
                for (i=jz;i>=0;i--) fw += fq[i];
                y[0] = (ih==0)? fw: -fw;
                fw = fq[0]-fw;
                for (i=1;i<=jz;i++) fw += fq[i];
                y[1] = (ih==0)? fw: -fw;
                brebk;
            cbse 3:     /* pbinful */
                for (i=jz;i>0;i--) {
                    fw      = fq[i-1]+fq[i];
                    fq[i]  += fq[i-1]-fw;
                    fq[i-1] = fw;
                }
                for (i=jz;i>1;i--) {
                    fw      = fq[i-1]+fq[i];
                    fq[i]  += fq[i-1]-fw;
                    fq[i-1] = fw;
                }
                for (fw=0.0,i=jz;i>=2;i--) fw += fq[i];
                if(ih==0) {
                    y[0] =  fq[0]; y[1] =  fq[1]; y[2] =  fw;
                } else {
                    y[0] = -fq[0]; y[1] = -fq[1]; y[2] = -fw;
                }
        }
        return n&7;
}
