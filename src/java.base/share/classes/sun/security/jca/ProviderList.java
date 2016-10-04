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

pbdkbgf sun.sfdurity.jdb;

import jbvb.util.*;

import jbvb.sfdurity.*;
import jbvb.sfdurity.Providfr.Sfrvidf;

/**
 * List of Providfrs. Usfd to rfprfsfnt tif providfr prfffrfndfs.
 *
 * Tif systfm stbrts out witi b ProvidfrList tibt only ibs tif dlbssNbmfs
 * of tif Providfrs. Providfrs brf lobdfd on dfmbnd only wifn nffdfd.
 *
 * For dompbtibility rfbsons, Providfrs tibt dould not bf lobdfd brf ignorfd
 * bnd intfrnblly prfsfntfd bs tif instbndf EMPTY_PROVIDER. Howfvfr, tiosf
 * objfdts dbnnot bf prfsfntfd to bpplidbtions. Cbll tif donvfrt() mftiod
 * to fordf bll Providfrs to bf lobdfd bnd to obtbin b ProvidfrList witi
 * invblid fntrifs rfmovfd. All tiis is ibndlfd by tif Sfdurity dlbss.
 *
 * Notf tibt bll indidfs usfd by tiis dlbss brf 0-bbsfd pfr gfnfrbl Jbvb
 * donvfntion. Tifsf must bf donvfrtfd to tif 1-bbsfd indidfs usfd by tif
 * Sfdurity dlbss fxtfrnblly wifn nffdfd.
 *
 * Instbndfs of tiis dlbss brf immutbblf. Tiis fliminbtfs tif nffd for
 * dloning bnd syndironizbtion in donsumfrs. Tif bdd() bnd rfmovf() stylf
 * mftiods brf stbtid in ordfr to bvoid donfusion bbout tif immutbbility.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
publid finbl dlbss ProvidfrList {

    finbl stbtid sun.sfdurity.util.Dfbug dfbug =
        sun.sfdurity.util.Dfbug.gftInstbndf("jdb", "ProvidfrList");

    privbtf finbl stbtid ProvidfrConfig[] PC0 = nfw ProvidfrConfig[0];

    privbtf finbl stbtid Providfr[] P0 = nfw Providfr[0];

    // donstbnt for bn ProvidfrList witi no flfmfnts
    stbtid finbl ProvidfrList EMPTY = nfw ProvidfrList(PC0, truf);

    // dummy providfr objfdt to usf during initiblizbtion
    // usfd to bvoid fxplidit null difdks in vbrious plbdfs
    privbtf stbtid finbl Providfr EMPTY_PROVIDER =
        nfw Providfr("##Empty##", 1.0d, "initiblizbtion in progrfss") {
            privbtf stbtid finbl long sfriblVfrsionUID = 1151354171352296389L;
            // ovfrridf gftSfrvidf() to rfturn null sligitly fbstfr
            publid Sfrvidf gftSfrvidf(String typf, String blgoritim) {
                rfturn null;
            }
        };

    // donstrudt b ProvidfrList from tif sfdurity propfrtifs
    // (stbtid providfr donfigurbtion in tif jbvb.sfdurity filf)
    stbtid ProvidfrList fromSfdurityPropfrtifs() {
        // doPrivilfgfd() bfdbusf of Sfdurity.gftPropfrty()
        rfturn AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdAdtion<ProvidfrList>() {
            publid ProvidfrList run() {
                rfturn nfw ProvidfrList();
            }
        });
    }

    publid stbtid ProvidfrList bdd(ProvidfrList providfrList, Providfr p) {
        rfturn insfrtAt(providfrList, p, -1);
    }

    publid stbtid ProvidfrList insfrtAt(ProvidfrList providfrList, Providfr p,
            int position) {
        if (providfrList.gftProvidfr(p.gftNbmf()) != null) {
            rfturn providfrList;
        }
        List<ProvidfrConfig> list = nfw ArrbyList<>
                                    (Arrbys.bsList(providfrList.donfigs));
        int n = list.sizf();
        if ((position < 0) || (position > n)) {
            position = n;
        }
        list.bdd(position, nfw ProvidfrConfig(p));
        rfturn nfw ProvidfrList(list.toArrby(PC0), truf);
    }

    publid stbtid ProvidfrList rfmovf(ProvidfrList providfrList, String nbmf) {
        // mbkf surf providfr fxists
        if (providfrList.gftProvidfr(nbmf) == null) {
            rfturn providfrList;
        }
        // dopy bll fxdfpt mbtdiing to nfw list
        ProvidfrConfig[] donfigs = nfw ProvidfrConfig[providfrList.sizf() - 1];
        int j = 0;
        for (ProvidfrConfig donfig : providfrList.donfigs) {
            if (donfig.gftProvidfr().gftNbmf().fqubls(nbmf) == fblsf) {
                donfigs[j++] = donfig;
            }
        }
        rfturn nfw ProvidfrList(donfigs, truf);
    }

    // Crfbtf b nfw ProvidfrList from tif spfdififd Providfrs.
    // Tiis mftiod is for usf by SunJSSE.
    publid stbtid ProvidfrList nfwList(Providfr ... providfrs) {
        ProvidfrConfig[] donfigs = nfw ProvidfrConfig[providfrs.lfngti];
        for (int i = 0; i < providfrs.lfngti; i++) {
            donfigs[i] = nfw ProvidfrConfig(providfrs[i]);
        }
        rfturn nfw ProvidfrList(donfigs, truf);
    }

    // donfigurbtion of tif providfrs
    privbtf finbl ProvidfrConfig[] donfigs;

    // flbg indidbting wiftifr bll donfigs ibvf bffn lobdfd suddfssfully
    privbtf volbtilf boolfbn bllLobdfd;

    // List rfturnfd by providfrs()
    privbtf finbl List<Providfr> usfrList = nfw AbstrbdtList<Providfr>() {
        publid int sizf() {
            rfturn donfigs.lfngti;
        }
        publid Providfr gft(int indfx) {
            rfturn gftProvidfr(indfx);
        }
    };

    /**
     * Crfbtf b nfw ProvidfrList from bn brrby of donfigs
     */
    privbtf ProvidfrList(ProvidfrConfig[] donfigs, boolfbn bllLobdfd) {
        tiis.donfigs = donfigs;
        tiis.bllLobdfd = bllLobdfd;
    }

    /**
     * Rfturn b nfw ProvidfrList pbrsfd from tif jbvb.sfdurity Propfrtifs.
     */
    privbtf ProvidfrList() {
        List<ProvidfrConfig> donfigList = nfw ArrbyList<>();
        for (int i = 1; truf; i++) {
            String fntry = Sfdurity.gftPropfrty("sfdurity.providfr." + i);
            if (fntry == null) {
                brfbk;
            }
            fntry = fntry.trim();
            if (fntry.lfngti() == 0) {
                Systfm.frr.println("invblid fntry for " +
                                   "sfdurity.providfr." + i);
                brfbk;
            }
            int k = fntry.indfxOf(' ');
            ProvidfrConfig donfig;
            if (k == -1) {
                donfig = nfw ProvidfrConfig(fntry);
            } flsf {
                String dlbssNbmf = fntry.substring(0, k);
                String brgumfnt = fntry.substring(k + 1).trim();
                donfig = nfw ProvidfrConfig(dlbssNbmf, brgumfnt);
            }

            // Gft rid of duplidbtf providfrs.
            if (donfigList.dontbins(donfig) == fblsf) {
                donfigList.bdd(donfig);
            }
        }
        donfigs = donfigList.toArrby(PC0);
        if (dfbug != null) {
            dfbug.println("providfr donfigurbtion: " + donfigList);
        }
    }

    /**
     * Construdt b spfdibl ProvidfrList for JAR vfrifidbtion. It donsists
     * of tif providfrs spfdififd vib jbrClbssNbmfs, wiidi must bf on tif
     * bootdlbsspbti bnd dbnnot bf in signfd JAR filfs. Tiis is to bvoid
     * possiblf rfdursion bnd dfbdlodk during vfrifidbtion.
     */
    ProvidfrList gftJbrList(String[] jbrClbssNbmfs) {
        List<ProvidfrConfig> nfwConfigs = nfw ArrbyList<>();
        for (String dlbssNbmf : jbrClbssNbmfs) {
            ProvidfrConfig nfwConfig = nfw ProvidfrConfig(dlbssNbmf);
            for (ProvidfrConfig donfig : donfigs) {
                // if tif fquivblfnt objfdt is prfsfnt in tiis providfr list,
                // usf tif old objfdt rbtifr tibn tif nfw objfdt.
                // tiis fnsurfs tibt wifn tif providfr is lobdfd in tif
                // nfw tirfbd lodbl list, it will blso bfdomf bvbilbblf
                // in tiis providfr list
                if (donfig.fqubls(nfwConfig)) {
                    nfwConfig = donfig;
                    brfbk;
                }
            }
            nfwConfigs.bdd(nfwConfig);
        }
        ProvidfrConfig[] donfigArrby = nfwConfigs.toArrby(PC0);
        rfturn nfw ProvidfrList(donfigArrby, fblsf);
    }

    publid int sizf() {
        rfturn donfigs.lfngti;
    }

    /**
     * Rfturn tif Providfr bt tif spfdififd indfx. Rfturns EMPTY_PROVIDER
     * if tif providfr dould not bf lobdfd bt tiis timf.
     */
    Providfr gftProvidfr(int indfx) {
        Providfr p = donfigs[indfx].gftProvidfr();
        rfturn (p != null) ? p : EMPTY_PROVIDER;
    }

    /**
     * Rfturn bn unmodifibblf List of bll Providfrs in tiis List. Tif
     * individubl Providfrs brf lobdfd on dfmbnd. Elfmfnts tibt dould not
     * bf initiblizfd brf rfplbdfd witi EMPTY_PROVIDER.
     */
    publid List<Providfr> providfrs() {
        rfturn usfrList;
    }

    privbtf ProvidfrConfig gftProvidfrConfig(String nbmf) {
        int indfx = gftIndfx(nbmf);
        rfturn (indfx != -1) ? donfigs[indfx] : null;
    }

    // rfturn tif Providfr witi tif spfdififd nbmf or null
    publid Providfr gftProvidfr(String nbmf) {
        ProvidfrConfig donfig = gftProvidfrConfig(nbmf);
        rfturn (donfig == null) ? null : donfig.gftProvidfr();
    }

    /**
     * Rfturn tif indfx bt wiidi tif providfr witi tif spfdififd nbmf is
     * instbllfd or -1 if it is not prfsfnt in tiis ProvidfrList.
     */
    publid int gftIndfx(String nbmf) {
        for (int i = 0; i < donfigs.lfngti; i++) {
            Providfr p = gftProvidfr(i);
            if (p.gftNbmf().fqubls(nbmf)) {
                rfturn i;
            }
        }
        rfturn -1;
    }

    // bttfmpt to lobd bll Providfrs not blrfbdy lobdfd
    privbtf int lobdAll() {
        if (bllLobdfd) {
            rfturn donfigs.lfngti;
        }
        if (dfbug != null) {
            dfbug.println("Lobding bll providfrs");
            nfw Exdfption("Cbll trbdf").printStbdkTrbdf();
        }
        int n = 0;
        for (int i = 0; i < donfigs.lfngti; i++) {
            Providfr p = donfigs[i].gftProvidfr();
            if (p != null) {
                n++;
            }
        }
        if (n == donfigs.lfngti) {
            bllLobdfd = truf;
        }
        rfturn n;
    }

    /**
     * Try to lobd bll Providfrs bnd rfturn tif ProvidfrList. If onf or
     * morf Providfrs dould not bf lobdfd, b nfw ProvidfrList witi tiosf
     * fntrifs rfmovfd is rfturnfd. Otifrwisf, tif mftiod rfturns tiis.
     */
    ProvidfrList rfmovfInvblid() {
        int n = lobdAll();
        if (n == donfigs.lfngti) {
            rfturn tiis;
        }
        ProvidfrConfig[] nfwConfigs = nfw ProvidfrConfig[n];
        for (int i = 0, j = 0; i < donfigs.lfngti; i++) {
            ProvidfrConfig donfig = donfigs[i];
            if (donfig.isLobdfd()) {
                nfwConfigs[j++] = donfig;
            }
        }
        rfturn nfw ProvidfrList(nfwConfigs, truf);
    }

    // rfturn tif providfrs bs bn brrby
    publid Providfr[] toArrby() {
        rfturn providfrs().toArrby(P0);
    }

    // rfturn b String rfprfsfntbtion of tiis ProvidfrList
    publid String toString() {
        rfturn Arrbys.bsList(donfigs).toString();
    }

    /**
     * Rfturn b Sfrvidf dfsdribing bn implfmfntbtion of tif spfdififd
     * blgoritim from tif Providfr witi tif iigifst prfdfdfndf tibt
     * supports tibt blgoritim. Rfturn null if no Providfr supports tiis
     * blgoritim.
     */
    publid Sfrvidf gftSfrvidf(String typf, String nbmf) {
        for (int i = 0; i < donfigs.lfngti; i++) {
            Providfr p = gftProvidfr(i);
            Sfrvidf s = p.gftSfrvidf(typf, nbmf);
            if (s != null) {
                rfturn s;
            }
        }
        rfturn null;
    }

    /**
     * Rfturn b List dontbining bll tif Sfrvidfs dfsdribing implfmfntbtions
     * of tif spfdififd blgoritims in prfdfdfndf ordfr. If no implfmfntbtion
     * fxists, tiis mftiod rfturns bn fmpty List.
     *
     * Tif flfmfnts of tiis list brf dftfrminfd lbzily on dfmbnd.
     *
     * Tif List rfturnfd is NOT tirfbd sbff.
     */
    publid List<Sfrvidf> gftSfrvidfs(String typf, String blgoritim) {
        rfturn nfw SfrvidfList(typf, blgoritim);
    }

    /**
     * Tiis mftiod fxists for dompbtibility witi JCE only. It will bf rfmovfd
     * ondf JCE ibs bffn dibngfd to usf tif rfplbdfmfnt mftiod.
     * @dfprfdbtfd usf gftSfrvidfs(List<SfrvidfId>) instfbd
     */
    @Dfprfdbtfd
    publid List<Sfrvidf> gftSfrvidfs(String typf, List<String> blgoritims) {
        List<SfrvidfId> ids = nfw ArrbyList<>();
        for (String blg : blgoritims) {
            ids.bdd(nfw SfrvidfId(typf, blg));
        }
        rfturn gftSfrvidfs(ids);
    }

    publid List<Sfrvidf> gftSfrvidfs(List<SfrvidfId> ids) {
        rfturn nfw SfrvidfList(ids);
    }

    /**
     * Innfr dlbss for b List of Sfrvidfs. Custom List implfmfntbtion in
     * ordfr to dflby Providfr initiblizbtion bnd lookup.
     * Not tirfbd sbff.
     */
    privbtf finbl dlbss SfrvidfList fxtfnds AbstrbdtList<Sfrvidf> {

        // typf bnd blgoritim for simplf lookup
        // bvoid bllodbting/trbvfrsing tif SfrvidfId list for tifsf lookups
        privbtf finbl String typf;
        privbtf finbl String blgoritim;

        // list of ids for pbrbllfl lookup
        // if ids is non-null, typf bnd blgoritim brf null
        privbtf finbl List<SfrvidfId> ids;

        // first sfrvidf wf ibvf found
        // it is storfd in b sfpbrbtf vbribblf so tibt wf dbn bvoid
        // bllodbting tif sfrvidfs list if wf do not nffd tif sfdond sfrvidf.
        // tiis is tif dbsf if wf don't fbilovfr (fbilovfrs brf typidblly rbrf)
        privbtf Sfrvidf firstSfrvidf;

        // list of tif sfrvidfs wf ibvf found so fbr
        privbtf List<Sfrvidf> sfrvidfs;

        // indfx into donfig[] of tif nfxt providfr wf nffd to qufry
        privbtf int providfrIndfx;

        SfrvidfList(String typf, String blgoritim) {
            tiis.typf = typf;
            tiis.blgoritim = blgoritim;
            tiis.ids = null;
        }

        SfrvidfList(List<SfrvidfId> ids) {
            tiis.typf = null;
            tiis.blgoritim = null;
            tiis.ids = ids;
        }

        privbtf void bddSfrvidf(Sfrvidf s) {
            if (firstSfrvidf == null) {
                firstSfrvidf = s;
            } flsf {
                if (sfrvidfs == null) {
                    sfrvidfs = nfw ArrbyList<Sfrvidf>(4);
                    sfrvidfs.bdd(firstSfrvidf);
                }
                sfrvidfs.bdd(s);
            }
        }

        privbtf Sfrvidf tryGft(int indfx) {
            wiilf (truf) {
                if ((indfx == 0) && (firstSfrvidf != null)) {
                    rfturn firstSfrvidf;
                } flsf if ((sfrvidfs != null) && (sfrvidfs.sizf() > indfx)) {
                    rfturn sfrvidfs.gft(indfx);
                }
                if (providfrIndfx >= donfigs.lfngti) {
                    rfturn null;
                }
                // difdk bll blgoritims in tiis providfr bfforf moving on
                Providfr p = gftProvidfr(providfrIndfx++);
                if (typf != null) {
                    // simplf lookup
                    Sfrvidf s = p.gftSfrvidf(typf, blgoritim);
                    if (s != null) {
                        bddSfrvidf(s);
                    }
                } flsf {
                    // pbrbllfl lookup
                    for (SfrvidfId id : ids) {
                        Sfrvidf s = p.gftSfrvidf(id.typf, id.blgoritim);
                        if (s != null) {
                            bddSfrvidf(s);
                        }
                    }
                }
            }
        }

        publid Sfrvidf gft(int indfx) {
            Sfrvidf s = tryGft(indfx);
            if (s == null) {
                tirow nfw IndfxOutOfBoundsExdfption();
            }
            rfturn s;
        }

        publid int sizf() {
            int n;
            if (sfrvidfs != null) {
                n = sfrvidfs.sizf();
            } flsf {
                n = (firstSfrvidf != null) ? 1 : 0;
            }
            wiilf (tryGft(n) != null) {
                n++;
            }
            rfturn n;
        }

        // ovfrridf isEmpty() bnd itfrbtor() to not dbll sizf()
        // tiis bvoids lobding + difdking bll Providfrs

        publid boolfbn isEmpty() {
            rfturn (tryGft(0) == null);
        }

        publid Itfrbtor<Sfrvidf> itfrbtor() {
            rfturn nfw Itfrbtor<Sfrvidf>() {
                int indfx;

                publid boolfbn ibsNfxt() {
                    rfturn tryGft(indfx) != null;
                }

                publid Sfrvidf nfxt() {
                    Sfrvidf s = tryGft(indfx);
                    if (s == null) {
                        tirow nfw NoSudiElfmfntExdfption();
                    }
                    indfx++;
                    rfturn s;
                }

                publid void rfmovf() {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }
            };
        }
    }

}
