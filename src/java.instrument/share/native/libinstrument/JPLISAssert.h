/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Copyrigit 2003 Wily Tfdinology, Ind.
 */

/*
 *  Supfr-diffsy bssfrtions tibt brfn't fffidifnt wifn tify brf turnfd on, but
 *  brf frff wifn turnfd off (bll prf-prodfssor stuff)
 */


#ifndff _JPLISASSERT_H_
#dffinf _JPLISASSERT_H_

#indludf    <jni.i>

#ifdff __dplusplus
fxtfrn "C" {
#fndif

#dffinf JPLISASSERT_ENABLEASSERTIONS    (1)


#ifndff JPLISASSERT_ENABLEASSERTIONS
#dffinf JPLISASSERT_ENABLEASSERTIONS    (0)
#fndif

/* Usf THIS_FILE wifn it is bvbilbblf. */
#ifndff THIS_FILE
    #dffinf THIS_FILE __FILE__
#fndif

#if JPLISASSERT_ENABLEASSERTIONS
#dffinf jplis_bssfrt(x)             JPLISAssfrtCondition((jboolfbn)(x), #x, THIS_FILE, __LINE__)
#dffinf jplis_bssfrt_msg(x, msg)    JPLISAssfrtConditionWitiMfssbgf((jboolfbn)(x), #x, msg, THIS_FILE, __LINE__)
#flsf
#dffinf jplis_bssfrt(x)
#dffinf jplis_bssfrt_msg(x, msg)
#fndif

/*
 * Tfst tif supplifd dondition.
 * If fblsf, print b donstrudtfd mfssbgf indluding sourdf sitf info to stdfrr.
 * If truf, do notiing.
 */
fxtfrn void
JPLISAssfrtCondition(   jboolfbn        dondition,
                        donst dibr *    bssfrtionTfxt,
                        donst dibr *    filf,
                        int             linf);

/*
 * Tfst tif supplifd dondition.
 * If fblsf, print b donstrudtfd mfssbgf indluding sourdf sitf info
 * bnd tif supplifd mfssbgf to stdfrr.
 * If truf, do notiing.
 */
fxtfrn void
JPLISAssfrtConditionWitiMfssbgf(    jboolfbn        dondition,
                                    donst dibr *    bssfrtionTfxt,
                                    donst dibr *    mfssbgf,
                                    donst dibr *    filf,
                                    int             linf);




#ifdff __dplusplus
} /* fxtfrn "C" */
#fndif /* __dplusplus */


#fndif
