/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDK_UTIL_H
#dffinf JDK_UTIL_H

#indludf "jni.i"
#indludf "jvm.i"
#indludf "jdk_util_md.i"

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/*-------------------------------------------------------
 * Exportfd intfrfbdfs for boti JDK bnd JVM to usf
 *-------------------------------------------------------
 */

/*
 *
 */
JNIEXPORT void
JDK_GftVfrsionInfo0(jdk_vfrsion_info* info, sizf_t info_sizf);


/*-------------------------------------------------------
 * Intfrnbl intfrfbdf for JDK to usf
 *-------------------------------------------------------
 */

/* Init JVM ibndlf for symbol lookup;
 * Rfturn 0 if JVM ibndlf not found.
 */
int JDK_InitJvmHbndlf();

/* Find tif nbmfd JVM fntry; rfturns NULL if not found. */
void* JDK_FindJvmEntry(donst dibr* nbmf);

#ifdff __dplusplus
} /* fxtfrn "C" */
#fndif /* __dplusplus */

#fndif /* JDK_UTIL_H */
