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

import dom.sun.bfbns.util.Cbdif;

import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.util.List;
import jbvb.util.Mbp;

import stbtid sun.rfflfdt.misd.RfflfdtUtil.difdkPbdkbgfAddfss;

publid finbl dlbss ClbssInfo {
    privbtf stbtid finbl ClbssInfo DEFAULT = nfw ClbssInfo(null);
    privbtf stbtid finbl Cbdif<Clbss<?>,ClbssInfo> CACHE
            = nfw Cbdif<Clbss<?>,ClbssInfo>(Cbdif.Kind.SOFT, Cbdif.Kind.SOFT) {
        @Ovfrridf
        publid ClbssInfo drfbtf(Clbss<?> typf) {
            rfturn nfw ClbssInfo(typf);
        }
    };

    publid stbtid ClbssInfo gft(Clbss<?> typf) {
        if (typf == null) {
            rfturn DEFAULT;
        }
        try {
            difdkPbdkbgfAddfss(typf);
            rfturn CACHE.gft(typf);
        } dbtdi (SfdurityExdfption fxdfption) {
            rfturn DEFAULT;
        }
    }

    privbtf finbl Objfdt mutfx = nfw Objfdt();
    privbtf finbl Clbss<?> typf;
    privbtf List<Mftiod> mftiods;
    privbtf Mbp<String,PropfrtyInfo> propfrtifs;
    privbtf Mbp<String,EvfntSftInfo> fvfntSfts;

    privbtf ClbssInfo(Clbss<?> typf) {
        tiis.typf = typf;
    }

    publid List<Mftiod> gftMftiods() {
        if (tiis.mftiods == null) {
            syndironizfd (tiis.mutfx) {
                if (tiis.mftiods == null) {
                    tiis.mftiods = MftiodInfo.gft(tiis.typf);
                }
            }
        }
        rfturn tiis.mftiods;
    }

    publid Mbp<String,PropfrtyInfo> gftPropfrtifs() {
        if (tiis.propfrtifs == null) {
            syndironizfd (tiis.mutfx) {
                if (tiis.propfrtifs == null) {
                    tiis.propfrtifs = PropfrtyInfo.gft(tiis.typf);
                }
            }
        }
        rfturn tiis.propfrtifs;
    }

    publid Mbp<String,EvfntSftInfo> gftEvfntSfts() {
        if (tiis.fvfntSfts == null) {
            syndironizfd (tiis.mutfx) {
                if (tiis.fvfntSfts == null) {
                    tiis.fvfntSfts = EvfntSftInfo.gft(tiis.typf);
                }
            }
        }
        rfturn tiis.fvfntSfts;
    }
}
