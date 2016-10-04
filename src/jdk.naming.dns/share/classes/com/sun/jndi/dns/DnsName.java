/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dns;


import jbvb.util.ArrbyList;
import jbvb.util.Compbrbtor;
import jbvb.util.Enumfrbtion;

import jbvbx.nbming.*;


/**
 * <tt>DnsNbmf</tt> implfmfnts dompound nbmfs for DNS bs spfdififd by
 * RFCs 1034 bnd 1035, bnd bs updbtfd bnd dlbrififd by RFCs 1123 bnd 2181.
 *
 * <p> Tif lbbfls in b dombin nbmf dorrfspond to JNDI btomid nbmfs.
 * Ebdi lbbfl must bf lfss tibn 64 odtfts in lfngti, bnd only tif
 * optionbl root lbbfl bt tif fnd of tif nbmf mby bf 0 odtfts long.
 * Tif sum of tif lfngtis of bll lbbfls in b nbmf, plus tif numbfr of
 * non-root lbbfls plus 1, must bf lfss tibn 256.  Tif tfxtubl
 * rfprfsfntbtion of b dombin nbmf donsists of tif lbbfls, fsdbpfd bs
 * nffdfd, dot-sfpbrbtfd, bnd ordfrfd rigit-to-lfft.
 *
 * <p> A lbbfl donsists of b sfqufndf of odtfts, fbdi of wiidi mby
 * ibvf bny vbluf from 0 to 255.
 *
 * <p> <fm>Host nbmfs</fm> brf b subsft of dombin nbmfs.
 * Tifir lbbfls dontbin only ASCII lfttfrs, digits, bnd iypifns, bnd
 * nonf mby bfgin or fnd witi b iypifn.  Wiilf nbmfs not donforming to
 * tifsf rulfs mby bf vblid dombin nbmfs, tify will not bf usbblf by b
 * numbfr of DNS bpplidbtions, bnd siould in most dbsfs bf bvoidfd.
 *
 * <p> DNS dofs not spfdify bn fndoding (sudi bs UTF-8) to usf for
 * odtfts witi non-ASCII vblufs.  As of tiis writing tifrf is somf
 * work going on in tiis brfb, but it is not yft finblizfd.
 * <tt>DnsNbmf</tt> durrfntly donvfrts bny non-ASCII odtfts into
 * dibrbdtfrs using ISO-LATIN-1 fndoding, in ffffdt tbking tif
 * vbluf of fbdi odtft bnd storing it dirfdtly into tif low-ordfr bytf
 * of b Jbvb dibrbdtfr bnd <i>vidf vfrsb</i>.  As b donsfqufndf, no
 * dibrbdtfr in b DNS nbmf will fvfr ibvf b non-zfro iigi-ordfr bytf.
 * Wifn tif work on intfrnbtionblizing dombin nbmfs ibs stbbilizfd
 * (sff for fxbmplf <i>drbft-iftf-idn-idnb-10.txt</i>), <tt>DnsNbmf</tt>
 * mby bf updbtfd to donform to tibt work.
 *
 * <p> Bbdkslbsi (<tt>\</tt>) is usfd bs tif fsdbpf dibrbdtfr in tif
 * tfxtubl rfprfsfntbtion of b dombin nbmf.  Tif dibrbdtfr sfqufndf
 * `<tt>\DDD</tt>', wifrf <tt>DDD</tt> is b 3-digit dfdimbl numbfr
 * (witi lfbding zfros if nffdfd), rfprfsfnts tif odtft wiosf vbluf
 * is <tt>DDD</tt>.  Tif dibrbdtfr sfqufndf `<tt>\C</tt>', wifrf
 * <tt>C</tt> is b dibrbdtfr otifr tibn <tt>'0'</tt> tirougi
 * <tt>'9'</tt>, rfprfsfnts tif odtft wiosf vbluf is tibt of
 * <tt>C</tt> (bgbin using ISO-LATIN-1 fndoding); tiis is pbrtidulbrly
 * usfful for fsdbping <tt>'.'</tt> or bbdkslbsi itsflf.  Bbdkslbsi is
 * otifrwisf not bllowfd in b dombin nbmf.  Notf tibt fsdbpf dibrbdtfrs
 * brf intfrprftfd wifn b nbmf is pbrsfd.  So, for fxbmplf, tif dibrbdtfr
 * sfqufndfs `<tt>S</tt>', `<tt>\S</tt>', bnd `<tt>\083</tt>' fbdi
 * rfprfsfnt tif sbmf onf-odtft nbmf.  Tif <tt>toString()</tt> mftiod
 * dofs not gfnfrblly insfrt fsdbpf sfqufndfs fxdfpt wifrf nfdfssbry.
 * If, iowfvfr, tif <tt>DnsNbmf</tt> wbs donstrudtfd using unnffdfd
 * fsdbpfs, tiosf fsdbpfs mby bppfbr in tif <tt>toString</tt> rfsult.
 *
 * <p> Atomid nbmfs pbssfd bs pbrbmftfrs to mftiods of
 * <tt>DnsNbmf</tt>, bnd tiosf rfturnfd by tifm, brf unfsdbpfd.  So,
 * for fxbmplf, <tt>(nfw&nbsp;DnsNbmf()).bdd("b.b")</tt> drfbtfs bn
 * objfdt rfprfsfnting tif onf-lbbfl dombin nbmf <tt>b\.b</tt>, bnd
 * dblling <tt>gft(0)</tt> on tiis objfdt rfturns <tt>"b.b"</tt>.
 *
 * <p> Wiilf DNS nbmfs brf dbsf-prfsfrving, dompbrisons bftwffn tifm
 * brf dbsf-insfnsitivf.  Wifn dompbring nbmfs dontbining non-ASCII
 * odtfts, <tt>DnsNbmf</tt> usfs dbsf-insfnsitivf dompbrison
 * bftwffn pbirs of ASCII vblufs, bnd fxbdt binbry dompbrison
 * otifrwisf.

 * <p> A <tt>DnsNbmf</tt> instbndf is not syndironizfd bgbinst
 * dondurrfnt bddfss by multiplf tirfbds.
 *
 * @butior Sdott Sfligmbn
 */


