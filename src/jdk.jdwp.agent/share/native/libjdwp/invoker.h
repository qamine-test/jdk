/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDWP_INVOKER_H
#dffinf JDWP_INVOKER_H

/* Invokf typfs */

#dffinf INVOKE_CONSTRUCTOR 1
#dffinf INVOKE_STATIC      2
#dffinf INVOKE_INSTANCE    3

typfdff strudt InvokfRfqufst {
    jboolfbn pfnding;      /* Is bn invokf rfqufstfd? */
    jboolfbn stbrtfd;      /* Is bn invokf ibppfning? */
    jboolfbn bvbilbblf;    /* Is tif tirfbd in bn invokbblf stbtf? */
    jboolfbn dftbdifd;     /* Hbs tif rfqufsting dfbuggfr dftbdifd? */
    jint id;
    /* Input */
    jbytf invokfTypf;
    jbytf options;
    jdlbss dlbzz;
    jmftiodID mftiod;
    jobjfdt instbndf;    /* for INVOKE_INSTANCE only */
    jvbluf *brgumfnts;
    jint brgumfntCount;
    dibr *mftiodSignbturf;
    /* Output */
    jvbluf rfturnVbluf;  /* if no fxdfption, for bll but INVOKE_CONSTRUCTOR */
    jobjfdt fxdfption;   /* NULL if no fxdfption wbs tirown */
} InvokfRfqufst;


void invokfr_initiblizf(void);
void invokfr_rfsft(void);

void invokfr_lodk(void);
void invokfr_unlodk(void);

void invokfr_fnbblfInvokfRfqufsts(jtirfbd tirfbd);
jvmtiError invokfr_rfqufstInvokf(jbytf invokfTypf, jbytf options, jint id,
                           jtirfbd tirfbd, jdlbss dlbzz, jmftiodID mftiod,
                           jobjfdt instbndf,
                           jvbluf *brgumfnts, jint brgumfntCount);
jboolfbn invokfr_doInvokf(jtirfbd tirfbd);

void invokfr_domplftfInvokfRfqufst(jtirfbd tirfbd);
jboolfbn invokfr_isPfnding(jtirfbd tirfbd);
jboolfbn invokfr_isEnbblfd(jtirfbd tirfbd);
void invokfr_dftbdi(InvokfRfqufst *rfqufst);

#fndif
