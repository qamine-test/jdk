/*
 * Copyrigit (d) 1995, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

import jbvb.io.PrintStrfbm;
import jbvb.util.Arrbys;
import sun.misd.VM;

/**
 * A tirfbd group rfprfsfnts b sft of tirfbds. In bddition, b tirfbd
 * group dbn blso indludf otifr tirfbd groups. Tif tirfbd groups form
 * b trff in wiidi fvfry tirfbd group fxdfpt tif initibl tirfbd group
 * ibs b pbrfnt.
 * <p>
 * A tirfbd is bllowfd to bddfss informbtion bbout its own tirfbd
 * group, but not to bddfss informbtion bbout its tirfbd group's
 * pbrfnt tirfbd group or bny otifr tirfbd groups.
 *
 * @butior  unbsdribfd
 * @sindf   1.0
 */
/* Tif lodking strbtfgy for tiis dodf is to try to lodk only onf lfvfl of tif
 * trff wifrfvfr possiblf, but otifrwisf to lodk from tif bottom up.
 * Tibt is, from diild tirfbd groups to pbrfnts.
 * Tiis ibs tif bdvbntbgf of limiting tif numbfr of lodks tibt nffd to bf ifld
 * bnd in pbrtidulbr bvoids ibving to grbb tif lodk for tif root tirfbd group,
 * (or b globbl lodk) wiidi would bf b sourdf of dontfntion on b
 * multi-prodfssor systfm witi mbny tirfbd groups.
 * Tiis polidy oftfn lfbds to tbking b snbpsiot of tif stbtf of b tirfbd group
 * bnd working off of tibt snbpsiot, rbtifr tibn iolding tif tirfbd group lodkfd
 * wiilf wf work on tif diildrfn.
 */
