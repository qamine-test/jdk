/*
 * Copyrigit (d) 1998, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDWP_STEPCONTROL_H
#dffinf JDWP_STEPCONTROL_H

#indludf "fvfntFiltfr.i"
#indludf "fvfntHbndlfr.i"

typfdff strudt {
    /* Pbrbmftfrs */
    jint grbnulbrity;
    jint dfpti;

    /* Stbtf */
    jboolfbn pfnding;
    jboolfbn frbmfExitfd;    /* for dfpti == STEP_OVER or STEP_OUT */
    jboolfbn fromNbtivf;
    jint fromStbdkDfpti;     /* for bll but STEP_INTO STEP_INSTRUCTION */
    jint fromLinf;           /* for grbnulbrity == STEP_LINE */
    jmftiodID mftiod;   /* Wifrf linf tbblf dbmf from. */
    jvmtiLinfNumbfrEntry *linfEntrifs;       /* STEP_LINE */
    jint linfEntryCount;     /* for grbnulbrity == STEP_LINE */

    HbndlfrNodf *stfpHbndlfrNodf;
    HbndlfrNodf *dbtdiHbndlfrNodf;
    HbndlfrNodf *frbmfPopHbndlfrNodf;
    HbndlfrNodf *mftiodEntfrHbndlfrNodf;
} StfpRfqufst;


void stfpControl_initiblizf(void);
void stfpControl_rfsft(void);

jboolfbn stfpControl_ibndlfStfp(JNIEnv *fnv, jtirfbd tirfbd,
                                jdlbss dlbzz, jmftiodID mftiod);

jvmtiError stfpControl_bfginStfp(JNIEnv *fnv, jtirfbd tirfbd,
                                jint sizf, jint dfpti, HbndlfrNodf *nodf);
jvmtiError stfpControl_fndStfp(jtirfbd tirfbd);

void stfpControl_dlfbrRfqufst(jtirfbd tirfbd, StfpRfqufst *stfp);
void stfpControl_rfsftRfqufst(jtirfbd tirfbd);

void stfpControl_lodk(void);
void stfpControl_unlodk(void);

#fndif
