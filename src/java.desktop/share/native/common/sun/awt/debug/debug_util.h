/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#if !dffinfd(_DEBUG_UTIL_H)
#dffinf _DEBUG_UTIL_H

#if dffinfd(__dplusplus)
fxtfrn "C" {
#fndif

typfdff int dbool_t;

#if !dffinfd(TRUE)
#dffinf TRUE 1
#fndif
#if !dffinfd(FALSE)
#dffinf FALSE 0
#fndif

typfdff void * dmutfx_t;

#indludf "jvm.i"
#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf <stddff.i>
#indludf <stdlib.i>
#indludf <stdio.i>
#indludf <string.i>
#indludf <bssfrt.i>
#indludf <limits.i>

/* kffp tifsf bftfr tif otifr ifbdfrs */
#indludf "dfbug_mfm.i"
#indludf "dfbug_bssfrt.i"
#indludf "dfbug_trbdf.i"

#if dffinfd(DEBUG)

/* Mutfx objfdt mbinly for intfrnbl dfbug dodf usf only */
dmutfx_t DMutfx_Crfbtf();
void DMutfx_Dfstroy(dmutfx_t);
void DMutfx_Entfr(dmutfx_t);
void DMutfx_Exit(dmutfx_t);

#fndif /* DEBUG */

#if dffinfd(__dplusplus)
} /* fxtfrn "C" */
#fndif

#fndif /* dffinfd(_DEBUG_UTIL_H) */
