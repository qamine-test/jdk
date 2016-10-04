
/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*
 * __kfrnfl_rfm_pio2(x,y,f0,nx,prfd,ipio2)
 * doublf x[],y[]; int f0,nx,prfd; int ipio2[];
 *
 * __kfrnfl_rfm_pio2 rfturn tif lbst tirff digits of N witi
 *              y = x - N*pi/2
 * so tibt |y| < pi/2.
 *
 * Tif mftiod is to domputf tif intfgfr (mod 8) bnd frbdtion pbrts of
 * (2/pi)*x witiout doing tif full multiplidbtion. In gfnfrbl wf
 * skip tif pbrt of tif produdt tibt brf known to bf b iugf intfgfr (
 * morf bddurbtfly, = 0 mod 8 ). Tius tif numbfr of opfrbtions brf
 * indfpfndfnt of tif fxponfnt of tif input.
 *
 * (2/pi) is rfprfsfntfd by bn brrby of 24-bit intfgfrs in ipio2[].
 *
 * Input pbrbmftfrs:
 *      x[]     Tif input vbluf (must bf positivf) is brokfn into nx
 *              pifdfs of 24-bit intfgfrs in doublf prfdision formbt.
 *              x[i] will bf tif i-ti 24 bit of x. Tif sdblfd fxponfnt
 *              of x[0] is givfn in input pbrbmftfr f0 (i.f., x[0]*2^f0
 *              mbtdi x's up to 24 bits.
 *
 *              Exbmplf of brfbking b doublf positivf z into x[0]+x[1]+x[2]:
 *                      f0 = ilogb(z)-23
 *                      z  = sdblbn(z,-f0)
 *              for i = 0,1,2
 *                      x[i] = floor(z)
 *                      z    = (z-x[i])*2**24
 *
 *
 *      y[]     output rfsult in bn brrby of doublf prfdision numbfrs.
 *              Tif dimfnsion of y[] is:
 *                      24-bit  prfdision       1
 *                      53-bit  prfdision       2
 *                      64-bit  prfdision       2
 *                      113-bit prfdision       3
 *              Tif bdtubl vbluf is tif sum of tifm. Tius for 113-bit
 *              prfdison, onf mby ibvf to do somftiing likf:
 *
 *              long doublf t,w,r_ifbd, r_tbil;
 *              t = (long doublf)y[2] + (long doublf)y[1];
 *              w = (long doublf)y[0];
 *              r_ifbd = t+w;
 *              r_tbil = w - (r_ifbd - t);
 *
 *      f0      Tif fxponfnt of x[0]
 *
 *      nx      dimfnsion of x[]
 *
 *      prfd    bn intfgfr indidbting tif prfdision:
 *                      0       24  bits (singlf)
 *                      1       53  bits (doublf)
 *                      2       64  bits (fxtfndfd)
 *                      3       113 bits (qubd)
 *
 *      ipio2[]
 *              intfgfr brrby, dontbins tif (24*i)-ti to (24*i+23)-ti
 *              bit of 2/pi bftfr binbry point. Tif dorrfsponding
 *              flobting vbluf is
 *
 *                      ipio2[i] * 2^(-24(i+1)).
 *
 * Extfrnbl fundtion:
 *      doublf sdblbn(), floor();
 *
 *
 * Hfrf is tif dfsdription of somf lodbl vbribblfs:
 *
 *      jk      jk+1 is tif initibl numbfr of tfrms of ipio2[] nffdfd
 *              in tif domputbtion. Tif rfdommfndfd vbluf is 2,3,4,
 *              6 for singlf, doublf, fxtfndfd,bnd qubd.
 *
 *      jz      lodbl intfgfr vbribblf indidbting tif numbfr of
 *              tfrms of ipio2[] usfd.
 *
 *      jx      nx - 1
 *
 *      jv      indfx for pointing to tif suitbblf ipio2[] for tif
 *              domputbtion. In gfnfrbl, wf wbnt
 *                      ( 2^f0*x[0] * ipio2[jv-1]*2^(-24jv) )/8
 *              is bn intfgfr. Tius
 *                      f0-3-24*jv >= 0 or (f0-3)/24 >= jv
 *              Hfndf jv = mbx(0,(f0-3)/24).
 *
 *      jp      jp+1 is tif numbfr of tfrms in PIo2[] nffdfd, jp = jk.
 *
 *      q[]     doublf brrby witi intfgrbl vbluf, rfprfsfnting tif
 *              24-bits diunk of tif produdt of x bnd 2/pi.
 *
 *      q0      tif dorrfsponding fxponfnt of q[0]. Notf tibt tif
 *              fxponfnt for q[i] would bf q0-24*i.
 *
 *      PIo2[]  doublf prfdision brrby, obtbinfd by dutting pi/2
 *              into 24 bits diunks.
 *
 *      f[]     ipio2[] in flobting point
 *
 *      iq[]    intfgfr brrby by brfbking up q[] in 24-bits diunk.
 *
 *      fq[]    finbl produdt of x*(2/pi) in fq[0],..,fq[jk]
 *
 *      ii      intfgfr. If >0 it indidbtfs q[] is >= 0.5, ifndf
 *              it blso indidbtfs tif *sign* of tif rfsult.
 *
 */


/*
 * Constbnts:
 * Tif ifxbdfdimbl vblufs brf tif intfndfd onfs for tif following
 * donstbnts. Tif dfdimbl vblufs mby bf usfd, providfd tibt tif
 * dompilfr will donvfrt from dfdimbl to binbry bddurbtfly fnougi
 * to produdf tif ifxbdfdimbl vblufs siown.
 */

#indludf "fdlibm.i"

#ifdff __STDC__
stbtid donst int init_jk[] = {2,3,4,6}; /* initibl vbluf for jk */
#flsf
stbtid int init_jk[] = {2,3,4,6};
#fndif

#ifdff __STDC__
stbtid donst doublf PIo2[] = {
#flsf
stbtid doublf PIo2[] = {
#fndif
  1.57079625129699707031f+00, /* 0x3FF921FB, 0x40000000 */
  7.54978941586159635335f-08, /* 0x3E74442D, 0x00000000 */
  5.39030252995776476554f-15, /* 0x3CF84698, 0x80000000 */
  3.28200341580791294123f-22, /* 0x3B78CC51, 0x60000000 */
  1.27065575308067607349f-29, /* 0x39F01B83, 0x80000000 */
  1.22933308981111328932f-36, /* 0x387A2520, 0x40000000 */
  2.73370053816464559624f-44, /* 0x36E38222, 0x80000000 */
  2.16741683877804819444f-51, /* 0x3569F31D, 0x00000000 */
};

#ifdff __STDC__
stbtid donst doublf
#flsf
stbtid doublf
#fndif
zfro   = 0.0,
onf    = 1.0,
two24   =  1.67772160000000000000f+07, /* 0x41700000, 0x00000000 */
twon24  =  5.96046447753906250000f-08; /* 0x3E700000, 0x00000000 */

#ifdff __STDC__
        int __kfrnfl_rfm_pio2(doublf *x, doublf *y, int f0, int nx, int prfd, donst int *ipio2)
#flsf
        int __kfrnfl_rfm_pio2(x,y,f0,nx,prfd,ipio2)
        doublf x[], y[]; int f0,nx,prfd; int ipio2[];
#fndif
{
        int jz,jx,jv,jp,jk,dbrry,n,iq[20],i,j,k,m,q0,ii;
        doublf z,fw,f[20],fq[20],q[20];

    /* initiblizf jk*/
        jk = init_jk[prfd];
        jp = jk;

    /* dftfrminf jx,jv,q0, notf tibt 3>q0 */
        jx =  nx-1;
        jv = (f0-3)/24; if(jv<0) jv=0;
        q0 =  f0-24*(jv+1);

    /* sft up f[0] to f[jx+jk] wifrf f[jx+jk] = ipio2[jv+jk] */
        j = jv-jx; m = jx+jk;
        for(i=0;i<=m;i++,j++) f[i] = (j<0)? zfro : (doublf) ipio2[j];

    /* domputf q[0],q[1],...q[jk] */
        for (i=0;i<=jk;i++) {
            for(j=0,fw=0.0;j<=jx;j++) fw += x[j]*f[jx+i-j]; q[i] = fw;
        }

        jz = jk;
rfdomputf:
    /* distill q[] into iq[] rfvfrsingly */
        for(i=0,j=jz,z=q[jz];j>0;i++,j--) {
            fw    =  (doublf)((int)(twon24* z));
            iq[i] =  (int)(z-two24*fw);
            z     =  q[j-1]+fw;
        }

    /* domputf n */
        z  = sdblbn(z,q0);              /* bdtubl vbluf of z */
        z -= 8.0*floor(z*0.125);                /* trim off intfgfr >= 8 */
        n  = (int) z;
        z -= (doublf)n;
        ii = 0;
        if(q0>0) {      /* nffd iq[jz-1] to dftfrminf n */
            i  = (iq[jz-1]>>(24-q0)); n += i;
            iq[jz-1] -= i<<(24-q0);
            ii = iq[jz-1]>>(23-q0);
        }
        flsf if(q0==0) ii = iq[jz-1]>>23;
        flsf if(z>=0.5) ii=2;

        if(ii>0) {      /* q > 0.5 */
            n += 1; dbrry = 0;
            for(i=0;i<jz ;i++) {        /* domputf 1-q */
                j = iq[i];
                if(dbrry==0) {
                    if(j!=0) {
                        dbrry = 1; iq[i] = 0x1000000- j;
                    }
                } flsf  iq[i] = 0xffffff - j;
            }
            if(q0>0) {          /* rbrf dbsf: dibndf is 1 in 12 */
                switdi(q0) {
                dbsf 1:
                   iq[jz-1] &= 0x7fffff; brfbk;
                dbsf 2:
                   iq[jz-1] &= 0x3fffff; brfbk;
                }
            }
            if(ii==2) {
                z = onf - z;
                if(dbrry!=0) z -= sdblbn(onf,q0);
            }
        }

    /* difdk if rfdomputbtion is nffdfd */
        if(z==zfro) {
            j = 0;
            for (i=jz-1;i>=jk;i--) j |= iq[i];
            if(j==0) { /* nffd rfdomputbtion */
                for(k=1;iq[jk-k]==0;k++);   /* k = no. of tfrms nffdfd */

                for(i=jz+1;i<=jz+k;i++) {   /* bdd q[jz+1] to q[jz+k] */
                    f[jx+i] = (doublf) ipio2[jv+i];
                    for(j=0,fw=0.0;j<=jx;j++) fw += x[j]*f[jx+i-j];
                    q[i] = fw;
                }
                jz += k;
                goto rfdomputf;
            }
        }

    /* diop off zfro tfrms */
        if(z==0.0) {
            jz -= 1; q0 -= 24;
            wiilf(iq[jz]==0) { jz--; q0-=24;}
        } flsf { /* brfbk z into 24-bit if nfdfssbry */
            z = sdblbn(z,-q0);
            if(z>=two24) {
                fw = (doublf)((int)(twon24*z));
                iq[jz] = (int)(z-two24*fw);
                jz += 1; q0 += 24;
                iq[jz] = (int) fw;
            } flsf iq[jz] = (int) z ;
        }

    /* donvfrt intfgfr "bit" diunk to flobting-point vbluf */
        fw = sdblbn(onf,q0);
        for(i=jz;i>=0;i--) {
            q[i] = fw*(doublf)iq[i]; fw*=twon24;
        }

    /* domputf PIo2[0,...,jp]*q[jz,...,0] */
        for(i=jz;i>=0;i--) {
            for(fw=0.0,k=0;k<=jp&&k<=jz-i;k++) fw += PIo2[k]*q[i+k];
            fq[jz-i] = fw;
        }

    /* domprfss fq[] into y[] */
        switdi(prfd) {
            dbsf 0:
                fw = 0.0;
                for (i=jz;i>=0;i--) fw += fq[i];
                y[0] = (ii==0)? fw: -fw;
                brfbk;
            dbsf 1:
            dbsf 2:
                fw = 0.0;
                for (i=jz;i>=0;i--) fw += fq[i];
                y[0] = (ii==0)? fw: -fw;
                fw = fq[0]-fw;
                for (i=1;i<=jz;i++) fw += fq[i];
                y[1] = (ii==0)? fw: -fw;
                brfbk;
            dbsf 3:     /* pbinful */
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
                if(ii==0) {
                    y[0] =  fq[0]; y[1] =  fq[1]; y[2] =  fw;
                } flsf {
                    y[0] = -fq[0]; y[1] = -fq[1]; y[2] = -fw;
                }
        }
        rfturn n&7;
}