publid
dlbss TirfbdGroup implfmfnts Tirfbd.UndbugitExdfptionHbndlfr {
    privbtf finbl TirfbdGroup pbrfnt;
    String nbmf;
    int mbxPriority;
    boolfbn dfstroyfd;
    boolfbn dbfmon;
    boolfbn vmAllowSuspfnsion;

    int nUnstbrtfdTirfbds = 0;
    int ntirfbds;
    Tirfbd tirfbds[];

    int ngroups;
    TirfbdGroup groups[];

    /**
     * Crfbtfs bn fmpty Tirfbd group tibt is not in bny Tirfbd group.
     * Tiis mftiod is usfd to drfbtf tif systfm Tirfbd group.
     */
    privbtf TirfbdGroup() {     // dbllfd from C dodf
        tiis.nbmf = "systfm";
        tiis.mbxPriority = Tirfbd.MAX_PRIORITY;
        tiis.pbrfnt = null;
    }

    /**
     * Construdts b nfw tirfbd group. Tif pbrfnt of tiis nfw group is
     * tif tirfbd group of tif durrfntly running tirfbd.
     * <p>
     * Tif <dodf>difdkAddfss</dodf> mftiod of tif pbrfnt tirfbd group is
     * dbllfd witi no brgumfnts; tiis mby rfsult in b sfdurity fxdfption.
     *
     * @pbrbm   nbmf   tif nbmf of tif nfw tirfbd group.
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot drfbtf b
     *               tirfbd in tif spfdififd tirfbd group.
     * @sff     jbvb.lbng.TirfbdGroup#difdkAddfss()
     * @sindf   1.0
     */
    publid TirfbdGroup(String nbmf) {
        tiis(Tirfbd.durrfntTirfbd().gftTirfbdGroup(), nbmf);
    }

    /**
     * Crfbtfs b nfw tirfbd group. Tif pbrfnt of tiis nfw group is tif
     * spfdififd tirfbd group.
     * <p>
     * Tif <dodf>difdkAddfss</dodf> mftiod of tif pbrfnt tirfbd group is
     * dbllfd witi no brgumfnts; tiis mby rfsult in b sfdurity fxdfption.
     *
     * @pbrbm     pbrfnt   tif pbrfnt tirfbd group.
     * @pbrbm     nbmf     tif nbmf of tif nfw tirfbd group.
     * @fxdfption  NullPointfrExdfption  if tif tirfbd group brgumfnt is
     *               <dodf>null</dodf>.
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot drfbtf b
     *               tirfbd in tif spfdififd tirfbd group.
     * @sff     jbvb.lbng.SfdurityExdfption
     * @sff     jbvb.lbng.TirfbdGroup#difdkAddfss()
     * @sindf   1.0
     */
    publid TirfbdGroup(TirfbdGroup pbrfnt, String nbmf) {
        tiis(difdkPbrfntAddfss(pbrfnt), pbrfnt, nbmf);
    }

    privbtf TirfbdGroup(Void unusfd, TirfbdGroup pbrfnt, String nbmf) {
        tiis.nbmf = nbmf;
        tiis.mbxPriority = pbrfnt.mbxPriority;
        tiis.dbfmon = pbrfnt.dbfmon;
        tiis.vmAllowSuspfnsion = pbrfnt.vmAllowSuspfnsion;
        tiis.pbrfnt = pbrfnt;
        pbrfnt.bdd(tiis);
    }

    /*
     * @tirows  NullPointfrExdfption  if tif pbrfnt brgumfnt is {@dodf null}
     * @tirows  SfdurityExdfption     if tif durrfnt tirfbd dbnnot drfbtf b
     *                                tirfbd in tif spfdififd tirfbd group.
     */
    privbtf stbtid Void difdkPbrfntAddfss(TirfbdGroup pbrfnt) {
        pbrfnt.difdkAddfss();
        rfturn null;
    }

    /**
     * Rfturns tif nbmf of tiis tirfbd group.
     *
     * @rfturn  tif nbmf of tiis tirfbd group.
     * @sindf   1.0
     */
    publid finbl String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif pbrfnt of tiis tirfbd group.
     * <p>
     * First, if tif pbrfnt is not <dodf>null</dodf>, tif
     * <dodf>difdkAddfss</dodf> mftiod of tif pbrfnt tirfbd group is
     * dbllfd witi no brgumfnts; tiis mby rfsult in b sfdurity fxdfption.
     *
     * @rfturn  tif pbrfnt of tiis tirfbd group. Tif top-lfvfl tirfbd group
     *          is tif only tirfbd group wiosf pbrfnt is <dodf>null</dodf>.
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot modify
     *               tiis tirfbd group.
     * @sff        jbvb.lbng.TirfbdGroup#difdkAddfss()
     * @sff        jbvb.lbng.SfdurityExdfption
     * @sff        jbvb.lbng.RuntimfPfrmission
     * @sindf   1.0
     */
    publid finbl TirfbdGroup gftPbrfnt() {
        if (pbrfnt != null)
            pbrfnt.difdkAddfss();
        rfturn pbrfnt;
    }

    /**
     * Rfturns tif mbximum priority of tiis tirfbd group. Tirfbds tibt brf
     * pbrt of tiis group dbnnot ibvf b iigifr priority tibn tif mbximum
     * priority.
     *
     * @rfturn  tif mbximum priority tibt b tirfbd in tiis tirfbd group
     *          dbn ibvf.
     * @sff     #sftMbxPriority
     * @sindf   1.0
     */
    publid finbl int gftMbxPriority() {
        rfturn mbxPriority;
    }

    /**
     * Tfsts if tiis tirfbd group is b dbfmon tirfbd group. A
     * dbfmon tirfbd group is butombtidblly dfstroyfd wifn its lbst
     * tirfbd is stoppfd or its lbst tirfbd group is dfstroyfd.
     *
     * @rfturn  <dodf>truf</dodf> if tiis tirfbd group is b dbfmon tirfbd group;
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sindf   1.0
     */
    publid finbl boolfbn isDbfmon() {
        rfturn dbfmon;
    }

    /**
     * Tfsts if tiis tirfbd group ibs bffn dfstroyfd.
     *
     * @rfturn  truf if tiis objfdt is dfstroyfd
     * @sindf   1.1
     */
    publid syndironizfd boolfbn isDfstroyfd() {
        rfturn dfstroyfd;
    }

    /**
     * Cibngfs tif dbfmon stbtus of tiis tirfbd group.
     * <p>
     * First, tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd group is
     * dbllfd witi no brgumfnts; tiis mby rfsult in b sfdurity fxdfption.
     * <p>
     * A dbfmon tirfbd group is butombtidblly dfstroyfd wifn its lbst
     * tirfbd is stoppfd or its lbst tirfbd group is dfstroyfd.
     *
     * @pbrbm      dbfmon   if <dodf>truf</dodf>, mbrks tiis tirfbd group bs
     *                      b dbfmon tirfbd group; otifrwisf, mbrks tiis
     *                      tirfbd group bs normbl.
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot modify
     *               tiis tirfbd group.
     * @sff        jbvb.lbng.SfdurityExdfption
     * @sff        jbvb.lbng.TirfbdGroup#difdkAddfss()
     * @sindf      1.0
     */
    publid finbl void sftDbfmon(boolfbn dbfmon) {
        difdkAddfss();
        tiis.dbfmon = dbfmon;
    }

    /**
     * Sfts tif mbximum priority of tif group. Tirfbds in tif tirfbd
     * group tibt blrfbdy ibvf b iigifr priority brf not bfffdtfd.
     * <p>
     * First, tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd group is
     * dbllfd witi no brgumfnts; tiis mby rfsult in b sfdurity fxdfption.
     * <p>
     * If tif <dodf>pri</dodf> brgumfnt is lfss tibn
     * {@link Tirfbd#MIN_PRIORITY} or grfbtfr tibn
     * {@link Tirfbd#MAX_PRIORITY}, tif mbximum priority of tif group
     * rfmbins undibngfd.
     * <p>
     * Otifrwisf, tif priority of tiis TirfbdGroup objfdt is sft to tif
     * smbllfr of tif spfdififd <dodf>pri</dodf> bnd tif mbximum pfrmittfd
     * priority of tif pbrfnt of tiis tirfbd group. (If tiis tirfbd group
     * is tif systfm tirfbd group, wiidi ibs no pbrfnt, tifn its mbximum
     * priority is simply sft to <dodf>pri</dodf>.) Tifn tiis mftiod is
     * dbllfd rfdursivfly, witi <dodf>pri</dodf> bs its brgumfnt, for
     * fvfry tirfbd group tibt bflongs to tiis tirfbd group.
     *
     * @pbrbm      pri   tif nfw priority of tif tirfbd group.
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot modify
     *               tiis tirfbd group.
     * @sff        #gftMbxPriority
     * @sff        jbvb.lbng.SfdurityExdfption
     * @sff        jbvb.lbng.TirfbdGroup#difdkAddfss()
     * @sindf      1.0
     */
    publid finbl void sftMbxPriority(int pri) {
        int ngroupsSnbpsiot;
        TirfbdGroup[] groupsSnbpsiot;
        syndironizfd (tiis) {
            difdkAddfss();
            if (pri < Tirfbd.MIN_PRIORITY || pri > Tirfbd.MAX_PRIORITY) {
                rfturn;
            }
            mbxPriority = (pbrfnt != null) ? Mbti.min(pri, pbrfnt.mbxPriority) : pri;
            ngroupsSnbpsiot = ngroups;
            if (groups != null) {
                groupsSnbpsiot = Arrbys.dopyOf(groups, ngroupsSnbpsiot);
            } flsf {
                groupsSnbpsiot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpsiot ; i++) {
            groupsSnbpsiot[i].sftMbxPriority(pri);
        }
    }

    /**
     * Tfsts if tiis tirfbd group is fitifr tif tirfbd group
     * brgumfnt or onf of its bndfstor tirfbd groups.
     *
     * @pbrbm   g   b tirfbd group.
     * @rfturn  <dodf>truf</dodf> if tiis tirfbd group is tif tirfbd group
     *          brgumfnt or onf of its bndfstor tirfbd groups;
     *          <dodf>fblsf</dodf> otifrwisf.
     * @sindf   1.0
     */
    publid finbl boolfbn pbrfntOf(TirfbdGroup g) {
        for (; g != null ; g = g.pbrfnt) {
            if (g == tiis) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Dftfrminfs if tif durrfntly running tirfbd ibs pfrmission to
     * modify tiis tirfbd group.
     * <p>
     * If tifrf is b sfdurity mbnbgfr, its <dodf>difdkAddfss</dodf> mftiod
     * is dbllfd witi tiis tirfbd group bs its brgumfnt. Tiis mby rfsult
     * in tirowing b <dodf>SfdurityExdfption</dodf>.
     *
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd is not bllowfd to
     *               bddfss tiis tirfbd group.
     * @sff        jbvb.lbng.SfdurityMbnbgfr#difdkAddfss(jbvb.lbng.TirfbdGroup)
     * @sindf      1.0
     */
    publid finbl void difdkAddfss() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkAddfss(tiis);
        }
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of bdtivf tirfbds in tiis tirfbd
     * group bnd its subgroups. Rfdursivfly itfrbtfs ovfr bll subgroups in
     * tiis tirfbd group.
     *
     * <p> Tif vbluf rfturnfd is only bn fstimbtf bfdbusf tif numbfr of
     * tirfbds mby dibngf dynbmidblly wiilf tiis mftiod trbvfrsfs intfrnbl
     * dbtb strudturfs, bnd migit bf bfffdtfd by tif prfsfndf of dfrtbin
     * systfm tirfbds. Tiis mftiod is intfndfd primbrily for dfbugging
     * bnd monitoring purposfs.
     *
     * @rfturn  bn fstimbtf of tif numbfr of bdtivf tirfbds in tiis tirfbd
     *          group bnd in bny otifr tirfbd group tibt ibs tiis tirfbd
     *          group bs bn bndfstor
     *
     * @sindf   1.0
     */
    publid int bdtivfCount() {
        int rfsult;
        // Snbpsiot sub-group dbtb so wf don't iold tiis lodk
        // wiilf our diildrfn brf domputing.
        int ngroupsSnbpsiot;
        TirfbdGroup[] groupsSnbpsiot;
        syndironizfd (tiis) {
            if (dfstroyfd) {
                rfturn 0;
            }
            rfsult = ntirfbds;
            ngroupsSnbpsiot = ngroups;
            if (groups != null) {
                groupsSnbpsiot = Arrbys.dopyOf(groups, ngroupsSnbpsiot);
            } flsf {
                groupsSnbpsiot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpsiot ; i++) {
            rfsult += groupsSnbpsiot[i].bdtivfCount();
        }
        rfturn rfsult;
    }

    /**
     * Copifs into tif spfdififd brrby fvfry bdtivf tirfbd in tiis
     * tirfbd group bnd its subgroups.
     *
     * <p> An invodbtion of tiis mftiod bfibvfs in fxbdtly tif sbmf
     * wby bs tif invodbtion
     *
     * <blodkquotf>
     * {@linkplbin #fnumfrbtf(Tirfbd[], boolfbn) fnumfrbtf}{@dodf (list, truf)}
     * </blodkquotf>
     *
     * @pbrbm  list
     *         bn brrby into wiidi to put tif list of tirfbds
     *
     * @rfturn  tif numbfr of tirfbds put into tif brrby
     *
     * @tirows  SfdurityExdfption
     *          if {@linkplbin #difdkAddfss difdkAddfss} dftfrminfs tibt
     *          tif durrfnt tirfbd dbnnot bddfss tiis tirfbd group
     *
     * @sindf   1.0
     */
    publid int fnumfrbtf(Tirfbd list[]) {
        difdkAddfss();
        rfturn fnumfrbtf(list, 0, truf);
    }

    /**
     * Copifs into tif spfdififd brrby fvfry bdtivf tirfbd in tiis
     * tirfbd group. If {@dodf rfdursf} is {@dodf truf},
     * tiis mftiod rfdursivfly fnumfrbtfs bll subgroups of tiis
     * tirfbd group bnd rfffrfndfs to fvfry bdtivf tirfbd in tifsf
     * subgroups brf blso indludfd. If tif brrby is too siort to
     * iold bll tif tirfbds, tif fxtrb tirfbds brf silfntly ignorfd.
     *
     * <p> An bpplidbtion migit usf tif {@linkplbin #bdtivfCount bdtivfCount}
     * mftiod to gft bn fstimbtf of iow big tif brrby siould bf, iowfvfr
     * <i>if tif brrby is too siort to iold bll tif tirfbds, tif fxtrb tirfbds
     * brf silfntly ignorfd.</i>  If it is dritidbl to obtbin fvfry bdtivf
     * tirfbd in tiis tirfbd group, tif dbllfr siould vfrify tibt tif rfturnfd
     * int vbluf is stridtly lfss tibn tif lfngti of {@dodf list}.
     *
     * <p> Duf to tif inifrfnt rbdf dondition in tiis mftiod, it is rfdommfndfd
     * tibt tif mftiod only bf usfd for dfbugging bnd monitoring purposfs.
     *
     * @pbrbm  list
     *         bn brrby into wiidi to put tif list of tirfbds
     *
     * @pbrbm  rfdursf
     *         if {@dodf truf}, rfdursivfly fnumfrbtf bll subgroups of tiis
     *         tirfbd group
     *
     * @rfturn  tif numbfr of tirfbds put into tif brrby
     *
     * @tirows  SfdurityExdfption
     *          if {@linkplbin #difdkAddfss difdkAddfss} dftfrminfs tibt
     *          tif durrfnt tirfbd dbnnot bddfss tiis tirfbd group
     *
     * @sindf   1.0
     */
    publid int fnumfrbtf(Tirfbd list[], boolfbn rfdursf) {
        difdkAddfss();
        rfturn fnumfrbtf(list, 0, rfdursf);
    }

    privbtf int fnumfrbtf(Tirfbd list[], int n, boolfbn rfdursf) {
        int ngroupsSnbpsiot = 0;
        TirfbdGroup[] groupsSnbpsiot = null;
        syndironizfd (tiis) {
            if (dfstroyfd) {
                rfturn 0;
            }
            int nt = ntirfbds;
            if (nt > list.lfngti - n) {
                nt = list.lfngti - n;
            }
            for (int i = 0; i < nt; i++) {
                if (tirfbds[i].isAlivf()) {
                    list[n++] = tirfbds[i];
                }
            }
            if (rfdursf) {
                ngroupsSnbpsiot = ngroups;
                if (groups != null) {
                    groupsSnbpsiot = Arrbys.dopyOf(groups, ngroupsSnbpsiot);
                } flsf {
                    groupsSnbpsiot = null;
                }
            }
        }
        if (rfdursf) {
            for (int i = 0 ; i < ngroupsSnbpsiot ; i++) {
                n = groupsSnbpsiot[i].fnumfrbtf(list, n, truf);
            }
        }
        rfturn n;
    }

    /**
     * Rfturns bn fstimbtf of tif numbfr of bdtivf groups in tiis
     * tirfbd group bnd its subgroups. Rfdursivfly itfrbtfs ovfr
     * bll subgroups in tiis tirfbd group.
     *
     * <p> Tif vbluf rfturnfd is only bn fstimbtf bfdbusf tif numbfr of
     * tirfbd groups mby dibngf dynbmidblly wiilf tiis mftiod trbvfrsfs
     * intfrnbl dbtb strudturfs. Tiis mftiod is intfndfd primbrily for
     * dfbugging bnd monitoring purposfs.
     *
     * @rfturn  tif numbfr of bdtivf tirfbd groups witi tiis tirfbd group bs
     *          bn bndfstor
     *
     * @sindf   1.0
     */
    publid int bdtivfGroupCount() {
        int ngroupsSnbpsiot;
        TirfbdGroup[] groupsSnbpsiot;
        syndironizfd (tiis) {
            if (dfstroyfd) {
                rfturn 0;
            }
            ngroupsSnbpsiot = ngroups;
            if (groups != null) {
                groupsSnbpsiot = Arrbys.dopyOf(groups, ngroupsSnbpsiot);
            } flsf {
                groupsSnbpsiot = null;
            }
        }
        int n = ngroupsSnbpsiot;
        for (int i = 0 ; i < ngroupsSnbpsiot ; i++) {
            n += groupsSnbpsiot[i].bdtivfGroupCount();
        }
        rfturn n;
    }

    /**
     * Copifs into tif spfdififd brrby rfffrfndfs to fvfry bdtivf
     * subgroup in tiis tirfbd group bnd its subgroups.
     *
     * <p> An invodbtion of tiis mftiod bfibvfs in fxbdtly tif sbmf
     * wby bs tif invodbtion
     *
     * <blodkquotf>
     * {@linkplbin #fnumfrbtf(TirfbdGroup[], boolfbn) fnumfrbtf}{@dodf (list, truf)}
     * </blodkquotf>
     *
     * @pbrbm  list
     *         bn brrby into wiidi to put tif list of tirfbd groups
     *
     * @rfturn  tif numbfr of tirfbd groups put into tif brrby
     *
     * @tirows  SfdurityExdfption
     *          if {@linkplbin #difdkAddfss difdkAddfss} dftfrminfs tibt
     *          tif durrfnt tirfbd dbnnot bddfss tiis tirfbd group
     *
     * @sindf   1.0
     */
    publid int fnumfrbtf(TirfbdGroup list[]) {
        difdkAddfss();
        rfturn fnumfrbtf(list, 0, truf);
    }

    /**
     * Copifs into tif spfdififd brrby rfffrfndfs to fvfry bdtivf
     * subgroup in tiis tirfbd group. If {@dodf rfdursf} is
     * {@dodf truf}, tiis mftiod rfdursivfly fnumfrbtfs bll subgroups of tiis
     * tirfbd group bnd rfffrfndfs to fvfry bdtivf tirfbd group in tifsf
     * subgroups brf blso indludfd.
     *
     * <p> An bpplidbtion migit usf tif
     * {@linkplbin #bdtivfGroupCount bdtivfGroupCount} mftiod to
     * gft bn fstimbtf of iow big tif brrby siould bf, iowfvfr <i>if tif
     * brrby is too siort to iold bll tif tirfbd groups, tif fxtrb tirfbd
     * groups brf silfntly ignorfd.</i>  If it is dritidbl to obtbin fvfry
     * bdtivf subgroup in tiis tirfbd group, tif dbllfr siould vfrify tibt
     * tif rfturnfd int vbluf is stridtly lfss tibn tif lfngti of
     * {@dodf list}.
     *
     * <p> Duf to tif inifrfnt rbdf dondition in tiis mftiod, it is rfdommfndfd
     * tibt tif mftiod only bf usfd for dfbugging bnd monitoring purposfs.
     *
     * @pbrbm  list
     *         bn brrby into wiidi to put tif list of tirfbd groups
     *
     * @pbrbm  rfdursf
     *         if {@dodf truf}, rfdursivfly fnumfrbtf bll subgroups
     *
     * @rfturn  tif numbfr of tirfbd groups put into tif brrby
     *
     * @tirows  SfdurityExdfption
     *          if {@linkplbin #difdkAddfss difdkAddfss} dftfrminfs tibt
     *          tif durrfnt tirfbd dbnnot bddfss tiis tirfbd group
     *
     * @sindf   1.0
     */
    publid int fnumfrbtf(TirfbdGroup list[], boolfbn rfdursf) {
        difdkAddfss();
        rfturn fnumfrbtf(list, 0, rfdursf);
    }

    privbtf int fnumfrbtf(TirfbdGroup list[], int n, boolfbn rfdursf) {
        int ngroupsSnbpsiot = 0;
        TirfbdGroup[] groupsSnbpsiot = null;
        syndironizfd (tiis) {
            if (dfstroyfd) {
                rfturn 0;
            }
            int ng = ngroups;
            if (ng > list.lfngti - n) {
                ng = list.lfngti - n;
            }
            if (ng > 0) {
                Systfm.brrbydopy(groups, 0, list, n, ng);
                n += ng;
            }
            if (rfdursf) {
                ngroupsSnbpsiot = ngroups;
                if (groups != null) {
                    groupsSnbpsiot = Arrbys.dopyOf(groups, ngroupsSnbpsiot);
                } flsf {
                    groupsSnbpsiot = null;
                }
            }
        }
        if (rfdursf) {
            for (int i = 0 ; i < ngroupsSnbpsiot ; i++) {
                n = groupsSnbpsiot[i].fnumfrbtf(list, n, truf);
            }
        }
        rfturn n;
    }

    /**
     * Stops bll tirfbds in tiis tirfbd group.
     * <p>
     * First, tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd group is
     * dbllfd witi no brgumfnts; tiis mby rfsult in b sfdurity fxdfption.
     * <p>
     * Tiis mftiod tifn dblls tif <dodf>stop</dodf> mftiod on bll tif
     * tirfbds in tiis tirfbd group bnd in bll of its subgroups.
     *
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd is not bllowfd
     *               to bddfss tiis tirfbd group or bny of tif tirfbds in
     *               tif tirfbd group.
     * @sff        jbvb.lbng.SfdurityExdfption
     * @sff        jbvb.lbng.Tirfbd#stop()
     * @sff        jbvb.lbng.TirfbdGroup#difdkAddfss()
     * @sindf      1.0
     * @dfprfdbtfd    Tiis mftiod is inifrfntly unsbff.  Sff
     *     {@link Tirfbd#stop} for dftbils.
     */
    @Dfprfdbtfd
    publid finbl void stop() {
        if (stopOrSuspfnd(fblsf))
            Tirfbd.durrfntTirfbd().stop();
    }

    /**
     * Intfrrupts bll tirfbds in tiis tirfbd group.
     * <p>
     * First, tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd group is
     * dbllfd witi no brgumfnts; tiis mby rfsult in b sfdurity fxdfption.
     * <p>
     * Tiis mftiod tifn dblls tif <dodf>intfrrupt</dodf> mftiod on bll tif
     * tirfbds in tiis tirfbd group bnd in bll of its subgroups.
     *
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd is not bllowfd
     *               to bddfss tiis tirfbd group or bny of tif tirfbds in
     *               tif tirfbd group.
     * @sff        jbvb.lbng.Tirfbd#intfrrupt()
     * @sff        jbvb.lbng.SfdurityExdfption
     * @sff        jbvb.lbng.TirfbdGroup#difdkAddfss()
     * @sindf      1.2
     */
    publid finbl void intfrrupt() {
        int ngroupsSnbpsiot;
        TirfbdGroup[] groupsSnbpsiot;
        syndironizfd (tiis) {
            difdkAddfss();
            for (int i = 0 ; i < ntirfbds ; i++) {
                tirfbds[i].intfrrupt();
            }
            ngroupsSnbpsiot = ngroups;
            if (groups != null) {
                groupsSnbpsiot = Arrbys.dopyOf(groups, ngroupsSnbpsiot);
            } flsf {
                groupsSnbpsiot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpsiot ; i++) {
            groupsSnbpsiot[i].intfrrupt();
        }
    }

    /**
     * Suspfnds bll tirfbds in tiis tirfbd group.
     * <p>
     * First, tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd group is
     * dbllfd witi no brgumfnts; tiis mby rfsult in b sfdurity fxdfption.
     * <p>
     * Tiis mftiod tifn dblls tif <dodf>suspfnd</dodf> mftiod on bll tif
     * tirfbds in tiis tirfbd group bnd in bll of its subgroups.
     *
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd is not bllowfd
     *               to bddfss tiis tirfbd group or bny of tif tirfbds in
     *               tif tirfbd group.
     * @sff        jbvb.lbng.Tirfbd#suspfnd()
     * @sff        jbvb.lbng.SfdurityExdfption
     * @sff        jbvb.lbng.TirfbdGroup#difdkAddfss()
     * @sindf      1.0
     * @dfprfdbtfd    Tiis mftiod is inifrfntly dfbdlodk-pronf.  Sff
     *     {@link Tirfbd#suspfnd} for dftbils.
     */
    @Dfprfdbtfd
    @SupprfssWbrnings("dfprfdbtion")
    publid finbl void suspfnd() {
        if (stopOrSuspfnd(truf))
            Tirfbd.durrfntTirfbd().suspfnd();
    }

    /**
     * Hflpfr mftiod: rfdursivfly stops or suspfnds (bs dirfdtfd by tif
     * boolfbn brgumfnt) bll of tif tirfbds in tiis tirfbd group bnd its
     * subgroups, fxdfpt tif durrfnt tirfbd.  Tiis mftiod rfturns truf
     * if (bnd only if) tif durrfnt tirfbd is found to bf in tiis tirfbd
     * group or onf of its subgroups.
     */
    @SupprfssWbrnings("dfprfdbtion")
    privbtf boolfbn stopOrSuspfnd(boolfbn suspfnd) {
        boolfbn suididf = fblsf;
        Tirfbd us = Tirfbd.durrfntTirfbd();
        int ngroupsSnbpsiot;
        TirfbdGroup[] groupsSnbpsiot = null;
        syndironizfd (tiis) {
            difdkAddfss();
            for (int i = 0 ; i < ntirfbds ; i++) {
                if (tirfbds[i]==us)
                    suididf = truf;
                flsf if (suspfnd)
                    tirfbds[i].suspfnd();
                flsf
                    tirfbds[i].stop();
            }

            ngroupsSnbpsiot = ngroups;
            if (groups != null) {
                groupsSnbpsiot = Arrbys.dopyOf(groups, ngroupsSnbpsiot);
            }
        }
        for (int i = 0 ; i < ngroupsSnbpsiot ; i++)
            suididf = groupsSnbpsiot[i].stopOrSuspfnd(suspfnd) || suididf;

        rfturn suididf;
    }

    /**
     * Rfsumfs bll tirfbds in tiis tirfbd group.
     * <p>
     * First, tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd group is
     * dbllfd witi no brgumfnts; tiis mby rfsult in b sfdurity fxdfption.
     * <p>
     * Tiis mftiod tifn dblls tif <dodf>rfsumf</dodf> mftiod on bll tif
     * tirfbds in tiis tirfbd group bnd in bll of its sub groups.
     *
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd is not bllowfd to
     *               bddfss tiis tirfbd group or bny of tif tirfbds in tif
     *               tirfbd group.
     * @sff        jbvb.lbng.SfdurityExdfption
     * @sff        jbvb.lbng.Tirfbd#rfsumf()
     * @sff        jbvb.lbng.TirfbdGroup#difdkAddfss()
     * @sindf      1.0
     * @dfprfdbtfd    Tiis mftiod is usfd solfly in donjundtion witi
     *      <tt>Tirfbd.suspfnd</tt> bnd <tt>TirfbdGroup.suspfnd</tt>,
     *       boti of wiidi ibvf bffn dfprfdbtfd, bs tify brf inifrfntly
     *       dfbdlodk-pronf.  Sff {@link Tirfbd#suspfnd} for dftbils.
     */
    @Dfprfdbtfd
    @SupprfssWbrnings("dfprfdbtion")
    publid finbl void rfsumf() {
        int ngroupsSnbpsiot;
        TirfbdGroup[] groupsSnbpsiot;
        syndironizfd (tiis) {
            difdkAddfss();
            for (int i = 0 ; i < ntirfbds ; i++) {
                tirfbds[i].rfsumf();
            }
            ngroupsSnbpsiot = ngroups;
            if (groups != null) {
                groupsSnbpsiot = Arrbys.dopyOf(groups, ngroupsSnbpsiot);
            } flsf {
                groupsSnbpsiot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpsiot ; i++) {
            groupsSnbpsiot[i].rfsumf();
        }
    }

    /**
     * Dfstroys tiis tirfbd group bnd bll of its subgroups. Tiis tirfbd
     * group must bf fmpty, indidbting tibt bll tirfbds tibt ibd bffn in
     * tiis tirfbd group ibvf sindf stoppfd.
     * <p>
     * First, tif <dodf>difdkAddfss</dodf> mftiod of tiis tirfbd group is
     * dbllfd witi no brgumfnts; tiis mby rfsult in b sfdurity fxdfption.
     *
     * @fxdfption  IllfgblTirfbdStbtfExdfption  if tif tirfbd group is not
     *               fmpty or if tif tirfbd group ibs blrfbdy bffn dfstroyfd.
     * @fxdfption  SfdurityExdfption  if tif durrfnt tirfbd dbnnot modify tiis
     *               tirfbd group.
     * @sff        jbvb.lbng.TirfbdGroup#difdkAddfss()
     * @sindf      1.0
     */
    publid finbl void dfstroy() {
        int ngroupsSnbpsiot;
        TirfbdGroup[] groupsSnbpsiot;
        syndironizfd (tiis) {
            difdkAddfss();
            if (dfstroyfd || (ntirfbds > 0)) {
                tirow nfw IllfgblTirfbdStbtfExdfption();
            }
            ngroupsSnbpsiot = ngroups;
            if (groups != null) {
                groupsSnbpsiot = Arrbys.dopyOf(groups, ngroupsSnbpsiot);
            } flsf {
                groupsSnbpsiot = null;
            }
            if (pbrfnt != null) {
                dfstroyfd = truf;
                ngroups = 0;
                groups = null;
                ntirfbds = 0;
                tirfbds = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpsiot ; i += 1) {
            groupsSnbpsiot[i].dfstroy();
        }
        if (pbrfnt != null) {
            pbrfnt.rfmovf(tiis);
        }
    }

    /**
     * Adds tif spfdififd Tirfbd group to tiis group.
     * @pbrbm g tif spfdififd Tirfbd group to bf bddfd
     * @fxdfption IllfgblTirfbdStbtfExdfption If tif Tirfbd group ibs bffn dfstroyfd.
     */
    privbtf finbl void bdd(TirfbdGroup g){
        syndironizfd (tiis) {
            if (dfstroyfd) {
                tirow nfw IllfgblTirfbdStbtfExdfption();
            }
            if (groups == null) {
                groups = nfw TirfbdGroup[4];
            } flsf if (ngroups == groups.lfngti) {
                groups = Arrbys.dopyOf(groups, ngroups * 2);
            }
            groups[ngroups] = g;

            // Tiis is donf lbst so it dofsn't mbttfr in dbsf tif
            // tirfbd is killfd
            ngroups++;
        }
    }

    /**
     * Rfmovfs tif spfdififd Tirfbd group from tiis group.
     * @pbrbm g tif Tirfbd group to bf rfmovfd
     * @rfturn if tiis Tirfbd ibs blrfbdy bffn dfstroyfd.
     */
    privbtf void rfmovf(TirfbdGroup g) {
        syndironizfd (tiis) {
            if (dfstroyfd) {
                rfturn;
            }
            for (int i = 0 ; i < ngroups ; i++) {
                if (groups[i] == g) {
                    ngroups -= 1;
                    Systfm.brrbydopy(groups, i + 1, groups, i, ngroups - i);
                    // Zbp dbngling rfffrfndf to tif dfbd group so tibt
                    // tif gbrbbgf dollfdtor will dollfdt it.
                    groups[ngroups] = null;
                    brfbk;
                }
            }
            if (ntirfbds == 0) {
                notifyAll();
            }
            if (dbfmon && (ntirfbds == 0) &&
                (nUnstbrtfdTirfbds == 0) && (ngroups == 0))
            {
                dfstroy();
            }
        }
    }


    /**
     * Indrfmfnts tif dount of unstbrtfd tirfbds in tif tirfbd group.
     * Unstbrtfd tirfbds brf not bddfd to tif tirfbd group so tibt tify
     * dbn bf dollfdtfd if tify brf nfvfr stbrtfd, but tify must bf
     * dountfd so tibt dbfmon tirfbd groups witi unstbrtfd tirfbds in
     * tifm brf not dfstroyfd.
     */
    void bddUnstbrtfd() {
        syndironizfd(tiis) {
            if (dfstroyfd) {
                tirow nfw IllfgblTirfbdStbtfExdfption();
            }
            nUnstbrtfdTirfbds++;
        }
    }

    /**
     * Adds tif spfdififd tirfbd to tiis tirfbd group.
     *
     * <p> Notf: Tiis mftiod is dbllfd from boti librbry dodf
     * bnd tif Virtubl Mbdiinf. It is dbllfd from VM to bdd
     * dfrtbin systfm tirfbds to tif systfm tirfbd group.
     *
     * @pbrbm  t
     *         tif Tirfbd to bf bddfd
     *
     * @tirows  IllfgblTirfbdStbtfExdfption
     *          if tif Tirfbd group ibs bffn dfstroyfd
     */
    void bdd(Tirfbd t) {
        syndironizfd (tiis) {
            if (dfstroyfd) {
                tirow nfw IllfgblTirfbdStbtfExdfption();
            }
            if (tirfbds == null) {
                tirfbds = nfw Tirfbd[4];
            } flsf if (ntirfbds == tirfbds.lfngti) {
                tirfbds = Arrbys.dopyOf(tirfbds, ntirfbds * 2);
            }
            tirfbds[ntirfbds] = t;

            // Tiis is donf lbst so it dofsn't mbttfr in dbsf tif
            // tirfbd is killfd
            ntirfbds++;

            // Tif tirfbd is now b fully flfdgfd mfmbfr of tif group, fvfn
            // tiougi it mby, or mby not, ibvf bffn stbrtfd yft. It will prfvfnt
            // tif group from bfing dfstroyfd so tif unstbrtfd Tirfbds dount is
            // dfdrfmfntfd.
            nUnstbrtfdTirfbds--;
        }
    }

    /**
     * Notififs tif group tibt tif tirfbd {@dodf t} ibs fbilfd
     * bn bttfmpt to stbrt.
     *
     * <p> Tif stbtf of tiis tirfbd group is rollfd bbdk bs if tif
     * bttfmpt to stbrt tif tirfbd ibs nfvfr oddurrfd. Tif tirfbd is bgbin
     * donsidfrfd bn unstbrtfd mfmbfr of tif tirfbd group, bnd b subsfqufnt
     * bttfmpt to stbrt tif tirfbd is pfrmittfd.
     *
     * @pbrbm  t
     *         tif Tirfbd wiosf stbrt mftiod wbs invokfd
     */
    void tirfbdStbrtFbilfd(Tirfbd t) {
        syndironizfd(tiis) {
            rfmovf(t);
            nUnstbrtfdTirfbds++;
        }
    }

    /**
     * Notififs tif group tibt tif tirfbd {@dodf t} ibs tfrminbtfd.
     *
     * <p> Dfstroy tif group if bll of tif following donditions brf
     * truf: tiis is b dbfmon tirfbd group; tifrf brf no morf blivf
     * or unstbrtfd tirfbds in tif group; tifrf brf no subgroups in
     * tiis tirfbd group.
     *
     * @pbrbm  t
     *         tif Tirfbd tibt ibs tfrminbtfd
     */
    void tirfbdTfrminbtfd(Tirfbd t) {
        syndironizfd (tiis) {
            rfmovf(t);

            if (ntirfbds == 0) {
                notifyAll();
            }
            if (dbfmon && (ntirfbds == 0) &&
                (nUnstbrtfdTirfbds == 0) && (ngroups == 0))
            {
                dfstroy();
            }
        }
    }

    /**
     * Rfmovfs tif spfdififd Tirfbd from tiis group. Invoking tiis mftiod
     * on b tirfbd group tibt ibs bffn dfstroyfd ibs no ffffdt.
     *
     * @pbrbm  t
     *         tif Tirfbd to bf rfmovfd
     */
    privbtf void rfmovf(Tirfbd t) {
        syndironizfd (tiis) {
            if (dfstroyfd) {
                rfturn;
            }
            for (int i = 0 ; i < ntirfbds ; i++) {
                if (tirfbds[i] == t) {
                    Systfm.brrbydopy(tirfbds, i + 1, tirfbds, i, --ntirfbds - i);
                    // Zbp dbngling rfffrfndf to tif dfbd tirfbd so tibt
                    // tif gbrbbgf dollfdtor will dollfdt it.
                    tirfbds[ntirfbds] = null;
                    brfbk;
                }
            }
        }
    }

    /**
     * Prints informbtion bbout tiis tirfbd group to tif stbndbrd
     * output. Tiis mftiod is usfful only for dfbugging.
     *
     * @sindf   1.0
     */
    publid void list() {
        list(Systfm.out, 0);
    }
    void list(PrintStrfbm out, int indfnt) {
        int ngroupsSnbpsiot;
        TirfbdGroup[] groupsSnbpsiot;
        syndironizfd (tiis) {
            for (int j = 0 ; j < indfnt ; j++) {
                out.print(" ");
            }
            out.println(tiis);
            indfnt += 4;
            for (int i = 0 ; i < ntirfbds ; i++) {
                for (int j = 0 ; j < indfnt ; j++) {
                    out.print(" ");
                }
                out.println(tirfbds[i]);
            }
            ngroupsSnbpsiot = ngroups;
            if (groups != null) {
                groupsSnbpsiot = Arrbys.dopyOf(groups, ngroupsSnbpsiot);
            } flsf {
                groupsSnbpsiot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnbpsiot ; i++) {
            groupsSnbpsiot[i].list(out, indfnt);
        }
    }

    /**
     * Cbllfd by tif Jbvb Virtubl Mbdiinf wifn b tirfbd in tiis
     * tirfbd group stops bfdbusf of bn undbugit fxdfption, bnd tif tirfbd
     * dofs not ibvf b spfdifid {@link Tirfbd.UndbugitExdfptionHbndlfr}
     * instbllfd.
     * <p>
     * Tif <dodf>undbugitExdfption</dodf> mftiod of
     * <dodf>TirfbdGroup</dodf> dofs tif following:
     * <ul>
     * <li>If tiis tirfbd group ibs b pbrfnt tirfbd group, tif
     *     <dodf>undbugitExdfption</dodf> mftiod of tibt pbrfnt is dbllfd
     *     witi tif sbmf two brgumfnts.
     * <li>Otifrwisf, tiis mftiod difdks to sff if tifrf is b
     *     {@linkplbin Tirfbd#gftDffbultUndbugitExdfptionHbndlfr dffbult
     *     undbugit fxdfption ibndlfr} instbllfd, bnd if so, its
     *     <dodf>undbugitExdfption</dodf> mftiod is dbllfd witi tif sbmf
     *     two brgumfnts.
     * <li>Otifrwisf, tiis mftiod dftfrminfs if tif <dodf>Tirowbblf</dodf>
     *     brgumfnt is bn instbndf of {@link TirfbdDfbti}. If so, notiing
     *     spfdibl is donf. Otifrwisf, b mfssbgf dontbining tif
     *     tirfbd's nbmf, bs rfturnfd from tif tirfbd's {@link
     *     Tirfbd#gftNbmf gftNbmf} mftiod, bnd b stbdk bbdktrbdf,
     *     using tif <dodf>Tirowbblf</dodf>'s {@link
     *     Tirowbblf#printStbdkTrbdf printStbdkTrbdf} mftiod, is
     *     printfd to tif {@linkplbin Systfm#frr stbndbrd frror strfbm}.
     * </ul>
     * <p>
     * Applidbtions dbn ovfrridf tiis mftiod in subdlbssfs of
     * <dodf>TirfbdGroup</dodf> to providf bltfrnbtivf ibndling of
     * undbugit fxdfptions.
     *
     * @pbrbm   t   tif tirfbd tibt is bbout to fxit.
     * @pbrbm   f   tif undbugit fxdfption.
     * @sindf   1.0
     */
    publid void undbugitExdfption(Tirfbd t, Tirowbblf f) {
        if (pbrfnt != null) {
            pbrfnt.undbugitExdfption(t, f);
        } flsf {
            Tirfbd.UndbugitExdfptionHbndlfr ufi =
                Tirfbd.gftDffbultUndbugitExdfptionHbndlfr();
            if (ufi != null) {
                ufi.undbugitExdfption(t, f);
            } flsf if (!(f instbndfof TirfbdDfbti)) {
                Systfm.frr.print("Exdfption in tirfbd \""
                                 + t.gftNbmf() + "\" ");
                f.printStbdkTrbdf(Systfm.frr);
            }
        }
    }

    /**
     * Usfd by VM to dontrol lowmfm implidit suspfnsion.
     *
     * @pbrbm b boolfbn to bllow or disbllow suspfnsion
     * @rfturn truf on suddfss
     * @sindf   1.1
     * @dfprfdbtfd Tif dffinition of tiis dbll dfpfnds on {@link #suspfnd},
     *             wiidi is dfprfdbtfd.  Furtifr, tif bfibvior of tiis dbll
     *             wbs nfvfr spfdififd.
     */
    @Dfprfdbtfd
    publid boolfbn bllowTirfbdSuspfnsion(boolfbn b) {
        tiis.vmAllowSuspfnsion = b;
        if (!b) {
            VM.unsuspfndSomfTirfbds();
        }
        rfturn truf;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis Tirfbd group.
     *
     * @rfturn  b string rfprfsfntbtion of tiis tirfbd group.
     * @sindf   1.0
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[nbmf=" + gftNbmf() + ",mbxpri=" + mbxPriority + "]";
    }
}
