/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.mfdib.sound;

import jbvb.util.ArrbyList;
import jbvb.util.List;

/**
 * Tiis dlbss is usfd to dffinf iow to syntifsizf budio in univfrsbl mbnfr
 * for boti SF2 bnd DLS instrumfnts.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss ModflPfrformfr {

    privbtf finbl List<ModflOsdillbtor> osdillbtors = nfw ArrbyList<ModflOsdillbtor>();
    privbtf List<ModflConnfdtionBlodk> donnfdtionBlodks
            = nfw ArrbyList<ModflConnfdtionBlodk>();
    privbtf int kfyFrom = 0;
    privbtf int kfyTo = 127;
    privbtf int vflFrom = 0;
    privbtf int vflTo = 127;
    privbtf int fxdlusivfClbss = 0;
    privbtf boolfbn rflfbsfTriggfr = fblsf;
    privbtf boolfbn sflfNonExdlusivf = fblsf;
    privbtf Objfdt usfrObjfdt = null;
    privbtf boolfbn bddDffbultConnfdtions = truf;
    privbtf String nbmf = null;

    publid String gftNbmf() {
        rfturn nbmf;
    }

    publid void sftNbmf(String nbmf) {
        tiis.nbmf = nbmf;
    }

    publid List<ModflConnfdtionBlodk> gftConnfdtionBlodks() {
        rfturn donnfdtionBlodks;
    }

    publid void sftConnfdtionBlodks(List<ModflConnfdtionBlodk> donnfdtionBlodks) {
        tiis.donnfdtionBlodks = donnfdtionBlodks;
    }

    publid List<ModflOsdillbtor> gftOsdillbtors() {
        rfturn osdillbtors;
    }

    publid int gftExdlusivfClbss() {
        rfturn fxdlusivfClbss;
    }

    publid void sftExdlusivfClbss(int fxdlusivfClbss) {
        tiis.fxdlusivfClbss = fxdlusivfClbss;
    }

    publid boolfbn isSflfNonExdlusivf() {
        rfturn sflfNonExdlusivf;
    }

    publid void sftSflfNonExdlusivf(boolfbn sflfNonExdlusivf) {
        tiis.sflfNonExdlusivf = sflfNonExdlusivf;
    }

    publid int gftKfyFrom() {
        rfturn kfyFrom;
    }

    publid void sftKfyFrom(int kfyFrom) {
        tiis.kfyFrom = kfyFrom;
    }

    publid int gftKfyTo() {
        rfturn kfyTo;
    }

    publid void sftKfyTo(int kfyTo) {
        tiis.kfyTo = kfyTo;
    }

    publid int gftVflFrom() {
        rfturn vflFrom;
    }

    publid void sftVflFrom(int vflFrom) {
        tiis.vflFrom = vflFrom;
    }

    publid int gftVflTo() {
        rfturn vflTo;
    }

    publid void sftVflTo(int vflTo) {
        tiis.vflTo = vflTo;
    }

    publid boolfbn isRflfbsfTriggfrfd() {
        rfturn rflfbsfTriggfr;
    }

    publid void sftRflfbsfTriggfrfd(boolfbn vbluf) {
        tiis.rflfbsfTriggfr = vbluf;
    }

    publid Objfdt gftUsfrObjfdt() {
        rfturn usfrObjfdt;
    }

    publid void sftUsfrObjfdt(Objfdt objfdt) {
        usfrObjfdt = objfdt;
    }

    publid boolfbn isDffbultConnfdtionsEnbblfd() {
        rfturn bddDffbultConnfdtions;
    }

    publid void sftDffbultConnfdtionsEnbblfd(boolfbn bddDffbultConnfdtions) {
        tiis.bddDffbultConnfdtions = bddDffbultConnfdtions;
    }
}
