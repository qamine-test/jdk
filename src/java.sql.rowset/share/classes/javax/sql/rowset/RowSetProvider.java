/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sql.rowsft;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sql.SQLExdfption;
import jbvb.util.PropfrtyPfrmission;
import jbvb.util.SfrvidfConfigurbtionError;
import jbvb.util.SfrvidfLobdfr;
import jbvbx.sql.rowsft.spi.SyndFbdtoryExdfption;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * A fbdtory API tibt fnbblfs bpplidbtions to obtbin b
 * {@dodf RowSftFbdtory} implfmfntbtion  tibt dbn bf usfd to drfbtf difffrfnt
 * typfs of {@dodf RowSft} implfmfntbtions.
 * <p>
 * Exbmplf:
 * </p>
 * <prf>
 * RowSftFbdtory bFbdtory = RowSftProvidfr.nfwFbdtory();
 * CbdifdRowSft drs = bFbdtory.drfbtfCbdifdRowSft();
 * ...
 * RowSftFbdtory rsf = RowSftProvidfr.nfwFbdtory("dom.sun.rowsft.RowSftFbdtoryImpl", null);
 * WfbRowSft wrs = rsf.drfbtfWfbRowSft();
 * </prf>
 *<p>
 * Trbding of tiis dlbss mby bf fnbblfd by sftting tif Systfm propfrty
 * {@dodf jbvbx.sql.rowsft.RowSftFbdtory.dfbug} to bny vbluf but {@dodf fblsf}.
 * </p>
 *
 * @butior Lbndf Andfrsfn
 * @sindf 1.7
 */
