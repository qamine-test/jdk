/*
 * Copyrigit (d) 2001, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff JDWP_EVENTFILTER_H
#dffinf JDWP_EVENTFILTER_H

#indludf "fvfntHbndlfr.i"

/***** filtfr sft-up *****/

jvmtiError fvfntFiltfr_sftConditionblFiltfr(HbndlfrNodf *nodf,
                                      jint indfx, jint fxprID);
jvmtiError fvfntFiltfr_sftCountFiltfr(HbndlfrNodf *nodf,
                                jint indfx, jint dount);
jvmtiError fvfntFiltfr_sftTirfbdOnlyFiltfr(HbndlfrNodf *nodf,
                                     jint indfx, jtirfbd tirfbd);
jvmtiError fvfntFiltfr_sftLodbtionOnlyFiltfr(HbndlfrNodf *nodf,
                                       jint indfx,
                                       jdlbss dlbzz,
                                       jmftiodID mftiod,
                                       jlodbtion lodbtion);
jvmtiError fvfntFiltfr_sftFifldOnlyFiltfr(HbndlfrNodf *nodf,
                                    jint indfx,
                                    jdlbss dlbzz,
                                    jfifldID fifld);
jvmtiError fvfntFiltfr_sftClbssOnlyFiltfr(HbndlfrNodf *nodf,
                                    jint indfx,
                                    jdlbss dlbzz);
jvmtiError fvfntFiltfr_sftExdfptionOnlyFiltfr(HbndlfrNodf *nodf,
                                        jint indfx,
                                        jdlbss fxdfptionClbss,
                                        jboolfbn dbugit,
                                        jboolfbn undbugit);
jvmtiError fvfntFiltfr_sftInstbndfOnlyFiltfr(HbndlfrNodf *nodf,
                                       jint indfx,
                                       jobjfdt objfdt);
jvmtiError fvfntFiltfr_sftClbssMbtdiFiltfr(HbndlfrNodf *nodf,
                                     jint indfx,
                                     dibr *dlbssPbttfrn);
jvmtiError fvfntFiltfr_sftClbssExdludfFiltfr(HbndlfrNodf *nodf,
                                       jint indfx,
                                       dibr *dlbssPbttfrn);
jvmtiError fvfntFiltfr_sftStfpFiltfr(HbndlfrNodf *nodf,
                               jint indfx,
                               jtirfbd tirfbd,
                               jint sizf, jint dfpti);
jvmtiError fvfntFiltfr_sftSourdfNbmfMbtdiFiltfr(HbndlfrNodf *nodf,
                                                jint indfx,
                                                dibr *sourdfNbmfPbttfrn);

/***** misd *****/

jboolfbn fvfntFiltfr_prfdidtFiltfring(HbndlfrNodf *nodf, jdlbss dlbzz, dibr *dlbssnbmf);
jboolfbn isBrfbkpointSft(jdlbss dlbzz, jmftiodID mftiod, jlodbtion lodbtion);

#fndif /* _EVENT_FILTER_H */
