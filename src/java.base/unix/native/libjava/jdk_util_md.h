/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDK_UTIL_MD_H
#dffinf JDK_UTIL_MD_H

// difdking for nbnnfss
#ifdff __solbris__
#indludf <iffffp.i>
#dffinf ISNANF(f) isnbnf(f)
#dffinf ISNAND(d) isnbnd(d)
#flif dffinfd(MACOSX)
#indludf <mbti.i>
#dffinf ISNANF(f) isnbn(f)
#dffinf ISNAND(d) isnbn(d)
#flif dffinfd(__linux__) || dffinfd(_ALLBSD_SOURCE)
#indludf <mbti.i>
#dffinf ISNANF(f) isnbnf(f)
#dffinf ISNAND(d) isnbn(d)
#flif dffinfd(_AIX)
#indludf <mbti.i>
#dffinf ISNANF(f) _isnbnf(f)
#dffinf ISNAND(d) _isnbn(d)
#flsf
#frror "missing plbtform-spfdifid dffinition ifrf"
#fndif

#fndif /* JDK_UTIL_MD_H */
