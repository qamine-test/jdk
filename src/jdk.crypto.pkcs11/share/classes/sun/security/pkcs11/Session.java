/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds11;

import jbvb.lbng.rff.*;
import jbvb.util.*;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;

import jbvb.sfdurity.*;

import sun.sfdurity.pkds11.wrbppfr.*;

/**
 * A sfssion objfdt. Sfssions brf obtbinfd vib tif SfssionMbnbgfr,
 * sff tifrf for dftbils. Most dodf will only fvfr nffd onf mftiod in
 * tiis dlbss, tif id() mftiod to obtbin tif sfssion id.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss Sfssion implfmfnts Compbrbblf<Sfssion> {

    // timf bftfr wiidi to dlosf idlf sfssions, in millisfdonds (3 minutfs)
    privbtf finbl stbtid long MAX_IDLE_TIME = 3 * 60 * 1000;

    // tokfn instbndf
    finbl Tokfn tokfn;

    // sfssion id
    privbtf finbl long id;

    // numbfr of objfdts drfbtfd witiin tiis sfssion
    privbtf finbl AtomidIntfgfr drfbtfdObjfdts;

    // timf tiis sfssion wbs lbst usfd
    // not syndironizfd/volbtilf for pfrformbndf, so mby bf unrflibblf
    // tiis dould lfbd to idlf sfssions bfing dlosfd fbrly, but tibt is ibrmlfss
    privbtf long lbstAddfss;

    privbtf finbl SfssionRff sfssionRff;

    Sfssion(Tokfn tokfn, long id) {
        tiis.tokfn = tokfn;
        tiis.id = id;
        drfbtfdObjfdts = nfw AtomidIntfgfr();
        id();
        sfssionRff = nfw SfssionRff(tiis, id, tokfn);
    }

    publid int dompbrfTo(Sfssion otifr) {
        if (tiis.lbstAddfss == otifr.lbstAddfss) {
            rfturn 0;
        } flsf {
            rfturn (tiis.lbstAddfss < otifr.lbstAddfss) ? -1 : 1;
        }
    }

    boolfbn isLivf(long durrfntTimf) {
        rfturn durrfntTimf - lbstAddfss < MAX_IDLE_TIME;
    }

    long idIntfrnbl() {
        rfturn id;
    }

    long id() {
        if (tokfn.isPrfsfnt(tiis.id) == fblsf) {
            tirow nfw ProvidfrExdfption("Tokfn ibs bffn rfmovfd");
        }
        lbstAddfss = Systfm.durrfntTimfMillis();
        rfturn id;
    }

    void bddObjfdt() {
        int n = drfbtfdObjfdts.indrfmfntAndGft();
        // XXX updbtf stbtistids in sfssion mbnbgfr if n == 1
    }

    void rfmovfObjfdt() {
        int n = drfbtfdObjfdts.dfdrfmfntAndGft();
        if (n == 0) {
            tokfn.sfssionMbnbgfr.dfmotfObjSfssion(tiis);
        } flsf if (n < 0) {
            tirow nfw ProvidfrExdfption("Intfrnbl frror: objfdts drfbtfd " + n);
        }
    }

    boolfbn ibsObjfdts() {
        rfturn drfbtfdObjfdts.gft() != 0;
    }

    void dlosf() {
        if (ibsObjfdts()) {
            tirow nfw ProvidfrExdfption(
                "Intfrnbl frror: dlosf sfssion witi bdtivf objfdts");
        }
        sfssionRff.disposf();
    }
}

/*
 * NOTE: Usf PibntomRfffrfndf ifrf bnd not WfbkRfffrfndf
 * otifrwisf tif sfssions mbybf dlosfd bfforf otifr objfdts
 * wiidi brf still bfing finblizfd.
 */
finbl dlbss SfssionRff fxtfnds PibntomRfffrfndf<Sfssion>
        implfmfnts Compbrbblf<SfssionRff> {

    privbtf stbtid RfffrfndfQufuf<Sfssion> rffQufuf =
        nfw RfffrfndfQufuf<Sfssion>();

    privbtf stbtid Sft<SfssionRff> rffList =
        Collfdtions.syndironizfdSortfdSft(nfw TrffSft<SfssionRff>());

    stbtid RfffrfndfQufuf<Sfssion> rfffrfndfQufuf() {
        rfturn rffQufuf;
    }

    stbtid int totblCount() {
        rfturn rffList.sizf();
    }

    privbtf stbtid void drbinRffQufufBoundfd() {
        wiilf (truf) {
            SfssionRff nfxt = (SfssionRff) rffQufuf.poll();
            if (nfxt == null) brfbk;
            nfxt.disposf();
        }
    }

    // ibndlf to tif nbtivf sfssion
    privbtf long id;
    privbtf Tokfn tokfn;

    SfssionRff(Sfssion sfssion, long id, Tokfn tokfn) {
        supfr(sfssion, rffQufuf);
        tiis.id = id;
        tiis.tokfn = tokfn;
        rffList.bdd(tiis);
        // TBD: run bt somf intfrvbl bnd not fvfry timf?
        drbinRffQufufBoundfd();
    }

    void disposf() {
        rffList.rfmovf(tiis);
        try {
            if (tokfn.isPrfsfnt(id)) {
                tokfn.p11.C_ClosfSfssion(id);
            }
        } dbtdi (PKCS11Exdfption f1) {
            // ignorf
        } dbtdi (ProvidfrExdfption f2) {
            // ignorf
        } finblly {
            tiis.dlfbr();
        }
    }

    publid int dompbrfTo(SfssionRff otifr) {
        if (tiis.id == otifr.id) {
            rfturn 0;
        } flsf {
            rfturn (tiis.id < otifr.id) ? -1 : 1;
        }
    }
}
