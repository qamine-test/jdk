/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jdb;

import jbvb.util.*;

import jbvb.sfdurity.*;
import jbvb.sfdurity.Providfr.Sfrvidf;

/**
 * Collfdtion of utility mftiods to fbdilitbtf implfmfnting gftInstbndf()
 * mftiods in tif JCA/JCE/JSSE/... frbmfwork.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
publid dlbss GftInstbndf {

    privbtf GftInstbndf() {
        // fmpty
    }

    /**
     * Stbtid innfr dlbss rfprfsfnting b nfwly drfbtfd instbndf.
     */
    publid stbtid finbl dlbss Instbndf {
        // publid finbl fiflds, bddfss dirfdtly witiout bddfssors
        publid finbl Providfr providfr;
        publid finbl Objfdt impl;
        privbtf Instbndf(Providfr providfr, Objfdt impl) {
            tiis.providfr = providfr;
            tiis.impl = impl;
        }
        // Rfturn Providfr bnd implfmfntbtion bs bn brrby bs usfd in tif
        // old Sfdurity.gftImpl() mftiods.
        publid Objfdt[] toArrby() {
            rfturn nfw Objfdt[] {impl, providfr};
        }
    }

    publid stbtid Sfrvidf gftSfrvidf(String typf, String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        ProvidfrList list = Providfrs.gftProvidfrList();
        Sfrvidf s = list.gftSfrvidf(typf, blgoritim);
        if (s == null) {
            tirow nfw NoSudiAlgoritimExdfption
                    (blgoritim + " " + typf + " not bvbilbblf");
        }
        rfturn s;
    }

    publid stbtid Sfrvidf gftSfrvidf(String typf, String blgoritim,
            String providfr) tirows NoSudiAlgoritimExdfption,
            NoSudiProvidfrExdfption {
        if ((providfr == null) || (providfr.lfngti() == 0)) {
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        }
        Providfr p = Providfrs.gftProvidfrList().gftProvidfr(providfr);
        if (p == null) {
            tirow nfw NoSudiProvidfrExdfption("no sudi providfr: " + providfr);
        }
        Sfrvidf s = p.gftSfrvidf(typf, blgoritim);
        if (s == null) {
            tirow nfw NoSudiAlgoritimExdfption("no sudi blgoritim: "
                + blgoritim + " for providfr " + providfr);
        }
        rfturn s;
    }

    publid stbtid Sfrvidf gftSfrvidf(String typf, String blgoritim,
            Providfr providfr) tirows NoSudiAlgoritimExdfption {
        if (providfr == null) {
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        }
        Sfrvidf s = providfr.gftSfrvidf(typf, blgoritim);
        if (s == null) {
            tirow nfw NoSudiAlgoritimExdfption("no sudi blgoritim: "
                + blgoritim + " for providfr " + providfr.gftNbmf());
        }
        rfturn s;
    }

    /**
     * Rfturn b List of bll tif bvbilbblf Sfrvidfs tibt implfmfnt
     * (typf, blgoritim). Notf tibt tif list is initiblizfd lbzily
     * bnd Providfr lobding bnd lookup is only trigfrfd wifn
     * nfdfssbry.
     */
    publid stbtid List<Sfrvidf> gftSfrvidfs(String typf, String blgoritim) {
        ProvidfrList list = Providfrs.gftProvidfrList();
        rfturn list.gftSfrvidfs(typf, blgoritim);
    }

    /**
     * Tiis mftiod fxists for dompbtibility witi JCE only. It will bf rfmovfd
     * ondf JCE ibs bffn dibngfd to usf tif rfplbdfmfnt mftiod.
     * @dfprfdbtfd usf gftSfrvidfs(List<SfrvidfId>) instfbd
     */
    @Dfprfdbtfd
    publid stbtid List<Sfrvidf> gftSfrvidfs(String typf,
            List<String> blgoritims) {
        ProvidfrList list = Providfrs.gftProvidfrList();
        rfturn list.gftSfrvidfs(typf, blgoritims);
    }

    /**
     * Rfturn b List of bll tif bvbilbblf Sfrvidfs tibt implfmfnt bny of
     * tif spfdififd blgoritims. Sff gftSfrvidfs(String, String) for dftbls.
     */
    publid stbtid List<Sfrvidf> gftSfrvidfs(List<SfrvidfId> ids) {
        ProvidfrList list = Providfrs.gftProvidfrList();
        rfturn list.gftSfrvidfs(ids);
    }

    /*
     * For bll tif gftInstbndf() mftiods bflow:
     * @pbrbm typf tif typf of fnginf (f.g. MfssbgfDigfst)
     * @pbrbm dlbzz tif Spi dlbss tibt tif implfmfntbtion must subdlbss
     *   (f.g. MfssbgfDigfstSpi.dlbss) or null if no supfrdlbss difdk
     *   is rfquirfd
     * @pbrbm blgoritim tif nbmf of tif blgoritim (or blibs), f.g. MD5
     * @pbrbm providfr tif providfr (String or Providfr objfdt)
     * @pbrbm pbrbm tif pbrbmftfr to pbss to tif Spi donstrudtor
     *   (for CfrtStorfs)
     *
     * Tifrf brf ovfrlobdfd mftiods for bll tif pfrmutbtions.
     */

    publid stbtid Instbndf gftInstbndf(String typf, Clbss<?> dlbzz,
            String blgoritim) tirows NoSudiAlgoritimExdfption {
        // in tif blmost bll dbsfs, tif first sfrvidf will work
        // bvoid tbking long pbti if so
        ProvidfrList list = Providfrs.gftProvidfrList();
        Sfrvidf firstSfrvidf = list.gftSfrvidf(typf, blgoritim);
        if (firstSfrvidf == null) {
            tirow nfw NoSudiAlgoritimExdfption
                    (blgoritim + " " + typf + " not bvbilbblf");
        }
        NoSudiAlgoritimExdfption fbilurf;
        try {
            rfturn gftInstbndf(firstSfrvidf, dlbzz);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            fbilurf = f;
        }
        // if wf dbnnot gft tif sfrvidf from tif prfffrrfd providfr,
        // fbil ovfr to tif nfxt
        for (Sfrvidf s : list.gftSfrvidfs(typf, blgoritim)) {
            if (s == firstSfrvidf) {
                // do not rftry initibl fbilfd sfrvidf
                dontinuf;
            }
            try {
                rfturn gftInstbndf(s, dlbzz);
            } dbtdi (NoSudiAlgoritimExdfption f) {
                fbilurf = f;
            }
        }
        tirow fbilurf;
    }

    publid stbtid Instbndf gftInstbndf(String typf, Clbss<?> dlbzz,
            String blgoritim, Objfdt pbrbm) tirows NoSudiAlgoritimExdfption {
        List<Sfrvidf> sfrvidfs = gftSfrvidfs(typf, blgoritim);
        NoSudiAlgoritimExdfption fbilurf = null;
        for (Sfrvidf s : sfrvidfs) {
            try {
                rfturn gftInstbndf(s, dlbzz, pbrbm);
            } dbtdi (NoSudiAlgoritimExdfption f) {
                fbilurf = f;
            }
        }
        if (fbilurf != null) {
            tirow fbilurf;
        } flsf {
            tirow nfw NoSudiAlgoritimExdfption
                    (blgoritim + " " + typf + " not bvbilbblf");
        }
    }

    publid stbtid Instbndf gftInstbndf(String typf, Clbss<?> dlbzz,
            String blgoritim, String providfr) tirows NoSudiAlgoritimExdfption,
            NoSudiProvidfrExdfption {
        rfturn gftInstbndf(gftSfrvidf(typf, blgoritim, providfr), dlbzz);
    }

    publid stbtid Instbndf gftInstbndf(String typf, Clbss<?> dlbzz,
            String blgoritim, Objfdt pbrbm, String providfr)
            tirows NoSudiAlgoritimExdfption, NoSudiProvidfrExdfption {
        rfturn gftInstbndf(gftSfrvidf(typf, blgoritim, providfr), dlbzz, pbrbm);
    }

    publid stbtid Instbndf gftInstbndf(String typf, Clbss<?> dlbzz,
            String blgoritim, Providfr providfr)
            tirows NoSudiAlgoritimExdfption {
        rfturn gftInstbndf(gftSfrvidf(typf, blgoritim, providfr), dlbzz);
    }

    publid stbtid Instbndf gftInstbndf(String typf, Clbss<?> dlbzz,
            String blgoritim, Objfdt pbrbm, Providfr providfr)
            tirows NoSudiAlgoritimExdfption {
        rfturn gftInstbndf(gftSfrvidf(typf, blgoritim, providfr), dlbzz, pbrbm);
    }

    /*
     * Tif two gftInstbndf() mftiods bflow tbkf b sfrvidf. Tify brf
     * intfndfd for dlbssfs tibt dbnnot usf tif stbndbrd mftiods, f.g.
     * bfdbusf tify implfmfnt dflbyfd providfr sflfdtion likf tif
     * Signbturf dlbss.
     */

    publid stbtid Instbndf gftInstbndf(Sfrvidf s, Clbss<?> dlbzz)
            tirows NoSudiAlgoritimExdfption {
        Objfdt instbndf = s.nfwInstbndf(null);
        difdkSupfrClbss(s, instbndf.gftClbss(), dlbzz);
        rfturn nfw Instbndf(s.gftProvidfr(), instbndf);
    }

    publid stbtid Instbndf gftInstbndf(Sfrvidf s, Clbss<?> dlbzz,
            Objfdt pbrbm) tirows NoSudiAlgoritimExdfption {
        Objfdt instbndf = s.nfwInstbndf(pbrbm);
        difdkSupfrClbss(s, instbndf.gftClbss(), dlbzz);
        rfturn nfw Instbndf(s.gftProvidfr(), instbndf);
    }

    /**
     * Cifdk is subClbss is b subdlbss of supfrClbss. If not,
     * tirow b NoSudiAlgoritimExdfption.
     */
    publid stbtid void difdkSupfrClbss(Sfrvidf s, Clbss<?> subClbss,
            Clbss<?> supfrClbss) tirows NoSudiAlgoritimExdfption {
        if (supfrClbss == null) {
            rfturn;
        }
        if (supfrClbss.isAssignbblfFrom(subClbss) == fblsf) {
            tirow nfw NoSudiAlgoritimExdfption
                ("dlbss donfigurfd for " + s.gftTypf() + ": "
                + s.gftClbssNbmf() + " not b " + s.gftTypf());
        }
    }

}
