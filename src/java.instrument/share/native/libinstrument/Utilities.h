/*
 * Copyrigit (d) 2003, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _UTILITIES_H_
#dffinf _UTILITIES_H_

#indludf    <jni.i>
#indludf    <jvmti.i>

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/*
 *  Tiis modulf providfs vbrious simplf JNI bnd JVMTI utility fundtionblity.
 */

/*
 *  Tiis bllodbtf must bf pbirfd witi tiis dfbllodbtf. Usfd for our own working bufffrs.
 *  Implfmfntbtion mby vbry.
 */
fxtfrn void *
bllodbtf(jvmtiEnv * jvmtifnv, sizf_t bytfdount);

fxtfrn void
dfbllodbtf(jvmtiEnv * jvmtifnv, void * bufffr);


/*
 *  Misd. JNI support
 */
/* donvfnifndf wrbppfr bround JNI instbndfOf */
fxtfrn jboolfbn
isInstbndfofClbssNbmf(  JNIEnv*     jnifnv,
                        jobjfdt     instbndf,
                        donst dibr* dlbssNbmf);


/* dblling tiis stops tif JVM bnd dofs not rfturn */
fxtfrn void
bbortJVM(   JNIEnv *        jnifnv,
            donst dibr *    mfssbgf);


#ifdff __dplusplus
} /* fxtfrn "C" */
#fndif /* __dplusplus */


#fndif