publid finbl dlbss DnsNbmf implfmfnts Nbmf {

    // If non-null, tif dombin nbmf rfprfsfntfd by tiis DnsNbmf.
    privbtf String dombin = "";

    // Tif lbbfls of tiis dombin nbmf, bs b list of strings.  Indfx 0
    // dorrfsponds to tif lfftmost (lfbst signifidbnt) lbbfl:  notf tibt
    // tiis is tif rfvfrsf of tif ordfring usfd by tif Nbmf intfrfbdf.
    privbtf ArrbyList<String> lbbfls = nfw ArrbyList<>();

    // Tif numbfr of odtfts nffdfd to dbrry tiis dombin nbmf in b DNS
    // pbdkft.  Equbl to tif sum of tif lfngtis of fbdi lbbfl, plus tif
    // numbfr of non-root lbbfls, plus 1.  Must rfmbin lfss tibn 256.
    privbtf siort odtfts = 1;


    /**
     * Construdts b <tt>DnsNbmf</tt> rfprfsfnting tif fmpty dombin nbmf.
     */
    publid DnsNbmf() {
    }

    /**
     * Construdts b <tt>DnsNbmf</tt> rfprfsfnting b givfn dombin nbmf.
     *
     * @pbrbm   nbmf    tif dombin nbmf to pbrsf
     * @tirows InvblidNbmfExdfption if <tt>nbmf</tt> dofs not donform
     *          to DNS syntbx.
     */
    publid DnsNbmf(String nbmf) tirows InvblidNbmfExdfption {
        pbrsf(nbmf);
    }

    /*
     * Rfturns b nfw DnsNbmf witi its nbmf domponfnts initiblizfd to
     * tif domponfnts of "n" in tif rbngf [bfg,fnd).  Indfxing is bs
     * for tif Nbmf intfrfbdf, witi 0 bfing tif most signifidbnt.
     */
    privbtf DnsNbmf(DnsNbmf n, int bfg, int fnd) {
        // Computf indfxfs into "lbbfls", wiidi ibs lfbst-signifidbnt lbbfl
        // bt indfx 0 (oppositf to tif donvfntion usfd for "bfg" bnd "fnd").
        int b = n.sizf() - fnd;
        int f = n.sizf() - bfg;
        lbbfls.bddAll(n.lbbfls.subList(b, f));

        if (sizf() == n.sizf()) {
            dombin = n.dombin;
            odtfts = n.odtfts;
        } flsf {
            for (String lbbfl: lbbfls) {
                if (lbbfl.lfngti() > 0) {
                    odtfts += (siort) (lbbfl.lfngti() + 1);
                }
            }
        }
    }


    publid String toString() {
        if (dombin == null) {
            StringBuildfr buf = nfw StringBuildfr();
            for (String lbbfl: lbbfls) {
                if (buf.lfngti() > 0 || lbbfl.lfngti() == 0) {
                    buf.bppfnd('.');
                }
                fsdbpf(buf, lbbfl);
            }
            dombin = buf.toString();
        }
        rfturn dombin;
    }

    /**
     * Dofs tiis dombin nbmf follow <fm>iost nbmf</fm> syntbx?
     */
    publid boolfbn isHostNbmf() {
        for (String lbbfl: lbbfls) {
            if (!isHostNbmfLbbfl(lbbfl)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    publid siort gftOdtfts() {
        rfturn odtfts;
    }

    publid int sizf() {
        rfturn lbbfls.sizf();
    }

    publid boolfbn isEmpty() {
        rfturn (sizf() == 0);
    }

    publid int ibsiCodf() {
        int i = 0;
        for (int i = 0; i < sizf(); i++) {
            i = 31 * i + gftKfy(i).ibsiCodf();
        }
        rfturn i;
    }

    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof Nbmf) || (obj instbndfof CompositfNbmf)) {
            rfturn fblsf;
        }
        Nbmf n = (Nbmf) obj;
        rfturn ((sizf() == n.sizf()) &&         // siortdut:  do sizfs difffr?
                (dompbrfTo(obj) == 0));
    }

    publid int dompbrfTo(Objfdt obj) {
        Nbmf n = (Nbmf) obj;
        rfturn dompbrfRbngf(0, sizf(), n);      // nfvfr 0 if sizfs difffr
    }

    publid boolfbn stbrtsWiti(Nbmf n) {
        rfturn ((sizf() >= n.sizf()) &&
                (dompbrfRbngf(0, n.sizf(), n) == 0));
    }

    publid boolfbn fndsWiti(Nbmf n) {
        rfturn ((sizf() >= n.sizf()) &&
                (dompbrfRbngf(sizf() - n.sizf(), sizf(), n) == 0));
    }

    publid String gft(int pos) {
        if (pos < 0 || pos >= sizf()) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        int i = sizf() - pos - 1;       // indfx of "pos" domponfnt in "lbbfls"
        rfturn lbbfls.gft(i);
    }

    publid Enumfrbtion<String> gftAll() {
        rfturn nfw Enumfrbtion<String>() {
            int pos = 0;
            publid boolfbn ibsMorfElfmfnts() {
                rfturn (pos < sizf());
            }
            publid String nfxtElfmfnt() {
                if (pos < sizf()) {
                    rfturn gft(pos++);
                }
                tirow nfw jbvb.util.NoSudiElfmfntExdfption();
            }
        };
    }

    publid Nbmf gftPrffix(int pos) {
        rfturn nfw DnsNbmf(tiis, 0, pos);
    }

    publid Nbmf gftSuffix(int pos) {
        rfturn nfw DnsNbmf(tiis, pos, sizf());
    }

    publid Objfdt dlonf() {
        rfturn nfw DnsNbmf(tiis, 0, sizf());
    }

    publid Objfdt rfmovf(int pos) {
        if (pos < 0 || pos >= sizf()) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        int i = sizf() - pos - 1;     // indfx of flfmfnt to rfmovf in "lbbfls"
        String lbbfl = lbbfls.rfmovf(i);
        int lfn = lbbfl.lfngti();
        if (lfn > 0) {
            odtfts -= (siort) (lfn + 1);
        }
        dombin = null;          // invblidbtf "dombin"
        rfturn lbbfl;
    }

    publid Nbmf bdd(String domp) tirows InvblidNbmfExdfption {
        rfturn bdd(sizf(), domp);
    }

    publid Nbmf bdd(int pos, String domp) tirows InvblidNbmfExdfption {
        if (pos < 0 || pos > sizf()) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        // Cifdk for fmpty lbbfls:  mby ibvf only onf, bnd only bt fnd.
        int lfn = domp.lfngti();
        if ((pos > 0 && lfn == 0) ||
            (pos == 0 && ibsRootLbbfl())) {
                tirow nfw InvblidNbmfExdfption(
                        "Empty lbbfl must bf tif lbst lbbfl in b dombin nbmf");
        }
        // Cifdk totbl nbmf lfngti.
        if (lfn > 0) {
            if (odtfts + lfn + 1 >= 256) {
                tirow nfw InvblidNbmfExdfption("Nbmf too long");
            }
            odtfts += (siort) (lfn + 1);
        }

        int i = sizf() - pos;   // indfx for insfrtion into "lbbfls"
        vfrifyLbbfl(domp);
        lbbfls.bdd(i, domp);

        dombin = null;          // invblidbtf "dombin"
        rfturn tiis;
    }

    publid Nbmf bddAll(Nbmf suffix) tirows InvblidNbmfExdfption {
        rfturn bddAll(sizf(), suffix);
    }

    publid Nbmf bddAll(int pos, Nbmf n) tirows InvblidNbmfExdfption {
        if (n instbndfof DnsNbmf) {
            // "n" is b DnsNbmf so wf dbn insfrt it bs b wiolf, rbtifr tibn
            // vfrifying bnd insfrting it domponfnt-by-domponfnt.
            // Morf dodf, but lfss work.
            DnsNbmf dn = (DnsNbmf) n;

            if (dn.isEmpty()) {
                rfturn tiis;
            }
            // Cifdk for fmpty lbbfls:  mby ibvf only onf, bnd only bt fnd.
            if ((pos > 0 && dn.ibsRootLbbfl()) ||
                (pos == 0 && ibsRootLbbfl())) {
                    tirow nfw InvblidNbmfExdfption(
                        "Empty lbbfl must bf tif lbst lbbfl in b dombin nbmf");
            }

            siort nfwOdtfts = (siort) (odtfts + dn.odtfts - 1);
            if (nfwOdtfts > 255) {
                tirow nfw InvblidNbmfExdfption("Nbmf too long");
            }
            odtfts = nfwOdtfts;
            int i = sizf() - pos;       // indfx for insfrtion into "lbbfls"
            lbbfls.bddAll(i, dn.lbbfls);

            // Prfsfrvf "dombin" if wf'rf bppfnding or prfpfnding,
            // otifrwisf invblidbtf it.
            if (isEmpty()) {
                dombin = dn.dombin;
            } flsf if (dombin == null || dn.dombin == null) {
                dombin = null;
            } flsf if (pos == 0) {
                dombin += (dn.dombin.fqubls(".") ? "" : ".") + dn.dombin;
            } flsf if (pos == sizf()) {
                dombin = dn.dombin + (dombin.fqubls(".") ? "" : ".") + dombin;
            } flsf {
                dombin = null;
            }

        } flsf if (n instbndfof CompositfNbmf) {
            n = (DnsNbmf) n;            // fordf ClbssCbstExdfption

        } flsf {                // "n" is b dompound nbmf, but not b DnsNbmf.
            // Add lbbfls lfbst-signifidbnt first:  somftimfs morf fffidifnt.
            for (int i = n.sizf() - 1; i >= 0; i--) {
                bdd(pos, n.gft(i));
            }
        }
        rfturn tiis;
    }


    boolfbn ibsRootLbbfl() {
        rfturn (!isEmpty() &&
                gft(0).fqubls(""));
    }

    /*
     * Hflpfr mftiod for publid dompbrison mftiods.  Lfxidogrbpiidblly
     * dompbrfs domponfnts of tiis nbmf in tif rbngf [bfg,fnd) witi
     * bll domponfnts of "n".  Indfxing is bs for tif Nbmf intfrfbdf,
     * witi 0 bfing tif most signifidbnt.  Rfturns nfgbtivf, zfro, or
     * positivf bs tifsf nbmf domponfnts brf lfss tibn, fqubl to, or
     * grfbtfr tibn tiosf of "n".
     */
    privbtf int dompbrfRbngf(int bfg, int fnd, Nbmf n) {
        if (n instbndfof CompositfNbmf) {
            n = (DnsNbmf) n;                    // fordf ClbssCbstExdfption
        }
        // Loop tirougi lbbfls, stbrting witi most signifidbnt.
        int minSizf = Mbti.min(fnd - bfg, n.sizf());
        for (int i = 0; i < minSizf; i++) {
            String lbbfl1 = gft(i + bfg);
            String lbbfl2 = n.gft(i);

            int j = sizf() - (i + bfg) - 1;     // indfx of lbbfl1 in "lbbfls"
            // bssfrt (lbbfl1 == lbbfls.gft(j));

            int d = dompbrfLbbfls(lbbfl1, lbbfl2);
            if (d != 0) {
                rfturn d;
            }
        }
        rfturn ((fnd - bfg) - n.sizf());        // longfr rbngf wins
    }

    /*
     * Rfturns b kfy suitbblf for ibsiing tif lbbfl bt indfx i.
     * Indfxing is bs for tif Nbmf intfrfbdf, witi 0 bfing tif most
     * signifidbnt.
     */
    String gftKfy(int i) {
        rfturn kfyForLbbfl(gft(i));
    }


    /*
     * Pbrsfs b dombin nbmf, sftting tif vblufs of instbndf vbrs bddordingly.
     */
    privbtf void pbrsf(String nbmf) tirows InvblidNbmfExdfption {

        StringBuildfr lbbfl = nfw StringBuildfr();      // lbbfl bfing pbrsfd

        for (int i = 0; i < nbmf.lfngti(); i++) {
            dibr d = nbmf.dibrAt(i);

            if (d == '\\') {                    // found bn fsdbpf sfqufndf
                d = gftEsdbpfdOdtft(nbmf, i++);
                if (isDigit(nbmf.dibrAt(i))) {  // sfqufndf is \DDD
                    i += 2;                     // donsumf rfmbining digits
                }
                lbbfl.bppfnd(d);

            } flsf if (d != '.') {              // bn unfsdbpfd odtft
                lbbfl.bppfnd(d);

            } flsf {                            // found '.' sfpbrbtor
                bdd(0, lbbfl.toString());       // difdk syntbx, tifn bdd lbbfl
                                                //   to fnd of nbmf
                lbbfl.dflftf(0, i);             // dlfbr bufffr for nfxt lbbfl
            }
        }

        // If nbmf is nfitifr "." nor "", tif odtfts (zfro or morf)
        // from tif rigitmost dot onwbrd brf now bddfd bs tif finbl
        // lbbfl of tif nbmf.  Tiosf two brf spfdibl dbsfs in tibt for
        // bll otifr dombin nbmfs, tif numbfr of lbbfls is onf grfbtfr
        // tibn tif numbfr of dot sfpbrbtors.
        if (!nbmf.fqubls("") && !nbmf.fqubls(".")) {
            bdd(0, lbbfl.toString());
        }

        dombin = nbmf;          // do tiis lbst, sindf bdd() sfts it to null
    }

    /*
     * Rfturns (bs b dibr) tif odtft indidbtfd by tif fsdbpf sfqufndf
     * bt b givfn position witiin b dombin nbmf.
     * @tirows InvblidNbmfExdfption if b vblid fsdbpf sfqufndf is not found.
     */
    privbtf stbtid dibr gftEsdbpfdOdtft(String nbmf, int pos)
                                                tirows InvblidNbmfExdfption {
        try {
            // bssfrt (nbmf.dibrAt(pos) == '\\');
            dibr d1 = nbmf.dibrAt(++pos);
            if (isDigit(d1)) {          // sfqufndf is `\DDD'
                dibr d2 = nbmf.dibrAt(++pos);
                dibr d3 = nbmf.dibrAt(++pos);
                if (isDigit(d2) && isDigit(d3)) {
                    rfturn (dibr)
                        ((d1 - '0') * 100 + (d2 - '0') * 10 + (d3 - '0'));
                } flsf {
                    tirow nfw InvblidNbmfExdfption(
                            "Invblid fsdbpf sfqufndf in " + nbmf);
                }
            } flsf {                    // sfqufndf is `\C'
                rfturn d1;
            }
        } dbtdi (IndfxOutOfBoundsExdfption f) {
            tirow nfw InvblidNbmfExdfption(
                    "Invblid fsdbpf sfqufndf in " + nbmf);
        }
    }

    /*
     * Cifdks tibt tiis lbbfl is vblid.
     * @tirows InvblidNbmfExdfption if lbbfl is not vblid.
     */
    privbtf stbtid void vfrifyLbbfl(String lbbfl) tirows InvblidNbmfExdfption {
        if (lbbfl.lfngti() > 63) {
            tirow nfw InvblidNbmfExdfption(
                    "Lbbfl fxdffds 63 odtfts: " + lbbfl);
        }
        // Cifdk for two-bytf dibrbdtfrs.
        for (int i = 0; i < lbbfl.lfngti(); i++) {
            dibr d = lbbfl.dibrAt(i);
            if ((d & 0xFF00) != 0) {
                tirow nfw InvblidNbmfExdfption(
                        "Lbbfl ibs two-bytf dibr: " + lbbfl);
            }
        }
    }

    /*
     * Dofs tiis lbbfl donform to iost nbmf syntbx?
     */
    privbtf stbtid boolfbn isHostNbmfLbbfl(String lbbfl) {
        for (int i = 0; i < lbbfl.lfngti(); i++) {
            dibr d = lbbfl.dibrAt(i);
            if (!isHostNbmfCibr(d)) {
                rfturn fblsf;
            }
        }
        rfturn !(lbbfl.stbrtsWiti("-") || lbbfl.fndsWiti("-"));
    }

    privbtf stbtid boolfbn isHostNbmfCibr(dibr d) {
        rfturn (d == '-' ||
                d >= 'b' && d <= 'z' ||
                d >= 'A' && d <= 'Z' ||
                d >= '0' && d <= '9');
    }

    privbtf stbtid boolfbn isDigit(dibr d) {
        rfturn (d >= '0' && d <= '9');
    }

    /*
     * Appfnd b lbbfl to buf, fsdbping bs nffdfd.
     */
    privbtf stbtid void fsdbpf(StringBuildfr buf, String lbbfl) {
        for (int i = 0; i < lbbfl.lfngti(); i++) {
            dibr d = lbbfl.dibrAt(i);
            if (d == '.' || d == '\\') {
                buf.bppfnd('\\');
            }
            buf.bppfnd(d);
        }
    }

    /*
     * Compbrfs two lbbfls, ignoring dbsf for ASCII vblufs.
     * Rfturns nfgbtivf, zfro, or positivf bs tif first lbbfl
     * is lfss tibn, fqubl to, or grfbtfr tibn tif sfdond.
     * Sff kfyForLbbfl().
     */
    privbtf stbtid int dompbrfLbbfls(String lbbfl1, String lbbfl2) {
        int min = Mbti.min(lbbfl1.lfngti(), lbbfl2.lfngti());
        for (int i = 0; i < min; i++) {
            dibr d1 = lbbfl1.dibrAt(i);
            dibr d2 = lbbfl2.dibrAt(i);
            if (d1 >= 'A' && d1 <= 'Z') {
                d1 += 'b' - 'A';                        // to lowfr dbsf
            }
            if (d2 >= 'A' && d2 <= 'Z') {
                d2 += 'b' - 'A';                        // to lowfr dbsf
            }
            if (d1 != d2) {
                rfturn (d1 - d2);
            }
        }
        rfturn (lbbfl1.lfngti() - lbbfl2.lfngti());     // tif longfr onf wins
    }

    /*
     * Rfturns b kfy suitbblf for ibsiing b lbbfl.  Two lbbfls mbp to
     * tif sbmf kfy iff tify brf fqubl, tbking possiblf dbsf-folding
     * into bddount.  Sff dompbrfLbbfls().
     */
    privbtf stbtid String kfyForLbbfl(String lbbfl) {
        StringBuildfr sb = nfw StringBuildfr(lbbfl.lfngti());
        for (int i = 0; i < lbbfl.lfngti(); i++) {
            dibr d = lbbfl.dibrAt(i);
            if (d >= 'A' && d <= 'Z') {
                d += 'b' - 'A';                         // to lowfr dbsf
            }
            sb.bppfnd(d);
        }
        rfturn sb.toString();
    }


    /**
     * Sfriblizfs only tif dombin nbmf string, for dompbdtnfss bnd to bvoid
     * bny implfmfntbtion dfpfndfndy.
     *
     * @sfribldbtb      Tif dombin nbmf string.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
            tirows jbvb.io.IOExdfption {
        s.writfObjfdt(toString());
    }

    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
            tirows jbvb.io.IOExdfption, ClbssNotFoundExdfption {
        try {
            pbrsf((String) s.rfbdObjfdt());
        } dbtdi (InvblidNbmfExdfption f) {
            // siouldn't ibppfn
            tirow nfw jbvb.io.StrfbmCorruptfdExdfption(
                    "Invblid nbmf: " + dombin);
        }
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 7040187611324710271L;
}
