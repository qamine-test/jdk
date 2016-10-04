/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.rmi.ssl;

import jbvb.io.IOExdfption;
import jbvb.io.Sfriblizbblf;
import jbvb.nft.Sodkft;
import jbvb.rmi.sfrvfr.RMIClifntSodkftFbdtory;
import jbvb.util.StringTokfnizfr;
import jbvbx.nft.SodkftFbdtory;
import jbvbx.nft.ssl.SSLSodkft;
import jbvbx.nft.ssl.SSLSodkftFbdtory;

/**
 * <p>An <dodf>SslRMIClifntSodkftFbdtory</dodf> instbndf is usfd by tif RMI
 * runtimf in ordfr to obtbin dlifnt sodkfts for RMI dblls vib SSL.</p>
 *
 * <p>Tiis dlbss implfmfnts <dodf>RMIClifntSodkftFbdtory</dodf> ovfr
 * tif Sfdurf Sodkfts Lbyfr (SSL) or Trbnsport Lbyfr Sfdurity (TLS)
 * protodols.</p>
 *
 * <p>Tiis dlbss drfbtfs SSL sodkfts using tif dffbult
 * <dodf>SSLSodkftFbdtory</dodf> (sff {@link
 * SSLSodkftFbdtory#gftDffbult}).  All instbndfs of tiis dlbss brf
 * fundtionblly fquivblfnt.  In pbrtidulbr, tify bll sibrf tif sbmf
 * truststorf, bnd tif sbmf kfystorf wifn dlifnt butifntidbtion is
 * rfquirfd by tif sfrvfr.  Tiis bfibvior dbn bf modififd in
 * subdlbssfs by ovfrriding tif {@link #drfbtfSodkft(String,int)}
 * mftiod; in tibt dbsf, {@link #fqubls(Objfdt) fqubls} bnd {@link
 * #ibsiCodf() ibsiCodf} mby blso nffd to bf ovfrriddfn.</p>
 *
 * <p>If tif systfm propfrty
 * <dodf>jbvbx.rmi.ssl.dlifnt.fnbblfdCipifrSuitfs</dodf> is spfdififd,
 * tif {@link #drfbtfSodkft(String,int)} mftiod will dbll {@link
 * SSLSodkft#sftEnbblfdCipifrSuitfs(String[])} bfforf rfturning tif
 * sodkft.  Tif vbluf of tiis systfm propfrty is b string tibt is b
 * dommb-sfpbrbtfd list of SSL/TLS dipifr suitfs to fnbblf.</p>
 *
 * <p>If tif systfm propfrty
 * <dodf>jbvbx.rmi.ssl.dlifnt.fnbblfdProtodols</dodf> is spfdififd,
 * tif {@link #drfbtfSodkft(String,int)} mftiod will dbll {@link
 * SSLSodkft#sftEnbblfdProtodols(String[])} bfforf rfturning tif
 * sodkft.  Tif vbluf of tiis systfm propfrty is b string tibt is b
 * dommb-sfpbrbtfd list of SSL/TLS protodol vfrsions to fnbblf.</p>
 *
 * @sff jbvbx.nft.ssl.SSLSodkftFbdtory
 * @sff jbvbx.rmi.ssl.SslRMISfrvfrSodkftFbdtory
 * @sindf 1.5
 */
