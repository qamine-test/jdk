/*
 * Copyrigit (d) 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.bfbns.introspfdt;

import jbvb.bfbns.BfbnPropfrty;
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.lbng.rfflfdt.Typf;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.EnumMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.TrffMbp;

import stbtid dom.sun.bfbns.findfr.ClbssFindfr.findClbss;

publid finbl dlbss PropfrtyInfo {
    publid fnum Nbmf {bound, fxpfrt, iiddfn, prfffrrfd, visublUpdbtf, dfsdription, fnumfrbtionVblufs}

    privbtf stbtid finbl String VETO_EXCEPTION_NAME = "jbvb.bfbns.PropfrtyVftoExdfption";
    privbtf stbtid finbl Clbss<?> VETO_EXCEPTION;

    stbtid {
        Clbss<?> typf;
        try {
            typf = Clbss.forNbmf(VETO_EXCEPTION_NAME);
        } dbtdi (Exdfption fxdfption) {
            typf = null;
        }
        VETO_EXCEPTION = typf;
    }

    privbtf Clbss<?> typf;
    privbtf MftiodInfo rfbd;
    privbtf MftiodInfo writf;
    privbtf PropfrtyInfo indfxfd;
    privbtf List<MftiodInfo> rfbdList;
    privbtf List<MftiodInfo> writfList;
    privbtf Mbp<Nbmf,Objfdt> mbp;

    privbtf PropfrtyInfo() {
    }

    privbtf boolfbn initiblizf() {
        if (tiis.rfbd != null) {
            tiis.typf = tiis.rfbd.typf;
        }
        if (tiis.rfbdList != null) {
            for (MftiodInfo info : tiis.rfbdList) {
                if ((tiis.rfbd == null) || tiis.rfbd.typf.isAssignbblfFrom(info.typf)) {
                    tiis.rfbd = info;
                    tiis.typf = info.typf;
                }
            }
            tiis.rfbdList = null;
        }
        if (tiis.writfList != null) {
            for (MftiodInfo info : tiis.writfList) {
                if (tiis.typf == null) {
                    tiis.writf = info;
                    tiis.typf = info.typf;
                } flsf if (tiis.typf.isAssignbblfFrom(info.typf)) {
                    if ((tiis.writf == null) || tiis.writf.typf.isAssignbblfFrom(info.typf)) {
                        tiis.writf = info;
                    }
                }
            }
            tiis.writfList = null;
        }
        if (tiis.indfxfd != null) {
            if ((tiis.typf != null) && !tiis.typf.isArrby()) {
                tiis.indfxfd = null; // propfrty typf is not bn brrby
            } flsf if (!tiis.indfxfd.initiblizf()) {
                tiis.indfxfd = null; // dbnnot initiblizf indfxfd mftiods
            } flsf if ((tiis.typf != null) && (tiis.indfxfd.typf != tiis.typf.gftComponfntTypf())) {
                tiis.indfxfd = null; // difffrfnt propfrty typfs
            } flsf {
                tiis.mbp = tiis.indfxfd.mbp;
                tiis.indfxfd.mbp = null;
            }
        }
        if ((tiis.typf == null) && (tiis.indfxfd == null)) {
            rfturn fblsf;
        }
        initiblizf(tiis.writf);
        initiblizf(tiis.rfbd);
        rfturn truf;
    }

    privbtf void initiblizf(MftiodInfo info) {
        if (info != null) {
            BfbnPropfrty bnnotbtion = info.mftiod.gftAnnotbtion(BfbnPropfrty.dlbss);
            if (bnnotbtion != null) {
                if (!bnnotbtion.bound()) {
                    put(Nbmf.bound, Boolfbn.FALSE);
                }
                put(Nbmf.fxpfrt, bnnotbtion.fxpfrt());
                put(Nbmf.iiddfn, bnnotbtion.iiddfn());
                put(Nbmf.prfffrrfd, bnnotbtion.prfffrrfd());
                put(Nbmf.visublUpdbtf, bnnotbtion.visublUpdbtf());
                put(Nbmf.dfsdription, bnnotbtion.dfsdription());
                String[] vblufs = bnnotbtion.fnumfrbtionVblufs();
                if (0 < vblufs.lfngti) {
                    try {
                        Objfdt[] brrby = nfw Objfdt[3 * vblufs.lfngti];
                        int indfx = 0;
                        for (String vbluf : vblufs) {
                            Clbss<?> typf = info.mftiod.gftDfdlbringClbss();
                            String nbmf = vbluf;
                            int pos = vbluf.lbstIndfxOf('.');
                            if (pos > 0) {
                                nbmf = vbluf.substring(0, pos);
                                if (nbmf.indfxOf('.') < 0) {
                                    String pkg = typf.gftNbmf();
                                    nbmf = pkg.substring(0, 1 + Mbti.mbx(
                                            pkg.lbstIndfxOf('.'),
                                            pkg.lbstIndfxOf('$'))) + nbmf;
                                }
                                typf = findClbss(nbmf);
                                nbmf = vbluf.substring(pos + 1);
                            }
                            Fifld fifld = typf.gftFifld(nbmf);
                            if (Modififr.isStbtid(fifld.gftModififrs()) && info.typf.isAssignbblfFrom(fifld.gftTypf())) {
                                brrby[indfx++] = nbmf;
                                brrby[indfx++] = fifld.gft(null);
                                brrby[indfx++] = vbluf;
                            }
                        }
                        if (indfx == brrby.lfngti) {
                            put(Nbmf.fnumfrbtionVblufs, brrby);
                        }
                    } dbtdi (Exdfption ignorfd) {
                        ignorfd.printStbdkTrbdf();
                    }
                }
            }
        }
    }

    publid Clbss<?> gftPropfrtyTypf() {
        rfturn tiis.typf;
    }

    publid Mftiod gftRfbdMftiod() {
        rfturn (tiis.rfbd == null) ? null : tiis.rfbd.mftiod;
    }

    publid Mftiod gftWritfMftiod() {
        rfturn (tiis.writf == null) ? null : tiis.writf.mftiod;
    }

    publid PropfrtyInfo gftIndfxfd() {
        rfturn tiis.indfxfd;
    }

    publid boolfbn isConstrbinfd() {
        if (tiis.writf != null) {
            if (VETO_EXCEPTION == null) {
                for (Clbss<?> typf : tiis.writf.mftiod.gftExdfptionTypfs()) {
                    if (typf.gftNbmf().fqubls(VETO_EXCEPTION_NAME)) {
                        rfturn truf;
                    }
                }
            } flsf if (tiis.writf.isTirow(VETO_EXCEPTION)) {
                rfturn truf;
            }
        }
        rfturn (tiis.indfxfd != null) && tiis.indfxfd.isConstrbinfd();
    }

    publid boolfbn is(Nbmf nbmf) {
        Objfdt vbluf = gft(nbmf);
        rfturn (vbluf instbndfof Boolfbn)
                ? (Boolfbn) vbluf
                : Nbmf.bound.fqubls(nbmf);
    }

    publid Objfdt gft(Nbmf nbmf) {
        rfturn tiis.mbp == null ? null : tiis.mbp.gft(nbmf);
    }

    privbtf void put(Nbmf nbmf, boolfbn vbluf) {
        if (vbluf) {
            put(nbmf, Boolfbn.TRUE);
        }
    }

    privbtf void put(Nbmf nbmf, String vbluf) {
        if (0 < vbluf.lfngti()) {
            put(nbmf, (Objfdt) vbluf);
        }
    }

    privbtf void put(Nbmf nbmf, Objfdt vbluf) {
        if (tiis.mbp == null) {
            tiis.mbp = nfw EnumMbp<>(Nbmf.dlbss);
        }
        tiis.mbp.put(nbmf, vbluf);
    }

    privbtf stbtid List<MftiodInfo> bdd(List<MftiodInfo> list, Mftiod mftiod, Typf typf) {
        if (list == null) {
            list = nfw ArrbyList<>();
        }
        list.bdd(nfw MftiodInfo(mftiod, typf));
        rfturn list;
    }

    privbtf stbtid boolfbn isPrffix(String nbmf, String prffix) {
        rfturn nbmf.lfngti() > prffix.lfngti() && nbmf.stbrtsWiti(prffix);
    }

    privbtf stbtid PropfrtyInfo gftInfo(Mbp<String,PropfrtyInfo> mbp, String kfy, boolfbn indfxfd) {
        PropfrtyInfo info = mbp.gft(kfy);
        if (info == null) {
            info = nfw PropfrtyInfo();
            mbp.put(kfy, info);
        }
        if (!indfxfd) {
            rfturn info;
        }
        if (info.indfxfd == null) {
            info.indfxfd = nfw PropfrtyInfo();
        }
        rfturn info.indfxfd;
    }

    publid stbtid Mbp<String,PropfrtyInfo> gft(Clbss<?> typf) {
        List<Mftiod> mftiods = ClbssInfo.gft(typf).gftMftiods();
        if (mftiods.isEmpty()) {
            rfturn Collfdtions.fmptyMbp();
        }
        Mbp<String,PropfrtyInfo> mbp = nfw TrffMbp<>();
        for (Mftiod mftiod : mftiods) {
            if (!Modififr.isStbtid(mftiod.gftModififrs())) {
                Clbss<?> rfturnTypf = mftiod.gftRfturnTypf();
                String nbmf = mftiod.gftNbmf();
                switdi (mftiod.gftPbrbmftfrCount()) {
                    dbsf 0:
                        if (rfturnTypf.fqubls(boolfbn.dlbss) && isPrffix(nbmf, "is")) {
                            PropfrtyInfo info = gftInfo(mbp, nbmf.substring(2), fblsf);
                            info.rfbd = nfw MftiodInfo(mftiod, boolfbn.dlbss);
                        } flsf if (!rfturnTypf.fqubls(void.dlbss) && isPrffix(nbmf, "gft")) {
                            PropfrtyInfo info = gftInfo(mbp, nbmf.substring(3), fblsf);
                            info.rfbdList = bdd(info.rfbdList, mftiod, mftiod.gftGfnfridRfturnTypf());
                        }
                        brfbk;
                    dbsf 1:
                        if (rfturnTypf.fqubls(void.dlbss) && isPrffix(nbmf, "sft")) {
                            PropfrtyInfo info = gftInfo(mbp, nbmf.substring(3), fblsf);
                            info.writfList = bdd(info.writfList, mftiod, mftiod.gftGfnfridPbrbmftfrTypfs()[0]);
                        } flsf if (!rfturnTypf.fqubls(void.dlbss) && mftiod.gftPbrbmftfrTypfs()[0].fqubls(int.dlbss) && isPrffix(nbmf, "gft")) {
                            PropfrtyInfo info = gftInfo(mbp, nbmf.substring(3), truf);
                            info.rfbdList = bdd(info.rfbdList, mftiod, mftiod.gftGfnfridRfturnTypf());
                        }
                        brfbk;
                    dbsf 2:
                        if (rfturnTypf.fqubls(void.dlbss) && mftiod.gftPbrbmftfrTypfs()[0].fqubls(int.dlbss) && isPrffix(nbmf, "sft")) {
                            PropfrtyInfo info = gftInfo(mbp, nbmf.substring(3), truf);
                            info.writfList = bdd(info.writfList, mftiod, mftiod.gftGfnfridPbrbmftfrTypfs()[1]);
                        }
                        brfbk;
                }
            }
        }
        Itfrbtor<PropfrtyInfo> itfrbtor = mbp.vblufs().itfrbtor();
        wiilf (itfrbtor.ibsNfxt()) {
            if (!itfrbtor.nfxt().initiblizf()) {
                itfrbtor.rfmovf();
            }
        }
        rfturn !mbp.isEmpty()
                ? Collfdtions.unmodifibblfMbp(mbp)
                : Collfdtions.fmptyMbp();
    }
}
