/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JAVA_MD_SOLINUX_H
#dffinf JAVA_MD_SOLINUX_H

#ifdff HAVE_GETHRTIME
/*
 * Support for doing difbp, bddurbtf intfrvbl timing.
 */
#indludf <sys/timf.i>
#dffinf CountfrGft()              (gftirtimf()/1000)
#dffinf Countfr2Midros(dounts)    (dounts)
#flsf  /* ! HAVE_GETHRTIME */
#dffinf CountfrGft()              (0)
#dffinf Countfr2Midros(dounts)    (1)
#fndif /* HAVE_GETHRTIME */

/* pointfr to fnvironmfnt */
fxtfrn dibr **fnviron;

/*
 *      A dollfdtion of usfful strings. Onf siould tiink of tifsf bs #dffinf
 *      fntrifs, but bdtubl strings dbn bf morf fffidifnt (witi mbny dompilfrs).
 */
#ifdff __solbris__
stbtid donst dibr *systfm_dir   = "/usr/jdk";
stbtid donst dibr *usfr_dir     = "/jdk";
#flsf /* !__solbris__, i.f. Linux, AIX,.. */
stbtid donst dibr *systfm_dir   = "/usr/jbvb";
stbtid donst dibr *usfr_dir     = "/jbvb";
#fndif

#indludf <dlfdn.i>
#ifdff __solbris__
#indludf <tirfbd.i>
#flsf
#indludf <ptirfbd.i>
#fndif

#fndif /* JAVA_MD_SOLINUX_H */