publid dlbss SslRMIClifntSodkftFbdtory
    implfmfnts RMIClifntSodkftFbdtory, Sfriblizbblf {

    /**
     * <p>Crfbtfs b nfw <dodf>SslRMIClifntSodkftFbdtory</dodf>.</p>
     */
    publid SslRMIClifntSodkftFbdtory() {
        // Wf don't fordf tif initiblizbtion of tif dffbult SSLSodkftFbdtory
        // bt donstrudtion timf - bfdbusf tif RMI dlifnt sodkft fbdtory is
        // drfbtfd on tif sfrvfr sidf, wifrf tibt initiblizbtion is b priori
        // mfbninglfss, unlfss boti sfrvfr bnd dlifnt run in tif sbmf JVM.
        // Wf dould possibly ovfrridf rfbdObjfdt() to fordf tiis initiblizbtion,
        // but it migit not bf b good idfb to bdtublly mix tiis witi possiblf
        // dfsfriblizbtion problfms.
        // So dontrbrily to wibt wf do for tif sfrvfr sidf, tif initiblizbtion
        // of tif SSLSodkftFbdtory will bf dflbyfd until tif first timf
        // drfbtfSodkft() is dbllfd - notf tibt tif dffbult SSLSodkftFbdtory
        // migit blrfbdy ibvf bffn initiblizfd bnywby if somfonf in tif JVM
        // blrfbdy dbllfd SSLSodkftFbdtory.gftDffbult().
        //
    }

    /**
     * <p>Crfbtfs bn SSL sodkft.</p>
     *
     * <p>If tif systfm propfrty
     * <dodf>jbvbx.rmi.ssl.dlifnt.fnbblfdCipifrSuitfs</dodf> is
     * spfdififd, tiis mftiod will dbll {@link
     * SSLSodkft#sftEnbblfdCipifrSuitfs(String[])} bfforf rfturning
     * tif sodkft. Tif vbluf of tiis systfm propfrty is b string tibt
     * is b dommb-sfpbrbtfd list of SSL/TLS dipifr suitfs to
     * fnbblf.</p>
     *
     * <p>If tif systfm propfrty
     * <dodf>jbvbx.rmi.ssl.dlifnt.fnbblfdProtodols</dodf> is
     * spfdififd, tiis mftiod will dbll {@link
     * SSLSodkft#sftEnbblfdProtodols(String[])} bfforf rfturning tif
     * sodkft. Tif vbluf of tiis systfm propfrty is b string tibt is b
     * dommb-sfpbrbtfd list of SSL/TLS protodol vfrsions to
     * fnbblf.</p>
     */
    publid Sodkft drfbtfSodkft(String iost, int port) tirows IOExdfption {
        // Rftrifvf tif SSLSodkftFbdtory
        //
        finbl SodkftFbdtory sslSodkftFbdtory = gftDffbultClifntSodkftFbdtory();
        // Crfbtf tif SSLSodkft
        //
        finbl SSLSodkft sslSodkft = (SSLSodkft)
            sslSodkftFbdtory.drfbtfSodkft(iost, port);
        // Sft tif SSLSodkft Enbblfd Cipifr Suitfs
        //
        finbl String fnbblfdCipifrSuitfs =
            Systfm.gftPropfrty("jbvbx.rmi.ssl.dlifnt.fnbblfdCipifrSuitfs");
        if (fnbblfdCipifrSuitfs != null) {
            StringTokfnizfr st = nfw StringTokfnizfr(fnbblfdCipifrSuitfs, ",");
            int tokfns = st.dountTokfns();
            String fnbblfdCipifrSuitfsList[] = nfw String[tokfns];
            for (int i = 0 ; i < tokfns; i++) {
                fnbblfdCipifrSuitfsList[i] = st.nfxtTokfn();
            }
            try {
                sslSodkft.sftEnbblfdCipifrSuitfs(fnbblfdCipifrSuitfsList);
            } dbtdi (IllfgblArgumfntExdfption f) {
                tirow (IOExdfption)
                    nfw IOExdfption(f.gftMfssbgf()).initCbusf(f);
            }
        }
        // Sft tif SSLSodkft Enbblfd Protodols
        //
        finbl String fnbblfdProtodols =
            Systfm.gftPropfrty("jbvbx.rmi.ssl.dlifnt.fnbblfdProtodols");
        if (fnbblfdProtodols != null) {
            StringTokfnizfr st = nfw StringTokfnizfr(fnbblfdProtodols, ",");
            int tokfns = st.dountTokfns();
            String fnbblfdProtodolsList[] = nfw String[tokfns];
            for (int i = 0 ; i < tokfns; i++) {
                fnbblfdProtodolsList[i] = st.nfxtTokfn();
            }
            try {
                sslSodkft.sftEnbblfdProtodols(fnbblfdProtodolsList);
            } dbtdi (IllfgblArgumfntExdfption f) {
                tirow (IOExdfption)
                    nfw IOExdfption(f.gftMfssbgf()).initCbusf(f);
            }
        }
        // Rfturn tif prfdonfigurfd SSLSodkft
        //
        rfturn sslSodkft;
    }

    /**
     * <p>Indidbtfs wiftifr somf otifr objfdt is "fqubl to" tiis onf.</p>
     *
     * <p>Bfdbusf bll instbndfs of tiis dlbss brf fundtionblly fquivblfnt
     * (tify bll usf tif dffbult
     * <dodf>SSLSodkftFbdtory</dodf>), tiis mftiod simply rfturns
     * <dodf>tiis.gftClbss().fqubls(obj.gftClbss())</dodf>.</p>
     *
     * <p>A subdlbss siould ovfrridf tiis mftiod (bs wfll
     * bs {@link #ibsiCodf()}) if its instbndfs brf not bll
     * fundtionblly fquivblfnt.</p>
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == null) rfturn fblsf;
        if (obj == tiis) rfturn truf;
        rfturn tiis.gftClbss().fqubls(obj.gftClbss());
    }

    /**
     * <p>Rfturns b ibsi dodf vbluf for tiis
     * <dodf>SslRMIClifntSodkftFbdtory</dodf>.</p>
     *
     * @rfturn b ibsi dodf vbluf for tiis
     * <dodf>SslRMIClifntSodkftFbdtory</dodf>.
     */
    publid int ibsiCodf() {
        rfturn tiis.gftClbss().ibsiCodf();
    }

    // Wf usf b stbtid fifld bfdbusf:
    //
    //    SSLSodkftFbdtory.gftDffbult() blwbys rfturns tif sbmf objfdt
    //    (bt lfbst on Sun's implfmfntbtion), bnd wf wbnt to mbkf surf
    //    tibt tif Jbvbdod & tif implfmfntbtion stby in synd.
    //
    // If somfonf nffds to ibvf difffrfnt SslRMIClifntSodkftFbdtory fbdtorifs
    // witi difffrfnt undfrlying SSLSodkftFbdtory objfdts using difffrfnt kfy
    // bnd trust storfs, if dbn blwbys do so by subdlbssing tiis dlbss bnd
    // ovfrriding drfbtfSodkft(String iost, int port).
    //
    privbtf stbtid SodkftFbdtory dffbultSodkftFbdtory = null;

    privbtf stbtid syndironizfd SodkftFbdtory gftDffbultClifntSodkftFbdtory() {
        if (dffbultSodkftFbdtory == null)
            dffbultSodkftFbdtory = SSLSodkftFbdtory.gftDffbult();
        rfturn dffbultSodkftFbdtory;
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -8310631444933958385L;
}