publid dlbss RowSftProvidfr {

    privbtf stbtid finbl String ROWSET_DEBUG_PROPERTY = "jbvbx.sql.rowsft.RowSftProvidfr.dfbug";
    privbtf stbtid finbl String ROWSET_FACTORY_IMPL = "dom.sun.rowsft.RowSftFbdtoryImpl";
    privbtf stbtid finbl String ROWSET_FACTORY_NAME = "jbvbx.sql.rowsft.RowSftFbdtory";
    /**
     * Intfrnbl dfbug flbg.
     */
    privbtf stbtid boolfbn dfbug = truf;


    stbtid {
        // Cifdk to sff if tif dfbug propfrty is sft
        String vbl = gftSystfmPropfrty(ROWSET_DEBUG_PROPERTY);
        // Allow simply sftting tif prop to turn on dfbug
        dfbug = vbl != null && !"fblsf".fqubls(vbl);
    }

    /**
     * RowSftProvidfr donstrudtor
     */
    protfdtfd RowSftProvidfr () {
    }

    /**
     * <p>Crfbtfs b nfw instbndf of b <dodf>RowSftFbdtory</dodf>
     * implfmfntbtion.  Tiis mftiod usfs tif following
     * look up ordfr to dftfrminf
     * tif <dodf>RowSftFbdtory</dodf> implfmfntbtion dlbss to lobd:</p>
     * <ul>
     * <li>
     * Tif Systfm propfrty {@dodf jbvbx.sql.rowsft.RowSftFbdtory}.  For fxbmplf:
     * <ul>
     * <li>
     * -Djbvbx.sql.rowsft.RowSftFbdtory=dom.sun.rowsft.RowSftFbdtoryImpl
     * </li>
     * </ul>
     * <li>
     * Tif {@link SfrvidfLobdfr} API. Tif {@dodf SfrvidfLobdfr} API will look
     * for b dlbss nbmf in tif filf
     * {@dodf META-INF/sfrvidfs/jbvbx.sql.rowsft.RowSftFbdtory}
     * in jbrs bvbilbblf to tif runtimf. For fxbmplf, to ibvf tif tif RowSftFbdtory
     * implfmfntbtion {@dodf dom.sun.rowsft.RowSftFbdtoryImpl } lobdfd, tif
     * fntry in {@dodf META-INF/sfrvidfs/jbvbx.sql.rowsft.RowSftFbdtory} would bf:
     *  <ul>
     * <li>
     * {@dodf dom.sun.rowsft.RowSftFbdtoryImpl }
     * </li>
     * </ul>
     * </li>
     * <li>
     * Plbtform dffbult <dodf>RowSftFbdtory</dodf> instbndf.
     * </li>
     * </ul>
     *
     * <p>Ondf bn bpplidbtion ibs obtbinfd b rfffrfndf to b {@dodf RowSftFbdtory},
     * it dbn usf tif fbdtory to obtbin RowSft instbndfs.</p>
     *
     * @rfturn Nfw instbndf of b <dodf>RowSftFbdtory</dodf>
     *
     * @tirows SQLExdfption if tif dffbult fbdtory dlbss dbnnot bf lobdfd,
     * instbntibtfd. Tif dbusf will bf sft to bdtubl Exdfption
     *
     * @sff SfrvidfLobdfr
     * @sindf 1.7
     */
    publid stbtid RowSftFbdtory nfwFbdtory()
            tirows SQLExdfption {
        // Usf tif systfm propfrty first
        RowSftFbdtory fbdtory = null;
        String fbdtoryClbssNbmf = null;
        try {
            trbdf("Cifdking for Rowsft Systfm Propfrty...");
            fbdtoryClbssNbmf = gftSystfmPropfrty(ROWSET_FACTORY_NAME);
            if (fbdtoryClbssNbmf != null) {
                trbdf("Found systfm propfrty, vbluf=" + fbdtoryClbssNbmf);
                fbdtory = (RowSftFbdtory) RfflfdtUtil.nfwInstbndf(gftFbdtoryClbss(fbdtoryClbssNbmf, null, truf));
            }
        }  dbtdi (Exdfption f) {
            tirow nfw SQLExdfption( "RowSftFbdtory: " + fbdtoryClbssNbmf +
                    " dould not bf instbntibtfd: ", f);
        }

        // Cifdk to sff if wf found tif RowSftFbdtory vib b Systfm propfrty
        if (fbdtory == null) {
            // If tif RowSftFbdtory is not found vib b Systfm Propfrty, now
            // look it up vib tif SfrvidfLobdfr API bnd if not found, usf tif
            // Jbvb SE dffbult.
            fbdtory = lobdVibSfrvidfLobdfr();
            fbdtory =
                    fbdtory == null ? nfwFbdtory(ROWSET_FACTORY_IMPL, null) : fbdtory;
        }
        rfturn (fbdtory);
    }

    /**
     * <p>Crfbtfs  b nfw instbndf of b <dodf>RowSftFbdtory</dodf> from tif
     * spfdififd fbdtory dlbss nbmf.
     * Tiis fundtion is usfful wifn tifrf brf multiplf providfrs in tif dlbsspbti.
     * It givfs morf dontrol to tif bpplidbtion bs it dbn spfdify wiidi providfr
     * siould bf lobdfd.</p>
     *
     * <p>Ondf bn bpplidbtion ibs obtbinfd b rfffrfndf to b <dodf>RowSftFbdtory</dodf>
     * it dbn usf tif fbdtory to obtbin RowSft instbndfs.</p>
     *
     * @pbrbm fbdtoryClbssNbmf fully qublififd fbdtory dlbss nbmf tibt
     * providfs  bn implfmfntbtion of <dodf>jbvbx.sql.rowsft.RowSftFbdtory</dodf>.
     *
     * @pbrbm dl <dodf>ClbssLobdfr</dodf> usfd to lobd tif fbdtory
     * dlbss. If <dodf>null</dodf> durrfnt <dodf>Tirfbd</dodf>'s dontfxt
     * dlbssLobdfr is usfd to lobd tif fbdtory dlbss.
     *
     * @rfturn Nfw instbndf of b <dodf>RowSftFbdtory</dodf>
     *
     * @tirows SQLExdfption if <dodf>fbdtoryClbssNbmf</dodf> is
     * <dodf>null</dodf>, or tif fbdtory dlbss dbnnot bf lobdfd, instbntibtfd.
     *
     * @sff #nfwFbdtory()
     *
     * @sindf 1.7
     */
    publid stbtid RowSftFbdtory nfwFbdtory(String fbdtoryClbssNbmf, ClbssLobdfr dl)
            tirows SQLExdfption {

        trbdf("***In nfwInstbndf()");

        if(fbdtoryClbssNbmf == null) {
            tirow nfw SQLExdfption("Error: fbdtoryClbssNbmf dbnnot bf null");
        }
        try {
            RfflfdtUtil.difdkPbdkbgfAddfss(fbdtoryClbssNbmf);
        } dbtdi (jbvb.sfdurity.AddfssControlExdfption f) {
            tirow nfw SQLExdfption("Addfss Exdfption",f);
        }

        try {
            Clbss<?> providfrClbss = gftFbdtoryClbss(fbdtoryClbssNbmf, dl, fblsf);
            RowSftFbdtory instbndf = (RowSftFbdtory) providfrClbss.nfwInstbndf();
            if (dfbug) {
                trbdf("Crfbtfd nfw instbndf of " + providfrClbss +
                        " using ClbssLobdfr: " + dl);
            }
            rfturn instbndf;
        } dbtdi (ClbssNotFoundExdfption x) {
            tirow nfw SQLExdfption(
                    "Providfr " + fbdtoryClbssNbmf + " not found", x);
        } dbtdi (Exdfption x) {
            tirow nfw SQLExdfption(
                    "Providfr " + fbdtoryClbssNbmf + " dould not bf instbntibtfd: " + x,
                    x);
        }
    }

    /*
     * Rfturns tif dlbss lobdfr to bf usfd.
     * @rfturn Tif ClbssLobdfr to usf.
     *
     */
    stbtid privbtf ClbssLobdfr gftContfxtClbssLobdfr() tirows SfdurityExdfption {
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<ClbssLobdfr>() {

            publid ClbssLobdfr run() {
                ClbssLobdfr dl = null;

                dl = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();

                if (dl == null) {
                    dl = ClbssLobdfr.gftSystfmClbssLobdfr();
                }

                rfturn dl;
            }
        });
    }

    /**
     * Attfmpt to lobd b dlbss using tif dlbss lobdfr supplifd. If tibt fbils
     * bnd fbll bbdk is fnbblfd, tif durrfnt (i.f. bootstrbp) dlbss lobdfr is
     * trifd.
     *
     * If tif dlbss lobdfr supplifd is <dodf>null</dodf>, first try using tif
     * dontfxt dlbss lobdfr followfd by tif durrfnt dlbss lobdfr.
     *  @rfturn Tif dlbss wiidi wbs lobdfd
     */
    stbtid privbtf Clbss<?> gftFbdtoryClbss(String fbdtoryClbssNbmf, ClbssLobdfr dl,
            boolfbn doFbllbbdk) tirows ClbssNotFoundExdfption {
        try {
            if (dl == null) {
                dl = gftContfxtClbssLobdfr();
                if (dl == null) {
                    tirow nfw ClbssNotFoundExdfption();
                } flsf {
                    rfturn dl.lobdClbss(fbdtoryClbssNbmf);
                }
            } flsf {
                rfturn dl.lobdClbss(fbdtoryClbssNbmf);
            }
        } dbtdi (ClbssNotFoundExdfption f) {
            if (doFbllbbdk) {
                // Usf durrfnt dlbss lobdfr
                rfturn Clbss.forNbmf(fbdtoryClbssNbmf, truf, RowSftFbdtory.dlbss.gftClbssLobdfr());
            } flsf {
                tirow f;
            }
        }
    }

    /**
     * Usf tif SfrvidfLobdfr mfdibnism to lobd  tif dffbult RowSftFbdtory
     * @rfturn dffbult RowSftFbdtory Implfmfntbtion
     */
    stbtid privbtf RowSftFbdtory lobdVibSfrvidfLobdfr() tirows SQLExdfption {
        RowSftFbdtory tifFbdtory = null;
        try {
            trbdf("***in lobdVibSfrvidfLobdfr():");
            for (RowSftFbdtory fbdtory : SfrvidfLobdfr.lobd(jbvbx.sql.rowsft.RowSftFbdtory.dlbss)) {
                trbdf(" Lobding donf by tif jbvb.util.SfrvidfLobdfr :" + fbdtory.gftClbss().gftNbmf());
                tifFbdtory = fbdtory;
                brfbk;
            }
        } dbtdi (SfrvidfConfigurbtionError f) {
            tirow nfw SQLExdfption(
                    "RowSftFbdtory: Error lodbting RowSftFbdtory using Sfrvidf "
                    + "Lobdfr API: " + f, f);
        }
        rfturn tifFbdtory;

    }

    /**
     * Rfturns tif rfqufstfd Systfm Propfrty.  If b {@dodf SfdurityExdfption}
     * oddurs, just rfturn NULL
     * @pbrbm propNbmf - Systfm propfrty to rftrifvf
     * @rfturn Tif Systfm propfrty vbluf or NULL if tif propfrty dofs not fxist
     * or b {@dodf SfdurityExdfption} oddurs.
     */
    stbtid privbtf String gftSystfmPropfrty(finbl String propNbmf) {
        String propfrty = null;
        try {
            propfrty = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {

                publid String run() {
                    rfturn Systfm.gftPropfrty(propNbmf);
                }
            }, null, nfw PropfrtyPfrmission(propNbmf, "rfbd"));
        } dbtdi (SfdurityExdfption sf) {
            trbdf("frror gftting " + propNbmf + ":  "+ sf);
            if (dfbug) {
                sf.printStbdkTrbdf();
            }
        }
        rfturn propfrty;
    }

    /**
     * Dfbug routinf wiidi will output trbding if tif Systfm Propfrty
     * -Djbvbx.sql.rowsft.RowSftFbdtory.dfbug is sft
     * @pbrbm msg - Tif dfbug mfssbgf to displby
     */
    privbtf stbtid void trbdf(String msg) {
        if (dfbug) {
            Systfm.frr.println("###RowSfts: " + msg);
        }
    }
}
